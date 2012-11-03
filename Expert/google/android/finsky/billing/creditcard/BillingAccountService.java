package com.google.android.finsky.billing.creditcard;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.android.vending.billing.IBillingAccountService.Stub;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.Authenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AvailablePromoOfferActivity;
import com.google.android.finsky.activities.GetMarketMetadataAction;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.BuyInstruments.CheckInstrumentResponse;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.RedeemedPromoOffer;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BillingAccountService extends Service
{
  private Bundle checkPromoOffers(final Account paramAccount)
    throws AuthFailureError
  {
    final Bundle localBundle = new Bundle();
    final Semaphore localSemaphore = new Semaphore(0);
    final AndroidAuthenticator localAndroidAuthenticator = new AndroidAuthenticator(FinskyApp.get(), paramAccount, (String)G.checkoutAuthTokenType.get());
    final AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
    final String str = localAndroidAuthenticator.getAuthToken();
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(paramAccount.name);
    Analytics localAnalytics = FinskyApp.get().getAnalytics(paramAccount.name);
    localDfeApi.checkPromoOffers(str, new Response.Listener()
    {
      public void onResponse(CheckPromoOffer.CheckPromoOfferResponse paramAnonymousCheckPromoOfferResponse)
      {
        if (paramAnonymousCheckPromoOfferResponse.getCheckoutTokenRequired())
        {
          FinskyLog.w("Checkout token invalid.", new Object[0]);
          localAndroidAuthenticator.invalidateAuthToken(str);
          localAtomicBoolean.set(true);
        }
        while (true)
        {
          localSemaphore.release();
          return;
          if (paramAnonymousCheckPromoOfferResponse.getAvailableOfferCount() > 0)
          {
            localBundle.putInt("result_code", 1);
            Intent localIntent = AvailablePromoOfferActivity.getIntent(paramAccount.name, paramAnonymousCheckPromoOfferResponse.getAvailableOffer(0));
            localBundle.putParcelable("available_offer_redemption_intent", localIntent);
          }
          else if (paramAnonymousCheckPromoOfferResponse.hasRedeemedOffer())
          {
            localBundle.putInt("result_code", 2);
            String str = paramAnonymousCheckPromoOfferResponse.getRedeemedOffer().getDescriptionHtml();
            localBundle.putString("redeemed_offer_message_html", str);
          }
          else
          {
            localBundle.putInt("result_code", 3);
          }
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.e("Error while checking for offers: %s", new Object[] { paramAnonymousVolleyError });
        int i = -2;
        if ((paramAnonymousVolleyError instanceof ServerError))
          i = -1;
        localBundle.putInt("result_code", i);
        localSemaphore.release();
      }
    });
    try
    {
      if (!localSemaphore.tryAcquire(45L, TimeUnit.SECONDS))
        localBundle.putInt("result_code", -4);
      if (localAtomicBoolean.get())
      {
        logOfferResultCode(localAnalytics, -3);
        throw new AuthFailureError();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localBundle.putInt("result_code", -4);
      int i = localBundle.getInt("result_code");
      logOfferResultCode(localAnalytics, i);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      FinskyLog.d("CheckPromoOffers result: %d", arrayOfObject);
    }
    return localBundle;
  }

  private void checkValidInstrument(final Authenticator paramAuthenticator, final DfeApi paramDfeApi, final String paramString, final int[] paramArrayOfInt, final Semaphore paramSemaphore)
  {
    paramDfeApi.checkInstrument(paramString, null, new Response.Listener()
    {
      public void onResponse(BuyInstruments.CheckInstrumentResponse paramAnonymousCheckInstrumentResponse)
      {
        if (paramAnonymousCheckInstrumentResponse.getCheckoutTokenRequired())
        {
          paramAuthenticator.invalidateAuthToken(paramString);
          FinskyLog.w("Received checkout_token_required.", new Object[0]);
          BillingAccountService.returnResult(paramDfeApi, -3, paramArrayOfInt, paramSemaphore);
          return;
        }
        if (paramAnonymousCheckInstrumentResponse.getUserHasValidInstrument());
        for (int i = 1; ; i = 2)
        {
          BillingAccountService.returnResult(paramDfeApi, i, paramArrayOfInt, paramSemaphore);
          break;
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.w("Received error: %s", new Object[] { paramAnonymousVolleyError });
        BillingAccountService.returnResult(paramDfeApi, BillingAccountService.access$400(BillingAccountService.this, paramAnonymousVolleyError), paramArrayOfInt, paramSemaphore);
      }
    });
  }

  private int convertErrorCode(VolleyError paramVolleyError)
  {
    int i;
    if ((paramVolleyError instanceof ServerError))
      i = -1;
    while (true)
    {
      return i;
      if ((paramVolleyError instanceof NetworkError))
        i = -2;
      else if ((paramVolleyError instanceof AuthFailureError))
        i = -3;
      else if ((paramVolleyError instanceof TimeoutError))
        i = -4;
      else
        i = 0;
    }
  }

  private static void logOfferResultCode(Analytics paramAnalytics, int paramInt)
  {
    if (paramInt > 0);
    for (String str = "promoOfferCheck"; ; str = "promoOfferCheckError")
    {
      paramAnalytics.logPageView("externalPackage", null, str + "?code=" + paramInt);
      return;
    }
  }

  private static void logResult(DfeApi paramDfeApi, int paramInt)
  {
    new DfeAnalytics(new Handler(Looper.getMainLooper()), paramDfeApi).logPageView("externalPackage", null, "checkInstrument?result=" + paramInt);
  }

  private static void returnResult(DfeApi paramDfeApi, int paramInt, int[] paramArrayOfInt, Semaphore paramSemaphore)
  {
    logResult(paramDfeApi, paramInt);
    paramArrayOfInt[0] = paramInt;
    paramSemaphore.release();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return new IBillingAccountService.Stub()
    {
      public Bundle getOffers(String paramAnonymousString)
      {
        Utils.ensureNotOnMainThread();
        Account localAccount = AccountHandler.findAccount(paramAnonymousString, BillingAccountService.this);
        Object localObject;
        if (localAccount == null)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(paramAnonymousString);
          FinskyLog.e("Received invalid account name: %s", arrayOfObject);
          localObject = new Bundle();
          ((Bundle)localObject).putInt("result_code", -5);
        }
        while (true)
        {
          return localObject;
          try
          {
            Bundle localBundle2 = BillingAccountService.this.checkPromoOffers(localAccount);
            localObject = localBundle2;
          }
          catch (AuthFailureError localAuthFailureError1)
          {
            try
            {
              Bundle localBundle1 = BillingAccountService.this.checkPromoOffers(localAccount);
              localObject = localBundle1;
            }
            catch (AuthFailureError localAuthFailureError2)
            {
              localObject = new Bundle();
              ((Bundle)localObject).putInt("result_code", -3);
            }
          }
        }
      }

      public int hasValidCreditCard(String paramAnonymousString)
        throws RemoteException
      {
        Utils.ensureNotOnMainThread();
        int[] arrayOfInt = new int[1];
        Semaphore localSemaphore = new Semaphore(0);
        Account localAccount = AccountHandler.findAccount(paramAnonymousString, BillingAccountService.this);
        int i;
        if (localAccount == null)
        {
          FinskyLog.e("Received invalid account name: " + paramAnonymousString, new Object[0]);
          i = -5;
        }
        while (true)
        {
          return i;
          AndroidAuthenticator localAndroidAuthenticator = new AndroidAuthenticator(BillingAccountService.this, localAccount, (String)G.checkoutAuthTokenType.get());
          DfeApi localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
          try
          {
            String str = localAndroidAuthenticator.getAuthToken();
            new GetMarketMetadataAction().run(BillingAccountService.this, localAccount.name, new BillingAccountService.GetMarketMetadataListener(BillingAccountService.this, localDfeApi, localAndroidAuthenticator, str, arrayOfInt, localSemaphore), new BillingAccountService.GetMarketMetadataErrorListener(BillingAccountService.this, localDfeApi, arrayOfInt, localSemaphore));
            if (!localSemaphore.tryAcquire(45L, TimeUnit.SECONDS))
              arrayOfInt[0] = -4;
            BillingAccountService.logResult(localDfeApi, arrayOfInt[0]);
            i = arrayOfInt[0];
          }
          catch (InterruptedException localInterruptedException)
          {
            FinskyLog.d("Timed out while waiting for response.", new Object[0]);
            BillingAccountService.logResult(localDfeApi, -4);
            i = -4;
          }
          catch (AuthFailureError localAuthFailureError)
          {
            FinskyLog.w("AuthFailureError: " + localAuthFailureError.getMessage(), new Object[0]);
            BillingAccountService.logResult(localDfeApi, -3);
            i = -3;
          }
        }
      }
    };
  }

  private class GetMarketMetadataErrorListener
    implements Response.ErrorListener
  {
    private final DfeApi mDfeApi;
    private final int[] mResultCodeOut;
    private final Semaphore mSemaphore;

    public GetMarketMetadataErrorListener(DfeApi paramArrayOfInt, int[] paramSemaphore, Semaphore arg4)
    {
      this.mDfeApi = paramArrayOfInt;
      this.mResultCodeOut = paramSemaphore;
      Object localObject;
      this.mSemaphore = localObject;
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      BillingAccountService.returnResult(this.mDfeApi, BillingAccountService.access$400(BillingAccountService.this, paramVolleyError), this.mResultCodeOut, this.mSemaphore);
    }
  }

  private class GetMarketMetadataListener
    implements Response.Listener<VendingProtos.GetMarketMetadataResponseProto>
  {
    private final Authenticator mAuthenticator;
    private final String mCheckoutToken;
    private final DfeApi mDfeApi;
    private final int[] mResultCodeOut;
    private final Semaphore mSemaphore;

    public GetMarketMetadataListener(DfeApi paramAuthenticator, Authenticator paramString, String paramArrayOfInt, int[] paramSemaphore, Semaphore arg6)
    {
      this.mDfeApi = paramAuthenticator;
      this.mAuthenticator = paramString;
      this.mCheckoutToken = paramArrayOfInt;
      this.mResultCodeOut = paramSemaphore;
      Object localObject;
      this.mSemaphore = localObject;
    }

    public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
    {
      if ((paramGetMarketMetadataResponseProto.getInAppBillingEnabled()) || (paramGetMarketMetadataResponseProto.getPaidAppsEnabled()))
      {
        FinskyLog.d("Paid apps enabled.", new Object[0]);
        BillingAccountService.this.checkValidInstrument(this.mAuthenticator, this.mDfeApi, this.mCheckoutToken, this.mResultCodeOut, this.mSemaphore);
      }
      while (true)
      {
        return;
        FinskyLog.w("Paid apps disabled.", new Object[0]);
        BillingAccountService.returnResult(this.mDfeApi, 3, this.mResultCodeOut, this.mSemaphore);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.BillingAccountService
 * JD-Core Version:    0.6.2
 */
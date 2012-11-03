package com.google.android.finsky.billing;

import android.accounts.Account;
import android.content.Intent;
import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.Authenticator;
import com.google.android.finsky.activities.AvailablePromoOfferActivity;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.CheckPromoOfferResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class CheckPromoOffersAction
  implements AsyncAuthenticator.Listener
{
  private static boolean sRunning = false;
  private final Account mAccount;
  private final MainActivity mActivity;
  private final Authenticator mAuthenticator;
  private Runnable mCallback;
  private final DfeApi mDfeApi;
  private int sNumAuthRetries = 1;

  public CheckPromoOffersAction(MainActivity paramMainActivity, DfeApi paramDfeApi)
  {
    this.mDfeApi = paramDfeApi;
    this.mAccount = paramDfeApi.getApiContext().getAccount();
    this.mActivity = paramMainActivity;
    this.mAuthenticator = new AndroidAuthenticator(this.mActivity, this.mAccount, (String)G.checkoutAuthTokenType.get());
  }

  private void checkPromoOffers(String paramString)
  {
    new AsyncAuthenticator(this.mAuthenticator).getToken(this, paramString);
  }

  public void onAuthTokenReceived(final String paramString)
  {
    this.mDfeApi.checkPromoOffers(paramString, new Response.Listener()
    {
      public void onResponse(CheckPromoOffer.CheckPromoOfferResponse paramAnonymousCheckPromoOfferResponse)
      {
        Intent localIntent = null;
        if (paramAnonymousCheckPromoOfferResponse.getCheckoutTokenRequired())
        {
          FinskyLog.w("Checkout token invalid.", new Object[0]);
          if (CheckPromoOffersAction.this.sNumAuthRetries > 0)
          {
            CheckPromoOffersAction.this.checkPromoOffers(paramString);
            CheckPromoOffersAction.access$006(CheckPromoOffersAction.this);
            return;
          }
          FinskyLog.e("auth retries exceeded, skipping promo offer check", new Object[0]);
        }
        while (true)
        {
          FinskyPreferences.checkPromoOffers.get(CheckPromoOffersAction.this.mAccount.name).remove();
          if (localIntent != null)
            CheckPromoOffersAction.this.mActivity.startActivity(localIntent);
          CheckPromoOffersAction.access$402(false);
          CheckPromoOffersAction.this.mCallback.run();
          break;
          if (paramAnonymousCheckPromoOfferResponse.getAvailableOfferCount() > 0)
            localIntent = AvailablePromoOfferActivity.getIntent(CheckPromoOffersAction.this.mAccount.name, paramAnonymousCheckPromoOfferResponse.getAvailableOffer(0));
          else if (!paramAnonymousCheckPromoOfferResponse.hasRedeemedOffer());
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.e("Error while checking for offers: %s", new Object[] { paramAnonymousVolleyError });
        CheckPromoOffersAction.access$402(false);
        CheckPromoOffersAction.this.mCallback.run();
      }
    });
  }

  public void onError(AuthFailureError paramAuthFailureError)
  {
    FinskyLog.e("Could not retrieve Checkout auth token.", new Object[0]);
    sRunning = false;
    this.mCallback.run();
  }

  public void run(Runnable paramRunnable)
  {
    this.mCallback = paramRunnable;
    if ((((Boolean)FinskyPreferences.checkPromoOffers.get(this.mAccount.name).get()).booleanValue()) && (!sRunning))
    {
      sRunning = true;
      checkPromoOffers(null);
    }
    while (true)
    {
      return;
      this.mCallback.run();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.CheckPromoOffersAction
 * JD-Core Version:    0.6.2
 */
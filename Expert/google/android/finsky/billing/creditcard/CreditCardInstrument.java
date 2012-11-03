package com.google.android.finsky.billing.creditcard;

import android.accounts.Account;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.InstrumentFactory.FormOfPayment;
import com.google.android.finsky.billing.UpdateAddressFlow;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;

public class CreditCardInstrument extends Instrument
{
  public CreditCardInstrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    super(paramCheckoutOption, paramDrawable);
  }

  public static void registerWithFactory(InstrumentFactory paramInstrumentFactory)
  {
    paramInstrumentFactory.registerFormOfPayment(0, new InstrumentFactory.FormOfPayment()
    {
      private String mAddCcString = FinskyApp.get().getString(2131165246);

      public boolean canAdd()
      {
        return true;
      }

      public BillingFlow create(BillingFlowContext paramAnonymousBillingFlowContext, BillingFlowListener paramAnonymousBillingFlowListener, Bundle paramAnonymousBundle)
      {
        Account localAccount = AccountHandler.findAccount(paramAnonymousBundle.getString("authAccount"), FinskyApp.get());
        if (localAccount == null)
          FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
        DfeApi localDfeApi;
        for (Object localObject = null; ; localObject = new CreateCreditCardFlow(paramAnonymousBillingFlowContext, paramAnonymousBillingFlowListener, new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), localDfeApi, new DfeAnalytics(new Handler(Looper.getMainLooper()), localDfeApi), paramAnonymousBundle))
        {
          return localObject;
          localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
        }
      }

      public Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramAnonymousCheckoutOption, Drawable paramAnonymousDrawable)
      {
        return new CreditCardInstrument(paramAnonymousCheckoutOption, paramAnonymousDrawable);
      }

      public Drawable getAddIcon()
      {
        return FinskyApp.get().getResources().getDrawable(2130837595);
      }

      public String getAddText()
      {
        return this.mAddCcString;
      }

      public String getUpdateAddressText()
      {
        return FinskyApp.get().getString(2131165296);
      }

      public BillingFlow updateAddress(BillingFlowContext paramAnonymousBillingFlowContext, BillingFlowListener paramAnonymousBillingFlowListener, Bundle paramAnonymousBundle)
      {
        Account localAccount = AccountHandler.findAccount(paramAnonymousBundle.getString("authAccount"), FinskyApp.get());
        if (localAccount == null)
          FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
        DfeApi localDfeApi;
        for (Object localObject = null; ; localObject = new UpdateAddressFlow(paramAnonymousBillingFlowContext, paramAnonymousBillingFlowListener, new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), localDfeApi, new DfeAnalytics(new Handler(Looper.getMainLooper()), localDfeApi), paramAnonymousBundle))
        {
          return localObject;
          localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
        }
      }

      public void updateStatus(CommonDevice.Instrument paramAnonymousInstrument)
      {
        if (paramAnonymousInstrument.hasDisplayTitle());
        for (this.mAddCcString = paramAnonymousInstrument.getDisplayTitle(); ; this.mAddCcString = FinskyApp.get().getString(2131165246))
          return;
      }
    });
  }

  public BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    return null;
  }

  public static class CreditCardNumberFilter
    implements InputFilter
  {
    public static String getNumbers(CharSequence paramCharSequence)
    {
      StringBuilder localStringBuilder = new StringBuilder(16);
      int i = 0;
      int j = paramCharSequence.length();
      while (i < j)
      {
        char c = paramCharSequence.charAt(i);
        if (isNumber(c))
          localStringBuilder.append(c);
        i++;
      }
      return localStringBuilder.toString();
    }

    private static boolean isAllowed(char paramChar1, char paramChar2)
    {
      if ((isNumber(paramChar1)) || ((isSeparator(paramChar1)) && (isNumber(paramChar2))));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    private static boolean isNumber(char paramChar)
    {
      if ((paramChar >= '0') && (paramChar <= '9'));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    private static boolean isSeparator(char paramChar)
    {
      if ((paramChar == ' ') || (paramChar == '-'));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      char c1;
      int i;
      Object localObject;
      if (paramInt3 > 0)
      {
        c1 = paramSpanned.charAt(paramInt3 - 1);
        i = paramInt2 - paramInt1;
        if (i != 1)
          break label63;
        if (!isAllowed(paramCharSequence.charAt(paramInt1), c1))
          break label56;
        localObject = null;
      }
      while (true)
      {
        return localObject;
        c1 = '\000';
        break;
        label56: localObject = "";
        continue;
        label63: if (i == 0)
        {
          localObject = null;
        }
        else
        {
          char[] arrayOfChar = new char[i];
          int j = 0;
          int k = paramInt1;
          if (k < paramInt2)
          {
            char c2 = paramCharSequence.charAt(k);
            if (isAllowed(c2, c1))
            {
              arrayOfChar[0] = c2;
              c1 = c2;
            }
            while (true)
            {
              k++;
              break;
              j = 1;
            }
          }
          if (j != 0)
            localObject = new String(arrayOfChar, 0, 0);
          else
            localObject = null;
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.CreditCardInstrument
 * JD-Core Version:    0.6.2
 */
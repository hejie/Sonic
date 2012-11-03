package com.google.android.finsky.billing.carrierbilling;

import android.accounts.Account;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.InstrumentFactory.FormOfPayment;
import com.google.android.finsky.billing.UpdateAddressFlow;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;

public abstract class CarrierBillingFop extends InstrumentFactory.FormOfPayment
{
  public Drawable getAddIcon()
  {
    return FinskyApp.get().getResources().getDrawable(2130837595);
  }

  public String getAddText()
  {
    if (BillingLocator.getCarrierBillingStorage().getParams() == null);
    FinskyApp localFinskyApp;
    Object[] arrayOfObject;
    for (String str = null; ; str = localFinskyApp.getString(2131165269, arrayOfObject))
    {
      return str;
      localFinskyApp = FinskyApp.get();
      arrayOfObject = new Object[1];
      arrayOfObject[0] = BillingLocator.getCarrierBillingStorage().getParams().getName();
    }
  }

  public String getUpdateAddressText()
  {
    FinskyApp localFinskyApp;
    Object[] arrayOfObject;
    if (BillingLocator.getCarrierBillingStorage().getParams() != null)
    {
      localFinskyApp = FinskyApp.get();
      arrayOfObject = new Object[1];
      arrayOfObject[0] = BillingLocator.getCarrierBillingStorage().getParams().getName();
    }
    for (String str = localFinskyApp.getString(2131165297, arrayOfObject); ; str = FinskyApp.get().getString(2131165298))
      return str;
  }

  public BillingFlow updateAddress(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    Account localAccount = AccountHandler.findAccount(paramBundle.getString("authAccount"), FinskyApp.get());
    if (localAccount == null)
      FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
    DfeApi localDfeApi;
    for (Object localObject = null; ; localObject = new UpdateAddressFlow(paramBillingFlowContext, paramBillingFlowListener, new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), localDfeApi, new DfeAnalytics(new Handler(Looper.getMainLooper()), localDfeApi), paramBundle))
    {
      return localObject;
      localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.CarrierBillingFop
 * JD-Core Version:    0.6.2
 */
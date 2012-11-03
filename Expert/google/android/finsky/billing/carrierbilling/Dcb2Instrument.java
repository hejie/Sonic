package com.google.android.finsky.billing.carrierbilling;

import android.accounts.Account;
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
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.flow.CompleteCarrierBillingFlow;
import com.google.android.finsky.billing.carrierbilling.flow.CreateCarrierBillingFlow;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dcb2Instrument extends Instrument
{
  private boolean mCanFetchCredentials;

  public Dcb2Instrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    super(paramCheckoutOption, paramDrawable);
    if ((CarrierBillingUtils.isRadioNetworkAvailable()) || (CarrierBillingUtils.areCredentialsValid(BillingLocator.getCarrierBillingStorage())));
    for (boolean bool = true; ; bool = false)
    {
      this.mCanFetchCredentials = bool;
      return;
    }
  }

  public static void registerWithFactory(InstrumentFactory paramInstrumentFactory)
  {
    paramInstrumentFactory.registerFormOfPayment(2, 2, new CarrierBillingFop()
    {
      public boolean canAdd()
      {
        return CarrierBillingUtils.isRadioNetworkAvailable();
      }

      public BillingFlow create(BillingFlowContext paramAnonymousBillingFlowContext, BillingFlowListener paramAnonymousBillingFlowListener, Bundle paramAnonymousBundle)
      {
        Account localAccount = AccountHandler.findAccount(paramAnonymousBundle.getString("authAccount"), FinskyApp.get());
        if (localAccount == null)
          FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
        DfeApi localDfeApi;
        for (Object localObject = null; ; localObject = new CreateCarrierBillingFlow(paramAnonymousBillingFlowContext, paramAnonymousBillingFlowListener, new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), localDfeApi, new DfeAnalytics(new Handler(Looper.getMainLooper()), localDfeApi), paramAnonymousBundle))
        {
          return localObject;
          localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
        }
      }

      public Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramAnonymousCheckoutOption, Drawable paramAnonymousDrawable)
      {
        FinskyApp localFinskyApp = FinskyApp.get();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = BillingLocator.getCarrierBillingStorage().getParams().getName();
        paramAnonymousCheckoutOption.setFormOfPayment(localFinskyApp.getString(2131165269, arrayOfObject));
        return new Dcb2Instrument(paramAnonymousCheckoutOption, paramAnonymousDrawable);
      }

      public Map<String, String> getPrepareParams()
      {
        Object localObject = null;
        CarrierBillingStorage localCarrierBillingStorage = BillingLocator.getCarrierBillingStorage();
        CarrierBillingProvisioning localCarrierBillingProvisioning = localCarrierBillingStorage.getProvisioning();
        if (localCarrierBillingProvisioning == null)
          new CarrierProvisioningAction().run(null);
        while (true)
        {
          return localObject;
          if (localCarrierBillingProvisioning.isProvisioned())
          {
            HashMap localHashMap = Maps.newHashMap();
            localHashMap.put("dcbch", localCarrierBillingStorage.getCurrentSimIdentifier());
            Long localLong = Long.valueOf(localCarrierBillingProvisioning.getTransactionLimit());
            if (localLong != null)
              localHashMap.put("dcbtl", localLong.toString());
            String str = localCarrierBillingProvisioning.getSubscriberCurrency();
            if (str != null)
              localHashMap.put("dcbsc", str);
            localObject = Collections.unmodifiableMap(localHashMap);
          }
          else
          {
            FinskyLog.d("Not provisioned, not including identifier with params", new Object[0]);
          }
        }
      }
    });
  }

  public BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    return new CompleteCarrierBillingFlow(paramBillingFlowContext, paramBillingFlowListener, FinskyApp.get().getAnalytics(), FinskyApp.get().getEventLogger(), paramBundle);
  }

  public Map<String, String> getCompleteParams()
  {
    CarrierBillingCredentials localCarrierBillingCredentials = BillingLocator.getCarrierBillingStorage().getCredentials();
    if (localCarrierBillingCredentials == null);
    HashMap localHashMap;
    for (Object localObject = null; ; localObject = Collections.unmodifiableMap(localHashMap))
    {
      return localObject;
      localHashMap = Maps.newHashMap();
      localHashMap.put("dcbct", localCarrierBillingCredentials.getCredentials());
      Long localLong = Long.valueOf(localCarrierBillingCredentials.getExpirationTime());
      if (localLong != null)
        localHashMap.put("dcbctt", localLong.toString());
    }
  }

  public List<String> getMessages()
  {
    Object localObject;
    if (!this.mCanFetchCredentials)
    {
      localObject = new ArrayList();
      ((List)localObject).add(FinskyApp.get().getString(2131165305));
      ((List)localObject).addAll(super.getMessages());
    }
    while (true)
    {
      return localObject;
      localObject = super.getMessages();
    }
  }

  public boolean hasMessages()
  {
    if ((super.hasMessages()) || (!this.mCanFetchCredentials));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isValid()
  {
    if ((this.mCanFetchCredentials) && (super.isValid()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.Dcb2Instrument
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.carrierbilling;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.FinskyCreateInstrumentActivity;
import com.google.android.finsky.analytics.DfeAnalytics;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.carrierbilling.flow.CompleteDcb3Flow;
import com.google.android.finsky.billing.carrierbilling.flow.CreateDcb3Flow;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dcb3Instrument extends Instrument
{
  private String mPassphrase;

  public Dcb3Instrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    super(paramCheckoutOption, paramDrawable);
  }

  private static boolean canAssociate()
  {
    boolean bool1 = false;
    if (!BillingLocator.isDeviceInService())
      return bool1;
    if ((((Boolean)G.dcb3SetupWhileRoamingEnabled.get()).booleanValue()) || (!BillingLocator.isNetworkRoaming()));
    for (boolean bool2 = true; ; bool2 = false)
    {
      bool1 = bool2;
      break;
    }
  }

  private static Map<String, String> getDcb3UserIdentifierParams()
  {
    HashMap localHashMap = Maps.newHashMap();
    String str1 = BillingLocator.getCarrierBillingStorage().getCurrentSimIdentifier();
    if (!TextUtils.isEmpty(str1))
      localHashMap.put("dcbch", str1);
    if (((Boolean)G.dcb3SendProvisioningData.get()).booleanValue())
    {
      String str2 = BillingLocator.getSubscriberIdFromTelephony();
      if (!TextUtils.isEmpty(str2))
        localHashMap.put("dcbsubid", str2);
      String str3 = BillingLocator.getDeviceIdFromTelephony();
      if (!TextUtils.isEmpty(str3))
        localHashMap.put("dcbhardwareid", str3);
      String str4 = BillingLocator.getSimSerialNumberFromTelephony();
      if (!TextUtils.isEmpty(str4))
        localHashMap.put("dcbsimserialnumber", str4);
    }
    if (performDeviceBootedCheck())
      localHashMap.put("dcbdevicerebooted", "true");
    return localHashMap;
  }

  private CommonDevice.CarrierBillingInstrumentStatus getInstrumentStatus()
  {
    Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = getCheckoutOption();
    CommonDevice.Instrument localInstrument;
    if (localCheckoutOption.hasInstrument())
    {
      localInstrument = localCheckoutOption.getInstrument();
      if (!localInstrument.hasCarrierBillingStatus());
    }
    for (CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = localInstrument.getCarrierBillingStatus(); ; localCarrierBillingInstrumentStatus = null)
    {
      return localCarrierBillingInstrumentStatus;
      FinskyLog.w("CheckoutOption does not have instrument status.", new Object[0]);
    }
  }

  private boolean isAssociationValid()
  {
    CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = getInstrumentStatus();
    if ((localCarrierBillingInstrumentStatus != null) && (!localCarrierBillingInstrumentStatus.getAssociationRequired()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private static boolean performDeviceBootedCheck()
  {
    long l1 = System.currentTimeMillis();
    long l2 = SystemClock.elapsedRealtime();
    if (l1 - ((Long)BillingPreferences.LAST_DCB3_PROVISIONING_TIME_MILLIS.get()).longValue() > l2);
    for (boolean bool = true; ; bool = false)
    {
      BillingPreferences.LAST_DCB3_PROVISIONING_TIME_MILLIS.put(Long.valueOf(System.currentTimeMillis()));
      return bool;
    }
  }

  public static void registerWithFactory(InstrumentFactory paramInstrumentFactory)
  {
    paramInstrumentFactory.registerFormOfPayment(2, 3, new CarrierBillingFop()
    {
      private void storeDcb3Status(CommonDevice.Instrument paramAnonymousInstrument)
      {
        if (paramAnonymousInstrument.hasCarrierBillingStatus())
          CarrierBillingUtils.storeDcbStatus(paramAnonymousInstrument.getCarrierBillingStatus());
      }

      public boolean canAdd()
      {
        return Dcb3Instrument.access$000();
      }

      public BillingFlow create(BillingFlowContext paramAnonymousBillingFlowContext, BillingFlowListener paramAnonymousBillingFlowListener, Bundle paramAnonymousBundle)
      {
        Account localAccount = AccountHandler.findAccount(paramAnonymousBundle.getString("authAccount"), FinskyApp.get());
        if (localAccount == null)
          FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
        DfeApi localDfeApi;
        for (Object localObject = null; ; localObject = new CreateDcb3Flow(paramAnonymousBillingFlowContext, paramAnonymousBillingFlowListener, new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), localDfeApi, new DfeAnalytics(new Handler(Looper.getMainLooper()), localDfeApi), paramAnonymousBundle))
        {
          return localObject;
          localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
        }
      }

      public Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramAnonymousCheckoutOption, Drawable paramAnonymousDrawable)
      {
        if (BillingLocator.getCarrierBillingStorage().getParams() != null)
        {
          FinskyApp localFinskyApp = FinskyApp.get();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = BillingLocator.getCarrierBillingStorage().getParams().getName();
          paramAnonymousCheckoutOption.setFormOfPayment(localFinskyApp.getString(2131165269, arrayOfObject));
        }
        if (paramAnonymousCheckoutOption.hasInstrument())
          storeDcb3Status(paramAnonymousCheckoutOption.getInstrument());
        return new Dcb3Instrument(paramAnonymousCheckoutOption, paramAnonymousDrawable);
      }

      public Intent getCreateIntent(CommonDevice.Instrument paramAnonymousInstrument, String paramAnonymousString1, int paramAnonymousInt, BillingUtils.CreateInstrumentUiMode paramAnonymousCreateInstrumentUiMode, String paramAnonymousString2, String paramAnonymousString3)
      {
        Intent localIntent = FinskyCreateInstrumentActivity.createIntent(paramAnonymousString1, paramAnonymousInstrument, paramAnonymousInt, BillingUtils.CreateInstrumentUiMode.INTERNAL, false, paramAnonymousString2, paramAnonymousString3);
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("dcb_instrument", ParcelableProto.forProto(paramAnonymousInstrument));
        localIntent.putExtra("extra_paramters", localBundle);
        return localIntent;
      }

      public Map<String, String> getPrepareParams()
      {
        return Dcb3Instrument.access$100();
      }

      public void updateStatus(CommonDevice.Instrument paramAnonymousInstrument)
      {
        storeDcb3Status(paramAnonymousInstrument);
      }
    });
  }

  public BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    Account localAccount = AccountHandler.findAccount(paramBundle.getString("authAccount"), FinskyApp.get());
    Object localObject;
    if (localAccount == null)
    {
      FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
      localObject = null;
    }
    while (true)
    {
      return localObject;
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = getCheckoutOption();
      if (localCheckoutOption.hasInstrument())
      {
        paramBundle.putParcelable("dcb_instrument", ParcelableProto.forProto(localCheckoutOption.getInstrument()));
        DfeApi localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
        AsyncAuthenticator localAsyncAuthenticator = new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get()));
        localObject = new CompleteDcb3Flow(paramBillingFlowContext, localDfeApi, paramBillingFlowListener, FinskyApp.get().getAnalytics(), FinskyApp.get().getEventLogger(), paramBundle, this, localAsyncAuthenticator);
      }
      else
      {
        FinskyLog.wtf("Checkout option does not contain valid DCB instrument.", new Object[0]);
        localObject = null;
      }
    }
  }

  public Map<String, String> getCompleteParams()
  {
    HashMap localHashMap = Maps.newHashMap();
    String str = BillingLocator.getCarrierBillingStorage().getCurrentSimIdentifier();
    if (!TextUtils.isEmpty(str))
      localHashMap.put("dcbch", str);
    if (!TextUtils.isEmpty(this.mPassphrase))
      localHashMap.put("dcbpassphrase", this.mPassphrase);
    return localHashMap;
  }

  public List<String> getMessages()
  {
    Object localObject;
    if ((!isAssociationValid()) && (!canAssociate()))
    {
      localObject = Lists.newArrayList();
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
    if ((super.hasMessages()) || ((!isAssociationValid()) && (!canAssociate())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isValid()
  {
    if (((isAssociationValid()) || (canAssociate())) && (super.isValid()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void setPassphrase(String paramString)
  {
    this.mPassphrase = paramString;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.Dcb3Instrument
 * JD-Core Version:    0.6.2
 */
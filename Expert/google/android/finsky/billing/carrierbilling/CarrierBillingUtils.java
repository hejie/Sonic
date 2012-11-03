package com.google.android.finsky.billing.carrierbilling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierParamsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTos;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTosEntry;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarrierBillingUtils
{
  public static ArrayList<Integer> GetRetriableErrors(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    ArrayList localArrayList;
    if (paramUpdateInstrumentResponse.getResult() == 2)
    {
      localArrayList = getInvalidEntries(paramUpdateInstrumentResponse.getErrorInputFieldList());
      if ((localArrayList == null) || (localArrayList.isEmpty()));
    }
    while (true)
    {
      return localArrayList;
      localArrayList = null;
    }
  }

  public static boolean areCredentialsValid(CarrierBillingStorage paramCarrierBillingStorage)
  {
    boolean bool1 = false;
    CarrierBillingCredentials localCarrierBillingCredentials = paramCarrierBillingStorage.getCredentials();
    if (localCarrierBillingCredentials == null)
      return bool1;
    long l1 = ((Long)G.vendingCarrierCredentialsBufferMs.get()).longValue();
    long l2 = localCarrierBillingCredentials.getExpirationTime() - l1;
    long l3 = System.currentTimeMillis();
    if ((isProvisioned(paramCarrierBillingStorage)) && (!TextUtils.isEmpty(localCarrierBillingCredentials.getCredentials())) && (l2 > l3));
    for (boolean bool2 = true; ; bool2 = false)
    {
      bool1 = bool2;
      break;
    }
  }

  private static ArrayList<Integer> getInvalidEntries(List<ChallengeProtos.InputValidationError> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ChallengeProtos.InputValidationError localInputValidationError = (ChallengeProtos.InputValidationError)localIterator.next();
      int i = localInputValidationError.getInputField();
      switch (i)
      {
      case 10:
      case 11:
      case 12:
      default:
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(localInputValidationError.getInputField());
        arrayOfObject[1] = localInputValidationError.getErrorMessage();
        FinskyLog.d("InputValidationError that can't be edited: type=%d, message=%s", arrayOfObject);
        break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 13:
        localArrayList.add(Integer.valueOf(i));
      }
    }
    return localArrayList;
  }

  public static void initializeCarrierBillingParams(Context paramContext, final Runnable paramRunnable)
  {
    new CarrierParamsAction(FinskyApp.get().getMarketMetadata()).run(new Runnable()
    {
      public void run()
      {
        if ((CarrierBillingUtils.isCarrierBillingParamsAvailable()) && (!CarrierBillingUtils.isDcb30()))
          new CarrierProvisioningAction().runIfNotOnWifi(this.val$context);
        if (paramRunnable != null)
          paramRunnable.run();
      }
    });
  }

  public static void initializeCarrierBillingProvisioning(Runnable paramRunnable)
  {
    if (isCarrierBillingParamsAvailable())
      new CarrierProvisioningAction().run(paramRunnable);
    while (true)
    {
      return;
      if (paramRunnable != null)
        paramRunnable.run();
    }
  }

  public static void initializeCarrierBillingStorage(Runnable paramRunnable)
  {
    new CarrierBillingAction().run(paramRunnable);
  }

  public static boolean isCarrierBillingParamsAvailable()
  {
    if ((BillingLocator.getCarrierBillingStorage() != null) && (BillingLocator.getCarrierBillingStorage().getParams() != null));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isDcb30()
  {
    boolean bool = true;
    CarrierBillingStorage localCarrierBillingStorage = BillingLocator.getCarrierBillingStorage();
    if (localCarrierBillingStorage == null)
      FinskyLog.wtf("CarrierBillingStorage is null, fallback to 3.0", new Object[0]);
    while (true)
    {
      return bool;
      CarrierBillingParameters localCarrierBillingParameters = localCarrierBillingStorage.getParams();
      if ((localCarrierBillingParameters != null) && (localCarrierBillingParameters.getCarrierApiVersion() != 3))
        bool = false;
    }
  }

  public static boolean isProvisioned(CarrierBillingStorage paramCarrierBillingStorage)
  {
    boolean bool = false;
    if (paramCarrierBillingStorage == null)
      FinskyLog.wtf("CarrierBillingStorage is null. Return false", new Object[0]);
    while (true)
    {
      return bool;
      CarrierBillingProvisioning localCarrierBillingProvisioning = paramCarrierBillingStorage.getProvisioning();
      if (localCarrierBillingProvisioning != null)
        bool = localCarrierBillingProvisioning.isProvisioned();
    }
  }

  public static boolean isRadioNetworkAvailable()
  {
    boolean bool = false;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)FinskyApp.get().getSystemService("connectivity")).getNetworkInfo(0);
    if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
      bool = true;
    return bool;
  }

  public static void registerDcbInstrument(InstrumentFactory paramInstrumentFactory)
  {
    if (!isDcb30())
    {
      if (FinskyLog.DEBUG)
        FinskyLog.v("Register DCB 2.0 Instrument.", new Object[0]);
      Dcb2Instrument.registerWithFactory(paramInstrumentFactory);
      if (isProvisioned(BillingLocator.getCarrierBillingStorage()))
      {
        if (FinskyLog.DEBUG)
          FinskyLog.v("Register DCB 3.0 Instrument.", new Object[0]);
        Dcb3Instrument.registerWithFactory(paramInstrumentFactory);
      }
    }
    while (true)
    {
      return;
      if (FinskyLog.DEBUG)
        FinskyLog.v("Register DCB 3.0 Instrument.", new Object[0]);
      Dcb3Instrument.registerWithFactory(paramInstrumentFactory);
    }
  }

  public static void storeDcbStatus(CommonDevice.CarrierBillingInstrumentStatus paramCarrierBillingInstrumentStatus)
  {
    CommonDevice.CarrierTosEntry localCarrierTosEntry1 = new CommonDevice.CarrierTosEntry();
    CommonDevice.CarrierTosEntry localCarrierTosEntry2 = new CommonDevice.CarrierTosEntry();
    boolean bool = false;
    if (paramCarrierBillingInstrumentStatus.hasCarrierTos())
    {
      CommonDevice.CarrierTos localCarrierTos = paramCarrierBillingInstrumentStatus.getCarrierTos();
      if (localCarrierTos.hasDcbTos())
        localCarrierTosEntry1 = localCarrierTos.getDcbTos();
      if (localCarrierTos.hasPiiTos())
        localCarrierTosEntry2 = localCarrierTos.getPiiTos();
    }
    if (paramCarrierBillingInstrumentStatus.hasAssociationRequired())
      bool = paramCarrierBillingInstrumentStatus.getAssociationRequired();
    BillingPreferences.CARRIER_DCB_TOS_URL.put(localCarrierTosEntry1.getUrl());
    BillingPreferences.CARRIER_DCB_TOS_VERSION.put(localCarrierTosEntry1.getVersion());
    BillingPreferences.CARRIER_PII_TOS_URL.put(localCarrierTosEntry2.getUrl());
    BillingPreferences.CARRIER_PII_TOS_VERSION.put(localCarrierTosEntry2.getVersion());
    BillingPreferences.DEVICE_ASSOCIATION_NEEDED.put(Boolean.valueOf(bool));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils
 * JD-Core Version:    0.6.2
 */
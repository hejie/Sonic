package com.google.android.finsky.billing;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.api.DcbApiContext;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.GoogleProxyHttpClientStack;
import com.google.android.finsky.remoting.RadioConnectionFactoryImpl;
import com.google.android.finsky.remoting.RadioHttpClient;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BillingLocator
{
  private static boolean isInitialized = false;
  private static Context sApplicationContext;
  private static CarrierBillingStorage sCarrierBillingStorage;
  private static boolean sDeviceInService = false;

  public static DcbApi createDcbApi()
  {
    Utils.ensureOnMainThread();
    if (sApplicationContext == null);
    RadioHttpClient localRadioHttpClient;
    for (DcbApi localDcbApi = null; ; localDcbApi = new DcbApi(new RequestQueue(new NoCache(), new BasicNetwork(localRadioHttpClient)), new DcbApiContext(getCarrierBillingStorage(), getLine1NumberFromTelephony(), getSubscriberIdFromTelephony())))
    {
      return localDcbApi;
      RadioConnectionFactoryImpl localRadioConnectionFactoryImpl = new RadioConnectionFactoryImpl((ConnectivityManager)sApplicationContext.getSystemService("connectivity"));
      localRadioHttpClient = new RadioHttpClient(new GoogleProxyHttpClientStack(sApplicationContext), localRadioConnectionFactoryImpl);
    }
  }

  public static List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> getBillingCountries()
  {
    Utils.ensureOnMainThread();
    ArrayList localArrayList = Lists.newArrayList();
    String str1 = (String)BillingPreferences.BILLING_COUNTRIES.get();
    if (str1 == null)
    {
      localArrayList = null;
      return localArrayList;
    }
    String[] arrayOfString1 = str1.split("\\}\\{");
    int i = arrayOfString1.length;
    int j = 0;
    label38: String str2;
    if (j < i)
    {
      str2 = arrayOfString1[j];
      if (str2.length() != 0)
        break label73;
      FinskyLog.w("Got empty billing country string.", new Object[0]);
    }
    while (true)
    {
      j++;
      break label38;
      break;
      label73: if (str2.charAt(0) == '{')
        str2 = str2.substring(1);
      if (str2.charAt(-1 + str2.length()) == '}')
        str2 = str2.substring(0, -1 + str2.length());
      String[] arrayOfString2 = str2.split(",");
      if (arrayOfString2.length < 2)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = str2;
        arrayOfObject[1] = Integer.valueOf(arrayOfString2.length);
        FinskyLog.w("Invalid country string: %s. Expected at least 2 parts, got %d.", arrayOfObject);
      }
      else
      {
        VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry = new VendingProtos.PurchaseMetadataResponseProto.Countries.Country();
        localCountry.setCountryCode(arrayOfString2[0]);
        localCountry.setCountryName(arrayOfString2[1]);
        if (arrayOfString2.length >= 3)
        {
          if ((!arrayOfString2[2].equals("1")) && (!arrayOfString2[2].equals("0")))
            FinskyLog.w("Invalid reducedBillingAddress flag: " + arrayOfString2[2], new Object[0]);
          else
            localCountry.setAllowsReducedBillingAddress(arrayOfString2[2].equals("1"));
        }
        else
          localArrayList.add(localCountry);
      }
    }
  }

  public static CarrierBillingStorage getCarrierBillingStorage()
  {
    Utils.ensureOnMainThread();
    if ((sCarrierBillingStorage == null) && (sApplicationContext != null))
      throw new IllegalStateException("CarrierBillingStorage has not been initialized.");
    return sCarrierBillingStorage;
  }

  public static String getDeviceIdFromTelephony()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
    if (localTelephonyManager == null);
    for (String str = null; ; str = localTelephonyManager.getDeviceId())
      return str;
  }

  public static String getLine1NumberFromTelephony()
  {
    Utils.ensureOnMainThread();
    if (sApplicationContext == null)
      throw new IllegalStateException("BillingLocator has not been initialized.");
    String str = ((TelephonyManager)sApplicationContext.getSystemService("phone")).getLine1Number();
    if (str == null)
      str = "";
    return str;
  }

  public static String getSimSerialNumberFromTelephony()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
    if (localTelephonyManager == null);
    for (String str = null; ; str = localTelephonyManager.getSimSerialNumber())
      return str;
  }

  public static String getSubscriberIdFromTelephony()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
    if (localTelephonyManager == null);
    for (String str = null; ; str = localTelephonyManager.getSubscriberId())
      return str;
  }

  public static void initCarrierBillingStorage(Runnable paramRunnable)
  {
    Utils.ensureOnMainThread();
    setupServiceStateListener();
    sCarrierBillingStorage.init(paramRunnable);
  }

  public static void initSingleton()
  {
    if (!isInitialized)
    {
      isInitialized = true;
      sApplicationContext = FinskyApp.get();
      sCarrierBillingStorage = new CarrierBillingStorage(sApplicationContext);
      return;
    }
    throw new IllegalStateException("BillingLocator already initialized.");
  }

  public static boolean isDeviceInService()
  {
    return sDeviceInService;
  }

  public static boolean isNetworkRoaming()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
    if (localTelephonyManager == null);
    for (boolean bool = false; ; bool = localTelephonyManager.isNetworkRoaming())
      return bool;
  }

  public static void setBillingCountries(List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> paramList)
  {
    Utils.ensureOnMainThread();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    if (localIterator.hasNext())
    {
      VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry = (VendingProtos.PurchaseMetadataResponseProto.Countries.Country)localIterator.next();
      localStringBuilder.append('{').append(localCountry.getCountryCode()).append(',');
      localStringBuilder.append(localCountry.getCountryName()).append(',');
      if (localCountry.getAllowsReducedBillingAddress());
      for (char c = '1'; ; c = '0')
      {
        localStringBuilder.append(c).append('}');
        break;
      }
    }
    BillingPreferences.BILLING_COUNTRIES.put(localStringBuilder.toString());
    BillingPreferences.LAST_BILLING_COUNTRIES_REFRESH_MILLIS.put(Long.valueOf(System.currentTimeMillis()));
  }

  private static void setupServiceStateListener()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
    if (localTelephonyManager == null);
    while (true)
    {
      return;
      localTelephonyManager.listen(new PhoneStateListener()
      {
        public void onServiceStateChanged(ServiceState paramAnonymousServiceState)
        {
          if (paramAnonymousServiceState.getState() == 0)
            BillingLocator.access$002(true);
          while (true)
          {
            return;
            BillingLocator.access$002(false);
          }
        }
      }
      , 1);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingLocator
 * JD-Core Version:    0.6.2
 */
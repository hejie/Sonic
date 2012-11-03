package com.google.android.finsky.billing.carrierbilling.action;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.billing.BillingEventRecorder;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierProvisioningAction
{
  private final DcbApi mDcbApi;
  private final CarrierBillingStorage mDcbStorage;

  public CarrierProvisioningAction()
  {
    this(BillingLocator.getCarrierBillingStorage(), BillingLocator.createDcbApi());
  }

  public CarrierProvisioningAction(CarrierBillingStorage paramCarrierBillingStorage, DcbApi paramDcbApi)
  {
    this.mDcbStorage = paramCarrierBillingStorage;
    this.mDcbApi = paramDcbApi;
  }

  private void fetchProvisioning(String paramString, Response.Listener<CarrierBillingProvisioning> paramListener, Response.ErrorListener paramErrorListener)
  {
    this.mDcbApi.getProvisioning(paramString, paramListener, paramErrorListener);
  }

  private void fetchProvisioning(String paramString, final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    CarrierBillingParameters localCarrierBillingParameters = this.mDcbStorage.getParams();
    if (localCarrierBillingParameters == null)
      if (paramRunnable2 != null)
        paramRunnable2.run();
    while (true)
    {
      return;
      final String str = localCarrierBillingParameters.getId();
      fetchProvisioning(paramString, new Response.Listener()
      {
        public void onResponse(CarrierBillingProvisioning paramAnonymousCarrierBillingProvisioning)
        {
          long l1 = System.currentTimeMillis();
          if (paramAnonymousCarrierBillingProvisioning == null)
          {
            FinskyLog.w("Fetching provisioning returned null.", new Object[0]);
            long l3 = ((Long)G.vendingCarrierProvisioningRetryMs.get()).longValue();
            BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(l1 + l3));
            BillingEventRecorder.recordError(str, 0, "SERVER");
          }
          while (true)
          {
            if (paramRunnable1 != null)
              paramRunnable1.run();
            return;
            long l2 = ((Long)G.vendingCarrierProvisioningRefreshFrequencyMs.get()).longValue();
            BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(l1 + l2));
            CarrierProvisioningAction.this.mDcbStorage.setProvisioning(paramAnonymousCarrierBillingProvisioning);
            BillingEventRecorder.recordSuccess(str, 0, paramAnonymousCarrierBillingProvisioning.isProvisioned());
          }
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          FinskyLog.d("CarrierProvisioningAction encountered an error: %s", new Object[] { paramAnonymousVolleyError });
          String str = String.format("%s/%s/%s", new Object[] { DfeUtils.getLegacyErrorCode(paramAnonymousVolleyError), paramAnonymousVolleyError.getMessage(), paramAnonymousVolleyError.getClass().getCanonicalName() });
          long l1 = System.currentTimeMillis();
          long l2 = ((Long)G.vendingCarrierProvisioningRetryMs.get()).longValue();
          BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(l1 + l2));
          BillingEventRecorder.recordError(str, 0, str);
          if (paramRunnable2 != null)
            paramRunnable2.run();
        }
      });
      updateBillingPreferences(System.currentTimeMillis());
    }
  }

  public static boolean shouldFetchProvisioning(CarrierBillingStorage paramCarrierBillingStorage)
  {
    return shouldFetchProvisioning(paramCarrierBillingStorage, System.currentTimeMillis(), SystemClock.elapsedRealtime(), ((Long)BillingPreferences.LAST_PROVISIONING_TIME_MILLIS.get()).longValue(), ((Long)BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.get()).longValue());
  }

  static boolean shouldFetchProvisioning(CarrierBillingStorage paramCarrierBillingStorage, long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    boolean bool1 = true;
    CarrierBillingParameters localCarrierBillingParameters = paramCarrierBillingStorage.getParams();
    if ((localCarrierBillingParameters == null) || (localCarrierBillingParameters.getGetProvisioningUrl() == null))
    {
      FinskyLog.d("Required CarrierBillingParams missing. Shouldn't fetch provisioning.", new Object[0]);
      bool1 = false;
    }
    label50: label90: label94: 
    while (true)
    {
      return bool1;
      boolean bool2;
      if (paramLong1 - paramLong3 > paramLong2)
      {
        bool2 = bool1;
        if (paramLong1 <= paramLong4)
          break label90;
      }
      for (boolean bool3 = bool1; ; bool3 = false)
      {
        if ((bool3) || ((!CarrierBillingUtils.isProvisioned(paramCarrierBillingStorage)) && (bool2)))
          break label94;
        bool1 = false;
        break;
        bool2 = false;
        break label50;
      }
    }
  }

  private void updateBillingPreferences(long paramLong)
  {
    long l = Math.min(((Long)G.vendingCarrierProvisioningRefreshFrequencyMs.get()).longValue(), ((Long)G.vendingCarrierProvisioningRetryMs.get()).longValue());
    BillingPreferences.EARLIEST_PROVISIONING_CHECK_TIME_MILLIS.put(Long.valueOf(paramLong + l));
    BillingPreferences.LAST_PROVISIONING_TIME_MILLIS.put(Long.valueOf(paramLong));
  }

  public void forceRun(Runnable paramRunnable1, Runnable paramRunnable2)
  {
    fetchProvisioning(null, paramRunnable1, paramRunnable2);
  }

  public void forceRun(Runnable paramRunnable1, Runnable paramRunnable2, String paramString)
  {
    fetchProvisioning(paramString, paramRunnable1, paramRunnable2);
  }

  public void run(Runnable paramRunnable)
  {
    if (shouldFetchProvisioning(this.mDcbStorage))
      fetchProvisioning((String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get(), paramRunnable, paramRunnable);
    while (true)
    {
      return;
      if (paramRunnable != null)
        paramRunnable.run();
      FinskyLog.d("No need to fetch provisioning from carrier.", new Object[0]);
    }
  }

  public void runIfNotOnWifi(Context paramContext)
  {
    if (shouldRunIfNotOnWifi(paramContext))
      run(null);
  }

  public boolean shouldRunIfNotOnWifi(Context paramContext)
  {
    int i = 1;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(i);
    if ((localNetworkInfo == null) || (localNetworkInfo.isConnectedOrConnecting()))
      i = 0;
    return i;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction
 * JD-Core Version:    0.6.2
 */
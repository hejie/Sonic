package com.google.android.finsky.billing.carrierbilling.action;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.billing.BillingEventRecorder;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierCredentialsAction
{
  private final CarrierBillingStorage mDcbStorage;

  public CarrierCredentialsAction()
  {
    this(BillingLocator.getCarrierBillingStorage());
  }

  public CarrierCredentialsAction(CarrierBillingStorage paramCarrierBillingStorage)
  {
    this.mDcbStorage = paramCarrierBillingStorage;
  }

  public void run(String paramString1, String paramString2, final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    DcbApi localDcbApi = BillingLocator.createDcbApi();
    CarrierBillingParameters localCarrierBillingParameters = this.mDcbStorage.getParams();
    if (localCarrierBillingParameters == null)
      paramRunnable2.run();
    while (true)
    {
      return;
      final String str = localCarrierBillingParameters.getId();
      localDcbApi.getCredentials(paramString1, paramString2, new Response.Listener()
      {
        public void onResponse(CarrierBillingCredentials paramAnonymousCarrierBillingCredentials)
        {
          if (paramAnonymousCarrierBillingCredentials != null)
          {
            CarrierCredentialsAction.this.mDcbStorage.setCredentials(paramAnonymousCarrierBillingCredentials);
            BillingEventRecorder.recordSuccess(str, 1, CarrierBillingUtils.areCredentialsValid(CarrierCredentialsAction.this.mDcbStorage));
          }
          while (true)
          {
            if (paramRunnable1 != null)
              paramRunnable1.run();
            return;
            FinskyLog.d("Fetching credentials returned null.", new Object[0]);
            BillingEventRecorder.recordError(str, 1, "SERVER");
          }
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          FinskyLog.d("Fetching credentials returned an error: %s", new Object[] { paramAnonymousVolleyError });
          String str = String.format("%s/%s/%s", new Object[] { DfeUtils.getLegacyErrorCode(paramAnonymousVolleyError), paramAnonymousVolleyError.getMessage(), paramAnonymousVolleyError.getClass().getCanonicalName() });
          BillingEventRecorder.recordError(str, 1, str);
          if (paramRunnable2 != null)
            paramRunnable2.run();
        }
      });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.action.CarrierCredentialsAction
 * JD-Core Version:    0.6.2
 */
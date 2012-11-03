package com.google.android.finsky.billing;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.BillingEventRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.BillingEventResponseProto;

public class BillingEventRecorder
{
  private static final Response.ErrorListener LOGGING_ERROR_LISTENER = new Response.ErrorListener()
  {
    public void onErrorResponse(VolleyError paramAnonymousVolleyError)
    {
      FinskyLog.w("BillingEventRecorder got error: %s", new Object[] { paramAnonymousVolleyError });
    }
  };
  private static final Response.Listener<VendingProtos.BillingEventResponseProto> NOP_LISTENER = new Response.Listener()
  {
    public void onResponse(VendingProtos.BillingEventResponseProto paramAnonymousBillingEventResponseProto)
    {
    }
  };

  public static void recordError(String paramString1, int paramInt, String paramString2)
  {
    if (!((Boolean)BillingPreferences.LOG_BILLING_EVENTS.get()).booleanValue());
    while (true)
    {
      return;
      VendingProtos.BillingEventRequestProto localBillingEventRequestProto = new VendingProtos.BillingEventRequestProto().setBillingParametersId(paramString1).setEventType(paramInt).setResultSuccess(false).setClientMessage(paramString2);
      FinskyApp.get().getVendingApi().recordBillingEvent(localBillingEventRequestProto, NOP_LISTENER, LOGGING_ERROR_LISTENER);
    }
  }

  public static void recordSuccess(String paramString, int paramInt, boolean paramBoolean)
  {
    if (!((Boolean)BillingPreferences.LOG_BILLING_EVENTS.get()).booleanValue());
    while (true)
    {
      return;
      VendingProtos.BillingEventRequestProto localBillingEventRequestProto = new VendingProtos.BillingEventRequestProto().setBillingParametersId(paramString).setEventType(paramInt).setResultSuccess(paramBoolean);
      FinskyApp.get().getVendingApi().recordBillingEvent(localBillingEventRequestProto, NOP_LISTENER, LOGGING_ERROR_LISTENER);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingEventRecorder
 * JD-Core Version:    0.6.2
 */
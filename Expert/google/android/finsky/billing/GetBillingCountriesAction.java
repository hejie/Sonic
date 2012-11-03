package com.google.android.finsky.billing;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.List;

public class GetBillingCountriesAction
{
  private boolean canSkip()
  {
    long l = ((Long)BillingPreferences.LAST_BILLING_COUNTRIES_REFRESH_MILLIS.get()).longValue();
    if (!enoughTimePassed(System.currentTimeMillis(), l));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  boolean enoughTimePassed(long paramLong1, long paramLong2)
  {
    if (paramLong1 > paramLong2 + ((Long)G.vendingBillingCountriesRefreshMs.get()).longValue());
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void run(String paramString, final Runnable paramRunnable)
  {
    if (canSkip())
    {
      if (paramRunnable != null)
        paramRunnable.run();
      FinskyLog.d("Skip getting fresh list of billing countries.", new Object[0]);
    }
    while (true)
    {
      return;
      FinskyApp.get().getVendingApi(paramString).getBillingCountries(new Response.Listener()
      {
        public void onResponse(List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> paramAnonymousList)
        {
          BillingLocator.setBillingCountries(paramAnonymousList);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramAnonymousList.size());
          FinskyLog.d("Received and stored %d billing countries.", arrayOfObject);
          if (paramRunnable != null)
            paramRunnable.run();
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          FinskyLog.w("PurchaseMetadataRequest failed: %s", new Object[] { paramAnonymousVolleyError });
          if (paramRunnable != null)
            paramRunnable.run();
        }
      });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.GetBillingCountriesAction
 * JD-Core Version:    0.6.2
 */
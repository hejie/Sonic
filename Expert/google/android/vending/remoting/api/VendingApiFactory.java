package com.google.android.vending.remoting.api;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.android.volley.RequestQueue;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.Maps;
import java.util.Locale;
import java.util.Map;

public class VendingApiFactory
{
  private Context mContext;
  private RequestQueue mQueue;
  private final Map<String, VendingApi> mVendingApiMap = Maps.newHashMap();

  public VendingApiFactory(Context paramContext, RequestQueue paramRequestQueue)
  {
    this.mContext = paramContext;
    this.mQueue = paramRequestQueue;
  }

  private VendingApiContext getApiContext(Account paramAccount)
  {
    try
    {
      int i = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
      TelephonyManager localTelephonyManager = (TelephonyManager)this.mContext.getSystemService("phone");
      VendingApiContext localVendingApiContext = new VendingApiContext(this.mContext, paramAccount, Locale.getDefault(), Long.toHexString(((Long)DfeApiConfig.androidId.get()).longValue()), i, localTelephonyManager.getNetworkOperatorName(), localTelephonyManager.getSimOperatorName(), localTelephonyManager.getNetworkOperator(), localTelephonyManager.getSimOperator(), Build.DEVICE, Build.VERSION.SDK, (String)DfeApiConfig.clientId.get(), (String)DfeApiConfig.loggingId.get());
      return localVendingApiContext;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException("Can't find our own package", localNameNotFoundException);
    }
  }

  public VendingApi getApi(String paramString)
  {
    synchronized (this.mVendingApiMap)
    {
      VendingApi localVendingApi = (VendingApi)this.mVendingApiMap.get(paramString);
      if (localVendingApi == null)
      {
        localVendingApi = new VendingApi(this.mQueue, getApiContext(new Account(paramString, "com.google")));
        this.mVendingApiMap.put(paramString, localVendingApi);
      }
      return localVendingApi;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.VendingApiFactory
 * JD-Core Version:    0.6.2
 */
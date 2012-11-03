package com.google.android.finsky.activities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.LegacyDeviceConfigurationHelper;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingPreferences;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import java.util.concurrent.Semaphore;

public class GetMarketMetadataAction
{
  private void showWarningIfNecessary(Context paramContext, VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
  {
    int i = 0;
    long l1 = ((Long)VendingPreferences.LAST_METADATA_WARNING_TIMESTAMP.get()).longValue();
    long l2 = System.currentTimeMillis();
    if (l1 == 0L)
      i = 1;
    while (true)
    {
      if (i != 0)
      {
        VendingPreferences.LAST_METADATA_WARNING_TIMESTAMP.put(Long.valueOf(l2));
        Toast.makeText(paramContext, paramGetMarketMetadataResponseProto.getWarningMessage(), 1).show();
      }
      return;
      if (l1 + ((Long)G.vendingWarningMessageFrequencyMs.get()).longValue() < l2)
        i = 1;
    }
  }

  public void run(final Context paramContext, final String paramString, final Response.Listener<VendingProtos.GetMarketMetadataResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingProtos.GetMarketMetadataRequestProto localGetMarketMetadataRequestProto = new VendingProtos.GetMarketMetadataRequestProto();
    localGetMarketMetadataRequestProto.setDeviceConfiguration(LegacyDeviceConfigurationHelper.getDeviceConfiguration());
    localGetMarketMetadataRequestProto.setDeviceRoaming(((TelephonyManager)paramContext.getSystemService("phone")).isNetworkRoaming());
    try
    {
      Signature[] arrayOfSignature = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures;
      for (int i = 0; i < arrayOfSignature.length; i++)
        localGetMarketMetadataRequestProto.addMarketSignatureHash(Md5Util.secureHash(arrayOfSignature[i].toByteArray()));
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localGetMarketMetadataRequestProto.setDeviceModelName(Build.MODEL);
      localGetMarketMetadataRequestProto.setDeviceManufacturerName(Build.MANUFACTURER);
      VendingApi localVendingApi = FinskyApp.get().getVendingApi(paramString);
      Response.Listener local1 = new Response.Listener()
      {
        public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramAnonymousGetMarketMetadataResponseProto)
        {
          InAppBillingSetting.setEnabled(paramString, paramAnonymousGetMarketMetadataResponseProto.getInAppBillingEnabled());
          BillingPreferences.LOG_BILLING_EVENTS.put(Boolean.valueOf(paramAnonymousGetMarketMetadataResponseProto.getBillingEventsEnabled()));
          if ((!((Boolean)G.vendingHideWarningMessage.get()).booleanValue()) && (paramAnonymousGetMarketMetadataResponseProto.hasWarningMessage()))
            GetMarketMetadataAction.this.showWarningIfNecessary(paramContext, paramAnonymousGetMarketMetadataResponseProto);
          if (paramListener != null)
            paramListener.onResponse(paramAnonymousGetMarketMetadataResponseProto);
        }
      };
      localVendingApi.getMetadata(localGetMarketMetadataRequestProto, LegacyDeviceConfigurationHelper.getHashCode(localGetMarketMetadataRequestProto.getDeviceConfiguration()), FinskyApp.get().getVersionCode(), local1, paramErrorListener);
    }
  }

  public VendingProtos.GetMarketMetadataResponseProto runBlocking(Context paramContext, String paramString)
  {
    Utils.ensureNotOnMainThread();
    final Semaphore localSemaphore = new Semaphore(0);
    final VendingProtos.GetMarketMetadataResponseProto[] arrayOfGetMarketMetadataResponseProto = new VendingProtos.GetMarketMetadataResponseProto[1];
    run(paramContext, paramString, new Response.Listener()
    {
      public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramAnonymousGetMarketMetadataResponseProto)
      {
        arrayOfGetMarketMetadataResponseProto[0] = paramAnonymousGetMarketMetadataResponseProto;
        localSemaphore.release();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        localSemaphore.release();
      }
    });
    localSemaphore.acquireUninterruptibly();
    return arrayOfGetMarketMetadataResponseProto[0];
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.GetMarketMetadataAction
 * JD-Core Version:    0.6.2
 */
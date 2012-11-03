package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AppFileMetadata;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.HttpCookie;
import com.google.android.finsky.utils.FinskyLog;

public class DownloadTickleReceiver extends DownloadReceiver
{
  private void finishOnReceive(Intent paramIntent)
  {
    FinskyApp localFinskyApp = FinskyApp.get();
    Bundle localBundle = paramIntent.getExtras();
    boolean bool = localBundle.containsKey("server_initiated");
    int i = -1 + getApplicationCount(localBundle);
    if (i >= 0)
    {
      String str1;
      label40: String str2;
      String str3;
      if (i == 0)
      {
        str1 = "";
        str2 = localBundle.getString("asset_name" + str1);
        str3 = localBundle.getString("asset_package" + str1);
        if (bool)
          break label142;
        FinskyLog.d("Ignoring download tickle with !server_initiated: pkg=%s", new Object[] { str3 });
      }
      while (true)
      {
        i--;
        break;
        str1 = "_" + i;
        break label40;
        label142: int j = Integer.parseInt(localBundle.getString("asset_version_code" + str1));
        String str4 = localBundle.getString("user_email" + str1);
        AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = generateDeliveryData(localBundle, str1);
        localFinskyApp.getAnalytics().logTagAndPackage("install.receiveTickle", str3, null);
        localFinskyApp.getEventLogger().logTag("install.receiveTickle", new Object[] { "cidi", str3 });
        localFinskyApp.getInstaller().requestInstall(str3, j, localAndroidAppDeliveryData, str4, null, null, str2, false, "tickle");
      }
    }
  }

  private AndroidAppDelivery.AndroidAppDeliveryData generateDeliveryData(Bundle paramBundle, String paramString)
  {
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = new AndroidAppDelivery.AndroidAppDeliveryData();
    String str1 = getAssetDownloadUrl(paramBundle, Boolean.parseBoolean(paramBundle.getString("asset_secure" + paramString)), "asset_blob_url" + paramString);
    String str2 = paramBundle.getString("download_auth_cookie_name" + paramString);
    String str3 = paramBundle.getString("download_auth_cookie_value" + paramString);
    AndroidAppDelivery.HttpCookie localHttpCookie = new AndroidAppDelivery.HttpCookie().setName(str2).setValue(str3);
    String str4 = paramBundle.getString("asset_signature" + paramString);
    long l1 = Long.parseLong(paramBundle.getString("asset_size" + paramString));
    boolean bool = Boolean.parseBoolean(paramBundle.getString("asset_is_forward_locked" + paramString));
    long l2 = 0L;
    String str5 = paramBundle.getString("asset_refundtimeout" + paramString);
    if (str5 != null);
    try
    {
      long l3 = Long.parseLong(str5);
      l2 = l3;
      localAndroidAppDeliveryData.setDownloadUrl(str1);
      localAndroidAppDeliveryData.setDownloadSize(l1);
      localAndroidAppDeliveryData.setForwardLocked(bool);
      localAndroidAppDeliveryData.setRefundTimeout(l2);
      localAndroidAppDeliveryData.setSignature(str4);
      localAndroidAppDeliveryData.addDownloadAuthCookie(localHttpCookie);
      String str6 = paramBundle.getString("asset_package" + paramString);
      AndroidAppDelivery.AppFileMetadata localAppFileMetadata1 = parseObb(paramBundle, str6, false, paramString);
      if (localAppFileMetadata1 != null)
        localAndroidAppDeliveryData.addAdditionalFile(localAppFileMetadata1);
      AndroidAppDelivery.AppFileMetadata localAppFileMetadata2 = parseObb(paramBundle, str6, true, paramString);
      if (localAppFileMetadata2 != null)
        localAndroidAppDeliveryData.addAdditionalFile(localAppFileMetadata2);
      return localAndroidAppDeliveryData;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        FinskyLog.w("Received refund period time end string : %s", new Object[] { str5 });
    }
  }

  private int getApplicationCount(Bundle paramBundle)
  {
    int i = 0;
    StringBuilder localStringBuilder;
    do
    {
      localStringBuilder = new StringBuilder().append("assetid_");
      i++;
    }
    while (paramBundle.getString(i) != null);
    return i;
  }

  private String getAssetDownloadUrl(Bundle paramBundle, boolean paramBoolean, String paramString)
  {
    String str = paramBundle.getString(paramString);
    if ((paramBoolean) && (!str.startsWith("https:")))
      str = "https" + str.substring(4);
    return str;
  }

  private AndroidAppDelivery.AppFileMetadata parseObb(Bundle paramBundle, String paramString1, boolean paramBoolean, String paramString2)
  {
    int i = 1;
    String str1;
    String str2;
    AndroidAppDelivery.AppFileMetadata localAppFileMetadata;
    if (i <= 2)
    {
      str1 = "_" + i + paramString2;
      str2 = paramBundle.getString("additional_file_type" + str1);
      if (str2 == null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(paramBoolean);
        FinskyLog.d("Not generating OBB (patch %b)", arrayOfObject);
        localAppFileMetadata = null;
      }
    }
    while (true)
    {
      return localAppFileMetadata;
      if (paramBoolean)
      {
        if (!str2.equals("OBB"));
      }
      else
        while (str2.equals("OBB_PATCH"))
        {
          i++;
          break;
        }
      int j = Integer.parseInt(paramBundle.getString("additional_file_version_code" + str1));
      String str3 = paramBundle.getString("additional_file_url" + str1);
      long l = Long.parseLong(paramBundle.getString("additional_file_size" + str1));
      FinskyLog.d("Generating %s OBB", new Object[] { str2 });
      localAppFileMetadata = new AndroidAppDelivery.AppFileMetadata();
      if (paramBoolean);
      for (int k = 1; ; k = 0)
      {
        localAppFileMetadata.setFileType(k);
        localAppFileMetadata.setVersionCode(j);
        localAppFileMetadata.setSize(l);
        localAppFileMetadata.setDownloadUrl(str3);
        break;
      }
      localAppFileMetadata = null;
    }
  }

  public void onReceive(Context paramContext, final Intent paramIntent)
  {
    if (!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE"));
    while (true)
    {
      return;
      setResultCode(-1);
      if (paramIntent.getStringExtra("from").equals("google.com"))
        FinskyApp.get().getAppStates().load(new Runnable()
        {
          public void run()
          {
            DownloadTickleReceiver.this.finishOnReceive(paramIntent);
          }
        });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.DownloadTickleReceiver
 * JD-Core Version:    0.6.2
 */
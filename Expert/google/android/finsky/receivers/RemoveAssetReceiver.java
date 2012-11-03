package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.PackageManagerHelper;
import java.util.Set;

public class RemoveAssetReceiver extends DownloadReceiver
{
  private static Notifier sNotificationHelper;

  private void finishOnReceive(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("asset_package");
    String str2 = paramIntent.getStringExtra("asset_name");
    boolean bool = Boolean.parseBoolean(paramIntent.getStringExtra("asset_malicious"));
    if (TextUtils.isEmpty(str1))
      FinskyLog.w("Unexpected empty package name", new Object[0]);
    while (true)
    {
      return;
      AppStates.AppState localAppState = FinskyApp.get().getAppStates().getApp(str1);
      removePackage(str1, bool);
      if (localAppState != null)
        notifyRemovingKnownApp(str1, str2, bool, localAppState);
    }
  }

  public static void initialize(Notifier paramNotifier)
  {
    sNotificationHelper = paramNotifier;
  }

  private void notifyRemovingKnownApp(String paramString1, String paramString2, boolean paramBoolean, AppStates.AppState paramAppState)
  {
    if (paramAppState.packageManagerState != null)
    {
      if (!paramBoolean)
        break label23;
      sNotificationHelper.showMaliciousAssetRemovedMessage(paramString2, paramString1);
    }
    while (true)
    {
      return;
      label23: sNotificationHelper.showNormalAssetRemovedMessage(paramString2, paramString1);
    }
  }

  private void removePackage(String paramString, boolean paramBoolean)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Boolean.valueOf(paramBoolean);
    FinskyLog.d("Removing package '%s'. Malicious='%s'", arrayOfObject);
    if (paramBoolean)
    {
      PackageManager localPackageManager = FinskyApp.get().getPackageManager();
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 0);
        for (String str : localPackageManager.getPackagesForUid(localPackageInfo.applicationInfo.uid))
        {
          FinskyLog.d("Removing package '%s' (child of '%s')", new Object[] { str, paramString });
          PackageManagerHelper.uninstallPackage(str);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        FinskyLog.d("'%s' not found in PM.", new Object[] { paramString });
      }
    }
    while (true)
    {
      return;
      PackageManagerHelper.uninstallPackage(paramString);
    }
  }

  public void onReceive(Context paramContext, final Intent paramIntent)
  {
    if (!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE"));
    while (true)
    {
      return;
      setResultCode(-1);
      if ((paramIntent.getStringExtra("from").equals("google.com")) && (paramIntent.getCategories().contains("REMOVE_ASSET")))
        FinskyApp.get().getAppStates().load(new Runnable()
        {
          public void run()
          {
            RemoveAssetReceiver.this.finishOnReceive(paramIntent);
          }
        });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.RemoveAssetReceiver
 * JD-Core Version:    0.6.2
 */
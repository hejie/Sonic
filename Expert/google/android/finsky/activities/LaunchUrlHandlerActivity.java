package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.receivers.ExpireLaunchUrlReceiver;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import java.net.URLDecoder;

public class LaunchUrlHandlerActivity extends Activity
{
  private static final Uri BASE_DETAILS_URI = Uri.parse("http://market.android.com/details");

  private static Intent getGoToMarketHomeIntent(Context paramContext)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
  }

  public static Intent handleUrl(Context paramContext, Intent paramIntent, AppStates paramAppStates, Analytics paramAnalytics)
  {
    Uri localUri = paramIntent.getData();
    String str1 = localUri.getQueryParameter("url");
    Intent localIntent2;
    if (TextUtils.isEmpty(str1))
    {
      FinskyLog.e("Launch URL without continue URL", new Object[0]);
      paramIntent.setData(localUri.buildUpon().scheme("http").authority("market.android.com").path("details").build());
      localIntent2 = IntentUtils.createForwardToMainActivityIntent(paramContext, paramIntent);
    }
    while (true)
    {
      return localIntent2;
      String str2 = localUri.getQueryParameter("id");
      if (TextUtils.isEmpty(str2))
      {
        FinskyLog.e("Launch URL without package name", new Object[0]);
        localIntent2 = getGoToMarketHomeIntent(paramContext);
        continue;
      }
      String str3 = localUri.getQueryParameter("min_version");
      int i = -1;
      if (!TextUtils.isEmpty(str3));
      try
      {
        int k = Integer.parseInt(str3);
        i = k;
        label136: int j = -1;
        AppStates.AppState localAppState = paramAppStates.getApp(str2);
        if ((localAppState != null) && (localAppState.packageManagerState != null))
          j = localAppState.packageManagerState.installedVersion;
        if ((j >= 0) && (j >= i));
        String str4;
        for (boolean bool1 = true; ; bool1 = false)
        {
          str4 = URLDecoder.decode(str1);
          boolean bool2 = IntentUtils.canResolveUrl(paramContext.getPackageManager(), str2, str4);
          paramAnalytics.logPageView(null, null, new Uri.Builder().path("launchDeepLink").appendQueryParameter("url", str4).appendQueryParameter("id", str2).appendQueryParameter("min_version", Integer.toString(i)).appendQueryParameter("new_enough", Boolean.toString(bool1)).appendQueryParameter("can_resolve", Boolean.toString(bool2)).build().toString());
          if ((!bool1) || (!bool2))
            break label347;
          localIntent2 = new Intent("android.intent.action.VIEW");
          localIntent2.setData(Uri.parse(str4));
          if ((localAppState == null) || (localAppState.installerData == null))
            break;
          ExpireLaunchUrlReceiver.cancel(paramContext, str2);
          paramAppStates.getInstallerDataStore().setContinueUrl(str2, null);
          break;
        }
        label347: String str5 = localUri.getQueryParameter("referrer");
        Uri.Builder localBuilder = BASE_DETAILS_URI.buildUpon();
        localBuilder.appendQueryParameter("id", str2);
        localBuilder.appendQueryParameter("url", str4);
        if (!TextUtils.isEmpty(str5))
          localBuilder.appendQueryParameter("referrer", str5);
        Intent localIntent1 = new Intent("android.intent.action.VIEW");
        localIntent1.setData(localBuilder.build());
        localIntent2 = IntentUtils.createForwardToMainActivityIntent(paramContext, localIntent1);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label136;
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    final Intent localIntent = getGoToMarketHomeIntent(this);
    if (!((Boolean)G.launchUrlsEnabled.get()).booleanValue())
    {
      startActivity(localIntent);
      finish();
    }
    while (true)
    {
      return;
      final AppStates localAppStates = FinskyApp.get().getAppStates();
      localAppStates.load(new Runnable()
      {
        public void run()
        {
          Intent localIntent1 = localIntent;
          try
          {
            Intent localIntent2 = LaunchUrlHandlerActivity.handleUrl(LaunchUrlHandlerActivity.this, LaunchUrlHandlerActivity.this.getIntent(), localAppStates, FinskyApp.get().getAnalytics());
            return;
          }
          catch (Exception localException)
          {
            while (true)
            {
              FinskyLog.wtf(localException, "Error while processing launch URL", new Object[0]);
              LaunchUrlHandlerActivity.this.startActivity(localIntent1);
              LaunchUrlHandlerActivity.this.finish();
            }
          }
          finally
          {
            LaunchUrlHandlerActivity.this.startActivity(localIntent1);
            LaunchUrlHandlerActivity.this.finish();
          }
        }
      });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.LaunchUrlHandlerActivity
 * JD-Core Version:    0.6.2
 */
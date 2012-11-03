package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParameterizedRunnable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PackageMonitorReceiver
{
  private final List<PackageStatusListener> mListeners = new ArrayList();

  public PackageMonitorReceiver()
  {
    this.mListeners.add(new ReferrerRebroadcaster(null));
  }

  private static String getPackageName(Intent paramIntent)
  {
    Uri localUri = paramIntent.getData();
    if (localUri != null);
    for (String str = localUri.getSchemeSpecificPart(); ; str = null)
      return str;
  }

  private void notifyListeners(ParameterizedRunnable<PackageStatusListener> paramParameterizedRunnable)
  {
    for (int i = -1 + this.mListeners.size(); i >= 0; i--)
      paramParameterizedRunnable.run(this.mListeners.get(i));
  }

  public void attach(PackageStatusListener paramPackageStatusListener)
  {
    this.mListeners.add(paramPackageStatusListener);
  }

  public void detach(PackageStatusListener paramPackageStatusListener)
  {
    this.mListeners.remove(paramPackageStatusListener);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    PackageStateRepository localPackageStateRepository = FinskyApp.get().getPackageInfoRepository();
    final boolean bool1 = "android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(str1);
    boolean bool2 = "android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(str1);
    if ((bool1) || (bool2))
    {
      final String[] arrayOfString = paramIntent.getStringArrayExtra("android.intent.extra.changed_package_list");
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
        localPackageStateRepository.invalidate(arrayOfString[j]);
      notifyListeners(new ParameterizedRunnable()
      {
        public void run(PackageMonitorReceiver.PackageStatusListener paramAnonymousPackageStatusListener)
        {
          paramAnonymousPackageStatusListener.onPackageAvailabilityChanged(arrayOfString, bool1);
        }
      });
    }
    while (true)
    {
      return;
      final String str2 = getPackageName(paramIntent);
      if (str2 != null)
      {
        localPackageStateRepository.invalidate(str2);
        if ("android.intent.action.PACKAGE_REMOVED".equals(str1))
        {
          Bundle localBundle = paramIntent.getExtras();
          if ((localBundle != null) && (localBundle.getBoolean("android.intent.extra.REPLACING", false)));
          for (final boolean bool3 = true; ; bool3 = false)
          {
            notifyListeners(new ParameterizedRunnable()
            {
              public void run(PackageMonitorReceiver.PackageStatusListener paramAnonymousPackageStatusListener)
              {
                paramAnonymousPackageStatusListener.onPackageRemoved(str2, bool3);
              }
            });
            break;
          }
        }
        if ("android.intent.action.PACKAGE_ADDED".equals(str1))
        {
          notifyListeners(new ParameterizedRunnable()
          {
            public void run(PackageMonitorReceiver.PackageStatusListener paramAnonymousPackageStatusListener)
            {
              paramAnonymousPackageStatusListener.onPackageAdded(str2);
            }
          });
          ExpireLaunchUrlReceiver.schedule(paramContext, str2);
        }
        else if ("android.intent.action.PACKAGE_CHANGED".equals(str1))
        {
          notifyListeners(new ParameterizedRunnable()
          {
            public void run(PackageMonitorReceiver.PackageStatusListener paramAnonymousPackageStatusListener)
            {
              paramAnonymousPackageStatusListener.onPackageChanged(str2);
            }
          });
        }
        else if ("android.intent.action.PACKAGE_FIRST_LAUNCH".equals(str1))
        {
          notifyListeners(new ParameterizedRunnable()
          {
            public void run(PackageMonitorReceiver.PackageStatusListener paramAnonymousPackageStatusListener)
            {
              paramAnonymousPackageStatusListener.onPackageFirstLaunch(str2);
            }
          });
        }
        else
        {
          FinskyLog.w("Unhandled intent type action type: %s", new Object[] { str1 });
        }
      }
    }
  }

  public static abstract interface PackageStatusListener
  {
    public abstract void onPackageAdded(String paramString);

    public abstract void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean);

    public abstract void onPackageChanged(String paramString);

    public abstract void onPackageFirstLaunch(String paramString);

    public abstract void onPackageRemoved(String paramString, boolean paramBoolean);
  }

  private static class ReferrerRebroadcaster
    implements PackageMonitorReceiver.PackageStatusListener
  {
    private void broadcastInstallReferrer(String paramString, boolean paramBoolean)
    {
      AppStates localAppStates = FinskyApp.get().getAppStates();
      AppStates.AppState localAppState = localAppStates.getApp(paramString);
      if ((localAppState == null) || (localAppState.installerData == null));
      label56: label67: label242: label248: label252: 
      while (true)
      {
        return;
        int i;
        boolean bool;
        if ((localAppState.installerData.getDeliveryData() != null) && (localAppState.installerData.getDeliveryData().getImmediateStartNeeded()))
        {
          i = 1;
          if (Build.VERSION.SDK_INT < 12)
            break label242;
          bool = true;
          if ((i == 0) && (bool == paramBoolean))
            break label248;
        }
        for (int j = 1; ; j = 0)
        {
          if (j == 0)
            break label252;
          String str1 = localAppState.installerData.getReferrer();
          if (i != 0)
            str1 = "forced-launch";
          if (TextUtils.isEmpty(str1))
            break;
          URLDecoder.decode(str1);
          FinskyApp localFinskyApp = FinskyApp.get();
          Intent localIntent = new Intent("com.android.vending.INSTALL_REFERRER");
          if ((i != 0) && (Build.VERSION.SDK_INT >= 13))
          {
            localIntent.addFlags(32);
            FinskyLog.d("Forcing %s to wake up", new Object[] { paramString });
          }
          localIntent.putExtra("referrer", str1);
          String str2 = getActivityNameForBroadcast(paramString, localIntent, localFinskyApp.getPackageManager());
          if (str2 == null)
            break;
          localIntent.setClassName(paramString, str2);
          if (!TextUtils.isEmpty(str1))
            localFinskyApp.sendBroadcast(localIntent);
          localAppStates.getInstallerDataStore().setReferrer(paramString, null);
          break;
          i = 0;
          break label56;
          bool = false;
          break label67;
        }
      }
    }

    private String getActivityNameForBroadcast(String paramString, Intent paramIntent, PackageManager paramPackageManager)
    {
      String str = null;
      Iterator localIterator = paramPackageManager.queryBroadcastReceivers(paramIntent, 0).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        if ((localResolveInfo.activityInfo != null) && (paramString.equals(localResolveInfo.activityInfo.packageName)))
          str = localResolveInfo.activityInfo.name;
      }
      return str;
    }

    public void onPackageAdded(String paramString)
    {
      broadcastInstallReferrer(paramString, true);
    }

    public void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean)
    {
    }

    public void onPackageChanged(String paramString)
    {
    }

    public void onPackageFirstLaunch(String paramString)
    {
      broadcastInstallReferrer(paramString, false);
    }

    public void onPackageRemoved(String paramString, boolean paramBoolean)
    {
    }
  }

  public static class RegisteredReceiver extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      FinskyApp.get().getPackageMonitorReceiver().onReceive(paramContext, paramIntent);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.PackageMonitorReceiver
 * JD-Core Version:    0.6.2
 */
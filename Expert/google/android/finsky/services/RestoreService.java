package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.download.Storage;
import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LegacyDeviceConfigurationHelper;
import com.google.android.finsky.utils.Sets;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.GetAssetResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetAssetResponseProto.InstallAsset;
import com.google.android.vending.remoting.protos.VendingProtos.RestoreApplicationsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.RestoreApplicationsResponseProto;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RestoreService extends Service
{
  private int mDebugCountAlreadyInstalled;
  private int mDebugCountAlreadyTracked;
  private int mDebugCountInstalledOtherAccount;
  private int mServiceStartId;
  private RestoreTracker mTracker = new RestoreTracker(null);

  public RestoreService()
  {
    FinskyApp.get().getInstaller().addListener(this.mTracker);
  }

  private void doRestore(String paramString1, final String paramString2)
  {
    VendingProtos.RestoreApplicationsRequestProto localRestoreApplicationsRequestProto = new VendingProtos.RestoreApplicationsRequestProto().setBackupAndroidId(paramString1).setTosVersion("1.0").setDeviceConfiguration(LegacyDeviceConfigurationHelper.getDeviceConfiguration());
    FinskyApp.get().getVendingApi(paramString2).restoreApplications(localRestoreApplicationsRequestProto, new RestoreResponseListener(paramString2), new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        RestoreService.this.mTracker.finishAccount(paramString2);
        FinskyLog.e("Error while getting list of applications to restore from server: %s", new Object[] { paramAnonymousVolleyError });
      }
    });
  }

  private boolean handleIntent(Intent paramIntent)
  {
    boolean bool = false;
    if (paramIntent.getBooleanExtra("kick_installer", false))
      FinskyApp.get().getInstaller().startDeferredInstalls();
    String str1;
    Account localAccount;
    while (true)
    {
      return bool;
      str1 = paramIntent.getStringExtra("aid");
      if (TextUtils.isEmpty(str1))
        FinskyLog.e("Expecting a non-empty aid extra", new Object[0]);
      else
        try
        {
          Long.parseLong(str1, 16);
          String str2 = paramIntent.getStringExtra("authAccount");
          if (str2 == null)
            break label137;
          localAccount = AccountHandler.findAccount(str2, FinskyApp.get());
          if (localAccount != null)
            break;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(str2);
          FinskyLog.e("Can't find restore acct:%s", arrayOfObject);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          FinskyLog.e("Provided aid can't be parsed as long", new Object[0]);
        }
    }
    restore(str1, localAccount);
    while (true)
    {
      bool = true;
      break;
      label137: Account[] arrayOfAccount = AccountHandler.getAccounts(this);
      if (arrayOfAccount.length <= 0)
      {
        FinskyLog.e("RestoreService can't run - no accounts configured on device!", new Object[0]);
        break;
      }
      int i = arrayOfAccount.length;
      for (int j = 0; j < i; j++)
        restore(str1, arrayOfAccount[j]);
    }
  }

  private void restore(final String paramString, final Account paramAccount)
  {
    if (this.mTracker.isAccountInFlight(paramAccount.name))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = FinskyLog.scrubPii(paramAccount.name);
      FinskyLog.d("Skip restore acct:%s already started", arrayOfObject2);
    }
    while (true)
    {
      return;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = FinskyLog.scrubPii(paramString);
      arrayOfObject1[1] = FinskyLog.scrubPii(paramAccount.name);
      FinskyLog.d("Start restore aid:%s acct:%s", arrayOfObject1);
      this.mTracker.startAccount(paramAccount.name);
      Runnable local1 = new Runnable()
      {
        private int mLoaded;

        public void run()
        {
          this.mLoaded = (1 + this.mLoaded);
          if (this.mLoaded == 3)
            RestoreService.this.doRestore(paramString, paramAccount.name);
        }
      };
      FinskyApp.get().getLibraries().load(local1);
      LibraryReplicators localLibraryReplicators = FinskyApp.get().getLibraryReplicators();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = AccountLibrary.LIBRARY_ID_APPS;
      localLibraryReplicators.replicateAccount(paramAccount, arrayOfString, local1, "restore-service");
      FinskyApp.get().getAppStates().load(local1);
    }
  }

  private boolean shouldRestore(String paramString1, int paramInt, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, String paramString2)
  {
    boolean bool;
    if (this.mTracker.isTracked(paramString1))
    {
      this.mDebugCountAlreadyTracked = (1 + this.mDebugCountAlreadyTracked);
      FinskyApp.get().getAnalytics().logTagAndPackage("install.restoreSkip", paramString1, "is-tracked");
      FinskyApp.get().getEventLogger().logTag("install.restoreSkip", new Object[] { "cidi", paramString1, "packageTag", "is-tracked" });
      FinskyLog.d("Skipping restore of %s because already restoring", new Object[] { paramString1 });
      bool = false;
    }
    while (true)
    {
      return bool;
      if (paramAndroidAppDeliveryData.getAdditionalFileCount() > 0)
      {
        Obb localObb1 = AssetUtils.extractObb(paramAndroidAppDeliveryData, paramString1, false);
        Obb localObb2 = AssetUtils.extractObb(paramAndroidAppDeliveryData, paramString1, true);
        if ((localObb1 != null) && (localObb2 != null) && (!Storage.externalStorageAvailable()))
        {
          FinskyApp.get().getAnalytics().logTagAndPackage("install.restoreSkip", paramString1, "obb-storage");
          FinskyApp.get().getEventLogger().logTag("install.restoreSkip", new Object[] { "cidi", paramString1, "packageTag", "obb-storage" });
          FinskyLog.d("Skipping restore of %s because OBB required but no external storage", new Object[] { paramString1 });
          bool = false;
        }
      }
      else
      {
        int i = -1;
        PackageStateRepository.PackageState localPackageState = FinskyApp.get().getPackageInfoRepository().get(paramString1);
        if (localPackageState != null)
          i = localPackageState.installedVersion;
        if (i >= paramInt)
        {
          FinskyApp.get().getAnalytics().logTagAndPackage("install.restoreSkip", paramString1, "older-version");
          FinskyApp.get().getEventLogger().logTag("install.restoreSkip", new Object[] { "cidi", paramString1, "packageTag", "older-version" });
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = paramString1;
          arrayOfObject[1] = Integer.valueOf(i);
          arrayOfObject[2] = Integer.valueOf(paramInt);
          FinskyLog.d("Skipping restore of %s v:%d because v:%d is installed", arrayOfObject);
          this.mDebugCountAlreadyInstalled = (1 + this.mDebugCountAlreadyInstalled);
          bool = false;
        }
        else
        {
          AppStates.AppState localAppState = FinskyApp.get().getAppStates().getApp(paramString1);
          if ((localAppState != null) && (localAppState.installerData != null) && (!paramString2.equals(localAppState.installerData.getAccountName())))
          {
            this.mDebugCountInstalledOtherAccount = (1 + this.mDebugCountInstalledOtherAccount);
            FinskyApp.get().getAnalytics().logTagAndPackage("install.restoreSkip", paramString1, "other-account");
            FinskyApp.get().getEventLogger().logTag("install.restoreSkip", new Object[] { "cidi", paramString1, "packageTag", "other-account" });
            FinskyLog.d("Skipping restore of %s because tracked by another account", new Object[] { paramString1 });
            bool = false;
          }
          else
          {
            FinskyLog.d("Should attempt restore of %s", new Object[] { paramString1 });
            bool = true;
          }
        }
      }
    }
  }

  public static void start(Context paramContext, String paramString1, String paramString2)
  {
    Context localContext = paramContext.getApplicationContext();
    Intent localIntent = new Intent(localContext, RestoreService.class);
    localIntent.putExtra("aid", paramString1);
    localIntent.putExtra("authAccount", paramString2);
    localContext.startService(localIntent);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    this.mServiceStartId = paramInt2;
    if (handleIntent(paramIntent));
    for (int i = 3; ; i = 2)
    {
      return i;
      stopSelf(paramInt2);
    }
  }

  class RestoreResponseListener
    implements Response.Listener<VendingProtos.RestoreApplicationsResponseProto>
  {
    private final String mAccountName;

    public RestoreResponseListener(String arg2)
    {
      Object localObject;
      this.mAccountName = localObject;
    }

    public void onResponse(VendingProtos.RestoreApplicationsResponseProto paramRestoreApplicationsResponseProto)
    {
      RestoreService.access$202(RestoreService.this, 0);
      RestoreService.access$302(RestoreService.this, 0);
      RestoreService.access$402(RestoreService.this, 0);
      List localList = paramRestoreApplicationsResponseProto.getAssetList();
      Installer localInstaller = FinskyApp.get().getInstaller();
      Iterator localIterator = localList.iterator();
      int i = 0;
      while (localIterator.hasNext())
      {
        VendingProtos.GetAssetResponseProto localGetAssetResponseProto = (VendingProtos.GetAssetResponseProto)localIterator.next();
        VendingProtos.GetAssetResponseProto.InstallAsset localInstallAsset = localGetAssetResponseProto.getInstallAsset();
        if (localInstallAsset == null)
        {
          FinskyLog.e("Unexpected null InstallAsset for restore asset.", new Object[0]);
        }
        else
        {
          String str = localInstallAsset.getAssetPackage();
          int j = localInstallAsset.getVersionCode();
          AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = AssetUtils.assetResponseToDeliveryData(localGetAssetResponseProto);
          if (RestoreService.this.shouldRestore(str, j, localAndroidAppDeliveryData, this.mAccountName))
          {
            RestoreService.this.mTracker.start(str);
            localInstaller.setVisibility(str, true, false);
            localInstaller.requestInstall(str, j, null, this.mAccountName, null, null, localInstallAsset.getAssetName(), true, "restore");
            i++;
          }
        }
      }
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(localList.size());
      FinskyLog.d("Attempted to restore %d assets.", arrayOfObject1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(RestoreService.this.mDebugCountAlreadyTracked);
      FinskyLog.d("  Skipped (already tracked): %d", arrayOfObject2);
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(RestoreService.this.mDebugCountInstalledOtherAccount);
      FinskyLog.d("  Skipped (other account): %d", arrayOfObject3);
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(RestoreService.this.mDebugCountAlreadyInstalled);
      FinskyLog.d("  Skipped (already installed): %d", arrayOfObject4);
      if (i > 0)
      {
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = Integer.valueOf(i);
        FinskyLog.d("  Posted for deferred download/install: %d", arrayOfObject5);
        Context localContext = RestoreService.this.getApplicationContext();
        AlarmManager localAlarmManager = (AlarmManager)localContext.getSystemService("alarm");
        long l = ((Long)G.appRestoreHoldoffMs.get()).longValue() + System.currentTimeMillis();
        Intent localIntent = new Intent(localContext, RestoreService.class);
        localIntent.putExtra("kick_installer", true);
        localAlarmManager.set(0, l, PendingIntent.getService(localContext, 0, localIntent, 0));
      }
      RestoreService.this.mTracker.finishAccount(this.mAccountName);
    }
  }

  private class RestoreTracker
    implements InstallerListener
  {
    private final Set<String> mAccounts = Sets.newHashSet();
    private final Set<String> mAllDownloads = Sets.newHashSet();
    private final Set<String> mDownloads = Sets.newHashSet();
    private int mFailed = 0;
    private int mSucceeded = 0;

    private RestoreTracker()
    {
    }

    public void finish(String paramString, boolean paramBoolean)
    {
      if (this.mDownloads.contains(paramString))
      {
        this.mDownloads.remove(paramString);
        if (!paramBoolean)
          break label43;
        this.mSucceeded = (1 + this.mSucceeded);
      }
      while (true)
      {
        stopServiceIfDone();
        return;
        label43: this.mFailed = (1 + this.mFailed);
      }
    }

    public void finishAccount(String paramString)
    {
      this.mAccounts.remove(paramString);
      stopServiceIfDone();
    }

    public boolean isAccountInFlight(String paramString)
    {
      return this.mAccounts.contains(paramString);
    }

    public boolean isTracked(String paramString)
    {
      return this.mAllDownloads.contains(paramString);
    }

    public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
    {
      switch (RestoreService.3.$SwitchMap$com$google$android$finsky$installer$InstallerListener$InstallerPackageEvent[paramInstallerPackageEvent.ordinal()])
      {
      case 1:
      case 2:
      case 3:
      case 4:
      default:
      case 5:
      case 6:
      case 7:
      case 8:
      }
      while (true)
      {
        return;
        FinskyLog.e("Restore package %s download cancelled", new Object[] { paramString });
        finish(paramString, false);
        continue;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramString;
        arrayOfObject2[1] = Integer.valueOf(paramInt);
        FinskyLog.e("Restore package %s download error %d", arrayOfObject2);
        finish(paramString, false);
        continue;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = paramString;
        arrayOfObject1[1] = Integer.valueOf(paramInt);
        FinskyLog.e("Restore package %s install error %d", arrayOfObject1);
        finish(paramString, false);
        continue;
        FinskyLog.d("Restore package %s install complete", new Object[] { paramString });
        finish(paramString, true);
      }
    }

    public void start(String paramString)
    {
      this.mDownloads.add(paramString);
      this.mAllDownloads.add(paramString);
    }

    public void startAccount(String paramString)
    {
      this.mAccounts.add(paramString);
    }

    public void stopServiceIfDone()
    {
      if ((this.mDownloads.isEmpty()) && (this.mAccounts.isEmpty()))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(this.mSucceeded);
        arrayOfObject[1] = Integer.valueOf(this.mFailed);
        FinskyLog.d("Restore complete with %d success and %d failed.", arrayOfObject);
        RestoreService.this.stopSelf(RestoreService.this.mServiceStartId);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.RestoreService
 * JD-Core Version:    0.6.2
 */
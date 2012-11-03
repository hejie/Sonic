package com.google.android.finsky.receivers;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueue.RunningDownload;
import com.google.android.finsky.download.DownloadQueueListener;
import com.google.android.finsky.download.InternalDownload;
import com.google.android.finsky.installer.IMultiUserCoordinatorService;
import com.google.android.finsky.installer.IMultiUserCoordinatorService.Stub;
import com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener;
import com.google.android.finsky.installer.IMultiUserCoordinatorServiceListener.Stub;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.installer.MultiUserCoordinatorService;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.NotificationManager;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.Objects;
import com.google.android.finsky.utils.PackageManagerHelper;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InstallerImpl
  implements DownloadQueueListener, Installer, PackageMonitorReceiver.PackageStatusListener
{
  private static Installer.InstallerProgressReport PROGRESS_DOWNLOAD_PENDING = new Installer.InstallerProgressReport(Installer.InstallerState.DOWNLOAD_PENDING, 0L, 0L, 0);
  private static Installer.InstallerProgressReport PROGRESS_NOT_TRACKED = new Installer.InstallerProgressReport(Installer.InstallerState.NOT_TRACKED, 0L, 0L, 0);
  private static Installer.InstallerProgressReport PROGRESS_UNINSTALLING = new Installer.InstallerProgressReport(Installer.InstallerState.UNINSTALLING, 0L, 0L, 0);
  private final AppStates mAppStates;
  private final Context mContext;
  private IMultiUserCoordinatorService mCoordinatorService;
  private final DownloadQueue mDownloadQueue;
  private final Handler mHandler;
  private final InstallPolicies mInstallPolicies;
  private final InstallerDataStore mInstallerDataStore;
  private InstallerTask mInstallerTask;
  private final Libraries mLibraries;
  IMultiUserCoordinatorServiceListener mListener = new IMultiUserCoordinatorServiceListener.Stub()
  {
    public void packageAcquired(String paramAnonymousString)
    {
    }

    public void packageReleased(final String paramAnonymousString)
    {
      InstallerImpl.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          InstallerImpl.this.mAppStates.getPackageStateRepository().invalidate(paramAnonymousString);
          InstallerImpl.this.kick(paramAnonymousString, false);
        }
      });
    }
  };
  private final List<InstallerListener> mListeners;
  private final Notifier mNotifier;
  private final PackageMonitorReceiver mPackageMonitorReceiver;
  private boolean mRunning;
  private RemoteServiceConnection mServiceConnection = null;
  private ArrayList<Runnable> mServiceConnectionCallbacks = Lists.newArrayList();
  private Set<String> mUninstallingPackages;
  private final Users mUsers;

  public InstallerImpl(Context paramContext, AppStates paramAppStates, Libraries paramLibraries, DownloadQueue paramDownloadQueue, Notifier paramNotifier, InstallPolicies paramInstallPolicies, PackageMonitorReceiver paramPackageMonitorReceiver, Users paramUsers)
  {
    this.mContext = paramContext;
    this.mAppStates = paramAppStates;
    this.mLibraries = paramLibraries;
    this.mDownloadQueue = paramDownloadQueue;
    this.mNotifier = paramNotifier;
    this.mInstallPolicies = paramInstallPolicies;
    this.mPackageMonitorReceiver = paramPackageMonitorReceiver;
    this.mUsers = paramUsers;
    this.mListeners = Lists.newArrayList();
    this.mInstallerDataStore = paramAppStates.getInstallerDataStore();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.mRunning = false;
    this.mUninstallingPackages = Sets.newHashSet();
  }

  private void bindToMultiUserCoordinator(Runnable paramRunnable)
  {
    Utils.ensureOnMainThread();
    if (this.mCoordinatorService != null)
      paramRunnable.run();
    while (true)
    {
      return;
      this.mServiceConnectionCallbacks.add(paramRunnable);
      if (this.mServiceConnection == null)
      {
        this.mServiceConnection = new RemoteServiceConnection();
        Intent localIntent = MultiUserCoordinatorService.createBindIntent(this.mContext);
        if (!this.mContext.bindService(localIntent, this.mServiceConnection, 5))
          FinskyLog.w("Couldn't start service for %s", new Object[] { localIntent });
      }
    }
  }

  private void cancelPendingInstall(AppStates.AppState paramAppState)
  {
    if (paramAppState != null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramAppState.packageName;
      FinskyLog.d("Cancel pending install of %s", arrayOfObject);
      if (paramAppState.installerData != null)
      {
        clearInstallerState(paramAppState);
        notifyListeners(paramAppState.packageName, InstallerListener.InstallerPackageEvent.DOWNLOAD_CANCELLED, 0);
      }
    }
  }

  private InstallerTask getInstallerTask(Download paramDownload)
  {
    String str = ((InternalDownload)paramDownload).getPackageName();
    InstallerTask localInstallerTask;
    if (str == null)
      localInstallerTask = null;
    while (true)
    {
      return localInstallerTask;
      localInstallerTask = getInstallerTask(str);
      if (localInstallerTask == null)
      {
        this.mDownloadQueue.cancel(paramDownload);
        localInstallerTask = null;
      }
      else
      {
        AppStates.AppState localAppState = this.mAppStates.getApp(str);
        if ((localAppState == null) || (localAppState.installerData == null))
        {
          this.mDownloadQueue.cancel(paramDownload);
          localInstallerTask = null;
        }
      }
    }
  }

  private InstallerTask getInstallerTask(String paramString)
  {
    if ((this.mInstallerTask != null) && (this.mInstallerTask.packageName.equals(paramString)));
    for (InstallerTask localInstallerTask = this.mInstallerTask; ; localInstallerTask = null)
      return localInstallerTask;
  }

  private void kick(final String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          InstallerImpl.this.kick(paramString, false);
        }
      });
    while (true)
    {
      return;
      if (!this.mRunning)
      {
        FinskyLog.d("Installer kick %s - no action, not running yet", new Object[] { paramString });
      }
      else if (this.mInstallerTask != null)
      {
        FinskyLog.d("Installer kick %s - no action because busy.", new Object[] { paramString });
      }
      else if (multiUserMode())
      {
        kickMultiUser(paramString);
      }
      else
      {
        AppStates.AppState localAppState = selectNextTask(paramString);
        if (localAppState != null)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramString;
          arrayOfObject[1] = localAppState.packageName;
          FinskyLog.d("Installer kick %s - starting %s", arrayOfObject);
          this.mInstallerTask = new InstallerTask(localAppState.packageName, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies);
          this.mInstallerTask.start();
        }
      }
    }
  }

  private void kickMultiUser(final String paramString)
  {
    List localList = this.mAppStates.getAppsToInstall();
    if (localList.isEmpty())
      unbindMultiUserCoordinator();
    while (true)
    {
      return;
      if (this.mCoordinatorService == null)
      {
        bindToMultiUserCoordinator(new Runnable()
        {
          public void run()
          {
            InstallerImpl.this.kick(paramString, false);
          }
        });
      }
      else
      {
        AppStates.AppState localAppState = selectNextTaskMultiUser(paramString, localList);
        if (localAppState != null)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramString;
          arrayOfObject[1] = localAppState.packageName;
          FinskyLog.d("Installer kick %s - starting %s", arrayOfObject);
          this.mInstallerTask = new InstallerTask(localAppState.packageName, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies);
          this.mInstallerTask.start();
        }
      }
    }
  }

  private boolean multiUserMode()
  {
    return this.mUsers.hasMultipleUsers();
  }

  private boolean recoverDownload(Uri paramUri, int paramInt)
  {
    String str1;
    boolean bool;
    if (paramUri != null)
    {
      str1 = paramUri.toString();
      if (!TextUtils.isEmpty(str1))
        break label27;
      bool = false;
    }
    while (true)
    {
      return bool;
      str1 = null;
      break;
      label27: if (this.mInstallerTask != null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.mInstallerTask.packageName;
        FinskyLog.w("tried recovery while already handling %s", arrayOfObject);
        bool = false;
        continue;
      }
      Collection localCollection = this.mAppStates.getInstallerDataStore().getAll();
      Object localObject = null;
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        InstallerDataStore.InstallerData localInstallerData = (InstallerDataStore.InstallerData)localIterator.next();
        if (str1.equals(localInstallerData.getDownloadUri()))
          localObject = localInstallerData;
      }
      if (localObject == null)
      {
        bool = false;
        continue;
      }
      String str2 = localObject.getPackageName();
      FinskyLog.d("Recovering download for running %s", new Object[] { str2 });
      if (multiUserMode())
        try
        {
          if (!this.mCoordinatorService.acquirePackage(str2))
          {
            FinskyLog.w("Can't recover %s *** cannot acquire", new Object[] { str2 });
            bool = false;
          }
        }
        catch (RemoteException localRemoteException2)
        {
          FinskyLog.w("Acquiring %s *** received %s", new Object[] { str2, localRemoteException2 });
        }
      InstallerTask localInstallerTask = new InstallerTask(str2, this, this.mAppStates, this.mDownloadQueue, this.mNotifier, this.mInstallPolicies);
      if (localInstallerTask.recover(paramUri, paramInt))
      {
        this.mInstallerTask = localInstallerTask;
        bool = true;
        continue;
      }
      if (multiUserMode());
      try
      {
        this.mCoordinatorService.releasePackage(str2);
        bool = false;
      }
      catch (RemoteException localRemoteException1)
      {
        while (true)
          FinskyLog.w("Releasing %s *** received %s", new Object[] { str2, localRemoteException1 });
      }
    }
  }

  private void recoverRunningDownloads(Collection<DownloadQueue.RunningDownload> paramCollection)
  {
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      DownloadQueue.RunningDownload localRunningDownload = (DownloadQueue.RunningDownload)localIterator.next();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = localRunningDownload.contentUri;
      arrayOfObject1[1] = Integer.valueOf(localRunningDownload.status);
      FinskyLog.d("Attempt recovery of %s %d", arrayOfObject1);
      Uri localUri = Uri.parse(localRunningDownload.contentUri);
      if ((i != 0) || (!recoverDownload(localUri, localRunningDownload.status)))
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localRunningDownload.contentUri;
        arrayOfObject2[1] = Integer.valueOf(localRunningDownload.status);
        FinskyLog.d("Releasing %s %d", arrayOfObject2);
        this.mDownloadQueue.release(localUri);
      }
      else
      {
        i = 1;
      }
    }
  }

  private void releaseMultiUserPackage(final String paramString)
  {
    bindToMultiUserCoordinator(new Runnable()
    {
      public void run()
      {
        try
        {
          InstallerImpl.this.mCoordinatorService.releasePackage(paramString);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          while (true)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = paramString;
            arrayOfObject[1] = localRemoteException;
            FinskyLog.w("Couldn't release %s *** received %s", arrayOfObject);
          }
        }
      }
    });
  }

  private AppStates.AppState selectNextTask(String paramString)
  {
    AppStates.AppState localAppState = null;
    if (!TextUtils.isEmpty(paramString))
      localAppState = this.mAppStates.getApp(paramString);
    if (localAppState == null)
    {
      List localList = this.mAppStates.getAppsToInstall();
      if (!localList.isEmpty())
        localAppState = (AppStates.AppState)localList.get(0);
    }
    return localAppState;
  }

  private AppStates.AppState selectNextTaskMultiUser(String paramString, List<AppStates.AppState> paramList)
  {
    Object localObject;
    if (!TextUtils.isEmpty(paramString))
    {
      localObject = this.mAppStates.getApp(paramString);
      if ((localObject == null) || (((AppStates.AppState)localObject).installerData == null) || (((AppStates.AppState)localObject).installerData.getDesiredVersion() == -1));
    }
    while (true)
    {
      try
      {
        boolean bool = this.mCoordinatorService.acquirePackage(paramString);
        if (bool)
          return localObject;
      }
      catch (RemoteException localRemoteException2)
      {
        FinskyLog.w("Couldn't acquire %s (proceed anyway) received %s", new Object[] { paramString, localRemoteException2 });
        continue;
      }
      int i = 0;
      if (i < paramList.size())
      {
        AppStates.AppState localAppState = (AppStates.AppState)paramList.get(i);
        try
        {
          if (this.mCoordinatorService.acquirePackage(localAppState.packageName))
          {
            localObject = localAppState;
            continue;
          }
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localAppState.packageName;
          FinskyLog.d("Skipping install of %s - not acquired", arrayOfObject);
          i++;
        }
        catch (RemoteException localRemoteException1)
        {
          FinskyLog.w("Couldn't acquire %s (proceed anyway) received %s", new Object[] { paramString, localRemoteException1 });
          localObject = localAppState;
        }
      }
      else
      {
        localObject = null;
      }
    }
  }

  private void unbindMultiUserCoordinator()
  {
    Utils.ensureOnMainThread();
    if (this.mServiceConnection == null);
    while (true)
    {
      return;
      try
      {
        if (this.mCoordinatorService != null)
        {
          this.mCoordinatorService.registerListener(null);
          this.mCoordinatorService.releaseAllPackages();
        }
        this.mCoordinatorService = null;
        this.mContext.unbindService(this.mServiceConnection);
        this.mServiceConnection = null;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          FinskyLog.w("Couldn't sign out from coordinator *** received %s", new Object[] { localRemoteException });
      }
    }
  }

  public void addListener(InstallerListener paramInstallerListener)
  {
    Utils.ensureOnMainThread();
    this.mListeners.add(paramInstallerListener);
  }

  public void cancel(String paramString)
  {
    InstallerTask localInstallerTask = getInstallerTask(paramString);
    if (localInstallerTask != null)
      localInstallerTask.cancel(true);
    while (true)
    {
      kick(null, true);
      return;
      cancelPendingInstall(this.mAppStates.getApp(paramString));
    }
  }

  public void cancelAll()
  {
    if (this.mInstallerTask != null)
      this.mInstallerTask.cancel(true);
    Iterator localIterator = this.mAppStates.getAppsToInstall().iterator();
    while (localIterator.hasNext())
      cancelPendingInstall((AppStates.AppState)localIterator.next());
    kick(null, true);
  }

  void clearInstallerState(AppStates.AppState paramAppState)
  {
    if ((paramAppState != null) && (paramAppState.installerData == null));
    this.mInstallerDataStore.setDesiredVersion(paramAppState.packageName, -1);
    this.mInstallerDataStore.setInstallerState(paramAppState.packageName, 0, null);
    if (paramAppState.installerData.getFlags() != 0)
      this.mInstallerDataStore.setFlags(paramAppState.packageName, 0);
  }

  public Installer.InstallerProgressReport getProgress(String paramString)
  {
    InstallerTask localInstallerTask = getInstallerTask(paramString);
    Installer.InstallerProgressReport localInstallerProgressReport;
    if (localInstallerTask != null)
      localInstallerProgressReport = localInstallerTask.getProgress();
    while (true)
    {
      return localInstallerProgressReport;
      if (this.mUninstallingPackages.contains(paramString))
      {
        localInstallerProgressReport = PROGRESS_UNINSTALLING;
      }
      else
      {
        AppStates.AppState localAppState = this.mAppStates.getApp(paramString);
        if (localAppState != null)
        {
          int i = -1;
          if (localAppState.packageManagerState != null)
            i = localAppState.packageManagerState.installedVersion;
          if ((localAppState.installerData != null) && (localAppState.installerData.getDesiredVersion() > i))
            localInstallerProgressReport = PROGRESS_DOWNLOAD_PENDING;
        }
        else
        {
          localInstallerProgressReport = PROGRESS_NOT_TRACKED;
        }
      }
    }
  }

  public Installer.InstallerState getState(String paramString)
  {
    InstallerTask localInstallerTask = getInstallerTask(paramString);
    Installer.InstallerState localInstallerState;
    if (localInstallerTask != null)
      localInstallerState = localInstallerTask.getState();
    while (true)
    {
      return localInstallerState;
      if (this.mUninstallingPackages.contains(paramString))
      {
        localInstallerState = Installer.InstallerState.UNINSTALLING;
      }
      else
      {
        AppStates.AppState localAppState = this.mAppStates.getApp(paramString);
        if (localAppState != null)
        {
          int i = -1;
          if (localAppState.packageManagerState != null)
            i = localAppState.packageManagerState.installedVersion;
          if ((localAppState.installerData != null) && (localAppState.installerData.getDesiredVersion() > i))
            localInstallerState = Installer.InstallerState.DOWNLOAD_PENDING;
        }
        else
        {
          localInstallerState = Installer.InstallerState.NOT_TRACKED;
        }
      }
    }
  }

  public boolean isBusy()
  {
    return false;
  }

  void notifyListeners(final String paramString, final InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, final int paramInt)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = Lists.newArrayList(InstallerImpl.this.mListeners).iterator();
        while (localIterator.hasNext())
        {
          InstallerListener localInstallerListener = (InstallerListener)localIterator.next();
          try
          {
            localInstallerListener.onInstallPackageEvent(paramString, paramInstallerPackageEvent, paramInt);
          }
          catch (Exception localException)
          {
            FinskyLog.wtf(localException, "Exception caught in InstallerListener", new Object[0]);
          }
        }
      }
    });
  }

  public void onCancel(Download paramDownload)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadCancel", paramDownload.toString(), null);
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "downloadState";
    arrayOfObject[1] = paramDownload.toString();
    localFinskyEventLog.logTag("install.downloadCancel", arrayOfObject);
    InstallerTask localInstallerTask = getInstallerTask(paramDownload);
    if (localInstallerTask != null)
      localInstallerTask.cancel(true);
  }

  public void onComplete(Download paramDownload)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadComplete", paramDownload.toString(), null);
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "downloadState";
    arrayOfObject[1] = paramDownload.toString();
    localFinskyEventLog.logTag("install.downloadComplete", arrayOfObject);
    InstallerTask localInstallerTask = getInstallerTask(paramDownload);
    if (localInstallerTask != null)
      localInstallerTask.onComplete(paramDownload);
  }

  public void onError(Download paramDownload, int paramInt)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadError", paramDownload.toString(), String.valueOf(paramInt));
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = "downloadState";
    arrayOfObject[1] = paramDownload.toString();
    arrayOfObject[2] = "httpStatus";
    arrayOfObject[3] = String.valueOf(paramInt);
    localFinskyEventLog.logTag("install.downloadError", arrayOfObject);
    InstallerTask localInstallerTask = getInstallerTask(paramDownload);
    if (localInstallerTask != null)
      localInstallerTask.onError(paramDownload, paramInt);
  }

  public void onNotificationClicked(Download paramDownload)
  {
    String str = ((InternalDownload)paramDownload).getPackageName();
    if (str == null)
      FinskyLog.d("Discarding notification click, no packageName for " + paramDownload.toString(), new Object[0]);
    while (true)
    {
      return;
      Intent localIntent = NotificationManager.createDefaultClickIntent(this.mContext, str, null, null, DfeUtils.createDetailsUrlFromId(str));
      localIntent.setFlags(268435456);
      this.mContext.startActivity(localIntent);
    }
  }

  public void onPackageAdded(String paramString)
  {
  }

  public void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean)
  {
  }

  public void onPackageChanged(String paramString)
  {
  }

  public void onPackageFirstLaunch(String paramString)
  {
  }

  public void onPackageRemoved(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      this.mNotifier.hideAllMessagesForPackage(paramString);
    this.mUninstallingPackages.remove(paramString);
    AppStates.AppState localAppState = this.mAppStates.getApp(paramString);
    int i;
    InstallerDataStore.InstallerData localInstallerData;
    if ((localAppState != null) && (localAppState.installerData != null))
    {
      i = 0;
      localInstallerData = localAppState.installerData;
      if (localInstallerData.getDesiredVersion() != -1)
        break label93;
    }
    while (true)
    {
      if (i != 0)
        this.mInstallerDataStore.setDesiredVersion(paramString, -1);
      notifyListeners(paramString, InstallerListener.InstallerPackageEvent.UNINSTALLED, 0);
      return;
      label93: if ((paramBoolean) && (localAppState.packageManagerState != null))
      {
        int j = localInstallerData.getDesiredVersion();
        if (localAppState.packageManagerState.installedVersion < j)
          i = 1;
      }
      else
      {
        i = 1;
      }
    }
  }

  public void onProgress(Download paramDownload, DownloadProgress paramDownloadProgress)
  {
    InstallerTask localInstallerTask = getInstallerTask(paramDownload);
    if (localInstallerTask != null)
      localInstallerTask.onProgress(paramDownload, paramDownloadProgress);
  }

  public void onStart(Download paramDownload)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadStarted", paramDownload.toString(), null);
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "downloadState";
    arrayOfObject[1] = paramDownload.toString();
    localFinskyEventLog.logTag("install.downloadStarted", arrayOfObject);
    InstallerTask localInstallerTask = getInstallerTask(paramDownload);
    if (localInstallerTask != null)
      localInstallerTask.onStart(paramDownload);
  }

  public void removeListener(InstallerListener paramInstallerListener)
  {
    Utils.ensureOnMainThread();
    this.mListeners.remove(paramInstallerListener);
  }

  public void requestInstall(String paramString1, int paramInt, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, String paramString6)
  {
    if (getState(paramString1) != Installer.InstallerState.NOT_TRACKED)
      FinskyLog.d("Dropping install request for %s because already installing", new Object[] { paramString1 });
    AppStates.AppState localAppState;
    label48: label60: label215: label221: label225: 
    while (true)
    {
      return;
      localAppState = this.mAppStates.getApp(paramString1);
      PackageStateRepository.PackageState localPackageState;
      int i;
      if (localAppState != null)
      {
        localPackageState = localAppState.packageManagerState;
        if (localPackageState == null)
          break label215;
        i = localPackageState.installedVersion;
        if (paramInt > i)
          break;
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = paramString1;
        arrayOfObject2[1] = Integer.valueOf(paramInt);
        arrayOfObject2[2] = Integer.valueOf(i);
        FinskyLog.e("Skipping attempt to download %s version %d over version %d", arrayOfObject2);
        FinskyApp.get().getAnalytics().logTagAndPackage("install.abandonInstall", paramString1, "older-version");
        FinskyApp.get().getEventLogger().logTag("install.abandonInstall", new Object[] { "cidi", paramString1, "r", "older-version" });
        if (localAppState.installerData == null)
          break label221;
      }
      for (int i1 = localAppState.installerData.getFlags(); ; i1 = 0)
      {
        if ((i1 & 0x1) != 0)
          break label225;
        this.mNotifier.showInstallationFailureMessage(paramString5, paramString1, this.mContext.getString(2131165363), -1);
        break;
        localPackageState = null;
        break label48;
        i = -1;
        break label60;
      }
    }
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = paramString1;
    arrayOfObject1[1] = Integer.valueOf(paramInt);
    arrayOfObject1[2] = paramString6;
    FinskyLog.d("Request install of %s v=%d for %s", arrayOfObject1);
    FinskyApp.get().getAnalytics().logTagAndPackage("install.request", paramString1, null, paramString6);
    FinskyApp.get().getEventLogger().logTag("install.request", new Object[] { "cidi", paramString1, "r", "older-version" });
    int j = 0;
    label342: long l;
    int k;
    label362: InstallerDataStore.InstallerData localInstallerData1;
    if (paramAndroidAppDeliveryData != null)
    {
      if (AssetUtils.totalDeliverySize(paramAndroidAppDeliveryData, paramString1) < this.mInstallPolicies.getMaxBytesOverMobileRecommended())
        j = 1;
    }
    else
    {
      l = 0L;
      if (paramAndroidAppDeliveryData != null)
        l = System.currentTimeMillis();
      if (j == 0)
        break label446;
      k = 2;
      localInstallerData1 = new InstallerDataStore.InstallerData(paramString1, InstallerDataStore.AutoUpdateState.DEFAULT, paramInt, paramInt, paramAndroidAppDeliveryData, l, 0, null, 0L, paramString3, paramString4, paramString2, paramString5, k);
      if ((localAppState != null) && (localAppState.installerData != null))
        break label452;
      this.mInstallerDataStore.put(localInstallerData1);
    }
    while (true)
    {
      notifyListeners(paramString1, InstallerListener.InstallerPackageEvent.DOWNLOAD_PENDING, 0);
      if (paramBoolean)
        break;
      kick(paramString1, false);
      break;
      j = 0;
      break label342;
      label446: k = 0;
      break label362;
      label452: InstallerDataStore.InstallerData localInstallerData2 = localAppState.installerData;
      if (localInstallerData2.getDesiredVersion() != localInstallerData1.getDesiredVersion())
        this.mInstallerDataStore.setDesiredVersion(paramString1, paramInt);
      if (localInstallerData2.getLastNotifiedVersion() != localInstallerData1.getLastNotifiedVersion())
        this.mInstallerDataStore.setLastNotifiedVersion(paramString1, paramInt);
      if (!Objects.equal(localInstallerData2.getDeliveryData(), localInstallerData1.getDeliveryData()))
        this.mInstallerDataStore.setDeliveryData(paramString1, paramAndroidAppDeliveryData, l);
      this.mInstallerDataStore.setInstallerState(paramString1, 0, null);
      if (!TextUtils.isEmpty(localInstallerData1.getReferrer()))
        this.mInstallerDataStore.setReferrer(paramString1, paramString3);
      if (!Objects.equal(localInstallerData2.getAccountName(), localInstallerData1.getAccountName()))
        this.mInstallerDataStore.setAccount(paramString1, paramString2);
      if (!Objects.equal(localInstallerData2.getTitle(), localInstallerData1.getTitle()))
        this.mInstallerDataStore.setTitle(paramString1, paramString5);
      int m = localInstallerData2.getFlags();
      int n = k | m & 0xFFFFFFF3;
      if (m != n)
        this.mInstallerDataStore.setFlags(paramString1, n);
      if (!Objects.equal(localInstallerData2.getContinueUrl(), localInstallerData1.getContinueUrl()))
        this.mInstallerDataStore.setContinueUrl(paramString1, paramString4);
    }
  }

  public void setMobileDataAllowed(String paramString)
  {
    InstallerDataStore localInstallerDataStore = this.mAppStates.getInstallerDataStore();
    InstallerDataStore.InstallerData localInstallerData = localInstallerDataStore.get(paramString);
    int i = 0;
    if (localInstallerData != null)
      i = localInstallerData.getFlags();
    int j = i | 0x2;
    if (j != i)
      localInstallerDataStore.setFlags(paramString, j);
  }

  public void setVisibility(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    InstallerDataStore localInstallerDataStore = this.mAppStates.getInstallerDataStore();
    InstallerDataStore.InstallerData localInstallerData = localInstallerDataStore.get(paramString);
    int i = 0;
    if (localInstallerData != null)
      i = localInstallerData.getFlags();
    int j = i & 0xFFFFFFEE;
    if (!paramBoolean1)
      j |= 16;
    if (!paramBoolean2)
      j |= 1;
    if (j != i)
      localInstallerDataStore.setFlags(paramString, j);
  }

  public void start()
  {
    this.mDownloadQueue.addListener(this);
    this.mPackageMonitorReceiver.attach(this);
    new AsyncTask()
    {
      protected Collection<DownloadQueue.RunningDownload> doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        File localFile = new File(FinskyApp.get().getCacheDir(), "patches");
        if (localFile.exists())
        {
          File[] arrayOfFile = localFile.listFiles();
          if ((arrayOfFile != null) && (arrayOfFile.length != 0))
          {
            int i = arrayOfFile.length;
            for (int j = 0; j < i; j++)
              arrayOfFile[j].delete();
          }
        }
        InstallerImpl.this.mAppStates.blockingLoad();
        return InstallerImpl.this.mDownloadQueue.getRunningUris();
      }

      protected void onPostExecute(final Collection<DownloadQueue.RunningDownload> paramAnonymousCollection)
      {
        int i = 1;
        if (paramAnonymousCollection != null)
        {
          if (!InstallerImpl.this.multiUserMode())
            break label57;
          i = 0;
          InstallerImpl.this.bindToMultiUserCoordinator(new Runnable()
          {
            public void run()
            {
              InstallerImpl.this.recoverRunningDownloads(paramAnonymousCollection);
              InstallerImpl.access$402(InstallerImpl.this, true);
              InstallerImpl.this.kick(null, true);
            }
          });
        }
        while (true)
        {
          if (i != 0)
          {
            InstallerImpl.access$402(InstallerImpl.this, true);
            InstallerImpl.this.kick(null, true);
          }
          return;
          label57: InstallerImpl.this.recoverRunningDownloads(paramAnonymousCollection);
        }
      }
    }
    .execute(new Void[0]);
  }

  public void startDeferredInstalls()
  {
    kick(null, true);
  }

  void taskFinished(InstallerTask paramInstallerTask)
  {
    if ((this.mInstallerTask != null) && (paramInstallerTask != this.mInstallerTask))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramInstallerTask.packageName;
      FinskyLog.wtf("Unexpected (late?) finish of task for %s", arrayOfObject);
    }
    this.mInstallerTask = null;
    if (multiUserMode())
      releaseMultiUserPackage(paramInstallerTask.packageName);
    kick(null, true);
  }

  public void uninstallAssetSilently(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      FinskyLog.w("Unexpected empty package name", new Object[0]);
    while (true)
    {
      return;
      InstallerTask localInstallerTask = getInstallerTask(paramString);
      if (localInstallerTask != null)
        localInstallerTask.cancel(true);
      FinskyApp.get().getAnalytics().logTagAndPackage("uninstall", paramString, null);
      FinskyApp.get().getEventLogger().logTag("uninstall", new Object[] { "cidi", paramString });
      if (this.mInstallerDataStore.get(paramString) != null)
        this.mInstallerDataStore.setDesiredVersion(paramString, -1);
      try
      {
        this.mContext.getPackageManager().getPackageInfo(paramString, 0);
        this.mUninstallingPackages.add(paramString);
        notifyListeners(paramString, InstallerListener.InstallerPackageEvent.UNINSTALLING, 0);
        PackageManagerHelper.uninstallPackage(paramString);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        FinskyLog.d("Skipping uninstall of %s - already uninstalled.", new Object[] { paramString });
      }
    }
  }

  public void uninstallPackagesByUid(String paramString)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 0);
      for (String str : localPackageManager.getPackagesForUid(localPackageInfo.applicationInfo.uid))
      {
        FinskyLog.d("Removing package '%s' (child of '%s')", new Object[] { str, paramString });
        uninstallAssetSilently(str);
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      FinskyLog.d("Skipping uninstall of %s - already uninstalled.", new Object[] { paramString });
    }
  }

  public void updateInstalledApps(List<Document> paramList, String paramString)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      String str = localDocument.getDocId();
      AppStates.AppState localAppState = this.mAppStates.getApp(str);
      if ((localAppState == null) || (localAppState.packageManagerState == null))
      {
        FinskyLog.d("Cannot update %s because not installed.", new Object[] { str });
      }
      else
      {
        Account localAccount = AppActionAnalyzer.getInstallAccount(str, null, this.mAppStates, this.mLibraries);
        if (localAccount == null)
          FinskyLog.d("Cannot update %s because cannot determine owner.", new Object[] { str });
        else
          requestInstall(str, localDocument.getVersionCode(), null, localAccount.name, null, null, localDocument.getTitle(), false, paramString);
      }
    }
  }

  class RemoteServiceConnection
    implements ServiceConnection
  {
    RemoteServiceConnection()
    {
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      Utils.ensureOnMainThread();
      InstallerImpl.access$802(InstallerImpl.this, IMultiUserCoordinatorService.Stub.asInterface(paramIBinder));
      try
      {
        InstallerImpl.this.mCoordinatorService.registerListener(InstallerImpl.this.mListener);
        for (int i = 0; i < InstallerImpl.this.mServiceConnectionCallbacks.size(); i++)
          ((Runnable)InstallerImpl.this.mServiceConnectionCallbacks.get(i)).run();
      }
      catch (RemoteException localRemoteException)
      {
        FinskyLog.w("Couldn't register listener *** received %s", new Object[] { localRemoteException });
        InstallerImpl.this.mContext.unbindService(InstallerImpl.this.mServiceConnection);
        InstallerImpl.access$802(InstallerImpl.this, null);
        InstallerImpl.this.mServiceConnectionCallbacks.clear();
        if (!InstallerImpl.this.mRunning)
        {
          FinskyLog.w("Force-starting the installer after connection failure", new Object[0]);
          InstallerImpl.access$402(InstallerImpl.this, true);
          InstallerImpl.this.kick(null, true);
        }
      }
      while (true)
      {
        return;
        InstallerImpl.this.mServiceConnectionCallbacks.clear();
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.InstallerImpl
 * JD-Core Version:    0.6.2
 */
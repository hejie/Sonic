package com.google.android.finsky.receivers;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeServerError;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadImpl;
import com.google.android.finsky.download.DownloadManagerConstants;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.InternalDownload;
import com.google.android.finsky.download.Storage;
import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppPatchData;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.HttpCookie;
import com.google.android.finsky.remoting.protos.Delivery.DeliveryResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.Objects;
import com.google.android.finsky.utils.PackageManagerHelper;
import com.google.android.finsky.utils.PackageManagerHelper.InstallPackageListener;
import com.google.android.finsky.utils.PackageManagerUtils;
import com.google.android.finsky.utils.Users;
import com.google.android.finsky.utils.VendingPreferences;
import com.google.android.volley.DisplayMessageError;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class InstallerTask
{
  private static Installer.InstallerProgressReport PROGRESS_INSTALLING = new Installer.InstallerProgressReport(Installer.InstallerState.INSTALLING, 0L, 0L, 0);
  private static Installer.InstallerProgressReport PROGRESS_NOT_TRACKED = new Installer.InstallerProgressReport(Installer.InstallerState.NOT_TRACKED, 0L, 0L, 0);
  private long mApkCompleted;
  private long mApkSize;
  private final AppStates mAppStates;
  private final DownloadQueue mDownloadQueue;
  private int mDownloadStatus;
  private final InstallPolicies mInstallPolicies;
  private final InstallerImpl mInstaller;
  private final InstallerDataStore mInstallerDataStore;
  private boolean mIsUpdate;
  private boolean mMobileDataAllowed;
  private final Notifier mNotifier;
  private Obb mObbMain;
  private long mObbMainCompleted;
  private long mObbMainSize;
  private Obb mObbPatch;
  private long mObbPatchCompleted;
  private long mObbPatchSize;
  private int mRecoveredIntoState;
  private boolean mShowDownloadNotifications;
  private boolean mShowInstallNotifications;
  private boolean mShowProgress;
  private String mTitle;
  public final String packageName;

  public InstallerTask(String paramString, InstallerImpl paramInstallerImpl, AppStates paramAppStates, DownloadQueue paramDownloadQueue, Notifier paramNotifier, InstallPolicies paramInstallPolicies)
  {
    this.packageName = paramString;
    this.mInstaller = paramInstallerImpl;
    this.mAppStates = paramAppStates;
    this.mDownloadQueue = paramDownloadQueue;
    this.mNotifier = paramNotifier;
    this.mInstallerDataStore = paramAppStates.getInstallerDataStore();
    this.mInstallPolicies = paramInstallPolicies;
  }

  private void advanceState()
  {
    AppStates.AppState localAppState = this.mAppStates.getApp(this.packageName);
    if ((localAppState == null) || (localAppState.installerData == null))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = this.packageName;
      FinskyLog.wtf("Unexpected missing installer data for %s", arrayOfObject1);
      cancel(true);
    }
    while (true)
    {
      return;
      InstallerDataStore.InstallerData localInstallerData = localAppState.installerData;
      switch (localInstallerData.getInstallerState())
      {
      case 25:
      case 35:
      case 45:
      default:
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(localInstallerData.getInstallerState());
        arrayOfObject2[1] = this.packageName;
        FinskyLog.wtf("Bad state %d for %s", arrayOfObject2);
        cancel(true);
        break;
      case 0:
        int i = localInstallerData.getDesiredVersion();
        PackageStateRepository.PackageState localPackageState = localAppState.packageManagerState;
        if (localPackageState != null);
        for (int j = localPackageState.installedVersion; ; j = -1)
        {
          if (j < i)
            break label283;
          if (j > i)
            this.mAppStates.getInstallerDataStore().setDesiredVersion(this.packageName, j);
          setInstallerState(70, (String)null);
          advanceState();
          break;
        }
        int k = getInstalledVersionForOtherUser(localAppState);
        if (k >= i)
        {
          if (k > i)
            this.mAppStates.getInstallerDataStore().setDesiredVersion(this.packageName, k);
          int m = 0x20 | localInstallerData.getFlags();
          this.mInstallerDataStore.setFlags(this.packageName, m);
          setInstallerState(50, (String)null);
          advanceState();
        }
        else if (!checkValidDeliveryData(localInstallerData))
        {
          requestDeliveryData(localAppState);
        }
        break;
      case 10:
        processDeliveryData(localInstallerData, false);
      case 20:
      case 30:
      case 40:
        startNextDownload(localInstallerData);
        break;
      case 50:
        if ((0x4 & localInstallerData.getFlags()) != 0)
          startApplyingPatch(localAppState);
        break;
      case 60:
        startInstaller(localAppState);
        break;
      case 70:
        cleanup(localAppState);
        break;
      case 80:
        label283: this.mInstaller.clearInstallerState(localAppState);
        this.mInstaller.taskFinished(this);
      }
    }
  }

  private boolean canDownloadPatch(InstallerDataStore.InstallerData paramInstallerData)
  {
    int i = paramInstallerData.getFlags();
    boolean bool;
    if ((i & 0x4) != 0)
      bool = true;
    while (true)
    {
      return bool;
      if ((i & 0x8) != 0)
      {
        bool = false;
      }
      else
      {
        String str1 = paramInstallerData.getPackageName();
        AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = paramInstallerData.getDeliveryData();
        AndroidAppDelivery.AndroidAppPatchData localAndroidAppPatchData = localAndroidAppDeliveryData.getPatchData();
        if (localAndroidAppPatchData == null)
        {
          bool = false;
        }
        else if (Build.VERSION.SDK_INT < 9)
        {
          reportPatchFailure(str1, "gingerbread");
          bool = false;
        }
        else
        {
          PackageStateRepository.PackageState localPackageState = this.mAppStates.getPackageStateRepository().get(str1);
          if (localPackageState == null)
          {
            reportPatchFailure(str1, "no-base-app-installed");
            bool = false;
          }
          else
          {
            int j = localAndroidAppPatchData.getBaseVersionCode();
            if (localPackageState.installedVersion != j)
            {
              reportPatchFailure(str1, "wrong-base-app-installed");
              Object[] arrayOfObject2 = new Object[3];
              arrayOfObject2[0] = str1;
              arrayOfObject2[1] = Integer.valueOf(j);
              arrayOfObject2[2] = Integer.valueOf(localPackageState.installedVersion);
              FinskyLog.d("Cannot patch %s, need version %d but has %d", arrayOfObject2);
              bool = false;
            }
            else
            {
              PackageManager localPackageManager = FinskyApp.get().getPackageManager();
              String str2;
              try
              {
                ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str1, 0);
                str2 = localApplicationInfo.sourceDir;
                String str3 = localApplicationInfo.publicSourceDir;
                if ((!TextUtils.isEmpty(str2)) && (Objects.equal(str2, str3)))
                  break label298;
                reportPatchFailure(str1, "base-app-dirs-mismatch");
                FinskyLog.d("Cannot patch %s, source dir is %s", new Object[] { str1, str2 });
                bool = false;
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException)
              {
                reportPatchFailure(str1, "base-app-NameNotFoundException");
                FinskyLog.d("Cannot patch %s, NameNotFoundException", new Object[] { str1 });
                bool = false;
              }
              continue;
              label298: long l1 = Storage.dataPartitionAvailableSpace();
              long l2 = ((Integer)G.downloadPatchFreeSpaceFactor.get()).intValue() * localAndroidAppDeliveryData.getDownloadSize() / 100L;
              if (l1 < l2)
              {
                reportPatchFailure(str1, "free-space");
                Object[] arrayOfObject1 = new Object[3];
                arrayOfObject1[0] = str1;
                arrayOfObject1[1] = Long.valueOf(l2);
                arrayOfObject1[2] = Long.valueOf(l1);
                FinskyLog.d("Cannot patch %s, need %d, free %d", arrayOfObject1);
                bool = false;
              }
              else
              {
                File localFile = new File(str2);
                if (!localFile.exists())
                {
                  reportPatchFailure(str1, "base-file-exists");
                  FinskyLog.d("Cannot patch %s, file does not exist %s", new Object[] { str1, localFile });
                  bool = false;
                }
                else
                {
                  try
                  {
                    if (PackageManagerHelper.verifyApk(new FileInputStream(localFile), -1L, localAndroidAppPatchData.getBaseSignature()))
                      break label525;
                    reportPatchFailure(str1, "base-file-signature");
                    FinskyLog.d("Cannot patch %s, signature mismatch", new Object[] { str1 });
                    bool = false;
                  }
                  catch (FileNotFoundException localFileNotFoundException)
                  {
                    reportPatchFailure(str1, "base-file-FileNotFoundException");
                    FinskyLog.d("Cannot patch %s, FileNotFoundException, %s", new Object[] { str1, localFile });
                    bool = false;
                  }
                  continue;
                  label525: bool = true;
                }
              }
            }
          }
        }
      }
    }
  }

  private void cancelCleanup(AppStates.AppState paramAppState)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.packageName;
    FinskyLog.d("Cancel running installation of %s", arrayOfObject);
    Download localDownload = this.mDownloadQueue.getByPackageName(this.packageName);
    if (localDownload != null)
      this.mDownloadQueue.cancel(localDownload);
    this.mInstaller.clearInstallerState(paramAppState);
    if ((this.mObbMain == null) && (this.mObbPatch == null) && (paramAppState.installerData != null) && (paramAppState.installerData.getDeliveryData() != null))
      processDeliveryData(paramAppState.installerData, false);
    if (this.mObbMain != null)
      this.mObbMain.getTempFile().delete();
    if (this.mObbPatch != null)
      this.mObbPatch.getTempFile().delete();
    this.mInstaller.taskFinished(this);
  }

  private boolean checkValidDeliveryData(InstallerDataStore.InstallerData paramInstallerData)
  {
    boolean bool;
    if (paramInstallerData.getDeliveryData() == null)
      bool = false;
    while (true)
    {
      return bool;
      long l = paramInstallerData.getDeliveryDataTimestampMs();
      if (l == 0L)
      {
        bool = true;
      }
      else if (l + ((Long)G.deliveryDataExpirationMs.get()).longValue() < System.currentTimeMillis())
      {
        this.mInstallerDataStore.setDeliveryData(this.packageName, null, 0L);
        bool = false;
      }
      else
      {
        bool = true;
      }
    }
  }

  public static void cleanObbDirectory(Obb paramObb1, Obb paramObb2, String paramString)
  {
    if ((paramObb1 == null) && (paramObb2 == null));
    File localFile1;
    File localFile2;
    File[] arrayOfFile;
    do
    {
      return;
      localFile1 = null;
      if ((paramObb1 != null) && (paramObb1.getState() == 3))
        localFile1 = paramObb1.getFile();
      localFile2 = null;
      if ((paramObb2 != null) && (paramObb2.getState() == 3))
        localFile2 = paramObb2.getFile();
      arrayOfFile = ObbFactory.getParentDirectory(paramString).listFiles();
    }
    while (arrayOfFile == null);
    int i = arrayOfFile.length;
    int j = 0;
    label79: File localFile3;
    if (j < i)
    {
      localFile3 = arrayOfFile[j];
      if ((localFile1 == null) || (!localFile1.equals(localFile3)))
        break label112;
    }
    while (true)
    {
      j++;
      break label79;
      break;
      label112: if ((localFile2 == null) || (!localFile2.equals(localFile3)))
      {
        FinskyLog.d("OBB cleanup %s", new Object[] { localFile3 });
        localFile3.delete();
      }
    }
  }

  private void cleanup(AppStates.AppState paramAppState)
  {
    int i = -1;
    if (paramAppState.packageManagerState != null)
      i = paramAppState.packageManagerState.installedVersion;
    if (i != paramAppState.installerData.getDesiredVersion())
    {
      cancelCleanup(paramAppState);
      notifyListeners(InstallerListener.InstallerPackageEvent.INSTALL_ERROR, 910);
    }
    while (true)
    {
      return;
      if ((this.mObbMain != null) && (this.mObbMain.getState() != 5))
      {
        this.mObbMain.syncStateWithStorage();
        if (this.mObbMain.getState() != 3)
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = this.packageName;
          FinskyLog.e("Lost main obb file for %s", arrayOfObject2);
          cancelCleanup(paramAppState);
          notifyListeners(InstallerListener.InstallerPackageEvent.INSTALL_ERROR, 911);
          showDownloadNotification(911, null);
        }
      }
      else if ((this.mObbPatch != null) && (this.mObbPatch.getState() != 5))
      {
        this.mObbPatch.syncStateWithStorage();
        if (this.mObbPatch.getState() != 3)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = this.packageName;
          FinskyLog.e("Lost patch obb file for %s", arrayOfObject1);
          cancelCleanup(paramAppState);
          notifyListeners(InstallerListener.InstallerPackageEvent.INSTALL_ERROR, 912);
          showDownloadNotification(912, null);
        }
      }
      else
      {
        cleanObbDirectory(this.mObbMain, this.mObbPatch, this.packageName);
        setInstallerState(80, (String)null);
        notifyListeners(InstallerListener.InstallerPackageEvent.INSTALLED, 0);
        InstallerDataStore.InstallerData localInstallerData = paramAppState.installerData;
        if (this.mShowInstallNotifications)
        {
          String str1 = localInstallerData.getTitle();
          String str2 = localInstallerData.getContinueUrl();
          this.mNotifier.showSuccessfulInstallMessage(str1, this.packageName, str2, this.mIsUpdate);
        }
        advanceState();
      }
    }
  }

  private int deliveryResponseToInstallerError(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    default:
      i = 943;
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return i;
      i = 940;
      continue;
      i = 941;
      continue;
      i = 942;
    }
  }

  private InternalDownload generateDownload(InstallerDataStore.InstallerData paramInstallerData)
  {
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = paramInstallerData.getDeliveryData();
    String str1 = paramInstallerData.getTitle();
    String str2 = paramInstallerData.getPackageName();
    int i = paramInstallerData.getDesiredVersion();
    AndroidAppDelivery.HttpCookie localHttpCookie = localAndroidAppDeliveryData.getDownloadAuthCookie(0);
    String str3;
    long l1;
    long l2;
    String str4;
    String str5;
    boolean bool1;
    if (canDownloadPatch(paramInstallerData))
    {
      AndroidAppDelivery.AndroidAppPatchData localAndroidAppPatchData = localAndroidAppDeliveryData.getPatchData();
      str3 = localAndroidAppPatchData.getDownloadUrl();
      l1 = -1L;
      l2 = localAndroidAppPatchData.getMaxPatchSize();
      int j = paramInstallerData.getFlags();
      if ((j & 0x4) == 0)
      {
        int k = j | 0x4;
        this.mInstallerDataStore.setFlags(str2, k);
      }
      str4 = localHttpCookie.getName();
      str5 = localHttpCookie.getValue();
      if (this.mMobileDataAllowed)
        break label176;
      bool1 = true;
      label118: if (this.mShowProgress)
        break label182;
    }
    label176: label182: for (boolean bool2 = true; ; bool2 = false)
    {
      return new DownloadImpl(str3, str1, str2, i, str4, str5, null, l1, l2, null, bool1, bool2);
      str3 = localAndroidAppDeliveryData.getDownloadUrl();
      l1 = localAndroidAppDeliveryData.getDownloadSize();
      l2 = l1;
      break;
      bool1 = false;
      break label118;
    }
  }

  private InternalDownload generateObbDownload(InstallerDataStore.InstallerData paramInstallerData, Obb paramObb)
  {
    FinskyApp localFinskyApp = FinskyApp.get();
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = paramInstallerData.getDeliveryData();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramInstallerData.getTitle();
    String str1 = localFinskyApp.getString(2131165593, arrayOfObject);
    String str2 = paramInstallerData.getPackageName();
    int i = paramInstallerData.getDesiredVersion();
    AndroidAppDelivery.HttpCookie localHttpCookie = localAndroidAppDeliveryData.getDownloadAuthCookie(0);
    Uri localUri = Uri.fromFile(paramObb.getTempFile());
    long l = localAndroidAppDeliveryData.getDownloadSize();
    String str3 = paramObb.getUrl();
    String str4 = localHttpCookie.getName();
    String str5 = localHttpCookie.getValue();
    boolean bool1;
    if (!this.mMobileDataAllowed)
    {
      bool1 = true;
      if (this.mShowProgress)
        break label152;
    }
    label152: for (boolean bool2 = true; ; bool2 = false)
    {
      return new DownloadImpl(str3, str1, str2, i, str4, str5, localUri, l, l, paramObb, bool1, bool2);
      bool1 = false;
      break;
    }
  }

  private int getInstalledVersionForOtherUser(AppStates.AppState paramAppState)
  {
    int i = -1;
    if (!FinskyApp.get().getUsers().supportsMultiUser());
    while (true)
    {
      return i;
      if (paramAppState.packageManagerState == null)
      {
        PackageManager localPackageManager = FinskyApp.get().getPackageManager();
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(this.packageName, 8192);
          if ((0x800000 & localPackageInfo.applicationInfo.flags) == 0)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = this.packageName;
            arrayOfObject[1] = Integer.valueOf(localPackageInfo.versionCode);
            FinskyLog.d("Found %s version %d installed for another user", arrayOfObject);
            i = localPackageInfo.versionCode;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
        }
      }
    }
  }

  private PackageManagerHelper.InstallPackageListener getInstallerListener(final Uri paramUri)
  {
    return new PackageManagerHelper.InstallPackageListener()
    {
      public void installCompleted(boolean paramAnonymousBoolean, String paramAnonymousString)
      {
        InstallerTask.this.releaseInstalledUri(paramUri);
        if (paramAnonymousBoolean)
        {
          FinskyApp.get().getAnalytics().logTagAndPackage("install.installerFinished", InstallerTask.this.packageName, null);
          FinskyEventLog localFinskyEventLog2 = FinskyApp.get().getEventLogger();
          Object[] arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = "cidi";
          arrayOfObject2[1] = InstallerTask.this.packageName;
          localFinskyEventLog2.logTag("install.installerFinished", arrayOfObject2);
        }
        while (true)
        {
          InstallerTask.this.setInstallerState(70, (String)null);
          InstallerTask.this.advanceState();
          return;
          FinskyApp.get().getAnalytics().logTagAndPackage("install.installerError", InstallerTask.this.packageName, paramAnonymousString);
          FinskyEventLog localFinskyEventLog1 = FinskyApp.get().getEventLogger();
          Object[] arrayOfObject1 = new Object[4];
          arrayOfObject1[0] = "cidi";
          arrayOfObject1[1] = InstallerTask.this.packageName;
          arrayOfObject1[2] = "r";
          arrayOfObject1[3] = paramAnonymousString;
          localFinskyEventLog1.logTag("install.installerError", arrayOfObject1);
        }
      }
    };
  }

  private void getUiFields(AppStates.AppState paramAppState)
  {
    boolean bool1 = true;
    InstallerDataStore.InstallerData localInstallerData = paramAppState.installerData;
    boolean bool2;
    boolean bool3;
    if (paramAppState.packageManagerState != null)
    {
      bool2 = bool1;
      this.mIsUpdate = bool2;
      this.mTitle = localInstallerData.getTitle();
      int i = localInstallerData.getFlags();
      if ((i & 0x1) != 0)
        break label81;
      bool3 = bool1;
      label47: this.mShowInstallNotifications = bool3;
      if ((i & 0x10) != 0)
        break label87;
    }
    while (true)
    {
      this.mShowProgress = bool1;
      this.mShowDownloadNotifications = this.mShowProgress;
      return;
      bool2 = false;
      break;
      label81: bool3 = false;
      break label47;
      label87: bool1 = false;
    }
  }

  private void notifyListeners(InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    this.mInstaller.notifyListeners(this.packageName, paramInstallerPackageEvent, paramInt);
  }

  private void processDeliveryData(InstallerDataStore.InstallerData paramInstallerData, boolean paramBoolean)
  {
    boolean bool1 = true;
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = paramInstallerData.getDeliveryData();
    this.mApkSize = localAndroidAppDeliveryData.getDownloadSize();
    this.mObbMainSize = 0L;
    this.mObbPatchSize = 0L;
    this.mApkCompleted = 0L;
    this.mObbMainCompleted = 0L;
    this.mObbPatchCompleted = 0L;
    if (localAndroidAppDeliveryData.getAdditionalFileCount() > 0)
      if (!Storage.externalStorageAvailable())
      {
        cancel(false);
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 901);
        FinskyApp.get().getAnalytics().logTagAndPackage("install.abandonInstall", this.packageName, "obb-no-external-storage");
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = "cidi";
        arrayOfObject[bool1] = this.packageName;
        arrayOfObject[2] = "r";
        arrayOfObject[3] = "obb-no-external-storage";
        localFinskyEventLog.logTag("install.abandonInstall", arrayOfObject);
        if (this.mShowDownloadNotifications)
          this.mNotifier.showExternalStorageMissing(paramInstallerData.getTitle(), this.packageName);
      }
    boolean bool2;
    label294: 
    do
    {
      return;
      this.mObbMain = AssetUtils.extractObb(localAndroidAppDeliveryData, paramInstallerData.getPackageName(), false);
      if (this.mObbMain != null)
      {
        this.mObbMain.syncStateWithStorage();
        if (this.mObbMain.getState() == 4)
          this.mObbMainSize = this.mObbMain.getSize();
      }
      this.mObbPatch = AssetUtils.extractObb(localAndroidAppDeliveryData, paramInstallerData.getPackageName(), bool1);
      if (this.mObbPatch != null)
      {
        this.mObbMain.syncStateWithStorage();
        if (this.mObbPatch.getState() == 4)
          this.mObbPatchSize = this.mObbPatch.getSize();
      }
      if ((0x2 & paramInstallerData.getFlags()) == 0)
        break;
      bool2 = bool1;
      this.mMobileDataAllowed = bool2;
    }
    while ((!paramBoolean) || (this.mMobileDataAllowed));
    if (this.mApkSize + this.mObbMainSize + this.mObbPatchSize < this.mInstallPolicies.getMaxBytesOverMobileRecommended());
    while (true)
    {
      this.mMobileDataAllowed = bool1;
      if (!this.mMobileDataAllowed)
        break;
      this.mInstaller.setMobileDataAllowed(this.packageName);
      break;
      bool2 = false;
      break label294;
      bool1 = false;
    }
  }

  private boolean recoverApk(AppStates.AppState paramAppState, Uri paramUri, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    if (paramInt2 <= paramInt3)
    {
      Object[] arrayOfObject4 = new Object[3];
      arrayOfObject4[0] = this.packageName;
      arrayOfObject4[1] = Integer.valueOf(paramInt2);
      arrayOfObject4[2] = Integer.valueOf(paramInt3);
      FinskyLog.d("Recovery of %s skipped because desired= %d installed= %d", arrayOfObject4);
    }
    while (true)
    {
      return bool;
      if (!DownloadManagerConstants.isStatusCompleted(paramInt1))
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.packageName;
        FinskyLog.d("Recovery of %s into downloading APK state", arrayOfObject3);
        InternalDownload localInternalDownload = generateDownload(paramAppState.installerData);
        localInternalDownload.setContentUri(paramUri);
        this.mDownloadQueue.addRecoveredDownload(localInternalDownload);
        bool = true;
      }
      else if (DownloadManagerConstants.isStatusSuccess(paramInt1))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.packageName;
        FinskyLog.d("Recovery of %s into ready to install state", arrayOfObject2);
        setInstallerState(50, paramUri.toString());
        advanceState();
        bool = true;
      }
      else
      {
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = this.packageName;
        arrayOfObject1[1] = Integer.valueOf(paramInt1);
        FinskyLog.d("Recovery of %s into error state, status= %d", arrayOfObject1);
        cancel(false);
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, paramInt1);
        showDownloadNotification(paramInt1, null);
      }
    }
  }

  private boolean recoverInstalling(AppStates.AppState paramAppState, Uri paramUri, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 < paramInt3)
    {
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = this.packageName;
      arrayOfObject3[1] = Integer.valueOf(paramInt2);
      arrayOfObject3[2] = Integer.valueOf(paramInt3);
      FinskyLog.d("Recovery of %s skipped because desired= %d installed= %d", arrayOfObject3);
    }
    while (true)
    {
      return false;
      if (paramInt2 == paramInt3)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.packageName;
        FinskyLog.d("Recovery of %s - installation seems complete", arrayOfObject2);
        setInstallerState(70, paramUri.toString());
        advanceState();
      }
      else
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = this.packageName;
        FinskyLog.d("Recovery of %s with incomplete installation", arrayOfObject1);
        cancel(false);
        notifyListeners(InstallerListener.InstallerPackageEvent.UNINSTALLED, paramInt1);
      }
    }
  }

  private boolean recoverObb(AppStates.AppState paramAppState, Uri paramUri, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    String str;
    boolean bool;
    if (paramBoolean)
    {
      str = "Patch";
      if (paramInt2 > paramInt3)
        break label77;
      Object[] arrayOfObject5 = new Object[4];
      arrayOfObject5[0] = this.packageName;
      arrayOfObject5[1] = str;
      arrayOfObject5[2] = Integer.valueOf(paramInt2);
      arrayOfObject5[3] = Integer.valueOf(paramInt3);
      FinskyLog.d("Recovery of %s %s Obb skipped because desired= %d installed= %d", arrayOfObject5);
      bool = false;
    }
    while (true)
    {
      return bool;
      str = "Main";
      break;
      label77: if (!DownloadManagerConstants.isStatusCompleted(paramInt1))
      {
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = this.packageName;
        arrayOfObject4[1] = str;
        FinskyLog.d("Recovery of %s %s Obb into downloading OBB state", arrayOfObject4);
        if (paramBoolean);
        for (Obb localObb2 = this.mObbPatch; ; localObb2 = this.mObbMain)
        {
          InternalDownload localInternalDownload = generateObbDownload(paramAppState.installerData, localObb2);
          localInternalDownload.setContentUri(paramUri);
          this.mDownloadQueue.addRecoveredDownload(localInternalDownload);
          bool = true;
          break;
        }
      }
      if (DownloadManagerConstants.isStatusSuccess(paramInt1))
      {
        Obb localObb1;
        if (paramBoolean)
        {
          localObb1 = this.mObbPatch;
          label187: localObb1.syncStateWithStorage();
          if ((localObb1.getState() != 4) || (localObb1.finalizeTempFile()))
            break label297;
          Object[] arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = this.packageName;
          arrayOfObject3[1] = str;
          FinskyLog.d("Recovery of %s %s Obb skipped - finalize failed", arrayOfObject3);
          cancel(false);
          if (!paramBoolean)
            break label289;
        }
        label289: for (int j = 912; ; j = 911)
        {
          notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, j);
          showDownloadNotification(j, null);
          bool = false;
          break;
          localObb1 = this.mObbMain;
          break label187;
        }
        label297: Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = this.packageName;
        arrayOfObject2[1] = str;
        FinskyLog.d("Recovery of %s %s Obb into ready to install state", arrayOfObject2);
        if (paramBoolean);
        for (int i = 40; ; i = 30)
        {
          setInstallerState(i, paramUri.toString());
          advanceState();
          bool = true;
          break;
        }
      }
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = this.packageName;
      arrayOfObject1[1] = str;
      arrayOfObject1[2] = Integer.valueOf(paramInt1);
      FinskyLog.d("Recovery of %s %s Obb into error state, status= %d", arrayOfObject1);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, paramInt1);
      showDownloadNotification(paramInt1, null);
      bool = false;
    }
  }

  private void reportPatchFailure(String paramString1, String paramString2)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.applyPatch", paramString1, paramString2);
    FinskyApp.get().getEventLogger().logTag("install.applyPatch", new Object[] { "cidi", paramString1, "r", paramString2 });
  }

  private void requestDeliveryData(AppStates.AppState paramAppState)
  {
    InstallerDataStore.InstallerData localInstallerData = paramAppState.installerData;
    final String str1 = localInstallerData.getPackageName();
    int i = localInstallerData.getDesiredVersion();
    String str2 = localInstallerData.getAccountName();
    Account localAccount = AccountHandler.findAccount(str2, FinskyApp.get());
    if (localAccount == null)
    {
      FinskyLog.d("Invalid account %s", new Object[] { str2 });
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 906);
      showDownloadNotification(906, null);
    }
    while (true)
    {
      return;
      Integer localInteger = null;
      if ((paramAppState.packageManagerState != null) && (((Boolean)G.downloadSendBaseVersionCode.get()).booleanValue()))
        localInteger = Integer.valueOf(paramAppState.packageManagerState.installedVersion);
      byte[] arrayOfByte = FinskyApp.get().getLibraries().getAccountLibrary(localAccount).getServerToken(AccountLibrary.LIBRARY_ID_APPS);
      FinskyApp.get().getDfeApi(str2).delivery(str1, 1, arrayOfByte, Integer.valueOf(i), localInteger, "1", new Response.Listener()
      {
        public void onResponse(Delivery.DeliveryResponse paramAnonymousDeliveryResponse)
        {
          int i = paramAnonymousDeliveryResponse.getStatus();
          if (i == 1)
          {
            InstallerTask.this.mInstallerDataStore.setDeliveryData(str1, paramAnonymousDeliveryResponse.getAppDeliveryData(), System.currentTimeMillis());
            InstallerDataStore.InstallerData localInstallerData = InstallerTask.this.mAppStates.getApp(str1).installerData;
            InstallerTask.this.processDeliveryData(localInstallerData, true);
            InstallerTask.this.startNextDownload(localInstallerData);
          }
          while (true)
          {
            return;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(i);
            FinskyLog.d("Received non-OK response %d", arrayOfObject);
            InstallerTask.this.cancel(false);
            int j = InstallerTask.this.deliveryResponseToInstallerError(i);
            InstallerTask.this.notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, j);
            InstallerTask.this.showDownloadNotification(j, null);
          }
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          int i = InstallerTask.this.volleyErrorToInstallerError(paramAnonymousVolleyError);
          if ((paramAnonymousVolleyError instanceof DisplayMessageError));
          for (String str = ((DisplayMessageError)paramAnonymousVolleyError).getDisplayErrorHtml(); ; str = null)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(i);
            arrayOfObject[1] = str;
            FinskyLog.d("Received VolleyError %d (%s)", arrayOfObject);
            InstallerTask.this.cancel(false);
            InstallerTask.this.notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, i);
            InstallerTask.this.showDownloadNotification(i, str);
            return;
          }
        }
      });
      setInstallerState(10, localInstallerData.getDownloadUri());
    }
  }

  private void setInstallerState(int paramInt, Uri paramUri)
  {
    if (paramUri != null);
    for (String str = paramUri.toString(); ; str = null)
    {
      setInstallerState(paramInt, str);
      return;
    }
  }

  private void setInstallerState(int paramInt, String paramString)
  {
    this.mInstallerDataStore.setInstallerState(this.packageName, paramInt, paramString);
  }

  private void showDownloadNotification(int paramInt, String paramString)
  {
    if (this.mShowDownloadNotifications)
      this.mNotifier.showDownloadErrorMessage(this.mTitle, this.packageName, paramInt, paramString, this.mIsUpdate);
  }

  private boolean startActivation(final AppStates.AppState paramAppState)
  {
    if ((0x20 & paramAppState.installerData.getFlags()) == 0);
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      new AsyncTask()
      {
        protected Integer doInBackground(Void[] paramAnonymousArrayOfVoid)
        {
          try
          {
            Integer localInteger2 = Integer.valueOf(PackageManagerUtils.installExistingPackage(FinskyApp.get(), paramAppState.packageName));
            localInteger1 = localInteger2;
            return localInteger1;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            while (true)
              Integer localInteger1 = Integer.valueOf(-3);
          }
        }

        protected void onPostExecute(Integer paramAnonymousInteger)
        {
          String str = null;
          FinskyApp.get().getPackageInfoRepository().invalidate(paramAppState.packageName);
          if (paramAnonymousInteger.intValue() == 1)
            if ((InstallerTask.this.mShowInstallNotifications) && (((Boolean)VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue()))
              PackageManagerHelper.addAppShortcut(FinskyApp.get(), paramAppState.packageName);
          while (true)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = paramAppState.packageName;
            arrayOfObject[1] = paramAnonymousInteger;
            FinskyLog.d("installExistingPackage %s result %d", arrayOfObject);
            FinskyApp.get().getAnalytics().logTagAndPackage("install.activate", InstallerTask.this.packageName, str);
            InstallerTask.this.setInstallerState(70, (String)null);
            InstallerTask.this.advanceState();
            return;
            PackageManagerHelper.notifyFailedInstall(paramAppState.packageName, paramAnonymousInteger.intValue());
            str = Integer.toString(paramAnonymousInteger.intValue());
          }
        }
      }
      .execute(new Void[0]);
      setInstallerState(60, (String)null);
      notifyListeners(InstallerListener.InstallerPackageEvent.INSTALLING, 0);
    }
  }

  private void startApplyingPatch(AppStates.AppState paramAppState)
  {
    final InstallerDataStore.InstallerData localInstallerData = paramAppState.installerData;
    final String str = localInstallerData.getDownloadUri();
    final Uri localUri = Uri.parse(str);
    setInstallerState(55, localUri);
    new AsyncTask()
    {
      // ERROR //
      protected File doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 24	com/google/android/finsky/receivers/InstallerTask$6:val$installerData	Lcom/google/android/finsky/appstate/InstallerDataStore$InstallerData;
        //   4: invokevirtual 49	com/google/android/finsky/appstate/InstallerDataStore$InstallerData:getPackageName	()Ljava/lang/String;
        //   7: astore_2
        //   8: invokestatic 55	com/google/android/finsky/FinskyApp:get	()Lcom/google/android/finsky/FinskyApp;
        //   11: astore_3
        //   12: aload_3
        //   13: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
        //   16: astore 4
        //   18: aload_3
        //   19: invokevirtual 65	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   22: astore 5
        //   24: new 67	java/io/RandomAccessFile
        //   27: dup
        //   28: new 69	java/io/File
        //   31: dup
        //   32: aload 5
        //   34: aload_2
        //   35: iconst_0
        //   36: invokevirtual 75	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   39: getfield 80	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
        //   42: invokespecial 83	java/io/File:<init>	(Ljava/lang/String;)V
        //   45: ldc 85
        //   47: invokespecial 88	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
        //   50: astore 6
        //   52: aload_3
        //   53: invokevirtual 61	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
        //   56: aload_0
        //   57: getfield 26	com/google/android/finsky/receivers/InstallerTask$6:val$downloadUri	Landroid/net/Uri;
        //   60: invokevirtual 94	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
        //   63: astore 9
        //   65: aload_3
        //   66: invokevirtual 98	android/content/Context:getCacheDir	()Ljava/io/File;
        //   69: astore 11
        //   71: aload 11
        //   73: iconst_1
        //   74: iconst_0
        //   75: invokevirtual 102	java/io/File:setExecutable	(ZZ)Z
        //   78: ifne +87 -> 165
        //   81: aload_0
        //   82: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   85: aload_2
        //   86: ldc 104
        //   88: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   91: ldc 110
        //   93: iconst_1
        //   94: anewarray 112	java/lang/Object
        //   97: dup
        //   98: iconst_0
        //   99: aload 11
        //   101: aastore
        //   102: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   105: aconst_null
        //   106: astore 8
        //   108: aload 8
        //   110: areturn
        //   111: astore 48
        //   113: aload_0
        //   114: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   117: aload_2
        //   118: ldc 120
        //   120: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   123: aconst_null
        //   124: astore 8
        //   126: goto -18 -> 108
        //   129: astore 47
        //   131: aload_0
        //   132: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   135: aload_2
        //   136: ldc 122
        //   138: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   141: aconst_null
        //   142: astore 8
        //   144: goto -36 -> 108
        //   147: astore 7
        //   149: aload_0
        //   150: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   153: aload_2
        //   154: ldc 124
        //   156: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   159: aconst_null
        //   160: astore 8
        //   162: goto -54 -> 108
        //   165: new 69	java/io/File
        //   168: dup
        //   169: aload 11
        //   171: ldc 126
        //   173: invokespecial 127	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
        //   176: astore 12
        //   178: aload 12
        //   180: invokevirtual 131	java/io/File:mkdirs	()Z
        //   183: pop
        //   184: aload 12
        //   186: iconst_1
        //   187: iconst_0
        //   188: invokevirtual 102	java/io/File:setExecutable	(ZZ)Z
        //   191: ifeq +513 -> 704
        //   194: aload 12
        //   196: iconst_1
        //   197: iconst_0
        //   198: invokevirtual 134	java/io/File:setReadable	(ZZ)Z
        //   201: ifeq +503 -> 704
        //   204: iconst_1
        //   205: istore 14
        //   207: iload 14
        //   209: ifne +33 -> 242
        //   212: aload_0
        //   213: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   216: aload_2
        //   217: ldc 136
        //   219: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   222: ldc 138
        //   224: iconst_1
        //   225: anewarray 112	java/lang/Object
        //   228: dup
        //   229: iconst_0
        //   230: aload 12
        //   232: aastore
        //   233: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   236: aconst_null
        //   237: astore 8
        //   239: goto -131 -> 108
        //   242: aload_2
        //   243: ldc 140
        //   245: aload 12
        //   247: invokestatic 144	java/io/File:createTempFile	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
        //   250: astore 8
        //   252: new 146	java/io/FileOutputStream
        //   255: dup
        //   256: aload 8
        //   258: invokespecial 149	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   261: astore 15
        //   263: aload 8
        //   265: iconst_1
        //   266: iconst_0
        //   267: invokevirtual 134	java/io/File:setReadable	(ZZ)Z
        //   270: ifne +57 -> 327
        //   273: aload_0
        //   274: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   277: aload_2
        //   278: ldc 151
        //   280: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   283: ldc 138
        //   285: iconst_1
        //   286: anewarray 112	java/lang/Object
        //   289: dup
        //   290: iconst_0
        //   291: aload 8
        //   293: aastore
        //   294: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   297: aload 8
        //   299: invokevirtual 154	java/io/File:delete	()Z
        //   302: pop
        //   303: aconst_null
        //   304: astore 8
        //   306: goto -198 -> 108
        //   309: astore 10
        //   311: aload_0
        //   312: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   315: aload_2
        //   316: ldc 156
        //   318: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   321: aconst_null
        //   322: astore 8
        //   324: goto -216 -> 108
        //   327: aload_0
        //   328: getfield 24	com/google/android/finsky/receivers/InstallerTask$6:val$installerData	Lcom/google/android/finsky/appstate/InstallerDataStore$InstallerData;
        //   331: invokevirtual 160	com/google/android/finsky/appstate/InstallerDataStore$InstallerData:getDeliveryData	()Lcom/google/android/finsky/remoting/protos/AndroidAppDelivery$AndroidAppDeliveryData;
        //   334: invokevirtual 166	com/google/android/finsky/remoting/protos/AndroidAppDelivery$AndroidAppDeliveryData:getDownloadSize	()J
        //   337: lstore 17
        //   339: aload 6
        //   341: aload 9
        //   343: aload 15
        //   345: lload 17
        //   347: invokestatic 172	com/google/android/finsky/installer/Gdiff:patch	(Ljava/io/RandomAccessFile;Ljava/io/InputStream;Ljava/io/OutputStream;J)J
        //   350: pop2
        //   351: aload 6
        //   353: invokevirtual 175	java/io/RandomAccessFile:close	()V
        //   356: aload 9
        //   358: invokevirtual 178	java/io/InputStream:close	()V
        //   361: aload 15
        //   363: invokevirtual 181	java/io/OutputStream:close	()V
        //   366: iconst_1
        //   367: ifne +9 -> 376
        //   370: aload 8
        //   372: invokevirtual 154	java/io/File:delete	()Z
        //   375: pop
        //   376: goto -268 -> 108
        //   379: astore 34
        //   381: aload_0
        //   382: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   385: aload_2
        //   386: ldc 183
        //   388: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   391: ldc 185
        //   393: iconst_2
        //   394: anewarray 112	java/lang/Object
        //   397: dup
        //   398: iconst_0
        //   399: aload_2
        //   400: aastore
        //   401: dup
        //   402: iconst_1
        //   403: aload 4
        //   405: aload_0
        //   406: getfield 28	com/google/android/finsky/receivers/InstallerTask$6:val$downloadUriString	Ljava/lang/String;
        //   409: invokevirtual 190	java/lang/String:toString	()Ljava/lang/String;
        //   412: ldc 192
        //   414: ldc 194
        //   416: invokevirtual 198	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   419: invokestatic 204	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
        //   422: invokevirtual 208	android/content/ContentResolver:getType	(Landroid/net/Uri;)Ljava/lang/String;
        //   425: aastore
        //   426: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   429: new 69	java/io/File
        //   432: dup
        //   433: ldc 210
        //   435: invokespecial 83	java/io/File:<init>	(Ljava/lang/String;)V
        //   438: astore 35
        //   440: aload 6
        //   442: invokevirtual 175	java/io/RandomAccessFile:close	()V
        //   445: aload 9
        //   447: invokevirtual 178	java/io/InputStream:close	()V
        //   450: aload 15
        //   452: invokevirtual 181	java/io/OutputStream:close	()V
        //   455: iconst_0
        //   456: ifne +9 -> 465
        //   459: aload 8
        //   461: invokevirtual 154	java/io/File:delete	()Z
        //   464: pop
        //   465: aload 35
        //   467: astore 8
        //   469: goto -361 -> 108
        //   472: astore 29
        //   474: aload_0
        //   475: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   478: aload_2
        //   479: ldc 212
        //   481: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   484: ldc 214
        //   486: iconst_2
        //   487: anewarray 112	java/lang/Object
        //   490: dup
        //   491: iconst_0
        //   492: aload_2
        //   493: aastore
        //   494: dup
        //   495: iconst_1
        //   496: aload 29
        //   498: aastore
        //   499: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   502: aload 6
        //   504: invokevirtual 175	java/io/RandomAccessFile:close	()V
        //   507: aload 9
        //   509: invokevirtual 178	java/io/InputStream:close	()V
        //   512: aload 15
        //   514: invokevirtual 181	java/io/OutputStream:close	()V
        //   517: iconst_0
        //   518: ifne +9 -> 527
        //   521: aload 8
        //   523: invokevirtual 154	java/io/File:delete	()Z
        //   526: pop
        //   527: aconst_null
        //   528: astore 8
        //   530: goto -422 -> 108
        //   533: astore 24
        //   535: aload_0
        //   536: getfield 22	com/google/android/finsky/receivers/InstallerTask$6:this$0	Lcom/google/android/finsky/receivers/InstallerTask;
        //   539: aload_2
        //   540: ldc 216
        //   542: invokestatic 108	com/google/android/finsky/receivers/InstallerTask:access$1100	(Lcom/google/android/finsky/receivers/InstallerTask;Ljava/lang/String;Ljava/lang/String;)V
        //   545: ldc 214
        //   547: iconst_2
        //   548: anewarray 112	java/lang/Object
        //   551: dup
        //   552: iconst_0
        //   553: aload_2
        //   554: aastore
        //   555: dup
        //   556: iconst_1
        //   557: aload 24
        //   559: aastore
        //   560: invokestatic 118	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   563: aload 6
        //   565: invokevirtual 175	java/io/RandomAccessFile:close	()V
        //   568: aload 9
        //   570: invokevirtual 178	java/io/InputStream:close	()V
        //   573: aload 15
        //   575: invokevirtual 181	java/io/OutputStream:close	()V
        //   578: iconst_0
        //   579: ifne +9 -> 588
        //   582: aload 8
        //   584: invokevirtual 154	java/io/File:delete	()Z
        //   587: pop
        //   588: aconst_null
        //   589: astore 8
        //   591: goto -483 -> 108
        //   594: astore 19
        //   596: aload 6
        //   598: invokevirtual 175	java/io/RandomAccessFile:close	()V
        //   601: aload 9
        //   603: invokevirtual 178	java/io/InputStream:close	()V
        //   606: aload 15
        //   608: invokevirtual 181	java/io/OutputStream:close	()V
        //   611: iconst_0
        //   612: ifne +9 -> 621
        //   615: aload 8
        //   617: invokevirtual 154	java/io/File:delete	()Z
        //   620: pop
        //   621: aload 19
        //   623: athrow
        //   624: astore 42
        //   626: goto -270 -> 356
        //   629: astore 43
        //   631: goto -270 -> 361
        //   634: astore 44
        //   636: goto -270 -> 366
        //   639: astore 36
        //   641: goto -196 -> 445
        //   644: astore 37
        //   646: goto -196 -> 450
        //   649: astore 38
        //   651: goto -196 -> 455
        //   654: astore 30
        //   656: goto -149 -> 507
        //   659: astore 31
        //   661: goto -149 -> 512
        //   664: astore 32
        //   666: goto -149 -> 517
        //   669: astore 25
        //   671: goto -103 -> 568
        //   674: astore 26
        //   676: goto -103 -> 573
        //   679: astore 27
        //   681: goto -103 -> 578
        //   684: astore 20
        //   686: goto -85 -> 601
        //   689: astore 21
        //   691: goto -85 -> 606
        //   694: astore 22
        //   696: goto -85 -> 611
        //   699: astore 16
        //   701: goto -390 -> 311
        //   704: iconst_0
        //   705: istore 14
        //   707: goto -500 -> 207
        //
        // Exception table:
        //   from	to	target	type
        //   24	52	111	android/content/pm/PackageManager$NameNotFoundException
        //   24	52	129	java/io/FileNotFoundException
        //   52	65	147	java/io/FileNotFoundException
        //   65	105	309	java/io/IOException
        //   165	263	309	java/io/IOException
        //   339	351	379	com/google/android/finsky/installer/Gdiff$FileFormatException
        //   339	351	472	java/io/IOException
        //   339	351	533	java/lang/Exception
        //   339	351	594	finally
        //   381	440	594	finally
        //   474	502	594	finally
        //   535	563	594	finally
        //   351	356	624	java/io/IOException
        //   356	361	629	java/io/IOException
        //   361	366	634	java/io/IOException
        //   440	445	639	java/io/IOException
        //   445	450	644	java/io/IOException
        //   450	455	649	java/io/IOException
        //   502	507	654	java/io/IOException
        //   507	512	659	java/io/IOException
        //   512	517	664	java/io/IOException
        //   563	568	669	java/io/IOException
        //   568	573	674	java/io/IOException
        //   573	578	679	java/io/IOException
        //   596	601	684	java/io/IOException
        //   601	606	689	java/io/IOException
        //   606	611	694	java/io/IOException
        //   263	303	699	java/io/IOException
      }

      protected void onPostExecute(File paramAnonymousFile)
      {
        if ((paramAnonymousFile != null) && (TextUtils.isEmpty(paramAnonymousFile.getName())))
        {
          InstallerTask.this.setInstallerState(60, localUri);
          InstallerTask.this.advanceState();
        }
        while (true)
        {
          return;
          InstallerTask.this.mDownloadQueue.release(localUri);
          if (paramAnonymousFile == null)
          {
            InstallerTask.this.tryRestartWithoutPatch();
          }
          else
          {
            FinskyApp.get().getAnalytics().logTagAndPackage("install.applyPatch", InstallerTask.this.packageName, null);
            FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
            Object[] arrayOfObject1 = new Object[2];
            arrayOfObject1[0] = "cidi";
            arrayOfObject1[1] = InstallerTask.this.packageName;
            localFinskyEventLog.logTag("install.applyPatch", arrayOfObject1);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = InstallerTask.this.packageName;
            FinskyLog.d("Successfully applied patch to update %s", arrayOfObject2);
            InstallerTask.this.setInstallerState(60, "file://" + paramAnonymousFile.getAbsolutePath());
            InstallerTask.this.advanceState();
          }
        }
      }
    }
    .execute(new Void[0]);
  }

  private void startInstaller(AppStates.AppState paramAppState)
  {
    if (startActivation(paramAppState))
      return;
    InstallerDataStore.InstallerData localInstallerData = paramAppState.installerData;
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = localInstallerData.getDeliveryData();
    if (this.mObbMain != null)
    {
      this.mObbMain.syncStateWithStorage();
      int j = this.mObbMain.getState();
      if ((j != 5) && (j != 3))
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.packageName;
        FinskyLog.e("Can't find main obb file for %s", arrayOfObject3);
        cancel(false);
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 911);
        showDownloadNotification(911, null);
      }
    }
    if (this.mObbPatch != null)
    {
      this.mObbPatch.syncStateWithStorage();
      int i = this.mObbPatch.getState();
      if ((i != 5) && (i != 3))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.packageName;
        FinskyLog.e("Can't find patch obb file for %s", arrayOfObject2);
        cancel(false);
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 912);
        showDownloadNotification(912, null);
      }
    }
    String str1 = localInstallerData.getTitle();
    long l = localAndroidAppDeliveryData.getDownloadSize();
    String str2 = localAndroidAppDeliveryData.getSignature();
    Uri localUri = Uri.parse(localInstallerData.getDownloadUri());
    boolean bool;
    label224: String str3;
    if (paramAppState.packageManagerState != null)
    {
      bool = true;
      this.mIsUpdate = bool;
      if (!localAndroidAppDeliveryData.hasEncryptionParams())
        break label374;
      str3 = "install.installerStart.encrypted";
      label242: FinskyApp.get().getAnalytics().logTagAndPackage(str3, this.packageName, null);
      if (!localAndroidAppDeliveryData.hasEncryptionParams())
        break label382;
    }
    label374: label382: for (String str4 = "install.installerStart.encrypted"; ; str4 = "install.installerStart")
    {
      FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = "cidi";
      arrayOfObject1[1] = this.packageName;
      localFinskyEventLog.logTag(str4, arrayOfObject1);
      PackageManagerHelper.installPackage(localUri, str1, l, str2, this.mShowInstallNotifications, getInstallerListener(localUri), this.mIsUpdate, localAndroidAppDeliveryData.getForwardLocked(), this.packageName, localAndroidAppDeliveryData.getEncryptionParams());
      setInstallerState(60, localInstallerData.getDownloadUri());
      notifyListeners(InstallerListener.InstallerPackageEvent.INSTALLING, 0);
      break;
      bool = false;
      break label224;
      str3 = "install.installerStart";
      break label242;
    }
  }

  private void startNextDownload(InstallerDataStore.InstallerData paramInstallerData)
  {
    InternalDownload localInternalDownload = null;
    int i = paramInstallerData.getInstallerState();
    if (i < 20)
      i = 20;
    int j = -1;
    switch (i)
    {
    default:
      if (j >= 0)
      {
        this.mDownloadQueue.add(localInternalDownload);
        FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadQueued", localInternalDownload.toString(), null);
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = "downloadState";
        arrayOfObject2[1] = localInternalDownload.toString();
        localFinskyEventLog.logTag("install.downloadQueued", arrayOfObject2);
        setInstallerState(j, (String)null);
      }
      break;
    case 20:
    case 30:
    case 40:
    }
    while (true)
    {
      return;
      if (this.mObbMain != null)
      {
        this.mObbMain.syncStateWithStorage();
        if (this.mObbMain.getState() == 4)
        {
          this.mObbMain.getTempFile().delete();
          localInternalDownload = generateObbDownload(paramInstallerData, this.mObbMain);
          j = 20;
          break;
        }
      }
      if (this.mObbPatch != null)
      {
        this.mObbPatch.syncStateWithStorage();
        if (this.mObbPatch.getState() == 4)
        {
          this.mObbPatch.getTempFile().delete();
          localInternalDownload = generateObbDownload(paramInstallerData, this.mObbPatch);
          j = 30;
          break;
        }
      }
      localInternalDownload = generateDownload(paramInstallerData);
      j = 40;
      break;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = this.packageName;
      arrayOfObject1[1] = Integer.valueOf(paramInstallerData.getInstallerState());
      arrayOfObject1[2] = Integer.valueOf(j);
      FinskyLog.e("Unexpected download start states for %s: %d %d", arrayOfObject1);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 902);
      showDownloadNotification(902, null);
    }
  }

  private boolean tryRestartWithoutPatch()
  {
    InstallerDataStore localInstallerDataStore = this.mInstallerDataStore;
    int i = localInstallerDataStore.get(this.packageName).getFlags();
    if ((i & 0x4) != 0);
    for (boolean bool = true; ; bool = false)
    {
      if (bool)
      {
        int j = 0xFFFFFFFB & (i | 0x8);
        localInstallerDataStore.setFlags(this.packageName, j);
        setInstallerState(40, (String)null);
        advanceState();
      }
      return bool;
    }
  }

  private int volleyErrorToInstallerError(VolleyError paramVolleyError)
  {
    int i;
    if ((paramVolleyError instanceof AuthFailureError))
      i = 920;
    while (true)
    {
      return i;
      if ((paramVolleyError instanceof DisplayMessageError))
        i = 921;
      else if ((paramVolleyError instanceof DfeServerError))
        i = 922;
      else if ((paramVolleyError instanceof NetworkError))
        i = 923;
      else if ((paramVolleyError instanceof NoConnectionError))
        i = 924;
      else if ((paramVolleyError instanceof ParseError))
        i = 925;
      else if ((paramVolleyError instanceof ServerError))
        i = 926;
      else if ((paramVolleyError instanceof TimeoutError))
        i = 927;
      else
        i = 928;
    }
  }

  public void cancel(boolean paramBoolean)
  {
    AppStates.AppState localAppState = this.mAppStates.getApp(this.packageName);
    if ((localAppState != null) && (localAppState.installerData != null) && (localAppState.installerData.getInstallerState() >= 60))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.packageName;
      FinskyLog.d("Cannot cancel installing %s - too late", arrayOfObject);
    }
    while (true)
    {
      return;
      cancelCleanup(localAppState);
      if (paramBoolean)
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_CANCELLED, 0);
    }
  }

  public Installer.InstallerProgressReport getProgress()
  {
    Installer.InstallerProgressReport localInstallerProgressReport;
    switch (7.$SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[getState().ordinal()])
    {
    default:
      long l1 = this.mApkCompleted;
      long l2 = this.mApkSize;
      long l3 = l1 + this.mObbMainCompleted;
      long l4 = l2 + this.mObbMainSize;
      long l5 = l3 + this.mObbPatchCompleted;
      long l6 = l4 + this.mObbPatchSize;
      localInstallerProgressReport = new Installer.InstallerProgressReport(Installer.InstallerState.DOWNLOADING, l5, l6, this.mDownloadStatus);
    case 1:
    case 2:
    }
    while (true)
    {
      return localInstallerProgressReport;
      localInstallerProgressReport = PROGRESS_NOT_TRACKED;
      continue;
      localInstallerProgressReport = PROGRESS_INSTALLING;
    }
  }

  public Installer.InstallerState getState()
  {
    AppStates.AppState localAppState = this.mAppStates.getApp(this.packageName);
    Installer.InstallerState localInstallerState;
    if ((localAppState != null) && (localAppState.installerData != null))
      switch (localAppState.installerData.getInstallerState())
      {
      default:
        localInstallerState = Installer.InstallerState.DOWNLOADING;
      case 0:
      case 70:
      case 80:
      case 55:
      case 60:
      }
    while (true)
    {
      return localInstallerState;
      localInstallerState = Installer.InstallerState.NOT_TRACKED;
      continue;
      localInstallerState = Installer.InstallerState.INSTALLING;
      continue;
      localInstallerState = Installer.InstallerState.NOT_TRACKED;
    }
  }

  public void onComplete(Download paramDownload)
  {
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    InstallerDataStore.InstallerData localInstallerData = this.mAppStates.getApp(this.packageName).installerData;
    int i = -1;
    int j = 904;
    boolean bool1 = localInternalDownload.isObb();
    boolean bool2;
    if ((bool1) && (!localInternalDownload.getObb().isPatch()))
    {
      bool2 = true;
      switch (localInstallerData.getInstallerState())
      {
      default:
        label96: if (i >= 0)
        {
          setInstallerState(i, paramDownload.getContentUri());
          advanceState();
        }
        break;
      case 25:
      case 35:
      case 45:
      }
    }
    while (true)
    {
      return;
      bool2 = false;
      break;
      if ((!bool1) || (!bool2))
        break label96;
      if (this.mObbMain.finalizeTempFile())
      {
        i = 30;
        break label96;
      }
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = this.packageName;
      FinskyLog.e("Can't finalize main obb file for %s", arrayOfObject3);
      j = 911;
      break label96;
      if ((!bool1) || (bool2))
        break label96;
      if (this.mObbPatch.finalizeTempFile())
      {
        i = 40;
        break label96;
      }
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.packageName;
      FinskyLog.e("Can't finalize patch obb file for %s", arrayOfObject2);
      j = 912;
      break label96;
      if (bool1)
        break label96;
      i = 50;
      break label96;
      Object[] arrayOfObject1 = new Object[5];
      arrayOfObject1[0] = this.packageName;
      arrayOfObject1[1] = Integer.valueOf(localInstallerData.getInstallerState());
      arrayOfObject1[2] = Integer.valueOf(i);
      arrayOfObject1[3] = Boolean.valueOf(bool1);
      arrayOfObject1[4] = Boolean.valueOf(bool2);
      FinskyLog.e("Unexpected download completion states for %s: %d %d %b %b", arrayOfObject1);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, j);
      showDownloadNotification(j, null);
    }
  }

  public void onError(Download paramDownload, int paramInt)
  {
    if ((paramInt == 420) || ((paramInt >= 500) && (paramInt <= 599)));
    for (int i = 1; (i != 0) && (tryRestartWithoutPatch()); i = 0)
      return;
    cancel(false);
    if (paramInt == 498)
      if (this.mShowDownloadNotifications)
        this.mNotifier.showInternalStorageFull(this.mTitle, this.packageName);
    while (true)
    {
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, paramInt);
      break;
      showDownloadNotification(paramInt, null);
    }
  }

  public void onProgress(Download paramDownload, DownloadProgress paramDownloadProgress)
  {
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    if (localInternalDownload.isObb())
      if (localInternalDownload.getObb().isPatch())
        this.mObbPatchCompleted = paramDownloadProgress.bytesCompleted;
    while (true)
    {
      this.mDownloadStatus = paramDownloadProgress.statusCode;
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOADING, 0);
      return;
      this.mObbMainCompleted = paramDownloadProgress.bytesCompleted;
      continue;
      this.mApkCompleted = paramDownloadProgress.bytesCompleted;
      if ((paramDownloadProgress.bytesCompleted > 0L) && (this.mAppStates.getInstallerDataStore().get(this.packageName).getFirstDownloadMs() == 0L))
        this.mInstallerDataStore.setFirstDownloadMs(this.packageName, System.currentTimeMillis());
    }
  }

  public void onStart(Download paramDownload)
  {
    String str = ((InternalDownload)paramDownload).getPackageName();
    InstallerDataStore.InstallerData localInstallerData = this.mAppStates.getApp(str).installerData;
    int i = -1;
    switch (localInstallerData.getInstallerState())
    {
    default:
      if (i >= 0)
      {
        setInstallerState(i, paramDownload.getContentUri());
        notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOADING, 0);
      }
      break;
    case 20:
    case 25:
    case 30:
    case 35:
    case 40:
    case 45:
    }
    while (true)
    {
      return;
      i = 25;
      break;
      i = 35;
      break;
      i = 45;
      break;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str;
      arrayOfObject[1] = Integer.valueOf(localInstallerData.getInstallerState());
      arrayOfObject[2] = Integer.valueOf(i);
      FinskyLog.e("Unexpected download start states for %s: %d %d", arrayOfObject);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 903);
      showDownloadNotification(903, null);
    }
  }

  public boolean recover(Uri paramUri, int paramInt)
  {
    boolean bool = false;
    AppStates.AppState localAppState = this.mAppStates.getApp(this.packageName);
    InstallerDataStore.InstallerData localInstallerData = localAppState.installerData;
    if ((localInstallerData == null) || (localInstallerData.getDeliveryData() == null))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = this.packageName;
      FinskyLog.d("Recovery of %s skipped because incomplete installerdata", arrayOfObject1);
    }
    while (true)
    {
      label57: return bool;
      getUiFields(localAppState);
      processDeliveryData(localAppState.installerData, false);
      if (localAppState.packageManagerState != null);
      int j;
      int k;
      for (int i = localAppState.packageManagerState.installedVersion; ; i = -1)
      {
        j = localInstallerData.getDesiredVersion();
        k = localInstallerData.getInstallerState();
        this.mRecoveredIntoState = k;
        switch (k)
        {
        default:
          Object[] arrayOfObject3 = new Object[2];
          arrayOfObject3[0] = this.packageName;
          arrayOfObject3[1] = Integer.valueOf(k);
          FinskyLog.d("Recovery of %s skipped because state= %d", arrayOfObject3);
          break label57;
        case 25:
        case 35:
        case 45:
        case 55:
        case 50:
        case 60:
        }
      }
      bool = recoverObb(localAppState, paramUri, paramInt, j, i, false);
      continue;
      bool = recoverObb(localAppState, paramUri, paramInt, j, i, true);
      continue;
      bool = recoverApk(localAppState, paramUri, paramInt, j, i);
      continue;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = this.packageName;
      arrayOfObject2[1] = Integer.valueOf(k);
      FinskyLog.d("Recovery of %s skipped because state= %d", arrayOfObject2);
      cancel(false);
      continue;
      bool = recoverInstalling(localAppState, paramUri, paramInt, j, i);
    }
  }

  void releaseInstalledUri(Uri paramUri)
  {
    if ("file".equalsIgnoreCase(paramUri.getScheme()))
      new File(paramUri.getPath()).delete();
    while (true)
    {
      return;
      this.mDownloadQueue.release(paramUri);
    }
  }

  public void start()
  {
    AppStates.AppState localAppState = this.mAppStates.getApp(this.packageName);
    if ((localAppState == null) || (localAppState.installerData == null))
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = this.packageName;
      FinskyLog.wtf("Unexpected missing installer data for %s", arrayOfObject1);
      cancel(true);
    }
    InstallerDataStore.InstallerData localInstallerData;
    int i;
    while (true)
    {
      return;
      localInstallerData = localAppState.installerData;
      i = localInstallerData.getInstallerState();
      getUiFields(localAppState);
      if ((i > 0) && (localInstallerData.getDeliveryData() != null))
        processDeliveryData(localInstallerData, false);
      j = -1;
      this.mRecoveredIntoState = i;
      switch (i)
      {
      default:
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = Integer.valueOf(i);
        arrayOfObject4[1] = this.packageName;
        FinskyLog.wtf("Unknown state %d for %s", arrayOfObject4);
        cancel(true);
      case 10:
      case 0:
      case 80:
      case 20:
      case 30:
      case 40:
      case 25:
      case 35:
      case 45:
      case 50:
      case 55:
      case 60:
      case 70:
      }
    }
    int j = 0;
    while (true)
    {
      if ((j != -1) && (j != i))
        setInstallerState(j, localInstallerData.getDownloadUri());
      advanceState();
      break;
      j = 10;
      continue;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = this.packageName;
      arrayOfObject3[1] = Integer.valueOf(i);
      FinskyLog.w("Cannot restart %s from downloading state %d", arrayOfObject3);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 905);
      showDownloadNotification(905, null);
      break;
      j = 60;
      continue;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.packageName;
      FinskyLog.w("Restarting while applying patch for %s", arrayOfObject2);
      cancel(false);
      notifyListeners(InstallerListener.InstallerPackageEvent.DOWNLOAD_ERROR, 907);
      showDownloadNotification(907, null);
      break;
      j = 70;
    }
  }

  public String toString()
  {
    return this.packageName;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.InstallerTask
 * JD-Core Version:    0.6.2
 */
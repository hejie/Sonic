package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryAppEntry;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.Users;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AppActionAnalyzer
{
  public int installedVersion = -1;
  public boolean isActiveDeviceAdmin = false;
  public boolean isContinueLaunch = false;
  public boolean isDisabled = false;
  public boolean isDisabledByUser = false;
  public boolean isInstalled = false;
  public boolean isInstalledOwnedPackage = false;
  public boolean isInstalledSystemApp = false;
  public boolean isLaunchable = false;
  public boolean isRefundable = false;
  public boolean isUninstallable = false;
  public boolean isUpdatedSystemApp = false;
  public String refundAccount = null;

  public AppActionAnalyzer(String paramString, AppStates paramAppStates, Libraries paramLibraries)
  {
    AppStates.AppState localAppState = paramAppStates.getApp(paramString);
    boolean bool4;
    boolean bool6;
    label221: int i;
    label272: boolean bool2;
    label288: label299: boolean bool3;
    if ((localAppState != null) && (localAppState.packageManagerState != null))
    {
      this.isInstalled = bool1;
      PackageStateRepository.PackageState localPackageState = localAppState.packageManagerState;
      this.installedVersion = localPackageState.installedVersion;
      this.isInstalledSystemApp = localPackageState.isSystemApp;
      this.isUpdatedSystemApp = localPackageState.isUpdatedSystemApp;
      this.isActiveDeviceAdmin = localPackageState.isActiveDeviceAdmin;
      if ((!this.isActiveDeviceAdmin) && ((!this.isInstalledSystemApp) || (this.isUpdatedSystemApp)))
      {
        bool4 = bool1;
        this.isUninstallable = bool4;
        boolean bool5 = paramAppStates.getPackageStateRepository().canLaunch(paramString);
        this.isDisabled = localPackageState.isDisabled;
        this.isDisabledByUser = localPackageState.isDisabledByUser;
        if ((!bool5) || (this.isDisabled))
          break label411;
        bool6 = bool1;
        this.isLaunchable = bool6;
      }
    }
    else
    {
      String[] arrayOfString = LibraryAppEntry.ANY_CERTIFICATE_HASHES;
      if (this.isInstalled)
        arrayOfString = localAppState.packageManagerState.certificateHashes;
      List localList = paramLibraries.getAppEntries(paramString, arrayOfString);
      if (localList.isEmpty())
        break label417;
      i = bool1;
      if ((!this.isInstalled) || (i == 0))
        break label423;
      bool2 = bool1;
      this.isInstalledOwnedPackage = bool2;
      if (localAppState != null)
        break label429;
      this.refundAccount = getRefundAccount(localInstallerData, localList);
      if (this.refundAccount == null)
        break label439;
      bool3 = bool1;
      label321: this.isRefundable = bool3;
      if ((this.isInstalled) && (i == 0) && (!paramLibraries.getAppOwners(paramString).isEmpty()))
      {
        Object[] arrayOfObject = new Object[bool1];
        arrayOfObject[0] = paramString;
        FinskyLog.d("%s is installed but certificate mistmatch", arrayOfObject);
      }
      if ((localAppState != null) && (localAppState.installerData != null))
        if (TextUtils.isEmpty(localAppState.installerData.getContinueUrl()))
          break label445;
    }
    while (true)
    {
      this.isContinueLaunch = bool1;
      return;
      bool4 = false;
      break;
      label411: bool6 = false;
      break label221;
      label417: i = 0;
      break label272;
      label423: bool2 = false;
      break label288;
      label429: localInstallerData = localAppState.installerData;
      break label299;
      label439: bool3 = false;
      break label321;
      label445: bool1 = false;
    }
  }

  public static boolean canRemoveFromLibrary(Document paramDocument)
  {
    boolean bool = false;
    if (paramDocument.getDocumentType() != 1)
      FinskyLog.wtf("Method invalid for non-ANDROID_APP docs.", new Object[0]);
    while (true)
    {
      return bool;
      Libraries localLibraries = FinskyApp.get().getLibraries();
      AppActionAnalyzer localAppActionAnalyzer = new AppActionAnalyzer(paramDocument.getBackendDocId(), FinskyApp.get().getAppStates(), localLibraries);
      if ((!localAppActionAnalyzer.isInstalled) && (!localAppActionAnalyzer.isRefundable) && (!DocUtils.hasAutoRenewingSubscriptions(localLibraries, paramDocument)))
        bool = true;
    }
  }

  public static Account getInstallAccount(String paramString, Account paramAccount, AppStates paramAppStates, Libraries paramLibraries)
  {
    List localList = paramLibraries.getAppOwners(paramString);
    if (localList.contains(paramAccount));
    while (true)
    {
      return paramAccount;
      InstallerDataStore.InstallerData localInstallerData = paramAppStates.getInstallerDataStore().get(paramString);
      if (localInstallerData != null)
      {
        Iterator localIterator = localList.iterator();
        while (true)
          if (localIterator.hasNext())
          {
            Account localAccount = (Account)localIterator.next();
            if (localAccount.name.equals(localInstallerData.getAccountName()))
            {
              paramAccount = localAccount;
              break;
            }
          }
      }
      if (!localList.isEmpty())
        paramAccount = (Account)localList.get(0);
    }
  }

  private static String getRefundAccount(InstallerDataStore.InstallerData paramInstallerData, List<LibraryAppEntry> paramList)
  {
    return internalGetRefundAccount(paramInstallerData, paramList, System.currentTimeMillis());
  }

  static String internalGetRefundAccount(InstallerDataStore.InstallerData paramInstallerData, List<LibraryAppEntry> paramList, long paramLong)
  {
    long l1 = 0L;
    if (paramInstallerData != null)
      l1 = paramInstallerData.getFirstDownloadMs();
    Iterator localIterator = paramList.iterator();
    LibraryAppEntry localLibraryAppEntry;
    long l2;
    do
    {
      if (!localIterator.hasNext())
        break;
      localLibraryAppEntry = (LibraryAppEntry)localIterator.next();
      l2 = localLibraryAppEntry.refundPreDeliveryEndtimeMs;
      if (l1 != 0L)
        l2 = Math.min(l2, l1 + localLibraryAppEntry.refundPostDeliveryWindowMs);
    }
    while (l2 < paramLong);
    for (String str = localLibraryAppEntry.accountName; ; str = null)
      return str;
  }

  public static boolean isMultiUserCertificateConflict(Document paramDocument)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (!FinskyApp.get().getUsers().supportsMultiUser());
    while (true)
    {
      return bool2;
      if (FinskyApp.get().getPackageInfoRepository().get(paramDocument.getDocId()) == null)
      {
        PackageManager localPackageManager = FinskyApp.get().getPackageManager();
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramDocument.getDocId(), 8256);
          int i = localPackageInfo.signatures.length;
          if (i != paramDocument.getAppDetails().getCertificateHashCount())
          {
            bool2 = bool1;
          }
          else
          {
            HashSet localHashSet1 = Sets.newHashSet();
            HashSet localHashSet2 = Sets.newHashSet();
            for (int j = 0; j < i; j++)
            {
              localHashSet1.add(Sha1Util.secureHash(localPackageInfo.signatures[j].toByteArray()));
              localHashSet2.add(paramDocument.getAppDetails().getCertificateHash(0));
            }
            boolean bool3 = localHashSet1.equals(localHashSet2);
            if (!bool3);
            while (true)
            {
              bool2 = bool1;
              break;
              bool1 = false;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AppActionAnalyzer
 * JD-Core Version:    0.6.2
 */
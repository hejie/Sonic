package com.google.android.finsky.installer;

import android.content.ContentResolver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.VendingPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class InstallPolicies
{
  private final AppStates mAppStates;
  private final ConnectivityManager mConnectivityManager;
  private final Libraries mLibraries;
  private long mMaxBytesOverMobile;
  private long mMaxBytesOverMobileRecommended;
  private final PackageManager mPackageManager;

  public InstallPolicies(ContentResolver paramContentResolver, PackageManager paramPackageManager, AppStates paramAppStates, Libraries paramLibraries)
  {
    setMobileDownloadThresholds(paramContentResolver);
    this.mPackageManager = paramPackageManager;
    this.mConnectivityManager = ((ConnectivityManager)FinskyApp.get().getSystemService("connectivity"));
    this.mAppStates = paramAppStates;
    this.mLibraries = paramLibraries;
  }

  private boolean containsDangerousNewPermissions(String paramString, List<String> paramList, PackageManager paramPackageManager)
    throws PackageManager.NameNotFoundException
  {
    int i = 1;
    ArrayList localArrayList = Lists.newArrayList();
    if (paramList != null)
    {
      Iterator localIterator2 = paramList.iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        try
        {
          localArrayList.add(paramPackageManager.getPermissionInfo(str, 0));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
        }
      }
    }
    HashSet localHashSet = new HashSet();
    PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(paramString, 4096);
    if ((localPackageInfo != null) && (localPackageInfo.requestedPermissions != null))
    {
      String[] arrayOfString = localPackageInfo.requestedPermissions;
      int n = arrayOfString.length;
      for (int i1 = 0; i1 < n; i1++)
        localHashSet.add(arrayOfString[i1]);
    }
    Iterator localIterator1 = localArrayList.iterator();
    if (localIterator1.hasNext())
    {
      PermissionInfo localPermissionInfo = (PermissionInfo)localIterator1.next();
      if (localPermissionInfo.protectionLevel == i)
      {
        int k = i;
        label183: if ((k == 0) || (localHashSet.contains(localPermissionInfo.name)))
          break label210;
      }
    }
    while (true)
    {
      return i;
      int m = 0;
      break label183;
      label210: break;
      int j = 0;
    }
  }

  private void setMobileDownloadThresholds(ContentResolver paramContentResolver)
  {
    this.mMaxBytesOverMobile = ((Long)G.downloadBytesOverMobileMaximum.get()).longValue();
    this.mMaxBytesOverMobileRecommended = ((Long)G.downloadBytesOverMobileRecommended.get()).longValue();
    try
    {
      long l2 = Settings.Secure.getLong(paramContentResolver, "download_manager_max_bytes_over_mobile");
      if ((l2 > 0L) && (l2 < this.mMaxBytesOverMobile))
        this.mMaxBytesOverMobile = l2;
      try
      {
        label63: long l1 = Settings.Secure.getLong(paramContentResolver, "download_manager_recommended_max_bytes_over_mobile");
        if ((l1 > 0L) && (l1 < this.mMaxBytesOverMobileRecommended))
          this.mMaxBytesOverMobileRecommended = l1;
        label94: this.mMaxBytesOverMobileRecommended = Math.min(this.mMaxBytesOverMobileRecommended, this.mMaxBytesOverMobile);
        return;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        break label94;
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException1)
    {
      break label63;
    }
  }

  public boolean canUpdateApp(PackageStateRepository.PackageState paramPackageState, Document paramDocument)
  {
    boolean bool = false;
    if (paramPackageState == null);
    while (true)
    {
      return bool;
      if (!this.mLibraries.isLoaded())
        FinskyLog.wtf("Library not loaded.", new Object[0]);
      int i = paramPackageState.installedVersion;
      int j = paramDocument.getAppDetails().getVersionCode();
      if (!paramPackageState.isDisabled)
      {
        String str = paramPackageState.packageName;
        if (j > i)
          if (!LibraryUtils.isAvailable(paramDocument, null, this.mLibraries))
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = str;
            arrayOfObject[1] = Integer.valueOf(paramDocument.getAvailabilityRestriction());
            FinskyLog.d("Cannot update unavailable app: pkg=%s,restriction=%d", arrayOfObject);
          }
          else
          {
            bool = true;
          }
      }
    }
  }

  public List<Document> getApplicationsEligibleForAutoUpdate(List<Document> paramList, boolean paramBoolean)
  {
    if (!this.mLibraries.isLoaded())
      FinskyLog.wtf("Library not loaded.", new Object[0]);
    long l1;
    ArrayList localArrayList;
    Iterator localIterator;
    if (isMobileNetwork())
    {
      l1 = getMaxBytesOverMobileRecommended();
      localArrayList = Lists.newArrayList();
      localIterator = paramList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        break label301;
      Document localDocument = (Document)localIterator.next();
      DocDetails.AppDetails localAppDetails = localDocument.getAppDetails();
      String str = localAppDetails.getPackageName();
      AppStates.AppState localAppState = this.mAppStates.getApp(str);
      if ((localAppState == null) || (localAppState.packageManagerState == null))
      {
        FinskyLog.w("Server thinks we have an asset that we don't have : %s", new Object[] { str });
        continue;
        l1 = 9223372036854775807L;
        break;
      }
      if (localAppDetails.getVersionCode() > localAppState.packageManagerState.installedVersion)
      {
        InstallerDataStore.AutoUpdateState localAutoUpdateState;
        boolean bool2;
        if (paramBoolean)
        {
          localAutoUpdateState = InstallerDataStore.AutoUpdateState.DEFAULT;
          if (localAppState.installerData != null)
            localAutoUpdateState = localAppState.installerData.getAutoUpdate();
          if (localAutoUpdateState == InstallerDataStore.AutoUpdateState.DEFAULT)
          {
            bool2 = ((Boolean)VendingPreferences.AUTO_UPDATE_BY_DEFAULT.get()).booleanValue();
            if (!bool2)
              continue;
          }
        }
        else
        {
          long l2;
          if (localAppDetails.hasInstallationSize())
          {
            l2 = localAppDetails.getInstallationSize();
            if (l2 >= l1);
          }
          else
          {
            try
            {
              boolean bool1 = containsDangerousNewPermissions(str, localAppDetails.getPermissionList(), this.mPackageManager);
              if (!bool1)
              {
                localArrayList.add(localDocument);
                continue;
                if (localAutoUpdateState == InstallerDataStore.AutoUpdateState.ENABLED);
                for (bool2 = true; ; bool2 = false)
                  break;
                l2 = 0L;
              }
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              FinskyLog.w("Asset %s marked installed but not in pkg mgr", new Object[] { str });
            }
          }
        }
      }
    }
    label301: return localArrayList;
  }

  public List<Document> getApplicationsEligibleForNotification(List<Document> paramList)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      DocDetails.AppDetails localAppDetails = localDocument.getAppDetails();
      InstallerDataStore.InstallerData localInstallerData = this.mAppStates.getInstallerDataStore().get(localAppDetails.getPackageName());
      if ((this.mAppStates.getApp(localAppDetails.getPackageName()).installerData == null) || (localAppDetails.getVersionCode() > localInstallerData.getLastNotifiedVersion()))
        localArrayList.add(localDocument);
    }
    return localArrayList;
  }

  public List<Document> getApplicationsWithUpdates(List<Document> paramList)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      String str = localDocument.getAppDetails().getPackageName();
      if (canUpdateApp(this.mAppStates.getPackageStateRepository().get(str), localDocument))
        localArrayList.add(localDocument);
    }
    return localArrayList;
  }

  public long getMaxBytesOverMobile()
  {
    return this.mMaxBytesOverMobile;
  }

  public long getMaxBytesOverMobileRecommended()
  {
    return this.mMaxBytesOverMobileRecommended;
  }

  public boolean hasMobileNetwork()
  {
    boolean bool = false;
    if (this.mConnectivityManager.getNetworkInfo(0) != null)
      bool = true;
    return bool;
  }

  public boolean hasNetwork()
  {
    NetworkInfo localNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isMobileNetwork()
  {
    int i = 1;
    NetworkInfo localNetworkInfo = this.mConnectivityManager.getNetworkInfo(i);
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()));
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  public boolean isWifiNetwork()
  {
    int i = 1;
    NetworkInfo localNetworkInfo = this.mConnectivityManager.getNetworkInfo(i);
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
    while (true)
    {
      return i;
      int j = 0;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.installer.InstallPolicies
 * JD-Core Version:    0.6.2
 */
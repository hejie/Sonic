package com.google.android.finsky.appstate;

import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryAppEntry;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AppStates
{
  private final PackageStateRepository mPackageManager;
  private final WriteThroughInstallerDataStore mStateStore;

  public AppStates(WriteThroughInstallerDataStore paramWriteThroughInstallerDataStore, PackageStateRepository paramPackageStateRepository)
  {
    this.mStateStore = paramWriteThroughInstallerDataStore;
    this.mPackageManager = paramPackageStateRepository;
  }

  public void blockingLoad()
  {
    this.mStateStore.load();
  }

  public Collection<AppState> getAllBlocking()
  {
    HashMap localHashMap = Maps.newHashMap();
    ArrayList localArrayList = Lists.newArrayList(localHashMap.size());
    Iterator localIterator1 = this.mPackageManager.getAllBlocking().iterator();
    while (localIterator1.hasNext())
    {
      PackageStateRepository.PackageState localPackageState3 = (PackageStateRepository.PackageState)localIterator1.next();
      localHashMap.put(localPackageState3.packageName, localPackageState3);
    }
    Iterator localIterator2 = this.mStateStore.getAll().iterator();
    while (localIterator2.hasNext())
    {
      InstallerDataStore.InstallerData localInstallerData = (InstallerDataStore.InstallerData)localIterator2.next();
      PackageStateRepository.PackageState localPackageState2 = (PackageStateRepository.PackageState)localHashMap.remove(localInstallerData.getPackageName());
      localArrayList.add(new AppState(localInstallerData.getPackageName(), localPackageState2, localInstallerData));
    }
    Iterator localIterator3 = localHashMap.values().iterator();
    while (localIterator3.hasNext())
    {
      PackageStateRepository.PackageState localPackageState1 = (PackageStateRepository.PackageState)localIterator3.next();
      localArrayList.add(new AppState(localPackageState1.packageName, localPackageState1, null));
    }
    return localArrayList;
  }

  public AppState getApp(String paramString)
  {
    InstallerDataStore.InstallerData localInstallerData = this.mStateStore.get(paramString);
    PackageStateRepository.PackageState localPackageState = this.mPackageManager.get(paramString);
    if ((localInstallerData != null) || (localPackageState != null));
    for (AppState localAppState = new AppState(paramString, localPackageState, localInstallerData); ; localAppState = null)
      return localAppState;
  }

  public List<AppState> getAppsToInstall()
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = this.mStateStore.getAll().iterator();
    while (localIterator.hasNext())
    {
      InstallerDataStore.InstallerData localInstallerData = (InstallerDataStore.InstallerData)localIterator.next();
      if (localInstallerData.getDesiredVersion() != -1)
      {
        PackageStateRepository.PackageState localPackageState = this.mPackageManager.get(localInstallerData.getPackageName());
        if ((localPackageState == null) || (localInstallerData.getDesiredVersion() > localPackageState.installedVersion))
          localArrayList.add(new AppState(localInstallerData.getPackageName(), localPackageState, localInstallerData));
      }
    }
    return localArrayList;
  }

  public Collection<AppState> getInstalledAndOwnedBlocking(Libraries paramLibraries)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = getAllBlocking().iterator();
    while (localIterator.hasNext())
    {
      AppState localAppState = (AppState)localIterator.next();
      if ((localAppState.packageManagerState != null) && (!paramLibraries.getAppEntries(localAppState.packageName, localAppState.packageManagerState.certificateHashes).isEmpty()))
        localArrayList.add(localAppState);
    }
    return localArrayList;
  }

  public InstallerDataStore getInstallerDataStore()
  {
    return this.mStateStore;
  }

  public List<AppState> getOwnedBlocking(Libraries paramLibraries)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = getAllBlocking().iterator();
    label90: 
    while (localIterator.hasNext())
    {
      AppState localAppState = (AppState)localIterator.next();
      PackageStateRepository.PackageState localPackageState = localAppState.packageManagerState;
      if (localPackageState == null);
      for (String[] arrayOfString = LibraryAppEntry.ANY_CERTIFICATE_HASHES; ; arrayOfString = localPackageState.certificateHashes)
      {
        if (paramLibraries.getAppEntries(localAppState.packageName, arrayOfString).isEmpty())
          break label90;
        localArrayList.add(localAppState);
        break;
      }
    }
    return localArrayList;
  }

  public PackageStateRepository getPackageStateRepository()
  {
    return this.mPackageManager;
  }

  public boolean isLoaded()
  {
    return this.mStateStore.isLoaded();
  }

  public boolean load(Runnable paramRunnable)
  {
    return this.mStateStore.load(paramRunnable);
  }

  public static class AppState
  {
    public final InstallerDataStore.InstallerData installerData;
    public final PackageStateRepository.PackageState packageManagerState;
    public final String packageName;

    public AppState(String paramString, PackageStateRepository.PackageState paramPackageState, InstallerDataStore.InstallerData paramInstallerData)
    {
      this.packageName = paramString;
      this.packageManagerState = paramPackageState;
      this.installerData = paramInstallerData;
    }

    public String toString()
    {
      int i = -1;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.packageName;
      if (this.packageManagerState != null);
      for (int j = this.packageManagerState.installedVersion; ; j = i)
      {
        arrayOfObject[1] = Integer.valueOf(j);
        if (this.installerData != null)
          i = this.installerData.getDesiredVersion();
        arrayOfObject[2] = Integer.valueOf(i);
        return String.format("{package=%s installed=%d desired=%d}", arrayOfObject);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.AppStates
 * JD-Core Version:    0.6.2
 */
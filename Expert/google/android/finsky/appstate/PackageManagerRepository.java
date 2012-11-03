package com.google.android.finsky.appstate;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackageManagerRepository
  implements PackageStateRepository, PackageMonitorReceiver.PackageStatusListener
{
  private static final PackageStateRepository.PackageState NOT_INSTALLED_MARKER = new PackageStateRepository.PackageState(null, null, false, false, false, false, -1, false);
  private final DevicePolicyManager mDevicePolicyManager;
  private final PackageManager mPackageManager;
  private final Map<String, PackageStateRepository.PackageState> mPackageStates = Maps.newHashMap();

  public PackageManagerRepository(PackageManager paramPackageManager, PackageMonitorReceiver paramPackageMonitorReceiver, DevicePolicyManager paramDevicePolicyManager)
  {
    this.mPackageManager = paramPackageManager;
    this.mDevicePolicyManager = paramDevicePolicyManager;
    if (paramPackageMonitorReceiver != null)
      paramPackageMonitorReceiver.attach(this);
  }

  private static String[] computeCertificateHashes(PackageInfo paramPackageInfo)
  {
    int i = paramPackageInfo.signatures.length;
    String[] arrayOfString = new String[i];
    for (int j = 0; j < i; j++)
      arrayOfString[j] = Sha1Util.secureHash(paramPackageInfo.signatures[j].toByteArray());
    return arrayOfString;
  }

  private PackageStateRepository.PackageState createPackageState(PackageInfo paramPackageInfo)
  {
    try
    {
      int i = paramPackageInfo.versionCode;
      boolean bool1;
      boolean bool2;
      if ((0x1 & paramPackageInfo.applicationInfo.flags) != 0)
      {
        bool1 = true;
        if ((0x80 & paramPackageInfo.applicationInfo.flags) == 0)
          break label107;
        bool2 = true;
        label38: j = this.mPackageManager.getApplicationEnabledSetting(paramPackageInfo.packageName);
        if (j != 3)
          break label113;
        bool3 = true;
        break label152;
      }
      while (true)
      {
        localPackageState = new PackageStateRepository.PackageState(paramPackageInfo.packageName, computeCertificateHashes(paramPackageInfo), bool1, bool2, bool4, bool3, i, isActiveDeviceAdmin(paramPackageInfo.packageName));
        return localPackageState;
        bool1 = false;
        break;
        label107: bool2 = false;
        break label38;
        label113: bool3 = false;
        break label152;
        bool4 = false;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        int j;
        boolean bool3;
        boolean bool4;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramPackageInfo.packageName;
        FinskyLog.w("Package %s not installed", arrayOfObject);
        PackageStateRepository.PackageState localPackageState = NOT_INSTALLED_MARKER;
        continue;
        label152: if ((j == 2) || (bool3))
          bool4 = true;
      }
    }
  }

  private boolean isActiveDeviceAdmin(String paramString)
  {
    boolean bool = false;
    List localList = this.mDevicePolicyManager.getActiveAdmins();
    if (localList == null)
      break label24;
    while (true)
    {
      return bool;
      Iterator localIterator = localList.iterator();
      label24: if (localIterator.hasNext())
      {
        if (!((ComponentName)localIterator.next()).getPackageName().equals(paramString))
          break;
        bool = true;
      }
    }
  }

  private PackageStateRepository.PackageState refreshEntry(String paramString, boolean paramBoolean)
  {
    PackageStateRepository.PackageState localPackageState;
    if (paramBoolean)
    {
      this.mPackageStates.put(paramString, NOT_INSTALLED_MARKER);
      localPackageState = NOT_INSTALLED_MARKER;
    }
    while (true)
    {
      return localPackageState;
      try
      {
        localPackageState = createPackageState(this.mPackageManager.getPackageInfo(paramString, 64));
        this.mPackageStates.put(paramString, localPackageState);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        this.mPackageStates.put(paramString, NOT_INSTALLED_MARKER);
        localPackageState = NOT_INSTALLED_MARKER;
      }
    }
  }

  public boolean canLaunch(String paramString)
  {
    if (this.mPackageManager.getLaunchIntentForPackage(paramString) != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public PackageStateRepository.PackageState get(String paramString)
  {
    try
    {
      PackageStateRepository.PackageState localPackageState1 = (PackageStateRepository.PackageState)this.mPackageStates.get(paramString);
      if (localPackageState1 == null)
        localPackageState1 = refreshEntry(paramString, false);
      PackageStateRepository.PackageState localPackageState2 = NOT_INSTALLED_MARKER;
      if (localPackageState1 == localPackageState2)
        localPackageState1 = null;
      return localPackageState1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Collection<PackageStateRepository.PackageState> getAllBlocking()
  {
    Utils.ensureNotOnMainThread();
    List localList = this.mPackageManager.getInstalledPackages(64);
    ArrayList localArrayList = Lists.newArrayList(localList.size());
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      PackageStateRepository.PackageState localPackageState = createPackageState((PackageInfo)localIterator.next());
      if (localPackageState != NOT_INSTALLED_MARKER)
        localArrayList.add(localPackageState);
    }
    return localArrayList;
  }

  public String getVersionName(String paramString)
  {
    try
    {
      str = this.mPackageManager.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        String str = null;
    }
  }

  public void invalidate(String paramString)
  {
    try
    {
      this.mPackageStates.remove(paramString);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onPackageAdded(String paramString)
  {
    refreshEntry(paramString, false);
  }

  public void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
      refreshEntry(paramArrayOfString[j], false);
  }

  public void onPackageChanged(String paramString)
  {
    refreshEntry(paramString, false);
  }

  public void onPackageFirstLaunch(String paramString)
  {
  }

  public void onPackageRemoved(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      refreshEntry(paramString, bool);
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.PackageManagerRepository
 * JD-Core Version:    0.6.2
 */
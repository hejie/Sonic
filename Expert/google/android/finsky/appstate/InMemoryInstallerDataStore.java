package com.google.android.finsky.appstate;

import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.Map;

public class InMemoryInstallerDataStore
  implements InstallerDataStore
{
  private final Map<String, InstallerDataStore.InstallerData> mAppStates = Maps.newHashMap();

  public InstallerDataStore.InstallerData get(String paramString)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = (InstallerDataStore.InstallerData)this.mAppStates.get(paramString);
      return localInstallerData;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Collection<InstallerDataStore.InstallerData> getAll()
  {
    try
    {
      Collection localCollection = this.mAppStates.values();
      return localCollection;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void put(InstallerDataStore.InstallerData paramInstallerData)
  {
    try
    {
      this.mAppStates.put(paramInstallerData.getPackageName(), paramInstallerData);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAccount(String paramString1, String paramString2)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString1);
      this.mAppStates.put(paramString1, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString1).setAccountName(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAutoUpdate(String paramString, InstallerDataStore.AutoUpdateState paramAutoUpdateState)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setAutoUpdate(paramAutoUpdateState).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setContinueUrl(String paramString1, String paramString2)
  {
    InstallerDataStore.InstallerData localInstallerData = get(paramString1);
    this.mAppStates.put(paramString1, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString1).setContinueUrl(paramString2).build());
  }

  public void setDeliveryData(String paramString, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, long paramLong)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setDeliveryData(paramAndroidAppDeliveryData, paramLong).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDesiredVersion(String paramString, int paramInt)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setDesiredVersion(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFirstDownloadMs(String paramString, long paramLong)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setFirstDownloadMs(paramLong).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFlags(String paramString, int paramInt)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setFlags(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setInstallerState(String paramString1, int paramInt, String paramString2)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString1);
      this.mAppStates.put(paramString1, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString1).setInstallerState(paramInt).setDownloadUri(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setLastNotifiedVersion(String paramString, int paramInt)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString);
      this.mAppStates.put(paramString, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString).setLastNotifiedVersion(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setReferrer(String paramString1, String paramString2)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString1);
      this.mAppStates.put(paramString1, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString1).setExternalReferrer(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setTitle(String paramString1, String paramString2)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = get(paramString1);
      this.mAppStates.put(paramString1, InstallerDataStore.InstallerData.Builder.buildUpon(localInstallerData, paramString1).setTitle(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.InMemoryInstallerDataStore
 * JD-Core Version:    0.6.2
 */
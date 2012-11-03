package com.google.android.finsky.appstate;

import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Collection;

public abstract interface InstallerDataStore
{
  public abstract InstallerData get(String paramString);

  public abstract Collection<InstallerData> getAll();

  public abstract void put(InstallerData paramInstallerData);

  public abstract void setAccount(String paramString1, String paramString2);

  public abstract void setAutoUpdate(String paramString, AutoUpdateState paramAutoUpdateState);

  public abstract void setContinueUrl(String paramString1, String paramString2);

  public abstract void setDeliveryData(String paramString, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, long paramLong);

  public abstract void setDesiredVersion(String paramString, int paramInt);

  public abstract void setFirstDownloadMs(String paramString, long paramLong);

  public abstract void setFlags(String paramString, int paramInt);

  public abstract void setInstallerState(String paramString1, int paramInt, String paramString2);

  public abstract void setLastNotifiedVersion(String paramString, int paramInt);

  public abstract void setReferrer(String paramString1, String paramString2);

  public abstract void setTitle(String paramString1, String paramString2);

  public static enum AutoUpdateState
  {
    static
    {
      DISABLED = new AutoUpdateState("DISABLED", 2);
      AutoUpdateState[] arrayOfAutoUpdateState = new AutoUpdateState[3];
      arrayOfAutoUpdateState[0] = DEFAULT;
      arrayOfAutoUpdateState[1] = ENABLED;
      arrayOfAutoUpdateState[2] = DISABLED;
    }

    public static AutoUpdateState valueOf(int paramInt)
    {
      for (AutoUpdateState localAutoUpdateState : values())
        if (localAutoUpdateState.ordinal() == paramInt)
          return localAutoUpdateState;
      throw new IllegalArgumentException("Unknown ordinal: " + paramInt);
    }
  }

  public static class InstallerData
  {
    private String accountName;
    private InstallerDataStore.AutoUpdateState autoUpdate = InstallerDataStore.AutoUpdateState.DEFAULT;
    private String continueUrl;
    private AndroidAppDelivery.AndroidAppDeliveryData deliveryData;
    private long deliveryDataTimestampMs;
    private int desiredVersion = -1;
    private String downloadUri;
    private long firstDownloadMs;
    private int flags;
    private int installerState;
    private int lastNotifiedVersion = -1;
    private String packageName;
    private String referrer;
    private String title;

    private InstallerData()
    {
    }

    public InstallerData(String paramString1, InstallerDataStore.AutoUpdateState paramAutoUpdateState, int paramInt1, int paramInt2, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, long paramLong1, int paramInt3, String paramString2, long paramLong2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt4)
    {
      this.packageName = paramString1;
      this.autoUpdate = paramAutoUpdateState;
      this.desiredVersion = paramInt1;
      this.lastNotifiedVersion = paramInt2;
      this.deliveryData = paramAndroidAppDeliveryData;
      this.deliveryDataTimestampMs = paramLong1;
      this.installerState = paramInt3;
      this.downloadUri = paramString2;
      this.firstDownloadMs = paramLong2;
      this.referrer = paramString3;
      this.continueUrl = paramString4;
      this.accountName = paramString5;
      this.title = paramString6;
      this.flags = paramInt4;
    }

    public String getAccountName()
    {
      return this.accountName;
    }

    public InstallerDataStore.AutoUpdateState getAutoUpdate()
    {
      return this.autoUpdate;
    }

    public String getContinueUrl()
    {
      return this.continueUrl;
    }

    public AndroidAppDelivery.AndroidAppDeliveryData getDeliveryData()
    {
      return this.deliveryData;
    }

    public long getDeliveryDataTimestampMs()
    {
      return this.deliveryDataTimestampMs;
    }

    public int getDesiredVersion()
    {
      return this.desiredVersion;
    }

    public String getDownloadUri()
    {
      return this.downloadUri;
    }

    public long getFirstDownloadMs()
    {
      return this.firstDownloadMs;
    }

    public int getFlags()
    {
      return this.flags;
    }

    public int getInstallerState()
    {
      return this.installerState;
    }

    public int getLastNotifiedVersion()
    {
      return this.lastNotifiedVersion;
    }

    public String getPackageName()
    {
      return this.packageName;
    }

    public String getReferrer()
    {
      return this.referrer;
    }

    public String getTitle()
    {
      return this.title;
    }

    public String toString()
    {
      boolean bool = true;
      Object[] arrayOfObject = new Object[10];
      arrayOfObject[0] = this.packageName;
      arrayOfObject[bool] = this.autoUpdate;
      arrayOfObject[2] = Integer.valueOf(this.desiredVersion);
      if (this.deliveryData != null);
      while (true)
      {
        arrayOfObject[3] = Boolean.valueOf(bool);
        arrayOfObject[4] = Integer.valueOf(this.installerState);
        arrayOfObject[5] = this.downloadUri;
        arrayOfObject[6] = Long.valueOf(this.firstDownloadMs);
        arrayOfObject[7] = this.referrer;
        arrayOfObject[8] = this.continueUrl;
        arrayOfObject[9] = FinskyLog.scrubPii(this.accountName);
        return String.format("(packageName=%s,autoUpdate=%s,desiredVersion=%d,hasDeliveryData=%b,installerState=%d,downloadUri=%s,firstDownloadMs=%d,referrer=%s,continueUrl=%s,account=%s)", arrayOfObject);
        bool = false;
      }
    }

    public static class Builder
    {
      private final InstallerDataStore.InstallerData mInstance = new InstallerDataStore.InstallerData(null);

      public static Builder buildUpon(InstallerDataStore.InstallerData paramInstallerData, String paramString)
      {
        Builder localBuilder = new Builder();
        localBuilder.setPackageName(paramString);
        if (paramInstallerData != null)
        {
          localBuilder.setAutoUpdate(paramInstallerData.getAutoUpdate());
          localBuilder.setDesiredVersion(paramInstallerData.getDesiredVersion());
          localBuilder.setLastNotifiedVersion(paramInstallerData.getLastNotifiedVersion());
          localBuilder.setDeliveryData(paramInstallerData.getDeliveryData(), paramInstallerData.getDeliveryDataTimestampMs());
          localBuilder.setInstallerState(paramInstallerData.getInstallerState());
          localBuilder.setDownloadUri(paramInstallerData.getDownloadUri());
          localBuilder.setFirstDownloadMs(paramInstallerData.getFirstDownloadMs());
          localBuilder.setExternalReferrer(paramInstallerData.getReferrer());
          localBuilder.setContinueUrl(paramInstallerData.getContinueUrl());
          localBuilder.setAccountName(paramInstallerData.getAccountName());
          localBuilder.setTitle(paramInstallerData.getTitle());
          localBuilder.setFlags(paramInstallerData.getFlags());
        }
        return localBuilder;
      }

      public InstallerDataStore.InstallerData build()
      {
        return this.mInstance;
      }

      public Builder setAccountName(String paramString)
      {
        InstallerDataStore.InstallerData.access$1102(this.mInstance, paramString);
        return this;
      }

      public Builder setAutoUpdate(InstallerDataStore.AutoUpdateState paramAutoUpdateState)
      {
        InstallerDataStore.InstallerData.access$202(this.mInstance, paramAutoUpdateState);
        return this;
      }

      public Builder setContinueUrl(String paramString)
      {
        InstallerDataStore.InstallerData.access$1402(this.mInstance, paramString);
        return this;
      }

      public Builder setDeliveryData(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, long paramLong)
      {
        InstallerDataStore.InstallerData.access$502(this.mInstance, paramAndroidAppDeliveryData);
        InstallerDataStore.InstallerData.access$602(this.mInstance, paramLong);
        return this;
      }

      public Builder setDesiredVersion(int paramInt)
      {
        InstallerDataStore.InstallerData.access$302(this.mInstance, paramInt);
        return this;
      }

      public Builder setDownloadUri(String paramString)
      {
        InstallerDataStore.InstallerData.access$802(this.mInstance, paramString);
        return this;
      }

      public Builder setExternalReferrer(String paramString)
      {
        InstallerDataStore.InstallerData.access$1002(this.mInstance, paramString);
        return this;
      }

      public Builder setFirstDownloadMs(long paramLong)
      {
        InstallerDataStore.InstallerData.access$902(this.mInstance, paramLong);
        return this;
      }

      public Builder setFlags(int paramInt)
      {
        InstallerDataStore.InstallerData.access$1302(this.mInstance, paramInt);
        return this;
      }

      public Builder setInstallerState(int paramInt)
      {
        InstallerDataStore.InstallerData.access$702(this.mInstance, paramInt);
        return this;
      }

      public Builder setLastNotifiedVersion(int paramInt)
      {
        InstallerDataStore.InstallerData.access$402(this.mInstance, paramInt);
        return this;
      }

      public Builder setPackageName(String paramString)
      {
        InstallerDataStore.InstallerData.access$102(this.mInstance, paramString);
        return this;
      }

      public Builder setTitle(String paramString)
      {
        InstallerDataStore.InstallerData.access$1202(this.mInstance, paramString);
        return this;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.InstallerDataStore
 * JD-Core Version:    0.6.2
 */
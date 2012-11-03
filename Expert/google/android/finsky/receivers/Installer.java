package com.google.android.finsky.receivers;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import java.util.List;

public abstract interface Installer
{
  public abstract void addListener(InstallerListener paramInstallerListener);

  public abstract void cancel(String paramString);

  public abstract void cancelAll();

  public abstract InstallerProgressReport getProgress(String paramString);

  public abstract InstallerState getState(String paramString);

  public abstract boolean isBusy();

  public abstract void removeListener(InstallerListener paramInstallerListener);

  public abstract void requestInstall(String paramString1, int paramInt, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, String paramString6);

  public abstract void setMobileDataAllowed(String paramString);

  public abstract void setVisibility(String paramString, boolean paramBoolean1, boolean paramBoolean2);

  public abstract void start();

  public abstract void startDeferredInstalls();

  public abstract void uninstallAssetSilently(String paramString);

  public abstract void uninstallPackagesByUid(String paramString);

  public abstract void updateInstalledApps(List<Document> paramList, String paramString);

  public static class InstallerProgressReport
  {
    public final long bytesCompleted;
    public final long bytesTotal;
    public final int downloadStatus;
    public final Installer.InstallerState installerState;

    public InstallerProgressReport(Installer.InstallerState paramInstallerState, long paramLong1, long paramLong2, int paramInt)
    {
      this.installerState = paramInstallerState;
      this.bytesCompleted = paramLong1;
      this.bytesTotal = paramLong2;
      this.downloadStatus = paramInt;
    }
  }

  public static enum InstallerState
  {
    static
    {
      DOWNLOAD_PENDING = new InstallerState("DOWNLOAD_PENDING", 1);
      DOWNLOADING = new InstallerState("DOWNLOADING", 2);
      INSTALLING = new InstallerState("INSTALLING", 3);
      UNINSTALLING = new InstallerState("UNINSTALLING", 4);
      InstallerState[] arrayOfInstallerState = new InstallerState[5];
      arrayOfInstallerState[0] = NOT_TRACKED;
      arrayOfInstallerState[1] = DOWNLOAD_PENDING;
      arrayOfInstallerState[2] = DOWNLOADING;
      arrayOfInstallerState[3] = INSTALLING;
      arrayOfInstallerState[4] = UNINSTALLING;
    }

    public boolean isDownloadingOrInstalling()
    {
      if ((this == DOWNLOAD_PENDING) || (this == DOWNLOADING) || (this == INSTALLING));
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.Installer
 * JD-Core Version:    0.6.2
 */
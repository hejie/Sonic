package com.google.android.finsky.installer;

public abstract interface InstallerListener
{
  public abstract void onInstallPackageEvent(String paramString, InstallerPackageEvent paramInstallerPackageEvent, int paramInt);

  public static enum InstallerPackageEvent
  {
    static
    {
      DOWNLOADING = new InstallerPackageEvent("DOWNLOADING", 1);
      DOWNLOAD_CANCELLED = new InstallerPackageEvent("DOWNLOAD_CANCELLED", 2);
      DOWNLOAD_ERROR = new InstallerPackageEvent("DOWNLOAD_ERROR", 3);
      INSTALLING = new InstallerPackageEvent("INSTALLING", 4);
      INSTALL_ERROR = new InstallerPackageEvent("INSTALL_ERROR", 5);
      INSTALLED = new InstallerPackageEvent("INSTALLED", 6);
      UNINSTALLING = new InstallerPackageEvent("UNINSTALLING", 7);
      UNINSTALLED = new InstallerPackageEvent("UNINSTALLED", 8);
      InstallerPackageEvent[] arrayOfInstallerPackageEvent = new InstallerPackageEvent[9];
      arrayOfInstallerPackageEvent[0] = DOWNLOAD_PENDING;
      arrayOfInstallerPackageEvent[1] = DOWNLOADING;
      arrayOfInstallerPackageEvent[2] = DOWNLOAD_CANCELLED;
      arrayOfInstallerPackageEvent[3] = DOWNLOAD_ERROR;
      arrayOfInstallerPackageEvent[4] = INSTALLING;
      arrayOfInstallerPackageEvent[5] = INSTALL_ERROR;
      arrayOfInstallerPackageEvent[6] = INSTALLED;
      arrayOfInstallerPackageEvent[7] = UNINSTALLING;
      arrayOfInstallerPackageEvent[8] = UNINSTALLED;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.installer.InstallerListener
 * JD-Core Version:    0.6.2
 */
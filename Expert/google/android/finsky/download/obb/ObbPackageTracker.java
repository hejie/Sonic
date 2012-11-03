package com.google.android.finsky.download.obb;

import android.os.Build.VERSION;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import java.io.File;

public class ObbPackageTracker
  implements PackageMonitorReceiver.PackageStatusListener
{
  private final int GINGERBREAD_MR1 = 10;

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
    if ((Build.VERSION.SDK_INT <= 10) && (!paramBoolean))
    {
      File localFile = ObbFactory.getParentDirectory(paramString);
      if (localFile.exists())
      {
        File[] arrayOfFile = localFile.listFiles();
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++)
          arrayOfFile[j].delete();
        localFile.delete();
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.obb.ObbPackageTracker
 * JD-Core Version:    0.6.2
 */
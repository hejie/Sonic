package com.google.android.finsky.download.obb;

import android.os.Environment;
import java.io.File;

public class ObbFactory
{
  private static File sObbMasterDirectory;

  public static Obb create(boolean paramBoolean, String paramString1, int paramInt1, String paramString2, long paramLong, int paramInt2)
  {
    return new ObbImpl(paramBoolean, paramString1, paramInt1, paramString2, paramLong, paramInt2);
  }

  public static File getParentDirectory(String paramString)
  {
    return new File(sObbMasterDirectory, paramString);
  }

  public static void initialize()
  {
    sObbMasterDirectory = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "obb");
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.obb.ObbFactory
 * JD-Core Version:    0.6.2
 */
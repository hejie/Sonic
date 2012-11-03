package com.google.android.finsky.download.obb;

import java.io.File;

public abstract interface Obb
{
  public abstract boolean finalizeTempFile();

  public abstract File getFile();

  public abstract String getPackage();

  public abstract long getSize();

  public abstract int getState();

  public abstract File getTempFile();

  public abstract String getUrl();

  public abstract boolean isPatch();

  public abstract void syncStateWithStorage();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.obb.Obb
 * JD-Core Version:    0.6.2
 */
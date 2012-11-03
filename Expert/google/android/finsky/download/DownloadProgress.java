package com.google.android.finsky.download;

public class DownloadProgress
{
  public final long bytesCompleted;
  public final long bytesTotal;
  public final int statusCode;

  public DownloadProgress(long paramLong1, long paramLong2, int paramInt)
  {
    this.bytesCompleted = paramLong1;
    this.bytesTotal = paramLong2;
    this.statusCode = paramInt;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if ((paramObject != null) && ((paramObject instanceof DownloadProgress)))
    {
      DownloadProgress localDownloadProgress = (DownloadProgress)paramObject;
      if ((this.bytesCompleted == localDownloadProgress.bytesCompleted) && (this.bytesTotal == localDownloadProgress.bytesTotal) && (this.statusCode == localDownloadProgress.statusCode))
        bool = true;
    }
    return bool;
  }

  public int hashCode()
  {
    return 42;
  }

  public String toString()
  {
    return this.bytesCompleted + "/" + this.bytesTotal + " Status: " + this.statusCode;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadProgress
 * JD-Core Version:    0.6.2
 */
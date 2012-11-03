package com.google.android.finsky.download;

import android.net.Uri;

public abstract interface Download
{
  public abstract Uri getContentUri();

  public abstract DownloadProgress getProgress();

  public abstract String getUrl();

  public abstract boolean isCompleted();

  public static enum DownloadState
  {
    static
    {
      QUEUED = new DownloadState("QUEUED", 1);
      DOWNLOADING = new DownloadState("DOWNLOADING", 2);
      SUCCESS = new DownloadState("SUCCESS", 3);
      CANCELLED = new DownloadState("CANCELLED", 4);
      ERROR = new DownloadState("ERROR", 5);
      DownloadState[] arrayOfDownloadState = new DownloadState[6];
      arrayOfDownloadState[0] = UNQUEUED;
      arrayOfDownloadState[1] = QUEUED;
      arrayOfDownloadState[2] = DOWNLOADING;
      arrayOfDownloadState[3] = SUCCESS;
      arrayOfDownloadState[4] = CANCELLED;
      arrayOfDownloadState[5] = ERROR;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.Download
 * JD-Core Version:    0.6.2
 */
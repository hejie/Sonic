package com.google.android.finsky.download;

public abstract interface DownloadQueueListener
{
  public abstract void onCancel(Download paramDownload);

  public abstract void onComplete(Download paramDownload);

  public abstract void onError(Download paramDownload, int paramInt);

  public abstract void onNotificationClicked(Download paramDownload);

  public abstract void onProgress(Download paramDownload, DownloadProgress paramDownloadProgress);

  public abstract void onStart(Download paramDownload);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadQueueListener
 * JD-Core Version:    0.6.2
 */
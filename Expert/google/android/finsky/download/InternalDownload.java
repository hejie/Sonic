package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.download.obb.Obb;

public abstract interface InternalDownload extends Download
{
  public abstract DownloadRequest createDownloadRequest(String paramString1, String paramString2);

  public abstract int getHttpStatus();

  public abstract long getMaximumSize();

  public abstract Obb getObb();

  public abstract String getPackageName();

  public abstract Uri getRequestedDestination();

  public abstract Download.DownloadState getState();

  public abstract boolean isObb();

  public abstract void setContentUri(Uri paramUri);

  public abstract void setHttpStatus(int paramInt);

  public abstract void setProgress(DownloadProgress paramDownloadProgress);

  public abstract void setState(Download.DownloadState paramDownloadState);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.InternalDownload
 * JD-Core Version:    0.6.2
 */
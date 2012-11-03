package com.google.android.finsky.download;

import android.net.Uri;
import java.util.Collection;

public abstract interface DownloadQueue
{
  public abstract void add(InternalDownload paramInternalDownload);

  public abstract void addListener(DownloadQueueListener paramDownloadQueueListener);

  public abstract void addRecoveredDownload(InternalDownload paramInternalDownload);

  public abstract void cancel(Download paramDownload);

  public abstract Download getByPackageName(String paramString);

  public abstract Collection<RunningDownload> getRunningUris();

  public abstract void release(Uri paramUri);

  public static class RunningDownload
  {
    public final String contentUri;
    public final int status;

    public RunningDownload(String paramString, int paramInt)
    {
      this.contentUri = paramString;
      this.status = paramInt;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadQueue
 * JD-Core Version:    0.6.2
 */
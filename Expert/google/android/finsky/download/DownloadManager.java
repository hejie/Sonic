package com.google.android.finsky.download;

import android.database.Cursor;
import android.net.Uri;
import com.google.android.finsky.utils.ParameterizedRunnable;

public abstract interface DownloadManager
{
  public abstract void enqueue(DownloadRequest paramDownloadRequest, ParameterizedRunnable<Uri> paramParameterizedRunnable);

  public abstract Cursor queryAllDownloads();

  public abstract Cursor queryStatus(Uri paramUri);

  public abstract void remove(Uri paramUri);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadManager
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.download;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParameterizedRunnable;
import com.google.android.finsky.utils.Utils;

public class DownloadManagerImpl
  implements DownloadManager
{
  private final ContentResolver mContentResolver;
  private final Handler mHandler;

  public DownloadManagerImpl(Context paramContext)
  {
    this.mContentResolver = paramContext.getContentResolver();
    HandlerThread localHandlerThread = new HandlerThread("download-manager-thread");
    localHandlerThread.start();
    this.mHandler = new Handler(localHandlerThread.getLooper());
  }

  private void synchronousRemove(Uri paramUri)
  {
    Utils.ensureNotOnMainThread();
    if ((paramUri != null) && (paramUri.toString() != null) && (!paramUri.equals(Uri.EMPTY)))
      this.mContentResolver.delete(paramUri, null, null);
  }

  public void enqueue(final DownloadRequest paramDownloadRequest, final ParameterizedRunnable<Uri> paramParameterizedRunnable)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        Uri localUri = DownloadManagerImpl.this.mContentResolver.insert(DownloadManagerConstants.getContentUri(), paramDownloadRequest.toContentValues());
        DownloadManagerConstants.sniffDownloadManagerVersion(localUri);
        if (paramParameterizedRunnable != null)
          paramParameterizedRunnable.run(localUri);
      }
    });
  }

  public Cursor queryAllDownloads()
  {
    Object localObject = null;
    Utils.ensureNotOnMainThread();
    String[] arrayOfString;
    Cursor localCursor;
    if (Build.VERSION.SDK_INT > 8)
    {
      arrayOfString = new String[5];
      arrayOfString[0] = "_id";
      arrayOfString[1] = "uri";
      arrayOfString[2] = "status";
      arrayOfString[3] = "lastmod";
      arrayOfString[4] = "title";
      localCursor = this.mContentResolver.query(DownloadManagerConstants.getContentUri(), arrayOfString, null, null, null);
      if (localCursor != null)
        break label101;
      FinskyLog.w("Download progress cursor null when attempting to fetch all Downloads.", new Object[0]);
    }
    while (true)
    {
      return localObject;
      arrayOfString = new String[] { "_id", "status", "lastmod", "title" };
      break;
      label101: localObject = localCursor;
    }
  }

  public Cursor queryStatus(Uri paramUri)
  {
    Utils.ensureNotOnMainThread();
    return this.mContentResolver.query(paramUri, new String[] { "status" }, null, null, null);
  }

  public void remove(final Uri paramUri)
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        DownloadManagerImpl.this.synchronousRemove(paramUri);
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadManagerImpl
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.download;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class DownloadProgressManager
{
  private static volatile Map<Uri, DownloadProgress> sDownloadProgressMap = null;
  private final Context mContext = FinskyApp.get();
  private Cursor mCursor = null;
  private final DownloadQueueImpl mDownloadQueueImpl;
  private final Handler mHandler;
  private final HandlerThread mHandlerThread;
  private final long mHandlerThreadId;

  public DownloadProgressManager(DownloadQueueImpl paramDownloadQueueImpl)
  {
    this.mDownloadQueueImpl = paramDownloadQueueImpl;
    this.mHandlerThread = new HandlerThread("Download progress manager runner");
    this.mHandlerThread.start();
    this.mHandlerThreadId = this.mHandlerThread.getId();
    this.mHandler = new Handler(this.mHandlerThread.getLooper());
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        DownloadProgressManager.this.makeCursorIfNeeded(DownloadProgressManager.this.mContext);
        DownloadProgressManager.this.onDownloadProgress();
      }
    });
  }

  private void assertOnHandlerThread()
  {
    long l = Thread.currentThread().getId();
    if (l != this.mHandlerThreadId)
      throw new IllegalStateException("This should only be run on DownloadProgressManager's handler thread (" + this.mHandlerThreadId + "). " + "Instead we're on thread " + l);
  }

  private Map<Uri, DownloadProgress> generateDownloadProgressFromCursor()
  {
    assertOnHandlerThread();
    HashMap localHashMap = new HashMap();
    if (!this.mCursor.requery())
    {
      this.mCursor = null;
      makeCursorIfNeeded(this.mContext);
    }
    if (this.mCursor.getCount() > 0)
    {
      int i = this.mCursor.getColumnIndexOrThrow("_id");
      int j = this.mCursor.getColumnIndexOrThrow("current_bytes");
      int k = this.mCursor.getColumnIndexOrThrow("total_bytes");
      int m = this.mCursor.getColumnIndexOrThrow("status");
      int n = this.mCursor.getColumnIndex("allowed_network_types");
      while (this.mCursor.moveToNext())
      {
        Uri localUri = Uri.parse(DownloadManagerConstants.getContentUriString(String.valueOf(this.mCursor.getLong(i))));
        int i1 = this.mCursor.getInt(m);
        long l1 = this.mCursor.getLong(k);
        long l2 = this.mCursor.getLong(j);
        if ((i1 == 195) && (n != -1) && (this.mCursor.getInt(n) == 2))
          i1 = 196;
        localHashMap.put(localUri, new DownloadProgress(l2, l1, i1));
      }
    }
    return localHashMap;
  }

  private static Map<Uri, DownloadProgress> getCachedProgress()
  {
    return sDownloadProgressMap;
  }

  private static Set<Uri> getUris()
  {
    HashSet localHashSet = new HashSet();
    Map localMap = getCachedProgress();
    if (localMap != null)
    {
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
        localHashSet.add((Uri)localIterator.next());
    }
    return localHashSet;
  }

  private static Cursor makeCursor(Context paramContext)
  {
    Uri localUri = DownloadManagerConstants.getContentUriForContentObserver();
    Cursor localCursor = paramContext.getContentResolver().query(localUri, null, null, null, null);
    if (localCursor == null)
    {
      FinskyLog.wtf("Download progress cursor null: %s", new Object[] { localUri });
      System.exit(0);
    }
    return localCursor;
  }

  private void makeCursorIfNeeded(Context paramContext)
  {
    assertOnHandlerThread();
    if (this.mCursor == null)
    {
      this.mCursor = makeCursor(paramContext);
      ContentObserver local2 = new ContentObserver(this.mHandler)
      {
        public boolean deliverSelfNotifications()
        {
          return false;
        }

        public void onChange(boolean paramAnonymousBoolean)
        {
          DownloadProgressManager.this.onDownloadProgress();
        }
      };
      this.mCursor.registerContentObserver(local2);
    }
  }

  private void onDownloadProgress()
  {
    assertOnHandlerThread();
    Set localSet1 = getUris();
    Map localMap1 = Collections.unmodifiableMap(generateDownloadProgressFromCursor());
    Map localMap2 = getCachedProgress();
    if ((localMap2 == null) || (!localMap2.equals(localMap1)))
    {
      sDownloadProgressMap = localMap1;
      Set localSet2 = getUris();
      localSet1.removeAll(localSet2);
      ProgressRunnable localProgressRunnable = new ProgressRunnable(localMap1, localSet1, localSet2);
      new Handler(Looper.getMainLooper()).post(localProgressRunnable);
    }
  }

  public void cleanup()
  {
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        if (DownloadProgressManager.this.mCursor != null)
        {
          DownloadProgressManager.this.mCursor.close();
          DownloadProgressManager.access$302(DownloadProgressManager.this, null);
        }
        DownloadProgressManager.this.mHandlerThread.quit();
      }
    });
  }

  private class ProgressRunnable
    implements Runnable
  {
    private Map<Uri, DownloadProgress> mDownloadProgressMap;
    private Set<Uri> mNewUris;
    private Set<Uri> mOldUris;

    public ProgressRunnable(Set<Uri> paramSet1, Set<Uri> arg3)
    {
      Object localObject1;
      this.mOldUris = localObject1;
      Object localObject2;
      this.mNewUris = localObject2;
      this.mDownloadProgressMap = paramSet1;
    }

    public void run()
    {
      DownloadQueueImpl localDownloadQueueImpl = DownloadProgressManager.this.mDownloadQueueImpl;
      Iterator localIterator1 = this.mOldUris.iterator();
      while (localIterator1.hasNext())
      {
        InternalDownload localInternalDownload2 = (InternalDownload)localDownloadQueueImpl.getDownloadByContentUri((Uri)localIterator1.next());
        if ((localInternalDownload2 != null) && (localInternalDownload2.getState().equals(Download.DownloadState.DOWNLOADING)))
          DownloadProgressManager.this.mDownloadQueueImpl.cancel(localInternalDownload2);
      }
      Iterator localIterator2 = this.mNewUris.iterator();
      while (localIterator2.hasNext())
      {
        Uri localUri = (Uri)localIterator2.next();
        InternalDownload localInternalDownload1 = (InternalDownload)localDownloadQueueImpl.getDownloadByContentUri(localUri);
        if (localInternalDownload1 != null)
          localDownloadQueueImpl.notifyProgress(localInternalDownload1, (DownloadProgress)this.mDownloadProgressMap.get(localUri));
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadProgressManager
 * JD-Core Version:    0.6.2
 */
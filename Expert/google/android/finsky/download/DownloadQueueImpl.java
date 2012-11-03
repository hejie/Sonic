package com.google.android.finsky.download;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.PackageManagerUtils;
import com.google.android.finsky.utils.PackageManagerUtils.FreeSpaceListener;
import com.google.android.finsky.utils.ParameterizedRunnable;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.api.VendingApiContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class DownloadQueueImpl
  implements DownloadQueue, DownloadQueueListener
{
  private Context mContext;
  private final DownloadManager mDownloadManager;
  private DownloadProgressManager mDownloadProgressManager;
  private LinkedList<DownloadQueueListener> mListeners;
  private final int mMaxConcurrent;
  private LinkedHashMap<String, InternalDownload> mPendingQueue;
  private Uri mPreviousContentUri = null;
  private int mPreviousProgressStatus = -1;
  private HashMap<String, InternalDownload> mRunningMap;

  public DownloadQueueImpl(Context paramContext, int paramInt, DownloadManager paramDownloadManager)
  {
    setupQueue();
    this.mMaxConcurrent = paramInt;
    this.mDownloadManager = paramDownloadManager;
    this.mContext = paramContext;
    this.mListeners = new LinkedList();
    this.mListeners.add(this);
  }

  public DownloadQueueImpl(Context paramContext, DownloadManager paramDownloadManager)
  {
    this(paramContext, 1, paramDownloadManager);
  }

  private void enqueueDownload(final InternalDownload paramInternalDownload)
  {
    final FinskyApp localFinskyApp = FinskyApp.get();
    DownloadRequest localDownloadRequest = paramInternalDownload.createDownloadRequest(localFinskyApp.getPackageName(), DownloadBroadcastReceiver.class.getName());
    this.mDownloadManager.enqueue(localDownloadRequest, new ParameterizedRunnable()
    {
      public void run(final Uri paramAnonymousUri)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramInternalDownload;
        arrayOfObject[1] = paramAnonymousUri.toString();
        FinskyLog.d("Enqueued %s as %s", arrayOfObject);
        new Handler(localFinskyApp.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if (DownloadQueueImpl.7.this.val$download.isCompleted())
              DownloadQueueImpl.this.mDownloadManager.remove(paramAnonymousUri);
            while (true)
            {
              return;
              DownloadQueueImpl.7.this.val$download.setContentUri(paramAnonymousUri);
              DownloadQueueImpl.this.setDownloadState(DownloadQueueImpl.7.this.val$download, Download.DownloadState.DOWNLOADING);
            }
          }
        });
      }
    });
  }

  private void remove(InternalDownload paramInternalDownload)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramInternalDownload.toString();
    FinskyLog.d("Download %s removed from DownloadQueue", arrayOfObject);
    String str = paramInternalDownload.getUrl();
    if (this.mPendingQueue.containsKey(str))
      this.mPendingQueue.remove(str);
    while (true)
    {
      return;
      this.mRunningMap.remove(paramInternalDownload.getUrl());
      startNextDownload();
    }
  }

  private void removeFromDownloadManager(InternalDownload paramInternalDownload)
  {
    Uri localUri = paramInternalDownload.getContentUri();
    if (localUri != null)
      this.mDownloadManager.remove(localUri);
  }

  private void setupQueue()
  {
    this.mPendingQueue = Maps.newLinkedHashMap();
    this.mRunningMap = Maps.newHashMap();
  }

  private void startNextDownload()
  {
    if (this.mRunningMap.size() >= this.mMaxConcurrent);
    while (true)
    {
      return;
      long l = 0L;
      Iterator localIterator = this.mPendingQueue.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        l = Math.max(((InternalDownload)this.mPendingQueue.get(str)).getMaximumSize(), l);
      }
      PackageManagerUtils.freeStorageAndNotify(this.mContext, l, new PurgeCacheCallback(null));
    }
  }

  public void add(InternalDownload paramInternalDownload)
  {
    Utils.ensureOnMainThread();
    if ((getExisting(paramInternalDownload.getUrl()) != null) || (!paramInternalDownload.getState().equals(Download.DownloadState.UNQUEUED)))
      FinskyLog.wtf("Tried to add invalid download to DownloadQueue.", new Object[0]);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramInternalDownload.toString();
    FinskyLog.d("Download %s added to DownloadQueue", arrayOfObject);
    this.mPendingQueue.put(paramInternalDownload.getUrl(), paramInternalDownload);
    if (this.mDownloadProgressManager == null)
      this.mDownloadProgressManager = new DownloadProgressManager(this);
    setDownloadState(paramInternalDownload, Download.DownloadState.QUEUED);
    startNextDownload();
  }

  public void addListener(DownloadQueueListener paramDownloadQueueListener)
  {
    Utils.ensureOnMainThread();
    this.mListeners.add(paramDownloadQueueListener);
  }

  public void addRecoveredDownload(InternalDownload paramInternalDownload)
  {
    Utils.ensureOnMainThread();
    String str = paramInternalDownload.getUrl();
    FinskyLog.d("Download queue recovering download %s.", new Object[] { paramInternalDownload });
    setDownloadState(paramInternalDownload, Download.DownloadState.DOWNLOADING);
    this.mRunningMap.put(str, paramInternalDownload);
    if (this.mDownloadProgressManager == null)
      this.mDownloadProgressManager = new DownloadProgressManager(this);
  }

  public void cancel(Download paramDownload)
  {
    Utils.ensureOnMainThread();
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    if ((localInternalDownload == null) || (localInternalDownload.isCompleted()));
    while (true)
    {
      return;
      if (localInternalDownload.getState().equals(Download.DownloadState.DOWNLOADING))
        this.mDownloadManager.remove(localInternalDownload.getContentUri());
      setDownloadState(localInternalDownload, Download.DownloadState.CANCELLED);
    }
  }

  public Download getByPackageName(String paramString)
  {
    Utils.ensureOnMainThread();
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("empty packageName");
    Iterator localIterator1 = this.mPendingQueue.values().iterator();
    Object localObject;
    do
    {
      if (!localIterator1.hasNext())
        break;
      localObject = (InternalDownload)localIterator1.next();
    }
    while (!paramString.equals(((InternalDownload)localObject).getPackageName()));
    while (true)
    {
      return localObject;
      Iterator localIterator2 = this.mRunningMap.values().iterator();
      while (true)
        if (localIterator2.hasNext())
        {
          InternalDownload localInternalDownload = (InternalDownload)localIterator2.next();
          if (paramString.equals(localInternalDownload.getPackageName()))
          {
            localObject = localInternalDownload;
            break;
          }
        }
      localObject = null;
    }
  }

  public Download getDownloadByContentUri(Uri paramUri)
  {
    Object localObject = null;
    Utils.ensureOnMainThread();
    String str;
    if (paramUri != null)
    {
      str = paramUri.toString();
      if (!TextUtils.isEmpty(str))
        break label28;
      label20: break label42;
    }
    while (true)
    {
      return localObject;
      str = null;
      break;
      label28: Iterator localIterator = this.mRunningMap.values().iterator();
      label42: if (localIterator.hasNext())
      {
        InternalDownload localInternalDownload = (InternalDownload)localIterator.next();
        if (!paramUri.equals(localInternalDownload.getContentUri()))
          break label20;
        localObject = localInternalDownload;
      }
    }
  }

  public DownloadManager getDownloadManager()
  {
    return this.mDownloadManager;
  }

  InternalDownload getExisting(String paramString)
  {
    InternalDownload localInternalDownload;
    if (this.mRunningMap.containsKey(paramString))
      localInternalDownload = (InternalDownload)this.mRunningMap.get(paramString);
    while (true)
    {
      return localInternalDownload;
      if (this.mPendingQueue.containsKey(paramString))
        localInternalDownload = (InternalDownload)this.mPendingQueue.get(paramString);
      else
        localInternalDownload = null;
    }
  }

  public Collection<DownloadQueue.RunningDownload> getRunningUris()
  {
    Object localObject1 = null;
    Utils.ensureNotOnMainThread();
    Cursor localCursor = null;
    while (true)
    {
      try
      {
        localCursor = this.mDownloadManager.queryAllDownloads();
        if (localCursor != null)
        {
          int i = localCursor.getCount();
          if (i != 0);
        }
        else
        {
          return localObject1;
        }
        int j = localCursor.getColumnIndex("_id");
        int k = localCursor.getColumnIndex("status");
        if ((j == -1) || (k == -1))
        {
          FinskyLog.wtf("Missing column(s) in download mgr cursor", new Object[0]);
          if (localCursor == null)
            continue;
          localCursor.close();
          continue;
        }
        localObject1 = Lists.newArrayList(localCursor.getCount());
        if (localCursor.moveToNext())
        {
          ((ArrayList)localObject1).add(new DownloadQueue.RunningDownload(DownloadManagerConstants.getContentUriString(localCursor.getString(j)), localCursor.getInt(k)));
          continue;
        }
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
      if (localCursor != null)
        localCursor.close();
    }
  }

  public void notifyClicked(InternalDownload paramInternalDownload)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramInternalDownload.toString();
    FinskyLog.d("%s: onNotificationClicked", arrayOfObject);
    notifyListeners(UpdateListenerType.NOTIFICATION_CLICKED, paramInternalDownload);
  }

  void notifyListeners(UpdateListenerType paramUpdateListenerType, final Download paramDownload)
  {
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    final DownloadProgress localDownloadProgress;
    if (paramDownload == null)
    {
      localDownloadProgress = null;
      label12: if (paramDownload != null)
        break label86;
    }
    label86: for (final int i = -1; ; i = localInternalDownload.getHttpStatus())
      switch (8.$SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[paramUpdateListenerType.ordinal()])
      {
      default:
        throw new IllegalStateException("Bad listener type.");
        localDownloadProgress = localInternalDownload.getProgress();
        break label12;
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    Object localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
    {
      public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
      {
        paramAnonymousDownloadQueueListener.onNotificationClicked(paramDownload);
      }
    };
    while (true)
    {
      new Handler(Looper.getMainLooper()).post((Runnable)localObject);
      return;
      localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
      {
        public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
        {
          paramAnonymousDownloadQueueListener.onComplete(paramDownload);
        }
      };
      continue;
      localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
      {
        public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
        {
          paramAnonymousDownloadQueueListener.onProgress(paramDownload, localDownloadProgress);
        }
      };
      continue;
      localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
      {
        public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
        {
          paramAnonymousDownloadQueueListener.onCancel(paramDownload);
        }
      };
      continue;
      localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
      {
        public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
        {
          paramAnonymousDownloadQueueListener.onError(paramDownload, i);
        }
      };
      continue;
      localObject = new ListenerNotifier(paramUpdateListenerType, paramDownload)
      {
        public void updateListener(DownloadQueueListener paramAnonymousDownloadQueueListener)
        {
          paramAnonymousDownloadQueueListener.onStart(paramDownload);
        }
      };
    }
  }

  public void notifyProgress(InternalDownload paramInternalDownload, DownloadProgress paramDownloadProgress)
  {
    if (paramDownloadProgress.equals(paramInternalDownload.getProgress()))
      return;
    paramInternalDownload.setProgress(paramDownloadProgress);
    int i = 0;
    if (paramDownloadProgress.statusCode != this.mPreviousProgressStatus);
    for (i = 1; ; i = 1)
      do
      {
        if (i != 0)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramInternalDownload.toString();
          arrayOfObject[1] = paramDownloadProgress.toString();
          FinskyLog.d("%s: onProgress %s.", arrayOfObject);
          this.mPreviousContentUri = paramInternalDownload.getContentUri();
          this.mPreviousProgressStatus = paramDownloadProgress.statusCode;
        }
        notifyListeners(UpdateListenerType.PROGRESS, paramInternalDownload);
        break;
      }
      while ((this.mPreviousContentUri != null) && (this.mPreviousContentUri.equals(paramInternalDownload.getContentUri())));
  }

  public void onCancel(Download paramDownload)
  {
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = localInternalDownload.toString();
    FinskyLog.d("%s: onCancel", arrayOfObject);
    remove(localInternalDownload);
    removeFromDownloadManager(localInternalDownload);
  }

  public void onComplete(Download paramDownload)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramDownload.toString();
    FinskyLog.d("%s: onComplete", arrayOfObject);
    remove((InternalDownload)paramDownload);
  }

  public void onError(Download paramDownload, int paramInt)
  {
    InternalDownload localInternalDownload = (InternalDownload)paramDownload;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localInternalDownload.toString();
    arrayOfObject[1] = Integer.valueOf(paramInt);
    FinskyLog.d("%s: onError %d.", arrayOfObject);
    if ((paramInt == 403) || (paramInt == 401))
      FinskyApp.get().getVendingApi().getApiContext().scheduleReauthentication(true);
    remove(localInternalDownload);
    removeFromDownloadManager(localInternalDownload);
  }

  public void onNotificationClicked(Download paramDownload)
  {
  }

  public void onProgress(Download paramDownload, DownloadProgress paramDownloadProgress)
  {
  }

  public void onStart(Download paramDownload)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramDownload.toString();
    FinskyLog.d("%s: onStart", arrayOfObject);
  }

  public void release(Uri paramUri)
  {
    this.mDownloadManager.remove(paramUri);
  }

  public void removeListener(DownloadQueueListener paramDownloadQueueListener)
  {
    Utils.ensureOnMainThread();
    this.mListeners.remove(paramDownloadQueueListener);
  }

  public void setDownloadState(InternalDownload paramInternalDownload, Download.DownloadState paramDownloadState)
  {
    paramInternalDownload.setState(paramDownloadState);
    switch (8.$SwitchMap$com$google$android$finsky$download$Download$DownloadState[paramDownloadState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      notifyListeners(UpdateListenerType.START, paramInternalDownload);
      continue;
      notifyListeners(UpdateListenerType.CANCEL, paramInternalDownload);
      continue;
      notifyListeners(UpdateListenerType.ERROR, paramInternalDownload);
      continue;
      notifyListeners(UpdateListenerType.COMPLETE, paramInternalDownload);
    }
  }

  void startDownload(InternalDownload paramInternalDownload)
  {
    if (paramInternalDownload == null);
    while (true)
    {
      return;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramInternalDownload.toString();
      FinskyLog.d("Download %s starting", arrayOfObject);
      this.mRunningMap.put(paramInternalDownload.getUrl(), paramInternalDownload);
      enqueueDownload(paramInternalDownload);
    }
  }

  private abstract class ListenerNotifier
    implements Runnable
  {
    DownloadQueueImpl.UpdateListenerType mType;

    public ListenerNotifier(DownloadQueueImpl.UpdateListenerType arg2)
    {
      Object localObject;
      this.mType = localObject;
    }

    public void run()
    {
      Iterator localIterator = DownloadQueueImpl.this.mListeners.iterator();
      while (localIterator.hasNext())
      {
        DownloadQueueListener localDownloadQueueListener = (DownloadQueueListener)localIterator.next();
        try
        {
          updateListener(localDownloadQueueListener);
        }
        catch (Exception localException)
        {
          FinskyLog.wtf(localException, "Download listener threw an exception during " + this.mType, new Object[0]);
        }
      }
    }

    abstract void updateListener(DownloadQueueListener paramDownloadQueueListener);
  }

  private class PurgeCacheCallback
    implements PackageManagerUtils.FreeSpaceListener
  {
    private PurgeCacheCallback()
    {
    }

    public void onComplete(boolean paramBoolean)
    {
      if (!paramBoolean)
        FinskyLog.w("Could not free required amount of space for download", new Object[0]);
      new Handler(DownloadQueueImpl.this.mContext.getMainLooper()).post(new DownloadQueueImpl.StartNextDownloadRunnable(DownloadQueueImpl.this, null));
    }
  }

  private class StartNextDownloadRunnable
    implements Runnable
  {
    private StartNextDownloadRunnable()
    {
    }

    public void run()
    {
      Utils.ensureOnMainThread();
      if (DownloadQueueImpl.this.mRunningMap.size() >= DownloadQueueImpl.this.mMaxConcurrent);
      while (true)
      {
        return;
        Object localObject = null;
        LinkedList localLinkedList = new LinkedList();
        Iterator localIterator1 = DownloadQueueImpl.this.mPendingQueue.keySet().iterator();
        while (localIterator1.hasNext())
        {
          String str2 = (String)localIterator1.next();
          InternalDownload localInternalDownload = (InternalDownload)DownloadQueueImpl.this.mPendingQueue.get(str2);
          localLinkedList.add(str2);
          if (localInternalDownload.getState().equals(Download.DownloadState.QUEUED))
          {
            long l1 = localInternalDownload.getMaximumSize();
            long l2 = Storage.cachePartitionAvailableSpace();
            long l3 = Storage.dataPartitionAvailableSpace();
            long l4 = Storage.externalStorageAvailableSpace();
            if (FinskyLog.DEBUG)
            {
              Object[] arrayOfObject = new Object[4];
              arrayOfObject[0] = Float.valueOf((float)l1 / 1048576.0F);
              arrayOfObject[1] = Float.valueOf((float)l2 / 1048576.0F);
              arrayOfObject[2] = Float.valueOf((float)l3 / 1048576.0F);
              arrayOfObject[3] = Float.valueOf((float)l4 / 1048576.0F);
              FinskyLog.v("b/4503710 : Download size : %f, Cache partition space : %f, Data partition space : %f, External storage space : %f", arrayOfObject);
            }
            if (localInternalDownload.getRequestedDestination() != null)
            {
              if (l4 < l1)
              {
                localInternalDownload.setHttpStatus(498);
                DownloadQueueImpl.this.setDownloadState(localInternalDownload, Download.DownloadState.ERROR);
              }
            }
            else if (l3 < l1)
            {
              localInternalDownload.setHttpStatus(498);
              DownloadQueueImpl.this.setDownloadState(localInternalDownload, Download.DownloadState.ERROR);
            }
            else
            {
              localObject = localInternalDownload;
            }
          }
        }
        Iterator localIterator2 = localLinkedList.iterator();
        while (localIterator2.hasNext())
        {
          String str1 = (String)localIterator2.next();
          DownloadQueueImpl.this.mPendingQueue.remove(str1);
        }
        DownloadQueueImpl.this.startDownload(localObject);
        if ((DownloadQueueImpl.this.mRunningMap.size() == 0) && (DownloadQueueImpl.this.mDownloadProgressManager != null))
        {
          DownloadQueueImpl.this.mDownloadProgressManager.cleanup();
          DownloadQueueImpl.access$402(DownloadQueueImpl.this, null);
        }
      }
    }
  }

  private static enum UpdateListenerType
  {
    static
    {
      COMPLETE = new UpdateListenerType("COMPLETE", 1);
      PROGRESS = new UpdateListenerType("PROGRESS", 2);
      CANCEL = new UpdateListenerType("CANCEL", 3);
      START = new UpdateListenerType("START", 4);
      ERROR = new UpdateListenerType("ERROR", 5);
      UpdateListenerType[] arrayOfUpdateListenerType = new UpdateListenerType[6];
      arrayOfUpdateListenerType[0] = NOTIFICATION_CLICKED;
      arrayOfUpdateListenerType[1] = COMPLETE;
      arrayOfUpdateListenerType[2] = PROGRESS;
      arrayOfUpdateListenerType[3] = CANCEL;
      arrayOfUpdateListenerType[4] = START;
      arrayOfUpdateListenerType[5] = ERROR;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadQueueImpl
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils.persistence;

import android.os.Handler;
import android.os.Looper;
import com.google.android.finsky.utils.Sets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteThroughKeyValueStore
  implements KeyValueStore
{
  private static final ExecutorService sWriteThread = Executors.newSingleThreadExecutor();
  private final KeyValueStore mBackingStore;
  private Map<String, Map<String, String>> mDataMap = null;
  private final Handler mHandler;
  private Set<Runnable> mOnCompleteListeners = Sets.newHashSet();

  public WriteThroughKeyValueStore(KeyValueStore paramKeyValueStore)
  {
    this.mBackingStore = paramKeyValueStore;
    this.mHandler = new Handler(Looper.getMainLooper());
  }

  private void ensureOnMainThread()
  {
    if (Looper.myLooper() != Looper.getMainLooper())
      throw new IllegalStateException("Tried to access data off of the main thread.");
  }

  private void ensureReadyAndOnMainThread()
  {
    if (this.mDataMap == null)
      throw new IllegalStateException("Tried to access data before initializing.");
    ensureOnMainThread();
  }

  private void fetchAllFromBackingStoreAsync()
  {
    sWriteThread.submit(new Runnable()
    {
      public void run()
      {
        final Map localMap = WriteThroughKeyValueStore.this.mBackingStore.fetchAll();
        WriteThroughKeyValueStore.this.mHandler.post(new Runnable()
        {
          public void run()
          {
            WriteThroughKeyValueStore.this.handleDataLoaded(localMap);
          }
        });
      }
    });
  }

  private void handleDataLoaded(Map<String, Map<String, String>> paramMap)
  {
    this.mDataMap = paramMap;
    Iterator localIterator = this.mOnCompleteListeners.iterator();
    while (localIterator.hasNext())
      ((Runnable)localIterator.next()).run();
    this.mOnCompleteListeners.clear();
  }

  public void delete(final String paramString)
  {
    ensureReadyAndOnMainThread();
    this.mDataMap.remove(paramString);
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        WriteThroughKeyValueStore.this.mBackingStore.delete(paramString);
      }
    };
    sWriteThread.submit(local1);
  }

  public Map<String, Map<String, String>> fetchAll()
  {
    ensureReadyAndOnMainThread();
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.mDataMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, Collections.unmodifiableMap((Map)this.mDataMap.get(str)));
    }
    return Collections.unmodifiableMap(localHashMap);
  }

  public void forceSynchronousLoad()
  {
    ensureOnMainThread();
    this.mDataMap = this.mBackingStore.fetchAll();
    this.mOnCompleteListeners.clear();
  }

  public Map<String, String> get(String paramString)
  {
    ensureReadyAndOnMainThread();
    Map localMap1 = (Map)this.mDataMap.get(paramString);
    if (localMap1 != null);
    for (Map localMap2 = Collections.unmodifiableMap(localMap1); ; localMap2 = null)
      return localMap2;
  }

  public void load(Runnable paramRunnable)
  {
    ensureOnMainThread();
    if (this.mDataMap != null)
      this.mHandler.post(paramRunnable);
    while (true)
    {
      return;
      this.mOnCompleteListeners.add(paramRunnable);
      if (this.mOnCompleteListeners.size() == 1)
        fetchAllFromBackingStoreAsync();
    }
  }

  public void put(final String paramString, Map<String, String> paramMap)
  {
    ensureReadyAndOnMainThread();
    this.mDataMap.put(paramString, paramMap);
    Runnable local2 = new Runnable()
    {
      public void run()
      {
        WriteThroughKeyValueStore.this.mBackingStore.put(paramString, this.val$mapCopy);
      }
    };
    sWriteThread.submit(local2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore
 * JD-Core Version:    0.6.2
 */
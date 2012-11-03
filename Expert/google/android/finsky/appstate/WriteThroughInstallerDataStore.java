package com.google.android.finsky.appstate;

import android.os.Handler;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class WriteThroughInstallerDataStore
  implements InstallerDataStore
{
  private final InstallerDataStore mInMemoryStore;
  private boolean mIsLoaded;
  private Collection<Runnable> mLoadedCallbacks = new ArrayList();
  private final Handler mNotificationHandler;
  private final InstallerDataStore mPersistentStore;
  private final Handler mWriteThroughHandler;

  public WriteThroughInstallerDataStore(InstallerDataStore paramInstallerDataStore1, InstallerDataStore paramInstallerDataStore2, Handler paramHandler1, Handler paramHandler2)
  {
    this.mInMemoryStore = paramInstallerDataStore1;
    this.mPersistentStore = paramInstallerDataStore2;
    this.mWriteThroughHandler = paramHandler1;
    this.mNotificationHandler = paramHandler2;
  }

  public InstallerDataStore.InstallerData get(String paramString)
  {
    try
    {
      InstallerDataStore.InstallerData localInstallerData = this.mInMemoryStore.get(paramString);
      return localInstallerData;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Collection<InstallerDataStore.InstallerData> getAll()
  {
    try
    {
      Collection localCollection = this.mInMemoryStore.getAll();
      return localCollection;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean isLoaded()
  {
    try
    {
      boolean bool = this.mIsLoaded;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void load()
  {
    try
    {
      if (this.mIsLoaded)
        break label71;
      Iterator localIterator2 = this.mPersistentStore.getAll().iterator();
      while (localIterator2.hasNext())
      {
        InstallerDataStore.InstallerData localInstallerData = (InstallerDataStore.InstallerData)localIterator2.next();
        this.mInMemoryStore.put(localInstallerData);
      }
    }
    finally
    {
    }
    this.mIsLoaded = true;
    label71: Iterator localIterator1 = this.mLoadedCallbacks.iterator();
    while (localIterator1.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator1.next();
      if (localRunnable != null)
        this.mNotificationHandler.post(localRunnable);
    }
    this.mLoadedCallbacks.clear();
  }

  public boolean load(Runnable paramRunnable)
  {
    try
    {
      if (this.mIsLoaded)
        if (paramRunnable != null)
          this.mNotificationHandler.post(paramRunnable);
      for (boolean bool = true; ; bool = false)
      {
        return bool;
        this.mLoadedCallbacks.add(paramRunnable);
        if (this.mLoadedCallbacks.size() < 2)
          this.mWriteThroughHandler.post(new Runnable()
          {
            public void run()
            {
              WriteThroughInstallerDataStore.this.load();
            }
          });
      }
    }
    finally
    {
    }
  }

  public void put(final InstallerDataStore.InstallerData paramInstallerData)
  {
    try
    {
      this.mInMemoryStore.put(paramInstallerData);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.put(paramInstallerData);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAccount(final String paramString1, final String paramString2)
  {
    try
    {
      this.mInMemoryStore.setAccount(paramString1, paramString2);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setAccount(paramString1, paramString2);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAutoUpdate(final String paramString, final InstallerDataStore.AutoUpdateState paramAutoUpdateState)
  {
    try
    {
      this.mInMemoryStore.setAutoUpdate(paramString, paramAutoUpdateState);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setAutoUpdate(paramString, paramAutoUpdateState);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setContinueUrl(final String paramString1, final String paramString2)
  {
    try
    {
      this.mInMemoryStore.setContinueUrl(paramString1, paramString2);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setContinueUrl(paramString1, paramString2);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDeliveryData(final String paramString, final AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, final long paramLong)
  {
    try
    {
      this.mInMemoryStore.setDeliveryData(paramString, paramAndroidAppDeliveryData, paramLong);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setDeliveryData(paramString, paramAndroidAppDeliveryData, paramLong);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDesiredVersion(final String paramString, final int paramInt)
  {
    try
    {
      this.mInMemoryStore.setDesiredVersion(paramString, paramInt);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setDesiredVersion(paramString, paramInt);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFirstDownloadMs(final String paramString, final long paramLong)
  {
    try
    {
      this.mInMemoryStore.setFirstDownloadMs(paramString, paramLong);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setFirstDownloadMs(paramString, paramLong);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFlags(final String paramString, final int paramInt)
  {
    try
    {
      this.mInMemoryStore.setFlags(paramString, paramInt);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setFlags(paramString, paramInt);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setInstallerState(final String paramString1, final int paramInt, final String paramString2)
  {
    try
    {
      this.mInMemoryStore.setInstallerState(paramString1, paramInt, paramString2);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setInstallerState(paramString1, paramInt, paramString2);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setLastNotifiedVersion(final String paramString, final int paramInt)
  {
    try
    {
      this.mInMemoryStore.setLastNotifiedVersion(paramString, paramInt);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setLastNotifiedVersion(paramString, paramInt);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setReferrer(final String paramString1, final String paramString2)
  {
    try
    {
      this.mInMemoryStore.setReferrer(paramString1, paramString2);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setReferrer(paramString1, paramString2);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setTitle(final String paramString1, final String paramString2)
  {
    try
    {
      this.mInMemoryStore.setTitle(paramString1, paramString2);
      this.mWriteThroughHandler.post(new Runnable()
      {
        public void run()
        {
          WriteThroughInstallerDataStore.this.mPersistentStore.setTitle(paramString1, paramString2);
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.WriteThroughInstallerDataStore
 * JD-Core Version:    0.6.2
 */
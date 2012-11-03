package com.google.android.finsky.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import com.google.android.play.IUserContentService;
import com.google.android.play.IUserContentService.Stub;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FetchConsumptionDataService extends IntentService
{
  private static final ExecutorService sFetchThread = Executors.newSingleThreadExecutor();

  public FetchConsumptionDataService()
  {
    super(FetchConsumptionDataService.class.getSimpleName());
  }

  public static void fetch(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, FetchConsumptionDataService.class);
    localIntent.putExtra("backendId", paramInt);
    localIntent.setAction(FetchConsumptionDataService.class.getSimpleName() + ":" + paramInt);
    PendingIntent localPendingIntent = PendingIntent.getService(paramContext, 0, localIntent, 0);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, 500L + System.currentTimeMillis(), localPendingIntent);
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("backendId", 0);
    String str = IntentUtils.getPackageName(i);
    Semaphore localSemaphore = new Semaphore(0);
    FinskyLog.d("Starting ServiceConnection to consumption app: %s", new Object[] { str });
    Intent localIntent = new Intent("com.google.android.play.IUserContentService.BIND");
    localIntent.setPackage(str);
    ConsumptionAppServiceConn localConsumptionAppServiceConn = new ConsumptionAppServiceConn(i, localSemaphore);
    bindService(localIntent, localConsumptionAppServiceConn, 1);
    long l = ((Long)G.consumptionAppTimeoutMs.get()).longValue();
    try
    {
      if (!localSemaphore.tryAcquire(l, TimeUnit.MILLISECONDS))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(i);
        arrayOfObject[1] = Long.valueOf(l);
        FinskyLog.e("Service connection for %d timed out after %d", arrayOfObject);
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        FinskyLog.d("Interrupted while connecting to remote service", new Object[0]);
        unbindService(localConsumptionAppServiceConn);
      }
    }
    finally
    {
      unbindService(localConsumptionAppServiceConn);
    }
  }

  private class ConsumptionAppServiceConn
    implements ServiceConnection
  {
    private final int mBackendId;
    private final Semaphore mLock;
    private IUserContentService mService;

    public ConsumptionAppServiceConn(int paramSemaphore, Semaphore arg3)
    {
      this.mBackendId = paramSemaphore;
      Object localObject;
      this.mLock = localObject;
    }

    private void getDataFromService()
    {
      try
      {
        List localList = this.mService.getDocuments(0, 20);
        if ((localList != null) && (localList.size() >= 0))
        {
          Object[] arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = Integer.valueOf(localList.size());
          arrayOfObject2[1] = Integer.valueOf(this.mBackendId);
          FinskyLog.d("retrieved %d documents from [%d]", arrayOfObject2);
          ConsumptionAppDataCache localConsumptionAppDataCache = ConsumptionAppDataCache.get();
          FetchConsumptionDataService localFetchConsumptionDataService = FetchConsumptionDataService.this;
          localConsumptionAppDataCache.setConsumptionAppData(localFetchConsumptionDataService, this.mBackendId, localList);
          ConsumptionAppDocList localConsumptionAppDocList = ConsumptionAppDocList.createFromBundles(this.mBackendId, localList);
          File localFile = ConsumptionAppDataCache.getCacheFile(localFetchConsumptionDataService, localConsumptionAppDocList.getBackend());
          try
          {
            ParcelUtils.writeToDisk(localFile, localConsumptionAppDocList);
            return;
          }
          catch (IOException localIOException)
          {
            while (true)
            {
              Object[] arrayOfObject3 = new Object[2];
              arrayOfObject3[0] = Integer.valueOf(this.mBackendId);
              arrayOfObject3[1] = localIOException.getMessage();
              FinskyLog.e("Failed to cache %s: %s", arrayOfObject3);
            }
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
        {
          localRemoteException.printStackTrace();
          continue;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(this.mBackendId);
          FinskyLog.d("Error fetching data from service for backend=[%s]", arrayOfObject1);
        }
      }
    }

    public void onServiceConnected(ComponentName paramComponentName, final IBinder paramIBinder)
    {
      FetchConsumptionDataService.sFetchThread.execute(new Runnable()
      {
        public void run()
        {
          FetchConsumptionDataService.ConsumptionAppServiceConn.access$002(FetchConsumptionDataService.ConsumptionAppServiceConn.this, IUserContentService.Stub.asInterface(paramIBinder));
          FetchConsumptionDataService.ConsumptionAppServiceConn.this.getDataFromService();
          FetchConsumptionDataService.ConsumptionAppServiceConn.this.mLock.release();
        }
      });
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      this.mService = null;
      this.mLock.release();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.FetchConsumptionDataService
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.AppStatesReplicator;
import com.google.android.finsky.appstate.AppStatesReplicator.Listener;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.library.LibraryReplicators.Listener;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class ContentSyncService extends Service
{
  private static final Intent WAKE_ME_UP = new Intent(FinskyApp.get(), ContentSyncService.class);
  private final AppStatesReplicator mAppStatesReplicator = FinskyApp.get().getAppStatesReplicator();
  private boolean mReplicating;
  private AppStatesReplicator.Listener mReplicationListener = new AppStatesReplicator.Listener()
  {
    public void onFinished(boolean paramAnonymousBoolean)
    {
      ContentSyncService.access$302(ContentSyncService.this, false);
      if (paramAnonymousBoolean)
      {
        FinskyLog.d("Installation state replication succeeded.", new Object[0]);
        FinskyPreferences.installationReplicationRetries.remove();
        if (ContentSyncService.this.mReplicationRequested)
        {
          FinskyLog.d("Another replication has been requested, rescheduling.", new Object[0]);
          ContentSyncService.access$200();
        }
      }
      while (true)
      {
        ContentSyncService.this.stopSelf();
        return;
        FinskyLog.d("Installation state replication failed.", new Object[0]);
        int i = 1 + ((Integer)FinskyPreferences.installationReplicationRetries.get()).intValue();
        if (i > 5)
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(i);
          FinskyLog.d("Giving up after %d failures.", arrayOfObject2);
          FinskyPreferences.installationReplicationRetries.remove();
        }
        else
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(i);
          FinskyLog.d("Scheduling replication attempt %d.", arrayOfObject1);
          FinskyPreferences.installationReplicationRetries.put(Integer.valueOf(i));
          ContentSyncService.access$200();
        }
      }
    }
  };
  private boolean mReplicationRequested;

  public static Facade get()
  {
    return new Facade()
    {
      public void scheduleSync()
      {
        ContentSyncService.access$200();
      }
    };
  }

  private static void scheduleReplication()
  {
    if (((Integer)FinskyPreferences.installationReplicationRetries.get()).intValue() == 0);
    for (long l = 15000L; ; l = 20000L)
    {
      ((AlarmManager)FinskyApp.get().getSystemService("alarm")).set(0, l + System.currentTimeMillis(), PendingIntent.getService(FinskyApp.get(), 0, WAKE_ME_UP, 0));
      return;
    }
  }

  public static void setupListeners(LibraryReplicators paramLibraryReplicators, PackageMonitorReceiver paramPackageMonitorReceiver)
  {
    paramLibraryReplicators.addListener(new LibraryReplicators.Listener()
    {
      public void onMutationsApplied(AccountLibrary paramAnonymousAccountLibrary, String paramAnonymousString)
      {
        if (AccountLibrary.LIBRARY_ID_APPS.equals(paramAnonymousString))
        {
          FinskyLog.d("App library has changed, requesting content sync.", new Object[0]);
          ContentSyncService.get().scheduleSync();
        }
      }
    });
    paramPackageMonitorReceiver.attach(new PackageMonitorReceiver.PackageStatusListener()
    {
      public void onPackageAdded(String paramAnonymousString)
      {
        ContentSyncService.get().scheduleSync();
      }

      public void onPackageAvailabilityChanged(String[] paramAnonymousArrayOfString, boolean paramAnonymousBoolean)
      {
        ContentSyncService.get().scheduleSync();
      }

      public void onPackageChanged(String paramAnonymousString)
      {
      }

      public void onPackageFirstLaunch(String paramAnonymousString)
      {
      }

      public void onPackageRemoved(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        ContentSyncService.get().scheduleSync();
      }
    });
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.mReplicating)
      this.mReplicationRequested = true;
    while (true)
    {
      return 2;
      this.mReplicationRequested = false;
      this.mAppStatesReplicator.load(new Runnable()
      {
        public void run()
        {
          ContentSyncService.this.mAppStatesReplicator.replicate(ContentSyncService.this.mReplicationListener);
        }
      });
    }
  }

  public static abstract interface Facade
  {
    public abstract void scheduleSync();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.ContentSyncService
 * JD-Core Version:    0.6.2
 */
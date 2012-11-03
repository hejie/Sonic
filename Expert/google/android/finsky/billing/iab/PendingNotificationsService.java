package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.os.SystemClock;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.VendingPreferences;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.AppDataProto;
import com.google.android.vending.remoting.protos.VendingProtos.DataMessageProto;
import com.google.android.vending.remoting.protos.VendingProtos.PendingNotificationsProto;
import java.util.Iterator;
import java.util.List;

public class PendingNotificationsService extends Service
{
  private static String ACTION_ALARM = "action_alarm";
  public static String ACTION_RESTART_ALARM = "action_restart_alarm";

  public static void cancelMarketAlarm(Context paramContext, String paramString)
  {
    FinskyLog.d("Canceling alarm for account=%s", new Object[] { paramString });
    VendingPreferences.getMarketAlarmStartTime(paramString).put(Long.valueOf(0L));
    PendingIntent localPendingIntent = createPendingIntentForMarketAlarm(paramContext, paramString);
    ((AlarmManager)paramContext.getSystemService("alarm")).cancel(localPendingIntent);
  }

  private static PendingIntent createPendingIntentForMarketAlarm(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(ACTION_ALARM);
    localIntent.setClass(paramContext, PendingNotificationsService.class);
    localIntent.putExtra("account", paramString);
    localIntent.setData(Uri.fromParts("vendingpending", paramString, null));
    return PendingIntent.getService(paramContext, 0, localIntent, 1073741824);
  }

  private void handleAlarm(final String paramString)
  {
    FinskyApp.get().getVendingApi(paramString).checkForPendingNotifications(new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.d("CheckForPendingNotifications failed: error=%s", new Object[] { paramAnonymousVolleyError });
        PendingNotificationsService.rescheduleMarketAlarm(FinskyApp.get(), paramString);
      }
    });
  }

  public static boolean handlePendingNotifications(Context paramContext, String paramString, VendingProtos.PendingNotificationsProto paramPendingNotificationsProto, boolean paramBoolean)
  {
    boolean bool = false;
    int i;
    Iterator localIterator1;
    if (paramPendingNotificationsProto.hasNextCheckMillis())
    {
      long l = paramPendingNotificationsProto.getNextCheckMillis();
      if (FinskyLog.DEBUG)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Long.valueOf(l);
        FinskyLog.v("Got next_check_millis=%d", arrayOfObject2);
      }
      if (l > 0L)
      {
        setMarketAlarm(FinskyApp.get(), paramString, l);
        bool = true;
      }
    }
    else
    {
      i = 0;
      localIterator1 = paramPendingNotificationsProto.getNotificationList().iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext())
        break label270;
      VendingProtos.DataMessageProto localDataMessageProto = (VendingProtos.DataMessageProto)localIterator1.next();
      String str = localDataMessageProto.getCategory();
      if (FinskyLog.DEBUG)
        FinskyLog.v("Processing pending notification with category=%s", new Object[] { str });
      Intent localIntent1 = new Intent("com.google.android.c2dm.intent.RECEIVE");
      localIntent1.addCategory(str);
      Intent localIntent2 = IntentUtils.createIntentForReceiver(paramContext.getPackageManager(), FinskyApp.get().getPackageName(), localIntent1);
      if (localIntent2 == null)
      {
        FinskyLog.w("Cannot find receiver for intent category: %s", new Object[] { str });
        continue;
        if (!paramBoolean)
          break;
        cancelMarketAlarm(FinskyApp.get(), paramString);
        bool = true;
        break;
      }
      Iterator localIterator2 = localDataMessageProto.getAppDataList().iterator();
      while (localIterator2.hasNext())
      {
        VendingProtos.AppDataProto localAppDataProto = (VendingProtos.AppDataProto)localIterator2.next();
        localIntent2.putExtra(localAppDataProto.getKey(), localAppDataProto.getValue());
      }
      paramContext.sendOrderedBroadcast(localIntent2, null);
      bool = true;
      i++;
    }
    label270: if (FinskyLog.DEBUG)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(i);
      FinskyLog.v("Handled %d notifications.", arrayOfObject1);
    }
    return bool;
  }

  private static void rescheduleMarketAlarm(final Context paramContext, String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        long l1 = ((Long)G.vendingAlarmExpirationTimeoutMs.get()).longValue();
        long l2 = 2L * ((Long)VendingPreferences.getMarketAlarmTimeout(this.val$account).get()).longValue();
        if (l2 > l1);
        while (true)
        {
          return;
          long l3 = Math.max(Math.min(l2, ((Long)G.vendingAlarmMaxTimeoutMs.get()).longValue()), ((Long)G.vendingAlarmMinTimeoutMs.get()).longValue());
          PendingNotificationsService.setMarketAlarm(paramContext, this.val$account, l3);
        }
      }
    }).start();
  }

  private void restartAlarmsAfterBoot()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Account[] arrayOfAccount = AccountHandler.getAccounts(PendingNotificationsService.this);
        int i = arrayOfAccount.length;
        for (int j = 0; ; j++)
        {
          String str;
          long l1;
          if (j < i)
          {
            str = arrayOfAccount[j].name;
            if (FinskyLog.DEBUG)
              FinskyLog.v("Checking for pending alarms for account=%s", new Object[] { str });
            l1 = ((Long)VendingPreferences.getMarketAlarmStartTime(str).get()).longValue();
            if (l1 != 0L)
              break label85;
            if (FinskyLog.DEBUG)
              FinskyLog.v("No pending alarm.", new Object[0]);
          }
          return;
          label85: long l2 = ((Long)VendingPreferences.getMarketAlarmTimeout(str).get()).longValue();
          long l3 = System.currentTimeMillis();
          long l4 = l3 - l1;
          if (l4 < 0L)
          {
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = Long.valueOf(l3);
            arrayOfObject2[1] = Long.valueOf(l1);
            FinskyLog.e("Current time is wrong? current time=%d, alarm start time=%d", arrayOfObject2);
            l4 = 0L;
          }
          long l5 = l2 - l4;
          if (l5 < 20000L)
          {
            if (FinskyLog.DEBUG)
            {
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = Long.valueOf(l5);
              FinskyLog.v("remaining=%d, delaying alarm for a while.", arrayOfObject1);
            }
            l5 = 20000L;
          }
          PendingNotificationsService.setMarketAlarm(PendingNotificationsService.this, str, l5, l3);
        }
      }
    }).start();
  }

  public static void setMarketAlarm(Context paramContext, String paramString, long paramLong)
  {
    setMarketAlarm(paramContext, paramString, paramLong, System.currentTimeMillis());
  }

  public static void setMarketAlarm(Context paramContext, String paramString, long paramLong1, long paramLong2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Long.valueOf(paramLong1);
    FinskyLog.d("Setting alarm for account=%s, duration=%d", arrayOfObject);
    PendingIntent localPendingIntent = createPendingIntentForMarketAlarm(paramContext, paramString);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(3, paramLong1 + SystemClock.elapsedRealtime(), localPendingIntent);
    VendingPreferences.getMarketAlarmStartTime(paramString).put(Long.valueOf(paramLong2));
    VendingPreferences.getMarketAlarmTimeout(paramString).put(Long.valueOf(paramLong1));
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null)
      stopSelf();
    while (true)
    {
      return 2;
      String str = paramIntent.getAction();
      if (str.equals(ACTION_ALARM))
      {
        handleAlarm(paramIntent.getStringExtra("account"));
      }
      else if (str.equals(ACTION_RESTART_ALARM))
      {
        restartAlarmsAfterBoot();
      }
      else
      {
        FinskyLog.e("unexpected action: %s", new Object[] { str });
        stopSelf();
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.iab.PendingNotificationsService
 * JD-Core Version:    0.6.2
 */
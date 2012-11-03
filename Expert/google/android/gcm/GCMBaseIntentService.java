package com.google.android.gcm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.util.Log;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class GCMBaseIntentService extends IntentService
{
  private static final Object LOCK = GCMBaseIntentService.class;
  private static final int MAX_BACKOFF_MS = (int)TimeUnit.SECONDS.toMillis(3600L);
  private static final String TOKEN = Long.toBinaryString(sRandom.nextLong());
  private static int sCounter = 0;
  private static final Random sRandom = new Random();
  private static PowerManager.WakeLock sWakeLock;
  private final String[] mSenderIds;

  protected GCMBaseIntentService()
  {
    this(getName("DynamicSenderIds"), null);
  }

  private GCMBaseIntentService(String paramString, String[] paramArrayOfString)
  {
    super(paramString);
    this.mSenderIds = paramArrayOfString;
  }

  protected GCMBaseIntentService(String[] paramArrayOfString)
  {
    this(getName(paramArrayOfString), paramArrayOfString);
  }

  private static String getName(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("GCMIntentService-").append(paramString).append("-");
    int i = 1 + sCounter;
    sCounter = i;
    String str = i;
    Log.v("GCMBaseIntentService", "Intent service name: " + str);
    return str;
  }

  private static String getName(String[] paramArrayOfString)
  {
    return getName(GCMRegistrar.getFlatSenderIds(paramArrayOfString));
  }

  private void handleRegistration(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("registration_id");
    String str2 = paramIntent.getStringExtra("error");
    String str3 = paramIntent.getStringExtra("unregistered");
    Log.d("GCMBaseIntentService", "handleRegistration: registrationId = " + str1 + ", error = " + str2 + ", unregistered = " + str3);
    if (str1 != null)
    {
      GCMRegistrar.resetBackoff(paramContext);
      GCMRegistrar.setRegistrationId(paramContext, str1);
      onRegistered(paramContext, str1);
    }
    while (true)
    {
      return;
      if (str3 != null)
      {
        GCMRegistrar.resetBackoff(paramContext);
        onUnregistered(paramContext, GCMRegistrar.clearRegistrationId(paramContext));
      }
      else
      {
        Log.d("GCMBaseIntentService", "Registration error: " + str2);
        if ("SERVICE_NOT_AVAILABLE".equals(str2))
        {
          if (onRecoverableError(paramContext, str2))
          {
            int i = GCMRegistrar.getBackoff(paramContext);
            int j = i / 2 + sRandom.nextInt(i);
            Log.d("GCMBaseIntentService", "Scheduling registration retry, backoff = " + j + " (" + i + ")");
            Intent localIntent = new Intent("com.google.android.gcm.intent.RETRY");
            localIntent.putExtra("token", TOKEN);
            PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 0, localIntent, 0);
            ((AlarmManager)paramContext.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + j, localPendingIntent);
            if (i < MAX_BACKOFF_MS)
              GCMRegistrar.setBackoff(paramContext, i * 2);
          }
          else
          {
            Log.d("GCMBaseIntentService", "Not retrying failed operation");
          }
        }
        else
          onError(paramContext, str2);
      }
    }
  }

  static void runIntentInService(Context paramContext, Intent paramIntent, String paramString)
  {
    synchronized (LOCK)
    {
      if (sWakeLock == null)
        sWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "GCM_LIB");
      Log.v("GCMBaseIntentService", "Acquiring wakelock");
      sWakeLock.acquire();
      paramIntent.setClassName(paramContext, paramString);
      paramContext.startService(paramIntent);
      return;
    }
  }

  protected String[] getSenderIds(Context paramContext)
  {
    if (this.mSenderIds == null)
      throw new IllegalStateException("sender id not set on constructor");
    return this.mSenderIds;
  }

  protected void onDeletedMessages(Context paramContext, int paramInt)
  {
  }

  protected abstract void onError(Context paramContext, String paramString);

  // ERROR //
  public final void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 264	com/google/android/gcm/GCMBaseIntentService:getApplicationContext	()Landroid/content/Context;
    //   4: astore 7
    //   6: aload_1
    //   7: invokevirtual 267	android/content/Intent:getAction	()Ljava/lang/String;
    //   10: astore 8
    //   12: aload 8
    //   14: ldc_w 269
    //   17: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   20: ifeq +48 -> 68
    //   23: aload 7
    //   25: invokestatic 272	com/google/android/gcm/GCMRegistrar:setRetryBroadcastReceiver	(Landroid/content/Context;)V
    //   28: aload_0
    //   29: aload 7
    //   31: aload_1
    //   32: invokespecial 274	com/google/android/gcm/GCMBaseIntentService:handleRegistration	(Landroid/content/Context;Landroid/content/Intent;)V
    //   35: getstatic 21	com/google/android/gcm/GCMBaseIntentService:LOCK	Ljava/lang/Object;
    //   38: astore 10
    //   40: aload 10
    //   42: monitorenter
    //   43: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   46: ifnull +362 -> 408
    //   49: ldc 94
    //   51: ldc_w 276
    //   54: invokestatic 102	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   57: pop
    //   58: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   61: invokevirtual 279	android/os/PowerManager$WakeLock:release	()V
    //   64: aload 10
    //   66: monitorexit
    //   67: return
    //   68: aload 8
    //   70: ldc_w 281
    //   73: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   76: ifeq +191 -> 267
    //   79: aload_1
    //   80: ldc_w 283
    //   83: invokevirtual 116	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   86: astore 19
    //   88: aload 19
    //   90: ifnull +167 -> 257
    //   93: aload 19
    //   95: ldc_w 285
    //   98: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +126 -> 227
    //   104: aload_1
    //   105: ldc_w 287
    //   108: invokevirtual 116	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 21
    //   113: aload 21
    //   115: ifnull -80 -> 35
    //   118: aload 21
    //   120: invokestatic 293	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   123: istore 24
    //   125: ldc 94
    //   127: new 76	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 295
    //   137: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: iload 24
    //   142: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   145: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokestatic 102	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   151: pop
    //   152: aload_0
    //   153: aload 7
    //   155: iload 24
    //   157: invokevirtual 297	com/google/android/gcm/GCMBaseIntentService:onDeletedMessages	(Landroid/content/Context;I)V
    //   160: goto -125 -> 35
    //   163: astore 22
    //   165: ldc 94
    //   167: new 76	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   174: ldc_w 299
    //   177: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload 21
    //   182: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   191: pop
    //   192: goto -157 -> 35
    //   195: astore_2
    //   196: getstatic 21	com/google/android/gcm/GCMBaseIntentService:LOCK	Ljava/lang/Object;
    //   199: astore_3
    //   200: aload_3
    //   201: monitorenter
    //   202: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   205: ifnull +223 -> 428
    //   208: ldc 94
    //   210: ldc_w 276
    //   213: invokestatic 102	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   216: pop
    //   217: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   220: invokevirtual 279	android/os/PowerManager$WakeLock:release	()V
    //   223: aload_3
    //   224: monitorexit
    //   225: aload_2
    //   226: athrow
    //   227: ldc 94
    //   229: new 76	java/lang/StringBuilder
    //   232: dup
    //   233: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   236: ldc_w 304
    //   239: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: aload 19
    //   244: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   250: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   253: pop
    //   254: goto -219 -> 35
    //   257: aload_0
    //   258: aload 7
    //   260: aload_1
    //   261: invokevirtual 307	com/google/android/gcm/GCMBaseIntentService:onMessage	(Landroid/content/Context;Landroid/content/Intent;)V
    //   264: goto -229 -> 35
    //   267: aload 8
    //   269: ldc 178
    //   271: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   274: ifeq -239 -> 35
    //   277: aload_1
    //   278: ldc 181
    //   280: invokevirtual 116	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   283: astore 9
    //   285: getstatic 56	com/google/android/gcm/GCMBaseIntentService:TOKEN	Ljava/lang/String;
    //   288: aload 9
    //   290: invokevirtual 158	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   293: ifne +85 -> 378
    //   296: ldc 94
    //   298: new 76	java/lang/StringBuilder
    //   301: dup
    //   302: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   305: ldc_w 309
    //   308: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: aload 9
    //   313: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   322: pop
    //   323: getstatic 21	com/google/android/gcm/GCMBaseIntentService:LOCK	Ljava/lang/Object;
    //   326: astore 15
    //   328: aload 15
    //   330: monitorenter
    //   331: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   334: ifnull +32 -> 366
    //   337: ldc 94
    //   339: ldc_w 276
    //   342: invokestatic 102	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   345: pop
    //   346: getstatic 223	com/google/android/gcm/GCMBaseIntentService:sWakeLock	Landroid/os/PowerManager$WakeLock;
    //   349: invokevirtual 279	android/os/PowerManager$WakeLock:release	()V
    //   352: aload 15
    //   354: monitorexit
    //   355: goto -288 -> 67
    //   358: astore 16
    //   360: aload 15
    //   362: monitorexit
    //   363: aload 16
    //   365: athrow
    //   366: ldc 94
    //   368: ldc_w 311
    //   371: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   374: pop
    //   375: goto -23 -> 352
    //   378: aload 7
    //   380: invokestatic 315	com/google/android/gcm/GCMRegistrar:isRegistered	(Landroid/content/Context;)Z
    //   383: ifeq +11 -> 394
    //   386: aload 7
    //   388: invokestatic 318	com/google/android/gcm/GCMRegistrar:internalUnregister	(Landroid/content/Context;)V
    //   391: goto -356 -> 35
    //   394: aload 7
    //   396: aload_0
    //   397: aload 7
    //   399: invokevirtual 320	com/google/android/gcm/GCMBaseIntentService:getSenderIds	(Landroid/content/Context;)[Ljava/lang/String;
    //   402: invokestatic 324	com/google/android/gcm/GCMRegistrar:internalRegister	(Landroid/content/Context;[Ljava/lang/String;)V
    //   405: goto -370 -> 35
    //   408: ldc 94
    //   410: ldc_w 311
    //   413: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   416: pop
    //   417: goto -353 -> 64
    //   420: astore 11
    //   422: aload 10
    //   424: monitorexit
    //   425: aload 11
    //   427: athrow
    //   428: ldc 94
    //   430: ldc_w 311
    //   433: invokestatic 302	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   436: pop
    //   437: goto -214 -> 223
    //   440: astore 4
    //   442: aload_3
    //   443: monitorexit
    //   444: aload 4
    //   446: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   118	160	163	java/lang/NumberFormatException
    //   0	35	195	finally
    //   68	113	195	finally
    //   118	160	195	finally
    //   165	192	195	finally
    //   227	323	195	finally
    //   378	405	195	finally
    //   331	363	358	finally
    //   366	375	358	finally
    //   43	67	420	finally
    //   408	425	420	finally
    //   202	225	440	finally
    //   428	444	440	finally
  }

  protected abstract void onMessage(Context paramContext, Intent paramIntent);

  protected boolean onRecoverableError(Context paramContext, String paramString)
  {
    return true;
  }

  protected abstract void onRegistered(Context paramContext, String paramString);

  protected abstract void onUnregistered(Context paramContext, String paramString);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.gcm.GCMBaseIntentService
 * JD-Core Version:    0.6.2
 */
package com.google.android.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;
import java.sql.Timestamp;

public final class GCMRegistrar
{
  private static GCMBroadcastReceiver sRetryReceiver;
  private static String sRetryReceiverClassName;

  private GCMRegistrar()
  {
    throw new UnsupportedOperationException();
  }

  public static void checkDevice(Context paramContext)
  {
    int i = Build.VERSION.SDK_INT;
    if (i < 8)
      throw new UnsupportedOperationException("Device must be at least API Level 8 (instead of " + i + ")");
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo("com.google.android.gsf", 0);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    throw new UnsupportedOperationException("Device does not have package com.google.android.gsf");
  }

  static String clearRegistrationId(Context paramContext)
  {
    return setRegistrationId(paramContext, "");
  }

  private static int getAppVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException("Coult not get package name: " + localNameNotFoundException);
    }
  }

  static int getBackoff(Context paramContext)
  {
    return getGCMPreferences(paramContext).getInt("backoff_ms", 3000);
  }

  static String getFlatSenderIds(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      throw new IllegalArgumentException("No senderIds");
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfString[0]);
    for (int i = 1; i < paramArrayOfString.length; i++)
      localStringBuilder.append(',').append(paramArrayOfString[i]);
    return localStringBuilder.toString();
  }

  private static SharedPreferences getGCMPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.google.android.gcm", 0);
  }

  public static long getRegisterOnServerLifespan(Context paramContext)
  {
    return getGCMPreferences(paramContext).getLong("onServerLifeSpan", 604800000L);
  }

  public static String getRegistrationId(Context paramContext)
  {
    SharedPreferences localSharedPreferences = getGCMPreferences(paramContext);
    String str = localSharedPreferences.getString("regId", "");
    int i = localSharedPreferences.getInt("appVersion", -2147483648);
    int j = getAppVersion(paramContext);
    if ((i != -2147483648) && (i != j))
    {
      Log.v("GCMRegistrar", "App version changed from " + i + " to " + j + "; resetting registration id");
      clearRegistrationId(paramContext);
      str = "";
    }
    return str;
  }

  static void internalRegister(Context paramContext, String[] paramArrayOfString)
  {
    String str = getFlatSenderIds(paramArrayOfString);
    Log.v("GCMRegistrar", "Registering app " + paramContext.getPackageName() + " of senders " + str);
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage("com.google.android.gsf");
    localIntent.putExtra("app", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    localIntent.putExtra("sender", str);
    paramContext.startService(localIntent);
  }

  static void internalUnregister(Context paramContext)
  {
    Log.v("GCMRegistrar", "Unregistering app " + paramContext.getPackageName());
    Intent localIntent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
    localIntent.setPackage("com.google.android.gsf");
    localIntent.putExtra("app", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    paramContext.startService(localIntent);
  }

  public static boolean isRegistered(Context paramContext)
  {
    if (getRegistrationId(paramContext).length() > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isRegisteredOnServer(Context paramContext)
  {
    SharedPreferences localSharedPreferences = getGCMPreferences(paramContext);
    boolean bool = localSharedPreferences.getBoolean("onServer", false);
    Log.v("GCMRegistrar", "Is registered on server: " + bool);
    if (bool)
    {
      long l = localSharedPreferences.getLong("onServerExpirationTime", -1L);
      if (System.currentTimeMillis() > l)
      {
        Log.v("GCMRegistrar", "flag expired on: " + new Timestamp(l));
        bool = false;
      }
    }
    return bool;
  }

  public static void register(Context paramContext, String[] paramArrayOfString)
  {
    resetBackoff(paramContext);
    internalRegister(paramContext, paramArrayOfString);
  }

  static void resetBackoff(Context paramContext)
  {
    Log.d("GCMRegistrar", "resetting backoff for " + paramContext.getPackageName());
    setBackoff(paramContext, 3000);
  }

  static void setBackoff(Context paramContext, int paramInt)
  {
    SharedPreferences.Editor localEditor = getGCMPreferences(paramContext).edit();
    localEditor.putInt("backoff_ms", paramInt);
    localEditor.commit();
  }

  public static void setRegisteredOnServer(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getGCMPreferences(paramContext).edit();
    localEditor.putBoolean("onServer", paramBoolean);
    long l = getRegisterOnServerLifespan(paramContext) + System.currentTimeMillis();
    Log.v("GCMRegistrar", "Setting registeredOnServer status as " + paramBoolean + " until " + new Timestamp(l));
    localEditor.putLong("onServerExpirationTime", l);
    localEditor.commit();
  }

  static String setRegistrationId(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = getGCMPreferences(paramContext);
    String str = localSharedPreferences.getString("regId", "");
    int i = getAppVersion(paramContext);
    Log.v("GCMRegistrar", "Saving regId on app version " + i);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    localEditor.putString("regId", paramString);
    localEditor.putInt("appVersion", i);
    localEditor.commit();
    return str;
  }

  static void setRetryBroadcastReceiver(Context paramContext)
  {
    try
    {
      if (sRetryReceiver == null)
      {
        if (sRetryReceiverClassName != null)
          break label108;
        Log.e("GCMRegistrar", "internal error: retry receiver class not set yet");
        sRetryReceiver = new GCMBroadcastReceiver();
      }
      while (true)
      {
        String str1 = paramContext.getPackageName();
        IntentFilter localIntentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
        localIntentFilter.addCategory(str1);
        String str2 = str1 + ".permission.C2D_MESSAGE";
        Log.v("GCMRegistrar", "Registering receiver");
        paramContext.registerReceiver(sRetryReceiver, localIntentFilter, str2, null);
        return;
        try
        {
          label108: sRetryReceiver = (GCMBroadcastReceiver)Class.forName(sRetryReceiverClassName).newInstance();
        }
        catch (Exception localException)
        {
          Log.e("GCMRegistrar", "Could not create instance of " + sRetryReceiverClassName + ". Using " + GCMBroadcastReceiver.class.getName() + " directly.");
          sRetryReceiver = new GCMBroadcastReceiver();
        }
      }
    }
    finally
    {
    }
  }

  static void setRetryReceiverClassName(String paramString)
  {
    Log.v("GCMRegistrar", "Setting the name of retry receiver class to " + paramString);
    sRetryReceiverClassName = paramString;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.gcm.GCMRegistrar
 * JD-Core Version:    0.6.2
 */
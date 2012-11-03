package com.google.android.finsky.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;

public class ExpireLaunchUrlReceiver extends BroadcastReceiver
{
  public static void cancel(Context paramContext, String paramString)
  {
    if (!shouldSetAlarm(paramString));
    while (true)
    {
      return;
      ((AlarmManager)paramContext.getSystemService("alarm")).cancel(createPendingIntent(paramContext, paramString));
    }
  }

  private static PendingIntent createPendingIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ExpireLaunchUrlReceiver.class);
    localIntent.putExtra("package_name", paramString);
    return PendingIntent.getBroadcast(paramContext, 1, localIntent, 1073741824);
  }

  private static boolean hasContinueUrl(String paramString)
  {
    InstallerDataStore.InstallerData localInstallerData = FinskyApp.get().getInstallerDataStore().get(paramString);
    if ((localInstallerData != null) && (!TextUtils.isEmpty(localInstallerData.getContinueUrl())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void schedule(Context paramContext, String paramString)
  {
    if (!shouldSetAlarm(paramString));
    while (true)
    {
      return;
      long l = System.currentTimeMillis() + ((Long)G.continueUrlExpirationMs.get()).longValue();
      PendingIntent localPendingIntent = createPendingIntent(paramContext, paramString);
      ((AlarmManager)paramContext.getSystemService("alarm")).set(1, l, localPendingIntent);
    }
  }

  private static boolean shouldSetAlarm(String paramString)
  {
    if ((Build.VERSION.SDK_INT < 13) && (hasContinueUrl(paramString)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getExtras().getString("package_name");
    FinskyApp.get().getInstallerDataStore().setContinueUrl(str, null);
    FinskyLog.d("Removed expired continue URL", new Object[0]);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.ExpireLaunchUrlReceiver
 * JD-Core Version:    0.6.2
 */
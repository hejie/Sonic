package com.google.android.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GCMBroadcastReceiver extends BroadcastReceiver
{
  private static boolean mReceiverSet = false;

  static final String getDefaultIntentServiceClassName(Context paramContext)
  {
    return paramContext.getPackageName() + ".GCMIntentService";
  }

  protected String getGCMIntentServiceClassName(Context paramContext)
  {
    return getDefaultIntentServiceClassName(paramContext);
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.v("GCMBroadcastReceiver", "onReceive: " + paramIntent.getAction());
    if (!mReceiverSet)
    {
      mReceiverSet = true;
      String str2 = getClass().getName();
      if (!str2.equals(GCMBroadcastReceiver.class.getName()))
        GCMRegistrar.setRetryReceiverClassName(str2);
    }
    String str1 = getGCMIntentServiceClassName(paramContext);
    Log.v("GCMBroadcastReceiver", "GCM IntentService class: " + str1);
    GCMBaseIntentService.runIntentInService(paramContext, paramIntent, str1);
    setResult(-1, null, null);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.gcm.GCMBroadcastReceiver
 * JD-Core Version:    0.6.2
 */
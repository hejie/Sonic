package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;

public class ClearCacheReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramIntent.getAction();
    FinskyLog.d("Received %s. Clearing cache and exiting.", arrayOfObject);
    FinskyApp.get().clearCacheAsync(new Runnable()
    {
      public void run()
      {
        System.exit(0);
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.ClearCacheReceiver
 * JD-Core Version:    0.6.2
 */
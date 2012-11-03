package com.google.android.finsky.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import java.util.List;

public class ApplicationDismissedDeferrer
{
  private final Context mContext;

  public ApplicationDismissedDeferrer(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private boolean isContextPackageInBackground()
  {
    int i = 1;
    List localList = ((ActivityManager)this.mContext.getSystemService("activity")).getRecentTasks(i, 0);
    if (localList.size() == 0);
    while (true)
    {
      return i;
      ActivityManager.RecentTaskInfo localRecentTaskInfo = (ActivityManager.RecentTaskInfo)localList.get(0);
      if ((localRecentTaskInfo.baseIntent == null) || (localRecentTaskInfo.baseIntent.getComponent() == null) || (this.mContext.getPackageName().equals(localRecentTaskInfo.baseIntent.getComponent().getPackageName())))
        int j = 0;
    }
  }

  public void runOnApplicationClose(final Runnable paramRunnable, final int paramInt)
  {
    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
    {
      public void run()
      {
        if (ApplicationDismissedDeferrer.this.isContextPackageInBackground())
          paramRunnable.run();
        while (true)
        {
          return;
          ApplicationDismissedDeferrer.this.runOnApplicationClose(paramRunnable, paramInt);
        }
      }
    }
    , paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ApplicationDismissedDeferrer
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.receivers;

import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.download.DownloadReceiver;
import com.google.android.finsky.utils.FinskyLog;

public class DeclineAssetReceiver extends DownloadReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (!paramIntent.getAction().equals("com.google.android.c2dm.intent.RECEIVE"));
    while (true)
    {
      return;
      setResultCode(-1);
      if (!paramIntent.getStringExtra("from").equals("google.com"))
        continue;
      String str1 = paramIntent.getStringExtra("asset_package");
      String str2 = paramIntent.getStringExtra("decline_reason");
      int i = -1;
      if (str2 != null);
      try
      {
        int j = Integer.valueOf(str2).intValue();
        i = j;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = str1;
        arrayOfObject1[1] = Integer.valueOf(i);
        FinskyLog.d("Received PURCHASE_DECLINED tickle for %s reason=%d", arrayOfObject1);
        FinskyApp.get().getAnalytics().logTagAndPackage("install.declinedTickle", str1, String.valueOf(i));
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject2 = new Object[4];
        arrayOfObject2[0] = "cidi";
        arrayOfObject2[1] = str1;
        arrayOfObject2[2] = "r";
        arrayOfObject2[3] = String.valueOf(i);
        localFinskyEventLog.logTag("install.declinedTickle", arrayOfObject2);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
          FinskyLog.w("Non-numeric decline reason: %s", new Object[] { str2 });
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.DeclineAssetReceiver
 * JD-Core Version:    0.6.2
 */
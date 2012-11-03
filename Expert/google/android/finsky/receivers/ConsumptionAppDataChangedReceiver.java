package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.services.LoadConsumptionDataService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider;

public class ConsumptionAppDataChangedReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle == null)
      FinskyLog.d("Consumption app update contained no extras.", new Object[0]);
    while (true)
    {
      return;
      if (!localBundle.containsKey("Play.BackendId"))
      {
        FinskyLog.d("Consumption app did not specify backend id, ignoring!", new Object[0]);
      }
      else if (!localBundle.containsKey("Play.DataType"))
      {
        FinskyLog.d("Consumption app did not specify which list type was updated, ignoring!", new Object[0]);
      }
      else
      {
        int i = localBundle.getInt("Play.BackendId");
        int j = localBundle.getInt("Play.DataType");
        if (WidgetTypeMap.get(paramContext).getWidgets(NowPlayingWidgetProvider.class, WidgetUtils.translate(i)).length == 0)
        {
          if (FinskyLog.DEBUG)
          {
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(i);
            FinskyLog.v("Ignoring update because no widgets are listening to [%s].", arrayOfObject2);
          }
        }
        else if ((LoadConsumptionDataService.isBackendSupported(i)) && (LoadConsumptionDataService.isSupportedDataType(j)))
        {
          LoadConsumptionDataService.scheduleAlarmForUpdate(paramContext, new int[] { i });
        }
        else
        {
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = Integer.valueOf(i);
          arrayOfObject1[1] = Integer.valueOf(j);
          FinskyLog.d("Either backendId=[%d] or dataType=[%d] is not supported.", arrayOfObject1);
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.ConsumptionAppDataChangedReceiver
 * JD-Core Version:    0.6.2
 */
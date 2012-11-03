package com.google.android.finsky.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import java.io.File;

public class LoadConsumptionDataService extends IntentService
{
  public LoadConsumptionDataService()
  {
    super(LoadConsumptionDataService.class.getSimpleName());
  }

  public static boolean isBackendSupported(int paramInt)
  {
    switch (paramInt)
    {
    case 5:
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 6:
    }
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  public static boolean isSupportedDataType(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 0:
    }
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  private ConsumptionAppDocList readDataFromCache(Context paramContext, int paramInt)
  {
    Object localObject1 = new ConsumptionAppDocList(paramInt);
    File localFile = ConsumptionAppDataCache.getCacheFile(paramContext, paramInt);
    if (!localFile.exists())
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localFile.getAbsolutePath();
      FinskyLog.d("%s doesn't exist", arrayOfObject);
    }
    for (Object localObject2 = localObject1; ; localObject2 = localObject1)
    {
      return localObject2;
      ConsumptionAppDocList localConsumptionAppDocList = (ConsumptionAppDocList)ParcelUtils.readFromDisk(localFile);
      if (localConsumptionAppDocList != null)
        localObject1 = localConsumptionAppDocList;
    }
  }

  public static void scheduleAlarmForUpdate(Context paramContext, int[] paramArrayOfInt)
  {
    Intent localIntent = new Intent(paramContext, LoadConsumptionDataService.class);
    localIntent.setAction(String.valueOf(paramArrayOfInt.hashCode()));
    localIntent.putExtra("backendIds", paramArrayOfInt);
    paramContext.startService(localIntent);
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    ConsumptionAppDataCache localConsumptionAppDataCache = ConsumptionAppDataCache.get();
    int[] arrayOfInt1 = paramIntent.getIntArrayExtra("backendIds");
    int[] arrayOfInt2 = new int[arrayOfInt1.length];
    int i = arrayOfInt1.length;
    int j = 0;
    int k = 0;
    int n;
    int i1;
    if (j < i)
    {
      n = arrayOfInt1[j];
      ConsumptionAppDocList localConsumptionAppDocList = readDataFromCache(this, n);
      if (localConsumptionAppDocList.isEmpty())
        break label143;
      i1 = k + 1;
      arrayOfInt2[k] = n;
      localConsumptionAppDataCache.setConsumptionAppData(this, localConsumptionAppDocList, false);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(n);
      FinskyLog.d("Was able to read from cache for %d", arrayOfObject);
    }
    while (true)
    {
      FetchConsumptionDataService.fetch(this, n);
      j++;
      k = i1;
      break;
      for (int m = 0; m < k; m++)
        localConsumptionAppDataCache.updateWidgets(this, arrayOfInt2[m]);
      return;
      label143: i1 = k;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.LoadConsumptionDataService
 * JD-Core Version:    0.6.2
 */
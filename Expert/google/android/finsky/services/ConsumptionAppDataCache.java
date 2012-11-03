package com.google.android.finsky.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.widget.consumption.ConsumptionAppDocList;
import com.google.android.finsky.widget.consumption.NowPlayingWidgetProvider;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ConsumptionAppDataCache
{
  private static final String CACHE_FILE_PREFIX = ConsumptionAppDataCache.class.getSimpleName();
  static final DateFormat DATE_FORMAT = SimpleDateFormat.getDateTimeInstance();
  private static ConsumptionAppDataCache mInstance;
  private SparseArray<List<ConsumptionAppDoc>> mConsumptionAppData = new SparseArray();
  private SparseArray<Integer> mDataLoadState = new SparseArray();
  private final Handler mHandler = new Handler(Looper.getMainLooper());

  public static ConsumptionAppDataCache get()
  {
    if (mInstance == null)
      mInstance = new ConsumptionAppDataCache();
    return mInstance;
  }

  public static File getCacheFile(Context paramContext, int paramInt)
  {
    File localFile = new File(paramContext.getCacheDir(), "consumption");
    localFile.mkdirs();
    return new File(localFile, CACHE_FILE_PREFIX + "_" + paramInt + ".cache");
  }

  private int getDataStateForBackend(int paramInt)
  {
    Utils.ensureOnMainThread();
    if (this.mDataLoadState.get(paramInt) != null);
    for (int i = ((Integer)this.mDataLoadState.get(paramInt)).intValue(); ; i = 0)
      return i;
  }

  void filter(List<Bundle> paramList, int paramInt)
  {
    String str1 = (String)G.consumptionAppDataFilter.get();
    if ((!((Boolean)G.debugOptionsEnabled.get()).booleanValue()) || (TextUtils.isEmpty(str1)));
    while (true)
    {
      return;
      String[] arrayOfString1 = str1.split(";");
      String[] arrayOfString2 = null;
      int i = arrayOfString1.length;
      int j = 0;
      if (j < i)
      {
        String str2 = arrayOfString1[j];
        String[] arrayOfString3 = str2.trim().split(":");
        if (arrayOfString3.length != 2)
          FinskyLog.d("Bad corpus filter expression %s", new Object[] { str2 });
        int i1;
        do
        {
          j++;
          break;
          i1 = Integer.parseInt(arrayOfString3[0]);
          if ((i1 == 0) || (i1 == paramInt))
            arrayOfString2 = arrayOfString3[1].trim().split(",");
        }
        while (i1 != paramInt);
      }
      if (arrayOfString2 == null)
      {
        paramList.clear();
      }
      else
      {
        int k = arrayOfString2.length;
        if (!arrayOfString2[(k - 1)].equals("..."));
        for (int m = 1; m != 0; m = 0)
          while (paramList.size() > k)
            paramList.remove(-1 + paramList.size());
        k--;
        long l1 = System.currentTimeMillis();
        for (int n = 0; n < paramList.size(); n++)
        {
          long l2 = ()((float)l1 - 86400000.0F * Float.parseFloat(arrayOfString2[java.lang.Math.min(k - 1, n)]));
          ((Bundle)paramList.get(n)).putLong("Play.LastUpdateTimeMillis", l2);
        }
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(paramInt);
          FinskyLog.v("Filtered data for corpus %d:", arrayOfObject1);
          Iterator localIterator = paramList.iterator();
          while (localIterator.hasNext())
          {
            Bundle localBundle = (Bundle)localIterator.next();
            Object[] arrayOfObject2 = new Object[1];
            ConsumptionAppDoc localConsumptionAppDoc = new ConsumptionAppDoc(localBundle);
            arrayOfObject2[0] = localConsumptionAppDoc.toString();
            FinskyLog.v("%s", arrayOfObject2);
          }
        }
      }
    }
  }

  public ConsumptionAppDocList getConsumptionAppData(int paramInt)
  {
    Utils.ensureOnMainThread();
    ConsumptionAppDocList localConsumptionAppDocList = new ConsumptionAppDocList(paramInt);
    if (hasConsumptionAppData(paramInt))
      localConsumptionAppDocList.addAll((Collection)this.mConsumptionAppData.get(paramInt));
    return localConsumptionAppDocList;
  }

  public boolean hasConsumptionAppData(int paramInt)
  {
    Utils.ensureOnMainThread();
    if (getDataStateForBackend(paramInt) == 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isLoadingData(int paramInt)
  {
    int i = 1;
    Utils.ensureOnMainThread();
    if (getDataStateForBackend(paramInt) == i);
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  public void setConsumptionAppData(final Context paramContext, final int paramInt, final List<Bundle> paramList)
  {
    filter(paramList, paramInt);
    if (Looper.myLooper() != Looper.getMainLooper())
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          ConsumptionAppDataCache.this.setConsumptionAppData(paramContext, paramInt, paramList);
        }
      });
    while (true)
    {
      return;
      if (paramList != null)
        setConsumptionAppData(paramContext, ConsumptionAppDocList.createFromBundles(paramInt, paramList), true);
    }
  }

  public void setConsumptionAppData(final Context paramContext, final ConsumptionAppDocList paramConsumptionAppDocList, final boolean paramBoolean)
  {
    int i = paramConsumptionAppDocList.getBackend();
    if (Looper.myLooper() != Looper.getMainLooper())
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          ConsumptionAppDataCache.this.setConsumptionAppData(paramContext, paramConsumptionAppDocList, paramBoolean);
        }
      });
    while (true)
    {
      return;
      int j = 0;
      if (hasConsumptionAppData(i))
        if (!getConsumptionAppData(i).equals(paramConsumptionAppDocList))
          j = 1;
      while (true)
      {
        this.mConsumptionAppData.put(i, paramConsumptionAppDocList);
        this.mDataLoadState.put(i, Integer.valueOf(2));
        if (j != 0)
          break label136;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        FinskyLog.d("data didn't change for backend=[%s], ignoring!", arrayOfObject);
        break;
        j = 0;
        continue;
        if (paramConsumptionAppDocList.size() > 0)
          j = 1;
      }
      label136: if ((paramConsumptionAppDocList.size() > 0) && (paramBoolean))
        updateWidgets(paramContext, i);
    }
  }

  public void startLoading(int paramInt)
  {
    Utils.ensureOnMainThread();
    if (!hasConsumptionAppData(paramInt))
      this.mDataLoadState.put(paramInt, Integer.valueOf(1));
  }

  public void updateWidgets(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("com.android.vending.action.APPWIDGET_UPDATE_CONSUMPTION_DATA");
    localIntent.setClass(paramContext, NowPlayingWidgetProvider.class);
    localIntent.putExtra("backendId", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.ConsumptionAppDataCache
 * JD-Core Version:    0.6.2
 */
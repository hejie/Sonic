package com.google.android.finsky.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Utils
{
  public static void ensureNotOnMainThread()
  {
    if (Looper.myLooper() != Looper.getMainLooper())
      return;
    throw new IllegalStateException("This method cannot be called from the UI thread.");
  }

  public static void ensureOnMainThread()
  {
    if (Looper.myLooper() == Looper.getMainLooper())
      return;
    throw new IllegalStateException("This method must be called from the UI thread.");
  }

  public static String getPreferenceKey(String paramString1, String paramString2)
  {
    return paramString2 + ":" + paramString1;
  }

  public static boolean isBackgroundDataEnabled(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    boolean bool;
    if (Build.VERSION.SDK_INT < 14)
      bool = localConnectivityManager.getBackgroundDataSetting();
    while (true)
    {
      return bool;
      NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      int i = arrayOfNetworkInfo.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label78;
        NetworkInfo localNetworkInfo = arrayOfNetworkInfo[j];
        if ((localNetworkInfo != null) && (localNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED))
        {
          bool = false;
          break;
        }
      }
      label78: bool = true;
    }
  }

  public static Map<String, String> mapFromBundleStrings(Bundle paramBundle)
  {
    HashMap localHashMap = Maps.newHashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = paramBundle.getString(str1);
      if (str2 != null)
        localHashMap.put(str1, str2);
    }
    return localHashMap;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Utils
 * JD-Core Version:    0.6.2
 */
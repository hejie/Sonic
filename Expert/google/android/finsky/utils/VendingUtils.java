package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;

public class VendingUtils
{
  private static volatile boolean sSystemWasUpgraded = false;

  public static Pair<Integer, Integer> getScreenDimensions(Context paramContext)
  {
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = Math.min(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    int j = Math.max(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
    return new Pair(Integer.valueOf(i), Integer.valueOf(j));
  }

  public static boolean wasSystemUpgraded()
  {
    boolean bool = true;
    if (sSystemWasUpgraded);
    while (true)
    {
      return bool;
      String str = Build.FINGERPRINT;
      if (str.equals((String)VendingPreferences.LAST_BUILD_FINGERPRINT.get()))
      {
        bool = false;
      }
      else
      {
        sSystemWasUpgraded = bool;
        VendingPreferences.LAST_BUILD_FINGERPRINT.put(str);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.VendingUtils
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.finsky.FinskyApp;
import com.google.android.vending.remoting.protos.DeviceConfigurationProto;
import java.util.Iterator;
import java.util.List;

public class LegacyDeviceConfigurationHelper
{
  private static DeviceConfigurationProto sDeviceConfiguration;

  public static void customizeDeviceConfiguration(Context paramContext, DeviceConfigurationProto paramDeviceConfigurationProto)
  {
    FeatureInfo[] arrayOfFeatureInfo = paramContext.getPackageManager().getSystemAvailableFeatures();
    if (arrayOfFeatureInfo != null)
    {
      int i = arrayOfFeatureInfo.length;
      for (int j = 0; j < i; j++)
      {
        FeatureInfo localFeatureInfo = arrayOfFeatureInfo[j];
        if (localFeatureInfo.name != null)
          paramDeviceConfigurationProto.addSystemAvailableFeature(localFeatureInfo.name);
      }
    }
    paramDeviceConfigurationProto.addNativePlatform(Build.CPU_ABI);
    if (!Build.CPU_ABI2.equals("unknown"))
      paramDeviceConfigurationProto.addNativePlatform(Build.CPU_ABI2);
  }

  public static DeviceConfigurationProto getDeviceConfiguration()
  {
    while (true)
    {
      FinskyApp localFinskyApp;
      try
      {
        if (sDeviceConfiguration != null)
          break label383;
        sDeviceConfiguration = new DeviceConfigurationProto();
        localFinskyApp = FinskyApp.get();
        ConfigurationInfo localConfigurationInfo = ((ActivityManager)localFinskyApp.getSystemService("activity")).getDeviceConfigurationInfo();
        Pair localPair = VendingUtils.getScreenDimensions(localFinskyApp);
        WindowManager localWindowManager = (WindowManager)localFinskyApp.getSystemService("window");
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        sDeviceConfiguration.setTouchScreen(getTouchScreenId(localConfigurationInfo.reqTouchScreen)).setKeyboard(getKeyboardConfigId(localConfigurationInfo.reqKeyboardType)).setNavigation(getNavigationId(localConfigurationInfo.reqNavigation)).setGlEsVersion(localConfigurationInfo.reqGlEsVersion).setScreenWidth(((Integer)localPair.first).intValue()).setScreenHeight(((Integer)localPair.second).intValue()).setScreenDensity(localDisplayMetrics.densityDpi);
        DeviceConfigurationProto localDeviceConfigurationProto2 = sDeviceConfiguration;
        if ((0x1 & localConfigurationInfo.reqInputFeatures) <= 0)
          break label392;
        bool1 = true;
        localDeviceConfigurationProto2.setHasHardKeyboard(bool1);
        DeviceConfigurationProto localDeviceConfigurationProto3 = sDeviceConfiguration;
        if ((0x2 & localConfigurationInfo.reqInputFeatures) <= 0)
          break label398;
        bool2 = true;
        localDeviceConfigurationProto3.setHasFiveWayNavigation(bool2);
        Configuration localConfiguration = localFinskyApp.getResources().getConfiguration();
        sDeviceConfiguration.setScreenLayout(getScreenLayoutSizeId(localConfiguration.screenLayout));
        String[] arrayOfString1 = localFinskyApp.getPackageManager().getSystemSharedLibraryNames();
        int i = arrayOfString1.length;
        int j = 0;
        if (j < i)
        {
          String str3 = arrayOfString1[j];
          sDeviceConfiguration.addSystemSharedLibrary(str3);
          j++;
          continue;
        }
        String[] arrayOfString2 = FinskyApp.get().getAssets().getLocales();
        int k = arrayOfString2.length;
        int m = 0;
        if (m < k)
        {
          String str2 = arrayOfString2[m];
          sDeviceConfiguration.addSystemSupportedLocale(str2);
          m++;
          continue;
        }
        Iterator localIterator = new GlExtensionReader().getGlExtensions().iterator();
        if (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          sDeviceConfiguration.addGlExtension(str1);
          continue;
        }
      }
      finally
      {
      }
      customizeDeviceConfiguration(localFinskyApp, sDeviceConfiguration);
      label383: DeviceConfigurationProto localDeviceConfigurationProto1 = sDeviceConfiguration;
      return localDeviceConfigurationProto1;
      label392: boolean bool1 = false;
      continue;
      label398: boolean bool2 = false;
    }
  }

  public static int getHashCode(DeviceConfigurationProto paramDeviceConfigurationProto)
  {
    return hashRepeatedString(hashRepeatedString(hashRepeatedString(hashRepeatedString(hashRepeatedString(31 * (31 * (31 * (31 * (31 * (31 * (31 * getHashCode(Integer.valueOf(paramDeviceConfigurationProto.getTouchScreen())) + paramDeviceConfigurationProto.getKeyboard()) + paramDeviceConfigurationProto.getNavigation()) + paramDeviceConfigurationProto.getScreenLayout()) + paramDeviceConfigurationProto.getScreenDensity()) + paramDeviceConfigurationProto.getScreenWidth()) + paramDeviceConfigurationProto.getScreenHeight()) + paramDeviceConfigurationProto.getGlEsVersion(), paramDeviceConfigurationProto.getSystemSharedLibraryList()), paramDeviceConfigurationProto.getSystemAvailableFeatureList()), paramDeviceConfigurationProto.getNativePlatformList()), paramDeviceConfigurationProto.getSystemSupportedLocaleList()), paramDeviceConfigurationProto.getGlExtensionList());
  }

  private static int getHashCode(Object paramObject)
  {
    if (paramObject == null);
    for (int i = 7; ; i = paramObject.hashCode())
      return i;
  }

  private static int getKeyboardConfigId(int paramInt)
  {
    int i = 0;
    switch (paramInt)
    {
    case 0:
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
    }
  }

  private static int getNavigationId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    default:
      i = 0;
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
    }
  }

  private static int getScreenLayoutSizeId(int paramInt)
  {
    int i;
    switch (paramInt & 0xF)
    {
    default:
      i = 0;
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
      continue;
      i = 4;
    }
  }

  private static int getTouchScreenId(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    default:
      i = 0;
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
    }
  }

  private static int hashRepeatedString(int paramInt, List<String> paramList)
  {
    if (paramList == null)
      return paramInt;
    Iterator localIterator = paramList.iterator();
    label13: String str;
    if (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      if (str != null)
        break label49;
    }
    label49: for (int i = 7; ; i = str.hashCode())
    {
      paramInt = i * 31;
      break label13;
      break;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.LegacyDeviceConfigurationHelper
 * JD-Core Version:    0.6.2
 */
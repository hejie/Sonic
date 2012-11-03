package com.google.android.vending.remoting.api;

import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;

public class VendingApiPreferences
{
  private static PreferenceFile sPrefs = new PreferenceFile("vending_preferences", 0);

  public static PreferenceFile.SharedPreference<Integer> getDeviceConfigurationHashProperty(String paramString)
  {
    return sPrefs.value("device_configuration_hash_" + paramString, Integer.valueOf(-1));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.VendingApiPreferences
 * JD-Core Version:    0.6.2
 */
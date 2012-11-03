package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VendingPreferences
{
  public static final String[] ACCOUNT_SPECIFIC_PREFIXES;
  public static PreferenceFile.SharedPreference<Boolean> AUTO_ADD_SHORTCUTS;
  public static PreferenceFile.SharedPreference<Boolean> AUTO_UPDATE_BY_DEFAULT;
  public static PreferenceFile.SharedPreference<Boolean> AUTO_UPDATE_WIFI_ONLY;
  public static PreferenceFile.SharedPreference<Boolean> BACKED_UP;
  public static PreferenceFile.SharedPreference<String> CACHED_GL_EXTENSIONS;
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static PreferenceFile.SharedPreference<Boolean> GL_DRIVER_CRASHED;
  public static PreferenceFile.SharedPreference<Boolean> HAS_SEEN_AUTO_UPDATE_DEFAULT;
  public static PreferenceFile.SharedPreference<Boolean> INTEREST_BASED_ADS_ENABLED;
  public static PreferenceFile.SharedPreference<String> LAST_BUILD_FINGERPRINT;
  public static PreferenceFile.SharedPreference<Long> LAST_METADATA_WARNING_TIMESTAMP;
  public static PreferenceFile.SharedPreference<Boolean> NOTIFY_UPDATES;
  public static PreferenceFile.SharedPreference<String> RESTORED_ANDROID_ID;
  private static PreferenceFile sPrefs;

  static
  {
    ACCOUNT_SPECIFIC_PREFIXES = new String[] { "last_checkin_hash_", "last_systems_apps_hash_", "last_market_alarm_timeout_", "last_market_alarm_start_time_", "account_exists_" };
    sPrefs = new PreferenceFile("vending_preferences", 0);
    CACHED_GL_EXTENSIONS = sPrefs.value("cached_gl_extensions", (String)null);
    GL_DRIVER_CRASHED = sPrefs.value("gl_driver_crashed", Boolean.valueOf(false));
    LAST_BUILD_FINGERPRINT = sPrefs.value("last_build_fingerprint", (String)null);
    BACKED_UP = sPrefs.value("finsky_backed_up", Boolean.valueOf(false));
    RESTORED_ANDROID_ID = sPrefs.value("finsky_restored_android_id", (String)null);
    INTEREST_BASED_ADS_ENABLED = sPrefs.value("ads_interest_based", Boolean.valueOf(true));
    LAST_METADATA_WARNING_TIMESTAMP = sPrefs.value("last_metadata_warning_timestamp", Long.valueOf(0L));
    NOTIFY_UPDATES = sPrefs.value("notify_updates", Boolean.valueOf(true));
    AUTO_UPDATE_WIFI_ONLY = sPrefs.value("update_over_wifi_only", Boolean.valueOf(false));
    HAS_SEEN_AUTO_UPDATE_DEFAULT = sPrefs.value("user_notified_about_auto_update", Boolean.valueOf(false));
    AUTO_UPDATE_BY_DEFAULT = sPrefs.value("auto_update_default", Boolean.valueOf(false));
    PreferenceFile localPreferenceFile = sPrefs;
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
    {
      AUTO_ADD_SHORTCUTS = localPreferenceFile.value("auto_add_shortcuts", Boolean.valueOf(bool));
      return;
    }
  }

  public static boolean contains(Account[] paramArrayOfAccount, String paramString)
  {
    int i = paramArrayOfAccount.length;
    int j = 0;
    if (j < i)
      if (!paramArrayOfAccount[j].name.equals(paramString));
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }

  public static PreferenceFile.SharedPreference<Boolean> getAccountExists(String paramString)
  {
    return sPrefs.value("account_exists_" + paramString, Boolean.valueOf(false));
  }

  public static PreferenceFile getMainPrefs()
  {
    return sPrefs;
  }

  public static PreferenceFile.SharedPreference<Long> getMarketAlarmStartTime(String paramString)
  {
    return sPrefs.value("last_market_alarm_start_time_" + paramString, Long.valueOf(0L));
  }

  public static PreferenceFile.SharedPreference<Long> getMarketAlarmTimeout(String paramString)
  {
    return sPrefs.value("last_market_alarm_timeout_" + paramString, Long.valueOf(0L));
  }

  public static String[] getNewAccounts(Account[] paramArrayOfAccount)
  {
    ArrayList localArrayList = null;
    int i = paramArrayOfAccount.length;
    for (int j = 0; j < i; j++)
    {
      Account localAccount = paramArrayOfAccount[j];
      if (!getAccountExists(localAccount.name).exists())
      {
        if (localArrayList == null)
          localArrayList = Lists.newArrayList(1);
        localArrayList.add(localAccount.name);
      }
    }
    if (localArrayList == null);
    for (String[] arrayOfString = EMPTY_STRING_ARRAY; ; arrayOfString = (String[])localArrayList.toArray(new String[localArrayList.size()]))
      return arrayOfString;
  }

  public static void saveCurrentAccountList(Account[] paramArrayOfAccount)
  {
    SharedPreferences localSharedPreferences = sPrefs.open();
    Map localMap = localSharedPreferences.getAll();
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    int i = 0;
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str.startsWith("account_exists_")) && (!contains(paramArrayOfAccount, str.substring("account_exists_".length()))))
      {
        localEditor.remove(str);
        i = 1;
      }
    }
    if (i != 0)
      PreferenceFile.commit(localEditor);
    int j = paramArrayOfAccount.length;
    for (int k = 0; k < j; k++)
    {
      Account localAccount = paramArrayOfAccount[k];
      if (!getAccountExists(localAccount.name).exists())
        getAccountExists(localAccount.name).put(Boolean.valueOf(true));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.VendingPreferences
 * JD-Core Version:    0.6.2
 */
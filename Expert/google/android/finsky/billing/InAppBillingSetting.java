package com.google.android.finsky.billing;

import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.GetMarketMetadataAction;
import com.google.android.finsky.config.PreferenceFile;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.VendingPreferences;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InAppBillingSetting
{
  private static PreferenceFile.SharedPreference<Boolean> getSharedPreference(String paramString)
  {
    PreferenceFile localPreferenceFile = VendingPreferences.getMainPrefs();
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      PreferenceFile.SharedPreference localSharedPreference = localPreferenceFile.value("IAB_" + str, Boolean.valueOf(false));
      return localSharedPreference;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new UnsupportedOperationException("Caught exception while encoding IAB status.", localUnsupportedEncodingException);
    }
  }

  public static boolean isEnabled(String paramString)
  {
    if (paramString == null);
    PreferenceFile.SharedPreference localSharedPreference;
    for (boolean bool = false; ; bool = ((Boolean)localSharedPreference.get()).booleanValue())
    {
      return bool;
      localSharedPreference = getSharedPreference(paramString);
      if (!localSharedPreference.exists())
        new GetMarketMetadataAction().runBlocking(FinskyApp.get(), paramString);
    }
  }

  public static void setEnabled(String paramString, boolean paramBoolean)
  {
    PreferenceFile.SharedPreference localSharedPreference = getSharedPreference(paramString);
    if (localSharedPreference != null)
      localSharedPreference.put(Boolean.valueOf(paramBoolean));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.InAppBillingSetting
 * JD-Core Version:    0.6.2
 */
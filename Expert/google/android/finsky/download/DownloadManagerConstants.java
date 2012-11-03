package com.google.android.finsky.download;

import android.net.Uri;
import android.os.Build.VERSION;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public final class DownloadManagerConstants
{
  private static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");
  private static final Uri FROYO_CONTENT_URI = Uri.parse("content://downloads/download");
  private static Boolean sDownloadManagerUsesFroyoStrings = null;

  public static Uri getContentUri()
  {
    if (Build.VERSION.SDK_INT <= 8);
    for (Uri localUri = FROYO_CONTENT_URI; ; localUri = CONTENT_URI)
      return localUri;
  }

  public static Uri getContentUriForContentObserver()
  {
    if (isFroyoDownloadManager());
    for (Uri localUri = FROYO_CONTENT_URI; ; localUri = CONTENT_URI)
      return localUri;
  }

  public static String getContentUriString(String paramString)
  {
    if (isFroyoDownloadManager());
    for (String str = "content://downloads/download/" + paramString; ; str = "content://downloads/my_downloads/" + paramString)
      return str;
  }

  public static int getFileDestinationConstant()
  {
    if (Build.VERSION.SDK_INT <= 8);
    for (int i = 0; ; i = 4)
      return i;
  }

  private static boolean isFroyoDownloadManager()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT > 10)
      bool = false;
    while (true)
    {
      return bool;
      if (Build.VERSION.SDK_INT < 9)
        bool = true;
      else if (sDownloadManagerUsesFroyoStrings != null)
        bool = sDownloadManagerUsesFroyoStrings.booleanValue();
      else
        bool = ((Boolean)FinskyPreferences.downloadManagerUsesFroyoStrings.get()).booleanValue();
    }
  }

  public static boolean isStatusCompleted(int paramInt)
  {
    if (((paramInt >= 200) && (paramInt < 300)) || ((paramInt >= 400) && (paramInt < 600)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean isStatusSuccess(int paramInt)
  {
    if ((paramInt >= 200) && (paramInt < 300));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void sniffDownloadManagerVersion(Uri paramUri)
  {
    if ((Build.VERSION.SDK_INT > 10) || (Build.VERSION.SDK_INT < 9));
    while (true)
    {
      return;
      if (sDownloadManagerUsesFroyoStrings == null)
      {
        String str = paramUri.toString().toLowerCase();
        if (str.startsWith("content://downloads/download"))
        {
          sDownloadManagerUsesFroyoStrings = Boolean.TRUE;
          FinskyPreferences.downloadManagerUsesFroyoStrings.put(Boolean.valueOf(true));
        }
        else if (str.startsWith("content://downloads/my_downloads"))
        {
          sDownloadManagerUsesFroyoStrings = Boolean.FALSE;
          FinskyPreferences.downloadManagerUsesFroyoStrings.put(Boolean.valueOf(false));
        }
        else
        {
          FinskyLog.w("Unknown download manager URI string: %s", new Object[] { str });
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadManagerConstants
 * JD-Core Version:    0.6.2
 */
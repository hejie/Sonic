package com.google.android.finsky.api;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.finsky.utils.DfeLog;
import com.google.android.volley.UrlTools;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DfeApiContext
{
  private static int sCachedSmallestScreenWidthDp = -1;
  private final AndroidAuthenticator mAuthenticator;
  private final Cache mCache;
  private final Context mContext;
  private final Experiments mExperiments;
  private boolean mHasPerformedInitialTokenInvalidation;
  private final Map<String, String> mHeaders = new HashMap();
  private String mLastAuthToken;
  private final DfeNotificationManager mNotificationManager;

  protected DfeApiContext(Context paramContext, AndroidAuthenticator paramAndroidAuthenticator, Cache paramCache, Experiments paramExperiments, DfeNotificationManager paramDfeNotificationManager, String paramString1, int paramInt1, int paramInt2, Locale paramLocale, String paramString2, String paramString3, String paramString4, int paramInt3)
  {
    this.mContext = paramContext;
    this.mAuthenticator = paramAndroidAuthenticator;
    this.mCache = paramCache;
    this.mNotificationManager = paramDfeNotificationManager;
    this.mExperiments = paramExperiments;
    this.mHeaders.put("X-DFE-Device-Id", Long.toHexString(((Long)DfeApiConfig.androidId.get()).longValue()));
    this.mHeaders.put("Accept-Language", paramLocale.getLanguage() + "-" + paramLocale.getCountry());
    if (!TextUtils.isEmpty(paramString2))
      this.mHeaders.put("X-DFE-MCCMNC", paramString2);
    if (!TextUtils.isEmpty(paramString3))
      this.mHeaders.put("X-DFE-Client-Id", paramString3);
    if (!TextUtils.isEmpty(paramString3))
      this.mHeaders.put("X-DFE-Logging-Id", paramString4);
    this.mHeaders.put("User-Agent", makeUserAgentString(paramString1, paramInt1, paramInt2));
    this.mHeaders.put("X-DFE-SmallestScreenWidthDp", getSmallestScreenWidthDp(paramContext));
    this.mHeaders.put("X-DFE-Filter-Level", String.valueOf(paramInt3));
    checkUrlRules();
  }

  private static void checkUrlIsSecure(String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      if ((!localURL.getProtocol().toLowerCase().equals("https")) && (!localURL.getHost().toLowerCase().endsWith("corp.google.com")))
      {
        boolean bool = localURL.getHost().toLowerCase().startsWith("192.168.0");
        if (!bool);
      }
      else
      {
        return;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      DfeLog.d("Cannot parse URL: " + paramString, new Object[0]);
    }
    throw new RuntimeException("Insecure URL: " + paramString);
  }

  private void checkUrlRules()
  {
    String str1 = DfeApi.BASE_URI.toString();
    String str2 = UrlTools.rewrite(this.mContext, str1);
    if (str2 == null)
      throw new RuntimeException("BASE_URI blocked by UrlRules: " + str1);
    checkUrlIsSecure(str2);
  }

  public static DfeApiContext create(Context paramContext, Cache paramCache, Experiments paramExperiments, DfeNotificationManager paramDfeNotificationManager, String paramString, int paramInt)
  {
    AndroidAuthenticator localAndroidAuthenticator = new AndroidAuthenticator(paramContext, AccountHandler.findAccount(paramString, paramContext), (String)DfeApiConfig.authTokenType.get());
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      String str = localPackageInfo.versionName;
      int i = localPackageInfo.versionCode;
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      DfeApiContext localDfeApiContext = new DfeApiContext(paramContext, localAndroidAuthenticator, paramCache, paramExperiments, paramDfeNotificationManager, str, i, 3, Locale.getDefault(), localTelephonyManager.getSimOperator(), (String)DfeApiConfig.clientId.get(), (String)DfeApiConfig.loggingId.get(), paramInt);
      return localDfeApiContext;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException("Can't find our own package", localNameNotFoundException);
    }
  }

  private String getSmallestScreenWidthDp(Context paramContext)
  {
    if (sCachedSmallestScreenWidthDp == -1)
      if (Build.VERSION.SDK_INT < 14)
        break label35;
    label35: DisplayMetrics localDisplayMetrics;
    for (sCachedSmallestScreenWidthDp = paramContext.getResources().getConfiguration().smallestScreenWidthDp; ; sCachedSmallestScreenWidthDp = Math.min((int)(localDisplayMetrics.widthPixels / localDisplayMetrics.density), (int)(localDisplayMetrics.heightPixels / localDisplayMetrics.density)))
    {
      return String.valueOf(sCachedSmallestScreenWidthDp);
      localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    }
  }

  private String makeUserAgentString(String paramString, int paramInt1, int paramInt2)
  {
    String str1 = sanitizeHeaderValue(Build.DEVICE);
    String str2 = sanitizeHeaderValue(Build.HARDWARE);
    String str3 = sanitizeHeaderValue(Build.PRODUCT);
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    arrayOfObject[2] = Integer.valueOf(paramInt1);
    arrayOfObject[3] = Integer.valueOf(Build.VERSION.SDK_INT);
    arrayOfObject[4] = str1;
    arrayOfObject[5] = str2;
    arrayOfObject[6] = str3;
    return String.format(localLocale, "Android-Finsky/%s (api=%d,versionCode=%d,sdk=%d,device=%s,hardware=%s,product=%s)", arrayOfObject);
  }

  static String sanitizeHeaderValue(String paramString)
  {
    return Uri.encode(paramString).replace("(", "").replace(")", "");
  }

  public Account getAccount()
  {
    return this.mAuthenticator.getAccount();
  }

  public String getAccountName()
  {
    Account localAccount = getAccount();
    if (localAccount == null);
    for (String str = null; ; str = localAccount.name)
      return str;
  }

  public Cache getCache()
  {
    return this.mCache;
  }

  public Experiments getExperiments()
  {
    return this.mExperiments;
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    try
    {
      if (!this.mHasPerformedInitialTokenInvalidation)
      {
        invalidateAuthToken();
        this.mHasPerformedInitialTokenInvalidation = true;
      }
      this.mLastAuthToken = this.mAuthenticator.getAuthToken();
      HashMap localHashMap = new HashMap();
      localHashMap.putAll(this.mHeaders);
      if ((this.mExperiments != null) && (this.mExperiments.hasEnabledExperiments()))
        localHashMap.put("X-DFE-Enabled-Experiments", this.mExperiments.getEnabledExperimentsHeaderValue());
      if ((this.mExperiments != null) && (this.mExperiments.hasUnsupportedExperiments()))
        localHashMap.put("X-DFE-Unsupported-Experiments", this.mExperiments.getUnsupportedExperimentsHeaderValue());
      localHashMap.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
      return localHashMap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public DfeNotificationManager getNotificationManager()
  {
    return this.mNotificationManager;
  }

  public void invalidateAuthToken()
  {
    if (this.mLastAuthToken != null)
    {
      this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
      this.mLastAuthToken = null;
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[DfeApiContext headers={");
    int i = 1;
    Iterator localIterator = this.mHeaders.keySet().iterator();
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(str).append(": ").append((String)this.mHeaders.get(str));
        break;
        localStringBuilder.append(", ");
      }
    }
    localStringBuilder.append("}]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeApiContext
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.common.http.UrlRules;
import com.google.android.common.http.UrlRules.Rule;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DebugActivity extends PreferenceActivity
{
  private static final String ORIGINAL_DFE_URL = DfeApi.BASE_URI.toString();
  private static final String ORIGINAL_VENDING_API_URL = "https://android.clients.google.com/vending/api/ApiRequest".replace("api/ApiRequest", "");
  private final ICsvSelectorHelper<CarrierOverride> mCarrierHelper = new ICsvSelectorHelper()
  {
    public DebugActivity.CarrierOverride fromStringList(String[] paramAnonymousArrayOfString)
    {
      if (paramAnonymousArrayOfString != null);
      for (DebugActivity.CarrierOverride localCarrierOverride = new DebugActivity.CarrierOverride(paramAnonymousArrayOfString[0], paramAnonymousArrayOfString[1], paramAnonymousArrayOfString[2]); ; localCarrierOverride = new DebugActivity.CarrierOverride(null, null, null))
        return localCarrierOverride;
    }

    public boolean isSelected(DebugActivity.CarrierOverride paramAnonymousCarrierOverride)
    {
      String str1 = (String)DfeApiConfig.ipCountryOverride.get();
      String str2 = (String)DfeApiConfig.mccMncOverride.get();
      if ((TextUtils.equals(str1, paramAnonymousCarrierOverride.countryCode)) && (TextUtils.equals(str2, paramAnonymousCarrierOverride.simCode)));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    public void selectItem(DebugActivity.CarrierOverride paramAnonymousCarrierOverride)
    {
      DebugActivity.this.selectCarrier(paramAnonymousCarrierOverride);
    }
  };
  private final ICsvSelectorHelper<EnvironmentOverride> mEnvironmentHelper = new ICsvSelectorHelper()
  {
    public DebugActivity.EnvironmentOverride fromStringList(String[] paramAnonymousArrayOfString)
    {
      if (paramAnonymousArrayOfString != null);
      for (DebugActivity.EnvironmentOverride localEnvironmentOverride = new DebugActivity.EnvironmentOverride(paramAnonymousArrayOfString[1], paramAnonymousArrayOfString[2], paramAnonymousArrayOfString[3], paramAnonymousArrayOfString[4]); ; localEnvironmentOverride = new DebugActivity.EnvironmentOverride(null, null, null, null))
        return localEnvironmentOverride;
    }

    public boolean isSelected(DebugActivity.EnvironmentOverride paramAnonymousEnvironmentOverride)
    {
      String str1 = paramAnonymousEnvironmentOverride.dfeBaseUrl;
      UrlRules.Rule localRule = UrlRules.getRules(DebugActivity.this.getContentResolver()).matchRule(DebugActivity.ORIGINAL_DFE_URL);
      if (localRule == UrlRules.Rule.DEFAULT);
      for (String str2 = DebugActivity.ORIGINAL_DFE_URL; ; str2 = localRule.apply(DebugActivity.ORIGINAL_DFE_URL))
        return TextUtils.equals(str1, str2);
    }

    public void selectItem(DebugActivity.EnvironmentOverride paramAnonymousEnvironmentOverride)
    {
      DebugActivity.this.selectEnvironment(paramAnonymousEnvironmentOverride);
    }
  };

  private void clearCacheAndQuit()
  {
    FinskyApp.get().clearCacheAsync(new Runnable()
    {
      public void run()
      {
        Runtime.getRuntime().halt(0);
      }
    });
  }

  private void exportDatabase(String paramString)
  {
    int i = 0;
    try
    {
      File localFile1 = getDatabasePath(paramString);
      if ((localFile1.exists()) && (localFile1.canRead()))
      {
        File localFile2 = new File(Environment.getExternalStorageDirectory(), paramString);
        FileChannel localFileChannel1 = new FileInputStream(localFile1).getChannel();
        FileChannel localFileChannel2 = new FileOutputStream(localFile2).getChannel();
        localFileChannel1.transferTo(0L, localFileChannel1.size(), localFileChannel2);
        localFileChannel1.close();
        localFileChannel2.close();
        i = 1;
      }
      if (i == 0)
        Toast.makeText(this, "Unable to export " + paramString, 0).show();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException.getMessage();
        FinskyLog.e("Unable to export: %s", arrayOfObject);
      }
    }
  }

  // ERROR //
  private <T> int getOptionsFromCsv(String paramString, List<T> paramList, List<String> paramList1, ICsvSelectorHelper<T> paramICsvSelectorHelper)
  {
    // Byte code:
    //   0: aload_2
    //   1: aload 4
    //   3: aconst_null
    //   4: invokeinterface 181 2 0
    //   9: invokeinterface 187 2 0
    //   14: pop
    //   15: aload_3
    //   16: ldc 189
    //   18: invokeinterface 187 2 0
    //   23: pop
    //   24: iconst_0
    //   25: istore 7
    //   27: aconst_null
    //   28: astore 8
    //   30: invokestatic 110	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   33: astore 14
    //   35: new 97	java/io/File
    //   38: dup
    //   39: aload 14
    //   41: aload_1
    //   42: invokespecial 113	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   45: astore 15
    //   47: aload 15
    //   49: invokevirtual 101	java/io/File:exists	()Z
    //   52: ifne +24 -> 76
    //   55: new 97	java/io/File
    //   58: dup
    //   59: new 97	java/io/File
    //   62: dup
    //   63: aload 14
    //   65: ldc 191
    //   67: invokespecial 113	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   70: aload_1
    //   71: invokespecial 113	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   74: astore 15
    //   76: new 193	java/io/BufferedReader
    //   79: dup
    //   80: new 195	java/io/FileReader
    //   83: dup
    //   84: aload 15
    //   86: invokespecial 196	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   89: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   92: astore 16
    //   94: aload 16
    //   96: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   99: astore 17
    //   101: aload 17
    //   103: ifnull +175 -> 278
    //   106: aload 17
    //   108: invokevirtual 205	java/lang/String:trim	()Ljava/lang/String;
    //   111: astore 19
    //   113: aload 19
    //   115: ldc 207
    //   117: invokevirtual 211	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   120: ifne -26 -> 94
    //   123: aload 19
    //   125: invokestatic 217	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   128: ifne -34 -> 94
    //   131: aload 19
    //   133: ldc 219
    //   135: invokevirtual 223	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   138: astore 20
    //   140: aload 20
    //   142: iconst_0
    //   143: aaload
    //   144: astore 21
    //   146: aload 21
    //   148: invokestatic 217	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   151: ifeq +69 -> 220
    //   154: ldc 225
    //   156: iconst_0
    //   157: anewarray 160	java/lang/Object
    //   160: invokestatic 228	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   163: goto -69 -> 94
    //   166: astore 9
    //   168: aload 16
    //   170: astore 8
    //   172: iconst_1
    //   173: anewarray 160	java/lang/Object
    //   176: astore 12
    //   178: aload 12
    //   180: iconst_0
    //   181: aload 9
    //   183: invokevirtual 229	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   186: aastore
    //   187: ldc 231
    //   189: aload 12
    //   191: invokestatic 171	com/google/android/finsky/utils/FinskyLog:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   194: aload_0
    //   195: ldc 233
    //   197: iconst_1
    //   198: invokestatic 155	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   201: invokevirtual 158	android/widget/Toast:show	()V
    //   204: iconst_0
    //   205: istore 7
    //   207: aload 8
    //   209: ifnull +8 -> 217
    //   212: aload 8
    //   214: invokevirtual 234	java/io/BufferedReader:close	()V
    //   217: iload 7
    //   219: ireturn
    //   220: aload_3
    //   221: aload 21
    //   223: invokeinterface 187 2 0
    //   228: pop
    //   229: aload 4
    //   231: aload 20
    //   233: invokeinterface 181 2 0
    //   238: astore 23
    //   240: aload_2
    //   241: aload 23
    //   243: invokeinterface 187 2 0
    //   248: pop
    //   249: aload 4
    //   251: aload 23
    //   253: invokeinterface 237 2 0
    //   258: ifeq -164 -> 94
    //   261: aload_2
    //   262: invokeinterface 240 1 0
    //   267: istore 25
    //   269: iload 25
    //   271: iconst_1
    //   272: isub
    //   273: istore 7
    //   275: goto -181 -> 94
    //   278: aload 16
    //   280: ifnull +55 -> 335
    //   283: aload 16
    //   285: invokevirtual 234	java/io/BufferedReader:close	()V
    //   288: goto -71 -> 217
    //   291: astore 18
    //   293: goto -76 -> 217
    //   296: astore 10
    //   298: aload 8
    //   300: ifnull +8 -> 308
    //   303: aload 8
    //   305: invokevirtual 234	java/io/BufferedReader:close	()V
    //   308: aload 10
    //   310: athrow
    //   311: astore 13
    //   313: goto -96 -> 217
    //   316: astore 11
    //   318: goto -10 -> 308
    //   321: astore 10
    //   323: aload 16
    //   325: astore 8
    //   327: goto -29 -> 298
    //   330: astore 9
    //   332: goto -160 -> 172
    //   335: goto -118 -> 217
    //
    // Exception table:
    //   from	to	target	type
    //   94	163	166	java/lang/Exception
    //   220	269	166	java/lang/Exception
    //   283	288	291	java/io/IOException
    //   30	94	296	finally
    //   172	204	296	finally
    //   212	217	311	java/io/IOException
    //   303	308	316	java/io/IOException
    //   94	163	321	finally
    //   220	269	321	finally
    //   30	94	330	java/lang/Exception
  }

  private void override(GservicesValue<? extends Object> paramGservicesValue, String paramString)
  {
    Intent localIntent = new Intent("com.google.gservices.intent.action.GSERVICES_OVERRIDE");
    localIntent.putExtra(paramGservicesValue.getKey(), paramString);
    sendBroadcast(localIntent);
  }

  private void selectCarrier(CarrierOverride paramCarrierOverride)
  {
    override(DfeApiConfig.ipCountryOverride, paramCarrierOverride.countryCode);
    override(DfeApiConfig.mccMncOverride, paramCarrierOverride.simCode);
  }

  private void selectEnvironment(EnvironmentOverride paramEnvironmentOverride)
  {
    Intent localIntent = new Intent("com.google.gservices.intent.action.GSERVICES_OVERRIDE");
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    if (paramEnvironmentOverride != null)
    {
      if (paramEnvironmentOverride.dfeBaseUrl != null)
        break label117;
      str1 = null;
      if (paramEnvironmentOverride.vendingBaseUrl != null)
        break label150;
    }
    label150: for (str2 = null; ; str2 = ORIGINAL_VENDING_API_URL + " rewrite " + paramEnvironmentOverride.vendingBaseUrl)
    {
      str3 = paramEnvironmentOverride.checkoutTokenType;
      str4 = paramEnvironmentOverride.escrowUrl;
      localIntent.putExtra("url:finsky_dfe_url", str1);
      localIntent.putExtra("url:vending_machine_ssl_url", str2);
      localIntent.putExtra("url:licensing_url", str2);
      localIntent.putExtra(G.checkoutAuthTokenType.getKey(), str3);
      localIntent.putExtra(G.checkoutEscrowUrl.getKey(), str4);
      sendBroadcast(localIntent);
      return;
      label117: str1 = ORIGINAL_DFE_URL + " rewrite " + paramEnvironmentOverride.dfeBaseUrl;
      break;
    }
  }

  private void setupImageDebugging()
  {
    ((CheckBoxPreference)findPreference("image_debugging")).setChecked(((Boolean)G.debugImageSizes.get()).booleanValue());
  }

  private void setupShowStagingMerchData()
  {
    ((CheckBoxPreference)findPreference("show_staging_data")).setChecked(((Boolean)DfeApiConfig.showStagingData.get()).booleanValue());
  }

  private void setupSkipDfeCache()
  {
    ((CheckBoxPreference)findPreference("skip_all_caches")).setChecked(((Boolean)DfeApiConfig.skipAllCaches.get()).booleanValue());
  }

  private void setupVolleyLogging()
  {
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)findPreference("verbose_volley_logging");
    localCheckBoxPreference.setChecked(Log.isLoggable("Volley", 2));
    localCheckBoxPreference.setEnabled(false);
  }

  public void buildSelectCountryDialog()
  {
    buildSelectorFromCsv(this.mCarrierHelper, "carriers.csv", "Switching country...", 2131165849);
  }

  public void buildSelectEnvironmentDialog()
  {
    buildSelectorFromCsv(this.mEnvironmentHelper, "marketenvs.csv", "Switching environment...", 2131165848);
  }

  public <T> void buildSelectorFromCsv(final ICsvSelectorHelper<T> paramICsvSelectorHelper, String paramString1, final String paramString2, int paramInt)
  {
    final ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = Lists.newArrayList();
    int i = getOptionsFromCsv(paramString1, localArrayList1, localArrayList2, paramICsvSelectorHelper);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(paramInt);
    localBuilder.setSingleChoiceItems((String[])localArrayList2.toArray(new String[localArrayList2.size()]), i, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        Toast.makeText(DebugActivity.this, paramString2, 1).show();
        paramICsvSelectorHelper.selectItem(localArrayList1.get(paramAnonymousInt));
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
        {
          public void run()
          {
            DebugActivity.this.clearCacheAndQuit();
          }
        }
        , 3000L);
      }
    });
    localBuilder.create().show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!((Boolean)G.debugOptionsEnabled.get()).booleanValue())
      finish();
    while (true)
    {
      return;
      addPreferencesFromResource(2130968630);
      setupImageDebugging();
      setupSkipDfeCache();
      setupShowStagingMerchData();
      setupVolleyLogging();
    }
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    boolean bool1 = false;
    boolean bool2 = true;
    String str = paramPreference.getKey();
    boolean bool3;
    if ("image_debugging".equals(str))
    {
      GservicesValue localGservicesValue3 = G.debugImageSizes;
      if (!((Boolean)G.debugImageSizes.get()).booleanValue())
      {
        bool3 = bool2;
        override(localGservicesValue3, Boolean.toString(bool3).toLowerCase());
      }
    }
    while (true)
    {
      return bool2;
      bool3 = false;
      break;
      if ("skip_all_caches".equals(str))
      {
        GservicesValue localGservicesValue2 = DfeApiConfig.skipAllCaches;
        if (!((Boolean)DfeApiConfig.skipAllCaches.get()).booleanValue())
          label100: override(localGservicesValue2, Boolean.toString(bool2).toLowerCase());
      }
      do
      {
        bool2 = super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
        break;
        bool2 = false;
        break label100;
        if ("environments".equals(str))
        {
          buildSelectEnvironmentDialog();
          break;
        }
        if ("clear_cache".equals(str))
        {
          clearCacheAndQuit();
          break;
        }
        if ("show_staging_data".equals(str))
        {
          GservicesValue localGservicesValue1 = DfeApiConfig.showStagingData;
          if (!((Boolean)DfeApiConfig.showStagingData.get()).booleanValue())
            bool1 = bool2;
          override(localGservicesValue1, Boolean.toString(bool1).toLowerCase());
          clearCacheAndQuit();
          break;
        }
        if ("reset_all".equals(str))
        {
          override(G.debugImageSizes, "false");
          selectEnvironment(new EnvironmentOverride(null, null, null, null));
          selectCarrier(new CarrierOverride(null, null, null));
          clearCacheAndQuit();
          break;
        }
        if ("country".equals(str))
        {
          buildSelectCountryDialog();
          break;
        }
        if ("export_library".equals(str))
        {
          exportDatabase("localappstate.db");
          exportDatabase("library.db");
          Toast.makeText(this, "Finished database exports", 0).show();
          break;
        }
      }
      while (!"dump_library_state".equals(str));
      Log.d("FinskyLibrary", "------------ LIBRARY DUMP BEGIN");
      FinskyApp.get().getLibraries().dumpState();
      FinskyApp.get().getLibraryReplicators().dumpState();
      Log.d("FinskyLibrary", "------------ LIBRARY DUMP END");
      Toast.makeText(this, "Library state dumped to logcat.", 0).show();
    }
  }

  public static class CarrierOverride
  {
    public final String countryCode;
    public final String countryName;
    public final String simCode;

    public CarrierOverride(String paramString1, String paramString2, String paramString3)
    {
      this.countryName = paramString1;
      this.countryCode = paramString2;
      this.simCode = paramString3;
    }
  }

  public static class EnvironmentOverride
  {
    public final String checkoutTokenType;
    public final String dfeBaseUrl;
    public final String escrowUrl;
    public final String vendingBaseUrl;

    public EnvironmentOverride(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      this.dfeBaseUrl = paramString1;
      this.vendingBaseUrl = paramString2;
      this.checkoutTokenType = paramString3;
      this.escrowUrl = paramString4;
    }
  }

  public static abstract interface ICsvSelectorHelper<T>
  {
    public abstract T fromStringList(String[] paramArrayOfString);

    public abstract boolean isSelected(T paramT);

    public abstract void selectItem(T paramT);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DebugActivity
 * JD-Core Version:    0.6.2
 */
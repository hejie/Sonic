package com.google.android.finsky.appstate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MigrationAsyncTask extends AsyncTask<Void, Void, Map<String, InstallerDataStore.AutoUpdateState>>
{
  private final Boolean FINSKY_AUTOUPDATE_IS_STRINGS = Boolean.valueOf(true);
  private final String FINSKY_COLUMN_AUTO_UPDATE = "AUTO_UPDATE";
  private final String FINSKY_COLUMN_PACKAGE_NAME = "PACKAGE";
  private final String FINSKY_DATABASE_NAME = "market_assets.db";
  private final String FINSKY_TABLE_NAME = "assets";
  private final Boolean GRANOLA_AUTOUPDATE_IS_STRINGS = Boolean.valueOf(false);
  private final String GRANOLA_COLUMN_AUTO_UPDATE = "auto_update";
  private final String GRANOLA_COLUMN_PACKAGE_NAME = "package_name";
  private final String GRANOLA_DATABASE_NAME = "assets14.db";
  private final String GRANOLA_TABLE_NAME = "assets10";
  private final AppStates mAppStates;
  private final Context mContext;

  public MigrationAsyncTask(Context paramContext)
  {
    this.mContext = paramContext;
    this.mAppStates = FinskyApp.get().getAppStates();
  }

  private void collectLegacyData(Map<String, InstallerDataStore.AutoUpdateState> paramMap, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    File localFile = this.mContext.getDatabasePath(paramString1);
    if (!localFile.exists())
      return;
    while (true)
    {
      SQLiteDatabase localSQLiteDatabase;
      Cursor localCursor;
      String str1;
      int i;
      String str2;
      try
      {
        localSQLiteDatabase = SQLiteDatabase.openDatabase(localFile.getAbsolutePath(), null, 1);
        FinskyLog.d("Reading from legacy database %s", new Object[] { paramString1 });
        localCursor = localSQLiteDatabase.query(paramString2, new String[] { paramString3, paramString4 }, null, null, null, null, null);
        if (localCursor == null)
          break label248;
        try
        {
          if (!localCursor.moveToNext())
            break label241;
          str1 = localCursor.getString(0);
          if (TextUtils.isEmpty(str1))
            continue;
          i = 0;
          if (!paramBoolean)
            break label213;
          str2 = localCursor.getString(1);
          if (!"DISABLED".equals(str2))
            break label197;
          i = 1;
          break label256;
          paramMap.put(str1, InstallerDataStore.AutoUpdateState.DISABLED);
          continue;
        }
        finally
        {
          localCursor.close();
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString1;
        arrayOfObject[1] = localSQLiteException.toString();
        FinskyLog.e("Unable to open %s because %s", arrayOfObject);
      }
      break;
      label197: if ("ENABLED".equals(str2))
      {
        i = 2;
        break label256;
        label213: i = localCursor.getInt(1);
        break label256;
        paramMap.put(str1, InstallerDataStore.AutoUpdateState.ENABLED);
        continue;
        label241: localCursor.close();
        label248: localSQLiteDatabase.close();
        break;
      }
      label256: switch (i)
      {
      case 1:
      case 2:
      }
    }
  }

  protected Map<String, InstallerDataStore.AutoUpdateState> doInBackground(Void[] paramArrayOfVoid)
  {
    this.mAppStates.blockingLoad();
    HashMap localHashMap = Maps.newHashMap();
    collectLegacyData(localHashMap, "assets14.db", "assets10", "package_name", "auto_update", this.GRANOLA_AUTOUPDATE_IS_STRINGS.booleanValue());
    collectLegacyData(localHashMap, "market_assets.db", "assets", "PACKAGE", "AUTO_UPDATE", this.FINSKY_AUTOUPDATE_IS_STRINGS.booleanValue());
    return localHashMap;
  }

  protected void onPostExecute(Map<String, InstallerDataStore.AutoUpdateState> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      AppStates.AppState localAppState = this.mAppStates.getApp(str);
      if ((localAppState != null) && (localAppState.packageManagerState != null) && ((localAppState.installerData == null) || (localAppState.installerData.getAutoUpdate() == InstallerDataStore.AutoUpdateState.DEFAULT)))
      {
        InstallerDataStore.AutoUpdateState localAutoUpdateState = (InstallerDataStore.AutoUpdateState)paramMap.get(str);
        FinskyLog.d("Migrating %s autoupdate = %s", new Object[] { str, localAutoUpdateState });
        this.mAppStates.getInstallerDataStore().setAutoUpdate(str, localAutoUpdateState);
      }
    }
    this.mContext.deleteDatabase("assets14.db");
    this.mContext.deleteDatabase("market_assets.db");
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.MigrationAsyncTask
 * JD-Core Version:    0.6.2
 */
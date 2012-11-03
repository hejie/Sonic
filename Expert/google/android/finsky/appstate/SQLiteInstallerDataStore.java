package com.google.android.finsky.appstate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import java.util.ArrayList;
import java.util.Collection;

public class SQLiteInstallerDataStore
  implements InstallerDataStore
{
  private static final String[] FULL_PROJECTION = { "package_name", "auto_update", "desired_version", "download_uri", "delivery_data", "delivery_data_timestamp_ms", "installer_state", "first_download_ms", "referrer", "account", "title", "flags", "continue_url", "last_notified_version" };
  private SQLiteDatabase mDb;
  private final Helper mHelper;

  public SQLiteInstallerDataStore(Context paramContext)
  {
    this.mHelper = new Helper(paramContext);
  }

  private void close()
  {
    Utils.ensureNotOnMainThread();
    this.mDb.close();
  }

  private InstallerDataStore.InstallerData localAppStateFromCursor(Cursor paramCursor)
  {
    String str1 = paramCursor.getString(0);
    InstallerDataStore.AutoUpdateState localAutoUpdateState;
    if (paramCursor.isNull(1))
      localAutoUpdateState = InstallerDataStore.AutoUpdateState.DEFAULT;
    while (true)
    {
      int i;
      label35: int j;
      Object localObject;
      if (paramCursor.isNull(2))
      {
        i = -1;
        if (!paramCursor.isNull(13))
          break label223;
        j = -1;
        localObject = null;
        if (paramCursor.isNull(4));
      }
      try
      {
        AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = AndroidAppDelivery.AndroidAppDeliveryData.parseFrom(paramCursor.getBlob(4));
        localObject = localAndroidAppDeliveryData;
        long l1 = paramCursor.getLong(5);
        int k = paramCursor.getInt(6);
        String str2 = paramCursor.getString(3);
        long l2 = paramCursor.getLong(7);
        String str3 = paramCursor.getString(8);
        String str4 = paramCursor.getString(9);
        String str5 = paramCursor.getString(10);
        int m = paramCursor.getInt(11);
        return new InstallerDataStore.InstallerData(str1, localAutoUpdateState, i, j, localObject, l1, k, str2, l2, str3, paramCursor.getString(12), str4, str5, m);
        localAutoUpdateState = InstallerDataStore.AutoUpdateState.valueOf((int)paramCursor.getLong(1));
        continue;
        i = paramCursor.getInt(2);
        break label35;
        label223: j = paramCursor.getInt(13);
      }
      catch (InvalidProtocolBufferMicroException localInvalidProtocolBufferMicroException)
      {
        while (true)
          FinskyLog.w("Couldn't parse blob as AndroidAppDeliveryData", new Object[] { localInvalidProtocolBufferMicroException });
      }
    }
  }

  private void reopen()
  {
    Utils.ensureNotOnMainThread();
    this.mDb = this.mHelper.getWritableDatabase();
  }

  // ERROR //
  public InstallerDataStore.InstallerData get(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 133	com/google/android/finsky/appstate/SQLiteInstallerDataStore:reopen	()V
    //   6: aload_0
    //   7: getfield 65	com/google/android/finsky/appstate/SQLiteInstallerDataStore:mDb	Landroid/database/sqlite/SQLiteDatabase;
    //   10: ldc 135
    //   12: getstatic 46	com/google/android/finsky/appstate/SQLiteInstallerDataStore:FULL_PROJECTION	[Ljava/lang/String;
    //   15: ldc 137
    //   17: iconst_1
    //   18: anewarray 16	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: aload_1
    //   24: aastore
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokevirtual 141	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   31: astore_3
    //   32: aload_3
    //   33: invokeinterface 145 1 0
    //   38: istore 5
    //   40: iload 5
    //   42: iconst_1
    //   43: if_icmpeq +21 -> 64
    //   46: aload_3
    //   47: invokeinterface 146 1 0
    //   52: aload_0
    //   53: invokespecial 147	com/google/android/finsky/appstate/SQLiteInstallerDataStore:close	()V
    //   56: aconst_null
    //   57: astore 8
    //   59: aload_0
    //   60: monitorexit
    //   61: aload 8
    //   63: areturn
    //   64: aload_3
    //   65: invokeinterface 151 1 0
    //   70: pop
    //   71: aload_0
    //   72: aload_3
    //   73: invokespecial 153	com/google/android/finsky/appstate/SQLiteInstallerDataStore:localAppStateFromCursor	(Landroid/database/Cursor;)Lcom/google/android/finsky/appstate/InstallerDataStore$InstallerData;
    //   76: astore 7
    //   78: aload 7
    //   80: astore 8
    //   82: aload_3
    //   83: invokeinterface 146 1 0
    //   88: aload_0
    //   89: invokespecial 147	com/google/android/finsky/appstate/SQLiteInstallerDataStore:close	()V
    //   92: goto -33 -> 59
    //   95: astore_2
    //   96: aload_0
    //   97: monitorexit
    //   98: aload_2
    //   99: athrow
    //   100: astore 4
    //   102: aload_3
    //   103: invokeinterface 146 1 0
    //   108: aload_0
    //   109: invokespecial 147	com/google/android/finsky/appstate/SQLiteInstallerDataStore:close	()V
    //   112: aload 4
    //   114: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	32	95	finally
    //   46	56	95	finally
    //   82	92	95	finally
    //   102	115	95	finally
    //   32	40	100	finally
    //   64	78	100	finally
  }

  public Collection<InstallerDataStore.InstallerData> getAll()
  {
    Cursor localCursor;
    ArrayList localArrayList;
    try
    {
      reopen();
      localCursor = this.mDb.query("appstate", FULL_PROJECTION, null, null, null, null, null);
      localArrayList = new ArrayList(localCursor.getCount());
    }
    finally
    {
      try
      {
        if (localCursor.moveToNext())
          localArrayList.add(localAppStateFromCursor(localCursor));
      }
      finally
      {
        localCursor.close();
        close();
      }
    }
    localCursor.close();
    close();
    return localArrayList;
  }

  public void put(InstallerDataStore.InstallerData paramInstallerData)
  {
    try
    {
      reopen();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("package_name", paramInstallerData.getPackageName());
      localContentValues.put("auto_update", Integer.valueOf(paramInstallerData.getAutoUpdate().ordinal()));
      localContentValues.put("desired_version", Integer.valueOf(paramInstallerData.getDesiredVersion()));
      localContentValues.put("download_uri", paramInstallerData.getDownloadUri());
      if (paramInstallerData.getDeliveryData() != null)
        localContentValues.put("delivery_data", paramInstallerData.getDeliveryData().toByteArray());
      while (true)
      {
        localContentValues.put("delivery_data_timestamp_ms", Long.valueOf(paramInstallerData.getDeliveryDataTimestampMs()));
        localContentValues.put("installer_state", Integer.valueOf(paramInstallerData.getInstallerState()));
        localContentValues.put("first_download_ms", Long.valueOf(paramInstallerData.getFirstDownloadMs()));
        localContentValues.put("referrer", paramInstallerData.getReferrer());
        localContentValues.put("account", paramInstallerData.getAccountName());
        localContentValues.put("title", paramInstallerData.getTitle());
        localContentValues.put("flags", Integer.valueOf(paramInstallerData.getFlags()));
        localContentValues.put("continue_url", paramInstallerData.getContinueUrl());
        localContentValues.put("last_notified_version", Integer.valueOf(paramInstallerData.getLastNotifiedVersion()));
        this.mDb.replace("appstate", null, localContentValues);
        close();
        return;
        localContentValues.putNull("delivery_data");
      }
    }
    finally
    {
    }
  }

  public void setAccount(String paramString1, String paramString2)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString1), paramString1).setAccountName(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAutoUpdate(String paramString, InstallerDataStore.AutoUpdateState paramAutoUpdateState)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setAutoUpdate(paramAutoUpdateState).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setContinueUrl(String paramString1, String paramString2)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString1), paramString1).setContinueUrl(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDeliveryData(String paramString, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, long paramLong)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setDeliveryData(paramAndroidAppDeliveryData, paramLong).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setDesiredVersion(String paramString, int paramInt)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setDesiredVersion(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFirstDownloadMs(String paramString, long paramLong)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setFirstDownloadMs(paramLong).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setFlags(String paramString, int paramInt)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setFlags(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setInstallerState(String paramString1, int paramInt, String paramString2)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString1), paramString1).setInstallerState(paramInt).setDownloadUri(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setLastNotifiedVersion(String paramString, int paramInt)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString), paramString).setLastNotifiedVersion(paramInt).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setReferrer(String paramString1, String paramString2)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString1), paramString1).setExternalReferrer(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setTitle(String paramString1, String paramString2)
  {
    try
    {
      put(InstallerDataStore.InstallerData.Builder.buildUpon(get(paramString1), paramString1).setTitle(paramString2).build());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private class Helper extends SQLiteOpenHelper
  {
    public Helper(Context arg2)
    {
      super("localappstate.db", null, 10);
    }

    private void recreateDatabase(SQLiteDatabase paramSQLiteDatabase)
    {
      try
      {
        paramSQLiteDatabase.execSQL("DROP TABLE appstate");
        label6: onCreate(paramSQLiteDatabase);
        return;
      }
      catch (SQLException localSQLException)
      {
        break label6;
      }
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE appstate (package_name STRING, auto_update INTEGER, desired_version INTEGER, download_uri STRING, delivery_data BLOB, delivery_data_timestamp_ms INTEGER,installer_state INTEGER, first_download_ms INTEGER, referrer STRING, account STRING, title STRING,flags INTEGER, continue_url STRING, last_notified_version INTEGER, PRIMARY KEY (package_name))");
    }

    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      FinskyLog.d("Downgrading InstallerDataStore from %d to %d", arrayOfObject);
      recreateDatabase(paramSQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      switch (paramInt1)
      {
      default:
        recreateDatabase(paramSQLiteDatabase);
      case 7:
      case 8:
      case 9:
      }
      while (true)
      {
        return;
        paramSQLiteDatabase.execSQL("ALTER TABLE appstate ADD COLUMN continue_url STRING");
        paramSQLiteDatabase.execSQL("ALTER TABLE appstate ADD COLUMN delivery_data_timestamp_ms INTEGER");
        paramSQLiteDatabase.execSQL("ALTER TABLE appstate ADD COLUMN last_notified_version INTEGER");
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.SQLiteInstallerDataStore
 * JD-Core Version:    0.6.2
 */
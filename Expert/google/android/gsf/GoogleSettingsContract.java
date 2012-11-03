package com.google.android.gsf;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

public final class GoogleSettingsContract
{
  public static class NameValueTable
    implements BaseColumns
  {
  }

  public static final class Partner extends GoogleSettingsContract.NameValueTable
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.settings/partner");

    public static String getString(ContentResolver paramContentResolver, String paramString)
    {
      Object localObject1 = null;
      Cursor localCursor = null;
      try
      {
        localCursor = paramContentResolver.query(CONTENT_URI, new String[] { "value" }, "name=?", new String[] { paramString }, null);
        if ((localCursor != null) && (localCursor.moveToNext()))
        {
          String str = localCursor.getString(0);
          localObject1 = str;
        }
        return localObject1;
      }
      catch (SQLException localSQLException)
      {
        while (true)
        {
          Log.e("GoogleSettings", "Can't get key " + paramString + " from " + CONTENT_URI, localSQLException);
          if (localCursor != null)
            localCursor.close();
        }
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }

    public static String getString(ContentResolver paramContentResolver, String paramString1, String paramString2)
    {
      String str = getString(paramContentResolver, paramString1);
      if (str == null)
        str = paramString2;
      return str;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.gsf.GoogleSettingsContract
 * JD-Core Version:    0.6.2
 */
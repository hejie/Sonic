package com.google.android.finsky.download;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import com.google.android.finsky.FinskyApp;
import java.io.File;

public class Storage
{
  public static long cachePartitionAvailableSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getDownloadCacheDirectory().getPath());
    return localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
  }

  public static long dataPartitionAvailableSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    return localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
  }

  public static boolean externalStorageAvailable()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }

  public static long externalStorageAvailableSpace()
  {
    if (!externalStorageAvailable());
    StatFs localStatFs;
    for (long l = -1L; ; l = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize())
    {
      return l;
      localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    }
  }

  public static Uri getFileUriForContentUri(Uri paramUri)
  {
    if ("file".equalsIgnoreCase(paramUri.getScheme()));
    while (true)
    {
      return paramUri;
      Object localObject1 = null;
      Cursor localCursor = null;
      try
      {
        ContentResolver localContentResolver = FinskyApp.get().getContentResolver();
        String[] arrayOfString = { "_data" };
        localCursor = localContentResolver.query(paramUri, arrayOfString, null, null, null);
        if (localCursor.moveToFirst())
        {
          String str = localCursor.getString(0);
          localObject1 = str;
        }
        if (localCursor != null)
          localCursor.close();
        if (localObject1 == null)
          paramUri = null;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.Storage
 * JD-Core Version:    0.6.2
 */
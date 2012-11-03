package com.google.android.finsky.utils.persistence;

import android.text.TextUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FileBasedKeyValueStore
  implements KeyValueStore
{
  private final String mDataStoreId;
  private final File mRootDirectory;

  public FileBasedKeyValueStore(File paramFile, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("A dataStoreId must be specified");
    this.mRootDirectory = paramFile;
    this.mDataStoreId = paramString;
  }

  private Map<String, String> parseMapFromJson(JSONObject paramJSONObject)
    throws JSONException
  {
    HashMap localHashMap = Maps.newHashMap();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!paramJSONObject.isNull(str))
        localHashMap.put(str, paramJSONObject.getString(str));
      else
        localHashMap.put(str, null);
    }
    return localHashMap;
  }

  public void delete(String paramString)
  {
    if (!new File(this.mRootDirectory, this.mDataStoreId + paramString).delete())
      FinskyLog.e("Attempt to delete '%s' failed!", new Object[] { paramString });
  }

  public Map<String, Map<String, String>> fetchAll()
  {
    HashMap localHashMap = Maps.newHashMap();
    File[] arrayOfFile = this.mRootDirectory.listFiles();
    int i;
    if (arrayOfFile != null)
      i = arrayOfFile.length;
    for (int j = 0; ; j++)
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        String str1 = localFile.getName();
        try
        {
          if (!str1.startsWith(this.mDataStoreId))
            continue;
          FileInputStream localFileInputStream = new FileInputStream(localFile);
          ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
          String str2 = str1.replace(this.mDataStoreId, "");
          JSONObject localJSONObject = new JSONObject(localObjectInputStream.readUTF());
          localFileInputStream.close();
          localHashMap.put(str2, parseMapFromJson(localJSONObject));
        }
        catch (IOException localIOException)
        {
          FinskyLog.d("IOException when reading file '%s'. Deleting.", new Object[] { str1 });
          if (localFile.delete())
            continue;
          FinskyLog.e("Attempt to delete '%s' failed!", new Object[] { str1 });
        }
        catch (JSONException localJSONException)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = str1;
          arrayOfObject[1] = localJSONException.toString();
          FinskyLog.e("JSONException when reading file '%s'. Deleting. error=[%s]", arrayOfObject);
          if (localFile.delete())
            continue;
        }
        FinskyLog.e("Attempt to delete '%s' failed!", new Object[] { str1 });
      }
      else
      {
        return localHashMap;
      }
  }

  // ERROR //
  public void put(String paramString, Map<String, String> paramMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 150	java/io/FileOutputStream
    //   5: dup
    //   6: new 77	java/io/File
    //   9: dup
    //   10: aload_0
    //   11: getfield 30	com/google/android/finsky/utils/persistence/FileBasedKeyValueStore:mRootDirectory	Ljava/io/File;
    //   14: new 79	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   21: aload_0
    //   22: getfield 32	com/google/android/finsky/utils/persistence/FileBasedKeyValueStore:mDataStoreId	Ljava/lang/String;
    //   25: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: aload_1
    //   29: invokevirtual 84	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 90	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   38: invokespecial 151	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   41: astore 4
    //   43: new 153	java/io/ObjectOutputStream
    //   46: dup
    //   47: aload 4
    //   49: invokespecial 156	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   52: astore 5
    //   54: aload 5
    //   56: new 44	org/json/JSONObject
    //   59: dup
    //   60: aload_2
    //   61: invokespecial 159	org/json/JSONObject:<init>	(Ljava/util/Map;)V
    //   64: invokevirtual 160	org/json/JSONObject:toString	()Ljava/lang/String;
    //   67: invokevirtual 163	java/io/ObjectOutputStream:writeUTF	(Ljava/lang/String;)V
    //   70: aload 5
    //   72: invokevirtual 166	java/io/ObjectOutputStream:flush	()V
    //   75: aload 4
    //   77: ifnull +71 -> 148
    //   80: aload 4
    //   82: invokevirtual 167	java/io/FileOutputStream:close	()V
    //   85: return
    //   86: astore 10
    //   88: goto -3 -> 85
    //   91: astore 6
    //   93: aload 6
    //   95: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   98: aload_3
    //   99: ifnull -14 -> 85
    //   102: aload_3
    //   103: invokevirtual 167	java/io/FileOutputStream:close	()V
    //   106: goto -21 -> 85
    //   109: astore 9
    //   111: goto -26 -> 85
    //   114: astore 7
    //   116: aload_3
    //   117: ifnull +7 -> 124
    //   120: aload_3
    //   121: invokevirtual 167	java/io/FileOutputStream:close	()V
    //   124: aload 7
    //   126: athrow
    //   127: astore 8
    //   129: goto -5 -> 124
    //   132: astore 7
    //   134: aload 4
    //   136: astore_3
    //   137: goto -21 -> 116
    //   140: astore 6
    //   142: aload 4
    //   144: astore_3
    //   145: goto -52 -> 93
    //   148: goto -63 -> 85
    //
    // Exception table:
    //   from	to	target	type
    //   80	85	86	java/io/IOException
    //   2	43	91	java/io/IOException
    //   102	106	109	java/io/IOException
    //   2	43	114	finally
    //   93	98	114	finally
    //   120	124	127	java/io/IOException
    //   43	75	132	finally
    //   43	75	140	java/io/IOException
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.persistence.FileBasedKeyValueStore
 * JD-Core Version:    0.6.2
 */
package com.google.android.play.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class PlayUtils
{
  private static String sProcessName = null;

  public static String getDefaultUserAgentString(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder("Android/");
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      localStringBuilder.append(localPackageInfo.packageName);
      localStringBuilder.append("/");
      localStringBuilder.append(localPackageInfo.versionCode);
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        Log.wtf("PlayUtils", localNameNotFoundException.getMessage(), localNameNotFoundException);
    }
  }

  // ERROR //
  public static String getProcessName()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 10	com/google/android/play/utils/PlayUtils:sProcessName	Ljava/lang/String;
    //   6: ifnull +14 -> 20
    //   9: getstatic 10	com/google/android/play/utils/PlayUtils:sProcessName	Ljava/lang/String;
    //   12: astore 9
    //   14: ldc 2
    //   16: monitorexit
    //   17: goto +180 -> 197
    //   20: ldc 2
    //   22: monitorexit
    //   23: new 79	java/io/BufferedReader
    //   26: dup
    //   27: new 81	java/io/FileReader
    //   30: dup
    //   31: ldc 83
    //   33: invokespecial 84	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   36: invokespecial 87	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   39: astore_1
    //   40: aload_1
    //   41: invokevirtual 90	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   44: invokevirtual 95	java/lang/String:trim	()Ljava/lang/String;
    //   47: astore 10
    //   49: ldc 2
    //   51: monitorenter
    //   52: getstatic 10	com/google/android/play/utils/PlayUtils:sProcessName	Ljava/lang/String;
    //   55: ifnonnull +8 -> 63
    //   58: aload 10
    //   60: putstatic 10	com/google/android/play/utils/PlayUtils:sProcessName	Ljava/lang/String;
    //   63: ldc 2
    //   65: monitorexit
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 98	java/io/BufferedReader:close	()V
    //   74: getstatic 10	com/google/android/play/utils/PlayUtils:sProcessName	Ljava/lang/String;
    //   77: astore 9
    //   79: goto +118 -> 197
    //   82: astore_0
    //   83: ldc 2
    //   85: monitorexit
    //   86: aload_0
    //   87: athrow
    //   88: astore 11
    //   90: ldc 2
    //   92: monitorexit
    //   93: aload 11
    //   95: athrow
    //   96: astore 5
    //   98: ldc 63
    //   100: aload 5
    //   102: invokevirtual 99	java/io/IOException:getMessage	()Ljava/lang/String;
    //   105: aload 5
    //   107: invokestatic 102	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   110: pop
    //   111: aload_1
    //   112: ifnull -38 -> 74
    //   115: aload_1
    //   116: invokevirtual 98	java/io/BufferedReader:close	()V
    //   119: goto -45 -> 74
    //   122: astore 7
    //   124: ldc 63
    //   126: aload 7
    //   128: invokevirtual 103	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   131: aload 7
    //   133: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   136: pop
    //   137: goto -63 -> 74
    //   140: astore 12
    //   142: ldc 63
    //   144: aload 12
    //   146: invokevirtual 103	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   149: aload 12
    //   151: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   154: pop
    //   155: goto -81 -> 74
    //   158: astore_2
    //   159: aconst_null
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +7 -> 169
    //   165: aload_1
    //   166: invokevirtual 98	java/io/BufferedReader:close	()V
    //   169: aload_2
    //   170: athrow
    //   171: astore_3
    //   172: ldc 63
    //   174: aload_3
    //   175: invokevirtual 103	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   178: aload_3
    //   179: invokestatic 106	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   182: pop
    //   183: goto -14 -> 169
    //   186: astore_2
    //   187: goto -26 -> 161
    //   190: astore 5
    //   192: aconst_null
    //   193: astore_1
    //   194: goto -96 -> 98
    //   197: aload 9
    //   199: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   3	23	82	finally
    //   83	86	82	finally
    //   52	66	88	finally
    //   90	93	88	finally
    //   40	52	96	java/io/IOException
    //   93	96	96	java/io/IOException
    //   115	119	122	java/lang/Exception
    //   70	74	140	java/lang/Exception
    //   23	40	158	finally
    //   165	169	171	java/lang/Exception
    //   40	52	186	finally
    //   93	96	186	finally
    //   98	111	186	finally
    //   23	40	190	java/io/IOException
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.utils.PlayUtils
 * JD-Core Version:    0.6.2
 */
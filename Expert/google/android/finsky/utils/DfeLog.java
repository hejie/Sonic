package com.google.android.finsky.utils;

import android.util.Log;
import java.util.IllegalFormatException;
import java.util.Locale;

public class DfeLog
{
  public static final boolean DEBUG = Log.isLoggable(TAG, 2);
  private static String TAG = "DfeApi";
  private static long sStartTime = System.currentTimeMillis();

  private static String buildMessage(String paramString, Object[] paramArrayOfObject)
  {
    StackTraceElement[] arrayOfStackTraceElement;
    int i;
    label22: String str4;
    if (paramArrayOfObject == null)
    {
      arrayOfStackTraceElement = new Throwable().fillInStackTrace().getStackTrace();
      i = 2;
      if (i >= arrayOfStackTraceElement.length)
        break label232;
      String str2 = arrayOfStackTraceElement[i].getClassName();
      if (str2.equals(DfeLog.class.getName()))
        break label226;
      String str3 = str2.substring(1 + str2.lastIndexOf('.'));
      str4 = str3.substring(1 + str3.lastIndexOf('$'));
    }
    label226: label232: for (String str1 = str4 + "." + arrayOfStackTraceElement[i].getMethodName(); ; str1 = "<unknown>")
    {
      while (true)
      {
        Locale localLocale = Locale.US;
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = Long.valueOf(Thread.currentThread().getId());
        arrayOfObject2[1] = str1;
        arrayOfObject2[2] = paramString;
        return String.format(localLocale, "[%d] %s: %s", arrayOfObject2);
        try
        {
          String str5 = String.format(Locale.US, paramString, paramArrayOfObject);
          paramString = str5;
        }
        catch (IllegalFormatException localIllegalFormatException)
        {
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = paramString;
          arrayOfObject1[1] = Integer.valueOf(paramArrayOfObject.length);
          wtf("IllegalFormatException: formatString='%s' numArgs=%d", arrayOfObject1);
          paramString = paramString + " (An error occurred while formatting the message.)";
        }
      }
      break;
      i++;
      break label22;
    }
  }

  public static void d(String paramString, Object[] paramArrayOfObject)
  {
    Log.d(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void e(String paramString, Object[] paramArrayOfObject)
  {
    Log.e(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void logTiming(String paramString, Object[] paramArrayOfObject)
  {
    if (!Log.isLoggable(TAG, 2))
      return;
    if (paramArrayOfObject == null);
    while (true)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - sStartTime);
      arrayOfObject[1] = paramString;
      v("%4dms: %s", arrayOfObject);
      break;
      paramString = String.format(Locale.US, paramString, paramArrayOfObject);
    }
  }

  public static void v(String paramString, Object[] paramArrayOfObject)
  {
    Log.v(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void w(String paramString, Object[] paramArrayOfObject)
  {
    Log.w(TAG, buildMessage(paramString, paramArrayOfObject));
  }

  public static void wtf(String paramString, Object[] paramArrayOfObject)
  {
    Log.e(TAG, buildMessage(paramString, paramArrayOfObject));
    Log.wtf(TAG, buildMessage(paramString, paramArrayOfObject));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.DfeLog
 * JD-Core Version:    0.6.2
 */
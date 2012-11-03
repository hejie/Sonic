package com.google.android.finsky.utils;

import android.view.View;
import java.lang.reflect.Method;

public class Compat
{
  private static final Method sViewSetSystemUiVisibility = findMethod(View.class, "setSystemUiVisibility", Integer.TYPE);

  private static Method findMethod(Class<?> paramClass1, String paramString, Class<?> paramClass2)
  {
    Object localObject = null;
    if (paramClass1 == null);
    while (true)
    {
      return localObject;
      try
      {
        Method localMethod = paramClass1.getMethod(paramString, new Class[] { paramClass2 });
        localObject = localMethod;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        FinskyLog.d("Method not found: %s", new Object[] { paramString });
      }
    }
  }

  public static void viewSetSystemUiVisibility(View paramView, int paramInt)
  {
    if (sViewSetSystemUiVisibility != null);
    try
    {
      Method localMethod = sViewSetSystemUiVisibility;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localMethod.invoke(paramView, arrayOfObject);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        FinskyLog.e("%s", new Object[] { localThrowable });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Compat
 * JD-Core Version:    0.6.2
 */
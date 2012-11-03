package com.google.android.volley;

import com.google.protobuf.micro.MessageMicro;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MicroProtoHelper
{
  private static final Map<Class<?>, Method> sGetMethodCache = new HashMap();
  private static final Map<Class<?>, Method> sSetMethodCache = new HashMap();

  private static Method findGetMethod(Class<?> paramClass1, Class<?> paramClass2)
  {
    Object localObject = (Method)sGetMethodCache.get(paramClass2);
    if (localObject != null)
      return localObject;
    Method[] arrayOfMethod = paramClass1.getMethods();
    int i = arrayOfMethod.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        break label93;
      Method localMethod = arrayOfMethod[j];
      if ((localMethod.getReturnType().equals(paramClass2)) && (localMethod.getName().startsWith("get")))
      {
        sGetMethodCache.put(paramClass2, localMethod);
        localObject = localMethod;
        break;
      }
    }
    label93: throw new IllegalArgumentException("No getter for " + paramClass2 + " in " + paramClass1);
  }

  private static Method findSetMethod(Class<?> paramClass1, Class<?> paramClass2)
  {
    Object localObject = (Method)sSetMethodCache.get(paramClass2);
    if (localObject != null)
      return localObject;
    Method[] arrayOfMethod = paramClass1.getMethods();
    int i = arrayOfMethod.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        break label106;
      Method localMethod = arrayOfMethod[j];
      Class[] arrayOfClass = localMethod.getParameterTypes();
      if ((arrayOfClass.length == 1) && (paramClass2.equals(arrayOfClass[0])) && (localMethod.getName().startsWith("set")))
      {
        sSetMethodCache.put(paramClass2, localMethod);
        localObject = localMethod;
        break;
      }
    }
    label106: throw new IllegalArgumentException("No setter for " + paramClass2 + " in " + paramClass1);
  }

  public static <X extends MessageMicro, Y extends MessageMicro> Y getParsedResponseFromWrapper(X paramX, Class<X> paramClass, Class<Y> paramClass1)
  {
    try
    {
      MessageMicro localMessageMicro = (MessageMicro)findGetMethod(paramClass, paramClass1).invoke(paramX, new Object[0]);
      return localMessageMicro;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }

  public static <X extends MessageMicro, Y extends MessageMicro> void setRequestInWrapper(X paramX, Class<X> paramClass, Y paramY, Class<Y> paramClass1)
  {
    try
    {
      findSetMethod(paramClass, paramClass1).invoke(paramX, new Object[] { paramY });
      return;
    }
    catch (Exception localException)
    {
      throw new RuntimeException(localException);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.MicroProtoHelper
 * JD-Core Version:    0.6.2
 */
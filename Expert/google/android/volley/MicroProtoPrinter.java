package com.google.android.volley;

import com.android.volley.VolleyLog;
import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.MessageMicro;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MicroProtoPrinter
{
  private static String PRETTY_PRINT_INDENT = "  ";

  private static String deCamelCaseify(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (i == 0)
        localStringBuffer.append(Character.toLowerCase(c));
      while (true)
      {
        i++;
        break;
        if (Character.isUpperCase(c))
          localStringBuffer.append('_').append(Character.toLowerCase(c));
        else
          localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }

  private static String escapeString(String paramString)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(i);
    int j = 0;
    if (j < i)
    {
      char c = paramString.charAt(j);
      if ((c >= ' ') && (c <= '~'))
        localStringBuilder.append(c);
      while (true)
      {
        j++;
        break;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(c);
        localStringBuilder.append(String.format("\\u%04x", arrayOfObject));
      }
    }
    return localStringBuilder.toString();
  }

  public static <T extends MessageMicro> String prettyPrint(String paramString, Class<T> paramClass, T paramT)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      prettyPrint(paramString, paramClass, paramT, "", localStringBuffer);
      str = localStringBuffer.toString();
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        str = "Error during pretty print: " + localIllegalAccessException.getMessage();
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      while (true)
        str = "Error during pretty print: " + localNoSuchFieldException.getMessage();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        String str = "Error during pretty print: " + localInvocationTargetException.getMessage();
    }
  }

  private static void prettyPrint(String paramString1, Class<?> paramClass, Object paramObject, String paramString2, StringBuffer paramStringBuffer)
    throws IllegalAccessException, NoSuchFieldException, InvocationTargetException
  {
    String str3;
    Method[] arrayOfMethod;
    int i;
    int j;
    if (MessageMicro.class.isAssignableFrom(paramClass))
    {
      str3 = paramString2 + PRETTY_PRINT_INDENT;
      paramStringBuffer.append(paramString2).append(paramString1).append(" <\n");
      arrayOfMethod = paramClass.getMethods();
      i = arrayOfMethod.length;
      j = 0;
    }
    while (true)
    {
      String str4;
      String str5;
      Object localObject1;
      int k;
      if (j < i)
      {
        str4 = arrayOfMethod[j].getName();
        if (str4.startsWith("set"))
        {
          str5 = str4.substring(3);
          localObject1 = null;
          k = 0;
        }
      }
      try
      {
        Method localMethod5 = paramClass.getMethod("get" + str5, new Class[0]);
        localObject1 = localMethod5;
        try
        {
          label136: Method localMethod4 = paramClass.getMethod("get" + str5 + "List", new Class[0]);
          localObject1 = localMethod4;
          k = 1;
          label178: if (localObject1 == null)
            VolleyLog.v("No getter found for setter: " + str4, new Object[0]);
          while (true)
          {
            j++;
            break;
            Class localClass = localObject1.getReturnType();
            Object localObject2 = localObject1.invoke(paramObject, new Object[0]);
            if (k == 0);
            try
            {
              Method localMethod3 = paramClass.getMethod("has" + str5, new Class[0]);
              if (((Boolean)localMethod3.invoke(paramObject, new Object[0])).booleanValue())
              {
                prettyPrint(str5, localClass, localObject2, str3, paramStringBuffer);
                continue;
                try
                {
                  String str6 = "get" + str5;
                  Class[] arrayOfClass = new Class[1];
                  arrayOfClass[0] = Integer.TYPE;
                  Method localMethod2 = paramClass.getMethod(str6, arrayOfClass);
                  localMethod1 = localMethod2;
                  if (localMethod1 == null)
                    continue;
                  List localList = (List)localObject2;
                  for (int m = 0; m < localList.size(); m++)
                    prettyPrint(str5, localMethod1.getReturnType(), localList.get(m), str3, paramStringBuffer);
                }
                catch (NoSuchMethodException localNoSuchMethodException3)
                {
                  while (true)
                    Method localMethod1 = null;
                }
                paramStringBuffer.append(paramString2).append(">");
                while (true)
                {
                  paramStringBuffer.append('\n');
                  return;
                  String str1 = deCamelCaseify(paramString1);
                  paramStringBuffer.append(paramString2).append(str1).append(": ");
                  if ((paramObject instanceof String))
                  {
                    String str2 = sanitizeString((String)paramObject);
                    paramStringBuffer.append('"').append(str2).append('"');
                  }
                  else if ((paramObject instanceof ByteStringMicro))
                  {
                    ByteStringMicro localByteStringMicro = (ByteStringMicro)paramObject;
                    paramStringBuffer.append("byte[").append(localByteStringMicro.size()).append(']');
                  }
                  else
                  {
                    paramStringBuffer.append(paramObject);
                  }
                }
              }
            }
            catch (NoSuchMethodException localNoSuchMethodException4)
            {
            }
          }
        }
        catch (NoSuchMethodException localNoSuchMethodException2)
        {
          break label178;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException1)
      {
        break label136;
      }
    }
  }

  private static String sanitizeString(String paramString)
  {
    if ((!paramString.startsWith("http")) && (paramString.length() > 200))
      paramString = paramString.substring(0, 200) + "[...]";
    return escapeString(paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.MicroProtoPrinter
 * JD-Core Version:    0.6.2
 */
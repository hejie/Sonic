package com.google.android.finsky.billing.carrierbilling.debug;

import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ReflectionDcbDetailExtractor
  implements DcbDetailExtractor
{
  private Collection<DcbDetail> mCachedDetails = null;
  private final Object mObject;
  private final String mRootName;

  public ReflectionDcbDetailExtractor(Object paramObject, String paramString)
  {
    this.mObject = paramObject;
    this.mRootName = paramString;
  }

  private static Collection<DcbDetail> buildDetails(Object paramObject, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    buildDetailsHelper(paramObject, paramString, localArrayList);
    return localArrayList;
  }

  private static void buildDetailsHelper(Object paramObject, String paramString, List<DcbDetail> paramList)
  {
    int i = 0;
    if (paramObject == null)
      paramList.add(new SimpleDetail(paramString, null));
    while (true)
    {
      return;
      Class localClass = paramObject.getClass();
      boolean bool1 = isArray(paramObject);
      boolean bool2 = isIterable(paramObject);
      if ((localClass.isPrimitive()) || (localClass.isAssignableFrom(Number.class)) || (localClass.isAssignableFrom(String.class)))
      {
        paramList.add(new SimpleDetail(paramString, paramObject.toString()));
      }
      else
      {
        if ((bool2) || (bool1))
        {
          if (bool1);
          for (Object localObject1 = Arrays.asList((Object[])paramObject); ; localObject1 = (Iterable)paramObject)
          {
            Iterator localIterator = ((Iterable)localObject1).iterator();
            while (localIterator.hasNext())
            {
              buildDetailsHelper(localIterator.next(), paramString + "." + i, paramList);
              i++;
            }
            break;
          }
        }
        ArrayList localArrayList = Lists.newArrayList();
        Method[] arrayOfMethod = localClass.getDeclaredMethods();
        int j = arrayOfMethod.length;
        int k = 0;
        while (true)
          if (k < j)
          {
            Method localMethod = arrayOfMethod[k];
            String str1;
            if ((localMethod.getName().startsWith("get")) && ((0x1 & localMethod.getModifiers()) != 0) && (localMethod.getParameterTypes().length == 0))
              str1 = methodNameToTitle(localMethod.getName());
            try
            {
              Object localObject2 = localMethod.invoke(paramObject, new Object[0]);
              buildDetailsHelper(localObject2, paramString + "." + str1, localArrayList);
              k++;
            }
            catch (IllegalArgumentException localIllegalArgumentException)
            {
              while (true)
                FinskyLog.wtf(localIllegalArgumentException, "Shouldn't happen with no-arg methods", new Object[0]);
            }
            catch (IllegalAccessException localIllegalAccessException)
            {
              while (true)
                FinskyLog.wtf(localIllegalAccessException, "Shouldn't happen with public methods", new Object[0]);
            }
            catch (InvocationTargetException localInvocationTargetException)
            {
              while (true)
              {
                String str2 = "unknown";
                if (localInvocationTargetException.getCause() != null)
                  str2 = localInvocationTargetException.getCause().getClass().getName();
                Object[] arrayOfObject = new Object[3];
                arrayOfObject[0] = localMethod.getName();
                arrayOfObject[1] = str2;
                arrayOfObject[2] = localInvocationTargetException.getMessage();
                FinskyLog.e("%s throw exception (%s): %s", arrayOfObject);
              }
            }
          }
        if (!localArrayList.isEmpty())
          paramList.addAll(localArrayList);
        else
          paramList.add(new SimpleDetail(paramString, paramObject.toString()));
      }
    }
  }

  static boolean isArray(Object paramObject)
  {
    if (paramObject == null);
    for (boolean bool = false; ; bool = paramObject.getClass().isArray())
      return bool;
  }

  static boolean isIterable(Object paramObject)
  {
    if (paramObject == null);
    for (boolean bool = false; ; bool = Iterable.class.isAssignableFrom(paramObject.getClass()))
      return bool;
  }

  private static String methodNameToTitle(String paramString)
  {
    if (!paramString.startsWith("get"));
    while (true)
    {
      return paramString;
      paramString = paramString.substring(3);
    }
  }

  public Collection<DcbDetail> getDetails()
  {
    if (this.mCachedDetails == null)
      this.mCachedDetails = buildDetails(this.mObject, this.mRootName);
    return this.mCachedDetails;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.debug.ReflectionDcbDetailExtractor
 * JD-Core Version:    0.6.2
 */
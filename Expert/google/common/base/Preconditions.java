package com.google.common.base;

public final class Preconditions
{
  private static String badPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    Object[] arrayOfObject2;
    if (paramInt1 < 0)
    {
      arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(paramInt1);
    }
    Object[] arrayOfObject1;
    for (String str = format("%s (%s) must not be negative", arrayOfObject2); ; str = format("%s (%s) must not be greater than size (%s)", arrayOfObject1))
    {
      return str;
      if (paramInt2 < 0)
        throw new IllegalArgumentException("negative size: " + paramInt2);
      arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Integer.valueOf(paramInt1);
      arrayOfObject1[2] = Integer.valueOf(paramInt2);
    }
  }

  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static void checkArgument(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(String.valueOf(paramObject));
  }

  public static void checkArgument(boolean paramBoolean, String paramString, Object[] paramArrayOfObject)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException(format(paramString, paramArrayOfObject));
  }

  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    return paramT;
  }

  public static <T> T checkNotNull(T paramT, Object paramObject)
  {
    if (paramT == null)
      throw new NullPointerException(String.valueOf(paramObject));
    return paramT;
  }

  public static int checkPositionIndex(int paramInt1, int paramInt2)
  {
    return checkPositionIndex(paramInt1, paramInt2, "index");
  }

  public static int checkPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt2))
      throw new IndexOutOfBoundsException(badPositionIndex(paramInt1, paramInt2, paramString));
    return paramInt1;
  }

  public static void checkState(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalStateException();
  }

  public static void checkState(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean)
      throw new IllegalStateException(String.valueOf(paramObject));
  }

  public static void checkState(boolean paramBoolean, String paramString, Object[] paramArrayOfObject)
  {
    if (!paramBoolean)
      throw new IllegalStateException(format(paramString, paramArrayOfObject));
  }

  static String format(String paramString, Object[] paramArrayOfObject)
  {
    String str = String.valueOf(paramString);
    StringBuilder localStringBuilder = new StringBuilder(str.length() + 16 * paramArrayOfObject.length);
    int i = 0;
    int i2;
    for (int j = 0; ; j = i2)
    {
      int i1;
      if (j < paramArrayOfObject.length)
      {
        i1 = str.indexOf("%s", i);
        if (i1 != -1);
      }
      else
      {
        localStringBuilder.append(str.substring(i));
        if (j >= paramArrayOfObject.length)
          break label180;
        localStringBuilder.append(" [");
        int k = j + 1;
        localStringBuilder.append(paramArrayOfObject[j]);
        int n;
        for (int m = k; m < paramArrayOfObject.length; m = n)
        {
          localStringBuilder.append(", ");
          n = m + 1;
          localStringBuilder.append(paramArrayOfObject[m]);
        }
      }
      localStringBuilder.append(str.substring(i, i1));
      i2 = j + 1;
      localStringBuilder.append(paramArrayOfObject[j]);
      i = i1 + 2;
    }
    localStringBuilder.append(']');
    label180: return localStringBuilder.toString();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.base.Preconditions
 * JD-Core Version:    0.6.2
 */
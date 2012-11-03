package com.google.common.base;

public final class Objects
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2))));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static <T> T firstNonNull(T paramT1, T paramT2)
  {
    if (paramT1 != null);
    while (true)
    {
      return paramT1;
      paramT1 = Preconditions.checkNotNull(paramT2);
    }
  }

  private static String simpleName(Class<?> paramClass)
  {
    String str = paramClass.getName().replaceAll("\\$[0-9]+", "\\$");
    int i = str.lastIndexOf('$');
    if (i == -1)
      i = str.lastIndexOf('.');
    return str.substring(i + 1);
  }

  public static ToStringHelper toStringHelper(Object paramObject)
  {
    return new ToStringHelper(simpleName(paramObject.getClass()), null);
  }

  public static final class ToStringHelper
  {
    private final StringBuilder builder;
    private boolean needsSeparator = false;

    private ToStringHelper(String paramString)
    {
      Preconditions.checkNotNull(paramString);
      this.builder = new StringBuilder(32).append(paramString).append('{');
    }

    private StringBuilder checkNameAndAppend(String paramString)
    {
      Preconditions.checkNotNull(paramString);
      return maybeAppendSeparator().append(paramString).append('=');
    }

    private StringBuilder maybeAppendSeparator()
    {
      if (this.needsSeparator);
      for (StringBuilder localStringBuilder = this.builder.append(", "); ; localStringBuilder = this.builder)
      {
        return localStringBuilder;
        this.needsSeparator = true;
      }
    }

    public ToStringHelper add(String paramString, int paramInt)
    {
      checkNameAndAppend(paramString).append(paramInt);
      return this;
    }

    public ToStringHelper add(String paramString, Object paramObject)
    {
      checkNameAndAppend(paramString).append(paramObject);
      return this;
    }

    public ToStringHelper addValue(Object paramObject)
    {
      maybeAppendSeparator().append(paramObject);
      return this;
    }

    public String toString()
    {
      try
      {
        String str = '}';
        return str;
      }
      finally
      {
        this.builder.setLength(-1 + this.builder.length());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.base.Objects
 * JD-Core Version:    0.6.2
 */
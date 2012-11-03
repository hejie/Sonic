package com.google.common.base;

public final class Throwables
{
  public static <X extends Throwable> void propagateIfInstanceOf(Throwable paramThrowable, Class<X> paramClass)
    throws Throwable
  {
    if ((paramThrowable != null) && (paramClass.isInstance(paramThrowable)))
      throw ((Throwable)paramClass.cast(paramThrowable));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.base.Throwables
 * JD-Core Version:    0.6.2
 */
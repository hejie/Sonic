package com.google.common.collect;

import java.util.Set;

public abstract interface Table<R, C, V>
{
  public abstract Set<Cell<R, C, V>> cellSet();

  public static abstract interface Cell<R, C, V>
  {
    public abstract V getValue();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.Table
 * JD-Core Version:    0.6.2
 */
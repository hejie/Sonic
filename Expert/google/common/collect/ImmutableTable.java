package com.google.common.collect;

import java.util.Map;

public abstract class ImmutableTable<R, C, V>
  implements Table<R, C, V>
{
  public abstract ImmutableSet<Table.Cell<R, C, V>> cellSet();

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (paramObject == this)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramObject instanceof Table))
      {
        Table localTable = (Table)paramObject;
        bool = cellSet().equals(localTable.cellSet());
      }
      else
      {
        bool = false;
      }
    }
  }

  public int hashCode()
  {
    return cellSet().hashCode();
  }

  public abstract ImmutableMap<R, Map<C, V>> rowMap();

  public String toString()
  {
    return rowMap().toString();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ImmutableTable
 * JD-Core Version:    0.6.2
 */
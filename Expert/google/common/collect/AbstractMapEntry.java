package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Map.Entry;

abstract class AbstractMapEntry<K, V>
  implements Map.Entry<K, V>
{
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if ((paramObject instanceof Map.Entry))
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      if ((Objects.equal(getKey(), localEntry.getKey())) && (Objects.equal(getValue(), localEntry.getValue())))
        bool = true;
    }
    return bool;
  }

  public abstract K getKey();

  public abstract V getValue();

  public int hashCode()
  {
    int i = 0;
    Object localObject1 = getKey();
    Object localObject2 = getValue();
    int j;
    if (localObject1 == null)
    {
      j = 0;
      if (localObject2 != null)
        break label37;
    }
    while (true)
    {
      return i ^ j;
      j = localObject1.hashCode();
      break;
      label37: i = localObject2.hashCode();
    }
  }

  public V setValue(V paramV)
  {
    throw new UnsupportedOperationException();
  }

  public String toString()
  {
    return getKey() + "=" + getValue();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.AbstractMapEntry
 * JD-Core Version:    0.6.2
 */
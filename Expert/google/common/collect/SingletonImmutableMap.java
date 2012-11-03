package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class SingletonImmutableMap<K, V> extends ImmutableMap<K, V>
{
  private transient Map.Entry<K, V> entry;
  private transient ImmutableSet<Map.Entry<K, V>> entrySet;
  private transient ImmutableSet<K> keySet;
  final transient K singleKey;
  final transient V singleValue;
  private transient ImmutableCollection<V> values;

  SingletonImmutableMap(Map.Entry<K, V> paramEntry)
  {
    this.entry = paramEntry;
    this.singleKey = paramEntry.getKey();
    this.singleValue = paramEntry.getValue();
  }

  private Map.Entry<K, V> entry()
  {
    Map.Entry localEntry = this.entry;
    if (localEntry == null)
    {
      localEntry = Maps.immutableEntry(this.singleKey, this.singleValue);
      this.entry = localEntry;
    }
    return localEntry;
  }

  public boolean containsKey(Object paramObject)
  {
    return this.singleKey.equals(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return this.singleValue.equals(paramObject);
  }

  public ImmutableSet<Map.Entry<K, V>> entrySet()
  {
    ImmutableSet localImmutableSet = this.entrySet;
    if (localImmutableSet == null)
    {
      localImmutableSet = ImmutableSet.of(entry());
      this.entrySet = localImmutableSet;
    }
    return localImmutableSet;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (paramObject == this);
    while (true)
    {
      return i;
      int j;
      if ((paramObject instanceof Map))
      {
        Map localMap = (Map)paramObject;
        if (localMap.size() != i)
        {
          j = 0;
        }
        else
        {
          Map.Entry localEntry = (Map.Entry)localMap.entrySet().iterator().next();
          if ((!this.singleKey.equals(localEntry.getKey())) || (!this.singleValue.equals(localEntry.getValue())))
            j = 0;
        }
      }
      else
      {
        j = 0;
      }
    }
  }

  public V get(Object paramObject)
  {
    if (this.singleKey.equals(paramObject));
    for (Object localObject = this.singleValue; ; localObject = null)
      return localObject;
  }

  public int hashCode()
  {
    return this.singleKey.hashCode() ^ this.singleValue.hashCode();
  }

  public boolean isEmpty()
  {
    return false;
  }

  public ImmutableSet<K> keySet()
  {
    ImmutableSet localImmutableSet = this.keySet;
    if (localImmutableSet == null)
    {
      localImmutableSet = ImmutableSet.of(this.singleKey);
      this.keySet = localImmutableSet;
    }
    return localImmutableSet;
  }

  public int size()
  {
    return 1;
  }

  public String toString()
  {
    return '{' + this.singleKey.toString() + '=' + this.singleValue.toString() + '}';
  }

  public ImmutableCollection<V> values()
  {
    Object localObject = this.values;
    if (localObject == null)
    {
      localObject = new Values(this.singleValue);
      this.values = ((ImmutableCollection)localObject);
    }
    return localObject;
  }

  private static class Values<V> extends ImmutableCollection<V>
  {
    final V singleValue;

    Values(V paramV)
    {
      this.singleValue = paramV;
    }

    public boolean contains(Object paramObject)
    {
      return this.singleValue.equals(paramObject);
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator<V> iterator()
    {
      return Iterators.singletonIterator(this.singleValue);
    }

    public int size()
    {
      return 1;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.SingletonImmutableMap
 * JD-Core Version:    0.6.2
 */
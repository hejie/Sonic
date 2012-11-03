package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ImmutableMap<K, V>
  implements Map<K, V>, Serializable
{
  public static <K, V> Builder<K, V> builder()
  {
    return new Builder();
  }

  static <K, V> Map.Entry<K, V> entryOf(K paramK, V paramV)
  {
    return Maps.immutableEntry(Preconditions.checkNotNull(paramK, "null key"), Preconditions.checkNotNull(paramV, "null value"));
  }

  public static <K, V> ImmutableMap<K, V> of()
  {
    return EmptyImmutableMap.INSTANCE;
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  public boolean containsKey(Object paramObject)
  {
    if (get(paramObject) != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public abstract ImmutableSet<Map.Entry<K, V>> entrySet();

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (paramObject == this)
      bool = true;
    while (true)
    {
      return bool;
      if ((paramObject instanceof Map))
      {
        Map localMap = (Map)paramObject;
        bool = entrySet().equals(localMap.entrySet());
      }
      else
      {
        bool = false;
      }
    }
  }

  public abstract V get(Object paramObject);

  public int hashCode()
  {
    return entrySet().hashCode();
  }

  public boolean isEmpty()
  {
    if (size() == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public abstract ImmutableSet<K> keySet();

  public final V put(K paramK, V paramV)
  {
    throw new UnsupportedOperationException();
  }

  public final void putAll(Map<? extends K, ? extends V> paramMap)
  {
    throw new UnsupportedOperationException();
  }

  public final V remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public String toString()
  {
    return Maps.toStringImpl(this);
  }

  public abstract ImmutableCollection<V> values();

  Object writeReplace()
  {
    return new SerializedForm(this);
  }

  public static class Builder<K, V>
  {
    final ArrayList<Map.Entry<K, V>> entries = Lists.newArrayList();

    private static <K, V> ImmutableMap<K, V> fromEntryList(List<Map.Entry<K, V>> paramList)
    {
      Object localObject;
      switch (paramList.size())
      {
      default:
        localObject = new RegularImmutableMap((Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]));
      case 0:
      case 1:
      }
      while (true)
      {
        return localObject;
        localObject = ImmutableMap.of();
        continue;
        localObject = new SingletonImmutableMap((Map.Entry)Iterables.getOnlyElement(paramList));
      }
    }

    public ImmutableMap<K, V> build()
    {
      return fromEntryList(this.entries);
    }

    public Builder<K, V> put(K paramK, V paramV)
    {
      this.entries.add(ImmutableMap.entryOf(paramK, paramV));
      return this;
    }
  }

  static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    private final Object[] keys;
    private final Object[] values;

    SerializedForm(ImmutableMap<?, ?> paramImmutableMap)
    {
      this.keys = new Object[paramImmutableMap.size()];
      this.values = new Object[paramImmutableMap.size()];
      int i = 0;
      Iterator localIterator = paramImmutableMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.keys[i] = localEntry.getKey();
        this.values[i] = localEntry.getValue();
        i++;
      }
    }

    Object createMap(ImmutableMap.Builder<Object, Object> paramBuilder)
    {
      for (int i = 0; i < this.keys.length; i++)
        paramBuilder.put(this.keys[i], this.values[i]);
      return paramBuilder.build();
    }

    Object readResolve()
    {
      return createMap(new ImmutableMap.Builder());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ImmutableMap
 * JD-Core Version:    0.6.2
 */
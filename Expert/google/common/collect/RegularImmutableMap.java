package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.Map.Entry;

final class RegularImmutableMap<K, V> extends ImmutableMap<K, V>
{
  private static final long serialVersionUID;
  private final transient LinkedEntry<K, V>[] entries;
  private transient ImmutableSet<Map.Entry<K, V>> entrySet;
  private transient ImmutableSet<K> keySet;
  private final transient int keySetHashCode;
  private final transient int mask;
  private final transient LinkedEntry<K, V>[] table;
  private transient ImmutableCollection<V> values;

  RegularImmutableMap(Map.Entry<?, ?>[] paramArrayOfEntry)
  {
    int i = paramArrayOfEntry.length;
    this.entries = createEntryArray(i);
    int j = chooseTableSize(i);
    this.table = createEntryArray(j);
    this.mask = (j - 1);
    int k = 0;
    for (int m = 0; m < i; m++)
    {
      Map.Entry<?, ?> localEntry = paramArrayOfEntry[m];
      Object localObject = localEntry.getKey();
      int n = localObject.hashCode();
      k += n;
      int i1 = Hashing.smear(n) & this.mask;
      LinkedEntry localLinkedEntry1 = this.table[i1];
      LinkedEntry localLinkedEntry2 = newLinkedEntry(localObject, localEntry.getValue(), localLinkedEntry1);
      this.table[i1] = localLinkedEntry2;
      this.entries[m] = localLinkedEntry2;
      if (localLinkedEntry1 != null)
      {
        if (!localObject.equals(localLinkedEntry1.getKey()));
        for (boolean bool = true; ; bool = false)
        {
          Preconditions.checkArgument(bool, "duplicate key: %s", new Object[] { localObject });
          localLinkedEntry1 = localLinkedEntry1.next();
          break;
        }
      }
    }
    this.keySetHashCode = k;
  }

  private static int chooseTableSize(int paramInt)
  {
    int i = Integer.highestOneBit(paramInt) << 1;
    if (i > 0);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      Preconditions.checkArgument(bool, "table too large: %s", arrayOfObject);
      return i;
    }
  }

  private LinkedEntry<K, V>[] createEntryArray(int paramInt)
  {
    return new LinkedEntry[paramInt];
  }

  private static <K, V> LinkedEntry<K, V> newLinkedEntry(K paramK, V paramV, LinkedEntry<K, V> paramLinkedEntry)
  {
    if (paramLinkedEntry == null);
    for (Object localObject = new TerminalEntry(paramK, paramV); ; localObject = new NonTerminalEntry(paramK, paramV, paramLinkedEntry))
      return localObject;
  }

  public boolean containsValue(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == null);
    label52: 
    while (true)
    {
      return bool;
      LinkedEntry[] arrayOfLinkedEntry = this.entries;
      int i = arrayOfLinkedEntry.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label52;
        if (arrayOfLinkedEntry[j].getValue().equals(paramObject))
        {
          bool = true;
          break;
        }
      }
    }
  }

  public ImmutableSet<Map.Entry<K, V>> entrySet()
  {
    Object localObject = this.entrySet;
    if (localObject == null)
    {
      localObject = new EntrySet(this);
      this.entrySet = ((ImmutableSet)localObject);
    }
    return localObject;
  }

  public V get(Object paramObject)
  {
    Object localObject = null;
    if (paramObject == null);
    label69: 
    while (true)
    {
      return localObject;
      int i = Hashing.smear(paramObject.hashCode()) & this.mask;
      for (LinkedEntry localLinkedEntry = this.table[i]; ; localLinkedEntry = localLinkedEntry.next())
      {
        if (localLinkedEntry == null)
          break label69;
        if (paramObject.equals(localLinkedEntry.getKey()))
        {
          localObject = localLinkedEntry.getValue();
          break;
        }
      }
    }
  }

  public boolean isEmpty()
  {
    return false;
  }

  public ImmutableSet<K> keySet()
  {
    Object localObject = this.keySet;
    if (localObject == null)
    {
      localObject = new KeySet(this);
      this.keySet = ((ImmutableSet)localObject);
    }
    return localObject;
  }

  public int size()
  {
    return this.entries.length;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(size()).append('{');
    Collections2.STANDARD_JOINER.appendTo(localStringBuilder, this.entries);
    return '}';
  }

  public ImmutableCollection<V> values()
  {
    Object localObject = this.values;
    if (localObject == null)
    {
      localObject = new Values(this);
      this.values = ((ImmutableCollection)localObject);
    }
    return localObject;
  }

  private static class EntrySet<K, V> extends ImmutableSet.ArrayImmutableSet<Map.Entry<K, V>>
  {
    final transient RegularImmutableMap<K, V> map;

    EntrySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
    {
      super();
      this.map = paramRegularImmutableMap;
    }

    public boolean contains(Object paramObject)
    {
      boolean bool = false;
      if ((paramObject instanceof Map.Entry))
      {
        Map.Entry localEntry = (Map.Entry)paramObject;
        Object localObject = this.map.get(localEntry.getKey());
        if ((localObject != null) && (localObject.equals(localEntry.getValue())))
          bool = true;
      }
      return bool;
    }
  }

  private static class KeySet<K, V> extends ImmutableSet.TransformedImmutableSet<Map.Entry<K, V>, K>
  {
    final RegularImmutableMap<K, V> map;

    KeySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
    {
      super(paramRegularImmutableMap.keySetHashCode);
      this.map = paramRegularImmutableMap;
    }

    public boolean contains(Object paramObject)
    {
      return this.map.containsKey(paramObject);
    }

    K transform(Map.Entry<K, V> paramEntry)
    {
      return paramEntry.getKey();
    }
  }

  private static abstract interface LinkedEntry<K, V> extends Map.Entry<K, V>
  {
    public abstract LinkedEntry<K, V> next();
  }

  private static final class NonTerminalEntry<K, V> extends ImmutableEntry<K, V>
    implements RegularImmutableMap.LinkedEntry<K, V>
  {
    final RegularImmutableMap.LinkedEntry<K, V> next;

    NonTerminalEntry(K paramK, V paramV, RegularImmutableMap.LinkedEntry<K, V> paramLinkedEntry)
    {
      super(paramV);
      this.next = paramLinkedEntry;
    }

    public RegularImmutableMap.LinkedEntry<K, V> next()
    {
      return this.next;
    }
  }

  private static final class TerminalEntry<K, V> extends ImmutableEntry<K, V>
    implements RegularImmutableMap.LinkedEntry<K, V>
  {
    TerminalEntry(K paramK, V paramV)
    {
      super(paramV);
    }

    public RegularImmutableMap.LinkedEntry<K, V> next()
    {
      return null;
    }
  }

  private static class Values<V> extends ImmutableCollection<V>
  {
    final RegularImmutableMap<?, V> map;

    Values(RegularImmutableMap<?, V> paramRegularImmutableMap)
    {
      this.map = paramRegularImmutableMap;
    }

    public boolean contains(Object paramObject)
    {
      return this.map.containsValue(paramObject);
    }

    public UnmodifiableIterator<V> iterator()
    {
      return new AbstractIndexedListIterator(this.map.entries.length)
      {
        protected V get(int paramAnonymousInt)
        {
          return RegularImmutableMap.Values.this.map.entries[paramAnonymousInt].getValue();
        }
      };
    }

    public int size()
    {
      return this.map.entries.length;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.RegularImmutableMap
 * JD-Core Version:    0.6.2
 */
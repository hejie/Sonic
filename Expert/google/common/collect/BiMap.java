package com.google.common.collect;

import java.util.Map;
import java.util.Set;

public abstract interface BiMap<K, V> extends Map<K, V>
{
  public abstract Set<V> values();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.BiMap
 * JD-Core Version:    0.6.2
 */
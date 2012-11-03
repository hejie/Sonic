package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class Maps
{
  static final Joiner.MapJoiner STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");

  public static <K, V> Map.Entry<K, V> immutableEntry(K paramK, V paramV)
  {
    return new ImmutableEntry(paramK, paramV);
  }

  public static <K, V> HashMap<K, V> newHashMap()
  {
    return new HashMap();
  }

  static String toStringImpl(Map<?, ?> paramMap)
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(paramMap.size()).append('{');
    STANDARD_JOINER.appendTo(localStringBuilder, paramMap);
    return '}';
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.Maps
 * JD-Core Version:    0.6.2
 */
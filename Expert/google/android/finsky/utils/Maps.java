package com.google.android.finsky.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Maps
{
  public static <K, V> HashMap<K, V> newHashMap()
  {
    return new HashMap();
  }

  public static <K, V> LinkedHashMap<K, V> newLinkedHashMap()
  {
    return new LinkedHashMap();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Maps
 * JD-Core Version:    0.6.2
 */
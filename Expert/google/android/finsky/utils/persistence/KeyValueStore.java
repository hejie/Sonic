package com.google.android.finsky.utils.persistence;

import java.util.Map;

public abstract interface KeyValueStore
{
  public abstract void delete(String paramString);

  public abstract Map<String, Map<String, String>> fetchAll();

  public abstract void put(String paramString, Map<String, String> paramMap);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.persistence.KeyValueStore
 * JD-Core Version:    0.6.2
 */
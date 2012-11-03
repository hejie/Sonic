package com.google.android.finsky.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class LruCache<K, V>
{
  private int createCount;
  private int evictionCount;
  private int hitCount;
  private final LinkedHashMap<K, V> map;
  private int maxSize;
  private int missCount;
  private int putCount;
  private int size;

  public LruCache(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    this.maxSize = paramInt;
    this.map = new LinkedHashMap(0, 0.75F, true);
  }

  private int safeSizeOf(K paramK, V paramV)
  {
    int i = sizeOf(paramK, paramV);
    if (i < 0)
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV);
    return i;
  }

  private void trimToSize(int paramInt)
  {
    while (true)
    {
      Map.Entry localEntry;
      if ((this.size > paramInt) && (!this.map.isEmpty()))
      {
        localEntry = (Map.Entry)this.map.entrySet().iterator().next();
        if (localEntry != null);
      }
      else
      {
        if ((this.size >= 0) && ((!this.map.isEmpty()) || (this.size == 0)))
          break;
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
      }
      Object localObject1 = localEntry.getKey();
      Object localObject2 = localEntry.getValue();
      this.map.remove(localObject1);
      this.size -= safeSizeOf(localObject1, localObject2);
      this.evictionCount = (1 + this.evictionCount);
      entryEvicted(localObject1, localObject2);
    }
  }

  protected V create(K paramK)
  {
    return null;
  }

  protected void entryEvicted(K paramK, V paramV)
  {
  }

  public final void evictAll()
  {
    try
    {
      trimToSize(-1);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final V get(K paramK)
  {
    if (paramK == null)
      try
      {
        throw new NullPointerException("key == null");
      }
      finally
      {
      }
    Object localObject1 = this.map.get(paramK);
    if (localObject1 != null)
      this.hitCount = (1 + this.hitCount);
    Object localObject2;
    for (Object localObject3 = localObject1; ; localObject3 = localObject2)
    {
      return localObject3;
      this.missCount = (1 + this.missCount);
      localObject2 = create(paramK);
      if (localObject2 != null)
      {
        this.createCount = (1 + this.createCount);
        this.size += safeSizeOf(paramK, localObject2);
        this.map.put(paramK, localObject2);
        trimToSize(this.maxSize);
      }
    }
  }

  public final V put(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null))
      try
      {
        throw new NullPointerException("key == null || value == null");
      }
      finally
      {
      }
    this.putCount = (1 + this.putCount);
    this.size += safeSizeOf(paramK, paramV);
    Object localObject2 = this.map.put(paramK, paramV);
    if (localObject2 != null)
      this.size -= safeSizeOf(paramK, localObject2);
    trimToSize(this.maxSize);
    return localObject2;
  }

  public final V remove(K paramK)
  {
    if (paramK == null)
      try
      {
        throw new NullPointerException("key == null");
      }
      finally
      {
      }
    Object localObject1 = this.map.remove(paramK);
    if (localObject1 != null)
      this.size -= safeSizeOf(paramK, localObject1);
    return localObject1;
  }

  protected int sizeOf(K paramK, V paramV)
  {
    return 1;
  }

  public final String toString()
  {
    int i = 0;
    try
    {
      int j = this.hitCount + this.missCount;
      if (j != 0)
        i = 100 * this.hitCount / j;
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(this.maxSize);
      arrayOfObject[1] = Integer.valueOf(this.hitCount);
      arrayOfObject[2] = Integer.valueOf(this.missCount);
      arrayOfObject[3] = Integer.valueOf(i);
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", arrayOfObject);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.LruCache
 * JD-Core Version:    0.6.2
 */
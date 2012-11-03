package com.google.android.finsky.library;

import android.util.Log;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class HashMapLibrary extends HashingLibrary
{
  private final Map<LibraryEntry, LibraryEntry> mEntries = Maps.newHashMap();

  public HashMapLibrary(LibraryHasher paramLibraryHasher)
  {
    super(paramLibraryHasher);
  }

  public void add(LibraryEntry paramLibraryEntry)
  {
    try
    {
      super.add(paramLibraryEntry);
      this.mEntries.put(paramLibraryEntry, paramLibraryEntry);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean contains(LibraryEntry paramLibraryEntry)
  {
    try
    {
      boolean bool = this.mEntries.containsKey(paramLibraryEntry);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void dumpState(String paramString1, String paramString2)
  {
    Log.d("FinskyLibrary", paramString2 + "Library (" + paramString1 + ") {");
    Log.d("FinskyLibrary", paramString2 + "  entryCount=" + this.mEntries.size());
    Log.d("FinskyLibrary", paramString2 + "}");
  }

  public LibraryEntry get(LibraryEntry paramLibraryEntry)
  {
    return (LibraryEntry)this.mEntries.get(paramLibraryEntry);
  }

  public Iterator<LibraryEntry> iterator()
  {
    try
    {
      Iterator localIterator = this.mEntries.values().iterator();
      return localIterator;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    try
    {
      super.remove(paramLibraryEntry);
      this.mEntries.remove(paramLibraryEntry);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void reset()
  {
    try
    {
      super.reset();
      this.mEntries.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int size()
  {
    try
    {
      int i = this.mEntries.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.HashMapLibrary
 * JD-Core Version:    0.6.2
 */
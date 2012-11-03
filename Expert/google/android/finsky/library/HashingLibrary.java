package com.google.android.finsky.library;

public abstract class HashingLibrary
  implements Library
{
  final LibraryHasher mHasher;

  public HashingLibrary(LibraryHasher paramLibraryHasher)
  {
    this.mHasher = paramLibraryHasher;
  }

  public void add(LibraryEntry paramLibraryEntry)
  {
    if (!contains(paramLibraryEntry))
      this.mHasher.add(paramLibraryEntry.documentHash);
  }

  public abstract void dumpState(String paramString1, String paramString2);

  abstract LibraryEntry get(LibraryEntry paramLibraryEntry);

  public long getHash()
  {
    return this.mHasher.compute();
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    LibraryEntry localLibraryEntry = get(paramLibraryEntry);
    if (localLibraryEntry != null)
      this.mHasher.remove(localLibraryEntry.documentHash);
  }

  public void reset()
  {
    this.mHasher.reset();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.HashingLibrary
 * JD-Core Version:    0.6.2
 */
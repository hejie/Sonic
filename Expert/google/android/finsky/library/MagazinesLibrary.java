package com.google.android.finsky.library;

public class MagazinesLibrary extends HashMapLibrary
{
  public MagazinesLibrary(LibraryHasher paramLibraryHasher)
  {
    super(paramLibraryHasher);
  }

  public LibrarySubscriptionEntry getSubscriptionEntry(String paramString)
  {
    return (LibrarySubscriptionEntry)get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_MAGAZINE, 6, paramString, 15, 1));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.MagazinesLibrary
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.library;

public abstract interface Library extends Iterable<LibraryEntry>
{
  public abstract boolean contains(LibraryEntry paramLibraryEntry);

  public abstract void remove(LibraryEntry paramLibraryEntry);

  public abstract int size();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.Library
 * JD-Core Version:    0.6.2
 */
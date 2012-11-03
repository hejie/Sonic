package com.google.android.finsky.library;

public abstract interface LibraryHasher
{
  public abstract void add(long paramLong);

  public abstract long compute();

  public abstract void remove(long paramLong);

  public abstract void reset();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryHasher
 * JD-Core Version:    0.6.2
 */
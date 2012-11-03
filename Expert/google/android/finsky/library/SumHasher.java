package com.google.android.finsky.library;

public class SumHasher
  implements LibraryHasher
{
  private long mHash;

  public void add(long paramLong)
  {
    this.mHash = (paramLong + this.mHash);
  }

  public long compute()
  {
    return this.mHash;
  }

  public void remove(long paramLong)
  {
    this.mHash -= paramLong;
  }

  public void reset()
  {
    this.mHash = 0L;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.SumHasher
 * JD-Core Version:    0.6.2
 */
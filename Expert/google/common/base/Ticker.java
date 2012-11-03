package com.google.common.base;

public abstract class Ticker
{
  private static final Ticker SYSTEM_TICKER = new Ticker()
  {
    public long read()
    {
      return Platform.systemNanoTime();
    }
  };

  public static Ticker systemTicker()
  {
    return SYSTEM_TICKER;
  }

  public abstract long read();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.base.Ticker
 * JD-Core Version:    0.6.2
 */
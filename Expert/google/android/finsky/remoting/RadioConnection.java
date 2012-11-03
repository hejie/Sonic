package com.google.android.finsky.remoting;

public abstract interface RadioConnection
{
  public abstract void ensureRouteToHost(String paramString)
    throws RadioConnectionException;

  public abstract void start()
    throws RadioConnectionException;

  public abstract void stop()
    throws RadioConnectionException;
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.RadioConnection
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting;

public class RadioConnectionWithFallback
  implements RadioConnection
{
  private final RadioConnection mFallback;
  private final RadioConnection mPrimary;
  private boolean mUseFallback;

  public RadioConnectionWithFallback(RadioConnection paramRadioConnection1, RadioConnection paramRadioConnection2)
  {
    this.mPrimary = paramRadioConnection1;
    this.mFallback = paramRadioConnection2;
    this.mUseFallback = false;
  }

  public void ensureRouteToHost(String paramString)
    throws RadioConnectionException
  {
    if (this.mUseFallback)
      this.mFallback.ensureRouteToHost(paramString);
    while (true)
    {
      return;
      this.mPrimary.ensureRouteToHost(paramString);
    }
  }

  public void start()
    throws RadioConnectionException
  {
    try
    {
      this.mPrimary.start();
      return;
    }
    catch (RadioConnectionException localRadioConnectionException)
    {
      while (true)
      {
        this.mUseFallback = true;
        this.mFallback.start();
      }
    }
  }

  public void stop()
    throws RadioConnectionException
  {
    if (this.mUseFallback)
      this.mFallback.stop();
    while (true)
    {
      return;
      this.mPrimary.stop();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.RadioConnectionWithFallback
 * JD-Core Version:    0.6.2
 */
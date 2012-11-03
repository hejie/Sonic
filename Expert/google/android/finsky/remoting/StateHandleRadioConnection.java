package com.google.android.finsky.remoting;

import com.google.android.finsky.utils.FinskyLog;

public class StateHandleRadioConnection
  implements RadioConnection
{
  private final RadioConnection mDelegate;
  private int mStartCount = 0;
  State mState = State.INIT;

  public StateHandleRadioConnection(RadioConnection paramRadioConnection)
  {
    this.mDelegate = paramRadioConnection;
  }

  public void ensureRouteToHost(String paramString)
    throws RadioConnectionException
  {
    this.mDelegate.ensureRouteToHost(paramString);
  }

  public void start()
    throws RadioConnectionException
  {
    try
    {
      this.mStartCount = (1 + this.mStartCount);
      State localState1 = this.mState;
      State localState2 = State.INIT;
      if (localState1 == localState2);
      try
      {
        this.mDelegate.start();
        this.mState = State.STARTED;
        return;
      }
      catch (RadioConnectionException localRadioConnectionException)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localRadioConnectionException.getMessage();
        FinskyLog.e("Unable to start radio: %s", arrayOfObject);
        this.mState = State.STOPPED;
        throw localRadioConnectionException;
      }
    }
    finally
    {
    }
  }

  public void stop()
    throws RadioConnectionException
  {
    try
    {
      if (this.mState == State.STARTED)
      {
        this.mStartCount = (-1 + this.mStartCount);
        if (this.mStartCount == 0)
          this.mState = State.STOPPED;
      }
      try
      {
        this.mDelegate.stop();
        return;
      }
      catch (RadioConnectionException localRadioConnectionException)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localRadioConnectionException.getMessage();
        FinskyLog.e("Unable to start radio: %s", arrayOfObject);
        throw localRadioConnectionException;
      }
    }
    finally
    {
    }
  }

  static enum State
  {
    static
    {
      State[] arrayOfState = new State[3];
      arrayOfState[0] = INIT;
      arrayOfState[1] = STARTED;
      arrayOfState[2] = STOPPED;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.StateHandleRadioConnection
 * JD-Core Version:    0.6.2
 */
package com.google.android.play.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoggableHandler extends Handler
{
  public LoggableHandler(String paramString)
  {
    this(paramString, 1);
  }

  private LoggableHandler(String paramString, int paramInt)
  {
    super(startHandlerThread(paramString, paramInt).getLooper());
  }

  private static HandlerThread startHandlerThread(String paramString, int paramInt)
  {
    final AtomicBoolean localAtomicBoolean = new AtomicBoolean(false);
    HandlerThread local1 = new HandlerThread(paramString, paramInt)
    {
      protected void onLooperPrepared()
      {
        synchronized (localAtomicBoolean)
        {
          localAtomicBoolean.set(true);
          localAtomicBoolean.notifyAll();
          return;
        }
      }
    };
    local1.start();
    try
    {
      while (true)
      {
        boolean bool = localAtomicBoolean.get();
        if (bool)
          break;
        try
        {
          localAtomicBoolean.wait();
        }
        catch (InterruptedException localInterruptedException)
        {
        }
      }
      return local1;
    }
    finally
    {
    }
  }

  public void dispatchMessage(Message paramMessage)
  {
    super.dispatchMessage(paramMessage);
  }

  public boolean sendMessageAtTime(Message paramMessage, long paramLong)
  {
    return super.sendMessageAtTime(paramMessage, paramLong);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.utils.LoggableHandler
 * JD-Core Version:    0.6.2
 */
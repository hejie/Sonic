package com.google.android.finsky.remoting;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RadioConnectionImpl
  implements RadioConnection
{
  private final ConnectivityManager mConnMgr;
  private final int mConnectionType;
  private final PhoneFeature mPhoneFeature;

  public RadioConnectionImpl(ConnectivityManager paramConnectivityManager, int paramInt, PhoneFeature paramPhoneFeature)
  {
    this.mConnectionType = paramInt;
    this.mPhoneFeature = paramPhoneFeature;
    this.mConnMgr = paramConnectivityManager;
  }

  private boolean isRadioActive()
  {
    return this.mConnMgr.getNetworkInfo(this.mConnectionType).isConnected();
  }

  private void startRadio()
    throws RadioConnectionException
  {
    int i = this.mConnMgr.startUsingNetworkFeature(0, this.mPhoneFeature.getValue());
    switch (i)
    {
    default:
      throw new RadioConnectionException(this.mPhoneFeature + ": Start network failed - " + i);
    case 1:
      if (FinskyLog.DEBUG)
        FinskyLog.v(this.mPhoneFeature + ": APN request started: " + Thread.currentThread(), new Object[0]);
      break;
    case 0:
    }
  }

  private boolean waitForRadio(int paramInt1, int paramInt2)
    throws RadioConnectionException
  {
    boolean bool = true;
    long l1 = System.currentTimeMillis();
    long l2;
    if (System.currentTimeMillis() < l1 + paramInt1)
      l2 = paramInt2;
    while (true)
    {
      try
      {
        Thread.sleep(l2);
        if (!isRadioActive())
          break;
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l1);
          arrayOfObject[bool] = Integer.valueOf(paramInt1);
          arrayOfObject[2] = Integer.valueOf(paramInt2);
          FinskyLog.v("Radio came up after %dms (timeoutMs=%d, pollIntervalMs=%d).", arrayOfObject);
        }
        return bool;
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new RadioConnectionException(localInterruptedException);
      }
      bool = false;
    }
  }

  public void ensureRouteToHost(String paramString)
    throws RadioConnectionException
  {
    if ((!paramString.startsWith("http://")) && (!paramString.startsWith("https://")))
      paramString = "http://" + paramString;
    Uri localUri = Uri.parse(paramString);
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(localUri.getHost());
      byte[] arrayOfByte = localInetAddress.getAddress();
      int i = (0xFF & arrayOfByte[3]) << 24 | (0xFF & arrayOfByte[2]) << 16 | (0xFF & arrayOfByte[1]) << 8 | 0xFF & arrayOfByte[0];
      if (!this.mConnMgr.requestRouteToHost(this.mConnectionType, i))
        throw new RadioConnectionException("Cannot establish route to " + localInetAddress + " for " + paramString);
    }
    catch (UnknownHostException localUnknownHostException)
    {
      throw new RadioConnectionException("Cannot establish route for " + paramString + ": Unknown host");
    }
  }

  public void start()
    throws RadioConnectionException
  {
    if (isRadioActive())
      this.mConnMgr.startUsingNetworkFeature(0, this.mPhoneFeature.getValue());
    do
    {
      return;
      startRadio();
    }
    while (waitForRadio(((Integer)G.vendingDcbPollTimeoutMs.get()).intValue(), 500));
    throw new RadioConnectionException("Timeout waiting for radio to come up");
  }

  public void stop()
  {
    FinskyLog.d("Giving back radio.", new Object[0]);
    this.mConnMgr.stopUsingNetworkFeature(0, this.mPhoneFeature.getValue());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.RadioConnectionImpl
 * JD-Core Version:    0.6.2
 */
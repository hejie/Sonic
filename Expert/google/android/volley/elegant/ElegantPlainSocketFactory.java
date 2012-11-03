package com.google.android.volley.elegant;

import com.android.volley.VolleyLog;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.HostNameResolver;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class ElegantPlainSocketFactory
  implements SocketFactory
{
  private static final ElegantPlainSocketFactory DEFAULT_FACTORY = new ElegantPlainSocketFactory();
  private final HostNameResolver nameResolver;

  public ElegantPlainSocketFactory()
  {
    this(null);
  }

  public ElegantPlainSocketFactory(HostNameResolver paramHostNameResolver)
  {
    this.nameResolver = paramHostNameResolver;
  }

  public static ElegantPlainSocketFactory getSocketFactory()
  {
    return DEFAULT_FACTORY;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Target host may not be null.");
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameters may not be null.");
    if (paramSocket == null)
      paramSocket = createSocket();
    if ((paramInetAddress != null) || (paramInt2 > 0))
    {
      if (paramInt2 < 0)
        paramInt2 = 0;
      paramSocket.bind(new InetSocketAddress(paramInetAddress, paramInt2));
    }
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    InetSocketAddress localInetSocketAddress;
    if (this.nameResolver != null)
      localInetSocketAddress = new InetSocketAddress(this.nameResolver.resolve(paramString), paramInt1);
    try
    {
      while (true)
      {
        long l1 = System.currentTimeMillis();
        paramSocket.connect(localInetSocketAddress, i);
        long l2 = System.currentTimeMillis() - l1;
        if (VolleyLog.DEBUG)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramString;
          arrayOfObject[1] = Long.valueOf(l2);
          VolleyLog.d("Established connection to [host=%s] in [%s ms]", arrayOfObject);
        }
        return paramSocket;
        localInetSocketAddress = new InetSocketAddress(paramString, paramInt1);
      }
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
    }
    throw new ConnectTimeoutException("Connect to " + localInetSocketAddress + " timed out");
  }

  public Socket createSocket()
  {
    return new Socket();
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public int hashCode()
  {
    return ElegantPlainSocketFactory.class.hashCode();
  }

  public final boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null.");
    if (paramSocket.getClass() != Socket.class)
      throw new IllegalArgumentException("Socket not created by this factory.");
    if (paramSocket.isClosed())
      throw new IllegalArgumentException("Socket is closed.");
    return false;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.elegant.ElegantPlainSocketFactory
 * JD-Core Version:    0.6.2
 */
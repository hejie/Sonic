package com.google.android.volley;

import android.content.ContentResolver;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.util.EventLog;
import android.util.Log;
import com.google.android.common.http.NetworkStatsEntity;
import com.google.android.common.http.UrlRules;
import com.google.android.common.http.UrlRules.Rule;
import com.google.android.gsf.Gservices;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class GoogleHttpClient
  implements HttpClient
{
  private final String mAppName;
  private final AndroidHttpClient mClient;
  private final ThreadLocal<Boolean> mConnectionAllocated = new ThreadLocal();
  private final ContentResolver mResolver;

  public GoogleHttpClient(Context paramContext, String paramString, boolean paramBoolean)
  {
    String str1 = paramString + " (" + Build.DEVICE + " " + Build.ID + ")";
    if (paramBoolean)
      str1 = str1 + "; gzip";
    this.mClient = AndroidHttpClient.newInstance(str1, paramContext);
    this.mResolver = paramContext.getContentResolver();
    this.mAppName = paramString;
    SchemeRegistry localSchemeRegistry = getConnectionManager().getSchemeRegistry();
    Iterator localIterator = localSchemeRegistry.getSchemeNames().iterator();
    if (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      Scheme localScheme = localSchemeRegistry.unregister(str2);
      SocketFactory localSocketFactory = localScheme.getSocketFactory();
      if ((localSocketFactory instanceof LayeredSocketFactory));
      for (Object localObject = new WrappedLayeredSocketFactory((LayeredSocketFactory)localSocketFactory, null); ; localObject = new WrappedSocketFactory(localSocketFactory, null))
      {
        localSchemeRegistry.register(new Scheme(str2, (SocketFactory)localObject, localScheme.getDefaultPort()));
        break;
      }
    }
  }

  private static RequestWrapper wrapRequest(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    try
    {
      if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest));
      for (Object localObject = new EntityEnclosingRequestWrapper((HttpEntityEnclosingRequest)paramHttpUriRequest); ; localObject = new RequestWrapper(paramHttpUriRequest))
      {
        ((RequestWrapper)localObject).resetHeaders();
        return localObject;
      }
    }
    catch (ProtocolException localProtocolException)
    {
      throw new ClientProtocolException(localProtocolException);
    }
  }

  public void enableCurlLogging(String paramString, int paramInt)
  {
    this.mClient.enableCurlLogging(paramString, paramInt);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return this.mClient.execute(paramHttpHost, paramHttpRequest, paramResponseHandler);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return this.mClient.execute(paramHttpHost, paramHttpRequest, paramResponseHandler, paramHttpContext);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return this.mClient.execute(paramHttpUriRequest, paramResponseHandler);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return this.mClient.execute(paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException
  {
    return this.mClient.execute(paramHttpHost, paramHttpRequest);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return this.mClient.execute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return execute(paramHttpUriRequest, (HttpContext)null);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    String str1 = paramHttpUriRequest.getURI().toString();
    UrlRules.Rule localRule = UrlRules.getRules(this.mResolver).matchRule(str1);
    String str2 = localRule.apply(str1);
    if (str2 == null)
    {
      Log.w("GoogleHttpClient", "Blocked by " + localRule.mName + ": " + str1);
      throw new BlockedRequestException(localRule);
    }
    HttpResponse localHttpResponse;
    if (str2 == str1)
      localHttpResponse = executeWithoutRewriting(paramHttpUriRequest, paramHttpContext);
    while (true)
    {
      return localHttpResponse;
      try
      {
        URI localURI = new URI(str2);
        RequestWrapper localRequestWrapper = wrapRequest(paramHttpUriRequest);
        localRequestWrapper.setURI(localURI);
        localHttpResponse = executeWithoutRewriting(localRequestWrapper, paramHttpContext);
      }
      catch (URISyntaxException localURISyntaxException)
      {
        throw new RuntimeException("Bad URL from rule: " + localRule.mName, localURISyntaxException);
      }
    }
  }

  public HttpResponse executeWithoutRewriting(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    long l1 = SystemClock.elapsedRealtime();
    try
    {
      this.mConnectionAllocated.set(null);
      if (Gservices.getBoolean(this.mResolver, "http_stats", false))
      {
        int m = Process.myUid();
        long l4 = TrafficStats.getUidTxBytes(m);
        long l5 = TrafficStats.getUidRxBytes(m);
        localObject5 = this.mClient.execute(paramHttpUriRequest, paramHttpContext);
        if (localObject5 == null)
        {
          localHttpEntity = null;
          if (localHttpEntity != null)
          {
            long l6 = SystemClock.elapsedRealtime();
            long l7 = l6 - l1;
            NetworkStatsEntity localNetworkStatsEntity = new NetworkStatsEntity(localHttpEntity, this.mAppName, m, l4, l5, l7, l6);
            ((HttpResponse)localObject5).setEntity(localNetworkStatsEntity);
          }
          j = ((HttpResponse)localObject5).getStatusLine().getStatusCode();
        }
      }
    }
    finally
    {
      try
      {
        while (true)
        {
          int j;
          long l3 = SystemClock.elapsedRealtime() - l1;
          if ((this.mConnectionAllocated.get() != null) || (j < 0))
            break;
          k = 1;
          Object[] arrayOfObject2 = new Object[4];
          arrayOfObject2[0] = Long.valueOf(l3);
          arrayOfObject2[1] = Integer.valueOf(j);
          arrayOfObject2[2] = this.mAppName;
          arrayOfObject2[3] = Integer.valueOf(k);
          EventLog.writeEvent(203002, arrayOfObject2);
          return localObject5;
          HttpEntity localHttpEntity = ((HttpResponse)localObject5).getEntity();
          continue;
          HttpResponse localHttpResponse = this.mClient.execute(paramHttpUriRequest, paramHttpContext);
          Object localObject5 = localHttpResponse;
        }
        while (true)
        {
          int i = 0;
          try
          {
            while (true)
            {
              Object[] arrayOfObject1 = new Object[4];
              Object localObject3;
              arrayOfObject1[0] = Long.valueOf(localObject3);
              arrayOfObject1[1] = Integer.valueOf(-1);
              arrayOfObject1[2] = this.mAppName;
              arrayOfObject1[3] = Integer.valueOf(i);
              EventLog.writeEvent(203002, arrayOfObject1);
              Object localObject1;
              throw localObject1;
              localObject2 = finally;
              long l2 = SystemClock.elapsedRealtime() - l1;
              Object localObject4 = this.mConnectionAllocated.get();
              if ((localObject4 != null) || (-1 < 0))
                break;
              i = 1;
            }
          }
          catch (Exception localException1)
          {
            while (true)
              Log.e("GoogleHttpClient", "Error recording stats", localException1);
          }
        }
      }
      catch (Exception localException2)
      {
        while (true)
        {
          Log.e("GoogleHttpClient", "Error recording stats", localException2);
          continue;
          int k = 0;
        }
      }
    }
  }

  public ClientConnectionManager getConnectionManager()
  {
    return this.mClient.getConnectionManager();
  }

  public HttpParams getParams()
  {
    return this.mClient.getParams();
  }

  public static class BlockedRequestException extends IOException
  {
    BlockedRequestException(UrlRules.Rule paramRule)
    {
      super();
    }
  }

  private class WrappedLayeredSocketFactory extends GoogleHttpClient.WrappedSocketFactory
    implements LayeredSocketFactory
  {
    private LayeredSocketFactory mDelegate;

    private WrappedLayeredSocketFactory(LayeredSocketFactory arg2)
    {
      super(localSocketFactory, null);
      this.mDelegate = localSocketFactory;
    }

    public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
      throws IOException
    {
      return this.mDelegate.createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }

  private class WrappedSocketFactory
    implements SocketFactory
  {
    private SocketFactory mDelegate;

    private WrappedSocketFactory(SocketFactory arg2)
    {
      Object localObject;
      this.mDelegate = localObject;
    }

    public final Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
      throws IOException
    {
      GoogleHttpClient.this.mConnectionAllocated.set(Boolean.TRUE);
      return this.mDelegate.connectSocket(paramSocket, paramString, paramInt1, paramInetAddress, paramInt2, paramHttpParams);
    }

    public final Socket createSocket()
      throws IOException
    {
      return this.mDelegate.createSocket();
    }

    public final boolean isSecure(Socket paramSocket)
    {
      return this.mDelegate.isSecure(paramSocket);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.GoogleHttpClient
 * JD-Core Version:    0.6.2
 */
package com.google.android.volley;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Looper;
import android.util.Log;
import com.google.android.volley.elegant.ElegantPlainSocketFactory;
import com.google.android.volley.elegant.ElegantRequestDirector;
import com.google.android.volley.elegant.ElegantThreadSafeConnManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.RedirectHandler;
import org.apache.http.client.RequestDirector;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerParamBean;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;

public final class AndroidHttpClient
  implements HttpClient
{
  public static long DEFAULT_SYNC_MIN_GZIP_BYTES = 256L;
  private static final HttpRequestInterceptor sThreadCheckInterceptor = new HttpRequestInterceptor()
  {
    public void process(HttpRequest paramAnonymousHttpRequest, HttpContext paramAnonymousHttpContext)
    {
      if ((Looper.myLooper() != null) && (Looper.myLooper() == Looper.getMainLooper()))
        throw new RuntimeException("This thread forbids HTTP requests");
    }
  };
  private volatile LoggingConfiguration curlConfiguration;
  private final HttpClient delegate;
  private RuntimeException mLeakedException = new IllegalStateException("AndroidHttpClient created and never closed");

  private AndroidHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.delegate = new DefaultHttpClient(paramClientConnectionManager, paramHttpParams)
    {
      protected RequestDirector createClientRequestDirector(HttpRequestExecutor paramAnonymousHttpRequestExecutor, ClientConnectionManager paramAnonymousClientConnectionManager, ConnectionReuseStrategy paramAnonymousConnectionReuseStrategy, ConnectionKeepAliveStrategy paramAnonymousConnectionKeepAliveStrategy, HttpRoutePlanner paramAnonymousHttpRoutePlanner, HttpProcessor paramAnonymousHttpProcessor, HttpRequestRetryHandler paramAnonymousHttpRequestRetryHandler, RedirectHandler paramAnonymousRedirectHandler, AuthenticationHandler paramAnonymousAuthenticationHandler1, AuthenticationHandler paramAnonymousAuthenticationHandler2, UserTokenHandler paramAnonymousUserTokenHandler, HttpParams paramAnonymousHttpParams)
      {
        return new ElegantRequestDirector(paramAnonymousHttpRequestExecutor, paramAnonymousClientConnectionManager, paramAnonymousConnectionReuseStrategy, paramAnonymousConnectionKeepAliveStrategy, paramAnonymousHttpRoutePlanner, paramAnonymousHttpProcessor, paramAnonymousHttpRequestRetryHandler, paramAnonymousRedirectHandler, paramAnonymousAuthenticationHandler1, paramAnonymousAuthenticationHandler2, paramAnonymousUserTokenHandler, paramAnonymousHttpParams);
      }

      protected HttpContext createHttpContext()
      {
        BasicHttpContext localBasicHttpContext = new BasicHttpContext();
        localBasicHttpContext.setAttribute("http.authscheme-registry", getAuthSchemes());
        localBasicHttpContext.setAttribute("http.cookiespec-registry", getCookieSpecs());
        localBasicHttpContext.setAttribute("http.auth.credentials-provider", getCredentialsProvider());
        return localBasicHttpContext;
      }

      protected BasicHttpProcessor createHttpProcessor()
      {
        BasicHttpProcessor localBasicHttpProcessor = super.createHttpProcessor();
        localBasicHttpProcessor.addRequestInterceptor(AndroidHttpClient.sThreadCheckInterceptor);
        localBasicHttpProcessor.addRequestInterceptor(new AndroidHttpClient.CurlLogger(AndroidHttpClient.this, null));
        return localBasicHttpProcessor;
      }
    };
  }

  public static AndroidHttpClient newInstance(String paramString, Context paramContext)
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpClientParams.setRedirecting(localBasicHttpParams, false);
    if (paramContext == null);
    for (SSLSessionCache localSSLSessionCache = null; ; localSSLSessionCache = new SSLSessionCache(paramContext))
    {
      HttpProtocolParams.setUserAgent(localBasicHttpParams, paramString);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", ElegantPlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(5000, localSSLSessionCache), 443));
      ConnManagerParamBean localConnManagerParamBean = new ConnManagerParamBean(localBasicHttpParams);
      localConnManagerParamBean.setConnectionsPerRoute(new ConnPerRouteBean(4));
      localConnManagerParamBean.setMaxTotalConnections(8);
      return new AndroidHttpClient(new ElegantThreadSafeConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
    }
  }

  private static String toCurl(HttpUriRequest paramHttpUriRequest, boolean paramBoolean)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("curl ");
    Header[] arrayOfHeader = paramHttpUriRequest.getAllHeaders();
    int i = arrayOfHeader.length;
    int j = 0;
    if (j < i)
    {
      Header localHeader = arrayOfHeader[j];
      if ((!paramBoolean) && ((localHeader.getName().equals("Authorization")) || (localHeader.getName().equals("Cookie"))));
      while (true)
      {
        j++;
        break;
        localStringBuilder.append("--header \"");
        localStringBuilder.append(localHeader.toString().trim());
        localStringBuilder.append("\" ");
      }
    }
    URI localURI = paramHttpUriRequest.getURI();
    if ((paramHttpUriRequest instanceof RequestWrapper))
    {
      HttpRequest localHttpRequest = ((RequestWrapper)paramHttpUriRequest).getOriginal();
      if ((localHttpRequest instanceof HttpUriRequest))
        localURI = ((HttpUriRequest)localHttpRequest).getURI();
    }
    localStringBuilder.append("\"");
    localStringBuilder.append(localURI);
    localStringBuilder.append("\"");
    if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest))
    {
      HttpEntity localHttpEntity = ((HttpEntityEnclosingRequest)paramHttpUriRequest).getEntity();
      if ((localHttpEntity != null) && (localHttpEntity.isRepeatable()))
      {
        if (localHttpEntity.getContentLength() >= 1024L)
          break label274;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        localHttpEntity.writeTo(localByteArrayOutputStream);
        String str = localByteArrayOutputStream.toString();
        localStringBuilder.append(" --data-ascii \"").append(str).append("\"");
      }
    }
    while (true)
    {
      return localStringBuilder.toString();
      label274: localStringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
    }
  }

  public void enableCurlLogging(String paramString, int paramInt)
  {
    if (paramString == null)
      throw new NullPointerException("name");
    if ((paramInt < 2) || (paramInt > 7))
      throw new IllegalArgumentException("Level is out of range [2..7]");
    this.curlConfiguration = new LoggingConfiguration(paramString, paramInt, null);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest, paramResponseHandler);
  }

  public <T> T execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest, paramResponseHandler, paramHttpContext);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler)
    throws IOException, ClientProtocolException
  {
    return this.delegate.execute(paramHttpUriRequest, paramResponseHandler);
  }

  public <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<? extends T> paramResponseHandler, HttpContext paramHttpContext)
    throws IOException, ClientProtocolException
  {
    return this.delegate.execute(paramHttpUriRequest, paramResponseHandler, paramHttpContext);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
    throws IOException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest);
  }

  public HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return this.delegate.execute(paramHttpHost, paramHttpRequest, paramHttpContext);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    return this.delegate.execute(paramHttpUriRequest);
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest, HttpContext paramHttpContext)
    throws IOException
  {
    return this.delegate.execute(paramHttpUriRequest, paramHttpContext);
  }

  protected void finalize()
    throws Throwable
  {
    super.finalize();
    if (this.mLeakedException != null)
    {
      Log.e("AndroidHttpClient", "Leak found", this.mLeakedException);
      this.mLeakedException = null;
    }
  }

  public ClientConnectionManager getConnectionManager()
  {
    return this.delegate.getConnectionManager();
  }

  public HttpParams getParams()
  {
    return this.delegate.getParams();
  }

  private class CurlLogger
    implements HttpRequestInterceptor
  {
    private CurlLogger()
    {
    }

    public void process(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
      throws IOException
    {
      AndroidHttpClient.LoggingConfiguration localLoggingConfiguration = AndroidHttpClient.this.curlConfiguration;
      if ((localLoggingConfiguration != null) && (AndroidHttpClient.LoggingConfiguration.access$400(localLoggingConfiguration)) && ((paramHttpRequest instanceof HttpUriRequest)))
        AndroidHttpClient.LoggingConfiguration.access$600(localLoggingConfiguration, AndroidHttpClient.toCurl((HttpUriRequest)paramHttpRequest, true));
    }
  }

  private static class LoggingConfiguration
  {
    private final int level;
    private final String tag;

    private LoggingConfiguration(String paramString, int paramInt)
    {
      this.tag = paramString;
      this.level = paramInt;
    }

    private boolean isLoggable()
    {
      return Log.isLoggable(this.tag, this.level);
    }

    private void println(String paramString)
    {
      Log.println(this.level, this.tag, paramString);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.AndroidHttpClient
 * JD-Core Version:    0.6.2
 */
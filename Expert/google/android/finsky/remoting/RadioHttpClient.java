package com.google.android.finsky.remoting;

import android.net.Proxy;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.ByteArrayEntity;

public class RadioHttpClient<E extends HttpStack,  extends SupportsProxy>
  implements HttpStack
{
  private final E mHttpStack;
  private final RadioConnectionFactory mRadioConnFactory;
  private RadioConnection mRadioConnection;

  public RadioHttpClient(E paramE, RadioConnectionFactory paramRadioConnectionFactory)
  {
    this.mHttpStack = paramE;
    this.mRadioConnFactory = paramRadioConnectionFactory;
  }

  private void ensureRouteToHost(String paramString)
    throws IOException
  {
    try
    {
      this.mRadioConnection.ensureRouteToHost(paramString);
      return;
    }
    catch (RadioConnectionException localRadioConnectionException)
    {
      throw new IOException(localRadioConnectionException.getMessage());
    }
  }

  private void fetchEntity(HttpResponse paramHttpResponse)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramHttpResponse.getEntity().writeTo(localByteArrayOutputStream);
    localByteArrayOutputStream.close();
    paramHttpResponse.setEntity(new ByteArrayEntity(localByteArrayOutputStream.toByteArray()));
  }

  private void setProxyForRequest(Request<?> paramRequest)
    throws IOException
  {
    String str = (String)G.vendingDcbProxyHost.get();
    if (str == null)
      str = Proxy.getDefaultHost();
    int i = ((Integer)G.vendingDcbProxyPort.get()).intValue();
    if (i == -1)
      i = Proxy.getDefaultPort();
    if ((!TextUtils.isEmpty(str)) && (i > 0))
    {
      ensureRouteToHost(str);
      ((SupportsProxy)this.mHttpStack).setProxy(str, i);
    }
    while (true)
    {
      return;
      ((SupportsProxy)this.mHttpStack).clearProxy();
      ensureRouteToHost(paramRequest.getUrl());
    }
  }

  private void throwExceptionIfError(HttpResponse paramHttpResponse)
    throws IOException
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if (i == 200)
      return;
    throw new IOException("Unexpected HTTP status code from carrier: " + i + " " + paramHttpResponse.getStatusLine().getReasonPhrase());
  }

  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    this.mRadioConnection = this.mRadioConnFactory.createNewConnection();
    try
    {
      this.mRadioConnection.start();
      setProxyForRequest(paramRequest);
      HttpResponse localHttpResponse = this.mHttpStack.performRequest(paramRequest, paramMap);
      fetchEntity(localHttpResponse);
      throwExceptionIfError(localHttpResponse);
      try
      {
        this.mRadioConnection.stop();
        return localHttpResponse;
      }
      catch (RadioConnectionException localRadioConnectionException3)
      {
        while (true)
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = localRadioConnectionException3.getMessage();
          FinskyLog.w("Unable to stop radio: %s", arrayOfObject3);
        }
      }
    }
    catch (RadioConnectionException localRadioConnectionException2)
    {
      localRadioConnectionException2 = localRadioConnectionException2;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localRadioConnectionException2.getMessage();
      FinskyLog.e("Unable to start radio: %s", arrayOfObject2);
      throw new IOException("Unable to start radio: " + localRadioConnectionException2.getMessage());
    }
    finally
    {
    }
    try
    {
      this.mRadioConnection.stop();
      throw localObject;
    }
    catch (RadioConnectionException localRadioConnectionException1)
    {
      while (true)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localRadioConnectionException1.getMessage();
        FinskyLog.w("Unable to stop radio: %s", arrayOfObject1);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.RadioHttpClient
 * JD-Core Version:    0.6.2
 */
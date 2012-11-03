package com.google.android.finsky.remoting;

import android.content.Context;
import com.google.android.volley.GoogleHttpClientStack;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnRouteParams;

public class GoogleProxyHttpClientStack extends GoogleHttpClientStack
  implements SupportsProxy
{
  private String proxyHost;
  private int proxyPort;

  public GoogleProxyHttpClientStack(Context paramContext)
  {
    super(paramContext);
  }

  public void clearProxy()
  {
    this.proxyHost = null;
    this.proxyPort = -1;
  }

  protected void onPrepareRequest(HttpUriRequest paramHttpUriRequest)
  {
    if ((this.proxyHost != null) && (this.proxyPort > 0))
      ConnRouteParams.setDefaultProxy(paramHttpUriRequest.getParams(), new HttpHost(this.proxyHost, this.proxyPort));
  }

  public void setProxy(String paramString, int paramInt)
  {
    this.proxyHost = paramString;
    this.proxyPort = paramInt;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.GoogleProxyHttpClientStack
 * JD-Core Version:    0.6.2
 */
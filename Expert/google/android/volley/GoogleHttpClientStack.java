package com.google.android.volley;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpClientStack;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

public class GoogleHttpClientStack extends HttpClientStack
{
  public GoogleHttpClientStack(Context paramContext)
  {
    this(paramContext, false);
  }

  public GoogleHttpClientStack(Context paramContext, boolean paramBoolean)
  {
    this(new GoogleHttpClient(paramContext, "unused/0", true), paramBoolean);
  }

  private GoogleHttpClientStack(GoogleHttpClient paramGoogleHttpClient, boolean paramBoolean)
  {
    super(paramGoogleHttpClient);
    if ((VolleyLog.DEBUG) && (paramBoolean))
      paramGoogleHttpClient.enableCurlLogging(VolleyLog.TAG, 2);
  }

  public HttpResponse performRequest(Request<?> paramRequest, Map<String, String> paramMap)
    throws IOException, AuthFailureError
  {
    return super.performRequest(paramRequest, paramMap);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.GoogleHttpClientStack
 * JD-Core Version:    0.6.2
 */
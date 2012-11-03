package com.google.android.finsky.api;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.protobuf.micro.MessageMicro;

public class ProtoDfeRequest<T extends MessageMicro> extends DfeRequest<T>
{
  private final MessageMicro mRequest;

  public ProtoDfeRequest(String paramString, MessageMicro paramMessageMicro, DfeApiContext paramDfeApiContext, Class<T> paramClass, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramString, paramDfeApiContext, paramClass, paramListener, paramErrorListener);
    this.mRequest = paramMessageMicro;
    setShouldCache(false);
  }

  public byte[] getPostBody()
  {
    return this.mRequest.toByteArray();
  }

  public String getPostBodyContentType()
  {
    return "application/x-protobuf";
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.ProtoDfeRequest
 * JD-Core Version:    0.6.2
 */
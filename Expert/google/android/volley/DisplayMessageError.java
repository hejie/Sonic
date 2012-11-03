package com.google.android.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

public abstract class DisplayMessageError extends VolleyError
{
  String mDisplayErrorHtml;

  public DisplayMessageError()
  {
  }

  public DisplayMessageError(NetworkResponse paramNetworkResponse)
  {
    super(paramNetworkResponse);
  }

  public DisplayMessageError(String paramString)
  {
    this.mDisplayErrorHtml = paramString;
  }

  public String getDisplayErrorHtml()
  {
    return this.mDisplayErrorHtml;
  }

  public String toString()
  {
    return "DisplayErrorMessage[" + this.mDisplayErrorHtml + "]";
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.DisplayMessageError
 * JD-Core Version:    0.6.2
 */
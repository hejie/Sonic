package com.google.android.finsky.utils;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;

public class DenyAllNetwork
  implements Network
{
  public NetworkResponse performRequest(Request<?> paramRequest)
    throws NoConnectionError
  {
    throw new BgDataDisabledError();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.DenyAllNetwork
 * JD-Core Version:    0.6.2
 */
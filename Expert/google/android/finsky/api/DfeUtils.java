package com.google.android.finsky.api;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class DfeUtils
{
  public static String createDetailsUrlFromId(String paramString)
  {
    return "details?doc=" + paramString;
  }

  public static String formSearchUrl(String paramString, int paramInt)
  {
    return DfeApi.SEARCH_CHANNEL_URI.buildUpon().appendQueryParameter("c", Integer.toString(paramInt)).appendQueryParameter("q", paramString).build().toString();
  }

  public static String getLegacyErrorCode(VolleyError paramVolleyError)
  {
    String str;
    if ((paramVolleyError instanceof ServerError))
      str = "SERVER";
    while (true)
    {
      return str;
      if ((paramVolleyError instanceof AuthFailureError))
        str = "AUTH";
      else if ((paramVolleyError instanceof NetworkError))
        str = "NETWORK";
      else if ((paramVolleyError instanceof TimeoutError))
        str = "TIMEOUT";
      else if ((paramVolleyError instanceof ParseError))
        str = "SERVER";
      else
        str = "SERVER";
    }
  }

  public static boolean isSameDocumentDetailsUrl(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    for (boolean bool = false; ; bool = TextUtils.equals(Uri.parse(paramString1).getQueryParameter("doc"), Uri.parse(paramString2).getQueryParameter("doc")))
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeUtils
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.volley.DisplayMessageError;

public class ErrorStrings
{
  public static String get(Context paramContext, VolleyError paramVolleyError)
  {
    String str;
    if ((paramVolleyError instanceof DisplayMessageError))
      str = ((DisplayMessageError)paramVolleyError).getDisplayErrorHtml();
    while (true)
    {
      return str;
      if ((paramVolleyError instanceof AuthFailureError))
      {
        str = paramContext.getString(2131165443);
      }
      else if ((paramVolleyError instanceof ServerError))
      {
        str = paramContext.getString(2131165441);
      }
      else if ((paramVolleyError instanceof TimeoutError))
      {
        str = paramContext.getString(2131165436);
      }
      else if ((paramVolleyError instanceof NetworkError))
      {
        str = paramContext.getString(2131165435);
      }
      else
      {
        FinskyLog.d("No specific error message for: %s", new Object[] { paramVolleyError });
        str = paramContext.getString(2131165435);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ErrorStrings
 * JD-Core Version:    0.6.2
 */
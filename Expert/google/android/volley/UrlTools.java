package com.google.android.volley;

import android.content.Context;
import com.google.android.common.http.UrlRules;
import com.google.android.common.http.UrlRules.Rule;

public class UrlTools
{
  public static String rewrite(Context paramContext, String paramString)
  {
    return UrlRules.getRules(paramContext.getContentResolver()).matchRule(paramString).apply(paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.UrlTools
 * JD-Core Version:    0.6.2
 */
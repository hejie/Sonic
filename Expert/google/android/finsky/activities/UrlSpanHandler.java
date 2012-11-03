package com.google.android.finsky.activities;

import android.text.style.URLSpan;
import android.view.View;

public abstract class UrlSpanHandler extends URLSpan
{
  String mUrl;

  public UrlSpanHandler(String paramString)
  {
    super(paramString);
    this.mUrl = paramString;
  }

  public void onClick(View paramView)
  {
    onUrlClick(paramView, this.mUrl);
  }

  public abstract void onUrlClick(View paramView, String paramString);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.UrlSpanHandler
 * JD-Core Version:    0.6.2
 */
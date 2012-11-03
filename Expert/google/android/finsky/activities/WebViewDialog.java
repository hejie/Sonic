package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

public class WebViewDialog extends FragmentActivity
{
  public static Intent getIntent(Context paramContext, int paramInt, String paramString)
  {
    Intent localIntent = new Intent(paramContext, WebViewDialog.class);
    localIntent.putExtra("android.intent.extra.TITLE", paramInt);
    localIntent.putExtra("android.intent.extra.STREAM", paramString);
    return localIntent;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTitle(getIntent().getIntExtra("android.intent.extra.TITLE", 2131165393));
    setContentView(2130968578);
    ((WebView)findViewById(2131230739)).loadUrl(getIntent().getStringExtra("android.intent.extra.STREAM"));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.WebViewDialog
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QSBSuggestionsActivity2 extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    if ("android.intent.action.VIEW".equals(localIntent.getAction()))
    {
      localIntent.setClass(this, MainActivity.class);
      startActivity(localIntent);
    }
    finish();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.QSBSuggestionsActivity2
 * JD-Core Version:    0.6.2
 */
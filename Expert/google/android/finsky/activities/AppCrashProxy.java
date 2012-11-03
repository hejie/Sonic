package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AppCrashProxy extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    localIntent.setPackage("com.google.android.feedback");
    localIntent.setComponent(null);
    startActivity(localIntent);
    finish();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AppCrashProxy
 * JD-Core Version:    0.6.2
 */
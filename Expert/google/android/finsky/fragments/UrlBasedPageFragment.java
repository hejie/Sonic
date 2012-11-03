package com.google.android.finsky.fragments;

import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.DfeToc;

public abstract class UrlBasedPageFragment extends PageFragment
{
  protected String mUrl;

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mUrl = getArguments().getString("finsky.UrlBasedPageFragment.url");
    FinskyApp.get().getEventLogger().logView(this.mUrl, null, null);
  }

  protected void setDfeTocAndUrl(DfeToc paramDfeToc, String paramString)
  {
    super.setDfeToc(paramDfeToc);
    setArgument("finsky.UrlBasedPageFragment.url", paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.UrlBasedPageFragment
 * JD-Core Version:    0.6.2
 */
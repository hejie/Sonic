package com.google.android.finsky.analytics;

public abstract interface Analytics
{
  public abstract void logListViewOnPage(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract void logPageView(String paramString1, String paramString2, String paramString3);

  public abstract void logTagAndPackage(String paramString1, String paramString2, String paramString3);

  public abstract void logTagAndPackage(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract void reset();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.analytics.Analytics
 * JD-Core Version:    0.6.2
 */
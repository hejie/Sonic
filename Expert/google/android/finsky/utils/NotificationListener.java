package com.google.android.finsky.utils;

public abstract interface NotificationListener
{
  public abstract boolean showAppAlert(String paramString1, String paramString2, String paramString3, int paramInt);

  public abstract boolean showAppNotification(String paramString1, String paramString2, String paramString3);

  public abstract boolean showDocAlert(String paramString1, String paramString2, String paramString3, String paramString4);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.NotificationListener
 * JD-Core Version:    0.6.2
 */
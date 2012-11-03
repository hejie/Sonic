package com.google.android.finsky.analytics;

import com.google.android.play.analytics.EventLogger;

public class FinskyEventLog
{
  private final EventLogger mEventLogger;

  public FinskyEventLog(EventLogger paramEventLogger)
  {
    this.mEventLogger = paramEventLogger;
  }

  public static String getCorpusButtonDocId(int paramInt)
  {
    return "synthetic-doc-id-" + paramInt;
  }

  public void logClick(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (this.mEventLogger == null);
    while (true)
    {
      return;
      this.mEventLogger.logEvent("c", new Object[] { "cidi", paramString1, "c", paramString2, "d", paramString3, "lc", paramString4, "lu", paramString5 });
    }
  }

  public void logDeepLink(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (this.mEventLogger == null);
    while (true)
    {
      return;
      EventLogger localEventLogger = this.mEventLogger;
      Object[] arrayOfObject = new Object[10];
      arrayOfObject[0] = "c";
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = "d";
      arrayOfObject[3] = paramString2;
      arrayOfObject[4] = "isDirectPurchase";
      arrayOfObject[5] = Boolean.valueOf(paramBoolean1);
      arrayOfObject[6] = "isRedeemGiftCardLink";
      arrayOfObject[7] = Boolean.valueOf(paramBoolean2);
      arrayOfObject[8] = "isUnmatchedUrl";
      arrayOfObject[9] = Boolean.valueOf(paramBoolean3);
      localEventLogger.logEvent("deepLink", arrayOfObject);
    }
  }

  public void logSearch(String paramString1, String paramString2)
  {
    if (this.mEventLogger == null);
    while (true)
    {
      return;
      this.mEventLogger.logEvent("s", new Object[] { "c", paramString1, "d", paramString2 });
    }
  }

  public void logTag(String paramString, Object[] paramArrayOfObject)
  {
    if (this.mEventLogger == null);
    while (true)
    {
      return;
      this.mEventLogger.logEvent(paramString, paramArrayOfObject);
    }
  }

  public void logView(String paramString1, String paramString2, String paramString3)
  {
    if (this.mEventLogger == null);
    while (true)
    {
      return;
      this.mEventLogger.logEvent("v", new Object[] { "c", paramString1, "lc", paramString2, "lu", paramString3 });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.analytics.FinskyEventLog
 * JD-Core Version:    0.6.2
 */
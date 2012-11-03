package com.google.android.finsky.library;

public class LibrarySubscriptionEntry extends LibraryEntry
{
  public final long initiationTimestampMs;
  public final boolean isAutoRenewing;
  public final long trialUntilTimestampMs;
  public final long validUntilTimestampMs;

  public LibrarySubscriptionEntry(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean, long paramLong4)
  {
    super(paramString1, paramString2, paramInt1, paramString3, 15, paramInt2, paramLong4);
    this.initiationTimestampMs = paramLong1;
    this.validUntilTimestampMs = paramLong2;
    this.trialUntilTimestampMs = paramLong3;
    this.isAutoRenewing = paramBoolean;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibrarySubscriptionEntry
 * JD-Core Version:    0.6.2
 */
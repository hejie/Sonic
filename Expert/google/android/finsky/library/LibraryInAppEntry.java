package com.google.android.finsky.library;

public class LibraryInAppEntry extends LibraryEntry
{
  public final String signature;
  public final String signedPurchaseData;

  public LibraryInAppEntry(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, long paramLong)
  {
    super(paramString1, paramString2, 3, paramString3, 11, paramInt, paramLong);
    this.signedPurchaseData = paramString4;
    this.signature = paramString5;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryInAppEntry
 * JD-Core Version:    0.6.2
 */
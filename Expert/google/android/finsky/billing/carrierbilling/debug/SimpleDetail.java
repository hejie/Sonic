package com.google.android.finsky.billing.carrierbilling.debug;

public class SimpleDetail
  implements DcbDetail
{
  private final String mTitle;
  private final String mValue;

  public SimpleDetail(String paramString1, String paramString2)
  {
    this.mTitle = paramString1;
    this.mValue = paramString2;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public String getValue()
  {
    return this.mValue;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.debug.SimpleDetail
 * JD-Core Version:    0.6.2
 */
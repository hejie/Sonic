package com.google.android.finsky.billing.carrierbilling.debug;

import com.google.android.finsky.config.GservicesValue;

public class GServicesDetail<E>
  implements DcbDetail
{
  private final GservicesValue<E> mValue;

  public GServicesDetail(GservicesValue<E> paramGservicesValue)
  {
    this.mValue = paramGservicesValue;
  }

  public String getTitle()
  {
    return this.mValue.getKey();
  }

  public String getValue()
  {
    Object localObject = this.mValue.get();
    if (localObject == null);
    for (String str = "null"; ; str = localObject.toString())
      return str;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.debug.GServicesDetail
 * JD-Core Version:    0.6.2
 */
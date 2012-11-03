package com.google.android.finsky.billing.carrierbilling.api;

import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;

public class DcbApiContext
{
  private final CarrierBillingStorage mDcbStorage;
  private final String mLine1Number;
  private final String mSubscriberId;

  public DcbApiContext(CarrierBillingStorage paramCarrierBillingStorage, String paramString1, String paramString2)
  {
    this.mDcbStorage = paramCarrierBillingStorage;
    this.mLine1Number = paramString1;
    this.mSubscriberId = paramString2;
  }

  public CarrierBillingParameters getCarrierBillingParameters()
  {
    return this.mDcbStorage.getParams();
  }

  public String getLine1Number()
  {
    return this.mLine1Number;
  }

  public String getSubscriberId()
  {
    return this.mSubscriberId;
  }

  public String toString()
  {
    return "[DcbApiContext: " + "Line1Number: " + this.mLine1Number + ", " + "SubscriberId: " + this.mSubscriberId + "]";
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.api.DcbApiContext
 * JD-Core Version:    0.6.2
 */
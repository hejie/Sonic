package com.google.android.finsky.billing;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import java.util.List;
import java.util.Map;

public abstract class Instrument
{
  private Buy.BuyResponse.CheckoutInfo.CheckoutOption mCheckoutOption;
  protected final Drawable mDisplayIcon;

  protected Instrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    this.mCheckoutOption = paramCheckoutOption;
    this.mDisplayIcon = paramDrawable;
  }

  public abstract BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle);

  public Buy.BuyResponse.CheckoutInfo.CheckoutOption getCheckoutOption()
  {
    return this.mCheckoutOption;
  }

  public Map<String, String> getCompleteParams()
  {
    return null;
  }

  public String getDisplayName()
  {
    return this.mCheckoutOption.getFormOfPayment();
  }

  public int getFopVersion()
  {
    if (this.mCheckoutOption.hasInstrument());
    for (int i = BillingUtils.getFopVersion(this.mCheckoutOption.getInstrument()); ; i = 0)
      return i;
  }

  public int getInstrumentFamily()
  {
    return this.mCheckoutOption.getInstrumentFamily();
  }

  public String getInstrumentId()
  {
    return this.mCheckoutOption.getInstrumentId();
  }

  public List<String> getMessages()
  {
    return this.mCheckoutOption.getDisabledReasonList();
  }

  public boolean hasMessages()
  {
    if (this.mCheckoutOption.getDisabledReasonCount() > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isValid()
  {
    return this.mCheckoutOption.hasEncodedAdjustedCart();
  }

  public String toString()
  {
    return getDisplayName();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.Instrument
 * JD-Core Version:    0.6.2
 */
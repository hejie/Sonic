package com.google.android.finsky.billing.storedvalue;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.InstrumentFactory.FormOfPayment;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;

public class StoredValueFormOfPayment extends InstrumentFactory.FormOfPayment
{
  private String mAddLabel;

  public static void registerWithInstrumentFactory(InstrumentFactory paramInstrumentFactory)
  {
    paramInstrumentFactory.registerFormOfPayment(7, new StoredValueFormOfPayment());
  }

  public boolean canAdd()
  {
    return true;
  }

  public BillingFlow create(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    throw new UnsupportedOperationException();
  }

  public Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    return new StoredValueInstrument(paramCheckoutOption, paramDrawable);
  }

  public Drawable getAddIcon()
  {
    return null;
  }

  public String getAddText()
  {
    return this.mAddLabel;
  }

  public Intent getCreateIntent(CommonDevice.Instrument paramInstrument, String paramString1, int paramInt, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString2, String paramString3)
  {
    return StoredValueTopUpActivity.createIntent(paramString1, paramInstrument);
  }

  public String getUpdateAddressText()
  {
    return null;
  }

  public BillingFlow updateAddress(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    throw new UnsupportedOperationException();
  }

  public void updateStatus(CommonDevice.Instrument paramInstrument)
  {
    if (paramInstrument.hasDisplayTitle());
    for (this.mAddLabel = paramInstrument.getDisplayTitle(); ; this.mAddLabel = null)
      return;
  }

  public class StoredValueInstrument extends Instrument
  {
    protected StoredValueInstrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramDrawable, Drawable arg3)
    {
      super(localDrawable);
    }

    public BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
    {
      return null;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.storedvalue.StoredValueFormOfPayment
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import com.google.android.finsky.activities.FinskyCreateInstrumentActivity;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InstrumentFactory
{
  private Map<Pair<Integer, Integer>, FormOfPayment> mFormsOfPayment = Maps.newHashMap();

  private Pair<Integer, Integer> getKey(int paramInt1, int paramInt2)
  {
    return Pair.create(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
  }

  public boolean canAdd(int paramInt1, int paramInt2)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).canAdd();
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public BillingFlow create(int paramInt1, int paramInt2, BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).create(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public Instrument get(int paramInt1, int paramInt2, Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).get(paramCheckoutOption, paramDrawable);
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public Drawable getAddIcon(int paramInt1, int paramInt2)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).getAddIcon();
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public String getAddText(int paramInt1, int paramInt2)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).getAddText();
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public Map<String, String> getAllPrepareParameters()
  {
    HashMap localHashMap = Maps.newHashMap();
    Iterator localIterator = this.mFormsOfPayment.values().iterator();
    while (localIterator.hasNext())
    {
      Map localMap = ((FormOfPayment)localIterator.next()).getPrepareParams();
      if (localMap != null)
        localHashMap.putAll(localMap);
    }
    return localHashMap;
  }

  public Intent getCreateIntent(CommonDevice.Instrument paramInstrument, String paramString1, int paramInt, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString2, String paramString3)
  {
    Pair localPair = getKey(paramInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(paramInstrument));
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).getCreateIntent(paramInstrument, paramString1, paramInt, paramCreateInstrumentUiMode, paramString2, paramString3);
    throw new IllegalArgumentException("Missing FOP for type " + paramInstrument.getInstrumentFamily() + " version " + BillingUtils.getFopVersion(paramInstrument));
  }

  public String getUpdateAddressText(int paramInt1, int paramInt2)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).getUpdateAddressText();
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public boolean isRegistered(int paramInt1, int paramInt2)
  {
    return this.mFormsOfPayment.containsKey(getKey(paramInt1, paramInt2));
  }

  public void registerFormOfPayment(int paramInt1, int paramInt2, FormOfPayment paramFormOfPayment)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      throw new IllegalArgumentException("Already have a FOP for type " + paramInt1);
    this.mFormsOfPayment.put(localPair, paramFormOfPayment);
  }

  public void registerFormOfPayment(int paramInt, FormOfPayment paramFormOfPayment)
  {
    registerFormOfPayment(paramInt, 0, paramFormOfPayment);
  }

  public BillingFlow updateAddress(int paramInt1, int paramInt2, BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
      return ((FormOfPayment)this.mFormsOfPayment.get(localPair)).updateAddress(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public void updateStatus(int paramInt1, int paramInt2, CommonDevice.Instrument paramInstrument)
  {
    Pair localPair = getKey(paramInt1, paramInt2);
    if (this.mFormsOfPayment.containsKey(localPair))
    {
      ((FormOfPayment)this.mFormsOfPayment.get(localPair)).updateStatus(paramInstrument);
      return;
    }
    throw new IllegalArgumentException("Missing FOP for type " + paramInt1 + " version " + paramInt2);
  }

  public static abstract class FormOfPayment
  {
    public abstract boolean canAdd();

    public abstract BillingFlow create(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle);

    public abstract Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable);

    public abstract Drawable getAddIcon();

    public abstract String getAddText();

    public Intent getCreateIntent(CommonDevice.Instrument paramInstrument, String paramString1, int paramInt, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString2, String paramString3)
    {
      return FinskyCreateInstrumentActivity.createIntent(paramString1, paramInstrument, paramInt, BillingUtils.CreateInstrumentUiMode.INTERNAL, false, paramString2, paramString3);
    }

    public Map<String, String> getPrepareParams()
    {
      return null;
    }

    public abstract String getUpdateAddressText();

    public abstract BillingFlow updateAddress(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle);

    public void updateStatus(CommonDevice.Instrument paramInstrument)
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.InstrumentFactory
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.giftcard;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.RedeemGiftCardActivity;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.InstrumentFactory.FormOfPayment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;

public class GiftCardFormOfPayment extends InstrumentFactory.FormOfPayment
{
  private String mAddLabel;

  public static void registerWithInstrumentFactory(InstrumentFactory paramInstrumentFactory)
  {
    paramInstrumentFactory.registerFormOfPayment(100, new GiftCardFormOfPayment());
  }

  public boolean canAdd()
  {
    return true;
  }

  public BillingFlow create(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    Account localAccount = AccountHandler.findAccount(paramBundle.getString("authAccount"), FinskyApp.get());
    if (localAccount == null)
      FinskyLog.e("Invalid account passed in parameters.", new Object[0]);
    for (Object localObject = null; ; localObject = new RedeemGiftCardBillingFlow(paramBillingFlowContext, paramBillingFlowListener, FinskyApp.get().getDfeApi(localAccount.name), new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.checkoutAuthTokenType.get())), paramBundle))
      return localObject;
  }

  public Instrument get(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption, Drawable paramDrawable)
  {
    return new GiftCardInstrument(paramCheckoutOption, paramDrawable);
  }

  public Drawable getAddIcon()
  {
    return null;
  }

  public String getAddText()
  {
    if (!TextUtils.isEmpty(this.mAddLabel));
    for (String str = this.mAddLabel; ; str = FinskyApp.get().getString(2131165309))
      return str;
  }

  public Intent getCreateIntent(CommonDevice.Instrument paramInstrument, String paramString1, int paramInt, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString2, String paramString3)
  {
    return RedeemGiftCardActivity.createIntent(paramString1, paramInt, false, paramString2, paramString3);
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

  public class GiftCardInstrument extends Instrument
  {
    protected GiftCardInstrument(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramDrawable, Drawable arg3)
    {
      super(localDrawable);
    }

    public BillingFlow completePurchase(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
    {
      throw new UnsupportedOperationException();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.giftcard.GiftCardFormOfPayment
 * JD-Core Version:    0.6.2
 */
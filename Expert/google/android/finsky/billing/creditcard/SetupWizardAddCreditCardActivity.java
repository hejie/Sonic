package com.google.android.finsky.billing.creditcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.finsky.activities.InstrumentActivity;
import com.google.android.finsky.activities.InstrumentActivity.Mode;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.utils.Compat;
import com.google.android.finsky.utils.FinskyLog;

public class SetupWizardAddCreditCardActivity extends InstrumentActivity
{
  private boolean mInitialSetup = false;

  public void onBackPressed()
  {
    FinskyLog.d("Cannot interrupt the add credit card flow in the setup wizard.", new Object[0]);
  }

  protected void onCreate(Bundle paramBundle)
  {
    Intent localIntent = getIntent();
    localIntent.putExtra("billing_flow", 0);
    if (!localIntent.hasExtra("ui_mode"))
      localIntent.putExtra("ui_mode", BillingUtils.CreateInstrumentUiMode.SETUP_WIZARD.name());
    localIntent.putExtra("instrument_mode", InstrumentActivity.Mode.ADD);
    super.onCreate(paramBundle);
    if (localIntent.getBooleanExtra("on_initial_setup", true))
      Compat.viewSetSystemUiVisibility(this.mMainView, 7798784);
    if (this.mUiMode == BillingUtils.CreateInstrumentUiMode.PROMO_OFFER)
      this.mTitleView.setText(2131165247);
    removeActionBar();
    if (this.mBillingFlowParameters.getString("referrer_url") == null)
      this.mBillingFlowParameters.putString("referrer_url", "externalPackage?pkg=" + getCallingPackage());
    this.mBillingFlowParameters.putString("cardholder_name", localIntent.getStringExtra("cardholder_name"));
    this.mInitialSetup = localIntent.getBooleanExtra("on_initial_setup", false);
  }

  protected void setTitle(String paramString)
  {
    FinskyLog.d("Swallowing title: %s", new Object[] { paramString });
  }

  protected boolean showRedeemedOfferDialog()
  {
    if (!this.mInitialSetup);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.SetupWizardAddCreditCardActivity
 * JD-Core Version:    0.6.2
 */
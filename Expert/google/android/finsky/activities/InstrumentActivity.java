package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.ProgressDialogFragment;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.creditcard.CreditCardInstrument;
import com.google.android.finsky.billing.giftcard.GiftCardFormOfPayment;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.utils.FinskyLog;

public abstract class InstrumentActivity extends FragmentActivity
  implements SimpleAlertDialog.Listener, BillingFlowContext, BillingFlowListener
{
  protected Bundle mBillingFlowParameters = new Bundle();
  protected ViewGroup mFragmentContainer;
  private InstrumentFactory mInstrumentFactory;
  private Mode mInstrumentMode;
  protected View mMainView;
  private boolean mNeedsHideProgress;
  private ProgressDialogFragment mProgressDialog;
  private BillingFlow mRunningFlow;
  private boolean mSaveInstanceStateCalled;
  protected Bundle mSavedFlowState;
  protected TextView mTitleView;
  protected BillingUtils.CreateInstrumentUiMode mUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL;

  private void startOrResumeFlow(Bundle paramBundle)
  {
    int i = getIntent().getIntExtra("billing_flow", 0);
    int j = getIntent().getIntExtra("billing_flow_version", 0);
    if (this.mInstrumentMode == Mode.UPDATE)
    {
      this.mBillingFlowParameters.putString("update_address_header", this.mInstrumentFactory.getUpdateAddressText(i, j));
      this.mRunningFlow = this.mInstrumentFactory.updateAddress(i, j, this, this, this.mBillingFlowParameters);
    }
    label176: 
    while (true)
    {
      if (this.mRunningFlow == null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        FinskyLog.w("Couldn't instantiate BillingFlow for FOP type %d", arrayOfObject);
        finish();
      }
      while (true)
      {
        return;
        if (this.mInstrumentMode != Mode.ADD)
          break label176;
        this.mRunningFlow = this.mInstrumentFactory.create(i, j, this, this, this.mBillingFlowParameters);
        break;
        this.mFragmentContainer.setVisibility(0);
        findViewById(2131230776).setVisibility(8);
        if (paramBundle == null)
          this.mRunningFlow.start();
        else
          this.mRunningFlow.resumeFromSavedState(paramBundle);
      }
    }
  }

  private void stopFlow()
  {
    this.mFragmentContainer.setVisibility(8);
    this.mRunningFlow = null;
  }

  private boolean useProgressDialog()
  {
    if ((this.mUiMode == BillingUtils.CreateInstrumentUiMode.INTERNAL) || (Build.VERSION.SDK_INT < 11));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void hideFragment(Fragment paramFragment, boolean paramBoolean)
  {
    if (this.mSaveInstanceStateCalled);
    while (true)
    {
      return;
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.remove(paramFragment);
      if (paramBoolean)
        localFragmentTransaction.addToBackStack(null);
      localFragmentTransaction.commit();
    }
  }

  public void hideProgress()
  {
    this.mNeedsHideProgress = false;
    if (useProgressDialog())
      if (this.mProgressDialog != null)
      {
        if (!this.mSaveInstanceStateCalled)
          break label32;
        this.mNeedsHideProgress = true;
      }
    while (true)
    {
      return;
      label32: this.mProgressDialog.dismiss();
      this.mProgressDialog = null;
      continue;
      findViewById(2131230773).setVisibility(0);
      findViewById(2131230774).setVisibility(4);
    }
  }

  public void onBackPressed()
  {
    if (this.mRunningFlow != null)
      if (this.mRunningFlow.canGoBack())
        this.mRunningFlow.back();
    while (true)
    {
      return;
      FinskyLog.d("Cannot interrupt the current flow.", new Object[0]);
      continue;
      super.onBackPressed();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mMainView = View.inflate(this, 2130968592, null);
    this.mTitleView = ((TextView)this.mMainView.findViewById(2131230771));
    setContentView(this.mMainView);
    this.mFragmentContainer = ((ViewGroup)findViewById(2131230775));
    String str1 = getIntent().getStringExtra("authAccount");
    if (!AccountHandler.hasAccount(str1, this))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = FinskyLog.scrubPii(str1);
      FinskyLog.e("Invalid account supplied in the intent: %s", arrayOfObject);
      finish();
    }
    this.mBillingFlowParameters.putString("authAccount", str1);
    String str2 = getIntent().getStringExtra("ui_mode");
    if (str2 != null)
    {
      this.mUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(str2);
      if (this.mUiMode == BillingUtils.CreateInstrumentUiMode.INTERNAL)
      {
        this.mMainView.findViewById(2131230771).setVisibility(8);
        this.mMainView.findViewById(2131230772).setVisibility(8);
      }
    }
    this.mInstrumentMode = ((Mode)getIntent().getSerializableExtra("instrument_mode"));
    if (this.mInstrumentMode == Mode.ADD)
      this.mTitleView.setText(2131165220);
    while (true)
    {
      this.mBillingFlowParameters.putBundle("extra_paramters", getIntent().getBundleExtra("extra_paramters"));
      this.mBillingFlowParameters.putString("ui_mode", str2);
      this.mBillingFlowParameters.putBoolean("entry_point_menu", getIntent().getBooleanExtra("entry_point_menu", false));
      this.mBillingFlowParameters.putString("referrer_url", getIntent().getStringExtra("referrer_url"));
      this.mBillingFlowParameters.putString("referrer_list_cookie", getIntent().getStringExtra("referrer_list_cookie"));
      if (paramBundle != null)
      {
        this.mSavedFlowState = paramBundle.getBundle("flow_state");
        this.mProgressDialog = ((ProgressDialogFragment)restoreFragment(paramBundle, "progress_dialog"));
        if (this.mProgressDialog != null)
        {
          this.mProgressDialog.dismiss();
          this.mProgressDialog = null;
        }
      }
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          InstrumentActivity.access$002(InstrumentActivity.this, new InstrumentFactory());
          CreditCardInstrument.registerWithFactory(InstrumentActivity.this.mInstrumentFactory);
          GiftCardFormOfPayment.registerWithInstrumentFactory(InstrumentActivity.this.mInstrumentFactory);
          CarrierBillingUtils.initializeCarrierBillingStorage(new Runnable()
          {
            public void run()
            {
              CarrierBillingUtils.initializeCarrierBillingParams(InstrumentActivity.this, new Runnable()
              {
                public void run()
                {
                  CarrierBillingUtils.registerDcbInstrument(InstrumentActivity.this.mInstrumentFactory);
                  InstrumentActivity.this.startOrResumeFlow(InstrumentActivity.this.mSavedFlowState);
                }
              });
            }
          });
        }
      });
      return;
      if (this.mInstrumentMode == Mode.UPDATE)
        this.mTitleView.setVisibility(8);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    stopFlow();
  }

  public void onError(BillingFlow paramBillingFlow, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("billing_flow_error", true);
    localIntent.putExtra("billing_flow_error_message", paramString);
    setResult(-1, localIntent);
    finish();
  }

  public void onFinished(BillingFlow paramBillingFlow, boolean paramBoolean, Bundle paramBundle)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("billing_flow_error", false);
    localIntent.putExtra("billing_flow_canceled", paramBoolean);
    if ((!paramBoolean) && (paramBundle != null) && (paramBundle.containsKey("redeemed_offer_message_html")))
    {
      String str = paramBundle.getString("redeemed_offer_message_html");
      localIntent.putExtra("redeemed_offer_message_html", str);
      if (showRedeemedOfferDialog())
      {
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(Html.fromHtml(str).toString(), 2131165599, -1);
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("result_intent", localIntent);
        localSimpleAlertDialog.setCallback(null, 1, localBundle);
        localSimpleAlertDialog.show(getSupportFragmentManager(), "redeemed_promo_offer");
      }
    }
    while (true)
    {
      return;
      setResult(-1, localIntent);
      finish();
    }
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      onBackPressed();
    }
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 1)
    {
      setResult(-1, (Intent)paramBundle.getParcelable("result_intent"));
      finish();
    }
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mRunningFlow != null)
      this.mRunningFlow.onActivityResume();
    this.mSaveInstanceStateCalled = false;
    if (this.mNeedsHideProgress)
      hideProgress();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.mSaveInstanceStateCalled = true;
    super.onSaveInstanceState(paramBundle);
    if (this.mRunningFlow != null)
    {
      Bundle localBundle = new Bundle();
      this.mRunningFlow.saveState(localBundle);
      paramBundle.putBundle("flow_state", localBundle);
    }
    if (this.mProgressDialog != null)
      persistFragment(paramBundle, "progress_dialog", this.mProgressDialog);
  }

  public void persistFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    getSupportFragmentManager().putFragment(paramBundle, paramString, paramFragment);
  }

  protected void removeActionBar()
  {
    CustomActionBarFactory.getInstance(this).hide();
  }

  public Fragment restoreFragment(Bundle paramBundle, String paramString)
  {
    return getSupportFragmentManager().getFragment(paramBundle, paramString);
  }

  protected abstract void setTitle(String paramString);

  public void showDialogFragment(DialogFragment paramDialogFragment, String paramString)
  {
    if (this.mSaveInstanceStateCalled);
    while (true)
    {
      return;
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      Fragment localFragment = getSupportFragmentManager().findFragmentByTag(paramString);
      if (localFragment != null)
        localFragmentTransaction.remove(localFragment);
      localFragmentTransaction.addToBackStack(null);
      paramDialogFragment.show(getSupportFragmentManager(), paramString);
    }
  }

  public void showFragment(Fragment paramFragment, int paramInt, boolean paramBoolean)
  {
    if (this.mSaveInstanceStateCalled)
      return;
    if (paramInt != -1)
      setTitle(getString(paramInt));
    while (true)
    {
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.add(2131230775, paramFragment);
      if (paramBoolean)
        localFragmentTransaction.addToBackStack(null);
      localFragmentTransaction.commitAllowingStateLoss();
      break;
      setTitle(null);
    }
  }

  public void showProgress(int paramInt)
  {
    if (this.mSaveInstanceStateCalled);
    while (true)
    {
      return;
      if (useProgressDialog())
      {
        this.mProgressDialog = ProgressDialogFragment.newInstance(paramInt);
        this.mProgressDialog.show(getSupportFragmentManager(), "progress_dialog");
      }
      else
      {
        findViewById(2131230773).setVisibility(4);
        findViewById(2131230774).setVisibility(0);
      }
    }
  }

  protected boolean showRedeemedOfferDialog()
  {
    return true;
  }

  public static enum Mode
  {
    static
    {
      Mode[] arrayOfMode = new Mode[2];
      arrayOfMode[0] = ADD;
      arrayOfMode[1] = UPDATE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.InstrumentActivity
 * JD-Core Version:    0.6.2
 */
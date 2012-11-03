package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.giftcard.GiftCardFormOfPayment;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.utils.FinskyLog;

public class RedeemGiftCardActivity extends FragmentActivity
  implements BillingFlowContext, BillingFlowListener
{
  private CustomActionBar mActionBar;
  private BillingFlow mFlow;
  private final FakeNavigationManager mNavigationManager = new FakeNavigationManager(this);

  public static Intent createIntent(String paramString1, int paramInt, boolean paramBoolean, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent(FinskyApp.get(), RedeemGiftCardActivity.class);
    localIntent.putExtra("backend", paramInt);
    Bundle localBundle = new Bundle();
    localBundle.putString("authAccount", paramString1);
    localBundle.putBoolean("entry_point_menu", paramBoolean);
    localBundle.putString("referrer_url", paramString2);
    localBundle.putString("referrer_url", paramString3);
    localIntent.putExtra("params", localBundle);
    return localIntent;
  }

  private void setTitle(String paramString)
  {
    this.mActionBar.updateBreadcrumb(paramString);
  }

  private void setupActionBar(Bundle paramBundle)
  {
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    int i = getIntent().getIntExtra("backend", 0);
    this.mActionBar.updateCurrentBackendId(i);
    if ((paramBundle != null) && (paramBundle.containsKey("last_title")))
      setTitle(paramBundle.getString("last_title"));
  }

  public static void show(Activity paramActivity, String paramString, int paramInt)
  {
    if (paramString == null)
      FinskyLog.wtf("Redeem chosen with no current account.", new Object[0]);
    while (true)
    {
      return;
      paramActivity.startActivity(createIntent(paramString, paramInt, true, null, null));
    }
  }

  public void hideFragment(Fragment paramFragment, boolean paramBoolean)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.remove(paramFragment);
    if (paramBoolean)
      localFragmentTransaction.addToBackStack(null);
    localFragmentTransaction.commit();
  }

  public void hideProgress()
  {
  }

  public void onBackPressed()
  {
    if (this.mFlow != null)
      if (this.mFlow.canGoBack())
        this.mFlow.back();
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
    setContentView(2130968600);
    setupActionBar(paramBundle);
    this.mFlow = new GiftCardFormOfPayment().create(this, this, getIntent().getBundleExtra("params"));
    if (paramBundle != null)
    {
      Bundle localBundle = paramBundle.getBundle("flow_state");
      this.mFlow.resumeFromSavedState(localBundle);
    }
    while (true)
    {
      return;
      this.mFlow.start();
    }
  }

  public void onError(BillingFlow paramBillingFlow, String paramString)
  {
    if (paramString != null)
      Toast.makeText(this, paramString, 1).show();
    setResult(0);
    finish();
  }

  public void onFinished(BillingFlow paramBillingFlow, boolean paramBoolean, Bundle paramBundle)
  {
    int i = 0;
    Intent localIntent = null;
    if (!getIntent().getBooleanExtra("entry_point_menu", false))
    {
      localIntent = new Intent();
      localIntent.putExtra("billing_flow_error", false);
      localIntent.putExtra("billing_flow_canceled", paramBoolean);
    }
    if (paramBoolean);
    while (true)
    {
      setResult(i, localIntent);
      finish();
      return;
      i = -1;
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
      finish();
    for (boolean bool = true; ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mFlow != null)
    {
      Bundle localBundle = new Bundle();
      this.mFlow.saveState(localBundle);
      paramBundle.putBundle("flow_state", localBundle);
    }
    paramBundle.putString("last_title", this.mActionBar.getBreadcrumbText());
    super.onSaveInstanceState(paramBundle);
  }

  public void persistFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    getSupportFragmentManager().putFragment(paramBundle, paramString, paramFragment);
  }

  public Fragment restoreFragment(Bundle paramBundle, String paramString)
  {
    return getSupportFragmentManager().getFragment(paramBundle, paramString);
  }

  public void showDialogFragment(DialogFragment paramDialogFragment, String paramString)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    Fragment localFragment = getSupportFragmentManager().findFragmentByTag(paramString);
    if (localFragment != null)
      localFragmentTransaction.remove(localFragment);
    localFragmentTransaction.addToBackStack(null);
    paramDialogFragment.show(getSupportFragmentManager(), paramString);
  }

  public void showFragment(Fragment paramFragment, int paramInt, boolean paramBoolean)
  {
    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.add(2131230775, paramFragment);
    if (paramInt != -1)
      setTitle(getString(paramInt));
    while (true)
    {
      if (paramBoolean)
        localFragmentTransaction.addToBackStack(null);
      localFragmentTransaction.commitAllowingStateLoss();
      return;
      setTitle(null);
    }
  }

  public void showProgress(int paramInt)
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.RedeemGiftCardActivity
 * JD-Core Version:    0.6.2
 */
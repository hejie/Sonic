package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;

public class ChallengeActivity extends FragmentActivity
  implements BillingFlowContext, BillingFlowListener
{
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

  public void onError(BillingFlow paramBillingFlow, String paramString)
  {
    if (paramString != null)
      Toast.makeText(this, paramString, 1).show();
    setResult(0);
    finish();
  }

  public void onFinished(BillingFlow paramBillingFlow, boolean paramBoolean, Bundle paramBundle)
  {
    if (paramBoolean)
    {
      setResult(0);
      finish();
    }
    while (true)
    {
      return;
      Intent localIntent = new Intent();
      localIntent.putExtra("challenge_response", paramBundle);
      setResult(-1, localIntent);
      finish();
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return super.onOptionsItemSelected(paramMenuItem);
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
    if (paramBoolean)
      localFragmentTransaction.addToBackStack(null);
    localFragmentTransaction.commitAllowingStateLoss();
  }

  public void showProgress(int paramInt)
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ChallengeActivity
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public abstract interface BillingFlowContext
{
  public abstract void hideFragment(Fragment paramFragment, boolean paramBoolean);

  public abstract void hideProgress();

  public abstract void persistFragment(Bundle paramBundle, String paramString, Fragment paramFragment);

  public abstract Fragment restoreFragment(Bundle paramBundle, String paramString);

  public abstract void showDialogFragment(DialogFragment paramDialogFragment, String paramString);

  public abstract void showFragment(Fragment paramFragment, int paramInt, boolean paramBoolean);

  public abstract void showProgress(int paramInt);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingFlowContext
 * JD-Core Version:    0.6.2
 */
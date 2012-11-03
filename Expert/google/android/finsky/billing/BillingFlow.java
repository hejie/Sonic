package com.google.android.finsky.billing;

import android.os.Bundle;

public abstract class BillingFlow
{
  protected final BillingFlowContext mBillingFlowContext;
  private boolean mFinished;
  private final BillingFlowListener mListener;
  protected final Bundle mParameters;

  public BillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Bundle paramBundle)
  {
    this.mParameters = paramBundle;
    this.mBillingFlowContext = paramBillingFlowContext;
    this.mListener = paramBillingFlowListener;
  }

  private void notifyError(String paramString)
  {
    this.mListener.onError(this, paramString);
  }

  private void notifyFinished(boolean paramBoolean, Bundle paramBundle)
  {
    this.mListener.onFinished(this, paramBoolean, paramBundle);
  }

  public void back()
  {
    throw new UnsupportedOperationException();
  }

  public boolean canGoBack()
  {
    return false;
  }

  public void cancel()
  {
    this.mFinished = true;
    notifyFinished(true, null);
  }

  protected void fail(String paramString)
  {
    this.mFinished = true;
    notifyError(paramString);
  }

  protected void finish()
  {
    finish(null);
  }

  protected void finish(Bundle paramBundle)
  {
    this.mFinished = true;
    notifyFinished(false, paramBundle);
  }

  public void onActivityResume()
  {
  }

  public abstract void resumeFromSavedState(Bundle paramBundle);

  public abstract void saveState(Bundle paramBundle);

  public abstract void start();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingFlow
 * JD-Core Version:    0.6.2
 */
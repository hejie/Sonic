package com.google.android.finsky.billing;

import android.os.Bundle;

public abstract interface BillingFlowListener
{
  public abstract void onError(BillingFlow paramBillingFlow, String paramString);

  public abstract void onFinished(BillingFlow paramBillingFlow, boolean paramBoolean, Bundle paramBundle);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingFlowListener
 * JD-Core Version:    0.6.2
 */
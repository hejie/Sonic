package com.google.android.finsky.billing.carrierbilling.action;

import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;

public class CarrierBillingAction
{
  private boolean canSkip()
  {
    return BillingLocator.getCarrierBillingStorage().isInit();
  }

  public void run(Runnable paramRunnable)
  {
    if (canSkip())
      if (paramRunnable != null)
        paramRunnable.run();
    while (true)
    {
      return;
      BillingLocator.initCarrierBillingStorage(paramRunnable);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction
 * JD-Core Version:    0.6.2
 */
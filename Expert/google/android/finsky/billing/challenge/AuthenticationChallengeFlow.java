package com.google.android.finsky.billing.challenge;

import android.os.Bundle;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AuthenticationChallenge;

public class AuthenticationChallengeFlow extends BillingFlow
  implements AuthenticationChallengeFragment.Listener
{
  private final ChallengeProtos.AuthenticationChallenge mChallenge;
  private AuthenticationChallengeFragment mFragment;

  public AuthenticationChallengeFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, ChallengeProtos.AuthenticationChallenge paramAuthenticationChallenge, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    this.mChallenge = paramAuthenticationChallenge;
  }

  public void onCancel()
  {
    cancel();
  }

  public void onSuccess(int paramInt1, int paramInt2)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString(this.mChallenge.getResponseAuthenticationTypeParam(), String.valueOf(paramInt1));
    localBundle.putString(this.mChallenge.getResponseRetryCountParam(), String.valueOf(paramInt2));
    finish(localBundle);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    this.mFragment = ((AuthenticationChallengeFragment)this.mBillingFlowContext.restoreFragment(paramBundle, "auth_challenge_fragment"));
    this.mFragment.setListener(this);
  }

  public void saveState(Bundle paramBundle)
  {
    this.mBillingFlowContext.persistFragment(paramBundle, "auth_challenge_fragment", this.mFragment);
  }

  public void start()
  {
    this.mFragment = AuthenticationChallengeFragment.newInstance(this.mParameters.getString("authAccount"), this.mChallenge);
    this.mFragment.setListener(this);
    this.mBillingFlowContext.showFragment(this.mFragment, 0, false);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.challenge.AuthenticationChallengeFlow
 * JD-Core Version:    0.6.2
 */
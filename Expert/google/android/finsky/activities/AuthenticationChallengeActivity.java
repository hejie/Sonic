package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.challenge.AuthenticationChallengeFlow;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AuthenticationChallenge;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;
import com.google.android.finsky.utils.ParcelableProto;

public class AuthenticationChallengeActivity extends ChallengeActivity
{
  private BillingFlow mFlow;
  private final FakeNavigationManager mNavigationManager = new FakeNavigationManager(this);

  public static Intent getIntent(int paramInt, ChallengeProtos.Challenge paramChallenge, Bundle paramBundle)
  {
    Intent localIntent = new Intent(FinskyApp.get(), AuthenticationChallengeActivity.class);
    localIntent.putExtra("challenge", ParcelableProto.forProto(paramChallenge));
    localIntent.putExtra("extra_parameters", paramBundle);
    return localIntent;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968597);
    ChallengeProtos.Challenge localChallenge = (ChallengeProtos.Challenge)((ParcelableProto)getIntent().getParcelableExtra("challenge")).getPayload();
    Bundle localBundle = getIntent().getBundleExtra("extra_parameters");
    this.mFlow = new AuthenticationChallengeFlow(this, this, localChallenge.getAuthenticationChallenge(), localBundle);
    setTitle(localChallenge.getAuthenticationChallenge().getGaiaHeaderText());
    if (paramBundle != null)
      this.mFlow.resumeFromSavedState(paramBundle);
    while (true)
    {
      return;
      this.mFlow.start();
    }
  }

  public void onFinished(BillingFlow paramBillingFlow, boolean paramBoolean, Bundle paramBundle)
  {
    if (paramBoolean)
    {
      FinskyApp.get().getAnalytics().logPageView(null, null, "authChallenge.canceled");
      FinskyApp.get().getEventLogger().logTag("authChallenge.canceled", new Object[0]);
    }
    super.onFinished(paramBillingFlow, paramBoolean, paramBundle);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mFlow != null)
      this.mFlow.saveState(paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AuthenticationChallengeActivity
 * JD-Core Version:    0.6.2
 */
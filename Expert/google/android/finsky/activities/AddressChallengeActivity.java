package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.challenge.AddressChallengeFlow;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;
import com.google.android.finsky.utils.ParcelableProto;

public class AddressChallengeActivity extends ChallengeActivity
{
  private BillingFlow mFlow;
  private final FakeNavigationManager mNavigationManager = new FakeNavigationManager(this);

  public static Intent getIntent(int paramInt, ChallengeProtos.Challenge paramChallenge, Bundle paramBundle)
  {
    return getIntent(AddressChallengeActivity.class, paramInt, paramChallenge, paramBundle);
  }

  protected static Intent getIntent(Class<? extends AddressChallengeActivity> paramClass, int paramInt, ChallengeProtos.Challenge paramChallenge, Bundle paramBundle)
  {
    Intent localIntent = new Intent(FinskyApp.get(), paramClass);
    localIntent.putExtra("backend", paramInt);
    localIntent.putExtra("challenge", ParcelableProto.forProto(paramChallenge));
    localIntent.putExtra("extra_parameters", paramBundle);
    return localIntent;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968600);
    CustomActionBar localCustomActionBar = CustomActionBarFactory.getInstance(this);
    localCustomActionBar.initialize(this.mNavigationManager, this);
    localCustomActionBar.updateCurrentBackendId(getIntent().getIntExtra("backend", 0));
    ChallengeProtos.Challenge localChallenge = (ChallengeProtos.Challenge)ParcelableProto.getProtoFromIntent(getIntent(), "challenge");
    Bundle localBundle = getIntent().getBundleExtra("extra_parameters");
    this.mFlow = new AddressChallengeFlow(this, this, localChallenge.getAddressChallenge(), localBundle);
    if (paramBundle != null)
      this.mFlow.resumeFromSavedState(paramBundle);
    while (true)
    {
      return;
      this.mFlow.start();
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
      this.mNavigationManager.goUp();
    for (boolean bool = true; ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mFlow != null)
      this.mFlow.saveState(paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AddressChallengeActivity
 * JD-Core Version:    0.6.2
 */
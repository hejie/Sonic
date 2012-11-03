package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;

public class AddressChallengeDialog extends AddressChallengeActivity
{
  public static Intent getIntent(int paramInt, ChallengeProtos.Challenge paramChallenge, Bundle paramBundle)
  {
    return AddressChallengeActivity.getIntent(AddressChallengeDialog.class, paramInt, paramChallenge, paramBundle);
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    CustomActionBarFactory.getInstance(this).hide();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AddressChallengeDialog
 * JD-Core Version:    0.6.2
 */
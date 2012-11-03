package com.google.android.finsky.billing.challenge;

import android.os.Bundle;
import android.util.Base64;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AddressChallenge;
import com.google.android.finsky.remoting.protos.ChallengeProtos.FormCheckbox;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;

public class AddressChallengeFlow extends BillingFlow
  implements AddressChallengeFragment.AddressChallengeResultListener
{
  private ChallengeProtos.AddressChallenge mAddressChallenge;
  private AddressChallengeFragment mAddressChallengeFragment;
  private boolean mFinishOnSwitchCountry;
  private int mResultFormat = 0;

  public AddressChallengeFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, ChallengeProtos.AddressChallenge paramAddressChallenge, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    this.mAddressChallenge = paramAddressChallenge;
    if ((paramBundle != null) && (paramBundle.getBoolean("AddressChallengeFlow.finishOnSwitchCountry")))
      bool = true;
    this.mFinishOnSwitchCountry = bool;
    if ((paramBundle != null) && (paramBundle.containsKey("AddressChallengeFlow.resultFormat")))
      this.mResultFormat = paramBundle.getInt("AddressChallengeFlow.resultFormat");
  }

  public void onAddressChallengeResult(AddressChallengeFragment.AddressChallengeResultListener.Result paramResult, BillingAddress.Address paramAddress, boolean[] paramArrayOfBoolean)
  {
    if (paramResult == AddressChallengeFragment.AddressChallengeResultListener.Result.CANCELED)
      cancel();
    while (true)
    {
      return;
      if (paramResult == AddressChallengeFragment.AddressChallengeResultListener.Result.SUCCESS)
      {
        Bundle localBundle = new Bundle();
        if (this.mResultFormat == 0)
        {
          String str = Base64.encodeToString(paramAddress.toByteArray(), 8);
          localBundle.putString(this.mAddressChallenge.getResponseAddressParam(), str);
        }
        StringBuilder localStringBuilder;
        ArrayList localArrayList;
        while (true)
        {
          localStringBuilder = new StringBuilder();
          localArrayList = Lists.newArrayList();
          for (int i = 0; i < paramArrayOfBoolean.length; i++)
          {
            if (i > 0)
              localStringBuilder.append(',');
            localStringBuilder.append(String.valueOf(paramArrayOfBoolean[i]));
            if (paramArrayOfBoolean[i] != 0)
              localArrayList.add(this.mAddressChallenge.getCheckbox(i).getId());
          }
          if (this.mResultFormat == 1)
            localBundle.putParcelable("AddressChallengeFlow.address", ParcelableProto.forProto(paramAddress));
        }
        if (this.mResultFormat == 0)
          localBundle.putString(this.mAddressChallenge.getResponseCheckboxesParam(), localStringBuilder.toString());
        while (true)
        {
          finish(localBundle);
          break;
          localBundle.putStringArrayList("AddressChallengeFlow.checkedCheckboxes", localArrayList);
        }
      }
      if (paramResult == AddressChallengeFragment.AddressChallengeResultListener.Result.FAILURE)
        fail(null);
    }
  }

  public void onCountryChanged(String paramString, Bundle paramBundle)
  {
    if (this.mFinishOnSwitchCountry)
    {
      FinskyLog.v("Finishing due to country switch.", new Object[0]);
      Bundle localBundle = new Bundle();
      localBundle.putString("AddressChallengeFlow.switchCountry", paramString);
      localBundle.putBundle("AddressChallengeFlow.currentState", paramBundle);
      finish(localBundle);
    }
  }

  public void onInitialized()
  {
    this.mBillingFlowContext.hideProgress();
  }

  public void onInitializing()
  {
    this.mBillingFlowContext.showProgress(2131165432);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    if (paramBundle.containsKey("address_widget"))
    {
      this.mAddressChallengeFragment = ((AddressChallengeFragment)this.mBillingFlowContext.restoreFragment(paramBundle, "address_widget"));
      if (this.mAddressChallengeFragment != null)
        this.mAddressChallengeFragment.setOnResultListener(this);
    }
  }

  public void saveState(Bundle paramBundle)
  {
    if (this.mAddressChallengeFragment != null)
      this.mBillingFlowContext.persistFragment(paramBundle, "address_widget", this.mAddressChallengeFragment);
  }

  public void start()
  {
    this.mAddressChallengeFragment = AddressChallengeFragment.newInstance(this.mParameters.getString("authAccount"), this.mAddressChallenge, this.mParameters.getBundle("AddressChallengeFlow.previousState"));
    this.mAddressChallengeFragment.setOnResultListener(this);
    this.mBillingFlowContext.showFragment(this.mAddressChallengeFragment, -1, false);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.challenge.AddressChallengeFlow
 * JD-Core Version:    0.6.2
 */
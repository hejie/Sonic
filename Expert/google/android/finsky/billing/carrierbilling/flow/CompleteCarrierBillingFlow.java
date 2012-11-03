package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.carrierbilling.action.CarrierCredentialsAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener.TosResult;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;

public class CompleteCarrierBillingFlow extends BillingFlow
  implements CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener, CarrierTosDialogFragment.CarrierTosResultListener
{
  private final Analytics mAnalytics;
  private final BillingFlowContext mContext;
  private CarrierCredentialsAction mCredentialsAction;
  private boolean mCredentialsCheckPerformed;
  private final FinskyEventLog mEventLog;
  private CarrierBillingParameters mParams;
  private String mPassword;
  private CarrierBillingPasswordDialogFragment mPasswordFragment;
  private CarrierBillingProvisioning mProvisioning;
  private CarrierProvisioningAction mProvisioningAction;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private State mState;
  private final CarrierBillingStorage mStorage;
  private CarrierTosDialogFragment mTosFragment;
  private int mTosNumber = 0;
  private String mTosVersion;

  public CompleteCarrierBillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, Analytics paramAnalytics, FinskyEventLog paramFinskyEventLog, Bundle paramBundle)
  {
    this(paramBillingFlowContext, paramBillingFlowListener, BillingLocator.getCarrierBillingStorage(), paramAnalytics, paramFinskyEventLog, paramBundle);
    this.mState = State.CHECK_CARRIER_TOS_VERSION;
    this.mCredentialsCheckPerformed = false;
    this.mProvisioningAction = new CarrierProvisioningAction();
  }

  CompleteCarrierBillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, CarrierBillingStorage paramCarrierBillingStorage, Analytics paramAnalytics, FinskyEventLog paramFinskyEventLog, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mStorage = paramCarrierBillingStorage;
    this.mParams = this.mStorage.getParams();
    this.mProvisioning = this.mStorage.getProvisioning();
    this.mAnalytics = paramAnalytics;
    this.mEventLog = paramFinskyEventLog;
    if (paramBundle != null)
    {
      this.mReferrerUrl = paramBundle.getString("referrer_url");
      this.mReferrerListCookie = paramBundle.getString("referrer_list_cookie");
    }
    this.mCredentialsAction = new CarrierCredentialsAction(paramCarrierBillingStorage);
  }

  private void log(String paramString)
  {
    this.mAnalytics.logPageView(this.mReferrerUrl, this.mReferrerListCookie, paramString);
  }

  void createAndShowPasswordFragment()
  {
    log("dcbPinEntry");
    this.mEventLog.logTag("dcbPinEntry", new Object[0]);
    this.mPasswordFragment = CarrierBillingPasswordDialogFragment.newInstance(this.mProvisioning.getPasswordPrompt(), this.mProvisioning.getPasswordForgotUrl());
    this.mPasswordFragment.setOnResultListener(this);
    this.mBillingFlowContext.showDialogFragment(this.mPasswordFragment, "PasswordDialog");
  }

  void createAndShowTosFragment()
  {
    log("dcbTosChanged");
    this.mEventLog.logTag("dcbTosChanged", new Object[0]);
    this.mTosVersion = this.mProvisioning.getTosVersion();
    this.mTosFragment = CarrierTosDialogFragment.newInstance(this.mProvisioning.getTosUrl());
    this.mTosFragment.setOnResultListener(this);
    this.mBillingFlowContext.showDialogFragment(this.mTosFragment, "TosDialog" + this.mTosNumber);
    this.mTosNumber = (1 + this.mTosNumber);
  }

  boolean credentialTimeStillValid(long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong1 - paramLong2 > paramLong3);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  boolean credentialsStillValid(CarrierBillingCredentials paramCarrierBillingCredentials)
  {
    if ((paramCarrierBillingCredentials == null) || (!paramCarrierBillingCredentials.isProvisioned()) || (TextUtils.isEmpty(paramCarrierBillingCredentials.getCredentials())));
    for (boolean bool = false; ; bool = credentialTimeStillValid(paramCarrierBillingCredentials.getExpirationTime(), ((Long)G.vendingCarrierCredentialsBufferMs.get()).longValue(), System.currentTimeMillis()))
      return bool;
  }

  public void onCarrierBillingPasswordResult(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult paramPasswordResult, String paramString)
  {
    if ((!paramPasswordResult.equals(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult.SUCCESS)) && (this.mPasswordFragment != null))
    {
      this.mPasswordFragment.dismiss();
      this.mPasswordFragment = null;
    }
    switch (1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[paramPasswordResult.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      log("dcbPinEntryConfirm");
      this.mEventLog.logTag("dcbPinEntryConfirm", new Object[0]);
      this.mPassword = paramString;
      performNext();
      continue;
      log("dcbPinEntryCancel");
      this.mEventLog.logTag("dcbPinEntryCancel", new Object[0]);
      cancel();
      continue;
      FinskyLog.d("Getting password info failed.", new Object[0]);
      fail(FinskyApp.get().getString(2131165279));
    }
  }

  public void onCarrierTosResult(CarrierTosDialogFragment.CarrierTosResultListener.TosResult paramTosResult)
  {
    this.mTosFragment.dismiss();
    this.mTosFragment = null;
    switch (1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[paramTosResult.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      log("dcbTosChangedConfirm");
      this.mEventLog.logTag("dcbTosChangedConfirm", new Object[0]);
      BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.put(this.mTosVersion);
      this.mProvisioningAction.forceRun(new AfterProvisioning(null), new AfterError(null), (String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
      continue;
      FinskyLog.d("Showing TOS to user failed.", new Object[0]);
      fail(FinskyApp.get().getString(2131165279));
      continue;
      log("dcbTosChangedCancel");
      this.mEventLog.logTag("dcbTosChangedCancel", new Object[0]);
      cancel();
    }
  }

  protected void performNext()
  {
    if (this.mState == State.CHECK_CARRIER_TOS_VERSION)
      if (!this.mParams.showCarrierTos())
      {
        this.mState = State.CHECK_VALID_CREDENTIALS;
        performNext();
      }
    while (true)
    {
      return;
      String str1 = (String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get();
      String str2 = this.mProvisioning.getTosVersion();
      if ((str1 == null) || (!str1.equals(str2)))
      {
        createAndShowTosFragment();
      }
      else
      {
        this.mState = State.CHECK_VALID_CREDENTIALS;
        performNext();
        continue;
        if (this.mState == State.CHECK_VALID_CREDENTIALS)
        {
          if (this.mProvisioning.isPasswordRequired())
          {
            this.mState = State.PASSWORD_REQUEST;
            createAndShowPasswordFragment();
          }
          else
          {
            CarrierBillingCredentials localCarrierBillingCredentials3 = null;
            if ((!this.mParams.perTransactionCredentialsRequired()) || (this.mCredentialsCheckPerformed))
              localCarrierBillingCredentials3 = this.mStorage.getCredentials();
            if (!credentialsStillValid(localCarrierBillingCredentials3))
            {
              if (this.mCredentialsCheckPerformed)
              {
                FinskyLog.d("Credentials already fetched once and still not valid.", new Object[0]);
                fail(FinskyApp.get().getString(2131165279));
              }
              else
              {
                this.mState = State.CARRIER_CREDENTIALS_REQUEST;
                this.mCredentialsAction.run(this.mProvisioning.getProvisioningId(), null, new AfterCredentials(null), new AfterError(null));
                if (this.mPasswordFragment != null)
                  this.mPasswordFragment.showProgressIndicator();
              }
            }
            else
              finish();
          }
        }
        else if (this.mState == State.CHECK_VALID_PASSWORD)
        {
          CarrierBillingCredentials localCarrierBillingCredentials2 = this.mStorage.getCredentials();
          Boolean localBoolean = Boolean.valueOf(localCarrierBillingCredentials2.invalidPassword());
          if ((localBoolean != null) && (localBoolean.booleanValue()))
          {
            log("dcbPinEntryInvalid");
            this.mEventLog.logTag("dcbPinEntryInvalid", new Object[0]);
            Toast.makeText(FinskyApp.get(), 2131165276, 1).show();
            if (this.mPasswordFragment != null)
              this.mPasswordFragment.clearPasswordField();
            this.mState = State.PASSWORD_REQUEST;
          }
          else
          {
            if (this.mPasswordFragment != null)
              this.mPasswordFragment.dismiss();
            if (credentialsStillValid(localCarrierBillingCredentials2))
            {
              finish();
            }
            else
            {
              FinskyLog.d("Valid password, but invalid credentials.", new Object[0]);
              fail(FinskyApp.get().getString(2131165279));
            }
          }
        }
        else if (this.mState == State.CARRIER_CREDENTIALS_REQUEST)
        {
          CarrierBillingCredentials localCarrierBillingCredentials1 = this.mStorage.getCredentials();
          this.mState = State.CHECK_VALID_CREDENTIALS;
          if (!localCarrierBillingCredentials1.isProvisioned())
          {
            this.mProvisioningAction.forceRun(new AfterProvisioning(null), new AfterError(null), (String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
            if (this.mPasswordFragment != null)
              this.mPasswordFragment.showProgressIndicator();
          }
          else
          {
            performNext();
          }
        }
        else
        {
          if (this.mState != State.PASSWORD_REQUEST)
            break;
          this.mState = State.CHECK_VALID_PASSWORD;
          this.mCredentialsAction.run(this.mProvisioning.getProvisioningId(), this.mPassword, new AfterCredentials(null), new AfterError(null));
          if (this.mPasswordFragment != null)
            this.mPasswordFragment.showProgressIndicator();
        }
      }
    }
    throw new IllegalStateException("Unexpected state: " + this.mState);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    if (this.mState != State.CHECK_CARRIER_TOS_VERSION)
      throw new IllegalStateException();
    this.mState = State.valueOf(paramBundle.getString("state"));
    if ((this.mParams == null) || (this.mProvisioning == null))
    {
      FinskyLog.d("Cannot run this BillingFlow since params or provisioning are null.", new Object[0]);
      fail(FinskyApp.get().getString(2131165279));
    }
    if (paramBundle.containsKey("tos_version"))
      this.mTosVersion = paramBundle.getString("tos_version");
    if (paramBundle.containsKey("tos_fragment"))
    {
      this.mTosFragment = ((CarrierTosDialogFragment)this.mContext.restoreFragment(paramBundle, "tos_fragment"));
      this.mTosFragment.setOnResultListener(this);
    }
    if (paramBundle.containsKey("password_fragment"))
    {
      this.mPasswordFragment = ((CarrierBillingPasswordDialogFragment)this.mContext.restoreFragment(paramBundle, "password_fragment"));
      this.mPasswordFragment.setOnResultListener(this);
    }
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putString("state", this.mState.name());
    if (this.mTosVersion != null)
      paramBundle.putString("tos_version", this.mTosVersion);
    if (this.mTosFragment != null)
      this.mContext.persistFragment(paramBundle, "tos_fragment", this.mTosFragment);
    if (this.mPasswordFragment != null)
      this.mContext.persistFragment(paramBundle, "password_fragment", this.mPasswordFragment);
  }

  public void start()
  {
    if ((this.mParams == null) || (this.mProvisioning == null))
    {
      FinskyLog.d("Cannot run this BillingFlow since params or provisioning are null.", new Object[0]);
      fail(FinskyApp.get().getString(2131165279));
    }
    while (true)
    {
      return;
      performNext();
    }
  }

  private class AfterCredentials
    implements Runnable
  {
    private AfterCredentials()
    {
    }

    public void run()
    {
      if (CompleteCarrierBillingFlow.this.mPasswordFragment != null)
        CompleteCarrierBillingFlow.this.mPasswordFragment.hideProgressIndicator();
      CompleteCarrierBillingFlow.access$602(CompleteCarrierBillingFlow.this, true);
      CompleteCarrierBillingFlow.this.performNext();
    }
  }

  private class AfterError
    implements Runnable
  {
    private AfterError()
    {
    }

    public void run()
    {
      if (CompleteCarrierBillingFlow.this.mPasswordFragment != null)
        CompleteCarrierBillingFlow.this.mPasswordFragment.dismiss();
      FinskyLog.d("Fetching info from carrier failed", new Object[0]);
      CompleteCarrierBillingFlow.this.fail(FinskyApp.get().getString(2131165279));
    }
  }

  private class AfterProvisioning
    implements Runnable
  {
    private AfterProvisioning()
    {
    }

    public void run()
    {
      if (CompleteCarrierBillingFlow.this.mPasswordFragment != null)
        CompleteCarrierBillingFlow.this.mPasswordFragment.hideProgressIndicator();
      CompleteCarrierBillingFlow.access$402(CompleteCarrierBillingFlow.this, CompleteCarrierBillingFlow.this.mStorage.getProvisioning());
      CompleteCarrierBillingFlow.this.performNext();
    }
  }

  public static enum State
  {
    static
    {
      CARRIER_CREDENTIALS_REQUEST = new State("CARRIER_CREDENTIALS_REQUEST", 3);
      PASSWORD_REQUEST = new State("PASSWORD_REQUEST", 4);
      State[] arrayOfState = new State[5];
      arrayOfState[0] = CHECK_CARRIER_TOS_VERSION;
      arrayOfState[1] = CHECK_VALID_CREDENTIALS;
      arrayOfState[2] = CHECK_VALID_PASSWORD;
      arrayOfState[3] = CARRIER_CREDENTIALS_REQUEST;
      arrayOfState[4] = PASSWORD_REQUEST;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.CompleteCarrierBillingFlow
 * JD-Core Version:    0.6.2
 */
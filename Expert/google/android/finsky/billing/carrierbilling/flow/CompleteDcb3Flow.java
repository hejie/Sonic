package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.billing.carrierbilling.Dcb3Instrument;
import com.google.android.finsky.billing.carrierbilling.flow.association.AccountAssociationFactoryImpl;
import com.google.android.finsky.billing.carrierbilling.flow.association.AssociationAction;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment.CarrierTosResultListener.TosResult;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyPinDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyPinDialogFragment.VerifyPinListener;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTos;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTosEntry;
import com.google.android.finsky.remoting.protos.CommonDevice.DeviceAssociation;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.remoting.protos.CommonDevice.PasswordPrompt;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;

public class CompleteDcb3Flow extends InstrumentFlow
  implements CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener, CarrierTosDialogFragment.CarrierTosResultListener, Response.Listener<CarrierBilling.VerifyAssociationResponse>, Response.ErrorListener, VerifyPinDialogFragment.VerifyPinListener
{
  private final Analytics mAnalytics;
  private AssociationAction mAssociationAction;
  private String mAssociationAddress;
  private String mAssociationPrefix;
  private boolean mAssociationRequired;
  private final BillingFlowContext mContext;
  private boolean mDcbTosAcceptanceRequired;
  private final DfeApi mDfeApi;
  private final FinskyEventLog mEventLog;
  private Dcb3Instrument mInstrument;
  private boolean mInstrumentUpdateRequired;
  private CarrierBillingParameters mParams;
  private String mPassword;
  private String mPasswordForgotUrl;
  private CarrierBillingPasswordDialogFragment mPasswordFragment;
  private String mPasswordPrompt;
  private boolean mPasswordRequired;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private State mState;
  private final CarrierBillingStorage mStorage;
  private CarrierTosDialogFragment mTosFragment;
  private int mTosNumber = 0;
  private BuyInstruments.UpdateInstrumentResponse mUpdateInstrumentResponse;
  private VerifyPinDialogFragment mVerifyAssociationFragment;

  public CompleteDcb3Flow(BillingFlowContext paramBillingFlowContext, DfeApi paramDfeApi, BillingFlowListener paramBillingFlowListener, Analytics paramAnalytics, FinskyEventLog paramFinskyEventLog, Bundle paramBundle, Dcb3Instrument paramDcb3Instrument, AsyncAuthenticator paramAsyncAuthenticator)
  {
    this(paramBillingFlowContext, paramDfeApi, paramBillingFlowListener, BillingLocator.getCarrierBillingStorage(), paramAnalytics, paramFinskyEventLog, paramBundle, paramDcb3Instrument, paramAsyncAuthenticator);
  }

  CompleteDcb3Flow(BillingFlowContext paramBillingFlowContext, DfeApi paramDfeApi, BillingFlowListener paramBillingFlowListener, CarrierBillingStorage paramCarrierBillingStorage, Analytics paramAnalytics, FinskyEventLog paramFinskyEventLog, Bundle paramBundle, Dcb3Instrument paramDcb3Instrument, AsyncAuthenticator paramAsyncAuthenticator)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mDfeApi = paramDfeApi;
    this.mStorage = paramCarrierBillingStorage;
    this.mParams = this.mStorage.getParams();
    this.mAnalytics = paramAnalytics;
    this.mEventLog = paramFinskyEventLog;
    this.mState = State.CHECK_INSTRUMENT_STATUS;
    this.mInstrument = paramDcb3Instrument;
    if (paramBundle != null)
    {
      this.mReferrerUrl = paramBundle.getString("referrer_url");
      this.mReferrerListCookie = paramBundle.getString("referrer_list_cookie");
      CommonDevice.Instrument localInstrument = (CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(paramBundle, "dcb_instrument");
      if (localInstrument.hasCarrierBillingStatus())
      {
        CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = localInstrument.getCarrierBillingStatus();
        this.mPasswordRequired = localCarrierBillingInstrumentStatus.getPasswordRequired();
        if (localCarrierBillingInstrumentStatus.hasCarrierPasswordPrompt())
        {
          this.mPasswordPrompt = localCarrierBillingInstrumentStatus.getCarrierPasswordPrompt().getPrompt();
          this.mPasswordForgotUrl = localCarrierBillingInstrumentStatus.getCarrierPasswordPrompt().getForgotPasswordUrl();
        }
        this.mAssociationRequired = localCarrierBillingInstrumentStatus.getAssociationRequired();
        if (localCarrierBillingInstrumentStatus.hasDeviceAssociation())
        {
          this.mAssociationAddress = localCarrierBillingInstrumentStatus.getDeviceAssociation().getUserTokenRequestAddress();
          this.mAssociationPrefix = localCarrierBillingInstrumentStatus.getDeviceAssociation().getUserTokenRequestMessage();
        }
        if (localCarrierBillingInstrumentStatus.hasCarrierTos())
          this.mDcbTosAcceptanceRequired = localCarrierBillingInstrumentStatus.getCarrierTos().getNeedsDcbTosAcceptance();
      }
    }
  }

  private void dissmissPasswordFragment()
  {
    if (this.mPasswordFragment != null)
    {
      this.mPasswordFragment.dismiss();
      this.mPasswordFragment = null;
    }
  }

  private void hideVerifyAssociationDialog()
  {
    if (this.mVerifyAssociationFragment != null)
    {
      this.mVerifyAssociationFragment.dismiss();
      this.mVerifyAssociationFragment = null;
    }
  }

  private void log(String paramString)
  {
    this.mAnalytics.logPageView(this.mReferrerUrl, this.mReferrerListCookie, paramString);
  }

  private void setAcceptedTos()
  {
    this.mInstrumentUpdateRequired = true;
    this.mDcbTosAcceptanceRequired = false;
    getDcbTosPreference().put(BillingPreferences.CARRIER_DCB_TOS_VERSION.get());
  }

  private void showVerifyAssociationDialog()
  {
    this.mVerifyAssociationFragment = VerifyPinDialogFragment.newInstance();
    this.mVerifyAssociationFragment.setOnResultListener(this);
    this.mContext.showDialogFragment(this.mVerifyAssociationFragment, "verifying pin");
  }

  private void startAssociation()
  {
    showVerifyAssociationDialog();
    this.mAssociationAction = new AccountAssociationFactoryImpl(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix).createAccountAssociationAction();
    this.mAssociationAction.start(this, this);
  }

  void createAndShowPasswordFragment()
  {
    log("dcbPinEntry");
    this.mEventLog.logTag("dcbPinEntry", new Object[0]);
    this.mPasswordFragment = CarrierBillingPasswordDialogFragment.newInstance(this.mPasswordPrompt, this.mPasswordForgotUrl);
    this.mPasswordFragment.setOnResultListener(this);
    this.mBillingFlowContext.showDialogFragment(this.mPasswordFragment, "PasswordDialog");
  }

  void createAndShowTosFragment(String paramString)
  {
    log("dcbTosChanged");
    this.mEventLog.logTag("dcbTosChanged", new Object[0]);
    this.mTosFragment = CarrierTosDialogFragment.newInstance(paramString);
    this.mTosFragment.setOnResultListener(this);
    this.mBillingFlowContext.showDialogFragment(this.mTosFragment, "TosDialog" + this.mTosNumber);
    this.mTosNumber = (1 + this.mTosNumber);
  }

  protected PreferenceFile.SharedPreference<String> getDcbTosPreference()
  {
    return BillingPreferences.getCarrierAcceptedDcbTos(BillingLocator.getSubscriberIdFromTelephony()).get(this.mDfeApi.getAccountName());
  }

  public void onCarrierBillingPasswordResult(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult paramPasswordResult, String paramString)
  {
    if (!paramPasswordResult.equals(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult.SUCCESS))
      dissmissPasswordFragment();
    switch (2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierBillingPasswordDialogFragment$CarrierBillingPasswordResultListener$PasswordResult[paramPasswordResult.ordinal()])
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
    if (this.mTosFragment != null)
    {
      this.mTosFragment.dismiss();
      this.mTosFragment = null;
    }
    switch (2.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$CarrierTosDialogFragment$CarrierTosResultListener$TosResult[paramTosResult.ordinal()])
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
      setAcceptedTos();
      performNext();
      continue;
      FinskyLog.d("Showing TOS to user failed.", new Object[0]);
      fail(FinskyApp.get().getString(2131165279));
      continue;
      log("dcbTosChangedCancel");
      this.mEventLog.logTag("dcbTosChangedCancel", new Object[0]);
      cancel();
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    dissmissPasswordFragment();
    if (this.mAssociationAction != null)
      this.mAssociationAction.cancel();
    hideVerifyAssociationDialog();
    fail(FinskyApp.get().getString(2131165279));
  }

  public void onResponse(CarrierBilling.VerifyAssociationResponse paramVerifyAssociationResponse)
  {
    hideVerifyAssociationDialog();
    if (paramVerifyAssociationResponse.getStatus() == 1)
    {
      this.mAssociationRequired = false;
      this.mInstrumentUpdateRequired = true;
      if ((paramVerifyAssociationResponse.hasCarrierTos()) && (paramVerifyAssociationResponse.getCarrierTos().getNeedsDcbTosAcceptance()))
      {
        this.mDcbTosAcceptanceRequired = true;
        CommonDevice.CarrierTosEntry localCarrierTosEntry = paramVerifyAssociationResponse.getCarrierTos().getDcbTos();
        BillingPreferences.CARRIER_DCB_TOS_URL.put(localCarrierTosEntry.getUrl());
        BillingPreferences.CARRIER_DCB_TOS_VERSION.put(localCarrierTosEntry.getVersion());
      }
      performNext();
      return;
    }
    String str;
    if (paramVerifyAssociationResponse.getStatus() == 3)
      str = FinskyApp.get().getString(2131165302);
    while (true)
    {
      fail(str);
      break;
      if (paramVerifyAssociationResponse.getStatus() == 2)
      {
        if ((paramVerifyAssociationResponse.hasCarrierErrorMessage()) && (!TextUtils.isEmpty(paramVerifyAssociationResponse.getCarrierErrorMessage())))
        {
          str = paramVerifyAssociationResponse.getCarrierErrorMessage();
        }
        else if ((this.mParams == null) || (TextUtils.isEmpty(this.mParams.getCustomerSupport())))
        {
          str = FinskyApp.get().getString(2131165304);
        }
        else
        {
          FinskyApp localFinskyApp = FinskyApp.get();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = this.mParams.getCustomerSupport();
          str = localFinskyApp.getString(2131165303, arrayOfObject);
        }
      }
      else
        str = FinskyApp.get().getString(2131165279);
    }
  }

  public void onVerifyCancel()
  {
    if (this.mAssociationAction != null)
      this.mAssociationAction.cancel();
    cancel();
  }

  protected void performNext()
  {
    if (this.mState == State.CHECK_INSTRUMENT_STATUS)
      if (this.mAssociationRequired)
        startAssociation();
    while (true)
    {
      return;
      this.mState = State.CHECK_DCB_TOS_VERSION;
      performNext();
      continue;
      if (this.mState == State.CHECK_DCB_TOS_VERSION)
      {
        if (this.mDcbTosAcceptanceRequired)
        {
          createAndShowTosFragment((String)BillingPreferences.CARRIER_DCB_TOS_URL.get());
        }
        else if (this.mInstrumentUpdateRequired)
        {
          this.mState = State.SENDING_REQUEST;
          updateCarrierBillingInstrument();
        }
        else if (this.mPasswordRequired)
        {
          this.mState = State.PASSWORD_REQUEST;
          createAndShowPasswordFragment();
        }
        else
        {
          finish();
        }
      }
      else if (this.mState == State.SENDING_REQUEST)
      {
        this.mContext.hideProgress();
        if (this.mUpdateInstrumentResponse == null)
        {
          FinskyLog.w("Failed to get update instrument response.", new Object[0]);
          fail(FinskyApp.get().getString(2131165279));
        }
        else if (this.mUpdateInstrumentResponse.getResult() == 0)
        {
          if (this.mPasswordRequired)
          {
            this.mState = State.PASSWORD_REQUEST;
            createAndShowPasswordFragment();
          }
          else
          {
            finish();
          }
        }
        else if (this.mUpdateInstrumentResponse.getCheckoutTokenRequired())
        {
          getAuthTokenAndContinue(true);
        }
        else
        {
          FinskyLog.w("Updating DCB instrument failed.", new Object[0]);
          fail(FinskyApp.get().getString(2131165279));
        }
      }
      else
      {
        if (this.mState != State.PASSWORD_REQUEST)
          break;
        dissmissPasswordFragment();
        this.mInstrument.setPassphrase(this.mPassword);
        finish();
      }
    }
    throw new IllegalStateException("Unexpected state: " + this.mState);
  }

  public void performRequestWithToken(String paramString)
  {
    CommonDevice.CarrierTos localCarrierTos = new CommonDevice.CarrierTos();
    String str = (String)getDcbTosPreference().get();
    if (!TextUtils.isEmpty(str))
      localCarrierTos.setDcbTos(new CommonDevice.CarrierTosEntry().setVersion(str));
    CommonDevice.CarrierBillingInstrument localCarrierBillingInstrument = new CommonDevice.CarrierBillingInstrument();
    localCarrierBillingInstrument.setInstrumentKey(this.mStorage.getCurrentSimIdentifier());
    localCarrierBillingInstrument.setAcceptedCarrierTos(localCarrierTos);
    BuyInstruments.UpdateInstrumentRequest localUpdateInstrumentRequest = new BuyInstruments.UpdateInstrumentRequest();
    localUpdateInstrumentRequest.setInstrument(new CommonDevice.Instrument().setCarrierBilling(localCarrierBillingInstrument));
    this.mDfeApi.updateInstrument(localUpdateInstrumentRequest, paramString, new Response.Listener()
    {
      public void onResponse(BuyInstruments.UpdateInstrumentResponse paramAnonymousUpdateInstrumentResponse)
      {
        CompleteDcb3Flow.access$002(CompleteDcb3Flow.this, paramAnonymousUpdateInstrumentResponse);
        CompleteDcb3Flow.this.performNext();
      }
    }
    , this);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    if (this.mState != State.CHECK_INSTRUMENT_STATUS)
      throw new IllegalStateException();
    this.mState = State.valueOf(paramBundle.getString("state"));
    if (this.mState == State.SENDING_REQUEST)
    {
      if (!this.mPasswordRequired)
        break label213;
      this.mState = State.PASSWORD_REQUEST;
      createAndShowPasswordFragment();
    }
    while (true)
    {
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
      if (paramBundle.containsKey("verify_association_dialog"))
      {
        this.mVerifyAssociationFragment = ((VerifyPinDialogFragment)this.mContext.restoreFragment(paramBundle, "verify_association_dialog"));
        this.mVerifyAssociationFragment.setOnResultListener(this);
        this.mAssociationAction = new AccountAssociationFactoryImpl(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix).createAccountAssociationAction();
        this.mAssociationAction.resumeState(paramBundle, this, this);
      }
      return;
      label213: finish();
    }
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putString("state", this.mState.name());
    if (this.mTosFragment != null)
      this.mContext.persistFragment(paramBundle, "tos_fragment", this.mTosFragment);
    if (this.mPasswordFragment != null)
      this.mContext.persistFragment(paramBundle, "password_fragment", this.mPasswordFragment);
    if (this.mVerifyAssociationFragment != null)
    {
      this.mContext.persistFragment(paramBundle, "verify_association_dialog", this.mVerifyAssociationFragment);
      if (this.mAssociationAction != null)
      {
        this.mAssociationAction.saveState(paramBundle);
        this.mAssociationAction.cancel();
      }
    }
  }

  public void start()
  {
    performNext();
  }

  void updateCarrierBillingInstrument()
  {
    this.mContext.showProgress(2131165259);
    getAuthTokenAndContinue(false);
  }

  public static enum State
  {
    static
    {
      CHECK_DCB_TOS_VERSION = new State("CHECK_DCB_TOS_VERSION", 1);
      PASSWORD_REQUEST = new State("PASSWORD_REQUEST", 2);
      SENDING_REQUEST = new State("SENDING_REQUEST", 3);
      State[] arrayOfState = new State[4];
      arrayOfState[0] = CHECK_INSTRUMENT_STATUS;
      arrayOfState[1] = CHECK_DCB_TOS_VERSION;
      arrayOfState[2] = PASSWORD_REQUEST;
      arrayOfState[3] = SENDING_REQUEST;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.CompleteDcb3Flow
 * JD-Core Version:    0.6.2
 */
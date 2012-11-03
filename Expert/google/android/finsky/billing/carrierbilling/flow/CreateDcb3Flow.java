package com.google.android.finsky.billing.carrierbilling.flow;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingPreferences;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.flow.association.AccountAssociationFactoryImpl;
import com.google.android.finsky.billing.carrierbilling.flow.association.AssociationAction;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.Type;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog.CarrierBillingErrorListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyPinDialogFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.VerifyPinDialogFragment.VerifyPinListener;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTos;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierTosEntry;
import com.google.android.finsky.remoting.protos.CommonDevice.DeviceAssociation;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.List;

public class CreateDcb3Flow extends InstrumentFlow
  implements AddCarrierBillingFragment.AddCarrierBillingResultListener, EditCarrierBillingFragment.EditCarrierBillingResultListener, Response.ErrorListener, CarrierBillingErrorDialog.CarrierBillingErrorListener, VerifyPinDialogFragment.VerifyPinListener, Response.Listener<BuyInstruments.UpdateInstrumentResponse>
{
  private final String mAccountName;
  private BuyInstruments.UpdateInstrumentResponse mAddCarrierBillingResponse;
  private AddCarrierBillingFragment mAddFragment;
  private boolean mAddFragmentShown;
  private AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult mAddResult = null;
  private BillingUtils.AddressMode mAddressMode = BillingUtils.AddressMode.FULL_ADDRESS;
  private final Analytics mAnalytics;
  private AssociationAction mAssociationAction;
  private String mAssociationAddress;
  private AssociationListener mAssociationListener;
  private String mAssociationPrefix;
  private final BillingFlowContext mContext;
  private final DfeApi mDfeApi;
  private EditCarrierBillingFragment mEditFragment;
  private CarrierBillingErrorDialog mErrorFragment;
  private CarrierBillingParameters mParams;
  private String mReferrer;
  private String mReferrerListCookie;
  private ArrayList<Integer> mRetriableErrorList;
  private State mState = State.INIT;
  private final CarrierBillingStorage mStorage;
  private BillingAddress.Address mSubscriberAddress;
  private BillingUtils.CreateInstrumentUiMode mUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL;
  private SubscriberInfo mUserProvidedAddress;
  private VerifyPinDialogFragment mVerifyFragment;

  public CreateDcb3Flow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    this(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, BillingLocator.getCarrierBillingStorage(), paramDfeApi, paramAnalytics, paramBundle);
  }

  CreateDcb3Flow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, CarrierBillingStorage paramCarrierBillingStorage, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mStorage = paramCarrierBillingStorage;
    this.mDfeApi = paramDfeApi;
    this.mAnalytics = paramAnalytics;
    this.mParams = this.mStorage.getParams();
    this.mAccountName = paramDfeApi.getAccountName();
    BillingUtils.AddressMode localAddressMode;
    if (((Boolean)G.enableDcbReducedBillingAddress.get()).booleanValue())
    {
      List localList = BillingLocator.getBillingCountries();
      if (localList != null)
      {
        VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(FinskyApp.get(), null), localList);
        if (localCountry != null)
        {
          if (!localCountry.getAllowsReducedBillingAddress())
            break label247;
          localAddressMode = BillingUtils.AddressMode.REDUCED_ADDRESS;
          this.mAddressMode = localAddressMode;
        }
      }
    }
    if (paramBundle != null)
    {
      this.mReferrer = paramBundle.getString("referrer_url");
      this.mReferrerListCookie = paramBundle.getString("referrer_list_cookie");
      if (!paramBundle.containsKey("ui_mode"))
        break label255;
    }
    label247: label255: for (this.mUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(paramBundle.getString("ui_mode")); ; this.mUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL)
    {
      CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = ((CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(paramBundle.getBundle("extra_paramters"), "dcb_instrument")).getCarrierBillingStatus();
      if (localCarrierBillingInstrumentStatus.hasDeviceAssociation())
      {
        this.mAssociationAddress = localCarrierBillingInstrumentStatus.getDeviceAssociation().getUserTokenRequestAddress();
        this.mAssociationPrefix = localCarrierBillingInstrumentStatus.getDeviceAssociation().getUserTokenRequestMessage();
      }
      return;
      localAddressMode = BillingUtils.AddressMode.FULL_ADDRESS;
      break;
    }
  }

  private void continueResume(Bundle paramBundle)
  {
    if (this.mState != State.INIT)
      throw new IllegalStateException();
    this.mState = State.valueOf(paramBundle.getString("state"));
    if (this.mState == State.SENDING_REQUEST)
      finish();
    this.mAddFragmentShown = paramBundle.getBoolean("add_fragment_shown");
    this.mUserProvidedAddress = ((SubscriberInfo)paramBundle.getParcelable("user_provided_address"));
    if (paramBundle.containsKey("error_dialog"))
    {
      this.mErrorFragment = ((CarrierBillingErrorDialog)this.mContext.restoreFragment(paramBundle, "error_dialog"));
      this.mErrorFragment.setOnResultListener(this);
    }
    if (paramBundle.containsKey("add_fragment"))
    {
      this.mAddFragment = ((AddCarrierBillingFragment)this.mContext.restoreFragment(paramBundle, "add_fragment"));
      this.mAddFragment.setOnResultListener(this);
    }
    if (paramBundle.containsKey("edit_fragment"))
    {
      this.mEditFragment = ((EditCarrierBillingFragment)this.mContext.restoreFragment(paramBundle, "edit_fragment"));
      this.mEditFragment.setOnResultListener(this);
    }
    if (paramBundle.containsKey("verify_dialog"))
    {
      this.mAssociationAction = new AccountAssociationFactoryImpl(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix).createAccountAssociationAction();
      this.mAssociationListener = new AssociationListener();
      this.mAssociationAction.resumeState(paramBundle, this.mAssociationListener, this);
      this.mVerifyFragment = ((VerifyPinDialogFragment)this.mContext.restoreFragment(paramBundle, "verify_dialog"));
      this.mVerifyFragment.setOnResultListener(this);
    }
  }

  private CommonDevice.Instrument createCarrierBillingInstrument()
  {
    CommonDevice.CarrierBillingInstrument localCarrierBillingInstrument = new CommonDevice.CarrierBillingInstrument();
    localCarrierBillingInstrument.setInstrumentKey(this.mStorage.getCurrentSimIdentifier());
    CommonDevice.CarrierTos localCarrierTos = new CommonDevice.CarrierTos();
    String str1 = (String)BillingPreferences.getCarrierAcceptedDcbTos(BillingLocator.getSubscriberIdFromTelephony()).get(this.mAccountName).get();
    if (!TextUtils.isEmpty(str1))
      localCarrierTos.setDcbTos(new CommonDevice.CarrierTosEntry().setVersion(str1));
    String str2 = (String)BillingPreferences.getCarrierAcceptedPiiTos(BillingLocator.getSubscriberIdFromTelephony()).get(this.mAccountName).get();
    if (!TextUtils.isEmpty(str2))
      localCarrierTos.setPiiTos(new CommonDevice.CarrierTosEntry().setVersion(str2));
    localCarrierBillingInstrument.setAcceptedCarrierTos(localCarrierTos);
    CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
    if (this.mUserProvidedAddress != null)
      localInstrument.setBillingAddress(PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mUserProvidedAddress, this.mAddressMode));
    while (true)
    {
      localInstrument.setCarrierBilling(localCarrierBillingInstrument);
      return localInstrument;
      if (this.mSubscriberAddress == null)
        FinskyLog.wtf("No Subscriber address available.", new Object[0]);
      else
        localInstrument.setBillingAddress(this.mSubscriberAddress);
    }
  }

  private void hideEditFragment()
  {
    if (this.mEditFragment != null)
    {
      this.mContext.hideFragment(this.mEditFragment, false);
      this.mEditFragment = null;
    }
  }

  private void hideProgress()
  {
    this.mContext.hideProgress();
    if (this.mAddFragment != null)
      this.mAddFragment.enableUi(true);
  }

  private void hideTosFragment()
  {
    if (this.mAddFragment != null)
    {
      this.mContext.hideFragment(this.mAddFragment, false);
      this.mAddFragment = null;
    }
  }

  private void hideVerifyAssociationDialog()
  {
    if (this.mVerifyFragment != null)
    {
      this.mVerifyFragment.dismiss();
      this.mVerifyFragment = null;
    }
  }

  private boolean isAssociationRequired()
  {
    return ((Boolean)BillingPreferences.DEVICE_ASSOCIATION_NEEDED.get()).booleanValue();
  }

  private boolean isSubscriberAddressValid(BillingAddress.Address paramAddress)
  {
    boolean bool = true;
    if ((paramAddress == null) || (TextUtils.isEmpty(paramAddress.getPostalCode())) || (TextUtils.isEmpty(paramAddress.getName())));
    for (bool = false; ; bool = false)
      do
        return bool;
      while ((this.mAddressMode != BillingUtils.AddressMode.FULL_ADDRESS) || ((!TextUtils.isEmpty(paramAddress.getAddressLine1())) && (!TextUtils.isEmpty(paramAddress.getCity()))));
  }

  private void log(String paramString)
  {
    this.mAnalytics.logPageView(this.mReferrer, this.mReferrerListCookie, paramString);
  }

  private void logError(VolleyError paramVolleyError)
  {
    logError(DfeUtils.getLegacyErrorCode(paramVolleyError));
  }

  private void logError(String paramString)
  {
    log("addDcbError?error=" + paramString);
  }

  private void showErrorDialog(String paramString, boolean paramBoolean)
  {
    this.mErrorFragment = CarrierBillingErrorDialog.newInstance(paramString, paramBoolean);
    this.mErrorFragment.setOnResultListener(this);
    this.mContext.showDialogFragment(this.mErrorFragment, "error");
  }

  private void showProgress()
  {
    this.mContext.showProgress(2131165259);
    if (this.mAddFragment != null)
      this.mAddFragment.enableUi(false);
  }

  private void showVerifyAssociationDialog()
  {
    this.mVerifyFragment = VerifyPinDialogFragment.newInstance();
    this.mVerifyFragment.setOnResultListener(this);
    this.mContext.showDialogFragment(this.mVerifyFragment, "verifying pin");
  }

  public void back()
  {
    cancel();
  }

  public boolean canGoBack()
  {
    return true;
  }

  public void cancel()
  {
    log("addDcbCancel");
    super.cancel();
  }

  boolean initParams()
  {
    boolean bool = false;
    if ((this.mParams == null) || (this.mDfeApi == null))
    {
      FinskyLog.d("Cannot run this BillingFlow since params are null.", new Object[0]);
      fail(FinskyApp.get().getString(2131165278));
    }
    while (true)
    {
      return bool;
      bool = true;
    }
  }

  public void onActivityResume()
  {
    if ((this.mAssociationAction != null) && (this.mAssociationListener != null))
      this.mAssociationAction.setListener(this.mAssociationListener, this);
  }

  public void onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult paramAddResult)
  {
    this.mAddResult = paramAddResult;
    hideTosFragment();
    if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.SUCCESS)
    {
      setAcceptedTos();
      log("addDcbConfirm");
      performNext();
    }
    while (true)
    {
      return;
      if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.EDIT_ADDRESS)
        performNext();
      else if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.CANCELED)
        cancel();
      else if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.NETWORK_FAILURE)
        showError("Network Connection error while loading Tos.", "NETWORK", FinskyApp.get().getString(2131165435), false);
      else
        showGenericError("Invalid error code.", "UNKNOWN");
    }
  }

  public void onEditCarrierBillingResult(EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult paramEditResult, SubscriberInfo paramSubscriberInfo)
  {
    hideEditFragment();
    if (paramEditResult == EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.SUCCESS)
    {
      this.mUserProvidedAddress = paramSubscriberInfo;
      log("addDcbEditConfirm");
      performNext();
    }
    while (true)
    {
      return;
      if (paramEditResult == EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.CANCELED)
      {
        log("addDcbEditCancel");
        cancel();
      }
      else
      {
        showGenericError("Invalid error code.", "addDcbError");
      }
    }
  }

  public void onErrorDismiss(boolean paramBoolean)
  {
    if (this.mAssociationAction != null)
      this.mAssociationAction.cancel();
    if (paramBoolean)
      fail(FinskyApp.get().getString(2131165278));
    while (true)
    {
      return;
      cancel();
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    hideProgress();
    FinskyLog.w("Error received: %s", new Object[] { paramVolleyError });
    logError(paramVolleyError);
    showErrorDialog(ErrorStrings.get(FinskyApp.get(), paramVolleyError), false);
  }

  public void onResponse(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    this.mAddCarrierBillingResponse = paramUpdateInstrumentResponse;
    hideProgress();
    performNext();
  }

  public void onVerifyCancel()
  {
    log("addDcbCancel");
    if (this.mAssociationAction != null)
      this.mAssociationAction.cancel();
    cancel();
  }

  void performNext()
  {
    switch (3.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateDcb3Flow$State[this.mState.ordinal()])
    {
    default:
      throw new IllegalStateException("Unexpected state: " + this.mState);
    case 1:
      this.mState = State.SHOWING_PII_TOS;
      String str = (String)BillingPreferences.CARRIER_PII_TOS_URL.get();
      if (!TextUtils.isEmpty(str))
        showTosFragment(null, str, null);
      break;
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return;
      performNext();
      continue;
      if (isAssociationRequired())
      {
        this.mState = State.ASSOCIATING_PIN;
        startAssociation();
      }
      else
      {
        this.mState = State.SHOWING_EDIT_USERINFO;
        showEditAddressFragment(null);
        continue;
        if (!isSubscriberAddressValid(this.mSubscriberAddress))
        {
          this.mState = State.SHOWING_EDIT_USERINFO;
          showEditAddressFragment(null);
        }
        else
        {
          this.mState = State.SHOWING_DCB_TOS_AND_USERINFO;
          if (TextUtils.isEmpty(this.mSubscriberAddress.getAddressLine1()))
          {
            showTosFragment(PhoneCarrierBillingUtils.getSubscriberInfo(this.mSubscriberAddress), (String)BillingPreferences.CARRIER_DCB_TOS_URL.get(), null);
          }
          else
          {
            showTosFragment(null, (String)BillingPreferences.CARRIER_DCB_TOS_URL.get(), this.mSubscriberAddress.getAddressLine1());
            continue;
            this.mState = State.SHOWING_DCB_TOS_AND_USERINFO;
            showTosFragment(this.mUserProvidedAddress, (String)BillingPreferences.CARRIER_DCB_TOS_URL.get(), null);
            continue;
            if (this.mAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.EDIT_ADDRESS)
            {
              this.mState = State.SHOWING_EDIT_USERINFO;
              showEditAddressFragment(null);
            }
            else
            {
              this.mState = State.SENDING_REQUEST;
              showProgress();
              getAuthTokenAndContinue(false);
              continue;
              if (this.mAddCarrierBillingResponse == null)
              {
                FinskyLog.wtf("Update instrument response is null.", new Object[0]);
                showGenericError("Update instrument response is null.", "UNKNOWN");
              }
              else if (this.mAddCarrierBillingResponse.getResult() == 0)
              {
                log("addDcbSuccess");
                this.mState = State.DONE;
                finishWithUpdateInstrumentResponse(this.mAddCarrierBillingResponse);
              }
              else if (this.mAddCarrierBillingResponse.getCheckoutTokenRequired())
              {
                getAuthTokenAndContinue(true);
              }
              else if (CarrierBillingUtils.GetRetriableErrors(this.mAddCarrierBillingResponse) != null)
              {
                this.mState = State.SHOWING_EDIT_USERINFO;
                logError("INVALID_INPUT");
                showEditAddressFragment(this.mRetriableErrorList);
              }
              else if (this.mAddCarrierBillingResponse.hasUserMessageHtml())
              {
                showError("Update carrier billing instrument had error", "UNKNOWN", this.mAddCarrierBillingResponse.getUserMessageHtml(), false);
              }
              else
              {
                showGenericError("Could not add carrier billing instrument.", "UNKNOWN");
              }
            }
          }
        }
      }
    }
  }

  public void performRequestWithToken(String paramString)
  {
    BuyInstruments.UpdateInstrumentRequest localUpdateInstrumentRequest = new BuyInstruments.UpdateInstrumentRequest();
    localUpdateInstrumentRequest.setInstrument(createCarrierBillingInstrument());
    this.mDfeApi.updateInstrument(localUpdateInstrumentRequest, paramString, this, this);
  }

  public void resumeFromSavedState(final Bundle paramBundle)
  {
    super.resumeFromSavedState(paramBundle);
    if (!initParams());
    while (true)
    {
      return;
      new CarrierBillingAction().run(new Runnable()
      {
        public void run()
        {
          CreateDcb3Flow.this.continueResume(paramBundle);
        }
      });
    }
  }

  public void saveState(Bundle paramBundle)
  {
    super.saveState(paramBundle);
    paramBundle.putString("state", this.mState.name());
    paramBundle.putBoolean("add_fragment_shown", this.mAddFragmentShown);
    if (this.mErrorFragment != null)
      this.mContext.persistFragment(paramBundle, "error_dialog", this.mErrorFragment);
    if (this.mAddFragment != null)
      this.mContext.persistFragment(paramBundle, "add_fragment", this.mAddFragment);
    if (this.mEditFragment != null)
      this.mContext.persistFragment(paramBundle, "edit_fragment", this.mEditFragment);
    if (this.mVerifyFragment != null)
    {
      this.mContext.persistFragment(paramBundle, "verify_dialog", this.mVerifyFragment);
      if (this.mAssociationAction != null)
        this.mAssociationAction.saveState(paramBundle);
    }
    if (this.mUserProvidedAddress != null)
      paramBundle.putParcelable("user_provided_address", this.mUserProvidedAddress);
  }

  void setAcceptedTos()
  {
    if (this.mState == State.SHOWING_PII_TOS)
      BillingPreferences.getCarrierAcceptedPiiTos(BillingLocator.getSubscriberIdFromTelephony()).get(this.mAccountName).put(BillingPreferences.CARRIER_PII_TOS_VERSION.get());
    while (true)
    {
      return;
      if (this.mState == State.SHOWING_DCB_TOS_AND_USERINFO)
        BillingPreferences.getCarrierAcceptedDcbTos(BillingLocator.getSubscriberIdFromTelephony()).get(this.mAccountName).put(BillingPreferences.CARRIER_DCB_TOS_VERSION.get());
    }
  }

  void showEditAddressFragment(ArrayList<Integer> paramArrayList)
  {
    BillingUtils.CreateInstrumentUiMode localCreateInstrumentUiMode = this.mUiMode;
    if (this.mUserProvidedAddress != null);
    for (SubscriberInfo localSubscriberInfo = this.mUserProvidedAddress; ; localSubscriberInfo = PhoneCarrierBillingUtils.getSubscriberInfo(this.mSubscriberAddress))
    {
      this.mEditFragment = EditCarrierBillingFragment.newInstance(this.mAddressMode, localSubscriberInfo, paramArrayList, localCreateInstrumentUiMode);
      this.mEditFragment.setOnResultListener(this);
      this.mContext.showFragment(this.mEditFragment, -1, false);
      return;
    }
  }

  void showError(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    FinskyLog.w(paramString1, new Object[0]);
    logError(paramString2);
    showErrorDialog(paramString3, paramBoolean);
  }

  void showGenericError(String paramString1, String paramString2)
  {
    FinskyApp localFinskyApp = FinskyApp.get();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.mParams.getName();
    showError(paramString1, paramString2, localFinskyApp.getString(2131165282, arrayOfObject), false);
  }

  void showTosFragment(SubscriberInfo paramSubscriberInfo, String paramString1, String paramString2)
  {
    AddCarrierBillingFragment.Type localType;
    if (TextUtils.isEmpty(paramString1))
      if (paramSubscriberInfo != null)
        localType = AddCarrierBillingFragment.Type.FULL_ADDRESS;
    while (true)
    {
      this.mAddFragment = AddCarrierBillingFragment.newInstance(localType, paramSubscriberInfo, this.mUiMode, paramString1, paramString2);
      this.mAddFragment.setOnResultListener(this);
      this.mAddFragmentShown = true;
      this.mContext.showFragment(this.mAddFragment, -1, false);
      while (true)
      {
        return;
        if (!TextUtils.isEmpty(paramString2))
        {
          localType = AddCarrierBillingFragment.Type.ADDRESS_SNIPPET;
          break;
        }
        FinskyLog.w("showTosFragment has no address and tos. wrong fragment.", new Object[0]);
      }
      if (paramSubscriberInfo != null)
        localType = AddCarrierBillingFragment.Type.FULL_ADDRESS_AND_TOS;
      else if (!TextUtils.isEmpty(paramString2))
        localType = AddCarrierBillingFragment.Type.ADDRESS_SNIPPET_AND_TOS;
      else
        localType = AddCarrierBillingFragment.Type.TOS;
    }
  }

  public void start()
  {
    log("addDcb");
    if (!initParams());
    while (true)
    {
      return;
      new CarrierBillingAction().run(new Runnable()
      {
        public void run()
        {
          CreateDcb3Flow.this.performNext();
        }
      });
    }
  }

  void startAssociation()
  {
    showVerifyAssociationDialog();
    this.mAssociationAction = new AccountAssociationFactoryImpl(this.mDfeApi, this.mAssociationAddress, this.mAssociationPrefix).createAccountAssociationAction();
    this.mAssociationListener = new AssociationListener();
    this.mAssociationAction.start(this.mAssociationListener, this);
  }

  class AssociationListener
    implements Response.Listener<CarrierBilling.VerifyAssociationResponse>
  {
    AssociationListener()
    {
    }

    public void onResponse(CarrierBilling.VerifyAssociationResponse paramVerifyAssociationResponse)
    {
      CreateDcb3Flow.this.hideVerifyAssociationDialog();
      if (paramVerifyAssociationResponse.getStatus() == 1)
      {
        if (paramVerifyAssociationResponse.hasBillingAddress())
          CreateDcb3Flow.access$202(CreateDcb3Flow.this, paramVerifyAssociationResponse.getBillingAddress());
        if (paramVerifyAssociationResponse.hasCarrierTos())
        {
          CommonDevice.CarrierTosEntry localCarrierTosEntry = paramVerifyAssociationResponse.getCarrierTos().getDcbTos();
          BillingPreferences.CARRIER_DCB_TOS_URL.put(localCarrierTosEntry.getUrl());
          BillingPreferences.CARRIER_DCB_TOS_VERSION.put(localCarrierTosEntry.getVersion());
        }
        CreateDcb3Flow.this.performNext();
        return;
      }
      String str1 = "UNKNOWN";
      String str2;
      if (paramVerifyAssociationResponse.getStatus() == 3)
      {
        str2 = FinskyApp.get().getString(2131165302);
        str1 = "ASSOCIATION_TIMEOUT";
      }
      while (true)
      {
        CreateDcb3Flow.this.showError(str2, str1, str2, true);
        break;
        if (paramVerifyAssociationResponse.getStatus() == 2)
        {
          if ((paramVerifyAssociationResponse.hasCarrierErrorMessage()) && (!TextUtils.isEmpty(paramVerifyAssociationResponse.getCarrierErrorMessage())))
            str2 = paramVerifyAssociationResponse.getCarrierErrorMessage();
          while (true)
          {
            str1 = "INVALID_USER";
            break;
            if (TextUtils.isEmpty(CreateDcb3Flow.this.mParams.getCustomerSupport()))
            {
              str2 = FinskyApp.get().getString(2131165304);
            }
            else
            {
              FinskyApp localFinskyApp = FinskyApp.get();
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = CreateDcb3Flow.this.mParams.getCustomerSupport();
              str2 = localFinskyApp.getString(2131165303, arrayOfObject);
            }
          }
        }
        str2 = FinskyApp.get().getString(2131165279);
      }
    }
  }

  static enum State
  {
    static
    {
      ASSOCIATING_PIN = new State("ASSOCIATING_PIN", 2);
      SHOWING_EDIT_USERINFO = new State("SHOWING_EDIT_USERINFO", 3);
      SHOWING_DCB_TOS_AND_USERINFO = new State("SHOWING_DCB_TOS_AND_USERINFO", 4);
      SENDING_REQUEST = new State("SENDING_REQUEST", 5);
      DONE = new State("DONE", 6);
      State[] arrayOfState = new State[7];
      arrayOfState[0] = INIT;
      arrayOfState[1] = SHOWING_PII_TOS;
      arrayOfState[2] = ASSOCIATING_PIN;
      arrayOfState[3] = SHOWING_EDIT_USERINFO;
      arrayOfState[4] = SHOWING_DCB_TOS_AND_USERINFO;
      arrayOfState[5] = SENDING_REQUEST;
      arrayOfState[6] = DONE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.CreateDcb3Flow
 * JD-Core Version:    0.6.2
 */
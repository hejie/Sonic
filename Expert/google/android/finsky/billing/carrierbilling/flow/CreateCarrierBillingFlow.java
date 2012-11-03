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
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.action.CarrierBillingAction;
import com.google.android.finsky.billing.carrierbilling.action.CarrierProvisioningAction;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult;
import com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment.Type;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog;
import com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog.CarrierBillingErrorListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener;
import com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.EncryptedSubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingCredentials;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrument;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateCarrierBillingFlow extends InstrumentFlow
  implements Response.ErrorListener, AddCarrierBillingFragment.AddCarrierBillingResultListener, CarrierBillingErrorDialog.CarrierBillingErrorListener, EditCarrierBillingFragment.EditCarrierBillingResultListener
{
  private AddCarrierBillingFragment mAddFragment;
  private boolean mAddFragmentShown;
  private AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult mAddResult = null;
  private AddressAvailable mAddressAvailable = AddressAvailable.NO_ADDRESS;
  private BillingUtils.AddressMode mAddressMode = BillingUtils.AddressMode.FULL_ADDRESS;
  private final Analytics mAnalytics;
  private final BillingFlowContext mContext;
  private final DfeApi mDfeApi;
  private EditCarrierBillingFragment mEditFragment;
  private CarrierBillingErrorDialog mErrorFragment;
  private CarrierBillingParameters mParams;
  private CarrierBillingProvisioning mProvisioning;
  private String mReferrer;
  private String mReferrerListCookie;
  private boolean mSavingScreenShown;
  private State mState = State.INIT;
  private final CarrierBillingStorage mStorage;
  private String mTosVersion;
  private BillingUtils.CreateInstrumentUiMode mUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL;
  private BuyInstruments.UpdateInstrumentResponse mUpdateInstrumentResponse;
  private SubscriberInfo mUserProvidedAddress;

  public CreateCarrierBillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    this(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, BillingLocator.getCarrierBillingStorage(), paramDfeApi, paramAnalytics, paramBundle);
  }

  CreateCarrierBillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, CarrierBillingStorage paramCarrierBillingStorage, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mStorage = paramCarrierBillingStorage;
    this.mDfeApi = paramDfeApi;
    this.mAnalytics = paramAnalytics;
    this.mParams = this.mStorage.getParams();
    this.mProvisioning = this.mStorage.getProvisioning();
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
            break label202;
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
        break label210;
    }
    label202: label210: for (this.mUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(paramBundle.getString("ui_mode")); ; this.mUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL)
    {
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
    this.mAddFragmentShown = paramBundle.getBoolean("add_fragment_shown");
    this.mUserProvidedAddress = ((SubscriberInfo)paramBundle.getParcelable("user_provided_address"));
    this.mSavingScreenShown = paramBundle.getBoolean("saving_dialog_fragment");
    if (this.mSavingScreenShown)
      finish();
    while (true)
    {
      return;
      setAddressAvailability();
      if (paramBundle.containsKey("error_dialog"))
      {
        this.mErrorFragment = ((CarrierBillingErrorDialog)this.mContext.restoreFragment(paramBundle, "error_dialog"));
        this.mErrorFragment.setOnResultListener(this);
      }
      switch (5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()])
      {
      default:
        if (this.mErrorFragment != null)
          cancel();
        break;
      case 1:
      case 2:
        if (paramBundle.containsKey("add_fragment"))
        {
          this.mAddFragment = ((AddCarrierBillingFragment)this.mContext.restoreFragment(paramBundle, "add_fragment"));
          this.mAddFragment.setOnResultListener(this);
        }
        break;
      case 3:
        if (paramBundle.containsKey("edit_fragment"))
        {
          this.mEditFragment = ((EditCarrierBillingFragment)this.mContext.restoreFragment(paramBundle, "edit_fragment"));
          this.mEditFragment.setOnResultListener(this);
          continue;
          finish();
        }
        break;
      }
    }
  }

  private CommonDevice.Instrument createCarrierBillingInstrument()
  {
    CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
    CommonDevice.CarrierBillingInstrument localCarrierBillingInstrument = new CommonDevice.CarrierBillingInstrument();
    localCarrierBillingInstrument.setInstrumentKey(this.mStorage.getCurrentSimIdentifier());
    localCarrierBillingInstrument.setAccountType(this.mProvisioning.getAccountType());
    localCarrierBillingInstrument.setCurrencyCode(this.mProvisioning.getSubscriberCurrency());
    localCarrierBillingInstrument.setTransactionLimit(this.mProvisioning.getTransactionLimit());
    if (this.mProvisioning.getSubscriberInfo() != null)
      localCarrierBillingInstrument.setSubscriberIdentifier(this.mProvisioning.getSubscriberInfo().getIdentifier());
    CommonDevice.CarrierBillingCredentials localCarrierBillingCredentials = new CommonDevice.CarrierBillingCredentials();
    if (this.mStorage.getCredentials() != null)
    {
      localCarrierBillingCredentials.setValue(this.mStorage.getCredentials().getCredentials());
      localCarrierBillingCredentials.setExpiration(this.mStorage.getCredentials().getExpirationTime());
    }
    localCarrierBillingInstrument.setCredentials(localCarrierBillingCredentials);
    if (this.mUserProvidedAddress != null)
      localInstrument.setBillingAddress(PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mUserProvidedAddress, this.mAddressMode));
    while (true)
    {
      localInstrument.setCarrierBilling(localCarrierBillingInstrument);
      return localInstrument;
      if (this.mProvisioning.getSubscriberInfo() != null)
        localInstrument.setBillingAddress(PhoneCarrierBillingUtils.subscriberInfoToAddress(this.mProvisioning.getSubscriberInfo(), this.mAddressMode));
      else if (this.mProvisioning.getEncryptedSubscriberInfo() != null)
        localCarrierBillingInstrument.setEncryptedSubscriberInfo(this.mProvisioning.getEncryptedSubscriberInfo().toProto());
    }
  }

  private ArrayList<Integer> getInvalidEntries(List<ChallengeProtos.InputValidationError> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ChallengeProtos.InputValidationError localInputValidationError = (ChallengeProtos.InputValidationError)localIterator.next();
      int i = localInputValidationError.getInputField();
      switch (i)
      {
      case 10:
      case 11:
      case 12:
      default:
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(localInputValidationError.getInputField());
        arrayOfObject[1] = localInputValidationError.getErrorMessage();
        FinskyLog.d("InputValidationError that can't be edited: type=%d, message=%s", arrayOfObject);
        break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 13:
        localArrayList.add(Integer.valueOf(i));
      }
    }
    return localArrayList;
  }

  private void getProvisioning(Runnable paramRunnable1, Runnable paramRunnable2)
  {
    new CarrierProvisioningAction().forceRun(paramRunnable1, paramRunnable2, (String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get());
  }

  private ArrayList<Integer> getRetriableErrorList()
  {
    if (2 == this.mUpdateInstrumentResponse.getResult());
    for (ArrayList localArrayList = getInvalidEntries(this.mUpdateInstrumentResponse.getErrorInputFieldList()); ; localArrayList = null)
      return localArrayList;
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
    this.mSavingScreenShown = false;
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

  private boolean isSnippetValid()
  {
    String str = this.mProvisioning.getAddressSnippet();
    if ((!TextUtils.isEmpty(str)) && (!"null".equals(str)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isSubscriberInfoValid()
  {
    if ((this.mProvisioning.getSubscriberInfo() != null) && (!TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getAddress1())) && (!TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getName())) && (!TextUtils.isEmpty(this.mProvisioning.getSubscriberInfo().getCity())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void log(String paramString)
  {
    this.mAnalytics.logPageView(this.mReferrer, this.mReferrerListCookie, paramString);
  }

  private void logEditAddress(boolean paramBoolean)
  {
    log("addDcbEdit?address=" + paramBoolean);
  }

  private void logError(VolleyError paramVolleyError)
  {
    logError(DfeUtils.getLegacyErrorCode(paramVolleyError));
  }

  private void logError(String paramString)
  {
    log("addDcbError?error=" + paramString);
  }

  private void logTosAndAddress(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    String str;
    if (paramBoolean3)
      str = "FULL";
    while (true)
    {
      log("addDcbTos?tos=" + paramBoolean1 + "&address=" + str);
      return;
      if (paramBoolean2)
        str = "SNIPPET";
      else
        str = "NONE";
    }
  }

  private void onEditCancel()
  {
    log("addDcbEditCancel");
    if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS)
      cancel();
    while (true)
    {
      return;
      performNext();
    }
  }

  private void onEditSuccess(SubscriberInfo paramSubscriberInfo)
  {
    this.mUserProvidedAddress = paramSubscriberInfo;
    this.mAddressAvailable = AddressAvailable.FULL_ADDRESS;
    log("addDcbEditConfirm");
    performNext();
  }

  private void setAddressAvailability()
  {
    if ((this.mUserProvidedAddress != null) || (isSubscriberInfoValid()))
      this.mAddressAvailable = AddressAvailable.FULL_ADDRESS;
    while (true)
    {
      return;
      if (isSnippetValid())
        this.mAddressAvailable = AddressAvailable.ADDRESS_SNIPPET;
      else
        this.mAddressAvailable = AddressAvailable.NO_ADDRESS;
    }
  }

  private boolean shouldShowTos()
  {
    boolean bool;
    if (this.mParams.showCarrierTos())
      if (BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get() != null)
        if (!((String)BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.get()).equals(this.mProvisioning.getTosVersion()))
          bool = true;
    while (true)
    {
      return bool;
      bool = false;
      continue;
      bool = true;
      continue;
      bool = false;
    }
  }

  private void showEditAddressFragment(ArrayList<Integer> paramArrayList)
  {
    SubscriberInfo localSubscriberInfo;
    if (this.mAddressAvailable == AddressAvailable.FULL_ADDRESS)
      if (this.mUserProvidedAddress != null)
      {
        localSubscriberInfo = this.mUserProvidedAddress;
        showEditFragment(localSubscriberInfo, paramArrayList);
      }
    while (true)
    {
      return;
      localSubscriberInfo = this.mProvisioning.getSubscriberInfo();
      break;
      showEditFragment(null, null);
    }
  }

  private void showError(String paramString1, String paramString2, String paramString3)
  {
    FinskyLog.w(paramString1, new Object[0]);
    logError(paramString2);
    showErrorDialog(paramString3);
  }

  private void showErrorDialog(String paramString)
  {
    this.mErrorFragment = CarrierBillingErrorDialog.newInstance(paramString, false);
    this.mErrorFragment.setOnResultListener(this);
    this.mContext.showDialogFragment(this.mErrorFragment, "error");
  }

  private void showGenericError(String paramString1, String paramString2)
  {
    FinskyApp localFinskyApp = FinskyApp.get();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.mParams.getName();
    showError(paramString1, paramString2, localFinskyApp.getString(2131165282, arrayOfObject));
  }

  private void showNetworkError(VolleyError paramVolleyError)
  {
    FinskyLog.w("Error received: %s", new Object[] { paramVolleyError });
    logError(paramVolleyError);
    showErrorDialog(ErrorStrings.get(FinskyApp.get(), paramVolleyError));
  }

  private void showProgress()
  {
    this.mSavingScreenShown = true;
    this.mContext.showProgress(2131165259);
    if (this.mAddFragment != null)
      this.mAddFragment.enableUi(false);
  }

  private void showTosFragment()
  {
    if (shouldShowTos())
    {
      if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS)
      {
        localType = AddCarrierBillingFragment.Type.TOS;
        showAddFragment(localType, this.mUserProvidedAddress);
        return;
      }
      if (this.mAddressAvailable == AddressAvailable.FULL_ADDRESS);
      for (localType = AddCarrierBillingFragment.Type.FULL_ADDRESS_AND_TOS; ; localType = AddCarrierBillingFragment.Type.ADDRESS_SNIPPET_AND_TOS)
        break;
    }
    if (this.mAddressAvailable == AddressAvailable.FULL_ADDRESS);
    for (AddCarrierBillingFragment.Type localType = AddCarrierBillingFragment.Type.FULL_ADDRESS; ; localType = AddCarrierBillingFragment.Type.ADDRESS_SNIPPET)
      break;
  }

  public void back()
  {
    if (this.mState == State.SHOWING_EDIT_USERINFO)
    {
      hideEditFragment();
      onEditCancel();
    }
    while (true)
    {
      return;
      cancel();
    }
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
    if ((this.mParams == null) || (this.mProvisioning == null))
    {
      FinskyLog.d("Cannot run this BillingFlow since params or provisioning is null.", new Object[0]);
      fail(FinskyApp.get().getString(2131165278));
    }
    while (true)
    {
      return bool;
      setAddressAvailability();
      bool = true;
    }
  }

  public void onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult paramAddResult)
  {
    this.mAddResult = paramAddResult;
    if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.SUCCESS)
    {
      BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.put(this.mTosVersion);
      showProgress();
      log("addDcbConfirm");
      getProvisioning(new AfterProvisioning(), new AfterError());
    }
    while (true)
    {
      return;
      if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.EDIT_ADDRESS)
        performNext();
      else if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.CANCELED)
        cancel();
      else if (paramAddResult == AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.NETWORK_FAILURE)
        showError("Network Connection error while loading Tos.", "NETWORK", FinskyApp.get().getString(2131165435));
      else
        showGenericError("Invalid error code.", "UNKNOWN");
    }
  }

  public void onEditCarrierBillingResult(EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult paramEditResult, SubscriberInfo paramSubscriberInfo)
  {
    hideEditFragment();
    if (paramEditResult == EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.SUCCESS)
      onEditSuccess(paramSubscriberInfo);
    while (true)
    {
      return;
      if (paramEditResult == EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.CANCELED)
        onEditCancel();
      else
        showGenericError("Invalid error code.", "addDcbError");
    }
  }

  public void onErrorDismiss(boolean paramBoolean)
  {
    this.mState = State.DONE;
    cancel();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    showNetworkError(paramVolleyError);
  }

  void performNext()
  {
    switch (5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()])
    {
    default:
      showGenericError("Invalid Dcb state.", "UNKNOWN");
    case 5:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      BillingPreferences.ACCEPTED_CARRIER_TOS_VERSION.put("");
      if ((!shouldShowTos()) && (this.mAddressAvailable == AddressAvailable.NO_ADDRESS))
      {
        this.mState = State.SHOWING_EDIT_USERINFO;
        showEditAddressFragment(null);
      }
      else
      {
        if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS);
        for (State localState = State.SHOWING_TOS; ; localState = State.SHOWING_TOS_AND_USERINFO)
        {
          this.mState = localState;
          showTosFragment();
          break;
        }
        if (this.mAddressAvailable == AddressAvailable.NO_ADDRESS)
        {
          this.mState = State.SHOWING_EDIT_USERINFO;
          showEditAddressFragment(null);
        }
        else
        {
          this.mState = State.SHOWING_TOS_AND_USERINFO;
          showTosFragment();
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
            this.mState = State.SHOWING_TOS_AND_USERINFO;
            showTosFragment();
            continue;
            if (this.mUpdateInstrumentResponse.getResult() == 0)
            {
              this.mState = State.DONE;
              log("addDcbSuccess");
              finishWithUpdateInstrumentResponse(this.mUpdateInstrumentResponse);
            }
            else if (this.mUpdateInstrumentResponse.getCheckoutTokenRequired())
            {
              getAuthTokenAndContinue(false);
            }
            else
            {
              ArrayList localArrayList = getRetriableErrorList();
              if ((localArrayList != null) && (!localArrayList.isEmpty()))
              {
                this.mState = State.SHOWING_EDIT_USERINFO;
                logError("INVALID_INPUT");
                showEditAddressFragment(localArrayList);
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
    this.mDfeApi.updateInstrument(localUpdateInstrumentRequest, paramString, new Response.Listener()
    {
      public void onResponse(BuyInstruments.UpdateInstrumentResponse paramAnonymousUpdateInstrumentResponse)
      {
        CreateCarrierBillingFlow.access$102(CreateCarrierBillingFlow.this, paramAnonymousUpdateInstrumentResponse);
        CreateCarrierBillingFlow.this.hideProgress();
        CreateCarrierBillingFlow.this.performNext();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        CreateCarrierBillingFlow.this.showNetworkError(paramAnonymousVolleyError);
        CreateCarrierBillingFlow.this.hideProgress();
      }
    });
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
          CreateCarrierBillingFlow.this.continueResume(paramBundle);
        }
      });
    }
  }

  public void saveState(Bundle paramBundle)
  {
    super.saveState(paramBundle);
    paramBundle.putString("state", this.mState.name());
    paramBundle.putBoolean("add_fragment_shown", this.mAddFragmentShown);
    paramBundle.putBoolean("saving_dialog_fragment", this.mSavingScreenShown);
    if (this.mErrorFragment != null)
      this.mContext.persistFragment(paramBundle, "error_dialog", this.mErrorFragment);
    if (this.mUserProvidedAddress != null)
      paramBundle.putParcelable("user_provided_address", this.mUserProvidedAddress);
    switch (5.$SwitchMap$com$google$android$finsky$billing$carrierbilling$flow$CreateCarrierBillingFlow$State[this.mState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return;
      if (this.mAddFragment != null)
      {
        this.mContext.persistFragment(paramBundle, "add_fragment", this.mAddFragment);
        continue;
        if (this.mEditFragment != null)
          this.mContext.persistFragment(paramBundle, "edit_fragment", this.mEditFragment);
      }
    }
  }

  void showAddFragment(AddCarrierBillingFragment.Type paramType, SubscriberInfo paramSubscriberInfo)
  {
    boolean bool1;
    boolean bool2;
    if ((paramType == AddCarrierBillingFragment.Type.TOS) || (paramType == AddCarrierBillingFragment.Type.ADDRESS_SNIPPET_AND_TOS) || (paramType == AddCarrierBillingFragment.Type.FULL_ADDRESS_AND_TOS))
    {
      bool1 = true;
      if ((paramType != AddCarrierBillingFragment.Type.ADDRESS_SNIPPET) && (paramType != AddCarrierBillingFragment.Type.ADDRESS_SNIPPET_AND_TOS))
        break label146;
      bool2 = true;
      label40: if ((paramType != AddCarrierBillingFragment.Type.FULL_ADDRESS) && (paramType != AddCarrierBillingFragment.Type.FULL_ADDRESS_AND_TOS))
        break label152;
    }
    label146: label152: for (boolean bool3 = true; ; bool3 = false)
    {
      logTosAndAddress(bool1, bool2, bool3);
      hideTosFragment();
      this.mTosVersion = this.mProvisioning.getTosVersion();
      String str = this.mProvisioning.getTosUrl();
      this.mAddFragment = AddCarrierBillingFragment.newInstance(paramType, paramSubscriberInfo, this.mUiMode, str, this.mProvisioning.getAddressSnippet());
      this.mAddFragment.setOnResultListener(this);
      this.mAddFragmentShown = true;
      this.mContext.showFragment(this.mAddFragment, -1, false);
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label40;
    }
  }

  void showEditFragment(SubscriberInfo paramSubscriberInfo, ArrayList<Integer> paramArrayList)
  {
    hideTosFragment();
    if (paramSubscriberInfo != null);
    for (boolean bool = true; ; bool = false)
    {
      logEditAddress(bool);
      BillingUtils.CreateInstrumentUiMode localCreateInstrumentUiMode = this.mUiMode;
      this.mEditFragment = EditCarrierBillingFragment.newInstance(this.mAddressMode, paramSubscriberInfo, paramArrayList, localCreateInstrumentUiMode);
      this.mEditFragment.setOnResultListener(this);
      this.mContext.showFragment(this.mEditFragment, -1, false);
      return;
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
          CreateCarrierBillingFlow.this.performNext();
        }
      });
    }
  }

  private static enum AddressAvailable
  {
    static
    {
      ADDRESS_SNIPPET = new AddressAvailable("ADDRESS_SNIPPET", 1);
      NO_ADDRESS = new AddressAvailable("NO_ADDRESS", 2);
      AddressAvailable[] arrayOfAddressAvailable = new AddressAvailable[3];
      arrayOfAddressAvailable[0] = FULL_ADDRESS;
      arrayOfAddressAvailable[1] = ADDRESS_SNIPPET;
      arrayOfAddressAvailable[2] = NO_ADDRESS;
    }
  }

  class AfterError
    implements Runnable
  {
    AfterError()
    {
    }

    public void run()
    {
      CreateCarrierBillingFlow.this.hideProgress();
      CreateCarrierBillingFlow.this.showGenericError("Fetching provisioning from carrier failed", "UNKNOWN");
    }
  }

  class AfterProvisioning
    implements Runnable
  {
    AfterProvisioning()
    {
    }

    public void run()
    {
      CreateCarrierBillingFlow.this.hideProgress();
      CreateCarrierBillingFlow.access$402(CreateCarrierBillingFlow.this, CreateCarrierBillingFlow.this.mStorage.getProvisioning());
      CreateCarrierBillingFlow.this.setAddressAvailability();
      CreateCarrierBillingFlow.this.performNext();
    }
  }

  static enum State
  {
    static
    {
      SHOWING_EDIT_USERINFO = new State("SHOWING_EDIT_USERINFO", 3);
      SENDING_REQUEST = new State("SENDING_REQUEST", 4);
      DONE = new State("DONE", 5);
      State[] arrayOfState = new State[6];
      arrayOfState[0] = INIT;
      arrayOfState[1] = SHOWING_TOS;
      arrayOfState[2] = SHOWING_TOS_AND_USERINFO;
      arrayOfState[3] = SHOWING_EDIT_USERINFO;
      arrayOfState[4] = SENDING_REQUEST;
      arrayOfState[5] = DONE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.CreateCarrierBillingFlow
 * JD-Core Version:    0.6.2
 */
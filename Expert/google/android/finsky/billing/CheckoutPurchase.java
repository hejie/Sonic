package com.google.android.finsky.billing;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.Buy.PurchaseStatusResponse;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;
import com.google.android.finsky.remoting.protos.Common.Docid;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.remoting.protos.Library.LibraryInAppDetails;
import com.google.android.finsky.remoting.protos.Library.LibraryMutation;
import com.google.android.finsky.remoting.protos.Library.LibraryUpdate;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CheckoutPurchase
  implements Response.Listener<Buy.BuyResponse>, Response.ErrorListener, AsyncAuthenticator.Listener
{
  private static Map<State, List<State>> sValidTransitions = Maps.newHashMap();
  private String mAddInstrumentHintText = null;
  private final AsyncAuthenticator mAuthenticator;
  private Buy.BuyResponse.CheckoutInfo mCheckoutInfo;
  private String mCheckoutToken;
  private Bundle mCompleteChallengeResponses = new Bundle();
  private Buy.BuyResponse mCompleteResponse;
  private boolean mCompletingHasAcceptedTos;
  private Instrument mCompletingInstrument;
  private String mCompletingRiskHeader;
  private String mConfirmButtonText = null;
  private Instrument mDefaultInstrument;
  private final DfeApi mDfeApi;
  private final Document mDoc;
  private List<CommonDevice.Instrument> mEligibleInstruments = Lists.newArrayList();
  private Error mError;
  private Bundle mExtraPurchaseData;
  private final InstrumentFactory mInstrumentFactory;
  private boolean mInstrumentRequired = true;
  private List<Instrument> mInstruments = Lists.newArrayList();
  private boolean mIsCheckoutTokenRetry;
  private Listener mListener;
  private int mNumAuthRetries;
  private final int mOfferType;
  private boolean mPinAuthenticated = false;
  private Bundle mPrepareChallengeResponses = new Bundle();
  private Buy.BuyResponse mPrepareResponse;
  private IabParameters mPreparingCompletingIabParameters;
  private Request<?> mPreparingRequest;
  private final PurchaseStatusListener mPurchaseStatusListener;
  private CommonDevice.Instrument mRejectedInstrument;
  private State mState;
  private List<Tos> mTosList = Lists.newArrayList();
  private VolleyError mVolleyError;

  static
  {
    Map localMap1 = sValidTransitions;
    State localState1 = State.PREPARING;
    State[] arrayOfState1 = new State[6];
    arrayOfState1[0] = State.INIT;
    arrayOfState1[1] = State.PREPARED;
    arrayOfState1[2] = State.PREPARE_CHALLENGE_REQUIRED;
    arrayOfState1[3] = State.PREPARING;
    arrayOfState1[4] = State.COMPLETE_CHALLENGE_REQUIRED;
    arrayOfState1[5] = State.ERROR;
    localMap1.put(localState1, Lists.newArrayList(arrayOfState1));
    Map localMap2 = sValidTransitions;
    State localState2 = State.PREPARED;
    State[] arrayOfState2 = new State[2];
    arrayOfState2[0] = State.PREPARING;
    arrayOfState2[1] = State.COMPLETING;
    localMap2.put(localState2, Lists.newArrayList(arrayOfState2));
    Map localMap3 = sValidTransitions;
    State localState3 = State.COMPLETING;
    State[] arrayOfState3 = new State[3];
    arrayOfState3[0] = State.PREPARED;
    arrayOfState3[1] = State.COMPLETING;
    arrayOfState3[2] = State.COMPLETE_CHALLENGE_REQUIRED;
    localMap3.put(localState3, Lists.newArrayList(arrayOfState3));
    sValidTransitions.put(State.ERROR, Lists.newArrayList(State.values()));
    Map localMap4 = sValidTransitions;
    State localState4 = State.PREPARE_CHALLENGE_REQUIRED;
    State[] arrayOfState4 = new State[1];
    arrayOfState4[0] = State.PREPARING;
    localMap4.put(localState4, Lists.newArrayList(arrayOfState4));
    Map localMap5 = sValidTransitions;
    State localState5 = State.COMPLETE_CHALLENGE_REQUIRED;
    State[] arrayOfState5 = new State[1];
    arrayOfState5[0] = State.COMPLETING;
    localMap5.put(localState5, Lists.newArrayList(arrayOfState5));
    Map localMap6 = sValidTransitions;
    State localState6 = State.COMPLETING_POLLING_STATUS;
    State[] arrayOfState6 = new State[1];
    arrayOfState6[0] = State.COMPLETING;
    localMap6.put(localState6, Lists.newArrayList(arrayOfState6));
    Map localMap7 = sValidTransitions;
    State localState7 = State.COMPLETED;
    State[] arrayOfState7 = new State[2];
    arrayOfState7[0] = State.COMPLETING;
    arrayOfState7[1] = State.COMPLETING_POLLING_STATUS;
    localMap7.put(localState7, Lists.newArrayList(arrayOfState7));
  }

  public CheckoutPurchase(DfeApi paramDfeApi, AsyncAuthenticator paramAsyncAuthenticator, InstrumentFactory paramInstrumentFactory, PurchaseStatusListener paramPurchaseStatusListener, Document paramDocument, int paramInt)
  {
    this.mDfeApi = paramDfeApi;
    this.mAuthenticator = paramAsyncAuthenticator;
    this.mInstrumentFactory = paramInstrumentFactory;
    this.mPurchaseStatusListener = paramPurchaseStatusListener;
    this.mDoc = paramDocument;
    this.mOfferType = paramInt;
    this.mState = State.INIT;
  }

  private void cancelCurrentAction()
  {
    if ((this.mState == State.PREPARING) && (this.mPreparingRequest != null))
      this.mPreparingRequest.cancel();
  }

  private void checkInState(State paramState)
  {
    if (this.mState != paramState)
      throw new IllegalStateException("This operation is only valid in state " + paramState + ". Current state = " + this.mState + ")");
  }

  private void checkStateTransition(State paramState1, State paramState2)
  {
    if (!((List)sValidTransitions.get(paramState2)).contains(paramState1))
      throw new IllegalStateException("Cannot transition from state " + paramState1 + " to " + paramState2);
  }

  private void checkTosAcceptance()
  {
    if (!areAllTossesAccepted())
      FinskyLog.wtf("TOSes to accept present, not all were accepted!", new Object[0]);
  }

  private Bundle extractExtraPurchaseData(Buy.PurchaseStatusResponse paramPurchaseStatusResponse)
  {
    Bundle localBundle;
    if ((paramPurchaseStatusResponse.hasLibraryUpdate()) && (paramPurchaseStatusResponse.getLibraryUpdate().getMutationCount() > 0))
    {
      Iterator localIterator = paramPurchaseStatusResponse.getLibraryUpdate().getMutationList().iterator();
      while (localIterator.hasNext())
      {
        Library.LibraryMutation localLibraryMutation = (Library.LibraryMutation)localIterator.next();
        if ((localLibraryMutation.getDocid().getType() == 11) && (TextUtils.equals(localLibraryMutation.getDocid().getBackendDocid(), this.mDoc.getBackendDocId())) && (localLibraryMutation.hasInAppDetails()))
        {
          Library.LibraryInAppDetails localLibraryInAppDetails = localLibraryMutation.getInAppDetails();
          if ((localLibraryInAppDetails.hasSignature()) && (localLibraryInAppDetails.hasSignedPurchaseData()))
          {
            localBundle = new Bundle();
            localBundle.putString("inapp_signed_purchase_data", localLibraryInAppDetails.getSignedPurchaseData());
            localBundle.putString("inapp_purchase_data_signature", localLibraryInAppDetails.getSignature());
          }
        }
      }
    }
    while (true)
    {
      return localBundle;
      localBundle = null;
    }
  }

  private void getAuthTokenAsyncAndPerformRequest()
  {
    AsyncAuthenticator localAsyncAuthenticator = this.mAuthenticator;
    if (this.mIsCheckoutTokenRetry);
    for (String str = this.mCheckoutToken; ; str = null)
    {
      localAsyncAuthenticator.getToken(this, str);
      return;
    }
  }

  private int getFopVersionFromOption(Buy.BuyResponse.CheckoutInfo.CheckoutOption paramCheckoutOption)
  {
    if (paramCheckoutOption.hasInstrument());
    for (int i = BillingUtils.getFopVersion(paramCheckoutOption.getInstrument()); ; i = 0)
      return i;
  }

  private void onPurchaseStatusResponse(Buy.PurchaseStatusResponse paramPurchaseStatusResponse)
  {
    this.mExtraPurchaseData = extractExtraPurchaseData(paramPurchaseStatusResponse);
    if (this.mPurchaseStatusListener != null)
      this.mPurchaseStatusListener.onPurchaseStatusResponse(paramPurchaseStatusResponse, this.mState.toString());
    if ((paramPurchaseStatusResponse.getStatus() == 3) && (paramPurchaseStatusResponse.hasRejectedInstrument()))
    {
      this.mRejectedInstrument = paramPurchaseStatusResponse.getRejectedInstrument();
      setError(new Error(ErrorType.MIN_ADDRESS_PURCHASE_LIMIT_EXCEEDED, -1, 2131165294), null);
    }
    while (true)
    {
      return;
      if (paramPurchaseStatusResponse.getStatus() != 1)
        setError(new Error(ErrorType.PURCHASE_FAILED, paramPurchaseStatusResponse.getStatus(), paramPurchaseStatusResponse.getStatusMsg()), null);
      else
        transitionToState(State.COMPLETED);
    }
  }

  private void pollStatus(String paramString)
  {
    this.mDfeApi.getPurchaseStatus(paramString, new Response.Listener()
    {
      public void onResponse(Buy.PurchaseStatusResponse paramAnonymousPurchaseStatusResponse)
      {
        CheckoutPurchase.this.onPurchaseStatusResponse(paramAnonymousPurchaseStatusResponse);
      }
    }
    , this);
  }

  private void populateFieldsFromPrepareResponse()
  {
    this.mCheckoutInfo = this.mPrepareResponse.getCheckoutInfo();
    List localList = this.mCheckoutInfo.getCheckoutOptionList();
    this.mInstruments.clear();
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = (Buy.BuyResponse.CheckoutInfo.CheckoutOption)localIterator1.next();
      int j = getFopVersionFromOption(localCheckoutOption);
      if (!this.mInstrumentFactory.isRegistered(localCheckoutOption.getInstrumentFamily(), j))
      {
        Object[] arrayOfObject3 = new Object[4];
        arrayOfObject3[0] = localCheckoutOption.getFormOfPayment();
        arrayOfObject3[1] = localCheckoutOption.getInstrumentId();
        arrayOfObject3[2] = Integer.valueOf(localCheckoutOption.getInstrumentFamily());
        arrayOfObject3[3] = Integer.valueOf(j);
        FinskyLog.w("Ignoring instrument [%s,id=%s]. Instrument %d family version %d not supported.", arrayOfObject3);
      }
      else
      {
        Drawable localDrawable = this.mInstrumentFactory.getAddIcon(localCheckoutOption.getInstrumentFamily(), j);
        Instrument localInstrument1 = this.mInstrumentFactory.get(localCheckoutOption.getInstrumentFamily(), j, localCheckoutOption, localDrawable);
        if (localCheckoutOption.getSelectedInstrument())
          this.mDefaultInstrument = localInstrument1;
        this.mInstruments.add(localInstrument1);
      }
    }
    this.mEligibleInstruments.clear();
    Iterator localIterator2 = this.mCheckoutInfo.getEligibleInstrumentList().iterator();
    while (localIterator2.hasNext())
    {
      CommonDevice.Instrument localInstrument = (CommonDevice.Instrument)localIterator2.next();
      int i = BillingUtils.getFopVersion(localInstrument);
      if (!this.mInstrumentFactory.isRegistered(localInstrument.getInstrumentFamily(), i))
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(localInstrument.getInstrumentFamily());
        FinskyLog.w("Ignoring eligible instrument family %d. Not supported.", arrayOfObject2);
      }
      else
      {
        this.mInstrumentFactory.updateStatus(localInstrument.getInstrumentFamily(), i, localInstrument);
        if (TextUtils.isEmpty(this.mInstrumentFactory.getAddText(localInstrument.getInstrumentFamily(), i)))
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(localInstrument.getInstrumentFamily());
          FinskyLog.w("Ignoring eligible instrument family %d. Valid add text not found.", arrayOfObject1);
        }
        else
        {
          this.mEligibleInstruments.add(localInstrument);
        }
      }
    }
    if (this.mInstruments.isEmpty())
    {
      if (!this.mEligibleInstruments.isEmpty())
        break label510;
      this.mInstrumentRequired = false;
    }
    while (true)
    {
      if (this.mPrepareResponse.hasConfirmButtonText())
        this.mConfirmButtonText = this.mPrepareResponse.getConfirmButtonText();
      this.mPreparingRequest = null;
      this.mCheckoutInfo = this.mPrepareResponse.getCheckoutInfo();
      this.mTosList.clear();
      Iterator localIterator3 = this.mPrepareResponse.getTosCheckboxHtmlList().iterator();
      while (localIterator3.hasNext())
      {
        String str = (String)localIterator3.next();
        this.mTosList.add(new Tos(str));
      }
      label510: if (this.mPrepareResponse.hasAddInstrumentPromptHtml())
        this.mAddInstrumentHintText = this.mPrepareResponse.getAddInstrumentPromptHtml();
    }
  }

  private void queueCompletingRequest(Instrument paramInstrument)
  {
    HashMap localHashMap;
    if (paramInstrument != null)
    {
      localHashMap = Maps.newHashMap();
      localHashMap.putAll(Utils.mapFromBundleStrings(this.mCompleteChallengeResponses));
      if (this.mPinAuthenticated)
        localHashMap.put("pcauth", String.valueOf(1));
    }
    label223: 
    while (true)
    {
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = paramInstrument.getCheckoutOption();
      Map localMap = paramInstrument.getCompleteParams();
      if (localMap != null)
        localHashMap.putAll(localMap);
      this.mDfeApi.completePurchase(this.mDoc, localCheckoutOption.getEncodedAdjustedCart(), this.mCheckoutToken, localHashMap, this.mCompletingHasAcceptedTos, localCheckoutOption.getPurchaseCookie(), this.mCompletingRiskHeader, this, this);
      while (true)
      {
        return;
        if (((Long)FinskyPreferences.lastGaiaAuthTimestamp.get(this.mDfeApi.getAccountName()).get()).longValue() + ((Long)G.gaiaAuthValidityMs.get()).longValue() < System.currentTimeMillis())
          break label223;
        localHashMap.put("pcauth", String.valueOf(3));
        break;
        if (isInstrumentRequired())
          FinskyLog.wtf("Instrument is required, but completing request does not have any", new Object[0]);
        this.mDfeApi.completePurchase(this.mDoc, null, this.mCheckoutToken, null, this.mCompletingHasAcceptedTos, this.mPrepareResponse.getPurchaseCookie(), this.mCompletingRiskHeader, this, this);
      }
    }
  }

  private void queuePreparingRequest()
  {
    HashMap localHashMap = Maps.newHashMap();
    Map localMap = this.mInstrumentFactory.getAllPrepareParameters();
    if (localMap != null)
      localHashMap.putAll(localMap);
    localHashMap.putAll(Utils.mapFromBundleStrings(this.mPrepareChallengeResponses));
    if (this.mPreparingCompletingIabParameters != null);
    for (this.mPreparingRequest = this.mDfeApi.makePurchase(this.mDoc, this.mOfferType, this.mCheckoutToken, this.mPreparingCompletingIabParameters.billingApiVersion, this.mPreparingCompletingIabParameters.packageName, this.mPreparingCompletingIabParameters.packageSignatureHash, this.mPreparingCompletingIabParameters.packageVersionCode, this.mPreparingCompletingIabParameters.developerPayload, localHashMap, this, this); ; this.mPreparingRequest = this.mDfeApi.makePurchase(this.mDoc, this.mOfferType, this.mCheckoutToken, localHashMap, this, this))
      return;
  }

  private void retryLastRequest()
  {
    if ((this.mState != State.PREPARING) && (this.mState != State.COMPLETING))
      throw new IllegalStateException("Must be in state PREPARING or COMPLETING for retry.");
    transitionToState(this.mState);
  }

  private void setError(Error paramError, VolleyError paramVolleyError)
  {
    this.mError = paramError;
    this.mVolleyError = paramVolleyError;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramError.type.name();
    arrayOfObject[1] = Integer.valueOf(paramError.code);
    arrayOfObject[2] = paramError.message;
    FinskyLog.e("type=%s, code=%d, message=%s", arrayOfObject);
    transitionToState(State.ERROR);
  }

  private void transitionToState(State paramState)
  {
    checkStateTransition(this.mState, paramState);
    cancelCurrentAction();
    State localState = this.mState;
    this.mState = paramState;
    if (this.mListener != null)
      this.mListener.onStateChange(this, localState, paramState);
    switch (2.$SwitchMap$com$google$android$finsky$billing$CheckoutPurchase$State[paramState.ordinal()])
    {
    case 2:
    case 3:
    case 5:
    default:
    case 1:
    case 4:
    case 6:
    }
    while (true)
    {
      return;
      this.mError = null;
      this.mAddInstrumentHintText = null;
      getAuthTokenAsyncAndPerformRequest();
      continue;
      this.mError = null;
      getAuthTokenAsyncAndPerformRequest();
      continue;
      pollStatus(this.mCompleteResponse.getPurchaseStatusUrl());
    }
  }

  public void answerChallenge(Bundle paramBundle)
  {
    if ((this.mState != State.PREPARE_CHALLENGE_REQUIRED) && (this.mState != State.COMPLETE_CHALLENGE_REQUIRED))
      throw new IllegalStateException();
    if (this.mState == State.PREPARE_CHALLENGE_REQUIRED);
    for (Bundle localBundle = this.mPrepareChallengeResponses; ; localBundle = this.mCompleteChallengeResponses)
    {
      localBundle.putAll(paramBundle);
      return;
    }
  }

  public boolean areAllTossesAccepted()
  {
    Iterator localIterator = this.mTosList.iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (((Tos)localIterator.next()).mIsAccepted);
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  public void attach(Listener paramListener)
  {
    this.mListener = paramListener;
    paramListener.onStateChange(this, getState(), getState());
  }

  public void complete(Instrument paramInstrument, String paramString)
  {
    if ((this.mState != State.PREPARED) && (this.mState != State.COMPLETE_CHALLENGE_REQUIRED))
      throw new IllegalStateException("Cannot complete in current state (" + this.mState + ")");
    checkTosAcceptance();
    this.mCompletingInstrument = paramInstrument;
    if ((this.mTosList.size() > 0) && (areAllTossesAccepted()));
    for (boolean bool = true; ; bool = false)
    {
      this.mCompletingHasAcceptedTos = bool;
      this.mCompletingRiskHeader = paramString;
      transitionToState(State.COMPLETING);
      return;
    }
  }

  public void detach()
  {
    this.mListener = null;
  }

  public String getAddInstrumentHintText()
  {
    return this.mAddInstrumentHintText;
  }

  public ChallengeProtos.Challenge getChallenge()
  {
    if (this.mState == State.PREPARE_CHALLENGE_REQUIRED);
    for (ChallengeProtos.Challenge localChallenge = this.mPrepareResponse.getChallenge(); ; localChallenge = this.mCompleteResponse.getChallenge())
    {
      return localChallenge;
      if (this.mState != State.COMPLETE_CHALLENGE_REQUIRED)
        break;
    }
    throw new IllegalStateException();
  }

  public Buy.BuyResponse.CheckoutInfo getCheckoutInfo()
  {
    checkInState(State.PREPARED);
    return this.mCheckoutInfo;
  }

  public String getConfirmButtonText()
  {
    return this.mConfirmButtonText;
  }

  public Instrument getDefaultInstrument()
  {
    checkInState(State.PREPARED);
    return this.mDefaultInstrument;
  }

  public List<CommonDevice.Instrument> getEligibleInstruments()
  {
    checkInState(State.PREPARED);
    return this.mEligibleInstruments;
  }

  public Error getError()
  {
    checkInState(State.ERROR);
    return this.mError;
  }

  public Bundle getExtraPurchaseData()
  {
    checkInState(State.COMPLETED);
    return this.mExtraPurchaseData;
  }

  public Instrument getInstrument(String paramString)
  {
    Iterator localIterator = this.mInstruments.iterator();
    Instrument localInstrument;
    do
    {
      if (!localIterator.hasNext())
        break;
      localInstrument = (Instrument)localIterator.next();
    }
    while (!localInstrument.getInstrumentId().equals(paramString));
    while (true)
    {
      return localInstrument;
      localInstrument = null;
    }
  }

  public List<Instrument> getInstruments()
  {
    checkInState(State.PREPARED);
    return this.mInstruments;
  }

  public CommonDevice.Instrument getRejectedInstrument()
  {
    checkInState(State.ERROR);
    return this.mRejectedInstrument;
  }

  public State getState()
  {
    return this.mState;
  }

  public List<Tos> getToses()
  {
    checkInState(State.PREPARED);
    return this.mTosList;
  }

  public VolleyError getVolleyError()
  {
    checkInState(State.ERROR);
    return this.mVolleyError;
  }

  public boolean hasAddInstrumentHintText()
  {
    if (this.mAddInstrumentHintText != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasConfirmButtonText()
  {
    if (!TextUtils.isEmpty(this.mConfirmButtonText));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isInstrumentRequired()
  {
    return this.mInstrumentRequired;
  }

  public void onAuthTokenReceived(String paramString)
  {
    this.mCheckoutToken = paramString;
    switch (2.$SwitchMap$com$google$android$finsky$billing$CheckoutPurchase$State[getState().ordinal()])
    {
    case 2:
    case 3:
    default:
      throw new IllegalStateException("Don't know which request to send for state: " + getState());
    case 1:
      queuePreparingRequest();
    case 4:
    }
    while (true)
    {
      return;
      queueCompletingRequest(this.mCompletingInstrument);
    }
  }

  public void onError(AuthFailureError paramAuthFailureError)
  {
    FinskyLog.d("Could not retrieve Checkout auth token: " + paramAuthFailureError.getMessage(), new Object[0]);
    ErrorType localErrorType = ErrorType.UNKNOWN;
    if (this.mState == State.PREPARING);
    for (int i = 2131165278; ; i = 2131165279)
    {
      setError(new Error(localErrorType, -1, i), paramAuthFailureError);
      return;
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(this.mNumAuthRetries);
      arrayOfObject[1] = Integer.valueOf(3);
      FinskyLog.d("Auth failure. Retry [n=%d,max=%d]", arrayOfObject);
      if (((AuthFailureError)paramVolleyError).getResolutionIntent() != null)
        setError(new Error(ErrorType.NETWORK_OR_SERVER, -1, null), paramVolleyError);
    }
    while (true)
    {
      return;
      if (this.mNumAuthRetries < 3)
      {
        this.mNumAuthRetries = (1 + this.mNumAuthRetries);
        retryLastRequest();
        continue;
        FinskyLog.w("%s", new Object[] { paramVolleyError });
        ErrorType localErrorType = ErrorType.UNKNOWN;
        if (((paramVolleyError instanceof ServerError)) || ((paramVolleyError instanceof NetworkError)) || ((paramVolleyError instanceof TimeoutError)))
          localErrorType = ErrorType.NETWORK_OR_SERVER;
        setError(new Error(localErrorType, -1, ErrorStrings.get(FinskyApp.get(), paramVolleyError)), paramVolleyError);
      }
    }
  }

  public void onResponse(Buy.BuyResponse paramBuyResponse)
  {
    this.mNumAuthRetries = 0;
    if (paramBuyResponse.getCheckoutTokenRequired())
      if (this.mIsCheckoutTokenRetry)
      {
        FinskyLog.w("Checkout token still invalid after having sent a fresh one.", new Object[0]);
        setError(new Error(ErrorType.NETWORK_OR_SERVER, -1, 2131165278), null);
      }
    while (true)
    {
      return;
      FinskyLog.d("Checkout token invalid, invalidating and retrying request.", new Object[0]);
      this.mIsCheckoutTokenRetry = true;
      retryLastRequest();
      continue;
      this.mIsCheckoutTokenRetry = false;
      if (this.mState != State.PREPARING)
        break;
      this.mPrepareResponse = paramBuyResponse;
      if (this.mPrepareResponse.hasChallenge())
      {
        transitionToState(State.PREPARE_CHALLENGE_REQUIRED);
      }
      else if (this.mPrepareResponse.hasIabPermissionError())
      {
        setError(new Error(ErrorType.IAB_PERMISSION_ERROR, this.mPrepareResponse.getIabPermissionError(), null), null);
      }
      else if ((this.mPrepareResponse != null) && (this.mPrepareResponse.hasCheckoutInfo()))
      {
        populateFieldsFromPrepareResponse();
        transitionToState(State.PREPARED);
      }
      else
      {
        setError(new Error(ErrorType.NETWORK_OR_SERVER, -1, 2131165278), null);
      }
    }
    if (this.mState == State.COMPLETING)
    {
      this.mCompleteResponse = paramBuyResponse;
      if (this.mCompleteResponse.hasChallenge())
        transitionToState(State.COMPLETE_CHALLENGE_REQUIRED);
      while (true)
      {
        this.mCompletingInstrument = null;
        break;
        if (this.mCompleteResponse.hasPurchaseStatusResponse())
        {
          onPurchaseStatusResponse(this.mCompleteResponse.getPurchaseStatusResponse());
        }
        else if (this.mCompleteResponse.hasPurchaseStatusUrl())
        {
          transitionToState(State.COMPLETING_POLLING_STATUS);
        }
        else
        {
          FinskyLog.w("BuyResponse without purchaseStatusUrl.", new Object[0]);
          setError(new Error(ErrorType.NETWORK_OR_SERVER, -1, 2131165278), null);
        }
      }
    }
    throw new IllegalStateException("Received network response while in state: " + getState());
  }

  public void prepare()
  {
    transitionToState(State.PREPARING);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    State localState = State.valueOf(paramBundle.getString("state"));
    this.mError = ((Error)paramBundle.getParcelable("error"));
    this.mPreparingCompletingIabParameters = ((IabParameters)paramBundle.getParcelable("iab_parameters"));
    if (localState == State.PREPARING)
    {
      this.mState = State.INIT;
      prepare();
    }
    while (true)
    {
      return;
      if (localState == State.COMPLETING)
      {
        setError(new Error(ErrorType.UNKNOWN, -1, FinskyApp.get().getString(2131165280)), null);
      }
      else
      {
        if (paramBundle.containsKey("prepare_response"))
        {
          this.mPrepareResponse = ((Buy.BuyResponse)ParcelableProto.getProtoFromBundle(paramBundle, "prepare_response"));
          populateFieldsFromPrepareResponse();
        }
        if (paramBundle.containsKey("instrument_id"))
        {
          String str = paramBundle.getString("instrument_id");
          this.mCompletingInstrument = getInstrument(str);
          if (this.mCompletingInstrument == null)
            throw new IllegalStateException("Could not find instrument with persisted ID " + str);
        }
        if (paramBundle.containsKey("risk_header"))
          this.mCompletingRiskHeader = paramBundle.getString("risk_header");
        if (paramBundle.containsKey("rejected_instrument"))
          this.mRejectedInstrument = ((CommonDevice.Instrument)((ParcelableProto)paramBundle.getParcelable("rejected_instrument")).getPayload());
        if (paramBundle.containsKey("extra_purchase_data"))
          this.mExtraPurchaseData = paramBundle.getBundle("extra_purchase_data");
        this.mPinAuthenticated = paramBundle.getBoolean("pin_authenticated");
        this.mPrepareChallengeResponses = paramBundle.getBundle("prepare_challenge_responses");
        this.mCompleteChallengeResponses = paramBundle.getBundle("complete_challenge_responses");
        this.mState = localState;
      }
    }
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putString("state", this.mState.name());
    paramBundle.putParcelable("error", this.mError);
    paramBundle.putInt("num_auth_retries", this.mNumAuthRetries);
    paramBundle.putString("checkout_token", this.mCheckoutToken);
    paramBundle.putBoolean("pin_authenticated", this.mPinAuthenticated);
    if (this.mPreparingCompletingIabParameters != null)
      paramBundle.putParcelable("iab_parameters", this.mPreparingCompletingIabParameters);
    if (this.mPrepareResponse != null)
      paramBundle.putParcelable("prepare_response", ParcelableProto.forProto(this.mPrepareResponse));
    if (this.mCompleteResponse != null)
      paramBundle.putParcelable("complete_response", ParcelableProto.forProto(this.mCompleteResponse));
    if (this.mCompletingInstrument != null)
      paramBundle.putString("instrument_id", this.mCompletingInstrument.getInstrumentId());
    if (this.mCompletingRiskHeader != null)
      paramBundle.putString("risk_header", this.mCompletingRiskHeader);
    if (this.mRejectedInstrument != null)
      paramBundle.putParcelable("rejected_instrument", ParcelableProto.forProto(this.mRejectedInstrument));
    if (this.mExtraPurchaseData != null)
      paramBundle.putBundle("extra_purchase_data", this.mExtraPurchaseData);
    paramBundle.putBundle("prepare_challenge_responses", this.mPrepareChallengeResponses);
    paramBundle.putBundle("complete_challenge_responses", this.mCompleteChallengeResponses);
  }

  public void setDevicePinAuthenticated()
  {
    this.mPinAuthenticated = true;
  }

  public void setIabParameters(IabParameters paramIabParameters)
  {
    this.mPreparingCompletingIabParameters = paramIabParameters;
  }

  public static class Error
    implements Parcelable
  {
    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator()
    {
      public CheckoutPurchase.Error createFromParcel(Parcel paramAnonymousParcel)
      {
        String str1 = paramAnonymousParcel.readString();
        int i = paramAnonymousParcel.readInt();
        String str2 = paramAnonymousParcel.readString();
        return new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.valueOf(str1), i, str2);
      }

      public CheckoutPurchase.Error[] newArray(int paramAnonymousInt)
      {
        return new CheckoutPurchase.Error[paramAnonymousInt];
      }
    };
    public final int code;
    public final String message;
    public final CheckoutPurchase.ErrorType type;

    public Error(CheckoutPurchase.ErrorType paramErrorType, int paramInt1, int paramInt2)
    {
      this.type = paramErrorType;
      this.code = paramInt1;
      this.message = FinskyApp.get().getString(paramInt2);
    }

    public Error(CheckoutPurchase.ErrorType paramErrorType, int paramInt, String paramString)
    {
      this.type = paramErrorType;
      this.code = paramInt;
      this.message = paramString;
    }

    public int describeContents()
    {
      return 0;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = false;
      if (paramObject == null);
      while (true)
      {
        return bool;
        if ((paramObject instanceof Error))
        {
          Error localError = (Error)paramObject;
          if ((this.type.equals(localError.type)) && (this.code == localError.code) && ((this.message == localError.message) || ((this.message != null) && (this.message.equals(localError.message)))))
            bool = true;
        }
      }
    }

    public int hashCode()
    {
      return 42;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.type.name();
      arrayOfObject[1] = Integer.valueOf(this.code);
      arrayOfObject[2] = this.message;
      return String.format("Error[type=%s,code=%d,message=%s", arrayOfObject);
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(this.type.name());
      paramParcel.writeInt(this.code);
      paramParcel.writeString(this.message);
    }
  }

  public static enum ErrorType
  {
    static
    {
      IAB_PERMISSION_ERROR = new ErrorType("IAB_PERMISSION_ERROR", 1);
      NETWORK_OR_SERVER = new ErrorType("NETWORK_OR_SERVER", 2);
      MIN_ADDRESS_PURCHASE_LIMIT_EXCEEDED = new ErrorType("MIN_ADDRESS_PURCHASE_LIMIT_EXCEEDED", 3);
      SUBSCRIPTION_ALREADY_OWNED = new ErrorType("SUBSCRIPTION_ALREADY_OWNED", 4);
      DOCUMENT_ALREADY_OWNED = new ErrorType("DOCUMENT_ALREADY_OWNED", 5);
      APP_ALREADY_INSTALLED = new ErrorType("APP_ALREADY_INSTALLED", 6);
      APP_ALREADY_INSTALLED_OTHER_USER = new ErrorType("APP_ALREADY_INSTALLED_OTHER_USER", 7);
      DOCUMENT_UNAVAILABLE = new ErrorType("DOCUMENT_UNAVAILABLE", 8);
      PURCHASE_FAILED = new ErrorType("PURCHASE_FAILED", 9);
      ErrorType[] arrayOfErrorType = new ErrorType[10];
      arrayOfErrorType[0] = UNKNOWN;
      arrayOfErrorType[1] = IAB_PERMISSION_ERROR;
      arrayOfErrorType[2] = NETWORK_OR_SERVER;
      arrayOfErrorType[3] = MIN_ADDRESS_PURCHASE_LIMIT_EXCEEDED;
      arrayOfErrorType[4] = SUBSCRIPTION_ALREADY_OWNED;
      arrayOfErrorType[5] = DOCUMENT_ALREADY_OWNED;
      arrayOfErrorType[6] = APP_ALREADY_INSTALLED;
      arrayOfErrorType[7] = APP_ALREADY_INSTALLED_OTHER_USER;
      arrayOfErrorType[8] = DOCUMENT_UNAVAILABLE;
      arrayOfErrorType[9] = PURCHASE_FAILED;
    }
  }

  public static abstract interface Listener
  {
    public abstract void onStateChange(CheckoutPurchase paramCheckoutPurchase, CheckoutPurchase.State paramState1, CheckoutPurchase.State paramState2);
  }

  public static abstract interface PurchaseStatusListener
  {
    public abstract void onPurchaseStatusResponse(Buy.PurchaseStatusResponse paramPurchaseStatusResponse, String paramString);
  }

  public static enum State
  {
    static
    {
      PREPARE_CHALLENGE_REQUIRED = new State("PREPARE_CHALLENGE_REQUIRED", 2);
      PREPARED = new State("PREPARED", 3);
      COMPLETING = new State("COMPLETING", 4);
      COMPLETE_CHALLENGE_REQUIRED = new State("COMPLETE_CHALLENGE_REQUIRED", 5);
      COMPLETING_POLLING_STATUS = new State("COMPLETING_POLLING_STATUS", 6);
      COMPLETED = new State("COMPLETED", 7);
      ERROR = new State("ERROR", 8);
      State[] arrayOfState = new State[9];
      arrayOfState[0] = INIT;
      arrayOfState[1] = PREPARING;
      arrayOfState[2] = PREPARE_CHALLENGE_REQUIRED;
      arrayOfState[3] = PREPARED;
      arrayOfState[4] = COMPLETING;
      arrayOfState[5] = COMPLETE_CHALLENGE_REQUIRED;
      arrayOfState[6] = COMPLETING_POLLING_STATUS;
      arrayOfState[7] = COMPLETED;
      arrayOfState[8] = ERROR;
    }
  }

  public class Tos
  {
    protected boolean mIsAccepted;
    protected String mTosLink;

    protected Tos(String arg2)
    {
      Object localObject;
      this.mTosLink = localObject;
      this.mIsAccepted = false;
    }

    public boolean getAcceptance()
    {
      return this.mIsAccepted;
    }

    public String getShorthand()
    {
      return this.mTosLink;
    }

    public void setAcceptance(boolean paramBoolean)
    {
      this.mIsAccepted = paramBoolean;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.CheckoutPurchase
 * JD-Core Version:    0.6.2
 */
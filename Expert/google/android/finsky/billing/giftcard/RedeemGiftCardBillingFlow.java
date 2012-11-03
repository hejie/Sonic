package com.google.android.finsky.billing.giftcard;

import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.BuyInstruments.InstrumentSetupInfoResponse;
import com.google.android.finsky.remoting.protos.BuyInstruments.RedeemGiftCardRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.RedeemGiftCardResponse;
import com.google.android.finsky.remoting.protos.CommonDevice.InstrumentSetupInfo;
import com.google.android.finsky.remoting.protos.CommonDevice.Money;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;
import java.util.Iterator;

public class RedeemGiftCardBillingFlow extends InstrumentFlow
  implements RedeemGiftCardFragment.Listener, Response.ErrorListener, Response.Listener<BuyInstruments.RedeemGiftCardResponse>
{
  private BillingAddress.Address mAddress;
  private ArrayList<String> mCheckedCheckboxIds;
  private String mCountry;
  private DfeApi mDfeApi;
  private String mPin;
  private Bundle mPreviousChallengeState;
  private RedeemGiftCardFragment mRedeemGiftCardFragment;
  private State mState;

  public RedeemGiftCardBillingFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, DfeApi paramDfeApi, AsyncAuthenticator paramAsyncAuthenticator, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mDfeApi = paramDfeApi;
  }

  private void fetchInstrumentSetupInfo(String paramString)
  {
    this.mDfeApi.instrumentSetupInfo(100, this.mCountry, paramString, new Response.Listener()
    {
      public void onResponse(BuyInstruments.InstrumentSetupInfoResponse paramAnonymousInstrumentSetupInfoResponse)
      {
        if (paramAnonymousInstrumentSetupInfoResponse.getCheckoutTokenRequired())
          RedeemGiftCardBillingFlow.this.getAuthTokenAndContinue(true);
        while (true)
        {
          return;
          CommonDevice.InstrumentSetupInfo localInstrumentSetupInfo = null;
          if (paramAnonymousInstrumentSetupInfoResponse.getSetupInfoCount() > 0)
            localInstrumentSetupInfo = paramAnonymousInstrumentSetupInfoResponse.getSetupInfo(0);
          if ((localInstrumentSetupInfo != null) && (localInstrumentSetupInfo.getInstrumentFamily() == 100) && (localInstrumentSetupInfo.getSupported()))
          {
            RedeemGiftCardBillingFlow.this.updateFragmentFromSetupInfo(localInstrumentSetupInfo);
            RedeemGiftCardBillingFlow.this.transitionToState(RedeemGiftCardBillingFlow.State.SHOWING_REDEEM_FORM);
          }
          else
          {
            FinskyLog.d("Gift cards not supported.", new Object[0]);
            String str = FinskyApp.get().getString(2131165314);
            RedeemGiftCardBillingFlow.this.mRedeemGiftCardFragment.showError(str, true);
          }
        }
      }
    }
    , this);
  }

  private void redeemGiftCard(String paramString)
  {
    BuyInstruments.RedeemGiftCardRequest localRedeemGiftCardRequest = new BuyInstruments.RedeemGiftCardRequest().setGiftCardPin(this.mPin);
    localRedeemGiftCardRequest.setCheckoutToken(paramString);
    if (this.mAddress != null)
      localRedeemGiftCardRequest.setAddress(this.mAddress);
    if (this.mCheckedCheckboxIds != null)
    {
      Iterator localIterator = this.mCheckedCheckboxIds.iterator();
      while (localIterator.hasNext())
        localRedeemGiftCardRequest.addAcceptedLegalDocumentId((String)localIterator.next());
    }
    this.mDfeApi.redeemGiftCard(localRedeemGiftCardRequest, this, this);
  }

  private void transitionToState(State paramState)
  {
    this.mState = paramState;
    switch (2.$SwitchMap$com$google$android$finsky$billing$giftcard$RedeemGiftCardBillingFlow$State[paramState.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      this.mRedeemGiftCardFragment.switchToLoading();
      getAuthTokenAndContinue(false);
      continue;
      this.mRedeemGiftCardFragment.switchToProgress();
      getAuthTokenAndContinue(false);
      continue;
      this.mRedeemGiftCardFragment.switchToRedeemForm();
      continue;
      this.mRedeemGiftCardFragment.switchToContinueForm();
    }
  }

  private void updateFragmentFromSetupInfo(CommonDevice.InstrumentSetupInfo paramInstrumentSetupInfo)
  {
    if ((paramInstrumentSetupInfo.hasBalance()) && (paramInstrumentSetupInfo.getBalance().hasFormattedAmount()))
    {
      this.mRedeemGiftCardFragment.setBalance(paramInstrumentSetupInfo.getBalance().getFormattedAmount());
      this.mRedeemGiftCardFragment.setFooters(paramInstrumentSetupInfo.getFooterHtmlList());
      if (paramInstrumentSetupInfo.hasAddressChallenge())
      {
        if (this.mPreviousChallengeState == null)
          break label97;
        this.mRedeemGiftCardFragment.showChallenge(paramInstrumentSetupInfo.getAddressChallenge(), this.mPreviousChallengeState);
        this.mPreviousChallengeState = null;
      }
    }
    while (true)
    {
      return;
      this.mRedeemGiftCardFragment.setBalance(null);
      FinskyLog.w("Didn't receive gift card balance.", new Object[0]);
      break;
      label97: this.mRedeemGiftCardFragment.setChallenge(paramInstrumentSetupInfo.getAddressChallenge());
    }
  }

  public void back()
  {
    cancel();
  }

  public boolean canGoBack()
  {
    if (this.mState != State.REDEEMING_GIFT_CARD);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onCancel()
  {
    finish();
  }

  public void onCountrySwitch(String paramString, Bundle paramBundle)
  {
    this.mCountry = paramString;
    this.mPreviousChallengeState = paramBundle;
    transitionToState(State.FETCHING_INSTRUMENT_SETUP_INFO);
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (this.mState == State.FETCHING_INSTRUMENT_SETUP_INFO);
    for (boolean bool = true; ; bool = false)
    {
      String str = ErrorStrings.get(FinskyApp.get(), paramVolleyError);
      this.mRedeemGiftCardFragment.showError(str, bool);
      transitionToState(State.SHOWING_REDEEM_FORM);
      return;
    }
  }

  public void onRedeem(String paramString, BillingAddress.Address paramAddress, ArrayList<String> paramArrayList)
  {
    this.mPin = paramString;
    this.mAddress = paramAddress;
    this.mCheckedCheckboxIds = paramArrayList;
    transitionToState(State.REDEEMING_GIFT_CARD);
  }

  public void onResponse(BuyInstruments.RedeemGiftCardResponse paramRedeemGiftCardResponse)
  {
    if (paramRedeemGiftCardResponse.getCheckoutTokenRequired())
      getAuthTokenAndContinue(true);
    while (true)
    {
      return;
      switch (paramRedeemGiftCardResponse.getResult())
      {
      case 1:
      default:
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramRedeemGiftCardResponse.getResult());
        FinskyLog.d("Received error result code: %d", arrayOfObject);
        if (paramRedeemGiftCardResponse.hasUserMessageHtml())
          this.mRedeemGiftCardFragment.showError(paramRedeemGiftCardResponse.getUserMessageHtml(), false);
        transitionToState(State.SHOWING_REDEEM_FORM);
        break;
      case 0:
        this.mRedeemGiftCardFragment.clearGiftCardCode();
        this.mRedeemGiftCardFragment.setBalance(paramRedeemGiftCardResponse.getBalanceHtml());
        this.mRedeemGiftCardFragment.setUserMessage(paramRedeemGiftCardResponse.getUserMessageHtml());
        this.mRedeemGiftCardFragment.setChallenge(null);
        if (!this.mParameters.getBoolean("entry_point_menu"))
          transitionToState(State.SHOWING_CONTINUE_FORM);
        else
          transitionToState(State.SHOWING_REDEEM_FORM);
        break;
      case 2:
      }
    }
    if (paramRedeemGiftCardResponse.hasAddressChallenge())
      this.mRedeemGiftCardFragment.showChallenge(paramRedeemGiftCardResponse.getAddressChallenge(), null);
    while (true)
    {
      transitionToState(State.SHOWING_REDEEM_FORM);
      break;
      FinskyLog.wtf("INPUT_VALIDATION_ERROR received without challenge.", new Object[0]);
    }
  }

  public void performRequestWithToken(String paramString)
  {
    if (this.mState == State.FETCHING_INSTRUMENT_SETUP_INFO)
      fetchInstrumentSetupInfo(paramString);
    while (true)
    {
      return;
      if (this.mState == State.REDEEMING_GIFT_CARD)
        redeemGiftCard(paramString);
    }
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    this.mRedeemGiftCardFragment = ((RedeemGiftCardFragment)this.mBillingFlowContext.restoreFragment(paramBundle, "fragment"));
    this.mRedeemGiftCardFragment.setListener(this);
    this.mState = State.valueOf(paramBundle.getString("state"));
    this.mCountry = paramBundle.getString("country");
    this.mPreviousChallengeState = paramBundle.getBundle("previous_challenge_state");
    if ((this.mState != State.SHOWING_REDEEM_FORM) && (this.mState != State.SHOWING_CONTINUE_FORM))
      transitionToState(State.FETCHING_INSTRUMENT_SETUP_INFO);
    while (true)
    {
      return;
      transitionToState(this.mState);
    }
  }

  public void saveState(Bundle paramBundle)
  {
    if (this.mState == State.REDEEMING_GIFT_CARD)
      this.mRedeemGiftCardFragment.clearGiftCardCode();
    if (this.mRedeemGiftCardFragment != null)
      this.mBillingFlowContext.persistFragment(paramBundle, "fragment", this.mRedeemGiftCardFragment);
    paramBundle.putString("state", this.mState.toString());
    paramBundle.putString("country", this.mCountry);
    paramBundle.putBundle("previous_challenge_state", this.mPreviousChallengeState);
  }

  public void start()
  {
    this.mRedeemGiftCardFragment = RedeemGiftCardFragment.newInstance(this.mDfeApi.getAccountName(), this.mParameters);
    this.mRedeemGiftCardFragment.setListener(this);
    this.mBillingFlowContext.showFragment(this.mRedeemGiftCardFragment, 2131165309, true);
    transitionToState(State.FETCHING_INSTRUMENT_SETUP_INFO);
  }

  private static enum State
  {
    static
    {
      REDEEMING_GIFT_CARD = new State("REDEEMING_GIFT_CARD", 2);
      SHOWING_CONTINUE_FORM = new State("SHOWING_CONTINUE_FORM", 3);
      State[] arrayOfState = new State[4];
      arrayOfState[0] = FETCHING_INSTRUMENT_SETUP_INFO;
      arrayOfState[1] = SHOWING_REDEEM_FORM;
      arrayOfState[2] = REDEEMING_GIFT_CARD;
      arrayOfState[3] = SHOWING_CONTINUE_FORM;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.giftcard.RedeemGiftCardBillingFlow
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing;

import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;

public class UpdateAddressFlow extends InstrumentFlow
  implements UpdateAddressFragment.UpdateAddressResultListener, Response.Listener<BuyInstruments.UpdateInstrumentResponse>, Response.ErrorListener
{
  private final Analytics mAnalytics;
  private final BillingFlowContext mContext;
  private final DfeApi mDfeApi;
  private String mHeaderText;
  private CommonDevice.Instrument mInstrument;
  private String mInstrumentDisplayName;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private CommonDevice.Instrument mRejectedInstrument;
  private State mState = State.INIT;
  private UpdateAddressFragment mUpdateAddressFragment;
  private BuyInstruments.UpdateInstrumentResponse mUpdateAddressResponse;

  public UpdateAddressFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mDfeApi = paramDfeApi;
    this.mAnalytics = paramAnalytics;
    if (paramBundle != null)
    {
      this.mReferrerUrl = paramBundle.getString("referrer_url");
      this.mReferrerListCookie = paramBundle.getString("referrer_list_cookie");
      this.mHeaderText = paramBundle.getString("update_address_header");
      Bundle localBundle = paramBundle.getBundle("extra_paramters");
      this.mRejectedInstrument = ((CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(localBundle, "rejected_instrument"));
      this.mInstrumentDisplayName = localBundle.getString("instrument_display_name");
    }
  }

  private void hideProgress()
  {
    if (this.mUpdateAddressFragment != null)
      this.mUpdateAddressFragment.enableUi(true);
    this.mContext.hideProgress();
  }

  private boolean isRetryableError(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    if (paramUpdateInstrumentResponse.getResult() == 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isSuccess(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    if (paramUpdateInstrumentResponse.getResult() == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void log(String paramString)
  {
    this.mAnalytics.logPageView(this.mReferrerUrl, this.mReferrerListCookie, paramString);
  }

  private void showError(String paramString)
  {
    this.mState = State.SHOWING_FORM;
    hideProgress();
    if (this.mUpdateAddressFragment.getFragmentManager() == null)
      FinskyLog.w("No fragment manager, swallowing error: %s", new Object[] { paramString });
    while (true)
    {
      return;
      ErrorDialog.show(this.mUpdateAddressFragment.getFragmentManager(), null, paramString, false);
    }
  }

  private void showFormErrors(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    this.mUpdateAddressFragment.displayErrors(paramUpdateInstrumentResponse.getErrorInputFieldList());
  }

  private void showProgress()
  {
    if (this.mUpdateAddressFragment != null)
      this.mUpdateAddressFragment.enableUi(false);
    this.mContext.showProgress(2131165259);
  }

  public void back()
  {
    if (this.mState != State.SHOWING_FORM)
      throw new IllegalStateException();
    cancel();
  }

  public boolean canGoBack()
  {
    if (this.mState == State.SHOWING_FORM);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void cancel()
  {
    log("updateAddressCancel");
    super.cancel();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    FinskyLog.w("Error received: %s", new Object[] { paramVolleyError });
    showError(ErrorStrings.get(FinskyApp.get(), paramVolleyError));
  }

  public void onInitialized()
  {
    this.mBillingFlowContext.hideProgress();
  }

  public void onInitializing()
  {
    this.mBillingFlowContext.showProgress(2131165184);
  }

  public void onResponse(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    this.mUpdateAddressResponse = paramUpdateInstrumentResponse;
    performNext();
  }

  public void onUpdateAddressResult(UpdateAddressFragment.UpdateAddressResultListener.Result paramResult, CommonDevice.Instrument paramInstrument)
  {
    if (paramResult == UpdateAddressFragment.UpdateAddressResultListener.Result.SUCCESS)
    {
      this.mInstrument = paramInstrument;
      performNext();
    }
    while (true)
    {
      return;
      if (paramResult == UpdateAddressFragment.UpdateAddressResultListener.Result.CANCELED)
      {
        if (FinskyLog.DEBUG)
          FinskyLog.v("Update address canceled.", new Object[0]);
        cancel();
      }
      else if (paramResult == UpdateAddressFragment.UpdateAddressResultListener.Result.FAILURE)
      {
        showError(FinskyApp.get().getString(2131165299));
      }
    }
  }

  protected void performNext()
  {
    if (this.mState == State.INIT)
    {
      this.mState = State.SHOWING_FORM;
      this.mUpdateAddressFragment = UpdateAddressFragment.newInstance(this.mDfeApi.getAccountName(), null, this.mRejectedInstrument, this.mInstrumentDisplayName, this.mHeaderText);
      this.mUpdateAddressFragment.setOnResultListener(this);
      this.mContext.showFragment(this.mUpdateAddressFragment, 2131165295, true);
      log("updateAddress");
    }
    while (true)
    {
      return;
      if (this.mState == State.SHOWING_FORM)
      {
        this.mState = State.SENDING_REQUEST;
        showProgress();
        getAuthTokenAndContinue(false);
      }
      else if (this.mState == State.SENDING_REQUEST)
      {
        hideProgress();
        if (this.mUpdateAddressResponse == null)
        {
          FinskyLog.e("Null response to an update address request", new Object[0]);
          showError(FinskyApp.get().getString(2131165299));
          log("updateAddressError");
        }
        else if (isSuccess(this.mUpdateAddressResponse))
        {
          this.mState = State.DONE;
          log("updateAddressSuccess");
          finishWithUpdateInstrumentResponse(this.mUpdateAddressResponse);
        }
        else if (this.mUpdateAddressResponse.getCheckoutTokenRequired())
        {
          getAuthTokenAndContinue(true);
        }
        else
        {
          if (!isRetryableError(this.mUpdateAddressResponse))
            break;
          this.mState = State.SHOWING_FORM;
          showFormErrors(this.mUpdateAddressResponse);
          log("updateAddressError");
        }
      }
    }
    if (this.mUpdateAddressResponse.hasUserMessageHtml())
      showError(this.mUpdateAddressResponse.getUserMessageHtml());
    while (true)
    {
      log("updateAddressError");
      break;
      showError(FinskyApp.get().getString(2131165299));
    }
  }

  public void performRequestWithToken(String paramString)
  {
    BuyInstruments.UpdateInstrumentRequest localUpdateInstrumentRequest = new BuyInstruments.UpdateInstrumentRequest();
    localUpdateInstrumentRequest.setInstrument(this.mInstrument);
    this.mDfeApi.updateInstrument(localUpdateInstrumentRequest, paramString, this, this);
  }

  public void resumeFromSavedState(Bundle paramBundle)
  {
    super.resumeFromSavedState(paramBundle);
    if (this.mState != State.INIT)
      throw new IllegalStateException();
    this.mState = State.valueOf(paramBundle.getString("state"));
    this.mHeaderText = paramBundle.getString("update_address_header");
    if (this.mState != State.SHOWING_FORM)
    {
      hideProgress();
      finish();
    }
    if (paramBundle.containsKey("update_address_fragment"))
    {
      this.mUpdateAddressFragment = ((UpdateAddressFragment)this.mContext.restoreFragment(paramBundle, "update_address_fragment"));
      this.mUpdateAddressFragment.setOnResultListener(this);
    }
    this.mInstrumentDisplayName = paramBundle.getString("instrument_display_name");
    this.mRejectedInstrument = ((CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(paramBundle, "rejected_instrument"));
  }

  public void saveState(Bundle paramBundle)
  {
    super.saveState(paramBundle);
    paramBundle.putString("state", this.mState.name());
    paramBundle.putParcelable("rejected_instrument", ParcelableProto.forProto(this.mRejectedInstrument));
    paramBundle.putString("instrument_display_name", this.mInstrumentDisplayName);
    paramBundle.putString("update_address_header", this.mHeaderText);
    if (this.mUpdateAddressFragment != null)
      this.mContext.persistFragment(paramBundle, "update_address_fragment", this.mUpdateAddressFragment);
  }

  public void start()
  {
    performNext();
  }

  private static enum State
  {
    static
    {
      SENDING_REQUEST = new State("SENDING_REQUEST", 2);
      DONE = new State("DONE", 3);
      State[] arrayOfState = new State[4];
      arrayOfState[0] = INIT;
      arrayOfState[1] = SHOWING_FORM;
      arrayOfState[2] = SENDING_REQUEST;
      arrayOfState[3] = DONE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.UpdateAddressFlow
 * JD-Core Version:    0.6.2
 */
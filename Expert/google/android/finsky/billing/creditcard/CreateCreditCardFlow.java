package com.google.android.finsky.billing.creditcard;

import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.InstrumentFlow;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentRequest;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.CommonDevice.CreditCardInstrument;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Random;

public class CreateCreditCardFlow extends InstrumentFlow
  implements AddCreditCardFragment.AddCreditCardResultListener, Response.Listener<BuyInstruments.UpdateInstrumentResponse>, Response.ErrorListener
{
  private AddCreditCardFragment mAddCreditCardFragment;
  private BuyInstruments.UpdateInstrumentResponse mAddCreditCardResponse;
  private final Analytics mAnalytics;
  private final BillingFlowContext mContext;
  private String mCreditCardNumber;
  private String mCvc;
  private final DfeApi mDfeApi;
  private CommonDevice.Instrument mInstrument;
  private BillingUtils.CreateInstrumentUiMode mMode = BillingUtils.CreateInstrumentUiMode.INTERNAL;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private State mState = State.INIT;

  public CreateCreditCardFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, DfeApi paramDfeApi, Analytics paramAnalytics, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramAsyncAuthenticator, paramBundle);
    this.mContext = paramBillingFlowContext;
    this.mDfeApi = paramDfeApi;
    this.mAnalytics = paramAnalytics;
    if (paramBundle != null)
    {
      if (paramBundle.containsKey("ui_mode"))
        this.mMode = BillingUtils.CreateInstrumentUiMode.valueOf(paramBundle.getString("ui_mode"));
      this.mReferrerUrl = paramBundle.getString("referrer_url");
      this.mReferrerListCookie = paramBundle.getString("referrer_list_cookie");
    }
  }

  private static boolean allCorporaEnabled()
  {
    DfeToc localDfeToc = FinskyApp.get().getToc();
    if ((localDfeToc != null) && (localDfeToc.getCorpus(2) != null));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void hideProgress()
  {
    if (this.mAddCreditCardFragment != null)
      this.mAddCreditCardFragment.enableUi(true);
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

  private void logError(String paramString)
  {
    log("addCreditCardError?error=" + paramString);
  }

  private void queueEscrowCredentialsRequest()
  {
    EscrowRequest localEscrowRequest = new EscrowRequest(Math.abs(0xFFFFFFFE & new Random(System.currentTimeMillis() ^ ((Long)DfeApiConfig.androidId.get()).longValue()).nextInt()), this.mCreditCardNumber, this.mCvc, new EscrowResponseListener(null), new EscrowErrorListener(null));
    FinskyApp.get().getRequestQueue().add(localEscrowRequest);
    this.mCreditCardNumber = null;
    this.mCvc = null;
  }

  private void showError(String paramString)
  {
    this.mState = State.SHOWING_FORM;
    hideProgress();
    if (this.mAddCreditCardFragment.getFragmentManager() == null)
      FinskyLog.w("No fragment manager, swallowing error: %s", new Object[] { paramString });
    while (true)
    {
      return;
      ErrorDialog.show(this.mAddCreditCardFragment.getFragmentManager(), null, paramString, false);
    }
  }

  private void showErrorWithChoice(String paramString)
  {
    this.mState = State.SHOWING_FORM;
    hideProgress();
    if (this.mAddCreditCardFragment.getFragmentManager() == null)
      FinskyLog.w("No fragment manager, swallowing error: %s", new Object[] { paramString });
    while (true)
    {
      return;
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(paramString, 2131165405, 2131165404);
      localSimpleAlertDialog.setCallback(this.mAddCreditCardFragment, 2, null);
      localSimpleAlertDialog.show(this.mAddCreditCardFragment.getFragmentManager(), "error_with_choice");
    }
  }

  private void showFormErrors(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    this.mAddCreditCardFragment.displayErrors(paramUpdateInstrumentResponse.getErrorInputFieldList());
  }

  private void showProgress()
  {
    if (this.mAddCreditCardFragment != null)
      this.mAddCreditCardFragment.enableUi(false);
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
    log("addCreditCardCancel");
    super.cancel();
  }

  public void onAddCreditCardResult(AddCreditCardFragment.AddCreditCardResultListener.Result paramResult, String paramString1, String paramString2, CommonDevice.Instrument paramInstrument)
  {
    if (paramResult == AddCreditCardFragment.AddCreditCardResultListener.Result.SUCCESS)
    {
      this.mCreditCardNumber = paramString1;
      this.mCvc = paramString2;
      this.mInstrument = paramInstrument;
      log("addCreditCardConfirm");
      performNext();
    }
    while (true)
    {
      return;
      if (paramResult == AddCreditCardFragment.AddCreditCardResultListener.Result.CANCELED)
      {
        if (FinskyLog.DEBUG)
          FinskyLog.v("Add credit card canceled.", new Object[0]);
        cancel();
      }
      else if (paramResult == AddCreditCardFragment.AddCreditCardResultListener.Result.FAILURE)
      {
        logError("UNKNOWN");
        showError(FinskyApp.get().getString(2131165281));
      }
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    FinskyLog.w("Error received: %s", new Object[] { paramVolleyError });
    logError(DfeUtils.getLegacyErrorCode(paramVolleyError));
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
    this.mAddCreditCardResponse = paramUpdateInstrumentResponse;
    performNext();
  }

  protected void performNext()
  {
    if (this.mState == State.INIT)
    {
      this.mState = State.SHOWING_FORM;
      String str = this.mParameters.getString("cardholder_name");
      this.mAddCreditCardFragment = AddCreditCardFragment.newInstance(this.mMode, this.mDfeApi.getAccountName(), str, allCorporaEnabled());
      this.mAddCreditCardFragment.setOnResultListener(this);
      this.mContext.showFragment(this.mAddCreditCardFragment, 2131165247, true);
    }
    while (true)
    {
      return;
      if (this.mState == State.SHOWING_FORM)
      {
        this.mState = State.ESCROWING_CREDENTIALS;
        showProgress();
        queueEscrowCredentialsRequest();
      }
      else if (this.mState == State.ESCROWING_CREDENTIALS)
      {
        this.mState = State.SENDING_REQUEST;
        getAuthTokenAndContinue(false);
      }
      else if (this.mState == State.SENDING_REQUEST)
      {
        hideProgress();
        if (this.mAddCreditCardResponse == null)
        {
          FinskyLog.e("AddCreditCard Response is null.", new Object[0]);
          logError("UNKNOWN");
          showError(FinskyApp.get().getString(2131165281));
        }
        else if (isSuccess(this.mAddCreditCardResponse))
        {
          log("addCreditCardSuccess");
          this.mState = State.DONE;
          finishWithUpdateInstrumentResponse(this.mAddCreditCardResponse);
        }
        else if (this.mAddCreditCardResponse.getCheckoutTokenRequired())
        {
          getAuthTokenAndContinue(true);
        }
        else if (isRetryableError(this.mAddCreditCardResponse))
        {
          logError("INVALID_INPUT");
          this.mState = State.SHOWING_FORM;
          showFormErrors(this.mAddCreditCardResponse);
        }
        else
        {
          logError("UNKNOWN");
          if (this.mAddCreditCardResponse.hasUserMessageHtml())
            showErrorWithChoice(this.mAddCreditCardResponse.getUserMessageHtml());
          else
            showError(FinskyApp.get().getString(2131165281));
        }
      }
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
    if (this.mState != State.SHOWING_FORM)
    {
      hideProgress();
      finish();
    }
    if (paramBundle.containsKey("fragment"))
    {
      this.mAddCreditCardFragment = ((AddCreditCardFragment)this.mContext.restoreFragment(paramBundle, "fragment"));
      this.mAddCreditCardFragment.setOnResultListener(this);
    }
  }

  public void saveState(Bundle paramBundle)
  {
    super.saveState(paramBundle);
    paramBundle.putString("state", this.mState.name());
    if (this.mAddCreditCardFragment != null)
      this.mContext.persistFragment(paramBundle, "fragment", this.mAddCreditCardFragment);
  }

  public void start()
  {
    log("addCreditCard");
    performNext();
  }

  private class EscrowErrorListener
    implements Response.ErrorListener
  {
    private EscrowErrorListener()
    {
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      FinskyLog.w("Error received: %s", new Object[] { paramVolleyError });
      CreateCreditCardFlow.this.logError("ESCROW." + DfeUtils.getLegacyErrorCode(paramVolleyError));
      CreateCreditCardFlow.this.showError(ErrorStrings.get(FinskyApp.get(), paramVolleyError));
    }
  }

  private class EscrowResponseListener
    implements Response.Listener<String>
  {
    private EscrowResponseListener()
    {
    }

    public void onResponse(String paramString)
    {
      CreateCreditCardFlow.this.mInstrument.getCreditCard().setEscrowHandle(paramString);
      CreateCreditCardFlow.this.performNext();
    }
  }

  private static enum State
  {
    static
    {
      ESCROWING_CREDENTIALS = new State("ESCROWING_CREDENTIALS", 2);
      SENDING_REQUEST = new State("SENDING_REQUEST", 3);
      DONE = new State("DONE", 4);
      State[] arrayOfState = new State[5];
      arrayOfState[0] = INIT;
      arrayOfState[1] = SHOWING_FORM;
      arrayOfState[2] = ESCROWING_CREDENTIALS;
      arrayOfState[3] = SENDING_REQUEST;
      arrayOfState[4] = DONE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.CreateCreditCardFlow
 * JD-Core Version:    0.6.2
 */
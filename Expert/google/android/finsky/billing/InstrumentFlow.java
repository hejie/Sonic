package com.google.android.finsky.billing;

import android.os.Bundle;
import com.android.volley.AuthFailureError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.remoting.protos.BuyInstruments.UpdateInstrumentResponse;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.RedeemedPromoOffer;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;

public abstract class InstrumentFlow extends BillingFlow
  implements AsyncAuthenticator.Listener
{
  private final AsyncAuthenticator mAsyncAuthenticator;
  private String mCheckoutToken;
  private boolean mHasRetried;

  public InstrumentFlow(BillingFlowContext paramBillingFlowContext, BillingFlowListener paramBillingFlowListener, AsyncAuthenticator paramAsyncAuthenticator, Bundle paramBundle)
  {
    super(paramBillingFlowContext, paramBillingFlowListener, paramBundle);
    this.mAsyncAuthenticator = paramAsyncAuthenticator;
  }

  protected void finishWithUpdateInstrumentResponse(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
  {
    Bundle localBundle = null;
    if (paramUpdateInstrumentResponse.hasRedeemedOffer())
    {
      localBundle = new Bundle();
      localBundle.putString("redeemed_offer_message_html", paramUpdateInstrumentResponse.getRedeemedOffer().getDescriptionHtml());
    }
    finish(localBundle);
  }

  protected void getAuthTokenAndContinue(boolean paramBoolean)
  {
    if ((this.mHasRetried) && (paramBoolean))
    {
      FinskyLog.w("Invalid token after retry, giving up.", new Object[0]);
      fail(FinskyApp.get().getString(2131165443));
      return;
    }
    if (paramBoolean)
      FinskyLog.d("Invalid token, fetching fresh one.", new Object[0]);
    this.mHasRetried = paramBoolean;
    AsyncAuthenticator localAsyncAuthenticator = this.mAsyncAuthenticator;
    if (paramBoolean);
    for (String str = this.mCheckoutToken; ; str = null)
    {
      localAsyncAuthenticator.getToken(this, str);
      break;
    }
  }

  public void onAuthTokenReceived(String paramString)
  {
    this.mCheckoutToken = paramString;
    performRequestWithToken(paramString);
  }

  public void onError(AuthFailureError paramAuthFailureError)
  {
    fail(ErrorStrings.get(FinskyApp.get(), paramAuthFailureError));
  }

  public abstract void performRequestWithToken(String paramString);

  public void resumeFromSavedState(Bundle paramBundle)
  {
    this.mHasRetried = paramBundle.getBoolean("InstrumentFlow.is_retry");
    this.mCheckoutToken = paramBundle.getString("InstrumentFlow.token");
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putBoolean("InstrumentFlow.is_retry", this.mHasRetried);
    paramBundle.putString("InstrumentFlow.token", this.mCheckoutToken);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.InstrumentFlow
 * JD-Core Version:    0.6.2
 */
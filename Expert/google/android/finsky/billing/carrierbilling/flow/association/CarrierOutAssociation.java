package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.os.Bundle;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.remoting.protos.CarrierBilling.InitiateAssociationResponse;
import com.google.android.finsky.remoting.protos.CarrierBilling.VerifyAssociationResponse;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierOutAssociation
  implements AssociationAction, SmsSender.SmsSendListener, Response.ErrorListener, Response.Listener<CarrierBilling.InitiateAssociationResponse>
{
  private final DfeApi mDfeApi;
  private Response.ErrorListener mErrorListener;
  private Response.Listener<CarrierBilling.VerifyAssociationResponse> mListener;
  private String mSmsAddress;
  private String mSmsPrefix;
  private State mState = State.INITIATING_ASSOCIATION;
  private final CarrierBillingStorage mStorage;

  CarrierOutAssociation(DfeApi paramDfeApi, CarrierBillingStorage paramCarrierBillingStorage, String paramString1, String paramString2)
  {
    this.mStorage = paramCarrierBillingStorage;
    this.mDfeApi = paramDfeApi;
    this.mSmsAddress = paramString1;
    this.mSmsPrefix = paramString2;
  }

  public CarrierOutAssociation(DfeApi paramDfeApi, String paramString1, String paramString2)
  {
    this(paramDfeApi, BillingLocator.getCarrierBillingStorage(), paramString1, paramString2);
  }

  private void dispatch(CarrierBilling.VerifyAssociationResponse paramVerifyAssociationResponse)
  {
    if (this.mListener != null)
      this.mListener.onResponse(paramVerifyAssociationResponse);
  }

  private void dispatchError()
  {
    dispatch(new CarrierBilling.VerifyAssociationResponse().setStatus(4));
  }

  private String formattedTextToSend(String paramString)
  {
    String str = this.mSmsPrefix + ":";
    return str + paramString;
  }

  private void initiateAssociation()
  {
    this.mState = State.INITIATING_ASSOCIATION;
    this.mDfeApi.initiateAssociation(this.mStorage.getCurrentSimIdentifier(), this, this);
  }

  private void sendGutToCarrier(String paramString)
  {
    String str1 = this.mSmsAddress;
    if (TextUtils.isEmpty(str1))
    {
      FinskyLog.w("Address to send SMS is unavailable", new Object[0]);
      dispatchError();
    }
    while (true)
    {
      return;
      String str2 = formattedTextToSend(paramString);
      SmsSender.send(FinskyApp.get(), str1, null, str2, this);
      verifyAssociation();
    }
  }

  private void verifyAssociation()
  {
    this.mState = State.VERIFYING_ASSOCIATION;
    this.mDfeApi.verifyAssociation(this.mStorage.getCurrentSimIdentifier(), new Response.Listener()
    {
      public void onResponse(CarrierBilling.VerifyAssociationResponse paramAnonymousVerifyAssociationResponse)
      {
        CarrierOutAssociation.this.dispatch(paramAnonymousVerifyAssociationResponse);
      }
    }
    , this);
  }

  public void cancel()
  {
    this.mListener = null;
    this.mErrorListener = null;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    FinskyLog.w("Error while receiving Volley response in state " + this.mState.name(), new Object[0]);
    if (this.mErrorListener != null)
      this.mErrorListener.onErrorResponse(paramVolleyError);
  }

  public void onResponse(CarrierBilling.InitiateAssociationResponse paramInitiateAssociationResponse)
  {
    if (this.mListener == null);
    while (true)
    {
      return;
      String str = paramInitiateAssociationResponse.getUserToken();
      if (!TextUtils.isEmpty(str))
      {
        sendGutToCarrier(str);
      }
      else
      {
        FinskyLog.w("Invalid Google User Token received.", new Object[0]);
        dispatchError();
      }
    }
  }

  public void onResult(SmsSender.SmsSendListener.SmsSenderResult paramSmsSenderResult)
  {
    if (paramSmsSenderResult == SmsSender.SmsSendListener.SmsSenderResult.RESULT_ERROR)
    {
      FinskyLog.w("Sending Sms Failed", new Object[0]);
      dispatchError();
      cancel();
    }
  }

  public void resumeState(Bundle paramBundle, Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener)
  {
    this.mState = State.valueOf(paramBundle.getString("association_state"));
    this.mListener = paramListener;
    this.mErrorListener = paramErrorListener;
    if (this.mState == State.VERIFYING_ASSOCIATION)
      verifyAssociation();
    while (true)
    {
      return;
      initiateAssociation();
    }
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putString("association_state", this.mState.name());
    cancel();
  }

  public void setListener(Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener)
  {
    this.mListener = paramListener;
    this.mErrorListener = paramErrorListener;
  }

  public void start(Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener)
  {
    this.mListener = paramListener;
    this.mErrorListener = paramErrorListener;
    initiateAssociation();
  }

  private static enum State
  {
    static
    {
      State[] arrayOfState = new State[2];
      arrayOfState[0] = INITIATING_ASSOCIATION;
      arrayOfState[1] = VERIFYING_ASSOCIATION;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.association.CarrierOutAssociation
 * JD-Core Version:    0.6.2
 */
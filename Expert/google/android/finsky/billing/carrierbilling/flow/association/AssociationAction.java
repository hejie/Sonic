package com.google.android.finsky.billing.carrierbilling.flow.association;

import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.remoting.protos.CarrierBilling.VerifyAssociationResponse;

public abstract interface AssociationAction
{
  public abstract void cancel();

  public abstract void resumeState(Bundle paramBundle, Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener);

  public abstract void saveState(Bundle paramBundle);

  public abstract void setListener(Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener);

  public abstract void start(Response.Listener<CarrierBilling.VerifyAssociationResponse> paramListener, Response.ErrorListener paramErrorListener);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.association.AssociationAction
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Rev.ReviewResponse;

public class DfeRateReview extends DfeModel
  implements Response.Listener<Rev.ReviewResponse>
{
  private boolean mResponseRecieved;

  public DfeRateReview(DfeApi paramDfeApi, String paramString1, String paramString2, int paramInt)
  {
    paramDfeApi.rateReview(paramString1, paramString2, paramInt, this, this);
  }

  public boolean isReady()
  {
    return this.mResponseRecieved;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    this.mResponseRecieved = true;
    super.onErrorResponse(paramVolleyError);
    unregisterAll();
  }

  public void onResponse(Rev.ReviewResponse paramReviewResponse)
  {
    this.mResponseRecieved = true;
    notifyDataSetChanged();
    unregisterAll();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeRateReview
 * JD-Core Version:    0.6.2
 */
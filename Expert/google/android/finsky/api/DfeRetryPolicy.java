package com.google.android.finsky.api;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.google.android.finsky.config.GservicesValue;

public class DfeRetryPolicy extends DefaultRetryPolicy
{
  private final DfeApiContext mDfeApiContext;
  private boolean mHadAuthException;

  public DfeRetryPolicy(int paramInt1, int paramInt2, float paramFloat, DfeApiContext paramDfeApiContext)
  {
    super(paramInt1, paramInt2, paramFloat);
    this.mDfeApiContext = paramDfeApiContext;
  }

  public DfeRetryPolicy(DfeApiContext paramDfeApiContext)
  {
    super(((Integer)DfeApiConfig.dfeRequestTimeoutMs.get()).intValue(), ((Integer)DfeApiConfig.dfeMaxRetries.get()).intValue(), ((Float)DfeApiConfig.dfeBackoffMultipler.get()).floatValue());
    this.mDfeApiContext = paramDfeApiContext;
  }

  public void retry(VolleyError paramVolleyError)
    throws VolleyError
  {
    if ((paramVolleyError instanceof AuthFailureError))
    {
      if (this.mHadAuthException)
        throw paramVolleyError;
      this.mHadAuthException = true;
      this.mDfeApiContext.invalidateAuthToken();
    }
    super.retry(paramVolleyError);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeRetryPolicy
 * JD-Core Version:    0.6.2
 */
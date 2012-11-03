package com.google.android.finsky.api;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;

public class DfeClearCacheRequest extends Request<Object>
{
  private final Cache mCache;
  private final String mCacheKey;
  private final Runnable mCallback;
  private final boolean mFullExpire;

  public DfeClearCacheRequest(Cache paramCache, String paramString, boolean paramBoolean, Runnable paramRunnable)
  {
    super(null, null);
    this.mCache = paramCache;
    this.mCacheKey = paramString;
    this.mFullExpire = paramBoolean;
    this.mCallback = paramRunnable;
  }

  protected void deliverResponse(Object paramObject)
  {
  }

  public Request.Priority getPriority()
  {
    return Request.Priority.IMMEDIATE;
  }

  public boolean isCanceled()
  {
    this.mCache.invalidate(this.mCacheKey, this.mFullExpire);
    if (this.mCallback != null)
      new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
    return true;
  }

  protected Response<Object> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    return null;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeClearCacheRequest
 * JD-Core Version:    0.6.2
 */
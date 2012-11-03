package com.google.android.finsky.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BitmapLoader
{
  private static int MIN_CACHE_SIZE_BYTES = 3145728;
  private final HashMap<String, RequestListenerWrapper> mBatchedResponses = Maps.newHashMap();
  private final BitmapLruCache mCachedRemoteImages;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private final HashMap<String, RequestListenerWrapper> mInFlightRequests = Maps.newHashMap();
  private final RequestQueue mRequestQueue;
  private Runnable mRunnable;

  public BitmapLoader(RequestQueue paramRequestQueue, int paramInt1, int paramInt2)
  {
    this.mRequestQueue = paramRequestQueue;
    int i = ((Integer)G.bitmapLoaderCacheSizeOverrideMb.get()).intValue();
    if (i == -1);
    for (int j = Math.max(MIN_CACHE_SIZE_BYTES, (int)(4 * (paramInt1 * paramInt2) * ((Float)G.bitmapLoaderCacheSizeRatioToScreen.get()).floatValue())); ; j = 1024 * (i * 1024))
    {
      this.mCachedRemoteImages = new BitmapLruCache(j);
      return;
    }
  }

  private void batchResponse(String paramString, RequestListenerWrapper paramRequestListenerWrapper)
  {
    this.mBatchedResponses.put(paramString, paramRequestListenerWrapper);
    if (this.mRunnable == null)
    {
      this.mRunnable = new Runnable()
      {
        public void run()
        {
          Iterator localIterator1 = BitmapLoader.this.mBatchedResponses.values().iterator();
          while (localIterator1.hasNext())
          {
            BitmapLoader.RequestListenerWrapper localRequestListenerWrapper = (BitmapLoader.RequestListenerWrapper)localIterator1.next();
            Iterator localIterator2 = BitmapLoader.RequestListenerWrapper.access$600(localRequestListenerWrapper).iterator();
            while (localIterator2.hasNext())
            {
              BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)localIterator2.next();
              BitmapLoader.BitmapContainer.access$702(localBitmapContainer, BitmapLoader.RequestListenerWrapper.access$300(localRequestListenerWrapper));
              BitmapLoader.BitmapContainer.access$800(localBitmapContainer).onResponse(localBitmapContainer);
            }
          }
          BitmapLoader.this.mBatchedResponses.clear();
          BitmapLoader.access$902(BitmapLoader.this, null);
        }
      };
      this.mHandler.postDelayed(this.mRunnable, 100L);
    }
  }

  private BitmapContainer get(String paramString1, boolean paramBoolean, String paramString2, Bitmap paramBitmap, RemoteRequestCreator paramRemoteRequestCreator, BitmapLoadedHandler paramBitmapLoadedHandler)
  {
    Object localObject;
    if ((!paramBoolean) && (TextUtils.isEmpty(paramString1)))
      localObject = new BitmapContainer(paramBitmap, null, null, null);
    while (true)
    {
      return localObject;
      Bitmap localBitmap = (Bitmap)this.mCachedRemoteImages.get(paramString2);
      if (localBitmap != null)
      {
        localObject = new BitmapContainer(localBitmap, paramString1, null, null);
      }
      else
      {
        BitmapContainer localBitmapContainer = new BitmapContainer(paramBitmap, paramString1, paramString2, paramBitmapLoadedHandler);
        RequestListenerWrapper localRequestListenerWrapper = (RequestListenerWrapper)this.mInFlightRequests.get(paramString2);
        if (localRequestListenerWrapper != null)
        {
          localRequestListenerWrapper.addHandler(localBitmapContainer);
          localObject = localBitmapContainer;
        }
        else
        {
          Request localRequest = paramRemoteRequestCreator.create();
          this.mRequestQueue.add(localRequest);
          this.mInFlightRequests.put(paramString2, new RequestListenerWrapper(localRequest, localBitmapContainer));
          localObject = localBitmapContainer;
        }
      }
    }
  }

  private static String getCacheKey(String paramString, int paramInt1, int paramInt2)
  {
    return 256 + "#W" + paramInt1 + "#H" + paramInt2 + paramString;
  }

  private void onGetImageError(String paramString)
  {
    RequestListenerWrapper localRequestListenerWrapper = (RequestListenerWrapper)this.mInFlightRequests.remove(paramString);
    if (localRequestListenerWrapper != null)
    {
      batchResponse(paramString, localRequestListenerWrapper);
      if (localRequestListenerWrapper.request == null)
        break label51;
    }
    label51: for (String str = localRequestListenerWrapper.request.getUrl(); ; str = "<null request>")
    {
      FinskyLog.logTiming("Bitmap error %s", new Object[] { str });
      return;
    }
  }

  private void onGetImageSuccess(String paramString, Bitmap paramBitmap)
  {
    RequestListenerWrapper localRequestListenerWrapper;
    if (paramBitmap.getHeight() * paramBitmap.getRowBytes() <= 512000)
    {
      this.mCachedRemoteImages.put(paramString, paramBitmap);
      localRequestListenerWrapper = (RequestListenerWrapper)this.mInFlightRequests.remove(paramString);
      if ((!((Boolean)G.explorerEnabled.get()).booleanValue()) || (localRequestListenerWrapper != null))
        break label72;
    }
    while (true)
    {
      return;
      FinskyLog.d("%s is too large to cache", new Object[] { paramString });
      break;
      label72: RequestListenerWrapper.access$302(localRequestListenerWrapper, paramBitmap);
      batchResponse(paramString, localRequestListenerWrapper);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localRequestListenerWrapper.request.getUrl();
      FinskyLog.logTiming("Loaded bitmap %s", arrayOfObject);
    }
  }

  public void drain(int paramInt)
  {
    FinskyApp.drain(this.mRequestQueue, paramInt);
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator1 = this.mInFlightRequests.keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str2 = (String)localIterator1.next();
      if ((((RequestListenerWrapper)this.mInFlightRequests.get(str2)).request == null) || (((RequestListenerWrapper)this.mInFlightRequests.get(str2)).request.getSequence() < paramInt))
        localArrayList.add(str2);
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
    {
      String str1 = (String)localIterator2.next();
      this.mInFlightRequests.remove(str1);
    }
  }

  public void evictCache()
  {
    this.mCachedRemoteImages.evictAll();
  }

  public BitmapContainer get(String paramString, Bitmap paramBitmap, BitmapLoadedHandler paramBitmapLoadedHandler)
  {
    return get(paramString, paramBitmap, paramBitmapLoadedHandler, 0, 0);
  }

  public BitmapContainer get(final String paramString, Bitmap paramBitmap, BitmapLoadedHandler paramBitmapLoadedHandler, final int paramInt1, final int paramInt2)
  {
    final String str = getCacheKey(paramString, paramInt1, paramInt2);
    return get(paramString, false, str, paramBitmap, new RemoteRequestCreator()
    {
      public Request<?> create()
      {
        return new BitmapLoader.DebugImageRequest(paramString, new Response.Listener()
        {
          public void onResponse(Bitmap paramAnonymous2Bitmap)
          {
            BitmapLoader.this.onGetImageSuccess(BitmapLoader.1.this.val$cacheKey, paramAnonymous2Bitmap);
          }
        }
        , paramInt1, paramInt2, Bitmap.Config.RGB_565, new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymous2VolleyError)
          {
            BitmapLoader.this.onGetImageError(BitmapLoader.1.this.val$cacheKey);
          }
        });
      }
    }
    , paramBitmapLoadedHandler);
  }

  public class BitmapContainer
  {
    private Bitmap mBitmap;
    private BitmapLoader.BitmapLoadedHandler mBitmapLoaded;
    private String mCacheKey;
    private final String mRequestUrl;

    public BitmapContainer(Bitmap paramString1, String paramString2, String paramBitmapLoadedHandler, BitmapLoader.BitmapLoadedHandler arg5)
    {
      this.mBitmap = paramString1;
      this.mRequestUrl = paramString2;
      this.mCacheKey = paramBitmapLoadedHandler;
      Object localObject;
      this.mBitmapLoaded = localObject;
    }

    public void cancelRequest()
    {
      if (this.mBitmapLoaded == null);
      while (true)
      {
        return;
        BitmapLoader.RequestListenerWrapper localRequestListenerWrapper1 = (BitmapLoader.RequestListenerWrapper)BitmapLoader.this.mInFlightRequests.get(this.mCacheKey);
        if (localRequestListenerWrapper1 != null)
        {
          if (localRequestListenerWrapper1.removeHandlerAndCancelIfNecessary(this))
            BitmapLoader.this.mInFlightRequests.remove(this.mCacheKey);
        }
        else
        {
          BitmapLoader.RequestListenerWrapper localRequestListenerWrapper2 = (BitmapLoader.RequestListenerWrapper)BitmapLoader.this.mBatchedResponses.get(this.mCacheKey);
          if (localRequestListenerWrapper2 != null)
          {
            localRequestListenerWrapper2.removeHandlerAndCancelIfNecessary(this);
            if (BitmapLoader.RequestListenerWrapper.access$600(localRequestListenerWrapper2).size() == 0)
              BitmapLoader.this.mBatchedResponses.remove(this.mCacheKey);
          }
        }
      }
    }

    public Bitmap getBitmap()
    {
      return this.mBitmap;
    }

    public String getRequestUrl()
    {
      return this.mRequestUrl;
    }
  }

  public static abstract interface BitmapLoadedHandler extends Response.Listener<BitmapLoader.BitmapContainer>
  {
    public abstract void onResponse(BitmapLoader.BitmapContainer paramBitmapContainer);
  }

  private static class DebugImageRequest extends ImageRequest
  {
    private static final Matrix IDENTITY = new Matrix();
    private boolean mResponseDelivered;

    public DebugImageRequest(String paramString, Response.Listener<Bitmap> paramListener, int paramInt1, int paramInt2, Bitmap.Config paramConfig, Response.ErrorListener paramErrorListener)
    {
      super(paramListener, paramInt1, paramInt2, paramConfig, paramErrorListener);
    }

    private Bitmap overlayDebugInfo(Bitmap paramBitmap, int paramInt)
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), paramBitmap.getConfig());
      Canvas localCanvas = new Canvas(localBitmap);
      localCanvas.drawBitmap(paramBitmap, IDENTITY, null);
      Paint localPaint = new Paint(8);
      int i;
      String str1;
      String str2;
      String str3;
      if (getUrl().contains("ggpht.com"))
      {
        i = -16711681;
        localPaint.setColor(i);
        localPaint.setStrokeWidth(3.0F);
        localPaint.setTextAlign(Paint.Align.LEFT);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paramInt);
        str1 = String.format("%dk", arrayOfObject1);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramBitmap.getHeight());
        str2 = String.format("%dh", arrayOfObject2);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Integer.valueOf(paramBitmap.getWidth());
        str3 = String.format("%dw", arrayOfObject3);
      }
      for (float f1 = 40.0F; ; f1 = (float)(0.8D * f1))
      {
        localPaint.setTextSize(f1);
        if ((3.1D * f1 <= localCanvas.getHeight()) && (1.1D * Math.max(Math.max(localPaint.measureText(str2), localPaint.measureText(str3)), localPaint.measureText(str1)) < localCanvas.getWidth()))
        {
          float f2 = localCanvas.getHeight() / 2 - f1;
          localCanvas.drawText(str1, 4.0F, f2, localPaint);
          float f3 = f2 + (5.0F + f1);
          localCanvas.drawText(str2, 4.0F, f3, localPaint);
          localCanvas.drawText(str3, 4.0F, f3 + (5.0F + f1), localPaint);
          paramBitmap.recycle();
          return localBitmap;
          i = -65281;
          break;
        }
      }
    }

    protected void deliverResponse(Bitmap paramBitmap)
    {
      if (this.mResponseDelivered);
      while (true)
      {
        return;
        this.mResponseDelivered = true;
        super.deliverResponse(paramBitmap);
      }
    }

    protected Response<Bitmap> parseNetworkResponse(NetworkResponse paramNetworkResponse)
    {
      Response localResponse = super.parseNetworkResponse(paramNetworkResponse);
      if ((!localResponse.isSuccess()) || (!((Boolean)G.debugImageSizes.get()).booleanValue()));
      while (true)
      {
        return localResponse;
        localResponse = Response.success(overlayDebugInfo((Bitmap)localResponse.result, paramNetworkResponse.data.length / 1024), localResponse.cacheEntry);
      }
    }
  }

  private static abstract interface RemoteRequestCreator
  {
    public abstract Request<?> create();
  }

  private class RequestListenerWrapper
  {
    private List<BitmapLoader.BitmapContainer> handlers = new ArrayList();
    private Request<?> request;
    private Bitmap responseBitmap;

    public RequestListenerWrapper(BitmapLoader.BitmapContainer arg2)
    {
      Object localObject1;
      this.request = localObject1;
      Object localObject2;
      this.handlers.add(localObject2);
    }

    public void addHandler(BitmapLoader.BitmapContainer paramBitmapContainer)
    {
      this.handlers.add(paramBitmapContainer);
    }

    public boolean removeHandlerAndCancelIfNecessary(BitmapLoader.BitmapContainer paramBitmapContainer)
    {
      this.handlers.remove(paramBitmapContainer);
      if (this.handlers.size() == 0)
        this.request.cancel();
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.BitmapLoader
 * JD-Core Version:    0.6.2
 */
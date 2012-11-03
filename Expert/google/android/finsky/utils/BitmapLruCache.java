package com.google.android.finsky.utils;

import android.graphics.Bitmap;

public class BitmapLruCache extends LruCache<String, Bitmap>
{
  public BitmapLruCache(int paramInt)
  {
    super(paramInt);
  }

  protected int sizeOf(String paramString, Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.BitmapLruCache
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.fragments;

import android.content.Context;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public abstract class ViewBinder<T>
{
  protected BitmapLoader mBitmapLoader;
  protected Context mContext;
  protected T mData;
  protected NavigationManager mNavManager;

  public void init(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader)
  {
    if (this.mContext == paramContext);
    while (true)
    {
      return;
      this.mContext = paramContext;
      this.mNavManager = paramNavigationManager;
      this.mBitmapLoader = paramBitmapLoader;
      this.mData = null;
    }
  }

  public void setData(T paramT)
  {
    this.mData = paramT;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.ViewBinder
 * JD-Core Version:    0.6.2
 */
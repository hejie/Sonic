package com.google.android.finsky.fragments;

import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public abstract interface PageFragmentHost
{
  public abstract BitmapLoader getBitmapLoader();

  public abstract DfeApi getDfeApi();

  public abstract NavigationManager getNavigationManager();

  public abstract void goBack();

  public abstract void showErrorDialog(String paramString1, String paramString2, boolean paramBoolean);

  public abstract void updateBreadcrumb(String paramString);

  public abstract void updateCurrentBackendId(int paramInt);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.PageFragmentHost
 * JD-Core Version:    0.6.2
 */
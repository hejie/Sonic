package com.google.android.finsky.layout;

import android.app.Activity;
import android.view.Menu;
import com.google.android.finsky.navigationmanager.NavigationManager;

public abstract interface CustomActionBar
{
  public abstract void addTab(String paramString, TabListener paramTabListener);

  public abstract void clearTabs();

  public abstract void configureMenu(Activity paramActivity, Menu paramMenu);

  public abstract String getBreadcrumbText();

  public abstract int getCurrentBackendId();

  public abstract void hide();

  public abstract void initialize(NavigationManager paramNavigationManager, Activity paramActivity);

  public abstract boolean searchButtonClicked(Activity paramActivity);

  public abstract void setSelectedTab(int paramInt);

  public abstract void shareButtonClicked(Activity paramActivity);

  public abstract void updateBreadcrumb(String paramString);

  public abstract void updateCurrentBackendId(int paramInt);

  public abstract void updateSearchQuery(String paramString);

  public abstract void wishlistButtonClicked(Activity paramActivity);

  public static abstract interface TabListener
  {
    public abstract void onTabReselected(int paramInt);

    public abstract void onTabSelected(int paramInt);

    public abstract void onTabUnselected(int paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CustomActionBar
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.app.Activity;
import android.view.Menu;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.providers.RecentSuggestionsProvider;

public class FakeActionBar
  implements CustomActionBar
{
  private int mBackendId;
  private String mBreadcrumb;

  public void addTab(String paramString, CustomActionBar.TabListener paramTabListener)
  {
  }

  public void clearTabs()
  {
  }

  public void configureMenu(Activity paramActivity, Menu paramMenu)
  {
  }

  public String getBreadcrumbText()
  {
    return this.mBreadcrumb;
  }

  public int getCurrentBackendId()
  {
    return this.mBackendId;
  }

  public void hide()
  {
  }

  public void initialize(NavigationManager paramNavigationManager, Activity paramActivity)
  {
  }

  public boolean searchButtonClicked(Activity paramActivity)
  {
    return false;
  }

  public void setSelectedTab(int paramInt)
  {
  }

  public void shareButtonClicked(Activity paramActivity)
  {
  }

  public void updateBreadcrumb(String paramString)
  {
    this.mBreadcrumb = paramString;
  }

  public void updateCurrentBackendId(int paramInt)
  {
    this.mBackendId = paramInt;
    RecentSuggestionsProvider.setCurrentBackendId(this.mBackendId);
  }

  public void updateSearchQuery(String paramString)
  {
  }

  public void wishlistButtonClicked(Activity paramActivity)
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.FakeActionBar
 * JD-Core Version:    0.6.2
 */
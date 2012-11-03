package com.google.android.finsky.activities;

import android.app.Activity;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import com.google.android.finsky.navigationmanager.NavigationManager;

class FakeNavigationManager extends NavigationManager
{
  private Activity mActivity;

  public FakeNavigationManager(Activity paramActivity)
  {
    super(null);
    this.mActivity = paramActivity;
  }

  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
  }

  public boolean canGoUp()
  {
    return true;
  }

  public boolean canSearch()
  {
    return false;
  }

  public boolean goBack()
  {
    this.mActivity.onBackPressed();
    return true;
  }

  public void goUp()
  {
    this.mActivity.onBackPressed();
  }

  public void init(MainActivity paramMainActivity)
  {
  }

  public boolean isEmpty()
  {
    return false;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FakeNavigationManager
 * JD-Core Version:    0.6.2
 */
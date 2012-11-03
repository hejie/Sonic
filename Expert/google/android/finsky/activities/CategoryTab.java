package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public class CategoryTab
  implements ViewPagerTab
{
  private final CategoryViewBinder mCategoryBinder;
  private ViewGroup mCategoryView;
  private final String mCurrentBrowseUrl;
  private boolean mIsCurrentlySelected;
  private final LayoutInflater mLayoutInflater;
  private final DfeToc mToc;

  public CategoryTab(Context paramContext, LayoutInflater paramLayoutInflater, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeBrowse paramDfeBrowse, String paramString, DfeToc paramDfeToc)
  {
    if ((paramDfeBrowse == null) || (!paramDfeBrowse.isReady()))
      throw new IllegalStateException("Tried to create category tab with invalid data!");
    this.mLayoutInflater = paramLayoutInflater;
    this.mCurrentBrowseUrl = paramString;
    this.mCategoryBinder = new CategoryViewBinder();
    this.mCategoryBinder.init(paramContext, paramNavigationManager, paramBitmapLoader);
    this.mCategoryBinder.setData(paramDfeBrowse);
    this.mToc = paramDfeToc;
  }

  private void logClickForCurrentPage()
  {
    FinskyApp.get().getEventLogger().logView(this.mCurrentBrowseUrl, null, "cat");
  }

  public View getView(int paramInt)
  {
    if (this.mCategoryView == null)
    {
      this.mCategoryView = ((ViewGroup)this.mLayoutInflater.inflate(2130968617, null));
      this.mCategoryView.findViewById(2131230848).setVisibility(8);
      this.mCategoryView.findViewById(2131230821).setVisibility(0);
      this.mCategoryBinder.bind(this.mCategoryView, paramInt, this.mCurrentBrowseUrl, this.mToc);
    }
    return this.mCategoryView;
  }

  public void onDestroy()
  {
    this.mCategoryBinder.onDestroy();
    this.mCategoryView = null;
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
  }

  public Bundle onSaveInstanceState()
  {
    return null;
  }

  public void setTabSelected(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.mIsCurrentlySelected))
      logClickForCurrentPage();
    this.mIsCurrentlySelected = paramBoolean;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.CategoryTab
 * JD-Core Version:    0.6.2
 */
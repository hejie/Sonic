package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.BucketedListBinder;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;
import java.util.List;

public final class ListTab
  implements Response.ErrorListener, ViewPagerTab, OnDataChangedListener
{
  private BucketedListBinder mBinder;
  private final String mCurrentBrowseUrl;
  private boolean mIsCurrentlySelected;
  private Bucket mLastKnownBucket;
  private final LayoutInflater mLayoutInflater;
  private final DfeList mList;
  private boolean mListBoundAlready = false;
  private ViewGroup mListTabContainer;
  private ViewGroup mListTabWrapper;
  protected final NavigationManager mNavigationManager;
  private final int mNumCellsWide;
  private final String mReferrerBrowseUrl;
  private Bundle mRestoreBundle;
  protected final DfeToc mToc;

  public ListTab(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeApi paramDfeApi, LayoutInflater paramLayoutInflater, DfeList paramDfeList, String paramString1, String paramString2, DfeToc paramDfeToc, int paramInt)
  {
    this.mLayoutInflater = paramLayoutInflater;
    this.mList = paramDfeList;
    this.mReferrerBrowseUrl = paramString1;
    this.mCurrentBrowseUrl = paramString2;
    this.mNumCellsWide = getColumnCount(paramContext, paramInt);
    this.mList.setWindowDistance(paramContext.getResources().getInteger(2131492871) * this.mNumCellsWide);
    this.mList.addDataChangedListener(this);
    this.mList.addErrorListener(this);
    this.mList.startLoadItems();
    this.mToc = paramDfeToc;
    this.mNavigationManager = paramNavigationManager;
    createBinder(paramContext, paramBitmapLoader, paramDfeApi);
  }

  private void bindList(View paramView, ListView paramListView)
  {
    if (!this.mListBoundAlready)
    {
      paramView.setVisibility(8);
      paramListView.setVisibility(0);
      this.mBinder.setData(this.mList);
      this.mBinder.bind(this.mListTabWrapper, this.mNumCellsWide, 1, this.mLastKnownBucket, this.mCurrentBrowseUrl, this.mRestoreBundle);
      if (this.mRestoreBundle != null)
      {
        paramListView.onRestoreInstanceState(this.mRestoreBundle.getParcelable("ListTab.ListParcelKey"));
        this.mRestoreBundle = null;
      }
      this.mListBoundAlready = true;
    }
  }

  private void createBinder(Context paramContext, BitmapLoader paramBitmapLoader, DfeApi paramDfeApi)
  {
    this.mBinder = new BucketedListBinder(2130968618, this.mToc, paramDfeApi);
    this.mBinder.init(paramContext, this.mNavigationManager, paramBitmapLoader);
  }

  private int getColumnCount(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    if (paramInt == 6);
    for (int i = localResources.getDisplayMetrics().widthPixels / localResources.getDimensionPixelSize(2131427346); ; i = localResources.getInteger(2131492870))
      return i;
  }

  private int getListTabContainerLayoutRes()
  {
    if ((this.mList.getBucketCount() == 1) && (((Bucket)this.mList.getBucketList().get(0)).hasEditorialSeriesContainer()));
    for (int i = 2130968722; ; i = 2130968608)
      return i;
  }

  private void inflateLayoutForTemplateType()
  {
    this.mListTabContainer = ((ViewGroup)this.mLayoutInflater.inflate(getListTabContainerLayoutRes(), null));
    this.mListTabWrapper.addView(this.mListTabContainer);
  }

  private void logViewImpressionForList()
  {
    if ((this.mList.isReady()) && (this.mList.getBucketCount() > 0))
    {
      String str = ((Bucket)this.mList.getBucketList().get(0)).getAnalyticsCookie();
      FinskyApp.get().getAnalytics().logListViewOnPage(this.mReferrerBrowseUrl, null, this.mCurrentBrowseUrl, str);
      FinskyApp.get().getEventLogger().logView(this.mCurrentBrowseUrl, str, this.mList.getInitialUrl());
    }
  }

  private void syncViewToState()
  {
    if (this.mListTabWrapper == null);
    while (true)
    {
      return;
      View localView1 = this.mListTabWrapper.findViewById(2131230848);
      View localView2 = this.mListTabWrapper.findViewById(2131231139);
      if (this.mList.inErrorState())
      {
        if (!this.mListBoundAlready)
        {
          localView2.setVisibility(0);
          ((TextView)localView2.findViewById(2131230788)).setText(ErrorStrings.get(FinskyApp.get(), this.mList.getVolleyError()));
          localView2.findViewById(2131231014).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ListTab.this.mList.resetItems();
              ListTab.this.mList.clearTransientState();
              ListTab.this.mList.startLoadItems();
              ListTab.this.syncViewToState();
            }
          });
          localView1.setVisibility(8);
          if (this.mListTabContainer != null)
            this.mListTabContainer.setVisibility(8);
        }
      }
      else if (this.mList.isReady())
      {
        if ((this.mList.getBucketCount() > 0) && (this.mLastKnownBucket == null))
          this.mLastKnownBucket = ((Bucket)this.mList.getBucketList().get(0));
        if (this.mListTabContainer == null)
          inflateLayoutForTemplateType();
        ListView localListView = (ListView)this.mListTabWrapper.findViewById(2131230821);
        localView2.setVisibility(8);
        localListView.setVisibility(0);
        localView1.setVisibility(8);
        bindList(localView1, localListView);
      }
      else
      {
        localView2.setVisibility(8);
        localView1.setVisibility(0);
        if (this.mListTabContainer != null)
          this.mListTabContainer.setVisibility(8);
      }
    }
  }

  public View getView(int paramInt)
  {
    if (this.mListTabWrapper == null)
    {
      this.mListTabWrapper = ((ViewGroup)this.mLayoutInflater.inflate(2130968724, null));
      syncViewToState();
    }
    return this.mListTabWrapper;
  }

  public void onDataChanged()
  {
    if (this.mIsCurrentlySelected)
      logViewImpressionForList();
    syncViewToState();
  }

  public void onDestroy()
  {
    this.mBinder.onDestroyView();
    this.mList.removeDataChangedListener(this);
    this.mList.removeErrorListener(this);
    this.mList.flushUnusedPages();
    this.mListTabContainer = null;
    this.mListTabWrapper = null;
    this.mListBoundAlready = false;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    syncViewToState();
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
    this.mRestoreBundle = paramBundle;
  }

  public Bundle onSaveInstanceState()
  {
    Bundle localBundle;
    if (this.mListTabContainer != null)
    {
      ListView localListView = (ListView)this.mListTabContainer.findViewById(2131230821);
      if (localListView.getVisibility() == 0)
      {
        localBundle = new Bundle();
        localBundle.putParcelable("ListTab.ListParcelKey", localListView.onSaveInstanceState());
        this.mBinder.onSaveInstanceState(localBundle);
      }
    }
    while (true)
    {
      return localBundle;
      localBundle = null;
    }
  }

  public void setTabSelected(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.mIsCurrentlySelected))
      logViewImpressionForList();
    this.mIsCurrentlySelected = paramBoolean;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ListTab
 * JD-Core Version:    0.6.2
 */
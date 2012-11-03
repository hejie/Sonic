package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.UnevenGridAdapter;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.GridSequencer;
import com.google.android.finsky.layout.UnevenGrid;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.List;

public class CorporaHomeFragment extends UrlBasedPageFragment
{
  private static final int[] GRID_SEQUENCE_4xN = { 1, 2, 4, 3, 1, 3, 3, 1 };
  private static final int[] GRID_SEQUENCE_6xN = { 1, 2, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1 };
  private static final int[] GRID_SEQUENCE_6xN_PROMO_HEAVY = { 1, 2, 4, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1, 1, 4, 4 };
  private static final int[] GRID_SEQUENCE_8xN = { 1, 1, 2, 4, 4, 4, 4, 4, 4, 4 };
  private DfeBrowse mBrowseData;
  private boolean mGridSequenceDataAdded;
  private GridSequencer mGridSequencer;
  private final Handler mHandler = new Handler();
  private boolean mHaveLoggedBefore = false;
  private View mPageContent;
  private DfeList mPromoListData;
  private int mScrollPosition = 0;
  private final Runnable mScrollRunnable = new Runnable()
  {
    public void run()
    {
      CorporaHomeFragment.this.mPageContent.scrollTo(0, CorporaHomeFragment.this.mScrollPosition);
    }
  };

  private void addItemsToGridSequence()
  {
    if (!this.mGridSequenceDataAdded)
    {
      ArrayList localArrayList = Lists.newArrayList();
      if ((getPromoListData() != null) && (getPromoListData().getBucketCount() > 0))
        localArrayList.addAll(((Bucket)getPromoListData().getBucketList().get(0)).getChildren());
      this.mGridSequencer.setPromoData(localArrayList);
      this.mGridSequenceDataAdded = true;
    }
  }

  private DfeList getPromoListData()
  {
    if (this.mPromoListData == null)
      if ((this.mBrowseData != null) && (this.mBrowseData.isReady()) && (this.mBrowseData.hasPromotionalItems()));
    for (DfeList localDfeList = null; ; localDfeList = this.mPromoListData)
    {
      return localDfeList;
      this.mPromoListData = this.mBrowseData.buildPromoList(this.mDfeApi);
      this.mPromoListData.addDataChangedListener(this);
      this.mPromoListData.addErrorListener(this);
    }
  }

  private void logPageView()
  {
    if (!this.mHaveLoggedBefore)
    {
      String str1 = null;
      String str2 = null;
      if (this.mBrowseData.hasPromotionalItems())
      {
        str1 = ((Bucket)getPromoListData().getBucketList().get(0)).getAnalyticsCookie();
        str2 = getPromoListData().getInitialUrl();
      }
      FinskyApp.get().getAnalytics().logListViewOnPage(getArguments().getString("CorporaHomeFragment.ReferrerUrl"), null, this.mUrl, str1);
      FinskyApp.get().getEventLogger().logView(this.mUrl, str1, str2);
      this.mHaveLoggedBefore = true;
    }
  }

  public static CorporaHomeFragment newInstance(DfeToc paramDfeToc, String paramString1, String paramString2)
  {
    CorporaHomeFragment localCorporaHomeFragment = new CorporaHomeFragment();
    localCorporaHomeFragment.setDfeTocAndUrl(paramDfeToc, paramString1);
    localCorporaHomeFragment.setArgument("CorporaHomeFragment.ReferrerUrl", paramString2);
    return localCorporaHomeFragment;
  }

  private void resetDfeModels()
  {
    if (this.mBrowseData != null)
    {
      this.mBrowseData.removeDataChangedListener(this);
      this.mBrowseData.removeErrorListener(this);
    }
    this.mBrowseData = null;
    if (this.mPromoListData != null)
    {
      this.mPromoListData.removeDataChangedListener(this);
      this.mPromoListData.removeErrorListener(this);
    }
    this.mPromoListData = null;
  }

  protected int getLayoutRes()
  {
    return 2130968621;
  }

  protected boolean isDataReady()
  {
    if ((this.mBrowseData != null) && (this.mBrowseData.isReady()) && ((!this.mBrowseData.hasPromotionalItems()) || (getPromoListData().isReady())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (!isDataReady())
    {
      switchToLoading();
      requestData();
      rebindActionBar();
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (isDataReady())
      rebindViews();
    return localView;
  }

  public void onDataChanged()
  {
    if (!isAdded());
    while (true)
    {
      return;
      if ((this.mBrowseData != null) && (this.mBrowseData.isReady()) && (this.mPromoListData == null))
      {
        getPromoListData();
        if (this.mPromoListData != null)
          this.mPromoListData.startLoadItems();
      }
      if (isDataReady())
      {
        logPageView();
        addItemsToGridSequence();
        super.onDataChanged();
      }
    }
  }

  public void onDestroyView()
  {
    this.mHandler.removeCallbacks(this.mScrollRunnable);
    if (this.mPageContent != null)
      this.mScrollPosition = this.mPageContent.getScrollY();
    ((UnevenGrid)this.mDataView.findViewById(2131230856)).onDestroyView();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
    int i = getResources().getInteger(2131492872);
    int[] arrayOfInt;
    if (i == 8)
      arrayOfInt = GRID_SEQUENCE_8xN;
    while (true)
    {
      this.mGridSequencer = new GridSequencer(this.mContext, this.mNavigationManager, this.mBitmapLoader, arrayOfInt, getToc(), this.mUrl);
      return;
      if (i == 6)
      {
        if (getResources().getBoolean(2131296259))
          arrayOfInt = GRID_SEQUENCE_6xN_PROMO_HEAVY;
        else
          arrayOfInt = GRID_SEQUENCE_6xN;
      }
      else
        arrayOfInt = GRID_SEQUENCE_4xN;
    }
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(this.mContext.getString(2131165653));
    this.mPageFragmentHost.updateCurrentBackendId(0);
  }

  protected void rebindViews()
  {
    switchToData();
    rebindActionBar();
    UnevenGrid localUnevenGrid = (UnevenGrid)this.mDataView.findViewById(2131230856);
    localUnevenGrid.setHasTopGutter(false);
    UnevenGridAdapter localUnevenGridAdapter = this.mGridSequencer.getAdapter();
    localUnevenGridAdapter.setShowCorpusStrip(true);
    localUnevenGrid.setAdapter(localUnevenGridAdapter);
    this.mPageContent = this.mDataView.findViewById(2131231045);
    this.mHandler.post(this.mScrollRunnable);
  }

  protected void requestData()
  {
    resetDfeModels();
    this.mBrowseData = new DfeBrowse(this.mDfeApi, this.mUrl);
    this.mBrowseData.addDataChangedListener(this);
    this.mBrowseData.addErrorListener(this);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.CorporaHomeFragment
 * JD-Core Version:    0.6.2
 */
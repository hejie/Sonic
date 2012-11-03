package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.GridSequencer;
import com.google.android.finsky.layout.UnevenGrid;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.BitmapLoader;
import java.util.List;

public class GridFeaturedTab
  implements ViewPagerTab, OnDataChangedListener
{
  private static final int[] GRID_LAYOUT_SEQUENCE_4xN_6xN = { 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1 };
  private static final int[] GRID_LAYOUT_SEQUENCE_8xN = { 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4 };
  private static final int[] GRID_LAYOUT_SEQUENCE_APPS_4xN_6xN = { 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1 };
  private static final int[] GRID_LAYOUT_SEQUENCE_MUSIC_4xN_6xN = { 1, 4, 4, 4, 4, 1, 4, 4, 4, 4, 1 };
  private ViewGroup mCachedView;
  private final Context mContext;
  private final String mCurrentBrowseUrl;
  private boolean mIsCurrentlySelected;
  private final LayoutInflater mLayoutInflater;
  private final GridSequencer mLayoutSequencer;
  private DfeList mPromoList;
  private final String mReferrerBrowseUrl;
  private final int[] mSequence;

  public GridFeaturedTab(Context paramContext, LayoutInflater paramLayoutInflater, BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, DfeList paramDfeList, String paramString1, String paramString2, DfeToc paramDfeToc)
  {
    this.mContext = paramContext;
    this.mLayoutInflater = paramLayoutInflater;
    this.mPromoList = paramDfeList;
    this.mReferrerBrowseUrl = paramString1;
    this.mCurrentBrowseUrl = paramString2;
    if (paramContext.getResources().getInteger(2131492872) == 8)
    {
      this.mSequence = GRID_LAYOUT_SEQUENCE_8xN;
      this.mLayoutSequencer = new GridSequencer(this.mContext, paramNavigationManager, paramBitmapLoader, this.mSequence, paramDfeToc, paramString2);
      if ((!this.mPromoList.isMoreAvailable()) && (this.mPromoList.isReady()))
        break label197;
      this.mPromoList.startLoadItems();
      this.mPromoList.addDataChangedListener(this);
    }
    while (true)
    {
      return;
      if ((paramDfeToc.getCorpus(2) != null) && (paramString2.equals(paramDfeToc.getCorpus(2).getLandingUrl())))
      {
        this.mSequence = GRID_LAYOUT_SEQUENCE_MUSIC_4xN_6xN;
        break;
      }
      if ((paramDfeToc.getCorpus(3) != null) && (paramString2.equals(paramDfeToc.getCorpus(3).getLandingUrl())))
      {
        this.mSequence = GRID_LAYOUT_SEQUENCE_APPS_4xN_6xN;
        break;
      }
      this.mSequence = GRID_LAYOUT_SEQUENCE_4xN_6xN;
      break;
      label197: onDataChanged();
    }
  }

  private void logClickForCurrentPage()
  {
    if ((this.mPromoList.isReady()) && (this.mPromoList.getBucketCount() > 0))
    {
      String str = ((Bucket)this.mPromoList.getBucketList().get(0)).getAnalyticsCookie();
      FinskyApp.get().getAnalytics().logListViewOnPage(this.mReferrerBrowseUrl, null, this.mCurrentBrowseUrl, str);
      FinskyApp.get().getEventLogger().logView(this.mCurrentBrowseUrl, str, this.mPromoList.getInitialUrl());
    }
  }

  public View getView(int paramInt)
  {
    if (this.mCachedView == null)
    {
      this.mCachedView = ((ViewGroup)this.mLayoutInflater.inflate(2130968771, null));
      UnevenGrid localUnevenGrid = (UnevenGrid)this.mCachedView.findViewById(2131231132);
      localUnevenGrid.setHasTopGutter(true);
      localUnevenGrid.setAdapter(this.mLayoutSequencer.getAdapter());
    }
    return this.mCachedView;
  }

  public void onDataChanged()
  {
    if (this.mIsCurrentlySelected)
      logClickForCurrentPage();
    this.mLayoutSequencer.setPromoData(((Bucket)this.mPromoList.getBucketList().get(0)).getChildren());
  }

  public void onDestroy()
  {
    if (this.mCachedView != null)
      ((UnevenGrid)this.mCachedView.findViewById(2131231132)).onDestroyView();
    this.mPromoList.removeDataChangedListener(this);
    this.mLayoutSequencer.onDestroy();
    this.mCachedView = null;
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
 * Qualified Name:     com.google.android.finsky.activities.GridFeaturedTab
 * JD-Core Version:    0.6.2
 */
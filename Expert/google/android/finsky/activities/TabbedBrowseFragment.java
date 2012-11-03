package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Browse.BrowseLink;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.util.List;

public class TabbedBrowseFragment extends UrlBasedPageFragment
  implements ViewPager.OnPageChangeListener
{
  private int mBackendId = 0;
  private String mBreadcrumb;
  DfeBrowse mBrowseData;
  DfeList mContentListData;
  private DfeList mPromoListData;
  private String mReferringBrowseUrl;
  private int mRestoreSelectedPanel = -1;
  private Bundle mSavedInstanceState = new Bundle();
  TabbedAdapter mTabbedAdapter;
  private Bundle mTabbedAdapterBundle;
  ViewPager mViewPager;

  private void clearState()
  {
    if (this.mBrowseData != null)
    {
      this.mBrowseData.removeDataChangedListener(this);
      this.mBrowseData.removeErrorListener(this);
      this.mBrowseData = null;
    }
    if (this.mContentListData != null)
    {
      this.mContentListData.removeDataChangedListener(this);
      this.mContentListData.removeErrorListener(this);
      this.mContentListData = null;
    }
    if (this.mPromoListData != null)
    {
      this.mPromoListData.removeDataChangedListener(this);
      this.mPromoListData.removeErrorListener(this);
      this.mPromoListData = null;
    }
    if (this.mDataView != null)
      this.mDataView.findViewById(2131231102).setVisibility(4);
    if (this.mViewPager != null)
    {
      this.mViewPager.setOnPageChangeListener(null);
      this.mViewPager.setAdapter(null);
      this.mViewPager = null;
    }
  }

  private String getBreadcrumbText()
  {
    int i = this.mBrowseData.getBreadcrumbList().size();
    String str;
    if (i > 0)
      str = ((Browse.BrowseLink)this.mBrowseData.getBreadcrumbList().get(i - 1)).getName();
    while (true)
    {
      return str;
      Toc.CorpusMetadata localCorpusMetadata = getToc().getCorpus(this.mBackendId);
      if (localCorpusMetadata == null)
        str = "";
      else if (!this.mNavigationManager.canGoUp())
        str = this.mContext.getString(2131165653);
      else
        str = localCorpusMetadata.getName();
    }
  }

  public static TabbedBrowseFragment newInstance(String paramString1, String paramString2, int paramInt, String paramString3, DfeToc paramDfeToc)
  {
    TabbedBrowseFragment localTabbedBrowseFragment = new TabbedBrowseFragment();
    if (paramInt >= 0)
      localTabbedBrowseFragment.mBackendId = paramInt;
    if (!TextUtils.isEmpty(paramString2))
      localTabbedBrowseFragment.mBreadcrumb = paramString2;
    localTabbedBrowseFragment.setDfeTocAndUrl(paramDfeToc, paramString1);
    localTabbedBrowseFragment.setArgument("TabbedBrowseFragment.ReferrerUrl", paramString3);
    return localTabbedBrowseFragment;
  }

  private void recordState()
  {
    if (isDataReady())
    {
      this.mSavedInstanceState.putParcelable("TabbedBrowseFragment.BrowseData", this.mBrowseData);
      if (this.mBreadcrumb != null)
        this.mSavedInstanceState.putString("TabbedBrowseFragment.Breadcrumb", this.mBreadcrumb);
      this.mSavedInstanceState.putInt("TabbedBrowseFragment.BackendId", this.mBackendId);
      if (this.mContentListData != null)
        this.mSavedInstanceState.putParcelable("TabbedBrowseFragment.ListData", this.mContentListData);
      if (this.mPromoListData != null)
        this.mSavedInstanceState.putParcelable("TabbedBrowseFragment.PromoData", this.mPromoListData);
      if (this.mViewPager != null)
      {
        this.mSavedInstanceState.putInt("TabbedBrowseFragment.CurrentTab", this.mViewPager.getCurrentItem());
        this.mTabbedAdapter.onSaveInstanceState(this.mSavedInstanceState);
      }
    }
  }

  private void restoreFromBundle(boolean paramBoolean)
  {
    if (this.mBrowseData != null)
    {
      this.mBrowseData.removeDataChangedListener(this);
      this.mBrowseData.removeErrorListener(this);
    }
    this.mBrowseData = ((DfeBrowse)this.mSavedInstanceState.getParcelable("TabbedBrowseFragment.BrowseData"));
    if ((this.mSavedInstanceState.containsKey("TabbedBrowseFragment.ListData")) && (!paramBoolean))
    {
      this.mContentListData = ((DfeList)this.mSavedInstanceState.getParcelable("TabbedBrowseFragment.ListData"));
      this.mContentListData.setDfeApi(this.mDfeApi);
      this.mTabbedAdapterBundle = this.mSavedInstanceState;
    }
    if ((this.mSavedInstanceState.containsKey("TabbedBrowseFragment.PromoData")) && (!paramBoolean))
    {
      this.mPromoListData = ((DfeList)this.mSavedInstanceState.getParcelable("TabbedBrowseFragment.PromoData"));
      this.mPromoListData.setDfeApi(this.mDfeApi);
    }
    if (this.mSavedInstanceState.containsKey("TabbedBrowseFragment.Breadcrumb"));
    for (this.mBreadcrumb = this.mSavedInstanceState.getString("TabbedBrowseFragment.Breadcrumb"); ; this.mBreadcrumb = null)
    {
      if (this.mSavedInstanceState.containsKey("TabbedBrowseFragment.BackendId"))
        this.mBackendId = this.mSavedInstanceState.getInt("TabbedBrowseFragment.BackendId");
      if (this.mSavedInstanceState.containsKey("TabbedBrowseFragment.CurrentTab"))
        this.mRestoreSelectedPanel = this.mSavedInstanceState.getInt("TabbedBrowseFragment.CurrentTab");
      onDataChanged();
      if (!isDataReady())
        switchToLoading();
      return;
    }
  }

  protected int getLayoutRes()
  {
    return 2130968842;
  }

  protected boolean isDataReady()
  {
    if ((this.mBrowseData != null) && (this.mBrowseData.isReady()) && ((this.mContentListData == null) || (this.mContentListData.isReady())) && ((this.mPromoListData == null) || (this.mPromoListData.isReady())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mReferringBrowseUrl = getArguments().getString("TabbedBrowseFragment.ReferrerUrl");
    if (paramBundle != null)
      this.mSavedInstanceState = paramBundle;
    boolean bool;
    if (this.mSavedInstanceState.containsKey("TabbedBrowseFragment.BrowseData"))
      if (paramBundle != null)
      {
        bool = true;
        restoreFromBundle(bool);
      }
    while (true)
    {
      return;
      bool = false;
      break;
      if (!isDataReady())
      {
        switchToLoading();
        requestData();
        rebindActionBar();
      }
      else
      {
        rebindViews();
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
  }

  public void onDataChanged()
  {
    if (this.mBrowseData.isReady())
    {
      if (this.mContentListData == null)
      {
        this.mContentListData = this.mBrowseData.buildContentList(this.mDfeApi);
        if (this.mContentListData != null)
        {
          this.mContentListData.addDataChangedListener(this);
          this.mContentListData.addErrorListener(this);
          this.mContentListData.startLoadItems();
        }
      }
      if (this.mPromoListData == null)
      {
        this.mPromoListData = this.mBrowseData.buildPromoList(this.mDfeApi);
        if (this.mPromoListData != null)
        {
          this.mPromoListData.addDataChangedListener(this);
          this.mPromoListData.addErrorListener(this);
          this.mPromoListData.startLoadItems();
        }
      }
      if (isDataReady())
      {
        if (this.mContentListData != null)
        {
          this.mBackendId = this.mContentListData.getBackendId(this.mBackendId);
          this.mContentListData.removeDataChangedListener(this);
          this.mContentListData.removeErrorListener(this);
        }
        if (TextUtils.isEmpty(this.mBreadcrumb))
          this.mBreadcrumb = getBreadcrumbText();
        super.onDataChanged();
      }
    }
  }

  public void onDestroyView()
  {
    recordState();
    clearState();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
  }

  public void onPageScrollStateChanged(int paramInt)
  {
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
  }

  public void onPageSelected(int paramInt)
  {
    this.mTabbedAdapter.onPageSelected(paramInt);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    recordState();
    paramBundle.putAll(this.mSavedInstanceState);
    super.onSaveInstanceState(paramBundle);
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
    this.mPageFragmentHost.updateCurrentBackendId(this.mBackendId);
  }

  public void rebindViews()
  {
    switchToData();
    rebindActionBar();
    if ((this.mViewPager != null) && (this.mTabbedAdapter != null));
    while (true)
    {
      return;
      this.mViewPager = ((ViewPager)this.mDataView.findViewById(2131231101));
      this.mTabbedAdapter = new TabbedAdapter(this.mContext, getActivity().getLayoutInflater(), this.mNavigationManager, getToc(), this.mDfeApi, this.mBitmapLoader, this.mBrowseData, this.mContentListData, this.mPromoListData, this.mBackendId, this.mUrl, this.mReferringBrowseUrl, this.mTabbedAdapterBundle);
      this.mViewPager.setAdapter(this.mTabbedAdapter);
      this.mViewPager.setPageMargin(getResources().getDimensionPixelSize(2131427380));
      this.mViewPager.setPageMarginDrawable(2131361797);
      int i = 1;
      if (this.mContentListData.getBucketCount() == 1)
      {
        Bucket localBucket = (Bucket)this.mContentListData.getBucketList().get(0);
        if ((localBucket.hasEditorialSeriesContainer()) || (localBucket.hasContainerWithBannerTemplate()))
          i = 0;
      }
      PagerTabStrip localPagerTabStrip = (PagerTabStrip)this.mDataView.findViewById(2131231102);
      if (i != 0)
      {
        localPagerTabStrip.setVisibility(0);
        int k = CorpusResourceUtils.getBackendForegroundColor(this.mContext, this.mBackendId);
        localPagerTabStrip.setTextColor(k);
        localPagerTabStrip.setTabIndicatorColor(k);
        localPagerTabStrip.setDrawFullUnderline(true);
        localPagerTabStrip.setTextSpacing((int)(80.0F * getResources().getDisplayMetrics().density));
        localPagerTabStrip.setNonPrimaryAlpha(0.7F);
        label271: this.mViewPager.setOnPageChangeListener(this);
        if (!this.mBrowseData.hasCategories())
          break label347;
      }
      label347: for (int j = 1; ; j = 0)
      {
        if (this.mRestoreSelectedPanel != -1)
        {
          j = this.mRestoreSelectedPanel;
          this.mRestoreSelectedPanel = -1;
        }
        onPageScrolled(j, 0.0F, 0);
        if (this.mViewPager.getCurrentItem() != j)
          break label352;
        this.mTabbedAdapter.onPageSelected(j);
        break;
        localPagerTabStrip.setVisibility(8);
        break label271;
      }
      label352: this.mViewPager.setCurrentItem(j, false);
    }
  }

  public void refresh()
  {
    if (isDataReady())
      rebindViews();
    while (true)
    {
      return;
      super.refresh();
    }
  }

  protected void requestData()
  {
    clearState();
    this.mBrowseData = new DfeBrowse(this.mDfeApi, this.mUrl);
    this.mBrowseData.addDataChangedListener(this);
    this.mBrowseData.addErrorListener(this);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.TabbedBrowseFragment
 * JD-Core Version:    0.6.2
 */
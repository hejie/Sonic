package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabbedAdapter extends PagerAdapter
{
  private final int mBackendId;
  private final BitmapLoader mBitmapLoader;
  private final DfeList mContentListData;
  private final Context mContext;
  private final String mCurrentPageUrl;
  private final DfeApi mDfeApi;
  private final DfeBrowse mDfeBrowse;
  private final DfeToc mDfeToc;
  private final LayoutInflater mLayoutInflater;
  private final NavigationManager mNavigationManager;
  private int mNonListTabCount;
  private final DfeList mPromoData;
  private final String mReferrerUrl;
  List<TabType> mTabDataList = Lists.newArrayList();
  private final List<String> mTabTitles;

  public TabbedAdapter(Context paramContext, LayoutInflater paramLayoutInflater, NavigationManager paramNavigationManager, DfeToc paramDfeToc, DfeApi paramDfeApi, BitmapLoader paramBitmapLoader, DfeBrowse paramDfeBrowse, DfeList paramDfeList1, DfeList paramDfeList2, int paramInt, String paramString1, String paramString2, Bundle paramBundle)
  {
    this.mContext = paramContext;
    this.mLayoutInflater = paramLayoutInflater;
    this.mNavigationManager = paramNavigationManager;
    this.mDfeToc = paramDfeToc;
    this.mDfeApi = paramDfeApi;
    this.mBitmapLoader = paramBitmapLoader;
    this.mDfeBrowse = paramDfeBrowse;
    this.mContentListData = paramDfeList1;
    this.mPromoData = paramDfeList2;
    this.mBackendId = paramInt;
    this.mCurrentPageUrl = paramString1;
    this.mReferrerUrl = paramString2;
    generateTabList(paramBundle, this.mPromoData);
    this.mTabTitles = getTitles();
  }

  private void generateTabList(Bundle paramBundle, DfeList paramDfeList)
  {
    int i = 1;
    List localList1 = restoreScrollPositions(paramBundle);
    List localList2 = restoreDfeLists(paramBundle);
    this.mTabDataList.clear();
    if (this.mDfeBrowse.hasCategories())
      this.mTabDataList.add(new TabType(0));
    if (this.mDfeBrowse.hasPromotionalItems())
    {
      TabType localTabType = new TabType(i);
      localTabType.tabListData = paramDfeList;
      this.mTabDataList.add(localTabType);
    }
    this.mNonListTabCount = this.mTabDataList.size();
    if (this.mContentListData != null)
      for (int m = 0; m < this.mContentListData.getBucketCount(); m++)
        this.mTabDataList.add(new TabType(2));
    int j;
    if ((localList2 != null) && (localList2.size() == this.mTabDataList.size()))
    {
      j = i;
      if ((localList1 == null) || (localList1.size() != this.mTabDataList.size()))
        break label301;
    }
    while (true)
    {
      for (int k = this.mNonListTabCount; k < this.mTabDataList.size(); k++)
      {
        if (j != 0)
          ((TabType)this.mTabDataList.get(k)).tabListData = ((DfeList)localList2.get(k));
        if (i != 0)
          TabType.access$002((TabType)this.mTabDataList.get(k), (Bundle)localList1.get(k));
      }
      j = 0;
      break;
      label301: i = 0;
    }
  }

  private DfeList getDfeListForListTab(int paramInt)
  {
    if (this.mContentListData.getBucketCount() == 1);
    String str;
    for (DfeList localDfeList = this.mContentListData; ; localDfeList = new DfeList(this.mDfeApi, str, true))
    {
      return localDfeList;
      str = ((Bucket)this.mContentListData.getBucketList().get(paramInt)).getBrowseUrl().replaceFirst("browse", "list");
    }
  }

  private ArrayList<DfeList> getDfeLists()
  {
    Object localObject;
    if ((this.mTabDataList == null) || (this.mTabDataList.isEmpty()))
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = Lists.newArrayList();
      Iterator localIterator = this.mTabDataList.iterator();
      while (localIterator.hasNext())
        ((ArrayList)localObject).add(((TabType)localIterator.next()).tabListData);
    }
  }

  private ArrayList<Bundle> getTabInstanceStates()
  {
    Object localObject;
    if ((this.mTabDataList == null) || (this.mTabDataList.isEmpty()))
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = new ArrayList();
      Iterator localIterator = this.mTabDataList.iterator();
      while (localIterator.hasNext())
      {
        TabType localTabType = (TabType)localIterator.next();
        if (localTabType.slidingPanelTab != null)
          ((ArrayList)localObject).add(localTabType.slidingPanelTab.onSaveInstanceState());
        else
          ((ArrayList)localObject).add(localTabType.tabBundle);
      }
    }
  }

  private List<String> getTitles()
  {
    ArrayList localArrayList = Lists.newArrayList();
    if (this.mDfeBrowse.hasCategories())
    {
      if (this.mBackendId != 2)
        break label132;
      localArrayList.add(this.mContext.getString(2131165624).toUpperCase());
    }
    while (true)
    {
      if (this.mPromoData != null)
        localArrayList.add(((Bucket)this.mPromoData.getBucketList().get(0)).getTitle().toUpperCase());
      if (this.mContentListData == null)
        break;
      for (int i = 0; i < this.mContentListData.getBucketCount(); i++)
        localArrayList.add(((Bucket)this.mContentListData.getBucketList().get(i)).getTitle().toUpperCase());
      label132: localArrayList.add(this.mContext.getString(2131165623).toUpperCase());
    }
    return localArrayList;
  }

  private List<DfeList> restoreDfeLists(Bundle paramBundle)
  {
    ArrayList localArrayList = null;
    if ((paramBundle != null) && (paramBundle.containsKey("TabbedAdapter.TabDfeListParcels")))
    {
      localArrayList = paramBundle.getParcelableArrayList("TabbedAdapter.TabDfeListParcels");
      if (localArrayList != null)
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          DfeList localDfeList = (DfeList)localIterator.next();
          if (localDfeList != null)
            localDfeList.setDfeApi(this.mDfeApi);
        }
      }
    }
    return localArrayList;
  }

  private List<Bundle> restoreScrollPositions(Bundle paramBundle)
  {
    ArrayList localArrayList = null;
    if ((paramBundle != null) && (paramBundle.containsKey("TabbedAdapter.TabParcels")))
      localArrayList = paramBundle.getParcelableArrayList("TabbedAdapter.TabParcels");
    return localArrayList;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    ViewPagerTab localViewPagerTab = (ViewPagerTab)paramObject;
    ((ViewPager)paramViewGroup).removeView(localViewPagerTab.getView(this.mBackendId));
    TabType localTabType = (TabType)this.mTabDataList.get(paramInt);
    if (localTabType.tabListData != null)
      localTabType.tabListData.flushUnusedPages();
    TabType.access$002(localTabType, localTabType.slidingPanelTab.onSaveInstanceState());
    localTabType.slidingPanelTab = null;
    localViewPagerTab.onDestroy();
  }

  public void finishUpdate(ViewGroup paramViewGroup)
  {
  }

  public int getCount()
  {
    int i = 1;
    int j = 0;
    int k;
    if (this.mDfeBrowse.hasCategories())
    {
      k = i;
      if (!this.mDfeBrowse.hasPromotionalItems())
        break label56;
    }
    while (true)
    {
      int m = k + i;
      if (this.mContentListData != null)
        j = this.mContentListData.getBucketCount();
      return m + j;
      k = 0;
      break;
      label56: i = 0;
    }
  }

  public String getPageTitle(int paramInt)
  {
    if (paramInt >= this.mTabTitles.size());
    for (String str = ""; ; str = (String)this.mTabTitles.get(paramInt))
      return str;
  }

  public float getPageWidth(int paramInt)
  {
    if ((paramInt == 0) && (this.mTabDataList.size() > 0) && (((TabType)this.mTabDataList.get(0)).type == 0));
    for (float f = this.mContext.getResources().getInteger(2131492888) / 100.0F; ; f = 1.0F)
      return f;
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    Object localObject = null;
    TabType localTabType = (TabType)this.mTabDataList.get(paramInt);
    switch (localTabType.type)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      localTabType.slidingPanelTab = ((ViewPagerTab)localObject);
      paramViewGroup.addView(((ViewPagerTab)localObject).getView(this.mBackendId));
      return localObject;
      localObject = new CategoryTab(this.mContext, this.mLayoutInflater, this.mNavigationManager, this.mBitmapLoader, this.mDfeBrowse, this.mCurrentPageUrl, this.mDfeToc);
      ((ViewPagerTab)localObject).onRestoreInstanceState(localTabType.tabBundle);
      continue;
      if (localTabType.tabListData == null)
        localTabType.tabListData = this.mDfeBrowse.buildPromoList(this.mDfeApi);
      localObject = new GridFeaturedTab(this.mContext, this.mLayoutInflater, this.mBitmapLoader, this.mNavigationManager, localTabType.tabListData, this.mReferrerUrl, this.mCurrentPageUrl, this.mDfeToc);
      ((ViewPagerTab)localObject).onRestoreInstanceState(localTabType.tabBundle);
      continue;
      if (localTabType.tabListData == null)
        localTabType.tabListData = getDfeListForListTab(paramInt - this.mNonListTabCount);
      localObject = new ListTab(this.mContext, this.mNavigationManager, this.mBitmapLoader, this.mDfeApi, this.mLayoutInflater, localTabType.tabListData, this.mReferrerUrl, this.mCurrentPageUrl, this.mDfeToc, this.mBackendId);
      ((ViewPagerTab)localObject).onRestoreInstanceState(localTabType.tabBundle);
    }
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    if (((ViewPagerTab)paramObject).getView(this.mBackendId) == paramView);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onPageSelected(int paramInt)
  {
    int i = 0;
    if (i < this.mTabDataList.size())
    {
      ViewPagerTab localViewPagerTab;
      if (((TabType)this.mTabDataList.get(i)).slidingPanelTab != null)
      {
        localViewPagerTab = ((TabType)this.mTabDataList.get(i)).slidingPanelTab;
        if (i != paramInt)
          break label73;
      }
      label73: for (boolean bool = true; ; bool = false)
      {
        localViewPagerTab.setTabSelected(bool);
        i++;
        break;
      }
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putParcelableArrayList("TabbedAdapter.TabParcels", getTabInstanceStates());
    paramBundle.putParcelableArrayList("TabbedAdapter.TabDfeListParcels", getDfeLists());
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
  }

  public Parcelable saveState()
  {
    return null;
  }

  public void startUpdate(ViewGroup paramViewGroup)
  {
  }

  private static class TabType
  {
    public ViewPagerTab slidingPanelTab;
    private Bundle tabBundle;
    public DfeList tabListData;
    public final int type;

    public TabType(int paramInt)
    {
      this.type = paramInt;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.TabbedAdapter
 * JD-Core Version:    0.6.2
 */
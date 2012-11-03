package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ViewPagerTab;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAppsTabbedAdapter extends PagerAdapter
{
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private final DfeApi mDfeApi;
  private final DfeToc mDfeToc;
  private final MyAppsTabbedFragment mFragment;
  private final boolean mHasSubscriptions;
  private final NavigationManager mNavigationManager;
  protected final List<TabType> mTabDataList = Lists.newArrayList();
  private final List<String> mTabTitles;

  public MyAppsTabbedAdapter(Context paramContext, NavigationManager paramNavigationManager, DfeApi paramDfeApi, DfeToc paramDfeToc, BitmapLoader paramBitmapLoader, boolean paramBoolean, Bundle paramBundle, MyAppsTabbedFragment paramMyAppsTabbedFragment)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mDfeApi = paramDfeApi;
    this.mDfeToc = paramDfeToc;
    this.mHasSubscriptions = paramBoolean;
    this.mFragment = paramMyAppsTabbedFragment;
    generateTabList(paramBundle);
    this.mTabTitles = getTitles();
  }

  private void generateTabList(Bundle paramBundle)
  {
    int i = 1;
    List localList = restoreTabBundles(paramBundle);
    this.mTabDataList.clear();
    if (this.mHasSubscriptions)
      this.mTabDataList.add(new TabType(0));
    this.mTabDataList.add(new TabType(i));
    this.mTabDataList.add(new TabType(2));
    if ((localList != null) && (localList.size() == this.mTabDataList.size()));
    while (true)
    {
      for (int j = 0; j < this.mTabDataList.size(); j++)
        if (i != 0)
          TabType.access$002((TabType)this.mTabDataList.get(j), (Bundle)localList.get(j));
      i = 0;
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
    if (this.mHasSubscriptions)
      localArrayList.add(this.mContext.getString(2131165787).toUpperCase());
    localArrayList.add(this.mContext.getString(2131165785).toUpperCase());
    localArrayList.add(this.mContext.getString(2131165786).toUpperCase());
    return localArrayList;
  }

  private List<Bundle> restoreTabBundles(Bundle paramBundle)
  {
    ArrayList localArrayList = null;
    if ((paramBundle != null) && (paramBundle.containsKey("MyAppsTabbedAdapter.TabParcels")))
      localArrayList = paramBundle.getParcelableArrayList("MyAppsTabbedAdapter.TabParcels");
    return localArrayList;
  }

  public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
  {
    ViewPagerTab localViewPagerTab = (ViewPagerTab)paramObject;
    ((ViewPager)paramViewGroup).removeView(localViewPagerTab.getView(3));
    TabType localTabType = (TabType)this.mTabDataList.get(paramInt);
    TabType.access$002(localTabType, localTabType.slidingPanelTab.onSaveInstanceState());
    localTabType.slidingPanelTab = null;
    localViewPagerTab.onDestroy();
  }

  public void finishUpdate(ViewGroup paramViewGroup)
  {
  }

  public int getCount()
  {
    return this.mTabDataList.size();
  }

  public String getPageTitle(int paramInt)
  {
    return (String)this.mTabTitles.get(paramInt);
  }

  public int getTabType(int paramInt)
  {
    return ((TabType)this.mTabDataList.get(paramInt)).type;
  }

  public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
  {
    TabType localTabType = (TabType)this.mTabDataList.get(paramInt);
    Object localObject = localTabType.slidingPanelTab;
    if (localObject == null)
      switch (localTabType.type)
      {
      default:
      case 1:
      case 2:
      case 0:
      }
    while (true)
    {
      localTabType.slidingPanelTab = ((MyAppsTab)localObject);
      paramViewGroup.addView(((MyAppsTab)localObject).getView(3));
      ((MyAppsTab)localObject).loadData();
      return localObject;
      localObject = new MyAppsInstalledTab(this.mContext, this.mBitmapLoader, this.mDfeApi, this.mFragment, localTabType.tabBundle);
      continue;
      AccountLibrary localAccountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(this.mDfeApi.getAccount());
      localObject = new MyAppsLibraryTab(this.mContext, this.mFragment, this.mNavigationManager, this.mBitmapLoader, this.mDfeApi, this.mFragment, this.mDfeToc, localAccountLibrary, localTabType.tabBundle);
      continue;
      localObject = new MyAppsSubscriptionsTab(this.mContext, this.mBitmapLoader, this.mDfeApi, this.mFragment, localTabType.tabBundle);
    }
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    if (((ViewPagerTab)paramObject).getView(3) == paramView);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onPageSelected(int paramInt)
  {
    int i = 0;
    if (i < this.mTabDataList.size())
    {
      MyAppsTab localMyAppsTab;
      if (((TabType)this.mTabDataList.get(i)).slidingPanelTab != null)
      {
        localMyAppsTab = ((TabType)this.mTabDataList.get(i)).slidingPanelTab;
        if (i != paramInt)
          break label71;
      }
      label71: for (boolean bool = true; ; bool = false)
      {
        localMyAppsTab.setTabSelected(bool);
        i++;
        break;
      }
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putParcelableArrayList("MyAppsTabbedAdapter.TabParcels", getTabInstanceStates());
  }

  public void refreshAllTabs()
  {
    for (int i = 0; i < this.mTabDataList.size(); i++)
    {
      MyAppsTab localMyAppsTab = ((TabType)this.mTabDataList.get(i)).slidingPanelTab;
      if ((localMyAppsTab instanceof MyAppsTab))
        ((MyAppsTab)localMyAppsTab).requestData();
    }
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

  public static class TabType
  {
    public MyAppsTab slidingPanelTab;
    private Bundle tabBundle;
    public final int type;

    public TabType(int paramInt)
    {
      this.type = paramInt;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsTabbedAdapter
 * JD-Core Version:    0.6.2
 */
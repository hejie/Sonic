package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.SearchAdapter;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeSearch;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.utils.FinskyLog;
import java.util.List;

public class SearchFragment extends PageFragment
  implements OnDataChangedListener
{
  private SearchAdapter mAdapter;
  private boolean mAdapterSet;
  private int mBackendId;
  private ListView mListView;
  private int mNumCellsTallSearch;
  private int mNumCellsWideSearch;
  private String mQuery;
  private String mReferrerUrl;
  private DfeSearch mSearchData;
  private String mSearchUrl;

  public static SearchFragment newInstance(String paramString1, String paramString2, String paramString3)
  {
    SearchFragment localSearchFragment = new SearchFragment();
    localSearchFragment.setDfeToc(FinskyApp.get().getToc());
    localSearchFragment.setArgument("finsky.PageFragment.SearchFragment.query", paramString1);
    localSearchFragment.setArgument("finsky.PageFragment.SearchFragment.referrer", paramString3);
    localSearchFragment.setArgument("finsky.PageFragment.SearchFragment.searchUrl", paramString2);
    return localSearchFragment;
  }

  protected int getLayoutRes()
  {
    return 2130968608;
  }

  public String getQuery()
  {
    return this.mQuery;
  }

  protected boolean isDataReady()
  {
    if ((this.mSearchData != null) && (this.mSearchData.isReady()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void logListView()
  {
    if (!isDataReady());
    while (true)
    {
      return;
      for (int i = 0; i < this.mSearchData.getBucketCount(); i++)
      {
        String str = ((Bucket)this.mSearchData.getBucketList().get(i)).getAnalyticsCookie();
        FinskyApp.get().getAnalytics().logListViewOnPage(this.mReferrerUrl, null, this.mSearchData.getUrl(), str);
        FinskyApp.get().getEventLogger().logView(this.mSearchData.getUrl(), str, this.mSearchData.getUrl());
      }
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (paramBundle != null)
    {
      this.mBackendId = paramBundle.getInt("backend_id", 0);
      this.mPageFragmentHost.updateCurrentBackendId(this.mBackendId);
    }
    this.mListView = ((ListView)this.mDataView.findViewById(2131230821));
    this.mListView.setVisibility(0);
    this.mListView.setItemsCanFocus(true);
    TextView localTextView = (TextView)this.mDataView.findViewById(2131231110);
    Resources localResources = getResources();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.mQuery;
    localTextView.setText(localResources.getString(2131165431, arrayOfObject));
    this.mNumCellsWideSearch = getResources().getInteger(2131492878);
    this.mNumCellsTallSearch = getResources().getInteger(2131492877);
    rebindActionBar();
    this.mAdapterSet = false;
    if (!isDataReady())
    {
      switchToLoading();
      requestData();
    }
    rebindAdapter();
    FinskyApp.get().getAnalytics().logPageView(this.mReferrerUrl, null, this.mSearchData.getUrl());
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mQuery = getArguments().getString("finsky.PageFragment.SearchFragment.query");
    this.mReferrerUrl = getArguments().getString("finsky.PageFragment.SearchFragment.referrer");
    this.mSearchUrl = getArguments().getString("finsky.PageFragment.SearchFragment.searchUrl");
  }

  public void onDataChanged()
  {
    super.onDataChanged();
    logListView();
    rebindAdapter();
    rebindActionBar();
  }

  public void onDestroy()
  {
    if (this.mSearchData != null)
    {
      this.mSearchData.removeDataChangedListener(this);
      this.mSearchData.removeErrorListener(this);
      this.mSearchData = null;
    }
    if (this.mAdapter != null)
      this.mAdapter.onDestroy();
    super.onDestroy();
  }

  public void onDestroyView()
  {
    this.mListView = null;
    if (this.mAdapter != null)
      this.mAdapter.onDestroyView();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("backend_id", this.mBackendId);
  }

  public void rebindActionBar()
  {
    if (!isDataReady());
    while (true)
    {
      return;
      this.mBackendId = this.mSearchData.getBackendId();
      if ((this.mBackendId != 3) || (!this.mQuery.startsWith("pub:")))
        break;
      Resources localResources2 = this.mContext.getResources();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.mQuery.replaceFirst("pub:", "");
      String str = localResources2.getString(2131165754, arrayOfObject2);
      this.mPageFragmentHost.updateBreadcrumb(str);
      ((MainActivity)this.mPageFragmentHost).getCustomActionBar().updateSearchQuery(this.mSearchData.getQuery());
      this.mPageFragmentHost.updateCurrentBackendId(this.mBackendId);
    }
    Resources localResources1 = getResources();
    if (localResources1.getBoolean(2131296262));
    for (int i = 2131165749; ; i = 2131165748)
    {
      PageFragmentHost localPageFragmentHost = this.mPageFragmentHost;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = this.mQuery;
      localPageFragmentHost.updateBreadcrumb(localResources1.getString(i, arrayOfObject1));
      break;
    }
  }

  public void rebindAdapter()
  {
    if (this.mListView == null)
      FinskyLog.w("List view null, ignoring.", new Object[0]);
    label133: label145: 
    while (true)
    {
      return;
      if (isDataReady())
      {
        if (this.mAdapter == null)
        {
          String str = this.mSearchData.getUrl();
          this.mAdapter = new SearchAdapter(this.mContext, this.mNavigationManager, this.mBitmapLoader, getToc(), this.mSearchData, this.mNumCellsWideSearch, this.mNumCellsTallSearch, 2130968705, str);
        }
        if (this.mAdapterSet)
          break label133;
        this.mListView.setAdapter(this.mAdapter);
        this.mAdapterSet = true;
      }
      while (true)
      {
        if (!isAdded())
          break label145;
        this.mListView.setEmptyView(this.mDataView.findViewById(2131230994));
        break;
        break;
        this.mAdapter.updateAdapterData(this.mSearchData);
      }
    }
  }

  protected void rebindViews()
  {
  }

  public void refresh()
  {
    if (isDataReady())
      logListView();
    while (true)
    {
      return;
      this.mSearchData.clearTransientState();
      super.refresh();
    }
  }

  protected void requestData()
  {
    if (this.mSearchData == null)
    {
      this.mSearchData = new DfeSearch(this.mDfeApi, this.mQuery, this.mSearchUrl);
      this.mSearchData.addDataChangedListener(this);
      this.mSearchData.addErrorListener(this);
    }
    this.mSearchData.startLoadItems();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SearchFragment
 * JD-Core Version:    0.6.2
 */
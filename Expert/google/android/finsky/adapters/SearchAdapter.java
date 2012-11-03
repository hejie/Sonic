package com.google.android.finsky.adapters;

import android.content.Context;
import android.provider.SearchRecentSuggestions;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeSearch;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.SuggestionBarLayout;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Search.RelatedSearch;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.util.List;

public class SearchAdapter extends BucketedListAdapter
{
  private DfeSearch mDfeSearch;
  private Spinner mSpinner;
  private SearchSpinnerAdapter mSpinnerAdapter;

  public SearchAdapter(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc, DfeSearch paramDfeSearch, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    super(paramContext, paramNavigationManager, paramBitmapLoader, paramDfeToc, paramDfeSearch, paramInt1, paramInt2, paramInt3, paramString);
    this.mDfeSearch = paramDfeSearch;
    ignoreCustomTiles();
  }

  private void bindSuggestionHeader(SuggestionBarLayout paramSuggestionBarLayout)
  {
    boolean bool1 = true;
    String str = this.mDfeSearch.getSuggestedQuery();
    paramSuggestionBarLayout.bind(str);
    View.OnClickListener localOnClickListener = makeSuggestionClickListener(str);
    paramSuggestionBarLayout.setOnClickListener(localOnClickListener);
    boolean bool2;
    if (localOnClickListener != null)
    {
      bool2 = bool1;
      paramSuggestionBarLayout.setClickable(bool2);
      if (localOnClickListener == null)
        break label59;
    }
    while (true)
    {
      paramSuggestionBarLayout.setFocusable(bool1);
      return;
      bool2 = false;
      break;
      label59: bool1 = false;
    }
  }

  private View getSuggestionHeaderView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    for (View localView = inflate(2130968841, paramViewGroup, false); ; localView = paramView)
    {
      bindSuggestionHeader((SuggestionBarLayout)localView);
      return localView;
    }
  }

  private View getSwitcherHeader(View paramView, ViewGroup paramViewGroup)
  {
    int i = this.mDfeSearch.getBackendId();
    List localList1;
    if (paramView == null)
    {
      if (this.mDfeSearch.isAggregateResult())
        paramView = inflate(2130968607, paramViewGroup, false);
    }
    else
    {
      if (this.mSpinnerAdapter == null)
      {
        List localList2 = this.mDfeSearch.getRelatedSearches();
        this.mSpinnerAdapter = new SearchSpinnerAdapter(paramView.getContext(), localList2);
      }
      if (this.mSpinner == null)
      {
        this.mSpinner = ((Spinner)paramView.findViewById(2131230817));
        this.mSpinner.setVisibility(0);
        this.mSpinner.setAdapter(this.mSpinnerAdapter);
        localList1 = this.mDfeSearch.getRelatedSearches();
      }
    }
    for (int j = 0; ; j++)
      if (j < localList1.size())
      {
        if (((Search.RelatedSearch)localList1.get(j)).getCurrent())
          this.mSpinner.setSelection(j);
      }
      else
      {
        this.mSpinner.setBackgroundResource(CorpusResourceUtils.getCorpusSpinnerDrawable(i));
        this.mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            if (SearchAdapter.this.mSpinner == null);
            while (true)
            {
              return;
              Search.RelatedSearch localRelatedSearch = (Search.RelatedSearch)SearchAdapter.this.mSpinner.getAdapter().getItem(paramAnonymousInt);
              if ((SearchAdapter.this.mSpinner.getVisibility() == 0) && (!localRelatedSearch.getCurrent()) && (SearchAdapter.this.mNavigationManager.getCurrentPageType() == 7))
                SearchAdapter.this.mNavigationManager.goToSearch(SearchAdapter.this.mDfeSearch.getQuery(), localRelatedSearch.getSearchUrl(), null);
            }
          }

          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
          {
          }
        });
        View localView1 = paramView.findViewById(2131230820);
        if (localView1 != null)
          localView1.setBackgroundColor(CorpusResourceUtils.getBackendHintColor(this.mContext, i));
        View localView2 = paramView.findViewById(2131230819);
        if (localView2 != null)
          localView2.setVisibility(8);
        return paramView;
        paramView = inflate(2130968606, paramViewGroup, false);
        break;
      }
  }

  private View.OnClickListener makeSuggestionClickListener(final String paramString)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FinskyApp.get().getRecentSuggestions().saveRecentQuery(paramString, null);
        SearchAdapter.this.mNavigationManager.searchFromSuggestion(paramString, SearchAdapter.this.mDfeSearch.getBackendId(), SearchAdapter.this.mCurrentPageUrl);
      }
    };
  }

  private boolean shouldShowSuggestionHeader()
  {
    if (!TextUtils.isEmpty(this.mDfeSearch.getSuggestedQuery()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean shouldShowSwitcher()
  {
    int i = 1;
    if ((this.mDfeSearch.getBucketCount() == i) || (this.mDfeSearch.getBackendId() != 0));
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  protected int getBucketHeaderLayoutId(Bucket paramBucket)
  {
    if (this.mDfeSearch.isAggregateResult());
    for (int i = 2130968606; ; i = 2130968607)
      return i;
  }

  public int getCount()
  {
    int i = 1;
    int j = super.getCount();
    int k;
    int m;
    if (shouldShowSwitcher())
    {
      k = i;
      m = k + j;
      if (!shouldShowSuggestionHeader())
        break label38;
    }
    while (true)
    {
      return m + i;
      k = 0;
      break;
      label38: i = 0;
    }
  }

  public int getItemViewType(int paramInt)
  {
    int i = 5;
    int j = 1;
    if ((shouldShowSwitcher()) && (shouldShowSuggestionHeader()))
      if (paramInt != 0);
    do
    {
      while (true)
      {
        return i;
        if (paramInt != j)
          break;
        i = 6;
      }
      if (!shouldShowSuggestionHeader())
        break label81;
    }
    while (paramInt == 0);
    label46: int k;
    label56: int m;
    if (shouldShowSwitcher())
    {
      k = j;
      m = paramInt - k;
      if (!shouldShowSuggestionHeader())
        break label104;
    }
    while (true)
    {
      i = super.getItemViewType(m - j);
      break;
      label81: if ((!shouldShowSwitcher()) || (paramInt != 0))
        break label46;
      i = 6;
      break;
      k = 0;
      break label56;
      label104: j = 0;
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 1;
    int j;
    int k;
    switch (getItemViewType(paramInt))
    {
    default:
      if (shouldShowSwitcher())
      {
        j = i;
        k = paramInt - j;
        if (!shouldShowSuggestionHeader())
          break label109;
      }
      break;
    case 5:
    case 6:
    }
    while (true)
    {
      int m = k - i;
      if (m < 0)
        break label115;
      View localView = super.getView(m, paramView, paramViewGroup);
      while (true)
      {
        return localView;
        localView = getSuggestionHeaderView(paramView, paramViewGroup);
        continue;
        localView = getSwitcherHeader(paramView, paramViewGroup);
      }
      j = 0;
      break;
      label109: i = 0;
    }
    label115: throw new IllegalStateException("Uncaught view type=[" + getItemViewType(paramInt) + "] position=[" + paramInt + "]");
  }

  public int getViewTypeCount()
  {
    return 7;
  }

  public boolean isEmpty()
  {
    int i = 1;
    int j = getCount();
    if (shouldShowSwitcher())
    {
      int k = i;
      int n = j - k;
      if (!shouldShowSuggestionHeader())
        break label46;
      int i1 = i;
      label31: if (n - i1 != 0)
        break label52;
    }
    while (true)
    {
      return i;
      int m = 0;
      break;
      label46: int i2 = 0;
      break label31;
      label52: i = 0;
    }
  }

  protected View.OnClickListener makeHeaderClickListener(final Bucket paramBucket)
  {
    if (!TextUtils.isEmpty(paramBucket.getBrowseUrl()));
    for (View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchAdapter.this.mNavigationManager.goToSearch(SearchAdapter.this.mDfeSearch.getQuery(), paramBucket.getBrowseUrl(), SearchAdapter.this.mCurrentPageUrl);
      }
    }
    ; ; local1 = null)
      return local1;
  }

  public void onDestroyView()
  {
    this.mSpinner = null;
    super.onDestroyView();
  }

  public void updateAdapterData(BucketedList<?> paramBucketedList)
  {
    super.updateAdapterData(paramBucketedList);
    this.mDfeSearch = ((DfeSearch)paramBucketedList);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.SearchAdapter
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.adapters.PaginatedListAdapter;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.layout.AccountSelectorView;
import com.google.android.finsky.layout.OverviewBucketEntry;
import com.google.android.finsky.layout.OverviewBucketEntry.OnActionListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;

public class MyAppsLibraryAdapter extends PaginatedListAdapter
  implements MyAppsListAdapter
{
  private static boolean sEnableRemoveAppsFromLibrary = ((Boolean)G.enableRemoveAppsFromLibrary.get()).booleanValue();
  private final BitmapLoader mBitmapLoader;
  private boolean mHasAccountSwitcher;
  private final boolean mHasDocumentView;
  private DfeList mList;
  private final OverviewBucketEntry.OnActionListener mOnActionListener;
  private final View.OnClickListener mOnClickListener;
  private final View.OnLongClickListener mOnLongClickListener;

  public MyAppsLibraryAdapter(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, View.OnClickListener paramOnClickListener, OverviewBucketEntry.OnActionListener paramOnActionListener, View.OnLongClickListener paramOnLongClickListener, boolean paramBoolean)
  {
    super(paramContext, paramNavigationManager, false, true);
    this.mBitmapLoader = paramBitmapLoader;
    this.mHasDocumentView = paramBoolean;
    this.mOnClickListener = paramOnClickListener;
    this.mOnActionListener = paramOnActionListener;
    this.mOnLongClickListener = paramOnLongClickListener;
  }

  private View getAccountSwitcherView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = inflate(2130968735, paramViewGroup, false);
    ((AccountSelectorView)paramView.findViewById(2131230995)).configure((AuthenticatedActivity)this.mContext);
    return paramView;
  }

  private View getDocView(Document paramDocument, View paramView, ViewGroup paramViewGroup)
  {
    int i;
    OverviewBucketEntry localOverviewBucketEntry;
    if (paramView == null)
    {
      if (this.mHasDocumentView)
      {
        i = 2130968673;
        paramView = inflate(i, paramViewGroup, false);
      }
    }
    else
    {
      localOverviewBucketEntry = (OverviewBucketEntry)paramView;
      localOverviewBucketEntry.setRightSeparatorVisibility(false);
      if (paramDocument != null)
        break label73;
      localOverviewBucketEntry.setMockDocument(3);
      localOverviewBucketEntry.setOnClickListener(null);
      localOverviewBucketEntry.setOnLongClickListener(null);
    }
    while (true)
    {
      localOverviewBucketEntry.setTag(paramDocument);
      return paramView;
      i = 2130968705;
      break;
      label73: localOverviewBucketEntry.bind(null, paramDocument, this.mBitmapLoader, false, this.mOnClickListener);
      if (sEnableRemoveAppsFromLibrary)
        if (AppActionAnalyzer.canRemoveFromLibrary(paramDocument))
        {
          localOverviewBucketEntry.configureActionPack(2130837665, this.mOnActionListener, 2131165847);
          localOverviewBucketEntry.setOnLongClickListener(this.mOnLongClickListener);
        }
        else
        {
          localOverviewBucketEntry.hideActionPack();
          localOverviewBucketEntry.setOnLongClickListener(null);
        }
    }
  }

  public static Document getViewDoc(View paramView)
  {
    return (Document)paramView.getTag();
  }

  public int getCount()
  {
    if (this.mList == null);
    for (int i = 0; ; i++)
      do
      {
        return i;
        i = this.mList.getCount();
        if (isMoreDataAvailable())
          i++;
      }
      while ((i <= 0) || (!this.mHasAccountSwitcher));
  }

  public Document getDocument(int paramInt)
  {
    return (Document)getItem(paramInt);
  }

  public Object getItem(int paramInt)
  {
    if ((this.mHasAccountSwitcher) && (paramInt == 0));
    for (Object localObject = null; ; localObject = this.mList.getItem(paramInt))
    {
      return localObject;
      if (this.mHasAccountSwitcher)
        paramInt--;
    }
  }

  public int getItemViewType(int paramInt)
  {
    int i = 0;
    if (paramInt == -1 + getCount())
      switch (1.$SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[getFooterMode().ordinal()])
      {
      default:
        throw new IllegalStateException("No footer or item at row " + paramInt);
      case 1:
        i = 1;
      case 3:
      case 2:
      }
    while (true)
    {
      return i;
      i = 2;
      continue;
      if ((this.mHasAccountSwitcher) && (paramInt == 0))
        i = 3;
    }
  }

  protected String getLastRequestError()
  {
    return ErrorStrings.get(this.mContext, this.mList.getVolleyError());
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    switch (getItemViewType(paramInt))
    {
    default:
      throw new IllegalStateException("Unknown type for getView " + paramInt);
    case 3:
      localView = getAccountSwitcherView(paramView, paramViewGroup);
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return localView;
      localView = getDocView((Document)getItem(paramInt), paramView, paramViewGroup);
      continue;
      localView = getLoadingFooterView(paramView, paramViewGroup);
      continue;
      localView = getErrorFooterView(paramView, paramViewGroup);
    }
  }

  public int getViewTypeCount()
  {
    return 4;
  }

  protected boolean isMoreDataAvailable()
  {
    if ((this.mList != null) && (this.mList.isMoreAvailable()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void retryLoadingItems()
  {
    if (this.mList != null)
      this.mList.retryLoadItems();
  }

  public void setDfeList(DfeList paramDfeList)
  {
    this.mList = paramDfeList;
    notifyDataSetChanged();
  }

  public void showAccountSwitcher()
  {
    this.mHasAccountSwitcher = true;
    notifyDataSetChanged();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsLibraryAdapter
 * JD-Core Version:    0.6.2
 */
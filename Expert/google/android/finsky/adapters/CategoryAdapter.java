package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Browse.BrowseLink;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.util.List;

public class CategoryAdapter extends BaseAdapter
{
  private final int mBackendId;
  private final List<Browse.BrowseLink> mCategories;
  private final String mCurrentBrowseUrl;
  private final LayoutInflater mLayoutInflater;
  private final NavigationManager mNavigationManager;
  private final int mTextColor;
  private final DfeToc mToc;

  public CategoryAdapter(Context paramContext, DfeBrowse paramDfeBrowse, NavigationManager paramNavigationManager, int paramInt, String paramString, DfeToc paramDfeToc)
  {
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mCategories = paramDfeBrowse.getCategoryList();
    this.mNavigationManager = paramNavigationManager;
    this.mBackendId = paramInt;
    this.mCurrentBrowseUrl = paramString;
    this.mTextColor = CorpusResourceUtils.getBackendHintColor(paramContext, paramInt);
    this.mToc = paramDfeToc;
  }

  public int getCount()
  {
    return this.mCategories.size();
  }

  public Object getItem(int paramInt)
  {
    return this.mCategories.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    final Browse.BrowseLink localBrowseLink = (Browse.BrowseLink)getItem(paramInt);
    if (paramView == null)
      paramView = this.mLayoutInflater.inflate(2130968616, paramViewGroup, false);
    ViewHolder localViewHolder = (ViewHolder)paramView.getTag();
    if (localViewHolder == null)
    {
      localViewHolder = new ViewHolder(null);
      localViewHolder.title = ((TextView)paramView.findViewById(2131230847));
      paramView.setTag(localViewHolder);
    }
    localViewHolder.title.setTextColor(this.mTextColor);
    localViewHolder.title.setText(localBrowseLink.getName().toUpperCase());
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoryAdapter.this.mNavigationManager.goBrowse(localBrowseLink.getDataUrl(), localBrowseLink.getName(), CategoryAdapter.this.mBackendId, CategoryAdapter.this.mCurrentBrowseUrl, CategoryAdapter.this.mToc);
      }
    });
    localViewHolder.title.setContentDescription(localBrowseLink.getName());
    return paramView;
  }

  private static class ViewHolder
  {
    TextView title;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.CategoryAdapter
 * JD-Core Version:    0.6.2
 */
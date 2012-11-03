package com.google.android.finsky.activities;

import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.adapters.CategoryAdapter;
import com.google.android.finsky.api.model.DfeBrowse;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.fragments.ViewBinder;

public class CategoryViewBinder extends ViewBinder<DfeBrowse>
{
  CategoryAdapter mAdapter;

  public void bind(ViewGroup paramViewGroup, int paramInt, String paramString, DfeToc paramDfeToc)
  {
    ListView localListView = (ListView)paramViewGroup.findViewById(2131230821);
    this.mAdapter = new CategoryAdapter(this.mContext, (DfeBrowse)this.mData, this.mNavManager, paramInt, paramString, paramDfeToc);
    localListView.setAdapter(this.mAdapter);
    localListView.setItemsCanFocus(true);
  }

  public void onDestroy()
  {
    this.mAdapter = null;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.CategoryViewBinder
 * JD-Core Version:    0.6.2
 */
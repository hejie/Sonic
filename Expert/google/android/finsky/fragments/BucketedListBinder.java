package com.google.android.finsky.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.adapters.BucketedListAdapter;
import com.google.android.finsky.adapters.EditorialBucketListAdapter;
import com.google.android.finsky.adapters.RecommendationsListAdapter;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.EditorialPageHeader;
import com.google.android.finsky.remoting.protos.DocAnnotations.EditorialSeriesContainer;

public class BucketedListBinder extends ViewBinder<BucketedList<?>>
  implements Response.ErrorListener, OnDataChangedListener
{
  private BucketedListAdapter mAdapter;
  private final int mCellLayoutId;
  private ViewGroup mContentLayout;
  private DfeApi mDfeApi;
  private boolean mHasLoadedAtLeastOnce;
  private ListView mListView;
  protected DfeToc mToc;

  public BucketedListBinder(int paramInt, DfeToc paramDfeToc, DfeApi paramDfeApi)
  {
    this.mCellLayoutId = paramInt;
    this.mToc = paramDfeToc;
    this.mDfeApi = paramDfeApi;
  }

  private void detachFromData()
  {
    if (this.mData != null)
    {
      ((BucketedList)this.mData).removeDataChangedListener(this);
      ((BucketedList)this.mData).removeErrorListener(this);
      this.mData = null;
    }
  }

  private BucketedListAdapter getAdapterForBucketType(Bucket paramBucket, int paramInt1, int paramInt2, String paramString, Bundle paramBundle)
  {
    int i;
    Object localObject;
    if (((BucketedList)this.mData).getBucketCount() == 1)
    {
      i = 1;
      if ((i == 0) || (!paramBucket.hasRecommendationsTemplate()))
        break label80;
      localObject = new RecommendationsListAdapter(this.mContext, this.mNavManager, this.mDfeApi, this.mBitmapLoader, this.mToc, (BucketedList)this.mData, paramString, this.mListView);
    }
    while (true)
    {
      return localObject;
      i = 0;
      break;
      label80: if ((i != 0) && (paramBucket.hasEditorialSeriesContainer()))
      {
        DocAnnotations.EditorialSeriesContainer localEditorialSeriesContainer = paramBucket.getEditorialSeriesContainer();
        ((EditorialPageHeader)this.mContentLayout.findViewById(2131230991)).showSeriesInfo(localEditorialSeriesContainer.getSeriesTitle(), localEditorialSeriesContainer.getSeriesSubtitle(), localEditorialSeriesContainer.getColorThemeArgb());
        localObject = new EditorialBucketListAdapter(this.mContext, this.mNavManager, this.mBitmapLoader, this.mToc, paramBucket, (BucketedList)this.mData, paramString, paramBundle);
      }
      else
      {
        localObject = new BucketedListAdapter(this.mContext, this.mNavManager, this.mBitmapLoader, this.mToc, (BucketedList)this.mData, paramInt1, paramInt2, this.mCellLayoutId, paramString);
      }
    }
  }

  public void bind(ViewGroup paramViewGroup, int paramInt1, int paramInt2, Bucket paramBucket, String paramString, Bundle paramBundle)
  {
    this.mContentLayout = paramViewGroup;
    this.mListView = ((ListView)paramViewGroup.findViewById(2131230821));
    this.mListView.setItemsCanFocus(true);
    if ((paramBucket == null) || (paramBucket.getChildCount() == 0))
    {
      View localView = this.mContentLayout.findViewById(2131230994);
      if (localView != null)
      {
        this.mListView.setEmptyView(localView);
        configureEmptyUi(localView);
      }
      return;
    }
    if (this.mAdapter != null)
    {
      this.mAdapter.onDestroyView();
      this.mAdapter.onDestroy();
    }
    this.mAdapter = getAdapterForBucketType(paramBucket, paramInt1, paramInt2, paramString, paramBundle);
    if (this.mHasLoadedAtLeastOnce)
      this.mListView.setEmptyView(null);
    while (true)
    {
      this.mListView.setAdapter(this.mAdapter);
      break;
      this.mListView.setEmptyView(this.mContentLayout.findViewById(2131230994));
    }
  }

  protected void configureEmptyUi(View paramView)
  {
  }

  public void onDataChanged()
  {
    if ((!this.mHasLoadedAtLeastOnce) && (this.mListView != null))
    {
      this.mListView.setEmptyView(this.mContentLayout.findViewById(2131230994));
      this.mHasLoadedAtLeastOnce = true;
    }
    if (this.mAdapter != null)
      this.mAdapter.notifyDataSetChanged();
  }

  public void onDestroyView()
  {
    detachFromData();
    if (this.mAdapter != null)
    {
      this.mAdapter.onDestroyView();
      this.mAdapter.onDestroy();
      this.mAdapter = null;
    }
    this.mListView = null;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (this.mListView != null)
      this.mAdapter.triggerFooterErrorMode();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mAdapter != null)
      this.mAdapter.onSaveInstanceState(paramBundle);
  }

  public void setData(BucketedList<?> paramBucketedList)
  {
    detachFromData();
    super.setData(paramBucketedList);
    this.mHasLoadedAtLeastOnce = false;
    if (this.mData != null)
    {
      ((BucketedList)this.mData).addDataChangedListener(this);
      ((BucketedList)this.mData).addErrorListener(this);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.BucketedListBinder
 * JD-Core Version:    0.6.2
 */
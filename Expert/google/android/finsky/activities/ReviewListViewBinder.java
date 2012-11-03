package com.google.android.finsky.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.adapters.ReviewsAdapter;
import com.google.android.finsky.adapters.ReviewsAdapter.ChooseFilterOptionsHandler;
import com.google.android.finsky.adapters.ReviewsAdapter.RateReviewHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.ViewBinder;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.BitmapLoader;

public class ReviewListViewBinder extends ViewBinder<DfeReviews>
  implements OnDataChangedListener, Response.ErrorListener, ReviewsAdapter.RateReviewHandler, ReviewsAdapter.ChooseFilterOptionsHandler
{
  private ReviewsAdapter mAdapter;
  private Fragment mContainerFragment;
  private ViewGroup mContentLayout;
  private Document mDocument;
  private boolean mHasLoadedAtLeastOnce;
  private ListView mReviewList;

  public void bind(View paramView, Document paramDocument)
  {
    this.mContentLayout = ((ViewGroup)paramView);
    this.mReviewList = ((ListView)this.mContentLayout.findViewById(2131231058));
    if (this.mAdapter != null)
      this.mAdapter.onDestroyView();
    this.mAdapter = new ReviewsAdapter(this.mContext, paramDocument, (DfeReviews)this.mData, this, this);
    this.mDocument = paramDocument;
    if (this.mHasLoadedAtLeastOnce)
      this.mReviewList.setEmptyView(null);
    while (true)
    {
      this.mReviewList.setItemsCanFocus(true);
      this.mReviewList.setAdapter(this.mAdapter);
      return;
      this.mReviewList.setEmptyView(this.mContentLayout.findViewById(2131230994));
    }
  }

  public void init(Context paramContext, Fragment paramFragment, DfeApi paramDfeApi, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader)
  {
    DfeReviews localDfeReviews = (DfeReviews)this.mData;
    super.init(paramContext, paramNavigationManager, paramBitmapLoader);
    this.mData = localDfeReviews;
    this.mContainerFragment = paramFragment;
  }

  public void onChooseFilterOptions()
  {
    if (this.mData == null);
    while (true)
    {
      return;
      FragmentManager localFragmentManager = this.mContainerFragment.getFragmentManager();
      if (localFragmentManager.findFragmentByTag("filter_options_dialog") == null)
      {
        FilterOptionsDialog localFilterOptionsDialog = FilterOptionsDialog.newInstance(((DfeReviews)this.mData).currentlyFilteringByVersion(), ((DfeReviews)this.mData).shouldFilterByDevice());
        localFilterOptionsDialog.setTargetFragment(this.mContainerFragment, 0);
        localFilterOptionsDialog.show(localFragmentManager, "filter_options_dialog");
      }
    }
  }

  public void onDataChanged()
  {
    if ((this.mReviewList != null) && (this.mData != null))
    {
      if (this.mHasLoadedAtLeastOnce)
        break label43;
      this.mReviewList.setEmptyView(this.mContentLayout.findViewById(2131230994));
      this.mHasLoadedAtLeastOnce = true;
    }
    while (true)
    {
      return;
      label43: this.mAdapter.notifyDataSetChanged();
    }
  }

  public void onDestroyView()
  {
    if (this.mAdapter != null)
    {
      this.mAdapter.onDestroyView();
      this.mAdapter = null;
    }
    this.mReviewList = null;
    if (this.mData != null)
    {
      ((DfeReviews)this.mData).removeDataChangedListener(this);
      ((DfeReviews)this.mData).removeErrorListener(this);
      this.mData = null;
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (this.mReviewList != null)
      this.mAdapter.triggerFooterErrorMode();
  }

  public void onRateReview(Rev.Review paramReview)
  {
    FragmentManager localFragmentManager = this.mContainerFragment.getFragmentManager();
    if (localFragmentManager.findFragmentByTag("rate_review_dialog") != null);
    while (true)
    {
      return;
      RateReviewDialog localRateReviewDialog = RateReviewDialog.newInstance(this.mDocument.getDocId(), paramReview.getCommentId(), null);
      localRateReviewDialog.setTargetFragment(this.mContainerFragment, 0);
      localRateReviewDialog.show(localFragmentManager, "rate_review_dialog");
    }
  }

  public void setData(DfeReviews paramDfeReviews)
  {
    this.mHasLoadedAtLeastOnce = false;
    if (this.mData != null)
    {
      ((DfeReviews)this.mData).removeDataChangedListener(this);
      ((DfeReviews)this.mData).removeErrorListener(this);
    }
    super.setData(paramDfeReviews);
    ((DfeReviews)this.mData).clearTransientState();
    ((DfeReviews)this.mData).addDataChangedListener(this);
    ((DfeReviews)this.mData).addErrorListener(this);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ReviewListViewBinder
 * JD-Core Version:    0.6.2
 */
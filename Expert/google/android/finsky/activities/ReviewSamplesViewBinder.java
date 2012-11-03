package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.google.android.finsky.adapters.ReviewsAdapter;
import com.google.android.finsky.adapters.ReviewsAdapter.RateReviewHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.HistogramView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Rev.Review;

public class ReviewSamplesViewBinder extends DetailsViewBinder
  implements ReviewsAdapter.RateReviewHandler, OnDataChangedListener
{
  private ReviewsAdapter mAdapter;
  private boolean mAlwaysShowMore;
  private Fragment mContainerFragment;
  private DfeReviews mData;
  private LinearLayout mReviewHolder;

  public void bind(View paramView, Document paramDocument, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramDocument.getReviewsUrl()));
    while (true)
    {
      return;
      super.bind(paramView, paramDocument, 2131165525);
      if (!paramBoolean)
      {
        paramView.setVisibility(8);
      }
      else
      {
        this.mReviewHolder = ((LinearLayout)paramView.findViewById(2131230945));
        HistogramView localHistogramView = (HistogramView)paramView.findViewById(2131231242);
        if (localHistogramView != null)
          localHistogramView.bind(paramDocument);
        refresh();
      }
    }
  }

  public void init(Context paramContext, Fragment paramFragment, DfeApi paramDfeApi, NavigationManager paramNavigationManager)
  {
    super.init(paramContext, paramDfeApi, paramNavigationManager);
    this.mContainerFragment = paramFragment;
    this.mAlwaysShowMore = this.mContext.getResources().getBoolean(2131296258);
  }

  public void invalidateCurrentReviewUrl()
  {
    if (this.mData == null);
    while (true)
    {
      return;
      this.mDfeApi.invalidateReviewsCache(this.mDoc.getReviewsUrl(), this.mData.shouldFilterByDevice(), this.mData.getVersionFilter(), this.mData.getRatingFilter(), this.mData.getSortType(), true);
    }
  }

  public void onDataChanged()
  {
    int i = this.mData.getCount();
    if (i == 0)
    {
      this.mLayout.setVisibility(8);
      return;
    }
    this.mLayout.setVisibility(0);
    View localView1 = this.mLayout.findViewById(2131230946);
    if ((this.mAlwaysShowMore) || (i > 3))
    {
      localView1.setVisibility(0);
      localView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReviewSamplesViewBinder.this.mNavigationManager.goToAllReviews(ReviewSamplesViewBinder.this.mDoc);
        }
      });
    }
    while (true)
    {
      if (this.mReviewHolder.getChildCount() > 1)
        this.mReviewHolder.removeViews(1, -1 + this.mReviewHolder.getChildCount());
      for (int j = 0; j < Math.min(3, i); j++)
      {
        View localView2 = this.mAdapter.getView(j + this.mAdapter.getFirstReviewViewIndex(), null, this.mReviewHolder);
        this.mReviewHolder.addView(localView2);
      }
      break;
      localView1.setVisibility(8);
    }
  }

  public void onDestroyView()
  {
    if (this.mAdapter != null)
      this.mAdapter.onDestroyView();
    if (this.mData != null)
      this.mData.removeDataChangedListener(this);
  }

  public void onRateReview(Rev.Review paramReview)
  {
    FragmentManager localFragmentManager = this.mContainerFragment.getFragmentManager();
    if (localFragmentManager.findFragmentByTag("rate_review_dialog") != null);
    while (true)
    {
      return;
      RateReviewDialog localRateReviewDialog = RateReviewDialog.newInstance(this.mDoc.getDocId(), paramReview.getCommentId(), null);
      localRateReviewDialog.setTargetFragment(this.mContainerFragment, 0);
      localRateReviewDialog.show(localFragmentManager, "rate_review_dialog");
    }
  }

  public void refresh()
  {
    if (this.mDoc == null);
    while (true)
    {
      return;
      if (this.mData != null)
        this.mData.removeDataChangedListener(this);
      this.mData = new DfeReviews(this.mDfeApi, this.mDoc.getReviewsUrl(), this.mDoc.getVersionCode(), false);
      this.mAdapter = new ReviewsAdapter(this.mContext, this.mDoc, this.mData, this, null);
      this.mData.addDataChangedListener(this);
      this.mData.startLoadItems();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ReviewSamplesViewBinder
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeRateReview;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;

public class ReviewsFragment extends PageFragment
  implements FilterOptionsDialog.Listener, RateReviewDialog.Listener
{
  protected DfeDetails mDfeDetails;
  protected Document mDocument;
  private boolean mFilterByDevice;
  private boolean mFilterByVersion;
  protected final ReviewListViewBinder mReviewsBinder = new ReviewListViewBinder();
  private DfeReviews mReviewsData;
  private Bundle mSavedInstanceState = new Bundle();
  private int mSortType;

  public static ReviewsFragment newInstance(Context paramContext, Document paramDocument)
  {
    ReviewsFragment localReviewsFragment = new ReviewsFragment();
    localReviewsFragment.setTheme(2131624002);
    localReviewsFragment.setDfeToc(FinskyApp.get().getToc());
    localReviewsFragment.setArgument("finsky.ReviewsFragment.document", paramDocument);
    return localReviewsFragment;
  }

  private void recordState()
  {
    if (isDataReady())
    {
      this.mSavedInstanceState.putBoolean("finsky.PageFragment.ReviewsFragment.filterByDevice", this.mFilterByDevice);
      this.mSavedInstanceState.putBoolean("finsky.PageFragment.ReviewsFragment.filterByVersion", this.mFilterByVersion);
      if (this.mReviewsData != null)
        this.mSavedInstanceState.putInt("finsky.PageFragment.ReviewsFragment.sortType", this.mReviewsData.getSortType());
    }
  }

  private void reloadReviews()
  {
    FinskyApp.get().getDfeApi().invalidateReviewsCache(this.mDocument.getReviewsUrl(), this.mReviewsData.shouldFilterByDevice(), this.mReviewsData.getVersionFilter(), this.mReviewsData.getRatingFilter(), this.mReviewsData.getSortType(), true);
    this.mReviewsData.resetItems();
    switchToLoading();
    this.mReviewsData.addDataChangedListener(this);
    this.mReviewsData.addErrorListener(this);
    requestData();
  }

  protected int getLayoutRes()
  {
    return 2130968712;
  }

  protected boolean isDataReady()
  {
    if ((this.mReviewsData != null) && (this.mReviewsData.isReady()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if ((paramBundle != null) && (this.mSavedInstanceState.isEmpty()))
      this.mSavedInstanceState = paramBundle;
    this.mFilterByDevice = this.mSavedInstanceState.getBoolean("finsky.PageFragment.ReviewsFragment.filterByDevice", false);
    this.mFilterByVersion = this.mSavedInstanceState.getBoolean("finsky.PageFragment.ReviewsFragment.filterByVersion", false);
    this.mSortType = this.mSavedInstanceState.getInt("finsky.PageFragment.ReviewsFragment.sortType", 2);
    this.mDfeDetails = new DfeDetails(this.mDfeApi, this.mDocument.getDetailsUrl());
    if (this.mReviewsData == null)
    {
      this.mReviewsData = new DfeReviews(this.mDfeApi, this.mDocument.getReviewsUrl(), this.mDocument.getVersionCode(), true);
      this.mReviewsData.addDataChangedListener(this);
      this.mReviewsData.addErrorListener(this);
    }
    this.mReviewsData.setFilters(this.mFilterByVersion, this.mFilterByDevice);
    this.mReviewsData.setSortType(this.mSortType);
    if (!isDataReady())
    {
      switchToLoading();
      requestData();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mDocument = ((Document)getArguments().getParcelable("finsky.ReviewsFragment.document"));
    setRetainInstance(true);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    if (isDataReady())
      onDataChanged();
    return localView;
  }

  public void onDataChanged()
  {
    this.mReviewsData.removeDataChangedListener(this);
    this.mReviewsData.removeErrorListener(this);
    this.mReviewsBinder.setData(this.mReviewsData);
    super.onDataChanged();
  }

  public void onDestroyView()
  {
    this.mReviewsBinder.onDestroyView();
    if (this.mReviewsData != null)
    {
      this.mReviewsData.removeDataChangedListener(this);
      this.mReviewsData.removeErrorListener(this);
    }
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
    this.mReviewsBinder.init(this.mContext, this, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader);
  }

  public void onRateReview(String paramString1, String paramString2, final RateReviewDialog.CommentRating paramCommentRating)
  {
    DfeRateReview localDfeRateReview = new DfeRateReview(FinskyApp.get().getDfeApi(), paramString1, paramString2, paramCommentRating.getRpcId());
    localDfeRateReview.addDataChangedListener(new OnDataChangedListener()
    {
      public void onDataChanged()
      {
        if (paramCommentRating == RateReviewDialog.CommentRating.SPAM)
          ReviewsFragment.this.reloadReviews();
      }
    });
    localDfeRateReview.addErrorListener(new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ReviewsFragment.this.toast(2131165576, 0);
      }
    });
  }

  public void onReviewFilterChanged(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mFilterByVersion = paramBoolean1;
    this.mFilterByDevice = paramBoolean2;
    this.mReviewsData.setFilters(paramBoolean1, paramBoolean2);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    recordState();
    paramBundle.putAll(this.mSavedInstanceState);
    super.onSaveInstanceState(paramBundle);
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateCurrentBackendId(this.mDocument.getBackend());
    this.mPageFragmentHost.updateBreadcrumb(this.mDocument.getTitle());
  }

  protected void rebindViews()
  {
    rebindActionBar();
    this.mReviewsBinder.bind(this.mDataView, this.mDocument);
  }

  protected void requestData()
  {
    this.mReviewsData.startLoadItems();
  }

  protected void toast(int paramInt1, int paramInt2)
  {
    Toast.makeText(this.mContext, paramInt1, paramInt2).show();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ReviewsFragment
 * JD-Core Version:    0.6.2
 */
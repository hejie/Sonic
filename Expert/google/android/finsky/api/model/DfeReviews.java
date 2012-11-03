package com.google.android.finsky.api.model;

import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Rev.GetReviewsResponse;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.remoting.protos.Rev.ReviewResponse;
import java.util.List;

public class DfeReviews extends PaginatedList<Rev.ReviewResponse, Rev.Review>
  implements Response.Listener<Rev.ReviewResponse>
{
  private DfeApi mDfeApi;
  private boolean mFilterByDevice;
  private boolean mFilterByVersion;
  private int mRating;
  private int mSortType;
  private int mVersionCode;

  public DfeReviews(DfeApi paramDfeApi, String paramString, int paramInt, boolean paramBoolean)
  {
    super(paramString, paramBoolean);
    this.mDfeApi = paramDfeApi;
    this.mFilterByVersion = false;
    this.mFilterByDevice = false;
    this.mRating = 0;
    this.mVersionCode = paramInt;
    this.mSortType = 2;
  }

  private void refetchReviews()
  {
    resetItems();
    startLoadItems();
  }

  public boolean currentlyFilteringByVersion()
  {
    return this.mFilterByVersion;
  }

  protected List<Rev.Review> getItemsFromResponse(Rev.ReviewResponse paramReviewResponse)
  {
    return paramReviewResponse.getGetResponse().getReviewList();
  }

  protected String getNextPageUrl(Rev.ReviewResponse paramReviewResponse)
  {
    return paramReviewResponse.getNextPageUrl();
  }

  public int getRatingFilter()
  {
    return this.mRating;
  }

  public int getSortType()
  {
    return this.mSortType;
  }

  public int getVersionFilter()
  {
    if (this.mFilterByVersion);
    for (int i = this.mVersionCode; ; i = -1)
      return i;
  }

  protected Request<?> makeRequest(String paramString)
  {
    DfeApi localDfeApi = this.mDfeApi;
    boolean bool = this.mFilterByDevice;
    if (this.mFilterByVersion);
    for (int i = this.mVersionCode; ; i = -1)
      return localDfeApi.getReviews(paramString, bool, i, this.mRating, this.mSortType, this, this);
  }

  public void setFilters(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1 != this.mFilterByVersion) || (paramBoolean2 != this.mFilterByDevice))
    {
      this.mFilterByVersion = paramBoolean1;
      this.mFilterByDevice = paramBoolean2;
      refetchReviews();
    }
  }

  public void setSortType(int paramInt)
  {
    if (paramInt != this.mSortType)
    {
      this.mSortType = paramInt;
      refetchReviews();
    }
  }

  public boolean shouldFilterByDevice()
  {
    return this.mFilterByDevice;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeReviews
 * JD-Core Version:    0.6.2
 */
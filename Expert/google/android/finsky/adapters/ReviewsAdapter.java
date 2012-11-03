package com.google.android.finsky.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.HistogramView;
import com.google.android.finsky.layout.ReviewItemLayout;
import com.google.android.finsky.layout.ReviewReplyLayout;
import com.google.android.finsky.layout.ReviewsControlContainer;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;

public class ReviewsAdapter extends PaginatedListAdapter
  implements Response.ErrorListener
{
  private final DfeReviews mData;
  private final Document mDoc;
  private final ChooseFilterOptionsHandler mFilterHandler;
  private boolean mHeaderVisible = true;
  private final LayoutInflater mLayoutInflater;
  private final RateReviewHandler mRatingHandler;

  public ReviewsAdapter(Context paramContext, Document paramDocument, DfeReviews paramDfeReviews, RateReviewHandler paramRateReviewHandler, ChooseFilterOptionsHandler paramChooseFilterOptionsHandler)
  {
    super(paramContext, null, paramDfeReviews.inErrorState(), paramDfeReviews.isMoreAvailable());
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mDoc = paramDocument;
    this.mData = paramDfeReviews;
    this.mData.addDataChangedListener(this);
    this.mData.addErrorListener(this);
    this.mRatingHandler = paramRateReviewHandler;
    this.mFilterHandler = paramChooseFilterOptionsHandler;
  }

  private void bindItemView(View paramView, ViewHolder paramViewHolder, int paramInt)
  {
    final Rev.Review localReview = getItem(paramInt);
    int i;
    if (!TextUtils.isEmpty(localReview.getCommentId()))
    {
      i = 1;
      paramViewHolder.reviewItemLayout.setReviewInfo(this.mDoc, localReview);
      if (i == 0)
        break label110;
      paramViewHolder.reviewItemLayout.setRateReviewClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ReviewsAdapter.this.mRatingHandler.onRateReview(localReview);
        }
      });
      label56: if (!localReview.hasReplyText())
        break label121;
      if (paramViewHolder.reviewReplyLayout == null)
      {
        paramViewHolder.reviewReplyLayout = ((ReviewReplyLayout)paramViewHolder.reviewReplyStub.inflate());
        paramViewHolder.reviewReplyStub = null;
      }
      paramViewHolder.reviewReplyLayout.setReviewInfo(this.mDoc, localReview);
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label110: paramViewHolder.reviewItemLayout.setRateReviewClickListener(null);
      break label56;
      label121: if (paramViewHolder.reviewReplyLayout != null)
        paramViewHolder.reviewReplyLayout.setVisibility(8);
    }
  }

  private boolean filtersVisible()
  {
    int i = 1;
    if ((this.mDoc != null) && (this.mDoc.getDocumentType() == i));
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  private View getFiltersView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    if (paramView == null);
    for (ReviewsControlContainer localReviewsControlContainer = (ReviewsControlContainer)inflate(2130968828, paramViewGroup, false); ; localReviewsControlContainer = (ReviewsControlContainer)paramView)
    {
      localReviewsControlContainer.setData(this.mData);
      localReviewsControlContainer.setFilterHandler(this.mFilterHandler);
      paramView = localReviewsControlContainer;
      break;
    }
  }

  private View getHeaderView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    for (TextView localTextView = (TextView)inflate(2130968829, paramViewGroup, false); ; localTextView = (TextView)paramView)
    {
      localTextView.setTextColor(CorpusResourceUtils.getBackendForegroundColor(this.mContext, this.mDoc.getBackend()));
      localTextView.setText(this.mContext.getString(2131165525).toUpperCase());
      return localTextView;
    }
  }

  private View getItemView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.mLayoutInflater.inflate(2130968830, paramViewGroup, false);
    ViewHolder localViewHolder = (ViewHolder)paramView.getTag();
    if (localViewHolder == null)
      localViewHolder = new ViewHolder(paramView);
    bindItemView(paramView, localViewHolder, paramInt);
    return paramView;
  }

  private View getNoMatchingView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null);
    for (View localView = inflate(2130968831, paramViewGroup, false); ; localView = paramView)
      return localView;
  }

  private View getStatisticsView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null)
      return paramView;
    if (paramView == null);
    for (HistogramView localHistogramView = (HistogramView)inflate(2130968832, paramViewGroup, false); ; localHistogramView = (HistogramView)paramView)
    {
      localHistogramView.bind(this.mDoc);
      paramView = localHistogramView;
      break;
    }
  }

  private boolean statsVisible()
  {
    if ((this.mDoc != null) && (this.mDoc.hasReviewHistogramData()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public int getCount()
  {
    int i = this.mData.getCount();
    if (this.mHeaderVisible)
      i++;
    if (statsVisible())
      i++;
    if (filtersVisible())
      i++;
    if (getFooterMode() != PaginatedListAdapter.FooterMode.NONE)
      i++;
    while (true)
    {
      return i;
      if (this.mData.getCount() == 0)
        i++;
    }
  }

  public int getFirstReviewViewIndex()
  {
    int i;
    if (this.mData.getCount() == 0)
      i = -1;
    while (true)
    {
      return i;
      i = 0;
      if (this.mHeaderVisible)
        i = 0 + 1;
      if (statsVisible())
        i++;
      if (filtersVisible())
        i++;
    }
  }

  public Rev.Review getItem(int paramInt)
  {
    if (paramInt < this.mData.getCount());
    for (Rev.Review localReview = (Rev.Review)this.mData.getItem(paramInt); ; localReview = null)
      return localReview;
  }

  public int getItemViewType(int paramInt)
  {
    int i = 1;
    int j;
    if (paramInt == -1 + getCount())
    {
      j = i;
      if ((!this.mHeaderVisible) || (paramInt != 0))
        break label34;
      i = 0;
    }
    while (true)
    {
      return i;
      j = 0;
      break;
      label34: if (this.mHeaderVisible)
        paramInt--;
      if ((!statsVisible()) || (paramInt != 0))
      {
        if (statsVisible())
          paramInt--;
        if ((filtersVisible()) && (paramInt == 0))
          i = 2;
        else if (j != 0)
        {
          if (this.mData.getCount() == 0)
          {
            if (this.mData.isMoreAvailable())
              i = 5;
            else
              i = 3;
          }
          else
            switch (2.$SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[getFooterMode().ordinal()])
            {
            default:
              throw new IllegalStateException("No footer or item at row " + paramInt);
            case 1:
              i = 5;
              break;
            case 2:
              i = 6;
              break;
            case 3:
              i = 4;
              break;
            }
        }
        else
          i = 4;
      }
    }
  }

  protected String getLastRequestError()
  {
    return ErrorStrings.get(this.mContext, this.mData.getVolleyError());
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    switch (getItemViewType(paramInt))
    {
    default:
      throw new IllegalStateException("Unknown type for getView " + getItemViewType(paramInt));
    case 0:
      localView = getHeaderView(paramView, paramViewGroup);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    }
    while (true)
    {
      return localView;
      localView = getStatisticsView(paramView, paramViewGroup);
      continue;
      localView = getFiltersView(paramView, paramViewGroup);
      continue;
      localView = getNoMatchingView(paramView, paramViewGroup);
      continue;
      localView = getItemView(paramInt - getFirstReviewViewIndex(), paramView, paramViewGroup);
      continue;
      localView = getLoadingFooterView(paramView, paramViewGroup);
      continue;
      localView = getErrorFooterView(paramView, paramViewGroup);
    }
  }

  public int getViewTypeCount()
  {
    return 7;
  }

  protected boolean isMoreDataAvailable()
  {
    return this.mData.isMoreAvailable();
  }

  public void onDestroyView()
  {
    this.mData.removeDataChangedListener(this);
    this.mData.removeErrorListener(this);
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    triggerFooterErrorMode();
  }

  protected void retryLoadingItems()
  {
    this.mData.retryLoadItems();
  }

  public static abstract interface ChooseFilterOptionsHandler
  {
    public abstract void onChooseFilterOptions();
  }

  public static abstract interface RateReviewHandler
  {
    public abstract void onRateReview(Rev.Review paramReview);
  }

  public static class ViewHolder
  {
    ReviewItemLayout reviewItemLayout;
    ReviewReplyLayout reviewReplyLayout;
    ViewStub reviewReplyStub;

    public ViewHolder(View paramView)
    {
      this.reviewItemLayout = ((ReviewItemLayout)paramView.findViewById(2131231227));
      this.reviewReplyStub = ((ViewStub)paramView.findViewById(2131231241));
      this.reviewReplyLayout = ((ReviewReplyLayout)paramView.findViewById(2131231219));
      paramView.setTag(this);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.ReviewsAdapter
 * JD-Core Version:    0.6.2
 */
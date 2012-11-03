package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;

public class DetailsLeftColumnContainer extends LinearLayout
{
  private ObservableScrollView mColumnScroller;
  private View mItemDetails;
  private View mSummary;
  private View mSummaryStrip;
  private int mTopBannerHeight;

  public DetailsLeftColumnContainer(Context paramContext)
  {
    super(paramContext);
  }

  public DetailsLeftColumnContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public DetailsLeftColumnContainer(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void findViews()
  {
    ObservableScrollView localObservableScrollView = (ObservableScrollView)getParent();
    if (localObservableScrollView != this.mColumnScroller)
      this.mColumnScroller = localObservableScrollView;
  }

  public int getSummaryBottom()
  {
    findViews();
    int i = this.mColumnScroller.getScrollY();
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.mItemDetails.getLayoutParams();
    return Math.max(this.mItemDetails.getMeasuredHeight() + localMarginLayoutParams.bottomMargin, i + this.mTopBannerHeight);
  }

  public int getSummaryStripTop()
  {
    findViews();
    int i = this.mColumnScroller.getScrollY();
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.mItemDetails.getLayoutParams();
    return Math.max(this.mItemDetails.getMeasuredHeight() + localMarginLayoutParams.bottomMargin + ((ViewGroup.MarginLayoutParams)this.mSummaryStrip.getLayoutParams()).topMargin, i + this.mTopBannerHeight);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSummary = findViewById(2131230915);
    this.mSummaryStrip = findViewById(2131230879);
    this.mItemDetails = findViewById(2131230881);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getWidth() - i - getPaddingRight();
    int m = j;
    int n = 0;
    if (n < getChildCount())
    {
      View localView = getChildAt(n);
      if (localView.getVisibility() == 8);
      while (true)
      {
        n++;
        break;
        if (localView == this.mSummary)
        {
          int i4 = getSummaryBottom();
          this.mSummary.layout(i, i4 - this.mSummary.getMeasuredHeight(), i + k, i4);
        }
        else if (localView == this.mSummaryStrip)
        {
          int i3 = getSummaryStripTop();
          this.mSummaryStrip.layout(i, i3, i + k, i3 + this.mSummaryStrip.getMeasuredHeight());
        }
        else
        {
          ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
          int i1 = localView.getMeasuredHeight();
          int i2 = m + localMarginLayoutParams.topMargin;
          localView.layout(i, i2, i + k, i2 + i1);
          m = i2 + (i1 + localMarginLayoutParams.bottomMargin);
        }
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 0;
    if (j < getChildCount())
    {
      View localView = getChildAt(j);
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
      if (localView.getVisibility() == 8);
      label121: 
      while (true)
      {
        j++;
        break;
        if (localMarginLayoutParams.height > 0)
          localView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(localMarginLayoutParams.height, 1073741824));
        while (true)
        {
          if ((localView == this.mSummary) || (localView == this.mSummaryStrip))
            break label121;
          i += localView.getMeasuredHeight() + localMarginLayoutParams.topMargin + localMarginLayoutParams.bottomMargin;
          break;
          localView.measure(paramInt1, 0);
        }
      }
    }
    if (this.mSummary.getMeasuredHeight() < this.mTopBannerHeight)
      this.mSummary.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(this.mTopBannerHeight, 1073741824));
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i);
  }

  public void setTopBannerHeight(int paramInt)
  {
    this.mTopBannerHeight = paramInt;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsLeftColumnContainer
 * JD-Core Version:    0.6.2
 */
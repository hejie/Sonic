package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout.LayoutParams;

public class DetailsMarginBox extends MarginBox
{
  private View mLeadingBanner;
  private View mLeftColumn;
  private DetailsLeftColumnContainer mLeftColumnContainer;
  private View mLeftColumnItemDetails;
  private View mLeftColumnItemThumbnail;
  private View mLeftColumnSummary;
  private final int mMaxBannerHeight;
  private final int mMinBannerHeight;
  private View mRightColumn;
  private DetailsRightColumn mRightColumnContainer;
  private View mRightColumnSummary;
  private View mTopBanner;

  public DetailsMarginBox(Context paramContext)
  {
    this(paramContext, null);
  }

  public DetailsMarginBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public DetailsMarginBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mMaxBannerHeight = localResources.getDimensionPixelSize(2131427395);
    this.mMinBannerHeight = localResources.getDimensionPixelSize(2131427394);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mLeadingBanner = findViewById(2131230906);
    this.mLeftColumn = findViewById(2131230907);
    this.mRightColumn = findViewById(2131230921);
    this.mTopBanner = this.mLeadingBanner.findViewById(2131230884);
    this.mLeftColumnContainer = ((DetailsLeftColumnContainer)findViewById(2131230908));
    this.mLeftColumnSummary = this.mLeftColumn.findViewById(2131230915);
    this.mLeftColumnItemDetails = this.mLeftColumn.findViewById(2131230881);
    this.mLeftColumnItemThumbnail = this.mLeftColumnItemDetails.findViewById(2131230909);
    this.mRightColumnSummary = this.mRightColumn.findViewById(2131230922);
    this.mRightColumnContainer = ((DetailsRightColumn)this.mRightColumn.findViewById(2131230926));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt3 - paramInt1;
    int j = paramInt4 - paramInt2;
    int k = this.mLeadingBanner.getMeasuredWidth();
    this.mLeadingBanner.layout(0, 0, k, this.mLeadingBanner.getMeasuredHeight());
    int m = 0 + k;
    int n = this.mLeftColumn.getMeasuredWidth();
    this.mLeftColumn.layout(m, 0, m + n, j);
    int i1 = m + n;
    this.mRightColumn.layout(i1, 0, i, j);
    View localView = this.mRightColumn.findViewById(2131230926);
    if ((localView instanceof DetailsRightColumn))
      ((DetailsRightColumn)localView).setRightMargin(k);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    int k = Math.min(Math.max((int)(0.58F * j), this.mMinBannerHeight), this.mMaxBannerHeight);
    int m = (int)(0.36F * k);
    int n = k - m;
    int i1 = this.mLeftColumn.getLayoutParams().width;
    int i2 = View.MeasureSpec.makeMeasureSpec(i1, 1073741824);
    ViewGroup.MarginLayoutParams localMarginLayoutParams1 = (ViewGroup.MarginLayoutParams)this.mLeftColumnItemDetails.getLayoutParams();
    this.mLeftColumnContainer.setTopBannerHeight(m);
    this.mLeftColumn.measure(i2, paramInt2);
    localMarginLayoutParams1.bottomMargin = this.mLeftColumnSummary.getMeasuredHeight();
    ViewGroup.MarginLayoutParams localMarginLayoutParams2 = (ViewGroup.MarginLayoutParams)this.mLeftColumnItemThumbnail.getLayoutParams();
    int i3 = i1 - localMarginLayoutParams2.leftMargin - localMarginLayoutParams2.rightMargin - this.mLeftColumnItemDetails.getPaddingLeft() - this.mLeftColumnItemDetails.getPaddingRight();
    int i4 = k - localMarginLayoutParams2.topMargin - localMarginLayoutParams2.bottomMargin - Math.max(m, this.mLeftColumnSummary.getMeasuredHeight());
    localMarginLayoutParams2.width = i3;
    localMarginLayoutParams2.height = i4;
    this.mLeftColumn.measure(i2, paramInt2);
    if (i > this.mMaxContentWidth);
    for (int i5 = (i - this.mMaxContentWidth) / 2; ; i5 = 0)
    {
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mTopBanner.getLayoutParams();
      localLayoutParams.height = 0;
      localLayoutParams.weight = 1.0F;
      this.mLeadingBanner.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(m, 1073741824));
      int i6 = i - i1 - i5;
      this.mRightColumnSummary.getLayoutParams().height = m;
      this.mRightColumnContainer.setBottomBannerHeight(n);
      this.mRightColumn.measure(View.MeasureSpec.makeMeasureSpec(i6, 1073741824), paramInt2);
      setMeasuredDimension(i, j);
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsMarginBox
 * JD-Core Version:    0.6.2
 */
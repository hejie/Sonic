package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class PurchaseSummaryLayout extends LinearLayout
{
  private View mRightColumn;
  private View mThumbnail;

  public PurchaseSummaryLayout(Context paramContext)
  {
    super(paramContext);
  }

  public PurchaseSummaryLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mThumbnail = findViewById(2131230909);
    this.mRightColumn = findViewById(2131231177);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = this.mThumbnail.getMeasuredHeight();
    if (i > this.mRightColumn.getMeasuredHeight())
      this.mRightColumn.measure(View.MeasureSpec.makeMeasureSpec(this.mRightColumn.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i, 1073741824));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.PurchaseSummaryLayout
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;

public class EditorialAppBucketContent extends EditorialBucketContent
{
  public EditorialAppBucketContent(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public EditorialAppBucketContent(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public EditorialAppBucketContent(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mCreator.layout(this.mCreator.getLeft(), this.mCreator.getTop(), this.mCreator.getLeft() + this.mCreator.getMeasuredWidth(), this.mCreator.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = this.mCreator.getMeasuredWidth();
    int j = this.mTitle.getMeasuredHeight();
    int k = this.mBuyButton.getMeasuredWidth();
    int m = this.mBuyButton.getMeasuredHeight();
    int n = View.MeasureSpec.getSize(paramInt1);
    if ((k + i >= n) && (j < m))
    {
      int i1 = this.mCreator.getMeasuredHeight();
      int i2 = n - k;
      this.mCreator.measure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i1, 1073741824));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EditorialAppBucketContent
 * JD-Core Version:    0.6.2
 */
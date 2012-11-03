package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class BucketRow extends LinearLayout
{
  private boolean mIsCompact;

  public BucketRow(Context paramContext)
  {
    super(paramContext);
  }

  public BucketRow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    int j = getHeight();
    for (int k = 0; k < getChildCount(); k++)
    {
      View localView = getChildAt(k);
      localView.layout(i, 0, i + localView.getMeasuredWidth(), j);
      i += localView.getMeasuredWidth();
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    int k = getChildCount();
    int m = i / k;
    int n = 0;
    for (int i1 = 0; i1 < k; i1++)
    {
      View localView2 = getChildAt(i1);
      localView2.measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), 0);
      n = Math.max(n, localView2.getMeasuredHeight());
    }
    if (this.mIsCompact);
    for (int i2 = n; ; i2 = Math.max(j, n))
    {
      int i3 = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
      int i4 = i;
      for (int i5 = 0; i5 < k; i5++)
      {
        View localView1 = getChildAt(i5);
        localView1.measure(View.MeasureSpec.makeMeasureSpec(i4 / (k - i5), 1073741824), i3);
        i4 -= localView1.getMeasuredWidth();
      }
    }
    setMeasuredDimension(i, i2);
  }

  public void setCompact(boolean paramBoolean)
  {
    this.mIsCompact = paramBoolean;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.BucketRow
 * JD-Core Version:    0.6.2
 */
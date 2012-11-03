package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;

public class SimpleMarginBox extends MarginBox
{
  public SimpleMarginBox(Context paramContext)
  {
    super(paramContext);
  }

  public SimpleMarginBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public SimpleMarginBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt3 - paramInt1;
    int j = paramInt4 - paramInt2;
    for (int k = 0; k < getChildCount(); k++)
    {
      View localView = getChildAt(k);
      int m = localView.getMeasuredWidth();
      int n = (i - m) / 2;
      localView.layout(n, 0, n + m, j);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.makeMeasureSpec(Math.min(this.mMaxContentWidth, i), 1073741824);
    int k = 0;
    int m = 0;
    if (m < getChildCount())
    {
      View localView = getChildAt(m);
      if (localView.getLayoutParams().height == -1)
      {
        k = View.MeasureSpec.getSize(paramInt2);
        localView.measure(j, View.MeasureSpec.makeMeasureSpec(k, 1073741824));
      }
      while (true)
      {
        m++;
        break;
        localView.measure(j, View.MeasureSpec.makeMeasureSpec(0, 0));
        k = Math.max(k, localView.getMeasuredHeight());
      }
    }
    setMeasuredDimension(i, k);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SimpleMarginBox
 * JD-Core Version:    0.6.2
 */
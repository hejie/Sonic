package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import com.android.vending.R.styleable;

public class DetailsButtonColumnLayout extends LinearLayout
{
  private int mMinimumRowCount;

  public DetailsButtonColumnLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public DetailsButtonColumnLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DetailsButtonColumnLayout, 0, 0);
    this.mMinimumRowCount = localTypedArray.getInt(0, -1);
    localTypedArray.recycle();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = 0;
    int j = getChildCount();
    int k = 0;
    if (k < j)
    {
      View localView2 = getChildAt(k);
      if (localView2.getVisibility() == 8);
      while (true)
      {
        k++;
        break;
        if ((localView2 instanceof Button))
        {
          localView2.measure(0, 0);
          i = Math.max(localView2.getMeasuredWidth(), i);
        }
      }
    }
    int m = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    while (i3 < j)
    {
      View localView1 = getChildAt(i3);
      if (localView1.getVisibility() == 8)
      {
        i3++;
      }
      else
      {
        if ((localView1 instanceof Button))
          localView1.measure(m, 0);
        while (true)
        {
          ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localView1.getLayoutParams();
          int i5 = localMarginLayoutParams.topMargin + localView1.getMeasuredHeight() + localMarginLayoutParams.bottomMargin;
          n += i5;
          if (!(localView1 instanceof Button))
            break;
          i1++;
          i2 += i5;
          break;
          localView1.measure(0, 0);
        }
      }
    }
    if ((this.mMinimumRowCount > 0) && (i1 > 0) && (i1 < this.mMinimumRowCount))
      n = i2 / i1 * this.mMinimumRowCount + (n - i2);
    int i4 = n + (getPaddingTop() + getPaddingBottom());
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i4);
  }

  public void setMinimumRowCount(int paramInt)
  {
    if (this.mMinimumRowCount != paramInt)
    {
      this.mMinimumRowCount = paramInt;
      requestLayout();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsButtonColumnLayout
 * JD-Core Version:    0.6.2
 */
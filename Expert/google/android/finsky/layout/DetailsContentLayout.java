package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DetailsContentLayout extends LinearLayout
{
  private ObservableScrollView mColumnScroller;
  private View mSubscriptionsSection;

  public DetailsContentLayout(Context paramContext)
  {
    super(paramContext);
  }

  public DetailsContentLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public DetailsContentLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void findViews()
  {
    ViewParent localViewParent = getParent();
    if ((localViewParent instanceof ObservableScrollView))
    {
      ObservableScrollView localObservableScrollView = (ObservableScrollView)localViewParent;
      if (localObservableScrollView != this.mColumnScroller)
        this.mColumnScroller = localObservableScrollView;
    }
  }

  public int getSummaryStripTop()
  {
    findViews();
    int i;
    int j;
    if (this.mSubscriptionsSection != null)
    {
      i = this.mSubscriptionsSection.getMeasuredHeight();
      if ((this.mSubscriptionsSection == null) || (this.mSubscriptionsSection.getVisibility() != 0))
        break label56;
      j = 1;
      label38: if (this.mColumnScroller != null)
        break label66;
      if (j == 0)
        break label61;
    }
    while (true)
    {
      return i;
      i = 0;
      break;
      label56: j = 0;
      break label38;
      label61: i = 0;
      continue;
      label66: int k = this.mColumnScroller.getScrollY();
      if (j == 0)
        i = k;
      else
        i = Math.max(i, k);
    }
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
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localView.getLayoutParams();
        if (localView.getId() == 2131230879)
        {
          int i3 = getSummaryStripTop();
          localView.layout(i, i3, i + k, i3 + localView.getMeasuredHeight());
        }
        else
        {
          int i1 = localView.getMeasuredHeight();
          int i2 = m + localLayoutParams.topMargin;
          localView.layout(i, i2, i + k, i2 + i1);
          m = i2 + (i1 + localLayoutParams.bottomMargin);
        }
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = 0;
    int j = 0;
    if (j < getChildCount())
    {
      View localView = getChildAt(j);
      if (localView.getId() == 2131230911)
        this.mSubscriptionsSection = localView;
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localView.getLayoutParams();
      if (localView.getVisibility() == 8);
      while (true)
      {
        j++;
        break;
        if (localView.getId() != 2131230879)
          i += localView.getMeasuredHeight() + localLayoutParams.topMargin + localLayoutParams.bottomMargin;
      }
    }
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsContentLayout
 * JD-Core Version:    0.6.2
 */
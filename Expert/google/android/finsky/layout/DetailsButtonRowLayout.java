package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class DetailsButtonRowLayout extends LinearLayout
{
  public DetailsButtonRowLayout(Context paramContext)
  {
    super(paramContext);
  }

  public DetailsButtonRowLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private View getSingleVisibleChild()
  {
    int i = getChildCount();
    int j = 0;
    View localView;
    if (j < i)
    {
      localView = getChildAt(j);
      if (localView.getVisibility() != 0);
    }
    while (true)
    {
      return localView;
      j++;
      break;
      localView = null;
    }
  }

  private int getVisibleChildrenCount()
  {
    int i = 0;
    int j = getChildCount();
    for (int k = 0; k < j; k++)
      if (getChildAt(k).getVisibility() == 0)
        i++;
    return i;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (getVisibleChildrenCount() != 1);
    while (true)
    {
      return;
      View localView = getSingleVisibleChild();
      int i = getWidth() - getPaddingRight();
      localView.layout(i - localView.getMeasuredWidth(), localView.getTop(), i, localView.getBottom());
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getVisibleChildrenCount() != 1);
    while (true)
    {
      return;
      View localView = getSingleVisibleChild();
      int i = View.MeasureSpec.getSize(paramInt1) - getPaddingLeft() - getPaddingRight();
      int j = localView.getLayoutParams().height;
      localView.measure(View.MeasureSpec.makeMeasureSpec(i / 2, 1073741824), View.MeasureSpec.makeMeasureSpec(j, 1073741824));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsButtonRowLayout
 * JD-Core Version:    0.6.2
 */
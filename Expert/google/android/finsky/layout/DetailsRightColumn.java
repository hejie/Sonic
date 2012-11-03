package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class DetailsRightColumn extends LinearLayout
{
  private int mBottomBannerHeight;
  private int mRightMargin;

  public DetailsRightColumn(Context paramContext)
  {
    this(paramContext, null);
  }

  public DetailsRightColumn(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private boolean isPartOfBottomBanner(View paramView)
  {
    int i = paramView.getId();
    if ((i == 2131230949) || (i == 2131230950));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean toApplyRightPadding(View paramView)
  {
    if (paramView.getId() == 2131230950);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = 0;
    int j = 0;
    while (j < getChildCount())
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() == 8)
      {
        j++;
      }
      else
      {
        int k = localView.getMeasuredWidth();
        boolean bool = isPartOfBottomBanner(localView);
        if (!bool)
        {
          k -= this.mRightMargin;
          label73: if (toApplyRightPadding(localView))
            localView.setPadding(localView.getPaddingLeft(), localView.getPaddingTop(), this.mRightMargin, localView.getPaddingBottom());
          if (!bool)
            break label162;
        }
        label162: for (int m = View.MeasureSpec.makeMeasureSpec(this.mBottomBannerHeight, 1073741824); ; m = 0)
        {
          localView.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), m);
          i += localView.getMeasuredHeight();
          break;
          localView.getLayoutParams().height = this.mBottomBannerHeight;
          break label73;
        }
      }
    }
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i);
  }

  public void setBottomBannerHeight(int paramInt)
  {
    this.mBottomBannerHeight = paramInt;
  }

  public void setRightMargin(int paramInt)
  {
    this.mRightMargin = paramInt;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsRightColumn
 * JD-Core Version:    0.6.2
 */
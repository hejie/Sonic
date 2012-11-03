package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;

public class CombinedTitleLayout extends LinearLayout
{
  private View mTipperSticker;
  private View mTitle;

  public CombinedTitleLayout(Context paramContext)
  {
    super(paramContext);
  }

  public CombinedTitleLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = findViewById(2131230957);
    this.mTipperSticker = findViewById(2131230965);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mTipperSticker.getVisibility() == 8);
    while (true)
    {
      return;
      int i = this.mTipperSticker.getMeasuredWidth();
      this.mTipperSticker.measure(0, 0);
      if (this.mTipperSticker.getMeasuredWidth() > i)
      {
        int j = View.MeasureSpec.getSize(paramInt1) - ((ViewGroup.MarginLayoutParams)this.mTipperSticker.getLayoutParams()).leftMargin - this.mTipperSticker.getMeasuredWidth();
        this.mTitle.measure(View.MeasureSpec.makeMeasureSpec(j, 1073741824), 0);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CombinedTitleLayout
 * JD-Core Version:    0.6.2
 */
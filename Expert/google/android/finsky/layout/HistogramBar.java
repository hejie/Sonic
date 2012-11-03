package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HistogramBar extends View
{
  private Paint mBarPaint = new Paint();
  private double mWidthPercentage;

  public HistogramBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public HistogramBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mBarPaint.setColor(paramContext.getResources().getColor(2131361845));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = getPaddingLeft();
    int j = getWidth() - i - getPaddingRight();
    int k = (int)(this.mWidthPercentage * j);
    if (k <= 0);
    while (true)
    {
      return;
      int m = getPaddingTop();
      int n = getHeight() - m - getPaddingBottom();
      paramCanvas.drawRect(i, m, k, n, this.mBarPaint);
    }
  }

  public void setWidthPercentage(double paramDouble)
  {
    this.mWidthPercentage = paramDouble;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.HistogramBar
 * JD-Core Version:    0.6.2
 */
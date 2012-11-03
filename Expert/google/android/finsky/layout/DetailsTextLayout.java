package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailsTextLayout extends LinearLayout
{
  private TextView mContentView;
  private int mCurrentMaxLines;
  private int mDefaultMaxLines;
  private MetricsListener mMetricsListener;
  private int mPrevWidth;

  public DetailsTextLayout(Context paramContext)
  {
    super(paramContext);
  }

  public DetailsTextLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mContentView = ((TextView)findViewById(2131230973));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mMetricsListener == null);
    while (true)
    {
      return;
      int i = paramInt3 - paramInt1;
      if ((i > 0) && (this.mPrevWidth != i))
      {
        this.mContentView.setMaxLines(2147483647);
        int j = View.MeasureSpec.makeMeasureSpec(this.mContentView.getMeasuredWidth(), 1073741824);
        this.mContentView.measure(j, 0);
        int k = this.mContentView.getMeasuredHeight();
        this.mContentView.setMaxLines(this.mDefaultMaxLines);
        this.mContentView.measure(j, 0);
        int m = this.mContentView.getMeasuredHeight();
        this.mContentView.setMaxLines(this.mCurrentMaxLines);
        this.mContentView.measure(j, 0);
        this.mMetricsListener.metricsAvailable(k, m);
        this.mPrevWidth = i;
      }
    }
  }

  public void setCurrentMaxLines(int paramInt)
  {
    this.mContentView.setMaxLines(paramInt);
    this.mCurrentMaxLines = paramInt;
  }

  public void setDefaultMaxLines(int paramInt)
  {
    this.mDefaultMaxLines = paramInt;
    setCurrentMaxLines(paramInt);
  }

  public void setMetricsListener(MetricsListener paramMetricsListener)
  {
    this.mMetricsListener = paramMetricsListener;
  }

  public static abstract interface MetricsListener
  {
    public abstract void metricsAvailable(int paramInt1, int paramInt2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsTextLayout
 * JD-Core Version:    0.6.2
 */
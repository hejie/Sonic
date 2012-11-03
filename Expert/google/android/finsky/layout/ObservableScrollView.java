package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView
{
  private ScrollListener mOnScrollListener;

  public ObservableScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public ObservableScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ObservableScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollListener != null)
      this.mOnScrollListener.onScroll(paramInt1, paramInt2);
  }

  public void setOnScrollListener(ScrollListener paramScrollListener)
  {
    this.mOnScrollListener = paramScrollListener;
  }

  public static abstract interface ScrollListener
  {
    public abstract void onScroll(int paramInt1, int paramInt2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ObservableScrollView
 * JD-Core Version:    0.6.2
 */
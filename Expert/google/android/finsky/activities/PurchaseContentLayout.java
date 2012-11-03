package com.google.android.finsky.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.finsky.layout.ObservableScrollView;

public class PurchaseContentLayout extends ViewGroup
{
  private View mAcquireRow;
  private View mAcquireRowDummy;
  private ObservableScrollView mDetailsScroller;
  private View mInputPanel;
  private View mLeadingStrip;
  private View mPurchasePanel;
  private View mWalletBylineContainer;

  public PurchaseContentLayout(Context paramContext)
  {
    super(paramContext);
  }

  public PurchaseContentLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PurchaseContentLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void findViews()
  {
    ObservableScrollView localObservableScrollView = (ObservableScrollView)getParent();
    if (localObservableScrollView != this.mDetailsScroller)
    {
      this.mDetailsScroller = localObservableScrollView;
      this.mInputPanel = this.mDetailsScroller.findViewById(2131231168);
      this.mPurchasePanel = this.mDetailsScroller.findViewById(2131231170);
      this.mAcquireRow = this.mDetailsScroller.findViewById(2131231153);
      this.mAcquireRowDummy = this.mDetailsScroller.findViewById(2131231157);
      this.mLeadingStrip = findViewById(2131230879);
      this.mWalletBylineContainer = findViewById(2131231180);
    }
  }

  public int getAcquireRowFooterTop()
  {
    findViews();
    int i = this.mDetailsScroller.getScrollY();
    int j = this.mInputPanel.getTop();
    if (this.mPurchasePanel.getVisibility() != 8)
      j += this.mPurchasePanel.getBottom();
    if (this.mLeadingStrip != null)
      j += this.mLeadingStrip.getBottom();
    if (this.mWalletBylineContainer != null)
      j += this.mWalletBylineContainer.getMeasuredHeight();
    int k = this.mAcquireRowDummy.getLayoutParams().height;
    int m = this.mAcquireRow.getLayoutParams().height;
    if (i >= j + k - m);
    for (int n = i + m; ; n = j + k)
      return n;
  }

  public int getAcquireRowTop()
  {
    findViews();
    int i = this.mDetailsScroller.getScrollY();
    int j = this.mInputPanel.getTop();
    if (this.mPurchasePanel.getVisibility() != 8)
      j += this.mPurchasePanel.getBottom();
    if (this.mLeadingStrip != null)
      j += this.mLeadingStrip.getBottom();
    int k = this.mAcquireRowDummy.getLayoutParams().height;
    int m = this.mAcquireRow.getLayoutParams().height;
    if (i >= j + (k - m) / 2);
    while (true)
    {
      return i;
      i = j + (k - m) / 2;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView1 = getChildAt(0);
    View localView2 = getChildAt(1);
    View localView3 = getChildAt(2);
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getWidth() - i - getPaddingRight();
    int m = getHeight() - j - getPaddingBottom();
    localView1.layout(i, j, i + k, j + m);
    int n = getAcquireRowTop();
    localView2.layout(i, n, i + k, n + localView2.getMeasuredHeight());
    int i1 = getAcquireRowFooterTop();
    localView3.layout(i, i1, i + k, i1 + localView3.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    View localView1 = getChildAt(0);
    View localView2 = getChildAt(1);
    View localView3 = getChildAt(2);
    int i = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt1) - getPaddingLeft() - getPaddingRight(), 1073741824);
    localView1.measure(i, 0);
    localView2.measure(i, View.MeasureSpec.makeMeasureSpec(localView2.getLayoutParams().height, 1073741824));
    localView3.measure(i, View.MeasureSpec.makeMeasureSpec(localView3.getLayoutParams().height, 1073741824));
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), localView1.getMeasuredHeight());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.PurchaseContentLayout
 * JD-Core Version:    0.6.2
 */
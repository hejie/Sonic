package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.android.vending.R.styleable;
import com.google.android.finsky.adapters.ImageStripAdapter;
import com.google.android.finsky.remoting.protos.Doc.Image.Dimension;
import com.google.android.finsky.utils.ThumbnailUtils;

public class HorizontalStrip extends DraggableHorizontalStrip
{
  private ImageStripAdapter mAdapter;
  protected final Drawable mChildBackgroundDrawable;
  private final Doc.Image.Dimension mDimension;
  private int mEdgeFadeColor;
  private OnImageChildViewTapListener mImageChildTappedListener;
  private final boolean mIsInDoubleColumnLayout;
  protected final float mScreenScaleFactor;
  private OnVideoChildViewTapListener mVideoChildTappedListener;

  public HorizontalStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public HorizontalStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HorizontalStrip, paramInt, 0);
    this.mChildBackgroundDrawable = localTypedArray.getDrawable(0);
    localTypedArray.recycle();
    this.mDimension = new Doc.Image.Dimension();
    Resources localResources = paramContext.getResources();
    this.mScreenScaleFactor = localResources.getDisplayMetrics().density;
    this.mEdgeFadeColor = localResources.getColor(2131361846);
    this.mIsInDoubleColumnLayout = localResources.getBoolean(2131296262);
    setWillNotDraw(false);
  }

  private int getChildHeight(int paramInt)
  {
    this.mAdapter.getImageDimensionAt(paramInt, this.mDimension, this.mScreenScaleFactor);
    return this.mDimension.getHeight();
  }

  private int getChildWidth(int paramInt)
  {
    this.mAdapter.getImageDimensionAt(paramInt, this.mDimension, this.mScreenScaleFactor);
    return this.mDimension.getWidth();
  }

  private int getTotalChildWidth(int paramInt)
  {
    int i = getChildAt(paramInt).getWidth();
    if (((paramInt == 0) && (!this.mIsInDoubleColumnLayout) && (this.mAdapter.hasLeadingMargin())) || (paramInt != 0))
      i += this.mLayoutMargin;
    return i;
  }

  private void recreateChildViews()
  {
    removeAllViews();
    if (this.mAdapter == null);
    while (true)
    {
      return;
      for (int i = 0; i < this.mAdapter.getChildCount(); i++)
      {
        View localView = this.mAdapter.getViewAt(this.mContext, this, i);
        localView.setBackgroundDrawable(this.mChildBackgroundDrawable);
        addView(localView);
      }
      syncChildViews();
    }
  }

  private void syncChildViews()
  {
    int i = 1;
    int j = 0;
    if (j < this.mAdapter.getChildCount())
    {
      View localView = getChildAt(j);
      Drawable localDrawable = this.mAdapter.getImageAt(j);
      if ((localView instanceof ImageView))
        if (localDrawable != null)
          ThumbnailUtils.setImageDrawableWithFade((ImageView)localView, localDrawable);
      while (true)
      {
        j++;
        break;
        i = 0;
        continue;
        if ((localView instanceof VideoFrame))
        {
          VideoFrame localVideoFrame = (VideoFrame)localView;
          if (localDrawable != null)
            localVideoFrame.configurePreviewWithFlatOverlay(localDrawable);
        }
      }
    }
    if (i != 0)
      requestLayout();
  }

  protected float getLeftEdgeOfChildOnLeft(float paramFloat)
  {
    int i = 0;
    int j = 0;
    for (int k = 0; ; k++)
    {
      if (k < getChildCount())
      {
        j += getTotalChildWidth(k);
        if (j <= paramFloat);
      }
      else
      {
        if ((this.mIsInDoubleColumnLayout) && (k != 0))
          i += this.mLayoutMargin;
        return i;
      }
      i = j;
    }
  }

  protected float getLeftEdgeOfChildOnRight(float paramFloat)
  {
    int i = 0;
    int j = 0;
    for (int k = 0; ; k++)
      if (k < getChildCount())
      {
        j += getTotalChildWidth(k);
        i = j;
        if (j <= paramFloat);
      }
      else
      {
        if (this.mIsInDoubleColumnLayout)
          i += this.mLayoutMargin;
        return i;
      }
  }

  protected float getLeftFadingEdgeStrength()
  {
    float f1 = 0.0F;
    float f2 = getScrollPosition();
    if ((this.mAdapter != null) && (!this.mAdapter.hasLeadingMargin()))
      f2 += this.mLayoutMargin;
    if (f2 >= 0.0F);
    while (true)
    {
      return f1;
      float f3 = -f2;
      int i = getHorizontalFadingEdgeLength();
      if (f3 > i)
        f1 = 1.0F;
      else
        f1 = f3 / i;
    }
  }

  protected float getRightFadingEdgeStrength()
  {
    float f1 = 0.0F;
    float f2 = getScrollPosition() + this.mTotalChildrenWidth - getWidth();
    if (f2 <= 0.0F);
    while (true)
    {
      return f1;
      int i = getHorizontalFadingEdgeLength();
      if (f2 > i)
        f1 = 1.0F;
      else
        f1 = f2 / i;
    }
  }

  public int getSolidColor()
  {
    return this.mEdgeFadeColor;
  }

  public void onDestroyView()
  {
    setAdapter(null);
    setImageChildTappedListener(null);
    setVideoChildTappedListener(null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mAdapter == null);
    while (true)
    {
      return;
      int i = paramInt4 - paramInt2;
      this.mTotalChildrenWidth = 0.0F;
      int j = getPaddingLeft();
      if ((!this.mIsInDoubleColumnLayout) && (this.mAdapter.hasLeadingMargin()))
        j += this.mLayoutMargin;
      for (int k = 0; k < getChildCount(); k++)
      {
        View localView = getChildAt(k);
        int m = localView.getMeasuredWidth();
        localView.layout(j, 0, j + m, i);
        j += m + this.mLayoutMargin;
        this.mTotalChildrenWidth += m;
      }
      this.mTotalChildrenWidth += this.mLayoutMargin * (-1 + getChildCount());
      if (!this.mIsInDoubleColumnLayout)
      {
        this.mTotalChildrenWidth += this.mLayoutMargin;
        if (this.mAdapter.hasLeadingMargin())
          this.mTotalChildrenWidth += this.mLayoutMargin;
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = 0;
    while (j < getChildCount())
    {
      View localView = getChildAt(j);
      if ((localView instanceof ImageView))
      {
        int m = getChildWidth(j);
        int n = getChildHeight(j);
        float f = i / n;
        if (f < 1.0D)
          m = (int)(f * m);
        localView.measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), paramInt2);
        j++;
      }
      else
      {
        if (this.mAdapter.hasImageDimensionAt(j));
        for (int k = getChildWidth(j); ; k = localView.getLayoutParams().width)
        {
          localView.measure(View.MeasureSpec.makeMeasureSpec(k, 1073741824), paramInt2);
          break;
        }
      }
    }
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), i);
  }

  protected boolean onTouchEventTriggeredTap(float paramFloat)
  {
    boolean bool = false;
    int i = findViewIndexAtX(paramFloat);
    if (i < 0);
    while (true)
    {
      return bool;
      View localView = getChildAt(i);
      localView.setPressed(false);
      if (((localView instanceof ImageView)) && (this.mImageChildTappedListener != null))
      {
        this.mImageChildTappedListener.onImageChildViewTap(this.mAdapter.toImageIndex(i));
        bool = true;
      }
      else if (((localView instanceof VideoFrame)) && (this.mVideoChildTappedListener != null))
      {
        this.mVideoChildTappedListener.onVideoChildViewTap(this.mAdapter.toVideoIndex(i));
        bool = true;
      }
    }
  }

  public void setAdapter(ImageStripAdapter paramImageStripAdapter)
  {
    this.mAdapter = paramImageStripAdapter;
    if (this.mAdapter != null)
      this.mAdapter.registerDataSetObserver(new DataSetObserver()
      {
        public void onChanged()
        {
          HorizontalStrip.this.syncChildViews();
        }

        public void onInvalidated()
        {
          HorizontalStrip.this.recreateChildViews();
        }
      });
    recreateChildViews();
  }

  public void setImageChildTappedListener(OnImageChildViewTapListener paramOnImageChildViewTapListener)
  {
    this.mImageChildTappedListener = paramOnImageChildViewTapListener;
  }

  public void setVideoChildTappedListener(OnVideoChildViewTapListener paramOnVideoChildViewTapListener)
  {
    this.mVideoChildTappedListener = paramOnVideoChildViewTapListener;
  }

  public static abstract interface OnImageChildViewTapListener
  {
    public abstract void onImageChildViewTap(int paramInt);
  }

  public static abstract interface OnVideoChildViewTapListener
  {
    public abstract void onVideoChildViewTap(int paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.HorizontalStrip
 * JD-Core Version:    0.6.2
 */
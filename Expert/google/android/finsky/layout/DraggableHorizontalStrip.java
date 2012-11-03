package com.google.android.finsky.layout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

public abstract class DraggableHorizontalStrip extends ViewGroup
{
  protected Context mContext;
  protected final float mDeceleration;
  protected int mIndexOfPressedChild = -1;
  private float mLastMotionX;
  private float mLastMotionY;
  protected int mLayoutMargin;
  protected float mOriginalPixelOffsetOfFirstChild = 0.0F;
  private Animator mScrollAnimation;
  private final int mScrollThreshold;
  protected float mTotalChildrenWidth;
  private VelocityTracker mVelocityTracker;
  private float mXDistanceScrolledSinceLastDown;
  private float mYDistanceScrolledSinceLastDown;

  public DraggableHorizontalStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mScrollThreshold = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.mDeceleration = (1158.2634F * (160.0F * paramContext.getResources().getDisplayMetrics().density) * ViewConfiguration.getScrollFriction());
  }

  private void onTouchEventDone(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    resetPressedState();
    float f1 = Math.abs(paramFloat2);
    float f10;
    int m;
    float f12;
    label94: float f13;
    float f15;
    if ((f1 > ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity()) && (this.mTotalChildrenWidth > getWidth()))
    {
      float f9 = f1 / this.mDeceleration;
      f10 = f1 * f9 - f9 * (f9 * this.mDeceleration) / 2.0F;
      if (paramFloat2 < 0.0F)
      {
        m = 1;
        float f11 = getScrollPosition();
        if (m == 0)
          break label180;
        f12 = -f10;
        f13 = clampToTotalStripWidth(f12 + f11);
        if (m == 0)
          break label187;
        f15 = -FloatMath.sqrt((f10 + (getLeftEdgeOfChildOnRight(clampToTotalStripWidth(this.mTotalChildrenWidth - f13)) - clampToTotalStripWidth(this.mTotalChildrenWidth - f13))) * (2.0F * this.mDeceleration));
        label153: float f16 = Math.abs(f15) / this.mDeceleration;
        runScrollAnimation(f15, f16);
      }
    }
    label180: label187: 
    do
      while (true)
      {
        return;
        m = 0;
        break;
        f12 = f10;
        break label94;
        float f14 = getLeftEdgeOfChildOnLeft(clampToTotalStripWidth(this.mTotalChildrenWidth - f13));
        f15 = FloatMath.sqrt((f10 + (clampToTotalStripWidth(this.mTotalChildrenWidth - f13) - f14)) * (2.0F * this.mDeceleration));
        break label153;
        if ((this.mXDistanceScrolledSinceLastDown > this.mScrollThreshold) || (this.mYDistanceScrolledSinceLastDown > this.mScrollThreshold) || (paramBoolean) || (!onTouchEventTriggeredTap(paramFloat1 + getScrollX())))
          break label288;
        this.mOriginalPixelOffsetOfFirstChild = 0.0F;
      }
    while (this.mTotalChildrenWidth <= getWidth());
    label288: float f2 = clampToTotalStripWidth(this.mTotalChildrenWidth - getScrollPosition());
    float f3 = 0.0F;
    int i = 0;
    int j = 0;
    label325: int k;
    float f8;
    label385: float f4;
    float f5;
    if (j < getChildCount())
    {
      k = i + getChildAt(j).getWidth();
      if (k < f2)
        break label456;
      float f7 = f2 - i;
      if (k - f2 > f7)
      {
        f8 = getLeftEdgeOfChildOnLeft(f2);
        f3 = f2 - clampToTotalStripWidth(f8);
      }
    }
    else
    {
      f4 = FloatMath.sqrt(Math.abs(f3 * (2.0F * this.mDeceleration)));
      f5 = f4 / this.mDeceleration;
      if (f3 >= 0.0F)
        break label471;
    }
    label456: label471: for (float f6 = -f4; ; f6 = f4)
    {
      runScrollAnimation(f6, f5);
      break;
      f8 = getLeftEdgeOfChildOnRight(f2);
      break label385;
      i = k + this.mLayoutMargin;
      j++;
      break label325;
    }
  }

  private void onTouchEventDown(float paramFloat1, float paramFloat2)
  {
    this.mLastMotionX = paramFloat1;
    this.mLastMotionY = paramFloat2;
    this.mXDistanceScrolledSinceLastDown = 0.0F;
    this.mYDistanceScrolledSinceLastDown = 0.0F;
    this.mIndexOfPressedChild = findViewIndexAtX(paramFloat1 + getScrollX());
    setPressedState();
  }

  private void onTouchEventMove(float paramFloat1, float paramFloat2)
  {
    float f1 = this.mLastMotionX - paramFloat1;
    float f2 = this.mLastMotionY - paramFloat2;
    this.mLastMotionX = paramFloat1;
    this.mLastMotionY = paramFloat2;
    if (findViewIndexAtX(paramFloat1 + getScrollX()) != this.mIndexOfPressedChild)
    {
      resetPressedState();
      this.mIndexOfPressedChild = -1;
    }
    this.mXDistanceScrolledSinceLastDown += Math.abs(f1);
    this.mYDistanceScrolledSinceLastDown += Math.abs(f2);
    if ((this.mYDistanceScrolledSinceLastDown <= this.mScrollThreshold) && (this.mXDistanceScrolledSinceLastDown > this.mScrollThreshold))
      requestDisallowInterceptTouchEvent(true);
    if (this.mTotalChildrenWidth > getWidth())
      updateScrollPosition(getScrollPosition() - f1);
  }

  private void resetPressedState()
  {
    if (this.mIndexOfPressedChild >= 0)
      getChildAt(this.mIndexOfPressedChild).setPressed(false);
  }

  private void setPressedState()
  {
    if (this.mIndexOfPressedChild >= 0)
      getChildAt(this.mIndexOfPressedChild).setPressed(true);
  }

  private void updateScrollPosition(float paramFloat)
  {
    scrollTo(-(int)limitScrollPosition(paramFloat), 0);
  }

  protected float clampToTotalStripWidth(float paramFloat)
  {
    if (this.mTotalChildrenWidth == 0.0F);
    for (float f = paramFloat; ; f = paramFloat)
    {
      return f;
      while (paramFloat < 0.0F)
        paramFloat += this.mTotalChildrenWidth;
      while (paramFloat >= this.mTotalChildrenWidth)
        paramFloat -= this.mTotalChildrenWidth;
    }
  }

  protected Animator createScrollAnimation(final float paramFloat, long paramLong)
  {
    this.mOriginalPixelOffsetOfFirstChild = getScrollPosition();
    float[] arrayOfFloat = new float[2];
    arrayOfFloat[0] = 0.0F;
    arrayOfFloat[1] = ((float)paramLong);
    ValueAnimator localValueAnimator = ValueAnimator.ofFloat(arrayOfFloat).setDuration(paramLong);
    localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        float f1 = ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue() / 1000.0F;
        float f2 = f1 * Math.abs(paramFloat) - f1 * (f1 * DraggableHorizontalStrip.this.mDeceleration) / 2.0F;
        if (paramFloat < 0.0F)
          f2 = -f2;
        DraggableHorizontalStrip.this.updateScrollPosition(DraggableHorizontalStrip.this.mOriginalPixelOffsetOfFirstChild + Math.round(f2));
      }
    });
    localValueAnimator.setInterpolator(new LinearInterpolator());
    return localValueAnimator;
  }

  protected int findViewIndexAtX(float paramFloat)
  {
    int i = 0;
    if (i < getChildCount())
    {
      View localView = getChildAt(i);
      if ((localView.getLeft() > paramFloat) || (localView.getRight() < paramFloat));
    }
    while (true)
    {
      return i;
      i++;
      break;
      i = -1;
    }
  }

  abstract float getLeftEdgeOfChildOnLeft(float paramFloat);

  abstract float getLeftEdgeOfChildOnRight(float paramFloat);

  protected float getScrollPosition()
  {
    return -getScrollX();
  }

  protected float limitScrollPosition(float paramFloat)
  {
    if (paramFloat > 0.0F)
      paramFloat = 0.0F;
    int i = (int)(this.mTotalChildrenWidth - getWidth());
    if (-paramFloat > i)
      paramFloat = -i;
    return paramFloat;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    float f2;
    int i;
    while (true)
    {
      float f1;
      try
      {
        f1 = paramMotionEvent.getY();
        f2 = paramMotionEvent.getX();
        if (this.mVelocityTracker == null)
          this.mVelocityTracker = VelocityTracker.obtain();
        this.mVelocityTracker.addMovement(paramMotionEvent);
        i = paramMotionEvent.getAction();
        switch (i)
        {
        default:
          return true;
        case 0:
          onTouchEventDown(f2, f1);
          continue;
        case 2:
        case 1:
        case 3:
        }
      }
      finally
      {
      }
      onTouchEventMove(f2, f1);
    }
    this.mVelocityTracker.computeCurrentVelocity(1000, 1250.0F);
    float f3 = this.mVelocityTracker.getXVelocity();
    if (i == 3);
    for (boolean bool = true; ; bool = false)
    {
      onTouchEventDone(f2, f3, bool);
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
      break;
    }
  }

  abstract boolean onTouchEventTriggeredTap(float paramFloat);

  protected void runScrollAnimation(float paramFloat1, float paramFloat2)
  {
    this.mScrollAnimation = createScrollAnimation(paramFloat1, ()Math.abs(1000.0F * paramFloat2));
    this.mScrollAnimation.start();
  }

  public void setLayoutMargin(int paramInt)
  {
    this.mLayoutMargin = paramInt;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DraggableHorizontalStrip
 * JD-Core Version:    0.6.2
 */
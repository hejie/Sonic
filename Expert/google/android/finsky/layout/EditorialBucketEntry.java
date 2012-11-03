package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.widget.FrameLayout;

public class EditorialBucketEntry extends FrameLayout
{
  private final int mHalfSeparatorThickness;
  private final Paint mSeparatorPaint;
  private final float mSeparatorThickness;
  private boolean mShowLeft;

  public EditorialBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public EditorialBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public EditorialBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mSeparatorThickness = localResources.getDimensionPixelSize(2131427368);
    this.mHalfSeparatorThickness = ((int)FloatMath.ceil(this.mSeparatorThickness / 2.0F));
    this.mSeparatorPaint = new Paint();
    this.mSeparatorPaint.setColor(localResources.getColor(2131361807));
    this.mSeparatorPaint.setStrokeWidth(this.mSeparatorThickness);
  }

  public void onDraw(Canvas paramCanvas)
  {
    int i = getHeight();
    if (this.mShowLeft)
    {
      int k = this.mHalfSeparatorThickness;
      paramCanvas.drawLine(k, 0.0F, k, i, this.mSeparatorPaint);
    }
    int j = this.mHalfSeparatorThickness;
    paramCanvas.drawLine(0.0F, j, getWidth(), j, this.mSeparatorPaint);
    super.onDraw(paramCanvas);
  }

  public void setLeftSeparatorVisibility(boolean paramBoolean)
  {
    this.mShowLeft = paramBoolean;
    invalidate();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EditorialBucketEntry
 * JD-Core Version:    0.6.2
 */
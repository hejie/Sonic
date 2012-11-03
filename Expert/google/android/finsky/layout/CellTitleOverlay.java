package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import com.android.vending.R.styleable;

public class CellTitleOverlay extends DecoratedTextView
{
  private int mBackgroundColor;
  private int mCornerSize;
  private float mMaxWidthPercent;
  private float mMinWidthPercent;
  private Paint mPaint;
  private Path mPath;

  public CellTitleOverlay(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public CellTitleOverlay(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CellTitleOverlay(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CellTitleOverlay);
    this.mMinWidthPercent = localTypedArray.getFloat(0, 0.25F);
    this.mMaxWidthPercent = localTypedArray.getFloat(1, 0.85F);
    localTypedArray.recycle();
    Resources localResources = paramContext.getResources();
    this.mBackgroundColor = localResources.getColor(2131361848);
    this.mCornerSize = localResources.getDimensionPixelSize(2131427406);
    setWillNotDraw(false);
    this.mPaint = new Paint();
    this.mPath = new Path();
  }

  public float getMaxWidthPercent()
  {
    return this.mMaxWidthPercent;
  }

  public float getMinWidthPercent()
  {
    return this.mMinWidthPercent;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = getWidth();
    int j = getHeight();
    this.mPath.reset();
    this.mPath.moveTo(0.0F, 0.0F);
    this.mPath.lineTo(i, 0.0F);
    this.mPath.lineTo(i, j - this.mCornerSize);
    this.mPath.lineTo(i - this.mCornerSize, j);
    this.mPath.lineTo(0.0F, j);
    this.mPath.close();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(this.mBackgroundColor);
    paramCanvas.drawPath(this.mPath, this.mPaint);
    super.onDraw(paramCanvas);
  }

  public void setBackgroundColor(int paramInt)
  {
    this.mBackgroundColor = paramInt;
    invalidate();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CellTitleOverlay
 * JD-Core Version:    0.6.2
 */
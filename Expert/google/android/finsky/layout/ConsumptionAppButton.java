package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import com.android.vending.R.styleable;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class ConsumptionAppButton extends Button
{
  private Drawable mIconBitmap;
  private Paint mTextPaint = new Paint();
  private float mTextWidth;
  private boolean mUseHoloDarkIcon;

  public ConsumptionAppButton(Context paramContext)
  {
    this(paramContext, null);
  }

  public ConsumptionAppButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ConsumptionAppButton);
    this.mUseHoloDarkIcon = localTypedArray.getBoolean(0, false);
    localTypedArray.recycle();
    setWillNotDraw(false);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mIconBitmap == null);
    while (true)
    {
      return;
      paramCanvas.save();
      int i = (getHeight() - this.mIconBitmap.getIntrinsicHeight()) / 2;
      int j = getPaddingLeft();
      int k = getPaddingRight();
      int m = getWidth() - j - k;
      int n = (int)this.mTextWidth + this.mIconBitmap.getIntrinsicWidth();
      paramCanvas.translate((j + m - n) / 2 + getScrollX(), i);
      this.mIconBitmap.draw(paramCanvas);
      paramCanvas.restore();
    }
  }

  public void setDocumentBackend(int paramInt)
  {
    if (this.mUseHoloDarkIcon);
    for (int i = CorpusResourceUtils.getHoloDarkOpenButtonDrawableId(paramInt); ; i = CorpusResourceUtils.getOpenButtonDrawableId(paramInt))
    {
      this.mIconBitmap = getResources().getDrawable(i);
      this.mIconBitmap.setBounds(0, 0, this.mIconBitmap.getIntrinsicWidth(), this.mIconBitmap.getIntrinsicHeight());
      this.mTextPaint.setTextSize(getTextSize());
      this.mTextWidth = this.mTextPaint.measureText(getText().toString());
      invalidate();
      requestLayout();
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ConsumptionAppButton
 * JD-Core Version:    0.6.2
 */
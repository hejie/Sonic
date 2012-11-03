package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.android.vending.R.styleable;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;

public class DecoratedTextView extends TextView
  implements BitmapLoader.BitmapLoadedHandler
{
  private Paint mBorderPaint;
  private int mDecorationPosition;
  private boolean mDrawBorder;
  private boolean mUseWhitescale;

  public DecoratedTextView(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public DecoratedTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public DecoratedTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DecoratedTextView);
    this.mDecorationPosition = localTypedArray.getInt(0, 1);
    this.mUseWhitescale = localTypedArray.getBoolean(1, false);
    localTypedArray.recycle();
    this.mBorderPaint = new Paint();
    this.mBorderPaint.setStrokeWidth(2.0F);
    this.mBorderPaint.setStyle(Paint.Style.STROKE);
    setWillNotDraw(false);
  }

  private static Bitmap getWhitescale(Bitmap paramBitmap)
  {
    Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), localConfig);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColorFilter(new LightingColorFilter(-1, 16777215));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }

  private void setBitmap(Bitmap paramBitmap)
  {
    if (this.mUseWhitescale)
      paramBitmap = getWhitescale(paramBitmap);
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(getResources(), paramBitmap);
    switch (this.mDecorationPosition)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      setCompoundDrawablesWithIntrinsicBounds(localBitmapDrawable, null, null, null);
      continue;
      setCompoundDrawablesWithIntrinsicBounds(null, null, localBitmapDrawable, null);
    }
  }

  private void setDrawable(int paramInt)
  {
    switch (this.mDecorationPosition)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      setCompoundDrawablesWithIntrinsicBounds(paramInt, 0, 0, 0);
      continue;
      setCompoundDrawablesWithIntrinsicBounds(0, 0, paramInt, 0);
    }
  }

  public void loadDecoration(int paramInt)
  {
    setDrawable(paramInt);
  }

  public void loadDecoration(BitmapLoader paramBitmapLoader, String paramString, int paramInt)
  {
    Bitmap localBitmap = paramBitmapLoader.get(paramString, null, this, paramInt, paramInt).getBitmap();
    if (localBitmap != null)
      setBitmap(localBitmap);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mDrawBorder)
      paramCanvas.drawRect(1.0F, 1.0F, -1 + getWidth(), -2 + getHeight(), this.mBorderPaint);
  }

  public void onResponse(BitmapLoader.BitmapContainer paramBitmapContainer)
  {
    Bitmap localBitmap = paramBitmapContainer.getBitmap();
    if (localBitmap == null);
    while (true)
    {
      return;
      setBitmap(localBitmap);
    }
  }

  public void setContentColorId(int paramInt, boolean paramBoolean)
  {
    if (this.mUseWhitescale)
      paramInt = 2131361794;
    int i = getResources().getColor(paramInt);
    setTextColor(i);
    this.mDrawBorder = paramBoolean;
    if (this.mDrawBorder)
      this.mBorderPaint.setColor(i);
    invalidate();
  }

  public boolean useWhitescale()
  {
    return this.mUseWhitescale;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DecoratedTextView
 * JD-Core Version:    0.6.2
 */
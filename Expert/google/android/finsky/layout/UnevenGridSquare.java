package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class UnevenGridSquare extends RelativeLayout
{
  private Paint mReflectionSeparatorPaint;

  public UnevenGridSquare(Context paramContext)
  {
    this(paramContext, null);
  }

  public UnevenGridSquare(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public UnevenGridSquare(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setWillNotDraw(false);
    this.mReflectionSeparatorPaint = new Paint();
    this.mReflectionSeparatorPaint.setColor(-16777216);
    this.mReflectionSeparatorPaint.setAntiAlias(false);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    ImageView localImageView = (ImageView)findViewById(2131231266);
    Drawable localDrawable = localImageView.getDrawable();
    if (localDrawable == null);
    while (true)
    {
      return;
      int i = localImageView.getBottom();
      int j = localImageView.getHeight();
      paramCanvas.save();
      paramCanvas.translate(0.0F, 1 + i * 2);
      float f = j / localDrawable.getIntrinsicHeight();
      paramCanvas.scale(f, -f);
      localDrawable.draw(paramCanvas);
      paramCanvas.restore();
      paramCanvas.drawLine(0.0F, i, getWidth(), i, this.mReflectionSeparatorPaint);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ImageView localImageView = (ImageView)findViewById(2131231266);
    View localView = findViewById(2131231267);
    RatingBar localRatingBar = (RatingBar)findViewById(2131231069);
    TextView localTextView = (TextView)findViewById(2131230752);
    int i = getWidth();
    int j = getHeight();
    localImageView.layout(0, 0, i, localImageView.getMeasuredHeight());
    localView.layout(0, j - localView.getMeasuredHeight(), i, j);
    RelativeLayout.LayoutParams localLayoutParams1 = (RelativeLayout.LayoutParams)localRatingBar.getLayoutParams();
    localRatingBar.layout(localLayoutParams1.leftMargin, j - localRatingBar.getMeasuredHeight() - localLayoutParams1.bottomMargin, localLayoutParams1.leftMargin + localRatingBar.getMeasuredWidth(), j - localLayoutParams1.bottomMargin);
    RelativeLayout.LayoutParams localLayoutParams2 = (RelativeLayout.LayoutParams)localTextView.getLayoutParams();
    localTextView.layout(i - localTextView.getMeasuredWidth() - localLayoutParams2.rightMargin, j - localTextView.getMeasuredHeight() - localLayoutParams2.bottomMargin, i - localLayoutParams2.rightMargin, j - localLayoutParams2.bottomMargin);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    ImageView localImageView = (ImageView)findViewById(2131231266);
    View localView = findViewById(2131231267);
    RatingBar localRatingBar = (RatingBar)findViewById(2131231069);
    TextView localTextView = (TextView)findViewById(2131230752);
    Drawable localDrawable = localImageView.getDrawable();
    if (localDrawable == null)
    {
      int k = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
      localImageView.measure(paramInt1, k);
      localView.measure(paramInt1, k);
    }
    while (true)
    {
      localRatingBar.measure(0, 0);
      localTextView.measure(0, 0);
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
      return;
      int i = View.MeasureSpec.getSize(paramInt1);
      float f = localDrawable.getIntrinsicWidth() / i;
      int j = (int)(localDrawable.getIntrinsicHeight() / f);
      localImageView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(j, 1073741824));
      localView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt2) - j, 1073741824));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.UnevenGridSquare
 * JD-Core Version:    0.6.2
 */
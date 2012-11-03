package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;

public class RelatedArtistBucketEntry extends ViewGroup
  implements DocumentCell
{
  private View mAccessibilityOverlay;
  private final int mHalfSeparatorThickness;
  private DocImageView mImageView;
  private boolean mIsLastRow = false;
  private final Paint mSeparatorPaint;
  private final float mSeparatorThickness;
  private DecoratedTextView mTitleView;

  public RelatedArtistBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public RelatedArtistBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public RelatedArtistBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mSeparatorThickness = localResources.getDimensionPixelSize(2131427368);
    this.mHalfSeparatorThickness = ((int)FloatMath.ceil(this.mSeparatorThickness / 2.0F));
    this.mSeparatorPaint = new Paint();
    this.mSeparatorPaint.setColor(localResources.getColor(2131361807));
    this.mSeparatorPaint.setStrokeWidth(this.mSeparatorThickness);
    setWillNotDraw(false);
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    this.mTitleView.setText(paramDocument2.getTitle());
    BadgeUtils.configureItemBadge(paramDocument2, paramBitmapLoader, this.mTitleView, -1);
    this.mImageView.bind(paramDocument2, paramBitmapLoader);
    this.mAccessibilityOverlay.setContentDescription(paramDocument2.getTitle());
    this.mAccessibilityOverlay.setOnClickListener(paramOnClickListener);
    this.mIsLastRow = paramBoolean;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = getWidth();
    int j = getHeight();
    if (!this.mIsLastRow)
    {
      int k = j - this.mHalfSeparatorThickness;
      paramCanvas.drawLine(0.0F, k, i, k, this.mSeparatorPaint);
    }
    super.onDraw(paramCanvas);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mImageView = ((DocImageView)findViewById(2131230742));
    this.mTitleView = ((DecoratedTextView)findViewById(2131231210));
    this.mAccessibilityOverlay = findViewById(2131230744);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = this.mImageView.getMeasuredWidth();
    this.mImageView.layout(0, 0, i, this.mImageView.getMeasuredHeight());
    this.mTitleView.layout(i, 0, i + this.mTitleView.getMeasuredWidth(), this.mTitleView.getMeasuredHeight());
    this.mAccessibilityOverlay.layout(0, 0, this.mAccessibilityOverlay.getMeasuredWidth(), this.mAccessibilityOverlay.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = i / 4;
    int k = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
    this.mImageView.measure(View.MeasureSpec.makeMeasureSpec(i / 2, 1073741824), k);
    this.mTitleView.measure(View.MeasureSpec.makeMeasureSpec(i / 2, -2147483648), k);
    this.mAccessibilityOverlay.measure(paramInt1, k);
    setMeasuredDimension(i, j);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.RelatedArtistBucketEntry
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import com.android.vending.R.styleable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BitmapLoader;

public class ArtistBucketEntry extends CellWithOverlay
  implements DocumentCell
{
  private View mAccessibilityOverlay;
  private boolean mEnforceRatio;
  private DocImageView mImageView;
  private float mRatio;

  public ArtistBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public ArtistBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ArtistBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ArtistBucketEntry);
    this.mRatio = localTypedArray.getFloat(0, 0.5F);
    localTypedArray.recycle();
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    this.mEnforceRatio = true;
    this.mImageView.bind(paramDocument2, paramBitmapLoader);
    setOverlayCaption(paramDocument2, paramBitmapLoader);
    this.mAccessibilityOverlay.setContentDescription(paramDocument2.getTitle());
    this.mAccessibilityOverlay.setOnClickListener(paramOnClickListener);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mImageView = ((DocImageView)findViewById(2131230742));
    this.mAccessibilityOverlay = findViewById(2131230744);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!this.mEnforceRatio)
      super.onMeasure(paramInt1, paramInt2);
    while (true)
    {
      return;
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec((int)(View.MeasureSpec.getSize(paramInt1) * this.mRatio), 1073741824));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ArtistBucketEntry
 * JD-Core Version:    0.6.2
 */
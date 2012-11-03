package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.android.vending.R.styleable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;

public class HeroGraphicView extends YoutubeFrameView
{
  private int mMaxHeight;
  private boolean mRestrictHeight;

  public HeroGraphicView(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public HeroGraphicView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public HeroGraphicView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mMaxHeight = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HeroGraphicView).getDimensionPixelSize(0, 2147483647);
  }

  public void bindLightboxImage(final Document paramDocument, final NavigationManager paramNavigationManager, final boolean paramBoolean)
  {
    this.mAccessibilityOverlay.setClickable(true);
    this.mAccessibilityOverlay.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramBoolean)
          paramNavigationManager.goToImagesLightbox(paramDocument, 0, 2);
        while (true)
        {
          return;
          if (paramDocument.getImages(4) != null)
            paramNavigationManager.goToImagesLightbox(paramDocument, 0, 4);
          else if (paramDocument.getImages(0) != null)
            paramNavigationManager.goToImagesLightbox(paramDocument, 0, 0);
        }
      }
    });
  }

  public int getHeightRestriction()
  {
    return this.mMaxHeight;
  }

  public void hideAccessibilityOverlay()
  {
    this.mAccessibilityOverlay.setVisibility(8);
  }

  public void load(BitmapLoader paramBitmapLoader, Document paramDocument, String paramString, boolean paramBoolean)
  {
    this.mRestrictHeight = paramBoolean;
    int i = getLayoutParams().height;
    if (!TextUtils.isEmpty(paramString))
    {
      if ((this.mBitmapContainer == null) || (this.mBitmapContainer.getBitmap() == null))
        this.mBitmapContainer = paramBitmapLoader.get(paramString, null, this, 0, Math.max(i, 0));
      if (this.mBitmapContainer.getBitmap() != null)
        this.mThumbnailImageView.setImageBitmap(this.mBitmapContainer.getBitmap());
    }
    while (true)
    {
      return;
      setVisibility(8);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getWidth();
    int j = getHeight();
    int k = getPaddingRight();
    if (this.mThumbnailImageView.getDrawable() != null)
    {
      int i3 = this.mThumbnailImageView.getMeasuredWidth();
      int i4 = (i - i3 - k) / 2;
      this.mThumbnailImageView.layout(i4, 0, i4 + i3, j);
    }
    if (this.mPlayImageView.getVisibility() == 0)
    {
      int m = this.mPlayImageView.getMeasuredWidth();
      int n = this.mPlayImageView.getMeasuredHeight();
      int i1 = (i - m - k) / 2;
      int i2 = (j - n) / 2;
      this.mPlayImageView.layout(i1, i2, i1 + m, i2 + n);
    }
    if (this.mAccessibilityOverlay.getVisibility() == 0)
      this.mAccessibilityOverlay.layout(0, 0, i, j);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mPlayImageView.measure(0, 0);
    this.mAccessibilityOverlay.measure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    Drawable localDrawable = this.mThumbnailImageView.getDrawable();
    if (localDrawable == null)
      setMeasuredDimension(i, View.MeasureSpec.getSize(paramInt2));
    while (true)
    {
      return;
      int j = localDrawable.getIntrinsicWidth();
      int k = localDrawable.getIntrinsicHeight();
      int m = i * k / j;
      int n = View.MeasureSpec.makeMeasureSpec(m, 1073741824);
      if (this.mRestrictHeight)
      {
        if (m <= this.mMaxHeight)
        {
          this.mThumbnailImageView.measure(paramInt1, n);
          setMeasuredDimension(i, m);
        }
        else
        {
          int i2 = View.MeasureSpec.makeMeasureSpec(j * this.mMaxHeight / k, 1073741824);
          int i3 = View.MeasureSpec.makeMeasureSpec(this.mMaxHeight, 1073741824);
          this.mThumbnailImageView.measure(i2, i3);
          setMeasuredDimension(i, this.mMaxHeight);
        }
      }
      else
      {
        if (getLayoutParams().height != m)
        {
          getLayoutParams().height = m;
          requestLayout();
        }
        int i1 = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        this.mThumbnailImageView.measure(i1, n);
        setMeasuredDimension(i, m);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.HeroGraphicView
 * JD-Core Version:    0.6.2
 */
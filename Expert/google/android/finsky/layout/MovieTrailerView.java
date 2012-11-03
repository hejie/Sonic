package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;

public class MovieTrailerView extends YoutubeFrameView
{
  private BitmapLoader mBitmapLoader;
  private TextView mCurtainCaptionView;
  private int mLastRequestedHeight;
  private String mTrailerFrameUrl;

  public MovieTrailerView(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public MovieTrailerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public MovieTrailerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void loadTrailerFrameIfNecessary()
  {
    int i = this.mThumbnailImageView.getWidth();
    int j = this.mThumbnailImageView.getHeight();
    if ((i > 0) && (j > 0) && (j != this.mLastRequestedHeight) && (!TextUtils.isEmpty(this.mTrailerFrameUrl)) && (this.mBitmapLoader != null))
    {
      BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)getTag();
      if ((localBitmapContainer == null) || (localBitmapContainer.getRequestUrl() == null))
        break label69;
    }
    while (true)
    {
      return;
      label69: if ((this.mBitmapContainer == null) || (this.mBitmapContainer.getBitmap() == null))
        this.mBitmapContainer = this.mBitmapLoader.get(this.mTrailerFrameUrl, null, this, 0, j);
      if (this.mBitmapContainer.getBitmap() != null)
      {
        this.mThumbnailImageView.setImageBitmap(this.mBitmapContainer.getBitmap());
        requestLayout();
      }
      setTag(this.mBitmapContainer);
      this.mLastRequestedHeight = j;
    }
  }

  public void load(BitmapLoader paramBitmapLoader, String paramString)
  {
    this.mTrailerFrameUrl = paramString;
    this.mBitmapLoader = paramBitmapLoader;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mCurtainCaptionView = ((TextView)findViewById(2131231087));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getWidth();
    int j = getHeight();
    int k = getPaddingRight();
    int m = this.mThumbnailImageView.getMeasuredWidth();
    int n = (i - m - k) / 2;
    this.mThumbnailImageView.layout(n, 0, n + m, j);
    loadTrailerFrameIfNecessary();
    if (this.mPlayImageView.getVisibility() == 0)
    {
      int i1 = this.mPlayImageView.getMeasuredWidth();
      int i2 = this.mPlayImageView.getMeasuredHeight();
      int i3 = (i - i1 - k) / 2;
      int i4 = (j - i2) / 2;
      this.mPlayImageView.layout(i3, i4, i3 + i1, i4 + i2);
    }
    if (this.mCurtainCaptionView.getVisibility() == 0)
      this.mCurtainCaptionView.layout(0, j - this.mCurtainCaptionView.getMeasuredHeight(), this.mCurtainCaptionView.getMeasuredWidth(), j);
    if (this.mAccessibilityOverlay.getVisibility() == 0)
      this.mAccessibilityOverlay.layout(0, 0, i, j);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mPlayImageView.measure(0, 0);
    this.mAccessibilityOverlay.measure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt2);
    this.mThumbnailImageView.measure(paramInt1, paramInt2);
    if (this.mCurtainCaptionView.getVisibility() == 0)
    {
      int k = View.MeasureSpec.makeMeasureSpec(i - getPaddingRight(), 1073741824);
      this.mCurtainCaptionView.measure(k, 0);
    }
    if (this.mPlayImageView.getVisibility() == 0)
      this.mPlayImageView.measure(0, 0);
    setMeasuredDimension(i, j);
  }

  public void setCurtainCaption(String paramString)
  {
    this.mCurtainCaptionView.setText(paramString);
    this.mCurtainCaptionView.setVisibility(0);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.MovieTrailerView
 * JD-Core Version:    0.6.2
 */
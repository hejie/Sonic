package com.google.android.finsky.layout;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.ThumbnailUtils;

public class ThumbnailListener
  implements BitmapLoader.BitmapLoadedHandler
{
  private final boolean mFadeIn;
  protected final ImageView mImageView;
  protected final View mViewToFadeIn;

  public ThumbnailListener(ImageView paramImageView, boolean paramBoolean)
  {
    this(paramImageView, paramBoolean, null);
  }

  public ThumbnailListener(ImageView paramImageView, boolean paramBoolean, View paramView)
  {
    this.mImageView = paramImageView;
    this.mFadeIn = paramBoolean;
    this.mViewToFadeIn = paramView;
  }

  protected void onImageFailed()
  {
  }

  public void onResponse(BitmapLoader.BitmapContainer paramBitmapContainer)
  {
    Bitmap localBitmap = paramBitmapContainer.getBitmap();
    if (localBitmap == null)
      onImageFailed();
    while (true)
    {
      return;
      if (this.mFadeIn)
      {
        if (this.mViewToFadeIn != null)
        {
          Animation localAnimation = AnimationUtils.loadAnimation(this.mImageView.getContext(), 2131034114);
          this.mImageView.setImageBitmap(localBitmap);
          this.mViewToFadeIn.startAnimation(localAnimation);
          this.mViewToFadeIn.setVisibility(0);
        }
        else
        {
          ThumbnailUtils.setImageBitmapWithFade(this.mImageView, localBitmap);
        }
      }
      else
        this.mImageView.setImageBitmap(localBitmap);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ThumbnailListener
 * JD-Core Version:    0.6.2
 */
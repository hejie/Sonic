package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BitmapLoader;

public class ScreenshotView extends FrameLayout
  implements FifeImageView.OnLoadedListener
{
  private Runnable mFadeInRunnable = new FadeInViewRunnable(null);
  private Handler mHandler = new Handler();
  private FifeImageView mImageView;
  private ProgressBar mProgressBar;

  public ScreenshotView(Context paramContext)
  {
    super(paramContext);
  }

  public ScreenshotView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ScreenshotView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mHandler.removeCallbacks(this.mFadeInRunnable);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mImageView = ((FifeImageView)findViewById(2131231250));
    this.mProgressBar = ((ProgressBar)findViewById(2131231249));
  }

  public void onLoaded(ImageView paramImageView)
  {
    this.mHandler.removeCallbacks(this.mFadeInRunnable);
    if (this.mProgressBar.getVisibility() == 0)
    {
      Animation localAnimation = AnimationUtils.loadAnimation(getContext(), 2131034113);
      localAnimation.setAnimationListener(new HideAfterEndAnimationListener(null));
      this.mProgressBar.startAnimation(localAnimation);
    }
  }

  public void setImage(Doc.Image paramImage, BitmapLoader paramBitmapLoader)
  {
    this.mImageView.setViewToFadeIn(this.mImageView);
    this.mImageView.setOnLoadedListener(this);
    this.mImageView.setImage(paramImage, paramBitmapLoader);
    if (!this.mImageView.isLoaded())
      this.mHandler.postDelayed(this.mFadeInRunnable, 500L);
  }

  private class FadeInViewRunnable
    implements Runnable
  {
    private FadeInViewRunnable()
    {
    }

    public void run()
    {
      Animation localAnimation = AnimationUtils.loadAnimation(ScreenshotView.this.mProgressBar.getContext(), 2131034112);
      ScreenshotView.this.mProgressBar.setVisibility(0);
      ScreenshotView.this.mProgressBar.startAnimation(localAnimation);
    }
  }

  private class HideAfterEndAnimationListener
    implements Animation.AnimationListener
  {
    private HideAfterEndAnimationListener()
    {
    }

    public void onAnimationEnd(Animation paramAnimation)
    {
      ScreenshotView.this.mProgressBar.setVisibility(8);
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ScreenshotView
 * JD-Core Version:    0.6.2
 */
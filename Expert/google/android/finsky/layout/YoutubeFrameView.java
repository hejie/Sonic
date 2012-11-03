package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.ThumbnailUtils;

public abstract class YoutubeFrameView extends ViewGroup
  implements BitmapLoader.BitmapLoadedHandler
{
  protected View mAccessibilityOverlay;
  protected BitmapLoader.BitmapContainer mBitmapContainer;
  protected ImageView mPlayImageView;
  protected ImageView mThumbnailImageView;

  public YoutubeFrameView(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public YoutubeFrameView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public YoutubeFrameView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onDetachedFromWindow()
  {
    if (this.mBitmapContainer != null)
      this.mBitmapContainer.cancelRequest();
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mThumbnailImageView = ((ImageView)findViewById(2131231062));
    this.mPlayImageView = ((ImageView)findViewById(2131231063));
    this.mAccessibilityOverlay = findViewById(2131230744);
  }

  public void onResponse(BitmapLoader.BitmapContainer paramBitmapContainer)
  {
    Bitmap localBitmap = paramBitmapContainer.getBitmap();
    if (localBitmap == null)
      setVisibility(8);
    while (true)
    {
      return;
      setVisibility(0);
      ThumbnailUtils.setImageBitmapWithFade(this.mThumbnailImageView, localBitmap);
      requestLayout();
    }
  }

  public void setContentDescription(int paramInt)
  {
    this.mAccessibilityOverlay.setContentDescription(getContext().getString(paramInt));
  }

  public void showPlayIcon(final String paramString)
  {
    this.mPlayImageView.setVisibility(0);
    this.mAccessibilityOverlay.setVisibility(0);
    this.mAccessibilityOverlay.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = IntentUtils.createYouTubeIntentForUrl(YoutubeFrameView.this.getContext().getPackageManager(), Uri.parse(paramString), FinskyApp.get().getCurrentAccountName());
        YoutubeFrameView.this.getContext().startActivity(localIntent);
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.YoutubeFrameView
 * JD-Core Version:    0.6.2
 */
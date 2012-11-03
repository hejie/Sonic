package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.ThumbnailUtils;

public class VideoFrame extends FrameLayout
{
  public VideoFrame(Context paramContext)
  {
    super(paramContext);
  }

  public VideoFrame(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public VideoFrame(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void configureFilmOverlay(Document paramDocument, BitmapLoader paramBitmapLoader)
  {
    findViewById(2131231269).setVisibility(8);
    findViewById(2131231272).setVisibility(8);
    View localView = ((ViewStub)findViewById(2131231270)).inflate();
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837769);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.CLAMP, Shader.TileMode.REPEAT);
    localView.findViewById(2131231274).setBackgroundDrawable(localBitmapDrawable);
    localView.findViewById(2131231275).setBackgroundDrawable(localBitmapDrawable);
    ImageView localImageView = (ImageView)localView.findViewById(2131231273);
    int i = localImageView.getLayoutParams().width;
    int j = localImageView.getLayoutParams().height;
    String str = ThumbnailUtils.getIconUrlFromDocument(paramDocument, i, 0);
    if (str != null)
    {
      BitmapLoader.BitmapContainer localBitmapContainer = paramBitmapLoader.get(str, null, new ThumbnailListener(localImageView, true), i, j);
      if (localBitmapContainer.getBitmap() != null)
        localImageView.setImageBitmap(localBitmapContainer.getBitmap());
    }
  }

  public void configurePreviewWithFlatOverlay(Drawable paramDrawable)
  {
    ImageView localImageView = (ImageView)findViewById(2131231269);
    localImageView.setVisibility(0);
    View localView = findViewById(2131231271);
    if (localView != null)
      localView.setVisibility(8);
    findViewById(2131231272).setVisibility(0);
    localImageView.setImageDrawable(paramDrawable);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.VideoFrame
 * JD-Core Version:    0.6.2
 */
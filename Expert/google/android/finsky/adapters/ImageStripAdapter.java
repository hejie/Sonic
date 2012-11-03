package com.google.android.finsky.adapters;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.VideoFrame;
import com.google.android.finsky.remoting.protos.Doc.Image.Dimension;
import com.google.android.finsky.utils.BitmapLoader;

public class ImageStripAdapter
{
  private final BitmapLoader mBitmapLoader;
  private final DataSetObservable mDataSetObservable;
  private final Document mDoc;
  private final int mImageCount;
  private final Doc.Image.Dimension[] mImageDimensions;
  private int mImageIndexOffset;
  private final Drawable[] mImages;
  private final boolean mLoadVideoFilmOverlay;
  private final int mVideoCount;
  private final int mVideoFrameWidth;

  public ImageStripAdapter(Document paramDocument, BitmapLoader paramBitmapLoader, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    this.mDoc = paramDocument;
    this.mBitmapLoader = paramBitmapLoader;
    this.mImageCount = paramInt1;
    this.mVideoCount = paramInt2;
    this.mImages = new Drawable[this.mImageCount + this.mVideoCount];
    this.mImageDimensions = new Doc.Image.Dimension[this.mImageCount + this.mVideoCount];
    this.mDataSetObservable = new DataSetObservable();
    this.mImageIndexOffset = this.mVideoCount;
    this.mLoadVideoFilmOverlay = paramBoolean;
    this.mVideoFrameWidth = paramInt3;
  }

  public int getChildCount()
  {
    return this.mImageCount + this.mVideoCount;
  }

  public Drawable getImageAt(int paramInt)
  {
    return this.mImages[paramInt];
  }

  public void getImageDimensionAt(int paramInt, Doc.Image.Dimension paramDimension, float paramFloat)
  {
    Drawable localDrawable = getImageAt(paramInt);
    if (localDrawable != null)
    {
      paramDimension.setWidth((int)(paramFloat * localDrawable.getIntrinsicWidth()));
      paramDimension.setHeight((int)(paramFloat * localDrawable.getIntrinsicHeight()));
    }
    while (true)
    {
      return;
      if (this.mImageDimensions[paramInt] != null)
      {
        paramDimension.setWidth(this.mImageDimensions[paramInt].getWidth());
        paramDimension.setHeight(this.mImageDimensions[paramInt].getHeight());
      }
      else
      {
        paramDimension.setWidth(0);
        paramDimension.setHeight(0);
      }
    }
  }

  public View getViewAt(Context paramContext, ViewGroup paramViewGroup, int paramInt)
  {
    Object localObject;
    if (paramInt < this.mVideoCount)
    {
      localObject = (VideoFrame)LayoutInflater.from(paramContext).inflate(2130968849, paramViewGroup, false);
      if (this.mLoadVideoFilmOverlay)
      {
        ((VideoFrame)localObject).getLayoutParams().width = this.mVideoFrameWidth;
        ((VideoFrame)localObject).configureFilmOverlay(this.mDoc, this.mBitmapLoader);
      }
    }
    while (true)
    {
      return localObject;
      ImageView localImageView = new ImageView(paramContext);
      localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
      localObject = localImageView;
    }
  }

  public boolean hasImageDimensionAt(int paramInt)
  {
    boolean bool = true;
    if (getImageAt(paramInt) != null);
    while (true)
    {
      return bool;
      if (this.mImageDimensions[paramInt] == null)
        bool = false;
    }
  }

  public boolean hasLeadingMargin()
  {
    if (this.mVideoCount == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void notifyDataSetChanged()
  {
    this.mDataSetObservable.notifyChanged();
  }

  public void notifyDataSetInvalidated()
  {
    this.mDataSetObservable.notifyInvalidated();
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mDataSetObservable.registerObserver(paramDataSetObserver);
  }

  public void setImageAt(int paramInt, Drawable paramDrawable)
  {
    this.mImages[paramInt] = paramDrawable;
    notifyDataSetChanged();
  }

  public void setImageDimensionAt(int paramInt, Doc.Image.Dimension paramDimension)
  {
    this.mImageDimensions[paramInt] = paramDimension;
  }

  public int toImageIndex(int paramInt)
  {
    return paramInt - this.mImageIndexOffset;
  }

  public int toVideoIndex(int paramInt)
  {
    return paramInt;
  }

  public void unregisterAll()
  {
    this.mDataSetObservable.unregisterAll();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.ImageStripAdapter
 * JD-Core Version:    0.6.2
 */
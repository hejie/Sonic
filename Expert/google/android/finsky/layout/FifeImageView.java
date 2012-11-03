package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.android.vending.R.styleable;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.ThumbnailUtils;

public class FifeImageView extends ImageView
{
  BitmapLoader mBitmapLoader;
  private Drawable mFocusedDrawable;
  private Doc.Image mImage;
  boolean mIsLoaded;
  private int mMaxHeight = 0;
  private int mMaxWidth = 0;
  private OnLoadedListener mOnLoadedListener;
  private Drawable mPressedDrawable;
  int mRequestCount;
  private boolean mToFadeInAfterLoad;
  private boolean mToLoadOnce;
  private View mViewToFadeIn;
  private boolean mWaitForLayout;

  public FifeImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public FifeImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public FifeImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HeroGraphicView);
    this.mMaxHeight = localTypedArray1.getDimensionPixelSize(0, 2147483647);
    localTypedArray1.recycle();
    TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FifeImageView);
    this.mToLoadOnce = localTypedArray2.getBoolean(0, false);
    this.mToFadeInAfterLoad = localTypedArray2.getBoolean(1, true);
    localTypedArray2.recycle();
  }

  private String getFifeUrl(String paramString)
  {
    int i = 0;
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    int j;
    int k;
    label29: int m;
    if (localLayoutParams.width == -1)
    {
      j = 1;
      if (localLayoutParams.height != -1)
        break label105;
      k = 1;
      if ((j == 0) || (k == 0))
        break label111;
      m = getWidth();
      i = getHeight();
      if (this.mMaxWidth > 0)
        m = Math.min(m, this.mMaxWidth);
      if (this.mMaxHeight > 0)
        i = Math.min(i, this.mMaxHeight);
      if ((m != 0) && (i != 0))
        break label165;
    }
    for (String str = null; ; str = null)
    {
      return str;
      j = 0;
      break;
      label105: k = 0;
      break label29;
      label111: if (j == 0)
        break label177;
      m = getWidth();
      if (this.mMaxWidth > 0)
        m = Math.min(m, this.mMaxWidth);
      if (m != 0)
        break label151;
    }
    label151: if (this.mMaxHeight != 2147483647)
      i = this.mMaxHeight;
    while (true)
    {
      label165: str = ThumbnailUtils.buildFifeUrl(paramString, m, i);
      break;
      label177: if (localLayoutParams.width > 0)
      {
        m = getWidth();
        if (localLayoutParams.height > 0)
          i = localLayoutParams.height;
      }
      else
      {
        if (k != 0)
        {
          i = getHeight();
          if (this.mMaxHeight > 0)
            i = Math.min(i, this.mMaxHeight);
          if (i == 0)
          {
            str = null;
            break;
          }
          m = this.mMaxWidth;
          continue;
        }
        m = getWidth();
        i = getHeight();
      }
    }
  }

  private String getImageUrlToLoad(Doc.Image paramImage)
  {
    String str;
    if (paramImage == null)
      str = null;
    while (true)
    {
      return str;
      str = paramImage.getImageUrl();
      if (TextUtils.isEmpty(str))
        str = null;
      else if (paramImage.getSupportsFifeUrlOptions())
        str = getFifeUrl(str);
    }
  }

  private void invokeOnLoaded()
  {
    if (this.mOnLoadedListener != null)
      this.mOnLoadedListener.onLoaded(this);
  }

  public void delayLoadingUntilLayout()
  {
    this.mWaitForLayout = true;
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    invalidate();
  }

  protected Doc.Image getImageToLoad()
  {
    return this.mImage;
  }

  protected Bitmap getPlaceholder()
  {
    return null;
  }

  public boolean isLoaded()
  {
    try
    {
      boolean bool = this.mIsLoaded;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void loadImageIfNecessary()
  {
    boolean bool = true;
    if (((this.mToLoadOnce) && (this.mRequestCount > 0)) || (this.mWaitForLayout));
    Bitmap localBitmap1;
    Bitmap localBitmap2;
    do
    {
      int i;
      int j;
      String str;
      while (true)
      {
        return;
        i = getWidth();
        j = getHeight();
        if ((i != 0) || (j != 0))
        {
          str = getImageUrlToLoad(getImageToLoad());
          if (!TextUtils.isEmpty(str))
            break;
          BitmapLoader.BitmapContainer localBitmapContainer3 = (BitmapLoader.BitmapContainer)getTag();
          if (localBitmapContainer3 != null)
          {
            localBitmapContainer3.cancelRequest();
            setImageBitmap(null);
          }
        }
      }
      localBitmap1 = getPlaceholder();
      BitmapLoader.BitmapContainer localBitmapContainer1 = (BitmapLoader.BitmapContainer)getTag();
      if ((localBitmapContainer1 != null) && (localBitmapContainer1.getRequestUrl() != null))
      {
        if (localBitmapContainer1.getRequestUrl().equals(str))
        {
          if (localBitmapContainer1.getBitmap() != localBitmap1);
          while (true)
          {
            setLoaded(bool);
            break;
            bool = false;
          }
        }
        localBitmapContainer1.cancelRequest();
      }
      this.mRequestCount = (1 + this.mRequestCount);
      ThumbnailListener local1 = new ThumbnailListener(this, this.mToFadeInAfterLoad, this.mViewToFadeIn)
      {
        public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
        {
          if (paramAnonymousBitmapContainer.getBitmap() != null);
          for (boolean bool = true; ; bool = false)
          {
            if (!bool)
            {
              FifeImageView localFifeImageView = FifeImageView.this;
              localFifeImageView.mRequestCount = (-1 + localFifeImageView.mRequestCount);
            }
            super.onResponse(paramAnonymousBitmapContainer);
            FifeImageView.this.setLoaded(bool);
            FifeImageView.this.invokeOnLoaded();
            return;
          }
        }
      };
      BitmapLoader.BitmapContainer localBitmapContainer2 = this.mBitmapLoader.get(str, localBitmap1, local1, i, j);
      setTag(localBitmapContainer2);
      localBitmap2 = localBitmapContainer2.getBitmap();
    }
    while (localBitmap2 == null);
    if (localBitmap2 != localBitmap1);
    while (true)
    {
      setImageBitmap(localBitmap2);
      setLoaded(bool);
      invokeOnLoaded();
      break;
      bool = false;
    }
  }

  protected void onDetachedFromWindow()
  {
    BitmapLoader.BitmapContainer localBitmapContainer = (BitmapLoader.BitmapContainer)getTag();
    if (localBitmapContainer != null)
      localBitmapContainer.cancelRequest();
    super.onDetachedFromWindow();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((isPressed()) && (isClickable()))
    {
      if (this.mPressedDrawable == null)
        this.mPressedDrawable = getResources().getDrawable(2130837704);
      this.mPressedDrawable.setBounds(0, 0, getWidth(), getHeight());
      this.mPressedDrawable.draw(paramCanvas);
    }
    while (true)
    {
      return;
      if (isFocused())
      {
        if (this.mFocusedDrawable == null)
          this.mFocusedDrawable = getResources().getDrawable(2130837703);
        this.mFocusedDrawable.setBounds(0, 0, getWidth(), getHeight());
        this.mFocusedDrawable.draw(paramCanvas);
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mWaitForLayout = false;
    loadImageIfNecessary();
  }

  public void setImage(Doc.Image paramImage, BitmapLoader paramBitmapLoader)
  {
    this.mImage = paramImage;
    this.mBitmapLoader = paramBitmapLoader;
    setLoaded(false);
    this.mRequestCount = 0;
    loadImageIfNecessary();
  }

  void setLoaded(boolean paramBoolean)
  {
    try
    {
      this.mIsLoaded = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setMaxHeight(int paramInt)
  {
    this.mMaxHeight = paramInt;
  }

  public void setMaxWidth(int paramInt)
  {
    this.mMaxWidth = paramInt;
  }

  public void setOnLoadedListener(OnLoadedListener paramOnLoadedListener)
  {
    this.mOnLoadedListener = paramOnLoadedListener;
  }

  public void setViewToFadeIn(View paramView)
  {
    this.mViewToFadeIn = paramView;
  }

  public static abstract interface OnLoadedListener
  {
    public abstract void onLoaded(ImageView paramImageView);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.FifeImageView
 * JD-Core Version:    0.6.2
 */
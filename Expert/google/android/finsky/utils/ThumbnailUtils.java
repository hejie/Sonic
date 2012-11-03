package com.google.android.finsky.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.remoting.protos.Doc.Image.Dimension;
import java.util.Iterator;
import java.util.List;

public class ThumbnailUtils
{
  private static String addFifeOptions(String paramString, StringBuilder paramStringBuilder)
  {
    String str1 = Uri.parse(paramString).getEncodedPath();
    if ((str1.length() > 1) && (str1.indexOf('/', 1) > 0) && (!paramString.endsWith("?fife")))
      paramStringBuilder.insert(0, "/");
    for (String str2 = new StringBuilder(paramString).insert(paramString.lastIndexOf("/"), paramStringBuilder).toString(); ; str2 = paramString + "=" + paramStringBuilder)
      return str2;
  }

  public static String buildCroppedFifeUrl(String paramString, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder("s");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("-p");
    return addFifeOptions(paramString, localStringBuilder);
  }

  public static String buildFifeUrl(String paramString, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (paramInt1 > 0)
    {
      i = 1;
      localStringBuilder.append("w").append(paramInt1);
    }
    if (paramInt2 > 0)
    {
      if (i != 0)
        localStringBuilder.append('-');
      localStringBuilder.append('h').append(paramInt2);
    }
    if (localStringBuilder.length() == 0);
    while (true)
    {
      return paramString;
      paramString = addFifeOptions(paramString, localStringBuilder);
    }
  }

  private static String getBestCroppedImageUrl(List<Doc.Image> paramList, int paramInt)
  {
    Doc.Image localImage = getBestImage(paramList, paramInt, paramInt);
    String str;
    if (localImage == null)
      str = null;
    while (true)
    {
      return str;
      if (localImage.getSupportsFifeUrlOptions())
        str = buildCroppedFifeUrl(localImage.getImageUrl(), paramInt);
      else
        str = localImage.getImageUrl();
    }
  }

  private static Doc.Image getBestImage(List<Doc.Image> paramList, int paramInt1, int paramInt2)
  {
    Object localObject2;
    if (paramList == null)
      localObject2 = null;
    while (true)
    {
      return localObject2;
      int i = 2147483647;
      int j = 2147483647;
      Object localObject1 = null;
      Iterator localIterator = paramList.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          break label125;
        localObject2 = (Doc.Image)localIterator.next();
        if (((Doc.Image)localObject2).getSupportsFifeUrlOptions())
          break;
        if (((Doc.Image)localObject2).getDimension() != null)
        {
          int k = ((Doc.Image)localObject2).getDimension().getWidth();
          int m = ((Doc.Image)localObject2).getDimension().getHeight();
          if ((k >= paramInt1) && (m >= paramInt2) && (i >= k) && (j >= m))
          {
            i = k;
            j = m;
            localObject1 = localObject2;
          }
        }
      }
      label125: if (localObject1 != null)
        localObject2 = localObject1;
      else if (paramList.size() > 0)
        localObject2 = (Doc.Image)paramList.get(-1 + paramList.size());
      else
        localObject2 = null;
    }
  }

  private static String getBestImageUrl(List<Doc.Image> paramList, int paramInt1, int paramInt2)
  {
    Doc.Image localImage = getBestImage(paramList, paramInt1, paramInt2);
    String str;
    if (localImage == null)
      str = null;
    while (true)
    {
      return str;
      if (localImage.getSupportsFifeUrlOptions())
        str = buildFifeUrl(localImage.getImageUrl(), paramInt1, paramInt2);
      else
        str = localImage.getImageUrl();
    }
  }

  public static String getCroppedIconUrlFromDocument(Document paramDocument, int paramInt)
  {
    String str = getBestCroppedImageUrl(paramDocument.getImages(4), paramInt);
    if (str == null)
      str = getBestCroppedImageUrl(paramDocument.getImages(0), paramInt);
    return str;
  }

  public static String getIconUrlFromDocument(Document paramDocument, int paramInt1, int paramInt2)
  {
    String str = getBestImageUrl(paramDocument.getImages(4), paramInt1, paramInt2);
    if (str == null)
      str = getBestImageUrl(paramDocument.getImages(0), paramInt1, paramInt2);
    return str;
  }

  public static Doc.Image getImageFromDocument(Document paramDocument, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int j = 0;
    Doc.Image localImage;
    if (j < i)
    {
      localImage = getBestImage(paramDocument.getImages(paramArrayOfInt[j]), paramInt1, paramInt2);
      if (localImage == null);
    }
    while (true)
    {
      return localImage;
      j++;
      break;
      localImage = null;
    }
  }

  public static String getPageHeaderBannerUrlFromDocument(Document paramDocument, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramBoolean);
    for (int i = 8; ; i = 9)
      return getBestImageUrl(paramDocument.getImages(i), paramInt1, paramInt2);
  }

  public static String getPreviewUrlFromDocument(Document paramDocument, int paramInt1, int paramInt2)
  {
    return getBestImageUrl(paramDocument.getImages(1), paramInt1, paramInt2);
  }

  public static String getPromoBitmapUrlFromDocument(Document paramDocument, int paramInt1, int paramInt2)
  {
    return getBestImageUrl(paramDocument.getImages(2), paramInt1, paramInt2);
  }

  public static String getScreenshotUrlFromDocument(Document paramDocument, int paramInt1, int paramInt2)
  {
    return getBestImageUrl(paramDocument.getImages(13), paramInt1, paramInt2);
  }

  public static void setImageBitmapWithFade(ImageView paramImageView, Bitmap paramBitmap)
  {
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramImageView.getResources(), paramBitmap);
    localBitmapDrawable.setGravity(51);
    setImageDrawableWithFade(paramImageView, localBitmapDrawable);
  }

  public static void setImageDrawableWithFade(ImageView paramImageView, Drawable paramDrawable)
  {
    Drawable localDrawable = paramImageView.getDrawable();
    if (localDrawable != null)
    {
      CustomBoundsTransitionDrawable localCustomBoundsTransitionDrawable = new CustomBoundsTransitionDrawable(new Drawable[] { localDrawable, paramDrawable }, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      localCustomBoundsTransitionDrawable.setCrossFadeEnabled(true);
      paramImageView.setImageDrawable(localCustomBoundsTransitionDrawable);
      localCustomBoundsTransitionDrawable.startTransition(250);
    }
    while (true)
    {
      return;
      paramImageView.setImageDrawable(paramDrawable);
    }
  }

  private static class CustomBoundsTransitionDrawable extends TransitionDrawable
  {
    private final int mIntrinsicHeight;
    private final int mIntrinsicWidth;

    public CustomBoundsTransitionDrawable(Drawable[] paramArrayOfDrawable, int paramInt1, int paramInt2)
    {
      super();
      this.mIntrinsicWidth = paramInt1;
      this.mIntrinsicHeight = paramInt2;
    }

    public int getIntrinsicHeight()
    {
      return this.mIntrinsicHeight;
    }

    public int getIntrinsicWidth()
    {
      return this.mIntrinsicWidth;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ThumbnailUtils
 * JD-Core Version:    0.6.2
 */
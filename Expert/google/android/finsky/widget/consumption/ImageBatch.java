package com.google.android.finsky.widget.consumption;

import android.net.Uri;
import java.util.List;

public class ImageBatch
{
  int backendId;
  BatchedImageLoader.BatchedImageCallback callback;
  List<ImageSpec> urisToLoad;

  public ImageBatch(int paramInt, List<ImageSpec> paramList, BatchedImageLoader.BatchedImageCallback paramBatchedImageCallback)
  {
    this.backendId = paramInt;
    this.urisToLoad = paramList;
    this.callback = paramBatchedImageCallback;
  }

  public boolean equals(Object paramObject)
  {
    if (((paramObject instanceof ImageBatch)) && (((ImageBatch)paramObject).backendId == this.backendId));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static class ImageSpec
  {
    public final int height;
    public final Uri uri;
    public final int width;

    public ImageSpec(Uri paramUri, int paramInt1, int paramInt2)
    {
      this.uri = paramUri;
      this.width = paramInt1;
      this.height = paramInt2;
    }

    public static ImageSpec merge(ImageSpec paramImageSpec1, ImageSpec paramImageSpec2)
    {
      if (!paramImageSpec1.uri.equals(paramImageSpec2.uri))
        throw new IllegalStateException("tried to merge wrappers with different uris!");
      return new ImageSpec(paramImageSpec1.uri, Math.max(paramImageSpec1.width, paramImageSpec2.width), Math.max(paramImageSpec1.height, paramImageSpec2.height));
    }

    public boolean satisfies(Uri paramUri, int paramInt1, int paramInt2)
    {
      if ((this.uri.equals(paramUri)) && (this.width >= paramInt1) && (this.height >= paramInt2));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.uri;
      arrayOfObject[1] = Integer.valueOf(this.width);
      arrayOfObject[2] = Integer.valueOf(this.height);
      return String.format("uri=[%s], [%s x %s]", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.ImageBatch
 * JD-Core Version:    0.6.2
 */
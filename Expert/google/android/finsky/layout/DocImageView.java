package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.android.vending.R.styleable;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ThumbnailUtils;

public class DocImageView extends FifeImageView
{
  private Document mDoc;
  private int[] mImageTypes;
  private int mPlaceholderType;

  public DocImageView(Context paramContext)
  {
    this(paramContext, null);
  }

  public DocImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public DocImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DocImageView);
    this.mPlaceholderType = localTypedArray.getInt(0, 1);
    localTypedArray.recycle();
  }

  public void bind(Document paramDocument, BitmapLoader paramBitmapLoader)
  {
    bind(paramDocument, paramBitmapLoader, new int[] { 4, 0 });
  }

  public void bind(Document paramDocument, BitmapLoader paramBitmapLoader, int[] paramArrayOfInt)
  {
    this.mDoc = paramDocument;
    this.mBitmapLoader = paramBitmapLoader;
    this.mImageTypes = paramArrayOfInt;
    setLoaded(false);
    this.mRequestCount = 0;
    loadImageIfNecessary();
  }

  protected Doc.Image getImageToLoad()
  {
    if (this.mDoc == null)
    {
      localImage = null;
      return localImage;
    }
    int i = getWidth();
    int j = getHeight();
    if (j > 0);
    for (Doc.Image localImage = ThumbnailUtils.getImageFromDocument(this.mDoc, 0, j, this.mImageTypes); ; localImage = ThumbnailUtils.getImageFromDocument(this.mDoc, i, 0, this.mImageTypes))
      break;
  }

  protected Bitmap getPlaceholder()
  {
    Bitmap localBitmap = null;
    if (this.mDoc == null);
    while (true)
    {
      return localBitmap;
      switch (this.mPlaceholderType)
      {
      default:
        break;
      case 1:
        localBitmap = CorpusResourceUtils.getPlaceholderIcon(this.mDoc.getBackend(), getResources());
        break;
      case 2:
        localBitmap = CorpusResourceUtils.getPlaceholderPromo(this.mDoc.getBackend(), getResources());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DocImageView
 * JD-Core Version:    0.6.2
 */
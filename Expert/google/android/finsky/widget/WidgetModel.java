package com.google.android.finsky.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.Lists;
import java.util.Collection;
import java.util.List;

public class WidgetModel
  implements Response.ErrorListener, OnDataChangedListener
{
  private final BitmapLoader mBitmapLoader;
  private final int[] mDocumentTypes;
  private final int mImageHeightResource;
  private final ImageSelector mImageSelector;
  private final List<PromotionalItem> mItems;
  private DfeList mList;
  private RefreshListener mListener;
  private int mLoadedImagesSoFar = 0;
  private int mMaxHeight;
  private final int mMaxItems;
  private int mSize;
  private boolean mUpdatePending;

  public WidgetModel(BitmapLoader paramBitmapLoader, int[] paramArrayOfInt, ImageSelector paramImageSelector, int paramInt1, int paramInt2)
  {
    this.mMaxItems = paramInt2;
    this.mItems = Lists.newArrayList(paramInt2);
    this.mBitmapLoader = paramBitmapLoader;
    this.mDocumentTypes = paramArrayOfInt;
    this.mImageSelector = paramImageSelector;
    this.mImageHeightResource = paramInt1;
  }

  private void bitmapLoaded(Document paramDocument, BitmapLoader.BitmapContainer paramBitmapContainer, int paramInt)
  {
    this.mLoadedImagesSoFar = (1 + this.mLoadedImagesSoFar);
    if (paramBitmapContainer.getBitmap() != null)
      this.mItems.add(new PromotionalItem(paramBitmapContainer.getBitmap(), paramInt, paramDocument.getTitle(), paramDocument.getCreator(), paramDocument));
    loadViewsIfDone();
  }

  private boolean isValidDocument(Document paramDocument)
  {
    boolean bool = true;
    if (this.mDocumentTypes == null);
    while (true)
    {
      return bool;
      if (paramDocument.hasDocumentType())
      {
        int i = paramDocument.getDocumentType();
        int[] arrayOfInt = this.mDocumentTypes;
        int j = arrayOfInt.length;
        for (int k = 0; ; k++)
        {
          if (k >= j)
            break label59;
          if (arrayOfInt[k] == i)
            break;
        }
      }
      label59: bool = false;
    }
  }

  private void loadViewsIfDone()
  {
    if (this.mLoadedImagesSoFar == this.mSize)
      this.mListener.onData();
  }

  public Collection<PromotionalItem> getItems()
  {
    return this.mItems;
  }

  public void onDataChanged()
  {
    this.mItems.clear();
    this.mUpdatePending = false;
    this.mLoadedImagesSoFar = 0;
    int i = Math.min(this.mList.getCount(), this.mMaxItems);
    this.mSize = i;
    int j = 0;
    if (j < i)
    {
      final Document localDocument = (Document)this.mList.getItem(j);
      String str = this.mImageSelector.getImageUrl(localDocument, this.mMaxHeight);
      final int k = this.mImageSelector.getImageType(localDocument);
      if ((!isValidDocument(localDocument)) || (TextUtils.isEmpty(str)))
      {
        this.mSize = (-1 + this.mSize);
        loadViewsIfDone();
      }
      while (true)
      {
        j++;
        break;
        BitmapLoader.BitmapContainer localBitmapContainer = this.mBitmapLoader.get(str, null, new BitmapLoader.BitmapLoadedHandler()
        {
          public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
          {
            WidgetModel.this.bitmapLoaded(localDocument, paramAnonymousBitmapContainer, k);
          }
        }
        , 0, this.mMaxHeight);
        if (localBitmapContainer.getBitmap() != null)
          bitmapLoaded(localDocument, localBitmapContainer, k);
      }
    }
    loadViewsIfDone();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    this.mUpdatePending = false;
    this.mListener.onError(ErrorStrings.get(FinskyApp.get(), paramVolleyError));
  }

  public void refresh(Context paramContext, DfeApi paramDfeApi, String paramString, RefreshListener paramRefreshListener)
  {
    if (this.mUpdatePending);
    while (true)
    {
      return;
      this.mUpdatePending = true;
      this.mListener = paramRefreshListener;
      if (this.mList != null)
      {
        this.mList.removeDataChangedListener(this);
        this.mList.removeErrorListener(this);
      }
      this.mMaxHeight = paramContext.getResources().getDimensionPixelSize(this.mImageHeightResource);
      this.mList = new DfeList(paramDfeApi, paramString, false);
      this.mList.addErrorListener(this);
      this.mList.addDataChangedListener(this);
      this.mList.startLoadItems();
    }
  }

  public void reset()
  {
    this.mUpdatePending = false;
    this.mItems.clear();
  }

  public static abstract interface ImageSelector
  {
    public abstract int getImageType(Document paramDocument);

    public abstract String getImageUrl(Document paramDocument, int paramInt);
  }

  public static class PromotionalItem
  {
    public final String developer;
    public final Document doc;
    public final Bitmap image;
    public final int imageType;
    public final String title;

    public PromotionalItem(Bitmap paramBitmap, int paramInt, String paramString1, String paramString2, Document paramDocument)
    {
      this.image = paramBitmap;
      this.imageType = paramInt;
      this.title = paramString1;
      this.developer = paramString2;
      this.doc = paramDocument;
    }
  }

  public static abstract interface RefreshListener
  {
    public abstract void onData();

    public abstract void onError(String paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.WidgetModel
 * JD-Core Version:    0.6.2
 */
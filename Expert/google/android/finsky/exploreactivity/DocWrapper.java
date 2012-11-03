package com.google.android.finsky.exploreactivity;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class DocWrapper
{
  private Bitmap mBitmap;
  private Document mDoc;
  private String mDocid;
  private int mInProgressState = 0;
  private List<DocWrapperListener> mListeners = Lists.newArrayList(0);
  private int mLoadedState = 0;
  private List<DocWrapper> mRelations;
  private Document mSong;
  private List<Document> mSongList;

  public DocWrapper(Document paramDocument)
  {
    setDoc(paramDocument);
  }

  public static String stateToString(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder(8);
    if ((paramInt & 0x8) > 0)
      localStringBuilder.append('R');
    if ((paramInt & 0x4) > 0)
      localStringBuilder.append('S');
    if ((paramInt & 0x2) > 0)
      localStringBuilder.append('D');
    if ((paramInt & 0x1) > 0)
      localStringBuilder.append('T');
    return localStringBuilder.toString();
  }

  public void clearInProgress()
  {
    this.mInProgressState = 0;
  }

  public Bitmap disposeObjects(int paramInt)
  {
    Bitmap localBitmap = null;
    if ((((paramInt & this.mLoadedState) != 0) || ((paramInt & this.mInProgressState) != 0)) && (FinskyLog.DEBUG))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = getTitle();
      arrayOfObject[1] = stateToString(paramInt);
      FinskyLog.v("Unloading %s: %s", arrayOfObject);
    }
    if ((paramInt & 0x1) != 0)
    {
      localBitmap = this.mBitmap;
      this.mBitmap = null;
    }
    if ((paramInt & 0x8) != 0)
      this.mRelations = null;
    if ((paramInt & 0x4) != 0)
      this.mSongList = null;
    this.mLoadedState &= (paramInt ^ 0xFFFFFFFF);
    this.mInProgressState &= (paramInt ^ 0xFFFFFFFF);
    return localBitmap;
  }

  public boolean equals(Object paramObject)
  {
    if (((paramObject instanceof DocWrapper)) && (paramObject != null) && (((DocWrapper)paramObject).mDocid.equals(this.mDocid)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public String getDetailsUrl()
  {
    if (this.mDoc != null);
    for (String str = this.mDoc.getDetailsUrl(); ; str = DfeUtils.createDetailsUrlFromId(this.mDocid))
      return str;
  }

  public Document getDoc()
  {
    return this.mDoc;
  }

  public String getDocId()
  {
    return this.mDocid;
  }

  public int getInProgressState()
  {
    return this.mInProgressState;
  }

  public int getLoadedState()
  {
    return this.mLoadedState;
  }

  public String getRelatedItemUrl()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.mDoc.getBackend());
    arrayOfObject[1] = this.mDocid;
    return String.format("rec?c=%d&doc=%s&rt=1", arrayOfObject);
  }

  public List<DocWrapper> getRelations()
  {
    return this.mRelations;
  }

  public Document getSong()
  {
    return this.mSong;
  }

  public Bitmap getThumbnail()
  {
    return this.mBitmap;
  }

  public String getTitle()
  {
    return this.mDoc.getTitle();
  }

  public int hashCode()
  {
    return this.mDocid.hashCode();
  }

  public void markInProgress(int paramInt)
  {
    if ((paramInt != 0) && (FinskyLog.DEBUG))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = getTitle();
      arrayOfObject[1] = stateToString(paramInt);
      FinskyLog.v("Loading %s:%s", arrayOfObject);
    }
    this.mInProgressState = (paramInt | this.mInProgressState);
  }

  public void markLoaded(int paramInt)
  {
    if (FinskyLog.DEBUG)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = getTitle();
      arrayOfObject[1] = stateToString(paramInt);
      FinskyLog.v("Loaded %s:%s", arrayOfObject);
    }
    this.mLoadedState = (paramInt | this.mLoadedState);
    this.mInProgressState &= (paramInt ^ 0xFFFFFFFF);
    for (int i = 0; i < this.mListeners.size(); i++)
      ((DocWrapperListener)this.mListeners.get(i)).docChanged(this);
  }

  public void setDoc(Document paramDocument)
  {
    this.mDoc = paramDocument;
    this.mDocid = paramDocument.getDocId();
    if (!TextUtils.isEmpty(paramDocument.getDescription()))
      this.mLoadedState = (0x2 | this.mLoadedState);
  }

  public void setRelations(List<DocWrapper> paramList)
  {
    this.mRelations = paramList;
    markLoaded(8);
  }

  public void setSong(Document paramDocument)
  {
    this.mSong = paramDocument;
    markLoaded(4);
  }

  public void setSongList(List<Document> paramList)
  {
    this.mSongList = paramList;
    markLoaded(4);
  }

  public void setThumbnail(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
    markLoaded(1);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    localStringBuilder.append(getTitle());
    if (((0x8 & this.mLoadedState) > 0) && (this.mRelations != null))
    {
      localStringBuilder.append(",R=");
      localStringBuilder.append(this.mRelations.size());
    }
    if (((0x4 & this.mLoadedState) > 0) && (this.mSongList != null))
    {
      localStringBuilder.append(",S=");
      localStringBuilder.append(this.mSongList.size());
    }
    if ((0x2 & this.mLoadedState) > 0)
      localStringBuilder.append(",D");
    if (((0x1 & this.mLoadedState) > 0) && (this.mBitmap != null))
    {
      localStringBuilder.append(",T");
      localStringBuilder.append("=");
      localStringBuilder.append(this.mBitmap.getRowBytes() * this.mBitmap.getHeight());
    }
    return localStringBuilder.toString();
  }

  public static abstract interface DocWrapperListener
  {
    public abstract void docChanged(DocWrapper paramDocWrapper);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.DocWrapper
 * JD-Core Version:    0.6.2
 */
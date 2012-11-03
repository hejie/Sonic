package com.google.android.finsky.widget.recommendation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.ParcelUtils.CacheVersionException;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.List;

public class Recommendation
  implements Parcelable
{
  private static final int[] APP_IMAGE_TYPES = { 2, 4, 0 };
  public static final Parcelable.Creator<Recommendation> CREATOR = new Parcelable.Creator()
  {
    public Recommendation createFromParcel(Parcel paramAnonymousParcel)
    {
      ClassLoader localClassLoader = Recommendation.class.getClassLoader();
      long l = paramAnonymousParcel.readLong();
      if (2L != l)
        throw new ParcelUtils.CacheVersionException(Recommendation.class, 2L, l);
      return new Recommendation((Document)paramAnonymousParcel.readParcelable(localClassLoader), paramAnonymousParcel.readLong(), null);
    }

    public Recommendation[] newArray(int paramAnonymousInt)
    {
      return new Recommendation[paramAnonymousInt];
    }
  };
  private static final int[] NONAPP_IMAGE_TYPES = { 4, 2, 0 };
  private final Document mDocument;
  private final long mExpirationTimeMs;
  private final Doc.Image mImage;

  public Recommendation(Document paramDocument)
  {
    this(paramDocument, System.currentTimeMillis() + ((Long)G.recommendationTtlMs.get()).longValue());
  }

  private Recommendation(Document paramDocument, long paramLong)
  {
    this.mDocument = paramDocument;
    this.mImage = getImage();
    this.mExpirationTimeMs = paramLong;
  }

  private Doc.Image getImage()
  {
    int[] arrayOfInt1;
    int j;
    label23: List localList;
    if (this.mDocument.getBackend() == 3)
    {
      arrayOfInt1 = APP_IMAGE_TYPES;
      int[] arrayOfInt2 = arrayOfInt1;
      int i = arrayOfInt2.length;
      j = 0;
      if (j >= i)
        break label90;
      int k = arrayOfInt2[j];
      localList = this.mDocument.getImages(k);
      if ((localList == null) || (localList.isEmpty()))
        break label84;
    }
    label84: label90: for (Doc.Image localImage = (Doc.Image)localList.get(0); ; localImage = null)
    {
      return localImage;
      arrayOfInt1 = NONAPP_IMAGE_TYPES;
      break;
      j++;
      break label23;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Recommendation));
    for (boolean bool = false; ; bool = ((Recommendation)paramObject).mDocument.getDocId().equals(this.mDocument.getDocId()))
      return bool;
  }

  public int getBackend()
  {
    return this.mDocument.getBackend();
  }

  public Document getDocument()
  {
    return this.mDocument;
  }

  public int getImageType()
  {
    if (this.mImage != null);
    for (int i = this.mImage.getImageType(); ; i = 0)
      return i;
  }

  public String getImageUrl(int paramInt)
  {
    if (this.mImage != null);
    for (String str = this.mImage.getImageUrl(); ; str = null)
    {
      if ((this.mImage != null) && (this.mImage.getSupportsFifeUrlOptions()))
        str = ThumbnailUtils.buildFifeUrl(str, 0, paramInt);
      return str;
    }
  }

  public int hashCode()
  {
    return 31 * this.mDocument.hashCode() + this.mImage.getImageType();
  }

  public boolean isExpired()
  {
    if (this.mExpirationTimeMs < System.currentTimeMillis());
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public String toString()
  {
    return this.mDocument.getReasonUniqueId();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(2L);
    paramParcel.writeParcelable(this.mDocument, 0);
    paramParcel.writeLong(this.mExpirationTimeMs);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.Recommendation
 * JD-Core Version:    0.6.2
 */
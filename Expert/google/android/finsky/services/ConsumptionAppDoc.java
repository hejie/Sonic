package com.google.android.finsky.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.SpannableString;
import android.text.TextUtils;
import com.google.android.finsky.utils.Objects;
import java.text.DateFormat;
import java.util.Date;

public class ConsumptionAppDoc
  implements Parcelable
{
  public static final Parcelable.Creator<ConsumptionAppDoc> CREATOR = new Parcelable.Creator()
  {
    public ConsumptionAppDoc createFromParcel(Parcel paramAnonymousParcel)
    {
      ClassLoader localClassLoader = ConsumptionAppDoc.class.getClassLoader();
      long l = paramAnonymousParcel.readLong();
      if (1L != l)
        throw new IllegalArgumentException("Unable to unparcel v" + l);
      return new ConsumptionAppDoc((Uri)paramAnonymousParcel.readParcelable(localClassLoader), paramAnonymousParcel.readString(), paramAnonymousParcel.readLong(), paramAnonymousParcel.readString(), (Intent)paramAnonymousParcel.readParcelable(localClassLoader), null);
    }

    public ConsumptionAppDoc[] newArray(int paramAnonymousInt)
    {
      return new ConsumptionAppDoc[paramAnonymousInt];
    }
  };
  private final String mDocId;
  private final Uri mImageUri;
  private final long mLastUpdateTimeMs;
  private final String mReason;
  private final Intent mViewIntent;

  private ConsumptionAppDoc(Uri paramUri, CharSequence paramCharSequence, long paramLong, String paramString, Intent paramIntent)
  {
    this.mImageUri = paramUri;
    this.mLastUpdateTimeMs = paramLong;
    this.mDocId = paramString;
    this.mViewIntent = paramIntent;
    String str = (String)paramCharSequence;
    if (TextUtils.isEmpty(str))
    {
      SpannableString localSpannableString = (SpannableString)paramCharSequence;
      if (localSpannableString != null)
        str = localSpannableString.toString();
    }
    this.mReason = str;
  }

  public ConsumptionAppDoc(Bundle paramBundle)
  {
    this((Uri)paramBundle.getParcelable("Play.ImageUri"), (CharSequence)paramBundle.get("Play.Reason"), paramBundle.getLong("Play.LastUpdateTimeMillis"), paramBundle.getString("Play.FinskyDocId"), (Intent)paramBundle.getParcelable("Play.ViewIntent"));
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof ConsumptionAppDoc));
    while (true)
    {
      return bool;
      ConsumptionAppDoc localConsumptionAppDoc = (ConsumptionAppDoc)paramObject;
      if ((Objects.equal(getImageUri(), localConsumptionAppDoc.getImageUri())) && (TextUtils.equals(getReason(), localConsumptionAppDoc.getReason())) && (getLastUpdateTimeMs() == localConsumptionAppDoc.getLastUpdateTimeMs()) && (TextUtils.equals(getDocId(), localConsumptionAppDoc.getDocId())))
        bool = true;
    }
  }

  public String getDocId()
  {
    return this.mDocId;
  }

  public Uri getImageUri()
  {
    return this.mImageUri;
  }

  public long getLastUpdateTimeMs()
  {
    return this.mLastUpdateTimeMs;
  }

  public String getReason()
  {
    return this.mReason;
  }

  public Intent getViewIntent()
  {
    return this.mViewIntent;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = getDocId();
    arrayOfObject[1] = getImageUri();
    arrayOfObject[2] = getReason();
    arrayOfObject[3] = ConsumptionAppDataCache.DATE_FORMAT.format(new Date(getLastUpdateTimeMs()));
    return String.format("Doc %s, Image %s, Reason %s, Last update %s", arrayOfObject);
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(1L);
    paramParcel.writeParcelable(this.mImageUri, 0);
    paramParcel.writeString(this.mReason);
    paramParcel.writeLong(this.mLastUpdateTimeMs);
    paramParcel.writeString(this.mDocId);
    paramParcel.writeParcelable(this.mViewIntent, 0);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.ConsumptionAppDoc
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.protobuf.micro.MessageMicro;
import java.lang.reflect.Constructor;

public class ParcelableProto<T extends MessageMicro>
  implements Parcelable
{
  public static Parcelable.Creator<ParcelableProto<MessageMicro>> CREATOR = new Parcelable.Creator()
  {
    public ParcelableProto<MessageMicro> createFromParcel(Parcel paramAnonymousParcel)
    {
      byte[] arrayOfByte = new byte[paramAnonymousParcel.readInt()];
      paramAnonymousParcel.readByteArray(arrayOfByte);
      String str = paramAnonymousParcel.readString();
      try
      {
        MessageMicro localMessageMicro = (MessageMicro)Class.forName(str).getConstructor((Class[])null).newInstance((Object[])null);
        localMessageMicro.mergeFrom(arrayOfByte);
        ParcelableProto localParcelableProto = new ParcelableProto(localMessageMicro);
        return localParcelableProto;
      }
      catch (Exception localException)
      {
        throw new IllegalArgumentException("Exception when unmarshalling: " + str, localException);
      }
    }

    public ParcelableProto<MessageMicro>[] newArray(int paramAnonymousInt)
    {
      return new ParcelableProto[paramAnonymousInt];
    }
  };
  private final T mPayload;
  private byte[] mSerialized = null;

  public ParcelableProto(T paramT)
  {
    this.mPayload = paramT;
  }

  public static <T extends MessageMicro> ParcelableProto<T> forProto(T paramT)
  {
    return new ParcelableProto(paramT);
  }

  public static <T extends MessageMicro> T getProtoFromBundle(Bundle paramBundle, String paramString)
  {
    ParcelableProto localParcelableProto = (ParcelableProto)paramBundle.getParcelable(paramString);
    if (localParcelableProto != null);
    for (MessageMicro localMessageMicro = localParcelableProto.getPayload(); ; localMessageMicro = null)
      return localMessageMicro;
  }

  public static <T extends MessageMicro> T getProtoFromIntent(Intent paramIntent, String paramString)
  {
    ParcelableProto localParcelableProto = (ParcelableProto)paramIntent.getParcelableExtra(paramString);
    if (localParcelableProto != null);
    for (MessageMicro localMessageMicro = localParcelableProto.getPayload(); ; localMessageMicro = null)
      return localMessageMicro;
  }

  public static <T extends MessageMicro> T getProtoFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
  {
    ParcelableProto localParcelableProto = (ParcelableProto)paramParcel.readParcelable(paramClassLoader);
    if (localParcelableProto != null);
    for (MessageMicro localMessageMicro = localParcelableProto.getPayload(); ; localMessageMicro = null)
      return localMessageMicro;
  }

  private void serializePayload()
  {
    this.mSerialized = this.mPayload.toByteArray();
  }

  public int describeContents()
  {
    return 0;
  }

  public T getPayload()
  {
    return this.mPayload;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.mSerialized == null)
      serializePayload();
    paramParcel.writeInt(this.mSerialized.length);
    paramParcel.writeByteArray(this.mSerialized);
    paramParcel.writeString(this.mPayload.getClass().getName());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ParcelableProto
 * JD-Core Version:    0.6.2
 */
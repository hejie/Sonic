package com.google.android.finsky.billing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IabParameters
  implements Parcelable
{
  public static final Parcelable.Creator<IabParameters> CREATOR = new Parcelable.Creator()
  {
    public IabParameters createFromParcel(Parcel paramAnonymousParcel)
    {
      String str1 = paramAnonymousParcel.readString();
      int i = paramAnonymousParcel.readInt();
      String str2 = paramAnonymousParcel.readString();
      if (paramAnonymousParcel.readByte() > 0);
      for (int j = 1; ; j = 0)
      {
        String str3 = null;
        if (j != 0)
          str3 = paramAnonymousParcel.readString();
        return new IabParameters(paramAnonymousParcel.readInt(), str1, i, str2, str3);
      }
    }

    public IabParameters[] newArray(int paramAnonymousInt)
    {
      return new IabParameters[paramAnonymousInt];
    }
  };
  public final int billingApiVersion;
  public final String developerPayload;
  public final String packageName;
  public final String packageSignatureHash;
  public final int packageVersionCode;

  public IabParameters(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3)
  {
    this.billingApiVersion = paramInt1;
    this.packageName = paramString1;
    this.packageVersionCode = paramInt2;
    this.packageSignatureHash = paramString2;
    this.developerPayload = paramString3;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
      }
      else
      {
        IabParameters localIabParameters = (IabParameters)paramObject;
        if (this.packageVersionCode != localIabParameters.packageVersionCode)
        {
          bool = false;
        }
        else
        {
          if (this.developerPayload != null)
          {
            if (this.developerPayload.equals(localIabParameters.developerPayload));
          }
          else
            while (localIabParameters.developerPayload != null)
            {
              bool = false;
              break;
            }
          if (this.packageName != null)
          {
            if (this.packageName.equals(localIabParameters.packageName));
          }
          else
            while (localIabParameters.packageName != null)
            {
              bool = false;
              break;
            }
          if (this.packageSignatureHash != null)
          {
            if (this.packageSignatureHash.equals(localIabParameters.packageSignatureHash));
          }
          else
            while (localIabParameters.packageSignatureHash != null)
            {
              bool = false;
              break;
            }
          if (this.billingApiVersion != localIabParameters.billingApiVersion)
            bool = false;
        }
      }
    }
  }

  public int hashCode()
  {
    return 42;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.packageName);
    paramParcel.writeInt(this.packageVersionCode);
    paramParcel.writeString(this.packageSignatureHash);
    if (this.developerPayload != null)
    {
      paramParcel.writeByte((byte)1);
      paramParcel.writeString(this.developerPayload);
    }
    while (true)
    {
      paramParcel.writeInt(this.billingApiVersion);
      return;
      paramParcel.writeByte((byte)0);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.IabParameters
 * JD-Core Version:    0.6.2
 */
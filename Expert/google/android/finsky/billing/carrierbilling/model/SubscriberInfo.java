package com.google.android.finsky.billing.carrierbilling.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Objects;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SubscriberInfo
  implements Parcelable
{
  public static final Parcelable.Creator<SubscriberInfo> CREATOR = new Parcelable.Creator()
  {
    public SubscriberInfo createFromParcel(Parcel paramAnonymousParcel)
    {
      return new SubscriberInfo(paramAnonymousParcel, null);
    }

    public SubscriberInfo[] newArray(int paramAnonymousInt)
    {
      return new SubscriberInfo[paramAnonymousInt];
    }
  };
  private final String mAddress1;
  private final String mAddress2;
  private final String mCity;
  private final String mCountry;
  private final String mIdentifier;
  private final String mName;
  private final String mPostalCode;
  private final String mState;

  private SubscriberInfo(Parcel paramParcel)
  {
    this.mName = paramParcel.readString();
    this.mIdentifier = paramParcel.readString();
    this.mAddress1 = paramParcel.readString();
    this.mAddress2 = paramParcel.readString();
    this.mCity = paramParcel.readString();
    this.mState = paramParcel.readString();
    this.mPostalCode = paramParcel.readString();
    this.mCountry = paramParcel.readString();
  }

  private SubscriberInfo(Builder paramBuilder)
  {
    this.mName = paramBuilder.name;
    this.mIdentifier = paramBuilder.identifier;
    this.mAddress1 = paramBuilder.address1;
    this.mAddress2 = paramBuilder.address2;
    this.mCity = paramBuilder.city;
    this.mState = paramBuilder.state;
    this.mPostalCode = paramBuilder.postalCode;
    this.mCountry = paramBuilder.country;
  }

  public static SubscriberInfo fromObfuscatedString(String paramString)
  {
    Builder localBuilder = new Builder();
    String[] arrayOfString = new String(Base64.decode(switchChars(paramString).getBytes(), 0)).split(",", 8);
    if (arrayOfString.length != 8)
      throw new IllegalArgumentException("SubscriberInfo could not be parsed from " + paramString);
    try
    {
      localBuilder.setName(URLDecoder.decode(arrayOfString[0], "UTF-8")).setIdentifier(URLDecoder.decode(arrayOfString[1], "UTF-8")).setAddress1(URLDecoder.decode(arrayOfString[2], "UTF-8")).setAddress2(URLDecoder.decode(arrayOfString[3], "UTF-8")).setCity(URLDecoder.decode(arrayOfString[4], "UTF-8")).setState(URLDecoder.decode(arrayOfString[5], "UTF-8")).setPostalCode(URLDecoder.decode(arrayOfString[6], "UTF-8")).setCountry(URLDecoder.decode(arrayOfString[7], "UTF-8"));
      localSubscriberInfo = localBuilder.build();
      return localSubscriberInfo;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        FinskyLog.w("UTF-8 not supported: %s", new Object[] { localUnsupportedEncodingException });
        SubscriberInfo localSubscriberInfo = null;
      }
    }
  }

  private static String switchChars(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    char[] arrayOfChar = paramString.toCharArray();
    int i = arrayOfChar.length;
    int j = 0;
    if (j < i)
    {
      char c = arrayOfChar[j];
      if (((c >= 'a') && (c <= 'm')) || ((c >= 'A') && (c <= 'M')))
        c = (char)(c + '\r');
      while (true)
      {
        localStringBuilder.append(c);
        j++;
        break;
        if (((c >= 'n') && (c <= 'z')) || ((c >= 'N') && (c <= 'Z')))
          c = (char)(c - '\r');
        else if ((c >= '0') && (c <= '4'))
          c = (char)(c + '\005');
        else if ((c >= '5') && (c <= '9'))
          c = (char)(c - '\005');
      }
    }
    return localStringBuilder.toString();
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
      if (!(paramObject instanceof SubscriberInfo))
      {
        bool = false;
      }
      else
      {
        SubscriberInfo localSubscriberInfo = (SubscriberInfo)paramObject;
        if ((!Objects.equal(this.mName, localSubscriberInfo.mName)) || (!Objects.equal(this.mIdentifier, localSubscriberInfo.mIdentifier)) || (!Objects.equal(this.mAddress1, localSubscriberInfo.mAddress1)) || (!Objects.equal(this.mAddress2, localSubscriberInfo.mAddress2)) || (!Objects.equal(this.mCity, localSubscriberInfo.mCity)) || (!Objects.equal(this.mState, localSubscriberInfo.mState)) || (!Objects.equal(this.mPostalCode, localSubscriberInfo.mPostalCode)) || (!Objects.equal(this.mCountry, localSubscriberInfo.mCountry)))
          bool = false;
      }
    }
  }

  public String getAddress1()
  {
    return this.mAddress1;
  }

  public String getAddress2()
  {
    return this.mAddress2;
  }

  public String getCity()
  {
    return this.mCity;
  }

  public String getCountry()
  {
    return this.mCountry;
  }

  public String getIdentifier()
  {
    return this.mIdentifier;
  }

  public String getName()
  {
    return this.mName;
  }

  public String getPostalCode()
  {
    return this.mPostalCode;
  }

  public String getState()
  {
    return this.mState;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = this.mName;
    arrayOfObject[1] = this.mIdentifier;
    arrayOfObject[2] = this.mAddress1;
    arrayOfObject[3] = this.mAddress2;
    arrayOfObject[4] = this.mCity;
    arrayOfObject[5] = this.mState;
    arrayOfObject[6] = this.mPostalCode;
    arrayOfObject[7] = this.mCountry;
    return Objects.hashCode(arrayOfObject);
  }

  public String toObfuscatedString()
  {
    try
    {
      String str1;
      String str2;
      label20: String str3;
      label31: String str4;
      label42: String str5;
      label53: String str6;
      label64: String str7;
      if (this.mName == null)
      {
        str1 = "";
        if (this.mIdentifier != null)
          break label200;
        str2 = "";
        if (this.mAddress1 != null)
          break label213;
        str3 = "";
        if (this.mAddress2 != null)
          break label227;
        str4 = "";
        if (this.mCity != null)
          break label241;
        str5 = "";
        if (this.mState != null)
          break label255;
        str6 = "";
        if (this.mPostalCode != null)
          break label269;
        str7 = "";
        label75: if (this.mCountry != null)
          break label283;
      }
      label200: label213: label227: label241: label255: String str8;
      for (Object localObject = ""; ; localObject = str8)
      {
        return switchChars(new String(Base64.encode((str1 + "," + str2 + "," + str3 + "," + str4 + "," + str5 + "," + str6 + "," + str7 + "," + (String)localObject).getBytes(), 0)));
        str1 = URLEncoder.encode(this.mName, "UTF-8");
        break;
        str2 = URLEncoder.encode(this.mIdentifier, "UTF-8");
        break label20;
        str3 = URLEncoder.encode(this.mAddress1, "UTF-8");
        break label31;
        str4 = URLEncoder.encode(this.mAddress2, "UTF-8");
        break label42;
        str5 = URLEncoder.encode(this.mCity, "UTF-8");
        break label53;
        str6 = URLEncoder.encode(this.mState, "UTF-8");
        break label64;
        label269: str7 = URLEncoder.encode(this.mPostalCode, "UTF-8");
        break label75;
        label283: str8 = URLEncoder.encode(this.mCountry, "UTF-8");
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new IllegalArgumentException("UTF-8 not supported", localUnsupportedEncodingException);
    }
  }

  public String toString()
  {
    return toObfuscatedString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.mName);
    paramParcel.writeString(this.mIdentifier);
    paramParcel.writeString(this.mAddress1);
    paramParcel.writeString(this.mAddress2);
    paramParcel.writeString(this.mCity);
    paramParcel.writeString(this.mState);
    paramParcel.writeString(this.mPostalCode);
    paramParcel.writeString(this.mCountry);
  }

  public static class Builder
  {
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String identifier;
    private String name;
    private String postalCode;
    private String state;

    public SubscriberInfo build()
    {
      return new SubscriberInfo(this, null);
    }

    public Builder setAddress1(String paramString)
    {
      this.address1 = paramString;
      return this;
    }

    public Builder setAddress2(String paramString)
    {
      this.address2 = paramString;
      return this;
    }

    public Builder setCity(String paramString)
    {
      this.city = paramString;
      return this;
    }

    public Builder setCountry(String paramString)
    {
      this.country = paramString;
      return this;
    }

    public Builder setIdentifier(String paramString)
    {
      this.identifier = paramString;
      return this;
    }

    public Builder setName(String paramString)
    {
      this.name = paramString;
      return this;
    }

    public Builder setPostalCode(String paramString)
    {
      this.postalCode = paramString;
      return this;
    }

    public Builder setState(String paramString)
    {
      this.state = paramString;
      return this;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo
 * JD-Core Version:    0.6.2
 */
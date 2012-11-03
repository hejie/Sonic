package com.google.android.finsky.billing.carrierbilling.model;

import android.text.TextUtils;
import com.google.android.finsky.utils.Objects;

public class EncryptedSubscriberInfo
{
  public final int mCarrierKeyVersion;
  public final String mEncryptedKey;
  public final int mGoogleKeyVersion;
  public final String mInitVector;
  public final String mMessage;
  public final String mSignature;

  private EncryptedSubscriberInfo(Builder paramBuilder)
  {
    this.mMessage = paramBuilder.message;
    this.mEncryptedKey = paramBuilder.encryptedKey;
    this.mSignature = paramBuilder.signature;
    this.mInitVector = paramBuilder.initVector;
    this.mCarrierKeyVersion = paramBuilder.carrierKeyVersion;
    this.mGoogleKeyVersion = paramBuilder.googleKeyVersion;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if (!(paramObject instanceof EncryptedSubscriberInfo))
      {
        bool = false;
      }
      else
      {
        EncryptedSubscriberInfo localEncryptedSubscriberInfo = (EncryptedSubscriberInfo)paramObject;
        if ((!Objects.equal(this.mMessage, localEncryptedSubscriberInfo.mMessage)) || (!Objects.equal(this.mEncryptedKey, localEncryptedSubscriberInfo.mEncryptedKey)) || (!Objects.equal(this.mSignature, localEncryptedSubscriberInfo.mSignature)) || (!Objects.equal(this.mInitVector, localEncryptedSubscriberInfo.mInitVector)) || (!Objects.equal(Integer.valueOf(this.mCarrierKeyVersion), Integer.valueOf(localEncryptedSubscriberInfo.mCarrierKeyVersion))) || (!Objects.equal(Integer.valueOf(this.mGoogleKeyVersion), Integer.valueOf(localEncryptedSubscriberInfo.mGoogleKeyVersion))))
          bool = false;
      }
    }
  }

  public int getCarrierKeyVersion()
  {
    return this.mCarrierKeyVersion;
  }

  public String getEncryptedKey()
  {
    return this.mEncryptedKey;
  }

  public int getGoogleKeyVersion()
  {
    return this.mGoogleKeyVersion;
  }

  public String getInitVector()
  {
    return this.mInitVector;
  }

  public String getMessage()
  {
    return this.mMessage;
  }

  public String getSignature()
  {
    return this.mSignature;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = this.mMessage;
    arrayOfObject[1] = this.mEncryptedKey;
    arrayOfObject[2] = this.mSignature;
    arrayOfObject[3] = this.mInitVector;
    arrayOfObject[4] = Integer.valueOf(this.mCarrierKeyVersion);
    arrayOfObject[5] = Integer.valueOf(this.mGoogleKeyVersion);
    return Objects.hashCode(arrayOfObject);
  }

  public boolean isEmpty()
  {
    if ((TextUtils.isEmpty(getMessage())) && (TextUtils.isEmpty(getEncryptedKey())) && (TextUtils.isEmpty(getSignature())) && (TextUtils.isEmpty(getInitVector())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public com.google.android.finsky.remoting.protos.EncryptedSubscriberInfo toProto()
  {
    return new com.google.android.finsky.remoting.protos.EncryptedSubscriberInfo().setData(this.mMessage).setEncryptedKey(this.mEncryptedKey).setSignature(this.mSignature).setInitVector(this.mInitVector).setGoogleKeyVersion(this.mGoogleKeyVersion).setCarrierKeyVersion(this.mCarrierKeyVersion);
  }

  public String toString()
  {
    return "EncryptedSubscriberInfo:" + "\n" + "  message          : " + this.mMessage + "\n" + "  encryptedKey     : " + this.mEncryptedKey + "\n" + "  signature        : " + this.mSignature + "\n" + "  initVector       : " + this.mInitVector + "\n" + "  carrierKeyVersion: " + this.mCarrierKeyVersion + "\n" + "  googleKeyVersion : " + this.mGoogleKeyVersion + "\n";
  }

  public static class Builder
  {
    private int carrierKeyVersion;
    private String encryptedKey;
    private int googleKeyVersion;
    private String initVector;
    private String message;
    private String signature;

    public EncryptedSubscriberInfo build()
    {
      return new EncryptedSubscriberInfo(this, null);
    }

    public Builder setCarrierKeyVersion(int paramInt)
    {
      this.carrierKeyVersion = paramInt;
      return this;
    }

    public Builder setEncryptedKey(String paramString)
    {
      this.encryptedKey = paramString;
      return this;
    }

    public Builder setGoogleKeyVersion(int paramInt)
    {
      this.googleKeyVersion = paramInt;
      return this;
    }

    public Builder setInitVector(String paramString)
    {
      this.initVector = paramString;
      return this;
    }

    public Builder setMessage(String paramString)
    {
      this.message = paramString;
      return this;
    }

    public Builder setSignature(String paramString)
    {
      this.signature = paramString;
      return this;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.EncryptedSubscriberInfo
 * JD-Core Version:    0.6.2
 */
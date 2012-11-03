package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class EncryptedSubscriberInfo extends MessageMicro
{
  private int cachedSize = -1;
  private int carrierKeyVersion_ = 0;
  private String data_ = "";
  private String encryptedKey_ = "";
  private int googleKeyVersion_ = 0;
  private boolean hasCarrierKeyVersion;
  private boolean hasData;
  private boolean hasEncryptedKey;
  private boolean hasGoogleKeyVersion;
  private boolean hasInitVector;
  private boolean hasSignature;
  private String initVector_ = "";
  private String signature_ = "";

  public int getCachedSize()
  {
    if (this.cachedSize < 0)
      getSerializedSize();
    return this.cachedSize;
  }

  public int getCarrierKeyVersion()
  {
    return this.carrierKeyVersion_;
  }

  public String getData()
  {
    return this.data_;
  }

  public String getEncryptedKey()
  {
    return this.encryptedKey_;
  }

  public int getGoogleKeyVersion()
  {
    return this.googleKeyVersion_;
  }

  public String getInitVector()
  {
    return this.initVector_;
  }

  public int getSerializedSize()
  {
    int i = 0;
    if (hasData())
      i = 0 + CodedOutputStreamMicro.computeStringSize(1, getData());
    if (hasEncryptedKey())
      i += CodedOutputStreamMicro.computeStringSize(2, getEncryptedKey());
    if (hasSignature())
      i += CodedOutputStreamMicro.computeStringSize(3, getSignature());
    if (hasInitVector())
      i += CodedOutputStreamMicro.computeStringSize(4, getInitVector());
    if (hasGoogleKeyVersion())
      i += CodedOutputStreamMicro.computeInt32Size(5, getGoogleKeyVersion());
    if (hasCarrierKeyVersion())
      i += CodedOutputStreamMicro.computeInt32Size(6, getCarrierKeyVersion());
    this.cachedSize = i;
    return i;
  }

  public String getSignature()
  {
    return this.signature_;
  }

  public boolean hasCarrierKeyVersion()
  {
    return this.hasCarrierKeyVersion;
  }

  public boolean hasData()
  {
    return this.hasData;
  }

  public boolean hasEncryptedKey()
  {
    return this.hasEncryptedKey;
  }

  public boolean hasGoogleKeyVersion()
  {
    return this.hasGoogleKeyVersion;
  }

  public boolean hasInitVector()
  {
    return this.hasInitVector;
  }

  public boolean hasSignature()
  {
    return this.hasSignature;
  }

  public EncryptedSubscriberInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
    throws IOException
  {
    while (true)
    {
      int i = paramCodedInputStreamMicro.readTag();
      switch (i)
      {
      default:
        if (parseUnknownField(paramCodedInputStreamMicro, i))
          continue;
      case 0:
        return this;
      case 10:
        setData(paramCodedInputStreamMicro.readString());
        break;
      case 18:
        setEncryptedKey(paramCodedInputStreamMicro.readString());
        break;
      case 26:
        setSignature(paramCodedInputStreamMicro.readString());
        break;
      case 34:
        setInitVector(paramCodedInputStreamMicro.readString());
        break;
      case 40:
        setGoogleKeyVersion(paramCodedInputStreamMicro.readInt32());
        break;
      case 48:
      }
      setCarrierKeyVersion(paramCodedInputStreamMicro.readInt32());
    }
  }

  public EncryptedSubscriberInfo setCarrierKeyVersion(int paramInt)
  {
    this.hasCarrierKeyVersion = true;
    this.carrierKeyVersion_ = paramInt;
    return this;
  }

  public EncryptedSubscriberInfo setData(String paramString)
  {
    this.hasData = true;
    this.data_ = paramString;
    return this;
  }

  public EncryptedSubscriberInfo setEncryptedKey(String paramString)
  {
    this.hasEncryptedKey = true;
    this.encryptedKey_ = paramString;
    return this;
  }

  public EncryptedSubscriberInfo setGoogleKeyVersion(int paramInt)
  {
    this.hasGoogleKeyVersion = true;
    this.googleKeyVersion_ = paramInt;
    return this;
  }

  public EncryptedSubscriberInfo setInitVector(String paramString)
  {
    this.hasInitVector = true;
    this.initVector_ = paramString;
    return this;
  }

  public EncryptedSubscriberInfo setSignature(String paramString)
  {
    this.hasSignature = true;
    this.signature_ = paramString;
    return this;
  }

  public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException
  {
    if (hasData())
      paramCodedOutputStreamMicro.writeString(1, getData());
    if (hasEncryptedKey())
      paramCodedOutputStreamMicro.writeString(2, getEncryptedKey());
    if (hasSignature())
      paramCodedOutputStreamMicro.writeString(3, getSignature());
    if (hasInitVector())
      paramCodedOutputStreamMicro.writeString(4, getInitVector());
    if (hasGoogleKeyVersion())
      paramCodedOutputStreamMicro.writeInt32(5, getGoogleKeyVersion());
    if (hasCarrierKeyVersion())
      paramCodedOutputStreamMicro.writeInt32(6, getCarrierKeyVersion());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.EncryptedSubscriberInfo
 * JD-Core Version:    0.6.2
 */
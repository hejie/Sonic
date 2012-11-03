package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class AndroidAppDelivery
{
  public static final class AndroidAppDeliveryData extends MessageMicro
  {
    private List<AndroidAppDelivery.AppFileMetadata> additionalFile_ = Collections.emptyList();
    private int cachedSize = -1;
    private List<AndroidAppDelivery.HttpCookie> downloadAuthCookie_ = Collections.emptyList();
    private long downloadSize_ = 0L;
    private String downloadUrl_ = "";
    private AndroidAppDelivery.EncryptionParams encryptionParams_ = null;
    private boolean forwardLocked_ = false;
    private boolean hasDownloadSize;
    private boolean hasDownloadUrl;
    private boolean hasEncryptionParams;
    private boolean hasForwardLocked;
    private boolean hasImmediateStartNeeded;
    private boolean hasPatchData;
    private boolean hasPostInstallRefundWindowMillis;
    private boolean hasRefundTimeout;
    private boolean hasServerInitiated;
    private boolean hasSignature;
    private boolean immediateStartNeeded_ = false;
    private AndroidAppDelivery.AndroidAppPatchData patchData_ = null;
    private long postInstallRefundWindowMillis_ = 0L;
    private long refundTimeout_ = 0L;
    private boolean serverInitiated_ = true;
    private String signature_ = "";

    public static AndroidAppDeliveryData parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferMicroException
    {
      return (AndroidAppDeliveryData)new AndroidAppDeliveryData().mergeFrom(paramArrayOfByte);
    }

    public AndroidAppDeliveryData addAdditionalFile(AndroidAppDelivery.AppFileMetadata paramAppFileMetadata)
    {
      if (paramAppFileMetadata == null)
        throw new NullPointerException();
      if (this.additionalFile_.isEmpty())
        this.additionalFile_ = new ArrayList();
      this.additionalFile_.add(paramAppFileMetadata);
      return this;
    }

    public AndroidAppDeliveryData addDownloadAuthCookie(AndroidAppDelivery.HttpCookie paramHttpCookie)
    {
      if (paramHttpCookie == null)
        throw new NullPointerException();
      if (this.downloadAuthCookie_.isEmpty())
        this.downloadAuthCookie_ = new ArrayList();
      this.downloadAuthCookie_.add(paramHttpCookie);
      return this;
    }

    public AndroidAppDelivery.AppFileMetadata getAdditionalFile(int paramInt)
    {
      return (AndroidAppDelivery.AppFileMetadata)this.additionalFile_.get(paramInt);
    }

    public int getAdditionalFileCount()
    {
      return this.additionalFile_.size();
    }

    public List<AndroidAppDelivery.AppFileMetadata> getAdditionalFileList()
    {
      return this.additionalFile_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public AndroidAppDelivery.HttpCookie getDownloadAuthCookie(int paramInt)
    {
      return (AndroidAppDelivery.HttpCookie)this.downloadAuthCookie_.get(paramInt);
    }

    public int getDownloadAuthCookieCount()
    {
      return this.downloadAuthCookie_.size();
    }

    public List<AndroidAppDelivery.HttpCookie> getDownloadAuthCookieList()
    {
      return this.downloadAuthCookie_;
    }

    public long getDownloadSize()
    {
      return this.downloadSize_;
    }

    public String getDownloadUrl()
    {
      return this.downloadUrl_;
    }

    public AndroidAppDelivery.EncryptionParams getEncryptionParams()
    {
      return this.encryptionParams_;
    }

    public boolean getForwardLocked()
    {
      return this.forwardLocked_;
    }

    public boolean getImmediateStartNeeded()
    {
      return this.immediateStartNeeded_;
    }

    public AndroidAppDelivery.AndroidAppPatchData getPatchData()
    {
      return this.patchData_;
    }

    public long getPostInstallRefundWindowMillis()
    {
      return this.postInstallRefundWindowMillis_;
    }

    public long getRefundTimeout()
    {
      return this.refundTimeout_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDownloadSize())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getDownloadSize());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeStringSize(2, getSignature());
      if (hasDownloadUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getDownloadUrl());
      Iterator localIterator1 = getAdditionalFileList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (AndroidAppDelivery.AppFileMetadata)localIterator1.next());
      Iterator localIterator2 = getDownloadAuthCookieList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(5, (AndroidAppDelivery.HttpCookie)localIterator2.next());
      if (hasForwardLocked())
        i += CodedOutputStreamMicro.computeBoolSize(6, getForwardLocked());
      if (hasRefundTimeout())
        i += CodedOutputStreamMicro.computeInt64Size(7, getRefundTimeout());
      if (hasServerInitiated())
        i += CodedOutputStreamMicro.computeBoolSize(8, getServerInitiated());
      if (hasPostInstallRefundWindowMillis())
        i += CodedOutputStreamMicro.computeInt64Size(9, getPostInstallRefundWindowMillis());
      if (hasImmediateStartNeeded())
        i += CodedOutputStreamMicro.computeBoolSize(10, getImmediateStartNeeded());
      if (hasPatchData())
        i += CodedOutputStreamMicro.computeMessageSize(11, getPatchData());
      if (hasEncryptionParams())
        i += CodedOutputStreamMicro.computeMessageSize(12, getEncryptionParams());
      this.cachedSize = i;
      return i;
    }

    public boolean getServerInitiated()
    {
      return this.serverInitiated_;
    }

    public String getSignature()
    {
      return this.signature_;
    }

    public boolean hasDownloadSize()
    {
      return this.hasDownloadSize;
    }

    public boolean hasDownloadUrl()
    {
      return this.hasDownloadUrl;
    }

    public boolean hasEncryptionParams()
    {
      return this.hasEncryptionParams;
    }

    public boolean hasForwardLocked()
    {
      return this.hasForwardLocked;
    }

    public boolean hasImmediateStartNeeded()
    {
      return this.hasImmediateStartNeeded;
    }

    public boolean hasPatchData()
    {
      return this.hasPatchData;
    }

    public boolean hasPostInstallRefundWindowMillis()
    {
      return this.hasPostInstallRefundWindowMillis;
    }

    public boolean hasRefundTimeout()
    {
      return this.hasRefundTimeout;
    }

    public boolean hasServerInitiated()
    {
      return this.hasServerInitiated;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public AndroidAppDeliveryData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setDownloadSize(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
          setSignature(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setDownloadUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          AndroidAppDelivery.AppFileMetadata localAppFileMetadata = new AndroidAppDelivery.AppFileMetadata();
          paramCodedInputStreamMicro.readMessage(localAppFileMetadata);
          addAdditionalFile(localAppFileMetadata);
          break;
        case 42:
          AndroidAppDelivery.HttpCookie localHttpCookie = new AndroidAppDelivery.HttpCookie();
          paramCodedInputStreamMicro.readMessage(localHttpCookie);
          addDownloadAuthCookie(localHttpCookie);
          break;
        case 48:
          setForwardLocked(paramCodedInputStreamMicro.readBool());
          break;
        case 56:
          setRefundTimeout(paramCodedInputStreamMicro.readInt64());
          break;
        case 64:
          setServerInitiated(paramCodedInputStreamMicro.readBool());
          break;
        case 72:
          setPostInstallRefundWindowMillis(paramCodedInputStreamMicro.readInt64());
          break;
        case 80:
          setImmediateStartNeeded(paramCodedInputStreamMicro.readBool());
          break;
        case 90:
          AndroidAppDelivery.AndroidAppPatchData localAndroidAppPatchData = new AndroidAppDelivery.AndroidAppPatchData();
          paramCodedInputStreamMicro.readMessage(localAndroidAppPatchData);
          setPatchData(localAndroidAppPatchData);
          break;
        case 98:
        }
        AndroidAppDelivery.EncryptionParams localEncryptionParams = new AndroidAppDelivery.EncryptionParams();
        paramCodedInputStreamMicro.readMessage(localEncryptionParams);
        setEncryptionParams(localEncryptionParams);
      }
    }

    public AndroidAppDeliveryData setDownloadSize(long paramLong)
    {
      this.hasDownloadSize = true;
      this.downloadSize_ = paramLong;
      return this;
    }

    public AndroidAppDeliveryData setDownloadUrl(String paramString)
    {
      this.hasDownloadUrl = true;
      this.downloadUrl_ = paramString;
      return this;
    }

    public AndroidAppDeliveryData setEncryptionParams(AndroidAppDelivery.EncryptionParams paramEncryptionParams)
    {
      if (paramEncryptionParams == null)
        throw new NullPointerException();
      this.hasEncryptionParams = true;
      this.encryptionParams_ = paramEncryptionParams;
      return this;
    }

    public AndroidAppDeliveryData setForwardLocked(boolean paramBoolean)
    {
      this.hasForwardLocked = true;
      this.forwardLocked_ = paramBoolean;
      return this;
    }

    public AndroidAppDeliveryData setImmediateStartNeeded(boolean paramBoolean)
    {
      this.hasImmediateStartNeeded = true;
      this.immediateStartNeeded_ = paramBoolean;
      return this;
    }

    public AndroidAppDeliveryData setPatchData(AndroidAppDelivery.AndroidAppPatchData paramAndroidAppPatchData)
    {
      if (paramAndroidAppPatchData == null)
        throw new NullPointerException();
      this.hasPatchData = true;
      this.patchData_ = paramAndroidAppPatchData;
      return this;
    }

    public AndroidAppDeliveryData setPostInstallRefundWindowMillis(long paramLong)
    {
      this.hasPostInstallRefundWindowMillis = true;
      this.postInstallRefundWindowMillis_ = paramLong;
      return this;
    }

    public AndroidAppDeliveryData setRefundTimeout(long paramLong)
    {
      this.hasRefundTimeout = true;
      this.refundTimeout_ = paramLong;
      return this;
    }

    public AndroidAppDeliveryData setServerInitiated(boolean paramBoolean)
    {
      this.hasServerInitiated = true;
      this.serverInitiated_ = paramBoolean;
      return this;
    }

    public AndroidAppDeliveryData setSignature(String paramString)
    {
      this.hasSignature = true;
      this.signature_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDownloadSize())
        paramCodedOutputStreamMicro.writeInt64(1, getDownloadSize());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeString(2, getSignature());
      if (hasDownloadUrl())
        paramCodedOutputStreamMicro.writeString(3, getDownloadUrl());
      Iterator localIterator1 = getAdditionalFileList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (AndroidAppDelivery.AppFileMetadata)localIterator1.next());
      Iterator localIterator2 = getDownloadAuthCookieList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(5, (AndroidAppDelivery.HttpCookie)localIterator2.next());
      if (hasForwardLocked())
        paramCodedOutputStreamMicro.writeBool(6, getForwardLocked());
      if (hasRefundTimeout())
        paramCodedOutputStreamMicro.writeInt64(7, getRefundTimeout());
      if (hasServerInitiated())
        paramCodedOutputStreamMicro.writeBool(8, getServerInitiated());
      if (hasPostInstallRefundWindowMillis())
        paramCodedOutputStreamMicro.writeInt64(9, getPostInstallRefundWindowMillis());
      if (hasImmediateStartNeeded())
        paramCodedOutputStreamMicro.writeBool(10, getImmediateStartNeeded());
      if (hasPatchData())
        paramCodedOutputStreamMicro.writeMessage(11, getPatchData());
      if (hasEncryptionParams())
        paramCodedOutputStreamMicro.writeMessage(12, getEncryptionParams());
    }
  }

  public static final class AndroidAppPatchData extends MessageMicro
  {
    private String baseSignature_ = "";
    private int baseVersionCode_ = 0;
    private int cachedSize = -1;
    private String downloadUrl_ = "";
    private boolean hasBaseSignature;
    private boolean hasBaseVersionCode;
    private boolean hasDownloadUrl;
    private boolean hasMaxPatchSize;
    private boolean hasPatchFormat;
    private long maxPatchSize_ = 0L;
    private int patchFormat_ = 1;

    public String getBaseSignature()
    {
      return this.baseSignature_;
    }

    public int getBaseVersionCode()
    {
      return this.baseVersionCode_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDownloadUrl()
    {
      return this.downloadUrl_;
    }

    public long getMaxPatchSize()
    {
      return this.maxPatchSize_;
    }

    public int getPatchFormat()
    {
      return this.patchFormat_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBaseVersionCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getBaseVersionCode());
      if (hasBaseSignature())
        i += CodedOutputStreamMicro.computeStringSize(2, getBaseSignature());
      if (hasDownloadUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getDownloadUrl());
      if (hasPatchFormat())
        i += CodedOutputStreamMicro.computeInt32Size(4, getPatchFormat());
      if (hasMaxPatchSize())
        i += CodedOutputStreamMicro.computeInt64Size(5, getMaxPatchSize());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBaseSignature()
    {
      return this.hasBaseSignature;
    }

    public boolean hasBaseVersionCode()
    {
      return this.hasBaseVersionCode;
    }

    public boolean hasDownloadUrl()
    {
      return this.hasDownloadUrl;
    }

    public boolean hasMaxPatchSize()
    {
      return this.hasMaxPatchSize;
    }

    public boolean hasPatchFormat()
    {
      return this.hasPatchFormat;
    }

    public AndroidAppPatchData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setBaseVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setBaseSignature(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setDownloadUrl(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setPatchFormat(paramCodedInputStreamMicro.readInt32());
          break;
        case 40:
        }
        setMaxPatchSize(paramCodedInputStreamMicro.readInt64());
      }
    }

    public AndroidAppPatchData setBaseSignature(String paramString)
    {
      this.hasBaseSignature = true;
      this.baseSignature_ = paramString;
      return this;
    }

    public AndroidAppPatchData setBaseVersionCode(int paramInt)
    {
      this.hasBaseVersionCode = true;
      this.baseVersionCode_ = paramInt;
      return this;
    }

    public AndroidAppPatchData setDownloadUrl(String paramString)
    {
      this.hasDownloadUrl = true;
      this.downloadUrl_ = paramString;
      return this;
    }

    public AndroidAppPatchData setMaxPatchSize(long paramLong)
    {
      this.hasMaxPatchSize = true;
      this.maxPatchSize_ = paramLong;
      return this;
    }

    public AndroidAppPatchData setPatchFormat(int paramInt)
    {
      this.hasPatchFormat = true;
      this.patchFormat_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBaseVersionCode())
        paramCodedOutputStreamMicro.writeInt32(1, getBaseVersionCode());
      if (hasBaseSignature())
        paramCodedOutputStreamMicro.writeString(2, getBaseSignature());
      if (hasDownloadUrl())
        paramCodedOutputStreamMicro.writeString(3, getDownloadUrl());
      if (hasPatchFormat())
        paramCodedOutputStreamMicro.writeInt32(4, getPatchFormat());
      if (hasMaxPatchSize())
        paramCodedOutputStreamMicro.writeInt64(5, getMaxPatchSize());
    }
  }

  public static final class AppFileMetadata extends MessageMicro
  {
    private int cachedSize = -1;
    private String downloadUrl_ = "";
    private int fileType_ = 0;
    private boolean hasDownloadUrl;
    private boolean hasFileType;
    private boolean hasSize;
    private boolean hasVersionCode;
    private long size_ = 0L;
    private int versionCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDownloadUrl()
    {
      return this.downloadUrl_;
    }

    public int getFileType()
    {
      return this.fileType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasFileType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getFileType());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
      if (hasSize())
        i += CodedOutputStreamMicro.computeInt64Size(3, getSize());
      if (hasDownloadUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getDownloadUrl());
      this.cachedSize = i;
      return i;
    }

    public long getSize()
    {
      return this.size_;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasDownloadUrl()
    {
      return this.hasDownloadUrl;
    }

    public boolean hasFileType()
    {
      return this.hasFileType;
    }

    public boolean hasSize()
    {
      return this.hasSize;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public AppFileMetadata mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setFileType(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setSize(paramCodedInputStreamMicro.readInt64());
          break;
        case 34:
        }
        setDownloadUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public AppFileMetadata setDownloadUrl(String paramString)
    {
      this.hasDownloadUrl = true;
      this.downloadUrl_ = paramString;
      return this;
    }

    public AppFileMetadata setFileType(int paramInt)
    {
      this.hasFileType = true;
      this.fileType_ = paramInt;
      return this;
    }

    public AppFileMetadata setSize(long paramLong)
    {
      this.hasSize = true;
      this.size_ = paramLong;
      return this;
    }

    public AppFileMetadata setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasFileType())
        paramCodedOutputStreamMicro.writeInt32(1, getFileType());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      if (hasSize())
        paramCodedOutputStreamMicro.writeInt64(3, getSize());
      if (hasDownloadUrl())
        paramCodedOutputStreamMicro.writeString(4, getDownloadUrl());
    }
  }

  public static final class EncryptionParams extends MessageMicro
  {
    private int cachedSize = -1;
    private String encryptionKey_ = "";
    private boolean hasEncryptionKey;
    private boolean hasHmacKey;
    private boolean hasVersion;
    private String hmacKey_ = "";
    private int version_ = 1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getEncryptionKey()
    {
      return this.encryptionKey_;
    }

    public String getHmacKey()
    {
      return this.hmacKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasVersion())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getVersion());
      if (hasEncryptionKey())
        i += CodedOutputStreamMicro.computeStringSize(2, getEncryptionKey());
      if (hasHmacKey())
        i += CodedOutputStreamMicro.computeStringSize(3, getHmacKey());
      this.cachedSize = i;
      return i;
    }

    public int getVersion()
    {
      return this.version_;
    }

    public boolean hasEncryptionKey()
    {
      return this.hasEncryptionKey;
    }

    public boolean hasHmacKey()
    {
      return this.hasHmacKey;
    }

    public boolean hasVersion()
    {
      return this.hasVersion;
    }

    public EncryptionParams mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setEncryptionKey(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setHmacKey(paramCodedInputStreamMicro.readString());
      }
    }

    public EncryptionParams setEncryptionKey(String paramString)
    {
      this.hasEncryptionKey = true;
      this.encryptionKey_ = paramString;
      return this;
    }

    public EncryptionParams setHmacKey(String paramString)
    {
      this.hasHmacKey = true;
      this.hmacKey_ = paramString;
      return this;
    }

    public EncryptionParams setVersion(int paramInt)
    {
      this.hasVersion = true;
      this.version_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasVersion())
        paramCodedOutputStreamMicro.writeInt32(1, getVersion());
      if (hasEncryptionKey())
        paramCodedOutputStreamMicro.writeString(2, getEncryptionKey());
      if (hasHmacKey())
        paramCodedOutputStreamMicro.writeString(3, getHmacKey());
    }
  }

  public static final class HttpCookie extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasName;
    private boolean hasValue;
    private String name_ = "";
    private String value_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getName()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasValue())
        i += CodedOutputStreamMicro.computeStringSize(2, getValue());
      this.cachedSize = i;
      return i;
    }

    public String getValue()
    {
      return this.value_;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasValue()
    {
      return this.hasValue;
    }

    public HttpCookie mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setValue(paramCodedInputStreamMicro.readString());
      }
    }

    public HttpCookie setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public HttpCookie setValue(String paramString)
    {
      this.hasValue = true;
      this.value_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasValue())
        paramCodedOutputStreamMicro.writeString(2, getValue());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.AndroidAppDelivery
 * JD-Core Version:    0.6.2
 */
package com.google.android.vending.verifier.protos;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class CsdClient
{
  public static final class ClientDownloadRequest extends MessageMicro
  {
    private long androidId_ = 0L;
    private ApkInfo apkInfo_ = null;
    private int cachedSize = -1;
    private List<String> clientAsn_ = Collections.emptyList();
    private Digests digests_ = null;
    private int downloadType_ = 0;
    private String fileBasename_ = "";
    private boolean hasAndroidId;
    private boolean hasApkInfo;
    private boolean hasDigests;
    private boolean hasDownloadType;
    private boolean hasFileBasename;
    private boolean hasLength;
    private boolean hasLocale;
    private boolean hasSignature;
    private boolean hasUrl;
    private boolean hasUserInitiated;
    private long length_ = 0L;
    private String locale_ = "";
    private List<Resource> resources_ = Collections.emptyList();
    private SignatureInfo signature_ = null;
    private String url_ = "";
    private boolean userInitiated_ = false;

    public ClientDownloadRequest addClientAsn(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.clientAsn_.isEmpty())
        this.clientAsn_ = new ArrayList();
      this.clientAsn_.add(paramString);
      return this;
    }

    public ClientDownloadRequest addResources(Resource paramResource)
    {
      if (paramResource == null)
        throw new NullPointerException();
      if (this.resources_.isEmpty())
        this.resources_ = new ArrayList();
      this.resources_.add(paramResource);
      return this;
    }

    public long getAndroidId()
    {
      return this.androidId_;
    }

    public ApkInfo getApkInfo()
    {
      return this.apkInfo_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getClientAsnList()
    {
      return this.clientAsn_;
    }

    public Digests getDigests()
    {
      return this.digests_;
    }

    public int getDownloadType()
    {
      return this.downloadType_;
    }

    public String getFileBasename()
    {
      return this.fileBasename_;
    }

    public long getLength()
    {
      return this.length_;
    }

    public String getLocale()
    {
      return this.locale_;
    }

    public List<Resource> getResourcesList()
    {
      return this.resources_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUrl());
      if (hasDigests())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDigests());
      if (hasLength())
        i += CodedOutputStreamMicro.computeInt64Size(3, getLength());
      Iterator localIterator1 = getResourcesList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (Resource)localIterator1.next());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeMessageSize(5, getSignature());
      if (hasUserInitiated())
        i += CodedOutputStreamMicro.computeBoolSize(6, getUserInitiated());
      int j = 0;
      Iterator localIterator2 = getClientAsnList().iterator();
      while (localIterator2.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int k = i + j + 1 * getClientAsnList().size();
      if (hasFileBasename())
        k += CodedOutputStreamMicro.computeStringSize(9, getFileBasename());
      if (hasDownloadType())
        k += CodedOutputStreamMicro.computeInt32Size(10, getDownloadType());
      if (hasLocale())
        k += CodedOutputStreamMicro.computeStringSize(11, getLocale());
      if (hasApkInfo())
        k += CodedOutputStreamMicro.computeMessageSize(12, getApkInfo());
      if (hasAndroidId())
        k += CodedOutputStreamMicro.computeFixed64Size(13, getAndroidId());
      this.cachedSize = k;
      return k;
    }

    public SignatureInfo getSignature()
    {
      return this.signature_;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public boolean getUserInitiated()
    {
      return this.userInitiated_;
    }

    public boolean hasAndroidId()
    {
      return this.hasAndroidId;
    }

    public boolean hasApkInfo()
    {
      return this.hasApkInfo;
    }

    public boolean hasDigests()
    {
      return this.hasDigests;
    }

    public boolean hasDownloadType()
    {
      return this.hasDownloadType;
    }

    public boolean hasFileBasename()
    {
      return this.hasFileBasename;
    }

    public boolean hasLength()
    {
      return this.hasLength;
    }

    public boolean hasLocale()
    {
      return this.hasLocale;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public boolean hasUserInitiated()
    {
      return this.hasUserInitiated;
    }

    public ClientDownloadRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          Digests localDigests = new Digests();
          paramCodedInputStreamMicro.readMessage(localDigests);
          setDigests(localDigests);
          break;
        case 24:
          setLength(paramCodedInputStreamMicro.readInt64());
          break;
        case 34:
          Resource localResource = new Resource();
          paramCodedInputStreamMicro.readMessage(localResource);
          addResources(localResource);
          break;
        case 42:
          SignatureInfo localSignatureInfo = new SignatureInfo();
          paramCodedInputStreamMicro.readMessage(localSignatureInfo);
          setSignature(localSignatureInfo);
          break;
        case 48:
          setUserInitiated(paramCodedInputStreamMicro.readBool());
          break;
        case 66:
          addClientAsn(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setFileBasename(paramCodedInputStreamMicro.readString());
          break;
        case 80:
          setDownloadType(paramCodedInputStreamMicro.readInt32());
          break;
        case 90:
          setLocale(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          ApkInfo localApkInfo = new ApkInfo();
          paramCodedInputStreamMicro.readMessage(localApkInfo);
          setApkInfo(localApkInfo);
          break;
        case 105:
        }
        setAndroidId(paramCodedInputStreamMicro.readFixed64());
      }
    }

    public ClientDownloadRequest setAndroidId(long paramLong)
    {
      this.hasAndroidId = true;
      this.androidId_ = paramLong;
      return this;
    }

    public ClientDownloadRequest setApkInfo(ApkInfo paramApkInfo)
    {
      if (paramApkInfo == null)
        throw new NullPointerException();
      this.hasApkInfo = true;
      this.apkInfo_ = paramApkInfo;
      return this;
    }

    public ClientDownloadRequest setDigests(Digests paramDigests)
    {
      if (paramDigests == null)
        throw new NullPointerException();
      this.hasDigests = true;
      this.digests_ = paramDigests;
      return this;
    }

    public ClientDownloadRequest setDownloadType(int paramInt)
    {
      this.hasDownloadType = true;
      this.downloadType_ = paramInt;
      return this;
    }

    public ClientDownloadRequest setFileBasename(String paramString)
    {
      this.hasFileBasename = true;
      this.fileBasename_ = paramString;
      return this;
    }

    public ClientDownloadRequest setLength(long paramLong)
    {
      this.hasLength = true;
      this.length_ = paramLong;
      return this;
    }

    public ClientDownloadRequest setLocale(String paramString)
    {
      this.hasLocale = true;
      this.locale_ = paramString;
      return this;
    }

    public ClientDownloadRequest setSignature(SignatureInfo paramSignatureInfo)
    {
      if (paramSignatureInfo == null)
        throw new NullPointerException();
      this.hasSignature = true;
      this.signature_ = paramSignatureInfo;
      return this;
    }

    public ClientDownloadRequest setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public ClientDownloadRequest setUserInitiated(boolean paramBoolean)
    {
      this.hasUserInitiated = true;
      this.userInitiated_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(1, getUrl());
      if (hasDigests())
        paramCodedOutputStreamMicro.writeMessage(2, getDigests());
      if (hasLength())
        paramCodedOutputStreamMicro.writeInt64(3, getLength());
      Iterator localIterator1 = getResourcesList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (Resource)localIterator1.next());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeMessage(5, getSignature());
      if (hasUserInitiated())
        paramCodedOutputStreamMicro.writeBool(6, getUserInitiated());
      Iterator localIterator2 = getClientAsnList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(8, (String)localIterator2.next());
      if (hasFileBasename())
        paramCodedOutputStreamMicro.writeString(9, getFileBasename());
      if (hasDownloadType())
        paramCodedOutputStreamMicro.writeInt32(10, getDownloadType());
      if (hasLocale())
        paramCodedOutputStreamMicro.writeString(11, getLocale());
      if (hasApkInfo())
        paramCodedOutputStreamMicro.writeMessage(12, getApkInfo());
      if (hasAndroidId())
        paramCodedOutputStreamMicro.writeFixed64(13, getAndroidId());
    }

    public static final class ApkInfo extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasPackageName;
      private boolean hasVersionCode;
      private String packageName_ = "";
      private int versionCode_ = 0;

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getPackageName()
      {
        return this.packageName_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasPackageName())
          i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPackageName());
        if (hasVersionCode())
          i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
        this.cachedSize = i;
        return i;
      }

      public int getVersionCode()
      {
        return this.versionCode_;
      }

      public boolean hasPackageName()
      {
        return this.hasPackageName;
      }

      public boolean hasVersionCode()
      {
        return this.hasVersionCode;
      }

      public ApkInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setPackageName(paramCodedInputStreamMicro.readString());
            break;
          case 16:
          }
          setVersionCode(paramCodedInputStreamMicro.readInt32());
        }
      }

      public ApkInfo setPackageName(String paramString)
      {
        this.hasPackageName = true;
        this.packageName_ = paramString;
        return this;
      }

      public ApkInfo setVersionCode(int paramInt)
      {
        this.hasVersionCode = true;
        this.versionCode_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasPackageName())
          paramCodedOutputStreamMicro.writeString(1, getPackageName());
        if (hasVersionCode())
          paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      }
    }

    public static final class CertificateChain extends MessageMicro
    {
      private int cachedSize = -1;
      private List<Element> element_ = Collections.emptyList();

      public CertificateChain addElement(Element paramElement)
      {
        if (paramElement == null)
          throw new NullPointerException();
        if (this.element_.isEmpty())
          this.element_ = new ArrayList();
        this.element_.add(paramElement);
        return this;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public List<Element> getElementList()
      {
        return this.element_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        Iterator localIterator = getElementList().iterator();
        while (localIterator.hasNext())
          i += CodedOutputStreamMicro.computeMessageSize(1, (Element)localIterator.next());
        this.cachedSize = i;
        return i;
      }

      public CertificateChain mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          }
          Element localElement = new Element();
          paramCodedInputStreamMicro.readMessage(localElement);
          addElement(localElement);
        }
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        Iterator localIterator = getElementList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeMessage(1, (Element)localIterator.next());
      }

      public static final class Element extends MessageMicro
      {
        private int cachedSize = -1;
        private ByteStringMicro certificate_ = ByteStringMicro.EMPTY;
        private long expiryTime_ = 0L;
        private ByteStringMicro fingerprint_ = ByteStringMicro.EMPTY;
        private boolean hasCertificate;
        private boolean hasExpiryTime;
        private boolean hasFingerprint;
        private boolean hasIssuer;
        private boolean hasParsedSuccessfully;
        private boolean hasStartTime;
        private boolean hasSubject;
        private ByteStringMicro issuer_ = ByteStringMicro.EMPTY;
        private boolean parsedSuccessfully_ = false;
        private long startTime_ = 0L;
        private ByteStringMicro subject_ = ByteStringMicro.EMPTY;

        public int getCachedSize()
        {
          if (this.cachedSize < 0)
            getSerializedSize();
          return this.cachedSize;
        }

        public ByteStringMicro getCertificate()
        {
          return this.certificate_;
        }

        public long getExpiryTime()
        {
          return this.expiryTime_;
        }

        public ByteStringMicro getFingerprint()
        {
          return this.fingerprint_;
        }

        public ByteStringMicro getIssuer()
        {
          return this.issuer_;
        }

        public boolean getParsedSuccessfully()
        {
          return this.parsedSuccessfully_;
        }

        public int getSerializedSize()
        {
          int i = 0;
          if (hasCertificate())
            i = 0 + CodedOutputStreamMicro.computeBytesSize(1, getCertificate());
          if (hasParsedSuccessfully())
            i += CodedOutputStreamMicro.computeBoolSize(2, getParsedSuccessfully());
          if (hasSubject())
            i += CodedOutputStreamMicro.computeBytesSize(3, getSubject());
          if (hasIssuer())
            i += CodedOutputStreamMicro.computeBytesSize(4, getIssuer());
          if (hasFingerprint())
            i += CodedOutputStreamMicro.computeBytesSize(5, getFingerprint());
          if (hasExpiryTime())
            i += CodedOutputStreamMicro.computeInt64Size(6, getExpiryTime());
          if (hasStartTime())
            i += CodedOutputStreamMicro.computeInt64Size(7, getStartTime());
          this.cachedSize = i;
          return i;
        }

        public long getStartTime()
        {
          return this.startTime_;
        }

        public ByteStringMicro getSubject()
        {
          return this.subject_;
        }

        public boolean hasCertificate()
        {
          return this.hasCertificate;
        }

        public boolean hasExpiryTime()
        {
          return this.hasExpiryTime;
        }

        public boolean hasFingerprint()
        {
          return this.hasFingerprint;
        }

        public boolean hasIssuer()
        {
          return this.hasIssuer;
        }

        public boolean hasParsedSuccessfully()
        {
          return this.hasParsedSuccessfully;
        }

        public boolean hasStartTime()
        {
          return this.hasStartTime;
        }

        public boolean hasSubject()
        {
          return this.hasSubject;
        }

        public Element mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
              setCertificate(paramCodedInputStreamMicro.readBytes());
              break;
            case 16:
              setParsedSuccessfully(paramCodedInputStreamMicro.readBool());
              break;
            case 26:
              setSubject(paramCodedInputStreamMicro.readBytes());
              break;
            case 34:
              setIssuer(paramCodedInputStreamMicro.readBytes());
              break;
            case 42:
              setFingerprint(paramCodedInputStreamMicro.readBytes());
              break;
            case 48:
              setExpiryTime(paramCodedInputStreamMicro.readInt64());
              break;
            case 56:
            }
            setStartTime(paramCodedInputStreamMicro.readInt64());
          }
        }

        public Element setCertificate(ByteStringMicro paramByteStringMicro)
        {
          this.hasCertificate = true;
          this.certificate_ = paramByteStringMicro;
          return this;
        }

        public Element setExpiryTime(long paramLong)
        {
          this.hasExpiryTime = true;
          this.expiryTime_ = paramLong;
          return this;
        }

        public Element setFingerprint(ByteStringMicro paramByteStringMicro)
        {
          this.hasFingerprint = true;
          this.fingerprint_ = paramByteStringMicro;
          return this;
        }

        public Element setIssuer(ByteStringMicro paramByteStringMicro)
        {
          this.hasIssuer = true;
          this.issuer_ = paramByteStringMicro;
          return this;
        }

        public Element setParsedSuccessfully(boolean paramBoolean)
        {
          this.hasParsedSuccessfully = true;
          this.parsedSuccessfully_ = paramBoolean;
          return this;
        }

        public Element setStartTime(long paramLong)
        {
          this.hasStartTime = true;
          this.startTime_ = paramLong;
          return this;
        }

        public Element setSubject(ByteStringMicro paramByteStringMicro)
        {
          this.hasSubject = true;
          this.subject_ = paramByteStringMicro;
          return this;
        }

        public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
          throws IOException
        {
          if (hasCertificate())
            paramCodedOutputStreamMicro.writeBytes(1, getCertificate());
          if (hasParsedSuccessfully())
            paramCodedOutputStreamMicro.writeBool(2, getParsedSuccessfully());
          if (hasSubject())
            paramCodedOutputStreamMicro.writeBytes(3, getSubject());
          if (hasIssuer())
            paramCodedOutputStreamMicro.writeBytes(4, getIssuer());
          if (hasFingerprint())
            paramCodedOutputStreamMicro.writeBytes(5, getFingerprint());
          if (hasExpiryTime())
            paramCodedOutputStreamMicro.writeInt64(6, getExpiryTime());
          if (hasStartTime())
            paramCodedOutputStreamMicro.writeInt64(7, getStartTime());
        }
      }
    }

    public static final class Digests extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasMd5;
      private boolean hasSha1;
      private boolean hasSha256;
      private ByteStringMicro md5_ = ByteStringMicro.EMPTY;
      private ByteStringMicro sha1_ = ByteStringMicro.EMPTY;
      private ByteStringMicro sha256_ = ByteStringMicro.EMPTY;

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public ByteStringMicro getMd5()
      {
        return this.md5_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasSha256())
          i = 0 + CodedOutputStreamMicro.computeBytesSize(1, getSha256());
        if (hasSha1())
          i += CodedOutputStreamMicro.computeBytesSize(2, getSha1());
        if (hasMd5())
          i += CodedOutputStreamMicro.computeBytesSize(3, getMd5());
        this.cachedSize = i;
        return i;
      }

      public ByteStringMicro getSha1()
      {
        return this.sha1_;
      }

      public ByteStringMicro getSha256()
      {
        return this.sha256_;
      }

      public boolean hasMd5()
      {
        return this.hasMd5;
      }

      public boolean hasSha1()
      {
        return this.hasSha1;
      }

      public boolean hasSha256()
      {
        return this.hasSha256;
      }

      public Digests mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setSha256(paramCodedInputStreamMicro.readBytes());
            break;
          case 18:
            setSha1(paramCodedInputStreamMicro.readBytes());
            break;
          case 26:
          }
          setMd5(paramCodedInputStreamMicro.readBytes());
        }
      }

      public Digests setMd5(ByteStringMicro paramByteStringMicro)
      {
        this.hasMd5 = true;
        this.md5_ = paramByteStringMicro;
        return this;
      }

      public Digests setSha1(ByteStringMicro paramByteStringMicro)
      {
        this.hasSha1 = true;
        this.sha1_ = paramByteStringMicro;
        return this;
      }

      public Digests setSha256(ByteStringMicro paramByteStringMicro)
      {
        this.hasSha256 = true;
        this.sha256_ = paramByteStringMicro;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasSha256())
          paramCodedOutputStreamMicro.writeBytes(1, getSha256());
        if (hasSha1())
          paramCodedOutputStreamMicro.writeBytes(2, getSha1());
        if (hasMd5())
          paramCodedOutputStreamMicro.writeBytes(3, getMd5());
      }
    }

    public static final class Resource extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasReferrer;
      private boolean hasRemoteIp;
      private boolean hasType;
      private boolean hasUrl;
      private String referrer_ = "";
      private ByteStringMicro remoteIp_ = ByteStringMicro.EMPTY;
      private int type_ = 0;
      private String url_ = "";

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getReferrer()
      {
        return this.referrer_;
      }

      public ByteStringMicro getRemoteIp()
      {
        return this.remoteIp_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasUrl())
          i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUrl());
        if (hasType())
          i += CodedOutputStreamMicro.computeInt32Size(2, getType());
        if (hasRemoteIp())
          i += CodedOutputStreamMicro.computeBytesSize(3, getRemoteIp());
        if (hasReferrer())
          i += CodedOutputStreamMicro.computeStringSize(4, getReferrer());
        this.cachedSize = i;
        return i;
      }

      public int getType()
      {
        return this.type_;
      }

      public String getUrl()
      {
        return this.url_;
      }

      public boolean hasReferrer()
      {
        return this.hasReferrer;
      }

      public boolean hasRemoteIp()
      {
        return this.hasRemoteIp;
      }

      public boolean hasType()
      {
        return this.hasType;
      }

      public boolean hasUrl()
      {
        return this.hasUrl;
      }

      public Resource mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setUrl(paramCodedInputStreamMicro.readString());
            break;
          case 16:
            setType(paramCodedInputStreamMicro.readInt32());
            break;
          case 26:
            setRemoteIp(paramCodedInputStreamMicro.readBytes());
            break;
          case 34:
          }
          setReferrer(paramCodedInputStreamMicro.readString());
        }
      }

      public Resource setReferrer(String paramString)
      {
        this.hasReferrer = true;
        this.referrer_ = paramString;
        return this;
      }

      public Resource setRemoteIp(ByteStringMicro paramByteStringMicro)
      {
        this.hasRemoteIp = true;
        this.remoteIp_ = paramByteStringMicro;
        return this;
      }

      public Resource setType(int paramInt)
      {
        this.hasType = true;
        this.type_ = paramInt;
        return this;
      }

      public Resource setUrl(String paramString)
      {
        this.hasUrl = true;
        this.url_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasUrl())
          paramCodedOutputStreamMicro.writeString(1, getUrl());
        if (hasType())
          paramCodedOutputStreamMicro.writeInt32(2, getType());
        if (hasRemoteIp())
          paramCodedOutputStreamMicro.writeBytes(3, getRemoteIp());
        if (hasReferrer())
          paramCodedOutputStreamMicro.writeString(4, getReferrer());
      }
    }

    public static final class SignatureInfo extends MessageMicro
    {
      private int cachedSize = -1;
      private List<CsdClient.ClientDownloadRequest.CertificateChain> certificateChain_ = Collections.emptyList();
      private boolean hasTrusted;
      private boolean trusted_ = false;

      public SignatureInfo addCertificateChain(CsdClient.ClientDownloadRequest.CertificateChain paramCertificateChain)
      {
        if (paramCertificateChain == null)
          throw new NullPointerException();
        if (this.certificateChain_.isEmpty())
          this.certificateChain_ = new ArrayList();
        this.certificateChain_.add(paramCertificateChain);
        return this;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public List<CsdClient.ClientDownloadRequest.CertificateChain> getCertificateChainList()
      {
        return this.certificateChain_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        Iterator localIterator = getCertificateChainList().iterator();
        while (localIterator.hasNext())
          i += CodedOutputStreamMicro.computeMessageSize(1, (CsdClient.ClientDownloadRequest.CertificateChain)localIterator.next());
        if (hasTrusted())
          i += CodedOutputStreamMicro.computeBoolSize(2, getTrusted());
        this.cachedSize = i;
        return i;
      }

      public boolean getTrusted()
      {
        return this.trusted_;
      }

      public boolean hasTrusted()
      {
        return this.hasTrusted;
      }

      public SignatureInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            CsdClient.ClientDownloadRequest.CertificateChain localCertificateChain = new CsdClient.ClientDownloadRequest.CertificateChain();
            paramCodedInputStreamMicro.readMessage(localCertificateChain);
            addCertificateChain(localCertificateChain);
            break;
          case 16:
          }
          setTrusted(paramCodedInputStreamMicro.readBool());
        }
      }

      public SignatureInfo setTrusted(boolean paramBoolean)
      {
        this.hasTrusted = true;
        this.trusted_ = paramBoolean;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        Iterator localIterator = getCertificateChainList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeMessage(1, (CsdClient.ClientDownloadRequest.CertificateChain)localIterator.next());
        if (hasTrusted())
          paramCodedOutputStreamMicro.writeBool(2, getTrusted());
      }
    }
  }

  public static final class ClientDownloadResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasMoreInfo;
    private boolean hasToken;
    private boolean hasVerdict;
    private MoreInfo moreInfo_ = null;
    private ByteStringMicro token_ = ByteStringMicro.EMPTY;
    private int verdict_ = 0;

    public static ClientDownloadResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferMicroException
    {
      return (ClientDownloadResponse)new ClientDownloadResponse().mergeFrom(paramArrayOfByte);
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public MoreInfo getMoreInfo()
    {
      return this.moreInfo_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasVerdict())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getVerdict());
      if (hasMoreInfo())
        i += CodedOutputStreamMicro.computeMessageSize(2, getMoreInfo());
      if (hasToken())
        i += CodedOutputStreamMicro.computeBytesSize(3, getToken());
      this.cachedSize = i;
      return i;
    }

    public ByteStringMicro getToken()
    {
      return this.token_;
    }

    public int getVerdict()
    {
      return this.verdict_;
    }

    public boolean hasMoreInfo()
    {
      return this.hasMoreInfo;
    }

    public boolean hasToken()
    {
      return this.hasToken;
    }

    public boolean hasVerdict()
    {
      return this.hasVerdict;
    }

    public ClientDownloadResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setVerdict(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          MoreInfo localMoreInfo = new MoreInfo();
          paramCodedInputStreamMicro.readMessage(localMoreInfo);
          setMoreInfo(localMoreInfo);
          break;
        case 26:
        }
        setToken(paramCodedInputStreamMicro.readBytes());
      }
    }

    public ClientDownloadResponse setMoreInfo(MoreInfo paramMoreInfo)
    {
      if (paramMoreInfo == null)
        throw new NullPointerException();
      this.hasMoreInfo = true;
      this.moreInfo_ = paramMoreInfo;
      return this;
    }

    public ClientDownloadResponse setToken(ByteStringMicro paramByteStringMicro)
    {
      this.hasToken = true;
      this.token_ = paramByteStringMicro;
      return this;
    }

    public ClientDownloadResponse setVerdict(int paramInt)
    {
      this.hasVerdict = true;
      this.verdict_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasVerdict())
        paramCodedOutputStreamMicro.writeInt32(1, getVerdict());
      if (hasMoreInfo())
        paramCodedOutputStreamMicro.writeMessage(2, getMoreInfo());
      if (hasToken())
        paramCodedOutputStreamMicro.writeBytes(3, getToken());
    }

    public static final class MoreInfo extends MessageMicro
    {
      private int cachedSize = -1;
      private String description_ = "";
      private boolean hasDescription;
      private boolean hasUrl;
      private String url_ = "";

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getDescription()
      {
        return this.description_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasDescription())
          i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDescription());
        if (hasUrl())
          i += CodedOutputStreamMicro.computeStringSize(2, getUrl());
        this.cachedSize = i;
        return i;
      }

      public String getUrl()
      {
        return this.url_;
      }

      public boolean hasDescription()
      {
        return this.hasDescription;
      }

      public boolean hasUrl()
      {
        return this.hasUrl;
      }

      public MoreInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setDescription(paramCodedInputStreamMicro.readString());
            break;
          case 18:
          }
          setUrl(paramCodedInputStreamMicro.readString());
        }
      }

      public MoreInfo setDescription(String paramString)
      {
        this.hasDescription = true;
        this.description_ = paramString;
        return this;
      }

      public MoreInfo setUrl(String paramString)
      {
        this.hasUrl = true;
        this.url_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasDescription())
          paramCodedOutputStreamMicro.writeString(1, getDescription());
        if (hasUrl())
          paramCodedOutputStreamMicro.writeString(2, getUrl());
      }
    }
  }

  public static final class ClientDownloadStatsRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasToken;
    private boolean hasUserDecision;
    private ByteStringMicro token_ = ByteStringMicro.EMPTY;
    private int userDecision_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUserDecision())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getUserDecision());
      if (hasToken())
        i += CodedOutputStreamMicro.computeBytesSize(2, getToken());
      this.cachedSize = i;
      return i;
    }

    public ByteStringMicro getToken()
    {
      return this.token_;
    }

    public int getUserDecision()
    {
      return this.userDecision_;
    }

    public boolean hasToken()
    {
      return this.hasToken;
    }

    public boolean hasUserDecision()
    {
      return this.hasUserDecision;
    }

    public ClientDownloadStatsRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUserDecision(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setToken(paramCodedInputStreamMicro.readBytes());
      }
    }

    public ClientDownloadStatsRequest setToken(ByteStringMicro paramByteStringMicro)
    {
      this.hasToken = true;
      this.token_ = paramByteStringMicro;
      return this;
    }

    public ClientDownloadStatsRequest setUserDecision(int paramInt)
    {
      this.hasUserDecision = true;
      this.userDecision_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUserDecision())
        paramCodedOutputStreamMicro.writeInt32(1, getUserDecision());
      if (hasToken())
        paramCodedOutputStreamMicro.writeBytes(2, getToken());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.protos.CsdClient
 * JD-Core Version:    0.6.2
 */
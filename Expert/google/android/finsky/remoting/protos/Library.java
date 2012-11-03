package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Library
{
  public static final class LibraryAppDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private String certificateHash_ = "";
    private boolean hasCertificateHash;
    private boolean hasPostDeliveryRefundWindowMsec;
    private boolean hasRefundTimeoutTimestampMsec;
    private long postDeliveryRefundWindowMsec_ = 0L;
    private long refundTimeoutTimestampMsec_ = 0L;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCertificateHash()
    {
      return this.certificateHash_;
    }

    public long getPostDeliveryRefundWindowMsec()
    {
      return this.postDeliveryRefundWindowMsec_;
    }

    public long getRefundTimeoutTimestampMsec()
    {
      return this.refundTimeoutTimestampMsec_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCertificateHash())
        i = 0 + CodedOutputStreamMicro.computeStringSize(2, getCertificateHash());
      if (hasRefundTimeoutTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(3, getRefundTimeoutTimestampMsec());
      if (hasPostDeliveryRefundWindowMsec())
        i += CodedOutputStreamMicro.computeInt64Size(4, getPostDeliveryRefundWindowMsec());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCertificateHash()
    {
      return this.hasCertificateHash;
    }

    public boolean hasPostDeliveryRefundWindowMsec()
    {
      return this.hasPostDeliveryRefundWindowMsec;
    }

    public boolean hasRefundTimeoutTimestampMsec()
    {
      return this.hasRefundTimeoutTimestampMsec;
    }

    public LibraryAppDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 18:
          setCertificateHash(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setRefundTimeoutTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 32:
        }
        setPostDeliveryRefundWindowMsec(paramCodedInputStreamMicro.readInt64());
      }
    }

    public LibraryAppDetails setCertificateHash(String paramString)
    {
      this.hasCertificateHash = true;
      this.certificateHash_ = paramString;
      return this;
    }

    public LibraryAppDetails setPostDeliveryRefundWindowMsec(long paramLong)
    {
      this.hasPostDeliveryRefundWindowMsec = true;
      this.postDeliveryRefundWindowMsec_ = paramLong;
      return this;
    }

    public LibraryAppDetails setRefundTimeoutTimestampMsec(long paramLong)
    {
      this.hasRefundTimeoutTimestampMsec = true;
      this.refundTimeoutTimestampMsec_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCertificateHash())
        paramCodedOutputStreamMicro.writeString(2, getCertificateHash());
      if (hasRefundTimeoutTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(3, getRefundTimeoutTimestampMsec());
      if (hasPostDeliveryRefundWindowMsec())
        paramCodedOutputStreamMicro.writeInt64(4, getPostDeliveryRefundWindowMsec());
    }
  }

  public static final class LibraryInAppDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSignature;
    private boolean hasSignedPurchaseData;
    private String signature_ = "";
    private String signedPurchaseData_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignedPurchaseData())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSignedPurchaseData());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeStringSize(2, getSignature());
      this.cachedSize = i;
      return i;
    }

    public String getSignature()
    {
      return this.signature_;
    }

    public String getSignedPurchaseData()
    {
      return this.signedPurchaseData_;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public boolean hasSignedPurchaseData()
    {
      return this.hasSignedPurchaseData;
    }

    public LibraryInAppDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSignedPurchaseData(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setSignature(paramCodedInputStreamMicro.readString());
      }
    }

    public LibraryInAppDetails setSignature(String paramString)
    {
      this.hasSignature = true;
      this.signature_ = paramString;
      return this;
    }

    public LibraryInAppDetails setSignedPurchaseData(String paramString)
    {
      this.hasSignedPurchaseData = true;
      this.signedPurchaseData_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignedPurchaseData())
        paramCodedOutputStreamMicro.writeString(1, getSignedPurchaseData());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeString(2, getSignature());
    }
  }

  public static final class LibraryMutation extends MessageMicro
  {
    private Library.LibraryAppDetails appDetails_ = null;
    private int cachedSize = -1;
    private boolean deleted_ = false;
    private Common.Docid docid_ = null;
    private long documentHash_ = 0L;
    private boolean hasAppDetails;
    private boolean hasDeleted;
    private boolean hasDocid;
    private boolean hasDocumentHash;
    private boolean hasInAppDetails;
    private boolean hasOfferType;
    private boolean hasSubscriptionDetails;
    private Library.LibraryInAppDetails inAppDetails_ = null;
    private int offerType_ = 1;
    private Library.LibrarySubscriptionDetails subscriptionDetails_ = null;

    public Library.LibraryAppDetails getAppDetails()
    {
      return this.appDetails_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getDeleted()
    {
      return this.deleted_;
    }

    public Common.Docid getDocid()
    {
      return this.docid_;
    }

    public long getDocumentHash()
    {
      return this.documentHash_;
    }

    public Library.LibraryInAppDetails getInAppDetails()
    {
      return this.inAppDetails_;
    }

    public int getOfferType()
    {
      return this.offerType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDocid())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDocid());
      if (hasOfferType())
        i += CodedOutputStreamMicro.computeInt32Size(2, getOfferType());
      if (hasDocumentHash())
        i += CodedOutputStreamMicro.computeInt64Size(3, getDocumentHash());
      if (hasDeleted())
        i += CodedOutputStreamMicro.computeBoolSize(4, getDeleted());
      if (hasAppDetails())
        i += CodedOutputStreamMicro.computeMessageSize(5, getAppDetails());
      if (hasSubscriptionDetails())
        i += CodedOutputStreamMicro.computeMessageSize(6, getSubscriptionDetails());
      if (hasInAppDetails())
        i += CodedOutputStreamMicro.computeMessageSize(7, getInAppDetails());
      this.cachedSize = i;
      return i;
    }

    public Library.LibrarySubscriptionDetails getSubscriptionDetails()
    {
      return this.subscriptionDetails_;
    }

    public boolean hasAppDetails()
    {
      return this.hasAppDetails;
    }

    public boolean hasDeleted()
    {
      return this.hasDeleted;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasDocumentHash()
    {
      return this.hasDocumentHash;
    }

    public boolean hasInAppDetails()
    {
      return this.hasInAppDetails;
    }

    public boolean hasOfferType()
    {
      return this.hasOfferType;
    }

    public boolean hasSubscriptionDetails()
    {
      return this.hasSubscriptionDetails;
    }

    public LibraryMutation mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Common.Docid localDocid = new Common.Docid();
          paramCodedInputStreamMicro.readMessage(localDocid);
          setDocid(localDocid);
          break;
        case 16:
          setOfferType(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setDocumentHash(paramCodedInputStreamMicro.readInt64());
          break;
        case 32:
          setDeleted(paramCodedInputStreamMicro.readBool());
          break;
        case 42:
          Library.LibraryAppDetails localLibraryAppDetails = new Library.LibraryAppDetails();
          paramCodedInputStreamMicro.readMessage(localLibraryAppDetails);
          setAppDetails(localLibraryAppDetails);
          break;
        case 50:
          Library.LibrarySubscriptionDetails localLibrarySubscriptionDetails = new Library.LibrarySubscriptionDetails();
          paramCodedInputStreamMicro.readMessage(localLibrarySubscriptionDetails);
          setSubscriptionDetails(localLibrarySubscriptionDetails);
          break;
        case 58:
        }
        Library.LibraryInAppDetails localLibraryInAppDetails = new Library.LibraryInAppDetails();
        paramCodedInputStreamMicro.readMessage(localLibraryInAppDetails);
        setInAppDetails(localLibraryInAppDetails);
      }
    }

    public LibraryMutation setAppDetails(Library.LibraryAppDetails paramLibraryAppDetails)
    {
      if (paramLibraryAppDetails == null)
        throw new NullPointerException();
      this.hasAppDetails = true;
      this.appDetails_ = paramLibraryAppDetails;
      return this;
    }

    public LibraryMutation setDeleted(boolean paramBoolean)
    {
      this.hasDeleted = true;
      this.deleted_ = paramBoolean;
      return this;
    }

    public LibraryMutation setDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasDocid = true;
      this.docid_ = paramDocid;
      return this;
    }

    public LibraryMutation setDocumentHash(long paramLong)
    {
      this.hasDocumentHash = true;
      this.documentHash_ = paramLong;
      return this;
    }

    public LibraryMutation setInAppDetails(Library.LibraryInAppDetails paramLibraryInAppDetails)
    {
      if (paramLibraryInAppDetails == null)
        throw new NullPointerException();
      this.hasInAppDetails = true;
      this.inAppDetails_ = paramLibraryInAppDetails;
      return this;
    }

    public LibraryMutation setOfferType(int paramInt)
    {
      this.hasOfferType = true;
      this.offerType_ = paramInt;
      return this;
    }

    public LibraryMutation setSubscriptionDetails(Library.LibrarySubscriptionDetails paramLibrarySubscriptionDetails)
    {
      if (paramLibrarySubscriptionDetails == null)
        throw new NullPointerException();
      this.hasSubscriptionDetails = true;
      this.subscriptionDetails_ = paramLibrarySubscriptionDetails;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDocid())
        paramCodedOutputStreamMicro.writeMessage(1, getDocid());
      if (hasOfferType())
        paramCodedOutputStreamMicro.writeInt32(2, getOfferType());
      if (hasDocumentHash())
        paramCodedOutputStreamMicro.writeInt64(3, getDocumentHash());
      if (hasDeleted())
        paramCodedOutputStreamMicro.writeBool(4, getDeleted());
      if (hasAppDetails())
        paramCodedOutputStreamMicro.writeMessage(5, getAppDetails());
      if (hasSubscriptionDetails())
        paramCodedOutputStreamMicro.writeMessage(6, getSubscriptionDetails());
      if (hasInAppDetails())
        paramCodedOutputStreamMicro.writeMessage(7, getInAppDetails());
    }
  }

  public static final class LibrarySubscriptionDetails extends MessageMicro
  {
    private boolean autoRenewing_ = false;
    private int cachedSize = -1;
    private boolean hasAutoRenewing;
    private boolean hasInitiationTimestampMsec;
    private boolean hasTrialUntilTimestampMsec;
    private boolean hasValidUntilTimestampMsec;
    private long initiationTimestampMsec_ = 0L;
    private long trialUntilTimestampMsec_ = 0L;
    private long validUntilTimestampMsec_ = 0L;

    public boolean getAutoRenewing()
    {
      return this.autoRenewing_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getInitiationTimestampMsec()
    {
      return this.initiationTimestampMsec_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInitiationTimestampMsec())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getInitiationTimestampMsec());
      if (hasValidUntilTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(2, getValidUntilTimestampMsec());
      if (hasAutoRenewing())
        i += CodedOutputStreamMicro.computeBoolSize(3, getAutoRenewing());
      if (hasTrialUntilTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(4, getTrialUntilTimestampMsec());
      this.cachedSize = i;
      return i;
    }

    public long getTrialUntilTimestampMsec()
    {
      return this.trialUntilTimestampMsec_;
    }

    public long getValidUntilTimestampMsec()
    {
      return this.validUntilTimestampMsec_;
    }

    public boolean hasAutoRenewing()
    {
      return this.hasAutoRenewing;
    }

    public boolean hasInitiationTimestampMsec()
    {
      return this.hasInitiationTimestampMsec;
    }

    public boolean hasTrialUntilTimestampMsec()
    {
      return this.hasTrialUntilTimestampMsec;
    }

    public boolean hasValidUntilTimestampMsec()
    {
      return this.hasValidUntilTimestampMsec;
    }

    public LibrarySubscriptionDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setInitiationTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 16:
          setValidUntilTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 24:
          setAutoRenewing(paramCodedInputStreamMicro.readBool());
          break;
        case 32:
        }
        setTrialUntilTimestampMsec(paramCodedInputStreamMicro.readInt64());
      }
    }

    public LibrarySubscriptionDetails setAutoRenewing(boolean paramBoolean)
    {
      this.hasAutoRenewing = true;
      this.autoRenewing_ = paramBoolean;
      return this;
    }

    public LibrarySubscriptionDetails setInitiationTimestampMsec(long paramLong)
    {
      this.hasInitiationTimestampMsec = true;
      this.initiationTimestampMsec_ = paramLong;
      return this;
    }

    public LibrarySubscriptionDetails setTrialUntilTimestampMsec(long paramLong)
    {
      this.hasTrialUntilTimestampMsec = true;
      this.trialUntilTimestampMsec_ = paramLong;
      return this;
    }

    public LibrarySubscriptionDetails setValidUntilTimestampMsec(long paramLong)
    {
      this.hasValidUntilTimestampMsec = true;
      this.validUntilTimestampMsec_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInitiationTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(1, getInitiationTimestampMsec());
      if (hasValidUntilTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(2, getValidUntilTimestampMsec());
      if (hasAutoRenewing())
        paramCodedOutputStreamMicro.writeBool(3, getAutoRenewing());
      if (hasTrialUntilTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(4, getTrialUntilTimestampMsec());
    }
  }

  public static final class LibraryUpdate extends MessageMicro
  {
    private int cachedSize = -1;
    private int corpus_ = 0;
    private boolean hasCorpus;
    private boolean hasHasMore;
    private boolean hasLibraryId;
    private boolean hasMore_ = false;
    private boolean hasServerToken;
    private boolean hasStatus;
    private String libraryId_ = "";
    private List<Library.LibraryMutation> mutation_ = Collections.emptyList();
    private ByteStringMicro serverToken_ = ByteStringMicro.EMPTY;
    private int status_ = 1;

    public LibraryUpdate addMutation(Library.LibraryMutation paramLibraryMutation)
    {
      if (paramLibraryMutation == null)
        throw new NullPointerException();
      if (this.mutation_.isEmpty())
        this.mutation_ = new ArrayList();
      this.mutation_.add(paramLibraryMutation);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCorpus()
    {
      return this.corpus_;
    }

    public boolean getHasMore()
    {
      return this.hasMore_;
    }

    public String getLibraryId()
    {
      return this.libraryId_;
    }

    public int getMutationCount()
    {
      return this.mutation_.size();
    }

    public List<Library.LibraryMutation> getMutationList()
    {
      return this.mutation_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasStatus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatus());
      if (hasCorpus())
        i += CodedOutputStreamMicro.computeInt32Size(2, getCorpus());
      if (hasServerToken())
        i += CodedOutputStreamMicro.computeBytesSize(3, getServerToken());
      Iterator localIterator = getMutationList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (Library.LibraryMutation)localIterator.next());
      if (hasHasMore())
        i += CodedOutputStreamMicro.computeBoolSize(5, getHasMore());
      if (hasLibraryId())
        i += CodedOutputStreamMicro.computeStringSize(6, getLibraryId());
      this.cachedSize = i;
      return i;
    }

    public ByteStringMicro getServerToken()
    {
      return this.serverToken_;
    }

    public int getStatus()
    {
      return this.status_;
    }

    public boolean hasCorpus()
    {
      return this.hasCorpus;
    }

    public boolean hasHasMore()
    {
      return this.hasHasMore;
    }

    public boolean hasLibraryId()
    {
      return this.hasLibraryId;
    }

    public boolean hasServerToken()
    {
      return this.hasServerToken;
    }

    public boolean hasStatus()
    {
      return this.hasStatus;
    }

    public LibraryUpdate mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setStatus(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setCorpus(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setServerToken(paramCodedInputStreamMicro.readBytes());
          break;
        case 34:
          Library.LibraryMutation localLibraryMutation = new Library.LibraryMutation();
          paramCodedInputStreamMicro.readMessage(localLibraryMutation);
          addMutation(localLibraryMutation);
          break;
        case 40:
          setHasMore(paramCodedInputStreamMicro.readBool());
          break;
        case 50:
        }
        setLibraryId(paramCodedInputStreamMicro.readString());
      }
    }

    public LibraryUpdate setCorpus(int paramInt)
    {
      this.hasCorpus = true;
      this.corpus_ = paramInt;
      return this;
    }

    public LibraryUpdate setHasMore(boolean paramBoolean)
    {
      this.hasHasMore = true;
      this.hasMore_ = paramBoolean;
      return this;
    }

    public LibraryUpdate setLibraryId(String paramString)
    {
      this.hasLibraryId = true;
      this.libraryId_ = paramString;
      return this;
    }

    public LibraryUpdate setServerToken(ByteStringMicro paramByteStringMicro)
    {
      this.hasServerToken = true;
      this.serverToken_ = paramByteStringMicro;
      return this;
    }

    public LibraryUpdate setStatus(int paramInt)
    {
      this.hasStatus = true;
      this.status_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasStatus())
        paramCodedOutputStreamMicro.writeInt32(1, getStatus());
      if (hasCorpus())
        paramCodedOutputStreamMicro.writeInt32(2, getCorpus());
      if (hasServerToken())
        paramCodedOutputStreamMicro.writeBytes(3, getServerToken());
      Iterator localIterator = getMutationList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (Library.LibraryMutation)localIterator.next());
      if (hasHasMore())
        paramCodedOutputStreamMicro.writeBool(5, getHasMore());
      if (hasLibraryId())
        paramCodedOutputStreamMicro.writeString(6, getLibraryId());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Library
 * JD-Core Version:    0.6.2
 */
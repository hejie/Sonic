package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Common
{
  public static final class Docid extends MessageMicro
  {
    private String backendDocid_ = "";
    private int backend_ = 0;
    private int cachedSize = -1;
    private boolean hasBackend;
    private boolean hasBackendDocid;
    private boolean hasType;
    private int type_ = 1;

    public int getBackend()
    {
      return this.backend_;
    }

    public String getBackendDocid()
    {
      return this.backendDocid_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBackendDocid())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getBackendDocid());
      if (hasType())
        i += CodedOutputStreamMicro.computeInt32Size(2, getType());
      if (hasBackend())
        i += CodedOutputStreamMicro.computeInt32Size(3, getBackend());
      this.cachedSize = i;
      return i;
    }

    public int getType()
    {
      return this.type_;
    }

    public boolean hasBackend()
    {
      return this.hasBackend;
    }

    public boolean hasBackendDocid()
    {
      return this.hasBackendDocid;
    }

    public boolean hasType()
    {
      return this.hasType;
    }

    public Docid mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBackendDocid(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setType(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
        }
        setBackend(paramCodedInputStreamMicro.readInt32());
      }
    }

    public Docid setBackend(int paramInt)
    {
      this.hasBackend = true;
      this.backend_ = paramInt;
      return this;
    }

    public Docid setBackendDocid(String paramString)
    {
      this.hasBackendDocid = true;
      this.backendDocid_ = paramString;
      return this;
    }

    public Docid setType(int paramInt)
    {
      this.hasType = true;
      this.type_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBackendDocid())
        paramCodedOutputStreamMicro.writeString(1, getBackendDocid());
      if (hasType())
        paramCodedOutputStreamMicro.writeInt32(2, getType());
      if (hasBackend())
        paramCodedOutputStreamMicro.writeInt32(3, getBackend());
    }
  }

  public static final class Install extends MessageMicro
  {
    private long androidId_ = 0L;
    private boolean bundled_ = false;
    private int cachedSize = -1;
    private boolean hasAndroidId;
    private boolean hasBundled;
    private boolean hasVersion;
    private int version_ = 0;

    public long getAndroidId()
    {
      return this.androidId_;
    }

    public boolean getBundled()
    {
      return this.bundled_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAndroidId())
        i = 0 + CodedOutputStreamMicro.computeFixed64Size(1, getAndroidId());
      if (hasVersion())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersion());
      if (hasBundled())
        i += CodedOutputStreamMicro.computeBoolSize(3, getBundled());
      this.cachedSize = i;
      return i;
    }

    public int getVersion()
    {
      return this.version_;
    }

    public boolean hasAndroidId()
    {
      return this.hasAndroidId;
    }

    public boolean hasBundled()
    {
      return this.hasBundled;
    }

    public boolean hasVersion()
    {
      return this.hasVersion;
    }

    public Install mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 9:
          setAndroidId(paramCodedInputStreamMicro.readFixed64());
          break;
        case 16:
          setVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
        }
        setBundled(paramCodedInputStreamMicro.readBool());
      }
    }

    public Install setAndroidId(long paramLong)
    {
      this.hasAndroidId = true;
      this.androidId_ = paramLong;
      return this;
    }

    public Install setBundled(boolean paramBoolean)
    {
      this.hasBundled = true;
      this.bundled_ = paramBoolean;
      return this;
    }

    public Install setVersion(int paramInt)
    {
      this.hasVersion = true;
      this.version_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAndroidId())
        paramCodedOutputStreamMicro.writeFixed64(1, getAndroidId());
      if (hasVersion())
        paramCodedOutputStreamMicro.writeInt32(2, getVersion());
      if (hasBundled())
        paramCodedOutputStreamMicro.writeBool(3, getBundled());
    }
  }

  public static final class Offer extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean checkoutFlowRequired_ = false;
    private List<Offer> convertedPrice_ = Collections.emptyList();
    private String currencyCode_ = "";
    private String formattedAmount_ = "";
    private String formattedDescription_ = "";
    private String formattedFullAmount_ = "";
    private String formattedName_ = "";
    private long fullPriceMicros_ = 0L;
    private boolean hasCheckoutFlowRequired;
    private boolean hasCurrencyCode;
    private boolean hasFormattedAmount;
    private boolean hasFormattedDescription;
    private boolean hasFormattedFullAmount;
    private boolean hasFormattedName;
    private boolean hasFullPriceMicros;
    private boolean hasMicros;
    private boolean hasOfferType;
    private boolean hasOnSaleDate;
    private boolean hasPreorder;
    private boolean hasRentalTerms;
    private boolean hasSubscriptionTerms;
    private long micros_ = 0L;
    private int offerType_ = 1;
    private long onSaleDate_ = 0L;
    private boolean preorder_ = false;
    private List<String> promotionLabel_ = Collections.emptyList();
    private Common.RentalTerms rentalTerms_ = null;
    private Common.SubscriptionTerms subscriptionTerms_ = null;

    public Offer addConvertedPrice(Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      if (this.convertedPrice_.isEmpty())
        this.convertedPrice_ = new ArrayList();
      this.convertedPrice_.add(paramOffer);
      return this;
    }

    public Offer addPromotionLabel(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.promotionLabel_.isEmpty())
        this.promotionLabel_ = new ArrayList();
      this.promotionLabel_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutFlowRequired()
    {
      return this.checkoutFlowRequired_;
    }

    public List<Offer> getConvertedPriceList()
    {
      return this.convertedPrice_;
    }

    public String getCurrencyCode()
    {
      return this.currencyCode_;
    }

    public String getFormattedAmount()
    {
      return this.formattedAmount_;
    }

    public String getFormattedDescription()
    {
      return this.formattedDescription_;
    }

    public String getFormattedFullAmount()
    {
      return this.formattedFullAmount_;
    }

    public String getFormattedName()
    {
      return this.formattedName_;
    }

    public long getFullPriceMicros()
    {
      return this.fullPriceMicros_;
    }

    public long getMicros()
    {
      return this.micros_;
    }

    public int getOfferType()
    {
      return this.offerType_;
    }

    public long getOnSaleDate()
    {
      return this.onSaleDate_;
    }

    public boolean getPreorder()
    {
      return this.preorder_;
    }

    public List<String> getPromotionLabelList()
    {
      return this.promotionLabel_;
    }

    public Common.RentalTerms getRentalTerms()
    {
      return this.rentalTerms_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasMicros())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getMicros());
      if (hasCurrencyCode())
        i += CodedOutputStreamMicro.computeStringSize(2, getCurrencyCode());
      if (hasFormattedAmount())
        i += CodedOutputStreamMicro.computeStringSize(3, getFormattedAmount());
      Iterator localIterator1 = getConvertedPriceList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (Offer)localIterator1.next());
      if (hasCheckoutFlowRequired())
        i += CodedOutputStreamMicro.computeBoolSize(5, getCheckoutFlowRequired());
      if (hasFullPriceMicros())
        i += CodedOutputStreamMicro.computeInt64Size(6, getFullPriceMicros());
      if (hasFormattedFullAmount())
        i += CodedOutputStreamMicro.computeStringSize(7, getFormattedFullAmount());
      if (hasOfferType())
        i += CodedOutputStreamMicro.computeInt32Size(8, getOfferType());
      if (hasRentalTerms())
        i += CodedOutputStreamMicro.computeMessageSize(9, getRentalTerms());
      if (hasOnSaleDate())
        i += CodedOutputStreamMicro.computeInt64Size(10, getOnSaleDate());
      int j = 0;
      Iterator localIterator2 = getPromotionLabelList().iterator();
      while (localIterator2.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int k = i + j + 1 * getPromotionLabelList().size();
      if (hasSubscriptionTerms())
        k += CodedOutputStreamMicro.computeMessageSize(12, getSubscriptionTerms());
      if (hasFormattedName())
        k += CodedOutputStreamMicro.computeStringSize(13, getFormattedName());
      if (hasFormattedDescription())
        k += CodedOutputStreamMicro.computeStringSize(14, getFormattedDescription());
      if (hasPreorder())
        k += CodedOutputStreamMicro.computeBoolSize(15, getPreorder());
      this.cachedSize = k;
      return k;
    }

    public Common.SubscriptionTerms getSubscriptionTerms()
    {
      return this.subscriptionTerms_;
    }

    public boolean hasCheckoutFlowRequired()
    {
      return this.hasCheckoutFlowRequired;
    }

    public boolean hasCurrencyCode()
    {
      return this.hasCurrencyCode;
    }

    public boolean hasFormattedAmount()
    {
      return this.hasFormattedAmount;
    }

    public boolean hasFormattedDescription()
    {
      return this.hasFormattedDescription;
    }

    public boolean hasFormattedFullAmount()
    {
      return this.hasFormattedFullAmount;
    }

    public boolean hasFormattedName()
    {
      return this.hasFormattedName;
    }

    public boolean hasFullPriceMicros()
    {
      return this.hasFullPriceMicros;
    }

    public boolean hasMicros()
    {
      return this.hasMicros;
    }

    public boolean hasOfferType()
    {
      return this.hasOfferType;
    }

    public boolean hasOnSaleDate()
    {
      return this.hasOnSaleDate;
    }

    public boolean hasPreorder()
    {
      return this.hasPreorder;
    }

    public boolean hasRentalTerms()
    {
      return this.hasRentalTerms;
    }

    public boolean hasSubscriptionTerms()
    {
      return this.hasSubscriptionTerms;
    }

    public Offer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setMicros(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
          setCurrencyCode(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setFormattedAmount(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          Offer localOffer = new Offer();
          paramCodedInputStreamMicro.readMessage(localOffer);
          addConvertedPrice(localOffer);
          break;
        case 40:
          setCheckoutFlowRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 48:
          setFullPriceMicros(paramCodedInputStreamMicro.readInt64());
          break;
        case 58:
          setFormattedFullAmount(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setOfferType(paramCodedInputStreamMicro.readInt32());
          break;
        case 74:
          Common.RentalTerms localRentalTerms = new Common.RentalTerms();
          paramCodedInputStreamMicro.readMessage(localRentalTerms);
          setRentalTerms(localRentalTerms);
          break;
        case 80:
          setOnSaleDate(paramCodedInputStreamMicro.readInt64());
          break;
        case 90:
          addPromotionLabel(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          Common.SubscriptionTerms localSubscriptionTerms = new Common.SubscriptionTerms();
          paramCodedInputStreamMicro.readMessage(localSubscriptionTerms);
          setSubscriptionTerms(localSubscriptionTerms);
          break;
        case 106:
          setFormattedName(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          setFormattedDescription(paramCodedInputStreamMicro.readString());
          break;
        case 120:
        }
        setPreorder(paramCodedInputStreamMicro.readBool());
      }
    }

    public Offer setCheckoutFlowRequired(boolean paramBoolean)
    {
      this.hasCheckoutFlowRequired = true;
      this.checkoutFlowRequired_ = paramBoolean;
      return this;
    }

    public Offer setCurrencyCode(String paramString)
    {
      this.hasCurrencyCode = true;
      this.currencyCode_ = paramString;
      return this;
    }

    public Offer setFormattedAmount(String paramString)
    {
      this.hasFormattedAmount = true;
      this.formattedAmount_ = paramString;
      return this;
    }

    public Offer setFormattedDescription(String paramString)
    {
      this.hasFormattedDescription = true;
      this.formattedDescription_ = paramString;
      return this;
    }

    public Offer setFormattedFullAmount(String paramString)
    {
      this.hasFormattedFullAmount = true;
      this.formattedFullAmount_ = paramString;
      return this;
    }

    public Offer setFormattedName(String paramString)
    {
      this.hasFormattedName = true;
      this.formattedName_ = paramString;
      return this;
    }

    public Offer setFullPriceMicros(long paramLong)
    {
      this.hasFullPriceMicros = true;
      this.fullPriceMicros_ = paramLong;
      return this;
    }

    public Offer setMicros(long paramLong)
    {
      this.hasMicros = true;
      this.micros_ = paramLong;
      return this;
    }

    public Offer setOfferType(int paramInt)
    {
      this.hasOfferType = true;
      this.offerType_ = paramInt;
      return this;
    }

    public Offer setOnSaleDate(long paramLong)
    {
      this.hasOnSaleDate = true;
      this.onSaleDate_ = paramLong;
      return this;
    }

    public Offer setPreorder(boolean paramBoolean)
    {
      this.hasPreorder = true;
      this.preorder_ = paramBoolean;
      return this;
    }

    public Offer setRentalTerms(Common.RentalTerms paramRentalTerms)
    {
      if (paramRentalTerms == null)
        throw new NullPointerException();
      this.hasRentalTerms = true;
      this.rentalTerms_ = paramRentalTerms;
      return this;
    }

    public Offer setSubscriptionTerms(Common.SubscriptionTerms paramSubscriptionTerms)
    {
      if (paramSubscriptionTerms == null)
        throw new NullPointerException();
      this.hasSubscriptionTerms = true;
      this.subscriptionTerms_ = paramSubscriptionTerms;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasMicros())
        paramCodedOutputStreamMicro.writeInt64(1, getMicros());
      if (hasCurrencyCode())
        paramCodedOutputStreamMicro.writeString(2, getCurrencyCode());
      if (hasFormattedAmount())
        paramCodedOutputStreamMicro.writeString(3, getFormattedAmount());
      Iterator localIterator1 = getConvertedPriceList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (Offer)localIterator1.next());
      if (hasCheckoutFlowRequired())
        paramCodedOutputStreamMicro.writeBool(5, getCheckoutFlowRequired());
      if (hasFullPriceMicros())
        paramCodedOutputStreamMicro.writeInt64(6, getFullPriceMicros());
      if (hasFormattedFullAmount())
        paramCodedOutputStreamMicro.writeString(7, getFormattedFullAmount());
      if (hasOfferType())
        paramCodedOutputStreamMicro.writeInt32(8, getOfferType());
      if (hasRentalTerms())
        paramCodedOutputStreamMicro.writeMessage(9, getRentalTerms());
      if (hasOnSaleDate())
        paramCodedOutputStreamMicro.writeInt64(10, getOnSaleDate());
      Iterator localIterator2 = getPromotionLabelList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(11, (String)localIterator2.next());
      if (hasSubscriptionTerms())
        paramCodedOutputStreamMicro.writeMessage(12, getSubscriptionTerms());
      if (hasFormattedName())
        paramCodedOutputStreamMicro.writeString(13, getFormattedName());
      if (hasFormattedDescription())
        paramCodedOutputStreamMicro.writeString(14, getFormattedDescription());
      if (hasPreorder())
        paramCodedOutputStreamMicro.writeBool(15, getPreorder());
    }
  }

  public static final class OwnershipInfo extends MessageMicro
  {
    private boolean autoRenewing_ = false;
    private int cachedSize = -1;
    private Common.SignedData developerPurchaseInfo_ = null;
    private boolean hasAutoRenewing;
    private boolean hasDeveloperPurchaseInfo;
    private boolean hasHidden;
    private boolean hasInitiationTimestampMsec;
    private boolean hasPostDeliveryRefundWindowMsec;
    private boolean hasPreordered;
    private boolean hasRefundTimeoutTimestampMsec;
    private boolean hasValidUntilTimestampMsec;
    private boolean hidden_ = false;
    private long initiationTimestampMsec_ = 0L;
    private long postDeliveryRefundWindowMsec_ = 0L;
    private boolean preordered_ = false;
    private long refundTimeoutTimestampMsec_ = 0L;
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

    public Common.SignedData getDeveloperPurchaseInfo()
    {
      return this.developerPurchaseInfo_;
    }

    public boolean getHidden()
    {
      return this.hidden_;
    }

    public long getInitiationTimestampMsec()
    {
      return this.initiationTimestampMsec_;
    }

    public long getPostDeliveryRefundWindowMsec()
    {
      return this.postDeliveryRefundWindowMsec_;
    }

    public boolean getPreordered()
    {
      return this.preordered_;
    }

    public long getRefundTimeoutTimestampMsec()
    {
      return this.refundTimeoutTimestampMsec_;
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
      if (hasRefundTimeoutTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(4, getRefundTimeoutTimestampMsec());
      if (hasPostDeliveryRefundWindowMsec())
        i += CodedOutputStreamMicro.computeInt64Size(5, getPostDeliveryRefundWindowMsec());
      if (hasDeveloperPurchaseInfo())
        i += CodedOutputStreamMicro.computeMessageSize(6, getDeveloperPurchaseInfo());
      if (hasPreordered())
        i += CodedOutputStreamMicro.computeBoolSize(7, getPreordered());
      if (hasHidden())
        i += CodedOutputStreamMicro.computeBoolSize(8, getHidden());
      this.cachedSize = i;
      return i;
    }

    public long getValidUntilTimestampMsec()
    {
      return this.validUntilTimestampMsec_;
    }

    public boolean hasAutoRenewing()
    {
      return this.hasAutoRenewing;
    }

    public boolean hasDeveloperPurchaseInfo()
    {
      return this.hasDeveloperPurchaseInfo;
    }

    public boolean hasHidden()
    {
      return this.hasHidden;
    }

    public boolean hasInitiationTimestampMsec()
    {
      return this.hasInitiationTimestampMsec;
    }

    public boolean hasPostDeliveryRefundWindowMsec()
    {
      return this.hasPostDeliveryRefundWindowMsec;
    }

    public boolean hasPreordered()
    {
      return this.hasPreordered;
    }

    public boolean hasRefundTimeoutTimestampMsec()
    {
      return this.hasRefundTimeoutTimestampMsec;
    }

    public boolean hasValidUntilTimestampMsec()
    {
      return this.hasValidUntilTimestampMsec;
    }

    public OwnershipInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setRefundTimeoutTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 40:
          setPostDeliveryRefundWindowMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 50:
          Common.SignedData localSignedData = new Common.SignedData();
          paramCodedInputStreamMicro.readMessage(localSignedData);
          setDeveloperPurchaseInfo(localSignedData);
          break;
        case 56:
          setPreordered(paramCodedInputStreamMicro.readBool());
          break;
        case 64:
        }
        setHidden(paramCodedInputStreamMicro.readBool());
      }
    }

    public OwnershipInfo setAutoRenewing(boolean paramBoolean)
    {
      this.hasAutoRenewing = true;
      this.autoRenewing_ = paramBoolean;
      return this;
    }

    public OwnershipInfo setDeveloperPurchaseInfo(Common.SignedData paramSignedData)
    {
      if (paramSignedData == null)
        throw new NullPointerException();
      this.hasDeveloperPurchaseInfo = true;
      this.developerPurchaseInfo_ = paramSignedData;
      return this;
    }

    public OwnershipInfo setHidden(boolean paramBoolean)
    {
      this.hasHidden = true;
      this.hidden_ = paramBoolean;
      return this;
    }

    public OwnershipInfo setInitiationTimestampMsec(long paramLong)
    {
      this.hasInitiationTimestampMsec = true;
      this.initiationTimestampMsec_ = paramLong;
      return this;
    }

    public OwnershipInfo setPostDeliveryRefundWindowMsec(long paramLong)
    {
      this.hasPostDeliveryRefundWindowMsec = true;
      this.postDeliveryRefundWindowMsec_ = paramLong;
      return this;
    }

    public OwnershipInfo setPreordered(boolean paramBoolean)
    {
      this.hasPreordered = true;
      this.preordered_ = paramBoolean;
      return this;
    }

    public OwnershipInfo setRefundTimeoutTimestampMsec(long paramLong)
    {
      this.hasRefundTimeoutTimestampMsec = true;
      this.refundTimeoutTimestampMsec_ = paramLong;
      return this;
    }

    public OwnershipInfo setValidUntilTimestampMsec(long paramLong)
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
      if (hasRefundTimeoutTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(4, getRefundTimeoutTimestampMsec());
      if (hasPostDeliveryRefundWindowMsec())
        paramCodedOutputStreamMicro.writeInt64(5, getPostDeliveryRefundWindowMsec());
      if (hasDeveloperPurchaseInfo())
        paramCodedOutputStreamMicro.writeMessage(6, getDeveloperPurchaseInfo());
      if (hasPreordered())
        paramCodedOutputStreamMicro.writeBool(7, getPreordered());
      if (hasHidden())
        paramCodedOutputStreamMicro.writeBool(8, getHidden());
    }
  }

  public static final class RentalTerms extends MessageMicro
  {
    private int activatePeriodSeconds_ = 0;
    private int cachedSize = -1;
    private int grantPeriodSeconds_ = 0;
    private boolean hasActivatePeriodSeconds;
    private boolean hasGrantPeriodSeconds;

    public int getActivatePeriodSeconds()
    {
      return this.activatePeriodSeconds_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getGrantPeriodSeconds()
    {
      return this.grantPeriodSeconds_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGrantPeriodSeconds())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getGrantPeriodSeconds());
      if (hasActivatePeriodSeconds())
        i += CodedOutputStreamMicro.computeInt32Size(2, getActivatePeriodSeconds());
      this.cachedSize = i;
      return i;
    }

    public boolean hasActivatePeriodSeconds()
    {
      return this.hasActivatePeriodSeconds;
    }

    public boolean hasGrantPeriodSeconds()
    {
      return this.hasGrantPeriodSeconds;
    }

    public RentalTerms mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGrantPeriodSeconds(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
        }
        setActivatePeriodSeconds(paramCodedInputStreamMicro.readInt32());
      }
    }

    public RentalTerms setActivatePeriodSeconds(int paramInt)
    {
      this.hasActivatePeriodSeconds = true;
      this.activatePeriodSeconds_ = paramInt;
      return this;
    }

    public RentalTerms setGrantPeriodSeconds(int paramInt)
    {
      this.hasGrantPeriodSeconds = true;
      this.grantPeriodSeconds_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGrantPeriodSeconds())
        paramCodedOutputStreamMicro.writeInt32(1, getGrantPeriodSeconds());
      if (hasActivatePeriodSeconds())
        paramCodedOutputStreamMicro.writeInt32(2, getActivatePeriodSeconds());
    }
  }

  public static final class SignedData extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSignature;
    private boolean hasSignedData;
    private String signature_ = "";
    private String signedData_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignedData())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSignedData());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeStringSize(2, getSignature());
      this.cachedSize = i;
      return i;
    }

    public String getSignature()
    {
      return this.signature_;
    }

    public String getSignedData()
    {
      return this.signedData_;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public boolean hasSignedData()
    {
      return this.hasSignedData;
    }

    public SignedData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSignedData(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setSignature(paramCodedInputStreamMicro.readString());
      }
    }

    public SignedData setSignature(String paramString)
    {
      this.hasSignature = true;
      this.signature_ = paramString;
      return this;
    }

    public SignedData setSignedData(String paramString)
    {
      this.hasSignedData = true;
      this.signedData_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignedData())
        paramCodedOutputStreamMicro.writeString(1, getSignedData());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeString(2, getSignature());
    }
  }

  public static final class SubscriptionTerms extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasRecurringPeriod;
    private boolean hasTrialPeriod;
    private Common.TimePeriod recurringPeriod_ = null;
    private Common.TimePeriod trialPeriod_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Common.TimePeriod getRecurringPeriod()
    {
      return this.recurringPeriod_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRecurringPeriod())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getRecurringPeriod());
      if (hasTrialPeriod())
        i += CodedOutputStreamMicro.computeMessageSize(2, getTrialPeriod());
      this.cachedSize = i;
      return i;
    }

    public Common.TimePeriod getTrialPeriod()
    {
      return this.trialPeriod_;
    }

    public boolean hasRecurringPeriod()
    {
      return this.hasRecurringPeriod;
    }

    public boolean hasTrialPeriod()
    {
      return this.hasTrialPeriod;
    }

    public SubscriptionTerms mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Common.TimePeriod localTimePeriod2 = new Common.TimePeriod();
          paramCodedInputStreamMicro.readMessage(localTimePeriod2);
          setRecurringPeriod(localTimePeriod2);
          break;
        case 18:
        }
        Common.TimePeriod localTimePeriod1 = new Common.TimePeriod();
        paramCodedInputStreamMicro.readMessage(localTimePeriod1);
        setTrialPeriod(localTimePeriod1);
      }
    }

    public SubscriptionTerms setRecurringPeriod(Common.TimePeriod paramTimePeriod)
    {
      if (paramTimePeriod == null)
        throw new NullPointerException();
      this.hasRecurringPeriod = true;
      this.recurringPeriod_ = paramTimePeriod;
      return this;
    }

    public SubscriptionTerms setTrialPeriod(Common.TimePeriod paramTimePeriod)
    {
      if (paramTimePeriod == null)
        throw new NullPointerException();
      this.hasTrialPeriod = true;
      this.trialPeriod_ = paramTimePeriod;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRecurringPeriod())
        paramCodedOutputStreamMicro.writeMessage(1, getRecurringPeriod());
      if (hasTrialPeriod())
        paramCodedOutputStreamMicro.writeMessage(2, getTrialPeriod());
    }
  }

  public static final class TimePeriod extends MessageMicro
  {
    private int cachedSize = -1;
    private int count_ = 0;
    private boolean hasCount;
    private boolean hasUnit;
    private int unit_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCount()
    {
      return this.count_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUnit())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getUnit());
      if (hasCount())
        i += CodedOutputStreamMicro.computeInt32Size(2, getCount());
      this.cachedSize = i;
      return i;
    }

    public int getUnit()
    {
      return this.unit_;
    }

    public boolean hasCount()
    {
      return this.hasCount;
    }

    public boolean hasUnit()
    {
      return this.hasUnit;
    }

    public TimePeriod mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUnit(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
        }
        setCount(paramCodedInputStreamMicro.readInt32());
      }
    }

    public TimePeriod setCount(int paramInt)
    {
      this.hasCount = true;
      this.count_ = paramInt;
      return this;
    }

    public TimePeriod setUnit(int paramInt)
    {
      this.hasUnit = true;
      this.unit_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUnit())
        paramCodedOutputStreamMicro.writeInt32(1, getUnit());
      if (hasCount())
        paramCodedOutputStreamMicro.writeInt32(2, getCount());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Common
 * JD-Core Version:    0.6.2
 */
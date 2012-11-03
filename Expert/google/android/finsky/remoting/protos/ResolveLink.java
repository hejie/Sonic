package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class ResolveLink
{
  public static final class DirectPurchase extends MessageMicro
  {
    private int cachedSize = -1;
    private String detailsUrl_ = "";
    private boolean hasDetailsUrl;
    private boolean hasOfferType;
    private boolean hasParentDocid;
    private boolean hasPurchaseDocid;
    private int offerType_ = 1;
    private String parentDocid_ = "";
    private String purchaseDocid_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public int getOfferType()
    {
      return this.offerType_;
    }

    public String getParentDocid()
    {
      return this.parentDocid_;
    }

    public String getPurchaseDocid()
    {
      return this.purchaseDocid_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDetailsUrl());
      if (hasPurchaseDocid())
        i += CodedOutputStreamMicro.computeStringSize(2, getPurchaseDocid());
      if (hasParentDocid())
        i += CodedOutputStreamMicro.computeStringSize(3, getParentDocid());
      if (hasOfferType())
        i += CodedOutputStreamMicro.computeInt32Size(4, getOfferType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasOfferType()
    {
      return this.hasOfferType;
    }

    public boolean hasParentDocid()
    {
      return this.hasParentDocid;
    }

    public boolean hasPurchaseDocid()
    {
      return this.hasPurchaseDocid;
    }

    public DirectPurchase mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setPurchaseDocid(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setParentDocid(paramCodedInputStreamMicro.readString());
          break;
        case 32:
        }
        setOfferType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public DirectPurchase setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public DirectPurchase setOfferType(int paramInt)
    {
      this.hasOfferType = true;
      this.offerType_ = paramInt;
      return this;
    }

    public DirectPurchase setParentDocid(String paramString)
    {
      this.hasParentDocid = true;
      this.parentDocid_ = paramString;
      return this;
    }

    public DirectPurchase setPurchaseDocid(String paramString)
    {
      this.hasPurchaseDocid = true;
      this.purchaseDocid_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getDetailsUrl());
      if (hasPurchaseDocid())
        paramCodedOutputStreamMicro.writeString(2, getPurchaseDocid());
      if (hasParentDocid())
        paramCodedOutputStreamMicro.writeString(3, getParentDocid());
      if (hasOfferType())
        paramCodedOutputStreamMicro.writeInt32(4, getOfferType());
    }
  }

  public static final class RedeemGiftCard extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public RedeemGiftCard mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    {
    }
  }

  public static final class ResolveLinkResponse extends MessageMicro
  {
    private String browseUrl_ = "";
    private int cachedSize = -1;
    private String detailsUrl_ = "";
    private ResolveLink.DirectPurchase directPurchase_ = null;
    private boolean hasBrowseUrl;
    private boolean hasDetailsUrl;
    private boolean hasDirectPurchase;
    private boolean hasHomeUrl;
    private boolean hasRedeemGiftCard;
    private boolean hasSearchUrl;
    private String homeUrl_ = "";
    private ResolveLink.RedeemGiftCard redeemGiftCard_ = null;
    private String searchUrl_ = "";

    public String getBrowseUrl()
    {
      return this.browseUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public ResolveLink.DirectPurchase getDirectPurchase()
    {
      return this.directPurchase_;
    }

    public String getHomeUrl()
    {
      return this.homeUrl_;
    }

    public ResolveLink.RedeemGiftCard getRedeemGiftCard()
    {
      return this.redeemGiftCard_;
    }

    public String getSearchUrl()
    {
      return this.searchUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDetailsUrl());
      if (hasBrowseUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getBrowseUrl());
      if (hasSearchUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getSearchUrl());
      if (hasDirectPurchase())
        i += CodedOutputStreamMicro.computeMessageSize(4, getDirectPurchase());
      if (hasHomeUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getHomeUrl());
      if (hasRedeemGiftCard())
        i += CodedOutputStreamMicro.computeMessageSize(6, getRedeemGiftCard());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBrowseUrl()
    {
      return this.hasBrowseUrl;
    }

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasDirectPurchase()
    {
      return this.hasDirectPurchase;
    }

    public boolean hasHomeUrl()
    {
      return this.hasHomeUrl;
    }

    public boolean hasRedeemGiftCard()
    {
      return this.hasRedeemGiftCard;
    }

    public boolean hasSearchUrl()
    {
      return this.hasSearchUrl;
    }

    public ResolveLinkResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setBrowseUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setSearchUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          ResolveLink.DirectPurchase localDirectPurchase = new ResolveLink.DirectPurchase();
          paramCodedInputStreamMicro.readMessage(localDirectPurchase);
          setDirectPurchase(localDirectPurchase);
          break;
        case 42:
          setHomeUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
        }
        ResolveLink.RedeemGiftCard localRedeemGiftCard = new ResolveLink.RedeemGiftCard();
        paramCodedInputStreamMicro.readMessage(localRedeemGiftCard);
        setRedeemGiftCard(localRedeemGiftCard);
      }
    }

    public ResolveLinkResponse setBrowseUrl(String paramString)
    {
      this.hasBrowseUrl = true;
      this.browseUrl_ = paramString;
      return this;
    }

    public ResolveLinkResponse setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public ResolveLinkResponse setDirectPurchase(ResolveLink.DirectPurchase paramDirectPurchase)
    {
      if (paramDirectPurchase == null)
        throw new NullPointerException();
      this.hasDirectPurchase = true;
      this.directPurchase_ = paramDirectPurchase;
      return this;
    }

    public ResolveLinkResponse setHomeUrl(String paramString)
    {
      this.hasHomeUrl = true;
      this.homeUrl_ = paramString;
      return this;
    }

    public ResolveLinkResponse setRedeemGiftCard(ResolveLink.RedeemGiftCard paramRedeemGiftCard)
    {
      if (paramRedeemGiftCard == null)
        throw new NullPointerException();
      this.hasRedeemGiftCard = true;
      this.redeemGiftCard_ = paramRedeemGiftCard;
      return this;
    }

    public ResolveLinkResponse setSearchUrl(String paramString)
    {
      this.hasSearchUrl = true;
      this.searchUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getDetailsUrl());
      if (hasBrowseUrl())
        paramCodedOutputStreamMicro.writeString(2, getBrowseUrl());
      if (hasSearchUrl())
        paramCodedOutputStreamMicro.writeString(3, getSearchUrl());
      if (hasDirectPurchase())
        paramCodedOutputStreamMicro.writeMessage(4, getDirectPurchase());
      if (hasHomeUrl())
        paramCodedOutputStreamMicro.writeString(5, getHomeUrl());
      if (hasRedeemGiftCard())
        paramCodedOutputStreamMicro.writeMessage(6, getRedeemGiftCard());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.ResolveLink
 * JD-Core Version:    0.6.2
 */
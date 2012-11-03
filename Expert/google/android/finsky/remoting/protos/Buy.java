package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Buy
{
  public static final class BuyResponse extends MessageMicro
  {
    private String addInstrumentPromptHtml_ = "";
    private String baseCheckoutUrl_ = "";
    private int cachedSize = -1;
    private ChallengeProtos.Challenge challenge_ = null;
    private CheckoutInfo checkoutInfo_ = null;
    private String checkoutServiceId_ = "";
    private boolean checkoutTokenRequired_ = false;
    private String confirmButtonText_ = "";
    private String continueViaUrl_ = "";
    private boolean hasAddInstrumentPromptHtml;
    private boolean hasBaseCheckoutUrl;
    private boolean hasChallenge;
    private boolean hasCheckoutInfo;
    private boolean hasCheckoutServiceId;
    private boolean hasCheckoutTokenRequired;
    private boolean hasConfirmButtonText;
    private boolean hasContinueViaUrl;
    private boolean hasIabPermissionError;
    private boolean hasPurchaseCookie;
    private boolean hasPurchaseResponse;
    private boolean hasPurchaseStatusResponse;
    private boolean hasPurchaseStatusUrl;
    private int iabPermissionError_ = 0;
    private String purchaseCookie_ = "";
    private Buy.PurchaseNotificationResponse purchaseResponse_ = null;
    private Buy.PurchaseStatusResponse purchaseStatusResponse_ = null;
    private String purchaseStatusUrl_ = "";
    private List<String> tosCheckboxHtml_ = Collections.emptyList();

    public BuyResponse addTosCheckboxHtml(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.tosCheckboxHtml_.isEmpty())
        this.tosCheckboxHtml_ = new ArrayList();
      this.tosCheckboxHtml_.add(paramString);
      return this;
    }

    public String getAddInstrumentPromptHtml()
    {
      return this.addInstrumentPromptHtml_;
    }

    public String getBaseCheckoutUrl()
    {
      return this.baseCheckoutUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ChallengeProtos.Challenge getChallenge()
    {
      return this.challenge_;
    }

    public CheckoutInfo getCheckoutInfo()
    {
      return this.checkoutInfo_;
    }

    public String getCheckoutServiceId()
    {
      return this.checkoutServiceId_;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public String getConfirmButtonText()
    {
      return this.confirmButtonText_;
    }

    public String getContinueViaUrl()
    {
      return this.continueViaUrl_;
    }

    public int getIabPermissionError()
    {
      return this.iabPermissionError_;
    }

    public String getPurchaseCookie()
    {
      return this.purchaseCookie_;
    }

    public Buy.PurchaseNotificationResponse getPurchaseResponse()
    {
      return this.purchaseResponse_;
    }

    public Buy.PurchaseStatusResponse getPurchaseStatusResponse()
    {
      return this.purchaseStatusResponse_;
    }

    public String getPurchaseStatusUrl()
    {
      return this.purchaseStatusUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPurchaseResponse())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getPurchaseResponse());
      if (hasCheckoutInfo())
        i += CodedOutputStreamMicro.computeGroupSize(2, getCheckoutInfo());
      if (hasContinueViaUrl())
        i += CodedOutputStreamMicro.computeStringSize(8, getContinueViaUrl());
      if (hasPurchaseStatusUrl())
        i += CodedOutputStreamMicro.computeStringSize(9, getPurchaseStatusUrl());
      if (hasCheckoutServiceId())
        i += CodedOutputStreamMicro.computeStringSize(12, getCheckoutServiceId());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(13, getCheckoutTokenRequired());
      if (hasBaseCheckoutUrl())
        i += CodedOutputStreamMicro.computeStringSize(14, getBaseCheckoutUrl());
      int j = 0;
      Iterator localIterator = getTosCheckboxHtmlList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 2 * getTosCheckboxHtmlList().size();
      if (hasIabPermissionError())
        k += CodedOutputStreamMicro.computeInt32Size(38, getIabPermissionError());
      if (hasPurchaseStatusResponse())
        k += CodedOutputStreamMicro.computeMessageSize(39, getPurchaseStatusResponse());
      if (hasPurchaseCookie())
        k += CodedOutputStreamMicro.computeStringSize(46, getPurchaseCookie());
      if (hasChallenge())
        k += CodedOutputStreamMicro.computeMessageSize(49, getChallenge());
      if (hasAddInstrumentPromptHtml())
        k += CodedOutputStreamMicro.computeStringSize(50, getAddInstrumentPromptHtml());
      if (hasConfirmButtonText())
        k += CodedOutputStreamMicro.computeStringSize(51, getConfirmButtonText());
      this.cachedSize = k;
      return k;
    }

    public List<String> getTosCheckboxHtmlList()
    {
      return this.tosCheckboxHtml_;
    }

    public boolean hasAddInstrumentPromptHtml()
    {
      return this.hasAddInstrumentPromptHtml;
    }

    public boolean hasBaseCheckoutUrl()
    {
      return this.hasBaseCheckoutUrl;
    }

    public boolean hasChallenge()
    {
      return this.hasChallenge;
    }

    public boolean hasCheckoutInfo()
    {
      return this.hasCheckoutInfo;
    }

    public boolean hasCheckoutServiceId()
    {
      return this.hasCheckoutServiceId;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public boolean hasConfirmButtonText()
    {
      return this.hasConfirmButtonText;
    }

    public boolean hasContinueViaUrl()
    {
      return this.hasContinueViaUrl;
    }

    public boolean hasIabPermissionError()
    {
      return this.hasIabPermissionError;
    }

    public boolean hasPurchaseCookie()
    {
      return this.hasPurchaseCookie;
    }

    public boolean hasPurchaseResponse()
    {
      return this.hasPurchaseResponse;
    }

    public boolean hasPurchaseStatusResponse()
    {
      return this.hasPurchaseStatusResponse;
    }

    public boolean hasPurchaseStatusUrl()
    {
      return this.hasPurchaseStatusUrl;
    }

    public BuyResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Buy.PurchaseNotificationResponse localPurchaseNotificationResponse = new Buy.PurchaseNotificationResponse();
          paramCodedInputStreamMicro.readMessage(localPurchaseNotificationResponse);
          setPurchaseResponse(localPurchaseNotificationResponse);
          break;
        case 19:
          CheckoutInfo localCheckoutInfo = new CheckoutInfo();
          paramCodedInputStreamMicro.readGroup(localCheckoutInfo, 2);
          setCheckoutInfo(localCheckoutInfo);
          break;
        case 66:
          setContinueViaUrl(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setPurchaseStatusUrl(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setCheckoutServiceId(paramCodedInputStreamMicro.readString());
          break;
        case 104:
          setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 114:
          setBaseCheckoutUrl(paramCodedInputStreamMicro.readString());
          break;
        case 298:
          addTosCheckboxHtml(paramCodedInputStreamMicro.readString());
          break;
        case 304:
          setIabPermissionError(paramCodedInputStreamMicro.readInt32());
          break;
        case 314:
          Buy.PurchaseStatusResponse localPurchaseStatusResponse = new Buy.PurchaseStatusResponse();
          paramCodedInputStreamMicro.readMessage(localPurchaseStatusResponse);
          setPurchaseStatusResponse(localPurchaseStatusResponse);
          break;
        case 370:
          setPurchaseCookie(paramCodedInputStreamMicro.readString());
          break;
        case 394:
          ChallengeProtos.Challenge localChallenge = new ChallengeProtos.Challenge();
          paramCodedInputStreamMicro.readMessage(localChallenge);
          setChallenge(localChallenge);
          break;
        case 402:
          setAddInstrumentPromptHtml(paramCodedInputStreamMicro.readString());
          break;
        case 410:
        }
        setConfirmButtonText(paramCodedInputStreamMicro.readString());
      }
    }

    public BuyResponse setAddInstrumentPromptHtml(String paramString)
    {
      this.hasAddInstrumentPromptHtml = true;
      this.addInstrumentPromptHtml_ = paramString;
      return this;
    }

    public BuyResponse setBaseCheckoutUrl(String paramString)
    {
      this.hasBaseCheckoutUrl = true;
      this.baseCheckoutUrl_ = paramString;
      return this;
    }

    public BuyResponse setChallenge(ChallengeProtos.Challenge paramChallenge)
    {
      if (paramChallenge == null)
        throw new NullPointerException();
      this.hasChallenge = true;
      this.challenge_ = paramChallenge;
      return this;
    }

    public BuyResponse setCheckoutInfo(CheckoutInfo paramCheckoutInfo)
    {
      if (paramCheckoutInfo == null)
        throw new NullPointerException();
      this.hasCheckoutInfo = true;
      this.checkoutInfo_ = paramCheckoutInfo;
      return this;
    }

    public BuyResponse setCheckoutServiceId(String paramString)
    {
      this.hasCheckoutServiceId = true;
      this.checkoutServiceId_ = paramString;
      return this;
    }

    public BuyResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public BuyResponse setConfirmButtonText(String paramString)
    {
      this.hasConfirmButtonText = true;
      this.confirmButtonText_ = paramString;
      return this;
    }

    public BuyResponse setContinueViaUrl(String paramString)
    {
      this.hasContinueViaUrl = true;
      this.continueViaUrl_ = paramString;
      return this;
    }

    public BuyResponse setIabPermissionError(int paramInt)
    {
      this.hasIabPermissionError = true;
      this.iabPermissionError_ = paramInt;
      return this;
    }

    public BuyResponse setPurchaseCookie(String paramString)
    {
      this.hasPurchaseCookie = true;
      this.purchaseCookie_ = paramString;
      return this;
    }

    public BuyResponse setPurchaseResponse(Buy.PurchaseNotificationResponse paramPurchaseNotificationResponse)
    {
      if (paramPurchaseNotificationResponse == null)
        throw new NullPointerException();
      this.hasPurchaseResponse = true;
      this.purchaseResponse_ = paramPurchaseNotificationResponse;
      return this;
    }

    public BuyResponse setPurchaseStatusResponse(Buy.PurchaseStatusResponse paramPurchaseStatusResponse)
    {
      if (paramPurchaseStatusResponse == null)
        throw new NullPointerException();
      this.hasPurchaseStatusResponse = true;
      this.purchaseStatusResponse_ = paramPurchaseStatusResponse;
      return this;
    }

    public BuyResponse setPurchaseStatusUrl(String paramString)
    {
      this.hasPurchaseStatusUrl = true;
      this.purchaseStatusUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPurchaseResponse())
        paramCodedOutputStreamMicro.writeMessage(1, getPurchaseResponse());
      if (hasCheckoutInfo())
        paramCodedOutputStreamMicro.writeGroup(2, getCheckoutInfo());
      if (hasContinueViaUrl())
        paramCodedOutputStreamMicro.writeString(8, getContinueViaUrl());
      if (hasPurchaseStatusUrl())
        paramCodedOutputStreamMicro.writeString(9, getPurchaseStatusUrl());
      if (hasCheckoutServiceId())
        paramCodedOutputStreamMicro.writeString(12, getCheckoutServiceId());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(13, getCheckoutTokenRequired());
      if (hasBaseCheckoutUrl())
        paramCodedOutputStreamMicro.writeString(14, getBaseCheckoutUrl());
      Iterator localIterator = getTosCheckboxHtmlList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(37, (String)localIterator.next());
      if (hasIabPermissionError())
        paramCodedOutputStreamMicro.writeInt32(38, getIabPermissionError());
      if (hasPurchaseStatusResponse())
        paramCodedOutputStreamMicro.writeMessage(39, getPurchaseStatusResponse());
      if (hasPurchaseCookie())
        paramCodedOutputStreamMicro.writeString(46, getPurchaseCookie());
      if (hasChallenge())
        paramCodedOutputStreamMicro.writeMessage(49, getChallenge());
      if (hasAddInstrumentPromptHtml())
        paramCodedOutputStreamMicro.writeString(50, getAddInstrumentPromptHtml());
      if (hasConfirmButtonText())
        paramCodedOutputStreamMicro.writeString(51, getConfirmButtonText());
    }

    public static final class CheckoutInfo extends MessageMicro
    {
      private String addInstrumentUrl_ = "";
      private int cachedSize = -1;
      private List<CheckoutOption> checkoutOption_ = Collections.emptyList();
      private String deprecatedCheckoutUrl_ = "";
      private List<Integer> eligibleInstrumentFamily_ = Collections.emptyList();
      private List<CommonDevice.Instrument> eligibleInstrument_ = Collections.emptyList();
      private List<String> footerHtml_ = Collections.emptyList();
      private List<String> footnoteHtml_ = Collections.emptyList();
      private boolean hasAddInstrumentUrl;
      private boolean hasDeprecatedCheckoutUrl;
      private boolean hasItem;
      private Buy.LineItem item_ = null;
      private List<Buy.LineItem> subItem_ = Collections.emptyList();

      public CheckoutInfo addCheckoutOption(CheckoutOption paramCheckoutOption)
      {
        if (paramCheckoutOption == null)
          throw new NullPointerException();
        if (this.checkoutOption_.isEmpty())
          this.checkoutOption_ = new ArrayList();
        this.checkoutOption_.add(paramCheckoutOption);
        return this;
      }

      public CheckoutInfo addEligibleInstrument(CommonDevice.Instrument paramInstrument)
      {
        if (paramInstrument == null)
          throw new NullPointerException();
        if (this.eligibleInstrument_.isEmpty())
          this.eligibleInstrument_ = new ArrayList();
        this.eligibleInstrument_.add(paramInstrument);
        return this;
      }

      public CheckoutInfo addEligibleInstrumentFamily(int paramInt)
      {
        if (this.eligibleInstrumentFamily_.isEmpty())
          this.eligibleInstrumentFamily_ = new ArrayList();
        this.eligibleInstrumentFamily_.add(Integer.valueOf(paramInt));
        return this;
      }

      public CheckoutInfo addFooterHtml(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        if (this.footerHtml_.isEmpty())
          this.footerHtml_ = new ArrayList();
        this.footerHtml_.add(paramString);
        return this;
      }

      public CheckoutInfo addFootnoteHtml(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        if (this.footnoteHtml_.isEmpty())
          this.footnoteHtml_ = new ArrayList();
        this.footnoteHtml_.add(paramString);
        return this;
      }

      public CheckoutInfo addSubItem(Buy.LineItem paramLineItem)
      {
        if (paramLineItem == null)
          throw new NullPointerException();
        if (this.subItem_.isEmpty())
          this.subItem_ = new ArrayList();
        this.subItem_.add(paramLineItem);
        return this;
      }

      public String getAddInstrumentUrl()
      {
        return this.addInstrumentUrl_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public List<CheckoutOption> getCheckoutOptionList()
      {
        return this.checkoutOption_;
      }

      public String getDeprecatedCheckoutUrl()
      {
        return this.deprecatedCheckoutUrl_;
      }

      public List<Integer> getEligibleInstrumentFamilyList()
      {
        return this.eligibleInstrumentFamily_;
      }

      public List<CommonDevice.Instrument> getEligibleInstrumentList()
      {
        return this.eligibleInstrument_;
      }

      public List<String> getFooterHtmlList()
      {
        return this.footerHtml_;
      }

      public List<String> getFootnoteHtmlList()
      {
        return this.footnoteHtml_;
      }

      public Buy.LineItem getItem()
      {
        return this.item_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasItem())
          i = 0 + CodedOutputStreamMicro.computeMessageSize(3, getItem());
        Iterator localIterator1 = getSubItemList().iterator();
        while (localIterator1.hasNext())
          i += CodedOutputStreamMicro.computeMessageSize(4, (Buy.LineItem)localIterator1.next());
        Iterator localIterator2 = getCheckoutOptionList().iterator();
        while (localIterator2.hasNext())
          i += CodedOutputStreamMicro.computeGroupSize(5, (CheckoutOption)localIterator2.next());
        if (hasDeprecatedCheckoutUrl())
          i += CodedOutputStreamMicro.computeStringSize(10, getDeprecatedCheckoutUrl());
        if (hasAddInstrumentUrl())
          i += CodedOutputStreamMicro.computeStringSize(11, getAddInstrumentUrl());
        int j = 0;
        Iterator localIterator3 = getFooterHtmlList().iterator();
        while (localIterator3.hasNext())
          j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator3.next());
        int k = i + j + 2 * getFooterHtmlList().size();
        int m = 0;
        Iterator localIterator4 = getEligibleInstrumentFamilyList().iterator();
        while (localIterator4.hasNext())
          m += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator4.next()).intValue());
        int n = k + m + 2 * getEligibleInstrumentFamilyList().size();
        int i1 = 0;
        Iterator localIterator5 = getFootnoteHtmlList().iterator();
        while (localIterator5.hasNext())
          i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator5.next());
        int i2 = n + i1 + 2 * getFootnoteHtmlList().size();
        Iterator localIterator6 = getEligibleInstrumentList().iterator();
        while (localIterator6.hasNext())
          i2 += CodedOutputStreamMicro.computeMessageSize(44, (CommonDevice.Instrument)localIterator6.next());
        this.cachedSize = i2;
        return i2;
      }

      public List<Buy.LineItem> getSubItemList()
      {
        return this.subItem_;
      }

      public boolean hasAddInstrumentUrl()
      {
        return this.hasAddInstrumentUrl;
      }

      public boolean hasDeprecatedCheckoutUrl()
      {
        return this.hasDeprecatedCheckoutUrl;
      }

      public boolean hasItem()
      {
        return this.hasItem;
      }

      public CheckoutInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 26:
            Buy.LineItem localLineItem2 = new Buy.LineItem();
            paramCodedInputStreamMicro.readMessage(localLineItem2);
            setItem(localLineItem2);
            break;
          case 34:
            Buy.LineItem localLineItem1 = new Buy.LineItem();
            paramCodedInputStreamMicro.readMessage(localLineItem1);
            addSubItem(localLineItem1);
            break;
          case 43:
            CheckoutOption localCheckoutOption = new CheckoutOption();
            paramCodedInputStreamMicro.readGroup(localCheckoutOption, 5);
            addCheckoutOption(localCheckoutOption);
            break;
          case 82:
            setDeprecatedCheckoutUrl(paramCodedInputStreamMicro.readString());
            break;
          case 90:
            setAddInstrumentUrl(paramCodedInputStreamMicro.readString());
            break;
          case 162:
            addFooterHtml(paramCodedInputStreamMicro.readString());
            break;
          case 248:
            addEligibleInstrumentFamily(paramCodedInputStreamMicro.readInt32());
            break;
          case 290:
            addFootnoteHtml(paramCodedInputStreamMicro.readString());
            break;
          case 354:
          }
          CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
          paramCodedInputStreamMicro.readMessage(localInstrument);
          addEligibleInstrument(localInstrument);
        }
      }

      public CheckoutInfo setAddInstrumentUrl(String paramString)
      {
        this.hasAddInstrumentUrl = true;
        this.addInstrumentUrl_ = paramString;
        return this;
      }

      public CheckoutInfo setDeprecatedCheckoutUrl(String paramString)
      {
        this.hasDeprecatedCheckoutUrl = true;
        this.deprecatedCheckoutUrl_ = paramString;
        return this;
      }

      public CheckoutInfo setItem(Buy.LineItem paramLineItem)
      {
        if (paramLineItem == null)
          throw new NullPointerException();
        this.hasItem = true;
        this.item_ = paramLineItem;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasItem())
          paramCodedOutputStreamMicro.writeMessage(3, getItem());
        Iterator localIterator1 = getSubItemList().iterator();
        while (localIterator1.hasNext())
          paramCodedOutputStreamMicro.writeMessage(4, (Buy.LineItem)localIterator1.next());
        Iterator localIterator2 = getCheckoutOptionList().iterator();
        while (localIterator2.hasNext())
          paramCodedOutputStreamMicro.writeGroup(5, (CheckoutOption)localIterator2.next());
        if (hasDeprecatedCheckoutUrl())
          paramCodedOutputStreamMicro.writeString(10, getDeprecatedCheckoutUrl());
        if (hasAddInstrumentUrl())
          paramCodedOutputStreamMicro.writeString(11, getAddInstrumentUrl());
        Iterator localIterator3 = getFooterHtmlList().iterator();
        while (localIterator3.hasNext())
          paramCodedOutputStreamMicro.writeString(20, (String)localIterator3.next());
        Iterator localIterator4 = getEligibleInstrumentFamilyList().iterator();
        while (localIterator4.hasNext())
          paramCodedOutputStreamMicro.writeInt32(31, ((Integer)localIterator4.next()).intValue());
        Iterator localIterator5 = getFootnoteHtmlList().iterator();
        while (localIterator5.hasNext())
          paramCodedOutputStreamMicro.writeString(36, (String)localIterator5.next());
        Iterator localIterator6 = getEligibleInstrumentList().iterator();
        while (localIterator6.hasNext())
          paramCodedOutputStreamMicro.writeMessage(44, (CommonDevice.Instrument)localIterator6.next());
      }

      public static final class CheckoutOption extends MessageMicro
      {
        private int cachedSize = -1;
        private List<Integer> deprecatedInstrumentInapplicableReason_ = Collections.emptyList();
        private List<String> disabledReason_ = Collections.emptyList();
        private String encodedAdjustedCart_ = "";
        private List<String> footerHtml_ = Collections.emptyList();
        private List<String> footnoteHtml_ = Collections.emptyList();
        private String formOfPayment_ = "";
        private boolean hasEncodedAdjustedCart;
        private boolean hasFormOfPayment;
        private boolean hasInstrument;
        private boolean hasInstrumentFamily;
        private boolean hasInstrumentId;
        private boolean hasPurchaseCookie;
        private boolean hasSelectedInstrument;
        private boolean hasSummary;
        private boolean hasTotal;
        private int instrumentFamily_ = 0;
        private String instrumentId_ = "";
        private CommonDevice.Instrument instrument_ = null;
        private List<Buy.LineItem> item_ = Collections.emptyList();
        private String purchaseCookie_ = "";
        private boolean selectedInstrument_ = false;
        private List<Buy.LineItem> subItem_ = Collections.emptyList();
        private Buy.LineItem summary_ = null;
        private Buy.LineItem total_ = null;

        public CheckoutOption addDeprecatedInstrumentInapplicableReason(int paramInt)
        {
          if (this.deprecatedInstrumentInapplicableReason_.isEmpty())
            this.deprecatedInstrumentInapplicableReason_ = new ArrayList();
          this.deprecatedInstrumentInapplicableReason_.add(Integer.valueOf(paramInt));
          return this;
        }

        public CheckoutOption addDisabledReason(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          if (this.disabledReason_.isEmpty())
            this.disabledReason_ = new ArrayList();
          this.disabledReason_.add(paramString);
          return this;
        }

        public CheckoutOption addFooterHtml(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          if (this.footerHtml_.isEmpty())
            this.footerHtml_ = new ArrayList();
          this.footerHtml_.add(paramString);
          return this;
        }

        public CheckoutOption addFootnoteHtml(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          if (this.footnoteHtml_.isEmpty())
            this.footnoteHtml_ = new ArrayList();
          this.footnoteHtml_.add(paramString);
          return this;
        }

        public CheckoutOption addItem(Buy.LineItem paramLineItem)
        {
          if (paramLineItem == null)
            throw new NullPointerException();
          if (this.item_.isEmpty())
            this.item_ = new ArrayList();
          this.item_.add(paramLineItem);
          return this;
        }

        public CheckoutOption addSubItem(Buy.LineItem paramLineItem)
        {
          if (paramLineItem == null)
            throw new NullPointerException();
          if (this.subItem_.isEmpty())
            this.subItem_ = new ArrayList();
          this.subItem_.add(paramLineItem);
          return this;
        }

        public int getCachedSize()
        {
          if (this.cachedSize < 0)
            getSerializedSize();
          return this.cachedSize;
        }

        public List<Integer> getDeprecatedInstrumentInapplicableReasonList()
        {
          return this.deprecatedInstrumentInapplicableReason_;
        }

        public int getDisabledReasonCount()
        {
          return this.disabledReason_.size();
        }

        public List<String> getDisabledReasonList()
        {
          return this.disabledReason_;
        }

        public String getEncodedAdjustedCart()
        {
          return this.encodedAdjustedCart_;
        }

        public List<String> getFooterHtmlList()
        {
          return this.footerHtml_;
        }

        public List<String> getFootnoteHtmlList()
        {
          return this.footnoteHtml_;
        }

        public String getFormOfPayment()
        {
          return this.formOfPayment_;
        }

        public CommonDevice.Instrument getInstrument()
        {
          return this.instrument_;
        }

        public int getInstrumentFamily()
        {
          return this.instrumentFamily_;
        }

        public String getInstrumentId()
        {
          return this.instrumentId_;
        }

        public List<Buy.LineItem> getItemList()
        {
          return this.item_;
        }

        public String getPurchaseCookie()
        {
          return this.purchaseCookie_;
        }

        public boolean getSelectedInstrument()
        {
          return this.selectedInstrument_;
        }

        public int getSerializedSize()
        {
          int i = 0;
          if (hasFormOfPayment())
            i = 0 + CodedOutputStreamMicro.computeStringSize(6, getFormOfPayment());
          if (hasEncodedAdjustedCart())
            i += CodedOutputStreamMicro.computeStringSize(7, getEncodedAdjustedCart());
          if (hasInstrumentId())
            i += CodedOutputStreamMicro.computeStringSize(15, getInstrumentId());
          Iterator localIterator1 = getItemList().iterator();
          while (localIterator1.hasNext())
            i += CodedOutputStreamMicro.computeMessageSize(16, (Buy.LineItem)localIterator1.next());
          Iterator localIterator2 = getSubItemList().iterator();
          while (localIterator2.hasNext())
            i += CodedOutputStreamMicro.computeMessageSize(17, (Buy.LineItem)localIterator2.next());
          if (hasTotal())
            i += CodedOutputStreamMicro.computeMessageSize(18, getTotal());
          int j = 0;
          Iterator localIterator3 = getFooterHtmlList().iterator();
          while (localIterator3.hasNext())
            j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator3.next());
          int k = i + j + 2 * getFooterHtmlList().size();
          if (hasInstrumentFamily())
            k += CodedOutputStreamMicro.computeInt32Size(29, getInstrumentFamily());
          int m = 0;
          Iterator localIterator4 = getDeprecatedInstrumentInapplicableReasonList().iterator();
          while (localIterator4.hasNext())
            m += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator4.next()).intValue());
          int n = k + m + 2 * getDeprecatedInstrumentInapplicableReasonList().size();
          if (hasSelectedInstrument())
            n += CodedOutputStreamMicro.computeBoolSize(32, getSelectedInstrument());
          if (hasSummary())
            n += CodedOutputStreamMicro.computeMessageSize(33, getSummary());
          int i1 = 0;
          Iterator localIterator5 = getFootnoteHtmlList().iterator();
          while (localIterator5.hasNext())
            i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator5.next());
          int i2 = n + i1 + 2 * getFootnoteHtmlList().size();
          if (hasInstrument())
            i2 += CodedOutputStreamMicro.computeMessageSize(43, getInstrument());
          if (hasPurchaseCookie())
            i2 += CodedOutputStreamMicro.computeStringSize(45, getPurchaseCookie());
          int i3 = 0;
          Iterator localIterator6 = getDisabledReasonList().iterator();
          while (localIterator6.hasNext())
            i3 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator6.next());
          int i4 = i2 + i3 + 2 * getDisabledReasonList().size();
          this.cachedSize = i4;
          return i4;
        }

        public List<Buy.LineItem> getSubItemList()
        {
          return this.subItem_;
        }

        public Buy.LineItem getSummary()
        {
          return this.summary_;
        }

        public Buy.LineItem getTotal()
        {
          return this.total_;
        }

        public boolean hasEncodedAdjustedCart()
        {
          return this.hasEncodedAdjustedCart;
        }

        public boolean hasFormOfPayment()
        {
          return this.hasFormOfPayment;
        }

        public boolean hasInstrument()
        {
          return this.hasInstrument;
        }

        public boolean hasInstrumentFamily()
        {
          return this.hasInstrumentFamily;
        }

        public boolean hasInstrumentId()
        {
          return this.hasInstrumentId;
        }

        public boolean hasPurchaseCookie()
        {
          return this.hasPurchaseCookie;
        }

        public boolean hasSelectedInstrument()
        {
          return this.hasSelectedInstrument;
        }

        public boolean hasSummary()
        {
          return this.hasSummary;
        }

        public boolean hasTotal()
        {
          return this.hasTotal;
        }

        public CheckoutOption mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            case 50:
              setFormOfPayment(paramCodedInputStreamMicro.readString());
              break;
            case 58:
              setEncodedAdjustedCart(paramCodedInputStreamMicro.readString());
              break;
            case 122:
              setInstrumentId(paramCodedInputStreamMicro.readString());
              break;
            case 130:
              Buy.LineItem localLineItem4 = new Buy.LineItem();
              paramCodedInputStreamMicro.readMessage(localLineItem4);
              addItem(localLineItem4);
              break;
            case 138:
              Buy.LineItem localLineItem3 = new Buy.LineItem();
              paramCodedInputStreamMicro.readMessage(localLineItem3);
              addSubItem(localLineItem3);
              break;
            case 146:
              Buy.LineItem localLineItem2 = new Buy.LineItem();
              paramCodedInputStreamMicro.readMessage(localLineItem2);
              setTotal(localLineItem2);
              break;
            case 154:
              addFooterHtml(paramCodedInputStreamMicro.readString());
              break;
            case 232:
              setInstrumentFamily(paramCodedInputStreamMicro.readInt32());
              break;
            case 240:
              addDeprecatedInstrumentInapplicableReason(paramCodedInputStreamMicro.readInt32());
              break;
            case 256:
              setSelectedInstrument(paramCodedInputStreamMicro.readBool());
              break;
            case 266:
              Buy.LineItem localLineItem1 = new Buy.LineItem();
              paramCodedInputStreamMicro.readMessage(localLineItem1);
              setSummary(localLineItem1);
              break;
            case 282:
              addFootnoteHtml(paramCodedInputStreamMicro.readString());
              break;
            case 346:
              CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
              paramCodedInputStreamMicro.readMessage(localInstrument);
              setInstrument(localInstrument);
              break;
            case 362:
              setPurchaseCookie(paramCodedInputStreamMicro.readString());
              break;
            case 386:
            }
            addDisabledReason(paramCodedInputStreamMicro.readString());
          }
        }

        public CheckoutOption setEncodedAdjustedCart(String paramString)
        {
          this.hasEncodedAdjustedCart = true;
          this.encodedAdjustedCart_ = paramString;
          return this;
        }

        public CheckoutOption setFormOfPayment(String paramString)
        {
          this.hasFormOfPayment = true;
          this.formOfPayment_ = paramString;
          return this;
        }

        public CheckoutOption setInstrument(CommonDevice.Instrument paramInstrument)
        {
          if (paramInstrument == null)
            throw new NullPointerException();
          this.hasInstrument = true;
          this.instrument_ = paramInstrument;
          return this;
        }

        public CheckoutOption setInstrumentFamily(int paramInt)
        {
          this.hasInstrumentFamily = true;
          this.instrumentFamily_ = paramInt;
          return this;
        }

        public CheckoutOption setInstrumentId(String paramString)
        {
          this.hasInstrumentId = true;
          this.instrumentId_ = paramString;
          return this;
        }

        public CheckoutOption setPurchaseCookie(String paramString)
        {
          this.hasPurchaseCookie = true;
          this.purchaseCookie_ = paramString;
          return this;
        }

        public CheckoutOption setSelectedInstrument(boolean paramBoolean)
        {
          this.hasSelectedInstrument = true;
          this.selectedInstrument_ = paramBoolean;
          return this;
        }

        public CheckoutOption setSummary(Buy.LineItem paramLineItem)
        {
          if (paramLineItem == null)
            throw new NullPointerException();
          this.hasSummary = true;
          this.summary_ = paramLineItem;
          return this;
        }

        public CheckoutOption setTotal(Buy.LineItem paramLineItem)
        {
          if (paramLineItem == null)
            throw new NullPointerException();
          this.hasTotal = true;
          this.total_ = paramLineItem;
          return this;
        }

        public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
          throws IOException
        {
          if (hasFormOfPayment())
            paramCodedOutputStreamMicro.writeString(6, getFormOfPayment());
          if (hasEncodedAdjustedCart())
            paramCodedOutputStreamMicro.writeString(7, getEncodedAdjustedCart());
          if (hasInstrumentId())
            paramCodedOutputStreamMicro.writeString(15, getInstrumentId());
          Iterator localIterator1 = getItemList().iterator();
          while (localIterator1.hasNext())
            paramCodedOutputStreamMicro.writeMessage(16, (Buy.LineItem)localIterator1.next());
          Iterator localIterator2 = getSubItemList().iterator();
          while (localIterator2.hasNext())
            paramCodedOutputStreamMicro.writeMessage(17, (Buy.LineItem)localIterator2.next());
          if (hasTotal())
            paramCodedOutputStreamMicro.writeMessage(18, getTotal());
          Iterator localIterator3 = getFooterHtmlList().iterator();
          while (localIterator3.hasNext())
            paramCodedOutputStreamMicro.writeString(19, (String)localIterator3.next());
          if (hasInstrumentFamily())
            paramCodedOutputStreamMicro.writeInt32(29, getInstrumentFamily());
          Iterator localIterator4 = getDeprecatedInstrumentInapplicableReasonList().iterator();
          while (localIterator4.hasNext())
            paramCodedOutputStreamMicro.writeInt32(30, ((Integer)localIterator4.next()).intValue());
          if (hasSelectedInstrument())
            paramCodedOutputStreamMicro.writeBool(32, getSelectedInstrument());
          if (hasSummary())
            paramCodedOutputStreamMicro.writeMessage(33, getSummary());
          Iterator localIterator5 = getFootnoteHtmlList().iterator();
          while (localIterator5.hasNext())
            paramCodedOutputStreamMicro.writeString(35, (String)localIterator5.next());
          if (hasInstrument())
            paramCodedOutputStreamMicro.writeMessage(43, getInstrument());
          if (hasPurchaseCookie())
            paramCodedOutputStreamMicro.writeString(45, getPurchaseCookie());
          Iterator localIterator6 = getDisabledReasonList().iterator();
          while (localIterator6.hasNext())
            paramCodedOutputStreamMicro.writeString(48, (String)localIterator6.next());
        }
      }
    }
  }

  public static final class LineItem extends MessageMicro
  {
    private Buy.Money amount_ = null;
    private int cachedSize = -1;
    private String description_ = "";
    private boolean hasAmount;
    private boolean hasDescription;
    private boolean hasName;
    private boolean hasOffer;
    private String name_ = "";
    private Common.Offer offer_ = null;

    public Buy.Money getAmount()
    {
      return this.amount_;
    }

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

    public String getName()
    {
      return this.name_;
    }

    public Common.Offer getOffer()
    {
      return this.offer_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasDescription())
        i += CodedOutputStreamMicro.computeStringSize(2, getDescription());
      if (hasOffer())
        i += CodedOutputStreamMicro.computeMessageSize(3, getOffer());
      if (hasAmount())
        i += CodedOutputStreamMicro.computeMessageSize(4, getAmount());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAmount()
    {
      return this.hasAmount;
    }

    public boolean hasDescription()
    {
      return this.hasDescription;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasOffer()
    {
      return this.hasOffer;
    }

    public LineItem mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDescription(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          Common.Offer localOffer = new Common.Offer();
          paramCodedInputStreamMicro.readMessage(localOffer);
          setOffer(localOffer);
          break;
        case 34:
        }
        Buy.Money localMoney = new Buy.Money();
        paramCodedInputStreamMicro.readMessage(localMoney);
        setAmount(localMoney);
      }
    }

    public LineItem setAmount(Buy.Money paramMoney)
    {
      if (paramMoney == null)
        throw new NullPointerException();
      this.hasAmount = true;
      this.amount_ = paramMoney;
      return this;
    }

    public LineItem setDescription(String paramString)
    {
      this.hasDescription = true;
      this.description_ = paramString;
      return this;
    }

    public LineItem setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public LineItem setOffer(Common.Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      this.hasOffer = true;
      this.offer_ = paramOffer;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasDescription())
        paramCodedOutputStreamMicro.writeString(2, getDescription());
      if (hasOffer())
        paramCodedOutputStreamMicro.writeMessage(3, getOffer());
      if (hasAmount())
        paramCodedOutputStreamMicro.writeMessage(4, getAmount());
    }
  }

  public static final class Money extends MessageMicro
  {
    private int cachedSize = -1;
    private String currencyCode_ = "";
    private String formattedAmount_ = "";
    private boolean hasCurrencyCode;
    private boolean hasFormattedAmount;
    private boolean hasMicros;
    private long micros_ = 0L;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCurrencyCode()
    {
      return this.currencyCode_;
    }

    public String getFormattedAmount()
    {
      return this.formattedAmount_;
    }

    public long getMicros()
    {
      return this.micros_;
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
      this.cachedSize = i;
      return i;
    }

    public boolean hasCurrencyCode()
    {
      return this.hasCurrencyCode;
    }

    public boolean hasFormattedAmount()
    {
      return this.hasFormattedAmount;
    }

    public boolean hasMicros()
    {
      return this.hasMicros;
    }

    public Money mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        setFormattedAmount(paramCodedInputStreamMicro.readString());
      }
    }

    public Money setCurrencyCode(String paramString)
    {
      this.hasCurrencyCode = true;
      this.currencyCode_ = paramString;
      return this;
    }

    public Money setFormattedAmount(String paramString)
    {
      this.hasFormattedAmount = true;
      this.formattedAmount_ = paramString;
      return this;
    }

    public Money setMicros(long paramLong)
    {
      this.hasMicros = true;
      this.micros_ = paramLong;
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
    }
  }

  public static final class PurchaseNotificationResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private DebugInfoProto.DebugInfo debugInfo_ = null;
    private boolean hasDebugInfo;
    private boolean hasLocalizedErrorMessage;
    private boolean hasPurchaseId;
    private boolean hasStatus;
    private String localizedErrorMessage_ = "";
    private String purchaseId_ = "";
    private int status_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DebugInfoProto.DebugInfo getDebugInfo()
    {
      return this.debugInfo_;
    }

    public String getLocalizedErrorMessage()
    {
      return this.localizedErrorMessage_;
    }

    public String getPurchaseId()
    {
      return this.purchaseId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasStatus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatus());
      if (hasDebugInfo())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDebugInfo());
      if (hasLocalizedErrorMessage())
        i += CodedOutputStreamMicro.computeStringSize(3, getLocalizedErrorMessage());
      if (hasPurchaseId())
        i += CodedOutputStreamMicro.computeStringSize(4, getPurchaseId());
      this.cachedSize = i;
      return i;
    }

    public int getStatus()
    {
      return this.status_;
    }

    public boolean hasDebugInfo()
    {
      return this.hasDebugInfo;
    }

    public boolean hasLocalizedErrorMessage()
    {
      return this.hasLocalizedErrorMessage;
    }

    public boolean hasPurchaseId()
    {
      return this.hasPurchaseId;
    }

    public boolean hasStatus()
    {
      return this.hasStatus;
    }

    public PurchaseNotificationResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 18:
          DebugInfoProto.DebugInfo localDebugInfo = new DebugInfoProto.DebugInfo();
          paramCodedInputStreamMicro.readMessage(localDebugInfo);
          setDebugInfo(localDebugInfo);
          break;
        case 26:
          setLocalizedErrorMessage(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setPurchaseId(paramCodedInputStreamMicro.readString());
      }
    }

    public PurchaseNotificationResponse setDebugInfo(DebugInfoProto.DebugInfo paramDebugInfo)
    {
      if (paramDebugInfo == null)
        throw new NullPointerException();
      this.hasDebugInfo = true;
      this.debugInfo_ = paramDebugInfo;
      return this;
    }

    public PurchaseNotificationResponse setLocalizedErrorMessage(String paramString)
    {
      this.hasLocalizedErrorMessage = true;
      this.localizedErrorMessage_ = paramString;
      return this;
    }

    public PurchaseNotificationResponse setPurchaseId(String paramString)
    {
      this.hasPurchaseId = true;
      this.purchaseId_ = paramString;
      return this;
    }

    public PurchaseNotificationResponse setStatus(int paramInt)
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
      if (hasDebugInfo())
        paramCodedOutputStreamMicro.writeMessage(2, getDebugInfo());
      if (hasLocalizedErrorMessage())
        paramCodedOutputStreamMicro.writeString(3, getLocalizedErrorMessage());
      if (hasPurchaseId())
        paramCodedOutputStreamMicro.writeString(4, getPurchaseId());
    }
  }

  public static final class PurchaseStatusResponse extends MessageMicro
  {
    private AndroidAppDelivery.AndroidAppDeliveryData appDeliveryData_ = null;
    private String briefMessage_ = "";
    private int cachedSize = -1;
    private boolean hasAppDeliveryData;
    private boolean hasBriefMessage;
    private boolean hasInfoUrl;
    private boolean hasLibraryUpdate;
    private boolean hasRejectedInstrument;
    private boolean hasStatus;
    private boolean hasStatusMsg;
    private boolean hasStatusTitle;
    private String infoUrl_ = "";
    private Library.LibraryUpdate libraryUpdate_ = null;
    private CommonDevice.Instrument rejectedInstrument_ = null;
    private String statusMsg_ = "";
    private String statusTitle_ = "";
    private int status_ = 1;

    public AndroidAppDelivery.AndroidAppDeliveryData getAppDeliveryData()
    {
      return this.appDeliveryData_;
    }

    public String getBriefMessage()
    {
      return this.briefMessage_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getInfoUrl()
    {
      return this.infoUrl_;
    }

    public Library.LibraryUpdate getLibraryUpdate()
    {
      return this.libraryUpdate_;
    }

    public CommonDevice.Instrument getRejectedInstrument()
    {
      return this.rejectedInstrument_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasStatus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatus());
      if (hasStatusMsg())
        i += CodedOutputStreamMicro.computeStringSize(2, getStatusMsg());
      if (hasStatusTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getStatusTitle());
      if (hasBriefMessage())
        i += CodedOutputStreamMicro.computeStringSize(4, getBriefMessage());
      if (hasInfoUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getInfoUrl());
      if (hasLibraryUpdate())
        i += CodedOutputStreamMicro.computeMessageSize(6, getLibraryUpdate());
      if (hasRejectedInstrument())
        i += CodedOutputStreamMicro.computeMessageSize(7, getRejectedInstrument());
      if (hasAppDeliveryData())
        i += CodedOutputStreamMicro.computeMessageSize(8, getAppDeliveryData());
      this.cachedSize = i;
      return i;
    }

    public int getStatus()
    {
      return this.status_;
    }

    public String getStatusMsg()
    {
      return this.statusMsg_;
    }

    public String getStatusTitle()
    {
      return this.statusTitle_;
    }

    public boolean hasAppDeliveryData()
    {
      return this.hasAppDeliveryData;
    }

    public boolean hasBriefMessage()
    {
      return this.hasBriefMessage;
    }

    public boolean hasInfoUrl()
    {
      return this.hasInfoUrl;
    }

    public boolean hasLibraryUpdate()
    {
      return this.hasLibraryUpdate;
    }

    public boolean hasRejectedInstrument()
    {
      return this.hasRejectedInstrument;
    }

    public boolean hasStatus()
    {
      return this.hasStatus;
    }

    public boolean hasStatusMsg()
    {
      return this.hasStatusMsg;
    }

    public boolean hasStatusTitle()
    {
      return this.hasStatusTitle;
    }

    public PurchaseStatusResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 18:
          setStatusMsg(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setStatusTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setBriefMessage(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setInfoUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          Library.LibraryUpdate localLibraryUpdate = new Library.LibraryUpdate();
          paramCodedInputStreamMicro.readMessage(localLibraryUpdate);
          setLibraryUpdate(localLibraryUpdate);
          break;
        case 58:
          CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
          paramCodedInputStreamMicro.readMessage(localInstrument);
          setRejectedInstrument(localInstrument);
          break;
        case 66:
        }
        AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = new AndroidAppDelivery.AndroidAppDeliveryData();
        paramCodedInputStreamMicro.readMessage(localAndroidAppDeliveryData);
        setAppDeliveryData(localAndroidAppDeliveryData);
      }
    }

    public PurchaseStatusResponse setAppDeliveryData(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData)
    {
      if (paramAndroidAppDeliveryData == null)
        throw new NullPointerException();
      this.hasAppDeliveryData = true;
      this.appDeliveryData_ = paramAndroidAppDeliveryData;
      return this;
    }

    public PurchaseStatusResponse setBriefMessage(String paramString)
    {
      this.hasBriefMessage = true;
      this.briefMessage_ = paramString;
      return this;
    }

    public PurchaseStatusResponse setInfoUrl(String paramString)
    {
      this.hasInfoUrl = true;
      this.infoUrl_ = paramString;
      return this;
    }

    public PurchaseStatusResponse setLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate)
    {
      if (paramLibraryUpdate == null)
        throw new NullPointerException();
      this.hasLibraryUpdate = true;
      this.libraryUpdate_ = paramLibraryUpdate;
      return this;
    }

    public PurchaseStatusResponse setRejectedInstrument(CommonDevice.Instrument paramInstrument)
    {
      if (paramInstrument == null)
        throw new NullPointerException();
      this.hasRejectedInstrument = true;
      this.rejectedInstrument_ = paramInstrument;
      return this;
    }

    public PurchaseStatusResponse setStatus(int paramInt)
    {
      this.hasStatus = true;
      this.status_ = paramInt;
      return this;
    }

    public PurchaseStatusResponse setStatusMsg(String paramString)
    {
      this.hasStatusMsg = true;
      this.statusMsg_ = paramString;
      return this;
    }

    public PurchaseStatusResponse setStatusTitle(String paramString)
    {
      this.hasStatusTitle = true;
      this.statusTitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasStatus())
        paramCodedOutputStreamMicro.writeInt32(1, getStatus());
      if (hasStatusMsg())
        paramCodedOutputStreamMicro.writeString(2, getStatusMsg());
      if (hasStatusTitle())
        paramCodedOutputStreamMicro.writeString(3, getStatusTitle());
      if (hasBriefMessage())
        paramCodedOutputStreamMicro.writeString(4, getBriefMessage());
      if (hasInfoUrl())
        paramCodedOutputStreamMicro.writeString(5, getInfoUrl());
      if (hasLibraryUpdate())
        paramCodedOutputStreamMicro.writeMessage(6, getLibraryUpdate());
      if (hasRejectedInstrument())
        paramCodedOutputStreamMicro.writeMessage(7, getRejectedInstrument());
      if (hasAppDeliveryData())
        paramCodedOutputStreamMicro.writeMessage(8, getAppDeliveryData());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Buy
 * JD-Core Version:    0.6.2
 */
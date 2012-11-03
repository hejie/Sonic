package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class BuyInstruments
{
  public static final class CheckInstrumentResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean checkoutTokenRequired_ = false;
    private List<CommonDevice.Instrument> eligibleInstrument_ = Collections.emptyList();
    private boolean hasCheckoutTokenRequired;
    private boolean hasUserHasValidInstrument;
    private List<CommonDevice.Instrument> instrument_ = Collections.emptyList();
    private boolean userHasValidInstrument_ = false;

    public CheckInstrumentResponse addEligibleInstrument(CommonDevice.Instrument paramInstrument)
    {
      if (paramInstrument == null)
        throw new NullPointerException();
      if (this.eligibleInstrument_.isEmpty())
        this.eligibleInstrument_ = new ArrayList();
      this.eligibleInstrument_.add(paramInstrument);
      return this;
    }

    public CheckInstrumentResponse addInstrument(CommonDevice.Instrument paramInstrument)
    {
      if (paramInstrument == null)
        throw new NullPointerException();
      if (this.instrument_.isEmpty())
        this.instrument_ = new ArrayList();
      this.instrument_.add(paramInstrument);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public List<CommonDevice.Instrument> getEligibleInstrumentList()
    {
      return this.eligibleInstrument_;
    }

    public List<CommonDevice.Instrument> getInstrumentList()
    {
      return this.instrument_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUserHasValidInstrument())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getUserHasValidInstrument());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(2, getCheckoutTokenRequired());
      Iterator localIterator1 = getInstrumentList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (CommonDevice.Instrument)localIterator1.next());
      Iterator localIterator2 = getEligibleInstrumentList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(5, (CommonDevice.Instrument)localIterator2.next());
      this.cachedSize = i;
      return i;
    }

    public boolean getUserHasValidInstrument()
    {
      return this.userHasValidInstrument_;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public boolean hasUserHasValidInstrument()
    {
      return this.hasUserHasValidInstrument;
    }

    public CheckInstrumentResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUserHasValidInstrument(paramCodedInputStreamMicro.readBool());
          break;
        case 16:
          setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          CommonDevice.Instrument localInstrument2 = new CommonDevice.Instrument();
          paramCodedInputStreamMicro.readMessage(localInstrument2);
          addInstrument(localInstrument2);
          break;
        case 42:
        }
        CommonDevice.Instrument localInstrument1 = new CommonDevice.Instrument();
        paramCodedInputStreamMicro.readMessage(localInstrument1);
        addEligibleInstrument(localInstrument1);
      }
    }

    public CheckInstrumentResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public CheckInstrumentResponse setUserHasValidInstrument(boolean paramBoolean)
    {
      this.hasUserHasValidInstrument = true;
      this.userHasValidInstrument_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUserHasValidInstrument())
        paramCodedOutputStreamMicro.writeBool(1, getUserHasValidInstrument());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(2, getCheckoutTokenRequired());
      Iterator localIterator1 = getInstrumentList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (CommonDevice.Instrument)localIterator1.next());
      Iterator localIterator2 = getEligibleInstrumentList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(5, (CommonDevice.Instrument)localIterator2.next());
    }
  }

  public static final class InstrumentSetupInfoResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean checkoutTokenRequired_ = false;
    private boolean hasCheckoutTokenRequired;
    private List<CommonDevice.InstrumentSetupInfo> setupInfo_ = Collections.emptyList();

    public InstrumentSetupInfoResponse addSetupInfo(CommonDevice.InstrumentSetupInfo paramInstrumentSetupInfo)
    {
      if (paramInstrumentSetupInfo == null)
        throw new NullPointerException();
      if (this.setupInfo_.isEmpty())
        this.setupInfo_ = new ArrayList();
      this.setupInfo_.add(paramInstrumentSetupInfo);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getSetupInfoList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (CommonDevice.InstrumentSetupInfo)localIterator.next());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(2, getCheckoutTokenRequired());
      this.cachedSize = i;
      return i;
    }

    public CommonDevice.InstrumentSetupInfo getSetupInfo(int paramInt)
    {
      return (CommonDevice.InstrumentSetupInfo)this.setupInfo_.get(paramInt);
    }

    public int getSetupInfoCount()
    {
      return this.setupInfo_.size();
    }

    public List<CommonDevice.InstrumentSetupInfo> getSetupInfoList()
    {
      return this.setupInfo_;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public InstrumentSetupInfoResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          CommonDevice.InstrumentSetupInfo localInstrumentSetupInfo = new CommonDevice.InstrumentSetupInfo();
          paramCodedInputStreamMicro.readMessage(localInstrumentSetupInfo);
          addSetupInfo(localInstrumentSetupInfo);
          break;
        case 16:
        }
        setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
      }
    }

    public InstrumentSetupInfoResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getSetupInfoList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (CommonDevice.InstrumentSetupInfo)localIterator.next());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(2, getCheckoutTokenRequired());
    }
  }

  public static final class RedeemGiftCardRequest extends MessageMicro
  {
    private List<String> acceptedLegalDocumentId_ = Collections.emptyList();
    private BillingAddress.Address address_ = null;
    private int cachedSize = -1;
    private String checkoutToken_ = "";
    private String giftCardPin_ = "";
    private boolean hasAddress;
    private boolean hasCheckoutToken;
    private boolean hasGiftCardPin;

    public RedeemGiftCardRequest addAcceptedLegalDocumentId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.acceptedLegalDocumentId_.isEmpty())
        this.acceptedLegalDocumentId_ = new ArrayList();
      this.acceptedLegalDocumentId_.add(paramString);
      return this;
    }

    public List<String> getAcceptedLegalDocumentIdList()
    {
      return this.acceptedLegalDocumentId_;
    }

    public BillingAddress.Address getAddress()
    {
      return this.address_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCheckoutToken()
    {
      return this.checkoutToken_;
    }

    public String getGiftCardPin()
    {
      return this.giftCardPin_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGiftCardPin())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getGiftCardPin());
      if (hasAddress())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAddress());
      int j = 0;
      Iterator localIterator = getAcceptedLegalDocumentIdList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 1 * getAcceptedLegalDocumentIdList().size();
      if (hasCheckoutToken())
        k += CodedOutputStreamMicro.computeStringSize(4, getCheckoutToken());
      this.cachedSize = k;
      return k;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public boolean hasCheckoutToken()
    {
      return this.hasCheckoutToken;
    }

    public boolean hasGiftCardPin()
    {
      return this.hasGiftCardPin;
    }

    public RedeemGiftCardRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGiftCardPin(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          BillingAddress.Address localAddress = new BillingAddress.Address();
          paramCodedInputStreamMicro.readMessage(localAddress);
          setAddress(localAddress);
          break;
        case 26:
          addAcceptedLegalDocumentId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setCheckoutToken(paramCodedInputStreamMicro.readString());
      }
    }

    public RedeemGiftCardRequest setAddress(BillingAddress.Address paramAddress)
    {
      if (paramAddress == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddress;
      return this;
    }

    public RedeemGiftCardRequest setCheckoutToken(String paramString)
    {
      this.hasCheckoutToken = true;
      this.checkoutToken_ = paramString;
      return this;
    }

    public RedeemGiftCardRequest setGiftCardPin(String paramString)
    {
      this.hasGiftCardPin = true;
      this.giftCardPin_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGiftCardPin())
        paramCodedOutputStreamMicro.writeString(1, getGiftCardPin());
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(2, getAddress());
      Iterator localIterator = getAcceptedLegalDocumentIdList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator.next());
      if (hasCheckoutToken())
        paramCodedOutputStreamMicro.writeString(4, getCheckoutToken());
    }
  }

  public static final class RedeemGiftCardResponse extends MessageMicro
  {
    private ChallengeProtos.AddressChallenge addressChallenge_ = null;
    private String balanceHtml_ = "";
    private int cachedSize = -1;
    private boolean checkoutTokenRequired_ = false;
    private boolean hasAddressChallenge;
    private boolean hasBalanceHtml;
    private boolean hasCheckoutTokenRequired;
    private boolean hasResult;
    private boolean hasUserMessageHtml;
    private int result_ = 0;
    private String userMessageHtml_ = "";

    public ChallengeProtos.AddressChallenge getAddressChallenge()
    {
      return this.addressChallenge_;
    }

    public String getBalanceHtml()
    {
      return this.balanceHtml_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public int getResult()
    {
      return this.result_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResult())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResult());
      if (hasUserMessageHtml())
        i += CodedOutputStreamMicro.computeStringSize(2, getUserMessageHtml());
      if (hasBalanceHtml())
        i += CodedOutputStreamMicro.computeStringSize(3, getBalanceHtml());
      if (hasAddressChallenge())
        i += CodedOutputStreamMicro.computeMessageSize(4, getAddressChallenge());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(5, getCheckoutTokenRequired());
      this.cachedSize = i;
      return i;
    }

    public String getUserMessageHtml()
    {
      return this.userMessageHtml_;
    }

    public boolean hasAddressChallenge()
    {
      return this.hasAddressChallenge;
    }

    public boolean hasBalanceHtml()
    {
      return this.hasBalanceHtml;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public boolean hasResult()
    {
      return this.hasResult;
    }

    public boolean hasUserMessageHtml()
    {
      return this.hasUserMessageHtml;
    }

    public RedeemGiftCardResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResult(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setUserMessageHtml(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setBalanceHtml(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          ChallengeProtos.AddressChallenge localAddressChallenge = new ChallengeProtos.AddressChallenge();
          paramCodedInputStreamMicro.readMessage(localAddressChallenge);
          setAddressChallenge(localAddressChallenge);
          break;
        case 40:
        }
        setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
      }
    }

    public RedeemGiftCardResponse setAddressChallenge(ChallengeProtos.AddressChallenge paramAddressChallenge)
    {
      if (paramAddressChallenge == null)
        throw new NullPointerException();
      this.hasAddressChallenge = true;
      this.addressChallenge_ = paramAddressChallenge;
      return this;
    }

    public RedeemGiftCardResponse setBalanceHtml(String paramString)
    {
      this.hasBalanceHtml = true;
      this.balanceHtml_ = paramString;
      return this;
    }

    public RedeemGiftCardResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public RedeemGiftCardResponse setResult(int paramInt)
    {
      this.hasResult = true;
      this.result_ = paramInt;
      return this;
    }

    public RedeemGiftCardResponse setUserMessageHtml(String paramString)
    {
      this.hasUserMessageHtml = true;
      this.userMessageHtml_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResult())
        paramCodedOutputStreamMicro.writeInt32(1, getResult());
      if (hasUserMessageHtml())
        paramCodedOutputStreamMicro.writeString(2, getUserMessageHtml());
      if (hasBalanceHtml())
        paramCodedOutputStreamMicro.writeString(3, getBalanceHtml());
      if (hasAddressChallenge())
        paramCodedOutputStreamMicro.writeMessage(4, getAddressChallenge());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(5, getCheckoutTokenRequired());
    }
  }

  public static final class UpdateInstrumentRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private String checkoutToken_ = "";
    private boolean hasCheckoutToken;
    private boolean hasInstrument;
    private CommonDevice.Instrument instrument_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCheckoutToken()
    {
      return this.checkoutToken_;
    }

    public CommonDevice.Instrument getInstrument()
    {
      return this.instrument_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrument())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getInstrument());
      if (hasCheckoutToken())
        i += CodedOutputStreamMicro.computeStringSize(2, getCheckoutToken());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCheckoutToken()
    {
      return this.hasCheckoutToken;
    }

    public boolean hasInstrument()
    {
      return this.hasInstrument;
    }

    public UpdateInstrumentRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          CommonDevice.Instrument localInstrument = new CommonDevice.Instrument();
          paramCodedInputStreamMicro.readMessage(localInstrument);
          setInstrument(localInstrument);
          break;
        case 18:
        }
        setCheckoutToken(paramCodedInputStreamMicro.readString());
      }
    }

    public UpdateInstrumentRequest setCheckoutToken(String paramString)
    {
      this.hasCheckoutToken = true;
      this.checkoutToken_ = paramString;
      return this;
    }

    public UpdateInstrumentRequest setInstrument(CommonDevice.Instrument paramInstrument)
    {
      if (paramInstrument == null)
        throw new NullPointerException();
      this.hasInstrument = true;
      this.instrument_ = paramInstrument;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstrument())
        paramCodedOutputStreamMicro.writeMessage(1, getInstrument());
      if (hasCheckoutToken())
        paramCodedOutputStreamMicro.writeString(2, getCheckoutToken());
    }
  }

  public static final class UpdateInstrumentResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean checkoutTokenRequired_ = false;
    private List<ChallengeProtos.InputValidationError> errorInputField_ = Collections.emptyList();
    private boolean hasCheckoutTokenRequired;
    private boolean hasInstrumentId;
    private boolean hasRedeemedOffer;
    private boolean hasResult;
    private boolean hasUserMessageHtml;
    private String instrumentId_ = "";
    private CheckPromoOffer.RedeemedPromoOffer redeemedOffer_ = null;
    private int result_ = 0;
    private String userMessageHtml_ = "";

    public UpdateInstrumentResponse addErrorInputField(ChallengeProtos.InputValidationError paramInputValidationError)
    {
      if (paramInputValidationError == null)
        throw new NullPointerException();
      if (this.errorInputField_.isEmpty())
        this.errorInputField_ = new ArrayList();
      this.errorInputField_.add(paramInputValidationError);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCheckoutTokenRequired()
    {
      return this.checkoutTokenRequired_;
    }

    public List<ChallengeProtos.InputValidationError> getErrorInputFieldList()
    {
      return this.errorInputField_;
    }

    public String getInstrumentId()
    {
      return this.instrumentId_;
    }

    public CheckPromoOffer.RedeemedPromoOffer getRedeemedOffer()
    {
      return this.redeemedOffer_;
    }

    public int getResult()
    {
      return this.result_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResult())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResult());
      if (hasInstrumentId())
        i += CodedOutputStreamMicro.computeStringSize(2, getInstrumentId());
      if (hasUserMessageHtml())
        i += CodedOutputStreamMicro.computeStringSize(3, getUserMessageHtml());
      Iterator localIterator = getErrorInputFieldList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (ChallengeProtos.InputValidationError)localIterator.next());
      if (hasCheckoutTokenRequired())
        i += CodedOutputStreamMicro.computeBoolSize(5, getCheckoutTokenRequired());
      if (hasRedeemedOffer())
        i += CodedOutputStreamMicro.computeMessageSize(6, getRedeemedOffer());
      this.cachedSize = i;
      return i;
    }

    public String getUserMessageHtml()
    {
      return this.userMessageHtml_;
    }

    public boolean hasCheckoutTokenRequired()
    {
      return this.hasCheckoutTokenRequired;
    }

    public boolean hasInstrumentId()
    {
      return this.hasInstrumentId;
    }

    public boolean hasRedeemedOffer()
    {
      return this.hasRedeemedOffer;
    }

    public boolean hasResult()
    {
      return this.hasResult;
    }

    public boolean hasUserMessageHtml()
    {
      return this.hasUserMessageHtml;
    }

    public UpdateInstrumentResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResult(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setInstrumentId(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setUserMessageHtml(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          ChallengeProtos.InputValidationError localInputValidationError = new ChallengeProtos.InputValidationError();
          paramCodedInputStreamMicro.readMessage(localInputValidationError);
          addErrorInputField(localInputValidationError);
          break;
        case 40:
          setCheckoutTokenRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 50:
        }
        CheckPromoOffer.RedeemedPromoOffer localRedeemedPromoOffer = new CheckPromoOffer.RedeemedPromoOffer();
        paramCodedInputStreamMicro.readMessage(localRedeemedPromoOffer);
        setRedeemedOffer(localRedeemedPromoOffer);
      }
    }

    public UpdateInstrumentResponse setCheckoutTokenRequired(boolean paramBoolean)
    {
      this.hasCheckoutTokenRequired = true;
      this.checkoutTokenRequired_ = paramBoolean;
      return this;
    }

    public UpdateInstrumentResponse setInstrumentId(String paramString)
    {
      this.hasInstrumentId = true;
      this.instrumentId_ = paramString;
      return this;
    }

    public UpdateInstrumentResponse setRedeemedOffer(CheckPromoOffer.RedeemedPromoOffer paramRedeemedPromoOffer)
    {
      if (paramRedeemedPromoOffer == null)
        throw new NullPointerException();
      this.hasRedeemedOffer = true;
      this.redeemedOffer_ = paramRedeemedPromoOffer;
      return this;
    }

    public UpdateInstrumentResponse setResult(int paramInt)
    {
      this.hasResult = true;
      this.result_ = paramInt;
      return this;
    }

    public UpdateInstrumentResponse setUserMessageHtml(String paramString)
    {
      this.hasUserMessageHtml = true;
      this.userMessageHtml_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResult())
        paramCodedOutputStreamMicro.writeInt32(1, getResult());
      if (hasInstrumentId())
        paramCodedOutputStreamMicro.writeString(2, getInstrumentId());
      if (hasUserMessageHtml())
        paramCodedOutputStreamMicro.writeString(3, getUserMessageHtml());
      Iterator localIterator = getErrorInputFieldList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (ChallengeProtos.InputValidationError)localIterator.next());
      if (hasCheckoutTokenRequired())
        paramCodedOutputStreamMicro.writeBool(5, getCheckoutTokenRequired());
      if (hasRedeemedOffer())
        paramCodedOutputStreamMicro.writeMessage(6, getRedeemedOffer());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.BuyInstruments
 * JD-Core Version:    0.6.2
 */
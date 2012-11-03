package com.google.android.finsky.remoting.protos;

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

public final class Response
{
  public static final class Payload extends MessageMicro
  {
    private Tos.AcceptTosResponse acceptTosResponse_ = null;
    private AckNotification.AckNotificationResponse ackNotificationResponse_ = null;
    private Browse.BrowseResponse browseResponse_ = null;
    private Details.BulkDetailsResponse bulkDetailsResponse_ = null;
    private Buy.BuyResponse buyResponse_ = null;
    private int cachedSize = -1;
    private BuyInstruments.CheckInstrumentResponse checkInstrumentResponse_ = null;
    private CheckPromoOffer.CheckPromoOfferResponse checkPromoOfferResponse_ = null;
    private ConsumePurchaseResponse consumePurchaseResponse_ = null;
    private Delivery.DeliveryResponse deliveryResponse_ = null;
    private Details.DetailsResponse detailsResponse_ = null;
    private ContentFlagging.FlagContentResponse flagContentResponse_ = null;
    private boolean hasAcceptTosResponse;
    private boolean hasAckNotificationResponse;
    private boolean hasBrowseResponse;
    private boolean hasBulkDetailsResponse;
    private boolean hasBuyResponse;
    private boolean hasCheckInstrumentResponse;
    private boolean hasCheckPromoOfferResponse;
    private boolean hasConsumePurchaseResponse;
    private boolean hasDeliveryResponse;
    private boolean hasDetailsResponse;
    private boolean hasFlagContentResponse;
    private boolean hasInitiateAssociationResponse;
    private boolean hasInstrumentSetupInfoResponse;
    private boolean hasLibraryReplicationResponse;
    private boolean hasListResponse;
    private boolean hasLogResponse;
    private boolean hasModifyLibraryResponse;
    private boolean hasPlusOneResponse;
    private boolean hasPlusProfileResponse;
    private boolean hasPurchaseStatusResponse;
    private boolean hasRateSuggestedContentResponse;
    private boolean hasRedeemGiftCardResponse;
    private boolean hasResolveLinkResponse;
    private boolean hasReviewResponse;
    private boolean hasRevokeResponse;
    private boolean hasSearchResponse;
    private boolean hasTocResponse;
    private boolean hasUpdateInstrumentResponse;
    private boolean hasUploadDeviceConfigResponse;
    private boolean hasVerifyAssociationResponse;
    private CarrierBilling.InitiateAssociationResponse initiateAssociationResponse_ = null;
    private BuyInstruments.InstrumentSetupInfoResponse instrumentSetupInfoResponse_ = null;
    private LibraryReplication.LibraryReplicationResponse libraryReplicationResponse_ = null;
    private DocList.ListResponse listResponse_ = null;
    private Log.LogResponse logResponse_ = null;
    private ModifyLibrary.ModifyLibraryResponse modifyLibraryResponse_ = null;
    private PlusOne.PlusOneResponse plusOneResponse_ = null;
    private PlusProfile.PlusProfileResponse plusProfileResponse_ = null;
    private Buy.PurchaseStatusResponse purchaseStatusResponse_ = null;
    private RateSuggestedContentResponse rateSuggestedContentResponse_ = null;
    private BuyInstruments.RedeemGiftCardResponse redeemGiftCardResponse_ = null;
    private ResolveLink.ResolveLinkResponse resolveLinkResponse_ = null;
    private Rev.ReviewResponse reviewResponse_ = null;
    private RevokeResponse revokeResponse_ = null;
    private Search.SearchResponse searchResponse_ = null;
    private Toc.TocResponse tocResponse_ = null;
    private BuyInstruments.UpdateInstrumentResponse updateInstrumentResponse_ = null;
    private UploadDeviceConfig.UploadDeviceConfigResponse uploadDeviceConfigResponse_ = null;
    private CarrierBilling.VerifyAssociationResponse verifyAssociationResponse_ = null;

    public Tos.AcceptTosResponse getAcceptTosResponse()
    {
      return this.acceptTosResponse_;
    }

    public AckNotification.AckNotificationResponse getAckNotificationResponse()
    {
      return this.ackNotificationResponse_;
    }

    public Browse.BrowseResponse getBrowseResponse()
    {
      return this.browseResponse_;
    }

    public Details.BulkDetailsResponse getBulkDetailsResponse()
    {
      return this.bulkDetailsResponse_;
    }

    public Buy.BuyResponse getBuyResponse()
    {
      return this.buyResponse_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public BuyInstruments.CheckInstrumentResponse getCheckInstrumentResponse()
    {
      return this.checkInstrumentResponse_;
    }

    public CheckPromoOffer.CheckPromoOfferResponse getCheckPromoOfferResponse()
    {
      return this.checkPromoOfferResponse_;
    }

    public ConsumePurchaseResponse getConsumePurchaseResponse()
    {
      return this.consumePurchaseResponse_;
    }

    public Delivery.DeliveryResponse getDeliveryResponse()
    {
      return this.deliveryResponse_;
    }

    public Details.DetailsResponse getDetailsResponse()
    {
      return this.detailsResponse_;
    }

    public ContentFlagging.FlagContentResponse getFlagContentResponse()
    {
      return this.flagContentResponse_;
    }

    public CarrierBilling.InitiateAssociationResponse getInitiateAssociationResponse()
    {
      return this.initiateAssociationResponse_;
    }

    public BuyInstruments.InstrumentSetupInfoResponse getInstrumentSetupInfoResponse()
    {
      return this.instrumentSetupInfoResponse_;
    }

    public LibraryReplication.LibraryReplicationResponse getLibraryReplicationResponse()
    {
      return this.libraryReplicationResponse_;
    }

    public DocList.ListResponse getListResponse()
    {
      return this.listResponse_;
    }

    public Log.LogResponse getLogResponse()
    {
      return this.logResponse_;
    }

    public ModifyLibrary.ModifyLibraryResponse getModifyLibraryResponse()
    {
      return this.modifyLibraryResponse_;
    }

    public PlusOne.PlusOneResponse getPlusOneResponse()
    {
      return this.plusOneResponse_;
    }

    public PlusProfile.PlusProfileResponse getPlusProfileResponse()
    {
      return this.plusProfileResponse_;
    }

    public Buy.PurchaseStatusResponse getPurchaseStatusResponse()
    {
      return this.purchaseStatusResponse_;
    }

    public RateSuggestedContentResponse getRateSuggestedContentResponse()
    {
      return this.rateSuggestedContentResponse_;
    }

    public BuyInstruments.RedeemGiftCardResponse getRedeemGiftCardResponse()
    {
      return this.redeemGiftCardResponse_;
    }

    public ResolveLink.ResolveLinkResponse getResolveLinkResponse()
    {
      return this.resolveLinkResponse_;
    }

    public Rev.ReviewResponse getReviewResponse()
    {
      return this.reviewResponse_;
    }

    public RevokeResponse getRevokeResponse()
    {
      return this.revokeResponse_;
    }

    public Search.SearchResponse getSearchResponse()
    {
      return this.searchResponse_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasListResponse())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getListResponse());
      if (hasDetailsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDetailsResponse());
      if (hasReviewResponse())
        i += CodedOutputStreamMicro.computeMessageSize(3, getReviewResponse());
      if (hasBuyResponse())
        i += CodedOutputStreamMicro.computeMessageSize(4, getBuyResponse());
      if (hasSearchResponse())
        i += CodedOutputStreamMicro.computeMessageSize(5, getSearchResponse());
      if (hasTocResponse())
        i += CodedOutputStreamMicro.computeMessageSize(6, getTocResponse());
      if (hasBrowseResponse())
        i += CodedOutputStreamMicro.computeMessageSize(7, getBrowseResponse());
      if (hasPurchaseStatusResponse())
        i += CodedOutputStreamMicro.computeMessageSize(8, getPurchaseStatusResponse());
      if (hasUpdateInstrumentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(9, getUpdateInstrumentResponse());
      if (hasLogResponse())
        i += CodedOutputStreamMicro.computeMessageSize(10, getLogResponse());
      if (hasCheckInstrumentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(11, getCheckInstrumentResponse());
      if (hasPlusOneResponse())
        i += CodedOutputStreamMicro.computeMessageSize(12, getPlusOneResponse());
      if (hasFlagContentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(13, getFlagContentResponse());
      if (hasAckNotificationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(14, getAckNotificationResponse());
      if (hasInitiateAssociationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(15, getInitiateAssociationResponse());
      if (hasVerifyAssociationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(16, getVerifyAssociationResponse());
      if (hasLibraryReplicationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(17, getLibraryReplicationResponse());
      if (hasRevokeResponse())
        i += CodedOutputStreamMicro.computeMessageSize(18, getRevokeResponse());
      if (hasBulkDetailsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(19, getBulkDetailsResponse());
      if (hasResolveLinkResponse())
        i += CodedOutputStreamMicro.computeMessageSize(20, getResolveLinkResponse());
      if (hasDeliveryResponse())
        i += CodedOutputStreamMicro.computeMessageSize(21, getDeliveryResponse());
      if (hasAcceptTosResponse())
        i += CodedOutputStreamMicro.computeMessageSize(22, getAcceptTosResponse());
      if (hasRateSuggestedContentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(23, getRateSuggestedContentResponse());
      if (hasCheckPromoOfferResponse())
        i += CodedOutputStreamMicro.computeMessageSize(24, getCheckPromoOfferResponse());
      if (hasInstrumentSetupInfoResponse())
        i += CodedOutputStreamMicro.computeMessageSize(25, getInstrumentSetupInfoResponse());
      if (hasRedeemGiftCardResponse())
        i += CodedOutputStreamMicro.computeMessageSize(26, getRedeemGiftCardResponse());
      if (hasModifyLibraryResponse())
        i += CodedOutputStreamMicro.computeMessageSize(27, getModifyLibraryResponse());
      if (hasUploadDeviceConfigResponse())
        i += CodedOutputStreamMicro.computeMessageSize(28, getUploadDeviceConfigResponse());
      if (hasPlusProfileResponse())
        i += CodedOutputStreamMicro.computeMessageSize(29, getPlusProfileResponse());
      if (hasConsumePurchaseResponse())
        i += CodedOutputStreamMicro.computeMessageSize(30, getConsumePurchaseResponse());
      this.cachedSize = i;
      return i;
    }

    public Toc.TocResponse getTocResponse()
    {
      return this.tocResponse_;
    }

    public BuyInstruments.UpdateInstrumentResponse getUpdateInstrumentResponse()
    {
      return this.updateInstrumentResponse_;
    }

    public UploadDeviceConfig.UploadDeviceConfigResponse getUploadDeviceConfigResponse()
    {
      return this.uploadDeviceConfigResponse_;
    }

    public CarrierBilling.VerifyAssociationResponse getVerifyAssociationResponse()
    {
      return this.verifyAssociationResponse_;
    }

    public boolean hasAcceptTosResponse()
    {
      return this.hasAcceptTosResponse;
    }

    public boolean hasAckNotificationResponse()
    {
      return this.hasAckNotificationResponse;
    }

    public boolean hasBrowseResponse()
    {
      return this.hasBrowseResponse;
    }

    public boolean hasBulkDetailsResponse()
    {
      return this.hasBulkDetailsResponse;
    }

    public boolean hasBuyResponse()
    {
      return this.hasBuyResponse;
    }

    public boolean hasCheckInstrumentResponse()
    {
      return this.hasCheckInstrumentResponse;
    }

    public boolean hasCheckPromoOfferResponse()
    {
      return this.hasCheckPromoOfferResponse;
    }

    public boolean hasConsumePurchaseResponse()
    {
      return this.hasConsumePurchaseResponse;
    }

    public boolean hasDeliveryResponse()
    {
      return this.hasDeliveryResponse;
    }

    public boolean hasDetailsResponse()
    {
      return this.hasDetailsResponse;
    }

    public boolean hasFlagContentResponse()
    {
      return this.hasFlagContentResponse;
    }

    public boolean hasInitiateAssociationResponse()
    {
      return this.hasInitiateAssociationResponse;
    }

    public boolean hasInstrumentSetupInfoResponse()
    {
      return this.hasInstrumentSetupInfoResponse;
    }

    public boolean hasLibraryReplicationResponse()
    {
      return this.hasLibraryReplicationResponse;
    }

    public boolean hasListResponse()
    {
      return this.hasListResponse;
    }

    public boolean hasLogResponse()
    {
      return this.hasLogResponse;
    }

    public boolean hasModifyLibraryResponse()
    {
      return this.hasModifyLibraryResponse;
    }

    public boolean hasPlusOneResponse()
    {
      return this.hasPlusOneResponse;
    }

    public boolean hasPlusProfileResponse()
    {
      return this.hasPlusProfileResponse;
    }

    public boolean hasPurchaseStatusResponse()
    {
      return this.hasPurchaseStatusResponse;
    }

    public boolean hasRateSuggestedContentResponse()
    {
      return this.hasRateSuggestedContentResponse;
    }

    public boolean hasRedeemGiftCardResponse()
    {
      return this.hasRedeemGiftCardResponse;
    }

    public boolean hasResolveLinkResponse()
    {
      return this.hasResolveLinkResponse;
    }

    public boolean hasReviewResponse()
    {
      return this.hasReviewResponse;
    }

    public boolean hasRevokeResponse()
    {
      return this.hasRevokeResponse;
    }

    public boolean hasSearchResponse()
    {
      return this.hasSearchResponse;
    }

    public boolean hasTocResponse()
    {
      return this.hasTocResponse;
    }

    public boolean hasUpdateInstrumentResponse()
    {
      return this.hasUpdateInstrumentResponse;
    }

    public boolean hasUploadDeviceConfigResponse()
    {
      return this.hasUploadDeviceConfigResponse;
    }

    public boolean hasVerifyAssociationResponse()
    {
      return this.hasVerifyAssociationResponse;
    }

    public Payload mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocList.ListResponse localListResponse = new DocList.ListResponse();
          paramCodedInputStreamMicro.readMessage(localListResponse);
          setListResponse(localListResponse);
          break;
        case 18:
          Details.DetailsResponse localDetailsResponse = new Details.DetailsResponse();
          paramCodedInputStreamMicro.readMessage(localDetailsResponse);
          setDetailsResponse(localDetailsResponse);
          break;
        case 26:
          Rev.ReviewResponse localReviewResponse = new Rev.ReviewResponse();
          paramCodedInputStreamMicro.readMessage(localReviewResponse);
          setReviewResponse(localReviewResponse);
          break;
        case 34:
          Buy.BuyResponse localBuyResponse = new Buy.BuyResponse();
          paramCodedInputStreamMicro.readMessage(localBuyResponse);
          setBuyResponse(localBuyResponse);
          break;
        case 42:
          Search.SearchResponse localSearchResponse = new Search.SearchResponse();
          paramCodedInputStreamMicro.readMessage(localSearchResponse);
          setSearchResponse(localSearchResponse);
          break;
        case 50:
          Toc.TocResponse localTocResponse = new Toc.TocResponse();
          paramCodedInputStreamMicro.readMessage(localTocResponse);
          setTocResponse(localTocResponse);
          break;
        case 58:
          Browse.BrowseResponse localBrowseResponse = new Browse.BrowseResponse();
          paramCodedInputStreamMicro.readMessage(localBrowseResponse);
          setBrowseResponse(localBrowseResponse);
          break;
        case 66:
          Buy.PurchaseStatusResponse localPurchaseStatusResponse = new Buy.PurchaseStatusResponse();
          paramCodedInputStreamMicro.readMessage(localPurchaseStatusResponse);
          setPurchaseStatusResponse(localPurchaseStatusResponse);
          break;
        case 74:
          BuyInstruments.UpdateInstrumentResponse localUpdateInstrumentResponse = new BuyInstruments.UpdateInstrumentResponse();
          paramCodedInputStreamMicro.readMessage(localUpdateInstrumentResponse);
          setUpdateInstrumentResponse(localUpdateInstrumentResponse);
          break;
        case 82:
          Log.LogResponse localLogResponse = new Log.LogResponse();
          paramCodedInputStreamMicro.readMessage(localLogResponse);
          setLogResponse(localLogResponse);
          break;
        case 90:
          BuyInstruments.CheckInstrumentResponse localCheckInstrumentResponse = new BuyInstruments.CheckInstrumentResponse();
          paramCodedInputStreamMicro.readMessage(localCheckInstrumentResponse);
          setCheckInstrumentResponse(localCheckInstrumentResponse);
          break;
        case 98:
          PlusOne.PlusOneResponse localPlusOneResponse = new PlusOne.PlusOneResponse();
          paramCodedInputStreamMicro.readMessage(localPlusOneResponse);
          setPlusOneResponse(localPlusOneResponse);
          break;
        case 106:
          ContentFlagging.FlagContentResponse localFlagContentResponse = new ContentFlagging.FlagContentResponse();
          paramCodedInputStreamMicro.readMessage(localFlagContentResponse);
          setFlagContentResponse(localFlagContentResponse);
          break;
        case 114:
          AckNotification.AckNotificationResponse localAckNotificationResponse = new AckNotification.AckNotificationResponse();
          paramCodedInputStreamMicro.readMessage(localAckNotificationResponse);
          setAckNotificationResponse(localAckNotificationResponse);
          break;
        case 122:
          CarrierBilling.InitiateAssociationResponse localInitiateAssociationResponse = new CarrierBilling.InitiateAssociationResponse();
          paramCodedInputStreamMicro.readMessage(localInitiateAssociationResponse);
          setInitiateAssociationResponse(localInitiateAssociationResponse);
          break;
        case 130:
          CarrierBilling.VerifyAssociationResponse localVerifyAssociationResponse = new CarrierBilling.VerifyAssociationResponse();
          paramCodedInputStreamMicro.readMessage(localVerifyAssociationResponse);
          setVerifyAssociationResponse(localVerifyAssociationResponse);
          break;
        case 138:
          LibraryReplication.LibraryReplicationResponse localLibraryReplicationResponse = new LibraryReplication.LibraryReplicationResponse();
          paramCodedInputStreamMicro.readMessage(localLibraryReplicationResponse);
          setLibraryReplicationResponse(localLibraryReplicationResponse);
          break;
        case 146:
          RevokeResponse localRevokeResponse = new RevokeResponse();
          paramCodedInputStreamMicro.readMessage(localRevokeResponse);
          setRevokeResponse(localRevokeResponse);
          break;
        case 154:
          Details.BulkDetailsResponse localBulkDetailsResponse = new Details.BulkDetailsResponse();
          paramCodedInputStreamMicro.readMessage(localBulkDetailsResponse);
          setBulkDetailsResponse(localBulkDetailsResponse);
          break;
        case 162:
          ResolveLink.ResolveLinkResponse localResolveLinkResponse = new ResolveLink.ResolveLinkResponse();
          paramCodedInputStreamMicro.readMessage(localResolveLinkResponse);
          setResolveLinkResponse(localResolveLinkResponse);
          break;
        case 170:
          Delivery.DeliveryResponse localDeliveryResponse = new Delivery.DeliveryResponse();
          paramCodedInputStreamMicro.readMessage(localDeliveryResponse);
          setDeliveryResponse(localDeliveryResponse);
          break;
        case 178:
          Tos.AcceptTosResponse localAcceptTosResponse = new Tos.AcceptTosResponse();
          paramCodedInputStreamMicro.readMessage(localAcceptTosResponse);
          setAcceptTosResponse(localAcceptTosResponse);
          break;
        case 186:
          RateSuggestedContentResponse localRateSuggestedContentResponse = new RateSuggestedContentResponse();
          paramCodedInputStreamMicro.readMessage(localRateSuggestedContentResponse);
          setRateSuggestedContentResponse(localRateSuggestedContentResponse);
          break;
        case 194:
          CheckPromoOffer.CheckPromoOfferResponse localCheckPromoOfferResponse = new CheckPromoOffer.CheckPromoOfferResponse();
          paramCodedInputStreamMicro.readMessage(localCheckPromoOfferResponse);
          setCheckPromoOfferResponse(localCheckPromoOfferResponse);
          break;
        case 202:
          BuyInstruments.InstrumentSetupInfoResponse localInstrumentSetupInfoResponse = new BuyInstruments.InstrumentSetupInfoResponse();
          paramCodedInputStreamMicro.readMessage(localInstrumentSetupInfoResponse);
          setInstrumentSetupInfoResponse(localInstrumentSetupInfoResponse);
          break;
        case 210:
          BuyInstruments.RedeemGiftCardResponse localRedeemGiftCardResponse = new BuyInstruments.RedeemGiftCardResponse();
          paramCodedInputStreamMicro.readMessage(localRedeemGiftCardResponse);
          setRedeemGiftCardResponse(localRedeemGiftCardResponse);
          break;
        case 218:
          ModifyLibrary.ModifyLibraryResponse localModifyLibraryResponse = new ModifyLibrary.ModifyLibraryResponse();
          paramCodedInputStreamMicro.readMessage(localModifyLibraryResponse);
          setModifyLibraryResponse(localModifyLibraryResponse);
          break;
        case 226:
          UploadDeviceConfig.UploadDeviceConfigResponse localUploadDeviceConfigResponse = new UploadDeviceConfig.UploadDeviceConfigResponse();
          paramCodedInputStreamMicro.readMessage(localUploadDeviceConfigResponse);
          setUploadDeviceConfigResponse(localUploadDeviceConfigResponse);
          break;
        case 234:
          PlusProfile.PlusProfileResponse localPlusProfileResponse = new PlusProfile.PlusProfileResponse();
          paramCodedInputStreamMicro.readMessage(localPlusProfileResponse);
          setPlusProfileResponse(localPlusProfileResponse);
          break;
        case 242:
        }
        ConsumePurchaseResponse localConsumePurchaseResponse = new ConsumePurchaseResponse();
        paramCodedInputStreamMicro.readMessage(localConsumePurchaseResponse);
        setConsumePurchaseResponse(localConsumePurchaseResponse);
      }
    }

    public Payload setAcceptTosResponse(Tos.AcceptTosResponse paramAcceptTosResponse)
    {
      if (paramAcceptTosResponse == null)
        throw new NullPointerException();
      this.hasAcceptTosResponse = true;
      this.acceptTosResponse_ = paramAcceptTosResponse;
      return this;
    }

    public Payload setAckNotificationResponse(AckNotification.AckNotificationResponse paramAckNotificationResponse)
    {
      if (paramAckNotificationResponse == null)
        throw new NullPointerException();
      this.hasAckNotificationResponse = true;
      this.ackNotificationResponse_ = paramAckNotificationResponse;
      return this;
    }

    public Payload setBrowseResponse(Browse.BrowseResponse paramBrowseResponse)
    {
      if (paramBrowseResponse == null)
        throw new NullPointerException();
      this.hasBrowseResponse = true;
      this.browseResponse_ = paramBrowseResponse;
      return this;
    }

    public Payload setBulkDetailsResponse(Details.BulkDetailsResponse paramBulkDetailsResponse)
    {
      if (paramBulkDetailsResponse == null)
        throw new NullPointerException();
      this.hasBulkDetailsResponse = true;
      this.bulkDetailsResponse_ = paramBulkDetailsResponse;
      return this;
    }

    public Payload setBuyResponse(Buy.BuyResponse paramBuyResponse)
    {
      if (paramBuyResponse == null)
        throw new NullPointerException();
      this.hasBuyResponse = true;
      this.buyResponse_ = paramBuyResponse;
      return this;
    }

    public Payload setCheckInstrumentResponse(BuyInstruments.CheckInstrumentResponse paramCheckInstrumentResponse)
    {
      if (paramCheckInstrumentResponse == null)
        throw new NullPointerException();
      this.hasCheckInstrumentResponse = true;
      this.checkInstrumentResponse_ = paramCheckInstrumentResponse;
      return this;
    }

    public Payload setCheckPromoOfferResponse(CheckPromoOffer.CheckPromoOfferResponse paramCheckPromoOfferResponse)
    {
      if (paramCheckPromoOfferResponse == null)
        throw new NullPointerException();
      this.hasCheckPromoOfferResponse = true;
      this.checkPromoOfferResponse_ = paramCheckPromoOfferResponse;
      return this;
    }

    public Payload setConsumePurchaseResponse(ConsumePurchaseResponse paramConsumePurchaseResponse)
    {
      if (paramConsumePurchaseResponse == null)
        throw new NullPointerException();
      this.hasConsumePurchaseResponse = true;
      this.consumePurchaseResponse_ = paramConsumePurchaseResponse;
      return this;
    }

    public Payload setDeliveryResponse(Delivery.DeliveryResponse paramDeliveryResponse)
    {
      if (paramDeliveryResponse == null)
        throw new NullPointerException();
      this.hasDeliveryResponse = true;
      this.deliveryResponse_ = paramDeliveryResponse;
      return this;
    }

    public Payload setDetailsResponse(Details.DetailsResponse paramDetailsResponse)
    {
      if (paramDetailsResponse == null)
        throw new NullPointerException();
      this.hasDetailsResponse = true;
      this.detailsResponse_ = paramDetailsResponse;
      return this;
    }

    public Payload setFlagContentResponse(ContentFlagging.FlagContentResponse paramFlagContentResponse)
    {
      if (paramFlagContentResponse == null)
        throw new NullPointerException();
      this.hasFlagContentResponse = true;
      this.flagContentResponse_ = paramFlagContentResponse;
      return this;
    }

    public Payload setInitiateAssociationResponse(CarrierBilling.InitiateAssociationResponse paramInitiateAssociationResponse)
    {
      if (paramInitiateAssociationResponse == null)
        throw new NullPointerException();
      this.hasInitiateAssociationResponse = true;
      this.initiateAssociationResponse_ = paramInitiateAssociationResponse;
      return this;
    }

    public Payload setInstrumentSetupInfoResponse(BuyInstruments.InstrumentSetupInfoResponse paramInstrumentSetupInfoResponse)
    {
      if (paramInstrumentSetupInfoResponse == null)
        throw new NullPointerException();
      this.hasInstrumentSetupInfoResponse = true;
      this.instrumentSetupInfoResponse_ = paramInstrumentSetupInfoResponse;
      return this;
    }

    public Payload setLibraryReplicationResponse(LibraryReplication.LibraryReplicationResponse paramLibraryReplicationResponse)
    {
      if (paramLibraryReplicationResponse == null)
        throw new NullPointerException();
      this.hasLibraryReplicationResponse = true;
      this.libraryReplicationResponse_ = paramLibraryReplicationResponse;
      return this;
    }

    public Payload setListResponse(DocList.ListResponse paramListResponse)
    {
      if (paramListResponse == null)
        throw new NullPointerException();
      this.hasListResponse = true;
      this.listResponse_ = paramListResponse;
      return this;
    }

    public Payload setLogResponse(Log.LogResponse paramLogResponse)
    {
      if (paramLogResponse == null)
        throw new NullPointerException();
      this.hasLogResponse = true;
      this.logResponse_ = paramLogResponse;
      return this;
    }

    public Payload setModifyLibraryResponse(ModifyLibrary.ModifyLibraryResponse paramModifyLibraryResponse)
    {
      if (paramModifyLibraryResponse == null)
        throw new NullPointerException();
      this.hasModifyLibraryResponse = true;
      this.modifyLibraryResponse_ = paramModifyLibraryResponse;
      return this;
    }

    public Payload setPlusOneResponse(PlusOne.PlusOneResponse paramPlusOneResponse)
    {
      if (paramPlusOneResponse == null)
        throw new NullPointerException();
      this.hasPlusOneResponse = true;
      this.plusOneResponse_ = paramPlusOneResponse;
      return this;
    }

    public Payload setPlusProfileResponse(PlusProfile.PlusProfileResponse paramPlusProfileResponse)
    {
      if (paramPlusProfileResponse == null)
        throw new NullPointerException();
      this.hasPlusProfileResponse = true;
      this.plusProfileResponse_ = paramPlusProfileResponse;
      return this;
    }

    public Payload setPurchaseStatusResponse(Buy.PurchaseStatusResponse paramPurchaseStatusResponse)
    {
      if (paramPurchaseStatusResponse == null)
        throw new NullPointerException();
      this.hasPurchaseStatusResponse = true;
      this.purchaseStatusResponse_ = paramPurchaseStatusResponse;
      return this;
    }

    public Payload setRateSuggestedContentResponse(RateSuggestedContentResponse paramRateSuggestedContentResponse)
    {
      if (paramRateSuggestedContentResponse == null)
        throw new NullPointerException();
      this.hasRateSuggestedContentResponse = true;
      this.rateSuggestedContentResponse_ = paramRateSuggestedContentResponse;
      return this;
    }

    public Payload setRedeemGiftCardResponse(BuyInstruments.RedeemGiftCardResponse paramRedeemGiftCardResponse)
    {
      if (paramRedeemGiftCardResponse == null)
        throw new NullPointerException();
      this.hasRedeemGiftCardResponse = true;
      this.redeemGiftCardResponse_ = paramRedeemGiftCardResponse;
      return this;
    }

    public Payload setResolveLinkResponse(ResolveLink.ResolveLinkResponse paramResolveLinkResponse)
    {
      if (paramResolveLinkResponse == null)
        throw new NullPointerException();
      this.hasResolveLinkResponse = true;
      this.resolveLinkResponse_ = paramResolveLinkResponse;
      return this;
    }

    public Payload setReviewResponse(Rev.ReviewResponse paramReviewResponse)
    {
      if (paramReviewResponse == null)
        throw new NullPointerException();
      this.hasReviewResponse = true;
      this.reviewResponse_ = paramReviewResponse;
      return this;
    }

    public Payload setRevokeResponse(RevokeResponse paramRevokeResponse)
    {
      if (paramRevokeResponse == null)
        throw new NullPointerException();
      this.hasRevokeResponse = true;
      this.revokeResponse_ = paramRevokeResponse;
      return this;
    }

    public Payload setSearchResponse(Search.SearchResponse paramSearchResponse)
    {
      if (paramSearchResponse == null)
        throw new NullPointerException();
      this.hasSearchResponse = true;
      this.searchResponse_ = paramSearchResponse;
      return this;
    }

    public Payload setTocResponse(Toc.TocResponse paramTocResponse)
    {
      if (paramTocResponse == null)
        throw new NullPointerException();
      this.hasTocResponse = true;
      this.tocResponse_ = paramTocResponse;
      return this;
    }

    public Payload setUpdateInstrumentResponse(BuyInstruments.UpdateInstrumentResponse paramUpdateInstrumentResponse)
    {
      if (paramUpdateInstrumentResponse == null)
        throw new NullPointerException();
      this.hasUpdateInstrumentResponse = true;
      this.updateInstrumentResponse_ = paramUpdateInstrumentResponse;
      return this;
    }

    public Payload setUploadDeviceConfigResponse(UploadDeviceConfig.UploadDeviceConfigResponse paramUploadDeviceConfigResponse)
    {
      if (paramUploadDeviceConfigResponse == null)
        throw new NullPointerException();
      this.hasUploadDeviceConfigResponse = true;
      this.uploadDeviceConfigResponse_ = paramUploadDeviceConfigResponse;
      return this;
    }

    public Payload setVerifyAssociationResponse(CarrierBilling.VerifyAssociationResponse paramVerifyAssociationResponse)
    {
      if (paramVerifyAssociationResponse == null)
        throw new NullPointerException();
      this.hasVerifyAssociationResponse = true;
      this.verifyAssociationResponse_ = paramVerifyAssociationResponse;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasListResponse())
        paramCodedOutputStreamMicro.writeMessage(1, getListResponse());
      if (hasDetailsResponse())
        paramCodedOutputStreamMicro.writeMessage(2, getDetailsResponse());
      if (hasReviewResponse())
        paramCodedOutputStreamMicro.writeMessage(3, getReviewResponse());
      if (hasBuyResponse())
        paramCodedOutputStreamMicro.writeMessage(4, getBuyResponse());
      if (hasSearchResponse())
        paramCodedOutputStreamMicro.writeMessage(5, getSearchResponse());
      if (hasTocResponse())
        paramCodedOutputStreamMicro.writeMessage(6, getTocResponse());
      if (hasBrowseResponse())
        paramCodedOutputStreamMicro.writeMessage(7, getBrowseResponse());
      if (hasPurchaseStatusResponse())
        paramCodedOutputStreamMicro.writeMessage(8, getPurchaseStatusResponse());
      if (hasUpdateInstrumentResponse())
        paramCodedOutputStreamMicro.writeMessage(9, getUpdateInstrumentResponse());
      if (hasLogResponse())
        paramCodedOutputStreamMicro.writeMessage(10, getLogResponse());
      if (hasCheckInstrumentResponse())
        paramCodedOutputStreamMicro.writeMessage(11, getCheckInstrumentResponse());
      if (hasPlusOneResponse())
        paramCodedOutputStreamMicro.writeMessage(12, getPlusOneResponse());
      if (hasFlagContentResponse())
        paramCodedOutputStreamMicro.writeMessage(13, getFlagContentResponse());
      if (hasAckNotificationResponse())
        paramCodedOutputStreamMicro.writeMessage(14, getAckNotificationResponse());
      if (hasInitiateAssociationResponse())
        paramCodedOutputStreamMicro.writeMessage(15, getInitiateAssociationResponse());
      if (hasVerifyAssociationResponse())
        paramCodedOutputStreamMicro.writeMessage(16, getVerifyAssociationResponse());
      if (hasLibraryReplicationResponse())
        paramCodedOutputStreamMicro.writeMessage(17, getLibraryReplicationResponse());
      if (hasRevokeResponse())
        paramCodedOutputStreamMicro.writeMessage(18, getRevokeResponse());
      if (hasBulkDetailsResponse())
        paramCodedOutputStreamMicro.writeMessage(19, getBulkDetailsResponse());
      if (hasResolveLinkResponse())
        paramCodedOutputStreamMicro.writeMessage(20, getResolveLinkResponse());
      if (hasDeliveryResponse())
        paramCodedOutputStreamMicro.writeMessage(21, getDeliveryResponse());
      if (hasAcceptTosResponse())
        paramCodedOutputStreamMicro.writeMessage(22, getAcceptTosResponse());
      if (hasRateSuggestedContentResponse())
        paramCodedOutputStreamMicro.writeMessage(23, getRateSuggestedContentResponse());
      if (hasCheckPromoOfferResponse())
        paramCodedOutputStreamMicro.writeMessage(24, getCheckPromoOfferResponse());
      if (hasInstrumentSetupInfoResponse())
        paramCodedOutputStreamMicro.writeMessage(25, getInstrumentSetupInfoResponse());
      if (hasRedeemGiftCardResponse())
        paramCodedOutputStreamMicro.writeMessage(26, getRedeemGiftCardResponse());
      if (hasModifyLibraryResponse())
        paramCodedOutputStreamMicro.writeMessage(27, getModifyLibraryResponse());
      if (hasUploadDeviceConfigResponse())
        paramCodedOutputStreamMicro.writeMessage(28, getUploadDeviceConfigResponse());
      if (hasPlusProfileResponse())
        paramCodedOutputStreamMicro.writeMessage(29, getPlusProfileResponse());
      if (hasConsumePurchaseResponse())
        paramCodedOutputStreamMicro.writeMessage(30, getConsumePurchaseResponse());
    }
  }

  public static final class PreFetch extends MessageMicro
  {
    private int cachedSize = -1;
    private String etag_ = "";
    private boolean hasEtag;
    private boolean hasResponse;
    private boolean hasSoftTtl;
    private boolean hasTtl;
    private boolean hasUrl;
    private ByteStringMicro response_ = ByteStringMicro.EMPTY;
    private long softTtl_ = 0L;
    private long ttl_ = 0L;
    private String url_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getEtag()
    {
      return this.etag_;
    }

    public ByteStringMicro getResponse()
    {
      return this.response_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUrl());
      if (hasResponse())
        i += CodedOutputStreamMicro.computeBytesSize(2, getResponse());
      if (hasEtag())
        i += CodedOutputStreamMicro.computeStringSize(3, getEtag());
      if (hasTtl())
        i += CodedOutputStreamMicro.computeInt64Size(4, getTtl());
      if (hasSoftTtl())
        i += CodedOutputStreamMicro.computeInt64Size(5, getSoftTtl());
      this.cachedSize = i;
      return i;
    }

    public long getSoftTtl()
    {
      return this.softTtl_;
    }

    public long getTtl()
    {
      return this.ttl_;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public boolean hasEtag()
    {
      return this.hasEtag;
    }

    public boolean hasResponse()
    {
      return this.hasResponse;
    }

    public boolean hasSoftTtl()
    {
      return this.hasSoftTtl;
    }

    public boolean hasTtl()
    {
      return this.hasTtl;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public PreFetch mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResponse(paramCodedInputStreamMicro.readBytes());
          break;
        case 26:
          setEtag(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setTtl(paramCodedInputStreamMicro.readInt64());
          break;
        case 40:
        }
        setSoftTtl(paramCodedInputStreamMicro.readInt64());
      }
    }

    public PreFetch setEtag(String paramString)
    {
      this.hasEtag = true;
      this.etag_ = paramString;
      return this;
    }

    public PreFetch setResponse(ByteStringMicro paramByteStringMicro)
    {
      this.hasResponse = true;
      this.response_ = paramByteStringMicro;
      return this;
    }

    public PreFetch setSoftTtl(long paramLong)
    {
      this.hasSoftTtl = true;
      this.softTtl_ = paramLong;
      return this;
    }

    public PreFetch setTtl(long paramLong)
    {
      this.hasTtl = true;
      this.ttl_ = paramLong;
      return this;
    }

    public PreFetch setUrl(String paramString)
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
      if (hasResponse())
        paramCodedOutputStreamMicro.writeBytes(2, getResponse());
      if (hasEtag())
        paramCodedOutputStreamMicro.writeString(3, getEtag());
      if (hasTtl())
        paramCodedOutputStreamMicro.writeInt64(4, getTtl());
      if (hasSoftTtl())
        paramCodedOutputStreamMicro.writeInt64(5, getSoftTtl());
    }
  }

  public static final class ResponseWrapper extends MessageMicro
  {
    private int cachedSize = -1;
    private Response.ServerCommands commands_ = null;
    private boolean hasCommands;
    private boolean hasPayload;
    private List<Notifications.Notification> notification_ = Collections.emptyList();
    private Response.Payload payload_ = null;
    private List<Response.PreFetch> preFetch_ = Collections.emptyList();

    public static ResponseWrapper parseFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      return new ResponseWrapper().mergeFrom(paramCodedInputStreamMicro);
    }

    public static ResponseWrapper parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferMicroException
    {
      return (ResponseWrapper)new ResponseWrapper().mergeFrom(paramArrayOfByte);
    }

    public ResponseWrapper addNotification(Notifications.Notification paramNotification)
    {
      if (paramNotification == null)
        throw new NullPointerException();
      if (this.notification_.isEmpty())
        this.notification_ = new ArrayList();
      this.notification_.add(paramNotification);
      return this;
    }

    public ResponseWrapper addPreFetch(Response.PreFetch paramPreFetch)
    {
      if (paramPreFetch == null)
        throw new NullPointerException();
      if (this.preFetch_.isEmpty())
        this.preFetch_ = new ArrayList();
      this.preFetch_.add(paramPreFetch);
      return this;
    }

    public ResponseWrapper clearCommands()
    {
      this.hasCommands = false;
      this.commands_ = null;
      return this;
    }

    public ResponseWrapper clearNotification()
    {
      this.notification_ = Collections.emptyList();
      return this;
    }

    public ResponseWrapper clearPreFetch()
    {
      this.preFetch_ = Collections.emptyList();
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Response.ServerCommands getCommands()
    {
      return this.commands_;
    }

    public int getNotificationCount()
    {
      return this.notification_.size();
    }

    public List<Notifications.Notification> getNotificationList()
    {
      return this.notification_;
    }

    public Response.Payload getPayload()
    {
      return this.payload_;
    }

    public int getPreFetchCount()
    {
      return this.preFetch_.size();
    }

    public List<Response.PreFetch> getPreFetchList()
    {
      return this.preFetch_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPayload())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getPayload());
      if (hasCommands())
        i += CodedOutputStreamMicro.computeMessageSize(2, getCommands());
      Iterator localIterator1 = getPreFetchList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (Response.PreFetch)localIterator1.next());
      Iterator localIterator2 = getNotificationList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (Notifications.Notification)localIterator2.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCommands()
    {
      return this.hasCommands;
    }

    public boolean hasPayload()
    {
      return this.hasPayload;
    }

    public ResponseWrapper mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Response.Payload localPayload = new Response.Payload();
          paramCodedInputStreamMicro.readMessage(localPayload);
          setPayload(localPayload);
          break;
        case 18:
          Response.ServerCommands localServerCommands = new Response.ServerCommands();
          paramCodedInputStreamMicro.readMessage(localServerCommands);
          setCommands(localServerCommands);
          break;
        case 26:
          Response.PreFetch localPreFetch = new Response.PreFetch();
          paramCodedInputStreamMicro.readMessage(localPreFetch);
          addPreFetch(localPreFetch);
          break;
        case 34:
        }
        Notifications.Notification localNotification = new Notifications.Notification();
        paramCodedInputStreamMicro.readMessage(localNotification);
        addNotification(localNotification);
      }
    }

    public ResponseWrapper setCommands(Response.ServerCommands paramServerCommands)
    {
      if (paramServerCommands == null)
        throw new NullPointerException();
      this.hasCommands = true;
      this.commands_ = paramServerCommands;
      return this;
    }

    public ResponseWrapper setPayload(Response.Payload paramPayload)
    {
      if (paramPayload == null)
        throw new NullPointerException();
      this.hasPayload = true;
      this.payload_ = paramPayload;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPayload())
        paramCodedOutputStreamMicro.writeMessage(1, getPayload());
      if (hasCommands())
        paramCodedOutputStreamMicro.writeMessage(2, getCommands());
      Iterator localIterator1 = getPreFetchList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (Response.PreFetch)localIterator1.next());
      Iterator localIterator2 = getNotificationList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (Notifications.Notification)localIterator2.next());
    }
  }

  public static final class ServerCommands extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean clearCache_ = false;
    private String displayErrorMessage_ = "";
    private boolean hasClearCache;
    private boolean hasDisplayErrorMessage;
    private boolean hasLogErrorStacktrace;
    private String logErrorStacktrace_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getClearCache()
    {
      return this.clearCache_;
    }

    public String getDisplayErrorMessage()
    {
      return this.displayErrorMessage_;
    }

    public String getLogErrorStacktrace()
    {
      return this.logErrorStacktrace_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasClearCache())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getClearCache());
      if (hasDisplayErrorMessage())
        i += CodedOutputStreamMicro.computeStringSize(2, getDisplayErrorMessage());
      if (hasLogErrorStacktrace())
        i += CodedOutputStreamMicro.computeStringSize(3, getLogErrorStacktrace());
      this.cachedSize = i;
      return i;
    }

    public boolean hasClearCache()
    {
      return this.hasClearCache;
    }

    public boolean hasDisplayErrorMessage()
    {
      return this.hasDisplayErrorMessage;
    }

    public boolean hasLogErrorStacktrace()
    {
      return this.hasLogErrorStacktrace;
    }

    public ServerCommands mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setClearCache(paramCodedInputStreamMicro.readBool());
          break;
        case 18:
          setDisplayErrorMessage(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setLogErrorStacktrace(paramCodedInputStreamMicro.readString());
      }
    }

    public ServerCommands setClearCache(boolean paramBoolean)
    {
      this.hasClearCache = true;
      this.clearCache_ = paramBoolean;
      return this;
    }

    public ServerCommands setDisplayErrorMessage(String paramString)
    {
      this.hasDisplayErrorMessage = true;
      this.displayErrorMessage_ = paramString;
      return this;
    }

    public ServerCommands setLogErrorStacktrace(String paramString)
    {
      this.hasLogErrorStacktrace = true;
      this.logErrorStacktrace_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasClearCache())
        paramCodedOutputStreamMicro.writeBool(1, getClearCache());
      if (hasDisplayErrorMessage())
        paramCodedOutputStreamMicro.writeString(2, getDisplayErrorMessage());
      if (hasLogErrorStacktrace())
        paramCodedOutputStreamMicro.writeString(3, getLogErrorStacktrace());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Response
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DocumentV2
{
  public static final class Annotations extends MessageMicro
  {
    private List<DocAnnotations.Badge> badgeForCreator_ = Collections.emptyList();
    private List<DocAnnotations.Badge> badgeForDoc_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean hasLink;
    private boolean hasOfferNote;
    private boolean hasPlusOneData;
    private boolean hasPrivacyPolicyUrl;
    private boolean hasReason;
    private boolean hasSectionBodyOfWork;
    private boolean hasSectionCoreContent;
    private boolean hasSectionCrossSell;
    private boolean hasSectionMoreBy;
    private boolean hasSectionRelated;
    private boolean hasSectionRelatedDocType;
    private boolean hasTemplate;
    private DocAnnotations.Link link_ = null;
    private String offerNote_ = "";
    private DocAnnotations.PlusOneData plusOneData_ = null;
    private String privacyPolicyUrl_ = "";
    private List<DocAnnotations.PromotedDoc> promotedDoc_ = Collections.emptyList();
    private DocAnnotations.Reason reason_ = null;
    private DocAnnotations.SectionMetadata sectionBodyOfWork_ = null;
    private DocAnnotations.SectionMetadata sectionCoreContent_ = null;
    private DocAnnotations.SectionMetadata sectionCrossSell_ = null;
    private DocAnnotations.SectionMetadata sectionMoreBy_ = null;
    private DocAnnotations.SectionMetadata sectionRelatedDocType_ = null;
    private DocAnnotations.SectionMetadata sectionRelated_ = null;
    private List<DocumentV2.DocV2> subscription_ = Collections.emptyList();
    private DocAnnotations.Template template_ = null;
    private List<DocAnnotations.Warning> warning_ = Collections.emptyList();

    public Annotations addBadgeForCreator(DocAnnotations.Badge paramBadge)
    {
      if (paramBadge == null)
        throw new NullPointerException();
      if (this.badgeForCreator_.isEmpty())
        this.badgeForCreator_ = new ArrayList();
      this.badgeForCreator_.add(paramBadge);
      return this;
    }

    public Annotations addBadgeForDoc(DocAnnotations.Badge paramBadge)
    {
      if (paramBadge == null)
        throw new NullPointerException();
      if (this.badgeForDoc_.isEmpty())
        this.badgeForDoc_ = new ArrayList();
      this.badgeForDoc_.add(paramBadge);
      return this;
    }

    public Annotations addPromotedDoc(DocAnnotations.PromotedDoc paramPromotedDoc)
    {
      if (paramPromotedDoc == null)
        throw new NullPointerException();
      if (this.promotedDoc_.isEmpty())
        this.promotedDoc_ = new ArrayList();
      this.promotedDoc_.add(paramPromotedDoc);
      return this;
    }

    public Annotations addSubscription(DocumentV2.DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      if (this.subscription_.isEmpty())
        this.subscription_ = new ArrayList();
      this.subscription_.add(paramDocV2);
      return this;
    }

    public Annotations addWarning(DocAnnotations.Warning paramWarning)
    {
      if (paramWarning == null)
        throw new NullPointerException();
      if (this.warning_.isEmpty())
        this.warning_ = new ArrayList();
      this.warning_.add(paramWarning);
      return this;
    }

    public DocAnnotations.Badge getBadgeForCreator(int paramInt)
    {
      return (DocAnnotations.Badge)this.badgeForCreator_.get(paramInt);
    }

    public int getBadgeForCreatorCount()
    {
      return this.badgeForCreator_.size();
    }

    public List<DocAnnotations.Badge> getBadgeForCreatorList()
    {
      return this.badgeForCreator_;
    }

    public DocAnnotations.Badge getBadgeForDoc(int paramInt)
    {
      return (DocAnnotations.Badge)this.badgeForDoc_.get(paramInt);
    }

    public int getBadgeForDocCount()
    {
      return this.badgeForDoc_.size();
    }

    public List<DocAnnotations.Badge> getBadgeForDocList()
    {
      return this.badgeForDoc_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocAnnotations.Link getLink()
    {
      return this.link_;
    }

    public String getOfferNote()
    {
      return this.offerNote_;
    }

    public DocAnnotations.PlusOneData getPlusOneData()
    {
      return this.plusOneData_;
    }

    public String getPrivacyPolicyUrl()
    {
      return this.privacyPolicyUrl_;
    }

    public List<DocAnnotations.PromotedDoc> getPromotedDocList()
    {
      return this.promotedDoc_;
    }

    public DocAnnotations.Reason getReason()
    {
      return this.reason_;
    }

    public DocAnnotations.SectionMetadata getSectionBodyOfWork()
    {
      return this.sectionBodyOfWork_;
    }

    public DocAnnotations.SectionMetadata getSectionCoreContent()
    {
      return this.sectionCoreContent_;
    }

    public DocAnnotations.SectionMetadata getSectionCrossSell()
    {
      return this.sectionCrossSell_;
    }

    public DocAnnotations.SectionMetadata getSectionMoreBy()
    {
      return this.sectionMoreBy_;
    }

    public DocAnnotations.SectionMetadata getSectionRelated()
    {
      return this.sectionRelated_;
    }

    public DocAnnotations.SectionMetadata getSectionRelatedDocType()
    {
      return this.sectionRelatedDocType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSectionRelated())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSectionRelated());
      if (hasSectionMoreBy())
        i += CodedOutputStreamMicro.computeMessageSize(2, getSectionMoreBy());
      if (hasPlusOneData())
        i += CodedOutputStreamMicro.computeMessageSize(3, getPlusOneData());
      Iterator localIterator1 = getWarningList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (DocAnnotations.Warning)localIterator1.next());
      if (hasSectionBodyOfWork())
        i += CodedOutputStreamMicro.computeMessageSize(5, getSectionBodyOfWork());
      if (hasSectionCoreContent())
        i += CodedOutputStreamMicro.computeMessageSize(6, getSectionCoreContent());
      if (hasTemplate())
        i += CodedOutputStreamMicro.computeMessageSize(7, getTemplate());
      Iterator localIterator2 = getBadgeForCreatorList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(8, (DocAnnotations.Badge)localIterator2.next());
      Iterator localIterator3 = getBadgeForDocList().iterator();
      while (localIterator3.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(9, (DocAnnotations.Badge)localIterator3.next());
      if (hasLink())
        i += CodedOutputStreamMicro.computeMessageSize(10, getLink());
      if (hasSectionCrossSell())
        i += CodedOutputStreamMicro.computeMessageSize(11, getSectionCrossSell());
      if (hasSectionRelatedDocType())
        i += CodedOutputStreamMicro.computeMessageSize(12, getSectionRelatedDocType());
      Iterator localIterator4 = getPromotedDocList().iterator();
      while (localIterator4.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(13, (DocAnnotations.PromotedDoc)localIterator4.next());
      if (hasOfferNote())
        i += CodedOutputStreamMicro.computeStringSize(14, getOfferNote());
      Iterator localIterator5 = getSubscriptionList().iterator();
      while (localIterator5.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(16, (DocumentV2.DocV2)localIterator5.next());
      if (hasReason())
        i += CodedOutputStreamMicro.computeMessageSize(17, getReason());
      if (hasPrivacyPolicyUrl())
        i += CodedOutputStreamMicro.computeStringSize(18, getPrivacyPolicyUrl());
      this.cachedSize = i;
      return i;
    }

    public int getSubscriptionCount()
    {
      return this.subscription_.size();
    }

    public List<DocumentV2.DocV2> getSubscriptionList()
    {
      return this.subscription_;
    }

    public DocAnnotations.Template getTemplate()
    {
      return this.template_;
    }

    public DocAnnotations.Warning getWarning(int paramInt)
    {
      return (DocAnnotations.Warning)this.warning_.get(paramInt);
    }

    public int getWarningCount()
    {
      return this.warning_.size();
    }

    public List<DocAnnotations.Warning> getWarningList()
    {
      return this.warning_;
    }

    public boolean hasLink()
    {
      return this.hasLink;
    }

    public boolean hasOfferNote()
    {
      return this.hasOfferNote;
    }

    public boolean hasPlusOneData()
    {
      return this.hasPlusOneData;
    }

    public boolean hasPrivacyPolicyUrl()
    {
      return this.hasPrivacyPolicyUrl;
    }

    public boolean hasReason()
    {
      return this.hasReason;
    }

    public boolean hasSectionBodyOfWork()
    {
      return this.hasSectionBodyOfWork;
    }

    public boolean hasSectionCoreContent()
    {
      return this.hasSectionCoreContent;
    }

    public boolean hasSectionCrossSell()
    {
      return this.hasSectionCrossSell;
    }

    public boolean hasSectionMoreBy()
    {
      return this.hasSectionMoreBy;
    }

    public boolean hasSectionRelated()
    {
      return this.hasSectionRelated;
    }

    public boolean hasSectionRelatedDocType()
    {
      return this.hasSectionRelatedDocType;
    }

    public boolean hasTemplate()
    {
      return this.hasTemplate;
    }

    public Annotations mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocAnnotations.SectionMetadata localSectionMetadata6 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata6);
          setSectionRelated(localSectionMetadata6);
          break;
        case 18:
          DocAnnotations.SectionMetadata localSectionMetadata5 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata5);
          setSectionMoreBy(localSectionMetadata5);
          break;
        case 26:
          DocAnnotations.PlusOneData localPlusOneData = new DocAnnotations.PlusOneData();
          paramCodedInputStreamMicro.readMessage(localPlusOneData);
          setPlusOneData(localPlusOneData);
          break;
        case 34:
          DocAnnotations.Warning localWarning = new DocAnnotations.Warning();
          paramCodedInputStreamMicro.readMessage(localWarning);
          addWarning(localWarning);
          break;
        case 42:
          DocAnnotations.SectionMetadata localSectionMetadata4 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata4);
          setSectionBodyOfWork(localSectionMetadata4);
          break;
        case 50:
          DocAnnotations.SectionMetadata localSectionMetadata3 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata3);
          setSectionCoreContent(localSectionMetadata3);
          break;
        case 58:
          DocAnnotations.Template localTemplate = new DocAnnotations.Template();
          paramCodedInputStreamMicro.readMessage(localTemplate);
          setTemplate(localTemplate);
          break;
        case 66:
          DocAnnotations.Badge localBadge2 = new DocAnnotations.Badge();
          paramCodedInputStreamMicro.readMessage(localBadge2);
          addBadgeForCreator(localBadge2);
          break;
        case 74:
          DocAnnotations.Badge localBadge1 = new DocAnnotations.Badge();
          paramCodedInputStreamMicro.readMessage(localBadge1);
          addBadgeForDoc(localBadge1);
          break;
        case 82:
          DocAnnotations.Link localLink = new DocAnnotations.Link();
          paramCodedInputStreamMicro.readMessage(localLink);
          setLink(localLink);
          break;
        case 90:
          DocAnnotations.SectionMetadata localSectionMetadata2 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata2);
          setSectionCrossSell(localSectionMetadata2);
          break;
        case 98:
          DocAnnotations.SectionMetadata localSectionMetadata1 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata1);
          setSectionRelatedDocType(localSectionMetadata1);
          break;
        case 106:
          DocAnnotations.PromotedDoc localPromotedDoc = new DocAnnotations.PromotedDoc();
          paramCodedInputStreamMicro.readMessage(localPromotedDoc);
          addPromotedDoc(localPromotedDoc);
          break;
        case 114:
          setOfferNote(paramCodedInputStreamMicro.readString());
          break;
        case 130:
          DocumentV2.DocV2 localDocV2 = new DocumentV2.DocV2();
          paramCodedInputStreamMicro.readMessage(localDocV2);
          addSubscription(localDocV2);
          break;
        case 138:
          DocAnnotations.Reason localReason = new DocAnnotations.Reason();
          paramCodedInputStreamMicro.readMessage(localReason);
          setReason(localReason);
          break;
        case 146:
        }
        setPrivacyPolicyUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public Annotations setLink(DocAnnotations.Link paramLink)
    {
      if (paramLink == null)
        throw new NullPointerException();
      this.hasLink = true;
      this.link_ = paramLink;
      return this;
    }

    public Annotations setOfferNote(String paramString)
    {
      this.hasOfferNote = true;
      this.offerNote_ = paramString;
      return this;
    }

    public Annotations setPlusOneData(DocAnnotations.PlusOneData paramPlusOneData)
    {
      if (paramPlusOneData == null)
        throw new NullPointerException();
      this.hasPlusOneData = true;
      this.plusOneData_ = paramPlusOneData;
      return this;
    }

    public Annotations setPrivacyPolicyUrl(String paramString)
    {
      this.hasPrivacyPolicyUrl = true;
      this.privacyPolicyUrl_ = paramString;
      return this;
    }

    public Annotations setReason(DocAnnotations.Reason paramReason)
    {
      if (paramReason == null)
        throw new NullPointerException();
      this.hasReason = true;
      this.reason_ = paramReason;
      return this;
    }

    public Annotations setSectionBodyOfWork(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionBodyOfWork = true;
      this.sectionBodyOfWork_ = paramSectionMetadata;
      return this;
    }

    public Annotations setSectionCoreContent(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionCoreContent = true;
      this.sectionCoreContent_ = paramSectionMetadata;
      return this;
    }

    public Annotations setSectionCrossSell(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionCrossSell = true;
      this.sectionCrossSell_ = paramSectionMetadata;
      return this;
    }

    public Annotations setSectionMoreBy(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionMoreBy = true;
      this.sectionMoreBy_ = paramSectionMetadata;
      return this;
    }

    public Annotations setSectionRelated(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionRelated = true;
      this.sectionRelated_ = paramSectionMetadata;
      return this;
    }

    public Annotations setSectionRelatedDocType(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionRelatedDocType = true;
      this.sectionRelatedDocType_ = paramSectionMetadata;
      return this;
    }

    public Annotations setTemplate(DocAnnotations.Template paramTemplate)
    {
      if (paramTemplate == null)
        throw new NullPointerException();
      this.hasTemplate = true;
      this.template_ = paramTemplate;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSectionRelated())
        paramCodedOutputStreamMicro.writeMessage(1, getSectionRelated());
      if (hasSectionMoreBy())
        paramCodedOutputStreamMicro.writeMessage(2, getSectionMoreBy());
      if (hasPlusOneData())
        paramCodedOutputStreamMicro.writeMessage(3, getPlusOneData());
      Iterator localIterator1 = getWarningList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (DocAnnotations.Warning)localIterator1.next());
      if (hasSectionBodyOfWork())
        paramCodedOutputStreamMicro.writeMessage(5, getSectionBodyOfWork());
      if (hasSectionCoreContent())
        paramCodedOutputStreamMicro.writeMessage(6, getSectionCoreContent());
      if (hasTemplate())
        paramCodedOutputStreamMicro.writeMessage(7, getTemplate());
      Iterator localIterator2 = getBadgeForCreatorList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (DocAnnotations.Badge)localIterator2.next());
      Iterator localIterator3 = getBadgeForDocList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(9, (DocAnnotations.Badge)localIterator3.next());
      if (hasLink())
        paramCodedOutputStreamMicro.writeMessage(10, getLink());
      if (hasSectionCrossSell())
        paramCodedOutputStreamMicro.writeMessage(11, getSectionCrossSell());
      if (hasSectionRelatedDocType())
        paramCodedOutputStreamMicro.writeMessage(12, getSectionRelatedDocType());
      Iterator localIterator4 = getPromotedDocList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(13, (DocAnnotations.PromotedDoc)localIterator4.next());
      if (hasOfferNote())
        paramCodedOutputStreamMicro.writeString(14, getOfferNote());
      Iterator localIterator5 = getSubscriptionList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeMessage(16, (DocumentV2.DocV2)localIterator5.next());
      if (hasReason())
        paramCodedOutputStreamMicro.writeMessage(17, getReason());
      if (hasPrivacyPolicyUrl())
        paramCodedOutputStreamMicro.writeString(18, getPrivacyPolicyUrl());
    }
  }

  public static final class DocV2 extends MessageMicro
  {
    private Rating.AggregateRating aggregateRating_ = null;
    private DocumentV2.Annotations annotations_ = null;
    private FilterRules.Availability availability_ = null;
    private String backendDocid_ = "";
    private int backendId_ = 0;
    private String backendUrl_ = "";
    private int cachedSize = -1;
    private List<DocV2> child_ = Collections.emptyList();
    private Containers.ContainerMetadata containerMetadata_ = null;
    private String creator_ = "";
    private String descriptionHtml_ = "";
    private boolean detailsReusable_ = false;
    private String detailsUrl_ = "";
    private DocDetails.DocumentDetails details_ = null;
    private int docType_ = 1;
    private String docid_ = "";
    private boolean hasAggregateRating;
    private boolean hasAnnotations;
    private boolean hasAvailability;
    private boolean hasBackendDocid;
    private boolean hasBackendId;
    private boolean hasBackendUrl;
    private boolean hasContainerMetadata;
    private boolean hasCreator;
    private boolean hasDescriptionHtml;
    private boolean hasDetails;
    private boolean hasDetailsReusable;
    private boolean hasDetailsUrl;
    private boolean hasDocType;
    private boolean hasDocid;
    private boolean hasPurchaseDetailsUrl;
    private boolean hasReviewsUrl;
    private boolean hasShareUrl;
    private boolean hasSubtitle;
    private boolean hasTitle;
    private List<Doc.Image> image_ = Collections.emptyList();
    private List<Common.Offer> offer_ = Collections.emptyList();
    private String purchaseDetailsUrl_ = "";
    private String reviewsUrl_ = "";
    private String shareUrl_ = "";
    private String subtitle_ = "";
    private String title_ = "";

    public DocV2 addChild(DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      if (this.child_.isEmpty())
        this.child_ = new ArrayList();
      this.child_.add(paramDocV2);
      return this;
    }

    public DocV2 addImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      if (this.image_.isEmpty())
        this.image_ = new ArrayList();
      this.image_.add(paramImage);
      return this;
    }

    public DocV2 addOffer(Common.Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      if (this.offer_.isEmpty())
        this.offer_ = new ArrayList();
      this.offer_.add(paramOffer);
      return this;
    }

    public Rating.AggregateRating getAggregateRating()
    {
      return this.aggregateRating_;
    }

    public DocumentV2.Annotations getAnnotations()
    {
      return this.annotations_;
    }

    public FilterRules.Availability getAvailability()
    {
      return this.availability_;
    }

    public String getBackendDocid()
    {
      return this.backendDocid_;
    }

    public int getBackendId()
    {
      return this.backendId_;
    }

    public String getBackendUrl()
    {
      return this.backendUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocV2 getChild(int paramInt)
    {
      return (DocV2)this.child_.get(paramInt);
    }

    public int getChildCount()
    {
      return this.child_.size();
    }

    public List<DocV2> getChildList()
    {
      return this.child_;
    }

    public Containers.ContainerMetadata getContainerMetadata()
    {
      return this.containerMetadata_;
    }

    public String getCreator()
    {
      return this.creator_;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public DocDetails.DocumentDetails getDetails()
    {
      return this.details_;
    }

    public boolean getDetailsReusable()
    {
      return this.detailsReusable_;
    }

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public int getDocType()
    {
      return this.docType_;
    }

    public String getDocid()
    {
      return this.docid_;
    }

    public List<Doc.Image> getImageList()
    {
      return this.image_;
    }

    public Common.Offer getOffer(int paramInt)
    {
      return (Common.Offer)this.offer_.get(paramInt);
    }

    public List<Common.Offer> getOfferList()
    {
      return this.offer_;
    }

    public String getPurchaseDetailsUrl()
    {
      return this.purchaseDetailsUrl_;
    }

    public String getReviewsUrl()
    {
      return this.reviewsUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDocid())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDocid());
      if (hasBackendDocid())
        i += CodedOutputStreamMicro.computeStringSize(2, getBackendDocid());
      if (hasDocType())
        i += CodedOutputStreamMicro.computeInt32Size(3, getDocType());
      if (hasBackendId())
        i += CodedOutputStreamMicro.computeInt32Size(4, getBackendId());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getTitle());
      if (hasCreator())
        i += CodedOutputStreamMicro.computeStringSize(6, getCreator());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(7, getDescriptionHtml());
      Iterator localIterator1 = getOfferList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(8, (Common.Offer)localIterator1.next());
      if (hasAvailability())
        i += CodedOutputStreamMicro.computeMessageSize(9, getAvailability());
      Iterator localIterator2 = getImageList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(10, (Doc.Image)localIterator2.next());
      Iterator localIterator3 = getChildList().iterator();
      while (localIterator3.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(11, (DocV2)localIterator3.next());
      if (hasContainerMetadata())
        i += CodedOutputStreamMicro.computeMessageSize(12, getContainerMetadata());
      if (hasDetails())
        i += CodedOutputStreamMicro.computeMessageSize(13, getDetails());
      if (hasAggregateRating())
        i += CodedOutputStreamMicro.computeMessageSize(14, getAggregateRating());
      if (hasAnnotations())
        i += CodedOutputStreamMicro.computeMessageSize(15, getAnnotations());
      if (hasDetailsUrl())
        i += CodedOutputStreamMicro.computeStringSize(16, getDetailsUrl());
      if (hasShareUrl())
        i += CodedOutputStreamMicro.computeStringSize(17, getShareUrl());
      if (hasReviewsUrl())
        i += CodedOutputStreamMicro.computeStringSize(18, getReviewsUrl());
      if (hasBackendUrl())
        i += CodedOutputStreamMicro.computeStringSize(19, getBackendUrl());
      if (hasPurchaseDetailsUrl())
        i += CodedOutputStreamMicro.computeStringSize(20, getPurchaseDetailsUrl());
      if (hasDetailsReusable())
        i += CodedOutputStreamMicro.computeBoolSize(21, getDetailsReusable());
      if (hasSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(22, getSubtitle());
      this.cachedSize = i;
      return i;
    }

    public String getShareUrl()
    {
      return this.shareUrl_;
    }

    public String getSubtitle()
    {
      return this.subtitle_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasAggregateRating()
    {
      return this.hasAggregateRating;
    }

    public boolean hasAnnotations()
    {
      return this.hasAnnotations;
    }

    public boolean hasAvailability()
    {
      return this.hasAvailability;
    }

    public boolean hasBackendDocid()
    {
      return this.hasBackendDocid;
    }

    public boolean hasBackendId()
    {
      return this.hasBackendId;
    }

    public boolean hasBackendUrl()
    {
      return this.hasBackendUrl;
    }

    public boolean hasContainerMetadata()
    {
      return this.hasContainerMetadata;
    }

    public boolean hasCreator()
    {
      return this.hasCreator;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasDetails()
    {
      return this.hasDetails;
    }

    public boolean hasDetailsReusable()
    {
      return this.hasDetailsReusable;
    }

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasDocType()
    {
      return this.hasDocType;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasPurchaseDetailsUrl()
    {
      return this.hasPurchaseDetailsUrl;
    }

    public boolean hasReviewsUrl()
    {
      return this.hasReviewsUrl;
    }

    public boolean hasShareUrl()
    {
      return this.hasShareUrl;
    }

    public boolean hasSubtitle()
    {
      return this.hasSubtitle;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public DocV2 mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDocid(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setBackendDocid(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setDocType(paramCodedInputStreamMicro.readInt32());
          break;
        case 32:
          setBackendId(paramCodedInputStreamMicro.readInt32());
          break;
        case 42:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setCreator(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          Common.Offer localOffer = new Common.Offer();
          paramCodedInputStreamMicro.readMessage(localOffer);
          addOffer(localOffer);
          break;
        case 74:
          FilterRules.Availability localAvailability = new FilterRules.Availability();
          paramCodedInputStreamMicro.readMessage(localAvailability);
          setAvailability(localAvailability);
          break;
        case 82:
          Doc.Image localImage = new Doc.Image();
          paramCodedInputStreamMicro.readMessage(localImage);
          addImage(localImage);
          break;
        case 90:
          DocV2 localDocV2 = new DocV2();
          paramCodedInputStreamMicro.readMessage(localDocV2);
          addChild(localDocV2);
          break;
        case 98:
          Containers.ContainerMetadata localContainerMetadata = new Containers.ContainerMetadata();
          paramCodedInputStreamMicro.readMessage(localContainerMetadata);
          setContainerMetadata(localContainerMetadata);
          break;
        case 106:
          DocDetails.DocumentDetails localDocumentDetails = new DocDetails.DocumentDetails();
          paramCodedInputStreamMicro.readMessage(localDocumentDetails);
          setDetails(localDocumentDetails);
          break;
        case 114:
          Rating.AggregateRating localAggregateRating = new Rating.AggregateRating();
          paramCodedInputStreamMicro.readMessage(localAggregateRating);
          setAggregateRating(localAggregateRating);
          break;
        case 122:
          DocumentV2.Annotations localAnnotations = new DocumentV2.Annotations();
          paramCodedInputStreamMicro.readMessage(localAnnotations);
          setAnnotations(localAnnotations);
          break;
        case 130:
          setDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 138:
          setShareUrl(paramCodedInputStreamMicro.readString());
          break;
        case 146:
          setReviewsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 154:
          setBackendUrl(paramCodedInputStreamMicro.readString());
          break;
        case 162:
          setPurchaseDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 168:
          setDetailsReusable(paramCodedInputStreamMicro.readBool());
          break;
        case 178:
        }
        setSubtitle(paramCodedInputStreamMicro.readString());
      }
    }

    public DocV2 setAggregateRating(Rating.AggregateRating paramAggregateRating)
    {
      if (paramAggregateRating == null)
        throw new NullPointerException();
      this.hasAggregateRating = true;
      this.aggregateRating_ = paramAggregateRating;
      return this;
    }

    public DocV2 setAnnotations(DocumentV2.Annotations paramAnnotations)
    {
      if (paramAnnotations == null)
        throw new NullPointerException();
      this.hasAnnotations = true;
      this.annotations_ = paramAnnotations;
      return this;
    }

    public DocV2 setAvailability(FilterRules.Availability paramAvailability)
    {
      if (paramAvailability == null)
        throw new NullPointerException();
      this.hasAvailability = true;
      this.availability_ = paramAvailability;
      return this;
    }

    public DocV2 setBackendDocid(String paramString)
    {
      this.hasBackendDocid = true;
      this.backendDocid_ = paramString;
      return this;
    }

    public DocV2 setBackendId(int paramInt)
    {
      this.hasBackendId = true;
      this.backendId_ = paramInt;
      return this;
    }

    public DocV2 setBackendUrl(String paramString)
    {
      this.hasBackendUrl = true;
      this.backendUrl_ = paramString;
      return this;
    }

    public DocV2 setContainerMetadata(Containers.ContainerMetadata paramContainerMetadata)
    {
      if (paramContainerMetadata == null)
        throw new NullPointerException();
      this.hasContainerMetadata = true;
      this.containerMetadata_ = paramContainerMetadata;
      return this;
    }

    public DocV2 setCreator(String paramString)
    {
      this.hasCreator = true;
      this.creator_ = paramString;
      return this;
    }

    public DocV2 setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public DocV2 setDetails(DocDetails.DocumentDetails paramDocumentDetails)
    {
      if (paramDocumentDetails == null)
        throw new NullPointerException();
      this.hasDetails = true;
      this.details_ = paramDocumentDetails;
      return this;
    }

    public DocV2 setDetailsReusable(boolean paramBoolean)
    {
      this.hasDetailsReusable = true;
      this.detailsReusable_ = paramBoolean;
      return this;
    }

    public DocV2 setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public DocV2 setDocType(int paramInt)
    {
      this.hasDocType = true;
      this.docType_ = paramInt;
      return this;
    }

    public DocV2 setDocid(String paramString)
    {
      this.hasDocid = true;
      this.docid_ = paramString;
      return this;
    }

    public DocV2 setPurchaseDetailsUrl(String paramString)
    {
      this.hasPurchaseDetailsUrl = true;
      this.purchaseDetailsUrl_ = paramString;
      return this;
    }

    public DocV2 setReviewsUrl(String paramString)
    {
      this.hasReviewsUrl = true;
      this.reviewsUrl_ = paramString;
      return this;
    }

    public DocV2 setShareUrl(String paramString)
    {
      this.hasShareUrl = true;
      this.shareUrl_ = paramString;
      return this;
    }

    public DocV2 setSubtitle(String paramString)
    {
      this.hasSubtitle = true;
      this.subtitle_ = paramString;
      return this;
    }

    public DocV2 setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDocid())
        paramCodedOutputStreamMicro.writeString(1, getDocid());
      if (hasBackendDocid())
        paramCodedOutputStreamMicro.writeString(2, getBackendDocid());
      if (hasDocType())
        paramCodedOutputStreamMicro.writeInt32(3, getDocType());
      if (hasBackendId())
        paramCodedOutputStreamMicro.writeInt32(4, getBackendId());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(5, getTitle());
      if (hasCreator())
        paramCodedOutputStreamMicro.writeString(6, getCreator());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(7, getDescriptionHtml());
      Iterator localIterator1 = getOfferList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (Common.Offer)localIterator1.next());
      if (hasAvailability())
        paramCodedOutputStreamMicro.writeMessage(9, getAvailability());
      Iterator localIterator2 = getImageList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(10, (Doc.Image)localIterator2.next());
      Iterator localIterator3 = getChildList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(11, (DocV2)localIterator3.next());
      if (hasContainerMetadata())
        paramCodedOutputStreamMicro.writeMessage(12, getContainerMetadata());
      if (hasDetails())
        paramCodedOutputStreamMicro.writeMessage(13, getDetails());
      if (hasAggregateRating())
        paramCodedOutputStreamMicro.writeMessage(14, getAggregateRating());
      if (hasAnnotations())
        paramCodedOutputStreamMicro.writeMessage(15, getAnnotations());
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(16, getDetailsUrl());
      if (hasShareUrl())
        paramCodedOutputStreamMicro.writeString(17, getShareUrl());
      if (hasReviewsUrl())
        paramCodedOutputStreamMicro.writeString(18, getReviewsUrl());
      if (hasBackendUrl())
        paramCodedOutputStreamMicro.writeString(19, getBackendUrl());
      if (hasPurchaseDetailsUrl())
        paramCodedOutputStreamMicro.writeString(20, getPurchaseDetailsUrl());
      if (hasDetailsReusable())
        paramCodedOutputStreamMicro.writeBool(21, getDetailsReusable());
      if (hasSubtitle())
        paramCodedOutputStreamMicro.writeString(22, getSubtitle());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DocumentV2
 * JD-Core Version:    0.6.2
 */
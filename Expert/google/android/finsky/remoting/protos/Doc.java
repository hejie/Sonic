package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Doc
{
  public static final class Document extends MessageMicro
  {
    private Rating.AggregateRating aggregateRating_ = null;
    private FilterRules.Availability availability_ = null;
    private int cachedSize = -1;
    private List<String> categoryId_ = Collections.emptyList();
    private List<Document> child_ = Collections.emptyList();
    private String consumptionUrl_ = "";
    private List<Document> decoration_ = Collections.emptyList();
    private Common.Docid docid_ = null;
    private List<Doc.DocumentVariant> documentVariant_ = Collections.emptyList();
    private Common.Docid fetchDocid_ = null;
    private boolean hasAggregateRating;
    private boolean hasAvailability;
    private boolean hasConsumptionUrl;
    private boolean hasDocid;
    private boolean hasFetchDocid;
    private boolean hasPriceDeprecated;
    private boolean hasPrivacyPolicyUrl;
    private boolean hasSampleDocid;
    private boolean hasTitle;
    private boolean hasUrl;
    private List<Doc.Image> image_ = Collections.emptyList();
    private List<Common.Offer> offer_ = Collections.emptyList();
    private List<Document> parent_ = Collections.emptyList();
    private Common.Offer priceDeprecated_ = null;
    private String privacyPolicyUrl_ = "";
    private Common.Docid sampleDocid_ = null;
    private List<String> snippet_ = Collections.emptyList();
    private String title_ = "";
    private List<Doc.TranslatedText> translatedSnippet_ = Collections.emptyList();
    private String url_ = "";

    public Document addCategoryId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.categoryId_.isEmpty())
        this.categoryId_ = new ArrayList();
      this.categoryId_.add(paramString);
      return this;
    }

    public Document addChild(Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      if (this.child_.isEmpty())
        this.child_ = new ArrayList();
      this.child_.add(paramDocument);
      return this;
    }

    public Document addDecoration(Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      if (this.decoration_.isEmpty())
        this.decoration_ = new ArrayList();
      this.decoration_.add(paramDocument);
      return this;
    }

    public Document addDocumentVariant(Doc.DocumentVariant paramDocumentVariant)
    {
      if (paramDocumentVariant == null)
        throw new NullPointerException();
      if (this.documentVariant_.isEmpty())
        this.documentVariant_ = new ArrayList();
      this.documentVariant_.add(paramDocumentVariant);
      return this;
    }

    public Document addImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      if (this.image_.isEmpty())
        this.image_ = new ArrayList();
      this.image_.add(paramImage);
      return this;
    }

    public Document addOffer(Common.Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      if (this.offer_.isEmpty())
        this.offer_ = new ArrayList();
      this.offer_.add(paramOffer);
      return this;
    }

    public Document addParent(Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      if (this.parent_.isEmpty())
        this.parent_ = new ArrayList();
      this.parent_.add(paramDocument);
      return this;
    }

    public Document addSnippet(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.snippet_.isEmpty())
        this.snippet_ = new ArrayList();
      this.snippet_.add(paramString);
      return this;
    }

    public Document addTranslatedSnippet(Doc.TranslatedText paramTranslatedText)
    {
      if (paramTranslatedText == null)
        throw new NullPointerException();
      if (this.translatedSnippet_.isEmpty())
        this.translatedSnippet_ = new ArrayList();
      this.translatedSnippet_.add(paramTranslatedText);
      return this;
    }

    public Rating.AggregateRating getAggregateRating()
    {
      return this.aggregateRating_;
    }

    public FilterRules.Availability getAvailability()
    {
      return this.availability_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getCategoryIdList()
    {
      return this.categoryId_;
    }

    public List<Document> getChildList()
    {
      return this.child_;
    }

    public String getConsumptionUrl()
    {
      return this.consumptionUrl_;
    }

    public List<Document> getDecorationList()
    {
      return this.decoration_;
    }

    public Common.Docid getDocid()
    {
      return this.docid_;
    }

    public List<Doc.DocumentVariant> getDocumentVariantList()
    {
      return this.documentVariant_;
    }

    public Common.Docid getFetchDocid()
    {
      return this.fetchDocid_;
    }

    public List<Doc.Image> getImageList()
    {
      return this.image_;
    }

    public List<Common.Offer> getOfferList()
    {
      return this.offer_;
    }

    public List<Document> getParentList()
    {
      return this.parent_;
    }

    public Common.Offer getPriceDeprecated()
    {
      return this.priceDeprecated_;
    }

    public String getPrivacyPolicyUrl()
    {
      return this.privacyPolicyUrl_;
    }

    public Common.Docid getSampleDocid()
    {
      return this.sampleDocid_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDocid())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDocid());
      if (hasFetchDocid())
        i += CodedOutputStreamMicro.computeMessageSize(2, getFetchDocid());
      if (hasSampleDocid())
        i += CodedOutputStreamMicro.computeMessageSize(3, getSampleDocid());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(4, getTitle());
      if (hasUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getUrl());
      int j = 0;
      Iterator localIterator1 = getSnippetList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getSnippetList().size();
      if (hasPriceDeprecated())
        k += CodedOutputStreamMicro.computeMessageSize(7, getPriceDeprecated());
      if (hasAvailability())
        k += CodedOutputStreamMicro.computeMessageSize(9, getAvailability());
      Iterator localIterator2 = getImageList().iterator();
      while (localIterator2.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(10, (Doc.Image)localIterator2.next());
      Iterator localIterator3 = getChildList().iterator();
      while (localIterator3.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(11, (Document)localIterator3.next());
      if (hasAggregateRating())
        k += CodedOutputStreamMicro.computeMessageSize(13, getAggregateRating());
      Iterator localIterator4 = getOfferList().iterator();
      while (localIterator4.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(14, (Common.Offer)localIterator4.next());
      Iterator localIterator5 = getTranslatedSnippetList().iterator();
      while (localIterator5.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(15, (Doc.TranslatedText)localIterator5.next());
      Iterator localIterator6 = getDocumentVariantList().iterator();
      while (localIterator6.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(16, (Doc.DocumentVariant)localIterator6.next());
      int m = 0;
      Iterator localIterator7 = getCategoryIdList().iterator();
      while (localIterator7.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator7.next());
      int n = k + m + 2 * getCategoryIdList().size();
      Iterator localIterator8 = getDecorationList().iterator();
      while (localIterator8.hasNext())
        n += CodedOutputStreamMicro.computeMessageSize(18, (Document)localIterator8.next());
      Iterator localIterator9 = getParentList().iterator();
      while (localIterator9.hasNext())
        n += CodedOutputStreamMicro.computeMessageSize(19, (Document)localIterator9.next());
      if (hasPrivacyPolicyUrl())
        n += CodedOutputStreamMicro.computeStringSize(20, getPrivacyPolicyUrl());
      if (hasConsumptionUrl())
        n += CodedOutputStreamMicro.computeStringSize(21, getConsumptionUrl());
      this.cachedSize = n;
      return n;
    }

    public List<String> getSnippetList()
    {
      return this.snippet_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public List<Doc.TranslatedText> getTranslatedSnippetList()
    {
      return this.translatedSnippet_;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public boolean hasAggregateRating()
    {
      return this.hasAggregateRating;
    }

    public boolean hasAvailability()
    {
      return this.hasAvailability;
    }

    public boolean hasConsumptionUrl()
    {
      return this.hasConsumptionUrl;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasFetchDocid()
    {
      return this.hasFetchDocid;
    }

    public boolean hasPriceDeprecated()
    {
      return this.hasPriceDeprecated;
    }

    public boolean hasPrivacyPolicyUrl()
    {
      return this.hasPrivacyPolicyUrl;
    }

    public boolean hasSampleDocid()
    {
      return this.hasSampleDocid;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public Document mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Common.Docid localDocid3 = new Common.Docid();
          paramCodedInputStreamMicro.readMessage(localDocid3);
          setDocid(localDocid3);
          break;
        case 18:
          Common.Docid localDocid2 = new Common.Docid();
          paramCodedInputStreamMicro.readMessage(localDocid2);
          setFetchDocid(localDocid2);
          break;
        case 26:
          Common.Docid localDocid1 = new Common.Docid();
          paramCodedInputStreamMicro.readMessage(localDocid1);
          setSampleDocid(localDocid1);
          break;
        case 34:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          addSnippet(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          Common.Offer localOffer2 = new Common.Offer();
          paramCodedInputStreamMicro.readMessage(localOffer2);
          setPriceDeprecated(localOffer2);
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
          Document localDocument3 = new Document();
          paramCodedInputStreamMicro.readMessage(localDocument3);
          addChild(localDocument3);
          break;
        case 106:
          Rating.AggregateRating localAggregateRating = new Rating.AggregateRating();
          paramCodedInputStreamMicro.readMessage(localAggregateRating);
          setAggregateRating(localAggregateRating);
          break;
        case 114:
          Common.Offer localOffer1 = new Common.Offer();
          paramCodedInputStreamMicro.readMessage(localOffer1);
          addOffer(localOffer1);
          break;
        case 122:
          Doc.TranslatedText localTranslatedText = new Doc.TranslatedText();
          paramCodedInputStreamMicro.readMessage(localTranslatedText);
          addTranslatedSnippet(localTranslatedText);
          break;
        case 130:
          Doc.DocumentVariant localDocumentVariant = new Doc.DocumentVariant();
          paramCodedInputStreamMicro.readMessage(localDocumentVariant);
          addDocumentVariant(localDocumentVariant);
          break;
        case 138:
          addCategoryId(paramCodedInputStreamMicro.readString());
          break;
        case 146:
          Document localDocument2 = new Document();
          paramCodedInputStreamMicro.readMessage(localDocument2);
          addDecoration(localDocument2);
          break;
        case 154:
          Document localDocument1 = new Document();
          paramCodedInputStreamMicro.readMessage(localDocument1);
          addParent(localDocument1);
          break;
        case 162:
          setPrivacyPolicyUrl(paramCodedInputStreamMicro.readString());
          break;
        case 170:
        }
        setConsumptionUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public Document setAggregateRating(Rating.AggregateRating paramAggregateRating)
    {
      if (paramAggregateRating == null)
        throw new NullPointerException();
      this.hasAggregateRating = true;
      this.aggregateRating_ = paramAggregateRating;
      return this;
    }

    public Document setAvailability(FilterRules.Availability paramAvailability)
    {
      if (paramAvailability == null)
        throw new NullPointerException();
      this.hasAvailability = true;
      this.availability_ = paramAvailability;
      return this;
    }

    public Document setConsumptionUrl(String paramString)
    {
      this.hasConsumptionUrl = true;
      this.consumptionUrl_ = paramString;
      return this;
    }

    public Document setDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasDocid = true;
      this.docid_ = paramDocid;
      return this;
    }

    public Document setFetchDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasFetchDocid = true;
      this.fetchDocid_ = paramDocid;
      return this;
    }

    public Document setPriceDeprecated(Common.Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      this.hasPriceDeprecated = true;
      this.priceDeprecated_ = paramOffer;
      return this;
    }

    public Document setPrivacyPolicyUrl(String paramString)
    {
      this.hasPrivacyPolicyUrl = true;
      this.privacyPolicyUrl_ = paramString;
      return this;
    }

    public Document setSampleDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasSampleDocid = true;
      this.sampleDocid_ = paramDocid;
      return this;
    }

    public Document setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public Document setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDocid())
        paramCodedOutputStreamMicro.writeMessage(1, getDocid());
      if (hasFetchDocid())
        paramCodedOutputStreamMicro.writeMessage(2, getFetchDocid());
      if (hasSampleDocid())
        paramCodedOutputStreamMicro.writeMessage(3, getSampleDocid());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(4, getTitle());
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(5, getUrl());
      Iterator localIterator1 = getSnippetList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(6, (String)localIterator1.next());
      if (hasPriceDeprecated())
        paramCodedOutputStreamMicro.writeMessage(7, getPriceDeprecated());
      if (hasAvailability())
        paramCodedOutputStreamMicro.writeMessage(9, getAvailability());
      Iterator localIterator2 = getImageList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(10, (Doc.Image)localIterator2.next());
      Iterator localIterator3 = getChildList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(11, (Document)localIterator3.next());
      if (hasAggregateRating())
        paramCodedOutputStreamMicro.writeMessage(13, getAggregateRating());
      Iterator localIterator4 = getOfferList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(14, (Common.Offer)localIterator4.next());
      Iterator localIterator5 = getTranslatedSnippetList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeMessage(15, (Doc.TranslatedText)localIterator5.next());
      Iterator localIterator6 = getDocumentVariantList().iterator();
      while (localIterator6.hasNext())
        paramCodedOutputStreamMicro.writeMessage(16, (Doc.DocumentVariant)localIterator6.next());
      Iterator localIterator7 = getCategoryIdList().iterator();
      while (localIterator7.hasNext())
        paramCodedOutputStreamMicro.writeString(17, (String)localIterator7.next());
      Iterator localIterator8 = getDecorationList().iterator();
      while (localIterator8.hasNext())
        paramCodedOutputStreamMicro.writeMessage(18, (Document)localIterator8.next());
      Iterator localIterator9 = getParentList().iterator();
      while (localIterator9.hasNext())
        paramCodedOutputStreamMicro.writeMessage(19, (Document)localIterator9.next());
      if (hasPrivacyPolicyUrl())
        paramCodedOutputStreamMicro.writeString(20, getPrivacyPolicyUrl());
      if (hasConsumptionUrl())
        paramCodedOutputStreamMicro.writeString(21, getConsumptionUrl());
    }
  }

  public static final class DocumentVariant extends MessageMicro
  {
    private List<Doc.TranslatedText> autoTranslation_ = Collections.emptyList();
    private int cachedSize = -1;
    private long channelId_ = 0L;
    private List<Doc.Document> child_ = Collections.emptyList();
    private List<Doc.Document> decoration_ = Collections.emptyList();
    private boolean hasChannelId;
    private boolean hasRecentChanges;
    private boolean hasRule;
    private boolean hasTitle;
    private boolean hasVariationType;
    private List<Doc.Image> image_ = Collections.emptyList();
    private List<Common.Offer> offer_ = Collections.emptyList();
    private String recentChanges_ = "";
    private FilterRules.Rule rule_ = null;
    private List<String> snippet_ = Collections.emptyList();
    private String title_ = "";
    private int variationType_ = 1;

    public DocumentVariant addAutoTranslation(Doc.TranslatedText paramTranslatedText)
    {
      if (paramTranslatedText == null)
        throw new NullPointerException();
      if (this.autoTranslation_.isEmpty())
        this.autoTranslation_ = new ArrayList();
      this.autoTranslation_.add(paramTranslatedText);
      return this;
    }

    public DocumentVariant addChild(Doc.Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      if (this.child_.isEmpty())
        this.child_ = new ArrayList();
      this.child_.add(paramDocument);
      return this;
    }

    public DocumentVariant addDecoration(Doc.Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      if (this.decoration_.isEmpty())
        this.decoration_ = new ArrayList();
      this.decoration_.add(paramDocument);
      return this;
    }

    public DocumentVariant addImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      if (this.image_.isEmpty())
        this.image_ = new ArrayList();
      this.image_.add(paramImage);
      return this;
    }

    public DocumentVariant addOffer(Common.Offer paramOffer)
    {
      if (paramOffer == null)
        throw new NullPointerException();
      if (this.offer_.isEmpty())
        this.offer_ = new ArrayList();
      this.offer_.add(paramOffer);
      return this;
    }

    public DocumentVariant addSnippet(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.snippet_.isEmpty())
        this.snippet_ = new ArrayList();
      this.snippet_.add(paramString);
      return this;
    }

    public List<Doc.TranslatedText> getAutoTranslationList()
    {
      return this.autoTranslation_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getChannelId()
    {
      return this.channelId_;
    }

    public List<Doc.Document> getChildList()
    {
      return this.child_;
    }

    public List<Doc.Document> getDecorationList()
    {
      return this.decoration_;
    }

    public List<Doc.Image> getImageList()
    {
      return this.image_;
    }

    public List<Common.Offer> getOfferList()
    {
      return this.offer_;
    }

    public String getRecentChanges()
    {
      return this.recentChanges_;
    }

    public FilterRules.Rule getRule()
    {
      return this.rule_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasVariationType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getVariationType());
      if (hasRule())
        i += CodedOutputStreamMicro.computeMessageSize(2, getRule());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getTitle());
      int j = 0;
      Iterator localIterator1 = getSnippetList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getSnippetList().size();
      if (hasRecentChanges())
        k += CodedOutputStreamMicro.computeStringSize(5, getRecentChanges());
      Iterator localIterator2 = getAutoTranslationList().iterator();
      while (localIterator2.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(6, (Doc.TranslatedText)localIterator2.next());
      Iterator localIterator3 = getOfferList().iterator();
      while (localIterator3.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(7, (Common.Offer)localIterator3.next());
      if (hasChannelId())
        k += CodedOutputStreamMicro.computeInt64Size(9, getChannelId());
      Iterator localIterator4 = getChildList().iterator();
      while (localIterator4.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(10, (Doc.Document)localIterator4.next());
      Iterator localIterator5 = getDecorationList().iterator();
      while (localIterator5.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(11, (Doc.Document)localIterator5.next());
      Iterator localIterator6 = getImageList().iterator();
      while (localIterator6.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(12, (Doc.Image)localIterator6.next());
      this.cachedSize = k;
      return k;
    }

    public List<String> getSnippetList()
    {
      return this.snippet_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public int getVariationType()
    {
      return this.variationType_;
    }

    public boolean hasChannelId()
    {
      return this.hasChannelId;
    }

    public boolean hasRecentChanges()
    {
      return this.hasRecentChanges;
    }

    public boolean hasRule()
    {
      return this.hasRule;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasVariationType()
    {
      return this.hasVariationType;
    }

    public DocumentVariant mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setVariationType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          FilterRules.Rule localRule = new FilterRules.Rule();
          paramCodedInputStreamMicro.readMessage(localRule);
          setRule(localRule);
          break;
        case 26:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          addSnippet(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setRecentChanges(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          Doc.TranslatedText localTranslatedText = new Doc.TranslatedText();
          paramCodedInputStreamMicro.readMessage(localTranslatedText);
          addAutoTranslation(localTranslatedText);
          break;
        case 58:
          Common.Offer localOffer = new Common.Offer();
          paramCodedInputStreamMicro.readMessage(localOffer);
          addOffer(localOffer);
          break;
        case 72:
          setChannelId(paramCodedInputStreamMicro.readInt64());
          break;
        case 82:
          Doc.Document localDocument2 = new Doc.Document();
          paramCodedInputStreamMicro.readMessage(localDocument2);
          addChild(localDocument2);
          break;
        case 90:
          Doc.Document localDocument1 = new Doc.Document();
          paramCodedInputStreamMicro.readMessage(localDocument1);
          addDecoration(localDocument1);
          break;
        case 98:
        }
        Doc.Image localImage = new Doc.Image();
        paramCodedInputStreamMicro.readMessage(localImage);
        addImage(localImage);
      }
    }

    public DocumentVariant setChannelId(long paramLong)
    {
      this.hasChannelId = true;
      this.channelId_ = paramLong;
      return this;
    }

    public DocumentVariant setRecentChanges(String paramString)
    {
      this.hasRecentChanges = true;
      this.recentChanges_ = paramString;
      return this;
    }

    public DocumentVariant setRule(FilterRules.Rule paramRule)
    {
      if (paramRule == null)
        throw new NullPointerException();
      this.hasRule = true;
      this.rule_ = paramRule;
      return this;
    }

    public DocumentVariant setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public DocumentVariant setVariationType(int paramInt)
    {
      this.hasVariationType = true;
      this.variationType_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasVariationType())
        paramCodedOutputStreamMicro.writeInt32(1, getVariationType());
      if (hasRule())
        paramCodedOutputStreamMicro.writeMessage(2, getRule());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(3, getTitle());
      Iterator localIterator1 = getSnippetList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator1.next());
      if (hasRecentChanges())
        paramCodedOutputStreamMicro.writeString(5, getRecentChanges());
      Iterator localIterator2 = getAutoTranslationList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(6, (Doc.TranslatedText)localIterator2.next());
      Iterator localIterator3 = getOfferList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(7, (Common.Offer)localIterator3.next());
      if (hasChannelId())
        paramCodedOutputStreamMicro.writeInt64(9, getChannelId());
      Iterator localIterator4 = getChildList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(10, (Doc.Document)localIterator4.next());
      Iterator localIterator5 = getDecorationList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeMessage(11, (Doc.Document)localIterator5.next());
      Iterator localIterator6 = getImageList().iterator();
      while (localIterator6.hasNext())
        paramCodedOutputStreamMicro.writeMessage(12, (Doc.Image)localIterator6.next());
    }
  }

  public static final class Image extends MessageMicro
  {
    private String altTextLocalized_ = "";
    private int cachedSize = -1;
    private Citation citation_ = null;
    private Dimension dimension_ = null;
    private boolean hasAltTextLocalized;
    private boolean hasCitation;
    private boolean hasDimension;
    private boolean hasImageType;
    private boolean hasImageUrl;
    private boolean hasPositionInSequence;
    private boolean hasSecureUrl;
    private boolean hasSupportsFifeUrlOptions;
    private int imageType_ = 0;
    private String imageUrl_ = "";
    private int positionInSequence_ = 0;
    private String secureUrl_ = "";
    private boolean supportsFifeUrlOptions_ = false;

    public String getAltTextLocalized()
    {
      return this.altTextLocalized_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Citation getCitation()
    {
      return this.citation_;
    }

    public Dimension getDimension()
    {
      return this.dimension_;
    }

    public int getImageType()
    {
      return this.imageType_;
    }

    public String getImageUrl()
    {
      return this.imageUrl_;
    }

    public int getPositionInSequence()
    {
      return this.positionInSequence_;
    }

    public String getSecureUrl()
    {
      return this.secureUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasImageType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getImageType());
      if (hasDimension())
        i += CodedOutputStreamMicro.computeGroupSize(2, getDimension());
      if (hasImageUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getImageUrl());
      if (hasAltTextLocalized())
        i += CodedOutputStreamMicro.computeStringSize(6, getAltTextLocalized());
      if (hasSecureUrl())
        i += CodedOutputStreamMicro.computeStringSize(7, getSecureUrl());
      if (hasPositionInSequence())
        i += CodedOutputStreamMicro.computeInt32Size(8, getPositionInSequence());
      if (hasSupportsFifeUrlOptions())
        i += CodedOutputStreamMicro.computeBoolSize(9, getSupportsFifeUrlOptions());
      if (hasCitation())
        i += CodedOutputStreamMicro.computeGroupSize(10, getCitation());
      this.cachedSize = i;
      return i;
    }

    public boolean getSupportsFifeUrlOptions()
    {
      return this.supportsFifeUrlOptions_;
    }

    public boolean hasAltTextLocalized()
    {
      return this.hasAltTextLocalized;
    }

    public boolean hasCitation()
    {
      return this.hasCitation;
    }

    public boolean hasDimension()
    {
      return this.hasDimension;
    }

    public boolean hasImageType()
    {
      return this.hasImageType;
    }

    public boolean hasImageUrl()
    {
      return this.hasImageUrl;
    }

    public boolean hasPositionInSequence()
    {
      return this.hasPositionInSequence;
    }

    public boolean hasSecureUrl()
    {
      return this.hasSecureUrl;
    }

    public boolean hasSupportsFifeUrlOptions()
    {
      return this.hasSupportsFifeUrlOptions;
    }

    public Image mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setImageType(paramCodedInputStreamMicro.readInt32());
          break;
        case 19:
          Dimension localDimension = new Dimension();
          paramCodedInputStreamMicro.readGroup(localDimension, 2);
          setDimension(localDimension);
          break;
        case 42:
          setImageUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setAltTextLocalized(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setSecureUrl(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setPositionInSequence(paramCodedInputStreamMicro.readInt32());
          break;
        case 72:
          setSupportsFifeUrlOptions(paramCodedInputStreamMicro.readBool());
          break;
        case 83:
        }
        Citation localCitation = new Citation();
        paramCodedInputStreamMicro.readGroup(localCitation, 10);
        setCitation(localCitation);
      }
    }

    public Image setAltTextLocalized(String paramString)
    {
      this.hasAltTextLocalized = true;
      this.altTextLocalized_ = paramString;
      return this;
    }

    public Image setCitation(Citation paramCitation)
    {
      if (paramCitation == null)
        throw new NullPointerException();
      this.hasCitation = true;
      this.citation_ = paramCitation;
      return this;
    }

    public Image setDimension(Dimension paramDimension)
    {
      if (paramDimension == null)
        throw new NullPointerException();
      this.hasDimension = true;
      this.dimension_ = paramDimension;
      return this;
    }

    public Image setImageType(int paramInt)
    {
      this.hasImageType = true;
      this.imageType_ = paramInt;
      return this;
    }

    public Image setImageUrl(String paramString)
    {
      this.hasImageUrl = true;
      this.imageUrl_ = paramString;
      return this;
    }

    public Image setPositionInSequence(int paramInt)
    {
      this.hasPositionInSequence = true;
      this.positionInSequence_ = paramInt;
      return this;
    }

    public Image setSecureUrl(String paramString)
    {
      this.hasSecureUrl = true;
      this.secureUrl_ = paramString;
      return this;
    }

    public Image setSupportsFifeUrlOptions(boolean paramBoolean)
    {
      this.hasSupportsFifeUrlOptions = true;
      this.supportsFifeUrlOptions_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasImageType())
        paramCodedOutputStreamMicro.writeInt32(1, getImageType());
      if (hasDimension())
        paramCodedOutputStreamMicro.writeGroup(2, getDimension());
      if (hasImageUrl())
        paramCodedOutputStreamMicro.writeString(5, getImageUrl());
      if (hasAltTextLocalized())
        paramCodedOutputStreamMicro.writeString(6, getAltTextLocalized());
      if (hasSecureUrl())
        paramCodedOutputStreamMicro.writeString(7, getSecureUrl());
      if (hasPositionInSequence())
        paramCodedOutputStreamMicro.writeInt32(8, getPositionInSequence());
      if (hasSupportsFifeUrlOptions())
        paramCodedOutputStreamMicro.writeBool(9, getSupportsFifeUrlOptions());
      if (hasCitation())
        paramCodedOutputStreamMicro.writeGroup(10, getCitation());
    }

    public static final class Citation extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasTitleLocalized;
      private boolean hasUrl;
      private String titleLocalized_ = "";
      private String url_ = "";

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasTitleLocalized())
          i = 0 + CodedOutputStreamMicro.computeStringSize(11, getTitleLocalized());
        if (hasUrl())
          i += CodedOutputStreamMicro.computeStringSize(12, getUrl());
        this.cachedSize = i;
        return i;
      }

      public String getTitleLocalized()
      {
        return this.titleLocalized_;
      }

      public String getUrl()
      {
        return this.url_;
      }

      public boolean hasTitleLocalized()
      {
        return this.hasTitleLocalized;
      }

      public boolean hasUrl()
      {
        return this.hasUrl;
      }

      public Citation mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 90:
            setTitleLocalized(paramCodedInputStreamMicro.readString());
            break;
          case 98:
          }
          setUrl(paramCodedInputStreamMicro.readString());
        }
      }

      public Citation setTitleLocalized(String paramString)
      {
        this.hasTitleLocalized = true;
        this.titleLocalized_ = paramString;
        return this;
      }

      public Citation setUrl(String paramString)
      {
        this.hasUrl = true;
        this.url_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasTitleLocalized())
          paramCodedOutputStreamMicro.writeString(11, getTitleLocalized());
        if (hasUrl())
          paramCodedOutputStreamMicro.writeString(12, getUrl());
      }
    }

    public static final class Dimension extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasHeight;
      private boolean hasWidth;
      private int height_ = 0;
      private int width_ = 0;

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public int getHeight()
      {
        return this.height_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasWidth())
          i = 0 + CodedOutputStreamMicro.computeInt32Size(3, getWidth());
        if (hasHeight())
          i += CodedOutputStreamMicro.computeInt32Size(4, getHeight());
        this.cachedSize = i;
        return i;
      }

      public int getWidth()
      {
        return this.width_;
      }

      public boolean hasHeight()
      {
        return this.hasHeight;
      }

      public boolean hasWidth()
      {
        return this.hasWidth;
      }

      public Dimension mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 24:
            setWidth(paramCodedInputStreamMicro.readInt32());
            break;
          case 32:
          }
          setHeight(paramCodedInputStreamMicro.readInt32());
        }
      }

      public Dimension setHeight(int paramInt)
      {
        this.hasHeight = true;
        this.height_ = paramInt;
        return this;
      }

      public Dimension setWidth(int paramInt)
      {
        this.hasWidth = true;
        this.width_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasWidth())
          paramCodedOutputStreamMicro.writeInt32(3, getWidth());
        if (hasHeight())
          paramCodedOutputStreamMicro.writeInt32(4, getHeight());
      }
    }
  }

  public static final class TranslatedText extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSourceLocale;
    private boolean hasTargetLocale;
    private boolean hasText;
    private String sourceLocale_ = "";
    private String targetLocale_ = "";
    private String text_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasText())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getText());
      if (hasSourceLocale())
        i += CodedOutputStreamMicro.computeStringSize(2, getSourceLocale());
      if (hasTargetLocale())
        i += CodedOutputStreamMicro.computeStringSize(3, getTargetLocale());
      this.cachedSize = i;
      return i;
    }

    public String getSourceLocale()
    {
      return this.sourceLocale_;
    }

    public String getTargetLocale()
    {
      return this.targetLocale_;
    }

    public String getText()
    {
      return this.text_;
    }

    public boolean hasSourceLocale()
    {
      return this.hasSourceLocale;
    }

    public boolean hasTargetLocale()
    {
      return this.hasTargetLocale;
    }

    public boolean hasText()
    {
      return this.hasText;
    }

    public TranslatedText mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setText(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setSourceLocale(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setTargetLocale(paramCodedInputStreamMicro.readString());
      }
    }

    public TranslatedText setSourceLocale(String paramString)
    {
      this.hasSourceLocale = true;
      this.sourceLocale_ = paramString;
      return this;
    }

    public TranslatedText setTargetLocale(String paramString)
    {
      this.hasTargetLocale = true;
      this.targetLocale_ = paramString;
      return this;
    }

    public TranslatedText setText(String paramString)
    {
      this.hasText = true;
      this.text_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasText())
        paramCodedOutputStreamMicro.writeString(1, getText());
      if (hasSourceLocale())
        paramCodedOutputStreamMicro.writeString(2, getSourceLocale());
      if (hasTargetLocale())
        paramCodedOutputStreamMicro.writeString(3, getTargetLocale());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Doc
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class DocumentV1
{
  public static final class DocV1 extends MessageMicro
  {
    private int cachedSize = -1;
    private String creator_ = "";
    private String descriptionHtml_ = "";
    private String detailsUrl_ = "";
    private DocDetails.DocumentDetails details_ = null;
    private String docid_ = "";
    private Doc.Document finskyDoc_ = null;
    private boolean hasCreator;
    private boolean hasDescriptionHtml;
    private boolean hasDetails;
    private boolean hasDetailsUrl;
    private boolean hasDocid;
    private boolean hasFinskyDoc;
    private boolean hasMoreByBrowseUrl;
    private boolean hasMoreByHeader;
    private boolean hasMoreByListUrl;
    private boolean hasPlusOneData;
    private boolean hasRelatedBrowseUrl;
    private boolean hasRelatedHeader;
    private boolean hasRelatedListUrl;
    private boolean hasReviewsUrl;
    private boolean hasShareUrl;
    private boolean hasTitle;
    private boolean hasWarningMessage;
    private String moreByBrowseUrl_ = "";
    private String moreByHeader_ = "";
    private String moreByListUrl_ = "";
    private DocAnnotations.PlusOneData plusOneData_ = null;
    private String relatedBrowseUrl_ = "";
    private String relatedHeader_ = "";
    private String relatedListUrl_ = "";
    private String reviewsUrl_ = "";
    private String shareUrl_ = "";
    private String title_ = "";
    private String warningMessage_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
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

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public String getDocid()
    {
      return this.docid_;
    }

    public Doc.Document getFinskyDoc()
    {
      return this.finskyDoc_;
    }

    public String getMoreByBrowseUrl()
    {
      return this.moreByBrowseUrl_;
    }

    public String getMoreByHeader()
    {
      return this.moreByHeader_;
    }

    public String getMoreByListUrl()
    {
      return this.moreByListUrl_;
    }

    public DocAnnotations.PlusOneData getPlusOneData()
    {
      return this.plusOneData_;
    }

    public String getRelatedBrowseUrl()
    {
      return this.relatedBrowseUrl_;
    }

    public String getRelatedHeader()
    {
      return this.relatedHeader_;
    }

    public String getRelatedListUrl()
    {
      return this.relatedListUrl_;
    }

    public String getReviewsUrl()
    {
      return this.reviewsUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasFinskyDoc())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getFinskyDoc());
      if (hasDocid())
        i += CodedOutputStreamMicro.computeStringSize(2, getDocid());
      if (hasDetailsUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getDetailsUrl());
      if (hasReviewsUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getReviewsUrl());
      if (hasRelatedListUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getRelatedListUrl());
      if (hasMoreByListUrl())
        i += CodedOutputStreamMicro.computeStringSize(6, getMoreByListUrl());
      if (hasShareUrl())
        i += CodedOutputStreamMicro.computeStringSize(7, getShareUrl());
      if (hasCreator())
        i += CodedOutputStreamMicro.computeStringSize(8, getCreator());
      if (hasDetails())
        i += CodedOutputStreamMicro.computeMessageSize(9, getDetails());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(10, getDescriptionHtml());
      if (hasRelatedBrowseUrl())
        i += CodedOutputStreamMicro.computeStringSize(11, getRelatedBrowseUrl());
      if (hasMoreByBrowseUrl())
        i += CodedOutputStreamMicro.computeStringSize(12, getMoreByBrowseUrl());
      if (hasRelatedHeader())
        i += CodedOutputStreamMicro.computeStringSize(13, getRelatedHeader());
      if (hasMoreByHeader())
        i += CodedOutputStreamMicro.computeStringSize(14, getMoreByHeader());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(15, getTitle());
      if (hasPlusOneData())
        i += CodedOutputStreamMicro.computeMessageSize(16, getPlusOneData());
      if (hasWarningMessage())
        i += CodedOutputStreamMicro.computeStringSize(17, getWarningMessage());
      this.cachedSize = i;
      return i;
    }

    public String getShareUrl()
    {
      return this.shareUrl_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public String getWarningMessage()
    {
      return this.warningMessage_;
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

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasFinskyDoc()
    {
      return this.hasFinskyDoc;
    }

    public boolean hasMoreByBrowseUrl()
    {
      return this.hasMoreByBrowseUrl;
    }

    public boolean hasMoreByHeader()
    {
      return this.hasMoreByHeader;
    }

    public boolean hasMoreByListUrl()
    {
      return this.hasMoreByListUrl;
    }

    public boolean hasPlusOneData()
    {
      return this.hasPlusOneData;
    }

    public boolean hasRelatedBrowseUrl()
    {
      return this.hasRelatedBrowseUrl;
    }

    public boolean hasRelatedHeader()
    {
      return this.hasRelatedHeader;
    }

    public boolean hasRelatedListUrl()
    {
      return this.hasRelatedListUrl;
    }

    public boolean hasReviewsUrl()
    {
      return this.hasReviewsUrl;
    }

    public boolean hasShareUrl()
    {
      return this.hasShareUrl;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasWarningMessage()
    {
      return this.hasWarningMessage;
    }

    public DocV1 mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Doc.Document localDocument = new Doc.Document();
          paramCodedInputStreamMicro.readMessage(localDocument);
          setFinskyDoc(localDocument);
          break;
        case 18:
          setDocid(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setReviewsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setRelatedListUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setMoreByListUrl(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setShareUrl(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setCreator(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          DocDetails.DocumentDetails localDocumentDetails = new DocDetails.DocumentDetails();
          paramCodedInputStreamMicro.readMessage(localDocumentDetails);
          setDetails(localDocumentDetails);
          break;
        case 82:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setRelatedBrowseUrl(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setMoreByBrowseUrl(paramCodedInputStreamMicro.readString());
          break;
        case 106:
          setRelatedHeader(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          setMoreByHeader(paramCodedInputStreamMicro.readString());
          break;
        case 122:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 130:
          DocAnnotations.PlusOneData localPlusOneData = new DocAnnotations.PlusOneData();
          paramCodedInputStreamMicro.readMessage(localPlusOneData);
          setPlusOneData(localPlusOneData);
          break;
        case 138:
        }
        setWarningMessage(paramCodedInputStreamMicro.readString());
      }
    }

    public DocV1 setCreator(String paramString)
    {
      this.hasCreator = true;
      this.creator_ = paramString;
      return this;
    }

    public DocV1 setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public DocV1 setDetails(DocDetails.DocumentDetails paramDocumentDetails)
    {
      if (paramDocumentDetails == null)
        throw new NullPointerException();
      this.hasDetails = true;
      this.details_ = paramDocumentDetails;
      return this;
    }

    public DocV1 setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public DocV1 setDocid(String paramString)
    {
      this.hasDocid = true;
      this.docid_ = paramString;
      return this;
    }

    public DocV1 setFinskyDoc(Doc.Document paramDocument)
    {
      if (paramDocument == null)
        throw new NullPointerException();
      this.hasFinskyDoc = true;
      this.finskyDoc_ = paramDocument;
      return this;
    }

    public DocV1 setMoreByBrowseUrl(String paramString)
    {
      this.hasMoreByBrowseUrl = true;
      this.moreByBrowseUrl_ = paramString;
      return this;
    }

    public DocV1 setMoreByHeader(String paramString)
    {
      this.hasMoreByHeader = true;
      this.moreByHeader_ = paramString;
      return this;
    }

    public DocV1 setMoreByListUrl(String paramString)
    {
      this.hasMoreByListUrl = true;
      this.moreByListUrl_ = paramString;
      return this;
    }

    public DocV1 setPlusOneData(DocAnnotations.PlusOneData paramPlusOneData)
    {
      if (paramPlusOneData == null)
        throw new NullPointerException();
      this.hasPlusOneData = true;
      this.plusOneData_ = paramPlusOneData;
      return this;
    }

    public DocV1 setRelatedBrowseUrl(String paramString)
    {
      this.hasRelatedBrowseUrl = true;
      this.relatedBrowseUrl_ = paramString;
      return this;
    }

    public DocV1 setRelatedHeader(String paramString)
    {
      this.hasRelatedHeader = true;
      this.relatedHeader_ = paramString;
      return this;
    }

    public DocV1 setRelatedListUrl(String paramString)
    {
      this.hasRelatedListUrl = true;
      this.relatedListUrl_ = paramString;
      return this;
    }

    public DocV1 setReviewsUrl(String paramString)
    {
      this.hasReviewsUrl = true;
      this.reviewsUrl_ = paramString;
      return this;
    }

    public DocV1 setShareUrl(String paramString)
    {
      this.hasShareUrl = true;
      this.shareUrl_ = paramString;
      return this;
    }

    public DocV1 setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public DocV1 setWarningMessage(String paramString)
    {
      this.hasWarningMessage = true;
      this.warningMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasFinskyDoc())
        paramCodedOutputStreamMicro.writeMessage(1, getFinskyDoc());
      if (hasDocid())
        paramCodedOutputStreamMicro.writeString(2, getDocid());
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(3, getDetailsUrl());
      if (hasReviewsUrl())
        paramCodedOutputStreamMicro.writeString(4, getReviewsUrl());
      if (hasRelatedListUrl())
        paramCodedOutputStreamMicro.writeString(5, getRelatedListUrl());
      if (hasMoreByListUrl())
        paramCodedOutputStreamMicro.writeString(6, getMoreByListUrl());
      if (hasShareUrl())
        paramCodedOutputStreamMicro.writeString(7, getShareUrl());
      if (hasCreator())
        paramCodedOutputStreamMicro.writeString(8, getCreator());
      if (hasDetails())
        paramCodedOutputStreamMicro.writeMessage(9, getDetails());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(10, getDescriptionHtml());
      if (hasRelatedBrowseUrl())
        paramCodedOutputStreamMicro.writeString(11, getRelatedBrowseUrl());
      if (hasMoreByBrowseUrl())
        paramCodedOutputStreamMicro.writeString(12, getMoreByBrowseUrl());
      if (hasRelatedHeader())
        paramCodedOutputStreamMicro.writeString(13, getRelatedHeader());
      if (hasMoreByHeader())
        paramCodedOutputStreamMicro.writeString(14, getMoreByHeader());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(15, getTitle());
      if (hasPlusOneData())
        paramCodedOutputStreamMicro.writeMessage(16, getPlusOneData());
      if (hasWarningMessage())
        paramCodedOutputStreamMicro.writeString(17, getWarningMessage());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DocumentV1
 * JD-Core Version:    0.6.2
 */
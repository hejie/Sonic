package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Rev
{
  public static final class GetReviewsResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasMatchingCount;
    private long matchingCount_ = 0L;
    private List<Rev.Review> review_ = Collections.emptyList();

    public GetReviewsResponse addReview(Rev.Review paramReview)
    {
      if (paramReview == null)
        throw new NullPointerException();
      if (this.review_.isEmpty())
        this.review_ = new ArrayList();
      this.review_.add(paramReview);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getMatchingCount()
    {
      return this.matchingCount_;
    }

    public List<Rev.Review> getReviewList()
    {
      return this.review_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getReviewList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (Rev.Review)localIterator.next());
      if (hasMatchingCount())
        i += CodedOutputStreamMicro.computeInt64Size(2, getMatchingCount());
      this.cachedSize = i;
      return i;
    }

    public boolean hasMatchingCount()
    {
      return this.hasMatchingCount;
    }

    public GetReviewsResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Rev.Review localReview = new Rev.Review();
          paramCodedInputStreamMicro.readMessage(localReview);
          addReview(localReview);
          break;
        case 16:
        }
        setMatchingCount(paramCodedInputStreamMicro.readInt64());
      }
    }

    public GetReviewsResponse setMatchingCount(long paramLong)
    {
      this.hasMatchingCount = true;
      this.matchingCount_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getReviewList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (Rev.Review)localIterator.next());
      if (hasMatchingCount())
        paramCodedOutputStreamMicro.writeInt64(2, getMatchingCount());
    }
  }

  public static final class Review extends MessageMicro
  {
    private String authorName_ = "";
    private int cachedSize = -1;
    private String commentId_ = "";
    private String comment_ = "";
    private String deviceName_ = "";
    private String documentVersion_ = "";
    private boolean hasAuthorName;
    private boolean hasComment;
    private boolean hasCommentId;
    private boolean hasDeviceName;
    private boolean hasDocumentVersion;
    private boolean hasPlusProfile;
    private boolean hasReplyText;
    private boolean hasReplyTimestampMsec;
    private boolean hasSource;
    private boolean hasStarRating;
    private boolean hasTimestampMsec;
    private boolean hasTitle;
    private boolean hasUrl;
    private DocAnnotations.PlusProfile plusProfile_ = null;
    private String replyText_ = "";
    private long replyTimestampMsec_ = 0L;
    private String source_ = "";
    private int starRating_ = 0;
    private long timestampMsec_ = 0L;
    private String title_ = "";
    private String url_ = "";

    public String getAuthorName()
    {
      return this.authorName_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getComment()
    {
      return this.comment_;
    }

    public String getCommentId()
    {
      return this.commentId_;
    }

    public String getDeviceName()
    {
      return this.deviceName_;
    }

    public String getDocumentVersion()
    {
      return this.documentVersion_;
    }

    public DocAnnotations.PlusProfile getPlusProfile()
    {
      return this.plusProfile_;
    }

    public String getReplyText()
    {
      return this.replyText_;
    }

    public long getReplyTimestampMsec()
    {
      return this.replyTimestampMsec_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAuthorName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAuthorName());
      if (hasUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getUrl());
      if (hasSource())
        i += CodedOutputStreamMicro.computeStringSize(3, getSource());
      if (hasDocumentVersion())
        i += CodedOutputStreamMicro.computeStringSize(4, getDocumentVersion());
      if (hasTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(5, getTimestampMsec());
      if (hasStarRating())
        i += CodedOutputStreamMicro.computeInt32Size(6, getStarRating());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(7, getTitle());
      if (hasComment())
        i += CodedOutputStreamMicro.computeStringSize(8, getComment());
      if (hasCommentId())
        i += CodedOutputStreamMicro.computeStringSize(9, getCommentId());
      if (hasDeviceName())
        i += CodedOutputStreamMicro.computeStringSize(19, getDeviceName());
      if (hasReplyText())
        i += CodedOutputStreamMicro.computeStringSize(29, getReplyText());
      if (hasReplyTimestampMsec())
        i += CodedOutputStreamMicro.computeInt64Size(30, getReplyTimestampMsec());
      if (hasPlusProfile())
        i += CodedOutputStreamMicro.computeMessageSize(31, getPlusProfile());
      this.cachedSize = i;
      return i;
    }

    public String getSource()
    {
      return this.source_;
    }

    public int getStarRating()
    {
      return this.starRating_;
    }

    public long getTimestampMsec()
    {
      return this.timestampMsec_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public boolean hasAuthorName()
    {
      return this.hasAuthorName;
    }

    public boolean hasComment()
    {
      return this.hasComment;
    }

    public boolean hasCommentId()
    {
      return this.hasCommentId;
    }

    public boolean hasDeviceName()
    {
      return this.hasDeviceName;
    }

    public boolean hasDocumentVersion()
    {
      return this.hasDocumentVersion;
    }

    public boolean hasPlusProfile()
    {
      return this.hasPlusProfile;
    }

    public boolean hasReplyText()
    {
      return this.hasReplyText;
    }

    public boolean hasReplyTimestampMsec()
    {
      return this.hasReplyTimestampMsec;
    }

    public boolean hasSource()
    {
      return this.hasSource;
    }

    public boolean hasStarRating()
    {
      return this.hasStarRating;
    }

    public boolean hasTimestampMsec()
    {
      return this.hasTimestampMsec;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public Review mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAuthorName(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setSource(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setDocumentVersion(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 48:
          setStarRating(paramCodedInputStreamMicro.readInt32());
          break;
        case 58:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setComment(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setCommentId(paramCodedInputStreamMicro.readString());
          break;
        case 154:
          setDeviceName(paramCodedInputStreamMicro.readString());
          break;
        case 234:
          setReplyText(paramCodedInputStreamMicro.readString());
          break;
        case 240:
          setReplyTimestampMsec(paramCodedInputStreamMicro.readInt64());
          break;
        case 250:
        }
        DocAnnotations.PlusProfile localPlusProfile = new DocAnnotations.PlusProfile();
        paramCodedInputStreamMicro.readMessage(localPlusProfile);
        setPlusProfile(localPlusProfile);
      }
    }

    public Review setAuthorName(String paramString)
    {
      this.hasAuthorName = true;
      this.authorName_ = paramString;
      return this;
    }

    public Review setComment(String paramString)
    {
      this.hasComment = true;
      this.comment_ = paramString;
      return this;
    }

    public Review setCommentId(String paramString)
    {
      this.hasCommentId = true;
      this.commentId_ = paramString;
      return this;
    }

    public Review setDeviceName(String paramString)
    {
      this.hasDeviceName = true;
      this.deviceName_ = paramString;
      return this;
    }

    public Review setDocumentVersion(String paramString)
    {
      this.hasDocumentVersion = true;
      this.documentVersion_ = paramString;
      return this;
    }

    public Review setPlusProfile(DocAnnotations.PlusProfile paramPlusProfile)
    {
      if (paramPlusProfile == null)
        throw new NullPointerException();
      this.hasPlusProfile = true;
      this.plusProfile_ = paramPlusProfile;
      return this;
    }

    public Review setReplyText(String paramString)
    {
      this.hasReplyText = true;
      this.replyText_ = paramString;
      return this;
    }

    public Review setReplyTimestampMsec(long paramLong)
    {
      this.hasReplyTimestampMsec = true;
      this.replyTimestampMsec_ = paramLong;
      return this;
    }

    public Review setSource(String paramString)
    {
      this.hasSource = true;
      this.source_ = paramString;
      return this;
    }

    public Review setStarRating(int paramInt)
    {
      this.hasStarRating = true;
      this.starRating_ = paramInt;
      return this;
    }

    public Review setTimestampMsec(long paramLong)
    {
      this.hasTimestampMsec = true;
      this.timestampMsec_ = paramLong;
      return this;
    }

    public Review setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public Review setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAuthorName())
        paramCodedOutputStreamMicro.writeString(1, getAuthorName());
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(2, getUrl());
      if (hasSource())
        paramCodedOutputStreamMicro.writeString(3, getSource());
      if (hasDocumentVersion())
        paramCodedOutputStreamMicro.writeString(4, getDocumentVersion());
      if (hasTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(5, getTimestampMsec());
      if (hasStarRating())
        paramCodedOutputStreamMicro.writeInt32(6, getStarRating());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(7, getTitle());
      if (hasComment())
        paramCodedOutputStreamMicro.writeString(8, getComment());
      if (hasCommentId())
        paramCodedOutputStreamMicro.writeString(9, getCommentId());
      if (hasDeviceName())
        paramCodedOutputStreamMicro.writeString(19, getDeviceName());
      if (hasReplyText())
        paramCodedOutputStreamMicro.writeString(29, getReplyText());
      if (hasReplyTimestampMsec())
        paramCodedOutputStreamMicro.writeInt64(30, getReplyTimestampMsec());
      if (hasPlusProfile())
        paramCodedOutputStreamMicro.writeMessage(31, getPlusProfile());
    }
  }

  public static final class ReviewResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private Rev.GetReviewsResponse getResponse_ = null;
    private boolean hasGetResponse;
    private boolean hasNextPageUrl;
    private String nextPageUrl_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Rev.GetReviewsResponse getGetResponse()
    {
      return this.getResponse_;
    }

    public String getNextPageUrl()
    {
      return this.nextPageUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGetResponse())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getGetResponse());
      if (hasNextPageUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getNextPageUrl());
      this.cachedSize = i;
      return i;
    }

    public boolean hasGetResponse()
    {
      return this.hasGetResponse;
    }

    public boolean hasNextPageUrl()
    {
      return this.hasNextPageUrl;
    }

    public ReviewResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Rev.GetReviewsResponse localGetReviewsResponse = new Rev.GetReviewsResponse();
          paramCodedInputStreamMicro.readMessage(localGetReviewsResponse);
          setGetResponse(localGetReviewsResponse);
          break;
        case 18:
        }
        setNextPageUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public ReviewResponse setGetResponse(Rev.GetReviewsResponse paramGetReviewsResponse)
    {
      if (paramGetReviewsResponse == null)
        throw new NullPointerException();
      this.hasGetResponse = true;
      this.getResponse_ = paramGetReviewsResponse;
      return this;
    }

    public ReviewResponse setNextPageUrl(String paramString)
    {
      this.hasNextPageUrl = true;
      this.nextPageUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGetResponse())
        paramCodedOutputStreamMicro.writeMessage(1, getGetResponse());
      if (hasNextPageUrl())
        paramCodedOutputStreamMicro.writeString(2, getNextPageUrl());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Rev
 * JD-Core Version:    0.6.2
 */
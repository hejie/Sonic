package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Search
{
  public static final class RelatedSearch extends MessageMicro
  {
    private int backendId_ = 0;
    private int cachedSize = -1;
    private boolean current_ = false;
    private int docType_ = 1;
    private boolean hasBackendId;
    private boolean hasCurrent;
    private boolean hasDocType;
    private boolean hasHeader;
    private boolean hasSearchUrl;
    private String header_ = "";
    private String searchUrl_ = "";

    public int getBackendId()
    {
      return this.backendId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCurrent()
    {
      return this.current_;
    }

    public int getDocType()
    {
      return this.docType_;
    }

    public String getHeader()
    {
      return this.header_;
    }

    public String getSearchUrl()
    {
      return this.searchUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSearchUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSearchUrl());
      if (hasHeader())
        i += CodedOutputStreamMicro.computeStringSize(2, getHeader());
      if (hasBackendId())
        i += CodedOutputStreamMicro.computeInt32Size(3, getBackendId());
      if (hasDocType())
        i += CodedOutputStreamMicro.computeInt32Size(4, getDocType());
      if (hasCurrent())
        i += CodedOutputStreamMicro.computeBoolSize(5, getCurrent());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBackendId()
    {
      return this.hasBackendId;
    }

    public boolean hasCurrent()
    {
      return this.hasCurrent;
    }

    public boolean hasDocType()
    {
      return this.hasDocType;
    }

    public boolean hasHeader()
    {
      return this.hasHeader;
    }

    public boolean hasSearchUrl()
    {
      return this.hasSearchUrl;
    }

    public RelatedSearch mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSearchUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setHeader(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setBackendId(paramCodedInputStreamMicro.readInt32());
          break;
        case 32:
          setDocType(paramCodedInputStreamMicro.readInt32());
          break;
        case 40:
        }
        setCurrent(paramCodedInputStreamMicro.readBool());
      }
    }

    public RelatedSearch setBackendId(int paramInt)
    {
      this.hasBackendId = true;
      this.backendId_ = paramInt;
      return this;
    }

    public RelatedSearch setCurrent(boolean paramBoolean)
    {
      this.hasCurrent = true;
      this.current_ = paramBoolean;
      return this;
    }

    public RelatedSearch setDocType(int paramInt)
    {
      this.hasDocType = true;
      this.docType_ = paramInt;
      return this;
    }

    public RelatedSearch setHeader(String paramString)
    {
      this.hasHeader = true;
      this.header_ = paramString;
      return this;
    }

    public RelatedSearch setSearchUrl(String paramString)
    {
      this.hasSearchUrl = true;
      this.searchUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSearchUrl())
        paramCodedOutputStreamMicro.writeString(1, getSearchUrl());
      if (hasHeader())
        paramCodedOutputStreamMicro.writeString(2, getHeader());
      if (hasBackendId())
        paramCodedOutputStreamMicro.writeInt32(3, getBackendId());
      if (hasDocType())
        paramCodedOutputStreamMicro.writeInt32(4, getDocType());
      if (hasCurrent())
        paramCodedOutputStreamMicro.writeBool(5, getCurrent());
    }
  }

  public static final class SearchResponse extends MessageMicro
  {
    private boolean aggregateQuery_ = false;
    private List<DocList.Bucket> bucket_ = Collections.emptyList();
    private int cachedSize = -1;
    private List<DocumentV2.DocV2> doc_ = Collections.emptyList();
    private boolean hasAggregateQuery;
    private boolean hasOriginalQuery;
    private boolean hasSuggestedQuery;
    private String originalQuery_ = "";
    private List<Search.RelatedSearch> relatedSearch_ = Collections.emptyList();
    private String suggestedQuery_ = "";

    public SearchResponse addBucket(DocList.Bucket paramBucket)
    {
      if (paramBucket == null)
        throw new NullPointerException();
      if (this.bucket_.isEmpty())
        this.bucket_ = new ArrayList();
      this.bucket_.add(paramBucket);
      return this;
    }

    public SearchResponse addDoc(DocumentV2.DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      if (this.doc_.isEmpty())
        this.doc_ = new ArrayList();
      this.doc_.add(paramDocV2);
      return this;
    }

    public SearchResponse addRelatedSearch(Search.RelatedSearch paramRelatedSearch)
    {
      if (paramRelatedSearch == null)
        throw new NullPointerException();
      if (this.relatedSearch_.isEmpty())
        this.relatedSearch_ = new ArrayList();
      this.relatedSearch_.add(paramRelatedSearch);
      return this;
    }

    public boolean getAggregateQuery()
    {
      return this.aggregateQuery_;
    }

    public List<DocList.Bucket> getBucketList()
    {
      return this.bucket_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocumentV2.DocV2 getDoc(int paramInt)
    {
      return (DocumentV2.DocV2)this.doc_.get(paramInt);
    }

    public int getDocCount()
    {
      return this.doc_.size();
    }

    public List<DocumentV2.DocV2> getDocList()
    {
      return this.doc_;
    }

    public String getOriginalQuery()
    {
      return this.originalQuery_;
    }

    public List<Search.RelatedSearch> getRelatedSearchList()
    {
      return this.relatedSearch_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasOriginalQuery())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getOriginalQuery());
      if (hasSuggestedQuery())
        i += CodedOutputStreamMicro.computeStringSize(2, getSuggestedQuery());
      if (hasAggregateQuery())
        i += CodedOutputStreamMicro.computeBoolSize(3, getAggregateQuery());
      Iterator localIterator1 = getBucketList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (DocList.Bucket)localIterator1.next());
      Iterator localIterator2 = getDocList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(5, (DocumentV2.DocV2)localIterator2.next());
      Iterator localIterator3 = getRelatedSearchList().iterator();
      while (localIterator3.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(6, (Search.RelatedSearch)localIterator3.next());
      this.cachedSize = i;
      return i;
    }

    public String getSuggestedQuery()
    {
      return this.suggestedQuery_;
    }

    public boolean hasAggregateQuery()
    {
      return this.hasAggregateQuery;
    }

    public boolean hasOriginalQuery()
    {
      return this.hasOriginalQuery;
    }

    public boolean hasSuggestedQuery()
    {
      return this.hasSuggestedQuery;
    }

    public SearchResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setOriginalQuery(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setSuggestedQuery(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setAggregateQuery(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          DocList.Bucket localBucket = new DocList.Bucket();
          paramCodedInputStreamMicro.readMessage(localBucket);
          addBucket(localBucket);
          break;
        case 42:
          DocumentV2.DocV2 localDocV2 = new DocumentV2.DocV2();
          paramCodedInputStreamMicro.readMessage(localDocV2);
          addDoc(localDocV2);
          break;
        case 50:
        }
        Search.RelatedSearch localRelatedSearch = new Search.RelatedSearch();
        paramCodedInputStreamMicro.readMessage(localRelatedSearch);
        addRelatedSearch(localRelatedSearch);
      }
    }

    public SearchResponse setAggregateQuery(boolean paramBoolean)
    {
      this.hasAggregateQuery = true;
      this.aggregateQuery_ = paramBoolean;
      return this;
    }

    public SearchResponse setOriginalQuery(String paramString)
    {
      this.hasOriginalQuery = true;
      this.originalQuery_ = paramString;
      return this;
    }

    public SearchResponse setSuggestedQuery(String paramString)
    {
      this.hasSuggestedQuery = true;
      this.suggestedQuery_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasOriginalQuery())
        paramCodedOutputStreamMicro.writeString(1, getOriginalQuery());
      if (hasSuggestedQuery())
        paramCodedOutputStreamMicro.writeString(2, getSuggestedQuery());
      if (hasAggregateQuery())
        paramCodedOutputStreamMicro.writeBool(3, getAggregateQuery());
      Iterator localIterator1 = getBucketList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (DocList.Bucket)localIterator1.next());
      Iterator localIterator2 = getDocList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(5, (DocumentV2.DocV2)localIterator2.next());
      Iterator localIterator3 = getRelatedSearchList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(6, (Search.RelatedSearch)localIterator3.next());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Search
 * JD-Core Version:    0.6.2
 */
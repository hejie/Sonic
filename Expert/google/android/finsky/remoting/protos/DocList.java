package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DocList
{
  public static final class Bucket extends MessageMicro
  {
    private String analyticsCookie_ = "";
    private int cachedSize = -1;
    private List<DocumentV1.DocV1> document_ = Collections.emptyList();
    private long estimatedResults_ = 0L;
    private String fullContentsListUrl_ = "";
    private String fullContentsUrl_ = "";
    private boolean hasAnalyticsCookie;
    private boolean hasEstimatedResults;
    private boolean hasFullContentsListUrl;
    private boolean hasFullContentsUrl;
    private boolean hasIconUrl;
    private boolean hasMultiCorpus;
    private boolean hasNextPageUrl;
    private boolean hasOrdered;
    private boolean hasRelevance;
    private boolean hasTitle;
    private String iconUrl_ = "";
    private boolean multiCorpus_ = false;
    private String nextPageUrl_ = "";
    private boolean ordered_ = false;
    private double relevance_ = 0.0D;
    private String title_ = "";

    public Bucket addDocument(DocumentV1.DocV1 paramDocV1)
    {
      if (paramDocV1 == null)
        throw new NullPointerException();
      if (this.document_.isEmpty())
        this.document_ = new ArrayList();
      this.document_.add(paramDocV1);
      return this;
    }

    public String getAnalyticsCookie()
    {
      return this.analyticsCookie_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<DocumentV1.DocV1> getDocumentList()
    {
      return this.document_;
    }

    public long getEstimatedResults()
    {
      return this.estimatedResults_;
    }

    public String getFullContentsListUrl()
    {
      return this.fullContentsListUrl_;
    }

    public String getFullContentsUrl()
    {
      return this.fullContentsUrl_;
    }

    public String getIconUrl()
    {
      return this.iconUrl_;
    }

    public boolean getMultiCorpus()
    {
      return this.multiCorpus_;
    }

    public String getNextPageUrl()
    {
      return this.nextPageUrl_;
    }

    public boolean getOrdered()
    {
      return this.ordered_;
    }

    public double getRelevance()
    {
      return this.relevance_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getDocumentList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (DocumentV1.DocV1)localIterator.next());
      if (hasMultiCorpus())
        i += CodedOutputStreamMicro.computeBoolSize(2, getMultiCorpus());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getTitle());
      if (hasIconUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getIconUrl());
      if (hasFullContentsUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getFullContentsUrl());
      if (hasRelevance())
        i += CodedOutputStreamMicro.computeDoubleSize(6, getRelevance());
      if (hasEstimatedResults())
        i += CodedOutputStreamMicro.computeInt64Size(7, getEstimatedResults());
      if (hasAnalyticsCookie())
        i += CodedOutputStreamMicro.computeStringSize(8, getAnalyticsCookie());
      if (hasFullContentsListUrl())
        i += CodedOutputStreamMicro.computeStringSize(9, getFullContentsListUrl());
      if (hasNextPageUrl())
        i += CodedOutputStreamMicro.computeStringSize(10, getNextPageUrl());
      if (hasOrdered())
        i += CodedOutputStreamMicro.computeBoolSize(11, getOrdered());
      this.cachedSize = i;
      return i;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasAnalyticsCookie()
    {
      return this.hasAnalyticsCookie;
    }

    public boolean hasEstimatedResults()
    {
      return this.hasEstimatedResults;
    }

    public boolean hasFullContentsListUrl()
    {
      return this.hasFullContentsListUrl;
    }

    public boolean hasFullContentsUrl()
    {
      return this.hasFullContentsUrl;
    }

    public boolean hasIconUrl()
    {
      return this.hasIconUrl;
    }

    public boolean hasMultiCorpus()
    {
      return this.hasMultiCorpus;
    }

    public boolean hasNextPageUrl()
    {
      return this.hasNextPageUrl;
    }

    public boolean hasOrdered()
    {
      return this.hasOrdered;
    }

    public boolean hasRelevance()
    {
      return this.hasRelevance;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public Bucket mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocumentV1.DocV1 localDocV1 = new DocumentV1.DocV1();
          paramCodedInputStreamMicro.readMessage(localDocV1);
          addDocument(localDocV1);
          break;
        case 16:
          setMultiCorpus(paramCodedInputStreamMicro.readBool());
          break;
        case 26:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setIconUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setFullContentsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 49:
          setRelevance(paramCodedInputStreamMicro.readDouble());
          break;
        case 56:
          setEstimatedResults(paramCodedInputStreamMicro.readInt64());
          break;
        case 66:
          setAnalyticsCookie(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setFullContentsListUrl(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          setNextPageUrl(paramCodedInputStreamMicro.readString());
          break;
        case 88:
        }
        setOrdered(paramCodedInputStreamMicro.readBool());
      }
    }

    public Bucket setAnalyticsCookie(String paramString)
    {
      this.hasAnalyticsCookie = true;
      this.analyticsCookie_ = paramString;
      return this;
    }

    public Bucket setEstimatedResults(long paramLong)
    {
      this.hasEstimatedResults = true;
      this.estimatedResults_ = paramLong;
      return this;
    }

    public Bucket setFullContentsListUrl(String paramString)
    {
      this.hasFullContentsListUrl = true;
      this.fullContentsListUrl_ = paramString;
      return this;
    }

    public Bucket setFullContentsUrl(String paramString)
    {
      this.hasFullContentsUrl = true;
      this.fullContentsUrl_ = paramString;
      return this;
    }

    public Bucket setIconUrl(String paramString)
    {
      this.hasIconUrl = true;
      this.iconUrl_ = paramString;
      return this;
    }

    public Bucket setMultiCorpus(boolean paramBoolean)
    {
      this.hasMultiCorpus = true;
      this.multiCorpus_ = paramBoolean;
      return this;
    }

    public Bucket setNextPageUrl(String paramString)
    {
      this.hasNextPageUrl = true;
      this.nextPageUrl_ = paramString;
      return this;
    }

    public Bucket setOrdered(boolean paramBoolean)
    {
      this.hasOrdered = true;
      this.ordered_ = paramBoolean;
      return this;
    }

    public Bucket setRelevance(double paramDouble)
    {
      this.hasRelevance = true;
      this.relevance_ = paramDouble;
      return this;
    }

    public Bucket setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getDocumentList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (DocumentV1.DocV1)localIterator.next());
      if (hasMultiCorpus())
        paramCodedOutputStreamMicro.writeBool(2, getMultiCorpus());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(3, getTitle());
      if (hasIconUrl())
        paramCodedOutputStreamMicro.writeString(4, getIconUrl());
      if (hasFullContentsUrl())
        paramCodedOutputStreamMicro.writeString(5, getFullContentsUrl());
      if (hasRelevance())
        paramCodedOutputStreamMicro.writeDouble(6, getRelevance());
      if (hasEstimatedResults())
        paramCodedOutputStreamMicro.writeInt64(7, getEstimatedResults());
      if (hasAnalyticsCookie())
        paramCodedOutputStreamMicro.writeString(8, getAnalyticsCookie());
      if (hasFullContentsListUrl())
        paramCodedOutputStreamMicro.writeString(9, getFullContentsListUrl());
      if (hasNextPageUrl())
        paramCodedOutputStreamMicro.writeString(10, getNextPageUrl());
      if (hasOrdered())
        paramCodedOutputStreamMicro.writeBool(11, getOrdered());
    }
  }

  public static final class ListResponse extends MessageMicro
  {
    private List<DocList.Bucket> bucket_ = Collections.emptyList();
    private int cachedSize = -1;
    private List<DocumentV2.DocV2> doc_ = Collections.emptyList();

    public ListResponse addBucket(DocList.Bucket paramBucket)
    {
      if (paramBucket == null)
        throw new NullPointerException();
      if (this.bucket_.isEmpty())
        this.bucket_ = new ArrayList();
      this.bucket_.add(paramBucket);
      return this;
    }

    public ListResponse addDoc(DocumentV2.DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      if (this.doc_.isEmpty())
        this.doc_ = new ArrayList();
      this.doc_.add(paramDocV2);
      return this;
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

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getBucketList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (DocList.Bucket)localIterator1.next());
      Iterator localIterator2 = getDocList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(2, (DocumentV2.DocV2)localIterator2.next());
      this.cachedSize = i;
      return i;
    }

    public ListResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocList.Bucket localBucket = new DocList.Bucket();
          paramCodedInputStreamMicro.readMessage(localBucket);
          addBucket(localBucket);
          break;
        case 18:
        }
        DocumentV2.DocV2 localDocV2 = new DocumentV2.DocV2();
        paramCodedInputStreamMicro.readMessage(localDocV2);
        addDoc(localDocV2);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getBucketList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (DocList.Bucket)localIterator1.next());
      Iterator localIterator2 = getDocList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(2, (DocumentV2.DocV2)localIterator2.next());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DocList
 * JD-Core Version:    0.6.2
 */
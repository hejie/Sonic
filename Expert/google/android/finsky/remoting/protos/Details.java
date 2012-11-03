package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Details
{
  public static final class BulkDetailsEntry extends MessageMicro
  {
    private int cachedSize = -1;
    private DocumentV2.DocV2 doc_ = null;
    private boolean hasDoc;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocumentV2.DocV2 getDoc()
    {
      return this.doc_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDoc())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDoc());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDoc()
    {
      return this.hasDoc;
    }

    public BulkDetailsEntry mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        DocumentV2.DocV2 localDocV2 = new DocumentV2.DocV2();
        paramCodedInputStreamMicro.readMessage(localDocV2);
        setDoc(localDocV2);
      }
    }

    public BulkDetailsEntry setDoc(DocumentV2.DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      this.hasDoc = true;
      this.doc_ = paramDocV2;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDoc())
        paramCodedOutputStreamMicro.writeMessage(1, getDoc());
    }
  }

  public static final class BulkDetailsRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private List<String> docid_ = Collections.emptyList();
    private boolean hasIncludeChildDocs;
    private boolean includeChildDocs_ = true;

    public BulkDetailsRequest addDocid(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.docid_.isEmpty())
        this.docid_ = new ArrayList();
      this.docid_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getDocidList()
    {
      return this.docid_;
    }

    public boolean getIncludeChildDocs()
    {
      return this.includeChildDocs_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getDocidList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int j = 0 + i + 1 * getDocidList().size();
      if (hasIncludeChildDocs())
        j += CodedOutputStreamMicro.computeBoolSize(2, getIncludeChildDocs());
      this.cachedSize = j;
      return j;
    }

    public boolean hasIncludeChildDocs()
    {
      return this.hasIncludeChildDocs;
    }

    public BulkDetailsRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          addDocid(paramCodedInputStreamMicro.readString());
          break;
        case 16:
        }
        setIncludeChildDocs(paramCodedInputStreamMicro.readBool());
      }
    }

    public BulkDetailsRequest setIncludeChildDocs(boolean paramBoolean)
    {
      this.hasIncludeChildDocs = true;
      this.includeChildDocs_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getDocidList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(1, (String)localIterator.next());
      if (hasIncludeChildDocs())
        paramCodedOutputStreamMicro.writeBool(2, getIncludeChildDocs());
    }
  }

  public static final class BulkDetailsResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private List<Details.BulkDetailsEntry> entry_ = Collections.emptyList();

    public BulkDetailsResponse addEntry(Details.BulkDetailsEntry paramBulkDetailsEntry)
    {
      if (paramBulkDetailsEntry == null)
        throw new NullPointerException();
      if (this.entry_.isEmpty())
        this.entry_ = new ArrayList();
      this.entry_.add(paramBulkDetailsEntry);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Details.BulkDetailsEntry getEntry(int paramInt)
    {
      return (Details.BulkDetailsEntry)this.entry_.get(paramInt);
    }

    public int getEntryCount()
    {
      return this.entry_.size();
    }

    public List<Details.BulkDetailsEntry> getEntryList()
    {
      return this.entry_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getEntryList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (Details.BulkDetailsEntry)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public BulkDetailsResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        Details.BulkDetailsEntry localBulkDetailsEntry = new Details.BulkDetailsEntry();
        paramCodedInputStreamMicro.readMessage(localBulkDetailsEntry);
        addEntry(localBulkDetailsEntry);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getEntryList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (Details.BulkDetailsEntry)localIterator.next());
    }
  }

  public static final class DetailsResponse extends MessageMicro
  {
    private String analyticsCookie_ = "";
    private int cachedSize = -1;
    private DocumentV1.DocV1 docV1_ = null;
    private DocumentV2.DocV2 docV2_ = null;
    private String footerHtml_ = "";
    private boolean hasAnalyticsCookie;
    private boolean hasDocV1;
    private boolean hasDocV2;
    private boolean hasFooterHtml;
    private boolean hasUserReview;
    private Rev.Review userReview_ = null;

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

    public DocumentV1.DocV1 getDocV1()
    {
      return this.docV1_;
    }

    public DocumentV2.DocV2 getDocV2()
    {
      return this.docV2_;
    }

    public String getFooterHtml()
    {
      return this.footerHtml_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDocV1())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDocV1());
      if (hasAnalyticsCookie())
        i += CodedOutputStreamMicro.computeStringSize(2, getAnalyticsCookie());
      if (hasUserReview())
        i += CodedOutputStreamMicro.computeMessageSize(3, getUserReview());
      if (hasDocV2())
        i += CodedOutputStreamMicro.computeMessageSize(4, getDocV2());
      if (hasFooterHtml())
        i += CodedOutputStreamMicro.computeStringSize(5, getFooterHtml());
      this.cachedSize = i;
      return i;
    }

    public Rev.Review getUserReview()
    {
      return this.userReview_;
    }

    public boolean hasAnalyticsCookie()
    {
      return this.hasAnalyticsCookie;
    }

    public boolean hasDocV1()
    {
      return this.hasDocV1;
    }

    public boolean hasDocV2()
    {
      return this.hasDocV2;
    }

    public boolean hasFooterHtml()
    {
      return this.hasFooterHtml;
    }

    public boolean hasUserReview()
    {
      return this.hasUserReview;
    }

    public DetailsResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDocV1(localDocV1);
          break;
        case 18:
          setAnalyticsCookie(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          Rev.Review localReview = new Rev.Review();
          paramCodedInputStreamMicro.readMessage(localReview);
          setUserReview(localReview);
          break;
        case 34:
          DocumentV2.DocV2 localDocV2 = new DocumentV2.DocV2();
          paramCodedInputStreamMicro.readMessage(localDocV2);
          setDocV2(localDocV2);
          break;
        case 42:
        }
        setFooterHtml(paramCodedInputStreamMicro.readString());
      }
    }

    public DetailsResponse setAnalyticsCookie(String paramString)
    {
      this.hasAnalyticsCookie = true;
      this.analyticsCookie_ = paramString;
      return this;
    }

    public DetailsResponse setDocV1(DocumentV1.DocV1 paramDocV1)
    {
      if (paramDocV1 == null)
        throw new NullPointerException();
      this.hasDocV1 = true;
      this.docV1_ = paramDocV1;
      return this;
    }

    public DetailsResponse setDocV2(DocumentV2.DocV2 paramDocV2)
    {
      if (paramDocV2 == null)
        throw new NullPointerException();
      this.hasDocV2 = true;
      this.docV2_ = paramDocV2;
      return this;
    }

    public DetailsResponse setFooterHtml(String paramString)
    {
      this.hasFooterHtml = true;
      this.footerHtml_ = paramString;
      return this;
    }

    public DetailsResponse setUserReview(Rev.Review paramReview)
    {
      if (paramReview == null)
        throw new NullPointerException();
      this.hasUserReview = true;
      this.userReview_ = paramReview;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDocV1())
        paramCodedOutputStreamMicro.writeMessage(1, getDocV1());
      if (hasAnalyticsCookie())
        paramCodedOutputStreamMicro.writeString(2, getAnalyticsCookie());
      if (hasUserReview())
        paramCodedOutputStreamMicro.writeMessage(3, getUserReview());
      if (hasDocV2())
        paramCodedOutputStreamMicro.writeMessage(4, getDocV2());
      if (hasFooterHtml())
        paramCodedOutputStreamMicro.writeString(5, getFooterHtml());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Details
 * JD-Core Version:    0.6.2
 */
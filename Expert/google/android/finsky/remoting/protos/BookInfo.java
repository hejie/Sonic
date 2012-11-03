package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class BookInfo
{
  public static final class BookAuthor extends MessageMicro
  {
    private int cachedSize = -1;
    private String deprecatedQuery_ = "";
    private Common.Docid docid_ = null;
    private boolean hasDeprecatedQuery;
    private boolean hasDocid;
    private boolean hasName;
    private String name_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDeprecatedQuery()
    {
      return this.deprecatedQuery_;
    }

    public Common.Docid getDocid()
    {
      return this.docid_;
    }

    public String getName()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasDeprecatedQuery())
        i += CodedOutputStreamMicro.computeStringSize(2, getDeprecatedQuery());
      if (hasDocid())
        i += CodedOutputStreamMicro.computeMessageSize(3, getDocid());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDeprecatedQuery()
    {
      return this.hasDeprecatedQuery;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public BookAuthor mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDeprecatedQuery(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        Common.Docid localDocid = new Common.Docid();
        paramCodedInputStreamMicro.readMessage(localDocid);
        setDocid(localDocid);
      }
    }

    public BookAuthor setDeprecatedQuery(String paramString)
    {
      this.hasDeprecatedQuery = true;
      this.deprecatedQuery_ = paramString;
      return this;
    }

    public BookAuthor setDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasDocid = true;
      this.docid_ = paramDocid;
      return this;
    }

    public BookAuthor setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasDeprecatedQuery())
        paramCodedOutputStreamMicro.writeString(2, getDeprecatedQuery());
      if (hasDocid())
        paramCodedOutputStreamMicro.writeMessage(3, getDocid());
    }
  }

  public static final class BookDetails extends MessageMicro
  {
    private String aboutTheAuthor_ = "";
    private String acsEpubTokenUrl_ = "";
    private String acsPdfTokenUrl_ = "";
    private boolean audioVideoContent_ = false;
    private List<BookInfo.BookAuthor> author_ = Collections.emptyList();
    private int cachedSize = -1;
    private String downloadEpubUrl_ = "";
    private String downloadPdfUrl_ = "";
    private boolean epubAvailable_ = false;
    private boolean fixedLayoutContent_ = false;
    private boolean hasAboutTheAuthor;
    private boolean hasAcsEpubTokenUrl;
    private boolean hasAcsPdfTokenUrl;
    private boolean hasAudioVideoContent;
    private boolean hasDownloadEpubUrl;
    private boolean hasDownloadPdfUrl;
    private boolean hasEpubAvailable;
    private boolean hasFixedLayoutContent;
    private boolean hasIsbn;
    private boolean hasNumberOfPages;
    private boolean hasPdfAvailable;
    private boolean hasPublicationDate;
    private boolean hasPublisher;
    private boolean hasReaderUrl;
    private boolean hasSubtitle;
    private List<Identifier> identifier_ = Collections.emptyList();
    private String isbn_ = "";
    private int numberOfPages_ = 0;
    private boolean pdfAvailable_ = false;
    private String publicationDate_ = "";
    private String publisher_ = "";
    private String readerUrl_ = "";
    private List<BookInfo.BookSubject> subject_ = Collections.emptyList();
    private String subtitle_ = "";

    public BookDetails addAuthor(BookInfo.BookAuthor paramBookAuthor)
    {
      if (paramBookAuthor == null)
        throw new NullPointerException();
      if (this.author_.isEmpty())
        this.author_ = new ArrayList();
      this.author_.add(paramBookAuthor);
      return this;
    }

    public BookDetails addIdentifier(Identifier paramIdentifier)
    {
      if (paramIdentifier == null)
        throw new NullPointerException();
      if (this.identifier_.isEmpty())
        this.identifier_ = new ArrayList();
      this.identifier_.add(paramIdentifier);
      return this;
    }

    public BookDetails addSubject(BookInfo.BookSubject paramBookSubject)
    {
      if (paramBookSubject == null)
        throw new NullPointerException();
      if (this.subject_.isEmpty())
        this.subject_ = new ArrayList();
      this.subject_.add(paramBookSubject);
      return this;
    }

    public String getAboutTheAuthor()
    {
      return this.aboutTheAuthor_;
    }

    public String getAcsEpubTokenUrl()
    {
      return this.acsEpubTokenUrl_;
    }

    public String getAcsPdfTokenUrl()
    {
      return this.acsPdfTokenUrl_;
    }

    public boolean getAudioVideoContent()
    {
      return this.audioVideoContent_;
    }

    public List<BookInfo.BookAuthor> getAuthorList()
    {
      return this.author_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDownloadEpubUrl()
    {
      return this.downloadEpubUrl_;
    }

    public String getDownloadPdfUrl()
    {
      return this.downloadPdfUrl_;
    }

    public boolean getEpubAvailable()
    {
      return this.epubAvailable_;
    }

    public boolean getFixedLayoutContent()
    {
      return this.fixedLayoutContent_;
    }

    public List<Identifier> getIdentifierList()
    {
      return this.identifier_;
    }

    public String getIsbn()
    {
      return this.isbn_;
    }

    public int getNumberOfPages()
    {
      return this.numberOfPages_;
    }

    public boolean getPdfAvailable()
    {
      return this.pdfAvailable_;
    }

    public String getPublicationDate()
    {
      return this.publicationDate_;
    }

    public String getPublisher()
    {
      return this.publisher_;
    }

    public String getReaderUrl()
    {
      return this.readerUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getSubjectList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (BookInfo.BookSubject)localIterator1.next());
      if (hasPublisher())
        i += CodedOutputStreamMicro.computeStringSize(4, getPublisher());
      if (hasPublicationDate())
        i += CodedOutputStreamMicro.computeStringSize(5, getPublicationDate());
      if (hasIsbn())
        i += CodedOutputStreamMicro.computeStringSize(6, getIsbn());
      if (hasNumberOfPages())
        i += CodedOutputStreamMicro.computeInt32Size(7, getNumberOfPages());
      if (hasSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(8, getSubtitle());
      Iterator localIterator2 = getAuthorList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(9, (BookInfo.BookAuthor)localIterator2.next());
      if (hasReaderUrl())
        i += CodedOutputStreamMicro.computeStringSize(10, getReaderUrl());
      if (hasDownloadEpubUrl())
        i += CodedOutputStreamMicro.computeStringSize(11, getDownloadEpubUrl());
      if (hasDownloadPdfUrl())
        i += CodedOutputStreamMicro.computeStringSize(12, getDownloadPdfUrl());
      if (hasAcsEpubTokenUrl())
        i += CodedOutputStreamMicro.computeStringSize(13, getAcsEpubTokenUrl());
      if (hasAcsPdfTokenUrl())
        i += CodedOutputStreamMicro.computeStringSize(14, getAcsPdfTokenUrl());
      if (hasEpubAvailable())
        i += CodedOutputStreamMicro.computeBoolSize(15, getEpubAvailable());
      if (hasPdfAvailable())
        i += CodedOutputStreamMicro.computeBoolSize(16, getPdfAvailable());
      if (hasAboutTheAuthor())
        i += CodedOutputStreamMicro.computeStringSize(17, getAboutTheAuthor());
      Iterator localIterator3 = getIdentifierList().iterator();
      while (localIterator3.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(18, (Identifier)localIterator3.next());
      if (hasFixedLayoutContent())
        i += CodedOutputStreamMicro.computeBoolSize(21, getFixedLayoutContent());
      if (hasAudioVideoContent())
        i += CodedOutputStreamMicro.computeBoolSize(22, getAudioVideoContent());
      this.cachedSize = i;
      return i;
    }

    public List<BookInfo.BookSubject> getSubjectList()
    {
      return this.subject_;
    }

    public String getSubtitle()
    {
      return this.subtitle_;
    }

    public boolean hasAboutTheAuthor()
    {
      return this.hasAboutTheAuthor;
    }

    public boolean hasAcsEpubTokenUrl()
    {
      return this.hasAcsEpubTokenUrl;
    }

    public boolean hasAcsPdfTokenUrl()
    {
      return this.hasAcsPdfTokenUrl;
    }

    public boolean hasAudioVideoContent()
    {
      return this.hasAudioVideoContent;
    }

    public boolean hasDownloadEpubUrl()
    {
      return this.hasDownloadEpubUrl;
    }

    public boolean hasDownloadPdfUrl()
    {
      return this.hasDownloadPdfUrl;
    }

    public boolean hasEpubAvailable()
    {
      return this.hasEpubAvailable;
    }

    public boolean hasFixedLayoutContent()
    {
      return this.hasFixedLayoutContent;
    }

    public boolean hasIsbn()
    {
      return this.hasIsbn;
    }

    public boolean hasNumberOfPages()
    {
      return this.hasNumberOfPages;
    }

    public boolean hasPdfAvailable()
    {
      return this.hasPdfAvailable;
    }

    public boolean hasPublicationDate()
    {
      return this.hasPublicationDate;
    }

    public boolean hasPublisher()
    {
      return this.hasPublisher;
    }

    public boolean hasReaderUrl()
    {
      return this.hasReaderUrl;
    }

    public boolean hasSubtitle()
    {
      return this.hasSubtitle;
    }

    public BookDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          BookInfo.BookSubject localBookSubject = new BookInfo.BookSubject();
          paramCodedInputStreamMicro.readMessage(localBookSubject);
          addSubject(localBookSubject);
          break;
        case 34:
          setPublisher(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setPublicationDate(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setIsbn(paramCodedInputStreamMicro.readString());
          break;
        case 56:
          setNumberOfPages(paramCodedInputStreamMicro.readInt32());
          break;
        case 66:
          setSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          BookInfo.BookAuthor localBookAuthor = new BookInfo.BookAuthor();
          paramCodedInputStreamMicro.readMessage(localBookAuthor);
          addAuthor(localBookAuthor);
          break;
        case 82:
          setReaderUrl(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setDownloadEpubUrl(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setDownloadPdfUrl(paramCodedInputStreamMicro.readString());
          break;
        case 106:
          setAcsEpubTokenUrl(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          setAcsPdfTokenUrl(paramCodedInputStreamMicro.readString());
          break;
        case 120:
          setEpubAvailable(paramCodedInputStreamMicro.readBool());
          break;
        case 128:
          setPdfAvailable(paramCodedInputStreamMicro.readBool());
          break;
        case 138:
          setAboutTheAuthor(paramCodedInputStreamMicro.readString());
          break;
        case 147:
          Identifier localIdentifier = new Identifier();
          paramCodedInputStreamMicro.readGroup(localIdentifier, 18);
          addIdentifier(localIdentifier);
          break;
        case 168:
          setFixedLayoutContent(paramCodedInputStreamMicro.readBool());
          break;
        case 176:
        }
        setAudioVideoContent(paramCodedInputStreamMicro.readBool());
      }
    }

    public BookDetails setAboutTheAuthor(String paramString)
    {
      this.hasAboutTheAuthor = true;
      this.aboutTheAuthor_ = paramString;
      return this;
    }

    public BookDetails setAcsEpubTokenUrl(String paramString)
    {
      this.hasAcsEpubTokenUrl = true;
      this.acsEpubTokenUrl_ = paramString;
      return this;
    }

    public BookDetails setAcsPdfTokenUrl(String paramString)
    {
      this.hasAcsPdfTokenUrl = true;
      this.acsPdfTokenUrl_ = paramString;
      return this;
    }

    public BookDetails setAudioVideoContent(boolean paramBoolean)
    {
      this.hasAudioVideoContent = true;
      this.audioVideoContent_ = paramBoolean;
      return this;
    }

    public BookDetails setDownloadEpubUrl(String paramString)
    {
      this.hasDownloadEpubUrl = true;
      this.downloadEpubUrl_ = paramString;
      return this;
    }

    public BookDetails setDownloadPdfUrl(String paramString)
    {
      this.hasDownloadPdfUrl = true;
      this.downloadPdfUrl_ = paramString;
      return this;
    }

    public BookDetails setEpubAvailable(boolean paramBoolean)
    {
      this.hasEpubAvailable = true;
      this.epubAvailable_ = paramBoolean;
      return this;
    }

    public BookDetails setFixedLayoutContent(boolean paramBoolean)
    {
      this.hasFixedLayoutContent = true;
      this.fixedLayoutContent_ = paramBoolean;
      return this;
    }

    public BookDetails setIsbn(String paramString)
    {
      this.hasIsbn = true;
      this.isbn_ = paramString;
      return this;
    }

    public BookDetails setNumberOfPages(int paramInt)
    {
      this.hasNumberOfPages = true;
      this.numberOfPages_ = paramInt;
      return this;
    }

    public BookDetails setPdfAvailable(boolean paramBoolean)
    {
      this.hasPdfAvailable = true;
      this.pdfAvailable_ = paramBoolean;
      return this;
    }

    public BookDetails setPublicationDate(String paramString)
    {
      this.hasPublicationDate = true;
      this.publicationDate_ = paramString;
      return this;
    }

    public BookDetails setPublisher(String paramString)
    {
      this.hasPublisher = true;
      this.publisher_ = paramString;
      return this;
    }

    public BookDetails setReaderUrl(String paramString)
    {
      this.hasReaderUrl = true;
      this.readerUrl_ = paramString;
      return this;
    }

    public BookDetails setSubtitle(String paramString)
    {
      this.hasSubtitle = true;
      this.subtitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getSubjectList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (BookInfo.BookSubject)localIterator1.next());
      if (hasPublisher())
        paramCodedOutputStreamMicro.writeString(4, getPublisher());
      if (hasPublicationDate())
        paramCodedOutputStreamMicro.writeString(5, getPublicationDate());
      if (hasIsbn())
        paramCodedOutputStreamMicro.writeString(6, getIsbn());
      if (hasNumberOfPages())
        paramCodedOutputStreamMicro.writeInt32(7, getNumberOfPages());
      if (hasSubtitle())
        paramCodedOutputStreamMicro.writeString(8, getSubtitle());
      Iterator localIterator2 = getAuthorList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(9, (BookInfo.BookAuthor)localIterator2.next());
      if (hasReaderUrl())
        paramCodedOutputStreamMicro.writeString(10, getReaderUrl());
      if (hasDownloadEpubUrl())
        paramCodedOutputStreamMicro.writeString(11, getDownloadEpubUrl());
      if (hasDownloadPdfUrl())
        paramCodedOutputStreamMicro.writeString(12, getDownloadPdfUrl());
      if (hasAcsEpubTokenUrl())
        paramCodedOutputStreamMicro.writeString(13, getAcsEpubTokenUrl());
      if (hasAcsPdfTokenUrl())
        paramCodedOutputStreamMicro.writeString(14, getAcsPdfTokenUrl());
      if (hasEpubAvailable())
        paramCodedOutputStreamMicro.writeBool(15, getEpubAvailable());
      if (hasPdfAvailable())
        paramCodedOutputStreamMicro.writeBool(16, getPdfAvailable());
      if (hasAboutTheAuthor())
        paramCodedOutputStreamMicro.writeString(17, getAboutTheAuthor());
      Iterator localIterator3 = getIdentifierList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeGroup(18, (Identifier)localIterator3.next());
      if (hasFixedLayoutContent())
        paramCodedOutputStreamMicro.writeBool(21, getFixedLayoutContent());
      if (hasAudioVideoContent())
        paramCodedOutputStreamMicro.writeBool(22, getAudioVideoContent());
    }

    public static final class Identifier extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasIdentifier;
      private boolean hasType;
      private String identifier_ = "";
      private int type_ = 0;

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getIdentifier()
      {
        return this.identifier_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasType())
          i = 0 + CodedOutputStreamMicro.computeInt32Size(19, getType());
        if (hasIdentifier())
          i += CodedOutputStreamMicro.computeStringSize(20, getIdentifier());
        this.cachedSize = i;
        return i;
      }

      public int getType()
      {
        return this.type_;
      }

      public boolean hasIdentifier()
      {
        return this.hasIdentifier;
      }

      public boolean hasType()
      {
        return this.hasType;
      }

      public Identifier mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 152:
            setType(paramCodedInputStreamMicro.readInt32());
            break;
          case 162:
          }
          setIdentifier(paramCodedInputStreamMicro.readString());
        }
      }

      public Identifier setIdentifier(String paramString)
      {
        this.hasIdentifier = true;
        this.identifier_ = paramString;
        return this;
      }

      public Identifier setType(int paramInt)
      {
        this.hasType = true;
        this.type_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasType())
          paramCodedOutputStreamMicro.writeInt32(19, getType());
        if (hasIdentifier())
          paramCodedOutputStreamMicro.writeString(20, getIdentifier());
      }
    }
  }

  public static final class BookSubject extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasName;
    private boolean hasQuery;
    private boolean hasSubjectId;
    private String name_ = "";
    private String query_ = "";
    private String subjectId_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getName()
    {
      return this.name_;
    }

    public String getQuery()
    {
      return this.query_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasQuery())
        i += CodedOutputStreamMicro.computeStringSize(2, getQuery());
      if (hasSubjectId())
        i += CodedOutputStreamMicro.computeStringSize(3, getSubjectId());
      this.cachedSize = i;
      return i;
    }

    public String getSubjectId()
    {
      return this.subjectId_;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasQuery()
    {
      return this.hasQuery;
    }

    public boolean hasSubjectId()
    {
      return this.hasSubjectId;
    }

    public BookSubject mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setQuery(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setSubjectId(paramCodedInputStreamMicro.readString());
      }
    }

    public BookSubject setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public BookSubject setQuery(String paramString)
    {
      this.hasQuery = true;
      this.query_ = paramString;
      return this;
    }

    public BookSubject setSubjectId(String paramString)
    {
      this.hasSubjectId = true;
      this.subjectId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasQuery())
        paramCodedOutputStreamMicro.writeString(2, getQuery());
      if (hasSubjectId())
        paramCodedOutputStreamMicro.writeString(3, getSubjectId());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.BookInfo
 * JD-Core Version:    0.6.2
 */
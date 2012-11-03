package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Browse
{
  public static final class BrowseLink extends MessageMicro
  {
    private int cachedSize = -1;
    private String dataUrl_ = "";
    private boolean hasDataUrl;
    private boolean hasName;
    private String name_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDataUrl()
    {
      return this.dataUrl_;
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
      if (hasDataUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getDataUrl());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDataUrl()
    {
      return this.hasDataUrl;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public BrowseLink mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 26:
        }
        setDataUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public BrowseLink setDataUrl(String paramString)
    {
      this.hasDataUrl = true;
      this.dataUrl_ = paramString;
      return this;
    }

    public BrowseLink setName(String paramString)
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
      if (hasDataUrl())
        paramCodedOutputStreamMicro.writeString(3, getDataUrl());
    }
  }

  public static final class BrowseResponse extends MessageMicro
  {
    private List<Browse.BrowseLink> breadcrumb_ = Collections.emptyList();
    private int cachedSize = -1;
    private List<Browse.BrowseLink> category_ = Collections.emptyList();
    private String contentsUrl_ = "";
    private boolean hasContentsUrl;
    private boolean hasPromoUrl;
    private String promoUrl_ = "";

    public BrowseResponse addBreadcrumb(Browse.BrowseLink paramBrowseLink)
    {
      if (paramBrowseLink == null)
        throw new NullPointerException();
      if (this.breadcrumb_.isEmpty())
        this.breadcrumb_ = new ArrayList();
      this.breadcrumb_.add(paramBrowseLink);
      return this;
    }

    public BrowseResponse addCategory(Browse.BrowseLink paramBrowseLink)
    {
      if (paramBrowseLink == null)
        throw new NullPointerException();
      if (this.category_.isEmpty())
        this.category_ = new ArrayList();
      this.category_.add(paramBrowseLink);
      return this;
    }

    public List<Browse.BrowseLink> getBreadcrumbList()
    {
      return this.breadcrumb_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<Browse.BrowseLink> getCategoryList()
    {
      return this.category_;
    }

    public String getContentsUrl()
    {
      return this.contentsUrl_;
    }

    public String getPromoUrl()
    {
      return this.promoUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasContentsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getContentsUrl());
      if (hasPromoUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getPromoUrl());
      Iterator localIterator1 = getCategoryList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (Browse.BrowseLink)localIterator1.next());
      Iterator localIterator2 = getBreadcrumbList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (Browse.BrowseLink)localIterator2.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasContentsUrl()
    {
      return this.hasContentsUrl;
    }

    public boolean hasPromoUrl()
    {
      return this.hasPromoUrl;
    }

    public BrowseResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setContentsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setPromoUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          Browse.BrowseLink localBrowseLink2 = new Browse.BrowseLink();
          paramCodedInputStreamMicro.readMessage(localBrowseLink2);
          addCategory(localBrowseLink2);
          break;
        case 34:
        }
        Browse.BrowseLink localBrowseLink1 = new Browse.BrowseLink();
        paramCodedInputStreamMicro.readMessage(localBrowseLink1);
        addBreadcrumb(localBrowseLink1);
      }
    }

    public BrowseResponse setContentsUrl(String paramString)
    {
      this.hasContentsUrl = true;
      this.contentsUrl_ = paramString;
      return this;
    }

    public BrowseResponse setPromoUrl(String paramString)
    {
      this.hasPromoUrl = true;
      this.promoUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasContentsUrl())
        paramCodedOutputStreamMicro.writeString(1, getContentsUrl());
      if (hasPromoUrl())
        paramCodedOutputStreamMicro.writeString(2, getPromoUrl());
      Iterator localIterator1 = getCategoryList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (Browse.BrowseLink)localIterator1.next());
      Iterator localIterator2 = getBreadcrumbList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (Browse.BrowseLink)localIterator2.next());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Browse
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class Containers
{
  public static final class ContainerMetadata extends MessageMicro
  {
    private String analyticsCookie_ = "";
    private String browseUrl_ = "";
    private int cachedSize = -1;
    private long estimatedResults_ = 0L;
    private boolean hasAnalyticsCookie;
    private boolean hasBrowseUrl;
    private boolean hasEstimatedResults;
    private boolean hasNextPageUrl;
    private boolean hasOrdered;
    private boolean hasRelevance;
    private String nextPageUrl_ = "";
    private boolean ordered_ = false;
    private double relevance_ = 0.0D;

    public String getAnalyticsCookie()
    {
      return this.analyticsCookie_;
    }

    public String getBrowseUrl()
    {
      return this.browseUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getEstimatedResults()
    {
      return this.estimatedResults_;
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
      if (hasBrowseUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getBrowseUrl());
      if (hasNextPageUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getNextPageUrl());
      if (hasRelevance())
        i += CodedOutputStreamMicro.computeDoubleSize(3, getRelevance());
      if (hasEstimatedResults())
        i += CodedOutputStreamMicro.computeInt64Size(4, getEstimatedResults());
      if (hasAnalyticsCookie())
        i += CodedOutputStreamMicro.computeStringSize(5, getAnalyticsCookie());
      if (hasOrdered())
        i += CodedOutputStreamMicro.computeBoolSize(6, getOrdered());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAnalyticsCookie()
    {
      return this.hasAnalyticsCookie;
    }

    public boolean hasBrowseUrl()
    {
      return this.hasBrowseUrl;
    }

    public boolean hasEstimatedResults()
    {
      return this.hasEstimatedResults;
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

    public ContainerMetadata mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBrowseUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setNextPageUrl(paramCodedInputStreamMicro.readString());
          break;
        case 25:
          setRelevance(paramCodedInputStreamMicro.readDouble());
          break;
        case 32:
          setEstimatedResults(paramCodedInputStreamMicro.readInt64());
          break;
        case 42:
          setAnalyticsCookie(paramCodedInputStreamMicro.readString());
          break;
        case 48:
        }
        setOrdered(paramCodedInputStreamMicro.readBool());
      }
    }

    public ContainerMetadata setAnalyticsCookie(String paramString)
    {
      this.hasAnalyticsCookie = true;
      this.analyticsCookie_ = paramString;
      return this;
    }

    public ContainerMetadata setBrowseUrl(String paramString)
    {
      this.hasBrowseUrl = true;
      this.browseUrl_ = paramString;
      return this;
    }

    public ContainerMetadata setEstimatedResults(long paramLong)
    {
      this.hasEstimatedResults = true;
      this.estimatedResults_ = paramLong;
      return this;
    }

    public ContainerMetadata setNextPageUrl(String paramString)
    {
      this.hasNextPageUrl = true;
      this.nextPageUrl_ = paramString;
      return this;
    }

    public ContainerMetadata setOrdered(boolean paramBoolean)
    {
      this.hasOrdered = true;
      this.ordered_ = paramBoolean;
      return this;
    }

    public ContainerMetadata setRelevance(double paramDouble)
    {
      this.hasRelevance = true;
      this.relevance_ = paramDouble;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBrowseUrl())
        paramCodedOutputStreamMicro.writeString(1, getBrowseUrl());
      if (hasNextPageUrl())
        paramCodedOutputStreamMicro.writeString(2, getNextPageUrl());
      if (hasRelevance())
        paramCodedOutputStreamMicro.writeDouble(3, getRelevance());
      if (hasEstimatedResults())
        paramCodedOutputStreamMicro.writeInt64(4, getEstimatedResults());
      if (hasAnalyticsCookie())
        paramCodedOutputStreamMicro.writeString(5, getAnalyticsCookie());
      if (hasOrdered())
        paramCodedOutputStreamMicro.writeBool(6, getOrdered());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Containers
 * JD-Core Version:    0.6.2
 */
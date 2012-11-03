package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Log
{
  public static final class ClickLogEvent extends MessageMicro
  {
    private int cachedSize = -1;
    private long eventTime_ = 0L;
    private boolean hasEventTime;
    private boolean hasListId;
    private boolean hasReferrerListId;
    private boolean hasReferrerUrl;
    private boolean hasUrl;
    private String listId_ = "";
    private String referrerListId_ = "";
    private String referrerUrl_ = "";
    private String url_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getEventTime()
    {
      return this.eventTime_;
    }

    public String getListId()
    {
      return this.listId_;
    }

    public String getReferrerListId()
    {
      return this.referrerListId_;
    }

    public String getReferrerUrl()
    {
      return this.referrerUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasEventTime())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getEventTime());
      if (hasUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getUrl());
      if (hasListId())
        i += CodedOutputStreamMicro.computeStringSize(3, getListId());
      if (hasReferrerUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getReferrerUrl());
      if (hasReferrerListId())
        i += CodedOutputStreamMicro.computeStringSize(5, getReferrerListId());
      this.cachedSize = i;
      return i;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public boolean hasEventTime()
    {
      return this.hasEventTime;
    }

    public boolean hasListId()
    {
      return this.hasListId;
    }

    public boolean hasReferrerListId()
    {
      return this.hasReferrerListId;
    }

    public boolean hasReferrerUrl()
    {
      return this.hasReferrerUrl;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public ClickLogEvent mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setEventTime(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
          setUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setListId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setReferrerUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        setReferrerListId(paramCodedInputStreamMicro.readString());
      }
    }

    public ClickLogEvent setEventTime(long paramLong)
    {
      this.hasEventTime = true;
      this.eventTime_ = paramLong;
      return this;
    }

    public ClickLogEvent setListId(String paramString)
    {
      this.hasListId = true;
      this.listId_ = paramString;
      return this;
    }

    public ClickLogEvent setReferrerListId(String paramString)
    {
      this.hasReferrerListId = true;
      this.referrerListId_ = paramString;
      return this;
    }

    public ClickLogEvent setReferrerUrl(String paramString)
    {
      this.hasReferrerUrl = true;
      this.referrerUrl_ = paramString;
      return this;
    }

    public ClickLogEvent setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasEventTime())
        paramCodedOutputStreamMicro.writeInt64(1, getEventTime());
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(2, getUrl());
      if (hasListId())
        paramCodedOutputStreamMicro.writeString(3, getListId());
      if (hasReferrerUrl())
        paramCodedOutputStreamMicro.writeString(4, getReferrerUrl());
      if (hasReferrerListId())
        paramCodedOutputStreamMicro.writeString(5, getReferrerListId());
    }
  }

  public static final class LogRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private List<Log.ClickLogEvent> clickEvent_ = Collections.emptyList();

    public LogRequest addClickEvent(Log.ClickLogEvent paramClickLogEvent)
    {
      if (paramClickLogEvent == null)
        throw new NullPointerException();
      if (this.clickEvent_.isEmpty())
        this.clickEvent_ = new ArrayList();
      this.clickEvent_.add(paramClickLogEvent);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<Log.ClickLogEvent> getClickEventList()
    {
      return this.clickEvent_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getClickEventList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (Log.ClickLogEvent)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public LogRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        Log.ClickLogEvent localClickLogEvent = new Log.ClickLogEvent();
        paramCodedInputStreamMicro.readMessage(localClickLogEvent);
        addClickEvent(localClickLogEvent);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getClickEventList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (Log.ClickLogEvent)localIterator.next());
    }
  }

  public static final class LogResponse extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public LogResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Log
 * JD-Core Version:    0.6.2
 */
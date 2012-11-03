package com.google.android.play.analytics;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ClientsAnalytics
{
  public static final class LogEvent extends MessageMicro
  {
    private int cachedSize = -1;
    private long eventTime_ = 0L;
    private boolean hasEventTime;
    private boolean hasTag;
    private String tag_ = "";
    private List<ClientsAnalytics.LogEventKeyValues> values_ = Collections.emptyList();

    public LogEvent addValues(ClientsAnalytics.LogEventKeyValues paramLogEventKeyValues)
    {
      if (paramLogEventKeyValues == null)
        throw new NullPointerException();
      if (this.values_.isEmpty())
        this.values_ = new ArrayList();
      this.values_.add(paramLogEventKeyValues);
      return this;
    }

    public final LogEvent clear()
    {
      clearEventTime();
      clearTag();
      clearValues();
      this.cachedSize = -1;
      return this;
    }

    public LogEvent clearEventTime()
    {
      this.hasEventTime = false;
      this.eventTime_ = 0L;
      return this;
    }

    public LogEvent clearTag()
    {
      this.hasTag = false;
      this.tag_ = "";
      return this;
    }

    public LogEvent clearValues()
    {
      this.values_ = Collections.emptyList();
      return this;
    }

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

    public int getSerializedSize()
    {
      int i = 0;
      if (hasEventTime())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getEventTime());
      if (hasTag())
        i += CodedOutputStreamMicro.computeStringSize(2, getTag());
      Iterator localIterator = getValuesList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (ClientsAnalytics.LogEventKeyValues)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public String getTag()
    {
      return this.tag_;
    }

    public List<ClientsAnalytics.LogEventKeyValues> getValuesList()
    {
      return this.values_;
    }

    public boolean hasEventTime()
    {
      return this.hasEventTime;
    }

    public boolean hasTag()
    {
      return this.hasTag;
    }

    public LogEvent mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setTag(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        ClientsAnalytics.LogEventKeyValues localLogEventKeyValues = new ClientsAnalytics.LogEventKeyValues();
        paramCodedInputStreamMicro.readMessage(localLogEventKeyValues);
        addValues(localLogEventKeyValues);
      }
    }

    public LogEvent setEventTime(long paramLong)
    {
      this.hasEventTime = true;
      this.eventTime_ = paramLong;
      return this;
    }

    public LogEvent setTag(String paramString)
    {
      this.hasTag = true;
      this.tag_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasEventTime())
        paramCodedOutputStreamMicro.writeInt64(1, getEventTime());
      if (hasTag())
        paramCodedOutputStreamMicro.writeString(2, getTag());
      Iterator localIterator = getValuesList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (ClientsAnalytics.LogEventKeyValues)localIterator.next());
    }
  }

  public static final class LogEventKeyValues extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasKey;
    private boolean hasValue;
    private String key_ = "";
    private String value_ = "";

    public final LogEventKeyValues clear()
    {
      clearKey();
      clearValue();
      this.cachedSize = -1;
      return this;
    }

    public LogEventKeyValues clearKey()
    {
      this.hasKey = false;
      this.key_ = "";
      return this;
    }

    public LogEventKeyValues clearValue()
    {
      this.hasValue = false;
      this.value_ = "";
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getKey()
    {
      return this.key_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getKey());
      if (hasValue())
        i += CodedOutputStreamMicro.computeStringSize(2, getValue());
      this.cachedSize = i;
      return i;
    }

    public String getValue()
    {
      return this.value_;
    }

    public boolean hasKey()
    {
      return this.hasKey;
    }

    public boolean hasValue()
    {
      return this.hasValue;
    }

    public LogEventKeyValues mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setKey(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setValue(paramCodedInputStreamMicro.readString());
      }
    }

    public LogEventKeyValues setKey(String paramString)
    {
      this.hasKey = true;
      this.key_ = paramString;
      return this;
    }

    public LogEventKeyValues setValue(String paramString)
    {
      this.hasValue = true;
      this.value_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasKey())
        paramCodedOutputStreamMicro.writeString(1, getKey());
      if (hasValue())
        paramCodedOutputStreamMicro.writeString(2, getValue());
    }
  }

  public static final class LogRequest extends MessageMicro
  {
    private String androidId_ = "";
    private String buildId_ = "";
    private int cachedSize = -1;
    private List<ClientsAnalytics.LogEvent> clickEvent_ = Collections.emptyList();
    private boolean hasAndroidId;
    private boolean hasBuildId;
    private boolean hasLogSource;
    private boolean hasLoggingId;
    private boolean hasModel;
    private boolean hasProduct;
    private boolean hasSdkVersion;
    private int logSource_ = 0;
    private String loggingId_ = "";
    private String model_ = "";
    private String product_ = "";
    private int sdkVersion_ = 0;

    public LogRequest addClickEvent(ClientsAnalytics.LogEvent paramLogEvent)
    {
      if (paramLogEvent == null)
        throw new NullPointerException();
      if (this.clickEvent_.isEmpty())
        this.clickEvent_ = new ArrayList();
      this.clickEvent_.add(paramLogEvent);
      return this;
    }

    public String getAndroidId()
    {
      return this.androidId_;
    }

    public String getBuildId()
    {
      return this.buildId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<ClientsAnalytics.LogEvent> getClickEventList()
    {
      return this.clickEvent_;
    }

    public int getLogSource()
    {
      return this.logSource_;
    }

    public String getLoggingId()
    {
      return this.loggingId_;
    }

    public String getModel()
    {
      return this.model_;
    }

    public String getProduct()
    {
      return this.product_;
    }

    public int getSdkVersion()
    {
      return this.sdkVersion_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSdkVersion())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getSdkVersion());
      if (hasModel())
        i += CodedOutputStreamMicro.computeStringSize(2, getModel());
      if (hasProduct())
        i += CodedOutputStreamMicro.computeStringSize(3, getProduct());
      if (hasBuildId())
        i += CodedOutputStreamMicro.computeStringSize(4, getBuildId());
      if (hasLoggingId())
        i += CodedOutputStreamMicro.computeStringSize(5, getLoggingId());
      if (hasAndroidId())
        i += CodedOutputStreamMicro.computeStringSize(6, getAndroidId());
      if (hasLogSource())
        i += CodedOutputStreamMicro.computeInt32Size(7, getLogSource());
      Iterator localIterator = getClickEventList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(8, (ClientsAnalytics.LogEvent)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAndroidId()
    {
      return this.hasAndroidId;
    }

    public boolean hasBuildId()
    {
      return this.hasBuildId;
    }

    public boolean hasLogSource()
    {
      return this.hasLogSource;
    }

    public boolean hasLoggingId()
    {
      return this.hasLoggingId;
    }

    public boolean hasModel()
    {
      return this.hasModel;
    }

    public boolean hasProduct()
    {
      return this.hasProduct;
    }

    public boolean hasSdkVersion()
    {
      return this.hasSdkVersion;
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
        case 8:
          setSdkVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setModel(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setProduct(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setBuildId(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setLoggingId(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setAndroidId(paramCodedInputStreamMicro.readString());
          break;
        case 56:
          setLogSource(paramCodedInputStreamMicro.readInt32());
          break;
        case 66:
        }
        ClientsAnalytics.LogEvent localLogEvent = new ClientsAnalytics.LogEvent();
        paramCodedInputStreamMicro.readMessage(localLogEvent);
        addClickEvent(localLogEvent);
      }
    }

    public LogRequest setAndroidId(String paramString)
    {
      this.hasAndroidId = true;
      this.androidId_ = paramString;
      return this;
    }

    public LogRequest setBuildId(String paramString)
    {
      this.hasBuildId = true;
      this.buildId_ = paramString;
      return this;
    }

    public LogRequest setLogSource(int paramInt)
    {
      this.hasLogSource = true;
      this.logSource_ = paramInt;
      return this;
    }

    public LogRequest setLoggingId(String paramString)
    {
      this.hasLoggingId = true;
      this.loggingId_ = paramString;
      return this;
    }

    public LogRequest setModel(String paramString)
    {
      this.hasModel = true;
      this.model_ = paramString;
      return this;
    }

    public LogRequest setProduct(String paramString)
    {
      this.hasProduct = true;
      this.product_ = paramString;
      return this;
    }

    public LogRequest setSdkVersion(int paramInt)
    {
      this.hasSdkVersion = true;
      this.sdkVersion_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSdkVersion())
        paramCodedOutputStreamMicro.writeInt32(1, getSdkVersion());
      if (hasModel())
        paramCodedOutputStreamMicro.writeString(2, getModel());
      if (hasProduct())
        paramCodedOutputStreamMicro.writeString(3, getProduct());
      if (hasBuildId())
        paramCodedOutputStreamMicro.writeString(4, getBuildId());
      if (hasLoggingId())
        paramCodedOutputStreamMicro.writeString(5, getLoggingId());
      if (hasAndroidId())
        paramCodedOutputStreamMicro.writeString(6, getAndroidId());
      if (hasLogSource())
        paramCodedOutputStreamMicro.writeInt32(7, getLogSource());
      Iterator localIterator = getClickEventList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (ClientsAnalytics.LogEvent)localIterator.next());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.analytics.ClientsAnalytics
 * JD-Core Version:    0.6.2
 */
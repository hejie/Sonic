package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DebugInfoProto
{
  public static final class DebugInfo extends MessageMicro
  {
    private int cachedSize = -1;
    private List<String> message_ = Collections.emptyList();
    private List<Timing> timing_ = Collections.emptyList();

    public DebugInfo addMessage(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.message_.isEmpty())
        this.message_ = new ArrayList();
      this.message_.add(paramString);
      return this;
    }

    public DebugInfo addTiming(Timing paramTiming)
    {
      if (paramTiming == null)
        throw new NullPointerException();
      if (this.timing_.isEmpty())
        this.timing_ = new ArrayList();
      this.timing_.add(paramTiming);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getMessageList()
    {
      return this.message_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getMessageList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int j = 0 + i + 1 * getMessageList().size();
      Iterator localIterator2 = getTimingList().iterator();
      while (localIterator2.hasNext())
        j += CodedOutputStreamMicro.computeGroupSize(2, (Timing)localIterator2.next());
      this.cachedSize = j;
      return j;
    }

    public List<Timing> getTimingList()
    {
      return this.timing_;
    }

    public DebugInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          addMessage(paramCodedInputStreamMicro.readString());
          break;
        case 19:
        }
        Timing localTiming = new Timing();
        paramCodedInputStreamMicro.readGroup(localTiming, 2);
        addTiming(localTiming);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getMessageList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(1, (String)localIterator1.next());
      Iterator localIterator2 = getTimingList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeGroup(2, (Timing)localIterator2.next());
    }

    public static final class Timing extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasName;
      private boolean hasTimeInMs;
      private String name_ = "";
      private double timeInMs_ = 0.0D;

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

      public int getSerializedSize()
      {
        int i = 0;
        if (hasName())
          i = 0 + CodedOutputStreamMicro.computeStringSize(3, getName());
        if (hasTimeInMs())
          i += CodedOutputStreamMicro.computeDoubleSize(4, getTimeInMs());
        this.cachedSize = i;
        return i;
      }

      public double getTimeInMs()
      {
        return this.timeInMs_;
      }

      public boolean hasName()
      {
        return this.hasName;
      }

      public boolean hasTimeInMs()
      {
        return this.hasTimeInMs;
      }

      public Timing mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setName(paramCodedInputStreamMicro.readString());
            break;
          case 33:
          }
          setTimeInMs(paramCodedInputStreamMicro.readDouble());
        }
      }

      public Timing setName(String paramString)
      {
        this.hasName = true;
        this.name_ = paramString;
        return this;
      }

      public Timing setTimeInMs(double paramDouble)
      {
        this.hasTimeInMs = true;
        this.timeInMs_ = paramDouble;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasName())
          paramCodedOutputStreamMicro.writeString(3, getName());
        if (hasTimeInMs())
          paramCodedOutputStreamMicro.writeDouble(4, getTimeInMs());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DebugInfoProto
 * JD-Core Version:    0.6.2
 */
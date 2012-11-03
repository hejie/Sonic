package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LibraryReplication
{
  public static final class ClientLibraryState extends MessageMicro
  {
    private int cachedSize = -1;
    private int corpus_ = 0;
    private boolean hasCorpus;
    private boolean hasHashCodeSum;
    private boolean hasLibraryId;
    private boolean hasLibrarySize;
    private boolean hasServerToken;
    private long hashCodeSum_ = 0L;
    private String libraryId_ = "";
    private int librarySize_ = 0;
    private ByteStringMicro serverToken_ = ByteStringMicro.EMPTY;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCorpus()
    {
      return this.corpus_;
    }

    public long getHashCodeSum()
    {
      return this.hashCodeSum_;
    }

    public String getLibraryId()
    {
      return this.libraryId_;
    }

    public int getLibrarySize()
    {
      return this.librarySize_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCorpus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getCorpus());
      if (hasServerToken())
        i += CodedOutputStreamMicro.computeBytesSize(2, getServerToken());
      if (hasHashCodeSum())
        i += CodedOutputStreamMicro.computeInt64Size(3, getHashCodeSum());
      if (hasLibrarySize())
        i += CodedOutputStreamMicro.computeInt32Size(4, getLibrarySize());
      if (hasLibraryId())
        i += CodedOutputStreamMicro.computeStringSize(5, getLibraryId());
      this.cachedSize = i;
      return i;
    }

    public ByteStringMicro getServerToken()
    {
      return this.serverToken_;
    }

    public boolean hasCorpus()
    {
      return this.hasCorpus;
    }

    public boolean hasHashCodeSum()
    {
      return this.hasHashCodeSum;
    }

    public boolean hasLibraryId()
    {
      return this.hasLibraryId;
    }

    public boolean hasLibrarySize()
    {
      return this.hasLibrarySize;
    }

    public boolean hasServerToken()
    {
      return this.hasServerToken;
    }

    public ClientLibraryState mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCorpus(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setServerToken(paramCodedInputStreamMicro.readBytes());
          break;
        case 24:
          setHashCodeSum(paramCodedInputStreamMicro.readInt64());
          break;
        case 32:
          setLibrarySize(paramCodedInputStreamMicro.readInt32());
          break;
        case 42:
        }
        setLibraryId(paramCodedInputStreamMicro.readString());
      }
    }

    public ClientLibraryState setCorpus(int paramInt)
    {
      this.hasCorpus = true;
      this.corpus_ = paramInt;
      return this;
    }

    public ClientLibraryState setHashCodeSum(long paramLong)
    {
      this.hasHashCodeSum = true;
      this.hashCodeSum_ = paramLong;
      return this;
    }

    public ClientLibraryState setLibraryId(String paramString)
    {
      this.hasLibraryId = true;
      this.libraryId_ = paramString;
      return this;
    }

    public ClientLibraryState setLibrarySize(int paramInt)
    {
      this.hasLibrarySize = true;
      this.librarySize_ = paramInt;
      return this;
    }

    public ClientLibraryState setServerToken(ByteStringMicro paramByteStringMicro)
    {
      this.hasServerToken = true;
      this.serverToken_ = paramByteStringMicro;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCorpus())
        paramCodedOutputStreamMicro.writeInt32(1, getCorpus());
      if (hasServerToken())
        paramCodedOutputStreamMicro.writeBytes(2, getServerToken());
      if (hasHashCodeSum())
        paramCodedOutputStreamMicro.writeInt64(3, getHashCodeSum());
      if (hasLibrarySize())
        paramCodedOutputStreamMicro.writeInt32(4, getLibrarySize());
      if (hasLibraryId())
        paramCodedOutputStreamMicro.writeString(5, getLibraryId());
    }
  }

  public static final class LibraryReplicationRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private List<LibraryReplication.ClientLibraryState> libraryState_ = Collections.emptyList();

    public LibraryReplicationRequest addLibraryState(LibraryReplication.ClientLibraryState paramClientLibraryState)
    {
      if (paramClientLibraryState == null)
        throw new NullPointerException();
      if (this.libraryState_.isEmpty())
        this.libraryState_ = new ArrayList();
      this.libraryState_.add(paramClientLibraryState);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<LibraryReplication.ClientLibraryState> getLibraryStateList()
    {
      return this.libraryState_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getLibraryStateList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (LibraryReplication.ClientLibraryState)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public LibraryReplicationRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        LibraryReplication.ClientLibraryState localClientLibraryState = new LibraryReplication.ClientLibraryState();
        paramCodedInputStreamMicro.readMessage(localClientLibraryState);
        addLibraryState(localClientLibraryState);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getLibraryStateList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (LibraryReplication.ClientLibraryState)localIterator.next());
    }
  }

  public static final class LibraryReplicationResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private List<Library.LibraryUpdate> update_ = Collections.emptyList();

    public LibraryReplicationResponse addUpdate(Library.LibraryUpdate paramLibraryUpdate)
    {
      if (paramLibraryUpdate == null)
        throw new NullPointerException();
      if (this.update_.isEmpty())
        this.update_ = new ArrayList();
      this.update_.add(paramLibraryUpdate);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getUpdateList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (Library.LibraryUpdate)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public List<Library.LibraryUpdate> getUpdateList()
    {
      return this.update_;
    }

    public LibraryReplicationResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        Library.LibraryUpdate localLibraryUpdate = new Library.LibraryUpdate();
        paramCodedInputStreamMicro.readMessage(localLibraryUpdate);
        addUpdate(localLibraryUpdate);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getUpdateList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (Library.LibraryUpdate)localIterator.next());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.LibraryReplication
 * JD-Core Version:    0.6.2
 */
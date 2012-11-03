package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ModifyLibrary
{
  public static final class ModifyLibraryRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private List<String> forAddDocid_ = Collections.emptyList();
    private List<String> forArchiveDocid_ = Collections.emptyList();
    private List<String> forRemovalDocid_ = Collections.emptyList();
    private boolean hasLibraryId;
    private String libraryId_ = "";

    public ModifyLibraryRequest addForAddDocid(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.forAddDocid_.isEmpty())
        this.forAddDocid_ = new ArrayList();
      this.forAddDocid_.add(paramString);
      return this;
    }

    public ModifyLibraryRequest addForArchiveDocid(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.forArchiveDocid_.isEmpty())
        this.forArchiveDocid_ = new ArrayList();
      this.forArchiveDocid_.add(paramString);
      return this;
    }

    public ModifyLibraryRequest addForRemovalDocid(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.forRemovalDocid_.isEmpty())
        this.forRemovalDocid_ = new ArrayList();
      this.forRemovalDocid_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getForAddDocidList()
    {
      return this.forAddDocid_;
    }

    public List<String> getForArchiveDocidList()
    {
      return this.forArchiveDocid_;
    }

    public List<String> getForRemovalDocidList()
    {
      return this.forRemovalDocid_;
    }

    public String getLibraryId()
    {
      return this.libraryId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLibraryId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getLibraryId());
      int j = 0;
      Iterator localIterator1 = getForAddDocidList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getForAddDocidList().size();
      int m = 0;
      Iterator localIterator2 = getForRemovalDocidList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int n = k + m + 1 * getForRemovalDocidList().size();
      int i1 = 0;
      Iterator localIterator3 = getForArchiveDocidList().iterator();
      while (localIterator3.hasNext())
        i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator3.next());
      int i2 = n + i1 + 1 * getForArchiveDocidList().size();
      this.cachedSize = i2;
      return i2;
    }

    public boolean hasLibraryId()
    {
      return this.hasLibraryId;
    }

    public ModifyLibraryRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setLibraryId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          addForAddDocid(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          addForRemovalDocid(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        addForArchiveDocid(paramCodedInputStreamMicro.readString());
      }
    }

    public ModifyLibraryRequest setLibraryId(String paramString)
    {
      this.hasLibraryId = true;
      this.libraryId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLibraryId())
        paramCodedOutputStreamMicro.writeString(1, getLibraryId());
      Iterator localIterator1 = getForAddDocidList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(2, (String)localIterator1.next());
      Iterator localIterator2 = getForRemovalDocidList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator2.next());
      Iterator localIterator3 = getForArchiveDocidList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator3.next());
    }
  }

  public static final class ModifyLibraryResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasLibraryUpdate;
    private Library.LibraryUpdate libraryUpdate_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Library.LibraryUpdate getLibraryUpdate()
    {
      return this.libraryUpdate_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLibraryUpdate())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getLibraryUpdate());
      this.cachedSize = i;
      return i;
    }

    public boolean hasLibraryUpdate()
    {
      return this.hasLibraryUpdate;
    }

    public ModifyLibraryResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setLibraryUpdate(localLibraryUpdate);
      }
    }

    public ModifyLibraryResponse setLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate)
    {
      if (paramLibraryUpdate == null)
        throw new NullPointerException();
      this.hasLibraryUpdate = true;
      this.libraryUpdate_ = paramLibraryUpdate;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLibraryUpdate())
        paramCodedOutputStreamMicro.writeMessage(1, getLibraryUpdate());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.ModifyLibrary
 * JD-Core Version:    0.6.2
 */
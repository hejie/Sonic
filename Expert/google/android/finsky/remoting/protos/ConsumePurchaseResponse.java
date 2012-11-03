package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class ConsumePurchaseResponse extends MessageMicro
{
  private int cachedSize = -1;
  private boolean hasLibraryUpdate;
  private boolean hasStatus;
  private Library.LibraryUpdate libraryUpdate_ = null;
  private int status_ = 0;

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
    if (hasStatus())
      i += CodedOutputStreamMicro.computeInt32Size(2, getStatus());
    this.cachedSize = i;
    return i;
  }

  public int getStatus()
  {
    return this.status_;
  }

  public boolean hasLibraryUpdate()
  {
    return this.hasLibraryUpdate;
  }

  public boolean hasStatus()
  {
    return this.hasStatus;
  }

  public ConsumePurchaseResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        Library.LibraryUpdate localLibraryUpdate = new Library.LibraryUpdate();
        paramCodedInputStreamMicro.readMessage(localLibraryUpdate);
        setLibraryUpdate(localLibraryUpdate);
        break;
      case 16:
      }
      setStatus(paramCodedInputStreamMicro.readInt32());
    }
  }

  public ConsumePurchaseResponse setLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate)
  {
    if (paramLibraryUpdate == null)
      throw new NullPointerException();
    this.hasLibraryUpdate = true;
    this.libraryUpdate_ = paramLibraryUpdate;
    return this;
  }

  public ConsumePurchaseResponse setStatus(int paramInt)
  {
    this.hasStatus = true;
    this.status_ = paramInt;
    return this;
  }

  public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException
  {
    if (hasLibraryUpdate())
      paramCodedOutputStreamMicro.writeMessage(1, getLibraryUpdate());
    if (hasStatus())
      paramCodedOutputStreamMicro.writeInt32(2, getStatus());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.ConsumePurchaseResponse
 * JD-Core Version:    0.6.2
 */
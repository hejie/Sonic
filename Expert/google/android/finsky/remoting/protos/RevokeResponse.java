package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class RevokeResponse extends MessageMicro
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

  public RevokeResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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

  public RevokeResponse setLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate)
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

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.RevokeResponse
 * JD-Core Version:    0.6.2
 */
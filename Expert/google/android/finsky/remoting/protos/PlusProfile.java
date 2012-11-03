package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class PlusProfile
{
  public static final class PlusProfileResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPlusProfile;
    private DocAnnotations.PlusProfile plusProfile_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocAnnotations.PlusProfile getPlusProfile()
    {
      return this.plusProfile_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPlusProfile())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getPlusProfile());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPlusProfile()
    {
      return this.hasPlusProfile;
    }

    public PlusProfileResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        DocAnnotations.PlusProfile localPlusProfile = new DocAnnotations.PlusProfile();
        paramCodedInputStreamMicro.readMessage(localPlusProfile);
        setPlusProfile(localPlusProfile);
      }
    }

    public PlusProfileResponse setPlusProfile(DocAnnotations.PlusProfile paramPlusProfile)
    {
      if (paramPlusProfile == null)
        throw new NullPointerException();
      this.hasPlusProfile = true;
      this.plusProfile_ = paramPlusProfile;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPlusProfile())
        paramCodedOutputStreamMicro.writeMessage(1, getPlusProfile());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.PlusProfile
 * JD-Core Version:    0.6.2
 */
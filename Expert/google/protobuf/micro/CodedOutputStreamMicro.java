package com.google.protobuf.micro;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public final class CodedOutputStreamMicro
{
  private final byte[] buffer;
  private final int limit;
  private final OutputStream output;
  private int position;

  private CodedOutputStreamMicro(OutputStream paramOutputStream, byte[] paramArrayOfByte)
  {
    this.output = paramOutputStream;
    this.buffer = paramArrayOfByte;
    this.position = 0;
    this.limit = paramArrayOfByte.length;
  }

  private CodedOutputStreamMicro(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.output = null;
    this.buffer = paramArrayOfByte;
    this.position = paramInt1;
    this.limit = (paramInt1 + paramInt2);
  }

  public static int computeBoolSize(int paramInt, boolean paramBoolean)
  {
    return computeTagSize(paramInt) + computeBoolSizeNoTag(paramBoolean);
  }

  public static int computeBoolSizeNoTag(boolean paramBoolean)
  {
    return 1;
  }

  public static int computeBytesSize(int paramInt, ByteStringMicro paramByteStringMicro)
  {
    return computeTagSize(paramInt) + computeBytesSizeNoTag(paramByteStringMicro);
  }

  public static int computeBytesSizeNoTag(ByteStringMicro paramByteStringMicro)
  {
    return computeRawVarint32Size(paramByteStringMicro.size()) + paramByteStringMicro.size();
  }

  public static int computeDoubleSize(int paramInt, double paramDouble)
  {
    return computeTagSize(paramInt) + computeDoubleSizeNoTag(paramDouble);
  }

  public static int computeDoubleSizeNoTag(double paramDouble)
  {
    return 8;
  }

  public static int computeFixed64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeFixed64SizeNoTag(paramLong);
  }

  public static int computeFixed64SizeNoTag(long paramLong)
  {
    return 8;
  }

  public static int computeFloatSize(int paramInt, float paramFloat)
  {
    return computeTagSize(paramInt) + computeFloatSizeNoTag(paramFloat);
  }

  public static int computeFloatSizeNoTag(float paramFloat)
  {
    return 4;
  }

  public static int computeGroupSize(int paramInt, MessageMicro paramMessageMicro)
  {
    return 2 * computeTagSize(paramInt) + computeGroupSizeNoTag(paramMessageMicro);
  }

  public static int computeGroupSizeNoTag(MessageMicro paramMessageMicro)
  {
    return paramMessageMicro.getSerializedSize();
  }

  public static int computeInt32Size(int paramInt1, int paramInt2)
  {
    return computeTagSize(paramInt1) + computeInt32SizeNoTag(paramInt2);
  }

  public static int computeInt32SizeNoTag(int paramInt)
  {
    if (paramInt >= 0);
    for (int i = computeRawVarint32Size(paramInt); ; i = 10)
      return i;
  }

  public static int computeInt64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeInt64SizeNoTag(paramLong);
  }

  public static int computeInt64SizeNoTag(long paramLong)
  {
    return computeRawVarint64Size(paramLong);
  }

  public static int computeMessageSize(int paramInt, MessageMicro paramMessageMicro)
  {
    return computeTagSize(paramInt) + computeMessageSizeNoTag(paramMessageMicro);
  }

  public static int computeMessageSizeNoTag(MessageMicro paramMessageMicro)
  {
    int i = paramMessageMicro.getSerializedSize();
    return i + computeRawVarint32Size(i);
  }

  public static int computeRawVarint32Size(int paramInt)
  {
    int i;
    if ((paramInt & 0xFFFFFF80) == 0)
      i = 1;
    while (true)
    {
      return i;
      if ((paramInt & 0xFFFFC000) == 0)
        i = 2;
      else if ((0xFFE00000 & paramInt) == 0)
        i = 3;
      else if ((0xF0000000 & paramInt) == 0)
        i = 4;
      else
        i = 5;
    }
  }

  public static int computeRawVarint64Size(long paramLong)
  {
    int i;
    if ((0xFFFFFF80 & paramLong) == 0L)
      i = 1;
    while (true)
    {
      return i;
      if ((0xFFFFC000 & paramLong) == 0L)
        i = 2;
      else if ((0xFFE00000 & paramLong) == 0L)
        i = 3;
      else if ((0xF0000000 & paramLong) == 0L)
        i = 4;
      else if ((0x0 & paramLong) == 0L)
        i = 5;
      else if ((0x0 & paramLong) == 0L)
        i = 6;
      else if ((0x0 & paramLong) == 0L)
        i = 7;
      else if ((0x0 & paramLong) == 0L)
        i = 8;
      else if ((0x0 & paramLong) == 0L)
        i = 9;
      else
        i = 10;
    }
  }

  public static int computeStringSize(int paramInt, String paramString)
  {
    return computeTagSize(paramInt) + computeStringSizeNoTag(paramString);
  }

  public static int computeStringSizeNoTag(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      int i = computeRawVarint32Size(arrayOfByte.length);
      int j = arrayOfByte.length;
      return i + j;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UTF-8 not supported.");
  }

  public static int computeTagSize(int paramInt)
  {
    return computeRawVarint32Size(WireFormatMicro.makeTag(paramInt, 0));
  }

  public static int computeUInt64Size(int paramInt, long paramLong)
  {
    return computeTagSize(paramInt) + computeUInt64SizeNoTag(paramLong);
  }

  public static int computeUInt64SizeNoTag(long paramLong)
  {
    return computeRawVarint64Size(paramLong);
  }

  public static CodedOutputStreamMicro newInstance(OutputStream paramOutputStream)
  {
    return newInstance(paramOutputStream, 4096);
  }

  public static CodedOutputStreamMicro newInstance(OutputStream paramOutputStream, int paramInt)
  {
    return new CodedOutputStreamMicro(paramOutputStream, new byte[paramInt]);
  }

  public static CodedOutputStreamMicro newInstance(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new CodedOutputStreamMicro(paramArrayOfByte, paramInt1, paramInt2);
  }

  private void refreshBuffer()
    throws IOException
  {
    if (this.output == null)
      throw new OutOfSpaceException();
    this.output.write(this.buffer, 0, this.position);
    this.position = 0;
  }

  public void checkNoSpaceLeft()
  {
    if (spaceLeft() != 0)
      throw new IllegalStateException("Did not write as much data as expected.");
  }

  public void flush()
    throws IOException
  {
    if (this.output != null)
      refreshBuffer();
  }

  public int spaceLeft()
  {
    if (this.output == null)
      return this.limit - this.position;
    throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
  }

  public void writeBool(int paramInt, boolean paramBoolean)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeBoolNoTag(paramBoolean);
  }

  public void writeBoolNoTag(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      writeRawByte(i);
      return;
    }
  }

  public void writeBytes(int paramInt, ByteStringMicro paramByteStringMicro)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeBytesNoTag(paramByteStringMicro);
  }

  public void writeBytesNoTag(ByteStringMicro paramByteStringMicro)
    throws IOException
  {
    byte[] arrayOfByte = paramByteStringMicro.toByteArray();
    writeRawVarint32(arrayOfByte.length);
    writeRawBytes(arrayOfByte);
  }

  public void writeDouble(int paramInt, double paramDouble)
    throws IOException
  {
    writeTag(paramInt, 1);
    writeDoubleNoTag(paramDouble);
  }

  public void writeDoubleNoTag(double paramDouble)
    throws IOException
  {
    writeRawLittleEndian64(Double.doubleToLongBits(paramDouble));
  }

  public void writeFixed64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 1);
    writeFixed64NoTag(paramLong);
  }

  public void writeFixed64NoTag(long paramLong)
    throws IOException
  {
    writeRawLittleEndian64(paramLong);
  }

  public void writeFloat(int paramInt, float paramFloat)
    throws IOException
  {
    writeTag(paramInt, 5);
    writeFloatNoTag(paramFloat);
  }

  public void writeFloatNoTag(float paramFloat)
    throws IOException
  {
    writeRawLittleEndian32(Float.floatToIntBits(paramFloat));
  }

  public void writeGroup(int paramInt, MessageMicro paramMessageMicro)
    throws IOException
  {
    writeTag(paramInt, 3);
    writeGroupNoTag(paramMessageMicro);
    writeTag(paramInt, 4);
  }

  public void writeGroupNoTag(MessageMicro paramMessageMicro)
    throws IOException
  {
    paramMessageMicro.writeTo(this);
  }

  public void writeInt32(int paramInt1, int paramInt2)
    throws IOException
  {
    writeTag(paramInt1, 0);
    writeInt32NoTag(paramInt2);
  }

  public void writeInt32NoTag(int paramInt)
    throws IOException
  {
    if (paramInt >= 0)
      writeRawVarint32(paramInt);
    while (true)
    {
      return;
      writeRawVarint64(paramInt);
    }
  }

  public void writeInt64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeInt64NoTag(paramLong);
  }

  public void writeInt64NoTag(long paramLong)
    throws IOException
  {
    writeRawVarint64(paramLong);
  }

  public void writeMessage(int paramInt, MessageMicro paramMessageMicro)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeMessageNoTag(paramMessageMicro);
  }

  public void writeMessageNoTag(MessageMicro paramMessageMicro)
    throws IOException
  {
    writeRawVarint32(paramMessageMicro.getCachedSize());
    paramMessageMicro.writeTo(this);
  }

  public void writeRawByte(byte paramByte)
    throws IOException
  {
    if (this.position == this.limit)
      refreshBuffer();
    byte[] arrayOfByte = this.buffer;
    int i = this.position;
    this.position = (i + 1);
    arrayOfByte[i] = paramByte;
  }

  public void writeRawByte(int paramInt)
    throws IOException
  {
    writeRawByte((byte)paramInt);
  }

  public void writeRawBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    writeRawBytes(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void writeRawBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.limit - this.position >= paramInt2)
    {
      System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, paramInt2);
      this.position = (paramInt2 + this.position);
    }
    while (true)
    {
      return;
      int i = this.limit - this.position;
      System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.position, i);
      int j = paramInt1 + i;
      int k = paramInt2 - i;
      this.position = this.limit;
      refreshBuffer();
      if (k <= this.limit)
      {
        System.arraycopy(paramArrayOfByte, j, this.buffer, 0, k);
        this.position = k;
      }
      else
      {
        this.output.write(paramArrayOfByte, j, k);
      }
    }
  }

  public void writeRawLittleEndian32(int paramInt)
    throws IOException
  {
    writeRawByte(paramInt & 0xFF);
    writeRawByte(0xFF & paramInt >> 8);
    writeRawByte(0xFF & paramInt >> 16);
    writeRawByte(0xFF & paramInt >> 24);
  }

  public void writeRawLittleEndian64(long paramLong)
    throws IOException
  {
    writeRawByte(0xFF & (int)paramLong);
    writeRawByte(0xFF & (int)(paramLong >> 8));
    writeRawByte(0xFF & (int)(paramLong >> 16));
    writeRawByte(0xFF & (int)(paramLong >> 24));
    writeRawByte(0xFF & (int)(paramLong >> 32));
    writeRawByte(0xFF & (int)(paramLong >> 40));
    writeRawByte(0xFF & (int)(paramLong >> 48));
    writeRawByte(0xFF & (int)(paramLong >> 56));
  }

  public void writeRawVarint32(int paramInt)
    throws IOException
  {
    while (true)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        writeRawByte(paramInt);
        return;
      }
      writeRawByte(0x80 | paramInt & 0x7F);
      paramInt >>>= 7;
    }
  }

  public void writeRawVarint64(long paramLong)
    throws IOException
  {
    while (true)
    {
      if ((0xFFFFFF80 & paramLong) == 0L)
      {
        writeRawByte((int)paramLong);
        return;
      }
      writeRawByte(0x80 | 0x7F & (int)paramLong);
      paramLong >>>= 7;
    }
  }

  public void writeString(int paramInt, String paramString)
    throws IOException
  {
    writeTag(paramInt, 2);
    writeStringNoTag(paramString);
  }

  public void writeStringNoTag(String paramString)
    throws IOException
  {
    byte[] arrayOfByte = paramString.getBytes("UTF-8");
    writeRawVarint32(arrayOfByte.length);
    writeRawBytes(arrayOfByte);
  }

  public void writeTag(int paramInt1, int paramInt2)
    throws IOException
  {
    writeRawVarint32(WireFormatMicro.makeTag(paramInt1, paramInt2));
  }

  public void writeUInt64(int paramInt, long paramLong)
    throws IOException
  {
    writeTag(paramInt, 0);
    writeUInt64NoTag(paramLong);
  }

  public void writeUInt64NoTag(long paramLong)
    throws IOException
  {
    writeRawVarint64(paramLong);
  }

  public static class OutOfSpaceException extends IOException
  {
    private static final long serialVersionUID = -6947486886997889499L;

    OutOfSpaceException()
    {
      super();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.micro.CodedOutputStreamMicro
 * JD-Core Version:    0.6.2
 */
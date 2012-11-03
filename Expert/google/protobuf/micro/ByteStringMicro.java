package com.google.protobuf.micro;

import java.io.UnsupportedEncodingException;

public final class ByteStringMicro
{
  public static final ByteStringMicro EMPTY = new ByteStringMicro(new byte[0]);
  private final byte[] bytes;
  private volatile int hash = 0;

  private ByteStringMicro(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
  }

  public static ByteStringMicro copyFrom(byte[] paramArrayOfByte)
  {
    return copyFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static ByteStringMicro copyFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return new ByteStringMicro(arrayOfByte);
  }

  public static ByteStringMicro copyFromUtf8(String paramString)
  {
    try
    {
      ByteStringMicro localByteStringMicro = new ByteStringMicro(paramString.getBytes("UTF-8"));
      return localByteStringMicro;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UTF-8 not supported?");
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this);
    label92: 
    while (true)
    {
      return bool;
      if (!(paramObject instanceof ByteStringMicro))
      {
        bool = false;
      }
      else
      {
        ByteStringMicro localByteStringMicro = (ByteStringMicro)paramObject;
        int i = this.bytes.length;
        if (i != localByteStringMicro.bytes.length)
        {
          bool = false;
        }
        else
        {
          byte[] arrayOfByte1 = this.bytes;
          byte[] arrayOfByte2 = localByteStringMicro.bytes;
          for (int j = 0; ; j++)
          {
            if (j >= i)
              break label92;
            if (arrayOfByte1[j] != arrayOfByte2[j])
            {
              bool = false;
              break;
            }
          }
        }
      }
    }
  }

  public int hashCode()
  {
    int i = this.hash;
    if (i == 0)
    {
      byte[] arrayOfByte = this.bytes;
      int j = this.bytes.length;
      i = j;
      for (int k = 0; k < j; k++)
        i = i * 31 + arrayOfByte[k];
      if (i == 0)
        i = 1;
      this.hash = i;
    }
    return i;
  }

  public int size()
  {
    return this.bytes.length;
  }

  public byte[] toByteArray()
  {
    int i = this.bytes.length;
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(this.bytes, 0, arrayOfByte, 0, i);
    return arrayOfByte;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.micro.ByteStringMicro
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.ads;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public final class Crypto
{
  public static String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(arrayOfChar[((0xF0 & paramArrayOfByte[i]) >>> 4)]);
      localStringBuilder.append(arrayOfChar[(0xF & paramArrayOfByte[i])]);
    }
    return localStringBuilder.toString();
  }

  public static void calculateCrc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 0;
    for (int k = paramInt1; k + 16 <= paramInt2; k += 16)
      for (int m = 0; m < 16; m++)
      {
        i += (0xFF & paramArrayOfByte[(k + m)]);
        j += i;
      }
    while (k < paramInt2)
    {
      i += (0xFF & paramArrayOfByte[k]);
      j += i;
      k++;
    }
    loadInt(i % 65521 | j % 65521 << 16, paramArrayOfByte, paramInt2);
  }

  public static byte[] calculateMd5(String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    return MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"));
  }

  public static byte[] encryptMobileId(int paramInt1, int paramInt2, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    byte[] arrayOfByte1 = new byte[256];
    loadInt(paramInt1, arrayOfByte1, 0);
    loadInt(paramInt2, arrayOfByte1, 4);
    byte[] arrayOfByte2 = byteArrayToHexString(calculateMd5(paramString)).getBytes("UTF-8");
    for (int i = 0; i < arrayOfByte2.length; i++)
      arrayOfByte1[(i + 8)] = arrayOfByte2[i];
    loadInt(0, arrayOfByte1, 40);
    calculateCrc(arrayOfByte1, 0, 44);
    Random localRandom = new Random();
    for (int j = 48; j < 256; j++)
      arrayOfByte1[j] = ((byte)(0xFF & localRandom.nextInt(256)));
    byte[] arrayOfByte3 = new byte[256];
    new Encryptor().encrypt(arrayOfByte1, arrayOfByte3);
    return arrayOfByte3;
  }

  private static void loadInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)((0xFF00 & paramInt1) >>> 8));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)((0xFF0000 & paramInt1) >>> 16));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)((0xFF000000 & paramInt1) >>> 24));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.ads.Crypto
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util
{
  public static String secureHash(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(secureHashBytes(paramArrayOfByte), 11);
  }

  public static byte[] secureHashBytes(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      arrayOfByte = localMessageDigest.digest();
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        byte[] arrayOfByte = null;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Md5Util
 * JD-Core Version:    0.6.2
 */
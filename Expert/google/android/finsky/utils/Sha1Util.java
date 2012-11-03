package com.google.android.finsky.utils;

import android.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Util
{
  private static DigestResult digest(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte1 = new byte[1024];
    long l = 0L;
    MessageDigest localMessageDigest;
    int i;
    DigestResult localDigestResult;
    try
    {
      localMessageDigest = MessageDigest.getInstance("SHA-1");
      while (true)
      {
        i = paramInputStream.read(arrayOfByte1);
        if (i < 0)
          break label88;
        if (i != 0)
        {
          if (i != arrayOfByte1.length)
            break;
          localMessageDigest.update(arrayOfByte1);
          l += i;
        }
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localDigestResult = null;
    }
    while (true)
    {
      return localDigestResult;
      byte[] arrayOfByte2 = new byte[i];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, i);
      localMessageDigest.update(arrayOfByte2);
      break;
      label88: paramInputStream.close();
      localDigestResult = new DigestResult(Base64.encodeToString(localMessageDigest.digest(), 11), l, null);
    }
  }

  public static String secureHash(String paramString)
  {
    return secureHash(paramString.getBytes());
  }

  public static String secureHash(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramArrayOfByte);
      str = Base64.encodeToString(localMessageDigest.digest(), 11);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        String str = null;
    }
  }

  public static boolean verify(InputStream paramInputStream, String paramString, long paramLong)
    throws IOException
  {
    DigestResult localDigestResult = digest(paramInputStream);
    if ((paramString.equals(localDigestResult.sha1HashBase64)) && ((paramLong == -1L) || (localDigestResult.byteCount == paramLong)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private static class DigestResult
  {
    public final long byteCount;
    public final String sha1HashBase64;

    private DigestResult(String paramString, long paramLong)
    {
      this.byteCount = paramLong;
      this.sha1HashBase64 = paramString;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.Sha1Util
 * JD-Core Version:    0.6.2
 */
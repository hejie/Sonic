package com.google.android.finsky.library;

import com.google.android.finsky.utils.Objects;

public class LibraryAppEntry extends LibraryEntry
{
  public static final String[] ANY_CERTIFICATE_HASHES = null;
  public final String certificateHash;
  public final long refundPostDeliveryWindowMs;
  public final long refundPreDeliveryEndtimeMs;

  public LibraryAppEntry(String paramString1, String paramString2, int paramInt, long paramLong1, String paramString3, long paramLong2, long paramLong3)
  {
    super(paramString1, AccountLibrary.LIBRARY_ID_APPS, 3, paramString2, 1, paramInt, paramLong1);
    this.certificateHash = paramString3;
    this.refundPreDeliveryEndtimeMs = paramLong2;
    this.refundPostDeliveryWindowMs = paramLong3;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (!super.equals(paramObject))
      bool = false;
    while (true)
    {
      return bool;
      if (!(paramObject instanceof LibraryAppEntry))
      {
        bool = true;
      }
      else
      {
        LibraryAppEntry localLibraryAppEntry = (LibraryAppEntry)paramObject;
        bool = Objects.equal(this.certificateHash, localLibraryAppEntry.certificateHash);
      }
    }
  }

  public boolean hasMatchingCertificateHash(String[] paramArrayOfString)
  {
    boolean bool = true;
    if (paramArrayOfString == ANY_CERTIFICATE_HASHES);
    while (true)
    {
      return bool;
      int i = paramArrayOfString.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label43;
        if (paramArrayOfString[j].equals(this.certificateHash))
          break;
      }
      label43: bool = false;
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.docId;
    return String.format("{package=%s}", arrayOfObject);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryAppEntry
 * JD-Core Version:    0.6.2
 */
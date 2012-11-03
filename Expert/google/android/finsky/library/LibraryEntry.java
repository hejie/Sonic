package com.google.android.finsky.library;

import com.google.android.finsky.api.model.Document;

public class LibraryEntry
{
  public static final String UNKNOWN_ACCOUNT = null;
  public final String accountName;
  public final int backendId;
  public final String docId;
  public final int docType;
  public final long documentHash;
  public final String libraryId;
  public final int offerType;

  public LibraryEntry(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, int paramInt3)
  {
    this(paramString1, paramString2, paramInt1, paramString3, paramInt2, paramInt3, -9223372036854775808L);
  }

  public LibraryEntry(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, int paramInt3, long paramLong)
  {
    if (paramString3 == null)
      throw new NullPointerException();
    this.accountName = paramString1;
    this.libraryId = paramString2;
    this.backendId = paramInt1;
    this.docId = paramString3;
    this.docType = paramInt2;
    this.offerType = paramInt3;
    this.documentHash = paramLong;
  }

  public static LibraryEntry fromDocument(String paramString1, String paramString2, Document paramDocument, int paramInt)
  {
    return new LibraryEntry(paramString1, paramString2, paramDocument.getBackend(), paramDocument.getBackendDocId(), paramDocument.getDocumentType(), paramInt);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if (!(paramObject instanceof LibraryEntry))
      {
        bool = false;
      }
      else
      {
        LibraryEntry localLibraryEntry = (LibraryEntry)paramObject;
        if (this.backendId != localLibraryEntry.backendId)
          bool = false;
        else if (this.docType != localLibraryEntry.docType)
          bool = false;
        else if (this.offerType != localLibraryEntry.offerType)
          bool = false;
        else if ((this.accountName != UNKNOWN_ACCOUNT) && (localLibraryEntry.accountName != UNKNOWN_ACCOUNT) && (!this.accountName.equals(localLibraryEntry.accountName)))
          bool = false;
        else if (!this.docId.equals(localLibraryEntry.docId))
          bool = false;
        else if (!this.libraryId.equals(localLibraryEntry.libraryId))
          bool = false;
      }
    }
  }

  public int hashCode()
  {
    int i = 0;
    if (this.libraryId != null);
    for (int j = this.libraryId.hashCode(); ; j = 0)
    {
      int k = 31 * (j + 0);
      if (this.docId != null)
        i = this.docId.hashCode();
      return 31 * (31 * (k + i) + this.docType) + this.offerType;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryEntry
 * JD-Core Version:    0.6.2
 */
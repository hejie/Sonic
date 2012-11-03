package com.google.android.finsky.utils;

import android.accounts.Account;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class LibraryUtils
{
  public static Account getOwnerWithCurrentAccount(Document paramDocument, Libraries paramLibraries, Account paramAccount)
  {
    Account localAccount = null;
    List localList;
    if (paramDocument.getDocumentType() == 1)
    {
      localList = getOwners(paramDocument, paramLibraries);
      if (!localList.contains(paramAccount));
    }
    while (true)
    {
      return paramAccount;
      if (!localList.isEmpty())
        localAccount = (Account)localList.get(0);
      paramAccount = localAccount;
      continue;
      if (!isOwned(paramDocument, paramLibraries.getAccountLibrary(paramAccount)))
        paramAccount = null;
    }
  }

  public static Account getOwnerWithCurrentAccount(List<Document> paramList, Libraries paramLibraries, Account paramAccount)
  {
    Iterator localIterator = paramList.iterator();
    Account localAccount;
    do
    {
      if (!localIterator.hasNext())
        break;
      localAccount = getOwnerWithCurrentAccount((Document)localIterator.next(), paramLibraries, paramAccount);
    }
    while (localAccount == null);
    while (true)
    {
      return localAccount;
      localAccount = null;
    }
  }

  public static List<Account> getOwners(Document paramDocument, Libraries paramLibraries)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramLibraries.getAccountLibraries().iterator();
    while (localIterator.hasNext())
    {
      AccountLibrary localAccountLibrary = (AccountLibrary)localIterator.next();
      if (isOwned(paramDocument, localAccountLibrary))
        localArrayList.add(localAccountLibrary.getAccount());
    }
    return localArrayList;
  }

  public static boolean isAvailable(Document paramDocument, DfeToc paramDfeToc, Library paramLibrary)
  {
    if (paramDocument.getBackend() != 0)
      if (paramDfeToc != null)
      {
        if (paramDfeToc.getCorpus(paramDocument.getBackend()) == null)
        {
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = paramDocument.getDocId();
          FinskyLog.d("Corpus for %s is not available.", arrayOfObject3);
        }
      }
      else
        for (bool = false; ; bool = false)
        {
          return bool;
          if (paramDocument.getBackend() == 3)
            break;
        }
    int i = paramDocument.getAvailabilityRestriction();
    if (i == 1);
    for (boolean bool = true; ; bool = false)
    {
      if ((!bool) && (paramDocument.isAvailableIfOwned()) && (isOwned(paramDocument, paramLibrary)))
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramDocument.getDocId();
        arrayOfObject2[1] = Integer.valueOf(i);
        FinskyLog.d("%s available because owned, overriding [restriction=%d].", arrayOfObject2);
        bool = true;
      }
      if (bool)
        break;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramDocument.getDocId();
      arrayOfObject1[1] = Integer.valueOf(i);
      FinskyLog.d("%s not available [restriction=%d].", arrayOfObject1);
      break;
    }
  }

  public static boolean isOfferOwned(Document paramDocument, Library paramLibrary, int paramInt)
  {
    return paramLibrary.contains(LibraryEntry.fromDocument(LibraryEntry.UNKNOWN_ACCOUNT, AccountLibrary.getLibraryIdFromBackend(paramDocument.getBackend()), paramDocument, paramInt));
  }

  public static boolean isOwned(Document paramDocument, Library paramLibrary)
  {
    int i = 1;
    if ((isOfferOwned(paramDocument, paramLibrary, i)) || ((paramDocument.getBackend() == 4) && (isOfferOwned(paramDocument, paramLibrary, 7))) || ((paramDocument.getDocumentType() == 6) && ((isOfferOwned(paramDocument, paramLibrary, 4)) || (isOfferOwned(paramDocument, paramLibrary, 3)))));
    while (true)
    {
      return i;
      int j = 0;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.LibraryUtils
 * JD-Core Version:    0.6.2
 */
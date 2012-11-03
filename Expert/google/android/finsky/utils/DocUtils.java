package com.google.android.finsky.utils;

import android.util.SparseIntArray;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DocUtils
{
  public static final SparseIntArray DOCUMENT_TYPE_TO_BACKEND;
  public static final Map<String, Integer> PREFIX_TO_DOCUMENT_TYPE;

  static
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("app", Integer.valueOf(1));
    localHashMap.put("album", Integer.valueOf(2));
    localHashMap.put("artist", Integer.valueOf(3));
    localHashMap.put("book", Integer.valueOf(5));
    localHashMap.put("device", Integer.valueOf(14));
    localHashMap.put("magazine", Integer.valueOf(16));
    localHashMap.put("magazineissue", Integer.valueOf(17));
    localHashMap.put("movie", Integer.valueOf(6));
    localHashMap.put("song", Integer.valueOf(4));
    localHashMap.put("tvepisode", Integer.valueOf(20));
    localHashMap.put("tvseason", Integer.valueOf(19));
    localHashMap.put("tvshow", Integer.valueOf(18));
    PREFIX_TO_DOCUMENT_TYPE = Collections.unmodifiableMap(localHashMap);
    DOCUMENT_TYPE_TO_BACKEND = new SparseIntArray();
    DOCUMENT_TYPE_TO_BACKEND.put(1, 3);
    DOCUMENT_TYPE_TO_BACKEND.put(2, 2);
    DOCUMENT_TYPE_TO_BACKEND.put(3, 2);
    DOCUMENT_TYPE_TO_BACKEND.put(5, 1);
    DOCUMENT_TYPE_TO_BACKEND.put(14, 5);
    DOCUMENT_TYPE_TO_BACKEND.put(16, 6);
    DOCUMENT_TYPE_TO_BACKEND.put(17, 6);
    DOCUMENT_TYPE_TO_BACKEND.put(6, 4);
    DOCUMENT_TYPE_TO_BACKEND.put(4, 2);
    DOCUMENT_TYPE_TO_BACKEND.put(20, 7);
    DOCUMENT_TYPE_TO_BACKEND.put(19, 7);
    DOCUMENT_TYPE_TO_BACKEND.put(18, 7);
  }

  public static boolean canRate(Libraries paramLibraries, Document paramDocument)
  {
    boolean bool = true;
    if ((paramDocument.getBackend() != 3) || (!paramLibraries.getAppOwners(paramDocument.getAppDetails().getPackageName()).isEmpty()));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public static int docidToBackend(String paramString)
  {
    String[] arrayOfString = paramString.split("[:-]", 2);
    int i;
    if (arrayOfString.length == 1)
      i = 3;
    while (true)
    {
      return i;
      try
      {
        int j = DOCUMENT_TYPE_TO_BACKEND.get(((Integer)PREFIX_TO_DOCUMENT_TYPE.get(arrayOfString[0])).intValue());
        i = j;
      }
      catch (Exception localException)
      {
        i = -1;
      }
    }
  }

  private static String extractPackageNameForInApp(String paramString)
  {
    int i = paramString.indexOf(':');
    int j = paramString.indexOf(':', i + 1);
    if ((i > 0) && (i < j) && (j < paramString.length()));
    for (String str = paramString.substring(i + 1, j); ; str = null)
      return str;
  }

  public static String extractSkuForInApp(String paramString)
  {
    int i = paramString.lastIndexOf(':');
    if ((i > 0) && (i < paramString.length()));
    for (String str = paramString.substring(i + 1, paramString.length()); ; str = null)
      return str;
  }

  public static int getAvailabilityRestrictionResourceId(Document paramDocument)
  {
    int i = paramDocument.getAvailabilityRestriction();
    int j = 2131165707;
    switch (i)
    {
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    default:
    case 2:
    case 10:
    case 11:
    case 9:
    case 12:
    case 8:
    }
    while (true)
    {
      FinskyLog.d("Item is not available. Reason: " + i, new Object[0]);
      return j;
      j = 2131165708;
      continue;
      j = 2131165709;
      continue;
      j = 2131165710;
      continue;
      if (paramDocument.getDocumentType() == 1)
      {
        j = 2131165711;
      }
      else
      {
        j = 2131165712;
        continue;
        j = 2131165713;
        continue;
        j = 2131165714;
      }
    }
  }

  public static Common.Offer getListingOffer(Document paramDocument, DfeToc paramDfeToc, Library paramLibrary)
  {
    Object localObject;
    if (paramDocument.getDocumentType() == 16)
    {
      List localList = getMagazineSubscriptions(paramDocument, paramDfeToc, paramLibrary);
      ArrayList localArrayList = Lists.newArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        localArrayList.addAll(((Document)localIterator.next()).getAvailableOffers());
      localObject = getLowestPricedOffer(localArrayList, false);
      if (localObject == null)
        localObject = getLowestPricedOffer(localArrayList, true);
      if (localObject == null);
    }
    while (true)
    {
      return localObject;
      Document localDocument = getMagazineCurrentIssueDocument(paramDocument);
      if (localDocument != null)
      {
        Common.Offer localOffer = getMagazineIssueOffer(localDocument, paramDfeToc, paramLibrary);
        if ((localOffer != null) && (localOffer.hasFormattedAmount()))
          localObject = localOffer;
      }
      else
      {
        localObject = null;
        continue;
        localObject = getLowestPricedOffer(paramDocument.getAvailableOffers(), true);
      }
    }
  }

  public static Common.Offer getLowestPricedOffer(List<Common.Offer> paramList, boolean paramBoolean)
  {
    Object localObject = null;
    long l1 = 9223372036854775807L;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Common.Offer localOffer = (Common.Offer)localIterator.next();
      if (localOffer.hasFormattedAmount())
      {
        int i = localOffer.getOfferType();
        if ((i == 1) || (i == 7) || (i == 3) || (i == 4))
        {
          long l2 = localOffer.getMicros();
          if (((paramBoolean) || (l2 != 0L)) && (l2 < l1))
          {
            l1 = l2;
            localObject = localOffer;
          }
        }
      }
    }
    return localObject;
  }

  public static Document getMagazineCurrentIssueDocument(Document paramDocument)
  {
    if (paramDocument.getDocumentType() != 16)
      throw new IllegalArgumentException("This method should be called only on magazine docs. Passed type " + paramDocument.getDocumentType());
    if (paramDocument.getChildCount() == 0);
    for (Document localDocument = null; ; localDocument = paramDocument.getChildAt(0))
      return localDocument;
  }

  public static Common.Offer getMagazineIssueOffer(Document paramDocument, DfeToc paramDfeToc, Library paramLibrary)
  {
    Common.Offer localOffer = null;
    if (paramDocument.getDocumentType() != 17);
    while (true)
    {
      return localOffer;
      if (LibraryUtils.isAvailable(paramDocument, paramDfeToc, paramLibrary))
      {
        List localList = paramDocument.getAvailableOffers();
        if (localList.size() > 0)
          localOffer = (Common.Offer)localList.get(0);
      }
    }
  }

  public static List<Document> getMagazineSubscriptions(Document paramDocument, DfeToc paramDfeToc, Library paramLibrary)
  {
    if (paramDocument.hasSubscriptions())
    {
      localObject = Lists.newArrayList();
      Iterator localIterator = paramDocument.getSubscriptionsList().iterator();
      while (localIterator.hasNext())
      {
        Document localDocument = (Document)localIterator.next();
        if ((LibraryUtils.isAvailable(localDocument, paramDfeToc, paramLibrary)) && (!localDocument.getAvailableOffers().isEmpty()))
          ((List)localObject).add(localDocument);
      }
    }
    Object localObject = Collections.emptyList();
    return localObject;
  }

  public static String getPackageNameForInApp(String paramString)
  {
    if (!paramString.startsWith("inapp:"));
    for (String str = null; ; str = extractPackageNameForInApp(paramString))
      return str;
  }

  public static String getPackageNameForSubscription(String paramString)
  {
    if (!paramString.startsWith("subs:"));
    for (String str = null; ; str = extractPackageNameForInApp(paramString))
      return str;
  }

  public static boolean hasAutoRenewingSubscriptions(Libraries paramLibraries, Document paramDocument)
  {
    if ((paramDocument.getBackend() == 3) && (paramDocument.hasAppSubscriptions()))
    {
      LibrarySubscriptionEntry localLibrarySubscriptionEntry;
      do
      {
        Iterator localIterator1 = paramDocument.getAppSubscriptionsList().iterator();
        Document localDocument;
        Iterator localIterator2;
        while (!localIterator2.hasNext())
        {
          if (!localIterator1.hasNext())
            break;
          localDocument = (Document)localIterator1.next();
          localIterator2 = paramLibraries.getAccountLibraries().iterator();
        }
        localLibrarySubscriptionEntry = ((AccountLibrary)localIterator2.next()).getAppSubscriptionEntry(localDocument.getDocId());
      }
      while ((localLibrarySubscriptionEntry == null) || (!localLibrarySubscriptionEntry.isAutoRenewing));
    }
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static boolean packageHasAutoRenewingSubscriptions(Libraries paramLibraries, String paramString)
  {
    LibrarySubscriptionEntry localLibrarySubscriptionEntry;
    do
    {
      Iterator localIterator1 = paramLibraries.getAccountLibraries().iterator();
      Iterator localIterator2;
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext())
          break;
        localIterator2 = ((AccountLibrary)localIterator1.next()).getAppSubscriptionsList().iterator();
      }
      localLibrarySubscriptionEntry = (LibrarySubscriptionEntry)localIterator2.next();
    }
    while ((!getPackageNameForSubscription(localLibrarySubscriptionEntry.docId).equals(paramString)) || (!localLibrarySubscriptionEntry.isAutoRenewing));
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.DocUtils
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.library;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AppLibrary extends HashMapLibrary
{
  private Set<String> mInAppDocIdSet = Sets.newHashSet();
  private Set<String> mSubscriptionsDocIdSet = Sets.newHashSet();

  public AppLibrary(LibraryHasher paramLibraryHasher)
  {
    super(paramLibraryHasher);
  }

  public void add(LibraryEntry paramLibraryEntry)
  {
    if (paramLibraryEntry.docType == 15)
      this.mSubscriptionsDocIdSet.add(paramLibraryEntry.docId);
    while (true)
    {
      super.add(paramLibraryEntry);
      return;
      if (paramLibraryEntry.docType == 11)
        this.mInAppDocIdSet.add(paramLibraryEntry.docId);
    }
  }

  public void dumpState(String paramString1, String paramString2)
  {
    Log.d("FinskyLibrary", paramString2 + "AppLibrary (" + paramString1 + ") {");
    Log.d("FinskyLibrary", paramString2 + "  totalCount=" + size());
    Log.d("FinskyLibrary", paramString2 + "  subscriptionsCount=" + this.mSubscriptionsDocIdSet.size());
    Log.d("FinskyLibrary", paramString2 + "}");
  }

  public LibraryAppEntry getAppEntry(String paramString)
  {
    return (LibraryAppEntry)get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, paramString, 1, 1));
  }

  public LibraryInAppEntry getInAppEntry(String paramString)
  {
    return (LibraryInAppEntry)get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, paramString, 11, 1));
  }

  List<LibraryInAppEntry> getInAppPurchasesForPackage(String paramString)
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = this.mInAppDocIdSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (TextUtils.equals(DocUtils.getPackageNameForInApp(str), paramString))
        localArrayList.add(getInAppEntry(str));
    }
    return localArrayList;
  }

  public LibrarySubscriptionEntry getSubscriptionEntry(String paramString)
  {
    return (LibrarySubscriptionEntry)get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, paramString, 15, 1));
  }

  List<LibrarySubscriptionEntry> getSubscriptionsList()
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = this.mSubscriptionsDocIdSet.iterator();
    while (localIterator.hasNext())
      localArrayList.add(getSubscriptionEntry((String)localIterator.next()));
    return localArrayList;
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    if (paramLibraryEntry.docType == 15)
      this.mSubscriptionsDocIdSet.remove(paramLibraryEntry.docId);
    while (true)
    {
      super.remove(paramLibraryEntry);
      return;
      if (paramLibraryEntry.docType == 11)
        this.mInAppDocIdSet.remove(paramLibraryEntry.docId);
    }
  }

  public void reset()
  {
    try
    {
      this.mSubscriptionsDocIdSet.clear();
      super.reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(size());
    return String.format("{num apps=%d}", arrayOfObject);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.AppLibrary
 * JD-Core Version:    0.6.2
 */
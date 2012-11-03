package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.util.Log;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountLibrary
  implements Library
{
  public static final String[] LIBRARY_IDS = arrayOfString;
  public static final String LIBRARY_ID_APPS = String.valueOf(3);
  public static final String LIBRARY_ID_MAGAZINE;
  public static final String LIBRARY_ID_MUSIC;
  public static final String LIBRARY_ID_OCEAN = String.valueOf(1);
  public static final String LIBRARY_ID_YOUTUBE = String.valueOf(4);
  private Account mAccount;
  private final Map<String, HashingLibrary> mLibraries = new HashMap();
  private final List<Listener> mListeners = Lists.newArrayList();
  private boolean mListenersEnabled = true;
  private final Handler mNotificationHandler;
  private final Map<String, byte[]> mTokens = new HashMap();

  static
  {
    LIBRARY_ID_MUSIC = String.valueOf(2);
    LIBRARY_ID_MAGAZINE = String.valueOf(6);
    String[] arrayOfString = new String[6];
    arrayOfString[0] = LIBRARY_ID_APPS;
    arrayOfString[1] = LIBRARY_ID_OCEAN;
    arrayOfString[2] = LIBRARY_ID_YOUTUBE;
    arrayOfString[3] = LIBRARY_ID_MUSIC;
    arrayOfString[4] = LIBRARY_ID_MAGAZINE;
    arrayOfString[5] = "u-wl";
  }

  public AccountLibrary(Account paramAccount, Handler paramHandler)
  {
    this.mAccount = paramAccount;
    this.mNotificationHandler = paramHandler;
    this.mLibraries.put(LIBRARY_ID_APPS, new AppLibrary(new SumHasher()));
    this.mLibraries.put(LIBRARY_ID_MUSIC, new HashMapLibrary(new SumHasher()));
    this.mLibraries.put(LIBRARY_ID_OCEAN, new HashMapLibrary(new SumHasher()));
    this.mLibraries.put(LIBRARY_ID_YOUTUBE, new HashMapLibrary(new SumHasher()));
    this.mLibraries.put(LIBRARY_ID_MAGAZINE, new MagazinesLibrary(new SumHasher()));
    this.mLibraries.put("u-wl", new HashMapLibrary(new SumHasher()));
  }

  public static int getBackendFromLibraryId(String paramString)
  {
    int i;
    if (LIBRARY_ID_APPS.equals(paramString))
      i = 3;
    while (true)
    {
      return i;
      if (LIBRARY_ID_OCEAN.equals(paramString))
        i = 1;
      else if (LIBRARY_ID_YOUTUBE.equals(paramString))
        i = 4;
      else if (LIBRARY_ID_MUSIC.equals(paramString))
        i = 2;
      else if (LIBRARY_ID_MAGAZINE.equals(paramString))
        i = 6;
      else
        i = 0;
    }
  }

  public static String getLibraryIdFromBackend(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 5:
    default:
      str = null;
    case 3:
    case 1:
    case 4:
    case 2:
    case 6:
    }
    while (true)
    {
      return str;
      str = LIBRARY_ID_APPS;
      continue;
      str = LIBRARY_ID_OCEAN;
      continue;
      str = LIBRARY_ID_YOUTUBE;
      continue;
      str = LIBRARY_ID_MUSIC;
      continue;
      str = LIBRARY_ID_MAGAZINE;
    }
  }

  private void notifyListeners()
  {
    if (!this.mListenersEnabled);
    while (true)
    {
      return;
      final ArrayList localArrayList = new ArrayList(this.mListeners);
      this.mNotificationHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = localArrayList.iterator();
          while (localIterator.hasNext())
            ((AccountLibrary.Listener)localIterator.next()).onLibraryChange();
        }
      });
    }
  }

  public void add(LibraryEntry paramLibraryEntry)
  {
    try
    {
      if (!this.mAccount.name.equals(paramLibraryEntry.accountName))
        throw new IllegalArgumentException("Invalid account.");
    }
    finally
    {
    }
    HashingLibrary localHashingLibrary = (HashingLibrary)this.mLibraries.get(paramLibraryEntry.libraryId);
    if (localHashingLibrary != null)
    {
      localHashingLibrary.add(paramLibraryEntry);
      notifyListeners();
    }
  }

  public void addAll(Collection<? extends LibraryEntry> paramCollection)
  {
    try
    {
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
        add((LibraryEntry)localIterator.next());
    }
    finally
    {
    }
  }

  public void addListener(Listener paramListener)
  {
    try
    {
      this.mListeners.add(paramListener);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean contains(LibraryEntry paramLibraryEntry)
  {
    try
    {
      Library localLibrary = (Library)this.mLibraries.get(paramLibraryEntry.libraryId);
      if (localLibrary != null)
      {
        boolean bool2 = localLibrary.contains(paramLibraryEntry);
        bool1 = bool2;
        return bool1;
      }
      boolean bool1 = false;
    }
    finally
    {
    }
  }

  public void disableListeners()
  {
    try
    {
      this.mListenersEnabled = false;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void dumpState(String paramString)
  {
    String str1 = FinskyLog.scrubPii(this.mAccount.name);
    Log.d("FinskyLibrary", paramString + "AccountLibrary (account=" + str1 + ") {");
    Iterator localIterator = this.mLibraries.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      ((HashingLibrary)this.mLibraries.get(str2)).dumpState("library=" + str2, paramString + "  ");
    }
    Log.d("FinskyLibrary", paramString + "} (account=" + str1 + ")");
  }

  public void enableListeners()
  {
    try
    {
      this.mListenersEnabled = true;
      notifyListeners();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Account getAccount()
  {
    return this.mAccount;
  }

  public LibraryAppEntry getAppEntry(String paramString)
  {
    try
    {
      LibraryAppEntry localLibraryAppEntry = ((AppLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).getAppEntry(paramString);
      return localLibraryAppEntry;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public LibrarySubscriptionEntry getAppSubscriptionEntry(String paramString)
  {
    try
    {
      LibrarySubscriptionEntry localLibrarySubscriptionEntry = ((AppLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).getSubscriptionEntry(paramString);
      return localLibrarySubscriptionEntry;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<LibrarySubscriptionEntry> getAppSubscriptionsList()
  {
    try
    {
      List localList = ((AppLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).getSubscriptionsList();
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public LibraryInAppEntry getInAppEntry(String paramString)
  {
    try
    {
      LibraryInAppEntry localLibraryInAppEntry = ((AppLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).getInAppEntry(paramString);
      return localLibraryInAppEntry;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<LibraryInAppEntry> getInAppPurchasesForPackage(String paramString)
  {
    try
    {
      List localList = ((AppLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).getInAppPurchasesForPackage(paramString);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public HashingLibrary getLibrary(String paramString)
  {
    return (HashingLibrary)this.mLibraries.get(paramString);
  }

  public LibrarySubscriptionEntry getMagazinesSubscriptionEntry(String paramString)
  {
    try
    {
      LibrarySubscriptionEntry localLibrarySubscriptionEntry = ((MagazinesLibrary)this.mLibraries.get(LIBRARY_ID_MAGAZINE)).getSubscriptionEntry(paramString);
      return localLibrarySubscriptionEntry;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte[] getServerToken(String paramString)
  {
    try
    {
      byte[] arrayOfByte = (byte[])this.mTokens.get(paramString);
      return arrayOfByte;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Iterator<LibraryEntry> iterator()
  {
    throw new UnsupportedOperationException();
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    try
    {
      if (!this.mAccount.name.equals(paramLibraryEntry.accountName))
        throw new IllegalArgumentException();
    }
    finally
    {
    }
    Library localLibrary = (Library)this.mLibraries.get(paramLibraryEntry.libraryId);
    if (localLibrary != null)
    {
      localLibrary.remove(paramLibraryEntry);
      notifyListeners();
    }
  }

  public void resetLibrary(String paramString)
  {
    try
    {
      HashingLibrary localHashingLibrary = (HashingLibrary)this.mLibraries.get(paramString);
      if (localHashingLibrary == null)
        FinskyLog.w("Cannot reset: %s", new Object[] { paramString });
      while (true)
      {
        notifyListeners();
        return;
        localHashingLibrary.reset();
      }
    }
    finally
    {
    }
  }

  public void setServerToken(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      this.mTokens.put(paramString, paramArrayOfByte);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int size()
  {
    int i = 0;
    try
    {
      Iterator localIterator = this.mLibraries.values().iterator();
      while (localIterator.hasNext())
      {
        int j = ((HashingLibrary)localIterator.next()).size();
        i += j;
      }
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean supportsLibrary(String paramString)
  {
    return this.mLibraries.containsKey(paramString);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = FinskyLog.scrubPii(this.mAccount.name);
    arrayOfObject[1] = Integer.valueOf(((HashingLibrary)this.mLibraries.get(LIBRARY_ID_APPS)).size());
    return String.format("{account=%s numapps=%d}", arrayOfObject);
  }

  public static abstract interface Listener
  {
    public abstract void onLibraryChange();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.AccountLibrary
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Libraries
  implements Library
{
  private final Accounts mAccounts;
  private final Handler mBackgroundHandler;
  private final Map<Account, AccountLibrary> mLibraries = Maps.newHashMap();
  private final List<Listener> mListeners = Lists.newArrayList();
  private int mLoadedAccountHash;
  private final List<Runnable> mLoadingCallbacks = Lists.newArrayList();
  private final Handler mNotificationHandler;
  private final SQLiteLibrary mSQLiteLibrary;

  public Libraries(Accounts paramAccounts, SQLiteLibrary paramSQLiteLibrary, Handler paramHandler1, Handler paramHandler2)
  {
    this.mAccounts = paramAccounts;
    this.mSQLiteLibrary = paramSQLiteLibrary;
    this.mBackgroundHandler = paramHandler2;
    this.mNotificationHandler = paramHandler1;
  }

  private int computeAccountHash(List<Account> paramList)
  {
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      i += ((Account)localIterator.next()).hashCode();
    return i;
  }

  private void fireAllLibrariesLoaded()
  {
    this.mNotificationHandler.post(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 17	com/google/android/finsky/library/Libraries$6:this$0	Lcom/google/android/finsky/library/Libraries;
        //   4: invokestatic 24	com/google/android/finsky/library/Libraries:access$600	(Lcom/google/android/finsky/library/Libraries;)Ljava/util/List;
        //   7: astore_1
        //   8: aload_1
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 17	com/google/android/finsky/library/Libraries$6:this$0	Lcom/google/android/finsky/library/Libraries;
        //   14: invokestatic 24	com/google/android/finsky/library/Libraries:access$600	(Lcom/google/android/finsky/library/Libraries;)Ljava/util/List;
        //   17: invokeinterface 30 1 0
        //   22: astore_3
        //   23: aload_3
        //   24: invokeinterface 36 1 0
        //   29: ifeq +25 -> 54
        //   32: aload_3
        //   33: invokeinterface 40 1 0
        //   38: checkcast 42	com/google/android/finsky/library/Libraries$Listener
        //   41: invokeinterface 45 1 0
        //   46: goto -23 -> 23
        //   49: astore_2
        //   50: aload_1
        //   51: monitorexit
        //   52: aload_2
        //   53: athrow
        //   54: aload_1
        //   55: monitorexit
        //   56: return
        //
        // Exception table:
        //   from	to	target	type
        //   10	52	49	finally
        //   54	56	49	finally
      }
    });
  }

  private void notifyLibraryChanged(final AccountLibrary paramAccountLibrary)
  {
    this.mNotificationHandler.post(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	com/google/android/finsky/library/Libraries$5:this$0	Lcom/google/android/finsky/library/Libraries;
        //   4: invokestatic 29	com/google/android/finsky/library/Libraries:access$600	(Lcom/google/android/finsky/library/Libraries;)Ljava/util/List;
        //   7: astore_1
        //   8: aload_1
        //   9: monitorenter
        //   10: aload_0
        //   11: getfield 19	com/google/android/finsky/library/Libraries$5:this$0	Lcom/google/android/finsky/library/Libraries;
        //   14: invokestatic 29	com/google/android/finsky/library/Libraries:access$600	(Lcom/google/android/finsky/library/Libraries;)Ljava/util/List;
        //   17: invokeinterface 35 1 0
        //   22: astore_3
        //   23: aload_3
        //   24: invokeinterface 41 1 0
        //   29: ifeq +29 -> 58
        //   32: aload_3
        //   33: invokeinterface 45 1 0
        //   38: checkcast 47	com/google/android/finsky/library/Libraries$Listener
        //   41: aload_0
        //   42: getfield 21	com/google/android/finsky/library/Libraries$5:val$library	Lcom/google/android/finsky/library/AccountLibrary;
        //   45: invokeinterface 50 2 0
        //   50: goto -27 -> 23
        //   53: astore_2
        //   54: aload_1
        //   55: monitorexit
        //   56: aload_2
        //   57: athrow
        //   58: aload_1
        //   59: monitorexit
        //   60: return
        //
        // Exception table:
        //   from	to	target	type
        //   10	56	53	finally
        //   58	60	53	finally
      }
    });
  }

  private void runAndClearLoadingCallbacks()
  {
    try
    {
      Iterator localIterator = this.mLoadingCallbacks.iterator();
      while (localIterator.hasNext())
      {
        Runnable localRunnable = (Runnable)localIterator.next();
        if (localRunnable != null)
          this.mNotificationHandler.post(localRunnable);
      }
    }
    finally
    {
    }
    this.mLoadingCallbacks.clear();
  }

  private AccountLibrary setupAccountLibrary(Account paramAccount)
  {
    final AccountLibrary localAccountLibrary = new AccountLibrary(paramAccount, this.mNotificationHandler);
    localAccountLibrary.addListener(new AccountLibrary.Listener()
    {
      public void onLibraryChange()
      {
        Libraries.this.notifyLibraryChanged(localAccountLibrary);
      }
    });
    return localAccountLibrary;
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

  public void blockingLoad()
  {
    if ((Looper.myLooper() == this.mNotificationHandler.getLooper()) || (Looper.myLooper() == this.mBackgroundHandler.getLooper()))
      throw new IllegalStateException();
    if (isLoaded());
    while (true)
    {
      return;
      final CountDownLatch localCountDownLatch = new CountDownLatch(1);
      load(new Runnable()
      {
        public void run()
        {
          localCountDownLatch.countDown();
        }
      });
      try
      {
        localCountDownLatch.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        throw new RuntimeException(localInterruptedException);
      }
    }
  }

  public void cleanup()
  {
    try
    {
      this.mBackgroundHandler.post(new Runnable()
      {
        public void run()
        {
          Libraries.this.mSQLiteLibrary.reopen();
          Libraries.this.mSQLiteLibrary.resetKeepOnlyAccounts(Libraries.this.mAccounts.getAccounts());
          Libraries.this.mSQLiteLibrary.close();
        }
      });
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
      Iterator localIterator = this.mLibraries.values().iterator();
      while (localIterator.hasNext())
      {
        boolean bool2 = ((AccountLibrary)localIterator.next()).contains(paramLibraryEntry);
        if (bool2)
        {
          bool1 = true;
          return bool1;
        }
      }
      boolean bool1 = false;
    }
    finally
    {
    }
  }

  public void dumpState()
  {
    Log.d("FinskyLibrary", "| Libraries {");
    Iterator localIterator = this.mLibraries.values().iterator();
    while (localIterator.hasNext())
      ((AccountLibrary)localIterator.next()).dumpState("|   ");
    Log.d("FinskyLibrary", "| }");
  }

  public Collection<AccountLibrary> getAccountLibraries()
  {
    try
    {
      ArrayList localArrayList = Lists.newArrayList(this.mLibraries.values());
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public AccountLibrary getAccountLibrary(Account paramAccount)
  {
    try
    {
      AccountLibrary localAccountLibrary = (AccountLibrary)this.mLibraries.get(paramAccount);
      return localAccountLibrary;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<LibraryAppEntry> getAppEntries(String paramString, String[] paramArrayOfString)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = Lists.newArrayList();
      Iterator localIterator = this.mLibraries.values().iterator();
      while (localIterator.hasNext())
      {
        LibraryAppEntry localLibraryAppEntry = ((AccountLibrary)localIterator.next()).getAppEntry(paramString);
        if ((localLibraryAppEntry != null) && (localLibraryAppEntry.hasMatchingCertificateHash(paramArrayOfString)))
          localArrayList.add(localLibraryAppEntry);
      }
    }
    finally
    {
    }
    return localArrayList;
  }

  public List<Account> getAppOwners(String paramString)
  {
    try
    {
      List localList = getAppOwners(paramString, LibraryAppEntry.ANY_CERTIFICATE_HASHES);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<Account> getAppOwners(String paramString, String[] paramArrayOfString)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = Lists.newArrayList();
      Iterator localIterator = this.mLibraries.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        LibraryAppEntry localLibraryAppEntry = ((AccountLibrary)localEntry.getValue()).getAppEntry(paramString);
        if ((localLibraryAppEntry != null) && (localLibraryAppEntry.hasMatchingCertificateHash(paramArrayOfString)))
          localArrayList.add(localEntry.getKey());
      }
    }
    finally
    {
    }
    return localArrayList;
  }

  public boolean hasSubscriptions()
  {
    try
    {
      Iterator localIterator = this.mLibraries.entrySet().iterator();
      while (localIterator.hasNext())
      {
        boolean bool2 = ((AccountLibrary)((Map.Entry)localIterator.next()).getValue()).getAppSubscriptionsList().isEmpty();
        if (!bool2)
        {
          bool1 = true;
          return bool1;
        }
      }
      boolean bool1 = false;
    }
    finally
    {
    }
  }

  public boolean isLoaded()
  {
    try
    {
      int i = this.mLoadedAccountHash;
      int j = computeAccountHash(this.mAccounts.getAccounts());
      if (i == j)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public Iterator<LibraryEntry> iterator()
  {
    throw new UnsupportedOperationException();
  }

  public void load(Runnable paramRunnable)
  {
    while (true)
    {
      List localList;
      final int i;
      ArrayList localArrayList1;
      try
      {
        if (isLoaded())
        {
          this.mNotificationHandler.post(paramRunnable);
          return;
        }
        this.mLoadingCallbacks.add(paramRunnable);
        if (this.mLoadingCallbacks.size() > 1)
          continue;
        localList = this.mAccounts.getAccounts();
        i = computeAccountHash(localList);
        localArrayList1 = null;
        Iterator localIterator1 = this.mLibraries.keySet().iterator();
        if (localIterator1.hasNext())
        {
          Account localAccount4 = (Account)localIterator1.next();
          if (localArrayList1 == null)
            localArrayList1 = Lists.newArrayList();
          if (localList.contains(localAccount4))
            continue;
          localArrayList1.add(localAccount4);
          continue;
        }
      }
      finally
      {
      }
      if (localArrayList1 != null)
      {
        Iterator localIterator4 = localArrayList1.iterator();
        while (localIterator4.hasNext())
        {
          Account localAccount3 = (Account)localIterator4.next();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(localAccount3.name);
          FinskyLog.d("Unloading AccountLibrary for account: %s", arrayOfObject);
          this.mLibraries.remove(localAccount3);
        }
      }
      final ArrayList localArrayList2 = Lists.newArrayList();
      Iterator localIterator2 = localList.iterator();
      while (localIterator2.hasNext())
      {
        Account localAccount2 = (Account)localIterator2.next();
        if (!this.mLibraries.containsKey(localAccount2))
        {
          localArrayList2.add(localAccount2);
          AccountLibrary localAccountLibrary2 = setupAccountLibrary(localAccount2);
          this.mLibraries.put(localAccount2, localAccountLibrary2);
        }
      }
      if (localArrayList2.size() == 0)
      {
        fireAllLibrariesLoaded();
        runAndClearLoadingCallbacks();
        this.mLoadedAccountHash = i;
      }
      else
      {
        final int[] arrayOfInt = { 0 };
        Iterator localIterator3 = localArrayList2.iterator();
        while (localIterator3.hasNext())
        {
          final Account localAccount1 = (Account)localIterator3.next();
          AccountLibrary localAccountLibrary1 = (AccountLibrary)this.mLibraries.get(localAccount1);
          new LibraryLoader(this.mSQLiteLibrary, localAccountLibrary1, this.mNotificationHandler, this.mBackgroundHandler).load(new Runnable()
          {
            public void run()
            {
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = FinskyLog.scrubPii(localAccount1.name);
              FinskyLog.d("Loaded library for account: %s", arrayOfObject1);
              int[] arrayOfInt = arrayOfInt;
              arrayOfInt[0] = (1 + arrayOfInt[0]);
              if (arrayOfInt[0] == localArrayList2.size())
              {
                Object[] arrayOfObject2 = new Object[1];
                arrayOfObject2[0] = Integer.valueOf(localArrayList2.size());
                FinskyLog.d("Finished loading %d libraries.", arrayOfObject2);
                Libraries.this.fireAllLibrariesLoaded();
                Libraries.this.runAndClearLoadingCallbacks();
                Libraries.access$202(Libraries.this, i);
              }
            }
          });
        }
      }
    }
  }

  public void remove(LibraryEntry paramLibraryEntry)
  {
    throw new UnsupportedOperationException();
  }

  public void removeListener(Listener paramListener)
  {
    try
    {
      this.mListeners.remove(paramListener);
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
        int j = ((AccountLibrary)localIterator.next()).size();
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

  public static abstract interface Listener
  {
    public abstract void onAllLibrariesLoaded();

    public abstract void onLibraryContentsChanged(AccountLibrary paramAccountLibrary);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.Libraries
 * JD-Core Version:    0.6.2
 */
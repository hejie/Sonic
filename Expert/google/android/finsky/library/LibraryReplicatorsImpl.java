package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.util.Log;
import com.google.android.finsky.api.DfeApiProvider;
import com.google.android.finsky.remoting.protos.Library.LibraryUpdate;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LibraryReplicatorsImpl
  implements LibraryReplicators
{
  private final Handler mBackgroundHandler;
  private final DfeApiProvider mDfeApiProvider;
  private final boolean mEnableDebugging;
  private final Libraries mLibraries;
  private final List<LibraryReplicators.Listener> mListeners = Lists.newArrayList();
  private final Handler mNotificationHandler;
  private final Map<Account, LibraryReplicator> mReplicators = Maps.newHashMap();
  private final SQLiteLibrary mSQLiteLibrary;

  public LibraryReplicatorsImpl(DfeApiProvider paramDfeApiProvider, SQLiteLibrary paramSQLiteLibrary, Libraries paramLibraries, Handler paramHandler1, Handler paramHandler2, boolean paramBoolean)
  {
    this.mDfeApiProvider = paramDfeApiProvider;
    this.mSQLiteLibrary = paramSQLiteLibrary;
    this.mLibraries = paramLibraries;
    this.mNotificationHandler = paramHandler1;
    this.mBackgroundHandler = paramHandler2;
    this.mEnableDebugging = paramBoolean;
    reinitialize();
  }

  private void notifyListeners(AccountLibrary paramAccountLibrary, String paramString)
  {
    try
    {
      Iterator localIterator = this.mListeners.iterator();
      if (localIterator.hasNext())
        ((LibraryReplicators.Listener)localIterator.next()).onMutationsApplied(paramAccountLibrary, paramString);
    }
    finally
    {
    }
  }

  public void addListener(LibraryReplicators.Listener paramListener)
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

  public void applyLibraryUpdate(final Account paramAccount, final Library.LibraryUpdate paramLibraryUpdate, final String paramString)
  {
    this.mLibraries.load(new Runnable()
    {
      public void run()
      {
        synchronized (LibraryReplicatorsImpl.this)
        {
          if (!LibraryReplicatorsImpl.this.mReplicators.containsKey(paramAccount))
          {
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = FinskyLog.scrubPii(paramAccount.name);
            FinskyLog.e("LibraryUpdate for unknown account %s could not be applied", arrayOfObject2);
          }
          else
          {
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = FinskyLog.scrubPii(paramAccount.name);
            arrayOfObject1[1] = Integer.valueOf(paramLibraryUpdate.getCorpus());
            arrayOfObject1[2] = Integer.valueOf(paramLibraryUpdate.getMutationCount());
            FinskyLog.d("Applying library update: account=%s, corpus=%d, numMutations=%d", arrayOfObject1);
            ((LibraryReplicator)LibraryReplicatorsImpl.this.mReplicators.get(paramAccount)).applyLibraryUpdate(paramLibraryUpdate, paramString);
          }
        }
      }
    });
  }

  public void dumpState()
  {
    Log.d("FinskyLibrary", "| LibraryReplicators {");
    Iterator localIterator = this.mReplicators.values().iterator();
    while (localIterator.hasNext())
      ((LibraryReplicator)localIterator.next()).dumpState("|   ");
    Log.d("FinskyLibrary", "| }");
  }

  public void reinitialize()
  {
    try
    {
      this.mReplicators.clear();
      Iterator localIterator = this.mLibraries.getAccountLibraries().iterator();
      if (localIterator.hasNext())
      {
        final AccountLibrary localAccountLibrary = (AccountLibrary)localIterator.next();
        Account localAccount = localAccountLibrary.getAccount();
        LibraryReplicator localLibraryReplicator = new LibraryReplicator(this.mDfeApiProvider.getDfeApi(localAccount.name), this.mSQLiteLibrary, localAccountLibrary, this.mNotificationHandler, this.mBackgroundHandler, this.mEnableDebugging);
        localLibraryReplicator.addListener(new LibraryReplicator.Listener()
        {
          public void onMutationsApplied(String paramAnonymousString)
          {
            LibraryReplicatorsImpl.this.notifyListeners(localAccountLibrary, paramAnonymousString);
          }
        });
        this.mReplicators.put(localAccount, localLibraryReplicator);
      }
    }
    finally
    {
    }
  }

  public void replicateAccount(final Account paramAccount, final String[] paramArrayOfString, final Runnable paramRunnable, final String paramString)
  {
    this.mLibraries.load(new Runnable()
    {
      public void run()
      {
        synchronized (LibraryReplicatorsImpl.this)
        {
          ((LibraryReplicator)LibraryReplicatorsImpl.this.mReplicators.get(paramAccount)).replicate(paramArrayOfString, paramRunnable, paramString);
          return;
        }
      }
    });
  }

  public void replicateAllAccounts(final Runnable paramRunnable, final String paramString)
  {
    this.mLibraries.load(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 21	com/google/android/finsky/library/LibraryReplicatorsImpl$2:this$0	Lcom/google/android/finsky/library/LibraryReplicatorsImpl;
        //   4: astore_1
        //   5: aload_1
        //   6: monitorenter
        //   7: aload_0
        //   8: getfield 21	com/google/android/finsky/library/LibraryReplicatorsImpl$2:this$0	Lcom/google/android/finsky/library/LibraryReplicatorsImpl;
        //   11: invokestatic 33	com/google/android/finsky/library/LibraryReplicatorsImpl:access$100	(Lcom/google/android/finsky/library/LibraryReplicatorsImpl;)Ljava/util/Map;
        //   14: invokeinterface 39 1 0
        //   19: astore_3
        //   20: new 41	com/google/android/finsky/library/LibraryReplicatorsImpl$2$1
        //   23: dup
        //   24: aload_0
        //   25: aload_3
        //   26: invokespecial 44	com/google/android/finsky/library/LibraryReplicatorsImpl$2$1:<init>	(Lcom/google/android/finsky/library/LibraryReplicatorsImpl$2;Ljava/util/Collection;)V
        //   29: astore 4
        //   31: aload_3
        //   32: invokeinterface 50 1 0
        //   37: astore 5
        //   39: aload 5
        //   41: invokeinterface 56 1 0
        //   46: ifeq +33 -> 79
        //   49: aload 5
        //   51: invokeinterface 60 1 0
        //   56: checkcast 62	com/google/android/finsky/library/LibraryReplicator
        //   59: getstatic 68	com/google/android/finsky/library/AccountLibrary:LIBRARY_IDS	[Ljava/lang/String;
        //   62: aload 4
        //   64: aload_0
        //   65: getfield 25	com/google/android/finsky/library/LibraryReplicatorsImpl$2:val$debugTag	Ljava/lang/String;
        //   68: invokevirtual 72	com/google/android/finsky/library/LibraryReplicator:replicate	([Ljava/lang/String;Ljava/lang/Runnable;Ljava/lang/String;)V
        //   71: goto -32 -> 39
        //   74: astore_2
        //   75: aload_1
        //   76: monitorexit
        //   77: aload_2
        //   78: athrow
        //   79: aload_1
        //   80: monitorexit
        //   81: return
        //
        // Exception table:
        //   from	to	target	type
        //   7	77	74	finally
        //   79	81	74	finally
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryReplicatorsImpl
 * JD-Core Version:    0.6.2
 */
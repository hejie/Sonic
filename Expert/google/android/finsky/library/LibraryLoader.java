package com.google.android.finsky.library;

import android.os.Handler;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class LibraryLoader
{
  private final AccountLibrary mAccountLibrary;
  private final Handler mBackgroundHandler;
  private final List<Runnable> mLoadingCallbacks = Lists.newArrayList();
  private final Handler mNotificationHandler;
  private final SQLiteLibrary mSQLiteLibrary;
  private State mState = State.UNINITIALIZED;

  public LibraryLoader(SQLiteLibrary paramSQLiteLibrary, AccountLibrary paramAccountLibrary, Handler paramHandler1, Handler paramHandler2)
  {
    this.mSQLiteLibrary = paramSQLiteLibrary;
    this.mAccountLibrary = paramAccountLibrary;
    this.mBackgroundHandler = paramHandler2;
    this.mNotificationHandler = paramHandler1;
  }

  public void load(Runnable paramRunnable)
  {
    try
    {
      this.mLoadingCallbacks.add(paramRunnable);
      this.mBackgroundHandler.post(new Runnable()
      {
        public void run()
        {
          LibraryLoader.this.loadBlocking();
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

  // ERROR //
  void loadBlocking()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 36	com/google/android/finsky/library/LibraryLoader:mState	Lcom/google/android/finsky/library/LibraryLoader$State;
    //   4: getstatic 34	com/google/android/finsky/library/LibraryLoader$State:UNINITIALIZED	Lcom/google/android/finsky/library/LibraryLoader$State;
    //   7: if_acmpne +191 -> 198
    //   10: aload_0
    //   11: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   14: invokevirtual 69	com/google/android/finsky/library/AccountLibrary:disableListeners	()V
    //   17: aload_0
    //   18: getfield 38	com/google/android/finsky/library/LibraryLoader:mSQLiteLibrary	Lcom/google/android/finsky/library/SQLiteLibrary;
    //   21: invokevirtual 74	com/google/android/finsky/library/SQLiteLibrary:reopen	()V
    //   24: aload_0
    //   25: getfield 38	com/google/android/finsky/library/LibraryLoader:mSQLiteLibrary	Lcom/google/android/finsky/library/SQLiteLibrary;
    //   28: invokevirtual 78	com/google/android/finsky/library/SQLiteLibrary:iterator	()Ljava/util/Iterator;
    //   31: astore 5
    //   33: aload 5
    //   35: invokeinterface 84 1 0
    //   40: ifeq +48 -> 88
    //   43: aload 5
    //   45: invokeinterface 88 1 0
    //   50: checkcast 90	com/google/android/finsky/library/LibraryEntry
    //   53: astore 12
    //   55: aload_0
    //   56: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   59: invokevirtual 94	com/google/android/finsky/library/AccountLibrary:getAccount	()Landroid/accounts/Account;
    //   62: getfield 100	android/accounts/Account:name	Ljava/lang/String;
    //   65: aload 12
    //   67: getfield 103	com/google/android/finsky/library/LibraryEntry:accountName	Ljava/lang/String;
    //   70: invokevirtual 108	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   73: ifeq -40 -> 33
    //   76: aload_0
    //   77: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   80: aload 12
    //   82: invokevirtual 111	com/google/android/finsky/library/AccountLibrary:add	(Lcom/google/android/finsky/library/LibraryEntry;)V
    //   85: goto -52 -> 33
    //   88: getstatic 115	com/google/android/finsky/library/AccountLibrary:LIBRARY_IDS	[Ljava/lang/String;
    //   91: astore 6
    //   93: aload 6
    //   95: arraylength
    //   96: istore 7
    //   98: iconst_0
    //   99: istore 8
    //   101: iload 8
    //   103: iload 7
    //   105: if_icmpge +72 -> 177
    //   108: aload 6
    //   110: iload 8
    //   112: aaload
    //   113: astore 9
    //   115: aload 9
    //   117: invokestatic 121	com/google/android/finsky/utils/FinskyPreferences:getLibraryServerToken	(Ljava/lang/String;)Lcom/google/android/finsky/config/PreferenceFile$PrefixSharedPreference;
    //   120: aload_0
    //   121: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   124: invokevirtual 94	com/google/android/finsky/library/AccountLibrary:getAccount	()Landroid/accounts/Account;
    //   127: getfield 100	android/accounts/Account:name	Ljava/lang/String;
    //   130: invokevirtual 127	com/google/android/finsky/config/PreferenceFile$PrefixSharedPreference:get	(Ljava/lang/String;)Lcom/google/android/finsky/config/PreferenceFile$SharedPreference;
    //   133: invokevirtual 131	com/google/android/finsky/config/PreferenceFile$SharedPreference:get	()Ljava/lang/Object;
    //   136: checkcast 105	java/lang/String
    //   139: astore 10
    //   141: aload 10
    //   143: ifnull +28 -> 171
    //   146: aload 10
    //   148: iconst_0
    //   149: invokestatic 137	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   152: astore 11
    //   154: aload_0
    //   155: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   158: aload 9
    //   160: aload 11
    //   162: invokevirtual 141	com/google/android/finsky/library/AccountLibrary:setServerToken	(Ljava/lang/String;[B)V
    //   165: iinc 8 1
    //   168: goto -67 -> 101
    //   171: aconst_null
    //   172: astore 11
    //   174: goto -20 -> 154
    //   177: aload_0
    //   178: getstatic 144	com/google/android/finsky/library/LibraryLoader$State:LOADED	Lcom/google/android/finsky/library/LibraryLoader$State;
    //   181: putfield 36	com/google/android/finsky/library/LibraryLoader:mState	Lcom/google/android/finsky/library/LibraryLoader$State;
    //   184: aload_0
    //   185: getfield 38	com/google/android/finsky/library/LibraryLoader:mSQLiteLibrary	Lcom/google/android/finsky/library/SQLiteLibrary;
    //   188: invokevirtual 147	com/google/android/finsky/library/SQLiteLibrary:close	()V
    //   191: aload_0
    //   192: getfield 40	com/google/android/finsky/library/LibraryLoader:mAccountLibrary	Lcom/google/android/finsky/library/AccountLibrary;
    //   195: invokevirtual 150	com/google/android/finsky/library/AccountLibrary:enableListeners	()V
    //   198: aload_0
    //   199: monitorenter
    //   200: aload_0
    //   201: getfield 29	com/google/android/finsky/library/LibraryLoader:mLoadingCallbacks	Ljava/util/List;
    //   204: invokeinterface 151 1 0
    //   209: astore_2
    //   210: aload_2
    //   211: invokeinterface 84 1 0
    //   216: ifeq +30 -> 246
    //   219: aload_2
    //   220: invokeinterface 88 1 0
    //   225: checkcast 153	java/lang/Runnable
    //   228: astore_3
    //   229: aload_0
    //   230: getfield 44	com/google/android/finsky/library/LibraryLoader:mNotificationHandler	Landroid/os/Handler;
    //   233: aload_3
    //   234: invokevirtual 63	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   237: pop
    //   238: goto -28 -> 210
    //   241: astore_1
    //   242: aload_0
    //   243: monitorexit
    //   244: aload_1
    //   245: athrow
    //   246: aload_0
    //   247: getfield 29	com/google/android/finsky/library/LibraryLoader:mLoadingCallbacks	Ljava/util/List;
    //   250: invokeinterface 156 1 0
    //   255: aload_0
    //   256: monitorexit
    //   257: return
    //
    // Exception table:
    //   from	to	target	type
    //   200	244	241	finally
    //   246	257	241	finally
  }

  private static enum State
  {
    static
    {
      LOADING = new State("LOADING", 1);
      LOADED = new State("LOADED", 2);
      State[] arrayOfState = new State[3];
      arrayOfState[0] = UNINITIALIZED;
      arrayOfState[1] = LOADING;
      arrayOfState[2] = LOADED;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryLoader
 * JD-Core Version:    0.6.2
 */
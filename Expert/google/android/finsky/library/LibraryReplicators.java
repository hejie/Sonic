package com.google.android.finsky.library;

import android.accounts.Account;
import com.google.android.finsky.remoting.protos.Library.LibraryUpdate;

public abstract interface LibraryReplicators
{
  public abstract void addListener(Listener paramListener);

  public abstract void applyLibraryUpdate(Account paramAccount, Library.LibraryUpdate paramLibraryUpdate, String paramString);

  public abstract void dumpState();

  public abstract void reinitialize();

  public abstract void replicateAccount(Account paramAccount, String[] paramArrayOfString, Runnable paramRunnable, String paramString);

  public abstract void replicateAllAccounts(Runnable paramRunnable, String paramString);

  public static abstract interface Listener
  {
    public abstract void onMutationsApplied(AccountLibrary paramAccountLibrary, String paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryReplicators
 * JD-Core Version:    0.6.2
 */
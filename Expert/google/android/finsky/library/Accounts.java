package com.google.android.finsky.library;

import android.accounts.Account;
import java.util.List;

public abstract interface Accounts
{
  public abstract Account getAccount(String paramString);

  public abstract List<Account> getAccounts();
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.Accounts
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.api;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;

public class AccountHandler
{
  public static Account findAccount(String paramString, Context paramContext)
  {
    Account localAccount;
    if (TextUtils.isEmpty(paramString))
      localAccount = null;
    while (true)
    {
      return localAccount;
      Account[] arrayOfAccount = getAccounts(paramContext);
      int i = arrayOfAccount.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label54;
        localAccount = arrayOfAccount[j];
        if (localAccount.name.equals(paramString))
          break;
      }
      label54: localAccount = null;
    }
  }

  public static Account getAccountFromPreferences(Context paramContext, PreferenceFile.SharedPreference<String> paramSharedPreference)
  {
    String str = (String)paramSharedPreference.get();
    Account localAccount;
    if (hasAccount(str, paramContext))
      localAccount = new Account(str, "com.google");
    while (true)
    {
      return localAccount;
      localAccount = getFirstAccount(paramContext);
      saveAccountToPreferences(localAccount, paramSharedPreference);
    }
  }

  public static Account[] getAccounts(Context paramContext)
  {
    return AccountManager.get(paramContext).getAccountsByType("com.google");
  }

  public static Account getFirstAccount(Context paramContext)
  {
    Account[] arrayOfAccount = getAccounts(paramContext);
    if (arrayOfAccount.length > 0);
    for (Account localAccount = arrayOfAccount[0]; ; localAccount = null)
      return localAccount;
  }

  public static boolean hasAccount(String paramString, Context paramContext)
  {
    if (findAccount(paramString, paramContext) != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void saveAccountToPreferences(Account paramAccount, PreferenceFile.SharedPreference<String> paramSharedPreference)
  {
    if (paramAccount == null);
    while (true)
    {
      return;
      paramSharedPreference.put(paramAccount.name);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.AccountHandler
 * JD-Core Version:    0.6.2
 */
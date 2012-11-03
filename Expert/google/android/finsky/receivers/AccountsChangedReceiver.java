package com.google.android.finsky.receivers;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.services.DailyHygiene;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.VendingPreferences;

public class AccountsChangedReceiver extends BroadcastReceiver
{
  private void restoreNewAccountApps(Context paramContext)
  {
    Account[] arrayOfAccount = AccountHandler.getAccounts(paramContext);
    String str = (String)VendingPreferences.RESTORED_ANDROID_ID.get();
    if (!TextUtils.isEmpty(str))
    {
      String[] arrayOfString = VendingPreferences.getNewAccounts(arrayOfAccount);
      if (arrayOfString.length > 0)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(arrayOfString.length);
        FinskyLog.d("Restoring apps for %d new accounts.", arrayOfObject);
        RestoreService.start(paramContext, str, arrayOfString[0]);
      }
    }
    VendingPreferences.saveCurrentAccountList(arrayOfAccount);
  }

  public void onReceive(final Context paramContext, Intent paramIntent)
  {
    restoreNewAccountApps(paramContext);
    DailyHygiene.schedule(paramContext, DailyHygiene.IMMEDIATE_DELAY_MS);
    FinskyApp.get().clearCacheAsync(new Runnable()
    {
      public void run()
      {
        String str = FinskyApp.get().getCurrentAccountName();
        if ((str != null) && (!AccountHandler.hasAccount(str, paramContext)))
          System.exit(0);
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.AccountsChangedReceiver
 * JD-Core Version:    0.6.2
 */
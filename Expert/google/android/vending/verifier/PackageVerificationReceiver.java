package com.google.android.vending.verifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.utils.FinskyLog;

public class PackageVerificationReceiver extends BroadcastReceiver
{
  private boolean checkPrerequisites(Bundle paramBundle)
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 17)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(Build.VERSION.SDK_INT);
      FinskyLog.d("Skipping verification because SDK=%d", arrayOfObject);
      bool = false;
    }
    while (true)
    {
      return bool;
      if (!((Boolean)G.platformAntiMalwareEnabled.get()).booleanValue())
      {
        FinskyLog.d("Skipping verification because disabled", new Object[0]);
        bool = false;
      }
      else if (!FinskyApp.get().getInstallPolicies().hasNetwork())
      {
        FinskyLog.d("Skipping verification because network inactive", new Object[0]);
        bool = false;
      }
      else if (paramBundle.getInt("android.content.pm.extra.VERIFICATION_INSTALLER_UID") == Process.myUid())
      {
        FinskyLog.d("Skipping verification because own installation", new Object[0]);
        bool = false;
      }
      else
      {
        bool = true;
      }
    }
  }

  private void reportVerificationOk(Context paramContext, int paramInt)
  {
    paramContext.getPackageManager().verifyPendingInstall(paramInt, 1);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    int i;
    if (("android.intent.action.PACKAGE_NEEDS_VERIFICATION".equals(str)) || ("android.intent.action.PACKAGE_VERIFIED".equals(str)))
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        i = localBundle.getInt("android.content.pm.extra.VERIFICATION_ID");
        if (!checkPrerequisites(localBundle))
          break label80;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        FinskyLog.d("Verification requested, id = %d", arrayOfObject);
        PackageVerificationService.start(paramContext, paramIntent);
      }
    }
    while (true)
    {
      return;
      label80: reportVerificationOk(paramContext, i);
      continue;
      FinskyLog.w("Unexpected action %s", new Object[] { str });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.PackageVerificationReceiver
 * JD-Core Version:    0.6.2
 */
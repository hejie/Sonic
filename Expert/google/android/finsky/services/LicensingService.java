package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.RemoteException;
import com.android.vending.licensing.ILicenseResultListener;
import com.android.vending.licensing.ILicensingService.Stub;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.CheckLicenseRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.CheckLicenseResponseProto;
import java.util.List;

public class LicensingService extends Service
{
  private final ILicensingService.Stub mBinder = new ILicensingService.Stub()
  {
    private void checkLicense(long paramAnonymousLong, String paramAnonymousString1, final ILicenseResultListener paramAnonymousILicenseResultListener, int paramAnonymousInt, String paramAnonymousString2)
    {
      VendingProtos.CheckLicenseRequestProto localCheckLicenseRequestProto = new VendingProtos.CheckLicenseRequestProto();
      localCheckLicenseRequestProto.setPackageName(paramAnonymousString1);
      localCheckLicenseRequestProto.setVersionCode(paramAnonymousInt);
      localCheckLicenseRequestProto.setNonce(paramAnonymousLong);
      FinskyApp.get().getVendingApi(paramAnonymousString2).checkLicense(localCheckLicenseRequestProto, new Response.Listener()
      {
        public void onResponse(VendingProtos.CheckLicenseResponseProto paramAnonymous2CheckLicenseResponseProto)
        {
          LicensingService.notifyListener(paramAnonymousILicenseResultListener, paramAnonymous2CheckLicenseResponseProto.getResponseCode(), paramAnonymous2CheckLicenseResponseProto.getSignedData(), paramAnonymous2CheckLicenseResponseProto.getSignature());
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymous2VolleyError)
        {
          LicensingService.notifyListener(paramAnonymousILicenseResultListener, 257, null, null);
        }
      });
    }

    public void checkLicense(long paramAnonymousLong, String paramAnonymousString, ILicenseResultListener paramAnonymousILicenseResultListener)
    {
      int i;
      AppStates.AppState localAppState;
      try
      {
        PackageInfo localPackageInfo = LicensingService.this.getPackageManager().getPackageInfo(paramAnonymousString, 0);
        if (localPackageInfo.applicationInfo.uid != getCallingUid())
        {
          LicensingService.notifyListener(paramAnonymousILicenseResultListener, 259, null, null);
        }
        else
        {
          i = localPackageInfo.versionCode;
          AppStates localAppStates = FinskyApp.get().getAppStates();
          localAppStates.blockingLoad();
          localAppState = localAppStates.getApp(paramAnonymousString);
          if ((localAppState != null) && (localAppState.installerData != null))
            checkLicense(paramAnonymousLong, paramAnonymousString, paramAnonymousILicenseResultListener, i, localAppState.installerData.getAccountName());
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        LicensingService.notifyListener(paramAnonymousILicenseResultListener, 258, null, null);
      }
      Libraries localLibraries = FinskyApp.get().getLibraries();
      localLibraries.blockingLoad();
      List localList = localLibraries.getAppOwners(paramAnonymousString, localAppState.packageManagerState.certificateHashes);
      if (!localList.isEmpty())
      {
        checkLicense(paramAnonymousLong, paramAnonymousString, paramAnonymousILicenseResultListener, i, ((Account)localList.get(0)).name);
      }
      else
      {
        Account localAccount = AccountHandler.getFirstAccount(LicensingService.this);
        if (localAccount != null)
          checkLicense(paramAnonymousLong, paramAnonymousString, paramAnonymousILicenseResultListener, i, localAccount.name);
        else
          LicensingService.notifyListener(paramAnonymousILicenseResultListener, 1, null, null);
      }
    }
  };

  private static void notifyListener(ILicenseResultListener paramILicenseResultListener, int paramInt, String paramString1, String paramString2)
  {
    try
    {
      paramILicenseResultListener.verifyLicense(paramInt, paramString1, paramString2);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        FinskyLog.e("Unable to send license information", new Object[0]);
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.LicensingService
 * JD-Core Version:    0.6.2
 */
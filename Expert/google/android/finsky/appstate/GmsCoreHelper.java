package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PurchaseInitiator;
import java.util.List;

public class GmsCoreHelper
{
  private static final String PACKAGE_NAME = (String)G.gmsCorePackageName.get();
  private final Account mCurrentAccount;
  private final Libraries mLibraries;
  private final String mPackageName = PACKAGE_NAME;

  public GmsCoreHelper(Libraries paramLibraries, Account paramAccount)
  {
    this.mLibraries = paramLibraries;
    this.mCurrentAccount = paramAccount;
  }

  private void installGmsCore(Document paramDocument)
  {
    Installer localInstaller = FinskyApp.get().getInstaller();
    localInstaller.setMobileDataAllowed(this.mPackageName);
    localInstaller.setVisibility(this.mPackageName, false, false);
    Account localAccount = LibraryUtils.getOwnerWithCurrentAccount(paramDocument, this.mLibraries, this.mCurrentAccount);
    if (localAccount == null)
      PurchaseInitiator.makeFreePurchase(this.mCurrentAccount, paramDocument, 1, null, null, null);
    while (true)
    {
      return;
      PurchaseInitiator.initiateDownload(localAccount, paramDocument, null, null, null);
    }
  }

  public static boolean isGmsCore(Document paramDocument)
  {
    DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
    if (localAppDetails == null);
    for (boolean bool = false; ; bool = isGmsCore(localAppDetails.getPackageName()))
      return bool;
  }

  public static boolean isGmsCore(String paramString)
  {
    if (TextUtils.isEmpty(PACKAGE_NAME));
    for (boolean bool = false; ; bool = PACKAGE_NAME.equals(paramString))
      return bool;
  }

  public void insertGmsCore(List<String> paramList)
  {
    if (TextUtils.isEmpty(this.mPackageName));
    while (true)
    {
      return;
      if (!paramList.contains(this.mPackageName))
        paramList.add(this.mPackageName);
    }
  }

  public void updateGmsCore(Document paramDocument)
  {
    if (!LibraryUtils.isAvailable(paramDocument, null, this.mLibraries))
      FinskyLog.d("GMS Core is not available", new Object[0]);
    while (true)
    {
      return;
      DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
      if (localAppDetails == null)
      {
        FinskyLog.wtf("Unable to install something without app details", new Object[0]);
      }
      else
      {
        int i = -1;
        PackageStateRepository.PackageState localPackageState = FinskyApp.get().getPackageInfoRepository().get(this.mPackageName);
        if (localPackageState != null)
        {
          if (localPackageState.isDisabledByUser)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = this.mPackageName;
            FinskyLog.d("Not updating %s (disabled)", arrayOfObject);
          }
          else
          {
            i = localPackageState.installedVersion;
          }
        }
        else if (i < localAppDetails.getVersionCode())
          installGmsCore(paramDocument);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.GmsCoreHelper
 * JD-Core Version:    0.6.2
 */
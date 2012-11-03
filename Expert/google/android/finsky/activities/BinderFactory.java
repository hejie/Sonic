package com.google.android.finsky.activities;

import android.accounts.Account;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;

public class BinderFactory
{
  public static DetailsSummaryViewBinder getSummaryViewBinder(DfeToc paramDfeToc, int paramInt, Account paramAccount)
  {
    Object localObject;
    switch (paramInt)
    {
    case 5:
    default:
      localObject = new DetailsSummaryViewBinder(paramDfeToc, paramAccount);
    case 3:
    case 4:
    case 2:
    case 6:
    }
    while (true)
    {
      return localObject;
      localObject = new DetailsSummaryAppsViewBinder(paramDfeToc, paramAccount, FinskyApp.get().getPackageMonitorReceiver(), FinskyApp.get().getInstaller(), FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
      continue;
      localObject = new DetailsSummaryMoviesViewBinder(paramDfeToc, paramAccount, FinskyApp.get().getLibraries());
      continue;
      localObject = new DetailsSummaryMusicViewBinder(paramDfeToc, paramAccount);
      continue;
      localObject = new DetailsSummaryMagazinesViewBinder(paramDfeToc, paramAccount);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.BinderFactory
 * JD-Core Version:    0.6.2
 */
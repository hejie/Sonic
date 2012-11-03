package com.google.android.finsky.navigationmanager;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Notifier;

public class ConsumptionUtils
{
  private static int getConsumptionAppRequiredString(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    case 5:
    default:
      i = -1;
    case 1:
    case 4:
    case 2:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2131165678;
      continue;
      i = 2131165679;
      continue;
      i = 2131165680;
      continue;
      i = 2131165681;
    }
  }

  private static Intent getConsumptionIntent(Context paramContext, Document paramDocument, Account paramAccount)
  {
    Intent localIntent = null;
    if (paramDocument == null);
    while (true)
    {
      return localIntent;
      int i = paramDocument.getBackend();
      if (paramDocument.getBackendDocId() != null)
      {
        switch (i)
        {
        case 5:
        default:
          throw new IllegalStateException("Cannot open an item from the corpus " + i);
        case 1:
        case 2:
        case 3:
        case 4:
        case 6:
        }
        localIntent = IntentUtils.buildConsumptionAppViewItemIntent(paramContext.getPackageManager(), paramDocument, paramAccount.name);
      }
    }
  }

  public static boolean isConsumptionAppInstalled(Context paramContext, String paramString)
  {
    boolean bool = false;
    PackageStateRepository.PackageState localPackageState = FinskyApp.get().getAppStates().getPackageStateRepository().get(paramString);
    if (localPackageState == null);
    while (true)
    {
      return bool;
      if ((!"com.google.android.videos".equals(paramString)) || (localPackageState.installedVersion >= IntentUtils.getMinimumRequiredVideosAppVersion()))
        bool = true;
    }
  }

  private static boolean isConsumptionAppNeeded(Context paramContext, Document paramDocument, Account paramAccount)
  {
    boolean bool = true;
    String str = IntentUtils.getPackageName(paramDocument.getBackend());
    if (TextUtils.isEmpty(str))
      bool = false;
    while (true)
    {
      return bool;
      if (isConsumptionAppInstalled(paramContext, str))
      {
        Intent localIntent = getConsumptionIntent(paramContext, paramDocument, paramAccount);
        if ((localIntent != null) && (IntentUtils.canResolveIntent(FinskyApp.get().getPackageManager(), localIntent)))
          bool = false;
      }
    }
  }

  public static void openItem(Context paramContext, Fragment paramFragment, FragmentManager paramFragmentManager, Account paramAccount, Document paramDocument)
  {
    if (isConsumptionAppNeeded(paramContext, paramDocument, paramAccount))
      showAppNeededDialog(paramContext, paramFragment, paramFragmentManager, paramDocument.getBackend());
    while (true)
    {
      return;
      Intent localIntent = getConsumptionIntent(paramContext, paramDocument, paramAccount);
      ResolveInfo localResolveInfo = FinskyApp.get().getPackageManager().resolveActivity(localIntent, 0);
      if ((localIntent != null) && (localResolveInfo != null))
      {
        if (paramDocument.getAppDetails() != null)
          FinskyApp.get().getNotifier().hideAllMessagesForPackage(paramDocument.getAppDetails().getPackageName());
        paramContext.startActivity(localIntent);
      }
      else
      {
        Toast.makeText(paramContext, paramContext.getString(2131165491), 0).show();
      }
    }
  }

  public static void showAppNeededDialog(Context paramContext, Fragment paramFragment, FragmentManager paramFragmentManager, int paramInt)
  {
    String str = IntentUtils.getPackageName(paramInt);
    if (str == null)
      Toast.makeText(paramContext, paramContext.getString(2131165491), 0).show();
    while (true)
    {
      return;
      if (paramFragmentManager.findFragmentByTag("app_needed_dialog") == null)
      {
        int i = getConsumptionAppRequiredString(paramInt);
        Bundle localBundle = new Bundle();
        localBundle.putString("dialog_details_url", DfeUtils.createDetailsUrlFromId(str));
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(i, 2131165599, 2131165273);
        localSimpleAlertDialog.setCallback(paramFragment, 1, localBundle);
        localSimpleAlertDialog.show(paramFragmentManager, "app_needed_dialog");
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.navigationmanager.ConsumptionUtils
 * JD-Core Version:    0.6.2
 */
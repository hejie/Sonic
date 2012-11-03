package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiProvider;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.VendingPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UpdateChecker
{
  private final AppStates mAppStates;
  private final Context mContext;
  private final DfeApiProvider mDfeApiProvider;
  private final InstallPolicies mInstallPolicies;
  private final Installer mInstaller;
  private final Libraries mLibraries;
  private final Notifier mNotifier;

  public UpdateChecker(Context paramContext, Libraries paramLibraries, AppStates paramAppStates, DfeApiProvider paramDfeApiProvider, InstallPolicies paramInstallPolicies, Installer paramInstaller, Notifier paramNotifier)
  {
    this.mContext = paramContext;
    this.mLibraries = paramLibraries;
    this.mAppStates = paramAppStates;
    this.mDfeApiProvider = paramDfeApiProvider;
    this.mInstallPolicies = paramInstallPolicies;
    this.mInstaller = paramInstaller;
    this.mNotifier = paramNotifier;
  }

  private void handleUpdates(Context paramContext, List<Document> paramList)
  {
    List localList1 = this.mInstallPolicies.getApplicationsWithUpdates(paramList);
    if ((!((Boolean)VendingPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue()) || (this.mInstallPolicies.isWifiNetwork()))
    {
      List localList2 = this.mInstallPolicies.getApplicationsEligibleForAutoUpdate(localList1, true);
      this.mInstaller.updateInstalledApps(localList2, "auto_update");
      localList1.removeAll(localList2);
    }
    List localList3 = this.mInstallPolicies.getApplicationsEligibleForNotification(localList1);
    showManualUpdateNotifications(localList1, localList3);
    markAppsAsNotified(localList3);
  }

  private void markAppsAsNotified(List<Document> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DocDetails.AppDetails localAppDetails = ((Document)localIterator.next()).getAppDetails();
      String str = localAppDetails.getPackageName();
      InstallerDataStore.InstallerData localInstallerData = this.mAppStates.getInstallerDataStore().get(localAppDetails.getPackageName());
      int i = localAppDetails.getVersionCode();
      if ((localInstallerData == null) || (i > localInstallerData.getLastNotifiedVersion()))
        this.mAppStates.getInstallerDataStore().setLastNotifiedVersion(str, i);
    }
  }

  private void showManualUpdateNotifications(List<Document> paramList1, List<Document> paramList2)
  {
    int i = paramList1.size();
    int j = paramList2.size();
    if ((j > 0) && (((Boolean)VendingPreferences.NOTIFY_UPDATES.get()).booleanValue()))
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(j);
      arrayOfObject[1] = Integer.valueOf(i);
      FinskyLog.d("Notifying user that [%d/%d] applications have updates.", arrayOfObject);
      if (j != 1)
        break label91;
      this.mNotifier.showSingleUpdateAvailableMessage((Document)paramList2.get(0));
    }
    while (true)
    {
      return;
      label91: this.mNotifier.showUpdatesAvailableMessage(paramList2, i);
    }
  }

  public void checkForUpdates(final Runnable paramRunnable1, final Runnable paramRunnable2)
  {
    final Account localAccount = FinskyApp.get().getCurrentAccount();
    if (localAccount == null)
      if (paramRunnable1 != null)
        paramRunnable1.run();
    while (true)
    {
      return;
      if (!this.mAppStates.isLoaded())
      {
        FinskyLog.wtf("Require loaded app states to perform update check.", new Object[0]);
        if (paramRunnable2 != null)
          paramRunnable2.run();
      }
      new AsyncTask()
      {
        protected List<String> doInBackground(Void[] paramAnonymousArrayOfVoid)
        {
          ArrayList localArrayList = Lists.newArrayList();
          Iterator localIterator = UpdateChecker.this.mAppStates.getInstalledAndOwnedBlocking(UpdateChecker.this.mLibraries).iterator();
          while (localIterator.hasNext())
            localArrayList.add(((AppStates.AppState)localIterator.next()).packageName);
          this.val$gmsCoreHelper.insertGmsCore(localArrayList);
          return localArrayList;
        }

        protected void onPostExecute(final List<String> paramAnonymousList)
        {
          UpdateChecker.this.mDfeApiProvider.getDfeApi(localAccount.name).getDetails(paramAnonymousList, new Response.Listener()
          {
            public void onResponse(Details.BulkDetailsResponse paramAnonymous2BulkDetailsResponse)
            {
              ArrayList localArrayList = Lists.newArrayList(paramAnonymous2BulkDetailsResponse.getEntryCount());
              int i = 0;
              if (i < paramAnonymous2BulkDetailsResponse.getEntryCount())
              {
                Details.BulkDetailsEntry localBulkDetailsEntry = paramAnonymous2BulkDetailsResponse.getEntry(i);
                Document localDocument;
                if (localBulkDetailsEntry.hasDoc())
                {
                  localDocument = new Document(localBulkDetailsEntry.getDoc(), null);
                  if (GmsCoreHelper.isGmsCore(localDocument))
                    UpdateChecker.1.this.val$gmsCoreHelper.updateGmsCore(localDocument);
                }
                while (true)
                {
                  i++;
                  break;
                  localArrayList.add(localDocument);
                  continue;
                  Object[] arrayOfObject = new Object[1];
                  arrayOfObject[0] = paramAnonymousList.get(i);
                  FinskyLog.w("No document details for app: pkg=%s", arrayOfObject);
                }
              }
              UpdateChecker.this.handleUpdates(UpdateChecker.this.mContext, localArrayList);
              if (UpdateChecker.1.this.val$successRunnable != null)
                UpdateChecker.1.this.val$successRunnable.run();
            }
          }
          , new Response.ErrorListener()
          {
            public void onErrorResponse(VolleyError paramAnonymous2VolleyError)
            {
              FinskyLog.w("Update check failed: %s", new Object[] { paramAnonymous2VolleyError });
              if (UpdateChecker.1.this.val$errorRunnable != null)
                UpdateChecker.1.this.val$errorRunnable.run();
            }
          });
        }
      }
      .execute(new Void[0]);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.UpdateChecker
 * JD-Core Version:    0.6.2
 */
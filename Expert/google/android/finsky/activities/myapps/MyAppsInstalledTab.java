package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeBulkDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MyAppsInstalledTab extends MyAppsTab<DfeBulkDetails>
  implements MyAppsInstalledAdapter.BucketsChangedListener
{
  private MyAppsInstalledAdapter mAdapter = new MyAppsInstalledAdapter(paramContext, this.mInstaller, FinskyApp.get().getInstallPolicies(), FinskyApp.get().getAppStates(), paramBitmapLoader, this, paramContext.getResources().getBoolean(2131296262), this);
  private ViewGroup mInstalledView;
  private boolean mListInitialized;
  private ListView mMyAppsList;
  private List<String> mOwnedDocIds = Lists.newArrayList();

  public MyAppsInstalledTab(Context paramContext, BitmapLoader paramBitmapLoader, DfeApi paramDfeApi, OpenDetailsHandler paramOpenDetailsHandler, Bundle paramBundle)
  {
    super(paramContext, paramDfeApi, paramOpenDetailsHandler, paramBundle);
  }

  public void bucketsChanged()
  {
    if ((this.mMyAppsList != null) && (this.mInstalledView != null) && (this.mInstalledView.getVisibility() == 0))
      restoreTabSelection();
  }

  protected MyAppsListAdapter getAdapter()
  {
    return this.mAdapter;
  }

  protected Document getDocumentForView(View paramView)
  {
    return MyAppsInstalledAdapter.getViewDoc(paramView);
  }

  protected View getFullView()
  {
    return this.mInstalledView;
  }

  protected ListView getListView()
  {
    return this.mMyAppsList;
  }

  public View getView(int paramInt)
  {
    if (this.mInstalledView == null)
      this.mInstalledView = ((ViewGroup)this.mLayoutInflater.inflate(2130968733, null));
    return this.mInstalledView;
  }

  public void onDataChanged()
  {
    super.onDataChanged();
    List localList = ((DfeBulkDetails)this.mDfeModel).getDocuments();
    if (localList == null);
    while (true)
    {
      return;
      this.mAdapter.addDocs(localList);
      if (!this.mListInitialized)
      {
        this.mInstalledView.findViewById(2131230848).setVisibility(8);
        this.mMyAppsList = ((ListView)this.mInstalledView.findViewById(2131231097));
        this.mMyAppsList.setAdapter(this.mAdapter);
        this.mMyAppsList.setItemsCanFocus(true);
        if (this.mUseTwoColumnLayout)
          this.mMyAppsList.setChoiceMode(1);
        this.mListInitialized = true;
        restoreTabSelection();
      }
      syncViewToState();
    }
  }

  public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    if ((paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.DOWNLOADING) || (paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.INSTALLING))
      this.mAdapter.notifyDataSetChanged();
    while (true)
    {
      return;
      requestData();
    }
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    requestData();
  }

  protected void requestData()
  {
    new AsyncTask()
    {
      protected List<AppStates.AppState> doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        Libraries localLibraries = FinskyApp.get().getLibraries();
        return FinskyApp.get().getAppStates().getOwnedBlocking(localLibraries);
      }

      protected void onPostExecute(List<AppStates.AppState> paramAnonymousList)
      {
        ArrayList localArrayList = Lists.newArrayList();
        Iterator localIterator = paramAnonymousList.iterator();
        while (localIterator.hasNext())
        {
          AppStates.AppState localAppState = (AppStates.AppState)localIterator.next();
          if (!GmsCoreHelper.isGmsCore(localAppState.packageName))
            localArrayList.add(localAppState.packageName);
        }
        Collections.sort(localArrayList);
        if ((MyAppsInstalledTab.this.mDfeModel != null) && (MyAppsInstalledTab.this.mOwnedDocIds.equals(localArrayList)))
          MyAppsInstalledTab.this.onDataChanged();
        while (true)
        {
          return;
          MyAppsInstalledTab.access$002(MyAppsInstalledTab.this, localArrayList);
          MyAppsInstalledTab.this.clearState();
          MyAppsInstalledTab.this.mDfeModel = new DfeBulkDetails(MyAppsInstalledTab.this.mDfeApi, localArrayList);
          ((DfeBulkDetails)MyAppsInstalledTab.this.mDfeModel).addDataChangedListener(MyAppsInstalledTab.this);
          ((DfeBulkDetails)MyAppsInstalledTab.this.mDfeModel).addErrorListener(MyAppsInstalledTab.this);
        }
      }
    }
    .execute(new Void[0]);
  }

  protected void retryFromError()
  {
    clearState();
    requestData();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsInstalledTab
 * JD-Core Version:    0.6.2
 */
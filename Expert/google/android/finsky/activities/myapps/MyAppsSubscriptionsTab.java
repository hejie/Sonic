package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeBulkDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyAppsSubscriptionsTab extends MyAppsTab<DfeBulkDetails>
{
  private MyAppsSubscriptionsAdapter mAdapter = new MyAppsSubscriptionsAdapter(paramContext, this.mLayoutInflater, paramContext.getResources().getBoolean(2131296262), paramBitmapLoader, this);
  private boolean mAdapterInitialized = false;
  Map<String, LibrarySubscriptionEntry> mSubscriptionsInLibrary = Maps.newHashMap();
  private ListView mSubscriptionsListView;
  private ViewGroup mSubscriptionsView;

  public MyAppsSubscriptionsTab(Context paramContext, BitmapLoader paramBitmapLoader, DfeApi paramDfeApi, OpenDetailsHandler paramOpenDetailsHandler, Bundle paramBundle)
  {
    super(paramContext, paramDfeApi, paramOpenDetailsHandler, paramBundle);
  }

  protected MyAppsListAdapter getAdapter()
  {
    return this.mAdapter;
  }

  protected Document getDocumentForView(View paramView)
  {
    return (Document)paramView.getTag();
  }

  protected View getFullView()
  {
    return this.mSubscriptionsView;
  }

  protected ListView getListView()
  {
    return this.mSubscriptionsListView;
  }

  public View getView(int paramInt)
  {
    if (this.mSubscriptionsView == null)
      this.mSubscriptionsView = ((ViewGroup)this.mLayoutInflater.inflate(2130968739, null));
    return this.mSubscriptionsView;
  }

  public void onDataChanged()
  {
    super.onDataChanged();
    List localList = ((DfeBulkDetails)this.mDfeModel).getDocuments();
    HashMap localHashMap1 = Maps.newHashMap();
    HashMap localHashMap2 = Maps.newHashMap();
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      Document localDocument3 = (Document)localIterator1.next();
      switch (localDocument3.getDocumentType())
      {
      default:
        break;
      case 1:
        localHashMap2.put(localDocument3.getDocId(), localDocument3);
        break;
      case 15:
        localHashMap1.put(localDocument3.getDocId(), localDocument3);
      }
    }
    Iterator localIterator2 = this.mSubscriptionsInLibrary.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      String str1 = (String)localEntry.getKey();
      Document localDocument1 = (Document)localHashMap1.get(str1);
      String str2 = DocUtils.getPackageNameForSubscription(str1);
      Document localDocument2 = (Document)localHashMap2.get(str2);
      if (localDocument1 == null)
        FinskyLog.w("Subscription %s is unavailable, ignoring this entry", new Object[] { str1 });
      else if (localDocument2 == null)
        FinskyLog.w("Parent app %s of subscription %s is unavailable, ignoring this entry", new Object[] { str2, str1 });
      else
        this.mAdapter.addEntry((LibrarySubscriptionEntry)localEntry.getValue(), localDocument1, localDocument2);
    }
    this.mAdapter.sortDocs();
    if (!this.mAdapterInitialized)
    {
      this.mSubscriptionsListView = ((ListView)this.mSubscriptionsView.findViewById(2131231097));
      this.mSubscriptionsListView.setAdapter(this.mAdapter);
      this.mSubscriptionsListView.setItemsCanFocus(true);
      if (this.mUseTwoColumnLayout)
        this.mSubscriptionsListView.setChoiceMode(1);
      this.mAdapterInitialized = true;
      restoreTabSelection();
    }
    syncViewToState();
  }

  public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    if ((paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.INSTALLED) || (paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.UNINSTALLED))
      this.mAdapter.notifyDataSetChanged();
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    requestData();
  }

  protected void requestData()
  {
    clearState();
    this.mSubscriptionsInLibrary.clear();
    this.mAdapter.clear();
    Libraries localLibraries = FinskyApp.get().getLibraries();
    HashSet localHashSet = Sets.newHashSet();
    AccountLibrary localAccountLibrary1 = localLibraries.getAccountLibrary(this.mDfeApi.getAccount());
    Iterator localIterator1 = localLibraries.getAccountLibraries().iterator();
    while (localIterator1.hasNext())
    {
      AccountLibrary localAccountLibrary2 = (AccountLibrary)localIterator1.next();
      if (localAccountLibrary2 != localAccountLibrary1)
      {
        Iterator localIterator3 = localAccountLibrary2.getAppSubscriptionsList().iterator();
        while (localIterator3.hasNext())
        {
          LibrarySubscriptionEntry localLibrarySubscriptionEntry2 = (LibrarySubscriptionEntry)localIterator3.next();
          this.mSubscriptionsInLibrary.put(localLibrarySubscriptionEntry2.docId, localLibrarySubscriptionEntry2);
          localHashSet.add(localLibrarySubscriptionEntry2.docId);
          localHashSet.add(DocUtils.getPackageNameForSubscription(localLibrarySubscriptionEntry2.docId));
        }
      }
    }
    Iterator localIterator2 = localAccountLibrary1.getAppSubscriptionsList().iterator();
    while (localIterator2.hasNext())
    {
      LibrarySubscriptionEntry localLibrarySubscriptionEntry1 = (LibrarySubscriptionEntry)localIterator2.next();
      this.mSubscriptionsInLibrary.put(localLibrarySubscriptionEntry1.docId, localLibrarySubscriptionEntry1);
      localHashSet.add(localLibrarySubscriptionEntry1.docId);
      localHashSet.add(DocUtils.getPackageNameForSubscription(localLibrarySubscriptionEntry1.docId));
    }
    ArrayList localArrayList = Lists.newArrayList();
    localArrayList.addAll(localHashSet);
    this.mDfeModel = new DfeBulkDetails(this.mDfeApi, localArrayList);
    ((DfeBulkDetails)this.mDfeModel).addDataChangedListener(this);
    ((DfeBulkDetails)this.mDfeModel).addErrorListener(this);
  }

  protected void retryFromError()
  {
    requestData();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsSubscriptionsTab
 * JD-Core Version:    0.6.2
 */
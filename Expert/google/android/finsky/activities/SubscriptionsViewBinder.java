package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.SubscriptionView.CancelListener;
import com.google.android.finsky.layout.SubscriptionsSection;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.RevokeResponse;
import com.google.android.finsky.utils.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SubscriptionsViewBinder extends DetailsViewBinder
  implements SubscriptionView.CancelListener, Libraries.Listener
{
  private Document mDocument;
  private Fragment mFragment;
  private Libraries mLibraries;
  private final Map<String, Account> mOwningAccountLookup = Maps.newHashMap();
  private Bundle mSavedState;
  private int mSubscriptionItemLayoutId;
  private SubscriptionsSection mSubscriptionsSection;

  private void cancelSubscription(Document paramDocument)
  {
    Account localAccount = (Account)this.mOwningAccountLookup.get(paramDocument.getDocId());
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
    localDfeApi.revoke(paramDocument.getDocId(), 1, new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), localDfeApi.getAccount(), new Response.Listener()
    {
      public void onResponse(RevokeResponse paramAnonymousRevokeResponse)
      {
        Toast.makeText(SubscriptionsViewBinder.this.mContext, 2131165513, 0).show();
      }
    }), new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        SubscriptionsViewBinder.this.rebindViews();
        Toast.makeText(SubscriptionsViewBinder.this.mContext, 2131165514, 0).show();
      }
    });
  }

  private LibrarySubscriptionEntry getLibraryAppSubscriptionEntry(Document paramDocument)
  {
    Collection localCollection = this.mLibraries.getAccountLibraries();
    Account localAccount = this.mDfeApi.getAccount();
    LibrarySubscriptionEntry localLibrarySubscriptionEntry = this.mLibraries.getAccountLibrary(localAccount).getAppSubscriptionEntry(paramDocument.getDocId());
    if (localLibrarySubscriptionEntry == null)
    {
      Iterator localIterator = localCollection.iterator();
      while (localIterator.hasNext())
      {
        AccountLibrary localAccountLibrary = (AccountLibrary)localIterator.next();
        localLibrarySubscriptionEntry = localAccountLibrary.getAppSubscriptionEntry(paramDocument.getDocId());
        if (localLibrarySubscriptionEntry != null)
          localAccount = localAccountLibrary.getAccount();
      }
    }
    if (localLibrarySubscriptionEntry != null)
      this.mOwningAccountLookup.put(paramDocument.getDocId(), localAccount);
    return localLibrarySubscriptionEntry;
  }

  private LibrarySubscriptionEntry getLibraryMagazineSubscriptionEntry(Document paramDocument)
  {
    Account localAccount = this.mDfeApi.getAccount();
    LibrarySubscriptionEntry localLibrarySubscriptionEntry = this.mLibraries.getAccountLibrary(localAccount).getMagazinesSubscriptionEntry(paramDocument.getBackendDocId());
    if (localLibrarySubscriptionEntry != null)
      this.mOwningAccountLookup.put(paramDocument.getDocId(), localAccount);
    return localLibrarySubscriptionEntry;
  }

  private void rebindViews()
  {
    if (this.mSubscriptionsSection == null);
    while (true)
    {
      return;
      this.mSubscriptionsSection.setVisibility(8);
      int i = this.mDocument.getDocumentType();
      if (i == 1)
      {
        if (this.mDocument.hasAppSubscriptions())
        {
          this.mSubscriptionsSection.setVisibility(0);
          this.mSubscriptionsSection.clearSubscriptions();
          Iterator localIterator2 = this.mDocument.getAppSubscriptionsList().iterator();
          while (localIterator2.hasNext())
          {
            Document localDocument2 = (Document)localIterator2.next();
            LibrarySubscriptionEntry localLibrarySubscriptionEntry2 = getLibraryAppSubscriptionEntry(localDocument2);
            if (localLibrarySubscriptionEntry2 != null)
              this.mSubscriptionsSection.addSubscription(localDocument2, localLibrarySubscriptionEntry2, this.mSubscriptionItemLayoutId, this, this.mSavedState);
          }
          this.mSubscriptionsSection.syncSeparatorVisibility();
        }
      }
      else if (((i == 16) || (i == 17)) && (this.mDocument.hasSubscriptions()))
      {
        this.mSubscriptionsSection.setVisibility(0);
        this.mSubscriptionsSection.clearSubscriptions();
        Iterator localIterator1 = this.mDocument.getSubscriptionsList().iterator();
        while (localIterator1.hasNext())
        {
          Document localDocument1 = (Document)localIterator1.next();
          LibrarySubscriptionEntry localLibrarySubscriptionEntry1 = getLibraryMagazineSubscriptionEntry(localDocument1);
          if (localLibrarySubscriptionEntry1 != null)
            this.mSubscriptionsSection.addSubscription(localDocument1, localLibrarySubscriptionEntry1, this.mSubscriptionItemLayoutId, this, this.mSavedState);
        }
        this.mSubscriptionsSection.syncSeparatorVisibility();
      }
    }
  }

  public void bind(Fragment paramFragment, SubscriptionsSection paramSubscriptionsSection, Document paramDocument, int paramInt, Bundle paramBundle)
  {
    this.mFragment = paramFragment;
    this.mSubscriptionsSection = paramSubscriptionsSection;
    this.mDocument = paramDocument;
    this.mSavedState = paramBundle;
    this.mSubscriptionItemLayoutId = paramInt;
    rebindViews();
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, Libraries paramLibraries)
  {
    super.init(paramContext, paramDfeApi, paramNavigationManager);
    this.mLibraries = paramLibraries;
    this.mLibraries.addListener(this);
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onCancel(Document paramDocument, LibrarySubscriptionEntry paramLibrarySubscriptionEntry)
  {
    if (System.currentTimeMillis() < paramLibrarySubscriptionEntry.trialUntilTimestampMs);
    for (int i = 2131165509; ; i = 2131165508)
    {
      Resources localResources = this.mContext.getResources();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramDocument.getTitle();
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(localResources.getString(i, arrayOfObject), 2131165402, 2131165403);
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("subscription_doc", paramDocument);
      localSimpleAlertDialog.setCallback(this.mFragment, 3, localBundle);
      localSimpleAlertDialog.show(this.mFragment.getFragmentManager(), "confirm_cancel_dialog");
      return;
    }
  }

  public void onDestroyView()
  {
    this.mLibraries.removeListener(this);
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    rebindViews();
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    rebindViews();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    cancelSubscription((Document)paramBundle.getParcelable("subscription_doc"));
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    this.mSubscriptionsSection.saveInstanceState(paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SubscriptionsViewBinder
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities.myapps;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.AccountSelectorView;
import com.google.android.finsky.layout.OverviewBucketEntry.OnActionListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.List;

public class MyAppsLibraryTab extends MyAppsTab<DfeList>
  implements View.OnLongClickListener
{
  public static final boolean SUPPORTS_MULTI_CHOICE;
  private final AccountLibrary mAccountLibrary;
  private final MyAppsLibraryAdapter mAdapter;
  private ActionMode mCurrentActionMode;
  private final MyAppsTabbedFragment mFragment;
  private final boolean mIsTwoColumnLayout;
  private ViewGroup mLibraryView;
  private boolean mListInitialized = false;
  private ListView mListView;
  private final NavigationManager mNavigationManager;
  private final DfeToc mToc;

  static
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
    {
      SUPPORTS_MULTI_CHOICE = bool;
      return;
    }
  }

  public MyAppsLibraryTab(Context paramContext, MyAppsTabbedFragment paramMyAppsTabbedFragment, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeApi paramDfeApi, OpenDetailsHandler paramOpenDetailsHandler, DfeToc paramDfeToc, AccountLibrary paramAccountLibrary, Bundle paramBundle)
  {
    super(paramContext, paramDfeApi, paramOpenDetailsHandler, paramBundle);
    this.mFragment = paramMyAppsTabbedFragment;
    this.mToc = paramDfeToc;
    this.mAccountLibrary = paramAccountLibrary;
    this.mNavigationManager = paramNavigationManager;
    this.mIsTwoColumnLayout = paramContext.getResources().getBoolean(2131296262);
    OverviewBucketEntry.OnActionListener local1 = new OverviewBucketEntry.OnActionListener()
    {
      public void onAction(Document paramAnonymousDocument)
      {
        MyAppsLibraryTab.this.mFragment.confirmArchiveDocs(Lists.newArrayList(new Document[] { paramAnonymousDocument }));
      }
    };
    this.mAdapter = new MyAppsLibraryAdapter(this.mContext, paramNavigationManager, paramBitmapLoader, this, local1, this, this.mIsTwoColumnLayout);
    if (!this.mIsTwoColumnLayout)
      this.mAdapter.showAccountSwitcher();
  }

  private void configureNoAppsUi()
  {
    AccountSelectorView localAccountSelectorView = (AccountSelectorView)this.mLibraryView.findViewById(2131230995);
    View localView1;
    final Toc.CorpusMetadata localCorpusMetadata;
    label45: label55: View localView2;
    final String str;
    if (this.mIsTwoColumnLayout)
    {
      localAccountSelectorView.setVisibility(8);
      localView1 = this.mLibraryView.findViewById(2131230997);
      if (this.mToc != null)
        break label114;
      localCorpusMetadata = null;
      if (localCorpusMetadata != null)
        break label126;
      localView1.setVisibility(8);
      localView2 = this.mLibraryView.findViewById(2131230998);
      str = (String)G.gamesBrowseUrl.get();
      if ((this.mToc != null) && (!TextUtils.isEmpty(str)))
        break label142;
      localView2.setVisibility(8);
    }
    while (true)
    {
      return;
      localAccountSelectorView.configure((AuthenticatedActivity)this.mContext);
      break;
      label114: localCorpusMetadata = this.mToc.getCorpus(3);
      break label45;
      label126: localView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyAppsLibraryTab.this.mNavigationManager.goToCorpusHome(localCorpusMetadata.getLandingUrl(), localCorpusMetadata.getName(), 3, MyAppsLibraryTab.this.mToc, "myApps");
        }
      });
      break label55;
      label142: localView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyAppsLibraryTab.this.mNavigationManager.goToCorpusHome(str, MyAppsLibraryTab.this.mContext.getResources().getString(2131165683), 3, MyAppsLibraryTab.this.mToc, "myApps");
        }
      });
    }
  }

  private DfeList getLibraryList()
  {
    byte[] arrayOfByte = this.mAccountLibrary.getServerToken(AccountLibrary.LIBRARY_ID_APPS);
    String str = this.mDfeApi.getLibraryUrl(3, AccountLibrary.LIBRARY_ID_APPS, 1, arrayOfByte);
    DfeList localDfeList2;
    if ((this.mSavedInstanceState != null) && (this.mSavedInstanceState.containsKey("MyAppsLibraryTab.ListData")))
    {
      localDfeList2 = (DfeList)this.mSavedInstanceState.getParcelable("MyAppsLibraryTab.ListData");
      if (str.equals(localDfeList2.getInitialUrl()))
        localDfeList2.setDfeApi(this.mDfeApi);
    }
    DfeList localDfeList1;
    for (Object localObject = localDfeList2; ; localObject = localDfeList1)
    {
      return localObject;
      localDfeList1 = new DfeList(this.mDfeApi, str, true);
      localDfeList1.filterDocId((String)G.gmsCorePackageName.get());
    }
  }

  protected MyAppsListAdapter getAdapter()
  {
    return this.mAdapter;
  }

  protected Document getDocumentForView(View paramView)
  {
    return MyAppsLibraryAdapter.getViewDoc(paramView);
  }

  protected View getFullView()
  {
    return this.mLibraryView;
  }

  protected ListView getListView()
  {
    return this.mListView;
  }

  public View getView(int paramInt)
  {
    if (this.mLibraryView == null)
      this.mLibraryView = ((ViewGroup)this.mLayoutInflater.inflate(2130968734, null));
    return this.mLibraryView;
  }

  public void onDataChanged()
  {
    super.onDataChanged();
    if (!this.mListInitialized)
    {
      this.mListInitialized = true;
      this.mListView = ((ListView)this.mLibraryView.findViewById(2131231097));
      this.mListView.setAdapter(this.mAdapter);
      this.mListView.setItemsCanFocus(true);
      if (SUPPORTS_MULTI_CHOICE)
        this.mListView.setMultiChoiceModeListener(new MultiChoiceListener(this, null));
      if (this.mUseTwoColumnLayout)
        this.mListView.setChoiceMode(1);
      configureNoAppsUi();
      this.mListView.setEmptyView(this.mLibraryView.findViewById(2131230994));
      restoreTabSelection();
    }
    syncViewToState();
    this.mAdapter.onDataChanged();
  }

  public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    if ((paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.INSTALLED) || (paramInstallerPackageEvent == InstallerListener.InstallerPackageEvent.UNINSTALLED))
      this.mAdapter.notifyDataSetChanged();
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    if (paramAccountLibrary.getAccount().name.equals(FinskyApp.get().getCurrentAccountName()))
      requestData();
  }

  public boolean onLongClick(View paramView)
  {
    boolean bool = true;
    if (SUPPORTS_MULTI_CHOICE)
    {
      this.mListView.setChoiceMode(3);
      int i = getPositionForView(paramView);
      if (i != -1)
        this.mListView.setItemChecked(i, bool);
    }
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    if ((this.mDfeModel != null) && (((DfeList)this.mDfeModel).isReady()))
      this.mSavedInstanceState.putParcelable("MyAppsLibraryTab.ListData", (Parcelable)this.mDfeModel);
    return localBundle;
  }

  protected void requestData()
  {
    clearState();
    this.mDfeModel = getLibraryList();
    ((DfeList)this.mDfeModel).addDataChangedListener(this);
    ((DfeList)this.mDfeModel).addErrorListener(this);
    ((DfeList)this.mDfeModel).startLoadItems();
    this.mAdapter.setDfeList((DfeList)this.mDfeModel);
  }

  protected void retryFromError()
  {
    ((DfeList)this.mDfeModel).resetItems();
    ((DfeList)this.mDfeModel).clearTransientState();
    ((DfeList)this.mDfeModel).startLoadItems();
  }

  public void setTabSelected(boolean paramBoolean)
  {
    super.setTabSelected(paramBoolean);
    if ((!paramBoolean) && (this.mCurrentActionMode != null))
      this.mCurrentActionMode.finish();
  }

  private static class MultiChoiceListener
    implements AbsListView.MultiChoiceModeListener
  {
    private final MyAppsLibraryTab mTab;

    private MultiChoiceListener(MyAppsLibraryTab paramMyAppsLibraryTab)
    {
      this.mTab = paramMyAppsLibraryTab;
    }

    private List<Document> getCheckedDocuments()
    {
      SparseBooleanArray localSparseBooleanArray = this.mTab.getListView().getCheckedItemPositions();
      ArrayList localArrayList = Lists.newArrayList(localSparseBooleanArray.size());
      for (int i = 0; i < localSparseBooleanArray.size(); i++)
      {
        int j = localSparseBooleanArray.keyAt(i);
        if (localSparseBooleanArray.get(j))
          localArrayList.add(this.mTab.getAdapter().getDocument(j));
      }
      return localArrayList;
    }

    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      if (this.mTab.mFragment.handleMenuItem(getCheckedDocuments(), paramMenuItem))
        paramActionMode.finish();
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      paramActionMode.getMenuInflater().inflate(2131689476, paramMenu);
      MyAppsLibraryTab.access$402(this.mTab, paramActionMode);
      return true;
    }

    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      MyAppsLibraryTab.access$402(this.mTab, null);
      this.mTab.getListView().post(new Runnable()
      {
        public void run()
        {
          MyAppsLibraryTab.this.getListView().setChoiceMode(1);
        }
      });
    }

    public void onItemCheckedStateChanged(ActionMode paramActionMode, int paramInt, long paramLong, boolean paramBoolean)
    {
      this.mTab.mFragment.adjustMenu(getCheckedDocuments(), paramActionMode.getMenu());
    }

    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return false;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsLibraryTab
 * JD-Core Version:    0.6.2
 */
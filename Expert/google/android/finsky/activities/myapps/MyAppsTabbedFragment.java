package com.google.android.finsky.activities.myapps;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.activities.ViewPagerTab;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.billing.ProgressDialogFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.AccountSelectorView;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBar.TabListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.ModifyLibrary.ModifyLibraryResponse;
import com.google.android.finsky.utils.AppSupport;
import com.google.android.finsky.utils.AppSupport.RefundListener;
import com.google.android.finsky.utils.AppSupport.RefundResult;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Notifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAppsTabbedFragment extends PageFragment
  implements ViewPager.OnPageChangeListener, SimpleAlertDialog.Listener, OpenDetailsHandler, AppSupport.RefundListener
{
  private static int sLastSelectedTabType = 1;
  private AccountSelectorView mAccountSelector;
  private ProgressDialogFragment mArchiveProgressDialog;
  private String mBreadcrumb;
  private CustomActionBar mCustomActionBar;
  private DocumentView mDocView;
  InstallPolicies mInstallPolicies;
  Installer mInstaller;
  private ViewGroup mListContainer;
  boolean mMobileDataState;
  IntentFilter mNetworkStateChangedFilter;
  BroadcastReceiver mNetworkStateIntentReceiver;
  private Bundle mSavedInstanceState = new Bundle();
  private final CustomActionBar.TabListener mTabListener = new CustomActionBar.TabListener()
  {
    public void onTabReselected(int paramAnonymousInt)
    {
    }

    public void onTabSelected(int paramAnonymousInt)
    {
      MyAppsTabbedFragment.this.switchSelectedTab(paramAnonymousInt);
    }

    public void onTabUnselected(int paramAnonymousInt)
    {
    }
  };
  private MyAppsTabbedAdapter mTabbedAdapter;
  private boolean mUseActionBarTabs;
  private boolean mUseTwoColumnLayout;
  private ViewPager mViewPager;

  private void archiveDocs(List<String> paramList)
  {
    this.mArchiveProgressDialog = ProgressDialogFragment.newInstance(2131165844);
    this.mArchiveProgressDialog.show(getFragmentManager(), "archive_progress_dialog");
    this.mDfeApi.archiveFromLibrary(paramList, AccountLibrary.LIBRARY_ID_APPS, new Response.Listener()
    {
      public void onResponse(ModifyLibrary.ModifyLibraryResponse paramAnonymousModifyLibraryResponse)
      {
        if (paramAnonymousModifyLibraryResponse.hasLibraryUpdate())
          FinskyApp.get().getLibraryReplicators().applyLibraryUpdate(MyAppsTabbedFragment.this.mDfeApi.getAccount(), paramAnonymousModifyLibraryResponse.getLibraryUpdate(), "myapps-archive");
        if (MyAppsTabbedFragment.this.canChangeFragmentManagerState())
          MyAppsTabbedFragment.this.dismissArchiveProgressDialog();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        if (MyAppsTabbedFragment.this.canChangeFragmentManagerState())
        {
          MyAppsTabbedFragment.this.dismissArchiveProgressDialog();
          ErrorDialog.show(MyAppsTabbedFragment.this.getFragmentManager(), null, ErrorStrings.get(MyAppsTabbedFragment.this.getActivity(), paramAnonymousVolleyError), false);
        }
      }
    });
  }

  private void clearState()
  {
    if (this.mDataView != null)
    {
      View localView = this.mDataView.findViewById(2131231102);
      if (localView != null)
        localView.setVisibility(4);
    }
    if (this.mViewPager != null)
    {
      this.mViewPager.setOnPageChangeListener(null);
      this.mViewPager.setAdapter(null);
      this.mViewPager = null;
    }
    this.mCustomActionBar.clearTabs();
  }

  private void configureViewPager(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mViewPager = ((ViewPager)this.mDataView.findViewById(2131231101));
    if (this.mViewPager == null)
      return;
    this.mViewPager.setAdapter(this.mTabbedAdapter);
    this.mViewPager.setPageMargin(getResources().getDimensionPixelSize(2131427380));
    this.mViewPager.setPageMarginDrawable(2131361797);
    PagerTabStrip localPagerTabStrip = (PagerTabStrip)this.mDataView.findViewById(2131231102);
    localPagerTabStrip.setVisibility(8);
    if (paramBoolean1)
    {
      localPagerTabStrip.setVisibility(0);
      int i = CorpusResourceUtils.getBackendForegroundColor(this.mContext, 3);
      localPagerTabStrip.setTextColor(i);
      localPagerTabStrip.setTabIndicatorColor(i);
      localPagerTabStrip.setDrawFullUnderline(true);
      localPagerTabStrip.setTextSpacing((int)(80.0F * getResources().getDisplayMetrics().density));
      localPagerTabStrip.setNonPrimaryAlpha(0.7F);
    }
    while (true)
    {
      this.mViewPager.setOnPageChangeListener(this);
      break;
      localPagerTabStrip.setVisibility(8);
    }
  }

  private void dismissArchiveProgressDialog()
  {
    if (this.mArchiveProgressDialog != null)
    {
      this.mArchiveProgressDialog.dismiss();
      this.mArchiveProgressDialog = null;
    }
  }

  public static MyAppsTabbedFragment newInstance(DfeToc paramDfeToc)
  {
    MyAppsTabbedFragment localMyAppsTabbedFragment = new MyAppsTabbedFragment();
    localMyAppsTabbedFragment.setDfeToc(paramDfeToc);
    return localMyAppsTabbedFragment;
  }

  private void recordState()
  {
    if (isDataReady())
    {
      if (this.mTabbedAdapter != null)
        this.mTabbedAdapter.onSaveInstanceState(this.mSavedInstanceState);
      if (this.mViewPager != null)
      {
        sLastSelectedTabType = this.mTabbedAdapter.getTabType(this.mViewPager.getCurrentItem());
        this.mSavedInstanceState.putInt("MyAppsTabbedAdapter.CurrentTabType", sLastSelectedTabType);
      }
    }
    else
    {
      return;
    }
    int i;
    if (this.mListContainer != null)
      i = -1;
    for (int j = 0; ; j++)
      if (j < this.mListContainer.getChildCount())
      {
        if (this.mListContainer.getChildAt(j).getVisibility() == 0)
          i = j;
      }
      else
      {
        if (i < 0)
          break;
        sLastSelectedTabType = this.mTabbedAdapter.getTabType(i);
        this.mSavedInstanceState.putInt("MyAppsTabbedAdapter.CurrentTabType", sLastSelectedTabType);
        break;
        break;
      }
  }

  private void refundDocument(String paramString1, String paramString2)
  {
    FragmentManager localFragmentManager = getFragmentManager();
    if (localFragmentManager.findFragmentByTag("refund_confirm") != null);
    while (true)
    {
      return;
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165607, 2131165402, 2131165403);
      Bundle localBundle = new Bundle();
      localBundle.putString("package_name", paramString1);
      localBundle.putString("account_name", paramString2);
      localSimpleAlertDialog.setCallback(this, 4, localBundle);
      localSimpleAlertDialog.show(localFragmentManager, "refund_confirm");
    }
  }

  private void showAccountSelectorIfNecessary(int paramInt)
  {
    AccountSelectorView localAccountSelectorView;
    if (this.mAccountSelector != null)
    {
      int i = this.mTabbedAdapter.getTabType(paramInt);
      localAccountSelectorView = this.mAccountSelector;
      if (i != 2)
        break label36;
    }
    label36: for (int j = 0; ; j = 8)
    {
      localAccountSelectorView.setVisibility(j);
      return;
    }
  }

  private void switchSelectedTab(int paramInt)
  {
    if (this.mListContainer != null)
    {
      if (this.mDocView != null)
        this.mDocView.setVisibility(4);
      this.mCustomActionBar.setSelectedTab(paramInt);
      int i = 0;
      if (i < this.mListContainer.getChildCount())
      {
        boolean bool;
        if (paramInt == i)
        {
          bool = true;
          label52: if (!bool)
            break label134;
        }
        label134: for (int j = 0; ; j = 8)
        {
          View localView = this.mListContainer.getChildAt(i);
          localView.setVisibility(j);
          ViewPagerTab localViewPagerTab = (ViewPagerTab)localView.getTag();
          if (localViewPagerTab == null)
          {
            localViewPagerTab = (ViewPagerTab)this.mTabbedAdapter.instantiateItem(this.mViewPager, i);
            localView.setTag(localViewPagerTab);
          }
          localViewPagerTab.setTabSelected(bool);
          i++;
          break;
          bool = false;
          break label52;
        }
      }
    }
    showAccountSelectorIfNecessary(paramInt);
  }

  public void adjustMenu(List<Document> paramList, Menu paramMenu)
  {
    boolean bool = true;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      if (!AppActionAnalyzer.canRemoveFromLibrary((Document)localIterator.next()))
        bool = false;
    MenuItem localMenuItem = paramMenu.findItem(2131231294);
    if (localMenuItem != null)
      localMenuItem.setVisible(bool);
  }

  public void confirmArchiveDocs(List<Document> paramList)
  {
    Object[] arrayOfObject;
    if (paramList.size() == 1)
    {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = ((Document)paramList.get(0)).getTitle();
    }
    SimpleAlertDialog localSimpleAlertDialog;
    ArrayList localArrayList;
    for (String str = getString(2131165845, arrayOfObject); ; str = getString(2131165846))
    {
      localSimpleAlertDialog = SimpleAlertDialog.newInstance(str, 2131165599, 2131165273);
      localArrayList = Lists.newArrayList(paramList.size());
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((Document)localIterator.next()).getDocId());
    }
    Bundle localBundle = new Bundle();
    localBundle.putStringArrayList("docid_list", localArrayList);
    localSimpleAlertDialog.setCallback(this, 6, localBundle);
    localSimpleAlertDialog.show(getFragmentManager(), "archive_confirm");
  }

  protected int getLayoutRes()
  {
    return 2130968737;
  }

  boolean handleMenuItem(List<Document> paramList, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 2131231294:
    }
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      confirmArchiveDocs(paramList);
    }
  }

  protected boolean isDataReady()
  {
    return true;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    String str = CorpusResourceUtils.getCorpusMyCollectionDescription(3);
    if (TextUtils.isEmpty(str))
      str = this.mContext.getString(2131165558);
    this.mBreadcrumb = str;
    this.mCustomActionBar = ((MainActivity)getActivity()).getCustomActionBar();
    Resources localResources = getActivity().getResources();
    this.mUseActionBarTabs = localResources.getBoolean(2131296263);
    this.mUseTwoColumnLayout = localResources.getBoolean(2131296262);
    if ((paramBundle != null) && (this.mSavedInstanceState.isEmpty()))
      this.mSavedInstanceState = paramBundle;
    this.mInstaller = FinskyApp.get().getInstaller();
    this.mInstallPolicies = FinskyApp.get().getInstallPolicies();
    this.mMobileDataState = this.mInstallPolicies.isMobileNetwork();
    this.mNetworkStateChangedFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    this.mNetworkStateIntentReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramAnonymousIntent.getAction()))
        {
          boolean bool = MyAppsTabbedFragment.this.mInstallPolicies.isMobileNetwork();
          if (bool != MyAppsTabbedFragment.this.mMobileDataState)
          {
            if (MyAppsTabbedFragment.this.mTabbedAdapter != null)
              MyAppsTabbedFragment.this.mTabbedAdapter.refreshAllTabs();
            MyAppsTabbedFragment.this.mMobileDataState = bool;
          }
        }
      }
    };
    FinskyApp.get().getNotifier().hideAllMessagesForAccount(FinskyApp.get().getCurrentAccountName());
    if (!isDataReady())
    {
      switchToLoading();
      requestData();
      rebindActionBar();
    }
    while (true)
    {
      return;
      rebindViews();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
  }

  public void onDestroyView()
  {
    recordState();
    clearState();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      this.mDocView.onNegativeClick(paramInt, paramBundle);
      continue;
      refresh();
    }
  }

  public void onPageScrollStateChanged(int paramInt)
  {
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
  }

  public void onPageSelected(int paramInt)
  {
    if (this.mUseActionBarTabs)
      switchSelectedTab(paramInt);
    while (true)
    {
      return;
      this.mTabbedAdapter.onPageSelected(paramInt);
    }
  }

  public void onPause()
  {
    super.onPause();
    getActivity().unregisterReceiver(this.mNetworkStateIntentReceiver);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    case 2:
    case 5:
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Unexpected requestCode %d", arrayOfObject);
    case 3:
    case 1:
    case 4:
    case 6:
    }
    while (true)
    {
      return;
      this.mDocView.onPositiveClick(paramInt, paramBundle);
      continue;
      String str = paramBundle.getString("package_name");
      this.mInstaller.uninstallAssetSilently(str);
      continue;
      AppSupport.silentRefund(paramBundle.getString("package_name"), paramBundle.getString("account_name"), this);
      continue;
      archiveDocs(paramBundle.getStringArrayList("docid_list"));
    }
  }

  public void onRefundComplete(AppSupport.RefundResult paramRefundResult, String paramString)
  {
    switch (6.$SwitchMap$com$google$android$finsky$utils$AppSupport$RefundResult[paramRefundResult.ordinal()])
    {
    case 2:
    default:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      return;
      this.mInstaller.uninstallAssetSilently(paramString);
      continue;
      AppSupport.showRefundFailureDialog(getFragmentManager());
      refresh();
    }
  }

  public void onRefundStart()
  {
  }

  public void onResume()
  {
    super.onResume();
    getActivity().registerReceiver(this.mNetworkStateIntentReceiver, this.mNetworkStateChangedFilter);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    recordState();
    paramBundle.putAll(this.mSavedInstanceState);
    super.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    super.onStart();
    this.mArchiveProgressDialog = ((ProgressDialogFragment)getFragmentManager().findFragmentByTag("archive_progress_dialog"));
    dismissArchiveProgressDialog();
  }

  public void openDocDetails(final Document paramDocument)
  {
    if (this.mListContainer == null)
      this.mNavigationManager.goToDocPage(paramDocument, null, "myApps", null, null);
    while (true)
    {
      return;
      if (this.mDocView != null)
      {
        if (this.mDocView.getVisibility() != 0)
          this.mDocView.setVisibility(0);
        this.mDocView.bind(this, paramDocument, new DocumentView.DocumentActionHandler()
        {
          private void purchase()
          {
            FinskyApp localFinskyApp = FinskyApp.get();
            Account localAccount = AppActionAnalyzer.getInstallAccount(paramDocument.getDocId(), localFinskyApp.getCurrentAccount(), localFinskyApp.getAppStates(), localFinskyApp.getLibraries());
            MyAppsTabbedFragment.this.mNavigationManager.goToPurchase(localAccount, paramDocument.getDetailsUrl(), 1, "myApps", null, null, null, null, false);
          }

          public void enable()
          {
            String str = paramDocument.getDocId();
            MyAppsTabbedFragment.this.mContext.getPackageManager().setApplicationEnabledSetting(str, 1, 0);
            FinskyApp.get().getPackageInfoRepository().invalidate(str);
            MyAppsTabbedFragment.this.mDocView.onDataChanged();
          }

          public void install()
          {
            Intent localIntent = FinskyApp.get().getPackageManager().getLaunchIntentForPackage(paramDocument.getDocId());
            if (localIntent != null)
              MyAppsTabbedFragment.this.startActivity(localIntent);
            while (true)
            {
              return;
              purchase();
            }
          }

          public void launch()
          {
            MyAppsTabbedFragment.this.mNavigationManager.openItem(FinskyApp.get().getCurrentAccount(), paramDocument);
          }

          public void refund(String paramAnonymousString)
          {
            MyAppsTabbedFragment.this.refundDocument(paramDocument.getDocId(), paramAnonymousString);
          }

          public void uninstall(boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, boolean paramAnonymousBoolean3)
          {
            AppSupport.showUninstallConfirmationDialog(paramDocument.getDocId(), MyAppsTabbedFragment.this, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousBoolean3);
          }

          public void update()
          {
            purchase();
          }

          public void viewDetails()
          {
            MyAppsTabbedFragment.this.mNavigationManager.goToDocPage(paramDocument, null, "myApps", null, null);
          }
        }
        , new Bundle(), this.mNavigationManager);
      }
    }
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
    this.mPageFragmentHost.updateCurrentBackendId(3);
  }

  public void rebindViews()
  {
    switchToData();
    rebindActionBar();
    if ((this.mViewPager != null) && (this.mTabbedAdapter != null))
      return;
    int i = CorpusResourceUtils.getBackendHintColor(this.mContext, 3);
    int j = CorpusResourceUtils.getBackendDarkColor(this.mContext, 3);
    View localView1 = getView();
    View localView2 = localView1.findViewById(2131230884);
    if (localView2 != null)
    {
      localView2.setBackgroundColor(i);
      View localView3 = localView2.findViewById(2131230885);
      BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837714);
      localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      localView3.setBackgroundDrawable(localBitmapDrawable);
      localView1.findViewById(2131230883).setBackgroundColor(j);
    }
    boolean bool1;
    int k;
    label212: int m;
    int n;
    label222: int i2;
    label299: ViewPagerTab localViewPagerTab;
    int i4;
    if (!this.mUseActionBarTabs)
    {
      bool1 = true;
      boolean bool2 = FinskyApp.get().getLibraries().hasSubscriptions();
      this.mTabbedAdapter = new MyAppsTabbedAdapter(this.mContext, this.mNavigationManager, this.mDfeApi, getToc(), this.mBitmapLoader, bool2, this.mSavedInstanceState, this);
      configureViewPager(bool1, bool2);
      if (!this.mSavedInstanceState.containsKey("MyAppsTabbedAdapter.CurrentTabType"))
        break label424;
      k = this.mSavedInstanceState.getInt("MyAppsTabbedAdapter.CurrentTabType");
      sLastSelectedTabType = 1;
      m = 0;
      n = 0;
      int i1 = this.mTabbedAdapter.getCount();
      if (n < i1)
      {
        if (this.mTabbedAdapter.getTabType(n) != k)
          break label432;
        m = n;
      }
      if (!this.mUseActionBarTabs)
        break label526;
      this.mListContainer = ((ViewGroup)this.mDataView.findViewById(2131231108));
      this.mCustomActionBar.clearTabs();
      this.mListContainer.removeAllViews();
      i2 = 0;
      int i3 = this.mTabbedAdapter.getCount();
      if (i2 >= i3)
        break label451;
      this.mCustomActionBar.addTab(this.mTabbedAdapter.getPageTitle(i2), this.mTabListener);
      localViewPagerTab = (ViewPagerTab)this.mTabbedAdapter.instantiateItem(this.mListContainer, i2);
      if (i2 != m)
        break label438;
      i4 = 0;
      label365: this.mListContainer.getChildAt(i2).setVisibility(i4);
      this.mListContainer.getChildAt(i2).setTag(localViewPagerTab);
      if (i2 != m)
        break label445;
    }
    label424: label432: label438: label445: for (boolean bool3 = true; ; bool3 = false)
    {
      localViewPagerTab.setTabSelected(bool3);
      i2++;
      break label299;
      bool1 = false;
      break;
      k = sLastSelectedTabType;
      break label212;
      n++;
      break label222;
      i4 = 8;
      break label365;
    }
    label451: this.mCustomActionBar.setSelectedTab(m);
    while (true)
    {
      if (this.mUseTwoColumnLayout)
      {
        this.mAccountSelector = ((AccountSelectorView)this.mDataView.findViewById(2131230995));
        this.mAccountSelector.configure((AuthenticatedActivity)this.mContext);
        showAccountSelectorIfNecessary(m);
      }
      this.mDocView = ((DocumentView)this.mDataView.findViewById(2131231109));
      break;
      label526: this.mListContainer = null;
      onPageScrolled(m, 0.0F, 0);
      this.mViewPager.setCurrentItem(m, false);
    }
  }

  public void refresh()
  {
    if (isDataReady())
      rebindViews();
    while (true)
    {
      return;
      super.refresh();
    }
  }

  protected void requestData()
  {
    clearState();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsTabbedFragment
 * JD-Core Version:    0.6.2
 */
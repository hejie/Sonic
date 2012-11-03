package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.CheckPromoOffersAction;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.debug.DcbDebugActivity;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.BgDataDisabledError;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.NotificationListener;
import com.google.android.finsky.utils.Notifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AuthenticatedActivity
  implements SimpleAlertDialog.Listener, PageFragmentHost
{
  private static boolean sBillingInitialized;
  private CustomActionBar mActionBar;
  private final Handler mHandler = new Handler();
  private int mLastShownErrorHash;
  private MenuItem mMyCollectionItem;
  private MenuItem mMyWishlistItem;
  private NavigationManager mNavigationManager;
  private final NotificationListener mNotificationListener = new NotificationListener()
  {
    public boolean showAppAlert(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, int paramAnonymousInt)
    {
      Document localDocument = MainActivity.this.mNavigationManager.getCurrentDocument();
      if ((localDocument != null) && (localDocument.getBackend() == 3) && (localDocument.getAppDetails().getPackageName().equals(paramAnonymousString1)));
      for (boolean bool = MainActivity.this.showErrorDialogForCode(paramAnonymousString2, paramAnonymousString3, paramAnonymousInt, paramAnonymousString1); ; bool = false)
        return bool;
    }

    public boolean showAppNotification(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
    {
      Document localDocument = MainActivity.this.mNavigationManager.getCurrentDocument();
      if ((localDocument != null) && (localDocument.getBackend() == 3) && (localDocument.getAppDetails().getPackageName().equals(paramAnonymousString1)));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    public boolean showDocAlert(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4)
    {
      boolean bool = false;
      if ((MainActivity.this.mNavigationManager.getCurrentDocument() != null) && ((MainActivity.this.mNavigationManager.getCurrentDocument().getDocId().equals(paramAnonymousString1)) || (DfeUtils.isSameDocumentDetailsUrl(paramAnonymousString4, MainActivity.this.mNavigationManager.getCurrentDocument().getDetailsUrl()))))
      {
        MainActivity.this.showErrorDialog(paramAnonymousString2, paramAnonymousString3, false);
        bool = true;
      }
      return bool;
    }
  };
  private boolean mPageNeedsRefresh = false;
  private Bundle mSavedInstanceState;
  private int mSequenceNumberToDrainFrom = -1;
  private final Runnable mStopPreviewsRunnable = new Runnable()
  {
    public void run()
    {
      PreviewController.reset();
    }
  };

  private String buildAnalyticsUrl(String paramString, Intent paramIntent)
  {
    return new Uri.Builder().path(paramString).appendQueryParameter("url", paramIntent.getDataString()).appendQueryParameter("action", paramIntent.getAction()).build().toString();
  }

  private void checkHasPromoOffers(Runnable paramRunnable)
  {
    new CheckPromoOffersAction(this, FinskyApp.get().getDfeApi()).run(paramRunnable);
  }

  private int getCurrentBackend()
  {
    return this.mActionBar.getCurrentBackendId();
  }

  public static Intent getMyDownloadsIntent(Context paramContext)
  {
    return new Intent("com.google.android.finsky.VIEW_MY_DOWNLOADS").setClass(paramContext, MainActivity.class);
  }

  private void handleIntent()
  {
    this.mNavigationManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
    {
      public void onBackStackChanged()
      {
        MainActivity.this.findViewById(2131230776).setVisibility(8);
        MainActivity.this.mNavigationManager.removeOnBackStackChangedListener(this);
      }
    });
    Intent localIntent = getIntent();
    String str1 = localIntent.getStringExtra("authAccount");
    if (str1 != null)
    {
      localIntent.removeExtra("authAccount");
      setIntent(localIntent);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = FinskyLog.scrubPii(str1);
      FinskyLog.d("b/5160617: Switching account to %s due to intent", arrayOfObject);
      switchAccount(str1);
    }
    while (true)
    {
      return;
      maybeShowErrorDialog(localIntent);
      String str2 = localIntent.getAction();
      if ("android.intent.action.SEARCH".equals(str2))
      {
        handleSearchIntent(localIntent);
      }
      else if ("com.google.android.finsky.NAVIGATIONAL_SUGGESTION".equals(str2))
      {
        handleViewIntent(localIntent);
      }
      else if (("android.intent.action.VIEW".equals(str2)) || ("android.nfc.action.NDEF_DISCOVERED".equals(str2)))
      {
        this.mNavigationManager.clear();
        handleViewIntent(localIntent);
      }
      else if ("com.google.android.finsky.DETAILS".equals(str2))
      {
        this.mNavigationManager.goToDocPage(localIntent.getDataString(), localIntent.getStringExtra("referrer_cookie"), localIntent.getStringExtra("referrer_url"), null, null);
      }
      else if ("com.google.android.finsky.VIEW_MY_DOWNLOADS".equals(str2))
      {
        this.mNavigationManager.clear();
        this.mNavigationManager.goToMyDownloads(FinskyApp.get().getToc());
      }
      else if ("com.google.android.finsky.CORPUS_HOME".equals(str2))
      {
        int j = localIntent.getIntExtra("backend_id", 0);
        String str6 = localIntent.getStringExtra("title");
        String str7 = localIntent.getStringExtra("referrer_url");
        if (j == 0)
          this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc(), str7);
        else
          this.mNavigationManager.goToCorpusHome(localIntent.getDataString(), str6, j, FinskyApp.get().getToc(), str7);
      }
      else if ("com.google.android.finsky.VIEW_BROWSE".equals(str2))
      {
        int i = localIntent.getIntExtra("backend_id", 0);
        String str3 = localIntent.getStringExtra("title");
        String str4 = localIntent.getStringExtra("referrer_url");
        String str5 = localIntent.getDataString();
        if (localIntent.getBooleanExtra("clear_back_stack", false))
          this.mNavigationManager.clear();
        this.mNavigationManager.goBrowse(str5, str3, i, str4, FinskyApp.get().getToc());
      }
      else
      {
        this.mNavigationManager.clear();
        this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc());
        if (sSwitchToMyApps)
        {
          this.mNavigationManager.goToMyDownloads(FinskyApp.get().getToc());
          sSwitchToMyApps = false;
        }
      }
    }
  }

  private void handleSearchIntent(Intent paramIntent)
  {
    if (!isTosAccepted());
    while (true)
    {
      return;
      String str = paramIntent.getStringExtra("query");
      FinskyApp.get().getRecentSuggestions().saveRecentQuery(str, null);
      this.mNavigationManager.goToSearch(str, getCurrentBackend(), null);
    }
  }

  private void handleViewIntent(Intent paramIntent)
  {
    String str = buildAnalyticsUrl("deepLink", paramIntent);
    FinskyApp.get().getAnalytics().logPageView(null, null, str);
    if (getIntent().getBooleanExtra("dont_resolve_again", false))
      this.mNavigationManager.goToAggregatedHome(FinskyApp.get().getToc());
    while (true)
    {
      return;
      this.mNavigationManager.handleDeepLink(paramIntent.getDataString());
    }
  }

  private void initializeBilling()
  {
    if (sBillingInitialized);
    while (true)
    {
      return;
      sBillingInitialized = true;
      FinskyLog.d("Optimistically initializing billing parameters.", new Object[0]);
      CarrierBillingUtils.initializeCarrierBillingStorage(new Runnable()
      {
        public void run()
        {
          new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
          {
            public void run()
            {
              CarrierBillingUtils.initializeCarrierBillingParams(MainActivity.this, new Runnable()
              {
                public void run()
                {
                  MainActivity.this.initializeBillingCountries();
                }
              });
            }
          }
          , ((Integer)G.initializeBillingDelayMs.get()).intValue());
        }
      });
    }
  }

  private void initializeBillingCountries()
  {
    new GetBillingCountriesAction().run(FinskyApp.get().getCurrentAccountName(), null);
  }

  private void launchUrlInBrowser(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    localIntent.putExtra("com.android.browser.application_id", getPackageName());
    startActivity(localIntent);
  }

  private void maybeShowErrorDialog(Intent paramIntent)
  {
    if (paramIntent.hasExtra("error_html_message"))
    {
      String str1 = null;
      if (paramIntent.hasExtra("error_title"))
        str1 = paramIntent.getStringExtra("error_title");
      String str2 = paramIntent.getStringExtra("error_html_message");
      String str3 = paramIntent.getStringExtra("error_doc_id");
      int i = (str2 + str1 + str3).hashCode();
      if (this.mLastShownErrorHash != i)
      {
        showErrorDialogForCode(str1, str2, paramIntent.getIntExtra("error_return_code", -1), str3);
        this.mLastShownErrorHash = i;
      }
    }
  }

  private void onMyCollectionSelected()
  {
    PackageManager localPackageManager = getPackageManager();
    int i = getCurrentBackend();
    switch (i)
    {
    case 3:
    case 5:
    default:
      this.mNavigationManager.goToMyDownloads(FinskyApp.get().getToc());
    case 1:
    case 2:
    case 4:
    case 6:
    }
    while (true)
    {
      return;
      if (!IntentUtils.isConsumptionAppInstalled(localPackageManager, i))
        this.mNavigationManager.showAppNeededDialog(i);
      else
        startActivity(IntentUtils.buildConsumptionAppLaunchIntent(localPackageManager, i, FinskyApp.get().getCurrentAccountName()));
    }
  }

  private void onMyWishlistSelected()
  {
    this.mNavigationManager.goToMyWishlist();
  }

  private void redeemGiftCard()
  {
    String str = FinskyApp.get().getCurrentAccountName();
    if (str == null)
      FinskyLog.wtf("Redeem chosen with no current account.", new Object[0]);
    while (true)
    {
      return;
      RedeemGiftCardActivity.show(this, str, getCurrentBackend());
    }
  }

  private void setDebugActivityEnabled(boolean paramBoolean)
  {
    PackageManager localPackageManager = getPackageManager();
    if (paramBoolean);
    for (int i = 1; ; i = 2)
    {
      localPackageManager.setComponentEnabledSetting(new ComponentName(this, DebugActivity.class), i, 1);
      return;
    }
  }

  private void setupDcbDebugMenu(Menu paramMenu)
  {
    MenuItem localMenuItem = paramMenu.findItem(2131231291);
    localMenuItem.setVisible(true);
    localMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
      {
        Intent localIntent = new Intent(MainActivity.this, DcbDebugActivity.class);
        MainActivity.this.startActivity(localIntent);
        return true;
      }
    });
  }

  private void setupDebugMenu(Menu paramMenu)
  {
    MenuItem localMenuItem = paramMenu.findItem(2131231290);
    localMenuItem.setVisible(true);
    localMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(MenuItem paramAnonymousMenuItem)
      {
        MainActivity.this.startActivity(new Intent(MainActivity.this, DebugActivity.class));
        return true;
      }
    });
  }

  private boolean showErrorDialogForCode(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    PackageStateRepository.PackageState localPackageState = FinskyApp.get().getPackageInfoRepository().get(paramString3);
    switch (paramInt)
    {
    default:
    case 1:
    }
    label79: 
    while (true)
    {
      showErrorDialog(paramString1, paramString2, false);
      return true;
      if ((localPackageState != null) && (localPackageState.isSystemApp));
      for (int i = 1; ; i = 0)
      {
        if (i != 0)
          break label79;
        showMismatchedCertificatesDialog(paramString3);
        break;
      }
    }
  }

  private void showErrorMessage(VolleyError paramVolleyError)
  {
    if (this.mStateSaved)
    {
      PageFragment localPageFragment = this.mNavigationManager.getActivePage();
      if (localPageFragment != null)
        localPageFragment.refreshOnResume();
    }
    while (true)
    {
      return;
      if ((paramVolleyError instanceof BgDataDisabledError))
      {
        showBackgroundDataDialog();
      }
      else
      {
        String str = ErrorStrings.get(this, paramVolleyError);
        final View localView = findViewById(2131230777);
        localView.setVisibility(0);
        ((TextView)findViewById(2131230788)).setText(str);
        findViewById(2131231014).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            String str = FinskyApp.get().getCurrentAccountName();
            if (str == null)
            {
              FinskyLog.d("No account, restarting activity after network error", new Object[0]);
              MainActivity.this.restart();
            }
            while (true)
            {
              return;
              Account localAccount = AccountHandler.findAccount(str, MainActivity.this);
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = FinskyLog.scrubPii(localAccount.name);
              FinskyLog.d("b/5160617: Reinitialize account %s on retry button click", arrayOfObject);
              MainActivity.this.reinitialize(localAccount, false);
              MainActivity.this.showLoadingIndicator();
              MainActivity.this.findViewById(2131230776).setVisibility(0);
              localView.setVisibility(8);
            }
          }
        });
      }
    }
  }

  private void showMismatchedCertificatesDialog(String paramString)
  {
    SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165377, 2131165599, 2131165447);
    Bundle localBundle = new Bundle();
    localBundle.putString("error_package_name", paramString);
    localSimpleAlertDialog.setCallback(null, 32, localBundle);
    localSimpleAlertDialog.show(getSupportFragmentManager(), "mismatched_certificates");
  }

  private void updateConsumptionAppMenu()
  {
    if (this.mMyCollectionItem == null);
    while (true)
    {
      return;
      int i = this.mNavigationManager.getCurrentPageType();
      MenuItem localMenuItem = this.mMyCollectionItem;
      if (i != 3);
      int j;
      String str;
      for (boolean bool = true; ; bool = false)
      {
        localMenuItem.setVisible(bool);
        if (!this.mMyCollectionItem.isVisible())
          break;
        j = getCurrentBackend();
        str = CorpusResourceUtils.getCorpusMyCollectionDescription(j);
        if (!TextUtils.isEmpty(str))
          break label88;
        this.mMyCollectionItem.setVisible(false);
        break;
      }
      label88: this.mMyCollectionItem.setTitle(str);
      this.mMyCollectionItem.setIcon(CorpusResourceUtils.getCorpusMyCollectionIcon(j));
    }
  }

  private void updateWishlistAppMenu()
  {
    if (this.mMyWishlistItem == null)
      return;
    int i = this.mNavigationManager.getCurrentPageType();
    MenuItem localMenuItem = this.mMyWishlistItem;
    if (i != 10);
    for (boolean bool = true; ; bool = false)
    {
      localMenuItem.setVisible(bool);
      break;
    }
  }

  public BitmapLoader getBitmapLoader()
  {
    return FinskyApp.get().getBitmapLoader();
  }

  public CustomActionBar getCustomActionBar()
  {
    return this.mActionBar;
  }

  public DfeApi getDfeApi()
  {
    return FinskyApp.get().getDfeApi();
  }

  public NavigationManager getNavigationManager()
  {
    return this.mNavigationManager;
  }

  public void goBack()
  {
    onBackPressed();
  }

  protected void handleAuthenticationError(VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError))
    {
      Intent localIntent = ((AuthFailureError)paramVolleyError).getResolutionIntent();
      if (localIntent != null)
        handleUserAuthentication(localIntent);
    }
    while (true)
    {
      return;
      hideLoadingIndicator();
      findViewById(2131230776).setVisibility(8);
      showErrorMessage(paramVolleyError);
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 31) && (paramInt2 == 40))
    {
      FinskyLog.d("b/5160617: Reinitialize with null accountafter user changed content level", new Object[0]);
      reinitialize(null, true);
      return;
    }
    boolean bool2;
    int i;
    if (paramInt1 == 33)
    {
      String str1 = getIntent().getStringExtra("requested_doc_id");
      String str2 = null;
      boolean bool1 = false;
      if (paramIntent != null)
      {
        bool1 = paramIntent.getBooleanExtra("finsky.is_direct_link_purchase", false);
        str2 = paramIntent.getStringExtra("requested_doc_id");
        paramIntent = null;
      }
      bool2 = getIntent().getBooleanExtra("use_direct_purchase", false);
      if ((str1 != null) && (str1.equals(str2)))
      {
        i = 1;
        label109: if ((paramInt2 != -1) || (!bool1) || (i == 0))
          break label160;
        Toast.makeText(this, 2131165286, 1).show();
        setResult(-1);
        finish();
      }
    }
    while (true)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      break;
      i = 0;
      break label109;
      label160: if ((paramInt2 == 0) && (bool2))
      {
        setResult(0);
        finish();
        continue;
        if ((paramInt1 == 34) && (paramInt2 == -1))
        {
          FinskyApp.get().getDfeApi().invalidatePlusProfile(true);
          FinskyPreferences.acceptedPlusReviews.get(FinskyApp.get().getCurrentAccountName()).put(Boolean.valueOf(true));
        }
      }
    }
  }

  public void onBackPressed()
  {
    if (!this.mNavigationManager.goBack())
      super.onBackPressed();
  }

  protected void onCleanup()
  {
    FinskyApp.get().drainAllRequests(FinskyApp.get().getRequestQueue().getSequenceNumber());
    FinskyApp.get().clearCacheAsync(null);
    if (!this.mStateSaved)
    {
      this.mNavigationManager.clear();
      this.mNavigationManager.flush();
    }
    ViewGroup localViewGroup;
    ArrayList localArrayList;
    while (true)
    {
      localViewGroup = (ViewGroup)getWindow().findViewById(2131230775);
      int i = localViewGroup.getChildCount();
      localArrayList = Lists.newArrayList();
      for (int j = 0; j < i; j++)
      {
        View localView = localViewGroup.getChildAt(j);
        int k = localView.getId();
        if ((k != 2131230776) && (k != 2131230777))
          localArrayList.add(localView);
      }
      FinskyLog.w("Unable to clear NavigationManager as state has been saved.", new Object[0]);
    }
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
      localViewGroup.removeView((View)localIterator.next());
    showLoadingIndicator();
  }

  public void onCreate(Bundle paramBundle)
  {
    this.mSavedInstanceState = paramBundle;
    setContentView(2130968728);
    this.mNavigationManager = new NavigationManager(this);
    this.mNavigationManager.init(this);
    if (paramBundle != null)
      this.mLastShownErrorHash = paramBundle.getInt("last_shown_error_hash");
    super.onCreate(paramBundle);
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    setDefaultKeyMode(3);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    MenuInflater localMenuInflater = getMenuInflater();
    localMenuInflater.inflate(2131689474, paramMenu);
    localMenuInflater.inflate(2131689472, paramMenu);
    if (((Boolean)G.wishlistEnabled.get()).booleanValue())
      localMenuInflater.inflate(2131689477, paramMenu);
    localMenuInflater.inflate(2131689473, paramMenu);
    this.mActionBar.configureMenu(this, paramMenu);
    this.mMyCollectionItem = paramMenu.findItem(2131231292);
    this.mMyWishlistItem = paramMenu.findItem(2131231295);
    updateConsumptionAppMenu();
    updateWishlistAppMenu();
    return true;
  }

  protected void onDestroy()
  {
    this.mNavigationManager.terminate();
    super.onDestroy();
  }

  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    boolean bool1 = false;
    boolean bool2 = true;
    switch (paramMenuItem.getItemId())
    {
    case 2131231289:
    case 2131231290:
    case 2131231291:
    case 2131231293:
    case 2131231294:
    default:
      bool2 = super.onMenuItemSelected(paramInt, paramMenuItem);
    case 2131231292:
    case 2131231295:
    case 2131231286:
    case 2131231284:
    case 2131231285:
    case 2131231287:
    case 2131231288:
    }
    while (true)
    {
      return bool2;
      onMyCollectionSelected();
      continue;
      onMyWishlistSelected();
      continue;
      startActivityForResult(new Intent(FinskyApp.get(), SettingsActivity.class), 31);
      continue;
      redeemGiftCard();
      continue;
      if (this.mNavigationManager.getCurrentPageType() == 3)
        bool1 = bool2;
      chooseAccount(bool1);
      continue;
      FinskyApp.get().getAnalytics().logPageView(null, null, "help");
      FinskyApp.get().getEventLogger().logTag("help", new Object[0]);
      launchUrlInBrowser(BillingUtils.replaceLocale((String)G.vendingSupportUrl.get()));
      continue;
      launchUrlInBrowser((String)G.extraMenuItemUrl.get());
    }
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default:
    case 32:
    }
    while (true)
    {
      return;
      String str = paramBundle.getString("error_package_name");
      FinskyApp.get().getInstaller().uninstallAssetSilently(str);
    }
  }

  protected void onNewIntent(Intent paramIntent)
  {
    setIntent(paramIntent);
    if (!this.mStateSaved)
      handleIntent();
    while (true)
    {
      return;
      this.mStateSaved = false;
      super.onNewIntent(paramIntent);
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = true;
    switch (paramMenuItem.getItemId())
    {
    default:
      bool = super.onOptionsItemSelected(paramMenuItem);
    case 16908332:
    case 2131230735:
    case 2131230736:
    }
    while (true)
    {
      return bool;
      this.mNavigationManager.goUp();
      continue;
      this.mActionBar.wishlistButtonClicked(this);
      continue;
      this.mActionBar.shareButtonClicked(this);
    }
  }

  protected void onPause()
  {
    super.onPause();
    FinskyApp.get().getNotifier().setNotificationListener(null);
    this.mSequenceNumberToDrainFrom = FinskyApp.get().getRequestQueue().getSequenceNumber();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (this.mNavigationManager != null)
      this.mNavigationManager.onPositiveClick(paramInt, paramBundle);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    String str = (String)G.extraMenuItemTitle.get();
    if (!TextUtils.isEmpty(str))
    {
      MenuItem localMenuItem = paramMenu.findItem(2131231288);
      localMenuItem.setTitle(str);
      localMenuItem.setVisible(true);
    }
    paramMenu.findItem(2131231284).setVisible(((Boolean)G.enableRedeemGiftCardMenu.get()).booleanValue());
    setDebugActivityEnabled(((Boolean)G.debugOptionsEnabled.get()).booleanValue());
    if (((Boolean)G.debugOptionsEnabled.get()).booleanValue())
      setupDebugMenu(paramMenu);
    if (((Boolean)G.dcbDebugOptionsEnabled.get()).booleanValue())
      setupDcbDebugMenu(paramMenu);
    return true;
  }

  protected void onReady(final boolean paramBoolean)
  {
    checkHasPromoOffers(new Runnable()
    {
      public void run()
      {
        if ((MainActivity.this.mSavedInstanceState != null) && (MainActivity.this.mNavigationManager.deserialize(MainActivity.this.mSavedInstanceState)));
        while (true)
        {
          MainActivity.access$202(MainActivity.this, null);
          MainActivity.this.initializeBilling();
          return;
          if (paramBoolean)
            MainActivity.this.handleIntent();
        }
      }
    });
  }

  protected void onRestart()
  {
    super.onRestart();
    this.mPageNeedsRefresh = true;
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mPageNeedsRefresh)
    {
      this.mNavigationManager.refreshPage();
      this.mPageNeedsRefresh = false;
    }
    FinskyApp.get().getNotifier().setNotificationListener(this.mNotificationListener);
  }

  public Object onRetainCustomNonConfigurationInstance()
  {
    this.mHandler.removeCallbacks(this.mStopPreviewsRunnable);
    return super.onRetainCustomNonConfigurationInstance();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mSavedInstanceState != null)
      paramBundle.putAll(this.mSavedInstanceState);
    while (true)
    {
      super.onSaveInstanceState(paramBundle);
      paramBundle.putInt("last_shown_error_hash", this.mLastShownErrorHash);
      return;
      this.mNavigationManager.serialize(paramBundle);
    }
  }

  public boolean onSearchRequested()
  {
    boolean bool = false;
    if ((isTosAccepted()) && ((getCustomActionBar().searchButtonClicked(this)) || (super.onSearchRequested())))
      bool = true;
    return bool;
  }

  protected void onStop()
  {
    super.onStop();
    this.mHandler.post(this.mStopPreviewsRunnable);
    if (this.mSequenceNumberToDrainFrom == -1)
      FinskyApp.get().drainAllRequests(FinskyApp.get().getRequestQueue().getSequenceNumber());
    while (true)
    {
      return;
      FinskyApp.get().drainAllRequests(this.mSequenceNumberToDrainFrom);
      this.mSequenceNumberToDrainFrom = -1;
    }
  }

  public void showErrorDialog(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString2))
      if (this.mStateSaved)
      {
        FinskyLog.e(paramString2, new Object[0]);
        PageFragment localPageFragment = this.mNavigationManager.getActivePage();
        if (localPageFragment != null)
          localPageFragment.refreshOnResume();
      }
    while (true)
    {
      return;
      ErrorDialog.show(getSupportFragmentManager(), null, paramString2, paramBoolean);
      continue;
      FinskyLog.wtf("Unknown error with empty error message.", new Object[0]);
    }
  }

  public void updateBreadcrumb(String paramString)
  {
    this.mActionBar.updateBreadcrumb(paramString);
  }

  public void updateCurrentBackendId(int paramInt)
  {
    this.mActionBar.updateCurrentBackendId(paramInt);
    updateConsumptionAppMenu();
    updateWishlistAppMenu();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.MainActivity
 * JD-Core Version:    0.6.2
 */
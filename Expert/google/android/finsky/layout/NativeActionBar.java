package com.google.android.finsky.layout;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.providers.RecentSuggestionsProvider;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;

public class NativeActionBar
  implements CustomActionBar
{
  private static final boolean ICS_OR_NEWER;
  private final ActionBar mActionBar;
  private Activity mActivity;
  private int mCurrentBackendId;
  private MenuItem mMyCollectionItem;
  private NavigationManager mNavigationManager;
  private String mRequestedTitle;
  private MenuItem mSearchItem;
  private SearchView mSearchView;
  private MenuItem mShareItem;
  private boolean mUseActionBarTabs;
  private MenuItem mWishlistItem;

  static
  {
    if (Build.VERSION.SDK_INT >= 14);
    for (boolean bool = true; ; bool = false)
    {
      ICS_OR_NEWER = bool;
      return;
    }
  }

  public NativeActionBar(Activity paramActivity)
  {
    this.mActionBar = paramActivity.getActionBar();
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)paramActivity.getResources().getDrawable(2130837709);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    this.mActionBar.setBackgroundDrawable(localBitmapDrawable);
    this.mActivity = paramActivity;
  }

  private void clearSearchIfNecessary()
  {
    if (this.mSearchView == null);
    while (true)
    {
      return;
      if (this.mNavigationManager.getCurrentPageType() != 7)
      {
        this.mSearchView.setQuery("", false);
        this.mSearchView.setIconified(true);
        if (ICS_OR_NEWER)
          this.mSearchItem.collapseActionView();
      }
    }
  }

  private void setupCorpusIcon()
  {
    if (!ICS_OR_NEWER);
    while (true)
    {
      return;
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if ((localDfeToc != null) && (localDfeToc.hasIconOverrideUrl()))
      {
        BitmapLoader.BitmapContainer localBitmapContainer = FinskyApp.get().getBitmapLoader().get(localDfeToc.getIconOverrideUrl(), null, new BitmapLoader.BitmapLoadedHandler()
        {
          public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
          {
            BitmapDrawable localBitmapDrawable = new BitmapDrawable(NativeActionBar.this.mActivity.getResources(), paramAnonymousBitmapContainer.getBitmap());
            NativeActionBar.this.mActionBar.setIcon(localBitmapDrawable);
          }
        });
        if (localBitmapContainer.getBitmap() != null)
        {
          BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.mActivity.getResources(), localBitmapContainer.getBitmap());
          this.mActionBar.setIcon(localBitmapDrawable);
        }
      }
    }
  }

  private void syncActionBar()
  {
    setupCorpusIcon();
    syncDetailsPageMenuItem();
    if (this.mSearchItem != null)
      this.mSearchItem.setVisible(this.mNavigationManager.canSearch());
    this.mActionBar.setDisplayHomeAsUpEnabled(this.mNavigationManager.canGoUp());
    if (this.mMyCollectionItem != null)
      this.mMyCollectionItem.setIcon(CorpusResourceUtils.getCorpusMyCollectionIcon(this.mCurrentBackendId));
  }

  private void syncDetailsPageMenuItem()
  {
    boolean bool;
    DfeApi localDfeApi;
    Document localDocument;
    int i;
    if (this.mNavigationManager.getCurrentPageType() == 5)
    {
      bool = true;
      if (this.mShareItem != null)
        this.mShareItem.setVisible(bool);
      if (this.mWishlistItem != null)
      {
        localDfeApi = FinskyApp.get().getDfeApi();
        localDocument = this.mNavigationManager.getCurrentDocument();
        if ((localDocument != null) && (bool) && (!WishlistHelper.shouldHideWishlistAction(localDocument, localDfeApi)))
          break label94;
        i = 1;
        label72: if (i == 0)
          break label100;
        this.mWishlistItem.setVisible(false);
      }
    }
    while (true)
    {
      return;
      bool = false;
      break;
      label94: i = 0;
      break label72;
      label100: this.mWishlistItem.setVisible(true);
      syncWishlistStatus(WishlistHelper.isInWishlist(localDocument, localDfeApi.getAccount()));
    }
  }

  private void syncDisplayTitle()
  {
    if (TextUtils.isEmpty(this.mRequestedTitle))
    {
      this.mActionBar.setTitle(2131165393);
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if ((localDfeToc != null) && (this.mCurrentBackendId != 0))
      {
        Toc.CorpusMetadata localCorpusMetadata = localDfeToc.getCorpus(this.mCurrentBackendId);
        if (localCorpusMetadata != null)
          this.mActionBar.setTitle(localCorpusMetadata.getName());
      }
    }
    while (true)
    {
      return;
      this.mActionBar.setTitle(this.mRequestedTitle);
    }
  }

  private void syncWishlistStatus(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mWishlistItem.setIcon(2130837647);
      this.mWishlistItem.setTitle(2131165554);
    }
    while (true)
    {
      return;
      this.mWishlistItem.setIcon(2130837646);
      this.mWishlistItem.setTitle(2131165553);
    }
  }

  public void addTab(String paramString, final CustomActionBar.TabListener paramTabListener)
  {
    if (!this.mUseActionBarTabs);
    while (true)
    {
      return;
      ActionBar.Tab localTab = this.mActionBar.newTab();
      localTab.setText(paramString);
      if (Build.VERSION.SDK_INT >= 14)
        localTab.setContentDescription(paramString.toLowerCase());
      localTab.setTabListener(new ActionBar.TabListener()
      {
        public void onTabReselected(ActionBar.Tab paramAnonymousTab, FragmentTransaction paramAnonymousFragmentTransaction)
        {
          paramTabListener.onTabReselected(paramAnonymousTab.getPosition());
        }

        public void onTabSelected(ActionBar.Tab paramAnonymousTab, FragmentTransaction paramAnonymousFragmentTransaction)
        {
          paramTabListener.onTabSelected(paramAnonymousTab.getPosition());
        }

        public void onTabUnselected(ActionBar.Tab paramAnonymousTab, FragmentTransaction paramAnonymousFragmentTransaction)
        {
          paramTabListener.onTabUnselected(paramAnonymousTab.getPosition());
        }
      });
      this.mActionBar.setNavigationMode(2);
      this.mActionBar.setDisplayShowTitleEnabled(false);
      this.mActionBar.addTab(localTab);
    }
  }

  public void clearTabs()
  {
    this.mActionBar.setNavigationMode(0);
    this.mActionBar.setDisplayShowTitleEnabled(true);
    this.mActionBar.removeAllTabs();
  }

  public void configureMenu(Activity paramActivity, Menu paramMenu)
  {
    this.mSearchItem = paramMenu.findItem(2131230737);
    if (ICS_OR_NEWER);
    for (Object localObject = this.mActionBar.getThemedContext(); ; localObject = paramActivity)
    {
      this.mSearchView = ((SearchView)LayoutInflater.from((Context)localObject).inflate(2130968577, null));
      this.mSearchItem.setActionView(this.mSearchView);
      this.mShareItem = paramMenu.findItem(2131230736);
      this.mWishlistItem = paramMenu.findItem(2131230735);
      this.mMyCollectionItem = paramMenu.findItem(2131231292);
      SearchManager localSearchManager = (SearchManager)paramActivity.getSystemService("search");
      this.mSearchView.setSearchableInfo(localSearchManager.getSearchableInfo(paramActivity.getComponentName()));
      if (this.mNavigationManager == null)
      {
        this.mSearchView.setVisibility(8);
        this.mShareItem.setVisible(false);
        this.mSearchItem.setVisible(false);
        this.mWishlistItem.setVisible(false);
      }
      return;
    }
  }

  public String getBreadcrumbText()
  {
    if (this.mActionBar.getTitle() != null);
    for (String str = String.valueOf(this.mActionBar.getTitle()); ; str = null)
      return str;
  }

  public int getCurrentBackendId()
  {
    return this.mCurrentBackendId;
  }

  public void hide()
  {
    if (this.mActionBar == null);
    while (true)
    {
      return;
      this.mActionBar.hide();
    }
  }

  public void initialize(NavigationManager paramNavigationManager, Activity paramActivity)
  {
    this.mNavigationManager = paramNavigationManager;
    setupCorpusIcon();
    syncActionBar();
    this.mNavigationManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
    {
      public void onBackStackChanged()
      {
        NativeActionBar.this.clearSearchIfNecessary();
        NativeActionBar.this.syncActionBar();
      }
    });
    this.mUseActionBarTabs = paramActivity.getResources().getBoolean(2131296263);
  }

  public boolean searchButtonClicked(Activity paramActivity)
  {
    if ((this.mSearchItem != null) && (ICS_OR_NEWER))
      if (this.mSearchItem.isActionViewExpanded())
        this.mSearchItem.collapseActionView();
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      this.mSearchItem.expandActionView();
      break;
    }
  }

  public void setSelectedTab(int paramInt)
  {
    this.mActionBar.setSelectedNavigationItem(paramInt);
  }

  public void shareButtonClicked(Activity paramActivity)
  {
    Document localDocument = this.mNavigationManager.getCurrentDocument();
    if (localDocument != null)
    {
      Intent localIntent = IntentUtils.buildShareIntent(paramActivity.getApplicationContext(), localDocument);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localDocument.getTitle();
      paramActivity.startActivity(Intent.createChooser(localIntent, paramActivity.getString(2131165582, arrayOfObject1)));
      FinskyApp.get().getAnalytics().logPageView(null, null, "share?doc=" + localDocument.getDocId());
      FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = "cidi";
      arrayOfObject2[1] = localDocument.getDocId();
      localFinskyEventLog.logTag("share", arrayOfObject2);
    }
    while (true)
    {
      return;
      FinskyLog.w("Tried to share an item but there is no document active", new Object[0]);
    }
  }

  public void updateBreadcrumb(String paramString)
  {
    this.mRequestedTitle = paramString;
    syncDisplayTitle();
    syncActionBar();
  }

  public void updateCurrentBackendId(int paramInt)
  {
    this.mCurrentBackendId = paramInt;
    RecentSuggestionsProvider.setCurrentBackendId(this.mCurrentBackendId);
    syncDisplayTitle();
    syncActionBar();
  }

  public void updateSearchQuery(String paramString)
  {
    if (this.mSearchView != null)
    {
      this.mSearchView.setQuery(paramString, false);
      this.mSearchView.clearFocus();
    }
  }

  public void wishlistButtonClicked(Activity paramActivity)
  {
    WishlistHelper.WishlistStatusListener local2 = new WishlistHelper.WishlistStatusListener()
    {
      public void onWishlistStatusChanged(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        Document localDocument = NativeActionBar.this.mNavigationManager.getCurrentDocument();
        if ((localDocument != null) && (paramAnonymousString.equals(localDocument.getDocId())))
          NativeActionBar.this.syncWishlistStatus(paramAnonymousBoolean);
      }
    };
    WishlistHelper.processWishlistClick(this.mNavigationManager, FinskyApp.get().getDfeApi(), local2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.NativeActionBar
 * JD-Core Version:    0.6.2
 */
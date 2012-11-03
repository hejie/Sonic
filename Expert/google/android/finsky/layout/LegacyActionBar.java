package com.google.android.finsky.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SearchFragment;
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
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.WishlistHelper;
import com.google.android.finsky.utils.WishlistHelper.WishlistStatusListener;

public class LegacyActionBar extends LinearLayout
  implements CustomActionBar
{
  private Activity mActivity;
  private TextView mBreadcrumb;
  private ImageView mCorpusChevronIcon;
  private View mCorpusUpPack;
  private int mCurrentBackendId;
  private final View.OnClickListener mGoUpClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (LegacyActionBar.this.mNavigationManager != null)
        LegacyActionBar.this.mNavigationManager.goUp();
      while (true)
      {
        return;
        LegacyActionBar.this.mActivity.finish();
      }
    }
  };
  private NavigationManager mNavigationManager;
  private String mRequestedTitle;
  private ImageButton mSearchButton;
  private ImageButton mShareButton;
  private ImageButton mWishlistButton;

  public LegacyActionBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public LegacyActionBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setupBackground();
  }

  private void setupBackground()
  {
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837709);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    setBackgroundDrawable(localBitmapDrawable);
  }

  private void setupCorpusIcon()
  {
    this.mCorpusUpPack = findViewById(2131230731);
    this.mCorpusUpPack.setOnClickListener(this.mGoUpClickListener);
    this.mCorpusChevronIcon = ((ImageView)findViewById(2131230732));
  }

  private void setupSearchButton()
  {
    this.mSearchButton = ((ImageButton)findViewById(2131230737));
    this.mSearchButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (LegacyActionBar.this.mNavigationManager.getCurrentPageType() == 7)
        {
          SearchFragment localSearchFragment = (SearchFragment)LegacyActionBar.this.mNavigationManager.getActivePage();
          LegacyActionBar.this.mActivity.startSearch(localSearchFragment.getQuery(), true, null, false);
        }
        while (true)
        {
          return;
          LegacyActionBar.this.mActivity.onSearchRequested();
        }
      }
    });
  }

  private void setupShareButton()
  {
    this.mShareButton = ((ImageButton)findViewById(2131230736));
    this.mShareButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LegacyActionBar.this.shareButtonClicked(LegacyActionBar.this.mActivity);
      }
    });
  }

  private void setupWishlistButton()
  {
    this.mWishlistButton = ((ImageButton)findViewById(2131230735));
    this.mWishlistButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LegacyActionBar.this.wishlistButtonClicked(LegacyActionBar.this.mActivity);
      }
    });
  }

  private void syncActionBar()
  {
    int i;
    int j;
    label24: DfeApi localDfeApi;
    Document localDocument;
    int k;
    label68: label82: int m;
    label105: int n;
    label149: ImageButton localImageButton2;
    if (this.mNavigationManager.getCurrentPageType() == 5)
    {
      i = 1;
      ImageButton localImageButton1 = this.mShareButton;
      if (i == 0)
        break label323;
      j = 0;
      localImageButton1.setVisibility(j);
      localDfeApi = FinskyApp.get().getDfeApi();
      localDocument = this.mNavigationManager.getCurrentDocument();
      if ((localDocument != null) && (i != 0) && (!WishlistHelper.shouldHideWishlistAction(localDocument, localDfeApi)))
        break label329;
      k = 1;
      if (k == 0)
        break label335;
      this.mWishlistButton.setVisibility(8);
      boolean bool = this.mNavigationManager.canGoUp();
      ImageView localImageView1 = this.mCorpusChevronIcon;
      if (!bool)
        break label362;
      m = 0;
      localImageView1.setVisibility(m);
      this.mCorpusUpPack.setClickable(bool);
      this.mCorpusUpPack.setFocusable(bool);
      ImageView localImageView2 = this.mCorpusChevronIcon;
      if (!this.mNavigationManager.canGoUp())
        break label368;
      n = 0;
      localImageView2.setVisibility(n);
      localImageButton2 = this.mSearchButton;
      if (!this.mNavigationManager.canSearch())
        break label374;
    }
    label323: label329: label335: label362: label368: label374: for (int i1 = 0; ; i1 = 8)
    {
      localImageButton2.setVisibility(i1);
      if (this.mNavigationManager.isEmpty())
      {
        this.mCorpusUpPack.setClickable(false);
        this.mCorpusUpPack.setFocusable(false);
        this.mCorpusChevronIcon.setVisibility(4);
        this.mBreadcrumb.setVisibility(0);
        this.mBreadcrumb.setText(2131165653);
      }
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if ((localDfeToc != null) && (localDfeToc.hasIconOverrideUrl()))
      {
        final ImageView localImageView3 = (ImageView)findViewById(2131230733);
        BitmapLoader.BitmapContainer localBitmapContainer = FinskyApp.get().getBitmapLoader().get(localDfeToc.getIconOverrideUrl(), null, new BitmapLoader.BitmapLoadedHandler()
        {
          public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
          {
            BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramAnonymousBitmapContainer.getBitmap());
            localImageView3.setImageDrawable(localBitmapDrawable);
          }
        });
        if (localBitmapContainer.getBitmap() != null)
          localImageView3.setImageDrawable(new BitmapDrawable(localBitmapContainer.getBitmap()));
      }
      return;
      i = 0;
      break;
      j = 8;
      break label24;
      k = 0;
      break label68;
      this.mWishlistButton.setVisibility(0);
      syncWishlistStatus(WishlistHelper.isInWishlist(localDocument, localDfeApi.getAccount()));
      break label82;
      m = 4;
      break label105;
      n = 4;
      break label149;
    }
  }

  private void syncDisplayTitle()
  {
    if (TextUtils.isEmpty(this.mRequestedTitle))
    {
      this.mBreadcrumb.setText(2131165393);
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if ((localDfeToc != null) && (this.mCurrentBackendId != 0))
      {
        Toc.CorpusMetadata localCorpusMetadata = localDfeToc.getCorpus(this.mCurrentBackendId);
        if (localCorpusMetadata != null)
          this.mBreadcrumb.setText(localCorpusMetadata.getName());
      }
    }
    while (true)
    {
      this.mBreadcrumb.setVisibility(0);
      this.mBreadcrumb.setSelected(true);
      return;
      this.mBreadcrumb.setText(this.mRequestedTitle);
    }
  }

  private void syncWishlistStatus(boolean paramBoolean)
  {
    Resources localResources = FinskyApp.get().getResources();
    if (paramBoolean)
    {
      this.mWishlistButton.setImageResource(2130837647);
      this.mWishlistButton.setContentDescription(localResources.getString(2131165554));
    }
    while (true)
    {
      return;
      this.mWishlistButton.setImageResource(2130837646);
      this.mWishlistButton.setContentDescription(localResources.getString(2131165553));
    }
  }

  public void addTab(String paramString, CustomActionBar.TabListener paramTabListener)
  {
  }

  public void clearTabs()
  {
  }

  public void configureMenu(Activity paramActivity, Menu paramMenu)
  {
  }

  public String getBreadcrumbText()
  {
    return String.valueOf(this.mBreadcrumb.getText());
  }

  public int getCurrentBackendId()
  {
    return this.mCurrentBackendId;
  }

  public void hide()
  {
    setVisibility(8);
  }

  public void initialize(NavigationManager paramNavigationManager, Activity paramActivity)
  {
    this.mNavigationManager = paramNavigationManager;
    this.mActivity = paramActivity;
    this.mBreadcrumb = ((TextView)findViewById(2131230734));
    this.mBreadcrumb.setOnClickListener(this.mGoUpClickListener);
    setupSearchButton();
    setupWishlistButton();
    setupShareButton();
    setupCorpusIcon();
    syncActionBar();
    this.mNavigationManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener()
    {
      public void onBackStackChanged()
      {
        LegacyActionBar.this.syncActionBar();
      }
    });
    syncActionBar();
  }

  public boolean searchButtonClicked(Activity paramActivity)
  {
    return false;
  }

  public void setSelectedTab(int paramInt)
  {
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
  }

  public void wishlistButtonClicked(Activity paramActivity)
  {
    WishlistHelper.WishlistStatusListener local7 = new WishlistHelper.WishlistStatusListener()
    {
      public void onWishlistStatusChanged(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        Document localDocument = LegacyActionBar.this.mNavigationManager.getCurrentDocument();
        if ((localDocument != null) && (paramAnonymousString.equals(localDocument.getDocId())))
          LegacyActionBar.this.syncWishlistStatus(paramAnonymousBoolean);
      }
    };
    WishlistHelper.processWishlistClick(this.mNavigationManager, FinskyApp.get().getDfeApi(), local7);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.LegacyActionBar
 * JD-Core Version:    0.6.2
 */
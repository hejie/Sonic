package com.google.android.finsky.navigationmanager;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AntennaFragment;
import com.google.android.finsky.activities.CorporaHomeFragment;
import com.google.android.finsky.activities.CreatorDetailsFragment;
import com.google.android.finsky.activities.DetailsDataBasedFragment;
import com.google.android.finsky.activities.DetailsFragment;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.FlagItemDialog;
import com.google.android.finsky.activities.FreeSongOfTheDayFragment;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.activities.PurchaseDialog;
import com.google.android.finsky.activities.ReviewsActivity;
import com.google.android.finsky.activities.ScreenshotsActivity;
import com.google.android.finsky.activities.SearchFragment;
import com.google.android.finsky.activities.SynchronousPurchaseActivity;
import com.google.android.finsky.activities.TabbedBrowseFragment;
import com.google.android.finsky.activities.myapps.MyAppsTabbedFragment;
import com.google.android.finsky.activities.mywishlist.MyWishlistFragment;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.DeepLinkShimFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.remoting.protos.Containers.ContainerMetadata;
import com.google.android.finsky.remoting.protos.DocAnnotations.Link;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.DetailsShimFragment;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.MainThreadStack;
import com.google.android.finsky.utils.PurchaseInitiator;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NavigationManager
{
  private MainActivity mActivity;
  private final Stack<NavigationState> mBackStack = new MainThreadStack();
  private FragmentManager mFragmentManager;

  public NavigationManager(MainActivity paramMainActivity)
  {
    init(paramMainActivity);
  }

  private boolean canNavigate()
  {
    if ((this.mActivity != null) && (!this.mActivity.isStateSaved()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void goToAggregatedHome()
  {
    goToAggregatedHome(FinskyApp.get().getToc());
  }

  private void goToCorpusHome()
  {
    if ((this.mActivity == null) || (this.mActivity.isStateSaved()));
    while (true)
    {
      return;
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if (localDfeToc != null)
      {
        Document localDocument = getCurrentDocument();
        while (true)
        {
          if (!this.mBackStack.isEmpty())
          {
            NavigationState localNavigationState = (NavigationState)this.mBackStack.peek();
            int j = localNavigationState.pageType;
            if ((j == 3) && (localDocument != null) && (localDocument.getBackend() == 3))
            {
              this.mFragmentManager.popBackStack(localNavigationState.backstackName, 0);
              break;
            }
            if (j != 2)
              break label137;
          }
          if (this.mBackStack.isEmpty())
            break label148;
          this.mFragmentManager.popBackStack(((NavigationState)this.mBackStack.peek()).backstackName, 0);
          break;
          label137: this.mBackStack.pop();
        }
        label148: this.mFragmentManager.popBackStack(this.mFragmentManager.getBackStackEntryAt(0).getName(), 1);
        if (localDocument != null)
        {
          int i = localDocument.getBackend();
          Toc.CorpusMetadata localCorpusMetadata = localDfeToc.getCorpus(i);
          if (localCorpusMetadata != null)
            goToCorpusHome(localCorpusMetadata.getLandingUrl(), localCorpusMetadata.getName(), i, localDfeToc, null);
        }
        else
        {
          goToAggregatedHome();
        }
      }
    }
  }

  private void goToDocPage(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    goToDocPage(paramDocument, paramString1, paramString2, paramString3, paramString4, paramString5, false, paramBoolean);
  }

  private void goToDocPage(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!canNavigate());
    while (true)
    {
      return;
      int i = paramDocument.getDocumentType();
      if ((paramDocument.getBackend() == 2) && (paramDocument.hasAntennaInfo()))
        showPage(5, AntennaFragment.newInstance(paramDocument, paramString1, paramString2, paramString3), paramBoolean2);
      else if ((paramDocument.getBackend() == 2) && (paramDocument.hasDealOfTheDayInfo()))
        showPage(5, FreeSongOfTheDayFragment.newInstance(paramDocument, paramString1, paramString2, paramString3), paramBoolean2);
      else
        switch (i)
        {
        default:
          showPage(5, DetailsFragment.newInstance(paramDocument, paramString1, paramString2, paramString3, paramString4, paramString5, paramBoolean1), paramBoolean2);
          break;
        case 3:
        case 8:
        case 9:
        case 18:
        case 19:
        case 20:
          showPage(5, CreatorDetailsFragment.newInstance(paramDocument, paramString1, paramString2, paramString3), paramBoolean2);
          break;
        case 7:
          Resources localResources = this.mActivity.getResources();
          ErrorDialog.show(this.mActivity.getSupportFragmentManager(), localResources.getString(2131165442), localResources.getString(2131165763), false);
        }
    }
  }

  private void showPage(int paramInt, Fragment paramFragment)
  {
    showPage(paramInt, paramFragment, false);
  }

  private void showPage(int paramInt, Fragment paramFragment, boolean paramBoolean)
  {
    FinskyLog.startTiming();
    FragmentTransaction localFragmentTransaction = this.mFragmentManager.beginTransaction();
    localFragmentTransaction.replace(2131230775, paramFragment);
    if (paramBoolean)
      popBackStack();
    NavigationState localNavigationState = new NavigationState(paramInt);
    localFragmentTransaction.addToBackStack(localNavigationState.backstackName);
    localFragmentTransaction.setTransition(4097);
    this.mBackStack.push(localNavigationState);
    localFragmentTransaction.commit();
  }

  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    this.mFragmentManager.addOnBackStackChangedListener(paramOnBackStackChangedListener);
  }

  public void buy(Account paramAccount, Document paramDocument, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    buy(paramAccount, paramDocument, paramInt, paramString1, paramString2, paramString3, false);
  }

  public void buy(Account paramAccount, Document paramDocument, int paramInt, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (!paramDocument.needsConfirmation(paramInt))
      PurchaseInitiator.makeFreePurchase(paramAccount, paramDocument, paramInt, paramString2, paramDocument.getCookie(), paramString3);
    while (true)
    {
      return;
      switch (paramDocument.getBackend())
      {
      case 5:
      default:
        break;
      case 0:
        throw new IllegalArgumentException("Unsupported backend for buy.");
      case 1:
      case 2:
      case 3:
      case 4:
      case 6:
        goToPurchase(paramAccount, paramDocument.getDetailsUrl(), paramInt, paramString1, paramDocument.getCookie(), paramBoolean, paramString2, paramString3, paramDocument.getDocId(), paramBoolean);
      }
    }
  }

  public boolean canGoUp()
  {
    int i = 1;
    boolean bool = false;
    if (this.mBackStack.isEmpty());
    DfeToc localDfeToc;
    do
    {
      while (true)
      {
        return bool;
        NavigationState localNavigationState = (NavigationState)this.mBackStack.peek();
        if (localNavigationState.pageType != i)
        {
          if (localNavigationState.pageType == 2)
            break;
          bool = i;
        }
      }
      localDfeToc = getActivePage().getToc();
    }
    while (localDfeToc == null);
    if (localDfeToc.getCorpusList().size() > i);
    while (true)
    {
      bool = i;
      break;
      i = 0;
    }
  }

  public boolean canSearch()
  {
    switch (getCurrentPageType())
    {
    default:
    case 8:
    }
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void clear()
  {
    while (!this.mBackStack.isEmpty())
    {
      this.mBackStack.pop();
      this.mFragmentManager.popBackStack();
    }
  }

  public boolean deserialize(Bundle paramBundle)
  {
    ArrayList localArrayList = paramBundle.getParcelableArrayList("nm_state");
    if ((localArrayList == null) || (localArrayList.size() == 0) || (getActivePage() == null));
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        NavigationState localNavigationState = (NavigationState)localIterator.next();
        this.mBackStack.push(localNavigationState);
      }
      getActivePage().rebindActionBar();
    }
  }

  public boolean flush()
  {
    return this.mFragmentManager.executePendingTransactions();
  }

  public PageFragment getActivePage()
  {
    return (PageFragment)this.mFragmentManager.findFragmentById(2131230775);
  }

  public View.OnClickListener getBuyImmediateClickListener(Account paramAccount, Document paramDocument, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    return getBuyImmediateClickListener(paramAccount, paramDocument, paramInt, paramString1, paramString2, paramString3, false);
  }

  public View.OnClickListener getBuyImmediateClickListener(final Account paramAccount, final Document paramDocument, final int paramInt, final String paramString1, final String paramString2, final String paramString3, final boolean paramBoolean)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NavigationManager.this.buy(paramAccount, paramDocument, paramInt, paramString1, paramString2, paramString3, paramBoolean);
      }
    };
  }

  public View.OnClickListener getClickListener(final Document paramDocument, final String paramString)
  {
    Object localObject;
    if (paramDocument.hasLinkAnnotation())
      localObject = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str = paramDocument.getLinkAnnotation().getUri();
          Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
          paramAnonymousView.getContext().startActivity(localIntent);
          FinskyApp.get().getEventLogger().logClick(paramDocument.getDocId(), paramString, str, paramDocument.getCookie(), null);
        }
      };
    while (true)
    {
      return localObject;
      if ((paramDocument.hasContainerAnnotation()) && (!TextUtils.isEmpty(paramDocument.getContainerAnnotation().getBrowseUrl())))
        localObject = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            String str = paramDocument.getContainerAnnotation().getBrowseUrl();
            NavigationManager.this.goBrowse(str, paramDocument.getTitle(), paramDocument.getBackend(), paramString, FinskyApp.get().getToc());
            FinskyApp.get().getEventLogger().logClick(paramDocument.getDocId(), paramString, str, paramDocument.getCookie(), null);
          }
        };
      else
        localObject = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            String str = paramDocument.getDetailsUrl();
            if (!paramDocument.canUseAsPartialDocument())
              NavigationManager.this.goToDocPage(str, paramDocument.getCookie(), paramString, null, null);
            while (true)
            {
              FinskyApp.get().getEventLogger().logClick(paramDocument.getDocId(), paramString, str, paramDocument.getCookie(), null);
              return;
              NavigationManager.this.goToDocPage(paramDocument, paramDocument.getCookie(), paramString, null, null);
            }
          }
        };
    }
  }

  public Document getCurrentDocument()
  {
    Document localDocument = null;
    if (this.mFragmentManager == null);
    while (true)
    {
      return localDocument;
      PageFragment localPageFragment = getActivePage();
      if ((localPageFragment != null) && ((localPageFragment instanceof DetailsDataBasedFragment)))
        localDocument = ((DetailsDataBasedFragment)localPageFragment).getDocument();
    }
  }

  public int getCurrentPageType()
  {
    if (this.mBackStack.isEmpty());
    for (int i = 0; ; i = ((NavigationState)this.mBackStack.peek()).pageType)
      return i;
  }

  public View.OnClickListener getOpenClickListener(final Document paramDocument, final Account paramAccount)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConsumptionUtils.openItem(NavigationManager.this.mActivity, null, NavigationManager.this.mFragmentManager, paramAccount, paramDocument);
      }
    };
  }

  public boolean goBack()
  {
    boolean bool = false;
    if ((this.mActivity == null) || (this.mActivity.isStateSaved()));
    while (true)
    {
      return bool;
      try
      {
        FinskyLog.startTiming();
        ((NavigationState)this.mBackStack.pop());
        this.mFragmentManager.popBackStack();
        ((NavigationState)this.mBackStack.peek());
        bool = true;
      }
      catch (EmptyStackException localEmptyStackException)
      {
      }
    }
  }

  public void goBrowse(String paramString1, String paramString2, int paramInt, String paramString3, DfeToc paramDfeToc)
  {
    if (canNavigate())
      showPage(4, TabbedBrowseFragment.newInstance(paramString1, paramString2, paramInt, paramString3, paramDfeToc));
  }

  public void goToAggregatedHome(DfeToc paramDfeToc)
  {
    goToAggregatedHome(paramDfeToc, null);
  }

  public void goToAggregatedHome(DfeToc paramDfeToc, String paramString)
  {
    if (paramDfeToc == null);
    while (true)
    {
      return;
      if (canNavigate())
      {
        clear();
        if (paramDfeToc.getCorpusList().size() == 1)
        {
          Toc.CorpusMetadata localCorpusMetadata = (Toc.CorpusMetadata)paramDfeToc.getCorpusList().get(0);
          showPage(2, TabbedBrowseFragment.newInstance(localCorpusMetadata.getLandingUrl(), localCorpusMetadata.getName(), localCorpusMetadata.getBackend(), paramString, paramDfeToc), true);
        }
        else
        {
          if (!TextUtils.isEmpty(paramDfeToc.getHomeUrl()));
          for (String str = paramDfeToc.getHomeUrl(); ; str = paramDfeToc.getCorpus(3).getLandingUrl())
          {
            goToAggregatedHome(paramDfeToc, str, paramString);
            break;
          }
        }
      }
      else
      {
        this.mActivity.restartOnResume();
      }
    }
  }

  public void goToAggregatedHome(DfeToc paramDfeToc, String paramString1, String paramString2)
  {
    showPage(1, CorporaHomeFragment.newInstance(paramDfeToc, paramString1, paramString2), true);
  }

  public void goToAllReviews(Document paramDocument)
  {
    if ((this.mActivity == null) || (this.mActivity.isStateSaved()));
    while (true)
    {
      return;
      ReviewsActivity.show(this.mActivity, paramDocument);
    }
  }

  public void goToCorpusHome(String paramString1, String paramString2, int paramInt, DfeToc paramDfeToc, String paramString3)
  {
    if (canNavigate())
      showPage(2, TabbedBrowseFragment.newInstance(paramString1, paramString2, paramInt, paramString3, paramDfeToc));
  }

  public void goToDocPage(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    goToDocPage(paramDocument, paramDocument.getDetailsUrl(), paramString1, paramString2, paramString3, paramString4, false);
  }

  public void goToDocPage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (!canNavigate());
    while (true)
    {
      return;
      showPage(6, DetailsShimFragment.newInstance(paramString1, paramString2, paramString3, paramString4, paramString5));
    }
  }

  public void goToDocPage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    if (!canNavigate());
    while (true)
    {
      return;
      showPage(6, DetailsShimFragment.newInstance(paramString1, paramString2, paramString3, paramString4, paramString5, paramBoolean));
    }
  }

  public void goToFlagContent(String paramString)
  {
    if (canNavigate())
      FlagItemDialog.show(this.mActivity, paramString);
  }

  public void goToImagesLightbox(Document paramDocument, int paramInt1, int paramInt2)
  {
    if ((this.mActivity == null) || (this.mActivity.isStateSaved()));
    while (true)
    {
      return;
      ScreenshotsActivity.show(this.mActivity, paramDocument, paramInt1, paramInt2);
    }
  }

  public void goToMyDownloads(DfeToc paramDfeToc)
  {
    if (canNavigate())
      showPage(3, MyAppsTabbedFragment.newInstance(paramDfeToc));
  }

  public void goToMyWishlist()
  {
    if (canNavigate())
      showPage(10, new MyWishlistFragment());
  }

  public void goToPurchase(Account paramAccount, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, boolean paramBoolean)
  {
    goToPurchase(paramAccount, paramString1, paramInt, paramString2, paramString3, false, paramString4, paramString5, paramString6, paramBoolean);
  }

  public void goToPurchase(Account paramAccount, String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, String paramString5, String paramString6, boolean paramBoolean2)
  {
    if (canNavigate())
    {
      if (!paramBoolean2)
        break label38;
      SynchronousPurchaseActivity.show(this.mActivity, paramAccount.name, paramString1, paramInt, paramString2, paramString3, paramBoolean1, paramString4, paramString6, 33);
    }
    while (true)
    {
      return;
      label38: PurchaseDialog.show(this.mActivity, paramAccount, paramString1, paramInt, paramString2, paramString3, paramString4, paramString5, paramString6);
    }
  }

  public void goToScreenshots(Document paramDocument, int paramInt)
  {
    goToImagesLightbox(paramDocument, paramInt, 1);
  }

  public void goToSearch(String paramString1, int paramInt, String paramString2)
  {
    goToSearch(paramString1, DfeUtils.formSearchUrl(paramString1, paramInt), paramString2);
  }

  public void goToSearch(String paramString1, String paramString2, String paramString3)
  {
    if (canNavigate())
    {
      showPage(7, SearchFragment.newInstance(paramString1, paramString2, paramString3), false);
      FinskyApp.get().getEventLogger().logSearch(paramString3, paramString2);
    }
  }

  public void goUp()
  {
    if ((this.mActivity == null) || (this.mActivity.isStateSaved()) || (this.mBackStack.isEmpty()));
    while (true)
    {
      return;
      switch (((NavigationState)this.mBackStack.peek()).pageType)
      {
      case 9:
      default:
        break;
      case 2:
        goToAggregatedHome();
        break;
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 10:
        goToCorpusHome();
        break;
      case 8:
        goBack();
      }
    }
  }

  public void handleDeepLink(String paramString)
  {
    showPage(9, DeepLinkShimFragment.newInstance(paramString));
  }

  public void init(MainActivity paramMainActivity)
  {
    this.mActivity = paramMainActivity;
    this.mFragmentManager = this.mActivity.getSupportFragmentManager();
  }

  public boolean isEmpty()
  {
    return this.mBackStack.isEmpty();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if ((paramInt == 1) && (paramBundle != null))
    {
      String str = paramBundle.getString("dialog_details_url");
      if ((str != null) && (canNavigate()))
        goToDocPage(str, null, null, null, null);
    }
  }

  public void openItem(Account paramAccount, Document paramDocument)
  {
    ConsumptionUtils.openItem(this.mActivity, null, this.mFragmentManager, paramAccount, paramDocument);
  }

  public void popBackStack()
  {
    if (!this.mBackStack.isEmpty())
      this.mBackStack.pop();
    this.mFragmentManager.popBackStack();
  }

  public void refreshPage()
  {
    if (this.mBackStack.isEmpty());
    while (true)
    {
      return;
      PageFragment localPageFragment = getActivePage();
      if (localPageFragment != null)
      {
        localPageFragment.refresh();
        localPageFragment.onDataChanged();
      }
      else
      {
        FinskyLog.e("Called refresh but there was no active page", new Object[0]);
      }
    }
  }

  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    this.mFragmentManager.removeOnBackStackChangedListener(paramOnBackStackChangedListener);
  }

  public void replaceDetailsShimWithDocPage(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    goToDocPage(paramDocument, paramString1, paramString2, paramString3, paramString4, paramString5, paramBoolean, true);
  }

  public void searchFromSuggestion(String paramString1, int paramInt, String paramString2)
  {
    if (canNavigate())
      showPage(7, SearchFragment.newInstance(paramString1, DfeUtils.formSearchUrl(paramString1, paramInt), paramString2), true);
  }

  public void serialize(Bundle paramBundle)
  {
    if ((this.mBackStack == null) || (this.mBackStack.isEmpty()));
    while (true)
    {
      return;
      paramBundle.putParcelableArrayList("nm_state", new ArrayList(this.mBackStack));
    }
  }

  public void showAppNeededDialog(int paramInt)
  {
    ConsumptionUtils.showAppNeededDialog(this.mActivity, null, this.mFragmentManager, paramInt);
  }

  public void terminate()
  {
    this.mActivity = null;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.navigationmanager.NavigationManager
 * JD-Core Version:    0.6.2
 */
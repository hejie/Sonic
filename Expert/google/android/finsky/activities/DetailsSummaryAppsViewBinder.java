package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.adapters.DownloadProgressHelper;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.ExpireLaunchUrlReceiver;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.AppSupport;
import com.google.android.finsky.utils.AppSupport.RefundListener;
import com.google.android.finsky.utils.AppSupport.RefundResult;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.PurchaseButtonHelper;

public class DetailsSummaryAppsViewBinder extends DetailsSummaryViewBinder
  implements InstallerListener, Libraries.Listener, PackageMonitorReceiver.PackageStatusListener
{
  private final AppStates mAppStates;
  private final Installer mInstaller;
  private final Libraries mLibraries;
  private boolean mListenersAdded;
  private final PackageMonitorReceiver mPackageMonitorReceiver;
  private boolean mTrackPackageStatus;

  public DetailsSummaryAppsViewBinder(DfeToc paramDfeToc, Account paramAccount, PackageMonitorReceiver paramPackageMonitorReceiver, Installer paramInstaller, AppStates paramAppStates, Libraries paramLibraries)
  {
    super(paramDfeToc, paramAccount);
    this.mPackageMonitorReceiver = paramPackageMonitorReceiver;
    this.mInstaller = paramInstaller;
    this.mAppStates = paramAppStates;
    this.mLibraries = paramLibraries;
  }

  private void attachListeners()
  {
    if (this.mTrackPackageStatus)
    {
      this.mPackageMonitorReceiver.detach(this);
      this.mPackageMonitorReceiver.attach(this);
      if (!this.mListenersAdded)
      {
        this.mInstaller.addListener(this);
        this.mLibraries.addListener(this);
        this.mListenersAdded = true;
      }
    }
  }

  private void confirmRefundApp(String paramString1, String paramString2, boolean paramBoolean)
  {
    FragmentManager localFragmentManager = this.mContainerFragment.getFragmentManager();
    if (localFragmentManager.findFragmentByTag("refund_confirm") != null);
    while (true)
    {
      return;
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165607, 2131165402, 2131165403);
      Bundle localBundle = new Bundle();
      localBundle.putString("package_name", paramString1);
      localBundle.putString("account_name", paramString2);
      localBundle.putBoolean("try_uninstall", paramBoolean);
      localSimpleAlertDialog.setCallback(this.mContainerFragment, 4, localBundle);
      localSimpleAlertDialog.show(localFragmentManager, "refund_confirm");
    }
  }

  private String getUpdateReferrerUrl(String paramString)
  {
    if ((this.mExternalReferrer != null) && (this.mExternalReferrer.contains("details?")));
    for (String str = this.mExternalReferrer.replaceFirst("details", "manualUpdate?doc=" + paramString); ; str = "manualUpdate?doc=" + paramString)
      return str;
  }

  private void listenerRefresh(boolean paramBoolean)
  {
    if (this.mContainerFragment.isAdded())
    {
      refresh();
      if ((paramBoolean) && ((this.mContainerFragment instanceof DetailsFragment)))
        ((DetailsFragment)this.mContainerFragment).updateDetailsSections();
    }
  }

  private void refreshByPackageName(String paramString)
  {
    if ((this.mDoc != null) && (this.mDoc.getAppDetails() != null) && (this.mDoc.getAppDetails().getPackageName().equals(paramString)))
    {
      syncDynamicSection();
      this.mNavigationManager.refreshPage();
    }
  }

  private void refundAndUninstallAsset(String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (!paramBoolean1)
      uninstallAsset(paramString1, true, paramBoolean2, paramBoolean3, paramBoolean4);
    while (true)
    {
      return;
      confirmRefundApp(paramString1, paramString2, true);
    }
  }

  private void refundApp(Bundle paramBundle)
  {
    AppSupport.silentRefund(paramBundle.getString("package_name"), paramBundle.getString("account_name"), new AppSupport.RefundListener()
    {
      public void onRefundComplete(AppSupport.RefundResult paramAnonymousRefundResult, String paramAnonymousString)
      {
        DetailsSummaryAppsViewBinder.this.mIsPendingRefund = false;
        DetailsSummaryAppsViewBinder.this.refresh();
        switch (DetailsSummaryAppsViewBinder.8.$SwitchMap$com$google$android$finsky$utils$AppSupport$RefundResult[paramAnonymousRefundResult.ordinal()])
        {
        default:
        case 1:
        case 2:
        case 3:
        case 4:
        }
        while (true)
        {
          return;
          if (this.val$tryUninstall)
          {
            if (DetailsSummaryAppsViewBinder.this.mInstaller != null)
              DetailsSummaryAppsViewBinder.this.mInstaller.uninstallAssetSilently(paramAnonymousString);
            DetailsSummaryAppsViewBinder.this.refresh();
            continue;
            FinskyLog.d("CANNOT_REFUND received by DFE.", new Object[0]);
            continue;
            DetailsSummaryAppsViewBinder.this.handleRefundFailure();
            continue;
            DetailsSummaryAppsViewBinder.this.handleRefundFailure();
          }
        }
      }

      public void onRefundStart()
      {
        DetailsSummaryAppsViewBinder.this.mIsPendingRefund = true;
        DetailsSummaryAppsViewBinder.this.refresh();
      }
    });
  }

  private void uninstallAsset(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    if (paramBoolean1)
      AppSupport.showUninstallConfirmationDialog(paramString, this.mContainerFragment, paramBoolean2, paramBoolean3, paramBoolean4);
    while (true)
    {
      return;
      this.mInstaller.uninstallAssetSilently(paramString);
      refresh();
    }
  }

  private void updateContainerLayouts()
  {
    updateLayoutVisibility(this.mButtonSection);
    updateLayoutVisibility(this.mDynamicSection);
  }

  private void updateLayoutVisibility(ViewGroup paramViewGroup)
  {
    int i = 8;
    for (int j = 0; ; j++)
      if (j < paramViewGroup.getChildCount())
      {
        if (paramViewGroup.getChildAt(j).getVisibility() != 8)
          i = 0;
      }
      else
      {
        paramViewGroup.setVisibility(i);
        return;
      }
  }

  public void bind(Document paramDocument, boolean paramBoolean, View[] paramArrayOfView)
  {
    super.bind(paramDocument, paramBoolean, paramArrayOfView);
    attachListeners();
  }

  protected void handleRefundFailure()
  {
    FragmentManager localFragmentManager = this.mContainerFragment.getFragmentManager();
    if (localFragmentManager != null)
    {
      AppSupport.showRefundFailureDialog(localFragmentManager);
      refresh();
    }
  }

  public void init(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, PageFragment paramPageFragment, boolean paramBoolean1, String paramString1, String paramString2, String paramString3, boolean paramBoolean2)
  {
    super.init(paramContext, paramNavigationManager, paramBitmapLoader, paramPageFragment, paramBoolean1, paramString1, paramString2, paramString3, paramBoolean2);
    this.mTrackPackageStatus = paramBoolean1;
    attachListeners();
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onDestroyView()
  {
    this.mPackageMonitorReceiver.detach(this);
    if (this.mListenersAdded)
    {
      this.mInstaller.removeListener(this);
      this.mLibraries.removeListener(this);
      this.mListenersAdded = false;
    }
    super.onDestroyView();
  }

  public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
  {
    if ((this.mDoc != null) && (paramString.equals(this.mDoc.getAppDetails().getPackageName())))
      listenerRefresh(false);
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    if ((this.mDoc != null) && (paramAccountLibrary.getAppEntry(this.mDoc.getAppDetails().getPackageName()) != null))
      listenerRefresh(true);
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPackageAdded(String paramString)
  {
    refreshByPackageName(paramString);
  }

  public void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean)
  {
  }

  public void onPackageChanged(String paramString)
  {
    refreshByPackageName(paramString);
  }

  public void onPackageFirstLaunch(String paramString)
  {
  }

  public void onPackageRemoved(String paramString, boolean paramBoolean)
  {
    this.mNavigationManager.refreshPage();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    case 3:
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Unexpected requestCode %d", arrayOfObject);
    case 2:
    case 1:
    case 4:
    }
    while (true)
    {
      return;
      String str = paramBundle.getString("package_name");
      if (this.mInstaller != null)
      {
        this.mInstaller.uninstallAssetSilently(str);
        refresh();
        continue;
        refundApp(paramBundle);
      }
    }
  }

  protected void setupActionButtons(boolean paramBoolean)
  {
    Button localButton1 = (Button)findViewById(2131230897);
    Button localButton2 = (Button)findViewById(2131230900);
    Button localButton3 = (Button)findViewById(2131230902);
    Button localButton4 = (Button)findViewById(2131230901);
    localButton2.setVisibility(8);
    localButton1.setVisibility(8);
    localButton3.setVisibility(8);
    localButton4.setVisibility(8);
    if ((this.mHideDynamicFeatures) || (paramBoolean))
      return;
    final String str = this.mDoc.getAppDetails().getPackageName();
    final AppActionAnalyzer localAppActionAnalyzer = new AppActionAnalyzer(str, this.mAppStates, this.mLibraries);
    int i = 0;
    int j;
    label163: label189: Account localAccount1;
    Object localObject;
    if (localAppActionAnalyzer.isUninstallable)
    {
      final boolean bool2 = DocUtils.hasAutoRenewingSubscriptions(this.mLibraries, this.mDoc);
      localButton3.setVisibility(0);
      i = 0 + 1;
      if (localAppActionAnalyzer.isRefundable)
      {
        j = 2131165601;
        localButton3.setText(j);
        localButton3.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            DetailsSummaryAppsViewBinder.this.refundAndUninstallAsset(str, localAppActionAnalyzer.isRefundable, localAppActionAnalyzer.refundAccount, localAppActionAnalyzer.isInstalledSystemApp, localAppActionAnalyzer.isInstalledOwnedPackage, bool2);
          }
        });
        localAccount1 = AppActionAnalyzer.getInstallAccount(str, this.mAccount, this.mAppStates, this.mLibraries);
        AccountLibrary localAccountLibrary = this.mLibraries.getAccountLibrary(localAccount1);
        if ((PurchaseButtonHelper.hasUpdateAvailable(localAppActionAnalyzer, this.mDoc)) && (LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, localAccountLibrary)) && (!localAppActionAnalyzer.isDisabled))
        {
          localButton4.setVisibility(0);
          localButton4.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(localAccount1, this.mDoc, 1, getUpdateReferrerUrl(str), this.mExternalReferrer, this.mContinueUrl));
          i++;
        }
        if (i < 2)
        {
          localObject = null;
          localButton2.setVisibility(0);
          if (!localAppActionAnalyzer.isLaunchable)
            break label593;
          if (!localAppActionAnalyzer.isContinueLaunch)
            break label566;
          localButton2.setText(2131165488);
          localObject = new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              DetailsSummaryAppsViewBinder.this.mNavigationManager.openItem(DetailsSummaryAppsViewBinder.this.mAccount, DetailsSummaryAppsViewBinder.this.mDoc);
              ExpireLaunchUrlReceiver.cancel(DetailsSummaryAppsViewBinder.this.mContext, DetailsSummaryAppsViewBinder.this.mDoc.getDocId());
              FinskyApp.get().getInstallerDataStore().setContinueUrl(DetailsSummaryAppsViewBinder.this.mDoc.getDocId(), null);
            }
          };
          label344: localButton2.setOnClickListener((View.OnClickListener)localObject);
        }
        if ((!localAppActionAnalyzer.isInstalled) && (LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, this.mLibraries)))
        {
          Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, FinskyApp.get().getLibraries(), this.mAccount);
          localButton1.setVisibility(0);
          if (localAccount2 == null)
            break label630;
        }
      }
    }
    label566: label593: label630: for (boolean bool1 = true; ; bool1 = false)
    {
      localButton1.setText(getBuyButtonString(bool1, 1));
      localButton1.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(localAccount1, this.mDoc, 1, this.mReferrer, this.mExternalReferrer, this.mContinueUrl, this.mReturnAfterPurchase));
      updateContainerLayouts();
      break;
      j = 2131165447;
      break label163;
      if ((!localAppActionAnalyzer.isUninstallable) && (localAppActionAnalyzer.isActiveDeviceAdmin))
      {
        localButton3.setVisibility(0);
        i = 0 + 1;
        localButton3.setText(2131165448);
        localButton3.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            SimpleAlertDialog.newInstance(2131165449, 2131165599, -1).show(DetailsSummaryAppsViewBinder.this.mContainerFragment.getFragmentManager(), null);
          }
        });
        break label189;
      }
      if (!localAppActionAnalyzer.isRefundable)
        break label189;
      localButton3.setVisibility(0);
      i = 0 + 1;
      localButton3.setText(2131165601);
      localButton3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DetailsSummaryAppsViewBinder.this.confirmRefundApp(str, localAppActionAnalyzer.refundAccount, false);
        }
      });
      break label189;
      localButton2.setText(2131165487);
      localObject = this.mNavigationManager.getOpenClickListener(this.mDoc, this.mAccount);
      break label344;
      if (localAppActionAnalyzer.isDisabled)
      {
        localObject = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            String str = DetailsSummaryAppsViewBinder.this.mDoc.getDocId();
            DetailsSummaryAppsViewBinder.this.mContext.getPackageManager().setApplicationEnabledSetting(str, 1, 0);
          }
        };
        localButton2.setText(2131165490);
        break label344;
      }
      localButton2.setVisibility(8);
      break label344;
    }
  }

  protected void syncDynamicSection()
  {
    resetDynamicStatus();
    if (this.mDoc.getBackend() != 3)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(this.mDoc.getBackend());
      arrayOfObject[1] = this.mDoc;
      FinskyLog.wtf("Unexpected doc backend %s", arrayOfObject);
      super.syncDynamicSection();
    }
    while (true)
    {
      return;
      final String str = this.mDoc.getAppDetails().getPackageName();
      if (!TextUtils.isEmpty(str))
      {
        Installer.InstallerProgressReport localInstallerProgressReport = this.mInstaller.getProgress(str);
        switch (8.$SwitchMap$com$google$android$finsky$receivers$Installer$InstallerState[localInstallerProgressReport.installerState.ordinal()])
        {
        default:
          this.mDynamicSection.setVisibility(0);
          this.mDynamicSection.setBackgroundColor(CorpusResourceUtils.getBackendHintColor(this.mContext, 3));
          ViewGroup localViewGroup = (ViewGroup)this.mDynamicSection.findViewById(2131230960);
          localViewGroup.setVisibility(0);
          TextView localTextView1 = (TextView)localViewGroup.findViewById(2131230929);
          TextView localTextView2 = (TextView)localViewGroup.findViewById(2131230928);
          ProgressBar localProgressBar = (ProgressBar)localViewGroup.findViewById(2131230930);
          DownloadProgressHelper.configureDownloadProgressUi(this.mContext, localInstallerProgressReport, localTextView1, localTextView2, localProgressBar);
          ((ImageView)localViewGroup.findViewById(2131230927)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              DetailsSummaryAppsViewBinder.this.mInstaller.cancel(str);
            }
          });
          ((TextView)findViewById(2131230957)).setSelected(false);
          setupActionButtons(true);
          break;
        case 1:
          showDynamicStatus(2131165597);
          setupActionButtons(true);
          break;
        case 2:
          showDynamicStatus(2131165598);
          setupActionButtons(true);
          break;
        case 3:
          super.syncDynamicSection();
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsSummaryAppsViewBinder
 * JD-Core Version:    0.6.2
 */
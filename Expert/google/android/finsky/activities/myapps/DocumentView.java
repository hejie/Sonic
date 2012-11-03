package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.activities.SubscriptionsViewBinder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.AppSecurityPermissions;
import com.google.android.finsky.layout.AutoUpdateSection;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.SubscriptionsSection;
import com.google.android.finsky.layout.WarningMessageSection;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DocumentView extends LinearLayout
  implements Response.ErrorListener, OnDataChangedListener
{
  private final AppStates mAppStates = FinskyApp.get().getAppStates();
  private final BitmapLoader mBitmapLoader = FinskyApp.get().getBitmapLoader();
  private DfeDetails mDetails;
  private final DfeApi mDfeApi = FinskyApp.get().getDfeApi();
  private String mDocId;
  private Document mDocument;
  private Fragment mFragment;
  private DocumentActionHandler mHandler;
  private boolean mHasSubscriptions;
  private final Installer mInstaller = FinskyApp.get().getInstaller();
  private InstallerListener mInstallerListener = null;
  private boolean mIsSystemPackage;
  private final Libraries mLibraries = FinskyApp.get().getLibraries();
  private NavigationManager mNavigationManager;
  private final Runnable mRebindRunnable = new Runnable()
  {
    public void run()
    {
      DocumentView.this.bind(Bundle.EMPTY);
    }
  };
  private final SubscriptionsViewBinder mSubscriptionsViewBinder = new SubscriptionsViewBinder();
  private final Handler mUiHandler = new Handler(Looper.getMainLooper());

  public DocumentView(Context paramContext)
  {
    this(paramContext, null);
  }

  public DocumentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mSubscriptionsViewBinder.init(paramContext, this.mDfeApi, this.mNavigationManager, FinskyApp.get().getLibraries());
  }

  private void bind(Bundle paramBundle)
  {
    resetViewState();
    AppActionAnalyzer localAppActionAnalyzer = new AppActionAnalyzer(this.mDocId, this.mAppStates, this.mLibraries);
    showButtons(localAppActionAnalyzer);
    bindPermissions(this.mFragment.getFragmentManager(), paramBundle);
    bindSubscriptions(paramBundle);
    bindAvailabilityRestrictions();
    bindAutoUpdate(this.mFragment);
    bindButtons(localAppActionAnalyzer);
    bindHeader();
  }

  private void bindAutoUpdate(Fragment paramFragment)
  {
    ((AutoUpdateSection)findViewById(2131230745)).bind(this.mDocId, paramFragment, this.mLibraries, this.mAppStates, this.mInstaller);
  }

  private void bindAvailabilityRestrictions()
  {
    ((WarningMessageSection)findViewById(2131230910)).bind(this.mDocument, null, FinskyApp.get().getToc(), this.mLibraries, this.mDfeApi.getAccount());
  }

  private void bindButtons(final AppActionAnalyzer paramAppActionAnalyzer)
  {
    findViewById(2131230900).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAppActionAnalyzer.isDisabled)
          DocumentView.this.mHandler.enable();
        while (true)
        {
          return;
          DocumentView.this.mHandler.launch();
        }
      }
    });
    findViewById(2131230901).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DocumentView.this.mHandler.update();
      }
    });
    findViewById(2131230904).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DocumentView.this.mHandler.install();
      }
    });
    findViewById(2131230979).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DocumentView.this.mHandler.refund(paramAppActionAnalyzer.refundAccount);
      }
    });
    findViewById(2131230902).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DocumentView.this.mHandler.uninstall(DocumentView.this.mIsSystemPackage, true, DocumentView.this.mHasSubscriptions);
      }
    });
  }

  private void bindHeader()
  {
    ((TextView)findViewById(2131230957)).setText(this.mDocument.getTitle());
    ((TextView)findViewById(2131230958)).setText(this.mDocument.getCreator().toUpperCase());
    findViewById(2131230879).setBackgroundColor(CorpusResourceUtils.getBackendHintColor(getContext(), 3));
    ((DocImageView)findViewById(2131230909)).bind(this.mDocument, this.mBitmapLoader);
    findViewById(2131230978).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DocumentView.this.mHandler.viewDetails();
      }
    });
  }

  private void bindPermissions(FragmentManager paramFragmentManager, Bundle paramBundle)
  {
    DocDetails.AppDetails localAppDetails = this.mDocument.getAppDetails();
    if (localAppDetails == null);
    while (true)
    {
      return;
      ArrayList localArrayList = Lists.newArrayList();
      Iterator localIterator = localAppDetails.getPermissionList().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          localArrayList.add(FinskyApp.get().getPackageManager().getPermissionInfo(str, 0));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
        }
      }
      ((AppSecurityPermissions)findViewById(2131230980)).bindInfo(paramFragmentManager, this.mDocId, localArrayList, paramBundle);
    }
  }

  private void bindSubscriptions(Bundle paramBundle)
  {
    SubscriptionsSection localSubscriptionsSection = (SubscriptionsSection)findViewById(2131230911);
    if (localSubscriptionsSection != null)
      this.mSubscriptionsViewBinder.bind(this.mFragment, localSubscriptionsSection, this.mDocument, 2130968732, paramBundle);
  }

  private void resetViewState()
  {
    findViewById(2131230920).setVisibility(8);
    findViewById(2131230900).setVisibility(8);
    findViewById(2131230979).setVisibility(8);
    findViewById(2131230902).setVisibility(8);
    findViewById(2131230901).setVisibility(8);
    findViewById(2131230904).setVisibility(8);
  }

  private void showButtons(AppActionAnalyzer paramAppActionAnalyzer)
  {
    if (FinskyApp.get().getInstaller().getState(this.mDocId) != Installer.InstallerState.NOT_TRACKED);
    while (true)
    {
      return;
      findViewById(2131230920).setVisibility(0);
      this.mIsSystemPackage = paramAppActionAnalyzer.isInstalledSystemApp;
      label58: label116: Button localButton2;
      if (paramAppActionAnalyzer.isRefundable)
      {
        findViewById(2131230979).setVisibility(0);
        if ((LibraryUtils.isAvailable(this.mDocument, FinskyApp.get().getToc(), this.mLibraries)) && (!paramAppActionAnalyzer.isDisabled))
        {
          if ((paramAppActionAnalyzer.installedVersion < 0) || (paramAppActionAnalyzer.installedVersion >= this.mDocument.getVersionCode()))
            break label249;
          findViewById(2131230901).setVisibility(0);
        }
        if (paramAppActionAnalyzer.isLaunchable)
        {
          localButton2 = (Button)findViewById(2131230900);
          if (!paramAppActionAnalyzer.isContinueLaunch)
            break label269;
          localButton2.setText(2131165488);
        }
      }
      View localView;
      while (true)
      {
        localButton2.setVisibility(0);
        if (paramAppActionAnalyzer.isDisabled)
        {
          Button localButton1 = (Button)findViewById(2131230900);
          localButton1.setText(2131165490);
          localButton1.setVisibility(0);
        }
        ViewGroup localViewGroup = (ViewGroup)findViewById(2131230920);
        for (int i = 0; i < localViewGroup.getChildCount(); i++)
        {
          localView = localViewGroup.getChildAt(i);
          if (localView.getVisibility() == 0)
            break label280;
        }
        break;
        if (!paramAppActionAnalyzer.isUninstallable)
          break label58;
        findViewById(2131230902).setVisibility(0);
        break label58;
        label249: if (paramAppActionAnalyzer.isInstalled)
          break label116;
        findViewById(2131230904).setVisibility(0);
        break label116;
        label269: localButton2.setText(2131165487);
      }
      label280: localView.requestFocus();
    }
  }

  public void bind(Fragment paramFragment, Document paramDocument, DocumentActionHandler paramDocumentActionHandler, Bundle paramBundle, NavigationManager paramNavigationManager)
  {
    this.mDocument = paramDocument;
    this.mHandler = paramDocumentActionHandler;
    this.mIsSystemPackage = false;
    this.mHasSubscriptions = DocUtils.hasAutoRenewingSubscriptions(this.mLibraries, paramDocument);
    this.mDocId = paramDocument.getDocId();
    this.mFragment = paramFragment;
    this.mNavigationManager = paramNavigationManager;
    if (this.mDetails != null)
    {
      this.mDetails.removeDataChangedListener(this);
      this.mDetails.removeErrorListener(this);
    }
    this.mDetails = new DfeDetails(this.mDfeApi, this.mDocument.getDetailsUrl());
    this.mDetails.addDataChangedListener(this);
    this.mDetails.addErrorListener(this);
    bind(paramBundle);
    if (this.mInstallerListener != null)
      this.mInstaller.removeListener(this.mInstallerListener);
    this.mInstallerListener = new WatchedInstallerListener(this.mDocId);
    this.mInstaller.addListener(this.mInstallerListener);
  }

  public void onDataChanged()
  {
    this.mDocument = this.mDetails.getDocument();
    this.mHasSubscriptions = DocUtils.hasAutoRenewingSubscriptions(this.mLibraries, this.mDocument);
    bind(new Bundle());
  }

  protected void onDetachedFromWindow()
  {
    this.mSubscriptionsViewBinder.onDestroyView();
    if (this.mInstallerListener != null)
      this.mInstaller.removeListener(this.mInstallerListener);
    this.mUiHandler.removeCallbacks(this.mRebindRunnable);
    super.onDetachedFromWindow();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 3)
      this.mSubscriptionsViewBinder.onNegativeClick(paramInt, paramBundle);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 3)
      this.mSubscriptionsViewBinder.onPositiveClick(paramInt, paramBundle);
  }

  public static abstract interface DocumentActionHandler
  {
    public abstract void enable();

    public abstract void install();

    public abstract void launch();

    public abstract void refund(String paramString);

    public abstract void uninstall(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);

    public abstract void update();

    public abstract void viewDetails();
  }

  private final class WatchedInstallerListener
    implements InstallerListener
  {
    private final String mWatchedDocId;

    public WatchedInstallerListener(String arg2)
    {
      Object localObject;
      this.mWatchedDocId = localObject;
    }

    public void onInstallPackageEvent(String paramString, InstallerListener.InstallerPackageEvent paramInstallerPackageEvent, int paramInt)
    {
      if (paramString.equals(this.mWatchedDocId))
        DocumentView.this.mUiHandler.post(DocumentView.this.mRebindRunnable);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.DocumentView
 * JD-Core Version:    0.6.2
 */
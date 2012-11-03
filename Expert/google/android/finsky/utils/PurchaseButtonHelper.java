package com.google.android.finsky.utils;

import android.accounts.Account;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;

public class PurchaseButtonHelper
{
  private static String getManualUpdateReferrer(Document paramDocument)
  {
    return "manualUpdate?doc=" + paramDocument.getAppDetails().getPackageName();
  }

  public static boolean hasUpdateAvailable(AppActionAnalyzer paramAppActionAnalyzer, Document paramDocument)
  {
    if (((paramAppActionAnalyzer.isInstalledOwnedPackage) || (paramAppActionAnalyzer.isInstalledSystemApp)) && (paramDocument.getAppDetails().hasVersionCode()) && (paramDocument.getAppDetails().getVersionCode() > paramAppActionAnalyzer.installedVersion));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void setPurchaseOrOpenClickListener(Document paramDocument, TextView paramTextView, Account paramAccount, NavigationManager paramNavigationManager, String paramString)
  {
    Libraries localLibraries = FinskyApp.get().getLibraries();
    Account localAccount1 = FinskyApp.get().getCurrentAccount();
    Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(paramDocument, localLibraries, localAccount1);
    int i = DocUtils.getListingOffer(paramDocument, FinskyApp.get().getToc(), FinskyApp.get().getLibraries().getAccountLibrary(localAccount1)).getOfferType();
    int j;
    boolean bool;
    AppActionAnalyzer localAppActionAnalyzer;
    View.OnClickListener localOnClickListener;
    if (localAccount2 != null)
    {
      j = 1;
      bool = true;
      localAppActionAnalyzer = null;
      if (paramDocument.getDocumentType() == 1)
      {
        localAppActionAnalyzer = new AppActionAnalyzer(paramDocument.getAppDetails().getPackageName(), FinskyApp.get().getAppStates(), localLibraries);
        bool = localAppActionAnalyzer.isInstalled;
      }
      localOnClickListener = null;
      if (j != 0)
        break label140;
      localOnClickListener = paramNavigationManager.getBuyImmediateClickListener(paramAccount, paramDocument, i, paramString, null, null);
    }
    while (true)
    {
      label127: paramTextView.setOnClickListener(localOnClickListener);
      while (true)
      {
        return;
        j = 0;
        break;
        label140: if (!bool)
        {
          localOnClickListener = paramNavigationManager.getBuyImmediateClickListener(localAccount2, paramDocument, i, paramString, null, null);
          break label127;
        }
        if (localAppActionAnalyzer == null)
          break label210;
        if (!localAppActionAnalyzer.isDisabled)
          break label184;
        paramTextView.setOnClickListener(null);
      }
      label184: if (hasUpdateAvailable(localAppActionAnalyzer, paramDocument))
        localOnClickListener = paramNavigationManager.getBuyImmediateClickListener(localAccount2, paramDocument, i, getManualUpdateReferrer(paramDocument), null, null);
      label210: if (localOnClickListener == null)
        localOnClickListener = paramNavigationManager.getOpenClickListener(paramDocument, localAccount2);
    }
  }

  private static void styleFromOffer(TextView paramTextView, Common.Offer paramOffer)
  {
    int j;
    if (paramOffer != null)
    {
      int i = paramOffer.getOfferType();
      if ((i == 4) || (i == 7))
      {
        j = 1;
        if (j == 0)
          break label64;
        FinskyApp localFinskyApp = FinskyApp.get();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramOffer.getFormattedAmount();
        paramTextView.setText(localFinskyApp.getString(2131165474, arrayOfObject));
      }
    }
    while (true)
    {
      return;
      j = 0;
      break;
      label64: paramTextView.setText(paramOffer.getFormattedAmount());
      continue;
      paramTextView.setVisibility(8);
    }
  }

  public static void stylePurchaseButton(Document paramDocument, boolean paramBoolean, TextView paramTextView)
  {
    if (paramBoolean)
      paramTextView.setTextColor(CorpusResourceUtils.getBackendForegroundColor(paramTextView.getContext(), paramDocument.getBackend()));
    paramTextView.setVisibility(0);
    Account localAccount = FinskyApp.get().getCurrentAccount();
    Installer localInstaller = FinskyApp.get().getInstaller();
    AccountLibrary localAccountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(localAccount);
    Common.Offer localOffer = DocUtils.getListingOffer(paramDocument, FinskyApp.get().getToc(), localAccountLibrary);
    Libraries localLibraries = FinskyApp.get().getLibraries();
    int i;
    if (LibraryUtils.getOwnerWithCurrentAccount(paramDocument, localLibraries, FinskyApp.get().getCurrentAccount()) != null)
    {
      i = 1;
      switch (paramDocument.getBackend())
      {
      case 5:
      default:
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramDocument.getBackend());
        FinskyLog.wtf("Unsupported backend: %d", arrayOfObject);
        styleFromOffer(paramTextView, localOffer);
      case 3:
      case 6:
      case 1:
      case 2:
      case 4:
      }
    }
    while (true)
    {
      return;
      i = 0;
      break;
      if (paramDocument.getDocumentType() == 1)
      {
        DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
        AppActionAnalyzer localAppActionAnalyzer = new AppActionAnalyzer(localAppDetails.getPackageName(), FinskyApp.get().getAppStates(), localLibraries);
        if (localAppActionAnalyzer.isInstalled)
        {
          if (localAppActionAnalyzer.isDisabled)
            paramTextView.setText(2131165424);
          else if (hasUpdateAvailable(localAppActionAnalyzer, paramDocument))
            paramTextView.setText(2131165427);
          else
            paramTextView.setText(2131165423);
        }
        else if (localInstaller.getState(localAppDetails.getPackageName()) != Installer.InstallerState.NOT_TRACKED)
          paramTextView.setText(2131165597);
      }
      else if ((i != 0) && (localOffer != null) && (localOffer.getMicros() > 0L))
      {
        paramTextView.setText(2131165425);
      }
      else
      {
        styleFromOffer(paramTextView, localOffer);
        continue;
        if ((paramDocument.hasSubscriptions()) && (LibraryUtils.getOwnerWithCurrentAccount(paramDocument.getSubscriptionsList(), localLibraries, FinskyApp.get().getCurrentAccount()) != null))
        {
          paramTextView.setText(2131165428);
        }
        else
        {
          styleFromOffer(paramTextView, localOffer);
          continue;
          if (i != 0)
          {
            paramTextView.setText(2131165425);
          }
          else
          {
            styleFromOffer(paramTextView, localOffer);
            continue;
            if (i != 0)
            {
              if ((LibraryUtils.isOfferOwned(paramDocument, localAccountLibrary, 7)) || (LibraryUtils.isOfferOwned(paramDocument, localAccountLibrary, 1)))
                paramTextView.setText(2131165425);
              else
                paramTextView.setText(2131165426);
            }
            else
              styleFromOffer(paramTextView, localOffer);
          }
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.PurchaseButtonHelper
 * JD-Core Version:    0.6.2
 */
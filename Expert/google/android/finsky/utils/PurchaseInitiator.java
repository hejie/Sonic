package com.google.android.finsky.utils;

import android.accounts.Account;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse;
import com.google.android.finsky.remoting.protos.Buy.PurchaseNotificationResponse;
import com.google.android.finsky.remoting.protos.Buy.PurchaseStatusResponse;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;

public class PurchaseInitiator
{
  private static Response.Listener<Buy.BuyResponse> createFreePurchaseListener(Account paramAccount, final Document paramDocument, final String paramString1, final String paramString2, final String paramString3)
  {
    return new Response.Listener()
    {
      public void onResponse(Buy.BuyResponse paramAnonymousBuyResponse)
      {
        Buy.PurchaseNotificationResponse localPurchaseNotificationResponse;
        if (paramAnonymousBuyResponse.hasPurchaseResponse())
        {
          localPurchaseNotificationResponse = paramAnonymousBuyResponse.getPurchaseResponse();
          if (localPurchaseNotificationResponse.getStatus() == 0)
            if (paramAnonymousBuyResponse.hasPurchaseStatusResponse())
              PurchaseInitiator.processPurchaseStatusResponse(this.val$account, paramDocument, paramString1, paramString2, paramString3, paramAnonymousBuyResponse.getPurchaseStatusResponse(), true, "free_purchase");
        }
        while (true)
        {
          return;
          FinskyLog.w("Expected PurchaseStatusResponse.", new Object[0]);
          continue;
          String str1 = FinskyApp.get().getString(2131165442);
          String str2 = localPurchaseNotificationResponse.getLocalizedErrorMessage();
          FinskyApp.get().getNotifier().showPurchaseErrorMessage(str1, str2, str2, paramDocument.getDocId(), paramDocument.getDetailsUrl());
          continue;
          FinskyLog.w("Expected PurchaseResponse.", new Object[0]);
        }
      }
    };
  }

  private static Response.ErrorListener createPurchaseErrorListener(Document paramDocument)
  {
    return new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        String str1 = FinskyApp.get().getString(2131165442);
        String str2 = ErrorStrings.get(FinskyApp.get(), paramAnonymousVolleyError);
        FinskyApp.get().getNotifier().showPurchaseErrorMessage(str1, str2, str2, this.val$doc.getDocId(), this.val$doc.getDetailsUrl());
      }
    };
  }

  public static void initiateDownload(Account paramAccount, Document paramDocument, String paramString1, String paramString2, AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData)
  {
    if (paramDocument.getAppDetails() == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramDocument.getDocId();
      FinskyLog.wtf("Document does not contain AppDetails, cannot download: %s", arrayOfObject);
    }
    FinskyApp.get().getInstaller().requestInstall(paramDocument.getAppDetails().getPackageName(), paramDocument.getAppDetails().getVersionCode(), paramAndroidAppDeliveryData, paramAccount.name, paramString1, paramString2, paramDocument.getTitle(), false, "single_install");
  }

  public static void makeFreePurchase(Account paramAccount, Document paramDocument, int paramInt, String paramString1, String paramString2, String paramString3)
  {
    FinskyApp.get().getDfeApi().makePurchase(paramDocument, paramInt, null, null, createFreePurchaseListener(paramAccount, paramDocument, paramString1, paramString2, paramString3), createPurchaseErrorListener(paramDocument));
  }

  public static void processPurchaseStatusResponse(Account paramAccount, Document paramDocument, String paramString1, String paramString2, String paramString3, Buy.PurchaseStatusResponse paramPurchaseStatusResponse, boolean paramBoolean, String paramString4)
  {
    if (paramPurchaseStatusResponse.hasLibraryUpdate())
      FinskyApp.get().getLibraryReplicators().applyLibraryUpdate(paramAccount, paramPurchaseStatusResponse.getLibraryUpdate(), paramString4);
    int i = paramPurchaseStatusResponse.getStatus();
    if ((i == 1) && (paramBoolean) && (paramDocument.getDocumentType() == 1))
    {
      if (!paramPurchaseStatusResponse.hasAppDeliveryData())
        break label152;
      initiateDownload(paramAccount, paramDocument, paramString1, paramString3, paramPurchaseStatusResponse.getAppDeliveryData());
    }
    while (true)
    {
      StringBuilder localStringBuilder = new StringBuilder().append("purchaseFinished?doc=").append(paramDocument.getDocId());
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(i);
      arrayOfObject1[1] = Boolean.toString(paramBoolean);
      String str = String.format("&status=%d&initiateAppDownload=%s", arrayOfObject1);
      FinskyApp.get().getAnalytics().logPageView(paramString1, paramString2, str);
      return;
      label152: FinskyApp.get().getAppStates().getInstallerDataStore().setReferrer(paramDocument.getBackendDocId(), paramString1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramDocument.getDocId();
      FinskyLog.d("No delivery data for %s, waiting for notification.", arrayOfObject2);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.PurchaseInitiator
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.accounts.Account;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.AsyncAuthenticator.Listener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.download.Download;
import com.google.android.finsky.download.DownloadImpl;
import com.google.android.finsky.download.DownloadProgress;
import com.google.android.finsky.download.DownloadQueue;
import com.google.android.finsky.download.DownloadQueueListener;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;

public class SelfUpdateScheduler
  implements DownloadQueueListener
{
  private final DownloadQueue mDownloadQueue;
  private int mMarketVersion;
  private ApplicationDismissedDeferrer mOnAppExitDeferrer;
  private Download mUpdateDownload = null;
  private boolean mUpdateInProgress = false;

  public SelfUpdateScheduler(DownloadQueue paramDownloadQueue, int paramInt)
  {
    this.mDownloadQueue = paramDownloadQueue;
    this.mMarketVersion = paramInt;
  }

  public void checkForSelfUpdate(final VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
  {
    if (this.mUpdateInProgress)
      FinskyLog.d("Skipping self-update check as there is an update already queued.", new Object[0]);
    while (true)
    {
      return;
      int i = paramGetMarketMetadataResponseProto.getLatestClientVersionCode();
      if (this.mMarketVersion >= i)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(this.mMarketVersion);
        arrayOfObject[1] = Integer.valueOf(i);
        FinskyLog.d("Skipping self-update. Local Version [%d] >= Server Version [%d]", arrayOfObject);
      }
      else if (TextUtils.isEmpty(paramGetMarketMetadataResponseProto.getLatestClientUrl()))
      {
        FinskyLog.d("Skipping self-update. No upgrade URL specified.", new Object[0]);
      }
      else
      {
        this.mUpdateInProgress = true;
        Account localAccount = new Account(FinskyApp.get().getCurrentAccountName(), "com.google");
        new AsyncAuthenticator(new AndroidAuthenticator(FinskyApp.get(), localAccount, (String)G.vendingSecureAuthTokenType.get())).getToken(new AsyncAuthenticator.Listener()
        {
          public void onAuthTokenReceived(String paramAnonymousString)
          {
            DownloadImpl localDownloadImpl = new DownloadImpl(paramGetMarketMetadataResponseProto.getLatestClientUrl(), "", null, 0, "ANDROIDSECURE", paramAnonymousString, null, -1L, -1L, null, false, true);
            SelfUpdateScheduler.access$002(SelfUpdateScheduler.this, localDownloadImpl);
            SelfUpdateScheduler.this.mDownloadQueue.addListener(SelfUpdateScheduler.this);
            SelfUpdateScheduler.this.mDownloadQueue.add(localDownloadImpl);
            FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadQueued", "self-update-download", null);
            FinskyApp.get().getEventLogger().logTag("selfUpdateDownloadedQueued", new Object[0]);
          }

          public void onError(AuthFailureError paramAnonymousAuthFailureError)
          {
            FinskyLog.d("Exception occured while getting auth token.", new Object[] { paramAnonymousAuthFailureError });
          }
        }
        , null);
      }
    }
  }

  public void onCancel(Download paramDownload)
  {
  }

  public void onComplete(final Download paramDownload)
  {
    if (paramDownload != this.mUpdateDownload)
      FinskyLog.d("Self-update ignoring completed download " + paramDownload, new Object[0]);
    while (true)
    {
      return;
      FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadComplete", "self-update-download", null);
      FinskyApp.get().getEventLogger().logTag("selfUpdateDownloaded", new Object[0]);
      this.mUpdateDownload = null;
      if (this.mOnAppExitDeferrer != null)
      {
        FinskyLog.d("Self-update package Uri was already assigned!", new Object[0]);
      }
      else
      {
        FinskyLog.d("Self-update ready to be installed, waiting for market to close.", new Object[0]);
        this.mOnAppExitDeferrer = new ApplicationDismissedDeferrer(FinskyApp.get());
        this.mOnAppExitDeferrer.runOnApplicationClose(new Runnable()
        {
          public void run()
          {
            PackageManagerHelper.InstallPackageListener local1 = new PackageManagerHelper.InstallPackageListener()
            {
              public void installCompleted(boolean paramAnonymous2Boolean, String paramAnonymous2String)
              {
                FinskyApp.get().getAnalytics().logTagAndPackage("install.installerFinished", "selfupdate", paramAnonymous2String);
                FinskyApp.get().getEventLogger().logTag("selfUpdateInstallDone", new Object[] { "r", paramAnonymous2String });
              }
            };
            PackageManagerHelper.installPackage(paramDownload.getContentUri(), null, -1L, null, false, local1, false, false, "", null);
          }
        }
        , 10000);
      }
    }
  }

  public void onError(Download paramDownload, int paramInt)
  {
    if (paramDownload == this.mUpdateDownload)
    {
      FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadError", "self-update-download", String.valueOf(paramInt));
      FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = "r";
      arrayOfObject1[1] = String.valueOf(paramInt);
      localFinskyEventLog.logTag("selfUpdateDownloadError", arrayOfObject1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      FinskyLog.e("Self-update failed because of HTTP error code: %d", arrayOfObject2);
    }
  }

  public void onNotificationClicked(Download paramDownload)
  {
  }

  public void onProgress(Download paramDownload, DownloadProgress paramDownloadProgress)
  {
  }

  public void onStart(Download paramDownload)
  {
    FinskyApp.get().getAnalytics().logTagAndPackage("install.downloadStarted", "self-update-download", null);
    FinskyApp.get().getEventLogger().logTag("selfUpdateDownloadStart", new Object[0]);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.SelfUpdateScheduler
 * JD-Core Version:    0.6.2
 */
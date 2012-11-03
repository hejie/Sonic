package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;

public class UninstallRefundTracker
  implements PackageMonitorReceiver.PackageStatusListener
{
  private final AppStates mAppStates;
  private final Context mContext;

  public UninstallRefundTracker(Context paramContext, AppStates paramAppStates, PackageMonitorReceiver paramPackageMonitorReceiver)
  {
    this.mContext = paramContext;
    this.mAppStates = paramAppStates;
    paramPackageMonitorReceiver.attach(this);
  }

  private void refundIfNecessary(String paramString)
  {
    AppActionAnalyzer localAppActionAnalyzer = new AppActionAnalyzer(paramString, FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries());
    if (!localAppActionAnalyzer.isRefundable);
    while (true)
    {
      return;
      AppSupport.silentRefund(paramString, localAppActionAnalyzer.refundAccount, new AppSupport.RefundListener()
      {
        public void onRefundComplete(AppSupport.RefundResult paramAnonymousRefundResult, String paramAnonymousString)
        {
          switch (UninstallRefundTracker.3.$SwitchMap$com$google$android$finsky$utils$AppSupport$RefundResult[paramAnonymousRefundResult.ordinal()])
          {
          default:
            FinskyLog.d("Refund failed for %s: %s", new Object[] { paramAnonymousString, paramAnonymousRefundResult });
          case 1:
          }
          while (true)
          {
            return;
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
              public void run()
              {
                Toast.makeText(UninstallRefundTracker.this.mContext, 2131165609, 1).show();
              }
            });
          }
        }

        public void onRefundStart()
        {
        }
      });
    }
  }

  public void onPackageAdded(String paramString)
  {
  }

  public void onPackageAvailabilityChanged(String[] paramArrayOfString, boolean paramBoolean)
  {
  }

  public void onPackageChanged(String paramString)
  {
  }

  public void onPackageFirstLaunch(String paramString)
  {
  }

  public void onPackageRemoved(final String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      this.mAppStates.load(new Runnable()
      {
        public void run()
        {
          UninstallRefundTracker.this.refundIfNecessary(paramString);
        }
      });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.UninstallRefundTracker
 * JD-Core Version:    0.6.2
 */
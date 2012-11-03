package com.google.android.finsky.utils;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.PackageMonitorReceiver;
import com.google.android.finsky.receivers.PackageMonitorReceiver.PackageStatusListener;

public class UninstallSubscriptionsTracker
  implements PackageMonitorReceiver.PackageStatusListener
{
  private final Context mContext;
  private DfeDetails mDfeDetails;
  private final Libraries mLibraries;

  public UninstallSubscriptionsTracker(Context paramContext, Libraries paramLibraries, PackageMonitorReceiver paramPackageMonitorReceiver)
  {
    this.mLibraries = paramLibraries;
    this.mContext = paramContext;
    paramPackageMonitorReceiver.attach(this);
  }

  private void checkAndNotifyActiveSubscriptions(final String paramString)
  {
    if (DocUtils.packageHasAutoRenewingSubscriptions(this.mLibraries, paramString))
    {
      this.mDfeDetails = new DfeDetails(FinskyApp.get().getDfeApi(), DfeUtils.createDetailsUrlFromId(paramString));
      this.mDfeDetails.addDataChangedListener(new OnDataChangedListener()
      {
        public void onDataChanged()
        {
          if (UninstallSubscriptionsTracker.this.mDfeDetails.getDocument() != null)
          {
            Document localDocument = UninstallSubscriptionsTracker.this.mDfeDetails.getDocument();
            Notifier localNotifier = FinskyApp.get().getNotifier();
            Resources localResources = UninstallSubscriptionsTracker.this.mContext.getResources();
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localDocument.getTitle();
            localNotifier.showSubscriptionsWarningMessage(localResources.getString(2131165454, arrayOfObject), paramString, UninstallSubscriptionsTracker.this.mContext.getResources().getString(2131165455));
          }
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
      this.mLibraries.load(new Runnable()
      {
        public void run()
        {
          UninstallSubscriptionsTracker.this.checkAndNotifyActiveSubscriptions(paramString);
        }
      });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.UninstallSubscriptionsTracker
 * JD-Core Version:    0.6.2
 */
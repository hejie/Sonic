package com.google.android.finsky;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeNotificationManager;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.billing.iab.MarketBillingService;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.AckNotification.AckNotificationResponse;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.remoting.protos.Common.Docid;
import com.google.android.finsky.remoting.protos.Notifications.AndroidAppNotificationData;
import com.google.android.finsky.remoting.protos.Notifications.InAppNotificationData;
import com.google.android.finsky.remoting.protos.Notifications.LibraryDirtyData;
import com.google.android.finsky.remoting.protos.Notifications.Notification;
import com.google.android.finsky.remoting.protos.Notifications.PurchaseDeclinedData;
import com.google.android.finsky.remoting.protos.Notifications.PurchaseRemovalData;
import com.google.android.finsky.remoting.protos.Notifications.UserNotificationData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Notifier;
import java.util.Iterator;
import java.util.List;

public class DfeNotificationManagerImpl
  implements DfeNotificationManager
{
  private final Accounts mAccounts;
  private final AppStates mAppStates;
  private final Context mContext;
  private final List<String> mHandledNotifications = Lists.newArrayList();
  private final Installer mInstaller;
  private final LibraryReplicators mLibraryReplicators;
  private final Notifier mNotifier;
  private final List<String> mPendingAcks = Lists.newArrayList();

  public DfeNotificationManagerImpl(Context paramContext, Installer paramInstaller, Notifier paramNotifier, AppStates paramAppStates, LibraryReplicators paramLibraryReplicators, Accounts paramAccounts)
  {
    this.mInstaller = paramInstaller;
    this.mNotifier = paramNotifier;
    this.mContext = paramContext;
    this.mAppStates = paramAppStates;
    this.mLibraryReplicators = paramLibraryReplicators;
    this.mAccounts = paramAccounts;
    loadPendingAcks();
  }

  private void ackNotification(final String paramString)
  {
    FinskyApp.get().getDfeApi().ackNotification(paramString, new Response.Listener()
    {
      public void onResponse(AckNotification.AckNotificationResponse paramAnonymousAckNotificationResponse)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        FinskyLog.d("Notification [%s] successfully ack'd.", arrayOfObject);
        DfeNotificationManagerImpl.this.mPendingAcks.remove(paramString);
        DfeNotificationManagerImpl.this.savePendingAcks();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        FinskyLog.d("Error acking notification [%s]", arrayOfObject);
      }
    });
  }

  private void ackPendingNotifications(String paramString)
  {
    ackNotification(paramString);
    Iterator localIterator = this.mPendingAcks.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!str.equals(paramString))
        ackNotification(str);
    }
  }

  private void handleCheckPromoOffersNotification(Notifications.Notification paramNotification)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = FinskyLog.scrubPii(paramNotification.getUserEmail());
    FinskyLog.d("Received CheckPromoOffers notification for account %s", arrayOfObject);
    FinskyPreferences.checkPromoOffers.get(paramNotification.getUserEmail()).put(Boolean.valueOf(true));
  }

  private void handleInAppNotification(Notifications.Notification paramNotification)
  {
    MarketBillingService.sendNotify(this.mContext, paramNotification.getDocid().getBackendDocid(), paramNotification.getInAppNotificationData().getInAppNotificationId());
  }

  private void handleLibraryDirtyNotification(Notifications.Notification paramNotification)
  {
    if (!paramNotification.hasLibraryDirtyData())
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramNotification.getNotificationId();
      FinskyLog.e("Received LibraryDirty notification without LibraryDirtyData: id=%s", arrayOfObject2);
    }
    Account localAccount;
    while (true)
    {
      return;
      localAccount = AccountHandler.findAccount(paramNotification.getUserEmail(), this.mContext);
      if (localAccount != null)
        break;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramNotification.getNotificationId();
      arrayOfObject1[1] = FinskyLog.scrubPii(paramNotification.getUserEmail());
      FinskyLog.e("Received LibraryDirty notification for invalid account: id=%s, account=%s", arrayOfObject1);
    }
    String[] arrayOfString = new String[1];
    if (paramNotification.getLibraryDirtyData().hasLibraryId())
      arrayOfString[0] = paramNotification.getLibraryDirtyData().getLibraryId();
    while (true)
    {
      this.mLibraryReplicators.replicateAccount(localAccount, arrayOfString, null, "notification-" + paramNotification.getNotificationId());
      break;
      arrayOfString[0] = AccountLibrary.getLibraryIdFromBackend(paramNotification.getLibraryDirtyData().getBackend());
    }
  }

  private void handleNotification(Notifications.Notification paramNotification)
  {
    String str1 = paramNotification.getNotificationId();
    if (this.mHandledNotifications.contains(str1))
    {
      FinskyLog.d("Notification [%s] ignored, already handled.", new Object[] { str1 });
      ackPendingNotifications(str1);
    }
    while (true)
    {
      return;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(paramNotification.getNotificationType());
      arrayOfObject1[1] = str1;
      FinskyLog.d("Handling notification type=[%s], id=[%s]", arrayOfObject1);
      if (paramNotification.hasLibraryUpdate())
      {
        String str2 = paramNotification.getUserEmail();
        Account localAccount = this.mAccounts.getAccount(str2);
        if (localAccount != null)
        {
          FinskyLog.d("Processing notification library update.", new Object[0]);
          this.mLibraryReplicators.applyLibraryUpdate(localAccount, paramNotification.getLibraryUpdate(), makeReplicatorDebugTag(paramNotification));
        }
      }
      else
      {
        switch (paramNotification.getNotificationType())
        {
        default:
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(paramNotification.getNotificationType());
          FinskyLog.e("Unhandled notification type [%s]", arrayOfObject2);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      }
      while (true)
      {
        this.mHandledNotifications.add(str1);
        while (this.mPendingAcks.size() >= 10)
          this.mPendingAcks.remove(0);
        FinskyLog.d("Could not process library update for unknown account.", new Object[0]);
        break;
        handlePurchaseDeliveryNotification(paramNotification);
        continue;
        handlePurchaseRemovalNotification(paramNotification);
        continue;
        handlePurchaseDeclinedNotification(paramNotification);
        continue;
        handleUserNotification(paramNotification);
        continue;
        handleInAppNotification(paramNotification);
        continue;
        handleLibraryDirtyNotification(paramNotification);
        continue;
        handleCheckPromoOffersNotification(paramNotification);
      }
      this.mPendingAcks.add(str1);
      savePendingAcks();
      ackPendingNotifications(str1);
    }
  }

  private void handlePurchaseDeclinedNotification(Notifications.Notification paramNotification)
  {
    int i = paramNotification.getPurchaseDeclinedData().getReason();
    String str = paramNotification.getDocid().getBackendDocid();
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = str;
    arrayOfObject1[1] = Integer.valueOf(i);
    FinskyLog.d("Received PURCHASE_DECLINED tickle for %s reason=%d", arrayOfObject1);
    FinskyApp.get().getAnalytics().logTagAndPackage("install.declinedTickle", str, String.valueOf(i));
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject2 = new Object[4];
    arrayOfObject2[0] = "cidi";
    arrayOfObject2[1] = str;
    arrayOfObject2[2] = "r";
    arrayOfObject2[3] = String.valueOf(i);
    localFinskyEventLog.logTag("install.declinedTickle", arrayOfObject2);
  }

  private void handlePurchaseDeliveryNotification(Notifications.Notification paramNotification)
  {
    Notifications.AndroidAppNotificationData localAndroidAppNotificationData = paramNotification.getAppData();
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = paramNotification.getAppDeliveryData();
    if (!paramNotification.hasAppData())
      FinskyLog.d("Ignoring PurchaseDeliveryNotification because AppData was null.", new Object[0]);
    while (true)
    {
      return;
      if (!paramNotification.hasAppDeliveryData())
      {
        FinskyLog.d("Ignoring PurchaseDeliveryNotification because delivery data was null", new Object[0]);
      }
      else if (localAndroidAppDeliveryData.getDownloadAuthCookieCount() < 1)
      {
        FinskyLog.d("Ignoring PurchaseDeliveryNotification because no cookies were provided.", new Object[0]);
      }
      else
      {
        String str1 = paramNotification.getDocid().getBackendDocid();
        if (!localAndroidAppDeliveryData.getServerInitiated())
        {
          FinskyLog.d("Ignoring PurchaseDeliveryNotification with !server_initiated: pkg=%s", new Object[] { str1 });
        }
        else
        {
          String str2 = paramNotification.getUserEmail();
          FinskyApp.get().getAnalytics().logTagAndPackage("install.receiveTickle", str1, null);
          FinskyApp.get().getEventLogger().logTag("install.receiveTickle", new Object[] { "cidi", str1 });
          this.mInstaller.requestInstall(str1, localAndroidAppNotificationData.getVersionCode(), localAndroidAppDeliveryData, str2, null, null, paramNotification.getDocTitle(), false, "tickle");
        }
      }
    }
  }

  private void handlePurchaseRemovalNotification(Notifications.Notification paramNotification)
  {
    String str1 = paramNotification.getDocid().getBackendDocid();
    boolean bool;
    String str2;
    if ((paramNotification.hasPurchaseRemovalData()) && (paramNotification.getPurchaseRemovalData().getMalicious()))
    {
      bool = true;
      str2 = paramNotification.getDocTitle();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = Boolean.valueOf(bool);
      FinskyLog.d("Removing package '%s'. Malicious='%s'", arrayOfObject);
      FinskyApp.get().getAnalytics().logTagAndPackage("install.removeAppTickle", str1, null);
      FinskyApp.get().getEventLogger().logTag("install.removeAppTickle", new Object[] { "cidi", str1 });
      if (this.mAppStates.getPackageStateRepository().get(str1) != null)
      {
        if (!bool)
          break label154;
        this.mNotifier.showMaliciousAssetRemovedMessage(str2, str1);
      }
      label134: if (!bool)
        break label169;
      this.mInstaller.uninstallPackagesByUid(str1);
    }
    while (true)
    {
      return;
      bool = false;
      break;
      label154: this.mNotifier.showNormalAssetRemovedMessage(str2, str1);
      break label134;
      label169: this.mInstaller.uninstallAssetSilently(str1);
    }
  }

  private void handleUserNotification(Notifications.Notification paramNotification)
  {
    Notifications.UserNotificationData localUserNotificationData = paramNotification.getUserNotificationData();
    this.mNotifier.showMessage(localUserNotificationData.getNotificationTitle(), localUserNotificationData.getTickerText(), localUserNotificationData.getNotificationText());
  }

  private void loadPendingAcks()
  {
    String str = (String)FinskyPreferences.dfeNotificationPendingAcks.get();
    if (!TextUtils.isEmpty(str))
    {
      String[] arrayOfString = str.split(",");
      for (int i = 0; i < arrayOfString.length; i++)
      {
        this.mPendingAcks.add(arrayOfString[i]);
        this.mHandledNotifications.add(arrayOfString[i]);
      }
    }
  }

  private String makeReplicatorDebugTag(Notifications.Notification paramNotification)
  {
    return "notification (type=[" + paramNotification.getNotificationType() + "],id=[" + paramNotification.getNotificationId() + "])";
  }

  private void savePendingAcks()
  {
    if (this.mPendingAcks.isEmpty())
      FinskyPreferences.dfeNotificationPendingAcks.remove();
    while (true)
    {
      return;
      if (this.mPendingAcks.size() == 1)
      {
        FinskyPreferences.dfeNotificationPendingAcks.put(this.mPendingAcks.get(0));
      }
      else
      {
        String str = TextUtils.join(",", this.mPendingAcks);
        FinskyPreferences.dfeNotificationPendingAcks.put(str);
      }
    }
  }

  public void processNotification(final Notifications.Notification paramNotification)
  {
    if ((Looper.myLooper() == Looper.getMainLooper()) && (this.mAppStates.isLoaded()))
      handleNotification(paramNotification);
    while (true)
    {
      return;
      this.mAppStates.load(new Runnable()
      {
        public void run()
        {
          DfeNotificationManagerImpl.this.handleNotification(paramNotification);
        }
      });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.DfeNotificationManagerImpl
 * JD-Core Version:    0.6.2
 */
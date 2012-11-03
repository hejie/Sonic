package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Pair;
import com.android.vending.billing.IMarketBillingService.Stub;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.IabActivity;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.finsky.utils.Notifier;
import com.google.android.finsky.utils.Sets;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.AckNotificationsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.AckNotificationsResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppPurchaseInformationRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppPurchaseInformationResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppRestoreTransactionsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppRestoreTransactionsResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseResultProto;
import com.google.android.vending.remoting.protos.VendingProtos.SignatureHashProto;
import com.google.android.vending.remoting.protos.VendingProtos.SignedDataProto;
import com.google.android.vending.remoting.protos.VendingProtos.StatusBarNotificationProto;
import com.google.protobuf.micro.ByteStringMicro;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MarketBillingService extends Service
{
  private static final String[] EMPTY_STRING_ARRAY = new String[0];
  private static Random sRandom = new Random();
  private final IMarketBillingService.Stub mBinder = new Stub();
  BillingNotifier mNotifier = new BillingNotifier(this);
  PackageManager mPackageManager;
  UidProvider mUidProvider = new UidProvider();

  private String buildDocId(String paramString1, String paramString2, String paramString3)
  {
    return paramString2 + ":" + paramString3 + ":" + paramString1;
  }

  private Account getPreferredAccount(String paramString)
  {
    AppStates localAppStates = FinskyApp.get().getAppStates();
    localAppStates.blockingLoad();
    AppStates.AppState localAppState = localAppStates.getApp(paramString);
    Object localObject;
    if ((localAppState != null) && (localAppState.packageManagerState != null))
    {
      Libraries localLibraries = FinskyApp.get().getLibraries();
      localLibraries.blockingLoad();
      List localList = localLibraries.getAppOwners(paramString, localAppState.packageManagerState.certificateHashes);
      if (localList.size() > 0)
      {
        FinskyLog.d("%s: Account determined from library ownership.", new Object[] { paramString });
        localObject = (Account)localList.get(0);
      }
    }
    while (true)
    {
      return localObject;
      Account localAccount = AccountHandler.getFirstAccount(this);
      if (localAccount != null)
      {
        FinskyLog.d("%s: Account from first account.", new Object[] { paramString });
        localObject = localAccount;
      }
      else
      {
        FinskyLog.w("%s: No account found.", new Object[] { paramString });
        localObject = null;
      }
    }
  }

  private ResponseCode purchaseResultToResponseCode(VendingProtos.PurchaseResultProto paramPurchaseResultProto)
  {
    ResponseCode localResponseCode;
    switch (paramPurchaseResultProto.getResultCode())
    {
    default:
      localResponseCode = ResponseCode.RESULT_ERROR;
    case 0:
    case 9:
    case 7:
    }
    while (true)
    {
      return localResponseCode;
      localResponseCode = ResponseCode.RESULT_OK;
      continue;
      localResponseCode = ResponseCode.RESULT_DEVELOPER_ERROR;
      continue;
      localResponseCode = ResponseCode.RESULT_ITEM_UNAVAILABLE;
    }
  }

  public static boolean sendNotify(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = IntentUtils.createIntentForReceiver(paramContext.getPackageManager(), paramString1, new Intent("com.android.vending.billing.IN_APP_NOTIFY"));
    if (localIntent == null);
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      localIntent.putExtra("notification_id", paramString2);
      paramContext.sendBroadcast(localIntent);
    }
  }

  public static boolean sendResponseCode(Context paramContext, String paramString, long paramLong, ResponseCode paramResponseCode)
  {
    boolean bool = false;
    Intent localIntent = IntentUtils.createIntentForReceiver(paramContext.getPackageManager(), paramString, new Intent("com.android.vending.billing.RESPONSE_CODE"));
    if (localIntent == null)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramResponseCode.name();
      arrayOfObject2[1] = paramString;
      FinskyLog.d("Response %s cannot be delivered to %s. Intent does not resolve.", arrayOfObject2);
    }
    while (true)
    {
      return bool;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramResponseCode.name();
      arrayOfObject1[1] = Long.valueOf(paramLong);
      arrayOfObject1[2] = paramString;
      FinskyLog.d("Sending response %s for request %d to %s.", arrayOfObject1);
      localIntent.putExtra("request_id", paramLong);
      localIntent.putExtra("response_code", paramResponseCode.ordinal());
      paramContext.sendBroadcast(localIntent);
      bool = true;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onCreate()
  {
    super.onCreate();
    this.mPackageManager = getPackageManager();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  protected static class BillingNotifier
  {
    private MarketBillingService mService;

    public BillingNotifier(MarketBillingService paramMarketBillingService)
    {
      this.mService = paramMarketBillingService;
    }

    protected boolean sendPurchaseStateChanged(String paramString1, String paramString2, String paramString3)
    {
      Intent localIntent = IntentUtils.createIntentForReceiver(this.mService.mPackageManager, paramString1, new Intent("com.android.vending.billing.PURCHASE_STATE_CHANGED"));
      if (localIntent == null);
      for (boolean bool = false; ; bool = true)
      {
        return bool;
        localIntent.putExtra("inapp_signed_data", paramString2);
        localIntent.putExtra("inapp_signature", paramString3);
        this.mService.sendBroadcast(localIntent);
      }
    }

    protected boolean sendResponseCode(String paramString, long paramLong, MarketBillingService.ResponseCode paramResponseCode)
    {
      return MarketBillingService.sendResponseCode(this.mService, paramString, paramLong, paramResponseCode);
    }

    protected void showStatusBarNotifications(Context paramContext, String paramString, VendingProtos.InAppPurchaseInformationResponseProto paramInAppPurchaseInformationResponseProto)
    {
      String str1 = paramInAppPurchaseInformationResponseProto.getSignedResponse().getSignedData();
      String str2 = paramInAppPurchaseInformationResponseProto.getSignedResponse().getSignature();
      Iterator localIterator = paramInAppPurchaseInformationResponseProto.getStatusBarNotificationList().iterator();
      while (localIterator.hasNext())
      {
        VendingProtos.StatusBarNotificationProto localStatusBarNotificationProto = (VendingProtos.StatusBarNotificationProto)localIterator.next();
        String str3 = localStatusBarNotificationProto.getTickerText();
        String str4 = localStatusBarNotificationProto.getContentTitle();
        String str5 = localStatusBarNotificationProto.getContentText();
        Intent localIntent = this.mService.mPackageManager.getLaunchIntentForPackage(paramString);
        localIntent.putExtra("inapp_signed_data", str1);
        localIntent.putExtra("inapp_signature", str2);
        FinskyApp.get().getNotifier().showNotification(paramString, str3, str4, str5, 17301642, localIntent);
      }
    }
  }

  private static enum BillingRequest
  {
    static
    {
      GET_PURCHASE_INFORMATION = new BillingRequest("GET_PURCHASE_INFORMATION", 2);
      RESTORE_TRANSACTIONS = new BillingRequest("RESTORE_TRANSACTIONS", 3);
      CONFIRM_NOTIFICATIONS = new BillingRequest("CONFIRM_NOTIFICATIONS", 4);
      BillingRequest[] arrayOfBillingRequest = new BillingRequest[5];
      arrayOfBillingRequest[0] = CHECK_BILLING_SUPPORTED;
      arrayOfBillingRequest[1] = REQUEST_PURCHASE;
      arrayOfBillingRequest[2] = GET_PURCHASE_INFORMATION;
      arrayOfBillingRequest[3] = RESTORE_TRANSACTIONS;
      arrayOfBillingRequest[4] = CONFIRM_NOTIFICATIONS;
    }
  }

  public static enum ResponseCode
  {
    static
    {
      RESULT_SERVICE_UNAVAILABLE = new ResponseCode("RESULT_SERVICE_UNAVAILABLE", 2);
      RESULT_BILLING_UNAVAILABLE = new ResponseCode("RESULT_BILLING_UNAVAILABLE", 3);
      RESULT_ITEM_UNAVAILABLE = new ResponseCode("RESULT_ITEM_UNAVAILABLE", 4);
      RESULT_DEVELOPER_ERROR = new ResponseCode("RESULT_DEVELOPER_ERROR", 5);
      RESULT_ERROR = new ResponseCode("RESULT_ERROR", 6);
      ResponseCode[] arrayOfResponseCode = new ResponseCode[7];
      arrayOfResponseCode[0] = RESULT_OK;
      arrayOfResponseCode[1] = RESULT_USER_CANCELED;
      arrayOfResponseCode[2] = RESULT_SERVICE_UNAVAILABLE;
      arrayOfResponseCode[3] = RESULT_BILLING_UNAVAILABLE;
      arrayOfResponseCode[4] = RESULT_ITEM_UNAVAILABLE;
      arrayOfResponseCode[5] = RESULT_DEVELOPER_ERROR;
      arrayOfResponseCode[6] = RESULT_ERROR;
    }
  }

  class Stub extends IMarketBillingService.Stub
  {
    Stub()
    {
    }

    private boolean argumentsMatch(Bundle paramBundle, String[] paramArrayOfString1, String[] paramArrayOfString2)
    {
      Set localSet = paramBundle.keySet();
      HashSet localHashSet1 = Sets.newHashSet();
      HashSet localHashSet2 = Sets.newHashSet();
      localHashSet1.add("BILLING_REQUEST");
      localHashSet1.add("API_VERSION");
      int i = paramArrayOfString1.length;
      for (int j = 0; j < i; j++)
        localHashSet1.add(paramArrayOfString1[j]);
      int k = paramArrayOfString2.length;
      for (int m = 0; m < k; m++)
        localHashSet2.add(paramArrayOfString2[m]);
      localSet.removeAll(localHashSet2);
      return localSet.equals(localHashSet1);
    }

    private boolean argumentsMatchExactly(Bundle paramBundle, String[] paramArrayOfString)
    {
      return argumentsMatch(paramBundle, paramArrayOfString, MarketBillingService.EMPTY_STRING_ARRAY);
    }

    private String computeSignatureHash(PackageInfo paramPackageInfo)
    {
      return Md5Util.secureHash(paramPackageInfo.signatures[0].toByteArray());
    }

    private MarketBillingService.BillingRequest getBillingRequest(Bundle paramBundle)
    {
      Object localObject = null;
      String str = paramBundle.getString("BILLING_REQUEST");
      if (str == null)
        FinskyLog.w("Received bundle without billing request type", new Object[0]);
      while (true)
      {
        return localObject;
        try
        {
          MarketBillingService.BillingRequest localBillingRequest = MarketBillingService.BillingRequest.valueOf(str);
          localObject = localBillingRequest;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          FinskyLog.w("Unknown billing request type: %s", new Object[] { str });
        }
      }
    }

    private long getNextInAppRequestId()
    {
      return 0xFFFFFFFF & MarketBillingService.sRandom.nextLong();
    }

    private PackageInfo getPackageInfo(String paramString)
    {
      try
      {
        PackageInfo localPackageInfo2 = MarketBillingService.this.mPackageManager.getPackageInfo(paramString, 64);
        localPackageInfo1 = localPackageInfo2;
        return localPackageInfo1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          FinskyLog.w("cannot find package name: %s", new Object[] { paramString });
          PackageInfo localPackageInfo1 = null;
        }
      }
    }

    private Bundle handleBillingRequest(MarketBillingService.BillingRequest paramBillingRequest, Bundle paramBundle)
    {
      int i = paramBundle.getInt("API_VERSION", -1);
      String str1 = paramBundle.getString("PACKAGE_NAME");
      String str2 = paramBundle.getString("DEVELOPER_PAYLOAD");
      String str3 = paramBundle.getString("ITEM_ID");
      String str4 = paramBundle.getString("ITEM_TYPE");
      long l = paramBundle.getLong("NONCE");
      String[] arrayOfString = paramBundle.getStringArray("NOTIFY_IDS");
      Bundle localBundle1 = new Bundle();
      MarketBillingService.ResponseCode localResponseCode1 = checkBillingEnabled(i, str1);
      MarketBillingService.ResponseCode localResponseCode2 = MarketBillingService.ResponseCode.RESULT_OK;
      Bundle localBundle2;
      if (localResponseCode1 != localResponseCode2)
      {
        localBundle2 = setResponseCode(localBundle1, localResponseCode1);
        return localBundle2;
      }
      switch (MarketBillingService.1.$SwitchMap$com$google$android$finsky$billing$iab$MarketBillingService$BillingRequest[paramBillingRequest.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        localBundle2 = setResponseCode(localBundle1, localResponseCode1);
        break;
        if (!argumentsMatch(paramBundle, new String[] { "PACKAGE_NAME" }, new String[] { "ITEM_TYPE" }))
        {
          localBundle2 = setResponseCode(localBundle1, MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
          break;
        }
        if (str4 == null)
          str4 = "inapp";
        localResponseCode1 = checkTypeSupported(i, str4);
        continue;
        if (!argumentsMatch(paramBundle, new String[] { "PACKAGE_NAME", "ITEM_ID" }, new String[] { "DEVELOPER_PAYLOAD", "ITEM_TYPE" }))
        {
          localBundle2 = setResponseCode(localBundle1, MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
          break;
        }
        if (str4 == null)
          str4 = "inapp";
        localResponseCode1 = checkTypeSupported(i, str4);
        MarketBillingService.ResponseCode localResponseCode3 = MarketBillingService.ResponseCode.RESULT_OK;
        if (localResponseCode1 != localResponseCode3)
        {
          localBundle2 = setResponseCode(localBundle1, localResponseCode1);
          break;
        }
        Pair localPair = requestPurchase(i, str1, str3, str4, str2);
        localBundle1.putLong("REQUEST_ID", ((Long)localPair.first).longValue());
        localBundle1.putParcelable("PURCHASE_INTENT", (Parcelable)localPair.second);
        continue;
        if (!argumentsMatchExactly(paramBundle, new String[] { "PACKAGE_NAME", "NONCE", "NOTIFY_IDS" }))
        {
          localBundle2 = setResponseCode(localBundle1, MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
          break;
        }
        localResponseCode1 = updateWithRequestId(localBundle1, getPurchaseInformation(i, str1, l, arrayOfString));
        continue;
        if (!argumentsMatchExactly(paramBundle, new String[] { "PACKAGE_NAME", "NONCE" }))
        {
          localBundle2 = setResponseCode(localBundle1, MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
          break;
        }
        localResponseCode1 = updateWithRequestId(localBundle1, restoreTransactions(i, str1, l));
        continue;
        if (!argumentsMatchExactly(paramBundle, new String[] { "PACKAGE_NAME", "NOTIFY_IDS" }))
        {
          localBundle2 = setResponseCode(localBundle1, MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
          break;
        }
        localResponseCode1 = updateWithRequestId(localBundle1, confirmNotifications(i, str1, arrayOfString));
      }
    }

    private boolean isBillingEnabledForAccount(Account paramAccount)
    {
      if ((paramAccount != null) && (InAppBillingSetting.isEnabled(paramAccount.name)));
      for (boolean bool = true; ; bool = false)
        return bool;
    }

    private boolean isBillingEnabledForPackage(String paramString)
    {
      return isBillingEnabledForAccount(MarketBillingService.this.getPreferredAccount(paramString));
    }

    private boolean isRequestAllowed(String paramString)
    {
      return true;
    }

    private VendingProtos.SignatureHashProto makeSignatureHash(PackageInfo paramPackageInfo)
    {
      VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
      localSignatureHashProto.setPackageName(paramPackageInfo.packageName);
      localSignatureHashProto.setVersionCode(paramPackageInfo.versionCode);
      localSignatureHashProto.setHash(ByteStringMicro.copyFrom(Md5Util.secureHashBytes(paramPackageInfo.signatures[0].toByteArray())));
      return localSignatureHashProto;
    }

    private Bundle setResponseCode(Bundle paramBundle, MarketBillingService.ResponseCode paramResponseCode)
    {
      paramBundle.putInt("RESPONSE_CODE", paramResponseCode.ordinal());
      return paramBundle;
    }

    private MarketBillingService.ResponseCode updateWithRequestId(Bundle paramBundle, long paramLong)
    {
      if (paramLong == -1L);
      for (MarketBillingService.ResponseCode localResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR; ; localResponseCode = MarketBillingService.ResponseCode.RESULT_OK)
      {
        return localResponseCode;
        paramBundle.putLong("REQUEST_ID", paramLong);
      }
    }

    public MarketBillingService.ResponseCode checkBillingApiVersionSupport(int paramInt)
    {
      MarketBillingService.ResponseCode localResponseCode;
      if (paramInt <= 0)
      {
        FinskyLog.w("No billing API version given!", new Object[0]);
        localResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
      }
      while (true)
      {
        return localResponseCode;
        if (paramInt > 2)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramInt);
          FinskyLog.d("Unsupported (future) billing API version: %d", arrayOfObject);
          localResponseCode = MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE;
        }
        else
        {
          localResponseCode = MarketBillingService.ResponseCode.RESULT_OK;
        }
      }
    }

    public MarketBillingService.ResponseCode checkBillingEnabled(int paramInt, String paramString)
    {
      MarketBillingService.ResponseCode localResponseCode = checkBillingApiVersionSupport(paramInt);
      if (localResponseCode != MarketBillingService.ResponseCode.RESULT_OK);
      while (true)
      {
        return localResponseCode;
        if (paramString == null)
        {
          FinskyLog.w("No packageName given!", new Object[0]);
          localResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
        }
        else if (!isBillingEnabledForPackage(paramString))
        {
          FinskyLog.d("Billing unavailable for this package.", new Object[0]);
          localResponseCode = MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE;
        }
        else if (getPackageInfo(paramString) == null)
        {
          FinskyLog.d("No package info for %s", new Object[] { paramString });
          localResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR;
        }
        else
        {
          localResponseCode = MarketBillingService.ResponseCode.RESULT_OK;
        }
      }
    }

    public MarketBillingService.ResponseCode checkTypeSupported(int paramInt, String paramString)
    {
      MarketBillingService.ResponseCode localResponseCode;
      if ((!paramString.equals("inapp")) && (!paramString.equals("subs")))
      {
        FinskyLog.d("Unknown item type specified %s", new Object[] { paramString });
        localResponseCode = MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE;
      }
      while (true)
      {
        return localResponseCode;
        if (paramInt == 1)
        {
          if (!paramString.equals("inapp"))
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = paramString;
            arrayOfObject[1] = Integer.valueOf(paramInt);
            FinskyLog.d("Item type %s not supported in billing api version %d", arrayOfObject);
            localResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
          }
        }
        else if ((paramInt == 2) && (paramString.equals("subs")) && (!((Boolean)G.subscriptionsEnabled.get()).booleanValue()))
          localResponseCode = MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE;
        else
          localResponseCode = MarketBillingService.ResponseCode.RESULT_OK;
      }
    }

    public long confirmNotifications(int paramInt, final String paramString, String[] paramArrayOfString)
    {
      PackageInfo localPackageInfo = getPackageInfo(paramString);
      final long l;
      if (localPackageInfo == null)
        l = -1L;
      while (true)
      {
        return l;
        l = getNextInAppRequestId();
        Account localAccount = MarketBillingService.this.getPreferredAccount(paramString);
        if (!isBillingEnabledForAccount(localAccount))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE);
        }
        else if (!isRequestAllowed(paramString))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_ERROR);
        }
        else
        {
          VendingProtos.AckNotificationsRequestProto localAckNotificationsRequestProto = new VendingProtos.AckNotificationsRequestProto();
          localAckNotificationsRequestProto.setSignatureHash(makeSignatureHash(localPackageInfo));
          int i = paramArrayOfString.length;
          for (int j = 0; j < i; j++)
            localAckNotificationsRequestProto.addNotificationId(paramArrayOfString[j]);
          FinskyApp.get().getVendingApi(localAccount.name).ackNotifications(localAckNotificationsRequestProto, new Response.Listener()
          {
            public void onResponse(VendingProtos.AckNotificationsResponseProto paramAnonymousAckNotificationsResponseProto)
            {
              MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_OK);
            }
          }
          , new NotifyingErrorListener(paramString, l));
        }
      }
    }

    public long getPurchaseInformation(int paramInt, final String paramString, long paramLong, String[] paramArrayOfString)
    {
      PackageInfo localPackageInfo = getPackageInfo(paramString);
      final long l;
      if (localPackageInfo == null)
        l = -1L;
      while (true)
      {
        return l;
        l = getNextInAppRequestId();
        Account localAccount = MarketBillingService.this.getPreferredAccount(paramString);
        if (!isBillingEnabledForAccount(localAccount))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE);
        }
        else if (!isRequestAllowed(paramString))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_ERROR);
        }
        else
        {
          VendingProtos.InAppPurchaseInformationRequestProto localInAppPurchaseInformationRequestProto = new VendingProtos.InAppPurchaseInformationRequestProto();
          localInAppPurchaseInformationRequestProto.setBillingApiVersion(paramInt);
          localInAppPurchaseInformationRequestProto.setSignatureHash(makeSignatureHash(localPackageInfo));
          localInAppPurchaseInformationRequestProto.setSignatureAlgorithm("SHA1withRSA");
          localInAppPurchaseInformationRequestProto.setNonce(paramLong);
          int i = paramArrayOfString.length;
          for (int j = 0; j < i; j++)
            localInAppPurchaseInformationRequestProto.addNotificationId(paramArrayOfString[j]);
          FinskyApp.get().getVendingApi(localAccount.name).getInAppPurchaseInformation(localInAppPurchaseInformationRequestProto, new Response.Listener()
          {
            public void onResponse(VendingProtos.InAppPurchaseInformationResponseProto paramAnonymousInAppPurchaseInformationResponseProto)
            {
              MarketBillingService.this.mNotifier.sendPurchaseStateChanged(paramString, paramAnonymousInAppPurchaseInformationResponseProto.getSignedResponse().getSignedData(), paramAnonymousInAppPurchaseInformationResponseProto.getSignedResponse().getSignature());
              MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.this.purchaseResultToResponseCode(paramAnonymousInAppPurchaseInformationResponseProto.getPurchaseResult()));
              MarketBillingService.this.mNotifier.showStatusBarNotifications(MarketBillingService.this, paramString, paramAnonymousInAppPurchaseInformationResponseProto);
            }
          }
          , new NotifyingErrorListener(paramString, l));
        }
      }
    }

    public Pair<Long, PendingIntent> requestPurchase(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      Account localAccount = MarketBillingService.this.getPreferredAccount(paramString1);
      PackageInfo localPackageInfo = getPackageInfo(paramString1);
      if ((localPackageInfo == null) || (!isBillingEnabledForAccount(localAccount)));
      long l;
      PendingIntent localPendingIntent;
      for (Object localObject = null; ; localObject = Pair.create(Long.valueOf(l), localPendingIntent))
      {
        return localObject;
        l = getNextInAppRequestId();
        String str = computeSignatureHash(localPackageInfo);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setClass(MarketBillingService.this, IabActivity.class);
        localIntent.putExtra("document_id", MarketBillingService.this.buildDocId(paramString2, paramString3, paramString1));
        localIntent.putExtra("package_name", paramString1);
        localIntent.putExtra("package_version_code", localPackageInfo.versionCode);
        localIntent.putExtra("package_signature_hash", str);
        localIntent.putExtra("request_id", l);
        localIntent.putExtra("billing_api_version", paramInt);
        localIntent.putExtra("authAccount", localAccount.name);
        localIntent.putExtra("finsky.is_direct_link_purchase", true);
        if (paramString4 != null)
          localIntent.putExtra("developer_payload", paramString4);
        localPendingIntent = PendingIntent.getActivity(MarketBillingService.this, 0, localIntent, 1073741824);
      }
    }

    public long restoreTransactions(int paramInt, final String paramString, long paramLong)
    {
      PackageInfo localPackageInfo = getPackageInfo(paramString);
      final long l;
      if (localPackageInfo == null)
        l = -1L;
      while (true)
      {
        return l;
        l = getNextInAppRequestId();
        Account localAccount = MarketBillingService.this.getPreferredAccount(paramString);
        if (!isBillingEnabledForAccount(localAccount))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_BILLING_UNAVAILABLE);
        }
        else if (!isRequestAllowed(paramString))
        {
          MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.ResponseCode.RESULT_ERROR);
        }
        else
        {
          VendingProtos.InAppRestoreTransactionsRequestProto localInAppRestoreTransactionsRequestProto = new VendingProtos.InAppRestoreTransactionsRequestProto();
          localInAppRestoreTransactionsRequestProto.setBillingApiVersion(paramInt);
          localInAppRestoreTransactionsRequestProto.setSignatureHash(makeSignatureHash(localPackageInfo));
          localInAppRestoreTransactionsRequestProto.setSignatureAlgorithm("SHA1withRSA");
          localInAppRestoreTransactionsRequestProto.setNonce(paramLong);
          FinskyApp.get().getVendingApi(localAccount.name).restoreInAppTransactions(localInAppRestoreTransactionsRequestProto, new Response.Listener()
          {
            public void onResponse(VendingProtos.InAppRestoreTransactionsResponseProto paramAnonymousInAppRestoreTransactionsResponseProto)
            {
              if (paramAnonymousInAppRestoreTransactionsResponseProto.hasSignedResponse())
                MarketBillingService.this.mNotifier.sendPurchaseStateChanged(paramString, paramAnonymousInAppRestoreTransactionsResponseProto.getSignedResponse().getSignedData(), paramAnonymousInAppRestoreTransactionsResponseProto.getSignedResponse().getSignature());
              if (paramAnonymousInAppRestoreTransactionsResponseProto.hasPurchaseResult())
                MarketBillingService.this.mNotifier.sendResponseCode(paramString, l, MarketBillingService.this.purchaseResultToResponseCode(paramAnonymousInAppRestoreTransactionsResponseProto.getPurchaseResult()));
            }
          }
          , new NotifyingErrorListener(paramString, l));
        }
      }
    }

    public Bundle sendBillingRequest(Bundle paramBundle)
    {
      MarketBillingService.BillingRequest localBillingRequest = getBillingRequest(paramBundle);
      Bundle localBundle;
      if (localBillingRequest == null)
      {
        localBundle = new Bundle();
        localBundle.putInt("RESPONSE_CODE", MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR.ordinal());
      }
      while (true)
      {
        MarketBillingService.this.stopSelf();
        return localBundle;
        localBundle = handleBillingRequest(localBillingRequest, paramBundle);
      }
    }

    private class NotifyingErrorListener
      implements Response.ErrorListener
    {
      private final String mPackageName;
      private final long mRequestId;

      public NotifyingErrorListener(String paramLong, long arg3)
      {
        this.mPackageName = paramLong;
        Object localObject;
        this.mRequestId = localObject;
      }

      public void onErrorResponse(VolleyError paramVolleyError)
      {
        FinskyLog.e("Server error on InAppPurchaseInformationRequest: %s", new Object[] { paramVolleyError });
        MarketBillingService.this.mNotifier.sendResponseCode(this.mPackageName, this.mRequestId, MarketBillingService.ResponseCode.RESULT_SERVICE_UNAVAILABLE);
      }
    }
  }

  public static class UidProvider
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.iab.MarketBillingService
 * JD-Core Version:    0.6.2
 */
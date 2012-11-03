package com.google.android.finsky.billing.iab;

import android.accounts.Account;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
import android.text.TextUtils;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.IabActivity;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.InAppBillingSetting;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryInAppEntry;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.ConsumePurchaseResponse;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.remoting.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Md5Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppBillingService extends Service
{
  private static boolean DEBUG = ((Boolean)G.iabV3VerboseLogEnabled.get()).booleanValue();
  private final Stub mBinder = new Stub();
  private PackageManager mPackageManager;

  private String buildDetailsJson(Details.BulkDetailsEntry paramBulkDetailsEntry)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      String str = paramBulkDetailsEntry.getDoc().getBackendDocid();
      localJSONObject.put("productId", DocUtils.extractSkuForInApp(str));
      localJSONObject.put("type", getInAppType(str));
      localJSONObject.put("price", paramBulkDetailsEntry.getDoc().getOffer(0).getFormattedAmount());
      localJSONObject.put("title", paramBulkDetailsEntry.getDoc().getTitle());
      localJSONObject.put("description", Html.fromHtml(paramBulkDetailsEntry.getDoc().getDescriptionHtml()));
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      while (true)
        FinskyLog.wtf("Exception when creating json: %s", new Object[] { localJSONException });
    }
  }

  private String buildDocId(String paramString1, String paramString2, String paramString3)
  {
    return paramString2 + ":" + paramString3 + ":" + paramString1;
  }

  private ResponseCode checkBillingApiVersionSupport(int paramInt)
  {
    if ((paramInt < 3) || (paramInt > 3))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.d("Unsupported billing API version: %d", arrayOfObject);
    }
    for (ResponseCode localResponseCode = ResponseCode.RESULT_BILLING_UNAVAILABLE; ; localResponseCode = ResponseCode.RESULT_OK)
      return localResponseCode;
  }

  private ResponseCode checkBillingEnabled(int paramInt, String paramString)
  {
    ResponseCode localResponseCode = checkBillingApiVersionSupport(paramInt);
    if (localResponseCode != ResponseCode.RESULT_OK);
    while (true)
    {
      return localResponseCode;
      if (paramString == null)
      {
        FinskyLog.w("No packageName given!", new Object[0]);
        localResponseCode = ResponseCode.RESULT_DEVELOPER_ERROR;
      }
      else if (!isBillingEnabledForPackage(paramString))
      {
        FinskyLog.d("Billing unavailable for this package.", new Object[0]);
        localResponseCode = ResponseCode.RESULT_BILLING_UNAVAILABLE;
      }
      else
      {
        localResponseCode = ResponseCode.RESULT_OK;
      }
    }
  }

  private ResponseCode checkTypeSupported(int paramInt, String paramString)
  {
    ResponseCode localResponseCode;
    if ((!TextUtils.equals(paramString, "inapp")) && (!TextUtils.equals(paramString, "subs")))
    {
      FinskyLog.d("Unknown item type specified %s", new Object[] { paramString });
      localResponseCode = ResponseCode.RESULT_BILLING_UNAVAILABLE;
    }
    while (true)
    {
      return localResponseCode;
      if (paramString.equals("subs"))
      {
        FinskyLog.d("Subscriptions are not supported", new Object[0]);
        localResponseCode = ResponseCode.RESULT_BILLING_UNAVAILABLE;
      }
      else
      {
        localResponseCode = ResponseCode.RESULT_OK;
      }
    }
  }

  private String computeSignatureHash(PackageInfo paramPackageInfo)
  {
    return Md5Util.secureHash(paramPackageInfo.signatures[0].toByteArray());
  }

  private ResponseCode consumeIabPurchase(String paramString1, String paramString2)
  {
    final Semaphore localSemaphore = new Semaphore(0);
    final Account localAccount = getPreferredAccount(paramString1);
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(localAccount.name);
    final ResponseCode[] arrayOfResponseCode = new ResponseCode[1];
    arrayOfResponseCode[0] = ResponseCode.RESULT_OK;
    localDfeApi.consumePurchase(paramString2, 1, paramString1, new Response.Listener()
    {
      public void onResponse(ConsumePurchaseResponse paramAnonymousConsumePurchaseResponse)
      {
        if (paramAnonymousConsumePurchaseResponse.hasLibraryUpdate())
          FinskyApp.get().getLibraryReplicators().applyLibraryUpdate(localAccount, paramAnonymousConsumePurchaseResponse.getLibraryUpdate(), "confirmPurchase");
        switch (paramAnonymousConsumePurchaseResponse.getStatus())
        {
        default:
          arrayOfResponseCode[0] = InAppBillingService.ResponseCode.RESULT_ERROR;
        case 0:
        case 2:
        case 1:
        }
        while (true)
        {
          localSemaphore.release();
          return;
          arrayOfResponseCode[0] = InAppBillingService.ResponseCode.RESULT_OK;
          continue;
          arrayOfResponseCode[0] = InAppBillingService.ResponseCode.RESULT_ITEM_NOT_OWNED;
          continue;
          arrayOfResponseCode[0] = InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousVolleyError.toString();
        FinskyLog.w("Error response on confirmPurchase: %s", arrayOfObject);
        arrayOfResponseCode[0] = InAppBillingService.ResponseCode.RESULT_ERROR;
        localSemaphore.release();
      }
    });
    try
    {
      if (!localSemaphore.tryAcquire(20L, TimeUnit.SECONDS))
      {
        localResponseCode = ResponseCode.RESULT_ERROR;
        return localResponseCode;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        ResponseCode localResponseCode = ResponseCode.RESULT_ERROR;
        continue;
        localResponseCode = arrayOfResponseCode[0];
      }
    }
  }

  private void fetchSkuDetails(String paramString1, Bundle paramBundle1, String paramString2, final Bundle paramBundle2)
  {
    ArrayList localArrayList1 = paramBundle1.getStringArrayList("ITEM_ID_LIST");
    if ((localArrayList1.isEmpty()) || (localArrayList1.size() > 20))
      paramBundle2.putLong("RESPONSE_CODE", ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
    while (true)
    {
      return;
      ArrayList localArrayList2 = Lists.newArrayList();
      for (int i = 0; i != localArrayList1.size(); i++)
        localArrayList2.add(buildDocId((String)localArrayList1.get(i), paramString2, paramString1));
      final Semaphore localSemaphore = new Semaphore(0);
      Account localAccount = getPreferredAccount(paramString1);
      FinskyApp.get().getDfeApi(localAccount.name).getDetails(localArrayList2, new Response.Listener()
      {
        public void onResponse(Details.BulkDetailsResponse paramAnonymousBulkDetailsResponse)
        {
          List localList = paramAnonymousBulkDetailsResponse.getEntryList();
          ArrayList localArrayList = Lists.newArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            Details.BulkDetailsEntry localBulkDetailsEntry = (Details.BulkDetailsEntry)localIterator.next();
            if (localBulkDetailsEntry.getDoc() != null)
              localArrayList.add(InAppBillingService.this.buildDetailsJson(localBulkDetailsEntry));
          }
          paramBundle2.putStringArrayList("DETAILS_LIST", localArrayList);
          localSemaphore.release();
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          paramBundle2.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_ERROR.responseCode());
          localSemaphore.release();
        }
      });
      try
      {
        if (!localSemaphore.tryAcquire(20L, TimeUnit.SECONDS))
          paramBundle2.putLong("RESPONSE_CODE", ResponseCode.RESULT_ERROR.responseCode());
      }
      catch (InterruptedException localInterruptedException)
      {
        paramBundle2.putInt("RESPONSE_CODE", ResponseCode.RESULT_ERROR.responseCode());
      }
    }
  }

  private String getInAppType(String paramString)
  {
    String str;
    if (paramString.startsWith("inapp:"))
      str = "inapp";
    while (true)
    {
      return str;
      if (paramString.startsWith("subs:"))
        str = "subs";
      else
        str = null;
    }
  }

  private PackageInfo getPackageInfo(String paramString)
  {
    try
    {
      PackageInfo localPackageInfo2 = this.mPackageManager.getPackageInfo(paramString, 64);
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

  private boolean isBillingEnabledForAccount(Account paramAccount)
  {
    if ((paramAccount != null) && (InAppBillingSetting.isEnabled(paramAccount.name)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isBillingEnabledForPackage(String paramString)
  {
    return isBillingEnabledForAccount(getPreferredAccount(paramString));
  }

  private boolean isDocumentInLibrary(String paramString1, String paramString2, String paramString3)
  {
    Account localAccount = getPreferredAccount(paramString3);
    Libraries localLibraries = FinskyApp.get().getLibraries();
    localLibraries.blockingLoad();
    if (localLibraries.getAccountLibrary(localAccount).getInAppEntry(buildDocId(paramString2, paramString1, paramString3)) != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static void logEvent(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (DEBUG)
      FinskyLog.v("(" + paramString1 + ") " + paramString2, paramArrayOfObject);
  }

  public static void logResponseBundle(String paramString, Bundle paramBundle)
  {
    logEvent(paramString, "Response bundle: %s", new Object[] { paramBundle });
  }

  public static void logResponseCode(String paramString, ResponseCode paramResponseCode)
  {
    logEvent(paramString, "Response code: %s", new Object[] { paramResponseCode });
  }

  private PendingIntent makePurchaseIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Account localAccount = getPreferredAccount(paramString1);
    PackageInfo localPackageInfo = getPackageInfo(paramString1);
    String str = computeSignatureHash(localPackageInfo);
    Intent localIntent = IabActivity.getIntent(paramInt, localAccount.name, paramString1, localPackageInfo.versionCode, str, buildDocId(paramString2, paramString3, paramString1), paramString4);
    localIntent.setClass(this, IabActivity.class);
    return PendingIntent.getActivity(this, 0, localIntent, 1073741824);
  }

  private void populateInAppPurchasesForPackage(String paramString, Bundle paramBundle)
  {
    Account localAccount = getPreferredAccount(paramString);
    List localList = FinskyApp.get().getLibraries().getAccountLibrary(localAccount).getInAppPurchasesForPackage(paramString);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      LibraryInAppEntry localLibraryInAppEntry = (LibraryInAppEntry)localIterator.next();
      localArrayList1.add(DocUtils.extractSkuForInApp(localLibraryInAppEntry.docId));
      localArrayList2.add(localLibraryInAppEntry.signedPurchaseData);
      localArrayList3.add(localLibraryInAppEntry.signature);
    }
    paramBundle.putStringArrayList("INAPP_PURCHASE_ITEM_LIST", localArrayList1);
    paramBundle.putStringArrayList("INAPP_PURCHASE_DATA_LIST", localArrayList2);
    paramBundle.putStringArrayList("INAPP_DATA_SIGNATURE_LIST", localArrayList3);
  }

  private ResponseCode validatePackageName(String paramString)
  {
    int i = Binder.getCallingUid();
    String[] arrayOfString = this.mPackageManager.getPackagesForUid(i);
    if ((arrayOfString.length > 0) && (TextUtils.equals(paramString, arrayOfString[0])));
    for (ResponseCode localResponseCode = ResponseCode.RESULT_OK; ; localResponseCode = ResponseCode.RESULT_DEVELOPER_ERROR)
    {
      return localResponseCode;
      FinskyLog.d("Package name %s does not match the Uid", new Object[] { paramString });
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

  public static enum ResponseCode
  {
    private final int responseCode;

    static
    {
      RESULT_BILLING_UNAVAILABLE = new ResponseCode("RESULT_BILLING_UNAVAILABLE", 2, 3);
      RESULT_ITEM_UNAVAILABLE = new ResponseCode("RESULT_ITEM_UNAVAILABLE", 3, 4);
      RESULT_DEVELOPER_ERROR = new ResponseCode("RESULT_DEVELOPER_ERROR", 4, 5);
      RESULT_ERROR = new ResponseCode("RESULT_ERROR", 5, 6);
      RESULT_ITEM_ALREADY_OWNED = new ResponseCode("RESULT_ITEM_ALREADY_OWNED", 6, 7);
      RESULT_ITEM_NOT_OWNED = new ResponseCode("RESULT_ITEM_NOT_OWNED", 7, 8);
      ResponseCode[] arrayOfResponseCode = new ResponseCode[8];
      arrayOfResponseCode[0] = RESULT_OK;
      arrayOfResponseCode[1] = RESULT_USER_CANCELED;
      arrayOfResponseCode[2] = RESULT_BILLING_UNAVAILABLE;
      arrayOfResponseCode[3] = RESULT_ITEM_UNAVAILABLE;
      arrayOfResponseCode[4] = RESULT_DEVELOPER_ERROR;
      arrayOfResponseCode[5] = RESULT_ERROR;
      arrayOfResponseCode[6] = RESULT_ITEM_ALREADY_OWNED;
      arrayOfResponseCode[7] = RESULT_ITEM_NOT_OWNED;
    }

    private ResponseCode(int paramInt)
    {
      this.responseCode = paramInt;
    }

    public int responseCode()
    {
      return this.responseCode;
    }

    public String toString()
    {
      return super.toString() + '(' + this.responseCode + ')';
    }
  }

  class Stub extends IInAppBillingService.Stub
  {
    Stub()
    {
    }

    public int consumePurchase(int paramInt, String paramString1, String paramString2)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      InAppBillingService.logEvent("consumePurchase", "apiVersion: %d packageName: %s purchaseToken: %s", arrayOfObject);
      int i;
      if (TextUtils.isEmpty(paramString2))
      {
        InAppBillingService.logResponseCode("consumePurchase", InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR);
        i = InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR.responseCode();
      }
      while (true)
      {
        return i;
        InAppBillingService.ResponseCode localResponseCode1 = InAppBillingService.this.validatePackageName(paramString1);
        if (localResponseCode1 != InAppBillingService.ResponseCode.RESULT_OK)
        {
          InAppBillingService.logResponseCode("consumePurchase", localResponseCode1);
          i = localResponseCode1.responseCode();
        }
        else
        {
          InAppBillingService.ResponseCode localResponseCode2 = InAppBillingService.this.checkBillingEnabled(paramInt, paramString1);
          if (localResponseCode2 != InAppBillingService.ResponseCode.RESULT_OK)
          {
            InAppBillingService.logResponseCode("consumePurchase", localResponseCode2);
            i = localResponseCode2.responseCode();
          }
          else
          {
            InAppBillingService.ResponseCode localResponseCode3 = InAppBillingService.this.consumeIabPurchase(paramString1, paramString2);
            InAppBillingService.logResponseCode("consumePurchase", localResponseCode3);
            i = localResponseCode3.responseCode();
          }
        }
      }
    }

    public Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString3;
      arrayOfObject[3] = paramString4;
      InAppBillingService.logEvent("getBuyIntent", "apiVersion: %d packageName: %s type: %s developerPayload: %s", arrayOfObject);
      Bundle localBundle = new Bundle();
      if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString3)))
      {
        localBundle.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
        InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
      }
      while (true)
      {
        return localBundle;
        InAppBillingService.ResponseCode localResponseCode1 = InAppBillingService.this.validatePackageName(paramString1);
        if (localResponseCode1 != InAppBillingService.ResponseCode.RESULT_OK)
        {
          localBundle.putLong("RESPONSE_CODE", localResponseCode1.responseCode());
          InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
        }
        else
        {
          InAppBillingService.ResponseCode localResponseCode2 = InAppBillingService.this.checkBillingEnabled(paramInt, paramString1);
          if (localResponseCode2 != InAppBillingService.ResponseCode.RESULT_OK)
          {
            localBundle.putLong("RESPONSE_CODE", localResponseCode2.responseCode());
            InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
          }
          else
          {
            InAppBillingService.ResponseCode localResponseCode3 = InAppBillingService.this.checkTypeSupported(paramInt, paramString3);
            if (localResponseCode3 != InAppBillingService.ResponseCode.RESULT_OK)
            {
              localBundle.putLong("RESPONSE_CODE", localResponseCode3.responseCode());
              InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
            }
            else if (InAppBillingService.this.isDocumentInLibrary(paramString3, paramString2, paramString1))
            {
              localBundle.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_ITEM_ALREADY_OWNED.responseCode());
              InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
            }
            else
            {
              localBundle.putParcelable("BUY_INTENT", InAppBillingService.this.makePurchaseIntent(paramInt, paramString1, paramString2, paramString3, paramString4));
              localBundle.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_OK.responseCode());
              InAppBillingService.logResponseBundle("getBuyIntent", localBundle);
            }
          }
        }
      }
    }

    public Bundle getPurchases(int paramInt, String paramString1, String paramString2)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      InAppBillingService.logEvent("getPurchases", "apiVersion: %d packageName: %s type: %s", arrayOfObject);
      Bundle localBundle = new Bundle();
      if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)))
      {
        localBundle.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
        InAppBillingService.logResponseBundle("getPurchases", localBundle);
      }
      while (true)
      {
        return localBundle;
        InAppBillingService.ResponseCode localResponseCode1 = InAppBillingService.this.validatePackageName(paramString1);
        if (localResponseCode1 != InAppBillingService.ResponseCode.RESULT_OK)
        {
          localBundle.putLong("RESPONSE_CODE", localResponseCode1.responseCode());
          InAppBillingService.logResponseBundle("getPurchases", localBundle);
        }
        else
        {
          InAppBillingService.ResponseCode localResponseCode2 = InAppBillingService.this.checkBillingEnabled(paramInt, paramString1);
          if (localResponseCode2 != InAppBillingService.ResponseCode.RESULT_OK)
          {
            localBundle.putLong("RESPONSE_CODE", localResponseCode2.responseCode());
            InAppBillingService.logResponseBundle("getPurchases", localBundle);
          }
          else
          {
            InAppBillingService.ResponseCode localResponseCode3 = InAppBillingService.this.checkTypeSupported(paramInt, paramString2);
            if (localResponseCode3 != InAppBillingService.ResponseCode.RESULT_OK)
            {
              localBundle.putLong("RESPONSE_CODE", localResponseCode3.responseCode());
              InAppBillingService.logResponseBundle("getPurchases", localBundle);
            }
            else
            {
              InAppBillingService.this.populateInAppPurchasesForPackage(paramString1, localBundle);
              InAppBillingService.logResponseBundle("getPurchases", localBundle);
            }
          }
        }
      }
    }

    public Bundle getSkuDetails(int paramInt, String paramString1, String paramString2, Bundle paramBundle)
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      arrayOfObject[3] = paramBundle;
      InAppBillingService.logEvent("getSkuDetails", "apiVersion: %d packageName: %s type: %s skusBundle: %s", arrayOfObject);
      Bundle localBundle = new Bundle();
      InAppBillingService.ResponseCode localResponseCode1 = InAppBillingService.this.validatePackageName(paramString1);
      if (localResponseCode1 != InAppBillingService.ResponseCode.RESULT_OK)
      {
        localBundle.putLong("RESPONSE_CODE", localResponseCode1.responseCode());
        InAppBillingService.logResponseBundle("getSkuDetails", localBundle);
      }
      while (true)
      {
        return localBundle;
        InAppBillingService.ResponseCode localResponseCode2 = InAppBillingService.this.checkBillingEnabled(paramInt, paramString1);
        if (localResponseCode2 != InAppBillingService.ResponseCode.RESULT_OK)
        {
          localBundle.putLong("RESPONSE_CODE", localResponseCode2.responseCode());
          InAppBillingService.logResponseBundle("getSkuDetails", localBundle);
        }
        else
        {
          InAppBillingService.ResponseCode localResponseCode3 = InAppBillingService.this.checkTypeSupported(paramInt, paramString2);
          if (localResponseCode3 != InAppBillingService.ResponseCode.RESULT_OK)
          {
            localBundle.putLong("RESPONSE_CODE", localResponseCode3.responseCode());
            InAppBillingService.logResponseBundle("getSkuDetails", localBundle);
          }
          else if (!paramBundle.containsKey("ITEM_ID_LIST"))
          {
            localBundle.putLong("RESPONSE_CODE", InAppBillingService.ResponseCode.RESULT_DEVELOPER_ERROR.responseCode());
            InAppBillingService.logResponseBundle("getSkuDetails", localBundle);
          }
          else
          {
            InAppBillingService.this.fetchSkuDetails(paramString1, paramBundle, paramString2, localBundle);
            InAppBillingService.logResponseBundle("getSkuDetails", localBundle);
          }
        }
      }
    }

    public int isBillingSupported(int paramInt, String paramString1, String paramString2)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      InAppBillingService.logEvent("isBillingSupported", "apiVersion: %d packageName: %s type: %s", arrayOfObject);
      InAppBillingService.ResponseCode localResponseCode1 = InAppBillingService.this.validatePackageName(paramString1);
      int i;
      if (localResponseCode1 != InAppBillingService.ResponseCode.RESULT_OK)
      {
        InAppBillingService.logResponseCode("isBillingSupported", localResponseCode1);
        i = localResponseCode1.responseCode();
      }
      while (true)
      {
        return i;
        InAppBillingService.ResponseCode localResponseCode2 = InAppBillingService.this.checkBillingEnabled(paramInt, paramString1);
        if (localResponseCode2 != InAppBillingService.ResponseCode.RESULT_OK)
        {
          InAppBillingService.logResponseCode("isBillingSupported", localResponseCode2);
          i = localResponseCode2.responseCode();
        }
        else
        {
          InAppBillingService.ResponseCode localResponseCode3 = InAppBillingService.this.checkTypeSupported(paramInt, paramString2);
          InAppBillingService.logResponseCode("isBillingSupported", localResponseCode3);
          i = localResponseCode3.responseCode();
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.iab.InAppBillingService
 * JD-Core Version:    0.6.2
 */
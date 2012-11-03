package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.CheckoutPurchase.Error;
import com.google.android.finsky.billing.CheckoutPurchase.ErrorType;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.billing.iab.InAppBillingService;
import com.google.android.finsky.billing.iab.MarketBillingService;
import com.google.android.finsky.billing.iab.MarketBillingService.ResponseCode;
import com.google.android.finsky.billing.iab.PendingNotificationsService;
import com.google.android.finsky.utils.FinskyLog;

public class IabActivity extends SynchronousPurchaseActivity
{
  private int mBillingApiVersion;
  private IabParameters mIabParameters;
  private String mPackageName;
  private String mPurchaseData;
  private long mRequestId;
  private String mSignature;

  public static Intent getIntent(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3, String paramString4, String paramString5)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.putExtra("document_id", paramString4);
    localIntent.putExtra("package_name", paramString2);
    localIntent.putExtra("package_version_code", paramInt2);
    localIntent.putExtra("package_signature_hash", paramString3);
    localIntent.putExtra("billing_api_version", paramInt1);
    localIntent.putExtra("authAccount", paramString1);
    localIntent.putExtra("finsky.is_direct_link_purchase", true);
    if (paramString5 != null)
      localIntent.putExtra("developer_payload", paramString5);
    localIntent.putExtra("finsky.is_direct_link_purchase", true);
    return localIntent;
  }

  protected PurchaseFragment getPurchaseFragment()
  {
    return PurchaseFragment.newIabInstance(this.mAccount, this.mDocId, this.mIabParameters);
  }

  public boolean isIabV3Purchase()
  {
    if (this.mBillingApiVersion >= 3);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mDocId = getIntent().getStringExtra("document_id");
    this.mPackageName = getIntent().getStringExtra("package_name");
    this.mRequestId = getIntent().getLongExtra("request_id", -1L);
    this.mBillingApiVersion = getIntent().getIntExtra("billing_api_version", 2);
    int i = getIntent().getIntExtra("package_version_code", -1);
    String str1 = getIntent().getStringExtra("package_signature_hash");
    String str2 = getIntent().getStringExtra("developer_payload");
    this.mIabParameters = new IabParameters(this.mBillingApiVersion, this.mPackageName, i, str1, str2);
    this.mIsDirectLinkPurchase = true;
  }

  public void onFailure(CheckoutPurchase.Error paramError)
  {
    this.mResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR;
    if (this.mErrorShown);
    while (true)
    {
      return;
      this.mErrorShown = true;
      String str1 = null;
      String str2 = null;
      if (paramError.type == CheckoutPurchase.ErrorType.IAB_PERMISSION_ERROR)
        switch (paramError.code)
        {
        default:
          str2 = getString(2131165279);
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramError.code);
          FinskyLog.wtf("Unexpected PurchasePermissionResponse: %d", arrayOfObject);
        case 12:
        case 4:
        case 3:
        case 0:
        case 1:
        }
      while (true)
      {
        if (paramError.type == CheckoutPurchase.ErrorType.NETWORK_OR_SERVER)
          this.mResponseCode = MarketBillingService.ResponseCode.RESULT_SERVICE_UNAVAILABLE;
        if (str2 == null)
          break label293;
        showErrorDialog(str1, str2, true);
        break;
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
        str1 = getString(2131165291);
        str2 = getString(2131165292);
        continue;
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_ITEM_UNAVAILABLE;
        str1 = getString(2131165287);
        str2 = getString(2131165288);
        continue;
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
        str1 = getString(2131165289);
        str2 = getString(2131165290);
        continue;
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR;
        str1 = getString(2131165287);
        str2 = getString(2131165288);
        continue;
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_OK;
        FinskyLog.w("Unexpected INSTALL_OK response.", new Object[0]);
        continue;
        if (paramError.type == CheckoutPurchase.ErrorType.SUBSCRIPTION_ALREADY_OWNED)
          this.mResponseCode = MarketBillingService.ResponseCode.RESULT_DEVELOPER_ERROR;
        else
          str2 = paramError.message;
      }
      label293: FinskyLog.d("No error message to show to user.", new Object[0]);
      goBack();
    }
  }

  protected void onStop()
  {
    super.onStop();
    if ((isFinishing()) && (!isIabV3Purchase()))
    {
      MarketBillingService.sendResponseCode(FinskyApp.get().getApplicationContext(), this.mIabParameters.packageName, this.mRequestId, this.mResponseCode);
      if ((this.mResponseCode == MarketBillingService.ResponseCode.RESULT_ERROR) || (this.mResponseCode == MarketBillingService.ResponseCode.RESULT_OK))
        PendingNotificationsService.setMarketAlarm(FinskyApp.get(), this.mAccount.name, 120000L);
    }
  }

  public void onSuccess(Bundle paramBundle)
  {
    if ((isIabV3Purchase()) && (paramBundle != null))
    {
      this.mPurchaseData = paramBundle.getString("inapp_signed_purchase_data");
      this.mSignature = paramBundle.getString("inapp_purchase_data_signature");
    }
    Toast.makeText(this, 2131165285, 1).show();
    super.onSuccess(paramBundle);
  }

  protected void setResultData(Intent paramIntent)
  {
    if (isIabV3Purchase())
    {
      if ((this.mPurchaseData != null) && (this.mSignature != null))
      {
        paramIntent.putExtra("INAPP_PURCHASE_DATA", this.mPurchaseData);
        paramIntent.putExtra("INAPP_DATA_SIGNATURE", this.mSignature);
      }
      paramIntent.putExtra("RESPONSE_CODE", this.mResponseCode.ordinal());
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(this.mResponseCode.ordinal());
      arrayOfObject[1] = this.mPurchaseData;
      arrayOfObject[2] = this.mSignature;
      InAppBillingService.logEvent("purchaseResponse", "responseCode: %d purchase data: %s  signature: %s", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.IabActivity
 * JD-Core Version:    0.6.2
 */
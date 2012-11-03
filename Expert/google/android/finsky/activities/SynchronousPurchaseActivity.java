package com.google.android.finsky.activities;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.billing.CheckoutPurchase.Error;
import com.google.android.finsky.billing.CheckoutPurchase.ErrorType;
import com.google.android.finsky.billing.iab.MarketBillingService.ResponseCode;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.NotificationListener;
import com.google.android.finsky.utils.Notifier;

public class SynchronousPurchaseActivity extends AuthenticatedActivity
  implements PurchaseFragment.PurchaseFragmentListener, PageFragmentHost, NotificationListener
{
  protected Account mAccount;
  protected String mDocId;
  protected boolean mErrorShown;
  protected String mExternalReferrerUrl;
  protected boolean mIsDirectLinkPurchase;
  protected int mOfferType;
  protected String mOriginalUrl;
  protected PurchaseFragment mPurchaseFragment;
  protected String mReferrerCookie;
  protected String mReferrerUrl;
  protected MarketBillingService.ResponseCode mResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR;
  protected Bundle mSavedInstanceState;

  public static void show(Activity paramActivity, String paramString1, String paramString2, int paramInt1, String paramString3, String paramString4, boolean paramBoolean, String paramString5, String paramString6, int paramInt2)
  {
    Intent localIntent = new Intent(paramActivity, SynchronousPurchaseActivity.class);
    localIntent.putExtra("authAccount", paramString1);
    localIntent.putExtra("document_id", paramString6);
    localIntent.putExtra("finsky.original_url", paramString2);
    localIntent.putExtra("finsky.offer_type", paramInt1);
    localIntent.putExtra("finsky.referrer_url", paramString3);
    localIntent.putExtra("finsky.external_referrer_url", paramString5);
    localIntent.putExtra("finsky.referrer_cookie", paramString4);
    localIntent.putExtra("finsky.is_direct_link_purchase", paramBoolean);
    paramActivity.startActivityForResult(localIntent, paramInt2);
  }

  public final void finish()
  {
    Intent localIntent = new Intent();
    setResultData(localIntent);
    if (this.mResponseCode == MarketBillingService.ResponseCode.RESULT_OK);
    for (int i = -1; ; i = 0)
    {
      setResult(i, localIntent);
      super.finish();
      return;
    }
  }

  public BitmapLoader getBitmapLoader()
  {
    return FinskyApp.get().getBitmapLoader();
  }

  public DfeApi getDfeApi()
  {
    return FinskyApp.get().getDfeApi();
  }

  public NavigationManager getNavigationManager()
  {
    if (FinskyLog.DEBUG)
      FinskyLog.v("No navigation manager in IabActivity.", new Object[0]);
    return null;
  }

  protected PurchaseFragment getPurchaseFragment()
  {
    return PurchaseFragment.newInstance(FinskyApp.get().getCurrentAccount(), this.mOriginalUrl, this.mOfferType, this.mReferrerUrl, this.mReferrerCookie, this.mIsDirectLinkPurchase, this.mExternalReferrerUrl, null, this.mDocId);
  }

  public void goBack()
  {
    finish();
  }

  public void onBackPressed()
  {
    if ((!this.mIsDirectLinkPurchase) || (this.mPurchaseFragment == null) || (!this.mPurchaseFragment.handleBackPress()))
    {
      this.mResponseCode = MarketBillingService.ResponseCode.RESULT_USER_CANCELED;
      super.onBackPressed();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    setContentView(2130968698);
    super.onCreate(paramBundle);
    this.mSavedInstanceState = paramBundle;
    this.mDocId = getIntent().getStringExtra("document_id");
    this.mAccount = AccountHandler.findAccount(getIntent().getStringExtra("authAccount"), this);
    this.mOriginalUrl = getIntent().getStringExtra("finsky.original_url");
    this.mReferrerUrl = getIntent().getStringExtra("finsky.referrer_url");
    this.mExternalReferrerUrl = getIntent().getStringExtra("finsky.external_referrer_url");
    this.mOfferType = getIntent().getIntExtra("finsky.offer_type", 1);
    this.mReferrerCookie = getIntent().getStringExtra("finsky.referrer_cookie");
    this.mIsDirectLinkPurchase = getIntent().getBooleanExtra("finsky.is_direct_link_purchase", false);
    if (paramBundle != null)
    {
      this.mErrorShown = paramBundle.getBoolean("error_shown");
      this.mResponseCode = MarketBillingService.ResponseCode.valueOf(paramBundle.getString("response_code"));
    }
  }

  public void onFailure(CheckoutPurchase.Error paramError)
  {
    this.mResponseCode = MarketBillingService.ResponseCode.RESULT_ERROR;
    if (this.mErrorShown);
    while (true)
    {
      return;
      this.mErrorShown = true;
      String str = paramError.message;
      if (paramError.type == CheckoutPurchase.ErrorType.NETWORK_OR_SERVER)
        this.mResponseCode = MarketBillingService.ResponseCode.RESULT_SERVICE_UNAVAILABLE;
      if (str != null)
      {
        showErrorDialog(null, str, true);
      }
      else
      {
        FinskyLog.d("No error message to show to user.", new Object[0]);
        goBack();
      }
    }
  }

  protected void onPause()
  {
    FinskyApp.get().getNotifier().setNotificationListener(null);
    super.onPause();
  }

  protected void onReady(boolean paramBoolean)
  {
    if (this.mSavedInstanceState != null)
      this.mPurchaseFragment = ((PurchaseFragment)getSupportFragmentManager().getFragment(this.mSavedInstanceState, "purchase_fragment"));
    if (this.mPurchaseFragment == null)
    {
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      this.mPurchaseFragment = getPurchaseFragment();
      this.mPurchaseFragment.setPurchaseFragmentListener(this);
      localFragmentTransaction.add(2131230775, this.mPurchaseFragment, "purchase_fragment");
      localFragmentTransaction.commit();
    }
    while (true)
    {
      return;
      this.mPurchaseFragment.setPurchaseFragmentListener(this);
    }
  }

  protected void onResume()
  {
    FinskyApp.get().getNotifier().setNotificationListener(this);
    super.onResume();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mPurchaseFragment != null)
      getSupportFragmentManager().putFragment(paramBundle, "purchase_fragment", this.mPurchaseFragment);
    paramBundle.putBoolean("error_shown", this.mErrorShown);
    paramBundle.putString("response_code", this.mResponseCode.name());
  }

  public void onSuccess(Bundle paramBundle)
  {
    this.mResponseCode = MarketBillingService.ResponseCode.RESULT_OK;
    finish();
  }

  protected void setResultData(Intent paramIntent)
  {
    paramIntent.putExtra("finsky.is_direct_link_purchase", this.mIsDirectLinkPurchase);
    paramIntent.putExtra("requested_doc_id", this.mDocId);
  }

  public boolean showAppAlert(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    return false;
  }

  public boolean showAppNotification(String paramString1, String paramString2, String paramString3)
  {
    return false;
  }

  public boolean showDocAlert(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    boolean bool = true;
    if (paramString1.equals(this.mDocId))
      showErrorDialog(paramString2, paramString3, bool);
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public void showErrorDialog(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (this.mStateSaved);
    while (true)
    {
      return;
      ErrorDialog.show(getSupportFragmentManager(), paramString1, paramString2, paramBoolean);
    }
  }

  public void updateBreadcrumb(String paramString)
  {
    if (FinskyLog.DEBUG)
      FinskyLog.v("Ignoring breadcrumb: %s", new Object[] { paramString });
  }

  public void updateCurrentBackendId(int paramInt)
  {
    if (FinskyLog.DEBUG)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.v("Ignoring backend: %d", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SynchronousPurchaseActivity
 * JD-Core Version:    0.6.2
 */
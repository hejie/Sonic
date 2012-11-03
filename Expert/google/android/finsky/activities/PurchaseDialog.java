package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public class PurchaseDialog extends AuthenticatedActivity
  implements PageFragmentHost
{
  private Account mAccount;
  private CustomActionBar mActionBar;
  private String mContinueUrl;
  private String mDocIdToPurchase;
  private String mExternalReferrer;
  private NavigationManager mNavigationManager = new FakeNavigationManager(this);
  private int mOfferType;
  private String mReferrerCookie;
  private String mReferrerUrl;
  private String mUrl;

  public static void show(Context paramContext, Account paramAccount, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    Intent localIntent = new Intent(paramContext, PurchaseDialog.class);
    localIntent.putExtra("account", paramAccount);
    localIntent.putExtra("url", paramString1);
    localIntent.putExtra("offer", paramInt);
    localIntent.putExtra("referrer", paramString2);
    localIntent.putExtra("referrer_cookie", paramString3);
    localIntent.putExtra("ext_referrer", paramString4);
    localIntent.putExtra("continue_url", paramString5);
    localIntent.putExtra("docId_to_purchase", paramString6);
    localIntent.setFlags(536936448);
    paramContext.startActivity(localIntent);
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
    return null;
  }

  public void goBack()
  {
    finish();
  }

  protected void hideLoadingIndicator()
  {
    findViewById(2131230808).setVisibility(4);
  }

  protected void onCreate(Bundle paramBundle)
  {
    setContentView(2130968672);
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    this.mAccount = ((Account)localIntent.getParcelableExtra("account"));
    this.mUrl = localIntent.getStringExtra("url");
    this.mOfferType = localIntent.getIntExtra("offer", -1);
    this.mReferrerUrl = localIntent.getStringExtra("referrer");
    this.mReferrerCookie = localIntent.getStringExtra("referrer_cookie");
    this.mExternalReferrer = localIntent.getStringExtra("ext_referrer");
    this.mContinueUrl = localIntent.getStringExtra("continue_url");
    this.mDocIdToPurchase = localIntent.getStringExtra("docId_to_purchase");
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
      finish();
    for (boolean bool = true; ; bool = super.onOptionsItemSelected(paramMenuItem))
      return bool;
  }

  protected void onReady(boolean paramBoolean)
  {
    if (getSupportFragmentManager().findFragmentById(2131230775) != null);
    while (true)
    {
      return;
      PurchaseFragment localPurchaseFragment = PurchaseFragment.newInstance(this.mAccount, this.mUrl, this.mOfferType, this.mReferrerUrl, this.mReferrerCookie, false, this.mExternalReferrer, this.mContinueUrl, this.mDocIdToPurchase);
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.add(2131230775, localPurchaseFragment);
      localFragmentTransaction.commit();
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    this.mStateSaved = true;
    super.onSaveInstanceState(paramBundle);
  }

  public void showErrorDialog(String paramString1, String paramString2, boolean paramBoolean)
  {
    ErrorDialog.show(getSupportFragmentManager(), paramString1, paramString2, paramBoolean);
  }

  public void updateBreadcrumb(String paramString)
  {
    this.mActionBar.updateBreadcrumb(paramString);
  }

  public void updateCurrentBackendId(int paramInt)
  {
    this.mActionBar.updateCurrentBackendId(paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.PurchaseDialog
 * JD-Core Version:    0.6.2
 */
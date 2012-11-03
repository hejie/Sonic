package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;

public class FinskyCreateInstrumentActivity extends InstrumentActivity
{
  private CustomActionBar mActionBar;
  private NavigationManager mNavigationManager = new FakeNavigationManager(this);

  public static Intent createIntent(String paramString1, CommonDevice.Instrument paramInstrument, int paramInt, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, boolean paramBoolean, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent(FinskyApp.get(), FinskyCreateInstrumentActivity.class);
    localIntent.putExtra("authAccount", paramString1);
    localIntent.putExtra("billing_flow", paramInstrument.getInstrumentFamily());
    localIntent.putExtra("billing_flow_version", BillingUtils.getFopVersion(paramInstrument));
    localIntent.putExtra("backend_id", paramInt);
    localIntent.putExtra("ui_mode", paramCreateInstrumentUiMode.toString());
    localIntent.putExtra("instrument_mode", InstrumentActivity.Mode.ADD);
    localIntent.putExtra("entry_point_menu", paramBoolean);
    localIntent.putExtra("referrer_url", paramString2);
    localIntent.putExtra("referrer_list_cookie", paramString3);
    return localIntent;
  }

  private void setupActionBar(Bundle paramBundle)
  {
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    int i = getIntent().getIntExtra("backend_id", 0);
    this.mActionBar.updateCurrentBackendId(i);
    if ((paramBundle != null) && (paramBundle.containsKey("last_title")))
      setTitle(paramBundle.getString("last_title"));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setupActionBar(paramBundle);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("last_title", this.mActionBar.getBreadcrumbText());
  }

  protected void setTitle(String paramString)
  {
    this.mActionBar.updateBreadcrumb(paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FinskyCreateInstrumentActivity
 * JD-Core Version:    0.6.2
 */
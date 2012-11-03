package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public class FlagItemDialog extends AuthenticatedActivity
  implements PageFragmentHost
{
  private CustomActionBar mActionBar;
  private NavigationManager mNavigationManager = new FakeNavigationManager(this);

  public static void show(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, FlagItemDialog.class);
    localIntent.putExtra("url", paramString);
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

  protected void onCreate(Bundle paramBundle)
  {
    setContentView(2130968672);
    super.onCreate(paramBundle);
    String str = getIntent().getStringExtra("url");
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    if (getSupportFragmentManager().findFragmentById(2131230775) != null);
    while (true)
    {
      return;
      FlagItemFragment localFlagItemFragment = FlagItemFragment.newInstance(str);
      FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.add(2131230775, localFlagItemFragment);
      localFragmentTransaction.commit();
    }
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
 * Qualified Name:     com.google.android.finsky.activities.FlagItemDialog
 * JD-Core Version:    0.6.2
 */
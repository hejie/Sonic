package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public class ReviewsActivity extends AuthenticatedActivity
  implements PageFragmentHost
{
  private CustomActionBar mActionBar;
  private Document mDocument;
  private NavigationManager mNavigationManager;

  public static void show(Context paramContext, Document paramDocument)
  {
    Intent localIntent = new Intent(paramContext, ReviewsActivity.class);
    localIntent.putExtra("finsky.ReviewsActivity.document", paramDocument);
    localIntent.setFlags(536870912);
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
    return this.mNavigationManager;
  }

  public void goBack()
  {
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    setContentView(2130968672);
    super.onCreate(paramBundle);
    this.mDocument = ((Document)getIntent().getParcelableExtra("finsky.ReviewsActivity.document"));
    this.mNavigationManager = new FakeNavigationManager(this);
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    this.mActionBar.updateBreadcrumb(this.mDocument.getTitle());
    this.mActionBar.updateCurrentBackendId(this.mDocument.getBackend());
    FragmentManager localFragmentManager = getSupportFragmentManager();
    if (localFragmentManager.findFragmentById(2131230775) == null)
    {
      ReviewsFragment localReviewsFragment = ReviewsFragment.newInstance(this, this.mDocument);
      FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
      localFragmentTransaction.replace(2131230775, localReviewsFragment);
      localFragmentTransaction.commit();
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      this.mNavigationManager.goUp();
    }
  }

  protected void onReady(boolean paramBoolean)
  {
  }

  public void showErrorDialog(String paramString1, String paramString2, boolean paramBoolean)
  {
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
 * Qualified Name:     com.google.android.finsky.activities.ReviewsActivity
 * JD-Core Version:    0.6.2
 */
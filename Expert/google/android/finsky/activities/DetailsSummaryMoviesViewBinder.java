package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DetailsSummaryMoviesViewBinder extends DetailsSummaryViewBinder
{
  private final Libraries mLibraries;

  public DetailsSummaryMoviesViewBinder(DfeToc paramDfeToc, Account paramAccount, Libraries paramLibraries)
  {
    super(paramDfeToc, paramAccount);
    this.mLibraries = paramLibraries;
  }

  private void setupListOfferButton(Account paramAccount, final List<Common.Offer> paramList, Button paramButton, int paramInt)
  {
    if (paramList.size() == 1)
      setupSingleOfferButton(paramAccount, (Common.Offer)paramList.get(0), paramButton);
    while (true)
    {
      return;
      Common.Offer localOffer = DocUtils.getLowestPricedOffer(paramList, true);
      final ArrayList localArrayList = Lists.newArrayList();
      for (int i = 0; i < paramList.size(); i++)
        localArrayList.add(this.mDoc);
      paramButton.setVisibility(0);
      Context localContext = this.mContext;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localOffer.getFormattedAmount();
      paramButton.setText(localContext.getString(paramInt, arrayOfObject).toUpperCase());
      paramButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FragmentManager localFragmentManager = DetailsSummaryMoviesViewBinder.this.mContainerFragment.getFragmentManager();
          if (localFragmentManager.findFragmentByTag("movie_offer_resolution_dialog") != null);
          while (true)
          {
            return;
            OfferResolutionDialog localOfferResolutionDialog = OfferResolutionDialog.newInstance(paramList, localArrayList, 2131165482, this.val$lowestOfferIndex);
            localOfferResolutionDialog.setTargetFragment(DetailsSummaryMoviesViewBinder.this.mContainerFragment, 0);
            localOfferResolutionDialog.show(localFragmentManager, "movie_offer_resolution_dialog");
          }
        }
      });
    }
  }

  private void setupSingleOfferButton(Account paramAccount, Common.Offer paramOffer, Button paramButton)
  {
    int i = -1;
    int j = paramOffer.getOfferType();
    switch (j)
    {
    case 2:
    case 5:
    case 6:
    default:
      if (i >= 0)
        break;
    case 4:
    case 3:
    case 7:
    case 1:
    }
    while (true)
    {
      return;
      i = 2131165470;
      break;
      i = 2131165471;
      break;
      i = 2131165476;
      break;
      i = 2131165475;
      break;
      paramButton.setVisibility(0);
      Context localContext = this.mContext;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramOffer.getFormattedAmount();
      paramButton.setText(localContext.getString(i, arrayOfObject).toUpperCase());
      paramButton.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(paramAccount, this.mDoc, j, this.mReferrer, this.mExternalReferrer, this.mContinueUrl));
    }
  }

  protected void setupActionButtons(boolean paramBoolean)
  {
    super.setupActionButtons(paramBoolean);
    Button localButton = (Button)findViewById(2131230903);
    localButton.setVisibility(8);
    final Account localAccount = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, this.mLibraries, this.mAccount);
    if (localAccount == null);
    while (true)
    {
      return;
      if (!this.mContext.getPackageManager().hasSystemFeature("com.google.android.tv"))
      {
        localButton.setVisibility(0);
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!IntentUtils.isConsumptionAppInstalled(DetailsSummaryMoviesViewBinder.this.mContext.getPackageManager(), DetailsSummaryMoviesViewBinder.this.mDoc.getBackend()))
              DetailsSummaryMoviesViewBinder.this.mNavigationManager.showAppNeededDialog(DetailsSummaryMoviesViewBinder.this.mDoc.getBackend());
            while (true)
            {
              return;
              Intent localIntent = IntentUtils.buildConsumptionAppManageItemIntent(DetailsSummaryMoviesViewBinder.this.mContext.getPackageManager(), DetailsSummaryMoviesViewBinder.this.mDoc, localAccount.name);
              DetailsSummaryMoviesViewBinder.this.mContext.startActivity(localIntent);
            }
          }
        });
        localButton.setText(localButton.getText().toString().toUpperCase());
      }
    }
  }

  protected void setupBuyButtons(Account paramAccount, Button paramButton1, Button paramButton2, boolean paramBoolean)
  {
    List localList = this.mDoc.getAvailableOffers();
    if (localList.size() <= 2)
    {
      if (localList.size() > 0)
        setupSingleOfferButton(paramAccount, (Common.Offer)localList.get(0), paramButton1);
      if (localList.size() > 1)
        setupSingleOfferButton(paramAccount, (Common.Offer)localList.get(1), paramButton2);
    }
    while (true)
    {
      return;
      ArrayList localArrayList1 = Lists.newArrayList();
      ArrayList localArrayList2 = Lists.newArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Common.Offer localOffer = (Common.Offer)localIterator.next();
        switch (localOffer.getOfferType())
        {
        case 2:
        case 5:
        case 6:
        default:
          break;
        case 1:
        case 7:
          localArrayList2.add(localOffer);
          break;
        case 3:
        case 4:
          localArrayList1.add(localOffer);
        }
      }
      if (!localArrayList1.isEmpty())
        setupListOfferButton(paramAccount, localArrayList1, paramButton1, 2131165472);
      if (!localArrayList2.isEmpty())
        setupListOfferButton(paramAccount, localArrayList2, paramButton2, 2131165477);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsSummaryMoviesViewBinder
 * JD-Core Version:    0.6.2
 */
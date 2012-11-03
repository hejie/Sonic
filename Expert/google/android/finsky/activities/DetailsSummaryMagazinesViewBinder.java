package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DetailsSummaryMagazinesViewBinder extends DetailsSummaryViewBinder
{
  private Document mIssueDoc;
  private List<Document> mSubscriptions;

  public DetailsSummaryMagazinesViewBinder(DfeToc paramDfeToc, Account paramAccount)
  {
    super(paramDfeToc, paramAccount);
  }

  private void setupSingleOfferButton(Account paramAccount, Document paramDocument, Common.Offer paramOffer, Button paramButton, int paramInt)
  {
    paramButton.setVisibility(0);
    Context localContext = this.mContext;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramOffer.getFormattedAmount();
    paramButton.setText(localContext.getString(paramInt, arrayOfObject).toUpperCase());
    paramButton.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(paramAccount, paramDocument, paramOffer.getOfferType(), this.mReferrer, this.mExternalReferrer, this.mContinueUrl));
  }

  private void setupSubscriptionListOfferButton(Account paramAccount, List<Document> paramList, Button paramButton)
  {
    final ArrayList localArrayList1 = Lists.newArrayList();
    final ArrayList localArrayList2 = Lists.newArrayList();
    Object localObject = null;
    long l1 = 2147483647L;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      localArrayList2.add(localDocument);
      Common.Offer localOffer = (Common.Offer)localDocument.getAvailableOffers().get(0);
      if (localOffer.hasFormattedAmount())
      {
        long l2 = localOffer.getMicros();
        if (l2 < l1)
        {
          l1 = l2;
          localObject = localOffer;
        }
      }
      localArrayList1.add(localOffer);
    }
    paramButton.setVisibility(0);
    if (localArrayList1.size() == 1)
    {
      Context localContext = this.mContext;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = ((Common.Offer)localArrayList1.get(0)).getFormattedAmount();
      paramButton.setText(localContext.getString(2131165480, arrayOfObject).toUpperCase());
    }
    while (true)
    {
      paramButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          FragmentManager localFragmentManager = DetailsSummaryMagazinesViewBinder.this.mContainerFragment.getFragmentManager();
          if (localFragmentManager.findFragmentByTag("magazine_offer_resolution_dialog") != null)
            return;
          if (localArrayList1.size() == 1);
          for (int i = 2131165484; ; i = 2131165483)
          {
            OfferResolutionDialog localOfferResolutionDialog = OfferResolutionDialog.newInstance(localArrayList1, localArrayList2, i, this.val$lowestOfferIndex);
            localOfferResolutionDialog.setTargetFragment(DetailsSummaryMagazinesViewBinder.this.mContainerFragment, 0);
            localOfferResolutionDialog.show(localFragmentManager, "magazine_offer_resolution_dialog");
            break;
          }
        }
      });
      return;
      paramButton.setText(this.mContext.getString(2131165481).toUpperCase());
    }
  }

  public void bind(Document paramDocument, boolean paramBoolean, View[] paramArrayOfView)
  {
    if (paramDocument.getDocumentType() == 17);
    for (this.mIssueDoc = paramDocument; ; this.mIssueDoc = paramDocument.getChildAt(0))
      do
      {
        this.mSubscriptions = DocUtils.getMagazineSubscriptions(paramDocument, this.mDfeToc, FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount));
        super.bind(paramDocument, paramBoolean, paramArrayOfView);
        return;
      }
      while ((paramDocument.getDocumentType() != 16) || (paramDocument.getChildCount() <= 0));
  }

  protected boolean displayActionButtonsIfNecessary(Button paramButton1, Button paramButton2, Button paramButton3, Button paramButton4)
  {
    Libraries localLibraries = FinskyApp.get().getLibraries();
    AccountLibrary localAccountLibrary = localLibraries.getAccountLibrary(this.mAccount);
    boolean bool = false;
    if (this.mIssueDoc != null)
    {
      Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(this.mIssueDoc, localLibraries, this.mAccount);
      if (localAccount2 != null)
        configureLaunchButton(paramButton1, this.mIssueDoc, localAccount2);
    }
    else
    {
      Account localAccount1 = LibraryUtils.getOwnerWithCurrentAccount(this.mSubscriptions, localLibraries, this.mAccount);
      if (localAccount1 == null)
        break label166;
      configureLaunchButton(paramButton1, this.mDoc, localAccount1);
      bool = true;
    }
    while (true)
    {
      return bool;
      Common.Offer localOffer = DocUtils.getMagazineIssueOffer(this.mIssueDoc, this.mDfeToc, localAccountLibrary);
      if (localOffer == null)
        break;
      if (this.mContext.getResources().getBoolean(2131296262));
      for (int i = 2131165479; ; i = 2131165478)
      {
        setupSingleOfferButton(this.mAccount, this.mIssueDoc, localOffer, paramButton3, i);
        bool = true;
        break;
      }
      label166: if (this.mSubscriptions.size() != 0)
      {
        setupSubscriptionListOfferButton(this.mAccount, this.mSubscriptions, paramButton2);
        bool = true;
      }
    }
  }

  protected void setupActionButtons(boolean paramBoolean)
  {
    super.setupActionButtons(paramBoolean);
    ((Button)findViewById(2131230903)).setVisibility(8);
  }

  protected void setupCreator(DecoratedTextView paramDecoratedTextView)
  {
    paramDecoratedTextView.setText(this.mDoc.getSubtitle().toUpperCase());
  }

  protected boolean shouldDisplayOfferNote()
  {
    Libraries localLibraries = FinskyApp.get().getLibraries();
    if (LibraryUtils.getOwnerWithCurrentAccount(this.mSubscriptions, localLibraries, this.mAccount) == null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsSummaryMagazinesViewBinder
 * JD-Core Version:    0.6.2
 */
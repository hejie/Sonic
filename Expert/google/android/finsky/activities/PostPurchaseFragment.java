package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.ConsumptionAppButton;
import com.google.android.finsky.layout.GooglePlusShareSection;
import com.google.android.finsky.layout.ListingView;
import com.google.android.finsky.layout.PostPurchaseSummary;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.navigationmanager.ConsumptionUtils;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;

public class PostPurchaseFragment extends UrlBasedPageFragment
  implements SimpleAlertDialog.Listener, Libraries.Listener
{
  private Account mAccount;
  private DfeDetails mDfeDetails;
  private Document mDoc;
  private int mOfferType;

  public static PostPurchaseFragment newInstance(String paramString1, int paramInt, String paramString2)
  {
    PostPurchaseFragment localPostPurchaseFragment = new PostPurchaseFragment();
    localPostPurchaseFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localPostPurchaseFragment.setArgument("offerType", paramInt);
    localPostPurchaseFragment.setArgument("accountName", paramString2);
    return localPostPurchaseFragment;
  }

  private void onDocumentLoaded(Document paramDocument)
  {
    VolleyError localVolleyError;
    String str;
    if (paramDocument == null)
    {
      localVolleyError = this.mDfeDetails.getVolleyError();
      if (localVolleyError == null)
      {
        str = this.mContext.getString(2131165288);
        this.mPageFragmentHost.showErrorDialog(null, str, true);
      }
    }
    while (true)
    {
      return;
      str = ErrorStrings.get(this.mContext, localVolleyError);
      break;
      this.mDoc = paramDocument;
      switchToData();
      this.mAccount = AccountHandler.findAccount(getArguments().getString("accountName"), this.mContext);
      this.mOfferType = getArguments().getInt("offerType");
      onDataChanged();
      getView().requestFocus();
    }
  }

  protected int getLayoutRes()
  {
    return 2130968782;
  }

  protected boolean isDataReady()
  {
    if (this.mDoc != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (this.mDoc != null)
      onDocumentLoaded(this.mDoc);
    while (true)
    {
      if (isDataReady())
        rebindViews();
      return;
      if ((paramBundle != null) && (paramBundle.containsKey("doc")))
      {
        onDocumentLoaded((Document)paramBundle.getParcelable("doc"));
      }
      else
      {
        switchToLoadingImmediately();
        this.mDfeDetails = new DfeDetails(this.mDfeApi, this.mUrl);
        this.mDfeDetails.addDataChangedListener(this);
        this.mDfeDetails.addErrorListener(this);
      }
    }
  }

  public void onAllLibrariesLoaded()
  {
    if ((this.mDoc != null) && (this.mDoc.getBackend() == 6) && (this.mDoc.getDocumentType() == 15))
      rebindViews();
  }

  public void onDataChanged()
  {
    if (this.mDoc == null)
      onDocumentLoaded(this.mDfeDetails.getDocument());
    while (true)
    {
      super.onDataChanged();
      return;
      FinskyLog.d("Ignoring soft TTL refresh.", new Object[0]);
    }
  }

  public void onDestroyView()
  {
    if (this.mDfeDetails != null)
    {
      this.mDfeDetails.removeDataChangedListener(this);
      this.mDfeDetails.removeErrorListener(this);
    }
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    onAllLibrariesLoaded();
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 1)
    {
      String str = paramBundle.getString("dialog_details_url");
      startActivity(IntentUtils.createViewDocumentUrlIntent(this.mContext, str));
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mDoc != null)
      paramBundle.putParcelable("doc", this.mDoc);
  }

  public void onStart()
  {
    super.onStart();
    FinskyApp.get().getLibraries().addListener(this);
  }

  public void onStop()
  {
    FinskyApp.get().getLibraries().removeListener(this);
    super.onStop();
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(null);
    if (this.mDoc != null)
      this.mPageFragmentHost.updateCurrentBackendId(this.mDoc.getBackend());
  }

  public void rebindViews()
  {
    if (this.mDoc == null)
      return;
    int i = this.mDoc.getBackend();
    int j = this.mDoc.getDocumentType();
    View localView1 = getView();
    ((PostPurchaseSummary)localView1.findViewById(2131230881)).bind(this.mDoc, this.mBitmapLoader);
    View localView2 = localView1.findViewById(2131230879);
    localView2.setBackgroundColor(CorpusResourceUtils.getBackendDarkColor(localView2.getContext(), i));
    Button localButton = (Button)localView1.findViewById(2131230970);
    localButton.setText(localButton.getText().toString().toUpperCase());
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PostPurchaseFragment.this.mPageFragmentHost.goBack();
      }
    };
    localButton.setOnClickListener(local1);
    ConsumptionAppButton localConsumptionAppButton = (ConsumptionAppButton)localView1.findViewById(2131230900);
    int k = CorpusResourceUtils.getOpenButtonStringId(i);
    localConsumptionAppButton.setText(localConsumptionAppButton.getResources().getString(k).toUpperCase());
    localConsumptionAppButton.setDocumentBackend(this.mDoc.getBackend());
    View.OnClickListener local2 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ConsumptionUtils.openItem(PostPurchaseFragment.this.mContext, PostPurchaseFragment.this, PostPurchaseFragment.this.getActivity().getSupportFragmentManager(), PostPurchaseFragment.this.mAccount, PostPurchaseFragment.this.mDoc);
      }
    };
    localConsumptionAppButton.setOnClickListener(local2);
    ListingView localListingView = (ListingView)localView1.findViewById(2131231144);
    localListingView.setVisibility(8);
    label228: GooglePlusShareSection localGooglePlusShareSection;
    int m;
    switch (j)
    {
    default:
      ((ListingView)localView1.findViewById(2131231145)).bindHowToConsume(this.mUrl, this.mDoc);
      localGooglePlusShareSection = (GooglePlusShareSection)localView1.findViewById(2131230873);
      if (localGooglePlusShareSection != null)
      {
        if ((j != 2) && (j != 4))
          break label365;
        m = 1;
        label279: if (m == 0)
          break label371;
        localGooglePlusShareSection.bind(this.mDoc, this, this.mUrl);
      }
      break;
    case 6:
    case 15:
    }
    while (true)
    {
      rebindActionBar();
      break;
      localListingView.bindRentalTerms(this.mDoc, this.mOfferType);
      break label228;
      LibrarySubscriptionEntry localLibrarySubscriptionEntry = FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount).getMagazinesSubscriptionEntry(this.mDoc.getBackendDocId());
      if (localLibrarySubscriptionEntry == null)
        break label228;
      localListingView.bindSubscriptionInfo(this.mDoc, localLibrarySubscriptionEntry);
      break label228;
      label365: m = 0;
      break label279;
      label371: localGooglePlusShareSection.setVisibility(8);
    }
  }

  protected void requestData()
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.PostPurchaseFragment
 * JD-Core Version:    0.6.2
 */
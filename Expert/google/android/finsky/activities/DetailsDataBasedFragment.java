package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Nfc;
import com.google.android.finsky.utils.Nfc.NfcHandler;

public abstract class DetailsDataBasedFragment extends UrlBasedPageFragment
  implements OfferResolutionDialog.OfferResolutionListener
{
  private DfeDetails mDetailsData;
  private Document mDocument;
  private boolean mHaveLoggedBefore = false;
  private final Libraries mLibraries = FinskyApp.get().getLibraries();
  private Nfc.NfcHandler mNfcHandler;
  private long mPageCreationTime;
  private String mReferrer;
  protected Bundle mSavedInstanceState = new Bundle();

  private void logPageView()
  {
    if (!this.mHaveLoggedBefore)
    {
      String str = this.mUrl;
      if (!LibraryUtils.isAvailable(getDocument(), getToc(), getLibraries()))
        str = str + "&availability=" + getDocument().getAvailabilityRestriction();
      FinskyApp.get().getAnalytics().logPageView(this.mReferrer, getDocument().getCookie(), str);
      this.mHaveLoggedBefore = true;
    }
  }

  protected DfeDetails getDetailsData()
  {
    return this.mDetailsData;
  }

  public Document getDocument()
  {
    return this.mDocument;
  }

  protected final Libraries getLibraries()
  {
    return this.mLibraries;
  }

  protected String getReferrer()
  {
    return this.mReferrer;
  }

  protected boolean hasDetailsDataLoaded()
  {
    if ((this.mDetailsData != null) && (this.mDetailsData.isReady()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected final boolean isDataReady()
  {
    if (this.mDocument != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mNfcHandler = Nfc.getHandler(this);
    this.mDocument = ((Document)getArguments().getParcelable("finsky.DetailsDataBasedFragment.document"));
    if (paramBundle != null)
      this.mSavedInstanceState = paramBundle;
    switchToBlank();
    if (this.mDetailsData == null)
    {
      requestData();
      rebindActionBar();
    }
    while (true)
    {
      onDataChanged();
      return;
      this.mDetailsData.addDataChangedListener(this);
      this.mDetailsData.addErrorListener(this);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    this.mPageCreationTime = System.currentTimeMillis();
    super.onCreate(paramBundle);
  }

  public void onDataChanged()
  {
    if ((isAdded()) && (isDataReady()))
      if (hasDetailsDataLoaded())
      {
        if (this.mDetailsData.getDocument() != null)
          break label65;
        this.mPageFragmentHost.showErrorDialog(null, this.mContext.getString(2131165457), true);
      }
    while (true)
    {
      this.mNfcHandler.onDataChanged();
      super.onDataChanged();
      return;
      label65: updateDocument(this.mDetailsData.getDocument());
      logPageView();
    }
  }

  public void onDestroyView()
  {
    this.mHaveLoggedBefore = false;
    if (this.mDetailsData != null)
    {
      this.mDetailsData.removeDataChangedListener(this);
      this.mDetailsData.removeErrorListener(this);
    }
    super.onDestroyView();
  }

  public void onOfferSelected(Document paramDocument, int paramInt)
  {
    this.mNavigationManager.buy(this.mDfeApi.getAccount(), paramDocument, paramInt, getReferrer(), null, null, false);
  }

  public void onPause()
  {
    super.onPause();
    PreviewController.setupOnBackStackChangedListener(this.mNavigationManager);
    this.mNfcHandler.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.mNfcHandler.onResume();
  }

  public final void onSaveInstanceState(Bundle paramBundle)
  {
    recordState(this.mSavedInstanceState);
    if (this.mSavedInstanceState != null)
      paramBundle.putAll(this.mSavedInstanceState);
    super.onSaveInstanceState(paramBundle);
  }

  protected final void rebindViews()
  {
    rebindViews(this.mSavedInstanceState);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = getClass().getSimpleName();
    arrayOfObject[1] = Long.valueOf(System.currentTimeMillis() - this.mPageCreationTime);
    arrayOfObject[2] = Boolean.valueOf(hasDetailsDataLoaded());
    FinskyLog.d("Page [class=%s] loaded in [%s ms] (partial? %b)", arrayOfObject);
  }

  protected abstract void rebindViews(Bundle paramBundle);

  protected final void recordState()
  {
    recordState(this.mSavedInstanceState);
  }

  protected abstract void recordState(Bundle paramBundle);

  protected void requestData()
  {
    if (this.mDetailsData != null)
    {
      this.mDetailsData.removeDataChangedListener(this);
      this.mDetailsData.removeErrorListener(this);
    }
    this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, getArguments().getString("finsky.DetailsDataBasedFragment.cookie"));
    this.mReferrer = getArguments().getString("finsk.DetailsDatabasedFragment.referrer");
    this.mDetailsData.addDataChangedListener(this);
    this.mDetailsData.addErrorListener(this);
  }

  protected void setInitialDocument(Document paramDocument)
  {
    setArgument("finsky.DetailsDataBasedFragment.document", paramDocument);
  }

  protected void updateDocument(Document paramDocument)
  {
    this.mDocument = paramDocument;
    int i = -2147483648;
    if (this.mDocument.getBackend() == 2)
      i = 3;
    getActivity().setVolumeControlStream(i);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsDataBasedFragment
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.Rev.Review;

public class OwnedActions extends LinearLayout
{
  private final AppStates mAppStates;
  private AutoUpdateSection mAutoUpdateSection;
  private Document mDocument;
  private final LayoutInflater mInflater;
  private final Installer mInstaller;
  private final Libraries mLibraries;
  private RateReviewSection mRateReviewSection;
  private View mSeparator;

  public OwnedActions(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mInflater = LayoutInflater.from(paramContext);
    this.mInstaller = FinskyApp.get().getInstaller();
    this.mLibraries = FinskyApp.get().getLibraries();
    this.mAppStates = FinskyApp.get().getAppStates();
  }

  private void configureAutoUpdateSection(Fragment paramFragment)
  {
    this.mAutoUpdateSection = ((AutoUpdateSection)this.mInflater.inflate(2130968583, this, false));
    addView(this.mAutoUpdateSection);
    this.mAutoUpdateSection.bind(getPackageName(), paramFragment, this.mLibraries, this.mAppStates, this.mInstaller);
  }

  private void configureRateReviewSection(DfeDetails paramDfeDetails, NavigationManager paramNavigationManager, Fragment paramFragment, boolean paramBoolean)
  {
    this.mRateReviewSection = ((RateReviewSection)this.mInflater.inflate(2130968805, this, false));
    addView(this.mRateReviewSection);
    Rev.Review localReview = null;
    if (paramBoolean)
      localReview = paramDfeDetails.getUserReview();
    this.mRateReviewSection.configure(this.mDocument, localReview, paramFragment, this.mLibraries);
  }

  private void configureSeparator()
  {
    this.mSeparator = this.mInflater.inflate(2130968650, this, false);
    addView(this.mSeparator);
  }

  private String getPackageName()
  {
    DocDetails.AppDetails localAppDetails = this.mDocument.getAppDetails();
    if (localAppDetails != null);
    for (String str = localAppDetails.getPackageName(); ; str = null)
      return str;
  }

  private boolean shouldShowAutoUpdateSection()
  {
    int i = 1;
    if (this.mDocument.getDocumentType() == i);
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  private void updateVisibility()
  {
    int i = 0;
    if (shouldShowAutoUpdateSection())
    {
      this.mAutoUpdateSection.updateVisibility(getPackageName(), this.mLibraries, this.mAppStates, this.mInstaller);
      if (this.mAutoUpdateSection.getVisibility() == 0)
      {
        i = 1;
        this.mSeparator.setVisibility(this.mAutoUpdateSection.getVisibility());
      }
    }
    else
    {
      this.mRateReviewSection.updateVisibility(this.mLibraries, this.mDocument);
      if (this.mRateReviewSection.getVisibility() != 0)
        break label105;
    }
    label105: for (int j = 1; ; j = 0)
    {
      if ((i == 0) && (j == 0))
        setVisibility(8);
      return;
      i = 0;
      break;
    }
  }

  public void setDocument(Document paramDocument, DfeDetails paramDfeDetails, NavigationManager paramNavigationManager, Fragment paramFragment, boolean paramBoolean)
  {
    this.mDocument = paramDocument;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      setVisibility(i);
      removeAllViews();
      if (shouldShowAutoUpdateSection())
      {
        configureAutoUpdateSection(paramFragment);
        configureSeparator();
      }
      configureRateReviewSection(paramDfeDetails, paramNavigationManager, paramFragment, paramBoolean);
      updateVisibility();
      return;
    }
  }

  public void updateRating(int paramInt)
  {
    this.mRateReviewSection.updateRating(paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.OwnedActions
 * JD-Core Version:    0.6.2
 */
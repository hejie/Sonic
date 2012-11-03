package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.LibraryUtils;
import java.text.NumberFormat;

public class DetailsSummaryViewBinder
{
  private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance();
  protected final Account mAccount;
  private int mBadgeOverrideRes = -1;
  protected boolean mBindingDynamicSection;
  private BitmapLoader mBitmapLoader;
  protected ViewGroup mButtonSection;
  protected PageFragment mContainerFragment;
  protected Context mContext;
  protected String mContinueUrl;
  protected DfeToc mDfeToc;
  protected Document mDoc;
  protected ViewGroup mDynamicSection;
  protected String mExternalReferrer;
  protected boolean mHideDynamicFeatures;
  protected boolean mIsPendingRefund;
  private View[] mLayouts;
  protected NavigationManager mNavigationManager;
  protected String mReferrer;
  protected boolean mReturnAfterPurchase;

  public DetailsSummaryViewBinder(DfeToc paramDfeToc, Account paramAccount)
  {
    this.mAccount = paramAccount;
    this.mDfeToc = paramDfeToc;
  }

  private void setupOfferNote()
  {
    TextView localTextView = (TextView)findViewById(2131230919);
    if (localTextView != null)
    {
      if (!shouldDisplayOfferNote())
        break label47;
      String str = this.mDoc.getOfferNote();
      if (!TextUtils.isEmpty(str))
      {
        localTextView.setText(str);
        localTextView.setVisibility(0);
      }
    }
    while (true)
    {
      return;
      label47: localTextView.setVisibility(8);
    }
  }

  public void bind(Document paramDocument, boolean paramBoolean, View[] paramArrayOfView)
  {
    this.mLayouts = paramArrayOfView;
    this.mDoc = paramDocument;
    this.mBindingDynamicSection = paramBoolean;
    setupItemDetails();
    if (paramBoolean)
    {
      setupOfferNote();
      this.mDynamicSection = ((ViewGroup)findViewById(2131230959));
      this.mButtonSection = ((ViewGroup)findViewById(2131230920));
      syncDynamicSection();
    }
  }

  protected void configureLaunchButton(Button paramButton, Document paramDocument, Account paramAccount)
  {
    paramButton.setVisibility(0);
    paramButton.setText(CorpusResourceUtils.getOpenButtonStringId(paramDocument.getBackend()));
    paramButton.setOnClickListener(this.mNavigationManager.getOpenClickListener(paramDocument, paramAccount));
  }

  protected boolean displayActionButtonsIfNecessary(Button paramButton1, Button paramButton2, Button paramButton3, Button paramButton4)
  {
    Libraries localLibraries = FinskyApp.get().getLibraries();
    AccountLibrary localAccountLibrary = localLibraries.getAccountLibrary(this.mAccount);
    Account localAccount = LibraryUtils.getOwnerWithCurrentAccount(this.mDoc, localLibraries, this.mAccount);
    if (localAccount != null)
      configureLaunchButton(paramButton1, this.mDoc, localAccount);
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      if (LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, localAccountLibrary))
        break;
    }
    setupBuyButtons(this.mAccount, paramButton2, paramButton3, false);
    if ((this.mDoc.hasSample()) && (paramButton4 != null))
    {
      paramButton4.setVisibility(0);
      if (!LibraryUtils.isOfferOwned(this.mDoc, localAccountLibrary, 2))
        break label149;
      paramButton4.setOnClickListener(this.mNavigationManager.getOpenClickListener(this.mDoc, this.mAccount));
    }
    while (true)
    {
      bool = true;
      break;
      label149: paramButton4.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(this.mAccount, this.mDoc, 2, this.mReferrer, this.mExternalReferrer, this.mContinueUrl));
    }
  }

  protected View findViewById(int paramInt)
  {
    View[] arrayOfView = this.mLayouts;
    int i = arrayOfView.length;
    int j = 0;
    View localView1;
    if (j < i)
    {
      View localView2 = arrayOfView[j];
      if (localView2 == null);
      do
      {
        j++;
        break;
        localView1 = localView2.findViewById(paramInt);
      }
      while (localView1 == null);
    }
    while (true)
    {
      return localView1;
      localView1 = null;
    }
  }

  protected String getBuyButtonString(boolean paramBoolean, int paramInt)
  {
    String str;
    if (paramBoolean)
      str = this.mContext.getString(2131165446);
    while (true)
    {
      return str;
      if (!this.mDoc.needsCheckoutFlow(paramInt))
      {
        if (this.mDoc.getBackend() == 3)
          str = this.mContext.getString(2131165446);
        else if (this.mDoc.getBackend() == 1)
          str = this.mContext.getString(2131165487);
      }
      else
        str = this.mDoc.getFormattedPrice(1);
    }
  }

  public void hideDynamicFeatures()
  {
    this.mHideDynamicFeatures = true;
  }

  public void init(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, PageFragment paramPageFragment, boolean paramBoolean1, String paramString1, String paramString2, String paramString3, boolean paramBoolean2)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mContainerFragment = paramPageFragment;
    this.mReferrer = paramString1;
    this.mExternalReferrer = paramString2;
    this.mContinueUrl = paramString3;
    this.mReturnAfterPurchase = paramBoolean2;
  }

  public void onDestroyView()
  {
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
  }

  public void refresh()
  {
    bind(this.mDoc, this.mBindingDynamicSection, this.mLayouts);
  }

  protected void resetDynamicStatus()
  {
    int i = this.mDynamicSection.getChildCount();
    for (int j = 0; j < i; j++)
      this.mDynamicSection.getChildAt(j).setVisibility(8);
    this.mDynamicSection.setVisibility(8);
  }

  public void setBadgeOverride(int paramInt)
  {
    this.mBadgeOverrideRes = paramInt;
  }

  protected void setupActionButtons(boolean paramBoolean)
  {
    Button localButton1 = (Button)findViewById(2131230899);
    Button localButton2 = (Button)findViewById(2131230897);
    Button localButton3 = (Button)findViewById(2131230898);
    Button localButton4 = (Button)findViewById(2131230900);
    localButton2.setVisibility(8);
    localButton4.setVisibility(8);
    if (localButton1 != null)
      localButton1.setVisibility(8);
    localButton2.setVisibility(8);
    if (localButton3 != null)
      localButton3.setVisibility(8);
    if (this.mHideDynamicFeatures);
    while (true)
    {
      return;
      if ((!paramBoolean) && (displayActionButtonsIfNecessary(localButton4, localButton2, localButton3, localButton1)))
      {
        this.mButtonSection.setVisibility(0);
        if (localButton1 != null)
          localButton1.setText(localButton1.getText().toString().toUpperCase());
        localButton2.setText(localButton2.getText().toString().toUpperCase());
        localButton3.setText(localButton3.getText().toString().toUpperCase());
        localButton4.setText(localButton4.getText().toString().toUpperCase());
      }
    }
  }

  protected void setupBuyButtons(Account paramAccount, Button paramButton1, Button paramButton2, boolean paramBoolean)
  {
    if (this.mDoc.getOffer(1) != null)
    {
      paramButton1.setVisibility(0);
      paramButton1.setText(getBuyButtonString(paramBoolean, 1));
      paramButton1.setOnClickListener(this.mNavigationManager.getBuyImmediateClickListener(paramAccount, this.mDoc, 1, this.mReferrer, this.mExternalReferrer, this.mContinueUrl));
    }
  }

  protected void setupCreator(DecoratedTextView paramDecoratedTextView)
  {
    paramDecoratedTextView.setText(this.mDoc.getCreator().toUpperCase());
    BadgeUtils.configureCreatorBadge(this.mDoc, this.mBitmapLoader, paramDecoratedTextView, this.mBadgeOverrideRes);
  }

  protected void setupItemDetails()
  {
    TextView localTextView1 = (TextView)findViewById(2131230957);
    if (localTextView1 != null)
    {
      localTextView1.setText(this.mDoc.getTitle());
      localTextView1.setSelected(true);
    }
    DecoratedTextView localDecoratedTextView = (DecoratedTextView)findViewById(2131230958);
    if (localDecoratedTextView != null)
      setupCreator(localDecoratedTextView);
    ImageView localImageView = (ImageView)findViewById(2131230964);
    int i;
    View localView;
    if (localImageView != null)
    {
      if ((localDecoratedTextView != null) && (localDecoratedTextView.useWhitescale()))
      {
        i = 2130837655;
        localImageView.setImageResource(i);
      }
    }
    else
    {
      localView = findViewById(2131230916);
      if (localView != null)
      {
        RatingBar localRatingBar = (RatingBar)localView.findViewById(2131230917);
        TextView localTextView2 = (TextView)localView.findViewById(2131230918);
        if ((!this.mDoc.hasRating()) || (this.mDoc.getRatingCount() <= 0L))
          break label283;
        localView.setVisibility(0);
        localRatingBar.setRating(this.mDoc.getStarRating());
        localTextView2.setText(NUMBER_FORMATTER.format(this.mDoc.getRatingCount()));
      }
    }
    while (true)
    {
      DocImageView localDocImageView = (DocImageView)findViewById(2131230909);
      ViewGroup.LayoutParams localLayoutParams = localDocImageView.getLayoutParams();
      if (localLayoutParams.height == 0)
        localLayoutParams.height = CorpusResourceUtils.getRegularDetailsIconHeight(this.mContext, this.mDoc.getBackend());
      localDocImageView.bind(this.mDoc, this.mBitmapLoader);
      localDocImageView.setFocusable(this.mBindingDynamicSection);
      if (this.mBindingDynamicSection)
        localDocImageView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (DetailsSummaryViewBinder.this.mDoc.getImages(4) != null)
              DetailsSummaryViewBinder.this.mNavigationManager.goToImagesLightbox(DetailsSummaryViewBinder.this.mDoc, 0, 4);
            while (true)
            {
              return;
              if (DetailsSummaryViewBinder.this.mDoc.getImages(0) != null)
                DetailsSummaryViewBinder.this.mNavigationManager.goToImagesLightbox(DetailsSummaryViewBinder.this.mDoc, 0, 0);
            }
          }
        });
      return;
      i = 2130837654;
      break;
      label283: localView.setVisibility(4);
    }
  }

  protected boolean shouldDisplayOfferNote()
  {
    return true;
  }

  protected void showDynamicStatus(int paramInt)
  {
    showDynamicStatus(this.mContext.getResources().getString(paramInt));
  }

  protected void showDynamicStatus(String paramString)
  {
    TextView localTextView = (TextView)this.mDynamicSection.findViewById(2131230962);
    this.mButtonSection.setVisibility(8);
    this.mDynamicSection.setVisibility(0);
    localTextView.setVisibility(0);
    localTextView.setText(paramString);
  }

  protected void syncDynamicSection()
  {
    resetDynamicStatus();
    AccountLibrary localAccountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount);
    if (!LibraryUtils.isAvailable(this.mDoc, this.mDfeToc, localAccountLibrary))
      hideDynamicFeatures();
    if (this.mHideDynamicFeatures);
    while (true)
    {
      return;
      this.mDynamicSection.setBackgroundColor(CorpusResourceUtils.getBackendHintColor(this.mContext, this.mDoc.getBackend()));
      if (this.mIsPendingRefund)
        showDynamicStatus(2131165610);
      else
        setupActionButtons(false);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsSummaryViewBinder
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.OfferResolutionDialog;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.DocDetails.TvEpisodeDetails;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.ArrayList;
import java.util.List;

public class EpisodeSnippet extends CheckedRelativeLayout
{
  private View mAddedDrawable;
  private TextView mAddedState;
  private final int mBaseRowHeight;
  private BitmapLoader mBitmapLoader;
  private Button mBuyButton;
  private View mCollapsedContent;
  private OnCollapsedStateChanged mCollapsedStateChangedListener;
  private Document mEpisode;
  private TextView mEpisodeNumber;
  private TextView mEpisodeTitle;
  private View mExpandedContent;
  private TextView mExpandedDescription;
  private HeroGraphicView mExpandedEpisodeScreencap;
  private ViewStub mExpandedViewStub;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private boolean mIsNewPurchase;
  private int mMaxExpandedHeight;
  private NavigationManager mNavigationManager;
  private PageFragment mParentFragment;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private final Runnable mScrollerRunnable;
  private Document mSeasonDocument;

  public EpisodeSnippet(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mBaseRowHeight = paramContext.getResources().getDimensionPixelSize(2131427337);
    this.mScrollerRunnable = new Runnable()
    {
      public void run()
      {
        int i = EpisodeSnippet.this.getTop();
        View localView;
        for (Object localObject = (View)EpisodeSnippet.this.getParent(); ; localObject = localView)
        {
          localView = (View)((View)localObject).getParent();
          if ((localView instanceof ObservableScrollView))
          {
            ((ObservableScrollView)((View)localObject).getParent()).smoothScrollTo(0, i - EpisodeSnippet.this.mBaseRowHeight);
            return;
          }
          i += ((View)localObject).getTop();
        }
      }
    };
  }

  public static void handleBuyButtonClick(NavigationManager paramNavigationManager, Document paramDocument, PageFragment paramPageFragment)
  {
    Account localAccount1 = FinskyApp.get().getCurrentAccount();
    Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(paramDocument, FinskyApp.get().getLibraries(), localAccount1);
    if (localAccount2 != null)
      paramNavigationManager.openItem(localAccount2, paramDocument);
    while (true)
    {
      return;
      ArrayList localArrayList1 = Lists.newArrayList();
      Common.Offer localOffer1 = paramDocument.getOffer(1);
      if (localOffer1 != null)
        localArrayList1.add(localOffer1);
      Common.Offer localOffer2 = paramDocument.getOffer(7);
      if (localOffer2 != null)
        localArrayList1.add(localOffer2);
      if (localArrayList1.size() == 2)
      {
        FragmentManager localFragmentManager = paramPageFragment.getFragmentManager();
        if (localFragmentManager.findFragmentByTag("tv_offer_resolution_dialog") == null)
        {
          ArrayList localArrayList2 = Lists.newArrayList();
          for (int i = 0; i < localArrayList1.size(); i++)
            localArrayList2.add(paramDocument);
          OfferResolutionDialog localOfferResolutionDialog = OfferResolutionDialog.newInstance(localArrayList1, localArrayList2, 2131165482, localArrayList1.indexOf(DocUtils.getLowestPricedOffer(localArrayList1, true)));
          localOfferResolutionDialog.setTargetFragment(paramPageFragment, 0);
          localOfferResolutionDialog.show(localFragmentManager, "tv_offer_resolution_dialog");
        }
      }
      else if (localArrayList1.size() == 1)
      {
        paramNavigationManager.buy(localAccount1, paramDocument, ((Common.Offer)localArrayList1.get(0)).getOfferType(), null, null, null);
      }
    }
  }

  private void inflateViewStubIfNecessary()
  {
    if (this.mExpandedContent == null)
    {
      this.mExpandedContent = this.mExpandedViewStub.inflate();
      this.mExpandedDescription = ((TextView)findViewById(2131231018));
      this.mExpandedEpisodeScreencap = ((HeroGraphicView)findViewById(2131231017));
    }
  }

  private void logCurrentState()
  {
    String str = "episodeSelected?doc=" + this.mEpisode.getDocId() + "&expanded=" + isExpanded();
    FinskyApp.get().getAnalytics().logPageView(this.mReferrerUrl, this.mReferrerListCookie, str);
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = "cidi";
    arrayOfObject[1] = this.mEpisode.getDocId();
    arrayOfObject[2] = "expanded";
    arrayOfObject[3] = Boolean.valueOf(isExpanded());
    localFinskyEventLog.logTag("episodeSelected", arrayOfObject);
  }

  private void setExpandedContentVisibility(int paramInt)
  {
    boolean bool = true;
    inflateViewStubIfNecessary();
    this.mExpandedContent.setVisibility(paramInt);
    if (paramInt == 0)
    {
      String str1 = ThumbnailUtils.getScreenshotUrlFromDocument(this.mEpisode, 0, this.mMaxExpandedHeight);
      if (!TextUtils.isEmpty(str1))
        this.mExpandedEpisodeScreencap.load(this.mBitmapLoader, this.mSeasonDocument, str1, bool);
      if (!TextUtils.isEmpty(this.mEpisode.getDescription()))
      {
        String str2 = this.mEpisode.getDescription().toString();
        if (this.mEpisode.getTvEpisodeDetails() != null)
        {
          String str3 = str2 + "\n\n";
          StringBuilder localStringBuilder = new StringBuilder().append(str3);
          Resources localResources = getResources();
          Object[] arrayOfObject = new Object[bool];
          arrayOfObject[0] = this.mEpisode.getTvEpisodeDetails().getReleaseDate();
          str2 = localResources.getString(2131165461, arrayOfObject);
        }
        this.mExpandedDescription.setText(str2);
      }
      this.mExpandedContent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpisodeSnippet.this.toggleExpandedVisibility();
          EpisodeSnippet.this.logCurrentState();
        }
      });
    }
    OnCollapsedStateChanged localOnCollapsedStateChanged;
    if (this.mCollapsedStateChangedListener != null)
    {
      localOnCollapsedStateChanged = this.mCollapsedStateChangedListener;
      if (paramInt != 8)
        break label241;
    }
    while (true)
    {
      localOnCollapsedStateChanged.onCollapsedStateChanged(this, bool);
      this.mHandler.post(this.mScrollerRunnable);
      return;
      label241: bool = false;
    }
  }

  private void toggleExpandedVisibility()
  {
    if (isExpanded());
    for (int i = 8; ; i = 0)
    {
      setExpandedContentVisibility(i);
      return;
    }
  }

  public static void updateBuyButtonState(Resources paramResources, Button paramButton, TextView paramTextView, View paramView, Document paramDocument1, Document paramDocument2, int paramInt, boolean paramBoolean)
  {
    paramButton.setVisibility(0);
    Account localAccount1 = FinskyApp.get().getCurrentAccount();
    Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(paramDocument2, FinskyApp.get().getLibraries(), localAccount1);
    paramButton.setBackgroundResource(2130837587);
    paramButton.setPadding(paramButton.getPaddingLeft(), 0, paramButton.getPaddingRight(), 0);
    paramButton.setTextColor(paramResources.getColor(2131361794));
    if (localAccount2 != null)
      if (paramDocument2.getDocumentType() == 19)
      {
        paramButton.setText(2131165425);
        paramButton.setEnabled(false);
        if (paramTextView != null)
          if (!paramBoolean)
            break label292;
      }
    label292: for (int i = 0; ; i = 8)
    {
      paramView.setVisibility(i);
      paramTextView.setVisibility(i);
      return;
      paramButton.setBackgroundResource(2130837575);
      paramButton.setPadding(paramButton.getPaddingLeft(), 0, paramButton.getPaddingRight(), 0);
      paramButton.setText(2131165444);
      paramButton.setEnabled(true);
      paramButton.setTextColor(paramResources.getColorStateList(2131361868));
      break;
      if (paramDocument2.getOffer(1) != null)
      {
        if (paramInt != -1)
          paramButton.setText(paramInt);
        while (true)
        {
          paramButton.setEnabled(true);
          break;
          paramButton.setText(paramDocument2.getFormattedPrice(1));
        }
      }
      if (paramDocument2.getOffer(7) != null)
      {
        if (paramInt != -1)
          paramButton.setText(paramInt);
        while (true)
        {
          paramButton.setEnabled(true);
          break;
          paramButton.setText(paramDocument2.getFormattedPrice(7));
        }
      }
      if ((paramDocument1 != null) && ((paramDocument1.getOffer(1) != null) || (paramDocument1.getOffer(7) != null)))
      {
        paramButton.setText(2131165759);
        paramButton.setEnabled(false);
        break;
      }
      paramButton.setVisibility(4);
      break;
    }
  }

  public void collapseWithoutNotifyingListeners()
  {
    if (this.mExpandedContent == null);
    while (true)
    {
      return;
      this.mExpandedContent.setVisibility(8);
    }
  }

  public void expand()
  {
    setExpandedContentVisibility(0);
  }

  public boolean isExpanded()
  {
    if ((this.mExpandedContent != null) && (this.mExpandedContent.getVisibility() == 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    DocDetails.TvEpisodeDetails localTvEpisodeDetails = this.mEpisode.getTvEpisodeDetails();
    if (localTvEpisodeDetails == null)
      setVisibility(8);
    while (true)
    {
      return;
      this.mEpisodeNumber.setText(Integer.toString(localTvEpisodeDetails.getEpisodeIndex()));
      this.mEpisodeTitle.setText(this.mEpisode.getTitle());
      updateBuyButtonState(getResources(), this.mBuyButton, this.mAddedState, this.mAddedDrawable, this.mSeasonDocument, this.mEpisode, -1, this.mIsNewPurchase);
      this.mBuyButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpisodeSnippet.handleBuyButtonClick(EpisodeSnippet.this.mNavigationManager, EpisodeSnippet.this.mEpisode, EpisodeSnippet.this.mParentFragment);
        }
      });
      this.mEpisodeNumber.setClickable(false);
      if (((Boolean)G.prePurchaseSharingEnabled.get()).booleanValue())
        setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            Intent localIntent = IntentUtils.buildShareIntent(EpisodeSnippet.this.getContext(), EpisodeSnippet.this.mEpisode);
            Context localContext = EpisodeSnippet.this.getContext();
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = EpisodeSnippet.this.mEpisode.getTitle();
            String str = localContext.getString(2131165582, arrayOfObject1);
            EpisodeSnippet.this.getContext().startActivity(Intent.createChooser(localIntent, str));
            FinskyApp.get().getAnalytics().logPageView(null, null, "share?doc=" + EpisodeSnippet.this.mEpisode.getDocId());
            FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
            Object[] arrayOfObject2 = new Object[2];
            arrayOfObject2[0] = "cidi";
            arrayOfObject2[1] = EpisodeSnippet.this.mEpisode.getDocId();
            localFinskyEventLog.logTag("episodeShareLongPress", arrayOfObject2);
            return true;
          }
        });
      this.mCollapsedContent.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpisodeSnippet.this.toggleExpandedVisibility();
          EpisodeSnippet.this.logCurrentState();
        }
      });
      setContentDescription(this.mEpisode.getTitle());
    }
  }

  protected void onDetachedFromWindow()
  {
    this.mHandler.removeCallbacks(this.mScrollerRunnable);
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mCollapsedContent = findViewById(2131231004);
    this.mExpandedViewStub = ((ViewStub)findViewById(2131231011));
    this.mEpisodeNumber = ((TextView)findViewById(2131231006));
    this.mBuyButton = ((Button)findViewById(2131230897));
    this.mEpisodeTitle = ((TextView)findViewById(2131231010));
    this.mAddedState = ((TextView)findViewById(2131231009));
    this.mAddedDrawable = findViewById(2131230968);
    this.mMaxExpandedHeight = getResources().getDimensionPixelSize(2131427408);
  }

  public void setEpisodeDetails(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, boolean paramBoolean, PageFragment paramPageFragment, String paramString1, String paramString2, OnCollapsedStateChanged paramOnCollapsedStateChanged)
  {
    this.mSeasonDocument = paramDocument1;
    this.mEpisode = paramDocument2;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mIsNewPurchase = paramBoolean;
    this.mCollapsedStateChangedListener = paramOnCollapsedStateChanged;
    this.mParentFragment = paramPageFragment;
    this.mReferrerUrl = paramString1;
    this.mReferrerListCookie = paramString2;
  }

  public static abstract interface OnCollapsedStateChanged
  {
    public abstract void onCollapsedStateChanged(EpisodeSnippet paramEpisodeSnippet, boolean paramBoolean);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EpisodeSnippet
 * JD-Core Version:    0.6.2
 */
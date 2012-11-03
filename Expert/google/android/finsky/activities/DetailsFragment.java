package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.BadgeSection;
import com.google.android.finsky.layout.DetailsContentLayout;
import com.google.android.finsky.layout.DetailsLeftColumnContainer;
import com.google.android.finsky.layout.DetailsPlusOne;
import com.google.android.finsky.layout.DetailsSummaryByline;
import com.google.android.finsky.layout.ExplorePanel;
import com.google.android.finsky.layout.GooglePlusShareSection;
import com.google.android.finsky.layout.ListingView;
import com.google.android.finsky.layout.MovieTrailerView;
import com.google.android.finsky.layout.ObservableScrollView;
import com.google.android.finsky.layout.ObservableScrollView.ScrollListener;
import com.google.android.finsky.layout.OwnedActions;
import com.google.android.finsky.layout.ScreenshotGallery;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.layout.SubscriptionsSection;
import com.google.android.finsky.layout.WarningMessageSection;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.BookInfo.BookDetails;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.remoting.protos.DocDetails.Trailer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FastHtmlParser;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import java.util.List;

public class DetailsFragment extends DetailsDataBasedFragment
  implements OfferResolutionDialog.OfferResolutionListener, RateReviewDialog.Listener, ReviewDialog.Listener, SimpleAlertDialog.Listener, Libraries.Listener
{
  private final DetailsTextViewBinder mAboutAuthorBinder = new DetailsTextViewBinder();
  private final int mAlbumDetailsBackgroundColor;
  private final DetailsCastCreditsViewBinder mCastCreditsViewBinder = new DetailsCastCreditsViewBinder();
  private String mContinueUrl;
  private final DetailsPackViewBinder mCreatorRelatedViewBinder = new DetailsPackViewBinder();
  private final DetailsPackViewBinder mCrossSellViewBinder = new DetailsPackViewBinder();
  private final int mDefaultAlbumDescriptionLines;
  private final int mDefaultDescriptionLines;
  private final DetailsDescriptionViewBinder mDescriptionViewBinder = new DetailsDescriptionViewBinder();
  private ViewGroup mDetailsPanel;
  private String mExternalReferrer;
  private int mLastUsedSectionOrderId = -1;
  private final int mMaxCreatorMoreByItems;
  private final int mMaxCreatorMoreByItemsPerRow;
  private final int mMaxRelatedItems;
  private final int mMaxRelatedItemsPerRow;
  private final int mMaxRelatedMagazinesPerRow;
  private final int mMaxRelatedMusicItemsPerRow;
  private final DetailsPackViewBinder mMoreByViewBinder = new DetailsPackViewBinder();
  private OwnedActions mOwnedActions;
  private final DetailsPackViewBinder mRelatedViewBinder = new DetailsPackViewBinder();
  private boolean mReturnAfterPurchase;
  private ReviewDialogListener mReviewDialogListener;
  private final ReviewSamplesViewBinder mReviewSamplesViewBinder = new ReviewSamplesViewBinder();
  private final SongListViewBinder mSongListViewBinder = new SongListViewBinder();
  private final SubscriptionsViewBinder mSubscriptionsViewBinder = new SubscriptionsViewBinder();
  private DetailsSummaryViewBinder mSummaryViewBinder;
  private boolean mUseDynamicButtonsContainer;
  private final DetailsTextViewBinder mWhatsNewViewBinder = new DetailsTextViewBinder();

  public DetailsFragment()
  {
    Resources localResources = FinskyApp.get().getResources();
    this.mMaxCreatorMoreByItemsPerRow = localResources.getInteger(2131492865);
    this.mMaxRelatedItemsPerRow = localResources.getInteger(2131492864);
    this.mMaxRelatedMusicItemsPerRow = localResources.getInteger(2131492867);
    this.mMaxRelatedMagazinesPerRow = localResources.getInteger(2131492868);
    this.mMaxCreatorMoreByItems = localResources.getInteger(2131492873);
    this.mMaxRelatedItems = localResources.getInteger(2131492875);
    this.mUseDynamicButtonsContainer = localResources.getBoolean(2131296257);
    this.mDefaultDescriptionLines = localResources.getInteger(2131492881);
    this.mDefaultAlbumDescriptionLines = localResources.getInteger(2131492884);
    this.mAlbumDetailsBackgroundColor = localResources.getColor(2131361792);
  }

  private int getRepresentativeBackendId()
  {
    return getDocument().getBackend();
  }

  public static DetailsFragment newInstance(Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    DetailsFragment localDetailsFragment = new DetailsFragment();
    localDetailsFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localDetailsFragment.setInitialDocument(paramDocument);
    localDetailsFragment.setArgument("finsky.DetailsDataBasedFragment.cookie", paramString2);
    localDetailsFragment.setArgument("finsk.DetailsDatabasedFragment.referrer", paramString3);
    localDetailsFragment.setArgument("finsky.DetailsFragment.externalReferrerUrl", paramString4);
    localDetailsFragment.setArgument("finsky.DetailsFragment.continueUrl", paramString5);
    localDetailsFragment.setArgument("finsky.DetailsFragment.returnAfterPurchase", paramBoolean);
    return localDetailsFragment;
  }

  private void trackLeftColumnScrollingIfNecessary(View paramView)
  {
    ObservableScrollView localObservableScrollView = (ObservableScrollView)paramView.findViewById(2131230907);
    if (localObservableScrollView == null);
    while (true)
    {
      return;
      final DetailsLeftColumnContainer localDetailsLeftColumnContainer = (DetailsLeftColumnContainer)localObservableScrollView.findViewById(2131230908);
      localObservableScrollView.setOnScrollListener(new ObservableScrollView.ScrollListener()
      {
        public void onScroll(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          int i = this.val$leftColumnSummary.getBottom();
          int j = localDetailsLeftColumnContainer.getSummaryBottom();
          if (i != j)
            this.val$leftColumnSummary.offsetTopAndBottom(j - i);
          int k = this.val$leftColumnSummaryStrip.getTop();
          int m = localDetailsLeftColumnContainer.getSummaryStripTop();
          if (k != m)
            this.val$leftColumnSummaryStrip.offsetTopAndBottom(m - k);
        }
      });
    }
  }

  private void trackSingleColumnScrollingIfNecessary(View paramView)
  {
    ObservableScrollView localObservableScrollView = (ObservableScrollView)paramView.findViewById(2131230925);
    if (localObservableScrollView == null);
    while (true)
    {
      return;
      View localView = localObservableScrollView.findViewById(2131230926);
      if ((localView instanceof DetailsContentLayout))
        localObservableScrollView.setOnScrollListener(new ObservableScrollView.ScrollListener()
        {
          View summaryStrip;

          void findSummaryStrup()
          {
            if (this.summaryStrip == null)
              this.summaryStrip = this.val$detailsContent.findViewById(2131230879);
          }

          public void onScroll(int paramAnonymousInt1, int paramAnonymousInt2)
          {
            findSummaryStrup();
            int i = this.summaryStrip.getTop();
            int j = this.val$detailsContent.getSummaryStripTop();
            if (i != j)
              this.summaryStrip.offsetTopAndBottom(j - i);
          }
        });
    }
  }

  private void updateDetailsSections(Bundle paramBundle)
  {
    Document localDocument = getDocument();
    if (localDocument == null);
    label292: label452: label593: label725: label1623: label1633: label1763: label1767: 
    while (true)
    {
      return;
      ViewGroup localViewGroup = (ViewGroup)getView().findViewById(2131230926);
      LayoutInflater localLayoutInflater = LayoutInflater.from(localViewGroup.getContext());
      int m;
      View localView2;
      label364: View localView3;
      int i6;
      View localView7;
      int i1;
      int i2;
      int i5;
      View localView8;
      View localView9;
      MovieTrailerView localMovieTrailerView;
      SongList localSongList;
      GooglePlusShareSection localGooglePlusShareSection;
      View localView10;
      View localView11;
      TextView localTextView;
      View localView12;
      if (localDocument.getBackend() == 2)
      {
        localViewGroup.removeAllViews();
        localLayoutInflater.inflate(2130968654, localViewGroup, true);
        View localView1 = getView();
        SubscriptionsSection localSubscriptionsSection = (SubscriptionsSection)localView1.findViewById(2131230911);
        if (localSubscriptionsSection != null)
          this.mSubscriptionsViewBinder.bind(this, localSubscriptionsSection, localDocument, 2130968838, paramBundle);
        DetailsSummaryByline localDetailsSummaryByline = (DetailsSummaryByline)localView1.findViewById(2131230912);
        if (localDetailsSummaryByline != null)
        {
          localDetailsSummaryByline.setDocument(localDocument);
          if ((localDocument.getBackend() == 2) && (localDetailsSummaryByline != null))
            localDetailsSummaryByline.setBackgroundResource(2130837591);
        }
        BadgeSection localBadgeSection = (BadgeSection)localView1.findViewById(2131230905);
        if (localBadgeSection != null)
          localBadgeSection.configure(this.mNavigationManager, getToc(), getReferrer(), localDocument, this.mBitmapLoader, paramBundle);
        DetailsPlusOne localDetailsPlusOne = (DetailsPlusOne)localView1.findViewById(2131230941);
        if (localDetailsPlusOne != null)
          localDetailsPlusOne.bind(this.mDfeApi, this.mUrl, getArguments().getString("finsky.DetailsDataBasedFragment.cookie"), localDocument, hasDetailsDataLoaded(), paramBundle);
        this.mOwnedActions = ((OwnedActions)localView1.findViewById(2131230868));
        if (this.mOwnedActions != null)
          this.mOwnedActions.setDocument(localDocument, getDetailsData(), this.mNavigationManager, this, hasDetailsDataLoaded());
        int k = localDocument.getDocumentType();
        if ((k != 16) && (k != 17))
          break label1617;
        m = 1;
        localView2 = localView1.findViewById(2131230913);
        if (localView2 != null)
        {
          if ((!hasDetailsDataLoaded()) || (m != 0))
            break label1623;
          localView2.setVisibility(0);
          this.mCreatorRelatedViewBinder.bind(localView2, localDocument, localDocument.getMoreByHeader(), null, localDocument.getMoreByListUrl(), localDocument.getMoreByBrowseUrl(), this.mMaxCreatorMoreByItemsPerRow, this.mMaxCreatorMoreByItems, this.mUrl, getReferrer());
        }
        localView3 = localView1.findViewById(2131230872);
        if (localView3 != null)
        {
          if (!hasDetailsDataLoaded())
            break label1633;
          localView3.setVisibility(0);
          this.mDescriptionViewBinder.bind(localView3, localDocument, paramBundle);
          if (localDocument.getBackend() == 2)
          {
            this.mDescriptionViewBinder.setDefaultMaxLines(this.mDefaultAlbumDescriptionLines);
            if (localView3.getVisibility() == 0)
            {
              localView3.setBackgroundResource(2130837591);
              if (localDetailsSummaryByline != null)
                localDetailsSummaryByline.setBackgroundColor(this.mAlbumDetailsBackgroundColor);
            }
          }
        }
        View localView4 = localView1.findViewById(2131230914);
        if (localView4 != null)
          this.mCastCreditsViewBinder.bind(localView4, localDocument, hasDetailsDataLoaded());
        View localView5 = localView1.findViewById(2131230952);
        if (localView5 != null)
        {
          CharSequence localCharSequence2 = localDocument.getWhatsNew();
          this.mWhatsNewViewBinder.bind(localView5, localDocument, 2131165495, localCharSequence2, paramBundle);
        }
        View localView6 = localView1.findViewById(2131230877);
        if (localView6 != null)
        {
          this.mReviewDialogListener = new ReviewDialogListener(this.mContext, this.mNavigationManager, this, getDocument(), getDetailsData(), this.mReviewSamplesViewBinder, this.mOwnedActions);
          this.mReviewSamplesViewBinder.bind(localView6, localDocument, hasDetailsDataLoaded());
          if (!hasDetailsDataLoaded())
            break label1643;
          i6 = 0;
          localView6.setVisibility(i6);
        }
        ListingView localListingView1 = (ListingView)localView1.findViewById(2131230869);
        if (localListingView1 != null)
          localListingView1.bindLinks(this.mUrl, localDocument);
        localView7 = localView1.findViewById(2131230870);
        if (localView7 != null)
        {
          if (localDocument.getBackend() != 3)
            break label1650;
          i1 = 1;
          if (localDocument.getBackend() != 2)
            break label1656;
          i2 = 1;
          int i3 = this.mMaxRelatedItemsPerRow;
          int i4 = this.mMaxRelatedItems;
          if (i2 != 0)
            i3 = this.mMaxRelatedMusicItemsPerRow;
          if (m != 0)
          {
            i3 = this.mMaxRelatedMagazinesPerRow;
            i4 = this.mMaxRelatedMagazinesPerRow;
          }
          if ((i1 != 0) && (!localDocument.hasCreatorRelatedContent()) && (localView2 != null))
            break label1662;
          i5 = 1;
          if ((!hasDetailsDataLoaded()) || (i5 == 0))
            break label1668;
          this.mRelatedViewBinder.bind(localView7, localDocument, localDocument.getRelatedHeader(), null, localDocument.getRelatedListUrl(), localDocument.getRelatedBrowseUrl(), i3, i4, this.mUrl, getReferrer());
        }
        localView8 = localView1.findViewById(2131230953);
        if (localView8 != null)
        {
          if (!localDocument.hasCrossSellContent())
            break label1678;
          this.mCrossSellViewBinder.bind(localView8, localDocument, localDocument.getCrossSellHeader(), null, localDocument.getCrossSellListUrl(), localDocument.getCrossSellBrowseUrl(), this.mMaxRelatedItemsPerRow, this.mMaxRelatedItems, this.mUrl, getReferrer());
        }
        localView9 = localView1.findViewById(2131230954);
        if (localView9 != null)
        {
          if ((!localDocument.hasMoreBy()) || (m == 0))
            break label1688;
          this.mMoreByViewBinder.bind(localView9, localDocument, localDocument.getMoreByHeader(), null, localDocument.getMoreByListUrl(), localDocument.getMoreByBrowseUrl(), this.mMaxRelatedMagazinesPerRow, this.mMaxRelatedMagazinesPerRow, this.mUrl, getReferrer());
        }
        ScreenshotGallery localScreenshotGallery = (ScreenshotGallery)localView1.findViewById(2131230949);
        if (localScreenshotGallery != null)
          localScreenshotGallery.bind(localDocument, this.mBitmapLoader, this.mNavigationManager, hasDetailsDataLoaded());
        localMovieTrailerView = (MovieTrailerView)localView1.findViewById(2131230950);
        if (localMovieTrailerView != null)
        {
          List localList = localDocument.getMovieTrailers();
          if ((localList == null) || (localList.size() <= 0) || (TextUtils.isEmpty(((DocDetails.Trailer)localList.get(0)).getThumbnailUrl())))
            break label1698;
          DocDetails.Trailer localTrailer = (DocDetails.Trailer)localList.get(0);
          localMovieTrailerView.load(this.mBitmapLoader, localTrailer.getThumbnailUrl());
          localMovieTrailerView.showPlayIcon(localTrailer.getWatchUrl());
          localMovieTrailerView.setCurtainCaption(localTrailer.getDuration());
          localMovieTrailerView.setVisibility(0);
        }
        ListingView localListingView2 = (ListingView)localView1.findViewById(2131230878);
        if (localListingView2 != null)
          localListingView2.bindFlagContent(localDocument, this.mNavigationManager, hasDetailsDataLoaded());
        localSongList = (SongList)localView1.findViewById(2131230874);
        if (localSongList != null)
        {
          if (TextUtils.isEmpty(localDocument.getCoreContentListUrl()))
            break label1708;
          localSongList.setVisibility(0);
          this.mSongListViewBinder.restoreInstanceState(paramBundle);
          this.mSongListViewBinder.bind(localSongList, getDocument(), localDocument.getCoreContentHeader(), null, localDocument.getCoreContentListUrl(), true, 2147483647, hasDetailsDataLoaded(), getLibraries(), this.mBitmapLoader);
          String str2 = Uri.parse(this.mUrl).getQueryParameter("tid");
          if (str2 != null)
            localSongList.setHighlightedSong(str2, (ScrollView)getActivity().findViewById(2131230925));
        }
        localGooglePlusShareSection = (GooglePlusShareSection)localView1.findViewById(2131230873);
        if (localGooglePlusShareSection != null)
        {
          if (!hasDetailsDataLoaded())
            break label1718;
          localGooglePlusShareSection.bind(localDocument, this, this.mUrl);
        }
        localView10 = localView1.findViewById(2131230955);
        if (localView10 != null)
        {
          if ((!hasDetailsDataLoaded()) || (localDocument.getDocumentType() != 5) || (localDocument.getBookDetails() == null) || (!localDocument.getBookDetails().hasAboutTheAuthor()))
            break label1728;
          CharSequence localCharSequence1 = FastHtmlParser.fromHtml(localDocument.getBookDetails().getAboutTheAuthor());
          this.mAboutAuthorBinder.bind(localView10, localDocument, 2131165808, localCharSequence1, paramBundle);
        }
        WarningMessageSection localWarningMessageSection = (WarningMessageSection)getView().findViewById(2131230910);
        if (!this.mUseDynamicButtonsContainer)
          break label1738;
        localView11 = null;
        localWarningMessageSection.bind(localDocument, (ImageView)localView11, getToc(), getLibraries(), this.mDfeApi.getAccount());
        localTextView = (TextView)getView().findViewById(2131230931);
        String str1 = getDetailsData().getFooterHtml();
        if (TextUtils.isEmpty(str1))
          break label1753;
        localTextView.setVisibility(0);
        localTextView.setText(Html.fromHtml(str1));
        ExplorePanel localExplorePanel = (ExplorePanel)localView1.findViewById(2131230866);
        if (localExplorePanel != null)
          localExplorePanel.configure(localDocument, this);
        localView12 = localView1.findViewById(2131230935);
        if ((localScreenshotGallery == null) || (localScreenshotGallery.getVisibility() != 0))
          break label1763;
      }
      for (int n = 1; ; n = 0)
      {
        if (((!hasDetailsDataLoaded()) && (n == 0)) || (localView12 == null))
          break label1767;
        localView12.setVisibility(8);
        break;
        int i;
        if (LibraryUtils.getOwnerWithCurrentAccount(localDocument, FinskyApp.get().getLibraries(), this.mDfeApi.getAccount()) != null)
        {
          i = 2130968657;
          label1515: if (localViewGroup.getChildCount() != 0)
            break label1560;
          localLayoutInflater.inflate(2130968655, localViewGroup, true);
          localLayoutInflater.inflate(i, localViewGroup, true);
        }
        while (true)
        {
          this.mLastUsedSectionOrderId = i;
          break;
          i = 2130968656;
          break label1515;
          if (i != this.mLastUsedSectionOrderId)
          {
            int j = localViewGroup.indexOfChild(localViewGroup.findViewById(2131230951));
            while (localViewGroup.getChildCount() > j + 1)
              localViewGroup.removeViewAt(j + 1);
            localLayoutInflater.inflate(i, localViewGroup, true);
          }
        }
        m = 0;
        break label292;
        localView2.setVisibility(8);
        break label364;
        localView3.setVisibility(8);
        break label452;
        label1643: i6 = 8;
        break label593;
        label1650: i1 = 0;
        break label654;
        label1656: i2 = 0;
        break label665;
        label1662: i5 = 0;
        break label725;
        localView7.setVisibility(8);
        break label772;
        localView8.setVisibility(8);
        break label833;
        localView9.setVisibility(8);
        break label899;
        localMovieTrailerView.setVisibility(8);
        break label1047;
        localSongList.setVisibility(8);
        break label1198;
        localGooglePlusShareSection.setVisibility(8);
        break label1234;
        localView10.setVisibility(8);
        break label1309;
        localView11 = this.mDetailsPanel.findViewById(2131230909);
        break label1334;
        localTextView.setVisibility(8);
        break label1410;
      }
    }
  }

  protected int getLayoutRes()
  {
    return 2130968642;
  }

  public void onAllLibrariesLoaded()
  {
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    getLibraries().addListener(this);
    return localView;
  }

  public void onDataChanged()
  {
    if ((isAdded()) && (isDataReady()) && (this.mSummaryViewBinder == null))
    {
      this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(getToc(), getRepresentativeBackendId(), this.mDfeApi.getAccount());
      this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this, true, getReferrer(), this.mExternalReferrer, this.mContinueUrl, this.mReturnAfterPurchase);
    }
    super.onDataChanged();
  }

  public void onDeleteReview(String paramString1, String paramString2)
  {
    this.mReviewDialogListener.onDeleteReview(paramString1, paramString2);
  }

  public void onDestroyView()
  {
    getLibraries().removeListener(this);
    recordState();
    if (this.mSummaryViewBinder != null)
      this.mSummaryViewBinder.onDestroyView();
    this.mCreatorRelatedViewBinder.onDestroyView();
    this.mRelatedViewBinder.onDestroyView();
    this.mCrossSellViewBinder.onDestroyView();
    this.mMoreByViewBinder.onDestroyView();
    this.mCastCreditsViewBinder.onDestroyView();
    this.mDescriptionViewBinder.onDestroyView();
    this.mWhatsNewViewBinder.onDestroyView();
    this.mReviewSamplesViewBinder.onDestroyView();
    this.mSubscriptionsViewBinder.onDestroyView();
    this.mAboutAuthorBinder.onDestroyView();
    super.onDestroyView();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (canChangeFragmentManagerState())
      ErrorDialog.show(getFragmentManager(), null, ErrorStrings.get(getActivity(), paramVolleyError), true);
    while (true)
    {
      return;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramVolleyError.getMessage();
      FinskyLog.e("Volley error: %s", arrayOfObject);
    }
  }

  protected void onInitViewBinders()
  {
    this.mCreatorRelatedViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mRelatedViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mCrossSellViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mMoreByViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mCastCreditsViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
    this.mDescriptionViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mDefaultDescriptionLines);
    this.mWhatsNewViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mDefaultDescriptionLines);
    this.mReviewSamplesViewBinder.init(this.mContext, this, this.mDfeApi, this.mNavigationManager);
    this.mSongListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
    this.mSubscriptionsViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, FinskyApp.get().getLibraries());
    this.mAboutAuthorBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mDefaultDescriptionLines);
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    if (this.mDfeApi != null)
      refresh();
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.w("Unknown request code %d", arrayOfObject);
    case 3:
    case 1:
    case 2:
    case 4:
    }
    while (true)
    {
      return;
      if (this.mSubscriptionsViewBinder != null)
      {
        this.mSubscriptionsViewBinder.onNegativeClick(paramInt, paramBundle);
        continue;
        if (this.mSummaryViewBinder != null)
          this.mSummaryViewBinder.onNegativeClick(paramInt, paramBundle);
      }
    }
  }

  public void onOfferSelected(Document paramDocument, int paramInt)
  {
    this.mNavigationManager.buy(this.mDfeApi.getAccount(), paramDocument, paramInt, getReferrer(), this.mExternalReferrer, this.mContinueUrl, false);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.w("Unknown request code %d", arrayOfObject);
    case 3:
    case 1:
    case 2:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      if (this.mSubscriptionsViewBinder != null)
      {
        this.mSubscriptionsViewBinder.onPositiveClick(paramInt, paramBundle);
        continue;
        if (this.mSummaryViewBinder != null)
        {
          this.mSummaryViewBinder.onPositiveClick(paramInt, paramBundle);
          continue;
          Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
          localIntent.setFlags(537526272);
          getActivity().startActivity(localIntent);
        }
      }
    }
  }

  public void onRateReview(String paramString1, String paramString2, RateReviewDialog.CommentRating paramCommentRating)
  {
    this.mReviewDialogListener.onRateReview(paramString1, paramString2, paramCommentRating);
  }

  public void onSaveReview(String paramString1, int paramInt, String paramString2, String paramString3, DocAnnotations.PlusProfile paramPlusProfile, boolean paramBoolean)
  {
    this.mReviewDialogListener.onSaveReview(paramString1, paramInt, paramString2, paramString3, paramPlusProfile, paramBoolean);
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(null);
    if (isDataReady())
      this.mPageFragmentHost.updateCurrentBackendId(getDocument().getBackend());
  }

  protected void rebindViews(Bundle paramBundle)
  {
    this.mDetailsPanel = ((ViewGroup)getView().findViewById(2131230881));
    ViewGroup localViewGroup1 = (ViewGroup)getView().findViewById(2131230922);
    ViewGroup localViewGroup2 = (ViewGroup)getView().findViewById(2131230924);
    ViewGroup localViewGroup3 = (ViewGroup)getView().findViewById(2131230915);
    if (!this.mUseDynamicButtonsContainer)
      this.mDetailsPanel.removeAllViews();
    rebindActionBar();
    Document localDocument = getDocument();
    int i = localDocument.getBackend();
    int j = CorpusResourceUtils.getBackendHintColor(this.mContext, i);
    int k = CorpusResourceUtils.getBackendDarkColor(this.mContext, i);
    View localView1 = getView();
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContext);
    if (!this.mUseDynamicButtonsContainer)
      localLayoutInflater.inflate(CorpusResourceUtils.getCorpusDetailsLayoutResource(i), this.mDetailsPanel, true);
    View localView2 = localView1.findViewById(2131230884);
    if (localView2 != null)
    {
      localView2.setBackgroundColor(j);
      View localView4 = localView2.findViewById(2131230885);
      BitmapDrawable localBitmapDrawable2 = (BitmapDrawable)getResources().getDrawable(2130837714);
      localBitmapDrawable2.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      localView4.setBackgroundDrawable(localBitmapDrawable2);
      localView1.findViewById(2131230883).setBackgroundColor(k);
    }
    if (localViewGroup1 != null)
    {
      localViewGroup1.setBackgroundColor(j);
      BitmapDrawable localBitmapDrawable1 = (BitmapDrawable)getResources().getDrawable(2130837714);
      localBitmapDrawable1.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      localViewGroup2.setBackgroundDrawable(localBitmapDrawable1);
      localViewGroup1.findViewById(2131230923).setBackgroundColor(k);
    }
    DetailsSummaryViewBinder localDetailsSummaryViewBinder = this.mSummaryViewBinder;
    View[] arrayOfView = new View[3];
    arrayOfView[0] = this.mDetailsPanel;
    arrayOfView[1] = localViewGroup2;
    arrayOfView[2] = localViewGroup3;
    localDetailsSummaryViewBinder.bind(localDocument, true, arrayOfView);
    trackSingleColumnScrollingIfNecessary(localView1);
    trackLeftColumnScrollingIfNecessary(localView1);
    updateDetailsSections(paramBundle);
    View localView3 = localView1.findViewById(2131230879);
    if (localView3 != null)
      localView3.setBackgroundColor(k);
  }

  protected void recordState(Bundle paramBundle)
  {
    View localView = getView();
    if (localView == null);
    while (true)
    {
      return;
      ViewGroup localViewGroup = (ViewGroup)localView.findViewById(2131230926);
      if (localViewGroup.findViewById(2131230872) != null)
        this.mDescriptionViewBinder.saveInstanceState(paramBundle);
      if (localViewGroup.findViewById(2131230952) != null)
        this.mWhatsNewViewBinder.saveInstanceState(paramBundle);
      BadgeSection localBadgeSection = (BadgeSection)localViewGroup.findViewById(2131230905);
      if (localBadgeSection != null)
        localBadgeSection.saveInstanceState(paramBundle);
      DetailsPlusOne localDetailsPlusOne = (DetailsPlusOne)localView.findViewById(2131230941);
      if (localDetailsPlusOne != null)
        localDetailsPlusOne.saveInstanceState(paramBundle);
      if ((SubscriptionsSection)localViewGroup.findViewById(2131230911) != null)
        this.mSubscriptionsViewBinder.saveInstanceState(paramBundle);
      if ((SongList)localViewGroup.findViewById(2131230874) != null)
        this.mSongListViewBinder.saveInstanceState(paramBundle);
      if (localViewGroup.findViewById(2131230955) != null)
        this.mAboutAuthorBinder.saveInstanceState(paramBundle);
    }
  }

  protected void requestData()
  {
    super.requestData();
    this.mExternalReferrer = getArguments().getString("finsky.DetailsFragment.externalReferrerUrl");
    this.mContinueUrl = getArguments().getString("finsky.DetailsFragment.continueUrl");
    this.mReturnAfterPurchase = getArguments().getBoolean("finsky.DetailsFragment.returnAfterPurchase");
  }

  public void updateDetailsSections()
  {
    if (getView() != null)
    {
      updateDetailsSections(Bundle.EMPTY);
      trackSingleColumnScrollingIfNecessary(getView());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsFragment
 * JD-Core Version:    0.6.2
 */
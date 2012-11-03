package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.BadgeSection;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DetailsPlusOne;
import com.google.android.finsky.layout.EpisodeList;
import com.google.android.finsky.layout.ExplorePanel;
import com.google.android.finsky.layout.GooglePlusShareSection;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.ListingView;
import com.google.android.finsky.layout.OwnedActions;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ThumbnailUtils;

public class CreatorDetailsFragment extends DetailsDataBasedFragment
  implements RateReviewDialog.Listener, ReviewDialog.Listener, SimpleAlertDialog.Listener
{
  private final AlbumPackViewBinder mAlbumsViewBinder = new AlbumPackViewBinder();
  private final int mDefaultDescriptionLines;
  private final DetailsTextViewBinder mDescriptionViewBinder = new DetailsTextViewBinder();
  private final int mHeroImageHeight;
  private final int mMaxCreatorRelatedItems;
  private final int mMaxCreatorRelatedItemsPerRow;
  private final int mMaxRelatedItems;
  private final int mMaxRelatedItemsPerRow;
  private OwnedActions mOwnedActions;
  private final DetailsPackViewBinder mRelatedViewBinder = new DetailsPackViewBinder();
  private ReviewDialogListener mReviewDialogListener;
  private final ReviewSamplesViewBinder mReviewSamplesViewBinder = new ReviewSamplesViewBinder();
  private final SeasonListViewBinder mSeasonListViewBinder = new SeasonListViewBinder();
  private final SongListViewBinder mSongListViewBinder = new SongListViewBinder();

  public CreatorDetailsFragment()
  {
    Resources localResources = FinskyApp.get().getResources();
    this.mMaxCreatorRelatedItemsPerRow = localResources.getInteger(2131492866);
    this.mMaxRelatedItemsPerRow = localResources.getInteger(2131492864);
    this.mMaxCreatorRelatedItems = localResources.getInteger(2131492874);
    this.mMaxRelatedItems = localResources.getInteger(2131492875);
    this.mDefaultDescriptionLines = localResources.getInteger(2131492882);
    this.mHeroImageHeight = localResources.getInteger(2131492885);
  }

  private void moveViewOneUp(View paramView)
  {
    ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
    for (int i = 0; ; i++)
      if (i < localViewGroup.getChildCount())
      {
        if (localViewGroup.getChildAt(i) == paramView)
        {
          localViewGroup.removeViewAt(i);
          localViewGroup.addView(paramView, i - 1);
        }
      }
      else
        return;
  }

  public static CreatorDetailsFragment newInstance(Document paramDocument, String paramString1, String paramString2, String paramString3)
  {
    CreatorDetailsFragment localCreatorDetailsFragment = new CreatorDetailsFragment();
    localCreatorDetailsFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localCreatorDetailsFragment.setInitialDocument(paramDocument);
    localCreatorDetailsFragment.setArgument("finsky.DetailsDataBasedFragment.cookie", paramString2);
    localCreatorDetailsFragment.setArgument("finsky.CreatorDetailsFragment.referrerUrl", paramString3);
    return localCreatorDetailsFragment;
  }

  private void rebindMusicSections(View paramView, Document paramDocument, Bundle paramBundle)
  {
    int i = 0;
    SongList localSongList = (SongList)paramView.findViewById(2131230874);
    GooglePlusShareSection localGooglePlusShareSection;
    label139: ListingView localListingView;
    label177: View localView;
    if (localSongList != null)
    {
      if ((paramDocument.getBackend() != 2) || (!hasDetailsDataLoaded()))
        break label308;
      localSongList.setVisibility(0);
      this.mSongListViewBinder.restoreInstanceState(paramBundle);
      if (!TextUtils.isEmpty(paramDocument.getCoreContentListUrl()))
        this.mSongListViewBinder.bind(localSongList, null, paramDocument.getCoreContentHeader(), null, paramDocument.getCoreContentListUrl(), false, 5, hasDetailsDataLoaded(), getLibraries(), this.mBitmapLoader);
    }
    else
    {
      localGooglePlusShareSection = (GooglePlusShareSection)paramView.findViewById(2131230873);
      if (localGooglePlusShareSection != null)
      {
        if ((!hasDetailsDataLoaded()) || (paramDocument.getBackend() != 2))
          break label318;
        localGooglePlusShareSection.setVisibility(0);
        localGooglePlusShareSection.bind(paramDocument, this, this.mUrl);
      }
      localListingView = (ListingView)paramView.findViewById(2131230878);
      if (localListingView != null)
      {
        if (paramDocument.getBackend() != 2)
          break label328;
        localListingView.bindFlagContent(paramDocument, this.mNavigationManager, hasDetailsDataLoaded());
      }
      localView = paramView.findViewById(2131230876);
      if (localView != null)
      {
        if ((paramDocument.getBackend() != 2) || (!hasDetailsDataLoaded()) || (TextUtils.isEmpty(paramDocument.getBodyOfWorkListUrl())))
          break label338;
        localView.setVisibility(0);
        this.mAlbumsViewBinder.bind(localView, paramDocument, paramDocument.getBodyOfWorkHeader(), null, paramDocument.getBodyOfWorkListUrl(), paramDocument.getBodyOfWorkBrowseUrl(), this.mMaxRelatedItemsPerRow, this.mMaxRelatedItems, this.mUrl, null);
        if (i != 0)
          moveViewOneUp(localView);
      }
    }
    while (true)
    {
      return;
      this.mSongListViewBinder.bind(localSongList, null, paramDocument.getRelatedDocTypeHeader(), null, paramDocument.getRelatedDocTypeListUrl(), false, 5, hasDetailsDataLoaded(), getLibraries(), this.mBitmapLoader);
      i = 1;
      break;
      label308: localSongList.setVisibility(8);
      break;
      label318: localGooglePlusShareSection.setVisibility(8);
      break label139;
      label328: localListingView.setVisibility(8);
      break label177;
      label338: localView.setVisibility(8);
    }
  }

  private void rebindTvSections(View paramView, Document paramDocument, Bundle paramBundle)
  {
    EpisodeList localEpisodeList = (EpisodeList)paramView.findViewById(2131230875);
    View localView;
    int i;
    if (localEpisodeList != null)
    {
      if ((paramDocument.getDocumentType() == 18) && (hasDetailsDataLoaded()) && (!TextUtils.isEmpty(paramDocument.getCoreContentListUrl())))
      {
        localEpisodeList.setVisibility(0);
        Uri localUri = Uri.parse(this.mUrl);
        String str1 = localUri.getQueryParameter("cdid");
        String str2 = localUri.getQueryParameter("gdid");
        this.mSeasonListViewBinder.bind(getLibraries(), localEpisodeList, str1, str2, paramDocument.getCoreContentHeader(), null, paramDocument.getCoreContentListUrl(), hasDetailsDataLoaded(), getReferrer(), paramDocument.getCookie(), this.mUrl);
        this.mSeasonListViewBinder.restoreInstanceState(paramBundle);
      }
    }
    else
    {
      localView = paramView.findViewById(2131230877);
      if (localView != null)
      {
        this.mOwnedActions = ((OwnedActions)paramView.findViewById(2131230868));
        if ((paramDocument.getDocumentType() != 18) || (!hasDetailsDataLoaded()))
          break label277;
        this.mOwnedActions.setDocument(paramDocument, getDetailsData(), this.mNavigationManager, this, hasDetailsDataLoaded());
        this.mReviewDialogListener = new ReviewDialogListener(this.mContext, this.mNavigationManager, this, getDocument(), getDetailsData(), this.mReviewSamplesViewBinder, this.mOwnedActions);
        this.mReviewSamplesViewBinder.bind(localView, paramDocument, hasDetailsDataLoaded());
        if (!hasDetailsDataLoaded())
          break label270;
        i = 0;
        label252: localView.setVisibility(i);
      }
    }
    while (true)
    {
      return;
      localEpisodeList.setVisibility(8);
      break;
      label270: i = 8;
      break label252;
      label277: this.mOwnedActions.setVisibility(8);
      localView.setVisibility(8);
    }
  }

  protected int getLayoutRes()
  {
    return 2130968625;
  }

  public void onDeleteReview(String paramString1, String paramString2)
  {
    this.mReviewDialogListener.onDeleteReview(paramString1, paramString2);
  }

  public void onDestroyView()
  {
    recordState();
    this.mDescriptionViewBinder.onDestroyView();
    this.mSongListViewBinder.onDestroyView();
    this.mSeasonListViewBinder.onDestroyView();
    this.mAlbumsViewBinder.onDestroyView();
    this.mRelatedViewBinder.onDestroyView();
    this.mReviewSamplesViewBinder.onDestroyView();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
    this.mDescriptionViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mDefaultDescriptionLines);
    this.mSongListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
    this.mSeasonListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this, this.mBitmapLoader, FinskyApp.get().getLibraries());
    this.mAlbumsViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mRelatedViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
    this.mReviewSamplesViewBinder.init(this.mContext, this, this.mDfeApi, this.mNavigationManager);
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 5)
    {
      Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
      localIntent.setFlags(537526272);
      getActivity().startActivity(localIntent);
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
    if (getDocument() != null)
      this.mPageFragmentHost.updateCurrentBackendId(getDocument().getBackend());
  }

  protected void rebindViews(Bundle paramBundle)
  {
    rebindActionBar();
    Document localDocument = getDocument();
    int i = localDocument.getBackend();
    int j = CorpusResourceUtils.getBackendHintColor(this.mContext, i);
    int k = CorpusResourceUtils.getBackendDarkColor(this.mContext, i);
    View localView1 = getView();
    View localView2 = localView1.findViewById(2131230884);
    if (localView2 != null)
    {
      localView2.setBackgroundColor(j);
      View localView8 = localView2.findViewById(2131230885);
      BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837714);
      localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      localView8.setBackgroundDrawable(localBitmapDrawable);
      localView1.findViewById(2131230883).setBackgroundColor(k);
    }
    View localView3 = localView1.findViewById(2131230867);
    if (localView3 != null)
      localView3.setBackgroundColor(j);
    DecoratedTextView localDecoratedTextView = (DecoratedTextView)localView1.findViewById(2131230871);
    if (localDecoratedTextView != null)
    {
      localDecoratedTextView.setText(localDocument.getTitle());
      BadgeUtils.configureItemBadge(localDocument, this.mBitmapLoader, localDecoratedTextView, -1);
    }
    HeroGraphicView localHeroGraphicView = (HeroGraphicView)localView1.findViewById(2131230865);
    String str = null;
    boolean bool = false;
    if (localDocument.getDocumentType() == 18)
    {
      bool = true;
      str = ThumbnailUtils.getPromoBitmapUrlFromDocument(localDocument, 0, this.mHeroImageHeight);
    }
    if (TextUtils.isEmpty(str))
    {
      str = ThumbnailUtils.getIconUrlFromDocument(localDocument, 0, this.mHeroImageHeight);
      bool = false;
    }
    localHeroGraphicView.load(this.mBitmapLoader, localDocument, str, true);
    localHeroGraphicView.bindLightboxImage(localDocument, this.mNavigationManager, bool);
    View localView4 = localView1.findViewById(2131230879);
    if (localView4 != null)
      localView4.setBackgroundColor(j);
    BadgeSection localBadgeSection = (BadgeSection)localView1.findViewById(2131230905);
    if (localBadgeSection != null)
      localBadgeSection.configure(this.mNavigationManager, getToc(), getReferrer(), localDocument, this.mBitmapLoader, paramBundle);
    View localView5 = localView1.findViewById(2131230872);
    this.mDescriptionViewBinder.bind(localView5, localDocument, -1, localDocument.getDescription(), paramBundle);
    if (localView1.findViewById(2131230880) == null)
      localView5.setBackgroundResource(2130837591);
    View localView6 = localView1.findViewById(2131230870);
    if (localView6 != null)
    {
      if ((!hasDetailsDataLoaded()) || (TextUtils.isEmpty(localDocument.getRelatedListUrl())))
        break label594;
      localView6.setVisibility(0);
      this.mRelatedViewBinder.bind(localView6, localDocument, localDocument.getRelatedHeader(), null, localDocument.getRelatedListUrl(), localDocument.getRelatedBrowseUrl(), this.mMaxCreatorRelatedItemsPerRow, this.mMaxCreatorRelatedItems, this.mUrl, null);
    }
    while (true)
    {
      ListingView localListingView = (ListingView)localView1.findViewById(2131230869);
      if (localListingView != null)
        localListingView.bindLinks(this.mUrl, localDocument);
      ExplorePanel localExplorePanel = (ExplorePanel)localView1.findViewById(2131230866);
      if (localExplorePanel != null)
        localExplorePanel.configure(localDocument, this);
      DetailsPlusOne localDetailsPlusOne = (DetailsPlusOne)localView1.findViewById(2131230941);
      if (localDetailsPlusOne != null)
        localDetailsPlusOne.bind(this.mDfeApi, this.mUrl, getArguments().getString("finsky.DetailsDataBasedFragment.cookie"), localDocument, hasDetailsDataLoaded(), paramBundle);
      rebindTvSections(localView1, localDocument, paramBundle);
      rebindMusicSections(localView1, localDocument, paramBundle);
      View localView7 = localView1.findViewById(2131230935);
      if ((hasDetailsDataLoaded()) && (localView7 != null))
        localView7.setVisibility(8);
      return;
      label594: localView6.setVisibility(8);
    }
  }

  protected void recordState(Bundle paramBundle)
  {
    View localView = getView();
    if (localView == null);
    while (true)
    {
      return;
      if (localView.findViewById(2131230872) != null)
        this.mDescriptionViewBinder.saveInstanceState(paramBundle);
      DetailsPlusOne localDetailsPlusOne = (DetailsPlusOne)localView.findViewById(2131230941);
      if (localDetailsPlusOne != null)
        localDetailsPlusOne.saveInstanceState(paramBundle);
      this.mSeasonListViewBinder.saveInstanceState(paramBundle);
      this.mSongListViewBinder.saveInstanceState(paramBundle);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.CreatorDetailsFragment
 * JD-Core Version:    0.6.2
 */
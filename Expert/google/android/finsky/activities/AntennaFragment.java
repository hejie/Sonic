package com.google.android.finsky.activities;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.DescriptionEditorialHeader;
import com.google.android.finsky.layout.DetailsPlusOne;
import com.google.android.finsky.layout.EditorialPageHeader;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.remoting.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.remoting.protos.DocAnnotations.SeriesAntenna;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.List;

public class AntennaFragment extends DetailsDataBasedFragment
{
  private final DetailsPackViewBinder mBodyOfWorkViewBinder = new DetailsPackViewBinder();
  private final int mDefaultDescriptionLines;
  private final DetailsTextViewBinder mDescriptionViewBinder = new DetailsTextViewBinder();
  private final int mMaxRelatedItems;
  private final int mMaxRelatedItemsPerRow;
  private final SongListViewBinder mSongListViewBinder = new SongListViewBinder();
  private final boolean mUseWideHeaderImage;

  public AntennaFragment()
  {
    Resources localResources = FinskyApp.get().getResources();
    this.mMaxRelatedItemsPerRow = localResources.getInteger(2131492869);
    this.mMaxRelatedItems = localResources.getInteger(2131492875);
    this.mDefaultDescriptionLines = localResources.getInteger(2131492882);
    this.mUseWideHeaderImage = localResources.getBoolean(2131296262);
  }

  public static AntennaFragment newInstance(Document paramDocument, String paramString1, String paramString2, String paramString3)
  {
    AntennaFragment localAntennaFragment = new AntennaFragment();
    localAntennaFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localAntennaFragment.setInitialDocument(paramDocument);
    localAntennaFragment.setArgument("finsky.DetailsDataBasedFragment.cookie", paramString2);
    localAntennaFragment.setArgument("finsk.DetailsDatabasedFragment.referrer", paramString3);
    return localAntennaFragment;
  }

  protected int getLayoutRes()
  {
    return 2130968679;
  }

  public void onDestroyView()
  {
    recordState();
    this.mDescriptionViewBinder.onDestroyView();
    this.mSongListViewBinder.onDestroyView();
    this.mBodyOfWorkViewBinder.onDestroyView();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
    this.mDescriptionViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mDefaultDescriptionLines);
    this.mSongListViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager);
    this.mBodyOfWorkViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc());
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(null);
    if (getDocument() != null)
    {
      Document localDocument = getDocument();
      if (localDocument != null)
        this.mPageFragmentHost.updateCurrentBackendId(localDocument.getBackend());
    }
  }

  protected void rebindViews(Bundle paramBundle)
  {
    rebindActionBar();
    Document localDocument = getDocument();
    DocAnnotations.SeriesAntenna localSeriesAntenna = localDocument.getAntennaInfo();
    View localView1 = getView();
    HeroGraphicView localHeroGraphicView1 = (HeroGraphicView)localView1.findViewById(2131230990);
    localHeroGraphicView1.hideAccessibilityOverlay();
    localHeroGraphicView1.load(this.mBitmapLoader, localDocument, ThumbnailUtils.getPageHeaderBannerUrlFromDocument(localDocument, this.mUseWideHeaderImage, 0, 0), true);
    ((EditorialPageHeader)localView1.findViewById(2131230991)).showSeriesInfo(localSeriesAntenna.getSeriesTitle().toUpperCase(), localSeriesAntenna.getSeriesSubtitle(), localSeriesAntenna.getColorThemeArgb());
    if (this.mUseWideHeaderImage)
      localView1.findViewById(2131230987).setBackgroundColor(Color.parseColor(localSeriesAntenna.getColorThemeArgb()));
    ((DescriptionEditorialHeader)localView1.findViewById(2131230989)).showEpisodeInfo(localSeriesAntenna.getEpisodeTitle().toUpperCase(), localSeriesAntenna.getEpisodeSubtitle());
    View localView2 = localView1.findViewById(2131230872);
    if (localView2 != null)
    {
      this.mDescriptionViewBinder.bind(localView2, localDocument, -1, localDocument.getDescription(), paramBundle);
      this.mDescriptionViewBinder.hideHeader();
    }
    HeroGraphicView localHeroGraphicView2 = (HeroGraphicView)localView1.findViewById(2131230988);
    List localList = localDocument.getImages(3);
    if ((localList != null) && (localList.size() > 0) && (!TextUtils.isEmpty(((Doc.Image)localList.get(0)).getImageUrl())))
    {
      localHeroGraphicView2.load(this.mBitmapLoader, localDocument, ThumbnailUtils.getPreviewUrlFromDocument(localDocument, 0, 0), true);
      localHeroGraphicView2.showPlayIcon(((Doc.Image)localList.get(0)).getImageUrl());
      localHeroGraphicView2.setContentDescription(2131165557);
      localHeroGraphicView2.setVisibility(0);
    }
    while (true)
    {
      SongList localSongList = (SongList)localView1.findViewById(2131230874);
      if (localSongList != null)
      {
        DocAnnotations.SectionMetadata localSectionMetadata2 = localSeriesAntenna.getSectionTracks();
        this.mSongListViewBinder.bind(localSongList, null, getResources().getString(2131165762), localSectionMetadata2.getHeader(), localSectionMetadata2.getListUrl(), false, 2147483647, hasDetailsDataLoaded(), getLibraries(), this.mBitmapLoader);
      }
      View localView3 = localView1.findViewById(2131230876);
      if (localView3 != null)
      {
        DocAnnotations.SectionMetadata localSectionMetadata1 = localSeriesAntenna.getSectionAlbums();
        this.mBodyOfWorkViewBinder.bind(localView3, localDocument, localSectionMetadata1.getHeader(), localSectionMetadata1.getDescriptionHtml(), localSectionMetadata1.getListUrl(), localSectionMetadata1.getBrowseUrl(), this.mMaxRelatedItemsPerRow, this.mMaxRelatedItems, this.mUrl, null);
      }
      DetailsPlusOne localDetailsPlusOne = (DetailsPlusOne)localView1.findViewById(2131230941);
      if (localDetailsPlusOne != null)
        localDetailsPlusOne.bind(this.mDfeApi, this.mUrl, getArguments().getString("finsky.DetailsDataBasedFragment.cookie"), localDocument, hasDetailsDataLoaded(), paramBundle);
      return;
      localHeroGraphicView2.setVisibility(8);
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
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AntennaFragment
 * JD-Core Version:    0.6.2
 */
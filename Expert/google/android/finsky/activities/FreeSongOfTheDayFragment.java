package com.google.android.finsky.activities;

import android.content.res.Resources;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.layout.FreeSongOfTheDayHeader;
import com.google.android.finsky.layout.FreeSongOfTheDaySummary;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.remoting.protos.DocAnnotations.DealOfTheDay;
import com.google.android.finsky.utils.ThumbnailUtils;

public class FreeSongOfTheDayFragment extends DetailsDataBasedFragment
{
  private final FreeSongOfTheDayAlbumViewBinder mAlbumViewBinder = new FreeSongOfTheDayAlbumViewBinder();
  private boolean mRestrictHeroHeight;

  public static FreeSongOfTheDayFragment newInstance(Document paramDocument, String paramString1, String paramString2, String paramString3)
  {
    FreeSongOfTheDayFragment localFreeSongOfTheDayFragment = new FreeSongOfTheDayFragment();
    localFreeSongOfTheDayFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localFreeSongOfTheDayFragment.setInitialDocument(paramDocument);
    localFreeSongOfTheDayFragment.setArgument("finsky.DetailsDataBasedFragment.cookie", paramString2);
    localFreeSongOfTheDayFragment.setArgument("finsk.DetailsDatabasedFragment.referrer", paramString3);
    return localFreeSongOfTheDayFragment;
  }

  private void setTiledBackground(View paramView, int paramInt)
  {
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(paramInt);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    paramView.setBackgroundDrawable(localBitmapDrawable);
  }

  protected int getLayoutRes()
  {
    return 2130968702;
  }

  public void onDestroyView()
  {
    this.mAlbumViewBinder.onDestroyView();
    super.onDestroyView();
  }

  protected void onInitViewBinders()
  {
    this.mAlbumViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getArguments().getString("finsky.DetailsDataBasedFragment.cookie"));
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
    DocAnnotations.DealOfTheDay localDealOfTheDay = localDocument.getDealOfTheDayInfo();
    View localView1 = getView();
    HeroGraphicView localHeroGraphicView = (HeroGraphicView)localView1.findViewById(2131230990);
    localHeroGraphicView.hideAccessibilityOverlay();
    localHeroGraphicView.load(this.mBitmapLoader, localDocument, ThumbnailUtils.getPageHeaderBannerUrlFromDocument(localDocument, false, 0, 0), this.mRestrictHeroHeight);
    setTiledBackground(localHeroGraphicView, 2130837714);
    ((FreeSongOfTheDayHeader)localView1.findViewById(2131230991)).showDealOfTheDayInfo(localDealOfTheDay);
    ((FreeSongOfTheDaySummary)localView1.findViewById(2131231046)).showSummary(localDocument, this.mNavigationManager, getReferrer(), null);
    TextView localTextView = (TextView)localView1.findViewById(2131231023);
    localTextView.setText(localDocument.getDescription());
    setTiledBackground(localTextView, 2130837713);
    View localView2 = localView1.findViewById(2131231047);
    if (localView2 != null)
      this.mAlbumViewBinder.bind(localView2, localDocument, localDocument.getDetailsUrl(), getReferrer());
  }

  protected void recordState(Bundle paramBundle)
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FreeSongOfTheDayFragment
 * JD-Core Version:    0.6.2
 */
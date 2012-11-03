package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.activities.DetailsTextViewBinder;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DescriptionEditorialHeader;
import com.google.android.finsky.layout.EditorialBucketContent;
import com.google.android.finsky.layout.EditorialBucketEntry;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.remoting.protos.DocAnnotations.EditorialSeriesContainer;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.List;

public class EditorialBucketListAdapter extends BucketedListAdapter
{
  private final Bucket mBucket;
  DetailsTextViewBinder mDetailsTextViewBinder;
  private final Bundle mInitialRestoreState;
  private final boolean mUseWideHeaderImage;

  public EditorialBucketListAdapter(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc, Bucket paramBucket, BucketedList<?> paramBucketedList, String paramString, Bundle paramBundle)
  {
  }

  private void bindEditorialDocument(Document paramDocument, ViewGroup paramViewGroup, int paramInt)
  {
    int i = 1;
    EditorialDocumentViewHolder localEditorialDocumentViewHolder = getConvertView(paramViewGroup);
    localEditorialDocumentViewHolder.editorialEntry.setOnClickListener(this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
    localEditorialDocumentViewHolder.editorialContent.setDocument(this.mLoader, this.mNavigationManager, paramDocument, this.mCurrentPageUrl);
    String str = paramDocument.getTitle();
    localEditorialDocumentViewHolder.editorialContent.setTitle(str);
    localEditorialDocumentViewHolder.editorialContent.setTitleColor(this.mTitleForeground);
    if ((this.mColumnCount > i) && ((paramInt + 1) % this.mColumnCount == 0));
    while (true)
    {
      localEditorialDocumentViewHolder.editorialEntry.setLeftSeparatorVisibility(i);
      return;
      int j = 0;
    }
  }

  private void bindMockEditorialDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    EditorialDocumentViewHolder localEditorialDocumentViewHolder = getConvertView(paramViewGroup);
    localEditorialDocumentViewHolder.editorialContent.setMockDocument(paramBucket.getBackend());
    localEditorialDocumentViewHolder.editorialContent.setOnClickListener(null);
    localEditorialDocumentViewHolder.editorialContent.setTitleColor(this.mLoadingForeground);
  }

  private void bindNoDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    EditorialDocumentViewHolder localEditorialDocumentViewHolder = getConvertView(paramViewGroup);
    localEditorialDocumentViewHolder.editorialContent.setNoDocument();
    localEditorialDocumentViewHolder.editorialContent.setOnClickListener(null);
  }

  private EditorialDocumentViewHolder getConvertView(ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getTag() == null)
    {
      EditorialDocumentViewHolder localEditorialDocumentViewHolder = new EditorialDocumentViewHolder(null);
      localEditorialDocumentViewHolder.editorialEntry = ((EditorialBucketEntry)paramViewGroup.findViewById(2131230984));
      localEditorialDocumentViewHolder.editorialContent = ((EditorialBucketContent)paramViewGroup.findViewById(2131230985));
      paramViewGroup.setTag(localEditorialDocumentViewHolder);
    }
    return (EditorialDocumentViewHolder)paramViewGroup.getTag();
  }

  private View getEditorialHeaderView(View paramView, ViewGroup paramViewGroup)
  {
    Object localObject;
    if (this.mDetailsTextViewBinder != null)
    {
      localObject = paramView;
      return localObject;
    }
    View localView = inflate(2130968681, paramViewGroup, false);
    DescriptionEditorialHeader localDescriptionEditorialHeader = (DescriptionEditorialHeader)localView.findViewById(2131230991);
    DocAnnotations.EditorialSeriesContainer localEditorialSeriesContainer = this.mBucket.getEditorialSeriesContainer();
    localDescriptionEditorialHeader.showEpisodeInfo(localEditorialSeriesContainer.getEpisodeTitle(), localEditorialSeriesContainer.getEpisodeSubtitle());
    HeroGraphicView localHeroGraphicView1 = (HeroGraphicView)localView.findViewById(2131230990);
    Document localDocument = this.mBucket.getDocument();
    localHeroGraphicView1.load(this.mLoader, localDocument, ThumbnailUtils.getPageHeaderBannerUrlFromDocument(localDocument, this.mUseWideHeaderImage, 0, 0), true);
    HeroGraphicView localHeroGraphicView2 = (HeroGraphicView)localView.findViewById(2131230992);
    List localList = localDocument.getImages(3);
    if ((localList == null) || (localList.size() == 0))
      localHeroGraphicView2.setVisibility(8);
    while (true)
    {
      this.mDetailsTextViewBinder = new DetailsTextViewBinder();
      this.mDetailsTextViewBinder.init(this.mContext, null, this.mNavigationManager, this.mContext.getResources().getInteger(2131492883), false);
      this.mDetailsTextViewBinder.bind(localView.findViewById(2131230872), localDocument, -1, localDocument.getDescription(), this.mInitialRestoreState);
      this.mDetailsTextViewBinder.hideHeader();
      localObject = localView;
      break;
      localHeroGraphicView2.setVisibility(0);
      String str1 = ThumbnailUtils.getPreviewUrlFromDocument(localDocument, 0, localHeroGraphicView2.getHeightRestriction());
      String str2 = ((Doc.Image)localList.get(0)).getImageUrl();
      localHeroGraphicView2.load(this.mLoader, localDocument, str1, true);
      localHeroGraphicView2.showPlayIcon(str2);
    }
  }

  protected void bindRowEntry(Bucket paramBucket, Document paramDocument, int paramInt, ViewGroup paramViewGroup)
  {
    if (paramDocument == null)
      if (paramInt < this.mBucketedList.getCount())
        bindMockEditorialDocument(paramBucket, paramViewGroup);
    while (true)
    {
      return;
      bindNoDocument(paramBucket, paramViewGroup);
      continue;
      bindEditorialDocument(paramDocument, paramViewGroup, paramInt);
    }
  }

  public int getCount()
  {
    return 1 + super.getCount();
  }

  public int getItemViewType(int paramInt)
  {
    if (paramInt == 0);
    for (int i = 5; ; i = super.getItemViewType(paramInt - 1))
      return i;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    switch (getItemViewType(paramInt))
    {
    default:
    case 5:
    }
    for (View localView = super.getView(paramInt - 1, paramView, paramViewGroup); ; localView = getEditorialHeaderView(paramView, paramViewGroup))
      return localView;
  }

  public int getViewTypeCount()
  {
    return 6;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.mDetailsTextViewBinder != null)
    {
      this.mDetailsTextViewBinder.onDestroyView();
      this.mDetailsTextViewBinder = null;
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mDetailsTextViewBinder != null)
      this.mDetailsTextViewBinder.saveInstanceState(paramBundle);
  }

  private static class EditorialDocumentViewHolder
  {
    public EditorialBucketContent editorialContent;
    public EditorialBucketEntry editorialEntry;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.EditorialBucketListAdapter
 * JD-Core Version:    0.6.2
 */
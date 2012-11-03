package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.CellTitleOverlay;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.Template;
import com.google.android.finsky.remoting.protos.DocAnnotations.TileTemplate;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ThumbnailUtils;

public class GraphicColoredTitle
  implements UnevenGridAdapter.UnevenGridItem
{
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private final String mCurrentPageUrl;
  private final Document mDocument;
  private final int mHeightInCells;
  private final NavigationManager mNavigationManager;
  private final int mPromoImageWidth;
  private final DocAnnotations.TileTemplate mTileTemplate;
  private final int mWidthInCells;

  private GraphicColoredTitle(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString, int paramInt1, int paramInt2, DocAnnotations.TileTemplate paramTileTemplate)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mDocument = paramDocument;
    this.mCurrentPageUrl = paramString;
    this.mWidthInCells = paramInt1;
    this.mHeightInCells = paramInt2;
    this.mPromoImageWidth = (this.mWidthInCells * paramContext.getResources().getInteger(2131492880));
    this.mTileTemplate = paramTileTemplate;
  }

  private void bindCorpusStrip(View paramView)
  {
    if (paramView != null)
      paramView.setBackgroundColor(0xC0FFFFFF & CorpusResourceUtils.getBackendHintColor(this.mContext, this.mDocument.getBackend()));
  }

  private void bindPromoImageToBackground(final View paramView, final ImageView paramImageView)
  {
    int i = 0;
    String str = ThumbnailUtils.getPromoBitmapUrlFromDocument(this.mDocument, this.mPromoImageWidth, 0);
    if (str != null)
    {
      BitmapLoader.BitmapLoadedHandler local1 = new BitmapLoader.BitmapLoadedHandler()
      {
        public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
        {
          if ((paramAnonymousBitmapContainer != null) && (paramAnonymousBitmapContainer.getBitmap() != null))
          {
            paramImageView.setImageBitmap(paramAnonymousBitmapContainer.getBitmap());
            paramView.startAnimation(this.val$fadeInAnimation);
            paramView.setVisibility(0);
          }
          while (true)
          {
            return;
            paramImageView.setImageBitmap(null);
            paramView.setVisibility(0);
          }
        }
      };
      Bitmap localBitmap = this.mBitmapLoader.get(str, null, local1).getBitmap();
      paramImageView.setImageBitmap(localBitmap);
      if (localBitmap == null)
        i = 4;
      paramView.setVisibility(i);
    }
  }

  private void bindTitleOverlay(CellTitleOverlay paramCellTitleOverlay)
  {
    paramCellTitleOverlay.setText(this.mDocument.getTitle());
    paramCellTitleOverlay.setTextColor(Color.parseColor(this.mTileTemplate.getColorTextArgb()));
    paramCellTitleOverlay.setBackgroundColor(0xD0FFFFFF & Color.parseColor(this.mTileTemplate.getColorThemeArgb()));
  }

  public static GraphicColoredTitle create2x1(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new GraphicColoredTitle(paramContext, paramNavigationManager, paramBitmapLoader, paramDocument, paramString, 2, 1, paramDocument.getTemplate().getTileGraphicColoredTitle2X1());
  }

  public static GraphicColoredTitle create4x2(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new GraphicColoredTitle(paramContext, paramNavigationManager, paramBitmapLoader, paramDocument, paramString, 4, 2, paramDocument.getTemplate().getTileGraphicColoredTitle4X2());
  }

  public void bind(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (paramViewGroup.getTag() == null)
    {
      ViewHolder localViewHolder1 = new ViewHolder(null);
      localViewHolder1.cellContent = paramViewGroup.findViewById(2131231060);
      localViewHolder1.imageView = ((ImageView)paramViewGroup.findViewById(2131231061));
      localViewHolder1.titleOverlay = ((CellTitleOverlay)paramViewGroup.findViewById(2131230743));
      localViewHolder1.accessibilityOverlay = paramViewGroup.findViewById(2131230744);
      localViewHolder1.corpusStrip = paramViewGroup.findViewById(2131230858);
      paramViewGroup.setTag(localViewHolder1);
    }
    ViewHolder localViewHolder2 = (ViewHolder)paramViewGroup.getTag();
    bindPromoImageToBackground(localViewHolder2.cellContent, localViewHolder2.imageView);
    bindTitleOverlay(localViewHolder2.titleOverlay);
    if (paramBoolean)
      bindCorpusStrip(localViewHolder2.corpusStrip);
    localViewHolder2.accessibilityOverlay.setContentDescription(this.mDocument.getTitle());
    localViewHolder2.accessibilityOverlay.setOnClickListener(this.mNavigationManager.getClickListener(this.mDocument, this.mCurrentPageUrl));
  }

  public int getCellHeight()
  {
    return this.mHeightInCells;
  }

  public int getCellWidth()
  {
    return this.mWidthInCells;
  }

  public UnevenGridItemType getGridItemType()
  {
    return UnevenGridItemType.GRAPHIC_COLORED_TITLE_2x1;
  }

  public int getLayoutId()
  {
    return 2130968713;
  }

  private static class ViewHolder
  {
    View accessibilityOverlay;
    View cellContent;
    View corpusStrip;
    ImageView imageView;
    CellTitleOverlay titleOverlay;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.GraphicColoredTitle
 * JD-Core Version:    0.6.2
 */
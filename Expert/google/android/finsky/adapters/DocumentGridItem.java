package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.PurchaseButtonHelper;
import com.google.android.finsky.utils.ThumbnailUtils;

public class DocumentGridItem
  implements UnevenGridAdapter.UnevenGridItem
{
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private final String mCurrentPageUrl;
  private Document mDocument;
  private final UnevenGridItemType mGridItemType;
  private final int mHeightInGridCells;
  private final int mLayoutId;
  private final NavigationManager mNavigationManager;
  private final int mPromoImageWidth;
  private final int mWidthInGridCells;

  private DocumentGridItem(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, int paramInt1, int paramInt2, int paramInt3, UnevenGridItemType paramUnevenGridItemType, Document paramDocument, String paramString)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mWidthInGridCells = paramInt1;
    this.mHeightInGridCells = paramInt2;
    this.mLayoutId = paramInt3;
    this.mGridItemType = paramUnevenGridItemType;
    this.mDocument = paramDocument;
    this.mCurrentPageUrl = paramString;
    this.mPromoImageWidth = (this.mWidthInGridCells * paramContext.getResources().getInteger(2131492880));
  }

  public static DocumentGridItem create2x1PromoGraphic(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new DocumentGridItem(paramContext, paramNavigationManager, paramBitmapLoader, 2, 1, 2130968845, UnevenGridItemType.GRAPHIC_2x1, paramDocument, paramString);
  }

  public static DocumentGridItem create4x2PromoGraphic(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new DocumentGridItem(paramContext, paramNavigationManager, paramBitmapLoader, 4, 2, 2130968845, UnevenGridItemType.GRAPHIC_4x2, paramDocument, paramString);
  }

  public static DocumentGridItem createSmallPromo(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new DocumentGridItem(paramContext, paramNavigationManager, paramBitmapLoader, 2, 1, 2130968846, UnevenGridItemType.DOCUMENT_2x1, paramDocument, paramString);
  }

  public static DocumentGridItem createSquarePromo(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    return new DocumentGridItem(paramContext, paramNavigationManager, paramBitmapLoader, 2, 2, 2130968847, UnevenGridItemType.DOC_DETAILS_WITH_REFLECTED_PROMO_2x2, paramDocument, paramString);
  }

  public void bind(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (paramViewGroup.getTag() == null)
    {
      ViewHolder localViewHolder1 = new ViewHolder(null);
      localViewHolder1.ratingBar = ((RatingBar)paramViewGroup.findViewById(2131231069));
      localViewHolder1.priceView = ((TextView)paramViewGroup.findViewById(2131230752));
      localViewHolder1.thumbnailBitmapView = ((DocImageView)paramViewGroup.findViewById(2131230751));
      localViewHolder1.promoBitmapView = ((ImageView)paramViewGroup.findViewById(2131231266));
      localViewHolder1.title = ((TextView)paramViewGroup.findViewById(2131230754));
      localViewHolder1.creator = ((DecoratedTextView)paramViewGroup.findViewById(2131230755));
      localViewHolder1.badge = ((DecoratedTextView)paramViewGroup.findViewById(2131231070));
      localViewHolder1.accessibilityOverlay = paramViewGroup.findViewById(2131230744);
      localViewHolder1.cellContent = paramViewGroup.findViewById(2131231060);
      localViewHolder1.corpusStrip = paramViewGroup.findViewById(2131230858);
      paramViewGroup.setTag(localViewHolder1);
    }
    ViewHolder localViewHolder2 = (ViewHolder)paramViewGroup.getTag();
    localViewHolder2.accessibilityOverlay.setOnClickListener(this.mNavigationManager.getClickListener(this.mDocument, this.mCurrentPageUrl));
    if (localViewHolder2.title != null)
      localViewHolder2.title.setText(this.mDocument.getTitle());
    if (localViewHolder2.creator != null)
    {
      localViewHolder2.creator.setText(this.mDocument.getCreator());
      BadgeUtils.configureCreatorBadge(this.mDocument, this.mBitmapLoader, localViewHolder2.creator, -1);
    }
    if (localViewHolder2.ratingBar != null)
      BadgeUtils.configureRatingItemSection(this.mDocument, this.mBitmapLoader, localViewHolder2.ratingBar, localViewHolder2.badge);
    if (localViewHolder2.priceView != null)
      PurchaseButtonHelper.stylePurchaseButton(this.mDocument, false, localViewHolder2.priceView);
    if (localViewHolder2.thumbnailBitmapView != null)
    {
      localViewHolder2.thumbnailBitmapView.setViewToFadeIn(paramViewGroup);
      localViewHolder2.thumbnailBitmapView.bind(this.mDocument, this.mBitmapLoader);
    }
    if (localViewHolder2.promoBitmapView != null)
      bindImage(localViewHolder2.cellContent, localViewHolder2.promoBitmapView, ThumbnailUtils.getImageFromDocument(this.mDocument, this.mPromoImageWidth, 0, new int[] { 2 }), null, this.mPromoImageWidth, 0, false);
    if ((paramBoolean) && (localViewHolder2.corpusStrip != null))
    {
      int m = CorpusResourceUtils.getBackendHintColor(this.mContext, this.mDocument.getBackend());
      localViewHolder2.corpusStrip.setBackgroundColor(0xC0FFFFFF & m);
    }
    int i = this.mDocument.getBackend();
    Context localContext2;
    int k;
    Object[] arrayOfObject2;
    if (localViewHolder2.priceView != null)
    {
      localContext2 = this.mContext;
      k = CorpusResourceUtils.getCorpusCellContentLongDescriptionResource(i);
      arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = this.mDocument.getTitle();
      arrayOfObject2[1] = this.mDocument.getCreator();
      arrayOfObject2[2] = localViewHolder2.priceView.getText();
    }
    Context localContext1;
    int j;
    Object[] arrayOfObject1;
    for (String str = localContext2.getString(k, arrayOfObject2); ; str = localContext1.getString(j, arrayOfObject1))
    {
      localViewHolder2.accessibilityOverlay.setContentDescription(str);
      return;
      localContext1 = this.mContext;
      j = CorpusResourceUtils.getCorpusCellContentDescriptionResource(i);
      arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = this.mDocument.getTitle();
      arrayOfObject1[1] = this.mDocument.getCreator();
    }
  }

  protected void bindImage(final View paramView, final ImageView paramImageView, Doc.Image paramImage, final Bitmap paramBitmap, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    BitmapLoader.BitmapContainer localBitmapContainer1 = (BitmapLoader.BitmapContainer)paramImageView.getTag();
    String str;
    if (paramImage.getSupportsFifeUrlOptions())
      str = ThumbnailUtils.buildFifeUrl(paramImage.getImageUrl(), paramInt1, paramInt2);
    while ((localBitmapContainer1 != null) && (localBitmapContainer1.getRequestUrl() != null))
      if (localBitmapContainer1.getRequestUrl().equals(str))
      {
        return;
        str = paramImage.getImageUrl();
      }
      else
      {
        localBitmapContainer1.cancelRequest();
      }
    final Animation localAnimation = AnimationUtils.loadAnimation(paramImageView.getContext(), 2131034114);
    BitmapLoader.BitmapContainer localBitmapContainer2 = this.mBitmapLoader.get(str, paramBitmap, new BitmapLoader.BitmapLoadedHandler()
    {
      public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
      {
        if (paramAnonymousBitmapContainer.getBitmap() != null)
        {
          if (paramBitmap != null)
            break label45;
          paramImageView.setImageBitmap(paramAnonymousBitmapContainer.getBitmap());
          paramView.startAnimation(localAnimation);
          paramView.setVisibility(0);
        }
        while (true)
        {
          return;
          label45: ThumbnailUtils.setImageBitmapWithFade(paramImageView, paramAnonymousBitmapContainer.getBitmap());
        }
      }
    }
    , paramInt1, paramInt2);
    if (paramBoolean)
      paramImageView.getLayoutParams().width = paramInt1;
    paramImageView.setVisibility(0);
    paramImageView.setTag(localBitmapContainer2);
    Bitmap localBitmap = localBitmapContainer2.getBitmap();
    paramImageView.setImageBitmap(localBitmap);
    if (localBitmap == null);
    for (int i = 4; ; i = 0)
    {
      paramView.setVisibility(i);
      break;
    }
  }

  public int getCellHeight()
  {
    return this.mHeightInGridCells;
  }

  public int getCellWidth()
  {
    return this.mWidthInGridCells;
  }

  public UnevenGridItemType getGridItemType()
  {
    return this.mGridItemType;
  }

  public int getLayoutId()
  {
    return this.mLayoutId;
  }

  private static class ViewHolder
  {
    View accessibilityOverlay;
    DecoratedTextView badge;
    View cellContent;
    View corpusStrip;
    DecoratedTextView creator;
    TextView priceView;
    ImageView promoBitmapView;
    RatingBar ratingBar;
    DocImageView thumbnailBitmapView;
    TextView title;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.DocumentGridItem
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.utils;

import android.content.res.Resources;
import android.widget.RatingBar;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.remoting.protos.DocAnnotations.Badge;
import com.google.android.finsky.remoting.protos.DocDetails.TvEpisodeDetails;
import java.util.Iterator;
import java.util.List;

public class BadgeUtils
{
  public static void configureCreatorBadge(Document paramDocument, BitmapLoader paramBitmapLoader, DecoratedTextView paramDecoratedTextView, int paramInt)
  {
    if (paramInt != -1)
      paramDecoratedTextView.loadDecoration(paramInt);
    while (true)
    {
      return;
      if (paramDocument.hasCreatorBadges())
      {
        String str = getImageUrl(paramDocument.getFirstCreatorBadge(), 6);
        if (str != null)
          paramDecoratedTextView.loadDecoration(paramBitmapLoader, str, paramDecoratedTextView.getResources().getDimensionPixelSize(2131427397));
      }
      else
      {
        paramDecoratedTextView.setCompoundDrawables(null, null, null, null);
      }
    }
  }

  public static void configureItemBadge(Document paramDocument, BitmapLoader paramBitmapLoader, DecoratedTextView paramDecoratedTextView, int paramInt)
  {
    if (paramInt != -1)
      paramDecoratedTextView.loadDecoration(paramInt);
    while (true)
    {
      return;
      if (paramDocument.hasItemBadges())
      {
        String str = getImageUrl(paramDocument.getFirstItemBadge(), 6);
        if (str != null)
          paramDecoratedTextView.loadDecoration(paramBitmapLoader, str, paramDecoratedTextView.getResources().getDimensionPixelSize(2131427397));
      }
      else
      {
        paramDecoratedTextView.setCompoundDrawables(null, null, null, null);
      }
    }
  }

  public static void configureRatingItemSection(Document paramDocument, BitmapLoader paramBitmapLoader, RatingBar paramRatingBar, DecoratedTextView paramDecoratedTextView)
  {
    if (paramRatingBar != null)
      paramRatingBar.setVisibility(4);
    if (paramDecoratedTextView != null)
      paramDecoratedTextView.setVisibility(8);
    if ((paramDecoratedTextView != null) && (paramDocument.hasItemBadges()))
    {
      paramDecoratedTextView.setVisibility(0);
      DocAnnotations.Badge localBadge = paramDocument.getFirstItemBadge();
      String str = getImageUrl(localBadge, 6);
      if (str != null)
        paramDecoratedTextView.loadDecoration(paramBitmapLoader, str, paramDecoratedTextView.getResources().getDimensionPixelSize(2131427397));
      paramDecoratedTextView.setText(localBadge.getTitle());
      paramDecoratedTextView.setContentColorId(2131361797, false);
      paramDecoratedTextView.setTypeface(null, 1);
    }
    while (true)
    {
      return;
      if ((paramDecoratedTextView != null) && ((paramDocument.hasCensoring()) || (paramDocument.hasReleaseType())))
      {
        configureTipperSticker(paramDocument, paramDecoratedTextView);
      }
      else if ((paramDecoratedTextView != null) && (paramDocument.getDocumentType() == 20))
      {
        configureReleaseDate(paramDocument, paramDecoratedTextView);
      }
      else if ((paramRatingBar != null) && (paramDocument.hasRating()) && (paramDocument.getRatingCount() > 0L))
      {
        paramRatingBar.setRating(paramDocument.getStarRating());
        paramRatingBar.setVisibility(0);
      }
    }
  }

  private static void configureReleaseDate(Document paramDocument, DecoratedTextView paramDecoratedTextView)
  {
    if ((paramDocument.getTvEpisodeDetails() != null) && (paramDocument.getTvEpisodeDetails().hasReleaseDate()))
    {
      paramDecoratedTextView.setVisibility(0);
      paramDecoratedTextView.setContentColorId(2131361798, false);
      paramDecoratedTextView.setText(paramDocument.getTvEpisodeDetails().getReleaseDate());
      paramDecoratedTextView.setTypeface(null, 0);
    }
  }

  public static void configureTipperSticker(Document paramDocument, DecoratedTextView paramDecoratedTextView)
  {
    int i = -1;
    int j = 2131361852;
    if (paramDocument.hasCensoring());
    switch (paramDocument.getCensoring())
    {
    default:
      if ((i == -1) && (paramDocument.hasReleaseType()))
        switch (paramDocument.getReleaseType())
        {
        default:
        case 1:
        case 2:
        case 0:
        }
      break;
    case 1:
    case 2:
    }
    while (true)
    {
      if (i > -1)
      {
        paramDecoratedTextView.setVisibility(0);
        paramDecoratedTextView.setText(paramDecoratedTextView.getResources().getString(i).toUpperCase());
        paramDecoratedTextView.setContentColorId(j, true);
        paramDecoratedTextView.setTypeface(null, 0);
      }
      return;
      i = 2131165779;
      j = 2131361851;
      break;
      i = 2131165780;
      break;
      i = 2131165782;
      continue;
      i = 2131165783;
      continue;
      i = 2131165781;
    }
  }

  public static String getImageUrl(DocAnnotations.Badge paramBadge, int paramInt)
  {
    Iterator localIterator = paramBadge.getImageList().iterator();
    Doc.Image localImage;
    do
    {
      if (!localIterator.hasNext())
        break;
      localImage = (Doc.Image)localIterator.next();
    }
    while (localImage.getImageType() != paramInt);
    for (String str = localImage.getImageUrl(); ; str = null)
      return str;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.BadgeUtils
 * JD-Core Version:    0.6.2
 */
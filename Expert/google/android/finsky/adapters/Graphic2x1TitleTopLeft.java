package com.google.android.finsky.adapters;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.Template;
import com.google.android.finsky.remoting.protos.DocAnnotations.TileTemplate;
import com.google.android.finsky.utils.BitmapLoader;

public class Graphic2x1TitleTopLeft
  implements UnevenGridAdapter.UnevenGridItem
{
  private final BitmapLoader mBitmapLoader;
  private final Document mContainerDoc;
  private final String mCurrentPageUrl;
  private final NavigationManager mNavigationManager;

  public Graphic2x1TitleTopLeft(NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, String paramString)
  {
    this.mNavigationManager = paramNavigationManager;
    this.mBitmapLoader = paramBitmapLoader;
    this.mContainerDoc = paramDocument;
    this.mCurrentPageUrl = paramString;
  }

  public void bind(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    if (paramViewGroup.getTag() == null)
    {
      ViewHolder localViewHolder1 = new ViewHolder(null);
      localViewHolder1.imageView = ((DocImageView)paramViewGroup.findViewById(2131231061));
      localViewHolder1.title = ((TextView)paramViewGroup.findViewById(2131230754));
      localViewHolder1.accessibilityOverlay = paramViewGroup.findViewById(2131230744);
      paramViewGroup.setTag(localViewHolder1);
    }
    ViewHolder localViewHolder2 = (ViewHolder)paramViewGroup.getTag();
    localViewHolder2.imageView.bind(this.mContainerDoc, this.mBitmapLoader, new int[] { 2 });
    DocAnnotations.Template localTemplate = this.mContainerDoc.getTemplate();
    if ((localTemplate != null) && (localTemplate.getTileGraphicUpperLeftTitle2X1() != null))
    {
      String str = localTemplate.getTileGraphicUpperLeftTitle2X1().getColorTextArgb();
      if (!TextUtils.isEmpty(str))
        localViewHolder2.title.setTextColor(Color.parseColor(str));
    }
    localViewHolder2.title.setText(this.mContainerDoc.getTitle());
    localViewHolder2.accessibilityOverlay.setContentDescription(this.mContainerDoc.getTitle());
    localViewHolder2.accessibilityOverlay.setOnClickListener(this.mNavigationManager.getClickListener(this.mContainerDoc, this.mCurrentPageUrl));
  }

  public int getCellHeight()
  {
    return 1;
  }

  public int getCellWidth()
  {
    return 2;
  }

  public UnevenGridItemType getGridItemType()
  {
    return UnevenGridItemType.GRAPHIC_2x1_TITLE_TOP_LEFT;
  }

  public int getLayoutId()
  {
    return 2130968787;
  }

  private static class ViewHolder
  {
    View accessibilityOverlay;
    DocImageView imageView;
    TextView title;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.Graphic2x1TitleTopLeft
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;

public class BadgeRow extends LinearLayout
{
  private final int mBadgeImageSize = getResources().getDimensionPixelSize(2131427398);
  private LinearLayout mExtraBadgeContainer;
  private DecoratedTextView mPrimaryBadge;

  public BadgeRow(Context paramContext)
  {
    this(paramContext, null);
  }

  public BadgeRow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void addExtraBadge(BitmapLoader paramBitmapLoader, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      ImageView localImageView = (ImageView)LayoutInflater.from(getContext()).inflate(2130968585, this.mExtraBadgeContainer, false);
      BitmapLoader.BitmapContainer localBitmapContainer = paramBitmapLoader.get(paramString, null, new ThumbnailListener(localImageView, true), this.mBadgeImageSize, this.mBadgeImageSize);
      if (localBitmapContainer.getBitmap() != null)
        localImageView.setImageBitmap(localBitmapContainer.getBitmap());
      this.mExtraBadgeContainer.addView(localImageView);
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mExtraBadgeContainer = ((LinearLayout)findViewById(2131230748));
    this.mPrimaryBadge = ((DecoratedTextView)findViewById(2131230749));
  }

  public void setPrimaryBadge(final NavigationManager paramNavigationManager, final DfeToc paramDfeToc, final int paramInt, final String paramString, BitmapLoader paramBitmapLoader, final DocAnnotations.Badge paramBadge)
  {
    String str1 = BadgeUtils.getImageUrl(paramBadge, 6);
    if (str1 != null)
      this.mPrimaryBadge.loadDecoration(paramBitmapLoader, str1, this.mBadgeImageSize);
    final String str2 = paramBadge.getTitle();
    this.mPrimaryBadge.setText(str2.toUpperCase());
    if (paramBadge.hasBrowseUrl())
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramNavigationManager.goBrowse(paramBadge.getBrowseUrl(), str2, paramInt, paramString, paramDfeToc);
        }
      });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.BadgeRow
 * JD-Core Version:    0.6.2
 */
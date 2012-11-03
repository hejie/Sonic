package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ExpandableUtils;
import java.util.Collection;
import java.util.Iterator;

public class BadgeSection extends LinearLayout
{
  private BitmapLoader mBitmapLoader;
  private DfeToc mDfeToc;
  private Document mDocument;
  private int mExpansionState;
  private LayoutInflater mLayoutInflater;
  private NavigationManager mNavigationManager;
  private String mReferrerUrl;

  public BadgeSection(Context paramContext)
  {
    super(paramContext);
  }

  public BadgeSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void createOverviewRow()
  {
    removeAllViews();
    addView(createPrimaryBadgeRow());
    this.mExpansionState = 1;
  }

  private BadgeRow createPrimaryBadgeRow()
  {
    BadgeRow localBadgeRow = (BadgeRow)this.mLayoutInflater.inflate(2130968586, this, false);
    if (this.mDocument.hasItemBadges());
    int i;
    for (DocAnnotations.Badge localBadge1 = this.mDocument.getFirstItemBadge(); ; localBadge1 = this.mDocument.getFirstCreatorBadge())
    {
      localBadgeRow.setPrimaryBadge(this.mNavigationManager, this.mDfeToc, this.mDocument.getBackend(), this.mReferrerUrl, this.mBitmapLoader, localBadge1);
      i = 0;
      Iterator localIterator1 = this.mDocument.getCreatorBadges().iterator();
      while (localIterator1.hasNext())
      {
        DocAnnotations.Badge localBadge3 = (DocAnnotations.Badge)localIterator1.next();
        if (localBadge3 != localBadge1)
        {
          localBadgeRow.addExtraBadge(this.mBitmapLoader, BadgeUtils.getImageUrl(localBadge3, 6));
          i = 1;
        }
      }
    }
    Iterator localIterator2 = this.mDocument.getItemBadges().iterator();
    while (localIterator2.hasNext())
    {
      DocAnnotations.Badge localBadge2 = (DocAnnotations.Badge)localIterator2.next();
      if (localBadge2 != localBadge1)
      {
        localBadgeRow.addExtraBadge(this.mBitmapLoader, BadgeUtils.getImageUrl(localBadge2, 6));
        i = 1;
      }
    }
    if (i != 0)
      localBadgeRow.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BadgeSection.this.expand();
        }
      });
    return localBadgeRow;
  }

  private BadgeRow createSecondaryBadgeRow(DocAnnotations.Badge paramBadge)
  {
    BadgeRow localBadgeRow = (BadgeRow)this.mLayoutInflater.inflate(2130968586, this, false);
    localBadgeRow.setPrimaryBadge(this.mNavigationManager, this.mDfeToc, this.mDocument.getBackend(), this.mReferrerUrl, this.mBitmapLoader, paramBadge);
    return localBadgeRow;
  }

  private void expand()
  {
    removeAllViews();
    Iterator localIterator1 = this.mDocument.getCreatorBadges().iterator();
    while (localIterator1.hasNext())
      addView(createSecondaryBadgeRow((DocAnnotations.Badge)localIterator1.next()));
    Iterator localIterator2 = this.mDocument.getItemBadges().iterator();
    while (localIterator2.hasNext())
      addView(createSecondaryBadgeRow((DocAnnotations.Badge)localIterator2.next()));
    this.mExpansionState = 2;
  }

  public void configure(NavigationManager paramNavigationManager, DfeToc paramDfeToc, String paramString, Document paramDocument, BitmapLoader paramBitmapLoader, Bundle paramBundle)
  {
    this.mDocument = paramDocument;
    this.mBitmapLoader = paramBitmapLoader;
    this.mNavigationManager = paramNavigationManager;
    this.mDfeToc = paramDfeToc;
    this.mReferrerUrl = paramString;
    if ((!this.mDocument.hasCreatorBadges()) && (!this.mDocument.hasItemBadges()))
      setVisibility(8);
    while (true)
    {
      return;
      setVisibility(0);
      this.mLayoutInflater = LayoutInflater.from(getContext());
      this.mExpansionState = ExpandableUtils.getSavedExpansionState(paramBundle, "", 1);
      if (this.mExpansionState == 2)
        expand();
      else
        createOverviewRow();
    }
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    ExpandableUtils.saveExpansionState(paramBundle, "", this.mExpansionState);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.BadgeSection
 * JD-Core Version:    0.6.2
 */
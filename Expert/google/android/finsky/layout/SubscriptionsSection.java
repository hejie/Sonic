package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.LibrarySubscriptionEntry;

public class SubscriptionsSection extends LinearLayout
{
  private final boolean mIsTwoColumnLayout;
  private final LayoutInflater mLayoutInflater;

  public SubscriptionsSection(Context paramContext)
  {
    this(paramContext, null);
  }

  public SubscriptionsSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mIsTwoColumnLayout = paramContext.getResources().getBoolean(2131296262);
  }

  public void addSubscription(Document paramDocument, LibrarySubscriptionEntry paramLibrarySubscriptionEntry, int paramInt, SubscriptionView.CancelListener paramCancelListener, Bundle paramBundle)
  {
    SubscriptionView localSubscriptionView = (SubscriptionView)this.mLayoutInflater.inflate(paramInt, this, false);
    localSubscriptionView.configure(paramDocument, paramLibrarySubscriptionEntry, paramCancelListener, paramBundle);
    addView(localSubscriptionView);
  }

  public void clearSubscriptions()
  {
    removeAllViews();
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    for (int i = 0; i < getChildCount(); i++)
    {
      View localView = getChildAt(i);
      if ((localView instanceof SubscriptionView))
        ((SubscriptionView)localView).saveInstanceState(paramBundle);
    }
  }

  public void syncSeparatorVisibility()
  {
    if (this.mIsTwoColumnLayout);
    while (true)
    {
      return;
      int i = getChildCount();
      if (i != 0)
      {
        ((SubscriptionView)getChildAt(0)).showTopSeparator();
        ((SubscriptionView)getChildAt(i - 1)).hideBottomSeparator();
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SubscriptionsSection
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.DetailsTextLayout;
import com.google.android.finsky.layout.DetailsTextLayout.MetricsListener;
import com.google.android.finsky.layout.ObservableScrollView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ExpandableUtils;

public class DetailsTextViewBinder extends DetailsViewBinder
{
  private DetailsTextLayout mContentLayout;
  private TextView mContentView;
  private int mDefaultMaxLines;
  private int mExpansionState = -1;
  private ImageView mFooterIcon;
  private int mFullHeight;
  private boolean mShouldScrollWhenCollapsing;
  private int mTruncatedHeight;
  private boolean mUrlSpanClicked;

  private void collapseContent(boolean paramBoolean)
  {
    ObservableScrollView localObservableScrollView;
    int i;
    View localView;
    label47: int j;
    if ((paramBoolean) && (this.mShouldScrollWhenCollapsing))
    {
      localObservableScrollView = null;
      i = this.mLayout.getTop();
      localView = (View)this.mLayout.getParent();
      if (!(localView instanceof ObservableScrollView))
        break label121;
      localObservableScrollView = (ObservableScrollView)localView;
      if (localObservableScrollView != null)
      {
        j = localObservableScrollView.getScrollY();
        if (i < j)
          if (i + this.mContentView.getHeight() >= j + localObservableScrollView.getHeight())
            break label154;
      }
    }
    label154: for (int k = this.mTruncatedHeight - this.mFullHeight; ; k = i - j)
    {
      localObservableScrollView.scrollBy(0, k);
      this.mContentLayout.setCurrentMaxLines(this.mDefaultMaxLines);
      showMoreIndicator();
      this.mExpansionState = 1;
      return;
      label121: i += localView.getTop();
      ViewParent localViewParent = localView.getParent();
      if (!(localViewParent instanceof View))
        break label47;
      localView = (View)localViewParent;
      break;
    }
  }

  private void configureContent()
  {
    if (this.mExpansionState == 2)
      expandContent();
    while (true)
    {
      this.mContentLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DetailsTextViewBinder.this.handleClick();
        }
      });
      this.mContentView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          DetailsTextViewBinder.this.handleClick();
        }
      });
      return;
      collapseContent(false);
    }
  }

  private void expandContent()
  {
    this.mContentLayout.setCurrentMaxLines(2147483647);
    showLessIndicator();
    this.mExpansionState = 2;
  }

  private void handleClick()
  {
    this.mContentView.scrollTo(0, 0);
    if (this.mUrlSpanClicked)
      this.mUrlSpanClicked = false;
    while (true)
    {
      return;
      if (this.mExpansionState == 2)
        collapseContent(true);
      else
        expandContent();
    }
  }

  private void selfishifyUrlSpans(CharSequence paramCharSequence)
  {
    if (!(paramCharSequence instanceof Spannable));
    while (true)
    {
      return;
      Spannable localSpannable = (Spannable)paramCharSequence;
      for (URLSpan localURLSpan : (URLSpan[])localSpannable.getSpans(0, localSpannable.length(), URLSpan.class))
      {
        int k = localSpannable.getSpanStart(localURLSpan);
        int m = localSpannable.getSpanEnd(localURLSpan);
        localSpannable.removeSpan(localURLSpan);
        localSpannable.setSpan(new SelfishUrlSpan(localURLSpan.getURL()), k, m, 0);
      }
    }
  }

  private void showLessIndicator()
  {
    this.mFooterIcon.setImageResource(2130837652);
  }

  private void showMoreIndicator()
  {
    this.mFooterIcon.setImageResource(2130837648);
  }

  public void bind(View paramView, Document paramDocument, int paramInt, CharSequence paramCharSequence, Bundle paramBundle)
  {
    super.bind(paramView, paramDocument, paramInt);
    if (TextUtils.isEmpty(paramCharSequence))
      this.mLayout.setVisibility(8);
    while (true)
    {
      return;
      this.mLayout.setVisibility(0);
      this.mContentLayout = ((DetailsTextLayout)this.mLayout.findViewById(2131230972));
      this.mContentView = ((TextView)this.mLayout.findViewById(2131230973));
      this.mContentView.setMovementMethod(LinkMovementMethod.getInstance());
      this.mFooterIcon = ((ImageView)this.mLayout.findViewById(2131230974));
      selfishifyUrlSpans(paramCharSequence);
      this.mContentView.setText(paramCharSequence);
      this.mFullHeight = -1;
      this.mTruncatedHeight = -1;
      if (this.mExpansionState < 0)
        this.mExpansionState = ExpandableUtils.getSavedExpansionState(paramBundle, Integer.toString(this.mLayout.getId()), 1);
      this.mContentLayout.setDefaultMaxLines(this.mDefaultMaxLines);
      if (this.mExpansionState == 2)
      {
        this.mContentLayout.setCurrentMaxLines(2147483647);
        showLessIndicator();
      }
      this.mContentLayout.setMetricsListener(new DetailsTextLayout.MetricsListener()
      {
        public void metricsAvailable(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          DetailsTextViewBinder.access$002(DetailsTextViewBinder.this, paramAnonymousInt1);
          DetailsTextViewBinder.access$102(DetailsTextViewBinder.this, paramAnonymousInt2);
          if (DetailsTextViewBinder.this.mFullHeight <= DetailsTextViewBinder.this.mTruncatedHeight)
            DetailsTextViewBinder.this.mFooterIcon.setVisibility(8);
          while (true)
          {
            return;
            DetailsTextViewBinder.this.configureContent();
          }
        }
      });
    }
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, int paramInt)
  {
    init(paramContext, paramDfeApi, paramNavigationManager, paramInt, true);
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, int paramInt, boolean paramBoolean)
  {
    super.init(paramContext, paramDfeApi, paramNavigationManager);
    this.mDefaultMaxLines = paramInt;
    this.mShouldScrollWhenCollapsing = paramBoolean;
  }

  public void onDestroyView()
  {
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    if (this.mLayout != null)
      ExpandableUtils.saveExpansionState(paramBundle, Integer.toString(this.mLayout.getId()), this.mExpansionState);
  }

  public void setDefaultMaxLines(int paramInt)
  {
    this.mDefaultMaxLines = paramInt;
    if (this.mContentLayout != null)
      this.mContentLayout.setDefaultMaxLines(paramInt);
  }

  private class SelfishUrlSpan extends URLSpan
  {
    public SelfishUrlSpan(String arg2)
    {
      super();
    }

    public void onClick(View paramView)
    {
      DetailsTextViewBinder.access$402(DetailsTextViewBinder.this, true);
      Context localContext = paramView.getContext();
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(getURL()));
      localIntent.setPackage(localContext.getPackageName());
      if (localContext.getPackageManager().resolveActivity(localIntent, 65536) != null)
        DetailsTextViewBinder.this.mNavigationManager.handleDeepLink(getURL());
      while (true)
      {
        return;
        localIntent.setPackage(null);
        localContext.startActivity(localIntent);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsTextViewBinder
 * JD-Core Version:    0.6.2
 */
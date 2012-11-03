package com.google.android.finsky.activities;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;

public class FreeSongOfTheDayAlbumViewBinder extends DetailsViewBinder
  implements OnDataChangedListener
{
  private BitmapLoader mBitmapLoader;
  private String mCookie;
  private DfeDetails mDetailsData;
  private String mReferrerUrl;
  private String mUrl;

  private void attachToInternalRequest()
  {
    this.mDetailsData.addDataChangedListener(this);
    this.mDetailsData.addErrorListener(this);
    if (this.mDetailsData.getDocument() != null)
    {
      this.mLayout.setVisibility(0);
      this.mLayoutSwitcher.switchToDataMode();
      prepareAndPopulateContent();
    }
    while (true)
    {
      return;
      if (this.mDetailsData.inErrorState())
        this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(this.mContext, this.mDetailsData.getVolleyError()));
      else
        this.mLayoutSwitcher.switchToLoadingMode();
    }
  }

  private void detachListeners()
  {
    if (this.mDetailsData != null)
    {
      this.mDetailsData.removeDataChangedListener(this);
      this.mDetailsData.removeErrorListener(this);
    }
  }

  private void prepareAndPopulateContent()
  {
    final Document localDocument = this.mDetailsData.getDocument();
    ((TextView)this.mLayout.findViewById(2131230957)).setText(localDocument.getTitle());
    ((DecoratedTextView)this.mLayout.findViewById(2131230958)).setText(localDocument.getCreator().toUpperCase());
    ((DocImageView)this.mLayout.findViewById(2131230909)).bind(this.mDoc, this.mBitmapLoader);
    View localView = this.mLayout;
    Context localContext = this.mContext;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localDocument.getTitle();
    arrayOfObject[1] = localDocument.getCreator();
    localView.setContentDescription(localContext.getString(2131165667, arrayOfObject));
    this.mLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FreeSongOfTheDayAlbumViewBinder.this.mNavigationManager.goToDocPage(localDocument.getDetailsUrl(), FreeSongOfTheDayAlbumViewBinder.this.mCookie, FreeSongOfTheDayAlbumViewBinder.this.mReferrerUrl, null, null);
      }
    });
  }

  private void retryRequest()
  {
    detachListeners();
    this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, null);
    attachToInternalRequest();
    this.mLayoutSwitcher.switchToLoadingMode();
  }

  public void bind(View paramView, Document paramDocument, String paramString1, String paramString2)
  {
    super.bind(paramView, paramDocument, 2131165776);
    this.mUrl = paramString1;
    this.mReferrerUrl = paramString2;
    setupView();
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, String paramString)
  {
    super.init(paramContext, paramDfeApi, paramNavigationManager);
    this.mBitmapLoader = paramBitmapLoader;
    this.mCookie = paramString;
  }

  public void onDataChanged()
  {
    this.mLayoutSwitcher.switchToDataMode();
    if ((this.mDetailsData != null) && (this.mDetailsData.isReady()) && (this.mDetailsData.getDocument() != null))
      prepareAndPopulateContent();
    while (true)
    {
      return;
      this.mLayout.setVisibility(8);
    }
  }

  public void onDestroyView()
  {
    detachListeners();
  }

  public void setupView()
  {
    this.mLayoutSwitcher = new LayoutSwitcher(this.mLayout, 2131230934, 2131231139, 2131230808, new LayoutSwitcher.RetryButtonListener()
    {
      public void onRetry()
      {
        FreeSongOfTheDayAlbumViewBinder.this.retryRequest();
      }
    }
    , 2);
    ((TextView)this.mLayout.findViewById(2131231054)).setText(2131165432);
    this.mLayoutSwitcher.switchToLoadingDelayed(350);
    if (!TextUtils.isEmpty(this.mUrl))
    {
      this.mLayout.setVisibility(0);
      detachListeners();
      this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, null);
      attachToInternalRequest();
    }
    while (true)
    {
      return;
      this.mLayout.setVisibility(8);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FreeSongOfTheDayAlbumViewBinder
 * JD-Core Version:    0.6.2
 */
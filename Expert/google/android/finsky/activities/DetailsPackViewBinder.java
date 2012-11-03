package com.google.android.finsky.activities;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.DocumentCell;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.MagazineBucketEntry;
import com.google.android.finsky.layout.OverviewBucketEntry;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;
import java.util.List;

public class DetailsPackViewBinder extends DetailsViewBinder
  implements OnDataChangedListener
{
  private BitmapLoader mBitmapLoader;
  private String mCurrentPageUrl;
  private boolean mHaveLoggedBefore;
  private DfeList mItemListRequest;
  private int mMaxItemsCount;
  private int mMaxItemsPerRow;
  private String mMoreUrl;
  private String mReferrerUrl;
  private DfeToc mToc;
  private String mUrl;

  private void attachToInternalRequest()
  {
    if (this.mItemListRequest == null)
      throw new IllegalStateException("Cannot attach when no request is held internally");
    this.mItemListRequest.addDataChangedListener(this);
    this.mItemListRequest.addErrorListener(this);
    if (this.mItemListRequest.getCount() != 0)
    {
      this.mLayout.setVisibility(0);
      this.mLayoutSwitcher.switchToDataMode();
      prepareAndPopulateContent();
    }
    while (true)
    {
      return;
      if (!this.mItemListRequest.isMoreAvailable())
        handleEmptyData();
      else if (this.mItemListRequest.inErrorState())
        this.mLayoutSwitcher.switchToErrorMode(ErrorStrings.get(this.mContext, this.mItemListRequest.getVolleyError()));
    }
  }

  private void detachListeners()
  {
    if (this.mItemListRequest != null)
    {
      this.mItemListRequest.removeDataChangedListener(this);
      this.mItemListRequest.removeErrorListener(this);
    }
  }

  private void logListView()
  {
    String str1;
    Analytics localAnalytics;
    String str2;
    if ((this.mItemListRequest != null) && (this.mItemListRequest.isReady()) && (this.mItemListRequest.getBucketCount() > 0) && (!this.mHaveLoggedBefore))
    {
      str1 = ((Bucket)this.mItemListRequest.getBucketList().get(0)).getAnalyticsCookie();
      localAnalytics = FinskyApp.get().getAnalytics();
      str2 = this.mReferrerUrl;
      if (this.mDoc == null)
        break label123;
    }
    label123: for (String str3 = this.mDoc.getCookie(); ; str3 = null)
    {
      localAnalytics.logListViewOnPage(str2, str3, this.mCurrentPageUrl, str1);
      FinskyApp.get().getEventLogger().logView(this.mCurrentPageUrl, str1, this.mItemListRequest.getInitialUrl());
      this.mHaveLoggedBefore = true;
      return;
    }
  }

  private void populateArtistCell(ViewGroup paramViewGroup, Document paramDocument, boolean paramBoolean)
  {
    View localView = this.mInflater.inflate(2130968823, paramViewGroup, false);
    ((DocumentCell)localView).bind(null, paramDocument, this.mBitmapLoader, paramBoolean, this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
    paramViewGroup.addView(localView);
  }

  private void populateMagazineCell(ViewGroup paramViewGroup, Document paramDocument, boolean paramBoolean)
  {
    View localView = this.mInflater.inflate(2130968727, paramViewGroup, false);
    ((MagazineBucketEntry)localView.findViewById(2131231082)).bind(this.mDoc, paramDocument, this.mBitmapLoader, paramBoolean, this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
    paramViewGroup.addView(localView);
  }

  private void prepareAndPopulateContent()
  {
    this.mLayout.findViewById(2131230938).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DetailsPackViewBinder.this.mNavigationManager.goBrowse(DetailsPackViewBinder.this.mMoreUrl, DetailsPackViewBinder.this.mDoc.getTitle(), DetailsPackViewBinder.this.mDoc.getBackend(), DetailsPackViewBinder.this.mCurrentPageUrl, DetailsPackViewBinder.this.mToc);
      }
    });
    populateContent();
  }

  private void setupItemListRequest()
  {
    detachListeners();
    this.mItemListRequest = new DfeList(this.mDfeApi, this.mUrl, false);
    attachToInternalRequest();
    this.mItemListRequest.addErrorListener(this);
    this.mItemListRequest.startLoadItems();
  }

  private void setupView()
  {
    if (this.mItemListRequest != null)
      this.mItemListRequest.clearTransientState();
    ((TextView)this.mLayout.findViewById(2131231054)).setText(2131165432);
    this.mLayoutSwitcher.switchToLoadingDelayed(350);
    if (!TextUtils.isEmpty(this.mUrl))
    {
      this.mLayout.setVisibility(0);
      setupItemListRequest();
    }
    while (true)
    {
      return;
      handleEmptyData();
    }
  }

  public void bind(View paramView, Document paramDocument, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, String paramString5, String paramString6)
  {
    super.bind(paramView, paramDocument, paramString1);
    TextView localTextView = (TextView)this.mLayout.findViewById(2131230896);
    if (TextUtils.isEmpty(paramString2))
      localTextView.setVisibility(8);
    while (true)
    {
      this.mUrl = paramString3;
      this.mMoreUrl = paramString4;
      this.mCurrentPageUrl = paramString5;
      this.mReferrerUrl = paramString6;
      this.mMaxItemsPerRow = paramInt1;
      this.mMaxItemsCount = paramInt2;
      if (this.mLayoutSwitcher == null)
        this.mLayoutSwitcher = new LayoutSwitcher(this.mLayout, 2131230934, new LayoutSwitcher.RetryButtonListener()
        {
          public void onRetry()
          {
            DetailsPackViewBinder.this.mItemListRequest.retryLoadItems();
          }
        });
      setupView();
      return;
      localTextView.setVisibility(0);
      localTextView.setText(Html.fromHtml(paramString2));
    }
  }

  protected void handleEmptyData()
  {
    this.mLayout.setVisibility(8);
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc)
  {
    super.init(paramContext, paramDfeApi, paramNavigationManager);
    this.mBitmapLoader = paramBitmapLoader;
    this.mToc = paramDfeToc;
  }

  public void onDataChanged()
  {
    this.mLayoutSwitcher.switchToDataMode();
    if (this.mItemListRequest.getCount() == 0)
      handleEmptyData();
    while (true)
    {
      return;
      logListView();
      this.mLayout.setVisibility(0);
      prepareAndPopulateContent();
    }
  }

  public void onDestroyView()
  {
    this.mHaveLoggedBefore = false;
    detachListeners();
  }

  protected void populateContent()
  {
    LinearLayout localLinearLayout = (LinearLayout)this.mLayout.findViewById(2131230934);
    localLinearLayout.removeAllViews();
    int i = Math.min(this.mItemListRequest.getCount(), this.mMaxItemsCount);
    int j = (-1 + (i + this.mMaxItemsPerRow)) / this.mMaxItemsPerRow;
    int k = 0;
    if (j == 0)
      handleEmptyData();
    while (true)
    {
      return;
      setHeaderNavigable(true);
      this.mLayout.findViewById(2131230939).setVisibility(0);
      this.mLayout.findViewById(2131230933).setVisibility(0);
      for (int m = 0; m < j; m++)
      {
        int n = j - 1;
        boolean bool1;
        BucketRow localBucketRow;
        int i1;
        label142: Document localDocument;
        label171: int i2;
        int i4;
        label203: label221: View localView;
        OverviewBucketEntry localOverviewBucketEntry;
        boolean bool2;
        if (m == n)
        {
          bool1 = true;
          localBucketRow = (BucketRow)this.mInflater.inflate(2130968609, localLinearLayout, false);
          localBucketRow.setCompact(true);
          i1 = 0;
          if (i1 >= this.mMaxItemsPerRow)
            break label394;
          if (k >= i)
            break label329;
          localDocument = (Document)this.mItemListRequest.getItem(k);
          i2 = 0;
          if (localDocument != null)
          {
            int i3 = localDocument.getDocumentType();
            if ((i3 != 16) && (i3 != 17))
              break label335;
            i4 = 1;
            if (i4 == 0)
              break label341;
            populateMagazineCell(localBucketRow, localDocument, bool1);
            i2 = 1;
          }
          if (i2 == 0)
          {
            localView = this.mInflater.inflate(2130968772, localBucketRow, false);
            localOverviewBucketEntry = (OverviewBucketEntry)localView.findViewById(2131230750);
            if (localDocument == null)
              break label371;
            localOverviewBucketEntry.bind(null, localDocument, this.mBitmapLoader, bool1, this.mNavigationManager.getClickListener(localDocument, this.mCurrentPageUrl));
            if (i1 >= -1 + this.mMaxItemsPerRow)
              break label365;
            bool2 = true;
            label300: localOverviewBucketEntry.setRightSeparatorVisibility(bool2);
          }
        }
        while (true)
        {
          localBucketRow.addView(localView);
          k++;
          i1++;
          break label142;
          bool1 = false;
          break;
          label329: localDocument = null;
          break label171;
          label335: i4 = 0;
          break label203;
          label341: if (localDocument.getArtistDetails() == null)
            break label221;
          populateArtistCell(localBucketRow, localDocument, bool1);
          i2 = 1;
          break label221;
          label365: bool2 = false;
          break label300;
          label371: if (k == i)
            localOverviewBucketEntry.setNoDocument();
          else
            localOverviewBucketEntry.setVisibility(4);
        }
        label394: localLinearLayout.addView(localBucketRow);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsPackViewBinder
 * JD-Core Version:    0.6.2
 */
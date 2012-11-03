package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.ArtistBucketEntry;
import com.google.android.finsky.layout.MagazineBucketEntry;
import com.google.android.finsky.layout.OverviewBucketEntry;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.ContainerWithBanner;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.ThumbnailUtils;
import com.google.android.finsky.utils.VendingUtils;
import java.util.List;

public class BucketedListAdapter extends PaginatedListAdapter
{
  protected BucketedList<?> mBucketedList;
  private final int mCellLayoutId;
  protected final int mColumnCount;
  protected String mCurrentPageUrl;
  private final int mHeaderImageMaxHeight;
  protected final BitmapLoader mLoader;
  protected final int mLoadingForeground;
  private final int mRowCount;
  private final int mSmallestWidth;
  private boolean mSwitchToCustomLayoutIfNecessary = true;
  protected final int mTitleForeground;
  protected final DfeToc mToc;

  public BucketedListAdapter(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc, BucketedList<?> paramBucketedList, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    super(paramContext, paramNavigationManager, paramBucketedList.inErrorState(), paramBucketedList.isMoreAvailable());
    this.mLoader = paramBitmapLoader;
    this.mCurrentPageUrl = paramString;
    this.mBucketedList = paramBucketedList;
    this.mBucketedList.addDataChangedListener(this);
    this.mColumnCount = paramInt1;
    this.mRowCount = paramInt2;
    this.mCellLayoutId = paramInt3;
    this.mToc = paramDfeToc;
    Resources localResources = paramContext.getResources();
    this.mLoadingForeground = localResources.getColor(2131361798);
    this.mTitleForeground = localResources.getColor(2131361797);
    this.mHeaderImageMaxHeight = localResources.getDimensionPixelSize(2131427340);
    this.mSmallestWidth = ((Integer)VendingUtils.getScreenDimensions(paramContext).first).intValue();
  }

  private void bindArtistPromoLayer(Document paramDocument, DocumentViewHolder paramDocumentViewHolder)
  {
    if (paramDocumentViewHolder.artistStub != null)
    {
      paramDocumentViewHolder.artistLayer = ((ArtistBucketEntry)paramDocumentViewHolder.artistStub.inflate());
      paramDocumentViewHolder.artistStub = null;
    }
    paramDocumentViewHolder.artistLayer.setVisibility(0);
    if (paramDocumentViewHolder.genericLayer != null)
      paramDocumentViewHolder.genericLayer.setVisibility(8);
    if (paramDocumentViewHolder.magazineLayer != null)
      paramDocumentViewHolder.magazineLayer.setVisibility(8);
    paramDocumentViewHolder.artistLayer.bind(null, paramDocument, this.mLoader, false, this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
  }

  private void bindBucketEntries(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    LinearLayout localLinearLayout1 = (LinearLayout)paramViewGroup.findViewById(2131230824);
    int i = 0;
    while (i < this.mRowCount)
    {
      LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.getChildAt(i);
      if (i >= getDisplayedRowsForBucket(paramBucket))
      {
        localLinearLayout2.setVisibility(8);
        i++;
      }
      else
      {
        localLinearLayout2.setVisibility(0);
        int j = getDisplayedColumnsForBucket(paramBucket, i);
        int k = 0;
        label74: View localView;
        if (k < this.mColumnCount)
        {
          localView = localLinearLayout2.getChildAt(k);
          if (k >= j)
            break label149;
          int m = k + i * this.mColumnCount;
          bindRowEntry(paramBucket, (Document)paramBucket.getChildren().get(m), m, (ViewGroup)localView);
          localView.setVisibility(0);
        }
        while (true)
        {
          k++;
          break label74;
          break;
          label149: localView.setVisibility(4);
        }
      }
    }
  }

  private void bindBucketHeader(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getTag() == null)
    {
      HeaderViewHolder localHeaderViewHolder1 = new HeaderViewHolder(null);
      localHeaderViewHolder1.bucketHeader = paramViewGroup.findViewById(2131230815);
      localHeaderViewHolder1.searchResultsView = ((TextView)paramViewGroup.findViewById(2131230819));
      localHeaderViewHolder1.corpusStrip = paramViewGroup.findViewById(2131230820);
      localHeaderViewHolder1.sectionHeader = ((TextView)paramViewGroup.findViewById(2131230818));
      paramViewGroup.setTag(localHeaderViewHolder1);
    }
    HeaderViewHolder localHeaderViewHolder2 = (HeaderViewHolder)paramViewGroup.getTag();
    if (paramBucket.getChildCount() == 0)
      localHeaderViewHolder2.bucketHeader.setVisibility(8);
    label169: label191: label198: label344: label355: 
    while (true)
    {
      return;
      localHeaderViewHolder2.bucketHeader.setVisibility(0);
      View.OnClickListener localOnClickListener = makeHeaderClickListener(paramBucket);
      localHeaderViewHolder2.sectionHeader.setText(paramBucket.getTitle().toUpperCase());
      localHeaderViewHolder2.sectionHeader.setContentDescription(paramBucket.getTitle());
      boolean bool1;
      boolean bool2;
      if (paramBucket.hasMoreItems())
      {
        localHeaderViewHolder2.bucketHeader.setOnClickListener(localOnClickListener);
        View localView1 = localHeaderViewHolder2.bucketHeader;
        if (localOnClickListener != null)
        {
          bool1 = true;
          localView1.setClickable(bool1);
          View localView2 = localHeaderViewHolder2.bucketHeader;
          if (localOnClickListener == null)
            break label308;
          bool2 = true;
          localView2.setFocusable(bool2);
          int i = paramBucket.getEstimatedResults();
          if (i <= 0)
            break label344;
          String str = this.mContext.getResources().getQuantityText(2131558400, i).toString();
          TextView localTextView = localHeaderViewHolder2.searchResultsView;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(i);
          localTextView.setText(String.format(str, arrayOfObject));
          localHeaderViewHolder2.searchResultsView.setVisibility(0);
        }
      }
      while (true)
      {
        if (localHeaderViewHolder2.corpusStrip == null)
          break label355;
        localHeaderViewHolder2.corpusStrip.setBackgroundColor(CorpusResourceUtils.getBackendHintColor(this.mContext, paramBucket.getBackend()));
        break;
        bool1 = false;
        break label169;
        bool2 = false;
        break label191;
        localHeaderViewHolder2.bucketHeader.setOnClickListener(null);
        localHeaderViewHolder2.bucketHeader.setClickable(false);
        localHeaderViewHolder2.bucketHeader.setFocusable(false);
        break label198;
        localHeaderViewHolder2.searchResultsView.setVisibility(8);
      }
    }
  }

  private void bindDocument(Document paramDocument, ViewGroup paramViewGroup, boolean paramBoolean, int paramInt)
  {
    int i = 0;
    DocumentViewHolder localDocumentViewHolder = getConvertView(paramViewGroup);
    int j = paramDocument.getDocumentType();
    int k;
    if ((paramDocument.getBackend() == 2) && (paramDocument.getArtistDetails() != null))
    {
      k = 1;
      if ((j == 16) || (j == 17))
        i = 1;
      if ((k == 0) || (!this.mSwitchToCustomLayoutIfNecessary))
        break label77;
      bindArtistPromoLayer(paramDocument, localDocumentViewHolder);
    }
    while (true)
    {
      return;
      k = 0;
      break;
      label77: if ((i != 0) && (this.mSwitchToCustomLayoutIfNecessary))
        bindMagazinePromoLayer(paramDocument, localDocumentViewHolder, paramBoolean, paramInt);
      else
        bindGenericLayer(paramDocument, localDocumentViewHolder, paramBoolean, paramInt);
    }
  }

  private void bindGenericLayer(Document paramDocument, DocumentViewHolder paramDocumentViewHolder, boolean paramBoolean, int paramInt)
  {
    boolean bool = false;
    paramDocumentViewHolder.genericLayer.setVisibility(0);
    if (paramDocumentViewHolder.artistLayer != null)
    {
      paramDocumentViewHolder.artistLayer.setVisibility(8);
      paramDocumentViewHolder.artistLayer.setOnClickListener(null);
    }
    if (paramDocumentViewHolder.magazineLayer != null)
    {
      paramDocumentViewHolder.magazineLayer.setVisibility(8);
      paramDocumentViewHolder.magazineLayer.setOnClickListener(null);
    }
    paramDocumentViewHolder.genericLayer.bind(null, paramDocument, this.mLoader, false, this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
    paramDocumentViewHolder.genericLayer.setTitle(getDisplayTitle(paramDocument, paramBoolean, paramInt));
    paramDocumentViewHolder.genericLayer.setTitleColor(this.mTitleForeground);
    OverviewBucketEntry localOverviewBucketEntry = paramDocumentViewHolder.genericLayer;
    if (paramInt % this.mColumnCount < -1 + this.mColumnCount)
      bool = true;
    localOverviewBucketEntry.setRightSeparatorVisibility(bool);
  }

  private void bindMagazinePromoLayer(Document paramDocument, DocumentViewHolder paramDocumentViewHolder, boolean paramBoolean, int paramInt)
  {
    if (paramDocumentViewHolder.magazineStub != null)
    {
      paramDocumentViewHolder.magazineLayer = ((MagazineBucketEntry)paramDocumentViewHolder.magazineStub.inflate());
      paramDocumentViewHolder.magazineStub = null;
    }
    paramDocumentViewHolder.magazineLayer.setVisibility(0);
    if (paramDocumentViewHolder.genericLayer != null)
      paramDocumentViewHolder.genericLayer.setVisibility(8);
    if (paramDocumentViewHolder.artistLayer != null)
      paramDocumentViewHolder.artistLayer.setVisibility(8);
    paramDocumentViewHolder.magazineLayer.bind(null, paramDocument, this.mLoader, false, this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
    paramDocumentViewHolder.magazineLayer.setTitle(getDisplayTitle(paramDocument, paramBoolean, paramInt));
  }

  private void bindMockDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    DocumentViewHolder localDocumentViewHolder = getConvertView(paramViewGroup);
    localDocumentViewHolder.genericLayer.setVisibility(0);
    if (localDocumentViewHolder.artistLayer != null)
      localDocumentViewHolder.artistLayer.setVisibility(8);
    if (localDocumentViewHolder.magazineLayer != null)
      localDocumentViewHolder.magazineLayer.setVisibility(8);
    localDocumentViewHolder.genericLayer.setMockDocument(paramBucket.getBackend());
    localDocumentViewHolder.genericLayer.setTitleColor(this.mLoadingForeground);
    localDocumentViewHolder.genericLayer.setOnClickListener(null);
    localDocumentViewHolder.genericLayer.setClickable(false);
  }

  private void bindNoDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    DocumentViewHolder localDocumentViewHolder = getConvertView(paramViewGroup);
    localDocumentViewHolder.genericLayer.setVisibility(0);
    if (localDocumentViewHolder.artistLayer != null)
      localDocumentViewHolder.artistLayer.setVisibility(8);
    if (localDocumentViewHolder.magazineLayer != null)
      localDocumentViewHolder.magazineLayer.setVisibility(8);
    localDocumentViewHolder.genericLayer.setNoDocument();
    localDocumentViewHolder.genericLayer.setOnClickListener(null);
    localDocumentViewHolder.genericLayer.setClickable(false);
  }

  private View getBucketView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Bucket localBucket = (Bucket)this.mBucketedList.getBucketList().get(paramInt);
    if (paramView == null)
      paramView = inflateViewForBucket(localBucket, paramViewGroup);
    bindBucketEntries(localBucket, (ViewGroup)paramView);
    bindBucketHeader(localBucket, (ViewGroup)paramView);
    return paramView;
  }

  private int getBucketedListCount()
  {
    int i;
    if (this.mBucketedList.getBucketCount() == 1)
    {
      i = (int)Math.ceil(this.mBucketedList.getCount() / this.mColumnCount);
      if (((Bucket)this.mBucketedList.getBucketList().get(0)).hasContainerWithBannerTemplate())
        i++;
      if (getFooterMode() != PaginatedListAdapter.FooterMode.NONE)
        i++;
    }
    while (true)
    {
      return i;
      i = this.mBucketedList.getBucketCount();
    }
  }

  private int getBucketedListItemViewType(int paramInt)
  {
    int i = 0;
    if (paramInt == -1 + getBucketedListCount())
      switch (3.$SwitchMap$com$google$android$finsky$adapters$PaginatedListAdapter$FooterMode[getFooterMode().ordinal()])
      {
      default:
        throw new IllegalStateException("No footer or item at row " + paramInt);
      case 1:
        i = 2;
      case 2:
      case 3:
      }
    while (true)
    {
      return i;
      i = 3;
      continue;
      if (this.mBucketedList.getBucketCount() == 1)
      {
        i = 1;
        continue;
        if (this.mBucketedList.getBucketCount() == 1)
          if ((((Bucket)this.mBucketedList.getBucketList().get(0)).hasContainerWithBannerTemplate()) && (paramInt == 0))
            i = 4;
          else
            i = 1;
      }
    }
  }

  private DocumentViewHolder getConvertView(ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getTag() == null)
    {
      DocumentViewHolder localDocumentViewHolder = new DocumentViewHolder(null);
      localDocumentViewHolder.genericLayer = ((OverviewBucketEntry)paramViewGroup.findViewById(2131230750));
      localDocumentViewHolder.artistStub = ((ViewStub)paramViewGroup.findViewById(2131230850));
      localDocumentViewHolder.artistLayer = ((ArtistBucketEntry)paramViewGroup.findViewById(2131230741));
      localDocumentViewHolder.magazineStub = ((ViewStub)paramViewGroup.findViewById(2131230851));
      localDocumentViewHolder.magazineLayer = ((MagazineBucketEntry)paramViewGroup.findViewById(2131231082));
      paramViewGroup.setTag(localDocumentViewHolder);
    }
    return (DocumentViewHolder)paramViewGroup.getTag();
  }

  private String getDisplayTitle(Document paramDocument, boolean paramBoolean, int paramInt)
  {
    String str = paramDocument.getTitle();
    if (paramBoolean)
    {
      Resources localResources = this.mContext.getResources();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt + 1);
      arrayOfObject[1] = str;
      str = localResources.getString(2131165800, arrayOfObject);
    }
    return str;
  }

  private int getDisplayedColumnsForBucket(Bucket paramBucket, int paramInt)
  {
    if (paramInt < getDisplayedRowsForBucket(paramBucket));
    for (int i = Math.min(paramBucket.getChildCount() - paramInt * this.mColumnCount, this.mColumnCount); ; i = 0)
      return i;
  }

  private int getDisplayedRowsForBucket(Bucket paramBucket)
  {
    return Math.min((int)Math.ceil(paramBucket.getChildCount() / this.mColumnCount), this.mRowCount);
  }

  private View getHeaderView(View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = inflate(2130968619, paramViewGroup, false);
    View localView = paramView.findViewById(2131230852);
    final ImageView localImageView = (ImageView)paramView.findViewById(2131230853);
    DocAnnotations.ContainerWithBanner localContainerWithBanner = ((Bucket)this.mBucketedList.getBucketList().get(0)).getContainerWithBannerTemplate();
    Document localDocument = ((Bucket)this.mBucketedList.getBucketList().get(0)).getDocument();
    if (!TextUtils.isEmpty(localContainerWithBanner.getColorThemeArgb()))
      localView.setBackgroundColor(Color.parseColor(localContainerWithBanner.getColorThemeArgb()));
    String str = ThumbnailUtils.getPageHeaderBannerUrlFromDocument(localDocument, false, this.mSmallestWidth, this.mHeaderImageMaxHeight);
    if (!TextUtils.isEmpty(str))
      localImageView.setImageBitmap(this.mLoader.get(str, null, new BitmapLoader.BitmapLoadedHandler()
      {
        public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
        {
          localImageView.setImageBitmap(paramAnonymousBitmapContainer.getBitmap());
        }
      }
      , this.mSmallestWidth, this.mHeaderImageMaxHeight).getBitmap());
    return paramView;
  }

  private View getPaginatedRow(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = inflate(2130968609, paramViewGroup, false);
      for (int k = 0; k < this.mColumnCount; k++)
        ((ViewGroup)paramView).addView(inflate(this.mCellLayoutId, (ViewGroup)paramView, false));
    }
    for (int i = 0; i < this.mColumnCount; i++)
    {
      int j = i + paramInt * this.mColumnCount;
      bindRowEntry((Bucket)this.mBucketedList.getBucketList().get(0), (Document)getItem(j), j, (ViewGroup)((ViewGroup)paramView).getChildAt(i));
    }
    return paramView;
  }

  private int getPaginatedRowIndex(int paramInt)
  {
    int i = 0;
    if (hasHeader())
      i = 0 + 1;
    return paramInt - i;
  }

  private boolean hasHeader()
  {
    if ((this.mBucketedList.getBucketCount() == 1) && (((Bucket)this.mBucketedList.getBucketList().get(0)).hasContainerWithBannerTemplate()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private View inflateViewForBucket(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    View localView = inflate(2130968610, paramViewGroup, false);
    int i = getBucketHeaderLayoutId(paramBucket);
    ((ViewGroup)localView.findViewById(2131230823)).addView((LinearLayout)inflate(i, paramViewGroup, false));
    ViewGroup localViewGroup1 = (ViewGroup)localView.findViewById(2131230824);
    for (int j = 0; j < this.mRowCount; j++)
    {
      LinearLayout localLinearLayout = (LinearLayout)inflate(2130968609, null, false);
      for (int k = 0; k < this.mColumnCount; k++)
      {
        ViewGroup localViewGroup2 = (ViewGroup)inflate(this.mCellLayoutId, localLinearLayout, false);
        localViewGroup2.setVisibility(8);
        localLinearLayout.addView(localViewGroup2);
      }
      localViewGroup1.addView(localLinearLayout);
    }
    return localView;
  }

  protected void bindRowEntry(Bucket paramBucket, Document paramDocument, int paramInt, ViewGroup paramViewGroup)
  {
    if (paramDocument == null)
      if (paramInt < this.mBucketedList.getCount())
      {
        paramViewGroup.setVisibility(0);
        bindMockDocument(paramBucket, paramViewGroup);
      }
    while (true)
    {
      return;
      if (paramInt == this.mBucketedList.getCount());
      for (int i = 1; ; i = 0)
      {
        if (i == 0)
          break label70;
        paramViewGroup.setVisibility(0);
        bindNoDocument(paramBucket, paramViewGroup);
        break;
      }
      label70: paramViewGroup.setVisibility(4);
      continue;
      paramViewGroup.setVisibility(0);
      bindDocument(paramDocument, paramViewGroup, paramBucket.isOrdered(), paramInt);
    }
  }

  protected int getBucketHeaderLayoutId(Bucket paramBucket)
  {
    return 2130968606;
  }

  public int getCount()
  {
    return getBucketedListCount();
  }

  public Object getItem(int paramInt)
  {
    if (this.mBucketedList.getBucketCount() == 1);
    for (Object localObject = this.mBucketedList.getItem(paramInt); ; localObject = this.mBucketedList.getBucketList().get(paramInt))
      return localObject;
  }

  public int getItemViewType(int paramInt)
  {
    return getBucketedListItemViewType(paramInt);
  }

  protected String getLastRequestError()
  {
    return ErrorStrings.get(this.mContext, this.mBucketedList.getVolleyError());
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    switch (getBucketedListItemViewType(paramInt))
    {
    default:
      throw new IllegalStateException("Unknown type for getView " + getBucketedListItemViewType(paramInt));
    case 4:
      localView = getHeaderView(paramView, paramViewGroup);
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      return localView;
      localView = getBucketView(paramInt, paramView, paramViewGroup);
      continue;
      localView = getPaginatedRow(getPaginatedRowIndex(paramInt), paramView, paramViewGroup);
      continue;
      localView = getLoadingFooterView(paramView, paramViewGroup);
      continue;
      localView = getErrorFooterView(paramView, paramViewGroup);
    }
  }

  public int getViewTypeCount()
  {
    return 5;
  }

  protected void ignoreCustomTiles()
  {
    this.mSwitchToCustomLayoutIfNecessary = false;
  }

  protected boolean isMoreDataAvailable()
  {
    return this.mBucketedList.isMoreAvailable();
  }

  protected View.OnClickListener makeHeaderClickListener(final Bucket paramBucket)
  {
    if (!TextUtils.isEmpty(paramBucket.getBrowseUrl()));
    for (View.OnClickListener local2 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BucketedListAdapter.this.mNavigationManager.goBrowse(paramBucket.getBrowseUrl(), paramBucket.getTitle(), paramBucket.getBackend(), BucketedListAdapter.this.mCurrentPageUrl, BucketedListAdapter.this.mToc);
      }
    }
    ; ; local2 = null)
      return local2;
  }

  public void onDestroy()
  {
    this.mBucketedList.removeDataChangedListener(this);
  }

  public void onDestroyView()
  {
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
  }

  protected void retryLoadingItems()
  {
    this.mBucketedList.retryLoadItems();
  }

  public void updateAdapterData(BucketedList<?> paramBucketedList)
  {
    this.mBucketedList = paramBucketedList;
    notifyDataSetChanged();
  }

  private static class DocumentViewHolder
  {
    public ArtistBucketEntry artistLayer;
    public ViewStub artistStub;
    public OverviewBucketEntry genericLayer;
    public MagazineBucketEntry magazineLayer;
    public ViewStub magazineStub;
  }

  private static class HeaderViewHolder
  {
    public View bucketHeader;
    public View corpusStrip;
    public TextView searchResultsView;
    public TextView sectionHeader;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.BucketedListAdapter
 * JD-Core Version:    0.6.2
 */
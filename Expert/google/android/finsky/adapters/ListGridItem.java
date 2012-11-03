package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DecoratedTextView;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import java.util.List;

public class ListGridItem
  implements UnevenGridAdapter.UnevenGridItem
{
  private final BitmapLoader mBitmapLoader;
  private final Bucket mBucket;
  private final int mColumns;
  private final Context mContext;
  private final int mHeight;
  private int mMaxRowCount;
  private final NavigationManager mNavigationManager;
  private final String mReferrerUrl;
  private final int mRows;
  private final DfeToc mToc;
  private final int mWidth;

  private ListGridItem(Context paramContext, Document paramDocument, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, String paramString, DfeToc paramDfeToc)
  {
    this.mContext = paramContext;
    this.mBucket = Document.convertToBucket(paramDocument);
    this.mNavigationManager = paramNavigationManager;
    this.mToc = paramDfeToc;
    this.mBitmapLoader = paramBitmapLoader;
    this.mReferrerUrl = paramString;
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    this.mRows = paramInt3;
    this.mColumns = paramInt4;
  }

  public ListGridItem(Context paramContext, Document paramDocument, BitmapLoader paramBitmapLoader, NavigationManager paramNavigationManager, String paramString, DfeToc paramDfeToc)
  {
    this(paramContext, paramDocument, 4, 2, 2, 1, paramBitmapLoader, paramNavigationManager, paramString, paramDfeToc);
  }

  private void bindBucketEntry(Bucket paramBucket, int paramInt1, int paramInt2, ViewGroup paramViewGroup)
  {
    int i = 8;
    int j = paramInt2 + paramInt1 * this.mColumns;
    Document localDocument = (Document)paramBucket.getChildren().get(j);
    BucketEntryViewHolder localBucketEntryViewHolder = (BucketEntryViewHolder)paramViewGroup.getTag();
    if (localBucketEntryViewHolder == null)
    {
      localBucketEntryViewHolder = new BucketEntryViewHolder(null);
      localBucketEntryViewHolder.bitmapView = ((DocImageView)paramViewGroup.findViewById(2131230751));
      localBucketEntryViewHolder.title = ((TextView)paramViewGroup.findViewById(2131230754));
      localBucketEntryViewHolder.creator = ((DecoratedTextView)paramViewGroup.findViewById(2131230755));
      localBucketEntryViewHolder.reason = ((TextView)paramViewGroup.findViewById(2131231075));
      localBucketEntryViewHolder.bottomSeparator = paramViewGroup.findViewById(2131230983);
      localBucketEntryViewHolder.leftSeparator = paramViewGroup.findViewById(2131231073);
      localBucketEntryViewHolder.rightSeparator = paramViewGroup.findViewById(2131231074);
      localBucketEntryViewHolder.accessibilityOverlay = paramViewGroup.findViewById(2131230744);
      paramViewGroup.setTag(localBucketEntryViewHolder);
    }
    localBucketEntryViewHolder.accessibilityOverlay.setOnClickListener(this.mNavigationManager.getClickListener(localDocument, this.mReferrerUrl));
    String str = localDocument.getTitle();
    localBucketEntryViewHolder.title.setText(str);
    localBucketEntryViewHolder.creator.setVisibility(0);
    localBucketEntryViewHolder.creator.setText(localDocument.getCreator());
    localBucketEntryViewHolder.reason.setText(localDocument.getRecommendationReason());
    BadgeUtils.configureCreatorBadge(localDocument, this.mBitmapLoader, localBucketEntryViewHolder.creator, -1);
    View localView1 = localBucketEntryViewHolder.bottomSeparator;
    int k;
    int m;
    label301: View localView3;
    if (paramInt1 == -1 + this.mMaxRowCount)
    {
      k = i;
      localView1.setVisibility(k);
      View localView2 = localBucketEntryViewHolder.leftSeparator;
      if (paramInt2 != 0)
        break label414;
      m = i;
      localView2.setVisibility(m);
      localView3 = localBucketEntryViewHolder.rightSeparator;
      if (paramInt2 != -1 + this.mColumns)
        break label420;
    }
    while (true)
    {
      localView3.setVisibility(i);
      int n = CorpusResourceUtils.getCorpusCellContentDescriptionResource(localDocument.getBackend());
      View localView4 = localBucketEntryViewHolder.accessibilityOverlay;
      Context localContext = this.mContext;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localDocument.getTitle();
      arrayOfObject[1] = localDocument.getCreator();
      localView4.setContentDescription(localContext.getString(n, arrayOfObject));
      localBucketEntryViewHolder.bitmapView.bind(localDocument, this.mBitmapLoader);
      return;
      k = 0;
      break;
      label414: m = 0;
      break label301;
      label420: i = 0;
    }
  }

  private int getDisplayedColumns(Bucket paramBucket, int paramInt)
  {
    if (paramInt < getDisplayedRows(paramBucket));
    for (int i = Math.min(paramBucket.getChildCount() - paramInt * this.mColumns, this.mColumns); ; i = 0)
      return i;
  }

  private int getDisplayedRows(Bucket paramBucket)
  {
    return Math.min((int)Math.ceil(paramBucket.getChildCount() / this.mColumns), this.mRows);
  }

  public void bind(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContext);
    LinearLayout localLinearLayout1 = (LinearLayout)paramViewGroup.findViewById(2131230815);
    localLinearLayout1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ListGridItem.this.mNavigationManager.goBrowse(ListGridItem.this.mBucket.getBrowseUrl(), ListGridItem.this.mBucket.getTitle(), ListGridItem.this.mBucket.getBackend(), ListGridItem.this.mReferrerUrl, ListGridItem.this.mToc);
      }
    });
    TextView localTextView = (TextView)paramViewGroup.findViewById(2131230818);
    localTextView.setText(this.mBucket.getTitle().toUpperCase());
    localTextView.setTextColor(CorpusResourceUtils.getBackendForegroundColor(this.mContext, this.mBucket.getBackend()));
    localLinearLayout1.setContentDescription(this.mBucket.getTitle());
    LinearLayout localLinearLayout2 = (LinearLayout)paramViewGroup.findViewById(2131230824);
    for (int i = 0; i < this.mRows; i++)
    {
      LinearLayout localLinearLayout3 = (LinearLayout)localLinearLayout2.getChildAt(i);
      this.mMaxRowCount = getDisplayedRows(this.mBucket);
      if (i < this.mMaxRowCount)
      {
        if (localLinearLayout3 == null)
        {
          localLinearLayout3 = (LinearLayout)localLayoutInflater.inflate(2130968609, localLinearLayout2, false);
          LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localLinearLayout3.getLayoutParams();
          localLayoutParams.height = 0;
          localLayoutParams.weight = 1.0F;
          localLinearLayout2.addView(localLinearLayout3);
        }
        localLinearLayout3.setVisibility(0);
        int j = getDisplayedColumns(this.mBucket, i);
        int k = 0;
        if (k < this.mColumns)
        {
          View localView = localLinearLayout3.getChildAt(k);
          if (localView == null)
          {
            localView = localLayoutInflater.inflate(2130968721, localLinearLayout3, false);
            localLinearLayout3.addView(localView);
          }
          if (k < j)
          {
            bindBucketEntry(this.mBucket, i, k, (ViewGroup)localView);
            localView.setVisibility(0);
          }
          while (true)
          {
            k++;
            break;
            localView.setVisibility(4);
          }
        }
      }
      else if (localLinearLayout3 != null)
      {
        localLinearLayout3.setVisibility(8);
      }
    }
  }

  public int getCellHeight()
  {
    return this.mHeight;
  }

  public int getCellWidth()
  {
    return this.mWidth;
  }

  public UnevenGridItemType getGridItemType()
  {
    return UnevenGridItemType.LIST_FOUR_BLOCK_4x2;
  }

  public int getLayoutId()
  {
    return 2130968720;
  }

  private static class BucketEntryViewHolder
  {
    public View accessibilityOverlay;
    public DocImageView bitmapView;
    public View bottomSeparator;
    public DecoratedTextView creator;
    public View leftSeparator;
    public TextView reason;
    public View rightSeparator;
    public TextView title;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.ListGridItem
 * JD-Core Version:    0.6.2
 */
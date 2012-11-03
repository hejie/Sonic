package com.google.android.finsky.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.RecommendationsBucketEntry;
import com.google.android.finsky.layout.RecommendationsBucketEntry.OnDismissalListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.Sets;
import java.util.Iterator;
import java.util.Set;

public class RecommendationsListAdapter extends BucketedListAdapter
  implements RecommendationsBucketEntry.OnDismissalListener
{
  private DfeApi mDfeApi;
  private Set<String> mDismissedDocIds = Sets.newHashSet();
  private ListView mListView;

  public RecommendationsListAdapter(Context paramContext, NavigationManager paramNavigationManager, DfeApi paramDfeApi, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc, BucketedList<?> paramBucketedList, String paramString, ListView paramListView)
  {
    super(paramContext, paramNavigationManager, paramBitmapLoader, paramDfeToc, paramBucketedList, paramContext.getResources().getInteger(2131492879), 1, 2130968819, paramString);
    this.mDfeApi = paramDfeApi;
    this.mListView = paramListView;
  }

  private void bindDocument(Document paramDocument, ViewGroup paramViewGroup, int paramInt)
  {
    boolean bool = false;
    RecommendationViewHolder localRecommendationViewHolder = getConvertView(paramViewGroup);
    localRecommendationViewHolder.overviewBucketEntry.setVisibility(0);
    localRecommendationViewHolder.overviewBucketEntry.bind(null, paramDocument, this.mLoader, false, null);
    localRecommendationViewHolder.overviewBucketEntry.configureDismissal(this.mDfeApi, this, this.mDismissedDocIds.contains(paramDocument.getDocId()));
    localRecommendationViewHolder.overviewBucketEntry.setTitle(paramDocument.getTitle());
    localRecommendationViewHolder.overviewBucketEntry.setTitleColor(this.mTitleForeground);
    RecommendationsBucketEntry localRecommendationsBucketEntry = localRecommendationViewHolder.overviewBucketEntry;
    if (paramInt % this.mColumnCount < -1 + this.mColumnCount)
      bool = true;
    localRecommendationsBucketEntry.setRightSeparatorVisibility(bool);
    localRecommendationViewHolder.overviewBucketEntry.setOnClickListener(this.mNavigationManager.getClickListener(paramDocument, this.mCurrentPageUrl));
  }

  private void bindMockDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    RecommendationViewHolder localRecommendationViewHolder = getConvertView(paramViewGroup);
    localRecommendationViewHolder.overviewBucketEntry.setVisibility(0);
    localRecommendationViewHolder.overviewBucketEntry.setMockDocument(paramBucket.getBackend());
    localRecommendationViewHolder.overviewBucketEntry.setTitleColor(this.mLoadingForeground);
    localRecommendationViewHolder.overviewBucketEntry.setOnClickListener(null);
    localRecommendationViewHolder.overviewBucketEntry.setClickable(false);
  }

  private void bindNoDocument(Bucket paramBucket, ViewGroup paramViewGroup)
  {
    RecommendationViewHolder localRecommendationViewHolder = getConvertView(paramViewGroup);
    localRecommendationViewHolder.overviewBucketEntry.setVisibility(0);
    localRecommendationViewHolder.overviewBucketEntry.setNoDocument();
    localRecommendationViewHolder.overviewBucketEntry.setOnClickListener(null);
    localRecommendationViewHolder.overviewBucketEntry.setClickable(false);
  }

  private void dismissRecommendationsBucketEntry(View paramView, String paramString)
  {
    if ((paramView instanceof RecommendationsBucketEntry))
    {
      RecommendationsBucketEntry localRecommendationsBucketEntry = (RecommendationsBucketEntry)paramView;
      if (paramString.equals(localRecommendationsBucketEntry.getCurrentDocId()))
        localRecommendationsBucketEntry.dismissCurrentEntry();
    }
  }

  private RecommendationViewHolder getConvertView(ViewGroup paramViewGroup)
  {
    if (paramViewGroup.getTag() == null)
    {
      RecommendationViewHolder localRecommendationViewHolder = new RecommendationViewHolder(null);
      localRecommendationViewHolder.overviewBucketEntry = ((RecommendationsBucketEntry)paramViewGroup.findViewById(2131231200));
      paramViewGroup.setTag(localRecommendationViewHolder);
    }
    return (RecommendationViewHolder)paramViewGroup.getTag();
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
      bindDocument(paramDocument, paramViewGroup, paramInt);
    }
  }

  public void onDestroy()
  {
    if (!this.mDismissedDocIds.isEmpty())
    {
      Iterator localIterator = this.mBucketedList.getListPageUrls().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        this.mDfeApi.invalidateListCache(str, true);
      }
      this.mBucketedList.flushAllPages();
    }
    super.onDestroy();
  }

  public void onSuccessfulDismissal(String paramString)
  {
    this.mDismissedDocIds.add(paramString);
    int i = this.mListView.getFirstVisiblePosition();
    int j = this.mListView.getChildCount();
    for (int k = 0; k < j; k++)
    {
      (k + i);
      View localView = this.mListView.getChildAt(k);
      if ((localView instanceof BucketRow))
      {
        BucketRow localBucketRow = (BucketRow)localView;
        for (int m = 0; m < localBucketRow.getChildCount(); m++)
          dismissRecommendationsBucketEntry(localBucketRow.getChildAt(m), paramString);
      }
      dismissRecommendationsBucketEntry(localView, paramString);
    }
  }

  private static class RecommendationViewHolder
  {
    public RecommendationsBucketEntry overviewBucketEntry;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.RecommendationsListAdapter
 * JD-Core Version:    0.6.2
 */
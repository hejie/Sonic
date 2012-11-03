package com.google.android.finsky.adapters;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class AggregatedAdapter<T extends BaseAdapter> extends BaseAdapter
{
  private T[] mAdapters;
  private boolean mCachedAllItemsEnabled = true;
  private int mCachedCount = 0;
  private boolean mCachedHasStableIds = true;
  private ArrayList<IndexTranslation> mCachedTranslations;
  private DataSetObserver mChildObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      AggregatedAdapter.access$002(AggregatedAdapter.this, true);
      AggregatedAdapter.this.mDataSetObservable.notifyChanged();
    }

    public void onInvalidated()
    {
      AggregatedAdapter.access$002(AggregatedAdapter.this, true);
      AggregatedAdapter.this.mDataSetObservable.notifyInvalidated();
    }
  };
  private DataSetObservable mDataSetObservable = new DataSetObservable();
  private boolean mDirty = true;

  public AggregatedAdapter(T[] paramArrayOfT)
  {
    this.mAdapters = paramArrayOfT;
    registerAsListener();
  }

  private void refreshCachedData()
  {
    while (true)
    {
      int k;
      try
      {
        boolean bool1 = this.mDirty;
        if (!bool1)
          return;
        int i = this.mAdapters.length;
        int j = 0;
        this.mCachedAllItemsEnabled = true;
        this.mCachedHasStableIds = true;
        this.mCachedTranslations = new ArrayList(i * 3);
        k = 0;
        if (k < i)
        {
          BaseAdapter localBaseAdapter = this.mAdapters[k];
          int m = localBaseAdapter.getCount();
          j += m;
          if ((this.mCachedAllItemsEnabled) && (localBaseAdapter.areAllItemsEnabled()))
          {
            bool2 = true;
            this.mCachedAllItemsEnabled = bool2;
            if ((!this.mCachedHasStableIds) || (!localBaseAdapter.hasStableIds()))
              break label198;
            bool3 = true;
            this.mCachedHasStableIds = bool3;
            int n = 0;
            if (n >= m)
              break label204;
            IndexTranslation localIndexTranslation = new IndexTranslation(localBaseAdapter, n, null);
            this.mCachedTranslations.add(localIndexTranslation);
            n++;
            continue;
          }
        }
        else
        {
          this.mCachedCount = j;
          this.mDirty = false;
          continue;
        }
      }
      finally
      {
      }
      boolean bool2 = false;
      continue;
      label198: boolean bool3 = false;
      continue;
      label204: k++;
    }
  }

  private void registerAsListener()
  {
    for (int i = 0; i < this.mAdapters.length; i++)
      this.mAdapters[i].registerDataSetObserver(this.mChildObserver);
  }

  private IndexTranslation translate(int paramInt)
  {
    refreshCachedData();
    return (IndexTranslation)this.mCachedTranslations.get(paramInt);
  }

  public boolean areAllItemsEnabled()
  {
    refreshCachedData();
    return this.mCachedAllItemsEnabled;
  }

  public T[] getAdapters()
  {
    return this.mAdapters;
  }

  public int getCount()
  {
    refreshCachedData();
    return this.mCachedCount;
  }

  public Object getItem(int paramInt)
  {
    IndexTranslation localIndexTranslation = translate(paramInt);
    return localIndexTranslation.targetAdapter.getItem(localIndexTranslation.translatedIndex);
  }

  public long getItemId(int paramInt)
  {
    IndexTranslation localIndexTranslation = translate(paramInt);
    return localIndexTranslation.targetAdapter.getItemId(localIndexTranslation.translatedIndex);
  }

  public int getItemViewType(int paramInt)
  {
    IndexTranslation localIndexTranslation = translate(paramInt);
    return localIndexTranslation.targetAdapter.getItemViewType(localIndexTranslation.translatedIndex);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    IndexTranslation localIndexTranslation = translate(paramInt);
    return localIndexTranslation.targetAdapter.getView(localIndexTranslation.translatedIndex, paramView, paramViewGroup);
  }

  public boolean hasStableIds()
  {
    refreshCachedData();
    return this.mCachedHasStableIds;
  }

  public boolean isEmpty()
  {
    refreshCachedData();
    if (this.mCachedCount == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isEnabled(int paramInt)
  {
    IndexTranslation localIndexTranslation = translate(paramInt);
    return localIndexTranslation.targetAdapter.isEnabled(localIndexTranslation.translatedIndex);
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mDataSetObservable.registerObserver(paramDataSetObserver);
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mDataSetObservable.unregisterObserver(paramDataSetObserver);
  }

  private static class IndexTranslation
  {
    private ListAdapter targetAdapter;
    private int translatedIndex;

    private IndexTranslation(ListAdapter paramListAdapter, int paramInt)
    {
      this.targetAdapter = paramListAdapter;
      this.translatedIndex = paramInt;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.AggregatedAdapter
 * JD-Core Version:    0.6.2
 */
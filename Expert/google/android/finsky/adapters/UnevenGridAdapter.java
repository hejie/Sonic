package com.google.android.finsky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.finsky.layout.CellBasedLayout.Item;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class UnevenGridAdapter extends BaseAdapter
{
  protected Context mContext;
  protected List<UnevenGridItem> mItems = Lists.newArrayList();
  protected LayoutInflater mLayoutInflater;
  private boolean mShowCorpusStrip = false;

  public UnevenGridAdapter(Context paramContext)
  {
    this.mLayoutInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.mContext = paramContext;
  }

  public int getCount()
  {
    return this.mItems.size();
  }

  public UnevenGridItem getItem(int paramInt)
  {
    return (UnevenGridItem)this.mItems.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    return ((UnevenGridItem)this.mItems.get(paramInt)).getGridItemType().ordinal();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    UnevenGridItem localUnevenGridItem = (UnevenGridItem)this.mItems.get(paramInt);
    Object localObject;
    if (localUnevenGridItem == null)
    {
      localObject = null;
      return localObject;
    }
    if (paramView == null);
    for (View localView = this.mLayoutInflater.inflate(localUnevenGridItem.getLayoutId(), paramViewGroup, false); ; localView = paramView)
    {
      localObject = (ViewGroup)localView;
      localUnevenGridItem.bind((ViewGroup)localObject, this.mShowCorpusStrip);
      break;
    }
  }

  public int getViewTypeCount()
  {
    return UnevenGridItemType.values().length;
  }

  public void onDestroy()
  {
    this.mItems.clear();
  }

  public void setData(List<UnevenGridItem> paramList)
  {
    this.mItems = Lists.newArrayList();
    this.mItems.addAll(paramList);
    notifyDataSetChanged();
  }

  public void setShowCorpusStrip(boolean paramBoolean)
  {
    this.mShowCorpusStrip = paramBoolean;
  }

  public static abstract interface UnevenGridItem extends CellBasedLayout.Item
  {
    public abstract void bind(ViewGroup paramViewGroup, boolean paramBoolean);

    public abstract UnevenGridItemType getGridItemType();

    public abstract int getLayoutId();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.UnevenGridAdapter
 * JD-Core Version:    0.6.2
 */
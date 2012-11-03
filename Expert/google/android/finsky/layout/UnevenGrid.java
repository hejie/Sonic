package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.google.android.finsky.adapters.UnevenGridAdapter;
import com.google.android.finsky.adapters.UnevenGridAdapter.UnevenGridItem;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.List;
import java.util.Map;

public class UnevenGrid extends ViewGroup
{
  private UnevenGridAdapter mAdapter;
  private int mCellHeight;
  private int mCellWidth;
  Map<Integer, Integer> mConvertViewTypeMap = Maps.newHashMap();
  private CellBasedLayout mGridData;
  private int mGutterSize;
  private boolean mHasTopGutter;
  private List<CellBasedLayout.Item> mItems = Lists.newArrayList();
  private final int mNumCellsWide;
  private UnevenGridAdapterObserver mObserver;
  private boolean mRebindNecessary = false;
  private Bitmap mShopperBitmap;
  private Rect mShopperBitmapRect;

  public UnevenGrid(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public UnevenGrid(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    Resources localResources = paramContext.getResources();
    this.mNumCellsWide = localResources.getInteger(2131492872);
    this.mGridData = new CellBasedLayout(this.mNumCellsWide);
    this.mGutterSize = localResources.getDimensionPixelSize(2131427383);
    this.mShopperBitmapRect = new Rect();
    this.mShopperBitmap = BitmapFactory.decodeResource(getResources(), 2130837524);
    setWillNotDraw(false);
  }

  private int measureChildren()
  {
    int i = 0;
    int j = this.mGutterSize / 2;
    int k = 0;
    if (k < this.mItems.size())
    {
      CellBasedLayout.Item localItem = (CellBasedLayout.Item)this.mItems.get(k);
      int n;
      if (this.mGridData.isOnLeftEdge(localItem))
      {
        n = 0;
        label54: if (!this.mGridData.isOnRightEdge(localItem))
          break label156;
      }
      label156: for (int i1 = 0; ; i1 = j)
      {
        getChildAt(k).measure(View.MeasureSpec.makeMeasureSpec(this.mCellWidth * localItem.getCellWidth() - (n + i1), 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCellHeight * localItem.getCellHeight() - this.mGutterSize, 1073741824));
        i = Math.max(this.mGridData.getTop(localItem) + localItem.getCellHeight(), i);
        k++;
        break;
        n = j;
        break label54;
      }
    }
    int m = i * this.mCellHeight;
    if (!this.mHasTopGutter)
      m -= this.mGutterSize;
    return m;
  }

  private void rebindChildren()
  {
    this.mItems.clear();
    this.mGridData = new CellBasedLayout(this.mNumCellsWide);
    for (int i = 0; i < this.mAdapter.getCount(); i++)
    {
      View localView1 = getChildAt(i);
      if ((!this.mConvertViewTypeMap.containsKey(Integer.valueOf(i))) || (!((Integer)this.mConvertViewTypeMap.get(Integer.valueOf(i))).equals(Integer.valueOf(this.mAdapter.getItemViewType(i)))))
      {
        if (localView1 != null)
          removeViewInLayout(getChildAt(i));
        localView1 = null;
      }
      View localView2 = this.mAdapter.getView(i, localView1, this);
      UnevenGridAdapter.UnevenGridItem localUnevenGridItem = this.mAdapter.getItem(i);
      this.mGridData.addItem(localUnevenGridItem);
      this.mItems.add(localUnevenGridItem);
      if (localView2 != localView1)
      {
        if (localView1 != null)
          removeViewInLayout(getChildAt(i));
        addViewInLayout(localView2, i, localView2.getLayoutParams(), true);
        this.mConvertViewTypeMap.put(Integer.valueOf(i), Integer.valueOf(this.mAdapter.getItemViewType(i)));
      }
    }
    if (getChildCount() > this.mItems.size())
    {
      for (int j = this.mItems.size(); j < getChildCount(); j++)
        this.mConvertViewTypeMap.remove(Integer.valueOf(j));
      removeViewsInLayout(this.mItems.size(), getChildCount() - this.mItems.size());
    }
  }

  private void removeAllItems()
  {
    this.mGridData = new CellBasedLayout(this.mNumCellsWide);
    this.mItems.clear();
    this.mConvertViewTypeMap.clear();
    removeAllViewsInLayout();
  }

  private void resetAdapter()
  {
    if ((this.mAdapter != null) && (this.mObserver != null))
      this.mAdapter.unregisterDataSetObserver(this.mObserver);
  }

  public void onDestroyView()
  {
    this.mShopperBitmap.recycle();
    this.mShopperBitmap = null;
    this.mShopperBitmapRect = null;
    resetAdapter();
  }

  public void onDraw(Canvas paramCanvas)
  {
    if (this.mShopperBitmap != null)
    {
      int i = (int)(this.mCellHeight / this.mShopperBitmap.getHeight() * this.mShopperBitmap.getWidth());
      this.mShopperBitmapRect.set(getWidth() - i, getHeight() - this.mCellHeight, getWidth(), getHeight());
      paramCanvas.drawBitmap(this.mShopperBitmap, null, this.mShopperBitmapRect, null);
    }
    super.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt4 - paramInt2;
    if ((paramInt3 - paramInt1 <= 0) || (i <= 0))
      return;
    int j = this.mGutterSize / 2;
    int k = 0;
    label30: View localView;
    int m;
    int n;
    int i1;
    int i2;
    int i3;
    if (k < this.mItems.size())
    {
      CellBasedLayout.Item localItem = (CellBasedLayout.Item)this.mItems.get(k);
      localView = getChildAt(k);
      m = this.mGridData.getLeft(localItem) * this.mCellWidth;
      n = this.mGridData.getTop(localItem) * this.mCellHeight;
      i1 = m + localItem.getCellWidth() * this.mCellWidth;
      i2 = n + localItem.getCellHeight() * this.mCellHeight;
      if (!this.mGridData.isOnLeftEdge(localItem))
        break label219;
      i3 = 0;
      label149: if (!this.mGridData.isOnRightEdge(localItem))
        break label226;
    }
    label219: label226: for (int i4 = 0; ; i4 = j)
    {
      if (!this.mHasTopGutter)
      {
        n -= this.mGutterSize;
        i2 -= this.mGutterSize;
      }
      localView.layout(m + i3, n + this.mGutterSize, i1 - i4, i2);
      k++;
      break label30;
      break;
      i3 = j;
      break label149;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mRebindNecessary)
    {
      rebindChildren();
      this.mRebindNecessary = false;
    }
    if (getChildCount() == 0)
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    while (true)
    {
      return;
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
      this.mCellWidth = (getMeasuredWidth() / this.mNumCellsWide);
      this.mCellHeight = this.mCellWidth;
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), measureChildren());
    }
  }

  public void setAdapter(UnevenGridAdapter paramUnevenGridAdapter)
  {
    resetAdapter();
    removeAllItems();
    this.mAdapter = paramUnevenGridAdapter;
    if (this.mAdapter != null)
    {
      if (this.mObserver == null)
        this.mObserver = new UnevenGridAdapterObserver(null);
      this.mAdapter.registerDataSetObserver(this.mObserver);
      rebindChildren();
    }
    requestLayout();
  }

  public void setHasTopGutter(boolean paramBoolean)
  {
    this.mHasTopGutter = paramBoolean;
  }

  private class UnevenGridAdapterObserver extends DataSetObserver
  {
    private UnevenGridAdapterObserver()
    {
    }

    public void onChanged()
    {
      UnevenGrid.access$002(UnevenGrid.this, true);
      UnevenGrid.this.requestLayout();
    }

    public void onInvalidated()
    {
      onChanged();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.UnevenGrid
 * JD-Core Version:    0.6.2
 */
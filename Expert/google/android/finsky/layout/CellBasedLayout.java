package com.google.android.finsky.layout;

import android.util.Pair;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class CellBasedLayout
{
  private final Vector<int[]> mGrid = new Vector();
  private final List<Item> mItemList = Lists.newArrayList();
  private final Map<Item, Pair<Integer, Integer>> mItemPositionMap = Maps.newHashMap();
  private int mNextItemId = 1;
  private final int mWidthInCells;

  public CellBasedLayout(int paramInt)
  {
    this.mWidthInCells = paramInt;
  }

  private void allocateCells(int paramInt1, int paramInt2, Item paramItem, int paramInt3)
  {
    this.mItemPositionMap.put(paramItem, new Pair(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2)));
    for (int i = paramInt2; i < paramInt2 + paramItem.getCellHeight(); i++)
      for (int j = paramInt1; j < paramInt1 + paramItem.getCellWidth(); j++)
        setCellValue(j, i, paramInt3);
  }

  private void appendRows(int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
      this.mGrid.add(new int[getWidth()]);
  }

  private boolean checkVerticalRunsOfEmptyCells(int paramInt1, int paramInt2, Item paramItem)
  {
    int i = paramInt1;
    int j;
    if (i < paramInt1 + paramItem.getCellWidth())
    {
      j = paramInt2;
      label19: if ((j < paramInt2 + paramItem.getCellHeight()) && (j < getHeight()))
        if (getCellValue(i, j) == 0);
    }
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      j++;
      break label19;
      i++;
      break;
    }
  }

  private int findHorizontalRunOfEmptyCells(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 0;
    if (j < getWidth())
    {
      if (getCellValue(j, paramInt1) != 0)
        i = 0;
      do
      {
        j++;
        break;
        i++;
      }
      while (i < paramInt2);
    }
    for (int k = j + 1 - paramInt2; ; k = -1)
      return k;
  }

  private void fitItem(Item paramItem, int paramInt)
  {
    int i = -1;
    for (int j = 0; ; j++)
      if (j < getHeight())
      {
        i = findHorizontalRunOfEmptyCells(j, paramItem.getCellWidth());
        if ((i < 0) || (!checkVerticalRunsOfEmptyCells(i, j, paramItem)));
      }
      else
      {
        if (i == -1)
          i = 0;
        appendRows(j + paramItem.getCellHeight() - getHeight());
        allocateCells(i, j, paramItem, paramInt);
        return;
      }
  }

  private int getCellValue(int paramInt1, int paramInt2)
  {
    return ((int[])this.mGrid.get(paramInt2))[paramInt1];
  }

  private int getHeight()
  {
    return this.mGrid.size();
  }

  private int getWidth()
  {
    return this.mWidthInCells;
  }

  private void setCellValue(int paramInt1, int paramInt2, int paramInt3)
  {
    ((int[])this.mGrid.get(paramInt2))[paramInt1] = paramInt3;
  }

  public void addItem(Item paramItem)
  {
    if (paramItem.getCellWidth() > getWidth())
      throw new IllegalArgumentException("Item is too big for this grid");
    if ((paramItem.getCellHeight() <= 0) || (paramItem.getCellWidth() <= 0))
      throw new IllegalArgumentException("Item must be at least 1x1 cells");
    this.mItemList.add(paramItem);
    fitItem(paramItem, this.mNextItemId);
    this.mNextItemId = (1 + this.mNextItemId);
  }

  public int getLeft(Item paramItem)
  {
    return ((Integer)((Pair)this.mItemPositionMap.get(paramItem)).first).intValue();
  }

  public int getTop(Item paramItem)
  {
    return ((Integer)((Pair)this.mItemPositionMap.get(paramItem)).second).intValue();
  }

  public boolean isOnLeftEdge(Item paramItem)
  {
    if (getLeft(paramItem) == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isOnRightEdge(Item paramItem)
  {
    if (getLeft(paramItem) + paramItem.getCellWidth() == this.mWidthInCells);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static abstract interface Item
  {
    public abstract int getCellHeight();

    public abstract int getCellWidth();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CellBasedLayout
 * JD-Core Version:    0.6.2
 */
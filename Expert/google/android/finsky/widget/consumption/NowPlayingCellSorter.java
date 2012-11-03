package com.google.android.finsky.widget.consumption;

import android.content.res.Resources;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NowPlayingCellSorter
{
  Map<String, Integer> mSequenceMapping = Maps.newHashMap();

  private String getCellDescriptor(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 + ":" + paramInt2 + ":" + paramInt3;
  }

  public int getSortedIndex(int paramInt1, int paramInt2, int paramInt3)
  {
    String str = getCellDescriptor(paramInt1, paramInt2, paramInt3);
    if (!this.mSequenceMapping.containsKey(str));
    for (int i = -1; ; i = ((Integer)this.mSequenceMapping.get(str)).intValue())
      return i;
  }

  public void sort(List<List<Block>> paramList, Resources paramResources)
  {
    ArrayList localArrayList = Lists.newArrayList();
    for (int i = 0; i < paramList.size(); i++)
    {
      List localList = (List)paramList.get(i);
      for (int k = 0; k < localList.size(); k++)
      {
        Block localBlock = (Block)localList.get(k);
        int m = localBlock.getNumImages();
        for (int n = 0; n < m; n++)
        {
          CellInformation localCellInformation2 = new CellInformation(i, k, n);
          localCellInformation2.cellAreaInPixels = (paramResources.getDimensionPixelSize(localBlock.getImageWidthRes(n)) * paramResources.getDimensionPixelSize(localBlock.getImageHeightRes(n)));
          localArrayList.add(localCellInformation2);
        }
      }
    }
    Collections.sort(localArrayList);
    for (int j = 0; j < localArrayList.size(); j++)
    {
      CellInformation localCellInformation1 = (CellInformation)localArrayList.get(j);
      this.mSequenceMapping.put(getCellDescriptor(localCellInformation1.rowIndexInWidget, localCellInformation1.blockIndexInRow, localCellInformation1.cellIndexInBlock), Integer.valueOf(j));
    }
  }

  private class CellInformation
    implements Comparable<CellInformation>
  {
    int blockIndexInRow;
    long cellAreaInPixels;
    int cellIndexInBlock;
    int rowIndexInWidget;

    public CellInformation(int paramInt1, int paramInt2, int arg4)
    {
      this.rowIndexInWidget = paramInt1;
      this.blockIndexInRow = paramInt2;
      int i;
      this.cellIndexInBlock = i;
    }

    public int compareTo(CellInformation paramCellInformation)
    {
      int i = 1;
      if (this.cellAreaInPixels != paramCellInformation.cellAreaInPixels)
        if (this.cellAreaInPixels >= paramCellInformation.cellAreaInPixels);
      while (true)
      {
        return i;
        i = -1;
        continue;
        if (this.rowIndexInWidget != paramCellInformation.rowIndexInWidget)
        {
          if (this.rowIndexInWidget <= paramCellInformation.rowIndexInWidget)
            i = -1;
        }
        else if (this.blockIndexInRow != paramCellInformation.blockIndexInRow)
        {
          if (this.blockIndexInRow <= paramCellInformation.blockIndexInRow)
            i = -1;
        }
        else if (this.cellIndexInBlock != paramCellInformation.cellIndexInBlock)
        {
          if (this.cellIndexInBlock <= paramCellInformation.cellIndexInBlock)
            i = -1;
        }
        else
          i = 0;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.NowPlayingCellSorter
 * JD-Core Version:    0.6.2
 */
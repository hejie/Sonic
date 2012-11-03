package com.google.android.finsky.widget.consumption;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.finsky.widget.WidgetUtils;

public class Block
{
  private int mChildHeightRes;
  private SparseArray<int[]> mChildResArray;
  private int mChildWidthRes;
  private int mHeightRes;
  private Block mLastInRowReplacement;
  private final int mLayoutId;
  private int mMaxRowStartCount;
  private int mNumImages;
  private boolean mSupportsMetadata;
  private int mWidthRes;

  public Block(int paramInt)
  {
    this.mLayoutId = paramInt;
    this.mNumImages = 1;
    this.mMaxRowStartCount = -1;
  }

  public int getHeight(Context paramContext)
  {
    return WidgetUtils.getDips(paramContext, this.mHeightRes);
  }

  public int getImageHeightRes(int paramInt)
  {
    if (this.mChildResArray != null);
    for (int i = ((int[])this.mChildResArray.get(paramInt))[1]; ; i = this.mChildHeightRes)
      return i;
  }

  public int getImageWidthRes(int paramInt)
  {
    if (this.mChildResArray != null);
    for (int i = ((int[])this.mChildResArray.get(paramInt))[0]; ; i = this.mChildWidthRes)
      return i;
  }

  public Block getLastOccurenceInRowReplacement()
  {
    return this.mLastInRowReplacement;
  }

  public int getLayoutId()
  {
    return this.mLayoutId;
  }

  public int getMaxRowStartCount()
  {
    return this.mMaxRowStartCount;
  }

  public int getNumImages()
  {
    return this.mNumImages;
  }

  public int getWidth(Context paramContext)
  {
    return WidgetUtils.getDips(paramContext, this.mWidthRes);
  }

  public boolean hasLastOccurenceInRowReplacement()
  {
    if (this.mLastInRowReplacement != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasMaxRowStartCount()
  {
    if (this.mMaxRowStartCount >= 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public int hashCode()
  {
    return 31 * getLayoutId() + getMaxRowStartCount();
  }

  public Block hosting(int paramInt)
  {
    this.mNumImages = paramInt;
    return this;
  }

  public Block hosting(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mNumImages = paramInt1;
    this.mChildWidthRes = paramInt2;
    this.mChildHeightRes = paramInt3;
    return this;
  }

  public Block limitRowStartTo(int paramInt)
  {
    this.mMaxRowStartCount = paramInt;
    return this;
  }

  public Block markToSupportMetadata()
  {
    this.mSupportsMetadata = true;
    return this;
  }

  public Block replaceLastOccurenceInRowWith(Block paramBlock)
  {
    this.mLastInRowReplacement = paramBlock;
    return this;
  }

  public Block sized(int paramInt1, int paramInt2)
  {
    this.mWidthRes = paramInt1;
    this.mHeightRes = paramInt2;
    this.mChildWidthRes = this.mWidthRes;
    this.mChildHeightRes = this.mHeightRes;
    return this;
  }

  public boolean supportsMetadata()
  {
    return this.mSupportsMetadata;
  }

  public Block withChild(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.mChildResArray == null)
      this.mChildResArray = new SparseArray(this.mNumImages);
    this.mChildResArray.put(paramInt1, new int[] { paramInt2, paramInt3 });
    return this;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.Block
 * JD-Core Version:    0.6.2
 */
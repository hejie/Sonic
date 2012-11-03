package com.google.android.finsky.api.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BucketedList<T> extends PaginatedList<T, Document>
{
  protected List<Bucket> mBuckets = new ArrayList();

  protected BucketedList(String paramString)
  {
    super(paramString);
  }

  protected BucketedList(String paramString, boolean paramBoolean)
  {
    super(paramString, paramBoolean);
  }

  protected BucketedList(List<PaginatedList.UrlOffsetPair> paramList, int paramInt, boolean paramBoolean)
  {
    super(paramList, paramInt, paramBoolean);
  }

  public int getBackendId()
  {
    return getBackendId(0);
  }

  public int getBackendId(int paramInt)
  {
    if (getBucketCount() == 0);
    while (true)
    {
      return paramInt;
      int i = -1;
      for (int j = 0; ; j++)
      {
        if (j >= this.mBuckets.size())
          break label69;
        int k = ((Bucket)this.mBuckets.get(j)).getBackend();
        if ((k == 0) || ((i != -1) && (i != k)))
          break;
        i = k;
      }
      label69: paramInt = i;
    }
  }

  public int getBucketCount()
  {
    return this.mBuckets.size();
  }

  public List<Bucket> getBucketList()
  {
    return this.mBuckets;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.BucketedList
 * JD-Core Version:    0.6.2
 */
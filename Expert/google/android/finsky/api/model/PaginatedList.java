package com.google.android.finsky.api.model;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class PaginatedList<T, D> extends DfeModel
  implements Response.Listener<T>
{
  protected final boolean mAutoLoadNextPage;
  private int mCurrentOffset;
  private Request<?> mCurrentRequest;
  private final List<D> mItems = new ArrayList();
  private int mLastPositionRequested;
  protected T mLastResponse;
  private boolean mMoreAvailable;
  protected List<UrlOffsetPair> mUrlOffsetList = new ArrayList();
  private int mWindowDistance = 12;

  protected PaginatedList(String paramString)
  {
    this(paramString, true);
  }

  protected PaginatedList(String paramString, boolean paramBoolean)
  {
    this.mUrlOffsetList.add(new UrlOffsetPair(0, paramString));
    this.mMoreAvailable = true;
    this.mAutoLoadNextPage = paramBoolean;
  }

  protected PaginatedList(List<UrlOffsetPair> paramList, int paramInt, boolean paramBoolean)
  {
    this(null, paramBoolean);
    this.mUrlOffsetList = paramList;
    for (int i = 0; i < paramInt; i++)
      this.mItems.add(null);
  }

  private void requestMoreItemsIfNoRequestExists(UrlOffsetPair paramUrlOffsetPair)
  {
    if (inErrorState());
    while (true)
    {
      return;
      if (this.mCurrentRequest != null)
      {
        if (!this.mCurrentRequest.getUrl().endsWith(paramUrlOffsetPair.url))
          this.mCurrentRequest.cancel();
      }
      else
      {
        this.mCurrentOffset = paramUrlOffsetPair.offset;
        this.mCurrentRequest = makeRequest(paramUrlOffsetPair.url);
      }
    }
  }

  public void clearTransientState()
  {
    this.mCurrentRequest = null;
  }

  public void flushAllPages()
  {
    for (int i = 0; i < this.mItems.size(); i++)
      this.mItems.set(i, null);
  }

  public void flushUnusedPages()
  {
    if (this.mLastPositionRequested < 0)
      return;
    int i = 0;
    label10: if (i < this.mItems.size())
      if ((i < -1 + (this.mLastPositionRequested - this.mWindowDistance)) || (i >= this.mLastPositionRequested + this.mWindowDistance))
        break label57;
    while (true)
    {
      i++;
      break label10;
      break;
      label57: this.mItems.set(i, null);
    }
  }

  public int getCount()
  {
    return this.mItems.size();
  }

  public final D getItem(int paramInt)
  {
    this.mLastPositionRequested = paramInt;
    Object localObject1 = null;
    if (paramInt < 0)
      throw new IllegalArgumentException("Can't return an item with a negative index: " + paramInt);
    if (paramInt < getCount())
    {
      localObject1 = this.mItems.get(paramInt);
      if ((this.mAutoLoadNextPage) && (this.mMoreAvailable) && (paramInt >= -4 + getCount()))
        requestMoreItemsIfNoRequestExists((UrlOffsetPair)this.mUrlOffsetList.get(-1 + this.mUrlOffsetList.size()));
      if (localObject1 == null)
      {
        Object localObject2 = null;
        Iterator localIterator = this.mUrlOffsetList.iterator();
        while (localIterator.hasNext())
        {
          UrlOffsetPair localUrlOffsetPair = (UrlOffsetPair)localIterator.next();
          if (localUrlOffsetPair.offset > paramInt)
            break;
          localObject2 = localUrlOffsetPair;
        }
        requestMoreItemsIfNoRequestExists(localObject2);
      }
    }
    return localObject1;
  }

  protected abstract List<D> getItemsFromResponse(T paramT);

  public Set<String> getListPageUrls()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.mUrlOffsetList.iterator();
    while (localIterator.hasNext())
      localHashSet.add(((UrlOffsetPair)localIterator.next()).url);
    return localHashSet;
  }

  protected abstract String getNextPageUrl(T paramT);

  public boolean isMoreAvailable()
  {
    return this.mMoreAvailable;
  }

  public boolean isReady()
  {
    if ((this.mLastResponse != null) || (this.mItems.size() > 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected abstract Request<?> makeRequest(String paramString);

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    clearTransientState();
    super.onErrorResponse(paramVolleyError);
  }

  public void onResponse(T paramT)
  {
    clearErrors();
    this.mLastResponse = paramT;
    int i = this.mItems.size();
    List localList = getItemsFromResponse(paramT);
    if (this.mCurrentOffset >= this.mItems.size())
    {
      this.mItems.addAll(localList);
      String str = getNextPageUrl(paramT);
      int k = 0;
      if ((!TextUtils.isEmpty(str)) && (this.mCurrentOffset == i))
        this.mUrlOffsetList.add(new UrlOffsetPair(this.mItems.size(), str));
      if ((this.mItems.size() == ((UrlOffsetPair)this.mUrlOffsetList.get(-1 + this.mUrlOffsetList.size())).offset) && (localList.size() > 0))
        k = 1;
      if ((k == 0) || (!this.mAutoLoadNextPage))
        break label271;
    }
    label271: for (boolean bool = true; ; bool = false)
    {
      this.mMoreAvailable = bool;
      clearTransientState();
      notifyDataSetChanged();
      return;
      int j = 0;
      label189: if (j < localList.size())
      {
        if (j + this.mCurrentOffset >= this.mItems.size())
          break label250;
        this.mItems.set(j + this.mCurrentOffset, localList.get(j));
      }
      while (true)
      {
        j++;
        break label189;
        break;
        label250: this.mItems.add(localList.get(j));
      }
    }
  }

  public void resetItems()
  {
    this.mMoreAvailable = true;
    this.mItems.clear();
    notifyDataSetChanged();
  }

  public void retryLoadItems()
  {
    if (inErrorState())
    {
      clearTransientState();
      clearErrors();
      Object localObject = null;
      if (this.mCurrentOffset != -1)
      {
        Iterator localIterator = this.mUrlOffsetList.iterator();
        while (localIterator.hasNext())
        {
          UrlOffsetPair localUrlOffsetPair = (UrlOffsetPair)localIterator.next();
          if (this.mCurrentOffset == localUrlOffsetPair.offset)
            localObject = localUrlOffsetPair;
        }
      }
      if (localObject == null)
        localObject = (UrlOffsetPair)this.mUrlOffsetList.get(-1 + this.mUrlOffsetList.size());
      requestMoreItemsIfNoRequestExists((UrlOffsetPair)localObject);
    }
  }

  public void setWindowDistance(int paramInt)
  {
    this.mWindowDistance = paramInt;
  }

  public void startLoadItems()
  {
    if ((this.mMoreAvailable) && (getCount() == 0))
    {
      clearErrors();
      requestMoreItemsIfNoRequestExists((UrlOffsetPair)this.mUrlOffsetList.get(0));
    }
  }

  protected static class UrlOffsetPair
  {
    public final int offset;
    public final String url;

    public UrlOffsetPair(int paramInt, String paramString)
    {
      this.offset = paramInt;
      this.url = paramString;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.PaginatedList
 * JD-Core Version:    0.6.2
 */
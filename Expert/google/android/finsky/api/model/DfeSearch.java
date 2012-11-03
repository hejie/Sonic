package com.google.android.finsky.api.model;

import android.net.Uri;
import com.android.volley.Request;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Containers.ContainerMetadata;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.remoting.protos.Search.RelatedSearch;
import com.google.android.finsky.remoting.protos.Search.SearchResponse;
import com.google.android.finsky.utils.DfeLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DfeSearch extends BucketedList<Search.SearchResponse>
{
  private final DfeApi mDfeApi;
  private final String mInitialUrl;
  private String mQuery;
  private String mSuggestedQuery;

  public DfeSearch(DfeApi paramDfeApi, String paramString1, String paramString2)
  {
    super(paramString2);
    this.mInitialUrl = paramString2;
    this.mDfeApi = paramDfeApi;
    this.mQuery = paramString1;
  }

  public int getBackendId()
  {
    int i = 0;
    if (isAggregateResult());
    while (true)
    {
      return i;
      Uri localUri = Uri.parse(getUrl());
      if (!localUri.isHierarchical())
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = getUrl();
        DfeLog.e("Non-hierarchical uri: %s", arrayOfObject);
      }
      else
      {
        String str = localUri.getQueryParameter("c");
        if (localUri != null)
          try
          {
            int j = super.getBackendId(Integer.parseInt(str));
            i = j;
          }
          catch (NumberFormatException localNumberFormatException)
          {
          }
        else
          i = super.getBackendId();
      }
    }
  }

  protected List<Document> getItemsFromResponse(Search.SearchResponse paramSearchResponse)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramSearchResponse.getDocList();
    if (localList.size() == 1)
    {
      this.mBuckets.clear();
      this.mBuckets.add(new Bucket((DocumentV2.DocV2)localList.get(0)));
      String str = ((DocumentV2.DocV2)localList.get(0)).getContainerMetadata().getAnalyticsCookie();
      Iterator localIterator2 = ((DocumentV2.DocV2)localList.get(0)).getChildList().iterator();
      while (localIterator2.hasNext())
        localArrayList.add(new Document((DocumentV2.DocV2)localIterator2.next(), str));
    }
    this.mBuckets.clear();
    Iterator localIterator1 = localList.iterator();
    while (localIterator1.hasNext())
    {
      DocumentV2.DocV2 localDocV2 = (DocumentV2.DocV2)localIterator1.next();
      this.mBuckets.add(new Bucket(localDocV2));
    }
    if (paramSearchResponse.hasSuggestedQuery())
      this.mSuggestedQuery = paramSearchResponse.getSuggestedQuery();
    return localArrayList;
  }

  protected String getNextPageUrl(Search.SearchResponse paramSearchResponse)
  {
    String str = null;
    if (paramSearchResponse.getDocCount() == 1)
    {
      DocumentV2.DocV2 localDocV2 = paramSearchResponse.getDoc(0);
      if (localDocV2.hasContainerMetadata())
        str = localDocV2.getContainerMetadata().getNextPageUrl();
    }
    return str;
  }

  public String getQuery()
  {
    return this.mQuery;
  }

  public List<Search.RelatedSearch> getRelatedSearches()
  {
    return ((Search.SearchResponse)this.mLastResponse).getRelatedSearchList();
  }

  public String getSuggestedQuery()
  {
    return this.mSuggestedQuery;
  }

  public String getUrl()
  {
    return this.mInitialUrl;
  }

  public boolean isAggregateResult()
  {
    return ((Search.SearchResponse)this.mLastResponse).getAggregateQuery();
  }

  protected Request<?> makeRequest(String paramString)
  {
    return this.mDfeApi.search(paramString, this, this);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeSearch
 * JD-Core Version:    0.6.2
 */
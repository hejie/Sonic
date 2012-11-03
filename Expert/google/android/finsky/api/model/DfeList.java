package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.Request;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Containers.ContainerMetadata;
import com.google.android.finsky.remoting.protos.DocList.ListResponse;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DfeList extends BucketedList<DocList.ListResponse>
  implements Parcelable
{
  public static Parcelable.Creator<DfeList> CREATOR = new Parcelable.Creator()
  {
    public DfeList createFromParcel(Parcel paramAnonymousParcel)
    {
      int i = 1;
      int k = paramAnonymousParcel.readInt();
      ArrayList localArrayList = new ArrayList();
      for (int m = 0; m < k; m++)
        localArrayList.add(new PaginatedList.UrlOffsetPair(paramAnonymousParcel.readInt(), paramAnonymousParcel.readString()));
      int n = paramAnonymousParcel.readInt();
      if (paramAnonymousParcel.readInt() == i);
      while (true)
      {
        return new DfeList(localArrayList, n, i, paramAnonymousParcel.readString(), null);
        int j = 0;
      }
    }

    public DfeList[] newArray(int paramAnonymousInt)
    {
      return new DfeList[paramAnonymousInt];
    }
  };
  private DfeApi mDfeApi;
  private String mFilteredDocId = null;
  private final String mInitialListUrl;

  public DfeList(DfeApi paramDfeApi, String paramString, boolean paramBoolean)
  {
    super(paramString, paramBoolean);
    this.mInitialListUrl = paramString;
    this.mDfeApi = paramDfeApi;
  }

  private DfeList(List<PaginatedList.UrlOffsetPair> paramList, int paramInt, boolean paramBoolean, String paramString)
  {
    super(paramList, paramInt, paramBoolean);
    this.mFilteredDocId = paramString;
    if (paramList.size() > 0)
      str = ((PaginatedList.UrlOffsetPair)paramList.get(0)).url;
    this.mInitialListUrl = str;
  }

  public int describeContents()
  {
    return 0;
  }

  public void filterDocId(String paramString)
  {
    this.mFilteredDocId = paramString;
  }

  public String getInitialUrl()
  {
    return this.mInitialListUrl;
  }

  protected List<Document> getItemsFromResponse(DocList.ListResponse paramListResponse)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramListResponse.getDocCount() == 1)
    {
      DocumentV2.DocV2 localDocV22 = paramListResponse.getDoc(0);
      if (localDocV22.hasContainerMetadata());
      for (String str = localDocV22.getContainerMetadata().getAnalyticsCookie(); ; str = null)
      {
        Iterator localIterator2 = localDocV22.getChildList().iterator();
        while (localIterator2.hasNext())
        {
          Document localDocument = new Document((DocumentV2.DocV2)localIterator2.next(), str);
          if ((TextUtils.isEmpty(this.mFilteredDocId)) || (!localDocument.getDocId().equals(this.mFilteredDocId)))
            localArrayList.add(localDocument);
        }
      }
      this.mBuckets.clear();
      this.mBuckets.add(new Bucket(paramListResponse.getDoc(0)));
    }
    while (true)
    {
      return localArrayList;
      this.mBuckets.clear();
      Iterator localIterator1 = paramListResponse.getDocList().iterator();
      while (localIterator1.hasNext())
      {
        DocumentV2.DocV2 localDocV21 = (DocumentV2.DocV2)localIterator1.next();
        this.mBuckets.add(new Bucket(localDocV21));
      }
    }
  }

  protected String getNextPageUrl(DocList.ListResponse paramListResponse)
  {
    String str = null;
    if (paramListResponse.getDocCount() == 1)
    {
      DocumentV2.DocV2 localDocV2 = paramListResponse.getDoc(0);
      if (localDocV2.hasContainerMetadata())
        str = localDocV2.getContainerMetadata().getNextPageUrl();
    }
    return str;
  }

  protected Request<?> makeRequest(String paramString)
  {
    return this.mDfeApi.getList(paramString, this, this);
  }

  public void setDfeApi(DfeApi paramDfeApi)
  {
    this.mDfeApi = paramDfeApi;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mUrlOffsetList.size());
    Iterator localIterator = this.mUrlOffsetList.iterator();
    while (localIterator.hasNext())
    {
      PaginatedList.UrlOffsetPair localUrlOffsetPair = (PaginatedList.UrlOffsetPair)localIterator.next();
      paramParcel.writeInt(localUrlOffsetPair.offset);
      paramParcel.writeString(localUrlOffsetPair.url);
    }
    paramParcel.writeInt(getCount());
    if (this.mAutoLoadNextPage);
    for (int i = 1; ; i = 0)
    {
      paramParcel.writeInt(i);
      paramParcel.writeString(this.mFilteredDocId);
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeList
 * JD-Core Version:    0.6.2
 */
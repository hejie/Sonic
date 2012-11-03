package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Browse.BrowseLink;
import com.google.android.finsky.remoting.protos.Browse.BrowseResponse;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.List;

public class DfeBrowse extends DfeModel
  implements Response.Listener<Browse.BrowseResponse>, Parcelable
{
  public static Parcelable.Creator<DfeBrowse> CREATOR = new Parcelable.Creator()
  {
    public DfeBrowse createFromParcel(Parcel paramAnonymousParcel)
    {
      return new DfeBrowse((Browse.BrowseResponse)ParcelableProto.getProtoFromParcel(paramAnonymousParcel, ParcelableProto.class.getClassLoader()));
    }

    public DfeBrowse[] newArray(int paramAnonymousInt)
    {
      return new DfeBrowse[paramAnonymousInt];
    }
  };
  private Browse.BrowseResponse mBrowseResponse;

  public DfeBrowse(DfeApi paramDfeApi, String paramString)
  {
    paramDfeApi.getBrowseLayout(paramString, this, this);
  }

  public DfeBrowse(Browse.BrowseResponse paramBrowseResponse)
  {
    this.mBrowseResponse = paramBrowseResponse;
  }

  public DfeList buildContentList(DfeApi paramDfeApi)
  {
    if ((!isReady()) || (TextUtils.isEmpty(this.mBrowseResponse.getContentsUrl())));
    for (DfeList localDfeList = null; ; localDfeList = new DfeList(paramDfeApi, this.mBrowseResponse.getContentsUrl(), true))
      return localDfeList;
  }

  public DfeList buildPromoList(DfeApi paramDfeApi)
  {
    if (!hasPromotionalItems());
    for (DfeList localDfeList = null; ; localDfeList = new DfeList(paramDfeApi, this.mBrowseResponse.getPromoUrl(), false))
      return localDfeList;
  }

  public int describeContents()
  {
    return 0;
  }

  public List<Browse.BrowseLink> getBreadcrumbList()
  {
    if (this.mBrowseResponse != null);
    for (List localList = this.mBrowseResponse.getBreadcrumbList(); ; localList = null)
      return localList;
  }

  public List<Browse.BrowseLink> getCategoryList()
  {
    if (this.mBrowseResponse != null);
    for (List localList = this.mBrowseResponse.getCategoryList(); ; localList = null)
      return localList;
  }

  public boolean hasCategories()
  {
    if ((this.mBrowseResponse != null) && (this.mBrowseResponse.getCategoryList() != null) && (!this.mBrowseResponse.getCategoryList().isEmpty()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasPromotionalItems()
  {
    if ((this.mBrowseResponse != null) && (!TextUtils.isEmpty(this.mBrowseResponse.getPromoUrl())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isReady()
  {
    if (this.mBrowseResponse != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onResponse(Browse.BrowseResponse paramBrowseResponse)
  {
    clearErrors();
    this.mBrowseResponse = paramBrowseResponse;
    notifyDataSetChanged();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(ParcelableProto.forProto(this.mBrowseResponse), 0);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeBrowse
 * JD-Core Version:    0.6.2
 */
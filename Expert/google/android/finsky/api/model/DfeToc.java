package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.remoting.protos.Toc.TocResponse;
import com.google.android.finsky.remoting.protos.Toc.UserSettings;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DfeToc extends DfeModel
  implements Parcelable
{
  public static Parcelable.Creator<DfeToc> CREATOR = new Parcelable.Creator()
  {
    public DfeToc createFromParcel(Parcel paramAnonymousParcel)
    {
      return new DfeToc((Toc.TocResponse)ParcelableProto.getProtoFromParcel(paramAnonymousParcel, ParcelableProto.class.getClassLoader()));
    }

    public DfeToc[] newArray(int paramAnonymousInt)
    {
      return new DfeToc[paramAnonymousInt];
    }
  };
  private final Map<Integer, Toc.CorpusMetadata> mCorpusMap = new LinkedHashMap();
  private int mSelectedBackendId = 3;
  private final Toc.TocResponse mToc;

  public DfeToc(Toc.TocResponse paramTocResponse)
  {
    this.mToc = paramTocResponse;
    Iterator localIterator = this.mToc.getCorpusList().iterator();
    while (localIterator.hasNext())
    {
      Toc.CorpusMetadata localCorpusMetadata = (Toc.CorpusMetadata)localIterator.next();
      this.mCorpusMap.put(Integer.valueOf(localCorpusMetadata.getBackend()), localCorpusMetadata);
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public Toc.CorpusMetadata getCorpus(int paramInt)
  {
    return (Toc.CorpusMetadata)this.mCorpusMap.get(Integer.valueOf(paramInt));
  }

  public List<Toc.CorpusMetadata> getCorpusList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.mCorpusMap.values());
    return localArrayList;
  }

  public String getHomeUrl()
  {
    return this.mToc.getHomeUrl();
  }

  public String getIconOverrideUrl()
  {
    return this.mToc.getIconOverrideUrl();
  }

  public String getTosCheckboxTextMarketingEmails()
  {
    return this.mToc.getTosCheckboxTextMarketingEmails();
  }

  public String getTosContent()
  {
    return this.mToc.getTosContent();
  }

  public String getTosToken()
  {
    return this.mToc.getTosToken();
  }

  public boolean hasCurrentUserPreviouslyOptedIn()
  {
    return this.mToc.getUserSettings().getTosCheckboxMarketingEmailsOptedIn();
  }

  public boolean hasIconOverrideUrl()
  {
    return this.mToc.hasIconOverrideUrl();
  }

  public boolean isReady()
  {
    return true;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(ParcelableProto.forProto(this.mToc), 0);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeToc
 * JD-Core Version:    0.6.2
 */
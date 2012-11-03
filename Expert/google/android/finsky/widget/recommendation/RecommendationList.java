package com.google.android.finsky.widget.recommendation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.utils.ParcelUtils.CacheVersionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RecommendationList
  implements Collection<Recommendation>, Parcelable
{
  public static final Parcelable.Creator<RecommendationList> CREATOR = new Parcelable.Creator()
  {
    public RecommendationList createFromParcel(Parcel paramAnonymousParcel)
    {
      RecommendationList.class.getClassLoader();
      long l = paramAnonymousParcel.readLong();
      if (1L != l)
        throw new ParcelUtils.CacheVersionException(RecommendationList.class, 1L, l);
      String str = paramAnonymousParcel.readString();
      int i = paramAnonymousParcel.readInt();
      ArrayList localArrayList = Lists.newArrayList();
      paramAnonymousParcel.readTypedList(localArrayList, Recommendation.CREATOR);
      RecommendationList localRecommendationList = new RecommendationList(str, i);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        localRecommendationList.add((Recommendation)localIterator.next());
      return localRecommendationList;
    }

    public RecommendationList[] newArray(int paramAnonymousInt)
    {
      return new RecommendationList[paramAnonymousInt];
    }
  };
  private final int mBackendId;
  private final List<Recommendation> mRecommendations = Lists.newArrayList();
  private String mTitle;

  public RecommendationList(String paramString, int paramInt)
  {
    this.mTitle = paramString;
    this.mBackendId = paramInt;
  }

  public boolean add(Recommendation paramRecommendation)
  {
    return this.mRecommendations.add(paramRecommendation);
  }

  public boolean addAll(Collection<? extends Recommendation> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    Recommendation localRecommendation;
    do
    {
      if (!localIterator.hasNext())
        break;
      localRecommendation = (Recommendation)localIterator.next();
    }
    while ((contains(localRecommendation)) || (add(localRecommendation)));
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  public void clear()
  {
    this.mRecommendations.clear();
  }

  public boolean contains(Object paramObject)
  {
    return this.mRecommendations.contains(paramObject);
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    return this.mRecommendations.containsAll(paramCollection);
  }

  public int describeContents()
  {
    return 0;
  }

  public Recommendation get(int paramInt)
  {
    return (Recommendation)this.mRecommendations.get(paramInt);
  }

  public int getBackendId()
  {
    return this.mBackendId;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public boolean isEmpty()
  {
    return this.mRecommendations.isEmpty();
  }

  public Iterator<Recommendation> iterator()
  {
    return this.mRecommendations.iterator();
  }

  public int merge(RecommendationList paramRecommendationList)
  {
    int i = size();
    this.mTitle = paramRecommendationList.getTitle();
    addAll(paramRecommendationList);
    return size() - i;
  }

  public boolean needsBackfill()
  {
    if (size() < ((Integer)G.minimumNumberOfRecs.get()).intValue());
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean remove(Object paramObject)
  {
    return this.mRecommendations.remove(paramObject);
  }

  public boolean remove(String paramString)
  {
    Iterator localIterator = this.mRecommendations.iterator();
    Recommendation localRecommendation;
    do
    {
      if (!localIterator.hasNext())
        break;
      localRecommendation = (Recommendation)localIterator.next();
    }
    while (!localRecommendation.getDocument().getDocId().equals(paramString));
    for (boolean bool = remove(localRecommendation); ; bool = false)
      return bool;
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    return this.mRecommendations.removeAll(paramCollection);
  }

  public int removeExpiredRecommendations()
  {
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = this.mRecommendations.iterator();
    while (localIterator.hasNext())
    {
      Recommendation localRecommendation = (Recommendation)localIterator.next();
      if (localRecommendation.isExpired())
        localArrayList.add(localRecommendation);
    }
    this.mRecommendations.removeAll(localArrayList);
    return localArrayList.size();
  }

  public boolean retainAll(Collection<?> paramCollection)
  {
    return this.mRecommendations.retainAll(paramCollection);
  }

  public int size()
  {
    return this.mRecommendations.size();
  }

  public Object[] toArray()
  {
    return toArray(new Recommendation[size()]);
  }

  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return this.mRecommendations.toArray(paramArrayOfT);
  }

  public String toString()
  {
    return "[" + this.mBackendId + ", " + this.mRecommendations + "]";
  }

  public void writeToDisk(Context paramContext)
  {
    File localFile = RecommendationsStore.getCacheFile(paramContext, this.mBackendId);
    try
    {
      ParcelUtils.writeToDisk(localFile, this);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(this.mBackendId);
        FinskyLog.e("Unable to cache recs for %d", arrayOfObject);
        localIOException.printStackTrace();
      }
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(1L);
    paramParcel.writeString(this.mTitle);
    paramParcel.writeInt(this.mBackendId);
    paramParcel.writeTypedList(this.mRecommendations);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.RecommendationList
 * JD-Core Version:    0.6.2
 */
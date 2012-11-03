package com.google.android.finsky.widget.consumption;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.services.ConsumptionAppDoc;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ConsumptionAppDocList extends ArrayList<ConsumptionAppDoc>
  implements Parcelable
{
  public static final Parcelable.Creator<ConsumptionAppDocList> CREATOR = new Parcelable.Creator()
  {
    public ConsumptionAppDocList createFromParcel(Parcel paramAnonymousParcel)
    {
      int i = paramAnonymousParcel.readInt();
      ArrayList localArrayList = Lists.newArrayList();
      paramAnonymousParcel.readTypedList(localArrayList, ConsumptionAppDoc.CREATOR);
      return new ConsumptionAppDocList(i, localArrayList);
    }

    public ConsumptionAppDocList[] newArray(int paramAnonymousInt)
    {
      return new ConsumptionAppDocList[paramAnonymousInt];
    }
  };
  public static final Comparator<ConsumptionAppDocList> NEWEST_FIRST = new Comparator()
  {
    public int compare(ConsumptionAppDocList paramAnonymousConsumptionAppDocList1, ConsumptionAppDocList paramAnonymousConsumptionAppDocList2)
    {
      if ((paramAnonymousConsumptionAppDocList1.isEmpty()) || (paramAnonymousConsumptionAppDocList2.isEmpty()));
      for (int i = paramAnonymousConsumptionAppDocList2.size() - paramAnonymousConsumptionAppDocList1.size(); ; i = (int)(((ConsumptionAppDoc)paramAnonymousConsumptionAppDocList2.get(0)).getLastUpdateTimeMs() - ((ConsumptionAppDoc)paramAnonymousConsumptionAppDocList1.get(0)).getLastUpdateTimeMs()))
        return i;
    }
  };
  private static final long serialVersionUID = 3146031281074569458L;
  private final int mBackend;

  public ConsumptionAppDocList(int paramInt)
  {
    this.mBackend = paramInt;
  }

  public ConsumptionAppDocList(int paramInt, List<ConsumptionAppDoc> paramList)
  {
    this(paramInt);
    addAll(paramList);
  }

  public static ConsumptionAppDocList createFromBundles(int paramInt, List<Bundle> paramList)
  {
    ArrayList localArrayList = Lists.newArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localArrayList.add(new ConsumptionAppDoc((Bundle)localIterator.next()));
    return new ConsumptionAppDocList(paramInt, localArrayList);
  }

  public boolean add(ConsumptionAppDoc paramConsumptionAppDoc)
  {
    boolean bool = false;
    if (paramConsumptionAppDoc == null)
      FinskyLog.wtf("Not adding a null document", new Object[0]);
    while (true)
    {
      return bool;
      bool = super.add(paramConsumptionAppDoc);
    }
  }

  public boolean addAll(Collection<? extends ConsumptionAppDoc> paramCollection)
  {
    boolean bool = true;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
      bool &= add((ConsumptionAppDoc)localIterator.next());
    return bool;
  }

  public int describeContents()
  {
    return 0;
  }

  public int getBackend()
  {
    return this.mBackend;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mBackend);
    paramParcel.writeTypedList(this);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.ConsumptionAppDocList
 * JD-Core Version:    0.6.2
 */
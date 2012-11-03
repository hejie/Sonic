package com.google.android.finsky.navigationmanager;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class NavigationState
  implements Parcelable
{
  public static final Parcelable.Creator<NavigationState> CREATOR = new Parcelable.Creator()
  {
    public NavigationState createFromParcel(Parcel paramAnonymousParcel)
    {
      return new NavigationState(paramAnonymousParcel.readInt(), paramAnonymousParcel.readString(), null);
    }

    public NavigationState[] newArray(int paramAnonymousInt)
    {
      return new NavigationState[paramAnonymousInt];
    }
  };
  public final String backstackName;
  public final int pageType;

  public NavigationState(int paramInt)
  {
    this(paramInt, Integer.toString((int)(2147483646.0D * Math.random())));
  }

  private NavigationState(int paramInt, String paramString)
  {
    this.pageType = paramInt;
    this.backstackName = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.pageType);
    paramParcel.writeString(this.backstackName);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.navigationmanager.NavigationState
 * JD-Core Version:    0.6.2
 */
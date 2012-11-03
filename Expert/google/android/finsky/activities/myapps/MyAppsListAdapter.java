package com.google.android.finsky.activities.myapps;

import android.widget.Adapter;
import com.google.android.finsky.api.model.Document;

public abstract interface MyAppsListAdapter extends Adapter
{
  public abstract Document getDocument(int paramInt);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsListAdapter
 * JD-Core Version:    0.6.2
 */
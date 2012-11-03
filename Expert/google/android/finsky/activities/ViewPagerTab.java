package com.google.android.finsky.activities;

import android.os.Bundle;
import android.view.View;

public abstract interface ViewPagerTab
{
  public abstract View getView(int paramInt);

  public abstract void onDestroy();

  public abstract void onRestoreInstanceState(Bundle paramBundle);

  public abstract Bundle onSaveInstanceState();

  public abstract void setTabSelected(boolean paramBoolean);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ViewPagerTab
 * JD-Core Version:    0.6.2
 */
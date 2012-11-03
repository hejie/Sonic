package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.widget.recommendation.RecommendationList;
import com.google.android.finsky.widget.recommendation.RecommendationsStore;

public class FetchRecommendationsReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("backendId", -1);
    int j = paramIntent.getIntExtra("appWidgetId", 0);
    DfeApi localDfeApi = FinskyApp.get().getDfeApi();
    Libraries localLibraries = FinskyApp.get().getLibraries();
    RecommendationsStore.performBackFill(localDfeApi, paramContext, new RecommendationList("", i), localLibraries, j);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.FetchRecommendationsReceiver
 * JD-Core Version:    0.6.2
 */
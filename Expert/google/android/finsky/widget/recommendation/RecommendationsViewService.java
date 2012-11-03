package com.google.android.finsky.widget.recommendation;

import android.content.Intent;
import android.widget.RemoteViewsService;
import android.widget.RemoteViewsService.RemoteViewsFactory;

public class RecommendationsViewService extends RemoteViewsService
{
  public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent paramIntent)
  {
    return new RecommendationsViewFactory(this, paramIntent.getIntExtra("appWidgetId", 0));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.RecommendationsViewService
 * JD-Core Version:    0.6.2
 */
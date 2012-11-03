package com.google.android.finsky.widget.recommendation;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiImpl;
import com.google.android.finsky.services.LoadRecommendationsService;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.TrampolineActivity;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;

public class RecommendedWidgetProvider extends BaseWidgetProvider
{
  private static final int[] VIEW_IDS = { 2131231139, 2131231184, 2131231121, 2131231186, 2131231185, 2131231188 };

  private static RemoteViews getBaseWithVisibleViews(Context paramContext, int[] paramArrayOfInt)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968806);
    localRemoteViews.setImageViewResource(2131231124, 2130837673);
    localRemoteViews.setTextViewText(2131231125, "");
    int[] arrayOfInt = VIEW_IDS;
    int i = arrayOfInt.length;
    for (int j = 0; j < i; j++)
      localRemoteViews.setViewVisibility(arrayOfInt[j], 8);
    int k = paramArrayOfInt.length;
    for (int m = 0; m < k; m++)
      localRemoteViews.setViewVisibility(paramArrayOfInt[m], 0);
    return localRemoteViews;
  }

  private static RemoteViews getWidgetWithTitle(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    RemoteViews localRemoteViews = getBaseWithVisibleViews(paramContext, new int[] { 2131231184 });
    localRemoteViews.setEmptyView(2131231184, 2131231185);
    Intent localIntent = new Intent(paramContext, RecommendationsViewService.class);
    localIntent.setData(Uri.parse("content://market/factory/for/" + paramInt1));
    localIntent.putExtra("appWidgetId", paramInt1);
    localRemoteViews.setRemoteAdapter(2131231184, localIntent);
    if (!TextUtils.isEmpty(paramString))
    {
      localRemoteViews.setTextViewText(2131231125, paramString.toUpperCase());
      localRemoteViews.setOnClickPendingIntent(2131230744, PendingIntent.getActivity(paramContext, 0, IntentUtils.createBrowseIntent(paramContext, DfeApiImpl.getRecommendationsBrowseUrl(paramInt2), paramString, paramInt2, "widget", true), 0));
      localRemoteViews.setViewVisibility(2131230744, 0);
    }
    return localRemoteViews;
  }

  private static void showAccountsNeeded(Context paramContext, int paramInt)
  {
    showInteractiveError(paramContext, paramInt, 2131165443, getAddAccountIntent(paramContext));
  }

  private static void showConfigurationNeeded(Context paramContext, int paramInt)
  {
    showInteractiveError(paramContext, paramInt, 2131165772, TrampolineActivity.getLaunchIntent(paramContext, RecommendedTrampoline.class, paramInt));
  }

  public static void showData(Context paramContext, int paramInt1, String paramString, int paramInt2)
  {
    RemoteViews localRemoteViews = getWidgetWithTitle(paramContext, paramString, paramInt1, paramInt2);
    AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt1, localRemoteViews);
  }

  public static void showError(Context paramContext, int paramInt, String paramString)
  {
    RemoteViews localRemoteViews = getBaseWithVisibleViews(paramContext, new int[] { 2131231139 });
    localRemoteViews.setTextViewText(2131230788, paramString);
    AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt, localRemoteViews);
  }

  private static void showInteractiveError(Context paramContext, int paramInt1, int paramInt2, PendingIntent paramPendingIntent)
  {
    RemoteViews localRemoteViews = getBaseWithVisibleViews(paramContext, new int[] { 2131231186, 2131231121 });
    localRemoteViews.setTextViewText(2131231187, paramContext.getString(paramInt2));
    localRemoteViews.setOnClickPendingIntent(2131231121, paramPendingIntent);
    AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt1, localRemoteViews);
  }

  public void onAppWidgetOptionsChanged(Context paramContext, AppWidgetManager paramAppWidgetManager, int paramInt, Bundle paramBundle)
  {
    updateWidgets(paramContext, paramAppWidgetManager, new int[] { paramInt });
    paramAppWidgetManager.notifyAppWidgetViewDataChanged(paramInt, 2131231184);
  }

  protected void updateWidgets(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    DfeApi localDfeApi = FinskyApp.get().getDfeApi();
    int i = paramArrayOfInt.length;
    int j = 0;
    if (j < i)
    {
      int k = paramArrayOfInt[j];
      if (localDfeApi == null)
        showAccountsNeeded(paramContext, k);
      while (true)
      {
        j++;
        break;
        String str = WidgetTypeMap.get(paramContext).get(RecommendedWidgetProvider.class, k);
        if (str == null)
        {
          showConfigurationNeeded(paramContext, k);
        }
        else
        {
          int m = WidgetUtils.translate(str);
          paramAppWidgetManager.updateAppWidget(k, getBaseWithVisibleViews(paramContext, new int[] { 2131231188 }));
          LoadRecommendationsService.load(paramContext, k, m);
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider
 * JD-Core Version:    0.6.2
 */
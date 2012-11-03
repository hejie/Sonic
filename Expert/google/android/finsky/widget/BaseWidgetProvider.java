package com.google.android.finsky.widget;

import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;

public abstract class BaseWidgetProvider extends AppWidgetProvider
{
  protected static PendingIntent getAddAccountIntent(Context paramContext)
  {
    return PendingIntent.getActivity(paramContext, 0, AccountManager.newChooseAccountIntent(null, null, new String[] { "com.google" }, true, null, "androidmarket", null, AuthenticatedActivity.createAddAccountOptions(paramContext)), 0);
  }

  protected static int[] getBoundingBoxes(Context paramContext, int paramInt)
  {
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = WidgetUtils.getDips(paramContext, 2131427470);
    arrayOfInt[1] = WidgetUtils.getDips(paramContext, 2131427471);
    arrayOfInt[2] = WidgetUtils.getDips(paramContext, 2131427470);
    arrayOfInt[3] = WidgetUtils.getDips(paramContext, 2131427471);
    if (Build.VERSION.SDK_INT < 16);
    while (true)
    {
      return arrayOfInt;
      Bundle localBundle = AppWidgetManager.getInstance(paramContext).getAppWidgetOptions(paramInt);
      arrayOfInt[0] = localBundle.getInt("appWidgetMinWidth");
      arrayOfInt[1] = localBundle.getInt("appWidgetMinHeight");
      arrayOfInt[2] = localBundle.getInt("appWidgetMaxWidth");
      arrayOfInt[3] = localBundle.getInt("appWidgetMaxHeight");
    }
  }

  private void sendAnalytics(Context paramContext, Intent paramIntent)
  {
    int i = AppWidgetManager.getInstance(paramContext).getAppWidgetIds(getComponentName(paramContext)).length;
    Uri.Builder localBuilder = new Uri.Builder().path("widget").appendQueryParameter("class", getClass().getSimpleName()).appendQueryParameter("action", paramIntent.getAction()).appendQueryParameter("num", String.valueOf(i));
    if ("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS".equals(paramIntent.getAction()))
    {
      int j = paramIntent.getIntExtra("appWidgetId", 0);
      int[] arrayOfInt = getBoundingBoxes(paramContext, j);
      localBuilder.appendQueryParameter("widgetType", WidgetTypeMap.get(paramContext).get(BaseWidgetProvider.class, j));
      localBuilder.appendQueryParameter("appWidgetMinWidth", String.valueOf(arrayOfInt[0]));
      localBuilder.appendQueryParameter("appWidgetMinHeight", String.valueOf(arrayOfInt[1]));
      localBuilder.appendQueryParameter("appWidgetMaxWidth", String.valueOf(arrayOfInt[2]));
      localBuilder.appendQueryParameter("appWidgetMaxHeight", String.valueOf(arrayOfInt[3]));
      FinskyEventLog localFinskyEventLog2 = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject2 = new Object[14];
      arrayOfObject2[0] = "widgetClass";
      arrayOfObject2[1] = getClass().getSimpleName();
      arrayOfObject2[2] = "widgetAction";
      arrayOfObject2[3] = paramIntent.getAction();
      arrayOfObject2[4] = "widgetNum";
      arrayOfObject2[5] = String.valueOf(i);
      arrayOfObject2[6] = "widgetMinWidth";
      arrayOfObject2[7] = String.valueOf(arrayOfInt[0]);
      arrayOfObject2[8] = "widgetMinHeight";
      arrayOfObject2[9] = String.valueOf(arrayOfInt[1]);
      arrayOfObject2[10] = "widgetMaxWidth";
      arrayOfObject2[11] = String.valueOf(arrayOfInt[2]);
      arrayOfObject2[12] = "widgetMaxHeight";
      arrayOfObject2[13] = String.valueOf(arrayOfInt[3]);
      localFinskyEventLog2.logTag("widgetUpdate", arrayOfObject2);
    }
    while (true)
    {
      String str = localBuilder.build().toString();
      FinskyApp.get().getAnalytics().logPageView(null, null, str);
      return;
      FinskyEventLog localFinskyEventLog1 = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject1 = new Object[6];
      arrayOfObject1[0] = "widgetClass";
      arrayOfObject1[1] = getClass().getSimpleName();
      arrayOfObject1[2] = "widgetAction";
      arrayOfObject1[3] = paramIntent.getAction();
      arrayOfObject1[4] = "widgetNum";
      arrayOfObject1[5] = String.valueOf(i);
      localFinskyEventLog1.logTag("widgetUpdate", arrayOfObject1);
    }
  }

  public static void update(Context paramContext, Class<?> paramClass, int[] paramArrayOfInt)
  {
    Intent localIntent = new Intent(paramContext, paramClass);
    localIntent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
    localIntent.putExtra("appWidgetIds", paramArrayOfInt);
    paramContext.sendBroadcast(localIntent);
  }

  protected ComponentName getComponentName(Context paramContext)
  {
    return new ComponentName(paramContext, getClass());
  }

  protected void onDefaultConfiguration(Context paramContext, AppWidgetManager paramAppWidgetManager, Intent paramIntent, int paramInt)
  {
    WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
    String str = paramIntent.getStringExtra("type");
    localWidgetTypeMap.put(getClass(), paramInt, str);
    updateWidgets(paramContext, paramAppWidgetManager, new int[] { paramInt });
  }

  public void onDeleted(Context paramContext, int[] paramArrayOfInt)
  {
    WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfInt[j];
      localWidgetTypeMap.delete(getClass(), k);
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    super.onReceive(paramContext, paramIntent);
    AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(paramContext);
    String str = paramIntent.getAction();
    if (("android.appwidget.action.APPWIDGET_DELETED".equals(str)) || ("android.appwidget.action.APPWIDGET_DISABLED".equals(str)) || ("android.appwidget.action.APPWIDGET_UPDATE_OPTIONS".equals(str)) || ("android.appwidget.action.APPWIDGET_ENABLED".equals(str)))
      sendAnalytics(paramContext, paramIntent);
    if ("com.android.launcher.action.APPWIDGET_DEFAULT_WORKSPACE_CONFIGURE".equals(str))
    {
      int j = paramIntent.getIntExtra("appWidgetId", 0);
      onDefaultConfiguration(paramContext, AppWidgetManager.getInstance(paramContext), paramIntent, j);
    }
    if ("com.android.vending.action.APPWIDGET_UPDATE_CONSUMPTION_DATA".equals(str))
    {
      if (paramIntent.hasExtra("backendId"))
      {
        WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
        int i = paramIntent.getIntExtra("backendId", 0);
        updateWidgets(paramContext, localAppWidgetManager, localWidgetTypeMap.getWidgetsOfExactType(getClass(), WidgetUtils.translate(i)));
        updateWidgets(paramContext, localAppWidgetManager, localWidgetTypeMap.getWidgetsOfExactType(getClass(), "all"));
      }
    }
    else if (("android.appwidget.action.APPWIDGET_UPDATE".equals(paramIntent.getAction())) && (!paramIntent.hasExtra("appWidgetIds")))
    {
      if (!((Boolean)G.debugOptionsEnabled.get()).booleanValue())
        break label236;
      updateWidgets(paramContext, localAppWidgetManager, localAppWidgetManager.getAppWidgetIds(getComponentName(paramContext)));
    }
    while (true)
    {
      return;
      FinskyLog.wtf("No backend specified for update!", new Object[0]);
      break;
      label236: FinskyLog.e("Refusing to update all widgets; need to narrow scope", new Object[0]);
    }
  }

  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    updateWidgets(paramContext, paramAppWidgetManager, paramArrayOfInt);
  }

  protected abstract void updateWidgets(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.BaseWidgetProvider
 * JD-Core Version:    0.6.2
 */
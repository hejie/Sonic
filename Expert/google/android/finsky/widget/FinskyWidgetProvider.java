package com.google.android.finsky.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ThumbnailUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class FinskyWidgetProvider extends BaseWidgetProvider
{
  private static final HashMap<String, int[]> DOCUMENT_TYPES = new HashMap()
  {
  };
  private static final WidgetModel.ImageSelector mImageSelector = new WidgetModel.ImageSelector()
  {
    public int getImageType(Document paramAnonymousDocument)
    {
      return 2;
    }

    public String getImageUrl(Document paramAnonymousDocument, int paramAnonymousInt)
    {
      return ThumbnailUtils.getPromoBitmapUrlFromDocument(paramAnonymousDocument, 0, paramAnonymousInt);
    }
  };
  private final BitmapLoader mBitmapLoader = FinskyApp.get().getBitmapLoader();
  private final HashMap<String, String> mUrls = Maps.newHashMap();

  public FinskyWidgetProvider()
  {
    this.mUrls.put("apps", G.appsWidgetUrl.get());
    this.mUrls.put("books", G.booksWidgetUrl.get());
    this.mUrls.put("movies", G.moviesWidgetUrl.get());
    this.mUrls.put("music", G.musicWidgetUrl.get());
  }

  private RemoteViews buildView(Context paramContext, WidgetModel.PromotionalItem paramPromotionalItem)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968854);
    localRemoteViews.setTextViewText(2131231125, paramPromotionalItem.title);
    localRemoteViews.setTextViewText(2131231193, paramPromotionalItem.developer);
    localRemoteViews.setImageViewBitmap(2131231191, paramPromotionalItem.image);
    return localRemoteViews;
  }

  private PendingIntent buildViewIntent(Context paramContext, Document paramDocument)
  {
    Intent localIntent = IntentUtils.createViewDocumentIntent(paramContext, paramDocument);
    localIntent.addFlags(268435456);
    return PendingIntent.getActivity(paramContext, 0, localIntent, 134217728);
  }

  private void clearList(Context paramContext, int paramInt, WidgetTypeMap paramWidgetTypeMap, WidgetModel paramWidgetModel)
  {
    paramWidgetModel.reset();
    rebindWidget(paramContext, paramInt, paramWidgetTypeMap, paramWidgetModel);
  }

  private String getBackend(WidgetTypeMap paramWidgetTypeMap, int paramInt)
  {
    return paramWidgetTypeMap.get(getClass(), paramInt);
  }

  private String getDfeUrl(WidgetTypeMap paramWidgetTypeMap, int paramInt)
  {
    return (String)this.mUrls.get(paramWidgetTypeMap.get(getClass(), paramInt));
  }

  protected static PendingIntent getLaunchMarketIntent(Context paramContext)
  {
    return PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, MainActivity.class), 134217728);
  }

  private int[] getValidDocumentTypes(WidgetTypeMap paramWidgetTypeMap, int paramInt)
  {
    return (int[])DOCUMENT_TYPES.get(getBackend(paramWidgetTypeMap, paramInt));
  }

  private void rebindWidget(Context paramContext, int paramInt, WidgetTypeMap paramWidgetTypeMap, WidgetModel paramWidgetModel)
  {
    RemoteViews localRemoteViews1 = new RemoteViews(paramContext.getPackageName(), 2130968855);
    if (paramWidgetModel.getItems().isEmpty())
      showEmptyState(paramContext, localRemoteViews1);
    while (true)
    {
      AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt, localRemoteViews1);
      return;
      localRemoteViews1.removeAllViews(2131231184);
      localRemoteViews1.setViewVisibility(2131231281, 8);
      localRemoteViews1.setViewVisibility(2131231184, 0);
      Iterator localIterator = paramWidgetModel.getItems().iterator();
      while (localIterator.hasNext())
      {
        WidgetModel.PromotionalItem localPromotionalItem = (WidgetModel.PromotionalItem)localIterator.next();
        RemoteViews localRemoteViews2 = buildView(paramContext, localPromotionalItem);
        localRemoteViews2.setOnClickPendingIntent(2131231279, buildViewIntent(paramContext, localPromotionalItem.doc));
        localRemoteViews2.setImageViewResource(2131231280, 2130903044);
        localRemoteViews1.addView(2131231184, localRemoteViews2);
      }
    }
  }

  private void refreshList(final Context paramContext, final int paramInt, final WidgetTypeMap paramWidgetTypeMap, final WidgetModel paramWidgetModel)
  {
    DfeApi localDfeApi = FinskyApp.get().getDfeApi();
    String str = getDfeUrl(paramWidgetTypeMap, paramInt);
    if (str == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.d("%d has null URL", arrayOfObject);
      showError(paramContext, paramInt);
    }
    while (true)
    {
      return;
      paramWidgetModel.refresh(paramContext, localDfeApi, str, new WidgetModel.RefreshListener()
      {
        public void onData()
        {
          FinskyWidgetProvider.this.rebindWidget(paramContext, paramInt, paramWidgetTypeMap, paramWidgetModel);
        }

        public void onError(String paramAnonymousString)
        {
          FinskyLog.e("Failed to load list for widget! %s", new Object[] { paramAnonymousString });
          FinskyWidgetProvider.this.showError(paramContext, paramInt);
        }
      });
    }
  }

  private void showEmptyState(Context paramContext, RemoteViews paramRemoteViews)
  {
    paramRemoteViews.setImageViewResource(2131231282, 2130903044);
    paramRemoteViews.removeAllViews(2131231184);
    paramRemoteViews.setViewVisibility(2131231281, 0);
    paramRemoteViews.setViewVisibility(2131231184, 8);
    paramRemoteViews.setOnClickPendingIntent(2131231281, getLaunchMarketIntent(paramContext));
  }

  private void showError(Context paramContext, int paramInt)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), 2130968855);
    showEmptyState(paramContext, localRemoteViews);
    AppWidgetManager.getInstance(paramContext).updateAppWidget(paramInt, localRemoteViews);
  }

  protected void updateWidgets(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null)
      return;
    WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(paramContext);
    DfeApi localDfeApi = FinskyApp.get().getDfeApi();
    int i = paramArrayOfInt.length;
    int j = 0;
    label26: int k;
    WidgetModel localWidgetModel;
    if (j < i)
    {
      k = paramArrayOfInt[j];
      if (getBackend(localWidgetTypeMap, k) == null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(k);
        arrayOfObject[1] = "apps";
        FinskyLog.d("Defaulting %d to %s", arrayOfObject);
        localWidgetTypeMap.put(getClass(), k, "apps");
      }
      localWidgetModel = new WidgetModel(this.mBitmapLoader, getValidDocumentTypes(localWidgetTypeMap, k), mImageSelector, 2131427385, 10);
      clearList(paramContext, k, localWidgetTypeMap, localWidgetModel);
      if (localDfeApi != null)
        break label150;
      showError(paramContext, k);
    }
    while (true)
    {
      j++;
      break label26;
      break;
      label150: rebindWidget(paramContext, k, localWidgetTypeMap, localWidgetModel);
      refreshList(paramContext, k, localWidgetTypeMap, localWidgetModel);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.FinskyWidgetProvider
 * JD-Core Version:    0.6.2
 */
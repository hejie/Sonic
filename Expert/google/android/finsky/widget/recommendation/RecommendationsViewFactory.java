package com.google.android.finsky.widget.recommendation;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.services.DismissRecommendationService;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.AdvanceFlipperReceiver;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.WidgetTypeMap;
import com.google.android.finsky.widget.WidgetUtils;
import java.util.concurrent.Semaphore;

public class RecommendationsViewFactory
  implements RemoteViewsService.RemoteViewsFactory
{
  private final int mAppWidgetId;
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private final DfeApi mDfeApi;
  private RecommendationList mItems;
  private final Library mLibrary;
  private final int mMaxImageHeight;
  private final WidgetTypeMap mTypeMap;

  public RecommendationsViewFactory(Context paramContext, int paramInt)
  {
    this.mContext = paramContext;
    this.mAppWidgetId = paramInt;
    this.mDfeApi = FinskyApp.get().getDfeApi();
    this.mLibrary = FinskyApp.get().getLibraries();
    this.mMaxImageHeight = paramContext.getResources().getDimensionPixelSize(2131427433);
    this.mBitmapLoader = FinskyApp.get().getBitmapLoader();
    this.mTypeMap = WidgetTypeMap.get(paramContext);
  }

  private int getLastMeasuredWidth()
  {
    return AppWidgetManager.getInstance(this.mContext).getAppWidgetOptions(this.mAppWidgetId).getInt("appWidgetMinWidth");
  }

  private static int getLayoutRes(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 2130968812;
    int j = WidgetUtils.getDips(paramContext, 2131427484);
    switch (paramInt2)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Invalid backend: " + paramInt2);
    case 3:
      if (paramInt1 == 2)
        if (paramInt4 >= j)
          break;
      break;
    case 0:
    case 2:
    case 1:
    case 4:
    case 6:
    }
    while (true)
    {
      return i;
      i = 2130968817;
      continue;
      i = 2130968818;
      continue;
      switch (paramInt3)
      {
      case 3:
      case 5:
      default:
        switch (paramInt1)
        {
        default:
          i = 2130968818;
        case 2:
        }
        break;
      case 2:
        i = 2130968818;
        break;
      case 1:
      case 4:
      case 6:
        i = 2130968816;
        continue;
        if (paramInt4 >= j)
        {
          i = 2130968817;
          continue;
          i = 2130968818;
          continue;
          i = 2130968816;
        }
        break;
      }
    }
  }

  private RecommendationList getRecommendationItems()
  {
    return RecommendationsStore.getRecommendationsOrShowError(this.mContext, this.mDfeApi, getWidgetBackend(), this.mAppWidgetId, this.mLibrary);
  }

  private static int getStripRes(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Invalid backend");
    case 1:
      i = 2130837730;
    case 2:
    case 4:
    case 6:
    case 0:
    case 3:
    }
    while (true)
    {
      return i;
      i = 2130837734;
      continue;
      i = 2130837733;
      continue;
      i = 2130837732;
      continue;
      i = 2130837729;
    }
  }

  private int getWidgetBackend()
  {
    final Semaphore localSemaphore = new Semaphore(0);
    final int[] arrayOfInt = new int[1];
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        String str = RecommendationsViewFactory.this.mTypeMap.get(RecommendedWidgetProvider.class, RecommendationsViewFactory.this.mAppWidgetId);
        if (str == null)
          arrayOfInt[0] = -1;
        while (true)
        {
          localSemaphore.release();
          return;
          arrayOfInt[0] = WidgetUtils.translate(str);
        }
      }
    };
    if (Looper.getMainLooper() != Looper.myLooper())
    {
      new Handler(Looper.getMainLooper()).post(local1);
      localSemaphore.acquireUninterruptibly();
    }
    return arrayOfInt[0];
  }

  public static void notifyDataSetChanged(Context paramContext, int[] paramArrayOfInt)
  {
    AppWidgetManager localAppWidgetManager = AppWidgetManager.getInstance(paramContext);
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      paramArrayOfInt = localAppWidgetManager.getAppWidgetIds(new ComponentName(paramContext, RecommendedWidgetProvider.class));
    localAppWidgetManager.notifyAppWidgetViewDataChanged(paramArrayOfInt, 2131231184);
  }

  private static void populateItem(RemoteViews paramRemoteViews, Recommendation paramRecommendation, Bitmap paramBitmap)
  {
    Document localDocument = paramRecommendation.getDocument();
    paramRemoteViews.setTextViewText(2131231125, localDocument.getTitle());
    paramRemoteViews.setTextViewText(2131231193, localDocument.getCreator());
    paramRemoteViews.setTextViewText(2131231194, localDocument.getRecommendationReason());
    paramRemoteViews.setImageViewBitmap(2131231191, paramBitmap);
    paramRemoteViews.setImageViewResource(2131231189, getStripRes(localDocument.getBackend()));
  }

  private static void setIntents(Context paramContext, RemoteViews paramRemoteViews, Recommendation paramRecommendation, int paramInt1, int paramInt2, int paramInt3)
  {
    paramRemoteViews.setOnClickPendingIntent(2131231198, OpenRecommendationReceiver.getIntent(paramContext, paramRecommendation.getDocument(), paramInt3, paramInt2));
    paramRemoteViews.setOnClickPendingIntent(2131231199, AdvanceFlipperReceiver.getIntent(paramContext, paramInt2));
    paramRemoteViews.setOnClickPendingIntent(2131231192, DismissRecommendationService.getDismissPendingIntent(paramContext, paramInt2, paramRecommendation.getDocument(), paramInt3, paramInt1));
  }

  public int getCount()
  {
    if (this.mItems != null);
    for (int i = this.mItems.size(); ; i = 0)
      return i;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public RemoteViews getLoadingView()
  {
    return new RemoteViews(this.mContext.getPackageName(), 2130968813);
  }

  public RemoteViews getViewAt(int paramInt)
  {
    RemoteViews localRemoteViews = null;
    if (getWidgetBackend() == -1)
    {
      Context localContext = this.mContext;
      int[] arrayOfInt = new int[1];
      arrayOfInt[0] = this.mAppWidgetId;
      BaseWidgetProvider.update(localContext, RecommendedWidgetProvider.class, arrayOfInt);
    }
    while (true)
    {
      return localRemoteViews;
      if (this.mItems == null)
      {
        this.mItems = getRecommendationItems();
        if (this.mItems == null);
      }
      else if (paramInt >= this.mItems.size())
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        arrayOfObject[1] = Integer.valueOf(this.mItems.size());
        FinskyLog.d("Item out of bounds (pos = %d, size = %d)", arrayOfObject);
      }
      else
      {
        Recommendation localRecommendation = this.mItems.get(paramInt);
        Bitmap localBitmap = RecommendationsStore.getBitmap(this.mBitmapLoader, localRecommendation, this.mMaxImageHeight);
        int i = localRecommendation.getImageType();
        int j = getLastMeasuredWidth();
        int k = getLayoutRes(this.mContext, i, getWidgetBackend(), localRecommendation.getBackend(), j);
        localRemoteViews = new RemoteViews(this.mContext.getPackageName(), k);
        populateItem(localRemoteViews, localRecommendation, localBitmap);
        setIntents(this.mContext, localRemoteViews, localRecommendation, paramInt, this.mAppWidgetId, getWidgetBackend());
      }
    }
  }

  public int getViewTypeCount()
  {
    return 4;
  }

  public boolean hasStableIds()
  {
    return true;
  }

  public void onCreate()
  {
  }

  public void onDataSetChanged()
  {
    if (getWidgetBackend() == -1)
    {
      Context localContext = this.mContext;
      int[] arrayOfInt = new int[1];
      arrayOfInt[0] = this.mAppWidgetId;
      BaseWidgetProvider.update(localContext, RecommendedWidgetProvider.class, arrayOfInt);
    }
    while (true)
    {
      return;
      this.mItems = getRecommendationItems();
    }
  }

  public void onDestroy()
  {
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.RecommendationsViewFactory
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.widget.recommendation;

import android.content.Context;
import android.graphics.Bitmap;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocList.ListResponse;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RecommendationsStore
{
  private static final String CACHE_FILE_PREFIX = RecommendationList.class.getSimpleName();
  private static final ExecutorService sWriteThread = Executors.newSingleThreadExecutor();

  public static Bitmap getBitmap(BitmapLoader paramBitmapLoader, Recommendation paramRecommendation, int paramInt)
  {
    String str = paramRecommendation.getImageUrl(paramInt);
    final Semaphore localSemaphore = new Semaphore(0);
    Bitmap[] arrayOfBitmap = new Bitmap[1];
    BitmapLoader.BitmapContainer localBitmapContainer = paramBitmapLoader.get(str, null, new BitmapLoader.BitmapLoadedHandler()
    {
      public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
      {
        this.val$bitmap[0] = paramAnonymousBitmapContainer.getBitmap();
        localSemaphore.release();
      }
    }
    , 0, paramInt);
    if (localBitmapContainer.getBitmap() != null)
      arrayOfBitmap[0] = localBitmapContainer.getBitmap();
    while (true)
    {
      return arrayOfBitmap[0];
      try
      {
        if (!localSemaphore.tryAcquire(((Long)G.recommendationsFetchTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS))
          FinskyLog.e("Timed out while fetching %s", new Object[] { str });
      }
      catch (InterruptedException localInterruptedException)
      {
        FinskyLog.e("Interrupted while fetching %s", new Object[] { str });
      }
    }
  }

  public static File getCacheFile(Context paramContext, int paramInt)
  {
    File localFile = new File(paramContext.getCacheDir(), "recs");
    localFile.mkdirs();
    return new File(localFile, CACHE_FILE_PREFIX + "_" + paramInt + ".cache");
  }

  public static RecommendationList getRecommendations(Context paramContext, DfeApi paramDfeApi, int paramInt, Library paramLibrary)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    RecommendationList localRecommendationList = null;
    File localFile = getCacheFile(paramContext, paramInt);
    if (localFile.exists())
    {
      localRecommendationList = (RecommendationList)ParcelUtils.readFromDisk(localFile);
      if (localRecommendationList != null)
        localRecommendationList.removeExpiredRecommendations();
    }
    if ((localRecommendationList == null) || (localRecommendationList.isEmpty()))
      localRecommendationList = loadDocumentsFromNetwork(paramContext, paramDfeApi, paramInt, paramLibrary);
    try
    {
      ParcelUtils.writeToDisk(localFile, localRecommendationList);
      return localRecommendationList;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        FinskyLog.e("Unable to cache recs for %d", arrayOfObject);
        localIOException.printStackTrace();
      }
    }
  }

  public static RecommendationList getRecommendationsOrShowError(Context paramContext, DfeApi paramDfeApi, int paramInt1, int paramInt2, Library paramLibrary)
  {
    Utils.ensureNotOnMainThread();
    Object localObject1 = null;
    try
    {
      RecommendationList localRecommendationList = getRecommendations(paramContext, paramDfeApi, paramInt1, paramLibrary);
      localObject1 = localRecommendationList;
      return localObject1;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        localInterruptedException.printStackTrace();
        String str3 = paramContext.getString(2131165435);
        if (str3 != null)
          RecommendedWidgetProvider.showError(paramContext, paramInt2, str3);
      }
    }
    catch (ExecutionException localExecutionException)
    {
      while (true)
      {
        localExecutionException.printStackTrace();
        Throwable localThrowable = localExecutionException.getCause();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.getMessage();
        FinskyLog.e("Error loading recs widget: %s", arrayOfObject);
        String str2 = paramContext.getString(2131165435);
        if (str2 != null)
          RecommendedWidgetProvider.showError(paramContext, paramInt2, str2);
      }
    }
    catch (TimeoutException localTimeoutException)
    {
      while (true)
      {
        localTimeoutException.printStackTrace();
        String str1 = paramContext.getString(2131165436);
        if (str1 != null)
          RecommendedWidgetProvider.showError(paramContext, paramInt2, str1);
      }
    }
    finally
    {
      if (0 != 0)
        RecommendedWidgetProvider.showError(paramContext, paramInt2, null);
    }
  }

  private static RecommendationList loadDocumentsFromNetwork(Context paramContext, DfeApi paramDfeApi, int paramInt, Library paramLibrary)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    RequestFuture localRequestFuture = RequestFuture.newFuture();
    paramDfeApi.getRecommendations(paramInt, localRequestFuture, localRequestFuture);
    return parseNetworkResponse((DocList.ListResponse)localRequestFuture.get(((Long)G.recommendationsFetchTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS), paramInt, paramLibrary);
  }

  private static RecommendationList parseNetworkResponse(DocList.ListResponse paramListResponse, int paramInt, Library paramLibrary)
  {
    List localList = paramListResponse.getDocList();
    if (localList.isEmpty())
    {
      localRecommendationList = null;
      return localRecommendationList;
    }
    Document localDocument1 = new Document((DocumentV2.DocV2)localList.get(0), null);
    RecommendationList localRecommendationList = new RecommendationList(localDocument1.getTitle(), paramInt);
    int i = localDocument1.getChildCount();
    PackageStateRepository localPackageStateRepository = FinskyApp.get().getPackageInfoRepository();
    int j = 0;
    label73: Document localDocument2;
    int k;
    if (j < i)
    {
      localDocument2 = localDocument1.getChildAt(j);
      k = 0;
      if (localDocument2.getDocumentType() == 1)
      {
        if (localPackageStateRepository.get(localDocument2.getAppDetails().getPackageName()) == null)
          break label171;
        k = 1;
      }
      label122: if ((!LibraryUtils.isOwned(localDocument2, paramLibrary)) && (k == 0))
        break label177;
      if (FinskyLog.DEBUG)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localDocument2.getDocId();
        FinskyLog.v("Already own %s, skipping", arrayOfObject);
      }
    }
    while (true)
    {
      j++;
      break label73;
      break;
      label171: k = 0;
      break label122;
      label177: localRecommendationList.add(new Recommendation(localDocument2));
    }
  }

  public static void performBackFill(DfeApi paramDfeApi, final Context paramContext, RecommendationList paramRecommendationList, final Library paramLibrary, final int paramInt)
  {
    paramDfeApi.getRecommendations(paramRecommendationList.getBackendId(), new Response.Listener()
    {
      public void onResponse(DocList.ListResponse paramAnonymousListResponse)
      {
        RecommendationList localRecommendationList = RecommendationsStore.parseNetworkResponse(paramAnonymousListResponse, this.val$oldRecs.getBackendId(), paramLibrary);
        this.val$oldRecs.merge(localRecommendationList);
        RecommendationsStore.sWriteThread.execute(new Runnable()
        {
          public void run()
          {
            RecommendationsStore.1.this.val$oldRecs.writeToDisk(RecommendationsStore.1.this.val$context);
            Context localContext = RecommendationsStore.1.this.val$context;
            int[] arrayOfInt = new int[1];
            arrayOfInt[0] = RecommendationsStore.1.this.val$appWidgetId;
            RecommendationsViewFactory.notifyDataSetChanged(localContext, arrayOfInt);
          }
        });
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.e("Error while fetching more recs: %s", new Object[] { paramAnonymousVolleyError });
      }
    });
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.recommendation.RecommendationsStore
 * JD-Core Version:    0.6.2
 */
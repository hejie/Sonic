package com.google.android.finsky.widget.consumption;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.BitmapLoader.BitmapContainer;
import com.google.android.finsky.utils.BitmapLoader.BitmapLoadedHandler;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BatchedImageLoader
{
  private static final ExecutorService sFetchThread = Executors.newSingleThreadExecutor();
  private final BitmapLoader mBitmapLoader;
  private final Context mContext;
  private volatile Map<ImageBatch.ImageSpec, Bitmap> mPreviousBitmaps;
  private final Thread mProcessingThread;
  private LinkedBlockingQueue<ImageBatch> mQueue = new LinkedBlockingQueue();
  private int mTotalBitmapMemory = 0;
  private final Semaphore mWaitLock = new Semaphore(0);
  private final Cache mWidgetCache;

  public BatchedImageLoader(Context paramContext, Cache paramCache)
  {
    this.mWidgetCache = paramCache;
    this.mContext = paramContext;
    this.mBitmapLoader = FinskyApp.get().getBitmapLoader();
    this.mProcessingThread = new Thread("BatchedImageLoader.mProcessingThread")
    {
      public void run()
      {
        while (true)
          try
          {
            BatchedImageLoader.this.handleBatchedRequest((ImageBatch)BatchedImageLoader.this.mQueue.take());
          }
          catch (InterruptedException localInterruptedException)
          {
            FinskyLog.e("Interrupted while trying to load images!", new Object[0]);
          }
      }
    };
    this.mProcessingThread.start();
  }

  private static int findBestSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    double d = Math.min(paramInt1 / paramInt3, paramInt2 / paramInt4);
    for (float f = 1.0F; f * 2.0F <= d; f *= 2.0F);
    return (int)f;
  }

  private Bitmap getScaledBitmap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Object localObject;
    if ((paramInt1 == 0) || (paramInt2 == 0))
      localObject = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    while (true)
    {
      return localObject;
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      int i = localOptions.outWidth;
      int j = localOptions.outHeight;
      if ((i < paramInt1) || (j < paramInt2))
      {
        localObject = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
      }
      else
      {
        float f = i / j;
        int k = (int)(paramInt1 / f);
        int m = (int)(f * paramInt2);
        localOptions.inJustDecodeBounds = false;
        int n;
        if (k > paramInt2)
          n = paramInt1;
        Bitmap localBitmap;
        for (int i1 = k; ; i1 = paramInt2)
        {
          localOptions.inSampleSize = findBestSampleSize(i, j, n, i1);
          localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
          if ((localBitmap == null) || ((localBitmap.getWidth() == n) && (localBitmap.getHeight() == i1)))
            break label209;
          localObject = Bitmap.createScaledBitmap(localBitmap, n, i1, true);
          localBitmap.recycle();
          break;
          n = m;
        }
        label209: localObject = localBitmap;
      }
    }
  }

  private void handleBatchedRequest(ImageBatch paramImageBatch)
    throws InterruptedException
  {
    int i = 0;
    int j = 0;
    this.mTotalBitmapMemory = 0;
    final HashMap localHashMap = Maps.newHashMap();
    if (this.mPreviousBitmaps != null)
    {
      Iterator localIterator2 = paramImageBatch.urisToLoad.iterator();
      while (true)
      {
        if (!localIterator2.hasNext())
          break label169;
        ImageBatch.ImageSpec localImageSpec2 = (ImageBatch.ImageSpec)localIterator2.next();
        Iterator localIterator3 = this.mPreviousBitmaps.keySet().iterator();
        if (localIterator3.hasNext())
        {
          ImageBatch.ImageSpec localImageSpec3 = (ImageBatch.ImageSpec)localIterator3.next();
          if (!localImageSpec3.satisfies(localImageSpec2.uri, localImageSpec2.width, localImageSpec2.height))
            break;
          Bitmap localBitmap2 = (Bitmap)this.mPreviousBitmaps.get(localImageSpec3);
          if (localBitmap2 == null)
            break;
          localHashMap.put(localImageSpec3, localBitmap2);
          localIterator2.remove();
          i += localBitmap2.getByteCount();
          j++;
        }
      }
      label169: this.mPreviousBitmaps = null;
    }
    Iterator localIterator1 = paramImageBatch.urisToLoad.iterator();
    if (localIterator1.hasNext())
    {
      ImageBatch.ImageSpec localImageSpec1 = (ImageBatch.ImageSpec)localIterator1.next();
      Uri localUri = localImageSpec1.uri;
      if (("http".equals(localUri.getScheme())) || ("https".equals(localUri.getScheme())));
      for (Bitmap localBitmap1 = loadFromBitmapLoader(localHashMap, localImageSpec1); ; localBitmap1 = loadFromProvider(localImageSpec1))
      {
        if (localBitmap1 == null)
          localBitmap1 = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837518);
        localHashMap.put(localImageSpec1, localBitmap1);
        break;
      }
    }
    if (FinskyLog.DEBUG)
    {
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = Integer.valueOf(j + paramImageBatch.urisToLoad.size());
      arrayOfObject[1] = Integer.valueOf((i + this.mTotalBitmapMemory) / 1024);
      arrayOfObject[2] = Integer.valueOf(paramImageBatch.backendId);
      arrayOfObject[3] = Integer.valueOf(j);
      arrayOfObject[4] = Integer.valueOf(i / 1024);
      FinskyLog.v("Loaded %s images [%s k] for backend=[%s] (%s were reused, %s k)", arrayOfObject);
    }
    final BatchedImageCallback localBatchedImageCallback = paramImageBatch.callback;
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        if (localBatchedImageCallback != null)
          localBatchedImageCallback.onLoaded(localHashMap);
        BatchedImageLoader.this.mWaitLock.release();
      }
    });
    this.mWaitLock.acquire();
    this.mPreviousBitmaps = localHashMap;
  }

  // ERROR //
  private byte[] loadFileFromUri(ImageBatch.ImageSpec paramImageSpec)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: iload_2
    //   5: iconst_3
    //   6: if_icmpge +181 -> 187
    //   9: aload_3
    //   10: ifnonnull +177 -> 187
    //   13: aconst_null
    //   14: astore 4
    //   16: aload_1
    //   17: getfield 197	com/google/android/finsky/widget/consumption/ImageBatch$ImageSpec:uri	Landroid/net/Uri;
    //   20: invokevirtual 311	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   23: ldc_w 313
    //   26: aload_1
    //   27: getfield 200	com/google/android/finsky/widget/consumption/ImageBatch$ImageSpec:width	I
    //   30: invokestatic 316	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   33: invokevirtual 322	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   36: ldc_w 324
    //   39: aload_1
    //   40: getfield 203	com/google/android/finsky/widget/consumption/ImageBatch$ImageSpec:height	I
    //   43: invokestatic 316	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: invokevirtual 322	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   49: invokevirtual 328	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   52: astore 10
    //   54: aload_0
    //   55: getfield 56	com/google/android/finsky/widget/consumption/BatchedImageLoader:mContext	Landroid/content/Context;
    //   58: invokevirtual 332	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   61: aload 10
    //   63: ldc_w 334
    //   66: invokevirtual 340	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
    //   69: astore 11
    //   71: aload 11
    //   73: ifnull +32 -> 105
    //   76: new 342	java/io/FileInputStream
    //   79: dup
    //   80: aload 11
    //   82: invokevirtual 348	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   85: invokespecial 351	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   88: astore 12
    //   90: aload 12
    //   92: invokestatic 355	com/google/android/finsky/widget/consumption/BatchedImageLoader:streamToBytes	(Ljava/io/InputStream;)[B
    //   95: astore_3
    //   96: aload 11
    //   98: invokevirtual 358	android/os/ParcelFileDescriptor:close	()V
    //   101: aload 12
    //   103: astore 4
    //   105: iinc 2 1
    //   108: aload 4
    //   110: ifnull -106 -> 4
    //   113: aload 4
    //   115: invokevirtual 359	java/io/FileInputStream:close	()V
    //   118: goto -114 -> 4
    //   121: astore 14
    //   123: goto -119 -> 4
    //   126: astore 8
    //   128: ldc_w 361
    //   131: iconst_1
    //   132: anewarray 4	java/lang/Object
    //   135: dup
    //   136: iconst_0
    //   137: aload_1
    //   138: aastore
    //   139: invokestatic 364	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   142: iinc 2 1
    //   145: aload 4
    //   147: ifnull -143 -> 4
    //   150: aload 4
    //   152: invokevirtual 359	java/io/FileInputStream:close	()V
    //   155: goto -151 -> 4
    //   158: astore 9
    //   160: goto -156 -> 4
    //   163: astore 5
    //   165: iload_2
    //   166: iconst_1
    //   167: iadd
    //   168: pop
    //   169: aload 4
    //   171: ifnull +8 -> 179
    //   174: aload 4
    //   176: invokevirtual 359	java/io/FileInputStream:close	()V
    //   179: aload 5
    //   181: athrow
    //   182: astore 7
    //   184: goto -5 -> 179
    //   187: aload_3
    //   188: areturn
    //   189: astore 5
    //   191: aload 12
    //   193: astore 4
    //   195: goto -30 -> 165
    //   198: astore 13
    //   200: aload 12
    //   202: astore 4
    //   204: goto -76 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   113	118	121	java/io/IOException
    //   16	90	126	java/io/IOException
    //   150	155	158	java/io/IOException
    //   16	90	163	finally
    //   128	142	163	finally
    //   174	179	182	java/io/IOException
    //   90	101	189	finally
    //   90	101	198	java/io/IOException
  }

  private Bitmap loadFromBitmapLoader(Map<ImageBatch.ImageSpec, Bitmap> paramMap, ImageBatch.ImageSpec paramImageSpec)
  {
    final Bitmap[] arrayOfBitmap = new Bitmap[1];
    final Semaphore localSemaphore = new Semaphore(0);
    BitmapLoader.BitmapContainer localBitmapContainer = this.mBitmapLoader.get(paramImageSpec.uri.toString(), null, new BitmapLoader.BitmapLoadedHandler()
    {
      public void onResponse(BitmapLoader.BitmapContainer paramAnonymousBitmapContainer)
      {
        arrayOfBitmap[0] = paramAnonymousBitmapContainer.getBitmap();
        localSemaphore.release();
      }
    }
    , paramImageSpec.width, paramImageSpec.height);
    if (localBitmapContainer.getBitmap() != null)
      arrayOfBitmap[0] = localBitmapContainer.getBitmap();
    while (true)
    {
      return arrayOfBitmap[0];
      localSemaphore.acquireUninterruptibly();
    }
  }

  private Bitmap loadFromProvider(final ImageBatch.ImageSpec paramImageSpec)
  {
    Cache.Entry localEntry1 = this.mWidgetCache.get(paramImageSpec.toString());
    byte[] arrayOfByte = null;
    Bitmap localBitmap;
    if (localEntry1 != null)
    {
      arrayOfByte = localEntry1.data;
      localBitmap = null;
      if (arrayOfByte == null)
        break label272;
      localBitmap = getScaledBitmap(arrayOfByte, paramImageSpec.width, paramImageSpec.height);
      if (localBitmap == null)
        break label247;
      this.mTotalBitmapMemory += localBitmap.getByteCount();
    }
    while (true)
    {
      while (true)
      {
        return localBitmap;
        FutureTask localFutureTask = new FutureTask(new Callable()
        {
          public byte[] call()
            throws Exception
          {
            return BatchedImageLoader.this.loadFileFromUri(paramImageSpec);
          }
        });
        try
        {
          sFetchThread.execute(localFutureTask);
          arrayOfByte = (byte[])localFutureTask.get(((Long)G.consumptionAppImageTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS);
          if (arrayOfByte == null)
            break;
          Cache.Entry localEntry2 = new Cache.Entry();
          localEntry2.data = arrayOfByte;
          localEntry2.ttl = 9223372036854775807L;
          this.mWidgetCache.put(paramImageSpec.toString(), localEntry2);
        }
        catch (TimeoutException localTimeoutException)
        {
          Object[] arrayOfObject5 = new Object[1];
          arrayOfObject5[0] = paramImageSpec.uri;
          FinskyLog.e("Timed out while waiting for %s", arrayOfObject5);
        }
        catch (InterruptedException localInterruptedException)
        {
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = paramImageSpec.uri;
          FinskyLog.e("Interrupted while loading %s", arrayOfObject4);
        }
        catch (ExecutionException localExecutionException)
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = paramImageSpec.uri;
          FinskyLog.e("ExecutionException while loading %s", arrayOfObject1);
        }
      }
      break;
      label247: Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = paramImageSpec.uri;
      FinskyLog.e("Failed to decode bitmap %s", arrayOfObject3);
      continue;
      label272: Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramImageSpec.uri;
      FinskyLog.e("File was not loaded for uri=[%s]", arrayOfObject2);
    }
  }

  private static byte[] streamToBytes(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte1 = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte1);
      if (i == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte1, 0, i);
    }
    byte[] arrayOfByte2 = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte2;
  }

  public void enqueue(ImageBatch paramImageBatch)
  {
    this.mQueue.add(paramImageBatch);
  }

  public Map<ImageBatch.ImageSpec, Bitmap> getCachedBitmaps()
  {
    return this.mPreviousBitmaps;
  }

  public static abstract interface BatchedImageCallback
  {
    public abstract void onLoaded(Map<ImageBatch.ImageSpec, Bitmap> paramMap);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.BatchedImageLoader
 * JD-Core Version:    0.6.2
 */
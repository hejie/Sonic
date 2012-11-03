package com.google.android.finsky.exploreactivity;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NoCache;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.volley.GoogleHttpClient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

public class MusicPreviewManager
  implements Response.ErrorListener
{
  private static final byte[] BLANK_MP3_FRAME = new byte['Ñ'];
  private final File mCacheDir;
  private Map<String, CacheFileEntry> mCacheFiles = Maps.newHashMap();
  private int mCacheSizeBytes = 0;
  private ThreadPoolExecutor mDownloadExecutor;
  private GoogleHttpClient mHttpClient;
  private final int mMaxCacheSizeBytes;
  private final int mMaxSampleBytes;
  private final int mMinBufferSizeBytes;
  private final int mPrecacheBytes;
  private RequestQueue mRequestQueue;
  private final int mSampleRateKbit;

  static
  {
    BLANK_MP3_FRAME[0] = -1;
    BLANK_MP3_FRAME[1] = -13;
    BLANK_MP3_FRAME[2] = -126;
    BLANK_MP3_FRAME[3] = -60;
  }

  public MusicPreviewManager(Context paramContext)
  {
    NoCache localNoCache = new NoCache();
    FinskyApp.get();
    this.mRequestQueue = new RequestQueue(localNoCache, FinskyApp.createNetwork());
    this.mRequestQueue.start();
    int i = ((Integer)G.explorerPrefetchThreads.get()).intValue();
    this.mSampleRateKbit = ((Integer)G.explorerSampleRateKbit.get()).intValue();
    int j = 1024 * (this.mSampleRateKbit / 8);
    this.mPrecacheBytes = (j * ((Integer)G.explorerPrecacheSecs.get()).intValue());
    this.mMinBufferSizeBytes = (j * ((Integer)G.explorerMinBufferSecs.get()).intValue());
    this.mMaxSampleBytes = (j * ((Integer)G.explorerPreviewSecs.get()).intValue());
    this.mMaxCacheSizeBytes = (1024 * (1024 * ((Integer)G.explorerMaxDiskCacheSizeMb.get()).intValue()));
    this.mCacheDir = new File(paramContext.getCacheDir(), "music");
    this.mCacheDir.mkdirs();
    this.mHttpClient = new GoogleHttpClient(FinskyApp.get(), "unused/0", false);
    this.mDownloadExecutor = new ThreadPoolExecutor(i, i, 9223372036854775807L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  }

  private void deleteAll()
  {
    this.mDownloadExecutor.execute(new Runnable()
    {
      public void run()
      {
        File[] arrayOfFile = MusicPreviewManager.this.mCacheDir.listFiles();
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++)
          arrayOfFile[j].delete();
      }
    });
  }

  private void downloadFile(DownloadRequest paramDownloadRequest, HttpResponse paramHttpResponse)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramDownloadRequest.mSize);
    arrayOfObject[1] = paramDownloadRequest.mWrapper.getTitle();
    FinskyLog.d("Downloading %d bytes for MP3 %s", arrayOfObject);
    InputStream localInputStream = paramHttpResponse.getEntity().getContent();
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramDownloadRequest.mFile, "rw");
    while (true)
    {
      try
      {
        if (paramDownloadRequest.mSize < 2147483647)
        {
          writeBlankMp3(localRandomAccessFile, paramDownloadRequest.mOffset, paramDownloadRequest.mSize - paramDownloadRequest.mOffset);
          localRandomAccessFile.seek(paramDownloadRequest.mOffset);
        }
        byte[] arrayOfByte = new byte[1024];
        i = 0;
        int m;
        int j;
        if (paramDownloadRequest.mOffset > 0)
        {
          m = localInputStream.read(arrayOfByte, 0, Math.min(arrayOfByte.length, paramDownloadRequest.mOffset - i));
          if (m <= 0)
            break label344;
        }
        else
        {
          j = localInputStream.read(arrayOfByte, 0, Math.min(arrayOfByte.length, paramDownloadRequest.mSize - i));
          if (j > 0)
            continue;
          if (paramDownloadRequest.mListener != null)
            paramDownloadRequest.mListener.fileReady(paramDownloadRequest.mWrapper, paramDownloadRequest.mFile);
          return;
        }
        i += m;
        if (i < paramDownloadRequest.mOffset)
          continue;
        break label344;
        i += j;
        localRandomAccessFile.write(arrayOfByte, 0, j);
        if ((i >= this.mMinBufferSizeBytes) && (paramDownloadRequest.mListener != null))
        {
          paramDownloadRequest.mListener.fileReady(paramDownloadRequest.mWrapper, paramDownloadRequest.mFile);
          DownloadRequest.access$702(paramDownloadRequest, null);
        }
        int k = paramDownloadRequest.mSize;
        if (i < k)
          continue;
        continue;
      }
      finally
      {
        localInputStream.close();
        localRandomAccessFile.close();
        makeSpaceForFile(paramDownloadRequest.mWrapper.getDocId(), paramDownloadRequest.mFile);
      }
      label344: int i = 0;
    }
  }

  private void fetchUrl(DownloadRequest paramDownloadRequest)
  {
    HttpResponse localHttpResponse;
    try
    {
      localHttpResponse = this.mHttpClient.execute(new HttpGet(paramDownloadRequest.mUrl));
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        downloadFile(paramDownloadRequest, localHttpResponse);
      }
      else
      {
        Header[] arrayOfHeader = localHttpResponse.getHeaders("Location");
        if ((arrayOfHeader != null) && (arrayOfHeader.length != 0))
        {
          String str = arrayOfHeader[(-1 + arrayOfHeader.length)].getValue();
          if (paramDownloadRequest.mUrl.equals(str))
            return;
          DownloadRequest.access$302(paramDownloadRequest, str);
          fetchUrl(paramDownloadRequest);
        }
      }
    }
    catch (Exception localException)
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramDownloadRequest.mWrapper.getDocId();
      arrayOfObject1[1] = localException.toString();
      FinskyLog.e("Unable to download song sample %s: %s", arrayOfObject1);
    }
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = paramDownloadRequest.mWrapper.getDocId();
    arrayOfObject2[1] = localHttpResponse.getStatusLine().toString();
    FinskyLog.e("Unable to download song sample %s: %s", arrayOfObject2);
  }

  private void makeSpaceForFile(String paramString, File paramFile)
  {
    long l = paramFile.length();
    CacheFileEntry localCacheFileEntry1 = (CacheFileEntry)this.mCacheFiles.get(paramString);
    if (localCacheFileEntry1 != null)
    {
      this.mCacheSizeBytes = ((int)(this.mCacheSizeBytes + (l - localCacheFileEntry1.mSize)));
      CacheFileEntry.access$802(localCacheFileEntry1, l);
      if (this.mCacheSizeBytes > this.mMaxCacheSizeBytes)
        break label98;
      label60: return;
      break label124;
    }
    while (true)
    {
      this.mCacheFiles.put(paramString, new CacheFileEntry(paramString, paramFile, l));
      this.mCacheSizeBytes = ((int)(l + this.mCacheSizeBytes));
      break;
      label98: ArrayList localArrayList = Lists.newArrayList(this.mCacheFiles.values());
      Collections.sort(localArrayList);
      Iterator localIterator = localArrayList.iterator();
      label124: if (localIterator.hasNext())
      {
        CacheFileEntry localCacheFileEntry2 = (CacheFileEntry)localIterator.next();
        this.mCacheSizeBytes = ((int)(this.mCacheSizeBytes - localCacheFileEntry2.mSize));
        localCacheFileEntry2.mFile.delete();
        this.mCacheFiles.remove(localCacheFileEntry2.mDocId);
        if (this.mCacheSizeBytes > this.mMaxCacheSizeBytes)
          break label60;
      }
    }
  }

  private String replaceUrlParam(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1.indexOf(paramString2 + "=") == -1);
    for (String str = paramString1 + "&" + paramString2 + "=" + paramString3; ; str = paramString1.replaceFirst(paramString2 + "=[^&]*", paramString2 + "=" + paramString3))
      return str;
  }

  private void writeBlankMp3(File paramFile, int paramInt1, int paramInt2)
  {
    try
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, "rw");
      writeBlankMp3(localRandomAccessFile, paramInt1, paramInt2);
      localRandomAccessFile.close();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        FinskyLog.e(localIOException.toString(), new Object[0]);
    }
  }

  private void writeBlankMp3(RandomAccessFile paramRandomAccessFile, int paramInt1, int paramInt2)
  {
    long l = paramInt1;
    try
    {
      paramRandomAccessFile.seek(l);
      int i = 0;
      int j = BLANK_MP3_FRAME.length;
      while (i + j <= paramInt2)
      {
        paramRandomAccessFile.write(BLANK_MP3_FRAME);
        i += j;
      }
    }
    catch (IOException localIOException)
    {
      FinskyLog.e(localIOException.toString(), new Object[0]);
    }
  }

  public void cancel(DocWrapper paramDocWrapper)
  {
    this.mRequestQueue.cancelAll(paramDocWrapper);
    Iterator localIterator = this.mDownloadExecutor.getQueue().iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      if (((localRunnable instanceof DownloadRequest)) && (((DownloadRequest)localRunnable).mWrapper == paramDocWrapper))
        localIterator.remove();
    }
  }

  public void clear()
  {
    this.mDownloadExecutor.getQueue().clear();
    deleteAll();
    this.mCacheFiles.clear();
  }

  public void destroy()
  {
    deleteAll();
    this.mDownloadExecutor.shutdown();
    this.mCacheFiles = null;
    this.mRequestQueue.stop();
  }

  public void fetchPreview(DocWrapper paramDocWrapper, MusicPreviewListener paramMusicPreviewListener, boolean paramBoolean)
  {
    Document localDocument = paramDocWrapper.getSong();
    if (!paramBoolean)
      paramMusicPreviewListener = null;
    int i;
    File localFile;
    int j;
    if (paramBoolean)
    {
      i = this.mMaxSampleBytes;
      localFile = new File(this.mCacheDir, localDocument.getDocId());
      j = 0;
      if (!localFile.exists())
        break label157;
      l = localFile.length();
      if (l < i)
        break label90;
      if (paramMusicPreviewListener != null)
        paramMusicPreviewListener.fileReady(paramDocWrapper, localFile);
    }
    label90: 
    while ((localDocument.getSongDetails() == null) || (!localDocument.getSongDetails().hasPreviewUrl()))
    {
      long l;
      return;
      i = this.mPrecacheBytes;
      break;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localDocument.getTitle();
      FinskyLog.d("Inflating MP3 for %s", arrayOfObject2);
      j = (int)l;
      writeBlankMp3(localFile, j, i - j);
      if ((l > this.mMinBufferSizeBytes) && (paramMusicPreviewListener != null))
      {
        paramMusicPreviewListener.fileReady(paramDocWrapper, localFile);
        paramMusicPreviewListener = null;
      }
    }
    label157: Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = localDocument.getTitle();
    FinskyLog.d("Getting Skyjam preview URL for %s", arrayOfObject1);
    String str = replaceUrlParam(replaceUrlParam(localDocument.getSongDetails().getPreviewUrl(), "mode", "streaming"), "targetkbps", Integer.toString(this.mSampleRateKbit));
    int k = i - j;
    DownloadRequest localDownloadRequest = new DownloadRequest(paramDocWrapper, null, localFile, paramMusicPreviewListener, j, k);
    if (paramBoolean);
    for (Request.Priority localPriority = Request.Priority.IMMEDIATE; ; localPriority = Request.Priority.NORMAL)
    {
      PreviewUrlRequest localPreviewUrlRequest = new PreviewUrlRequest(localDownloadRequest, str, localPriority, null);
      this.mRequestQueue.add(localPreviewUrlRequest).setTag(paramDocWrapper);
      break;
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    FinskyLog.e(paramVolleyError.toString(), new Object[0]);
  }

  private static class CacheFileEntry
    implements Comparable<CacheFileEntry>
  {
    private final String mDocId;
    private final File mFile;
    private long mSize;
    private final long mTimestamp = System.currentTimeMillis();

    public CacheFileEntry(String paramString, File paramFile, long paramLong)
    {
      this.mDocId = paramString;
      this.mFile = paramFile;
      this.mSize = paramLong;
    }

    public int compareTo(CacheFileEntry paramCacheFileEntry)
    {
      int i;
      if (this.mTimestamp > paramCacheFileEntry.mTimestamp)
        i = 1;
      while (true)
      {
        return i;
        if (this.mTimestamp < paramCacheFileEntry.mTimestamp)
          i = -1;
        else
          i = 0;
      }
    }
  }

  private class DownloadRequest
    implements Runnable
  {
    private final File mFile;
    private MusicPreviewManager.MusicPreviewListener mListener;
    private final int mOffset;
    private final int mSize;
    private String mUrl;
    private final DocWrapper mWrapper;

    public DownloadRequest(DocWrapper paramString, String paramFile, File paramMusicPreviewListener, MusicPreviewManager.MusicPreviewListener paramInt1, int paramInt2, int arg7)
    {
      this.mWrapper = paramString;
      this.mUrl = paramFile;
      this.mFile = paramMusicPreviewListener;
      this.mListener = paramInt1;
      this.mOffset = paramInt2;
      int i;
      if (i > 0);
      while (true)
      {
        this.mSize = i;
        return;
        i = 2147483647;
      }
    }

    public void run()
    {
      MusicPreviewManager.this.fetchUrl(this);
    }
  }

  public static abstract interface MusicPreviewListener
  {
    public abstract void fileReady(DocWrapper paramDocWrapper, File paramFile);
  }

  private class PreviewUrlRequest extends SkyjamJsonObjectRequest
  {
    private final Request.Priority mPriority;

    private PreviewUrlRequest(final MusicPreviewManager.DownloadRequest paramString, String paramPriority, Request.Priority arg4)
    {
      super(null, new Response.Listener()
      {
        public void onResponse(JSONObject paramAnonymousJSONObject)
        {
          try
          {
            MusicPreviewManager.DownloadRequest.access$302(paramString, paramAnonymousJSONObject.getString("url"));
            MusicPreviewManager.PreviewUrlRequest.this.mDownloadExecutor.execute(paramString);
            label28: return;
          }
          catch (JSONException localJSONException)
          {
            while (true)
              FinskyLog.e(localJSONException.toString(), new Object[0]);
          }
          catch (RejectedExecutionException localRejectedExecutionException)
          {
            break label28;
          }
        }
      }
      , MusicPreviewManager.this);
      Object localObject;
      this.mPriority = localObject;
    }

    public Request.Priority getPriority()
    {
      return this.mPriority;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.MusicPreviewManager
 * JD-Core Version:    0.6.2
 */
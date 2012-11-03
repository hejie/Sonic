package com.google.android.finsky.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LruCache;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppIconProvider extends ContentProvider
{
  public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.finsky.AppIconProvider");
  private Semaphore mIoSemaphore = new Semaphore(0);

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }

  public String getType(Uri paramUri)
  {
    return "image/png";
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }

  public boolean onCreate()
  {
    new Thread()
    {
      public void run()
      {
        AppIconProvider.AppIconLoader.initialize(AppIconProvider.this.getContext().getCacheDir().listFiles());
        AppIconProvider.this.mIoSemaphore.release();
      }
    }
    .start();
    return true;
  }

  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    long l = Binder.clearCallingIdentity();
    try
    {
      this.mIoSemaphore.acquire();
      if (paramUri.getPathSegments().size() == 1)
      {
        String str = (String)paramUri.getPathSegments().get(0);
        ParcelFileDescriptor localParcelFileDescriptor2 = ParcelFileDescriptor.open(new AppIconLoader(getContext(), str).getIconFile(), 268435456);
        localParcelFileDescriptor1 = localParcelFileDescriptor2;
        return localParcelFileDescriptor1;
      }
      throw new IllegalArgumentException("Invalid URI: " + paramUri);
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
      {
        FinskyLog.w("Opening file interrupted while waiting for IO.", new Object[0]);
        ParcelFileDescriptor localParcelFileDescriptor1 = null;
        this.mIoSemaphore.release();
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      this.mIoSemaphore.release();
      Binder.restoreCallingIdentity(l);
    }
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return null;
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }

  public static class AppIconLoader
  {
    private static AppIconProvider.IconCache sIconCache;
    private String mAppPackage;
    private File mIconFile;

    public AppIconLoader(Context paramContext, String paramString)
    {
      if (sIconCache == null)
        throw new IllegalStateException("AppIconLoader must be initialized before use.");
      this.mAppPackage = paramString;
      this.mIconFile = ((File)sIconCache.get(paramString));
      if ((this.mIconFile != null) && (10800000L + this.mIconFile.lastModified() < System.currentTimeMillis()))
      {
        sIconCache.remove(paramString);
        sIconCache.destroy(paramString, this.mIconFile);
        this.mIconFile = null;
      }
      if (this.mIconFile == null)
        this.mIconFile = new File(paramContext.getCacheDir(), fileNameFromPackage(this.mAppPackage));
    }

    public static String fileNameFromPackage(String paramString)
    {
      return "thmb_" + paramString;
    }

    public static void initialize(File[] paramArrayOfFile)
    {
      try
      {
        if (sIconCache == null)
        {
          sIconCache = new AppIconProvider.IconCache(20);
          int i = paramArrayOfFile.length;
          for (int j = 0; j < i; j++)
          {
            File localFile = paramArrayOfFile[j];
            String str = localFile.getName();
            if (isTempFileName(str))
              sIconCache.put(packageNameFromFile(str), localFile);
          }
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public static boolean isTempFileName(String paramString)
    {
      return paramString.startsWith("thmb_");
    }

    public static String packageNameFromFile(String paramString)
    {
      return paramString.substring(5);
    }

    public File getIconFile()
    {
      return this.mIconFile;
    }

    public void loadToFileFromBlob(String paramString, AppIconProvider.TimedOnCompleteListener paramTimedOnCompleteListener)
    {
      if (sIconCache.get(this.mAppPackage) != null)
        paramTimedOnCompleteListener.callOnComplete();
      while (true)
      {
        return;
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(this.mIconFile);
          localFileOutputStream.write(Base64.decode(paramString, 0));
          localFileOutputStream.close();
          paramTimedOnCompleteListener.callOnComplete();
        }
        catch (IOException localIOException)
        {
          FinskyLog.w("Failed to write icon blob to file: " + localIOException, new Object[0]);
          paramTimedOnCompleteListener.callOnComplete();
        }
        finally
        {
          paramTimedOnCompleteListener.callOnComplete();
        }
      }
    }

    public void loadToFileFromUrl(String paramString, final AppIconProvider.TimedOnCompleteListener paramTimedOnCompleteListener)
    {
      if (sIconCache.get(this.mAppPackage) != null)
        paramTimedOnCompleteListener.callOnComplete();
      while (true)
      {
        return;
        FinskyApp.get().getRequestQueue().add(new AppIconProvider.FetchToFileRequest(paramString, this.mIconFile, new Response.Listener()
        {
          public void onResponse(byte[] paramAnonymousArrayOfByte)
          {
            paramTimedOnCompleteListener.callOnComplete();
          }
        }
        , new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            FinskyLog.w("Failed to fetch icon to file.", new Object[0]);
            paramTimedOnCompleteListener.callOnComplete();
          }
        }));
      }
    }
  }

  private static class FetchToFileRequest extends Request<byte[]>
  {
    private final File mFileToWrite;
    private final Response.Listener<byte[]> mListener;

    public FetchToFileRequest(String paramString, File paramFile, Response.Listener<byte[]> paramListener, Response.ErrorListener paramErrorListener)
    {
      super(paramErrorListener);
      this.mFileToWrite = paramFile;
      this.mListener = paramListener;
      setShouldCache(false);
    }

    protected void deliverResponse(byte[] paramArrayOfByte)
    {
      this.mListener.onResponse(paramArrayOfByte);
    }

    protected Response<byte[]> parseNetworkResponse(NetworkResponse paramNetworkResponse)
    {
      byte[] arrayOfByte = paramNetworkResponse.data;
      try
      {
        FileOutputStream localFileOutputStream = new FileOutputStream(this.mFileToWrite);
        localFileOutputStream.write(arrayOfByte);
        localFileOutputStream.close();
        Response localResponse2 = Response.success(arrayOfByte, HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
        localResponse1 = localResponse2;
        return localResponse1;
      }
      catch (IOException localIOException)
      {
        while (true)
          Response localResponse1 = Response.error(new ParseError());
      }
    }
  }

  private static class IconCache extends LruCache<String, File>
  {
    public IconCache(int paramInt)
    {
      super();
    }

    public void destroy(String paramString, File paramFile)
    {
      paramFile.delete();
    }

    protected void entryEvicted(String paramString, File paramFile)
    {
      destroy(paramString, paramFile);
    }
  }

  public static abstract class TimedOnCompleteListener
  {
    private AtomicBoolean completed = new AtomicBoolean(false);

    public TimedOnCompleteListener()
    {
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          AppIconProvider.TimedOnCompleteListener.this.callOnComplete();
        }
      }
      , 200L);
    }

    public void callOnComplete()
    {
      if (this.completed.compareAndSet(false, true))
        onComplete();
    }

    protected abstract void onComplete();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.providers.AppIconProvider
 * JD-Core Version:    0.6.2
 */
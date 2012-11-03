package com.google.android.finsky.analytics;

import android.net.Uri;
import android.os.Handler;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Log.ClickLogEvent;
import com.google.android.finsky.remoting.protos.Log.LogRequest;
import com.google.android.finsky.remoting.protos.Log.LogResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.Iterator;
import java.util.List;

public class DfeAnalytics
  implements Analytics
{
  private static final int DISPATCH_PERIOD_MS = 1000 * ((Integer)G.logsDispatchIntervalSeconds.get()).intValue();
  private static final int MAX_LOGS_BEFORE_FLUSH = ((Integer)G.maxLogQueueSizeBeforeFlush.get()).intValue();
  private DfeApi mDfeApi;
  private final Handler mHandler;
  private final Runnable mLogFlusher = new Runnable()
  {
    public void run()
    {
      DfeAnalytics.this.flushLogs();
    }
  };
  private List<Log.ClickLogEvent> mPendingEvents = Lists.newArrayList();

  public DfeAnalytics(Handler paramHandler, DfeApi paramDfeApi)
  {
    this.mHandler = paramHandler;
    this.mDfeApi = paramDfeApi;
  }

  private void flushLogs()
  {
    if (this.mDfeApi == null);
    while (true)
    {
      return;
      final int i = this.mPendingEvents.size();
      if (i != 0)
      {
        Log.LogRequest localLogRequest = new Log.LogRequest();
        Iterator localIterator = this.mPendingEvents.iterator();
        while (localIterator.hasNext())
          localLogRequest.addClickEvent((Log.ClickLogEvent)localIterator.next());
        this.mPendingEvents.clear();
        Response.Listener local1 = new Response.Listener()
        {
          public void onResponse(Log.LogResponse paramAnonymousLogResponse)
          {
            if (FinskyLog.DEBUG)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = Integer.valueOf(i);
              FinskyLog.v("Logged %d analytics events successfully.", arrayOfObject);
            }
          }
        };
        Response.ErrorListener local2 = new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = Integer.valueOf(i);
            arrayOfObject[1] = paramAnonymousVolleyError;
            FinskyLog.e("Failed to send %d events because of [%s]", arrayOfObject);
          }
        };
        this.mDfeApi.log(localLogRequest, local1, local2);
      }
    }
  }

  private static Log.ClickLogEvent makeClickLogEvent(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Log.ClickLogEvent localClickLogEvent = new Log.ClickLogEvent();
    localClickLogEvent.setEventTime(System.currentTimeMillis());
    if (paramString1 != null)
      localClickLogEvent.setReferrerUrl(paramString1);
    if (paramString2 != null)
      localClickLogEvent.setReferrerListId(paramString2);
    if (paramString3 != null)
      localClickLogEvent.setUrl(paramString3);
    if (paramString4 != null)
      localClickLogEvent.setListId(paramString4);
    return localClickLogEvent;
  }

  private void scheduleFlush(boolean paramBoolean)
  {
    this.mHandler.removeCallbacks(this.mLogFlusher);
    if ((paramBoolean) || (this.mPendingEvents.size() >= MAX_LOGS_BEFORE_FLUSH))
      this.mHandler.post(this.mLogFlusher);
    while (true)
    {
      return;
      this.mHandler.postDelayed(this.mLogFlusher, DISPATCH_PERIOD_MS);
    }
  }

  public void logListViewOnPage(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (FinskyLog.DEBUG)
      FinskyLog.v("Logging list view: referrerUrl=[%s], referrerCookie=[%s], currentPageUrl=[%s], listCookie=[%s]", new Object[] { paramString1, paramString2, paramString3, paramString4 });
    this.mPendingEvents.add(makeClickLogEvent(paramString1, paramString2, paramString3, paramString4));
    scheduleFlush(false);
  }

  public void logPageView(String paramString1, String paramString2, String paramString3)
  {
    if (FinskyLog.DEBUG)
      FinskyLog.v("Logging page view: referrerUrl=[%s], referrerCookie=[%s], currentPageUrl=[%s]", new Object[] { paramString1, paramString2, paramString3 });
    this.mPendingEvents.add(makeClickLogEvent(paramString1, paramString2, paramString3, null));
    scheduleFlush(false);
  }

  public void logTagAndPackage(String paramString1, String paramString2, String paramString3)
  {
    logTagAndPackage(paramString1, paramString2, paramString3, null);
  }

  public void logTagAndPackage(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append(paramString1).append("?doc=").append(Uri.encode(paramString2));
    String str1;
    StringBuilder localStringBuilder2;
    if (paramString3 == null)
    {
      str1 = "";
      localStringBuilder2 = localStringBuilder1.append(str1);
      if (paramString4 != null)
        break label95;
    }
    label95: for (String str2 = ""; ; str2 = "&reason=" + Uri.encode(paramString4))
    {
      logPageView(null, null, str2);
      return;
      str1 = "&error=" + Uri.encode(paramString3);
      break;
    }
  }

  public void reset()
  {
    DfeApi localDfeApi = this.mDfeApi;
    this.mDfeApi = FinskyApp.get().getDfeApi();
    if (localDfeApi != null)
      this.mPendingEvents.clear();
    while (true)
    {
      return;
      scheduleFlush(true);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.analytics.DfeAnalytics
 * JD-Core Version:    0.6.2
 */
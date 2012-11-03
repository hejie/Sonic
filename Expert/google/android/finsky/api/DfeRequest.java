package com.google.android.finsky.api;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.Notifications.Notification;
import com.google.android.finsky.remoting.protos.Response.Payload;
import com.google.android.finsky.remoting.protos.Response.PreFetch;
import com.google.android.finsky.remoting.protos.Response.ResponseWrapper;
import com.google.android.finsky.remoting.protos.Response.ServerCommands;
import com.google.android.finsky.utils.DfeLog;
import com.google.android.volley.MicroProtoHelper;
import com.google.android.volley.MicroProtoPrinter;
import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class DfeRequest<T extends MessageMicro> extends Request<Response.ResponseWrapper>
{
  private static final boolean DEBUG = DfeLog.DEBUG;
  private static final boolean PROTO_DEBUG = Log.isLoggable("DfeProto", 2);
  private boolean mAllowMultipleResponses = false;
  private final DfeApiContext mApiContext;
  private boolean mAvoidBulkCancel = false;
  private Map<String, String> mExtraHeaders;
  private Response.Listener<T> mListener;
  private final Class<T> mResponseClass;
  private boolean mResponseDelivered;

  public DfeRequest(String paramString, DfeApiContext paramDfeApiContext, Class<T> paramClass, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(Uri.withAppendedPath(DfeApi.BASE_URI, paramString).toString(), paramErrorListener);
    if (TextUtils.isEmpty(paramString))
      DfeLog.wtf("Empty DFE URL", new Object[0]);
    if (!((Boolean)DfeApiConfig.skipAllCaches.get()).booleanValue());
    for (boolean bool = true; ; bool = false)
    {
      setShouldCache(bool);
      setRetryPolicy(new DfeRetryPolicy(paramDfeApiContext));
      this.mApiContext = paramDfeApiContext;
      this.mListener = paramListener;
      this.mResponseClass = paramClass;
      return;
    }
  }

  private Response<Response.ResponseWrapper> handleServerCommands(Response.ResponseWrapper paramResponseWrapper)
  {
    Object localObject = null;
    if (!paramResponseWrapper.hasCommands());
    while (true)
    {
      return localObject;
      Response.ServerCommands localServerCommands = paramResponseWrapper.getCommands();
      if (localServerCommands.hasLogErrorStacktrace())
        DfeLog.d(localServerCommands.getLogErrorStacktrace(), new Object[0]);
      if (localServerCommands.getClearCache())
        this.mApiContext.getCache().clear();
      if (localServerCommands.hasDisplayErrorMessage())
        localObject = Response.error(new DfeServerError(localServerCommands.getDisplayErrorMessage()));
    }
  }

  private void logProtoResponse(Response.ResponseWrapper paramResponseWrapper)
  {
    String str1 = (String)DfeApiConfig.protoLogUrlRegexp.get();
    if (getUrl().matches(str1))
      try
      {
        Log.v("DfeProto", "Response for " + getUrl());
        for (String str2 : MicroProtoPrinter.prettyPrint("ResponseWrapper", Response.ResponseWrapper.class, paramResponseWrapper).split("\n"))
          Log.v("DfeProto", "| " + str2);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    else
      Log.v("DfeProto", "Url does not match regexp: url=" + getUrl() + " / regexp=" + str1);
  }

  private String makeCacheKey(String paramString)
  {
    return 256 + paramString + "/account=" + this.mApiContext.getAccountName();
  }

  public static Cache.Entry parseCacheHeaders(NetworkResponse paramNetworkResponse)
  {
    Cache.Entry localEntry = HttpHeaderParser.parseCacheHeaders(paramNetworkResponse);
    if (localEntry == null)
      localEntry = null;
    while (true)
    {
      return localEntry;
      long l = System.currentTimeMillis();
      try
      {
        String str1 = (String)paramNetworkResponse.headers.get("X-DFE-Soft-TTL");
        if (str1 != null)
          localEntry.softTtl = (l + Long.parseLong(str1));
        String str2 = (String)paramNetworkResponse.headers.get("X-DFE-Hard-TTL");
        if (str2 != null)
          localEntry.ttl = (l + Long.parseLong(str2));
        localEntry.ttl = Math.max(localEntry.ttl, localEntry.softTtl);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = paramNetworkResponse.headers;
          DfeLog.d("Invalid TTL: %s", arrayOfObject);
          localEntry.softTtl = 0L;
          localEntry.ttl = 0L;
        }
      }
    }
  }

  private static Response.ResponseWrapper parseWrapper(NetworkResponse paramNetworkResponse, boolean paramBoolean)
  {
    if (paramBoolean);
    Object localObject;
    try
    {
      localObject = Response.ResponseWrapper.parseFrom(CodedInputStreamMicro.newInstance(new GZIPInputStream(new ByteArrayInputStream(paramNetworkResponse.data))));
      break label91;
      Response.ResponseWrapper localResponseWrapper = Response.ResponseWrapper.parseFrom(paramNetworkResponse.data);
      localObject = localResponseWrapper;
    }
    catch (InvalidProtocolBufferMicroException localInvalidProtocolBufferMicroException)
    {
      if (!paramBoolean)
      {
        localObject = parseWrapper(paramNetworkResponse, true);
      }
      else
      {
        DfeLog.d("Cannot parse response as ResponseWrapper proto.", new Object[0]);
        localObject = null;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
        DfeLog.w("IOException while manually unzipping request.", new Object[0]);
    }
    label91: return localObject;
  }

  public void addExtraHeader(String paramString1, String paramString2)
  {
    if (this.mExtraHeaders == null)
      this.mExtraHeaders = new HashMap();
    this.mExtraHeaders.put(paramString1, paramString2);
  }

  public void deliverError(VolleyError paramVolleyError)
  {
    if ((paramVolleyError instanceof AuthFailureError))
      this.mApiContext.invalidateAuthToken();
    if (!this.mResponseDelivered)
      super.deliverError(paramVolleyError);
    while (true)
    {
      return;
      DfeLog.d("Not delivering error response for request=[%s], error=[%s] because response already delivered.", new Object[] { this, paramVolleyError });
    }
  }

  public void deliverResponse(Response.ResponseWrapper paramResponseWrapper)
  {
    MessageMicro localMessageMicro = MicroProtoHelper.getParsedResponseFromWrapper(paramResponseWrapper.getPayload(), Response.Payload.class, this.mResponseClass);
    if (localMessageMicro != null)
      if ((this.mAllowMultipleResponses) || (!this.mResponseDelivered))
      {
        this.mListener.onResponse(localMessageMicro);
        this.mResponseDelivered = true;
      }
    while (true)
    {
      return;
      DfeLog.d("Not delivering second response for request=[%s]", new Object[] { this });
      continue;
      DfeLog.e("Null parsed response for request=[%s]", new Object[] { this });
      deliverError(new VolleyError());
    }
  }

  public boolean getAvoidBulkCancel()
  {
    return this.mAvoidBulkCancel;
  }

  public String getCacheKey()
  {
    return makeCacheKey(super.getUrl());
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    Map localMap = this.mApiContext.getHeaders();
    if (this.mExtraHeaders != null)
      localMap.putAll(this.mExtraHeaders);
    return localMap;
  }

  public String getUrl()
  {
    char c1 = '&';
    String str1 = super.getUrl();
    String str2 = (String)DfeApiConfig.ipCountryOverride.get();
    char c4;
    char c3;
    label134: char c2;
    label214: StringBuilder localStringBuilder1;
    if (!TextUtils.isEmpty(str2))
    {
      StringBuilder localStringBuilder4 = new StringBuilder().append(str1);
      if (str1.indexOf('?') != -1)
      {
        c4 = c1;
        String str7 = c4;
        str1 = str7 + "ipCountryOverride=" + str2;
      }
    }
    else
    {
      String str3 = (String)DfeApiConfig.mccMncOverride.get();
      if (!TextUtils.isEmpty(str3))
      {
        StringBuilder localStringBuilder3 = new StringBuilder().append(str1);
        if (str1.indexOf('?') == -1)
          break label328;
        c3 = c1;
        String str6 = c3;
        str1 = str6 + "mccmncOverride=" + str3;
      }
      if (((Boolean)DfeApiConfig.skipAllCaches.get()).booleanValue())
      {
        StringBuilder localStringBuilder2 = new StringBuilder().append(str1);
        if (str1.indexOf('?') == -1)
          break label335;
        c2 = c1;
        String str5 = c2;
        str1 = str5 + "skipCache=true";
      }
      if (((Boolean)DfeApiConfig.showStagingData.get()).booleanValue())
      {
        localStringBuilder1 = new StringBuilder().append(str1);
        if (str1.indexOf('?') == -1)
          break label342;
      }
    }
    while (true)
    {
      String str4 = c1;
      str1 = str4 + "showStagingData=true";
      return str1;
      c4 = '?';
      break;
      label328: c3 = '?';
      break label134;
      label335: c2 = '?';
      break label214;
      label342: c1 = '?';
    }
  }

  public void handleNotifications(Response.ResponseWrapper paramResponseWrapper)
  {
    if ((this.mApiContext.getNotificationManager() == null) || (paramResponseWrapper.getNotificationCount() == 0));
    while (true)
    {
      return;
      Iterator localIterator = paramResponseWrapper.getNotificationList().iterator();
      while (localIterator.hasNext())
      {
        Notifications.Notification localNotification = (Notifications.Notification)localIterator.next();
        this.mApiContext.getNotificationManager().processNotification(localNotification);
      }
    }
  }

  protected VolleyError parseNetworkError(VolleyError paramVolleyError)
  {
    if (((paramVolleyError instanceof ServerError)) && (paramVolleyError.networkResponse != null))
    {
      Response.ResponseWrapper localResponseWrapper = parseWrapper(paramVolleyError.networkResponse, false);
      if (localResponseWrapper != null)
        paramVolleyError = handleServerCommands(localResponseWrapper).error;
    }
    return paramVolleyError;
  }

  public Response<Response.ResponseWrapper> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    if (DEBUG)
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramNetworkResponse.data.length);
      DfeLog.v("Response size: %d", arrayOfObject2);
    }
    Response.ResponseWrapper localResponseWrapper = parseWrapper(paramNetworkResponse, false);
    Object localObject;
    if (localResponseWrapper == null)
      localObject = Response.error(new ParseError(paramNetworkResponse));
    while (true)
    {
      return localObject;
      if (PROTO_DEBUG)
        logProtoResponse(localResponseWrapper);
      localObject = handleServerCommands(localResponseWrapper);
      if (localObject == null)
      {
        handleNotifications(localResponseWrapper);
        Cache.Entry localEntry = parseCacheHeaders(paramNetworkResponse);
        if (localEntry != null)
          stripForCache(localResponseWrapper, localEntry);
        Response localResponse = Response.success(localResponseWrapper, localEntry);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = getUrl();
        DfeLog.logTiming("DFE response %s", arrayOfObject1);
        localObject = localResponse;
      }
    }
  }

  public void setAllowMultipleResponses(boolean paramBoolean)
  {
    this.mAllowMultipleResponses = paramBoolean;
  }

  public void setAvoidBulkCancel()
  {
    this.mAvoidBulkCancel = true;
  }

  void stripForCache(Response.ResponseWrapper paramResponseWrapper, Cache.Entry paramEntry)
  {
    if ((paramResponseWrapper.getPreFetchCount() < 1) && (!paramResponseWrapper.hasCommands()) && (paramResponseWrapper.getNotificationCount() < 1));
    while (true)
    {
      return;
      Cache localCache = this.mApiContext.getCache();
      long l = System.currentTimeMillis();
      Iterator localIterator = paramResponseWrapper.getPreFetchList().iterator();
      while (localIterator.hasNext())
      {
        Response.PreFetch localPreFetch = (Response.PreFetch)localIterator.next();
        Cache.Entry localEntry = new Cache.Entry();
        localEntry.data = localPreFetch.getResponse().toByteArray();
        localEntry.etag = localPreFetch.getEtag();
        localEntry.serverDate = paramEntry.serverDate;
        localEntry.ttl = (l + localPreFetch.getTtl());
        localEntry.softTtl = (l + localPreFetch.getSoftTtl());
        localCache.put(makeCacheKey(Uri.withAppendedPath(DfeApi.BASE_URI, localPreFetch.getUrl()).toString()), localEntry);
      }
      paramResponseWrapper.clearPreFetch();
      paramResponseWrapper.clearCommands();
      paramResponseWrapper.clearNotification();
      paramEntry.data = paramResponseWrapper.toByteArray();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.DfeRequest
 * JD-Core Version:    0.6.2
 */
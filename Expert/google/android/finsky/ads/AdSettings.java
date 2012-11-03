package com.google.android.finsky.ads;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.utils.FinskyLog;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class AdSettings
{
  private final Context mContext;
  private final RequestQueue mRequestQueue;

  public AdSettings(Context paramContext, RequestQueue paramRequestQueue)
  {
    this.mContext = paramContext;
    this.mRequestQueue = paramRequestQueue;
  }

  private String getSettingsUrl(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("http://www.google.com/ads/preferences/");
    String str1;
    String str2;
    String str3;
    if (paramBoolean)
    {
      str1 = "mobile_optin";
      str2 = str1;
      str3 = getSigString(this.mContext);
      if (str3 != null)
        break label56;
    }
    label56: for (String str4 = null; ; str4 = str2 + "?sig=" + str3 + "&vv=" + 2)
    {
      return str4;
      str1 = "mobile_optout";
      break;
    }
  }

  public static String getSigString(Context paramContext)
  {
    Object localObject = null;
    try
    {
      String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (str1 != null)
      {
        String str2 = Base64.encodeToString(Crypto.encryptMobileId(2, (int)(System.currentTimeMillis() / 1000L), str1), 11);
        localObject = str2;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return localObject;
  }

  public void enableInterestBasedAds(boolean paramBoolean, Response.Listener<Boolean> paramListener, Response.ErrorListener paramErrorListener)
  {
    String str = getSettingsUrl(paramBoolean);
    if (str == null)
      paramErrorListener.onErrorResponse(new VolleyError());
    while (true)
    {
      return;
      AdPrefsRequest localAdPrefsRequest = new AdPrefsRequest(str, paramListener, paramErrorListener);
      this.mRequestQueue.add(localAdPrefsRequest);
    }
  }

  private class AdPrefsRequest extends Request<Boolean>
  {
    private final Response.Listener<Boolean> mListener;

    public AdPrefsRequest(Response.Listener<Boolean> paramErrorListener, Response.ErrorListener arg3)
    {
      super(localErrorListener);
      Object localObject;
      this.mListener = localObject;
    }

    protected void deliverResponse(Boolean paramBoolean)
    {
      this.mListener.onResponse(paramBoolean);
    }

    protected Response<Boolean> parseNetworkResponse(NetworkResponse paramNetworkResponse)
    {
      String str = (String)paramNetworkResponse.headers.get("X-Mobile-PrefMgr");
      Response localResponse;
      if ("OPTED_IN".equals(str))
        localResponse = Response.success(Boolean.valueOf(true), null);
      while (true)
      {
        return localResponse;
        if ("OPTED_OUT".equals(str))
        {
          localResponse = Response.success(Boolean.valueOf(false), null);
        }
        else
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = str;
          arrayOfObject[1] = getUrl();
          FinskyLog.d("result header %s for %s", arrayOfObject);
          localResponse = Response.error(new ParseError());
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.ads.AdSettings
 * JD-Core Version:    0.6.2
 */
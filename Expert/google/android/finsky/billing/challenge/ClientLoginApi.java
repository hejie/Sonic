package com.google.android.finsky.billing.challenge;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ClientLoginApi
{
  private static String CLIENT_LOGIN_URI = "https://www.google.com/accounts/ClientLogin";
  private static String REQUEST_PARAM_ACCOUNT_TYPE = "accountType";
  private static String REQUEST_PARAM_EMAIL = "Email";
  private static String REQUEST_PARAM_PASSWD = "Passwd";
  private static String REQUEST_PARAM_SERVICE = "service";
  private static String REQUEST_PARAM_SOURCE = "source";
  private static String REQUEST_VALUE_ACCOUNT_TYPE = "HOSTED_OR_GOOGLE";
  private static String REQUEST_VALUE_SERVICE = "apps";
  private static String REQUEST_VALUE_SOURCE = "Google-GooglePlay-";
  private static String RESULT_ERROR_BAD_AUTH = "Error=BadAuthentication";
  private static String RESULT_ERROR_CAPTCHA_REQUIRED = "Error=CaptchaRequired";
  private static String RESULT_ERROR_INFO_TWO_FACTOR = "Info=InvalidSecondFactor";
  private final RequestQueue mQueue;

  public ClientLoginApi(RequestQueue paramRequestQueue)
  {
    this.mQueue = paramRequestQueue;
  }

  public Request<?> validateUser(String paramString1, final String paramString2, final ClientLoginListener paramClientLoginListener)
  {
    ClientLoginRequest localClientLoginRequest = new ClientLoginRequest(CLIENT_LOGIN_URI, paramString1, paramString2, new Response.Listener()
    {
      public void onResponse(NetworkResponse paramAnonymousNetworkResponse)
      {
        if (paramAnonymousNetworkResponse.statusCode != 200)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramAnonymousNetworkResponse.statusCode);
          FinskyLog.wtf("Success response called with %d code", arrayOfObject);
        }
        paramClientLoginListener.onAuthSuccess();
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ClientLoginApi.ErrorType localErrorType = ClientLoginApi.ErrorType.UNKNOWN;
        try
        {
          String str = new String(paramAnonymousVolleyError.networkResponse.data, HttpHeaderParser.parseCharset(paramAnonymousVolleyError.networkResponse.headers));
          if ((str.contains(ClientLoginApi.RESULT_ERROR_BAD_AUTH)) && (str.contains(ClientLoginApi.RESULT_ERROR_INFO_TWO_FACTOR)))
            localErrorType = ClientLoginApi.ErrorType.TWO_FACTOR;
          while (true)
          {
            paramClientLoginListener.onAuthFailure(paramString2, localErrorType);
            return;
            if (str.contains(ClientLoginApi.RESULT_ERROR_CAPTCHA_REQUIRED))
              localErrorType = ClientLoginApi.ErrorType.CAPTCHA;
            else
              localErrorType = ClientLoginApi.ErrorType.UNKNOWN;
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          while (true)
            FinskyLog.e("Unsupported encoding %s", new Object[] { localUnsupportedEncodingException });
        }
      }
    });
    return this.mQueue.add(localClientLoginRequest);
  }

  public static abstract interface ClientLoginListener
  {
    public abstract void onAuthFailure(String paramString, ClientLoginApi.ErrorType paramErrorType);

    public abstract void onAuthSuccess();
  }

  private class ClientLoginRequest extends Request<NetworkResponse>
  {
    private final Map<String, String> mPostParams = Maps.newHashMap();
    private final Response.Listener<NetworkResponse> mResponseListener;

    public ClientLoginRequest(String paramString1, String paramListener, Response.Listener<NetworkResponse> paramErrorListener, Response.ErrorListener arg5)
    {
      super(localErrorListener);
      Object localObject;
      this.mResponseListener = localObject;
      this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_ACCOUNT_TYPE, ClientLoginApi.REQUEST_VALUE_ACCOUNT_TYPE);
      this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_EMAIL, paramListener);
      this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_PASSWD, paramErrorListener);
      this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_SERVICE, ClientLoginApi.REQUEST_VALUE_SERVICE);
      this.mPostParams.put(ClientLoginApi.REQUEST_PARAM_SOURCE, ClientLoginApi.REQUEST_VALUE_SOURCE + FinskyApp.get().getVersionCode());
    }

    protected void deliverResponse(NetworkResponse paramNetworkResponse)
    {
      this.mResponseListener.onResponse(paramNetworkResponse);
    }

    public Map<String, String> getPostParams()
    {
      return this.mPostParams;
    }

    public Request.Priority getPriority()
    {
      return Request.Priority.HIGH;
    }

    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse paramNetworkResponse)
    {
      return Response.success(paramNetworkResponse, null);
    }
  }

  public static enum ErrorType
  {
    static
    {
      TWO_FACTOR = new ErrorType("TWO_FACTOR", 1);
      CAPTCHA = new ErrorType("CAPTCHA", 2);
      ErrorType[] arrayOfErrorType = new ErrorType[3];
      arrayOfErrorType[0] = UNKNOWN;
      arrayOfErrorType[1] = TWO_FACTOR;
      arrayOfErrorType[2] = CAPTCHA;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.challenge.ClientLoginApi
 * JD-Core Version:    0.6.2
 */
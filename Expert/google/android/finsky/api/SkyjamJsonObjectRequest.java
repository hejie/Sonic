package com.google.android.finsky.api;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.config.GservicesValue;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SkyjamJsonObjectRequest extends JsonObjectRequest
{
  public SkyjamJsonObjectRequest(String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramString, paramJSONObject, paramListener, paramErrorListener);
  }

  public Map<String, String> getHeaders()
    throws AuthFailureError
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("X-Device-Logging-ID", DfeApiConfig.loggingId.get());
    localHashMap.put("X-Device-ID", Long.toHexString(((Long)DfeApiConfig.androidId.get()).longValue()));
    return localHashMap;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.SkyjamJsonObjectRequest
 * JD-Core Version:    0.6.2
 */
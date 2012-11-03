package com.google.android.finsky.billing.carrierbilling;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{
  public static Boolean getBoolean(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      Boolean localBoolean2 = Boolean.valueOf(paramJSONObject.getBoolean(paramString));
      localBoolean1 = localBoolean2;
      return localBoolean1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        Boolean localBoolean1 = null;
    }
  }

  public static Integer getInt(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = null;
    try
    {
      Integer localInteger = Integer.valueOf(Integer.parseInt(paramJSONObject.getString(paramString)));
      localObject = localInteger;
      label18: return localObject;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label18;
    }
    catch (JSONException localJSONException)
    {
      break label18;
    }
  }

  public static Long getLong(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = null;
    try
    {
      Long localLong = Long.valueOf(Long.parseLong(paramJSONObject.getString(paramString)));
      localObject = localLong;
      label18: return localObject;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label18;
    }
    catch (JSONException localJSONException)
    {
      break label18;
    }
  }

  public static JSONObject getObject(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      JSONObject localJSONObject2 = paramJSONObject.getJSONObject(paramString);
      localJSONObject1 = localJSONObject2;
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        JSONObject localJSONObject1 = null;
    }
  }

  public static String getString(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      str = paramJSONObject.getString(paramString);
      boolean bool = "null".equals(str);
      if (bool)
        str = null;
      return str;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        String str = null;
    }
  }

  public static JSONObject toLowerCase(JSONObject paramJSONObject)
    throws JSONException
  {
    Iterator localIterator = paramJSONObject.keys();
    JSONObject localJSONObject = new JSONObject();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramJSONObject.get(str);
      if ((localObject instanceof JSONObject))
        localObject = toLowerCase((JSONObject)localObject);
      localJSONObject.put(str.toLowerCase(), localObject);
    }
    return localJSONObject;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.JsonUtils
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.carrierbilling.api;

import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.billing.carrierbilling.JsonUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials.Builder;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning.Builder;
import com.google.android.finsky.billing.carrierbilling.model.EncryptedSubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.EncryptedSubscriberInfo.Builder;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo.Builder;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import org.json.JSONException;
import org.json.JSONObject;

public class DcbApi
{
  private static final int DCB_TIMEOUT_MS = ((Integer)DfeApiConfig.purchaseStatusTimeoutMs.get()).intValue();
  private final DcbApiContext mDcbContext;
  private final RequestQueue mRequestQueue;

  public DcbApi(RequestQueue paramRequestQueue, DcbApiContext paramDcbApiContext)
  {
    this.mRequestQueue = paramRequestQueue;
    this.mDcbContext = paramDcbApiContext;
  }

  static CarrierBillingCredentials buildCredentials(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = JsonUtils.toLowerCase(paramJSONObject);
      Integer localInteger = JsonUtils.getInt(localJSONObject, "version");
      Long localLong = JsonUtils.getLong(localJSONObject, "credentialexpirationtime");
      Boolean localBoolean1 = JsonUtils.getBoolean(localJSONObject, "isprovisioned");
      Boolean localBoolean2 = JsonUtils.getBoolean(localJSONObject, "passwordinvalid");
      CarrierBillingCredentials.Builder localBuilder1 = new CarrierBillingCredentials.Builder();
      int i;
      long l;
      label88: boolean bool1;
      label109: CarrierBillingCredentials.Builder localBuilder4;
      if (localInteger != null)
      {
        i = localInteger.intValue();
        CarrierBillingCredentials.Builder localBuilder2 = localBuilder1.setApiVersion(i).setCredentials(JsonUtils.getString(localJSONObject, "credential"));
        if (localLong == null)
          break label153;
        l = localLong.longValue();
        CarrierBillingCredentials.Builder localBuilder3 = localBuilder2.setExpirationTime(l);
        if (localBoolean1 == null)
          break label159;
        bool1 = localBoolean1.booleanValue();
        localBuilder4 = localBuilder3.setIsProvisioned(bool1);
        if (localBoolean2 == null)
          break label165;
      }
      label153: label159: label165: for (boolean bool2 = localBoolean2.booleanValue(); ; bool2 = false)
      {
        CarrierBillingCredentials localCarrierBillingCredentials2 = localBuilder4.setInvalidPassword(bool2).build();
        localCarrierBillingCredentials1 = localCarrierBillingCredentials2;
        return localCarrierBillingCredentials1;
        i = 0;
        break;
        l = 0L;
        break label88;
        bool1 = false;
        break label109;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        FinskyLog.e("JSON Exception while building credentials", new Object[0]);
        CarrierBillingCredentials localCarrierBillingCredentials1 = null;
      }
    }
  }

  private static EncryptedSubscriberInfo buildEncryptedSubscriberInfo(JSONObject paramJSONObject)
  {
    int i = 0;
    EncryptedSubscriberInfo localEncryptedSubscriberInfo;
    if (paramJSONObject == null)
    {
      localEncryptedSubscriberInfo = null;
      return localEncryptedSubscriberInfo;
    }
    Integer localInteger1 = JsonUtils.getInt(paramJSONObject, "googlekeyversion");
    Integer localInteger2 = JsonUtils.getInt(paramJSONObject, "carrierkeyversion");
    EncryptedSubscriberInfo.Builder localBuilder1 = new EncryptedSubscriberInfo.Builder().setMessage(JsonUtils.getString(paramJSONObject, "message")).setEncryptedKey(JsonUtils.getString(paramJSONObject, "encryptedkey")).setSignature(JsonUtils.getString(paramJSONObject, "signature")).setInitVector(JsonUtils.getString(paramJSONObject, "initvector"));
    if (localInteger1 != null);
    for (int j = localInteger1.intValue(); ; j = 0)
    {
      EncryptedSubscriberInfo.Builder localBuilder2 = localBuilder1.setGoogleKeyVersion(j);
      if (localInteger2 != null)
        i = localInteger2.intValue();
      localEncryptedSubscriberInfo = localBuilder2.setCarrierKeyVersion(i).build();
      break;
    }
  }

  static CarrierBillingProvisioning buildProvisioning(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = JsonUtils.toLowerCase(paramJSONObject);
      Boolean localBoolean1 = JsonUtils.getBoolean(localJSONObject, "isprovisioned");
      Long localLong = JsonUtils.getLong(localJSONObject, "transactionlimit");
      Boolean localBoolean2 = JsonUtils.getBoolean(localJSONObject, "passwordrequired");
      SubscriberInfo localSubscriberInfo = buildSubscriberInfo(JsonUtils.getString(localJSONObject, "subscribername"), JsonUtils.getString(localJSONObject, "subscriberidentifier"), JsonUtils.getObject(localJSONObject, "subscriberaddress"));
      EncryptedSubscriberInfo localEncryptedSubscriberInfo = buildEncryptedSubscriberInfo(JsonUtils.getObject(localJSONObject, "encryptedsubscriberinfo"));
      CarrierBillingProvisioning.Builder localBuilder1 = new CarrierBillingProvisioning.Builder().setApiVersion(JsonUtils.getInt(localJSONObject, "version").intValue());
      boolean bool1;
      long l;
      label153: CarrierBillingProvisioning.Builder localBuilder3;
      if (localBoolean1 != null)
      {
        bool1 = localBoolean1.booleanValue();
        CarrierBillingProvisioning.Builder localBuilder2 = localBuilder1.setIsProvisioned(bool1).setProvisioningId(JsonUtils.getString(localJSONObject, "provisioningid")).setTosUrl(JsonUtils.getString(localJSONObject, "tosurl")).setTosVersion(JsonUtils.getString(localJSONObject, "tosversion")).setSubscriberCurrency(JsonUtils.getString(localJSONObject, "subscribercurrency"));
        if (localLong == null)
          break label260;
        l = localLong.longValue();
        localBuilder3 = localBuilder2.setTransactionLimit(l).setAccountType(JsonUtils.getString(localJSONObject, "accounttype")).setSubscriberInfo(localSubscriberInfo).setCredentials(buildCredentials(localJSONObject));
        if (localBoolean2 == null)
          break label266;
      }
      label260: label266: for (boolean bool2 = localBoolean2.booleanValue(); ; bool2 = false)
      {
        CarrierBillingProvisioning localCarrierBillingProvisioning2 = localBuilder3.setPasswordRequired(bool2).setPasswordPrompt(JsonUtils.getString(localJSONObject, "passwordprompt")).setPasswordForgotUrl(JsonUtils.getString(localJSONObject, "passwordforgoturl")).setEncryptedSubscriberInfo(localEncryptedSubscriberInfo).setAddressSnippet(JsonUtils.getString(localJSONObject, "addresssnippet")).setCountry(JsonUtils.getString(localJSONObject, "country")).build();
        localCarrierBillingProvisioning1 = localCarrierBillingProvisioning2;
        return localCarrierBillingProvisioning1;
        bool1 = false;
        break;
        l = 0L;
        break label153;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        FinskyLog.e("JSON Exception while building provisioning", new Object[0]);
        CarrierBillingProvisioning localCarrierBillingProvisioning1 = null;
      }
    }
  }

  private static SubscriberInfo buildSubscriberInfo(String paramString1, String paramString2, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    for (SubscriberInfo localSubscriberInfo = null; ; localSubscriberInfo = new SubscriberInfo.Builder().setName(paramString1).setIdentifier(paramString2).setAddress1(JsonUtils.getString(paramJSONObject, "address1")).setAddress2(JsonUtils.getString(paramJSONObject, "address2")).setCity(JsonUtils.getString(paramJSONObject, "city")).setState(JsonUtils.getString(paramJSONObject, "state")).setPostalCode(JsonUtils.getString(paramJSONObject, "postalcode")).setCountry(JsonUtils.getString(paramJSONObject, "country")).build())
      return localSubscriberInfo;
  }

  JSONObject getBaseParametersAsJsonObject()
    throws JSONException
  {
    CarrierBillingParameters localCarrierBillingParameters = this.mDcbContext.getCarrierBillingParameters();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("format", "json");
    Integer localInteger = Integer.valueOf(localCarrierBillingParameters.getCarrierApiVersion());
    if ((localInteger == null) || (localInteger.intValue() <= 0))
      localInteger = Integer.valueOf(1);
    localJSONObject.put("version", localInteger);
    if (localCarrierBillingParameters.sendSubscriberInfoWithCarrierRequests())
    {
      String str1 = this.mDcbContext.getLine1Number();
      if (!TextUtils.isEmpty(str1))
        localJSONObject.put("line1Number", str1);
      String str2 = this.mDcbContext.getSubscriberId();
      if (!TextUtils.isEmpty(str2))
        localJSONObject.put("subscriberId", str2);
    }
    return localJSONObject;
  }

  public Request<?> getCredentials(String paramString1, String paramString2, Response.Listener<CarrierBillingCredentials> paramListener, Response.ErrorListener paramErrorListener)
  {
    CarrierBillingParameters localCarrierBillingParameters = this.mDcbContext.getCarrierBillingParameters();
    JSONObject localJSONObject = getCredentialsParametersAsJsonObject(paramString1, paramString2);
    JsonObjectRequest localJsonObjectRequest = new JsonObjectRequest(localCarrierBillingParameters.getGetCredentialsUrl(), localJSONObject, new CredentialsJsonConverter(paramListener), new RequestQueueErrorListener(paramErrorListener));
    localJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DCB_TIMEOUT_MS, 0, 0.0F));
    this.mRequestQueue.start();
    return this.mRequestQueue.add(localJsonObjectRequest);
  }

  JSONObject getCredentialsParametersAsJsonObject(String paramString1, String paramString2)
  {
    try
    {
      localJSONObject = getBaseParametersAsJsonObject();
      if (paramString1 != null)
        localJSONObject.put("provisioningId", paramString1);
      if (!TextUtils.isEmpty(paramString2))
        localJSONObject.put("password", paramString2);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        FinskyLog.e("JSONException while creating credentials request: %s", new Object[] { localJSONException });
        JSONObject localJSONObject = null;
      }
    }
  }

  public Request<?> getProvisioning(String paramString, Response.Listener<CarrierBillingProvisioning> paramListener, Response.ErrorListener paramErrorListener)
  {
    CarrierBillingParameters localCarrierBillingParameters = this.mDcbContext.getCarrierBillingParameters();
    if (((Boolean)G.vendingCarrierProvisioningUseTosVersion.get()).booleanValue());
    for (JSONObject localJSONObject = getProvisioningParametersAsJsonObject(paramString); ; localJSONObject = getProvisioningParametersAsJsonObject(null))
    {
      JsonObjectRequest localJsonObjectRequest = new JsonObjectRequest(localCarrierBillingParameters.getGetProvisioningUrl(), localJSONObject, new ProvisioningJsonConverter(paramListener), new RequestQueueErrorListener(paramErrorListener));
      localJsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DCB_TIMEOUT_MS, 0, 0.0F));
      this.mRequestQueue.start();
      return this.mRequestQueue.add(localJsonObjectRequest);
    }
  }

  JSONObject getProvisioningParametersAsJsonObject(String paramString)
  {
    try
    {
      localJSONObject = getBaseParametersAsJsonObject();
      if (paramString != null)
        localJSONObject.put("acceptedTosVersion", paramString);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        FinskyLog.e("JSONException while creating provisioning request: %s", new Object[] { localJSONException });
        JSONObject localJSONObject = null;
      }
    }
  }

  private class CredentialsJsonConverter
    implements Response.Listener<JSONObject>
  {
    private final Response.Listener<CarrierBillingCredentials> mListener;

    public CredentialsJsonConverter()
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void onResponse(JSONObject paramJSONObject)
    {
      DcbApi.this.mRequestQueue.stop();
      CarrierBillingCredentials localCarrierBillingCredentials = DcbApi.buildCredentials(paramJSONObject);
      this.mListener.onResponse(localCarrierBillingCredentials);
    }
  }

  private class ProvisioningJsonConverter
    implements Response.Listener<JSONObject>
  {
    private final Response.Listener<CarrierBillingProvisioning> mListener;

    public ProvisioningJsonConverter()
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void onResponse(JSONObject paramJSONObject)
    {
      DcbApi.this.mRequestQueue.stop();
      CarrierBillingProvisioning localCarrierBillingProvisioning = DcbApi.buildProvisioning(paramJSONObject);
      this.mListener.onResponse(localCarrierBillingProvisioning);
    }
  }

  private class RequestQueueErrorListener
    implements Response.ErrorListener
  {
    private final Response.ErrorListener mErrorListener;

    public RequestQueueErrorListener(Response.ErrorListener arg2)
    {
      Object localObject;
      this.mErrorListener = localObject;
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      DcbApi.this.mRequestQueue.stop();
      this.mErrorListener.onErrorResponse(paramVolleyError);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.api.DcbApi
 * JD-Core Version:    0.6.2
 */
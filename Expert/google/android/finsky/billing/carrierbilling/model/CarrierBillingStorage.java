package com.google.android.finsky.billing.carrierbilling.model;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.utils.Md5Util;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.finsky.utils.persistence.FileBasedKeyValueStore;
import com.google.android.finsky.utils.persistence.WriteThroughKeyValueStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrierBillingStorage
{
  private final String mCurrentSimIdentifier = initCurrentSimIdentifier();
  private volatile boolean mIsInit = false;
  private final WriteThroughKeyValueStore mStore = new WriteThroughKeyValueStore(new FileBasedKeyValueStore(paramContext.getDir("carrier_billing", 0), this.mCurrentSimIdentifier));

  public CarrierBillingStorage(Context paramContext)
  {
  }

  private String booleanToString(Boolean paramBoolean)
  {
    if (paramBoolean == null);
    for (String str = null; ; str = Boolean.toString(paramBoolean.booleanValue()))
      return str;
  }

  private String getParamsKey()
  {
    if (FinskyApp.get().getCurrentAccountName() == null);
    for (String str = "params"; ; str = "params" + Md5Util.secureHash(FinskyApp.get().getCurrentAccountName().getBytes()))
      return str;
  }

  private String initCurrentSimIdentifier()
  {
    String str1 = BillingLocator.getSubscriberIdFromTelephony();
    String str3;
    if (str1 != null)
      str3 = Sha1Util.secureHash(str1.getBytes());
    while (true)
    {
      return str3;
      String str2 = BillingLocator.getDeviceIdFromTelephony();
      if (str2 != null)
        str3 = Sha1Util.secureHash(str2.getBytes());
      else
        str3 = "invalid_sim_id";
    }
  }

  private String integerToString(Integer paramInteger)
  {
    if (paramInteger == null);
    for (String str = null; ; str = Integer.toString(paramInteger.intValue()))
      return str;
  }

  private String longToString(Long paramLong)
  {
    if (paramLong == null);
    for (String str = null; ; str = Long.toString(paramLong.longValue()))
      return str;
  }

  private Boolean stringToBoolean(String paramString)
  {
    if (paramString == null);
    for (Boolean localBoolean = null; ; localBoolean = Boolean.valueOf(Boolean.parseBoolean(paramString)))
      return localBoolean;
  }

  private Integer stringToInteger(String paramString)
  {
    if (paramString == null);
    for (Integer localInteger = null; ; localInteger = Integer.valueOf(Integer.parseInt(paramString)))
      return localInteger;
  }

  private Long stringToLong(String paramString)
  {
    if (paramString == null);
    for (Long localLong = null; ; localLong = Long.valueOf(Long.parseLong(paramString)))
      return localLong;
  }

  public void clearCredentials()
  {
    this.mStore.delete("credentials");
  }

  public void clearParams()
  {
    this.mStore.delete(getParamsKey());
  }

  public void clearProvisioning()
  {
    this.mStore.delete("provisioning");
  }

  public CarrierBillingCredentials getCredentials()
  {
    if (!isInit())
      throw new IllegalStateException("Attempt to fetch credentials when key store isn't ready.");
    Map localMap = this.mStore.get("credentials");
    if (localMap == null);
    CarrierBillingCredentials.Builder localBuilder;
    for (CarrierBillingCredentials localCarrierBillingCredentials = null; ; localCarrierBillingCredentials = localBuilder.build())
    {
      return localCarrierBillingCredentials;
      localBuilder = new CarrierBillingCredentials.Builder().setCredentials((String)localMap.get("credentials"));
      Integer localInteger = stringToInteger((String)localMap.get("api_version"));
      if (localInteger != null)
        localBuilder.setApiVersion(localInteger.intValue());
      Long localLong = stringToLong((String)localMap.get("expiration_time"));
      if (localLong != null)
        localBuilder.setExpirationTime(localLong.longValue());
      Boolean localBoolean1 = stringToBoolean((String)localMap.get("is_provisioned"));
      if (localBoolean1 != null)
        localBuilder.setIsProvisioned(localBoolean1.booleanValue());
      Boolean localBoolean2 = stringToBoolean((String)localMap.get("invalid_password"));
      if (localBoolean2 != null)
        localBuilder.setInvalidPassword(localBoolean2.booleanValue());
    }
  }

  public String getCurrentSimIdentifier()
  {
    return this.mCurrentSimIdentifier;
  }

  public CarrierBillingParameters getParams()
  {
    if (!isInit())
      throw new IllegalStateException("Attempt to fetch params when key store isn't ready.");
    Map localMap = this.mStore.get(getParamsKey());
    if (localMap == null);
    CarrierBillingParameters.Builder localBuilder;
    for (CarrierBillingParameters localCarrierBillingParameters = null; ; localCarrierBillingParameters = localBuilder.build())
    {
      return localCarrierBillingParameters;
      localBuilder = new CarrierBillingParameters.Builder().setId((String)localMap.get("carrier_id")).setName((String)localMap.get("carrier_name")).setMncMccs(stringToList((String)localMap.get("mnc_mcc_csv"))).setGetProvisioningUrl((String)localMap.get("get_provisioning_url")).setGetCredentialsUrl((String)localMap.get("get_credentials_url")).setCarrierIconId((String)localMap.get("carrier_icon_id"));
      Integer localInteger1 = stringToInteger((String)localMap.get("carrier_api_version"));
      if (localInteger1 != null)
        localBuilder.setCarrierApiVersion(localInteger1.intValue());
      Boolean localBoolean1 = stringToBoolean((String)localMap.get("show_carrier_tos"));
      if (localBoolean1 != null)
        localBuilder.setShowCarrierTos(localBoolean1.booleanValue());
      Boolean localBoolean2 = stringToBoolean((String)localMap.get("per_transaction_credentials_required"));
      if (localBoolean2 != null)
        localBuilder.setPerTransactionCredentialsRequired(localBoolean2.booleanValue());
      Boolean localBoolean3 = stringToBoolean((String)localMap.get("per_transaction_credentials_required"));
      if (localBoolean3 != null)
        localBuilder.setSendSubscriberInfoWithCarrierRequests(localBoolean3.booleanValue());
      Boolean localBoolean4 = stringToBoolean((String)localMap.get("password_required"));
      if (localBoolean4 != null)
        localBuilder.setPasswordRequired(localBoolean4.booleanValue());
      Integer localInteger2 = stringToInteger((String)localMap.get("association_method"));
      if (localInteger2 != null)
        localBuilder.setAssociationMethod(localInteger2.intValue());
      String str1 = (String)localMap.get("user_token_request_address");
      if (localInteger2 != null)
        localBuilder.setRequestUserTokenTo(str1);
      String str2 = (String)localMap.get("user_token_request_message");
      if (localInteger2 != null)
        localBuilder.setRequestUserTokenText(str2);
      String str3 = (String)localMap.get("customer_support");
      if (localInteger2 != null)
        localBuilder.setCustomerSupport(str3);
    }
  }

  public CarrierBillingProvisioning getProvisioning()
  {
    if (!isInit())
      throw new IllegalStateException("Attempt to fetch provisioning when key store isn't ready.");
    Map localMap = this.mStore.get("provisioning");
    CarrierBillingCredentials localCarrierBillingCredentials = getCredentials();
    CarrierBillingProvisioning localCarrierBillingProvisioning;
    if (localMap == null)
      if (localCarrierBillingCredentials != null)
        localCarrierBillingProvisioning = new CarrierBillingProvisioning.Builder().setCredentials(localCarrierBillingCredentials).build();
    while (true)
    {
      return localCarrierBillingProvisioning;
      localCarrierBillingProvisioning = null;
      continue;
      CarrierBillingProvisioning.Builder localBuilder = new CarrierBillingProvisioning.Builder();
      localBuilder.setProvisioningId((String)localMap.get("id")).setTosUrl((String)localMap.get("tos_url")).setTosVersion((String)localMap.get("tos_version")).setSubscriberCurrency((String)localMap.get("subscriber_currency")).setAccountType((String)localMap.get("account_type")).setPasswordPrompt((String)localMap.get("password_prompt")).setPasswordForgotUrl((String)localMap.get("password_forgot_url")).setAddressSnippet((String)localMap.get("address_snippet")).setCountry((String)localMap.get("country")).setCredentials(localCarrierBillingCredentials);
      Integer localInteger1 = stringToInteger((String)localMap.get("api_version"));
      if (localInteger1 != null)
        localBuilder.setApiVersion(localInteger1.intValue());
      Boolean localBoolean1 = stringToBoolean((String)localMap.get("is_provisioned"));
      if (localBoolean1 != null)
        localBuilder.setIsProvisioned(localBoolean1.booleanValue());
      Long localLong = stringToLong((String)localMap.get("transaction_limit"));
      if (localLong != null)
        localBuilder.setTransactionLimit(localLong.longValue());
      Boolean localBoolean2 = stringToBoolean((String)localMap.get("password_required"));
      if (localBoolean2 != null)
        localBuilder.setPasswordRequired(localBoolean2.booleanValue());
      String str = (String)localMap.get("subscriber_token");
      if (!TextUtils.isEmpty(str))
      {
        SubscriberInfo localSubscriberInfo = SubscriberInfo.fromObfuscatedString(str);
        if (localSubscriberInfo != null)
          localBuilder.setSubscriberInfo(localSubscriberInfo);
      }
      EncryptedSubscriberInfo.Builder localBuilder1 = new EncryptedSubscriberInfo.Builder().setMessage((String)localMap.get("encrypted_message")).setEncryptedKey((String)localMap.get("encrypted_key")).setSignature((String)localMap.get("encrypted_signature")).setInitVector((String)localMap.get("encrypted_init_vector"));
      Integer localInteger2 = stringToInteger((String)localMap.get("encrypted_google_key_version"));
      if (localInteger2 != null)
        localBuilder1.setGoogleKeyVersion(localInteger2.intValue());
      Integer localInteger3 = stringToInteger((String)localMap.get("encrypted_carrier_key_version"));
      if (localInteger3 != null)
        localBuilder1.setCarrierKeyVersion(localInteger3.intValue());
      EncryptedSubscriberInfo localEncryptedSubscriberInfo = localBuilder1.build();
      if (!localEncryptedSubscriberInfo.isEmpty())
        localBuilder.setEncryptedSubscriberInfo(localEncryptedSubscriberInfo);
      localCarrierBillingProvisioning = localBuilder.build();
    }
  }

  public void init(final Runnable paramRunnable)
  {
    this.mStore.load(new Runnable()
    {
      public void run()
      {
        CarrierBillingStorage.this.setInit(true);
        if (paramRunnable != null)
          paramRunnable.run();
      }
    });
  }

  public boolean isInit()
  {
    return this.mIsInit;
  }

  String listToString(List<String> paramList)
  {
    if (paramList == null);
    for (String str = null; ; str = TextUtils.join(",", paramList))
      return str;
  }

  public void setCredentials(CarrierBillingCredentials paramCarrierBillingCredentials)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("credentials", paramCarrierBillingCredentials.getCredentials());
    localHashMap.put("expiration_time", longToString(Long.valueOf(paramCarrierBillingCredentials.getExpirationTime())));
    localHashMap.put("is_provisioned", booleanToString(Boolean.valueOf(paramCarrierBillingCredentials.isProvisioned())));
    localHashMap.put("invalid_password", booleanToString(Boolean.valueOf(paramCarrierBillingCredentials.invalidPassword())));
    this.mStore.put("credentials", localHashMap);
  }

  void setInit(boolean paramBoolean)
  {
    this.mIsInit = paramBoolean;
  }

  public void setParams(CarrierBillingParameters paramCarrierBillingParameters)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("carrier_id", paramCarrierBillingParameters.getId());
    localHashMap.put("carrier_name", paramCarrierBillingParameters.getName());
    localHashMap.put("mnc_mcc_csv", listToString(paramCarrierBillingParameters.getMncMccs()));
    localHashMap.put("get_provisioning_url", paramCarrierBillingParameters.getGetProvisioningUrl());
    localHashMap.put("get_credentials_url", paramCarrierBillingParameters.getGetCredentialsUrl());
    localHashMap.put("carrier_icon_id", paramCarrierBillingParameters.getCarrierIconId());
    localHashMap.put("show_carrier_tos", booleanToString(Boolean.valueOf(paramCarrierBillingParameters.showCarrierTos())));
    localHashMap.put("carrier_api_version", integerToString(Integer.valueOf(paramCarrierBillingParameters.getCarrierApiVersion())));
    localHashMap.put("per_transaction_credentials_required", booleanToString(Boolean.valueOf(paramCarrierBillingParameters.perTransactionCredentialsRequired())));
    localHashMap.put("send_subscriber_info_with_carrier_requests", booleanToString(Boolean.valueOf(paramCarrierBillingParameters.sendSubscriberInfoWithCarrierRequests())));
    localHashMap.put("password_required", booleanToString(Boolean.valueOf(paramCarrierBillingParameters.passwordRequired())));
    localHashMap.put("association_method", integerToString(Integer.valueOf(paramCarrierBillingParameters.getAssociationMethod())));
    localHashMap.put("user_token_request_address", paramCarrierBillingParameters.getRequestUserTokenTo());
    localHashMap.put("user_token_request_message", paramCarrierBillingParameters.getRequestUserTokenText());
    localHashMap.put("customer_support", paramCarrierBillingParameters.getCustomerSupport());
    this.mStore.put(getParamsKey(), localHashMap);
  }

  public void setProvisioning(CarrierBillingProvisioning paramCarrierBillingProvisioning)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("api_version", integerToString(Integer.valueOf(paramCarrierBillingProvisioning.getApiVersion())));
    localHashMap.put("is_provisioned", booleanToString(Boolean.valueOf(paramCarrierBillingProvisioning.isProvisioned())));
    localHashMap.put("id", paramCarrierBillingProvisioning.getProvisioningId());
    localHashMap.put("tos_url", paramCarrierBillingProvisioning.getTosUrl());
    localHashMap.put("tos_version", paramCarrierBillingProvisioning.getTosVersion());
    localHashMap.put("subscriber_currency", paramCarrierBillingProvisioning.getSubscriberCurrency());
    localHashMap.put("transaction_limit", longToString(Long.valueOf(paramCarrierBillingProvisioning.getTransactionLimit())));
    localHashMap.put("account_type", paramCarrierBillingProvisioning.getAccountType());
    if (paramCarrierBillingProvisioning.getSubscriberInfo() != null)
      localHashMap.put("subscriber_token", paramCarrierBillingProvisioning.getSubscriberInfo().toObfuscatedString());
    localHashMap.put("password_required", booleanToString(Boolean.valueOf(paramCarrierBillingProvisioning.isPasswordRequired())));
    localHashMap.put("password_prompt", paramCarrierBillingProvisioning.getPasswordPrompt());
    localHashMap.put("password_forgot_url", paramCarrierBillingProvisioning.getPasswordForgotUrl());
    localHashMap.put("address_snippet", paramCarrierBillingProvisioning.getAddressSnippet());
    localHashMap.put("country", paramCarrierBillingProvisioning.getCountry());
    EncryptedSubscriberInfo localEncryptedSubscriberInfo = paramCarrierBillingProvisioning.getEncryptedSubscriberInfo();
    if (localEncryptedSubscriberInfo != null)
    {
      localHashMap.put("encrypted_message", localEncryptedSubscriberInfo.getMessage());
      localHashMap.put("encrypted_key", localEncryptedSubscriberInfo.getEncryptedKey());
      localHashMap.put("encrypted_signature", localEncryptedSubscriberInfo.getSignature());
      localHashMap.put("encrypted_init_vector", localEncryptedSubscriberInfo.getInitVector());
      localHashMap.put("encrypted_carrier_key_version", integerToString(Integer.valueOf(localEncryptedSubscriberInfo.getCarrierKeyVersion())));
      localHashMap.put("encrypted_google_key_version", integerToString(Integer.valueOf(localEncryptedSubscriberInfo.getGoogleKeyVersion())));
    }
    CarrierBillingCredentials localCarrierBillingCredentials = paramCarrierBillingProvisioning.getCredentials();
    if (localCarrierBillingCredentials != null)
      setCredentials(localCarrierBillingCredentials);
    this.mStore.put("provisioning", localHashMap);
  }

  List<String> stringToList(String paramString)
  {
    if (paramString == null);
    for (Object localObject = null; ; localObject = new ArrayList(Arrays.asList(paramString.split(","))))
      return localObject;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage
 * JD-Core Version:    0.6.2
 */
package com.google.android.vending.remoting.api;

import android.accounts.Account;
import android.content.Context;
import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.vending.remoting.protos.VendingProtos.RequestPropertiesProto;
import com.google.android.volley.UrlTools;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

public class VendingApiContext
{
  private final AndroidAuthenticator mAuthenticator;
  private final Context mContext;
  private boolean mHasPerformedInitialSecureTokenInvalidation;
  private boolean mHasPerformedInitialTokenInvalidation;
  private final Map<String, String> mHeaders = Maps.newHashMap();
  private String mLastAuthToken;
  private String mLastSecureAuthToken;
  private boolean mReauthenticate = false;
  private VendingProtos.RequestPropertiesProto mRequestProperties;
  private final AndroidAuthenticator mSecureAuthenticator;

  public VendingApiContext(Context paramContext, Account paramAccount, Locale paramLocale, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
  {
    this.mContext = paramContext;
    this.mHeaders.put("User-Agent", "Android-Market/2");
    this.mAuthenticator = new AndroidAuthenticator(paramContext, paramAccount, (String)G.vendingAuthTokenType.get());
    this.mSecureAuthenticator = new AndroidAuthenticator(paramContext, paramAccount, (String)G.vendingSecureAuthTokenType.get());
    this.mRequestProperties = new VendingProtos.RequestPropertiesProto();
    this.mRequestProperties.setAid(paramString1);
    this.mRequestProperties.setUserCountry(paramLocale.getCountry());
    this.mRequestProperties.setUserLanguage(paramLocale.getLanguage());
    this.mRequestProperties.setSoftwareVersion(paramInt);
    if (paramString2 != null)
      this.mRequestProperties.setOperatorName(paramString2);
    if (paramString3 != null)
      this.mRequestProperties.setSimOperatorName(paramString3);
    if (paramString4 != null)
      this.mRequestProperties.setOperatorNumericName(paramString4);
    if (paramString5 != null)
      this.mRequestProperties.setSimOperatorNumericName(paramString5);
    this.mRequestProperties.setProductNameAndVersion(paramString6 + ":" + paramString7);
    this.mRequestProperties.setClientId(paramString8);
    this.mRequestProperties.setLoggingId(paramString9);
    checkUrlRewrites();
  }

  private void checkRewrittenToSecureUrl(String paramString)
  {
    String str = UrlTools.rewrite(this.mContext, paramString);
    if (str == null)
      throw new RuntimeException("URL blocked: " + paramString);
    checkUrlIsSecure(str);
  }

  private static void checkUrlIsSecure(String paramString)
  {
    try
    {
      URL localURL = new URL(paramString);
      if (!localURL.getProtocol().toLowerCase().equals("https"))
      {
        boolean bool = localURL.getHost().toLowerCase().endsWith("corp.google.com");
        if (!bool);
      }
      else
      {
        return;
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      FinskyLog.d("Cannot parse URL: " + paramString, new Object[0]);
    }
    throw new RuntimeException("Insecure URL: " + paramString);
  }

  private void checkUrlRewrites()
  {
    checkRewrittenToSecureUrl("https://android.clients.google.com/vending/api/ApiRequest");
  }

  public Account getAccount()
  {
    return this.mAuthenticator.getAccount();
  }

  public String getAuthToken()
    throws AuthFailureError
  {
    if (this.mReauthenticate)
    {
      this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
      this.mReauthenticate = false;
    }
    this.mLastAuthToken = this.mAuthenticator.getAuthToken();
    return this.mLastAuthToken;
  }

  public Map<String, String> getHeaders()
  {
    return this.mHeaders;
  }

  public VendingProtos.RequestPropertiesProto getRequestProperties(boolean paramBoolean)
    throws AuthFailureError
  {
    if (((paramBoolean) && (!this.mHasPerformedInitialSecureTokenInvalidation)) || ((!paramBoolean) && (!this.mHasPerformedInitialTokenInvalidation)))
    {
      invalidateAuthToken(paramBoolean);
      if (!paramBoolean)
        break label81;
      this.mHasPerformedInitialSecureTokenInvalidation = true;
    }
    while (true)
    {
      VendingProtos.RequestPropertiesProto localRequestPropertiesProto = new VendingProtos.RequestPropertiesProto();
      try
      {
        localRequestPropertiesProto.mergeFrom(this.mRequestProperties.toByteArray());
        if (paramBoolean);
        label81: String str;
        for (Object localObject = getSecureAuthToken(); ; localObject = str)
        {
          localRequestPropertiesProto.setUserAuthToken((String)localObject);
          localRequestPropertiesProto.setUserAuthTokenSecure(paramBoolean);
          return localRequestPropertiesProto;
          this.mHasPerformedInitialTokenInvalidation = true;
          break;
          str = getAuthToken();
        }
      }
      catch (InvalidProtocolBufferMicroException localInvalidProtocolBufferMicroException)
      {
      }
    }
    throw new IllegalStateException("Cannot happen.");
  }

  public String getSecureAuthToken()
    throws AuthFailureError
  {
    if (this.mReauthenticate)
    {
      this.mSecureAuthenticator.invalidateAuthToken(this.mLastAuthToken);
      this.mReauthenticate = false;
    }
    this.mLastSecureAuthToken = this.mSecureAuthenticator.getAuthToken();
    return this.mLastSecureAuthToken;
  }

  public void invalidateAuthToken(boolean paramBoolean)
  {
    String str;
    if (paramBoolean)
    {
      str = this.mLastSecureAuthToken;
      if (str != null)
        this.mAuthenticator.invalidateAuthToken(str);
      if (!paramBoolean)
        break label39;
      this.mLastSecureAuthToken = null;
    }
    while (true)
    {
      return;
      str = this.mLastAuthToken;
      break;
      label39: this.mLastAuthToken = null;
    }
  }

  public void scheduleReauthentication(boolean paramBoolean)
  {
    this.mReauthenticate = true;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.VendingApiContext
 * JD-Core Version:    0.6.2
 */
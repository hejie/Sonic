package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;

public class CarrierBillingProvisioning
{
  private final String mAccountType;
  private final String mAddressSnippet;
  private final int mApiVersion;
  private final String mCountry;
  private final CarrierBillingCredentials mCredentials;
  private final EncryptedSubscriberInfo mEncryptedSubscriberInfo;
  private final boolean mIsProvisioned;
  private final String mPasswordForgotUrl;
  private final String mPasswordPrompt;
  private final boolean mPasswordRequired;
  private final String mProvisioningId;
  private final String mSubscriberCurrency;
  private final SubscriberInfo mSubscriberInfo;
  private final String mTosUrl;
  private final String mTosVersion;
  private final long mTransactionLimit;

  private CarrierBillingProvisioning(Builder paramBuilder)
  {
    this.mApiVersion = paramBuilder.apiVersion;
    this.mIsProvisioned = paramBuilder.isProvisioned;
    this.mProvisioningId = paramBuilder.provisioningId;
    this.mTosUrl = paramBuilder.tosUrl;
    this.mTosVersion = paramBuilder.tosVersion;
    this.mSubscriberCurrency = paramBuilder.subscriberCurrency;
    this.mTransactionLimit = paramBuilder.transactionLimit;
    this.mAccountType = paramBuilder.accountType;
    this.mSubscriberInfo = paramBuilder.subscriberInfo;
    this.mCredentials = paramBuilder.credentials;
    this.mPasswordRequired = paramBuilder.passwordRequired;
    this.mPasswordPrompt = paramBuilder.passwordPrompt;
    this.mPasswordForgotUrl = paramBuilder.passwordForgotUrl;
    this.mEncryptedSubscriberInfo = paramBuilder.encryptedSubscriberInfo;
    this.mAddressSnippet = paramBuilder.addressSnippet;
    this.mCountry = paramBuilder.country;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if (!(paramObject instanceof CarrierBillingProvisioning))
      {
        bool = false;
      }
      else
      {
        CarrierBillingProvisioning localCarrierBillingProvisioning = (CarrierBillingProvisioning)paramObject;
        if ((!Objects.equal(Integer.valueOf(this.mApiVersion), Integer.valueOf(localCarrierBillingProvisioning.mApiVersion))) || (!Objects.equal(Boolean.valueOf(this.mIsProvisioned), Boolean.valueOf(localCarrierBillingProvisioning.mIsProvisioned))) || (!Objects.equal(this.mProvisioningId, localCarrierBillingProvisioning.mProvisioningId)) || (!Objects.equal(this.mTosUrl, localCarrierBillingProvisioning.mTosUrl)) || (!Objects.equal(this.mTosVersion, localCarrierBillingProvisioning.mTosVersion)) || (!Objects.equal(this.mSubscriberCurrency, localCarrierBillingProvisioning.mSubscriberCurrency)) || (!Objects.equal(Long.valueOf(this.mTransactionLimit), Long.valueOf(localCarrierBillingProvisioning.mTransactionLimit))) || (!Objects.equal(this.mAccountType, localCarrierBillingProvisioning.mAccountType)) || (!Objects.equal(this.mSubscriberInfo, localCarrierBillingProvisioning.mSubscriberInfo)) || (!Objects.equal(this.mCredentials, localCarrierBillingProvisioning.mCredentials)) || (!Objects.equal(Boolean.valueOf(this.mPasswordRequired), Boolean.valueOf(localCarrierBillingProvisioning.mPasswordRequired))) || (!Objects.equal(this.mPasswordPrompt, localCarrierBillingProvisioning.mPasswordPrompt)) || (!Objects.equal(this.mPasswordForgotUrl, localCarrierBillingProvisioning.mPasswordForgotUrl)) || (!Objects.equal(this.mEncryptedSubscriberInfo, localCarrierBillingProvisioning.mEncryptedSubscriberInfo)) || (!Objects.equal(this.mAddressSnippet, localCarrierBillingProvisioning.mAddressSnippet)) || (!Objects.equal(this.mCountry, localCarrierBillingProvisioning.mCountry)))
          bool = false;
      }
    }
  }

  public String getAccountType()
  {
    return this.mAccountType;
  }

  public String getAddressSnippet()
  {
    return this.mAddressSnippet;
  }

  public int getApiVersion()
  {
    return this.mApiVersion;
  }

  public String getCountry()
  {
    return this.mCountry;
  }

  public CarrierBillingCredentials getCredentials()
  {
    return this.mCredentials;
  }

  public EncryptedSubscriberInfo getEncryptedSubscriberInfo()
  {
    return this.mEncryptedSubscriberInfo;
  }

  public String getPasswordForgotUrl()
  {
    return this.mPasswordForgotUrl;
  }

  public String getPasswordPrompt()
  {
    return this.mPasswordPrompt;
  }

  public String getProvisioningId()
  {
    return this.mProvisioningId;
  }

  public String getSubscriberCurrency()
  {
    return this.mSubscriberCurrency;
  }

  public SubscriberInfo getSubscriberInfo()
  {
    return this.mSubscriberInfo;
  }

  public String getTosUrl()
  {
    return this.mTosUrl;
  }

  public String getTosVersion()
  {
    return this.mTosVersion;
  }

  public long getTransactionLimit()
  {
    return this.mTransactionLimit;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[16];
    arrayOfObject[0] = Integer.valueOf(this.mApiVersion);
    arrayOfObject[1] = Boolean.valueOf(this.mIsProvisioned);
    arrayOfObject[2] = this.mProvisioningId;
    arrayOfObject[3] = this.mTosUrl;
    arrayOfObject[4] = this.mTosVersion;
    arrayOfObject[5] = this.mSubscriberCurrency;
    arrayOfObject[6] = Long.valueOf(this.mTransactionLimit);
    arrayOfObject[7] = this.mAccountType;
    arrayOfObject[8] = this.mSubscriberInfo;
    arrayOfObject[9] = this.mCredentials;
    arrayOfObject[10] = Boolean.valueOf(this.mPasswordRequired);
    arrayOfObject[11] = this.mPasswordPrompt;
    arrayOfObject[12] = this.mPasswordForgotUrl;
    arrayOfObject[13] = this.mEncryptedSubscriberInfo;
    arrayOfObject[14] = this.mAddressSnippet;
    arrayOfObject[15] = this.mCountry;
    return Objects.hashCode(arrayOfObject);
  }

  public boolean isPasswordRequired()
  {
    return this.mPasswordRequired;
  }

  public boolean isProvisioned()
  {
    return this.mIsProvisioned;
  }

  public String toString()
  {
    return "CarrierBillingProvisioning:" + "\n" + "  apiVersion             : " + this.mApiVersion + "\n" + "  isProvisioned          : " + this.mIsProvisioned + "\n" + "  provisioningId         : " + this.mProvisioningId + "\n" + "  tosUrl                 : " + this.mTosUrl + "\n" + "  tosVersion             : " + this.mTosVersion + "\n" + "  subscriberCurrency     : " + this.mSubscriberCurrency + "\n" + "  transactionLimit       : " + this.mTransactionLimit + "\n" + "  accountType            : " + this.mAccountType + "\n" + "  subscriberInfo         : " + this.mSubscriberInfo + "\n" + "  credentials            : " + this.mCredentials + "\n" + "  passwordRequired       : " + this.mPasswordRequired + "\n" + "  passwordPrompt         : " + this.mPasswordPrompt + "\n" + "  passwordForgotUrl      : " + this.mPasswordForgotUrl + "\n" + "  encryptedSubscriberInfo: " + this.mEncryptedSubscriberInfo + "\n" + "  addressSnippet         : " + this.mAddressSnippet + "\n" + "  country                : " + this.mCountry + "\n";
  }

  public static class Builder
  {
    private String accountType;
    private String addressSnippet;
    private int apiVersion;
    private String country;
    private CarrierBillingCredentials credentials;
    private EncryptedSubscriberInfo encryptedSubscriberInfo;
    private boolean isProvisioned;
    private String passwordForgotUrl;
    private String passwordPrompt;
    private boolean passwordRequired;
    private String provisioningId;
    private String subscriberCurrency;
    private SubscriberInfo subscriberInfo;
    private String tosUrl;
    private String tosVersion;
    private long transactionLimit;

    public CarrierBillingProvisioning build()
    {
      return new CarrierBillingProvisioning(this, null);
    }

    public Builder setAccountType(String paramString)
    {
      this.accountType = paramString;
      return this;
    }

    public Builder setAddressSnippet(String paramString)
    {
      this.addressSnippet = paramString;
      return this;
    }

    public Builder setApiVersion(int paramInt)
    {
      this.apiVersion = paramInt;
      return this;
    }

    public Builder setCountry(String paramString)
    {
      this.country = paramString;
      return this;
    }

    public Builder setCredentials(CarrierBillingCredentials paramCarrierBillingCredentials)
    {
      this.credentials = paramCarrierBillingCredentials;
      return this;
    }

    public Builder setEncryptedSubscriberInfo(EncryptedSubscriberInfo paramEncryptedSubscriberInfo)
    {
      this.encryptedSubscriberInfo = paramEncryptedSubscriberInfo;
      return this;
    }

    public Builder setIsProvisioned(boolean paramBoolean)
    {
      this.isProvisioned = paramBoolean;
      return this;
    }

    public Builder setPasswordForgotUrl(String paramString)
    {
      this.passwordForgotUrl = paramString;
      return this;
    }

    public Builder setPasswordPrompt(String paramString)
    {
      this.passwordPrompt = paramString;
      return this;
    }

    public Builder setPasswordRequired(boolean paramBoolean)
    {
      this.passwordRequired = paramBoolean;
      return this;
    }

    public Builder setProvisioningId(String paramString)
    {
      this.provisioningId = paramString;
      return this;
    }

    public Builder setSubscriberCurrency(String paramString)
    {
      this.subscriberCurrency = paramString;
      return this;
    }

    public Builder setSubscriberInfo(SubscriberInfo paramSubscriberInfo)
    {
      this.subscriberInfo = paramSubscriberInfo;
      return this;
    }

    public Builder setTosUrl(String paramString)
    {
      this.tosUrl = paramString;
      return this;
    }

    public Builder setTosVersion(String paramString)
    {
      this.tosVersion = paramString;
      return this;
    }

    public Builder setTransactionLimit(long paramLong)
    {
      this.transactionLimit = paramLong;
      return this;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning
 * JD-Core Version:    0.6.2
 */
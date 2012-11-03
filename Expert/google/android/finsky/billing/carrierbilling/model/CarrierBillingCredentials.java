package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;

public class CarrierBillingCredentials
{
  private final int mApiVersion;
  private final String mCredentials;
  private final long mExpirationTime;
  private final boolean mInvalidPassword;
  private final boolean mIsProvisioned;

  private CarrierBillingCredentials(Builder paramBuilder)
  {
    this.mApiVersion = paramBuilder.apiVersion;
    this.mCredentials = paramBuilder.credentials;
    this.mExpirationTime = paramBuilder.expirationTime;
    this.mIsProvisioned = paramBuilder.isProvisioned;
    this.mInvalidPassword = paramBuilder.invalidPassword;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if (!(paramObject instanceof CarrierBillingCredentials))
      {
        bool = false;
      }
      else
      {
        CarrierBillingCredentials localCarrierBillingCredentials = (CarrierBillingCredentials)paramObject;
        if ((!Objects.equal(Integer.valueOf(this.mApiVersion), Integer.valueOf(localCarrierBillingCredentials.mApiVersion))) || (!Objects.equal(this.mCredentials, localCarrierBillingCredentials.mCredentials)) || (!Objects.equal(Long.valueOf(this.mExpirationTime), Long.valueOf(localCarrierBillingCredentials.mExpirationTime))) || (!Objects.equal(Boolean.valueOf(this.mIsProvisioned), Boolean.valueOf(localCarrierBillingCredentials.mIsProvisioned))) || (!Objects.equal(Boolean.valueOf(this.mInvalidPassword), Boolean.valueOf(localCarrierBillingCredentials.mInvalidPassword))))
          bool = false;
      }
    }
  }

  public String getCredentials()
  {
    return this.mCredentials;
  }

  public long getExpirationTime()
  {
    return this.mExpirationTime;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = Integer.valueOf(this.mApiVersion);
    arrayOfObject[1] = this.mCredentials;
    arrayOfObject[2] = Long.valueOf(this.mExpirationTime);
    arrayOfObject[3] = Boolean.valueOf(this.mIsProvisioned);
    arrayOfObject[4] = Boolean.valueOf(this.mInvalidPassword);
    return Objects.hashCode(arrayOfObject);
  }

  public boolean invalidPassword()
  {
    return this.mInvalidPassword;
  }

  public boolean isProvisioned()
  {
    return this.mIsProvisioned;
  }

  public String toString()
  {
    return "CarrierBillingCredentials: " + "  apiVersion     : " + this.mApiVersion + "\n" + "  credentials    : " + this.mCredentials + "\n" + "  expirationTime : " + this.mExpirationTime + "\n" + "  isProvisioned  : " + this.mIsProvisioned + "\n" + "  invalidPassword: " + this.mInvalidPassword + "\n";
  }

  public static class Builder
  {
    private int apiVersion;
    private String credentials;
    private long expirationTime;
    private boolean invalidPassword;
    private boolean isProvisioned;

    public CarrierBillingCredentials build()
    {
      return new CarrierBillingCredentials(this, null);
    }

    public Builder setApiVersion(int paramInt)
    {
      this.apiVersion = paramInt;
      return this;
    }

    public Builder setCredentials(String paramString)
    {
      this.credentials = paramString;
      return this;
    }

    public Builder setExpirationTime(long paramLong)
    {
      this.expirationTime = paramLong;
      return this;
    }

    public Builder setInvalidPassword(boolean paramBoolean)
    {
      this.invalidPassword = paramBoolean;
      return this;
    }

    public Builder setIsProvisioned(boolean paramBoolean)
    {
      this.isProvisioned = paramBoolean;
      return this;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.CarrierBillingCredentials
 * JD-Core Version:    0.6.2
 */
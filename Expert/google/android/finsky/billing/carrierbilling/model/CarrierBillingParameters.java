package com.google.android.finsky.billing.carrierbilling.model;

import com.google.android.finsky.utils.Objects;
import java.util.List;

public class CarrierBillingParameters
{
  private final int mAssociationMethod;
  private final int mCarrierApiVersion;
  private final String mCarrierIconId;
  private final String mCustomerSupport;
  private final String mGetCredentialsUrl;
  private final String mGetProvisioningUrl;
  private final String mId;
  private final List<String> mMncMccs;
  private final String mName;
  private final boolean mPasswordRequired;
  private final boolean mPerTransactionCredentialsRequired;
  private final String mRequestUserTokenText;
  private final String mRequestUserTokenTo;
  private final boolean mSendSubscriberInfoWithCarrierRequests;
  private final boolean mShowCarrierTos;

  private CarrierBillingParameters(Builder paramBuilder)
  {
    this.mId = paramBuilder.id;
    this.mName = paramBuilder.name;
    this.mMncMccs = paramBuilder.mncMccs;
    this.mGetProvisioningUrl = paramBuilder.getProvisioningUrl;
    this.mGetCredentialsUrl = paramBuilder.getCredentialsUrl;
    this.mCarrierIconId = paramBuilder.carrierIconId;
    this.mShowCarrierTos = paramBuilder.showCarrierTos;
    this.mCarrierApiVersion = paramBuilder.carrierApiVersion;
    this.mPerTransactionCredentialsRequired = paramBuilder.perTransactionCredentialsRequired;
    this.mSendSubscriberInfoWithCarrierRequests = paramBuilder.sendSubscriberInfoWithCarrierRequests;
    this.mPasswordRequired = paramBuilder.passwordRequired;
    this.mAssociationMethod = paramBuilder.associationMethod;
    this.mRequestUserTokenTo = paramBuilder.requestUserTokenTo;
    this.mRequestUserTokenText = paramBuilder.requestUserTokenText;
    this.mCustomerSupport = paramBuilder.customerSupport;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    while (true)
    {
      return bool;
      if (!(paramObject instanceof CarrierBillingParameters))
      {
        bool = false;
      }
      else
      {
        CarrierBillingParameters localCarrierBillingParameters = (CarrierBillingParameters)paramObject;
        if ((!Objects.equal(this.mId, localCarrierBillingParameters.mId)) || (!Objects.equal(this.mName, localCarrierBillingParameters.mName)) || (!Objects.equal(this.mMncMccs, localCarrierBillingParameters.mMncMccs)) || (!Objects.equal(this.mGetProvisioningUrl, localCarrierBillingParameters.mGetProvisioningUrl)) || (!Objects.equal(this.mGetCredentialsUrl, localCarrierBillingParameters.mGetCredentialsUrl)) || (!Objects.equal(this.mCarrierIconId, localCarrierBillingParameters.mCarrierIconId)) || (!Objects.equal(Boolean.valueOf(this.mShowCarrierTos), Boolean.valueOf(localCarrierBillingParameters.mShowCarrierTos))) || (!Objects.equal(Integer.valueOf(this.mCarrierApiVersion), Integer.valueOf(localCarrierBillingParameters.mCarrierApiVersion))) || (!Objects.equal(Boolean.valueOf(this.mPerTransactionCredentialsRequired), Boolean.valueOf(localCarrierBillingParameters.mPerTransactionCredentialsRequired))) || (!Objects.equal(Boolean.valueOf(this.mSendSubscriberInfoWithCarrierRequests), Boolean.valueOf(localCarrierBillingParameters.mSendSubscriberInfoWithCarrierRequests))) || (!Objects.equal(Boolean.valueOf(this.mPasswordRequired), Boolean.valueOf(localCarrierBillingParameters.mPasswordRequired))) || (!Objects.equal(Integer.valueOf(this.mAssociationMethod), Integer.valueOf(localCarrierBillingParameters.mAssociationMethod))) || (!Objects.equal(this.mRequestUserTokenTo, localCarrierBillingParameters.mRequestUserTokenTo)) || (!Objects.equal(this.mRequestUserTokenText, localCarrierBillingParameters.mRequestUserTokenText)) || (!Objects.equal(this.mCustomerSupport, localCarrierBillingParameters.mCustomerSupport)))
          bool = false;
      }
    }
  }

  public int getAssociationMethod()
  {
    return this.mAssociationMethod;
  }

  public int getCarrierApiVersion()
  {
    return this.mCarrierApiVersion;
  }

  public String getCarrierIconId()
  {
    return this.mCarrierIconId;
  }

  public String getCustomerSupport()
  {
    return this.mCustomerSupport;
  }

  public String getGetCredentialsUrl()
  {
    return this.mGetCredentialsUrl;
  }

  public String getGetProvisioningUrl()
  {
    return this.mGetProvisioningUrl;
  }

  public String getId()
  {
    return this.mId;
  }

  public List<String> getMncMccs()
  {
    return this.mMncMccs;
  }

  public String getName()
  {
    return this.mName;
  }

  public String getRequestUserTokenText()
  {
    return this.mRequestUserTokenText;
  }

  public String getRequestUserTokenTo()
  {
    return this.mRequestUserTokenTo;
  }

  public int hashCode()
  {
    Object[] arrayOfObject = new Object[15];
    arrayOfObject[0] = this.mId;
    arrayOfObject[1] = this.mName;
    arrayOfObject[2] = this.mMncMccs;
    arrayOfObject[3] = this.mGetProvisioningUrl;
    arrayOfObject[4] = this.mGetCredentialsUrl;
    arrayOfObject[5] = this.mCarrierIconId;
    arrayOfObject[6] = Boolean.valueOf(this.mShowCarrierTos);
    arrayOfObject[7] = Integer.valueOf(this.mCarrierApiVersion);
    arrayOfObject[8] = Boolean.valueOf(this.mPerTransactionCredentialsRequired);
    arrayOfObject[9] = Boolean.valueOf(this.mSendSubscriberInfoWithCarrierRequests);
    arrayOfObject[10] = Boolean.valueOf(this.mPasswordRequired);
    arrayOfObject[11] = Integer.valueOf(this.mAssociationMethod);
    arrayOfObject[12] = this.mRequestUserTokenTo;
    arrayOfObject[13] = this.mRequestUserTokenText;
    arrayOfObject[14] = this.mCustomerSupport;
    return Objects.hashCode(arrayOfObject);
  }

  public boolean passwordRequired()
  {
    return this.mPasswordRequired;
  }

  public boolean perTransactionCredentialsRequired()
  {
    return this.mPerTransactionCredentialsRequired;
  }

  public boolean sendSubscriberInfoWithCarrierRequests()
  {
    return this.mSendSubscriberInfoWithCarrierRequests;
  }

  public boolean showCarrierTos()
  {
    return this.mShowCarrierTos;
  }

  public String toString()
  {
    return "CarrierBillingParameters:" + "\n" + "  id                                   : " + this.mId + "\n" + "  name                                 : " + this.mName + "\n" + "  mncMccs                              : " + this.mMncMccs + "\n" + "  getProvisioningUrl                   : " + this.mGetProvisioningUrl + "\n" + "  getCredentialsUrl                    : " + this.mGetCredentialsUrl + "\n" + "  carrierIconId                        : " + this.mCarrierIconId + "\n" + "  showCarrierTos                       : " + this.mShowCarrierTos + "\n" + "  carrierApiVersion                    : " + this.mCarrierApiVersion + "\n" + "  perTransactionCredentialsRequired    : " + this.mPerTransactionCredentialsRequired + "\n" + "  sendSubscriberInfoWithCarrierRequests: " + this.mSendSubscriberInfoWithCarrierRequests + "\n" + "  passwordRequired: " + this.mPasswordRequired + "\n" + "  assosiationMethod: " + this.mAssociationMethod + "\n" + "  userTokenRequestTo: " + this.mRequestUserTokenTo + "\n" + "  userTokenRequestText: " + this.mRequestUserTokenText + "\n" + "  customerSupport: " + this.mCustomerSupport + "\n";
  }

  public static class Builder
  {
    private int associationMethod;
    private int carrierApiVersion;
    private String carrierIconId;
    private String customerSupport;
    private String getCredentialsUrl;
    private String getProvisioningUrl;
    private String id;
    private List<String> mncMccs;
    private String name;
    private boolean passwordRequired;
    private boolean perTransactionCredentialsRequired;
    private String requestUserTokenText;
    private String requestUserTokenTo;
    private boolean sendSubscriberInfoWithCarrierRequests;
    private boolean showCarrierTos;

    public CarrierBillingParameters build()
    {
      return new CarrierBillingParameters(this, null);
    }

    public Builder setAssociationMethod(int paramInt)
    {
      this.associationMethod = paramInt;
      return this;
    }

    public Builder setCarrierApiVersion(int paramInt)
    {
      this.carrierApiVersion = paramInt;
      return this;
    }

    public Builder setCarrierIconId(String paramString)
    {
      this.carrierIconId = paramString;
      return this;
    }

    public Builder setCustomerSupport(String paramString)
    {
      this.customerSupport = paramString;
      return this;
    }

    public Builder setGetCredentialsUrl(String paramString)
    {
      this.getCredentialsUrl = paramString;
      return this;
    }

    public Builder setGetProvisioningUrl(String paramString)
    {
      this.getProvisioningUrl = paramString;
      return this;
    }

    public Builder setId(String paramString)
    {
      this.id = paramString;
      return this;
    }

    public Builder setMncMccs(List<String> paramList)
    {
      this.mncMccs = paramList;
      return this;
    }

    public Builder setName(String paramString)
    {
      this.name = paramString;
      return this;
    }

    public Builder setPasswordRequired(boolean paramBoolean)
    {
      this.passwordRequired = paramBoolean;
      return this;
    }

    public Builder setPerTransactionCredentialsRequired(boolean paramBoolean)
    {
      this.perTransactionCredentialsRequired = paramBoolean;
      return this;
    }

    public Builder setRequestUserTokenText(String paramString)
    {
      this.requestUserTokenText = paramString;
      return this;
    }

    public Builder setRequestUserTokenTo(String paramString)
    {
      this.requestUserTokenTo = paramString;
      return this;
    }

    public Builder setSendSubscriberInfoWithCarrierRequests(boolean paramBoolean)
    {
      this.sendSubscriberInfoWithCarrierRequests = paramBoolean;
      return this;
    }

    public Builder setShowCarrierTos(boolean paramBoolean)
    {
      this.showCarrierTos = paramBoolean;
      return this;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.carrierbilling.flow.association;

import com.google.android.finsky.api.DfeApi;

public class AccountAssociationFactoryImpl
{
  private final DfeApi mDfeApi;
  private final String mSmsAddress;
  private final String mSmsPrefix;

  public AccountAssociationFactoryImpl(DfeApi paramDfeApi, String paramString1, String paramString2)
  {
    this.mDfeApi = paramDfeApi;
    this.mSmsAddress = paramString1;
    this.mSmsPrefix = paramString2;
  }

  public AssociationAction createAccountAssociationAction()
  {
    return new CarrierOutAssociation(this.mDfeApi, this.mSmsAddress, this.mSmsPrefix);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.flow.association.AccountAssociationFactoryImpl
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting;

public enum PhoneFeature
{
  private final String mValue;

  static
  {
    ENABLE_DUN = new PhoneFeature("ENABLE_DUN", 3, "enableDUN");
    PhoneFeature[] arrayOfPhoneFeature = new PhoneFeature[4];
    arrayOfPhoneFeature[0] = ENABLE_HIPRI;
    arrayOfPhoneFeature[1] = ENABLE_MMS;
    arrayOfPhoneFeature[2] = ENABLE_SUPL;
    arrayOfPhoneFeature[3] = ENABLE_DUN;
  }

  private PhoneFeature(String paramString)
  {
    this.mValue = paramString;
  }

  public String getValue()
  {
    return this.mValue;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.PhoneFeature
 * JD-Core Version:    0.6.2
 */
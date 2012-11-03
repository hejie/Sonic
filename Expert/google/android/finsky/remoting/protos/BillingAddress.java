package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class BillingAddress
{
  public static final class Address extends MessageMicro
  {
    private String addressLine1_ = "";
    private String addressLine2_ = "";
    private int cachedSize = -1;
    private String city_ = "";
    private String dependentLocality_ = "";
    private boolean deprecatedIsReduced_ = false;
    private String email_ = "";
    private String firstName_ = "";
    private boolean hasAddressLine1;
    private boolean hasAddressLine2;
    private boolean hasCity;
    private boolean hasDependentLocality;
    private boolean hasDeprecatedIsReduced;
    private boolean hasEmail;
    private boolean hasFirstName;
    private boolean hasLanguageCode;
    private boolean hasLastName;
    private boolean hasName;
    private boolean hasPhoneNumber;
    private boolean hasPostalCode;
    private boolean hasPostalCountry;
    private boolean hasSortingCode;
    private boolean hasState;
    private String languageCode_ = "";
    private String lastName_ = "";
    private String name_ = "";
    private String phoneNumber_ = "";
    private String postalCode_ = "";
    private String postalCountry_ = "";
    private String sortingCode_ = "";
    private String state_ = "";

    public String getAddressLine1()
    {
      return this.addressLine1_;
    }

    public String getAddressLine2()
    {
      return this.addressLine2_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCity()
    {
      return this.city_;
    }

    public String getDependentLocality()
    {
      return this.dependentLocality_;
    }

    public boolean getDeprecatedIsReduced()
    {
      return this.deprecatedIsReduced_;
    }

    public String getEmail()
    {
      return this.email_;
    }

    public String getFirstName()
    {
      return this.firstName_;
    }

    public String getLanguageCode()
    {
      return this.languageCode_;
    }

    public String getLastName()
    {
      return this.lastName_;
    }

    public String getName()
    {
      return this.name_;
    }

    public String getPhoneNumber()
    {
      return this.phoneNumber_;
    }

    public String getPostalCode()
    {
      return this.postalCode_;
    }

    public String getPostalCountry()
    {
      return this.postalCountry_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasAddressLine1())
        i += CodedOutputStreamMicro.computeStringSize(2, getAddressLine1());
      if (hasAddressLine2())
        i += CodedOutputStreamMicro.computeStringSize(3, getAddressLine2());
      if (hasCity())
        i += CodedOutputStreamMicro.computeStringSize(4, getCity());
      if (hasState())
        i += CodedOutputStreamMicro.computeStringSize(5, getState());
      if (hasPostalCode())
        i += CodedOutputStreamMicro.computeStringSize(6, getPostalCode());
      if (hasPostalCountry())
        i += CodedOutputStreamMicro.computeStringSize(7, getPostalCountry());
      if (hasDependentLocality())
        i += CodedOutputStreamMicro.computeStringSize(8, getDependentLocality());
      if (hasSortingCode())
        i += CodedOutputStreamMicro.computeStringSize(9, getSortingCode());
      if (hasLanguageCode())
        i += CodedOutputStreamMicro.computeStringSize(10, getLanguageCode());
      if (hasPhoneNumber())
        i += CodedOutputStreamMicro.computeStringSize(11, getPhoneNumber());
      if (hasDeprecatedIsReduced())
        i += CodedOutputStreamMicro.computeBoolSize(12, getDeprecatedIsReduced());
      if (hasFirstName())
        i += CodedOutputStreamMicro.computeStringSize(13, getFirstName());
      if (hasLastName())
        i += CodedOutputStreamMicro.computeStringSize(14, getLastName());
      if (hasEmail())
        i += CodedOutputStreamMicro.computeStringSize(15, getEmail());
      this.cachedSize = i;
      return i;
    }

    public String getSortingCode()
    {
      return this.sortingCode_;
    }

    public String getState()
    {
      return this.state_;
    }

    public boolean hasAddressLine1()
    {
      return this.hasAddressLine1;
    }

    public boolean hasAddressLine2()
    {
      return this.hasAddressLine2;
    }

    public boolean hasCity()
    {
      return this.hasCity;
    }

    public boolean hasDependentLocality()
    {
      return this.hasDependentLocality;
    }

    public boolean hasDeprecatedIsReduced()
    {
      return this.hasDeprecatedIsReduced;
    }

    public boolean hasEmail()
    {
      return this.hasEmail;
    }

    public boolean hasFirstName()
    {
      return this.hasFirstName;
    }

    public boolean hasLanguageCode()
    {
      return this.hasLanguageCode;
    }

    public boolean hasLastName()
    {
      return this.hasLastName;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasPhoneNumber()
    {
      return this.hasPhoneNumber;
    }

    public boolean hasPostalCode()
    {
      return this.hasPostalCode;
    }

    public boolean hasPostalCountry()
    {
      return this.hasPostalCountry;
    }

    public boolean hasSortingCode()
    {
      return this.hasSortingCode;
    }

    public boolean hasState()
    {
      return this.hasState;
    }

    public Address mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      while (true)
      {
        int i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
          if (parseUnknownField(paramCodedInputStreamMicro, i))
            continue;
        case 0:
          return this;
        case 10:
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setAddressLine1(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setAddressLine2(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setCity(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setState(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setPostalCode(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setPostalCountry(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setDependentLocality(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setSortingCode(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          setLanguageCode(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setPhoneNumber(paramCodedInputStreamMicro.readString());
          break;
        case 96:
          setDeprecatedIsReduced(paramCodedInputStreamMicro.readBool());
          break;
        case 106:
          setFirstName(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          setLastName(paramCodedInputStreamMicro.readString());
          break;
        case 122:
        }
        setEmail(paramCodedInputStreamMicro.readString());
      }
    }

    public Address setAddressLine1(String paramString)
    {
      this.hasAddressLine1 = true;
      this.addressLine1_ = paramString;
      return this;
    }

    public Address setAddressLine2(String paramString)
    {
      this.hasAddressLine2 = true;
      this.addressLine2_ = paramString;
      return this;
    }

    public Address setCity(String paramString)
    {
      this.hasCity = true;
      this.city_ = paramString;
      return this;
    }

    public Address setDependentLocality(String paramString)
    {
      this.hasDependentLocality = true;
      this.dependentLocality_ = paramString;
      return this;
    }

    public Address setDeprecatedIsReduced(boolean paramBoolean)
    {
      this.hasDeprecatedIsReduced = true;
      this.deprecatedIsReduced_ = paramBoolean;
      return this;
    }

    public Address setEmail(String paramString)
    {
      this.hasEmail = true;
      this.email_ = paramString;
      return this;
    }

    public Address setFirstName(String paramString)
    {
      this.hasFirstName = true;
      this.firstName_ = paramString;
      return this;
    }

    public Address setLanguageCode(String paramString)
    {
      this.hasLanguageCode = true;
      this.languageCode_ = paramString;
      return this;
    }

    public Address setLastName(String paramString)
    {
      this.hasLastName = true;
      this.lastName_ = paramString;
      return this;
    }

    public Address setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public Address setPhoneNumber(String paramString)
    {
      this.hasPhoneNumber = true;
      this.phoneNumber_ = paramString;
      return this;
    }

    public Address setPostalCode(String paramString)
    {
      this.hasPostalCode = true;
      this.postalCode_ = paramString;
      return this;
    }

    public Address setPostalCountry(String paramString)
    {
      this.hasPostalCountry = true;
      this.postalCountry_ = paramString;
      return this;
    }

    public Address setSortingCode(String paramString)
    {
      this.hasSortingCode = true;
      this.sortingCode_ = paramString;
      return this;
    }

    public Address setState(String paramString)
    {
      this.hasState = true;
      this.state_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasAddressLine1())
        paramCodedOutputStreamMicro.writeString(2, getAddressLine1());
      if (hasAddressLine2())
        paramCodedOutputStreamMicro.writeString(3, getAddressLine2());
      if (hasCity())
        paramCodedOutputStreamMicro.writeString(4, getCity());
      if (hasState())
        paramCodedOutputStreamMicro.writeString(5, getState());
      if (hasPostalCode())
        paramCodedOutputStreamMicro.writeString(6, getPostalCode());
      if (hasPostalCountry())
        paramCodedOutputStreamMicro.writeString(7, getPostalCountry());
      if (hasDependentLocality())
        paramCodedOutputStreamMicro.writeString(8, getDependentLocality());
      if (hasSortingCode())
        paramCodedOutputStreamMicro.writeString(9, getSortingCode());
      if (hasLanguageCode())
        paramCodedOutputStreamMicro.writeString(10, getLanguageCode());
      if (hasPhoneNumber())
        paramCodedOutputStreamMicro.writeString(11, getPhoneNumber());
      if (hasDeprecatedIsReduced())
        paramCodedOutputStreamMicro.writeBool(12, getDeprecatedIsReduced());
      if (hasFirstName())
        paramCodedOutputStreamMicro.writeString(13, getFirstName());
      if (hasLastName())
        paramCodedOutputStreamMicro.writeString(14, getLastName());
      if (hasEmail())
        paramCodedOutputStreamMicro.writeString(15, getEmail());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.BillingAddress
 * JD-Core Version:    0.6.2
 */
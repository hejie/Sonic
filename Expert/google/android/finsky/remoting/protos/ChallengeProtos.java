package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ChallengeProtos
{
  public static final class AddressChallenge extends MessageMicro
  {
    private BillingAddress.Address address_ = null;
    private int cachedSize = -1;
    private List<ChallengeProtos.FormCheckbox> checkbox_ = Collections.emptyList();
    private String descriptionHtml_ = "";
    private String errorHtml_ = "";
    private List<ChallengeProtos.InputValidationError> errorInputField_ = Collections.emptyList();
    private boolean hasAddress;
    private boolean hasDescriptionHtml;
    private boolean hasErrorHtml;
    private boolean hasResponseAddressParam;
    private boolean hasResponseCheckboxesParam;
    private boolean hasTitle;
    private List<Integer> requiredField_ = Collections.emptyList();
    private String responseAddressParam_ = "";
    private String responseCheckboxesParam_ = "";
    private List<ChallengeProtos.Country> supportedCountry_ = Collections.emptyList();
    private String title_ = "";

    public AddressChallenge addCheckbox(ChallengeProtos.FormCheckbox paramFormCheckbox)
    {
      if (paramFormCheckbox == null)
        throw new NullPointerException();
      if (this.checkbox_.isEmpty())
        this.checkbox_ = new ArrayList();
      this.checkbox_.add(paramFormCheckbox);
      return this;
    }

    public AddressChallenge addErrorInputField(ChallengeProtos.InputValidationError paramInputValidationError)
    {
      if (paramInputValidationError == null)
        throw new NullPointerException();
      if (this.errorInputField_.isEmpty())
        this.errorInputField_ = new ArrayList();
      this.errorInputField_.add(paramInputValidationError);
      return this;
    }

    public AddressChallenge addRequiredField(int paramInt)
    {
      if (this.requiredField_.isEmpty())
        this.requiredField_ = new ArrayList();
      this.requiredField_.add(Integer.valueOf(paramInt));
      return this;
    }

    public AddressChallenge addSupportedCountry(ChallengeProtos.Country paramCountry)
    {
      if (paramCountry == null)
        throw new NullPointerException();
      if (this.supportedCountry_.isEmpty())
        this.supportedCountry_ = new ArrayList();
      this.supportedCountry_.add(paramCountry);
      return this;
    }

    public BillingAddress.Address getAddress()
    {
      return this.address_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ChallengeProtos.FormCheckbox getCheckbox(int paramInt)
    {
      return (ChallengeProtos.FormCheckbox)this.checkbox_.get(paramInt);
    }

    public int getCheckboxCount()
    {
      return this.checkbox_.size();
    }

    public List<ChallengeProtos.FormCheckbox> getCheckboxList()
    {
      return this.checkbox_;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public String getErrorHtml()
    {
      return this.errorHtml_;
    }

    public List<ChallengeProtos.InputValidationError> getErrorInputFieldList()
    {
      return this.errorInputField_;
    }

    public List<Integer> getRequiredFieldList()
    {
      return this.requiredField_;
    }

    public String getResponseAddressParam()
    {
      return this.responseAddressParam_;
    }

    public String getResponseCheckboxesParam()
    {
      return this.responseCheckboxesParam_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResponseAddressParam())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getResponseAddressParam());
      if (hasResponseCheckboxesParam())
        i += CodedOutputStreamMicro.computeStringSize(2, getResponseCheckboxesParam());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getTitle());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(4, getDescriptionHtml());
      Iterator localIterator1 = getCheckboxList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(5, (ChallengeProtos.FormCheckbox)localIterator1.next());
      if (hasAddress())
        i += CodedOutputStreamMicro.computeMessageSize(6, getAddress());
      Iterator localIterator2 = getErrorInputFieldList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(7, (ChallengeProtos.InputValidationError)localIterator2.next());
      if (hasErrorHtml())
        i += CodedOutputStreamMicro.computeStringSize(8, getErrorHtml());
      int j = 0;
      Iterator localIterator3 = getRequiredFieldList().iterator();
      while (localIterator3.hasNext())
        j += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator3.next()).intValue());
      int k = i + j + 1 * getRequiredFieldList().size();
      Iterator localIterator4 = getSupportedCountryList().iterator();
      while (localIterator4.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(10, (ChallengeProtos.Country)localIterator4.next());
      this.cachedSize = k;
      return k;
    }

    public int getSupportedCountryCount()
    {
      return this.supportedCountry_.size();
    }

    public List<ChallengeProtos.Country> getSupportedCountryList()
    {
      return this.supportedCountry_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasErrorHtml()
    {
      return this.hasErrorHtml;
    }

    public boolean hasResponseAddressParam()
    {
      return this.hasResponseAddressParam;
    }

    public boolean hasResponseCheckboxesParam()
    {
      return this.hasResponseCheckboxesParam;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public AddressChallenge mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResponseAddressParam(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setResponseCheckboxesParam(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          ChallengeProtos.FormCheckbox localFormCheckbox = new ChallengeProtos.FormCheckbox();
          paramCodedInputStreamMicro.readMessage(localFormCheckbox);
          addCheckbox(localFormCheckbox);
          break;
        case 50:
          BillingAddress.Address localAddress = new BillingAddress.Address();
          paramCodedInputStreamMicro.readMessage(localAddress);
          setAddress(localAddress);
          break;
        case 58:
          ChallengeProtos.InputValidationError localInputValidationError = new ChallengeProtos.InputValidationError();
          paramCodedInputStreamMicro.readMessage(localInputValidationError);
          addErrorInputField(localInputValidationError);
          break;
        case 66:
          setErrorHtml(paramCodedInputStreamMicro.readString());
          break;
        case 72:
          addRequiredField(paramCodedInputStreamMicro.readInt32());
          break;
        case 82:
        }
        ChallengeProtos.Country localCountry = new ChallengeProtos.Country();
        paramCodedInputStreamMicro.readMessage(localCountry);
        addSupportedCountry(localCountry);
      }
    }

    public AddressChallenge setAddress(BillingAddress.Address paramAddress)
    {
      if (paramAddress == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddress;
      return this;
    }

    public AddressChallenge setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public AddressChallenge setErrorHtml(String paramString)
    {
      this.hasErrorHtml = true;
      this.errorHtml_ = paramString;
      return this;
    }

    public AddressChallenge setResponseAddressParam(String paramString)
    {
      this.hasResponseAddressParam = true;
      this.responseAddressParam_ = paramString;
      return this;
    }

    public AddressChallenge setResponseCheckboxesParam(String paramString)
    {
      this.hasResponseCheckboxesParam = true;
      this.responseCheckboxesParam_ = paramString;
      return this;
    }

    public AddressChallenge setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResponseAddressParam())
        paramCodedOutputStreamMicro.writeString(1, getResponseAddressParam());
      if (hasResponseCheckboxesParam())
        paramCodedOutputStreamMicro.writeString(2, getResponseCheckboxesParam());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(3, getTitle());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(4, getDescriptionHtml());
      Iterator localIterator1 = getCheckboxList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(5, (ChallengeProtos.FormCheckbox)localIterator1.next());
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(6, getAddress());
      Iterator localIterator2 = getErrorInputFieldList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(7, (ChallengeProtos.InputValidationError)localIterator2.next());
      if (hasErrorHtml())
        paramCodedOutputStreamMicro.writeString(8, getErrorHtml());
      Iterator localIterator3 = getRequiredFieldList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeInt32(9, ((Integer)localIterator3.next()).intValue());
      Iterator localIterator4 = getSupportedCountryList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(10, (ChallengeProtos.Country)localIterator4.next());
    }
  }

  public static final class AuthenticationChallenge extends MessageMicro
  {
    private int authenticationType_ = 1;
    private int cachedSize = -1;
    private String gaiaDescriptionTextHtml_ = "";
    private String gaiaFooterTextHtml_ = "";
    private String gaiaHeaderText_ = "";
    private boolean hasAuthenticationType;
    private boolean hasGaiaDescriptionTextHtml;
    private boolean hasGaiaFooterTextHtml;
    private boolean hasGaiaHeaderText;
    private boolean hasResponseAuthenticationTypeParam;
    private boolean hasResponseRetryCountParam;
    private String responseAuthenticationTypeParam_ = "";
    private String responseRetryCountParam_ = "";

    public int getAuthenticationType()
    {
      return this.authenticationType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getGaiaDescriptionTextHtml()
    {
      return this.gaiaDescriptionTextHtml_;
    }

    public String getGaiaFooterTextHtml()
    {
      return this.gaiaFooterTextHtml_;
    }

    public String getGaiaHeaderText()
    {
      return this.gaiaHeaderText_;
    }

    public String getResponseAuthenticationTypeParam()
    {
      return this.responseAuthenticationTypeParam_;
    }

    public String getResponseRetryCountParam()
    {
      return this.responseRetryCountParam_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAuthenticationType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getAuthenticationType());
      if (hasResponseAuthenticationTypeParam())
        i += CodedOutputStreamMicro.computeStringSize(2, getResponseAuthenticationTypeParam());
      if (hasResponseRetryCountParam())
        i += CodedOutputStreamMicro.computeStringSize(3, getResponseRetryCountParam());
      if (hasGaiaHeaderText())
        i += CodedOutputStreamMicro.computeStringSize(6, getGaiaHeaderText());
      if (hasGaiaDescriptionTextHtml())
        i += CodedOutputStreamMicro.computeStringSize(7, getGaiaDescriptionTextHtml());
      if (hasGaiaFooterTextHtml())
        i += CodedOutputStreamMicro.computeStringSize(8, getGaiaFooterTextHtml());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAuthenticationType()
    {
      return this.hasAuthenticationType;
    }

    public boolean hasGaiaDescriptionTextHtml()
    {
      return this.hasGaiaDescriptionTextHtml;
    }

    public boolean hasGaiaFooterTextHtml()
    {
      return this.hasGaiaFooterTextHtml;
    }

    public boolean hasGaiaHeaderText()
    {
      return this.hasGaiaHeaderText;
    }

    public boolean hasResponseAuthenticationTypeParam()
    {
      return this.hasResponseAuthenticationTypeParam;
    }

    public boolean hasResponseRetryCountParam()
    {
      return this.hasResponseRetryCountParam;
    }

    public AuthenticationChallenge mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setAuthenticationType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setResponseAuthenticationTypeParam(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setResponseRetryCountParam(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setGaiaHeaderText(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setGaiaDescriptionTextHtml(paramCodedInputStreamMicro.readString());
          break;
        case 66:
        }
        setGaiaFooterTextHtml(paramCodedInputStreamMicro.readString());
      }
    }

    public AuthenticationChallenge setAuthenticationType(int paramInt)
    {
      this.hasAuthenticationType = true;
      this.authenticationType_ = paramInt;
      return this;
    }

    public AuthenticationChallenge setGaiaDescriptionTextHtml(String paramString)
    {
      this.hasGaiaDescriptionTextHtml = true;
      this.gaiaDescriptionTextHtml_ = paramString;
      return this;
    }

    public AuthenticationChallenge setGaiaFooterTextHtml(String paramString)
    {
      this.hasGaiaFooterTextHtml = true;
      this.gaiaFooterTextHtml_ = paramString;
      return this;
    }

    public AuthenticationChallenge setGaiaHeaderText(String paramString)
    {
      this.hasGaiaHeaderText = true;
      this.gaiaHeaderText_ = paramString;
      return this;
    }

    public AuthenticationChallenge setResponseAuthenticationTypeParam(String paramString)
    {
      this.hasResponseAuthenticationTypeParam = true;
      this.responseAuthenticationTypeParam_ = paramString;
      return this;
    }

    public AuthenticationChallenge setResponseRetryCountParam(String paramString)
    {
      this.hasResponseRetryCountParam = true;
      this.responseRetryCountParam_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAuthenticationType())
        paramCodedOutputStreamMicro.writeInt32(1, getAuthenticationType());
      if (hasResponseAuthenticationTypeParam())
        paramCodedOutputStreamMicro.writeString(2, getResponseAuthenticationTypeParam());
      if (hasResponseRetryCountParam())
        paramCodedOutputStreamMicro.writeString(3, getResponseRetryCountParam());
      if (hasGaiaHeaderText())
        paramCodedOutputStreamMicro.writeString(6, getGaiaHeaderText());
      if (hasGaiaDescriptionTextHtml())
        paramCodedOutputStreamMicro.writeString(7, getGaiaDescriptionTextHtml());
      if (hasGaiaFooterTextHtml())
        paramCodedOutputStreamMicro.writeString(8, getGaiaFooterTextHtml());
    }
  }

  public static final class Challenge extends MessageMicro
  {
    private ChallengeProtos.AddressChallenge addressChallenge_ = null;
    private ChallengeProtos.AuthenticationChallenge authenticationChallenge_ = null;
    private int cachedSize = -1;
    private boolean hasAddressChallenge;
    private boolean hasAuthenticationChallenge;

    public ChallengeProtos.AddressChallenge getAddressChallenge()
    {
      return this.addressChallenge_;
    }

    public ChallengeProtos.AuthenticationChallenge getAuthenticationChallenge()
    {
      return this.authenticationChallenge_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAddressChallenge())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAddressChallenge());
      if (hasAuthenticationChallenge())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAuthenticationChallenge());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddressChallenge()
    {
      return this.hasAddressChallenge;
    }

    public boolean hasAuthenticationChallenge()
    {
      return this.hasAuthenticationChallenge;
    }

    public Challenge mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          ChallengeProtos.AddressChallenge localAddressChallenge = new ChallengeProtos.AddressChallenge();
          paramCodedInputStreamMicro.readMessage(localAddressChallenge);
          setAddressChallenge(localAddressChallenge);
          break;
        case 18:
        }
        ChallengeProtos.AuthenticationChallenge localAuthenticationChallenge = new ChallengeProtos.AuthenticationChallenge();
        paramCodedInputStreamMicro.readMessage(localAuthenticationChallenge);
        setAuthenticationChallenge(localAuthenticationChallenge);
      }
    }

    public Challenge setAddressChallenge(ChallengeProtos.AddressChallenge paramAddressChallenge)
    {
      if (paramAddressChallenge == null)
        throw new NullPointerException();
      this.hasAddressChallenge = true;
      this.addressChallenge_ = paramAddressChallenge;
      return this;
    }

    public Challenge setAuthenticationChallenge(ChallengeProtos.AuthenticationChallenge paramAuthenticationChallenge)
    {
      if (paramAuthenticationChallenge == null)
        throw new NullPointerException();
      this.hasAuthenticationChallenge = true;
      this.authenticationChallenge_ = paramAuthenticationChallenge;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddressChallenge())
        paramCodedOutputStreamMicro.writeMessage(1, getAddressChallenge());
      if (hasAuthenticationChallenge())
        paramCodedOutputStreamMicro.writeMessage(2, getAuthenticationChallenge());
    }
  }

  public static final class Country extends MessageMicro
  {
    private int cachedSize = -1;
    private String displayName_ = "";
    private boolean hasDisplayName;
    private boolean hasRegionCode;
    private String regionCode_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDisplayName()
    {
      return this.displayName_;
    }

    public String getRegionCode()
    {
      return this.regionCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRegionCode())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getRegionCode());
      if (hasDisplayName())
        i += CodedOutputStreamMicro.computeStringSize(2, getDisplayName());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDisplayName()
    {
      return this.hasDisplayName;
    }

    public boolean hasRegionCode()
    {
      return this.hasRegionCode;
    }

    public Country mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setRegionCode(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setDisplayName(paramCodedInputStreamMicro.readString());
      }
    }

    public Country setDisplayName(String paramString)
    {
      this.hasDisplayName = true;
      this.displayName_ = paramString;
      return this;
    }

    public Country setRegionCode(String paramString)
    {
      this.hasRegionCode = true;
      this.regionCode_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRegionCode())
        paramCodedOutputStreamMicro.writeString(1, getRegionCode());
      if (hasDisplayName())
        paramCodedOutputStreamMicro.writeString(2, getDisplayName());
    }
  }

  public static final class FormCheckbox extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean checked_ = false;
    private String description_ = "";
    private boolean hasChecked;
    private boolean hasDescription;
    private boolean hasId;
    private boolean hasRequired;
    private String id_ = "";
    private boolean required_ = false;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getChecked()
    {
      return this.checked_;
    }

    public String getDescription()
    {
      return this.description_;
    }

    public String getId()
    {
      return this.id_;
    }

    public boolean getRequired()
    {
      return this.required_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDescription())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDescription());
      if (hasChecked())
        i += CodedOutputStreamMicro.computeBoolSize(2, getChecked());
      if (hasRequired())
        i += CodedOutputStreamMicro.computeBoolSize(3, getRequired());
      if (hasId())
        i += CodedOutputStreamMicro.computeStringSize(4, getId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasChecked()
    {
      return this.hasChecked;
    }

    public boolean hasDescription()
    {
      return this.hasDescription;
    }

    public boolean hasId()
    {
      return this.hasId;
    }

    public boolean hasRequired()
    {
      return this.hasRequired;
    }

    public FormCheckbox mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDescription(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setChecked(paramCodedInputStreamMicro.readBool());
          break;
        case 24:
          setRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
        }
        setId(paramCodedInputStreamMicro.readString());
      }
    }

    public FormCheckbox setChecked(boolean paramBoolean)
    {
      this.hasChecked = true;
      this.checked_ = paramBoolean;
      return this;
    }

    public FormCheckbox setDescription(String paramString)
    {
      this.hasDescription = true;
      this.description_ = paramString;
      return this;
    }

    public FormCheckbox setId(String paramString)
    {
      this.hasId = true;
      this.id_ = paramString;
      return this;
    }

    public FormCheckbox setRequired(boolean paramBoolean)
    {
      this.hasRequired = true;
      this.required_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDescription())
        paramCodedOutputStreamMicro.writeString(1, getDescription());
      if (hasChecked())
        paramCodedOutputStreamMicro.writeBool(2, getChecked());
      if (hasRequired())
        paramCodedOutputStreamMicro.writeBool(3, getRequired());
      if (hasId())
        paramCodedOutputStreamMicro.writeString(4, getId());
    }
  }

  public static final class InputValidationError extends MessageMicro
  {
    private int cachedSize = -1;
    private String errorMessage_ = "";
    private boolean hasErrorMessage;
    private boolean hasInputField;
    private int inputField_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getErrorMessage()
    {
      return this.errorMessage_;
    }

    public int getInputField()
    {
      return this.inputField_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInputField())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getInputField());
      if (hasErrorMessage())
        i += CodedOutputStreamMicro.computeStringSize(2, getErrorMessage());
      this.cachedSize = i;
      return i;
    }

    public boolean hasErrorMessage()
    {
      return this.hasErrorMessage;
    }

    public boolean hasInputField()
    {
      return this.hasInputField;
    }

    public InputValidationError mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 8:
          setInputField(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setErrorMessage(paramCodedInputStreamMicro.readString());
      }
    }

    public InputValidationError setErrorMessage(String paramString)
    {
      this.hasErrorMessage = true;
      this.errorMessage_ = paramString;
      return this;
    }

    public InputValidationError setInputField(int paramInt)
    {
      this.hasInputField = true;
      this.inputField_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInputField())
        paramCodedOutputStreamMicro.writeInt32(1, getInputField());
      if (hasErrorMessage())
        paramCodedOutputStreamMicro.writeString(2, getErrorMessage());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.ChallengeProtos
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class CommonDevice
{
  public static final class BillingAddressSpec extends MessageMicro
  {
    private int billingAddressType_ = 1;
    private int cachedSize = -1;
    private boolean hasBillingAddressType;
    private List<Integer> requiredField_ = Collections.emptyList();

    public BillingAddressSpec addRequiredField(int paramInt)
    {
      if (this.requiredField_.isEmpty())
        this.requiredField_ = new ArrayList();
      this.requiredField_.add(Integer.valueOf(paramInt));
      return this;
    }

    public int getBillingAddressType()
    {
      return this.billingAddressType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getRequiredFieldCount()
    {
      return this.requiredField_.size();
    }

    public List<Integer> getRequiredFieldList()
    {
      return this.requiredField_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBillingAddressType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getBillingAddressType());
      int j = 0;
      Iterator localIterator = getRequiredFieldList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator.next()).intValue());
      int k = i + j + 1 * getRequiredFieldList().size();
      this.cachedSize = k;
      return k;
    }

    public boolean hasBillingAddressType()
    {
      return this.hasBillingAddressType;
    }

    public BillingAddressSpec mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBillingAddressType(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
        }
        addRequiredField(paramCodedInputStreamMicro.readInt32());
      }
    }

    public BillingAddressSpec setBillingAddressType(int paramInt)
    {
      this.hasBillingAddressType = true;
      this.billingAddressType_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBillingAddressType())
        paramCodedOutputStreamMicro.writeInt32(1, getBillingAddressType());
      Iterator localIterator = getRequiredFieldList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeInt32(2, ((Integer)localIterator.next()).intValue());
    }
  }

  public static final class CarrierBillingCredentials extends MessageMicro
  {
    private int cachedSize = -1;
    private long expiration_ = 0L;
    private boolean hasExpiration;
    private boolean hasValue;
    private String value_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getExpiration()
    {
      return this.expiration_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasValue())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getValue());
      if (hasExpiration())
        i += CodedOutputStreamMicro.computeInt64Size(2, getExpiration());
      this.cachedSize = i;
      return i;
    }

    public String getValue()
    {
      return this.value_;
    }

    public boolean hasExpiration()
    {
      return this.hasExpiration;
    }

    public boolean hasValue()
    {
      return this.hasValue;
    }

    public CarrierBillingCredentials mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setValue(paramCodedInputStreamMicro.readString());
          break;
        case 16:
        }
        setExpiration(paramCodedInputStreamMicro.readInt64());
      }
    }

    public CarrierBillingCredentials setExpiration(long paramLong)
    {
      this.hasExpiration = true;
      this.expiration_ = paramLong;
      return this;
    }

    public CarrierBillingCredentials setValue(String paramString)
    {
      this.hasValue = true;
      this.value_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasValue())
        paramCodedOutputStreamMicro.writeString(1, getValue());
      if (hasExpiration())
        paramCodedOutputStreamMicro.writeInt64(2, getExpiration());
    }
  }

  public static final class CarrierBillingInstrument extends MessageMicro
  {
    private CommonDevice.CarrierTos acceptedCarrierTos_ = null;
    private String accountType_ = "";
    private int cachedSize = -1;
    private CommonDevice.CarrierBillingCredentials credentials_ = null;
    private String currencyCode_ = "";
    private EncryptedSubscriberInfo encryptedSubscriberInfo_ = null;
    private boolean hasAcceptedCarrierTos;
    private boolean hasAccountType;
    private boolean hasCredentials;
    private boolean hasCurrencyCode;
    private boolean hasEncryptedSubscriberInfo;
    private boolean hasInstrumentKey;
    private boolean hasSubscriberIdentifier;
    private boolean hasTransactionLimit;
    private String instrumentKey_ = "";
    private String subscriberIdentifier_ = "";
    private long transactionLimit_ = 0L;

    public CommonDevice.CarrierTos getAcceptedCarrierTos()
    {
      return this.acceptedCarrierTos_;
    }

    public String getAccountType()
    {
      return this.accountType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public CommonDevice.CarrierBillingCredentials getCredentials()
    {
      return this.credentials_;
    }

    public String getCurrencyCode()
    {
      return this.currencyCode_;
    }

    public EncryptedSubscriberInfo getEncryptedSubscriberInfo()
    {
      return this.encryptedSubscriberInfo_;
    }

    public String getInstrumentKey()
    {
      return this.instrumentKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrumentKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getInstrumentKey());
      if (hasAccountType())
        i += CodedOutputStreamMicro.computeStringSize(2, getAccountType());
      if (hasCurrencyCode())
        i += CodedOutputStreamMicro.computeStringSize(3, getCurrencyCode());
      if (hasTransactionLimit())
        i += CodedOutputStreamMicro.computeInt64Size(4, getTransactionLimit());
      if (hasSubscriberIdentifier())
        i += CodedOutputStreamMicro.computeStringSize(5, getSubscriberIdentifier());
      if (hasEncryptedSubscriberInfo())
        i += CodedOutputStreamMicro.computeMessageSize(6, getEncryptedSubscriberInfo());
      if (hasCredentials())
        i += CodedOutputStreamMicro.computeMessageSize(7, getCredentials());
      if (hasAcceptedCarrierTos())
        i += CodedOutputStreamMicro.computeMessageSize(8, getAcceptedCarrierTos());
      this.cachedSize = i;
      return i;
    }

    public String getSubscriberIdentifier()
    {
      return this.subscriberIdentifier_;
    }

    public long getTransactionLimit()
    {
      return this.transactionLimit_;
    }

    public boolean hasAcceptedCarrierTos()
    {
      return this.hasAcceptedCarrierTos;
    }

    public boolean hasAccountType()
    {
      return this.hasAccountType;
    }

    public boolean hasCredentials()
    {
      return this.hasCredentials;
    }

    public boolean hasCurrencyCode()
    {
      return this.hasCurrencyCode;
    }

    public boolean hasEncryptedSubscriberInfo()
    {
      return this.hasEncryptedSubscriberInfo;
    }

    public boolean hasInstrumentKey()
    {
      return this.hasInstrumentKey;
    }

    public boolean hasSubscriberIdentifier()
    {
      return this.hasSubscriberIdentifier;
    }

    public boolean hasTransactionLimit()
    {
      return this.hasTransactionLimit;
    }

    public CarrierBillingInstrument mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setInstrumentKey(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setAccountType(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setCurrencyCode(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setTransactionLimit(paramCodedInputStreamMicro.readInt64());
          break;
        case 42:
          setSubscriberIdentifier(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          EncryptedSubscriberInfo localEncryptedSubscriberInfo = new EncryptedSubscriberInfo();
          paramCodedInputStreamMicro.readMessage(localEncryptedSubscriberInfo);
          setEncryptedSubscriberInfo(localEncryptedSubscriberInfo);
          break;
        case 58:
          CommonDevice.CarrierBillingCredentials localCarrierBillingCredentials = new CommonDevice.CarrierBillingCredentials();
          paramCodedInputStreamMicro.readMessage(localCarrierBillingCredentials);
          setCredentials(localCarrierBillingCredentials);
          break;
        case 66:
        }
        CommonDevice.CarrierTos localCarrierTos = new CommonDevice.CarrierTos();
        paramCodedInputStreamMicro.readMessage(localCarrierTos);
        setAcceptedCarrierTos(localCarrierTos);
      }
    }

    public CarrierBillingInstrument setAcceptedCarrierTos(CommonDevice.CarrierTos paramCarrierTos)
    {
      if (paramCarrierTos == null)
        throw new NullPointerException();
      this.hasAcceptedCarrierTos = true;
      this.acceptedCarrierTos_ = paramCarrierTos;
      return this;
    }

    public CarrierBillingInstrument setAccountType(String paramString)
    {
      this.hasAccountType = true;
      this.accountType_ = paramString;
      return this;
    }

    public CarrierBillingInstrument setCredentials(CommonDevice.CarrierBillingCredentials paramCarrierBillingCredentials)
    {
      if (paramCarrierBillingCredentials == null)
        throw new NullPointerException();
      this.hasCredentials = true;
      this.credentials_ = paramCarrierBillingCredentials;
      return this;
    }

    public CarrierBillingInstrument setCurrencyCode(String paramString)
    {
      this.hasCurrencyCode = true;
      this.currencyCode_ = paramString;
      return this;
    }

    public CarrierBillingInstrument setEncryptedSubscriberInfo(EncryptedSubscriberInfo paramEncryptedSubscriberInfo)
    {
      if (paramEncryptedSubscriberInfo == null)
        throw new NullPointerException();
      this.hasEncryptedSubscriberInfo = true;
      this.encryptedSubscriberInfo_ = paramEncryptedSubscriberInfo;
      return this;
    }

    public CarrierBillingInstrument setInstrumentKey(String paramString)
    {
      this.hasInstrumentKey = true;
      this.instrumentKey_ = paramString;
      return this;
    }

    public CarrierBillingInstrument setSubscriberIdentifier(String paramString)
    {
      this.hasSubscriberIdentifier = true;
      this.subscriberIdentifier_ = paramString;
      return this;
    }

    public CarrierBillingInstrument setTransactionLimit(long paramLong)
    {
      this.hasTransactionLimit = true;
      this.transactionLimit_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstrumentKey())
        paramCodedOutputStreamMicro.writeString(1, getInstrumentKey());
      if (hasAccountType())
        paramCodedOutputStreamMicro.writeString(2, getAccountType());
      if (hasCurrencyCode())
        paramCodedOutputStreamMicro.writeString(3, getCurrencyCode());
      if (hasTransactionLimit())
        paramCodedOutputStreamMicro.writeInt64(4, getTransactionLimit());
      if (hasSubscriberIdentifier())
        paramCodedOutputStreamMicro.writeString(5, getSubscriberIdentifier());
      if (hasEncryptedSubscriberInfo())
        paramCodedOutputStreamMicro.writeMessage(6, getEncryptedSubscriberInfo());
      if (hasCredentials())
        paramCodedOutputStreamMicro.writeMessage(7, getCredentials());
      if (hasAcceptedCarrierTos())
        paramCodedOutputStreamMicro.writeMessage(8, getAcceptedCarrierTos());
    }
  }

  public static final class CarrierBillingInstrumentStatus extends MessageMicro
  {
    private int apiVersion_ = 0;
    private boolean associationRequired_ = false;
    private int cachedSize = -1;
    private CommonDevice.PasswordPrompt carrierPasswordPrompt_ = null;
    private CommonDevice.CarrierTos carrierTos_ = null;
    private CommonDevice.DeviceAssociation deviceAssociation_ = null;
    private boolean hasApiVersion;
    private boolean hasAssociationRequired;
    private boolean hasCarrierPasswordPrompt;
    private boolean hasCarrierTos;
    private boolean hasDeviceAssociation;
    private boolean hasName;
    private boolean hasPasswordRequired;
    private String name_ = "";
    private boolean passwordRequired_ = false;

    public int getApiVersion()
    {
      return this.apiVersion_;
    }

    public boolean getAssociationRequired()
    {
      return this.associationRequired_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public CommonDevice.PasswordPrompt getCarrierPasswordPrompt()
    {
      return this.carrierPasswordPrompt_;
    }

    public CommonDevice.CarrierTos getCarrierTos()
    {
      return this.carrierTos_;
    }

    public CommonDevice.DeviceAssociation getDeviceAssociation()
    {
      return this.deviceAssociation_;
    }

    public String getName()
    {
      return this.name_;
    }

    public boolean getPasswordRequired()
    {
      return this.passwordRequired_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCarrierTos())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getCarrierTos());
      if (hasAssociationRequired())
        i += CodedOutputStreamMicro.computeBoolSize(2, getAssociationRequired());
      if (hasPasswordRequired())
        i += CodedOutputStreamMicro.computeBoolSize(3, getPasswordRequired());
      if (hasCarrierPasswordPrompt())
        i += CodedOutputStreamMicro.computeMessageSize(4, getCarrierPasswordPrompt());
      if (hasApiVersion())
        i += CodedOutputStreamMicro.computeInt32Size(5, getApiVersion());
      if (hasName())
        i += CodedOutputStreamMicro.computeStringSize(6, getName());
      if (hasDeviceAssociation())
        i += CodedOutputStreamMicro.computeMessageSize(7, getDeviceAssociation());
      this.cachedSize = i;
      return i;
    }

    public boolean hasApiVersion()
    {
      return this.hasApiVersion;
    }

    public boolean hasAssociationRequired()
    {
      return this.hasAssociationRequired;
    }

    public boolean hasCarrierPasswordPrompt()
    {
      return this.hasCarrierPasswordPrompt;
    }

    public boolean hasCarrierTos()
    {
      return this.hasCarrierTos;
    }

    public boolean hasDeviceAssociation()
    {
      return this.hasDeviceAssociation;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasPasswordRequired()
    {
      return this.hasPasswordRequired;
    }

    public CarrierBillingInstrumentStatus mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          CommonDevice.CarrierTos localCarrierTos = new CommonDevice.CarrierTos();
          paramCodedInputStreamMicro.readMessage(localCarrierTos);
          setCarrierTos(localCarrierTos);
          break;
        case 16:
          setAssociationRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 24:
          setPasswordRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          CommonDevice.PasswordPrompt localPasswordPrompt = new CommonDevice.PasswordPrompt();
          paramCodedInputStreamMicro.readMessage(localPasswordPrompt);
          setCarrierPasswordPrompt(localPasswordPrompt);
          break;
        case 40:
          setApiVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 50:
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 58:
        }
        CommonDevice.DeviceAssociation localDeviceAssociation = new CommonDevice.DeviceAssociation();
        paramCodedInputStreamMicro.readMessage(localDeviceAssociation);
        setDeviceAssociation(localDeviceAssociation);
      }
    }

    public CarrierBillingInstrumentStatus setApiVersion(int paramInt)
    {
      this.hasApiVersion = true;
      this.apiVersion_ = paramInt;
      return this;
    }

    public CarrierBillingInstrumentStatus setAssociationRequired(boolean paramBoolean)
    {
      this.hasAssociationRequired = true;
      this.associationRequired_ = paramBoolean;
      return this;
    }

    public CarrierBillingInstrumentStatus setCarrierPasswordPrompt(CommonDevice.PasswordPrompt paramPasswordPrompt)
    {
      if (paramPasswordPrompt == null)
        throw new NullPointerException();
      this.hasCarrierPasswordPrompt = true;
      this.carrierPasswordPrompt_ = paramPasswordPrompt;
      return this;
    }

    public CarrierBillingInstrumentStatus setCarrierTos(CommonDevice.CarrierTos paramCarrierTos)
    {
      if (paramCarrierTos == null)
        throw new NullPointerException();
      this.hasCarrierTos = true;
      this.carrierTos_ = paramCarrierTos;
      return this;
    }

    public CarrierBillingInstrumentStatus setDeviceAssociation(CommonDevice.DeviceAssociation paramDeviceAssociation)
    {
      if (paramDeviceAssociation == null)
        throw new NullPointerException();
      this.hasDeviceAssociation = true;
      this.deviceAssociation_ = paramDeviceAssociation;
      return this;
    }

    public CarrierBillingInstrumentStatus setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public CarrierBillingInstrumentStatus setPasswordRequired(boolean paramBoolean)
    {
      this.hasPasswordRequired = true;
      this.passwordRequired_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCarrierTos())
        paramCodedOutputStreamMicro.writeMessage(1, getCarrierTos());
      if (hasAssociationRequired())
        paramCodedOutputStreamMicro.writeBool(2, getAssociationRequired());
      if (hasPasswordRequired())
        paramCodedOutputStreamMicro.writeBool(3, getPasswordRequired());
      if (hasCarrierPasswordPrompt())
        paramCodedOutputStreamMicro.writeMessage(4, getCarrierPasswordPrompt());
      if (hasApiVersion())
        paramCodedOutputStreamMicro.writeInt32(5, getApiVersion());
      if (hasName())
        paramCodedOutputStreamMicro.writeString(6, getName());
      if (hasDeviceAssociation())
        paramCodedOutputStreamMicro.writeMessage(7, getDeviceAssociation());
    }
  }

  public static final class CarrierTos extends MessageMicro
  {
    private int cachedSize = -1;
    private CommonDevice.CarrierTosEntry dcbTos_ = null;
    private boolean hasDcbTos;
    private boolean hasNeedsDcbTosAcceptance;
    private boolean hasNeedsPiiTosAcceptance;
    private boolean hasPiiTos;
    private boolean needsDcbTosAcceptance_ = false;
    private boolean needsPiiTosAcceptance_ = false;
    private CommonDevice.CarrierTosEntry piiTos_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public CommonDevice.CarrierTosEntry getDcbTos()
    {
      return this.dcbTos_;
    }

    public boolean getNeedsDcbTosAcceptance()
    {
      return this.needsDcbTosAcceptance_;
    }

    public boolean getNeedsPiiTosAcceptance()
    {
      return this.needsPiiTosAcceptance_;
    }

    public CommonDevice.CarrierTosEntry getPiiTos()
    {
      return this.piiTos_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDcbTos())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDcbTos());
      if (hasPiiTos())
        i += CodedOutputStreamMicro.computeMessageSize(2, getPiiTos());
      if (hasNeedsDcbTosAcceptance())
        i += CodedOutputStreamMicro.computeBoolSize(3, getNeedsDcbTosAcceptance());
      if (hasNeedsPiiTosAcceptance())
        i += CodedOutputStreamMicro.computeBoolSize(4, getNeedsPiiTosAcceptance());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDcbTos()
    {
      return this.hasDcbTos;
    }

    public boolean hasNeedsDcbTosAcceptance()
    {
      return this.hasNeedsDcbTosAcceptance;
    }

    public boolean hasNeedsPiiTosAcceptance()
    {
      return this.hasNeedsPiiTosAcceptance;
    }

    public boolean hasPiiTos()
    {
      return this.hasPiiTos;
    }

    public CarrierTos mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          CommonDevice.CarrierTosEntry localCarrierTosEntry2 = new CommonDevice.CarrierTosEntry();
          paramCodedInputStreamMicro.readMessage(localCarrierTosEntry2);
          setDcbTos(localCarrierTosEntry2);
          break;
        case 18:
          CommonDevice.CarrierTosEntry localCarrierTosEntry1 = new CommonDevice.CarrierTosEntry();
          paramCodedInputStreamMicro.readMessage(localCarrierTosEntry1);
          setPiiTos(localCarrierTosEntry1);
          break;
        case 24:
          setNeedsDcbTosAcceptance(paramCodedInputStreamMicro.readBool());
          break;
        case 32:
        }
        setNeedsPiiTosAcceptance(paramCodedInputStreamMicro.readBool());
      }
    }

    public CarrierTos setDcbTos(CommonDevice.CarrierTosEntry paramCarrierTosEntry)
    {
      if (paramCarrierTosEntry == null)
        throw new NullPointerException();
      this.hasDcbTos = true;
      this.dcbTos_ = paramCarrierTosEntry;
      return this;
    }

    public CarrierTos setNeedsDcbTosAcceptance(boolean paramBoolean)
    {
      this.hasNeedsDcbTosAcceptance = true;
      this.needsDcbTosAcceptance_ = paramBoolean;
      return this;
    }

    public CarrierTos setNeedsPiiTosAcceptance(boolean paramBoolean)
    {
      this.hasNeedsPiiTosAcceptance = true;
      this.needsPiiTosAcceptance_ = paramBoolean;
      return this;
    }

    public CarrierTos setPiiTos(CommonDevice.CarrierTosEntry paramCarrierTosEntry)
    {
      if (paramCarrierTosEntry == null)
        throw new NullPointerException();
      this.hasPiiTos = true;
      this.piiTos_ = paramCarrierTosEntry;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDcbTos())
        paramCodedOutputStreamMicro.writeMessage(1, getDcbTos());
      if (hasPiiTos())
        paramCodedOutputStreamMicro.writeMessage(2, getPiiTos());
      if (hasNeedsDcbTosAcceptance())
        paramCodedOutputStreamMicro.writeBool(3, getNeedsDcbTosAcceptance());
      if (hasNeedsPiiTosAcceptance())
        paramCodedOutputStreamMicro.writeBool(4, getNeedsPiiTosAcceptance());
    }
  }

  public static final class CarrierTosEntry extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUrl;
    private boolean hasVersion;
    private String url_ = "";
    private String version_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUrl());
      if (hasVersion())
        i += CodedOutputStreamMicro.computeStringSize(2, getVersion());
      this.cachedSize = i;
      return i;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public String getVersion()
    {
      return this.version_;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public boolean hasVersion()
    {
      return this.hasVersion;
    }

    public CarrierTosEntry mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setVersion(paramCodedInputStreamMicro.readString());
      }
    }

    public CarrierTosEntry setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public CarrierTosEntry setVersion(String paramString)
    {
      this.hasVersion = true;
      this.version_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(1, getUrl());
      if (hasVersion())
        paramCodedOutputStreamMicro.writeString(2, getVersion());
    }
  }

  public static final class CreditCardInstrument extends MessageMicro
  {
    private int cachedSize = -1;
    private List<CommonDevice.EfeParam> escrowEfeParam_ = Collections.emptyList();
    private String escrowHandle_ = "";
    private int expirationMonth_ = 0;
    private int expirationYear_ = 0;
    private boolean hasEscrowHandle;
    private boolean hasExpirationMonth;
    private boolean hasExpirationYear;
    private boolean hasLastDigits;
    private boolean hasType;
    private String lastDigits_ = "";
    private int type_ = 0;

    public CreditCardInstrument addEscrowEfeParam(CommonDevice.EfeParam paramEfeParam)
    {
      if (paramEfeParam == null)
        throw new NullPointerException();
      if (this.escrowEfeParam_.isEmpty())
        this.escrowEfeParam_ = new ArrayList();
      this.escrowEfeParam_.add(paramEfeParam);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<CommonDevice.EfeParam> getEscrowEfeParamList()
    {
      return this.escrowEfeParam_;
    }

    public String getEscrowHandle()
    {
      return this.escrowHandle_;
    }

    public int getExpirationMonth()
    {
      return this.expirationMonth_;
    }

    public int getExpirationYear()
    {
      return this.expirationYear_;
    }

    public String getLastDigits()
    {
      return this.lastDigits_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getType());
      if (hasEscrowHandle())
        i += CodedOutputStreamMicro.computeStringSize(2, getEscrowHandle());
      if (hasLastDigits())
        i += CodedOutputStreamMicro.computeStringSize(3, getLastDigits());
      if (hasExpirationMonth())
        i += CodedOutputStreamMicro.computeInt32Size(4, getExpirationMonth());
      if (hasExpirationYear())
        i += CodedOutputStreamMicro.computeInt32Size(5, getExpirationYear());
      Iterator localIterator = getEscrowEfeParamList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(6, (CommonDevice.EfeParam)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public int getType()
    {
      return this.type_;
    }

    public boolean hasEscrowHandle()
    {
      return this.hasEscrowHandle;
    }

    public boolean hasExpirationMonth()
    {
      return this.hasExpirationMonth;
    }

    public boolean hasExpirationYear()
    {
      return this.hasExpirationYear;
    }

    public boolean hasLastDigits()
    {
      return this.hasLastDigits;
    }

    public boolean hasType()
    {
      return this.hasType;
    }

    public CreditCardInstrument mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setEscrowHandle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setLastDigits(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setExpirationMonth(paramCodedInputStreamMicro.readInt32());
          break;
        case 40:
          setExpirationYear(paramCodedInputStreamMicro.readInt32());
          break;
        case 50:
        }
        CommonDevice.EfeParam localEfeParam = new CommonDevice.EfeParam();
        paramCodedInputStreamMicro.readMessage(localEfeParam);
        addEscrowEfeParam(localEfeParam);
      }
    }

    public CreditCardInstrument setEscrowHandle(String paramString)
    {
      this.hasEscrowHandle = true;
      this.escrowHandle_ = paramString;
      return this;
    }

    public CreditCardInstrument setExpirationMonth(int paramInt)
    {
      this.hasExpirationMonth = true;
      this.expirationMonth_ = paramInt;
      return this;
    }

    public CreditCardInstrument setExpirationYear(int paramInt)
    {
      this.hasExpirationYear = true;
      this.expirationYear_ = paramInt;
      return this;
    }

    public CreditCardInstrument setLastDigits(String paramString)
    {
      this.hasLastDigits = true;
      this.lastDigits_ = paramString;
      return this;
    }

    public CreditCardInstrument setType(int paramInt)
    {
      this.hasType = true;
      this.type_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasType())
        paramCodedOutputStreamMicro.writeInt32(1, getType());
      if (hasEscrowHandle())
        paramCodedOutputStreamMicro.writeString(2, getEscrowHandle());
      if (hasLastDigits())
        paramCodedOutputStreamMicro.writeString(3, getLastDigits());
      if (hasExpirationMonth())
        paramCodedOutputStreamMicro.writeInt32(4, getExpirationMonth());
      if (hasExpirationYear())
        paramCodedOutputStreamMicro.writeInt32(5, getExpirationYear());
      Iterator localIterator = getEscrowEfeParamList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(6, (CommonDevice.EfeParam)localIterator.next());
    }
  }

  public static final class DeviceAssociation extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUserTokenRequestAddress;
    private boolean hasUserTokenRequestMessage;
    private String userTokenRequestAddress_ = "";
    private String userTokenRequestMessage_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUserTokenRequestMessage())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUserTokenRequestMessage());
      if (hasUserTokenRequestAddress())
        i += CodedOutputStreamMicro.computeStringSize(2, getUserTokenRequestAddress());
      this.cachedSize = i;
      return i;
    }

    public String getUserTokenRequestAddress()
    {
      return this.userTokenRequestAddress_;
    }

    public String getUserTokenRequestMessage()
    {
      return this.userTokenRequestMessage_;
    }

    public boolean hasUserTokenRequestAddress()
    {
      return this.hasUserTokenRequestAddress;
    }

    public boolean hasUserTokenRequestMessage()
    {
      return this.hasUserTokenRequestMessage;
    }

    public DeviceAssociation mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUserTokenRequestMessage(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setUserTokenRequestAddress(paramCodedInputStreamMicro.readString());
      }
    }

    public DeviceAssociation setUserTokenRequestAddress(String paramString)
    {
      this.hasUserTokenRequestAddress = true;
      this.userTokenRequestAddress_ = paramString;
      return this;
    }

    public DeviceAssociation setUserTokenRequestMessage(String paramString)
    {
      this.hasUserTokenRequestMessage = true;
      this.userTokenRequestMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUserTokenRequestMessage())
        paramCodedOutputStreamMicro.writeString(1, getUserTokenRequestMessage());
      if (hasUserTokenRequestAddress())
        paramCodedOutputStreamMicro.writeString(2, getUserTokenRequestAddress());
    }
  }

  public static final class EfeParam extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasKey;
    private boolean hasValue;
    private int key_ = 1;
    private String value_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getKey()
    {
      return this.key_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasKey())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getKey());
      if (hasValue())
        i += CodedOutputStreamMicro.computeStringSize(2, getValue());
      this.cachedSize = i;
      return i;
    }

    public String getValue()
    {
      return this.value_;
    }

    public boolean hasKey()
    {
      return this.hasKey;
    }

    public boolean hasValue()
    {
      return this.hasValue;
    }

    public EfeParam mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setKey(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setValue(paramCodedInputStreamMicro.readString());
      }
    }

    public EfeParam setKey(int paramInt)
    {
      this.hasKey = true;
      this.key_ = paramInt;
      return this;
    }

    public EfeParam setValue(String paramString)
    {
      this.hasValue = true;
      this.value_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasKey())
        paramCodedOutputStreamMicro.writeInt32(1, getKey());
      if (hasValue())
        paramCodedOutputStreamMicro.writeString(2, getValue());
    }
  }

  public static final class Instrument extends MessageMicro
  {
    private CommonDevice.BillingAddressSpec billingAddressSpec_ = null;
    private BillingAddress.Address billingAddress_ = null;
    private int cachedSize = -1;
    private CommonDevice.CarrierBillingInstrumentStatus carrierBillingStatus_ = null;
    private CommonDevice.CarrierBillingInstrument carrierBilling_ = null;
    private CommonDevice.CreditCardInstrument creditCard_ = null;
    private String displayTitle_ = "";
    private boolean hasBillingAddress;
    private boolean hasBillingAddressSpec;
    private boolean hasCarrierBilling;
    private boolean hasCarrierBillingStatus;
    private boolean hasCreditCard;
    private boolean hasDisplayTitle;
    private boolean hasInstrumentFamily;
    private boolean hasInstrumentId;
    private boolean hasTopupInfo;
    private int instrumentFamily_ = 0;
    private String instrumentId_ = "";
    private CommonDevice.TopupInfo topupInfo_ = null;

    public BillingAddress.Address getBillingAddress()
    {
      return this.billingAddress_;
    }

    public CommonDevice.BillingAddressSpec getBillingAddressSpec()
    {
      return this.billingAddressSpec_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public CommonDevice.CarrierBillingInstrument getCarrierBilling()
    {
      return this.carrierBilling_;
    }

    public CommonDevice.CarrierBillingInstrumentStatus getCarrierBillingStatus()
    {
      return this.carrierBillingStatus_;
    }

    public CommonDevice.CreditCardInstrument getCreditCard()
    {
      return this.creditCard_;
    }

    public String getDisplayTitle()
    {
      return this.displayTitle_;
    }

    public int getInstrumentFamily()
    {
      return this.instrumentFamily_;
    }

    public String getInstrumentId()
    {
      return this.instrumentId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrumentId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getInstrumentId());
      if (hasBillingAddress())
        i += CodedOutputStreamMicro.computeMessageSize(2, getBillingAddress());
      if (hasCreditCard())
        i += CodedOutputStreamMicro.computeMessageSize(3, getCreditCard());
      if (hasCarrierBilling())
        i += CodedOutputStreamMicro.computeMessageSize(4, getCarrierBilling());
      if (hasBillingAddressSpec())
        i += CodedOutputStreamMicro.computeMessageSize(5, getBillingAddressSpec());
      if (hasInstrumentFamily())
        i += CodedOutputStreamMicro.computeInt32Size(6, getInstrumentFamily());
      if (hasCarrierBillingStatus())
        i += CodedOutputStreamMicro.computeMessageSize(7, getCarrierBillingStatus());
      if (hasDisplayTitle())
        i += CodedOutputStreamMicro.computeStringSize(8, getDisplayTitle());
      if (hasTopupInfo())
        i += CodedOutputStreamMicro.computeMessageSize(9, getTopupInfo());
      this.cachedSize = i;
      return i;
    }

    public CommonDevice.TopupInfo getTopupInfo()
    {
      return this.topupInfo_;
    }

    public boolean hasBillingAddress()
    {
      return this.hasBillingAddress;
    }

    public boolean hasBillingAddressSpec()
    {
      return this.hasBillingAddressSpec;
    }

    public boolean hasCarrierBilling()
    {
      return this.hasCarrierBilling;
    }

    public boolean hasCarrierBillingStatus()
    {
      return this.hasCarrierBillingStatus;
    }

    public boolean hasCreditCard()
    {
      return this.hasCreditCard;
    }

    public boolean hasDisplayTitle()
    {
      return this.hasDisplayTitle;
    }

    public boolean hasInstrumentFamily()
    {
      return this.hasInstrumentFamily;
    }

    public boolean hasInstrumentId()
    {
      return this.hasInstrumentId;
    }

    public boolean hasTopupInfo()
    {
      return this.hasTopupInfo;
    }

    public Instrument mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setInstrumentId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          BillingAddress.Address localAddress = new BillingAddress.Address();
          paramCodedInputStreamMicro.readMessage(localAddress);
          setBillingAddress(localAddress);
          break;
        case 26:
          CommonDevice.CreditCardInstrument localCreditCardInstrument = new CommonDevice.CreditCardInstrument();
          paramCodedInputStreamMicro.readMessage(localCreditCardInstrument);
          setCreditCard(localCreditCardInstrument);
          break;
        case 34:
          CommonDevice.CarrierBillingInstrument localCarrierBillingInstrument = new CommonDevice.CarrierBillingInstrument();
          paramCodedInputStreamMicro.readMessage(localCarrierBillingInstrument);
          setCarrierBilling(localCarrierBillingInstrument);
          break;
        case 42:
          CommonDevice.BillingAddressSpec localBillingAddressSpec = new CommonDevice.BillingAddressSpec();
          paramCodedInputStreamMicro.readMessage(localBillingAddressSpec);
          setBillingAddressSpec(localBillingAddressSpec);
          break;
        case 48:
          setInstrumentFamily(paramCodedInputStreamMicro.readInt32());
          break;
        case 58:
          CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = new CommonDevice.CarrierBillingInstrumentStatus();
          paramCodedInputStreamMicro.readMessage(localCarrierBillingInstrumentStatus);
          setCarrierBillingStatus(localCarrierBillingInstrumentStatus);
          break;
        case 66:
          setDisplayTitle(paramCodedInputStreamMicro.readString());
          break;
        case 74:
        }
        CommonDevice.TopupInfo localTopupInfo = new CommonDevice.TopupInfo();
        paramCodedInputStreamMicro.readMessage(localTopupInfo);
        setTopupInfo(localTopupInfo);
      }
    }

    public Instrument setBillingAddress(BillingAddress.Address paramAddress)
    {
      if (paramAddress == null)
        throw new NullPointerException();
      this.hasBillingAddress = true;
      this.billingAddress_ = paramAddress;
      return this;
    }

    public Instrument setBillingAddressSpec(CommonDevice.BillingAddressSpec paramBillingAddressSpec)
    {
      if (paramBillingAddressSpec == null)
        throw new NullPointerException();
      this.hasBillingAddressSpec = true;
      this.billingAddressSpec_ = paramBillingAddressSpec;
      return this;
    }

    public Instrument setCarrierBilling(CommonDevice.CarrierBillingInstrument paramCarrierBillingInstrument)
    {
      if (paramCarrierBillingInstrument == null)
        throw new NullPointerException();
      this.hasCarrierBilling = true;
      this.carrierBilling_ = paramCarrierBillingInstrument;
      return this;
    }

    public Instrument setCarrierBillingStatus(CommonDevice.CarrierBillingInstrumentStatus paramCarrierBillingInstrumentStatus)
    {
      if (paramCarrierBillingInstrumentStatus == null)
        throw new NullPointerException();
      this.hasCarrierBillingStatus = true;
      this.carrierBillingStatus_ = paramCarrierBillingInstrumentStatus;
      return this;
    }

    public Instrument setCreditCard(CommonDevice.CreditCardInstrument paramCreditCardInstrument)
    {
      if (paramCreditCardInstrument == null)
        throw new NullPointerException();
      this.hasCreditCard = true;
      this.creditCard_ = paramCreditCardInstrument;
      return this;
    }

    public Instrument setDisplayTitle(String paramString)
    {
      this.hasDisplayTitle = true;
      this.displayTitle_ = paramString;
      return this;
    }

    public Instrument setInstrumentFamily(int paramInt)
    {
      this.hasInstrumentFamily = true;
      this.instrumentFamily_ = paramInt;
      return this;
    }

    public Instrument setInstrumentId(String paramString)
    {
      this.hasInstrumentId = true;
      this.instrumentId_ = paramString;
      return this;
    }

    public Instrument setTopupInfo(CommonDevice.TopupInfo paramTopupInfo)
    {
      if (paramTopupInfo == null)
        throw new NullPointerException();
      this.hasTopupInfo = true;
      this.topupInfo_ = paramTopupInfo;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstrumentId())
        paramCodedOutputStreamMicro.writeString(1, getInstrumentId());
      if (hasBillingAddress())
        paramCodedOutputStreamMicro.writeMessage(2, getBillingAddress());
      if (hasCreditCard())
        paramCodedOutputStreamMicro.writeMessage(3, getCreditCard());
      if (hasCarrierBilling())
        paramCodedOutputStreamMicro.writeMessage(4, getCarrierBilling());
      if (hasBillingAddressSpec())
        paramCodedOutputStreamMicro.writeMessage(5, getBillingAddressSpec());
      if (hasInstrumentFamily())
        paramCodedOutputStreamMicro.writeInt32(6, getInstrumentFamily());
      if (hasCarrierBillingStatus())
        paramCodedOutputStreamMicro.writeMessage(7, getCarrierBillingStatus());
      if (hasDisplayTitle())
        paramCodedOutputStreamMicro.writeString(8, getDisplayTitle());
      if (hasTopupInfo())
        paramCodedOutputStreamMicro.writeMessage(9, getTopupInfo());
    }
  }

  public static final class InstrumentSetupInfo extends MessageMicro
  {
    private ChallengeProtos.AddressChallenge addressChallenge_ = null;
    private CommonDevice.Money balance_ = null;
    private int cachedSize = -1;
    private List<String> footerHtml_ = Collections.emptyList();
    private boolean hasAddressChallenge;
    private boolean hasBalance;
    private boolean hasInstrumentFamily;
    private boolean hasSupported;
    private int instrumentFamily_ = 0;
    private boolean supported_ = false;

    public InstrumentSetupInfo addFooterHtml(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.footerHtml_.isEmpty())
        this.footerHtml_ = new ArrayList();
      this.footerHtml_.add(paramString);
      return this;
    }

    public ChallengeProtos.AddressChallenge getAddressChallenge()
    {
      return this.addressChallenge_;
    }

    public CommonDevice.Money getBalance()
    {
      return this.balance_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getFooterHtmlList()
    {
      return this.footerHtml_;
    }

    public int getInstrumentFamily()
    {
      return this.instrumentFamily_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrumentFamily())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getInstrumentFamily());
      if (hasSupported())
        i += CodedOutputStreamMicro.computeBoolSize(2, getSupported());
      if (hasAddressChallenge())
        i += CodedOutputStreamMicro.computeMessageSize(3, getAddressChallenge());
      if (hasBalance())
        i += CodedOutputStreamMicro.computeMessageSize(4, getBalance());
      int j = 0;
      Iterator localIterator = getFooterHtmlList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 1 * getFooterHtmlList().size();
      this.cachedSize = k;
      return k;
    }

    public boolean getSupported()
    {
      return this.supported_;
    }

    public boolean hasAddressChallenge()
    {
      return this.hasAddressChallenge;
    }

    public boolean hasBalance()
    {
      return this.hasBalance;
    }

    public boolean hasInstrumentFamily()
    {
      return this.hasInstrumentFamily;
    }

    public boolean hasSupported()
    {
      return this.hasSupported;
    }

    public InstrumentSetupInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setInstrumentFamily(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setSupported(paramCodedInputStreamMicro.readBool());
          break;
        case 26:
          ChallengeProtos.AddressChallenge localAddressChallenge = new ChallengeProtos.AddressChallenge();
          paramCodedInputStreamMicro.readMessage(localAddressChallenge);
          setAddressChallenge(localAddressChallenge);
          break;
        case 34:
          CommonDevice.Money localMoney = new CommonDevice.Money();
          paramCodedInputStreamMicro.readMessage(localMoney);
          setBalance(localMoney);
          break;
        case 42:
        }
        addFooterHtml(paramCodedInputStreamMicro.readString());
      }
    }

    public InstrumentSetupInfo setAddressChallenge(ChallengeProtos.AddressChallenge paramAddressChallenge)
    {
      if (paramAddressChallenge == null)
        throw new NullPointerException();
      this.hasAddressChallenge = true;
      this.addressChallenge_ = paramAddressChallenge;
      return this;
    }

    public InstrumentSetupInfo setBalance(CommonDevice.Money paramMoney)
    {
      if (paramMoney == null)
        throw new NullPointerException();
      this.hasBalance = true;
      this.balance_ = paramMoney;
      return this;
    }

    public InstrumentSetupInfo setInstrumentFamily(int paramInt)
    {
      this.hasInstrumentFamily = true;
      this.instrumentFamily_ = paramInt;
      return this;
    }

    public InstrumentSetupInfo setSupported(boolean paramBoolean)
    {
      this.hasSupported = true;
      this.supported_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstrumentFamily())
        paramCodedOutputStreamMicro.writeInt32(1, getInstrumentFamily());
      if (hasSupported())
        paramCodedOutputStreamMicro.writeBool(2, getSupported());
      if (hasAddressChallenge())
        paramCodedOutputStreamMicro.writeMessage(3, getAddressChallenge());
      if (hasBalance())
        paramCodedOutputStreamMicro.writeMessage(4, getBalance());
      Iterator localIterator = getFooterHtmlList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(5, (String)localIterator.next());
    }
  }

  public static final class Money extends MessageMicro
  {
    private int cachedSize = -1;
    private String currencyCode_ = "";
    private String formattedAmount_ = "";
    private boolean hasCurrencyCode;
    private boolean hasFormattedAmount;
    private boolean hasMicros;
    private long micros_ = 0L;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCurrencyCode()
    {
      return this.currencyCode_;
    }

    public String getFormattedAmount()
    {
      return this.formattedAmount_;
    }

    public long getMicros()
    {
      return this.micros_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasMicros())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getMicros());
      if (hasCurrencyCode())
        i += CodedOutputStreamMicro.computeStringSize(2, getCurrencyCode());
      if (hasFormattedAmount())
        i += CodedOutputStreamMicro.computeStringSize(3, getFormattedAmount());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCurrencyCode()
    {
      return this.hasCurrencyCode;
    }

    public boolean hasFormattedAmount()
    {
      return this.hasFormattedAmount;
    }

    public boolean hasMicros()
    {
      return this.hasMicros;
    }

    public Money mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setMicros(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
          setCurrencyCode(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setFormattedAmount(paramCodedInputStreamMicro.readString());
      }
    }

    public Money setCurrencyCode(String paramString)
    {
      this.hasCurrencyCode = true;
      this.currencyCode_ = paramString;
      return this;
    }

    public Money setFormattedAmount(String paramString)
    {
      this.hasFormattedAmount = true;
      this.formattedAmount_ = paramString;
      return this;
    }

    public Money setMicros(long paramLong)
    {
      this.hasMicros = true;
      this.micros_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasMicros())
        paramCodedOutputStreamMicro.writeInt64(1, getMicros());
      if (hasCurrencyCode())
        paramCodedOutputStreamMicro.writeString(2, getCurrencyCode());
      if (hasFormattedAmount())
        paramCodedOutputStreamMicro.writeString(3, getFormattedAmount());
    }
  }

  public static final class PasswordPrompt extends MessageMicro
  {
    private int cachedSize = -1;
    private String forgotPasswordUrl_ = "";
    private boolean hasForgotPasswordUrl;
    private boolean hasPrompt;
    private String prompt_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getForgotPasswordUrl()
    {
      return this.forgotPasswordUrl_;
    }

    public String getPrompt()
    {
      return this.prompt_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPrompt())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPrompt());
      if (hasForgotPasswordUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getForgotPasswordUrl());
      this.cachedSize = i;
      return i;
    }

    public boolean hasForgotPasswordUrl()
    {
      return this.hasForgotPasswordUrl;
    }

    public boolean hasPrompt()
    {
      return this.hasPrompt;
    }

    public PasswordPrompt mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPrompt(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setForgotPasswordUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public PasswordPrompt setForgotPasswordUrl(String paramString)
    {
      this.hasForgotPasswordUrl = true;
      this.forgotPasswordUrl_ = paramString;
      return this;
    }

    public PasswordPrompt setPrompt(String paramString)
    {
      this.hasPrompt = true;
      this.prompt_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPrompt())
        paramCodedOutputStreamMicro.writeString(1, getPrompt());
      if (hasForgotPasswordUrl())
        paramCodedOutputStreamMicro.writeString(2, getForgotPasswordUrl());
    }
  }

  public static final class TopupInfo extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasOptionsContainerDocid;
    private boolean hasOptionsListUrl;
    private boolean hasSubtitle;
    private String optionsContainerDocid_ = "";
    private String optionsListUrl_ = "";
    private String subtitle_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getOptionsContainerDocid()
    {
      return this.optionsContainerDocid_;
    }

    public String getOptionsListUrl()
    {
      return this.optionsListUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasOptionsContainerDocid())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getOptionsContainerDocid());
      if (hasOptionsListUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getOptionsListUrl());
      if (hasSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getSubtitle());
      this.cachedSize = i;
      return i;
    }

    public String getSubtitle()
    {
      return this.subtitle_;
    }

    public boolean hasOptionsContainerDocid()
    {
      return this.hasOptionsContainerDocid;
    }

    public boolean hasOptionsListUrl()
    {
      return this.hasOptionsListUrl;
    }

    public boolean hasSubtitle()
    {
      return this.hasSubtitle;
    }

    public TopupInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setOptionsContainerDocid(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setOptionsListUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setSubtitle(paramCodedInputStreamMicro.readString());
      }
    }

    public TopupInfo setOptionsContainerDocid(String paramString)
    {
      this.hasOptionsContainerDocid = true;
      this.optionsContainerDocid_ = paramString;
      return this;
    }

    public TopupInfo setOptionsListUrl(String paramString)
    {
      this.hasOptionsListUrl = true;
      this.optionsListUrl_ = paramString;
      return this;
    }

    public TopupInfo setSubtitle(String paramString)
    {
      this.hasSubtitle = true;
      this.subtitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasOptionsContainerDocid())
        paramCodedOutputStreamMicro.writeString(1, getOptionsContainerDocid());
      if (hasOptionsListUrl())
        paramCodedOutputStreamMicro.writeString(2, getOptionsListUrl());
      if (hasSubtitle())
        paramCodedOutputStreamMicro.writeString(3, getSubtitle());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.CommonDevice
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class CarrierBilling
{
  public static final class InitiateAssociationResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUserToken;
    private String userToken_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUserToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUserToken());
      this.cachedSize = i;
      return i;
    }

    public String getUserToken()
    {
      return this.userToken_;
    }

    public boolean hasUserToken()
    {
      return this.hasUserToken;
    }

    public InitiateAssociationResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        setUserToken(paramCodedInputStreamMicro.readString());
      }
    }

    public InitiateAssociationResponse setUserToken(String paramString)
    {
      this.hasUserToken = true;
      this.userToken_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUserToken())
        paramCodedOutputStreamMicro.writeString(1, getUserToken());
    }
  }

  public static final class VerifyAssociationResponse extends MessageMicro
  {
    private BillingAddress.Address billingAddress_ = null;
    private int cachedSize = -1;
    private String carrierErrorMessage_ = "";
    private CommonDevice.CarrierTos carrierTos_ = null;
    private boolean hasBillingAddress;
    private boolean hasCarrierErrorMessage;
    private boolean hasCarrierTos;
    private boolean hasStatus;
    private int status_ = 1;

    public BillingAddress.Address getBillingAddress()
    {
      return this.billingAddress_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCarrierErrorMessage()
    {
      return this.carrierErrorMessage_;
    }

    public CommonDevice.CarrierTos getCarrierTos()
    {
      return this.carrierTos_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasStatus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatus());
      if (hasBillingAddress())
        i += CodedOutputStreamMicro.computeMessageSize(2, getBillingAddress());
      if (hasCarrierTos())
        i += CodedOutputStreamMicro.computeMessageSize(3, getCarrierTos());
      if (hasCarrierErrorMessage())
        i += CodedOutputStreamMicro.computeStringSize(4, getCarrierErrorMessage());
      this.cachedSize = i;
      return i;
    }

    public int getStatus()
    {
      return this.status_;
    }

    public boolean hasBillingAddress()
    {
      return this.hasBillingAddress;
    }

    public boolean hasCarrierErrorMessage()
    {
      return this.hasCarrierErrorMessage;
    }

    public boolean hasCarrierTos()
    {
      return this.hasCarrierTos;
    }

    public boolean hasStatus()
    {
      return this.hasStatus;
    }

    public VerifyAssociationResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setStatus(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          BillingAddress.Address localAddress = new BillingAddress.Address();
          paramCodedInputStreamMicro.readMessage(localAddress);
          setBillingAddress(localAddress);
          break;
        case 26:
          CommonDevice.CarrierTos localCarrierTos = new CommonDevice.CarrierTos();
          paramCodedInputStreamMicro.readMessage(localCarrierTos);
          setCarrierTos(localCarrierTos);
          break;
        case 34:
        }
        setCarrierErrorMessage(paramCodedInputStreamMicro.readString());
      }
    }

    public VerifyAssociationResponse setBillingAddress(BillingAddress.Address paramAddress)
    {
      if (paramAddress == null)
        throw new NullPointerException();
      this.hasBillingAddress = true;
      this.billingAddress_ = paramAddress;
      return this;
    }

    public VerifyAssociationResponse setCarrierErrorMessage(String paramString)
    {
      this.hasCarrierErrorMessage = true;
      this.carrierErrorMessage_ = paramString;
      return this;
    }

    public VerifyAssociationResponse setCarrierTos(CommonDevice.CarrierTos paramCarrierTos)
    {
      if (paramCarrierTos == null)
        throw new NullPointerException();
      this.hasCarrierTos = true;
      this.carrierTos_ = paramCarrierTos;
      return this;
    }

    public VerifyAssociationResponse setStatus(int paramInt)
    {
      this.hasStatus = true;
      this.status_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasStatus())
        paramCodedOutputStreamMicro.writeInt32(1, getStatus());
      if (hasBillingAddress())
        paramCodedOutputStreamMicro.writeMessage(2, getBillingAddress());
      if (hasCarrierTos())
        paramCodedOutputStreamMicro.writeMessage(3, getCarrierTos());
      if (hasCarrierErrorMessage())
        paramCodedOutputStreamMicro.writeString(4, getCarrierErrorMessage());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.CarrierBilling
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class Delivery
{
  public static final class DeliveryResponse extends MessageMicro
  {
    private AndroidAppDelivery.AndroidAppDeliveryData appDeliveryData_ = null;
    private int cachedSize = -1;
    private boolean hasAppDeliveryData;
    private boolean hasStatus;
    private int status_ = 1;

    public AndroidAppDelivery.AndroidAppDeliveryData getAppDeliveryData()
    {
      return this.appDeliveryData_;
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
      if (hasStatus())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getStatus());
      if (hasAppDeliveryData())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAppDeliveryData());
      this.cachedSize = i;
      return i;
    }

    public int getStatus()
    {
      return this.status_;
    }

    public boolean hasAppDeliveryData()
    {
      return this.hasAppDeliveryData;
    }

    public boolean hasStatus()
    {
      return this.hasStatus;
    }

    public DeliveryResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = new AndroidAppDelivery.AndroidAppDeliveryData();
        paramCodedInputStreamMicro.readMessage(localAndroidAppDeliveryData);
        setAppDeliveryData(localAndroidAppDeliveryData);
      }
    }

    public DeliveryResponse setAppDeliveryData(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData)
    {
      if (paramAndroidAppDeliveryData == null)
        throw new NullPointerException();
      this.hasAppDeliveryData = true;
      this.appDeliveryData_ = paramAndroidAppDeliveryData;
      return this;
    }

    public DeliveryResponse setStatus(int paramInt)
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
      if (hasAppDeliveryData())
        paramCodedOutputStreamMicro.writeMessage(2, getAppDeliveryData());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Delivery
 * JD-Core Version:    0.6.2
 */
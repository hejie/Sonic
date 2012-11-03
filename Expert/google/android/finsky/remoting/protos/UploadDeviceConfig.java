package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class UploadDeviceConfig
{
  public static final class UploadDeviceConfigRequest extends MessageMicro
  {
    private int cachedSize = -1;
    private DeviceConfigurationProto deviceConfiguration_ = null;
    private String gcmRegistrationId_ = "";
    private boolean hasDeviceConfiguration;
    private boolean hasGcmRegistrationId;
    private boolean hasManufacturer;
    private String manufacturer_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DeviceConfigurationProto getDeviceConfiguration()
    {
      return this.deviceConfiguration_;
    }

    public String getGcmRegistrationId()
    {
      return this.gcmRegistrationId_;
    }

    public String getManufacturer()
    {
      return this.manufacturer_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDeviceConfiguration())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getDeviceConfiguration());
      if (hasManufacturer())
        i += CodedOutputStreamMicro.computeStringSize(2, getManufacturer());
      if (hasGcmRegistrationId())
        i += CodedOutputStreamMicro.computeStringSize(3, getGcmRegistrationId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDeviceConfiguration()
    {
      return this.hasDeviceConfiguration;
    }

    public boolean hasGcmRegistrationId()
    {
      return this.hasGcmRegistrationId;
    }

    public boolean hasManufacturer()
    {
      return this.hasManufacturer;
    }

    public UploadDeviceConfigRequest mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DeviceConfigurationProto localDeviceConfigurationProto = new DeviceConfigurationProto();
          paramCodedInputStreamMicro.readMessage(localDeviceConfigurationProto);
          setDeviceConfiguration(localDeviceConfigurationProto);
          break;
        case 18:
          setManufacturer(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setGcmRegistrationId(paramCodedInputStreamMicro.readString());
      }
    }

    public UploadDeviceConfigRequest setDeviceConfiguration(DeviceConfigurationProto paramDeviceConfigurationProto)
    {
      if (paramDeviceConfigurationProto == null)
        throw new NullPointerException();
      this.hasDeviceConfiguration = true;
      this.deviceConfiguration_ = paramDeviceConfigurationProto;
      return this;
    }

    public UploadDeviceConfigRequest setGcmRegistrationId(String paramString)
    {
      this.hasGcmRegistrationId = true;
      this.gcmRegistrationId_ = paramString;
      return this;
    }

    public UploadDeviceConfigRequest setManufacturer(String paramString)
    {
      this.hasManufacturer = true;
      this.manufacturer_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDeviceConfiguration())
        paramCodedOutputStreamMicro.writeMessage(1, getDeviceConfiguration());
      if (hasManufacturer())
        paramCodedOutputStreamMicro.writeString(2, getManufacturer());
      if (hasGcmRegistrationId())
        paramCodedOutputStreamMicro.writeString(3, getGcmRegistrationId());
    }
  }

  public static final class UploadDeviceConfigResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUploadDeviceConfigToken;
    private String uploadDeviceConfigToken_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUploadDeviceConfigToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUploadDeviceConfigToken());
      this.cachedSize = i;
      return i;
    }

    public String getUploadDeviceConfigToken()
    {
      return this.uploadDeviceConfigToken_;
    }

    public boolean hasUploadDeviceConfigToken()
    {
      return this.hasUploadDeviceConfigToken;
    }

    public UploadDeviceConfigResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setUploadDeviceConfigToken(paramCodedInputStreamMicro.readString());
      }
    }

    public UploadDeviceConfigResponse setUploadDeviceConfigToken(String paramString)
    {
      this.hasUploadDeviceConfigToken = true;
      this.uploadDeviceConfigToken_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUploadDeviceConfigToken())
        paramCodedOutputStreamMicro.writeString(1, getUploadDeviceConfigToken());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.UploadDeviceConfig
 * JD-Core Version:    0.6.2
 */
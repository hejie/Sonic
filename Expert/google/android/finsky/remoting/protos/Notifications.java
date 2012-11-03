package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class Notifications
{
  public static final class AndroidAppNotificationData extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;
    private boolean hasVersionCode;
    private int versionCode_ = 0;

    public String getAssetId()
    {
      return this.assetId_;
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
      if (hasVersionCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getVersionCode());
      if (hasAssetId())
        i += CodedOutputStreamMicro.computeStringSize(2, getAssetId());
      this.cachedSize = i;
      return i;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public AndroidAppNotificationData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setAssetId(paramCodedInputStreamMicro.readString());
      }
    }

    public AndroidAppNotificationData setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public AndroidAppNotificationData setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(1, getVersionCode());
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(2, getAssetId());
    }
  }

  public static final class InAppNotificationData extends MessageMicro
  {
    private int cachedSize = -1;
    private String checkoutOrderId_ = "";
    private boolean hasCheckoutOrderId;
    private boolean hasInAppNotificationId;
    private String inAppNotificationId_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCheckoutOrderId()
    {
      return this.checkoutOrderId_;
    }

    public String getInAppNotificationId()
    {
      return this.inAppNotificationId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCheckoutOrderId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getCheckoutOrderId());
      if (hasInAppNotificationId())
        i += CodedOutputStreamMicro.computeStringSize(2, getInAppNotificationId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCheckoutOrderId()
    {
      return this.hasCheckoutOrderId;
    }

    public boolean hasInAppNotificationId()
    {
      return this.hasInAppNotificationId;
    }

    public InAppNotificationData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCheckoutOrderId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setInAppNotificationId(paramCodedInputStreamMicro.readString());
      }
    }

    public InAppNotificationData setCheckoutOrderId(String paramString)
    {
      this.hasCheckoutOrderId = true;
      this.checkoutOrderId_ = paramString;
      return this;
    }

    public InAppNotificationData setInAppNotificationId(String paramString)
    {
      this.hasInAppNotificationId = true;
      this.inAppNotificationId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCheckoutOrderId())
        paramCodedOutputStreamMicro.writeString(1, getCheckoutOrderId());
      if (hasInAppNotificationId())
        paramCodedOutputStreamMicro.writeString(2, getInAppNotificationId());
    }
  }

  public static final class LibraryDirtyData extends MessageMicro
  {
    private int backend_ = 0;
    private int cachedSize = -1;
    private boolean hasBackend;
    private boolean hasLibraryId;
    private String libraryId_ = "";

    public int getBackend()
    {
      return this.backend_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getLibraryId()
    {
      return this.libraryId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBackend())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getBackend());
      if (hasLibraryId())
        i += CodedOutputStreamMicro.computeStringSize(2, getLibraryId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBackend()
    {
      return this.hasBackend;
    }

    public boolean hasLibraryId()
    {
      return this.hasLibraryId;
    }

    public LibraryDirtyData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBackend(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setLibraryId(paramCodedInputStreamMicro.readString());
      }
    }

    public LibraryDirtyData setBackend(int paramInt)
    {
      this.hasBackend = true;
      this.backend_ = paramInt;
      return this;
    }

    public LibraryDirtyData setLibraryId(String paramString)
    {
      this.hasLibraryId = true;
      this.libraryId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBackend())
        paramCodedOutputStreamMicro.writeInt32(1, getBackend());
      if (hasLibraryId())
        paramCodedOutputStreamMicro.writeString(2, getLibraryId());
    }
  }

  public static final class Notification extends MessageMicro
  {
    private Notifications.AndroidAppNotificationData appData_ = null;
    private AndroidAppDelivery.AndroidAppDeliveryData appDeliveryData_ = null;
    private int cachedSize = -1;
    private String docTitle_ = "";
    private Common.Docid docid_ = null;
    private boolean hasAppData;
    private boolean hasAppDeliveryData;
    private boolean hasDocTitle;
    private boolean hasDocid;
    private boolean hasInAppNotificationData;
    private boolean hasLibraryDirtyData;
    private boolean hasLibraryUpdate;
    private boolean hasNotificationId;
    private boolean hasNotificationType;
    private boolean hasPurchaseDeclinedData;
    private boolean hasPurchaseRemovalData;
    private boolean hasTimestamp;
    private boolean hasUserEmail;
    private boolean hasUserNotificationData;
    private Notifications.InAppNotificationData inAppNotificationData_ = null;
    private Notifications.LibraryDirtyData libraryDirtyData_ = null;
    private Library.LibraryUpdate libraryUpdate_ = null;
    private String notificationId_ = "";
    private int notificationType_ = 1;
    private Notifications.PurchaseDeclinedData purchaseDeclinedData_ = null;
    private Notifications.PurchaseRemovalData purchaseRemovalData_ = null;
    private long timestamp_ = 0L;
    private String userEmail_ = "";
    private Notifications.UserNotificationData userNotificationData_ = null;

    public static Notification parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferMicroException
    {
      return (Notification)new Notification().mergeFrom(paramArrayOfByte);
    }

    public Notifications.AndroidAppNotificationData getAppData()
    {
      return this.appData_;
    }

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

    public String getDocTitle()
    {
      return this.docTitle_;
    }

    public Common.Docid getDocid()
    {
      return this.docid_;
    }

    public Notifications.InAppNotificationData getInAppNotificationData()
    {
      return this.inAppNotificationData_;
    }

    public Notifications.LibraryDirtyData getLibraryDirtyData()
    {
      return this.libraryDirtyData_;
    }

    public Library.LibraryUpdate getLibraryUpdate()
    {
      return this.libraryUpdate_;
    }

    public String getNotificationId()
    {
      return this.notificationId_;
    }

    public int getNotificationType()
    {
      return this.notificationType_;
    }

    public Notifications.PurchaseDeclinedData getPurchaseDeclinedData()
    {
      return this.purchaseDeclinedData_;
    }

    public Notifications.PurchaseRemovalData getPurchaseRemovalData()
    {
      return this.purchaseRemovalData_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasNotificationType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getNotificationType());
      if (hasTimestamp())
        i += CodedOutputStreamMicro.computeInt64Size(3, getTimestamp());
      if (hasDocid())
        i += CodedOutputStreamMicro.computeMessageSize(4, getDocid());
      if (hasDocTitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getDocTitle());
      if (hasUserEmail())
        i += CodedOutputStreamMicro.computeStringSize(6, getUserEmail());
      if (hasAppData())
        i += CodedOutputStreamMicro.computeMessageSize(7, getAppData());
      if (hasAppDeliveryData())
        i += CodedOutputStreamMicro.computeMessageSize(8, getAppDeliveryData());
      if (hasPurchaseRemovalData())
        i += CodedOutputStreamMicro.computeMessageSize(9, getPurchaseRemovalData());
      if (hasUserNotificationData())
        i += CodedOutputStreamMicro.computeMessageSize(10, getUserNotificationData());
      if (hasInAppNotificationData())
        i += CodedOutputStreamMicro.computeMessageSize(11, getInAppNotificationData());
      if (hasPurchaseDeclinedData())
        i += CodedOutputStreamMicro.computeMessageSize(12, getPurchaseDeclinedData());
      if (hasNotificationId())
        i += CodedOutputStreamMicro.computeStringSize(13, getNotificationId());
      if (hasLibraryUpdate())
        i += CodedOutputStreamMicro.computeMessageSize(14, getLibraryUpdate());
      if (hasLibraryDirtyData())
        i += CodedOutputStreamMicro.computeMessageSize(15, getLibraryDirtyData());
      this.cachedSize = i;
      return i;
    }

    public long getTimestamp()
    {
      return this.timestamp_;
    }

    public String getUserEmail()
    {
      return this.userEmail_;
    }

    public Notifications.UserNotificationData getUserNotificationData()
    {
      return this.userNotificationData_;
    }

    public boolean hasAppData()
    {
      return this.hasAppData;
    }

    public boolean hasAppDeliveryData()
    {
      return this.hasAppDeliveryData;
    }

    public boolean hasDocTitle()
    {
      return this.hasDocTitle;
    }

    public boolean hasDocid()
    {
      return this.hasDocid;
    }

    public boolean hasInAppNotificationData()
    {
      return this.hasInAppNotificationData;
    }

    public boolean hasLibraryDirtyData()
    {
      return this.hasLibraryDirtyData;
    }

    public boolean hasLibraryUpdate()
    {
      return this.hasLibraryUpdate;
    }

    public boolean hasNotificationId()
    {
      return this.hasNotificationId;
    }

    public boolean hasNotificationType()
    {
      return this.hasNotificationType;
    }

    public boolean hasPurchaseDeclinedData()
    {
      return this.hasPurchaseDeclinedData;
    }

    public boolean hasPurchaseRemovalData()
    {
      return this.hasPurchaseRemovalData;
    }

    public boolean hasTimestamp()
    {
      return this.hasTimestamp;
    }

    public boolean hasUserEmail()
    {
      return this.hasUserEmail;
    }

    public boolean hasUserNotificationData()
    {
      return this.hasUserNotificationData;
    }

    public Notification mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setNotificationType(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setTimestamp(paramCodedInputStreamMicro.readInt64());
          break;
        case 34:
          Common.Docid localDocid = new Common.Docid();
          paramCodedInputStreamMicro.readMessage(localDocid);
          setDocid(localDocid);
          break;
        case 42:
          setDocTitle(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setUserEmail(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          Notifications.AndroidAppNotificationData localAndroidAppNotificationData = new Notifications.AndroidAppNotificationData();
          paramCodedInputStreamMicro.readMessage(localAndroidAppNotificationData);
          setAppData(localAndroidAppNotificationData);
          break;
        case 66:
          AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = new AndroidAppDelivery.AndroidAppDeliveryData();
          paramCodedInputStreamMicro.readMessage(localAndroidAppDeliveryData);
          setAppDeliveryData(localAndroidAppDeliveryData);
          break;
        case 74:
          Notifications.PurchaseRemovalData localPurchaseRemovalData = new Notifications.PurchaseRemovalData();
          paramCodedInputStreamMicro.readMessage(localPurchaseRemovalData);
          setPurchaseRemovalData(localPurchaseRemovalData);
          break;
        case 82:
          Notifications.UserNotificationData localUserNotificationData = new Notifications.UserNotificationData();
          paramCodedInputStreamMicro.readMessage(localUserNotificationData);
          setUserNotificationData(localUserNotificationData);
          break;
        case 90:
          Notifications.InAppNotificationData localInAppNotificationData = new Notifications.InAppNotificationData();
          paramCodedInputStreamMicro.readMessage(localInAppNotificationData);
          setInAppNotificationData(localInAppNotificationData);
          break;
        case 98:
          Notifications.PurchaseDeclinedData localPurchaseDeclinedData = new Notifications.PurchaseDeclinedData();
          paramCodedInputStreamMicro.readMessage(localPurchaseDeclinedData);
          setPurchaseDeclinedData(localPurchaseDeclinedData);
          break;
        case 106:
          setNotificationId(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          Library.LibraryUpdate localLibraryUpdate = new Library.LibraryUpdate();
          paramCodedInputStreamMicro.readMessage(localLibraryUpdate);
          setLibraryUpdate(localLibraryUpdate);
          break;
        case 122:
        }
        Notifications.LibraryDirtyData localLibraryDirtyData = new Notifications.LibraryDirtyData();
        paramCodedInputStreamMicro.readMessage(localLibraryDirtyData);
        setLibraryDirtyData(localLibraryDirtyData);
      }
    }

    public Notification setAppData(Notifications.AndroidAppNotificationData paramAndroidAppNotificationData)
    {
      if (paramAndroidAppNotificationData == null)
        throw new NullPointerException();
      this.hasAppData = true;
      this.appData_ = paramAndroidAppNotificationData;
      return this;
    }

    public Notification setAppDeliveryData(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData)
    {
      if (paramAndroidAppDeliveryData == null)
        throw new NullPointerException();
      this.hasAppDeliveryData = true;
      this.appDeliveryData_ = paramAndroidAppDeliveryData;
      return this;
    }

    public Notification setDocTitle(String paramString)
    {
      this.hasDocTitle = true;
      this.docTitle_ = paramString;
      return this;
    }

    public Notification setDocid(Common.Docid paramDocid)
    {
      if (paramDocid == null)
        throw new NullPointerException();
      this.hasDocid = true;
      this.docid_ = paramDocid;
      return this;
    }

    public Notification setInAppNotificationData(Notifications.InAppNotificationData paramInAppNotificationData)
    {
      if (paramInAppNotificationData == null)
        throw new NullPointerException();
      this.hasInAppNotificationData = true;
      this.inAppNotificationData_ = paramInAppNotificationData;
      return this;
    }

    public Notification setLibraryDirtyData(Notifications.LibraryDirtyData paramLibraryDirtyData)
    {
      if (paramLibraryDirtyData == null)
        throw new NullPointerException();
      this.hasLibraryDirtyData = true;
      this.libraryDirtyData_ = paramLibraryDirtyData;
      return this;
    }

    public Notification setLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate)
    {
      if (paramLibraryUpdate == null)
        throw new NullPointerException();
      this.hasLibraryUpdate = true;
      this.libraryUpdate_ = paramLibraryUpdate;
      return this;
    }

    public Notification setNotificationId(String paramString)
    {
      this.hasNotificationId = true;
      this.notificationId_ = paramString;
      return this;
    }

    public Notification setNotificationType(int paramInt)
    {
      this.hasNotificationType = true;
      this.notificationType_ = paramInt;
      return this;
    }

    public Notification setPurchaseDeclinedData(Notifications.PurchaseDeclinedData paramPurchaseDeclinedData)
    {
      if (paramPurchaseDeclinedData == null)
        throw new NullPointerException();
      this.hasPurchaseDeclinedData = true;
      this.purchaseDeclinedData_ = paramPurchaseDeclinedData;
      return this;
    }

    public Notification setPurchaseRemovalData(Notifications.PurchaseRemovalData paramPurchaseRemovalData)
    {
      if (paramPurchaseRemovalData == null)
        throw new NullPointerException();
      this.hasPurchaseRemovalData = true;
      this.purchaseRemovalData_ = paramPurchaseRemovalData;
      return this;
    }

    public Notification setTimestamp(long paramLong)
    {
      this.hasTimestamp = true;
      this.timestamp_ = paramLong;
      return this;
    }

    public Notification setUserEmail(String paramString)
    {
      this.hasUserEmail = true;
      this.userEmail_ = paramString;
      return this;
    }

    public Notification setUserNotificationData(Notifications.UserNotificationData paramUserNotificationData)
    {
      if (paramUserNotificationData == null)
        throw new NullPointerException();
      this.hasUserNotificationData = true;
      this.userNotificationData_ = paramUserNotificationData;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasNotificationType())
        paramCodedOutputStreamMicro.writeInt32(1, getNotificationType());
      if (hasTimestamp())
        paramCodedOutputStreamMicro.writeInt64(3, getTimestamp());
      if (hasDocid())
        paramCodedOutputStreamMicro.writeMessage(4, getDocid());
      if (hasDocTitle())
        paramCodedOutputStreamMicro.writeString(5, getDocTitle());
      if (hasUserEmail())
        paramCodedOutputStreamMicro.writeString(6, getUserEmail());
      if (hasAppData())
        paramCodedOutputStreamMicro.writeMessage(7, getAppData());
      if (hasAppDeliveryData())
        paramCodedOutputStreamMicro.writeMessage(8, getAppDeliveryData());
      if (hasPurchaseRemovalData())
        paramCodedOutputStreamMicro.writeMessage(9, getPurchaseRemovalData());
      if (hasUserNotificationData())
        paramCodedOutputStreamMicro.writeMessage(10, getUserNotificationData());
      if (hasInAppNotificationData())
        paramCodedOutputStreamMicro.writeMessage(11, getInAppNotificationData());
      if (hasPurchaseDeclinedData())
        paramCodedOutputStreamMicro.writeMessage(12, getPurchaseDeclinedData());
      if (hasNotificationId())
        paramCodedOutputStreamMicro.writeString(13, getNotificationId());
      if (hasLibraryUpdate())
        paramCodedOutputStreamMicro.writeMessage(14, getLibraryUpdate());
      if (hasLibraryDirtyData())
        paramCodedOutputStreamMicro.writeMessage(15, getLibraryDirtyData());
    }
  }

  public static final class PurchaseDeclinedData extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasReason;
    private boolean hasShowNotification;
    private int reason_ = 0;
    private boolean showNotification_ = true;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getReason()
    {
      return this.reason_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasReason())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getReason());
      if (hasShowNotification())
        i += CodedOutputStreamMicro.computeBoolSize(2, getShowNotification());
      this.cachedSize = i;
      return i;
    }

    public boolean getShowNotification()
    {
      return this.showNotification_;
    }

    public boolean hasReason()
    {
      return this.hasReason;
    }

    public boolean hasShowNotification()
    {
      return this.hasShowNotification;
    }

    public PurchaseDeclinedData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setReason(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
        }
        setShowNotification(paramCodedInputStreamMicro.readBool());
      }
    }

    public PurchaseDeclinedData setReason(int paramInt)
    {
      this.hasReason = true;
      this.reason_ = paramInt;
      return this;
    }

    public PurchaseDeclinedData setShowNotification(boolean paramBoolean)
    {
      this.hasShowNotification = true;
      this.showNotification_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasReason())
        paramCodedOutputStreamMicro.writeInt32(1, getReason());
      if (hasShowNotification())
        paramCodedOutputStreamMicro.writeBool(2, getShowNotification());
    }
  }

  public static final class PurchaseRemovalData extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasMalicious;
    private boolean malicious_ = false;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getMalicious()
    {
      return this.malicious_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasMalicious())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getMalicious());
      this.cachedSize = i;
      return i;
    }

    public boolean hasMalicious()
    {
      return this.hasMalicious;
    }

    public PurchaseRemovalData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        setMalicious(paramCodedInputStreamMicro.readBool());
      }
    }

    public PurchaseRemovalData setMalicious(boolean paramBoolean)
    {
      this.hasMalicious = true;
      this.malicious_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasMalicious())
        paramCodedOutputStreamMicro.writeBool(1, getMalicious());
    }
  }

  public static final class UserNotificationData extends MessageMicro
  {
    private int cachedSize = -1;
    private String dialogText_ = "";
    private String dialogTitle_ = "";
    private boolean hasDialogText;
    private boolean hasDialogTitle;
    private boolean hasNotificationText;
    private boolean hasNotificationTitle;
    private boolean hasTickerText;
    private String notificationText_ = "";
    private String notificationTitle_ = "";
    private String tickerText_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDialogText()
    {
      return this.dialogText_;
    }

    public String getDialogTitle()
    {
      return this.dialogTitle_;
    }

    public String getNotificationText()
    {
      return this.notificationText_;
    }

    public String getNotificationTitle()
    {
      return this.notificationTitle_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasNotificationTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getNotificationTitle());
      if (hasNotificationText())
        i += CodedOutputStreamMicro.computeStringSize(2, getNotificationText());
      if (hasTickerText())
        i += CodedOutputStreamMicro.computeStringSize(3, getTickerText());
      if (hasDialogTitle())
        i += CodedOutputStreamMicro.computeStringSize(4, getDialogTitle());
      if (hasDialogText())
        i += CodedOutputStreamMicro.computeStringSize(5, getDialogText());
      this.cachedSize = i;
      return i;
    }

    public String getTickerText()
    {
      return this.tickerText_;
    }

    public boolean hasDialogText()
    {
      return this.hasDialogText;
    }

    public boolean hasDialogTitle()
    {
      return this.hasDialogTitle;
    }

    public boolean hasNotificationText()
    {
      return this.hasNotificationText;
    }

    public boolean hasNotificationTitle()
    {
      return this.hasNotificationTitle;
    }

    public boolean hasTickerText()
    {
      return this.hasTickerText;
    }

    public UserNotificationData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setNotificationTitle(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setNotificationText(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setTickerText(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setDialogTitle(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        setDialogText(paramCodedInputStreamMicro.readString());
      }
    }

    public UserNotificationData setDialogText(String paramString)
    {
      this.hasDialogText = true;
      this.dialogText_ = paramString;
      return this;
    }

    public UserNotificationData setDialogTitle(String paramString)
    {
      this.hasDialogTitle = true;
      this.dialogTitle_ = paramString;
      return this;
    }

    public UserNotificationData setNotificationText(String paramString)
    {
      this.hasNotificationText = true;
      this.notificationText_ = paramString;
      return this;
    }

    public UserNotificationData setNotificationTitle(String paramString)
    {
      this.hasNotificationTitle = true;
      this.notificationTitle_ = paramString;
      return this;
    }

    public UserNotificationData setTickerText(String paramString)
    {
      this.hasTickerText = true;
      this.tickerText_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasNotificationTitle())
        paramCodedOutputStreamMicro.writeString(1, getNotificationTitle());
      if (hasNotificationText())
        paramCodedOutputStreamMicro.writeString(2, getNotificationText());
      if (hasTickerText())
        paramCodedOutputStreamMicro.writeString(3, getTickerText());
      if (hasDialogTitle())
        paramCodedOutputStreamMicro.writeString(4, getDialogTitle());
      if (hasDialogText())
        paramCodedOutputStreamMicro.writeString(5, getDialogText());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Notifications
 * JD-Core Version:    0.6.2
 */
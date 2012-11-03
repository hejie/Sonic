package com.google.android.vending.remoting.protos;

import com.google.android.finsky.remoting.protos.CommonDevice.BillingAddressSpec;
import com.google.protobuf.micro.ByteStringMicro;
import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class VendingProtos
{
  public static final class AckNotificationsRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSignatureHash;
    private List<String> nackNotificationId_ = Collections.emptyList();
    private List<String> notificationId_ = Collections.emptyList();
    private VendingProtos.SignatureHashProto signatureHash_ = null;

    public AckNotificationsRequestProto addNackNotificationId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.nackNotificationId_.isEmpty())
        this.nackNotificationId_ = new ArrayList();
      this.nackNotificationId_.add(paramString);
      return this;
    }

    public AckNotificationsRequestProto addNotificationId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.notificationId_.isEmpty())
        this.notificationId_ = new ArrayList();
      this.notificationId_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getNackNotificationIdList()
    {
      return this.nackNotificationId_;
    }

    public List<String> getNotificationIdList()
    {
      return this.notificationId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getNotificationIdList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int j = 0 + i + 1 * getNotificationIdList().size();
      if (hasSignatureHash())
        j += CodedOutputStreamMicro.computeMessageSize(2, getSignatureHash());
      int k = 0;
      Iterator localIterator2 = getNackNotificationIdList().iterator();
      while (localIterator2.hasNext())
        k += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int m = j + k + 1 * getNackNotificationIdList().size();
      this.cachedSize = m;
      return m;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public AckNotificationsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          addNotificationId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
          paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
          setSignatureHash(localSignatureHashProto);
          break;
        case 26:
        }
        addNackNotificationId(paramCodedInputStreamMicro.readString());
      }
    }

    public AckNotificationsRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getNotificationIdList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(1, (String)localIterator1.next());
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(2, getSignatureHash());
      Iterator localIterator2 = getNackNotificationIdList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator2.next());
    }
  }

  public static final class AckNotificationsResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public AckNotificationsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class AddressProto extends MessageMicro
  {
    private String address1_ = "";
    private String address2_ = "";
    private int cachedSize = -1;
    private String city_ = "";
    private String country_ = "";
    private boolean hasAddress1;
    private boolean hasAddress2;
    private boolean hasCity;
    private boolean hasCountry;
    private boolean hasName;
    private boolean hasPhone;
    private boolean hasPostalCode;
    private boolean hasState;
    private boolean hasType;
    private String name_ = "";
    private String phone_ = "";
    private String postalCode_ = "";
    private String state_ = "";
    private String type_ = "";

    public String getAddress1()
    {
      return this.address1_;
    }

    public String getAddress2()
    {
      return this.address2_;
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

    public String getCountry()
    {
      return this.country_;
    }

    public String getName()
    {
      return this.name_;
    }

    public String getPhone()
    {
      return this.phone_;
    }

    public String getPostalCode()
    {
      return this.postalCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAddress1())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAddress1());
      if (hasAddress2())
        i += CodedOutputStreamMicro.computeStringSize(2, getAddress2());
      if (hasCity())
        i += CodedOutputStreamMicro.computeStringSize(3, getCity());
      if (hasState())
        i += CodedOutputStreamMicro.computeStringSize(4, getState());
      if (hasPostalCode())
        i += CodedOutputStreamMicro.computeStringSize(5, getPostalCode());
      if (hasCountry())
        i += CodedOutputStreamMicro.computeStringSize(6, getCountry());
      if (hasName())
        i += CodedOutputStreamMicro.computeStringSize(7, getName());
      if (hasType())
        i += CodedOutputStreamMicro.computeStringSize(8, getType());
      if (hasPhone())
        i += CodedOutputStreamMicro.computeStringSize(9, getPhone());
      this.cachedSize = i;
      return i;
    }

    public String getState()
    {
      return this.state_;
    }

    public String getType()
    {
      return this.type_;
    }

    public boolean hasAddress1()
    {
      return this.hasAddress1;
    }

    public boolean hasAddress2()
    {
      return this.hasAddress2;
    }

    public boolean hasCity()
    {
      return this.hasCity;
    }

    public boolean hasCountry()
    {
      return this.hasCountry;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasPhone()
    {
      return this.hasPhone;
    }

    public boolean hasPostalCode()
    {
      return this.hasPostalCode;
    }

    public boolean hasState()
    {
      return this.hasState;
    }

    public boolean hasType()
    {
      return this.hasType;
    }

    public AddressProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAddress1(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setAddress2(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setCity(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setState(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setPostalCode(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setCountry(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setType(paramCodedInputStreamMicro.readString());
          break;
        case 74:
        }
        setPhone(paramCodedInputStreamMicro.readString());
      }
    }

    public AddressProto setAddress1(String paramString)
    {
      this.hasAddress1 = true;
      this.address1_ = paramString;
      return this;
    }

    public AddressProto setAddress2(String paramString)
    {
      this.hasAddress2 = true;
      this.address2_ = paramString;
      return this;
    }

    public AddressProto setCity(String paramString)
    {
      this.hasCity = true;
      this.city_ = paramString;
      return this;
    }

    public AddressProto setCountry(String paramString)
    {
      this.hasCountry = true;
      this.country_ = paramString;
      return this;
    }

    public AddressProto setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public AddressProto setPhone(String paramString)
    {
      this.hasPhone = true;
      this.phone_ = paramString;
      return this;
    }

    public AddressProto setPostalCode(String paramString)
    {
      this.hasPostalCode = true;
      this.postalCode_ = paramString;
      return this;
    }

    public AddressProto setState(String paramString)
    {
      this.hasState = true;
      this.state_ = paramString;
      return this;
    }

    public AddressProto setType(String paramString)
    {
      this.hasType = true;
      this.type_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddress1())
        paramCodedOutputStreamMicro.writeString(1, getAddress1());
      if (hasAddress2())
        paramCodedOutputStreamMicro.writeString(2, getAddress2());
      if (hasCity())
        paramCodedOutputStreamMicro.writeString(3, getCity());
      if (hasState())
        paramCodedOutputStreamMicro.writeString(4, getState());
      if (hasPostalCode())
        paramCodedOutputStreamMicro.writeString(5, getPostalCode());
      if (hasCountry())
        paramCodedOutputStreamMicro.writeString(6, getCountry());
      if (hasName())
        paramCodedOutputStreamMicro.writeString(7, getName());
      if (hasType())
        paramCodedOutputStreamMicro.writeString(8, getType());
      if (hasPhone())
        paramCodedOutputStreamMicro.writeString(9, getPhone());
    }
  }

  public static final class AppDataProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasKey;
    private boolean hasValue;
    private String key_ = "";
    private String value_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getKey()
    {
      return this.key_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getKey());
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

    public AppDataProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setKey(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setValue(paramCodedInputStreamMicro.readString());
      }
    }

    public AppDataProto setKey(String paramString)
    {
      this.hasKey = true;
      this.key_ = paramString;
      return this;
    }

    public AppDataProto setValue(String paramString)
    {
      this.hasValue = true;
      this.value_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasKey())
        paramCodedOutputStreamMicro.writeString(1, getKey());
      if (hasValue())
        paramCodedOutputStreamMicro.writeString(2, getValue());
    }
  }

  public static final class AppSuggestionProto extends MessageMicro
  {
    private VendingProtos.ExternalAssetProto assetInfo_ = null;
    private int cachedSize = -1;
    private boolean hasAssetInfo;

    public VendingProtos.ExternalAssetProto getAssetInfo()
    {
      return this.assetInfo_;
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
      if (hasAssetInfo())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAssetInfo());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetInfo()
    {
      return this.hasAssetInfo;
    }

    public AppSuggestionProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.ExternalAssetProto localExternalAssetProto = new VendingProtos.ExternalAssetProto();
        paramCodedInputStreamMicro.readMessage(localExternalAssetProto);
        setAssetInfo(localExternalAssetProto);
      }
    }

    public AppSuggestionProto setAssetInfo(VendingProtos.ExternalAssetProto paramExternalAssetProto)
    {
      if (paramExternalAssetProto == null)
        throw new NullPointerException();
      this.hasAssetInfo = true;
      this.assetInfo_ = paramExternalAssetProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetInfo())
        paramCodedOutputStreamMicro.writeMessage(1, getAssetInfo());
    }
  }

  public static final class AssetIdentifierProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;
    private boolean hasPackageName;
    private boolean hasVersionCode;
    private String packageName_ = "";
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

    public String getPackageName()
    {
      return this.packageName_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPackageName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPackageName());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
      if (hasAssetId())
        i += CodedOutputStreamMicro.computeStringSize(3, getAssetId());
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

    public boolean hasPackageName()
    {
      return this.hasPackageName;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public AssetIdentifierProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPackageName(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
        }
        setAssetId(paramCodedInputStreamMicro.readString());
      }
    }

    public AssetIdentifierProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public AssetIdentifierProto setPackageName(String paramString)
    {
      this.hasPackageName = true;
      this.packageName_ = paramString;
      return this;
    }

    public AssetIdentifierProto setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPackageName())
        paramCodedOutputStreamMicro.writeString(1, getPackageName());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(3, getAssetId());
    }
  }

  public static final class AssetsRequestProto extends MessageMicro
  {
    private List<String> assetId_ = Collections.emptyList();
    private int assetType_ = 0;
    private List<String> badgeId_ = Collections.emptyList();
    private int cachedSize = -1;
    private String categoryId_ = "";
    private boolean hasAssetType;
    private boolean hasCategoryId;
    private boolean hasNumEntries;
    private boolean hasQuery;
    private boolean hasRankingType;
    private boolean hasReconstructVendingHistory;
    private boolean hasRetrieveCarrierChannel;
    private boolean hasRetrieveExtendedInfo;
    private boolean hasRetrieveVendingHistory;
    private boolean hasSortOrder;
    private boolean hasStartIndex;
    private boolean hasUnfilteredResults;
    private boolean hasViewFilter;
    private long numEntries_ = 10L;
    private List<String> pendingDownloadAssetId_ = Collections.emptyList();
    private String query_ = "";
    private String rankingType_ = "";
    private boolean reconstructVendingHistory_ = false;
    private boolean retrieveCarrierChannel_ = false;
    private boolean retrieveExtendedInfo_ = false;
    private boolean retrieveVendingHistory_ = false;
    private int sortOrder_ = 0;
    private long startIndex_ = 0L;
    private boolean unfilteredResults_ = false;
    private int viewFilter_ = 0;

    public AssetsRequestProto addAssetId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.assetId_.isEmpty())
        this.assetId_ = new ArrayList();
      this.assetId_.add(paramString);
      return this;
    }

    public AssetsRequestProto addBadgeId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.badgeId_.isEmpty())
        this.badgeId_ = new ArrayList();
      this.badgeId_.add(paramString);
      return this;
    }

    public AssetsRequestProto addPendingDownloadAssetId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.pendingDownloadAssetId_.isEmpty())
        this.pendingDownloadAssetId_ = new ArrayList();
      this.pendingDownloadAssetId_.add(paramString);
      return this;
    }

    public List<String> getAssetIdList()
    {
      return this.assetId_;
    }

    public int getAssetType()
    {
      return this.assetType_;
    }

    public List<String> getBadgeIdList()
    {
      return this.badgeId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCategoryId()
    {
      return this.categoryId_;
    }

    public long getNumEntries()
    {
      return this.numEntries_;
    }

    public List<String> getPendingDownloadAssetIdList()
    {
      return this.pendingDownloadAssetId_;
    }

    public String getQuery()
    {
      return this.query_;
    }

    public String getRankingType()
    {
      return this.rankingType_;
    }

    public boolean getReconstructVendingHistory()
    {
      return this.reconstructVendingHistory_;
    }

    public boolean getRetrieveCarrierChannel()
    {
      return this.retrieveCarrierChannel_;
    }

    public boolean getRetrieveExtendedInfo()
    {
      return this.retrieveExtendedInfo_;
    }

    public boolean getRetrieveVendingHistory()
    {
      return this.retrieveVendingHistory_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getAssetType());
      if (hasQuery())
        i += CodedOutputStreamMicro.computeStringSize(2, getQuery());
      if (hasCategoryId())
        i += CodedOutputStreamMicro.computeStringSize(3, getCategoryId());
      int j = 0;
      Iterator localIterator1 = getAssetIdList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getAssetIdList().size();
      if (hasRetrieveVendingHistory())
        k += CodedOutputStreamMicro.computeBoolSize(5, getRetrieveVendingHistory());
      if (hasRetrieveExtendedInfo())
        k += CodedOutputStreamMicro.computeBoolSize(6, getRetrieveExtendedInfo());
      if (hasSortOrder())
        k += CodedOutputStreamMicro.computeInt32Size(7, getSortOrder());
      if (hasStartIndex())
        k += CodedOutputStreamMicro.computeInt64Size(8, getStartIndex());
      if (hasNumEntries())
        k += CodedOutputStreamMicro.computeInt64Size(9, getNumEntries());
      if (hasViewFilter())
        k += CodedOutputStreamMicro.computeInt32Size(10, getViewFilter());
      if (hasRankingType())
        k += CodedOutputStreamMicro.computeStringSize(11, getRankingType());
      if (hasRetrieveCarrierChannel())
        k += CodedOutputStreamMicro.computeBoolSize(12, getRetrieveCarrierChannel());
      int m = 0;
      Iterator localIterator2 = getPendingDownloadAssetIdList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int n = k + m + 1 * getPendingDownloadAssetIdList().size();
      if (hasReconstructVendingHistory())
        n += CodedOutputStreamMicro.computeBoolSize(14, getReconstructVendingHistory());
      if (hasUnfilteredResults())
        n += CodedOutputStreamMicro.computeBoolSize(15, getUnfilteredResults());
      int i1 = 0;
      Iterator localIterator3 = getBadgeIdList().iterator();
      while (localIterator3.hasNext())
        i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator3.next());
      int i2 = n + i1 + 2 * getBadgeIdList().size();
      this.cachedSize = i2;
      return i2;
    }

    public int getSortOrder()
    {
      return this.sortOrder_;
    }

    public long getStartIndex()
    {
      return this.startIndex_;
    }

    public boolean getUnfilteredResults()
    {
      return this.unfilteredResults_;
    }

    public int getViewFilter()
    {
      return this.viewFilter_;
    }

    public boolean hasAssetType()
    {
      return this.hasAssetType;
    }

    public boolean hasCategoryId()
    {
      return this.hasCategoryId;
    }

    public boolean hasNumEntries()
    {
      return this.hasNumEntries;
    }

    public boolean hasQuery()
    {
      return this.hasQuery;
    }

    public boolean hasRankingType()
    {
      return this.hasRankingType;
    }

    public boolean hasReconstructVendingHistory()
    {
      return this.hasReconstructVendingHistory;
    }

    public boolean hasRetrieveCarrierChannel()
    {
      return this.hasRetrieveCarrierChannel;
    }

    public boolean hasRetrieveExtendedInfo()
    {
      return this.hasRetrieveExtendedInfo;
    }

    public boolean hasRetrieveVendingHistory()
    {
      return this.hasRetrieveVendingHistory;
    }

    public boolean hasSortOrder()
    {
      return this.hasSortOrder;
    }

    public boolean hasStartIndex()
    {
      return this.hasStartIndex;
    }

    public boolean hasUnfilteredResults()
    {
      return this.hasUnfilteredResults;
    }

    public boolean hasViewFilter()
    {
      return this.hasViewFilter;
    }

    public AssetsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setQuery(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setCategoryId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          addAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setRetrieveVendingHistory(paramCodedInputStreamMicro.readBool());
          break;
        case 48:
          setRetrieveExtendedInfo(paramCodedInputStreamMicro.readBool());
          break;
        case 56:
          setSortOrder(paramCodedInputStreamMicro.readInt32());
          break;
        case 64:
          setStartIndex(paramCodedInputStreamMicro.readInt64());
          break;
        case 72:
          setNumEntries(paramCodedInputStreamMicro.readInt64());
          break;
        case 80:
          setViewFilter(paramCodedInputStreamMicro.readInt32());
          break;
        case 90:
          setRankingType(paramCodedInputStreamMicro.readString());
          break;
        case 96:
          setRetrieveCarrierChannel(paramCodedInputStreamMicro.readBool());
          break;
        case 106:
          addPendingDownloadAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 112:
          setReconstructVendingHistory(paramCodedInputStreamMicro.readBool());
          break;
        case 120:
          setUnfilteredResults(paramCodedInputStreamMicro.readBool());
          break;
        case 130:
        }
        addBadgeId(paramCodedInputStreamMicro.readString());
      }
    }

    public AssetsRequestProto setAssetType(int paramInt)
    {
      this.hasAssetType = true;
      this.assetType_ = paramInt;
      return this;
    }

    public AssetsRequestProto setCategoryId(String paramString)
    {
      this.hasCategoryId = true;
      this.categoryId_ = paramString;
      return this;
    }

    public AssetsRequestProto setNumEntries(long paramLong)
    {
      this.hasNumEntries = true;
      this.numEntries_ = paramLong;
      return this;
    }

    public AssetsRequestProto setQuery(String paramString)
    {
      this.hasQuery = true;
      this.query_ = paramString;
      return this;
    }

    public AssetsRequestProto setRankingType(String paramString)
    {
      this.hasRankingType = true;
      this.rankingType_ = paramString;
      return this;
    }

    public AssetsRequestProto setReconstructVendingHistory(boolean paramBoolean)
    {
      this.hasReconstructVendingHistory = true;
      this.reconstructVendingHistory_ = paramBoolean;
      return this;
    }

    public AssetsRequestProto setRetrieveCarrierChannel(boolean paramBoolean)
    {
      this.hasRetrieveCarrierChannel = true;
      this.retrieveCarrierChannel_ = paramBoolean;
      return this;
    }

    public AssetsRequestProto setRetrieveExtendedInfo(boolean paramBoolean)
    {
      this.hasRetrieveExtendedInfo = true;
      this.retrieveExtendedInfo_ = paramBoolean;
      return this;
    }

    public AssetsRequestProto setRetrieveVendingHistory(boolean paramBoolean)
    {
      this.hasRetrieveVendingHistory = true;
      this.retrieveVendingHistory_ = paramBoolean;
      return this;
    }

    public AssetsRequestProto setSortOrder(int paramInt)
    {
      this.hasSortOrder = true;
      this.sortOrder_ = paramInt;
      return this;
    }

    public AssetsRequestProto setStartIndex(long paramLong)
    {
      this.hasStartIndex = true;
      this.startIndex_ = paramLong;
      return this;
    }

    public AssetsRequestProto setUnfilteredResults(boolean paramBoolean)
    {
      this.hasUnfilteredResults = true;
      this.unfilteredResults_ = paramBoolean;
      return this;
    }

    public AssetsRequestProto setViewFilter(int paramInt)
    {
      this.hasViewFilter = true;
      this.viewFilter_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetType())
        paramCodedOutputStreamMicro.writeInt32(1, getAssetType());
      if (hasQuery())
        paramCodedOutputStreamMicro.writeString(2, getQuery());
      if (hasCategoryId())
        paramCodedOutputStreamMicro.writeString(3, getCategoryId());
      Iterator localIterator1 = getAssetIdList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator1.next());
      if (hasRetrieveVendingHistory())
        paramCodedOutputStreamMicro.writeBool(5, getRetrieveVendingHistory());
      if (hasRetrieveExtendedInfo())
        paramCodedOutputStreamMicro.writeBool(6, getRetrieveExtendedInfo());
      if (hasSortOrder())
        paramCodedOutputStreamMicro.writeInt32(7, getSortOrder());
      if (hasStartIndex())
        paramCodedOutputStreamMicro.writeInt64(8, getStartIndex());
      if (hasNumEntries())
        paramCodedOutputStreamMicro.writeInt64(9, getNumEntries());
      if (hasViewFilter())
        paramCodedOutputStreamMicro.writeInt32(10, getViewFilter());
      if (hasRankingType())
        paramCodedOutputStreamMicro.writeString(11, getRankingType());
      if (hasRetrieveCarrierChannel())
        paramCodedOutputStreamMicro.writeBool(12, getRetrieveCarrierChannel());
      Iterator localIterator2 = getPendingDownloadAssetIdList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(13, (String)localIterator2.next());
      if (hasReconstructVendingHistory())
        paramCodedOutputStreamMicro.writeBool(14, getReconstructVendingHistory());
      if (hasUnfilteredResults())
        paramCodedOutputStreamMicro.writeBool(15, getUnfilteredResults());
      Iterator localIterator3 = getBadgeIdList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeString(16, (String)localIterator3.next());
    }
  }

  public static final class AssetsResponseProto extends MessageMicro
  {
    private List<VendingProtos.ExternalAssetProto> altAsset_ = Collections.emptyList();
    private List<VendingProtos.ExternalAssetProto> asset_ = Collections.emptyList();
    private int cachedSize = -1;
    private String correctedQuery_ = "";
    private boolean hasCorrectedQuery;
    private boolean hasHeader;
    private boolean hasListType;
    private boolean hasNumCorrectedEntries;
    private boolean hasNumTotalEntries;
    private String header_ = "";
    private int listType_ = 0;
    private long numCorrectedEntries_ = 0L;
    private long numTotalEntries_ = 0L;

    public AssetsResponseProto addAltAsset(VendingProtos.ExternalAssetProto paramExternalAssetProto)
    {
      if (paramExternalAssetProto == null)
        throw new NullPointerException();
      if (this.altAsset_.isEmpty())
        this.altAsset_ = new ArrayList();
      this.altAsset_.add(paramExternalAssetProto);
      return this;
    }

    public AssetsResponseProto addAsset(VendingProtos.ExternalAssetProto paramExternalAssetProto)
    {
      if (paramExternalAssetProto == null)
        throw new NullPointerException();
      if (this.asset_.isEmpty())
        this.asset_ = new ArrayList();
      this.asset_.add(paramExternalAssetProto);
      return this;
    }

    public List<VendingProtos.ExternalAssetProto> getAltAssetList()
    {
      return this.altAsset_;
    }

    public List<VendingProtos.ExternalAssetProto> getAssetList()
    {
      return this.asset_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCorrectedQuery()
    {
      return this.correctedQuery_;
    }

    public String getHeader()
    {
      return this.header_;
    }

    public int getListType()
    {
      return this.listType_;
    }

    public long getNumCorrectedEntries()
    {
      return this.numCorrectedEntries_;
    }

    public long getNumTotalEntries()
    {
      return this.numTotalEntries_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getAssetList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.ExternalAssetProto)localIterator1.next());
      if (hasNumTotalEntries())
        i += CodedOutputStreamMicro.computeInt64Size(2, getNumTotalEntries());
      if (hasCorrectedQuery())
        i += CodedOutputStreamMicro.computeStringSize(3, getCorrectedQuery());
      Iterator localIterator2 = getAltAssetList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (VendingProtos.ExternalAssetProto)localIterator2.next());
      if (hasNumCorrectedEntries())
        i += CodedOutputStreamMicro.computeInt64Size(5, getNumCorrectedEntries());
      if (hasHeader())
        i += CodedOutputStreamMicro.computeStringSize(6, getHeader());
      if (hasListType())
        i += CodedOutputStreamMicro.computeInt32Size(7, getListType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCorrectedQuery()
    {
      return this.hasCorrectedQuery;
    }

    public boolean hasHeader()
    {
      return this.hasHeader;
    }

    public boolean hasListType()
    {
      return this.hasListType;
    }

    public boolean hasNumCorrectedEntries()
    {
      return this.hasNumCorrectedEntries;
    }

    public boolean hasNumTotalEntries()
    {
      return this.hasNumTotalEntries;
    }

    public AssetsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.ExternalAssetProto localExternalAssetProto2 = new VendingProtos.ExternalAssetProto();
          paramCodedInputStreamMicro.readMessage(localExternalAssetProto2);
          addAsset(localExternalAssetProto2);
          break;
        case 16:
          setNumTotalEntries(paramCodedInputStreamMicro.readInt64());
          break;
        case 26:
          setCorrectedQuery(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          VendingProtos.ExternalAssetProto localExternalAssetProto1 = new VendingProtos.ExternalAssetProto();
          paramCodedInputStreamMicro.readMessage(localExternalAssetProto1);
          addAltAsset(localExternalAssetProto1);
          break;
        case 40:
          setNumCorrectedEntries(paramCodedInputStreamMicro.readInt64());
          break;
        case 50:
          setHeader(paramCodedInputStreamMicro.readString());
          break;
        case 56:
        }
        setListType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public AssetsResponseProto setCorrectedQuery(String paramString)
    {
      this.hasCorrectedQuery = true;
      this.correctedQuery_ = paramString;
      return this;
    }

    public AssetsResponseProto setHeader(String paramString)
    {
      this.hasHeader = true;
      this.header_ = paramString;
      return this;
    }

    public AssetsResponseProto setListType(int paramInt)
    {
      this.hasListType = true;
      this.listType_ = paramInt;
      return this;
    }

    public AssetsResponseProto setNumCorrectedEntries(long paramLong)
    {
      this.hasNumCorrectedEntries = true;
      this.numCorrectedEntries_ = paramLong;
      return this;
    }

    public AssetsResponseProto setNumTotalEntries(long paramLong)
    {
      this.hasNumTotalEntries = true;
      this.numTotalEntries_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getAssetList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.ExternalAssetProto)localIterator1.next());
      if (hasNumTotalEntries())
        paramCodedOutputStreamMicro.writeInt64(2, getNumTotalEntries());
      if (hasCorrectedQuery())
        paramCodedOutputStreamMicro.writeString(3, getCorrectedQuery());
      Iterator localIterator2 = getAltAssetList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (VendingProtos.ExternalAssetProto)localIterator2.next());
      if (hasNumCorrectedEntries())
        paramCodedOutputStreamMicro.writeInt64(5, getNumCorrectedEntries());
      if (hasHeader())
        paramCodedOutputStreamMicro.writeString(6, getHeader());
      if (hasListType())
        paramCodedOutputStreamMicro.writeInt32(7, getListType());
    }
  }

  public static final class BillingEventRequestProto extends MessageMicro
  {
    private String billingParametersId_ = "";
    private int cachedSize = -1;
    private VendingProtos.ExternalCarrierBillingInstrumentProto carrierInstrument_ = null;
    private String clientMessage_ = "";
    private int eventType_ = 0;
    private boolean hasBillingParametersId;
    private boolean hasCarrierInstrument;
    private boolean hasClientMessage;
    private boolean hasEventType;
    private boolean hasResultSuccess;
    private boolean resultSuccess_ = false;

    public String getBillingParametersId()
    {
      return this.billingParametersId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.ExternalCarrierBillingInstrumentProto getCarrierInstrument()
    {
      return this.carrierInstrument_;
    }

    public String getClientMessage()
    {
      return this.clientMessage_;
    }

    public int getEventType()
    {
      return this.eventType_;
    }

    public boolean getResultSuccess()
    {
      return this.resultSuccess_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasEventType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getEventType());
      if (hasBillingParametersId())
        i += CodedOutputStreamMicro.computeStringSize(2, getBillingParametersId());
      if (hasResultSuccess())
        i += CodedOutputStreamMicro.computeBoolSize(3, getResultSuccess());
      if (hasClientMessage())
        i += CodedOutputStreamMicro.computeStringSize(4, getClientMessage());
      if (hasCarrierInstrument())
        i += CodedOutputStreamMicro.computeMessageSize(5, getCarrierInstrument());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBillingParametersId()
    {
      return this.hasBillingParametersId;
    }

    public boolean hasCarrierInstrument()
    {
      return this.hasCarrierInstrument;
    }

    public boolean hasClientMessage()
    {
      return this.hasClientMessage;
    }

    public boolean hasEventType()
    {
      return this.hasEventType;
    }

    public boolean hasResultSuccess()
    {
      return this.hasResultSuccess;
    }

    public BillingEventRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setEventType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setBillingParametersId(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setResultSuccess(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          setClientMessage(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        VendingProtos.ExternalCarrierBillingInstrumentProto localExternalCarrierBillingInstrumentProto = new VendingProtos.ExternalCarrierBillingInstrumentProto();
        paramCodedInputStreamMicro.readMessage(localExternalCarrierBillingInstrumentProto);
        setCarrierInstrument(localExternalCarrierBillingInstrumentProto);
      }
    }

    public BillingEventRequestProto setBillingParametersId(String paramString)
    {
      this.hasBillingParametersId = true;
      this.billingParametersId_ = paramString;
      return this;
    }

    public BillingEventRequestProto setCarrierInstrument(VendingProtos.ExternalCarrierBillingInstrumentProto paramExternalCarrierBillingInstrumentProto)
    {
      if (paramExternalCarrierBillingInstrumentProto == null)
        throw new NullPointerException();
      this.hasCarrierInstrument = true;
      this.carrierInstrument_ = paramExternalCarrierBillingInstrumentProto;
      return this;
    }

    public BillingEventRequestProto setClientMessage(String paramString)
    {
      this.hasClientMessage = true;
      this.clientMessage_ = paramString;
      return this;
    }

    public BillingEventRequestProto setEventType(int paramInt)
    {
      this.hasEventType = true;
      this.eventType_ = paramInt;
      return this;
    }

    public BillingEventRequestProto setResultSuccess(boolean paramBoolean)
    {
      this.hasResultSuccess = true;
      this.resultSuccess_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasEventType())
        paramCodedOutputStreamMicro.writeInt32(1, getEventType());
      if (hasBillingParametersId())
        paramCodedOutputStreamMicro.writeString(2, getBillingParametersId());
      if (hasResultSuccess())
        paramCodedOutputStreamMicro.writeBool(3, getResultSuccess());
      if (hasClientMessage())
        paramCodedOutputStreamMicro.writeString(4, getClientMessage());
      if (hasCarrierInstrument())
        paramCodedOutputStreamMicro.writeMessage(5, getCarrierInstrument());
    }
  }

  public static final class BillingEventResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public BillingEventResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class BillingParameterProto extends MessageMicro
  {
    private int apiVersion_ = 1;
    private String applicationId_ = "";
    private List<String> backendUrl_ = Collections.emptyList();
    private int billingInstrumentType_ = 0;
    private int cachedSize = -1;
    private int deviceAssociationMethod_ = 1;
    private boolean hasApiVersion;
    private boolean hasApplicationId;
    private boolean hasBillingInstrumentType;
    private boolean hasDeviceAssociationMethod;
    private boolean hasIconId;
    private boolean hasId;
    private boolean hasInstrumentTosRequired;
    private boolean hasName;
    private boolean hasPassphraseRequired;
    private boolean hasPerTransactionCredentialsRequired;
    private boolean hasSendSubscriberIdWithCarrierBillingRequests;
    private boolean hasTosUrl;
    private boolean hasUserTokenRequestAddress;
    private boolean hasUserTokenRequestMessage;
    private String iconId_ = "";
    private String id_ = "";
    private boolean instrumentTosRequired_ = true;
    private List<String> mncMcc_ = Collections.emptyList();
    private String name_ = "";
    private boolean passphraseRequired_ = false;
    private boolean perTransactionCredentialsRequired_ = false;
    private boolean sendSubscriberIdWithCarrierBillingRequests_ = false;
    private String tosUrl_ = "";
    private String userTokenRequestAddress_ = "";
    private String userTokenRequestMessage_ = "";

    public BillingParameterProto addBackendUrl(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.backendUrl_.isEmpty())
        this.backendUrl_ = new ArrayList();
      this.backendUrl_.add(paramString);
      return this;
    }

    public BillingParameterProto addMncMcc(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.mncMcc_.isEmpty())
        this.mncMcc_ = new ArrayList();
      this.mncMcc_.add(paramString);
      return this;
    }

    public int getApiVersion()
    {
      return this.apiVersion_;
    }

    public String getApplicationId()
    {
      return this.applicationId_;
    }

    public String getBackendUrl(int paramInt)
    {
      return (String)this.backendUrl_.get(paramInt);
    }

    public List<String> getBackendUrlList()
    {
      return this.backendUrl_;
    }

    public int getBillingInstrumentType()
    {
      return this.billingInstrumentType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getDeviceAssociationMethod()
    {
      return this.deviceAssociationMethod_;
    }

    public String getIconId()
    {
      return this.iconId_;
    }

    public String getId()
    {
      return this.id_;
    }

    public boolean getInstrumentTosRequired()
    {
      return this.instrumentTosRequired_;
    }

    public List<String> getMncMccList()
    {
      return this.mncMcc_;
    }

    public String getName()
    {
      return this.name_;
    }

    public boolean getPassphraseRequired()
    {
      return this.passphraseRequired_;
    }

    public boolean getPerTransactionCredentialsRequired()
    {
      return this.perTransactionCredentialsRequired_;
    }

    public boolean getSendSubscriberIdWithCarrierBillingRequests()
    {
      return this.sendSubscriberIdWithCarrierBillingRequests_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getId());
      if (hasName())
        i += CodedOutputStreamMicro.computeStringSize(2, getName());
      int j = 0;
      Iterator localIterator1 = getMncMccList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getMncMccList().size();
      int m = 0;
      Iterator localIterator2 = getBackendUrlList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int n = k + m + 1 * getBackendUrlList().size();
      if (hasIconId())
        n += CodedOutputStreamMicro.computeStringSize(5, getIconId());
      if (hasBillingInstrumentType())
        n += CodedOutputStreamMicro.computeInt32Size(6, getBillingInstrumentType());
      if (hasApplicationId())
        n += CodedOutputStreamMicro.computeStringSize(7, getApplicationId());
      if (hasTosUrl())
        n += CodedOutputStreamMicro.computeStringSize(8, getTosUrl());
      if (hasInstrumentTosRequired())
        n += CodedOutputStreamMicro.computeBoolSize(9, getInstrumentTosRequired());
      if (hasApiVersion())
        n += CodedOutputStreamMicro.computeInt32Size(10, getApiVersion());
      if (hasPerTransactionCredentialsRequired())
        n += CodedOutputStreamMicro.computeBoolSize(11, getPerTransactionCredentialsRequired());
      if (hasSendSubscriberIdWithCarrierBillingRequests())
        n += CodedOutputStreamMicro.computeBoolSize(12, getSendSubscriberIdWithCarrierBillingRequests());
      if (hasDeviceAssociationMethod())
        n += CodedOutputStreamMicro.computeInt32Size(13, getDeviceAssociationMethod());
      if (hasUserTokenRequestMessage())
        n += CodedOutputStreamMicro.computeStringSize(14, getUserTokenRequestMessage());
      if (hasUserTokenRequestAddress())
        n += CodedOutputStreamMicro.computeStringSize(15, getUserTokenRequestAddress());
      if (hasPassphraseRequired())
        n += CodedOutputStreamMicro.computeBoolSize(16, getPassphraseRequired());
      this.cachedSize = n;
      return n;
    }

    public String getTosUrl()
    {
      return this.tosUrl_;
    }

    public String getUserTokenRequestAddress()
    {
      return this.userTokenRequestAddress_;
    }

    public String getUserTokenRequestMessage()
    {
      return this.userTokenRequestMessage_;
    }

    public boolean hasApiVersion()
    {
      return this.hasApiVersion;
    }

    public boolean hasApplicationId()
    {
      return this.hasApplicationId;
    }

    public boolean hasBillingInstrumentType()
    {
      return this.hasBillingInstrumentType;
    }

    public boolean hasDeviceAssociationMethod()
    {
      return this.hasDeviceAssociationMethod;
    }

    public boolean hasIconId()
    {
      return this.hasIconId;
    }

    public boolean hasId()
    {
      return this.hasId;
    }

    public boolean hasInstrumentTosRequired()
    {
      return this.hasInstrumentTosRequired;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasPassphraseRequired()
    {
      return this.hasPassphraseRequired;
    }

    public boolean hasPerTransactionCredentialsRequired()
    {
      return this.hasPerTransactionCredentialsRequired;
    }

    public boolean hasSendSubscriberIdWithCarrierBillingRequests()
    {
      return this.hasSendSubscriberIdWithCarrierBillingRequests;
    }

    public boolean hasTosUrl()
    {
      return this.hasTosUrl;
    }

    public boolean hasUserTokenRequestAddress()
    {
      return this.hasUserTokenRequestAddress;
    }

    public boolean hasUserTokenRequestMessage()
    {
      return this.hasUserTokenRequestMessage;
    }

    public BillingParameterProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          addMncMcc(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          addBackendUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setIconId(paramCodedInputStreamMicro.readString());
          break;
        case 48:
          setBillingInstrumentType(paramCodedInputStreamMicro.readInt32());
          break;
        case 58:
          setApplicationId(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setTosUrl(paramCodedInputStreamMicro.readString());
          break;
        case 72:
          setInstrumentTosRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 80:
          setApiVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 88:
          setPerTransactionCredentialsRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 96:
          setSendSubscriberIdWithCarrierBillingRequests(paramCodedInputStreamMicro.readBool());
          break;
        case 104:
          setDeviceAssociationMethod(paramCodedInputStreamMicro.readInt32());
          break;
        case 114:
          setUserTokenRequestMessage(paramCodedInputStreamMicro.readString());
          break;
        case 122:
          setUserTokenRequestAddress(paramCodedInputStreamMicro.readString());
          break;
        case 128:
        }
        setPassphraseRequired(paramCodedInputStreamMicro.readBool());
      }
    }

    public BillingParameterProto setApiVersion(int paramInt)
    {
      this.hasApiVersion = true;
      this.apiVersion_ = paramInt;
      return this;
    }

    public BillingParameterProto setApplicationId(String paramString)
    {
      this.hasApplicationId = true;
      this.applicationId_ = paramString;
      return this;
    }

    public BillingParameterProto setBillingInstrumentType(int paramInt)
    {
      this.hasBillingInstrumentType = true;
      this.billingInstrumentType_ = paramInt;
      return this;
    }

    public BillingParameterProto setDeviceAssociationMethod(int paramInt)
    {
      this.hasDeviceAssociationMethod = true;
      this.deviceAssociationMethod_ = paramInt;
      return this;
    }

    public BillingParameterProto setIconId(String paramString)
    {
      this.hasIconId = true;
      this.iconId_ = paramString;
      return this;
    }

    public BillingParameterProto setId(String paramString)
    {
      this.hasId = true;
      this.id_ = paramString;
      return this;
    }

    public BillingParameterProto setInstrumentTosRequired(boolean paramBoolean)
    {
      this.hasInstrumentTosRequired = true;
      this.instrumentTosRequired_ = paramBoolean;
      return this;
    }

    public BillingParameterProto setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public BillingParameterProto setPassphraseRequired(boolean paramBoolean)
    {
      this.hasPassphraseRequired = true;
      this.passphraseRequired_ = paramBoolean;
      return this;
    }

    public BillingParameterProto setPerTransactionCredentialsRequired(boolean paramBoolean)
    {
      this.hasPerTransactionCredentialsRequired = true;
      this.perTransactionCredentialsRequired_ = paramBoolean;
      return this;
    }

    public BillingParameterProto setSendSubscriberIdWithCarrierBillingRequests(boolean paramBoolean)
    {
      this.hasSendSubscriberIdWithCarrierBillingRequests = true;
      this.sendSubscriberIdWithCarrierBillingRequests_ = paramBoolean;
      return this;
    }

    public BillingParameterProto setTosUrl(String paramString)
    {
      this.hasTosUrl = true;
      this.tosUrl_ = paramString;
      return this;
    }

    public BillingParameterProto setUserTokenRequestAddress(String paramString)
    {
      this.hasUserTokenRequestAddress = true;
      this.userTokenRequestAddress_ = paramString;
      return this;
    }

    public BillingParameterProto setUserTokenRequestMessage(String paramString)
    {
      this.hasUserTokenRequestMessage = true;
      this.userTokenRequestMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasId())
        paramCodedOutputStreamMicro.writeString(1, getId());
      if (hasName())
        paramCodedOutputStreamMicro.writeString(2, getName());
      Iterator localIterator1 = getMncMccList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator1.next());
      Iterator localIterator2 = getBackendUrlList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator2.next());
      if (hasIconId())
        paramCodedOutputStreamMicro.writeString(5, getIconId());
      if (hasBillingInstrumentType())
        paramCodedOutputStreamMicro.writeInt32(6, getBillingInstrumentType());
      if (hasApplicationId())
        paramCodedOutputStreamMicro.writeString(7, getApplicationId());
      if (hasTosUrl())
        paramCodedOutputStreamMicro.writeString(8, getTosUrl());
      if (hasInstrumentTosRequired())
        paramCodedOutputStreamMicro.writeBool(9, getInstrumentTosRequired());
      if (hasApiVersion())
        paramCodedOutputStreamMicro.writeInt32(10, getApiVersion());
      if (hasPerTransactionCredentialsRequired())
        paramCodedOutputStreamMicro.writeBool(11, getPerTransactionCredentialsRequired());
      if (hasSendSubscriberIdWithCarrierBillingRequests())
        paramCodedOutputStreamMicro.writeBool(12, getSendSubscriberIdWithCarrierBillingRequests());
      if (hasDeviceAssociationMethod())
        paramCodedOutputStreamMicro.writeInt32(13, getDeviceAssociationMethod());
      if (hasUserTokenRequestMessage())
        paramCodedOutputStreamMicro.writeString(14, getUserTokenRequestMessage());
      if (hasUserTokenRequestAddress())
        paramCodedOutputStreamMicro.writeString(15, getUserTokenRequestAddress());
      if (hasPassphraseRequired())
        paramCodedOutputStreamMicro.writeBool(16, getPassphraseRequired());
    }
  }

  public static final class CarrierBillingCredentialsProto extends MessageMicro
  {
    private int cachedSize = -1;
    private long credentialsTimeout_ = 0L;
    private String credentials_ = "";
    private boolean hasCredentials;
    private boolean hasCredentialsTimeout;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCredentials()
    {
      return this.credentials_;
    }

    public long getCredentialsTimeout()
    {
      return this.credentialsTimeout_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCredentials())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getCredentials());
      if (hasCredentialsTimeout())
        i += CodedOutputStreamMicro.computeInt64Size(2, getCredentialsTimeout());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCredentials()
    {
      return this.hasCredentials;
    }

    public boolean hasCredentialsTimeout()
    {
      return this.hasCredentialsTimeout;
    }

    public CarrierBillingCredentialsProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCredentials(paramCodedInputStreamMicro.readString());
          break;
        case 16:
        }
        setCredentialsTimeout(paramCodedInputStreamMicro.readInt64());
      }
    }

    public CarrierBillingCredentialsProto setCredentials(String paramString)
    {
      this.hasCredentials = true;
      this.credentials_ = paramString;
      return this;
    }

    public CarrierBillingCredentialsProto setCredentialsTimeout(long paramLong)
    {
      this.hasCredentialsTimeout = true;
      this.credentialsTimeout_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCredentials())
        paramCodedOutputStreamMicro.writeString(1, getCredentials());
      if (hasCredentialsTimeout())
        paramCodedOutputStreamMicro.writeInt64(2, getCredentialsTimeout());
    }
  }

  public static final class CategoryProto extends MessageMicro
  {
    private int assetType_ = 0;
    private int cachedSize = -1;
    private String categoryDisplay_ = "";
    private String categoryId_ = "";
    private String categorySubtitle_ = "";
    private boolean hasAssetType;
    private boolean hasCategoryDisplay;
    private boolean hasCategoryId;
    private boolean hasCategorySubtitle;
    private List<String> promotedAssetsFree_ = Collections.emptyList();
    private List<String> promotedAssetsHome_ = Collections.emptyList();
    private List<String> promotedAssetsNew_ = Collections.emptyList();
    private List<String> promotedAssetsPaid_ = Collections.emptyList();
    private List<CategoryProto> subCategories_ = Collections.emptyList();

    public CategoryProto addPromotedAssetsFree(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.promotedAssetsFree_.isEmpty())
        this.promotedAssetsFree_ = new ArrayList();
      this.promotedAssetsFree_.add(paramString);
      return this;
    }

    public CategoryProto addPromotedAssetsHome(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.promotedAssetsHome_.isEmpty())
        this.promotedAssetsHome_ = new ArrayList();
      this.promotedAssetsHome_.add(paramString);
      return this;
    }

    public CategoryProto addPromotedAssetsNew(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.promotedAssetsNew_.isEmpty())
        this.promotedAssetsNew_ = new ArrayList();
      this.promotedAssetsNew_.add(paramString);
      return this;
    }

    public CategoryProto addPromotedAssetsPaid(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.promotedAssetsPaid_.isEmpty())
        this.promotedAssetsPaid_ = new ArrayList();
      this.promotedAssetsPaid_.add(paramString);
      return this;
    }

    public CategoryProto addSubCategories(CategoryProto paramCategoryProto)
    {
      if (paramCategoryProto == null)
        throw new NullPointerException();
      if (this.subCategories_.isEmpty())
        this.subCategories_ = new ArrayList();
      this.subCategories_.add(paramCategoryProto);
      return this;
    }

    public int getAssetType()
    {
      return this.assetType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCategoryDisplay()
    {
      return this.categoryDisplay_;
    }

    public String getCategoryId()
    {
      return this.categoryId_;
    }

    public String getCategorySubtitle()
    {
      return this.categorySubtitle_;
    }

    public List<String> getPromotedAssetsFreeList()
    {
      return this.promotedAssetsFree_;
    }

    public List<String> getPromotedAssetsHomeList()
    {
      return this.promotedAssetsHome_;
    }

    public List<String> getPromotedAssetsNewList()
    {
      return this.promotedAssetsNew_;
    }

    public List<String> getPromotedAssetsPaidList()
    {
      return this.promotedAssetsPaid_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(2, getAssetType());
      if (hasCategoryId())
        i += CodedOutputStreamMicro.computeStringSize(3, getCategoryId());
      if (hasCategoryDisplay())
        i += CodedOutputStreamMicro.computeStringSize(4, getCategoryDisplay());
      if (hasCategorySubtitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getCategorySubtitle());
      int j = 0;
      Iterator localIterator1 = getPromotedAssetsNewList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getPromotedAssetsNewList().size();
      int m = 0;
      Iterator localIterator2 = getPromotedAssetsHomeList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int n = k + m + 1 * getPromotedAssetsHomeList().size();
      Iterator localIterator3 = getSubCategoriesList().iterator();
      while (localIterator3.hasNext())
        n += CodedOutputStreamMicro.computeMessageSize(8, (CategoryProto)localIterator3.next());
      int i1 = 0;
      Iterator localIterator4 = getPromotedAssetsPaidList().iterator();
      while (localIterator4.hasNext())
        i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator4.next());
      int i2 = n + i1 + 1 * getPromotedAssetsPaidList().size();
      int i3 = 0;
      Iterator localIterator5 = getPromotedAssetsFreeList().iterator();
      while (localIterator5.hasNext())
        i3 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator5.next());
      int i4 = i2 + i3 + 1 * getPromotedAssetsFreeList().size();
      this.cachedSize = i4;
      return i4;
    }

    public List<CategoryProto> getSubCategoriesList()
    {
      return this.subCategories_;
    }

    public boolean hasAssetType()
    {
      return this.hasAssetType;
    }

    public boolean hasCategoryDisplay()
    {
      return this.hasCategoryDisplay;
    }

    public boolean hasCategoryId()
    {
      return this.hasCategoryId;
    }

    public boolean hasCategorySubtitle()
    {
      return this.hasCategorySubtitle;
    }

    public CategoryProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 16:
          setAssetType(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setCategoryId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setCategoryDisplay(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setCategorySubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          addPromotedAssetsNew(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          addPromotedAssetsHome(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          CategoryProto localCategoryProto = new CategoryProto();
          paramCodedInputStreamMicro.readMessage(localCategoryProto);
          addSubCategories(localCategoryProto);
          break;
        case 74:
          addPromotedAssetsPaid(paramCodedInputStreamMicro.readString());
          break;
        case 82:
        }
        addPromotedAssetsFree(paramCodedInputStreamMicro.readString());
      }
    }

    public CategoryProto setAssetType(int paramInt)
    {
      this.hasAssetType = true;
      this.assetType_ = paramInt;
      return this;
    }

    public CategoryProto setCategoryDisplay(String paramString)
    {
      this.hasCategoryDisplay = true;
      this.categoryDisplay_ = paramString;
      return this;
    }

    public CategoryProto setCategoryId(String paramString)
    {
      this.hasCategoryId = true;
      this.categoryId_ = paramString;
      return this;
    }

    public CategoryProto setCategorySubtitle(String paramString)
    {
      this.hasCategorySubtitle = true;
      this.categorySubtitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetType())
        paramCodedOutputStreamMicro.writeInt32(2, getAssetType());
      if (hasCategoryId())
        paramCodedOutputStreamMicro.writeString(3, getCategoryId());
      if (hasCategoryDisplay())
        paramCodedOutputStreamMicro.writeString(4, getCategoryDisplay());
      if (hasCategorySubtitle())
        paramCodedOutputStreamMicro.writeString(5, getCategorySubtitle());
      Iterator localIterator1 = getPromotedAssetsNewList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(6, (String)localIterator1.next());
      Iterator localIterator2 = getPromotedAssetsHomeList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(7, (String)localIterator2.next());
      Iterator localIterator3 = getSubCategoriesList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (CategoryProto)localIterator3.next());
      Iterator localIterator4 = getPromotedAssetsPaidList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeString(9, (String)localIterator4.next());
      Iterator localIterator5 = getPromotedAssetsFreeList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeString(10, (String)localIterator5.next());
    }
  }

  public static final class CheckForNotificationsRequestProto extends MessageMicro
  {
    private long alarmDuration_ = 0L;
    private int cachedSize = -1;
    private boolean hasAlarmDuration;

    public long getAlarmDuration()
    {
      return this.alarmDuration_;
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
      if (hasAlarmDuration())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getAlarmDuration());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAlarmDuration()
    {
      return this.hasAlarmDuration;
    }

    public CheckForNotificationsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setAlarmDuration(paramCodedInputStreamMicro.readInt64());
      }
    }

    public CheckForNotificationsRequestProto setAlarmDuration(long paramLong)
    {
      this.hasAlarmDuration = true;
      this.alarmDuration_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAlarmDuration())
        paramCodedOutputStreamMicro.writeInt64(1, getAlarmDuration());
    }
  }

  public static final class CheckForNotificationsResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public CheckForNotificationsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class CheckLicenseRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasNonce;
    private boolean hasPackageName;
    private boolean hasVersionCode;
    private long nonce_ = 0L;
    private String packageName_ = "";
    private int versionCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getNonce()
    {
      return this.nonce_;
    }

    public String getPackageName()
    {
      return this.packageName_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPackageName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPackageName());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
      if (hasNonce())
        i += CodedOutputStreamMicro.computeInt64Size(3, getNonce());
      this.cachedSize = i;
      return i;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasNonce()
    {
      return this.hasNonce;
    }

    public boolean hasPackageName()
    {
      return this.hasPackageName;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public CheckLicenseRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPackageName(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
        }
        setNonce(paramCodedInputStreamMicro.readInt64());
      }
    }

    public CheckLicenseRequestProto setNonce(long paramLong)
    {
      this.hasNonce = true;
      this.nonce_ = paramLong;
      return this;
    }

    public CheckLicenseRequestProto setPackageName(String paramString)
    {
      this.hasPackageName = true;
      this.packageName_ = paramString;
      return this;
    }

    public CheckLicenseRequestProto setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPackageName())
        paramCodedOutputStreamMicro.writeString(1, getPackageName());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      if (hasNonce())
        paramCodedOutputStreamMicro.writeInt64(3, getNonce());
    }
  }

  public static final class CheckLicenseResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasResponseCode;
    private boolean hasSignature;
    private boolean hasSignedData;
    private int responseCode_ = 0;
    private String signature_ = "";
    private String signedData_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getResponseCode()
    {
      return this.responseCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResponseCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResponseCode());
      if (hasSignedData())
        i += CodedOutputStreamMicro.computeStringSize(2, getSignedData());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeStringSize(3, getSignature());
      this.cachedSize = i;
      return i;
    }

    public String getSignature()
    {
      return this.signature_;
    }

    public String getSignedData()
    {
      return this.signedData_;
    }

    public boolean hasResponseCode()
    {
      return this.hasResponseCode;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public boolean hasSignedData()
    {
      return this.hasSignedData;
    }

    public CheckLicenseResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResponseCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setSignedData(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setSignature(paramCodedInputStreamMicro.readString());
      }
    }

    public CheckLicenseResponseProto setResponseCode(int paramInt)
    {
      this.hasResponseCode = true;
      this.responseCode_ = paramInt;
      return this;
    }

    public CheckLicenseResponseProto setSignature(String paramString)
    {
      this.hasSignature = true;
      this.signature_ = paramString;
      return this;
    }

    public CheckLicenseResponseProto setSignedData(String paramString)
    {
      this.hasSignedData = true;
      this.signedData_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResponseCode())
        paramCodedOutputStreamMicro.writeInt32(1, getResponseCode());
      if (hasSignedData())
        paramCodedOutputStreamMicro.writeString(2, getSignedData());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeString(3, getSignature());
    }
  }

  public static final class CommentsRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private String assetReferrer_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;
    private boolean hasAssetReferrer;
    private boolean hasNumEntries;
    private boolean hasShouldReturnSelfComment;
    private boolean hasStartIndex;
    private long numEntries_ = 0L;
    private boolean shouldReturnSelfComment_ = false;
    private long startIndex_ = 0L;

    public String getAssetId()
    {
      return this.assetId_;
    }

    public String getAssetReferrer()
    {
      return this.assetReferrer_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getNumEntries()
    {
      return this.numEntries_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasStartIndex())
        i += CodedOutputStreamMicro.computeInt64Size(2, getStartIndex());
      if (hasNumEntries())
        i += CodedOutputStreamMicro.computeInt64Size(3, getNumEntries());
      if (hasShouldReturnSelfComment())
        i += CodedOutputStreamMicro.computeBoolSize(4, getShouldReturnSelfComment());
      if (hasAssetReferrer())
        i += CodedOutputStreamMicro.computeStringSize(5, getAssetReferrer());
      this.cachedSize = i;
      return i;
    }

    public boolean getShouldReturnSelfComment()
    {
      return this.shouldReturnSelfComment_;
    }

    public long getStartIndex()
    {
      return this.startIndex_;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasAssetReferrer()
    {
      return this.hasAssetReferrer;
    }

    public boolean hasNumEntries()
    {
      return this.hasNumEntries;
    }

    public boolean hasShouldReturnSelfComment()
    {
      return this.hasShouldReturnSelfComment;
    }

    public boolean hasStartIndex()
    {
      return this.hasStartIndex;
    }

    public CommentsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setStartIndex(paramCodedInputStreamMicro.readInt64());
          break;
        case 24:
          setNumEntries(paramCodedInputStreamMicro.readInt64());
          break;
        case 32:
          setShouldReturnSelfComment(paramCodedInputStreamMicro.readBool());
          break;
        case 42:
        }
        setAssetReferrer(paramCodedInputStreamMicro.readString());
      }
    }

    public CommentsRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public CommentsRequestProto setAssetReferrer(String paramString)
    {
      this.hasAssetReferrer = true;
      this.assetReferrer_ = paramString;
      return this;
    }

    public CommentsRequestProto setNumEntries(long paramLong)
    {
      this.hasNumEntries = true;
      this.numEntries_ = paramLong;
      return this;
    }

    public CommentsRequestProto setShouldReturnSelfComment(boolean paramBoolean)
    {
      this.hasShouldReturnSelfComment = true;
      this.shouldReturnSelfComment_ = paramBoolean;
      return this;
    }

    public CommentsRequestProto setStartIndex(long paramLong)
    {
      this.hasStartIndex = true;
      this.startIndex_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasStartIndex())
        paramCodedOutputStreamMicro.writeInt64(2, getStartIndex());
      if (hasNumEntries())
        paramCodedOutputStreamMicro.writeInt64(3, getNumEntries());
      if (hasShouldReturnSelfComment())
        paramCodedOutputStreamMicro.writeBool(4, getShouldReturnSelfComment());
      if (hasAssetReferrer())
        paramCodedOutputStreamMicro.writeString(5, getAssetReferrer());
    }
  }

  public static final class CommentsResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private List<VendingProtos.ExternalCommentProto> comment_ = Collections.emptyList();
    private boolean hasNumTotalEntries;
    private boolean hasSelfComment;
    private long numTotalEntries_ = 0L;
    private VendingProtos.ExternalCommentProto selfComment_ = null;

    public CommentsResponseProto addComment(VendingProtos.ExternalCommentProto paramExternalCommentProto)
    {
      if (paramExternalCommentProto == null)
        throw new NullPointerException();
      if (this.comment_.isEmpty())
        this.comment_ = new ArrayList();
      this.comment_.add(paramExternalCommentProto);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<VendingProtos.ExternalCommentProto> getCommentList()
    {
      return this.comment_;
    }

    public long getNumTotalEntries()
    {
      return this.numTotalEntries_;
    }

    public VendingProtos.ExternalCommentProto getSelfComment()
    {
      return this.selfComment_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getCommentList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.ExternalCommentProto)localIterator.next());
      if (hasNumTotalEntries())
        i += CodedOutputStreamMicro.computeInt64Size(2, getNumTotalEntries());
      if (hasSelfComment())
        i += CodedOutputStreamMicro.computeMessageSize(3, getSelfComment());
      this.cachedSize = i;
      return i;
    }

    public boolean hasNumTotalEntries()
    {
      return this.hasNumTotalEntries;
    }

    public boolean hasSelfComment()
    {
      return this.hasSelfComment;
    }

    public CommentsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.ExternalCommentProto localExternalCommentProto2 = new VendingProtos.ExternalCommentProto();
          paramCodedInputStreamMicro.readMessage(localExternalCommentProto2);
          addComment(localExternalCommentProto2);
          break;
        case 16:
          setNumTotalEntries(paramCodedInputStreamMicro.readInt64());
          break;
        case 26:
        }
        VendingProtos.ExternalCommentProto localExternalCommentProto1 = new VendingProtos.ExternalCommentProto();
        paramCodedInputStreamMicro.readMessage(localExternalCommentProto1);
        setSelfComment(localExternalCommentProto1);
      }
    }

    public CommentsResponseProto setNumTotalEntries(long paramLong)
    {
      this.hasNumTotalEntries = true;
      this.numTotalEntries_ = paramLong;
      return this;
    }

    public CommentsResponseProto setSelfComment(VendingProtos.ExternalCommentProto paramExternalCommentProto)
    {
      if (paramExternalCommentProto == null)
        throw new NullPointerException();
      this.hasSelfComment = true;
      this.selfComment_ = paramExternalCommentProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getCommentList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.ExternalCommentProto)localIterator.next());
      if (hasNumTotalEntries())
        paramCodedOutputStreamMicro.writeInt64(2, getNumTotalEntries());
      if (hasSelfComment())
        paramCodedOutputStreamMicro.writeMessage(3, getSelfComment());
    }
  }

  public static final class ContentSyncRequestProto extends MessageMicro
  {
    private List<AssetInstallState> assetInstallState_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean hasIncremental;
    private boolean hasSideloadedAppCount;
    private boolean incremental_ = false;
    private int sideloadedAppCount_ = 0;
    private List<SystemApp> systemApp_ = Collections.emptyList();

    public ContentSyncRequestProto addAssetInstallState(AssetInstallState paramAssetInstallState)
    {
      if (paramAssetInstallState == null)
        throw new NullPointerException();
      if (this.assetInstallState_.isEmpty())
        this.assetInstallState_ = new ArrayList();
      this.assetInstallState_.add(paramAssetInstallState);
      return this;
    }

    public ContentSyncRequestProto addSystemApp(SystemApp paramSystemApp)
    {
      if (paramSystemApp == null)
        throw new NullPointerException();
      if (this.systemApp_.isEmpty())
        this.systemApp_ = new ArrayList();
      this.systemApp_.add(paramSystemApp);
      return this;
    }

    public int getAssetInstallStateCount()
    {
      return this.assetInstallState_.size();
    }

    public List<AssetInstallState> getAssetInstallStateList()
    {
      return this.assetInstallState_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getIncremental()
    {
      return this.incremental_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasIncremental())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getIncremental());
      Iterator localIterator1 = getAssetInstallStateList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(2, (AssetInstallState)localIterator1.next());
      Iterator localIterator2 = getSystemAppList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(10, (SystemApp)localIterator2.next());
      if (hasSideloadedAppCount())
        i += CodedOutputStreamMicro.computeInt32Size(14, getSideloadedAppCount());
      this.cachedSize = i;
      return i;
    }

    public int getSideloadedAppCount()
    {
      return this.sideloadedAppCount_;
    }

    public int getSystemAppCount()
    {
      return this.systemApp_.size();
    }

    public List<SystemApp> getSystemAppList()
    {
      return this.systemApp_;
    }

    public boolean hasIncremental()
    {
      return this.hasIncremental;
    }

    public boolean hasSideloadedAppCount()
    {
      return this.hasSideloadedAppCount;
    }

    public ContentSyncRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setIncremental(paramCodedInputStreamMicro.readBool());
          break;
        case 19:
          AssetInstallState localAssetInstallState = new AssetInstallState();
          paramCodedInputStreamMicro.readGroup(localAssetInstallState, 2);
          addAssetInstallState(localAssetInstallState);
          break;
        case 83:
          SystemApp localSystemApp = new SystemApp();
          paramCodedInputStreamMicro.readGroup(localSystemApp, 10);
          addSystemApp(localSystemApp);
          break;
        case 112:
        }
        setSideloadedAppCount(paramCodedInputStreamMicro.readInt32());
      }
    }

    public ContentSyncRequestProto setIncremental(boolean paramBoolean)
    {
      this.hasIncremental = true;
      this.incremental_ = paramBoolean;
      return this;
    }

    public ContentSyncRequestProto setSideloadedAppCount(int paramInt)
    {
      this.hasSideloadedAppCount = true;
      this.sideloadedAppCount_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasIncremental())
        paramCodedOutputStreamMicro.writeBool(1, getIncremental());
      Iterator localIterator1 = getAssetInstallStateList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeGroup(2, (AssetInstallState)localIterator1.next());
      Iterator localIterator2 = getSystemAppList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeGroup(10, (SystemApp)localIterator2.next());
      if (hasSideloadedAppCount())
        paramCodedOutputStreamMicro.writeInt32(14, getSideloadedAppCount());
    }

    public static final class AssetInstallState extends MessageMicro
    {
      private String assetId_ = "";
      private String assetReferrer_ = "";
      private int assetState_ = 1;
      private int cachedSize = -1;
      private boolean hasAssetId;
      private boolean hasAssetReferrer;
      private boolean hasAssetState;
      private boolean hasInstallTime;
      private boolean hasPackageName;
      private boolean hasUninstallTime;
      private boolean hasVersionCode;
      private long installTime_ = 0L;
      private String packageName_ = "";
      private long uninstallTime_ = 0L;
      private int versionCode_ = 0;

      public String getAssetId()
      {
        return this.assetId_;
      }

      public String getAssetReferrer()
      {
        return this.assetReferrer_;
      }

      public int getAssetState()
      {
        return this.assetState_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public long getInstallTime()
      {
        return this.installTime_;
      }

      public String getPackageName()
      {
        return this.packageName_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasAssetId())
          i = 0 + CodedOutputStreamMicro.computeStringSize(3, getAssetId());
        if (hasAssetState())
          i += CodedOutputStreamMicro.computeInt32Size(4, getAssetState());
        if (hasInstallTime())
          i += CodedOutputStreamMicro.computeInt64Size(5, getInstallTime());
        if (hasUninstallTime())
          i += CodedOutputStreamMicro.computeInt64Size(6, getUninstallTime());
        if (hasPackageName())
          i += CodedOutputStreamMicro.computeStringSize(7, getPackageName());
        if (hasVersionCode())
          i += CodedOutputStreamMicro.computeInt32Size(8, getVersionCode());
        if (hasAssetReferrer())
          i += CodedOutputStreamMicro.computeStringSize(9, getAssetReferrer());
        this.cachedSize = i;
        return i;
      }

      public long getUninstallTime()
      {
        return this.uninstallTime_;
      }

      public int getVersionCode()
      {
        return this.versionCode_;
      }

      public boolean hasAssetId()
      {
        return this.hasAssetId;
      }

      public boolean hasAssetReferrer()
      {
        return this.hasAssetReferrer;
      }

      public boolean hasAssetState()
      {
        return this.hasAssetState;
      }

      public boolean hasInstallTime()
      {
        return this.hasInstallTime;
      }

      public boolean hasPackageName()
      {
        return this.hasPackageName;
      }

      public boolean hasUninstallTime()
      {
        return this.hasUninstallTime;
      }

      public boolean hasVersionCode()
      {
        return this.hasVersionCode;
      }

      public AssetInstallState mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 26:
            setAssetId(paramCodedInputStreamMicro.readString());
            break;
          case 32:
            setAssetState(paramCodedInputStreamMicro.readInt32());
            break;
          case 40:
            setInstallTime(paramCodedInputStreamMicro.readInt64());
            break;
          case 48:
            setUninstallTime(paramCodedInputStreamMicro.readInt64());
            break;
          case 58:
            setPackageName(paramCodedInputStreamMicro.readString());
            break;
          case 64:
            setVersionCode(paramCodedInputStreamMicro.readInt32());
            break;
          case 74:
          }
          setAssetReferrer(paramCodedInputStreamMicro.readString());
        }
      }

      public AssetInstallState setAssetId(String paramString)
      {
        this.hasAssetId = true;
        this.assetId_ = paramString;
        return this;
      }

      public AssetInstallState setAssetReferrer(String paramString)
      {
        this.hasAssetReferrer = true;
        this.assetReferrer_ = paramString;
        return this;
      }

      public AssetInstallState setAssetState(int paramInt)
      {
        this.hasAssetState = true;
        this.assetState_ = paramInt;
        return this;
      }

      public AssetInstallState setInstallTime(long paramLong)
      {
        this.hasInstallTime = true;
        this.installTime_ = paramLong;
        return this;
      }

      public AssetInstallState setPackageName(String paramString)
      {
        this.hasPackageName = true;
        this.packageName_ = paramString;
        return this;
      }

      public AssetInstallState setUninstallTime(long paramLong)
      {
        this.hasUninstallTime = true;
        this.uninstallTime_ = paramLong;
        return this;
      }

      public AssetInstallState setVersionCode(int paramInt)
      {
        this.hasVersionCode = true;
        this.versionCode_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasAssetId())
          paramCodedOutputStreamMicro.writeString(3, getAssetId());
        if (hasAssetState())
          paramCodedOutputStreamMicro.writeInt32(4, getAssetState());
        if (hasInstallTime())
          paramCodedOutputStreamMicro.writeInt64(5, getInstallTime());
        if (hasUninstallTime())
          paramCodedOutputStreamMicro.writeInt64(6, getUninstallTime());
        if (hasPackageName())
          paramCodedOutputStreamMicro.writeString(7, getPackageName());
        if (hasVersionCode())
          paramCodedOutputStreamMicro.writeInt32(8, getVersionCode());
        if (hasAssetReferrer())
          paramCodedOutputStreamMicro.writeString(9, getAssetReferrer());
      }
    }

    public static final class SystemApp extends MessageMicro
    {
      private int cachedSize = -1;
      private List<String> certificateHash_ = Collections.emptyList();
      private boolean hasPackageName;
      private boolean hasVersionCode;
      private String packageName_ = "";
      private int versionCode_ = 0;

      public SystemApp addCertificateHash(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        if (this.certificateHash_.isEmpty())
          this.certificateHash_ = new ArrayList();
        this.certificateHash_.add(paramString);
        return this;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public List<String> getCertificateHashList()
      {
        return this.certificateHash_;
      }

      public String getPackageName()
      {
        return this.packageName_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasPackageName())
          i = 0 + CodedOutputStreamMicro.computeStringSize(11, getPackageName());
        if (hasVersionCode())
          i += CodedOutputStreamMicro.computeInt32Size(12, getVersionCode());
        int j = 0;
        Iterator localIterator = getCertificateHashList().iterator();
        while (localIterator.hasNext())
          j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
        int k = i + j + 1 * getCertificateHashList().size();
        this.cachedSize = k;
        return k;
      }

      public int getVersionCode()
      {
        return this.versionCode_;
      }

      public boolean hasPackageName()
      {
        return this.hasPackageName;
      }

      public boolean hasVersionCode()
      {
        return this.hasVersionCode;
      }

      public SystemApp mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 90:
            setPackageName(paramCodedInputStreamMicro.readString());
            break;
          case 96:
            setVersionCode(paramCodedInputStreamMicro.readInt32());
            break;
          case 106:
          }
          addCertificateHash(paramCodedInputStreamMicro.readString());
        }
      }

      public SystemApp setPackageName(String paramString)
      {
        this.hasPackageName = true;
        this.packageName_ = paramString;
        return this;
      }

      public SystemApp setVersionCode(int paramInt)
      {
        this.hasVersionCode = true;
        this.versionCode_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasPackageName())
          paramCodedOutputStreamMicro.writeString(11, getPackageName());
        if (hasVersionCode())
          paramCodedOutputStreamMicro.writeInt32(12, getVersionCode());
        Iterator localIterator = getCertificateHashList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeString(13, (String)localIterator.next());
      }
    }
  }

  public static final class ContentSyncResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasNumUpdatesAvailable;
    private int numUpdatesAvailable_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getNumUpdatesAvailable()
    {
      return this.numUpdatesAvailable_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasNumUpdatesAvailable())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getNumUpdatesAvailable());
      this.cachedSize = i;
      return i;
    }

    public boolean hasNumUpdatesAvailable()
    {
      return this.hasNumUpdatesAvailable;
    }

    public ContentSyncResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setNumUpdatesAvailable(paramCodedInputStreamMicro.readInt32());
      }
    }

    public ContentSyncResponseProto setNumUpdatesAvailable(int paramInt)
    {
      this.hasNumUpdatesAvailable = true;
      this.numUpdatesAvailable_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasNumUpdatesAvailable())
        paramCodedOutputStreamMicro.writeInt32(1, getNumUpdatesAvailable());
    }
  }

  public static final class DataMessageProto extends MessageMicro
  {
    private List<VendingProtos.AppDataProto> appData_ = Collections.emptyList();
    private int cachedSize = -1;
    private String category_ = "";
    private boolean hasCategory;

    public DataMessageProto addAppData(VendingProtos.AppDataProto paramAppDataProto)
    {
      if (paramAppDataProto == null)
        throw new NullPointerException();
      if (this.appData_.isEmpty())
        this.appData_ = new ArrayList();
      this.appData_.add(paramAppDataProto);
      return this;
    }

    public List<VendingProtos.AppDataProto> getAppDataList()
    {
      return this.appData_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCategory()
    {
      return this.category_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCategory())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getCategory());
      Iterator localIterator = getAppDataList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (VendingProtos.AppDataProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCategory()
    {
      return this.hasCategory;
    }

    public DataMessageProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCategory(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        VendingProtos.AppDataProto localAppDataProto = new VendingProtos.AppDataProto();
        paramCodedInputStreamMicro.readMessage(localAppDataProto);
        addAppData(localAppDataProto);
      }
    }

    public DataMessageProto setCategory(String paramString)
    {
      this.hasCategory = true;
      this.category_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCategory())
        paramCodedOutputStreamMicro.writeString(1, getCategory());
      Iterator localIterator = getAppDataList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (VendingProtos.AppDataProto)localIterator.next());
    }
  }

  public static final class DownloadInfoProto extends MessageMicro
  {
    private List<VendingProtos.FileMetadataProto> additionalFile_ = Collections.emptyList();
    private long apkSize_ = 0L;
    private int cachedSize = -1;
    private boolean hasApkSize;

    public DownloadInfoProto addAdditionalFile(VendingProtos.FileMetadataProto paramFileMetadataProto)
    {
      if (paramFileMetadataProto == null)
        throw new NullPointerException();
      if (this.additionalFile_.isEmpty())
        this.additionalFile_ = new ArrayList();
      this.additionalFile_.add(paramFileMetadataProto);
      return this;
    }

    public List<VendingProtos.FileMetadataProto> getAdditionalFileList()
    {
      return this.additionalFile_;
    }

    public long getApkSize()
    {
      return this.apkSize_;
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
      if (hasApkSize())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getApkSize());
      Iterator localIterator = getAdditionalFileList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(2, (VendingProtos.FileMetadataProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasApkSize()
    {
      return this.hasApkSize;
    }

    public DownloadInfoProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setApkSize(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
        }
        VendingProtos.FileMetadataProto localFileMetadataProto = new VendingProtos.FileMetadataProto();
        paramCodedInputStreamMicro.readMessage(localFileMetadataProto);
        addAdditionalFile(localFileMetadataProto);
      }
    }

    public DownloadInfoProto setApkSize(long paramLong)
    {
      this.hasApkSize = true;
      this.apkSize_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasApkSize())
        paramCodedOutputStreamMicro.writeInt64(1, getApkSize());
      Iterator localIterator = getAdditionalFileList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(2, (VendingProtos.FileMetadataProto)localIterator.next());
    }
  }

  public static final class ExternalAssetProto extends MessageMicro
  {
    private String actualSellerPrice_ = "";
    private List<VendingProtos.ExternalBadgeProto> appBadge_ = Collections.emptyList();
    private int assetType_ = 0;
    private String averageRating_ = "";
    private boolean bundledAsset_ = false;
    private int cachedSize = -1;
    private ExtendedInfo extendedInfo_ = null;
    private String filterReason_ = "";
    private boolean hasActualSellerPrice;
    private boolean hasAssetType;
    private boolean hasAverageRating;
    private boolean hasBundledAsset;
    private boolean hasExtendedInfo;
    private boolean hasFilterReason;
    private boolean hasId;
    private boolean hasNumRatings;
    private boolean hasOwner;
    private boolean hasOwnerId;
    private boolean hasPackageName;
    private boolean hasPrice;
    private boolean hasPriceCurrency;
    private boolean hasPriceMicros;
    private boolean hasPurchaseInformation;
    private boolean hasTitle;
    private boolean hasVersion;
    private boolean hasVersionCode;
    private String id_ = "";
    private long numRatings_ = 0L;
    private List<VendingProtos.ExternalBadgeProto> ownerBadge_ = Collections.emptyList();
    private String ownerId_ = "";
    private String owner_ = "";
    private String packageName_ = "";
    private String priceCurrency_ = "";
    private long priceMicros_ = 0L;
    private String price_ = "";
    private PurchaseInformation purchaseInformation_ = null;
    private String title_ = "";
    private int versionCode_ = 0;
    private String version_ = "";

    public ExternalAssetProto addAppBadge(VendingProtos.ExternalBadgeProto paramExternalBadgeProto)
    {
      if (paramExternalBadgeProto == null)
        throw new NullPointerException();
      if (this.appBadge_.isEmpty())
        this.appBadge_ = new ArrayList();
      this.appBadge_.add(paramExternalBadgeProto);
      return this;
    }

    public ExternalAssetProto addOwnerBadge(VendingProtos.ExternalBadgeProto paramExternalBadgeProto)
    {
      if (paramExternalBadgeProto == null)
        throw new NullPointerException();
      if (this.ownerBadge_.isEmpty())
        this.ownerBadge_ = new ArrayList();
      this.ownerBadge_.add(paramExternalBadgeProto);
      return this;
    }

    public String getActualSellerPrice()
    {
      return this.actualSellerPrice_;
    }

    public List<VendingProtos.ExternalBadgeProto> getAppBadgeList()
    {
      return this.appBadge_;
    }

    public int getAssetType()
    {
      return this.assetType_;
    }

    public String getAverageRating()
    {
      return this.averageRating_;
    }

    public boolean getBundledAsset()
    {
      return this.bundledAsset_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ExtendedInfo getExtendedInfo()
    {
      return this.extendedInfo_;
    }

    public String getFilterReason()
    {
      return this.filterReason_;
    }

    public String getId()
    {
      return this.id_;
    }

    public long getNumRatings()
    {
      return this.numRatings_;
    }

    public String getOwner()
    {
      return this.owner_;
    }

    public List<VendingProtos.ExternalBadgeProto> getOwnerBadgeList()
    {
      return this.ownerBadge_;
    }

    public String getOwnerId()
    {
      return this.ownerId_;
    }

    public String getPackageName()
    {
      return this.packageName_;
    }

    public String getPrice()
    {
      return this.price_;
    }

    public String getPriceCurrency()
    {
      return this.priceCurrency_;
    }

    public long getPriceMicros()
    {
      return this.priceMicros_;
    }

    public PurchaseInformation getPurchaseInformation()
    {
      return this.purchaseInformation_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getId());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getTitle());
      if (hasAssetType())
        i += CodedOutputStreamMicro.computeInt32Size(3, getAssetType());
      if (hasOwner())
        i += CodedOutputStreamMicro.computeStringSize(4, getOwner());
      if (hasVersion())
        i += CodedOutputStreamMicro.computeStringSize(5, getVersion());
      if (hasPrice())
        i += CodedOutputStreamMicro.computeStringSize(6, getPrice());
      if (hasAverageRating())
        i += CodedOutputStreamMicro.computeStringSize(7, getAverageRating());
      if (hasNumRatings())
        i += CodedOutputStreamMicro.computeInt64Size(8, getNumRatings());
      if (hasPurchaseInformation())
        i += CodedOutputStreamMicro.computeGroupSize(9, getPurchaseInformation());
      if (hasExtendedInfo())
        i += CodedOutputStreamMicro.computeGroupSize(12, getExtendedInfo());
      if (hasOwnerId())
        i += CodedOutputStreamMicro.computeStringSize(22, getOwnerId());
      if (hasPackageName())
        i += CodedOutputStreamMicro.computeStringSize(24, getPackageName());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(25, getVersionCode());
      if (hasBundledAsset())
        i += CodedOutputStreamMicro.computeBoolSize(29, getBundledAsset());
      if (hasPriceCurrency())
        i += CodedOutputStreamMicro.computeStringSize(32, getPriceCurrency());
      if (hasPriceMicros())
        i += CodedOutputStreamMicro.computeInt64Size(33, getPriceMicros());
      if (hasFilterReason())
        i += CodedOutputStreamMicro.computeStringSize(35, getFilterReason());
      if (hasActualSellerPrice())
        i += CodedOutputStreamMicro.computeStringSize(40, getActualSellerPrice());
      Iterator localIterator1 = getAppBadgeList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(47, (VendingProtos.ExternalBadgeProto)localIterator1.next());
      Iterator localIterator2 = getOwnerBadgeList().iterator();
      while (localIterator2.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(48, (VendingProtos.ExternalBadgeProto)localIterator2.next());
      this.cachedSize = i;
      return i;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public String getVersion()
    {
      return this.version_;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasActualSellerPrice()
    {
      return this.hasActualSellerPrice;
    }

    public boolean hasAssetType()
    {
      return this.hasAssetType;
    }

    public boolean hasAverageRating()
    {
      return this.hasAverageRating;
    }

    public boolean hasBundledAsset()
    {
      return this.hasBundledAsset;
    }

    public boolean hasExtendedInfo()
    {
      return this.hasExtendedInfo;
    }

    public boolean hasFilterReason()
    {
      return this.hasFilterReason;
    }

    public boolean hasId()
    {
      return this.hasId;
    }

    public boolean hasNumRatings()
    {
      return this.hasNumRatings;
    }

    public boolean hasOwner()
    {
      return this.hasOwner;
    }

    public boolean hasOwnerId()
    {
      return this.hasOwnerId;
    }

    public boolean hasPackageName()
    {
      return this.hasPackageName;
    }

    public boolean hasPrice()
    {
      return this.hasPrice;
    }

    public boolean hasPriceCurrency()
    {
      return this.hasPriceCurrency;
    }

    public boolean hasPriceMicros()
    {
      return this.hasPriceMicros;
    }

    public boolean hasPurchaseInformation()
    {
      return this.hasPurchaseInformation;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasVersion()
    {
      return this.hasVersion;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public ExternalAssetProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setAssetType(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
          setOwner(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setVersion(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setPrice(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setAverageRating(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setNumRatings(paramCodedInputStreamMicro.readInt64());
          break;
        case 75:
          PurchaseInformation localPurchaseInformation = new PurchaseInformation();
          paramCodedInputStreamMicro.readGroup(localPurchaseInformation, 9);
          setPurchaseInformation(localPurchaseInformation);
          break;
        case 99:
          ExtendedInfo localExtendedInfo = new ExtendedInfo();
          paramCodedInputStreamMicro.readGroup(localExtendedInfo, 12);
          setExtendedInfo(localExtendedInfo);
          break;
        case 178:
          setOwnerId(paramCodedInputStreamMicro.readString());
          break;
        case 194:
          setPackageName(paramCodedInputStreamMicro.readString());
          break;
        case 200:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 232:
          setBundledAsset(paramCodedInputStreamMicro.readBool());
          break;
        case 258:
          setPriceCurrency(paramCodedInputStreamMicro.readString());
          break;
        case 264:
          setPriceMicros(paramCodedInputStreamMicro.readInt64());
          break;
        case 282:
          setFilterReason(paramCodedInputStreamMicro.readString());
          break;
        case 322:
          setActualSellerPrice(paramCodedInputStreamMicro.readString());
          break;
        case 378:
          VendingProtos.ExternalBadgeProto localExternalBadgeProto2 = new VendingProtos.ExternalBadgeProto();
          paramCodedInputStreamMicro.readMessage(localExternalBadgeProto2);
          addAppBadge(localExternalBadgeProto2);
          break;
        case 386:
        }
        VendingProtos.ExternalBadgeProto localExternalBadgeProto1 = new VendingProtos.ExternalBadgeProto();
        paramCodedInputStreamMicro.readMessage(localExternalBadgeProto1);
        addOwnerBadge(localExternalBadgeProto1);
      }
    }

    public ExternalAssetProto setActualSellerPrice(String paramString)
    {
      this.hasActualSellerPrice = true;
      this.actualSellerPrice_ = paramString;
      return this;
    }

    public ExternalAssetProto setAssetType(int paramInt)
    {
      this.hasAssetType = true;
      this.assetType_ = paramInt;
      return this;
    }

    public ExternalAssetProto setAverageRating(String paramString)
    {
      this.hasAverageRating = true;
      this.averageRating_ = paramString;
      return this;
    }

    public ExternalAssetProto setBundledAsset(boolean paramBoolean)
    {
      this.hasBundledAsset = true;
      this.bundledAsset_ = paramBoolean;
      return this;
    }

    public ExternalAssetProto setExtendedInfo(ExtendedInfo paramExtendedInfo)
    {
      if (paramExtendedInfo == null)
        throw new NullPointerException();
      this.hasExtendedInfo = true;
      this.extendedInfo_ = paramExtendedInfo;
      return this;
    }

    public ExternalAssetProto setFilterReason(String paramString)
    {
      this.hasFilterReason = true;
      this.filterReason_ = paramString;
      return this;
    }

    public ExternalAssetProto setId(String paramString)
    {
      this.hasId = true;
      this.id_ = paramString;
      return this;
    }

    public ExternalAssetProto setNumRatings(long paramLong)
    {
      this.hasNumRatings = true;
      this.numRatings_ = paramLong;
      return this;
    }

    public ExternalAssetProto setOwner(String paramString)
    {
      this.hasOwner = true;
      this.owner_ = paramString;
      return this;
    }

    public ExternalAssetProto setOwnerId(String paramString)
    {
      this.hasOwnerId = true;
      this.ownerId_ = paramString;
      return this;
    }

    public ExternalAssetProto setPackageName(String paramString)
    {
      this.hasPackageName = true;
      this.packageName_ = paramString;
      return this;
    }

    public ExternalAssetProto setPrice(String paramString)
    {
      this.hasPrice = true;
      this.price_ = paramString;
      return this;
    }

    public ExternalAssetProto setPriceCurrency(String paramString)
    {
      this.hasPriceCurrency = true;
      this.priceCurrency_ = paramString;
      return this;
    }

    public ExternalAssetProto setPriceMicros(long paramLong)
    {
      this.hasPriceMicros = true;
      this.priceMicros_ = paramLong;
      return this;
    }

    public ExternalAssetProto setPurchaseInformation(PurchaseInformation paramPurchaseInformation)
    {
      if (paramPurchaseInformation == null)
        throw new NullPointerException();
      this.hasPurchaseInformation = true;
      this.purchaseInformation_ = paramPurchaseInformation;
      return this;
    }

    public ExternalAssetProto setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public ExternalAssetProto setVersion(String paramString)
    {
      this.hasVersion = true;
      this.version_ = paramString;
      return this;
    }

    public ExternalAssetProto setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasId())
        paramCodedOutputStreamMicro.writeString(1, getId());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(2, getTitle());
      if (hasAssetType())
        paramCodedOutputStreamMicro.writeInt32(3, getAssetType());
      if (hasOwner())
        paramCodedOutputStreamMicro.writeString(4, getOwner());
      if (hasVersion())
        paramCodedOutputStreamMicro.writeString(5, getVersion());
      if (hasPrice())
        paramCodedOutputStreamMicro.writeString(6, getPrice());
      if (hasAverageRating())
        paramCodedOutputStreamMicro.writeString(7, getAverageRating());
      if (hasNumRatings())
        paramCodedOutputStreamMicro.writeInt64(8, getNumRatings());
      if (hasPurchaseInformation())
        paramCodedOutputStreamMicro.writeGroup(9, getPurchaseInformation());
      if (hasExtendedInfo())
        paramCodedOutputStreamMicro.writeGroup(12, getExtendedInfo());
      if (hasOwnerId())
        paramCodedOutputStreamMicro.writeString(22, getOwnerId());
      if (hasPackageName())
        paramCodedOutputStreamMicro.writeString(24, getPackageName());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(25, getVersionCode());
      if (hasBundledAsset())
        paramCodedOutputStreamMicro.writeBool(29, getBundledAsset());
      if (hasPriceCurrency())
        paramCodedOutputStreamMicro.writeString(32, getPriceCurrency());
      if (hasPriceMicros())
        paramCodedOutputStreamMicro.writeInt64(33, getPriceMicros());
      if (hasFilterReason())
        paramCodedOutputStreamMicro.writeString(35, getFilterReason());
      if (hasActualSellerPrice())
        paramCodedOutputStreamMicro.writeString(40, getActualSellerPrice());
      Iterator localIterator1 = getAppBadgeList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(47, (VendingProtos.ExternalBadgeProto)localIterator1.next());
      Iterator localIterator2 = getOwnerBadgeList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeMessage(48, (VendingProtos.ExternalBadgeProto)localIterator2.next());
    }

    public static final class ExtendedInfo extends MessageMicro
    {
      private List<String> applicationPermissionId_ = Collections.emptyList();
      private int cachedSize = -1;
      private String category_ = "";
      private String contactEmail_ = "";
      private String contactPhone_ = "";
      private String contactWebsite_ = "";
      private int contentRatingLevel_ = 0;
      private String contentRatingString_ = "";
      private String description_ = "";
      private String downloadCountString_ = "";
      private long downloadCount_ = 0L;
      private VendingProtos.DownloadInfoProto downloadInfo_ = null;
      private boolean everInstalledByUser_ = false;
      private boolean forwardLocked_ = false;
      private boolean hasCategory;
      private boolean hasContactEmail;
      private boolean hasContactPhone;
      private boolean hasContactWebsite;
      private boolean hasContentRatingLevel;
      private boolean hasContentRatingString;
      private boolean hasDescription;
      private boolean hasDownloadCount;
      private boolean hasDownloadCountString;
      private boolean hasDownloadInfo;
      private boolean hasEverInstalledByUser;
      private boolean hasForwardLocked;
      private boolean hasNextPurchaseRefundable;
      private boolean hasNumScreenshots;
      private boolean hasPackageName;
      private boolean hasPromotionalDescription;
      private boolean hasRecentChanges;
      private boolean hasRequiredInstallationSize;
      private boolean hasServerAssetState;
      private boolean hasVideoLink;
      private boolean nextPurchaseRefundable_ = false;
      private int numScreenshots_ = 0;
      private List<PackageDependency> packageDependency_ = Collections.emptyList();
      private String packageName_ = "";
      private String promotionalDescription_ = "";
      private String recentChanges_ = "";
      private long requiredInstallationSize_ = 0L;
      private int serverAssetState_ = 1;
      private String videoLink_ = "";

      public ExtendedInfo addApplicationPermissionId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        if (this.applicationPermissionId_.isEmpty())
          this.applicationPermissionId_ = new ArrayList();
        this.applicationPermissionId_.add(paramString);
        return this;
      }

      public ExtendedInfo addPackageDependency(PackageDependency paramPackageDependency)
      {
        if (paramPackageDependency == null)
          throw new NullPointerException();
        if (this.packageDependency_.isEmpty())
          this.packageDependency_ = new ArrayList();
        this.packageDependency_.add(paramPackageDependency);
        return this;
      }

      public List<String> getApplicationPermissionIdList()
      {
        return this.applicationPermissionId_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getCategory()
      {
        return this.category_;
      }

      public String getContactEmail()
      {
        return this.contactEmail_;
      }

      public String getContactPhone()
      {
        return this.contactPhone_;
      }

      public String getContactWebsite()
      {
        return this.contactWebsite_;
      }

      public int getContentRatingLevel()
      {
        return this.contentRatingLevel_;
      }

      public String getContentRatingString()
      {
        return this.contentRatingString_;
      }

      public String getDescription()
      {
        return this.description_;
      }

      public long getDownloadCount()
      {
        return this.downloadCount_;
      }

      public String getDownloadCountString()
      {
        return this.downloadCountString_;
      }

      public VendingProtos.DownloadInfoProto getDownloadInfo()
      {
        return this.downloadInfo_;
      }

      public boolean getEverInstalledByUser()
      {
        return this.everInstalledByUser_;
      }

      public boolean getForwardLocked()
      {
        return this.forwardLocked_;
      }

      public boolean getNextPurchaseRefundable()
      {
        return this.nextPurchaseRefundable_;
      }

      public int getNumScreenshots()
      {
        return this.numScreenshots_;
      }

      public List<PackageDependency> getPackageDependencyList()
      {
        return this.packageDependency_;
      }

      public String getPackageName()
      {
        return this.packageName_;
      }

      public String getPromotionalDescription()
      {
        return this.promotionalDescription_;
      }

      public String getRecentChanges()
      {
        return this.recentChanges_;
      }

      public long getRequiredInstallationSize()
      {
        return this.requiredInstallationSize_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasDescription())
          i = 0 + CodedOutputStreamMicro.computeStringSize(13, getDescription());
        if (hasDownloadCount())
          i += CodedOutputStreamMicro.computeInt64Size(14, getDownloadCount());
        int j = 0;
        Iterator localIterator1 = getApplicationPermissionIdList().iterator();
        while (localIterator1.hasNext())
          j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
        int k = i + j + 1 * getApplicationPermissionIdList().size();
        if (hasRequiredInstallationSize())
          k += CodedOutputStreamMicro.computeInt64Size(16, getRequiredInstallationSize());
        if (hasPackageName())
          k += CodedOutputStreamMicro.computeStringSize(17, getPackageName());
        if (hasCategory())
          k += CodedOutputStreamMicro.computeStringSize(18, getCategory());
        if (hasForwardLocked())
          k += CodedOutputStreamMicro.computeBoolSize(19, getForwardLocked());
        if (hasContactEmail())
          k += CodedOutputStreamMicro.computeStringSize(20, getContactEmail());
        if (hasEverInstalledByUser())
          k += CodedOutputStreamMicro.computeBoolSize(21, getEverInstalledByUser());
        if (hasDownloadCountString())
          k += CodedOutputStreamMicro.computeStringSize(23, getDownloadCountString());
        if (hasContactPhone())
          k += CodedOutputStreamMicro.computeStringSize(26, getContactPhone());
        if (hasContactWebsite())
          k += CodedOutputStreamMicro.computeStringSize(27, getContactWebsite());
        if (hasNextPurchaseRefundable())
          k += CodedOutputStreamMicro.computeBoolSize(28, getNextPurchaseRefundable());
        if (hasNumScreenshots())
          k += CodedOutputStreamMicro.computeInt32Size(30, getNumScreenshots());
        if (hasPromotionalDescription())
          k += CodedOutputStreamMicro.computeStringSize(31, getPromotionalDescription());
        if (hasServerAssetState())
          k += CodedOutputStreamMicro.computeInt32Size(34, getServerAssetState());
        if (hasContentRatingLevel())
          k += CodedOutputStreamMicro.computeInt32Size(36, getContentRatingLevel());
        if (hasContentRatingString())
          k += CodedOutputStreamMicro.computeStringSize(37, getContentRatingString());
        if (hasRecentChanges())
          k += CodedOutputStreamMicro.computeStringSize(38, getRecentChanges());
        Iterator localIterator2 = getPackageDependencyList().iterator();
        while (localIterator2.hasNext())
          k += CodedOutputStreamMicro.computeGroupSize(39, (PackageDependency)localIterator2.next());
        if (hasVideoLink())
          k += CodedOutputStreamMicro.computeStringSize(43, getVideoLink());
        if (hasDownloadInfo())
          k += CodedOutputStreamMicro.computeMessageSize(49, getDownloadInfo());
        this.cachedSize = k;
        return k;
      }

      public int getServerAssetState()
      {
        return this.serverAssetState_;
      }

      public String getVideoLink()
      {
        return this.videoLink_;
      }

      public boolean hasCategory()
      {
        return this.hasCategory;
      }

      public boolean hasContactEmail()
      {
        return this.hasContactEmail;
      }

      public boolean hasContactPhone()
      {
        return this.hasContactPhone;
      }

      public boolean hasContactWebsite()
      {
        return this.hasContactWebsite;
      }

      public boolean hasContentRatingLevel()
      {
        return this.hasContentRatingLevel;
      }

      public boolean hasContentRatingString()
      {
        return this.hasContentRatingString;
      }

      public boolean hasDescription()
      {
        return this.hasDescription;
      }

      public boolean hasDownloadCount()
      {
        return this.hasDownloadCount;
      }

      public boolean hasDownloadCountString()
      {
        return this.hasDownloadCountString;
      }

      public boolean hasDownloadInfo()
      {
        return this.hasDownloadInfo;
      }

      public boolean hasEverInstalledByUser()
      {
        return this.hasEverInstalledByUser;
      }

      public boolean hasForwardLocked()
      {
        return this.hasForwardLocked;
      }

      public boolean hasNextPurchaseRefundable()
      {
        return this.hasNextPurchaseRefundable;
      }

      public boolean hasNumScreenshots()
      {
        return this.hasNumScreenshots;
      }

      public boolean hasPackageName()
      {
        return this.hasPackageName;
      }

      public boolean hasPromotionalDescription()
      {
        return this.hasPromotionalDescription;
      }

      public boolean hasRecentChanges()
      {
        return this.hasRecentChanges;
      }

      public boolean hasRequiredInstallationSize()
      {
        return this.hasRequiredInstallationSize;
      }

      public boolean hasServerAssetState()
      {
        return this.hasServerAssetState;
      }

      public boolean hasVideoLink()
      {
        return this.hasVideoLink;
      }

      public ExtendedInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 106:
            setDescription(paramCodedInputStreamMicro.readString());
            break;
          case 112:
            setDownloadCount(paramCodedInputStreamMicro.readInt64());
            break;
          case 122:
            addApplicationPermissionId(paramCodedInputStreamMicro.readString());
            break;
          case 128:
            setRequiredInstallationSize(paramCodedInputStreamMicro.readInt64());
            break;
          case 138:
            setPackageName(paramCodedInputStreamMicro.readString());
            break;
          case 146:
            setCategory(paramCodedInputStreamMicro.readString());
            break;
          case 152:
            setForwardLocked(paramCodedInputStreamMicro.readBool());
            break;
          case 162:
            setContactEmail(paramCodedInputStreamMicro.readString());
            break;
          case 168:
            setEverInstalledByUser(paramCodedInputStreamMicro.readBool());
            break;
          case 186:
            setDownloadCountString(paramCodedInputStreamMicro.readString());
            break;
          case 210:
            setContactPhone(paramCodedInputStreamMicro.readString());
            break;
          case 218:
            setContactWebsite(paramCodedInputStreamMicro.readString());
            break;
          case 224:
            setNextPurchaseRefundable(paramCodedInputStreamMicro.readBool());
            break;
          case 240:
            setNumScreenshots(paramCodedInputStreamMicro.readInt32());
            break;
          case 250:
            setPromotionalDescription(paramCodedInputStreamMicro.readString());
            break;
          case 272:
            setServerAssetState(paramCodedInputStreamMicro.readInt32());
            break;
          case 288:
            setContentRatingLevel(paramCodedInputStreamMicro.readInt32());
            break;
          case 298:
            setContentRatingString(paramCodedInputStreamMicro.readString());
            break;
          case 306:
            setRecentChanges(paramCodedInputStreamMicro.readString());
            break;
          case 315:
            PackageDependency localPackageDependency = new PackageDependency();
            paramCodedInputStreamMicro.readGroup(localPackageDependency, 39);
            addPackageDependency(localPackageDependency);
            break;
          case 346:
            setVideoLink(paramCodedInputStreamMicro.readString());
            break;
          case 394:
          }
          VendingProtos.DownloadInfoProto localDownloadInfoProto = new VendingProtos.DownloadInfoProto();
          paramCodedInputStreamMicro.readMessage(localDownloadInfoProto);
          setDownloadInfo(localDownloadInfoProto);
        }
      }

      public ExtendedInfo setCategory(String paramString)
      {
        this.hasCategory = true;
        this.category_ = paramString;
        return this;
      }

      public ExtendedInfo setContactEmail(String paramString)
      {
        this.hasContactEmail = true;
        this.contactEmail_ = paramString;
        return this;
      }

      public ExtendedInfo setContactPhone(String paramString)
      {
        this.hasContactPhone = true;
        this.contactPhone_ = paramString;
        return this;
      }

      public ExtendedInfo setContactWebsite(String paramString)
      {
        this.hasContactWebsite = true;
        this.contactWebsite_ = paramString;
        return this;
      }

      public ExtendedInfo setContentRatingLevel(int paramInt)
      {
        this.hasContentRatingLevel = true;
        this.contentRatingLevel_ = paramInt;
        return this;
      }

      public ExtendedInfo setContentRatingString(String paramString)
      {
        this.hasContentRatingString = true;
        this.contentRatingString_ = paramString;
        return this;
      }

      public ExtendedInfo setDescription(String paramString)
      {
        this.hasDescription = true;
        this.description_ = paramString;
        return this;
      }

      public ExtendedInfo setDownloadCount(long paramLong)
      {
        this.hasDownloadCount = true;
        this.downloadCount_ = paramLong;
        return this;
      }

      public ExtendedInfo setDownloadCountString(String paramString)
      {
        this.hasDownloadCountString = true;
        this.downloadCountString_ = paramString;
        return this;
      }

      public ExtendedInfo setDownloadInfo(VendingProtos.DownloadInfoProto paramDownloadInfoProto)
      {
        if (paramDownloadInfoProto == null)
          throw new NullPointerException();
        this.hasDownloadInfo = true;
        this.downloadInfo_ = paramDownloadInfoProto;
        return this;
      }

      public ExtendedInfo setEverInstalledByUser(boolean paramBoolean)
      {
        this.hasEverInstalledByUser = true;
        this.everInstalledByUser_ = paramBoolean;
        return this;
      }

      public ExtendedInfo setForwardLocked(boolean paramBoolean)
      {
        this.hasForwardLocked = true;
        this.forwardLocked_ = paramBoolean;
        return this;
      }

      public ExtendedInfo setNextPurchaseRefundable(boolean paramBoolean)
      {
        this.hasNextPurchaseRefundable = true;
        this.nextPurchaseRefundable_ = paramBoolean;
        return this;
      }

      public ExtendedInfo setNumScreenshots(int paramInt)
      {
        this.hasNumScreenshots = true;
        this.numScreenshots_ = paramInt;
        return this;
      }

      public ExtendedInfo setPackageName(String paramString)
      {
        this.hasPackageName = true;
        this.packageName_ = paramString;
        return this;
      }

      public ExtendedInfo setPromotionalDescription(String paramString)
      {
        this.hasPromotionalDescription = true;
        this.promotionalDescription_ = paramString;
        return this;
      }

      public ExtendedInfo setRecentChanges(String paramString)
      {
        this.hasRecentChanges = true;
        this.recentChanges_ = paramString;
        return this;
      }

      public ExtendedInfo setRequiredInstallationSize(long paramLong)
      {
        this.hasRequiredInstallationSize = true;
        this.requiredInstallationSize_ = paramLong;
        return this;
      }

      public ExtendedInfo setServerAssetState(int paramInt)
      {
        this.hasServerAssetState = true;
        this.serverAssetState_ = paramInt;
        return this;
      }

      public ExtendedInfo setVideoLink(String paramString)
      {
        this.hasVideoLink = true;
        this.videoLink_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasDescription())
          paramCodedOutputStreamMicro.writeString(13, getDescription());
        if (hasDownloadCount())
          paramCodedOutputStreamMicro.writeInt64(14, getDownloadCount());
        Iterator localIterator1 = getApplicationPermissionIdList().iterator();
        while (localIterator1.hasNext())
          paramCodedOutputStreamMicro.writeString(15, (String)localIterator1.next());
        if (hasRequiredInstallationSize())
          paramCodedOutputStreamMicro.writeInt64(16, getRequiredInstallationSize());
        if (hasPackageName())
          paramCodedOutputStreamMicro.writeString(17, getPackageName());
        if (hasCategory())
          paramCodedOutputStreamMicro.writeString(18, getCategory());
        if (hasForwardLocked())
          paramCodedOutputStreamMicro.writeBool(19, getForwardLocked());
        if (hasContactEmail())
          paramCodedOutputStreamMicro.writeString(20, getContactEmail());
        if (hasEverInstalledByUser())
          paramCodedOutputStreamMicro.writeBool(21, getEverInstalledByUser());
        if (hasDownloadCountString())
          paramCodedOutputStreamMicro.writeString(23, getDownloadCountString());
        if (hasContactPhone())
          paramCodedOutputStreamMicro.writeString(26, getContactPhone());
        if (hasContactWebsite())
          paramCodedOutputStreamMicro.writeString(27, getContactWebsite());
        if (hasNextPurchaseRefundable())
          paramCodedOutputStreamMicro.writeBool(28, getNextPurchaseRefundable());
        if (hasNumScreenshots())
          paramCodedOutputStreamMicro.writeInt32(30, getNumScreenshots());
        if (hasPromotionalDescription())
          paramCodedOutputStreamMicro.writeString(31, getPromotionalDescription());
        if (hasServerAssetState())
          paramCodedOutputStreamMicro.writeInt32(34, getServerAssetState());
        if (hasContentRatingLevel())
          paramCodedOutputStreamMicro.writeInt32(36, getContentRatingLevel());
        if (hasContentRatingString())
          paramCodedOutputStreamMicro.writeString(37, getContentRatingString());
        if (hasRecentChanges())
          paramCodedOutputStreamMicro.writeString(38, getRecentChanges());
        Iterator localIterator2 = getPackageDependencyList().iterator();
        while (localIterator2.hasNext())
          paramCodedOutputStreamMicro.writeGroup(39, (PackageDependency)localIterator2.next());
        if (hasVideoLink())
          paramCodedOutputStreamMicro.writeString(43, getVideoLink());
        if (hasDownloadInfo())
          paramCodedOutputStreamMicro.writeMessage(49, getDownloadInfo());
      }

      public static final class PackageDependency extends MessageMicro
      {
        private int cachedSize = -1;
        private boolean hasPackageName;
        private boolean hasSkipPermissions;
        private String packageName_ = "";
        private boolean skipPermissions_ = false;

        public int getCachedSize()
        {
          if (this.cachedSize < 0)
            getSerializedSize();
          return this.cachedSize;
        }

        public String getPackageName()
        {
          return this.packageName_;
        }

        public int getSerializedSize()
        {
          int i = 0;
          if (hasPackageName())
            i = 0 + CodedOutputStreamMicro.computeStringSize(41, getPackageName());
          if (hasSkipPermissions())
            i += CodedOutputStreamMicro.computeBoolSize(42, getSkipPermissions());
          this.cachedSize = i;
          return i;
        }

        public boolean getSkipPermissions()
        {
          return this.skipPermissions_;
        }

        public boolean hasPackageName()
        {
          return this.hasPackageName;
        }

        public boolean hasSkipPermissions()
        {
          return this.hasSkipPermissions;
        }

        public PackageDependency mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            case 330:
              setPackageName(paramCodedInputStreamMicro.readString());
              break;
            case 336:
            }
            setSkipPermissions(paramCodedInputStreamMicro.readBool());
          }
        }

        public PackageDependency setPackageName(String paramString)
        {
          this.hasPackageName = true;
          this.packageName_ = paramString;
          return this;
        }

        public PackageDependency setSkipPermissions(boolean paramBoolean)
        {
          this.hasSkipPermissions = true;
          this.skipPermissions_ = paramBoolean;
          return this;
        }

        public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
          throws IOException
        {
          if (hasPackageName())
            paramCodedOutputStreamMicro.writeString(41, getPackageName());
          if (hasSkipPermissions())
            paramCodedOutputStreamMicro.writeBool(42, getSkipPermissions());
        }
      }
    }

    public static final class PurchaseInformation extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasPurchaseTime;
      private boolean hasRefundStartPolicy;
      private boolean hasRefundTimeoutTime;
      private boolean hasRefundWindowDuration;
      private long purchaseTime_ = 0L;
      private int refundStartPolicy_ = 1;
      private long refundTimeoutTime_ = 0L;
      private long refundWindowDuration_ = 0L;

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public long getPurchaseTime()
      {
        return this.purchaseTime_;
      }

      public int getRefundStartPolicy()
      {
        return this.refundStartPolicy_;
      }

      public long getRefundTimeoutTime()
      {
        return this.refundTimeoutTime_;
      }

      public long getRefundWindowDuration()
      {
        return this.refundWindowDuration_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasPurchaseTime())
          i = 0 + CodedOutputStreamMicro.computeInt64Size(10, getPurchaseTime());
        if (hasRefundTimeoutTime())
          i += CodedOutputStreamMicro.computeInt64Size(11, getRefundTimeoutTime());
        if (hasRefundStartPolicy())
          i += CodedOutputStreamMicro.computeInt32Size(45, getRefundStartPolicy());
        if (hasRefundWindowDuration())
          i += CodedOutputStreamMicro.computeInt64Size(46, getRefundWindowDuration());
        this.cachedSize = i;
        return i;
      }

      public boolean hasPurchaseTime()
      {
        return this.hasPurchaseTime;
      }

      public boolean hasRefundStartPolicy()
      {
        return this.hasRefundStartPolicy;
      }

      public boolean hasRefundTimeoutTime()
      {
        return this.hasRefundTimeoutTime;
      }

      public boolean hasRefundWindowDuration()
      {
        return this.hasRefundWindowDuration;
      }

      public PurchaseInformation mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 80:
            setPurchaseTime(paramCodedInputStreamMicro.readInt64());
            break;
          case 88:
            setRefundTimeoutTime(paramCodedInputStreamMicro.readInt64());
            break;
          case 360:
            setRefundStartPolicy(paramCodedInputStreamMicro.readInt32());
            break;
          case 368:
          }
          setRefundWindowDuration(paramCodedInputStreamMicro.readInt64());
        }
      }

      public PurchaseInformation setPurchaseTime(long paramLong)
      {
        this.hasPurchaseTime = true;
        this.purchaseTime_ = paramLong;
        return this;
      }

      public PurchaseInformation setRefundStartPolicy(int paramInt)
      {
        this.hasRefundStartPolicy = true;
        this.refundStartPolicy_ = paramInt;
        return this;
      }

      public PurchaseInformation setRefundTimeoutTime(long paramLong)
      {
        this.hasRefundTimeoutTime = true;
        this.refundTimeoutTime_ = paramLong;
        return this;
      }

      public PurchaseInformation setRefundWindowDuration(long paramLong)
      {
        this.hasRefundWindowDuration = true;
        this.refundWindowDuration_ = paramLong;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasPurchaseTime())
          paramCodedOutputStreamMicro.writeInt64(10, getPurchaseTime());
        if (hasRefundTimeoutTime())
          paramCodedOutputStreamMicro.writeInt64(11, getRefundTimeoutTime());
        if (hasRefundStartPolicy())
          paramCodedOutputStreamMicro.writeInt32(45, getRefundStartPolicy());
        if (hasRefundWindowDuration())
          paramCodedOutputStreamMicro.writeInt64(46, getRefundWindowDuration());
      }
    }
  }

  public static final class ExternalBadgeImageProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUrl;
    private boolean hasUsage;
    private String url_ = "";
    private int usage_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUsage())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getUsage());
      if (hasUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getUrl());
      this.cachedSize = i;
      return i;
    }

    public String getUrl()
    {
      return this.url_;
    }

    public int getUsage()
    {
      return this.usage_;
    }

    public boolean hasUrl()
    {
      return this.hasUrl;
    }

    public boolean hasUsage()
    {
      return this.hasUsage;
    }

    public ExternalBadgeImageProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUsage(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public ExternalBadgeImageProto setUrl(String paramString)
    {
      this.hasUrl = true;
      this.url_ = paramString;
      return this;
    }

    public ExternalBadgeImageProto setUsage(int paramInt)
    {
      this.hasUsage = true;
      this.usage_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUsage())
        paramCodedOutputStreamMicro.writeInt32(1, getUsage());
      if (hasUrl())
        paramCodedOutputStreamMicro.writeString(2, getUrl());
    }
  }

  public static final class ExternalBadgeProto extends MessageMicro
  {
    private List<VendingProtos.ExternalBadgeImageProto> badgeImage_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean hasLocalizedDescription;
    private boolean hasLocalizedTitle;
    private boolean hasSearchId;
    private String localizedDescription_ = "";
    private String localizedTitle_ = "";
    private String searchId_ = "";

    public ExternalBadgeProto addBadgeImage(VendingProtos.ExternalBadgeImageProto paramExternalBadgeImageProto)
    {
      if (paramExternalBadgeImageProto == null)
        throw new NullPointerException();
      if (this.badgeImage_.isEmpty())
        this.badgeImage_ = new ArrayList();
      this.badgeImage_.add(paramExternalBadgeImageProto);
      return this;
    }

    public List<VendingProtos.ExternalBadgeImageProto> getBadgeImageList()
    {
      return this.badgeImage_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getLocalizedDescription()
    {
      return this.localizedDescription_;
    }

    public String getLocalizedTitle()
    {
      return this.localizedTitle_;
    }

    public String getSearchId()
    {
      return this.searchId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLocalizedTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getLocalizedTitle());
      if (hasLocalizedDescription())
        i += CodedOutputStreamMicro.computeStringSize(2, getLocalizedDescription());
      Iterator localIterator = getBadgeImageList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (VendingProtos.ExternalBadgeImageProto)localIterator.next());
      if (hasSearchId())
        i += CodedOutputStreamMicro.computeStringSize(4, getSearchId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasLocalizedDescription()
    {
      return this.hasLocalizedDescription;
    }

    public boolean hasLocalizedTitle()
    {
      return this.hasLocalizedTitle;
    }

    public boolean hasSearchId()
    {
      return this.hasSearchId;
    }

    public ExternalBadgeProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setLocalizedTitle(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setLocalizedDescription(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          VendingProtos.ExternalBadgeImageProto localExternalBadgeImageProto = new VendingProtos.ExternalBadgeImageProto();
          paramCodedInputStreamMicro.readMessage(localExternalBadgeImageProto);
          addBadgeImage(localExternalBadgeImageProto);
          break;
        case 34:
        }
        setSearchId(paramCodedInputStreamMicro.readString());
      }
    }

    public ExternalBadgeProto setLocalizedDescription(String paramString)
    {
      this.hasLocalizedDescription = true;
      this.localizedDescription_ = paramString;
      return this;
    }

    public ExternalBadgeProto setLocalizedTitle(String paramString)
    {
      this.hasLocalizedTitle = true;
      this.localizedTitle_ = paramString;
      return this;
    }

    public ExternalBadgeProto setSearchId(String paramString)
    {
      this.hasSearchId = true;
      this.searchId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLocalizedTitle())
        paramCodedOutputStreamMicro.writeString(1, getLocalizedTitle());
      if (hasLocalizedDescription())
        paramCodedOutputStreamMicro.writeString(2, getLocalizedDescription());
      Iterator localIterator = getBadgeImageList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (VendingProtos.ExternalBadgeImageProto)localIterator.next());
      if (hasSearchId())
        paramCodedOutputStreamMicro.writeString(4, getSearchId());
    }
  }

  public static final class ExternalCarrierBillingInstrumentProto extends MessageMicro
  {
    private String accountType_ = "";
    private String address1_ = "";
    private String address2_ = "";
    private int cachedSize = -1;
    private String city_ = "";
    private String country_ = "";
    private EncryptedSubscriberInfo encryptedSubscriberInfo_ = null;
    private boolean hasAccountType;
    private boolean hasAddress1;
    private boolean hasAddress2;
    private boolean hasCity;
    private boolean hasCountry;
    private boolean hasEncryptedSubscriberInfo;
    private boolean hasInstrumentKey;
    private boolean hasPostalCode;
    private boolean hasState;
    private boolean hasSubscriberCurrency;
    private boolean hasSubscriberIdentifier;
    private boolean hasSubscriberName;
    private boolean hasTransactionLimit;
    private String instrumentKey_ = "";
    private String postalCode_ = "";
    private String state_ = "";
    private String subscriberCurrency_ = "";
    private String subscriberIdentifier_ = "";
    private String subscriberName_ = "";
    private long transactionLimit_ = 0L;

    public String getAccountType()
    {
      return this.accountType_;
    }

    public String getAddress1()
    {
      return this.address1_;
    }

    public String getAddress2()
    {
      return this.address2_;
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

    public String getCountry()
    {
      return this.country_;
    }

    public EncryptedSubscriberInfo getEncryptedSubscriberInfo()
    {
      return this.encryptedSubscriberInfo_;
    }

    public String getInstrumentKey()
    {
      return this.instrumentKey_;
    }

    public String getPostalCode()
    {
      return this.postalCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrumentKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getInstrumentKey());
      if (hasSubscriberIdentifier())
        i += CodedOutputStreamMicro.computeStringSize(2, getSubscriberIdentifier());
      if (hasAccountType())
        i += CodedOutputStreamMicro.computeStringSize(3, getAccountType());
      if (hasSubscriberCurrency())
        i += CodedOutputStreamMicro.computeStringSize(4, getSubscriberCurrency());
      if (hasTransactionLimit())
        i += CodedOutputStreamMicro.computeUInt64Size(5, getTransactionLimit());
      if (hasSubscriberName())
        i += CodedOutputStreamMicro.computeStringSize(6, getSubscriberName());
      if (hasAddress1())
        i += CodedOutputStreamMicro.computeStringSize(7, getAddress1());
      if (hasAddress2())
        i += CodedOutputStreamMicro.computeStringSize(8, getAddress2());
      if (hasCity())
        i += CodedOutputStreamMicro.computeStringSize(9, getCity());
      if (hasState())
        i += CodedOutputStreamMicro.computeStringSize(10, getState());
      if (hasPostalCode())
        i += CodedOutputStreamMicro.computeStringSize(11, getPostalCode());
      if (hasCountry())
        i += CodedOutputStreamMicro.computeStringSize(12, getCountry());
      if (hasEncryptedSubscriberInfo())
        i += CodedOutputStreamMicro.computeMessageSize(13, getEncryptedSubscriberInfo());
      this.cachedSize = i;
      return i;
    }

    public String getState()
    {
      return this.state_;
    }

    public String getSubscriberCurrency()
    {
      return this.subscriberCurrency_;
    }

    public String getSubscriberIdentifier()
    {
      return this.subscriberIdentifier_;
    }

    public String getSubscriberName()
    {
      return this.subscriberName_;
    }

    public long getTransactionLimit()
    {
      return this.transactionLimit_;
    }

    public boolean hasAccountType()
    {
      return this.hasAccountType;
    }

    public boolean hasAddress1()
    {
      return this.hasAddress1;
    }

    public boolean hasAddress2()
    {
      return this.hasAddress2;
    }

    public boolean hasCity()
    {
      return this.hasCity;
    }

    public boolean hasCountry()
    {
      return this.hasCountry;
    }

    public boolean hasEncryptedSubscriberInfo()
    {
      return this.hasEncryptedSubscriberInfo;
    }

    public boolean hasInstrumentKey()
    {
      return this.hasInstrumentKey;
    }

    public boolean hasPostalCode()
    {
      return this.hasPostalCode;
    }

    public boolean hasState()
    {
      return this.hasState;
    }

    public boolean hasSubscriberCurrency()
    {
      return this.hasSubscriberCurrency;
    }

    public boolean hasSubscriberIdentifier()
    {
      return this.hasSubscriberIdentifier;
    }

    public boolean hasSubscriberName()
    {
      return this.hasSubscriberName;
    }

    public boolean hasTransactionLimit()
    {
      return this.hasTransactionLimit;
    }

    public ExternalCarrierBillingInstrumentProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSubscriberIdentifier(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setAccountType(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setSubscriberCurrency(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setTransactionLimit(paramCodedInputStreamMicro.readUInt64());
          break;
        case 50:
          setSubscriberName(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setAddress1(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setAddress2(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setCity(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          setState(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setPostalCode(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setCountry(paramCodedInputStreamMicro.readString());
          break;
        case 106:
        }
        EncryptedSubscriberInfo localEncryptedSubscriberInfo = new EncryptedSubscriberInfo();
        paramCodedInputStreamMicro.readMessage(localEncryptedSubscriberInfo);
        setEncryptedSubscriberInfo(localEncryptedSubscriberInfo);
      }
    }

    public ExternalCarrierBillingInstrumentProto setAccountType(String paramString)
    {
      this.hasAccountType = true;
      this.accountType_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setAddress1(String paramString)
    {
      this.hasAddress1 = true;
      this.address1_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setAddress2(String paramString)
    {
      this.hasAddress2 = true;
      this.address2_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setCity(String paramString)
    {
      this.hasCity = true;
      this.city_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setCountry(String paramString)
    {
      this.hasCountry = true;
      this.country_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setEncryptedSubscriberInfo(EncryptedSubscriberInfo paramEncryptedSubscriberInfo)
    {
      if (paramEncryptedSubscriberInfo == null)
        throw new NullPointerException();
      this.hasEncryptedSubscriberInfo = true;
      this.encryptedSubscriberInfo_ = paramEncryptedSubscriberInfo;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setInstrumentKey(String paramString)
    {
      this.hasInstrumentKey = true;
      this.instrumentKey_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setPostalCode(String paramString)
    {
      this.hasPostalCode = true;
      this.postalCode_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setState(String paramString)
    {
      this.hasState = true;
      this.state_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setSubscriberCurrency(String paramString)
    {
      this.hasSubscriberCurrency = true;
      this.subscriberCurrency_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setSubscriberIdentifier(String paramString)
    {
      this.hasSubscriberIdentifier = true;
      this.subscriberIdentifier_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setSubscriberName(String paramString)
    {
      this.hasSubscriberName = true;
      this.subscriberName_ = paramString;
      return this;
    }

    public ExternalCarrierBillingInstrumentProto setTransactionLimit(long paramLong)
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
      if (hasSubscriberIdentifier())
        paramCodedOutputStreamMicro.writeString(2, getSubscriberIdentifier());
      if (hasAccountType())
        paramCodedOutputStreamMicro.writeString(3, getAccountType());
      if (hasSubscriberCurrency())
        paramCodedOutputStreamMicro.writeString(4, getSubscriberCurrency());
      if (hasTransactionLimit())
        paramCodedOutputStreamMicro.writeUInt64(5, getTransactionLimit());
      if (hasSubscriberName())
        paramCodedOutputStreamMicro.writeString(6, getSubscriberName());
      if (hasAddress1())
        paramCodedOutputStreamMicro.writeString(7, getAddress1());
      if (hasAddress2())
        paramCodedOutputStreamMicro.writeString(8, getAddress2());
      if (hasCity())
        paramCodedOutputStreamMicro.writeString(9, getCity());
      if (hasState())
        paramCodedOutputStreamMicro.writeString(10, getState());
      if (hasPostalCode())
        paramCodedOutputStreamMicro.writeString(11, getPostalCode());
      if (hasCountry())
        paramCodedOutputStreamMicro.writeString(12, getCountry());
      if (hasEncryptedSubscriberInfo())
        paramCodedOutputStreamMicro.writeMessage(13, getEncryptedSubscriberInfo());
    }
  }

  public static final class ExternalCommentProto extends MessageMicro
  {
    private String body_ = "";
    private int cachedSize = -1;
    private long creationTime_ = 0L;
    private String creatorId_ = "";
    private String creatorName_ = "";
    private boolean hasBody;
    private boolean hasCreationTime;
    private boolean hasCreatorId;
    private boolean hasCreatorName;
    private boolean hasRating;
    private int rating_ = 0;

    public String getBody()
    {
      return this.body_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getCreationTime()
    {
      return this.creationTime_;
    }

    public String getCreatorId()
    {
      return this.creatorId_;
    }

    public String getCreatorName()
    {
      return this.creatorName_;
    }

    public int getRating()
    {
      return this.rating_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBody())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getBody());
      if (hasRating())
        i += CodedOutputStreamMicro.computeInt32Size(2, getRating());
      if (hasCreatorName())
        i += CodedOutputStreamMicro.computeStringSize(3, getCreatorName());
      if (hasCreationTime())
        i += CodedOutputStreamMicro.computeInt64Size(4, getCreationTime());
      if (hasCreatorId())
        i += CodedOutputStreamMicro.computeStringSize(5, getCreatorId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBody()
    {
      return this.hasBody;
    }

    public boolean hasCreationTime()
    {
      return this.hasCreationTime;
    }

    public boolean hasCreatorId()
    {
      return this.hasCreatorId;
    }

    public boolean hasCreatorName()
    {
      return this.hasCreatorName;
    }

    public boolean hasRating()
    {
      return this.hasRating;
    }

    public ExternalCommentProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBody(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setRating(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setCreatorName(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setCreationTime(paramCodedInputStreamMicro.readInt64());
          break;
        case 42:
        }
        setCreatorId(paramCodedInputStreamMicro.readString());
      }
    }

    public ExternalCommentProto setBody(String paramString)
    {
      this.hasBody = true;
      this.body_ = paramString;
      return this;
    }

    public ExternalCommentProto setCreationTime(long paramLong)
    {
      this.hasCreationTime = true;
      this.creationTime_ = paramLong;
      return this;
    }

    public ExternalCommentProto setCreatorId(String paramString)
    {
      this.hasCreatorId = true;
      this.creatorId_ = paramString;
      return this;
    }

    public ExternalCommentProto setCreatorName(String paramString)
    {
      this.hasCreatorName = true;
      this.creatorName_ = paramString;
      return this;
    }

    public ExternalCommentProto setRating(int paramInt)
    {
      this.hasRating = true;
      this.rating_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBody())
        paramCodedOutputStreamMicro.writeString(1, getBody());
      if (hasRating())
        paramCodedOutputStreamMicro.writeInt32(2, getRating());
      if (hasCreatorName())
        paramCodedOutputStreamMicro.writeString(3, getCreatorName());
      if (hasCreationTime())
        paramCodedOutputStreamMicro.writeInt64(4, getCreationTime());
      if (hasCreatorId())
        paramCodedOutputStreamMicro.writeString(5, getCreatorId());
    }
  }

  public static final class ExternalCreditCard extends MessageMicro
  {
    private String address1_ = "";
    private String address2_ = "";
    private int cachedSize = -1;
    private String city_ = "";
    private String countryCode_ = "";
    private int expMonth_ = 0;
    private int expYear_ = 0;
    private boolean hasAddress1;
    private boolean hasAddress2;
    private boolean hasCity;
    private boolean hasCountryCode;
    private boolean hasExpMonth;
    private boolean hasExpYear;
    private boolean hasLastDigits;
    private boolean hasMakeDefault;
    private boolean hasPersonName;
    private boolean hasPhone;
    private boolean hasPostalCode;
    private boolean hasState;
    private boolean hasType;
    private String lastDigits_ = "";
    private boolean makeDefault_ = false;
    private String personName_ = "";
    private String phone_ = "";
    private String postalCode_ = "";
    private String state_ = "";
    private String type_ = "";

    public String getAddress1()
    {
      return this.address1_;
    }

    public String getAddress2()
    {
      return this.address2_;
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

    public String getCountryCode()
    {
      return this.countryCode_;
    }

    public int getExpMonth()
    {
      return this.expMonth_;
    }

    public int getExpYear()
    {
      return this.expYear_;
    }

    public String getLastDigits()
    {
      return this.lastDigits_;
    }

    public boolean getMakeDefault()
    {
      return this.makeDefault_;
    }

    public String getPersonName()
    {
      return this.personName_;
    }

    public String getPhone()
    {
      return this.phone_;
    }

    public String getPostalCode()
    {
      return this.postalCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasType())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getType());
      if (hasLastDigits())
        i += CodedOutputStreamMicro.computeStringSize(2, getLastDigits());
      if (hasExpYear())
        i += CodedOutputStreamMicro.computeInt32Size(3, getExpYear());
      if (hasExpMonth())
        i += CodedOutputStreamMicro.computeInt32Size(4, getExpMonth());
      if (hasPersonName())
        i += CodedOutputStreamMicro.computeStringSize(5, getPersonName());
      if (hasCountryCode())
        i += CodedOutputStreamMicro.computeStringSize(6, getCountryCode());
      if (hasPostalCode())
        i += CodedOutputStreamMicro.computeStringSize(7, getPostalCode());
      if (hasMakeDefault())
        i += CodedOutputStreamMicro.computeBoolSize(8, getMakeDefault());
      if (hasAddress1())
        i += CodedOutputStreamMicro.computeStringSize(9, getAddress1());
      if (hasAddress2())
        i += CodedOutputStreamMicro.computeStringSize(10, getAddress2());
      if (hasCity())
        i += CodedOutputStreamMicro.computeStringSize(11, getCity());
      if (hasState())
        i += CodedOutputStreamMicro.computeStringSize(12, getState());
      if (hasPhone())
        i += CodedOutputStreamMicro.computeStringSize(13, getPhone());
      this.cachedSize = i;
      return i;
    }

    public String getState()
    {
      return this.state_;
    }

    public String getType()
    {
      return this.type_;
    }

    public boolean hasAddress1()
    {
      return this.hasAddress1;
    }

    public boolean hasAddress2()
    {
      return this.hasAddress2;
    }

    public boolean hasCity()
    {
      return this.hasCity;
    }

    public boolean hasCountryCode()
    {
      return this.hasCountryCode;
    }

    public boolean hasExpMonth()
    {
      return this.hasExpMonth;
    }

    public boolean hasExpYear()
    {
      return this.hasExpYear;
    }

    public boolean hasLastDigits()
    {
      return this.hasLastDigits;
    }

    public boolean hasMakeDefault()
    {
      return this.hasMakeDefault;
    }

    public boolean hasPersonName()
    {
      return this.hasPersonName;
    }

    public boolean hasPhone()
    {
      return this.hasPhone;
    }

    public boolean hasPostalCode()
    {
      return this.hasPostalCode;
    }

    public boolean hasState()
    {
      return this.hasState;
    }

    public boolean hasType()
    {
      return this.hasType;
    }

    public ExternalCreditCard mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setType(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setLastDigits(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setExpYear(paramCodedInputStreamMicro.readInt32());
          break;
        case 32:
          setExpMonth(paramCodedInputStreamMicro.readInt32());
          break;
        case 42:
          setPersonName(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setCountryCode(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setPostalCode(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setMakeDefault(paramCodedInputStreamMicro.readBool());
          break;
        case 74:
          setAddress1(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          setAddress2(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setCity(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setState(paramCodedInputStreamMicro.readString());
          break;
        case 106:
        }
        setPhone(paramCodedInputStreamMicro.readString());
      }
    }

    public ExternalCreditCard setAddress1(String paramString)
    {
      this.hasAddress1 = true;
      this.address1_ = paramString;
      return this;
    }

    public ExternalCreditCard setAddress2(String paramString)
    {
      this.hasAddress2 = true;
      this.address2_ = paramString;
      return this;
    }

    public ExternalCreditCard setCity(String paramString)
    {
      this.hasCity = true;
      this.city_ = paramString;
      return this;
    }

    public ExternalCreditCard setCountryCode(String paramString)
    {
      this.hasCountryCode = true;
      this.countryCode_ = paramString;
      return this;
    }

    public ExternalCreditCard setExpMonth(int paramInt)
    {
      this.hasExpMonth = true;
      this.expMonth_ = paramInt;
      return this;
    }

    public ExternalCreditCard setExpYear(int paramInt)
    {
      this.hasExpYear = true;
      this.expYear_ = paramInt;
      return this;
    }

    public ExternalCreditCard setLastDigits(String paramString)
    {
      this.hasLastDigits = true;
      this.lastDigits_ = paramString;
      return this;
    }

    public ExternalCreditCard setMakeDefault(boolean paramBoolean)
    {
      this.hasMakeDefault = true;
      this.makeDefault_ = paramBoolean;
      return this;
    }

    public ExternalCreditCard setPersonName(String paramString)
    {
      this.hasPersonName = true;
      this.personName_ = paramString;
      return this;
    }

    public ExternalCreditCard setPhone(String paramString)
    {
      this.hasPhone = true;
      this.phone_ = paramString;
      return this;
    }

    public ExternalCreditCard setPostalCode(String paramString)
    {
      this.hasPostalCode = true;
      this.postalCode_ = paramString;
      return this;
    }

    public ExternalCreditCard setState(String paramString)
    {
      this.hasState = true;
      this.state_ = paramString;
      return this;
    }

    public ExternalCreditCard setType(String paramString)
    {
      this.hasType = true;
      this.type_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasType())
        paramCodedOutputStreamMicro.writeString(1, getType());
      if (hasLastDigits())
        paramCodedOutputStreamMicro.writeString(2, getLastDigits());
      if (hasExpYear())
        paramCodedOutputStreamMicro.writeInt32(3, getExpYear());
      if (hasExpMonth())
        paramCodedOutputStreamMicro.writeInt32(4, getExpMonth());
      if (hasPersonName())
        paramCodedOutputStreamMicro.writeString(5, getPersonName());
      if (hasCountryCode())
        paramCodedOutputStreamMicro.writeString(6, getCountryCode());
      if (hasPostalCode())
        paramCodedOutputStreamMicro.writeString(7, getPostalCode());
      if (hasMakeDefault())
        paramCodedOutputStreamMicro.writeBool(8, getMakeDefault());
      if (hasAddress1())
        paramCodedOutputStreamMicro.writeString(9, getAddress1());
      if (hasAddress2())
        paramCodedOutputStreamMicro.writeString(10, getAddress2());
      if (hasCity())
        paramCodedOutputStreamMicro.writeString(11, getCity());
      if (hasState())
        paramCodedOutputStreamMicro.writeString(12, getState());
      if (hasPhone())
        paramCodedOutputStreamMicro.writeString(13, getPhone());
    }
  }

  public static final class ExternalPaypalInstrumentProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasInstrumentKey;
    private boolean hasMultiplePaypalInstrumentsSupported;
    private boolean hasPaypalAddress;
    private boolean hasPaypalEmail;
    private boolean hasPreapprovalKey;
    private String instrumentKey_ = "";
    private boolean multiplePaypalInstrumentsSupported_ = false;
    private VendingProtos.AddressProto paypalAddress_ = null;
    private String paypalEmail_ = "";
    private String preapprovalKey_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getInstrumentKey()
    {
      return this.instrumentKey_;
    }

    public boolean getMultiplePaypalInstrumentsSupported()
    {
      return this.multiplePaypalInstrumentsSupported_;
    }

    public VendingProtos.AddressProto getPaypalAddress()
    {
      return this.paypalAddress_;
    }

    public String getPaypalEmail()
    {
      return this.paypalEmail_;
    }

    public String getPreapprovalKey()
    {
      return this.preapprovalKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstrumentKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getInstrumentKey());
      if (hasPreapprovalKey())
        i += CodedOutputStreamMicro.computeStringSize(2, getPreapprovalKey());
      if (hasPaypalEmail())
        i += CodedOutputStreamMicro.computeStringSize(3, getPaypalEmail());
      if (hasPaypalAddress())
        i += CodedOutputStreamMicro.computeMessageSize(4, getPaypalAddress());
      if (hasMultiplePaypalInstrumentsSupported())
        i += CodedOutputStreamMicro.computeBoolSize(5, getMultiplePaypalInstrumentsSupported());
      this.cachedSize = i;
      return i;
    }

    public boolean hasInstrumentKey()
    {
      return this.hasInstrumentKey;
    }

    public boolean hasMultiplePaypalInstrumentsSupported()
    {
      return this.hasMultiplePaypalInstrumentsSupported;
    }

    public boolean hasPaypalAddress()
    {
      return this.hasPaypalAddress;
    }

    public boolean hasPaypalEmail()
    {
      return this.hasPaypalEmail;
    }

    public boolean hasPreapprovalKey()
    {
      return this.hasPreapprovalKey;
    }

    public ExternalPaypalInstrumentProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPreapprovalKey(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setPaypalEmail(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          VendingProtos.AddressProto localAddressProto = new VendingProtos.AddressProto();
          paramCodedInputStreamMicro.readMessage(localAddressProto);
          setPaypalAddress(localAddressProto);
          break;
        case 40:
        }
        setMultiplePaypalInstrumentsSupported(paramCodedInputStreamMicro.readBool());
      }
    }

    public ExternalPaypalInstrumentProto setInstrumentKey(String paramString)
    {
      this.hasInstrumentKey = true;
      this.instrumentKey_ = paramString;
      return this;
    }

    public ExternalPaypalInstrumentProto setMultiplePaypalInstrumentsSupported(boolean paramBoolean)
    {
      this.hasMultiplePaypalInstrumentsSupported = true;
      this.multiplePaypalInstrumentsSupported_ = paramBoolean;
      return this;
    }

    public ExternalPaypalInstrumentProto setPaypalAddress(VendingProtos.AddressProto paramAddressProto)
    {
      if (paramAddressProto == null)
        throw new NullPointerException();
      this.hasPaypalAddress = true;
      this.paypalAddress_ = paramAddressProto;
      return this;
    }

    public ExternalPaypalInstrumentProto setPaypalEmail(String paramString)
    {
      this.hasPaypalEmail = true;
      this.paypalEmail_ = paramString;
      return this;
    }

    public ExternalPaypalInstrumentProto setPreapprovalKey(String paramString)
    {
      this.hasPreapprovalKey = true;
      this.preapprovalKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstrumentKey())
        paramCodedOutputStreamMicro.writeString(1, getInstrumentKey());
      if (hasPreapprovalKey())
        paramCodedOutputStreamMicro.writeString(2, getPreapprovalKey());
      if (hasPaypalEmail())
        paramCodedOutputStreamMicro.writeString(3, getPaypalEmail());
      if (hasPaypalAddress())
        paramCodedOutputStreamMicro.writeMessage(4, getPaypalAddress());
      if (hasMultiplePaypalInstrumentsSupported())
        paramCodedOutputStreamMicro.writeBool(5, getMultiplePaypalInstrumentsSupported());
    }
  }

  public static final class FileMetadataProto extends MessageMicro
  {
    private int cachedSize = -1;
    private String downloadUrl_ = "";
    private int fileType_ = 0;
    private boolean hasDownloadUrl;
    private boolean hasFileType;
    private boolean hasSize;
    private boolean hasVersionCode;
    private long size_ = 0L;
    private int versionCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDownloadUrl()
    {
      return this.downloadUrl_;
    }

    public int getFileType()
    {
      return this.fileType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasFileType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getFileType());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
      if (hasSize())
        i += CodedOutputStreamMicro.computeInt64Size(3, getSize());
      if (hasDownloadUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getDownloadUrl());
      this.cachedSize = i;
      return i;
    }

    public long getSize()
    {
      return this.size_;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasDownloadUrl()
    {
      return this.hasDownloadUrl;
    }

    public boolean hasFileType()
    {
      return this.hasFileType;
    }

    public boolean hasSize()
    {
      return this.hasSize;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public FileMetadataProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setFileType(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setSize(paramCodedInputStreamMicro.readInt64());
          break;
        case 34:
        }
        setDownloadUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public FileMetadataProto setDownloadUrl(String paramString)
    {
      this.hasDownloadUrl = true;
      this.downloadUrl_ = paramString;
      return this;
    }

    public FileMetadataProto setFileType(int paramInt)
    {
      this.hasFileType = true;
      this.fileType_ = paramInt;
      return this;
    }

    public FileMetadataProto setSize(long paramLong)
    {
      this.hasSize = true;
      this.size_ = paramLong;
      return this;
    }

    public FileMetadataProto setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasFileType())
        paramCodedOutputStreamMicro.writeInt32(1, getFileType());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      if (hasSize())
        paramCodedOutputStreamMicro.writeInt64(3, getSize());
      if (hasDownloadUrl())
        paramCodedOutputStreamMicro.writeString(4, getDownloadUrl());
    }
  }

  public static final class GetAddressSnippetRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private EncryptedSubscriberInfo encryptedSubscriberInfo_ = null;
    private boolean hasEncryptedSubscriberInfo;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public EncryptedSubscriberInfo getEncryptedSubscriberInfo()
    {
      return this.encryptedSubscriberInfo_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasEncryptedSubscriberInfo())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getEncryptedSubscriberInfo());
      this.cachedSize = i;
      return i;
    }

    public boolean hasEncryptedSubscriberInfo()
    {
      return this.hasEncryptedSubscriberInfo;
    }

    public GetAddressSnippetRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        EncryptedSubscriberInfo localEncryptedSubscriberInfo = new EncryptedSubscriberInfo();
        paramCodedInputStreamMicro.readMessage(localEncryptedSubscriberInfo);
        setEncryptedSubscriberInfo(localEncryptedSubscriberInfo);
      }
    }

    public GetAddressSnippetRequestProto setEncryptedSubscriberInfo(EncryptedSubscriberInfo paramEncryptedSubscriberInfo)
    {
      if (paramEncryptedSubscriberInfo == null)
        throw new NullPointerException();
      this.hasEncryptedSubscriberInfo = true;
      this.encryptedSubscriberInfo_ = paramEncryptedSubscriberInfo;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasEncryptedSubscriberInfo())
        paramCodedOutputStreamMicro.writeMessage(1, getEncryptedSubscriberInfo());
    }
  }

  public static final class GetAddressSnippetResponseProto extends MessageMicro
  {
    private String addressSnippet_ = "";
    private int cachedSize = -1;
    private boolean hasAddressSnippet;

    public String getAddressSnippet()
    {
      return this.addressSnippet_;
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
      if (hasAddressSnippet())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAddressSnippet());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddressSnippet()
    {
      return this.hasAddressSnippet;
    }

    public GetAddressSnippetResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setAddressSnippet(paramCodedInputStreamMicro.readString());
      }
    }

    public GetAddressSnippetResponseProto setAddressSnippet(String paramString)
    {
      this.hasAddressSnippet = true;
      this.addressSnippet_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddressSnippet())
        paramCodedOutputStreamMicro.writeString(1, getAddressSnippet());
    }
  }

  public static final class GetAssetRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private String directDownloadKey_ = "";
    private boolean hasAssetId;
    private boolean hasDirectDownloadKey;

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

    public String getDirectDownloadKey()
    {
      return this.directDownloadKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasDirectDownloadKey())
        i += CodedOutputStreamMicro.computeStringSize(2, getDirectDownloadKey());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasDirectDownloadKey()
    {
      return this.hasDirectDownloadKey;
    }

    public GetAssetRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setDirectDownloadKey(paramCodedInputStreamMicro.readString());
      }
    }

    public GetAssetRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public GetAssetRequestProto setDirectDownloadKey(String paramString)
    {
      this.hasDirectDownloadKey = true;
      this.directDownloadKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasDirectDownloadKey())
        paramCodedOutputStreamMicro.writeString(2, getDirectDownloadKey());
    }
  }

  public static final class GetAssetResponseProto extends MessageMicro
  {
    private List<VendingProtos.FileMetadataProto> additionalFile_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean hasInstallAsset;
    private InstallAsset installAsset_ = null;

    public GetAssetResponseProto addAdditionalFile(VendingProtos.FileMetadataProto paramFileMetadataProto)
    {
      if (paramFileMetadataProto == null)
        throw new NullPointerException();
      if (this.additionalFile_.isEmpty())
        this.additionalFile_ = new ArrayList();
      this.additionalFile_.add(paramFileMetadataProto);
      return this;
    }

    public List<VendingProtos.FileMetadataProto> getAdditionalFileList()
    {
      return this.additionalFile_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public InstallAsset getInstallAsset()
    {
      return this.installAsset_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasInstallAsset())
        i = 0 + CodedOutputStreamMicro.computeGroupSize(1, getInstallAsset());
      Iterator localIterator = getAdditionalFileList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(15, (VendingProtos.FileMetadataProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasInstallAsset()
    {
      return this.hasInstallAsset;
    }

    public GetAssetResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 11:
          InstallAsset localInstallAsset = new InstallAsset();
          paramCodedInputStreamMicro.readGroup(localInstallAsset, 1);
          setInstallAsset(localInstallAsset);
          break;
        case 122:
        }
        VendingProtos.FileMetadataProto localFileMetadataProto = new VendingProtos.FileMetadataProto();
        paramCodedInputStreamMicro.readMessage(localFileMetadataProto);
        addAdditionalFile(localFileMetadataProto);
      }
    }

    public GetAssetResponseProto setInstallAsset(InstallAsset paramInstallAsset)
    {
      if (paramInstallAsset == null)
        throw new NullPointerException();
      this.hasInstallAsset = true;
      this.installAsset_ = paramInstallAsset;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasInstallAsset())
        paramCodedOutputStreamMicro.writeGroup(1, getInstallAsset());
      Iterator localIterator = getAdditionalFileList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(15, (VendingProtos.FileMetadataProto)localIterator.next());
    }

    public static final class InstallAsset extends MessageMicro
    {
      private String assetId_ = "";
      private String assetName_ = "";
      private String assetPackage_ = "";
      private String assetSignature_ = "";
      private long assetSize_ = 0L;
      private String assetType_ = "";
      private String blobUrl_ = "";
      private int cachedSize = -1;
      private String downloadAuthCookieName_ = "";
      private String downloadAuthCookieValue_ = "";
      private boolean forwardLocked_ = false;
      private boolean hasAssetId;
      private boolean hasAssetName;
      private boolean hasAssetPackage;
      private boolean hasAssetSignature;
      private boolean hasAssetSize;
      private boolean hasAssetType;
      private boolean hasBlobUrl;
      private boolean hasDownloadAuthCookieName;
      private boolean hasDownloadAuthCookieValue;
      private boolean hasForwardLocked;
      private boolean hasPostInstallRefundWindowMillis;
      private boolean hasRefundTimeoutMillis;
      private boolean hasSecured;
      private boolean hasVersionCode;
      private long postInstallRefundWindowMillis_ = 0L;
      private long refundTimeoutMillis_ = 0L;
      private boolean secured_ = false;
      private int versionCode_ = 0;

      public String getAssetId()
      {
        return this.assetId_;
      }

      public String getAssetName()
      {
        return this.assetName_;
      }

      public String getAssetPackage()
      {
        return this.assetPackage_;
      }

      public String getAssetSignature()
      {
        return this.assetSignature_;
      }

      public long getAssetSize()
      {
        return this.assetSize_;
      }

      public String getAssetType()
      {
        return this.assetType_;
      }

      public String getBlobUrl()
      {
        return this.blobUrl_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getDownloadAuthCookieName()
      {
        return this.downloadAuthCookieName_;
      }

      public String getDownloadAuthCookieValue()
      {
        return this.downloadAuthCookieValue_;
      }

      public boolean getForwardLocked()
      {
        return this.forwardLocked_;
      }

      public long getPostInstallRefundWindowMillis()
      {
        return this.postInstallRefundWindowMillis_;
      }

      public long getRefundTimeoutMillis()
      {
        return this.refundTimeoutMillis_;
      }

      public boolean getSecured()
      {
        return this.secured_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasAssetId())
          i = 0 + CodedOutputStreamMicro.computeStringSize(2, getAssetId());
        if (hasAssetName())
          i += CodedOutputStreamMicro.computeStringSize(3, getAssetName());
        if (hasAssetType())
          i += CodedOutputStreamMicro.computeStringSize(4, getAssetType());
        if (hasAssetPackage())
          i += CodedOutputStreamMicro.computeStringSize(5, getAssetPackage());
        if (hasBlobUrl())
          i += CodedOutputStreamMicro.computeStringSize(6, getBlobUrl());
        if (hasAssetSignature())
          i += CodedOutputStreamMicro.computeStringSize(7, getAssetSignature());
        if (hasAssetSize())
          i += CodedOutputStreamMicro.computeInt64Size(8, getAssetSize());
        if (hasRefundTimeoutMillis())
          i += CodedOutputStreamMicro.computeInt64Size(9, getRefundTimeoutMillis());
        if (hasForwardLocked())
          i += CodedOutputStreamMicro.computeBoolSize(10, getForwardLocked());
        if (hasSecured())
          i += CodedOutputStreamMicro.computeBoolSize(11, getSecured());
        if (hasVersionCode())
          i += CodedOutputStreamMicro.computeInt32Size(12, getVersionCode());
        if (hasDownloadAuthCookieName())
          i += CodedOutputStreamMicro.computeStringSize(13, getDownloadAuthCookieName());
        if (hasDownloadAuthCookieValue())
          i += CodedOutputStreamMicro.computeStringSize(14, getDownloadAuthCookieValue());
        if (hasPostInstallRefundWindowMillis())
          i += CodedOutputStreamMicro.computeInt64Size(16, getPostInstallRefundWindowMillis());
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

      public boolean hasAssetName()
      {
        return this.hasAssetName;
      }

      public boolean hasAssetPackage()
      {
        return this.hasAssetPackage;
      }

      public boolean hasAssetSignature()
      {
        return this.hasAssetSignature;
      }

      public boolean hasAssetSize()
      {
        return this.hasAssetSize;
      }

      public boolean hasAssetType()
      {
        return this.hasAssetType;
      }

      public boolean hasBlobUrl()
      {
        return this.hasBlobUrl;
      }

      public boolean hasDownloadAuthCookieName()
      {
        return this.hasDownloadAuthCookieName;
      }

      public boolean hasDownloadAuthCookieValue()
      {
        return this.hasDownloadAuthCookieValue;
      }

      public boolean hasForwardLocked()
      {
        return this.hasForwardLocked;
      }

      public boolean hasPostInstallRefundWindowMillis()
      {
        return this.hasPostInstallRefundWindowMillis;
      }

      public boolean hasRefundTimeoutMillis()
      {
        return this.hasRefundTimeoutMillis;
      }

      public boolean hasSecured()
      {
        return this.hasSecured;
      }

      public boolean hasVersionCode()
      {
        return this.hasVersionCode;
      }

      public InstallAsset mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 18:
            setAssetId(paramCodedInputStreamMicro.readString());
            break;
          case 26:
            setAssetName(paramCodedInputStreamMicro.readString());
            break;
          case 34:
            setAssetType(paramCodedInputStreamMicro.readString());
            break;
          case 42:
            setAssetPackage(paramCodedInputStreamMicro.readString());
            break;
          case 50:
            setBlobUrl(paramCodedInputStreamMicro.readString());
            break;
          case 58:
            setAssetSignature(paramCodedInputStreamMicro.readString());
            break;
          case 64:
            setAssetSize(paramCodedInputStreamMicro.readInt64());
            break;
          case 72:
            setRefundTimeoutMillis(paramCodedInputStreamMicro.readInt64());
            break;
          case 80:
            setForwardLocked(paramCodedInputStreamMicro.readBool());
            break;
          case 88:
            setSecured(paramCodedInputStreamMicro.readBool());
            break;
          case 96:
            setVersionCode(paramCodedInputStreamMicro.readInt32());
            break;
          case 106:
            setDownloadAuthCookieName(paramCodedInputStreamMicro.readString());
            break;
          case 114:
            setDownloadAuthCookieValue(paramCodedInputStreamMicro.readString());
            break;
          case 128:
          }
          setPostInstallRefundWindowMillis(paramCodedInputStreamMicro.readInt64());
        }
      }

      public InstallAsset setAssetId(String paramString)
      {
        this.hasAssetId = true;
        this.assetId_ = paramString;
        return this;
      }

      public InstallAsset setAssetName(String paramString)
      {
        this.hasAssetName = true;
        this.assetName_ = paramString;
        return this;
      }

      public InstallAsset setAssetPackage(String paramString)
      {
        this.hasAssetPackage = true;
        this.assetPackage_ = paramString;
        return this;
      }

      public InstallAsset setAssetSignature(String paramString)
      {
        this.hasAssetSignature = true;
        this.assetSignature_ = paramString;
        return this;
      }

      public InstallAsset setAssetSize(long paramLong)
      {
        this.hasAssetSize = true;
        this.assetSize_ = paramLong;
        return this;
      }

      public InstallAsset setAssetType(String paramString)
      {
        this.hasAssetType = true;
        this.assetType_ = paramString;
        return this;
      }

      public InstallAsset setBlobUrl(String paramString)
      {
        this.hasBlobUrl = true;
        this.blobUrl_ = paramString;
        return this;
      }

      public InstallAsset setDownloadAuthCookieName(String paramString)
      {
        this.hasDownloadAuthCookieName = true;
        this.downloadAuthCookieName_ = paramString;
        return this;
      }

      public InstallAsset setDownloadAuthCookieValue(String paramString)
      {
        this.hasDownloadAuthCookieValue = true;
        this.downloadAuthCookieValue_ = paramString;
        return this;
      }

      public InstallAsset setForwardLocked(boolean paramBoolean)
      {
        this.hasForwardLocked = true;
        this.forwardLocked_ = paramBoolean;
        return this;
      }

      public InstallAsset setPostInstallRefundWindowMillis(long paramLong)
      {
        this.hasPostInstallRefundWindowMillis = true;
        this.postInstallRefundWindowMillis_ = paramLong;
        return this;
      }

      public InstallAsset setRefundTimeoutMillis(long paramLong)
      {
        this.hasRefundTimeoutMillis = true;
        this.refundTimeoutMillis_ = paramLong;
        return this;
      }

      public InstallAsset setSecured(boolean paramBoolean)
      {
        this.hasSecured = true;
        this.secured_ = paramBoolean;
        return this;
      }

      public InstallAsset setVersionCode(int paramInt)
      {
        this.hasVersionCode = true;
        this.versionCode_ = paramInt;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasAssetId())
          paramCodedOutputStreamMicro.writeString(2, getAssetId());
        if (hasAssetName())
          paramCodedOutputStreamMicro.writeString(3, getAssetName());
        if (hasAssetType())
          paramCodedOutputStreamMicro.writeString(4, getAssetType());
        if (hasAssetPackage())
          paramCodedOutputStreamMicro.writeString(5, getAssetPackage());
        if (hasBlobUrl())
          paramCodedOutputStreamMicro.writeString(6, getBlobUrl());
        if (hasAssetSignature())
          paramCodedOutputStreamMicro.writeString(7, getAssetSignature());
        if (hasAssetSize())
          paramCodedOutputStreamMicro.writeInt64(8, getAssetSize());
        if (hasRefundTimeoutMillis())
          paramCodedOutputStreamMicro.writeInt64(9, getRefundTimeoutMillis());
        if (hasForwardLocked())
          paramCodedOutputStreamMicro.writeBool(10, getForwardLocked());
        if (hasSecured())
          paramCodedOutputStreamMicro.writeBool(11, getSecured());
        if (hasVersionCode())
          paramCodedOutputStreamMicro.writeInt32(12, getVersionCode());
        if (hasDownloadAuthCookieName())
          paramCodedOutputStreamMicro.writeString(13, getDownloadAuthCookieName());
        if (hasDownloadAuthCookieValue())
          paramCodedOutputStreamMicro.writeString(14, getDownloadAuthCookieValue());
        if (hasPostInstallRefundWindowMillis())
          paramCodedOutputStreamMicro.writeInt64(16, getPostInstallRefundWindowMillis());
      }
    }
  }

  public static final class GetCarrierInfoRequestProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public GetCarrierInfoRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class GetCarrierInfoResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private ByteStringMicro carrierBanner_ = ByteStringMicro.EMPTY;
    private boolean carrierChannelEnabled_ = false;
    private int carrierImageDensity_ = 160;
    private ByteStringMicro carrierLogoIcon_ = ByteStringMicro.EMPTY;
    private String carrierSubtitle_ = "";
    private String carrierTitle_ = "";
    private boolean hasCarrierBanner;
    private boolean hasCarrierChannelEnabled;
    private boolean hasCarrierImageDensity;
    private boolean hasCarrierLogoIcon;
    private boolean hasCarrierSubtitle;
    private boolean hasCarrierTitle;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ByteStringMicro getCarrierBanner()
    {
      return this.carrierBanner_;
    }

    public boolean getCarrierChannelEnabled()
    {
      return this.carrierChannelEnabled_;
    }

    public int getCarrierImageDensity()
    {
      return this.carrierImageDensity_;
    }

    public ByteStringMicro getCarrierLogoIcon()
    {
      return this.carrierLogoIcon_;
    }

    public String getCarrierSubtitle()
    {
      return this.carrierSubtitle_;
    }

    public String getCarrierTitle()
    {
      return this.carrierTitle_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCarrierChannelEnabled())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getCarrierChannelEnabled());
      if (hasCarrierLogoIcon())
        i += CodedOutputStreamMicro.computeBytesSize(2, getCarrierLogoIcon());
      if (hasCarrierBanner())
        i += CodedOutputStreamMicro.computeBytesSize(3, getCarrierBanner());
      if (hasCarrierSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(4, getCarrierSubtitle());
      if (hasCarrierTitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getCarrierTitle());
      if (hasCarrierImageDensity())
        i += CodedOutputStreamMicro.computeInt32Size(6, getCarrierImageDensity());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCarrierBanner()
    {
      return this.hasCarrierBanner;
    }

    public boolean hasCarrierChannelEnabled()
    {
      return this.hasCarrierChannelEnabled;
    }

    public boolean hasCarrierImageDensity()
    {
      return this.hasCarrierImageDensity;
    }

    public boolean hasCarrierLogoIcon()
    {
      return this.hasCarrierLogoIcon;
    }

    public boolean hasCarrierSubtitle()
    {
      return this.hasCarrierSubtitle;
    }

    public boolean hasCarrierTitle()
    {
      return this.hasCarrierTitle;
    }

    public GetCarrierInfoResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCarrierChannelEnabled(paramCodedInputStreamMicro.readBool());
          break;
        case 18:
          setCarrierLogoIcon(paramCodedInputStreamMicro.readBytes());
          break;
        case 26:
          setCarrierBanner(paramCodedInputStreamMicro.readBytes());
          break;
        case 34:
          setCarrierSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setCarrierTitle(paramCodedInputStreamMicro.readString());
          break;
        case 48:
        }
        setCarrierImageDensity(paramCodedInputStreamMicro.readInt32());
      }
    }

    public GetCarrierInfoResponseProto setCarrierBanner(ByteStringMicro paramByteStringMicro)
    {
      this.hasCarrierBanner = true;
      this.carrierBanner_ = paramByteStringMicro;
      return this;
    }

    public GetCarrierInfoResponseProto setCarrierChannelEnabled(boolean paramBoolean)
    {
      this.hasCarrierChannelEnabled = true;
      this.carrierChannelEnabled_ = paramBoolean;
      return this;
    }

    public GetCarrierInfoResponseProto setCarrierImageDensity(int paramInt)
    {
      this.hasCarrierImageDensity = true;
      this.carrierImageDensity_ = paramInt;
      return this;
    }

    public GetCarrierInfoResponseProto setCarrierLogoIcon(ByteStringMicro paramByteStringMicro)
    {
      this.hasCarrierLogoIcon = true;
      this.carrierLogoIcon_ = paramByteStringMicro;
      return this;
    }

    public GetCarrierInfoResponseProto setCarrierSubtitle(String paramString)
    {
      this.hasCarrierSubtitle = true;
      this.carrierSubtitle_ = paramString;
      return this;
    }

    public GetCarrierInfoResponseProto setCarrierTitle(String paramString)
    {
      this.hasCarrierTitle = true;
      this.carrierTitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCarrierChannelEnabled())
        paramCodedOutputStreamMicro.writeBool(1, getCarrierChannelEnabled());
      if (hasCarrierLogoIcon())
        paramCodedOutputStreamMicro.writeBytes(2, getCarrierLogoIcon());
      if (hasCarrierBanner())
        paramCodedOutputStreamMicro.writeBytes(3, getCarrierBanner());
      if (hasCarrierSubtitle())
        paramCodedOutputStreamMicro.writeString(4, getCarrierSubtitle());
      if (hasCarrierTitle())
        paramCodedOutputStreamMicro.writeString(5, getCarrierTitle());
      if (hasCarrierImageDensity())
        paramCodedOutputStreamMicro.writeInt32(6, getCarrierImageDensity());
    }
  }

  public static final class GetCategoriesRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPrefetchPromoData;
    private boolean prefetchPromoData_ = false;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getPrefetchPromoData()
    {
      return this.prefetchPromoData_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPrefetchPromoData())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getPrefetchPromoData());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPrefetchPromoData()
    {
      return this.hasPrefetchPromoData;
    }

    public GetCategoriesRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setPrefetchPromoData(paramCodedInputStreamMicro.readBool());
      }
    }

    public GetCategoriesRequestProto setPrefetchPromoData(boolean paramBoolean)
    {
      this.hasPrefetchPromoData = true;
      this.prefetchPromoData_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPrefetchPromoData())
        paramCodedOutputStreamMicro.writeBool(1, getPrefetchPromoData());
    }
  }

  public static final class GetCategoriesResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private List<VendingProtos.CategoryProto> categories_ = Collections.emptyList();

    public GetCategoriesResponseProto addCategories(VendingProtos.CategoryProto paramCategoryProto)
    {
      if (paramCategoryProto == null)
        throw new NullPointerException();
      if (this.categories_.isEmpty())
        this.categories_ = new ArrayList();
      this.categories_.add(paramCategoryProto);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<VendingProtos.CategoryProto> getCategoriesList()
    {
      return this.categories_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getCategoriesList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.CategoryProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public GetCategoriesResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.CategoryProto localCategoryProto = new VendingProtos.CategoryProto();
        paramCodedInputStreamMicro.readMessage(localCategoryProto);
        addCategories(localCategoryProto);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getCategoriesList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.CategoryProto)localIterator.next());
    }
  }

  public static final class GetImageRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;
    private boolean hasImageId;
    private boolean hasImageUsage;
    private boolean hasProductType;
    private boolean hasScreenPropertyDensity;
    private boolean hasScreenPropertyHeight;
    private boolean hasScreenPropertyWidth;
    private String imageId_ = "";
    private int imageUsage_ = 0;
    private int productType_ = 0;
    private int screenPropertyDensity_ = 0;
    private int screenPropertyHeight_ = 0;
    private int screenPropertyWidth_ = 0;

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

    public String getImageId()
    {
      return this.imageId_;
    }

    public int getImageUsage()
    {
      return this.imageUsage_;
    }

    public int getProductType()
    {
      return this.productType_;
    }

    public int getScreenPropertyDensity()
    {
      return this.screenPropertyDensity_;
    }

    public int getScreenPropertyHeight()
    {
      return this.screenPropertyHeight_;
    }

    public int getScreenPropertyWidth()
    {
      return this.screenPropertyWidth_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasImageUsage())
        i += CodedOutputStreamMicro.computeInt32Size(3, getImageUsage());
      if (hasImageId())
        i += CodedOutputStreamMicro.computeStringSize(4, getImageId());
      if (hasScreenPropertyWidth())
        i += CodedOutputStreamMicro.computeInt32Size(5, getScreenPropertyWidth());
      if (hasScreenPropertyHeight())
        i += CodedOutputStreamMicro.computeInt32Size(6, getScreenPropertyHeight());
      if (hasScreenPropertyDensity())
        i += CodedOutputStreamMicro.computeInt32Size(7, getScreenPropertyDensity());
      if (hasProductType())
        i += CodedOutputStreamMicro.computeInt32Size(8, getProductType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasImageId()
    {
      return this.hasImageId;
    }

    public boolean hasImageUsage()
    {
      return this.hasImageUsage;
    }

    public boolean hasProductType()
    {
      return this.hasProductType;
    }

    public boolean hasScreenPropertyDensity()
    {
      return this.hasScreenPropertyDensity;
    }

    public boolean hasScreenPropertyHeight()
    {
      return this.hasScreenPropertyHeight;
    }

    public boolean hasScreenPropertyWidth()
    {
      return this.hasScreenPropertyWidth;
    }

    public GetImageRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setImageUsage(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
          setImageId(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setScreenPropertyWidth(paramCodedInputStreamMicro.readInt32());
          break;
        case 48:
          setScreenPropertyHeight(paramCodedInputStreamMicro.readInt32());
          break;
        case 56:
          setScreenPropertyDensity(paramCodedInputStreamMicro.readInt32());
          break;
        case 64:
        }
        setProductType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public GetImageRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public GetImageRequestProto setImageId(String paramString)
    {
      this.hasImageId = true;
      this.imageId_ = paramString;
      return this;
    }

    public GetImageRequestProto setImageUsage(int paramInt)
    {
      this.hasImageUsage = true;
      this.imageUsage_ = paramInt;
      return this;
    }

    public GetImageRequestProto setProductType(int paramInt)
    {
      this.hasProductType = true;
      this.productType_ = paramInt;
      return this;
    }

    public GetImageRequestProto setScreenPropertyDensity(int paramInt)
    {
      this.hasScreenPropertyDensity = true;
      this.screenPropertyDensity_ = paramInt;
      return this;
    }

    public GetImageRequestProto setScreenPropertyHeight(int paramInt)
    {
      this.hasScreenPropertyHeight = true;
      this.screenPropertyHeight_ = paramInt;
      return this;
    }

    public GetImageRequestProto setScreenPropertyWidth(int paramInt)
    {
      this.hasScreenPropertyWidth = true;
      this.screenPropertyWidth_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasImageUsage())
        paramCodedOutputStreamMicro.writeInt32(3, getImageUsage());
      if (hasImageId())
        paramCodedOutputStreamMicro.writeString(4, getImageId());
      if (hasScreenPropertyWidth())
        paramCodedOutputStreamMicro.writeInt32(5, getScreenPropertyWidth());
      if (hasScreenPropertyHeight())
        paramCodedOutputStreamMicro.writeInt32(6, getScreenPropertyHeight());
      if (hasScreenPropertyDensity())
        paramCodedOutputStreamMicro.writeInt32(7, getScreenPropertyDensity());
      if (hasProductType())
        paramCodedOutputStreamMicro.writeInt32(8, getProductType());
    }
  }

  public static final class GetImageResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasImageData;
    private boolean hasImageDensity;
    private ByteStringMicro imageData_ = ByteStringMicro.EMPTY;
    private int imageDensity_ = 160;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ByteStringMicro getImageData()
    {
      return this.imageData_;
    }

    public int getImageDensity()
    {
      return this.imageDensity_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasImageData())
        i = 0 + CodedOutputStreamMicro.computeBytesSize(1, getImageData());
      if (hasImageDensity())
        i += CodedOutputStreamMicro.computeInt32Size(2, getImageDensity());
      this.cachedSize = i;
      return i;
    }

    public boolean hasImageData()
    {
      return this.hasImageData;
    }

    public boolean hasImageDensity()
    {
      return this.hasImageDensity;
    }

    public GetImageResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setImageData(paramCodedInputStreamMicro.readBytes());
          break;
        case 16:
        }
        setImageDensity(paramCodedInputStreamMicro.readInt32());
      }
    }

    public GetImageResponseProto setImageData(ByteStringMicro paramByteStringMicro)
    {
      this.hasImageData = true;
      this.imageData_ = paramByteStringMicro;
      return this;
    }

    public GetImageResponseProto setImageDensity(int paramInt)
    {
      this.hasImageDensity = true;
      this.imageDensity_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasImageData())
        paramCodedOutputStreamMicro.writeBytes(1, getImageData());
      if (hasImageDensity())
        paramCodedOutputStreamMicro.writeInt32(2, getImageDensity());
    }
  }

  public static final class GetMarketMetadataRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private int contentRating_ = 0;
    private DeviceConfigurationProto deviceConfiguration_ = null;
    private String deviceManufacturerName_ = "";
    private String deviceModelName_ = "";
    private boolean deviceRoaming_ = false;
    private boolean hasContentRating;
    private boolean hasDeviceConfiguration;
    private boolean hasDeviceManufacturerName;
    private boolean hasDeviceModelName;
    private boolean hasDeviceRoaming;
    private boolean hasLastRequestTime;
    private long lastRequestTime_ = 0L;
    private List<String> marketSignatureHash_ = Collections.emptyList();

    public GetMarketMetadataRequestProto addMarketSignatureHash(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.marketSignatureHash_.isEmpty())
        this.marketSignatureHash_ = new ArrayList();
      this.marketSignatureHash_.add(paramString);
      return this;
    }

    public GetMarketMetadataRequestProto clearDeviceConfiguration()
    {
      this.hasDeviceConfiguration = false;
      this.deviceConfiguration_ = null;
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getContentRating()
    {
      return this.contentRating_;
    }

    public DeviceConfigurationProto getDeviceConfiguration()
    {
      return this.deviceConfiguration_;
    }

    public String getDeviceManufacturerName()
    {
      return this.deviceManufacturerName_;
    }

    public String getDeviceModelName()
    {
      return this.deviceModelName_;
    }

    public boolean getDeviceRoaming()
    {
      return this.deviceRoaming_;
    }

    public long getLastRequestTime()
    {
      return this.lastRequestTime_;
    }

    public List<String> getMarketSignatureHashList()
    {
      return this.marketSignatureHash_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLastRequestTime())
        i = 0 + CodedOutputStreamMicro.computeInt64Size(1, getLastRequestTime());
      if (hasDeviceConfiguration())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDeviceConfiguration());
      if (hasDeviceRoaming())
        i += CodedOutputStreamMicro.computeBoolSize(3, getDeviceRoaming());
      int j = 0;
      Iterator localIterator = getMarketSignatureHashList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 1 * getMarketSignatureHashList().size();
      if (hasContentRating())
        k += CodedOutputStreamMicro.computeInt32Size(5, getContentRating());
      if (hasDeviceModelName())
        k += CodedOutputStreamMicro.computeStringSize(6, getDeviceModelName());
      if (hasDeviceManufacturerName())
        k += CodedOutputStreamMicro.computeStringSize(7, getDeviceManufacturerName());
      this.cachedSize = k;
      return k;
    }

    public boolean hasContentRating()
    {
      return this.hasContentRating;
    }

    public boolean hasDeviceConfiguration()
    {
      return this.hasDeviceConfiguration;
    }

    public boolean hasDeviceManufacturerName()
    {
      return this.hasDeviceManufacturerName;
    }

    public boolean hasDeviceModelName()
    {
      return this.hasDeviceModelName;
    }

    public boolean hasDeviceRoaming()
    {
      return this.hasDeviceRoaming;
    }

    public boolean hasLastRequestTime()
    {
      return this.hasLastRequestTime;
    }

    public GetMarketMetadataRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setLastRequestTime(paramCodedInputStreamMicro.readInt64());
          break;
        case 18:
          DeviceConfigurationProto localDeviceConfigurationProto = new DeviceConfigurationProto();
          paramCodedInputStreamMicro.readMessage(localDeviceConfigurationProto);
          setDeviceConfiguration(localDeviceConfigurationProto);
          break;
        case 24:
          setDeviceRoaming(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          addMarketSignatureHash(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setContentRating(paramCodedInputStreamMicro.readInt32());
          break;
        case 50:
          setDeviceModelName(paramCodedInputStreamMicro.readString());
          break;
        case 58:
        }
        setDeviceManufacturerName(paramCodedInputStreamMicro.readString());
      }
    }

    public GetMarketMetadataRequestProto setContentRating(int paramInt)
    {
      this.hasContentRating = true;
      this.contentRating_ = paramInt;
      return this;
    }

    public GetMarketMetadataRequestProto setDeviceConfiguration(DeviceConfigurationProto paramDeviceConfigurationProto)
    {
      if (paramDeviceConfigurationProto == null)
        throw new NullPointerException();
      this.hasDeviceConfiguration = true;
      this.deviceConfiguration_ = paramDeviceConfigurationProto;
      return this;
    }

    public GetMarketMetadataRequestProto setDeviceManufacturerName(String paramString)
    {
      this.hasDeviceManufacturerName = true;
      this.deviceManufacturerName_ = paramString;
      return this;
    }

    public GetMarketMetadataRequestProto setDeviceModelName(String paramString)
    {
      this.hasDeviceModelName = true;
      this.deviceModelName_ = paramString;
      return this;
    }

    public GetMarketMetadataRequestProto setDeviceRoaming(boolean paramBoolean)
    {
      this.hasDeviceRoaming = true;
      this.deviceRoaming_ = paramBoolean;
      return this;
    }

    public GetMarketMetadataRequestProto setLastRequestTime(long paramLong)
    {
      this.hasLastRequestTime = true;
      this.lastRequestTime_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLastRequestTime())
        paramCodedOutputStreamMicro.writeInt64(1, getLastRequestTime());
      if (hasDeviceConfiguration())
        paramCodedOutputStreamMicro.writeMessage(2, getDeviceConfiguration());
      if (hasDeviceRoaming())
        paramCodedOutputStreamMicro.writeBool(3, getDeviceRoaming());
      Iterator localIterator = getMarketSignatureHashList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(4, (String)localIterator.next());
      if (hasContentRating())
        paramCodedOutputStreamMicro.writeInt32(5, getContentRating());
      if (hasDeviceModelName())
        paramCodedOutputStreamMicro.writeString(6, getDeviceModelName());
      if (hasDeviceManufacturerName())
        paramCodedOutputStreamMicro.writeString(7, getDeviceManufacturerName());
    }
  }

  public static final class GetMarketMetadataResponseProto extends MessageMicro
  {
    private boolean billingEventsEnabled_ = false;
    private List<VendingProtos.BillingParameterProto> billingParameter_ = Collections.emptyList();
    private int cachedSize = -1;
    private boolean commentPostEnabled_ = true;
    private boolean hasBillingEventsEnabled;
    private boolean hasCommentPostEnabled;
    private boolean hasInAppBillingEnabled;
    private boolean hasInAppBillingMaxApiVersion;
    private boolean hasLatestClientUrl;
    private boolean hasLatestClientVersionCode;
    private boolean hasPaidAppsEnabled;
    private boolean hasWarningMessage;
    private boolean inAppBillingEnabled_ = false;
    private int inAppBillingMaxApiVersion_ = -1;
    private String latestClientUrl_ = "";
    private int latestClientVersionCode_ = 0;
    private boolean paidAppsEnabled_ = false;
    private String warningMessage_ = "";

    public GetMarketMetadataResponseProto addBillingParameter(VendingProtos.BillingParameterProto paramBillingParameterProto)
    {
      if (paramBillingParameterProto == null)
        throw new NullPointerException();
      if (this.billingParameter_.isEmpty())
        this.billingParameter_ = new ArrayList();
      this.billingParameter_.add(paramBillingParameterProto);
      return this;
    }

    public boolean getBillingEventsEnabled()
    {
      return this.billingEventsEnabled_;
    }

    public List<VendingProtos.BillingParameterProto> getBillingParameterList()
    {
      return this.billingParameter_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getCommentPostEnabled()
    {
      return this.commentPostEnabled_;
    }

    public boolean getInAppBillingEnabled()
    {
      return this.inAppBillingEnabled_;
    }

    public int getInAppBillingMaxApiVersion()
    {
      return this.inAppBillingMaxApiVersion_;
    }

    public String getLatestClientUrl()
    {
      return this.latestClientUrl_;
    }

    public int getLatestClientVersionCode()
    {
      return this.latestClientVersionCode_;
    }

    public boolean getPaidAppsEnabled()
    {
      return this.paidAppsEnabled_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLatestClientVersionCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getLatestClientVersionCode());
      if (hasLatestClientUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getLatestClientUrl());
      if (hasPaidAppsEnabled())
        i += CodedOutputStreamMicro.computeBoolSize(3, getPaidAppsEnabled());
      Iterator localIterator = getBillingParameterList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (VendingProtos.BillingParameterProto)localIterator.next());
      if (hasCommentPostEnabled())
        i += CodedOutputStreamMicro.computeBoolSize(5, getCommentPostEnabled());
      if (hasBillingEventsEnabled())
        i += CodedOutputStreamMicro.computeBoolSize(6, getBillingEventsEnabled());
      if (hasWarningMessage())
        i += CodedOutputStreamMicro.computeStringSize(7, getWarningMessage());
      if (hasInAppBillingEnabled())
        i += CodedOutputStreamMicro.computeBoolSize(8, getInAppBillingEnabled());
      if (hasInAppBillingMaxApiVersion())
        i += CodedOutputStreamMicro.computeInt32Size(9, getInAppBillingMaxApiVersion());
      this.cachedSize = i;
      return i;
    }

    public String getWarningMessage()
    {
      return this.warningMessage_;
    }

    public boolean hasBillingEventsEnabled()
    {
      return this.hasBillingEventsEnabled;
    }

    public boolean hasCommentPostEnabled()
    {
      return this.hasCommentPostEnabled;
    }

    public boolean hasInAppBillingEnabled()
    {
      return this.hasInAppBillingEnabled;
    }

    public boolean hasInAppBillingMaxApiVersion()
    {
      return this.hasInAppBillingMaxApiVersion;
    }

    public boolean hasLatestClientUrl()
    {
      return this.hasLatestClientUrl;
    }

    public boolean hasLatestClientVersionCode()
    {
      return this.hasLatestClientVersionCode;
    }

    public boolean hasPaidAppsEnabled()
    {
      return this.hasPaidAppsEnabled;
    }

    public boolean hasWarningMessage()
    {
      return this.hasWarningMessage;
    }

    public GetMarketMetadataResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setLatestClientVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setLatestClientUrl(paramCodedInputStreamMicro.readString());
          break;
        case 24:
          setPaidAppsEnabled(paramCodedInputStreamMicro.readBool());
          break;
        case 34:
          VendingProtos.BillingParameterProto localBillingParameterProto = new VendingProtos.BillingParameterProto();
          paramCodedInputStreamMicro.readMessage(localBillingParameterProto);
          addBillingParameter(localBillingParameterProto);
          break;
        case 40:
          setCommentPostEnabled(paramCodedInputStreamMicro.readBool());
          break;
        case 48:
          setBillingEventsEnabled(paramCodedInputStreamMicro.readBool());
          break;
        case 58:
          setWarningMessage(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setInAppBillingEnabled(paramCodedInputStreamMicro.readBool());
          break;
        case 72:
        }
        setInAppBillingMaxApiVersion(paramCodedInputStreamMicro.readInt32());
      }
    }

    public GetMarketMetadataResponseProto setBillingEventsEnabled(boolean paramBoolean)
    {
      this.hasBillingEventsEnabled = true;
      this.billingEventsEnabled_ = paramBoolean;
      return this;
    }

    public GetMarketMetadataResponseProto setCommentPostEnabled(boolean paramBoolean)
    {
      this.hasCommentPostEnabled = true;
      this.commentPostEnabled_ = paramBoolean;
      return this;
    }

    public GetMarketMetadataResponseProto setInAppBillingEnabled(boolean paramBoolean)
    {
      this.hasInAppBillingEnabled = true;
      this.inAppBillingEnabled_ = paramBoolean;
      return this;
    }

    public GetMarketMetadataResponseProto setInAppBillingMaxApiVersion(int paramInt)
    {
      this.hasInAppBillingMaxApiVersion = true;
      this.inAppBillingMaxApiVersion_ = paramInt;
      return this;
    }

    public GetMarketMetadataResponseProto setLatestClientUrl(String paramString)
    {
      this.hasLatestClientUrl = true;
      this.latestClientUrl_ = paramString;
      return this;
    }

    public GetMarketMetadataResponseProto setLatestClientVersionCode(int paramInt)
    {
      this.hasLatestClientVersionCode = true;
      this.latestClientVersionCode_ = paramInt;
      return this;
    }

    public GetMarketMetadataResponseProto setPaidAppsEnabled(boolean paramBoolean)
    {
      this.hasPaidAppsEnabled = true;
      this.paidAppsEnabled_ = paramBoolean;
      return this;
    }

    public GetMarketMetadataResponseProto setWarningMessage(String paramString)
    {
      this.hasWarningMessage = true;
      this.warningMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLatestClientVersionCode())
        paramCodedOutputStreamMicro.writeInt32(1, getLatestClientVersionCode());
      if (hasLatestClientUrl())
        paramCodedOutputStreamMicro.writeString(2, getLatestClientUrl());
      if (hasPaidAppsEnabled())
        paramCodedOutputStreamMicro.writeBool(3, getPaidAppsEnabled());
      Iterator localIterator = getBillingParameterList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (VendingProtos.BillingParameterProto)localIterator.next());
      if (hasCommentPostEnabled())
        paramCodedOutputStreamMicro.writeBool(5, getCommentPostEnabled());
      if (hasBillingEventsEnabled())
        paramCodedOutputStreamMicro.writeBool(6, getBillingEventsEnabled());
      if (hasWarningMessage())
        paramCodedOutputStreamMicro.writeString(7, getWarningMessage());
      if (hasInAppBillingEnabled())
        paramCodedOutputStreamMicro.writeBool(8, getInAppBillingEnabled());
      if (hasInAppBillingMaxApiVersion())
        paramCodedOutputStreamMicro.writeInt32(9, getInAppBillingMaxApiVersion());
    }
  }

  public static final class GetSubCategoriesRequestProto extends MessageMicro
  {
    private int assetType_ = 0;
    private int cachedSize = -1;
    private boolean hasAssetType;

    public int getAssetType()
    {
      return this.assetType_;
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
      if (hasAssetType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getAssetType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetType()
    {
      return this.hasAssetType;
    }

    public GetSubCategoriesRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setAssetType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public GetSubCategoriesRequestProto setAssetType(int paramInt)
    {
      this.hasAssetType = true;
      this.assetType_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetType())
        paramCodedOutputStreamMicro.writeInt32(1, getAssetType());
    }
  }

  public static final class GetSubCategoriesResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private List<SubCategory> subCategory_ = Collections.emptyList();

    public GetSubCategoriesResponseProto addSubCategory(SubCategory paramSubCategory)
    {
      if (paramSubCategory == null)
        throw new NullPointerException();
      if (this.subCategory_.isEmpty())
        this.subCategory_ = new ArrayList();
      this.subCategory_.add(paramSubCategory);
      return this;
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
      Iterator localIterator = getSubCategoryList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(1, (SubCategory)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public List<SubCategory> getSubCategoryList()
    {
      return this.subCategory_;
    }

    public GetSubCategoriesResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 11:
        }
        SubCategory localSubCategory = new SubCategory();
        paramCodedInputStreamMicro.readGroup(localSubCategory, 1);
        addSubCategory(localSubCategory);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getSubCategoryList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeGroup(1, (SubCategory)localIterator.next());
    }

    public static final class SubCategory extends MessageMicro
    {
      private int cachedSize = -1;
      private boolean hasSubCategoryDisplay;
      private boolean hasSubCategoryId;
      private String subCategoryDisplay_ = "";
      private String subCategoryId_ = "";

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasSubCategoryDisplay())
          i = 0 + CodedOutputStreamMicro.computeStringSize(2, getSubCategoryDisplay());
        if (hasSubCategoryId())
          i += CodedOutputStreamMicro.computeStringSize(3, getSubCategoryId());
        this.cachedSize = i;
        return i;
      }

      public String getSubCategoryDisplay()
      {
        return this.subCategoryDisplay_;
      }

      public String getSubCategoryId()
      {
        return this.subCategoryId_;
      }

      public boolean hasSubCategoryDisplay()
      {
        return this.hasSubCategoryDisplay;
      }

      public boolean hasSubCategoryId()
      {
        return this.hasSubCategoryId;
      }

      public SubCategory mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 18:
            setSubCategoryDisplay(paramCodedInputStreamMicro.readString());
            break;
          case 26:
          }
          setSubCategoryId(paramCodedInputStreamMicro.readString());
        }
      }

      public SubCategory setSubCategoryDisplay(String paramString)
      {
        this.hasSubCategoryDisplay = true;
        this.subCategoryDisplay_ = paramString;
        return this;
      }

      public SubCategory setSubCategoryId(String paramString)
      {
        this.hasSubCategoryId = true;
        this.subCategoryId_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasSubCategoryDisplay())
          paramCodedOutputStreamMicro.writeString(2, getSubCategoryDisplay());
        if (hasSubCategoryId())
          paramCodedOutputStreamMicro.writeString(3, getSubCategoryId());
      }
    }
  }

  public static final class InAppPurchaseInformationRequestProto extends MessageMicro
  {
    private int billingApiVersion_ = 0;
    private int cachedSize = -1;
    private boolean hasBillingApiVersion;
    private boolean hasNonce;
    private boolean hasSignatureAlgorithm;
    private boolean hasSignatureHash;
    private long nonce_ = 0L;
    private List<String> notificationId_ = Collections.emptyList();
    private String signatureAlgorithm_ = "";
    private VendingProtos.SignatureHashProto signatureHash_ = null;

    public InAppPurchaseInformationRequestProto addNotificationId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.notificationId_.isEmpty())
        this.notificationId_ = new ArrayList();
      this.notificationId_.add(paramString);
      return this;
    }

    public int getBillingApiVersion()
    {
      return this.billingApiVersion_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getNonce()
    {
      return this.nonce_;
    }

    public List<String> getNotificationIdList()
    {
      return this.notificationId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignatureHash())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSignatureHash());
      if (hasNonce())
        i += CodedOutputStreamMicro.computeInt64Size(2, getNonce());
      int j = 0;
      Iterator localIterator = getNotificationIdList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 1 * getNotificationIdList().size();
      if (hasSignatureAlgorithm())
        k += CodedOutputStreamMicro.computeStringSize(4, getSignatureAlgorithm());
      if (hasBillingApiVersion())
        k += CodedOutputStreamMicro.computeInt32Size(5, getBillingApiVersion());
      this.cachedSize = k;
      return k;
    }

    public String getSignatureAlgorithm()
    {
      return this.signatureAlgorithm_;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean hasBillingApiVersion()
    {
      return this.hasBillingApiVersion;
    }

    public boolean hasNonce()
    {
      return this.hasNonce;
    }

    public boolean hasSignatureAlgorithm()
    {
      return this.hasSignatureAlgorithm;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public InAppPurchaseInformationRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
          paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
          setSignatureHash(localSignatureHashProto);
          break;
        case 16:
          setNonce(paramCodedInputStreamMicro.readInt64());
          break;
        case 26:
          addNotificationId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setSignatureAlgorithm(paramCodedInputStreamMicro.readString());
          break;
        case 40:
        }
        setBillingApiVersion(paramCodedInputStreamMicro.readInt32());
      }
    }

    public InAppPurchaseInformationRequestProto setBillingApiVersion(int paramInt)
    {
      this.hasBillingApiVersion = true;
      this.billingApiVersion_ = paramInt;
      return this;
    }

    public InAppPurchaseInformationRequestProto setNonce(long paramLong)
    {
      this.hasNonce = true;
      this.nonce_ = paramLong;
      return this;
    }

    public InAppPurchaseInformationRequestProto setSignatureAlgorithm(String paramString)
    {
      this.hasSignatureAlgorithm = true;
      this.signatureAlgorithm_ = paramString;
      return this;
    }

    public InAppPurchaseInformationRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(1, getSignatureHash());
      if (hasNonce())
        paramCodedOutputStreamMicro.writeInt64(2, getNonce());
      Iterator localIterator = getNotificationIdList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator.next());
      if (hasSignatureAlgorithm())
        paramCodedOutputStreamMicro.writeString(4, getSignatureAlgorithm());
      if (hasBillingApiVersion())
        paramCodedOutputStreamMicro.writeInt32(5, getBillingApiVersion());
    }
  }

  public static final class InAppPurchaseInformationResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPurchaseResult;
    private boolean hasSignedResponse;
    private VendingProtos.PurchaseResultProto purchaseResult_ = null;
    private VendingProtos.SignedDataProto signedResponse_ = null;
    private List<VendingProtos.StatusBarNotificationProto> statusBarNotification_ = Collections.emptyList();

    public InAppPurchaseInformationResponseProto addStatusBarNotification(VendingProtos.StatusBarNotificationProto paramStatusBarNotificationProto)
    {
      if (paramStatusBarNotificationProto == null)
        throw new NullPointerException();
      if (this.statusBarNotification_.isEmpty())
        this.statusBarNotification_ = new ArrayList();
      this.statusBarNotification_.add(paramStatusBarNotificationProto);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.PurchaseResultProto getPurchaseResult()
    {
      return this.purchaseResult_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignedResponse())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSignedResponse());
      Iterator localIterator = getStatusBarNotificationList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(2, (VendingProtos.StatusBarNotificationProto)localIterator.next());
      if (hasPurchaseResult())
        i += CodedOutputStreamMicro.computeMessageSize(3, getPurchaseResult());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.SignedDataProto getSignedResponse()
    {
      return this.signedResponse_;
    }

    public List<VendingProtos.StatusBarNotificationProto> getStatusBarNotificationList()
    {
      return this.statusBarNotification_;
    }

    public boolean hasPurchaseResult()
    {
      return this.hasPurchaseResult;
    }

    public boolean hasSignedResponse()
    {
      return this.hasSignedResponse;
    }

    public InAppPurchaseInformationResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.SignedDataProto localSignedDataProto = new VendingProtos.SignedDataProto();
          paramCodedInputStreamMicro.readMessage(localSignedDataProto);
          setSignedResponse(localSignedDataProto);
          break;
        case 18:
          VendingProtos.StatusBarNotificationProto localStatusBarNotificationProto = new VendingProtos.StatusBarNotificationProto();
          paramCodedInputStreamMicro.readMessage(localStatusBarNotificationProto);
          addStatusBarNotification(localStatusBarNotificationProto);
          break;
        case 26:
        }
        VendingProtos.PurchaseResultProto localPurchaseResultProto = new VendingProtos.PurchaseResultProto();
        paramCodedInputStreamMicro.readMessage(localPurchaseResultProto);
        setPurchaseResult(localPurchaseResultProto);
      }
    }

    public InAppPurchaseInformationResponseProto setPurchaseResult(VendingProtos.PurchaseResultProto paramPurchaseResultProto)
    {
      if (paramPurchaseResultProto == null)
        throw new NullPointerException();
      this.hasPurchaseResult = true;
      this.purchaseResult_ = paramPurchaseResultProto;
      return this;
    }

    public InAppPurchaseInformationResponseProto setSignedResponse(VendingProtos.SignedDataProto paramSignedDataProto)
    {
      if (paramSignedDataProto == null)
        throw new NullPointerException();
      this.hasSignedResponse = true;
      this.signedResponse_ = paramSignedDataProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignedResponse())
        paramCodedOutputStreamMicro.writeMessage(1, getSignedResponse());
      Iterator localIterator = getStatusBarNotificationList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(2, (VendingProtos.StatusBarNotificationProto)localIterator.next());
      if (hasPurchaseResult())
        paramCodedOutputStreamMicro.writeMessage(3, getPurchaseResult());
    }
  }

  public static final class InAppRestoreTransactionsRequestProto extends MessageMicro
  {
    private int billingApiVersion_ = 0;
    private int cachedSize = -1;
    private boolean hasBillingApiVersion;
    private boolean hasNonce;
    private boolean hasSignatureAlgorithm;
    private boolean hasSignatureHash;
    private long nonce_ = 0L;
    private String signatureAlgorithm_ = "";
    private VendingProtos.SignatureHashProto signatureHash_ = null;

    public int getBillingApiVersion()
    {
      return this.billingApiVersion_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getNonce()
    {
      return this.nonce_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignatureHash())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSignatureHash());
      if (hasNonce())
        i += CodedOutputStreamMicro.computeInt64Size(2, getNonce());
      if (hasSignatureAlgorithm())
        i += CodedOutputStreamMicro.computeStringSize(3, getSignatureAlgorithm());
      if (hasBillingApiVersion())
        i += CodedOutputStreamMicro.computeInt32Size(4, getBillingApiVersion());
      this.cachedSize = i;
      return i;
    }

    public String getSignatureAlgorithm()
    {
      return this.signatureAlgorithm_;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean hasBillingApiVersion()
    {
      return this.hasBillingApiVersion;
    }

    public boolean hasNonce()
    {
      return this.hasNonce;
    }

    public boolean hasSignatureAlgorithm()
    {
      return this.hasSignatureAlgorithm;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public InAppRestoreTransactionsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
          paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
          setSignatureHash(localSignatureHashProto);
          break;
        case 16:
          setNonce(paramCodedInputStreamMicro.readInt64());
          break;
        case 26:
          setSignatureAlgorithm(paramCodedInputStreamMicro.readString());
          break;
        case 32:
        }
        setBillingApiVersion(paramCodedInputStreamMicro.readInt32());
      }
    }

    public InAppRestoreTransactionsRequestProto setBillingApiVersion(int paramInt)
    {
      this.hasBillingApiVersion = true;
      this.billingApiVersion_ = paramInt;
      return this;
    }

    public InAppRestoreTransactionsRequestProto setNonce(long paramLong)
    {
      this.hasNonce = true;
      this.nonce_ = paramLong;
      return this;
    }

    public InAppRestoreTransactionsRequestProto setSignatureAlgorithm(String paramString)
    {
      this.hasSignatureAlgorithm = true;
      this.signatureAlgorithm_ = paramString;
      return this;
    }

    public InAppRestoreTransactionsRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(1, getSignatureHash());
      if (hasNonce())
        paramCodedOutputStreamMicro.writeInt64(2, getNonce());
      if (hasSignatureAlgorithm())
        paramCodedOutputStreamMicro.writeString(3, getSignatureAlgorithm());
      if (hasBillingApiVersion())
        paramCodedOutputStreamMicro.writeInt32(4, getBillingApiVersion());
    }
  }

  public static final class InAppRestoreTransactionsResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPurchaseResult;
    private boolean hasSignedResponse;
    private VendingProtos.PurchaseResultProto purchaseResult_ = null;
    private VendingProtos.SignedDataProto signedResponse_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.PurchaseResultProto getPurchaseResult()
    {
      return this.purchaseResult_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignedResponse())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSignedResponse());
      if (hasPurchaseResult())
        i += CodedOutputStreamMicro.computeMessageSize(2, getPurchaseResult());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.SignedDataProto getSignedResponse()
    {
      return this.signedResponse_;
    }

    public boolean hasPurchaseResult()
    {
      return this.hasPurchaseResult;
    }

    public boolean hasSignedResponse()
    {
      return this.hasSignedResponse;
    }

    public InAppRestoreTransactionsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.SignedDataProto localSignedDataProto = new VendingProtos.SignedDataProto();
          paramCodedInputStreamMicro.readMessage(localSignedDataProto);
          setSignedResponse(localSignedDataProto);
          break;
        case 18:
        }
        VendingProtos.PurchaseResultProto localPurchaseResultProto = new VendingProtos.PurchaseResultProto();
        paramCodedInputStreamMicro.readMessage(localPurchaseResultProto);
        setPurchaseResult(localPurchaseResultProto);
      }
    }

    public InAppRestoreTransactionsResponseProto setPurchaseResult(VendingProtos.PurchaseResultProto paramPurchaseResultProto)
    {
      if (paramPurchaseResultProto == null)
        throw new NullPointerException();
      this.hasPurchaseResult = true;
      this.purchaseResult_ = paramPurchaseResultProto;
      return this;
    }

    public InAppRestoreTransactionsResponseProto setSignedResponse(VendingProtos.SignedDataProto paramSignedDataProto)
    {
      if (paramSignedDataProto == null)
        throw new NullPointerException();
      this.hasSignedResponse = true;
      this.signedResponse_ = paramSignedDataProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignedResponse())
        paramCodedOutputStreamMicro.writeMessage(1, getSignedResponse());
      if (hasPurchaseResult())
        paramCodedOutputStreamMicro.writeMessage(2, getPurchaseResult());
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

  public static final class ModifyCommentRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private VendingProtos.ExternalCommentProto comment_ = null;
    private boolean deleteComment_ = false;
    private boolean flagAsset_ = false;
    private String flagMessage_ = "";
    private int flagType_ = 1;
    private boolean hasAssetId;
    private boolean hasComment;
    private boolean hasDeleteComment;
    private boolean hasFlagAsset;
    private boolean hasFlagMessage;
    private boolean hasFlagType;
    private boolean hasNonFlagFlow;
    private boolean nonFlagFlow_ = false;

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

    public VendingProtos.ExternalCommentProto getComment()
    {
      return this.comment_;
    }

    public boolean getDeleteComment()
    {
      return this.deleteComment_;
    }

    public boolean getFlagAsset()
    {
      return this.flagAsset_;
    }

    public String getFlagMessage()
    {
      return this.flagMessage_;
    }

    public int getFlagType()
    {
      return this.flagType_;
    }

    public boolean getNonFlagFlow()
    {
      return this.nonFlagFlow_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasComment())
        i += CodedOutputStreamMicro.computeMessageSize(2, getComment());
      if (hasDeleteComment())
        i += CodedOutputStreamMicro.computeBoolSize(3, getDeleteComment());
      if (hasFlagAsset())
        i += CodedOutputStreamMicro.computeBoolSize(4, getFlagAsset());
      if (hasFlagType())
        i += CodedOutputStreamMicro.computeInt32Size(5, getFlagType());
      if (hasFlagMessage())
        i += CodedOutputStreamMicro.computeStringSize(6, getFlagMessage());
      if (hasNonFlagFlow())
        i += CodedOutputStreamMicro.computeBoolSize(7, getNonFlagFlow());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasComment()
    {
      return this.hasComment;
    }

    public boolean hasDeleteComment()
    {
      return this.hasDeleteComment;
    }

    public boolean hasFlagAsset()
    {
      return this.hasFlagAsset;
    }

    public boolean hasFlagMessage()
    {
      return this.hasFlagMessage;
    }

    public boolean hasFlagType()
    {
      return this.hasFlagType;
    }

    public boolean hasNonFlagFlow()
    {
      return this.hasNonFlagFlow;
    }

    public ModifyCommentRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          VendingProtos.ExternalCommentProto localExternalCommentProto = new VendingProtos.ExternalCommentProto();
          paramCodedInputStreamMicro.readMessage(localExternalCommentProto);
          setComment(localExternalCommentProto);
          break;
        case 24:
          setDeleteComment(paramCodedInputStreamMicro.readBool());
          break;
        case 32:
          setFlagAsset(paramCodedInputStreamMicro.readBool());
          break;
        case 40:
          setFlagType(paramCodedInputStreamMicro.readInt32());
          break;
        case 50:
          setFlagMessage(paramCodedInputStreamMicro.readString());
          break;
        case 56:
        }
        setNonFlagFlow(paramCodedInputStreamMicro.readBool());
      }
    }

    public ModifyCommentRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public ModifyCommentRequestProto setComment(VendingProtos.ExternalCommentProto paramExternalCommentProto)
    {
      if (paramExternalCommentProto == null)
        throw new NullPointerException();
      this.hasComment = true;
      this.comment_ = paramExternalCommentProto;
      return this;
    }

    public ModifyCommentRequestProto setDeleteComment(boolean paramBoolean)
    {
      this.hasDeleteComment = true;
      this.deleteComment_ = paramBoolean;
      return this;
    }

    public ModifyCommentRequestProto setFlagAsset(boolean paramBoolean)
    {
      this.hasFlagAsset = true;
      this.flagAsset_ = paramBoolean;
      return this;
    }

    public ModifyCommentRequestProto setFlagMessage(String paramString)
    {
      this.hasFlagMessage = true;
      this.flagMessage_ = paramString;
      return this;
    }

    public ModifyCommentRequestProto setFlagType(int paramInt)
    {
      this.hasFlagType = true;
      this.flagType_ = paramInt;
      return this;
    }

    public ModifyCommentRequestProto setNonFlagFlow(boolean paramBoolean)
    {
      this.hasNonFlagFlow = true;
      this.nonFlagFlow_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasComment())
        paramCodedOutputStreamMicro.writeMessage(2, getComment());
      if (hasDeleteComment())
        paramCodedOutputStreamMicro.writeBool(3, getDeleteComment());
      if (hasFlagAsset())
        paramCodedOutputStreamMicro.writeBool(4, getFlagAsset());
      if (hasFlagType())
        paramCodedOutputStreamMicro.writeInt32(5, getFlagType());
      if (hasFlagMessage())
        paramCodedOutputStreamMicro.writeString(6, getFlagMessage());
      if (hasNonFlagFlow())
        paramCodedOutputStreamMicro.writeBool(7, getNonFlagFlow());
    }
  }

  public static final class ModifyCommentResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public ModifyCommentResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class PaypalCountryInfoProto extends MessageMicro
  {
    private String billingAgreementText_ = "";
    private boolean birthDateRequired_ = false;
    private int cachedSize = -1;
    private boolean hasBillingAgreementText;
    private boolean hasBirthDateRequired;
    private boolean hasPreTosText;
    private boolean hasTosText;
    private String preTosText_ = "";
    private String tosText_ = "";

    public String getBillingAgreementText()
    {
      return this.billingAgreementText_;
    }

    public boolean getBirthDateRequired()
    {
      return this.birthDateRequired_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getPreTosText()
    {
      return this.preTosText_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBirthDateRequired())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getBirthDateRequired());
      if (hasTosText())
        i += CodedOutputStreamMicro.computeStringSize(2, getTosText());
      if (hasBillingAgreementText())
        i += CodedOutputStreamMicro.computeStringSize(3, getBillingAgreementText());
      if (hasPreTosText())
        i += CodedOutputStreamMicro.computeStringSize(4, getPreTosText());
      this.cachedSize = i;
      return i;
    }

    public String getTosText()
    {
      return this.tosText_;
    }

    public boolean hasBillingAgreementText()
    {
      return this.hasBillingAgreementText;
    }

    public boolean hasBirthDateRequired()
    {
      return this.hasBirthDateRequired;
    }

    public boolean hasPreTosText()
    {
      return this.hasPreTosText;
    }

    public boolean hasTosText()
    {
      return this.hasTosText;
    }

    public PaypalCountryInfoProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBirthDateRequired(paramCodedInputStreamMicro.readBool());
          break;
        case 18:
          setTosText(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setBillingAgreementText(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setPreTosText(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalCountryInfoProto setBillingAgreementText(String paramString)
    {
      this.hasBillingAgreementText = true;
      this.billingAgreementText_ = paramString;
      return this;
    }

    public PaypalCountryInfoProto setBirthDateRequired(boolean paramBoolean)
    {
      this.hasBirthDateRequired = true;
      this.birthDateRequired_ = paramBoolean;
      return this;
    }

    public PaypalCountryInfoProto setPreTosText(String paramString)
    {
      this.hasPreTosText = true;
      this.preTosText_ = paramString;
      return this;
    }

    public PaypalCountryInfoProto setTosText(String paramString)
    {
      this.hasTosText = true;
      this.tosText_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBirthDateRequired())
        paramCodedOutputStreamMicro.writeBool(1, getBirthDateRequired());
      if (hasTosText())
        paramCodedOutputStreamMicro.writeString(2, getTosText());
      if (hasBillingAgreementText())
        paramCodedOutputStreamMicro.writeString(3, getBillingAgreementText());
      if (hasPreTosText())
        paramCodedOutputStreamMicro.writeString(4, getPreTosText());
    }
  }

  public static final class PaypalCreateAccountRequestProto extends MessageMicro
  {
    private VendingProtos.AddressProto address_ = null;
    private String birthDate_ = "";
    private int cachedSize = -1;
    private String firstName_ = "";
    private boolean hasAddress;
    private boolean hasBirthDate;
    private boolean hasFirstName;
    private boolean hasLastName;
    private String lastName_ = "";

    public VendingProtos.AddressProto getAddress()
    {
      return this.address_;
    }

    public String getBirthDate()
    {
      return this.birthDate_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getFirstName()
    {
      return this.firstName_;
    }

    public String getLastName()
    {
      return this.lastName_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasFirstName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getFirstName());
      if (hasLastName())
        i += CodedOutputStreamMicro.computeStringSize(2, getLastName());
      if (hasAddress())
        i += CodedOutputStreamMicro.computeMessageSize(3, getAddress());
      if (hasBirthDate())
        i += CodedOutputStreamMicro.computeStringSize(4, getBirthDate());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public boolean hasBirthDate()
    {
      return this.hasBirthDate;
    }

    public boolean hasFirstName()
    {
      return this.hasFirstName;
    }

    public boolean hasLastName()
    {
      return this.hasLastName;
    }

    public PaypalCreateAccountRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setFirstName(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setLastName(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          VendingProtos.AddressProto localAddressProto = new VendingProtos.AddressProto();
          paramCodedInputStreamMicro.readMessage(localAddressProto);
          setAddress(localAddressProto);
          break;
        case 34:
        }
        setBirthDate(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalCreateAccountRequestProto setAddress(VendingProtos.AddressProto paramAddressProto)
    {
      if (paramAddressProto == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddressProto;
      return this;
    }

    public PaypalCreateAccountRequestProto setBirthDate(String paramString)
    {
      this.hasBirthDate = true;
      this.birthDate_ = paramString;
      return this;
    }

    public PaypalCreateAccountRequestProto setFirstName(String paramString)
    {
      this.hasFirstName = true;
      this.firstName_ = paramString;
      return this;
    }

    public PaypalCreateAccountRequestProto setLastName(String paramString)
    {
      this.hasLastName = true;
      this.lastName_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasFirstName())
        paramCodedOutputStreamMicro.writeString(1, getFirstName());
      if (hasLastName())
        paramCodedOutputStreamMicro.writeString(2, getLastName());
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(3, getAddress());
      if (hasBirthDate())
        paramCodedOutputStreamMicro.writeString(4, getBirthDate());
    }
  }

  public static final class PaypalCreateAccountResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private String createAccountKey_ = "";
    private boolean hasCreateAccountKey;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCreateAccountKey()
    {
      return this.createAccountKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCreateAccountKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getCreateAccountKey());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCreateAccountKey()
    {
      return this.hasCreateAccountKey;
    }

    public PaypalCreateAccountResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setCreateAccountKey(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalCreateAccountResponseProto setCreateAccountKey(String paramString)
    {
      this.hasCreateAccountKey = true;
      this.createAccountKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCreateAccountKey())
        paramCodedOutputStreamMicro.writeString(1, getCreateAccountKey());
    }
  }

  public static final class PaypalCredentialsProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPaypalEmail;
    private boolean hasPreapprovalKey;
    private String paypalEmail_ = "";
    private String preapprovalKey_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getPaypalEmail()
    {
      return this.paypalEmail_;
    }

    public String getPreapprovalKey()
    {
      return this.preapprovalKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPreapprovalKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPreapprovalKey());
      if (hasPaypalEmail())
        i += CodedOutputStreamMicro.computeStringSize(2, getPaypalEmail());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPaypalEmail()
    {
      return this.hasPaypalEmail;
    }

    public boolean hasPreapprovalKey()
    {
      return this.hasPreapprovalKey;
    }

    public PaypalCredentialsProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPreapprovalKey(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setPaypalEmail(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalCredentialsProto setPaypalEmail(String paramString)
    {
      this.hasPaypalEmail = true;
      this.paypalEmail_ = paramString;
      return this;
    }

    public PaypalCredentialsProto setPreapprovalKey(String paramString)
    {
      this.hasPreapprovalKey = true;
      this.preapprovalKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPreapprovalKey())
        paramCodedOutputStreamMicro.writeString(1, getPreapprovalKey());
      if (hasPaypalEmail())
        paramCodedOutputStreamMicro.writeString(2, getPaypalEmail());
    }
  }

  public static final class PaypalMassageAddressRequestProto extends MessageMicro
  {
    private VendingProtos.AddressProto address_ = null;
    private int cachedSize = -1;
    private boolean hasAddress;

    public VendingProtos.AddressProto getAddress()
    {
      return this.address_;
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
      if (hasAddress())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAddress());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public PaypalMassageAddressRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.AddressProto localAddressProto = new VendingProtos.AddressProto();
        paramCodedInputStreamMicro.readMessage(localAddressProto);
        setAddress(localAddressProto);
      }
    }

    public PaypalMassageAddressRequestProto setAddress(VendingProtos.AddressProto paramAddressProto)
    {
      if (paramAddressProto == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddressProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(1, getAddress());
    }
  }

  public static final class PaypalMassageAddressResponseProto extends MessageMicro
  {
    private VendingProtos.AddressProto address_ = null;
    private int cachedSize = -1;
    private boolean hasAddress;

    public VendingProtos.AddressProto getAddress()
    {
      return this.address_;
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
      if (hasAddress())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAddress());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public PaypalMassageAddressResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.AddressProto localAddressProto = new VendingProtos.AddressProto();
        paramCodedInputStreamMicro.readMessage(localAddressProto);
        setAddress(localAddressProto);
      }
    }

    public PaypalMassageAddressResponseProto setAddress(VendingProtos.AddressProto paramAddressProto)
    {
      if (paramAddressProto == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddressProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(1, getAddress());
    }
  }

  public static final class PaypalPreapprovalCredentialsRequestProto extends MessageMicro
  {
    private String billingInstrumentId_ = "";
    private int cachedSize = -1;
    private String gaiaAuthToken_ = "";
    private boolean hasBillingInstrumentId;
    private boolean hasGaiaAuthToken;

    public String getBillingInstrumentId()
    {
      return this.billingInstrumentId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getGaiaAuthToken()
    {
      return this.gaiaAuthToken_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGaiaAuthToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getGaiaAuthToken());
      if (hasBillingInstrumentId())
        i += CodedOutputStreamMicro.computeStringSize(2, getBillingInstrumentId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBillingInstrumentId()
    {
      return this.hasBillingInstrumentId;
    }

    public boolean hasGaiaAuthToken()
    {
      return this.hasGaiaAuthToken;
    }

    public PaypalPreapprovalCredentialsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGaiaAuthToken(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setBillingInstrumentId(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalPreapprovalCredentialsRequestProto setBillingInstrumentId(String paramString)
    {
      this.hasBillingInstrumentId = true;
      this.billingInstrumentId_ = paramString;
      return this;
    }

    public PaypalPreapprovalCredentialsRequestProto setGaiaAuthToken(String paramString)
    {
      this.hasGaiaAuthToken = true;
      this.gaiaAuthToken_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGaiaAuthToken())
        paramCodedOutputStreamMicro.writeString(1, getGaiaAuthToken());
      if (hasBillingInstrumentId())
        paramCodedOutputStreamMicro.writeString(2, getBillingInstrumentId());
    }
  }

  public static final class PaypalPreapprovalCredentialsResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPaypalAccountKey;
    private boolean hasPaypalEmail;
    private boolean hasResultCode;
    private String paypalAccountKey_ = "";
    private String paypalEmail_ = "";
    private int resultCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getPaypalAccountKey()
    {
      return this.paypalAccountKey_;
    }

    public String getPaypalEmail()
    {
      return this.paypalEmail_;
    }

    public int getResultCode()
    {
      return this.resultCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResultCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResultCode());
      if (hasPaypalAccountKey())
        i += CodedOutputStreamMicro.computeStringSize(2, getPaypalAccountKey());
      if (hasPaypalEmail())
        i += CodedOutputStreamMicro.computeStringSize(3, getPaypalEmail());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPaypalAccountKey()
    {
      return this.hasPaypalAccountKey;
    }

    public boolean hasPaypalEmail()
    {
      return this.hasPaypalEmail;
    }

    public boolean hasResultCode()
    {
      return this.hasResultCode;
    }

    public PaypalPreapprovalCredentialsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResultCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setPaypalAccountKey(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setPaypalEmail(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalPreapprovalCredentialsResponseProto setPaypalAccountKey(String paramString)
    {
      this.hasPaypalAccountKey = true;
      this.paypalAccountKey_ = paramString;
      return this;
    }

    public PaypalPreapprovalCredentialsResponseProto setPaypalEmail(String paramString)
    {
      this.hasPaypalEmail = true;
      this.paypalEmail_ = paramString;
      return this;
    }

    public PaypalPreapprovalCredentialsResponseProto setResultCode(int paramInt)
    {
      this.hasResultCode = true;
      this.resultCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResultCode())
        paramCodedOutputStreamMicro.writeInt32(1, getResultCode());
      if (hasPaypalAccountKey())
        paramCodedOutputStreamMicro.writeString(2, getPaypalAccountKey());
      if (hasPaypalEmail())
        paramCodedOutputStreamMicro.writeString(3, getPaypalEmail());
    }
  }

  public static final class PaypalPreapprovalDetailsRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean getAddress_ = true;
    private boolean hasGetAddress;
    private boolean hasPreapprovalKey;
    private String preapprovalKey_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getGetAddress()
    {
      return this.getAddress_;
    }

    public String getPreapprovalKey()
    {
      return this.preapprovalKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGetAddress())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getGetAddress());
      if (hasPreapprovalKey())
        i += CodedOutputStreamMicro.computeStringSize(2, getPreapprovalKey());
      this.cachedSize = i;
      return i;
    }

    public boolean hasGetAddress()
    {
      return this.hasGetAddress;
    }

    public boolean hasPreapprovalKey()
    {
      return this.hasPreapprovalKey;
    }

    public PaypalPreapprovalDetailsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGetAddress(paramCodedInputStreamMicro.readBool());
          break;
        case 18:
        }
        setPreapprovalKey(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalPreapprovalDetailsRequestProto setGetAddress(boolean paramBoolean)
    {
      this.hasGetAddress = true;
      this.getAddress_ = paramBoolean;
      return this;
    }

    public PaypalPreapprovalDetailsRequestProto setPreapprovalKey(String paramString)
    {
      this.hasPreapprovalKey = true;
      this.preapprovalKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGetAddress())
        paramCodedOutputStreamMicro.writeBool(1, getGetAddress());
      if (hasPreapprovalKey())
        paramCodedOutputStreamMicro.writeString(2, getPreapprovalKey());
    }
  }

  public static final class PaypalPreapprovalDetailsResponseProto extends MessageMicro
  {
    private VendingProtos.AddressProto address_ = null;
    private int cachedSize = -1;
    private boolean hasAddress;
    private boolean hasPaypalEmail;
    private String paypalEmail_ = "";

    public VendingProtos.AddressProto getAddress()
    {
      return this.address_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getPaypalEmail()
    {
      return this.paypalEmail_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPaypalEmail())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPaypalEmail());
      if (hasAddress())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAddress());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAddress()
    {
      return this.hasAddress;
    }

    public boolean hasPaypalEmail()
    {
      return this.hasPaypalEmail;
    }

    public PaypalPreapprovalDetailsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPaypalEmail(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        VendingProtos.AddressProto localAddressProto = new VendingProtos.AddressProto();
        paramCodedInputStreamMicro.readMessage(localAddressProto);
        setAddress(localAddressProto);
      }
    }

    public PaypalPreapprovalDetailsResponseProto setAddress(VendingProtos.AddressProto paramAddressProto)
    {
      if (paramAddressProto == null)
        throw new NullPointerException();
      this.hasAddress = true;
      this.address_ = paramAddressProto;
      return this;
    }

    public PaypalPreapprovalDetailsResponseProto setPaypalEmail(String paramString)
    {
      this.hasPaypalEmail = true;
      this.paypalEmail_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPaypalEmail())
        paramCodedOutputStreamMicro.writeString(1, getPaypalEmail());
      if (hasAddress())
        paramCodedOutputStreamMicro.writeMessage(2, getAddress());
    }
  }

  public static final class PaypalPreapprovalRequestProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public PaypalPreapprovalRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class PaypalPreapprovalResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPreapprovalKey;
    private String preapprovalKey_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getPreapprovalKey()
    {
      return this.preapprovalKey_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPreapprovalKey())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPreapprovalKey());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPreapprovalKey()
    {
      return this.hasPreapprovalKey;
    }

    public PaypalPreapprovalResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setPreapprovalKey(paramCodedInputStreamMicro.readString());
      }
    }

    public PaypalPreapprovalResponseProto setPreapprovalKey(String paramString)
    {
      this.hasPreapprovalKey = true;
      this.preapprovalKey_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPreapprovalKey())
        paramCodedOutputStreamMicro.writeString(1, getPreapprovalKey());
    }
  }

  public static final class PendingNotificationsProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasNextCheckMillis;
    private long nextCheckMillis_ = 0L;
    private List<VendingProtos.DataMessageProto> notification_ = Collections.emptyList();

    public PendingNotificationsProto addNotification(VendingProtos.DataMessageProto paramDataMessageProto)
    {
      if (paramDataMessageProto == null)
        throw new NullPointerException();
      if (this.notification_.isEmpty())
        this.notification_ = new ArrayList();
      this.notification_.add(paramDataMessageProto);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public long getNextCheckMillis()
    {
      return this.nextCheckMillis_;
    }

    public List<VendingProtos.DataMessageProto> getNotificationList()
    {
      return this.notification_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getNotificationList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.DataMessageProto)localIterator.next());
      if (hasNextCheckMillis())
        i += CodedOutputStreamMicro.computeInt64Size(2, getNextCheckMillis());
      this.cachedSize = i;
      return i;
    }

    public boolean hasNextCheckMillis()
    {
      return this.hasNextCheckMillis;
    }

    public PendingNotificationsProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.DataMessageProto localDataMessageProto = new VendingProtos.DataMessageProto();
          paramCodedInputStreamMicro.readMessage(localDataMessageProto);
          addNotification(localDataMessageProto);
          break;
        case 16:
        }
        setNextCheckMillis(paramCodedInputStreamMicro.readInt64());
      }
    }

    public PendingNotificationsProto setNextCheckMillis(long paramLong)
    {
      this.hasNextCheckMillis = true;
      this.nextCheckMillis_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getNotificationList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.DataMessageProto)localIterator.next());
      if (hasNextCheckMillis())
        paramCodedOutputStreamMicro.writeInt64(2, getNextCheckMillis());
    }
  }

  public static final class PrefetchedBundleProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasRequest;
    private boolean hasResponse;
    private VendingProtos.SingleRequestProto request_ = null;
    private VendingProtos.SingleResponseProto response_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.SingleRequestProto getRequest()
    {
      return this.request_;
    }

    public VendingProtos.SingleResponseProto getResponse()
    {
      return this.response_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRequest())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getRequest());
      if (hasResponse())
        i += CodedOutputStreamMicro.computeMessageSize(2, getResponse());
      this.cachedSize = i;
      return i;
    }

    public boolean hasRequest()
    {
      return this.hasRequest;
    }

    public boolean hasResponse()
    {
      return this.hasResponse;
    }

    public PrefetchedBundleProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.SingleRequestProto localSingleRequestProto = new VendingProtos.SingleRequestProto();
          paramCodedInputStreamMicro.readMessage(localSingleRequestProto);
          setRequest(localSingleRequestProto);
          break;
        case 18:
        }
        VendingProtos.SingleResponseProto localSingleResponseProto = new VendingProtos.SingleResponseProto();
        paramCodedInputStreamMicro.readMessage(localSingleResponseProto);
        setResponse(localSingleResponseProto);
      }
    }

    public PrefetchedBundleProto setRequest(VendingProtos.SingleRequestProto paramSingleRequestProto)
    {
      if (paramSingleRequestProto == null)
        throw new NullPointerException();
      this.hasRequest = true;
      this.request_ = paramSingleRequestProto;
      return this;
    }

    public PrefetchedBundleProto setResponse(VendingProtos.SingleResponseProto paramSingleResponseProto)
    {
      if (paramSingleResponseProto == null)
        throw new NullPointerException();
      this.hasResponse = true;
      this.response_ = paramSingleResponseProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRequest())
        paramCodedOutputStreamMicro.writeMessage(1, getRequest());
      if (hasResponse())
        paramCodedOutputStreamMicro.writeMessage(2, getResponse());
    }
  }

  public static final class PurchaseCartInfoProto extends MessageMicro
  {
    private int cachedSize = -1;
    private String footerMessage_ = "";
    private boolean hasFooterMessage;
    private boolean hasItemPrice;
    private boolean hasPriceCurrency;
    private boolean hasPriceMicros;
    private boolean hasTaxExclusive;
    private boolean hasTaxInclusive;
    private boolean hasTaxMessage;
    private boolean hasTotal;
    private String itemPrice_ = "";
    private String priceCurrency_ = "";
    private long priceMicros_ = 0L;
    private String taxExclusive_ = "";
    private String taxInclusive_ = "";
    private String taxMessage_ = "";
    private String total_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getFooterMessage()
    {
      return this.footerMessage_;
    }

    public String getItemPrice()
    {
      return this.itemPrice_;
    }

    public String getPriceCurrency()
    {
      return this.priceCurrency_;
    }

    public long getPriceMicros()
    {
      return this.priceMicros_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasItemPrice())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getItemPrice());
      if (hasTaxInclusive())
        i += CodedOutputStreamMicro.computeStringSize(2, getTaxInclusive());
      if (hasTaxExclusive())
        i += CodedOutputStreamMicro.computeStringSize(3, getTaxExclusive());
      if (hasTotal())
        i += CodedOutputStreamMicro.computeStringSize(4, getTotal());
      if (hasTaxMessage())
        i += CodedOutputStreamMicro.computeStringSize(5, getTaxMessage());
      if (hasFooterMessage())
        i += CodedOutputStreamMicro.computeStringSize(6, getFooterMessage());
      if (hasPriceCurrency())
        i += CodedOutputStreamMicro.computeStringSize(7, getPriceCurrency());
      if (hasPriceMicros())
        i += CodedOutputStreamMicro.computeInt64Size(8, getPriceMicros());
      this.cachedSize = i;
      return i;
    }

    public String getTaxExclusive()
    {
      return this.taxExclusive_;
    }

    public String getTaxInclusive()
    {
      return this.taxInclusive_;
    }

    public String getTaxMessage()
    {
      return this.taxMessage_;
    }

    public String getTotal()
    {
      return this.total_;
    }

    public boolean hasFooterMessage()
    {
      return this.hasFooterMessage;
    }

    public boolean hasItemPrice()
    {
      return this.hasItemPrice;
    }

    public boolean hasPriceCurrency()
    {
      return this.hasPriceCurrency;
    }

    public boolean hasPriceMicros()
    {
      return this.hasPriceMicros;
    }

    public boolean hasTaxExclusive()
    {
      return this.hasTaxExclusive;
    }

    public boolean hasTaxInclusive()
    {
      return this.hasTaxInclusive;
    }

    public boolean hasTaxMessage()
    {
      return this.hasTaxMessage;
    }

    public boolean hasTotal()
    {
      return this.hasTotal;
    }

    public PurchaseCartInfoProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setItemPrice(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setTaxInclusive(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setTaxExclusive(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setTotal(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setTaxMessage(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setFooterMessage(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setPriceCurrency(paramCodedInputStreamMicro.readString());
          break;
        case 64:
        }
        setPriceMicros(paramCodedInputStreamMicro.readInt64());
      }
    }

    public PurchaseCartInfoProto setFooterMessage(String paramString)
    {
      this.hasFooterMessage = true;
      this.footerMessage_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setItemPrice(String paramString)
    {
      this.hasItemPrice = true;
      this.itemPrice_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setPriceCurrency(String paramString)
    {
      this.hasPriceCurrency = true;
      this.priceCurrency_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setPriceMicros(long paramLong)
    {
      this.hasPriceMicros = true;
      this.priceMicros_ = paramLong;
      return this;
    }

    public PurchaseCartInfoProto setTaxExclusive(String paramString)
    {
      this.hasTaxExclusive = true;
      this.taxExclusive_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setTaxInclusive(String paramString)
    {
      this.hasTaxInclusive = true;
      this.taxInclusive_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setTaxMessage(String paramString)
    {
      this.hasTaxMessage = true;
      this.taxMessage_ = paramString;
      return this;
    }

    public PurchaseCartInfoProto setTotal(String paramString)
    {
      this.hasTotal = true;
      this.total_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasItemPrice())
        paramCodedOutputStreamMicro.writeString(1, getItemPrice());
      if (hasTaxInclusive())
        paramCodedOutputStreamMicro.writeString(2, getTaxInclusive());
      if (hasTaxExclusive())
        paramCodedOutputStreamMicro.writeString(3, getTaxExclusive());
      if (hasTotal())
        paramCodedOutputStreamMicro.writeString(4, getTotal());
      if (hasTaxMessage())
        paramCodedOutputStreamMicro.writeString(5, getTaxMessage());
      if (hasFooterMessage())
        paramCodedOutputStreamMicro.writeString(6, getFooterMessage());
      if (hasPriceCurrency())
        paramCodedOutputStreamMicro.writeString(7, getPriceCurrency());
      if (hasPriceMicros())
        paramCodedOutputStreamMicro.writeInt64(8, getPriceMicros());
    }
  }

  public static final class PurchaseInfoProto extends MessageMicro
  {
    private BillingInstruments billingInstruments_ = null;
    private int cachedSize = -1;
    private VendingProtos.PurchaseCartInfoProto cartInfo_ = null;
    private List<Integer> eligibleInstrumentTypes_ = Collections.emptyList();
    private List<Integer> errorInputFields_ = Collections.emptyList();
    private boolean hasBillingInstruments;
    private boolean hasCartInfo;
    private boolean hasOrderId;
    private boolean hasRefundPolicy;
    private boolean hasTransactionId;
    private boolean hasUserCanAddGdd;
    private String orderId_ = "";
    private String refundPolicy_ = "";
    private String transactionId_ = "";
    private boolean userCanAddGdd_ = false;

    public PurchaseInfoProto addEligibleInstrumentTypes(int paramInt)
    {
      if (this.eligibleInstrumentTypes_.isEmpty())
        this.eligibleInstrumentTypes_ = new ArrayList();
      this.eligibleInstrumentTypes_.add(Integer.valueOf(paramInt));
      return this;
    }

    public PurchaseInfoProto addErrorInputFields(int paramInt)
    {
      if (this.errorInputFields_.isEmpty())
        this.errorInputFields_ = new ArrayList();
      this.errorInputFields_.add(Integer.valueOf(paramInt));
      return this;
    }

    public BillingInstruments getBillingInstruments()
    {
      return this.billingInstruments_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.PurchaseCartInfoProto getCartInfo()
    {
      return this.cartInfo_;
    }

    public List<Integer> getEligibleInstrumentTypesList()
    {
      return this.eligibleInstrumentTypes_;
    }

    public List<Integer> getErrorInputFieldsList()
    {
      return this.errorInputFields_;
    }

    public String getOrderId()
    {
      return this.orderId_;
    }

    public String getRefundPolicy()
    {
      return this.refundPolicy_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTransactionId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTransactionId());
      if (hasCartInfo())
        i += CodedOutputStreamMicro.computeMessageSize(2, getCartInfo());
      if (hasBillingInstruments())
        i += CodedOutputStreamMicro.computeGroupSize(3, getBillingInstruments());
      int j = 0;
      Iterator localIterator1 = getErrorInputFieldsList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator1.next()).intValue());
      int k = i + j + 1 * getErrorInputFieldsList().size();
      if (hasRefundPolicy())
        k += CodedOutputStreamMicro.computeStringSize(10, getRefundPolicy());
      if (hasUserCanAddGdd())
        k += CodedOutputStreamMicro.computeBoolSize(12, getUserCanAddGdd());
      int m = 0;
      Iterator localIterator2 = getEligibleInstrumentTypesList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator2.next()).intValue());
      int n = k + m + 1 * getEligibleInstrumentTypesList().size();
      if (hasOrderId())
        n += CodedOutputStreamMicro.computeStringSize(15, getOrderId());
      this.cachedSize = n;
      return n;
    }

    public String getTransactionId()
    {
      return this.transactionId_;
    }

    public boolean getUserCanAddGdd()
    {
      return this.userCanAddGdd_;
    }

    public boolean hasBillingInstruments()
    {
      return this.hasBillingInstruments;
    }

    public boolean hasCartInfo()
    {
      return this.hasCartInfo;
    }

    public boolean hasOrderId()
    {
      return this.hasOrderId;
    }

    public boolean hasRefundPolicy()
    {
      return this.hasRefundPolicy;
    }

    public boolean hasTransactionId()
    {
      return this.hasTransactionId;
    }

    public boolean hasUserCanAddGdd()
    {
      return this.hasUserCanAddGdd;
    }

    public PurchaseInfoProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setTransactionId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          VendingProtos.PurchaseCartInfoProto localPurchaseCartInfoProto = new VendingProtos.PurchaseCartInfoProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseCartInfoProto);
          setCartInfo(localPurchaseCartInfoProto);
          break;
        case 27:
          BillingInstruments localBillingInstruments = new BillingInstruments();
          paramCodedInputStreamMicro.readGroup(localBillingInstruments, 3);
          setBillingInstruments(localBillingInstruments);
          break;
        case 72:
          addErrorInputFields(paramCodedInputStreamMicro.readInt32());
          break;
        case 82:
          setRefundPolicy(paramCodedInputStreamMicro.readString());
          break;
        case 96:
          setUserCanAddGdd(paramCodedInputStreamMicro.readBool());
          break;
        case 104:
          addEligibleInstrumentTypes(paramCodedInputStreamMicro.readInt32());
          break;
        case 122:
        }
        setOrderId(paramCodedInputStreamMicro.readString());
      }
    }

    public PurchaseInfoProto setBillingInstruments(BillingInstruments paramBillingInstruments)
    {
      if (paramBillingInstruments == null)
        throw new NullPointerException();
      this.hasBillingInstruments = true;
      this.billingInstruments_ = paramBillingInstruments;
      return this;
    }

    public PurchaseInfoProto setCartInfo(VendingProtos.PurchaseCartInfoProto paramPurchaseCartInfoProto)
    {
      if (paramPurchaseCartInfoProto == null)
        throw new NullPointerException();
      this.hasCartInfo = true;
      this.cartInfo_ = paramPurchaseCartInfoProto;
      return this;
    }

    public PurchaseInfoProto setOrderId(String paramString)
    {
      this.hasOrderId = true;
      this.orderId_ = paramString;
      return this;
    }

    public PurchaseInfoProto setRefundPolicy(String paramString)
    {
      this.hasRefundPolicy = true;
      this.refundPolicy_ = paramString;
      return this;
    }

    public PurchaseInfoProto setTransactionId(String paramString)
    {
      this.hasTransactionId = true;
      this.transactionId_ = paramString;
      return this;
    }

    public PurchaseInfoProto setUserCanAddGdd(boolean paramBoolean)
    {
      this.hasUserCanAddGdd = true;
      this.userCanAddGdd_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasTransactionId())
        paramCodedOutputStreamMicro.writeString(1, getTransactionId());
      if (hasCartInfo())
        paramCodedOutputStreamMicro.writeMessage(2, getCartInfo());
      if (hasBillingInstruments())
        paramCodedOutputStreamMicro.writeGroup(3, getBillingInstruments());
      Iterator localIterator1 = getErrorInputFieldsList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeInt32(9, ((Integer)localIterator1.next()).intValue());
      if (hasRefundPolicy())
        paramCodedOutputStreamMicro.writeString(10, getRefundPolicy());
      if (hasUserCanAddGdd())
        paramCodedOutputStreamMicro.writeBool(12, getUserCanAddGdd());
      Iterator localIterator2 = getEligibleInstrumentTypesList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeInt32(13, ((Integer)localIterator2.next()).intValue());
      if (hasOrderId())
        paramCodedOutputStreamMicro.writeString(15, getOrderId());
    }

    public static final class BillingInstruments extends MessageMicro
    {
      private List<BillingInstrument> billingInstrument_ = Collections.emptyList();
      private int cachedSize = -1;
      private String defaultBillingInstrumentId_ = "";
      private boolean hasDefaultBillingInstrumentId;

      public BillingInstruments addBillingInstrument(BillingInstrument paramBillingInstrument)
      {
        if (paramBillingInstrument == null)
          throw new NullPointerException();
        if (this.billingInstrument_.isEmpty())
          this.billingInstrument_ = new ArrayList();
        this.billingInstrument_.add(paramBillingInstrument);
        return this;
      }

      public List<BillingInstrument> getBillingInstrumentList()
      {
        return this.billingInstrument_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public String getDefaultBillingInstrumentId()
      {
        return this.defaultBillingInstrumentId_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        Iterator localIterator = getBillingInstrumentList().iterator();
        while (localIterator.hasNext())
          i += CodedOutputStreamMicro.computeGroupSize(4, (BillingInstrument)localIterator.next());
        if (hasDefaultBillingInstrumentId())
          i += CodedOutputStreamMicro.computeStringSize(8, getDefaultBillingInstrumentId());
        this.cachedSize = i;
        return i;
      }

      public boolean hasDefaultBillingInstrumentId()
      {
        return this.hasDefaultBillingInstrumentId;
      }

      public BillingInstruments mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 35:
            BillingInstrument localBillingInstrument = new BillingInstrument();
            paramCodedInputStreamMicro.readGroup(localBillingInstrument, 4);
            addBillingInstrument(localBillingInstrument);
            break;
          case 66:
          }
          setDefaultBillingInstrumentId(paramCodedInputStreamMicro.readString());
        }
      }

      public BillingInstruments setDefaultBillingInstrumentId(String paramString)
      {
        this.hasDefaultBillingInstrumentId = true;
        this.defaultBillingInstrumentId_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        Iterator localIterator = getBillingInstrumentList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeGroup(4, (BillingInstrument)localIterator.next());
        if (hasDefaultBillingInstrumentId())
          paramCodedOutputStreamMicro.writeString(8, getDefaultBillingInstrumentId());
      }

      public static final class BillingInstrument extends MessageMicro
      {
        private int cachedSize = -1;
        private boolean hasId;
        private boolean hasInstrumentStatus;
        private boolean hasInstrumentType;
        private boolean hasIsInvalid;
        private boolean hasName;
        private String id_ = "";
        private int instrumentStatus_ = 0;
        private int instrumentType_ = 0;
        private boolean isInvalid_ = false;
        private String name_ = "";

        public int getCachedSize()
        {
          if (this.cachedSize < 0)
            getSerializedSize();
          return this.cachedSize;
        }

        public String getId()
        {
          return this.id_;
        }

        public int getInstrumentStatus()
        {
          return this.instrumentStatus_;
        }

        public int getInstrumentType()
        {
          return this.instrumentType_;
        }

        public boolean getIsInvalid()
        {
          return this.isInvalid_;
        }

        public String getName()
        {
          return this.name_;
        }

        public int getSerializedSize()
        {
          int i = 0;
          if (hasId())
            i = 0 + CodedOutputStreamMicro.computeStringSize(5, getId());
          if (hasName())
            i += CodedOutputStreamMicro.computeStringSize(6, getName());
          if (hasIsInvalid())
            i += CodedOutputStreamMicro.computeBoolSize(7, getIsInvalid());
          if (hasInstrumentType())
            i += CodedOutputStreamMicro.computeInt32Size(11, getInstrumentType());
          if (hasInstrumentStatus())
            i += CodedOutputStreamMicro.computeInt32Size(14, getInstrumentStatus());
          this.cachedSize = i;
          return i;
        }

        public boolean hasId()
        {
          return this.hasId;
        }

        public boolean hasInstrumentStatus()
        {
          return this.hasInstrumentStatus;
        }

        public boolean hasInstrumentType()
        {
          return this.hasInstrumentType;
        }

        public boolean hasIsInvalid()
        {
          return this.hasIsInvalid;
        }

        public boolean hasName()
        {
          return this.hasName;
        }

        public BillingInstrument mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            case 42:
              setId(paramCodedInputStreamMicro.readString());
              break;
            case 50:
              setName(paramCodedInputStreamMicro.readString());
              break;
            case 56:
              setIsInvalid(paramCodedInputStreamMicro.readBool());
              break;
            case 88:
              setInstrumentType(paramCodedInputStreamMicro.readInt32());
              break;
            case 112:
            }
            setInstrumentStatus(paramCodedInputStreamMicro.readInt32());
          }
        }

        public BillingInstrument setId(String paramString)
        {
          this.hasId = true;
          this.id_ = paramString;
          return this;
        }

        public BillingInstrument setInstrumentStatus(int paramInt)
        {
          this.hasInstrumentStatus = true;
          this.instrumentStatus_ = paramInt;
          return this;
        }

        public BillingInstrument setInstrumentType(int paramInt)
        {
          this.hasInstrumentType = true;
          this.instrumentType_ = paramInt;
          return this;
        }

        public BillingInstrument setIsInvalid(boolean paramBoolean)
        {
          this.hasIsInvalid = true;
          this.isInvalid_ = paramBoolean;
          return this;
        }

        public BillingInstrument setName(String paramString)
        {
          this.hasName = true;
          this.name_ = paramString;
          return this;
        }

        public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
          throws IOException
        {
          if (hasId())
            paramCodedOutputStreamMicro.writeString(5, getId());
          if (hasName())
            paramCodedOutputStreamMicro.writeString(6, getName());
          if (hasIsInvalid())
            paramCodedOutputStreamMicro.writeBool(7, getIsInvalid());
          if (hasInstrumentType())
            paramCodedOutputStreamMicro.writeInt32(11, getInstrumentType());
          if (hasInstrumentStatus())
            paramCodedOutputStreamMicro.writeInt32(14, getInstrumentStatus());
        }
      }
    }
  }

  public static final class PurchaseMetadataRequestProto extends MessageMicro
  {
    private int billingInstrumentType_ = 2;
    private int cachedSize = -1;
    private boolean deprecatedRetrieveBillingCountries_ = false;
    private boolean hasBillingInstrumentType;
    private boolean hasDeprecatedRetrieveBillingCountries;

    public int getBillingInstrumentType()
    {
      return this.billingInstrumentType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getDeprecatedRetrieveBillingCountries()
    {
      return this.deprecatedRetrieveBillingCountries_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDeprecatedRetrieveBillingCountries())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getDeprecatedRetrieveBillingCountries());
      if (hasBillingInstrumentType())
        i += CodedOutputStreamMicro.computeInt32Size(2, getBillingInstrumentType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBillingInstrumentType()
    {
      return this.hasBillingInstrumentType;
    }

    public boolean hasDeprecatedRetrieveBillingCountries()
    {
      return this.hasDeprecatedRetrieveBillingCountries;
    }

    public PurchaseMetadataRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDeprecatedRetrieveBillingCountries(paramCodedInputStreamMicro.readBool());
          break;
        case 16:
        }
        setBillingInstrumentType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public PurchaseMetadataRequestProto setBillingInstrumentType(int paramInt)
    {
      this.hasBillingInstrumentType = true;
      this.billingInstrumentType_ = paramInt;
      return this;
    }

    public PurchaseMetadataRequestProto setDeprecatedRetrieveBillingCountries(boolean paramBoolean)
    {
      this.hasDeprecatedRetrieveBillingCountries = true;
      this.deprecatedRetrieveBillingCountries_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDeprecatedRetrieveBillingCountries())
        paramCodedOutputStreamMicro.writeBool(1, getDeprecatedRetrieveBillingCountries());
      if (hasBillingInstrumentType())
        paramCodedOutputStreamMicro.writeInt32(2, getBillingInstrumentType());
    }
  }

  public static final class PurchaseMetadataResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private Countries countries_ = null;
    private boolean hasCountries;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public Countries getCountries()
    {
      return this.countries_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCountries())
        i = 0 + CodedOutputStreamMicro.computeGroupSize(1, getCountries());
      this.cachedSize = i;
      return i;
    }

    public boolean hasCountries()
    {
      return this.hasCountries;
    }

    public PurchaseMetadataResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 11:
        }
        Countries localCountries = new Countries();
        paramCodedInputStreamMicro.readGroup(localCountries, 1);
        setCountries(localCountries);
      }
    }

    public PurchaseMetadataResponseProto setCountries(Countries paramCountries)
    {
      if (paramCountries == null)
        throw new NullPointerException();
      this.hasCountries = true;
      this.countries_ = paramCountries;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCountries())
        paramCodedOutputStreamMicro.writeGroup(1, getCountries());
    }

    public static final class Countries extends MessageMicro
    {
      private int cachedSize = -1;
      private List<Country> country_ = Collections.emptyList();

      public Countries addCountry(Country paramCountry)
      {
        if (paramCountry == null)
          throw new NullPointerException();
        if (this.country_.isEmpty())
          this.country_ = new ArrayList();
        this.country_.add(paramCountry);
        return this;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public List<Country> getCountryList()
      {
        return this.country_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        Iterator localIterator = getCountryList().iterator();
        while (localIterator.hasNext())
          i += CodedOutputStreamMicro.computeGroupSize(2, (Country)localIterator.next());
        this.cachedSize = i;
        return i;
      }

      public Countries mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 19:
          }
          Country localCountry = new Country();
          paramCodedInputStreamMicro.readGroup(localCountry, 2);
          addCountry(localCountry);
        }
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        Iterator localIterator = getCountryList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeGroup(2, (Country)localIterator.next());
      }

      public static final class Country extends MessageMicro
      {
        private boolean allowsReducedBillingAddress_ = false;
        private int cachedSize = -1;
        private String countryCode_ = "";
        private String countryName_ = "";
        private boolean hasAllowsReducedBillingAddress;
        private boolean hasCountryCode;
        private boolean hasCountryName;
        private boolean hasPaypalCountryInfo;
        private List<InstrumentAddressSpec> instrumentAddressSpec_ = Collections.emptyList();
        private VendingProtos.PaypalCountryInfoProto paypalCountryInfo_ = null;

        public Country addInstrumentAddressSpec(InstrumentAddressSpec paramInstrumentAddressSpec)
        {
          if (paramInstrumentAddressSpec == null)
            throw new NullPointerException();
          if (this.instrumentAddressSpec_.isEmpty())
            this.instrumentAddressSpec_ = new ArrayList();
          this.instrumentAddressSpec_.add(paramInstrumentAddressSpec);
          return this;
        }

        public boolean getAllowsReducedBillingAddress()
        {
          return this.allowsReducedBillingAddress_;
        }

        public int getCachedSize()
        {
          if (this.cachedSize < 0)
            getSerializedSize();
          return this.cachedSize;
        }

        public String getCountryCode()
        {
          return this.countryCode_;
        }

        public String getCountryName()
        {
          return this.countryName_;
        }

        public List<InstrumentAddressSpec> getInstrumentAddressSpecList()
        {
          return this.instrumentAddressSpec_;
        }

        public VendingProtos.PaypalCountryInfoProto getPaypalCountryInfo()
        {
          return this.paypalCountryInfo_;
        }

        public int getSerializedSize()
        {
          int i = 0;
          if (hasCountryCode())
            i = 0 + CodedOutputStreamMicro.computeStringSize(3, getCountryCode());
          if (hasCountryName())
            i += CodedOutputStreamMicro.computeStringSize(4, getCountryName());
          if (hasPaypalCountryInfo())
            i += CodedOutputStreamMicro.computeMessageSize(5, getPaypalCountryInfo());
          if (hasAllowsReducedBillingAddress())
            i += CodedOutputStreamMicro.computeBoolSize(6, getAllowsReducedBillingAddress());
          Iterator localIterator = getInstrumentAddressSpecList().iterator();
          while (localIterator.hasNext())
            i += CodedOutputStreamMicro.computeGroupSize(7, (InstrumentAddressSpec)localIterator.next());
          this.cachedSize = i;
          return i;
        }

        public boolean hasAllowsReducedBillingAddress()
        {
          return this.hasAllowsReducedBillingAddress;
        }

        public boolean hasCountryCode()
        {
          return this.hasCountryCode;
        }

        public boolean hasCountryName()
        {
          return this.hasCountryName;
        }

        public boolean hasPaypalCountryInfo()
        {
          return this.hasPaypalCountryInfo;
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
            case 26:
              setCountryCode(paramCodedInputStreamMicro.readString());
              break;
            case 34:
              setCountryName(paramCodedInputStreamMicro.readString());
              break;
            case 42:
              VendingProtos.PaypalCountryInfoProto localPaypalCountryInfoProto = new VendingProtos.PaypalCountryInfoProto();
              paramCodedInputStreamMicro.readMessage(localPaypalCountryInfoProto);
              setPaypalCountryInfo(localPaypalCountryInfoProto);
              break;
            case 48:
              setAllowsReducedBillingAddress(paramCodedInputStreamMicro.readBool());
              break;
            case 59:
            }
            InstrumentAddressSpec localInstrumentAddressSpec = new InstrumentAddressSpec();
            paramCodedInputStreamMicro.readGroup(localInstrumentAddressSpec, 7);
            addInstrumentAddressSpec(localInstrumentAddressSpec);
          }
        }

        public Country setAllowsReducedBillingAddress(boolean paramBoolean)
        {
          this.hasAllowsReducedBillingAddress = true;
          this.allowsReducedBillingAddress_ = paramBoolean;
          return this;
        }

        public Country setCountryCode(String paramString)
        {
          this.hasCountryCode = true;
          this.countryCode_ = paramString;
          return this;
        }

        public Country setCountryName(String paramString)
        {
          this.hasCountryName = true;
          this.countryName_ = paramString;
          return this;
        }

        public Country setPaypalCountryInfo(VendingProtos.PaypalCountryInfoProto paramPaypalCountryInfoProto)
        {
          if (paramPaypalCountryInfoProto == null)
            throw new NullPointerException();
          this.hasPaypalCountryInfo = true;
          this.paypalCountryInfo_ = paramPaypalCountryInfoProto;
          return this;
        }

        public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
          throws IOException
        {
          if (hasCountryCode())
            paramCodedOutputStreamMicro.writeString(3, getCountryCode());
          if (hasCountryName())
            paramCodedOutputStreamMicro.writeString(4, getCountryName());
          if (hasPaypalCountryInfo())
            paramCodedOutputStreamMicro.writeMessage(5, getPaypalCountryInfo());
          if (hasAllowsReducedBillingAddress())
            paramCodedOutputStreamMicro.writeBool(6, getAllowsReducedBillingAddress());
          Iterator localIterator = getInstrumentAddressSpecList().iterator();
          while (localIterator.hasNext())
            paramCodedOutputStreamMicro.writeGroup(7, (InstrumentAddressSpec)localIterator.next());
        }

        public static final class InstrumentAddressSpec extends MessageMicro
        {
          private CommonDevice.BillingAddressSpec billingAddressSpec_ = null;
          private int cachedSize = -1;
          private boolean hasBillingAddressSpec;
          private boolean hasInstrumentFamily;
          private int instrumentFamily_ = 0;

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

          public int getInstrumentFamily()
          {
            return this.instrumentFamily_;
          }

          public int getSerializedSize()
          {
            int i = 0;
            if (hasInstrumentFamily())
              i = 0 + CodedOutputStreamMicro.computeInt32Size(8, getInstrumentFamily());
            if (hasBillingAddressSpec())
              i += CodedOutputStreamMicro.computeMessageSize(9, getBillingAddressSpec());
            this.cachedSize = i;
            return i;
          }

          public boolean hasBillingAddressSpec()
          {
            return this.hasBillingAddressSpec;
          }

          public boolean hasInstrumentFamily()
          {
            return this.hasInstrumentFamily;
          }

          public InstrumentAddressSpec mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
              case 64:
                setInstrumentFamily(paramCodedInputStreamMicro.readInt32());
                break;
              case 74:
              }
              CommonDevice.BillingAddressSpec localBillingAddressSpec = new CommonDevice.BillingAddressSpec();
              paramCodedInputStreamMicro.readMessage(localBillingAddressSpec);
              setBillingAddressSpec(localBillingAddressSpec);
            }
          }

          public InstrumentAddressSpec setBillingAddressSpec(CommonDevice.BillingAddressSpec paramBillingAddressSpec)
          {
            if (paramBillingAddressSpec == null)
              throw new NullPointerException();
            this.hasBillingAddressSpec = true;
            this.billingAddressSpec_ = paramBillingAddressSpec;
            return this;
          }

          public InstrumentAddressSpec setInstrumentFamily(int paramInt)
          {
            this.hasInstrumentFamily = true;
            this.instrumentFamily_ = paramInt;
            return this;
          }

          public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
            throws IOException
          {
            if (hasInstrumentFamily())
              paramCodedOutputStreamMicro.writeInt32(8, getInstrumentFamily());
            if (hasBillingAddressSpec())
              paramCodedOutputStreamMicro.writeMessage(9, getBillingAddressSpec());
          }
        }
      }
    }
  }

  public static final class PurchaseOrderRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private String billingInstrumentId_ = "";
    private int billingInstrumentType_ = 0;
    private String billingParametersId_ = "";
    private int cachedSize = -1;
    private VendingProtos.CarrierBillingCredentialsProto carrierBillingCredentials_ = null;
    private String developerPayload_ = "";
    private String existingOrderId_ = "";
    private String gaiaAuthToken_ = "";
    private boolean hasAssetId;
    private boolean hasBillingInstrumentId;
    private boolean hasBillingInstrumentType;
    private boolean hasBillingParametersId;
    private boolean hasCarrierBillingCredentials;
    private boolean hasDeveloperPayload;
    private boolean hasExistingOrderId;
    private boolean hasGaiaAuthToken;
    private boolean hasPaypalCredentials;
    private boolean hasProductType;
    private boolean hasRiskHeaderInfo;
    private boolean hasSignatureHash;
    private boolean hasTosAccepted;
    private boolean hasTransactionId;
    private VendingProtos.PaypalCredentialsProto paypalCredentials_ = null;
    private int productType_ = 0;
    private VendingProtos.RiskHeaderInfoProto riskHeaderInfo_ = null;
    private VendingProtos.SignatureHashProto signatureHash_ = null;
    private boolean tosAccepted_ = false;
    private String transactionId_ = "";

    public String getAssetId()
    {
      return this.assetId_;
    }

    public String getBillingInstrumentId()
    {
      return this.billingInstrumentId_;
    }

    public int getBillingInstrumentType()
    {
      return this.billingInstrumentType_;
    }

    public String getBillingParametersId()
    {
      return this.billingParametersId_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.CarrierBillingCredentialsProto getCarrierBillingCredentials()
    {
      return this.carrierBillingCredentials_;
    }

    public String getDeveloperPayload()
    {
      return this.developerPayload_;
    }

    public String getExistingOrderId()
    {
      return this.existingOrderId_;
    }

    public String getGaiaAuthToken()
    {
      return this.gaiaAuthToken_;
    }

    public VendingProtos.PaypalCredentialsProto getPaypalCredentials()
    {
      return this.paypalCredentials_;
    }

    public int getProductType()
    {
      return this.productType_;
    }

    public VendingProtos.RiskHeaderInfoProto getRiskHeaderInfo()
    {
      return this.riskHeaderInfo_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGaiaAuthToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getGaiaAuthToken());
      if (hasAssetId())
        i += CodedOutputStreamMicro.computeStringSize(2, getAssetId());
      if (hasTransactionId())
        i += CodedOutputStreamMicro.computeStringSize(3, getTransactionId());
      if (hasBillingInstrumentId())
        i += CodedOutputStreamMicro.computeStringSize(4, getBillingInstrumentId());
      if (hasTosAccepted())
        i += CodedOutputStreamMicro.computeBoolSize(5, getTosAccepted());
      if (hasCarrierBillingCredentials())
        i += CodedOutputStreamMicro.computeMessageSize(6, getCarrierBillingCredentials());
      if (hasExistingOrderId())
        i += CodedOutputStreamMicro.computeStringSize(7, getExistingOrderId());
      if (hasBillingInstrumentType())
        i += CodedOutputStreamMicro.computeInt32Size(8, getBillingInstrumentType());
      if (hasBillingParametersId())
        i += CodedOutputStreamMicro.computeStringSize(9, getBillingParametersId());
      if (hasPaypalCredentials())
        i += CodedOutputStreamMicro.computeMessageSize(10, getPaypalCredentials());
      if (hasRiskHeaderInfo())
        i += CodedOutputStreamMicro.computeMessageSize(11, getRiskHeaderInfo());
      if (hasProductType())
        i += CodedOutputStreamMicro.computeInt32Size(12, getProductType());
      if (hasSignatureHash())
        i += CodedOutputStreamMicro.computeMessageSize(13, getSignatureHash());
      if (hasDeveloperPayload())
        i += CodedOutputStreamMicro.computeStringSize(14, getDeveloperPayload());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean getTosAccepted()
    {
      return this.tosAccepted_;
    }

    public String getTransactionId()
    {
      return this.transactionId_;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasBillingInstrumentId()
    {
      return this.hasBillingInstrumentId;
    }

    public boolean hasBillingInstrumentType()
    {
      return this.hasBillingInstrumentType;
    }

    public boolean hasBillingParametersId()
    {
      return this.hasBillingParametersId;
    }

    public boolean hasCarrierBillingCredentials()
    {
      return this.hasCarrierBillingCredentials;
    }

    public boolean hasDeveloperPayload()
    {
      return this.hasDeveloperPayload;
    }

    public boolean hasExistingOrderId()
    {
      return this.hasExistingOrderId;
    }

    public boolean hasGaiaAuthToken()
    {
      return this.hasGaiaAuthToken;
    }

    public boolean hasPaypalCredentials()
    {
      return this.hasPaypalCredentials;
    }

    public boolean hasProductType()
    {
      return this.hasProductType;
    }

    public boolean hasRiskHeaderInfo()
    {
      return this.hasRiskHeaderInfo;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public boolean hasTosAccepted()
    {
      return this.hasTosAccepted;
    }

    public boolean hasTransactionId()
    {
      return this.hasTransactionId;
    }

    public PurchaseOrderRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGaiaAuthToken(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setTransactionId(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setBillingInstrumentId(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setTosAccepted(paramCodedInputStreamMicro.readBool());
          break;
        case 50:
          VendingProtos.CarrierBillingCredentialsProto localCarrierBillingCredentialsProto = new VendingProtos.CarrierBillingCredentialsProto();
          paramCodedInputStreamMicro.readMessage(localCarrierBillingCredentialsProto);
          setCarrierBillingCredentials(localCarrierBillingCredentialsProto);
          break;
        case 58:
          setExistingOrderId(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setBillingInstrumentType(paramCodedInputStreamMicro.readInt32());
          break;
        case 74:
          setBillingParametersId(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          VendingProtos.PaypalCredentialsProto localPaypalCredentialsProto = new VendingProtos.PaypalCredentialsProto();
          paramCodedInputStreamMicro.readMessage(localPaypalCredentialsProto);
          setPaypalCredentials(localPaypalCredentialsProto);
          break;
        case 90:
          VendingProtos.RiskHeaderInfoProto localRiskHeaderInfoProto = new VendingProtos.RiskHeaderInfoProto();
          paramCodedInputStreamMicro.readMessage(localRiskHeaderInfoProto);
          setRiskHeaderInfo(localRiskHeaderInfoProto);
          break;
        case 96:
          setProductType(paramCodedInputStreamMicro.readInt32());
          break;
        case 106:
          VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
          paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
          setSignatureHash(localSignatureHashProto);
          break;
        case 114:
        }
        setDeveloperPayload(paramCodedInputStreamMicro.readString());
      }
    }

    public PurchaseOrderRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setBillingInstrumentId(String paramString)
    {
      this.hasBillingInstrumentId = true;
      this.billingInstrumentId_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setBillingInstrumentType(int paramInt)
    {
      this.hasBillingInstrumentType = true;
      this.billingInstrumentType_ = paramInt;
      return this;
    }

    public PurchaseOrderRequestProto setBillingParametersId(String paramString)
    {
      this.hasBillingParametersId = true;
      this.billingParametersId_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setCarrierBillingCredentials(VendingProtos.CarrierBillingCredentialsProto paramCarrierBillingCredentialsProto)
    {
      if (paramCarrierBillingCredentialsProto == null)
        throw new NullPointerException();
      this.hasCarrierBillingCredentials = true;
      this.carrierBillingCredentials_ = paramCarrierBillingCredentialsProto;
      return this;
    }

    public PurchaseOrderRequestProto setDeveloperPayload(String paramString)
    {
      this.hasDeveloperPayload = true;
      this.developerPayload_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setExistingOrderId(String paramString)
    {
      this.hasExistingOrderId = true;
      this.existingOrderId_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setGaiaAuthToken(String paramString)
    {
      this.hasGaiaAuthToken = true;
      this.gaiaAuthToken_ = paramString;
      return this;
    }

    public PurchaseOrderRequestProto setPaypalCredentials(VendingProtos.PaypalCredentialsProto paramPaypalCredentialsProto)
    {
      if (paramPaypalCredentialsProto == null)
        throw new NullPointerException();
      this.hasPaypalCredentials = true;
      this.paypalCredentials_ = paramPaypalCredentialsProto;
      return this;
    }

    public PurchaseOrderRequestProto setProductType(int paramInt)
    {
      this.hasProductType = true;
      this.productType_ = paramInt;
      return this;
    }

    public PurchaseOrderRequestProto setRiskHeaderInfo(VendingProtos.RiskHeaderInfoProto paramRiskHeaderInfoProto)
    {
      if (paramRiskHeaderInfoProto == null)
        throw new NullPointerException();
      this.hasRiskHeaderInfo = true;
      this.riskHeaderInfo_ = paramRiskHeaderInfoProto;
      return this;
    }

    public PurchaseOrderRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public PurchaseOrderRequestProto setTosAccepted(boolean paramBoolean)
    {
      this.hasTosAccepted = true;
      this.tosAccepted_ = paramBoolean;
      return this;
    }

    public PurchaseOrderRequestProto setTransactionId(String paramString)
    {
      this.hasTransactionId = true;
      this.transactionId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGaiaAuthToken())
        paramCodedOutputStreamMicro.writeString(1, getGaiaAuthToken());
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(2, getAssetId());
      if (hasTransactionId())
        paramCodedOutputStreamMicro.writeString(3, getTransactionId());
      if (hasBillingInstrumentId())
        paramCodedOutputStreamMicro.writeString(4, getBillingInstrumentId());
      if (hasTosAccepted())
        paramCodedOutputStreamMicro.writeBool(5, getTosAccepted());
      if (hasCarrierBillingCredentials())
        paramCodedOutputStreamMicro.writeMessage(6, getCarrierBillingCredentials());
      if (hasExistingOrderId())
        paramCodedOutputStreamMicro.writeString(7, getExistingOrderId());
      if (hasBillingInstrumentType())
        paramCodedOutputStreamMicro.writeInt32(8, getBillingInstrumentType());
      if (hasBillingParametersId())
        paramCodedOutputStreamMicro.writeString(9, getBillingParametersId());
      if (hasPaypalCredentials())
        paramCodedOutputStreamMicro.writeMessage(10, getPaypalCredentials());
      if (hasRiskHeaderInfo())
        paramCodedOutputStreamMicro.writeMessage(11, getRiskHeaderInfo());
      if (hasProductType())
        paramCodedOutputStreamMicro.writeInt32(12, getProductType());
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(13, getSignatureHash());
      if (hasDeveloperPayload())
        paramCodedOutputStreamMicro.writeString(14, getDeveloperPayload());
    }
  }

  public static final class PurchaseOrderResponseProto extends MessageMicro
  {
    private VendingProtos.ExternalAssetProto asset_ = null;
    private int cachedSize = -1;
    private int deprecatedResultCode_ = 0;
    private boolean hasAsset;
    private boolean hasDeprecatedResultCode;
    private boolean hasPurchaseInfo;
    private boolean hasPurchaseResult;
    private VendingProtos.PurchaseInfoProto purchaseInfo_ = null;
    private VendingProtos.PurchaseResultProto purchaseResult_ = null;

    public VendingProtos.ExternalAssetProto getAsset()
    {
      return this.asset_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getDeprecatedResultCode()
    {
      return this.deprecatedResultCode_;
    }

    public VendingProtos.PurchaseInfoProto getPurchaseInfo()
    {
      return this.purchaseInfo_;
    }

    public VendingProtos.PurchaseResultProto getPurchaseResult()
    {
      return this.purchaseResult_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDeprecatedResultCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getDeprecatedResultCode());
      if (hasPurchaseInfo())
        i += CodedOutputStreamMicro.computeMessageSize(2, getPurchaseInfo());
      if (hasAsset())
        i += CodedOutputStreamMicro.computeMessageSize(3, getAsset());
      if (hasPurchaseResult())
        i += CodedOutputStreamMicro.computeMessageSize(4, getPurchaseResult());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAsset()
    {
      return this.hasAsset;
    }

    public boolean hasDeprecatedResultCode()
    {
      return this.hasDeprecatedResultCode;
    }

    public boolean hasPurchaseInfo()
    {
      return this.hasPurchaseInfo;
    }

    public boolean hasPurchaseResult()
    {
      return this.hasPurchaseResult;
    }

    public PurchaseOrderResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDeprecatedResultCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          VendingProtos.PurchaseInfoProto localPurchaseInfoProto = new VendingProtos.PurchaseInfoProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseInfoProto);
          setPurchaseInfo(localPurchaseInfoProto);
          break;
        case 26:
          VendingProtos.ExternalAssetProto localExternalAssetProto = new VendingProtos.ExternalAssetProto();
          paramCodedInputStreamMicro.readMessage(localExternalAssetProto);
          setAsset(localExternalAssetProto);
          break;
        case 34:
        }
        VendingProtos.PurchaseResultProto localPurchaseResultProto = new VendingProtos.PurchaseResultProto();
        paramCodedInputStreamMicro.readMessage(localPurchaseResultProto);
        setPurchaseResult(localPurchaseResultProto);
      }
    }

    public PurchaseOrderResponseProto setAsset(VendingProtos.ExternalAssetProto paramExternalAssetProto)
    {
      if (paramExternalAssetProto == null)
        throw new NullPointerException();
      this.hasAsset = true;
      this.asset_ = paramExternalAssetProto;
      return this;
    }

    public PurchaseOrderResponseProto setDeprecatedResultCode(int paramInt)
    {
      this.hasDeprecatedResultCode = true;
      this.deprecatedResultCode_ = paramInt;
      return this;
    }

    public PurchaseOrderResponseProto setPurchaseInfo(VendingProtos.PurchaseInfoProto paramPurchaseInfoProto)
    {
      if (paramPurchaseInfoProto == null)
        throw new NullPointerException();
      this.hasPurchaseInfo = true;
      this.purchaseInfo_ = paramPurchaseInfoProto;
      return this;
    }

    public PurchaseOrderResponseProto setPurchaseResult(VendingProtos.PurchaseResultProto paramPurchaseResultProto)
    {
      if (paramPurchaseResultProto == null)
        throw new NullPointerException();
      this.hasPurchaseResult = true;
      this.purchaseResult_ = paramPurchaseResultProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDeprecatedResultCode())
        paramCodedOutputStreamMicro.writeInt32(1, getDeprecatedResultCode());
      if (hasPurchaseInfo())
        paramCodedOutputStreamMicro.writeMessage(2, getPurchaseInfo());
      if (hasAsset())
        paramCodedOutputStreamMicro.writeMessage(3, getAsset());
      if (hasPurchaseResult())
        paramCodedOutputStreamMicro.writeMessage(4, getPurchaseResult());
    }
  }

  public static final class PurchasePostRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private BillingInstrumentInfo billingInstrumentInfo_ = null;
    private int cachedSize = -1;
    private String cbInstrumentKey_ = "";
    private String gaiaAuthToken_ = "";
    private boolean hasAssetId;
    private boolean hasBillingInstrumentInfo;
    private boolean hasCbInstrumentKey;
    private boolean hasGaiaAuthToken;
    private boolean hasPaypalAuthConfirmed;
    private boolean hasProductType;
    private boolean hasSignatureHash;
    private boolean hasTosAccepted;
    private boolean hasTransactionId;
    private boolean paypalAuthConfirmed_ = false;
    private int productType_ = 0;
    private VendingProtos.SignatureHashProto signatureHash_ = null;
    private boolean tosAccepted_ = false;
    private String transactionId_ = "";

    public String getAssetId()
    {
      return this.assetId_;
    }

    public BillingInstrumentInfo getBillingInstrumentInfo()
    {
      return this.billingInstrumentInfo_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCbInstrumentKey()
    {
      return this.cbInstrumentKey_;
    }

    public String getGaiaAuthToken()
    {
      return this.gaiaAuthToken_;
    }

    public boolean getPaypalAuthConfirmed()
    {
      return this.paypalAuthConfirmed_;
    }

    public int getProductType()
    {
      return this.productType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasGaiaAuthToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getGaiaAuthToken());
      if (hasAssetId())
        i += CodedOutputStreamMicro.computeStringSize(2, getAssetId());
      if (hasTransactionId())
        i += CodedOutputStreamMicro.computeStringSize(3, getTransactionId());
      if (hasBillingInstrumentInfo())
        i += CodedOutputStreamMicro.computeGroupSize(4, getBillingInstrumentInfo());
      if (hasTosAccepted())
        i += CodedOutputStreamMicro.computeBoolSize(7, getTosAccepted());
      if (hasCbInstrumentKey())
        i += CodedOutputStreamMicro.computeStringSize(8, getCbInstrumentKey());
      if (hasPaypalAuthConfirmed())
        i += CodedOutputStreamMicro.computeBoolSize(11, getPaypalAuthConfirmed());
      if (hasProductType())
        i += CodedOutputStreamMicro.computeInt32Size(12, getProductType());
      if (hasSignatureHash())
        i += CodedOutputStreamMicro.computeMessageSize(13, getSignatureHash());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean getTosAccepted()
    {
      return this.tosAccepted_;
    }

    public String getTransactionId()
    {
      return this.transactionId_;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasBillingInstrumentInfo()
    {
      return this.hasBillingInstrumentInfo;
    }

    public boolean hasCbInstrumentKey()
    {
      return this.hasCbInstrumentKey;
    }

    public boolean hasGaiaAuthToken()
    {
      return this.hasGaiaAuthToken;
    }

    public boolean hasPaypalAuthConfirmed()
    {
      return this.hasPaypalAuthConfirmed;
    }

    public boolean hasProductType()
    {
      return this.hasProductType;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public boolean hasTosAccepted()
    {
      return this.hasTosAccepted;
    }

    public boolean hasTransactionId()
    {
      return this.hasTransactionId;
    }

    public PurchasePostRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setGaiaAuthToken(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setTransactionId(paramCodedInputStreamMicro.readString());
          break;
        case 35:
          BillingInstrumentInfo localBillingInstrumentInfo = new BillingInstrumentInfo();
          paramCodedInputStreamMicro.readGroup(localBillingInstrumentInfo, 4);
          setBillingInstrumentInfo(localBillingInstrumentInfo);
          break;
        case 56:
          setTosAccepted(paramCodedInputStreamMicro.readBool());
          break;
        case 66:
          setCbInstrumentKey(paramCodedInputStreamMicro.readString());
          break;
        case 88:
          setPaypalAuthConfirmed(paramCodedInputStreamMicro.readBool());
          break;
        case 96:
          setProductType(paramCodedInputStreamMicro.readInt32());
          break;
        case 106:
        }
        VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
        paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
        setSignatureHash(localSignatureHashProto);
      }
    }

    public PurchasePostRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public PurchasePostRequestProto setBillingInstrumentInfo(BillingInstrumentInfo paramBillingInstrumentInfo)
    {
      if (paramBillingInstrumentInfo == null)
        throw new NullPointerException();
      this.hasBillingInstrumentInfo = true;
      this.billingInstrumentInfo_ = paramBillingInstrumentInfo;
      return this;
    }

    public PurchasePostRequestProto setCbInstrumentKey(String paramString)
    {
      this.hasCbInstrumentKey = true;
      this.cbInstrumentKey_ = paramString;
      return this;
    }

    public PurchasePostRequestProto setGaiaAuthToken(String paramString)
    {
      this.hasGaiaAuthToken = true;
      this.gaiaAuthToken_ = paramString;
      return this;
    }

    public PurchasePostRequestProto setPaypalAuthConfirmed(boolean paramBoolean)
    {
      this.hasPaypalAuthConfirmed = true;
      this.paypalAuthConfirmed_ = paramBoolean;
      return this;
    }

    public PurchasePostRequestProto setProductType(int paramInt)
    {
      this.hasProductType = true;
      this.productType_ = paramInt;
      return this;
    }

    public PurchasePostRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public PurchasePostRequestProto setTosAccepted(boolean paramBoolean)
    {
      this.hasTosAccepted = true;
      this.tosAccepted_ = paramBoolean;
      return this;
    }

    public PurchasePostRequestProto setTransactionId(String paramString)
    {
      this.hasTransactionId = true;
      this.transactionId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasGaiaAuthToken())
        paramCodedOutputStreamMicro.writeString(1, getGaiaAuthToken());
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(2, getAssetId());
      if (hasTransactionId())
        paramCodedOutputStreamMicro.writeString(3, getTransactionId());
      if (hasBillingInstrumentInfo())
        paramCodedOutputStreamMicro.writeGroup(4, getBillingInstrumentInfo());
      if (hasTosAccepted())
        paramCodedOutputStreamMicro.writeBool(7, getTosAccepted());
      if (hasCbInstrumentKey())
        paramCodedOutputStreamMicro.writeString(8, getCbInstrumentKey());
      if (hasPaypalAuthConfirmed())
        paramCodedOutputStreamMicro.writeBool(11, getPaypalAuthConfirmed());
      if (hasProductType())
        paramCodedOutputStreamMicro.writeInt32(12, getProductType());
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(13, getSignatureHash());
    }

    public static final class BillingInstrumentInfo extends MessageMicro
    {
      private String billingInstrumentId_ = "";
      private int cachedSize = -1;
      private VendingProtos.ExternalCarrierBillingInstrumentProto carrierInstrument_ = null;
      private VendingProtos.ExternalCreditCard creditCard_ = null;
      private boolean hasBillingInstrumentId;
      private boolean hasCarrierInstrument;
      private boolean hasCreditCard;
      private boolean hasPaypalInstrument;
      private VendingProtos.ExternalPaypalInstrumentProto paypalInstrument_ = null;

      public String getBillingInstrumentId()
      {
        return this.billingInstrumentId_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public VendingProtos.ExternalCarrierBillingInstrumentProto getCarrierInstrument()
      {
        return this.carrierInstrument_;
      }

      public VendingProtos.ExternalCreditCard getCreditCard()
      {
        return this.creditCard_;
      }

      public VendingProtos.ExternalPaypalInstrumentProto getPaypalInstrument()
      {
        return this.paypalInstrument_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasBillingInstrumentId())
          i = 0 + CodedOutputStreamMicro.computeStringSize(5, getBillingInstrumentId());
        if (hasCreditCard())
          i += CodedOutputStreamMicro.computeMessageSize(6, getCreditCard());
        if (hasCarrierInstrument())
          i += CodedOutputStreamMicro.computeMessageSize(9, getCarrierInstrument());
        if (hasPaypalInstrument())
          i += CodedOutputStreamMicro.computeMessageSize(10, getPaypalInstrument());
        this.cachedSize = i;
        return i;
      }

      public boolean hasBillingInstrumentId()
      {
        return this.hasBillingInstrumentId;
      }

      public boolean hasCarrierInstrument()
      {
        return this.hasCarrierInstrument;
      }

      public boolean hasCreditCard()
      {
        return this.hasCreditCard;
      }

      public boolean hasPaypalInstrument()
      {
        return this.hasPaypalInstrument;
      }

      public BillingInstrumentInfo mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 42:
            setBillingInstrumentId(paramCodedInputStreamMicro.readString());
            break;
          case 50:
            VendingProtos.ExternalCreditCard localExternalCreditCard = new VendingProtos.ExternalCreditCard();
            paramCodedInputStreamMicro.readMessage(localExternalCreditCard);
            setCreditCard(localExternalCreditCard);
            break;
          case 74:
            VendingProtos.ExternalCarrierBillingInstrumentProto localExternalCarrierBillingInstrumentProto = new VendingProtos.ExternalCarrierBillingInstrumentProto();
            paramCodedInputStreamMicro.readMessage(localExternalCarrierBillingInstrumentProto);
            setCarrierInstrument(localExternalCarrierBillingInstrumentProto);
            break;
          case 82:
          }
          VendingProtos.ExternalPaypalInstrumentProto localExternalPaypalInstrumentProto = new VendingProtos.ExternalPaypalInstrumentProto();
          paramCodedInputStreamMicro.readMessage(localExternalPaypalInstrumentProto);
          setPaypalInstrument(localExternalPaypalInstrumentProto);
        }
      }

      public BillingInstrumentInfo setBillingInstrumentId(String paramString)
      {
        this.hasBillingInstrumentId = true;
        this.billingInstrumentId_ = paramString;
        return this;
      }

      public BillingInstrumentInfo setCarrierInstrument(VendingProtos.ExternalCarrierBillingInstrumentProto paramExternalCarrierBillingInstrumentProto)
      {
        if (paramExternalCarrierBillingInstrumentProto == null)
          throw new NullPointerException();
        this.hasCarrierInstrument = true;
        this.carrierInstrument_ = paramExternalCarrierBillingInstrumentProto;
        return this;
      }

      public BillingInstrumentInfo setCreditCard(VendingProtos.ExternalCreditCard paramExternalCreditCard)
      {
        if (paramExternalCreditCard == null)
          throw new NullPointerException();
        this.hasCreditCard = true;
        this.creditCard_ = paramExternalCreditCard;
        return this;
      }

      public BillingInstrumentInfo setPaypalInstrument(VendingProtos.ExternalPaypalInstrumentProto paramExternalPaypalInstrumentProto)
      {
        if (paramExternalPaypalInstrumentProto == null)
          throw new NullPointerException();
        this.hasPaypalInstrument = true;
        this.paypalInstrument_ = paramExternalPaypalInstrumentProto;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasBillingInstrumentId())
          paramCodedOutputStreamMicro.writeString(5, getBillingInstrumentId());
        if (hasCreditCard())
          paramCodedOutputStreamMicro.writeMessage(6, getCreditCard());
        if (hasCarrierInstrument())
          paramCodedOutputStreamMicro.writeMessage(9, getCarrierInstrument());
        if (hasPaypalInstrument())
          paramCodedOutputStreamMicro.writeMessage(10, getPaypalInstrument());
      }
    }
  }

  public static final class PurchasePostResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private int deprecatedResultCode_ = 0;
    private boolean hasDeprecatedResultCode;
    private boolean hasPurchaseInfo;
    private boolean hasPurchaseResult;
    private boolean hasTermsOfServiceCheckboxText;
    private boolean hasTermsOfServiceHeaderText;
    private boolean hasTermsOfServiceName;
    private boolean hasTermsOfServiceText;
    private boolean hasTermsOfServiceUrl;
    private VendingProtos.PurchaseInfoProto purchaseInfo_ = null;
    private VendingProtos.PurchaseResultProto purchaseResult_ = null;
    private String termsOfServiceCheckboxText_ = "";
    private String termsOfServiceHeaderText_ = "";
    private String termsOfServiceName_ = "";
    private String termsOfServiceText_ = "";
    private String termsOfServiceUrl_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getDeprecatedResultCode()
    {
      return this.deprecatedResultCode_;
    }

    public VendingProtos.PurchaseInfoProto getPurchaseInfo()
    {
      return this.purchaseInfo_;
    }

    public VendingProtos.PurchaseResultProto getPurchaseResult()
    {
      return this.purchaseResult_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDeprecatedResultCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getDeprecatedResultCode());
      if (hasPurchaseInfo())
        i += CodedOutputStreamMicro.computeMessageSize(2, getPurchaseInfo());
      if (hasTermsOfServiceUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getTermsOfServiceUrl());
      if (hasTermsOfServiceText())
        i += CodedOutputStreamMicro.computeStringSize(4, getTermsOfServiceText());
      if (hasTermsOfServiceName())
        i += CodedOutputStreamMicro.computeStringSize(5, getTermsOfServiceName());
      if (hasTermsOfServiceCheckboxText())
        i += CodedOutputStreamMicro.computeStringSize(6, getTermsOfServiceCheckboxText());
      if (hasTermsOfServiceHeaderText())
        i += CodedOutputStreamMicro.computeStringSize(7, getTermsOfServiceHeaderText());
      if (hasPurchaseResult())
        i += CodedOutputStreamMicro.computeMessageSize(8, getPurchaseResult());
      this.cachedSize = i;
      return i;
    }

    public String getTermsOfServiceCheckboxText()
    {
      return this.termsOfServiceCheckboxText_;
    }

    public String getTermsOfServiceHeaderText()
    {
      return this.termsOfServiceHeaderText_;
    }

    public String getTermsOfServiceName()
    {
      return this.termsOfServiceName_;
    }

    public String getTermsOfServiceText()
    {
      return this.termsOfServiceText_;
    }

    public String getTermsOfServiceUrl()
    {
      return this.termsOfServiceUrl_;
    }

    public boolean hasDeprecatedResultCode()
    {
      return this.hasDeprecatedResultCode;
    }

    public boolean hasPurchaseInfo()
    {
      return this.hasPurchaseInfo;
    }

    public boolean hasPurchaseResult()
    {
      return this.hasPurchaseResult;
    }

    public boolean hasTermsOfServiceCheckboxText()
    {
      return this.hasTermsOfServiceCheckboxText;
    }

    public boolean hasTermsOfServiceHeaderText()
    {
      return this.hasTermsOfServiceHeaderText;
    }

    public boolean hasTermsOfServiceName()
    {
      return this.hasTermsOfServiceName;
    }

    public boolean hasTermsOfServiceText()
    {
      return this.hasTermsOfServiceText;
    }

    public boolean hasTermsOfServiceUrl()
    {
      return this.hasTermsOfServiceUrl;
    }

    public PurchasePostResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDeprecatedResultCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          VendingProtos.PurchaseInfoProto localPurchaseInfoProto = new VendingProtos.PurchaseInfoProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseInfoProto);
          setPurchaseInfo(localPurchaseInfoProto);
          break;
        case 26:
          setTermsOfServiceUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setTermsOfServiceText(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setTermsOfServiceName(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setTermsOfServiceCheckboxText(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setTermsOfServiceHeaderText(paramCodedInputStreamMicro.readString());
          break;
        case 66:
        }
        VendingProtos.PurchaseResultProto localPurchaseResultProto = new VendingProtos.PurchaseResultProto();
        paramCodedInputStreamMicro.readMessage(localPurchaseResultProto);
        setPurchaseResult(localPurchaseResultProto);
      }
    }

    public PurchasePostResponseProto setDeprecatedResultCode(int paramInt)
    {
      this.hasDeprecatedResultCode = true;
      this.deprecatedResultCode_ = paramInt;
      return this;
    }

    public PurchasePostResponseProto setPurchaseInfo(VendingProtos.PurchaseInfoProto paramPurchaseInfoProto)
    {
      if (paramPurchaseInfoProto == null)
        throw new NullPointerException();
      this.hasPurchaseInfo = true;
      this.purchaseInfo_ = paramPurchaseInfoProto;
      return this;
    }

    public PurchasePostResponseProto setPurchaseResult(VendingProtos.PurchaseResultProto paramPurchaseResultProto)
    {
      if (paramPurchaseResultProto == null)
        throw new NullPointerException();
      this.hasPurchaseResult = true;
      this.purchaseResult_ = paramPurchaseResultProto;
      return this;
    }

    public PurchasePostResponseProto setTermsOfServiceCheckboxText(String paramString)
    {
      this.hasTermsOfServiceCheckboxText = true;
      this.termsOfServiceCheckboxText_ = paramString;
      return this;
    }

    public PurchasePostResponseProto setTermsOfServiceHeaderText(String paramString)
    {
      this.hasTermsOfServiceHeaderText = true;
      this.termsOfServiceHeaderText_ = paramString;
      return this;
    }

    public PurchasePostResponseProto setTermsOfServiceName(String paramString)
    {
      this.hasTermsOfServiceName = true;
      this.termsOfServiceName_ = paramString;
      return this;
    }

    public PurchasePostResponseProto setTermsOfServiceText(String paramString)
    {
      this.hasTermsOfServiceText = true;
      this.termsOfServiceText_ = paramString;
      return this;
    }

    public PurchasePostResponseProto setTermsOfServiceUrl(String paramString)
    {
      this.hasTermsOfServiceUrl = true;
      this.termsOfServiceUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDeprecatedResultCode())
        paramCodedOutputStreamMicro.writeInt32(1, getDeprecatedResultCode());
      if (hasPurchaseInfo())
        paramCodedOutputStreamMicro.writeMessage(2, getPurchaseInfo());
      if (hasTermsOfServiceUrl())
        paramCodedOutputStreamMicro.writeString(3, getTermsOfServiceUrl());
      if (hasTermsOfServiceText())
        paramCodedOutputStreamMicro.writeString(4, getTermsOfServiceText());
      if (hasTermsOfServiceName())
        paramCodedOutputStreamMicro.writeString(5, getTermsOfServiceName());
      if (hasTermsOfServiceCheckboxText())
        paramCodedOutputStreamMicro.writeString(6, getTermsOfServiceCheckboxText());
      if (hasTermsOfServiceHeaderText())
        paramCodedOutputStreamMicro.writeString(7, getTermsOfServiceHeaderText());
      if (hasPurchaseResult())
        paramCodedOutputStreamMicro.writeMessage(8, getPurchaseResult());
    }
  }

  public static final class PurchaseProductRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasProductId;
    private boolean hasProductType;
    private boolean hasSignatureHash;
    private String productId_ = "";
    private int productType_ = 0;
    private VendingProtos.SignatureHashProto signatureHash_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getProductId()
    {
      return this.productId_;
    }

    public int getProductType()
    {
      return this.productType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasProductType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getProductType());
      if (hasProductId())
        i += CodedOutputStreamMicro.computeStringSize(2, getProductId());
      if (hasSignatureHash())
        i += CodedOutputStreamMicro.computeMessageSize(3, getSignatureHash());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.SignatureHashProto getSignatureHash()
    {
      return this.signatureHash_;
    }

    public boolean hasProductId()
    {
      return this.hasProductId;
    }

    public boolean hasProductType()
    {
      return this.hasProductType;
    }

    public boolean hasSignatureHash()
    {
      return this.hasSignatureHash;
    }

    public PurchaseProductRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setProductType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setProductId(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        VendingProtos.SignatureHashProto localSignatureHashProto = new VendingProtos.SignatureHashProto();
        paramCodedInputStreamMicro.readMessage(localSignatureHashProto);
        setSignatureHash(localSignatureHashProto);
      }
    }

    public PurchaseProductRequestProto setProductId(String paramString)
    {
      this.hasProductId = true;
      this.productId_ = paramString;
      return this;
    }

    public PurchaseProductRequestProto setProductType(int paramInt)
    {
      this.hasProductType = true;
      this.productType_ = paramInt;
      return this;
    }

    public PurchaseProductRequestProto setSignatureHash(VendingProtos.SignatureHashProto paramSignatureHashProto)
    {
      if (paramSignatureHashProto == null)
        throw new NullPointerException();
      this.hasSignatureHash = true;
      this.signatureHash_ = paramSignatureHashProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasProductType())
        paramCodedOutputStreamMicro.writeInt32(1, getProductType());
      if (hasProductId())
        paramCodedOutputStreamMicro.writeString(2, getProductId());
      if (hasSignatureHash())
        paramCodedOutputStreamMicro.writeMessage(3, getSignatureHash());
    }
  }

  public static final class PurchaseProductResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasItemDescription;
    private boolean hasItemTitle;
    private boolean hasMerchantField;
    private boolean hasTitle;
    private String itemDescription_ = "";
    private String itemTitle_ = "";
    private String merchantField_ = "";
    private String title_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getItemDescription()
    {
      return this.itemDescription_;
    }

    public String getItemTitle()
    {
      return this.itemTitle_;
    }

    public String getMerchantField()
    {
      return this.merchantField_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTitle());
      if (hasItemTitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getItemTitle());
      if (hasItemDescription())
        i += CodedOutputStreamMicro.computeStringSize(3, getItemDescription());
      if (hasMerchantField())
        i += CodedOutputStreamMicro.computeStringSize(4, getMerchantField());
      this.cachedSize = i;
      return i;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasItemDescription()
    {
      return this.hasItemDescription;
    }

    public boolean hasItemTitle()
    {
      return this.hasItemTitle;
    }

    public boolean hasMerchantField()
    {
      return this.hasMerchantField;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public PurchaseProductResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setItemTitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setItemDescription(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setMerchantField(paramCodedInputStreamMicro.readString());
      }
    }

    public PurchaseProductResponseProto setItemDescription(String paramString)
    {
      this.hasItemDescription = true;
      this.itemDescription_ = paramString;
      return this;
    }

    public PurchaseProductResponseProto setItemTitle(String paramString)
    {
      this.hasItemTitle = true;
      this.itemTitle_ = paramString;
      return this;
    }

    public PurchaseProductResponseProto setMerchantField(String paramString)
    {
      this.hasMerchantField = true;
      this.merchantField_ = paramString;
      return this;
    }

    public PurchaseProductResponseProto setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(1, getTitle());
      if (hasItemTitle())
        paramCodedOutputStreamMicro.writeString(2, getItemTitle());
      if (hasItemDescription())
        paramCodedOutputStreamMicro.writeString(3, getItemDescription());
      if (hasMerchantField())
        paramCodedOutputStreamMicro.writeString(4, getMerchantField());
    }
  }

  public static final class PurchaseResultProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasResultCode;
    private boolean hasResultCodeMessage;
    private String resultCodeMessage_ = "";
    private int resultCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getResultCode()
    {
      return this.resultCode_;
    }

    public String getResultCodeMessage()
    {
      return this.resultCodeMessage_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResultCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResultCode());
      if (hasResultCodeMessage())
        i += CodedOutputStreamMicro.computeStringSize(2, getResultCodeMessage());
      this.cachedSize = i;
      return i;
    }

    public boolean hasResultCode()
    {
      return this.hasResultCode;
    }

    public boolean hasResultCodeMessage()
    {
      return this.hasResultCodeMessage;
    }

    public PurchaseResultProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResultCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
        }
        setResultCodeMessage(paramCodedInputStreamMicro.readString());
      }
    }

    public PurchaseResultProto setResultCode(int paramInt)
    {
      this.hasResultCode = true;
      this.resultCode_ = paramInt;
      return this;
    }

    public PurchaseResultProto setResultCodeMessage(String paramString)
    {
      this.hasResultCodeMessage = true;
      this.resultCodeMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResultCode())
        paramCodedOutputStreamMicro.writeInt32(1, getResultCode());
      if (hasResultCodeMessage())
        paramCodedOutputStreamMicro.writeString(2, getResultCodeMessage());
    }
  }

  public static final class QuerySuggestionProto extends MessageMicro
  {
    private int cachedSize = -1;
    private int estimatedNumResults_ = 0;
    private boolean hasEstimatedNumResults;
    private boolean hasQuery;
    private boolean hasQueryWeight;
    private int queryWeight_ = 0;
    private String query_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getEstimatedNumResults()
    {
      return this.estimatedNumResults_;
    }

    public String getQuery()
    {
      return this.query_;
    }

    public int getQueryWeight()
    {
      return this.queryWeight_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasQuery())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getQuery());
      if (hasEstimatedNumResults())
        i += CodedOutputStreamMicro.computeInt32Size(2, getEstimatedNumResults());
      if (hasQueryWeight())
        i += CodedOutputStreamMicro.computeInt32Size(3, getQueryWeight());
      this.cachedSize = i;
      return i;
    }

    public boolean hasEstimatedNumResults()
    {
      return this.hasEstimatedNumResults;
    }

    public boolean hasQuery()
    {
      return this.hasQuery;
    }

    public boolean hasQueryWeight()
    {
      return this.hasQueryWeight;
    }

    public QuerySuggestionProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setQuery(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setEstimatedNumResults(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
        }
        setQueryWeight(paramCodedInputStreamMicro.readInt32());
      }
    }

    public QuerySuggestionProto setEstimatedNumResults(int paramInt)
    {
      this.hasEstimatedNumResults = true;
      this.estimatedNumResults_ = paramInt;
      return this;
    }

    public QuerySuggestionProto setQuery(String paramString)
    {
      this.hasQuery = true;
      this.query_ = paramString;
      return this;
    }

    public QuerySuggestionProto setQueryWeight(int paramInt)
    {
      this.hasQueryWeight = true;
      this.queryWeight_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasQuery())
        paramCodedOutputStreamMicro.writeString(1, getQuery());
      if (hasEstimatedNumResults())
        paramCodedOutputStreamMicro.writeInt32(2, getEstimatedNumResults());
      if (hasQueryWeight())
        paramCodedOutputStreamMicro.writeInt32(3, getQueryWeight());
    }
  }

  public static final class QuerySuggestionRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasQuery;
    private boolean hasRequestType;
    private String query_ = "";
    private int requestType_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getQuery()
    {
      return this.query_;
    }

    public int getRequestType()
    {
      return this.requestType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasQuery())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getQuery());
      if (hasRequestType())
        i += CodedOutputStreamMicro.computeInt32Size(2, getRequestType());
      this.cachedSize = i;
      return i;
    }

    public boolean hasQuery()
    {
      return this.hasQuery;
    }

    public boolean hasRequestType()
    {
      return this.hasRequestType;
    }

    public QuerySuggestionRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setQuery(paramCodedInputStreamMicro.readString());
          break;
        case 16:
        }
        setRequestType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public QuerySuggestionRequestProto setQuery(String paramString)
    {
      this.hasQuery = true;
      this.query_ = paramString;
      return this;
    }

    public QuerySuggestionRequestProto setRequestType(int paramInt)
    {
      this.hasRequestType = true;
      this.requestType_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasQuery())
        paramCodedOutputStreamMicro.writeString(1, getQuery());
      if (hasRequestType())
        paramCodedOutputStreamMicro.writeInt32(2, getRequestType());
    }
  }

  public static final class QuerySuggestionResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private int estimatedNumAppSuggestions_ = 0;
    private int estimatedNumQuerySuggestions_ = 0;
    private boolean hasEstimatedNumAppSuggestions;
    private boolean hasEstimatedNumQuerySuggestions;
    private List<Suggestion> suggestion_ = Collections.emptyList();

    public QuerySuggestionResponseProto addSuggestion(Suggestion paramSuggestion)
    {
      if (paramSuggestion == null)
        throw new NullPointerException();
      if (this.suggestion_.isEmpty())
        this.suggestion_ = new ArrayList();
      this.suggestion_.add(paramSuggestion);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getEstimatedNumAppSuggestions()
    {
      return this.estimatedNumAppSuggestions_;
    }

    public int getEstimatedNumQuerySuggestions()
    {
      return this.estimatedNumQuerySuggestions_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getSuggestionList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(1, (Suggestion)localIterator.next());
      if (hasEstimatedNumAppSuggestions())
        i += CodedOutputStreamMicro.computeInt32Size(4, getEstimatedNumAppSuggestions());
      if (hasEstimatedNumQuerySuggestions())
        i += CodedOutputStreamMicro.computeInt32Size(5, getEstimatedNumQuerySuggestions());
      this.cachedSize = i;
      return i;
    }

    public List<Suggestion> getSuggestionList()
    {
      return this.suggestion_;
    }

    public boolean hasEstimatedNumAppSuggestions()
    {
      return this.hasEstimatedNumAppSuggestions;
    }

    public boolean hasEstimatedNumQuerySuggestions()
    {
      return this.hasEstimatedNumQuerySuggestions;
    }

    public QuerySuggestionResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 11:
          Suggestion localSuggestion = new Suggestion();
          paramCodedInputStreamMicro.readGroup(localSuggestion, 1);
          addSuggestion(localSuggestion);
          break;
        case 32:
          setEstimatedNumAppSuggestions(paramCodedInputStreamMicro.readInt32());
          break;
        case 40:
        }
        setEstimatedNumQuerySuggestions(paramCodedInputStreamMicro.readInt32());
      }
    }

    public QuerySuggestionResponseProto setEstimatedNumAppSuggestions(int paramInt)
    {
      this.hasEstimatedNumAppSuggestions = true;
      this.estimatedNumAppSuggestions_ = paramInt;
      return this;
    }

    public QuerySuggestionResponseProto setEstimatedNumQuerySuggestions(int paramInt)
    {
      this.hasEstimatedNumQuerySuggestions = true;
      this.estimatedNumQuerySuggestions_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getSuggestionList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeGroup(1, (Suggestion)localIterator.next());
      if (hasEstimatedNumAppSuggestions())
        paramCodedOutputStreamMicro.writeInt32(4, getEstimatedNumAppSuggestions());
      if (hasEstimatedNumQuerySuggestions())
        paramCodedOutputStreamMicro.writeInt32(5, getEstimatedNumQuerySuggestions());
    }

    public static final class Suggestion extends MessageMicro
    {
      private VendingProtos.AppSuggestionProto appSuggestion_ = null;
      private int cachedSize = -1;
      private boolean hasAppSuggestion;
      private boolean hasQuerySuggestion;
      private VendingProtos.QuerySuggestionProto querySuggestion_ = null;

      public VendingProtos.AppSuggestionProto getAppSuggestion()
      {
        return this.appSuggestion_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public VendingProtos.QuerySuggestionProto getQuerySuggestion()
      {
        return this.querySuggestion_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasAppSuggestion())
          i = 0 + CodedOutputStreamMicro.computeMessageSize(2, getAppSuggestion());
        if (hasQuerySuggestion())
          i += CodedOutputStreamMicro.computeMessageSize(3, getQuerySuggestion());
        this.cachedSize = i;
        return i;
      }

      public boolean hasAppSuggestion()
      {
        return this.hasAppSuggestion;
      }

      public boolean hasQuerySuggestion()
      {
        return this.hasQuerySuggestion;
      }

      public Suggestion mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 18:
            VendingProtos.AppSuggestionProto localAppSuggestionProto = new VendingProtos.AppSuggestionProto();
            paramCodedInputStreamMicro.readMessage(localAppSuggestionProto);
            setAppSuggestion(localAppSuggestionProto);
            break;
          case 26:
          }
          VendingProtos.QuerySuggestionProto localQuerySuggestionProto = new VendingProtos.QuerySuggestionProto();
          paramCodedInputStreamMicro.readMessage(localQuerySuggestionProto);
          setQuerySuggestion(localQuerySuggestionProto);
        }
      }

      public Suggestion setAppSuggestion(VendingProtos.AppSuggestionProto paramAppSuggestionProto)
      {
        if (paramAppSuggestionProto == null)
          throw new NullPointerException();
        this.hasAppSuggestion = true;
        this.appSuggestion_ = paramAppSuggestionProto;
        return this;
      }

      public Suggestion setQuerySuggestion(VendingProtos.QuerySuggestionProto paramQuerySuggestionProto)
      {
        if (paramQuerySuggestionProto == null)
          throw new NullPointerException();
        this.hasQuerySuggestion = true;
        this.querySuggestion_ = paramQuerySuggestionProto;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasAppSuggestion())
          paramCodedOutputStreamMicro.writeMessage(2, getAppSuggestion());
        if (hasQuerySuggestion())
          paramCodedOutputStreamMicro.writeMessage(3, getQuerySuggestion());
      }
    }
  }

  public static final class RateCommentRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private int commentRating_ = 0;
    private String creatorId_ = "";
    private boolean hasAssetId;
    private boolean hasCommentRating;
    private boolean hasCreatorId;

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

    public int getCommentRating()
    {
      return this.commentRating_;
    }

    public String getCreatorId()
    {
      return this.creatorId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasCreatorId())
        i += CodedOutputStreamMicro.computeStringSize(2, getCreatorId());
      if (hasCommentRating())
        i += CodedOutputStreamMicro.computeInt32Size(3, getCommentRating());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasCommentRating()
    {
      return this.hasCommentRating;
    }

    public boolean hasCreatorId()
    {
      return this.hasCreatorId;
    }

    public RateCommentRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setCreatorId(paramCodedInputStreamMicro.readString());
          break;
        case 24:
        }
        setCommentRating(paramCodedInputStreamMicro.readInt32());
      }
    }

    public RateCommentRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public RateCommentRequestProto setCommentRating(int paramInt)
    {
      this.hasCommentRating = true;
      this.commentRating_ = paramInt;
      return this;
    }

    public RateCommentRequestProto setCreatorId(String paramString)
    {
      this.hasCreatorId = true;
      this.creatorId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasCreatorId())
        paramCodedOutputStreamMicro.writeString(2, getCreatorId());
      if (hasCommentRating())
        paramCodedOutputStreamMicro.writeInt32(3, getCommentRating());
    }
  }

  public static final class RateCommentResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public RateCommentResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }

  public static final class ReconstructDatabaseRequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasRetrieveFullHistory;
    private boolean retrieveFullHistory_ = false;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public boolean getRetrieveFullHistory()
    {
      return this.retrieveFullHistory_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRetrieveFullHistory())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getRetrieveFullHistory());
      this.cachedSize = i;
      return i;
    }

    public boolean hasRetrieveFullHistory()
    {
      return this.hasRetrieveFullHistory;
    }

    public ReconstructDatabaseRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setRetrieveFullHistory(paramCodedInputStreamMicro.readBool());
      }
    }

    public ReconstructDatabaseRequestProto setRetrieveFullHistory(boolean paramBoolean)
    {
      this.hasRetrieveFullHistory = true;
      this.retrieveFullHistory_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRetrieveFullHistory())
        paramCodedOutputStreamMicro.writeBool(1, getRetrieveFullHistory());
    }
  }

  public static final class ReconstructDatabaseResponseProto extends MessageMicro
  {
    private List<VendingProtos.AssetIdentifierProto> asset_ = Collections.emptyList();
    private int cachedSize = -1;

    public ReconstructDatabaseResponseProto addAsset(VendingProtos.AssetIdentifierProto paramAssetIdentifierProto)
    {
      if (paramAssetIdentifierProto == null)
        throw new NullPointerException();
      if (this.asset_.isEmpty())
        this.asset_ = new ArrayList();
      this.asset_.add(paramAssetIdentifierProto);
      return this;
    }

    public List<VendingProtos.AssetIdentifierProto> getAssetList()
    {
      return this.asset_;
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
      Iterator localIterator = getAssetList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.AssetIdentifierProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public ReconstructDatabaseResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.AssetIdentifierProto localAssetIdentifierProto = new VendingProtos.AssetIdentifierProto();
        paramCodedInputStreamMicro.readMessage(localAssetIdentifierProto);
        addAsset(localAssetIdentifierProto);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getAssetList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.AssetIdentifierProto)localIterator.next());
    }
  }

  public static final class RefundRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;

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
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public RefundRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setAssetId(paramCodedInputStreamMicro.readString());
      }
    }

    public RefundRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
    }
  }

  public static final class RefundResponseProto extends MessageMicro
  {
    private VendingProtos.ExternalAssetProto asset_ = null;
    private int cachedSize = -1;
    private boolean hasAsset;
    private boolean hasResult;
    private boolean hasResultDetail;
    private String resultDetail_ = "";
    private int result_ = 0;

    public VendingProtos.ExternalAssetProto getAsset()
    {
      return this.asset_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getResult()
    {
      return this.result_;
    }

    public String getResultDetail()
    {
      return this.resultDetail_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResult())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResult());
      if (hasAsset())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAsset());
      if (hasResultDetail())
        i += CodedOutputStreamMicro.computeStringSize(3, getResultDetail());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAsset()
    {
      return this.hasAsset;
    }

    public boolean hasResult()
    {
      return this.hasResult;
    }

    public boolean hasResultDetail()
    {
      return this.hasResultDetail;
    }

    public RefundResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResult(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          VendingProtos.ExternalAssetProto localExternalAssetProto = new VendingProtos.ExternalAssetProto();
          paramCodedInputStreamMicro.readMessage(localExternalAssetProto);
          setAsset(localExternalAssetProto);
          break;
        case 26:
        }
        setResultDetail(paramCodedInputStreamMicro.readString());
      }
    }

    public RefundResponseProto setAsset(VendingProtos.ExternalAssetProto paramExternalAssetProto)
    {
      if (paramExternalAssetProto == null)
        throw new NullPointerException();
      this.hasAsset = true;
      this.asset_ = paramExternalAssetProto;
      return this;
    }

    public RefundResponseProto setResult(int paramInt)
    {
      this.hasResult = true;
      this.result_ = paramInt;
      return this;
    }

    public RefundResponseProto setResultDetail(String paramString)
    {
      this.hasResultDetail = true;
      this.resultDetail_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResult())
        paramCodedOutputStreamMicro.writeInt32(1, getResult());
      if (hasAsset())
        paramCodedOutputStreamMicro.writeMessage(2, getAsset());
      if (hasResultDetail())
        paramCodedOutputStreamMicro.writeString(3, getResultDetail());
    }
  }

  public static final class RemoveAssetRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;

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
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public RemoveAssetRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setAssetId(paramCodedInputStreamMicro.readString());
      }
    }

    public RemoveAssetRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
    }
  }

  public static final class RequestPropertiesProto extends MessageMicro
  {
    private String aid_ = "";
    private int cachedSize = -1;
    private String clientId_ = "";
    private boolean hasAid;
    private boolean hasClientId;
    private boolean hasLoggingId;
    private boolean hasOperatorName;
    private boolean hasOperatorNumericName;
    private boolean hasProductNameAndVersion;
    private boolean hasSimOperatorName;
    private boolean hasSimOperatorNumericName;
    private boolean hasSoftwareVersion;
    private boolean hasUserAuthToken;
    private boolean hasUserAuthTokenSecure;
    private boolean hasUserCountry;
    private boolean hasUserLanguage;
    private String loggingId_ = "";
    private String operatorName_ = "";
    private String operatorNumericName_ = "";
    private String productNameAndVersion_ = "";
    private String simOperatorName_ = "";
    private String simOperatorNumericName_ = "";
    private int softwareVersion_ = 0;
    private boolean userAuthTokenSecure_ = false;
    private String userAuthToken_ = "";
    private String userCountry_ = "";
    private String userLanguage_ = "";

    public String getAid()
    {
      return this.aid_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getClientId()
    {
      return this.clientId_;
    }

    public String getLoggingId()
    {
      return this.loggingId_;
    }

    public String getOperatorName()
    {
      return this.operatorName_;
    }

    public String getOperatorNumericName()
    {
      return this.operatorNumericName_;
    }

    public String getProductNameAndVersion()
    {
      return this.productNameAndVersion_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUserAuthToken())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUserAuthToken());
      if (hasUserAuthTokenSecure())
        i += CodedOutputStreamMicro.computeBoolSize(2, getUserAuthTokenSecure());
      if (hasSoftwareVersion())
        i += CodedOutputStreamMicro.computeInt32Size(3, getSoftwareVersion());
      if (hasAid())
        i += CodedOutputStreamMicro.computeStringSize(4, getAid());
      if (hasProductNameAndVersion())
        i += CodedOutputStreamMicro.computeStringSize(5, getProductNameAndVersion());
      if (hasUserLanguage())
        i += CodedOutputStreamMicro.computeStringSize(6, getUserLanguage());
      if (hasUserCountry())
        i += CodedOutputStreamMicro.computeStringSize(7, getUserCountry());
      if (hasOperatorName())
        i += CodedOutputStreamMicro.computeStringSize(8, getOperatorName());
      if (hasSimOperatorName())
        i += CodedOutputStreamMicro.computeStringSize(9, getSimOperatorName());
      if (hasOperatorNumericName())
        i += CodedOutputStreamMicro.computeStringSize(10, getOperatorNumericName());
      if (hasSimOperatorNumericName())
        i += CodedOutputStreamMicro.computeStringSize(11, getSimOperatorNumericName());
      if (hasClientId())
        i += CodedOutputStreamMicro.computeStringSize(12, getClientId());
      if (hasLoggingId())
        i += CodedOutputStreamMicro.computeStringSize(13, getLoggingId());
      this.cachedSize = i;
      return i;
    }

    public String getSimOperatorName()
    {
      return this.simOperatorName_;
    }

    public String getSimOperatorNumericName()
    {
      return this.simOperatorNumericName_;
    }

    public int getSoftwareVersion()
    {
      return this.softwareVersion_;
    }

    public String getUserAuthToken()
    {
      return this.userAuthToken_;
    }

    public boolean getUserAuthTokenSecure()
    {
      return this.userAuthTokenSecure_;
    }

    public String getUserCountry()
    {
      return this.userCountry_;
    }

    public String getUserLanguage()
    {
      return this.userLanguage_;
    }

    public boolean hasAid()
    {
      return this.hasAid;
    }

    public boolean hasClientId()
    {
      return this.hasClientId;
    }

    public boolean hasLoggingId()
    {
      return this.hasLoggingId;
    }

    public boolean hasOperatorName()
    {
      return this.hasOperatorName;
    }

    public boolean hasOperatorNumericName()
    {
      return this.hasOperatorNumericName;
    }

    public boolean hasProductNameAndVersion()
    {
      return this.hasProductNameAndVersion;
    }

    public boolean hasSimOperatorName()
    {
      return this.hasSimOperatorName;
    }

    public boolean hasSimOperatorNumericName()
    {
      return this.hasSimOperatorNumericName;
    }

    public boolean hasSoftwareVersion()
    {
      return this.hasSoftwareVersion;
    }

    public boolean hasUserAuthToken()
    {
      return this.hasUserAuthToken;
    }

    public boolean hasUserAuthTokenSecure()
    {
      return this.hasUserAuthTokenSecure;
    }

    public boolean hasUserCountry()
    {
      return this.hasUserCountry;
    }

    public boolean hasUserLanguage()
    {
      return this.hasUserLanguage;
    }

    public RequestPropertiesProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setUserAuthToken(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setUserAuthTokenSecure(paramCodedInputStreamMicro.readBool());
          break;
        case 24:
          setSoftwareVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
          setAid(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setProductNameAndVersion(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          setUserLanguage(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setUserCountry(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          setOperatorName(paramCodedInputStreamMicro.readString());
          break;
        case 74:
          setSimOperatorName(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          setOperatorNumericName(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setSimOperatorNumericName(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setClientId(paramCodedInputStreamMicro.readString());
          break;
        case 106:
        }
        setLoggingId(paramCodedInputStreamMicro.readString());
      }
    }

    public RequestPropertiesProto setAid(String paramString)
    {
      this.hasAid = true;
      this.aid_ = paramString;
      return this;
    }

    public RequestPropertiesProto setClientId(String paramString)
    {
      this.hasClientId = true;
      this.clientId_ = paramString;
      return this;
    }

    public RequestPropertiesProto setLoggingId(String paramString)
    {
      this.hasLoggingId = true;
      this.loggingId_ = paramString;
      return this;
    }

    public RequestPropertiesProto setOperatorName(String paramString)
    {
      this.hasOperatorName = true;
      this.operatorName_ = paramString;
      return this;
    }

    public RequestPropertiesProto setOperatorNumericName(String paramString)
    {
      this.hasOperatorNumericName = true;
      this.operatorNumericName_ = paramString;
      return this;
    }

    public RequestPropertiesProto setProductNameAndVersion(String paramString)
    {
      this.hasProductNameAndVersion = true;
      this.productNameAndVersion_ = paramString;
      return this;
    }

    public RequestPropertiesProto setSimOperatorName(String paramString)
    {
      this.hasSimOperatorName = true;
      this.simOperatorName_ = paramString;
      return this;
    }

    public RequestPropertiesProto setSimOperatorNumericName(String paramString)
    {
      this.hasSimOperatorNumericName = true;
      this.simOperatorNumericName_ = paramString;
      return this;
    }

    public RequestPropertiesProto setSoftwareVersion(int paramInt)
    {
      this.hasSoftwareVersion = true;
      this.softwareVersion_ = paramInt;
      return this;
    }

    public RequestPropertiesProto setUserAuthToken(String paramString)
    {
      this.hasUserAuthToken = true;
      this.userAuthToken_ = paramString;
      return this;
    }

    public RequestPropertiesProto setUserAuthTokenSecure(boolean paramBoolean)
    {
      this.hasUserAuthTokenSecure = true;
      this.userAuthTokenSecure_ = paramBoolean;
      return this;
    }

    public RequestPropertiesProto setUserCountry(String paramString)
    {
      this.hasUserCountry = true;
      this.userCountry_ = paramString;
      return this;
    }

    public RequestPropertiesProto setUserLanguage(String paramString)
    {
      this.hasUserLanguage = true;
      this.userLanguage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUserAuthToken())
        paramCodedOutputStreamMicro.writeString(1, getUserAuthToken());
      if (hasUserAuthTokenSecure())
        paramCodedOutputStreamMicro.writeBool(2, getUserAuthTokenSecure());
      if (hasSoftwareVersion())
        paramCodedOutputStreamMicro.writeInt32(3, getSoftwareVersion());
      if (hasAid())
        paramCodedOutputStreamMicro.writeString(4, getAid());
      if (hasProductNameAndVersion())
        paramCodedOutputStreamMicro.writeString(5, getProductNameAndVersion());
      if (hasUserLanguage())
        paramCodedOutputStreamMicro.writeString(6, getUserLanguage());
      if (hasUserCountry())
        paramCodedOutputStreamMicro.writeString(7, getUserCountry());
      if (hasOperatorName())
        paramCodedOutputStreamMicro.writeString(8, getOperatorName());
      if (hasSimOperatorName())
        paramCodedOutputStreamMicro.writeString(9, getSimOperatorName());
      if (hasOperatorNumericName())
        paramCodedOutputStreamMicro.writeString(10, getOperatorNumericName());
      if (hasSimOperatorNumericName())
        paramCodedOutputStreamMicro.writeString(11, getSimOperatorNumericName());
      if (hasClientId())
        paramCodedOutputStreamMicro.writeString(12, getClientId());
      if (hasLoggingId())
        paramCodedOutputStreamMicro.writeString(13, getLoggingId());
    }
  }

  public static final class RequestProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasRequestProperties;
    private VendingProtos.RequestPropertiesProto requestProperties_ = null;
    private List<Request> request_ = Collections.emptyList();

    public RequestProto addRequest(Request paramRequest)
    {
      if (paramRequest == null)
        throw new NullPointerException();
      if (this.request_.isEmpty())
        this.request_ = new ArrayList();
      this.request_.add(paramRequest);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<Request> getRequestList()
    {
      return this.request_;
    }

    public VendingProtos.RequestPropertiesProto getRequestProperties()
    {
      return this.requestProperties_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRequestProperties())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getRequestProperties());
      Iterator localIterator = getRequestList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(2, (Request)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean hasRequestProperties()
    {
      return this.hasRequestProperties;
    }

    public RequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          VendingProtos.RequestPropertiesProto localRequestPropertiesProto = new VendingProtos.RequestPropertiesProto();
          paramCodedInputStreamMicro.readMessage(localRequestPropertiesProto);
          setRequestProperties(localRequestPropertiesProto);
          break;
        case 19:
        }
        Request localRequest = new Request();
        paramCodedInputStreamMicro.readGroup(localRequest, 2);
        addRequest(localRequest);
      }
    }

    public RequestProto setRequestProperties(VendingProtos.RequestPropertiesProto paramRequestPropertiesProto)
    {
      if (paramRequestPropertiesProto == null)
        throw new NullPointerException();
      this.hasRequestProperties = true;
      this.requestProperties_ = paramRequestPropertiesProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRequestProperties())
        paramCodedOutputStreamMicro.writeMessage(1, getRequestProperties());
      Iterator localIterator = getRequestList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeGroup(2, (Request)localIterator.next());
    }

    public static final class Request extends MessageMicro
    {
      private VendingProtos.AckNotificationsRequestProto ackNotificationsRequest_ = null;
      private VendingProtos.AssetsRequestProto assetRequest_ = null;
      private VendingProtos.BillingEventRequestProto billingEventRequest_ = null;
      private int cachedSize = -1;
      private VendingProtos.CheckForNotificationsRequestProto checkForNotificationsRequest_ = null;
      private VendingProtos.CheckLicenseRequestProto checkLicenseRequest_ = null;
      private VendingProtos.CommentsRequestProto commentsRequest_ = null;
      private VendingProtos.ContentSyncRequestProto contentSyncRequest_ = null;
      private VendingProtos.GetAddressSnippetRequestProto getAddressSnippetRequest_ = null;
      private VendingProtos.GetAssetRequestProto getAssetRequest_ = null;
      private VendingProtos.GetCarrierInfoRequestProto getCarrierInfoRequest_ = null;
      private VendingProtos.GetCategoriesRequestProto getCategoriesRequest_ = null;
      private VendingProtos.GetImageRequestProto getImageRequest_ = null;
      private VendingProtos.GetMarketMetadataRequestProto getMarketMetadataRequest_ = null;
      private boolean hasAckNotificationsRequest;
      private boolean hasAssetRequest;
      private boolean hasBillingEventRequest;
      private boolean hasCheckForNotificationsRequest;
      private boolean hasCheckLicenseRequest;
      private boolean hasCommentsRequest;
      private boolean hasContentSyncRequest;
      private boolean hasGetAddressSnippetRequest;
      private boolean hasGetAssetRequest;
      private boolean hasGetCarrierInfoRequest;
      private boolean hasGetCategoriesRequest;
      private boolean hasGetImageRequest;
      private boolean hasGetMarketMetadataRequest;
      private boolean hasInAppPurchaseInformationRequest;
      private boolean hasInAppRestoreTransactionsRequest;
      private boolean hasModifyCommentRequest;
      private boolean hasPaypalCreateAccountRequest;
      private boolean hasPaypalMassageAddressRequest;
      private boolean hasPaypalPreapprovalCredentialsRequest;
      private boolean hasPaypalPreapprovalDetailsRequest;
      private boolean hasPaypalPreapprovalRequest;
      private boolean hasPurchaseMetadataRequest;
      private boolean hasPurchaseOrderRequest;
      private boolean hasPurchasePostRequest;
      private boolean hasPurchaseProductRequest;
      private boolean hasQuerySuggestionRequest;
      private boolean hasRateCommentRequest;
      private boolean hasReconstructDatabaseRequest;
      private boolean hasRefundRequest;
      private boolean hasRemoveAssetRequest;
      private boolean hasRequestSpecificProperties;
      private boolean hasRestoreApplicationsRequest;
      private boolean hasSubCategoriesRequest;
      private boolean hasUninstallReasonRequest;
      private VendingProtos.InAppPurchaseInformationRequestProto inAppPurchaseInformationRequest_ = null;
      private VendingProtos.InAppRestoreTransactionsRequestProto inAppRestoreTransactionsRequest_ = null;
      private VendingProtos.ModifyCommentRequestProto modifyCommentRequest_ = null;
      private VendingProtos.PaypalCreateAccountRequestProto paypalCreateAccountRequest_ = null;
      private VendingProtos.PaypalMassageAddressRequestProto paypalMassageAddressRequest_ = null;
      private VendingProtos.PaypalPreapprovalCredentialsRequestProto paypalPreapprovalCredentialsRequest_ = null;
      private VendingProtos.PaypalPreapprovalDetailsRequestProto paypalPreapprovalDetailsRequest_ = null;
      private VendingProtos.PaypalPreapprovalRequestProto paypalPreapprovalRequest_ = null;
      private VendingProtos.PurchaseMetadataRequestProto purchaseMetadataRequest_ = null;
      private VendingProtos.PurchaseOrderRequestProto purchaseOrderRequest_ = null;
      private VendingProtos.PurchasePostRequestProto purchasePostRequest_ = null;
      private VendingProtos.PurchaseProductRequestProto purchaseProductRequest_ = null;
      private VendingProtos.QuerySuggestionRequestProto querySuggestionRequest_ = null;
      private VendingProtos.RateCommentRequestProto rateCommentRequest_ = null;
      private VendingProtos.ReconstructDatabaseRequestProto reconstructDatabaseRequest_ = null;
      private VendingProtos.RefundRequestProto refundRequest_ = null;
      private VendingProtos.RemoveAssetRequestProto removeAssetRequest_ = null;
      private VendingProtos.RequestSpecificPropertiesProto requestSpecificProperties_ = null;
      private VendingProtos.RestoreApplicationsRequestProto restoreApplicationsRequest_ = null;
      private VendingProtos.GetSubCategoriesRequestProto subCategoriesRequest_ = null;
      private VendingProtos.UninstallReasonRequestProto uninstallReasonRequest_ = null;

      public VendingProtos.AckNotificationsRequestProto getAckNotificationsRequest()
      {
        return this.ackNotificationsRequest_;
      }

      public VendingProtos.AssetsRequestProto getAssetRequest()
      {
        return this.assetRequest_;
      }

      public VendingProtos.BillingEventRequestProto getBillingEventRequest()
      {
        return this.billingEventRequest_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public VendingProtos.CheckForNotificationsRequestProto getCheckForNotificationsRequest()
      {
        return this.checkForNotificationsRequest_;
      }

      public VendingProtos.CheckLicenseRequestProto getCheckLicenseRequest()
      {
        return this.checkLicenseRequest_;
      }

      public VendingProtos.CommentsRequestProto getCommentsRequest()
      {
        return this.commentsRequest_;
      }

      public VendingProtos.ContentSyncRequestProto getContentSyncRequest()
      {
        return this.contentSyncRequest_;
      }

      public VendingProtos.GetAddressSnippetRequestProto getGetAddressSnippetRequest()
      {
        return this.getAddressSnippetRequest_;
      }

      public VendingProtos.GetAssetRequestProto getGetAssetRequest()
      {
        return this.getAssetRequest_;
      }

      public VendingProtos.GetCarrierInfoRequestProto getGetCarrierInfoRequest()
      {
        return this.getCarrierInfoRequest_;
      }

      public VendingProtos.GetCategoriesRequestProto getGetCategoriesRequest()
      {
        return this.getCategoriesRequest_;
      }

      public VendingProtos.GetImageRequestProto getGetImageRequest()
      {
        return this.getImageRequest_;
      }

      public VendingProtos.GetMarketMetadataRequestProto getGetMarketMetadataRequest()
      {
        return this.getMarketMetadataRequest_;
      }

      public VendingProtos.InAppPurchaseInformationRequestProto getInAppPurchaseInformationRequest()
      {
        return this.inAppPurchaseInformationRequest_;
      }

      public VendingProtos.InAppRestoreTransactionsRequestProto getInAppRestoreTransactionsRequest()
      {
        return this.inAppRestoreTransactionsRequest_;
      }

      public VendingProtos.ModifyCommentRequestProto getModifyCommentRequest()
      {
        return this.modifyCommentRequest_;
      }

      public VendingProtos.PaypalCreateAccountRequestProto getPaypalCreateAccountRequest()
      {
        return this.paypalCreateAccountRequest_;
      }

      public VendingProtos.PaypalMassageAddressRequestProto getPaypalMassageAddressRequest()
      {
        return this.paypalMassageAddressRequest_;
      }

      public VendingProtos.PaypalPreapprovalCredentialsRequestProto getPaypalPreapprovalCredentialsRequest()
      {
        return this.paypalPreapprovalCredentialsRequest_;
      }

      public VendingProtos.PaypalPreapprovalDetailsRequestProto getPaypalPreapprovalDetailsRequest()
      {
        return this.paypalPreapprovalDetailsRequest_;
      }

      public VendingProtos.PaypalPreapprovalRequestProto getPaypalPreapprovalRequest()
      {
        return this.paypalPreapprovalRequest_;
      }

      public VendingProtos.PurchaseMetadataRequestProto getPurchaseMetadataRequest()
      {
        return this.purchaseMetadataRequest_;
      }

      public VendingProtos.PurchaseOrderRequestProto getPurchaseOrderRequest()
      {
        return this.purchaseOrderRequest_;
      }

      public VendingProtos.PurchasePostRequestProto getPurchasePostRequest()
      {
        return this.purchasePostRequest_;
      }

      public VendingProtos.PurchaseProductRequestProto getPurchaseProductRequest()
      {
        return this.purchaseProductRequest_;
      }

      public VendingProtos.QuerySuggestionRequestProto getQuerySuggestionRequest()
      {
        return this.querySuggestionRequest_;
      }

      public VendingProtos.RateCommentRequestProto getRateCommentRequest()
      {
        return this.rateCommentRequest_;
      }

      public VendingProtos.ReconstructDatabaseRequestProto getReconstructDatabaseRequest()
      {
        return this.reconstructDatabaseRequest_;
      }

      public VendingProtos.RefundRequestProto getRefundRequest()
      {
        return this.refundRequest_;
      }

      public VendingProtos.RemoveAssetRequestProto getRemoveAssetRequest()
      {
        return this.removeAssetRequest_;
      }

      public VendingProtos.RequestSpecificPropertiesProto getRequestSpecificProperties()
      {
        return this.requestSpecificProperties_;
      }

      public VendingProtos.RestoreApplicationsRequestProto getRestoreApplicationsRequest()
      {
        return this.restoreApplicationsRequest_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasRequestSpecificProperties())
          i = 0 + CodedOutputStreamMicro.computeMessageSize(3, getRequestSpecificProperties());
        if (hasAssetRequest())
          i += CodedOutputStreamMicro.computeMessageSize(4, getAssetRequest());
        if (hasCommentsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(5, getCommentsRequest());
        if (hasModifyCommentRequest())
          i += CodedOutputStreamMicro.computeMessageSize(6, getModifyCommentRequest());
        if (hasPurchasePostRequest())
          i += CodedOutputStreamMicro.computeMessageSize(7, getPurchasePostRequest());
        if (hasPurchaseOrderRequest())
          i += CodedOutputStreamMicro.computeMessageSize(8, getPurchaseOrderRequest());
        if (hasContentSyncRequest())
          i += CodedOutputStreamMicro.computeMessageSize(9, getContentSyncRequest());
        if (hasGetAssetRequest())
          i += CodedOutputStreamMicro.computeMessageSize(10, getGetAssetRequest());
        if (hasGetImageRequest())
          i += CodedOutputStreamMicro.computeMessageSize(11, getGetImageRequest());
        if (hasRefundRequest())
          i += CodedOutputStreamMicro.computeMessageSize(12, getRefundRequest());
        if (hasPurchaseMetadataRequest())
          i += CodedOutputStreamMicro.computeMessageSize(13, getPurchaseMetadataRequest());
        if (hasSubCategoriesRequest())
          i += CodedOutputStreamMicro.computeMessageSize(14, getSubCategoriesRequest());
        if (hasUninstallReasonRequest())
          i += CodedOutputStreamMicro.computeMessageSize(16, getUninstallReasonRequest());
        if (hasRateCommentRequest())
          i += CodedOutputStreamMicro.computeMessageSize(17, getRateCommentRequest());
        if (hasCheckLicenseRequest())
          i += CodedOutputStreamMicro.computeMessageSize(18, getCheckLicenseRequest());
        if (hasGetMarketMetadataRequest())
          i += CodedOutputStreamMicro.computeMessageSize(19, getGetMarketMetadataRequest());
        if (hasGetCategoriesRequest())
          i += CodedOutputStreamMicro.computeMessageSize(21, getGetCategoriesRequest());
        if (hasGetCarrierInfoRequest())
          i += CodedOutputStreamMicro.computeMessageSize(22, getGetCarrierInfoRequest());
        if (hasRemoveAssetRequest())
          i += CodedOutputStreamMicro.computeMessageSize(23, getRemoveAssetRequest());
        if (hasRestoreApplicationsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(24, getRestoreApplicationsRequest());
        if (hasQuerySuggestionRequest())
          i += CodedOutputStreamMicro.computeMessageSize(25, getQuerySuggestionRequest());
        if (hasBillingEventRequest())
          i += CodedOutputStreamMicro.computeMessageSize(26, getBillingEventRequest());
        if (hasPaypalPreapprovalRequest())
          i += CodedOutputStreamMicro.computeMessageSize(27, getPaypalPreapprovalRequest());
        if (hasPaypalPreapprovalDetailsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(28, getPaypalPreapprovalDetailsRequest());
        if (hasPaypalCreateAccountRequest())
          i += CodedOutputStreamMicro.computeMessageSize(29, getPaypalCreateAccountRequest());
        if (hasPaypalPreapprovalCredentialsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(30, getPaypalPreapprovalCredentialsRequest());
        if (hasInAppRestoreTransactionsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(31, getInAppRestoreTransactionsRequest());
        if (hasInAppPurchaseInformationRequest())
          i += CodedOutputStreamMicro.computeMessageSize(32, getInAppPurchaseInformationRequest());
        if (hasCheckForNotificationsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(33, getCheckForNotificationsRequest());
        if (hasAckNotificationsRequest())
          i += CodedOutputStreamMicro.computeMessageSize(34, getAckNotificationsRequest());
        if (hasPurchaseProductRequest())
          i += CodedOutputStreamMicro.computeMessageSize(35, getPurchaseProductRequest());
        if (hasReconstructDatabaseRequest())
          i += CodedOutputStreamMicro.computeMessageSize(36, getReconstructDatabaseRequest());
        if (hasPaypalMassageAddressRequest())
          i += CodedOutputStreamMicro.computeMessageSize(37, getPaypalMassageAddressRequest());
        if (hasGetAddressSnippetRequest())
          i += CodedOutputStreamMicro.computeMessageSize(38, getGetAddressSnippetRequest());
        this.cachedSize = i;
        return i;
      }

      public VendingProtos.GetSubCategoriesRequestProto getSubCategoriesRequest()
      {
        return this.subCategoriesRequest_;
      }

      public VendingProtos.UninstallReasonRequestProto getUninstallReasonRequest()
      {
        return this.uninstallReasonRequest_;
      }

      public boolean hasAckNotificationsRequest()
      {
        return this.hasAckNotificationsRequest;
      }

      public boolean hasAssetRequest()
      {
        return this.hasAssetRequest;
      }

      public boolean hasBillingEventRequest()
      {
        return this.hasBillingEventRequest;
      }

      public boolean hasCheckForNotificationsRequest()
      {
        return this.hasCheckForNotificationsRequest;
      }

      public boolean hasCheckLicenseRequest()
      {
        return this.hasCheckLicenseRequest;
      }

      public boolean hasCommentsRequest()
      {
        return this.hasCommentsRequest;
      }

      public boolean hasContentSyncRequest()
      {
        return this.hasContentSyncRequest;
      }

      public boolean hasGetAddressSnippetRequest()
      {
        return this.hasGetAddressSnippetRequest;
      }

      public boolean hasGetAssetRequest()
      {
        return this.hasGetAssetRequest;
      }

      public boolean hasGetCarrierInfoRequest()
      {
        return this.hasGetCarrierInfoRequest;
      }

      public boolean hasGetCategoriesRequest()
      {
        return this.hasGetCategoriesRequest;
      }

      public boolean hasGetImageRequest()
      {
        return this.hasGetImageRequest;
      }

      public boolean hasGetMarketMetadataRequest()
      {
        return this.hasGetMarketMetadataRequest;
      }

      public boolean hasInAppPurchaseInformationRequest()
      {
        return this.hasInAppPurchaseInformationRequest;
      }

      public boolean hasInAppRestoreTransactionsRequest()
      {
        return this.hasInAppRestoreTransactionsRequest;
      }

      public boolean hasModifyCommentRequest()
      {
        return this.hasModifyCommentRequest;
      }

      public boolean hasPaypalCreateAccountRequest()
      {
        return this.hasPaypalCreateAccountRequest;
      }

      public boolean hasPaypalMassageAddressRequest()
      {
        return this.hasPaypalMassageAddressRequest;
      }

      public boolean hasPaypalPreapprovalCredentialsRequest()
      {
        return this.hasPaypalPreapprovalCredentialsRequest;
      }

      public boolean hasPaypalPreapprovalDetailsRequest()
      {
        return this.hasPaypalPreapprovalDetailsRequest;
      }

      public boolean hasPaypalPreapprovalRequest()
      {
        return this.hasPaypalPreapprovalRequest;
      }

      public boolean hasPurchaseMetadataRequest()
      {
        return this.hasPurchaseMetadataRequest;
      }

      public boolean hasPurchaseOrderRequest()
      {
        return this.hasPurchaseOrderRequest;
      }

      public boolean hasPurchasePostRequest()
      {
        return this.hasPurchasePostRequest;
      }

      public boolean hasPurchaseProductRequest()
      {
        return this.hasPurchaseProductRequest;
      }

      public boolean hasQuerySuggestionRequest()
      {
        return this.hasQuerySuggestionRequest;
      }

      public boolean hasRateCommentRequest()
      {
        return this.hasRateCommentRequest;
      }

      public boolean hasReconstructDatabaseRequest()
      {
        return this.hasReconstructDatabaseRequest;
      }

      public boolean hasRefundRequest()
      {
        return this.hasRefundRequest;
      }

      public boolean hasRemoveAssetRequest()
      {
        return this.hasRemoveAssetRequest;
      }

      public boolean hasRequestSpecificProperties()
      {
        return this.hasRequestSpecificProperties;
      }

      public boolean hasRestoreApplicationsRequest()
      {
        return this.hasRestoreApplicationsRequest;
      }

      public boolean hasSubCategoriesRequest()
      {
        return this.hasSubCategoriesRequest;
      }

      public boolean hasUninstallReasonRequest()
      {
        return this.hasUninstallReasonRequest;
      }

      public Request mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 26:
            VendingProtos.RequestSpecificPropertiesProto localRequestSpecificPropertiesProto = new VendingProtos.RequestSpecificPropertiesProto();
            paramCodedInputStreamMicro.readMessage(localRequestSpecificPropertiesProto);
            setRequestSpecificProperties(localRequestSpecificPropertiesProto);
            break;
          case 34:
            VendingProtos.AssetsRequestProto localAssetsRequestProto = new VendingProtos.AssetsRequestProto();
            paramCodedInputStreamMicro.readMessage(localAssetsRequestProto);
            setAssetRequest(localAssetsRequestProto);
            break;
          case 42:
            VendingProtos.CommentsRequestProto localCommentsRequestProto = new VendingProtos.CommentsRequestProto();
            paramCodedInputStreamMicro.readMessage(localCommentsRequestProto);
            setCommentsRequest(localCommentsRequestProto);
            break;
          case 50:
            VendingProtos.ModifyCommentRequestProto localModifyCommentRequestProto = new VendingProtos.ModifyCommentRequestProto();
            paramCodedInputStreamMicro.readMessage(localModifyCommentRequestProto);
            setModifyCommentRequest(localModifyCommentRequestProto);
            break;
          case 58:
            VendingProtos.PurchasePostRequestProto localPurchasePostRequestProto = new VendingProtos.PurchasePostRequestProto();
            paramCodedInputStreamMicro.readMessage(localPurchasePostRequestProto);
            setPurchasePostRequest(localPurchasePostRequestProto);
            break;
          case 66:
            VendingProtos.PurchaseOrderRequestProto localPurchaseOrderRequestProto = new VendingProtos.PurchaseOrderRequestProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseOrderRequestProto);
            setPurchaseOrderRequest(localPurchaseOrderRequestProto);
            break;
          case 74:
            VendingProtos.ContentSyncRequestProto localContentSyncRequestProto = new VendingProtos.ContentSyncRequestProto();
            paramCodedInputStreamMicro.readMessage(localContentSyncRequestProto);
            setContentSyncRequest(localContentSyncRequestProto);
            break;
          case 82:
            VendingProtos.GetAssetRequestProto localGetAssetRequestProto = new VendingProtos.GetAssetRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetAssetRequestProto);
            setGetAssetRequest(localGetAssetRequestProto);
            break;
          case 90:
            VendingProtos.GetImageRequestProto localGetImageRequestProto = new VendingProtos.GetImageRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetImageRequestProto);
            setGetImageRequest(localGetImageRequestProto);
            break;
          case 98:
            VendingProtos.RefundRequestProto localRefundRequestProto = new VendingProtos.RefundRequestProto();
            paramCodedInputStreamMicro.readMessage(localRefundRequestProto);
            setRefundRequest(localRefundRequestProto);
            break;
          case 106:
            VendingProtos.PurchaseMetadataRequestProto localPurchaseMetadataRequestProto = new VendingProtos.PurchaseMetadataRequestProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseMetadataRequestProto);
            setPurchaseMetadataRequest(localPurchaseMetadataRequestProto);
            break;
          case 114:
            VendingProtos.GetSubCategoriesRequestProto localGetSubCategoriesRequestProto = new VendingProtos.GetSubCategoriesRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetSubCategoriesRequestProto);
            setSubCategoriesRequest(localGetSubCategoriesRequestProto);
            break;
          case 130:
            VendingProtos.UninstallReasonRequestProto localUninstallReasonRequestProto = new VendingProtos.UninstallReasonRequestProto();
            paramCodedInputStreamMicro.readMessage(localUninstallReasonRequestProto);
            setUninstallReasonRequest(localUninstallReasonRequestProto);
            break;
          case 138:
            VendingProtos.RateCommentRequestProto localRateCommentRequestProto = new VendingProtos.RateCommentRequestProto();
            paramCodedInputStreamMicro.readMessage(localRateCommentRequestProto);
            setRateCommentRequest(localRateCommentRequestProto);
            break;
          case 146:
            VendingProtos.CheckLicenseRequestProto localCheckLicenseRequestProto = new VendingProtos.CheckLicenseRequestProto();
            paramCodedInputStreamMicro.readMessage(localCheckLicenseRequestProto);
            setCheckLicenseRequest(localCheckLicenseRequestProto);
            break;
          case 154:
            VendingProtos.GetMarketMetadataRequestProto localGetMarketMetadataRequestProto = new VendingProtos.GetMarketMetadataRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetMarketMetadataRequestProto);
            setGetMarketMetadataRequest(localGetMarketMetadataRequestProto);
            break;
          case 170:
            VendingProtos.GetCategoriesRequestProto localGetCategoriesRequestProto = new VendingProtos.GetCategoriesRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetCategoriesRequestProto);
            setGetCategoriesRequest(localGetCategoriesRequestProto);
            break;
          case 178:
            VendingProtos.GetCarrierInfoRequestProto localGetCarrierInfoRequestProto = new VendingProtos.GetCarrierInfoRequestProto();
            paramCodedInputStreamMicro.readMessage(localGetCarrierInfoRequestProto);
            setGetCarrierInfoRequest(localGetCarrierInfoRequestProto);
            break;
          case 186:
            VendingProtos.RemoveAssetRequestProto localRemoveAssetRequestProto = new VendingProtos.RemoveAssetRequestProto();
            paramCodedInputStreamMicro.readMessage(localRemoveAssetRequestProto);
            setRemoveAssetRequest(localRemoveAssetRequestProto);
            break;
          case 194:
            VendingProtos.RestoreApplicationsRequestProto localRestoreApplicationsRequestProto = new VendingProtos.RestoreApplicationsRequestProto();
            paramCodedInputStreamMicro.readMessage(localRestoreApplicationsRequestProto);
            setRestoreApplicationsRequest(localRestoreApplicationsRequestProto);
            break;
          case 202:
            VendingProtos.QuerySuggestionRequestProto localQuerySuggestionRequestProto = new VendingProtos.QuerySuggestionRequestProto();
            paramCodedInputStreamMicro.readMessage(localQuerySuggestionRequestProto);
            setQuerySuggestionRequest(localQuerySuggestionRequestProto);
            break;
          case 210:
            VendingProtos.BillingEventRequestProto localBillingEventRequestProto = new VendingProtos.BillingEventRequestProto();
            paramCodedInputStreamMicro.readMessage(localBillingEventRequestProto);
            setBillingEventRequest(localBillingEventRequestProto);
            break;
          case 218:
            VendingProtos.PaypalPreapprovalRequestProto localPaypalPreapprovalRequestProto = new VendingProtos.PaypalPreapprovalRequestProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalRequestProto);
            setPaypalPreapprovalRequest(localPaypalPreapprovalRequestProto);
            break;
          case 226:
            VendingProtos.PaypalPreapprovalDetailsRequestProto localPaypalPreapprovalDetailsRequestProto = new VendingProtos.PaypalPreapprovalDetailsRequestProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalDetailsRequestProto);
            setPaypalPreapprovalDetailsRequest(localPaypalPreapprovalDetailsRequestProto);
            break;
          case 234:
            VendingProtos.PaypalCreateAccountRequestProto localPaypalCreateAccountRequestProto = new VendingProtos.PaypalCreateAccountRequestProto();
            paramCodedInputStreamMicro.readMessage(localPaypalCreateAccountRequestProto);
            setPaypalCreateAccountRequest(localPaypalCreateAccountRequestProto);
            break;
          case 242:
            VendingProtos.PaypalPreapprovalCredentialsRequestProto localPaypalPreapprovalCredentialsRequestProto = new VendingProtos.PaypalPreapprovalCredentialsRequestProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalCredentialsRequestProto);
            setPaypalPreapprovalCredentialsRequest(localPaypalPreapprovalCredentialsRequestProto);
            break;
          case 250:
            VendingProtos.InAppRestoreTransactionsRequestProto localInAppRestoreTransactionsRequestProto = new VendingProtos.InAppRestoreTransactionsRequestProto();
            paramCodedInputStreamMicro.readMessage(localInAppRestoreTransactionsRequestProto);
            setInAppRestoreTransactionsRequest(localInAppRestoreTransactionsRequestProto);
            break;
          case 258:
            VendingProtos.InAppPurchaseInformationRequestProto localInAppPurchaseInformationRequestProto = new VendingProtos.InAppPurchaseInformationRequestProto();
            paramCodedInputStreamMicro.readMessage(localInAppPurchaseInformationRequestProto);
            setInAppPurchaseInformationRequest(localInAppPurchaseInformationRequestProto);
            break;
          case 266:
            VendingProtos.CheckForNotificationsRequestProto localCheckForNotificationsRequestProto = new VendingProtos.CheckForNotificationsRequestProto();
            paramCodedInputStreamMicro.readMessage(localCheckForNotificationsRequestProto);
            setCheckForNotificationsRequest(localCheckForNotificationsRequestProto);
            break;
          case 274:
            VendingProtos.AckNotificationsRequestProto localAckNotificationsRequestProto = new VendingProtos.AckNotificationsRequestProto();
            paramCodedInputStreamMicro.readMessage(localAckNotificationsRequestProto);
            setAckNotificationsRequest(localAckNotificationsRequestProto);
            break;
          case 282:
            VendingProtos.PurchaseProductRequestProto localPurchaseProductRequestProto = new VendingProtos.PurchaseProductRequestProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseProductRequestProto);
            setPurchaseProductRequest(localPurchaseProductRequestProto);
            break;
          case 290:
            VendingProtos.ReconstructDatabaseRequestProto localReconstructDatabaseRequestProto = new VendingProtos.ReconstructDatabaseRequestProto();
            paramCodedInputStreamMicro.readMessage(localReconstructDatabaseRequestProto);
            setReconstructDatabaseRequest(localReconstructDatabaseRequestProto);
            break;
          case 298:
            VendingProtos.PaypalMassageAddressRequestProto localPaypalMassageAddressRequestProto = new VendingProtos.PaypalMassageAddressRequestProto();
            paramCodedInputStreamMicro.readMessage(localPaypalMassageAddressRequestProto);
            setPaypalMassageAddressRequest(localPaypalMassageAddressRequestProto);
            break;
          case 306:
          }
          VendingProtos.GetAddressSnippetRequestProto localGetAddressSnippetRequestProto = new VendingProtos.GetAddressSnippetRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetAddressSnippetRequestProto);
          setGetAddressSnippetRequest(localGetAddressSnippetRequestProto);
        }
      }

      public Request setAckNotificationsRequest(VendingProtos.AckNotificationsRequestProto paramAckNotificationsRequestProto)
      {
        if (paramAckNotificationsRequestProto == null)
          throw new NullPointerException();
        this.hasAckNotificationsRequest = true;
        this.ackNotificationsRequest_ = paramAckNotificationsRequestProto;
        return this;
      }

      public Request setAssetRequest(VendingProtos.AssetsRequestProto paramAssetsRequestProto)
      {
        if (paramAssetsRequestProto == null)
          throw new NullPointerException();
        this.hasAssetRequest = true;
        this.assetRequest_ = paramAssetsRequestProto;
        return this;
      }

      public Request setBillingEventRequest(VendingProtos.BillingEventRequestProto paramBillingEventRequestProto)
      {
        if (paramBillingEventRequestProto == null)
          throw new NullPointerException();
        this.hasBillingEventRequest = true;
        this.billingEventRequest_ = paramBillingEventRequestProto;
        return this;
      }

      public Request setCheckForNotificationsRequest(VendingProtos.CheckForNotificationsRequestProto paramCheckForNotificationsRequestProto)
      {
        if (paramCheckForNotificationsRequestProto == null)
          throw new NullPointerException();
        this.hasCheckForNotificationsRequest = true;
        this.checkForNotificationsRequest_ = paramCheckForNotificationsRequestProto;
        return this;
      }

      public Request setCheckLicenseRequest(VendingProtos.CheckLicenseRequestProto paramCheckLicenseRequestProto)
      {
        if (paramCheckLicenseRequestProto == null)
          throw new NullPointerException();
        this.hasCheckLicenseRequest = true;
        this.checkLicenseRequest_ = paramCheckLicenseRequestProto;
        return this;
      }

      public Request setCommentsRequest(VendingProtos.CommentsRequestProto paramCommentsRequestProto)
      {
        if (paramCommentsRequestProto == null)
          throw new NullPointerException();
        this.hasCommentsRequest = true;
        this.commentsRequest_ = paramCommentsRequestProto;
        return this;
      }

      public Request setContentSyncRequest(VendingProtos.ContentSyncRequestProto paramContentSyncRequestProto)
      {
        if (paramContentSyncRequestProto == null)
          throw new NullPointerException();
        this.hasContentSyncRequest = true;
        this.contentSyncRequest_ = paramContentSyncRequestProto;
        return this;
      }

      public Request setGetAddressSnippetRequest(VendingProtos.GetAddressSnippetRequestProto paramGetAddressSnippetRequestProto)
      {
        if (paramGetAddressSnippetRequestProto == null)
          throw new NullPointerException();
        this.hasGetAddressSnippetRequest = true;
        this.getAddressSnippetRequest_ = paramGetAddressSnippetRequestProto;
        return this;
      }

      public Request setGetAssetRequest(VendingProtos.GetAssetRequestProto paramGetAssetRequestProto)
      {
        if (paramGetAssetRequestProto == null)
          throw new NullPointerException();
        this.hasGetAssetRequest = true;
        this.getAssetRequest_ = paramGetAssetRequestProto;
        return this;
      }

      public Request setGetCarrierInfoRequest(VendingProtos.GetCarrierInfoRequestProto paramGetCarrierInfoRequestProto)
      {
        if (paramGetCarrierInfoRequestProto == null)
          throw new NullPointerException();
        this.hasGetCarrierInfoRequest = true;
        this.getCarrierInfoRequest_ = paramGetCarrierInfoRequestProto;
        return this;
      }

      public Request setGetCategoriesRequest(VendingProtos.GetCategoriesRequestProto paramGetCategoriesRequestProto)
      {
        if (paramGetCategoriesRequestProto == null)
          throw new NullPointerException();
        this.hasGetCategoriesRequest = true;
        this.getCategoriesRequest_ = paramGetCategoriesRequestProto;
        return this;
      }

      public Request setGetImageRequest(VendingProtos.GetImageRequestProto paramGetImageRequestProto)
      {
        if (paramGetImageRequestProto == null)
          throw new NullPointerException();
        this.hasGetImageRequest = true;
        this.getImageRequest_ = paramGetImageRequestProto;
        return this;
      }

      public Request setGetMarketMetadataRequest(VendingProtos.GetMarketMetadataRequestProto paramGetMarketMetadataRequestProto)
      {
        if (paramGetMarketMetadataRequestProto == null)
          throw new NullPointerException();
        this.hasGetMarketMetadataRequest = true;
        this.getMarketMetadataRequest_ = paramGetMarketMetadataRequestProto;
        return this;
      }

      public Request setInAppPurchaseInformationRequest(VendingProtos.InAppPurchaseInformationRequestProto paramInAppPurchaseInformationRequestProto)
      {
        if (paramInAppPurchaseInformationRequestProto == null)
          throw new NullPointerException();
        this.hasInAppPurchaseInformationRequest = true;
        this.inAppPurchaseInformationRequest_ = paramInAppPurchaseInformationRequestProto;
        return this;
      }

      public Request setInAppRestoreTransactionsRequest(VendingProtos.InAppRestoreTransactionsRequestProto paramInAppRestoreTransactionsRequestProto)
      {
        if (paramInAppRestoreTransactionsRequestProto == null)
          throw new NullPointerException();
        this.hasInAppRestoreTransactionsRequest = true;
        this.inAppRestoreTransactionsRequest_ = paramInAppRestoreTransactionsRequestProto;
        return this;
      }

      public Request setModifyCommentRequest(VendingProtos.ModifyCommentRequestProto paramModifyCommentRequestProto)
      {
        if (paramModifyCommentRequestProto == null)
          throw new NullPointerException();
        this.hasModifyCommentRequest = true;
        this.modifyCommentRequest_ = paramModifyCommentRequestProto;
        return this;
      }

      public Request setPaypalCreateAccountRequest(VendingProtos.PaypalCreateAccountRequestProto paramPaypalCreateAccountRequestProto)
      {
        if (paramPaypalCreateAccountRequestProto == null)
          throw new NullPointerException();
        this.hasPaypalCreateAccountRequest = true;
        this.paypalCreateAccountRequest_ = paramPaypalCreateAccountRequestProto;
        return this;
      }

      public Request setPaypalMassageAddressRequest(VendingProtos.PaypalMassageAddressRequestProto paramPaypalMassageAddressRequestProto)
      {
        if (paramPaypalMassageAddressRequestProto == null)
          throw new NullPointerException();
        this.hasPaypalMassageAddressRequest = true;
        this.paypalMassageAddressRequest_ = paramPaypalMassageAddressRequestProto;
        return this;
      }

      public Request setPaypalPreapprovalCredentialsRequest(VendingProtos.PaypalPreapprovalCredentialsRequestProto paramPaypalPreapprovalCredentialsRequestProto)
      {
        if (paramPaypalPreapprovalCredentialsRequestProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalCredentialsRequest = true;
        this.paypalPreapprovalCredentialsRequest_ = paramPaypalPreapprovalCredentialsRequestProto;
        return this;
      }

      public Request setPaypalPreapprovalDetailsRequest(VendingProtos.PaypalPreapprovalDetailsRequestProto paramPaypalPreapprovalDetailsRequestProto)
      {
        if (paramPaypalPreapprovalDetailsRequestProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalDetailsRequest = true;
        this.paypalPreapprovalDetailsRequest_ = paramPaypalPreapprovalDetailsRequestProto;
        return this;
      }

      public Request setPaypalPreapprovalRequest(VendingProtos.PaypalPreapprovalRequestProto paramPaypalPreapprovalRequestProto)
      {
        if (paramPaypalPreapprovalRequestProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalRequest = true;
        this.paypalPreapprovalRequest_ = paramPaypalPreapprovalRequestProto;
        return this;
      }

      public Request setPurchaseMetadataRequest(VendingProtos.PurchaseMetadataRequestProto paramPurchaseMetadataRequestProto)
      {
        if (paramPurchaseMetadataRequestProto == null)
          throw new NullPointerException();
        this.hasPurchaseMetadataRequest = true;
        this.purchaseMetadataRequest_ = paramPurchaseMetadataRequestProto;
        return this;
      }

      public Request setPurchaseOrderRequest(VendingProtos.PurchaseOrderRequestProto paramPurchaseOrderRequestProto)
      {
        if (paramPurchaseOrderRequestProto == null)
          throw new NullPointerException();
        this.hasPurchaseOrderRequest = true;
        this.purchaseOrderRequest_ = paramPurchaseOrderRequestProto;
        return this;
      }

      public Request setPurchasePostRequest(VendingProtos.PurchasePostRequestProto paramPurchasePostRequestProto)
      {
        if (paramPurchasePostRequestProto == null)
          throw new NullPointerException();
        this.hasPurchasePostRequest = true;
        this.purchasePostRequest_ = paramPurchasePostRequestProto;
        return this;
      }

      public Request setPurchaseProductRequest(VendingProtos.PurchaseProductRequestProto paramPurchaseProductRequestProto)
      {
        if (paramPurchaseProductRequestProto == null)
          throw new NullPointerException();
        this.hasPurchaseProductRequest = true;
        this.purchaseProductRequest_ = paramPurchaseProductRequestProto;
        return this;
      }

      public Request setQuerySuggestionRequest(VendingProtos.QuerySuggestionRequestProto paramQuerySuggestionRequestProto)
      {
        if (paramQuerySuggestionRequestProto == null)
          throw new NullPointerException();
        this.hasQuerySuggestionRequest = true;
        this.querySuggestionRequest_ = paramQuerySuggestionRequestProto;
        return this;
      }

      public Request setRateCommentRequest(VendingProtos.RateCommentRequestProto paramRateCommentRequestProto)
      {
        if (paramRateCommentRequestProto == null)
          throw new NullPointerException();
        this.hasRateCommentRequest = true;
        this.rateCommentRequest_ = paramRateCommentRequestProto;
        return this;
      }

      public Request setReconstructDatabaseRequest(VendingProtos.ReconstructDatabaseRequestProto paramReconstructDatabaseRequestProto)
      {
        if (paramReconstructDatabaseRequestProto == null)
          throw new NullPointerException();
        this.hasReconstructDatabaseRequest = true;
        this.reconstructDatabaseRequest_ = paramReconstructDatabaseRequestProto;
        return this;
      }

      public Request setRefundRequest(VendingProtos.RefundRequestProto paramRefundRequestProto)
      {
        if (paramRefundRequestProto == null)
          throw new NullPointerException();
        this.hasRefundRequest = true;
        this.refundRequest_ = paramRefundRequestProto;
        return this;
      }

      public Request setRemoveAssetRequest(VendingProtos.RemoveAssetRequestProto paramRemoveAssetRequestProto)
      {
        if (paramRemoveAssetRequestProto == null)
          throw new NullPointerException();
        this.hasRemoveAssetRequest = true;
        this.removeAssetRequest_ = paramRemoveAssetRequestProto;
        return this;
      }

      public Request setRequestSpecificProperties(VendingProtos.RequestSpecificPropertiesProto paramRequestSpecificPropertiesProto)
      {
        if (paramRequestSpecificPropertiesProto == null)
          throw new NullPointerException();
        this.hasRequestSpecificProperties = true;
        this.requestSpecificProperties_ = paramRequestSpecificPropertiesProto;
        return this;
      }

      public Request setRestoreApplicationsRequest(VendingProtos.RestoreApplicationsRequestProto paramRestoreApplicationsRequestProto)
      {
        if (paramRestoreApplicationsRequestProto == null)
          throw new NullPointerException();
        this.hasRestoreApplicationsRequest = true;
        this.restoreApplicationsRequest_ = paramRestoreApplicationsRequestProto;
        return this;
      }

      public Request setSubCategoriesRequest(VendingProtos.GetSubCategoriesRequestProto paramGetSubCategoriesRequestProto)
      {
        if (paramGetSubCategoriesRequestProto == null)
          throw new NullPointerException();
        this.hasSubCategoriesRequest = true;
        this.subCategoriesRequest_ = paramGetSubCategoriesRequestProto;
        return this;
      }

      public Request setUninstallReasonRequest(VendingProtos.UninstallReasonRequestProto paramUninstallReasonRequestProto)
      {
        if (paramUninstallReasonRequestProto == null)
          throw new NullPointerException();
        this.hasUninstallReasonRequest = true;
        this.uninstallReasonRequest_ = paramUninstallReasonRequestProto;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasRequestSpecificProperties())
          paramCodedOutputStreamMicro.writeMessage(3, getRequestSpecificProperties());
        if (hasAssetRequest())
          paramCodedOutputStreamMicro.writeMessage(4, getAssetRequest());
        if (hasCommentsRequest())
          paramCodedOutputStreamMicro.writeMessage(5, getCommentsRequest());
        if (hasModifyCommentRequest())
          paramCodedOutputStreamMicro.writeMessage(6, getModifyCommentRequest());
        if (hasPurchasePostRequest())
          paramCodedOutputStreamMicro.writeMessage(7, getPurchasePostRequest());
        if (hasPurchaseOrderRequest())
          paramCodedOutputStreamMicro.writeMessage(8, getPurchaseOrderRequest());
        if (hasContentSyncRequest())
          paramCodedOutputStreamMicro.writeMessage(9, getContentSyncRequest());
        if (hasGetAssetRequest())
          paramCodedOutputStreamMicro.writeMessage(10, getGetAssetRequest());
        if (hasGetImageRequest())
          paramCodedOutputStreamMicro.writeMessage(11, getGetImageRequest());
        if (hasRefundRequest())
          paramCodedOutputStreamMicro.writeMessage(12, getRefundRequest());
        if (hasPurchaseMetadataRequest())
          paramCodedOutputStreamMicro.writeMessage(13, getPurchaseMetadataRequest());
        if (hasSubCategoriesRequest())
          paramCodedOutputStreamMicro.writeMessage(14, getSubCategoriesRequest());
        if (hasUninstallReasonRequest())
          paramCodedOutputStreamMicro.writeMessage(16, getUninstallReasonRequest());
        if (hasRateCommentRequest())
          paramCodedOutputStreamMicro.writeMessage(17, getRateCommentRequest());
        if (hasCheckLicenseRequest())
          paramCodedOutputStreamMicro.writeMessage(18, getCheckLicenseRequest());
        if (hasGetMarketMetadataRequest())
          paramCodedOutputStreamMicro.writeMessage(19, getGetMarketMetadataRequest());
        if (hasGetCategoriesRequest())
          paramCodedOutputStreamMicro.writeMessage(21, getGetCategoriesRequest());
        if (hasGetCarrierInfoRequest())
          paramCodedOutputStreamMicro.writeMessage(22, getGetCarrierInfoRequest());
        if (hasRemoveAssetRequest())
          paramCodedOutputStreamMicro.writeMessage(23, getRemoveAssetRequest());
        if (hasRestoreApplicationsRequest())
          paramCodedOutputStreamMicro.writeMessage(24, getRestoreApplicationsRequest());
        if (hasQuerySuggestionRequest())
          paramCodedOutputStreamMicro.writeMessage(25, getQuerySuggestionRequest());
        if (hasBillingEventRequest())
          paramCodedOutputStreamMicro.writeMessage(26, getBillingEventRequest());
        if (hasPaypalPreapprovalRequest())
          paramCodedOutputStreamMicro.writeMessage(27, getPaypalPreapprovalRequest());
        if (hasPaypalPreapprovalDetailsRequest())
          paramCodedOutputStreamMicro.writeMessage(28, getPaypalPreapprovalDetailsRequest());
        if (hasPaypalCreateAccountRequest())
          paramCodedOutputStreamMicro.writeMessage(29, getPaypalCreateAccountRequest());
        if (hasPaypalPreapprovalCredentialsRequest())
          paramCodedOutputStreamMicro.writeMessage(30, getPaypalPreapprovalCredentialsRequest());
        if (hasInAppRestoreTransactionsRequest())
          paramCodedOutputStreamMicro.writeMessage(31, getInAppRestoreTransactionsRequest());
        if (hasInAppPurchaseInformationRequest())
          paramCodedOutputStreamMicro.writeMessage(32, getInAppPurchaseInformationRequest());
        if (hasCheckForNotificationsRequest())
          paramCodedOutputStreamMicro.writeMessage(33, getCheckForNotificationsRequest());
        if (hasAckNotificationsRequest())
          paramCodedOutputStreamMicro.writeMessage(34, getAckNotificationsRequest());
        if (hasPurchaseProductRequest())
          paramCodedOutputStreamMicro.writeMessage(35, getPurchaseProductRequest());
        if (hasReconstructDatabaseRequest())
          paramCodedOutputStreamMicro.writeMessage(36, getReconstructDatabaseRequest());
        if (hasPaypalMassageAddressRequest())
          paramCodedOutputStreamMicro.writeMessage(37, getPaypalMassageAddressRequest());
        if (hasGetAddressSnippetRequest())
          paramCodedOutputStreamMicro.writeMessage(38, getGetAddressSnippetRequest());
      }
    }
  }

  public static final class RequestSpecificPropertiesProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasIfNoneMatch;
    private String ifNoneMatch_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getIfNoneMatch()
    {
      return this.ifNoneMatch_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasIfNoneMatch())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getIfNoneMatch());
      this.cachedSize = i;
      return i;
    }

    public boolean hasIfNoneMatch()
    {
      return this.hasIfNoneMatch;
    }

    public RequestSpecificPropertiesProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setIfNoneMatch(paramCodedInputStreamMicro.readString());
      }
    }

    public RequestSpecificPropertiesProto setIfNoneMatch(String paramString)
    {
      this.hasIfNoneMatch = true;
      this.ifNoneMatch_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasIfNoneMatch())
        paramCodedOutputStreamMicro.writeString(1, getIfNoneMatch());
    }
  }

  public static final class ResponsePropertiesProto extends MessageMicro
  {
    private int cachedSize = -1;
    private List<VendingProtos.InputValidationError> errorInputField_ = Collections.emptyList();
    private String errorMessage_ = "";
    private String etag_ = "";
    private boolean hasErrorMessage;
    private boolean hasEtag;
    private boolean hasMaxAge;
    private boolean hasMaxAgeConsumable;
    private boolean hasResult;
    private boolean hasServerVersion;
    private int maxAgeConsumable_ = 0;
    private int maxAge_ = 0;
    private int result_ = 0;
    private int serverVersion_ = 0;

    public ResponsePropertiesProto addErrorInputField(VendingProtos.InputValidationError paramInputValidationError)
    {
      if (paramInputValidationError == null)
        throw new NullPointerException();
      if (this.errorInputField_.isEmpty())
        this.errorInputField_ = new ArrayList();
      this.errorInputField_.add(paramInputValidationError);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<VendingProtos.InputValidationError> getErrorInputFieldList()
    {
      return this.errorInputField_;
    }

    public String getErrorMessage()
    {
      return this.errorMessage_;
    }

    public String getEtag()
    {
      return this.etag_;
    }

    public int getMaxAge()
    {
      return this.maxAge_;
    }

    public int getMaxAgeConsumable()
    {
      return this.maxAgeConsumable_;
    }

    public int getResult()
    {
      return this.result_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResult())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getResult());
      if (hasMaxAge())
        i += CodedOutputStreamMicro.computeInt32Size(2, getMaxAge());
      if (hasEtag())
        i += CodedOutputStreamMicro.computeStringSize(3, getEtag());
      if (hasServerVersion())
        i += CodedOutputStreamMicro.computeInt32Size(4, getServerVersion());
      if (hasMaxAgeConsumable())
        i += CodedOutputStreamMicro.computeInt32Size(6, getMaxAgeConsumable());
      if (hasErrorMessage())
        i += CodedOutputStreamMicro.computeStringSize(7, getErrorMessage());
      Iterator localIterator = getErrorInputFieldList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(8, (VendingProtos.InputValidationError)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public int getServerVersion()
    {
      return this.serverVersion_;
    }

    public boolean hasErrorMessage()
    {
      return this.hasErrorMessage;
    }

    public boolean hasEtag()
    {
      return this.hasEtag;
    }

    public boolean hasMaxAge()
    {
      return this.hasMaxAge;
    }

    public boolean hasMaxAgeConsumable()
    {
      return this.hasMaxAgeConsumable;
    }

    public boolean hasResult()
    {
      return this.hasResult;
    }

    public boolean hasServerVersion()
    {
      return this.hasServerVersion;
    }

    public ResponsePropertiesProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setResult(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setMaxAge(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setEtag(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setServerVersion(paramCodedInputStreamMicro.readInt32());
          break;
        case 48:
          setMaxAgeConsumable(paramCodedInputStreamMicro.readInt32());
          break;
        case 58:
          setErrorMessage(paramCodedInputStreamMicro.readString());
          break;
        case 66:
        }
        VendingProtos.InputValidationError localInputValidationError = new VendingProtos.InputValidationError();
        paramCodedInputStreamMicro.readMessage(localInputValidationError);
        addErrorInputField(localInputValidationError);
      }
    }

    public ResponsePropertiesProto setErrorMessage(String paramString)
    {
      this.hasErrorMessage = true;
      this.errorMessage_ = paramString;
      return this;
    }

    public ResponsePropertiesProto setEtag(String paramString)
    {
      this.hasEtag = true;
      this.etag_ = paramString;
      return this;
    }

    public ResponsePropertiesProto setMaxAge(int paramInt)
    {
      this.hasMaxAge = true;
      this.maxAge_ = paramInt;
      return this;
    }

    public ResponsePropertiesProto setMaxAgeConsumable(int paramInt)
    {
      this.hasMaxAgeConsumable = true;
      this.maxAgeConsumable_ = paramInt;
      return this;
    }

    public ResponsePropertiesProto setResult(int paramInt)
    {
      this.hasResult = true;
      this.result_ = paramInt;
      return this;
    }

    public ResponsePropertiesProto setServerVersion(int paramInt)
    {
      this.hasServerVersion = true;
      this.serverVersion_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResult())
        paramCodedOutputStreamMicro.writeInt32(1, getResult());
      if (hasMaxAge())
        paramCodedOutputStreamMicro.writeInt32(2, getMaxAge());
      if (hasEtag())
        paramCodedOutputStreamMicro.writeString(3, getEtag());
      if (hasServerVersion())
        paramCodedOutputStreamMicro.writeInt32(4, getServerVersion());
      if (hasMaxAgeConsumable())
        paramCodedOutputStreamMicro.writeInt32(6, getMaxAgeConsumable());
      if (hasErrorMessage())
        paramCodedOutputStreamMicro.writeString(7, getErrorMessage());
      Iterator localIterator = getErrorInputFieldList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (VendingProtos.InputValidationError)localIterator.next());
    }
  }

  public static final class ResponseProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasPendingNotifications;
    private VendingProtos.PendingNotificationsProto pendingNotifications_ = null;
    private List<Response> response_ = Collections.emptyList();

    public static ResponseProto parseFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      return new ResponseProto().mergeFrom(paramCodedInputStreamMicro);
    }

    public ResponseProto addResponse(Response paramResponse)
    {
      if (paramResponse == null)
        throw new NullPointerException();
      if (this.response_.isEmpty())
        this.response_ = new ArrayList();
      this.response_.add(paramResponse);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.PendingNotificationsProto getPendingNotifications()
    {
      return this.pendingNotifications_;
    }

    public Response getResponse(int paramInt)
    {
      return (Response)this.response_.get(paramInt);
    }

    public int getResponseCount()
    {
      return this.response_.size();
    }

    public List<Response> getResponseList()
    {
      return this.response_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getResponseList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(1, (Response)localIterator.next());
      if (hasPendingNotifications())
        i += CodedOutputStreamMicro.computeMessageSize(38, getPendingNotifications());
      this.cachedSize = i;
      return i;
    }

    public boolean hasPendingNotifications()
    {
      return this.hasPendingNotifications;
    }

    public ResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 11:
          Response localResponse = new Response();
          paramCodedInputStreamMicro.readGroup(localResponse, 1);
          addResponse(localResponse);
          break;
        case 306:
        }
        VendingProtos.PendingNotificationsProto localPendingNotificationsProto = new VendingProtos.PendingNotificationsProto();
        paramCodedInputStreamMicro.readMessage(localPendingNotificationsProto);
        setPendingNotifications(localPendingNotificationsProto);
      }
    }

    public ResponseProto setPendingNotifications(VendingProtos.PendingNotificationsProto paramPendingNotificationsProto)
    {
      if (paramPendingNotificationsProto == null)
        throw new NullPointerException();
      this.hasPendingNotifications = true;
      this.pendingNotifications_ = paramPendingNotificationsProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getResponseList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeGroup(1, (Response)localIterator.next());
      if (hasPendingNotifications())
        paramCodedOutputStreamMicro.writeMessage(38, getPendingNotifications());
    }

    public static final class Response extends MessageMicro
    {
      private VendingProtos.AckNotificationsResponseProto ackNotificationsResponse_ = null;
      private VendingProtos.AssetsResponseProto assetsResponse_ = null;
      private VendingProtos.BillingEventResponseProto billingEventResponse_ = null;
      private int cachedSize = -1;
      private VendingProtos.CheckForNotificationsResponseProto checkForNotificationsResponse_ = null;
      private VendingProtos.CheckLicenseResponseProto checkLicenseResponse_ = null;
      private VendingProtos.CommentsResponseProto commentsResponse_ = null;
      private VendingProtos.ContentSyncResponseProto contentSyncResponse_ = null;
      private VendingProtos.GetAddressSnippetResponseProto getAddressSnippetResponse_ = null;
      private VendingProtos.GetAssetResponseProto getAssetResponse_ = null;
      private VendingProtos.GetCarrierInfoResponseProto getCarrierInfoResponse_ = null;
      private VendingProtos.GetCategoriesResponseProto getCategoriesResponse_ = null;
      private VendingProtos.GetImageResponseProto getImageResponse_ = null;
      private VendingProtos.GetMarketMetadataResponseProto getMarketMetadataResponse_ = null;
      private boolean hasAckNotificationsResponse;
      private boolean hasAssetsResponse;
      private boolean hasBillingEventResponse;
      private boolean hasCheckForNotificationsResponse;
      private boolean hasCheckLicenseResponse;
      private boolean hasCommentsResponse;
      private boolean hasContentSyncResponse;
      private boolean hasGetAddressSnippetResponse;
      private boolean hasGetAssetResponse;
      private boolean hasGetCarrierInfoResponse;
      private boolean hasGetCategoriesResponse;
      private boolean hasGetImageResponse;
      private boolean hasGetMarketMetadataResponse;
      private boolean hasInAppPurchaseInformationResponse;
      private boolean hasInAppRestoreTransactionsResponse;
      private boolean hasModifyCommentResponse;
      private boolean hasPaypalCreateAccountResponse;
      private boolean hasPaypalMassageAddressResponse;
      private boolean hasPaypalPreapprovalCredentialsResponse;
      private boolean hasPaypalPreapprovalDetailsResponse;
      private boolean hasPaypalPreapprovalResponse;
      private boolean hasPurchaseMetadataResponse;
      private boolean hasPurchaseOrderResponse;
      private boolean hasPurchasePostResponse;
      private boolean hasPurchaseProductResponse;
      private boolean hasQuerySuggestionResponse;
      private boolean hasRateCommentResponse;
      private boolean hasReconstructDatabaseResponse;
      private boolean hasRefundResponse;
      private boolean hasResponseProperties;
      private boolean hasRestoreApplicationResponse;
      private boolean hasSubCategoriesResponse;
      private boolean hasUninstallReasonResponse;
      private VendingProtos.InAppPurchaseInformationResponseProto inAppPurchaseInformationResponse_ = null;
      private VendingProtos.InAppRestoreTransactionsResponseProto inAppRestoreTransactionsResponse_ = null;
      private VendingProtos.ModifyCommentResponseProto modifyCommentResponse_ = null;
      private VendingProtos.PaypalCreateAccountResponseProto paypalCreateAccountResponse_ = null;
      private VendingProtos.PaypalMassageAddressResponseProto paypalMassageAddressResponse_ = null;
      private VendingProtos.PaypalPreapprovalCredentialsResponseProto paypalPreapprovalCredentialsResponse_ = null;
      private VendingProtos.PaypalPreapprovalDetailsResponseProto paypalPreapprovalDetailsResponse_ = null;
      private VendingProtos.PaypalPreapprovalResponseProto paypalPreapprovalResponse_ = null;
      private List<VendingProtos.PrefetchedBundleProto> prefetchedBundle_ = Collections.emptyList();
      private VendingProtos.PurchaseMetadataResponseProto purchaseMetadataResponse_ = null;
      private VendingProtos.PurchaseOrderResponseProto purchaseOrderResponse_ = null;
      private VendingProtos.PurchasePostResponseProto purchasePostResponse_ = null;
      private VendingProtos.PurchaseProductResponseProto purchaseProductResponse_ = null;
      private VendingProtos.QuerySuggestionResponseProto querySuggestionResponse_ = null;
      private VendingProtos.RateCommentResponseProto rateCommentResponse_ = null;
      private VendingProtos.ReconstructDatabaseResponseProto reconstructDatabaseResponse_ = null;
      private VendingProtos.RefundResponseProto refundResponse_ = null;
      private VendingProtos.ResponsePropertiesProto responseProperties_ = null;
      private VendingProtos.RestoreApplicationsResponseProto restoreApplicationResponse_ = null;
      private VendingProtos.GetSubCategoriesResponseProto subCategoriesResponse_ = null;
      private VendingProtos.UninstallReasonResponseProto uninstallReasonResponse_ = null;

      public Response addPrefetchedBundle(VendingProtos.PrefetchedBundleProto paramPrefetchedBundleProto)
      {
        if (paramPrefetchedBundleProto == null)
          throw new NullPointerException();
        if (this.prefetchedBundle_.isEmpty())
          this.prefetchedBundle_ = new ArrayList();
        this.prefetchedBundle_.add(paramPrefetchedBundleProto);
        return this;
      }

      public VendingProtos.AckNotificationsResponseProto getAckNotificationsResponse()
      {
        return this.ackNotificationsResponse_;
      }

      public VendingProtos.AssetsResponseProto getAssetsResponse()
      {
        return this.assetsResponse_;
      }

      public VendingProtos.BillingEventResponseProto getBillingEventResponse()
      {
        return this.billingEventResponse_;
      }

      public int getCachedSize()
      {
        if (this.cachedSize < 0)
          getSerializedSize();
        return this.cachedSize;
      }

      public VendingProtos.CheckForNotificationsResponseProto getCheckForNotificationsResponse()
      {
        return this.checkForNotificationsResponse_;
      }

      public VendingProtos.CheckLicenseResponseProto getCheckLicenseResponse()
      {
        return this.checkLicenseResponse_;
      }

      public VendingProtos.CommentsResponseProto getCommentsResponse()
      {
        return this.commentsResponse_;
      }

      public VendingProtos.ContentSyncResponseProto getContentSyncResponse()
      {
        return this.contentSyncResponse_;
      }

      public VendingProtos.GetAddressSnippetResponseProto getGetAddressSnippetResponse()
      {
        return this.getAddressSnippetResponse_;
      }

      public VendingProtos.GetAssetResponseProto getGetAssetResponse()
      {
        return this.getAssetResponse_;
      }

      public VendingProtos.GetCarrierInfoResponseProto getGetCarrierInfoResponse()
      {
        return this.getCarrierInfoResponse_;
      }

      public VendingProtos.GetCategoriesResponseProto getGetCategoriesResponse()
      {
        return this.getCategoriesResponse_;
      }

      public VendingProtos.GetImageResponseProto getGetImageResponse()
      {
        return this.getImageResponse_;
      }

      public VendingProtos.GetMarketMetadataResponseProto getGetMarketMetadataResponse()
      {
        return this.getMarketMetadataResponse_;
      }

      public VendingProtos.InAppPurchaseInformationResponseProto getInAppPurchaseInformationResponse()
      {
        return this.inAppPurchaseInformationResponse_;
      }

      public VendingProtos.InAppRestoreTransactionsResponseProto getInAppRestoreTransactionsResponse()
      {
        return this.inAppRestoreTransactionsResponse_;
      }

      public VendingProtos.ModifyCommentResponseProto getModifyCommentResponse()
      {
        return this.modifyCommentResponse_;
      }

      public VendingProtos.PaypalCreateAccountResponseProto getPaypalCreateAccountResponse()
      {
        return this.paypalCreateAccountResponse_;
      }

      public VendingProtos.PaypalMassageAddressResponseProto getPaypalMassageAddressResponse()
      {
        return this.paypalMassageAddressResponse_;
      }

      public VendingProtos.PaypalPreapprovalCredentialsResponseProto getPaypalPreapprovalCredentialsResponse()
      {
        return this.paypalPreapprovalCredentialsResponse_;
      }

      public VendingProtos.PaypalPreapprovalDetailsResponseProto getPaypalPreapprovalDetailsResponse()
      {
        return this.paypalPreapprovalDetailsResponse_;
      }

      public VendingProtos.PaypalPreapprovalResponseProto getPaypalPreapprovalResponse()
      {
        return this.paypalPreapprovalResponse_;
      }

      public List<VendingProtos.PrefetchedBundleProto> getPrefetchedBundleList()
      {
        return this.prefetchedBundle_;
      }

      public VendingProtos.PurchaseMetadataResponseProto getPurchaseMetadataResponse()
      {
        return this.purchaseMetadataResponse_;
      }

      public VendingProtos.PurchaseOrderResponseProto getPurchaseOrderResponse()
      {
        return this.purchaseOrderResponse_;
      }

      public VendingProtos.PurchasePostResponseProto getPurchasePostResponse()
      {
        return this.purchasePostResponse_;
      }

      public VendingProtos.PurchaseProductResponseProto getPurchaseProductResponse()
      {
        return this.purchaseProductResponse_;
      }

      public VendingProtos.QuerySuggestionResponseProto getQuerySuggestionResponse()
      {
        return this.querySuggestionResponse_;
      }

      public VendingProtos.RateCommentResponseProto getRateCommentResponse()
      {
        return this.rateCommentResponse_;
      }

      public VendingProtos.ReconstructDatabaseResponseProto getReconstructDatabaseResponse()
      {
        return this.reconstructDatabaseResponse_;
      }

      public VendingProtos.RefundResponseProto getRefundResponse()
      {
        return this.refundResponse_;
      }

      public VendingProtos.ResponsePropertiesProto getResponseProperties()
      {
        return this.responseProperties_;
      }

      public VendingProtos.RestoreApplicationsResponseProto getRestoreApplicationResponse()
      {
        return this.restoreApplicationResponse_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasResponseProperties())
          i = 0 + CodedOutputStreamMicro.computeMessageSize(2, getResponseProperties());
        if (hasAssetsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(3, getAssetsResponse());
        if (hasCommentsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(4, getCommentsResponse());
        if (hasModifyCommentResponse())
          i += CodedOutputStreamMicro.computeMessageSize(5, getModifyCommentResponse());
        if (hasPurchasePostResponse())
          i += CodedOutputStreamMicro.computeMessageSize(6, getPurchasePostResponse());
        if (hasPurchaseOrderResponse())
          i += CodedOutputStreamMicro.computeMessageSize(7, getPurchaseOrderResponse());
        if (hasContentSyncResponse())
          i += CodedOutputStreamMicro.computeMessageSize(8, getContentSyncResponse());
        if (hasGetAssetResponse())
          i += CodedOutputStreamMicro.computeMessageSize(9, getGetAssetResponse());
        if (hasGetImageResponse())
          i += CodedOutputStreamMicro.computeMessageSize(10, getGetImageResponse());
        if (hasRefundResponse())
          i += CodedOutputStreamMicro.computeMessageSize(11, getRefundResponse());
        if (hasPurchaseMetadataResponse())
          i += CodedOutputStreamMicro.computeMessageSize(12, getPurchaseMetadataResponse());
        if (hasSubCategoriesResponse())
          i += CodedOutputStreamMicro.computeMessageSize(13, getSubCategoriesResponse());
        if (hasUninstallReasonResponse())
          i += CodedOutputStreamMicro.computeMessageSize(15, getUninstallReasonResponse());
        if (hasRateCommentResponse())
          i += CodedOutputStreamMicro.computeMessageSize(16, getRateCommentResponse());
        if (hasCheckLicenseResponse())
          i += CodedOutputStreamMicro.computeMessageSize(17, getCheckLicenseResponse());
        if (hasGetMarketMetadataResponse())
          i += CodedOutputStreamMicro.computeMessageSize(18, getGetMarketMetadataResponse());
        Iterator localIterator = getPrefetchedBundleList().iterator();
        while (localIterator.hasNext())
          i += CodedOutputStreamMicro.computeMessageSize(19, (VendingProtos.PrefetchedBundleProto)localIterator.next());
        if (hasGetCategoriesResponse())
          i += CodedOutputStreamMicro.computeMessageSize(20, getGetCategoriesResponse());
        if (hasGetCarrierInfoResponse())
          i += CodedOutputStreamMicro.computeMessageSize(21, getGetCarrierInfoResponse());
        if (hasRestoreApplicationResponse())
          i += CodedOutputStreamMicro.computeMessageSize(23, getRestoreApplicationResponse());
        if (hasQuerySuggestionResponse())
          i += CodedOutputStreamMicro.computeMessageSize(24, getQuerySuggestionResponse());
        if (hasBillingEventResponse())
          i += CodedOutputStreamMicro.computeMessageSize(25, getBillingEventResponse());
        if (hasPaypalPreapprovalResponse())
          i += CodedOutputStreamMicro.computeMessageSize(26, getPaypalPreapprovalResponse());
        if (hasPaypalPreapprovalDetailsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(27, getPaypalPreapprovalDetailsResponse());
        if (hasPaypalCreateAccountResponse())
          i += CodedOutputStreamMicro.computeMessageSize(28, getPaypalCreateAccountResponse());
        if (hasPaypalPreapprovalCredentialsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(29, getPaypalPreapprovalCredentialsResponse());
        if (hasInAppRestoreTransactionsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(30, getInAppRestoreTransactionsResponse());
        if (hasInAppPurchaseInformationResponse())
          i += CodedOutputStreamMicro.computeMessageSize(31, getInAppPurchaseInformationResponse());
        if (hasCheckForNotificationsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(32, getCheckForNotificationsResponse());
        if (hasAckNotificationsResponse())
          i += CodedOutputStreamMicro.computeMessageSize(33, getAckNotificationsResponse());
        if (hasPurchaseProductResponse())
          i += CodedOutputStreamMicro.computeMessageSize(34, getPurchaseProductResponse());
        if (hasReconstructDatabaseResponse())
          i += CodedOutputStreamMicro.computeMessageSize(35, getReconstructDatabaseResponse());
        if (hasPaypalMassageAddressResponse())
          i += CodedOutputStreamMicro.computeMessageSize(36, getPaypalMassageAddressResponse());
        if (hasGetAddressSnippetResponse())
          i += CodedOutputStreamMicro.computeMessageSize(37, getGetAddressSnippetResponse());
        this.cachedSize = i;
        return i;
      }

      public VendingProtos.GetSubCategoriesResponseProto getSubCategoriesResponse()
      {
        return this.subCategoriesResponse_;
      }

      public VendingProtos.UninstallReasonResponseProto getUninstallReasonResponse()
      {
        return this.uninstallReasonResponse_;
      }

      public boolean hasAckNotificationsResponse()
      {
        return this.hasAckNotificationsResponse;
      }

      public boolean hasAssetsResponse()
      {
        return this.hasAssetsResponse;
      }

      public boolean hasBillingEventResponse()
      {
        return this.hasBillingEventResponse;
      }

      public boolean hasCheckForNotificationsResponse()
      {
        return this.hasCheckForNotificationsResponse;
      }

      public boolean hasCheckLicenseResponse()
      {
        return this.hasCheckLicenseResponse;
      }

      public boolean hasCommentsResponse()
      {
        return this.hasCommentsResponse;
      }

      public boolean hasContentSyncResponse()
      {
        return this.hasContentSyncResponse;
      }

      public boolean hasGetAddressSnippetResponse()
      {
        return this.hasGetAddressSnippetResponse;
      }

      public boolean hasGetAssetResponse()
      {
        return this.hasGetAssetResponse;
      }

      public boolean hasGetCarrierInfoResponse()
      {
        return this.hasGetCarrierInfoResponse;
      }

      public boolean hasGetCategoriesResponse()
      {
        return this.hasGetCategoriesResponse;
      }

      public boolean hasGetImageResponse()
      {
        return this.hasGetImageResponse;
      }

      public boolean hasGetMarketMetadataResponse()
      {
        return this.hasGetMarketMetadataResponse;
      }

      public boolean hasInAppPurchaseInformationResponse()
      {
        return this.hasInAppPurchaseInformationResponse;
      }

      public boolean hasInAppRestoreTransactionsResponse()
      {
        return this.hasInAppRestoreTransactionsResponse;
      }

      public boolean hasModifyCommentResponse()
      {
        return this.hasModifyCommentResponse;
      }

      public boolean hasPaypalCreateAccountResponse()
      {
        return this.hasPaypalCreateAccountResponse;
      }

      public boolean hasPaypalMassageAddressResponse()
      {
        return this.hasPaypalMassageAddressResponse;
      }

      public boolean hasPaypalPreapprovalCredentialsResponse()
      {
        return this.hasPaypalPreapprovalCredentialsResponse;
      }

      public boolean hasPaypalPreapprovalDetailsResponse()
      {
        return this.hasPaypalPreapprovalDetailsResponse;
      }

      public boolean hasPaypalPreapprovalResponse()
      {
        return this.hasPaypalPreapprovalResponse;
      }

      public boolean hasPurchaseMetadataResponse()
      {
        return this.hasPurchaseMetadataResponse;
      }

      public boolean hasPurchaseOrderResponse()
      {
        return this.hasPurchaseOrderResponse;
      }

      public boolean hasPurchasePostResponse()
      {
        return this.hasPurchasePostResponse;
      }

      public boolean hasPurchaseProductResponse()
      {
        return this.hasPurchaseProductResponse;
      }

      public boolean hasQuerySuggestionResponse()
      {
        return this.hasQuerySuggestionResponse;
      }

      public boolean hasRateCommentResponse()
      {
        return this.hasRateCommentResponse;
      }

      public boolean hasReconstructDatabaseResponse()
      {
        return this.hasReconstructDatabaseResponse;
      }

      public boolean hasRefundResponse()
      {
        return this.hasRefundResponse;
      }

      public boolean hasResponseProperties()
      {
        return this.hasResponseProperties;
      }

      public boolean hasRestoreApplicationResponse()
      {
        return this.hasRestoreApplicationResponse;
      }

      public boolean hasSubCategoriesResponse()
      {
        return this.hasSubCategoriesResponse;
      }

      public boolean hasUninstallReasonResponse()
      {
        return this.hasUninstallReasonResponse;
      }

      public Response mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          case 18:
            VendingProtos.ResponsePropertiesProto localResponsePropertiesProto = new VendingProtos.ResponsePropertiesProto();
            paramCodedInputStreamMicro.readMessage(localResponsePropertiesProto);
            setResponseProperties(localResponsePropertiesProto);
            break;
          case 26:
            VendingProtos.AssetsResponseProto localAssetsResponseProto = new VendingProtos.AssetsResponseProto();
            paramCodedInputStreamMicro.readMessage(localAssetsResponseProto);
            setAssetsResponse(localAssetsResponseProto);
            break;
          case 34:
            VendingProtos.CommentsResponseProto localCommentsResponseProto = new VendingProtos.CommentsResponseProto();
            paramCodedInputStreamMicro.readMessage(localCommentsResponseProto);
            setCommentsResponse(localCommentsResponseProto);
            break;
          case 42:
            VendingProtos.ModifyCommentResponseProto localModifyCommentResponseProto = new VendingProtos.ModifyCommentResponseProto();
            paramCodedInputStreamMicro.readMessage(localModifyCommentResponseProto);
            setModifyCommentResponse(localModifyCommentResponseProto);
            break;
          case 50:
            VendingProtos.PurchasePostResponseProto localPurchasePostResponseProto = new VendingProtos.PurchasePostResponseProto();
            paramCodedInputStreamMicro.readMessage(localPurchasePostResponseProto);
            setPurchasePostResponse(localPurchasePostResponseProto);
            break;
          case 58:
            VendingProtos.PurchaseOrderResponseProto localPurchaseOrderResponseProto = new VendingProtos.PurchaseOrderResponseProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseOrderResponseProto);
            setPurchaseOrderResponse(localPurchaseOrderResponseProto);
            break;
          case 66:
            VendingProtos.ContentSyncResponseProto localContentSyncResponseProto = new VendingProtos.ContentSyncResponseProto();
            paramCodedInputStreamMicro.readMessage(localContentSyncResponseProto);
            setContentSyncResponse(localContentSyncResponseProto);
            break;
          case 74:
            VendingProtos.GetAssetResponseProto localGetAssetResponseProto = new VendingProtos.GetAssetResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetAssetResponseProto);
            setGetAssetResponse(localGetAssetResponseProto);
            break;
          case 82:
            VendingProtos.GetImageResponseProto localGetImageResponseProto = new VendingProtos.GetImageResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetImageResponseProto);
            setGetImageResponse(localGetImageResponseProto);
            break;
          case 90:
            VendingProtos.RefundResponseProto localRefundResponseProto = new VendingProtos.RefundResponseProto();
            paramCodedInputStreamMicro.readMessage(localRefundResponseProto);
            setRefundResponse(localRefundResponseProto);
            break;
          case 98:
            VendingProtos.PurchaseMetadataResponseProto localPurchaseMetadataResponseProto = new VendingProtos.PurchaseMetadataResponseProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseMetadataResponseProto);
            setPurchaseMetadataResponse(localPurchaseMetadataResponseProto);
            break;
          case 106:
            VendingProtos.GetSubCategoriesResponseProto localGetSubCategoriesResponseProto = new VendingProtos.GetSubCategoriesResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetSubCategoriesResponseProto);
            setSubCategoriesResponse(localGetSubCategoriesResponseProto);
            break;
          case 122:
            VendingProtos.UninstallReasonResponseProto localUninstallReasonResponseProto = new VendingProtos.UninstallReasonResponseProto();
            paramCodedInputStreamMicro.readMessage(localUninstallReasonResponseProto);
            setUninstallReasonResponse(localUninstallReasonResponseProto);
            break;
          case 130:
            VendingProtos.RateCommentResponseProto localRateCommentResponseProto = new VendingProtos.RateCommentResponseProto();
            paramCodedInputStreamMicro.readMessage(localRateCommentResponseProto);
            setRateCommentResponse(localRateCommentResponseProto);
            break;
          case 138:
            VendingProtos.CheckLicenseResponseProto localCheckLicenseResponseProto = new VendingProtos.CheckLicenseResponseProto();
            paramCodedInputStreamMicro.readMessage(localCheckLicenseResponseProto);
            setCheckLicenseResponse(localCheckLicenseResponseProto);
            break;
          case 146:
            VendingProtos.GetMarketMetadataResponseProto localGetMarketMetadataResponseProto = new VendingProtos.GetMarketMetadataResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetMarketMetadataResponseProto);
            setGetMarketMetadataResponse(localGetMarketMetadataResponseProto);
            break;
          case 154:
            VendingProtos.PrefetchedBundleProto localPrefetchedBundleProto = new VendingProtos.PrefetchedBundleProto();
            paramCodedInputStreamMicro.readMessage(localPrefetchedBundleProto);
            addPrefetchedBundle(localPrefetchedBundleProto);
            break;
          case 162:
            VendingProtos.GetCategoriesResponseProto localGetCategoriesResponseProto = new VendingProtos.GetCategoriesResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetCategoriesResponseProto);
            setGetCategoriesResponse(localGetCategoriesResponseProto);
            break;
          case 170:
            VendingProtos.GetCarrierInfoResponseProto localGetCarrierInfoResponseProto = new VendingProtos.GetCarrierInfoResponseProto();
            paramCodedInputStreamMicro.readMessage(localGetCarrierInfoResponseProto);
            setGetCarrierInfoResponse(localGetCarrierInfoResponseProto);
            break;
          case 186:
            VendingProtos.RestoreApplicationsResponseProto localRestoreApplicationsResponseProto = new VendingProtos.RestoreApplicationsResponseProto();
            paramCodedInputStreamMicro.readMessage(localRestoreApplicationsResponseProto);
            setRestoreApplicationResponse(localRestoreApplicationsResponseProto);
            break;
          case 194:
            VendingProtos.QuerySuggestionResponseProto localQuerySuggestionResponseProto = new VendingProtos.QuerySuggestionResponseProto();
            paramCodedInputStreamMicro.readMessage(localQuerySuggestionResponseProto);
            setQuerySuggestionResponse(localQuerySuggestionResponseProto);
            break;
          case 202:
            VendingProtos.BillingEventResponseProto localBillingEventResponseProto = new VendingProtos.BillingEventResponseProto();
            paramCodedInputStreamMicro.readMessage(localBillingEventResponseProto);
            setBillingEventResponse(localBillingEventResponseProto);
            break;
          case 210:
            VendingProtos.PaypalPreapprovalResponseProto localPaypalPreapprovalResponseProto = new VendingProtos.PaypalPreapprovalResponseProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalResponseProto);
            setPaypalPreapprovalResponse(localPaypalPreapprovalResponseProto);
            break;
          case 218:
            VendingProtos.PaypalPreapprovalDetailsResponseProto localPaypalPreapprovalDetailsResponseProto = new VendingProtos.PaypalPreapprovalDetailsResponseProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalDetailsResponseProto);
            setPaypalPreapprovalDetailsResponse(localPaypalPreapprovalDetailsResponseProto);
            break;
          case 226:
            VendingProtos.PaypalCreateAccountResponseProto localPaypalCreateAccountResponseProto = new VendingProtos.PaypalCreateAccountResponseProto();
            paramCodedInputStreamMicro.readMessage(localPaypalCreateAccountResponseProto);
            setPaypalCreateAccountResponse(localPaypalCreateAccountResponseProto);
            break;
          case 234:
            VendingProtos.PaypalPreapprovalCredentialsResponseProto localPaypalPreapprovalCredentialsResponseProto = new VendingProtos.PaypalPreapprovalCredentialsResponseProto();
            paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalCredentialsResponseProto);
            setPaypalPreapprovalCredentialsResponse(localPaypalPreapprovalCredentialsResponseProto);
            break;
          case 242:
            VendingProtos.InAppRestoreTransactionsResponseProto localInAppRestoreTransactionsResponseProto = new VendingProtos.InAppRestoreTransactionsResponseProto();
            paramCodedInputStreamMicro.readMessage(localInAppRestoreTransactionsResponseProto);
            setInAppRestoreTransactionsResponse(localInAppRestoreTransactionsResponseProto);
            break;
          case 250:
            VendingProtos.InAppPurchaseInformationResponseProto localInAppPurchaseInformationResponseProto = new VendingProtos.InAppPurchaseInformationResponseProto();
            paramCodedInputStreamMicro.readMessage(localInAppPurchaseInformationResponseProto);
            setInAppPurchaseInformationResponse(localInAppPurchaseInformationResponseProto);
            break;
          case 258:
            VendingProtos.CheckForNotificationsResponseProto localCheckForNotificationsResponseProto = new VendingProtos.CheckForNotificationsResponseProto();
            paramCodedInputStreamMicro.readMessage(localCheckForNotificationsResponseProto);
            setCheckForNotificationsResponse(localCheckForNotificationsResponseProto);
            break;
          case 266:
            VendingProtos.AckNotificationsResponseProto localAckNotificationsResponseProto = new VendingProtos.AckNotificationsResponseProto();
            paramCodedInputStreamMicro.readMessage(localAckNotificationsResponseProto);
            setAckNotificationsResponse(localAckNotificationsResponseProto);
            break;
          case 274:
            VendingProtos.PurchaseProductResponseProto localPurchaseProductResponseProto = new VendingProtos.PurchaseProductResponseProto();
            paramCodedInputStreamMicro.readMessage(localPurchaseProductResponseProto);
            setPurchaseProductResponse(localPurchaseProductResponseProto);
            break;
          case 282:
            VendingProtos.ReconstructDatabaseResponseProto localReconstructDatabaseResponseProto = new VendingProtos.ReconstructDatabaseResponseProto();
            paramCodedInputStreamMicro.readMessage(localReconstructDatabaseResponseProto);
            setReconstructDatabaseResponse(localReconstructDatabaseResponseProto);
            break;
          case 290:
            VendingProtos.PaypalMassageAddressResponseProto localPaypalMassageAddressResponseProto = new VendingProtos.PaypalMassageAddressResponseProto();
            paramCodedInputStreamMicro.readMessage(localPaypalMassageAddressResponseProto);
            setPaypalMassageAddressResponse(localPaypalMassageAddressResponseProto);
            break;
          case 298:
          }
          VendingProtos.GetAddressSnippetResponseProto localGetAddressSnippetResponseProto = new VendingProtos.GetAddressSnippetResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetAddressSnippetResponseProto);
          setGetAddressSnippetResponse(localGetAddressSnippetResponseProto);
        }
      }

      public Response setAckNotificationsResponse(VendingProtos.AckNotificationsResponseProto paramAckNotificationsResponseProto)
      {
        if (paramAckNotificationsResponseProto == null)
          throw new NullPointerException();
        this.hasAckNotificationsResponse = true;
        this.ackNotificationsResponse_ = paramAckNotificationsResponseProto;
        return this;
      }

      public Response setAssetsResponse(VendingProtos.AssetsResponseProto paramAssetsResponseProto)
      {
        if (paramAssetsResponseProto == null)
          throw new NullPointerException();
        this.hasAssetsResponse = true;
        this.assetsResponse_ = paramAssetsResponseProto;
        return this;
      }

      public Response setBillingEventResponse(VendingProtos.BillingEventResponseProto paramBillingEventResponseProto)
      {
        if (paramBillingEventResponseProto == null)
          throw new NullPointerException();
        this.hasBillingEventResponse = true;
        this.billingEventResponse_ = paramBillingEventResponseProto;
        return this;
      }

      public Response setCheckForNotificationsResponse(VendingProtos.CheckForNotificationsResponseProto paramCheckForNotificationsResponseProto)
      {
        if (paramCheckForNotificationsResponseProto == null)
          throw new NullPointerException();
        this.hasCheckForNotificationsResponse = true;
        this.checkForNotificationsResponse_ = paramCheckForNotificationsResponseProto;
        return this;
      }

      public Response setCheckLicenseResponse(VendingProtos.CheckLicenseResponseProto paramCheckLicenseResponseProto)
      {
        if (paramCheckLicenseResponseProto == null)
          throw new NullPointerException();
        this.hasCheckLicenseResponse = true;
        this.checkLicenseResponse_ = paramCheckLicenseResponseProto;
        return this;
      }

      public Response setCommentsResponse(VendingProtos.CommentsResponseProto paramCommentsResponseProto)
      {
        if (paramCommentsResponseProto == null)
          throw new NullPointerException();
        this.hasCommentsResponse = true;
        this.commentsResponse_ = paramCommentsResponseProto;
        return this;
      }

      public Response setContentSyncResponse(VendingProtos.ContentSyncResponseProto paramContentSyncResponseProto)
      {
        if (paramContentSyncResponseProto == null)
          throw new NullPointerException();
        this.hasContentSyncResponse = true;
        this.contentSyncResponse_ = paramContentSyncResponseProto;
        return this;
      }

      public Response setGetAddressSnippetResponse(VendingProtos.GetAddressSnippetResponseProto paramGetAddressSnippetResponseProto)
      {
        if (paramGetAddressSnippetResponseProto == null)
          throw new NullPointerException();
        this.hasGetAddressSnippetResponse = true;
        this.getAddressSnippetResponse_ = paramGetAddressSnippetResponseProto;
        return this;
      }

      public Response setGetAssetResponse(VendingProtos.GetAssetResponseProto paramGetAssetResponseProto)
      {
        if (paramGetAssetResponseProto == null)
          throw new NullPointerException();
        this.hasGetAssetResponse = true;
        this.getAssetResponse_ = paramGetAssetResponseProto;
        return this;
      }

      public Response setGetCarrierInfoResponse(VendingProtos.GetCarrierInfoResponseProto paramGetCarrierInfoResponseProto)
      {
        if (paramGetCarrierInfoResponseProto == null)
          throw new NullPointerException();
        this.hasGetCarrierInfoResponse = true;
        this.getCarrierInfoResponse_ = paramGetCarrierInfoResponseProto;
        return this;
      }

      public Response setGetCategoriesResponse(VendingProtos.GetCategoriesResponseProto paramGetCategoriesResponseProto)
      {
        if (paramGetCategoriesResponseProto == null)
          throw new NullPointerException();
        this.hasGetCategoriesResponse = true;
        this.getCategoriesResponse_ = paramGetCategoriesResponseProto;
        return this;
      }

      public Response setGetImageResponse(VendingProtos.GetImageResponseProto paramGetImageResponseProto)
      {
        if (paramGetImageResponseProto == null)
          throw new NullPointerException();
        this.hasGetImageResponse = true;
        this.getImageResponse_ = paramGetImageResponseProto;
        return this;
      }

      public Response setGetMarketMetadataResponse(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
      {
        if (paramGetMarketMetadataResponseProto == null)
          throw new NullPointerException();
        this.hasGetMarketMetadataResponse = true;
        this.getMarketMetadataResponse_ = paramGetMarketMetadataResponseProto;
        return this;
      }

      public Response setInAppPurchaseInformationResponse(VendingProtos.InAppPurchaseInformationResponseProto paramInAppPurchaseInformationResponseProto)
      {
        if (paramInAppPurchaseInformationResponseProto == null)
          throw new NullPointerException();
        this.hasInAppPurchaseInformationResponse = true;
        this.inAppPurchaseInformationResponse_ = paramInAppPurchaseInformationResponseProto;
        return this;
      }

      public Response setInAppRestoreTransactionsResponse(VendingProtos.InAppRestoreTransactionsResponseProto paramInAppRestoreTransactionsResponseProto)
      {
        if (paramInAppRestoreTransactionsResponseProto == null)
          throw new NullPointerException();
        this.hasInAppRestoreTransactionsResponse = true;
        this.inAppRestoreTransactionsResponse_ = paramInAppRestoreTransactionsResponseProto;
        return this;
      }

      public Response setModifyCommentResponse(VendingProtos.ModifyCommentResponseProto paramModifyCommentResponseProto)
      {
        if (paramModifyCommentResponseProto == null)
          throw new NullPointerException();
        this.hasModifyCommentResponse = true;
        this.modifyCommentResponse_ = paramModifyCommentResponseProto;
        return this;
      }

      public Response setPaypalCreateAccountResponse(VendingProtos.PaypalCreateAccountResponseProto paramPaypalCreateAccountResponseProto)
      {
        if (paramPaypalCreateAccountResponseProto == null)
          throw new NullPointerException();
        this.hasPaypalCreateAccountResponse = true;
        this.paypalCreateAccountResponse_ = paramPaypalCreateAccountResponseProto;
        return this;
      }

      public Response setPaypalMassageAddressResponse(VendingProtos.PaypalMassageAddressResponseProto paramPaypalMassageAddressResponseProto)
      {
        if (paramPaypalMassageAddressResponseProto == null)
          throw new NullPointerException();
        this.hasPaypalMassageAddressResponse = true;
        this.paypalMassageAddressResponse_ = paramPaypalMassageAddressResponseProto;
        return this;
      }

      public Response setPaypalPreapprovalCredentialsResponse(VendingProtos.PaypalPreapprovalCredentialsResponseProto paramPaypalPreapprovalCredentialsResponseProto)
      {
        if (paramPaypalPreapprovalCredentialsResponseProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalCredentialsResponse = true;
        this.paypalPreapprovalCredentialsResponse_ = paramPaypalPreapprovalCredentialsResponseProto;
        return this;
      }

      public Response setPaypalPreapprovalDetailsResponse(VendingProtos.PaypalPreapprovalDetailsResponseProto paramPaypalPreapprovalDetailsResponseProto)
      {
        if (paramPaypalPreapprovalDetailsResponseProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalDetailsResponse = true;
        this.paypalPreapprovalDetailsResponse_ = paramPaypalPreapprovalDetailsResponseProto;
        return this;
      }

      public Response setPaypalPreapprovalResponse(VendingProtos.PaypalPreapprovalResponseProto paramPaypalPreapprovalResponseProto)
      {
        if (paramPaypalPreapprovalResponseProto == null)
          throw new NullPointerException();
        this.hasPaypalPreapprovalResponse = true;
        this.paypalPreapprovalResponse_ = paramPaypalPreapprovalResponseProto;
        return this;
      }

      public Response setPurchaseMetadataResponse(VendingProtos.PurchaseMetadataResponseProto paramPurchaseMetadataResponseProto)
      {
        if (paramPurchaseMetadataResponseProto == null)
          throw new NullPointerException();
        this.hasPurchaseMetadataResponse = true;
        this.purchaseMetadataResponse_ = paramPurchaseMetadataResponseProto;
        return this;
      }

      public Response setPurchaseOrderResponse(VendingProtos.PurchaseOrderResponseProto paramPurchaseOrderResponseProto)
      {
        if (paramPurchaseOrderResponseProto == null)
          throw new NullPointerException();
        this.hasPurchaseOrderResponse = true;
        this.purchaseOrderResponse_ = paramPurchaseOrderResponseProto;
        return this;
      }

      public Response setPurchasePostResponse(VendingProtos.PurchasePostResponseProto paramPurchasePostResponseProto)
      {
        if (paramPurchasePostResponseProto == null)
          throw new NullPointerException();
        this.hasPurchasePostResponse = true;
        this.purchasePostResponse_ = paramPurchasePostResponseProto;
        return this;
      }

      public Response setPurchaseProductResponse(VendingProtos.PurchaseProductResponseProto paramPurchaseProductResponseProto)
      {
        if (paramPurchaseProductResponseProto == null)
          throw new NullPointerException();
        this.hasPurchaseProductResponse = true;
        this.purchaseProductResponse_ = paramPurchaseProductResponseProto;
        return this;
      }

      public Response setQuerySuggestionResponse(VendingProtos.QuerySuggestionResponseProto paramQuerySuggestionResponseProto)
      {
        if (paramQuerySuggestionResponseProto == null)
          throw new NullPointerException();
        this.hasQuerySuggestionResponse = true;
        this.querySuggestionResponse_ = paramQuerySuggestionResponseProto;
        return this;
      }

      public Response setRateCommentResponse(VendingProtos.RateCommentResponseProto paramRateCommentResponseProto)
      {
        if (paramRateCommentResponseProto == null)
          throw new NullPointerException();
        this.hasRateCommentResponse = true;
        this.rateCommentResponse_ = paramRateCommentResponseProto;
        return this;
      }

      public Response setReconstructDatabaseResponse(VendingProtos.ReconstructDatabaseResponseProto paramReconstructDatabaseResponseProto)
      {
        if (paramReconstructDatabaseResponseProto == null)
          throw new NullPointerException();
        this.hasReconstructDatabaseResponse = true;
        this.reconstructDatabaseResponse_ = paramReconstructDatabaseResponseProto;
        return this;
      }

      public Response setRefundResponse(VendingProtos.RefundResponseProto paramRefundResponseProto)
      {
        if (paramRefundResponseProto == null)
          throw new NullPointerException();
        this.hasRefundResponse = true;
        this.refundResponse_ = paramRefundResponseProto;
        return this;
      }

      public Response setResponseProperties(VendingProtos.ResponsePropertiesProto paramResponsePropertiesProto)
      {
        if (paramResponsePropertiesProto == null)
          throw new NullPointerException();
        this.hasResponseProperties = true;
        this.responseProperties_ = paramResponsePropertiesProto;
        return this;
      }

      public Response setRestoreApplicationResponse(VendingProtos.RestoreApplicationsResponseProto paramRestoreApplicationsResponseProto)
      {
        if (paramRestoreApplicationsResponseProto == null)
          throw new NullPointerException();
        this.hasRestoreApplicationResponse = true;
        this.restoreApplicationResponse_ = paramRestoreApplicationsResponseProto;
        return this;
      }

      public Response setSubCategoriesResponse(VendingProtos.GetSubCategoriesResponseProto paramGetSubCategoriesResponseProto)
      {
        if (paramGetSubCategoriesResponseProto == null)
          throw new NullPointerException();
        this.hasSubCategoriesResponse = true;
        this.subCategoriesResponse_ = paramGetSubCategoriesResponseProto;
        return this;
      }

      public Response setUninstallReasonResponse(VendingProtos.UninstallReasonResponseProto paramUninstallReasonResponseProto)
      {
        if (paramUninstallReasonResponseProto == null)
          throw new NullPointerException();
        this.hasUninstallReasonResponse = true;
        this.uninstallReasonResponse_ = paramUninstallReasonResponseProto;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasResponseProperties())
          paramCodedOutputStreamMicro.writeMessage(2, getResponseProperties());
        if (hasAssetsResponse())
          paramCodedOutputStreamMicro.writeMessage(3, getAssetsResponse());
        if (hasCommentsResponse())
          paramCodedOutputStreamMicro.writeMessage(4, getCommentsResponse());
        if (hasModifyCommentResponse())
          paramCodedOutputStreamMicro.writeMessage(5, getModifyCommentResponse());
        if (hasPurchasePostResponse())
          paramCodedOutputStreamMicro.writeMessage(6, getPurchasePostResponse());
        if (hasPurchaseOrderResponse())
          paramCodedOutputStreamMicro.writeMessage(7, getPurchaseOrderResponse());
        if (hasContentSyncResponse())
          paramCodedOutputStreamMicro.writeMessage(8, getContentSyncResponse());
        if (hasGetAssetResponse())
          paramCodedOutputStreamMicro.writeMessage(9, getGetAssetResponse());
        if (hasGetImageResponse())
          paramCodedOutputStreamMicro.writeMessage(10, getGetImageResponse());
        if (hasRefundResponse())
          paramCodedOutputStreamMicro.writeMessage(11, getRefundResponse());
        if (hasPurchaseMetadataResponse())
          paramCodedOutputStreamMicro.writeMessage(12, getPurchaseMetadataResponse());
        if (hasSubCategoriesResponse())
          paramCodedOutputStreamMicro.writeMessage(13, getSubCategoriesResponse());
        if (hasUninstallReasonResponse())
          paramCodedOutputStreamMicro.writeMessage(15, getUninstallReasonResponse());
        if (hasRateCommentResponse())
          paramCodedOutputStreamMicro.writeMessage(16, getRateCommentResponse());
        if (hasCheckLicenseResponse())
          paramCodedOutputStreamMicro.writeMessage(17, getCheckLicenseResponse());
        if (hasGetMarketMetadataResponse())
          paramCodedOutputStreamMicro.writeMessage(18, getGetMarketMetadataResponse());
        Iterator localIterator = getPrefetchedBundleList().iterator();
        while (localIterator.hasNext())
          paramCodedOutputStreamMicro.writeMessage(19, (VendingProtos.PrefetchedBundleProto)localIterator.next());
        if (hasGetCategoriesResponse())
          paramCodedOutputStreamMicro.writeMessage(20, getGetCategoriesResponse());
        if (hasGetCarrierInfoResponse())
          paramCodedOutputStreamMicro.writeMessage(21, getGetCarrierInfoResponse());
        if (hasRestoreApplicationResponse())
          paramCodedOutputStreamMicro.writeMessage(23, getRestoreApplicationResponse());
        if (hasQuerySuggestionResponse())
          paramCodedOutputStreamMicro.writeMessage(24, getQuerySuggestionResponse());
        if (hasBillingEventResponse())
          paramCodedOutputStreamMicro.writeMessage(25, getBillingEventResponse());
        if (hasPaypalPreapprovalResponse())
          paramCodedOutputStreamMicro.writeMessage(26, getPaypalPreapprovalResponse());
        if (hasPaypalPreapprovalDetailsResponse())
          paramCodedOutputStreamMicro.writeMessage(27, getPaypalPreapprovalDetailsResponse());
        if (hasPaypalCreateAccountResponse())
          paramCodedOutputStreamMicro.writeMessage(28, getPaypalCreateAccountResponse());
        if (hasPaypalPreapprovalCredentialsResponse())
          paramCodedOutputStreamMicro.writeMessage(29, getPaypalPreapprovalCredentialsResponse());
        if (hasInAppRestoreTransactionsResponse())
          paramCodedOutputStreamMicro.writeMessage(30, getInAppRestoreTransactionsResponse());
        if (hasInAppPurchaseInformationResponse())
          paramCodedOutputStreamMicro.writeMessage(31, getInAppPurchaseInformationResponse());
        if (hasCheckForNotificationsResponse())
          paramCodedOutputStreamMicro.writeMessage(32, getCheckForNotificationsResponse());
        if (hasAckNotificationsResponse())
          paramCodedOutputStreamMicro.writeMessage(33, getAckNotificationsResponse());
        if (hasPurchaseProductResponse())
          paramCodedOutputStreamMicro.writeMessage(34, getPurchaseProductResponse());
        if (hasReconstructDatabaseResponse())
          paramCodedOutputStreamMicro.writeMessage(35, getReconstructDatabaseResponse());
        if (hasPaypalMassageAddressResponse())
          paramCodedOutputStreamMicro.writeMessage(36, getPaypalMassageAddressResponse());
        if (hasGetAddressSnippetResponse())
          paramCodedOutputStreamMicro.writeMessage(37, getGetAddressSnippetResponse());
      }
    }
  }

  public static final class RestoreApplicationsRequestProto extends MessageMicro
  {
    private String backupAndroidId_ = "";
    private int cachedSize = -1;
    private DeviceConfigurationProto deviceConfiguration_ = null;
    private boolean hasBackupAndroidId;
    private boolean hasDeviceConfiguration;
    private boolean hasTosVersion;
    private String tosVersion_ = "";

    public String getBackupAndroidId()
    {
      return this.backupAndroidId_;
    }

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

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBackupAndroidId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getBackupAndroidId());
      if (hasTosVersion())
        i += CodedOutputStreamMicro.computeStringSize(2, getTosVersion());
      if (hasDeviceConfiguration())
        i += CodedOutputStreamMicro.computeMessageSize(3, getDeviceConfiguration());
      this.cachedSize = i;
      return i;
    }

    public String getTosVersion()
    {
      return this.tosVersion_;
    }

    public boolean hasBackupAndroidId()
    {
      return this.hasBackupAndroidId;
    }

    public boolean hasDeviceConfiguration()
    {
      return this.hasDeviceConfiguration;
    }

    public boolean hasTosVersion()
    {
      return this.hasTosVersion;
    }

    public RestoreApplicationsRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBackupAndroidId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setTosVersion(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        DeviceConfigurationProto localDeviceConfigurationProto = new DeviceConfigurationProto();
        paramCodedInputStreamMicro.readMessage(localDeviceConfigurationProto);
        setDeviceConfiguration(localDeviceConfigurationProto);
      }
    }

    public RestoreApplicationsRequestProto setBackupAndroidId(String paramString)
    {
      this.hasBackupAndroidId = true;
      this.backupAndroidId_ = paramString;
      return this;
    }

    public RestoreApplicationsRequestProto setDeviceConfiguration(DeviceConfigurationProto paramDeviceConfigurationProto)
    {
      if (paramDeviceConfigurationProto == null)
        throw new NullPointerException();
      this.hasDeviceConfiguration = true;
      this.deviceConfiguration_ = paramDeviceConfigurationProto;
      return this;
    }

    public RestoreApplicationsRequestProto setTosVersion(String paramString)
    {
      this.hasTosVersion = true;
      this.tosVersion_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBackupAndroidId())
        paramCodedOutputStreamMicro.writeString(1, getBackupAndroidId());
      if (hasTosVersion())
        paramCodedOutputStreamMicro.writeString(2, getTosVersion());
      if (hasDeviceConfiguration())
        paramCodedOutputStreamMicro.writeMessage(3, getDeviceConfiguration());
    }
  }

  public static final class RestoreApplicationsResponseProto extends MessageMicro
  {
    private List<VendingProtos.GetAssetResponseProto> asset_ = Collections.emptyList();
    private int cachedSize = -1;

    public RestoreApplicationsResponseProto addAsset(VendingProtos.GetAssetResponseProto paramGetAssetResponseProto)
    {
      if (paramGetAssetResponseProto == null)
        throw new NullPointerException();
      if (this.asset_.isEmpty())
        this.asset_ = new ArrayList();
      this.asset_.add(paramGetAssetResponseProto);
      return this;
    }

    public List<VendingProtos.GetAssetResponseProto> getAssetList()
    {
      return this.asset_;
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
      Iterator localIterator = getAssetList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (VendingProtos.GetAssetResponseProto)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public RestoreApplicationsResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        VendingProtos.GetAssetResponseProto localGetAssetResponseProto = new VendingProtos.GetAssetResponseProto();
        paramCodedInputStreamMicro.readMessage(localGetAssetResponseProto);
        addAsset(localGetAssetResponseProto);
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getAssetList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (VendingProtos.GetAssetResponseProto)localIterator.next());
    }
  }

  public static final class RiskHeaderInfoProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasHashedDeviceInfo;
    private String hashedDeviceInfo_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getHashedDeviceInfo()
    {
      return this.hashedDeviceInfo_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasHashedDeviceInfo())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getHashedDeviceInfo());
      this.cachedSize = i;
      return i;
    }

    public boolean hasHashedDeviceInfo()
    {
      return this.hasHashedDeviceInfo;
    }

    public RiskHeaderInfoProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setHashedDeviceInfo(paramCodedInputStreamMicro.readString());
      }
    }

    public RiskHeaderInfoProto setHashedDeviceInfo(String paramString)
    {
      this.hasHashedDeviceInfo = true;
      this.hashedDeviceInfo_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasHashedDeviceInfo())
        paramCodedOutputStreamMicro.writeString(1, getHashedDeviceInfo());
    }
  }

  public static final class SignatureHashProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasHash;
    private boolean hasPackageName;
    private boolean hasVersionCode;
    private ByteStringMicro hash_ = ByteStringMicro.EMPTY;
    private String packageName_ = "";
    private int versionCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public ByteStringMicro getHash()
    {
      return this.hash_;
    }

    public String getPackageName()
    {
      return this.packageName_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasPackageName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getPackageName());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(2, getVersionCode());
      if (hasHash())
        i += CodedOutputStreamMicro.computeBytesSize(3, getHash());
      this.cachedSize = i;
      return i;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasHash()
    {
      return this.hasHash;
    }

    public boolean hasPackageName()
    {
      return this.hasPackageName;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public SignatureHashProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setPackageName(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
        }
        setHash(paramCodedInputStreamMicro.readBytes());
      }
    }

    public SignatureHashProto setHash(ByteStringMicro paramByteStringMicro)
    {
      this.hasHash = true;
      this.hash_ = paramByteStringMicro;
      return this;
    }

    public SignatureHashProto setPackageName(String paramString)
    {
      this.hasPackageName = true;
      this.packageName_ = paramString;
      return this;
    }

    public SignatureHashProto setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasPackageName())
        paramCodedOutputStreamMicro.writeString(1, getPackageName());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(2, getVersionCode());
      if (hasHash())
        paramCodedOutputStreamMicro.writeBytes(3, getHash());
    }
  }

  public static final class SignedDataProto extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSignature;
    private boolean hasSignedData;
    private String signature_ = "";
    private String signedData_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSignedData())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSignedData());
      if (hasSignature())
        i += CodedOutputStreamMicro.computeStringSize(2, getSignature());
      this.cachedSize = i;
      return i;
    }

    public String getSignature()
    {
      return this.signature_;
    }

    public String getSignedData()
    {
      return this.signedData_;
    }

    public boolean hasSignature()
    {
      return this.hasSignature;
    }

    public boolean hasSignedData()
    {
      return this.hasSignedData;
    }

    public SignedDataProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSignedData(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setSignature(paramCodedInputStreamMicro.readString());
      }
    }

    public SignedDataProto setSignature(String paramString)
    {
      this.hasSignature = true;
      this.signature_ = paramString;
      return this;
    }

    public SignedDataProto setSignedData(String paramString)
    {
      this.hasSignedData = true;
      this.signedData_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSignedData())
        paramCodedOutputStreamMicro.writeString(1, getSignedData());
      if (hasSignature())
        paramCodedOutputStreamMicro.writeString(2, getSignature());
    }
  }

  public static final class SingleRequestProto extends MessageMicro
  {
    private VendingProtos.AckNotificationsRequestProto ackNotificationsRequest_ = null;
    private VendingProtos.AssetsRequestProto assetRequest_ = null;
    private VendingProtos.BillingEventRequestProto billingEventRequest_ = null;
    private int cachedSize = -1;
    private VendingProtos.CheckForNotificationsRequestProto checkForNotificationsRequest_ = null;
    private VendingProtos.CheckLicenseRequestProto checkLicenseRequest_ = null;
    private VendingProtos.CommentsRequestProto commentsRequest_ = null;
    private VendingProtos.ContentSyncRequestProto contentSyncRequest_ = null;
    private VendingProtos.GetAddressSnippetRequestProto getAddressSnippetRequest_ = null;
    private VendingProtos.GetAssetRequestProto getAssetRequest_ = null;
    private VendingProtos.GetCarrierInfoRequestProto getCarrierInfoRequest_ = null;
    private VendingProtos.GetCategoriesRequestProto getCategoriesRequest_ = null;
    private VendingProtos.GetImageRequestProto getImageRequest_ = null;
    private VendingProtos.InAppPurchaseInformationRequestProto getInAppPurchaseInformationRequest_ = null;
    private VendingProtos.GetMarketMetadataRequestProto getMarketMetadataRequest_ = null;
    private boolean hasAckNotificationsRequest;
    private boolean hasAssetRequest;
    private boolean hasBillingEventRequest;
    private boolean hasCheckForNotificationsRequest;
    private boolean hasCheckLicenseRequest;
    private boolean hasCommentsRequest;
    private boolean hasContentSyncRequest;
    private boolean hasGetAddressSnippetRequest;
    private boolean hasGetAssetRequest;
    private boolean hasGetCarrierInfoRequest;
    private boolean hasGetCategoriesRequest;
    private boolean hasGetImageRequest;
    private boolean hasGetInAppPurchaseInformationRequest;
    private boolean hasGetMarketMetadataRequest;
    private boolean hasInAppRestoreTransactionsRequest;
    private boolean hasModifyCommentRequest;
    private boolean hasPaypalCreateAccountRequest;
    private boolean hasPaypalMassageAddressRequest;
    private boolean hasPaypalPreapprovalCredentialsRequest;
    private boolean hasPaypalPreapprovalDetailsRequest;
    private boolean hasPaypalPreapprovalRequest;
    private boolean hasPurchaseMetadataRequest;
    private boolean hasPurchaseOrderRequest;
    private boolean hasPurchasePostRequest;
    private boolean hasPurchaseProductRequest;
    private boolean hasQuerySuggestionRequest;
    private boolean hasRateCommentRequest;
    private boolean hasReconstructDatabaseRequest;
    private boolean hasRefundRequest;
    private boolean hasRemoveAssetRequest;
    private boolean hasRequestSpecificProperties;
    private boolean hasRestoreApplicationsRequest;
    private boolean hasSubCategoriesRequest;
    private boolean hasUninstallReasonRequest;
    private VendingProtos.InAppRestoreTransactionsRequestProto inAppRestoreTransactionsRequest_ = null;
    private VendingProtos.ModifyCommentRequestProto modifyCommentRequest_ = null;
    private VendingProtos.PaypalCreateAccountRequestProto paypalCreateAccountRequest_ = null;
    private VendingProtos.PaypalMassageAddressRequestProto paypalMassageAddressRequest_ = null;
    private VendingProtos.PaypalPreapprovalCredentialsRequestProto paypalPreapprovalCredentialsRequest_ = null;
    private VendingProtos.PaypalPreapprovalDetailsRequestProto paypalPreapprovalDetailsRequest_ = null;
    private VendingProtos.PaypalPreapprovalRequestProto paypalPreapprovalRequest_ = null;
    private VendingProtos.PurchaseMetadataRequestProto purchaseMetadataRequest_ = null;
    private VendingProtos.PurchaseOrderRequestProto purchaseOrderRequest_ = null;
    private VendingProtos.PurchasePostRequestProto purchasePostRequest_ = null;
    private VendingProtos.PurchaseProductRequestProto purchaseProductRequest_ = null;
    private VendingProtos.QuerySuggestionRequestProto querySuggestionRequest_ = null;
    private VendingProtos.RateCommentRequestProto rateCommentRequest_ = null;
    private VendingProtos.ReconstructDatabaseRequestProto reconstructDatabaseRequest_ = null;
    private VendingProtos.RefundRequestProto refundRequest_ = null;
    private VendingProtos.RemoveAssetRequestProto removeAssetRequest_ = null;
    private VendingProtos.RequestSpecificPropertiesProto requestSpecificProperties_ = null;
    private VendingProtos.RestoreApplicationsRequestProto restoreApplicationsRequest_ = null;
    private VendingProtos.GetSubCategoriesRequestProto subCategoriesRequest_ = null;
    private VendingProtos.UninstallReasonRequestProto uninstallReasonRequest_ = null;

    public VendingProtos.AckNotificationsRequestProto getAckNotificationsRequest()
    {
      return this.ackNotificationsRequest_;
    }

    public VendingProtos.AssetsRequestProto getAssetRequest()
    {
      return this.assetRequest_;
    }

    public VendingProtos.BillingEventRequestProto getBillingEventRequest()
    {
      return this.billingEventRequest_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.CheckForNotificationsRequestProto getCheckForNotificationsRequest()
    {
      return this.checkForNotificationsRequest_;
    }

    public VendingProtos.CheckLicenseRequestProto getCheckLicenseRequest()
    {
      return this.checkLicenseRequest_;
    }

    public VendingProtos.CommentsRequestProto getCommentsRequest()
    {
      return this.commentsRequest_;
    }

    public VendingProtos.ContentSyncRequestProto getContentSyncRequest()
    {
      return this.contentSyncRequest_;
    }

    public VendingProtos.GetAddressSnippetRequestProto getGetAddressSnippetRequest()
    {
      return this.getAddressSnippetRequest_;
    }

    public VendingProtos.GetAssetRequestProto getGetAssetRequest()
    {
      return this.getAssetRequest_;
    }

    public VendingProtos.GetCarrierInfoRequestProto getGetCarrierInfoRequest()
    {
      return this.getCarrierInfoRequest_;
    }

    public VendingProtos.GetCategoriesRequestProto getGetCategoriesRequest()
    {
      return this.getCategoriesRequest_;
    }

    public VendingProtos.GetImageRequestProto getGetImageRequest()
    {
      return this.getImageRequest_;
    }

    public VendingProtos.InAppPurchaseInformationRequestProto getGetInAppPurchaseInformationRequest()
    {
      return this.getInAppPurchaseInformationRequest_;
    }

    public VendingProtos.GetMarketMetadataRequestProto getGetMarketMetadataRequest()
    {
      return this.getMarketMetadataRequest_;
    }

    public VendingProtos.InAppRestoreTransactionsRequestProto getInAppRestoreTransactionsRequest()
    {
      return this.inAppRestoreTransactionsRequest_;
    }

    public VendingProtos.ModifyCommentRequestProto getModifyCommentRequest()
    {
      return this.modifyCommentRequest_;
    }

    public VendingProtos.PaypalCreateAccountRequestProto getPaypalCreateAccountRequest()
    {
      return this.paypalCreateAccountRequest_;
    }

    public VendingProtos.PaypalMassageAddressRequestProto getPaypalMassageAddressRequest()
    {
      return this.paypalMassageAddressRequest_;
    }

    public VendingProtos.PaypalPreapprovalCredentialsRequestProto getPaypalPreapprovalCredentialsRequest()
    {
      return this.paypalPreapprovalCredentialsRequest_;
    }

    public VendingProtos.PaypalPreapprovalDetailsRequestProto getPaypalPreapprovalDetailsRequest()
    {
      return this.paypalPreapprovalDetailsRequest_;
    }

    public VendingProtos.PaypalPreapprovalRequestProto getPaypalPreapprovalRequest()
    {
      return this.paypalPreapprovalRequest_;
    }

    public VendingProtos.PurchaseMetadataRequestProto getPurchaseMetadataRequest()
    {
      return this.purchaseMetadataRequest_;
    }

    public VendingProtos.PurchaseOrderRequestProto getPurchaseOrderRequest()
    {
      return this.purchaseOrderRequest_;
    }

    public VendingProtos.PurchasePostRequestProto getPurchasePostRequest()
    {
      return this.purchasePostRequest_;
    }

    public VendingProtos.PurchaseProductRequestProto getPurchaseProductRequest()
    {
      return this.purchaseProductRequest_;
    }

    public VendingProtos.QuerySuggestionRequestProto getQuerySuggestionRequest()
    {
      return this.querySuggestionRequest_;
    }

    public VendingProtos.RateCommentRequestProto getRateCommentRequest()
    {
      return this.rateCommentRequest_;
    }

    public VendingProtos.ReconstructDatabaseRequestProto getReconstructDatabaseRequest()
    {
      return this.reconstructDatabaseRequest_;
    }

    public VendingProtos.RefundRequestProto getRefundRequest()
    {
      return this.refundRequest_;
    }

    public VendingProtos.RemoveAssetRequestProto getRemoveAssetRequest()
    {
      return this.removeAssetRequest_;
    }

    public VendingProtos.RequestSpecificPropertiesProto getRequestSpecificProperties()
    {
      return this.requestSpecificProperties_;
    }

    public VendingProtos.RestoreApplicationsRequestProto getRestoreApplicationsRequest()
    {
      return this.restoreApplicationsRequest_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasRequestSpecificProperties())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(3, getRequestSpecificProperties());
      if (hasAssetRequest())
        i += CodedOutputStreamMicro.computeMessageSize(4, getAssetRequest());
      if (hasCommentsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(5, getCommentsRequest());
      if (hasModifyCommentRequest())
        i += CodedOutputStreamMicro.computeMessageSize(6, getModifyCommentRequest());
      if (hasPurchasePostRequest())
        i += CodedOutputStreamMicro.computeMessageSize(7, getPurchasePostRequest());
      if (hasPurchaseOrderRequest())
        i += CodedOutputStreamMicro.computeMessageSize(8, getPurchaseOrderRequest());
      if (hasContentSyncRequest())
        i += CodedOutputStreamMicro.computeMessageSize(9, getContentSyncRequest());
      if (hasGetAssetRequest())
        i += CodedOutputStreamMicro.computeMessageSize(10, getGetAssetRequest());
      if (hasGetImageRequest())
        i += CodedOutputStreamMicro.computeMessageSize(11, getGetImageRequest());
      if (hasRefundRequest())
        i += CodedOutputStreamMicro.computeMessageSize(12, getRefundRequest());
      if (hasPurchaseMetadataRequest())
        i += CodedOutputStreamMicro.computeMessageSize(13, getPurchaseMetadataRequest());
      if (hasSubCategoriesRequest())
        i += CodedOutputStreamMicro.computeMessageSize(14, getSubCategoriesRequest());
      if (hasUninstallReasonRequest())
        i += CodedOutputStreamMicro.computeMessageSize(16, getUninstallReasonRequest());
      if (hasRateCommentRequest())
        i += CodedOutputStreamMicro.computeMessageSize(17, getRateCommentRequest());
      if (hasCheckLicenseRequest())
        i += CodedOutputStreamMicro.computeMessageSize(18, getCheckLicenseRequest());
      if (hasGetMarketMetadataRequest())
        i += CodedOutputStreamMicro.computeMessageSize(19, getGetMarketMetadataRequest());
      if (hasGetCategoriesRequest())
        i += CodedOutputStreamMicro.computeMessageSize(21, getGetCategoriesRequest());
      if (hasGetCarrierInfoRequest())
        i += CodedOutputStreamMicro.computeMessageSize(22, getGetCarrierInfoRequest());
      if (hasRemoveAssetRequest())
        i += CodedOutputStreamMicro.computeMessageSize(23, getRemoveAssetRequest());
      if (hasRestoreApplicationsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(24, getRestoreApplicationsRequest());
      if (hasQuerySuggestionRequest())
        i += CodedOutputStreamMicro.computeMessageSize(25, getQuerySuggestionRequest());
      if (hasBillingEventRequest())
        i += CodedOutputStreamMicro.computeMessageSize(26, getBillingEventRequest());
      if (hasPaypalPreapprovalRequest())
        i += CodedOutputStreamMicro.computeMessageSize(27, getPaypalPreapprovalRequest());
      if (hasPaypalPreapprovalDetailsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(28, getPaypalPreapprovalDetailsRequest());
      if (hasPaypalCreateAccountRequest())
        i += CodedOutputStreamMicro.computeMessageSize(29, getPaypalCreateAccountRequest());
      if (hasPaypalPreapprovalCredentialsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(30, getPaypalPreapprovalCredentialsRequest());
      if (hasInAppRestoreTransactionsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(31, getInAppRestoreTransactionsRequest());
      if (hasGetInAppPurchaseInformationRequest())
        i += CodedOutputStreamMicro.computeMessageSize(32, getGetInAppPurchaseInformationRequest());
      if (hasCheckForNotificationsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(33, getCheckForNotificationsRequest());
      if (hasAckNotificationsRequest())
        i += CodedOutputStreamMicro.computeMessageSize(34, getAckNotificationsRequest());
      if (hasPurchaseProductRequest())
        i += CodedOutputStreamMicro.computeMessageSize(35, getPurchaseProductRequest());
      if (hasReconstructDatabaseRequest())
        i += CodedOutputStreamMicro.computeMessageSize(36, getReconstructDatabaseRequest());
      if (hasPaypalMassageAddressRequest())
        i += CodedOutputStreamMicro.computeMessageSize(37, getPaypalMassageAddressRequest());
      if (hasGetAddressSnippetRequest())
        i += CodedOutputStreamMicro.computeMessageSize(38, getGetAddressSnippetRequest());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.GetSubCategoriesRequestProto getSubCategoriesRequest()
    {
      return this.subCategoriesRequest_;
    }

    public VendingProtos.UninstallReasonRequestProto getUninstallReasonRequest()
    {
      return this.uninstallReasonRequest_;
    }

    public boolean hasAckNotificationsRequest()
    {
      return this.hasAckNotificationsRequest;
    }

    public boolean hasAssetRequest()
    {
      return this.hasAssetRequest;
    }

    public boolean hasBillingEventRequest()
    {
      return this.hasBillingEventRequest;
    }

    public boolean hasCheckForNotificationsRequest()
    {
      return this.hasCheckForNotificationsRequest;
    }

    public boolean hasCheckLicenseRequest()
    {
      return this.hasCheckLicenseRequest;
    }

    public boolean hasCommentsRequest()
    {
      return this.hasCommentsRequest;
    }

    public boolean hasContentSyncRequest()
    {
      return this.hasContentSyncRequest;
    }

    public boolean hasGetAddressSnippetRequest()
    {
      return this.hasGetAddressSnippetRequest;
    }

    public boolean hasGetAssetRequest()
    {
      return this.hasGetAssetRequest;
    }

    public boolean hasGetCarrierInfoRequest()
    {
      return this.hasGetCarrierInfoRequest;
    }

    public boolean hasGetCategoriesRequest()
    {
      return this.hasGetCategoriesRequest;
    }

    public boolean hasGetImageRequest()
    {
      return this.hasGetImageRequest;
    }

    public boolean hasGetInAppPurchaseInformationRequest()
    {
      return this.hasGetInAppPurchaseInformationRequest;
    }

    public boolean hasGetMarketMetadataRequest()
    {
      return this.hasGetMarketMetadataRequest;
    }

    public boolean hasInAppRestoreTransactionsRequest()
    {
      return this.hasInAppRestoreTransactionsRequest;
    }

    public boolean hasModifyCommentRequest()
    {
      return this.hasModifyCommentRequest;
    }

    public boolean hasPaypalCreateAccountRequest()
    {
      return this.hasPaypalCreateAccountRequest;
    }

    public boolean hasPaypalMassageAddressRequest()
    {
      return this.hasPaypalMassageAddressRequest;
    }

    public boolean hasPaypalPreapprovalCredentialsRequest()
    {
      return this.hasPaypalPreapprovalCredentialsRequest;
    }

    public boolean hasPaypalPreapprovalDetailsRequest()
    {
      return this.hasPaypalPreapprovalDetailsRequest;
    }

    public boolean hasPaypalPreapprovalRequest()
    {
      return this.hasPaypalPreapprovalRequest;
    }

    public boolean hasPurchaseMetadataRequest()
    {
      return this.hasPurchaseMetadataRequest;
    }

    public boolean hasPurchaseOrderRequest()
    {
      return this.hasPurchaseOrderRequest;
    }

    public boolean hasPurchasePostRequest()
    {
      return this.hasPurchasePostRequest;
    }

    public boolean hasPurchaseProductRequest()
    {
      return this.hasPurchaseProductRequest;
    }

    public boolean hasQuerySuggestionRequest()
    {
      return this.hasQuerySuggestionRequest;
    }

    public boolean hasRateCommentRequest()
    {
      return this.hasRateCommentRequest;
    }

    public boolean hasReconstructDatabaseRequest()
    {
      return this.hasReconstructDatabaseRequest;
    }

    public boolean hasRefundRequest()
    {
      return this.hasRefundRequest;
    }

    public boolean hasRemoveAssetRequest()
    {
      return this.hasRemoveAssetRequest;
    }

    public boolean hasRequestSpecificProperties()
    {
      return this.hasRequestSpecificProperties;
    }

    public boolean hasRestoreApplicationsRequest()
    {
      return this.hasRestoreApplicationsRequest;
    }

    public boolean hasSubCategoriesRequest()
    {
      return this.hasSubCategoriesRequest;
    }

    public boolean hasUninstallReasonRequest()
    {
      return this.hasUninstallReasonRequest;
    }

    public SingleRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 26:
          VendingProtos.RequestSpecificPropertiesProto localRequestSpecificPropertiesProto = new VendingProtos.RequestSpecificPropertiesProto();
          paramCodedInputStreamMicro.readMessage(localRequestSpecificPropertiesProto);
          setRequestSpecificProperties(localRequestSpecificPropertiesProto);
          break;
        case 34:
          VendingProtos.AssetsRequestProto localAssetsRequestProto = new VendingProtos.AssetsRequestProto();
          paramCodedInputStreamMicro.readMessage(localAssetsRequestProto);
          setAssetRequest(localAssetsRequestProto);
          break;
        case 42:
          VendingProtos.CommentsRequestProto localCommentsRequestProto = new VendingProtos.CommentsRequestProto();
          paramCodedInputStreamMicro.readMessage(localCommentsRequestProto);
          setCommentsRequest(localCommentsRequestProto);
          break;
        case 50:
          VendingProtos.ModifyCommentRequestProto localModifyCommentRequestProto = new VendingProtos.ModifyCommentRequestProto();
          paramCodedInputStreamMicro.readMessage(localModifyCommentRequestProto);
          setModifyCommentRequest(localModifyCommentRequestProto);
          break;
        case 58:
          VendingProtos.PurchasePostRequestProto localPurchasePostRequestProto = new VendingProtos.PurchasePostRequestProto();
          paramCodedInputStreamMicro.readMessage(localPurchasePostRequestProto);
          setPurchasePostRequest(localPurchasePostRequestProto);
          break;
        case 66:
          VendingProtos.PurchaseOrderRequestProto localPurchaseOrderRequestProto = new VendingProtos.PurchaseOrderRequestProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseOrderRequestProto);
          setPurchaseOrderRequest(localPurchaseOrderRequestProto);
          break;
        case 74:
          VendingProtos.ContentSyncRequestProto localContentSyncRequestProto = new VendingProtos.ContentSyncRequestProto();
          paramCodedInputStreamMicro.readMessage(localContentSyncRequestProto);
          setContentSyncRequest(localContentSyncRequestProto);
          break;
        case 82:
          VendingProtos.GetAssetRequestProto localGetAssetRequestProto = new VendingProtos.GetAssetRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetAssetRequestProto);
          setGetAssetRequest(localGetAssetRequestProto);
          break;
        case 90:
          VendingProtos.GetImageRequestProto localGetImageRequestProto = new VendingProtos.GetImageRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetImageRequestProto);
          setGetImageRequest(localGetImageRequestProto);
          break;
        case 98:
          VendingProtos.RefundRequestProto localRefundRequestProto = new VendingProtos.RefundRequestProto();
          paramCodedInputStreamMicro.readMessage(localRefundRequestProto);
          setRefundRequest(localRefundRequestProto);
          break;
        case 106:
          VendingProtos.PurchaseMetadataRequestProto localPurchaseMetadataRequestProto = new VendingProtos.PurchaseMetadataRequestProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseMetadataRequestProto);
          setPurchaseMetadataRequest(localPurchaseMetadataRequestProto);
          break;
        case 114:
          VendingProtos.GetSubCategoriesRequestProto localGetSubCategoriesRequestProto = new VendingProtos.GetSubCategoriesRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetSubCategoriesRequestProto);
          setSubCategoriesRequest(localGetSubCategoriesRequestProto);
          break;
        case 130:
          VendingProtos.UninstallReasonRequestProto localUninstallReasonRequestProto = new VendingProtos.UninstallReasonRequestProto();
          paramCodedInputStreamMicro.readMessage(localUninstallReasonRequestProto);
          setUninstallReasonRequest(localUninstallReasonRequestProto);
          break;
        case 138:
          VendingProtos.RateCommentRequestProto localRateCommentRequestProto = new VendingProtos.RateCommentRequestProto();
          paramCodedInputStreamMicro.readMessage(localRateCommentRequestProto);
          setRateCommentRequest(localRateCommentRequestProto);
          break;
        case 146:
          VendingProtos.CheckLicenseRequestProto localCheckLicenseRequestProto = new VendingProtos.CheckLicenseRequestProto();
          paramCodedInputStreamMicro.readMessage(localCheckLicenseRequestProto);
          setCheckLicenseRequest(localCheckLicenseRequestProto);
          break;
        case 154:
          VendingProtos.GetMarketMetadataRequestProto localGetMarketMetadataRequestProto = new VendingProtos.GetMarketMetadataRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetMarketMetadataRequestProto);
          setGetMarketMetadataRequest(localGetMarketMetadataRequestProto);
          break;
        case 170:
          VendingProtos.GetCategoriesRequestProto localGetCategoriesRequestProto = new VendingProtos.GetCategoriesRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetCategoriesRequestProto);
          setGetCategoriesRequest(localGetCategoriesRequestProto);
          break;
        case 178:
          VendingProtos.GetCarrierInfoRequestProto localGetCarrierInfoRequestProto = new VendingProtos.GetCarrierInfoRequestProto();
          paramCodedInputStreamMicro.readMessage(localGetCarrierInfoRequestProto);
          setGetCarrierInfoRequest(localGetCarrierInfoRequestProto);
          break;
        case 186:
          VendingProtos.RemoveAssetRequestProto localRemoveAssetRequestProto = new VendingProtos.RemoveAssetRequestProto();
          paramCodedInputStreamMicro.readMessage(localRemoveAssetRequestProto);
          setRemoveAssetRequest(localRemoveAssetRequestProto);
          break;
        case 194:
          VendingProtos.RestoreApplicationsRequestProto localRestoreApplicationsRequestProto = new VendingProtos.RestoreApplicationsRequestProto();
          paramCodedInputStreamMicro.readMessage(localRestoreApplicationsRequestProto);
          setRestoreApplicationsRequest(localRestoreApplicationsRequestProto);
          break;
        case 202:
          VendingProtos.QuerySuggestionRequestProto localQuerySuggestionRequestProto = new VendingProtos.QuerySuggestionRequestProto();
          paramCodedInputStreamMicro.readMessage(localQuerySuggestionRequestProto);
          setQuerySuggestionRequest(localQuerySuggestionRequestProto);
          break;
        case 210:
          VendingProtos.BillingEventRequestProto localBillingEventRequestProto = new VendingProtos.BillingEventRequestProto();
          paramCodedInputStreamMicro.readMessage(localBillingEventRequestProto);
          setBillingEventRequest(localBillingEventRequestProto);
          break;
        case 218:
          VendingProtos.PaypalPreapprovalRequestProto localPaypalPreapprovalRequestProto = new VendingProtos.PaypalPreapprovalRequestProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalRequestProto);
          setPaypalPreapprovalRequest(localPaypalPreapprovalRequestProto);
          break;
        case 226:
          VendingProtos.PaypalPreapprovalDetailsRequestProto localPaypalPreapprovalDetailsRequestProto = new VendingProtos.PaypalPreapprovalDetailsRequestProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalDetailsRequestProto);
          setPaypalPreapprovalDetailsRequest(localPaypalPreapprovalDetailsRequestProto);
          break;
        case 234:
          VendingProtos.PaypalCreateAccountRequestProto localPaypalCreateAccountRequestProto = new VendingProtos.PaypalCreateAccountRequestProto();
          paramCodedInputStreamMicro.readMessage(localPaypalCreateAccountRequestProto);
          setPaypalCreateAccountRequest(localPaypalCreateAccountRequestProto);
          break;
        case 242:
          VendingProtos.PaypalPreapprovalCredentialsRequestProto localPaypalPreapprovalCredentialsRequestProto = new VendingProtos.PaypalPreapprovalCredentialsRequestProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalCredentialsRequestProto);
          setPaypalPreapprovalCredentialsRequest(localPaypalPreapprovalCredentialsRequestProto);
          break;
        case 250:
          VendingProtos.InAppRestoreTransactionsRequestProto localInAppRestoreTransactionsRequestProto = new VendingProtos.InAppRestoreTransactionsRequestProto();
          paramCodedInputStreamMicro.readMessage(localInAppRestoreTransactionsRequestProto);
          setInAppRestoreTransactionsRequest(localInAppRestoreTransactionsRequestProto);
          break;
        case 258:
          VendingProtos.InAppPurchaseInformationRequestProto localInAppPurchaseInformationRequestProto = new VendingProtos.InAppPurchaseInformationRequestProto();
          paramCodedInputStreamMicro.readMessage(localInAppPurchaseInformationRequestProto);
          setGetInAppPurchaseInformationRequest(localInAppPurchaseInformationRequestProto);
          break;
        case 266:
          VendingProtos.CheckForNotificationsRequestProto localCheckForNotificationsRequestProto = new VendingProtos.CheckForNotificationsRequestProto();
          paramCodedInputStreamMicro.readMessage(localCheckForNotificationsRequestProto);
          setCheckForNotificationsRequest(localCheckForNotificationsRequestProto);
          break;
        case 274:
          VendingProtos.AckNotificationsRequestProto localAckNotificationsRequestProto = new VendingProtos.AckNotificationsRequestProto();
          paramCodedInputStreamMicro.readMessage(localAckNotificationsRequestProto);
          setAckNotificationsRequest(localAckNotificationsRequestProto);
          break;
        case 282:
          VendingProtos.PurchaseProductRequestProto localPurchaseProductRequestProto = new VendingProtos.PurchaseProductRequestProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseProductRequestProto);
          setPurchaseProductRequest(localPurchaseProductRequestProto);
          break;
        case 290:
          VendingProtos.ReconstructDatabaseRequestProto localReconstructDatabaseRequestProto = new VendingProtos.ReconstructDatabaseRequestProto();
          paramCodedInputStreamMicro.readMessage(localReconstructDatabaseRequestProto);
          setReconstructDatabaseRequest(localReconstructDatabaseRequestProto);
          break;
        case 298:
          VendingProtos.PaypalMassageAddressRequestProto localPaypalMassageAddressRequestProto = new VendingProtos.PaypalMassageAddressRequestProto();
          paramCodedInputStreamMicro.readMessage(localPaypalMassageAddressRequestProto);
          setPaypalMassageAddressRequest(localPaypalMassageAddressRequestProto);
          break;
        case 306:
        }
        VendingProtos.GetAddressSnippetRequestProto localGetAddressSnippetRequestProto = new VendingProtos.GetAddressSnippetRequestProto();
        paramCodedInputStreamMicro.readMessage(localGetAddressSnippetRequestProto);
        setGetAddressSnippetRequest(localGetAddressSnippetRequestProto);
      }
    }

    public SingleRequestProto setAckNotificationsRequest(VendingProtos.AckNotificationsRequestProto paramAckNotificationsRequestProto)
    {
      if (paramAckNotificationsRequestProto == null)
        throw new NullPointerException();
      this.hasAckNotificationsRequest = true;
      this.ackNotificationsRequest_ = paramAckNotificationsRequestProto;
      return this;
    }

    public SingleRequestProto setAssetRequest(VendingProtos.AssetsRequestProto paramAssetsRequestProto)
    {
      if (paramAssetsRequestProto == null)
        throw new NullPointerException();
      this.hasAssetRequest = true;
      this.assetRequest_ = paramAssetsRequestProto;
      return this;
    }

    public SingleRequestProto setBillingEventRequest(VendingProtos.BillingEventRequestProto paramBillingEventRequestProto)
    {
      if (paramBillingEventRequestProto == null)
        throw new NullPointerException();
      this.hasBillingEventRequest = true;
      this.billingEventRequest_ = paramBillingEventRequestProto;
      return this;
    }

    public SingleRequestProto setCheckForNotificationsRequest(VendingProtos.CheckForNotificationsRequestProto paramCheckForNotificationsRequestProto)
    {
      if (paramCheckForNotificationsRequestProto == null)
        throw new NullPointerException();
      this.hasCheckForNotificationsRequest = true;
      this.checkForNotificationsRequest_ = paramCheckForNotificationsRequestProto;
      return this;
    }

    public SingleRequestProto setCheckLicenseRequest(VendingProtos.CheckLicenseRequestProto paramCheckLicenseRequestProto)
    {
      if (paramCheckLicenseRequestProto == null)
        throw new NullPointerException();
      this.hasCheckLicenseRequest = true;
      this.checkLicenseRequest_ = paramCheckLicenseRequestProto;
      return this;
    }

    public SingleRequestProto setCommentsRequest(VendingProtos.CommentsRequestProto paramCommentsRequestProto)
    {
      if (paramCommentsRequestProto == null)
        throw new NullPointerException();
      this.hasCommentsRequest = true;
      this.commentsRequest_ = paramCommentsRequestProto;
      return this;
    }

    public SingleRequestProto setContentSyncRequest(VendingProtos.ContentSyncRequestProto paramContentSyncRequestProto)
    {
      if (paramContentSyncRequestProto == null)
        throw new NullPointerException();
      this.hasContentSyncRequest = true;
      this.contentSyncRequest_ = paramContentSyncRequestProto;
      return this;
    }

    public SingleRequestProto setGetAddressSnippetRequest(VendingProtos.GetAddressSnippetRequestProto paramGetAddressSnippetRequestProto)
    {
      if (paramGetAddressSnippetRequestProto == null)
        throw new NullPointerException();
      this.hasGetAddressSnippetRequest = true;
      this.getAddressSnippetRequest_ = paramGetAddressSnippetRequestProto;
      return this;
    }

    public SingleRequestProto setGetAssetRequest(VendingProtos.GetAssetRequestProto paramGetAssetRequestProto)
    {
      if (paramGetAssetRequestProto == null)
        throw new NullPointerException();
      this.hasGetAssetRequest = true;
      this.getAssetRequest_ = paramGetAssetRequestProto;
      return this;
    }

    public SingleRequestProto setGetCarrierInfoRequest(VendingProtos.GetCarrierInfoRequestProto paramGetCarrierInfoRequestProto)
    {
      if (paramGetCarrierInfoRequestProto == null)
        throw new NullPointerException();
      this.hasGetCarrierInfoRequest = true;
      this.getCarrierInfoRequest_ = paramGetCarrierInfoRequestProto;
      return this;
    }

    public SingleRequestProto setGetCategoriesRequest(VendingProtos.GetCategoriesRequestProto paramGetCategoriesRequestProto)
    {
      if (paramGetCategoriesRequestProto == null)
        throw new NullPointerException();
      this.hasGetCategoriesRequest = true;
      this.getCategoriesRequest_ = paramGetCategoriesRequestProto;
      return this;
    }

    public SingleRequestProto setGetImageRequest(VendingProtos.GetImageRequestProto paramGetImageRequestProto)
    {
      if (paramGetImageRequestProto == null)
        throw new NullPointerException();
      this.hasGetImageRequest = true;
      this.getImageRequest_ = paramGetImageRequestProto;
      return this;
    }

    public SingleRequestProto setGetInAppPurchaseInformationRequest(VendingProtos.InAppPurchaseInformationRequestProto paramInAppPurchaseInformationRequestProto)
    {
      if (paramInAppPurchaseInformationRequestProto == null)
        throw new NullPointerException();
      this.hasGetInAppPurchaseInformationRequest = true;
      this.getInAppPurchaseInformationRequest_ = paramInAppPurchaseInformationRequestProto;
      return this;
    }

    public SingleRequestProto setGetMarketMetadataRequest(VendingProtos.GetMarketMetadataRequestProto paramGetMarketMetadataRequestProto)
    {
      if (paramGetMarketMetadataRequestProto == null)
        throw new NullPointerException();
      this.hasGetMarketMetadataRequest = true;
      this.getMarketMetadataRequest_ = paramGetMarketMetadataRequestProto;
      return this;
    }

    public SingleRequestProto setInAppRestoreTransactionsRequest(VendingProtos.InAppRestoreTransactionsRequestProto paramInAppRestoreTransactionsRequestProto)
    {
      if (paramInAppRestoreTransactionsRequestProto == null)
        throw new NullPointerException();
      this.hasInAppRestoreTransactionsRequest = true;
      this.inAppRestoreTransactionsRequest_ = paramInAppRestoreTransactionsRequestProto;
      return this;
    }

    public SingleRequestProto setModifyCommentRequest(VendingProtos.ModifyCommentRequestProto paramModifyCommentRequestProto)
    {
      if (paramModifyCommentRequestProto == null)
        throw new NullPointerException();
      this.hasModifyCommentRequest = true;
      this.modifyCommentRequest_ = paramModifyCommentRequestProto;
      return this;
    }

    public SingleRequestProto setPaypalCreateAccountRequest(VendingProtos.PaypalCreateAccountRequestProto paramPaypalCreateAccountRequestProto)
    {
      if (paramPaypalCreateAccountRequestProto == null)
        throw new NullPointerException();
      this.hasPaypalCreateAccountRequest = true;
      this.paypalCreateAccountRequest_ = paramPaypalCreateAccountRequestProto;
      return this;
    }

    public SingleRequestProto setPaypalMassageAddressRequest(VendingProtos.PaypalMassageAddressRequestProto paramPaypalMassageAddressRequestProto)
    {
      if (paramPaypalMassageAddressRequestProto == null)
        throw new NullPointerException();
      this.hasPaypalMassageAddressRequest = true;
      this.paypalMassageAddressRequest_ = paramPaypalMassageAddressRequestProto;
      return this;
    }

    public SingleRequestProto setPaypalPreapprovalCredentialsRequest(VendingProtos.PaypalPreapprovalCredentialsRequestProto paramPaypalPreapprovalCredentialsRequestProto)
    {
      if (paramPaypalPreapprovalCredentialsRequestProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalCredentialsRequest = true;
      this.paypalPreapprovalCredentialsRequest_ = paramPaypalPreapprovalCredentialsRequestProto;
      return this;
    }

    public SingleRequestProto setPaypalPreapprovalDetailsRequest(VendingProtos.PaypalPreapprovalDetailsRequestProto paramPaypalPreapprovalDetailsRequestProto)
    {
      if (paramPaypalPreapprovalDetailsRequestProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalDetailsRequest = true;
      this.paypalPreapprovalDetailsRequest_ = paramPaypalPreapprovalDetailsRequestProto;
      return this;
    }

    public SingleRequestProto setPaypalPreapprovalRequest(VendingProtos.PaypalPreapprovalRequestProto paramPaypalPreapprovalRequestProto)
    {
      if (paramPaypalPreapprovalRequestProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalRequest = true;
      this.paypalPreapprovalRequest_ = paramPaypalPreapprovalRequestProto;
      return this;
    }

    public SingleRequestProto setPurchaseMetadataRequest(VendingProtos.PurchaseMetadataRequestProto paramPurchaseMetadataRequestProto)
    {
      if (paramPurchaseMetadataRequestProto == null)
        throw new NullPointerException();
      this.hasPurchaseMetadataRequest = true;
      this.purchaseMetadataRequest_ = paramPurchaseMetadataRequestProto;
      return this;
    }

    public SingleRequestProto setPurchaseOrderRequest(VendingProtos.PurchaseOrderRequestProto paramPurchaseOrderRequestProto)
    {
      if (paramPurchaseOrderRequestProto == null)
        throw new NullPointerException();
      this.hasPurchaseOrderRequest = true;
      this.purchaseOrderRequest_ = paramPurchaseOrderRequestProto;
      return this;
    }

    public SingleRequestProto setPurchasePostRequest(VendingProtos.PurchasePostRequestProto paramPurchasePostRequestProto)
    {
      if (paramPurchasePostRequestProto == null)
        throw new NullPointerException();
      this.hasPurchasePostRequest = true;
      this.purchasePostRequest_ = paramPurchasePostRequestProto;
      return this;
    }

    public SingleRequestProto setPurchaseProductRequest(VendingProtos.PurchaseProductRequestProto paramPurchaseProductRequestProto)
    {
      if (paramPurchaseProductRequestProto == null)
        throw new NullPointerException();
      this.hasPurchaseProductRequest = true;
      this.purchaseProductRequest_ = paramPurchaseProductRequestProto;
      return this;
    }

    public SingleRequestProto setQuerySuggestionRequest(VendingProtos.QuerySuggestionRequestProto paramQuerySuggestionRequestProto)
    {
      if (paramQuerySuggestionRequestProto == null)
        throw new NullPointerException();
      this.hasQuerySuggestionRequest = true;
      this.querySuggestionRequest_ = paramQuerySuggestionRequestProto;
      return this;
    }

    public SingleRequestProto setRateCommentRequest(VendingProtos.RateCommentRequestProto paramRateCommentRequestProto)
    {
      if (paramRateCommentRequestProto == null)
        throw new NullPointerException();
      this.hasRateCommentRequest = true;
      this.rateCommentRequest_ = paramRateCommentRequestProto;
      return this;
    }

    public SingleRequestProto setReconstructDatabaseRequest(VendingProtos.ReconstructDatabaseRequestProto paramReconstructDatabaseRequestProto)
    {
      if (paramReconstructDatabaseRequestProto == null)
        throw new NullPointerException();
      this.hasReconstructDatabaseRequest = true;
      this.reconstructDatabaseRequest_ = paramReconstructDatabaseRequestProto;
      return this;
    }

    public SingleRequestProto setRefundRequest(VendingProtos.RefundRequestProto paramRefundRequestProto)
    {
      if (paramRefundRequestProto == null)
        throw new NullPointerException();
      this.hasRefundRequest = true;
      this.refundRequest_ = paramRefundRequestProto;
      return this;
    }

    public SingleRequestProto setRemoveAssetRequest(VendingProtos.RemoveAssetRequestProto paramRemoveAssetRequestProto)
    {
      if (paramRemoveAssetRequestProto == null)
        throw new NullPointerException();
      this.hasRemoveAssetRequest = true;
      this.removeAssetRequest_ = paramRemoveAssetRequestProto;
      return this;
    }

    public SingleRequestProto setRequestSpecificProperties(VendingProtos.RequestSpecificPropertiesProto paramRequestSpecificPropertiesProto)
    {
      if (paramRequestSpecificPropertiesProto == null)
        throw new NullPointerException();
      this.hasRequestSpecificProperties = true;
      this.requestSpecificProperties_ = paramRequestSpecificPropertiesProto;
      return this;
    }

    public SingleRequestProto setRestoreApplicationsRequest(VendingProtos.RestoreApplicationsRequestProto paramRestoreApplicationsRequestProto)
    {
      if (paramRestoreApplicationsRequestProto == null)
        throw new NullPointerException();
      this.hasRestoreApplicationsRequest = true;
      this.restoreApplicationsRequest_ = paramRestoreApplicationsRequestProto;
      return this;
    }

    public SingleRequestProto setSubCategoriesRequest(VendingProtos.GetSubCategoriesRequestProto paramGetSubCategoriesRequestProto)
    {
      if (paramGetSubCategoriesRequestProto == null)
        throw new NullPointerException();
      this.hasSubCategoriesRequest = true;
      this.subCategoriesRequest_ = paramGetSubCategoriesRequestProto;
      return this;
    }

    public SingleRequestProto setUninstallReasonRequest(VendingProtos.UninstallReasonRequestProto paramUninstallReasonRequestProto)
    {
      if (paramUninstallReasonRequestProto == null)
        throw new NullPointerException();
      this.hasUninstallReasonRequest = true;
      this.uninstallReasonRequest_ = paramUninstallReasonRequestProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasRequestSpecificProperties())
        paramCodedOutputStreamMicro.writeMessage(3, getRequestSpecificProperties());
      if (hasAssetRequest())
        paramCodedOutputStreamMicro.writeMessage(4, getAssetRequest());
      if (hasCommentsRequest())
        paramCodedOutputStreamMicro.writeMessage(5, getCommentsRequest());
      if (hasModifyCommentRequest())
        paramCodedOutputStreamMicro.writeMessage(6, getModifyCommentRequest());
      if (hasPurchasePostRequest())
        paramCodedOutputStreamMicro.writeMessage(7, getPurchasePostRequest());
      if (hasPurchaseOrderRequest())
        paramCodedOutputStreamMicro.writeMessage(8, getPurchaseOrderRequest());
      if (hasContentSyncRequest())
        paramCodedOutputStreamMicro.writeMessage(9, getContentSyncRequest());
      if (hasGetAssetRequest())
        paramCodedOutputStreamMicro.writeMessage(10, getGetAssetRequest());
      if (hasGetImageRequest())
        paramCodedOutputStreamMicro.writeMessage(11, getGetImageRequest());
      if (hasRefundRequest())
        paramCodedOutputStreamMicro.writeMessage(12, getRefundRequest());
      if (hasPurchaseMetadataRequest())
        paramCodedOutputStreamMicro.writeMessage(13, getPurchaseMetadataRequest());
      if (hasSubCategoriesRequest())
        paramCodedOutputStreamMicro.writeMessage(14, getSubCategoriesRequest());
      if (hasUninstallReasonRequest())
        paramCodedOutputStreamMicro.writeMessage(16, getUninstallReasonRequest());
      if (hasRateCommentRequest())
        paramCodedOutputStreamMicro.writeMessage(17, getRateCommentRequest());
      if (hasCheckLicenseRequest())
        paramCodedOutputStreamMicro.writeMessage(18, getCheckLicenseRequest());
      if (hasGetMarketMetadataRequest())
        paramCodedOutputStreamMicro.writeMessage(19, getGetMarketMetadataRequest());
      if (hasGetCategoriesRequest())
        paramCodedOutputStreamMicro.writeMessage(21, getGetCategoriesRequest());
      if (hasGetCarrierInfoRequest())
        paramCodedOutputStreamMicro.writeMessage(22, getGetCarrierInfoRequest());
      if (hasRemoveAssetRequest())
        paramCodedOutputStreamMicro.writeMessage(23, getRemoveAssetRequest());
      if (hasRestoreApplicationsRequest())
        paramCodedOutputStreamMicro.writeMessage(24, getRestoreApplicationsRequest());
      if (hasQuerySuggestionRequest())
        paramCodedOutputStreamMicro.writeMessage(25, getQuerySuggestionRequest());
      if (hasBillingEventRequest())
        paramCodedOutputStreamMicro.writeMessage(26, getBillingEventRequest());
      if (hasPaypalPreapprovalRequest())
        paramCodedOutputStreamMicro.writeMessage(27, getPaypalPreapprovalRequest());
      if (hasPaypalPreapprovalDetailsRequest())
        paramCodedOutputStreamMicro.writeMessage(28, getPaypalPreapprovalDetailsRequest());
      if (hasPaypalCreateAccountRequest())
        paramCodedOutputStreamMicro.writeMessage(29, getPaypalCreateAccountRequest());
      if (hasPaypalPreapprovalCredentialsRequest())
        paramCodedOutputStreamMicro.writeMessage(30, getPaypalPreapprovalCredentialsRequest());
      if (hasInAppRestoreTransactionsRequest())
        paramCodedOutputStreamMicro.writeMessage(31, getInAppRestoreTransactionsRequest());
      if (hasGetInAppPurchaseInformationRequest())
        paramCodedOutputStreamMicro.writeMessage(32, getGetInAppPurchaseInformationRequest());
      if (hasCheckForNotificationsRequest())
        paramCodedOutputStreamMicro.writeMessage(33, getCheckForNotificationsRequest());
      if (hasAckNotificationsRequest())
        paramCodedOutputStreamMicro.writeMessage(34, getAckNotificationsRequest());
      if (hasPurchaseProductRequest())
        paramCodedOutputStreamMicro.writeMessage(35, getPurchaseProductRequest());
      if (hasReconstructDatabaseRequest())
        paramCodedOutputStreamMicro.writeMessage(36, getReconstructDatabaseRequest());
      if (hasPaypalMassageAddressRequest())
        paramCodedOutputStreamMicro.writeMessage(37, getPaypalMassageAddressRequest());
      if (hasGetAddressSnippetRequest())
        paramCodedOutputStreamMicro.writeMessage(38, getGetAddressSnippetRequest());
    }
  }

  public static final class SingleResponseProto extends MessageMicro
  {
    private VendingProtos.AckNotificationsResponseProto ackNotificationsResponse_ = null;
    private VendingProtos.AssetsResponseProto assetsResponse_ = null;
    private VendingProtos.BillingEventResponseProto billingEventResponse_ = null;
    private int cachedSize = -1;
    private VendingProtos.CheckForNotificationsResponseProto checkForNotificationsResponse_ = null;
    private VendingProtos.CheckLicenseResponseProto checkLicenseResponse_ = null;
    private VendingProtos.CommentsResponseProto commentsResponse_ = null;
    private VendingProtos.ContentSyncResponseProto contentSyncResponse_ = null;
    private VendingProtos.GetAddressSnippetResponseProto getAddressSnippetResponse_ = null;
    private VendingProtos.GetAssetResponseProto getAssetResponse_ = null;
    private VendingProtos.GetCarrierInfoResponseProto getCarrierInfoResponse_ = null;
    private VendingProtos.GetCategoriesResponseProto getCategoriesResponse_ = null;
    private VendingProtos.GetImageResponseProto getImageResponse_ = null;
    private VendingProtos.InAppPurchaseInformationResponseProto getInAppPurchaseInformationResponse_ = null;
    private VendingProtos.GetMarketMetadataResponseProto getMarketMetadataResponse_ = null;
    private boolean hasAckNotificationsResponse;
    private boolean hasAssetsResponse;
    private boolean hasBillingEventResponse;
    private boolean hasCheckForNotificationsResponse;
    private boolean hasCheckLicenseResponse;
    private boolean hasCommentsResponse;
    private boolean hasContentSyncResponse;
    private boolean hasGetAddressSnippetResponse;
    private boolean hasGetAssetResponse;
    private boolean hasGetCarrierInfoResponse;
    private boolean hasGetCategoriesResponse;
    private boolean hasGetImageResponse;
    private boolean hasGetInAppPurchaseInformationResponse;
    private boolean hasGetMarketMetadataResponse;
    private boolean hasInAppRestoreTransactionsResponse;
    private boolean hasModifyCommentResponse;
    private boolean hasPaypalCreateAccountResponse;
    private boolean hasPaypalMassageAddressResponse;
    private boolean hasPaypalPreapprovalCredentialsResponse;
    private boolean hasPaypalPreapprovalDetailsResponse;
    private boolean hasPaypalPreapprovalResponse;
    private boolean hasPurchaseMetadataResponse;
    private boolean hasPurchaseOrderResponse;
    private boolean hasPurchasePostResponse;
    private boolean hasPurchaseProductResponse;
    private boolean hasQuerySuggestionResponse;
    private boolean hasRateCommentResponse;
    private boolean hasReconstructDatabaseResponse;
    private boolean hasRefundResponse;
    private boolean hasResponseProperties;
    private boolean hasRestoreApplicationResponse;
    private boolean hasSubCategoriesResponse;
    private boolean hasUninstallReasonResponse;
    private VendingProtos.InAppRestoreTransactionsResponseProto inAppRestoreTransactionsResponse_ = null;
    private VendingProtos.ModifyCommentResponseProto modifyCommentResponse_ = null;
    private VendingProtos.PaypalCreateAccountResponseProto paypalCreateAccountResponse_ = null;
    private VendingProtos.PaypalMassageAddressResponseProto paypalMassageAddressResponse_ = null;
    private VendingProtos.PaypalPreapprovalCredentialsResponseProto paypalPreapprovalCredentialsResponse_ = null;
    private VendingProtos.PaypalPreapprovalDetailsResponseProto paypalPreapprovalDetailsResponse_ = null;
    private VendingProtos.PaypalPreapprovalResponseProto paypalPreapprovalResponse_ = null;
    private VendingProtos.PurchaseMetadataResponseProto purchaseMetadataResponse_ = null;
    private VendingProtos.PurchaseOrderResponseProto purchaseOrderResponse_ = null;
    private VendingProtos.PurchasePostResponseProto purchasePostResponse_ = null;
    private VendingProtos.PurchaseProductResponseProto purchaseProductResponse_ = null;
    private VendingProtos.QuerySuggestionResponseProto querySuggestionResponse_ = null;
    private VendingProtos.RateCommentResponseProto rateCommentResponse_ = null;
    private VendingProtos.ReconstructDatabaseResponseProto reconstructDatabaseResponse_ = null;
    private VendingProtos.RefundResponseProto refundResponse_ = null;
    private VendingProtos.ResponsePropertiesProto responseProperties_ = null;
    private VendingProtos.RestoreApplicationsResponseProto restoreApplicationResponse_ = null;
    private VendingProtos.GetSubCategoriesResponseProto subCategoriesResponse_ = null;
    private VendingProtos.UninstallReasonResponseProto uninstallReasonResponse_ = null;

    public VendingProtos.AckNotificationsResponseProto getAckNotificationsResponse()
    {
      return this.ackNotificationsResponse_;
    }

    public VendingProtos.AssetsResponseProto getAssetsResponse()
    {
      return this.assetsResponse_;
    }

    public VendingProtos.BillingEventResponseProto getBillingEventResponse()
    {
      return this.billingEventResponse_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public VendingProtos.CheckForNotificationsResponseProto getCheckForNotificationsResponse()
    {
      return this.checkForNotificationsResponse_;
    }

    public VendingProtos.CheckLicenseResponseProto getCheckLicenseResponse()
    {
      return this.checkLicenseResponse_;
    }

    public VendingProtos.CommentsResponseProto getCommentsResponse()
    {
      return this.commentsResponse_;
    }

    public VendingProtos.ContentSyncResponseProto getContentSyncResponse()
    {
      return this.contentSyncResponse_;
    }

    public VendingProtos.GetAddressSnippetResponseProto getGetAddressSnippetResponse()
    {
      return this.getAddressSnippetResponse_;
    }

    public VendingProtos.GetAssetResponseProto getGetAssetResponse()
    {
      return this.getAssetResponse_;
    }

    public VendingProtos.GetCarrierInfoResponseProto getGetCarrierInfoResponse()
    {
      return this.getCarrierInfoResponse_;
    }

    public VendingProtos.GetCategoriesResponseProto getGetCategoriesResponse()
    {
      return this.getCategoriesResponse_;
    }

    public VendingProtos.GetImageResponseProto getGetImageResponse()
    {
      return this.getImageResponse_;
    }

    public VendingProtos.InAppPurchaseInformationResponseProto getGetInAppPurchaseInformationResponse()
    {
      return this.getInAppPurchaseInformationResponse_;
    }

    public VendingProtos.GetMarketMetadataResponseProto getGetMarketMetadataResponse()
    {
      return this.getMarketMetadataResponse_;
    }

    public VendingProtos.InAppRestoreTransactionsResponseProto getInAppRestoreTransactionsResponse()
    {
      return this.inAppRestoreTransactionsResponse_;
    }

    public VendingProtos.ModifyCommentResponseProto getModifyCommentResponse()
    {
      return this.modifyCommentResponse_;
    }

    public VendingProtos.PaypalCreateAccountResponseProto getPaypalCreateAccountResponse()
    {
      return this.paypalCreateAccountResponse_;
    }

    public VendingProtos.PaypalMassageAddressResponseProto getPaypalMassageAddressResponse()
    {
      return this.paypalMassageAddressResponse_;
    }

    public VendingProtos.PaypalPreapprovalCredentialsResponseProto getPaypalPreapprovalCredentialsResponse()
    {
      return this.paypalPreapprovalCredentialsResponse_;
    }

    public VendingProtos.PaypalPreapprovalDetailsResponseProto getPaypalPreapprovalDetailsResponse()
    {
      return this.paypalPreapprovalDetailsResponse_;
    }

    public VendingProtos.PaypalPreapprovalResponseProto getPaypalPreapprovalResponse()
    {
      return this.paypalPreapprovalResponse_;
    }

    public VendingProtos.PurchaseMetadataResponseProto getPurchaseMetadataResponse()
    {
      return this.purchaseMetadataResponse_;
    }

    public VendingProtos.PurchaseOrderResponseProto getPurchaseOrderResponse()
    {
      return this.purchaseOrderResponse_;
    }

    public VendingProtos.PurchasePostResponseProto getPurchasePostResponse()
    {
      return this.purchasePostResponse_;
    }

    public VendingProtos.PurchaseProductResponseProto getPurchaseProductResponse()
    {
      return this.purchaseProductResponse_;
    }

    public VendingProtos.QuerySuggestionResponseProto getQuerySuggestionResponse()
    {
      return this.querySuggestionResponse_;
    }

    public VendingProtos.RateCommentResponseProto getRateCommentResponse()
    {
      return this.rateCommentResponse_;
    }

    public VendingProtos.ReconstructDatabaseResponseProto getReconstructDatabaseResponse()
    {
      return this.reconstructDatabaseResponse_;
    }

    public VendingProtos.RefundResponseProto getRefundResponse()
    {
      return this.refundResponse_;
    }

    public VendingProtos.ResponsePropertiesProto getResponseProperties()
    {
      return this.responseProperties_;
    }

    public VendingProtos.RestoreApplicationsResponseProto getRestoreApplicationResponse()
    {
      return this.restoreApplicationResponse_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasResponseProperties())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(2, getResponseProperties());
      if (hasAssetsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(3, getAssetsResponse());
      if (hasCommentsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(4, getCommentsResponse());
      if (hasModifyCommentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(5, getModifyCommentResponse());
      if (hasPurchasePostResponse())
        i += CodedOutputStreamMicro.computeMessageSize(6, getPurchasePostResponse());
      if (hasPurchaseOrderResponse())
        i += CodedOutputStreamMicro.computeMessageSize(7, getPurchaseOrderResponse());
      if (hasContentSyncResponse())
        i += CodedOutputStreamMicro.computeMessageSize(8, getContentSyncResponse());
      if (hasGetAssetResponse())
        i += CodedOutputStreamMicro.computeMessageSize(9, getGetAssetResponse());
      if (hasGetImageResponse())
        i += CodedOutputStreamMicro.computeMessageSize(10, getGetImageResponse());
      if (hasRefundResponse())
        i += CodedOutputStreamMicro.computeMessageSize(11, getRefundResponse());
      if (hasPurchaseMetadataResponse())
        i += CodedOutputStreamMicro.computeMessageSize(12, getPurchaseMetadataResponse());
      if (hasSubCategoriesResponse())
        i += CodedOutputStreamMicro.computeMessageSize(13, getSubCategoriesResponse());
      if (hasUninstallReasonResponse())
        i += CodedOutputStreamMicro.computeMessageSize(15, getUninstallReasonResponse());
      if (hasRateCommentResponse())
        i += CodedOutputStreamMicro.computeMessageSize(16, getRateCommentResponse());
      if (hasCheckLicenseResponse())
        i += CodedOutputStreamMicro.computeMessageSize(17, getCheckLicenseResponse());
      if (hasGetMarketMetadataResponse())
        i += CodedOutputStreamMicro.computeMessageSize(18, getGetMarketMetadataResponse());
      if (hasGetCategoriesResponse())
        i += CodedOutputStreamMicro.computeMessageSize(20, getGetCategoriesResponse());
      if (hasGetCarrierInfoResponse())
        i += CodedOutputStreamMicro.computeMessageSize(21, getGetCarrierInfoResponse());
      if (hasRestoreApplicationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(23, getRestoreApplicationResponse());
      if (hasQuerySuggestionResponse())
        i += CodedOutputStreamMicro.computeMessageSize(24, getQuerySuggestionResponse());
      if (hasBillingEventResponse())
        i += CodedOutputStreamMicro.computeMessageSize(25, getBillingEventResponse());
      if (hasPaypalPreapprovalResponse())
        i += CodedOutputStreamMicro.computeMessageSize(26, getPaypalPreapprovalResponse());
      if (hasPaypalPreapprovalDetailsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(27, getPaypalPreapprovalDetailsResponse());
      if (hasPaypalCreateAccountResponse())
        i += CodedOutputStreamMicro.computeMessageSize(28, getPaypalCreateAccountResponse());
      if (hasPaypalPreapprovalCredentialsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(29, getPaypalPreapprovalCredentialsResponse());
      if (hasInAppRestoreTransactionsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(30, getInAppRestoreTransactionsResponse());
      if (hasGetInAppPurchaseInformationResponse())
        i += CodedOutputStreamMicro.computeMessageSize(31, getGetInAppPurchaseInformationResponse());
      if (hasCheckForNotificationsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(32, getCheckForNotificationsResponse());
      if (hasAckNotificationsResponse())
        i += CodedOutputStreamMicro.computeMessageSize(33, getAckNotificationsResponse());
      if (hasPurchaseProductResponse())
        i += CodedOutputStreamMicro.computeMessageSize(34, getPurchaseProductResponse());
      if (hasReconstructDatabaseResponse())
        i += CodedOutputStreamMicro.computeMessageSize(35, getReconstructDatabaseResponse());
      if (hasPaypalMassageAddressResponse())
        i += CodedOutputStreamMicro.computeMessageSize(36, getPaypalMassageAddressResponse());
      if (hasGetAddressSnippetResponse())
        i += CodedOutputStreamMicro.computeMessageSize(37, getGetAddressSnippetResponse());
      this.cachedSize = i;
      return i;
    }

    public VendingProtos.GetSubCategoriesResponseProto getSubCategoriesResponse()
    {
      return this.subCategoriesResponse_;
    }

    public VendingProtos.UninstallReasonResponseProto getUninstallReasonResponse()
    {
      return this.uninstallReasonResponse_;
    }

    public boolean hasAckNotificationsResponse()
    {
      return this.hasAckNotificationsResponse;
    }

    public boolean hasAssetsResponse()
    {
      return this.hasAssetsResponse;
    }

    public boolean hasBillingEventResponse()
    {
      return this.hasBillingEventResponse;
    }

    public boolean hasCheckForNotificationsResponse()
    {
      return this.hasCheckForNotificationsResponse;
    }

    public boolean hasCheckLicenseResponse()
    {
      return this.hasCheckLicenseResponse;
    }

    public boolean hasCommentsResponse()
    {
      return this.hasCommentsResponse;
    }

    public boolean hasContentSyncResponse()
    {
      return this.hasContentSyncResponse;
    }

    public boolean hasGetAddressSnippetResponse()
    {
      return this.hasGetAddressSnippetResponse;
    }

    public boolean hasGetAssetResponse()
    {
      return this.hasGetAssetResponse;
    }

    public boolean hasGetCarrierInfoResponse()
    {
      return this.hasGetCarrierInfoResponse;
    }

    public boolean hasGetCategoriesResponse()
    {
      return this.hasGetCategoriesResponse;
    }

    public boolean hasGetImageResponse()
    {
      return this.hasGetImageResponse;
    }

    public boolean hasGetInAppPurchaseInformationResponse()
    {
      return this.hasGetInAppPurchaseInformationResponse;
    }

    public boolean hasGetMarketMetadataResponse()
    {
      return this.hasGetMarketMetadataResponse;
    }

    public boolean hasInAppRestoreTransactionsResponse()
    {
      return this.hasInAppRestoreTransactionsResponse;
    }

    public boolean hasModifyCommentResponse()
    {
      return this.hasModifyCommentResponse;
    }

    public boolean hasPaypalCreateAccountResponse()
    {
      return this.hasPaypalCreateAccountResponse;
    }

    public boolean hasPaypalMassageAddressResponse()
    {
      return this.hasPaypalMassageAddressResponse;
    }

    public boolean hasPaypalPreapprovalCredentialsResponse()
    {
      return this.hasPaypalPreapprovalCredentialsResponse;
    }

    public boolean hasPaypalPreapprovalDetailsResponse()
    {
      return this.hasPaypalPreapprovalDetailsResponse;
    }

    public boolean hasPaypalPreapprovalResponse()
    {
      return this.hasPaypalPreapprovalResponse;
    }

    public boolean hasPurchaseMetadataResponse()
    {
      return this.hasPurchaseMetadataResponse;
    }

    public boolean hasPurchaseOrderResponse()
    {
      return this.hasPurchaseOrderResponse;
    }

    public boolean hasPurchasePostResponse()
    {
      return this.hasPurchasePostResponse;
    }

    public boolean hasPurchaseProductResponse()
    {
      return this.hasPurchaseProductResponse;
    }

    public boolean hasQuerySuggestionResponse()
    {
      return this.hasQuerySuggestionResponse;
    }

    public boolean hasRateCommentResponse()
    {
      return this.hasRateCommentResponse;
    }

    public boolean hasReconstructDatabaseResponse()
    {
      return this.hasReconstructDatabaseResponse;
    }

    public boolean hasRefundResponse()
    {
      return this.hasRefundResponse;
    }

    public boolean hasResponseProperties()
    {
      return this.hasResponseProperties;
    }

    public boolean hasRestoreApplicationResponse()
    {
      return this.hasRestoreApplicationResponse;
    }

    public boolean hasSubCategoriesResponse()
    {
      return this.hasSubCategoriesResponse;
    }

    public boolean hasUninstallReasonResponse()
    {
      return this.hasUninstallReasonResponse;
    }

    public SingleResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        case 18:
          VendingProtos.ResponsePropertiesProto localResponsePropertiesProto = new VendingProtos.ResponsePropertiesProto();
          paramCodedInputStreamMicro.readMessage(localResponsePropertiesProto);
          setResponseProperties(localResponsePropertiesProto);
          break;
        case 26:
          VendingProtos.AssetsResponseProto localAssetsResponseProto = new VendingProtos.AssetsResponseProto();
          paramCodedInputStreamMicro.readMessage(localAssetsResponseProto);
          setAssetsResponse(localAssetsResponseProto);
          break;
        case 34:
          VendingProtos.CommentsResponseProto localCommentsResponseProto = new VendingProtos.CommentsResponseProto();
          paramCodedInputStreamMicro.readMessage(localCommentsResponseProto);
          setCommentsResponse(localCommentsResponseProto);
          break;
        case 42:
          VendingProtos.ModifyCommentResponseProto localModifyCommentResponseProto = new VendingProtos.ModifyCommentResponseProto();
          paramCodedInputStreamMicro.readMessage(localModifyCommentResponseProto);
          setModifyCommentResponse(localModifyCommentResponseProto);
          break;
        case 50:
          VendingProtos.PurchasePostResponseProto localPurchasePostResponseProto = new VendingProtos.PurchasePostResponseProto();
          paramCodedInputStreamMicro.readMessage(localPurchasePostResponseProto);
          setPurchasePostResponse(localPurchasePostResponseProto);
          break;
        case 58:
          VendingProtos.PurchaseOrderResponseProto localPurchaseOrderResponseProto = new VendingProtos.PurchaseOrderResponseProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseOrderResponseProto);
          setPurchaseOrderResponse(localPurchaseOrderResponseProto);
          break;
        case 66:
          VendingProtos.ContentSyncResponseProto localContentSyncResponseProto = new VendingProtos.ContentSyncResponseProto();
          paramCodedInputStreamMicro.readMessage(localContentSyncResponseProto);
          setContentSyncResponse(localContentSyncResponseProto);
          break;
        case 74:
          VendingProtos.GetAssetResponseProto localGetAssetResponseProto = new VendingProtos.GetAssetResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetAssetResponseProto);
          setGetAssetResponse(localGetAssetResponseProto);
          break;
        case 82:
          VendingProtos.GetImageResponseProto localGetImageResponseProto = new VendingProtos.GetImageResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetImageResponseProto);
          setGetImageResponse(localGetImageResponseProto);
          break;
        case 90:
          VendingProtos.RefundResponseProto localRefundResponseProto = new VendingProtos.RefundResponseProto();
          paramCodedInputStreamMicro.readMessage(localRefundResponseProto);
          setRefundResponse(localRefundResponseProto);
          break;
        case 98:
          VendingProtos.PurchaseMetadataResponseProto localPurchaseMetadataResponseProto = new VendingProtos.PurchaseMetadataResponseProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseMetadataResponseProto);
          setPurchaseMetadataResponse(localPurchaseMetadataResponseProto);
          break;
        case 106:
          VendingProtos.GetSubCategoriesResponseProto localGetSubCategoriesResponseProto = new VendingProtos.GetSubCategoriesResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetSubCategoriesResponseProto);
          setSubCategoriesResponse(localGetSubCategoriesResponseProto);
          break;
        case 122:
          VendingProtos.UninstallReasonResponseProto localUninstallReasonResponseProto = new VendingProtos.UninstallReasonResponseProto();
          paramCodedInputStreamMicro.readMessage(localUninstallReasonResponseProto);
          setUninstallReasonResponse(localUninstallReasonResponseProto);
          break;
        case 130:
          VendingProtos.RateCommentResponseProto localRateCommentResponseProto = new VendingProtos.RateCommentResponseProto();
          paramCodedInputStreamMicro.readMessage(localRateCommentResponseProto);
          setRateCommentResponse(localRateCommentResponseProto);
          break;
        case 138:
          VendingProtos.CheckLicenseResponseProto localCheckLicenseResponseProto = new VendingProtos.CheckLicenseResponseProto();
          paramCodedInputStreamMicro.readMessage(localCheckLicenseResponseProto);
          setCheckLicenseResponse(localCheckLicenseResponseProto);
          break;
        case 146:
          VendingProtos.GetMarketMetadataResponseProto localGetMarketMetadataResponseProto = new VendingProtos.GetMarketMetadataResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetMarketMetadataResponseProto);
          setGetMarketMetadataResponse(localGetMarketMetadataResponseProto);
          break;
        case 162:
          VendingProtos.GetCategoriesResponseProto localGetCategoriesResponseProto = new VendingProtos.GetCategoriesResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetCategoriesResponseProto);
          setGetCategoriesResponse(localGetCategoriesResponseProto);
          break;
        case 170:
          VendingProtos.GetCarrierInfoResponseProto localGetCarrierInfoResponseProto = new VendingProtos.GetCarrierInfoResponseProto();
          paramCodedInputStreamMicro.readMessage(localGetCarrierInfoResponseProto);
          setGetCarrierInfoResponse(localGetCarrierInfoResponseProto);
          break;
        case 186:
          VendingProtos.RestoreApplicationsResponseProto localRestoreApplicationsResponseProto = new VendingProtos.RestoreApplicationsResponseProto();
          paramCodedInputStreamMicro.readMessage(localRestoreApplicationsResponseProto);
          setRestoreApplicationResponse(localRestoreApplicationsResponseProto);
          break;
        case 194:
          VendingProtos.QuerySuggestionResponseProto localQuerySuggestionResponseProto = new VendingProtos.QuerySuggestionResponseProto();
          paramCodedInputStreamMicro.readMessage(localQuerySuggestionResponseProto);
          setQuerySuggestionResponse(localQuerySuggestionResponseProto);
          break;
        case 202:
          VendingProtos.BillingEventResponseProto localBillingEventResponseProto = new VendingProtos.BillingEventResponseProto();
          paramCodedInputStreamMicro.readMessage(localBillingEventResponseProto);
          setBillingEventResponse(localBillingEventResponseProto);
          break;
        case 210:
          VendingProtos.PaypalPreapprovalResponseProto localPaypalPreapprovalResponseProto = new VendingProtos.PaypalPreapprovalResponseProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalResponseProto);
          setPaypalPreapprovalResponse(localPaypalPreapprovalResponseProto);
          break;
        case 218:
          VendingProtos.PaypalPreapprovalDetailsResponseProto localPaypalPreapprovalDetailsResponseProto = new VendingProtos.PaypalPreapprovalDetailsResponseProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalDetailsResponseProto);
          setPaypalPreapprovalDetailsResponse(localPaypalPreapprovalDetailsResponseProto);
          break;
        case 226:
          VendingProtos.PaypalCreateAccountResponseProto localPaypalCreateAccountResponseProto = new VendingProtos.PaypalCreateAccountResponseProto();
          paramCodedInputStreamMicro.readMessage(localPaypalCreateAccountResponseProto);
          setPaypalCreateAccountResponse(localPaypalCreateAccountResponseProto);
          break;
        case 234:
          VendingProtos.PaypalPreapprovalCredentialsResponseProto localPaypalPreapprovalCredentialsResponseProto = new VendingProtos.PaypalPreapprovalCredentialsResponseProto();
          paramCodedInputStreamMicro.readMessage(localPaypalPreapprovalCredentialsResponseProto);
          setPaypalPreapprovalCredentialsResponse(localPaypalPreapprovalCredentialsResponseProto);
          break;
        case 242:
          VendingProtos.InAppRestoreTransactionsResponseProto localInAppRestoreTransactionsResponseProto = new VendingProtos.InAppRestoreTransactionsResponseProto();
          paramCodedInputStreamMicro.readMessage(localInAppRestoreTransactionsResponseProto);
          setInAppRestoreTransactionsResponse(localInAppRestoreTransactionsResponseProto);
          break;
        case 250:
          VendingProtos.InAppPurchaseInformationResponseProto localInAppPurchaseInformationResponseProto = new VendingProtos.InAppPurchaseInformationResponseProto();
          paramCodedInputStreamMicro.readMessage(localInAppPurchaseInformationResponseProto);
          setGetInAppPurchaseInformationResponse(localInAppPurchaseInformationResponseProto);
          break;
        case 258:
          VendingProtos.CheckForNotificationsResponseProto localCheckForNotificationsResponseProto = new VendingProtos.CheckForNotificationsResponseProto();
          paramCodedInputStreamMicro.readMessage(localCheckForNotificationsResponseProto);
          setCheckForNotificationsResponse(localCheckForNotificationsResponseProto);
          break;
        case 266:
          VendingProtos.AckNotificationsResponseProto localAckNotificationsResponseProto = new VendingProtos.AckNotificationsResponseProto();
          paramCodedInputStreamMicro.readMessage(localAckNotificationsResponseProto);
          setAckNotificationsResponse(localAckNotificationsResponseProto);
          break;
        case 274:
          VendingProtos.PurchaseProductResponseProto localPurchaseProductResponseProto = new VendingProtos.PurchaseProductResponseProto();
          paramCodedInputStreamMicro.readMessage(localPurchaseProductResponseProto);
          setPurchaseProductResponse(localPurchaseProductResponseProto);
          break;
        case 282:
          VendingProtos.ReconstructDatabaseResponseProto localReconstructDatabaseResponseProto = new VendingProtos.ReconstructDatabaseResponseProto();
          paramCodedInputStreamMicro.readMessage(localReconstructDatabaseResponseProto);
          setReconstructDatabaseResponse(localReconstructDatabaseResponseProto);
          break;
        case 290:
          VendingProtos.PaypalMassageAddressResponseProto localPaypalMassageAddressResponseProto = new VendingProtos.PaypalMassageAddressResponseProto();
          paramCodedInputStreamMicro.readMessage(localPaypalMassageAddressResponseProto);
          setPaypalMassageAddressResponse(localPaypalMassageAddressResponseProto);
          break;
        case 298:
        }
        VendingProtos.GetAddressSnippetResponseProto localGetAddressSnippetResponseProto = new VendingProtos.GetAddressSnippetResponseProto();
        paramCodedInputStreamMicro.readMessage(localGetAddressSnippetResponseProto);
        setGetAddressSnippetResponse(localGetAddressSnippetResponseProto);
      }
    }

    public SingleResponseProto setAckNotificationsResponse(VendingProtos.AckNotificationsResponseProto paramAckNotificationsResponseProto)
    {
      if (paramAckNotificationsResponseProto == null)
        throw new NullPointerException();
      this.hasAckNotificationsResponse = true;
      this.ackNotificationsResponse_ = paramAckNotificationsResponseProto;
      return this;
    }

    public SingleResponseProto setAssetsResponse(VendingProtos.AssetsResponseProto paramAssetsResponseProto)
    {
      if (paramAssetsResponseProto == null)
        throw new NullPointerException();
      this.hasAssetsResponse = true;
      this.assetsResponse_ = paramAssetsResponseProto;
      return this;
    }

    public SingleResponseProto setBillingEventResponse(VendingProtos.BillingEventResponseProto paramBillingEventResponseProto)
    {
      if (paramBillingEventResponseProto == null)
        throw new NullPointerException();
      this.hasBillingEventResponse = true;
      this.billingEventResponse_ = paramBillingEventResponseProto;
      return this;
    }

    public SingleResponseProto setCheckForNotificationsResponse(VendingProtos.CheckForNotificationsResponseProto paramCheckForNotificationsResponseProto)
    {
      if (paramCheckForNotificationsResponseProto == null)
        throw new NullPointerException();
      this.hasCheckForNotificationsResponse = true;
      this.checkForNotificationsResponse_ = paramCheckForNotificationsResponseProto;
      return this;
    }

    public SingleResponseProto setCheckLicenseResponse(VendingProtos.CheckLicenseResponseProto paramCheckLicenseResponseProto)
    {
      if (paramCheckLicenseResponseProto == null)
        throw new NullPointerException();
      this.hasCheckLicenseResponse = true;
      this.checkLicenseResponse_ = paramCheckLicenseResponseProto;
      return this;
    }

    public SingleResponseProto setCommentsResponse(VendingProtos.CommentsResponseProto paramCommentsResponseProto)
    {
      if (paramCommentsResponseProto == null)
        throw new NullPointerException();
      this.hasCommentsResponse = true;
      this.commentsResponse_ = paramCommentsResponseProto;
      return this;
    }

    public SingleResponseProto setContentSyncResponse(VendingProtos.ContentSyncResponseProto paramContentSyncResponseProto)
    {
      if (paramContentSyncResponseProto == null)
        throw new NullPointerException();
      this.hasContentSyncResponse = true;
      this.contentSyncResponse_ = paramContentSyncResponseProto;
      return this;
    }

    public SingleResponseProto setGetAddressSnippetResponse(VendingProtos.GetAddressSnippetResponseProto paramGetAddressSnippetResponseProto)
    {
      if (paramGetAddressSnippetResponseProto == null)
        throw new NullPointerException();
      this.hasGetAddressSnippetResponse = true;
      this.getAddressSnippetResponse_ = paramGetAddressSnippetResponseProto;
      return this;
    }

    public SingleResponseProto setGetAssetResponse(VendingProtos.GetAssetResponseProto paramGetAssetResponseProto)
    {
      if (paramGetAssetResponseProto == null)
        throw new NullPointerException();
      this.hasGetAssetResponse = true;
      this.getAssetResponse_ = paramGetAssetResponseProto;
      return this;
    }

    public SingleResponseProto setGetCarrierInfoResponse(VendingProtos.GetCarrierInfoResponseProto paramGetCarrierInfoResponseProto)
    {
      if (paramGetCarrierInfoResponseProto == null)
        throw new NullPointerException();
      this.hasGetCarrierInfoResponse = true;
      this.getCarrierInfoResponse_ = paramGetCarrierInfoResponseProto;
      return this;
    }

    public SingleResponseProto setGetCategoriesResponse(VendingProtos.GetCategoriesResponseProto paramGetCategoriesResponseProto)
    {
      if (paramGetCategoriesResponseProto == null)
        throw new NullPointerException();
      this.hasGetCategoriesResponse = true;
      this.getCategoriesResponse_ = paramGetCategoriesResponseProto;
      return this;
    }

    public SingleResponseProto setGetImageResponse(VendingProtos.GetImageResponseProto paramGetImageResponseProto)
    {
      if (paramGetImageResponseProto == null)
        throw new NullPointerException();
      this.hasGetImageResponse = true;
      this.getImageResponse_ = paramGetImageResponseProto;
      return this;
    }

    public SingleResponseProto setGetInAppPurchaseInformationResponse(VendingProtos.InAppPurchaseInformationResponseProto paramInAppPurchaseInformationResponseProto)
    {
      if (paramInAppPurchaseInformationResponseProto == null)
        throw new NullPointerException();
      this.hasGetInAppPurchaseInformationResponse = true;
      this.getInAppPurchaseInformationResponse_ = paramInAppPurchaseInformationResponseProto;
      return this;
    }

    public SingleResponseProto setGetMarketMetadataResponse(VendingProtos.GetMarketMetadataResponseProto paramGetMarketMetadataResponseProto)
    {
      if (paramGetMarketMetadataResponseProto == null)
        throw new NullPointerException();
      this.hasGetMarketMetadataResponse = true;
      this.getMarketMetadataResponse_ = paramGetMarketMetadataResponseProto;
      return this;
    }

    public SingleResponseProto setInAppRestoreTransactionsResponse(VendingProtos.InAppRestoreTransactionsResponseProto paramInAppRestoreTransactionsResponseProto)
    {
      if (paramInAppRestoreTransactionsResponseProto == null)
        throw new NullPointerException();
      this.hasInAppRestoreTransactionsResponse = true;
      this.inAppRestoreTransactionsResponse_ = paramInAppRestoreTransactionsResponseProto;
      return this;
    }

    public SingleResponseProto setModifyCommentResponse(VendingProtos.ModifyCommentResponseProto paramModifyCommentResponseProto)
    {
      if (paramModifyCommentResponseProto == null)
        throw new NullPointerException();
      this.hasModifyCommentResponse = true;
      this.modifyCommentResponse_ = paramModifyCommentResponseProto;
      return this;
    }

    public SingleResponseProto setPaypalCreateAccountResponse(VendingProtos.PaypalCreateAccountResponseProto paramPaypalCreateAccountResponseProto)
    {
      if (paramPaypalCreateAccountResponseProto == null)
        throw new NullPointerException();
      this.hasPaypalCreateAccountResponse = true;
      this.paypalCreateAccountResponse_ = paramPaypalCreateAccountResponseProto;
      return this;
    }

    public SingleResponseProto setPaypalMassageAddressResponse(VendingProtos.PaypalMassageAddressResponseProto paramPaypalMassageAddressResponseProto)
    {
      if (paramPaypalMassageAddressResponseProto == null)
        throw new NullPointerException();
      this.hasPaypalMassageAddressResponse = true;
      this.paypalMassageAddressResponse_ = paramPaypalMassageAddressResponseProto;
      return this;
    }

    public SingleResponseProto setPaypalPreapprovalCredentialsResponse(VendingProtos.PaypalPreapprovalCredentialsResponseProto paramPaypalPreapprovalCredentialsResponseProto)
    {
      if (paramPaypalPreapprovalCredentialsResponseProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalCredentialsResponse = true;
      this.paypalPreapprovalCredentialsResponse_ = paramPaypalPreapprovalCredentialsResponseProto;
      return this;
    }

    public SingleResponseProto setPaypalPreapprovalDetailsResponse(VendingProtos.PaypalPreapprovalDetailsResponseProto paramPaypalPreapprovalDetailsResponseProto)
    {
      if (paramPaypalPreapprovalDetailsResponseProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalDetailsResponse = true;
      this.paypalPreapprovalDetailsResponse_ = paramPaypalPreapprovalDetailsResponseProto;
      return this;
    }

    public SingleResponseProto setPaypalPreapprovalResponse(VendingProtos.PaypalPreapprovalResponseProto paramPaypalPreapprovalResponseProto)
    {
      if (paramPaypalPreapprovalResponseProto == null)
        throw new NullPointerException();
      this.hasPaypalPreapprovalResponse = true;
      this.paypalPreapprovalResponse_ = paramPaypalPreapprovalResponseProto;
      return this;
    }

    public SingleResponseProto setPurchaseMetadataResponse(VendingProtos.PurchaseMetadataResponseProto paramPurchaseMetadataResponseProto)
    {
      if (paramPurchaseMetadataResponseProto == null)
        throw new NullPointerException();
      this.hasPurchaseMetadataResponse = true;
      this.purchaseMetadataResponse_ = paramPurchaseMetadataResponseProto;
      return this;
    }

    public SingleResponseProto setPurchaseOrderResponse(VendingProtos.PurchaseOrderResponseProto paramPurchaseOrderResponseProto)
    {
      if (paramPurchaseOrderResponseProto == null)
        throw new NullPointerException();
      this.hasPurchaseOrderResponse = true;
      this.purchaseOrderResponse_ = paramPurchaseOrderResponseProto;
      return this;
    }

    public SingleResponseProto setPurchasePostResponse(VendingProtos.PurchasePostResponseProto paramPurchasePostResponseProto)
    {
      if (paramPurchasePostResponseProto == null)
        throw new NullPointerException();
      this.hasPurchasePostResponse = true;
      this.purchasePostResponse_ = paramPurchasePostResponseProto;
      return this;
    }

    public SingleResponseProto setPurchaseProductResponse(VendingProtos.PurchaseProductResponseProto paramPurchaseProductResponseProto)
    {
      if (paramPurchaseProductResponseProto == null)
        throw new NullPointerException();
      this.hasPurchaseProductResponse = true;
      this.purchaseProductResponse_ = paramPurchaseProductResponseProto;
      return this;
    }

    public SingleResponseProto setQuerySuggestionResponse(VendingProtos.QuerySuggestionResponseProto paramQuerySuggestionResponseProto)
    {
      if (paramQuerySuggestionResponseProto == null)
        throw new NullPointerException();
      this.hasQuerySuggestionResponse = true;
      this.querySuggestionResponse_ = paramQuerySuggestionResponseProto;
      return this;
    }

    public SingleResponseProto setRateCommentResponse(VendingProtos.RateCommentResponseProto paramRateCommentResponseProto)
    {
      if (paramRateCommentResponseProto == null)
        throw new NullPointerException();
      this.hasRateCommentResponse = true;
      this.rateCommentResponse_ = paramRateCommentResponseProto;
      return this;
    }

    public SingleResponseProto setReconstructDatabaseResponse(VendingProtos.ReconstructDatabaseResponseProto paramReconstructDatabaseResponseProto)
    {
      if (paramReconstructDatabaseResponseProto == null)
        throw new NullPointerException();
      this.hasReconstructDatabaseResponse = true;
      this.reconstructDatabaseResponse_ = paramReconstructDatabaseResponseProto;
      return this;
    }

    public SingleResponseProto setRefundResponse(VendingProtos.RefundResponseProto paramRefundResponseProto)
    {
      if (paramRefundResponseProto == null)
        throw new NullPointerException();
      this.hasRefundResponse = true;
      this.refundResponse_ = paramRefundResponseProto;
      return this;
    }

    public SingleResponseProto setResponseProperties(VendingProtos.ResponsePropertiesProto paramResponsePropertiesProto)
    {
      if (paramResponsePropertiesProto == null)
        throw new NullPointerException();
      this.hasResponseProperties = true;
      this.responseProperties_ = paramResponsePropertiesProto;
      return this;
    }

    public SingleResponseProto setRestoreApplicationResponse(VendingProtos.RestoreApplicationsResponseProto paramRestoreApplicationsResponseProto)
    {
      if (paramRestoreApplicationsResponseProto == null)
        throw new NullPointerException();
      this.hasRestoreApplicationResponse = true;
      this.restoreApplicationResponse_ = paramRestoreApplicationsResponseProto;
      return this;
    }

    public SingleResponseProto setSubCategoriesResponse(VendingProtos.GetSubCategoriesResponseProto paramGetSubCategoriesResponseProto)
    {
      if (paramGetSubCategoriesResponseProto == null)
        throw new NullPointerException();
      this.hasSubCategoriesResponse = true;
      this.subCategoriesResponse_ = paramGetSubCategoriesResponseProto;
      return this;
    }

    public SingleResponseProto setUninstallReasonResponse(VendingProtos.UninstallReasonResponseProto paramUninstallReasonResponseProto)
    {
      if (paramUninstallReasonResponseProto == null)
        throw new NullPointerException();
      this.hasUninstallReasonResponse = true;
      this.uninstallReasonResponse_ = paramUninstallReasonResponseProto;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasResponseProperties())
        paramCodedOutputStreamMicro.writeMessage(2, getResponseProperties());
      if (hasAssetsResponse())
        paramCodedOutputStreamMicro.writeMessage(3, getAssetsResponse());
      if (hasCommentsResponse())
        paramCodedOutputStreamMicro.writeMessage(4, getCommentsResponse());
      if (hasModifyCommentResponse())
        paramCodedOutputStreamMicro.writeMessage(5, getModifyCommentResponse());
      if (hasPurchasePostResponse())
        paramCodedOutputStreamMicro.writeMessage(6, getPurchasePostResponse());
      if (hasPurchaseOrderResponse())
        paramCodedOutputStreamMicro.writeMessage(7, getPurchaseOrderResponse());
      if (hasContentSyncResponse())
        paramCodedOutputStreamMicro.writeMessage(8, getContentSyncResponse());
      if (hasGetAssetResponse())
        paramCodedOutputStreamMicro.writeMessage(9, getGetAssetResponse());
      if (hasGetImageResponse())
        paramCodedOutputStreamMicro.writeMessage(10, getGetImageResponse());
      if (hasRefundResponse())
        paramCodedOutputStreamMicro.writeMessage(11, getRefundResponse());
      if (hasPurchaseMetadataResponse())
        paramCodedOutputStreamMicro.writeMessage(12, getPurchaseMetadataResponse());
      if (hasSubCategoriesResponse())
        paramCodedOutputStreamMicro.writeMessage(13, getSubCategoriesResponse());
      if (hasUninstallReasonResponse())
        paramCodedOutputStreamMicro.writeMessage(15, getUninstallReasonResponse());
      if (hasRateCommentResponse())
        paramCodedOutputStreamMicro.writeMessage(16, getRateCommentResponse());
      if (hasCheckLicenseResponse())
        paramCodedOutputStreamMicro.writeMessage(17, getCheckLicenseResponse());
      if (hasGetMarketMetadataResponse())
        paramCodedOutputStreamMicro.writeMessage(18, getGetMarketMetadataResponse());
      if (hasGetCategoriesResponse())
        paramCodedOutputStreamMicro.writeMessage(20, getGetCategoriesResponse());
      if (hasGetCarrierInfoResponse())
        paramCodedOutputStreamMicro.writeMessage(21, getGetCarrierInfoResponse());
      if (hasRestoreApplicationResponse())
        paramCodedOutputStreamMicro.writeMessage(23, getRestoreApplicationResponse());
      if (hasQuerySuggestionResponse())
        paramCodedOutputStreamMicro.writeMessage(24, getQuerySuggestionResponse());
      if (hasBillingEventResponse())
        paramCodedOutputStreamMicro.writeMessage(25, getBillingEventResponse());
      if (hasPaypalPreapprovalResponse())
        paramCodedOutputStreamMicro.writeMessage(26, getPaypalPreapprovalResponse());
      if (hasPaypalPreapprovalDetailsResponse())
        paramCodedOutputStreamMicro.writeMessage(27, getPaypalPreapprovalDetailsResponse());
      if (hasPaypalCreateAccountResponse())
        paramCodedOutputStreamMicro.writeMessage(28, getPaypalCreateAccountResponse());
      if (hasPaypalPreapprovalCredentialsResponse())
        paramCodedOutputStreamMicro.writeMessage(29, getPaypalPreapprovalCredentialsResponse());
      if (hasInAppRestoreTransactionsResponse())
        paramCodedOutputStreamMicro.writeMessage(30, getInAppRestoreTransactionsResponse());
      if (hasGetInAppPurchaseInformationResponse())
        paramCodedOutputStreamMicro.writeMessage(31, getGetInAppPurchaseInformationResponse());
      if (hasCheckForNotificationsResponse())
        paramCodedOutputStreamMicro.writeMessage(32, getCheckForNotificationsResponse());
      if (hasAckNotificationsResponse())
        paramCodedOutputStreamMicro.writeMessage(33, getAckNotificationsResponse());
      if (hasPurchaseProductResponse())
        paramCodedOutputStreamMicro.writeMessage(34, getPurchaseProductResponse());
      if (hasReconstructDatabaseResponse())
        paramCodedOutputStreamMicro.writeMessage(35, getReconstructDatabaseResponse());
      if (hasPaypalMassageAddressResponse())
        paramCodedOutputStreamMicro.writeMessage(36, getPaypalMassageAddressResponse());
      if (hasGetAddressSnippetResponse())
        paramCodedOutputStreamMicro.writeMessage(37, getGetAddressSnippetResponse());
    }
  }

  public static final class StatusBarNotificationProto extends MessageMicro
  {
    private int cachedSize = -1;
    private String contentText_ = "";
    private String contentTitle_ = "";
    private boolean hasContentText;
    private boolean hasContentTitle;
    private boolean hasTickerText;
    private String tickerText_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getContentText()
    {
      return this.contentText_;
    }

    public String getContentTitle()
    {
      return this.contentTitle_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTickerText())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTickerText());
      if (hasContentTitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getContentTitle());
      if (hasContentText())
        i += CodedOutputStreamMicro.computeStringSize(3, getContentText());
      this.cachedSize = i;
      return i;
    }

    public String getTickerText()
    {
      return this.tickerText_;
    }

    public boolean hasContentText()
    {
      return this.hasContentText;
    }

    public boolean hasContentTitle()
    {
      return this.hasContentTitle;
    }

    public boolean hasTickerText()
    {
      return this.hasTickerText;
    }

    public StatusBarNotificationProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setTickerText(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setContentTitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setContentText(paramCodedInputStreamMicro.readString());
      }
    }

    public StatusBarNotificationProto setContentText(String paramString)
    {
      this.hasContentText = true;
      this.contentText_ = paramString;
      return this;
    }

    public StatusBarNotificationProto setContentTitle(String paramString)
    {
      this.hasContentTitle = true;
      this.contentTitle_ = paramString;
      return this;
    }

    public StatusBarNotificationProto setTickerText(String paramString)
    {
      this.hasTickerText = true;
      this.tickerText_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasTickerText())
        paramCodedOutputStreamMicro.writeString(1, getTickerText());
      if (hasContentTitle())
        paramCodedOutputStreamMicro.writeString(2, getContentTitle());
      if (hasContentText())
        paramCodedOutputStreamMicro.writeString(3, getContentText());
    }
  }

  public static final class UninstallReasonRequestProto extends MessageMicro
  {
    private String assetId_ = "";
    private int cachedSize = -1;
    private boolean hasAssetId;
    private boolean hasReason;
    private int reason_ = 0;

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

    public int getReason()
    {
      return this.reason_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAssetId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getAssetId());
      if (hasReason())
        i += CodedOutputStreamMicro.computeInt32Size(2, getReason());
      this.cachedSize = i;
      return i;
    }

    public boolean hasAssetId()
    {
      return this.hasAssetId;
    }

    public boolean hasReason()
    {
      return this.hasReason;
    }

    public UninstallReasonRequestProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setAssetId(paramCodedInputStreamMicro.readString());
          break;
        case 16:
        }
        setReason(paramCodedInputStreamMicro.readInt32());
      }
    }

    public UninstallReasonRequestProto setAssetId(String paramString)
    {
      this.hasAssetId = true;
      this.assetId_ = paramString;
      return this;
    }

    public UninstallReasonRequestProto setReason(int paramInt)
    {
      this.hasReason = true;
      this.reason_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAssetId())
        paramCodedOutputStreamMicro.writeString(1, getAssetId());
      if (hasReason())
        paramCodedOutputStreamMicro.writeInt32(2, getReason());
    }
  }

  public static final class UninstallReasonResponseProto extends MessageMicro
  {
    private int cachedSize = -1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      this.cachedSize = 0;
      return 0;
    }

    public UninstallReasonResponseProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
      throws IOException
    {
      int i;
      do
      {
        i = paramCodedInputStreamMicro.readTag();
        switch (i)
        {
        default:
        case 0:
        }
      }
      while (parseUnknownField(paramCodedInputStreamMicro, i));
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.protos.VendingProtos
 * JD-Core Version:    0.6.2
 */
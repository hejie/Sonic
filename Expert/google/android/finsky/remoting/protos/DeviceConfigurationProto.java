package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DeviceConfigurationProto extends MessageMicro
{
  private int cachedSize = -1;
  private int glEsVersion_ = 0;
  private List<String> glExtension_ = Collections.emptyList();
  private boolean hasFiveWayNavigation_ = false;
  private boolean hasGlEsVersion;
  private boolean hasHardKeyboard_ = false;
  private boolean hasHasFiveWayNavigation;
  private boolean hasHasHardKeyboard;
  private boolean hasKeyboard;
  private boolean hasMaxApkDownloadSizeMb;
  private boolean hasNavigation;
  private boolean hasScreenDensity;
  private boolean hasScreenHeight;
  private boolean hasScreenLayout;
  private boolean hasScreenWidth;
  private boolean hasTouchScreen;
  private int keyboard_ = 0;
  private int maxApkDownloadSizeMb_ = 50;
  private List<String> nativePlatform_ = Collections.emptyList();
  private int navigation_ = 0;
  private int screenDensity_ = 0;
  private int screenHeight_ = 0;
  private int screenLayout_ = 0;
  private int screenWidth_ = 0;
  private List<String> systemAvailableFeature_ = Collections.emptyList();
  private List<String> systemSharedLibrary_ = Collections.emptyList();
  private List<String> systemSupportedLocale_ = Collections.emptyList();
  private int touchScreen_ = 0;

  public DeviceConfigurationProto addGlExtension(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    if (this.glExtension_.isEmpty())
      this.glExtension_ = new ArrayList();
    this.glExtension_.add(paramString);
    return this;
  }

  public DeviceConfigurationProto addNativePlatform(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    if (this.nativePlatform_.isEmpty())
      this.nativePlatform_ = new ArrayList();
    this.nativePlatform_.add(paramString);
    return this;
  }

  public DeviceConfigurationProto addSystemAvailableFeature(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    if (this.systemAvailableFeature_.isEmpty())
      this.systemAvailableFeature_ = new ArrayList();
    this.systemAvailableFeature_.add(paramString);
    return this;
  }

  public DeviceConfigurationProto addSystemSharedLibrary(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    if (this.systemSharedLibrary_.isEmpty())
      this.systemSharedLibrary_ = new ArrayList();
    this.systemSharedLibrary_.add(paramString);
    return this;
  }

  public DeviceConfigurationProto addSystemSupportedLocale(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException();
    if (this.systemSupportedLocale_.isEmpty())
      this.systemSupportedLocale_ = new ArrayList();
    this.systemSupportedLocale_.add(paramString);
    return this;
  }

  public int getCachedSize()
  {
    if (this.cachedSize < 0)
      getSerializedSize();
    return this.cachedSize;
  }

  public int getGlEsVersion()
  {
    return this.glEsVersion_;
  }

  public List<String> getGlExtensionList()
  {
    return this.glExtension_;
  }

  public boolean getHasFiveWayNavigation()
  {
    return this.hasFiveWayNavigation_;
  }

  public boolean getHasHardKeyboard()
  {
    return this.hasHardKeyboard_;
  }

  public int getKeyboard()
  {
    return this.keyboard_;
  }

  public int getMaxApkDownloadSizeMb()
  {
    return this.maxApkDownloadSizeMb_;
  }

  public List<String> getNativePlatformList()
  {
    return this.nativePlatform_;
  }

  public int getNavigation()
  {
    return this.navigation_;
  }

  public int getScreenDensity()
  {
    return this.screenDensity_;
  }

  public int getScreenHeight()
  {
    return this.screenHeight_;
  }

  public int getScreenLayout()
  {
    return this.screenLayout_;
  }

  public int getScreenWidth()
  {
    return this.screenWidth_;
  }

  public int getSerializedSize()
  {
    int i = 0;
    if (hasTouchScreen())
      i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getTouchScreen());
    if (hasKeyboard())
      i += CodedOutputStreamMicro.computeInt32Size(2, getKeyboard());
    if (hasNavigation())
      i += CodedOutputStreamMicro.computeInt32Size(3, getNavigation());
    if (hasScreenLayout())
      i += CodedOutputStreamMicro.computeInt32Size(4, getScreenLayout());
    if (hasHasHardKeyboard())
      i += CodedOutputStreamMicro.computeBoolSize(5, getHasHardKeyboard());
    if (hasHasFiveWayNavigation())
      i += CodedOutputStreamMicro.computeBoolSize(6, getHasFiveWayNavigation());
    if (hasScreenDensity())
      i += CodedOutputStreamMicro.computeInt32Size(7, getScreenDensity());
    if (hasGlEsVersion())
      i += CodedOutputStreamMicro.computeInt32Size(8, getGlEsVersion());
    int j = 0;
    Iterator localIterator1 = getSystemSharedLibraryList().iterator();
    while (localIterator1.hasNext())
      j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
    int k = i + j + 1 * getSystemSharedLibraryList().size();
    int m = 0;
    Iterator localIterator2 = getSystemAvailableFeatureList().iterator();
    while (localIterator2.hasNext())
      m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
    int n = k + m + 1 * getSystemAvailableFeatureList().size();
    int i1 = 0;
    Iterator localIterator3 = getNativePlatformList().iterator();
    while (localIterator3.hasNext())
      i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator3.next());
    int i2 = n + i1 + 1 * getNativePlatformList().size();
    if (hasScreenWidth())
      i2 += CodedOutputStreamMicro.computeInt32Size(12, getScreenWidth());
    if (hasScreenHeight())
      i2 += CodedOutputStreamMicro.computeInt32Size(13, getScreenHeight());
    int i3 = 0;
    Iterator localIterator4 = getSystemSupportedLocaleList().iterator();
    while (localIterator4.hasNext())
      i3 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator4.next());
    int i4 = i2 + i3 + 1 * getSystemSupportedLocaleList().size();
    int i5 = 0;
    Iterator localIterator5 = getGlExtensionList().iterator();
    while (localIterator5.hasNext())
      i5 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator5.next());
    int i6 = i4 + i5 + 1 * getGlExtensionList().size();
    if (hasMaxApkDownloadSizeMb())
      i6 += CodedOutputStreamMicro.computeInt32Size(17, getMaxApkDownloadSizeMb());
    this.cachedSize = i6;
    return i6;
  }

  public List<String> getSystemAvailableFeatureList()
  {
    return this.systemAvailableFeature_;
  }

  public List<String> getSystemSharedLibraryList()
  {
    return this.systemSharedLibrary_;
  }

  public List<String> getSystemSupportedLocaleList()
  {
    return this.systemSupportedLocale_;
  }

  public int getTouchScreen()
  {
    return this.touchScreen_;
  }

  public boolean hasGlEsVersion()
  {
    return this.hasGlEsVersion;
  }

  public boolean hasHasFiveWayNavigation()
  {
    return this.hasHasFiveWayNavigation;
  }

  public boolean hasHasHardKeyboard()
  {
    return this.hasHasHardKeyboard;
  }

  public boolean hasKeyboard()
  {
    return this.hasKeyboard;
  }

  public boolean hasMaxApkDownloadSizeMb()
  {
    return this.hasMaxApkDownloadSizeMb;
  }

  public boolean hasNavigation()
  {
    return this.hasNavigation;
  }

  public boolean hasScreenDensity()
  {
    return this.hasScreenDensity;
  }

  public boolean hasScreenHeight()
  {
    return this.hasScreenHeight;
  }

  public boolean hasScreenLayout()
  {
    return this.hasScreenLayout;
  }

  public boolean hasScreenWidth()
  {
    return this.hasScreenWidth;
  }

  public boolean hasTouchScreen()
  {
    return this.hasTouchScreen;
  }

  public DeviceConfigurationProto mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setTouchScreen(paramCodedInputStreamMicro.readInt32());
        break;
      case 16:
        setKeyboard(paramCodedInputStreamMicro.readInt32());
        break;
      case 24:
        setNavigation(paramCodedInputStreamMicro.readInt32());
        break;
      case 32:
        setScreenLayout(paramCodedInputStreamMicro.readInt32());
        break;
      case 40:
        setHasHardKeyboard(paramCodedInputStreamMicro.readBool());
        break;
      case 48:
        setHasFiveWayNavigation(paramCodedInputStreamMicro.readBool());
        break;
      case 56:
        setScreenDensity(paramCodedInputStreamMicro.readInt32());
        break;
      case 64:
        setGlEsVersion(paramCodedInputStreamMicro.readInt32());
        break;
      case 74:
        addSystemSharedLibrary(paramCodedInputStreamMicro.readString());
        break;
      case 82:
        addSystemAvailableFeature(paramCodedInputStreamMicro.readString());
        break;
      case 90:
        addNativePlatform(paramCodedInputStreamMicro.readString());
        break;
      case 96:
        setScreenWidth(paramCodedInputStreamMicro.readInt32());
        break;
      case 104:
        setScreenHeight(paramCodedInputStreamMicro.readInt32());
        break;
      case 114:
        addSystemSupportedLocale(paramCodedInputStreamMicro.readString());
        break;
      case 122:
        addGlExtension(paramCodedInputStreamMicro.readString());
        break;
      case 136:
      }
      setMaxApkDownloadSizeMb(paramCodedInputStreamMicro.readInt32());
    }
  }

  public DeviceConfigurationProto setGlEsVersion(int paramInt)
  {
    this.hasGlEsVersion = true;
    this.glEsVersion_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setHasFiveWayNavigation(boolean paramBoolean)
  {
    this.hasHasFiveWayNavigation = true;
    this.hasFiveWayNavigation_ = paramBoolean;
    return this;
  }

  public DeviceConfigurationProto setHasHardKeyboard(boolean paramBoolean)
  {
    this.hasHasHardKeyboard = true;
    this.hasHardKeyboard_ = paramBoolean;
    return this;
  }

  public DeviceConfigurationProto setKeyboard(int paramInt)
  {
    this.hasKeyboard = true;
    this.keyboard_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setMaxApkDownloadSizeMb(int paramInt)
  {
    this.hasMaxApkDownloadSizeMb = true;
    this.maxApkDownloadSizeMb_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setNavigation(int paramInt)
  {
    this.hasNavigation = true;
    this.navigation_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setScreenDensity(int paramInt)
  {
    this.hasScreenDensity = true;
    this.screenDensity_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setScreenHeight(int paramInt)
  {
    this.hasScreenHeight = true;
    this.screenHeight_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setScreenLayout(int paramInt)
  {
    this.hasScreenLayout = true;
    this.screenLayout_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setScreenWidth(int paramInt)
  {
    this.hasScreenWidth = true;
    this.screenWidth_ = paramInt;
    return this;
  }

  public DeviceConfigurationProto setTouchScreen(int paramInt)
  {
    this.hasTouchScreen = true;
    this.touchScreen_ = paramInt;
    return this;
  }

  public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException
  {
    if (hasTouchScreen())
      paramCodedOutputStreamMicro.writeInt32(1, getTouchScreen());
    if (hasKeyboard())
      paramCodedOutputStreamMicro.writeInt32(2, getKeyboard());
    if (hasNavigation())
      paramCodedOutputStreamMicro.writeInt32(3, getNavigation());
    if (hasScreenLayout())
      paramCodedOutputStreamMicro.writeInt32(4, getScreenLayout());
    if (hasHasHardKeyboard())
      paramCodedOutputStreamMicro.writeBool(5, getHasHardKeyboard());
    if (hasHasFiveWayNavigation())
      paramCodedOutputStreamMicro.writeBool(6, getHasFiveWayNavigation());
    if (hasScreenDensity())
      paramCodedOutputStreamMicro.writeInt32(7, getScreenDensity());
    if (hasGlEsVersion())
      paramCodedOutputStreamMicro.writeInt32(8, getGlEsVersion());
    Iterator localIterator1 = getSystemSharedLibraryList().iterator();
    while (localIterator1.hasNext())
      paramCodedOutputStreamMicro.writeString(9, (String)localIterator1.next());
    Iterator localIterator2 = getSystemAvailableFeatureList().iterator();
    while (localIterator2.hasNext())
      paramCodedOutputStreamMicro.writeString(10, (String)localIterator2.next());
    Iterator localIterator3 = getNativePlatformList().iterator();
    while (localIterator3.hasNext())
      paramCodedOutputStreamMicro.writeString(11, (String)localIterator3.next());
    if (hasScreenWidth())
      paramCodedOutputStreamMicro.writeInt32(12, getScreenWidth());
    if (hasScreenHeight())
      paramCodedOutputStreamMicro.writeInt32(13, getScreenHeight());
    Iterator localIterator4 = getSystemSupportedLocaleList().iterator();
    while (localIterator4.hasNext())
      paramCodedOutputStreamMicro.writeString(14, (String)localIterator4.next());
    Iterator localIterator5 = getGlExtensionList().iterator();
    while (localIterator5.hasNext())
      paramCodedOutputStreamMicro.writeString(15, (String)localIterator5.next());
    if (hasMaxApkDownloadSizeMb())
      paramCodedOutputStreamMicro.writeInt32(17, getMaxApkDownloadSizeMb());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DeviceConfigurationProto
 * JD-Core Version:    0.6.2
 */
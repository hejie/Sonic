package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Toc
{
  public static final class CorpusMetadata extends MessageMicro
  {
    private int backend_ = 0;
    private int cachedSize = -1;
    private boolean hasBackend;
    private boolean hasLandingUrl;
    private boolean hasLibraryName;
    private boolean hasName;
    private String landingUrl_ = "";
    private String libraryName_ = "";
    private String name_ = "";

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

    public String getLandingUrl()
    {
      return this.landingUrl_;
    }

    public String getLibraryName()
    {
      return this.libraryName_;
    }

    public String getName()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBackend())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getBackend());
      if (hasName())
        i += CodedOutputStreamMicro.computeStringSize(2, getName());
      if (hasLandingUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getLandingUrl());
      if (hasLibraryName())
        i += CodedOutputStreamMicro.computeStringSize(4, getLibraryName());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBackend()
    {
      return this.hasBackend;
    }

    public boolean hasLandingUrl()
    {
      return this.hasLandingUrl;
    }

    public boolean hasLibraryName()
    {
      return this.hasLibraryName;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public CorpusMetadata mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setLandingUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setLibraryName(paramCodedInputStreamMicro.readString());
      }
    }

    public CorpusMetadata setBackend(int paramInt)
    {
      this.hasBackend = true;
      this.backend_ = paramInt;
      return this;
    }

    public CorpusMetadata setLandingUrl(String paramString)
    {
      this.hasLandingUrl = true;
      this.landingUrl_ = paramString;
      return this;
    }

    public CorpusMetadata setLibraryName(String paramString)
    {
      this.hasLibraryName = true;
      this.libraryName_ = paramString;
      return this;
    }

    public CorpusMetadata setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBackend())
        paramCodedOutputStreamMicro.writeInt32(1, getBackend());
      if (hasName())
        paramCodedOutputStreamMicro.writeString(2, getName());
      if (hasLandingUrl())
        paramCodedOutputStreamMicro.writeString(3, getLandingUrl());
      if (hasLibraryName())
        paramCodedOutputStreamMicro.writeString(4, getLibraryName());
    }
  }

  public static final class Experiments extends MessageMicro
  {
    private int cachedSize = -1;
    private List<String> experimentId_ = Collections.emptyList();

    public Experiments addExperimentId(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.experimentId_.isEmpty())
        this.experimentId_ = new ArrayList();
      this.experimentId_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<String> getExperimentIdList()
    {
      return this.experimentId_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getExperimentIdList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int j = 0 + i + 1 * getExperimentIdList().size();
      this.cachedSize = j;
      return j;
    }

    public Experiments mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        addExperimentId(paramCodedInputStreamMicro.readString());
      }
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getExperimentIdList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(1, (String)localIterator.next());
    }
  }

  public static final class SelfUpdateConfig extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasLatestClientVersionCode;
    private int latestClientVersionCode_ = 0;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getLatestClientVersionCode()
    {
      return this.latestClientVersionCode_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLatestClientVersionCode())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getLatestClientVersionCode());
      this.cachedSize = i;
      return i;
    }

    public boolean hasLatestClientVersionCode()
    {
      return this.hasLatestClientVersionCode;
    }

    public SelfUpdateConfig mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setLatestClientVersionCode(paramCodedInputStreamMicro.readInt32());
      }
    }

    public SelfUpdateConfig setLatestClientVersionCode(int paramInt)
    {
      this.hasLatestClientVersionCode = true;
      this.latestClientVersionCode_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLatestClientVersionCode())
        paramCodedOutputStreamMicro.writeInt32(1, getLatestClientVersionCode());
    }
  }

  public static final class TocResponse extends MessageMicro
  {
    private int cachedSize = -1;
    private List<Toc.CorpusMetadata> corpus_ = Collections.emptyList();
    private Toc.Experiments experiments_ = null;
    private boolean hasExperiments;
    private boolean hasHomeUrl;
    private boolean hasIconOverrideUrl;
    private boolean hasRequiresUploadDeviceConfig;
    private boolean hasSelfUpdateConfig;
    private boolean hasTosCheckboxTextMarketingEmails;
    private boolean hasTosContent;
    private boolean hasTosToken;
    private boolean hasTosVersionDeprecated;
    private boolean hasUserSettings;
    private String homeUrl_ = "";
    private String iconOverrideUrl_ = "";
    private boolean requiresUploadDeviceConfig_ = false;
    private Toc.SelfUpdateConfig selfUpdateConfig_ = null;
    private String tosCheckboxTextMarketingEmails_ = "";
    private String tosContent_ = "";
    private String tosToken_ = "";
    private int tosVersionDeprecated_ = 0;
    private Toc.UserSettings userSettings_ = null;

    public TocResponse addCorpus(Toc.CorpusMetadata paramCorpusMetadata)
    {
      if (paramCorpusMetadata == null)
        throw new NullPointerException();
      if (this.corpus_.isEmpty())
        this.corpus_ = new ArrayList();
      this.corpus_.add(paramCorpusMetadata);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCorpusCount()
    {
      return this.corpus_.size();
    }

    public List<Toc.CorpusMetadata> getCorpusList()
    {
      return this.corpus_;
    }

    public Toc.Experiments getExperiments()
    {
      return this.experiments_;
    }

    public String getHomeUrl()
    {
      return this.homeUrl_;
    }

    public String getIconOverrideUrl()
    {
      return this.iconOverrideUrl_;
    }

    public boolean getRequiresUploadDeviceConfig()
    {
      return this.requiresUploadDeviceConfig_;
    }

    public Toc.SelfUpdateConfig getSelfUpdateConfig()
    {
      return this.selfUpdateConfig_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getCorpusList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (Toc.CorpusMetadata)localIterator.next());
      if (hasTosVersionDeprecated())
        i += CodedOutputStreamMicro.computeInt32Size(2, getTosVersionDeprecated());
      if (hasTosContent())
        i += CodedOutputStreamMicro.computeStringSize(3, getTosContent());
      if (hasHomeUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getHomeUrl());
      if (hasExperiments())
        i += CodedOutputStreamMicro.computeMessageSize(5, getExperiments());
      if (hasTosCheckboxTextMarketingEmails())
        i += CodedOutputStreamMicro.computeStringSize(6, getTosCheckboxTextMarketingEmails());
      if (hasTosToken())
        i += CodedOutputStreamMicro.computeStringSize(7, getTosToken());
      if (hasUserSettings())
        i += CodedOutputStreamMicro.computeMessageSize(8, getUserSettings());
      if (hasIconOverrideUrl())
        i += CodedOutputStreamMicro.computeStringSize(9, getIconOverrideUrl());
      if (hasSelfUpdateConfig())
        i += CodedOutputStreamMicro.computeMessageSize(10, getSelfUpdateConfig());
      if (hasRequiresUploadDeviceConfig())
        i += CodedOutputStreamMicro.computeBoolSize(11, getRequiresUploadDeviceConfig());
      this.cachedSize = i;
      return i;
    }

    public String getTosCheckboxTextMarketingEmails()
    {
      return this.tosCheckboxTextMarketingEmails_;
    }

    public String getTosContent()
    {
      return this.tosContent_;
    }

    public String getTosToken()
    {
      return this.tosToken_;
    }

    public int getTosVersionDeprecated()
    {
      return this.tosVersionDeprecated_;
    }

    public Toc.UserSettings getUserSettings()
    {
      return this.userSettings_;
    }

    public boolean hasExperiments()
    {
      return this.hasExperiments;
    }

    public boolean hasHomeUrl()
    {
      return this.hasHomeUrl;
    }

    public boolean hasIconOverrideUrl()
    {
      return this.hasIconOverrideUrl;
    }

    public boolean hasRequiresUploadDeviceConfig()
    {
      return this.hasRequiresUploadDeviceConfig;
    }

    public boolean hasSelfUpdateConfig()
    {
      return this.hasSelfUpdateConfig;
    }

    public boolean hasTosCheckboxTextMarketingEmails()
    {
      return this.hasTosCheckboxTextMarketingEmails;
    }

    public boolean hasTosContent()
    {
      return this.hasTosContent;
    }

    public boolean hasTosToken()
    {
      return this.hasTosToken;
    }

    public boolean hasTosVersionDeprecated()
    {
      return this.hasTosVersionDeprecated;
    }

    public boolean hasUserSettings()
    {
      return this.hasUserSettings;
    }

    public TocResponse mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Toc.CorpusMetadata localCorpusMetadata = new Toc.CorpusMetadata();
          paramCodedInputStreamMicro.readMessage(localCorpusMetadata);
          addCorpus(localCorpusMetadata);
          break;
        case 16:
          setTosVersionDeprecated(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setTosContent(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setHomeUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          Toc.Experiments localExperiments = new Toc.Experiments();
          paramCodedInputStreamMicro.readMessage(localExperiments);
          setExperiments(localExperiments);
          break;
        case 50:
          setTosCheckboxTextMarketingEmails(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setTosToken(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          Toc.UserSettings localUserSettings = new Toc.UserSettings();
          paramCodedInputStreamMicro.readMessage(localUserSettings);
          setUserSettings(localUserSettings);
          break;
        case 74:
          setIconOverrideUrl(paramCodedInputStreamMicro.readString());
          break;
        case 82:
          Toc.SelfUpdateConfig localSelfUpdateConfig = new Toc.SelfUpdateConfig();
          paramCodedInputStreamMicro.readMessage(localSelfUpdateConfig);
          setSelfUpdateConfig(localSelfUpdateConfig);
          break;
        case 88:
        }
        setRequiresUploadDeviceConfig(paramCodedInputStreamMicro.readBool());
      }
    }

    public TocResponse setExperiments(Toc.Experiments paramExperiments)
    {
      if (paramExperiments == null)
        throw new NullPointerException();
      this.hasExperiments = true;
      this.experiments_ = paramExperiments;
      return this;
    }

    public TocResponse setHomeUrl(String paramString)
    {
      this.hasHomeUrl = true;
      this.homeUrl_ = paramString;
      return this;
    }

    public TocResponse setIconOverrideUrl(String paramString)
    {
      this.hasIconOverrideUrl = true;
      this.iconOverrideUrl_ = paramString;
      return this;
    }

    public TocResponse setRequiresUploadDeviceConfig(boolean paramBoolean)
    {
      this.hasRequiresUploadDeviceConfig = true;
      this.requiresUploadDeviceConfig_ = paramBoolean;
      return this;
    }

    public TocResponse setSelfUpdateConfig(Toc.SelfUpdateConfig paramSelfUpdateConfig)
    {
      if (paramSelfUpdateConfig == null)
        throw new NullPointerException();
      this.hasSelfUpdateConfig = true;
      this.selfUpdateConfig_ = paramSelfUpdateConfig;
      return this;
    }

    public TocResponse setTosCheckboxTextMarketingEmails(String paramString)
    {
      this.hasTosCheckboxTextMarketingEmails = true;
      this.tosCheckboxTextMarketingEmails_ = paramString;
      return this;
    }

    public TocResponse setTosContent(String paramString)
    {
      this.hasTosContent = true;
      this.tosContent_ = paramString;
      return this;
    }

    public TocResponse setTosToken(String paramString)
    {
      this.hasTosToken = true;
      this.tosToken_ = paramString;
      return this;
    }

    public TocResponse setTosVersionDeprecated(int paramInt)
    {
      this.hasTosVersionDeprecated = true;
      this.tosVersionDeprecated_ = paramInt;
      return this;
    }

    public TocResponse setUserSettings(Toc.UserSettings paramUserSettings)
    {
      if (paramUserSettings == null)
        throw new NullPointerException();
      this.hasUserSettings = true;
      this.userSettings_ = paramUserSettings;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getCorpusList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (Toc.CorpusMetadata)localIterator.next());
      if (hasTosVersionDeprecated())
        paramCodedOutputStreamMicro.writeInt32(2, getTosVersionDeprecated());
      if (hasTosContent())
        paramCodedOutputStreamMicro.writeString(3, getTosContent());
      if (hasHomeUrl())
        paramCodedOutputStreamMicro.writeString(4, getHomeUrl());
      if (hasExperiments())
        paramCodedOutputStreamMicro.writeMessage(5, getExperiments());
      if (hasTosCheckboxTextMarketingEmails())
        paramCodedOutputStreamMicro.writeString(6, getTosCheckboxTextMarketingEmails());
      if (hasTosToken())
        paramCodedOutputStreamMicro.writeString(7, getTosToken());
      if (hasUserSettings())
        paramCodedOutputStreamMicro.writeMessage(8, getUserSettings());
      if (hasIconOverrideUrl())
        paramCodedOutputStreamMicro.writeString(9, getIconOverrideUrl());
      if (hasSelfUpdateConfig())
        paramCodedOutputStreamMicro.writeMessage(10, getSelfUpdateConfig());
      if (hasRequiresUploadDeviceConfig())
        paramCodedOutputStreamMicro.writeBool(11, getRequiresUploadDeviceConfig());
    }
  }

  public static final class UserSettings extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasTosCheckboxMarketingEmailsOptedIn;
    private boolean tosCheckboxMarketingEmailsOptedIn_ = false;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTosCheckboxMarketingEmailsOptedIn())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getTosCheckboxMarketingEmailsOptedIn());
      this.cachedSize = i;
      return i;
    }

    public boolean getTosCheckboxMarketingEmailsOptedIn()
    {
      return this.tosCheckboxMarketingEmailsOptedIn_;
    }

    public boolean hasTosCheckboxMarketingEmailsOptedIn()
    {
      return this.hasTosCheckboxMarketingEmailsOptedIn;
    }

    public UserSettings mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setTosCheckboxMarketingEmailsOptedIn(paramCodedInputStreamMicro.readBool());
      }
    }

    public UserSettings setTosCheckboxMarketingEmailsOptedIn(boolean paramBoolean)
    {
      this.hasTosCheckboxMarketingEmailsOptedIn = true;
      this.tosCheckboxMarketingEmailsOptedIn_ = paramBoolean;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasTosCheckboxMarketingEmailsOptedIn())
        paramCodedOutputStreamMicro.writeBool(1, getTosCheckboxMarketingEmailsOptedIn());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.Toc
 * JD-Core Version:    0.6.2
 */
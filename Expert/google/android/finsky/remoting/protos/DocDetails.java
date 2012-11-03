package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DocDetails
{
  public static final class AlbumDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private DocDetails.MusicDetails details_ = null;
    private DocDetails.ArtistDetails displayArtist_ = null;
    private boolean hasDetails;
    private boolean hasDisplayArtist;
    private boolean hasName;
    private String name_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocDetails.MusicDetails getDetails()
    {
      return this.details_;
    }

    public DocDetails.ArtistDetails getDisplayArtist()
    {
      return this.displayArtist_;
    }

    public String getName()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasDetails())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDetails());
      if (hasDisplayArtist())
        i += CodedOutputStreamMicro.computeMessageSize(3, getDisplayArtist());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDetails()
    {
      return this.hasDetails;
    }

    public boolean hasDisplayArtist()
    {
      return this.hasDisplayArtist;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public AlbumDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocDetails.MusicDetails localMusicDetails = new DocDetails.MusicDetails();
          paramCodedInputStreamMicro.readMessage(localMusicDetails);
          setDetails(localMusicDetails);
          break;
        case 26:
        }
        DocDetails.ArtistDetails localArtistDetails = new DocDetails.ArtistDetails();
        paramCodedInputStreamMicro.readMessage(localArtistDetails);
        setDisplayArtist(localArtistDetails);
      }
    }

    public AlbumDetails setDetails(DocDetails.MusicDetails paramMusicDetails)
    {
      if (paramMusicDetails == null)
        throw new NullPointerException();
      this.hasDetails = true;
      this.details_ = paramMusicDetails;
      return this;
    }

    public AlbumDetails setDisplayArtist(DocDetails.ArtistDetails paramArtistDetails)
    {
      if (paramArtistDetails == null)
        throw new NullPointerException();
      this.hasDisplayArtist = true;
      this.displayArtist_ = paramArtistDetails;
      return this;
    }

    public AlbumDetails setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasDetails())
        paramCodedOutputStreamMicro.writeMessage(2, getDetails());
      if (hasDisplayArtist())
        paramCodedOutputStreamMicro.writeMessage(3, getDisplayArtist());
    }
  }

  public static final class AppDetails extends MessageMicro
  {
    private List<String> appCategory_ = Collections.emptyList();
    private String appType_ = "";
    private int cachedSize = -1;
    private List<String> certificateHash_ = Collections.emptyList();
    private int contentRating_ = 0;
    private String developerEmail_ = "";
    private String developerName_ = "";
    private String developerWebsite_ = "";
    private List<DocDetails.FileMetadata> file_ = Collections.emptyList();
    private boolean hasAppType;
    private boolean hasContentRating;
    private boolean hasDeveloperEmail;
    private boolean hasDeveloperName;
    private boolean hasDeveloperWebsite;
    private boolean hasInstallationSize;
    private boolean hasMajorVersionNumber;
    private boolean hasNumDownloads;
    private boolean hasPackageName;
    private boolean hasRecentChangesHtml;
    private boolean hasTitle;
    private boolean hasUploadDate;
    private boolean hasVersionCode;
    private boolean hasVersionString;
    private long installationSize_ = 0L;
    private int majorVersionNumber_ = 0;
    private String numDownloads_ = "";
    private String packageName_ = "";
    private List<String> permission_ = Collections.emptyList();
    private String recentChangesHtml_ = "";
    private String title_ = "";
    private String uploadDate_ = "";
    private int versionCode_ = 0;
    private String versionString_ = "";

    public AppDetails addAppCategory(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.appCategory_.isEmpty())
        this.appCategory_ = new ArrayList();
      this.appCategory_.add(paramString);
      return this;
    }

    public AppDetails addCertificateHash(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.certificateHash_.isEmpty())
        this.certificateHash_ = new ArrayList();
      this.certificateHash_.add(paramString);
      return this;
    }

    public AppDetails addFile(DocDetails.FileMetadata paramFileMetadata)
    {
      if (paramFileMetadata == null)
        throw new NullPointerException();
      if (this.file_.isEmpty())
        this.file_ = new ArrayList();
      this.file_.add(paramFileMetadata);
      return this;
    }

    public AppDetails addPermission(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.permission_.isEmpty())
        this.permission_ = new ArrayList();
      this.permission_.add(paramString);
      return this;
    }

    public List<String> getAppCategoryList()
    {
      return this.appCategory_;
    }

    public String getAppType()
    {
      return this.appType_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCertificateHash(int paramInt)
    {
      return (String)this.certificateHash_.get(paramInt);
    }

    public int getCertificateHashCount()
    {
      return this.certificateHash_.size();
    }

    public List<String> getCertificateHashList()
    {
      return this.certificateHash_;
    }

    public int getContentRating()
    {
      return this.contentRating_;
    }

    public String getDeveloperEmail()
    {
      return this.developerEmail_;
    }

    public String getDeveloperName()
    {
      return this.developerName_;
    }

    public String getDeveloperWebsite()
    {
      return this.developerWebsite_;
    }

    public DocDetails.FileMetadata getFile(int paramInt)
    {
      return (DocDetails.FileMetadata)this.file_.get(paramInt);
    }

    public int getFileCount()
    {
      return this.file_.size();
    }

    public List<DocDetails.FileMetadata> getFileList()
    {
      return this.file_;
    }

    public long getInstallationSize()
    {
      return this.installationSize_;
    }

    public int getMajorVersionNumber()
    {
      return this.majorVersionNumber_;
    }

    public String getNumDownloads()
    {
      return this.numDownloads_;
    }

    public String getPackageName()
    {
      return this.packageName_;
    }

    public List<String> getPermissionList()
    {
      return this.permission_;
    }

    public String getRecentChangesHtml()
    {
      return this.recentChangesHtml_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDeveloperName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDeveloperName());
      if (hasMajorVersionNumber())
        i += CodedOutputStreamMicro.computeInt32Size(2, getMajorVersionNumber());
      if (hasVersionCode())
        i += CodedOutputStreamMicro.computeInt32Size(3, getVersionCode());
      if (hasVersionString())
        i += CodedOutputStreamMicro.computeStringSize(4, getVersionString());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(5, getTitle());
      int j = 0;
      Iterator localIterator1 = getAppCategoryList().iterator();
      while (localIterator1.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator1.next());
      int k = i + j + 1 * getAppCategoryList().size();
      if (hasContentRating())
        k += CodedOutputStreamMicro.computeInt32Size(8, getContentRating());
      if (hasInstallationSize())
        k += CodedOutputStreamMicro.computeInt64Size(9, getInstallationSize());
      int m = 0;
      Iterator localIterator2 = getPermissionList().iterator();
      while (localIterator2.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int n = k + m + 1 * getPermissionList().size();
      if (hasDeveloperEmail())
        n += CodedOutputStreamMicro.computeStringSize(11, getDeveloperEmail());
      if (hasDeveloperWebsite())
        n += CodedOutputStreamMicro.computeStringSize(12, getDeveloperWebsite());
      if (hasNumDownloads())
        n += CodedOutputStreamMicro.computeStringSize(13, getNumDownloads());
      if (hasPackageName())
        n += CodedOutputStreamMicro.computeStringSize(14, getPackageName());
      if (hasRecentChangesHtml())
        n += CodedOutputStreamMicro.computeStringSize(15, getRecentChangesHtml());
      if (hasUploadDate())
        n += CodedOutputStreamMicro.computeStringSize(16, getUploadDate());
      Iterator localIterator3 = getFileList().iterator();
      while (localIterator3.hasNext())
        n += CodedOutputStreamMicro.computeMessageSize(17, (DocDetails.FileMetadata)localIterator3.next());
      if (hasAppType())
        n += CodedOutputStreamMicro.computeStringSize(18, getAppType());
      int i1 = 0;
      Iterator localIterator4 = getCertificateHashList().iterator();
      while (localIterator4.hasNext())
        i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator4.next());
      int i2 = n + i1 + 2 * getCertificateHashList().size();
      this.cachedSize = i2;
      return i2;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public String getUploadDate()
    {
      return this.uploadDate_;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public String getVersionString()
    {
      return this.versionString_;
    }

    public boolean hasAppType()
    {
      return this.hasAppType;
    }

    public boolean hasContentRating()
    {
      return this.hasContentRating;
    }

    public boolean hasDeveloperEmail()
    {
      return this.hasDeveloperEmail;
    }

    public boolean hasDeveloperName()
    {
      return this.hasDeveloperName;
    }

    public boolean hasDeveloperWebsite()
    {
      return this.hasDeveloperWebsite;
    }

    public boolean hasInstallationSize()
    {
      return this.hasInstallationSize;
    }

    public boolean hasMajorVersionNumber()
    {
      return this.hasMajorVersionNumber;
    }

    public boolean hasNumDownloads()
    {
      return this.hasNumDownloads;
    }

    public boolean hasPackageName()
    {
      return this.hasPackageName;
    }

    public boolean hasRecentChangesHtml()
    {
      return this.hasRecentChangesHtml;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasUploadDate()
    {
      return this.hasUploadDate;
    }

    public boolean hasVersionCode()
    {
      return this.hasVersionCode;
    }

    public boolean hasVersionString()
    {
      return this.hasVersionString;
    }

    public AppDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDeveloperName(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setMajorVersionNumber(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setVersionCode(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
          setVersionString(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          addAppCategory(paramCodedInputStreamMicro.readString());
          break;
        case 64:
          setContentRating(paramCodedInputStreamMicro.readInt32());
          break;
        case 72:
          setInstallationSize(paramCodedInputStreamMicro.readInt64());
          break;
        case 82:
          addPermission(paramCodedInputStreamMicro.readString());
          break;
        case 90:
          setDeveloperEmail(paramCodedInputStreamMicro.readString());
          break;
        case 98:
          setDeveloperWebsite(paramCodedInputStreamMicro.readString());
          break;
        case 106:
          setNumDownloads(paramCodedInputStreamMicro.readString());
          break;
        case 114:
          setPackageName(paramCodedInputStreamMicro.readString());
          break;
        case 122:
          setRecentChangesHtml(paramCodedInputStreamMicro.readString());
          break;
        case 130:
          setUploadDate(paramCodedInputStreamMicro.readString());
          break;
        case 138:
          DocDetails.FileMetadata localFileMetadata = new DocDetails.FileMetadata();
          paramCodedInputStreamMicro.readMessage(localFileMetadata);
          addFile(localFileMetadata);
          break;
        case 146:
          setAppType(paramCodedInputStreamMicro.readString());
          break;
        case 154:
        }
        addCertificateHash(paramCodedInputStreamMicro.readString());
      }
    }

    public AppDetails setAppType(String paramString)
    {
      this.hasAppType = true;
      this.appType_ = paramString;
      return this;
    }

    public AppDetails setContentRating(int paramInt)
    {
      this.hasContentRating = true;
      this.contentRating_ = paramInt;
      return this;
    }

    public AppDetails setDeveloperEmail(String paramString)
    {
      this.hasDeveloperEmail = true;
      this.developerEmail_ = paramString;
      return this;
    }

    public AppDetails setDeveloperName(String paramString)
    {
      this.hasDeveloperName = true;
      this.developerName_ = paramString;
      return this;
    }

    public AppDetails setDeveloperWebsite(String paramString)
    {
      this.hasDeveloperWebsite = true;
      this.developerWebsite_ = paramString;
      return this;
    }

    public AppDetails setInstallationSize(long paramLong)
    {
      this.hasInstallationSize = true;
      this.installationSize_ = paramLong;
      return this;
    }

    public AppDetails setMajorVersionNumber(int paramInt)
    {
      this.hasMajorVersionNumber = true;
      this.majorVersionNumber_ = paramInt;
      return this;
    }

    public AppDetails setNumDownloads(String paramString)
    {
      this.hasNumDownloads = true;
      this.numDownloads_ = paramString;
      return this;
    }

    public AppDetails setPackageName(String paramString)
    {
      this.hasPackageName = true;
      this.packageName_ = paramString;
      return this;
    }

    public AppDetails setRecentChangesHtml(String paramString)
    {
      this.hasRecentChangesHtml = true;
      this.recentChangesHtml_ = paramString;
      return this;
    }

    public AppDetails setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public AppDetails setUploadDate(String paramString)
    {
      this.hasUploadDate = true;
      this.uploadDate_ = paramString;
      return this;
    }

    public AppDetails setVersionCode(int paramInt)
    {
      this.hasVersionCode = true;
      this.versionCode_ = paramInt;
      return this;
    }

    public AppDetails setVersionString(String paramString)
    {
      this.hasVersionString = true;
      this.versionString_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDeveloperName())
        paramCodedOutputStreamMicro.writeString(1, getDeveloperName());
      if (hasMajorVersionNumber())
        paramCodedOutputStreamMicro.writeInt32(2, getMajorVersionNumber());
      if (hasVersionCode())
        paramCodedOutputStreamMicro.writeInt32(3, getVersionCode());
      if (hasVersionString())
        paramCodedOutputStreamMicro.writeString(4, getVersionString());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(5, getTitle());
      Iterator localIterator1 = getAppCategoryList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeString(7, (String)localIterator1.next());
      if (hasContentRating())
        paramCodedOutputStreamMicro.writeInt32(8, getContentRating());
      if (hasInstallationSize())
        paramCodedOutputStreamMicro.writeInt64(9, getInstallationSize());
      Iterator localIterator2 = getPermissionList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(10, (String)localIterator2.next());
      if (hasDeveloperEmail())
        paramCodedOutputStreamMicro.writeString(11, getDeveloperEmail());
      if (hasDeveloperWebsite())
        paramCodedOutputStreamMicro.writeString(12, getDeveloperWebsite());
      if (hasNumDownloads())
        paramCodedOutputStreamMicro.writeString(13, getNumDownloads());
      if (hasPackageName())
        paramCodedOutputStreamMicro.writeString(14, getPackageName());
      if (hasRecentChangesHtml())
        paramCodedOutputStreamMicro.writeString(15, getRecentChangesHtml());
      if (hasUploadDate())
        paramCodedOutputStreamMicro.writeString(16, getUploadDate());
      Iterator localIterator3 = getFileList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(17, (DocDetails.FileMetadata)localIterator3.next());
      if (hasAppType())
        paramCodedOutputStreamMicro.writeString(18, getAppType());
      Iterator localIterator4 = getCertificateHashList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeString(19, (String)localIterator4.next());
    }
  }

  public static final class ArtistDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private String detailsUrl_ = "";
    private DocDetails.ArtistExternalLinks externalLinks_ = null;
    private boolean hasDetailsUrl;
    private boolean hasExternalLinks;
    private boolean hasName;
    private String name_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public DocDetails.ArtistExternalLinks getExternalLinks()
    {
      return this.externalLinks_;
    }

    public String getName()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getDetailsUrl());
      if (hasName())
        i += CodedOutputStreamMicro.computeStringSize(2, getName());
      if (hasExternalLinks())
        i += CodedOutputStreamMicro.computeMessageSize(3, getExternalLinks());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasExternalLinks()
    {
      return this.hasExternalLinks;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public ArtistDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setName(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        DocDetails.ArtistExternalLinks localArtistExternalLinks = new DocDetails.ArtistExternalLinks();
        paramCodedInputStreamMicro.readMessage(localArtistExternalLinks);
        setExternalLinks(localArtistExternalLinks);
      }
    }

    public ArtistDetails setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public ArtistDetails setExternalLinks(DocDetails.ArtistExternalLinks paramArtistExternalLinks)
    {
      if (paramArtistExternalLinks == null)
        throw new NullPointerException();
      this.hasExternalLinks = true;
      this.externalLinks_ = paramArtistExternalLinks;
      return this;
    }

    public ArtistDetails setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getDetailsUrl());
      if (hasName())
        paramCodedOutputStreamMicro.writeString(2, getName());
      if (hasExternalLinks())
        paramCodedOutputStreamMicro.writeMessage(3, getExternalLinks());
    }
  }

  public static final class ArtistExternalLinks extends MessageMicro
  {
    private int cachedSize = -1;
    private String googlePlusProfileUrl_ = "";
    private boolean hasGooglePlusProfileUrl;
    private boolean hasYoutubeChannelUrl;
    private List<String> websiteUrl_ = Collections.emptyList();
    private String youtubeChannelUrl_ = "";

    public ArtistExternalLinks addWebsiteUrl(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.websiteUrl_.isEmpty())
        this.websiteUrl_ = new ArrayList();
      this.websiteUrl_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getGooglePlusProfileUrl()
    {
      return this.googlePlusProfileUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator = getWebsiteUrlList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int j = 0 + i + 1 * getWebsiteUrlList().size();
      if (hasGooglePlusProfileUrl())
        j += CodedOutputStreamMicro.computeStringSize(2, getGooglePlusProfileUrl());
      if (hasYoutubeChannelUrl())
        j += CodedOutputStreamMicro.computeStringSize(3, getYoutubeChannelUrl());
      this.cachedSize = j;
      return j;
    }

    public int getWebsiteUrlCount()
    {
      return this.websiteUrl_.size();
    }

    public List<String> getWebsiteUrlList()
    {
      return this.websiteUrl_;
    }

    public String getYoutubeChannelUrl()
    {
      return this.youtubeChannelUrl_;
    }

    public boolean hasGooglePlusProfileUrl()
    {
      return this.hasGooglePlusProfileUrl;
    }

    public boolean hasYoutubeChannelUrl()
    {
      return this.hasYoutubeChannelUrl;
    }

    public ArtistExternalLinks mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          addWebsiteUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setGooglePlusProfileUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setYoutubeChannelUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public ArtistExternalLinks setGooglePlusProfileUrl(String paramString)
    {
      this.hasGooglePlusProfileUrl = true;
      this.googlePlusProfileUrl_ = paramString;
      return this;
    }

    public ArtistExternalLinks setYoutubeChannelUrl(String paramString)
    {
      this.hasYoutubeChannelUrl = true;
      this.youtubeChannelUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator = getWebsiteUrlList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(1, (String)localIterator.next());
      if (hasGooglePlusProfileUrl())
        paramCodedOutputStreamMicro.writeString(2, getGooglePlusProfileUrl());
      if (hasYoutubeChannelUrl())
        paramCodedOutputStreamMicro.writeString(3, getYoutubeChannelUrl());
    }
  }

  public static final class DocumentDetails extends MessageMicro
  {
    private DocDetails.AlbumDetails albumDetails_ = null;
    private DocDetails.AppDetails appDetails_ = null;
    private DocDetails.ArtistDetails artistDetails_ = null;
    private BookInfo.BookDetails bookDetails_ = null;
    private int cachedSize = -1;
    private boolean hasAlbumDetails;
    private boolean hasAppDetails;
    private boolean hasArtistDetails;
    private boolean hasBookDetails;
    private boolean hasMagazineDetails;
    private boolean hasSongDetails;
    private boolean hasSubscriptionDetails;
    private boolean hasTvEpisodeDetails;
    private boolean hasTvSeasonDetails;
    private boolean hasTvShowDetails;
    private boolean hasVideoDetails;
    private DocDetails.MagazineDetails magazineDetails_ = null;
    private DocDetails.SongDetails songDetails_ = null;
    private DocDetails.SubscriptionDetails subscriptionDetails_ = null;
    private DocDetails.TvEpisodeDetails tvEpisodeDetails_ = null;
    private DocDetails.TvSeasonDetails tvSeasonDetails_ = null;
    private DocDetails.TvShowDetails tvShowDetails_ = null;
    private DocDetails.VideoDetails videoDetails_ = null;

    public DocDetails.AlbumDetails getAlbumDetails()
    {
      return this.albumDetails_;
    }

    public DocDetails.AppDetails getAppDetails()
    {
      return this.appDetails_;
    }

    public DocDetails.ArtistDetails getArtistDetails()
    {
      return this.artistDetails_;
    }

    public BookInfo.BookDetails getBookDetails()
    {
      return this.bookDetails_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocDetails.MagazineDetails getMagazineDetails()
    {
      return this.magazineDetails_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasAppDetails())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getAppDetails());
      if (hasAlbumDetails())
        i += CodedOutputStreamMicro.computeMessageSize(2, getAlbumDetails());
      if (hasArtistDetails())
        i += CodedOutputStreamMicro.computeMessageSize(3, getArtistDetails());
      if (hasSongDetails())
        i += CodedOutputStreamMicro.computeMessageSize(4, getSongDetails());
      if (hasBookDetails())
        i += CodedOutputStreamMicro.computeMessageSize(5, getBookDetails());
      if (hasVideoDetails())
        i += CodedOutputStreamMicro.computeMessageSize(6, getVideoDetails());
      if (hasSubscriptionDetails())
        i += CodedOutputStreamMicro.computeMessageSize(7, getSubscriptionDetails());
      if (hasMagazineDetails())
        i += CodedOutputStreamMicro.computeMessageSize(8, getMagazineDetails());
      if (hasTvShowDetails())
        i += CodedOutputStreamMicro.computeMessageSize(9, getTvShowDetails());
      if (hasTvSeasonDetails())
        i += CodedOutputStreamMicro.computeMessageSize(10, getTvSeasonDetails());
      if (hasTvEpisodeDetails())
        i += CodedOutputStreamMicro.computeMessageSize(11, getTvEpisodeDetails());
      this.cachedSize = i;
      return i;
    }

    public DocDetails.SongDetails getSongDetails()
    {
      return this.songDetails_;
    }

    public DocDetails.SubscriptionDetails getSubscriptionDetails()
    {
      return this.subscriptionDetails_;
    }

    public DocDetails.TvEpisodeDetails getTvEpisodeDetails()
    {
      return this.tvEpisodeDetails_;
    }

    public DocDetails.TvSeasonDetails getTvSeasonDetails()
    {
      return this.tvSeasonDetails_;
    }

    public DocDetails.TvShowDetails getTvShowDetails()
    {
      return this.tvShowDetails_;
    }

    public DocDetails.VideoDetails getVideoDetails()
    {
      return this.videoDetails_;
    }

    public boolean hasAlbumDetails()
    {
      return this.hasAlbumDetails;
    }

    public boolean hasAppDetails()
    {
      return this.hasAppDetails;
    }

    public boolean hasArtistDetails()
    {
      return this.hasArtistDetails;
    }

    public boolean hasBookDetails()
    {
      return this.hasBookDetails;
    }

    public boolean hasMagazineDetails()
    {
      return this.hasMagazineDetails;
    }

    public boolean hasSongDetails()
    {
      return this.hasSongDetails;
    }

    public boolean hasSubscriptionDetails()
    {
      return this.hasSubscriptionDetails;
    }

    public boolean hasTvEpisodeDetails()
    {
      return this.hasTvEpisodeDetails;
    }

    public boolean hasTvSeasonDetails()
    {
      return this.hasTvSeasonDetails;
    }

    public boolean hasTvShowDetails()
    {
      return this.hasTvShowDetails;
    }

    public boolean hasVideoDetails()
    {
      return this.hasVideoDetails;
    }

    public DocumentDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocDetails.AppDetails localAppDetails = new DocDetails.AppDetails();
          paramCodedInputStreamMicro.readMessage(localAppDetails);
          setAppDetails(localAppDetails);
          break;
        case 18:
          DocDetails.AlbumDetails localAlbumDetails = new DocDetails.AlbumDetails();
          paramCodedInputStreamMicro.readMessage(localAlbumDetails);
          setAlbumDetails(localAlbumDetails);
          break;
        case 26:
          DocDetails.ArtistDetails localArtistDetails = new DocDetails.ArtistDetails();
          paramCodedInputStreamMicro.readMessage(localArtistDetails);
          setArtistDetails(localArtistDetails);
          break;
        case 34:
          DocDetails.SongDetails localSongDetails = new DocDetails.SongDetails();
          paramCodedInputStreamMicro.readMessage(localSongDetails);
          setSongDetails(localSongDetails);
          break;
        case 42:
          BookInfo.BookDetails localBookDetails = new BookInfo.BookDetails();
          paramCodedInputStreamMicro.readMessage(localBookDetails);
          setBookDetails(localBookDetails);
          break;
        case 50:
          DocDetails.VideoDetails localVideoDetails = new DocDetails.VideoDetails();
          paramCodedInputStreamMicro.readMessage(localVideoDetails);
          setVideoDetails(localVideoDetails);
          break;
        case 58:
          DocDetails.SubscriptionDetails localSubscriptionDetails = new DocDetails.SubscriptionDetails();
          paramCodedInputStreamMicro.readMessage(localSubscriptionDetails);
          setSubscriptionDetails(localSubscriptionDetails);
          break;
        case 66:
          DocDetails.MagazineDetails localMagazineDetails = new DocDetails.MagazineDetails();
          paramCodedInputStreamMicro.readMessage(localMagazineDetails);
          setMagazineDetails(localMagazineDetails);
          break;
        case 74:
          DocDetails.TvShowDetails localTvShowDetails = new DocDetails.TvShowDetails();
          paramCodedInputStreamMicro.readMessage(localTvShowDetails);
          setTvShowDetails(localTvShowDetails);
          break;
        case 82:
          DocDetails.TvSeasonDetails localTvSeasonDetails = new DocDetails.TvSeasonDetails();
          paramCodedInputStreamMicro.readMessage(localTvSeasonDetails);
          setTvSeasonDetails(localTvSeasonDetails);
          break;
        case 90:
        }
        DocDetails.TvEpisodeDetails localTvEpisodeDetails = new DocDetails.TvEpisodeDetails();
        paramCodedInputStreamMicro.readMessage(localTvEpisodeDetails);
        setTvEpisodeDetails(localTvEpisodeDetails);
      }
    }

    public DocumentDetails setAlbumDetails(DocDetails.AlbumDetails paramAlbumDetails)
    {
      if (paramAlbumDetails == null)
        throw new NullPointerException();
      this.hasAlbumDetails = true;
      this.albumDetails_ = paramAlbumDetails;
      return this;
    }

    public DocumentDetails setAppDetails(DocDetails.AppDetails paramAppDetails)
    {
      if (paramAppDetails == null)
        throw new NullPointerException();
      this.hasAppDetails = true;
      this.appDetails_ = paramAppDetails;
      return this;
    }

    public DocumentDetails setArtistDetails(DocDetails.ArtistDetails paramArtistDetails)
    {
      if (paramArtistDetails == null)
        throw new NullPointerException();
      this.hasArtistDetails = true;
      this.artistDetails_ = paramArtistDetails;
      return this;
    }

    public DocumentDetails setBookDetails(BookInfo.BookDetails paramBookDetails)
    {
      if (paramBookDetails == null)
        throw new NullPointerException();
      this.hasBookDetails = true;
      this.bookDetails_ = paramBookDetails;
      return this;
    }

    public DocumentDetails setMagazineDetails(DocDetails.MagazineDetails paramMagazineDetails)
    {
      if (paramMagazineDetails == null)
        throw new NullPointerException();
      this.hasMagazineDetails = true;
      this.magazineDetails_ = paramMagazineDetails;
      return this;
    }

    public DocumentDetails setSongDetails(DocDetails.SongDetails paramSongDetails)
    {
      if (paramSongDetails == null)
        throw new NullPointerException();
      this.hasSongDetails = true;
      this.songDetails_ = paramSongDetails;
      return this;
    }

    public DocumentDetails setSubscriptionDetails(DocDetails.SubscriptionDetails paramSubscriptionDetails)
    {
      if (paramSubscriptionDetails == null)
        throw new NullPointerException();
      this.hasSubscriptionDetails = true;
      this.subscriptionDetails_ = paramSubscriptionDetails;
      return this;
    }

    public DocumentDetails setTvEpisodeDetails(DocDetails.TvEpisodeDetails paramTvEpisodeDetails)
    {
      if (paramTvEpisodeDetails == null)
        throw new NullPointerException();
      this.hasTvEpisodeDetails = true;
      this.tvEpisodeDetails_ = paramTvEpisodeDetails;
      return this;
    }

    public DocumentDetails setTvSeasonDetails(DocDetails.TvSeasonDetails paramTvSeasonDetails)
    {
      if (paramTvSeasonDetails == null)
        throw new NullPointerException();
      this.hasTvSeasonDetails = true;
      this.tvSeasonDetails_ = paramTvSeasonDetails;
      return this;
    }

    public DocumentDetails setTvShowDetails(DocDetails.TvShowDetails paramTvShowDetails)
    {
      if (paramTvShowDetails == null)
        throw new NullPointerException();
      this.hasTvShowDetails = true;
      this.tvShowDetails_ = paramTvShowDetails;
      return this;
    }

    public DocumentDetails setVideoDetails(DocDetails.VideoDetails paramVideoDetails)
    {
      if (paramVideoDetails == null)
        throw new NullPointerException();
      this.hasVideoDetails = true;
      this.videoDetails_ = paramVideoDetails;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasAppDetails())
        paramCodedOutputStreamMicro.writeMessage(1, getAppDetails());
      if (hasAlbumDetails())
        paramCodedOutputStreamMicro.writeMessage(2, getAlbumDetails());
      if (hasArtistDetails())
        paramCodedOutputStreamMicro.writeMessage(3, getArtistDetails());
      if (hasSongDetails())
        paramCodedOutputStreamMicro.writeMessage(4, getSongDetails());
      if (hasBookDetails())
        paramCodedOutputStreamMicro.writeMessage(5, getBookDetails());
      if (hasVideoDetails())
        paramCodedOutputStreamMicro.writeMessage(6, getVideoDetails());
      if (hasSubscriptionDetails())
        paramCodedOutputStreamMicro.writeMessage(7, getSubscriptionDetails());
      if (hasMagazineDetails())
        paramCodedOutputStreamMicro.writeMessage(8, getMagazineDetails());
      if (hasTvShowDetails())
        paramCodedOutputStreamMicro.writeMessage(9, getTvShowDetails());
      if (hasTvSeasonDetails())
        paramCodedOutputStreamMicro.writeMessage(10, getTvSeasonDetails());
      if (hasTvEpisodeDetails())
        paramCodedOutputStreamMicro.writeMessage(11, getTvEpisodeDetails());
    }
  }

  public static final class FileMetadata extends MessageMicro
  {
    private int cachedSize = -1;
    private int fileType_ = 0;
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

    public FileMetadata mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        }
        setSize(paramCodedInputStreamMicro.readInt64());
      }
    }

    public FileMetadata setFileType(int paramInt)
    {
      this.hasFileType = true;
      this.fileType_ = paramInt;
      return this;
    }

    public FileMetadata setSize(long paramLong)
    {
      this.hasSize = true;
      this.size_ = paramLong;
      return this;
    }

    public FileMetadata setVersionCode(int paramInt)
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
    }
  }

  public static final class MagazineDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private String deliveryFrequencyDescription_ = "";
    private String deviceAvailabilityDescriptionHtml_ = "";
    private boolean hasDeliveryFrequencyDescription;
    private boolean hasDeviceAvailabilityDescriptionHtml;
    private boolean hasParentDetailsUrl;
    private boolean hasPsvDescription;
    private String parentDetailsUrl_ = "";
    private String psvDescription_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDeliveryFrequencyDescription()
    {
      return this.deliveryFrequencyDescription_;
    }

    public String getDeviceAvailabilityDescriptionHtml()
    {
      return this.deviceAvailabilityDescriptionHtml_;
    }

    public String getParentDetailsUrl()
    {
      return this.parentDetailsUrl_;
    }

    public String getPsvDescription()
    {
      return this.psvDescription_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasParentDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getParentDetailsUrl());
      if (hasDeviceAvailabilityDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(2, getDeviceAvailabilityDescriptionHtml());
      if (hasPsvDescription())
        i += CodedOutputStreamMicro.computeStringSize(3, getPsvDescription());
      if (hasDeliveryFrequencyDescription())
        i += CodedOutputStreamMicro.computeStringSize(4, getDeliveryFrequencyDescription());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDeliveryFrequencyDescription()
    {
      return this.hasDeliveryFrequencyDescription;
    }

    public boolean hasDeviceAvailabilityDescriptionHtml()
    {
      return this.hasDeviceAvailabilityDescriptionHtml;
    }

    public boolean hasParentDetailsUrl()
    {
      return this.hasParentDetailsUrl;
    }

    public boolean hasPsvDescription()
    {
      return this.hasPsvDescription;
    }

    public MagazineDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setParentDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setDeviceAvailabilityDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setPsvDescription(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setDeliveryFrequencyDescription(paramCodedInputStreamMicro.readString());
      }
    }

    public MagazineDetails setDeliveryFrequencyDescription(String paramString)
    {
      this.hasDeliveryFrequencyDescription = true;
      this.deliveryFrequencyDescription_ = paramString;
      return this;
    }

    public MagazineDetails setDeviceAvailabilityDescriptionHtml(String paramString)
    {
      this.hasDeviceAvailabilityDescriptionHtml = true;
      this.deviceAvailabilityDescriptionHtml_ = paramString;
      return this;
    }

    public MagazineDetails setParentDetailsUrl(String paramString)
    {
      this.hasParentDetailsUrl = true;
      this.parentDetailsUrl_ = paramString;
      return this;
    }

    public MagazineDetails setPsvDescription(String paramString)
    {
      this.hasPsvDescription = true;
      this.psvDescription_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasParentDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getParentDetailsUrl());
      if (hasDeviceAvailabilityDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(2, getDeviceAvailabilityDescriptionHtml());
      if (hasPsvDescription())
        paramCodedOutputStreamMicro.writeString(3, getPsvDescription());
      if (hasDeliveryFrequencyDescription())
        paramCodedOutputStreamMicro.writeString(4, getDeliveryFrequencyDescription());
    }
  }

  public static final class MusicDetails extends MessageMicro
  {
    private List<DocDetails.ArtistDetails> artist_ = Collections.emptyList();
    private int cachedSize = -1;
    private int censoring_ = 0;
    private int durationSec_ = 0;
    private List<String> genre_ = Collections.emptyList();
    private boolean hasCensoring;
    private boolean hasDurationSec;
    private boolean hasLabel;
    private boolean hasOriginalReleaseDate;
    private boolean hasReleaseDate;
    private String label_ = "";
    private String originalReleaseDate_ = "";
    private String releaseDate_ = "";
    private List<Integer> releaseType_ = Collections.emptyList();

    public MusicDetails addArtist(DocDetails.ArtistDetails paramArtistDetails)
    {
      if (paramArtistDetails == null)
        throw new NullPointerException();
      if (this.artist_.isEmpty())
        this.artist_ = new ArrayList();
      this.artist_.add(paramArtistDetails);
      return this;
    }

    public MusicDetails addGenre(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.genre_.isEmpty())
        this.genre_ = new ArrayList();
      this.genre_.add(paramString);
      return this;
    }

    public MusicDetails addReleaseType(int paramInt)
    {
      if (this.releaseType_.isEmpty())
        this.releaseType_ = new ArrayList();
      this.releaseType_.add(Integer.valueOf(paramInt));
      return this;
    }

    public List<DocDetails.ArtistDetails> getArtistList()
    {
      return this.artist_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCensoring()
    {
      return this.censoring_;
    }

    public int getDurationSec()
    {
      return this.durationSec_;
    }

    public int getGenreCount()
    {
      return this.genre_.size();
    }

    public List<String> getGenreList()
    {
      return this.genre_;
    }

    public String getLabel()
    {
      return this.label_;
    }

    public String getOriginalReleaseDate()
    {
      return this.originalReleaseDate_;
    }

    public String getReleaseDate()
    {
      return this.releaseDate_;
    }

    public int getReleaseType(int paramInt)
    {
      return ((Integer)this.releaseType_.get(paramInt)).intValue();
    }

    public int getReleaseTypeCount()
    {
      return this.releaseType_.size();
    }

    public List<Integer> getReleaseTypeList()
    {
      return this.releaseType_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCensoring())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getCensoring());
      if (hasDurationSec())
        i += CodedOutputStreamMicro.computeInt32Size(2, getDurationSec());
      if (hasOriginalReleaseDate())
        i += CodedOutputStreamMicro.computeStringSize(3, getOriginalReleaseDate());
      if (hasLabel())
        i += CodedOutputStreamMicro.computeStringSize(4, getLabel());
      Iterator localIterator1 = getArtistList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(5, (DocDetails.ArtistDetails)localIterator1.next());
      int j = 0;
      Iterator localIterator2 = getGenreList().iterator();
      while (localIterator2.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int k = i + j + 1 * getGenreList().size();
      if (hasReleaseDate())
        k += CodedOutputStreamMicro.computeStringSize(7, getReleaseDate());
      int m = 0;
      Iterator localIterator3 = getReleaseTypeList().iterator();
      while (localIterator3.hasNext())
        m += CodedOutputStreamMicro.computeInt32SizeNoTag(((Integer)localIterator3.next()).intValue());
      int n = k + m + 1 * getReleaseTypeList().size();
      this.cachedSize = n;
      return n;
    }

    public boolean hasCensoring()
    {
      return this.hasCensoring;
    }

    public boolean hasDurationSec()
    {
      return this.hasDurationSec;
    }

    public boolean hasLabel()
    {
      return this.hasLabel;
    }

    public boolean hasOriginalReleaseDate()
    {
      return this.hasOriginalReleaseDate;
    }

    public boolean hasReleaseDate()
    {
      return this.hasReleaseDate;
    }

    public MusicDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCensoring(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setDurationSec(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setOriginalReleaseDate(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setLabel(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          DocDetails.ArtistDetails localArtistDetails = new DocDetails.ArtistDetails();
          paramCodedInputStreamMicro.readMessage(localArtistDetails);
          addArtist(localArtistDetails);
          break;
        case 50:
          addGenre(paramCodedInputStreamMicro.readString());
          break;
        case 58:
          setReleaseDate(paramCodedInputStreamMicro.readString());
          break;
        case 64:
        }
        addReleaseType(paramCodedInputStreamMicro.readInt32());
      }
    }

    public MusicDetails setCensoring(int paramInt)
    {
      this.hasCensoring = true;
      this.censoring_ = paramInt;
      return this;
    }

    public MusicDetails setDurationSec(int paramInt)
    {
      this.hasDurationSec = true;
      this.durationSec_ = paramInt;
      return this;
    }

    public MusicDetails setLabel(String paramString)
    {
      this.hasLabel = true;
      this.label_ = paramString;
      return this;
    }

    public MusicDetails setOriginalReleaseDate(String paramString)
    {
      this.hasOriginalReleaseDate = true;
      this.originalReleaseDate_ = paramString;
      return this;
    }

    public MusicDetails setReleaseDate(String paramString)
    {
      this.hasReleaseDate = true;
      this.releaseDate_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCensoring())
        paramCodedOutputStreamMicro.writeInt32(1, getCensoring());
      if (hasDurationSec())
        paramCodedOutputStreamMicro.writeInt32(2, getDurationSec());
      if (hasOriginalReleaseDate())
        paramCodedOutputStreamMicro.writeString(3, getOriginalReleaseDate());
      if (hasLabel())
        paramCodedOutputStreamMicro.writeString(4, getLabel());
      Iterator localIterator1 = getArtistList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(5, (DocDetails.ArtistDetails)localIterator1.next());
      Iterator localIterator2 = getGenreList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(6, (String)localIterator2.next());
      if (hasReleaseDate())
        paramCodedOutputStreamMicro.writeString(7, getReleaseDate());
      Iterator localIterator3 = getReleaseTypeList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeInt32(8, ((Integer)localIterator3.next()).intValue());
    }
  }

  public static final class SongDetails extends MessageMicro
  {
    private String albumName_ = "";
    private int cachedSize = -1;
    private DocDetails.MusicDetails details_ = null;
    private DocDetails.ArtistDetails displayArtist_ = null;
    private boolean hasAlbumName;
    private boolean hasDetails;
    private boolean hasDisplayArtist;
    private boolean hasName;
    private boolean hasPreviewUrl;
    private boolean hasTrackNumber;
    private String name_ = "";
    private String previewUrl_ = "";
    private int trackNumber_ = 0;

    public String getAlbumName()
    {
      return this.albumName_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocDetails.MusicDetails getDetails()
    {
      return this.details_;
    }

    public DocDetails.ArtistDetails getDisplayArtist()
    {
      return this.displayArtist_;
    }

    public String getName()
    {
      return this.name_;
    }

    public String getPreviewUrl()
    {
      return this.previewUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getName());
      if (hasDetails())
        i += CodedOutputStreamMicro.computeMessageSize(2, getDetails());
      if (hasAlbumName())
        i += CodedOutputStreamMicro.computeStringSize(3, getAlbumName());
      if (hasTrackNumber())
        i += CodedOutputStreamMicro.computeInt32Size(4, getTrackNumber());
      if (hasPreviewUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getPreviewUrl());
      if (hasDisplayArtist())
        i += CodedOutputStreamMicro.computeMessageSize(6, getDisplayArtist());
      this.cachedSize = i;
      return i;
    }

    public int getTrackNumber()
    {
      return this.trackNumber_;
    }

    public boolean hasAlbumName()
    {
      return this.hasAlbumName;
    }

    public boolean hasDetails()
    {
      return this.hasDetails;
    }

    public boolean hasDisplayArtist()
    {
      return this.hasDisplayArtist;
    }

    public boolean hasName()
    {
      return this.hasName;
    }

    public boolean hasPreviewUrl()
    {
      return this.hasPreviewUrl;
    }

    public boolean hasTrackNumber()
    {
      return this.hasTrackNumber;
    }

    public SongDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocDetails.MusicDetails localMusicDetails = new DocDetails.MusicDetails();
          paramCodedInputStreamMicro.readMessage(localMusicDetails);
          setDetails(localMusicDetails);
          break;
        case 26:
          setAlbumName(paramCodedInputStreamMicro.readString());
          break;
        case 32:
          setTrackNumber(paramCodedInputStreamMicro.readInt32());
          break;
        case 42:
          setPreviewUrl(paramCodedInputStreamMicro.readString());
          break;
        case 50:
        }
        DocDetails.ArtistDetails localArtistDetails = new DocDetails.ArtistDetails();
        paramCodedInputStreamMicro.readMessage(localArtistDetails);
        setDisplayArtist(localArtistDetails);
      }
    }

    public SongDetails setAlbumName(String paramString)
    {
      this.hasAlbumName = true;
      this.albumName_ = paramString;
      return this;
    }

    public SongDetails setDetails(DocDetails.MusicDetails paramMusicDetails)
    {
      if (paramMusicDetails == null)
        throw new NullPointerException();
      this.hasDetails = true;
      this.details_ = paramMusicDetails;
      return this;
    }

    public SongDetails setDisplayArtist(DocDetails.ArtistDetails paramArtistDetails)
    {
      if (paramArtistDetails == null)
        throw new NullPointerException();
      this.hasDisplayArtist = true;
      this.displayArtist_ = paramArtistDetails;
      return this;
    }

    public SongDetails setName(String paramString)
    {
      this.hasName = true;
      this.name_ = paramString;
      return this;
    }

    public SongDetails setPreviewUrl(String paramString)
    {
      this.hasPreviewUrl = true;
      this.previewUrl_ = paramString;
      return this;
    }

    public SongDetails setTrackNumber(int paramInt)
    {
      this.hasTrackNumber = true;
      this.trackNumber_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasName())
        paramCodedOutputStreamMicro.writeString(1, getName());
      if (hasDetails())
        paramCodedOutputStreamMicro.writeMessage(2, getDetails());
      if (hasAlbumName())
        paramCodedOutputStreamMicro.writeString(3, getAlbumName());
      if (hasTrackNumber())
        paramCodedOutputStreamMicro.writeInt32(4, getTrackNumber());
      if (hasPreviewUrl())
        paramCodedOutputStreamMicro.writeString(5, getPreviewUrl());
      if (hasDisplayArtist())
        paramCodedOutputStreamMicro.writeMessage(6, getDisplayArtist());
    }
  }

  public static final class SubscriptionDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasSubscriptionPeriod;
    private int subscriptionPeriod_ = 1;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSubscriptionPeriod())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getSubscriptionPeriod());
      this.cachedSize = i;
      return i;
    }

    public int getSubscriptionPeriod()
    {
      return this.subscriptionPeriod_;
    }

    public boolean hasSubscriptionPeriod()
    {
      return this.hasSubscriptionPeriod;
    }

    public SubscriptionDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setSubscriptionPeriod(paramCodedInputStreamMicro.readInt32());
      }
    }

    public SubscriptionDetails setSubscriptionPeriod(int paramInt)
    {
      this.hasSubscriptionPeriod = true;
      this.subscriptionPeriod_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSubscriptionPeriod())
        paramCodedOutputStreamMicro.writeInt32(1, getSubscriptionPeriod());
    }
  }

  public static final class Trailer extends MessageMicro
  {
    private int cachedSize = -1;
    private String duration_ = "";
    private boolean hasDuration;
    private boolean hasThumbnailUrl;
    private boolean hasTitle;
    private boolean hasTrailerId;
    private boolean hasWatchUrl;
    private String thumbnailUrl_ = "";
    private String title_ = "";
    private String trailerId_ = "";
    private String watchUrl_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDuration()
    {
      return this.duration_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTrailerId())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTrailerId());
      if (hasTitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getTitle());
      if (hasThumbnailUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getThumbnailUrl());
      if (hasWatchUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getWatchUrl());
      if (hasDuration())
        i += CodedOutputStreamMicro.computeStringSize(5, getDuration());
      this.cachedSize = i;
      return i;
    }

    public String getThumbnailUrl()
    {
      return this.thumbnailUrl_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public String getTrailerId()
    {
      return this.trailerId_;
    }

    public String getWatchUrl()
    {
      return this.watchUrl_;
    }

    public boolean hasDuration()
    {
      return this.hasDuration;
    }

    public boolean hasThumbnailUrl()
    {
      return this.hasThumbnailUrl;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public boolean hasTrailerId()
    {
      return this.hasTrailerId;
    }

    public boolean hasWatchUrl()
    {
      return this.hasWatchUrl;
    }

    public Trailer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setTrailerId(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setTitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setThumbnailUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setWatchUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        setDuration(paramCodedInputStreamMicro.readString());
      }
    }

    public Trailer setDuration(String paramString)
    {
      this.hasDuration = true;
      this.duration_ = paramString;
      return this;
    }

    public Trailer setThumbnailUrl(String paramString)
    {
      this.hasThumbnailUrl = true;
      this.thumbnailUrl_ = paramString;
      return this;
    }

    public Trailer setTitle(String paramString)
    {
      this.hasTitle = true;
      this.title_ = paramString;
      return this;
    }

    public Trailer setTrailerId(String paramString)
    {
      this.hasTrailerId = true;
      this.trailerId_ = paramString;
      return this;
    }

    public Trailer setWatchUrl(String paramString)
    {
      this.hasWatchUrl = true;
      this.watchUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasTrailerId())
        paramCodedOutputStreamMicro.writeString(1, getTrailerId());
      if (hasTitle())
        paramCodedOutputStreamMicro.writeString(2, getTitle());
      if (hasThumbnailUrl())
        paramCodedOutputStreamMicro.writeString(3, getThumbnailUrl());
      if (hasWatchUrl())
        paramCodedOutputStreamMicro.writeString(4, getWatchUrl());
      if (hasDuration())
        paramCodedOutputStreamMicro.writeString(5, getDuration());
    }
  }

  public static final class TvEpisodeDetails extends MessageMicro
  {
    private int cachedSize = -1;
    private int episodeIndex_ = 0;
    private boolean hasEpisodeIndex;
    private boolean hasParentDetailsUrl;
    private boolean hasReleaseDate;
    private String parentDetailsUrl_ = "";
    private String releaseDate_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getEpisodeIndex()
    {
      return this.episodeIndex_;
    }

    public String getParentDetailsUrl()
    {
      return this.parentDetailsUrl_;
    }

    public String getReleaseDate()
    {
      return this.releaseDate_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasParentDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getParentDetailsUrl());
      if (hasEpisodeIndex())
        i += CodedOutputStreamMicro.computeInt32Size(2, getEpisodeIndex());
      if (hasReleaseDate())
        i += CodedOutputStreamMicro.computeStringSize(3, getReleaseDate());
      this.cachedSize = i;
      return i;
    }

    public boolean hasEpisodeIndex()
    {
      return this.hasEpisodeIndex;
    }

    public boolean hasParentDetailsUrl()
    {
      return this.hasParentDetailsUrl;
    }

    public boolean hasReleaseDate()
    {
      return this.hasReleaseDate;
    }

    public TvEpisodeDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setParentDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setEpisodeIndex(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
        }
        setReleaseDate(paramCodedInputStreamMicro.readString());
      }
    }

    public TvEpisodeDetails setEpisodeIndex(int paramInt)
    {
      this.hasEpisodeIndex = true;
      this.episodeIndex_ = paramInt;
      return this;
    }

    public TvEpisodeDetails setParentDetailsUrl(String paramString)
    {
      this.hasParentDetailsUrl = true;
      this.parentDetailsUrl_ = paramString;
      return this;
    }

    public TvEpisodeDetails setReleaseDate(String paramString)
    {
      this.hasReleaseDate = true;
      this.releaseDate_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasParentDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getParentDetailsUrl());
      if (hasEpisodeIndex())
        paramCodedOutputStreamMicro.writeInt32(2, getEpisodeIndex());
      if (hasReleaseDate())
        paramCodedOutputStreamMicro.writeString(3, getReleaseDate());
    }
  }

  public static final class TvSeasonDetails extends MessageMicro
  {
    private String broadcaster_ = "";
    private int cachedSize = -1;
    private int episodeCount_ = 0;
    private int expectedEpisodeCount_ = 0;
    private boolean hasBroadcaster;
    private boolean hasEpisodeCount;
    private boolean hasExpectedEpisodeCount;
    private boolean hasParentDetailsUrl;
    private boolean hasReleaseDate;
    private boolean hasSeasonIndex;
    private String parentDetailsUrl_ = "";
    private String releaseDate_ = "";
    private int seasonIndex_ = 0;

    public String getBroadcaster()
    {
      return this.broadcaster_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getEpisodeCount()
    {
      return this.episodeCount_;
    }

    public int getExpectedEpisodeCount()
    {
      return this.expectedEpisodeCount_;
    }

    public String getParentDetailsUrl()
    {
      return this.parentDetailsUrl_;
    }

    public String getReleaseDate()
    {
      return this.releaseDate_;
    }

    public int getSeasonIndex()
    {
      return this.seasonIndex_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasParentDetailsUrl())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getParentDetailsUrl());
      if (hasSeasonIndex())
        i += CodedOutputStreamMicro.computeInt32Size(2, getSeasonIndex());
      if (hasReleaseDate())
        i += CodedOutputStreamMicro.computeStringSize(3, getReleaseDate());
      if (hasBroadcaster())
        i += CodedOutputStreamMicro.computeStringSize(4, getBroadcaster());
      if (hasEpisodeCount())
        i += CodedOutputStreamMicro.computeInt32Size(5, getEpisodeCount());
      if (hasExpectedEpisodeCount())
        i += CodedOutputStreamMicro.computeInt32Size(6, getExpectedEpisodeCount());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBroadcaster()
    {
      return this.hasBroadcaster;
    }

    public boolean hasEpisodeCount()
    {
      return this.hasEpisodeCount;
    }

    public boolean hasExpectedEpisodeCount()
    {
      return this.hasExpectedEpisodeCount;
    }

    public boolean hasParentDetailsUrl()
    {
      return this.hasParentDetailsUrl;
    }

    public boolean hasReleaseDate()
    {
      return this.hasReleaseDate;
    }

    public boolean hasSeasonIndex()
    {
      return this.hasSeasonIndex;
    }

    public TvSeasonDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setParentDetailsUrl(paramCodedInputStreamMicro.readString());
          break;
        case 16:
          setSeasonIndex(paramCodedInputStreamMicro.readInt32());
          break;
        case 26:
          setReleaseDate(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setBroadcaster(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setEpisodeCount(paramCodedInputStreamMicro.readInt32());
          break;
        case 48:
        }
        setExpectedEpisodeCount(paramCodedInputStreamMicro.readInt32());
      }
    }

    public TvSeasonDetails setBroadcaster(String paramString)
    {
      this.hasBroadcaster = true;
      this.broadcaster_ = paramString;
      return this;
    }

    public TvSeasonDetails setEpisodeCount(int paramInt)
    {
      this.hasEpisodeCount = true;
      this.episodeCount_ = paramInt;
      return this;
    }

    public TvSeasonDetails setExpectedEpisodeCount(int paramInt)
    {
      this.hasExpectedEpisodeCount = true;
      this.expectedEpisodeCount_ = paramInt;
      return this;
    }

    public TvSeasonDetails setParentDetailsUrl(String paramString)
    {
      this.hasParentDetailsUrl = true;
      this.parentDetailsUrl_ = paramString;
      return this;
    }

    public TvSeasonDetails setReleaseDate(String paramString)
    {
      this.hasReleaseDate = true;
      this.releaseDate_ = paramString;
      return this;
    }

    public TvSeasonDetails setSeasonIndex(int paramInt)
    {
      this.hasSeasonIndex = true;
      this.seasonIndex_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasParentDetailsUrl())
        paramCodedOutputStreamMicro.writeString(1, getParentDetailsUrl());
      if (hasSeasonIndex())
        paramCodedOutputStreamMicro.writeInt32(2, getSeasonIndex());
      if (hasReleaseDate())
        paramCodedOutputStreamMicro.writeString(3, getReleaseDate());
      if (hasBroadcaster())
        paramCodedOutputStreamMicro.writeString(4, getBroadcaster());
      if (hasEpisodeCount())
        paramCodedOutputStreamMicro.writeInt32(5, getEpisodeCount());
      if (hasExpectedEpisodeCount())
        paramCodedOutputStreamMicro.writeInt32(6, getExpectedEpisodeCount());
    }
  }

  public static final class TvShowDetails extends MessageMicro
  {
    private String broadcaster_ = "";
    private int cachedSize = -1;
    private int endYear_ = 0;
    private boolean hasBroadcaster;
    private boolean hasEndYear;
    private boolean hasSeasonCount;
    private boolean hasStartYear;
    private int seasonCount_ = 0;
    private int startYear_ = 0;

    public String getBroadcaster()
    {
      return this.broadcaster_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getEndYear()
    {
      return this.endYear_;
    }

    public int getSeasonCount()
    {
      return this.seasonCount_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSeasonCount())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getSeasonCount());
      if (hasStartYear())
        i += CodedOutputStreamMicro.computeInt32Size(2, getStartYear());
      if (hasEndYear())
        i += CodedOutputStreamMicro.computeInt32Size(3, getEndYear());
      if (hasBroadcaster())
        i += CodedOutputStreamMicro.computeStringSize(4, getBroadcaster());
      this.cachedSize = i;
      return i;
    }

    public int getStartYear()
    {
      return this.startYear_;
    }

    public boolean hasBroadcaster()
    {
      return this.hasBroadcaster;
    }

    public boolean hasEndYear()
    {
      return this.hasEndYear;
    }

    public boolean hasSeasonCount()
    {
      return this.hasSeasonCount;
    }

    public boolean hasStartYear()
    {
      return this.hasStartYear;
    }

    public TvShowDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSeasonCount(paramCodedInputStreamMicro.readInt32());
          break;
        case 16:
          setStartYear(paramCodedInputStreamMicro.readInt32());
          break;
        case 24:
          setEndYear(paramCodedInputStreamMicro.readInt32());
          break;
        case 34:
        }
        setBroadcaster(paramCodedInputStreamMicro.readString());
      }
    }

    public TvShowDetails setBroadcaster(String paramString)
    {
      this.hasBroadcaster = true;
      this.broadcaster_ = paramString;
      return this;
    }

    public TvShowDetails setEndYear(int paramInt)
    {
      this.hasEndYear = true;
      this.endYear_ = paramInt;
      return this;
    }

    public TvShowDetails setSeasonCount(int paramInt)
    {
      this.hasSeasonCount = true;
      this.seasonCount_ = paramInt;
      return this;
    }

    public TvShowDetails setStartYear(int paramInt)
    {
      this.hasStartYear = true;
      this.startYear_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSeasonCount())
        paramCodedOutputStreamMicro.writeInt32(1, getSeasonCount());
      if (hasStartYear())
        paramCodedOutputStreamMicro.writeInt32(2, getStartYear());
      if (hasEndYear())
        paramCodedOutputStreamMicro.writeInt32(3, getEndYear());
      if (hasBroadcaster())
        paramCodedOutputStreamMicro.writeString(4, getBroadcaster());
    }
  }

  public static final class VideoCredit extends MessageMicro
  {
    private int cachedSize = -1;
    private int creditType_ = 0;
    private String credit_ = "";
    private boolean hasCredit;
    private boolean hasCreditType;
    private List<String> name_ = Collections.emptyList();

    public VideoCredit addName(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.name_.isEmpty())
        this.name_ = new ArrayList();
      this.name_.add(paramString);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getCredit()
    {
      return this.credit_;
    }

    public int getCreditType()
    {
      return this.creditType_;
    }

    public List<String> getNameList()
    {
      return this.name_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasCreditType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getCreditType());
      if (hasCredit())
        i += CodedOutputStreamMicro.computeStringSize(2, getCredit());
      int j = 0;
      Iterator localIterator = getNameList().iterator();
      while (localIterator.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
      int k = i + j + 1 * getNameList().size();
      this.cachedSize = k;
      return k;
    }

    public boolean hasCredit()
    {
      return this.hasCredit;
    }

    public boolean hasCreditType()
    {
      return this.hasCreditType;
    }

    public VideoCredit mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setCreditType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setCredit(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        addName(paramCodedInputStreamMicro.readString());
      }
    }

    public VideoCredit setCredit(String paramString)
    {
      this.hasCredit = true;
      this.credit_ = paramString;
      return this;
    }

    public VideoCredit setCreditType(int paramInt)
    {
      this.hasCreditType = true;
      this.creditType_ = paramInt;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasCreditType())
        paramCodedOutputStreamMicro.writeInt32(1, getCreditType());
      if (hasCredit())
        paramCodedOutputStreamMicro.writeString(2, getCredit());
      Iterator localIterator = getNameList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeString(3, (String)localIterator.next());
    }
  }

  public static final class VideoDetails extends MessageMicro
  {
    private List<String> audioLanguage_ = Collections.emptyList();
    private int cachedSize = -1;
    private List<String> captionLanguage_ = Collections.emptyList();
    private String contentRating_ = "";
    private List<DocDetails.VideoCredit> credit_ = Collections.emptyList();
    private long dislikes_ = 0L;
    private String duration_ = "";
    private List<String> genre_ = Collections.emptyList();
    private boolean hasContentRating;
    private boolean hasDislikes;
    private boolean hasDuration;
    private boolean hasLikes;
    private boolean hasReleaseDate;
    private long likes_ = 0L;
    private String releaseDate_ = "";
    private List<DocDetails.VideoRentalTerm> rentalTerm_ = Collections.emptyList();
    private List<DocDetails.Trailer> trailer_ = Collections.emptyList();

    public VideoDetails addAudioLanguage(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.audioLanguage_.isEmpty())
        this.audioLanguage_ = new ArrayList();
      this.audioLanguage_.add(paramString);
      return this;
    }

    public VideoDetails addCaptionLanguage(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.captionLanguage_.isEmpty())
        this.captionLanguage_ = new ArrayList();
      this.captionLanguage_.add(paramString);
      return this;
    }

    public VideoDetails addCredit(DocDetails.VideoCredit paramVideoCredit)
    {
      if (paramVideoCredit == null)
        throw new NullPointerException();
      if (this.credit_.isEmpty())
        this.credit_ = new ArrayList();
      this.credit_.add(paramVideoCredit);
      return this;
    }

    public VideoDetails addGenre(String paramString)
    {
      if (paramString == null)
        throw new NullPointerException();
      if (this.genre_.isEmpty())
        this.genre_ = new ArrayList();
      this.genre_.add(paramString);
      return this;
    }

    public VideoDetails addRentalTerm(DocDetails.VideoRentalTerm paramVideoRentalTerm)
    {
      if (paramVideoRentalTerm == null)
        throw new NullPointerException();
      if (this.rentalTerm_.isEmpty())
        this.rentalTerm_ = new ArrayList();
      this.rentalTerm_.add(paramVideoRentalTerm);
      return this;
    }

    public VideoDetails addTrailer(DocDetails.Trailer paramTrailer)
    {
      if (paramTrailer == null)
        throw new NullPointerException();
      if (this.trailer_.isEmpty())
        this.trailer_ = new ArrayList();
      this.trailer_.add(paramTrailer);
      return this;
    }

    public int getAudioLanguageCount()
    {
      return this.audioLanguage_.size();
    }

    public List<String> getAudioLanguageList()
    {
      return this.audioLanguage_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCaptionLanguageCount()
    {
      return this.captionLanguage_.size();
    }

    public List<String> getCaptionLanguageList()
    {
      return this.captionLanguage_;
    }

    public String getContentRating()
    {
      return this.contentRating_;
    }

    public List<DocDetails.VideoCredit> getCreditList()
    {
      return this.credit_;
    }

    public long getDislikes()
    {
      return this.dislikes_;
    }

    public String getDuration()
    {
      return this.duration_;
    }

    public List<String> getGenreList()
    {
      return this.genre_;
    }

    public long getLikes()
    {
      return this.likes_;
    }

    public String getReleaseDate()
    {
      return this.releaseDate_;
    }

    public List<DocDetails.VideoRentalTerm> getRentalTermList()
    {
      return this.rentalTerm_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      Iterator localIterator1 = getCreditList().iterator();
      while (localIterator1.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(1, (DocDetails.VideoCredit)localIterator1.next());
      if (hasDuration())
        i += CodedOutputStreamMicro.computeStringSize(2, getDuration());
      if (hasReleaseDate())
        i += CodedOutputStreamMicro.computeStringSize(3, getReleaseDate());
      if (hasContentRating())
        i += CodedOutputStreamMicro.computeStringSize(4, getContentRating());
      if (hasLikes())
        i += CodedOutputStreamMicro.computeInt64Size(5, getLikes());
      if (hasDislikes())
        i += CodedOutputStreamMicro.computeInt64Size(6, getDislikes());
      int j = 0;
      Iterator localIterator2 = getGenreList().iterator();
      while (localIterator2.hasNext())
        j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator2.next());
      int k = i + j + 1 * getGenreList().size();
      Iterator localIterator3 = getTrailerList().iterator();
      while (localIterator3.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(8, (DocDetails.Trailer)localIterator3.next());
      Iterator localIterator4 = getRentalTermList().iterator();
      while (localIterator4.hasNext())
        k += CodedOutputStreamMicro.computeMessageSize(9, (DocDetails.VideoRentalTerm)localIterator4.next());
      int m = 0;
      Iterator localIterator5 = getAudioLanguageList().iterator();
      while (localIterator5.hasNext())
        m += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator5.next());
      int n = k + m + 1 * getAudioLanguageList().size();
      int i1 = 0;
      Iterator localIterator6 = getCaptionLanguageList().iterator();
      while (localIterator6.hasNext())
        i1 += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator6.next());
      int i2 = n + i1 + 1 * getCaptionLanguageList().size();
      this.cachedSize = i2;
      return i2;
    }

    public List<DocDetails.Trailer> getTrailerList()
    {
      return this.trailer_;
    }

    public boolean hasContentRating()
    {
      return this.hasContentRating;
    }

    public boolean hasDislikes()
    {
      return this.hasDislikes;
    }

    public boolean hasDuration()
    {
      return this.hasDuration;
    }

    public boolean hasLikes()
    {
      return this.hasLikes;
    }

    public boolean hasReleaseDate()
    {
      return this.hasReleaseDate;
    }

    public VideoDetails mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocDetails.VideoCredit localVideoCredit = new DocDetails.VideoCredit();
          paramCodedInputStreamMicro.readMessage(localVideoCredit);
          addCredit(localVideoCredit);
          break;
        case 18:
          setDuration(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setReleaseDate(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setContentRating(paramCodedInputStreamMicro.readString());
          break;
        case 40:
          setLikes(paramCodedInputStreamMicro.readInt64());
          break;
        case 48:
          setDislikes(paramCodedInputStreamMicro.readInt64());
          break;
        case 58:
          addGenre(paramCodedInputStreamMicro.readString());
          break;
        case 66:
          DocDetails.Trailer localTrailer = new DocDetails.Trailer();
          paramCodedInputStreamMicro.readMessage(localTrailer);
          addTrailer(localTrailer);
          break;
        case 74:
          DocDetails.VideoRentalTerm localVideoRentalTerm = new DocDetails.VideoRentalTerm();
          paramCodedInputStreamMicro.readMessage(localVideoRentalTerm);
          addRentalTerm(localVideoRentalTerm);
          break;
        case 82:
          addAudioLanguage(paramCodedInputStreamMicro.readString());
          break;
        case 90:
        }
        addCaptionLanguage(paramCodedInputStreamMicro.readString());
      }
    }

    public VideoDetails setContentRating(String paramString)
    {
      this.hasContentRating = true;
      this.contentRating_ = paramString;
      return this;
    }

    public VideoDetails setDislikes(long paramLong)
    {
      this.hasDislikes = true;
      this.dislikes_ = paramLong;
      return this;
    }

    public VideoDetails setDuration(String paramString)
    {
      this.hasDuration = true;
      this.duration_ = paramString;
      return this;
    }

    public VideoDetails setLikes(long paramLong)
    {
      this.hasLikes = true;
      this.likes_ = paramLong;
      return this;
    }

    public VideoDetails setReleaseDate(String paramString)
    {
      this.hasReleaseDate = true;
      this.releaseDate_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      Iterator localIterator1 = getCreditList().iterator();
      while (localIterator1.hasNext())
        paramCodedOutputStreamMicro.writeMessage(1, (DocDetails.VideoCredit)localIterator1.next());
      if (hasDuration())
        paramCodedOutputStreamMicro.writeString(2, getDuration());
      if (hasReleaseDate())
        paramCodedOutputStreamMicro.writeString(3, getReleaseDate());
      if (hasContentRating())
        paramCodedOutputStreamMicro.writeString(4, getContentRating());
      if (hasLikes())
        paramCodedOutputStreamMicro.writeInt64(5, getLikes());
      if (hasDislikes())
        paramCodedOutputStreamMicro.writeInt64(6, getDislikes());
      Iterator localIterator2 = getGenreList().iterator();
      while (localIterator2.hasNext())
        paramCodedOutputStreamMicro.writeString(7, (String)localIterator2.next());
      Iterator localIterator3 = getTrailerList().iterator();
      while (localIterator3.hasNext())
        paramCodedOutputStreamMicro.writeMessage(8, (DocDetails.Trailer)localIterator3.next());
      Iterator localIterator4 = getRentalTermList().iterator();
      while (localIterator4.hasNext())
        paramCodedOutputStreamMicro.writeMessage(9, (DocDetails.VideoRentalTerm)localIterator4.next());
      Iterator localIterator5 = getAudioLanguageList().iterator();
      while (localIterator5.hasNext())
        paramCodedOutputStreamMicro.writeString(10, (String)localIterator5.next());
      Iterator localIterator6 = getCaptionLanguageList().iterator();
      while (localIterator6.hasNext())
        paramCodedOutputStreamMicro.writeString(11, (String)localIterator6.next());
    }
  }

  public static final class VideoRentalTerm extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasOfferAbbreviation;
    private boolean hasOfferType;
    private boolean hasRentalHeader;
    private String offerAbbreviation_ = "";
    private int offerType_ = 1;
    private String rentalHeader_ = "";
    private List<Term> term_ = Collections.emptyList();

    public VideoRentalTerm addTerm(Term paramTerm)
    {
      if (paramTerm == null)
        throw new NullPointerException();
      if (this.term_.isEmpty())
        this.term_ = new ArrayList();
      this.term_.add(paramTerm);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getOfferAbbreviation()
    {
      return this.offerAbbreviation_;
    }

    public int getOfferType()
    {
      return this.offerType_;
    }

    public String getRentalHeader()
    {
      return this.rentalHeader_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasOfferType())
        i = 0 + CodedOutputStreamMicro.computeInt32Size(1, getOfferType());
      if (hasOfferAbbreviation())
        i += CodedOutputStreamMicro.computeStringSize(2, getOfferAbbreviation());
      if (hasRentalHeader())
        i += CodedOutputStreamMicro.computeStringSize(3, getRentalHeader());
      Iterator localIterator = getTermList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeGroupSize(4, (Term)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public List<Term> getTermList()
    {
      return this.term_;
    }

    public boolean hasOfferAbbreviation()
    {
      return this.hasOfferAbbreviation;
    }

    public boolean hasOfferType()
    {
      return this.hasOfferType;
    }

    public boolean hasRentalHeader()
    {
      return this.hasRentalHeader;
    }

    public VideoRentalTerm mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setOfferType(paramCodedInputStreamMicro.readInt32());
          break;
        case 18:
          setOfferAbbreviation(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setRentalHeader(paramCodedInputStreamMicro.readString());
          break;
        case 35:
        }
        Term localTerm = new Term();
        paramCodedInputStreamMicro.readGroup(localTerm, 4);
        addTerm(localTerm);
      }
    }

    public VideoRentalTerm setOfferAbbreviation(String paramString)
    {
      this.hasOfferAbbreviation = true;
      this.offerAbbreviation_ = paramString;
      return this;
    }

    public VideoRentalTerm setOfferType(int paramInt)
    {
      this.hasOfferType = true;
      this.offerType_ = paramInt;
      return this;
    }

    public VideoRentalTerm setRentalHeader(String paramString)
    {
      this.hasRentalHeader = true;
      this.rentalHeader_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasOfferType())
        paramCodedOutputStreamMicro.writeInt32(1, getOfferType());
      if (hasOfferAbbreviation())
        paramCodedOutputStreamMicro.writeString(2, getOfferAbbreviation());
      if (hasRentalHeader())
        paramCodedOutputStreamMicro.writeString(3, getRentalHeader());
      Iterator localIterator = getTermList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeGroup(4, (Term)localIterator.next());
    }

    public static final class Term extends MessageMicro
    {
      private String body_ = "";
      private int cachedSize = -1;
      private boolean hasBody;
      private boolean hasHeader;
      private String header_ = "";

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

      public String getHeader()
      {
        return this.header_;
      }

      public int getSerializedSize()
      {
        int i = 0;
        if (hasHeader())
          i = 0 + CodedOutputStreamMicro.computeStringSize(5, getHeader());
        if (hasBody())
          i += CodedOutputStreamMicro.computeStringSize(6, getBody());
        this.cachedSize = i;
        return i;
      }

      public boolean hasBody()
      {
        return this.hasBody;
      }

      public boolean hasHeader()
      {
        return this.hasHeader;
      }

      public Term mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
            setHeader(paramCodedInputStreamMicro.readString());
            break;
          case 50:
          }
          setBody(paramCodedInputStreamMicro.readString());
        }
      }

      public Term setBody(String paramString)
      {
        this.hasBody = true;
        this.body_ = paramString;
        return this;
      }

      public Term setHeader(String paramString)
      {
        this.hasHeader = true;
        this.header_ = paramString;
        return this;
      }

      public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
        throws IOException
      {
        if (hasHeader())
          paramCodedOutputStreamMicro.writeString(5, getHeader());
        if (hasBody())
          paramCodedOutputStreamMicro.writeString(6, getBody());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DocDetails
 * JD-Core Version:    0.6.2
 */
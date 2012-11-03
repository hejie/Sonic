package com.google.android.finsky.remoting.protos;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DocAnnotations
{
  public static final class Badge extends MessageMicro
  {
    private String browseUrl_ = "";
    private int cachedSize = -1;
    private boolean hasBrowseUrl;
    private boolean hasTitle;
    private List<Doc.Image> image_ = Collections.emptyList();
    private String title_ = "";

    public Badge addImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      if (this.image_.isEmpty())
        this.image_ = new ArrayList();
      this.image_.add(paramImage);
      return this;
    }

    public String getBrowseUrl()
    {
      return this.browseUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public List<Doc.Image> getImageList()
    {
      return this.image_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTitle());
      Iterator localIterator = getImageList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(2, (Doc.Image)localIterator.next());
      if (hasBrowseUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getBrowseUrl());
      this.cachedSize = i;
      return i;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasBrowseUrl()
    {
      return this.hasBrowseUrl;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public Badge mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          Doc.Image localImage = new Doc.Image();
          paramCodedInputStreamMicro.readMessage(localImage);
          addImage(localImage);
          break;
        case 26:
        }
        setBrowseUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public Badge setBrowseUrl(String paramString)
    {
      this.hasBrowseUrl = true;
      this.browseUrl_ = paramString;
      return this;
    }

    public Badge setTitle(String paramString)
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
      Iterator localIterator = getImageList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(2, (Doc.Image)localIterator.next());
      if (hasBrowseUrl())
        paramCodedOutputStreamMicro.writeString(3, getBrowseUrl());
    }
  }

  public static final class ContainerWithBanner extends MessageMicro
  {
    private int cachedSize = -1;
    private String colorThemeArgb_ = "";
    private boolean hasColorThemeArgb;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getColorThemeArgb()
    {
      return this.colorThemeArgb_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasColorThemeArgb())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getColorThemeArgb());
      this.cachedSize = i;
      return i;
    }

    public boolean hasColorThemeArgb()
    {
      return this.hasColorThemeArgb;
    }

    public ContainerWithBanner mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setColorThemeArgb(paramCodedInputStreamMicro.readString());
      }
    }

    public ContainerWithBanner setColorThemeArgb(String paramString)
    {
      this.hasColorThemeArgb = true;
      this.colorThemeArgb_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasColorThemeArgb())
        paramCodedOutputStreamMicro.writeString(1, getColorThemeArgb());
    }
  }

  public static final class DealOfTheDay extends MessageMicro
  {
    private int cachedSize = -1;
    private String colorThemeArgb_ = "";
    private String featuredHeader_ = "";
    private boolean hasColorThemeArgb;
    private boolean hasFeaturedHeader;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getColorThemeArgb()
    {
      return this.colorThemeArgb_;
    }

    public String getFeaturedHeader()
    {
      return this.featuredHeader_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasFeaturedHeader())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getFeaturedHeader());
      if (hasColorThemeArgb())
        i += CodedOutputStreamMicro.computeStringSize(2, getColorThemeArgb());
      this.cachedSize = i;
      return i;
    }

    public boolean hasColorThemeArgb()
    {
      return this.hasColorThemeArgb;
    }

    public boolean hasFeaturedHeader()
    {
      return this.hasFeaturedHeader;
    }

    public DealOfTheDay mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setFeaturedHeader(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setColorThemeArgb(paramCodedInputStreamMicro.readString());
      }
    }

    public DealOfTheDay setColorThemeArgb(String paramString)
    {
      this.hasColorThemeArgb = true;
      this.colorThemeArgb_ = paramString;
      return this;
    }

    public DealOfTheDay setFeaturedHeader(String paramString)
    {
      this.hasFeaturedHeader = true;
      this.featuredHeader_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasFeaturedHeader())
        paramCodedOutputStreamMicro.writeString(1, getFeaturedHeader());
      if (hasColorThemeArgb())
        paramCodedOutputStreamMicro.writeString(2, getColorThemeArgb());
    }
  }

  public static final class EditorialSeriesContainer extends MessageMicro
  {
    private int cachedSize = -1;
    private String colorThemeArgb_ = "";
    private String episodeSubtitle_ = "";
    private String episodeTitle_ = "";
    private boolean hasColorThemeArgb;
    private boolean hasEpisodeSubtitle;
    private boolean hasEpisodeTitle;
    private boolean hasSeriesSubtitle;
    private boolean hasSeriesTitle;
    private String seriesSubtitle_ = "";
    private String seriesTitle_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getColorThemeArgb()
    {
      return this.colorThemeArgb_;
    }

    public String getEpisodeSubtitle()
    {
      return this.episodeSubtitle_;
    }

    public String getEpisodeTitle()
    {
      return this.episodeTitle_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSeriesTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSeriesTitle());
      if (hasSeriesSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getSeriesSubtitle());
      if (hasEpisodeTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getEpisodeTitle());
      if (hasEpisodeSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(4, getEpisodeSubtitle());
      if (hasColorThemeArgb())
        i += CodedOutputStreamMicro.computeStringSize(5, getColorThemeArgb());
      this.cachedSize = i;
      return i;
    }

    public String getSeriesSubtitle()
    {
      return this.seriesSubtitle_;
    }

    public String getSeriesTitle()
    {
      return this.seriesTitle_;
    }

    public boolean hasColorThemeArgb()
    {
      return this.hasColorThemeArgb;
    }

    public boolean hasEpisodeSubtitle()
    {
      return this.hasEpisodeSubtitle;
    }

    public boolean hasEpisodeTitle()
    {
      return this.hasEpisodeTitle;
    }

    public boolean hasSeriesSubtitle()
    {
      return this.hasSeriesSubtitle;
    }

    public boolean hasSeriesTitle()
    {
      return this.hasSeriesTitle;
    }

    public EditorialSeriesContainer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSeriesTitle(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setSeriesSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setEpisodeTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setEpisodeSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        setColorThemeArgb(paramCodedInputStreamMicro.readString());
      }
    }

    public EditorialSeriesContainer setColorThemeArgb(String paramString)
    {
      this.hasColorThemeArgb = true;
      this.colorThemeArgb_ = paramString;
      return this;
    }

    public EditorialSeriesContainer setEpisodeSubtitle(String paramString)
    {
      this.hasEpisodeSubtitle = true;
      this.episodeSubtitle_ = paramString;
      return this;
    }

    public EditorialSeriesContainer setEpisodeTitle(String paramString)
    {
      this.hasEpisodeTitle = true;
      this.episodeTitle_ = paramString;
      return this;
    }

    public EditorialSeriesContainer setSeriesSubtitle(String paramString)
    {
      this.hasSeriesSubtitle = true;
      this.seriesSubtitle_ = paramString;
      return this;
    }

    public EditorialSeriesContainer setSeriesTitle(String paramString)
    {
      this.hasSeriesTitle = true;
      this.seriesTitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSeriesTitle())
        paramCodedOutputStreamMicro.writeString(1, getSeriesTitle());
      if (hasSeriesSubtitle())
        paramCodedOutputStreamMicro.writeString(2, getSeriesSubtitle());
      if (hasEpisodeTitle())
        paramCodedOutputStreamMicro.writeString(3, getEpisodeTitle());
      if (hasEpisodeSubtitle())
        paramCodedOutputStreamMicro.writeString(4, getEpisodeSubtitle());
      if (hasColorThemeArgb())
        paramCodedOutputStreamMicro.writeString(5, getColorThemeArgb());
    }
  }

  public static final class Link extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasUri;
    private String uri_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasUri())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getUri());
      this.cachedSize = i;
      return i;
    }

    public String getUri()
    {
      return this.uri_;
    }

    public boolean hasUri()
    {
      return this.hasUri;
    }

    public Link mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setUri(paramCodedInputStreamMicro.readString());
      }
    }

    public Link setUri(String paramString)
    {
      this.hasUri = true;
      this.uri_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasUri())
        paramCodedOutputStreamMicro.writeString(1, getUri());
    }
  }

  public static final class PlusOneData extends MessageMicro
  {
    private int cachedSize = -1;
    private List<DocAnnotations.PlusProfile> circlesProfiles_ = Collections.emptyList();
    private long circlesTotal_ = 0L;
    private boolean hasCirclesTotal;
    private boolean hasSetByUser;
    private boolean hasTotal;
    private boolean setByUser_ = false;
    private long total_ = 0L;

    public PlusOneData addCirclesProfiles(DocAnnotations.PlusProfile paramPlusProfile)
    {
      if (paramPlusProfile == null)
        throw new NullPointerException();
      if (this.circlesProfiles_.isEmpty())
        this.circlesProfiles_ = new ArrayList();
      this.circlesProfiles_.add(paramPlusProfile);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public int getCirclesProfilesCount()
    {
      return this.circlesProfiles_.size();
    }

    public List<DocAnnotations.PlusProfile> getCirclesProfilesList()
    {
      return this.circlesProfiles_;
    }

    public long getCirclesTotal()
    {
      return this.circlesTotal_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSetByUser())
        i = 0 + CodedOutputStreamMicro.computeBoolSize(1, getSetByUser());
      if (hasTotal())
        i += CodedOutputStreamMicro.computeInt64Size(2, getTotal());
      if (hasCirclesTotal())
        i += CodedOutputStreamMicro.computeInt64Size(3, getCirclesTotal());
      Iterator localIterator = getCirclesProfilesList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(4, (DocAnnotations.PlusProfile)localIterator.next());
      this.cachedSize = i;
      return i;
    }

    public boolean getSetByUser()
    {
      return this.setByUser_;
    }

    public long getTotal()
    {
      return this.total_;
    }

    public boolean hasCirclesTotal()
    {
      return this.hasCirclesTotal;
    }

    public boolean hasSetByUser()
    {
      return this.hasSetByUser;
    }

    public boolean hasTotal()
    {
      return this.hasTotal;
    }

    public PlusOneData mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSetByUser(paramCodedInputStreamMicro.readBool());
          break;
        case 16:
          setTotal(paramCodedInputStreamMicro.readInt64());
          break;
        case 24:
          setCirclesTotal(paramCodedInputStreamMicro.readInt64());
          break;
        case 34:
        }
        DocAnnotations.PlusProfile localPlusProfile = new DocAnnotations.PlusProfile();
        paramCodedInputStreamMicro.readMessage(localPlusProfile);
        addCirclesProfiles(localPlusProfile);
      }
    }

    public PlusOneData setCirclesTotal(long paramLong)
    {
      this.hasCirclesTotal = true;
      this.circlesTotal_ = paramLong;
      return this;
    }

    public PlusOneData setSetByUser(boolean paramBoolean)
    {
      this.hasSetByUser = true;
      this.setByUser_ = paramBoolean;
      return this;
    }

    public PlusOneData setTotal(long paramLong)
    {
      this.hasTotal = true;
      this.total_ = paramLong;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSetByUser())
        paramCodedOutputStreamMicro.writeBool(1, getSetByUser());
      if (hasTotal())
        paramCodedOutputStreamMicro.writeInt64(2, getTotal());
      if (hasCirclesTotal())
        paramCodedOutputStreamMicro.writeInt64(3, getCirclesTotal());
      Iterator localIterator = getCirclesProfilesList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(4, (DocAnnotations.PlusProfile)localIterator.next());
    }
  }

  public static final class PlusProfile extends MessageMicro
  {
    private int cachedSize = -1;
    private String displayName_ = "";
    private boolean hasDisplayName;
    private boolean hasProfileImage;
    private boolean hasProfileImageUrl;
    private String profileImageUrl_ = "";
    private Doc.Image profileImage_ = null;

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

    public Doc.Image getProfileImage()
    {
      return this.profileImage_;
    }

    public String getProfileImageUrl()
    {
      return this.profileImageUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasDisplayName())
        i = 0 + CodedOutputStreamMicro.computeStringSize(2, getDisplayName());
      if (hasProfileImageUrl())
        i += CodedOutputStreamMicro.computeStringSize(4, getProfileImageUrl());
      if (hasProfileImage())
        i += CodedOutputStreamMicro.computeMessageSize(5, getProfileImage());
      this.cachedSize = i;
      return i;
    }

    public boolean hasDisplayName()
    {
      return this.hasDisplayName;
    }

    public boolean hasProfileImage()
    {
      return this.hasProfileImage;
    }

    public boolean hasProfileImageUrl()
    {
      return this.hasProfileImageUrl;
    }

    public PlusProfile mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setDisplayName(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setProfileImageUrl(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        Doc.Image localImage = new Doc.Image();
        paramCodedInputStreamMicro.readMessage(localImage);
        setProfileImage(localImage);
      }
    }

    public PlusProfile setDisplayName(String paramString)
    {
      this.hasDisplayName = true;
      this.displayName_ = paramString;
      return this;
    }

    public PlusProfile setProfileImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      this.hasProfileImage = true;
      this.profileImage_ = paramImage;
      return this;
    }

    public PlusProfile setProfileImageUrl(String paramString)
    {
      this.hasProfileImageUrl = true;
      this.profileImageUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasDisplayName())
        paramCodedOutputStreamMicro.writeString(2, getDisplayName());
      if (hasProfileImageUrl())
        paramCodedOutputStreamMicro.writeString(4, getProfileImageUrl());
      if (hasProfileImage())
        paramCodedOutputStreamMicro.writeMessage(5, getProfileImage());
    }
  }

  public static final class PromotedDoc extends MessageMicro
  {
    private int cachedSize = -1;
    private String descriptionHtml_ = "";
    private String detailsUrl_ = "";
    private boolean hasDescriptionHtml;
    private boolean hasDetailsUrl;
    private boolean hasSubtitle;
    private boolean hasTitle;
    private List<Doc.Image> image_ = Collections.emptyList();
    private String subtitle_ = "";
    private String title_ = "";

    public PromotedDoc addImage(Doc.Image paramImage)
    {
      if (paramImage == null)
        throw new NullPointerException();
      if (this.image_.isEmpty())
        this.image_ = new ArrayList();
      this.image_.add(paramImage);
      return this;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public String getDetailsUrl()
    {
      return this.detailsUrl_;
    }

    public List<Doc.Image> getImageList()
    {
      return this.image_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getTitle());
      if (hasSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getSubtitle());
      Iterator localIterator = getImageList().iterator();
      while (localIterator.hasNext())
        i += CodedOutputStreamMicro.computeMessageSize(3, (Doc.Image)localIterator.next());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(4, getDescriptionHtml());
      if (hasDetailsUrl())
        i += CodedOutputStreamMicro.computeStringSize(5, getDetailsUrl());
      this.cachedSize = i;
      return i;
    }

    public String getSubtitle()
    {
      return this.subtitle_;
    }

    public String getTitle()
    {
      return this.title_;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasDetailsUrl()
    {
      return this.hasDetailsUrl;
    }

    public boolean hasSubtitle()
    {
      return this.hasSubtitle;
    }

    public boolean hasTitle()
    {
      return this.hasTitle;
    }

    public PromotedDoc mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          Doc.Image localImage = new Doc.Image();
          paramCodedInputStreamMicro.readMessage(localImage);
          addImage(localImage);
          break;
        case 34:
          setDescriptionHtml(paramCodedInputStreamMicro.readString());
          break;
        case 42:
        }
        setDetailsUrl(paramCodedInputStreamMicro.readString());
      }
    }

    public PromotedDoc setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public PromotedDoc setDetailsUrl(String paramString)
    {
      this.hasDetailsUrl = true;
      this.detailsUrl_ = paramString;
      return this;
    }

    public PromotedDoc setSubtitle(String paramString)
    {
      this.hasSubtitle = true;
      this.subtitle_ = paramString;
      return this;
    }

    public PromotedDoc setTitle(String paramString)
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
      if (hasSubtitle())
        paramCodedOutputStreamMicro.writeString(2, getSubtitle());
      Iterator localIterator = getImageList().iterator();
      while (localIterator.hasNext())
        paramCodedOutputStreamMicro.writeMessage(3, (Doc.Image)localIterator.next());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(4, getDescriptionHtml());
      if (hasDetailsUrl())
        paramCodedOutputStreamMicro.writeString(5, getDetailsUrl());
    }
  }

  public static final class Reason extends MessageMicro
  {
    private String briefReason_ = "";
    private int cachedSize = -1;
    private String detailedReason_ = "";
    private boolean hasBriefReason;
    private boolean hasDetailedReason;
    private boolean hasUniqueId;
    private String uniqueId_ = "";

    public String getBriefReason()
    {
      return this.briefReason_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDetailedReason()
    {
      return this.detailedReason_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasBriefReason())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getBriefReason());
      if (hasDetailedReason())
        i += CodedOutputStreamMicro.computeStringSize(2, getDetailedReason());
      if (hasUniqueId())
        i += CodedOutputStreamMicro.computeStringSize(3, getUniqueId());
      this.cachedSize = i;
      return i;
    }

    public String getUniqueId()
    {
      return this.uniqueId_;
    }

    public boolean hasBriefReason()
    {
      return this.hasBriefReason;
    }

    public boolean hasDetailedReason()
    {
      return this.hasDetailedReason;
    }

    public boolean hasUniqueId()
    {
      return this.hasUniqueId;
    }

    public Reason mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setBriefReason(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setDetailedReason(paramCodedInputStreamMicro.readString());
          break;
        case 26:
        }
        setUniqueId(paramCodedInputStreamMicro.readString());
      }
    }

    public Reason setBriefReason(String paramString)
    {
      this.hasBriefReason = true;
      this.briefReason_ = paramString;
      return this;
    }

    public Reason setDetailedReason(String paramString)
    {
      this.hasDetailedReason = true;
      this.detailedReason_ = paramString;
      return this;
    }

    public Reason setUniqueId(String paramString)
    {
      this.hasUniqueId = true;
      this.uniqueId_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasBriefReason())
        paramCodedOutputStreamMicro.writeString(1, getBriefReason());
      if (hasDetailedReason())
        paramCodedOutputStreamMicro.writeString(2, getDetailedReason());
      if (hasUniqueId())
        paramCodedOutputStreamMicro.writeString(3, getUniqueId());
    }
  }

  public static final class RecommendationsContainer extends MessageMicro
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

    public RecommendationsContainer mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
    {
    }
  }

  public static final class SectionMetadata extends MessageMicro
  {
    private String browseUrl_ = "";
    private int cachedSize = -1;
    private String descriptionHtml_ = "";
    private boolean hasBrowseUrl;
    private boolean hasDescriptionHtml;
    private boolean hasHeader;
    private boolean hasListUrl;
    private String header_ = "";
    private String listUrl_ = "";

    public String getBrowseUrl()
    {
      return this.browseUrl_;
    }

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getDescriptionHtml()
    {
      return this.descriptionHtml_;
    }

    public String getHeader()
    {
      return this.header_;
    }

    public String getListUrl()
    {
      return this.listUrl_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasHeader())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getHeader());
      if (hasListUrl())
        i += CodedOutputStreamMicro.computeStringSize(2, getListUrl());
      if (hasBrowseUrl())
        i += CodedOutputStreamMicro.computeStringSize(3, getBrowseUrl());
      if (hasDescriptionHtml())
        i += CodedOutputStreamMicro.computeStringSize(4, getDescriptionHtml());
      this.cachedSize = i;
      return i;
    }

    public boolean hasBrowseUrl()
    {
      return this.hasBrowseUrl;
    }

    public boolean hasDescriptionHtml()
    {
      return this.hasDescriptionHtml;
    }

    public boolean hasHeader()
    {
      return this.hasHeader;
    }

    public boolean hasListUrl()
    {
      return this.hasListUrl;
    }

    public SectionMetadata mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setHeader(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setListUrl(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setBrowseUrl(paramCodedInputStreamMicro.readString());
          break;
        case 34:
        }
        setDescriptionHtml(paramCodedInputStreamMicro.readString());
      }
    }

    public SectionMetadata setBrowseUrl(String paramString)
    {
      this.hasBrowseUrl = true;
      this.browseUrl_ = paramString;
      return this;
    }

    public SectionMetadata setDescriptionHtml(String paramString)
    {
      this.hasDescriptionHtml = true;
      this.descriptionHtml_ = paramString;
      return this;
    }

    public SectionMetadata setHeader(String paramString)
    {
      this.hasHeader = true;
      this.header_ = paramString;
      return this;
    }

    public SectionMetadata setListUrl(String paramString)
    {
      this.hasListUrl = true;
      this.listUrl_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasHeader())
        paramCodedOutputStreamMicro.writeString(1, getHeader());
      if (hasListUrl())
        paramCodedOutputStreamMicro.writeString(2, getListUrl());
      if (hasBrowseUrl())
        paramCodedOutputStreamMicro.writeString(3, getBrowseUrl());
      if (hasDescriptionHtml())
        paramCodedOutputStreamMicro.writeString(4, getDescriptionHtml());
    }
  }

  public static final class SeriesAntenna extends MessageMicro
  {
    private int cachedSize = -1;
    private String colorThemeArgb_ = "";
    private String episodeSubtitle_ = "";
    private String episodeTitle_ = "";
    private boolean hasColorThemeArgb;
    private boolean hasEpisodeSubtitle;
    private boolean hasEpisodeTitle;
    private boolean hasSectionAlbums;
    private boolean hasSectionTracks;
    private boolean hasSeriesSubtitle;
    private boolean hasSeriesTitle;
    private DocAnnotations.SectionMetadata sectionAlbums_ = null;
    private DocAnnotations.SectionMetadata sectionTracks_ = null;
    private String seriesSubtitle_ = "";
    private String seriesTitle_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getColorThemeArgb()
    {
      return this.colorThemeArgb_;
    }

    public String getEpisodeSubtitle()
    {
      return this.episodeSubtitle_;
    }

    public String getEpisodeTitle()
    {
      return this.episodeTitle_;
    }

    public DocAnnotations.SectionMetadata getSectionAlbums()
    {
      return this.sectionAlbums_;
    }

    public DocAnnotations.SectionMetadata getSectionTracks()
    {
      return this.sectionTracks_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSeriesTitle())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getSeriesTitle());
      if (hasSeriesSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(2, getSeriesSubtitle());
      if (hasEpisodeTitle())
        i += CodedOutputStreamMicro.computeStringSize(3, getEpisodeTitle());
      if (hasEpisodeSubtitle())
        i += CodedOutputStreamMicro.computeStringSize(4, getEpisodeSubtitle());
      if (hasColorThemeArgb())
        i += CodedOutputStreamMicro.computeStringSize(5, getColorThemeArgb());
      if (hasSectionTracks())
        i += CodedOutputStreamMicro.computeMessageSize(6, getSectionTracks());
      if (hasSectionAlbums())
        i += CodedOutputStreamMicro.computeMessageSize(7, getSectionAlbums());
      this.cachedSize = i;
      return i;
    }

    public String getSeriesSubtitle()
    {
      return this.seriesSubtitle_;
    }

    public String getSeriesTitle()
    {
      return this.seriesTitle_;
    }

    public boolean hasColorThemeArgb()
    {
      return this.hasColorThemeArgb;
    }

    public boolean hasEpisodeSubtitle()
    {
      return this.hasEpisodeSubtitle;
    }

    public boolean hasEpisodeTitle()
    {
      return this.hasEpisodeTitle;
    }

    public boolean hasSectionAlbums()
    {
      return this.hasSectionAlbums;
    }

    public boolean hasSectionTracks()
    {
      return this.hasSectionTracks;
    }

    public boolean hasSeriesSubtitle()
    {
      return this.hasSeriesSubtitle;
    }

    public boolean hasSeriesTitle()
    {
      return this.hasSeriesTitle;
    }

    public SeriesAntenna mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setSeriesTitle(paramCodedInputStreamMicro.readString());
          break;
        case 18:
          setSeriesSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 26:
          setEpisodeTitle(paramCodedInputStreamMicro.readString());
          break;
        case 34:
          setEpisodeSubtitle(paramCodedInputStreamMicro.readString());
          break;
        case 42:
          setColorThemeArgb(paramCodedInputStreamMicro.readString());
          break;
        case 50:
          DocAnnotations.SectionMetadata localSectionMetadata2 = new DocAnnotations.SectionMetadata();
          paramCodedInputStreamMicro.readMessage(localSectionMetadata2);
          setSectionTracks(localSectionMetadata2);
          break;
        case 58:
        }
        DocAnnotations.SectionMetadata localSectionMetadata1 = new DocAnnotations.SectionMetadata();
        paramCodedInputStreamMicro.readMessage(localSectionMetadata1);
        setSectionAlbums(localSectionMetadata1);
      }
    }

    public SeriesAntenna setColorThemeArgb(String paramString)
    {
      this.hasColorThemeArgb = true;
      this.colorThemeArgb_ = paramString;
      return this;
    }

    public SeriesAntenna setEpisodeSubtitle(String paramString)
    {
      this.hasEpisodeSubtitle = true;
      this.episodeSubtitle_ = paramString;
      return this;
    }

    public SeriesAntenna setEpisodeTitle(String paramString)
    {
      this.hasEpisodeTitle = true;
      this.episodeTitle_ = paramString;
      return this;
    }

    public SeriesAntenna setSectionAlbums(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionAlbums = true;
      this.sectionAlbums_ = paramSectionMetadata;
      return this;
    }

    public SeriesAntenna setSectionTracks(DocAnnotations.SectionMetadata paramSectionMetadata)
    {
      if (paramSectionMetadata == null)
        throw new NullPointerException();
      this.hasSectionTracks = true;
      this.sectionTracks_ = paramSectionMetadata;
      return this;
    }

    public SeriesAntenna setSeriesSubtitle(String paramString)
    {
      this.hasSeriesSubtitle = true;
      this.seriesSubtitle_ = paramString;
      return this;
    }

    public SeriesAntenna setSeriesTitle(String paramString)
    {
      this.hasSeriesTitle = true;
      this.seriesTitle_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSeriesTitle())
        paramCodedOutputStreamMicro.writeString(1, getSeriesTitle());
      if (hasSeriesSubtitle())
        paramCodedOutputStreamMicro.writeString(2, getSeriesSubtitle());
      if (hasEpisodeTitle())
        paramCodedOutputStreamMicro.writeString(3, getEpisodeTitle());
      if (hasEpisodeSubtitle())
        paramCodedOutputStreamMicro.writeString(4, getEpisodeSubtitle());
      if (hasColorThemeArgb())
        paramCodedOutputStreamMicro.writeString(5, getColorThemeArgb());
      if (hasSectionTracks())
        paramCodedOutputStreamMicro.writeMessage(6, getSectionTracks());
      if (hasSectionAlbums())
        paramCodedOutputStreamMicro.writeMessage(7, getSectionAlbums());
    }
  }

  public static final class Template extends MessageMicro
  {
    private int cachedSize = -1;
    private DocAnnotations.ContainerWithBanner containerWithBanner_ = null;
    private DocAnnotations.DealOfTheDay dealOfTheDay_ = null;
    private DocAnnotations.EditorialSeriesContainer editorialSeriesContainer_ = null;
    private boolean hasContainerWithBanner;
    private boolean hasDealOfTheDay;
    private boolean hasEditorialSeriesContainer;
    private boolean hasRecommendationsContainer;
    private boolean hasSeriesAntenna;
    private boolean hasTileDetailsReflectedGraphic2X2;
    private boolean hasTileFourBlock4X2;
    private boolean hasTileGraphic2X1;
    private boolean hasTileGraphic4X2;
    private boolean hasTileGraphicColoredTitle2X1;
    private boolean hasTileGraphicColoredTitle4X2;
    private boolean hasTileGraphicUpperLeftTitle2X1;
    private DocAnnotations.RecommendationsContainer recommendationsContainer_ = null;
    private DocAnnotations.SeriesAntenna seriesAntenna_ = null;
    private DocAnnotations.TileTemplate tileDetailsReflectedGraphic2X2_ = null;
    private DocAnnotations.TileTemplate tileFourBlock4X2_ = null;
    private DocAnnotations.TileTemplate tileGraphic2X1_ = null;
    private DocAnnotations.TileTemplate tileGraphic4X2_ = null;
    private DocAnnotations.TileTemplate tileGraphicColoredTitle2X1_ = null;
    private DocAnnotations.TileTemplate tileGraphicColoredTitle4X2_ = null;
    private DocAnnotations.TileTemplate tileGraphicUpperLeftTitle2X1_ = null;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public DocAnnotations.ContainerWithBanner getContainerWithBanner()
    {
      return this.containerWithBanner_;
    }

    public DocAnnotations.DealOfTheDay getDealOfTheDay()
    {
      return this.dealOfTheDay_;
    }

    public DocAnnotations.EditorialSeriesContainer getEditorialSeriesContainer()
    {
      return this.editorialSeriesContainer_;
    }

    public DocAnnotations.RecommendationsContainer getRecommendationsContainer()
    {
      return this.recommendationsContainer_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasSeriesAntenna())
        i = 0 + CodedOutputStreamMicro.computeMessageSize(1, getSeriesAntenna());
      if (hasTileGraphic2X1())
        i += CodedOutputStreamMicro.computeMessageSize(2, getTileGraphic2X1());
      if (hasTileGraphic4X2())
        i += CodedOutputStreamMicro.computeMessageSize(3, getTileGraphic4X2());
      if (hasTileGraphicColoredTitle2X1())
        i += CodedOutputStreamMicro.computeMessageSize(4, getTileGraphicColoredTitle2X1());
      if (hasTileGraphicUpperLeftTitle2X1())
        i += CodedOutputStreamMicro.computeMessageSize(5, getTileGraphicUpperLeftTitle2X1());
      if (hasTileDetailsReflectedGraphic2X2())
        i += CodedOutputStreamMicro.computeMessageSize(6, getTileDetailsReflectedGraphic2X2());
      if (hasTileFourBlock4X2())
        i += CodedOutputStreamMicro.computeMessageSize(7, getTileFourBlock4X2());
      if (hasContainerWithBanner())
        i += CodedOutputStreamMicro.computeMessageSize(8, getContainerWithBanner());
      if (hasDealOfTheDay())
        i += CodedOutputStreamMicro.computeMessageSize(9, getDealOfTheDay());
      if (hasTileGraphicColoredTitle4X2())
        i += CodedOutputStreamMicro.computeMessageSize(10, getTileGraphicColoredTitle4X2());
      if (hasEditorialSeriesContainer())
        i += CodedOutputStreamMicro.computeMessageSize(11, getEditorialSeriesContainer());
      if (hasRecommendationsContainer())
        i += CodedOutputStreamMicro.computeMessageSize(12, getRecommendationsContainer());
      this.cachedSize = i;
      return i;
    }

    public DocAnnotations.SeriesAntenna getSeriesAntenna()
    {
      return this.seriesAntenna_;
    }

    public DocAnnotations.TileTemplate getTileDetailsReflectedGraphic2X2()
    {
      return this.tileDetailsReflectedGraphic2X2_;
    }

    public DocAnnotations.TileTemplate getTileFourBlock4X2()
    {
      return this.tileFourBlock4X2_;
    }

    public DocAnnotations.TileTemplate getTileGraphic2X1()
    {
      return this.tileGraphic2X1_;
    }

    public DocAnnotations.TileTemplate getTileGraphic4X2()
    {
      return this.tileGraphic4X2_;
    }

    public DocAnnotations.TileTemplate getTileGraphicColoredTitle2X1()
    {
      return this.tileGraphicColoredTitle2X1_;
    }

    public DocAnnotations.TileTemplate getTileGraphicColoredTitle4X2()
    {
      return this.tileGraphicColoredTitle4X2_;
    }

    public DocAnnotations.TileTemplate getTileGraphicUpperLeftTitle2X1()
    {
      return this.tileGraphicUpperLeftTitle2X1_;
    }

    public boolean hasContainerWithBanner()
    {
      return this.hasContainerWithBanner;
    }

    public boolean hasDealOfTheDay()
    {
      return this.hasDealOfTheDay;
    }

    public boolean hasEditorialSeriesContainer()
    {
      return this.hasEditorialSeriesContainer;
    }

    public boolean hasRecommendationsContainer()
    {
      return this.hasRecommendationsContainer;
    }

    public boolean hasSeriesAntenna()
    {
      return this.hasSeriesAntenna;
    }

    public boolean hasTileDetailsReflectedGraphic2X2()
    {
      return this.hasTileDetailsReflectedGraphic2X2;
    }

    public boolean hasTileFourBlock4X2()
    {
      return this.hasTileFourBlock4X2;
    }

    public boolean hasTileGraphic2X1()
    {
      return this.hasTileGraphic2X1;
    }

    public boolean hasTileGraphic4X2()
    {
      return this.hasTileGraphic4X2;
    }

    public boolean hasTileGraphicColoredTitle2X1()
    {
      return this.hasTileGraphicColoredTitle2X1;
    }

    public boolean hasTileGraphicColoredTitle4X2()
    {
      return this.hasTileGraphicColoredTitle4X2;
    }

    public boolean hasTileGraphicUpperLeftTitle2X1()
    {
      return this.hasTileGraphicUpperLeftTitle2X1;
    }

    public Template mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          DocAnnotations.SeriesAntenna localSeriesAntenna = new DocAnnotations.SeriesAntenna();
          paramCodedInputStreamMicro.readMessage(localSeriesAntenna);
          setSeriesAntenna(localSeriesAntenna);
          break;
        case 18:
          DocAnnotations.TileTemplate localTileTemplate7 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate7);
          setTileGraphic2X1(localTileTemplate7);
          break;
        case 26:
          DocAnnotations.TileTemplate localTileTemplate6 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate6);
          setTileGraphic4X2(localTileTemplate6);
          break;
        case 34:
          DocAnnotations.TileTemplate localTileTemplate5 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate5);
          setTileGraphicColoredTitle2X1(localTileTemplate5);
          break;
        case 42:
          DocAnnotations.TileTemplate localTileTemplate4 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate4);
          setTileGraphicUpperLeftTitle2X1(localTileTemplate4);
          break;
        case 50:
          DocAnnotations.TileTemplate localTileTemplate3 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate3);
          setTileDetailsReflectedGraphic2X2(localTileTemplate3);
          break;
        case 58:
          DocAnnotations.TileTemplate localTileTemplate2 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate2);
          setTileFourBlock4X2(localTileTemplate2);
          break;
        case 66:
          DocAnnotations.ContainerWithBanner localContainerWithBanner = new DocAnnotations.ContainerWithBanner();
          paramCodedInputStreamMicro.readMessage(localContainerWithBanner);
          setContainerWithBanner(localContainerWithBanner);
          break;
        case 74:
          DocAnnotations.DealOfTheDay localDealOfTheDay = new DocAnnotations.DealOfTheDay();
          paramCodedInputStreamMicro.readMessage(localDealOfTheDay);
          setDealOfTheDay(localDealOfTheDay);
          break;
        case 82:
          DocAnnotations.TileTemplate localTileTemplate1 = new DocAnnotations.TileTemplate();
          paramCodedInputStreamMicro.readMessage(localTileTemplate1);
          setTileGraphicColoredTitle4X2(localTileTemplate1);
          break;
        case 90:
          DocAnnotations.EditorialSeriesContainer localEditorialSeriesContainer = new DocAnnotations.EditorialSeriesContainer();
          paramCodedInputStreamMicro.readMessage(localEditorialSeriesContainer);
          setEditorialSeriesContainer(localEditorialSeriesContainer);
          break;
        case 98:
        }
        DocAnnotations.RecommendationsContainer localRecommendationsContainer = new DocAnnotations.RecommendationsContainer();
        paramCodedInputStreamMicro.readMessage(localRecommendationsContainer);
        setRecommendationsContainer(localRecommendationsContainer);
      }
    }

    public Template setContainerWithBanner(DocAnnotations.ContainerWithBanner paramContainerWithBanner)
    {
      if (paramContainerWithBanner == null)
        throw new NullPointerException();
      this.hasContainerWithBanner = true;
      this.containerWithBanner_ = paramContainerWithBanner;
      return this;
    }

    public Template setDealOfTheDay(DocAnnotations.DealOfTheDay paramDealOfTheDay)
    {
      if (paramDealOfTheDay == null)
        throw new NullPointerException();
      this.hasDealOfTheDay = true;
      this.dealOfTheDay_ = paramDealOfTheDay;
      return this;
    }

    public Template setEditorialSeriesContainer(DocAnnotations.EditorialSeriesContainer paramEditorialSeriesContainer)
    {
      if (paramEditorialSeriesContainer == null)
        throw new NullPointerException();
      this.hasEditorialSeriesContainer = true;
      this.editorialSeriesContainer_ = paramEditorialSeriesContainer;
      return this;
    }

    public Template setRecommendationsContainer(DocAnnotations.RecommendationsContainer paramRecommendationsContainer)
    {
      if (paramRecommendationsContainer == null)
        throw new NullPointerException();
      this.hasRecommendationsContainer = true;
      this.recommendationsContainer_ = paramRecommendationsContainer;
      return this;
    }

    public Template setSeriesAntenna(DocAnnotations.SeriesAntenna paramSeriesAntenna)
    {
      if (paramSeriesAntenna == null)
        throw new NullPointerException();
      this.hasSeriesAntenna = true;
      this.seriesAntenna_ = paramSeriesAntenna;
      return this;
    }

    public Template setTileDetailsReflectedGraphic2X2(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileDetailsReflectedGraphic2X2 = true;
      this.tileDetailsReflectedGraphic2X2_ = paramTileTemplate;
      return this;
    }

    public Template setTileFourBlock4X2(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileFourBlock4X2 = true;
      this.tileFourBlock4X2_ = paramTileTemplate;
      return this;
    }

    public Template setTileGraphic2X1(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileGraphic2X1 = true;
      this.tileGraphic2X1_ = paramTileTemplate;
      return this;
    }

    public Template setTileGraphic4X2(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileGraphic4X2 = true;
      this.tileGraphic4X2_ = paramTileTemplate;
      return this;
    }

    public Template setTileGraphicColoredTitle2X1(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileGraphicColoredTitle2X1 = true;
      this.tileGraphicColoredTitle2X1_ = paramTileTemplate;
      return this;
    }

    public Template setTileGraphicColoredTitle4X2(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileGraphicColoredTitle4X2 = true;
      this.tileGraphicColoredTitle4X2_ = paramTileTemplate;
      return this;
    }

    public Template setTileGraphicUpperLeftTitle2X1(DocAnnotations.TileTemplate paramTileTemplate)
    {
      if (paramTileTemplate == null)
        throw new NullPointerException();
      this.hasTileGraphicUpperLeftTitle2X1 = true;
      this.tileGraphicUpperLeftTitle2X1_ = paramTileTemplate;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasSeriesAntenna())
        paramCodedOutputStreamMicro.writeMessage(1, getSeriesAntenna());
      if (hasTileGraphic2X1())
        paramCodedOutputStreamMicro.writeMessage(2, getTileGraphic2X1());
      if (hasTileGraphic4X2())
        paramCodedOutputStreamMicro.writeMessage(3, getTileGraphic4X2());
      if (hasTileGraphicColoredTitle2X1())
        paramCodedOutputStreamMicro.writeMessage(4, getTileGraphicColoredTitle2X1());
      if (hasTileGraphicUpperLeftTitle2X1())
        paramCodedOutputStreamMicro.writeMessage(5, getTileGraphicUpperLeftTitle2X1());
      if (hasTileDetailsReflectedGraphic2X2())
        paramCodedOutputStreamMicro.writeMessage(6, getTileDetailsReflectedGraphic2X2());
      if (hasTileFourBlock4X2())
        paramCodedOutputStreamMicro.writeMessage(7, getTileFourBlock4X2());
      if (hasContainerWithBanner())
        paramCodedOutputStreamMicro.writeMessage(8, getContainerWithBanner());
      if (hasDealOfTheDay())
        paramCodedOutputStreamMicro.writeMessage(9, getDealOfTheDay());
      if (hasTileGraphicColoredTitle4X2())
        paramCodedOutputStreamMicro.writeMessage(10, getTileGraphicColoredTitle4X2());
      if (hasEditorialSeriesContainer())
        paramCodedOutputStreamMicro.writeMessage(11, getEditorialSeriesContainer());
      if (hasRecommendationsContainer())
        paramCodedOutputStreamMicro.writeMessage(12, getRecommendationsContainer());
    }
  }

  public static final class TileTemplate extends MessageMicro
  {
    private int cachedSize = -1;
    private String colorTextArgb_ = "";
    private String colorThemeArgb_ = "";
    private boolean hasColorTextArgb;
    private boolean hasColorThemeArgb;

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getColorTextArgb()
    {
      return this.colorTextArgb_;
    }

    public String getColorThemeArgb()
    {
      return this.colorThemeArgb_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasColorThemeArgb())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getColorThemeArgb());
      if (hasColorTextArgb())
        i += CodedOutputStreamMicro.computeStringSize(2, getColorTextArgb());
      this.cachedSize = i;
      return i;
    }

    public boolean hasColorTextArgb()
    {
      return this.hasColorTextArgb;
    }

    public boolean hasColorThemeArgb()
    {
      return this.hasColorThemeArgb;
    }

    public TileTemplate mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
          setColorThemeArgb(paramCodedInputStreamMicro.readString());
          break;
        case 18:
        }
        setColorTextArgb(paramCodedInputStreamMicro.readString());
      }
    }

    public TileTemplate setColorTextArgb(String paramString)
    {
      this.hasColorTextArgb = true;
      this.colorTextArgb_ = paramString;
      return this;
    }

    public TileTemplate setColorThemeArgb(String paramString)
    {
      this.hasColorThemeArgb = true;
      this.colorThemeArgb_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasColorThemeArgb())
        paramCodedOutputStreamMicro.writeString(1, getColorThemeArgb());
      if (hasColorTextArgb())
        paramCodedOutputStreamMicro.writeString(2, getColorTextArgb());
    }
  }

  public static final class Warning extends MessageMicro
  {
    private int cachedSize = -1;
    private boolean hasLocalizedMessage;
    private String localizedMessage_ = "";

    public int getCachedSize()
    {
      if (this.cachedSize < 0)
        getSerializedSize();
      return this.cachedSize;
    }

    public String getLocalizedMessage()
    {
      return this.localizedMessage_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      if (hasLocalizedMessage())
        i = 0 + CodedOutputStreamMicro.computeStringSize(1, getLocalizedMessage());
      this.cachedSize = i;
      return i;
    }

    public boolean hasLocalizedMessage()
    {
      return this.hasLocalizedMessage;
    }

    public Warning mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setLocalizedMessage(paramCodedInputStreamMicro.readString());
      }
    }

    public Warning setLocalizedMessage(String paramString)
    {
      this.hasLocalizedMessage = true;
      this.localizedMessage_ = paramString;
      return this;
    }

    public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
      throws IOException
    {
      if (hasLocalizedMessage())
        paramCodedOutputStreamMicro.writeString(1, getLocalizedMessage());
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.remoting.protos.DocAnnotations
 * JD-Core Version:    0.6.2
 */
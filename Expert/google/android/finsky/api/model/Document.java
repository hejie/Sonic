package com.google.android.finsky.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.finsky.remoting.protos.BookInfo.BookDetails;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.Containers.ContainerMetadata;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.remoting.protos.DocAnnotations.Badge;
import com.google.android.finsky.remoting.protos.DocAnnotations.DealOfTheDay;
import com.google.android.finsky.remoting.protos.DocAnnotations.Link;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusOneData;
import com.google.android.finsky.remoting.protos.DocAnnotations.Reason;
import com.google.android.finsky.remoting.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.remoting.protos.DocAnnotations.SeriesAntenna;
import com.google.android.finsky.remoting.protos.DocAnnotations.Template;
import com.google.android.finsky.remoting.protos.DocAnnotations.Warning;
import com.google.android.finsky.remoting.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.remoting.protos.DocDetails.DocumentDetails;
import com.google.android.finsky.remoting.protos.DocDetails.MagazineDetails;
import com.google.android.finsky.remoting.protos.DocDetails.MusicDetails;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.remoting.protos.DocDetails.SubscriptionDetails;
import com.google.android.finsky.remoting.protos.DocDetails.Trailer;
import com.google.android.finsky.remoting.protos.DocDetails.TvEpisodeDetails;
import com.google.android.finsky.remoting.protos.DocDetails.TvSeasonDetails;
import com.google.android.finsky.remoting.protos.DocDetails.VideoCredit;
import com.google.android.finsky.remoting.protos.DocDetails.VideoDetails;
import com.google.android.finsky.remoting.protos.DocDetails.VideoRentalTerm;
import com.google.android.finsky.remoting.protos.DocumentV2.Annotations;
import com.google.android.finsky.remoting.protos.DocumentV2.DocV2;
import com.google.android.finsky.remoting.protos.FilterRules.Availability;
import com.google.android.finsky.remoting.protos.Rating.AggregateRating;
import com.google.android.finsky.utils.FastHtmlParser;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Document
  implements Parcelable
{
  public static Parcelable.Creator<Document> CREATOR = new Parcelable.Creator()
  {
    public Document createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Document((DocumentV2.DocV2)ParcelableProto.getProtoFromParcel(paramAnonymousParcel, ParcelableProto.class.getClassLoader()), paramAnonymousParcel.readString());
    }

    public Document[] newArray(int paramAnonymousInt)
    {
      return new Document[paramAnonymousInt];
    }
  };
  private List<Document> mAppSubscriptionsList;
  private final String mCookie;
  private final DocumentV2.DocV2 mDocument;
  private Map<Integer, List<Doc.Image>> mImageTypeMap;
  private List<Document> mSubscriptionsList;

  public Document(DocumentV2.DocV2 paramDocV2, String paramString)
  {
    this.mDocument = paramDocV2;
    if (paramString != null)
    {
      this.mCookie = paramString;
      return;
    }
    if (paramDocV2.hasContainerMetadata());
    for (String str = paramDocV2.getContainerMetadata().getAnalyticsCookie(); ; str = null)
    {
      this.mCookie = str;
      break;
    }
  }

  public static Bucket convertToBucket(Document paramDocument)
  {
    return new Bucket(paramDocument.mDocument);
  }

  private Map<Integer, List<Doc.Image>> getImageTypeMap()
  {
    if (this.mImageTypeMap == null)
    {
      this.mImageTypeMap = new HashMap();
      Iterator localIterator = this.mDocument.getImageList().iterator();
      while (localIterator.hasNext())
      {
        Doc.Image localImage = (Doc.Image)localIterator.next();
        int i = localImage.getImageType();
        if (!this.mImageTypeMap.containsKey(Integer.valueOf(i)))
          this.mImageTypeMap.put(Integer.valueOf(i), new ArrayList());
        ((List)this.mImageTypeMap.get(Integer.valueOf(i))).add(localImage);
      }
    }
    return this.mImageTypeMap;
  }

  public boolean canUseAsPartialDocument()
  {
    boolean bool = false;
    if (getDocumentType() == 12);
    while (true)
    {
      return bool;
      if ((getSongDetails() == null) && (this.mDocument.getDetailsReusable()))
        bool = true;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public DocDetails.AlbumDetails getAlbumDetails()
  {
    if (hasDetails());
    for (DocDetails.AlbumDetails localAlbumDetails = this.mDocument.getDetails().getAlbumDetails(); ; localAlbumDetails = null)
      return localAlbumDetails;
  }

  public DocAnnotations.SeriesAntenna getAntennaInfo()
  {
    return this.mDocument.getAnnotations().getTemplate().getSeriesAntenna();
  }

  public DocDetails.AppDetails getAppDetails()
  {
    if (hasDetails());
    for (DocDetails.AppDetails localAppDetails = this.mDocument.getDetails().getAppDetails(); ; localAppDetails = null)
      return localAppDetails;
  }

  public List<String> getAppPermissionsList()
  {
    if ((getBackend() == 3) && (hasDetails()) && (this.mDocument.getDetails().hasAppDetails()));
    for (List localList = getAppDetails().getPermissionList(); ; localList = null)
      return localList;
  }

  public List<Document> getAppSubscriptionsList()
  {
    if (!hasAppSubscriptions());
    for (Object localObject = null; ; localObject = this.mAppSubscriptionsList)
    {
      return localObject;
      if (this.mAppSubscriptionsList == null)
      {
        this.mAppSubscriptionsList = new ArrayList();
        Iterator localIterator = this.mDocument.getChildList().iterator();
        while (localIterator.hasNext())
        {
          DocumentV2.DocV2 localDocV2 = (DocumentV2.DocV2)localIterator.next();
          this.mAppSubscriptionsList.add(new Document(localDocV2, this.mCookie));
        }
      }
    }
  }

  public DocDetails.ArtistDetails getArtistDetails()
  {
    if (hasDetails());
    for (DocDetails.ArtistDetails localArtistDetails = this.mDocument.getDetails().getArtistDetails(); ; localArtistDetails = null)
      return localArtistDetails;
  }

  public int getAvailabilityRestriction()
  {
    if (this.mDocument.hasAvailability());
    for (int i = this.mDocument.getAvailability().getRestriction(); ; i = -1)
      return i;
  }

  public List<Common.Offer> getAvailableOffers()
  {
    return this.mDocument.getOfferList();
  }

  public int getBackend()
  {
    return this.mDocument.getBackendId();
  }

  public String getBackendDocId()
  {
    return this.mDocument.getBackendDocid();
  }

  public String getBodyOfWorkBrowseUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionBodyOfWork()));
    for (String str = localAnnotations.getSectionBodyOfWork().getBrowseUrl(); ; str = "")
      return str;
  }

  public String getBodyOfWorkHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionBodyOfWork()));
    for (String str = localAnnotations.getSectionBodyOfWork().getHeader(); ; str = "")
      return str;
  }

  public String getBodyOfWorkListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionBodyOfWork()));
    for (String str = localAnnotations.getSectionBodyOfWork().getListUrl(); ; str = "")
      return str;
  }

  public BookInfo.BookDetails getBookDetails()
  {
    if (hasDetails());
    for (BookInfo.BookDetails localBookDetails = this.mDocument.getDetails().getBookDetails(); ; localBookDetails = null)
      return localBookDetails;
  }

  public int getCensoring()
  {
    return this.mDocument.getDetails().getAlbumDetails().getDetails().getCensoring();
  }

  public Document getChildAt(int paramInt)
  {
    return new Document(this.mDocument.getChild(paramInt), this.mCookie);
  }

  public int getChildCount()
  {
    return this.mDocument.getChildCount();
  }

  public Containers.ContainerMetadata getContainerAnnotation()
  {
    return this.mDocument.getContainerMetadata();
  }

  public String getCookie()
  {
    return this.mCookie;
  }

  public String getCoreContentHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCoreContent()));
    for (String str = localAnnotations.getSectionCoreContent().getHeader(); ; str = "")
      return str;
  }

  public String getCoreContentListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCoreContent()));
    for (String str = localAnnotations.getSectionCoreContent().getListUrl(); ; str = "")
      return str;
  }

  public String getCreator()
  {
    return this.mDocument.getCreator();
  }

  public Collection<DocAnnotations.Badge> getCreatorBadges()
  {
    return this.mDocument.getAnnotations().getBadgeForCreatorList();
  }

  public List<DocDetails.VideoCredit> getCreditsList()
  {
    DocDetails.VideoDetails localVideoDetails = getVideoDetails();
    if (localVideoDetails != null);
    for (List localList = localVideoDetails.getCreditList(); ; localList = null)
      return localList;
  }

  public String getCrossSellBrowseUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCrossSell()));
    for (String str = localAnnotations.getSectionCrossSell().getBrowseUrl(); ; str = "")
      return str;
  }

  public String getCrossSellHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCrossSell()));
    for (String str = localAnnotations.getSectionCrossSell().getHeader(); ; str = "")
      return str;
  }

  public String getCrossSellListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCrossSell()));
    for (String str = localAnnotations.getSectionCrossSell().getListUrl(); ; str = "")
      return str;
  }

  public DocAnnotations.DealOfTheDay getDealOfTheDayInfo()
  {
    return this.mDocument.getAnnotations().getTemplate().getDealOfTheDay();
  }

  public CharSequence getDescription()
  {
    return FastHtmlParser.fromHtml(getOriginalDescription());
  }

  public String getDetailsUrl()
  {
    return this.mDocument.getDetailsUrl();
  }

  public DocDetails.ArtistDetails getDisplayArtist()
  {
    if ((getAlbumDetails() != null) && (getAlbumDetails().getDisplayArtist() != null));
    for (DocDetails.ArtistDetails localArtistDetails = getAlbumDetails().getDisplayArtist(); ; localArtistDetails = null)
      return localArtistDetails;
  }

  public String getDocId()
  {
    return this.mDocument.getDocid();
  }

  public int getDocumentType()
  {
    return this.mDocument.getDocType();
  }

  public DocAnnotations.Badge getFirstCreatorBadge()
  {
    return this.mDocument.getAnnotations().getBadgeForCreator(0);
  }

  public DocAnnotations.Badge getFirstItemBadge()
  {
    return this.mDocument.getAnnotations().getBadgeForDoc(0);
  }

  public String getFormattedPrice(int paramInt)
  {
    Common.Offer localOffer = getOffer(paramInt);
    if ((localOffer != null) && (localOffer.hasFormattedAmount()));
    for (String str = localOffer.getFormattedAmount(); ; str = null)
      return str;
  }

  public List<Doc.Image> getImages(int paramInt)
  {
    return (List)getImageTypeMap().get(Integer.valueOf(paramInt));
  }

  public Collection<DocAnnotations.Badge> getItemBadges()
  {
    return this.mDocument.getAnnotations().getBadgeForDocList();
  }

  public DocAnnotations.Link getLinkAnnotation()
  {
    if (hasLinkAnnotation());
    for (DocAnnotations.Link localLink = this.mDocument.getAnnotations().getLink(); ; localLink = null)
      return localLink;
  }

  public DocDetails.MagazineDetails getMagazineDetails()
  {
    if (hasDetails());
    for (DocDetails.MagazineDetails localMagazineDetails = this.mDocument.getDetails().getMagazineDetails(); ; localMagazineDetails = null)
      return localMagazineDetails;
  }

  public String getMoreByBrowseUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionMoreBy()));
    for (String str = localAnnotations.getSectionMoreBy().getBrowseUrl(); ; str = "")
      return str;
  }

  public String getMoreByHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionMoreBy()));
    for (String str = localAnnotations.getSectionMoreBy().getHeader(); ; str = "")
      return str;
  }

  public String getMoreByListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionMoreBy()));
    for (String str = localAnnotations.getSectionMoreBy().getListUrl(); ; str = "")
      return str;
  }

  public List<DocDetails.VideoRentalTerm> getMovieRentalTerms()
  {
    DocDetails.VideoDetails localVideoDetails = getVideoDetails();
    if (localVideoDetails != null);
    for (List localList = localVideoDetails.getRentalTermList(); ; localList = null)
      return localList;
  }

  public List<DocDetails.Trailer> getMovieTrailers()
  {
    DocDetails.VideoDetails localVideoDetails = getVideoDetails();
    if (localVideoDetails != null);
    for (List localList = localVideoDetails.getTrailerList(); ; localList = null)
      return localList;
  }

  public int getNormalizedContentRating()
  {
    DocDetails.AppDetails localAppDetails = getAppDetails();
    if (localAppDetails == null);
    for (int i = -1; ; i = -1 + localAppDetails.getContentRating())
      return i;
  }

  public Common.Offer getOffer(int paramInt)
  {
    Iterator localIterator = getAvailableOffers().iterator();
    Common.Offer localOffer;
    do
    {
      if (!localIterator.hasNext())
        break;
      localOffer = (Common.Offer)localIterator.next();
    }
    while (localOffer.getOfferType() != paramInt);
    while (true)
    {
      return localOffer;
      localOffer = null;
    }
  }

  public String getOfferNote()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasOfferNote()));
    for (String str = localAnnotations.getOfferNote(); ; str = "")
      return str;
  }

  public String getOriginalDescription()
  {
    return this.mDocument.getDescriptionHtml();
  }

  public DocAnnotations.PlusOneData getPlusOneData()
  {
    if (this.mDocument.getAnnotations() != null);
    for (DocAnnotations.PlusOneData localPlusOneData = this.mDocument.getAnnotations().getPlusOneData(); ; localPlusOneData = null)
      return localPlusOneData;
  }

  public String getPrivacyPolicyUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasPrivacyPolicyUrl()));
    for (String str = localAnnotations.getPrivacyPolicyUrl(); ; str = null)
      return str;
  }

  public long getRatingCount()
  {
    return this.mDocument.getAggregateRating().getRatingsCount();
  }

  public int[] getRatingHistogram()
  {
    int[] arrayOfInt;
    if (!hasRating())
    {
      arrayOfInt = new int[5];
      arrayOfInt[0] = 0;
      arrayOfInt[1] = 0;
      arrayOfInt[2] = 0;
      arrayOfInt[3] = 0;
      arrayOfInt[4] = 0;
    }
    while (true)
    {
      return arrayOfInt;
      Rating.AggregateRating localAggregateRating = this.mDocument.getAggregateRating();
      arrayOfInt = new int[5];
      arrayOfInt[0] = ((int)localAggregateRating.getFiveStarRatings());
      arrayOfInt[1] = ((int)localAggregateRating.getFourStarRatings());
      arrayOfInt[2] = ((int)localAggregateRating.getThreeStarRatings());
      arrayOfInt[3] = ((int)localAggregateRating.getTwoStarRatings());
      arrayOfInt[4] = ((int)localAggregateRating.getOneStarRatings());
    }
  }

  public String getReasonUniqueId()
  {
    if ((!this.mDocument.hasAnnotations()) || (!this.mDocument.getAnnotations().hasReason()));
    for (String str = null; ; str = this.mDocument.getAnnotations().getReason().getUniqueId())
      return str;
  }

  public String getRecommendationReason()
  {
    if ((!this.mDocument.hasAnnotations()) || (!this.mDocument.getAnnotations().hasReason()));
    for (String str = null; ; str = this.mDocument.getAnnotations().getReason().getBriefReason())
      return str;
  }

  public String getRelatedBrowseUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionRelated()));
    for (String str = localAnnotations.getSectionRelated().getBrowseUrl(); ; str = "")
      return str;
  }

  public String getRelatedDocTypeHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionRelatedDocType()));
    for (String str = localAnnotations.getSectionRelatedDocType().getHeader(); ; str = "")
      return str;
  }

  public String getRelatedDocTypeListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionRelatedDocType()));
    for (String str = localAnnotations.getSectionRelatedDocType().getListUrl(); ; str = "")
      return str;
  }

  public String getRelatedHeader()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionRelated()));
    for (String str = localAnnotations.getSectionRelated().getHeader(); ; str = "")
      return str;
  }

  public String getRelatedListUrl()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionRelated()));
    for (String str = localAnnotations.getSectionRelated().getListUrl(); ; str = "")
      return str;
  }

  public int getReleaseType()
  {
    return this.mDocument.getDetails().getAlbumDetails().getDetails().getReleaseType(0);
  }

  public String getReviewsUrl()
  {
    return this.mDocument.getReviewsUrl();
  }

  public String getShareUrl()
  {
    return this.mDocument.getShareUrl();
  }

  public DocDetails.SongDetails getSongDetails()
  {
    if (hasDetails());
    for (DocDetails.SongDetails localSongDetails = this.mDocument.getDetails().getSongDetails(); ; localSongDetails = null)
      return localSongDetails;
  }

  public float getStarRating()
  {
    return this.mDocument.getAggregateRating().getStarRating();
  }

  public DocDetails.SubscriptionDetails getSubscriptionDetails()
  {
    if (hasDetails());
    for (DocDetails.SubscriptionDetails localSubscriptionDetails = this.mDocument.getDetails().getSubscriptionDetails(); ; localSubscriptionDetails = null)
      return localSubscriptionDetails;
  }

  public List<Document> getSubscriptionsList()
  {
    if (!hasSubscriptions());
    for (Object localObject = null; ; localObject = this.mSubscriptionsList)
    {
      return localObject;
      if (this.mSubscriptionsList == null)
      {
        this.mSubscriptionsList = new ArrayList();
        Iterator localIterator = this.mDocument.getAnnotations().getSubscriptionList().iterator();
        while (localIterator.hasNext())
        {
          DocumentV2.DocV2 localDocV2 = (DocumentV2.DocV2)localIterator.next();
          this.mSubscriptionsList.add(new Document(localDocV2, this.mCookie));
        }
      }
    }
  }

  public String getSubtitle()
  {
    if (this.mDocument.hasSubtitle());
    for (String str = this.mDocument.getSubtitle(); ; str = "")
      return str;
  }

  public DocAnnotations.Template getTemplate()
  {
    if (this.mDocument.hasAnnotations());
    for (DocAnnotations.Template localTemplate = this.mDocument.getAnnotations().getTemplate(); ; localTemplate = null)
      return localTemplate;
  }

  public String getTitle()
  {
    return this.mDocument.getTitle();
  }

  public DocDetails.TvEpisodeDetails getTvEpisodeDetails()
  {
    if (hasDetails());
    for (DocDetails.TvEpisodeDetails localTvEpisodeDetails = this.mDocument.getDetails().getTvEpisodeDetails(); ; localTvEpisodeDetails = null)
      return localTvEpisodeDetails;
  }

  public DocDetails.TvSeasonDetails getTvSeasonDetails()
  {
    if (hasDetails());
    for (DocDetails.TvSeasonDetails localTvSeasonDetails = this.mDocument.getDetails().getTvSeasonDetails(); ; localTvSeasonDetails = null)
      return localTvSeasonDetails;
  }

  public int getVersionCode()
  {
    if (getDocumentType() == 1);
    for (int i = getAppDetails().getVersionCode(); ; i = -1)
      return i;
  }

  public DocDetails.VideoDetails getVideoDetails()
  {
    if (hasDetails());
    for (DocDetails.VideoDetails localVideoDetails = this.mDocument.getDetails().getVideoDetails(); ; localVideoDetails = null)
      return localVideoDetails;
  }

  public CharSequence getWarningMessage()
  {
    String str = "";
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    int i = localAnnotations.getWarningCount();
    for (int j = 0; j < i; j++)
    {
      str = str + localAnnotations.getWarning(j).getLocalizedMessage();
      if (j < i - 1)
        str = str + "<br />";
    }
    return FastHtmlParser.fromHtml(str);
  }

  public CharSequence getWhatsNew()
  {
    if ((!hasDetails()) || (getAppDetails() == null));
    for (Object localObject = ""; ; localObject = FastHtmlParser.fromHtml(getAppDetails().getRecentChangesHtml()))
      return localObject;
  }

  public String getYouTubeWatchUrl()
  {
    if (getBackend() == 4);
    for (String str = this.mDocument.getBackendUrl(); ; str = null)
      return str;
  }

  public boolean hasAntennaInfo()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if (!localAnnotations.hasTemplate());
    for (boolean bool = false; ; bool = localAnnotations.getTemplate().hasSeriesAntenna())
      return bool;
  }

  public boolean hasAppSubscriptions()
  {
    int i = 1;
    if ((this.mDocument.getDocType() == i) && (this.mDocument.getChildCount() > 0));
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  public boolean hasCensoring()
  {
    DocDetails.AlbumDetails localAlbumDetails = getAlbumDetails();
    if ((localAlbumDetails != null) && (localAlbumDetails.hasDetails()) && (localAlbumDetails.getDetails().hasCensoring()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasContainerAnnotation()
  {
    return this.mDocument.hasContainerMetadata();
  }

  public boolean hasCreatorBadges()
  {
    if (this.mDocument.getAnnotations().getBadgeForCreatorCount() > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasCreatorRelatedContent()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionMoreBy()) && (!TextUtils.isEmpty(localAnnotations.getSectionMoreBy().getListUrl())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasCrossSellContent()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionCrossSell()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasDealOfTheDayInfo()
  {
    if ((getTemplate() != null) && (getTemplate().getDealOfTheDay() != null));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasDetails()
  {
    return this.mDocument.hasDetails();
  }

  public boolean hasDocumentType()
  {
    return this.mDocument.hasDocType();
  }

  public boolean hasItemBadges()
  {
    if (this.mDocument.getAnnotations().getBadgeForDocCount() > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasLinkAnnotation()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasLink()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasMoreBy()
  {
    DocumentV2.Annotations localAnnotations = this.mDocument.getAnnotations();
    if ((localAnnotations != null) && (localAnnotations.hasSectionMoreBy()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasPlusOneData()
  {
    if (this.mDocument.getAnnotations() != null);
    for (boolean bool = this.mDocument.getAnnotations().hasPlusOneData(); ; bool = false)
      return bool;
  }

  public boolean hasRating()
  {
    return this.mDocument.hasAggregateRating();
  }

  public boolean hasReleaseType()
  {
    DocDetails.AlbumDetails localAlbumDetails = getAlbumDetails();
    if ((localAlbumDetails != null) && (localAlbumDetails.hasDetails()) && (localAlbumDetails.getDetails().getReleaseTypeCount() > 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasReviewHistogramData()
  {
    int[] arrayOfInt = getRatingHistogram();
    int i = arrayOfInt.length;
    int j = 0;
    if (j < i)
      if (arrayOfInt[j] <= 0);
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      j++;
      break;
    }
  }

  public boolean hasSample()
  {
    Iterator localIterator = this.mDocument.getOfferList().iterator();
    do
      if (!localIterator.hasNext())
        break;
    while (((Common.Offer)localIterator.next()).getOfferType() != 2);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasScreenshots()
  {
    int i = 1;
    List localList = getImages(i);
    if ((localList != null) && (!localList.isEmpty()) && (i != getBackend()));
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  public boolean hasSubscriptions()
  {
    if ((this.mDocument.hasAnnotations()) && (this.mDocument.getAnnotations().getSubscriptionCount() > 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasVideoThumbnails()
  {
    List localList = getImages(13);
    if ((localList != null) && (!localList.isEmpty()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasVideos()
  {
    List localList = getImages(3);
    if ((localList != null) && (!localList.isEmpty()) && (!TextUtils.isEmpty(((Doc.Image)localList.get(0)).getImageUrl())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasWarningMessage()
  {
    if ((this.mDocument.hasAnnotations()) && (this.mDocument.getAnnotations().getWarningCount() > 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isAvailableIfOwned()
  {
    if ((this.mDocument.hasAvailability()) && (this.mDocument.getAvailability().getAvailableIfOwned()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isInProgressSeason()
  {
    DocDetails.TvSeasonDetails localTvSeasonDetails = getTvSeasonDetails();
    if ((this.mDocument.getDocType() == 19) && (localTvSeasonDetails != null) && (localTvSeasonDetails.hasExpectedEpisodeCount()) && (localTvSeasonDetails.getEpisodeCount() != localTvSeasonDetails.getExpectedEpisodeCount()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean needsCheckoutFlow(int paramInt)
  {
    Common.Offer localOffer = getOffer(paramInt);
    if (localOffer != null);
    for (boolean bool = localOffer.getCheckoutFlowRequired(); ; bool = false)
      return bool;
  }

  public boolean needsConfirmation(int paramInt)
  {
    if ((needsCheckoutFlow(paramInt)) || (getBackend() == 3));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('{');
    localStringBuilder.append(getDocId());
    if (getDocumentType() == 1)
      localStringBuilder.append(" v=").append(getAppDetails().getVersionCode());
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(ParcelableProto.forProto(this.mDocument), 0);
    paramParcel.writeString(this.mCookie);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.Document
 * JD-Core Version:    0.6.2
 */
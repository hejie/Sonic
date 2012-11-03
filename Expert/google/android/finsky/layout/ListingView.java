package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.remoting.protos.DocDetails.ArtistExternalLinks;
import com.google.android.finsky.remoting.protos.DocDetails.VideoRentalTerm;
import com.google.android.finsky.remoting.protos.DocDetails.VideoRentalTerm.Term;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.IntentUtils;
import java.util.Iterator;
import java.util.List;

public class ListingView extends LinearLayout
{
  private String mCurrentPageUrl;
  private Document mDoc;
  private LayoutInflater mInflater;
  private LinearLayout mListingLayout;
  private int mRows;

  public ListingView(Context paramContext)
  {
    this(paramContext, null);
  }

  public ListingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mInflater = LayoutInflater.from(paramContext);
  }

  private void addEmailLinkRow(int paramInt1, final String paramString, int paramInt2)
  {
    final Uri localUri = Uri.fromParts("mailto", paramString, null);
    final Intent localIntent = IntentUtils.createSendIntentForUrl(localUri);
    localIntent.putExtra("android.intent.extra.SUBJECT", this.mDoc.getTitle());
    ListingRow localListingRow = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
    localListingRow.populate(paramInt1, paramString, paramInt2);
    localListingRow.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ListingView.this.getContext().startActivity(localIntent);
        FinskyApp.get().getAnalytics().logPageView(ListingView.this.mCurrentPageUrl, null, localUri.toString());
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = "emailAddress";
        arrayOfObject[1] = paramString;
        localFinskyEventLog.logTag("emailLink", arrayOfObject);
      }
    });
    this.mListingLayout.addView(localListingRow);
    this.mRows = (1 + this.mRows);
  }

  private void addFlagContentRow(final NavigationManager paramNavigationManager)
  {
    ListingRow localListingRow = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
    if (this.mDoc.getBackend() == 3);
    for (int i = 2131165687; ; i = 2131165689)
    {
      localListingRow.populate(2131165686, i);
      localListingRow.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramNavigationManager.goToFlagContent(ListingView.this.mDoc.getDetailsUrl());
        }
      });
      this.mListingLayout.addView(localListingRow);
      return;
    }
  }

  private void addHowToRow(int paramInt1, int paramInt2, int paramInt3)
  {
    ListingRow localListingRow = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
    localListingRow.populate(paramInt1, paramInt2, paramInt3);
    localListingRow.hideSeparator();
    this.mListingLayout.addView(localListingRow);
    this.mRows = (1 + this.mRows);
  }

  private void addWebLinkRow(int paramInt1, final String paramString1, int paramInt2, final String paramString2)
  {
    final Intent localIntent = IntentUtils.createViewIntentForUrl(Uri.parse(paramString1));
    ListingRow localListingRow = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
    localListingRow.populate(paramInt1, paramString1, paramInt2);
    localListingRow.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ListingView.this.getContext().startActivity(localIntent);
        FinskyApp.get().getAnalytics().logPageView(ListingView.this.mCurrentPageUrl, null, paramString2 + ":" + paramString1);
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = "linkType";
        arrayOfObject[1] = paramString2;
        arrayOfObject[2] = "d";
        arrayOfObject[3] = paramString1;
        localFinskyEventLog.logTag("webLink", arrayOfObject);
      }
    });
    this.mListingLayout.addView(localListingRow);
    this.mRows = (1 + this.mRows);
  }

  public void bindFlagContent(Document paramDocument, NavigationManager paramNavigationManager, boolean paramBoolean)
  {
    this.mDoc = paramDocument;
    if (((paramDocument.getBackend() == 3) || (paramDocument.getBackend() == 2)) && (paramBoolean))
    {
      setupHeader(getContext().getString(2131165704), this.mDoc.getBackend());
      this.mListingLayout.removeAllViews();
      addFlagContentRow(paramNavigationManager);
      setVisibility(0);
    }
    while (true)
    {
      return;
      setVisibility(8);
    }
  }

  public void bindHowToConsume(String paramString, Document paramDocument)
  {
    Context localContext = getContext();
    this.mCurrentPageUrl = paramString;
    this.mRows = 0;
    this.mDoc = paramDocument;
    this.mListingLayout.removeAllViews();
    switch (paramDocument.getDocumentType())
    {
    case 3:
    case 7:
    case 8:
    case 9:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 18:
    default:
      if (this.mRows == 0)
        setVisibility(8);
      break;
    case 5:
    case 15:
    case 16:
    case 17:
    case 2:
    case 4:
    case 6:
    case 19:
    case 20:
    }
    while (true)
    {
      return;
      setupHeader(localContext.getString(2131165815), paramDocument.getBackend());
      addHowToRow(2131165817, 2131165818, 2130837616);
      addHowToRow(2131165822, 2131165823, 2130837617);
      break;
      setupHeader(localContext.getString(2131165815), paramDocument.getBackend());
      addHowToRow(2131165817, 2131165821, 2130837618);
      break;
      setupHeader(localContext.getString(2131165812), paramDocument.getBackend());
      addHowToRow(2131165817, 2131165820, 2130837620);
      addHowToRow(2131165822, 2131165825, 2130837617);
      break;
      setupHeader(localContext.getString(2131165816), paramDocument.getBackend());
      addHowToRow(2131165817, 2131165819, 2130837619);
      addHowToRow(2131165822, 2131165824, 2130837617);
      break;
      setVisibility(0);
    }
  }

  public void bindLinks(String paramString, Document paramDocument)
  {
    Context localContext = getContext();
    this.mCurrentPageUrl = paramString;
    this.mRows = 0;
    this.mDoc = paramDocument;
    this.mListingLayout.removeAllViews();
    switch (paramDocument.getDocumentType())
    {
    case 2:
    default:
      if (this.mRows == 0)
        setVisibility(8);
      break;
    case 1:
    case 3:
    }
    while (true)
    {
      return;
      setupHeader(localContext.getString(2131165493), this.mDoc.getBackend());
      DocDetails.AppDetails localAppDetails = this.mDoc.getAppDetails();
      if (localAppDetails.hasDeveloperWebsite())
      {
        String str6 = localAppDetails.getDeveloperWebsite();
        if (!TextUtils.isEmpty(str6))
          addWebLinkRow(2131165520, str6, 2130837613, "developerLink");
      }
      if (localAppDetails.hasDeveloperEmail())
      {
        String str5 = localAppDetails.getDeveloperEmail();
        if (!TextUtils.isEmpty(str5))
          addEmailLinkRow(2131165521, str5, 2130837611);
      }
      String str4 = this.mDoc.getPrivacyPolicyUrl();
      if (str4 == null)
        break;
      addWebLinkRow(2131165522, str4, 2130837613, "privacyPolicy");
      break;
      setupHeader(localContext.getString(2131165498), this.mDoc.getBackend());
      DocDetails.ArtistDetails localArtistDetails = this.mDoc.getArtistDetails();
      if ((localArtistDetails == null) || (!localArtistDetails.hasExternalLinks()))
        break;
      DocDetails.ArtistExternalLinks localArtistExternalLinks = localArtistDetails.getExternalLinks();
      if (localArtistExternalLinks.getWebsiteUrlCount() > 0)
      {
        Iterator localIterator = localArtistExternalLinks.getWebsiteUrlList().iterator();
        while (localIterator.hasNext())
        {
          String str3 = (String)localIterator.next();
          if (!TextUtils.isEmpty(str3))
            addWebLinkRow(2131165520, str3, 2130837613, "developerLink");
        }
      }
      if (localArtistExternalLinks.hasYoutubeChannelUrl())
      {
        String str2 = localArtistExternalLinks.getYoutubeChannelUrl();
        if (!TextUtils.isEmpty(str2))
          addWebLinkRow(2131165523, str2, 2130837614, "artistYoutubeLink");
      }
      if (!localArtistExternalLinks.hasGooglePlusProfileUrl())
        break;
      String str1 = localArtistExternalLinks.getGooglePlusProfileUrl();
      if (TextUtils.isEmpty(str1))
        break;
      addWebLinkRow(2131165524, str1, 2130837612, "artistGooglePlusLink");
      break;
      setVisibility(0);
    }
  }

  public void bindRentalTerms(Document paramDocument, int paramInt)
  {
    this.mListingLayout.removeAllViews();
    Iterator localIterator1 = paramDocument.getMovieRentalTerms().iterator();
    while (localIterator1.hasNext())
    {
      DocDetails.VideoRentalTerm localVideoRentalTerm = (DocDetails.VideoRentalTerm)localIterator1.next();
      if (localVideoRentalTerm.getOfferType() == paramInt)
      {
        setupHeader(localVideoRentalTerm.getRentalHeader(), paramDocument.getBackend());
        Iterator localIterator2 = localVideoRentalTerm.getTermList().iterator();
        while (localIterator2.hasNext())
        {
          DocDetails.VideoRentalTerm.Term localTerm = (DocDetails.VideoRentalTerm.Term)localIterator2.next();
          ListingRow localListingRow = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
          localListingRow.populateAsHtml(localTerm.getHeader(), localTerm.getBody());
          localListingRow.hideSeparator();
          this.mListingLayout.addView(localListingRow);
        }
        setVisibility(0);
      }
    }
  }

  public void bindSubscriptionInfo(Document paramDocument, LibrarySubscriptionEntry paramLibrarySubscriptionEntry)
  {
    if (paramDocument.getAvailableOffers().size() > 0)
    {
      this.mListingLayout.removeAllViews();
      setupHeader(getContext().getString(2131165813), paramDocument.getBackend());
      Common.Offer localOffer = (Common.Offer)paramDocument.getAvailableOffers().get(0);
      ListingRow localListingRow1 = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
      localListingRow1.populateAsHtml(localOffer.getFormattedName(), localOffer.getFormattedDescription());
      localListingRow1.hideSeparator();
      this.mListingLayout.addView(localListingRow1);
      String str = SubscriptionView.getSubscriptionPriceDescription(paramDocument, localOffer, getResources());
      SubscriptionView.SubscriptionDateInfo localSubscriptionDateInfo = new SubscriptionView.SubscriptionDateInfo();
      SubscriptionView.fillSubscriptionDateInfo(localSubscriptionDateInfo, paramLibrarySubscriptionEntry, getResources());
      ListingRow localListingRow2 = (ListingRow)this.mInflater.inflate(2130968725, this.mListingLayout, false);
      localListingRow2.populate(2131165814, str, localSubscriptionDateInfo.renewalDescription);
      localListingRow2.hideSeparator();
      this.mListingLayout.addView(localListingRow2);
      setVisibility(0);
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mListingLayout = ((LinearLayout)findViewById(2131230934));
  }

  protected void setupHeader(String paramString, int paramInt)
  {
    TextView localTextView = (TextView)findViewById(2131230895);
    if (localTextView != null)
    {
      if (!TextUtils.isEmpty(paramString))
        localTextView.setText(paramString.toUpperCase());
      localTextView.setTextColor(CorpusResourceUtils.getBackendHintColor(getContext(), paramInt));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ListingView
 * JD-Core Version:    0.6.2
 */
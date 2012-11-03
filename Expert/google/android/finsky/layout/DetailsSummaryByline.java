package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.BookInfo.BookDetails;
import com.google.android.finsky.remoting.protos.DocDetails.AlbumDetails;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.MagazineDetails;
import com.google.android.finsky.remoting.protos.DocDetails.MusicDetails;
import com.google.android.finsky.remoting.protos.DocDetails.VideoDetails;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.FinskyLog;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class DetailsSummaryByline extends AccessibleLinearLayout
{
  private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance();
  private static final DecimalFormat RATING_FORMATTER = new DecimalFormat("#.0");
  private LinearLayout mFirstRow;
  private LayoutInflater mInflater;
  private LinearLayout mSecondRow;
  private boolean mSingleColumnMode;

  public DetailsSummaryByline(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (!paramContext.getResources().getBoolean(2131296262));
    for (boolean bool = true; ; bool = false)
    {
      this.mSingleColumnMode = bool;
      return;
    }
  }

  private void configureContentDescription(Document paramDocument)
  {
    long l;
    String str1;
    label32: String str5;
    if (paramDocument.hasRating())
    {
      l = paramDocument.getRatingCount();
      if (!paramDocument.hasRating())
        break label134;
      str1 = RATING_FORMATTER.format(paramDocument.getStarRating());
      DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
      if (localAppDetails == null)
        break label148;
      if (!localAppDetails.hasNumDownloads())
        break label141;
      str5 = localAppDetails.getNumDownloads();
      label58: Context localContext5 = getContext();
      Object[] arrayOfObject5 = new Object[5];
      arrayOfObject5[0] = Long.valueOf(l);
      arrayOfObject5[1] = str1;
      arrayOfObject5[2] = str5;
      arrayOfObject5[3] = localAppDetails.getUploadDate();
      arrayOfObject5[4] = Formatter.formatFileSize(getContext(), localAppDetails.getInstallationSize());
      setContentDescription(localContext5.getString(2131165669, arrayOfObject5));
    }
    label134: label141: label148: label511: DocDetails.MagazineDetails localMagazineDetails;
    do
    {
      while (true)
      {
        return;
        l = 0L;
        break;
        str1 = "0";
        break label32;
        str5 = "0";
        break label58;
        BookInfo.BookDetails localBookDetails = paramDocument.getBookDetails();
        if (localBookDetails != null)
        {
          if ((localBookDetails.hasPublicationDate()) && (localBookDetails.hasPublisher()) && (localBookDetails.hasNumberOfPages()))
            try
            {
              Context localContext4 = getContext();
              Object[] arrayOfObject4 = new Object[5];
              arrayOfObject4[0] = Long.valueOf(l);
              arrayOfObject4[1] = str1;
              arrayOfObject4[2] = localBookDetails.getPublisher();
              arrayOfObject4[3] = DateUtils.formatIso8601Date(localBookDetails.getPublicationDate());
              arrayOfObject4[4] = Integer.valueOf(localBookDetails.getNumberOfPages());
              setContentDescription(localContext4.getString(2131165670, arrayOfObject4));
            }
            catch (ParseException localParseException2)
            {
            }
        }
        else
        {
          DocDetails.VideoDetails localVideoDetails = paramDocument.getVideoDetails();
          if (localVideoDetails != null)
          {
            if ((localVideoDetails.hasReleaseDate()) && (localVideoDetails.hasDuration()))
            {
              Context localContext3 = getContext();
              Object[] arrayOfObject3 = new Object[5];
              arrayOfObject3[0] = Long.valueOf(l);
              arrayOfObject3[1] = str1;
              if (localVideoDetails.hasContentRating());
              for (String str4 = localVideoDetails.getContentRating(); ; str4 = getContext().getString(2131165466))
              {
                arrayOfObject3[2] = str4;
                arrayOfObject3[3] = localVideoDetails.getReleaseDate();
                arrayOfObject3[4] = localVideoDetails.getDuration();
                setContentDescription(localContext3.getString(2131165671, arrayOfObject3));
                break;
              }
            }
          }
          else
          {
            DocDetails.AlbumDetails localAlbumDetails = paramDocument.getAlbumDetails();
            if (localAlbumDetails == null)
              break label511;
            DocDetails.MusicDetails localMusicDetails = localAlbumDetails.getDetails();
            if ((localMusicDetails != null) && (localMusicDetails.hasOriginalReleaseDate()) && (localMusicDetails.hasLabel()) && (!TextUtils.isEmpty(localMusicDetails.getLabel())) && (localMusicDetails.getGenreCount() > 0))
              try
              {
                Context localContext2 = getContext();
                Object[] arrayOfObject2 = new Object[3];
                arrayOfObject2[0] = localMusicDetails.getLabel();
                arrayOfObject2[1] = DateUtils.formatIso8601Date(localMusicDetails.getOriginalReleaseDate());
                arrayOfObject2[2] = TextUtils.join(", ", localMusicDetails.getGenreList());
                setContentDescription(localContext2.getString(2131165672, arrayOfObject2));
              }
              catch (ParseException localParseException1)
              {
              }
          }
        }
      }
      localMagazineDetails = paramDocument.getMagazineDetails();
    }
    while (localMagazineDetails == null);
    Context localContext1 = getContext();
    Object[] arrayOfObject1 = new Object[4];
    arrayOfObject1[0] = Long.valueOf(l);
    arrayOfObject1[1] = str1;
    String str2;
    if (localMagazineDetails.hasDeliveryFrequencyDescription())
    {
      str2 = localMagazineDetails.getDeliveryFrequencyDescription();
      label563: arrayOfObject1[2] = str2;
      if (!localMagazineDetails.hasPsvDescription())
        break label613;
    }
    label613: for (String str3 = localMagazineDetails.getPsvDescription(); ; str3 = "")
    {
      arrayOfObject1[3] = str3;
      setContentDescription(localContext1.getString(2131165673, arrayOfObject1));
      break;
      str2 = "";
      break label563;
    }
  }

  private void configureItemTextInfo(Document paramDocument)
  {
    switch (paramDocument.getBackend())
    {
    case 5:
    default:
    case 3:
    case 1:
    case 4:
    case 2:
    case 6:
    }
    while (true)
    {
      return;
      configureAppDetailsByline(paramDocument);
      continue;
      configureBookDetailsByline(paramDocument);
      continue;
      configureMovieDetailsByline(paramDocument);
      continue;
      configureAlbumDetailsByline(paramDocument);
      continue;
      configureMagazineDetailsByline(paramDocument);
    }
  }

  private void configureRating(Document paramDocument)
  {
    View localView = this.mInflater.inflate(2130968662, this.mFirstRow, false);
    RatingBar localRatingBar = (RatingBar)localView.findViewById(2131230917);
    TextView localTextView = (TextView)localView.findViewById(2131230918);
    if ((paramDocument.hasRating()) && (paramDocument.getRatingCount() > 0L))
    {
      localRatingBar.setVisibility(0);
      localTextView.setVisibility(0);
      localRatingBar.setRating(paramDocument.getStarRating());
      localTextView.setText(NUMBER_FORMATTER.format(paramDocument.getRatingCount()));
    }
    while (true)
    {
      this.mFirstRow.addView(localView);
      return;
      localRatingBar.setVisibility(4);
      localTextView.setVisibility(4);
    }
  }

  protected void configureAlbumDetailsByline(Document paramDocument)
  {
    DocDetails.AlbumDetails localAlbumDetails = paramDocument.getAlbumDetails();
    DocDetails.MusicDetails localMusicDetails;
    int i;
    if (localAlbumDetails != null)
    {
      localMusicDetails = localAlbumDetails.getDetails();
      i = 0;
      if (!localMusicDetails.hasOriginalReleaseDate());
    }
    try
    {
      String str4 = DateUtils.formatIso8601Date(localMusicDetails.getOriginalReleaseDate());
      this.mFirstRow.removeAllViews();
      TextView localTextView3 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
      localTextView3.setText(str4);
      this.mFirstRow.addView(localTextView3);
      i = 1;
      if ((localMusicDetails.hasLabel()) && (!TextUtils.isEmpty(localMusicDetails.getLabel())))
      {
        if ((localMusicDetails.hasReleaseDate()) && (localMusicDetails.getReleaseDate().length() >= 4))
        {
          String str3 = localMusicDetails.getReleaseDate().substring(0, 4);
          Context localContext2 = getContext();
          Object[] arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = str3;
          arrayOfObject2[1] = localMusicDetails.getLabel();
          str2 = localContext2.getString(2131165778, arrayOfObject2);
          TextView localTextView2 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
          localTextView2.setGravity(21);
          localTextView2.setText(str2);
          this.mFirstRow.addView(localTextView2);
          i = 1;
        }
      }
      else
      {
        if (localMusicDetails.getGenreCount() > 0)
        {
          String str1 = TextUtils.join(", ", localMusicDetails.getGenreList());
          TextView localTextView1 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
          localTextView1.setText(str1);
          this.mSecondRow.addView(localTextView1);
          if (!TextUtils.isEmpty(str1))
            i = 1;
        }
        if (i == 0)
          setVisibility(8);
        return;
      }
    }
    catch (ParseException localParseException)
    {
      while (true)
      {
        FinskyLog.e("Cannot parse ISO 8601 date " + localParseException, new Object[0]);
        continue;
        Context localContext1 = getContext();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localMusicDetails.getLabel();
        String str2 = localContext1.getString(2131165777, arrayOfObject1);
      }
    }
  }

  protected void configureAppDetailsByline(Document paramDocument)
  {
    String str1 = "";
    String str2 = "";
    long l = 0L;
    DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
    if (localAppDetails != null)
    {
      if (localAppDetails.hasUploadDate())
        str1 = localAppDetails.getUploadDate();
      if (localAppDetails.hasNumDownloads())
      {
        Context localContext = getContext();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localAppDetails.getNumDownloads();
        str2 = localContext.getString(2131165463, arrayOfObject);
      }
      if (localAppDetails.hasInstallationSize())
        l = localAppDetails.getInstallationSize();
    }
    TextView localTextView1 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
    localTextView1.setText(str1);
    if (this.mSingleColumnMode)
      localTextView1.setGravity(21);
    this.mFirstRow.addView(localTextView1);
    TextView localTextView2 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
    localTextView2.setText(str2);
    this.mSecondRow.addView(localTextView2);
    LinearLayout localLinearLayout;
    TextView localTextView3;
    if (this.mSingleColumnMode)
    {
      localLinearLayout = this.mSecondRow;
      localTextView3 = (TextView)this.mInflater.inflate(2130968664, localLinearLayout, false);
      if (l > 0L)
        break label246;
    }
    label246: for (String str3 = ""; ; str3 = Formatter.formatFileSize(getContext(), l))
    {
      localTextView3.setText(str3);
      localTextView3.setGravity(21);
      localLinearLayout.addView(localTextView3);
      return;
      localLinearLayout = this.mFirstRow;
      break;
    }
  }

  protected void configureBookDetailsByline(Document paramDocument)
  {
    BookInfo.BookDetails localBookDetails = paramDocument.getBookDetails();
    LinearLayout localLinearLayout;
    if (localBookDetails != null)
    {
      if (localBookDetails.hasPublisher())
      {
        TextView localTextView3 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
        localTextView3.setText(localBookDetails.getPublisher());
        this.mSecondRow.addView(localTextView3);
      }
      if (localBookDetails.hasNumberOfPages())
      {
        if (!this.mSingleColumnMode)
          break label207;
        localLinearLayout = this.mSecondRow;
      }
    }
    while (true)
    {
      TextView localTextView2 = (TextView)this.mInflater.inflate(2130968664, localLinearLayout, false);
      if (this.mSingleColumnMode)
        localTextView2.setGravity(21);
      Context localContext = getContext();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(localBookDetails.getNumberOfPages());
      localTextView2.setText(localContext.getString(2131165464, arrayOfObject));
      localLinearLayout.addView(localTextView2);
      if (localBookDetails.hasPublicationDate());
      try
      {
        TextView localTextView1 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
        localTextView1.setGravity(21);
        localTextView1.setText(DateUtils.formatIso8601Date(localBookDetails.getPublicationDate()));
        this.mFirstRow.addView(localTextView1);
        return;
        label207: localLinearLayout = this.mFirstRow;
      }
      catch (ParseException localParseException)
      {
        while (true)
          FinskyLog.e("Cannot parse ISO 8601 date " + localParseException, new Object[0]);
      }
    }
  }

  protected void configureMagazineDetailsByline(Document paramDocument)
  {
    DocDetails.MagazineDetails localMagazineDetails = paramDocument.getMagazineDetails();
    if (localMagazineDetails != null)
    {
      if (localMagazineDetails.hasDeliveryFrequencyDescription())
      {
        TextView localTextView2 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
        localTextView2.setText(localMagazineDetails.getDeliveryFrequencyDescription());
        if (this.mSingleColumnMode)
          localTextView2.setGravity(21);
        this.mFirstRow.addView(localTextView2);
      }
      if (localMagazineDetails.hasPsvDescription())
      {
        TextView localTextView1 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
        localTextView1.setText(localMagazineDetails.getPsvDescription());
        this.mSecondRow.addView(localTextView1);
      }
    }
  }

  protected void configureMovieDetailsByline(Document paramDocument)
  {
    DocDetails.VideoDetails localVideoDetails = paramDocument.getVideoDetails();
    Context localContext;
    Object[] arrayOfObject;
    if (localVideoDetails != null)
    {
      if (!localVideoDetails.hasContentRating())
        break label194;
      localContext = getContext();
      arrayOfObject = new Object[1];
      arrayOfObject[0] = localVideoDetails.getContentRating();
    }
    label194: for (String str = localContext.getString(2131165465, arrayOfObject); ; str = getContext().getString(2131165466))
    {
      TextView localTextView1 = (TextView)this.mInflater.inflate(2130968664, this.mFirstRow, false);
      localTextView1.setText(str);
      if (this.mSingleColumnMode)
        localTextView1.setGravity(21);
      this.mFirstRow.addView(localTextView1);
      if (localVideoDetails.hasReleaseDate())
      {
        TextView localTextView3 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
        localTextView3.setText(localVideoDetails.getReleaseDate());
        this.mSecondRow.addView(localTextView3);
      }
      if (localVideoDetails.hasDuration())
      {
        TextView localTextView2 = (TextView)this.mInflater.inflate(2130968664, this.mSecondRow, false);
        localTextView2.setText(localVideoDetails.getDuration());
        localTextView2.setGravity(21);
        this.mSecondRow.addView(localTextView2);
      }
      return;
    }
  }

  public void setDocument(Document paramDocument)
  {
    removeAllViews();
    this.mInflater = LayoutInflater.from(getContext());
    this.mFirstRow = ((LinearLayout)this.mInflater.inflate(2130968663, this, false));
    this.mSecondRow = ((LinearLayout)this.mInflater.inflate(2130968663, this, false));
    addView(this.mFirstRow);
    addView(this.mSecondRow);
    if (this.mSingleColumnMode)
      configureRating(paramDocument);
    configureItemTextInfo(paramDocument);
    configureContentDescription(paramDocument);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DetailsSummaryByline
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.BookInfo.BookDetails;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.MagazineDetails;
import com.google.android.finsky.remoting.protos.DocDetails.VideoDetails;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;

public class DetailsDescriptionViewBinder extends DetailsTextViewBinder
{
  private String getExtraSummary(Document paramDocument)
  {
    int i = paramDocument.getDocumentType();
    String str;
    if (i == 1)
    {
      DocDetails.AppDetails localAppDetails = paramDocument.getAppDetails();
      StringBuilder localStringBuilder2 = new StringBuilder();
      if (localAppDetails.hasVersionString())
      {
        Context localContext6 = this.mContext;
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = localAppDetails.getVersionString();
        localStringBuilder2.append(localContext6.getString(2131165462, arrayOfObject6));
      }
      if (localAppDetails.hasUploadDate())
      {
        if (localStringBuilder2.length() > 0)
          localStringBuilder2.append("<br>");
        Context localContext5 = this.mContext;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = localAppDetails.getUploadDate();
        localStringBuilder2.append(localContext5.getString(2131165654, arrayOfObject5));
      }
      if (localAppDetails.hasInstallationSize())
      {
        if (localStringBuilder2.length() > 0)
          localStringBuilder2.append("<br>");
        Context localContext4 = this.mContext;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Formatter.formatFileSize(this.mContext, localAppDetails.getInstallationSize());
        localStringBuilder2.append(localContext4.getString(2131165655, arrayOfObject4));
      }
      if (localStringBuilder2.length() > 0)
        localStringBuilder2.append("<br>");
      int j = paramDocument.getNormalizedContentRating();
      Context localContext3 = this.mContext;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = ContentFilterActivity.getLabel(this.mContext, j);
      localStringBuilder2.append(localContext3.getString(2131165460, arrayOfObject3));
      str = localStringBuilder2.toString();
    }
    while (true)
    {
      return str;
      if (i == 5)
      {
        BookInfo.BookDetails localBookDetails = paramDocument.getBookDetails();
        if (localBookDetails.hasIsbn())
          str = localBookDetails.getIsbn();
        else
          str = null;
      }
      else if (i == 16)
      {
        Document localDocument = DocUtils.getMagazineCurrentIssueDocument(paramDocument);
        if (localDocument != null)
        {
          DocDetails.MagazineDetails localMagazineDetails2 = localDocument.getMagazineDetails();
          if ((localMagazineDetails2 != null) && (localMagazineDetails2.hasDeviceAvailabilityDescriptionHtml()))
          {
            str = localMagazineDetails2.getDeviceAvailabilityDescriptionHtml();
            continue;
          }
          str = null;
        }
      }
      else if (i == 17)
      {
        DocDetails.MagazineDetails localMagazineDetails1 = paramDocument.getMagazineDetails();
        if (localMagazineDetails1.hasDeviceAvailabilityDescriptionHtml())
          str = localMagazineDetails1.getDeviceAvailabilityDescriptionHtml();
        else
          str = null;
      }
      else if (i == 6)
      {
        DocDetails.VideoDetails localVideoDetails = paramDocument.getVideoDetails();
        StringBuilder localStringBuilder1 = new StringBuilder();
        if (localVideoDetails.getAudioLanguageCount() > 0)
        {
          Context localContext2 = this.mContext;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = TextUtils.join(",", localVideoDetails.getAudioLanguageList());
          localStringBuilder1.append(localContext2.getString(2131165656, arrayOfObject2));
        }
        if (localVideoDetails.getCaptionLanguageCount() > 0)
        {
          if (localStringBuilder1.length() > 0)
            localStringBuilder1.append("<br>");
          Context localContext1 = this.mContext;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = TextUtils.join(",", localVideoDetails.getCaptionLanguageList());
          localStringBuilder1.append(localContext1.getString(2131165657, arrayOfObject1));
        }
        str = localStringBuilder1.toString();
      }
      else
      {
        str = null;
      }
    }
  }

  public void bind(View paramView, Document paramDocument, Bundle paramBundle)
  {
    int i = CorpusResourceUtils.getDescriptionHeaderStringId(paramDocument.getBackend());
    String str1 = paramDocument.getOriginalDescription();
    String str2 = getExtraSummary(paramDocument);
    if (TextUtils.isEmpty(str2));
    for (Spanned localSpanned = Html.fromHtml(str1); ; localSpanned = Html.fromHtml(str1 + "<br><br>" + str2))
    {
      super.bind(paramView, paramDocument, i, localSpanned, paramBundle);
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.DetailsDescriptionViewBinder
 * JD-Core Version:    0.6.2
 */
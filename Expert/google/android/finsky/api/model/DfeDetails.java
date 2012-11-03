package com.google.android.finsky.api.model;

import com.android.volley.Response.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Details.DetailsResponse;
import com.google.android.finsky.remoting.protos.Rev.Review;

public class DfeDetails extends DfeModel
  implements Response.Listener<Details.DetailsResponse>
{
  private final String mAnalyticsCookie;
  private Details.DetailsResponse mDetailsResponse;
  private final String mDetailsUrl;

  public DfeDetails(DfeApi paramDfeApi, String paramString)
  {
    this(paramDfeApi, paramString, null);
  }

  public DfeDetails(DfeApi paramDfeApi, String paramString1, String paramString2)
  {
    this.mAnalyticsCookie = paramString2;
    this.mDetailsUrl = paramString1;
    paramDfeApi.getDetails(this.mDetailsUrl, this, this);
  }

  public Document getDocument()
  {
    if ((this.mDetailsResponse == null) || (!this.mDetailsResponse.hasDocV2()));
    for (Document localDocument = null; ; localDocument = new Document(this.mDetailsResponse.getDocV2(), this.mAnalyticsCookie))
      return localDocument;
  }

  public String getFooterHtml()
  {
    if ((this.mDetailsResponse == null) || (!this.mDetailsResponse.hasFooterHtml()));
    for (String str = null; ; str = this.mDetailsResponse.getFooterHtml())
      return str;
  }

  public Rev.Review getUserReview()
  {
    if ((this.mDetailsResponse == null) || (!this.mDetailsResponse.hasUserReview()));
    for (Rev.Review localReview = null; ; localReview = this.mDetailsResponse.getUserReview())
      return localReview;
  }

  public Rev.Review initializeUserReview()
  {
    this.mDetailsResponse.setUserReview(new Rev.Review());
    return getUserReview();
  }

  public boolean isReady()
  {
    if (this.mDetailsResponse != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void onResponse(Details.DetailsResponse paramDetailsResponse)
  {
    this.mDetailsResponse = paramDetailsResponse;
    notifyDataSetChanged();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.api.model.DfeDetails
 * JD-Core Version:    0.6.2
 */
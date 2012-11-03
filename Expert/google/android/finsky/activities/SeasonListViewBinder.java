package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.EpisodeList;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.List;

public class SeasonListViewBinder extends DetailsViewBinder
  implements Response.ErrorListener, OnDataChangedListener
{
  private BitmapLoader mBitmapLoader;
  private String mCurrentPageUrl;
  private Libraries mLibraries;
  private PageFragment mPageFragment;
  private String mReferrerCookie;
  private String mReferrerUrl;
  private Bundle mRestoreBundle;
  private String mSeasonListUrl;
  private DfeList mSeasonsListRequest;
  private String mSelectedEpisodeId;
  private String mSelectedSeasonId;

  private void detachListeners()
  {
    if (this.mSeasonsListRequest != null)
    {
      this.mSeasonsListRequest.removeDataChangedListener(this);
      this.mSeasonsListRequest.removeErrorListener(this);
    }
  }

  public void bind(Libraries paramLibraries, EpisodeList paramEpisodeList, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, String paramString6, String paramString7, String paramString8)
  {
    super.bind(paramEpisodeList, paramString3, 4);
    if (!paramBoolean)
      this.mLayout.setVisibility(8);
    while (true)
    {
      return;
      this.mSelectedEpisodeId = paramString2;
      this.mSelectedSeasonId = paramString1;
      this.mSeasonListUrl = paramString5;
      this.mCurrentPageUrl = paramString8;
      this.mLayout.setVisibility(0);
      detachListeners();
      this.mSeasonsListRequest = new DfeList(this.mDfeApi, paramString5, false);
      this.mSeasonsListRequest.addDataChangedListener(this);
      this.mSeasonsListRequest.addErrorListener(this);
      this.mSeasonsListRequest.startLoadItems();
      this.mReferrerUrl = paramString6;
      this.mReferrerCookie = paramString7;
    }
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager)
  {
    throw new IllegalStateException("this version of init is not supported by this binder.");
  }

  public void init(Context paramContext, DfeApi paramDfeApi, NavigationManager paramNavigationManager, PageFragment paramPageFragment, BitmapLoader paramBitmapLoader, Libraries paramLibraries)
  {
    this.mContext = paramContext;
    this.mDfeApi = paramDfeApi;
    this.mNavigationManager = paramNavigationManager;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.mHeaderLayoutId = 2131230895;
    this.mPageFragment = paramPageFragment;
    this.mBitmapLoader = paramBitmapLoader;
    this.mLibraries = paramLibraries;
  }

  public void onDataChanged()
  {
    if (this.mSeasonsListRequest.getBucketCount() != 0);
    ArrayList localArrayList;
    for (String str1 = ((Bucket)this.mSeasonsListRequest.getBucketList().get(0)).getAnalyticsCookie(); ; str1 = null)
    {
      FinskyApp.get().getAnalytics().logListViewOnPage(this.mReferrerUrl, this.mReferrerCookie, this.mSeasonListUrl, str1);
      FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
      String str2 = this.mCurrentPageUrl;
      String str3 = this.mSeasonListUrl;
      localFinskyEventLog.logView(str2, str1, str3);
      localArrayList = Lists.newArrayList();
      FinskyApp.get().getLibraries();
      for (int i = 0; i < this.mSeasonsListRequest.getCount(); i++)
        localArrayList.add((Document)this.mSeasonsListRequest.getItem(i));
    }
    EpisodeList localEpisodeList = (EpisodeList)this.mLayout;
    localEpisodeList.restoreInstanceState(this.mRestoreBundle);
    localEpisodeList.setSeasonList(this.mPageFragment, this.mDfeApi, this.mLibraries, this.mNavigationManager, this.mBitmapLoader, this.mRestoreBundle, localArrayList, this.mSelectedSeasonId, this.mSelectedEpisodeId, this.mReferrerUrl, this.mReferrerCookie, this.mCurrentPageUrl);
  }

  public void onDestroyView()
  {
    detachListeners();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    Toast.makeText(this.mContext, ErrorStrings.get(this.mContext, paramVolleyError), 0).show();
  }

  public void restoreInstanceState(Bundle paramBundle)
  {
    this.mRestoreBundle = paramBundle;
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    EpisodeList localEpisodeList = (EpisodeList)this.mLayout;
    if (localEpisodeList != null)
      localEpisodeList.saveInstanceState(paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SeasonListViewBinder
 * JD-Core Version:    0.6.2
 */
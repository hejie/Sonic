package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.experiments.Experiments;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EpisodeList extends LinearLayout
  implements AdapterView.OnItemSelectedListener, Response.ErrorListener, OnDataChangedListener, EpisodeSnippet.OnCollapsedStateChanged, Libraries.Listener
{
  private BitmapLoader mBitmapLoader;
  private Button mBuyButton;
  private final int mCorpusColor;
  private String mCurrentPageUrl;
  private DfeApi mDfeApi;
  private String mEpisodeIdFromBundle;
  private final Map<String, EpisodeSnippet> mEpisodeSnippets = Maps.newHashMap();
  private LinearLayout mEpisodesContainer;
  private Pair<String, Set<String>> mEpisodesInLibraryFromFirstLoad;
  private Libraries mLibraries;
  private View mLoadingOverlay;
  private NavigationManager mNavigationManager;
  private Document mOldSeason;
  private PageFragment mPageFragment;
  private String mReferrerListCookie;
  private String mReferrerUrl;
  private String mSeasonIdFromBundle;
  private List<Document> mSeasonList;
  private Spinner mSeasonSpinner;
  private Document mSelectedSeason;
  private DfeList mSelectedSeasonRequest;
  private String mSelectedSeasonRequestUrl;

  public EpisodeList(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mCorpusColor = CorpusResourceUtils.getBackendHintColor(paramContext, 4);
  }

  private void hideUi()
  {
    ViewGroup localViewGroup = (ViewGroup)getParent();
    if (localViewGroup != null)
      localViewGroup.removeView(this);
    while (true)
    {
      return;
      setVisibility(8);
    }
  }

  private void setDefaultSelectionState(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(this.mSeasonIdFromBundle))
      this.mSeasonIdFromBundle = paramString1;
    if (TextUtils.isEmpty(this.mEpisodeIdFromBundle))
      this.mEpisodeIdFromBundle = paramString2;
  }

  private void setEpisodeList(Document paramDocument, List<Document> paramList)
  {
    if (!this.mEpisodeSnippets.isEmpty())
    {
      this.mEpisodeSnippets.clear();
      this.mEpisodesContainer.removeAllViews();
    }
    this.mSelectedSeason = paramDocument;
    Object localObject = null;
    Iterator localIterator = paramList.iterator();
    if (localIterator.hasNext())
    {
      Document localDocument = (Document)localIterator.next();
      EpisodeSnippet localEpisodeSnippet = (EpisodeSnippet)LayoutInflater.from(getContext()).inflate(2130968686, this.mEpisodesContainer, false);
      boolean bool1 = LibraryUtils.isOwned(localDocument, FinskyApp.get().getLibraries());
      boolean bool2 = ((Set)this.mEpisodesInLibraryFromFirstLoad.second).contains(localDocument.getDocId());
      if ((bool1) && (!bool2));
      for (boolean bool3 = true; ; bool3 = false)
      {
        localEpisodeSnippet.setEpisodeDetails(this.mSelectedSeason, localDocument, this.mBitmapLoader, this.mNavigationManager, bool3, this.mPageFragment, this.mReferrerUrl, this.mReferrerListCookie, this);
        this.mEpisodesContainer.addView(localEpisodeSnippet);
        this.mEpisodeSnippets.put(localDocument.getDocId(), localEpisodeSnippet);
        if (!localDocument.getDocId().equals(this.mEpisodeIdFromBundle))
          break;
        localObject = localEpisodeSnippet;
        break;
      }
    }
    if (localObject != null)
      localObject.expand();
    if (this.mSelectedSeason.isInProgressSeason())
      this.mEpisodesContainer.addView(LayoutInflater.from(getContext()).inflate(2130968716, this.mEpisodesContainer, false));
    this.mEpisodeIdFromBundle = null;
  }

  private void shouldEnableLoadingOverlay(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mLoadingOverlay.setVisibility(0);
      this.mLoadingOverlay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        }
      });
    }
    while (true)
    {
      return;
      this.mLoadingOverlay.setVisibility(4);
      this.mLoadingOverlay.setOnClickListener(null);
    }
  }

  private void updateSeasonBuyButton()
  {
    boolean bool = this.mDfeApi.getApiContext().getExperiments().isEnabled("cl:billing.show_tv_season_price_in_button");
    Resources localResources = getResources();
    Button localButton = this.mBuyButton;
    Document localDocument = this.mSelectedSeason;
    if (bool);
    for (int i = -1; ; i = 2131165760)
    {
      EpisodeSnippet.updateBuyButtonState(localResources, localButton, null, null, null, localDocument, i, false);
      this.mBuyButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpisodeSnippet.handleBuyButtonClick(EpisodeList.this.mNavigationManager, EpisodeList.this.mSelectedSeason, EpisodeList.this.mPageFragment);
        }
      });
      return;
    }
  }

  public String getSelectedEpisodeId()
  {
    String str;
    if (this.mEpisodeSnippets != null)
    {
      Iterator localIterator = this.mEpisodeSnippets.keySet().iterator();
      do
      {
        if (!localIterator.hasNext())
          break;
        str = (String)localIterator.next();
      }
      while (!((EpisodeSnippet)this.mEpisodeSnippets.get(str)).isExpanded());
    }
    while (true)
    {
      return str;
      str = null;
    }
  }

  public String getSelectedSeasonId()
  {
    if (this.mSelectedSeason != null);
    for (String str = this.mSelectedSeason.getDocId(); ; str = null)
      return str;
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onCollapsedStateChanged(EpisodeSnippet paramEpisodeSnippet, boolean paramBoolean)
  {
    Iterator localIterator = this.mEpisodeSnippets.values().iterator();
    while (localIterator.hasNext())
    {
      EpisodeSnippet localEpisodeSnippet = (EpisodeSnippet)localIterator.next();
      if (localEpisodeSnippet != paramEpisodeSnippet)
        localEpisodeSnippet.collapseWithoutNotifyingListeners();
    }
  }

  public void onDataChanged()
  {
    int i = 0;
    if (this.mSelectedSeasonRequest.getBucketCount() != 0);
    HashSet localHashSet;
    ArrayList localArrayList;
    for (String str = ((Bucket)this.mSelectedSeasonRequest.getBucketList().get(0)).getAnalyticsCookie(); ; str = null)
    {
      FinskyApp.get().getAnalytics().logListViewOnPage(this.mReferrerUrl, this.mReferrerListCookie, this.mSelectedSeasonRequestUrl, str);
      FinskyApp.get().getEventLogger().logView(this.mCurrentPageUrl, str, this.mSelectedSeasonRequestUrl);
      shouldEnableLoadingOverlay(false);
      if ((this.mEpisodesInLibraryFromFirstLoad == null) || (!((String)this.mEpisodesInLibraryFromFirstLoad.first).equals(this.mSelectedSeason.getDocId())))
        i = 1;
      localHashSet = Sets.newHashSet();
      localArrayList = Lists.newArrayList();
      for (int j = 0; j < this.mSelectedSeasonRequest.getCount(); j++)
      {
        Document localDocument = (Document)this.mSelectedSeasonRequest.getItem(j);
        localArrayList.add(localDocument);
        if ((i != 0) && (LibraryUtils.isOwned(localDocument, this.mLibraries)))
          localHashSet.add(localDocument.getDocId());
      }
    }
    if (i != 0)
      this.mEpisodesInLibraryFromFirstLoad = new Pair(this.mSelectedSeason.getDocId(), localHashSet);
    setEpisodeList(this.mSelectedSeason, localArrayList);
  }

  protected void onDetachedFromWindow()
  {
    if (this.mLibraries != null)
      this.mLibraries.removeListener(this);
    if (this.mSelectedSeasonRequest != null)
    {
      this.mSelectedSeasonRequest.removeDataChangedListener(this);
      this.mSelectedSeasonRequest.removeErrorListener(this);
    }
    this.mSelectedSeasonRequest = null;
    super.onDetachedFromWindow();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    shouldEnableLoadingOverlay(false);
    if ((this.mOldSeason != null) && (this.mOldSeason != this.mSelectedSeason))
    {
      this.mSelectedSeason = this.mOldSeason;
      this.mSeasonSpinner.setSelection(this.mSeasonList.indexOf(this.mOldSeason));
    }
    Toast.makeText(getContext(), ErrorStrings.get(getContext(), paramVolleyError), 0).show();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mEpisodesContainer = ((LinearLayout)findViewById(2131231001));
    this.mLoadingOverlay = findViewById(2131231002);
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mOldSeason = this.mSelectedSeason;
    this.mSelectedSeason = ((Document)paramAdapterView.getAdapter().getItem(paramInt));
    updateSeasonBuyButton();
    shouldEnableLoadingOverlay(true);
    if (this.mSelectedSeasonRequest != null)
    {
      this.mSelectedSeasonRequest.removeDataChangedListener(this);
      this.mSelectedSeasonRequest.removeErrorListener(this);
    }
    this.mSelectedSeasonRequest = new DfeList(this.mDfeApi, this.mSelectedSeason.getCoreContentListUrl(), false);
    this.mSelectedSeasonRequest.addDataChangedListener(this);
    this.mSelectedSeasonRequest.addErrorListener(this);
    this.mSelectedSeasonRequest.startLoadItems();
    this.mSelectedSeasonRequestUrl = this.mSelectedSeason.getCoreContentListUrl();
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    if (this.mSelectedSeason != null)
    {
      updateSeasonBuyButton();
      onDataChanged();
    }
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }

  public void restoreInstanceState(Bundle paramBundle)
  {
    if (!TextUtils.isEmpty(paramBundle.getString("SeasonListViewBinder.SelectedSeasonId")))
      this.mSeasonIdFromBundle = paramBundle.getString("SeasonListViewBinder.SelectedSeasonId");
    if (!TextUtils.isEmpty(paramBundle.getString("SeasonListViewBinder.SelectedEpisodeId")))
      this.mEpisodeIdFromBundle = paramBundle.getString("SeasonListViewBinder.SelectedEpisodeId");
    ArrayList localArrayList = paramBundle.getStringArrayList("SeasonListViewBinder.OwnedEpisodes");
    if (localArrayList != null)
      this.mEpisodesInLibraryFromFirstLoad = new Pair(this.mSeasonIdFromBundle, new HashSet(localArrayList));
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    if (!TextUtils.isEmpty(getSelectedSeasonId()))
      paramBundle.putString("SeasonListViewBinder.SelectedSeasonId", getSelectedSeasonId());
    if (!TextUtils.isEmpty(getSelectedEpisodeId()))
      paramBundle.putString("SeasonListViewBinder.SelectedEpisodeId", getSelectedEpisodeId());
    if ((this.mEpisodesInLibraryFromFirstLoad != null) && (this.mEpisodesInLibraryFromFirstLoad.second != null))
      paramBundle.putStringArrayList("SeasonListViewBinder.OwnedEpisodes", new ArrayList((Set)this.mEpisodesInLibraryFromFirstLoad.second));
  }

  public void setSeasonList(PageFragment paramPageFragment, DfeApi paramDfeApi, Libraries paramLibraries, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Bundle paramBundle, List<Document> paramList, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      hideUi();
    while (true)
    {
      return;
      this.mSeasonList = paramList;
      this.mLibraries = paramLibraries;
      this.mPageFragment = paramPageFragment;
      this.mDfeApi = paramDfeApi;
      this.mNavigationManager = paramNavigationManager;
      this.mBitmapLoader = paramBitmapLoader;
      this.mReferrerUrl = paramString3;
      this.mReferrerListCookie = paramString4;
      this.mCurrentPageUrl = paramString5;
      this.mLibraries.removeListener(this);
      this.mLibraries.addListener(this);
      setDefaultSelectionState(paramString1, paramString2);
      this.mSeasonSpinner = ((Spinner)findViewById(2131231000));
      this.mSeasonSpinner.setAdapter(new SeasonListAdapter(getContext(), this.mSeasonList));
      this.mSeasonSpinner.setOnItemSelectedListener(this);
      this.mSelectedSeason = ((Document)this.mSeasonList.get(0));
      if (!TextUtils.isEmpty(this.mSeasonIdFromBundle))
      {
        Iterator localIterator = this.mSeasonList.iterator();
        while (localIterator.hasNext())
        {
          Document localDocument = (Document)localIterator.next();
          if (localDocument.getDocId().equals(this.mSeasonIdFromBundle))
            this.mSelectedSeason = localDocument;
        }
      }
      this.mSeasonSpinner.setSelection(this.mSeasonList.indexOf(this.mSelectedSeason));
      if (this.mSeasonList.size() == 1)
      {
        this.mSeasonSpinner.setEnabled(false);
        this.mSeasonSpinner.setBackgroundResource(0);
      }
      this.mBuyButton = ((Button)findViewById(2131230897));
      updateSeasonBuyButton();
    }
  }

  private class SeasonListAdapter extends ArrayAdapter<Document>
  {
    public SeasonListAdapter(List<Document> arg2)
    {
      super(2130968835, localObject.toArray(new Document[0]));
    }

    private String getSeasonTitle(Document paramDocument)
    {
      return paramDocument.getTitle().toUpperCase() + " ";
    }

    public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = LayoutInflater.from(getContext()).inflate(2130968844, paramViewGroup, false);
      Document localDocument = (Document)getItem(paramInt);
      ((TextView)paramView.findViewById(2131231252)).setText(getSeasonTitle((Document)getItem(paramInt)));
      if (localDocument == EpisodeList.this.mSelectedSeason)
        paramView.setBackgroundResource(2131361840);
      while (true)
      {
        return paramView;
        paramView.setBackgroundResource(2130837602);
      }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      View localView = LayoutInflater.from(getContext()).inflate(2130968836, paramViewGroup, false);
      Document localDocument = (Document)getItem(paramInt);
      TextView localTextView = (TextView)localView;
      localTextView.setText(getSeasonTitle(localDocument));
      localTextView.setTextColor(EpisodeList.this.mCorpusColor);
      return localView;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EpisodeList
 * JD-Core Version:    0.6.2
 */
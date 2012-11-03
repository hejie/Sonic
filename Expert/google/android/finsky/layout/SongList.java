package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocDetails.ArtistDetails;
import com.google.android.finsky.remoting.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SongList extends LinearLayout
{
  private final int mCorpusColor;
  private String mHighlightedSongDocId;
  private ScrollView mParent;
  private final Runnable mScrollRunnable = new Runnable()
  {
    public void run()
    {
      SongSnippet localSongSnippet = (SongSnippet)SongList.this.mSongSnippets.get(SongList.this.mHighlightedSongDocId);
      if (localSongSnippet == null);
      while (true)
      {
        return;
        localSongSnippet.setState(2);
        if (SongList.this.mParent == null)
        {
          FinskyLog.d("Unable to scroll the highlighted song into view.", new Object[0]);
        }
        else
        {
          Rect localRect = new Rect();
          if (!localSongSnippet.getLocalVisibleRect(localRect))
            SongList.this.mParent.scrollTo(localRect.left, localRect.top);
        }
      }
    }
  };
  private final Map<String, SongSnippet> mSongSnippets = Maps.newHashMap();
  private LinearLayout mSongsContainer;

  public SongList(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mCorpusColor = CorpusResourceUtils.getBackendHintColor(paramContext, 2);
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

  private void highlightSong()
  {
    if (this.mSongSnippets.containsKey(this.mHighlightedSongDocId))
    {
      Iterator localIterator = this.mSongSnippets.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((SongSnippet)this.mSongSnippets.get(str)).setState(0);
      }
      post(this.mScrollRunnable);
    }
  }

  private boolean shouldShowArtistNames(Document paramDocument, List<Document> paramList)
  {
    String str;
    if ((paramDocument != null) && (paramDocument.getDisplayArtist() != null))
    {
      str = paramDocument.getDisplayArtist().getName();
      Iterator localIterator = paramList.iterator();
      do
        if (!localIterator.hasNext())
          break;
      while (TextUtils.equals(str, ((Document)localIterator.next()).getCreator()));
    }
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      str = ((Document)paramList.get(0)).getCreator();
      break;
    }
  }

  protected void onDetachedFromWindow()
  {
    removeCallbacks(this.mScrollRunnable);
    super.onDetachedFromWindow();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSongsContainer = ((LinearLayout)findViewById(2131231090));
  }

  public void setHighlightedSong(String paramString, ScrollView paramScrollView)
  {
    this.mHighlightedSongDocId = paramString;
    this.mParent = paramScrollView;
  }

  public void setListDetails(NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, Document paramDocument, List<Document> paramList, boolean paramBoolean, Set<String> paramSet)
  {
    if (!this.mSongSnippets.isEmpty())
    {
      this.mSongSnippets.clear();
      this.mSongsContainer.removeAllViews();
    }
    if ((paramList == null) || (paramList.isEmpty()))
      hideUi();
    while (true)
    {
      return;
      boolean bool1 = shouldShowArtistNames(paramDocument, paramList);
      ((TextView)findViewById(2131230895)).setTextColor(this.mCorpusColor);
      final PlaylistControlButtons localPlaylistControlButtons = (PlaylistControlButtons)findViewById(2131231089);
      localPlaylistControlButtons.setDocuments(paramList);
      findViewById(2131231088).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localPlaylistControlButtons.onClick(paramAnonymousView);
        }
      });
      LayoutInflater localLayoutInflater = LayoutInflater.from(getContext());
      Libraries localLibraries = FinskyApp.get().getLibraries();
      int i = 0;
      if (i < paramList.size())
      {
        Document localDocument = (Document)paramList.get(i);
        SongSnippet localSongSnippet = (SongSnippet)localLayoutInflater.inflate(2130968731, this, false);
        int j;
        if (paramBoolean)
        {
          j = localDocument.getSongDetails().getTrackNumber();
          label185: boolean bool2 = LibraryUtils.isOwned(localDocument, localLibraries);
          boolean bool3 = paramSet.contains(localDocument.getDocId());
          if ((!bool2) || (bool3))
            break label288;
        }
        label288: for (boolean bool4 = true; ; bool4 = false)
        {
          localSongSnippet.setSongDetails(paramBitmapLoader, paramDocument, localDocument, j, bool1, paramNavigationManager, bool4);
          this.mSongsContainer.addView(localSongSnippet);
          this.mSongSnippets.put(localDocument.getDocId(), localSongSnippet);
          if (i == 0)
            localSongSnippet.initialize();
          i++;
          break;
          j = i + 1;
          break label185;
        }
      }
      highlightSong();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SongList
 * JD-Core Version:    0.6.2
 */
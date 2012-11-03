package com.google.android.finsky.activities;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SongListViewBinder extends DetailsViewBinder
  implements Response.ErrorListener, OnDataChangedListener, Libraries.Listener
{
  private Document mAlbumDoc;
  private BitmapLoader mBitmapLoader;
  private Set<String> mInitialDocumentsOwned = null;
  private DfeList mItemListRequest;
  private Libraries mLibraries;
  private int mNumSongsToShow;
  private boolean mUseActualTrackNumbers;

  private void detachRequestListeners()
  {
    if (this.mItemListRequest != null)
    {
      this.mItemListRequest.removeDataChangedListener(this);
      this.mItemListRequest.removeErrorListener(this);
    }
  }

  private void populateFromLibrary(Library paramLibrary)
  {
    SongList localSongList = (SongList)this.mLayout;
    ArrayList localArrayList = Lists.newArrayList();
    int i = Math.min(this.mNumSongsToShow, this.mItemListRequest.getCount());
    if (this.mInitialDocumentsOwned == null);
    for (int j = 1; ; j = 0)
    {
      if (j != 0)
        this.mInitialDocumentsOwned = Sets.newHashSet();
      for (int k = 0; k < i; k++)
      {
        Document localDocument = (Document)this.mItemListRequest.getItem(k);
        localArrayList.add(localDocument);
        if ((j != 0) && (LibraryUtils.isOwned(localDocument, paramLibrary)))
          this.mInitialDocumentsOwned.add(localDocument.getDocId());
      }
    }
    localSongList.setListDetails(this.mNavigationManager, this.mBitmapLoader, this.mAlbumDoc, localArrayList, this.mUseActualTrackNumbers, this.mInitialDocumentsOwned);
  }

  public void bind(SongList paramSongList, Document paramDocument, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, int paramInt, boolean paramBoolean2, Libraries paramLibraries, BitmapLoader paramBitmapLoader)
  {
    super.bind(paramSongList, paramString1, 2);
    this.mLibraries = paramLibraries;
    this.mBitmapLoader = paramBitmapLoader;
    if (!paramBoolean2)
    {
      this.mLayout.setVisibility(8);
      return;
    }
    this.mLayout.setVisibility(0);
    TextView localTextView = (TextView)this.mLayout.findViewById(2131230896);
    if (TextUtils.isEmpty(paramString2))
      localTextView.setVisibility(8);
    while (true)
    {
      this.mAlbumDoc = paramDocument;
      this.mNumSongsToShow = paramInt;
      detachRequestListeners();
      this.mItemListRequest = new DfeList(this.mDfeApi, paramString3, false);
      this.mItemListRequest.addDataChangedListener(this);
      this.mItemListRequest.addErrorListener(this);
      this.mItemListRequest.startLoadItems();
      this.mUseActualTrackNumbers = paramBoolean1;
      this.mLibraries.removeListener(this);
      this.mLibraries.addListener(this);
      break;
      localTextView.setVisibility(0);
      localTextView.setText(Html.fromHtml(paramString2));
    }
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onDataChanged()
  {
    populateFromLibrary(this.mLibraries);
  }

  public void onDestroyView()
  {
    if (this.mLibraries != null)
      this.mLibraries.removeListener(this);
    detachRequestListeners();
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    Toast.makeText(this.mContext, ErrorStrings.get(this.mContext, paramVolleyError), 0).show();
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    populateFromLibrary(paramAccountLibrary);
  }

  public void restoreInstanceState(Bundle paramBundle)
  {
    String[] arrayOfString = paramBundle.getStringArray("SongListViewBinder.InitialDocumentsOwned");
    if (arrayOfString != null)
    {
      this.mInitialDocumentsOwned = Sets.newHashSet();
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfString[j];
        this.mInitialDocumentsOwned.add(str);
      }
    }
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    String[] arrayOfString = null;
    if (this.mInitialDocumentsOwned != null)
    {
      int i = this.mInitialDocumentsOwned.size();
      arrayOfString = (String[])this.mInitialDocumentsOwned.toArray(new String[i]);
    }
    paramBundle.putStringArray("SongListViewBinder.InitialDocumentsOwned", arrayOfString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SongListViewBinder
 * JD-Core Version:    0.6.2
 */
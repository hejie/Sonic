package com.google.android.finsky.providers;

import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;

public class QSBSuggestionsProvider extends SearchRecentSuggestionsProvider
{
  public QSBSuggestionsProvider()
  {
    setupSuggestions("com.google.android.finsky.QSBSuggestionsProvider2", 1);
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if ((paramArrayOfString2 == null) || (paramArrayOfString2.length == 0))
      throw new IllegalArgumentException("SelectionArgs must be provided for the Uri: " + paramUri);
    String str = paramArrayOfString2[0].toLowerCase();
    return new RecentSuggestionsProvider.SearchSuggestor(getContext(), str, true, true, 0).gatherSuggestions();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.providers.QSBSuggestionsProvider
 * JD-Core Version:    0.6.2
 */
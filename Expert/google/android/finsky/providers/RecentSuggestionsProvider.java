package com.google.android.finsky.providers;

import android.content.Context;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.DocUtils;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecentSuggestionsProvider extends SearchRecentSuggestionsProvider
{
  private static Boolean mIsGoogleTV = null;
  private static int sCurrentBackendId;

  public RecentSuggestionsProvider()
  {
    setupSuggestions("com.google.android.finsky.RecentSuggestionsProvider", 1);
  }

  public static void setCurrentBackendId(int paramInt)
  {
    sCurrentBackendId = paramInt;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if ((paramArrayOfString2 == null) || (paramArrayOfString2.length == 0))
      throw new IllegalArgumentException("SelectionArgs must be provided for the Uri: " + paramUri);
    String str = paramArrayOfString2[0].toLowerCase();
    HistorySearchSuggestor localHistorySearchSuggestor = new HistorySearchSuggestor(str, super.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2), getContext());
    switch (sCurrentBackendId)
    {
    default:
    case 2:
    }
    for (Object localObject = new SearchSuggestor(getContext(), sCurrentBackendId, str); ; localObject = new ThirdPartySearchSuggestor(sCurrentBackendId, str, getContext()))
    {
      Cursor[] arrayOfCursor = new Cursor[2];
      arrayOfCursor[0] = localHistorySearchSuggestor.gatherSuggestions();
      arrayOfCursor[1] = ((AsyncSuggestionAuthority)localObject).gatherSuggestions();
      return new MergeCursor(arrayOfCursor);
    }
  }

  static abstract class AsyncSuggestionAuthority
  {
    protected final String[] COLUMNS = { "_id", "suggest_icon_1", "suggest_text_1", "suggest_text_2", "suggest_intent_query", "suggest_intent_action", "suggest_intent_data" };
    protected final Context mContext;
    protected final String mQuery;
    private final MatrixCursor mResults = new MatrixCursor(this.COLUMNS);

    public AsyncSuggestionAuthority(String paramString, Context paramContext)
    {
      this.mQuery = paramString;
      this.mContext = paramContext;
    }

    protected void addRow(int paramInt, Object paramObject, String paramString)
    {
      addRow(paramInt, paramObject, paramString, null, "android.intent.action.SEARCH", null);
    }

    protected void addRow(int paramInt, Object paramObject, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      Object[] arrayOfObject = new Object[this.COLUMNS.length];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramObject;
      arrayOfObject[2] = paramString1;
      arrayOfObject[3] = paramString2;
      arrayOfObject[4] = paramString1;
      arrayOfObject[5] = paramString3;
      arrayOfObject[6] = paramString4;
      this.mResults.addRow(arrayOfObject);
    }

    public Cursor gatherSuggestions()
    {
      final Semaphore localSemaphore = new Semaphore(0);
      makeRequest(new RecentSuggestionsProvider.OnCompleteListener()
      {
        public void onComplete()
        {
          localSemaphore.release();
        }
      });
      try
      {
        localSemaphore.acquire();
        localMatrixCursor = this.mResults;
        return localMatrixCursor;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          MatrixCursor localMatrixCursor = this.mResults;
      }
    }

    protected String getCarrierCountry()
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)this.mContext.getSystemService("phone");
      String str2;
      if (localTelephonyManager != null)
      {
        str2 = localTelephonyManager.getNetworkCountryIso();
        if (str2 == null);
      }
      for (String str1 = str2.toUpperCase(); ; str1 = "")
        return str1;
    }

    protected String getCountry()
    {
      return Locale.getDefault().getCountry();
    }

    protected String getLanguage()
    {
      return Locale.getDefault().getLanguage();
    }

    protected abstract void makeRequest(RecentSuggestionsProvider.OnCompleteListener paramOnCompleteListener);
  }

  static class HistorySearchSuggestor extends RecentSuggestionsProvider.AsyncSuggestionAuthority
  {
    private final Cursor mCursor;

    public HistorySearchSuggestor(String paramString, Cursor paramCursor, Context paramContext)
    {
      super(paramContext);
      this.mCursor = paramCursor;
    }

    protected void makeRequest(RecentSuggestionsProvider.OnCompleteListener paramOnCompleteListener)
    {
      int i = 0;
      int j = 0;
      int k = 0;
      String[] arrayOfString = this.mCursor.getColumnNames();
      int m = arrayOfString.length;
      int n = 0;
      if (n < m)
      {
        String str2 = arrayOfString[n];
        if (str2.equalsIgnoreCase("_id"))
          j = i;
        while (true)
        {
          i++;
          n++;
          break;
          if (str2.equalsIgnoreCase("suggest_text_1"))
            k = i;
        }
      }
      this.mCursor.moveToPosition(0);
      while (!this.mCursor.isAfterLast())
      {
        String str1 = this.mCursor.getString(k);
        if (str1.startsWith(this.mQuery))
          addRow(this.mCursor.getInt(j), Integer.valueOf(17301578), str1);
        this.mCursor.moveToNext();
      }
      this.mCursor.close();
      paramOnCompleteListener.onComplete();
    }
  }

  private static abstract interface OnCompleteListener
  {
    public abstract void onComplete();
  }

  static class SearchSuggestor extends RecentSuggestionsProvider.AsyncSuggestionAuthority
  {
    private static String[] BACKEND_ID_TO_ICON_PACKAGE_NAME = { null, "com.google.android.apps.books", "com.google.android.music", null, "com.google.android.videos", null, "com.google.android.apps.magazines", "com.google.android.videos" };
    private static final Uri BASE_URI = Uri.parse("https://market.android.com/suggest/SuggRequest");
    private final boolean mAppsOnly;
    private final String mBackendId;
    private final String mBlobSize;
    private final String mDescription;

    public SearchSuggestor(Context paramContext, int paramInt, String paramString)
    {
      this(paramContext, paramString, false, false, paramInt);
    }

    public SearchSuggestor(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
    {
      super(paramContext);
      if (paramBoolean1);
      for (String str = this.mContext.getResources().getText(2131165393).toString(); ; str = null)
      {
        this.mDescription = str;
        this.mAppsOnly = paramBoolean2;
        this.mBackendId = Integer.toString(paramInt);
        this.mBlobSize = Integer.toString(paramContext.getResources().getDimensionPixelSize(2131427412));
        return;
      }
    }

    private Uri getBackendCanonicalIconUri(int paramInt)
    {
      try
      {
        String str = BACKEND_ID_TO_ICON_PACKAGE_NAME[paramInt];
        Intent localIntent = this.mContext.getPackageManager().getLaunchIntentForPackage(str);
        int i = ((ResolveInfo)this.mContext.getPackageManager().queryIntentActivities(localIntent, 65536).get(0)).activityInfo.applicationInfo.icon;
        if (i != 0)
        {
          Uri localUri2 = new Uri.Builder().scheme("android.resource").authority(str).path(Integer.toString(i)).build();
          localUri1 = localUri2;
          return localUri1;
        }
      }
      catch (Exception localException)
      {
        while (true)
          Uri localUri1 = null;
      }
    }

    private Uri getIconUri(JSONObject paramJSONObject, String paramString, final RefCountedOnCompleteListener paramRefCountedOnCompleteListener)
    {
      int i;
      Uri localUri;
      if ((!paramJSONObject.has("b")) && (!paramJSONObject.has("u")))
      {
        i = 1;
        int j = DocUtils.docidToBackend(paramString);
        if (i == 0)
          break label49;
        localUri = getBackendCanonicalIconUri(j);
      }
      while (true)
      {
        return localUri;
        i = 0;
        break;
        label49: paramRefCountedOnCompleteListener.addProducer();
        localUri = AppIconProvider.CONTENT_URI.buildUpon().appendPath(paramString).build();
        AppIconProvider.AppIconLoader localAppIconLoader = new AppIconProvider.AppIconLoader(this.mContext, paramString);
        AppIconProvider.TimedOnCompleteListener local1 = new AppIconProvider.TimedOnCompleteListener()
        {
          protected void onComplete()
          {
            paramRefCountedOnCompleteListener.onComplete();
          }
        };
        try
        {
          if (!paramJSONObject.has("b"))
            break label128;
          localAppIconLoader.loadToFileFromBlob(paramJSONObject.getString("b"), local1);
        }
        catch (JSONException localJSONException)
        {
          local1.onComplete();
        }
        continue;
        label128: localAppIconLoader.loadToFileFromUrl(paramJSONObject.getString("u"), local1);
      }
    }

    private boolean isGoogleTv()
    {
      if (RecentSuggestionsProvider.mIsGoogleTV == null)
        RecentSuggestionsProvider.access$002(Boolean.valueOf(this.mContext.getPackageManager().hasSystemFeature("com.google.android.tv")));
      return RecentSuggestionsProvider.mIsGoogleTV.booleanValue();
    }

    protected void makeRequest(RecentSuggestionsProvider.OnCompleteListener paramOnCompleteListener)
    {
      Uri.Builder localBuilder = BASE_URI.buildUpon().appendQueryParameter("query", this.mQuery).appendQueryParameter("json", "1").appendQueryParameter("blob", "1").appendQueryParameter("blob_sz", this.mBlobSize).appendQueryParameter("hl", getLanguage()).appendQueryParameter("gl", getCountry()).appendQueryParameter("ct", getCarrierCountry()).appendQueryParameter("c", this.mBackendId);
      if ((!isGoogleTv()) || (((Boolean)G.gtvNavigationalSuggestEnabled.get()).booleanValue()))
        localBuilder.appendQueryParameter("type", "aq");
      if (TextUtils.isEmpty(this.mQuery))
        paramOnCompleteListener.onComplete();
      while (true)
      {
        return;
        final RefCountedOnCompleteListener localRefCountedOnCompleteListener = new RefCountedOnCompleteListener(paramOnCompleteListener);
        JsonArrayRequest localJsonArrayRequest = new JsonArrayRequest(localBuilder.build().toString(), new Response.Listener()
        {
          public void onResponse(JSONArray paramAnonymousJSONArray)
          {
            for (int i = 0; ; i++)
              while (true)
              {
                try
                {
                  if (i < paramAnonymousJSONArray.length())
                  {
                    JSONObject localJSONObject = paramAnonymousJSONArray.getJSONObject(i);
                    String str1 = localJSONObject.getString("s");
                    if ((localJSONObject.has("t")) && (localJSONObject.has("p")) && (localJSONObject.getString("t").equals("a")))
                    {
                      String str2 = localJSONObject.getString("p");
                      Uri localUri = RecentSuggestionsProvider.SearchSuggestor.this.getIconUri(localJSONObject, str2, localRefCountedOnCompleteListener);
                      String str3 = RecentSuggestionsProvider.SearchSuggestor.this.mDescription;
                      if ((RecentSuggestionsProvider.SearchSuggestor.this.mDescription == null) && (localJSONObject.has("d")))
                        str3 = localJSONObject.getString("d");
                      RecentSuggestionsProvider.SearchSuggestor.this.addRow(i, localUri, str1, str3, "com.google.android.finsky.NAVIGATIONAL_SUGGESTION", "https://market.android.com/details?id=" + str2 + "&feature=sugg");
                    }
                    else if (!RecentSuggestionsProvider.SearchSuggestor.this.mAppsOnly)
                    {
                      RecentSuggestionsProvider.SearchSuggestor.this.addRow(i, Integer.valueOf(17301583), str1);
                    }
                  }
                }
                catch (JSONException localJSONException)
                {
                  localRefCountedOnCompleteListener.onComplete();
                  return;
                }
                localRefCountedOnCompleteListener.onComplete();
              }
          }
        }
        , new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            localRefCountedOnCompleteListener.onComplete();
          }
        });
        FinskyApp.get().getRequestQueue().add(localJsonArrayRequest);
      }
    }

    private static class RefCountedOnCompleteListener
      implements RecentSuggestionsProvider.OnCompleteListener
    {
      int mRefCount = 1;
      RecentSuggestionsProvider.OnCompleteListener mWrappedListener;

      RefCountedOnCompleteListener(RecentSuggestionsProvider.OnCompleteListener paramOnCompleteListener)
      {
        this.mWrappedListener = paramOnCompleteListener;
      }

      public void addProducer()
      {
        try
        {
          this.mRefCount = (1 + this.mRefCount);
          return;
        }
        finally
        {
          localObject = finally;
          throw localObject;
        }
      }

      public void onComplete()
      {
        try
        {
          int i = this.mRefCount;
          if (i <= 0);
          while (true)
          {
            return;
            this.mRefCount = (-1 + this.mRefCount);
            if (this.mRefCount <= 0)
              this.mWrappedListener.onComplete();
          }
        }
        finally
        {
        }
      }
    }
  }

  static class ThirdPartySearchSuggestor extends RecentSuggestionsProvider.AsyncSuggestionAuthority
  {
    final Uri BASE_URI = Uri.parse("http://www.google.com/complete/search");
    private String mRequestUrl;

    public ThirdPartySearchSuggestor(int paramInt, String paramString, Context paramContext)
    {
      super(paramContext);
      this.mRequestUrl = constructUrl(paramInt);
    }

    private String constructUrl(int paramInt)
    {
      Uri.Builder localBuilder = this.BASE_URI.buildUpon().appendQueryParameter("q", this.mQuery).appendQueryParameter("json", "1").appendQueryParameter("hl", getLanguage()).appendQueryParameter("gl", getCountry());
      if (paramInt == 1)
      {
        localBuilder.appendQueryParameter("ds", "bo");
        localBuilder.appendQueryParameter("client", "books");
      }
      while (true)
      {
        return localBuilder.build().toString();
        if (paramInt == 4)
        {
          localBuilder.appendQueryParameter("ds", "yt_mv");
        }
        else
        {
          localBuilder.appendQueryParameter("ds", "cse");
          localBuilder.appendQueryParameter("client", "partner");
          localBuilder.appendQueryParameter("partnerid", "skyjam-store");
        }
      }
    }

    protected void makeRequest(final RecentSuggestionsProvider.OnCompleteListener paramOnCompleteListener)
    {
      if (TextUtils.isEmpty(this.mQuery))
        paramOnCompleteListener.onComplete();
      while (true)
      {
        return;
        JsonArrayRequest localJsonArrayRequest = new JsonArrayRequest(this.mRequestUrl, new Response.Listener()
        {
          // ERROR //
          public void onResponse(JSONArray paramAnonymousJSONArray)
          {
            // Byte code:
            //   0: aload_1
            //   1: iconst_1
            //   2: invokevirtual 38	org/json/JSONArray:getJSONArray	(I)Lorg/json/JSONArray;
            //   5: astore 4
            //   7: iconst_0
            //   8: istore 5
            //   10: iload 5
            //   12: aload 4
            //   14: invokevirtual 42	org/json/JSONArray:length	()I
            //   17: if_icmpge +30 -> 47
            //   20: aload_0
            //   21: getfield 20	com/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor$1:this$0	Lcom/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor;
            //   24: iload 5
            //   26: ldc 43
            //   28: invokestatic 49	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
            //   31: aload 4
            //   33: iload 5
            //   35: invokevirtual 53	org/json/JSONArray:getString	(I)Ljava/lang/String;
            //   38: invokevirtual 57	com/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor:addRow	(ILjava/lang/Object;Ljava/lang/String;)V
            //   41: iinc 5 1
            //   44: goto -34 -> 10
            //   47: aload_0
            //   48: getfield 22	com/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor$1:val$listener	Lcom/google/android/finsky/providers/RecentSuggestionsProvider$OnCompleteListener;
            //   51: invokeinterface 62 1 0
            //   56: return
            //   57: astore_3
            //   58: aload_0
            //   59: getfield 22	com/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor$1:val$listener	Lcom/google/android/finsky/providers/RecentSuggestionsProvider$OnCompleteListener;
            //   62: invokeinterface 62 1 0
            //   67: goto -11 -> 56
            //   70: astore_2
            //   71: aload_0
            //   72: getfield 22	com/google/android/finsky/providers/RecentSuggestionsProvider$ThirdPartySearchSuggestor$1:val$listener	Lcom/google/android/finsky/providers/RecentSuggestionsProvider$OnCompleteListener;
            //   75: invokeinterface 62 1 0
            //   80: aload_2
            //   81: athrow
            //
            // Exception table:
            //   from	to	target	type
            //   0	41	57	org/json/JSONException
            //   0	41	70	finally
          }
        }
        , new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            paramOnCompleteListener.onComplete();
          }
        });
        FinskyApp.get().getRequestQueue().add(localJsonArrayRequest);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.providers.RecentSuggestionsProvider
 * JD-Core Version:    0.6.2
 */
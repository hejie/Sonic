package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.Authenticator;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.SkyjamJsonObjectRequest;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlusShareSection extends LinearLayout
{
  private Document mAlbumDocument;
  private String mCurrentPageUrl;
  private Fragment mFragment;
  private TextView mHeader;
  private boolean mIsFetching = false;
  private DfeList mItemListRequest;
  private ProgressBar mProgress;
  private JsonObjectRequest mRequest = null;
  private RequestQueue mRequestQueue;
  private final Response.ErrorListener mTrackListErrorListener = new Response.ErrorListener()
  {
    public void onErrorResponse(VolleyError paramAnonymousVolleyError)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramAnonymousVolleyError.getCause();
      FinskyLog.e("Unable to load child documents: %s", arrayOfObject);
      GooglePlusShareSection.this.updateWithDocument(null, GooglePlusShareSection.this.mAlbumDocument);
    }
  };
  private final OnDataChangedListener mTrackListListener = new OnDataChangedListener()
  {
    public void onDataChanged()
    {
      Libraries localLibraries = FinskyApp.get().getLibraries();
      Account localAccount1 = FinskyApp.get().getCurrentAccount();
      for (int i = 0; ; i++)
        if (i < GooglePlusShareSection.this.mItemListRequest.getCount())
        {
          Document localDocument = (Document)GooglePlusShareSection.this.mItemListRequest.getItem(i);
          Account localAccount2 = LibraryUtils.getOwnerWithCurrentAccount(localDocument, localLibraries, localAccount1);
          if (localAccount2 != null)
            GooglePlusShareSection.this.updateWithDocument(localAccount2, localDocument);
        }
        else
        {
          return;
        }
    }
  };
  private final Response.ErrorListener mUnrollErrorListener = new Response.ErrorListener()
  {
    public void onErrorResponse(VolleyError paramAnonymousVolleyError)
    {
      GooglePlusShareSection.access$002(GooglePlusShareSection.this, false);
      GooglePlusShareSection.this.updateUi();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramAnonymousVolleyError.getCause();
      FinskyLog.e("Unable to load JSON: %s", arrayOfObject);
    }
  };

  public GooglePlusShareSection(Context paramContext)
  {
    this(paramContext, null);
  }

  public GooglePlusShareSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private Intent buildBaseShareIntent(Document paramDocument)
  {
    Intent localIntent = new Intent("com.google.android.apps.plus.SHARE_GOOGLE", Uri.parse(paramDocument.getShareUrl()));
    localIntent.putExtra("authAccount", FinskyApp.get().getCurrentAccountName());
    localIntent.putExtra("com.google.android.apps.plus.VERSION", "1.00");
    return localIntent;
  }

  private Response.Listener<JSONObject> createJsonListener(final Document paramDocument)
  {
    return new Response.Listener()
    {
      public void onResponse(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          GooglePlusShareSection.access$002(GooglePlusShareSection.this, false);
          GooglePlusShareSection.this.updateUi();
          String str1 = paramAnonymousJSONObject.getString("externalId");
          String str2 = paramAnonymousJSONObject.getString("url");
          Intent localIntent = GooglePlusShareSection.this.buildBaseShareIntent(paramDocument);
          localIntent.setData(Uri.parse(str2));
          localIntent.putExtra("com.google.android.apps.plus.FOOTER", GooglePlusShareSection.this.getContext().getString(2131165562));
          localIntent.putExtra("com.google.android.apps.plus.EXTERNAL_ID", str1);
          GooglePlusShareSection.this.mFragment.startActivityForResult(localIntent, 0);
          return;
        }
        catch (JSONException localJSONException)
        {
          while (true)
            localJSONException.printStackTrace();
        }
      }
    };
  }

  private boolean isSharingSupported(Document paramDocument)
  {
    boolean bool = false;
    Intent localIntent = buildBaseShareIntent(paramDocument);
    if (!getContext().getPackageManager().queryIntentActivities(localIntent, 0).isEmpty())
      bool = true;
    return bool;
  }

  private boolean isSupportedCorpus()
  {
    if ((this.mAlbumDocument != null) && (this.mAlbumDocument.getBackend() == 2));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void updateUi()
  {
    ProgressBar localProgressBar = this.mProgress;
    if (this.mIsFetching);
    for (int i = 0; ; i = 8)
    {
      localProgressBar.setVisibility(i);
      return;
    }
  }

  private void updateWithDocument(final Account paramAccount, final Document paramDocument)
  {
    if (!isSupportedCorpus())
      return;
    TextView localTextView;
    if (isSharingSupported(paramDocument))
    {
      setVisibility(0);
      localTextView = (TextView)findViewById(2131230896);
      if (paramDocument.getAlbumDetails() == null)
        break label147;
      localTextView.setText(2131165563);
    }
    while (true)
    {
      this.mHeader = ((TextView)findViewById(2131230895));
      int i = CorpusResourceUtils.getBackendHintColor(getContext(), paramDocument.getBackend());
      this.mHeader.setTextColor(i);
      String str = getContext().getString(CorpusResourceUtils.getShareHeaderId(paramDocument.getBackend()));
      this.mHeader.setText(str.toUpperCase());
      this.mProgress = ((ProgressBar)findViewById(2131230943));
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (GooglePlusShareSection.this.mIsFetching);
          while (true)
          {
            return;
            GooglePlusShareSection.access$002(GooglePlusShareSection.this, true);
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = paramDocument.getBackendDocId();
            String str = String.format("https://music.google.com/music/sharepreview?storeId=%s&u=0&source=androidmarket-mobile", arrayOfObject1);
            GooglePlusShareSection.this.updateUi();
            AndroidAuthenticator localAndroidAuthenticator = new AndroidAuthenticator(GooglePlusShareSection.this.getContext(), paramAccount, "sj");
            GooglePlusShareSection.access$202(GooglePlusShareSection.this, new GooglePlusShareSection.AuthorizedSkyjamJsonObjectRequest(localAndroidAuthenticator, str, null, GooglePlusShareSection.this.createJsonListener(paramDocument), GooglePlusShareSection.this.mUnrollErrorListener));
            GooglePlusShareSection.this.mRequestQueue.add(GooglePlusShareSection.this.mRequest);
            FinskyApp.get().getAnalytics().logPageView(null, null, "shareGooglePlus?doc=" + paramDocument.getDocId());
            FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
            Object[] arrayOfObject2 = new Object[4];
            arrayOfObject2[0] = "cidi";
            arrayOfObject2[1] = paramDocument.getDocId();
            arrayOfObject2[2] = "c";
            arrayOfObject2[3] = GooglePlusShareSection.this.mCurrentPageUrl;
            localFinskyEventLog.logTag("+1", arrayOfObject2);
          }
        }
      });
      this.mRequestQueue = FinskyApp.get().getRequestQueue();
      break;
      break;
      label147: if (paramDocument.getSongDetails() != null)
      {
        Context localContext = getContext();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramDocument.getTitle();
        localTextView.setText(localContext.getString(2131165564, arrayOfObject));
      }
    }
  }

  public void bind(Document paramDocument, Fragment paramFragment, String paramString)
  {
    setVisibility(8);
    this.mCurrentPageUrl = paramString;
    if (!((Boolean)G.postPurchaseSharingEnabled.get()).booleanValue());
    while (true)
    {
      return;
      this.mFragment = paramFragment;
      this.mAlbumDocument = paramDocument;
      if (isSupportedCorpus())
      {
        Account localAccount = LibraryUtils.getOwnerWithCurrentAccount(this.mAlbumDocument, FinskyApp.get().getLibraries(), FinskyApp.get().getCurrentAccount());
        if (localAccount == null)
        {
          String str = this.mAlbumDocument.getCoreContentListUrl();
          if (!TextUtils.isEmpty(str))
          {
            if (this.mItemListRequest != null)
            {
              this.mItemListRequest.removeDataChangedListener(this.mTrackListListener);
              this.mItemListRequest.removeErrorListener(this.mTrackListErrorListener);
            }
            this.mItemListRequest = new DfeList(FinskyApp.get().getDfeApi(), str, false);
            this.mItemListRequest.addDataChangedListener(this.mTrackListListener);
            this.mItemListRequest.addErrorListener(this.mTrackListErrorListener);
            this.mItemListRequest.startLoadItems();
          }
        }
        else
        {
          updateWithDocument(localAccount, this.mAlbumDocument);
        }
      }
    }
  }

  public void onDetachedFromWindow()
  {
    if (this.mItemListRequest != null)
    {
      this.mItemListRequest.removeDataChangedListener(this.mTrackListListener);
      this.mItemListRequest.removeErrorListener(this.mTrackListErrorListener);
    }
    super.onDetachedFromWindow();
  }

  private static class AuthorizedSkyjamJsonObjectRequest extends SkyjamJsonObjectRequest
  {
    private final Authenticator mAuthenticator;
    private String mLastAuthToken = null;

    public AuthorizedSkyjamJsonObjectRequest(Authenticator paramAuthenticator, String paramString, JSONObject paramJSONObject, Response.Listener<JSONObject> paramListener, Response.ErrorListener paramErrorListener)
    {
      super(paramJSONObject, paramListener, paramErrorListener);
      this.mAuthenticator = paramAuthenticator;
    }

    public void deliverError(VolleyError paramVolleyError)
    {
      super.deliverError(paramVolleyError);
      if ((this.mLastAuthToken != null) && ((paramVolleyError instanceof AuthFailureError)))
      {
        this.mAuthenticator.invalidateAuthToken(this.mLastAuthToken);
        this.mLastAuthToken = null;
      }
    }

    public Map<String, String> getHeaders()
      throws AuthFailureError
    {
      Map localMap = super.getHeaders();
      this.mLastAuthToken = this.mAuthenticator.getAuthToken();
      if (this.mLastAuthToken == null)
        throw new AuthFailureError("Auth token is null");
      localMap.put("Authorization", "GoogleLogin auth=" + this.mLastAuthToken);
      return localMap;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.GooglePlusShareSection
 * JD-Core Version:    0.6.2
 */
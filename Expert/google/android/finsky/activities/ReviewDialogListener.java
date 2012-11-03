package com.google.android.finsky.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeRateReview;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.OwnedActions;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.remoting.protos.Rev.ReviewResponse;

public class ReviewDialogListener
  implements RateReviewDialog.Listener, ReviewDialog.Listener
{
  private final Context mContext;
  private final DfeDetails mDetailsData;
  private final Document mDoc;
  private final NavigationManager mNavigationManager;
  private final OwnedActions mOwnedActions;
  private final Fragment mParentFragment;
  private final ReviewSamplesViewBinder mReviewSamplesViewBinder;

  public ReviewDialogListener(Context paramContext, NavigationManager paramNavigationManager, Fragment paramFragment, Document paramDocument, DfeDetails paramDfeDetails, ReviewSamplesViewBinder paramReviewSamplesViewBinder, OwnedActions paramOwnedActions)
  {
    this.mContext = paramContext;
    this.mNavigationManager = paramNavigationManager;
    this.mParentFragment = paramFragment;
    this.mDoc = paramDocument;
    this.mReviewSamplesViewBinder = paramReviewSamplesViewBinder;
    this.mDetailsData = paramDfeDetails;
    this.mOwnedActions = paramOwnedActions;
  }

  private void updateUserReview(int paramInt, String paramString1, String paramString2, DocAnnotations.PlusProfile paramPlusProfile)
  {
    boolean bool = true;
    OwnedActions localOwnedActions;
    Document localDocument;
    DfeDetails localDfeDetails;
    NavigationManager localNavigationManager;
    Fragment localFragment;
    if (this.mDoc != null)
    {
      FinskyApp.get().getDfeApi().invalidateDetailsCache(this.mDoc.getDetailsUrl(), bool);
      this.mReviewSamplesViewBinder.invalidateCurrentReviewUrl();
      this.mReviewSamplesViewBinder.refresh();
      if (this.mOwnedActions != null)
        this.mOwnedActions.updateRating(paramInt);
      Rev.Review localReview = this.mDetailsData.getUserReview();
      if (localReview == null)
      {
        localReview = this.mDetailsData.initializeUserReview();
        if (paramPlusProfile != null)
          localReview.setPlusProfile(paramPlusProfile);
      }
      localReview.setStarRating(paramInt);
      localReview.setTitle(paramString1);
      localReview.setComment(paramString2);
      if (this.mOwnedActions != null)
      {
        localOwnedActions = this.mOwnedActions;
        localDocument = this.mDoc;
        localDfeDetails = this.mDetailsData;
        localNavigationManager = this.mNavigationManager;
        localFragment = this.mParentFragment;
        if ((this.mDetailsData == null) || (!this.mDetailsData.isReady()))
          break label186;
      }
    }
    while (true)
    {
      localOwnedActions.setDocument(localDocument, localDfeDetails, localNavigationManager, localFragment, bool);
      return;
      label186: bool = false;
    }
  }

  public void onDeleteReview(String paramString1, String paramString2)
  {
    FinskyApp.get().getDfeApi().deleteReview(paramString1, paramString2, new Response.Listener()
    {
      public void onResponse(Rev.ReviewResponse paramAnonymousReviewResponse)
      {
        ReviewDialogListener.this.updateUserReview(0, "", "", null);
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ReviewDialogListener.this.toast(2131165566, 0);
      }
    });
  }

  public void onRateReview(String paramString1, String paramString2, final RateReviewDialog.CommentRating paramCommentRating)
  {
    DfeRateReview localDfeRateReview = new DfeRateReview(FinskyApp.get().getDfeApi(), paramString1, paramString2, paramCommentRating.getRpcId());
    localDfeRateReview.addDataChangedListener(new OnDataChangedListener()
    {
      public void onDataChanged()
      {
        if (paramCommentRating == RateReviewDialog.CommentRating.SPAM)
        {
          ReviewDialogListener.this.mReviewSamplesViewBinder.invalidateCurrentReviewUrl();
          ReviewDialogListener.this.mReviewSamplesViewBinder.refresh();
        }
      }
    });
    localDfeRateReview.addErrorListener(new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ReviewDialogListener.this.toast(2131165576, 0);
      }
    });
  }

  public void onSaveReview(String paramString1, final int paramInt, final String paramString2, final String paramString3, final DocAnnotations.PlusProfile paramPlusProfile, boolean paramBoolean)
  {
    FinskyApp.get().getDfeApi().addReview(paramString1, paramString2, paramString3, paramInt, paramBoolean, new Response.Listener()
    {
      public void onResponse(Rev.ReviewResponse paramAnonymousReviewResponse)
      {
        ReviewDialogListener.this.updateUserReview(paramInt, paramString2, paramString3, paramPlusProfile);
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ReviewDialogListener.this.toast(2131165565, 0);
      }
    });
  }

  protected void toast(int paramInt1, int paramInt2)
  {
    Toast.makeText(this.mContext, paramInt1, paramInt2).show();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ReviewDialogListener
 * JD-Core Version:    0.6.2
 */
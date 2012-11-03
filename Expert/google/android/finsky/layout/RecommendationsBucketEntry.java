package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.RateSuggestedContentResponse;
import com.google.android.finsky.utils.BitmapLoader;

public class RecommendationsBucketEntry extends OverviewBucketEntry
{
  private View mDismissalButton;
  private View mDismissedOverlay;
  private Document mDocument;
  private TextView mReason;

  public RecommendationsBucketEntry(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public RecommendationsBucketEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public RecommendationsBucketEntry(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void clearState()
  {
    this.mDismissedOverlay.setVisibility(4);
    this.mDismissalButton.setVisibility(4);
    this.mDocument = null;
    this.mReason.setText(null);
    this.mDismissalButton.setOnClickListener(null);
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    super.bind(paramDocument1, paramDocument2, paramBitmapLoader, paramBoolean, paramOnClickListener);
    this.mDocument = paramDocument2;
    this.mReason.setText(paramDocument2.getRecommendationReason());
  }

  public void configureDismissal(final DfeApi paramDfeApi, final OnDismissalListener paramOnDismissalListener, boolean paramBoolean)
  {
    int i = 4;
    View localView1 = this.mDismissedOverlay;
    int j;
    View localView2;
    if (paramBoolean)
    {
      j = 0;
      localView1.setVisibility(j);
      localView2 = this.mDismissalButton;
      if (!paramBoolean)
        break label65;
    }
    while (true)
    {
      localView2.setVisibility(i);
      this.mDismissalButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramDfeApi.rateSuggestedContent(RecommendationsBucketEntry.this.mDocument.getReasonUniqueId(), new Response.Listener()
          {
            public void onResponse(RateSuggestedContentResponse paramAnonymous2RateSuggestedContentResponse)
            {
              RecommendationsBucketEntry.1.this.val$listener.onSuccessfulDismissal(RecommendationsBucketEntry.this.mDocument.getDocId());
            }
          }
          , new Response.ErrorListener()
          {
            public void onErrorResponse(VolleyError paramAnonymous2VolleyError)
            {
            }
          });
        }
      });
      return;
      j = i;
      break;
      label65: i = 0;
    }
  }

  public void dismissCurrentEntry()
  {
    this.mDismissedOverlay.setVisibility(0);
    this.mDismissalButton.setVisibility(4);
  }

  public String getCurrentDocId()
  {
    if (this.mDocument != null);
    for (String str = this.mDocument.getDocId(); ; str = null)
      return str;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mDismissalButton = findViewById(2131231201);
    this.mReason = ((TextView)findViewById(2131231075));
    this.mDismissedOverlay = findViewById(2131231202);
  }

  public void setMockDocument(int paramInt)
  {
    super.setMockDocument(paramInt);
    clearState();
  }

  public void setNoDocument()
  {
    super.setNoDocument();
    clearState();
  }

  public static abstract interface OnDismissalListener
  {
    public abstract void onSuccessfulDismissal(String paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.RecommendationsBucketEntry
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ReviewDialog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;

public class RateReviewSection extends LinearLayout
{
  private RatingBar mMyRatingBar;
  private TextView mMyRatingText;

  public RateReviewSection(Context paramContext)
  {
    super(paramContext);
  }

  public RateReviewSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void configure(final Document paramDocument, final Rev.Review paramReview, final Fragment paramFragment, Libraries paramLibraries)
  {
    updateVisibility(paramLibraries, paramDocument);
    if (getVisibility() != 0)
      return;
    if (paramReview != null);
    for (int i = paramReview.getStarRating(); ; i = -1)
    {
      updateRating(i);
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int i = 1;
          FragmentManager localFragmentManager = paramFragment.getFragmentManager();
          if (localFragmentManager.findFragmentByTag("review_dialog") != null)
            return;
          VendingProtos.GetMarketMetadataResponseProto localGetMarketMetadataResponseProto = FinskyApp.get().getMarketMetadata();
          if ((localGetMarketMetadataResponseProto != null) && (localGetMarketMetadataResponseProto.hasCommentPostEnabled()) && (localGetMarketMetadataResponseProto.getCommentPostEnabled()));
          for (int j = i; ; j = 0)
          {
            if (j != 0)
              i = 2;
            ReviewDialog localReviewDialog = ReviewDialog.newInstance(i, paramDocument.getDocId(), paramReview, paramDocument.getBackend());
            localReviewDialog.setTargetFragment(paramFragment, 0);
            localReviewDialog.show(localFragmentManager, "review_dialog");
            break;
          }
        }
      });
      int j = CorpusResourceUtils.getBackendHintColor(getContext(), paramDocument.getBackend());
      this.mMyRatingText.setTextColor(j);
      setContentDescription(getContext().getString(2131165668));
      break;
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mMyRatingText = ((TextView)findViewById(2131231182));
    this.mMyRatingBar = ((RatingBar)findViewById(2131231183));
  }

  public void updateRating(int paramInt)
  {
    int i = 2131165658;
    if (paramInt > 0)
      i = 2131165659;
    this.mMyRatingBar.setRating(paramInt);
    this.mMyRatingText.setText(getContext().getString(i).toUpperCase());
  }

  public void updateVisibility(Libraries paramLibraries, Document paramDocument)
  {
    if (!DocUtils.canRate(paramLibraries, paramDocument))
      setVisibility(8);
    while (true)
    {
      return;
      setVisibility(0);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.RateReviewSection
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.IntentUtils;
import java.util.Date;

public class ReviewItemHeaderLayout extends LinearLayout
{
  private TextView mAuthor;
  private TextView mDate;
  private TextView mEdited;
  private RatingBar mRating;
  private TextView mSource;
  private View mSourceSection;

  public ReviewItemHeaderLayout(Context paramContext)
  {
    super(paramContext);
  }

  public ReviewItemHeaderLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mRating = ((RatingBar)findViewById(2131231233));
    this.mSourceSection = findViewById(2131231234);
    this.mAuthor = ((TextView)findViewById(2131231235));
    this.mSource = ((TextView)findViewById(2131231236));
    this.mDate = ((TextView)findViewById(2131231237));
    this.mEdited = ((TextView)findViewById(2131231238));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingTop();
    int j = getPaddingBottom();
    int k = i + (getHeight() - i - j) / 2;
    int m = getPaddingLeft();
    if (this.mRating.getVisibility() != 8)
    {
      int i5 = k - this.mRating.getMeasuredHeight() / 2;
      this.mRating.layout(m, i5, m + this.mRating.getMeasuredWidth(), i5 + this.mRating.getMeasuredHeight());
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mRating.getLayoutParams();
      m += this.mRating.getMeasuredWidth() + localLayoutParams.rightMargin;
    }
    int n = k - this.mSourceSection.getMeasuredHeight() / 2;
    this.mSourceSection.layout(m, n, m + this.mSourceSection.getMeasuredWidth(), n + this.mSourceSection.getMeasuredHeight());
    int i1 = m + this.mSourceSection.getMeasuredWidth();
    int i2 = k - this.mDate.getMeasuredHeight() / 2;
    this.mDate.layout(i1, i2, i1 + this.mDate.getMeasuredWidth(), i2 + this.mDate.getMeasuredHeight());
    int i3 = i1 + this.mDate.getMeasuredWidth();
    if (this.mEdited.getVisibility() != 8)
    {
      int i4 = i2 + this.mDate.getMeasuredHeight() - this.mEdited.getMeasuredHeight();
      this.mEdited.layout(i3, i4, i3 + this.mEdited.getMeasuredWidth(), i4 + this.mEdited.getMeasuredHeight());
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt1);
    int k = i - getPaddingLeft() - getPaddingRight();
    this.mSourceSection.measure(0, 0);
    int m = this.mSourceSection.getMeasuredWidth();
    this.mDate.measure(0, 0);
    int n = this.mDate.getMeasuredWidth();
    int i1 = 0;
    if (this.mRating.getVisibility() != 8)
    {
      this.mRating.measure(0, 0);
      LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mRating.getLayoutParams();
      i1 = this.mRating.getMeasuredWidth() + localLayoutParams.rightMargin;
    }
    int i2 = 0;
    if (this.mEdited.getVisibility() != 8)
    {
      this.mEdited.measure(0, 0);
      i2 = this.mEdited.getMeasuredWidth();
    }
    int i3 = i2 + (n + (i1 + m));
    if ((j != 0) && (i3 > k))
      this.mSourceSection.measure(View.MeasureSpec.makeMeasureSpec(k - n - i1 - i2, 1073741824), 0);
    setMeasuredDimension(i, Math.max(Math.max(this.mSourceSection.getMeasuredHeight(), this.mDate.getMeasuredHeight()), Math.max(this.mEdited.getMeasuredHeight(), this.mRating.getMeasuredHeight())));
  }

  public void setReviewInfo(Rev.Review paramReview)
  {
    String str1 = paramReview.getAuthorName();
    String str2 = paramReview.getSource();
    final String str3 = paramReview.getUrl();
    if (!TextUtils.isEmpty(str1))
    {
      this.mAuthor.setText(str1);
      this.mAuthor.setVisibility(0);
      if (TextUtils.isEmpty(str2))
        break label208;
      this.mSource.setText(str2.toUpperCase());
      this.mSource.setVisibility(0);
      this.mSource.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView.getContext().startActivity(IntentUtils.createViewIntentForUrl(Uri.parse(str3)));
        }
      });
      label82: if (!paramReview.hasStarRating())
        break label220;
      this.mRating.setVisibility(0);
      this.mRating.setRating(paramReview.getStarRating());
      label109: if (!paramReview.hasTimestampMsec())
        break label232;
      this.mDate.setText(DateUtils.formatShortDisplayDate(new Date(paramReview.getTimestampMsec())));
      this.mDate.setVisibility(0);
    }
    while (true)
    {
      this.mEdited.setVisibility(8);
      if ((paramReview.hasReplyText()) && (paramReview.hasReplyTimestampMsec()) && (paramReview.hasTimestampMsec()) && (paramReview.getTimestampMsec() > paramReview.getReplyTimestampMsec()))
        this.mEdited.setVisibility(0);
      return;
      this.mAuthor.setVisibility(8);
      break;
      label208: this.mSource.setVisibility(8);
      break label82;
      label220: this.mRating.setVisibility(8);
      break label109;
      label232: this.mDate.setVisibility(8);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ReviewItemHeaderLayout
 * JD-Core Version:    0.6.2
 */
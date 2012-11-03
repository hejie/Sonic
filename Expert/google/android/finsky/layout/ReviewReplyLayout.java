package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.DateUtils;
import java.util.Date;

public class ReviewReplyLayout extends LinearLayout
{
  private boolean mIsExpanded;
  TextView mReplyHeader;
  TextView mReplyText;
  ImageView mReplyToggle;

  public ReviewReplyLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public ReviewReplyLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void disableToggle()
  {
    this.mReplyToggle.setVisibility(8);
    this.mReplyText.setVisibility(0);
    setOnClickListener(null);
  }

  private void enableToggle()
  {
    this.mIsExpanded = false;
    this.mReplyToggle.setVisibility(0);
    this.mReplyText.setVisibility(8);
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = false;
        if (ReviewReplyLayout.this.mIsExpanded)
        {
          ReviewReplyLayout.this.showMoreIndicator();
          ReviewReplyLayout.this.mReplyText.setVisibility(8);
        }
        while (true)
        {
          ReviewReplyLayout localReviewReplyLayout = ReviewReplyLayout.this;
          if (!ReviewReplyLayout.this.mIsExpanded)
            bool = true;
          ReviewReplyLayout.access$002(localReviewReplyLayout, bool);
          return;
          ReviewReplyLayout.this.showLessIndicator();
          ReviewReplyLayout.this.mReplyText.setVisibility(0);
        }
      }
    });
  }

  private void showLessIndicator()
  {
    this.mReplyToggle.setImageResource(2130837652);
  }

  private void showMoreIndicator()
  {
    this.mReplyToggle.setImageResource(2130837648);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mReplyHeader = ((TextView)findViewById(2131231220));
    this.mReplyText = ((TextView)findViewById(2131231221));
    this.mReplyToggle = ((ImageView)findViewById(2131231222));
  }

  public void setReviewInfo(Document paramDocument, Rev.Review paramReview)
  {
    if (!paramReview.hasReplyText())
      setVisibility(8);
    Context localContext = getContext();
    String str1 = paramDocument.getCreator();
    String str2;
    if (paramReview.hasReplyTimestampMsec())
    {
      str2 = DateUtils.formatShortDisplayDate(new Date(paramReview.getReplyTimestampMsec()));
      if ((paramReview.hasTimestampMsec()) && (paramReview.getTimestampMsec() > paramReview.getReplyTimestampMsec()))
      {
        enableToggle();
        showMoreIndicator();
        this.mReplyHeader.setText(Html.fromHtml(localContext.getString(2131165542, new Object[] { str1, str2 })));
      }
    }
    while (true)
    {
      this.mReplyText.setText(paramReview.getReplyText());
      setVisibility(0);
      return;
      disableToggle();
      this.mReplyHeader.setText(Html.fromHtml(localContext.getString(2131165540, new Object[] { str1, str2 })));
      continue;
      disableToggle();
      this.mReplyHeader.setText(localContext.getString(2131165541, new Object[] { str1 }));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ReviewReplyLayout
 * JD-Core Version:    0.6.2
 */
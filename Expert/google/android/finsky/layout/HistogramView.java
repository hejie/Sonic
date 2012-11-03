package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.FinskyLog;
import java.text.NumberFormat;

public class HistogramView extends LinearLayout
{
  private View mAverageBox;
  private TextView mAverageValue;
  private NumberFormat mFloatFormatter = NumberFormat.getNumberInstance();
  private HistogramTable mHistogramTable;
  private NumberFormat mIntegerFormatter;
  private RatingBar mRatingBar;
  private TextView mRatingCount;

  public HistogramView(Context paramContext)
  {
    this(paramContext, null);
  }

  public HistogramView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mFloatFormatter.setMinimumFractionDigits(1);
    this.mFloatFormatter.setMaximumFractionDigits(1);
    this.mIntegerFormatter = NumberFormat.getIntegerInstance();
  }

  public void bind(Document paramDocument)
  {
    if (!paramDocument.hasReviewHistogramData())
    {
      FinskyLog.w("No histogram data received from server", new Object[0]);
      setVisibility(8);
    }
    while (true)
    {
      return;
      setVisibility(0);
      this.mRatingCount.setText(this.mIntegerFormatter.format(paramDocument.getRatingCount()));
      this.mAverageValue.setText(this.mFloatFormatter.format(paramDocument.getStarRating()));
      this.mRatingBar.setRating(paramDocument.getStarRating());
      this.mHistogramTable.setHistogram(paramDocument.getRatingHistogram());
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mAverageBox = findViewById(2131231243);
    this.mHistogramTable = ((HistogramTable)findViewById(2131231248));
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131231243);
    this.mAverageValue = ((TextView)localLinearLayout.findViewById(2131231245));
    this.mRatingBar = ((RatingBar)localLinearLayout.findViewById(2131231246));
    this.mRatingCount = ((TextView)localLinearLayout.findViewById(2131231247));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getHeight();
    int j = this.mAverageBox.getMeasuredWidth();
    int k = this.mHistogramTable.getMeasuredWidth();
    int m = getPaddingLeft();
    int n = getPaddingTop();
    this.mAverageBox.layout(m, n, m + j, n + i);
    int i1 = m + j;
    this.mHistogramTable.layout(i1, n, i1 + k, n + i);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = this.mHistogramTable.getMeasuredWidth();
    int j = this.mHistogramTable.getMaxWidth();
    if (i > j)
      this.mHistogramTable.measure(View.MeasureSpec.makeMeasureSpec(j, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mHistogramTable.getMeasuredHeight(), 1073741824));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.HistogramView
 * JD-Core Version:    0.6.2
 */
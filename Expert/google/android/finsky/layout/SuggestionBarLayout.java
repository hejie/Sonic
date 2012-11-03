package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuggestionBarLayout extends RelativeLayout
{
  private View mSuggestionArrow;
  private TextView mSuggestionLine1;
  private TextView mSuggestionLine2;
  private TextView mSuggestionLineFull;

  public SuggestionBarLayout(Context paramContext)
  {
    super(paramContext);
  }

  public SuggestionBarLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public SuggestionBarLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void bind(String paramString)
  {
    this.mSuggestionLine2.setText(paramString + " ");
    this.mSuggestionLine2.setSelected(true);
    TextView localTextView = this.mSuggestionLineFull;
    Context localContext = this.mSuggestionLineFull.getContext();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = ("&nbsp;" + paramString + "&nbsp;");
    localTextView.setText(Html.fromHtml(localContext.getString(2131165741, arrayOfObject)));
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSuggestionLineFull = ((TextView)findViewById(2131231261));
    this.mSuggestionLine1 = ((TextView)findViewById(2131231262));
    this.mSuggestionLine2 = ((TextView)findViewById(2131231263));
    this.mSuggestionArrow = findViewById(2131231264);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    int j = getPaddingRight();
    int k = getPaddingTop();
    int m = getPaddingBottom();
    int n = k + (getHeight() - k - m) / 2;
    int i1 = this.mSuggestionArrow.getMeasuredHeight();
    int i2 = this.mSuggestionArrow.getMeasuredWidth();
    this.mSuggestionArrow.layout(getWidth() - j - i2, n - i1 / 2, getWidth() - j, n + i1 / 2);
    int i3 = getWidth() - i - j - i2;
    int i4 = this.mSuggestionLineFull.getMeasuredWidth();
    int i5 = this.mSuggestionLine1.getMeasuredWidth();
    int i6 = this.mSuggestionLine2.getMeasuredWidth();
    if (i4 <= i3)
    {
      this.mSuggestionLineFull.setVisibility(0);
      this.mSuggestionLine1.setVisibility(8);
      this.mSuggestionLine2.setVisibility(8);
      int i12 = this.mSuggestionLineFull.getMeasuredHeight();
      this.mSuggestionLineFull.layout(i, n - i12 / 2, i + i4, n + i12 / 2);
    }
    while (true)
    {
      return;
      this.mSuggestionLineFull.setVisibility(8);
      this.mSuggestionLine1.setVisibility(0);
      this.mSuggestionLine2.setVisibility(0);
      int i7 = this.mSuggestionLine1.getMeasuredHeight();
      int i8 = this.mSuggestionLine2.getMeasuredHeight();
      int i9 = ((ViewGroup.MarginLayoutParams)this.mSuggestionLine2.getLayoutParams()).topMargin;
      int i10 = n - (i9 + (i7 + i8)) / 2;
      int i11 = i9 + (i10 + i7);
      this.mSuggestionLine1.layout(i, i10, i + i5, i10 + i7);
      this.mSuggestionLine2.layout(i, i11, i + i6, i11 + i8);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = getPaddingLeft();
    int k = getPaddingRight();
    this.mSuggestionArrow.measure(0, 0);
    int m = View.MeasureSpec.makeMeasureSpec(i - j - k - this.mSuggestionArrow.getMeasuredWidth(), -2147483648);
    this.mSuggestionLineFull.measure(0, 0);
    this.mSuggestionLine1.measure(m, 0);
    this.mSuggestionLine2.measure(m, 0);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SuggestionBarLayout
 * JD-Core Version:    0.6.2
 */
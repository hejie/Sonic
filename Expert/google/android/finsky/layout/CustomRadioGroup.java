package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class CustomRadioGroup extends RadioGroup
{
  private int mStripTabUnderline;
  private int mStripTabUnderlineSelected;
  private Paint mTabUnderlinePaint;
  private Paint mTabUnderlineSelectedPaint;

  public CustomRadioGroup(Context paramContext)
  {
    super(paramContext);
    setWillNotDraw(false);
  }

  public CustomRadioGroup(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setWillNotDraw(false);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mTabUnderlinePaint == null);
    while (true)
    {
      return;
      int i = getHeight() - this.mStripTabUnderline / 2;
      paramCanvas.drawLine(0.0F, i, getWidth(), i, this.mTabUnderlinePaint);
      RadioButton localRadioButton;
      for (int j = 0; j < getChildCount(); j++)
      {
        localRadioButton = (RadioButton)getChildAt(j);
        if (localRadioButton.isChecked())
          break label77;
      }
      continue;
      label77: int k = getHeight() - this.mStripTabUnderlineSelected / 2;
      paramCanvas.drawLine(localRadioButton.getLeft(), k, localRadioButton.getRight(), k, this.mTabUnderlineSelectedPaint);
    }
  }

  public void setBackendId(int paramInt)
  {
    Context localContext = getContext();
    this.mStripTabUnderline = localContext.getResources().getDimensionPixelSize(2131427381);
    this.mStripTabUnderlineSelected = localContext.getResources().getDimensionPixelSize(2131427382);
    this.mTabUnderlinePaint = new Paint();
    this.mTabUnderlinePaint.setAntiAlias(true);
    this.mTabUnderlinePaint.setStrokeWidth(this.mStripTabUnderline);
    this.mTabUnderlineSelectedPaint = new Paint();
    this.mTabUnderlineSelectedPaint.setAntiAlias(true);
    this.mTabUnderlineSelectedPaint.setStrokeWidth(this.mStripTabUnderlineSelected);
    int i = CorpusResourceUtils.getBackendForegroundColor(getContext(), paramInt);
    this.mTabUnderlinePaint.setColor(i);
    this.mTabUnderlineSelectedPaint.setColor(i);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CustomRadioGroup
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonBar extends LinearLayout
  implements View.OnClickListener
{
  private ClickListener mClickListener;
  private Button mNegativeButton;
  private Button mPositiveButton;
  private Paint mTopSeparatorPaint;

  public ButtonBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (Build.VERSION.SDK_INT >= 11)
    {
      this.mTopSeparatorPaint = new Paint();
      this.mTopSeparatorPaint.setColor(paramContext.getResources().getColor(2131361799));
      this.mTopSeparatorPaint.setStrokeWidth(1.0F);
    }
  }

  public void onClick(View paramView)
  {
    if (this.mClickListener != null)
    {
      if (paramView != this.mPositiveButton)
        break label25;
      this.mClickListener.onPositiveButtonClick();
    }
    while (true)
    {
      return;
      label25: if (paramView == this.mNegativeButton)
        this.mClickListener.onNegativeButtonClick();
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mTopSeparatorPaint != null)
      paramCanvas.drawLine(0.0F, 0.0F, getWidth(), 0.0F, this.mTopSeparatorPaint);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mPositiveButton = ((Button)findViewById(2131230769));
    this.mNegativeButton = ((Button)findViewById(2131230770));
  }

  public void setClickListener(ClickListener paramClickListener)
  {
    this.mClickListener = paramClickListener;
    this.mPositiveButton.setOnClickListener(this);
    this.mNegativeButton.setOnClickListener(this);
  }

  public void setNegativeButtonTitle(int paramInt)
  {
    this.mNegativeButton.setText(paramInt);
  }

  public void setPositiveButtonEnabled(boolean paramBoolean)
  {
    this.mPositiveButton.setEnabled(paramBoolean);
  }

  public void setPositiveButtonTitle(int paramInt)
  {
    this.mPositiveButton.setText(paramInt);
  }

  public void setPositiveButtonVisible(boolean paramBoolean)
  {
    Button localButton = this.mPositiveButton;
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      localButton.setVisibility(i);
      return;
    }
  }

  public static abstract interface ClickListener
  {
    public abstract void onNegativeButtonClick();

    public abstract void onPositiveButtonClick();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ButtonBar
 * JD-Core Version:    0.6.2
 */
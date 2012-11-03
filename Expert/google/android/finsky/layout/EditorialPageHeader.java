package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditorialPageHeader extends LinearLayout
{
  private View mBottomStrip;
  private TextView mHeader;
  private TextView mSubheader;

  public EditorialPageHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mHeader = ((TextView)findViewById(2131230895));
    this.mSubheader = ((TextView)findViewById(2131230896));
    this.mBottomStrip = findViewById(2131230860);
  }

  public void showSeriesInfo(String paramString1, String paramString2, String paramString3)
  {
    this.mHeader.setText(paramString1);
    if (TextUtils.isEmpty(paramString2))
      this.mSubheader.setVisibility(8);
    while (true)
    {
      int i = Color.parseColor(paramString3);
      this.mHeader.setBackgroundColor(i);
      this.mSubheader.setBackgroundColor(i);
      if (this.mBottomStrip != null)
        this.mBottomStrip.setBackgroundColor(i);
      return;
      this.mSubheader.setVisibility(0);
      this.mSubheader.setText(paramString2);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.EditorialPageHeader
 * JD-Core Version:    0.6.2
 */
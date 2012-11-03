package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DescriptionEditorialHeader extends LinearLayout
{
  private TextView mHeader;
  private TextView mSubheader;

  public DescriptionEditorialHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mHeader = ((TextView)findViewById(2131230895));
    this.mSubheader = ((TextView)findViewById(2131230896));
  }

  public void showEpisodeInfo(String paramString1, String paramString2)
  {
    int i = 8;
    TextView localTextView1 = this.mHeader;
    int j;
    TextView localTextView2;
    if (TextUtils.isEmpty(paramString1))
    {
      j = i;
      localTextView1.setVisibility(j);
      this.mHeader.setText(paramString1);
      this.mSubheader.setText(paramString2);
      localTextView2 = this.mSubheader;
      if (!TextUtils.isEmpty(paramString2))
        break label68;
    }
    while (true)
    {
      localTextView2.setVisibility(i);
      return;
      j = 0;
      break;
      label68: i = 0;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DescriptionEditorialHeader
 * JD-Core Version:    0.6.2
 */
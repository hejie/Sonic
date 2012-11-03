package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.remoting.protos.DocAnnotations.DealOfTheDay;

public class FreeSongOfTheDayHeader extends LinearLayout
{
  private View mBottomStrip;
  private TextView mHeader;
  private View mSpacer;

  public FreeSongOfTheDayHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mHeader = ((TextView)findViewById(2131230895));
    this.mSpacer = findViewById(2131231048);
    this.mBottomStrip = findViewById(2131230860);
  }

  public void showDealOfTheDayInfo(DocAnnotations.DealOfTheDay paramDealOfTheDay)
  {
    this.mHeader.setText(paramDealOfTheDay.getFeaturedHeader());
    int i = Color.parseColor(paramDealOfTheDay.getColorThemeArgb());
    this.mHeader.setBackgroundColor(i);
    this.mBottomStrip.setBackgroundColor(i);
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)getResources().getDrawable(2130837711);
    localBitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    this.mSpacer.setBackgroundDrawable(localBitmapDrawable);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.FreeSongOfTheDayHeader
 * JD-Core Version:    0.6.2
 */
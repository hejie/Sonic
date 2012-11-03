package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SongIndex extends FrameLayout
{
  private ProgressBar mProgressIndicator;
  private TextView mSongIndexText;
  private int mState = 0;
  private ImageView mStatusIndicator;

  public SongIndex(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private int getStateDrawable(int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3:
    default:
      i = 2130837663;
    case 2:
    case 4:
    case 5:
    }
    while (true)
    {
      return i;
      i = 2130837662;
      continue;
      i = 2130837664;
      continue;
      i = 2130837548;
    }
  }

  private void updateUiVisiblity(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      this.mSongIndexText.setVisibility(paramInt2);
      continue;
      this.mProgressIndicator.setVisibility(paramInt2);
      continue;
      this.mStatusIndicator.setVisibility(paramInt2);
    }
  }

  public int getBaseline()
  {
    return this.mSongIndexText.getBaseline();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mSongIndexText = ((TextView)findViewById(2131231051));
    this.mStatusIndicator = ((ImageView)findViewById(2131231052));
    this.mProgressIndicator = ((ProgressBar)findViewById(2131230943));
  }

  public void setState(int paramInt)
  {
    updateUiVisiblity(this.mState, 8);
    updateUiVisiblity(paramInt, 0);
    this.mStatusIndicator.setImageResource(getStateDrawable(paramInt));
    this.mState = paramInt;
  }

  public void setTrackNumber(int paramInt)
  {
    this.mSongIndexText.setText(String.valueOf(paramInt));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.SongIndex
 * JD-Core Version:    0.6.2
 */
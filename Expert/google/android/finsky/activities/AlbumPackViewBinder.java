package com.google.android.finsky.activities;

import android.view.View;
import android.widget.TextView;

public class AlbumPackViewBinder extends DetailsPackViewBinder
{
  protected void handleEmptyData()
  {
    setHeaderNavigable(false);
    TextView localTextView = (TextView)this.mLayout.findViewById(2131230896);
    localTextView.setVisibility(0);
    localTextView.setText(2131165784);
    this.mLayout.findViewById(2131230939).setVisibility(8);
    this.mLayout.findViewById(2131230933).setVisibility(8);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AlbumPackViewBinder
 * JD-Core Version:    0.6.2
 */
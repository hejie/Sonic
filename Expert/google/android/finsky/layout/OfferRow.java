package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.finsky.remoting.protos.Common.Offer;

public class OfferRow extends LinearLayout
  implements Checkable
{
  private TextView mHeader;
  private TextView mPrice;
  private RadioButton mSelector;
  private TextView mSubheader;

  public OfferRow(Context paramContext)
  {
    super(paramContext);
  }

  public OfferRow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean isChecked()
  {
    return this.mSelector.isChecked();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mHeader = ((TextView)findViewById(2131230895));
    this.mSubheader = ((TextView)findViewById(2131230896));
    this.mPrice = ((TextView)findViewById(2131231031));
    this.mSelector = ((RadioButton)findViewById(2131231131));
  }

  public void setChecked(boolean paramBoolean)
  {
    this.mSelector.setChecked(paramBoolean);
  }

  public void setOffer(Common.Offer paramOffer)
  {
    this.mHeader.setText(paramOffer.getFormattedName());
    this.mSubheader.setText(paramOffer.getFormattedDescription());
    this.mPrice.setText(paramOffer.getFormattedAmount());
  }

  public void toggle()
  {
    this.mSelector.toggle();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.OfferRow
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class InstrumentFamilyRow extends LinearLayout
  implements Checkable
{
  private TextView mDescription;
  private RadioButton mSelector;

  public InstrumentFamilyRow(Context paramContext)
  {
    super(paramContext);
  }

  public InstrumentFamilyRow(Context paramContext, AttributeSet paramAttributeSet)
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
    this.mDescription = ((TextView)findViewById(2131231067));
    this.mSelector = ((RadioButton)findViewById(2131231068));
  }

  public void setChecked(boolean paramBoolean)
  {
    this.mSelector.setChecked(paramBoolean);
  }

  public void setInstrumentFamilyDescription(String paramString)
  {
    this.mDescription.setText(paramString);
  }

  public void toggle()
  {
    this.mSelector.toggle();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.InstrumentFamilyRow
 * JD-Core Version:    0.6.2
 */
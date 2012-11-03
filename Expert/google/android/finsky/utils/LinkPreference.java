package com.google.android.finsky.utils;

import android.content.Context;
import android.preference.Preference;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class LinkPreference extends Preference
{
  private CharSequence mLink;

  public LinkPreference(Context paramContext)
  {
    super(paramContext);
  }

  public LinkPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LinkPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    TextView localTextView = (TextView)paramView.findViewById(2131231071);
    if (localTextView != null)
    {
      localTextView.setText(this.mLink);
      localTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }

  public void setLink(CharSequence paramCharSequence)
  {
    if (((paramCharSequence == null) && (this.mLink != null)) || ((paramCharSequence != null) && (!paramCharSequence.equals(this.mLink))))
    {
      this.mLink = paramCharSequence;
      notifyChanged();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.LinkPreference
 * JD-Core Version:    0.6.2
 */
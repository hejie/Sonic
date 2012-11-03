package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListingRow extends RelativeLayout
{
  private TextView mExtra;
  private ImageView mIcon;
  private TextView mSubtitle;
  private TextView mTitle;
  private View mTopSeparator;

  public ListingRow(Context paramContext)
  {
    super(paramContext);
  }

  public ListingRow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void hideIcon()
  {
    this.mIcon.setVisibility(4);
    this.mIcon.setPadding(this.mIcon.getPaddingLeft(), 0, 0, 0);
  }

  public void hideSeparator()
  {
    this.mTopSeparator.setVisibility(8);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mTitle = ((TextView)findViewById(2131231078));
    this.mSubtitle = ((TextView)findViewById(2131231079));
    this.mExtra = ((TextView)findViewById(2131231080));
    this.mIcon = ((ImageView)findViewById(2131231077));
    this.mTopSeparator = findViewById(2131231005);
  }

  public void populate(int paramInt1, int paramInt2)
  {
    this.mTitle.setText(paramInt1);
    this.mSubtitle.setText(paramInt2);
    hideIcon();
  }

  public void populate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTitle.setText(paramInt1);
    this.mSubtitle.setText(paramInt2);
    this.mIcon.setImageResource(paramInt3);
  }

  public void populate(int paramInt, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    this.mTitle.setText(paramInt);
    this.mSubtitle.setText(paramCharSequence1);
    this.mExtra.setText(paramCharSequence2);
    this.mExtra.setVisibility(0);
    hideIcon();
  }

  public void populate(int paramInt1, String paramString, int paramInt2)
  {
    this.mTitle.setText(paramInt1);
    this.mSubtitle.setText(paramString);
    this.mIcon.setImageResource(paramInt2);
  }

  public void populateAsHtml(String paramString1, String paramString2)
  {
    this.mTitle.setText(Html.fromHtml(paramString1));
    this.mTitle.setMovementMethod(LinkMovementMethod.getInstance());
    this.mSubtitle.setText(Html.fromHtml(paramString2));
    this.mSubtitle.setMovementMethod(LinkMovementMethod.getInstance());
    hideIcon();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ListingRow
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusOneData;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.CorpusResourceUtils;

public class FullRowEntry extends OverviewBucketEntry
{
  private View mCorpusStrip;
  private TextView mPlusOne;

  public FullRowEntry(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener)
  {
    super.bind(paramDocument1, paramDocument2, paramBitmapLoader, paramBoolean, paramOnClickListener);
    if ((paramDocument2.getDocumentType() != 3) && (paramDocument2.hasPlusOneData()) && (this.mPlusOne != null))
    {
      long l = paramDocument2.getPlusOneData().getTotal();
      if (l > 0L)
      {
        this.mPlusOne.setVisibility(0);
        String str = DetailsPlusOne.formatPlusOneCount(getContext(), l, 2131165753);
        this.mPlusOne.setText(str + " ");
      }
    }
    if (this.mCorpusStrip != null)
    {
      this.mCorpusStrip.setVisibility(0);
      this.mCorpusStrip.setBackgroundColor(CorpusResourceUtils.getBackendHintColor(getContext(), paramDocument2.getBackend()));
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mPlusOne = ((TextView)findViewById(2131230756));
    this.mCorpusStrip = findViewById(2131230858);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.FullRowEntry
 * JD-Core Version:    0.6.2
 */
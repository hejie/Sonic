package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.finsky.utils.BitmapLoader;

public class CellWithOverlay extends RelativeLayout
{
  private CellTitleOverlay mName;

  public CellWithOverlay(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public CellWithOverlay(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CellWithOverlay(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mName = ((CellTitleOverlay)findViewById(2131230743));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mName.layout(this.mName.getLeft(), this.mName.getTop(), this.mName.getLeft() + this.mName.getMeasuredWidth(), this.mName.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = (int)(i * this.mName.getMinWidthPercent());
    int k = (int)(i * this.mName.getMaxWidthPercent());
    int m = Math.min(Math.max(this.mName.getMeasuredWidth(), j), k);
    this.mName.measure(View.MeasureSpec.makeMeasureSpec(m, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mName.getMeasuredHeight(), 1073741824));
  }

  protected void setOverlayCaption(Document paramDocument, BitmapLoader paramBitmapLoader)
  {
    this.mName.setText(paramDocument.getTitle());
    BadgeUtils.configureItemBadge(paramDocument, paramBitmapLoader, this.mName, -1);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CellWithOverlay
 * JD-Core Version:    0.6.2
 */
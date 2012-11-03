package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class GenericContentRow extends LinearLayout
{
  public GenericContentRow(Context paramContext)
  {
    super(paramContext);
  }

  public GenericContentRow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findViewById(2131231057);
    if (localView.getVisibility() == 8);
    while (true)
    {
      return;
      int i = localView.getHeight();
      int j = getHeight() - getPaddingBottom();
      localView.layout(localView.getLeft(), j - i, localView.getRight(), j);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.GenericContentRow
 * JD-Core Version:    0.6.2
 */
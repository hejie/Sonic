package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import com.android.vending.R.styleable;

public abstract class MarginBox extends ViewGroup
{
  protected int mMaxContentWidth;

  public MarginBox(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public MarginBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public MarginBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MarginBox);
    this.mMaxContentWidth = localTypedArray.getDimensionPixelSize(0, -1);
    localTypedArray.recycle();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.MarginBox
 * JD-Core Version:    0.6.2
 */
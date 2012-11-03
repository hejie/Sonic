package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.vending.R.styleable;

public class ContentFrame extends FrameLayout
{
  private ViewGroup mDataLayout;
  private final LayoutInflater mInflater;

  public ContentFrame(Context paramContext)
  {
    this(paramContext, null, 0);
  }

  public ContentFrame(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ContentFrame(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ContentFrame);
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    View localView1 = this.mInflater.inflate(2130968777, this, false);
    localView1.setVisibility(8);
    addView(localView1);
    View localView2 = this.mInflater.inflate(2130968776, this, false);
    localView2.setVisibility(8);
    addView(localView2);
    setDataLayout(localTypedArray.getResourceId(0, 0), localTypedArray.getResourceId(1, 0));
  }

  public ViewGroup getDataLayout()
  {
    return this.mDataLayout;
  }

  public void setDataLayout(int paramInt1, int paramInt2)
  {
    setDataLayout(this.mInflater, paramInt1, paramInt2);
  }

  public void setDataLayout(LayoutInflater paramLayoutInflater, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0);
    while (true)
    {
      return;
      this.mDataLayout = ((ViewGroup)paramLayoutInflater.inflate(paramInt1, this, false));
      if (paramInt2 != 0)
        this.mDataLayout.setId(paramInt2);
      addView(this.mDataLayout);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ContentFrame
 * JD-Core Version:    0.6.2
 */
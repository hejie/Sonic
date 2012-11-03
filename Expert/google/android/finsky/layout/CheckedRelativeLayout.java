package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;

public class CheckedRelativeLayout extends AccessibleRelativeLayout
  implements Checkable
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private boolean mChecked = false;

  public CheckedRelativeLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public CheckedRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CheckedRelativeLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isChecked()
  {
    return this.mChecked;
  }

  protected int[] onCreateDrawableState(int paramInt)
  {
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    if (this.mChecked)
      mergeDrawableStates(arrayOfInt, CHECKED_STATE_SET);
    return arrayOfInt;
  }

  public void setChecked(boolean paramBoolean)
  {
    if (this.mChecked == paramBoolean);
    while (true)
    {
      return;
      this.mChecked = paramBoolean;
      refreshDrawableState();
    }
  }

  public void toggle()
  {
    if (!this.mChecked);
    for (boolean bool = true; ; bool = false)
    {
      setChecked(bool);
      return;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CheckedRelativeLayout
 * JD-Core Version:    0.6.2
 */
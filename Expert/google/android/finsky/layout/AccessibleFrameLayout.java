package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import java.util.List;

public class AccessibleFrameLayout extends FrameLayout
{
  public AccessibleFrameLayout(Context paramContext)
  {
    super(paramContext);
  }

  public AccessibleFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    boolean bool = super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.getText().clear();
    return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.AccessibleFrameLayout
 * JD-Core Version:    0.6.2
 */
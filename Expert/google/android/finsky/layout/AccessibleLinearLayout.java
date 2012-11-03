package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import java.util.List;

public class AccessibleLinearLayout extends LinearLayout
{
  public AccessibleLinearLayout(Context paramContext)
  {
    super(paramContext);
  }

  public AccessibleLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
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
 * Qualified Name:     com.google.android.finsky.layout.AccessibleLinearLayout
 * JD-Core Version:    0.6.2
 */
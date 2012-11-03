package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TextView;
import java.util.List;

public class AccessibleTextView extends TextView
{
  public AccessibleTextView(Context paramContext)
  {
    super(paramContext);
  }

  public AccessibleTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public AccessibleTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (!isShown());
    while (true)
    {
      return false;
      CharSequence localCharSequence = getText();
      if (TextUtils.isEmpty(localCharSequence))
        localCharSequence = getHint();
      if (!TextUtils.isEmpty(localCharSequence))
      {
        if (localCharSequence.length() > 500)
          localCharSequence = localCharSequence.subSequence(0, 501);
        paramAccessibilityEvent.getText().add(localCharSequence.toString().toLowerCase());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.AccessibleTextView
 * JD-Core Version:    0.6.2
 */
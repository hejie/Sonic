package com.google.android.finsky.layout;

import android.app.Activity;
import android.os.Build.VERSION;

public class CustomActionBarFactory
{
  public static CustomActionBar getInstance(Activity paramActivity)
  {
    Object localObject;
    if (shouldUseSystemActionBar())
      if (paramActivity.getActionBar() == null)
        localObject = new FakeActionBar();
    while (true)
    {
      return localObject;
      localObject = new NativeActionBar(paramActivity);
      continue;
      localObject = (LegacyActionBar)paramActivity.findViewById(2131230730);
    }
  }

  public static boolean shouldUseSystemActionBar()
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.CustomActionBarFactory
 * JD-Core Version:    0.6.2
 */
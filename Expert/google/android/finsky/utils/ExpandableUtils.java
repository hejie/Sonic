package com.google.android.finsky.utils;

import android.os.Bundle;

public class ExpandableUtils
{
  public static int getSavedExpansionState(Bundle paramBundle, String paramString, int paramInt)
  {
    if (paramBundle == null);
    while (true)
    {
      return paramInt;
      String str = "expansion_state:" + paramString;
      if (paramBundle.containsKey(str))
        paramInt = paramBundle.getInt(str);
    }
  }

  public static void saveExpansionState(Bundle paramBundle, String paramString, int paramInt)
  {
    paramBundle.putInt("expansion_state:" + paramString, paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.ExpandableUtils
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.library.LibraryReplicators.Listener;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.widget.recommendation.RecommendationsViewFactory;
import com.google.android.finsky.widget.recommendation.RecommendedWidgetProvider;

public class WidgetUtils
{
  public static int getDips(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    return (int)(localResources.getDimensionPixelSize(paramInt) / localResources.getDisplayMetrics().density);
  }

  public static SparseIntArray parseSparseIntArray(String paramString)
  {
    String[] arrayOfString = paramString.split(",");
    SparseIntArray localSparseIntArray = new SparseIntArray(arrayOfString.length);
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str = arrayOfString[j];
      int k = str.indexOf(':');
      if (k < 0)
        FinskyLog.w("Invalid tuple: map=%s, tuple=%s", new Object[] { paramString, str });
      while (true)
      {
        j++;
        break;
        try
        {
          localSparseIntArray.put(Integer.parseInt(str.substring(0, k)), Integer.parseInt(str.substring(k + 1, str.length())));
        }
        catch (NumberFormatException localNumberFormatException)
        {
          FinskyLog.w("Malformed key or value: map=%s, tuple=%s", new Object[] { paramString, str });
        }
      }
    }
    return localSparseIntArray;
  }

  public static void registerLibraryMutationsListener(Context paramContext, LibraryReplicators paramLibraryReplicators)
  {
    paramLibraryReplicators.addListener(new LibraryReplicators.Listener()
    {
      public void onMutationsApplied(AccountLibrary paramAnonymousAccountLibrary, String paramAnonymousString)
      {
        WidgetTypeMap localWidgetTypeMap = WidgetTypeMap.get(this.val$context);
        int i = AccountLibrary.getBackendFromLibraryId(paramAnonymousString);
        if (i != -1)
        {
          int[] arrayOfInt = localWidgetTypeMap.getWidgets(RecommendedWidgetProvider.class, WidgetUtils.translate(i));
          if (arrayOfInt.length > 0)
            RecommendationsViewFactory.notifyDataSetChanged(this.val$context, arrayOfInt);
        }
      }
    });
  }

  public static String serializeSparseIntArray(SparseIntArray paramSparseIntArray)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    int j = 0;
    if (j < paramSparseIntArray.size())
    {
      int k = paramSparseIntArray.keyAt(j);
      if (k < 0);
      while (true)
      {
        j++;
        break;
        if (i == 0)
          localStringBuilder.append(',');
        i = 0;
        localStringBuilder.append(k).append(':').append(paramSparseIntArray.get(k));
      }
    }
    return localStringBuilder.toString();
  }

  public static int translate(String paramString)
  {
    int i;
    if ("all".equals(paramString))
      i = 0;
    while (true)
    {
      return i;
      if ("apps".equals(paramString))
      {
        i = 3;
      }
      else if ("books".equals(paramString))
      {
        i = 1;
      }
      else if ("movies".equals(paramString))
      {
        i = 4;
      }
      else if ("music".equals(paramString))
      {
        i = 2;
      }
      else
      {
        if (!"magazines".equals(paramString))
          break;
        i = 6;
      }
    }
    throw new IllegalArgumentException("Invalid backend type: " + paramString);
  }

  public static String translate(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 5:
    default:
      throw new IllegalArgumentException("Invalid backend ID: " + paramInt);
    case 3:
      str = "apps";
    case 1:
    case 4:
    case 2:
    case 6:
    case 0:
    }
    while (true)
    {
      return str;
      str = "books";
      continue;
      str = "movies";
      continue;
      str = "music";
      continue;
      str = "magazines";
      continue;
      str = "all";
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.WidgetUtils
 * JD-Core Version:    0.6.2
 */
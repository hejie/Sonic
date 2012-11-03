package com.google.android.finsky.utils;

import android.text.Html;

public abstract class FastHtmlParser
{
  public static CharSequence fromHtml(String paramString)
  {
    Object localObject = new StringBuilder(paramString);
    replace((StringBuilder)localObject, "<p>", "\n\n");
    replace((StringBuilder)localObject, "<br>", "\n");
    int i = ((StringBuilder)localObject).indexOf("<");
    if (i != -1)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = ((StringBuilder)localObject).substring(i, Math.min(i + 10, ((StringBuilder)localObject).length()));
      DfeLog.d("Doing slow HTML parse due to unexpected tag %s", arrayOfObject);
      localObject = Html.fromHtml(paramString);
    }
    while (true)
    {
      return localObject;
      int j = 0;
      int k = ((StringBuilder)localObject).indexOf("&", j);
      if (k != -1)
      {
        int m = ((StringBuilder)localObject).indexOf(";", k);
        if (m != -1)
        {
          String str = ((StringBuilder)localObject).substring(k + 1, m);
          ((StringBuilder)localObject).delete(k + 1, m + 1);
          if (str.charAt(0) == '#');
          while (true)
          {
            try
            {
              ((StringBuilder)localObject).setCharAt(k, (char)Integer.parseInt(str.substring(1)));
              j = k + 1;
            }
            catch (NumberFormatException localNumberFormatException)
            {
              DfeLog.d("Doing slow HTML parse due to unexpected &# escape %s", new Object[] { str });
              localObject = Html.fromHtml(paramString);
            }
            break;
            if ("quot".equals(str))
            {
              ((StringBuilder)localObject).setCharAt(k, '"');
            }
            else if ("apos".equals(str))
            {
              ((StringBuilder)localObject).setCharAt(k, '\'');
            }
            else if ("amp".equals(str))
            {
              ((StringBuilder)localObject).setCharAt(k, '&');
            }
            else if ("lt".equals(str))
            {
              ((StringBuilder)localObject).setCharAt(k, '<');
            }
            else
            {
              if (!"gt".equals(str))
                break label299;
              ((StringBuilder)localObject).setCharAt(k, '>');
            }
          }
          label299: DfeLog.d("Doing slow HTML parse due to unexpected & escape %s", new Object[] { str });
          localObject = Html.fromHtml(paramString);
        }
      }
    }
  }

  private static void replace(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    int i = paramString1.length();
    int j = paramString2.length();
    int m;
    for (int k = 0; ; k = m + j)
    {
      m = paramStringBuilder.indexOf(paramString1, k);
      if (m == -1)
        return;
      paramStringBuilder.replace(m, m + i, paramString2);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.FastHtmlParser
 * JD-Core Version:    0.6.2
 */
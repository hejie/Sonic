package com.google.android.finsky.billing.giftcard;

import android.text.TextUtils;

public class PromoCodeVerifier
{
  private static boolean isAllDigits(String paramString)
  {
    int i = 0;
    if (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if ((j >= 48) && (j <= 57));
    }
    for (boolean bool = false; ; bool = true)
    {
      return bool;
      i++;
      break;
    }
  }

  public static boolean verifyPromoCode(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.length() == 15) && (isAllDigits(paramString)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.giftcard.PromoCodeVerifier
 * JD-Core Version:    0.6.2
 */
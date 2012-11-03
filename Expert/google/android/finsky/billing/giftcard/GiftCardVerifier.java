package com.google.android.finsky.billing.giftcard;

import android.text.TextUtils;

class GiftCardVerifier
{
  private static final int RADIX = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ".length();

  private static boolean validateLuhnChecksum(String paramString)
  {
    int i = 1;
    boolean bool = false;
    int j = 1;
    int k = 0;
    char[] arrayOfChar = paramString.toCharArray();
    int m = -1 + arrayOfChar.length;
    if (m >= 0)
    {
      int n = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZ".indexOf(arrayOfChar[m]);
      if (n == -1)
        return bool;
      int i1 = n * j;
      k += i1 / RADIX + i1 % RADIX;
      if (j == 2);
      for (j = i; ; j = 2)
      {
        m--;
        break;
      }
    }
    if (k % RADIX == 0);
    while (true)
    {
      bool = i;
      break;
      i = 0;
    }
  }

  public static boolean verifyGiftCardCode(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.length() == 20) && (validateLuhnChecksum(paramString)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.giftcard.GiftCardVerifier
 * JD-Core Version:    0.6.2
 */
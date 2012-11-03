package com.google.android.finsky.billing.creditcard;

import android.text.TextUtils;

public enum CreditCardType
{
  public final int cvcLength;
  public final int[] numberLengths;
  public final String[] numberPrefixRanges;
  public final int protobufType;

  static
  {
    DISCOVER = new CreditCardType("DISCOVER", 1, 4, new int[] { 16 }, 3, new String[] { "6011", "650" });
    AMEX = new CreditCardType("AMEX", 2, 3, new int[] { 15 }, 4, new String[] { "34", "37" });
    MC = new CreditCardType("MC", 3, 2, new int[] { 14, 16 }, 3, new String[] { "51-55" });
    VISA = new CreditCardType("VISA", 4, 1, new int[] { 13, 16 }, 3, new String[] { "4" });
    CreditCardType[] arrayOfCreditCardType = new CreditCardType[5];
    arrayOfCreditCardType[0] = JCB;
    arrayOfCreditCardType[1] = DISCOVER;
    arrayOfCreditCardType[2] = AMEX;
    arrayOfCreditCardType[3] = MC;
    arrayOfCreditCardType[4] = VISA;
  }

  private CreditCardType(int paramInt1, int[] paramArrayOfInt, int paramInt2, String[] paramArrayOfString)
  {
    this.protobufType = paramInt1;
    this.numberLengths = paramArrayOfInt;
    this.cvcLength = paramInt2;
    this.numberPrefixRanges = paramArrayOfString;
  }

  public static int getMaxCvcLength()
  {
    int i = -2147483648;
    CreditCardType[] arrayOfCreditCardType = values();
    int j = arrayOfCreditCardType.length;
    for (int k = 0; k < j; k++)
      i = Math.max(i, arrayOfCreditCardType[k].cvcLength);
    return i;
  }

  public static int getMaxNumberLength()
  {
    int i = -2147483648;
    CreditCardType[] arrayOfCreditCardType = values();
    int j = arrayOfCreditCardType.length;
    for (int k = 0; k < j; k++)
    {
      int[] arrayOfInt = arrayOfCreditCardType[k].numberLengths;
      int m = arrayOfInt.length;
      for (int n = 0; n < m; n++)
        i = Math.max(i, arrayOfInt[n]);
    }
    return i;
  }

  public static CreditCardType getTypeForNumber(String paramString)
  {
    CreditCardType[] arrayOfCreditCardType = values();
    int i = arrayOfCreditCardType.length;
    int j = 0;
    CreditCardType localCreditCardType;
    if (j < i)
    {
      localCreditCardType = arrayOfCreditCardType[j];
      if (!localCreditCardType.isValidNumber(paramString));
    }
    while (true)
    {
      return localCreditCardType;
      j++;
      break;
      localCreditCardType = null;
    }
  }

  public static CreditCardType getTypeForPrefix(String paramString)
  {
    CreditCardType[] arrayOfCreditCardType = values();
    int i = arrayOfCreditCardType.length;
    int j = 0;
    CreditCardType localCreditCardType;
    if (j < i)
    {
      localCreditCardType = arrayOfCreditCardType[j];
      if (!localCreditCardType.isValidPrefix(paramString));
    }
    while (true)
    {
      return localCreditCardType;
      j++;
      break;
      localCreditCardType = null;
    }
  }

  protected boolean hasValidChecksum(String paramString)
  {
    boolean bool = false;
    if (!TextUtils.isEmpty(paramString))
    {
      int i = 0;
      int j = 0;
      for (int k = -1 + paramString.length(); k >= 0; k--)
      {
        int m = Integer.parseInt(String.valueOf(paramString.charAt(k)));
        int n = m + j * m;
        i += (int)(n + Math.floor(n / 10));
        j = 1 - j;
      }
      if (i % 10 == 0)
        bool = true;
    }
    return bool;
  }

  public boolean hasValidLength(String paramString)
  {
    int i = 0;
    if (i < this.numberLengths.length)
      if (paramString.length() != this.numberLengths[i]);
    for (boolean bool = true; ; bool = false)
    {
      return bool;
      i++;
      break;
    }
  }

  public boolean isValidNumber(String paramString)
  {
    if ((hasValidLength(paramString)) && (hasValidChecksum(paramString)) && (isValidPrefix(paramString)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean isValidPrefix(String paramString)
  {
    boolean bool = true;
    if (!TextUtils.isEmpty(paramString))
    {
      int i = 0;
      if (i < this.numberPrefixRanges.length)
      {
        String[] arrayOfString = this.numberPrefixRanges[i].split("-", 2);
        String str = arrayOfString[0];
        if (arrayOfString.length == 2)
          str = arrayOfString[0].substring(0, arrayOfString[0].length() - arrayOfString[bool].length());
        if (paramString.length() < str.length());
        int j;
        int k;
        int m;
        do
        {
          do
          {
            do
            {
              i++;
              break;
            }
            while (!str.equals(paramString.substring(0, str.length())));
            j = Integer.parseInt(arrayOfString[0]);
            k = j;
            if (arrayOfString.length == 2)
              k = Integer.parseInt(str + arrayOfString[bool]);
          }
          while (paramString.length() < arrayOfString[0].length());
          m = Integer.parseInt(paramString.substring(0, arrayOfString[0].length()));
        }
        while ((m < j) || (m > k));
      }
    }
    while (true)
    {
      return bool;
      bool = false;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.CreditCardType
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.creditcard;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

public class CreditCardValidator
{
  private static boolean checkNumber(String paramString, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    if ((paramString.length() < paramInt1) || (paramString.length() > paramInt2));
    while (true)
    {
      return bool;
      for (int i = 0; ; i++)
      {
        if (i >= paramString.length())
          break label60;
        if ((paramString.charAt(i) < '0') || (paramString.charAt(i) > '9'))
          break;
      }
      label60: bool = true;
    }
  }

  public static CreditCardType validate(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, Set<InputField> paramSet)
  {
    CreditCardType localCreditCardType = CreditCardType.getTypeForNumber(paramString1);
    if (localCreditCardType == null)
    {
      paramSet.add(InputField.NUMBER);
      localCreditCardType = CreditCardType.getTypeForPrefix(paramString1);
    }
    if (localCreditCardType != null)
      validateCvc(localCreditCardType, paramString2, paramSet);
    validateExpirationDate(paramString3, paramString4, paramInt, paramSet);
    return localCreditCardType;
  }

  private static void validateCvc(CreditCardType paramCreditCardType, String paramString, Set<InputField> paramSet)
  {
    if ((paramCreditCardType != null) && (!checkNumber(paramString, paramCreditCardType.cvcLength, paramCreditCardType.cvcLength)))
      paramSet.add(InputField.CVC);
  }

  private static void validateExpirationDate(String paramString1, String paramString2, int paramInt, Set<InputField> paramSet)
  {
    int i = 0;
    int j = 0;
    int k = -1;
    if (checkNumber(paramString2, 2, 2))
      k = Integer.parseInt(paramString2);
    if (k < 0)
    {
      paramSet.add(InputField.EXP_YEAR);
      i = 1;
    }
    int m = k + paramInt;
    int n = -1;
    if (checkNumber(paramString1, 1, 2))
      n = Integer.valueOf(paramString1).intValue();
    if ((n < 1) || (n > 12))
    {
      paramSet.add(InputField.EXP_MONTH);
      j = 1;
    }
    if (i != 0);
    while (true)
    {
      return;
      GregorianCalendar localGregorianCalendar1 = new GregorianCalendar();
      GregorianCalendar localGregorianCalendar2 = new GregorianCalendar(localGregorianCalendar1.get(1), localGregorianCalendar1.get(2), 1);
      if (m > 20 + localGregorianCalendar2.get(1))
        paramSet.add(InputField.EXP_YEAR);
      if ((j == 0) && (localGregorianCalendar2.after(new GregorianCalendar(m, n - 1, 1))))
      {
        paramSet.add(InputField.EXP_MONTH);
        paramSet.add(InputField.EXP_YEAR);
      }
    }
  }

  public static enum InputField
  {
    static
    {
      CVC = new InputField("CVC", 1);
      EXP_MONTH = new InputField("EXP_MONTH", 2);
      EXP_YEAR = new InputField("EXP_YEAR", 3);
      InputField[] arrayOfInputField = new InputField[4];
      arrayOfInputField[0] = NUMBER;
      arrayOfInputField[1] = CVC;
      arrayOfInputField[2] = EXP_MONTH;
      arrayOfInputField[3] = EXP_YEAR;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.CreditCardValidator
 * JD-Core Version:    0.6.2
 */
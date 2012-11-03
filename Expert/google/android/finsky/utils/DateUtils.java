package com.google.android.finsky.utils;

import com.google.android.finsky.FinskyApp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtils
{
  private static final java.text.DateFormat DISPLAY_DATE_FORMAT = android.text.format.DateFormat.getLongDateFormat(FinskyApp.get());
  private static final java.text.DateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final java.text.DateFormat SHORT_DISPLAY_DATE_FORMAT = android.text.format.DateFormat.getDateFormat(FinskyApp.get());

  public static String formatDisplayDate(Date paramDate)
  {
    try
    {
      String str = DISPLAY_DATE_FORMAT.format(paramDate);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static String formatIso8601Date(String paramString)
    throws ParseException
  {
    try
    {
      String str = formatDisplayDate(ISO8601_DATE_FORMAT.parse(paramString));
      paramString = str;
      return paramString;
    }
    catch (ParseException localParseException)
    {
      while (Pattern.matches("^\\d\\d\\d\\d$", paramString));
      throw localParseException;
    }
    finally
    {
    }
  }

  public static String formatShortDisplayDate(Date paramDate)
  {
    try
    {
      String str = SHORT_DISPLAY_DATE_FORMAT.format(paramDate);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.DateUtils
 * JD-Core Version:    0.6.2
 */
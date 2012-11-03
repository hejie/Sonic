package com.google.android.finsky.config;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Utils;
import java.util.EnumSet;

public enum ContentLevel
{
  private static final ContentLevel DEFAULT_VALUE = convertFromLegacyValue(((Integer)G.vendingContentRating.get()).intValue());
  private final int mValue;

  static
  {
    HIGH_MATURITY = new ContentLevel("HIGH_MATURITY", 3, 3);
    SHOW_ALL = new ContentLevel("SHOW_ALL", 4, 3);
    ContentLevel[] arrayOfContentLevel = new ContentLevel[5];
    arrayOfContentLevel[0] = EVERYONE;
    arrayOfContentLevel[1] = LOW_MATURITY;
    arrayOfContentLevel[2] = MEDIUM_MATURITY;
    arrayOfContentLevel[3] = HIGH_MATURITY;
    arrayOfContentLevel[4] = SHOW_ALL;
  }

  private ContentLevel(int paramInt)
  {
    this.mValue = paramInt;
  }

  public static ContentLevel convertFromLegacyValue(int paramInt)
  {
    ContentLevel localContentLevel;
    if (paramInt == -1)
      localContentLevel = HIGH_MATURITY;
    while (true)
    {
      return localContentLevel;
      ContentLevel[] arrayOfContentLevel = values();
      int i = arrayOfContentLevel.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label47;
        localContentLevel = arrayOfContentLevel[j];
        if (localContentLevel.mValue == paramInt)
          break;
      }
      label47: localContentLevel = HIGH_MATURITY;
    }
  }

  public static ContentLevel importFromSettings(Context paramContext)
  {
    int i = ((Integer)FinskyPreferences.contentFilterLevel.get()).intValue();
    if (i == -1)
    {
      int m = migrateOldLevels(paramContext, FinskyPreferences.getPreferencesFile().open());
      if (m != -1)
      {
        FinskyPreferences.contentFilterLevel.put(Integer.valueOf(m));
        i = m;
      }
    }
    int k;
    ContentLevel localContentLevel;
    if (i >= 0)
    {
      ContentLevel[] arrayOfContentLevel = values();
      int j = arrayOfContentLevel.length;
      k = 0;
      if (k < j)
      {
        localContentLevel = arrayOfContentLevel[k];
        if (localContentLevel.mValue != i);
      }
    }
    while (true)
    {
      return localContentLevel;
      k++;
      break;
      localContentLevel = DEFAULT_VALUE;
    }
  }

  private static int migrateOldLevels(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    int i = 0;
    int j = HIGH_MATURITY.getDfeValue();
    Account[] arrayOfAccount = AccountHandler.getAccounts(paramContext);
    for (int k = -1 + arrayOfAccount.length; k >= 0; k--)
    {
      String str = arrayOfAccount[k].name;
      int i2 = paramSharedPreferences.getInt(Utils.getPreferenceKey(FinskyPreferences.contentFilterLevel.getKey(), str), -1);
      if (i2 >= 0)
      {
        i = 1;
        j = Math.min(j, i2);
      }
    }
    if (i != 0);
    while (true)
    {
      return j;
      PreferenceFile.SharedPreference localSharedPreference = new PreferenceFile("vending_preferences", 0).value("content_rating", Integer.valueOf(-1));
      if (localSharedPreference.exists())
      {
        int m = ((Integer)localSharedPreference.get()).intValue();
        ContentLevel[] arrayOfContentLevel = values();
        int n = arrayOfContentLevel.length;
        for (int i1 = 0; ; i1++)
        {
          if (i1 >= n)
            break label177;
          ContentLevel localContentLevel = arrayOfContentLevel[i1];
          if (localContentLevel.mValue == m)
          {
            j = localContentLevel.mValue;
            break;
          }
        }
      }
      label177: j = -1;
    }
  }

  public boolean encompasses(ContentLevel paramContentLevel)
  {
    boolean bool = true;
    switch (1.$SwitchMap$com$google$android$finsky$config$ContentLevel[ordinal()])
    {
    default:
      bool = false;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return bool;
      bool = EnumSet.of(MEDIUM_MATURITY, LOW_MATURITY, EVERYONE).contains(paramContentLevel);
      continue;
      bool = EnumSet.of(LOW_MATURITY, EVERYONE).contains(paramContentLevel);
      continue;
      if (paramContentLevel != this)
        bool = false;
    }
  }

  public int getDfeValue()
  {
    return this.mValue;
  }

  public int getValue()
  {
    return this.mValue;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.config.ContentLevel
 * JD-Core Version:    0.6.2
 */
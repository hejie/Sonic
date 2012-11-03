package com.google.android.finsky.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

public class PreferenceFile
{
  private static Context sContext;
  private final int mMode;
  private final String mName;

  public PreferenceFile(String paramString, int paramInt)
  {
    this.mName = paramString;
    this.mMode = paramInt;
  }

  public static boolean commit(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT < 9);
    for (boolean bool = paramEditor.commit(); ; bool = true)
    {
      return bool;
      paramEditor.apply();
    }
  }

  public static void init(Context paramContext)
  {
    sContext = paramContext;
  }

  public SharedPreferences open()
  {
    return sContext.getSharedPreferences(this.mName, this.mMode);
  }

  public PrefixSharedPreference<Boolean> prefixPreference(String paramString, Boolean paramBoolean)
  {
    return new PrefixSharedPreference(paramString, paramBoolean)
    {
      protected PreferenceFile.SharedPreference<Boolean> valueWithKey(String paramAnonymousString)
      {
        return PreferenceFile.this.value(paramAnonymousString, (Boolean)this.mDefaultValue);
      }
    };
  }

  public PrefixSharedPreference<Integer> prefixPreference(String paramString, Integer paramInteger)
  {
    return new PrefixSharedPreference(paramString, paramInteger)
    {
      protected PreferenceFile.SharedPreference<Integer> valueWithKey(String paramAnonymousString)
      {
        return PreferenceFile.this.value(paramAnonymousString, (Integer)this.mDefaultValue);
      }
    };
  }

  public PrefixSharedPreference<Long> prefixPreference(String paramString, Long paramLong)
  {
    return new PrefixSharedPreference(paramString, paramLong)
    {
      protected PreferenceFile.SharedPreference<Long> valueWithKey(String paramAnonymousString)
      {
        return PreferenceFile.this.value(paramAnonymousString, (Long)this.mDefaultValue);
      }
    };
  }

  public PrefixSharedPreference<String> prefixPreference(String paramString1, String paramString2)
  {
    return new PrefixSharedPreference(paramString1, paramString2)
    {
      protected PreferenceFile.SharedPreference<String> valueWithKey(String paramAnonymousString)
      {
        return PreferenceFile.this.value(paramAnonymousString, (String)this.mDefaultValue);
      }
    };
  }

  public SharedPreference<Boolean> value(String paramString, Boolean paramBoolean)
  {
    return new SharedPreference(this, paramString, paramBoolean)
    {
      protected Boolean read(SharedPreferences paramAnonymousSharedPreferences)
      {
        if (paramAnonymousSharedPreferences.contains(this.mKey));
        for (Boolean localBoolean = Boolean.valueOf(paramAnonymousSharedPreferences.getBoolean(this.mKey, false)); ; localBoolean = (Boolean)this.mDefaultValue)
          return localBoolean;
      }

      protected void write(SharedPreferences.Editor paramAnonymousEditor, Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean == null)
          throw new IllegalArgumentException("null cannot be written for <Boolean>");
        paramAnonymousEditor.putBoolean(this.mKey, paramAnonymousBoolean.booleanValue());
      }
    };
  }

  public SharedPreference<Integer> value(String paramString, Integer paramInteger)
  {
    return new SharedPreference(this, paramString, paramInteger)
    {
      protected Integer read(SharedPreferences paramAnonymousSharedPreferences)
      {
        if (paramAnonymousSharedPreferences.contains(this.mKey));
        for (Integer localInteger = Integer.valueOf(paramAnonymousSharedPreferences.getInt(this.mKey, 0)); ; localInteger = (Integer)this.mDefaultValue)
          return localInteger;
      }

      protected void write(SharedPreferences.Editor paramAnonymousEditor, Integer paramAnonymousInteger)
      {
        if (paramAnonymousInteger == null)
          throw new IllegalArgumentException("null cannot be written for <Integer>");
        paramAnonymousEditor.putInt(this.mKey, paramAnonymousInteger.intValue());
      }
    };
  }

  public SharedPreference<Long> value(String paramString, Long paramLong)
  {
    return new SharedPreference(this, paramString, paramLong)
    {
      protected Long read(SharedPreferences paramAnonymousSharedPreferences)
      {
        if (paramAnonymousSharedPreferences.contains(this.mKey));
        for (Long localLong = Long.valueOf(paramAnonymousSharedPreferences.getLong(this.mKey, 0L)); ; localLong = (Long)this.mDefaultValue)
          return localLong;
      }

      protected void write(SharedPreferences.Editor paramAnonymousEditor, Long paramAnonymousLong)
      {
        if (paramAnonymousLong == null)
          throw new IllegalArgumentException("null cannot be written for <Long>");
        paramAnonymousEditor.putLong(this.mKey, paramAnonymousLong.longValue());
      }
    };
  }

  public SharedPreference<String> value(String paramString1, String paramString2)
  {
    return new SharedPreference(this, paramString1, paramString2)
    {
      protected String read(SharedPreferences paramAnonymousSharedPreferences)
      {
        if (paramAnonymousSharedPreferences.contains(this.mKey));
        for (String str = paramAnonymousSharedPreferences.getString(this.mKey, null); ; str = (String)this.mDefaultValue)
          return str;
      }

      protected void write(SharedPreferences.Editor paramAnonymousEditor, String paramAnonymousString)
      {
        paramAnonymousEditor.putString(this.mKey, paramAnonymousString);
      }
    };
  }

  public static abstract class PrefixSharedPreference<T>
  {
    protected final T mDefaultValue;
    protected final String mPrefix;

    protected PrefixSharedPreference(String paramString, T paramT)
    {
      this.mPrefix = paramString;
      this.mDefaultValue = paramT;
    }

    public PreferenceFile.SharedPreference<T> get(String paramString)
    {
      return valueWithKey(this.mPrefix + paramString);
    }

    protected abstract PreferenceFile.SharedPreference<T> valueWithKey(String paramString);
  }

  public static abstract class SharedPreference<T>
  {
    final T mDefaultValue;
    PreferenceFile mFile;
    final String mKey;

    protected SharedPreference(PreferenceFile paramPreferenceFile, String paramString, T paramT)
    {
      this.mFile = paramPreferenceFile;
      this.mKey = paramString;
      this.mDefaultValue = paramT;
    }

    public final boolean exists()
    {
      return this.mFile.open().contains(this.mKey);
    }

    public final T get()
    {
      return read(this.mFile.open());
    }

    public final String getKey()
    {
      return this.mKey;
    }

    public final void put(T paramT)
    {
      SharedPreferences.Editor localEditor = this.mFile.open().edit();
      write(localEditor, paramT);
      PreferenceFile.commit(localEditor);
    }

    protected abstract T read(SharedPreferences paramSharedPreferences);

    public final void remove()
    {
      PreferenceFile.commit(this.mFile.open().edit().remove(this.mKey));
    }

    protected abstract void write(SharedPreferences.Editor paramEditor, T paramT);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.config.PreferenceFile
 * JD-Core Version:    0.6.2
 */
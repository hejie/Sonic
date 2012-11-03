package com.google.android.finsky.config;

import android.content.ContentResolver;
import android.content.Context;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.Gservices;

public abstract class GservicesValue<T>
{
  private static GservicesReader sGservicesReader = null;
  private final String mKey;
  private T mOverride = null;

  private GservicesValue(String paramString)
  {
    this.mKey = paramString;
  }

  public static void init(Context paramContext, String[] paramArrayOfString)
  {
    sGservicesReader = new GservicesReaderImpl(paramContext.getContentResolver(), paramArrayOfString);
  }

  public static void initForTests()
  {
    sGservicesReader = new GservicesReaderForTests(null);
  }

  public static GservicesValue<String> partnerSetting(final String paramString1, final String paramString2)
  {
    return new GservicesValue(paramString1, paramString1)
    {
      protected String retrieve()
      {
        return GservicesValue.sGservicesReader.getPartnerString(paramString1, paramString2);
      }
    };
  }

  public static GservicesValue<Boolean> value(final String paramString, final Boolean paramBoolean)
  {
    return new GservicesValue(paramString, paramString)
    {
      protected Boolean retrieve()
      {
        return GservicesValue.sGservicesReader.getBoolean(paramString, paramBoolean);
      }
    };
  }

  public static GservicesValue<Float> value(final String paramString, final Float paramFloat)
  {
    return new GservicesValue(paramString, paramString)
    {
      protected Float retrieve()
      {
        return GservicesValue.sGservicesReader.getFloat(paramString, paramFloat);
      }
    };
  }

  public static GservicesValue<Integer> value(final String paramString, final Integer paramInteger)
  {
    return new GservicesValue(paramString, paramString)
    {
      protected Integer retrieve()
      {
        return GservicesValue.sGservicesReader.getInt(paramString, paramInteger);
      }
    };
  }

  public static GservicesValue<Long> value(final String paramString, final Long paramLong)
  {
    return new GservicesValue(paramString, paramString)
    {
      protected Long retrieve()
      {
        return GservicesValue.sGservicesReader.getLong(paramString, paramLong);
      }
    };
  }

  public static GservicesValue<String> value(final String paramString1, final String paramString2)
  {
    return new GservicesValue(paramString1, paramString1)
    {
      protected String retrieve()
      {
        return GservicesValue.sGservicesReader.getString(paramString1, paramString2);
      }
    };
  }

  public final T get()
  {
    if (this.mOverride != null);
    for (Object localObject = this.mOverride; ; localObject = retrieve())
      return localObject;
  }

  public String getKey()
  {
    return this.mKey;
  }

  public void override(T paramT)
  {
    this.mOverride = paramT;
  }

  protected abstract T retrieve();

  private static abstract interface GservicesReader
  {
    public abstract Boolean getBoolean(String paramString, Boolean paramBoolean);

    public abstract Float getFloat(String paramString, Float paramFloat);

    public abstract Integer getInt(String paramString, Integer paramInteger);

    public abstract Long getLong(String paramString, Long paramLong);

    public abstract String getPartnerString(String paramString1, String paramString2);

    public abstract String getString(String paramString1, String paramString2);
  }

  private static class GservicesReaderForTests
    implements GservicesValue.GservicesReader
  {
    public Boolean getBoolean(String paramString, Boolean paramBoolean)
    {
      return paramBoolean;
    }

    public Float getFloat(String paramString, Float paramFloat)
    {
      return paramFloat;
    }

    public Integer getInt(String paramString, Integer paramInteger)
    {
      return paramInteger;
    }

    public Long getLong(String paramString, Long paramLong)
    {
      return paramLong;
    }

    public String getPartnerString(String paramString1, String paramString2)
    {
      return paramString2;
    }

    public String getString(String paramString1, String paramString2)
    {
      return paramString2;
    }
  }

  private static class GservicesReaderImpl
    implements GservicesValue.GservicesReader
  {
    private final ContentResolver mContentResolver;

    public GservicesReaderImpl(ContentResolver paramContentResolver, String[] paramArrayOfString)
    {
      this.mContentResolver = paramContentResolver;
      Gservices.bulkCacheByPrefix(this.mContentResolver, paramArrayOfString);
    }

    public Boolean getBoolean(String paramString, Boolean paramBoolean)
    {
      return Boolean.valueOf(Gservices.getBoolean(this.mContentResolver, paramString, paramBoolean.booleanValue()));
    }

    public Float getFloat(String paramString, Float paramFloat)
    {
      String str = Gservices.getString(this.mContentResolver, paramString, null);
      if (str != null);
      try
      {
        Float localFloat = Float.valueOf(Float.parseFloat(str));
        paramFloat = localFloat;
        label26: return paramFloat;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label26;
      }
    }

    public Integer getInt(String paramString, Integer paramInteger)
    {
      return Integer.valueOf(Gservices.getInt(this.mContentResolver, paramString, paramInteger.intValue()));
    }

    public Long getLong(String paramString, Long paramLong)
    {
      return Long.valueOf(Gservices.getLong(this.mContentResolver, paramString, paramLong.longValue()));
    }

    public String getPartnerString(String paramString1, String paramString2)
    {
      return GoogleSettingsContract.Partner.getString(this.mContentResolver, paramString1, paramString2);
    }

    public String getString(String paramString1, String paramString2)
    {
      return Gservices.getString(this.mContentResolver, paramString1, paramString2);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.config.GservicesValue
 * JD-Core Version:    0.6.2
 */
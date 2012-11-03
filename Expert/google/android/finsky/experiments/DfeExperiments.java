package com.google.android.finsky.experiments;

import android.text.TextUtils;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.utils.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class DfeExperiments
  implements Experiments
{
  private static final Set<String> sRecognizedExperiments = Sets.newHashSet();
  private final Set<String> mEnabledExperiments = Sets.newHashSet();
  private String mEnabledHeaderValue;
  private final Set<String> mUnsupportedExperiments = Sets.newHashSet();
  private String mUnsupportedHeaderValue;

  static
  {
    sRecognizedExperiments.add("cl:warm_welcome.show_consumption_button");
  }

  public DfeExperiments()
  {
    reset();
  }

  private void reset()
  {
    this.mEnabledExperiments.clear();
    this.mUnsupportedExperiments.clear();
    if (((Boolean)DfeApiConfig.showStagingData.get()).booleanValue())
      this.mEnabledExperiments.add("android_group:eng.finsky.merchandising.staging");
    updateHeaders();
  }

  private void updateHeaders()
  {
    this.mEnabledHeaderValue = TextUtils.join(",", this.mEnabledExperiments);
    this.mUnsupportedHeaderValue = TextUtils.join(",", this.mUnsupportedExperiments);
  }

  public String getEnabledExperimentsHeaderValue()
  {
    try
    {
      String str = this.mEnabledHeaderValue;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getUnsupportedExperimentsHeaderValue()
  {
    try
    {
      String str = this.mUnsupportedHeaderValue;
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean hasEnabledExperiments()
  {
    if (this.mEnabledExperiments.size() > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean hasUnsupportedExperiments()
  {
    try
    {
      int i = this.mUnsupportedExperiments.size();
      if (i > 0)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean isEnabled(String paramString)
  {
    try
    {
      boolean bool = this.mEnabledExperiments.contains(paramString);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setExperiments(Collection<String> paramCollection)
  {
    while (true)
    {
      String str;
      try
      {
        reset();
        Iterator localIterator = paramCollection.iterator();
        if (!localIterator.hasNext())
          break;
        str = (String)localIterator.next();
        if (sRecognizedExperiments.contains(str))
        {
          this.mEnabledExperiments.add(str);
          continue;
        }
      }
      finally
      {
      }
      this.mUnsupportedExperiments.add(str);
    }
    updateHeaders();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.experiments.DfeExperiments
 * JD-Core Version:    0.6.2
 */
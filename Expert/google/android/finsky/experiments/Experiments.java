package com.google.android.finsky.experiments;

import java.util.Collection;

public abstract interface Experiments
{
  public abstract String getEnabledExperimentsHeaderValue();

  public abstract String getUnsupportedExperimentsHeaderValue();

  public abstract boolean hasEnabledExperiments();

  public abstract boolean hasUnsupportedExperiments();

  public abstract boolean isEnabled(String paramString);

  public abstract void setExperiments(Collection<String> paramCollection);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.experiments.Experiments
 * JD-Core Version:    0.6.2
 */
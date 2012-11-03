package com.google.android.finsky.widget.consumption;

import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.TrampolineActivity;

public class NowPlayingTrampoline extends TrampolineActivity
{
  protected boolean enableMultiCorpus()
  {
    return true;
  }

  protected String getCorpusName(int paramInt)
  {
    DfeToc localDfeToc = FinskyApp.get().getToc();
    Toc.CorpusMetadata localCorpusMetadata;
    if (localDfeToc != null)
    {
      localCorpusMetadata = localDfeToc.getCorpus(paramInt);
      if (localCorpusMetadata == null);
    }
    for (String str = localCorpusMetadata.getLibraryName(); ; str = getString(2131165766))
      return str;
  }

  protected int getDialogTitle()
  {
    return 2131165765;
  }

  protected Class<? extends BaseWidgetProvider> getWidgetClass()
  {
    return NowPlayingWidgetProvider.class;
  }

  protected boolean isBackendEnabled(int paramInt)
  {
    if (paramInt != 3);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.consumption.NowPlayingTrampoline
 * JD-Core Version:    0.6.2
 */
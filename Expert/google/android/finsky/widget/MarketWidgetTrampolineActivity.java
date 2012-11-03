package com.google.android.finsky.widget;

import com.android.vending.MarketWidgetProvider;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;

public class MarketWidgetTrampolineActivity extends TrampolineActivity
{
  protected boolean enableMultiCorpus()
  {
    return false;
  }

  protected Class<? extends BaseWidgetProvider> getWidgetClass()
  {
    return MarketWidgetProvider.class;
  }

  protected boolean shouldAllowConfiguration()
  {
    return ((Boolean)G.enableCorpusWidgets.get()).booleanValue();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.MarketWidgetTrampolineActivity
 * JD-Core Version:    0.6.2
 */
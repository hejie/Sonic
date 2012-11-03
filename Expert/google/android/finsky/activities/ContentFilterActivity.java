package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.config.ContentLevel;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import java.util.Iterator;
import java.util.List;

public class ContentFilterActivity extends Activity
  implements View.OnClickListener, ButtonBar.ClickListener
{
  private ButtonBar mButtonBar;
  private final List<View> mCheckables = Lists.newArrayList();
  private LinearLayout mCheckboxesView;
  private ContentLevel mLevel;
  private TextView mMoreInfoView;

  public static String getLabel(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    String str;
    switch (paramInt)
    {
    default:
      str = null;
    case -1:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return str;
      str = localResources.getString(2131165625);
      continue;
      str = localResources.getString(2131165626);
      continue;
      str = localResources.getString(2131165627);
      continue;
      str = localResources.getString(2131165628);
      continue;
      str = localResources.getString(2131165629);
      continue;
      str = localResources.getString(2131165630);
    }
  }

  private void setupCheckbox(LayoutInflater paramLayoutInflater, ContentLevel paramContentLevel, int paramInt1, int paramInt2)
  {
    View localView = paramLayoutInflater.inflate(17367056, this.mCheckboxesView, false);
    TextView localTextView = (TextView)localView.findViewById(16908308);
    localTextView.setText(paramInt2);
    localView.setTag(paramContentLevel);
    localView.setFocusable(true);
    localView.setOnClickListener(this);
    ((Checkable)localView).setChecked(this.mLevel.encompasses(paramContentLevel));
    localView.setId(paramInt1);
    this.mCheckables.add(localView);
    this.mCheckboxesView.addView(localTextView);
  }

  private void setupViews()
  {
    this.mCheckables.clear();
    this.mCheckboxesView.removeAllViews();
    LayoutInflater localLayoutInflater = LayoutInflater.from(getBaseContext());
    setupCheckbox(localLayoutInflater, ContentLevel.EVERYONE, 2131230725, 2131165626);
    setupCheckbox(localLayoutInflater, ContentLevel.LOW_MATURITY, 2131230726, 2131165627);
    setupCheckbox(localLayoutInflater, ContentLevel.MEDIUM_MATURITY, 2131230727, 2131165628);
    setupCheckbox(localLayoutInflater, ContentLevel.HIGH_MATURITY, 2131230728, 2131165629);
    setupCheckbox(localLayoutInflater, ContentLevel.SHOW_ALL, 2131230729, 2131165630);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = getString(2131165631);
    arrayOfObject[1] = G.contentFilterInfoUrl.get();
    arrayOfObject[2] = getString(2131165632);
    String str = String.format("%s <a href='%s'>%s</a>", arrayOfObject);
    this.mMoreInfoView.setText(Html.fromHtml(str));
    this.mMoreInfoView.setMovementMethod(LinkMovementMethod.getInstance());
    this.mButtonBar.setPositiveButtonTitle(2131165634);
    this.mButtonBar.setNegativeButtonTitle(2131165635);
    this.mButtonBar.setClickListener(this);
  }

  public void onClick(View paramView)
  {
    this.mLevel = ((ContentLevel)paramView.getTag());
    Iterator localIterator = this.mCheckables.iterator();
    while (localIterator.hasNext())
    {
      View localView = (View)localIterator.next();
      ((Checkable)localView).setChecked(this.mLevel.encompasses((ContentLevel)localView.getTag()));
    }
    FinskyApp.get().getAnalytics().logPageView("settings", null, "contentFilter?" + this.mLevel.name());
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "contentFilterLevel";
    arrayOfObject[1] = this.mLevel.name();
    localFinskyEventLog.logTag("contentFilter", arrayOfObject);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968620);
    if (paramBundle == null);
    for (this.mLevel = ContentLevel.importFromSettings(this); ; this.mLevel = ContentLevel.convertFromLegacyValue(paramBundle.getInt("level")))
    {
      this.mCheckboxesView = ((LinearLayout)findViewById(2131230854));
      this.mMoreInfoView = ((TextView)findViewById(2131230855));
      this.mButtonBar = ((ButtonBar)findViewById(2131230825));
      setupViews();
      setResult(0);
      return;
    }
  }

  public void onNegativeButtonClick()
  {
    finish();
  }

  public void onPositiveButtonClick()
  {
    int i = ((Integer)FinskyPreferences.contentFilterLevel.get()).intValue();
    int j = this.mLevel.getValue();
    if (i != j)
    {
      FinskyPreferences.contentFilterLevel.put(Integer.valueOf(j));
      setResult(-1);
    }
    finish();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("level", this.mLevel.getValue());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ContentFilterActivity
 * JD-Core Version:    0.6.2
 */
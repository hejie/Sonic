package com.google.android.finsky.activities;

import android.app.backup.BackupManager;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.SearchRecentSuggestions;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.ads.AdSettings;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.CustomActionBar;
import com.google.android.finsky.layout.CustomActionBarFactory;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LinkPreference;
import com.google.android.finsky.utils.VendingPreferences;

public class SettingsActivity extends PreferenceActivity
{
  private String mAccountName;
  private CustomActionBar mActionBar;
  private Analytics mAnalytics;
  private boolean mDestroyed;
  private FinskyEventLog mEventLog;
  private NavigationManager mNavigationManager = new FakeNavigationManager(this);
  private boolean mUserSettingsEnabled;

  private void changePasscode(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      FinskyPreferences.purchasePin.remove();
      FinskyPreferences.purchaseLock.put(Boolean.valueOf(false));
      ((CheckBoxPreference)getPreferenceScreen().findPreference("purchase-lock")).setChecked(false);
    }
    while (true)
    {
      return;
      FinskyPreferences.purchasePin.put(paramString);
    }
  }

  private void configureAboutSection(PreferenceScreen paramPreferenceScreen)
  {
    paramPreferenceScreen.findPreference("build-version").setSummary(getString(2131165706, new Object[] { FinskyApp.get().getPackageInfoRepository().getVersionName(FinskyApp.get().getPackageName()) }));
  }

  private void configureAdPrefsSection(PreferenceScreen paramPreferenceScreen)
  {
    setInterestBasedAds((CheckBoxPreference)paramPreferenceScreen.findPreference("admob-ad"), ((Boolean)VendingPreferences.INTEREST_BASED_ADS_ENABLED.get()).booleanValue());
    LinkPreference localLinkPreference = (LinkPreference)paramPreferenceScreen.findPreference("admob-ad-desc");
    localLinkPreference.setLayoutResource(2130968719);
    String str1 = (String)G.adPrefsLearnMoreUrl.get();
    String str2 = AdSettings.getSigString(getApplicationContext());
    if (str2 != null)
      str1 = str1 + "?sig=" + str2;
    localLinkPreference.setLink(Html.fromHtml(getString(2131165721, new Object[] { str1 })));
  }

  private void configureAutoAddShortcuts(PreferenceScreen paramPreferenceScreen)
  {
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)paramPreferenceScreen.findPreference("auto-add-shortcuts");
    int i;
    int j;
    if (Build.VERSION.SDK_INT >= 14)
    {
      i = 1;
      if (i == 0)
        break label70;
      j = 2131165733;
      label28: localCheckBoxPreference.setTitle(j);
      if (i == 0)
        break label77;
    }
    label70: label77: for (int k = 2131165734; ; k = 2131165732)
    {
      localCheckBoxPreference.setSummary(k);
      localCheckBoxPreference.setChecked(((Boolean)VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue());
      return;
      i = 0;
      break;
      j = 2131165731;
      break label28;
    }
  }

  private void configureAutoUpdateByDefault(PreferenceScreen paramPreferenceScreen)
  {
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)paramPreferenceScreen.findPreference("auto-update-by-default");
    Boolean localBoolean = (Boolean)VendingPreferences.AUTO_UPDATE_BY_DEFAULT.get();
    if (localBoolean == null);
    for (boolean bool = false; ; bool = localBoolean.booleanValue())
    {
      localCheckBoxPreference.setChecked(bool);
      return;
    }
  }

  private void configureUpdateNotifications(PreferenceScreen paramPreferenceScreen)
  {
    ((CheckBoxPreference)paramPreferenceScreen.findPreference("update-notifications")).setChecked(((Boolean)VendingPreferences.NOTIFY_UPDATES.get()).booleanValue());
  }

  private void configureUpdateOverWifiOnly(PreferenceScreen paramPreferenceScreen)
  {
    CheckBoxPreference localCheckBoxPreference = (CheckBoxPreference)paramPreferenceScreen.findPreference("update-over-wifi-only");
    if (localCheckBoxPreference == null);
    while (true)
    {
      return;
      if (FinskyApp.get().getInstallPolicies().hasMobileNetwork())
        localCheckBoxPreference.setChecked(((Boolean)VendingPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue());
      else
        ((PreferenceCategory)paramPreferenceScreen.findPreference("category-general")).removePreference(localCheckBoxPreference);
    }
  }

  private void configureUserControlsSection(PreferenceScreen paramPreferenceScreen)
  {
    if (((Boolean)G.vendingHideContentRating.get()).booleanValue())
      paramPreferenceScreen.removePreference(paramPreferenceScreen.findPreference("content-level"));
    ((CheckBoxPreference)paramPreferenceScreen.findPreference("purchase-lock")).setChecked(((Boolean)FinskyPreferences.purchaseLock.get()).booleanValue());
    syncUserControlsState();
  }

  private void processAdSettingChange(final CheckBoxPreference paramCheckBoxPreference, final boolean paramBoolean)
  {
    paramCheckBoxPreference.setEnabled(false);
    new AdSettings(FinskyApp.get(), FinskyApp.get().getRequestQueue()).enableInterestBasedAds(paramBoolean, new Response.Listener()
    {
      public void onResponse(Boolean paramAnonymousBoolean)
      {
        VendingPreferences.INTEREST_BASED_ADS_ENABLED.put(Boolean.valueOf(paramBoolean));
        SettingsActivity.this.setInterestBasedAds(paramCheckBoxPreference, paramBoolean);
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        SettingsActivity localSettingsActivity = SettingsActivity.this;
        CheckBoxPreference localCheckBoxPreference = paramCheckBoxPreference;
        if (!paramBoolean);
        for (boolean bool = true; ; bool = false)
        {
          localSettingsActivity.setInterestBasedAds(localCheckBoxPreference, bool);
          return;
        }
      }
    });
  }

  private void setInterestBasedAds(CheckBoxPreference paramCheckBoxPreference, boolean paramBoolean)
  {
    if (this.mDestroyed);
    while (true)
    {
      return;
      paramCheckBoxPreference.setChecked(paramBoolean);
      paramCheckBoxPreference.setEnabled(true);
    }
  }

  private void setupActionBar()
  {
    this.mActionBar = CustomActionBarFactory.getInstance(this);
    this.mActionBar.initialize(this.mNavigationManager, this);
    this.mActionBar.updateCurrentBackendId(0);
    this.mActionBar.updateBreadcrumb(getString(2131165652));
  }

  private void syncUserControlsState()
  {
    Preference localPreference = findPreference("unlock-settings");
    boolean bool1;
    if (!this.mUserSettingsEnabled)
    {
      bool1 = true;
      localPreference.setEnabled(bool1);
      findPreference("content-level").setEnabled(this.mUserSettingsEnabled);
      if ((!this.mUserSettingsEnabled) || (TextUtils.isEmpty((CharSequence)FinskyPreferences.purchasePin.get())))
        break label89;
    }
    label89: for (boolean bool2 = true; ; bool2 = false)
    {
      findPreference("purchase-lock").setEnabled(bool2);
      findPreference("change-passcode").setEnabled(this.mUserSettingsEnabled);
      return;
      bool1 = false;
      break;
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 30) && (paramInt2 == -1))
    {
      this.mNavigationManager.clear();
      setResult(40);
      finish();
    }
    while (true)
    {
      return;
      if ((paramInt1 == 31) && (paramInt2 == -1))
      {
        this.mUserSettingsEnabled = true;
        syncUserControlsState();
      }
      else if ((paramInt1 == 32) && (paramInt2 == -1))
      {
        String str = paramIntent.getStringExtra("new-pin");
        if (TextUtils.isEmpty(str))
        {
          changePasscode(null);
        }
        else
        {
          this.mAnalytics.logPageView(null, null, "pinLock.confirm");
          this.mEventLog.logTag("pinLock.confirm", new Object[0]);
          startActivityForResult(PinEntryDialog.getIntent(this, 2131165646, str, "new-pin", false), 33);
        }
      }
      else if ((paramInt1 == 33) && (paramInt2 == -1))
      {
        changePasscode(paramIntent.getStringExtra("new-pin"));
      }
      else
      {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    boolean bool = CustomActionBarFactory.shouldUseSystemActionBar();
    int i;
    if (bool)
    {
      i = 8;
      requestWindowFeature(i);
      super.onCreate(paramBundle);
      if (!bool)
        getWindow().setFeatureInt(7, 2130968576);
      addPreferencesFromResource(2130968837);
      this.mAnalytics = FinskyApp.get().getAnalytics();
      this.mEventLog = FinskyApp.get().getEventLogger();
      this.mAnalytics.logPageView(null, null, "settings");
      this.mEventLog.logTag("settings", new Object[0]);
      this.mAccountName = FinskyApp.get().getCurrentAccountName();
      if (this.mAccountName != null)
        break label131;
      FinskyLog.d("Exit SettingsActivity - no current account.", new Object[0]);
      finish();
    }
    while (true)
    {
      return;
      i = 7;
      break;
      label131: setupActionBar();
      if (paramBundle == null)
        paramBundle = Bundle.EMPTY;
      this.mUserSettingsEnabled = paramBundle.getBoolean("unlocked", TextUtils.isEmpty((CharSequence)FinskyPreferences.purchasePin.get()));
    }
  }

  protected void onDestroy()
  {
    this.mDestroyed = true;
    super.onDestroy();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
    case 16908332:
    }
    for (boolean bool = super.onOptionsItemSelected(paramMenuItem); ; bool = true)
    {
      return bool;
      onBackPressed();
    }
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    String str = paramPreference.getKey();
    int i;
    if ("admob-ad".equals(str))
    {
      CheckBoxPreference localCheckBoxPreference6 = (CheckBoxPreference)paramPreference;
      processAdSettingChange(localCheckBoxPreference6, localCheckBoxPreference6.isChecked());
      i = 0;
    }
    while (true)
    {
      if (i != 0)
        new BackupManager(this).dataChanged();
      return true;
      if ("update-notifications".equals(str))
      {
        CheckBoxPreference localCheckBoxPreference5 = (CheckBoxPreference)paramPreference;
        VendingPreferences.NOTIFY_UPDATES.put(Boolean.valueOf(localCheckBoxPreference5.isChecked()));
        i = 0;
      }
      else if ("auto-update-by-default".equals(str))
      {
        CheckBoxPreference localCheckBoxPreference4 = (CheckBoxPreference)paramPreference;
        VendingPreferences.AUTO_UPDATE_BY_DEFAULT.put(Boolean.valueOf(localCheckBoxPreference4.isChecked()));
        VendingPreferences.HAS_SEEN_AUTO_UPDATE_DEFAULT.put(Boolean.valueOf(true));
        i = 1;
      }
      else if ("update-over-wifi-only".equals(str))
      {
        CheckBoxPreference localCheckBoxPreference3 = (CheckBoxPreference)paramPreference;
        VendingPreferences.AUTO_UPDATE_WIFI_ONLY.put(Boolean.valueOf(localCheckBoxPreference3.isChecked()));
        i = 1;
      }
      else if ("auto-add-shortcuts".equals(str))
      {
        CheckBoxPreference localCheckBoxPreference2 = (CheckBoxPreference)paramPreference;
        VendingPreferences.AUTO_ADD_SHORTCUTS.put(Boolean.valueOf(localCheckBoxPreference2.isChecked()));
        i = 1;
      }
      else if ("clear-history".equals(str))
      {
        FinskyApp.get().getRecentSuggestions().clearHistory();
        i = 0;
      }
      else if ("purchase-lock".equals(str))
      {
        CheckBoxPreference localCheckBoxPreference1 = (CheckBoxPreference)paramPreference;
        FinskyPreferences.purchaseLock.put(Boolean.valueOf(localCheckBoxPreference1.isChecked()));
        i = 1;
      }
      else if ("unlock-settings".equals(str))
      {
        Intent localIntent2 = PinEntryDialog.getIntent(this, 2131165648, (String)FinskyPreferences.purchasePin.get(), null, false);
        this.mAnalytics.logPageView(null, null, "pinLock.unlockSettings");
        this.mEventLog.logTag("pinLock.unlockSettings", new Object[0]);
        startActivityForResult(localIntent2, 31);
        i = 0;
      }
      else if ("content-level".equals(str))
      {
        startActivityForResult(IntentUtils.createAccountSpecificIntent(this, ContentFilterActivity.class, "authAccount", this.mAccountName), 30);
        i = 0;
      }
      else
      {
        if ("change-passcode".equals(str))
        {
          boolean bool;
          if (!TextUtils.isEmpty((String)FinskyPreferences.purchasePin.get()))
          {
            bool = true;
            label397: if (!bool)
              break label469;
          }
          label469: for (int j = 2131165644; ; j = 2131165645)
          {
            Intent localIntent1 = PinEntryDialog.getIntent(this, j, null, "new-pin", bool);
            this.mAnalytics.logPageView(null, null, "pinLock.set");
            this.mEventLog.logTag("pinLock.set", new Object[0]);
            startActivityForResult(localIntent1, 32);
            i = 0;
            break;
            bool = false;
            break label397;
          }
        }
        if ("os-licenses".equals(str))
        {
          startActivity(WebViewDialog.getIntent(this, 2131165639, "file:///android_asset/licenses.html"));
          i = 0;
        }
        else
        {
          i = 0;
        }
      }
    }
  }

  protected void onResume()
  {
    super.onResume();
    PreferenceScreen localPreferenceScreen = getPreferenceScreen();
    configureUpdateNotifications(localPreferenceScreen);
    configureAutoUpdateByDefault(localPreferenceScreen);
    configureAutoAddShortcuts(localPreferenceScreen);
    configureUpdateOverWifiOnly(localPreferenceScreen);
    configureUserControlsSection(localPreferenceScreen);
    configureAdPrefsSection(localPreferenceScreen);
    configureAboutSection(localPreferenceScreen);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putBoolean("unlocked", this.mUserSettingsEnabled);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SettingsActivity
 * JD-Core Version:    0.6.2
 */
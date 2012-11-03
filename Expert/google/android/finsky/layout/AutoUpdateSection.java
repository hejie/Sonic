package com.google.android.finsky.layout;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.VendingPreferences;
import java.util.List;

public class AutoUpdateSection extends LinearLayout
{
  private CheckBox mCheckBox;
  private TextView mLabel;

  public AutoUpdateSection(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bind(final String paramString, Fragment paramFragment, Libraries paramLibraries, final AppStates paramAppStates, Installer paramInstaller)
  {
    removeAllViews();
    LayoutInflater.from(new ContextThemeWrapper(getContext(), 2131624006)).inflate(2130968584, this);
    this.mCheckBox = ((CheckBox)findViewById(2131230746));
    this.mLabel = ((TextView)findViewById(2131230747));
    final FragmentManager localFragmentManager = paramFragment.getFragmentManager();
    updateVisibility(paramString, paramLibraries, paramAppStates, paramInstaller);
    if (getVisibility() != 0)
      return;
    AppStates.AppState localAppState = paramAppStates.getApp(paramString);
    if (localAppState.installerData != null);
    for (InstallerDataStore.AutoUpdateState localAutoUpdateState = localAppState.installerData.getAutoUpdate(); ; localAutoUpdateState = InstallerDataStore.AutoUpdateState.DEFAULT)
    {
      boolean bool1 = ((Boolean)VendingPreferences.AUTO_UPDATE_BY_DEFAULT.get()).booleanValue();
      if (localAutoUpdateState != InstallerDataStore.AutoUpdateState.DEFAULT)
        break label210;
      bool2 = bool1;
      this.mCheckBox.setChecked(bool2);
      new Handler(getContext().getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          AutoUpdateSection.this.mCheckBox.setChecked(bool2);
        }
      });
      this.mCheckBox.setEnabled(true);
      this.mLabel.setEnabled(true);
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AutoUpdateSection.this.mCheckBox.toggle();
          if (AutoUpdateSection.this.mCheckBox.isChecked());
          for (InstallerDataStore.AutoUpdateState localAutoUpdateState = InstallerDataStore.AutoUpdateState.ENABLED; ; localAutoUpdateState = InstallerDataStore.AutoUpdateState.DISABLED)
          {
            paramAppStates.getInstallerDataStore().setAutoUpdate(paramString, localAutoUpdateState);
            if (!((Boolean)VendingPreferences.HAS_SEEN_AUTO_UPDATE_DEFAULT.get()).booleanValue())
              new AutoUpdateSection.AutoUpdateDialog().show(localFragmentManager, "auto_update_dialog");
            if (FinskyApp.get().getAnalytics() != null)
            {
              FinskyApp.get().getAnalytics().logPageView(null, null, "autoUpdate?doc=" + paramString + "&autoupdateenabled=" + AutoUpdateSection.this.mCheckBox.isChecked());
              FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
              Object[] arrayOfObject = new Object[4];
              arrayOfObject[0] = "cidi";
              arrayOfObject[1] = paramString;
              arrayOfObject[2] = "enabled";
              arrayOfObject[3] = Boolean.valueOf(AutoUpdateSection.this.mCheckBox.isChecked());
              localFinskyEventLog.logTag("autoUpdate", arrayOfObject);
            }
            return;
          }
        }
      });
      break;
    }
    label210: if (localAutoUpdateState == InstallerDataStore.AutoUpdateState.ENABLED);
    for (final boolean bool2 = true; ; bool2 = false)
      break;
  }

  public void updateVisibility(String paramString, Libraries paramLibraries, AppStates paramAppStates, Installer paramInstaller)
  {
    if (paramString == null)
      setVisibility(8);
    while (true)
    {
      return;
      if (paramLibraries.getAppOwners(paramString).isEmpty())
      {
        setVisibility(8);
      }
      else
      {
        AppStates.AppState localAppState = paramAppStates.getApp(paramString);
        if (localAppState == null)
        {
          setVisibility(8);
        }
        else
        {
          if (localAppState.packageManagerState != null);
          for (int i = 1; ; i = 0)
          {
            Installer.InstallerState localInstallerState = paramInstaller.getState(paramString);
            if ((i != 0) || (localInstallerState.isDownloadingOrInstalling()))
              break label103;
            setVisibility(8);
            break;
          }
          label103: if ((i != 0) && (localAppState.packageManagerState.isDisabled))
            setVisibility(8);
          else
            setVisibility(0);
        }
      }
    }
  }

  public static class AutoUpdateDialog extends DialogFragment
    implements DialogInterface.OnClickListener
  {
    private void setPreferences(boolean paramBoolean)
    {
      VendingPreferences.AUTO_UPDATE_BY_DEFAULT.put(Boolean.valueOf(paramBoolean));
      VendingPreferences.HAS_SEEN_AUTO_UPDATE_DEFAULT.put(Boolean.valueOf(true));
    }

    public void onCancel(DialogInterface paramDialogInterface)
    {
      super.onCancel(paramDialogInterface);
      setPreferences(false);
    }

    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      switch (paramInt)
      {
      default:
      case -1:
      case -2:
      }
      while (true)
      {
        return;
        dismiss();
        setPreferences(true);
        continue;
        dismiss();
        setPreferences(false);
      }
    }

    public Dialog onCreateDialog(Bundle paramBundle)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
      localBuilder.setMessage(2131165730);
      localBuilder.setPositiveButton(2131165402, this);
      localBuilder.setNegativeButton(2131165403, this);
      return localBuilder.create();
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.AutoUpdateSection
 * JD-Core Version:    0.6.2
 */
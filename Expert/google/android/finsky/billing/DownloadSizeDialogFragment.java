package com.google.android.finsky.billing;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.installer.InstallPolicies;
import java.util.List;

public class DownloadSizeDialogFragment extends DialogFragment
{
  public static DownloadSizeDialogFragment newInstance(Fragment paramFragment, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (!(paramFragment instanceof DownloadSizeDialogListener))
      throw new IllegalArgumentException("targetFragment must implement DownloadSizeDialogListener");
    DownloadSizeDialogFragment localDownloadSizeDialogFragment = new DownloadSizeDialogFragment();
    localDownloadSizeDialogFragment.setTargetFragment(paramFragment, -1);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("setWifiOnly", paramBoolean1);
    localBundle.putBoolean("showWifiOnly", paramBoolean2);
    localDownloadSizeDialogFragment.setArguments(localBundle);
    return localDownloadSizeDialogFragment;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    Bundle localBundle = getArguments();
    final boolean bool1 = localBundle.getBoolean("showWifiOnly");
    final boolean bool2 = localBundle.getBoolean("setWifiOnly");
    boolean bool3 = localBundle.getBoolean("onMobileNetwork");
    FragmentActivity localFragmentActivity = getActivity();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localFragmentActivity);
    localBuilder.setTitle(2131165616);
    View localView = LayoutInflater.from(new ContextThemeWrapper(localFragmentActivity, 2131623999)).inflate(2130968675, null);
    TextView localTextView = (TextView)localView.findViewById(2131230981);
    if (bool1)
    {
      i = 2131165617;
      localTextView.setText(i);
      final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131230982);
      if (bool1)
      {
        localCheckBox.setVisibility(0);
        if (paramBundle == null)
          localCheckBox.setChecked(bool2);
      }
      localBuilder.setView(localView);
      localBuilder.setPositiveButton(2131165622, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (bool1);
          for (boolean bool = localCheckBox.isChecked(); ; bool = bool2)
          {
            ((DownloadSizeDialogFragment.DownloadSizeDialogListener)DownloadSizeDialogFragment.this.getTargetFragment()).onDownloadOk(bool);
            return;
          }
        }
      });
      localBuilder.setNegativeButton(2131165273, null);
      if (FinskyApp.get().getInstallPolicies().isMobileNetwork())
      {
        Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
        if (localFragmentActivity.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0)
          localBuilder.setNeutralButton(2131165621, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              ((DownloadSizeDialogFragment.DownloadSizeDialogListener)DownloadSizeDialogFragment.this.getTargetFragment()).onDownloadWifi();
            }
          });
      }
      return localBuilder.create();
    }
    if (bool3);
    for (int i = 2131165618; ; i = 2131165619)
      break;
  }

  public static abstract interface DownloadSizeDialogListener
  {
    public abstract void onDownloadOk(boolean paramBoolean);

    public abstract void onDownloadWifi();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.DownloadSizeDialogFragment
 * JD-Core Version:    0.6.2
 */
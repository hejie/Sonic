package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.finsky.utils.Utils;

public class BackgroundDataDialog extends DialogFragment
{
  public static void dismissExisting(FragmentManager paramFragmentManager)
  {
    Fragment localFragment = paramFragmentManager.findFragmentByTag("bg_data_dialog");
    if (localFragment != null)
      ((DialogFragment)localFragment).dismiss();
  }

  public static void show(FragmentManager paramFragmentManager, Activity paramActivity)
  {
    if (paramFragmentManager.findFragmentByTag("bg_data_dialog") != null);
    while (true)
    {
      return;
      new BackgroundDataDialog().show(paramFragmentManager, "bg_data_dialog");
    }
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    FragmentActivity localFragmentActivity = getActivity();
    if (localFragmentActivity == null);
    while (true)
    {
      return;
      if (!Utils.isBackgroundDataEnabled(localFragmentActivity))
        ((BackgroundDataSettingListener)localFragmentActivity).onBackgroundDataNotEnabled();
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(getActivity()).setTitle(2131165437).setPositiveButton(2131165439, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Intent localIntent = new Intent("android.settings.SYNC_SETTINGS");
        localIntent.addCategory("android.intent.category.DEFAULT");
        localIntent.setFlags(524288);
        BackgroundDataDialog.this.startActivity(localIntent);
      }
    }).setNegativeButton(2131165440, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ((BackgroundDataDialog.BackgroundDataSettingListener)BackgroundDataDialog.this.getActivity()).onBackgroundDataNotEnabled();
      }
    }).create();
    View localView = localAlertDialog.getLayoutInflater().inflate(2130968687, null);
    ((TextView)localView.findViewById(2131231013)).setText(getString(2131165438));
    localAlertDialog.setView(localView);
    localAlertDialog.setCancelable(true);
    return localAlertDialog;
  }

  public static abstract interface BackgroundDataSettingListener
  {
    public abstract void onBackgroundDataNotEnabled();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.BackgroundDataDialog
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

public class SimpleAlertDialog extends DialogFragment
{
  private Listener getListener()
  {
    Fragment localFragment = getTargetFragment();
    Listener localListener;
    if ((localFragment instanceof Listener))
      localListener = (Listener)localFragment;
    while (true)
    {
      return localListener;
      FragmentActivity localFragmentActivity = getActivity();
      if ((localFragmentActivity instanceof Listener))
        localListener = (Listener)localFragmentActivity;
      else
        localListener = null;
    }
  }

  public static SimpleAlertDialog newInstance(int paramInt1, int paramInt2, int paramInt3)
  {
    return newInstance(paramInt1, null, -1, paramInt2, paramInt3);
  }

  private static SimpleAlertDialog newInstance(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
  {
    SimpleAlertDialog localSimpleAlertDialog = new SimpleAlertDialog();
    Bundle localBundle = new Bundle();
    localBundle.putInt("message_id", paramInt1);
    localBundle.putString("message", paramString);
    localBundle.putInt("layoutId", paramInt2);
    localBundle.putInt("positive_id", paramInt3);
    localBundle.putInt("negative_id", paramInt4);
    localSimpleAlertDialog.setArguments(localBundle);
    return localSimpleAlertDialog;
  }

  public static SimpleAlertDialog newInstance(String paramString, int paramInt1, int paramInt2)
  {
    return newInstance(-1, paramString, -1, paramInt1, paramInt2);
  }

  public static SimpleAlertDialog newInstanceWithLayout(int paramInt1, int paramInt2, int paramInt3)
  {
    return newInstance(-1, null, paramInt1, paramInt2, paramInt3);
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    Listener localListener = getListener();
    if (localListener != null)
    {
      Bundle localBundle1 = getArguments();
      Bundle localBundle2 = localBundle1.getBundle("extra_arguments");
      localListener.onNegativeClick(localBundle1.getInt("target_request_code"), localBundle2);
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    FragmentActivity localFragmentActivity = getActivity();
    Bundle localBundle1 = getArguments();
    int i = localBundle1.getInt("message_id");
    String str = localBundle1.getString("message");
    int j = localBundle1.getInt("layoutId");
    int k = localBundle1.getInt("positive_id");
    int m = localBundle1.getInt("negative_id");
    final Bundle localBundle2 = localBundle1.getBundle("extra_arguments");
    Bundle localBundle3 = localBundle1.getBundle("config_arguments");
    final int n = localBundle1.getInt("target_request_code");
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localFragmentActivity);
    if (i != -1)
      localBuilder.setMessage(i);
    while (true)
    {
      if (k != -1)
        localBuilder.setPositiveButton(k, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            SimpleAlertDialog.this.dismiss();
            SimpleAlertDialog.Listener localListener = SimpleAlertDialog.this.getListener();
            if (localListener != null)
              localListener.onPositiveClick(n, localBundle2);
          }
        });
      if (m != -1)
        localBuilder.setNegativeButton(m, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            SimpleAlertDialog.this.dismiss();
            SimpleAlertDialog.Listener localListener = SimpleAlertDialog.this.getListener();
            if (localListener != null)
              localListener.onNegativeClick(n, localBundle2);
          }
        });
      AlertDialog localAlertDialog = localBuilder.create();
      if (j != -1)
      {
        View localView = localAlertDialog.getLayoutInflater().inflate(j, null);
        localAlertDialog.setView(localView);
        if (((localView instanceof ConfigurableView)) && (localBundle3 != null))
          ((ConfigurableView)localView).configureView(localBundle3);
      }
      return localAlertDialog;
      if (!TextUtils.isEmpty(str))
        localBuilder.setMessage(str);
    }
  }

  public SimpleAlertDialog setCallback(Fragment paramFragment, int paramInt, Bundle paramBundle)
  {
    setTargetFragment(paramFragment, 0);
    if ((paramBundle != null) || (paramInt != 0))
    {
      Bundle localBundle = getArguments();
      localBundle.putBundle("extra_arguments", paramBundle);
      localBundle.putInt("target_request_code", paramInt);
      setArguments(localBundle);
    }
    return this;
  }

  public SimpleAlertDialog setViewConfiguration(Bundle paramBundle)
  {
    Bundle localBundle = getArguments();
    localBundle.putBundle("config_arguments", paramBundle);
    setArguments(localBundle);
    return this;
  }

  public static abstract interface ConfigurableView
  {
    public abstract void configureView(Bundle paramBundle);
  }

  public static abstract interface Listener
  {
    public abstract void onNegativeClick(int paramInt, Bundle paramBundle);

    public abstract void onPositiveClick(int paramInt, Bundle paramBundle);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SimpleAlertDialog
 * JD-Core Version:    0.6.2
 */
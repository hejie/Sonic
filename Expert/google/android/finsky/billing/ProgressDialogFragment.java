package com.google.android.finsky.billing;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment
{
  public static ProgressDialogFragment newInstance(int paramInt)
  {
    ProgressDialogFragment localProgressDialogFragment = new ProgressDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("message_id", paramInt);
    localProgressDialogFragment.setArguments(localBundle);
    return localProgressDialogFragment;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    setCancelable(false);
    ProgressDialog localProgressDialog = new ProgressDialog(getActivity());
    localProgressDialog.setProgressStyle(0);
    int i = getArguments().getInt("message_id", 2131165432);
    localProgressDialog.setMessage(getResources().getString(i));
    localProgressDialog.setCancelable(false);
    localProgressDialog.setIndeterminate(true);
    return localProgressDialog;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.ProgressDialogFragment
 * JD-Core Version:    0.6.2
 */
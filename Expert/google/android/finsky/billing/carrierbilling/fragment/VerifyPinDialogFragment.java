package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class VerifyPinDialogFragment extends DialogFragment
  implements DialogInterface.OnClickListener
{
  VerifyPinListener mListener;

  public static VerifyPinDialogFragment newInstance()
  {
    VerifyPinDialogFragment localVerifyPinDialogFragment = new VerifyPinDialogFragment();
    localVerifyPinDialogFragment.setCancelable(true);
    return localVerifyPinDialogFragment;
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.mListener != null)
      this.mListener.onVerifyCancel();
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    ProgressDialog localProgressDialog = new ProgressDialog(getActivity());
    localProgressDialog.setTitle(getResources().getString(2131165266));
    localProgressDialog.setMessage(getResources().getString(2131165301));
    localProgressDialog.setProgressStyle(0);
    localProgressDialog.setIndeterminate(true);
    localProgressDialog.setCancelable(false);
    localProgressDialog.setCanceledOnTouchOutside(false);
    localProgressDialog.setButton(-1, getResources().getString(2131165306), this);
    return localProgressDialog;
  }

  public void setOnResultListener(VerifyPinListener paramVerifyPinListener)
  {
    this.mListener = paramVerifyPinListener;
  }

  public static abstract interface VerifyPinListener
  {
    public abstract void onVerifyCancel();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.VerifyPinDialogFragment
 * JD-Core Version:    0.6.2
 */
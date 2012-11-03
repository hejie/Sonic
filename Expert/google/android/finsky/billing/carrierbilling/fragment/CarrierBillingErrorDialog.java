package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.google.android.finsky.FinskyApp;

public class CarrierBillingErrorDialog extends DialogFragment
  implements DialogInterface.OnClickListener
{
  CarrierBillingErrorListener mListener;

  public static CarrierBillingErrorDialog newInstance(String paramString, boolean paramBoolean)
  {
    CarrierBillingErrorDialog localCarrierBillingErrorDialog = new CarrierBillingErrorDialog();
    localCarrierBillingErrorDialog.setCancelable(false);
    Bundle localBundle = new Bundle();
    localBundle.putString("error_message", paramString);
    localBundle.putBoolean("fatal_error", paramBoolean);
    localCarrierBillingErrorDialog.setArguments(localBundle);
    return localCarrierBillingErrorDialog;
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.mListener != null)
      this.mListener.onErrorDismiss(getArguments().getBoolean("fatal_error"));
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setTitle(FinskyApp.get().getString(2131165442));
    localBuilder.setMessage(getArguments().getString("error_message"));
    localBuilder.setPositiveButton(17039370, this).setCancelable(false);
    return localBuilder.create();
  }

  public void setOnResultListener(CarrierBillingErrorListener paramCarrierBillingErrorListener)
  {
    this.mListener = paramCarrierBillingErrorListener;
  }

  public static abstract interface CarrierBillingErrorListener
  {
    public abstract void onErrorDismiss(boolean paramBoolean);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingErrorDialog
 * JD-Core Version:    0.6.2
 */
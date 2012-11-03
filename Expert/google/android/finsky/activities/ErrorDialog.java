package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.utils.FinskyLog;

public class ErrorDialog extends DialogFragment
{
  private boolean mIsRemoved = false;

  public ErrorDialog()
  {
    setCancelable(true);
  }

  private static ErrorDialog newInstance(String paramString1, String paramString2, boolean paramBoolean)
  {
    Bundle localBundle = new Bundle();
    if (paramString1 == null)
      paramString1 = FinskyApp.get().getString(2131165442);
    localBundle.putString("title", paramString1);
    localBundle.putString("html_message", paramString2);
    localBundle.putBoolean("go_back", paramBoolean);
    ErrorDialog localErrorDialog = new ErrorDialog();
    localErrorDialog.setArguments(localBundle);
    return localErrorDialog;
  }

  public static ErrorDialog show(FragmentManager paramFragmentManager, String paramString1, String paramString2, boolean paramBoolean)
  {
    paramFragmentManager.executePendingTransactions();
    ErrorDialog localErrorDialog1 = (ErrorDialog)paramFragmentManager.findFragmentByTag("error_dialog");
    FragmentTransaction localFragmentTransaction;
    if (localErrorDialog1 != null)
      localFragmentTransaction = paramFragmentManager.beginTransaction();
    try
    {
      localErrorDialog1.mIsRemoved = true;
      localFragmentTransaction.remove(localErrorDialog1).addToBackStack(null).commit();
      ErrorDialog localErrorDialog2 = newInstance(paramString1, paramString2, paramBoolean);
      localErrorDialog2.show(paramFragmentManager, "error_dialog");
      return localErrorDialog2;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
        FinskyLog.w("Double remove of error dialog fragment: " + localErrorDialog1, new Object[0]);
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(getActivity()).setTitle(getArguments().getString("title")).setPositiveButton(17039370, null).setCancelable(true).create();
    View localView = localAlertDialog.getLayoutInflater().inflate(2130968687, null);
    TextView localTextView = (TextView)localView.findViewById(2131231013);
    localTextView.setText(Html.fromHtml(getArguments().getString("html_message")));
    localTextView.setMovementMethod(LinkMovementMethod.getInstance());
    localAlertDialog.setView(localView);
    return localAlertDialog;
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if ((!this.mIsRemoved) && (getActivity() != null) && (getArguments().getBoolean("go_back")))
    {
      if (!(getActivity() instanceof PageFragmentHost))
        break label54;
      ((PageFragmentHost)getActivity()).goBack();
    }
    while (true)
    {
      super.onDismiss(paramDialogInterface);
      return;
      label54: FinskyLog.wtf("Dialog not hosted by PageFragmentHost. Cannot navigate back.", new Object[0]);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ErrorDialog
 * JD-Core Version:    0.6.2
 */
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FlagItemUserMessageDialog extends DialogFragment
{
  private String mMessage;

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

  public static FlagItemUserMessageDialog newInstance(int paramInt)
  {
    FlagItemUserMessageDialog localFlagItemUserMessageDialog = new FlagItemUserMessageDialog();
    Bundle localBundle = new Bundle();
    localBundle.putInt("prompt_string_res_id", paramInt);
    localFlagItemUserMessageDialog.setArguments(localBundle);
    return localFlagItemUserMessageDialog;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    FragmentActivity localFragmentActivity = getActivity();
    Bundle localBundle1 = getArguments();
    int i = localBundle1.getInt("prompt_string_res_id");
    if (paramBundle != null);
    for (Bundle localBundle2 = paramBundle; ; localBundle2 = localBundle1)
    {
      this.mMessage = localBundle2.getString("previous_message");
      View localView = LayoutInflater.from(getActivity()).inflate(2130968697, null, false);
      final TextView localTextView = (TextView)localView.findViewById(2131231044);
      localTextView.setText(this.mMessage);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(localFragmentActivity);
      localBuilder.setTitle(i);
      localBuilder.setView(localView);
      localBuilder.setCancelable(true);
      localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FlagItemUserMessageDialog.Listener localListener = FlagItemUserMessageDialog.this.getListener();
          if (localListener != null)
            localListener.onPositiveClick(localTextView.getText().toString());
        }
      });
      localBuilder.setNegativeButton(17039360, null);
      return localBuilder.create();
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (!TextUtils.isEmpty(this.mMessage))
      paramBundle.putString("previous_message", this.mMessage);
  }

  public void onStart()
  {
    super.onStart();
    AlertDialog localAlertDialog = (AlertDialog)getDialog();
    final Button localButton = localAlertDialog.getButton(-1);
    if (!TextUtils.isEmpty(this.mMessage));
    for (boolean bool = true; ; bool = false)
    {
      localButton.setEnabled(bool);
      ((TextView)localAlertDialog.findViewById(2131231044)).addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          FlagItemUserMessageDialog localFlagItemUserMessageDialog = FlagItemUserMessageDialog.this;
          String str;
          Button localButton;
          if (paramAnonymousEditable == null)
          {
            str = null;
            FlagItemUserMessageDialog.access$102(localFlagItemUserMessageDialog, str);
            localButton = localButton;
            if (TextUtils.isEmpty(FlagItemUserMessageDialog.this.mMessage))
              break label55;
          }
          label55: for (boolean bool = true; ; bool = false)
          {
            localButton.setEnabled(bool);
            return;
            str = paramAnonymousEditable.toString();
            break;
          }
        }

        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
        }

        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
        }
      });
      return;
    }
  }

  public static abstract interface Listener
  {
    public abstract void onPositiveClick(String paramString);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FlagItemUserMessageDialog
 * JD-Core Version:    0.6.2
 */
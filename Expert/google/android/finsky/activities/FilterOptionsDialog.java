package com.google.android.finsky.activities;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class FilterOptionsDialog extends DialogFragment
{
  private final boolean[] mChecked = new boolean[2];

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

  public static FilterOptionsDialog newInstance(boolean paramBoolean1, boolean paramBoolean2)
  {
    FilterOptionsDialog localFilterOptionsDialog = new FilterOptionsDialog();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("filterByVersion", paramBoolean1);
    localBundle.putBoolean("filterByDevice", paramBoolean2);
    localFilterOptionsDialog.setArguments(localBundle);
    return localFilterOptionsDialog;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    FragmentActivity localFragmentActivity = getActivity();
    Bundle localBundle = getArguments();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localFragmentActivity);
    localBuilder.setTitle(2131165535);
    String[] arrayOfString = new String[this.mChecked.length];
    arrayOfString[0] = localFragmentActivity.getString(2131165537);
    arrayOfString[1] = localFragmentActivity.getString(2131165536);
    this.mChecked[0] = localBundle.getBoolean("filterByVersion");
    this.mChecked[1] = localBundle.getBoolean("filterByDevice");
    localBuilder.setMultiChoiceItems(arrayOfString, this.mChecked, new DialogInterface.OnMultiChoiceClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        FilterOptionsDialog.this.mChecked[paramAnonymousInt] = paramAnonymousBoolean;
      }
    });
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FilterOptionsDialog.Listener localListener = FilterOptionsDialog.this.getListener();
        if (localListener != null)
          localListener.onReviewFilterChanged(FilterOptionsDialog.this.mChecked[0], FilterOptionsDialog.this.mChecked[1]);
        FilterOptionsDialog.this.dismiss();
      }
    });
    return localBuilder.create();
  }

  public static abstract interface Listener
  {
    public abstract void onReviewFilterChanged(boolean paramBoolean1, boolean paramBoolean2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FilterOptionsDialog
 * JD-Core Version:    0.6.2
 */
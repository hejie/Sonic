package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.widget.Button;

public class RateReviewDialog extends DialogFragment
{
  CommentRating mRating = null;

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

  private CommentRating getRatingForIndex(int paramInt)
  {
    CommentRating[] arrayOfCommentRating = CommentRating.values();
    int i = arrayOfCommentRating.length;
    int j = 0;
    CommentRating localCommentRating;
    if (j < i)
    {
      localCommentRating = arrayOfCommentRating[j];
      if (localCommentRating.getIndex() != paramInt);
    }
    while (true)
    {
      return localCommentRating;
      j++;
      break;
      localCommentRating = null;
    }
  }

  public static RateReviewDialog newInstance(String paramString1, String paramString2, CommentRating paramCommentRating)
  {
    RateReviewDialog localRateReviewDialog = new RateReviewDialog();
    Bundle localBundle = new Bundle();
    localBundle.putString("doc_id", paramString1);
    localBundle.putString("rating_id", paramString2);
    if (paramCommentRating != null)
      localBundle.putInt("previous_rating", paramCommentRating.getIndex());
    localRateReviewDialog.setArguments(localBundle);
    return localRateReviewDialog;
  }

  private void setRating(int paramInt)
  {
    this.mRating = getRatingForIndex(paramInt);
    syncOkButtonState();
  }

  private void syncOkButtonState()
  {
    if (this.mRating != null);
    for (boolean bool = true; ; bool = false)
    {
      ((AlertDialog)getDialog()).getButton(-1).setEnabled(bool);
      return;
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    ContextThemeWrapper localContextThemeWrapper = new ContextThemeWrapper(getActivity(), 2131623994);
    Bundle localBundle1 = getArguments();
    final String str1 = localBundle1.getString("rating_id");
    final String str2 = localBundle1.getString("doc_id");
    if (paramBundle != null);
    int i;
    CharSequence[] arrayOfCharSequence;
    for (Bundle localBundle2 = paramBundle; ; localBundle2 = localBundle1)
    {
      i = localBundle2.getInt("previous_rating", -1);
      this.mRating = getRatingForIndex(i);
      arrayOfCharSequence = new CharSequence[CommentRating.values().length];
      for (CommentRating localCommentRating : CommentRating.values())
        arrayOfCharSequence[localCommentRating.getIndex()] = localContextThemeWrapper.getString(localCommentRating.getTextResourceId());
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContextThemeWrapper);
    localBuilder.setTitle(2131165578);
    localBuilder.setCancelable(true);
    localBuilder.setSingleChoiceItems(arrayOfCharSequence, i, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        RateReviewDialog.this.setRating(paramAnonymousInt);
        RateReviewDialog.this.syncOkButtonState();
      }
    });
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        RateReviewDialog.Listener localListener = RateReviewDialog.this.getListener();
        if ((localListener != null) && (RateReviewDialog.this.mRating != null))
          localListener.onRateReview(str2, str1, RateReviewDialog.this.mRating);
      }
    });
    localBuilder.setNegativeButton(17039360, null);
    return localBuilder.create();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mRating != null)
      paramBundle.putInt("previous_rating", this.mRating.getIndex());
  }

  public void onStart()
  {
    super.onStart();
    syncOkButtonState();
  }

  public static enum CommentRating
  {
    private int mDisplayStringId;
    private int mIndex;
    private int mRpcId;

    static
    {
      HELPFUL = new CommentRating("HELPFUL", 1, 1, 0, 2131165580);
      NOT_HELPFUL = new CommentRating("NOT_HELPFUL", 2, 2, 1, 2131165581);
      CommentRating[] arrayOfCommentRating = new CommentRating[3];
      arrayOfCommentRating[0] = SPAM;
      arrayOfCommentRating[1] = HELPFUL;
      arrayOfCommentRating[2] = NOT_HELPFUL;
    }

    private CommentRating(int paramInt1, int paramInt2, int paramInt3)
    {
      this.mRpcId = paramInt1;
      this.mIndex = paramInt2;
      this.mDisplayStringId = paramInt3;
    }

    private int getIndex()
    {
      return this.mIndex;
    }

    private int getTextResourceId()
    {
      return this.mDisplayStringId;
    }

    public int getRpcId()
    {
      return this.mRpcId;
    }
  }

  public static abstract interface Listener
  {
    public abstract void onRateReview(String paramString1, String paramString2, RateReviewDialog.CommentRating paramCommentRating);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.RateReviewDialog
 * JD-Core Version:    0.6.2
 */
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import com.google.android.finsky.remoting.protos.DocAnnotations.PlusProfile;
import com.google.android.finsky.remoting.protos.Rev.Review;
import com.google.android.finsky.utils.ParcelableProto;

public class ReviewDialog extends DialogFragment
{
  private static final int[] DESCRIPTION_MAP = { 2131165570, 2131165571, 2131165572, 2131165573, 2131165574, 2131165575 };
  private TextView mCommentView;
  TextWatcher mEmptyTextWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
    }

    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      ReviewDialog.this.syncOkButtonState();
    }
  };
  private String mPreviousReviewId;
  private RatingBar mRatingBar;
  private boolean mRequiresPlusCheck = true;
  private boolean mReviewIsPublic = false;
  private int mReviewMode;
  private TextView mTitleView;
  private DocAnnotations.PlusProfile mUserProfile;

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

  private int getRatingDescription(float paramFloat)
  {
    return DESCRIPTION_MAP[Math.round(paramFloat)];
  }

  private String getUserComment()
  {
    return this.mCommentView.getText().toString().trim();
  }

  private int getUserRating()
  {
    return Math.round(this.mRatingBar.getRating());
  }

  private String getUserTitle()
  {
    return this.mTitleView.getText().toString().trim();
  }

  private boolean isDeletingReview()
  {
    if ((this.mPreviousReviewId != null) && (getUserRating() == 0) && (TextUtils.isEmpty(getUserTitle())) && (TextUtils.isEmpty(getUserComment())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static ReviewDialog newInstance(int paramInt1, String paramString, Rev.Review paramReview, int paramInt2)
  {
    ReviewDialog localReviewDialog = new ReviewDialog();
    Bundle localBundle = new Bundle();
    localBundle.putInt("review_mode", paramInt1);
    localBundle.putString("doc_id", paramString);
    localBundle.putInt("backend", paramInt2);
    if (paramReview != null);
    for (String str = paramReview.getCommentId(); ; str = null)
    {
      localBundle.putString("previous_review_id", str);
      if (paramReview != null)
      {
        localBundle.putInt("previous_rating", paramReview.getStarRating());
        localBundle.putString("previous_title", paramReview.getTitle());
        localBundle.putString("previous_comment", paramReview.getComment());
        localBundle.putParcelable("previous_review_profile", ParcelableProto.forProto(paramReview.getPlusProfile()));
      }
      localReviewDialog.setArguments(localBundle);
      return localReviewDialog;
    }
  }

  private void syncOkButtonState()
  {
    AlertDialog localAlertDialog = (AlertDialog)getDialog();
    if (localAlertDialog == null)
      return;
    Button localButton = localAlertDialog.getButton(-1);
    if (isDeletingReview())
    {
      if (!this.mRequiresPlusCheck);
      for (bool1 = true; ; bool1 = false)
      {
        localButton.setText(2131165547);
        localButton.setEnabled(bool1);
        break;
      }
    }
    label70: boolean bool2;
    boolean bool3;
    if ((getUserRating() > 0) && (!this.mRequiresPlusCheck))
    {
      bool1 = true;
      if (bool1)
      {
        bool2 = TextUtils.isEmpty(getUserTitle());
        bool3 = TextUtils.isEmpty(getUserComment());
        if (this.mReviewMode != 3)
          break label131;
        if ((bool2) || (bool3))
          break label126;
      }
    }
    label126: for (boolean bool1 = true; ; bool1 = false)
    {
      localButton.setText(2131165599);
      break;
      bool1 = false;
      break label70;
    }
    label131: if ((!bool2) || (bool3));
    for (bool1 = true; ; bool1 = false)
      break;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    Bundle localBundle1 = getArguments();
    this.mReviewMode = localBundle1.getInt("review_mode");
    final String str1 = localBundle1.getString("doc_id");
    localBundle1.getInt("backend");
    this.mPreviousReviewId = localBundle1.getString("previous_review_id");
    ((DocAnnotations.PlusProfile)ParcelableProto.getProtoFromBundle(localBundle1, "previous_review_profile"));
    Bundle localBundle2;
    String str2;
    String str3;
    ContextThemeWrapper localContextThemeWrapper;
    AlertDialog.Builder localBuilder;
    View localView;
    final TextView localTextView;
    if (paramBundle != null)
    {
      localBundle2 = paramBundle;
      int i = localBundle2.getInt("previous_rating", 0);
      str2 = localBundle2.getString("previous_title");
      str3 = localBundle2.getString("previous_comment");
      localContextThemeWrapper = new ContextThemeWrapper(getActivity(), 2131623999);
      localBuilder = new AlertDialog.Builder(localContextThemeWrapper);
      localView = LayoutInflater.from(localContextThemeWrapper).inflate(2130968824, null);
      this.mRatingBar = ((RatingBar)localView.findViewById(2131231211));
      localTextView = (TextView)localView.findViewById(2131231212);
      this.mTitleView = ((EditText)localView.findViewById(2131231213));
      this.mCommentView = ((EditText)localView.findViewById(2131231214));
      this.mRatingBar.setRating(i);
      localTextView.setText(getRatingDescription(i));
      if (this.mReviewMode != 1)
        break label357;
      this.mTitleView.setVisibility(8);
      this.mCommentView.setVisibility(8);
    }
    while (true)
    {
      localBuilder.setTitle(2131165567);
      localBuilder.setView(localView);
      setCancelable(true);
      this.mTitleView.addTextChangedListener(this.mEmptyTextWatcher);
      this.mCommentView.addTextChangedListener(this.mEmptyTextWatcher);
      localBuilder.setPositiveButton(localContextThemeWrapper.getString(17039370), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ReviewDialog.Listener localListener = ReviewDialog.this.getListener();
          if (localListener != null)
          {
            if (!ReviewDialog.this.isDeletingReview())
              break label40;
            localListener.onDeleteReview(str1, ReviewDialog.this.mPreviousReviewId);
          }
          while (true)
          {
            return;
            label40: localListener.onSaveReview(str1, ReviewDialog.this.getUserRating(), ReviewDialog.this.getUserTitle(), ReviewDialog.this.getUserComment(), ReviewDialog.this.mUserProfile, ReviewDialog.this.mReviewIsPublic);
          }
        }
      });
      localBuilder.setNegativeButton(localContextThemeWrapper.getString(17039360), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
        }
      });
      this.mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
      {
        public void onRatingChanged(RatingBar paramAnonymousRatingBar, float paramAnonymousFloat, boolean paramAnonymousBoolean)
        {
          ReviewDialog.this.syncOkButtonState();
          localTextView.setText(ReviewDialog.this.getRatingDescription(paramAnonymousFloat));
        }
      });
      AlertDialog localAlertDialog = localBuilder.create();
      this.mRequiresPlusCheck = false;
      return localAlertDialog;
      localBundle2 = localBundle1;
      break;
      label357: this.mTitleView.setText(str2);
      this.mCommentView.setText(str3);
    }
  }

  public void onDestroyView()
  {
    this.mTitleView.removeTextChangedListener(this.mEmptyTextWatcher);
    this.mCommentView.removeTextChangedListener(this.mEmptyTextWatcher);
    super.onDestroyView();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("previous_rating", getUserRating());
    paramBundle.putString("previous_title", getUserTitle());
    paramBundle.putString("previous_comment", getUserComment());
  }

  public void onStart()
  {
    super.onStart();
    syncOkButtonState();
  }

  public static abstract interface Listener
  {
    public abstract void onDeleteReview(String paramString1, String paramString2);

    public abstract void onSaveReview(String paramString1, int paramInt, String paramString2, String paramString3, DocAnnotations.PlusProfile paramPlusProfile, boolean paramBoolean);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.ReviewDialog
 * JD-Core Version:    0.6.2
 */
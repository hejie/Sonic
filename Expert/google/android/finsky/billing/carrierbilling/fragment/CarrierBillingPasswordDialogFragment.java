package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class CarrierBillingPasswordDialogFragment extends DialogFragment
  implements ButtonBar.ClickListener
{
  private CarrierBillingPasswordResultListener mListener;
  private View mMainPasswordView;
  private EditText mPasswordEditText;
  private View mProgressIndicator;

  private String createPasswordForgotHtml(String paramString)
  {
    return "<a href=\"" + paramString + "\">" + getString(2131165274) + "</a>";
  }

  public static CarrierBillingPasswordDialogFragment newInstance(String paramString1, String paramString2)
  {
    CarrierBillingPasswordDialogFragment localCarrierBillingPasswordDialogFragment = new CarrierBillingPasswordDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("password_prompt", paramString1);
    localBundle.putString("password_forgot_url", paramString2);
    localCarrierBillingPasswordDialogFragment.setArguments(localBundle);
    return localCarrierBillingPasswordDialogFragment;
  }

  public void clearPasswordField()
  {
    this.mPasswordEditText.setText("");
  }

  public void hideProgressIndicator()
  {
    this.mMainPasswordView.setVisibility(0);
    this.mProgressIndicator.setVisibility(8);
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    this.mListener.onCarrierBillingPasswordResult(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult.CANCELED, null);
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    View localView = View.inflate(getActivity(), 2130968612, null);
    Bundle localBundle = getArguments();
    String str1 = localBundle.getString("password_prompt");
    String str2 = localBundle.getString("password_forgot_url");
    this.mMainPasswordView = localView.findViewById(2131230826);
    this.mProgressIndicator = localView.findViewById(2131230808);
    hideProgressIndicator();
    TextView localTextView1 = (TextView)localView.findViewById(2131230828);
    TextView localTextView2 = (TextView)localView.findViewById(2131230830);
    this.mPasswordEditText = ((EditText)localView.findViewById(2131230829));
    ButtonBar localButtonBar = (ButtonBar)localView.findViewById(2131230825);
    localButtonBar.setClickListener(this);
    localButtonBar.setPositiveButtonTitle(2131165469);
    localButtonBar.setPadding(2, 4, 2, localButtonBar.getPaddingBottom());
    localTextView1.setText(str1);
    if (TextUtils.isEmpty(str2))
      localTextView2.setVisibility(8);
    while (true)
    {
      AlertDialog localAlertDialog = new AlertDialog.Builder(getActivity()).setTitle(2131165272).create();
      localAlertDialog.setView(localView, 0, 5, 0, 0);
      return localAlertDialog;
      localTextView2.setText(Html.fromHtml(createPasswordForgotHtml(str2)));
      localTextView2.setMovementMethod(LinkMovementMethod.getInstance());
    }
  }

  public void onNegativeButtonClick()
  {
    this.mListener.onCarrierBillingPasswordResult(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult.CANCELED, null);
  }

  public void onPositiveButtonClick()
  {
    String str = this.mPasswordEditText.getText().toString();
    if (TextUtils.isEmpty(str))
      Toast.makeText(getActivity(), 2131165275, 0).show();
    while (true)
    {
      return;
      this.mListener.onCarrierBillingPasswordResult(CarrierBillingPasswordDialogFragment.CarrierBillingPasswordResultListener.PasswordResult.SUCCESS, str);
    }
  }

  public void setOnResultListener(CarrierBillingPasswordResultListener paramCarrierBillingPasswordResultListener)
  {
    this.mListener = paramCarrierBillingPasswordResultListener;
  }

  public void showProgressIndicator()
  {
    this.mMainPasswordView.setVisibility(4);
    this.mProgressIndicator.setVisibility(0);
  }

  public static abstract interface CarrierBillingPasswordResultListener
  {
    public abstract void onCarrierBillingPasswordResult(PasswordResult paramPasswordResult, String paramString);

    public static enum PasswordResult
    {
      static
      {
        FAILURE = new PasswordResult("FAILURE", 1);
        CANCELED = new PasswordResult("CANCELED", 2);
        PasswordResult[] arrayOfPasswordResult = new PasswordResult[3];
        arrayOfPasswordResult[0] = SUCCESS;
        arrayOfPasswordResult[1] = FAILURE;
        arrayOfPasswordResult[2] = CANCELED;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.CarrierBillingPasswordDialogFragment
 * JD-Core Version:    0.6.2
 */
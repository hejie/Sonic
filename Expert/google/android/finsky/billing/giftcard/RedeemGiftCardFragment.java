package com.google.android.finsky.billing.giftcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.finsky.activities.AddressChallengeDialog;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AddressChallenge;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedeemGiftCardFragment extends Fragment
  implements View.OnClickListener, SimpleAlertDialog.Listener
{
  private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("[\\s-]");
  private ChallengeProtos.AddressChallenge mAddressChallenge;
  private String mBalance;
  private TextView mBalanceView;
  private EditText mCodeEntry;
  private View mContentView;
  private Button mContinueButton;
  private ViewGroup mFootersContainer;
  private List<String> mFootersHtml;
  private Listener mListener;
  private View mLoadingIndicator;
  private View mProgressBar;
  private Button mRedeemButton;
  private boolean mShowErrorFinish;
  private String mShowErrorMessage;
  private boolean mSwitchToContinueForm;
  private boolean mSwitchToLoading;
  private boolean mSwitchToProgress;
  private boolean mSwitchToRedeemForm;
  private String mUserMessage;
  private TextView mUserMessageView;

  private void clearCodeError()
  {
    this.mCodeEntry.setError(null);
  }

  private void internalShowChallenge(Bundle paramBundle)
  {
    ChallengeProtos.Challenge localChallenge = new ChallengeProtos.Challenge().setAddressChallenge(this.mAddressChallenge);
    if (getArguments().containsKey("address_challenge_params"));
    for (Bundle localBundle = new Bundle(getArguments().getBundle("address_challenge_params")); ; localBundle = new Bundle())
    {
      localBundle.putInt("AddressChallengeFlow.resultFormat", 1);
      localBundle.putBoolean("AddressChallengeFlow.finishOnSwitchCountry", true);
      if (paramBundle != null)
        localBundle.putBundle("AddressChallengeFlow.previousState", paramBundle);
      startActivityForResult(AddressChallengeDialog.getIntent(0, localChallenge, localBundle), 2);
      return;
    }
  }

  public static RedeemGiftCardFragment newInstance(String paramString, Bundle paramBundle)
  {
    RedeemGiftCardFragment localRedeemGiftCardFragment = new RedeemGiftCardFragment();
    paramBundle.putBoolean("AddressChallengeFlow.finishOnSwitchCountry", true);
    Bundle localBundle = new Bundle();
    localBundle.putString("authAccount", paramString);
    localBundle.putBundle("address_challenge_params", paramBundle);
    localRedeemGiftCardFragment.setArguments(localBundle);
    return localRedeemGiftCardFragment;
  }

  private String normalizeCode(String paramString)
  {
    return IGNORED_CHARS_PATTERN.matcher(paramString).replaceAll("").toUpperCase().replace('I', '1').replace('O', '0');
  }

  private void notifyListenerCancel()
  {
    if (this.mListener != null)
      this.mListener.onCancel();
  }

  private void notifyListenerCountrySwitch(String paramString, Bundle paramBundle)
  {
    if (this.mListener != null)
      this.mListener.onCountrySwitch(paramString, paramBundle);
  }

  private void notifyListenerRedeem(BillingAddress.Address paramAddress, ArrayList<String> paramArrayList)
  {
    if (this.mListener != null)
    {
      String str = normalizeCode(this.mCodeEntry.getText().toString());
      this.mListener.onRedeem(str, paramAddress, paramArrayList);
    }
  }

  private void showCodeError(String paramString)
  {
    this.mCodeEntry.setError(paramString);
  }

  private void turnOffProgress()
  {
    this.mProgressBar.setVisibility(8);
    this.mCodeEntry.setEnabled(true);
    this.mRedeemButton.setEnabled(true);
    this.mCodeEntry.setVisibility(0);
    this.mContinueButton.setVisibility(8);
  }

  public void clearGiftCardCode()
  {
    this.mCodeEntry.setText(null);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Bundle localBundle;
    if (paramInt1 == 2)
      if (paramInt2 == -1)
      {
        this.mAddressChallenge = null;
        localBundle = paramIntent.getBundleExtra("challenge_response");
        if (!localBundle.containsKey("AddressChallengeFlow.switchCountry"))
          break label52;
        notifyListenerCountrySwitch(localBundle.getString("AddressChallengeFlow.switchCountry"), localBundle.getBundle("AddressChallengeFlow.currentState"));
      }
    while (true)
    {
      return;
      label52: notifyListenerRedeem((BillingAddress.Address)ParcelableProto.getProtoFromBundle(localBundle, "AddressChallengeFlow.address"), localBundle.getStringArrayList("AddressChallengeFlow.checkedCheckboxes"));
      continue;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131231203)
    {
      String str = normalizeCode(this.mCodeEntry.getText().toString());
      if ((!GiftCardVerifier.verifyGiftCardCode(str)) && (!PromoCodeVerifier.verifyPromoCode(str)))
        showCodeError(getString(2131165313));
    }
    while (true)
    {
      return;
      if (this.mAddressChallenge != null)
      {
        clearCodeError();
        internalShowChallenge(null);
      }
      else if (this.mListener != null)
      {
        clearCodeError();
        notifyListenerRedeem(null, null);
      }
      else
      {
        FinskyLog.wtf("No listener registered.", new Object[0]);
        continue;
        if (paramView.getId() == 2131230798)
          notifyListenerCancel();
      }
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968820, paramViewGroup, false);
    ((TextView)localView.findViewById(2131230995)).setText(getArguments().getString("authAccount"));
    this.mRedeemButton = ((Button)localView.findViewById(2131231203));
    this.mRedeemButton.setOnClickListener(this);
    this.mContinueButton = ((Button)localView.findViewById(2131230798));
    this.mContinueButton.setOnClickListener(this);
    this.mCodeEntry = ((EditText)localView.findViewById(2131231209));
    this.mBalanceView = ((TextView)localView.findViewById(2131231208));
    this.mUserMessageView = ((TextView)localView.findViewById(2131231205));
    this.mContentView = localView.findViewById(2131230775);
    this.mLoadingIndicator = localView.findViewById(2131230808);
    this.mFootersContainer = ((ViewGroup)localView.findViewById(2131231160));
    this.mProgressBar = localView.findViewById(2131230930);
    String str1 = null;
    String str2 = null;
    if (paramBundle != null)
    {
      this.mAddressChallenge = ((ChallengeProtos.AddressChallenge)ParcelableProto.getProtoFromBundle(paramBundle, "address_challenge"));
      str1 = paramBundle.getString("balance");
      str2 = paramBundle.getString("user_message");
      if (paramBundle.containsKey("footers_html"))
        setFooters(paramBundle.getStringArrayList("footers_html"));
    }
    setBalance(str1);
    setUserMessage(str2);
    if (this.mSwitchToProgress)
      switchToProgress();
    if (this.mSwitchToLoading)
      switchToLoading();
    if (this.mSwitchToRedeemForm)
      switchToRedeemForm();
    if (this.mSwitchToContinueForm)
      switchToContinueForm();
    return localView;
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    onPositiveClick(paramInt, paramBundle);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if ((paramInt == 100) && (paramBundle.getBoolean("finish")))
      notifyListenerCancel();
  }

  public void onResume()
  {
    super.onResume();
    if (this.mShowErrorMessage != null)
      showError(this.mShowErrorMessage, this.mShowErrorFinish);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mAddressChallenge != null)
      paramBundle.putParcelable("address_challenge", ParcelableProto.forProto(this.mAddressChallenge));
    if (this.mFootersHtml != null)
      paramBundle.putStringArrayList("footers_html", new ArrayList(this.mFootersHtml));
    paramBundle.putString("balance", this.mBalance);
    paramBundle.putString("user_message", this.mUserMessage);
  }

  public void setBalance(String paramString)
  {
    this.mBalance = paramString;
    if (!TextUtils.isEmpty(paramString))
      this.mBalanceView.setText(paramString);
    while (true)
    {
      return;
      this.mBalanceView.setText(2131165311);
    }
  }

  public void setChallenge(ChallengeProtos.AddressChallenge paramAddressChallenge)
  {
    this.mAddressChallenge = paramAddressChallenge;
  }

  public void setFooters(List<String> paramList)
  {
    if (this.mContentView != null)
    {
      this.mFootersContainer.removeAllViews();
      this.mFootersHtml = paramList;
      LayoutInflater localLayoutInflater = LayoutInflater.from(this.mContentView.getContext());
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        TextView localTextView = (TextView)localLayoutInflater.inflate(2130968821, this.mFootersContainer, false);
        localTextView.setText(Html.fromHtml(str));
        localTextView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mFootersContainer.addView(localTextView);
      }
    }
  }

  public void setListener(Listener paramListener)
  {
    this.mListener = paramListener;
  }

  public void setUserMessage(String paramString)
  {
    this.mUserMessage = paramString;
    if (!TextUtils.isEmpty(paramString))
    {
      this.mUserMessageView.setText(this.mUserMessage);
      this.mUserMessageView.setVisibility(0);
    }
    while (true)
    {
      return;
      this.mUserMessageView.setVisibility(8);
    }
  }

  public void showChallenge(ChallengeProtos.AddressChallenge paramAddressChallenge, Bundle paramBundle)
  {
    setChallenge(paramAddressChallenge);
    internalShowChallenge(paramBundle);
  }

  public void showError(String paramString, boolean paramBoolean)
  {
    if (!isResumed())
    {
      this.mShowErrorMessage = paramString;
      this.mShowErrorFinish = paramBoolean;
    }
    while (true)
    {
      return;
      this.mShowErrorMessage = null;
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(paramString, 2131165599, -1);
      Bundle localBundle = new Bundle(1);
      localBundle.putBoolean("finish", paramBoolean);
      localSimpleAlertDialog.setCallback(this, 100, localBundle);
      localSimpleAlertDialog.show(getFragmentManager(), "error_dialog");
    }
  }

  public void switchToContinueForm()
  {
    if (this.mContentView == null)
      this.mSwitchToContinueForm = true;
    while (true)
    {
      return;
      this.mSwitchToContinueForm = false;
      turnOffProgress();
      this.mContentView.setVisibility(0);
      this.mLoadingIndicator.setVisibility(8);
      this.mRedeemButton.setVisibility(8);
      this.mCodeEntry.setVisibility(8);
      this.mContinueButton.setVisibility(0);
    }
  }

  public void switchToLoading()
  {
    if (this.mContentView == null)
      this.mSwitchToLoading = true;
    while (true)
    {
      return;
      this.mSwitchToLoading = false;
      turnOffProgress();
      this.mContentView.setVisibility(4);
      this.mLoadingIndicator.setVisibility(0);
    }
  }

  public void switchToProgress()
  {
    if (this.mContentView == null)
      this.mSwitchToProgress = true;
    while (true)
    {
      return;
      this.mSwitchToProgress = false;
      this.mProgressBar.setVisibility(0);
      this.mCodeEntry.setEnabled(false);
      this.mCodeEntry.setVisibility(0);
      this.mRedeemButton.setEnabled(false);
    }
  }

  public void switchToRedeemForm()
  {
    if (this.mContentView == null)
      this.mSwitchToRedeemForm = true;
    while (true)
    {
      return;
      this.mSwitchToRedeemForm = false;
      turnOffProgress();
      this.mContentView.setVisibility(0);
      this.mLoadingIndicator.setVisibility(8);
      this.mRedeemButton.setVisibility(0);
      this.mCodeEntry.setVisibility(0);
      this.mContinueButton.setVisibility(8);
    }
  }

  public static abstract interface Listener
  {
    public abstract void onCancel();

    public abstract void onCountrySwitch(String paramString, Bundle paramBundle);

    public abstract void onRedeem(String paramString, BillingAddress.Address paramAddress, ArrayList<String> paramArrayList);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.giftcard.RedeemGiftCardFragment
 * JD-Core Version:    0.6.2
 */
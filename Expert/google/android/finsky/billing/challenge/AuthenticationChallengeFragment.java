package com.google.android.finsky.billing.challenge;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AuthenticationChallenge;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.ParcelableProto;
import java.io.IOException;

public class AuthenticationChallengeFragment extends Fragment
  implements View.OnClickListener
{
  private String mAccountName;
  private Analytics mAnalytics;
  private Request<?> mAuthRequest = null;
  private int mAuthenticationType;
  private ChallengeProtos.AuthenticationChallenge mChallenge;
  private FinskyEventLog mEventLog;
  private EditText mInput;
  private Listener mListener;
  private int mRetryCount;

  private void failure()
  {
    this.mInput.setEnabled(true);
    this.mInput.setError(getResources().getString(2131165308));
    this.mRetryCount = (1 + this.mRetryCount);
    this.mAnalytics.logPageView(null, null, "authChallenge.failure?type=" + this.mAuthenticationType + "&retries=" + this.mRetryCount);
    FinskyEventLog localFinskyEventLog = this.mEventLog;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = "type";
    arrayOfObject[1] = Integer.valueOf(this.mAuthenticationType);
    arrayOfObject[2] = "retries";
    arrayOfObject[3] = Integer.valueOf(this.mRetryCount);
    localFinskyEventLog.logTag("authChallenge.failure", arrayOfObject);
  }

  public static AuthenticationChallengeFragment newInstance(String paramString, ChallengeProtos.AuthenticationChallenge paramAuthenticationChallenge)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("authAccount", paramString);
    localBundle.putParcelable("authentication_challenge", ParcelableProto.forProto(paramAuthenticationChallenge));
    AuthenticationChallengeFragment localAuthenticationChallengeFragment = new AuthenticationChallengeFragment();
    localAuthenticationChallengeFragment.setArguments(localBundle);
    return localAuthenticationChallengeFragment;
  }

  private void success()
  {
    this.mInput.setEnabled(true);
    this.mInput.setError(null);
    if (this.mListener != null)
      this.mListener.onSuccess(this.mAuthenticationType, this.mRetryCount);
    FinskyPreferences.lastGaiaAuthTimestamp.get(this.mAccountName).put(Long.valueOf(System.currentTimeMillis()));
    this.mAnalytics.logPageView(null, null, "authChallenge.success?type=" + this.mAuthenticationType + "&retries=" + this.mRetryCount);
    FinskyEventLog localFinskyEventLog = this.mEventLog;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = "type";
    arrayOfObject[1] = Integer.valueOf(this.mAuthenticationType);
    arrayOfObject[2] = "retries";
    arrayOfObject[3] = Integer.valueOf(this.mRetryCount);
    localFinskyEventLog.logTag("authChallenge.success", arrayOfObject);
  }

  private void verifyGaia(String paramString)
  {
    this.mAuthRequest = new ClientLoginApi(FinskyApp.get().getRequestQueue()).validateUser(this.mAccountName, paramString, new ClientLoginApi.ClientLoginListener()
    {
      public void onAuthFailure(String paramAnonymousString, ClientLoginApi.ErrorType paramAnonymousErrorType)
      {
        if ((paramAnonymousErrorType != ClientLoginApi.ErrorType.CAPTCHA) && (paramAnonymousErrorType != ClientLoginApi.ErrorType.TWO_FACTOR))
          AuthenticationChallengeFragment.this.failure();
        while (true)
        {
          return;
          Account localAccount = AccountHandler.findAccount(AuthenticationChallengeFragment.this.mAccountName, FinskyApp.get());
          AccountManager localAccountManager = AccountManager.get(FinskyApp.get());
          Bundle localBundle = new Bundle();
          localBundle.putString("password", paramAnonymousString);
          localAccountManager.confirmCredentials(localAccount, localBundle, AuthenticationChallengeFragment.this.getActivity(), new AccountManagerCallback()
          {
            public void run(AccountManagerFuture<Bundle> paramAnonymous2AccountManagerFuture)
            {
              try
              {
                Bundle localBundle = (Bundle)paramAnonymous2AccountManagerFuture.getResult();
                if (localBundle.getBoolean("booleanResult"))
                {
                  AuthenticationChallengeFragment.this.success();
                }
                else if (localBundle.containsKey("intent"))
                {
                  Intent localIntent = (Intent)localBundle.getParcelable("intent");
                  AuthenticationChallengeFragment.this.startActivityForResult(localIntent, 100);
                }
              }
              catch (OperationCanceledException localOperationCanceledException)
              {
                FinskyLog.w("OperationCanceledException: %s", new Object[] { localOperationCanceledException });
                AuthenticationChallengeFragment.this.failure();
                return;
                AuthenticationChallengeFragment.this.failure();
              }
              catch (IOException localIOException)
              {
                FinskyLog.w("IOException: %s", new Object[] { localIOException });
                AuthenticationChallengeFragment.this.failure();
              }
              catch (AuthenticatorException localAuthenticatorException)
              {
                FinskyLog.w("AuthenticatorException: %s", new Object[] { localAuthenticatorException });
                AuthenticationChallengeFragment.this.failure();
              }
            }
          }
          , null);
        }
      }

      public void onAuthSuccess()
      {
        AuthenticationChallengeFragment.this.success();
      }
    });
  }

  private void verifyInput()
  {
    String str = this.mInput.getText().toString();
    this.mInput.setEnabled(false);
    verifyGaia(str);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 100)
      if (paramInt2 == -1)
        success();
    while (true)
    {
      return;
      failure();
      continue;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131230769:
    case 2131230770:
    }
    while (true)
    {
      return;
      verifyInput();
      continue;
      if (this.mListener != null)
        this.mListener.onCancel();
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mChallenge = ((ChallengeProtos.AuthenticationChallenge)ParcelableProto.getProtoFromBundle(getArguments(), "authentication_challenge"));
    this.mAccountName = getArguments().getString("authAccount");
    this.mAnalytics = FinskyApp.get().getAnalytics();
    this.mEventLog = FinskyApp.get().getEventLogger();
    if (paramBundle != null)
      this.mRetryCount = paramBundle.getInt("retry_count");
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968598, null);
    this.mAuthenticationType = this.mChallenge.getAuthenticationType();
    this.mAnalytics.logPageView(null, null, "authChallenge.trigger?type=" + this.mAuthenticationType);
    FinskyEventLog localFinskyEventLog = this.mEventLog;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "type";
    arrayOfObject[1] = Integer.valueOf(this.mAuthenticationType);
    localFinskyEventLog.logTag("authChallenge.trigger", arrayOfObject);
    TextView localTextView1 = (TextView)localView.findViewById(2131230786);
    localTextView1.setVisibility(8);
    String str = this.mChallenge.getGaiaDescriptionTextHtml();
    if (str != null)
    {
      localTextView1.setVisibility(0);
      localTextView1.setText(Html.fromHtml(str));
      localTextView1.setMovementMethod(LinkMovementMethod.getInstance());
    }
    this.mInput = ((EditText)localView.findViewById(2131230789));
    this.mInput.setVisibility(0);
    TextView localTextView2 = (TextView)localView.findViewById(2131230790);
    if (this.mChallenge.hasGaiaFooterTextHtml())
    {
      localTextView2.setText(Html.fromHtml(this.mChallenge.getGaiaFooterTextHtml()));
      localTextView2.setMovementMethod(LinkMovementMethod.getInstance());
      localTextView2.setVisibility(0);
    }
    while (true)
    {
      Button localButton1 = (Button)localView.findViewById(2131230769);
      localButton1.setText(2131165599);
      localButton1.setOnClickListener(this);
      Button localButton2 = (Button)localView.findViewById(2131230770);
      localButton2.setText(2131165273);
      localButton2.setOnClickListener(this);
      return localView;
      localTextView2.setVisibility(8);
    }
  }

  public void onDestroyView()
  {
    if (this.mAuthRequest != null)
      this.mAuthRequest.cancel();
    super.onDestroyView();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("retry_count", this.mRetryCount);
  }

  public void setListener(Listener paramListener)
  {
    this.mListener = paramListener;
  }

  public static abstract interface Listener
  {
    public abstract void onCancel();

    public abstract void onSuccess(int paramInt1, int paramInt2);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.challenge.AuthenticationChallengeFragment
 * JD-Core Version:    0.6.2
 */
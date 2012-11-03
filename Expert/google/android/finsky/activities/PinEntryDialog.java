package com.google.android.finsky.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;

public class PinEntryDialog extends FragmentActivity
  implements View.OnClickListener, ButtonBar.ClickListener
{
  private Analytics mAnalytics;
  private ButtonBar mButtonBar;
  private FinskyEventLog mEventLog;
  private String mMatchPin;
  private EditText mPinEntryView;
  private TextWatcher mPinWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
    }

    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      PinEntryDialog.this.syncOkButtonState();
    }
  };

  public static Intent getIntent(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, PinEntryDialog.class);
    localIntent.putExtra("prompt-string-id", paramInt);
    localIntent.putExtra("pin-to-match", paramString1);
    localIntent.putExtra("result-key", paramString2);
    localIntent.putExtra("allow-remove-pin", paramBoolean);
    return localIntent;
  }

  private String getUserPin()
  {
    return this.mPinEntryView.getText().toString().trim();
  }

  private void setPinResult(String paramString)
  {
    String str = getIntent().getStringExtra("result-key");
    if (str != null)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra(str, paramString);
      setResult(-1, localIntent);
    }
    while (true)
    {
      return;
      setResult(-1);
    }
  }

  private void syncOkButtonState()
  {
    if (getUserPin().length() >= 4);
    for (boolean bool = true; ; bool = false)
    {
      this.mButtonBar.setPositiveButtonEnabled(bool);
      return;
    }
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131231143)
    {
      setPinResult(null);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968781);
    Intent localIntent = getIntent();
    int i = localIntent.getIntExtra("prompt-string-id", -1);
    this.mMatchPin = localIntent.getStringExtra("pin-to-match");
    boolean bool = localIntent.getBooleanExtra("allow-remove-pin", false);
    this.mAnalytics = FinskyApp.get().getAnalytics();
    this.mEventLog = FinskyApp.get().getEventLogger();
    TextView localTextView = (TextView)findViewById(2131231141);
    this.mPinEntryView = ((EditText)findViewById(2131231142));
    this.mButtonBar = ((ButtonBar)findViewById(2131230825));
    localTextView.setText(i);
    this.mButtonBar.setPositiveButtonTitle(2131165634);
    this.mButtonBar.setNegativeButtonTitle(2131165635);
    this.mButtonBar.setClickListener(this);
    this.mPinEntryView.addTextChangedListener(this.mPinWatcher);
    if (bool)
    {
      Button localButton = (Button)findViewById(2131231143);
      localButton.setVisibility(0);
      localButton.setOnClickListener(this);
    }
    if (paramBundle == null)
    {
      this.mAnalytics.logPageView(null, null, "pinLock.shown");
      this.mEventLog.logTag("pinLock.shown", new Object[0]);
    }
  }

  public void onNegativeButtonClick()
  {
    this.mAnalytics.logPageView(null, null, "pinLock.canceled");
    this.mEventLog.logTag("pinLock.canceled", new Object[0]);
    setResult(0);
    finish();
  }

  public void onPositiveButtonClick()
  {
    String str = getUserPin();
    if ((this.mMatchPin != null) && (!this.mMatchPin.equals(str)))
    {
      this.mAnalytics.logPageView(null, null, "pinLock.failure");
      this.mEventLog.logTag("pinLock.failure", new Object[0]);
      this.mPinEntryView.setText("");
      this.mPinEntryView.setError(getString(2131165651));
    }
    while (true)
    {
      return;
      this.mAnalytics.logPageView(null, null, "pinLock.success");
      this.mEventLog.logTag("pinLock.success", new Object[0]);
      setPinResult(str);
      finish();
    }
  }

  public void onResume()
  {
    super.onResume();
    syncOkButtonState();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.PinEntryDialog
 * JD-Core Version:    0.6.2
 */
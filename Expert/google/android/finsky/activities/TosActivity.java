package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.remoting.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Maps;
import java.util.Map;

public class TosActivity extends Activity
  implements ButtonBar.ClickListener
{
  private static Map<String, String> sLastTosAcceptedInProcessLife = Maps.newHashMap();
  private String mAccount = null;
  private ButtonBar mButtonBar;
  private CheckBox mEmailOptIn;
  private DfeToc mToc = null;

  public static Intent getIntent(Context paramContext, String paramString, DfeToc paramDfeToc)
  {
    Intent localIntent = new Intent(paramContext, TosActivity.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("finsky.TosActivity.account", paramString);
    localBundle.putParcelable("finsky.TosActivity.toc", paramDfeToc);
    localIntent.putExtras(localBundle);
    return localIntent;
  }

  public static boolean requiresAcceptance(String paramString, DfeToc paramDfeToc)
  {
    boolean bool1 = false;
    if ((sLastTosAcceptedInProcessLife.containsKey(paramString)) && (((String)sLastTosAcceptedInProcessLife.get(paramString)).equals(paramDfeToc.getTosToken())))
      return bool1;
    String str = (String)FinskyPreferences.acceptedTosToken.get(paramString).get();
    if ((TextUtils.isEmpty(str)) || (str.equals(paramDfeToc)));
    for (boolean bool2 = true; ; bool2 = false)
    {
      bool1 = bool2;
      break;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Bundle localBundle;
    if (paramBundle != null)
    {
      localBundle = paramBundle;
      if (localBundle != null)
      {
        this.mAccount = localBundle.getString("finsky.TosActivity.account");
        this.mToc = ((DfeToc)localBundle.getParcelable("finsky.TosActivity.toc"));
      }
      if ((this.mAccount != null) && (this.mToc != null))
        break label77;
      FinskyLog.w("Bad request to Terms of Service activity.", new Object[0]);
      finish();
    }
    while (true)
    {
      return;
      localBundle = getIntent().getExtras();
      break;
      label77: setContentView(2130968843);
      this.mButtonBar = ((ButtonBar)findViewById(2131230825));
      this.mButtonBar.setPositiveButtonTitle(2131165399);
      this.mButtonBar.setNegativeButtonTitle(2131165401);
      this.mButtonBar.setClickListener(this);
      ((TextView)findViewById(2131230995)).setText(this.mAccount);
      TextView localTextView = (TextView)findViewById(2131230739);
      localTextView.setMovementMethod(LinkMovementMethod.getInstance());
      localTextView.setText(Html.fromHtml(this.mToc.getTosContent()));
      this.mEmailOptIn = ((CheckBox)findViewById(2131231265));
      String str = this.mToc.getTosCheckboxTextMarketingEmails();
      if (!TextUtils.isEmpty(str))
      {
        this.mEmailOptIn.setText(str);
        this.mEmailOptIn.setChecked(this.mToc.hasCurrentUserPreviouslyOptedIn());
        this.mEmailOptIn.setVisibility(0);
      }
      else
      {
        this.mEmailOptIn.setVisibility(8);
      }
    }
  }

  public void onNegativeButtonClick()
  {
    setResult(0);
    finish();
  }

  public void onPositiveButtonClick()
  {
    Boolean localBoolean = null;
    if (!TextUtils.isEmpty(this.mToc.getTosCheckboxTextMarketingEmails()))
      localBoolean = Boolean.valueOf(this.mEmailOptIn.isChecked());
    FinskyApp.get().getDfeApi().acceptTos(this.mToc.getTosToken(), localBoolean, new Response.Listener()
    {
      public void onResponse(Tos.AcceptTosResponse paramAnonymousAcceptTosResponse)
      {
        FinskyPreferences.acceptedTosToken.get(TosActivity.this.mAccount).put(TosActivity.this.mToc.getTosToken());
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        FinskyLog.e("Error sending TOS acceptance: %s", new Object[] { paramAnonymousVolleyError });
      }
    });
    sLastTosAcceptedInProcessLife.put(this.mAccount, this.mToc.getTosToken());
    setResult(-1);
    finish();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("finsky.TosActivity.account", this.mAccount);
    paramBundle.putParcelable("finsky.TosActivity.toc", this.mToc);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.TosActivity
 * JD-Core Version:    0.6.2
 */
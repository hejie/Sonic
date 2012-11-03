package com.google.android.finsky.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.creditcard.SetupWizardAddCreditCardActivity;
import com.google.android.finsky.layout.FifeImageView;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.AddCreditCardPromoOffer;
import com.google.android.finsky.remoting.protos.CheckPromoOffer.AvailablePromoOffer;
import com.google.android.finsky.remoting.protos.Doc.Image;
import com.google.android.finsky.utils.Compat;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.ParcelableProto;

public class AvailablePromoOfferActivity extends FragmentActivity
  implements Html.ImageGetter, SimpleAlertDialog.Listener
{
  private String mAccountName;
  private Analytics mAnalytics;
  private CheckPromoOffer.AvailablePromoOffer mAvailablePromoOffer;
  private FifeImageView mPromoImageView;

  public static Intent getIntent(String paramString, CheckPromoOffer.AvailablePromoOffer paramAvailablePromoOffer)
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("available_offer", ParcelableProto.forProto(paramAvailablePromoOffer));
    Intent localIntent = new Intent(FinskyApp.get(), AvailablePromoOfferActivity.class);
    localIntent.putExtra("authAccount", paramString);
    localIntent.putExtra("internal_parameters", localBundle);
    return localIntent;
  }

  private void logAnalytics(String paramString)
  {
    this.mAnalytics.logPageView("externalPackage", null, paramString);
  }

  private void replaceUrlsWithHandlers(CharSequence paramCharSequence)
  {
    if (!(paramCharSequence instanceof Spannable));
    while (true)
    {
      return;
      Spannable localSpannable = (Spannable)paramCharSequence;
      for (URLSpan localURLSpan : (URLSpan[])localSpannable.getSpans(0, localSpannable.length(), URLSpan.class))
      {
        int k = localSpannable.getSpanStart(localURLSpan);
        int m = localSpannable.getSpanEnd(localURLSpan);
        localSpannable.removeSpan(localURLSpan);
        localSpannable.setSpan(new UrlSpanHandler(localURLSpan.getURL())
        {
          public void onUrlClick(View paramAnonymousView, String paramAnonymousString)
          {
            AvailablePromoOfferActivity.this.showUrlWebView(paramAnonymousString);
          }
        }
        , k, m, 0);
      }
    }
  }

  private void setResultAndFinish()
  {
    setResult(-1);
    finish();
  }

  public Drawable getDrawable(String paramString)
  {
    Drawable localDrawable = getResources().getDrawable(2130837597);
    localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
    return localDrawable;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 100)
    {
      if (!paramIntent.hasExtra("redeemed_offer_message_html"))
        break label32;
      logAnalytics("promoOfferRedeemed");
    }
    while (true)
    {
      setResult(-1, paramIntent);
      finish();
      return;
      label32: logAnalytics("promoOfferNotRedeemed");
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    if (!localIntent.hasExtra("internal_parameters"))
    {
      FinskyLog.e("No internal parameters passed.", new Object[0]);
      setResult(-1);
      finish();
    }
    Bundle localBundle = localIntent.getBundleExtra("internal_parameters");
    if (!localBundle.containsKey("available_offer"))
    {
      FinskyLog.e("No available_offer passed.", new Object[0]);
      setResult(-1);
      finish();
    }
    this.mAvailablePromoOffer = ((CheckPromoOffer.AvailablePromoOffer)ParcelableProto.getProtoFromBundle(localBundle, "available_offer"));
    this.mAccountName = localIntent.getStringExtra("authAccount");
    this.mAnalytics = FinskyApp.get().getAnalytics(this.mAccountName);
    if (paramBundle == null)
      logAnalytics("promoOfferShow");
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 101)
      setResultAndFinish();
  }

  protected void onResume()
  {
    super.onResume();
    String str1 = null;
    Spanned localSpanned1 = null;
    Spanned localSpanned2 = null;
    Spanned localSpanned3 = null;
    Doc.Image localImage = null;
    final String str2;
    ViewGroup localViewGroup;
    if (this.mAvailablePromoOffer.hasAddCreditCardOffer())
    {
      CheckPromoOffer.AddCreditCardPromoOffer localAddCreditCardPromoOffer = this.mAvailablePromoOffer.getAddCreditCardOffer();
      str1 = localAddCreditCardPromoOffer.getHeaderText();
      localSpanned1 = Html.fromHtml(localAddCreditCardPromoOffer.getIntroductoryTextHtml());
      localSpanned2 = Html.fromHtml(localAddCreditCardPromoOffer.getDescriptionHtml());
      if (localAddCreditCardPromoOffer.hasImage())
        localImage = localAddCreditCardPromoOffer.getImage();
      localSpanned3 = Html.fromHtml(localAddCreditCardPromoOffer.getTermsAndConditionsHtml());
      replaceUrlsWithHandlers(localSpanned3);
      str2 = localAddCreditCardPromoOffer.getNoActionDescription();
      setContentView(2130968592);
      localViewGroup = (ViewGroup)findViewById(2131230775);
      localViewGroup.removeAllViews();
      getLayoutInflater().inflate(2130968599, localViewGroup, true);
      ((TextView)findViewById(2131230771)).setText(str1);
      ((TextView)findViewById(2131230791)).setText(localSpanned1);
      if ((!TextUtils.isEmpty(localSpanned2)) || (!TextUtils.isEmpty(localSpanned3)) || (localImage != null))
        break label321;
      findViewById(2131230792).setVisibility(8);
      findViewById(2131230795).setVisibility(8);
    }
    while (true)
    {
      ((TextView)findViewById(2131230797)).setText(Html.fromHtml(getResources().getString(2131165229), this, null));
      findViewById(2131230798).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AvailablePromoOfferActivity.this.logAnalytics("promoOfferStartRedemption");
          if (AvailablePromoOfferActivity.this.mAvailablePromoOffer.hasAddCreditCardOffer())
          {
            Intent localIntent = new Intent(FinskyApp.get(), SetupWizardAddCreditCardActivity.class);
            if (AvailablePromoOfferActivity.this.getIntent().hasExtra("cardholder_name"))
              localIntent.putExtra("cardholder_name", AvailablePromoOfferActivity.this.getIntent().getStringExtra("cardholder_name"));
            if (AvailablePromoOfferActivity.this.getIntent().hasExtra("on_initial_setup"))
              localIntent.putExtra("on_initial_setup", AvailablePromoOfferActivity.this.getIntent().getBooleanExtra("on_initial_setup", true));
            localIntent.setPackage(FinskyApp.get().getPackageName());
            localIntent.putExtra("authAccount", AvailablePromoOfferActivity.this.mAccountName);
            localIntent.putExtra("ui_mode", BillingUtils.CreateInstrumentUiMode.PROMO_OFFER.name());
            AvailablePromoOfferActivity.this.startActivityForResult(localIntent, 100);
          }
        }
      });
      findViewById(2131230799).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AvailablePromoOfferActivity.this.logAnalytics("promoOfferSkip");
          if (!TextUtils.isEmpty(str2))
          {
            SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(str2, 2131165599, -1);
            localSimpleAlertDialog.setCallback(null, 101, null);
            localSimpleAlertDialog.show(AvailablePromoOfferActivity.this.getSupportFragmentManager(), "no_action_message");
          }
          while (true)
          {
            return;
            AvailablePromoOfferActivity.this.setResultAndFinish();
          }
        }
      });
      if (getIntent().getBooleanExtra("on_initial_setup", true))
        Compat.viewSetSystemUiVisibility(localViewGroup, 7798784);
      return;
      str2 = null;
      FinskyLog.w("Unsupported offer.", new Object[0]);
      setResult(-1);
      finish();
      break;
      label321: TextView localTextView = (TextView)findViewById(2131230796);
      localTextView.setText(localSpanned3);
      localTextView.setMovementMethod(LinkMovementMethod.getInstance());
      ((TextView)findViewById(2131230794)).setText(localSpanned2);
      this.mPromoImageView = ((FifeImageView)findViewById(2131230793));
      if (localImage != null)
      {
        this.mPromoImageView.setImage(localImage, FinskyApp.get().getBitmapLoader());
        this.mPromoImageView.setVisibility(0);
      }
      else
      {
        this.mPromoImageView.setVisibility(8);
      }
    }
  }

  public void showUrlWebView(String paramString)
  {
    SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstanceWithLayout(2130968596, 2131165599, -1);
    Bundle localBundle = new Bundle();
    localBundle.putString("url_key", paramString);
    localSimpleAlertDialog.setViewConfiguration(localBundle);
    localSimpleAlertDialog.show(getSupportFragmentManager(), "policy_terms");
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.AvailablePromoOfferActivity
 * JD-Core Version:    0.6.2
 */
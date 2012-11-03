package com.google.android.finsky.billing.carrierbilling.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressData.Builder;
import com.android.i18n.addressinput.AddressWidget;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingProvisioning;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.utils.FinskyLog;

public class AddCarrierBillingFragment extends Fragment
  implements View.OnClickListener
{
  private Button mAcceptButton;
  private Button mDeclineButton;
  private ImageButton mEditAddressButton;
  AddCarrierBillingResultListener mListener;
  private String mTosUrl;
  private BillingUtils.CreateInstrumentUiMode mUiMode;

  private void forceFinishLoadingTos(View paramView)
  {
    paramView.findViewById(2131230832).setVisibility(0);
    this.mAcceptButton.setEnabled(true);
    paramView.findViewById(2131230841).setVisibility(8);
  }

  public static AddCarrierBillingFragment newInstance(Type paramType, SubscriberInfo paramSubscriberInfo, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString1, String paramString2)
  {
    AddCarrierBillingFragment localAddCarrierBillingFragment = new AddCarrierBillingFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("type", paramType.name());
    localBundle.putParcelable("prefill_address", paramSubscriberInfo);
    localBundle.putString("prefill_snippet", paramString2);
    localBundle.putString("ui_mode", paramCreateInstrumentUiMode.toString());
    localBundle.putString("tos_url", paramString1);
    localAddCarrierBillingFragment.setArguments(localBundle);
    return localAddCarrierBillingFragment;
  }

  private void setAddressToFull(View paramView, SubscriberInfo paramSubscriberInfo)
  {
    CarrierBillingStorage localCarrierBillingStorage = BillingLocator.getCarrierBillingStorage();
    String str1 = getString(2131165265);
    ((TextView)paramView.findViewById(2131230837)).setText(str1);
    SubscriberInfo localSubscriberInfo;
    TextView localTextView;
    if (paramSubscriberInfo != null)
    {
      localSubscriberInfo = paramSubscriberInfo;
      AddressData localAddressData = new AddressData.Builder().setRecipient(localSubscriberInfo.getName()).setAddressLine1(localSubscriberInfo.getAddress1()).setAddressLine2(localSubscriberInfo.getAddress2()).setLocality(localSubscriberInfo.getCity()).setAdminArea(localSubscriberInfo.getState()).setPostalCode(localSubscriberInfo.getPostalCode()).setCountry(localSubscriberInfo.getCountry()).build();
      localTextView = (TextView)paramView.findViewById(2131230838);
      if ((TextUtils.isEmpty(localAddressData.getRecipient())) && (TextUtils.isEmpty(localAddressData.getAddressLine1())) && (TextUtils.isEmpty(localAddressData.getAddressLine2())) && (TextUtils.isEmpty(localAddressData.getLocality())) && (TextUtils.isEmpty(localAddressData.getAdministrativeArea())) && (TextUtils.isEmpty(localAddressData.getPostalCode())) && (TextUtils.isEmpty(localAddressData.getPostalCountry())))
        break label268;
      localTextView.setVisibility(0);
      localTextView.setText(TextUtils.join("\n", AddressWidget.getFullEnvelopeAddress(localAddressData, getActivity().getBaseContext())));
    }
    while (true)
    {
      String str2 = localSubscriberInfo.getIdentifier();
      if (BillingUtils.isEmptyOrSpaces(str2))
        str2 = PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony());
      showPhoneNumber((TextView)paramView.findViewById(2131230840), str2);
      return;
      localSubscriberInfo = localCarrierBillingStorage.getProvisioning().getSubscriberInfo();
      break;
      label268: localTextView.setVisibility(8);
    }
  }

  private void setAddressToMinimalAddress(View paramView, SubscriberInfo paramSubscriberInfo)
  {
    String str1 = getString(2131165265);
    ((TextView)paramView.findViewById(2131230837)).setText(str1);
    String str2 = paramSubscriberInfo.getIdentifier();
    showPhoneNumber((TextView)paramView.findViewById(2131230840), str2);
  }

  private void setAddressToSnippet(View paramView, String paramString)
  {
    String str1 = getString(2131165264, new Object[] { BillingLocator.getCarrierBillingStorage().getParams().getName() });
    ((TextView)paramView.findViewById(2131230837)).setText(str1);
    ((TextView)paramView.findViewById(2131230838)).setText(paramString);
    if (CarrierBillingUtils.isDcb30())
    {
      String str2 = BillingLocator.getLine1NumberFromTelephony();
      showPhoneNumber((TextView)paramView.findViewById(2131230840), str2);
    }
    while (true)
    {
      return;
      ((TextView)paramView.findViewById(2131230840)).setVisibility(8);
    }
  }

  private void setUpTos(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (true)
    {
      return;
      String str = getString(2131165263);
      if (!TextUtils.isEmpty(str))
        paramString = paramString.replace("%locale%", str);
      this.mTosUrl = BillingUtils.replaceLocale(paramString);
    }
  }

  private void setUpViewForType(View paramView, Type paramType, SubscriberInfo paramSubscriberInfo, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString)
  {
    switch (1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$fragment$AddCarrierBillingFragment$Type[paramType.ordinal()])
    {
    default:
      FinskyLog.d("Unexpected type " + paramType, new Object[0]);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      String str = BillingLocator.getCarrierBillingStorage().getParams().getName();
      ((TextView)paramView.findViewById(2131230836)).setText(getString(2131165227, new Object[] { str }));
      return;
      showTosSection(paramView, false);
      forceFinishLoadingTos(paramView);
      showAddressSection(paramView, true);
      setAddressToSnippet(paramView, paramString);
      continue;
      showTosSection(paramView, true);
      showAddressSection(paramView, true);
      setAddressToSnippet(paramView, paramString);
      continue;
      showTosSection(paramView, false);
      forceFinishLoadingTos(paramView);
      showAddressSection(paramView, true);
      setAddressToFull(paramView, paramSubscriberInfo);
      continue;
      showTosSection(paramView, true);
      showAddressSection(paramView, true);
      setAddressToFull(paramView, paramSubscriberInfo);
      continue;
      showTosSection(paramView, false);
      forceFinishLoadingTos(paramView);
      showAddressSection(paramView, true);
      setAddressToMinimalAddress(paramView, paramSubscriberInfo);
      continue;
      showTosSection(paramView, true);
      showAddressSection(paramView, true);
      setAddressToMinimalAddress(paramView, paramSubscriberInfo);
      continue;
      showTosSection(paramView, true);
      showAddressSection(paramView, false);
    }
  }

  private void showAddressSection(View paramView, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      View localView = paramView.findViewById(2131230835);
      localView.findViewById(2131230837).setVisibility(i);
      localView.findViewById(2131230839).setVisibility(i);
      localView.findViewById(2131230838).setVisibility(i);
      localView.findViewById(2131230840).setVisibility(i);
      return;
    }
  }

  private void showPhoneNumber(TextView paramTextView, String paramString)
  {
    if (!BillingUtils.isEmptyOrSpaces(paramString))
    {
      paramTextView.setVisibility(0);
      paramTextView.setText(paramString);
    }
    while (true)
    {
      return;
      paramTextView.setVisibility(8);
    }
  }

  private void showTosSection(View paramView, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      if (paramBoolean)
      {
        WebView localWebView = (WebView)paramView.findViewById(2131230833);
        localWebView.setWebViewClient(getCarrierTosWebViewClient(paramView.findViewById(2131230841), paramView.findViewById(2131230833)));
        localWebView.loadUrl(this.mTosUrl);
        localWebView.getSettings().setSupportZoom(false);
        this.mAcceptButton.setEnabled(false);
      }
      paramView.findViewById(2131230833).setVisibility(i);
      return;
    }
  }

  public void enableUi(boolean paramBoolean)
  {
    if (this.mAcceptButton == null);
    while (true)
    {
      return;
      this.mAcceptButton.setEnabled(paramBoolean);
      this.mDeclineButton.setEnabled(paramBoolean);
      this.mEditAddressButton.setEnabled(paramBoolean);
    }
  }

  CarrierTosWebViewClient getCarrierTosWebViewClient(View paramView1, View paramView2)
  {
    return new CarrierTosWebViewClient(paramView1, paramView2);
  }

  void onClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      FinskyLog.d("Unexpected button press. do nothing.", new Object[0]);
    case 2131230769:
    case 2131230770:
    case 2131230839:
    }
    while (true)
    {
      return;
      this.mListener.onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.SUCCESS);
      continue;
      this.mListener.onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.CANCELED);
      continue;
      this.mListener.onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.EDIT_ADDRESS);
    }
  }

  public void onClick(View paramView)
  {
    onClick(paramView.getId());
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Bundle localBundle = getArguments();
    this.mUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(localBundle.getString("ui_mode"));
    setUpTos(localBundle.getString("tos_url"));
    View localView = paramLayoutInflater.inflate(2130968614, paramViewGroup, false);
    this.mAcceptButton = ((Button)localView.findViewById(2131230769));
    this.mAcceptButton.setOnClickListener(this);
    this.mAcceptButton.setText(2131165399);
    this.mDeclineButton = ((Button)localView.findViewById(2131230770));
    this.mDeclineButton.setText(2131165401);
    this.mDeclineButton.setOnClickListener(this);
    this.mEditAddressButton = ((ImageButton)localView.findViewById(2131230839));
    this.mEditAddressButton.setOnClickListener(this);
    Type localType = Type.valueOf(localBundle.getString("type"));
    SubscriberInfo localSubscriberInfo = (SubscriberInfo)localBundle.getParcelable("prefill_address");
    String str = localBundle.getString("prefill_snippet");
    setUpViewForType(localView, localType, localSubscriberInfo, this.mUiMode, str);
    return localView;
  }

  public void setOnResultListener(AddCarrierBillingResultListener paramAddCarrierBillingResultListener)
  {
    this.mListener = paramAddCarrierBillingResultListener;
  }

  public static abstract interface AddCarrierBillingResultListener
  {
    public abstract void onAddCarrierBillingResult(AddResult paramAddResult);

    public static enum AddResult
    {
      static
      {
        NETWORK_FAILURE = new AddResult("NETWORK_FAILURE", 1);
        CANCELED = new AddResult("CANCELED", 2);
        EDIT_ADDRESS = new AddResult("EDIT_ADDRESS", 3);
        AddResult[] arrayOfAddResult = new AddResult[4];
        arrayOfAddResult[0] = SUCCESS;
        arrayOfAddResult[1] = NETWORK_FAILURE;
        arrayOfAddResult[2] = CANCELED;
        arrayOfAddResult[3] = EDIT_ADDRESS;
      }
    }
  }

  private class CarrierTosWebViewClient extends WebViewClient
  {
    private final View mProgress;
    private boolean mReceivedError;
    private boolean mShouldOverrideUrl;
    private final View mTosDisplayView;

    public CarrierTosWebViewClient(View paramView1, View arg3)
    {
      this.mProgress = paramView1;
      Object localObject;
      this.mTosDisplayView = localObject;
      this.mReceivedError = false;
      this.mProgress.setVisibility(0);
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      this.mShouldOverrideUrl = true;
      paramWebView.setVisibility(0);
      this.mTosDisplayView.setVisibility(0);
      if (!this.mReceivedError)
        AddCarrierBillingFragment.this.mAcceptButton.setEnabled(true);
      this.mProgress.setVisibility(8);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      FinskyLog.w("Web error: (" + paramString2 + ") " + paramString1, new Object[0]);
      this.mReceivedError = true;
      if (AddCarrierBillingFragment.this.mListener != null)
        AddCarrierBillingFragment.this.mListener.onAddCarrierBillingResult(AddCarrierBillingFragment.AddCarrierBillingResultListener.AddResult.NETWORK_FAILURE);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (this.mShouldOverrideUrl)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        AddCarrierBillingFragment.this.startActivity(localIntent);
      }
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }

  public static enum Type
  {
    static
    {
      Type[] arrayOfType = new Type[7];
      arrayOfType[0] = ADDRESS_SNIPPET;
      arrayOfType[1] = ADDRESS_SNIPPET_AND_TOS;
      arrayOfType[2] = FULL_ADDRESS;
      arrayOfType[3] = FULL_ADDRESS_AND_TOS;
      arrayOfType[4] = MINIMAL_ADDRESS;
      arrayOfType[5] = MINIMAL_ADDRESS_AND_TOS;
      arrayOfType[6] = TOS;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.AddCarrierBillingFragment
 * JD-Core Version:    0.6.2
 */
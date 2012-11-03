package com.google.android.finsky.billing.carrierbilling.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingParameters;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.utils.FinskyLog;

public class CarrierTosDialogFragment extends DialogFragment
  implements ButtonBar.ClickListener
{
  private ButtonBar mButtonBar;
  private CarrierTosResultListener mListener;
  private String mTosUrl;
  private CarrierTosWebViewClient mTosWebViewclient;

  public static CarrierTosDialogFragment newInstance(String paramString)
  {
    CarrierTosDialogFragment localCarrierTosDialogFragment = new CarrierTosDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("carrier_tos_url", paramString);
    localCarrierTosDialogFragment.setArguments(localBundle);
    return localCarrierTosDialogFragment;
  }

  private void setUpTos(String paramString)
  {
    String str = getString(2131165263);
    if (!TextUtils.isEmpty(str))
      paramString = paramString.replace("%locale%", str);
    this.mTosUrl = BillingUtils.replaceLocale(paramString);
  }

  CarrierTosWebViewClient getCarrierTosWebViewClient(View paramView1, View paramView2)
  {
    return new CarrierTosWebViewClient(paramView1, paramView2);
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    this.mListener.onCarrierTosResult(CarrierTosDialogFragment.CarrierTosResultListener.TosResult.CANCELED);
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    View localView = View.inflate(getActivity(), 2130968613, null);
    setUpTos(getArguments().getString("carrier_tos_url"));
    this.mButtonBar = ((ButtonBar)localView.findViewById(2131230825));
    this.mButtonBar.setClickListener(this);
    this.mButtonBar.setPositiveButtonTitle(2131165399);
    this.mButtonBar.setNegativeButtonTitle(2131165401);
    this.mButtonBar.setPositiveButtonEnabled(false);
    WebView localWebView = (WebView)localView.findViewById(2131230833);
    this.mTosWebViewclient = getCarrierTosWebViewClient(localView.findViewById(2131230834), localView.findViewById(2131230832));
    localWebView.setWebViewClient(this.mTosWebViewclient);
    localWebView.loadUrl(this.mTosUrl);
    localWebView.getSettings().setSupportZoom(false);
    String str = BillingLocator.getCarrierBillingStorage().getParams().getName();
    AlertDialog localAlertDialog = new AlertDialog.Builder(getActivity()).setTitle(getString(2131165262, new Object[] { str })).create();
    localAlertDialog.setView(localView, 0, 5, 0, 0);
    return localAlertDialog;
  }

  public void onNegativeButtonClick()
  {
    this.mListener.onCarrierTosResult(CarrierTosDialogFragment.CarrierTosResultListener.TosResult.CANCELED);
  }

  public void onPositiveButtonClick()
  {
    this.mListener.onCarrierTosResult(CarrierTosDialogFragment.CarrierTosResultListener.TosResult.SUCCESS);
  }

  public void setOnResultListener(CarrierTosResultListener paramCarrierTosResultListener)
  {
    this.mListener = paramCarrierTosResultListener;
  }

  public static abstract interface CarrierTosResultListener
  {
    public abstract void onCarrierTosResult(TosResult paramTosResult);

    public static enum TosResult
    {
      static
      {
        FAILURE = new TosResult("FAILURE", 1);
        CANCELED = new TosResult("CANCELED", 2);
        TosResult[] arrayOfTosResult = new TosResult[3];
        arrayOfTosResult[0] = SUCCESS;
        arrayOfTosResult[1] = FAILURE;
        arrayOfTosResult[2] = CANCELED;
      }
    }
  }

  private class CarrierTosWebViewClient extends WebViewClient
  {
    private final View mProgress;
    private boolean mReceivedError;
    private final View mTosDisplayView;

    public CarrierTosWebViewClient(View paramView1, View arg3)
    {
      this.mProgress = paramView1;
      Object localObject;
      this.mTosDisplayView = localObject;
      this.mReceivedError = false;
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      paramWebView.setVisibility(0);
      this.mTosDisplayView.setVisibility(0);
      if (!this.mReceivedError)
        CarrierTosDialogFragment.this.mButtonBar.setPositiveButtonEnabled(true);
      this.mProgress.setVisibility(8);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      FinskyLog.w("Web error: (" + paramString2 + ") " + paramString1, new Object[0]);
      this.mReceivedError = true;
      CarrierTosDialogFragment.this.mListener.onCarrierTosResult(CarrierTosDialogFragment.CarrierTosResultListener.TosResult.FAILURE);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.CarrierTosDialogFragment
 * JD-Core Version:    0.6.2
 */
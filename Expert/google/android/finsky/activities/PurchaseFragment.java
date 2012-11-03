package com.google.android.finsky.activities;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.Analytics;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiContext;
import com.google.android.finsky.api.DfeUtils;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.billing.AsyncAuthenticator;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.CheckoutPurchase;
import com.google.android.finsky.billing.CheckoutPurchase.Error;
import com.google.android.finsky.billing.CheckoutPurchase.ErrorType;
import com.google.android.finsky.billing.CheckoutPurchase.Listener;
import com.google.android.finsky.billing.CheckoutPurchase.PurchaseStatusListener;
import com.google.android.finsky.billing.CheckoutPurchase.State;
import com.google.android.finsky.billing.CheckoutPurchase.Tos;
import com.google.android.finsky.billing.DownloadSizeDialogFragment;
import com.google.android.finsky.billing.DownloadSizeDialogFragment.DownloadSizeDialogListener;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.billing.carrierbilling.CarrierBillingUtils;
import com.google.android.finsky.billing.creditcard.CreditCardInstrument;
import com.google.android.finsky.billing.giftcard.GiftCardFormOfPayment;
import com.google.android.finsky.billing.storedvalue.StoredValueFormOfPayment;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.PurchaseDocumentDetailsViewBinder;
import com.google.android.finsky.fragments.PurchaseInstrumentAndPriceViewBinder;
import com.google.android.finsky.fragments.PurchaseInstrumentAndPriceViewBinder.OnInstrumentSelectedListener;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.AccessibleLinearLayout;
import com.google.android.finsky.layout.ObservableScrollView;
import com.google.android.finsky.layout.ObservableScrollView.ScrollListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.Buy.LineItem;
import com.google.android.finsky.remoting.protos.Buy.Money;
import com.google.android.finsky.remoting.protos.Buy.PurchaseStatusResponse;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Challenge;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.finsky.utils.PurchaseInitiator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PurchaseFragment extends UrlBasedPageFragment
  implements CompoundButton.OnCheckedChangeListener, SelectInstrumentFamilyDialog.SelectInstrumentFamilyListener, SimpleAlertDialog.Listener, BillingFlowContext, CheckoutPurchase.Listener, DownloadSizeDialogFragment.DownloadSizeDialogListener, PurchaseInstrumentAndPriceViewBinder.OnInstrumentSelectedListener
{
  private Account mAccount;
  private TextView mAccountNameView;
  private AccessibleLinearLayout mAccountPanel;
  private View mAccountPayWithView;
  private DfeApi mAccountQualifiedDfe;
  private View mAccountSeparator;
  private View mAccountSeparatorWrapper;
  private Button mAcquireButton;
  private Button mAddInstrumentButton;
  private boolean mAddInstrumentScreenShown;
  private BillingFlowListener mBillingFlowListener = new BillingFlowListener()
  {
    public void onError(BillingFlow paramAnonymousBillingFlow, String paramAnonymousString)
    {
      PurchaseFragment.access$002(PurchaseFragment.this, null);
      ErrorDialog.show(PurchaseFragment.this.getActivity().getSupportFragmentManager(), null, paramAnonymousString, false);
      PurchaseFragment.this.updateUiFromInstrument();
    }

    public void onFinished(BillingFlow paramAnonymousBillingFlow, boolean paramAnonymousBoolean, Bundle paramAnonymousBundle)
    {
      PurchaseFragment.access$002(PurchaseFragment.this, null);
      if (paramAnonymousBoolean)
        PurchaseFragment.this.updateUiFromInstrument();
      while (true)
      {
        return;
        PurchaseFragment.this.completeCheckoutPurchase();
      }
    }
  };
  private boolean mChallenging;
  private CheckoutPurchase mCheckoutPurchase;
  private BillingFlow mCompletingBillingFlow;
  private String mContinueUrl;
  private ViewGroup mDetailsPanel;
  private DfeDetails mDfeDetails;
  private Document mDoc;
  private boolean mDynamicButtonContainer;
  private String mExternalReferrer;
  private ViewGroup mExtraDetailsContainer;
  private ObservableScrollView mExtraDetailsScroller;
  private CheckoutPurchase.Error mFinishedWithError;
  private boolean mFinishedWithSuccess;
  private ViewGroup mFooterContainer;
  private IabParameters mIabParameters;
  private DfeDetails mInnerDetails;
  private PurchaseInstrumentAndPriceViewBinder mInstrumentAndPriceViewBinder;
  private InstrumentFactory mInstrumentFactory = new InstrumentFactory();
  private boolean mInstrumentsRegistered;
  private boolean mIsDirectPurchase;
  private boolean mIsRetainedInstance;
  private String mLastSelectedInstrumentId;
  private View mLeadingStrip;
  private PurchaseFragmentListener mListener;
  private int mOfferType;
  private Document mPurchaseDoc;
  private PurchaseDocumentDetailsViewBinder mPurchaseDocumentDetailsViewBinder;
  private View mPurchaseRow;
  private View mPurchaseRowFooter;
  private String mReferrer;
  private String mReferrerCookie;
  private Bundle mSavedInstanceState;
  private Button mSelectInstrumentFamilyButton;
  private CommonDevice.Instrument mSelectedEligibleInstrument = null;
  private Instrument mSelectedInstrument;
  private DetailsSummaryViewBinder mSummaryViewBinder;
  private CheckoutPurchase.Error mSuppressError;
  private Bundle mViewStates;
  private TextView mWalletByline;

  private void answerChallenge(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.hasExtra("challenge_response")))
    {
      Bundle localBundle = (Bundle)paramIntent.getParcelableExtra("challenge_response");
      this.mCheckoutPurchase.answerChallenge(localBundle);
    }
  }

  private void attemptDocLoadFromPurchaseDocId()
  {
    String str = getArguments().getString("docId_to_purchase");
    this.mInnerDetails = new DfeDetails(this.mDfeApi, DfeUtils.createDetailsUrlFromId(str));
    this.mInnerDetails.addDataChangedListener(new OnDataChangedListener()
    {
      public void onDataChanged()
      {
        if (PurchaseFragment.this.mInnerDetails.getDocument() != null)
        {
          PurchaseFragment.access$2402(PurchaseFragment.this, PurchaseFragment.this.mInnerDetails.getDocument());
          PurchaseFragment.this.documentSuccessfullyFound();
        }
        while (true)
        {
          return;
          PurchaseFragment.this.showDocumentLoadError(null);
        }
      }
    });
    this.mInnerDetails.addErrorListener(new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        PurchaseFragment.this.showDocumentLoadError(PurchaseFragment.this.mInnerDetails.getVolleyError());
      }
    });
  }

  private void beginCheckout()
  {
    FragmentActivity localFragmentActivity;
    String str;
    if (this.mCheckoutPurchase != null)
    {
      localFragmentActivity = getActivity();
      if (!((Boolean)FinskyPreferences.purchaseLock.get()).booleanValue())
        break label168;
      str = (String)FinskyPreferences.purchasePin.get();
      if (!TextUtils.isEmpty(str))
        break label61;
      FinskyLog.wtf("Purchase Lock set, but no PIN code set.  Allowing purchase.", new Object[0]);
    }
    while (true)
    {
      continueCheckoutSizeCheck();
      while (true)
      {
        return;
        label61: FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "pinLock.purchase?doc=" + this.mPurchaseDoc.getDocId());
        FinskyEventLog localFinskyEventLog2 = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = "cidi";
        arrayOfObject2[1] = this.mPurchaseDoc.getDocId();
        localFinskyEventLog2.logTag("pinLock.purchase", arrayOfObject2);
        startActivityForResult(PinEntryDialog.getIntent(localFragmentActivity, 2131165647, str, null, false), 30);
      }
      label168: FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "pinLock.none?doc=" + this.mPurchaseDoc.getDocId());
      FinskyEventLog localFinskyEventLog1 = FinskyApp.get().getEventLogger();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = "cidi";
      arrayOfObject1[1] = this.mPurchaseDoc.getDocId();
      localFinskyEventLog1.logTag("pinLock.none", arrayOfObject1);
    }
  }

  private String buildCompleteAnalyticsExtra()
  {
    String str2;
    if (this.mSelectedInstrument == null)
    {
      FinskyLog.e("mSelectedInstrument cannot be null here.", new Object[0]);
      str2 = "";
      return str2;
    }
    int i = this.mSelectedInstrument.getInstrumentFamily();
    Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = this.mSelectedInstrument.getCheckoutOption();
    if ((localCheckoutOption != null) && (localCheckoutOption.hasTotal()) && (localCheckoutOption.getTotal().hasAmount()));
    for (Buy.Money localMoney = localCheckoutOption.getTotal().getAmount(); ; localMoney = null)
    {
      String str1;
      if (localMoney != null)
      {
        str1 = localMoney.getCurrencyCode();
        label80: if (localMoney == null)
          break label140;
      }
      label140: for (long l = localMoney.getMicros(); ; l = -1L)
      {
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Integer.valueOf(i);
        arrayOfObject[1] = str1;
        arrayOfObject[2] = Long.valueOf(l);
        str2 = String.format("instrumentFamily=%d&priceCurrency=%s&priceMicros=%d", arrayOfObject);
        break;
        str1 = "";
        break label80;
      }
    }
  }

  private Bundle buildCompleteFlowParameters()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("referrer_url", this.mReferrer);
    localBundle.putString("referrer_list_cookie", this.mReferrerCookie);
    localBundle.putString("authAccount", this.mAccount.name);
    return localBundle;
  }

  private String buildViewAnalyticsExtra()
  {
    String str3;
    if (this.mCheckoutPurchase == null)
    {
      FinskyLog.e("mCheckoutPurchase cannot be null here.", new Object[0]);
      str3 = "";
      return str3;
    }
    Buy.Money localMoney1 = null;
    Instrument localInstrument1 = this.mCheckoutPurchase.getDefaultInstrument();
    int i;
    Object localObject;
    if (localInstrument1 != null)
    {
      int j = localInstrument1.getInstrumentFamily();
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = localInstrument1.getCheckoutOption();
      if ((localCheckoutOption != null) && (localCheckoutOption.hasTotal()) && (localCheckoutOption.getTotal().hasAmount()))
        localMoney1 = localCheckoutOption.getTotal().getAmount();
      i = j;
      localObject = localMoney1;
    }
    while (true)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      StringBuilder localStringBuilder2 = new StringBuilder();
      Iterator localIterator1 = this.mCheckoutPurchase.getInstruments().iterator();
      label123: if (localIterator1.hasNext())
      {
        Instrument localInstrument2 = (Instrument)localIterator1.next();
        if (localInstrument2.isValid());
        for (StringBuilder localStringBuilder3 = localStringBuilder2; ; localStringBuilder3 = localStringBuilder1)
        {
          localStringBuilder3.append(localInstrument2.getInstrumentFamily()).append(',');
          break label123;
          Buy.BuyResponse.CheckoutInfo localCheckoutInfo = this.mCheckoutPurchase.getCheckoutInfo();
          if ((localCheckoutInfo == null) || (localCheckoutInfo.getItem() == null) || (!localCheckoutInfo.getItem().hasAmount()))
            break label433;
          Buy.Money localMoney2 = localCheckoutInfo.getItem().getAmount();
          i = -1;
          localObject = localMoney2;
          break;
        }
      }
      if (!this.mCheckoutPurchase.getToses().isEmpty());
      ArrayList localArrayList;
      for (boolean bool = true; ; bool = false)
      {
        localArrayList = Lists.newArrayList();
        Iterator localIterator2 = this.mCheckoutPurchase.getEligibleInstruments().iterator();
        while (localIterator2.hasNext())
          localArrayList.add(Integer.valueOf(((CommonDevice.Instrument)localIterator2.next()).getInstrumentFamily()));
      }
      String str1 = TextUtils.join(",", localArrayList);
      String str2;
      if (localObject != null)
      {
        str2 = ((Buy.Money)localObject).getCurrencyCode();
        label333: if (localObject == null)
          break label425;
      }
      label425: for (long l = ((Buy.Money)localObject).getMicros(); ; l = -1L)
      {
        Object[] arrayOfObject = new Object[7];
        arrayOfObject[0] = Integer.valueOf(i);
        arrayOfObject[1] = Boolean.valueOf(bool);
        arrayOfObject[2] = str2;
        arrayOfObject[3] = Long.valueOf(l);
        arrayOfObject[4] = localStringBuilder2;
        arrayOfObject[5] = localStringBuilder1;
        arrayOfObject[6] = str1;
        str3 = String.format("defaultInstrumentFamily=%d&hasTos=%s&priceCurrency=%s&priceMicros=%d&availableFamilies=%s&invalidFamilies=%s&eligibleFamilies=%s", arrayOfObject);
        break;
        str2 = "";
        break label333;
      }
      label433: i = -1;
      localObject = null;
    }
  }

  private void checkForInstallationState()
  {
    AppStates.AppState localAppState = FinskyApp.get().getAppStates().getApp(this.mPurchaseDoc.getDocId());
    if ((localAppState != null) && (localAppState.packageManagerState != null) && (localAppState.packageManagerState.installedVersion >= this.mPurchaseDoc.getAppDetails().getVersionCode()))
      showAlreadyOwnedOrInstalledDialog(2131165517, -1, 2131165599, 3, "already_installed_app");
  }

  private void checkForMultiUserCertificateConflict()
  {
    if (AppActionAnalyzer.isMultiUserCertificateConflict(this.mPurchaseDoc))
      showAlreadyOwnedOrInstalledDialog(2131165518, -1, 2131165599, 5, "already_installed_other_user");
  }

  private void completeCheckoutPurchase()
  {
    if (isAdded())
    {
      String str1 = "completePurchase?doc=" + this.mPurchaseDoc.getDocId() + "&" + buildCompleteAnalyticsExtra();
      FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, str1);
      logCompletePurchaseEvent();
      String str2 = BillingUtils.getRiskHeader();
      this.mCheckoutPurchase.complete(this.mSelectedInstrument, str2);
    }
  }

  private void continueCheckout()
  {
    if (this.mCheckoutPurchase != null)
      runCompleteFlowAndCompleteCheckoutPurchase();
    while (true)
    {
      return;
      continueFreeCheckout();
    }
  }

  private void continueCheckoutSizeCheck()
  {
    InstallPolicies localInstallPolicies;
    long l2;
    DocDetails.AppDetails localAppDetails;
    FragmentManager localFragmentManager;
    if ((this.mPurchaseDoc != null) && (this.mPurchaseDoc.getAppDetails() != null))
    {
      localInstallPolicies = FinskyApp.get().getInstallPolicies();
      long l1 = localInstallPolicies.getMaxBytesOverMobileRecommended();
      l2 = localInstallPolicies.getMaxBytesOverMobile();
      localAppDetails = this.mPurchaseDoc.getAppDetails();
      if ((localInstallPolicies.hasMobileNetwork()) && (l1 > 0L) && (AssetUtils.totalDeliverySize(localAppDetails) >= l1))
      {
        localFragmentManager = getFragmentManager();
        if (localFragmentManager.findFragmentByTag("wifi_download_dialog") == null);
      }
    }
    while (true)
    {
      return;
      if (this.mPurchaseDoc.getAppDetails().getInstallationSize() < l2);
      for (boolean bool = true; ; bool = false)
      {
        DownloadSizeDialogFragment.newInstance(this, true, bool, localInstallPolicies.isMobileNetwork()).show(localFragmentManager, "wifi_download_dialog");
        break;
      }
      String str = localAppDetails.getPackageName();
      FinskyApp.get().getInstaller().setMobileDataAllowed(str);
      continueCheckout();
    }
  }

  private void continueFreeCheckout()
  {
    FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "confirmFreeDownload?doc=" + this.mPurchaseDoc.getDocId());
    FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "cidi";
    arrayOfObject[1] = this.mPurchaseDoc.getDocId();
    localFinskyEventLog.logTag("confirmFreeDownload", arrayOfObject);
    if (!getLibrary().contains(LibraryEntry.fromDocument(this.mAccount.name, AccountLibrary.getLibraryIdFromBackend(this.mPurchaseDoc.getBackend()), this.mPurchaseDoc, this.mOfferType)))
      PurchaseInitiator.makeFreePurchase(this.mAccount, this.mPurchaseDoc, this.mOfferType, this.mExternalReferrer, this.mReferrerCookie, this.mContinueUrl);
    while (true)
    {
      finishWithSuccess();
      return;
      if (this.mPurchaseDoc.getDocumentType() == 1)
        PurchaseInitiator.initiateDownload(this.mAccount, this.mPurchaseDoc, this.mExternalReferrer, this.mContinueUrl, null);
    }
  }

  private void documentSuccessfullyFound()
  {
    switchToData();
    this.mReferrer = getArguments().getString("referrer_url");
    this.mReferrerCookie = getArguments().getString("referrer_cookie");
    this.mExternalReferrer = getArguments().getString("referrer");
    this.mContinueUrl = getArguments().getString("continue_url");
    this.mDetailsPanel.setVisibility(0);
    this.mLeadingStrip.setVisibility(0);
    this.mExtraDetailsScroller.setVisibility(0);
    onDataChanged();
    if (!isFreeUi())
    {
      if (this.mSavedInstanceState == null)
      {
        FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "purchase?doc=" + this.mPurchaseDoc.getDocId());
        FinskyEventLog localFinskyEventLog2 = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = "cidi";
        arrayOfObject2[1] = this.mPurchaseDoc.getDocId();
        localFinskyEventLog2.logTag("purchase", arrayOfObject2);
      }
      updateUiFromInstrument();
      initializeBilling();
    }
    while (true)
    {
      getView().requestFocus();
      return;
      if (this.mSavedInstanceState == null)
      {
        FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "freeDownload?doc=" + this.mPurchaseDoc.getDocId());
        FinskyEventLog localFinskyEventLog1 = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = "cidi";
        arrayOfObject1[1] = this.mPurchaseDoc.getDocId();
        localFinskyEventLog1.logTag("freeDownload", arrayOfObject1);
      }
      setupFreeUi();
    }
  }

  private void fillInTosses()
  {
    ContextThemeWrapper localContextThemeWrapper = new ContextThemeWrapper(this.mContext, 2131624006);
    LinearLayout localLinearLayout = (LinearLayout)this.mDataView.findViewById(2131231159);
    LayoutInflater localLayoutInflater = LayoutInflater.from(localContextThemeWrapper);
    localLinearLayout.removeAllViews();
    Iterator localIterator = this.mCheckoutPurchase.getToses().iterator();
    while (localIterator.hasNext())
    {
      CheckoutPurchase.Tos localTos = (CheckoutPurchase.Tos)localIterator.next();
      Spanned localSpanned = Html.fromHtml(localTos.getShorthand());
      View localView = localLayoutInflater.inflate(2130968803, localLinearLayout, false);
      CheckBox localCheckBox = (CheckBox)localView.findViewById(2131231178);
      localCheckBox.setChecked(localTos.getAcceptance());
      localCheckBox.setOnCheckedChangeListener(this);
      localCheckBox.setTag(localTos);
      localCheckBox.setContentDescription(localSpanned);
      TextView localTextView = (TextView)localView.findViewById(2131231179);
      localTextView.setText(localSpanned);
      localTextView.setMovementMethod(LinkMovementMethod.getInstance());
      localLinearLayout.addView(localView);
    }
  }

  private void finishWithError(CheckoutPurchase.Error paramError)
  {
    if (this.mCheckoutPurchase != null)
    {
      this.mCheckoutPurchase.detach();
      this.mCheckoutPurchase = null;
    }
    if (this.mFinishedWithError != null)
      FinskyLog.d("Ignoring second error: %s", new Object[] { paramError });
    while (true)
    {
      return;
      this.mFinishedWithError = paramError;
      this.mFinishedWithSuccess = false;
      if (this.mListener != null)
        this.mListener.onFailure(paramError);
      else if (paramError.message != null)
        this.mPageFragmentHost.showErrorDialog(null, paramError.message, true);
      else
        this.mPageFragmentHost.goBack();
    }
  }

  private void finishWithSuccess()
  {
    Bundle localBundle = null;
    if (this.mCheckoutPurchase != null)
    {
      localBundle = this.mCheckoutPurchase.getExtraPurchaseData();
      this.mCheckoutPurchase.detach();
      this.mCheckoutPurchase = null;
    }
    if (this.mFinishedWithSuccess);
    while (true)
    {
      return;
      this.mFinishedWithSuccess = true;
      this.mFinishedWithError = null;
      if (this.mListener != null)
      {
        this.mListener.onSuccess(localBundle);
      }
      else if (this.mPurchaseDoc.getDocumentType() == 1)
      {
        this.mPageFragmentHost.goBack();
      }
      else
      {
        this.mPageFragmentHost.goBack();
        String str = DfeUtils.createDetailsUrlFromId(getArguments().getString("docId_to_purchase"));
        PostPurchaseDialog.show(this.mContext, str, this.mOfferType, this.mAccount.name);
      }
    }
  }

  private Library getLibrary()
  {
    return FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount);
  }

  private void initializeBilling()
  {
    if (this.mCheckoutPurchase != null);
    while (true)
    {
      return;
      this.mInstrumentAndPriceViewBinder.switchToLoading(2131165283);
      CarrierBillingUtils.initializeCarrierBillingStorage(new Runnable()
      {
        public void run()
        {
          CarrierBillingUtils.initializeCarrierBillingParams(PurchaseFragment.this.mContext, new Runnable()
          {
            public void run()
            {
              PurchaseFragment.this.loadBillingCountries();
              PurchaseFragment.this.registerInstrumentFactories();
              if (CarrierBillingUtils.isDcb30())
                PurchaseFragment.this.startOrResumePurchase();
              while (true)
              {
                return;
                PurchaseFragment.this.initializeDcb2Provisioning();
              }
            }
          });
        }
      });
    }
  }

  private void initializeDcb2Provisioning()
  {
    CarrierBillingUtils.initializeCarrierBillingProvisioning(new Runnable()
    {
      public void run()
      {
        PurchaseFragment.this.startOrResumePurchase();
      }
    });
  }

  private boolean isEveryTosAccepted()
  {
    if ((this.mCheckoutPurchase == null) || (this.mCheckoutPurchase.areAllTossesAccepted()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isFreeUi()
  {
    if ((LibraryUtils.isOfferOwned(this.mPurchaseDoc, getLibrary(), this.mOfferType)) || (!this.mPurchaseDoc.needsCheckoutFlow(this.mOfferType)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isInstrumentRequired()
  {
    if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.isInstrumentRequired()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private void loadBillingCountries()
  {
    new GetBillingCountriesAction().run(this.mAccount.name, null);
  }

  private void logViewPurchaseEvent()
  {
    if (this.mCheckoutPurchase == null)
    {
      FinskyLog.e("mCheckoutPurchase cannot be null here.", new Object[0]);
      return;
    }
    Buy.Money localMoney1 = null;
    Instrument localInstrument1 = this.mCheckoutPurchase.getDefaultInstrument();
    int i;
    Object localObject;
    if (localInstrument1 != null)
    {
      int j = localInstrument1.getInstrumentFamily();
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = localInstrument1.getCheckoutOption();
      if ((localCheckoutOption != null) && (localCheckoutOption.hasTotal()) && (localCheckoutOption.getTotal().hasAmount()))
        localMoney1 = localCheckoutOption.getTotal().getAmount();
      i = j;
      localObject = localMoney1;
    }
    while (true)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      StringBuilder localStringBuilder2 = new StringBuilder();
      Iterator localIterator1 = this.mCheckoutPurchase.getInstruments().iterator();
      label116: if (localIterator1.hasNext())
      {
        Instrument localInstrument2 = (Instrument)localIterator1.next();
        if (localInstrument2.isValid());
        for (StringBuilder localStringBuilder3 = localStringBuilder2; ; localStringBuilder3 = localStringBuilder1)
        {
          localStringBuilder3.append(localInstrument2.getInstrumentFamily()).append(',');
          break label116;
          Buy.BuyResponse.CheckoutInfo localCheckoutInfo = this.mCheckoutPurchase.getCheckoutInfo();
          if ((localCheckoutInfo == null) || (localCheckoutInfo.getItem() == null) || (!localCheckoutInfo.getItem().hasAmount()))
            break label510;
          Buy.Money localMoney2 = localCheckoutInfo.getItem().getAmount();
          i = -1;
          localObject = localMoney2;
          break;
        }
      }
      if (!this.mCheckoutPurchase.getToses().isEmpty());
      ArrayList localArrayList;
      for (boolean bool = true; ; bool = false)
      {
        localArrayList = Lists.newArrayList();
        Iterator localIterator2 = this.mCheckoutPurchase.getEligibleInstruments().iterator();
        while (localIterator2.hasNext())
          localArrayList.add(Integer.valueOf(((CommonDevice.Instrument)localIterator2.next()).getInstrumentFamily()));
      }
      String str1 = TextUtils.join(",", localArrayList);
      String str2;
      if (localObject != null)
      {
        str2 = ((Buy.Money)localObject).getCurrencyCode();
        label326: if (localObject == null)
          break label502;
      }
      label502: for (long l = ((Buy.Money)localObject).getMicros(); ; l = -1L)
      {
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[16];
        arrayOfObject[0] = "cidi";
        arrayOfObject[1] = this.mPurchaseDoc.getDocId();
        arrayOfObject[2] = "defaultInstrumentFamily";
        arrayOfObject[3] = Integer.valueOf(i);
        arrayOfObject[4] = "hasTos";
        arrayOfObject[5] = Boolean.valueOf(bool);
        arrayOfObject[6] = "priceCurrency";
        arrayOfObject[7] = str2;
        arrayOfObject[8] = "priceMicros";
        arrayOfObject[9] = Long.valueOf(l);
        arrayOfObject[10] = "availableFamilies";
        arrayOfObject[11] = localStringBuilder2;
        arrayOfObject[12] = "invalidFamilies";
        arrayOfObject[13] = localStringBuilder1;
        arrayOfObject[14] = "eligibleFamilies";
        arrayOfObject[15] = str1;
        localFinskyEventLog.logTag("viewPurchase", arrayOfObject);
        break;
        str2 = "";
        break label326;
      }
      label510: i = -1;
      localObject = null;
    }
  }

  public static PurchaseFragment newIabInstance(Account paramAccount, String paramString, IabParameters paramIabParameters)
  {
    PurchaseFragment localPurchaseFragment = newInstance(paramAccount, DfeUtils.createDetailsUrlFromId(paramString), 1, null, null, false, null, null, paramString);
    localPurchaseFragment.setArgument("iab_parameters", paramIabParameters);
    return localPurchaseFragment;
  }

  public static PurchaseFragment newInstance(Account paramAccount, String paramString1, int paramInt, String paramString2, String paramString3, boolean paramBoolean, String paramString4, String paramString5, String paramString6)
  {
    PurchaseFragment localPurchaseFragment = new PurchaseFragment();
    localPurchaseFragment.setTheme(2131624002);
    localPurchaseFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localPurchaseFragment.setArgument("account", paramAccount);
    localPurchaseFragment.setArgument("offer_type", paramInt);
    localPurchaseFragment.setArgument("referrer_url", paramString2);
    localPurchaseFragment.setArgument("referrer_cookie", paramString3);
    localPurchaseFragment.setArgument("is_direct_link_purchase", paramBoolean);
    localPurchaseFragment.setArgument("referrer", paramString4);
    localPurchaseFragment.setArgument("continue_url", paramString5);
    localPurchaseFragment.setArgument("docId_to_purchase", paramString6);
    return localPurchaseFragment;
  }

  private void onAddInstrumentError(String paramString)
  {
    this.mCheckoutPurchase.prepare();
  }

  private void onAddInstrumentFinished(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.mCheckoutPurchase.prepare();
    while (true)
    {
      return;
      if (this.mSelectedEligibleInstrument != null)
        this.mInstrumentAndPriceViewBinder.selectAddInstrument(this.mSelectedEligibleInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(this.mSelectedEligibleInstrument));
      else
        this.mInstrumentAndPriceViewBinder.selectInstrument(this.mLastSelectedInstrumentId);
    }
  }

  private void onAddInstrumentResult(Intent paramIntent)
  {
    if (paramIntent == null)
      onAddInstrumentFinished(true);
    while (true)
    {
      return;
      if (paramIntent.getBooleanExtra("billing_flow_error", true))
        onAddInstrumentError(paramIntent.getStringExtra("billing_flow_error_message"));
      else
        onAddInstrumentFinished(paramIntent.getBooleanExtra("billing_flow_canceled", true));
    }
  }

  private void onDocumentLoaded(Document paramDocument)
  {
    if (paramDocument == null)
      showDocumentLoadError(this.mDfeDetails.getVolleyError());
    while (true)
    {
      return;
      this.mDoc = paramDocument;
      String str = getArguments().getString("docId_to_purchase");
      if ((TextUtils.isEmpty(str)) || (this.mDoc.getDocId().equals(str)))
      {
        this.mPurchaseDoc = this.mDoc;
        documentSuccessfullyFound();
      }
      else
      {
        attemptDocLoadFromPurchaseDocId();
      }
    }
  }

  private void onUpdateAddressResult(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getBooleanExtra("billing_flow_error", true)))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramIntent.getStringExtra("billing_flow_error_message");
      FinskyLog.e("Update Address error: %s", arrayOfObject);
    }
    this.mCheckoutPurchase.prepare();
  }

  private void openDocument(String paramString)
  {
    startActivity(IntentUtils.createViewDocumentUrlIntent(this.mContext, DfeUtils.createDetailsUrlFromId(paramString)));
  }

  private void registerInstrumentFactories()
  {
    if (!this.mInstrumentsRegistered)
    {
      this.mInstrumentsRegistered = true;
      CreditCardInstrument.registerWithFactory(this.mInstrumentFactory);
      GiftCardFormOfPayment.registerWithInstrumentFactory(this.mInstrumentFactory);
      StoredValueFormOfPayment.registerWithInstrumentFactory(this.mInstrumentFactory);
      CarrierBillingUtils.registerDcbInstrument(this.mInstrumentFactory);
    }
  }

  private void restoreSelectedInstrumentAndBillingFlow()
  {
    if (this.mIsRetainedInstance)
      if (this.mSelectedEligibleInstrument != null)
        this.mInstrumentAndPriceViewBinder.selectAddInstrument(this.mSelectedEligibleInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(this.mSelectedEligibleInstrument));
    label139: 
    while (true)
    {
      if (this.mViewStates != null)
        resumeBillingFlowFromBundle(this.mViewStates);
      while (true)
      {
        return;
        if (this.mLastSelectedInstrumentId == null)
          break label139;
        this.mInstrumentAndPriceViewBinder.selectInstrument(this.mLastSelectedInstrumentId);
        break;
        if (this.mSavedInstanceState == null)
        {
          updateUiFromInstrument();
        }
        else if (this.mSavedInstanceState.containsKey("selected_instrument_id"))
        {
          String str = this.mSavedInstanceState.getString("selected_instrument_id");
          this.mSelectedInstrument = this.mCheckoutPurchase.getInstrument(str);
          this.mInstrumentAndPriceViewBinder.selectInstrument(str);
          resumeBillingFlowFromBundle(this.mSavedInstanceState);
        }
      }
    }
  }

  private void resumeBillingFlowFromBundle(Bundle paramBundle)
  {
    if ((this.mSelectedInstrument != null) && (paramBundle.containsKey("completing_billing_flow")))
    {
      this.mCompletingBillingFlow = this.mSelectedInstrument.completePurchase(this, this.mBillingFlowListener, buildCompleteFlowParameters());
      if (this.mCompletingBillingFlow != null)
        this.mCompletingBillingFlow.resumeFromSavedState(paramBundle.getBundle("completing_billing_flow"));
    }
  }

  private void runCompleteFlowAndCompleteCheckoutPurchase()
  {
    if ((!isInstrumentRequired()) || (this.mSelectedInstrument != null))
    {
      this.mInstrumentAndPriceViewBinder.switchToLoading(2131165284);
      this.mAcquireButton.setEnabled(false);
      if (this.mSelectedInstrument != null)
        this.mCompletingBillingFlow = this.mSelectedInstrument.completePurchase(this, this.mBillingFlowListener, buildCompleteFlowParameters());
      if (this.mCompletingBillingFlow != null)
        this.mCompletingBillingFlow.start();
    }
    while (true)
    {
      return;
      completeCheckoutPurchase();
      continue;
      FinskyLog.wtf("No instrument selected.", new Object[0]);
    }
  }

  private void saveViewStates(Bundle paramBundle)
  {
    this.mPurchaseDocumentDetailsViewBinder.saveState(paramBundle);
    this.mInstrumentAndPriceViewBinder.saveState(paramBundle);
    if (this.mCompletingBillingFlow != null)
    {
      Bundle localBundle = new Bundle();
      this.mCompletingBillingFlow.saveState(localBundle);
      paramBundle.putBundle("completing_billing_flow", localBundle);
      this.mCompletingBillingFlow = null;
    }
  }

  private void setupFooters(Instrument paramInstrument)
  {
    this.mFooterContainer.removeAllViews();
    ArrayList localArrayList = Lists.newArrayList();
    localArrayList.addAll(this.mCheckoutPurchase.getCheckoutInfo().getFooterHtmlList());
    if (paramInstrument != null)
      localArrayList.addAll(paramInstrument.getCheckoutOption().getFooterHtmlList());
    LayoutInflater localLayoutInflater = getActivity().getLayoutInflater();
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      TextView localTextView = (TextView)localLayoutInflater.inflate(2130968798, this.mFooterContainer, false);
      localTextView.setText(Html.fromHtml(str));
      localTextView.setMovementMethod(LinkMovementMethod.getInstance());
      this.mFooterContainer.addView(localTextView);
    }
  }

  private void setupFreeUi()
  {
    this.mInstrumentAndPriceViewBinder.switchToFreeUi();
    this.mExtraDetailsScroller.setVisibility(0);
    this.mAccountSeparatorWrapper.setVisibility(0);
    View localView = this.mAccountSeparator;
    if (this.mDynamicButtonContainer);
    for (int i = 4; ; i = 0)
    {
      localView.setVisibility(i);
      DetailsSummaryViewBinder localDetailsSummaryViewBinder = this.mSummaryViewBinder;
      Document localDocument = this.mPurchaseDoc;
      View[] arrayOfView = new View[1];
      arrayOfView[0] = this.mDetailsPanel;
      localDetailsSummaryViewBinder.bind(localDocument, false, arrayOfView);
      updateUiFromInstrument();
      return;
    }
  }

  private void showAddInstrumentScreen(CommonDevice.Instrument paramInstrument)
  {
    if (this.mAddInstrumentScreenShown)
      FinskyLog.d("Already showing add instrument screen, ignoring.", new Object[0]);
    while (true)
    {
      return;
      this.mAddInstrumentScreenShown = true;
      startActivityForResult(this.mInstrumentFactory.getCreateIntent(paramInstrument, this.mAccount.name, this.mPurchaseDoc.getBackend(), BillingUtils.CreateInstrumentUiMode.INTERNAL, this.mReferrer, this.mReferrerCookie), 31);
    }
  }

  private void showAlreadyOwnedOrInstalledDialog(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
  {
    if (getFragmentManager().findFragmentByTag(paramString) != null);
    while (true)
    {
      return;
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(paramInt1, paramInt2, paramInt3);
      Bundle localBundle = new Bundle();
      localBundle.putString("docId_to_purchase", this.mPurchaseDoc.getDocId());
      localSimpleAlertDialog.setCallback(this, paramInt4, localBundle);
      localSimpleAlertDialog.setCancelable(false);
      localSimpleAlertDialog.show(getFragmentManager(), paramString);
    }
  }

  private void showAvailabilityRestriction()
  {
    if (this.mPurchaseDoc.getAvailabilityRestriction() == -1);
    while (true)
    {
      return;
      if (getFragmentManager().findFragmentByTag("availability_restriction") == null)
      {
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(DocUtils.getAvailabilityRestrictionResourceId(this.mPurchaseDoc), 2131165599, -1);
        localSimpleAlertDialog.setCallback(this, 4, Bundle.EMPTY);
        localSimpleAlertDialog.setCancelable(false);
        localSimpleAlertDialog.show(getFragmentManager(), "availability_restriction");
      }
    }
  }

  private void showDocumentLoadError(VolleyError paramVolleyError)
  {
    if (paramVolleyError == null);
    for (String str = this.mContext.getString(2131165288); ; str = ErrorStrings.get(this.mContext, paramVolleyError))
    {
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.UNKNOWN, 0, str));
      return;
    }
  }

  private void showUpdateInstrumentScreen(Instrument paramInstrument, CommonDevice.Instrument paramInstrument1)
  {
    startActivityForResult(UpdateInstrumentActivity.createIntent(this.mAccount.name, paramInstrument, paramInstrument1, this.mPurchaseDoc.getBackend(), this.mReferrer, this.mReferrerCookie), 33);
  }

  private void startOrResumePurchase()
  {
    int i = getArguments().getInt("offer_type", -1);
    if (this.mCheckoutPurchase == null)
    {
      AndroidAuthenticator localAndroidAuthenticator = new AndroidAuthenticator(FinskyApp.get(), this.mAccount, (String)G.checkoutAuthTokenType.get());
      CheckoutPurchase localCheckoutPurchase = new CheckoutPurchase(this.mAccountQualifiedDfe, new AsyncAuthenticator(localAndroidAuthenticator), this.mInstrumentFactory, new PurchaseStatusListener(this.mAccount, this.mPurchaseDoc, this.mExternalReferrer, this.mReferrerCookie, this.mContinueUrl, null), this.mPurchaseDoc, i);
      this.mCheckoutPurchase = localCheckoutPurchase;
      if ((this.mSavedInstanceState == null) || (!this.mSavedInstanceState.containsKey("checkout_purchase")))
        break label155;
      this.mCheckoutPurchase.resumeFromSavedState(this.mSavedInstanceState.getBundle("checkout_purchase"));
      restoreSelectedInstrumentAndBillingFlow();
    }
    while (true)
    {
      this.mCheckoutPurchase.attach(this);
      return;
      label155: this.mCheckoutPurchase.setIabParameters(this.mIabParameters);
      this.mCheckoutPurchase.prepare();
    }
  }

  private void updateButtons()
  {
    boolean bool1 = true;
    this.mAcquireButton.setVisibility(8);
    this.mAddInstrumentButton.setVisibility(8);
    this.mSelectInstrumentFamilyButton.setVisibility(8);
    this.mWalletByline.setVisibility(8);
    if (!LibraryUtils.isAvailable(this.mPurchaseDoc, getToc(), getLibrary()))
      showAvailabilityRestriction();
    while (true)
    {
      return;
      boolean bool2 = isFreeUi();
      if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.hasAddInstrumentHintText()))
        this.mSelectInstrumentFamilyButton.setVisibility(0);
      while (true)
        if (bool2)
        {
          this.mWalletByline.setVisibility(8);
          break;
          if (this.mSelectedEligibleInstrument != null)
          {
            this.mAddInstrumentButton.setVisibility(0);
          }
          else
          {
            this.mAcquireButton.setVisibility(0);
            if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.hasConfirmButtonText()))
              this.mAcquireButton.setText(this.mCheckoutPurchase.getConfirmButtonText());
            while (true)
              if (bool2)
              {
                this.mAcquireButton.setEnabled(isEveryTosAccepted());
                break;
                if (bool2)
                {
                  this.mAcquireButton.setText(2131165613);
                }
                else
                {
                  if (this.mPurchaseDoc.getDocumentType() == 15);
                  for (int i = 2131165614; ; i = 2131165612)
                  {
                    this.mAcquireButton.setText(i);
                    break;
                  }
                }
              }
            boolean bool3;
            label256: boolean bool4;
            label276: Button localButton;
            if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.getState() == CheckoutPurchase.State.PREPARED))
            {
              bool3 = bool1;
              if ((this.mSelectedInstrument == null) || (!this.mSelectedInstrument.isValid()))
                break label328;
              bool4 = bool1;
              localButton = this.mAcquireButton;
              if (((isInstrumentRequired()) && (!bool4)) || (!bool3) || (!isEveryTosAccepted()) || (this.mCompletingBillingFlow != null))
                break label334;
            }
            while (true)
            {
              localButton.setEnabled(bool1);
              break;
              bool3 = false;
              break label256;
              label328: bool4 = false;
              break label276;
              label334: bool1 = false;
            }
          }
        }
      this.mWalletByline.setVisibility(0);
      this.mWalletByline.setText(Html.fromHtml(getResources().getString(2131165229), new Html.ImageGetter()
      {
        public Drawable getDrawable(String paramAnonymousString)
        {
          Drawable localDrawable = PurchaseFragment.this.getResources().getDrawable(2130837598);
          localDrawable.setBounds(0, 0, localDrawable.getIntrinsicWidth(), localDrawable.getIntrinsicHeight());
          return localDrawable;
        }
      }
      , null));
    }
  }

  private void updateSummaryAndFops()
  {
    if (this.mSummaryViewBinder == null);
    while (true)
    {
      return;
      DetailsSummaryViewBinder localDetailsSummaryViewBinder = this.mSummaryViewBinder;
      Document localDocument = this.mPurchaseDoc;
      View[] arrayOfView = new View[1];
      arrayOfView[0] = this.mDetailsPanel;
      localDetailsSummaryViewBinder.bind(localDocument, false, arrayOfView);
      fillInTosses();
      updateUiFromInstrument();
    }
  }

  private void updateUiFromInstrument()
  {
    ArrayList localArrayList = Lists.newArrayList();
    updateButtons();
    int j;
    label109: boolean bool;
    if (!isFreeUi())
    {
      if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.getState() == CheckoutPurchase.State.PREPARED))
      {
        j = 1;
        if (j != 0)
        {
          localArrayList.addAll(this.mCheckoutPurchase.getCheckoutInfo().getFootnoteHtmlList());
          setupFooters(this.mSelectedInstrument);
          if (this.mSelectedInstrument != null)
            localArrayList.addAll(this.mSelectedInstrument.getCheckoutOption().getFootnoteHtmlList());
        }
        if (this.mCompletingBillingFlow == null)
          break label265;
        this.mInstrumentAndPriceViewBinder.switchToLoading(2131165284);
      }
    }
    else
    {
      PurchaseDocumentDetailsViewBinder localPurchaseDocumentDetailsViewBinder = this.mPurchaseDocumentDetailsViewBinder;
      FragmentManager localFragmentManager = getFragmentManager();
      ViewGroup localViewGroup = this.mExtraDetailsContainer;
      Document localDocument = this.mPurchaseDoc;
      int i = this.mOfferType;
      if (this.mIabParameters == null)
        break label275;
      bool = true;
      label147: localPurchaseDocumentDetailsViewBinder.bind(localFragmentManager, localViewGroup, localDocument, i, localArrayList, bool);
      this.mAccountNameView.setText(this.mAccount.name);
      this.mAccountNameView.setPadding(this.mAccountNameView.getPaddingLeft(), this.mAccountNameView.getPaddingTop(), this.mAccountNameView.getPaddingRight(), 0);
      if ((!isFreeUi()) && (isInstrumentRequired()) && ((this.mCheckoutPurchase == null) || (!this.mCheckoutPurchase.hasAddInstrumentHintText())))
        break label281;
      this.mAccountPayWithView.setVisibility(8);
      this.mAccountPanel.setContentDescription(this.mAccount.name);
    }
    while (true)
    {
      return;
      j = 0;
      break;
      label265: this.mInstrumentAndPriceViewBinder.showInstrumentAndPrice();
      break label109;
      label275: bool = false;
      break label147;
      label281: this.mAccountPayWithView.setVisibility(0);
      AccessibleLinearLayout localAccessibleLinearLayout = this.mAccountPanel;
      Resources localResources = getResources();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mAccount.name;
      localAccessibleLinearLayout.setContentDescription(localResources.getString(2131165316, arrayOfObject));
    }
  }

  protected int getLayoutRes()
  {
    return 2130968796;
  }

  public boolean handleBackPress()
  {
    if ((this.mCheckoutPurchase != null) && (this.mCheckoutPurchase.getState() == CheckoutPurchase.State.COMPLETING));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void hideFragment(Fragment paramFragment, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("This should be handled by PurchaseFlowActivity");
  }

  public void hideProgress()
  {
  }

  protected boolean isDataReady()
  {
    if (this.mDoc != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void logCompletePurchaseEvent()
  {
    if (this.mSelectedInstrument == null)
    {
      FinskyLog.e("mSelectedInstrument cannot be null here.", new Object[0]);
      return;
    }
    Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = this.mSelectedInstrument.getCheckoutOption();
    if ((localCheckoutOption != null) && (localCheckoutOption.hasTotal()) && (localCheckoutOption.getTotal().hasAmount()));
    for (Buy.Money localMoney = localCheckoutOption.getTotal().getAmount(); ; localMoney = null)
    {
      String str;
      if (localMoney != null)
      {
        str = localMoney.getCurrencyCode();
        label64: if (localMoney == null)
          break label178;
      }
      label178: for (long l = localMoney.getMicros(); ; l = -1L)
      {
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[8];
        arrayOfObject[0] = "cidi";
        arrayOfObject[1] = this.mPurchaseDoc.getDocId();
        arrayOfObject[2] = "instrumentFamily";
        arrayOfObject[3] = Integer.valueOf(this.mSelectedInstrument.getInstrumentFamily());
        arrayOfObject[4] = "priceCurrency";
        arrayOfObject[5] = str;
        arrayOfObject[6] = "priceMicros";
        arrayOfObject[7] = Long.valueOf(l);
        localFinskyEventLog.logTag("completePurchase", arrayOfObject);
        break;
        str = "";
        break label64;
      }
    }
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (paramBundle != null)
      this.mSavedInstanceState = paramBundle;
    if (getArguments().containsKey("iab_parameters"))
      this.mIabParameters = ((IabParameters)getArguments().getParcelable("iab_parameters"));
    if (getArguments().containsKey("is_direct_link_purchase"))
      this.mIsDirectPurchase = getArguments().getBoolean("is_direct_link_purchase");
    this.mOfferType = getArguments().getInt("offer_type", -1);
    if (paramBundle != null)
    {
      this.mLastSelectedInstrumentId = paramBundle.getString("last_selected_instrument_id");
      if (paramBundle.containsKey("selected_eligible_instrument"))
        this.mSelectedEligibleInstrument = ((CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(paramBundle, "selected_eligible_instrument"));
      this.mAddInstrumentScreenShown = paramBundle.getBoolean("add_instrument_screen_shown");
      this.mSuppressError = ((CheckoutPurchase.Error)paramBundle.getParcelable("suppress_error"));
      this.mFinishedWithSuccess = paramBundle.getBoolean("finished_with_success");
      this.mFinishedWithError = ((CheckoutPurchase.Error)paramBundle.getParcelable("finished_with_error"));
      this.mChallenging = paramBundle.getBoolean("challenging");
    }
    if (this.mDoc != null)
      onDocumentLoaded(this.mDoc);
    while (true)
    {
      if (isDataReady())
        rebindViews();
      while (true)
      {
        return;
        if ((paramBundle != null) && (paramBundle.containsKey("doc")))
        {
          onDocumentLoaded((Document)paramBundle.getParcelable("doc"));
          break;
        }
        switchToLoadingImmediately();
        if ((!this.mFinishedWithSuccess) && (this.mFinishedWithError == null))
          break label275;
        FinskyLog.d("PurchaseFragment finished. Not initializing.", new Object[0]);
      }
      label275: this.mDfeDetails = new DfeDetails(this.mAccountQualifiedDfe, this.mUrl);
      this.mDfeDetails.addDataChangedListener(new OnDataChangedListener()
      {
        public void onDataChanged()
        {
          if (PurchaseFragment.this.mDoc == null)
            PurchaseFragment.this.onDocumentLoaded(PurchaseFragment.this.mDfeDetails.getDocument());
          while (true)
          {
            return;
            FinskyLog.d("Ignoring soft TTL refresh.", new Object[0]);
          }
        }
      });
      this.mDfeDetails.addErrorListener(new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          String str = ErrorStrings.get(PurchaseFragment.this.getActivity(), paramAnonymousVolleyError);
          PurchaseFragment.this.finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.UNKNOWN, -1, str));
        }
      });
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, final Intent paramIntent)
  {
    Handler localHandler = new Handler(Looper.getMainLooper());
    if (paramInt1 == 31)
    {
      localHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          PurchaseFragment.access$2802(PurchaseFragment.this, false);
        }
      }
      , 1000L);
      localHandler.post(new Runnable()
      {
        public void run()
        {
          PurchaseFragment.this.onAddInstrumentResult(paramIntent);
        }
      });
    }
    while (true)
    {
      return;
      if ((paramInt1 == 30) && (paramInt2 == -1))
      {
        this.mCheckoutPurchase.setDevicePinAuthenticated();
        FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, "pinLock.purchase.complete?doc=" + this.mPurchaseDoc.getDocId());
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = "cidi";
        arrayOfObject[1] = this.mPurchaseDoc.getDocId();
        localFinskyEventLog.logTag("pinLock.purchase.complete", arrayOfObject);
        localHandler.post(new Runnable()
        {
          public void run()
          {
            PurchaseFragment.this.continueCheckoutSizeCheck();
          }
        });
      }
      else if (paramInt1 == 32)
      {
        if (paramInt2 == -1)
          this.mCheckoutPurchase.prepare();
        else
          localHandler.post(new Runnable()
          {
            public void run()
            {
              PurchaseFragment.this.finishWithError(PurchaseFragment.this.mCheckoutPurchase.getError());
            }
          });
      }
      else if (paramInt1 == 33)
      {
        localHandler.post(new Runnable()
        {
          public void run()
          {
            PurchaseFragment.this.onUpdateAddressResult(paramIntent);
          }
        });
      }
      else if (paramInt1 == 34)
      {
        CheckoutPurchase.State localState = this.mCheckoutPurchase.getState();
        if (localState == CheckoutPurchase.State.PREPARE_CHALLENGE_REQUIRED)
        {
          if (paramInt2 == 0)
          {
            localHandler.post(new Runnable()
            {
              public void run()
              {
                PurchaseFragment.access$3202(PurchaseFragment.this, false);
                PurchaseFragment.this.finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.UNKNOWN, 0, null));
              }
            });
          }
          else
          {
            this.mChallenging = false;
            answerChallenge(paramIntent);
            this.mCheckoutPurchase.prepare();
          }
        }
        else if (localState == CheckoutPurchase.State.COMPLETE_CHALLENGE_REQUIRED)
        {
          this.mChallenging = false;
          if (paramInt2 == 0)
          {
            finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.UNKNOWN, 0, null));
          }
          else
          {
            answerChallenge(paramIntent);
            completeCheckoutPurchase();
          }
        }
      }
      else
      {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
      }
    }
  }

  public void onAddInstrument(CommonDevice.Instrument paramInstrument, boolean paramBoolean)
  {
    this.mSelectedInstrument = null;
    this.mSelectedEligibleInstrument = paramInstrument;
    updateButtons();
    if (paramBoolean)
      showAddInstrumentScreen(paramInstrument);
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    ((CheckoutPurchase.Tos)paramCompoundButton.getTag()).setAcceptance(paramBoolean);
    updateUiFromInstrument();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    this.mViewStates = new Bundle();
    saveViewStates(this.mViewStates);
    if (this.mSummaryViewBinder != null)
    {
      this.mSummaryViewBinder.onDestroyView();
      this.mSummaryViewBinder = null;
    }
    if (this.mDfeDetails != null)
      this.mDfeDetails.unregisterAll();
    if (this.mInnerDetails != null)
      this.mInnerDetails.unregisterAll();
    this.mPurchaseDocumentDetailsViewBinder.onDestroyView();
    this.mPurchaseDocumentDetailsViewBinder = null;
    this.mInstrumentAndPriceViewBinder = null;
    this.mExtraDetailsScroller.setOnScrollListener(null);
    this.mExtraDetailsScroller = null;
    this.mExtraDetailsContainer = null;
    this.mAcquireButton = null;
    this.mLeadingStrip = null;
    this.mDetailsPanel = null;
    this.mAccountPanel = null;
    this.mAccountPayWithView = null;
    this.mAccountNameView = null;
    this.mAccountSeparatorWrapper = null;
    this.mAccountSeparator = null;
    this.mPurchaseRow = null;
    this.mPurchaseRowFooter = null;
    this.mFooterContainer = null;
  }

  public void onDownloadOk(boolean paramBoolean)
  {
    String str = this.mPurchaseDoc.getAppDetails().getPackageName();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = str;
    arrayOfObject[1] = Boolean.valueOf(paramBoolean);
    FinskyLog.d("Will download %s using wifi only = %b", arrayOfObject);
    if (!paramBoolean)
      FinskyApp.get().getInstaller().setMobileDataAllowed(str);
    continueCheckout();
  }

  public void onDownloadWifi()
  {
    Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
    getActivity().startActivity(localIntent);
  }

  protected void onInitViewBinders()
  {
    if (getArguments().containsKey("account"))
    {
      this.mAccount = ((Account)getArguments().getParcelable("account"));
      this.mAccountQualifiedDfe = FinskyApp.get().getDfeApi(this.mAccount.name);
      ViewGroup localViewGroup = this.mDataView;
      this.mInstrumentAndPriceViewBinder = new PurchaseInstrumentAndPriceViewBinder(this);
      this.mPurchaseDocumentDetailsViewBinder = new PurchaseDocumentDetailsViewBinder();
      this.mDynamicButtonContainer = getResources().getBoolean(2131296256);
      this.mExtraDetailsScroller = ((ObservableScrollView)localViewGroup.findViewById(2131231150));
      this.mExtraDetailsContainer = ((ViewGroup)localViewGroup.findViewById(2131231151));
      this.mFooterContainer = ((ViewGroup)localViewGroup.findViewById(2131231160));
      this.mDetailsPanel = ((ViewGroup)localViewGroup.findViewById(2131230881));
      this.mLeadingStrip = localViewGroup.findViewById(2131230879);
      this.mAccountPanel = ((AccessibleLinearLayout)localViewGroup.findViewById(2131231164));
      this.mAccountPayWithView = this.mAccountPanel.findViewById(2131231165);
      this.mAccountNameView = ((TextView)this.mAccountPanel.findViewById(2131230995));
      this.mAccountSeparatorWrapper = localViewGroup.findViewById(2131231166);
      this.mAccountSeparator = localViewGroup.findViewById(2131231167);
      this.mPurchaseRowFooter = localViewGroup.findViewById(2131231158);
      this.mAcquireButton = ((Button)this.mDataView.findViewById(2131231154));
      this.mAddInstrumentButton = ((Button)this.mDataView.findViewById(2131231155));
      this.mSelectInstrumentFamilyButton = ((Button)this.mDataView.findViewById(2131231156));
      this.mWalletByline = ((TextView)this.mDataView.findViewById(2131230797));
      if (!this.mDynamicButtonContainer)
      {
        this.mPurchaseRow = localViewGroup.findViewById(2131231153);
        this.mExtraDetailsScroller.setOnScrollListener(new ObservableScrollView.ScrollListener()
        {
          public void onScroll(int paramAnonymousInt1, int paramAnonymousInt2)
          {
            PurchaseContentLayout localPurchaseContentLayout = (PurchaseContentLayout)PurchaseFragment.this.mExtraDetailsContainer;
            int i = PurchaseFragment.this.mExtraDetailsContainer.findViewById(2131231153).getTop();
            int j = localPurchaseContentLayout.getAcquireRowTop();
            if (i != j)
              PurchaseFragment.this.mPurchaseRow.offsetTopAndBottom(j - i);
            int k = PurchaseFragment.this.mExtraDetailsContainer.findViewById(2131231158).getTop();
            int m = localPurchaseContentLayout.getAcquireRowFooterTop();
            if (k != m)
              PurchaseFragment.this.mPurchaseRowFooter.offsetTopAndBottom(m - k);
          }
        });
      }
      if (!this.mIsRetainedInstance)
        break label419;
    }
    label419: for (Bundle localBundle = this.mViewStates; ; localBundle = this.mSavedInstanceState)
    {
      this.mInstrumentAndPriceViewBinder.init(this, this.mInstrumentFactory, localBundle);
      this.mPurchaseDocumentDetailsViewBinder.init(this.mContext, getActivity().getPackageManager(), localBundle);
      this.mInstrumentAndPriceViewBinder.bind(this.mExtraDetailsContainer, null);
      return;
      this.mAccount = this.mDfeApi.getApiContext().getAccount();
      this.mAccountQualifiedDfe = this.mDfeApi;
      break;
    }
  }

  public void onInstrumentFamilySelected(CommonDevice.Instrument paramInstrument)
  {
    showAddInstrumentScreen(paramInstrument);
  }

  public void onInstrumentSelected(Instrument paramInstrument)
  {
    if (paramInstrument != null)
      this.mLastSelectedInstrumentId = paramInstrument.getInstrumentId();
    this.mSelectedInstrument = paramInstrument;
    this.mSelectedEligibleInstrument = null;
    updateUiFromInstrument();
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Unknown alert id %d", arrayOfObject);
    case 2:
    case 1:
    case 3:
    case 5:
    case 4:
    }
    while (true)
    {
      return;
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.SUBSCRIPTION_ALREADY_OWNED, 3, null));
      continue;
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.DOCUMENT_ALREADY_OWNED, 3, null));
      continue;
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.APP_ALREADY_INSTALLED, 3, null));
      continue;
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.APP_ALREADY_INSTALLED_OTHER_USER, 3, null));
      continue;
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.DOCUMENT_UNAVAILABLE, 4, null));
    }
  }

  public void onNothingSelected()
  {
    this.mSelectedInstrument = null;
    this.mSelectedEligibleInstrument = null;
    updateUiFromInstrument();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    String str = paramBundle.getString("docId_to_purchase");
    switch (paramInt)
    {
    default:
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      FinskyLog.wtf("Unknown alert id %d", arrayOfObject);
    case 2:
    case 1:
    }
    while (true)
    {
      return;
      openDocument(DocUtils.getPackageNameForSubscription(str));
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.SUBSCRIPTION_ALREADY_OWNED, 3, null));
      continue;
      openDocument(str);
      finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.DOCUMENT_ALREADY_OWNED, 3, null));
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mViewStates != null)
      paramBundle.putAll(this.mViewStates);
    if (this.mDoc != null)
    {
      paramBundle.putParcelable("doc", this.mDoc);
      if (this.mCheckoutPurchase != null)
      {
        Bundle localBundle = new Bundle();
        this.mCheckoutPurchase.saveState(localBundle);
        paramBundle.putBundle("checkout_purchase", localBundle);
      }
    }
    if (this.mSelectedInstrument != null)
      paramBundle.putString("selected_instrument_id", this.mSelectedInstrument.getInstrumentId());
    paramBundle.putString("last_selected_instrument_id", this.mLastSelectedInstrumentId);
    paramBundle.putParcelable("suppress_error", this.mSuppressError);
    paramBundle.putBoolean("add_instrument_screen_shown", this.mAddInstrumentScreenShown);
    paramBundle.putBoolean("finished_with_success", this.mFinishedWithSuccess);
    paramBundle.putParcelable("finished_with_error", this.mFinishedWithError);
    if (this.mSelectedEligibleInstrument != null)
      paramBundle.putParcelable("selected_eligible_instrument", ParcelableProto.forProto(this.mSelectedEligibleInstrument));
    paramBundle.putBoolean("challenging", this.mChallenging);
  }

  public void onStart()
  {
    super.onStart();
    if (this.mCheckoutPurchase != null)
    {
      this.mCheckoutPurchase.attach(this);
      restoreSelectedInstrumentAndBillingFlow();
    }
    this.mIsRetainedInstance = true;
  }

  public void onStateChange(CheckoutPurchase paramCheckoutPurchase, CheckoutPurchase.State paramState1, CheckoutPurchase.State paramState2)
  {
    if ((this.mFinishedWithSuccess) || (this.mFinishedWithError != null))
    {
      this.mInstrumentAndPriceViewBinder.switchToLoading(2131165602);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramState2.name();
      FinskyLog.d("Ignoring state change to %s, already finished.", arrayOfObject);
    }
    label46: CheckoutPurchase.Error localError;
    while (true)
    {
      return;
      if (!isAdded())
        break label209;
      if (paramState2 != CheckoutPurchase.State.ERROR)
        break label248;
      localError = this.mCheckoutPurchase.getError();
      VolleyError localVolleyError = this.mCheckoutPurchase.getVolleyError();
      if (localError.equals(this.mSuppressError))
      {
        FinskyLog.d("Already handled %s", new Object[] { localError });
      }
      else
      {
        this.mSuppressError = localError;
        if (!(localVolleyError instanceof AuthFailureError))
          break;
        Intent localIntent = ((AuthFailureError)localVolleyError).getResolutionIntent();
        if (localIntent == null)
          break;
        FinskyLog.d("Starting resolution intent.", new Object[0]);
        startActivityForResult(localIntent, 32);
      }
    }
    if (paramState1 == CheckoutPurchase.State.COMPLETING)
      if (localError.type == CheckoutPurchase.ErrorType.MIN_ADDRESS_PURCHASE_LIMIT_EXCEEDED)
      {
        CommonDevice.Instrument localInstrument = this.mCheckoutPurchase.getRejectedInstrument();
        showUpdateInstrumentScreen(this.mSelectedInstrument, localInstrument);
      }
    while (true)
      label196: if (paramState2 != CheckoutPurchase.State.ERROR)
      {
        this.mSuppressError = null;
        break label46;
        label209: break label46;
        this.mCheckoutPurchase.prepare();
        ErrorDialog.show(getActivity().getSupportFragmentManager(), null, localError.message, false);
        continue;
        finishWithError(localError);
        continue;
        label248: if (paramState2 == CheckoutPurchase.State.PREPARED)
        {
          if (paramState1 == CheckoutPurchase.State.PREPARING)
          {
            String str = "viewPurchase?doc=" + this.mPurchaseDoc.getDocId() + "&" + buildViewAnalyticsExtra();
            FinskyApp.get().getAnalytics().logPageView(this.mReferrer, this.mReferrerCookie, str);
            logViewPurchaseEvent();
          }
          this.mInstrumentAndPriceViewBinder.bind(this.mExtraDetailsContainer, this.mCheckoutPurchase);
          updateSummaryAndFops();
        }
        else if (paramState2 == CheckoutPurchase.State.COMPLETING_POLLING_STATUS)
        {
          this.mInstrumentAndPriceViewBinder.switchToLoading(2131165602);
        }
        else if (paramState2 == CheckoutPurchase.State.COMPLETED)
        {
          finishWithSuccess();
        }
        else
        {
          if ((paramState2 == CheckoutPurchase.State.PREPARE_CHALLENGE_REQUIRED) || (paramState2 == CheckoutPurchase.State.COMPLETE_CHALLENGE_REQUIRED))
          {
            if (this.mChallenging)
              break;
            this.mChallenging = true;
            if (this.mCheckoutPurchase.getChallenge().hasAddressChallenge())
            {
              Bundle localBundle1 = new Bundle();
              localBundle1.putString("authAccount", this.mAccount.name);
              startActivityForResult(AddressChallengeActivity.getIntent(this.mPurchaseDoc.getBackend(), this.mCheckoutPurchase.getChallenge(), localBundle1), 34);
              continue;
            }
            if (this.mCheckoutPurchase.getChallenge().hasAuthenticationChallenge())
            {
              Bundle localBundle2 = new Bundle();
              localBundle2.putString("authAccount", this.mAccount.name);
              startActivityForResult(AuthenticationChallengeActivity.getIntent(this.mPurchaseDoc.getBackend(), this.mCheckoutPurchase.getChallenge(), localBundle2), 34);
              continue;
            }
            FinskyLog.wtf("Unsupported challenge", new Object[0]);
            finishWithError(new CheckoutPurchase.Error(CheckoutPurchase.ErrorType.UNKNOWN, -1, "Unsupported challenge"));
            continue;
          }
          if ((paramState2 == CheckoutPurchase.State.PREPARING) || (paramState2 == CheckoutPurchase.State.COMPLETING))
            if (paramState2 != CheckoutPurchase.State.PREPARING)
              break label617;
        }
      }
    label617: for (int i = 2131165283; ; i = 2131165284)
    {
      this.mInstrumentAndPriceViewBinder.switchToLoading(i);
      this.mAcquireButton.setEnabled(false);
      break label196;
      break;
    }
  }

  public void onStop()
  {
    if (this.mCheckoutPurchase != null)
      this.mCheckoutPurchase.detach();
    super.onStop();
  }

  public void persistFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    getFragmentManager().putFragment(paramBundle, paramString, paramFragment);
  }

  public void rebindActionBar()
  {
    if (this.mDoc != null)
      this.mPageFragmentHost.updateCurrentBackendId(this.mDoc.getBackend());
  }

  public void rebindViews()
  {
    LayoutInflater localLayoutInflater = LayoutInflater.from(this.mDetailsPanel.getContext());
    int i;
    if (this.mPurchaseDoc != null)
    {
      i = this.mPurchaseDoc.getBackend();
      if (!this.mDynamicButtonContainer)
        break label389;
      this.mDataView.findViewById(2131230909).getLayoutParams().width = CorpusResourceUtils.getLargeDetailsIconWidth(this.mContext, this.mPurchaseDoc.getDocumentType());
      this.mAcquireButton.setOnClickListener(new BuyButtonClickListener(null));
      this.mAddInstrumentButton.setOnClickListener(new AddInstrumentButtonListener(null));
      this.mSelectInstrumentFamilyButton.setOnClickListener(new SelectInstrumentFamilyClickListener(null));
      updateButtons();
      AccountLibrary localAccountLibrary = FinskyApp.get().getLibraries().getAccountLibrary(this.mAccount);
      if (LibraryUtils.isOwned(this.mPurchaseDoc, localAccountLibrary))
        switch (this.mPurchaseDoc.getDocumentType())
        {
        default:
          showAlreadyOwnedOrInstalledDialog(2131165516, 2131165519, 2131165599, 1, "already_owned_document");
        case 1:
        case 15:
        }
    }
    while (true)
    {
      if (this.mPurchaseDoc.getDocumentType() == 1)
        checkForMultiUserCertificateConflict();
      if (this.mSummaryViewBinder == null)
      {
        this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(getToc(), this.mPurchaseDoc.getBackend(), this.mAccount);
        this.mSummaryViewBinder.hideDynamicFeatures();
        if ((this.mOfferType == 7) || (this.mOfferType == 4))
          this.mSummaryViewBinder.setBadgeOverride(2130837601);
        this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this, false, this.mReferrer, this.mExternalReferrer, this.mContinueUrl, this.mIsDirectPurchase);
      }
      DetailsSummaryViewBinder localDetailsSummaryViewBinder = this.mSummaryViewBinder;
      Document localDocument = this.mPurchaseDoc;
      View[] arrayOfView = new View[1];
      arrayOfView[0] = this.mDetailsPanel;
      localDetailsSummaryViewBinder.bind(localDocument, false, arrayOfView);
      rebindActionBar();
      int j = CorpusResourceUtils.getBackendHintColor(this.mContext, this.mPurchaseDoc.getBackend());
      this.mDataView.findViewById(2131230879).setBackgroundColor(j);
      this.mPurchaseRowFooter.setBackgroundColor(j);
      return;
      label389: this.mDetailsPanel.removeAllViews();
      localLayoutInflater.inflate(CorpusResourceUtils.getCorpusDetailsLayoutResource(i), this.mDetailsPanel, true);
      break;
      checkForInstallationState();
      continue;
      showAlreadyOwnedOrInstalledDialog(2131165515, 2131165519, 2131165599, 2, "already_owned_subscription");
    }
  }

  protected void requestData()
  {
  }

  public Fragment restoreFragment(Bundle paramBundle, String paramString)
  {
    return getFragmentManager().getFragment(paramBundle, paramString);
  }

  public void setPurchaseFragmentListener(PurchaseFragmentListener paramPurchaseFragmentListener)
  {
    this.mListener = paramPurchaseFragmentListener;
  }

  public void showDialogFragment(DialogFragment paramDialogFragment, String paramString)
  {
    FragmentTransaction localFragmentTransaction = getFragmentManager().beginTransaction();
    Fragment localFragment = getFragmentManager().findFragmentByTag(paramString);
    if (localFragment != null)
      localFragmentTransaction.remove(localFragment);
    localFragmentTransaction.addToBackStack(null);
    paramDialogFragment.show(getFragmentManager(), paramString);
  }

  public void showFragment(Fragment paramFragment, int paramInt, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("This should be handled by PurchaseFlowActivity");
  }

  public void showProgress(int paramInt)
  {
  }

  private class AddInstrumentButtonListener
    implements View.OnClickListener
  {
    private AddInstrumentButtonListener()
    {
    }

    public void onClick(View paramView)
    {
      if (PurchaseFragment.this.mSelectedEligibleInstrument == null)
        FinskyLog.wtf("No add instrument item selected.", new Object[0]);
      PurchaseFragment.this.showAddInstrumentScreen(PurchaseFragment.this.mSelectedEligibleInstrument);
    }
  }

  private class BuyButtonClickListener
    implements View.OnClickListener
  {
    private BuyButtonClickListener()
    {
    }

    public void onClick(View paramView)
    {
      PurchaseFragment.this.beginCheckout();
    }
  }

  public static abstract interface PurchaseFragmentListener
  {
    public abstract void onFailure(CheckoutPurchase.Error paramError);

    public abstract void onSuccess(Bundle paramBundle);
  }

  private static class PurchaseStatusListener
    implements CheckoutPurchase.PurchaseStatusListener
  {
    private final Account mAccount;
    private final String mContinueUrl;
    private final Document mDoc;
    private final String mExternalReferrer;
    private final String mReferrerCookie;

    private PurchaseStatusListener(Account paramAccount, Document paramDocument, String paramString1, String paramString2, String paramString3)
    {
      this.mAccount = paramAccount;
      this.mDoc = paramDocument;
      this.mExternalReferrer = paramString1;
      this.mReferrerCookie = paramString2;
      this.mContinueUrl = paramString3;
    }

    public void onPurchaseStatusResponse(Buy.PurchaseStatusResponse paramPurchaseStatusResponse, String paramString)
    {
      PurchaseInitiator.processPurchaseStatusResponse(this.mAccount, this.mDoc, this.mExternalReferrer, this.mReferrerCookie, this.mContinueUrl, paramPurchaseStatusResponse, true, paramString);
    }
  }

  private class SelectInstrumentFamilyClickListener
    implements View.OnClickListener
  {
    private SelectInstrumentFamilyClickListener()
    {
    }

    public void onClick(View paramView)
    {
      if (PurchaseFragment.this.mCheckoutPurchase.getEligibleInstruments().size() == 1)
      {
        CommonDevice.Instrument localInstrument = (CommonDevice.Instrument)PurchaseFragment.this.mCheckoutPurchase.getEligibleInstruments().get(0);
        PurchaseFragment.this.showAddInstrumentScreen(localInstrument);
      }
      while (true)
      {
        return;
        FragmentManager localFragmentManager = PurchaseFragment.this.getFragmentManager();
        if (localFragmentManager.findFragmentByTag("select_instr_family_dialog") == null)
        {
          SelectInstrumentFamilyDialog localSelectInstrumentFamilyDialog = SelectInstrumentFamilyDialog.newInstance(PurchaseFragment.this.mCheckoutPurchase.getEligibleInstruments(), PurchaseFragment.this.mInstrumentFactory, 2131165277, 0);
          localSelectInstrumentFamilyDialog.setTargetFragment(PurchaseFragment.this, 0);
          localSelectInstrumentFamilyDialog.show(localFragmentManager, "select_instr_family_dialog");
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.PurchaseFragment
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.creditcard;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.layout.BillingAddress;
import com.google.android.finsky.layout.BillingAddress.BillingCountryChangeListener;
import com.google.android.finsky.layout.BillingAddress.InitializationStateListener;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.remoting.protos.CommonDevice.CreditCardInstrument;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AddCreditCardFragment extends Fragment
  implements View.OnClickListener, SimpleAlertDialog.Listener
{
  private BillingAddress mBillingAddress;
  private Button mCancelButton;
  private TextView mCcCvcField;
  private TextView mCcExpMonthField;
  private TextView mCcExpYearField;
  private EditText mCcNumberField;
  private List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> mCountries;
  private AddCreditCardResultListener mListener;
  private ViewGroup mMainView;
  private Button mSaveButton;
  private Bundle mSavedInstanceState;
  private VendingProtos.PurchaseMetadataResponseProto.Countries.Country mSelectedCountry;
  private BillingUtils.CreateInstrumentUiMode mUiMode;

  private void clearErrorMessages()
  {
    this.mBillingAddress.clearErrorMessage();
    this.mCcNumberField.setError(null);
    this.mCcExpMonthField.setError(null);
    this.mCcExpYearField.setError(null);
    this.mCcCvcField.setError(null);
  }

  private void creditCardInputErrorsToInputValidationErrors(Set<CreditCardValidator.InputField> paramSet, List<ChallengeProtos.InputValidationError> paramList)
  {
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      CreditCardValidator.InputField localInputField = (CreditCardValidator.InputField)localIterator.next();
      switch (6.$SwitchMap$com$google$android$finsky$billing$creditcard$CreditCardValidator$InputField[localInputField.ordinal()])
      {
      default:
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localInputField.name();
        FinskyLog.w("Unhandled credit card input field error for: %s", arrayOfObject);
        break;
      case 1:
        paramList.add(makeInputValidationError(0, getString(2131165249)));
        break;
      case 2:
        paramList.add(makeInputValidationError(1, getString(2131165252)));
        break;
      case 3:
        paramList.add(makeInputValidationError(3, getString(2131165250)));
        break;
      case 4:
        paramList.add(makeInputValidationError(2, getString(2131165251)));
      }
    }
  }

  private TextView displayError(ChallengeProtos.InputValidationError paramInputValidationError)
  {
    String str = paramInputValidationError.getErrorMessage();
    Object localObject = null;
    switch (paramInputValidationError.getInputField())
    {
    default:
      if (localObject != null)
        ((TextView)localObject).setError(str);
      break;
    case 0:
    case 3:
    case 2:
    case 1:
    }
    while (true)
    {
      return localObject;
      localObject = this.mCcNumberField;
      break;
      localObject = this.mCcExpMonthField;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mCcExpYearField;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mCcCvcField;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mBillingAddress.displayError(paramInputValidationError);
    }
  }

  private CommonDevice.BillingAddressSpec getAddressSpec(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry)
  {
    if (paramCountry.getAllowsReducedBillingAddress());
    for (CommonDevice.BillingAddressSpec localBillingAddressSpec = new CommonDevice.BillingAddressSpec().setBillingAddressType(3); ; localBillingAddressSpec = new CommonDevice.BillingAddressSpec().setBillingAddressType(1))
      return localBillingAddressSpec;
  }

  private InstrumentAndCredentials getCreditCardOrShowErrors()
  {
    String str1 = CreditCardInstrument.CreditCardNumberFilter.getNumbers(this.mCcNumberField.getText());
    String str2 = this.mCcExpMonthField.getText().toString();
    String str3 = this.mCcExpYearField.getText().toString();
    String str4 = this.mCcCvcField.getText().toString();
    ArrayList localArrayList = Lists.newArrayList();
    HashSet localHashSet = Sets.newHashSet();
    CreditCardType localCreditCardType = CreditCardValidator.validate(str1, str4, str2, str3, 2000, localHashSet);
    creditCardInputErrorsToInputValidationErrors(localHashSet, localArrayList);
    localArrayList.addAll(this.mBillingAddress.getAddressValidationErrors());
    displayErrors(localArrayList);
    CommonDevice.Instrument localInstrument;
    if (localArrayList.size() == 0)
    {
      BillingAddress.Address localAddress = this.mBillingAddress.getAddress();
      CommonDevice.CreditCardInstrument localCreditCardInstrument = new CommonDevice.CreditCardInstrument();
      localCreditCardInstrument.setExpirationMonth(Integer.parseInt(str2));
      localCreditCardInstrument.setExpirationYear(2000 + Integer.parseInt(str3));
      localCreditCardInstrument.setLastDigits(str1.substring(-4 + str1.length()));
      localCreditCardInstrument.setType(localCreditCardType.protobufType);
      localInstrument = new CommonDevice.Instrument();
      localInstrument.setBillingAddress(localAddress);
      localInstrument.setCreditCard(localCreditCardInstrument);
    }
    for (InstrumentAndCredentials localInstrumentAndCredentials = new InstrumentAndCredentials(str1, str4, localInstrument, null); ; localInstrumentAndCredentials = null)
      return localInstrumentAndCredentials;
  }

  private void loadBillingCountries()
  {
    if (this.mListener != null)
      this.mListener.onInitializing();
    new GetBillingCountriesAction().run(getArguments().getString("authAccount"), new Runnable()
    {
      public void run()
      {
        AddCreditCardFragment.this.onBillingCountriesLoaded();
      }
    });
  }

  private static ChallengeProtos.InputValidationError makeInputValidationError(int paramInt, String paramString)
  {
    ChallengeProtos.InputValidationError localInputValidationError = new ChallengeProtos.InputValidationError();
    localInputValidationError.setInputField(paramInt);
    localInputValidationError.setErrorMessage(paramString);
    return localInputValidationError;
  }

  public static AddCreditCardFragment newInstance(BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode, String paramString1, String paramString2, boolean paramBoolean)
  {
    AddCreditCardFragment localAddCreditCardFragment = new AddCreditCardFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("ui_mode", paramCreateInstrumentUiMode.name());
    localBundle.putString("cardholder_name", paramString2);
    localBundle.putString("authAccount", paramString1);
    localBundle.putBoolean("all_corpora_enabled", paramBoolean);
    localAddCreditCardFragment.setArguments(localBundle);
    return localAddCreditCardFragment;
  }

  private void onBillingCountriesLoaded()
  {
    if (!isAdded());
    while (true)
    {
      return;
      this.mCountries = BillingLocator.getBillingCountries();
      if ((this.mCountries != null) && (this.mCountries.size() > 0))
      {
        if (this.mListener != null)
          this.mListener.onInitialized();
        this.mSaveButton.setEnabled(true);
        setupWidgets(this.mSavedInstanceState);
      }
      else
      {
        FinskyLog.d("BillingCountries not loaded.", new Object[0]);
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165293, 2131165434, 2131165273);
        localSimpleAlertDialog.setCallback(this, 1, null);
        localSimpleAlertDialog.show(getFragmentManager(), "error");
      }
    }
  }

  private void setupExpirationDateEntry(View paramView)
  {
    this.mCcExpMonthField = ((EditText)paramView.findViewById(2131230758));
    this.mCcExpYearField = ((EditText)paramView.findViewById(2131230759));
    TextView localTextView = (TextView)paramView.findViewById(2131230760);
    this.mCcExpMonthField.setHint(2131165232);
    this.mCcExpYearField.setHint(2131165233);
    localTextView.setText(2131165234);
    AutoAdvancer.setupAutoAdvancer(this.mCcExpMonthField, 2);
    AutoAdvancer.setupAutoAdvancer(this.mCcExpYearField, 2);
  }

  private void setupWidgets(Bundle paramBundle)
  {
    if (this.mSelectedCountry == null)
      this.mSelectedCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(getActivity(), null), this.mCountries);
    this.mCcNumberField = ((EditText)this.mMainView.findViewById(2131230757));
    EditText localEditText = this.mCcNumberField;
    InputFilter[] arrayOfInputFilter1 = new InputFilter[1];
    arrayOfInputFilter1[0] = new CreditCardInstrument.CreditCardNumberFilter();
    localEditText.setFilters(arrayOfInputFilter1);
    this.mCcNumberField.setKeyListener(DigitsKeyListener.getInstance("0123456789 -"));
    if (paramBundle == null)
    {
      this.mCcNumberField.requestFocus();
      getActivity().getWindow().setSoftInputMode(5);
    }
    AutoAdvancer.setupAutoAdvancer(this.mCcNumberField, 3 + CreditCardType.getMaxNumberLength());
    this.mCcCvcField = ((EditText)this.mMainView.findViewById(2131230761));
    int i = CreditCardType.getMaxCvcLength();
    InputFilter[] arrayOfInputFilter2 = new InputFilter[1];
    arrayOfInputFilter2[0] = new InputFilter.LengthFilter(i);
    this.mCcCvcField.setFilters(arrayOfInputFilter2);
    AutoAdvancer.setupAutoAdvancer(this.mCcCvcField, i);
    setupExpirationDateEntry(this.mMainView);
    this.mBillingAddress.setDefaultName(getArguments().getString("cardholder_name"));
    this.mBillingAddress.setNameInputHint(2131165238);
    this.mBillingAddress.setBillingCountries(BillingLocator.getBillingCountries());
    this.mBillingAddress.setAddressSpec(this.mSelectedCountry, getAddressSpec(this.mSelectedCountry));
    this.mBillingAddress.setPhoneNumber(BillingUtils.getLine1Number(getActivity()));
    if (paramBundle != null)
      this.mBillingAddress.restoreInstanceState(paramBundle);
    ((ImageView)this.mMainView.findViewById(2131230763)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SimpleAlertDialog.newInstanceWithLayout(2130968588, 2131165599, -1).show(AddCreditCardFragment.this.getFragmentManager(), "cvc_popup");
      }
    });
    ((TextView)this.mMainView.findViewById(2131230765)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstanceWithLayout(2130968596, 2131165599, -1);
        Bundle localBundle = new Bundle();
        localBundle.putString("url_key", BillingUtils.replaceLanguageAndRegion((String)G.checkoutPrivacyPolicyUrl.get()));
        localSimpleAlertDialog.setViewConfiguration(localBundle);
        localSimpleAlertDialog.show(AddCreditCardFragment.this.getFragmentManager(), "privacy_policy");
      }
    });
  }

  private boolean supportsBooksAndMovies()
  {
    return "US".equals(BillingUtils.getSimCountry(getActivity()));
  }

  public void displayErrors(List<ChallengeProtos.InputValidationError> paramList)
  {
    clearErrorMessages();
    ArrayList localArrayList = Lists.newArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      TextView localTextView2 = displayError((ChallengeProtos.InputValidationError)localIterator.next());
      if (localTextView2 != null)
        localArrayList.add(localTextView2);
    }
    TextView localTextView1 = (TextView)BillingUtils.getTopMostView(this.mMainView, localArrayList);
    if (localTextView1 != null)
      localTextView1.requestFocus();
  }

  public void enableUi(boolean paramBoolean)
  {
    if (this.mBillingAddress != null)
    {
      this.mBillingAddress.setEnabled(paramBoolean);
      this.mCcNumberField.setEnabled(paramBoolean);
      this.mCcCvcField.setEnabled(paramBoolean);
      this.mCcExpMonthField.setEnabled(paramBoolean);
      this.mCcExpYearField.setEnabled(paramBoolean);
      this.mSaveButton.setEnabled(paramBoolean);
      this.mCancelButton.setEnabled(paramBoolean);
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
      InstrumentAndCredentials localInstrumentAndCredentials = getCreditCardOrShowErrors();
      if (localInstrumentAndCredentials != null)
      {
        this.mListener.onAddCreditCardResult(AddCreditCardFragment.AddCreditCardResultListener.Result.SUCCESS, localInstrumentAndCredentials.creditCardNumber, localInstrumentAndCredentials.cvc, localInstrumentAndCredentials.instrument);
        continue;
        this.mListener.onAddCreditCardResult(AddCreditCardFragment.AddCreditCardResultListener.Result.CANCELED, null, null, null);
      }
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Object localObject;
    if (paramViewGroup == null)
    {
      localObject = null;
      return localObject;
    }
    String str = getArguments().getString("ui_mode");
    BillingUtils.CreateInstrumentUiMode localCreateInstrumentUiMode;
    label34: TextView localTextView;
    View localView;
    if (str != null)
    {
      localCreateInstrumentUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(str);
      this.mUiMode = localCreateInstrumentUiMode;
      this.mSavedInstanceState = paramBundle;
      this.mMainView = ((ViewGroup)paramLayoutInflater.inflate(2130968590, paramViewGroup, false));
      this.mBillingAddress = ((BillingAddress)this.mMainView.findViewById(2131230764));
      this.mBillingAddress.setNameInputHint(2131165238);
      this.mBillingAddress.setBillingCountryChangeListener(new BillingAddress.BillingCountryChangeListener()
      {
        public void onBillingCountryChanged(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramAnonymousCountry)
        {
          AddCreditCardFragment.this.mBillingAddress.setAddressSpec(paramAnonymousCountry, AddCreditCardFragment.this.getAddressSpec(paramAnonymousCountry));
        }
      });
      this.mBillingAddress.setInitializationStateListener(new BillingAddress.InitializationStateListener()
      {
        public void onInitialized()
        {
          if (AddCreditCardFragment.this.mListener != null)
            AddCreditCardFragment.this.mListener.onInitialized();
        }

        public void onInitializing()
        {
          if (AddCreditCardFragment.this.mListener != null)
            AddCreditCardFragment.this.mListener.onInitializing();
        }
      });
      this.mSaveButton = ((Button)this.mMainView.findViewById(2131230769));
      this.mSaveButton.setOnClickListener(this);
      this.mSaveButton.setEnabled(false);
      this.mSaveButton.setText(2131165258);
      localTextView = (TextView)this.mMainView.findViewById(2131230767);
      this.mCancelButton = ((Button)this.mMainView.findViewById(2131230770));
      this.mCancelButton.setOnClickListener(this);
      localView = this.mMainView.findViewById(2131230768);
    }
    switch (6.$SwitchMap$com$google$android$finsky$billing$BillingUtils$CreateInstrumentUiMode[this.mUiMode.ordinal()])
    {
    default:
    case 1:
    case 2:
      while (true)
      {
        loadBillingCountries();
        localObject = this.mMainView;
        break;
        localCreateInstrumentUiMode = BillingUtils.CreateInstrumentUiMode.INTERNAL;
        break label34;
        this.mCancelButton.setText(2131165273);
        localTextView.setText(2131165219);
        continue;
        this.mCancelButton.setText(2131165404);
        localTextView.setVisibility(8);
        localView.setVisibility(0);
      }
    case 3:
    }
    if (FinskyApp.get().isTablet())
    {
      if (supportsBooksAndMovies());
      for (i = 2131165222; ; i = 2131165224)
      {
        localTextView.setText(i);
        localView.setVisibility(8);
        this.mCancelButton.setText(2131165404);
        break;
      }
    }
    if (supportsBooksAndMovies());
    for (int i = 2131165221; ; i = 2131165223)
      break;
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    this.mListener.onAddCreditCardResult(AddCreditCardFragment.AddCreditCardResultListener.Result.CANCELED, null, null, null);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 1)
      loadBillingCountries();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mBillingAddress != null)
      this.mBillingAddress.saveInstanceState(paramBundle);
  }

  public void setOnResultListener(AddCreditCardResultListener paramAddCreditCardResultListener)
  {
    this.mListener = paramAddCreditCardResultListener;
  }

  public static abstract interface AddCreditCardResultListener
  {
    public abstract void onAddCreditCardResult(Result paramResult, String paramString1, String paramString2, CommonDevice.Instrument paramInstrument);

    public abstract void onInitialized();

    public abstract void onInitializing();

    public static enum Result
    {
      static
      {
        FAILURE = new Result("FAILURE", 1);
        CANCELED = new Result("CANCELED", 2);
        Result[] arrayOfResult = new Result[3];
        arrayOfResult[0] = SUCCESS;
        arrayOfResult[1] = FAILURE;
        arrayOfResult[2] = CANCELED;
      }
    }
  }

  private static class AutoAdvancer
    implements TextWatcher
  {
    private final int mMaxLength;
    private final TextView mTextView;

    public AutoAdvancer(TextView paramTextView, int paramInt)
    {
      paramTextView.addTextChangedListener(this);
      this.mTextView = paramTextView;
      this.mMaxLength = paramInt;
    }

    public static void setupAutoAdvancer(TextView paramTextView, int paramInt)
    {
      if (paramTextView.getTag() == null)
      {
        new AutoAdvancer(paramTextView, paramInt);
        paramTextView.setTag(Boolean.valueOf(true));
      }
    }

    public void afterTextChanged(Editable paramEditable)
    {
      if (paramEditable.length() >= this.mMaxLength)
        if (Build.VERSION.SDK_INT < 14)
          break label62;
      label62: for (View localView = this.mTextView.focusSearch(2); ; localView = this.mTextView.focusSearch(130))
      {
        if (localView != null)
          localView.requestFocus();
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            AddCreditCardFragment.AutoAdvancer.this.mTextView.removeTextChangedListener(AddCreditCardFragment.AutoAdvancer.this);
          }
        });
        return;
      }
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  }

  private class InstrumentAndCredentials
  {
    private String creditCardNumber;
    private String cvc;
    private CommonDevice.Instrument instrument;

    private InstrumentAndCredentials(String paramString1, String paramInstrument, CommonDevice.Instrument arg4)
    {
      this.creditCardNumber = paramString1;
      this.cvc = paramInstrument;
      Object localObject;
      this.instrument = localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.creditcard.AddCreditCardFragment
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.billing.challenge;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.GetBillingCountriesAction;
import com.google.android.finsky.layout.BillingAddress;
import com.google.android.finsky.layout.BillingAddress.BillingCountryChangeListener;
import com.google.android.finsky.layout.BillingAddress.InitializationStateListener;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.AddressChallenge;
import com.google.android.finsky.remoting.protos.ChallengeProtos.Country;
import com.google.android.finsky.remoting.protos.ChallengeProtos.FormCheckbox;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AddressChallengeFragment extends Fragment
  implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SimpleAlertDialog.Listener
{
  private CommonDevice.BillingAddressSpec mAddressSpec;
  private BillingAddress mBillingAddress;
  private Button mCancelButton;
  private ChallengeProtos.AddressChallenge mChallenge;
  private List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> mCountries;
  private AddressChallengeResultListener mListener;
  private ViewGroup mMainView;
  private Bundle mPreviousState;
  private Button mSaveButton;
  private Bundle mSavedInstanceState;

  private void clearErrorMessages()
  {
    this.mBillingAddress.clearErrorMessage();
  }

  private TextView displayError(ChallengeProtos.InputValidationError paramInputValidationError)
  {
    return this.mBillingAddress.displayError(paramInputValidationError);
  }

  private void displayErrors(List<ChallengeProtos.InputValidationError> paramList)
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

  private BillingAddress.Address getAddressOrShowErrors()
  {
    List localList = this.mBillingAddress.getAddressValidationErrors();
    displayErrors(localList);
    if (localList.size() == 0);
    for (BillingAddress.Address localAddress = this.mBillingAddress.getAddress(); ; localAddress = null)
      return localAddress;
  }

  private boolean[] getCheckboxState()
  {
    int i = this.mChallenge.getCheckboxCount();
    boolean[] arrayOfBoolean = new boolean[i];
    for (int j = 0; j < i; j++)
      arrayOfBoolean[j] = ((CheckBox)this.mMainView.findViewWithTag(this.mChallenge.getCheckbox(j))).isChecked();
    return arrayOfBoolean;
  }

  private void initializeCountriesFromChallenge()
  {
    this.mCountries = Lists.newArrayList(this.mChallenge.getSupportedCountryCount());
    Iterator localIterator = this.mChallenge.getSupportedCountryList().iterator();
    while (localIterator.hasNext())
    {
      ChallengeProtos.Country localCountry = (ChallengeProtos.Country)localIterator.next();
      VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry1 = new VendingProtos.PurchaseMetadataResponseProto.Countries.Country().setCountryCode(localCountry.getRegionCode()).setCountryName(localCountry.getDisplayName());
      this.mCountries.add(localCountry1);
    }
    syncContinueButton();
    setupWidgets(this.mSavedInstanceState);
  }

  private void loadBillingCountries()
  {
    if (this.mListener != null)
      this.mListener.onInitializing();
    new GetBillingCountriesAction().run(getArguments().getString("authAccount"), new Runnable()
    {
      public void run()
      {
        AddressChallengeFragment.this.onBillingCountriesLoaded();
      }
    });
  }

  public static AddressChallengeFragment newInstance(String paramString, ChallengeProtos.AddressChallenge paramAddressChallenge, Bundle paramBundle)
  {
    AddressChallengeFragment localAddressChallengeFragment = new AddressChallengeFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("authAccount", paramString);
    localBundle.putParcelable("address_challenge", ParcelableProto.forProto(paramAddressChallenge));
    localAddressChallengeFragment.setArguments(localBundle);
    localAddressChallengeFragment.mPreviousState = paramBundle;
    return localAddressChallengeFragment;
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
        syncContinueButton();
        setupWidgets(this.mSavedInstanceState);
      }
      else
      {
        FinskyLog.d("BillingCountries not loaded.", new Object[0]);
        SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165293, 2131165434, 2131165273);
        localSimpleAlertDialog.setCallback(this, 1, (Bundle)null);
        localSimpleAlertDialog.show(getFragmentManager(), "error");
      }
    }
  }

  private void saveMyState(Bundle paramBundle)
  {
    for (int i = 0; i < this.mChallenge.getCheckboxCount(); i++)
    {
      CheckBox localCheckBox = (CheckBox)this.mMainView.findViewWithTag(this.mChallenge.getCheckbox(i));
      paramBundle.putBoolean("checkbox_" + i, localCheckBox.isChecked());
    }
    if (this.mBillingAddress != null)
      this.mBillingAddress.saveInstanceState(paramBundle);
  }

  private void setupWidgets(Bundle paramBundle)
  {
    this.mBillingAddress.setBillingCountries(this.mCountries);
    if (paramBundle != null)
    {
      this.mBillingAddress.restoreInstanceState(paramBundle);
      return;
    }
    if ((this.mChallenge.hasAddress()) && (this.mChallenge.getAddress().hasPostalCountry()))
    {
      VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry2 = BillingUtils.findCountry(this.mChallenge.getAddress().getPostalCountry(), this.mCountries);
      this.mBillingAddress.setAddressSpec(localCountry2, this.mAddressSpec, this.mChallenge.getAddress());
    }
    while (true)
    {
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = AddressChallengeFragment.this.mChallenge.getErrorInputFieldList().iterator();
          while (localIterator.hasNext())
          {
            ChallengeProtos.InputValidationError localInputValidationError = (ChallengeProtos.InputValidationError)localIterator.next();
            AddressChallengeFragment.this.mBillingAddress.displayError(localInputValidationError);
          }
        }
      });
      break;
      VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry1 = BillingUtils.findCountry(BillingUtils.getDefaultCountry(getActivity(), null), this.mCountries);
      this.mBillingAddress.setAddressSpec(localCountry1, this.mAddressSpec);
    }
  }

  private void syncContinueButton()
  {
    boolean bool = true;
    int i = 0;
    if (i < this.mChallenge.getCheckboxCount())
    {
      ChallengeProtos.FormCheckbox localFormCheckbox = this.mChallenge.getCheckbox(i);
      CheckBox localCheckBox = (CheckBox)this.mMainView.findViewWithTag(localFormCheckbox);
      if ((bool) && ((!localFormCheckbox.getRequired()) || (localCheckBox.isChecked())));
      for (bool = true; ; bool = false)
      {
        i++;
        break;
      }
    }
    this.mSaveButton.setEnabled(bool);
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    syncContinueButton();
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
      BillingAddress.Address localAddress = getAddressOrShowErrors();
      if (localAddress != null)
      {
        this.mListener.onAddressChallengeResult(AddressChallengeFragment.AddressChallengeResultListener.Result.SUCCESS, localAddress, getCheckboxState());
        continue;
        this.mListener.onAddressChallengeResult(AddressChallengeFragment.AddressChallengeResultListener.Result.CANCELED, null, null);
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
    this.mSavedInstanceState = paramBundle;
    this.mMainView = ((ViewGroup)paramLayoutInflater.inflate(2130968595, paramViewGroup, false));
    this.mChallenge = ((ChallengeProtos.AddressChallenge)ParcelableProto.getProtoFromBundle(getArguments(), "address_challenge"));
    this.mAddressSpec = new CommonDevice.BillingAddressSpec();
    Iterator localIterator = this.mChallenge.getRequiredFieldList().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      this.mAddressSpec.addRequiredField(localInteger.intValue());
    }
    if ((this.mChallenge.hasErrorHtml()) && (paramBundle == null))
      ErrorDialog.show(getFragmentManager(), null, this.mChallenge.getErrorHtml(), false);
    TextView localTextView1 = (TextView)this.mMainView.findViewById(2131230785);
    TextView localTextView2;
    label226: ViewGroup localViewGroup;
    int i;
    int j;
    label264: CheckBox localCheckBox;
    if (this.mChallenge.hasTitle())
    {
      localTextView1.setText(this.mChallenge.getTitle());
      localTextView2 = (TextView)this.mMainView.findViewById(2131230786);
      if (!this.mChallenge.hasDescriptionHtml())
        break label377;
      localTextView2.setText(Html.fromHtml(this.mChallenge.getDescriptionHtml()));
      localTextView2.setMovementMethod(LinkMovementMethod.getInstance());
      LayoutInflater localLayoutInflater = LayoutInflater.from(getActivity());
      localViewGroup = (ViewGroup)this.mMainView.findViewById(2131230739);
      i = 1 + localViewGroup.indexOfChild(localTextView2);
      j = 0;
      if (j >= this.mChallenge.getCheckboxCount())
        break label463;
      ChallengeProtos.FormCheckbox localFormCheckbox = this.mChallenge.getCheckbox(j);
      localCheckBox = (CheckBox)localLayoutInflater.inflate(2130968594, this.mMainView, false);
      localCheckBox.setText(localFormCheckbox.getDescription());
      localCheckBox.setTag(localFormCheckbox);
      if ((paramBundle != null) || (this.mPreviousState != null))
        break label387;
      localCheckBox.setChecked(localFormCheckbox.getChecked());
    }
    while (true)
    {
      localCheckBox.setOnCheckedChangeListener(this);
      localViewGroup.addView(localCheckBox, i + j);
      j++;
      break label264;
      localTextView1.setVisibility(8);
      break;
      label377: localTextView2.setVisibility(8);
      break label226;
      label387: if (this.mPreviousState != null)
        localCheckBox.setChecked(this.mPreviousState.getBoolean("checkbox_" + j));
      else
        localCheckBox.setChecked(paramBundle.getBoolean("checkbox_" + j));
    }
    label463: this.mBillingAddress = ((BillingAddress)this.mMainView.findViewById(2131230764));
    this.mBillingAddress.setBillingCountryChangeListener(new BillingAddress.BillingCountryChangeListener()
    {
      public void onBillingCountryChanged(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramAnonymousCountry)
      {
        AddressChallengeFragment.this.mBillingAddress.setAddressSpec(paramAnonymousCountry, AddressChallengeFragment.this.mAddressSpec);
        if (AddressChallengeFragment.this.mListener != null)
        {
          Bundle localBundle = new Bundle();
          AddressChallengeFragment.this.saveMyState(localBundle);
          AddressChallengeFragment.this.mListener.onCountryChanged(paramAnonymousCountry.getCountryCode(), localBundle);
        }
      }
    });
    this.mBillingAddress.setInitializationStateListener(new BillingAddress.InitializationStateListener()
    {
      public void onInitialized()
      {
        if (AddressChallengeFragment.this.mListener != null)
          AddressChallengeFragment.this.mListener.onInitialized();
      }

      public void onInitializing()
      {
        if (AddressChallengeFragment.this.mListener != null)
          AddressChallengeFragment.this.mListener.onInitializing();
      }
    });
    this.mSaveButton = ((Button)this.mMainView.findViewById(2131230769));
    this.mSaveButton.setOnClickListener(this);
    this.mSaveButton.setEnabled(false);
    this.mSaveButton.setText(2131165488);
    this.mCancelButton = ((Button)this.mMainView.findViewById(2131230770));
    this.mCancelButton.setOnClickListener(this);
    this.mCancelButton.setText(2131165273);
    if (this.mChallenge.getSupportedCountryCount() > 0)
      initializeCountriesFromChallenge();
    while (true)
    {
      localObject = this.mMainView;
      break;
      loadBillingCountries();
    }
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    this.mListener.onAddressChallengeResult(AddressChallengeFragment.AddressChallengeResultListener.Result.CANCELED, null, null);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    loadBillingCountries();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    saveMyState(paramBundle);
  }

  public void setOnResultListener(AddressChallengeResultListener paramAddressChallengeResultListener)
  {
    this.mListener = paramAddressChallengeResultListener;
  }

  public static abstract interface AddressChallengeResultListener
  {
    public abstract void onAddressChallengeResult(Result paramResult, BillingAddress.Address paramAddress, boolean[] paramArrayOfBoolean);

    public abstract void onCountryChanged(String paramString, Bundle paramBundle);

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
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.challenge.AddressChallengeFragment
 * JD-Core Version:    0.6.2
 */
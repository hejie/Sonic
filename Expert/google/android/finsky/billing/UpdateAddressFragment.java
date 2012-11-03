package com.google.android.finsky.billing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.layout.BillingAddress;
import com.google.android.finsky.layout.BillingAddress.BillingCountryChangeListener;
import com.google.android.finsky.layout.BillingAddress.InitializationStateListener;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class UpdateAddressFragment extends Fragment
  implements View.OnClickListener, SimpleAlertDialog.Listener
{
  private BillingAddress mBillingAddress;
  private Button mCancelButton;
  private List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> mCountries;
  private String mHeaderText;
  private TextView mHeaderTextView;
  private CommonDevice.Instrument mInstrument;
  private String mInstrumentDisplayName;
  private TextView mInstrumentName;
  private UpdateAddressResultListener mListener;
  private ViewGroup mMainView;
  private Button mSaveButton;
  private Bundle mSavedInstanceState;
  private VendingProtos.PurchaseMetadataResponseProto.Countries.Country mSelectedCountry;

  private void clearErrorMessages()
  {
    this.mBillingAddress.clearErrorMessage();
  }

  private TextView displayError(ChallengeProtos.InputValidationError paramInputValidationError)
  {
    return this.mBillingAddress.displayError(paramInputValidationError);
  }

  private BillingAddress.Address getAddressOrShowErrors()
  {
    List localList = this.mBillingAddress.getAddressValidationErrors();
    displayErrors(localList);
    if (localList.size() == 0);
    for (BillingAddress.Address localAddress = this.mBillingAddress.getAddress(); ; localAddress = null)
      return localAddress;
  }

  private CommonDevice.BillingAddressSpec getAddressSpec(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry)
  {
    return new CommonDevice.BillingAddressSpec().setBillingAddressType(1);
  }

  private void loadBillingCountries()
  {
    if (this.mListener != null)
      this.mListener.onInitializing();
    new GetBillingCountriesAction().run(getArguments().getString("authAccount"), new Runnable()
    {
      public void run()
      {
        UpdateAddressFragment.this.onBillingCountriesLoaded();
      }
    });
  }

  public static UpdateAddressFragment newInstance(String paramString1, String paramString2, CommonDevice.Instrument paramInstrument, String paramString3, String paramString4)
  {
    UpdateAddressFragment localUpdateAddressFragment = new UpdateAddressFragment();
    Bundle localBundle = new Bundle();
    localBundle.putString("instrument_owner", paramString2);
    localBundle.putString("authAccount", paramString1);
    localBundle.putParcelable("instrument", ParcelableProto.forProto(paramInstrument));
    localBundle.putString("instrument_display_name", paramString3);
    localBundle.putString("update_address_header", paramString4);
    localUpdateAddressFragment.setArguments(localBundle);
    return localUpdateAddressFragment;
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
        localSimpleAlertDialog.setCallback(this, 1, (Bundle)null);
        localSimpleAlertDialog.show(getFragmentManager(), "error");
      }
    }
  }

  private void setupWidgets(Bundle paramBundle)
  {
    if (this.mSelectedCountry == null)
      if (!this.mInstrument.getBillingAddress().hasPostalCountry())
        break label146;
    label146: for (this.mSelectedCountry = BillingUtils.findCountry(this.mInstrument.getBillingAddress().getPostalCountry(), this.mCountries); ; this.mSelectedCountry = BillingUtils.findCountry(BillingUtils.getDefaultCountry(getActivity(), null), this.mCountries))
    {
      this.mHeaderTextView.setText(this.mHeaderText);
      this.mInstrumentName.setText(this.mInstrumentDisplayName);
      this.mBillingAddress.setDefaultName(this.mInstrument.getBillingAddress().getName());
      this.mBillingAddress.setBillingCountries(BillingLocator.getBillingCountries());
      this.mBillingAddress.setAddressSpec(this.mSelectedCountry, getAddressSpec(this.mSelectedCountry), this.mInstrument.getBillingAddress());
      this.mBillingAddress.setPhoneNumber(this.mInstrument.getBillingAddress().getPhoneNumber());
      if (paramBundle != null)
        this.mBillingAddress.restoreInstanceState(paramBundle);
      return;
    }
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
    this.mBillingAddress.setEnabled(paramBoolean);
    this.mSaveButton.setEnabled(paramBoolean);
    this.mCancelButton.setEnabled(paramBoolean);
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
        this.mInstrument.setBillingAddress(localAddress);
        this.mListener.onUpdateAddressResult(UpdateAddressFragment.UpdateAddressResultListener.Result.SUCCESS, this.mInstrument);
        continue;
        this.mListener.onUpdateAddressResult(UpdateAddressFragment.UpdateAddressResultListener.Result.CANCELED, null);
      }
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (paramViewGroup == null);
    for (Object localObject = null; ; localObject = this.mMainView)
    {
      return localObject;
      this.mSavedInstanceState = paramBundle;
      this.mMainView = ((ViewGroup)paramLayoutInflater.inflate(2130968604, paramViewGroup, false));
      this.mInstrumentDisplayName = getArguments().getString("instrument_display_name");
      this.mHeaderText = getArguments().getString("update_address_header");
      this.mInstrument = ((CommonDevice.Instrument)ParcelableProto.getProtoFromBundle(getArguments(), "instrument"));
      this.mHeaderTextView = ((TextView)this.mMainView.findViewById(2131230810));
      this.mInstrumentName = ((TextView)this.mMainView.findViewById(2131230811));
      this.mBillingAddress = ((BillingAddress)this.mMainView.findViewById(2131230764));
      this.mBillingAddress.setNameInputHint(2131165238);
      this.mBillingAddress.setBillingCountryChangeListener(new BillingAddress.BillingCountryChangeListener()
      {
        public void onBillingCountryChanged(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramAnonymousCountry)
        {
          UpdateAddressFragment.this.mBillingAddress.setAddressSpec(paramAnonymousCountry, UpdateAddressFragment.this.getAddressSpec(paramAnonymousCountry), UpdateAddressFragment.this.mInstrument.getBillingAddress());
        }
      });
      this.mBillingAddress.setInitializationStateListener(new BillingAddress.InitializationStateListener()
      {
        public void onInitialized()
        {
          if (UpdateAddressFragment.this.mListener != null)
            UpdateAddressFragment.this.mListener.onInitialized();
        }

        public void onInitializing()
        {
          if (UpdateAddressFragment.this.mListener != null)
            UpdateAddressFragment.this.mListener.onInitializing();
        }
      });
      this.mSaveButton = ((Button)this.mMainView.findViewById(2131230769));
      this.mSaveButton.setOnClickListener(this);
      this.mSaveButton.setEnabled(false);
      this.mSaveButton.setText(2131165258);
      this.mCancelButton = ((Button)this.mMainView.findViewById(2131230770));
      this.mCancelButton.setOnClickListener(this);
      this.mCancelButton.setText(2131165273);
      loadBillingCountries();
    }
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    this.mListener.onUpdateAddressResult(UpdateAddressFragment.UpdateAddressResultListener.Result.CANCELED, null);
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    loadBillingCountries();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mBillingAddress != null)
      this.mBillingAddress.saveInstanceState(paramBundle);
  }

  public void setOnResultListener(UpdateAddressResultListener paramUpdateAddressResultListener)
  {
    this.mListener = paramUpdateAddressResultListener;
  }

  public static abstract interface UpdateAddressResultListener
  {
    public abstract void onInitialized();

    public abstract void onInitializing();

    public abstract void onUpdateAddressResult(Result paramResult, CommonDevice.Instrument paramInstrument);

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
 * Qualified Name:     com.google.android.finsky.billing.UpdateAddressFragment
 * JD-Core Version:    0.6.2
 */
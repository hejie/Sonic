package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressProblems;
import com.android.i18n.addressinput.AddressWidget;
import com.android.i18n.addressinput.AddressWidget.Listener;
import com.android.i18n.addressinput.FormOptions;
import com.android.i18n.addressinput.FormOptions.Builder;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.AddressMetadataCacheManager;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.ChallengeProtos.InputValidationError;
import com.google.android.finsky.remoting.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillingAddress extends LinearLayout
  implements AddressWidget.Listener
{
  private static String KEY_ADDRESS_SPEC = "address_spec";
  private static String KEY_SELECTED_COUNTRY = "selected_country";
  private CommonDevice.BillingAddressSpec mAddressSpec;
  private AddressWidget mAddressWidget;
  private List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> mCountries;
  private BillingCountryChangeListener mCountryChangeListener;
  private Spinner mCountrySpinner;
  private boolean mCountrySpinnerSelectionSet = false;
  private EditText mEmailAddress;
  private EditText mFirstName;
  private InitializationStateListener mInitializationStateListener;
  private EditText mLastName;
  private EditText mNameEntry;
  private EditText mPhoneNumber;
  private VendingProtos.PurchaseMetadataResponseProto.Countries.Country mSelectedCountry;

  public BillingAddress(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130968593, this, true);
  }

  private static int addressFieldToAddressEnum(AddressField paramAddressField)
  {
    int i;
    switch (2.$SwitchMap$com$android$i18n$addressinput$AddressField[paramAddressField.ordinal()])
    {
    default:
      i = -1;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    while (true)
    {
      return i;
      i = 8;
      continue;
      i = 7;
      continue;
      i = 5;
      continue;
      i = 6;
      continue;
      i = 11;
      continue;
      i = 9;
      continue;
      i = 10;
    }
  }

  private void addressProblemsToInputValidationErrors(AddressProblems paramAddressProblems, List<ChallengeProtos.InputValidationError> paramList)
  {
    Iterator localIterator = paramAddressProblems.getProblems().entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int i;
      switch (2.$SwitchMap$com$android$i18n$addressinput$AddressField[((AddressField)localEntry.getKey()).ordinal()])
      {
      default:
        i = 13;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localEntry.getKey();
        FinskyLog.w("No equivalent for address widget field: %s", arrayOfObject);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      }
      while (true)
      {
        paramList.add(new ChallengeProtos.InputValidationError().setInputField(i));
        break;
        i = 8;
        continue;
        i = 7;
        continue;
        i = 5;
        continue;
        i = 6;
        continue;
        i = 11;
        continue;
        i = 9;
        continue;
        i = 10;
      }
    }
  }

  private boolean isInReducedAddressMode()
  {
    int i = 1;
    if (this.mAddressSpec.getBillingAddressType() != i);
    while (true)
    {
      return i;
      int j = 0;
    }
  }

  private FormOptions optionsFromInputFieldList(List<Integer> paramList)
  {
    FormOptions.Builder localBuilder = new FormOptions.Builder();
    localBuilder.hide(AddressField.COUNTRY).hide(AddressField.RECIPIENT).hide(AddressField.ORGANIZATION);
    for (AddressField localAddressField : AddressField.values())
      if (!paramList.contains(Integer.valueOf(addressFieldToAddressEnum(localAddressField))))
        localBuilder.hide(localAddressField);
    return localBuilder.build();
  }

  private static void populatedRequiredFieldsFromAddressType(int paramInt, CommonDevice.BillingAddressSpec paramBillingAddressSpec)
  {
    paramBillingAddressSpec.addRequiredField(4);
    paramBillingAddressSpec.addRequiredField(10);
    paramBillingAddressSpec.addRequiredField(9);
    if (paramInt == 1)
    {
      paramBillingAddressSpec.addRequiredField(5);
      paramBillingAddressSpec.addRequiredField(6);
      paramBillingAddressSpec.addRequiredField(8);
      paramBillingAddressSpec.addRequiredField(7);
      paramBillingAddressSpec.addRequiredField(12);
    }
    while (true)
    {
      return;
      if (((Boolean)G.reducedBillingAddressRequiresPhonenumber.get()).booleanValue())
        paramBillingAddressSpec.addRequiredField(12);
    }
  }

  private static boolean validateEmailAddress(CharSequence paramCharSequence)
  {
    return Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches();
  }

  public void clearErrorMessage()
  {
    this.mNameEntry.setError(null);
    this.mFirstName.setError(null);
    this.mLastName.setError(null);
    this.mPhoneNumber.setError(null);
    this.mEmailAddress.setError(null);
    this.mAddressWidget.clearErrorMessage();
  }

  public TextView displayError(ChallengeProtos.InputValidationError paramInputValidationError)
  {
    String str = paramInputValidationError.getErrorMessage();
    Object localObject = null;
    AddressField localAddressField = null;
    switch (paramInputValidationError.getInputField())
    {
    case 14:
    default:
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInputValidationError.getInputField());
      arrayOfObject[1] = paramInputValidationError.getErrorMessage();
      FinskyLog.d("InputValidationError that can't be displayed: type=%d, message=%s", arrayOfObject);
      if (localAddressField != null)
      {
        if (this.mAddressWidget.getViewForField(localAddressField) == null)
          break label277;
        this.mAddressWidget.displayErrorMessageForInvalidEntryIn(localAddressField);
      }
      break;
    case 4:
    case 15:
    case 16:
    case 12:
    case 17:
    case 8:
    case 7:
    case 13:
    case 5:
    case 6:
    case 11:
    case 9:
    case 10:
    }
    while (true)
    {
      return localObject;
      localObject = this.mNameEntry;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mFirstName;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mLastName;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mPhoneNumber;
      ((TextView)localObject).setError(str);
      break;
      localObject = this.mEmailAddress;
      ((TextView)localObject).setError(str);
      break;
      localAddressField = AddressField.ADMIN_AREA;
      break;
      localAddressField = AddressField.LOCALITY;
      break;
      FinskyLog.d("Input error ADDR_WHOLE_ADDRESS. Displaying at ADDRESS_LINE_1.", new Object[0]);
      localAddressField = AddressField.ADDRESS_LINE_1;
      break;
      localAddressField = AddressField.ADDRESS_LINE_2;
      break;
      localAddressField = AddressField.DEPENDENT_LOCALITY;
      break;
      localAddressField = AddressField.POSTAL_CODE;
      break;
      localAddressField = AddressField.COUNTRY;
      break;
      label277: localObject = this.mNameEntry;
      this.mNameEntry.setError(str);
    }
  }

  public BillingAddress.Address getAddress()
  {
    BillingAddress.Address localAddress = BillingUtils.instrumentAddressFromAddressData(this.mAddressWidget.getAddressData(), this.mAddressSpec.getRequiredFieldList());
    localAddress.setDeprecatedIsReduced(isInReducedAddressMode());
    if (this.mPhoneNumber.getVisibility() == 0)
      localAddress.setPhoneNumber(this.mPhoneNumber.getText().toString());
    if (this.mNameEntry.getVisibility() == 0)
      localAddress.setName(this.mNameEntry.getText().toString());
    if (this.mFirstName.getVisibility() == 0)
      localAddress.setFirstName(this.mFirstName.getText().toString());
    if (this.mLastName.getVisibility() == 0)
      localAddress.setLastName(this.mLastName.getText().toString());
    if (this.mEmailAddress.getVisibility() == 0)
      localAddress.setEmail(this.mEmailAddress.getText().toString());
    return localAddress;
  }

  public List<ChallengeProtos.InputValidationError> getAddressValidationErrors()
  {
    ArrayList localArrayList = Lists.newArrayList();
    addressProblemsToInputValidationErrors(this.mAddressWidget.getAddressProblems(), localArrayList);
    if ((this.mNameEntry.getVisibility() == 0) && (BillingUtils.isEmptyOrSpaces(this.mNameEntry.getText())))
      localArrayList.add(new ChallengeProtos.InputValidationError().setInputField(4).setErrorMessage(getContext().getString(2131165253)));
    if ((this.mFirstName.getVisibility() == 0) && (BillingUtils.isEmptyOrSpaces(this.mFirstName.getText())))
      localArrayList.add(new ChallengeProtos.InputValidationError().setInputField(15).setErrorMessage(getContext().getString(2131165253)));
    if ((this.mLastName.getVisibility() == 0) && (BillingUtils.isEmptyOrSpaces(this.mLastName.getText())))
      localArrayList.add(new ChallengeProtos.InputValidationError().setInputField(16).setErrorMessage(getContext().getString(2131165253)));
    if ((this.mPhoneNumber.getVisibility() == 0) && (BillingUtils.isEmptyOrSpaces(this.mPhoneNumber.getText())))
      localArrayList.add(new ChallengeProtos.InputValidationError().setInputField(12).setErrorMessage(getContext().getString(2131165256)));
    if ((this.mEmailAddress.getVisibility() == 0) && (!validateEmailAddress(this.mEmailAddress.getText())))
      localArrayList.add(new ChallengeProtos.InputValidationError().setInputField(17).setErrorMessage(getContext().getString(2131165257)));
    return localArrayList;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mNameEntry = ((EditText)findViewById(2131230762));
    this.mFirstName = ((EditText)findViewById(2131230778));
    this.mLastName = ((EditText)findViewById(2131230779));
    this.mEmailAddress = ((EditText)findViewById(2131230783));
    this.mCountrySpinner = ((Spinner)findViewById(2131230780));
    this.mPhoneNumber = ((EditText)findViewById(2131230782));
  }

  public void onInitialized()
  {
    if (this.mInitializationStateListener != null)
      this.mInitializationStateListener.onInitialized();
    View localView = this.mAddressWidget.getViewForField(AddressField.POSTAL_CODE);
    if ((localView instanceof TextView))
    {
      TextView localTextView = (TextView)localView;
      localTextView.setInputType(0x1000 | localTextView.getInputType());
    }
  }

  public void onInitializing()
  {
    if (this.mInitializationStateListener != null)
      this.mInitializationStateListener.onInitializing();
  }

  public void restoreInstanceState(Bundle paramBundle)
  {
    if (paramBundle.containsKey(KEY_ADDRESS_SPEC))
    {
      this.mAddressSpec = ((CommonDevice.BillingAddressSpec)ParcelableProto.getProtoFromBundle(paramBundle, KEY_ADDRESS_SPEC));
      this.mSelectedCountry = ((VendingProtos.PurchaseMetadataResponseProto.Countries.Country)ParcelableProto.getProtoFromBundle(paramBundle, KEY_SELECTED_COUNTRY));
      setAddressSpec(this.mSelectedCountry, this.mAddressSpec);
      this.mAddressWidget.restoreInstanceState(paramBundle);
    }
  }

  public void saveInstanceState(Bundle paramBundle)
  {
    if (this.mAddressSpec != null)
      paramBundle.putParcelable(KEY_ADDRESS_SPEC, ParcelableProto.forProto(this.mAddressSpec));
    if (this.mSelectedCountry != null)
      paramBundle.putParcelable(KEY_SELECTED_COUNTRY, ParcelableProto.forProto(this.mSelectedCountry));
    if (this.mAddressWidget != null)
      this.mAddressWidget.saveInstanceState(paramBundle);
  }

  public void setAddressSpec(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry, CommonDevice.BillingAddressSpec paramBillingAddressSpec)
  {
    setAddressSpec(paramCountry, paramBillingAddressSpec, null);
  }

  public void setAddressSpec(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry, CommonDevice.BillingAddressSpec paramBillingAddressSpec, BillingAddress.Address paramAddress)
  {
    if (!this.mCountrySpinnerSelectionSet)
    {
      int i = -1;
      int j = 0;
      Iterator localIterator = this.mCountries.iterator();
      while (localIterator.hasNext())
      {
        VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry = (VendingProtos.PurchaseMetadataResponseProto.Countries.Country)localIterator.next();
        if (paramCountry.getCountryCode().equals(localCountry.getCountryCode()))
          i = j;
        j++;
      }
      if (i >= 0)
      {
        this.mCountrySpinner.setSelection(i);
        this.mCountrySpinnerSelectionSet = true;
      }
    }
    this.mSelectedCountry = paramCountry;
    this.mAddressSpec = paramBillingAddressSpec;
    if (paramBillingAddressSpec.getRequiredFieldCount() == 0)
      populatedRequiredFieldsFromAddressType(paramBillingAddressSpec.getBillingAddressType(), paramBillingAddressSpec);
    List localList = paramBillingAddressSpec.getRequiredFieldList();
    FormOptions localFormOptions = optionsFromInputFieldList(paramBillingAddressSpec.getRequiredFieldList());
    if (localList.contains(Integer.valueOf(17)))
      this.mEmailAddress.setVisibility(0);
    if (localList.contains(Integer.valueOf(15)))
    {
      this.mNameEntry.setVisibility(8);
      this.mFirstName.setVisibility(0);
    }
    if (localList.contains(Integer.valueOf(16)))
    {
      this.mNameEntry.setVisibility(8);
      this.mLastName.setVisibility(0);
    }
    if (!localList.contains(Integer.valueOf(12)))
      this.mPhoneNumber.setVisibility(8);
    if (!localList.contains(Integer.valueOf(10)))
      this.mCountrySpinner.setVisibility(8);
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131230781);
    if (paramAddress != null)
    {
      if (paramAddress.hasName())
        this.mNameEntry.setText(paramAddress.getName());
      if (paramAddress.hasFirstName())
        this.mFirstName.setText(paramAddress.getFirstName());
      if (paramAddress.hasLastName())
        this.mLastName.setText(paramAddress.getLastName());
      if (paramAddress.hasEmail())
        this.mEmailAddress.setText(paramAddress.getEmail());
      if (paramAddress.hasPhoneNumber())
        this.mPhoneNumber.setText(paramAddress.getPhoneNumber());
    }
    for (this.mAddressWidget = new AddressWidget(getContext(), localViewGroup, localFormOptions, new AddressMetadataCacheManager(FinskyApp.get().getCache()), BillingUtils.addressDataFromInstrumentAddress(paramAddress)); ; this.mAddressWidget = new AddressWidget(getContext(), localViewGroup, localFormOptions, new AddressMetadataCacheManager(FinskyApp.get().getCache())))
    {
      this.mAddressWidget.updateWidgetOnCountryChange(this.mSelectedCountry.getCountryCode());
      this.mAddressWidget.setListener(this);
      return;
    }
  }

  public void setBillingCountries(List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> paramList)
  {
    this.mCountries = paramList;
    this.mCountrySpinner = ((Spinner)findViewById(2131230780));
    this.mCountrySpinner.setPrompt(getResources().getText(2131165245));
    this.mCountrySpinner.setOnItemSelectedListener(null);
    ArrayAdapter localArrayAdapter = new ArrayAdapter(getContext(), 17367048);
    localArrayAdapter.setDropDownViewResource(17367049);
    Iterator localIterator = this.mCountries.iterator();
    while (localIterator.hasNext())
      localArrayAdapter.add(new CountrySpinnerItem((VendingProtos.PurchaseMetadataResponseProto.Countries.Country)localIterator.next()));
    this.mCountrySpinner.setAdapter(localArrayAdapter);
    this.mCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry = (VendingProtos.PurchaseMetadataResponseProto.Countries.Country)BillingAddress.this.mCountries.get(paramAnonymousInt);
        if ((BillingAddress.this.mSelectedCountry != null) && (BillingAddress.this.mSelectedCountry.getCountryCode().equals(localCountry.getCountryCode())));
        while (true)
        {
          return;
          if (BillingAddress.this.mCountryChangeListener != null)
            BillingAddress.this.mCountryChangeListener.onBillingCountryChanged(localCountry);
        }
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
        onItemSelected(paramAnonymousAdapterView, null, 0, 0L);
      }
    });
  }

  public void setBillingCountryChangeListener(BillingCountryChangeListener paramBillingCountryChangeListener)
  {
    this.mCountryChangeListener = paramBillingCountryChangeListener;
  }

  public void setDefaultName(String paramString)
  {
    if (this.mNameEntry.getText().length() == 0)
      this.mNameEntry.setText(paramString);
  }

  public void setEnabled(boolean paramBoolean)
  {
    this.mNameEntry.setEnabled(paramBoolean);
    this.mFirstName.setEnabled(paramBoolean);
    this.mLastName.setEnabled(paramBoolean);
    this.mEmailAddress.setEnabled(paramBoolean);
    this.mCountrySpinner.setEnabled(paramBoolean);
    this.mAddressWidget.setEnabled(paramBoolean);
    this.mPhoneNumber.setEnabled(paramBoolean);
  }

  public void setInitializationStateListener(InitializationStateListener paramInitializationStateListener)
  {
    this.mInitializationStateListener = paramInitializationStateListener;
  }

  public void setNameInputHint(int paramInt)
  {
    this.mNameEntry.setHint(paramInt);
  }

  public void setPhoneNumber(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (TextUtils.isEmpty(this.mPhoneNumber.getText())))
      this.mPhoneNumber.setText(PhoneNumberUtils.formatNumber(paramString));
  }

  public static abstract interface BillingCountryChangeListener
  {
    public abstract void onBillingCountryChanged(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry);
  }

  public static class CountrySpinnerItem
  {
    final VendingProtos.PurchaseMetadataResponseProto.Countries.Country mCountry;

    public CountrySpinnerItem(VendingProtos.PurchaseMetadataResponseProto.Countries.Country paramCountry)
    {
      this.mCountry = paramCountry;
    }

    public String toString()
    {
      return this.mCountry.getCountryName();
    }
  }

  public static abstract interface InitializationStateListener
  {
    public abstract void onInitialized();

    public abstract void onInitializing();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.BillingAddress
 * JD-Core Version:    0.6.2
 */
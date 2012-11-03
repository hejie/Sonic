package com.google.android.finsky.billing.carrierbilling.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressWidget;
import com.android.i18n.addressinput.FormOptions;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.AddressMetadataCacheManager;
import com.google.android.finsky.billing.BillingLocator;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.BillingUtils.CreateInstrumentUiMode;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils;
import com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils.AddressInputField;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo.Builder;
import com.google.android.finsky.utils.FinskyLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class EditCarrierBillingFragment extends Fragment
  implements View.OnClickListener
{
  private BillingUtils.AddressMode mAddressMode;
  private AddressWidget mAddressWidget;
  private ViewGroup mEditSection;
  private EditCarrierBillingResultListener mListener;
  private TextView mNameView;
  private EditText mPhoneNumberEditView;
  private View mPhoneNumberLabel;
  private BillingUtils.CreateInstrumentUiMode mUiMode;

  private void displayErrorToast()
  {
    Toast.makeText(getActivity(), 2131165268, 1).show();
  }

  private void displayErrors(Collection<PhoneCarrierBillingUtils.AddressInputField> paramCollection)
  {
    Object localObject1 = null;
    int i = 0;
    int j = 0;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      PhoneCarrierBillingUtils.AddressInputField localAddressInputField = (PhoneCarrierBillingUtils.AddressInputField)localIterator.next();
      Object localObject2 = null;
      switch (1.$SwitchMap$com$google$android$finsky$billing$carrierbilling$PhoneCarrierBillingUtils$AddressInputField[localAddressInputField.ordinal()])
      {
      default:
        break;
      case 1:
      case 2:
        localObject2 = this.mAddressWidget.getViewForField(AddressField.ADDRESS_LINE_1);
        if (localObject2 != null)
        {
          setTextViewError((TextView)localObject2, 2131165254);
          j = 1;
        }
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
        while (true)
        {
          if (localObject1 != null)
            break label342;
          localObject1 = localObject2;
          i = BillingUtils.getViewOffsetToChild(this.mEditSection, (View)localObject2);
          break;
          localObject2 = this.mAddressWidget.getViewForField(AddressField.ADDRESS_LINE_2);
          if (localObject2 != null)
          {
            setTextViewError((TextView)localObject2, 2131165254);
            j = 1;
            continue;
            localObject2 = this.mAddressWidget.getViewForField(AddressField.LOCALITY);
            if (localObject2 != null)
            {
              setTextViewError((TextView)localObject2, 2131165255);
              j = 1;
              continue;
              localObject2 = this.mNameView;
              setTextViewError((TextView)localObject2, 2131165253);
              j = 1;
              continue;
              localObject2 = this.mAddressWidget.getViewForField(AddressField.POSTAL_CODE);
              if (localObject2 != null)
              {
                this.mAddressWidget.displayErrorMessageForInvalidEntryIn(AddressField.POSTAL_CODE);
                j = 1;
                continue;
                localObject2 = this.mAddressWidget.getViewForField(AddressField.ADMIN_AREA);
                if (localObject2 != null)
                {
                  this.mAddressWidget.displayErrorMessageForInvalidEntryIn(AddressField.ADMIN_AREA);
                  j = 1;
                  continue;
                  if (this.mPhoneNumberEditView.getVisibility() == 0)
                  {
                    localObject2 = this.mPhoneNumberEditView;
                    setTextViewError((TextView)localObject2, 2131165256);
                    j = 1;
                  }
                }
              }
            }
          }
        }
        label342: if (localObject2 != null)
        {
          int k = BillingUtils.getViewOffsetToChild(this.mEditSection, (View)localObject2);
          if (k < i)
          {
            localObject1 = localObject2;
            i = k;
          }
        }
        break;
      }
    }
    if (j != 0)
      displayErrorToast();
    localObject1.requestFocus();
  }

  private Collection<PhoneCarrierBillingUtils.AddressInputField> getFormErrors(ArrayList<Integer> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      switch (localInteger.intValue())
      {
      case 11:
      default:
        FinskyLog.w("InputValidationError that can't be displayed: type=%d", new Object[] { localInteger });
        break;
      case 4:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.PERSON_NAME);
        break;
      case 12:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_PHONE);
        break;
      case 8:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_STATE);
        break;
      case 7:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_CITY);
        break;
      case 13:
        FinskyLog.d("Input error ADDR_WHOLE_ADDRESS. Displaying at ADDRESS_LINE_1.", new Object[0]);
      case 5:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_ADDRESS1);
        break;
      case 6:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_ADDRESS2);
        break;
      case 9:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_POSTAL_CODE);
        break;
      case 10:
        localArrayList.add(PhoneCarrierBillingUtils.AddressInputField.ADDR_COUNTRY_CODE);
      }
    }
    return localArrayList;
  }

  private String getPhoneNumber()
  {
    return this.mPhoneNumberEditView.getText().toString();
  }

  private SubscriberInfo getReturnAddress()
  {
    AddressData localAddressData = this.mAddressWidget.getAddressData();
    SubscriberInfo.Builder localBuilder = new SubscriberInfo.Builder().setName(this.mNameView.getText().toString()).setPostalCode(localAddressData.getPostalCode()).setCountry(localAddressData.getPostalCountry());
    if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode))
      localBuilder.setIdentifier(getPhoneNumber());
    if (this.mAddressMode == BillingUtils.AddressMode.FULL_ADDRESS)
      localBuilder.setAddress1(localAddressData.getAddressLine1()).setAddress2(localAddressData.getAddressLine2()).setCity(localAddressData.getLocality()).setState(localAddressData.getAdministrativeArea());
    return localBuilder.build();
  }

  private void initViews(ViewGroup paramViewGroup)
  {
    this.mEditSection = paramViewGroup;
    this.mNameView = ((EditText)paramViewGroup.findViewById(2131230803));
    this.mPhoneNumberEditView = ((EditText)paramViewGroup.findViewById(2131230806));
    this.mPhoneNumberLabel = paramViewGroup.findViewById(2131230805);
  }

  public static EditCarrierBillingFragment newInstance(BillingUtils.AddressMode paramAddressMode, SubscriberInfo paramSubscriberInfo, ArrayList<Integer> paramArrayList, BillingUtils.CreateInstrumentUiMode paramCreateInstrumentUiMode)
  {
    EditCarrierBillingFragment localEditCarrierBillingFragment = new EditCarrierBillingFragment();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("prefill_address", paramSubscriberInfo);
    localBundle.putString("type", paramAddressMode.name());
    localBundle.putIntegerArrayList("highlight_address", paramArrayList);
    localBundle.putString("ui_mode", paramCreateInstrumentUiMode.name());
    localEditCarrierBillingFragment.setArguments(localBundle);
    return localEditCarrierBillingFragment;
  }

  private void setTextViewError(TextView paramTextView, int paramInt)
  {
    paramTextView.setError(getString(paramInt));
  }

  private void setupAddressEditView(View paramView)
  {
    if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode))
      showPhoneNumberEditView(PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony()));
    showAddressEditView(paramView, null);
  }

  private void setupAddressEditView(View paramView, SubscriberInfo paramSubscriberInfo)
  {
    showNameView(paramSubscriberInfo.getName());
    if (PhoneCarrierBillingUtils.isPhoneNumberRequired(this.mAddressMode))
    {
      String str = paramSubscriberInfo.getIdentifier();
      if (BillingUtils.isEmptyOrSpaces(str))
        str = PhoneNumberUtils.formatNumber(BillingLocator.getLine1NumberFromTelephony());
      showPhoneNumberEditView(str);
    }
    showAddressEditView(paramView, PhoneCarrierBillingUtils.subscriberInfoToAddressData(paramSubscriberInfo));
  }

  private void showAddressEditView(View paramView, AddressData paramAddressData)
  {
    FormOptions localFormOptions = BillingUtils.getAddressFormOptions(this.mAddressMode);
    ViewGroup localViewGroup = (ViewGroup)paramView.findViewById(2131230781);
    if (paramAddressData != null);
    for (this.mAddressWidget = new AddressWidget(getActivity(), localViewGroup, localFormOptions, new AddressMetadataCacheManager(FinskyApp.get().getCache()), paramAddressData); ; this.mAddressWidget = new AddressWidget(getActivity(), localViewGroup, localFormOptions, new AddressMetadataCacheManager(FinskyApp.get().getCache())))
      return;
  }

  private void showNameView(String paramString)
  {
    this.mNameView.setText(paramString);
  }

  private void showPhoneNumberEditView(String paramString)
  {
    this.mPhoneNumberLabel.setVisibility(0);
    this.mPhoneNumberEditView.setVisibility(0);
    if (!BillingUtils.isEmptyOrSpaces(paramString))
      this.mPhoneNumberEditView.setText(paramString);
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if (paramBundle != null)
      this.mAddressWidget.restoreInstanceState(paramBundle);
  }

  public void onClick(View paramView)
  {
    Collection localCollection;
    if (paramView.getId() == 2131230769)
    {
      localCollection = PhoneCarrierBillingUtils.getErrors(this.mNameView.getText().toString(), getPhoneNumber(), this.mAddressWidget.getAddressProblems(), this.mAddressMode);
      if (localCollection.isEmpty())
        this.mListener.onEditCarrierBillingResult(EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.SUCCESS, getReturnAddress());
    }
    while (true)
    {
      return;
      displayErrors(localCollection);
      continue;
      if (paramView.getId() == 2131230770)
        this.mListener.onEditCarrierBillingResult(EditCarrierBillingFragment.EditCarrierBillingResultListener.EditResult.CANCELED, null);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968601, paramViewGroup, false);
    ViewGroup localViewGroup = (ViewGroup)localView.findViewById(2131230802);
    localViewGroup.setFocusableInTouchMode(true);
    localViewGroup.requestFocus();
    initViews(localViewGroup);
    Button localButton1 = (Button)localView.findViewById(2131230769);
    localButton1.setText(2131165258);
    localButton1.setOnClickListener(this);
    Button localButton2 = (Button)localView.findViewById(2131230770);
    localButton2.setText(2131165273);
    localButton2.setOnClickListener(this);
    Bundle localBundle = getArguments();
    this.mAddressMode = BillingUtils.AddressMode.valueOf(localBundle.getString("type"));
    SubscriberInfo localSubscriberInfo = (SubscriberInfo)localBundle.getParcelable("prefill_address");
    this.mUiMode = BillingUtils.CreateInstrumentUiMode.valueOf(localBundle.getString("ui_mode"));
    if (localSubscriberInfo != null)
      setupAddressEditView(localViewGroup, localSubscriberInfo);
    while (true)
    {
      ArrayList localArrayList = getArguments().getIntegerArrayList("highlight_address");
      if (localArrayList != null)
        displayErrors(getFormErrors(localArrayList));
      return localView;
      setupAddressEditView(localViewGroup);
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mAddressWidget.saveInstanceState(paramBundle);
  }

  public void setOnResultListener(EditCarrierBillingResultListener paramEditCarrierBillingResultListener)
  {
    this.mListener = paramEditCarrierBillingResultListener;
  }

  public static abstract interface EditCarrierBillingResultListener
  {
    public abstract void onEditCarrierBillingResult(EditResult paramEditResult, SubscriberInfo paramSubscriberInfo);

    public static enum EditResult
    {
      static
      {
        CANCELED = new EditResult("CANCELED", 1);
        EditResult[] arrayOfEditResult = new EditResult[2];
        arrayOfEditResult[0] = SUCCESS;
        arrayOfEditResult[1] = CANCELED;
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.fragment.EditCarrierBillingFragment
 * JD-Core Version:    0.6.2
 */
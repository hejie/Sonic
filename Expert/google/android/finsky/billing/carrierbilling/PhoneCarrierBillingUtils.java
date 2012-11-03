package com.google.android.finsky.billing.carrierbilling;

import android.text.TextUtils;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressData.Builder;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.AddressProblems;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.BillingUtils.AddressMode;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo;
import com.google.android.finsky.billing.carrierbilling.model.SubscriberInfo.Builder;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PhoneCarrierBillingUtils
{
  private static final EnumMap<AddressField, AddressInputField> addressMap = new EnumMap(AddressField.class);

  static
  {
    addressMap.put(AddressField.STREET_ADDRESS, AddressInputField.ADDR_ADDRESS1);
    addressMap.put(AddressField.ADDRESS_LINE_2, AddressInputField.ADDR_ADDRESS2);
    addressMap.put(AddressField.LOCALITY, AddressInputField.ADDR_CITY);
    addressMap.put(AddressField.ADMIN_AREA, AddressInputField.ADDR_STATE);
    addressMap.put(AddressField.POSTAL_CODE, AddressInputField.ADDR_POSTAL_CODE);
    addressMap.put(AddressField.COUNTRY, AddressInputField.ADDR_COUNTRY_CODE);
  }

  private static AddressInputField convertAddressFieldToInputField(AddressField paramAddressField)
  {
    return (AddressInputField)addressMap.get(paramAddressField);
  }

  public static Collection<AddressInputField> getErrors(String paramString1, String paramString2, AddressProblems paramAddressProblems, BillingUtils.AddressMode paramAddressMode)
  {
    ArrayList localArrayList = new ArrayList();
    if (BillingUtils.isEmptyOrSpaces(paramString1))
      localArrayList.add(AddressInputField.PERSON_NAME);
    if ((isPhoneNumberRequired(paramAddressMode)) && (BillingUtils.isEmptyOrSpaces(paramString2)))
      localArrayList.add(AddressInputField.ADDR_PHONE);
    Iterator localIterator = paramAddressProblems.getProblems().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (BillingUtils.AddressMode.REDUCED_ADDRESS.equals(paramAddressMode))
        switch (1.$SwitchMap$com$android$i18n$addressinput$AddressField[((AddressField)localEntry.getKey()).ordinal()])
        {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      AddressInputField localAddressInputField = convertAddressFieldToInputField((AddressField)localEntry.getKey());
      if (localAddressInputField != null)
        localArrayList.add(localAddressInputField);
    }
    return localArrayList;
  }

  public static SubscriberInfo getSubscriberInfo(BillingAddress.Address paramAddress)
  {
    if (paramAddress == null);
    SubscriberInfo.Builder localBuilder;
    for (SubscriberInfo localSubscriberInfo = null; ; localSubscriberInfo = localBuilder.build())
    {
      return localSubscriberInfo;
      localBuilder = new SubscriberInfo.Builder();
      localBuilder.setName(paramAddress.getName());
      localBuilder.setAddress1(paramAddress.getAddressLine1());
      localBuilder.setAddress2(paramAddress.getAddressLine2());
      localBuilder.setCity(paramAddress.getCity());
      localBuilder.setState(paramAddress.getState());
      localBuilder.setPostalCode(paramAddress.getPostalCode());
    }
  }

  public static boolean isPhoneNumberRequired(BillingUtils.AddressMode paramAddressMode)
  {
    boolean bool = true;
    if (CarrierBillingUtils.isDcb30());
    while (true)
    {
      return bool;
      if (paramAddressMode == BillingUtils.AddressMode.REDUCED_ADDRESS)
        bool = ((Boolean)G.reducedBillingAddressRequiresPhonenumber.get()).booleanValue();
    }
  }

  public static BillingAddress.Address subscriberInfoToAddress(SubscriberInfo paramSubscriberInfo, BillingUtils.AddressMode paramAddressMode)
  {
    BillingAddress.Address localAddress = new BillingAddress.Address();
    if (!TextUtils.isEmpty(paramSubscriberInfo.getName()))
      localAddress.setName(paramSubscriberInfo.getName());
    if (!TextUtils.isEmpty(paramSubscriberInfo.getIdentifier()))
      localAddress.setPhoneNumber(paramSubscriberInfo.getIdentifier());
    if (BillingUtils.AddressMode.FULL_ADDRESS == paramAddressMode)
    {
      if (!TextUtils.isEmpty(paramSubscriberInfo.getAddress1()))
        localAddress.setAddressLine1(paramSubscriberInfo.getAddress1());
      if (!TextUtils.isEmpty(paramSubscriberInfo.getAddress2()))
        localAddress.setAddressLine2(paramSubscriberInfo.getAddress2());
      if (!TextUtils.isEmpty(paramSubscriberInfo.getCity()))
        localAddress.setCity(paramSubscriberInfo.getCity());
      if (!TextUtils.isEmpty(paramSubscriberInfo.getState()))
        localAddress.setState(paramSubscriberInfo.getState());
      localAddress.setDeprecatedIsReduced(false);
    }
    while (true)
    {
      if (!TextUtils.isEmpty(paramSubscriberInfo.getPostalCode()))
        localAddress.setPostalCode(paramSubscriberInfo.getPostalCode());
      if (!TextUtils.isEmpty(paramSubscriberInfo.getCountry()))
        localAddress.setPostalCountry(paramSubscriberInfo.getCountry());
      return localAddress;
      localAddress.setDeprecatedIsReduced(true);
    }
  }

  public static AddressData subscriberInfoToAddressData(SubscriberInfo paramSubscriberInfo)
  {
    return new AddressData.Builder().setAddressLine1(paramSubscriberInfo.getAddress1()).setAddressLine2(paramSubscriberInfo.getAddress2()).setLocality(paramSubscriberInfo.getCity()).setAdminArea(paramSubscriberInfo.getState()).setPostalCode(paramSubscriberInfo.getPostalCode()).setCountry(paramSubscriberInfo.getCountry()).build();
  }

  public static enum AddressInputField
  {
    static
    {
      ADDR_COUNTRY_CODE = new AddressInputField("ADDR_COUNTRY_CODE", 1);
      ADDR_POSTAL_CODE = new AddressInputField("ADDR_POSTAL_CODE", 2);
      ADDR_ADDRESS1 = new AddressInputField("ADDR_ADDRESS1", 3);
      ADDR_ADDRESS2 = new AddressInputField("ADDR_ADDRESS2", 4);
      ADDR_CITY = new AddressInputField("ADDR_CITY", 5);
      ADDR_STATE = new AddressInputField("ADDR_STATE", 6);
      ADDR_PHONE = new AddressInputField("ADDR_PHONE", 7);
      AddressInputField[] arrayOfAddressInputField = new AddressInputField[8];
      arrayOfAddressInputField[0] = PERSON_NAME;
      arrayOfAddressInputField[1] = ADDR_COUNTRY_CODE;
      arrayOfAddressInputField[2] = ADDR_POSTAL_CODE;
      arrayOfAddressInputField[3] = ADDR_ADDRESS1;
      arrayOfAddressInputField[4] = ADDR_ADDRESS2;
      arrayOfAddressInputField[5] = ADDR_CITY;
      arrayOfAddressInputField[6] = ADDR_STATE;
      arrayOfAddressInputField[7] = ADDR_PHONE;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.carrierbilling.PhoneCarrierBillingUtils
 * JD-Core Version:    0.6.2
 */
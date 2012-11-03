package com.google.android.finsky.billing;

import android.content.Context;
import android.graphics.Rect;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import com.android.i18n.addressinput.AddressData;
import com.android.i18n.addressinput.AddressData.Builder;
import com.android.i18n.addressinput.AddressField;
import com.android.i18n.addressinput.FormOptions;
import com.android.i18n.addressinput.FormOptions.Builder;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.remoting.protos.BillingAddress.Address;
import com.google.android.finsky.remoting.protos.CommonDevice.CarrierBillingInstrumentStatus;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sha1Util;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class BillingUtils
{
  public static AddressData addressDataFromInstrumentAddress(BillingAddress.Address paramAddress)
  {
    AddressData.Builder localBuilder = new AddressData.Builder();
    if (paramAddress.getPostalCountry() != null)
      localBuilder.setCountry(paramAddress.getPostalCountry());
    if (paramAddress.getAddressLine1() != null)
      localBuilder.setAddressLine1(paramAddress.getAddressLine1());
    if (paramAddress.getAddressLine2() != null)
      localBuilder.setAddressLine2(paramAddress.getAddressLine2());
    if (paramAddress.getState() != null)
      localBuilder.setAdminArea(paramAddress.getState());
    if (paramAddress.getCity() != null)
      localBuilder.setLocality(paramAddress.getCity());
    if (paramAddress.getDependentLocality() != null)
      localBuilder.setDependentLocality(paramAddress.getDependentLocality());
    if (paramAddress.getPostalCode() != null)
      localBuilder.setPostalCode(paramAddress.getPostalCode());
    if (paramAddress.getSortingCode() != null)
      localBuilder.setSortingCode(paramAddress.getSortingCode());
    if (paramAddress.getName() != null)
      localBuilder.setRecipient(paramAddress.getName());
    if (paramAddress.getLanguageCode() != null)
      localBuilder.setLanguageCode(paramAddress.getLanguageCode());
    return localBuilder.build();
  }

  public static VendingProtos.PurchaseMetadataResponseProto.Countries.Country findCountry(String paramString, List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country> paramList)
  {
    VendingProtos.PurchaseMetadataResponseProto.Countries.Country localCountry;
    if (!TextUtils.isEmpty(paramString))
    {
      Iterator localIterator = paramList.iterator();
      do
      {
        if (!localIterator.hasNext())
          break;
        localCountry = (VendingProtos.PurchaseMetadataResponseProto.Countries.Country)localIterator.next();
      }
      while (!paramString.equals(localCountry.getCountryCode()));
    }
    while (true)
    {
      return localCountry;
      if (paramList.size() > 0)
        localCountry = (VendingProtos.PurchaseMetadataResponseProto.Countries.Country)paramList.get(0);
      else
        localCountry = null;
    }
  }

  public static FormOptions getAddressFormOptions(AddressMode paramAddressMode)
  {
    FormOptions.Builder localBuilder = new FormOptions.Builder().hide(AddressField.COUNTRY).hide(AddressField.RECIPIENT).hide(AddressField.ORGANIZATION).hide(AddressField.DEPENDENT_LOCALITY).hide(AddressField.SORTING_CODE);
    if (paramAddressMode == AddressMode.REDUCED_ADDRESS)
    {
      localBuilder.hide(AddressField.ADDRESS_LINE_1).hide(AddressField.ADDRESS_LINE_2);
      localBuilder.hide(AddressField.LOCALITY);
      localBuilder.hide(AddressField.ADMIN_AREA).hide(AddressField.STREET_ADDRESS);
    }
    return localBuilder.build();
  }

  public static String getDefaultCountry(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = getSimCountry(paramContext);
    if (TextUtils.isEmpty(paramString))
      paramString = "US";
    return paramString;
  }

  public static int getFopVersion(CommonDevice.Instrument paramInstrument)
  {
    int i = 2;
    if (paramInstrument.getInstrumentFamily() == i)
      if (paramInstrument.hasCarrierBillingStatus())
      {
        CommonDevice.CarrierBillingInstrumentStatus localCarrierBillingInstrumentStatus = paramInstrument.getCarrierBillingStatus();
        if (localCarrierBillingInstrumentStatus.hasApiVersion())
          i = localCarrierBillingInstrumentStatus.getApiVersion();
      }
    while (true)
    {
      return i;
      if (FinskyLog.DEBUG)
      {
        FinskyLog.v("No api version in CarrierBillingInstrumentStatus. Return DCB_VERSION_2", new Object[0]);
        continue;
        i = 0;
      }
    }
  }

  public static String getLine1Number(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    try
    {
      boolean bool2 = ((Boolean)TelephonyManager.class.getMethod("isVoiceCapable", new Class[0]).invoke(localTelephonyManager, new Object[0])).booleanValue();
      bool1 = bool2;
      if (bool1)
      {
        str = localTelephonyManager.getLine1Number();
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        boolean bool1 = true;
        continue;
        String str = null;
      }
    }
  }

  public static String getRiskHeader()
  {
    return Sha1Util.secureHash(String.valueOf(DfeApiConfig.androidId.get()));
  }

  public static String getSimCountry(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso().toUpperCase();
  }

  public static <T extends View> T getTopMostView(ViewGroup paramViewGroup, Collection<T> paramCollection)
  {
    Pair localPair = null;
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      View localView2 = (View)localIterator.next();
      int i = getViewOffsetToChild(paramViewGroup, localView2);
      if ((localPair == null) || (i < ((Integer)localPair.first).intValue()))
        localPair = Pair.create(Integer.valueOf(i), localView2);
    }
    if (localPair != null);
    for (View localView1 = (View)localPair.second; ; localView1 = null)
      return localView1;
  }

  public static int getViewOffsetToChild(ViewGroup paramViewGroup, View paramView)
  {
    Rect localRect = new Rect();
    paramViewGroup.offsetDescendantRectToMyCoords(paramView, localRect);
    return localRect.top;
  }

  public static BillingAddress.Address instrumentAddressFromAddressData(AddressData paramAddressData, List<Integer> paramList)
  {
    BillingAddress.Address localAddress = new BillingAddress.Address();
    if ((paramList.contains(Integer.valueOf(10))) && (paramAddressData.getPostalCountry() != null))
      localAddress.setPostalCountry(paramAddressData.getPostalCountry());
    if ((paramList.contains(Integer.valueOf(5))) && (paramAddressData.getAddressLine1() != null))
      localAddress.setAddressLine1(paramAddressData.getAddressLine1());
    if ((paramList.contains(Integer.valueOf(6))) && (paramAddressData.getAddressLine2() != null))
      localAddress.setAddressLine2(paramAddressData.getAddressLine2());
    if ((paramList.contains(Integer.valueOf(8))) && (paramAddressData.getAdministrativeArea() != null))
      localAddress.setState(paramAddressData.getAdministrativeArea());
    if ((paramList.contains(Integer.valueOf(7))) && (paramAddressData.getLocality() != null))
      localAddress.setCity(paramAddressData.getLocality());
    if ((paramList.contains(Integer.valueOf(11))) && (paramAddressData.getDependentLocality() != null))
      localAddress.setDependentLocality(paramAddressData.getDependentLocality());
    if ((paramList.contains(Integer.valueOf(9))) && (paramAddressData.getPostalCode() != null))
      localAddress.setPostalCode(paramAddressData.getPostalCode());
    if (paramAddressData.getSortingCode() != null)
      localAddress.setSortingCode(paramAddressData.getSortingCode());
    if ((paramList.contains(Integer.valueOf(4))) && (paramAddressData.getRecipient() != null))
      localAddress.setName(paramAddressData.getRecipient());
    if (paramAddressData.getLanguageCode() != null)
      localAddress.setLanguageCode(paramAddressData.getLanguageCode());
    return localAddress;
  }

  public static boolean isEmptyOrSpaces(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (paramCharSequence.length() == 0) || (paramCharSequence.toString().trim().length() == 0));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public static String replaceLanguageAndRegion(String paramString)
  {
    if ((paramString.contains("%lang%")) || (paramString.contains("%region%")))
    {
      Locale localLocale = Locale.getDefault();
      paramString = paramString.replace("%lang%", localLocale.getLanguage().toLowerCase()).replace("%region%", localLocale.getCountry().toLowerCase());
    }
    return paramString;
  }

  public static String replaceLocale(String paramString)
  {
    if (paramString.contains("%locale%"))
    {
      Locale localLocale = Locale.getDefault();
      paramString = paramString.replace("%locale%", localLocale.getLanguage() + "_" + localLocale.getCountry().toLowerCase());
    }
    return paramString;
  }

  public static enum AddressMode
  {
    static
    {
      AddressMode[] arrayOfAddressMode = new AddressMode[2];
      arrayOfAddressMode[0] = FULL_ADDRESS;
      arrayOfAddressMode[1] = REDUCED_ADDRESS;
    }
  }

  public static enum CreateInstrumentUiMode
  {
    static
    {
      PROMO_OFFER = new CreateInstrumentUiMode("PROMO_OFFER", 2);
      CreateInstrumentUiMode[] arrayOfCreateInstrumentUiMode = new CreateInstrumentUiMode[3];
      arrayOfCreateInstrumentUiMode[0] = INTERNAL;
      arrayOfCreateInstrumentUiMode[1] = SETUP_WIZARD;
      arrayOfCreateInstrumentUiMode[2] = PROMO_OFFER;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.BillingUtils
 * JD-Core Version:    0.6.2
 */
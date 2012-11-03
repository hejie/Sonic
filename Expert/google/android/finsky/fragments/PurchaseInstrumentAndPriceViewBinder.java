package com.google.android.finsky.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.CheckoutPurchase;
import com.google.android.finsky.billing.CheckoutPurchase.State;
import com.google.android.finsky.billing.Instrument;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo;
import com.google.android.finsky.remoting.protos.Buy.BuyResponse.CheckoutInfo.CheckoutOption;
import com.google.android.finsky.remoting.protos.Buy.LineItem;
import com.google.android.finsky.remoting.protos.Buy.Money;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.remoting.protos.CommonDevice.TopupInfo;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PurchaseInstrumentAndPriceViewBinder
{
  private static final Integer TAG_DYNAMIC_ROW = Integer.valueOf(1);
  private TextView mAddInstrumentHint;
  private CheckoutPurchase mCheckoutPurchase;
  private Fragment mFragment;
  private View mInstrumentAndPrice;
  private InstrumentFactory mInstrumentFactory;
  private Spinner mInstrumentSpinner;
  private InstrumentAdapter mInstrumentSpinnerAdapter;
  private boolean mIsUserInitiatedSelection;
  private View mLoadingIndicator;
  private final OnInstrumentSelectedListener mOnInstrumentSelectedListener;
  private TableLayout mPriceTable;
  private boolean mPriceTableExpanded;
  private ViewGroup mPriceTableSummaryRow;
  private boolean mShowInstrumentAndPrice = false;
  private int purchaseSpinnerDescriptionBottomPadding;
  private int purchaseSpinnerDescriptionRightPadding;
  private int purchaseSpinnerDescriptionTopPadding;
  private int purchaseSpinnerLeftPadding;
  private int purchaseSpinnerRightPadding;

  public PurchaseInstrumentAndPriceViewBinder(OnInstrumentSelectedListener paramOnInstrumentSelectedListener)
  {
    this.mOnInstrumentSelectedListener = paramOnInstrumentSelectedListener;
  }

  private int addInstrumentsWithSpecificFamilyFirst(InstrumentAdapter paramInstrumentAdapter, CheckoutPurchase paramCheckoutPurchase, int paramInt)
  {
    List localList1 = paramCheckoutPurchase.getInstruments();
    List localList2 = paramCheckoutPurchase.getEligibleInstruments();
    Instrument localInstrument1 = paramCheckoutPurchase.getDefaultInstrument();
    Object localObject = null;
    Iterator localIterator1 = localList1.iterator();
    while (localIterator1.hasNext())
    {
      Instrument localInstrument5 = (Instrument)localIterator1.next();
      if (localInstrument5.getInstrumentFamily() == paramInt)
      {
        ExistingInstrumentSelectorItem localExistingInstrumentSelectorItem2 = new ExistingInstrumentSelectorItem(localInstrument5);
        paramInstrumentAdapter.add(localExistingInstrumentSelectorItem2);
        if ((localInstrument1 != null) && (localInstrument5.equals(localInstrument1)))
          localObject = localExistingInstrumentSelectorItem2;
      }
    }
    Iterator localIterator2 = localList2.iterator();
    while (localIterator2.hasNext())
    {
      CommonDevice.Instrument localInstrument4 = (CommonDevice.Instrument)localIterator2.next();
      if (localInstrument4.getInstrumentFamily() == paramInt)
      {
        AddInstrumentSelectorItem localAddInstrumentSelectorItem2 = new AddInstrumentSelectorItem(localInstrument4);
        paramInstrumentAdapter.add(localAddInstrumentSelectorItem2);
        if ((localObject == null) && (localAddInstrumentSelectorItem2.isEnabled()))
          localObject = localAddInstrumentSelectorItem2;
      }
    }
    Iterator localIterator3 = localList1.iterator();
    while (localIterator3.hasNext())
    {
      Instrument localInstrument3 = (Instrument)localIterator3.next();
      if (localInstrument3.getInstrumentFamily() != paramInt)
      {
        ExistingInstrumentSelectorItem localExistingInstrumentSelectorItem1 = new ExistingInstrumentSelectorItem(localInstrument3);
        paramInstrumentAdapter.add(localExistingInstrumentSelectorItem1);
        if ((localInstrument1 != null) && (localInstrument3.equals(localInstrument1)))
          localObject = localExistingInstrumentSelectorItem1;
      }
    }
    Iterator localIterator4 = localList2.iterator();
    while (localIterator4.hasNext())
    {
      CommonDevice.Instrument localInstrument2 = (CommonDevice.Instrument)localIterator4.next();
      if (localInstrument2.getInstrumentFamily() != paramInt)
      {
        AddInstrumentSelectorItem localAddInstrumentSelectorItem1 = new AddInstrumentSelectorItem(localInstrument2);
        paramInstrumentAdapter.add(localAddInstrumentSelectorItem1);
        if (localObject == null)
          localObject = localAddInstrumentSelectorItem1;
      }
    }
    if (localObject != null);
    for (int i = paramInstrumentAdapter.getPosition(localObject); ; i = -1)
      return i;
  }

  private void initPurchaseSpinnerDimens()
  {
    Resources localResources = this.mFragment.getActivity().getResources();
    this.purchaseSpinnerLeftPadding = localResources.getDimensionPixelSize(2131427375);
    this.purchaseSpinnerRightPadding = localResources.getDimensionPixelSize(2131427376);
    this.purchaseSpinnerDescriptionTopPadding = localResources.getDimensionPixelSize(2131427377);
    this.purchaseSpinnerDescriptionRightPadding = localResources.getDimensionPixelSize(2131427378);
    this.purchaseSpinnerDescriptionBottomPadding = localResources.getDimensionPixelSize(2131427379);
  }

  private void resetPriceTable()
  {
    ArrayList localArrayList = Lists.newArrayList();
    for (int i = 0; i < this.mPriceTable.getChildCount(); i++)
      if (this.mPriceTable.getChildAt(i).getTag() == TAG_DYNAMIC_ROW)
        localArrayList.add(this.mPriceTable.getChildAt(i));
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      View localView = (View)localIterator.next();
      this.mPriceTable.removeView(localView);
    }
  }

  private void setSpinnerSelection(int paramInt)
  {
    this.mIsUserInitiatedSelection = false;
    this.mInstrumentSpinner.setSelection(paramInt);
  }

  private void setupPriceRow(Buy.LineItem paramLineItem, View paramView, boolean paramBoolean, int paramInt)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131231147);
    localTextView1.setText(paramLineItem.getName());
    TextView localTextView2 = (TextView)paramView.findViewById(2131231148);
    if (paramLineItem.hasAmount())
      localTextView2.setText(paramLineItem.getAmount().getFormattedAmount());
    if (paramLineItem.hasOffer())
      localTextView2.setText(paramLineItem.getOffer().getFormattedAmount());
    if (paramBoolean)
    {
      localTextView1.setTypeface(null, 1);
      localTextView2.setTypeface(null, 1);
    }
    int i = localTextView2.getPaddingLeft();
    int j = localTextView2.getPaddingRight();
    int k = localTextView2.getPaddingTop();
    int m = localTextView2.getPaddingBottom();
    localTextView2.setBackgroundResource(paramInt);
    localTextView2.setPadding(i, k, j, m);
  }

  private void setupPriceTable(List<Buy.LineItem> paramList, Buy.LineItem paramLineItem)
  {
    setupPriceTable(paramList, paramLineItem, paramLineItem);
  }

  private void setupPriceTable(final List<Buy.LineItem> paramList, final Buy.LineItem paramLineItem1, final Buy.LineItem paramLineItem2)
  {
    boolean bool = true;
    if (this.mPriceTableExpanded)
    {
      this.mPriceTable.setVisibility(0);
      this.mPriceTableSummaryRow.setVisibility(8);
      resetPriceTable();
      View.OnClickListener local2 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int i = 0;
          PurchaseInstrumentAndPriceViewBinder.access$1302(PurchaseInstrumentAndPriceViewBinder.this, false);
          if ((PurchaseInstrumentAndPriceViewBinder.this.mPriceTable.hasFocus()) || (PurchaseInstrumentAndPriceViewBinder.this.mPriceTable.getFocusedChild() != null))
            i = 1;
          PurchaseInstrumentAndPriceViewBinder.this.setupPriceTable(paramList, paramLineItem1, paramLineItem2);
          if (i != 0)
            PurchaseInstrumentAndPriceViewBinder.this.mPriceTableSummaryRow.requestFocus();
        }
      };
      for (int j = -1 + paramList.size(); j >= 0; j--)
      {
        View localView2 = this.mFragment.getActivity().getLayoutInflater().inflate(2130968785, this.mPriceTable, false);
        localView2.setTag(TAG_DYNAMIC_ROW);
        setupPriceRow((Buy.LineItem)paramList.get(j), localView2, false, 0);
        localView2.setOnClickListener(local2);
        this.mPriceTable.addView(localView2, 0);
      }
      View localView1 = this.mPriceTable.findViewById(2131231146);
      setupPriceRow(paramLineItem1, localView1, bool, 0);
      localView1.setOnClickListener(local2);
      return;
    }
    this.mPriceTable.setVisibility(8);
    this.mPriceTableSummaryRow.setVisibility(0);
    label189: label212: ViewGroup localViewGroup;
    if (paramList.size() >= 2)
    {
      if (!bool)
        break label256;
      this.mPriceTableSummaryRow.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PurchaseInstrumentAndPriceViewBinder.access$1302(PurchaseInstrumentAndPriceViewBinder.this, true);
          boolean bool = PurchaseInstrumentAndPriceViewBinder.this.mPriceTableSummaryRow.isFocused();
          PurchaseInstrumentAndPriceViewBinder.this.setupPriceTable(paramList, paramLineItem1, paramLineItem2);
          if (bool)
          {
            if (PurchaseInstrumentAndPriceViewBinder.this.mPriceTable.getChildCount() <= 0)
              break label72;
            PurchaseInstrumentAndPriceViewBinder.this.mPriceTable.getChildAt(0).requestFocus();
          }
          while (true)
          {
            return;
            label72: PurchaseInstrumentAndPriceViewBinder.this.mPriceTable.findViewById(2131231146).requestFocus();
          }
        }
      });
      this.mPriceTableSummaryRow.setClickable(bool);
      localViewGroup = this.mPriceTableSummaryRow;
      if (!bool)
        break label267;
    }
    label256: label267: for (int i = 2130837753; ; i = 2130837593)
    {
      setupPriceRow(paramLineItem2, localViewGroup, false, i);
      break;
      bool = false;
      break label189;
      this.mPriceTableSummaryRow.setOnClickListener(null);
      break label212;
    }
  }

  private void updatePriceFromInstrument(Instrument paramInstrument)
  {
    if (paramInstrument != null)
    {
      Buy.BuyResponse.CheckoutInfo.CheckoutOption localCheckoutOption = paramInstrument.getCheckoutOption();
      if ((localCheckoutOption != null) && (localCheckoutOption.hasTotal()))
      {
        ArrayList localArrayList = Lists.newArrayList();
        localArrayList.addAll(localCheckoutOption.getItemList());
        localArrayList.addAll(localCheckoutOption.getSubItemList());
        setupPriceTable(localArrayList, localCheckoutOption.getTotal(), localCheckoutOption.getSummary());
      }
    }
    while (true)
    {
      return;
      if ((this.mCheckoutPurchase.getState() == CheckoutPurchase.State.PREPARED) && (!this.mCheckoutPurchase.hasAddInstrumentHintText()))
      {
        Buy.LineItem localLineItem1 = this.mCheckoutPurchase.getCheckoutInfo().getItem();
        Buy.LineItem localLineItem2 = new Buy.LineItem();
        if (localLineItem1.hasAmount())
          localLineItem2.setAmount(localLineItem1.getAmount());
        if (localLineItem1.hasOffer())
          localLineItem2.setOffer(localLineItem1.getOffer());
        localLineItem2.setName(this.mFragment.getActivity().getString(2131165611));
        setupPriceTable(Lists.newArrayList(new Buy.LineItem[] { localLineItem1 }), localLineItem2);
      }
    }
  }

  public void bind(ViewGroup paramViewGroup, CheckoutPurchase paramCheckoutPurchase)
  {
    this.mCheckoutPurchase = paramCheckoutPurchase;
    this.mInstrumentAndPrice = paramViewGroup.findViewById(2131231170);
    this.mLoadingIndicator = paramViewGroup.findViewById(2131230808);
    this.mPriceTable = ((TableLayout)paramViewGroup.findViewById(2131231173));
    this.mPriceTableSummaryRow = ((ViewGroup)paramViewGroup.findViewById(2131231172));
    this.mInstrumentSpinner = ((Spinner)paramViewGroup.findViewById(2131231171));
    this.mInstrumentSpinnerAdapter = new InstrumentAdapter(this.mInstrumentSpinner);
    this.mAddInstrumentHint = ((TextView)paramViewGroup.findViewById(2131231169));
    if (this.mCheckoutPurchase != null)
      if (!this.mCheckoutPurchase.isInstrumentRequired())
      {
        this.mInstrumentSpinner.setVisibility(8);
        this.mShowInstrumentAndPrice = true;
        updatePriceFromInstrument(null);
      }
    while (true)
    {
      return;
      if (this.mCheckoutPurchase.hasAddInstrumentHintText())
      {
        this.mAddInstrumentHint.setText(this.mCheckoutPurchase.getAddInstrumentHintText());
        this.mAddInstrumentHint.setVisibility(0);
        this.mInstrumentAndPrice.setVisibility(4);
        this.mShowInstrumentAndPrice = false;
        this.mInstrumentSpinner.setVisibility(4);
        this.mPriceTable.setVisibility(8);
        this.mPriceTableSummaryRow.setVisibility(4);
      }
      else
      {
        this.mAddInstrumentHint.setVisibility(8);
        this.mInstrumentAndPrice.setVisibility(0);
        this.mShowInstrumentAndPrice = true;
        this.mInstrumentSpinner.setVisibility(0);
        this.mPriceTableSummaryRow.setVisibility(0);
        int i = addInstrumentsWithSpecificFamilyFirst(this.mInstrumentSpinnerAdapter, paramCheckoutPurchase, 2);
        this.mInstrumentSpinner.setAdapter(this.mInstrumentSpinnerAdapter);
        this.mInstrumentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            ((PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem)PurchaseInstrumentAndPriceViewBinder.this.mInstrumentSpinnerAdapter.getItem(paramAnonymousInt)).onSelected(PurchaseInstrumentAndPriceViewBinder.this.mIsUserInitiatedSelection);
            PurchaseInstrumentAndPriceViewBinder.access$1002(PurchaseInstrumentAndPriceViewBinder.this, true);
          }

          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
          {
            PurchaseInstrumentAndPriceViewBinder.this.mOnInstrumentSelectedListener.onNothingSelected();
          }
        });
        if (i < 0)
          i = 0;
        setSpinnerSelection(i);
        showInstrumentAndPrice();
        continue;
        InstrumentAdapter localInstrumentAdapter = new InstrumentAdapter(this.mInstrumentSpinner);
        localInstrumentAdapter.add(new PlaceholderInstrumentSelectorItem(null));
        this.mInstrumentSpinner.setAdapter(localInstrumentAdapter);
        switchToLoading(-1);
      }
    }
  }

  public void init(Fragment paramFragment, InstrumentFactory paramInstrumentFactory, Bundle paramBundle)
  {
    this.mFragment = paramFragment;
    this.mInstrumentFactory = paramInstrumentFactory;
    if ((paramBundle != null) && (paramBundle.getBoolean("expanded")));
    for (boolean bool = true; ; bool = false)
    {
      this.mPriceTableExpanded = bool;
      initPurchaseSpinnerDimens();
      return;
    }
  }

  public void saveState(Bundle paramBundle)
  {
    paramBundle.putBoolean("expanded", this.mPriceTableExpanded);
  }

  public void selectAddInstrument(int paramInt1, int paramInt2)
  {
    for (int i = 0; ; i++)
      if (i < this.mInstrumentSpinnerAdapter.getCount())
      {
        InstrumentSelectorItem localInstrumentSelectorItem = (InstrumentSelectorItem)this.mInstrumentSpinnerAdapter.getItem(i);
        if ((localInstrumentSelectorItem instanceof AddInstrumentSelectorItem))
        {
          CommonDevice.Instrument localInstrument = ((AddInstrumentSelectorItem)localInstrumentSelectorItem).mEligibleInstrument;
          if ((localInstrument.getInstrumentFamily() == paramInt1) && (BillingUtils.getFopVersion(localInstrument) == paramInt2))
            setSpinnerSelection(i);
        }
      }
      else
      {
        return;
      }
  }

  public void selectInstrument(String paramString)
  {
    if (paramString == null)
      setSpinnerSelection(0);
    while (true)
    {
      return;
      for (int i = 0; ; i++)
      {
        if (i >= this.mInstrumentSpinnerAdapter.getCount())
          break label74;
        InstrumentSelectorItem localInstrumentSelectorItem = (InstrumentSelectorItem)this.mInstrumentSpinnerAdapter.getItem(i);
        if ((localInstrumentSelectorItem.getInstrument() != null) && (localInstrumentSelectorItem.getInstrument().getInstrumentId().equals(paramString)))
        {
          setSpinnerSelection(i);
          break;
        }
      }
      label74: setSpinnerSelection(0);
    }
  }

  public void showInstrumentAndPrice()
  {
    this.mLoadingIndicator.setVisibility(8);
    if (this.mShowInstrumentAndPrice)
      this.mInstrumentAndPrice.setVisibility(0);
  }

  public void switchToFreeUi()
  {
    this.mInstrumentAndPrice.setVisibility(8);
    this.mLoadingIndicator.setVisibility(8);
  }

  public void switchToLoading(int paramInt)
  {
    if (paramInt >= 0)
      ((TextView)this.mLoadingIndicator.findViewById(2131231175)).setText(paramInt);
    this.mLoadingIndicator.setVisibility(0);
    this.mInstrumentAndPrice.setVisibility(4);
    this.mAddInstrumentHint.setVisibility(8);
  }

  private class AddInstrumentSelectorItem
    implements PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem
  {
    private CommonDevice.Instrument mEligibleInstrument;

    public AddInstrumentSelectorItem(CommonDevice.Instrument arg2)
    {
      Object localObject;
      this.mEligibleInstrument = localObject;
    }

    public Instrument getInstrument()
    {
      return null;
    }

    public String getMessage()
    {
      String str = null;
      if ((this.mEligibleInstrument.getInstrumentFamily() == 7) && (this.mEligibleInstrument.hasTopupInfo()) && (this.mEligibleInstrument.getTopupInfo().hasSubtitle()))
        str = this.mEligibleInstrument.getTopupInfo().getSubtitle();
      return str;
    }

    public boolean isEnabled()
    {
      return PurchaseInstrumentAndPriceViewBinder.this.mInstrumentFactory.canAdd(this.mEligibleInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(this.mEligibleInstrument));
    }

    public boolean isValidSelection()
    {
      return true;
    }

    public void onSelected(boolean paramBoolean)
    {
      PurchaseInstrumentAndPriceViewBinder.this.updatePriceFromInstrument(null);
      PurchaseInstrumentAndPriceViewBinder.this.mOnInstrumentSelectedListener.onAddInstrument(this.mEligibleInstrument, paramBoolean);
    }

    public String toString()
    {
      return PurchaseInstrumentAndPriceViewBinder.this.mInstrumentFactory.getAddText(this.mEligibleInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(this.mEligibleInstrument));
    }
  }

  private class ExistingInstrumentSelectorItem
    implements PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem
  {
    private final Instrument mInstrument;

    public ExistingInstrumentSelectorItem(Instrument arg2)
    {
      Object localObject;
      this.mInstrument = localObject;
    }

    public Instrument getInstrument()
    {
      return this.mInstrument;
    }

    public String getMessage()
    {
      if ((this.mInstrument.hasMessages()) && (this.mInstrument.getMessages().size() > 0));
      for (String str = (String)this.mInstrument.getMessages().get(0); ; str = null)
        return str;
    }

    public boolean isEnabled()
    {
      return this.mInstrument.isValid();
    }

    public boolean isValidSelection()
    {
      return true;
    }

    public void onSelected(boolean paramBoolean)
    {
      PurchaseInstrumentAndPriceViewBinder.this.updatePriceFromInstrument(this.mInstrument);
      PurchaseInstrumentAndPriceViewBinder.this.mOnInstrumentSelectedListener.onInstrumentSelected(this.mInstrument);
    }

    public String toString()
    {
      return this.mInstrument.toString();
    }
  }

  private class InstrumentAdapter extends ArrayAdapter<PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem>
  {
    private final Spinner mSpinner;

    public InstrumentAdapter(Spinner arg2)
    {
      super(2130968800, 2131231067);
      Object localObject;
      this.mSpinner = localObject;
    }

    public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem localInstrumentSelectorItem = (PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem)getItem(paramInt);
      View localView = super.getView(paramInt, paramView, paramViewGroup);
      localView.setPadding(PurchaseInstrumentAndPriceViewBinder.this.purchaseSpinnerLeftPadding, 0, PurchaseInstrumentAndPriceViewBinder.this.purchaseSpinnerRightPadding, 0);
      TextView localTextView1 = (TextView)localView.findViewById(2131231067);
      localTextView1.setPadding(0, PurchaseInstrumentAndPriceViewBinder.this.purchaseSpinnerDescriptionTopPadding, PurchaseInstrumentAndPriceViewBinder.this.purchaseSpinnerDescriptionRightPadding, PurchaseInstrumentAndPriceViewBinder.this.purchaseSpinnerDescriptionBottomPadding);
      RadioButton localRadioButton = (RadioButton)localView.findViewById(2131231068);
      boolean bool;
      TextView localTextView2;
      if (localInstrumentSelectorItem.isValidSelection())
      {
        localTextView1.setTypeface(Typeface.DEFAULT);
        localRadioButton.setVisibility(0);
        if (this.mSpinner.getSelectedItemPosition() == paramInt)
        {
          bool = true;
          localRadioButton.setChecked(bool);
          localTextView1.setEnabled(localInstrumentSelectorItem.isEnabled());
          localTextView2 = (TextView)localView.findViewById(2131231176);
          String str = localInstrumentSelectorItem.getMessage();
          if (str == null)
            break label208;
          localTextView2.setText(str);
          localTextView2.setEnabled(localInstrumentSelectorItem.isEnabled());
          localTextView2.setVisibility(0);
        }
      }
      while (true)
      {
        return localView;
        bool = false;
        break;
        label208: localTextView2.setVisibility(8);
        continue;
        localTextView1.setTypeface(Typeface.DEFAULT_BOLD);
        localRadioButton.setVisibility(8);
      }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem localInstrumentSelectorItem = (PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem)getItem(paramInt);
      if (paramView == null)
        paramView = ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2130968801, paramViewGroup, false);
      TextView localTextView = (TextView)paramView;
      localTextView.setText(localInstrumentSelectorItem.toString());
      if (localInstrumentSelectorItem.isValidSelection())
        localTextView.setTypeface(Typeface.DEFAULT);
      while (true)
      {
        return paramView;
        localTextView.setTypeface(Typeface.DEFAULT_BOLD);
      }
    }

    public boolean isEnabled(int paramInt)
    {
      if ((super.isEnabled(paramInt)) && (((PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem)getItem(paramInt)).isEnabled()));
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }

  private static abstract interface InstrumentSelectorItem
  {
    public abstract Instrument getInstrument();

    public abstract String getMessage();

    public abstract boolean isEnabled();

    public abstract boolean isValidSelection();

    public abstract void onSelected(boolean paramBoolean);
  }

  public static abstract interface OnInstrumentSelectedListener
  {
    public abstract void onAddInstrument(CommonDevice.Instrument paramInstrument, boolean paramBoolean);

    public abstract void onInstrumentSelected(Instrument paramInstrument);

    public abstract void onNothingSelected();
  }

  private class PlaceholderInstrumentSelectorItem
    implements PurchaseInstrumentAndPriceViewBinder.InstrumentSelectorItem
  {
    private PlaceholderInstrumentSelectorItem()
    {
    }

    public Instrument getInstrument()
    {
      return null;
    }

    public String getMessage()
    {
      return null;
    }

    public boolean isEnabled()
    {
      return false;
    }

    public boolean isValidSelection()
    {
      return false;
    }

    public void onSelected(boolean paramBoolean)
    {
      PurchaseInstrumentAndPriceViewBinder.this.updatePriceFromInstrument(null);
    }

    public String toString()
    {
      return "";
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.PurchaseInstrumentAndPriceViewBinder
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.activities;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.google.android.finsky.billing.BillingUtils;
import com.google.android.finsky.billing.InstrumentFactory;
import com.google.android.finsky.layout.InstrumentFamilyRow;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.utils.ParcelableProto;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectInstrumentFamilyDialog extends DialogFragment
{
  private ListView mSelectionList;

  public static SelectInstrumentFamilyDialog newInstance(List<CommonDevice.Instrument> paramList, InstrumentFactory paramInstrumentFactory, int paramInt1, int paramInt2)
  {
    SelectInstrumentFamilyDialog localSelectInstrumentFamilyDialog = new SelectInstrumentFamilyDialog();
    Bundle localBundle = new Bundle();
    ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = Lists.newArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      CommonDevice.Instrument localInstrument = (CommonDevice.Instrument)localIterator.next();
      localArrayList2.add(paramInstrumentFactory.getAddText(localInstrument.getInstrumentFamily(), BillingUtils.getFopVersion(localInstrument)));
      localArrayList1.add(ParcelableProto.forProto(localInstrument));
    }
    localBundle.putParcelableArrayList("instruments", localArrayList1);
    localBundle.putStringArrayList("instrument_add_text_list", localArrayList2);
    localBundle.putInt("title_resource_id", paramInt1);
    localBundle.putInt("selected_index", paramInt2);
    localSelectInstrumentFamilyDialog.setArguments(localBundle);
    return localSelectInstrumentFamilyDialog;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    final ArrayList localArrayList1 = getArguments().getParcelableArrayList("instruments");
    ArrayList localArrayList2 = getArguments().getStringArrayList("instrument_add_text_list");
    if (paramBundle != null);
    for (Integer localInteger = Integer.valueOf(paramBundle.getInt("selected_index")); ; localInteger = Integer.valueOf(getArguments().getInt("selected_index")))
    {
      int i = getArguments().getInt("title_resource_id");
      ContextThemeWrapper localContextThemeWrapper = new ContextThemeWrapper(getActivity(), 2131624000);
      InstrumentFamilyListAdapter localInstrumentFamilyListAdapter = new InstrumentFamilyListAdapter(localArrayList2, getActivity());
      this.mSelectionList = new ListView(localContextThemeWrapper);
      this.mSelectionList.setChoiceMode(1);
      this.mSelectionList.setAdapter(localInstrumentFamilyListAdapter);
      this.mSelectionList.setItemChecked(localInteger.intValue(), true);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContextThemeWrapper);
      localBuilder.setTitle(i);
      localBuilder.setView(this.mSelectionList);
      localBuilder.setNegativeButton(2131165273, null);
      localBuilder.setPositiveButton(2131165488, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          int i = SelectInstrumentFamilyDialog.this.mSelectionList.getCheckedItemPosition();
          ParcelableProto localParcelableProto = (ParcelableProto)localArrayList1.get(i);
          ((SelectInstrumentFamilyDialog.SelectInstrumentFamilyListener)SelectInstrumentFamilyDialog.this.getTargetFragment()).onInstrumentFamilySelected((CommonDevice.Instrument)localParcelableProto.getPayload());
        }
      });
      return localBuilder.create();
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("selected_index", this.mSelectionList.getCheckedItemPosition());
  }

  private static class InstrumentFamilyListAdapter extends BaseAdapter
  {
    private LayoutInflater mInflater;
    private List<String> mInstrumentFamilyAddTextList;

    public InstrumentFamilyListAdapter(List<String> paramList, Context paramContext)
    {
      this.mInstrumentFamilyAddTextList = paramList;
      this.mInflater = LayoutInflater.from(paramContext);
    }

    public int getCount()
    {
      return this.mInstrumentFamilyAddTextList.size();
    }

    public Object getItem(int paramInt)
    {
      return this.mInstrumentFamilyAddTextList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = this.mInflater.inflate(2130968717, paramViewGroup, false);
      InstrumentFamilyRow localInstrumentFamilyRow = (InstrumentFamilyRow)paramView;
      localInstrumentFamilyRow.setInstrumentFamilyDescription((String)this.mInstrumentFamilyAddTextList.get(paramInt));
      return localInstrumentFamilyRow;
    }
  }

  public static abstract interface SelectInstrumentFamilyListener
  {
    public abstract void onInstrumentFamilySelected(CommonDevice.Instrument paramInstrument);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.SelectInstrumentFamilyDialog
 * JD-Core Version:    0.6.2
 */
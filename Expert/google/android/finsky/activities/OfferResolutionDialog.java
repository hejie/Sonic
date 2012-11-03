package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.OfferRow;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.List;

public class OfferResolutionDialog extends DialogFragment
{
  private List<Document> mDocuments;
  private ListView mOfferList;
  private List<Common.Offer> mOffers;
  private int mTitleResourceId;

  private OfferResolutionListener getListener()
  {
    Fragment localFragment = getTargetFragment();
    OfferResolutionListener localOfferResolutionListener;
    if ((localFragment instanceof OfferResolutionListener))
      localOfferResolutionListener = (OfferResolutionListener)localFragment;
    while (true)
    {
      return localOfferResolutionListener;
      FragmentActivity localFragmentActivity = getActivity();
      if ((localFragmentActivity instanceof OfferResolutionListener))
        localOfferResolutionListener = (OfferResolutionListener)localFragmentActivity;
      else
        localOfferResolutionListener = null;
    }
  }

  public static OfferResolutionDialog newInstance(List<Common.Offer> paramList, ArrayList<Document> paramArrayList, int paramInt1, int paramInt2)
  {
    OfferResolutionDialog localOfferResolutionDialog = new OfferResolutionDialog();
    Bundle localBundle = new Bundle();
    int i = paramList.size();
    ParcelableProto[] arrayOfParcelableProto = new ParcelableProto[i];
    for (int j = 0; j < i; j++)
      arrayOfParcelableProto[j] = new ParcelableProto((Common.Offer)paramList.get(j));
    localBundle.putParcelableArray("offers", arrayOfParcelableProto);
    localBundle.putParcelableArrayList("documents", paramArrayList);
    localBundle.putInt("title_resource_id", paramInt1);
    localBundle.putInt("selected_offer_index", paramInt2);
    localOfferResolutionDialog.setArguments(localBundle);
    return localOfferResolutionDialog;
  }

  private void syncOkButtonState()
  {
    Button localButton = ((AlertDialog)getDialog()).getButton(-1);
    if (this.mOfferList.getCheckedItemPosition() != -1);
    for (boolean bool = true; ; bool = false)
    {
      localButton.setEnabled(bool);
      return;
    }
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    this.mOffers = Lists.newArrayList();
    for (ParcelableProto localParcelableProto : (ParcelableProto[])getArguments().getParcelableArray("offers"))
      this.mOffers.add(localParcelableProto.getPayload());
    this.mDocuments = getArguments().getParcelableArrayList("documents");
    if (paramBundle != null);
    for (Bundle localBundle = paramBundle; ; localBundle = getArguments())
    {
      ContextThemeWrapper localContextThemeWrapper = new ContextThemeWrapper(getActivity(), 2131624000);
      OfferListAdapter localOfferListAdapter = new OfferListAdapter(this.mOffers, localContextThemeWrapper);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContextThemeWrapper);
      this.mTitleResourceId = localBundle.getInt("title_resource_id", 2131165482);
      localBuilder.setTitle(this.mTitleResourceId);
      this.mOfferList = new ListView(localContextThemeWrapper);
      this.mOfferList.setChoiceMode(1);
      this.mOfferList.setAdapter(localOfferListAdapter);
      this.mOfferList.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          OfferResolutionDialog.this.syncOkButtonState();
        }
      });
      int k = localBundle.getInt("selected_offer_index", -1);
      if (k != -1)
        this.mOfferList.setItemChecked(k, true);
      localBuilder.setView(this.mOfferList);
      localBuilder.setNegativeButton(2131165273, null);
      localBuilder.setPositiveButton(2131165488, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (OfferResolutionDialog.this.mOfferList.getCheckedItemPosition() != -1)
          {
            int i = OfferResolutionDialog.this.mOfferList.getCheckedItemPosition();
            OfferResolutionDialog.OfferResolutionListener localOfferResolutionListener = OfferResolutionDialog.this.getListener();
            if (localOfferResolutionListener != null)
              localOfferResolutionListener.onOfferSelected((Document)OfferResolutionDialog.this.mDocuments.get(i), ((Common.Offer)OfferResolutionDialog.this.mOffers.get(i)).getOfferType());
          }
        }
      });
      return localBuilder.create();
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mOfferList != null)
      paramBundle.putInt("selected_offer_index", this.mOfferList.getCheckedItemPosition());
    paramBundle.putInt("title_resource_id", this.mTitleResourceId);
    super.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    super.onStart();
    syncOkButtonState();
  }

  private static class OfferListAdapter extends BaseAdapter
  {
    private LayoutInflater mInflater;
    private List<Common.Offer> mOffers;

    OfferListAdapter(List<Common.Offer> paramList, Context paramContext)
    {
      this.mOffers = paramList;
      this.mInflater = LayoutInflater.from(paramContext);
    }

    public int getCount()
    {
      return this.mOffers.size();
    }

    public Object getItem(int paramInt)
    {
      return this.mOffers.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
        paramView = this.mInflater.inflate(2130968770, paramViewGroup, false);
      OfferRow localOfferRow = (OfferRow)paramView;
      localOfferRow.setOffer((Common.Offer)this.mOffers.get(paramInt));
      return localOfferRow;
    }
  }

  public static abstract interface OfferResolutionListener
  {
    public abstract void onOfferSelected(Document paramDocument, int paramInt);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.OfferResolutionDialog
 * JD-Core Version:    0.6.2
 */
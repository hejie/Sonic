package com.google.android.finsky.billing.storedvalue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.activities.SynchronousPurchaseActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Bucket;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.SidecarFragment;
import com.google.android.finsky.fragments.SidecarFragment.Listener;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.remoting.protos.Common.Offer;
import com.google.android.finsky.remoting.protos.CommonDevice.Instrument;
import com.google.android.finsky.remoting.protos.CommonDevice.TopupInfo;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StoredValueTopUpActivity extends FragmentActivity
  implements AdapterView.OnItemClickListener, SimpleAlertDialog.Listener, SidecarFragment.Listener, ButtonBar.ClickListener
{
  private String mAccountName;
  private ButtonBar mButtonBar;
  private List<Document> mDocuments;
  private int mLastShownErrorId = 0;
  private ListSidecar mListSidecar;
  private ListView mListView;
  private View mLoadingIndicator;
  private String mSelectedDocumentFormattedAmount;

  public static Intent createIntent(String paramString, CommonDevice.Instrument paramInstrument)
  {
    Intent localIntent = null;
    if (paramInstrument.getInstrumentFamily() != 7)
      FinskyLog.w("Non-STORED_VALUE instrument passed.", new Object[0]);
    while (true)
    {
      return localIntent;
      if (!paramInstrument.hasTopupInfo())
      {
        FinskyLog.w("Instrument without TopupInfo passed.", new Object[0]);
      }
      else
      {
        localIntent = new Intent(FinskyApp.get(), StoredValueTopUpActivity.class);
        localIntent.putExtra("authAccount", paramString);
        localIntent.putExtra("list_url", paramInstrument.getTopupInfo().getOptionsListUrl());
        localIntent.putExtra("title", paramInstrument.getDisplayTitle());
      }
    }
  }

  private void syncPositiveButton()
  {
    if (this.mListView.getCheckedItemPosition() != -1);
    for (boolean bool = true; ; bool = false)
    {
      this.mButtonBar.setPositiveButtonEnabled(bool);
      return;
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool;
    if (paramInt1 == 1)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("billing_flow_error", false);
      if (paramInt2 == 0)
      {
        bool = true;
        localIntent.putExtra("billing_flow_canceled", bool);
        if (paramInt2 == -1)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = this.mSelectedDocumentFormattedAmount;
          Toast.makeText(this, getString(2131165319, arrayOfObject), 0).show();
        }
        setResult(paramInt2, localIntent);
        finish();
      }
    }
    while (true)
    {
      return;
      bool = false;
      break;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    String str = getIntent().getStringExtra("title");
    if (!TextUtils.isEmpty(str))
      setTitle(str);
    this.mLoadingIndicator = findViewById(2131230808);
    this.mListView = ((ListView)findViewById(2131230809));
    this.mButtonBar = ((ButtonBar)findViewById(2131230825));
    this.mButtonBar.setPositiveButtonTitle(2131165599);
    this.mButtonBar.setNegativeButtonTitle(2131165273);
    this.mButtonBar.setClickListener(this);
    syncPositiveButton();
    this.mAccountName = getIntent().getStringExtra("authAccount");
    if (paramBundle == null)
    {
      this.mListSidecar = ListSidecar.newInstance(this.mAccountName, getIntent().getStringExtra("list_url"));
      getSupportFragmentManager().beginTransaction().add(this.mListSidecar, "list_sidecar").commit();
    }
    while (true)
    {
      return;
      this.mSelectedDocumentFormattedAmount = paramBundle.getString("selected_document_formatted_amount");
      this.mLastShownErrorId = paramBundle.getInt("last_shown_error");
      this.mListSidecar = ((ListSidecar)getSupportFragmentManager().findFragmentByTag("list_sidecar"));
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    syncPositiveButton();
  }

  public void onNegativeButtonClick()
  {
    setResult(0);
    finish();
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
  }

  public void onPositiveButtonClick()
  {
    int i = this.mListView.getCheckedItemPosition();
    Document localDocument = (Document)this.mDocuments.get(i);
    Common.Offer localOffer = localDocument.getOffer(1);
    if (localOffer == null)
      FinskyLog.w("Document selected without PURCHASE offer. Ignoring.", new Object[0]);
    while (true)
    {
      return;
      this.mSelectedDocumentFormattedAmount = localOffer.getFormattedAmount();
      SynchronousPurchaseActivity.show(this, this.mAccountName, localDocument.getDetailsUrl(), 1, null, null, true, null, localDocument.getDocId(), 1);
    }
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    if (paramInt == 0)
    {
      setResult(0);
      finish();
    }
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    syncPositiveButton();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putInt("last_shown_error", this.mLastShownErrorId);
    paramBundle.putString("selected_document_formatted_amount", this.mSelectedDocumentFormattedAmount);
  }

  protected void onStart()
  {
    super.onStart();
    this.mListSidecar.setListener(this);
    if (this.mListSidecar.getState() == 0)
      this.mListSidecar.load();
  }

  public void onStateChange(SidecarFragment paramSidecarFragment)
  {
    this.mLoadingIndicator.setVisibility(8);
    this.mListView.setVisibility(8);
    this.mButtonBar.setVisibility(4);
    int i = paramSidecarFragment.getState();
    if (i == 2)
    {
      this.mDocuments = this.mListSidecar.getDocuments();
      ArrayList localArrayList = Lists.newArrayList(this.mDocuments.size());
      Iterator localIterator = this.mDocuments.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((Document)localIterator.next()).getTitle());
      ArrayAdapter localArrayAdapter = new ArrayAdapter(this, 17367055, localArrayList);
      this.mListView.setAdapter(localArrayAdapter);
      this.mListView.setItemsCanFocus(false);
      this.mListView.setChoiceMode(1);
      this.mListView.setOnItemClickListener(this);
      this.mListView.setVisibility(0);
      this.mButtonBar.setVisibility(0);
      syncPositiveButton();
    }
    while (true)
    {
      return;
      if (i == 1)
        this.mLoadingIndicator.setVisibility(0);
      else if (paramSidecarFragment.getState() == 3)
        if (this.mLastShownErrorId == paramSidecarFragment.getErrorInstance())
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(this.mLastShownErrorId);
          FinskyLog.d("Already showed error %d, ignoring.", arrayOfObject);
        }
        else
        {
          this.mLastShownErrorId = paramSidecarFragment.getErrorInstance();
          String str = null;
          if (paramSidecarFragment.getSubstate() == 0)
            str = ErrorStrings.get(FinskyApp.get(), this.mListSidecar.getLastError());
          while (true)
          {
            if (str == null)
              break label325;
            SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(str, 2131165599, -1);
            localSimpleAlertDialog.setCallback(null, 0, null);
            localSimpleAlertDialog.show(getSupportFragmentManager(), "error_dialog");
            break;
            if (paramSidecarFragment.getSubstate() == 1)
              str = FinskyApp.get().getString(2131165317);
          }
          label325: FinskyLog.w("Received error without error message.", new Object[0]);
          setResult(0);
          finish();
        }
    }
  }

  protected void onStop()
  {
    this.mListSidecar.setListener(null);
    super.onStop();
  }

  public static final class ListSidecar extends SidecarFragment
    implements Response.ErrorListener, OnDataChangedListener
  {
    private DfeList mDfeList;
    private VolleyError mLastError;

    private static ListSidecar newInstance(String paramString1, String paramString2)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("list_url", paramString2);
      localBundle.putString("authAccount", paramString1);
      ListSidecar localListSidecar = new ListSidecar();
      localListSidecar.setArguments(localBundle);
      return localListSidecar;
    }

    public List<Document> getDocuments()
    {
      return ((Bucket)this.mDfeList.getBucketList().get(0)).getChildren();
    }

    public VolleyError getLastError()
    {
      return this.mLastError;
    }

    public void load()
    {
      this.mDfeList.startLoadItems();
      setState(1, 0);
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      String str = getArguments().getString("authAccount");
      DfeApi localDfeApi = FinskyApp.get().getDfeApi(str);
      if (this.mDfeList == null)
        this.mDfeList = new DfeList(localDfeApi, getArguments().getString("list_url"), false);
      while (true)
      {
        this.mDfeList.addDataChangedListener(this);
        this.mDfeList.addErrorListener(this);
        return;
        this.mDfeList.setDfeApi(localDfeApi);
      }
    }

    public void onDataChanged()
    {
      if ((this.mDfeList.getBucketList().size() > 0) && (((Bucket)this.mDfeList.getBucketList().get(0)).getChildCount() > 0))
        setState(2, 0);
      while (true)
      {
        return;
        setState(3, 1);
      }
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      this.mLastError = paramVolleyError;
      setState(3, 0);
    }

    public void onSaveInstanceState(Bundle paramBundle)
    {
      super.onSaveInstanceState(paramBundle);
      paramBundle.putParcelable("list", this.mDfeList);
    }

    protected void restoreFromSavedInstanceState(Bundle paramBundle)
    {
      super.restoreFromSavedInstanceState(paramBundle);
      this.mDfeList = ((DfeList)paramBundle.getParcelable("list"));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.billing.storedvalue.StoredValueTopUpActivity
 * JD-Core Version:    0.6.2
 */
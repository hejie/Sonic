package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ViewPagerTab;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeModel;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.installer.InstallerListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.ErrorStrings;

public abstract class MyAppsTab<T extends DfeModel>
  implements ViewPagerTab, View.OnClickListener, OnDataChangedListener, Response.ErrorListener, Libraries.Listener, InstallerListener
{
  protected final Context mContext;
  protected final OpenDetailsHandler mDetailsOpener;
  protected final DfeApi mDfeApi;
  protected T mDfeModel;
  protected final Installer mInstaller;
  private boolean mIsSelectedTab = false;
  private VolleyError mLastVolleyError;
  protected final LayoutInflater mLayoutInflater;
  protected final Libraries mLibraries;
  protected final Bundle mSavedInstanceState;
  protected final boolean mUseTwoColumnLayout;

  protected MyAppsTab(Context paramContext, DfeApi paramDfeApi, OpenDetailsHandler paramOpenDetailsHandler, Bundle paramBundle)
  {
    this.mContext = paramContext;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mDfeApi = paramDfeApi;
    this.mDetailsOpener = paramOpenDetailsHandler;
    this.mInstaller = FinskyApp.get().getInstaller();
    this.mInstaller.addListener(this);
    this.mLibraries = FinskyApp.get().getLibraries();
    this.mLibraries.addListener(this);
    this.mUseTwoColumnLayout = paramContext.getResources().getBoolean(2131296262);
    this.mSavedInstanceState = new Bundle();
    if (paramBundle != null)
      this.mSavedInstanceState.putAll(paramBundle);
  }

  private final String getKey(String paramString)
  {
    return getClass().getSimpleName() + "." + paramString;
  }

  private void makeDefaultSelection()
  {
    ListView localListView = getListView();
    MyAppsListAdapter localMyAppsListAdapter = getAdapter();
    int j;
    int k;
    if (localListView != null)
    {
      j = localListView.getCheckedItemPosition();
      if (j >= localMyAppsListAdapter.getCount())
        j = -1 + localMyAppsListAdapter.getCount();
      if (j >= 0)
        if (localMyAppsListAdapter.getDocument(j) == null)
        {
          k = j;
          if (k >= localMyAppsListAdapter.getCount())
            break label105;
          if (localMyAppsListAdapter.getItem(k) != null)
            selectDocumentAtPosition(k);
        }
    }
    label139: 
    while (true)
    {
      return;
      k++;
      break;
      selectDocumentAtPosition(j);
      continue;
      label105: for (int i = 0; ; i++)
      {
        if (i >= localMyAppsListAdapter.getCount())
          break label139;
        if (localMyAppsListAdapter.getItem(i) != null)
        {
          selectDocumentAtPosition(i);
          break;
        }
      }
    }
  }

  private void saveCurrentSelection()
  {
    if (getListView() == null);
    while (true)
    {
      return;
      int i = getListView().getCheckedItemPosition();
      if ((i != -1) && (i < getAdapter().getCount()))
      {
        Document localDocument = getAdapter().getDocument(i);
        if (localDocument != null)
          this.mSavedInstanceState.putString(getKey("checked_document_id"), localDocument.getDocId());
      }
    }
  }

  private void selectDocument(String paramString)
  {
    if (!isDataReady());
    while (true)
    {
      return;
      for (int i = 0; ; i++)
      {
        if (i >= getAdapter().getCount())
          break label63;
        Document localDocument = getAdapter().getDocument(i);
        if ((localDocument != null) && (localDocument.getDocId().equals(paramString)))
        {
          selectDocumentAtPosition(i);
          break;
        }
      }
      label63: makeDefaultSelection();
    }
  }

  private void selectDocumentAtPosition(int paramInt)
  {
    Document localDocument = getAdapter().getDocument(paramInt);
    if (localDocument != null)
    {
      this.mDetailsOpener.openDocDetails(localDocument);
      getListView().setItemChecked(paramInt, true);
      saveCurrentSelection();
    }
  }

  protected void clearState()
  {
    if (this.mDfeModel != null)
    {
      this.mDfeModel.removeDataChangedListener(this);
      this.mDfeModel.removeErrorListener(this);
      this.mDfeModel = null;
    }
  }

  protected abstract MyAppsListAdapter getAdapter();

  protected abstract Document getDocumentForView(View paramView);

  protected abstract View getFullView();

  protected abstract ListView getListView();

  protected int getPositionForView(View paramView)
  {
    int i = -1;
    if (getDocumentForView(paramView) == null);
    label62: 
    while (true)
    {
      return i;
      View localView = paramView;
      ListView localListView = getListView();
      while (true)
      {
        if (localView == null)
          break label62;
        ViewParent localViewParent = localView.getParent();
        if (localViewParent == localListView)
        {
          i = localListView.getPositionForView(paramView);
          break;
        }
        if (!(localViewParent instanceof View))
          break;
        localView = (View)localViewParent;
      }
    }
  }

  protected final boolean isDataReady()
  {
    if ((this.mDfeModel != null) && (this.mDfeModel.isReady()));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  final void loadData()
  {
    requestData();
    if (isDataReady())
      onDataChanged();
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onClick(View paramView)
  {
    ListView localListView = getListView();
    int i = getPositionForView(paramView);
    boolean bool;
    if (i != -1)
    {
      if (localListView.getChoiceMode() != 3)
        break label49;
      if (localListView.isItemChecked(i))
        break label43;
      bool = true;
      localListView.setItemChecked(i, bool);
    }
    while (true)
    {
      return;
      label43: bool = false;
      break;
      label49: selectDocumentAtPosition(i);
    }
  }

  public void onDataChanged()
  {
    this.mLastVolleyError = null;
  }

  public void onDestroy()
  {
    clearState();
    this.mInstaller.removeListener(this);
    this.mLibraries.removeListener(this);
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    this.mLastVolleyError = paramVolleyError;
    syncViewToState();
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
  }

  public Bundle onSaveInstanceState()
  {
    saveCurrentSelection();
    if (getListView() != null)
      this.mSavedInstanceState.putParcelable("MyAppsTab.ListParcelKey", getListView().onSaveInstanceState());
    return this.mSavedInstanceState;
  }

  protected abstract void requestData();

  protected void restoreTabSelection()
  {
    if ((isDataReady()) && (getListView() != null) && (this.mSavedInstanceState.containsKey("MyAppsTab.ListParcelKey")))
    {
      getListView().onRestoreInstanceState(this.mSavedInstanceState.getParcelable("MyAppsTab.ListParcelKey"));
      this.mSavedInstanceState.remove("MyAppsTab.ListParcelKey");
    }
    if ((!this.mIsSelectedTab) || (!this.mUseTwoColumnLayout));
    while (true)
    {
      return;
      if (this.mSavedInstanceState.containsKey(getKey("checked_document_id")))
        selectDocument(this.mSavedInstanceState.getString(getKey("checked_document_id")));
      else
        makeDefaultSelection();
    }
  }

  protected abstract void retryFromError();

  public void setTabSelected(boolean paramBoolean)
  {
    this.mIsSelectedTab = paramBoolean;
    if (paramBoolean)
      restoreTabSelection();
    while (true)
    {
      return;
      saveCurrentSelection();
    }
  }

  protected void syncViewToState()
  {
    View localView1 = getFullView();
    View localView2 = localView1.findViewById(2131230848);
    View localView3 = localView1.findViewById(2131231139);
    ListView localListView = (ListView)localView1.findViewById(2131231097);
    if (this.mLastVolleyError != null)
    {
      localView3.setVisibility(0);
      ((TextView)localView3.findViewById(2131230788)).setText(ErrorStrings.get(FinskyApp.get(), this.mLastVolleyError));
      localView3.findViewById(2131231014).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyAppsTab.this.retryFromError();
          MyAppsTab.this.syncViewToState();
        }
      });
      localView2.setVisibility(8);
      localListView.setVisibility(8);
    }
    while (true)
    {
      return;
      if (isDataReady())
      {
        localListView.setVisibility(0);
        localView3.setVisibility(8);
        localView2.setVisibility(8);
      }
      else
      {
        localView2.setVisibility(0);
        localView3.setVisibility(8);
        localListView.setVisibility(8);
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.myapps.MyAppsTab
 * JD-Core Version:    0.6.2
 */
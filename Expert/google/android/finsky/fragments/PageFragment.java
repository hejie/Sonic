package com.google.android.finsky.fragments;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;

public abstract class PageFragment extends Fragment
  implements Response.ErrorListener, OnDataChangedListener, LayoutSwitcher.RetryButtonListener
{
  protected BitmapLoader mBitmapLoader;
  protected Context mContext;
  protected ViewGroup mDataView;
  protected DfeApi mDfeApi;
  private DfeToc mDfeToc;
  private LayoutSwitcher mLayoutSwitcher;
  protected NavigationManager mNavigationManager;
  protected PageFragmentHost mPageFragmentHost;
  protected boolean mRefreshRequired;
  protected boolean mSaveInstanceStateCalled;
  private int mTheme = 2131623994;

  protected PageFragment()
  {
    setArguments(new Bundle());
  }

  public boolean canChangeFragmentManagerState()
  {
    FragmentActivity localFragmentActivity = getActivity();
    if ((!this.mSaveInstanceStateCalled) && (localFragmentActivity != null) && ((!(localFragmentActivity instanceof AuthenticatedActivity)) || (!((AuthenticatedActivity)localFragmentActivity).isStateSaved())));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  protected abstract int getLayoutRes();

  public DfeToc getToc()
  {
    return this.mDfeToc;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    if ((PageFragmentHost)getActivity() != this.mPageFragmentHost)
    {
      this.mPageFragmentHost = ((PageFragmentHost)getActivity());
      this.mContext = getActivity();
      this.mNavigationManager = this.mPageFragmentHost.getNavigationManager();
      this.mDfeApi = this.mPageFragmentHost.getDfeApi();
      this.mBitmapLoader = this.mPageFragmentHost.getBitmapLoader();
      onInitViewBinders();
    }
    this.mSaveInstanceStateCalled = false;
    FinskyLog.logTiming("Views bound", new Object[0]);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mDfeToc = ((DfeToc)getArguments().getParcelable("finsky.PageFragment.toc"));
    this.mSaveInstanceStateCalled = false;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      if (paramBundle != null)
        this.mTheme = paramBundle.getInt("finsky.PageFragment.theme", this.mTheme);
      paramLayoutInflater = LayoutInflater.from(new ContextThemeWrapper(getActivity(), this.mTheme));
    }
    ContentFrame localContentFrame = (ContentFrame)paramLayoutInflater.inflate(2130968711, paramViewGroup, false);
    localContentFrame.setDataLayout(paramLayoutInflater, getLayoutRes(), 2131231045);
    this.mDataView = localContentFrame.getDataLayout();
    this.mSaveInstanceStateCalled = false;
    this.mLayoutSwitcher = new LayoutSwitcher(localContentFrame, 2131231045, 2131231139, 2131230808, this, 2);
    FinskyLog.logTiming("Views inflated", new Object[0]);
    return localContentFrame;
  }

  public void onDataChanged()
  {
    if (isAdded())
    {
      this.mRefreshRequired = false;
      switchToData();
      rebindViews();
      FinskyLog.logTiming("Views rebound", new Object[0]);
    }
    while (true)
    {
      return;
      this.mRefreshRequired = true;
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    this.mDataView = null;
    this.mLayoutSwitcher = null;
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    if (canChangeFragmentManagerState())
      switchToError(ErrorStrings.get(this.mContext, paramVolleyError));
  }

  protected abstract void onInitViewBinders();

  public void onResume()
  {
    super.onResume();
    this.mSaveInstanceStateCalled = false;
    if (this.mRefreshRequired)
      refresh();
  }

  public void onRetry()
  {
    refresh();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putInt("finsky.PageFragment.theme", this.mTheme);
    this.mSaveInstanceStateCalled = true;
  }

  public void rebindActionBar()
  {
  }

  protected abstract void rebindViews();

  public void refresh()
  {
    this.mRefreshRequired = false;
    requestData();
    FinskyLog.logTiming("requestData called", new Object[0]);
  }

  public void refreshOnResume()
  {
    this.mRefreshRequired = true;
  }

  protected abstract void requestData();

  protected void setArgument(String paramString, int paramInt)
  {
    getArguments().putInt(paramString, paramInt);
  }

  protected void setArgument(String paramString, Parcelable paramParcelable)
  {
    getArguments().putParcelable(paramString, paramParcelable);
  }

  protected void setArgument(String paramString1, String paramString2)
  {
    getArguments().putString(paramString1, paramString2);
  }

  protected void setArgument(String paramString, boolean paramBoolean)
  {
    getArguments().putBoolean(paramString, paramBoolean);
  }

  protected void setDfeToc(DfeToc paramDfeToc)
  {
    setArgument("finsky.PageFragment.toc", paramDfeToc);
  }

  protected void setTheme(int paramInt)
  {
    this.mTheme = paramInt;
  }

  protected void switchToBlank()
  {
    this.mLayoutSwitcher.switchToBlankMode();
  }

  protected void switchToData()
  {
    this.mLayoutSwitcher.switchToDataMode();
  }

  protected void switchToError(String paramString)
  {
    this.mLayoutSwitcher.switchToErrorMode(paramString);
  }

  protected void switchToLoading()
  {
    this.mLayoutSwitcher.switchToLoadingDelayed(350);
  }

  protected void switchToLoadingImmediately()
  {
    this.mLayoutSwitcher.switchToLoadingMode();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.PageFragment
 * JD-Core Version:    0.6.2
 */
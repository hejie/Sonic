package com.google.android.finsky.activities.mywishlist;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyLog;

public class MyWishlistFragment extends PageFragment
  implements View.OnClickListener, Libraries.Listener
{
  private MyWishlistAdapter mAdapter;
  private boolean mAdapterSet;
  private String mBreadcrumb;
  private DfeList mDfeList;
  private Libraries mLibraries;
  private String mLibraryUrl;
  private ListView mMyWishlistListView;
  private int mNumCellsTall;
  private int mNumCellsWide;
  private final DfeToc mToc = FinskyApp.get().getToc();

  private DfeList getLibraryList()
  {
    byte[] arrayOfByte = this.mLibraries.getAccountLibrary(FinskyApp.get().getCurrentAccount()).getServerToken("u-wl");
    String str = this.mDfeApi.getLibraryUrl(0, "u-wl", 7, arrayOfByte);
    this.mLibraryUrl = str;
    return new DfeList(this.mDfeApi, str, true);
  }

  protected int getLayoutRes()
  {
    return 2130968856;
  }

  protected boolean isDataReady()
  {
    if (this.mDfeList == null);
    for (boolean bool = false; ; bool = this.mDfeList.isReady())
      return bool;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mBreadcrumb = this.mContext.getString(2131165807);
    this.mNumCellsWide = getResources().getInteger(2131492878);
    this.mNumCellsTall = getResources().getInteger(2131492877);
    this.mMyWishlistListView = ((ListView)this.mDataView.findViewById(2131230821));
    this.mMyWishlistListView.setItemsCanFocus(true);
    this.mMyWishlistListView.setVisibility(0);
    this.mAdapterSet = false;
    if (!isDataReady())
    {
      switchToLoading();
      requestData();
    }
    rebindActionBar();
    rebindAdapter();
  }

  public void onAllLibrariesLoaded()
  {
  }

  public void onClick(View paramView)
  {
    int i = this.mMyWishlistListView.getPositionForView(paramView);
    if (i != -1)
    {
      Document localDocument = (Document)this.mAdapter.getItem(i);
      if (localDocument != null)
        this.mNavigationManager.goToDocPage(localDocument, null, null, null, null);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mLibraries = FinskyApp.get().getLibraries();
  }

  public void onDataChanged()
  {
    super.onDataChanged();
    rebindAdapter();
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.mDfeList != null)
    {
      this.mDfeList.removeDataChangedListener(this);
      this.mDfeList.removeErrorListener(this);
      this.mDfeList = null;
    }
    if (this.mAdapter != null)
      this.mAdapter.onDestroy();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    this.mMyWishlistListView = null;
    if (this.mAdapter != null)
      this.mAdapter.onDestroyView();
  }

  protected void onInitViewBinders()
  {
  }

  public void onLibraryContentsChanged(AccountLibrary paramAccountLibrary)
  {
    if (paramAccountLibrary.getAccount().name.equals(FinskyApp.get().getCurrentAccountName()))
      requestData();
  }

  public void onStart()
  {
    super.onStart();
    this.mLibraries.addListener(this);
  }

  public void onStop()
  {
    this.mLibraries.removeListener(this);
    super.onStop();
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
    this.mPageFragmentHost.updateCurrentBackendId(0);
  }

  public void rebindAdapter()
  {
    if (this.mMyWishlistListView == null)
      FinskyLog.w("List view null, ignoring.", new Object[0]);
    label128: label140: 
    while (true)
    {
      return;
      if (isDataReady())
      {
        if (this.mAdapter == null)
          this.mAdapter = new MyWishlistAdapter(this.mContext, this.mNavigationManager, this.mBitmapLoader, this.mToc, this.mDfeList, this.mNumCellsWide, this.mNumCellsTall, 2130968705, this.mLibraryUrl);
        if (this.mAdapterSet)
          break label128;
        this.mMyWishlistListView.setAdapter(this.mAdapter);
        this.mAdapterSet = true;
      }
      while (true)
      {
        if (!isAdded())
          break label140;
        this.mMyWishlistListView.setEmptyView(this.mDataView.findViewById(2131230994));
        break;
        break;
        this.mAdapter.updateAdapterData(this.mDfeList);
      }
    }
  }

  protected void rebindViews()
  {
  }

  protected void requestData()
  {
    this.mDfeList = getLibraryList();
    this.mDfeList.addDataChangedListener(this);
    this.mDfeList.addErrorListener(this);
    this.mDfeList.startLoadItems();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.mywishlist.MyWishlistFragment
 * JD-Core Version:    0.6.2
 */
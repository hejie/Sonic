package com.google.android.finsky.utils;

import android.content.Context;
import android.os.Bundle;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.navigationmanager.NavigationManager;

public class DetailsShimFragment extends UrlBasedPageFragment
{
  private DfeDetails mDetailsData;

  public static DetailsShimFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    return newInstance(paramString1, paramString2, paramString3, paramString4, paramString5, false);
  }

  public static DetailsShimFragment newInstance(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    DetailsShimFragment localDetailsShimFragment = new DetailsShimFragment();
    localDetailsShimFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString1);
    localDetailsShimFragment.setArgument("finsky.DetailsFragment.cookie", paramString2);
    localDetailsShimFragment.setArgument("finsky.DetailsFragment.referrerUrl", paramString3);
    localDetailsShimFragment.setArgument("finsky.DetailsFragment.externalUrl", paramString4);
    localDetailsShimFragment.setArgument("finsky.DetailsFragment.continueUrl", paramString5);
    localDetailsShimFragment.setArgument("finsky.DetailsFragment.returnAfterPurchase", paramBoolean);
    return localDetailsShimFragment;
  }

  protected int getLayoutRes()
  {
    return 0;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    requestData();
  }

  public void onDataChanged()
  {
    if (this.mDetailsData.getDocument() == null)
      this.mPageFragmentHost.showErrorDialog(null, this.mContext.getString(2131165457), true);
    while (true)
    {
      return;
      this.mNavigationManager.replaceDetailsShimWithDocPage(this.mDetailsData.getDocument(), this.mUrl, getArguments().getString("finsky.DetailsFragment.cookie"), getArguments().getString("finsky.DetailsFragment.referrerUrl"), getArguments().getString("finsky.DetailsFragment.externalUrl"), getArguments().getString("finsky.DetailsFragment.continueUrl"), getArguments().getBoolean("finsky.DetailsFragment.returnAfterPurchase"));
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    if (this.mDetailsData != null)
    {
      this.mDetailsData.removeDataChangedListener(this);
      this.mDetailsData.removeErrorListener(this);
    }
  }

  protected void onInitViewBinders()
  {
  }

  protected void rebindViews()
  {
  }

  protected void requestData()
  {
    if (this.mDetailsData != null)
    {
      this.mDetailsData.removeDataChangedListener(this);
      this.mDetailsData.removeErrorListener(this);
    }
    this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, getArguments().getString("finsky.DetailsFragment.cookie"));
    this.mDetailsData.addDataChangedListener(this);
    this.mDetailsData.addErrorListener(this);
    switchToBlank();
    switchToLoading();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.DetailsShimFragment
 * JD-Core Version:    0.6.2
 */
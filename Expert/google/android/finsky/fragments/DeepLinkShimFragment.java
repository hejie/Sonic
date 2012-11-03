package com.google.android.finsky.fragments;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.RedeemGiftCardActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.ResolveLink.DirectPurchase;
import com.google.android.finsky.remoting.protos.ResolveLink.ResolveLinkResponse;
import java.net.URLDecoder;
import java.util.List;

public class DeepLinkShimFragment extends UrlBasedPageFragment
  implements Response.Listener<ResolveLink.ResolveLinkResponse>
{
  private ResolveLink.ResolveLinkResponse mResponse;
  private Uri mUri;

  public static String getContinueUrl(Uri paramUri)
  {
    String str1 = paramUri.getQueryParameter("url");
    if (!TextUtils.isEmpty(str1));
    for (String str2 = URLDecoder.decode(str1); ; str2 = null)
      return str2;
  }

  public static String getExternalReferrer(Uri paramUri)
  {
    String str1 = paramUri.getQueryParameter("referrer");
    String str3;
    if (TextUtils.isEmpty(str1))
    {
      str3 = paramUri.getQueryParameter("gclid");
      if (!TextUtils.isEmpty(str3));
    }
    for (String str2 = null; ; str2 = str1)
    {
      return str2;
      str1 = Uri.encode("gclid=" + str3);
    }
  }

  private boolean isFirstPartyCaller()
  {
    boolean bool = false;
    FragmentActivity localFragmentActivity = getActivity();
    String str = localFragmentActivity.getCallingPackage();
    if (str == null);
    while (true)
    {
      return bool;
      if (localFragmentActivity.getPackageManager().checkSignatures(localFragmentActivity.getPackageName(), str) == 0)
        bool = true;
    }
  }

  public static Fragment newInstance(String paramString)
  {
    DeepLinkShimFragment localDeepLinkShimFragment = new DeepLinkShimFragment();
    localDeepLinkShimFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString);
    return localDeepLinkShimFragment;
  }

  private boolean shouldSkipDetailsPage()
  {
    boolean bool1 = getActivity().getIntent().getBooleanExtra("use_direct_purchase", false);
    int i;
    if ((isFirstPartyCaller()) || (((Boolean)G.enableThirdPartyDirectPurchases.get()).booleanValue()))
    {
      i = 1;
      if ((!bool1) || (i == 0))
        break label55;
    }
    label55: for (boolean bool2 = true; ; bool2 = false)
    {
      return bool2;
      i = 0;
      break;
    }
  }

  protected int getLayoutRes()
  {
    return 0;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mUri = Uri.parse(this.mUrl);
    requestData();
  }

  public void onDataChanged()
  {
    if (this.mResponse == null);
    while (true)
    {
      return;
      if (canChangeFragmentManagerState())
      {
        this.mNavigationManager.popBackStack();
        FinskyEventLog localFinskyEventLog = FinskyApp.get().getEventLogger();
        if (this.mResponse.hasDetailsUrl())
        {
          String str6 = this.mResponse.getDetailsUrl();
          localFinskyEventLog.logDeepLink(this.mUrl, str6, false, false, false);
          this.mNavigationManager.goToDocPage(str6, null, this.mUrl, getExternalReferrer(this.mUri), getContinueUrl(this.mUri));
        }
        else if (this.mResponse.hasBrowseUrl())
        {
          String str5 = this.mResponse.getBrowseUrl();
          FinskyApp.get().getEventLogger().logDeepLink(this.mUrl, str5, false, false, false);
          this.mNavigationManager.goBrowse(str5, null, -1, this.mUrl, getToc());
        }
        else if (this.mResponse.hasSearchUrl())
        {
          String str3 = this.mResponse.getSearchUrl();
          localFinskyEventLog.logDeepLink(this.mUrl, str3, false, false, false);
          String str4 = URLDecoder.decode(Uri.parse("http://market.android.com/" + str3).getQueryParameter("q"));
          this.mNavigationManager.goToSearch(str4, str3, this.mUrl);
        }
        else
        {
          if (this.mResponse.hasDirectPurchase())
          {
            ResolveLink.DirectPurchase localDirectPurchase = this.mResponse.getDirectPurchase();
            String str2 = localDirectPurchase.getDetailsUrl();
            localFinskyEventLog.logDeepLink(this.mUrl, str2, true, false, false);
            boolean bool = shouldSkipDetailsPage();
            if (!bool)
              this.mNavigationManager.goToDocPage(str2, null, this.mUrl, getExternalReferrer(this.mUri), getContinueUrl(this.mUri), true);
            while (true)
            {
              getActivity().getIntent().putExtra("requested_doc_id", localDirectPurchase.getPurchaseDocid());
              break;
              Account localAccount = FinskyApp.get().getCurrentAccount();
              this.mNavigationManager.goToPurchase(localAccount, str2, localDirectPurchase.getOfferType(), this.mUrl, null, bool, getExternalReferrer(this.mUri), getContinueUrl(this.mUri), localDirectPurchase.getPurchaseDocid(), true);
            }
          }
          if (this.mResponse.hasHomeUrl())
          {
            String str1 = this.mResponse.getHomeUrl();
            localFinskyEventLog.logDeepLink(this.mUrl, str1, false, false, false);
            this.mNavigationManager.goToAggregatedHome(getToc(), str1, this.mUrl);
          }
          else if (this.mResponse.hasRedeemGiftCard())
          {
            localFinskyEventLog.logDeepLink(this.mUrl, null, false, true, false);
            RedeemGiftCardActivity.show(getActivity(), FinskyApp.get().getCurrentAccountName(), 0);
            getActivity().finish();
          }
          else
          {
            localFinskyEventLog.logDeepLink(this.mUrl, null, false, false, true);
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setData(Uri.parse(this.mUrl));
            localIntent.addFlags(268435456);
            localIntent.putExtra("dont_resolve_again", true);
            List localList = getActivity().getPackageManager().queryIntentActivities(localIntent, 0);
            if (localList.size() == 2)
            {
              ActivityInfo localActivityInfo = ((ResolveInfo)localList.get(0)).activityInfo;
              if (localActivityInfo.packageName.equals(getActivity().getPackageName()))
                localActivityInfo = ((ResolveInfo)localList.get(1)).activityInfo;
              localIntent.setPackage(localActivityInfo.packageName);
            }
            startActivity(localIntent);
          }
        }
      }
    }
  }

  public void onErrorResponse(VolleyError paramVolleyError)
  {
    this.mNavigationManager.goToAggregatedHome(getToc());
  }

  protected void onInitViewBinders()
  {
  }

  public void onResponse(ResolveLink.ResolveLinkResponse paramResolveLinkResponse)
  {
    this.mResponse = paramResolveLinkResponse;
    onDataChanged();
  }

  protected void rebindViews()
  {
  }

  protected void requestData()
  {
    this.mDfeApi.resolveLink(this.mUrl, this, this);
    switchToLoading();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.fragments.DeepLinkShimFragment
 * JD-Core Version:    0.6.2
 */
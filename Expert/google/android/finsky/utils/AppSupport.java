package com.google.android.finsky.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.volley.NetworkError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.remoting.protos.RevokeResponse;

public class AppSupport
{
  public static void showRefundFailureDialog(FragmentManager paramFragmentManager)
  {
    SimpleAlertDialog.newInstance(2131165608, 2131165599, -1).show(paramFragmentManager, "refund_failure");
  }

  public static void showUninstallConfirmationDialog(String paramString, Fragment paramFragment, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    FragmentManager localFragmentManager = paramFragment.getFragmentManager();
    if (localFragmentManager.findFragmentByTag("uninstall_confirm") != null)
      return;
    int i = 2131165599;
    int j = 2131165273;
    int k;
    if (paramBoolean2)
      if (paramBoolean1)
        k = 2131165450;
    while (true)
    {
      SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(k, i, j);
      Bundle localBundle = new Bundle();
      localBundle.putString("package_name", paramString);
      localSimpleAlertDialog.setCallback(paramFragment, 1, localBundle);
      localSimpleAlertDialog.show(localFragmentManager, "uninstall_confirm");
      break;
      if (paramBoolean3)
      {
        k = 2131165452;
        i = 2131165402;
        j = 2131165403;
      }
      else
      {
        k = 2131165451;
        continue;
        k = 2131165453;
      }
    }
  }

  public static void silentRefund(final String paramString1, String paramString2, RefundListener paramRefundListener)
  {
    paramRefundListener.onRefundStart();
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(paramString2);
    localDfeApi.revoke(paramString1, 1, new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), localDfeApi.getAccount(), new Response.Listener()
    {
      public void onResponse(RevokeResponse paramAnonymousRevokeResponse)
      {
        this.val$listener.onRefundComplete(AppSupport.RefundResult.SUCCESS, paramString1);
      }
    }), new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        if ((paramAnonymousVolleyError instanceof NetworkError));
        for (AppSupport.RefundResult localRefundResult = AppSupport.RefundResult.NETWORK_ERROR; ; localRefundResult = AppSupport.RefundResult.CANNOT_REFUND)
        {
          this.val$listener.onRefundComplete(localRefundResult, paramString1);
          return;
        }
      }
    });
  }

  public static abstract interface RefundListener
  {
    public abstract void onRefundComplete(AppSupport.RefundResult paramRefundResult, String paramString);

    public abstract void onRefundStart();
  }

  public static enum RefundResult
  {
    static
    {
      CANNOT_REFUND = new RefundResult("CANNOT_REFUND", 1);
      BAD_REQUEST = new RefundResult("BAD_REQUEST", 2);
      NETWORK_ERROR = new RefundResult("NETWORK_ERROR", 3);
      RefundResult[] arrayOfRefundResult = new RefundResult[4];
      arrayOfRefundResult[0] = SUCCESS;
      arrayOfRefundResult[1] = CANNOT_REFUND;
      arrayOfRefundResult[2] = BAD_REQUEST;
      arrayOfRefundResult[3] = NETWORK_ERROR;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.AppSupport
 * JD-Core Version:    0.6.2
 */
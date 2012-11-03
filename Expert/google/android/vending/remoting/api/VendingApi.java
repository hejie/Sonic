package com.google.android.vending.remoting.api;

import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.utils.Lists;
import com.google.android.vending.remoting.protos.VendingProtos.AckNotificationsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.AckNotificationsResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.BillingEventRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.BillingEventResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.CheckForNotificationsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.CheckLicenseRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.CheckLicenseResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppPurchaseInformationRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppPurchaseInformationResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppRestoreTransactionsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.InAppRestoreTransactionsResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.ModifyCommentRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.ModifyCommentResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries;
import com.google.android.vending.remoting.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.vending.remoting.protos.VendingProtos.RestoreApplicationsRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.RestoreApplicationsResponseProto;
import java.util.List;

public class VendingApi
{
  private final VendingApiContext mApiContext;
  private final RequestQueue mRequestQueue;

  public VendingApi(RequestQueue paramRequestQueue, VendingApiContext paramVendingApiContext)
  {
    this.mRequestQueue = paramRequestQueue;
    this.mApiContext = paramVendingApiContext;
  }

  public void ackNotifications(VendingProtos.AckNotificationsRequestProto paramAckNotificationsRequestProto, Response.Listener<VendingProtos.AckNotificationsResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.AckNotificationsRequestProto.class, paramAckNotificationsRequestProto, VendingProtos.AckNotificationsResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    localVendingRequest.setAvoidBulkCancel();
    this.mRequestQueue.add(localVendingRequest);
  }

  public void checkForPendingNotifications(Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.CheckForNotificationsRequestProto.class, new VendingProtos.CheckForNotificationsRequestProto(), VendingProtos.GetMarketMetadataResponseProto.class, new Response.Listener()
    {
      public void onResponse(VendingProtos.GetMarketMetadataResponseProto paramAnonymousGetMarketMetadataResponseProto)
      {
      }
    }
    , this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public void checkLicense(VendingProtos.CheckLicenseRequestProto paramCheckLicenseRequestProto, Response.Listener<VendingProtos.CheckLicenseResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.CheckLicenseRequestProto.class, paramCheckLicenseRequestProto, VendingProtos.CheckLicenseResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public void flagAsset(String paramString1, int paramInt, String paramString2, Response.Listener<VendingProtos.ModifyCommentResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingProtos.ModifyCommentRequestProto localModifyCommentRequestProto = new VendingProtos.ModifyCommentRequestProto();
    localModifyCommentRequestProto.setAssetId(paramString1);
    localModifyCommentRequestProto.setFlagType(paramInt);
    if (!TextUtils.isEmpty(paramString2))
      localModifyCommentRequestProto.setFlagMessage(paramString2);
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.ModifyCommentRequestProto.class, localModifyCommentRequestProto, VendingProtos.ModifyCommentResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public VendingApiContext getApiContext()
  {
    return this.mApiContext;
  }

  public void getBillingCountries(Response.Listener<List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country>> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.PurchaseMetadataRequestProto.class, new VendingProtos.PurchaseMetadataRequestProto(), VendingProtos.PurchaseMetadataResponseProto.class, new CountriesConverter(paramListener), this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public void getInAppPurchaseInformation(VendingProtos.InAppPurchaseInformationRequestProto paramInAppPurchaseInformationRequestProto, Response.Listener<VendingProtos.InAppPurchaseInformationResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.InAppPurchaseInformationRequestProto.class, paramInAppPurchaseInformationRequestProto, VendingProtos.InAppPurchaseInformationResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public void getMetadata(VendingProtos.GetMarketMetadataRequestProto paramGetMarketMetadataRequestProto, int paramInt1, int paramInt2, Response.Listener<VendingProtos.GetMarketMetadataResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    GetMarketMetadataRequest localGetMarketMetadataRequest = new GetMarketMetadataRequest(paramGetMarketMetadataRequestProto, paramInt1, paramInt2, paramListener, this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localGetMarketMetadataRequest);
  }

  public void recordBillingEvent(VendingProtos.BillingEventRequestProto paramBillingEventRequestProto, Response.Listener<VendingProtos.BillingEventResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.BillingEventRequestProto.class, paramBillingEventRequestProto, VendingProtos.BillingEventResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    localVendingRequest.setAvoidBulkCancel();
    this.mRequestQueue.add(localVendingRequest);
  }

  public void restoreApplications(VendingProtos.RestoreApplicationsRequestProto paramRestoreApplicationsRequestProto, Response.Listener<VendingProtos.RestoreApplicationsResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.RestoreApplicationsRequestProto.class, paramRestoreApplicationsRequestProto, VendingProtos.RestoreApplicationsResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    localVendingRequest.setAvoidBulkCancel();
    this.mRequestQueue.add(localVendingRequest);
  }

  public void restoreInAppTransactions(VendingProtos.InAppRestoreTransactionsRequestProto paramInAppRestoreTransactionsRequestProto, Response.Listener<VendingProtos.InAppRestoreTransactionsResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.InAppRestoreTransactionsRequestProto.class, paramInAppRestoreTransactionsRequestProto, VendingProtos.InAppRestoreTransactionsResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    this.mRequestQueue.add(localVendingRequest);
  }

  public void syncContent(VendingProtos.ContentSyncRequestProto paramContentSyncRequestProto, Response.Listener<VendingProtos.ContentSyncResponseProto> paramListener, Response.ErrorListener paramErrorListener)
  {
    VendingRequest localVendingRequest = VendingRequest.make("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.ContentSyncRequestProto.class, paramContentSyncRequestProto, VendingProtos.ContentSyncResponseProto.class, paramListener, this.mApiContext, paramErrorListener);
    String str = Settings.Secure.getString(FinskyApp.get().getContentResolver(), "android_id");
    if (!TextUtils.isEmpty(str))
      localVendingRequest.addExtraHeader("X-Public-Android-Id", str);
    localVendingRequest.setAvoidBulkCancel();
    this.mRequestQueue.add(localVendingRequest);
  }

  private static class CountriesConverter
    implements Response.Listener<VendingProtos.PurchaseMetadataResponseProto>
  {
    private final Response.Listener<List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country>> mListener;

    public CountriesConverter(Response.Listener<List<VendingProtos.PurchaseMetadataResponseProto.Countries.Country>> paramListener)
    {
      this.mListener = paramListener;
    }

    public void onResponse(VendingProtos.PurchaseMetadataResponseProto paramPurchaseMetadataResponseProto)
    {
      if ((paramPurchaseMetadataResponseProto != null) && (paramPurchaseMetadataResponseProto.hasCountries()))
        this.mListener.onResponse(paramPurchaseMetadataResponseProto.getCountries().getCountryList());
      while (true)
      {
        return;
        this.mListener.onResponse(Lists.newArrayList());
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.VendingApi
 * JD-Core Version:    0.6.2
 */
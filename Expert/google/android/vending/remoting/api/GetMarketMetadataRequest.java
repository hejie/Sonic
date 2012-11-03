package com.google.android.vending.remoting.api;

import android.accounts.Account;
import com.android.volley.Cache.Entry;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetMarketMetadataResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.ResponseProto;

public class GetMarketMetadataRequest extends VendingRequest<VendingProtos.GetMarketMetadataRequestProto, VendingProtos.GetMarketMetadataResponseProto>
{
  private boolean mDelivered = false;
  private final int mDeviceConfigHash;
  private final int mMarketVersion;

  public GetMarketMetadataRequest(VendingProtos.GetMarketMetadataRequestProto paramGetMarketMetadataRequestProto, int paramInt1, int paramInt2, Response.Listener<VendingProtos.GetMarketMetadataResponseProto> paramListener, VendingApiContext paramVendingApiContext, Response.ErrorListener paramErrorListener)
  {
    super("https://android.clients.google.com/vending/api/ApiRequest", VendingProtos.GetMarketMetadataRequestProto.class, paramGetMarketMetadataRequestProto, VendingProtos.GetMarketMetadataResponseProto.class, paramListener, paramVendingApiContext, paramErrorListener);
    this.mDeviceConfigHash = paramInt1;
    this.mMarketVersion = paramInt2;
    setAvoidBulkCancel();
    Integer localInteger = (Integer)VendingApiPreferences.getDeviceConfigurationHashProperty(paramVendingApiContext.getAccount().name).get();
    if ((localInteger != null) && (localInteger.equals(Integer.valueOf(this.mDeviceConfigHash))) && (!((Boolean)G.vendingAlwaysSendConfig.get()).booleanValue()))
      paramGetMarketMetadataRequestProto.clearDeviceConfiguration();
  }

  protected void deliverResponse(VendingProtos.ResponseProto paramResponseProto)
  {
    if (!this.mDelivered)
    {
      this.mDelivered = true;
      super.deliverResponse(paramResponseProto);
    }
  }

  public String getCacheKey()
  {
    StringBuilder localStringBuilder = new StringBuilder(super.getCacheKey());
    localStringBuilder.append("/ttl=").append(G.vendingSyncFrequencyMs.get());
    localStringBuilder.append("/account=").append(this.mApiContext.getAccount().name);
    localStringBuilder.append("/devicecfghash=").append(this.mDeviceConfigHash);
    localStringBuilder.append("/marketversion=").append(this.mMarketVersion);
    return localStringBuilder.toString();
  }

  protected Response<VendingProtos.ResponseProto> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    Response localResponse = super.parseNetworkResponse(paramNetworkResponse);
    Cache.Entry localEntry;
    boolean bool;
    if (localResponse.isSuccess())
    {
      VendingApiPreferences.getDeviceConfigurationHashProperty(this.mApiContext.getAccount().name).put(Integer.valueOf(this.mDeviceConfigHash));
      localEntry = null;
      bool = handlePendingNotifications((VendingProtos.ResponseProto)localResponse.result, false);
      if ((bool) || (((Long)G.vendingSyncFrequencyMs.get()).longValue() <= 0L))
        break label135;
      localEntry = new Cache.Entry();
      localEntry.data = paramNetworkResponse.data;
      localEntry.softTtl = (System.currentTimeMillis() + ((Long)G.vendingSyncFrequencyMs.get()).longValue());
      localEntry.ttl = 9223372036854775807L;
      localEntry.serverDate = System.currentTimeMillis();
    }
    while (true)
    {
      localResponse = Response.success(localResponse.result, localEntry);
      return localResponse;
      label135: Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(bool);
      FinskyLog.d("Disabling cache for GetMarketMetadata. hasPendingNotifications=%s", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.remoting.api.GetMarketMetadataRequest
 * JD-Core Version:    0.6.2
 */
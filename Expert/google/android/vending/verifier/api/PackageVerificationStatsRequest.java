package com.google.android.vending.verifier.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadStatsRequest;

public class PackageVerificationStatsRequest extends BaseVerificationRequest<NetworkResponse, CsdClient.ClientDownloadStatsRequest>
{
  public PackageVerificationStatsRequest(String paramString, Response.ErrorListener paramErrorListener, CsdClient.ClientDownloadStatsRequest paramClientDownloadStatsRequest)
  {
    super(paramString, paramErrorListener, paramClientDownloadStatsRequest);
  }

  protected void deliverResponse(NetworkResponse paramNetworkResponse)
  {
  }

  protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    return Response.success(paramNetworkResponse, null);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.api.PackageVerificationStatsRequest
 * JD-Core Version:    0.6.2
 */
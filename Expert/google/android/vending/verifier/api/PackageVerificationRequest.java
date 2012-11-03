package com.google.android.vending.verifier.api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;

public class PackageVerificationRequest extends BaseVerificationRequest<PackageVerificationResult, CsdClient.ClientDownloadRequest>
{
  private final Response.Listener<PackageVerificationResult> mListener;

  public PackageVerificationRequest(String paramString, Response.Listener<PackageVerificationResult> paramListener, Response.ErrorListener paramErrorListener, CsdClient.ClientDownloadRequest paramClientDownloadRequest)
  {
    super(paramString, paramErrorListener, paramClientDownloadRequest);
    this.mListener = paramListener;
  }

  protected void deliverResponse(PackageVerificationResult paramPackageVerificationResult)
  {
    this.mListener.onResponse(paramPackageVerificationResult);
  }

  protected Response<PackageVerificationResult> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      CsdClient.ClientDownloadResponse localClientDownloadResponse = CsdClient.ClientDownloadResponse.parseFrom(paramNetworkResponse.data);
      localResponse = Response.success(PackageVerificationResult.fromResponse(localClientDownloadResponse), null);
      return localResponse;
    }
    catch (InvalidProtocolBufferMicroException localInvalidProtocolBufferMicroException)
    {
      while (true)
        Response localResponse = Response.error(new VolleyError(localInvalidProtocolBufferMicroException));
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.api.PackageVerificationRequest
 * JD-Core Version:    0.6.2
 */
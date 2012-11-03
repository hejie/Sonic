package com.google.android.vending.verifier.api;

import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.FinskyApp;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.ApkInfo;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.Digests;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadRequest.Resource;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadStatsRequest;
import com.google.protobuf.micro.ByteStringMicro;
import java.net.InetAddress;

public class PackageVerificationApi
{
  private static CsdClient.ClientDownloadRequest.Digests buildSha256Digest(byte[] paramArrayOfByte)
  {
    return new CsdClient.ClientDownloadRequest.Digests().setSha256(ByteStringMicro.copyFrom(paramArrayOfByte));
  }

  private static CsdClient.ClientDownloadRequest.Resource createResource(Uri paramUri1, InetAddress paramInetAddress, Uri paramUri2, int paramInt)
  {
    CsdClient.ClientDownloadRequest.Resource localResource = new CsdClient.ClientDownloadRequest.Resource();
    localResource.setUrl(paramUri1.toString());
    localResource.setType(paramInt);
    if (paramUri2 != null)
      localResource.setReferrer(paramUri2.toString());
    if (paramInetAddress != null)
      localResource.setRemoteIp(ByteStringMicro.copyFromUtf8(paramInetAddress.getHostAddress()));
    return localResource;
  }

  public static Request<?> reportUserDecision(int paramInt, byte[] paramArrayOfByte, Response.ErrorListener paramErrorListener)
  {
    CsdClient.ClientDownloadStatsRequest localClientDownloadStatsRequest = new CsdClient.ClientDownloadStatsRequest();
    if (paramArrayOfByte != null)
      localClientDownloadStatsRequest.setToken(ByteStringMicro.copyFrom(paramArrayOfByte));
    localClientDownloadStatsRequest.setUserDecision(paramInt);
    PackageVerificationStatsRequest localPackageVerificationStatsRequest = new PackageVerificationStatsRequest("https://safebrowsing.google.com/safebrowsing/clientreport/download-stat", paramErrorListener, localClientDownloadStatsRequest);
    return FinskyApp.get().getRequestQueue().add(localPackageVerificationStatsRequest);
  }

  public static Request<?> verifyApp(byte[] paramArrayOfByte, long paramLong1, String paramString1, Integer paramInteger, String paramString2, Uri paramUri1, Uri paramUri2, InetAddress paramInetAddress1, InetAddress paramInetAddress2, String paramString3, long paramLong2, Response.Listener<PackageVerificationResult> paramListener, Response.ErrorListener paramErrorListener)
  {
    CsdClient.ClientDownloadRequest localClientDownloadRequest = new CsdClient.ClientDownloadRequest();
    if ((paramString1 != null) || (paramInteger != null))
    {
      CsdClient.ClientDownloadRequest.ApkInfo localApkInfo = new CsdClient.ClientDownloadRequest.ApkInfo();
      if (paramString1 != null)
        localApkInfo.setPackageName(paramString1);
      if (paramInteger != null)
        localApkInfo.setVersionCode(paramInteger.intValue());
      localClientDownloadRequest.setApkInfo(localApkInfo);
    }
    if (paramUri1 != null)
    {
      localClientDownloadRequest.setUrl(paramUri1.toString());
      localClientDownloadRequest.addResources(createResource(paramUri1, paramInetAddress1, paramUri2, 0));
      if (paramUri2 != null)
        localClientDownloadRequest.addResources(createResource(paramUri2, paramInetAddress2, null, 2));
    }
    while (true)
    {
      localClientDownloadRequest.setLength(paramLong1);
      localClientDownloadRequest.setDigests(buildSha256Digest(paramArrayOfByte));
      localClientDownloadRequest.setDownloadType(2);
      if (paramString3 != null)
        localClientDownloadRequest.setLocale(paramString3);
      localClientDownloadRequest.setAndroidId(paramLong2);
      PackageVerificationRequest localPackageVerificationRequest = new PackageVerificationRequest("https://safebrowsing.google.com/safebrowsing/clientreport/download", paramListener, paramErrorListener, localClientDownloadRequest);
      return FinskyApp.get().getRequestQueue().add(localPackageVerificationRequest);
      localClientDownloadRequest.setUrl("");
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.api.PackageVerificationApi
 * JD-Core Version:    0.6.2
 */
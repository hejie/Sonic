package com.google.android.vending.verifier.api;

import android.net.Uri;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse;
import com.google.android.vending.verifier.protos.CsdClient.ClientDownloadResponse.MoreInfo;
import com.google.protobuf.micro.ByteStringMicro;

public class PackageVerificationResult
{
  public final String description;
  public final Uri moreInfoUri;
  public final byte[] token;
  public final int verdict;

  public PackageVerificationResult(int paramInt, String paramString, Uri paramUri, byte[] paramArrayOfByte)
  {
    this.verdict = paramInt;
    this.description = paramString;
    this.moreInfoUri = paramUri;
    this.token = paramArrayOfByte;
  }

  public static PackageVerificationResult fromResponse(CsdClient.ClientDownloadResponse paramClientDownloadResponse)
  {
    String str = null;
    Uri localUri = null;
    if (paramClientDownloadResponse.hasMoreInfo())
    {
      CsdClient.ClientDownloadResponse.MoreInfo localMoreInfo = paramClientDownloadResponse.getMoreInfo();
      if (localMoreInfo.hasDescription())
        str = localMoreInfo.getDescription();
      if (localMoreInfo.hasUrl())
        localUri = Uri.parse(localMoreInfo.getUrl());
    }
    byte[] arrayOfByte = null;
    if (paramClientDownloadResponse.hasToken())
      arrayOfByte = paramClientDownloadResponse.getToken().toByteArray();
    return new PackageVerificationResult(paramClientDownloadResponse.getVerdict(), str, localUri, arrayOfByte);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.api.PackageVerificationResult
 * JD-Core Version:    0.6.2
 */
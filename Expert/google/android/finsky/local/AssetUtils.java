package com.google.android.finsky.local;

import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.AppFileMetadata;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.HttpCookie;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.DocDetails.FileMetadata;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.vending.remoting.protos.VendingProtos.FileMetadataProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetAssetResponseProto;
import com.google.android.vending.remoting.protos.VendingProtos.GetAssetResponseProto.InstallAsset;
import java.util.Iterator;
import java.util.List;

public class AssetUtils
{
  public static AndroidAppDelivery.AndroidAppDeliveryData assetResponseToDeliveryData(VendingProtos.GetAssetResponseProto paramGetAssetResponseProto)
  {
    AndroidAppDelivery.AndroidAppDeliveryData localAndroidAppDeliveryData = new AndroidAppDelivery.AndroidAppDeliveryData();
    VendingProtos.GetAssetResponseProto.InstallAsset localInstallAsset = paramGetAssetResponseProto.getInstallAsset();
    localAndroidAppDeliveryData.setDownloadUrl(localInstallAsset.getBlobUrl());
    localAndroidAppDeliveryData.setDownloadSize(localInstallAsset.getAssetSize());
    localAndroidAppDeliveryData.setForwardLocked(localInstallAsset.getForwardLocked());
    localAndroidAppDeliveryData.setRefundTimeout(localInstallAsset.getRefundTimeoutMillis());
    localAndroidAppDeliveryData.setSignature(localInstallAsset.getAssetSignature());
    localAndroidAppDeliveryData.addDownloadAuthCookie(new AndroidAppDelivery.HttpCookie().setName(localInstallAsset.getDownloadAuthCookieName()).setValue(localInstallAsset.getDownloadAuthCookieValue()));
    Iterator localIterator = paramGetAssetResponseProto.getAdditionalFileList().iterator();
    while (localIterator.hasNext())
    {
      VendingProtos.FileMetadataProto localFileMetadataProto = (VendingProtos.FileMetadataProto)localIterator.next();
      AndroidAppDelivery.AppFileMetadata localAppFileMetadata = new AndroidAppDelivery.AppFileMetadata();
      localAppFileMetadata.setFileType(localFileMetadataProto.getFileType());
      localAppFileMetadata.setVersionCode(localFileMetadataProto.getVersionCode());
      localAppFileMetadata.setSize(localFileMetadataProto.getSize());
      localAppFileMetadata.setDownloadUrl(localFileMetadataProto.getDownloadUrl());
      localAndroidAppDeliveryData.addAdditionalFile(localAppFileMetadata);
    }
    return localAndroidAppDeliveryData;
  }

  public static Obb extractObb(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, String paramString, boolean paramBoolean)
  {
    int i;
    int j;
    label9: AndroidAppDelivery.AppFileMetadata localAppFileMetadata;
    if (paramBoolean)
    {
      i = 1;
      j = 0;
      if (j >= paramAndroidAppDeliveryData.getAdditionalFileCount())
        break label72;
      localAppFileMetadata = paramAndroidAppDeliveryData.getAdditionalFile(j);
      if (localAppFileMetadata.getFileType() != i)
        break label66;
    }
    label66: label72: for (Obb localObb = ObbFactory.create(paramBoolean, paramString, localAppFileMetadata.getVersionCode(), localAppFileMetadata.getDownloadUrl(), localAppFileMetadata.getSize(), 4); ; localObb = null)
    {
      return localObb;
      i = 0;
      break;
      j++;
      break label9;
    }
  }

  public static String makeAssetId(DocDetails.AppDetails paramAppDetails)
  {
    return makeAssetId(paramAppDetails.getPackageName(), paramAppDetails.getVersionCode());
  }

  public static String makeAssetId(String paramString, int paramInt)
  {
    return "v2:" + paramString + ":1:" + paramInt;
  }

  public static long totalDeliverySize(AndroidAppDelivery.AndroidAppDeliveryData paramAndroidAppDeliveryData, String paramString)
  {
    long l = paramAndroidAppDeliveryData.getDownloadSize();
    Obb localObb1 = extractObb(paramAndroidAppDeliveryData, paramString, false);
    if ((localObb1 != null) && (localObb1.getState() == 4))
      l += localObb1.getSize();
    Obb localObb2 = extractObb(paramAndroidAppDeliveryData, paramString, true);
    if ((localObb2 != null) && (localObb2.getState() == 4))
      l += localObb2.getSize();
    return l;
  }

  public static long totalDeliverySize(DocDetails.AppDetails paramAppDetails)
  {
    long l = 0L;
    int i = 0;
    if (i < paramAppDetails.getFileCount())
    {
      DocDetails.FileMetadata localFileMetadata = paramAppDetails.getFile(i);
      int j = localFileMetadata.getFileType();
      switch (j)
      {
      default:
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Integer.valueOf(j);
        arrayOfObject[1] = paramAppDetails.getPackageName();
        arrayOfObject[2] = Integer.valueOf(i);
        FinskyLog.w("Bad file type %d in %s entry# %d", arrayOfObject);
      case 0:
        while (true)
        {
          i++;
          break;
          l += localFileMetadata.getSize();
        }
      case 1:
      case 2:
      }
      if (j == 2);
      for (boolean bool = true; ; bool = false)
      {
        Obb localObb = ObbFactory.create(bool, paramAppDetails.getPackageName(), localFileMetadata.getVersionCode(), null, localFileMetadata.getSize(), 4);
        if (localObb.getState() != 4)
          break;
        l += localObb.getSize();
        break;
      }
    }
    return l;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.local.AssetUtils
 * JD-Core Version:    0.6.2
 */
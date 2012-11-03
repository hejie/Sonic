package com.google.android.finsky.download;

import android.content.ContentValues;
import android.net.Uri;
import android.text.TextUtils;

public class DownloadRequest
{
  ContentValues mContentValues = new ContentValues();

  public DownloadRequest(Uri paramUri1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, Uri paramUri2, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mContentValues.put("otheruid", Integer.valueOf(1000));
    this.mContentValues.put("uri", paramUri1.toString());
    if (paramUri2 != null)
    {
      this.mContentValues.put("destination", Integer.valueOf(DownloadManagerConstants.getFileDestinationConstant()));
      this.mContentValues.put("hint", paramUri2.toString());
      this.mContentValues.put("notificationpackage", paramString2);
      this.mContentValues.put("notificationclass", paramString3);
      if ((paramString4 != null) && (paramString5 != null))
        this.mContentValues.put("cookiedata", paramString4 + "=" + paramString5);
      if ((!paramBoolean2) && (!TextUtils.isEmpty(paramString1)))
        break label228;
      this.mContentValues.put("visibility", Integer.valueOf(2));
    }
    while (true)
    {
      if (paramBoolean1)
      {
        this.mContentValues.put("allowed_network_types", Integer.valueOf(2));
        this.mContentValues.put("is_public_api", Boolean.valueOf(true));
        this.mContentValues.put("allow_roaming", Boolean.valueOf(true));
      }
      return;
      this.mContentValues.put("destination", Integer.valueOf(2));
      break;
      label228: this.mContentValues.put("visibility", Integer.valueOf(0));
      this.mContentValues.put("title", paramString1);
    }
  }

  ContentValues toContentValues()
  {
    return this.mContentValues;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadRequest
 * JD-Core Version:    0.6.2
 */
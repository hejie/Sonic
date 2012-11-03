package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import java.util.EnumSet;

public class DownloadImpl
  implements InternalDownload
{
  private static final EnumSet<Download.DownloadState> COMPLETED_STATES = EnumSet.of(Download.DownloadState.CANCELLED, Download.DownloadState.ERROR, Download.DownloadState.SUCCESS);
  private long mActualSize;
  private Uri mContentUri;
  private final String mCookieName;
  private final String mCookieValue;
  private final Uri mFileUri;
  private int mHttpStatus;
  private final boolean mInvisible;
  private DownloadProgress mLastProgress;
  private long mMaximumSize;
  private final Obb mObb;
  private final String mPackageName;
  private final int mPackageVersion;
  Download.DownloadState mState;
  private final String mTitle;
  private final String mUrl;
  private final boolean mWifiOnly;

  public DownloadImpl(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, Uri paramUri, long paramLong1, long paramLong2, Obb paramObb, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mUrl = paramString1;
    this.mTitle = paramString2;
    this.mPackageName = paramString3;
    this.mPackageVersion = paramInt;
    this.mCookieName = paramString4;
    this.mCookieValue = paramString5;
    this.mFileUri = paramUri;
    this.mActualSize = paramLong1;
    this.mMaximumSize = paramLong2;
    this.mObb = paramObb;
    this.mWifiOnly = paramBoolean1;
    this.mInvisible = paramBoolean2;
    setState(Download.DownloadState.UNQUEUED);
  }

  public DownloadRequest createDownloadRequest(String paramString1, String paramString2)
  {
    return new DownloadRequest(Uri.parse(getUrl()), this.mTitle, paramString1, paramString2, this.mCookieName, this.mCookieValue, this.mFileUri, this.mWifiOnly, this.mInvisible);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (paramObject == this)
      bool = true;
    while (true)
    {
      return bool;
      if (!(paramObject instanceof DownloadImpl))
      {
        bool = false;
      }
      else
      {
        DownloadImpl localDownloadImpl = (DownloadImpl)paramObject;
        bool = this.mUrl.equals(localDownloadImpl.mUrl);
      }
    }
  }

  public Uri getContentUri()
  {
    Utils.ensureOnMainThread();
    return this.mContentUri;
  }

  public int getHttpStatus()
  {
    return this.mHttpStatus;
  }

  public long getMaximumSize()
  {
    return this.mMaximumSize;
  }

  public Obb getObb()
  {
    return this.mObb;
  }

  public String getPackageName()
  {
    Utils.ensureOnMainThread();
    return this.mPackageName;
  }

  public DownloadProgress getProgress()
  {
    Utils.ensureOnMainThread();
    return this.mLastProgress;
  }

  public Uri getRequestedDestination()
  {
    return this.mFileUri;
  }

  public Download.DownloadState getState()
  {
    Utils.ensureOnMainThread();
    return this.mState;
  }

  public String getUrl()
  {
    Utils.ensureOnMainThread();
    return this.mUrl;
  }

  public int hashCode()
  {
    return this.mUrl.hashCode();
  }

  public boolean isCompleted()
  {
    return COMPLETED_STATES.contains(this.mState);
  }

  public boolean isObb()
  {
    if (this.mObb != null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void setContentUri(Uri paramUri)
  {
    this.mContentUri = paramUri;
  }

  public void setHttpStatus(int paramInt)
  {
    this.mHttpStatus = paramInt;
  }

  public void setProgress(DownloadProgress paramDownloadProgress)
  {
    Utils.ensureOnMainThread();
    this.mLastProgress = paramDownloadProgress;
    if ((this.mActualSize == -1L) && (paramDownloadProgress != null) && (paramDownloadProgress.bytesTotal > 0L))
      this.mActualSize = paramDownloadProgress.bytesTotal;
  }

  public void setState(Download.DownloadState paramDownloadState)
  {
    if (isCompleted())
      throw new IllegalStateException("Received state update when already completed.");
    if (this.mState == paramDownloadState)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = this;
      arrayOfObject2[1] = this.mState;
      FinskyLog.d("Duplicate state set for '%s' (%s). Already in that state", arrayOfObject2);
    }
    while (true)
    {
      this.mState = paramDownloadState;
      return;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = this;
      arrayOfObject1[1] = this.mState;
      arrayOfObject1[2] = paramDownloadState;
      FinskyLog.d("%s from %s to %s.", arrayOfObject1);
    }
  }

  public String toString()
  {
    String str;
    if (this.mPackageName != null)
      str = this.mPackageName;
    while (true)
    {
      if (str == null)
        str = "untitled-download";
      return str;
      if (this.mObb != null)
        str = "obb-for-" + this.mObb.getPackage();
      else
        str = "self-update-download";
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.DownloadImpl
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.download.obb;

import com.google.android.finsky.download.Storage;
import com.google.android.finsky.utils.FinskyLog;
import java.io.File;

class ObbImpl
  implements Obb
{
  private final String TEMP_OBB_FILE_PREFIX = "temp.";
  private String mFileName;
  private final boolean mIsPatch;
  private final String mPackageName;
  private long mSize;
  private int mState = -1;
  private String mUrl;
  private int mVersionCode;

  ObbImpl(boolean paramBoolean, String paramString1, int paramInt1, String paramString2, long paramLong, int paramInt2)
  {
    this.mIsPatch = paramBoolean;
    this.mVersionCode = paramInt1;
    this.mUrl = paramString2;
    this.mSize = paramLong;
    this.mPackageName = paramString1;
    this.mFileName = generateFileName();
    this.mState = paramInt2;
  }

  private String generateFileName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getTypeString()).append(".").append(this.mVersionCode).append(".").append(this.mPackageName).append(".obb");
    return localStringBuilder.toString();
  }

  private String getTypeString()
  {
    if (this.mIsPatch);
    for (String str = "patch"; ; str = "main")
      return str;
  }

  private boolean isDownloaded()
  {
    boolean bool = false;
    File localFile = getFile();
    if ((localFile != null) && (localFile.exists()) && (localFile.length() == this.mSize))
      bool = true;
    return bool;
  }

  private void logFinalizeProblem(String paramString)
  {
    File localFile1;
    try
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = toString();
      FinskyLog.w("Failure %s while finalizing %s", arrayOfObject1);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = this.mFileName;
      arrayOfObject2[1] = Long.valueOf(this.mSize);
      FinskyLog.w(" file=%s, size=%d", arrayOfObject2);
      localFile1 = ObbFactory.getParentDirectory(this.mPackageName);
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = localFile1.getAbsolutePath();
      FinskyLog.w(" Contents of %s:", arrayOfObject3);
      if (!localFile1.exists())
        FinskyLog.w(" (Does not exist)", new Object[0]);
      else if (!localFile1.isDirectory())
        FinskyLog.w(" (Is not a directory)", new Object[0]);
    }
    catch (Exception localException)
    {
      FinskyLog.wtf(localException, "Unexpected exception", new Object[0]);
    }
    File[] arrayOfFile = localFile1.listFiles();
    if (arrayOfFile == null)
    {
      FinskyLog.w(" (listFiles() returned null)", new Object[0]);
    }
    else
    {
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
      {
        File localFile2 = arrayOfFile[j];
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = localFile2.getName();
        arrayOfObject4[1] = Long.valueOf(localFile2.length());
        FinskyLog.w("  name=%s size=%d", arrayOfObject4);
      }
    }
  }

  private void reset()
  {
    this.mVersionCode = -1;
    this.mUrl = "";
    this.mSize = -1L;
    this.mFileName = "";
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject);
    ObbImpl localObbImpl;
    do
    {
      while (true)
      {
        return bool;
        if ((paramObject == null) || (getClass() != paramObject.getClass()))
        {
          bool = false;
        }
        else
        {
          localObbImpl = (ObbImpl)paramObject;
          if (this.mFileName != null)
          {
            if (this.mFileName.equals(localObbImpl.mFileName));
          }
          else
            while (localObbImpl.mFileName != null)
            {
              bool = false;
              break;
            }
          if (this.mIsPatch != localObbImpl.mIsPatch)
          {
            bool = false;
          }
          else if (this.mVersionCode != localObbImpl.mVersionCode)
          {
            bool = false;
          }
          else
          {
            if (this.mUrl != null)
            {
              if (this.mUrl.equals(localObbImpl.mUrl));
            }
            else
              while (localObbImpl.mUrl != null)
              {
                bool = false;
                break;
              }
            if (this.mSize == localObbImpl.mSize)
              break;
            bool = false;
          }
        }
      }
      if (this.mState >= 0)
      {
        if (this.mState == localObbImpl.mState);
      }
      else
        while (localObbImpl.mState >= 0)
        {
          bool = false;
          break;
        }
      if (this.mPackageName == null)
        break;
    }
    while (this.mPackageName.equals(localObbImpl.mPackageName));
    while (true)
    {
      bool = false;
      break;
      if (localObbImpl.mPackageName == null)
        break;
    }
  }

  public boolean finalizeTempFile()
  {
    boolean bool = false;
    File localFile1 = getFile();
    if (localFile1 == null)
      logFinalizeProblem("main file null");
    while (true)
    {
      return bool;
      File localFile2 = getTempFile();
      if (localFile2 == null)
        logFinalizeProblem("temp file null");
      else if (localFile2.length() != this.mSize)
        logFinalizeProblem("size mismatch: tempfile size=" + String.valueOf(localFile2.length()));
      else if (!localFile2.renameTo(localFile1))
        logFinalizeProblem("renameTo() returned false");
      else
        bool = true;
    }
  }

  public File getFile()
  {
    if ((!Storage.externalStorageAvailable()) || (this.mSize <= 0L));
    File localFile2;
    for (File localFile1 = null; ; localFile1 = new File(localFile2, this.mFileName))
    {
      return localFile1;
      localFile2 = ObbFactory.getParentDirectory(this.mPackageName);
      if (!localFile2.exists())
        localFile2.mkdirs();
    }
  }

  public String getPackage()
  {
    return this.mPackageName;
  }

  public long getSize()
  {
    return this.mSize;
  }

  public int getState()
  {
    return this.mState;
  }

  public File getTempFile()
  {
    File localFile1 = getFile();
    if (localFile1 == null);
    for (File localFile2 = null; ; localFile2 = new File(localFile1.getParentFile(), "temp." + localFile1.getName()))
      return localFile2;
  }

  public String getUrl()
  {
    return this.mUrl;
  }

  public boolean isPatch()
  {
    return this.mIsPatch;
  }

  public void setState(int paramInt)
  {
    if (paramInt == 5)
      reset();
    this.mState = paramInt;
  }

  public void syncStateWithStorage()
  {
    if (this.mState == 5);
    while (true)
    {
      return;
      if (!Storage.externalStorageAvailable())
        setState(4);
      else if (!isDownloaded())
        setState(4);
      else
        setState(3);
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    if (this.mIsPatch);
    for (String str = "Patch"; ; str = "Main")
    {
      arrayOfObject[0] = str;
      arrayOfObject[1] = this.mPackageName;
      arrayOfObject[2] = Integer.valueOf(this.mVersionCode);
      arrayOfObject[3] = ObbState.toString(this.mState);
      return String.format("%s: %s v:%d %s", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.download.obb.ObbImpl
 * JD-Core Version:    0.6.2
 */
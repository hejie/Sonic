package com.google.android.finsky.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AppActionAnalyzer;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.download.Storage;
import com.google.android.finsky.remoting.protos.AndroidAppDelivery.EncryptionParams;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PackageManagerHelper
{
  public static void addAppShortcut(Context paramContext, String paramString)
  {
    if (!new AppActionAnalyzer(paramString, FinskyApp.get().getAppStates(), FinskyApp.get().getLibraries()).isLaunchable);
    while (true)
    {
      return;
      PackageManager localPackageManager = paramContext.getPackageManager();
      try
      {
        localApplicationInfo = localPackageManager.getApplicationInfo(paramString, 0);
        Resources localResources = localPackageManager.getResourcesForApplication(localApplicationInfo);
        String str = localResources.getResourceName(localApplicationInfo.icon);
        Intent localIntent1 = localPackageManager.getLaunchIntentForPackage(paramString);
        int i = localPackageManager.getActivityInfo(localIntent1.getComponent(), 0).labelRes;
        if (i != 0)
        {
          localObject = localResources.getString(i);
          Intent localIntent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
          localIntent2.putExtra("android.intent.extra.shortcut.NAME", (CharSequence)localObject);
          localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
          Intent.ShortcutIconResource localShortcutIconResource = new Intent.ShortcutIconResource();
          localShortcutIconResource.packageName = paramString;
          localShortcutIconResource.resourceName = str;
          localIntent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", localShortcutIconResource);
          localIntent2.putExtra("duplicate", false);
          paramContext.sendBroadcast(localIntent2);
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        while (true)
        {
          ApplicationInfo localApplicationInfo;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localNotFoundException.getMessage();
          FinskyLog.d("Unable to load resources: %s", arrayOfObject);
          break;
          CharSequence localCharSequence = localPackageManager.getApplicationLabel(localApplicationInfo);
          Object localObject = localCharSequence;
        }
      }
      catch (Exception localException)
      {
        FinskyLog.wtf(localException, "addAppShortcut failed", new Object[0]);
      }
    }
  }

  private static String getApplicationName(String paramString)
  {
    Object localObject = null;
    try
    {
      PackageManager localPackageManager = FinskyApp.get().getPackageManager();
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramString, 0);
      if (localApplicationInfo != null)
      {
        CharSequence localCharSequence = localPackageManager.getApplicationLabel(localApplicationInfo);
        if (localCharSequence != null)
        {
          String str = localCharSequence.toString();
          localObject = str;
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return localObject;
  }

  private static int getInstallFailMessageId(int paramInt)
  {
    int i = 2131165363;
    switch (paramInt)
    {
    default:
      i = -1;
    case -5:
    case -1:
    case -2:
    case -3:
    case -4:
    case -7:
    case -8:
    case -9:
    case -10:
    case -11:
    case -12:
    case -13:
    case -14:
    case -16:
    case -100:
    case -101:
    case -103:
    case -104:
    case -105:
    case -106:
    case -107:
    case -108:
    case -109:
    case -17:
    case -18:
    case -20:
    case -19:
    case -21:
    case -22:
    case -23:
    }
    while (true)
    {
      return i;
      i = 2131165364;
      continue;
      i = 2131165365;
      continue;
      i = 2131165366;
      continue;
      i = 2131165367;
      continue;
      i = 2131165368;
      continue;
      i = 2131165369;
      continue;
      i = 2131165370;
      continue;
      i = 2131165364;
      continue;
      i = 2131165371;
      continue;
      i = 2131165372;
      continue;
      i = 2131165373;
      continue;
      i = 2131165374;
      continue;
      i = 2131165364;
      continue;
      i = 2131165375;
      continue;
      i = 2131165376;
      continue;
      i = 2131165377;
      continue;
      i = 2131165376;
      continue;
      i = 2131165378;
      continue;
      i = 2131165364;
      continue;
      i = 2131165375;
      continue;
      i = 2131165375;
      continue;
      i = 2131165379;
      continue;
      i = 2131165380;
      continue;
      i = 2131165381;
      continue;
      i = 2131165384;
      continue;
      i = 2131165383;
      continue;
      i = 2131165385;
    }
  }

  public static void installPackage(Uri paramUri, String paramString1, long paramLong, String paramString2, boolean paramBoolean1, InstallPackageListener paramInstallPackageListener, boolean paramBoolean2, boolean paramBoolean3, String paramString3, AndroidAppDelivery.EncryptionParams paramEncryptionParams)
  {
    if (paramInstallPackageListener == null)
      paramInstallPackageListener = new InstallPackageListener()
      {
        public void installCompleted(boolean paramAnonymousBoolean, String paramAnonymousString)
        {
        }
      };
    new OnCompleteListenerNotifier(paramUri, paramString1, paramLong, paramString2, paramBoolean1, paramInstallPackageListener, paramBoolean2, paramBoolean3, paramString3, paramEncryptionParams, null).execute(new Void[0]);
  }

  private static boolean isAlreadyInstalled(String paramString)
  {
    boolean bool = false;
    if (paramString != null);
    try
    {
      PackageInfo localPackageInfo = FinskyApp.get().getPackageManager().getPackageInfo(paramString, 0);
      if (localPackageInfo != null);
      for (bool = true; ; bool = false)
        label24: return bool;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label24;
    }
  }

  public static void notifyFailedInstall(final String paramString, int paramInt)
  {
    int i = getInstallFailMessageId(paramInt);
    FinskyApp localFinskyApp = FinskyApp.get();
    if (i >= 0);
    Object[] arrayOfObject;
    for (final String str = localFinskyApp.getString(i); ; str = localFinskyApp.getString(2131165382, arrayOfObject))
    {
      new Handler(Looper.getMainLooper()).post(new Runnable()
      {
        public void run()
        {
          switch (this.val$returnCode)
          {
          default:
          case -104:
          }
          for (int i = -1; ; i = 1)
          {
            Notifier localNotifier = FinskyApp.get().getNotifier();
            localNotifier.hideAllMessagesForPackage(paramString);
            localNotifier.showInstallationFailureMessage(PackageManagerHelper.getApplicationName(paramString), paramString, str, i);
            return;
          }
        }
      });
      return;
      arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
    }
  }

  public static void uninstallPackage(String paramString)
  {
    PackageManagerUtils.uninstallPackage(FinskyApp.get(), paramString);
  }

  // ERROR //
  public static boolean verifyApk(java.io.InputStream paramInputStream, long paramLong, String paramString)
  {
    // Byte code:
    //   0: new 257	java/io/BufferedInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 260	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   8: aload_3
    //   9: lload_1
    //   10: invokestatic 266	com/google/android/finsky/utils/Sha1Util:verify	(Ljava/io/InputStream;Ljava/lang/String;J)Z
    //   13: istore 9
    //   15: iload 9
    //   17: istore 7
    //   19: aload_0
    //   20: ifnull +7 -> 27
    //   23: aload_0
    //   24: invokevirtual 271	java/io/InputStream:close	()V
    //   27: iload 7
    //   29: ireturn
    //   30: astore 10
    //   32: ldc_w 273
    //   35: iconst_0
    //   36: anewarray 4	java/lang/Object
    //   39: invokestatic 276	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   42: goto -15 -> 27
    //   45: astore 6
    //   47: aload_0
    //   48: ifnull +7 -> 55
    //   51: aload_0
    //   52: invokevirtual 271	java/io/InputStream:close	()V
    //   55: iconst_0
    //   56: istore 7
    //   58: goto -31 -> 27
    //   61: astore 8
    //   63: ldc_w 273
    //   66: iconst_0
    //   67: anewarray 4	java/lang/Object
    //   70: invokestatic 276	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   73: goto -18 -> 55
    //   76: astore 4
    //   78: aload_0
    //   79: ifnull +7 -> 86
    //   82: aload_0
    //   83: invokevirtual 271	java/io/InputStream:close	()V
    //   86: aload 4
    //   88: athrow
    //   89: astore 5
    //   91: ldc_w 273
    //   94: iconst_0
    //   95: anewarray 4	java/lang/Object
    //   98: invokestatic 276	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   101: goto -15 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   23	27	30	java/io/IOException
    //   0	15	45	java/io/IOException
    //   51	55	61	java/io/IOException
    //   0	15	76	finally
    //   82	86	89	java/io/IOException
  }

  private static boolean verifySize(Context paramContext, Uri paramUri, long paramLong)
  {
    boolean bool = false;
    try
    {
      ParcelFileDescriptor localParcelFileDescriptor = paramContext.getContentResolver().openFileDescriptor(paramUri, "r");
      long l = localParcelFileDescriptor.getStatSize();
      localParcelFileDescriptor.close();
      if (paramLong == l)
        bool = true;
      label38: return bool;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      break label38;
    }
    catch (IOException localIOException)
    {
      break label38;
    }
  }

  public static abstract interface InstallPackageListener
  {
    public abstract void installCompleted(boolean paramBoolean, String paramString);
  }

  private static class OnCompleteListenerNotifier extends AsyncTask<Void, Void, Uri>
  {
    private final Uri mContentUri;
    private final boolean mDoNotifications;
    private final AndroidAppDelivery.EncryptionParams mEncryptionParams;
    private final String mExpectedSignature;
    private final long mExpectedSize;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final boolean mIsForwardLocked;
    private final boolean mIsUpdate;
    private final String mPackageName;
    private final PackageManagerHelper.InstallPackageListener mPostInstallCallback;
    private final String mTitle;
    private volatile boolean mVerified;

    private OnCompleteListenerNotifier(Uri paramUri, String paramString1, long paramLong, String paramString2, boolean paramBoolean1, PackageManagerHelper.InstallPackageListener paramInstallPackageListener, boolean paramBoolean2, boolean paramBoolean3, String paramString3, AndroidAppDelivery.EncryptionParams paramEncryptionParams)
    {
      this.mContentUri = paramUri;
      this.mTitle = paramString1;
      this.mExpectedSize = paramLong;
      this.mExpectedSignature = paramString2;
      this.mDoNotifications = paramBoolean1;
      this.mPostInstallCallback = paramInstallPackageListener;
      this.mIsUpdate = paramBoolean2;
      this.mIsForwardLocked = paramBoolean3;
      this.mPackageName = paramString3;
      this.mEncryptionParams = paramEncryptionParams;
    }

    protected Uri doInBackground(Void[] paramArrayOfVoid)
    {
      if (this.mExpectedSize >= 0L)
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = this.mTitle;
          arrayOfObject2[1] = Long.valueOf(this.mExpectedSize);
          FinskyLog.v("Performing verification of '%s' (expectedSize=%d)...", arrayOfObject2);
        }
      while (true)
      {
        try
        {
          if (this.mEncryptionParams == null)
          {
            this.mVerified = PackageManagerHelper.verifyApk(FinskyApp.get().getContentResolver().openInputStream(this.mContentUri), this.mExpectedSize, this.mExpectedSignature);
            if (FinskyLog.DEBUG)
            {
              Object[] arrayOfObject1 = new Object[2];
              arrayOfObject1[0] = this.mTitle;
              arrayOfObject1[1] = Boolean.valueOf(this.mVerified);
              FinskyLog.v("Verification of '%s' finished (result=%s).", arrayOfObject1);
            }
            return Storage.getFileUriForContentUri(this.mContentUri);
          }
          this.mVerified = PackageManagerHelper.verifySize(FinskyApp.get(), this.mContentUri, this.mExpectedSize);
          continue;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          this.mVerified = false;
          continue;
        }
        FinskyLog.d("Signature check not required.", new Object[0]);
        this.mVerified = true;
      }
    }

    protected void onPostExecute(Uri paramUri)
    {
      if (!this.mVerified)
      {
        FinskyLog.w("Signature check failed, aborting installation.", new Object[0]);
        if (this.mDoNotifications)
          PackageManagerHelper.notifyFailedInstall(this.mPackageName, -2);
        this.mPostInstallCallback.installCompleted(false, "signature-check");
      }
      while (true)
      {
        return;
        int i = 2;
        if (this.mIsForwardLocked)
          i |= 1;
        boolean bool = PackageManagerHelper.isAlreadyInstalled(this.mPackageName);
        PackageManagerUtils.PackageInstallObserver local1 = new PackageManagerUtils.PackageInstallObserver()
        {
          public void packageInstalled(String paramAnonymousString, final int paramAnonymousInt)
          {
            FinskyApp.get().getPackageInfoRepository().invalidate(paramAnonymousString);
            try
            {
              FinskyLog.d("Package install status for \"" + paramAnonymousString + "\" is " + paramAnonymousInt, new Object[0]);
              if (paramAnonymousString == null)
                paramAnonymousString = PackageManagerHelper.OnCompleteListenerNotifier.this.mPackageName;
              while (paramAnonymousInt == 1)
              {
                final String str2 = paramAnonymousString;
                PackageManagerHelper.OnCompleteListenerNotifier.this.mHandler.post(new Runnable()
                {
                  public void run()
                  {
                    PackageManagerHelper.OnCompleteListenerNotifier.this.mPostInstallCallback.installCompleted(true, null);
                    if ((PackageManagerHelper.OnCompleteListenerNotifier.this.mDoNotifications) && (!PackageManagerHelper.OnCompleteListenerNotifier.this.mIsUpdate) && (((Boolean)VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue()))
                      PackageManagerHelper.addAppShortcut(FinskyApp.get(), str2);
                  }
                });
                return;
                if (!paramAnonymousString.equals(PackageManagerHelper.OnCompleteListenerNotifier.this.mPackageName))
                  FinskyLog.w("Package name mismatsch: ours: " + PackageManagerHelper.OnCompleteListenerNotifier.this.mPackageName + " package manager's: " + paramAnonymousString, new Object[0]);
              }
            }
            catch (Exception localException)
            {
              final String str1 = localException.getClass().getSimpleName();
              PackageManagerHelper.OnCompleteListenerNotifier.this.mHandler.post(new Runnable()
              {
                public void run()
                {
                  PackageManagerHelper.OnCompleteListenerNotifier.this.mPostInstallCallback.installCompleted(false, str1);
                }
              });
              FinskyLog.wtf(localException, "Package install observer exception", new Object[0]);
              return;
              if (PackageManagerHelper.OnCompleteListenerNotifier.this.mDoNotifications)
                PackageManagerHelper.notifyFailedInstall(paramAnonymousString, paramAnonymousInt);
              PackageManagerHelper.OnCompleteListenerNotifier.this.mHandler.post(new Runnable()
              {
                public void run()
                {
                  PackageManagerHelper.OnCompleteListenerNotifier.this.mPostInstallCallback.installCompleted(false, String.valueOf(paramAnonymousInt));
                }
              });
            }
          }
        };
        if (this.mDoNotifications)
          FinskyApp.get().getNotifier().showInstallingMessage(this.mTitle, this.mPackageName, bool);
        String str1 = null;
        String str2 = null;
        if (this.mEncryptionParams != null)
        {
          if (this.mEncryptionParams.getVersion() != 1)
          {
            if (this.mDoNotifications)
              PackageManagerHelper.notifyFailedInstall(this.mPackageName, -2);
            this.mPostInstallCallback.installCompleted(false, "encryption-version");
          }
          else if (!PackageManagerUtils.isEncryptionSupported())
          {
            if (this.mDoNotifications)
              PackageManagerHelper.notifyFailedInstall(this.mPackageName, -2);
            this.mPostInstallCallback.installCompleted(false, "encryption-not-supported");
          }
          else
          {
            str1 = this.mEncryptionParams.getEncryptionKey();
            str2 = this.mEncryptionParams.getHmacKey();
            i |= 1;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = this.mPackageName;
            FinskyLog.d("Install encrypted %s", arrayOfObject);
          }
        }
        else
        {
          if (paramUri == null);
          for (Uri localUri = this.mContentUri; ; localUri = paramUri)
          {
            if (localUri == null)
              break label281;
            PackageManagerUtils.installPackage(FinskyApp.get(), localUri, local1, i, this.mExpectedSize, str1, str2);
            break;
          }
          label281: if (this.mDoNotifications)
            PackageManagerHelper.notifyFailedInstall(this.mPackageName, -3);
          this.mPostInstallCallback.installCompleted(false, "invalid-uri");
        }
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.PackageManagerHelper
 * JD-Core Version:    0.6.2
 */
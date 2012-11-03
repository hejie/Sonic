package com.google.android.vending.verifier;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.vending.verifier.api.PackageVerificationApi;
import com.google.android.vending.verifier.api.PackageVerificationResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class PackageVerificationService extends Service
{
  private static PackageVerificationService sInstance;
  private int mLastStartId;
  private ArrayList<VerificationState> mVerifications = Lists.newArrayList(1);

  private void cancelDialog(VerificationState paramVerificationState)
  {
    if (paramVerificationState.mDialog != null)
    {
      paramVerificationState.mDialog.finish();
      paramVerificationState.mDialog = null;
    }
  }

  private void cancelVerificationIntent(Intent paramIntent)
  {
    int i = paramIntent.getExtras().getInt("android.content.pm.extra.VERIFICATION_ID");
    VerificationState localVerificationState = findVerification(i);
    if ((localVerificationState != null) && (localVerificationState.mResult != -1))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      FinskyLog.d("Cancel active verification id=%d", arrayOfObject);
      cancelDialog(localVerificationState);
      this.mVerifications.remove(localVerificationState);
    }
  }

  private void destroyAllVerifications()
  {
    Utils.ensureOnMainThread();
    while (!this.mVerifications.isEmpty())
    {
      VerificationState localVerificationState = (VerificationState)this.mVerifications.remove(0);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(localVerificationState.id);
      FinskyLog.w("Destroying orphaned verification id=%d", arrayOfObject);
      reportVerificationResult(this, localVerificationState.id, 1);
      cancelDialog(localVerificationState);
    }
  }

  private void extendTimeout(int paramInt1, int paramInt2)
  {
    FinskyApp.get().getPackageManager().extendVerificationTimeout(paramInt1, paramInt2, ((Long)G.platformAntiMalwareDialogDelayMs.get()).longValue());
  }

  private VerificationState findVerification(int paramInt)
  {
    Iterator localIterator = this.mVerifications.iterator();
    VerificationState localVerificationState;
    do
    {
      if (!localIterator.hasNext())
        break;
      localVerificationState = (VerificationState)localIterator.next();
    }
    while (localVerificationState.id != paramInt);
    while (true)
    {
      return localVerificationState;
      localVerificationState = null;
    }
  }

  private boolean getPackageInfo(VerificationState paramVerificationState)
  {
    boolean bool = false;
    Uri localUri = paramVerificationState.dataUri;
    if (localUri == null)
    {
      Object[] arrayOfObject6 = new Object[1];
      arrayOfObject6[0] = Integer.valueOf(paramVerificationState.id);
      FinskyLog.d("Null data for request id=%d", arrayOfObject6);
    }
    while (true)
    {
      return bool;
      if (!"file".equalsIgnoreCase(localUri.getScheme()))
      {
        Object[] arrayOfObject5 = new Object[2];
        arrayOfObject5[0] = localUri;
        arrayOfObject5[1] = Integer.valueOf(paramVerificationState.id);
        FinskyLog.d("Unsupported scheme for %s in request id=%d", arrayOfObject5);
        continue;
      }
      String str = localUri.getPath();
      File localFile = new File(str);
      if (!localFile.exists())
      {
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = localUri;
        arrayOfObject4[1] = Integer.valueOf(paramVerificationState.id);
        FinskyLog.d("Cannot find file for %s in request id=%d", arrayOfObject4);
        continue;
      }
      if (!localFile.canRead())
      {
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = localUri;
        arrayOfObject3[1] = Integer.valueOf(paramVerificationState.id);
        FinskyLog.d("Cannot read file for %s in request id=%d", arrayOfObject3);
        continue;
      }
      PackageInfo localPackageInfo;
      try
      {
        localPackageInfo = FinskyApp.get().getPackageManager().getPackageArchiveInfo(str, 64);
        if (localPackageInfo != null)
          break label270;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localUri;
        arrayOfObject2[1] = Integer.valueOf(paramVerificationState.id);
        FinskyLog.d("Cannot read archive for %s in request id=%d", arrayOfObject2);
      }
      catch (Exception localException)
      {
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = localUri;
        arrayOfObject1[1] = Integer.valueOf(paramVerificationState.id);
        arrayOfObject1[2] = localException;
        FinskyLog.d("Exception reading %s in request id=%d %s", arrayOfObject1);
      }
      continue;
      label270: paramVerificationState.mPackageName = localPackageInfo.packageName;
      paramVerificationState.mVersion = Integer.valueOf(localPackageInfo.versionCode);
      paramVerificationState.mLength = localFile.length();
      try
      {
        paramVerificationState.mSha256 = getSha256Hash(localFile);
        localPackageInfo.applicationInfo.publicSourceDir = str;
        CharSequence localCharSequence = localPackageInfo.applicationInfo.loadLabel(FinskyApp.get().getPackageManager());
        if (localCharSequence != null)
          paramVerificationState.mLabel = localCharSequence.toString();
        paramVerificationState.mSignatures = localPackageInfo.signatures;
        bool = true;
      }
      catch (IOException localIOException)
      {
        FinskyLog.d("Error while calculating sha256 for file=%s, error=%s", new Object[] { localUri, localIOException });
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        throw new RuntimeException(localNoSuchAlgorithmException);
      }
    }
  }

  private static byte[] getSha256Hash(File paramFile)
    throws IOException, NoSuchAlgorithmException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA256");
    Object localObject1 = null;
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      try
      {
        byte[] arrayOfByte = new byte[16384];
        int i = localFileInputStream.read(arrayOfByte);
        if (i >= 0)
          localMessageDigest.update(arrayOfByte, 0, i);
      }
      finally
      {
        localObject1 = localFileInputStream;
        label54: if (localObject1 != null)
          localObject1.close();
      }
    }
    finally
    {
      break label54;
    }
  }

  private void handleVerificationIntent(Intent paramIntent)
  {
    VerificationState localVerificationState = new VerificationState(paramIntent);
    this.mVerifications.add(localVerificationState);
    new WorkerTask(localVerificationState).execute();
  }

  public static boolean registerDialog(int paramInt, Activity paramActivity)
  {
    boolean bool = false;
    Utils.ensureOnMainThread();
    if (sInstance == null);
    while (true)
    {
      return bool;
      VerificationState localVerificationState = sInstance.findVerification(paramInt);
      if (localVerificationState != null)
      {
        localVerificationState.mDialog = paramActivity;
        bool = true;
      }
    }
  }

  private void reportAndCleanup(Context paramContext, VerificationState paramVerificationState)
  {
    Utils.ensureOnMainThread();
    reportVerificationResult(paramContext, paramVerificationState.id, paramVerificationState.mResult);
    this.mVerifications.remove(paramVerificationState);
    if (this.mVerifications.isEmpty())
      stopSelf(this.mLastStartId);
  }

  public static void reportConsentDialog(int paramInt, boolean paramBoolean)
  {
    Utils.ensureOnMainThread();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Integer.valueOf(paramInt);
    FinskyLog.d("User consent %b for id=%d", arrayOfObject);
    if (sInstance == null);
    while (true)
    {
      return;
      VerificationState localVerificationState = sInstance.findVerification(paramInt);
      if (localVerificationState != null)
        if (paramBoolean)
        {
          FinskyPreferences.acceptedAntiMalwareConsent.put(Boolean.valueOf(true));
          sInstance.sendVerificationRequest(localVerificationState);
        }
        else
        {
          localVerificationState.mResult = 1;
          sInstance.reportAndCleanup(sInstance, localVerificationState);
        }
    }
  }

  public static void reportUserChoice(int paramInt1, int paramInt2)
  {
    int i = 0;
    Utils.ensureOnMainThread();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt2);
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    FinskyLog.d("User selected %d for id=%d", arrayOfObject);
    if (sInstance == null);
    VerificationState localVerificationState;
    do
    {
      return;
      localVerificationState = sInstance.findVerification(paramInt1);
    }
    while (localVerificationState == null);
    if (localVerificationState.mResult != -1)
      if (paramInt2 != 1)
        break label105;
    while (true)
    {
      PackageVerificationApi.reportUserDecision(i, localVerificationState.mToken, new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(this.val$state.id);
          arrayOfObject[1] = paramAnonymousVolleyError;
          FinskyLog.d("Verification feedback id=%d error response %s", arrayOfObject);
        }
      });
      localVerificationState.mResult = paramInt2;
      sInstance.reportAndCleanup(sInstance, localVerificationState);
      break;
      label105: i = 1;
    }
  }

  private void reportVerificationResult(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext.getPackageManager().verifyPendingInstall(paramInt1, paramInt2);
  }

  private static void resolveHosts(VerificationState paramVerificationState)
  {
    String str2;
    if (paramVerificationState.originatingUri != null)
      str2 = paramVerificationState.originatingUri.getHost();
    try
    {
      paramVerificationState.originatingIp = InetAddress.getByName(str2);
      if (paramVerificationState.referrerUri != null)
        str1 = paramVerificationState.referrerUri.getHost();
    }
    catch (UnknownHostException localUnknownHostException2)
    {
      try
      {
        paramVerificationState.referrerIp = InetAddress.getByName(str1);
        return;
        localUnknownHostException2 = localUnknownHostException2;
        FinskyLog.d("Could not resolve host %s", new Object[] { str2 });
      }
      catch (UnknownHostException localUnknownHostException1)
      {
        while (true)
        {
          String str1;
          FinskyLog.d("Could not resolve host %s", new Object[] { str1 });
        }
      }
    }
  }

  private void sendVerificationRequest(final VerificationState paramVerificationState)
  {
    String str = getResources().getConfiguration().locale.toString();
    long l = ((Long)DfeApiConfig.androidId.get()).longValue();
    PackageVerificationApi.verifyApp(paramVerificationState.mSha256, paramVerificationState.mLength, paramVerificationState.mPackageName, paramVerificationState.mVersion, paramVerificationState.installingPackage, paramVerificationState.originatingUri, paramVerificationState.referrerUri, paramVerificationState.originatingIp, paramVerificationState.referrerIp, str, l, new Response.Listener()
    {
      public void onResponse(PackageVerificationResult paramAnonymousPackageVerificationResult)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramVerificationState.id);
        arrayOfObject[1] = Integer.valueOf(paramAnonymousPackageVerificationResult.verdict);
        FinskyLog.d("Verification id=%d response=%d", arrayOfObject);
        PackageVerificationService localPackageVerificationService = PackageVerificationService.this;
        paramVerificationState.mToken = paramAnonymousPackageVerificationResult.token;
        switch (paramAnonymousPackageVerificationResult.verdict)
        {
        case 2:
        default:
          paramVerificationState.mResult = 1;
          PackageVerificationService.this.reportAndCleanup(localPackageVerificationService, paramVerificationState);
        case 1:
        case 3:
        }
        while (true)
        {
          return;
          paramVerificationState.mDescription = paramAnonymousPackageVerificationResult.description;
          paramVerificationState.mMoreInfoUri = paramAnonymousPackageVerificationResult.moreInfoUri;
          paramVerificationState.mResult = -1;
          PackageVerificationService.this.reportVerificationResult(localPackageVerificationService, paramVerificationState.id, paramVerificationState.mResult);
          PackageWarningDialog.show(localPackageVerificationService, paramVerificationState.id, true, paramVerificationState.mLabel, paramVerificationState.mDescription);
          continue;
          paramVerificationState.mDescription = paramAnonymousPackageVerificationResult.description;
          paramVerificationState.mMoreInfoUri = paramAnonymousPackageVerificationResult.moreInfoUri;
          PackageVerificationService.this.extendTimeout(paramVerificationState.id, 1);
          PackageWarningDialog.show(localPackageVerificationService, paramVerificationState.id, false, paramVerificationState.mLabel, paramVerificationState.mDescription);
        }
      }
    }
    , new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramVerificationState.id);
        arrayOfObject[1] = paramAnonymousVolleyError;
        FinskyLog.d("Verification id=%d error response %s", arrayOfObject);
        PackageVerificationService.this.reportAndCleanup(PackageVerificationService.this, paramVerificationState);
      }
    });
  }

  public static void start(Context paramContext, Intent paramIntent)
  {
    Intent localIntent = new Intent(paramContext, PackageVerificationService.class);
    localIntent.putExtra("broadcast_intent", paramIntent);
    paramContext.startService(localIntent);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    sInstance = this;
  }

  public void onDestroy()
  {
    destroyAllVerifications();
    sInstance = null;
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Intent localIntent = (Intent)paramIntent.getParcelableExtra("broadcast_intent");
    String str = localIntent.getAction();
    if ("android.intent.action.PACKAGE_NEEDS_VERIFICATION".equals(str))
      handleVerificationIntent(localIntent);
    while (true)
    {
      this.mLastStartId = paramInt2;
      if (this.mVerifications.isEmpty())
        stopSelf(this.mLastStartId);
      return 3;
      if ("android.intent.action.PACKAGE_VERIFIED".equals(str))
        cancelVerificationIntent(localIntent);
    }
  }

  private static class VerificationState
  {
    public final Uri dataUri;
    public final int flags;
    public final int id;
    public final String installingPackage;
    public String mDescription;
    public Activity mDialog;
    public String mLabel;
    public long mLength;
    public Uri mMoreInfoUri;
    public String mPackageName;
    public int mResult = 1;
    public byte[] mSha256;
    public Signature[] mSignatures;
    public byte[] mToken;
    public Integer mVersion;
    public InetAddress originatingIp;
    public final Uri originatingUri;
    public InetAddress referrerIp;
    public final Uri referrerUri;
    public final Uri verificationUri;

    public VerificationState(Intent paramIntent)
    {
      Bundle localBundle = paramIntent.getExtras();
      this.id = localBundle.getInt("android.content.pm.extra.VERIFICATION_ID");
      this.dataUri = paramIntent.getData();
      this.installingPackage = localBundle.getString("android.content.pm.extra.VERIFICATION_INSTALLER_PACKAGE");
      this.flags = localBundle.getInt("android.content.pm.extra.VERIFICATION_INSTALL_FLAGS");
      this.verificationUri = ((Uri)localBundle.getParcelable("android.content.pm.extra.VERIFICATION_URI"));
      this.originatingUri = ((Uri)localBundle.getParcelable("android.intent.extra.ORIGINATING_URI"));
      this.referrerUri = ((Uri)localBundle.getParcelable("android.intent.extra.REFERRER"));
      this.mPackageName = localBundle.getString("android.content.pm.extra.VERIFICATION_PACKAGE_NAME");
      this.mVersion = ((Integer)localBundle.get("android.content.pm.extra.VERIFICATION_VERSION_CODE"));
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(this.id);
      arrayOfObject[1] = this.dataUri;
      arrayOfObject[2] = this.installingPackage;
      arrayOfObject[3] = Integer.valueOf(this.flags);
      return String.format("id = %d, data=%s installing=%s flags=%d", arrayOfObject);
    }
  }

  private class WorkerTask extends AsyncTask<Void, Void, Boolean>
  {
    private final AppStates mAppStates;
    private final Context mContext;
    private final PackageVerificationService.VerificationState mState;

    public WorkerTask(PackageVerificationService.VerificationState arg2)
    {
      Object localObject;
      this.mState = localObject;
      this.mContext = PackageVerificationService.this;
      this.mAppStates = FinskyApp.get().getAppStates();
    }

    protected Boolean doInBackground(Void[] paramArrayOfVoid)
    {
      this.mState.mResult = 1;
      this.mAppStates.blockingLoad();
      if (!PackageVerificationService.this.getPackageInfo(this.mState));
      for (Boolean localBoolean = Boolean.valueOf(false); ; localBoolean = Boolean.valueOf(true))
      {
        return localBoolean;
        PackageVerificationService.resolveHosts(this.mState);
      }
    }

    public void execute()
    {
      executeOnExecutor(THREAD_POOL_EXECUTOR, (Void[])null);
    }

    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue())
        if (((Boolean)FinskyPreferences.acceptedAntiMalwareConsent.get()).booleanValue())
          PackageVerificationService.this.sendVerificationRequest(this.mState);
      while (true)
      {
        return;
        PackageVerificationService.this.extendTimeout(this.mState.id, 1);
        ConsentDialog.show(PackageVerificationService.this, this.mState.id);
        continue;
        PackageVerificationService.this.reportAndCleanup(this.mContext, this.mState);
      }
    }

    protected void onPreExecute()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mState;
      FinskyLog.d("Verification Requested for %s", arrayOfObject);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.vending.verifier.PackageVerificationService
 * JD-Core Version:    0.6.2
 */
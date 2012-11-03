package com.google.android.finsky.appstate;

import android.accounts.Account;
import android.os.Handler;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.library.Accounts;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.api.VendingApiFactory;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncRequestProto;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncRequestProto.AssetInstallState;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncRequestProto.SystemApp;
import com.google.android.vending.remoting.protos.VendingProtos.ContentSyncResponseProto;
import com.google.android.volley.MicroProtoPrinter;
import com.google.protobuf.micro.MessageMicro;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AppStatesReplicator
{
  private final Accounts mAccounts;
  private final AppStates mAppStates;
  private final Handler mBackgroundHandler;
  private final Libraries mLibraries;
  private final Handler mNotificationHandler;
  private final List<Listener> mReplicationListeners = Lists.newArrayList();
  private final VendingApiFactory mVendingApiFactory;

  public AppStatesReplicator(Accounts paramAccounts, Libraries paramLibraries, AppStates paramAppStates, VendingApiFactory paramVendingApiFactory, Handler paramHandler1, Handler paramHandler2)
  {
    this.mAccounts = paramAccounts;
    this.mLibraries = paramLibraries;
    this.mAppStates = paramAppStates;
    this.mVendingApiFactory = paramVendingApiFactory;
    this.mNotificationHandler = paramHandler1;
    this.mBackgroundHandler = paramHandler2;
  }

  private static List<PackageStateRepository.PackageState> getAccountList(Map<Account, List<PackageStateRepository.PackageState>> paramMap, Account paramAccount)
  {
    Object localObject = (List)paramMap.get(paramAccount);
    if (localObject == null)
    {
      localObject = Lists.newArrayList();
      paramMap.put(paramAccount, localObject);
    }
    return localObject;
  }

  private void handleContentSyncResponse(final int paramInt1, final int paramInt2, int paramInt3)
  {
    if (paramInt3 == paramInt1)
    {
      try
      {
        Iterator localIterator = this.mReplicationListeners.iterator();
        while (localIterator.hasNext())
        {
          final Listener localListener = (Listener)localIterator.next();
          if (localListener != null)
            this.mNotificationHandler.post(new Runnable()
            {
              public void run()
              {
                AppStatesReplicator.Listener localListener = localListener;
                if (paramInt2 == paramInt1);
                for (boolean bool = true; ; bool = false)
                {
                  localListener.onFinished(bool);
                  return;
                }
              }
            });
        }
      }
      finally
      {
      }
      this.mReplicationListeners.clear();
    }
  }

  private void internalReplicate()
  {
    Collection localCollection = this.mAppStates.getPackageStateRepository().getAllBlocking();
    HashMap localHashMap = Maps.newHashMap();
    ArrayList localArrayList1 = Lists.newArrayList();
    int i = bucketAppsByOwner(localCollection, localHashMap, localArrayList1);
    final Integer localInteger1 = Integer.valueOf(computeHash(localArrayList1));
    long l = System.currentTimeMillis();
    final ArrayList localArrayList2 = Lists.newArrayList();
    final int[] arrayOfInt1 = { 0 };
    final int[] arrayOfInt2 = { 0 };
    ArrayList localArrayList3 = Lists.newArrayList();
    ArrayList localArrayList4 = Lists.newArrayList();
    Iterator localIterator = this.mAccounts.getAccounts().iterator();
    while (localIterator.hasNext())
    {
      Account localAccount = (Account)localIterator.next();
      List localList = (List)localHashMap.get(localAccount);
      final Integer localInteger2 = Integer.valueOf(computeHash(localList));
      final PreferenceFile.SharedPreference localSharedPreference1 = FinskyPreferences.replicatedAccountAppsHash.get(localAccount.name);
      final PreferenceFile.SharedPreference localSharedPreference2 = FinskyPreferences.replicatedSystemAppsHash.get(localAccount.name);
      int j = ((Integer)localSharedPreference2.get()).intValue();
      int k = ((Integer)localSharedPreference1.get()).intValue();
      VendingProtos.ContentSyncRequestProto localContentSyncRequestProto = makeContentSyncRequest(localInteger1.intValue(), j, localArrayList1, localInteger2.intValue(), k, localList, i, l);
      if (localContentSyncRequestProto == null)
      {
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = FinskyLog.scrubPii(localAccount.name);
          FinskyLog.v("No installation states replication required: account=%s", arrayOfObject2);
        }
      }
      else
      {
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject1 = new Object[4];
          arrayOfObject1[0] = FinskyLog.scrubPii(localAccount.name);
          arrayOfObject1[1] = Integer.valueOf(localContentSyncRequestProto.getSystemAppCount());
          arrayOfObject1[2] = Integer.valueOf(localContentSyncRequestProto.getAssetInstallStateCount());
          arrayOfObject1[3] = Integer.valueOf(localContentSyncRequestProto.getSideloadedAppCount());
          FinskyLog.v("Replicating installation states: account=%s, numSystemApps=%d, numAccountApps=%d, numSideloaded=%d", arrayOfObject1);
        }
        localArrayList2.add(localAccount);
        localArrayList3.add(localContentSyncRequestProto);
        localArrayList4.add(new Response.Listener()
        {
          public void onResponse(VendingProtos.ContentSyncResponseProto paramAnonymousContentSyncResponseProto)
          {
            localSharedPreference2.put(localInteger1);
            localSharedPreference1.put(localInteger2);
            int[] arrayOfInt1 = arrayOfInt1;
            arrayOfInt1[0] = (1 + arrayOfInt1[0]);
            int[] arrayOfInt2 = arrayOfInt2;
            arrayOfInt2[0] = (1 + arrayOfInt2[0]);
            AppStatesReplicator.this.handleContentSyncResponse(localArrayList2.size(), arrayOfInt1[0], arrayOfInt2[0]);
          }
        });
      }
    }
    performRequests(localArrayList2, arrayOfInt1, arrayOfInt2, localArrayList3, localArrayList4);
  }

  private VendingProtos.ContentSyncRequestProto.AssetInstallState makeInstallState(PackageStateRepository.PackageState paramPackageState, long paramLong)
  {
    VendingProtos.ContentSyncRequestProto.AssetInstallState localAssetInstallState = new VendingProtos.ContentSyncRequestProto.AssetInstallState();
    localAssetInstallState.setAssetId(AssetUtils.makeAssetId(paramPackageState.packageName, paramPackageState.installedVersion));
    localAssetInstallState.setAssetState(2);
    localAssetInstallState.setInstallTime(paramLong);
    localAssetInstallState.setPackageName(paramPackageState.packageName);
    localAssetInstallState.setVersionCode(paramPackageState.installedVersion);
    return localAssetInstallState;
  }

  private VendingProtos.ContentSyncRequestProto.SystemApp makeSystemApp(PackageStateRepository.PackageState paramPackageState)
  {
    VendingProtos.ContentSyncRequestProto.SystemApp localSystemApp = new VendingProtos.ContentSyncRequestProto.SystemApp();
    localSystemApp.setPackageName(paramPackageState.packageName);
    localSystemApp.setVersionCode(paramPackageState.installedVersion);
    String[] arrayOfString = paramPackageState.certificateHashes;
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      localSystemApp.addCertificateHash(arrayOfString[j]);
    Collections.sort(localSystemApp.getCertificateHashList());
    return localSystemApp;
  }

  private void performRequests(final List<Account> paramList, final int[] paramArrayOfInt1, final int[] paramArrayOfInt2, List<VendingProtos.ContentSyncRequestProto> paramList1, List<Response.Listener<VendingProtos.ContentSyncResponseProto>> paramList2)
  {
    if (paramList.size() == 0)
      handleContentSyncResponse(0, 0, 0);
    while (true)
    {
      return;
      for (int i = 0; i < paramList.size(); i++)
      {
        Account localAccount = (Account)paramList.get(i);
        if (FinskyLog.DEBUG)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = FinskyLog.scrubPii(localAccount.name);
          FinskyLog.d("ContentSyncRequestProto for account %s:", arrayOfObject);
          String[] arrayOfString = MicroProtoPrinter.prettyPrint("ContentSyncRequestProto", VendingProtos.ContentSyncRequestProto.class, (MessageMicro)paramList1.get(i)).split("\n");
          int j = arrayOfString.length;
          for (int k = 0; k < j; k++)
            FinskyLog.d(": %s", new Object[] { arrayOfString[k] });
        }
        VendingProtos.ContentSyncRequestProto localContentSyncRequestProto = (VendingProtos.ContentSyncRequestProto)paramList1.get(i);
        Response.Listener localListener = (Response.Listener)paramList2.get(i);
        this.mVendingApiFactory.getApi(localAccount.name).syncContent(localContentSyncRequestProto, localListener, new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            int[] arrayOfInt = paramArrayOfInt1;
            arrayOfInt[0] = (1 + arrayOfInt[0]);
            AppStatesReplicator.this.handleContentSyncResponse(paramList.size(), paramArrayOfInt1[0], paramArrayOfInt2[0]);
          }
        });
      }
    }
  }

  int bucketAppsByOwner(Collection<PackageStateRepository.PackageState> paramCollection, Map<Account, List<PackageStateRepository.PackageState>> paramMap, List<PackageStateRepository.PackageState> paramList)
  {
    if ((paramMap.size() != 0) || (paramList.size() != 0))
      throw new IllegalArgumentException("Buckets must be empty");
    int i = 0;
    Iterator localIterator1 = paramCollection.iterator();
    label172: label176: 
    while (localIterator1.hasNext())
    {
      PackageStateRepository.PackageState localPackageState = (PackageStateRepository.PackageState)localIterator1.next();
      int j;
      if ((!localPackageState.isSystemApp) || (localPackageState.isUpdatedSystemApp))
      {
        j = 1;
        label81: if (j != 0)
        {
          List localList = this.mLibraries.getAppOwners(localPackageState.packageName, localPackageState.certificateHashes);
          if (localList.size() <= 0)
            break label172;
          getAccountList(paramMap, (Account)localList.get(0)).add(localPackageState);
        }
      }
      while (true)
      {
        if ((!localPackageState.isSystemApp) || (localPackageState.isDisabled))
          break label176;
        paramList.add(localPackageState);
        break;
        j = 0;
        break label81;
        i++;
      }
    }
    Iterator localIterator2 = this.mAccounts.getAccounts().iterator();
    while (localIterator2.hasNext())
    {
      Account localAccount = (Account)localIterator2.next();
      if (!paramMap.containsKey(localAccount))
        paramMap.put(localAccount, Collections.emptyList());
    }
    return i;
  }

  int computeHash(Collection<PackageStateRepository.PackageState> paramCollection)
  {
    return paramCollection.hashCode();
  }

  public void load(final Runnable paramRunnable)
  {
    Runnable local1 = new Runnable()
    {
      private int mCount;

      public void run()
      {
        this.mCount = (1 + this.mCount);
        if ((this.mCount == 2) && (paramRunnable != null))
          AppStatesReplicator.this.mNotificationHandler.post(paramRunnable);
      }
    };
    this.mAppStates.load(local1);
    this.mLibraries.load(local1);
  }

  VendingProtos.ContentSyncRequestProto makeContentSyncRequest(int paramInt1, int paramInt2, List<PackageStateRepository.PackageState> paramList1, int paramInt3, int paramInt4, List<PackageStateRepository.PackageState> paramList2, int paramInt5, long paramLong)
  {
    int i = 0;
    if (paramInt1 != paramInt2);
    VendingProtos.ContentSyncRequestProto localContentSyncRequestProto;
    for (int j = 1; ; j = 0)
    {
      if ((j != 0) || (paramInt3 != paramInt4))
        i = 1;
      if ((i == 0) && (j == 0))
        break label157;
      localContentSyncRequestProto = new VendingProtos.ContentSyncRequestProto();
      if (paramList2 == null)
        break;
      Iterator localIterator2 = paramList2.iterator();
      while (localIterator2.hasNext())
        localContentSyncRequestProto.addAssetInstallState(makeInstallState((PackageStateRepository.PackageState)localIterator2.next(), paramLong));
    }
    if (j != 0)
    {
      Iterator localIterator1 = paramList1.iterator();
      while (localIterator1.hasNext())
        localContentSyncRequestProto.addSystemApp(makeSystemApp((PackageStateRepository.PackageState)localIterator1.next()));
    }
    localContentSyncRequestProto.setSideloadedAppCount(paramInt5);
    while (true)
    {
      return localContentSyncRequestProto;
      label157: localContentSyncRequestProto = null;
    }
  }

  public void replicate(Listener paramListener)
  {
    try
    {
      this.mReplicationListeners.add(paramListener);
      int i = this.mReplicationListeners.size();
      if (i > 1);
      while (true)
      {
        return;
        this.mBackgroundHandler.post(new Runnable()
        {
          public void run()
          {
            AppStatesReplicator.this.internalReplicate();
          }
        });
      }
    }
    finally
    {
    }
  }

  public static abstract interface Listener
  {
    public abstract void onFinished(boolean paramBoolean);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.appstate.AppStatesReplicator
 * JD-Core Version:    0.6.2
 */
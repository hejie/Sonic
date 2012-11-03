package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.Common.Docid;
import com.google.android.finsky.remoting.protos.Library.LibraryAppDetails;
import com.google.android.finsky.remoting.protos.Library.LibraryInAppDetails;
import com.google.android.finsky.remoting.protos.Library.LibraryMutation;
import com.google.android.finsky.remoting.protos.Library.LibrarySubscriptionDetails;
import com.google.android.finsky.remoting.protos.Library.LibraryUpdate;
import com.google.android.finsky.remoting.protos.LibraryReplication.ClientLibraryState;
import com.google.android.finsky.remoting.protos.LibraryReplication.LibraryReplicationRequest;
import com.google.android.finsky.remoting.protos.LibraryReplication.LibraryReplicationResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.volley.MicroProtoPrinter;
import com.google.protobuf.micro.ByteStringMicro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LibraryReplicator
{
  private final AccountLibrary mAccountLibrary;
  private final Handler mBackgroundHandler;
  private ReplicationRequest mCurrentRequest;
  private Queue<DebugEvent> mDebugEvents;
  private final DfeApi mDfeApi;
  private final boolean mEnableDebugging;
  private final List<Listener> mListeners = Lists.newArrayList();
  private final Handler mNotificationHandler;
  private final List<ReplicationRequest> mReplicationRequests = Lists.newArrayList();
  private final Runnable mReplicationRunnable = new Runnable()
  {
    public void run()
    {
      if (LibraryReplicator.this.mCurrentRequest == null)
      {
        LibraryReplication.LibraryReplicationRequest localLibraryReplicationRequest;
        try
        {
          if (LibraryReplicator.this.mReplicationRequests.size() == 0)
            return;
          LibraryReplicator.access$002(LibraryReplicator.this, (LibraryReplicator.ReplicationRequest)LibraryReplicator.this.mReplicationRequests.remove(0));
          if (LibraryReplicator.this.mEnableDebugging)
            LibraryReplicator.this.recordDebugEvent(2, null, null, LibraryReplicator.this.mCurrentRequest.debugTag);
          localLibraryReplicationRequest = new LibraryReplication.LibraryReplicationRequest();
          for (String str : LibraryReplicator.this.mCurrentRequest.libraryIds)
            localLibraryReplicationRequest.addLibraryState(LibraryReplicator.this.buildLibraryState(str));
        }
        finally
        {
        }
        LibraryReplicator.LibraryUpdateListener localLibraryUpdateListener = new LibraryReplicator.LibraryUpdateListener(LibraryReplicator.this);
        LibraryReplicator.this.mDfeApi.replicateLibrary(localLibraryReplicationRequest, localLibraryUpdateListener, localLibraryUpdateListener);
      }
      else if (FinskyLog.DEBUG)
      {
        FinskyLog.v("Replication request pending.", new Object[0]);
      }
    }
  };
  private final SQLiteLibrary mSQLiteLibrary;

  public LibraryReplicator(DfeApi paramDfeApi, SQLiteLibrary paramSQLiteLibrary, AccountLibrary paramAccountLibrary, Handler paramHandler1, Handler paramHandler2, boolean paramBoolean)
  {
    this.mDfeApi = paramDfeApi;
    this.mSQLiteLibrary = paramSQLiteLibrary;
    this.mAccountLibrary = paramAccountLibrary;
    this.mNotificationHandler = paramHandler1;
    this.mBackgroundHandler = paramHandler2;
    this.mEnableDebugging = paramBoolean;
  }

  private LibraryReplication.ClientLibraryState buildLibraryState(String paramString)
  {
    HashingLibrary localHashingLibrary = this.mAccountLibrary.getLibrary(paramString);
    LibraryReplication.ClientLibraryState localClientLibraryState = new LibraryReplication.ClientLibraryState();
    localClientLibraryState.setCorpus(AccountLibrary.getBackendFromLibraryId(paramString));
    localClientLibraryState.setLibraryId(paramString);
    localClientLibraryState.setHashCodeSum(localHashingLibrary.getHash());
    localClientLibraryState.setLibrarySize(localHashingLibrary.size());
    byte[] arrayOfByte = this.mAccountLibrary.getServerToken(paramString);
    if (arrayOfByte != null)
      localClientLibraryState.setServerToken(ByteStringMicro.copyFrom(arrayOfByte));
    return localClientLibraryState;
  }

  private void checkOnBackgroundHandler()
  {
    if (Looper.myLooper() != this.mBackgroundHandler.getLooper())
      FinskyLog.wtf("This method must be called from the background handler.", new Object[0]);
  }

  private LibraryEntry createLibraryEntry(Library.LibraryMutation paramLibraryMutation, String paramString)
  {
    String str1 = this.mAccountLibrary.getAccount().name;
    String str2 = paramLibraryMutation.getDocid().getBackendDocid();
    int i = paramLibraryMutation.getDocid().getBackend();
    int j = paramLibraryMutation.getDocid().getType();
    int k = paramLibraryMutation.getOfferType();
    long l1 = paramLibraryMutation.getDocumentHash();
    Object localObject;
    if ((!"u-wl".equals(paramString)) && (!paramLibraryMutation.getDeleted()))
      if (j == 1)
      {
        Library.LibraryAppDetails localLibraryAppDetails = paramLibraryMutation.getAppDetails();
        String str3 = localLibraryAppDetails.getCertificateHash();
        long l5 = 0L;
        if (localLibraryAppDetails.hasRefundTimeoutTimestampMsec())
          l5 = localLibraryAppDetails.getRefundTimeoutTimestampMsec();
        long l6 = 0L;
        if (localLibraryAppDetails.hasPostDeliveryRefundWindowMsec())
          l6 = localLibraryAppDetails.getPostDeliveryRefundWindowMsec();
        localObject = new LibraryAppEntry(str1, str2, k, l1, str3, l5, l6);
      }
    while (true)
    {
      return localObject;
      if (j == 15)
      {
        Library.LibrarySubscriptionDetails localLibrarySubscriptionDetails = paramLibraryMutation.getSubscriptionDetails();
        long l2 = localLibrarySubscriptionDetails.getInitiationTimestampMsec();
        long l3 = localLibrarySubscriptionDetails.getValidUntilTimestampMsec();
        if (localLibrarySubscriptionDetails.hasTrialUntilTimestampMsec());
        for (long l4 = localLibrarySubscriptionDetails.getTrialUntilTimestampMsec(); ; l4 = 0L)
        {
          localObject = new LibrarySubscriptionEntry(str1, paramString, i, str2, k, l2, l3, l4, localLibrarySubscriptionDetails.getAutoRenewing(), l1);
          break;
        }
      }
      if ((j == 11) && (paramLibraryMutation.hasInAppDetails()))
      {
        Library.LibraryInAppDetails localLibraryInAppDetails = paramLibraryMutation.getInAppDetails();
        localObject = new LibraryInAppEntry(str1, paramString, str2, k, localLibraryInAppDetails.getSignedPurchaseData(), localLibraryInAppDetails.getSignature(), l1);
      }
      else
      {
        localObject = new LibraryEntry(str1, paramString, i, str2, j, k, l1);
      }
    }
  }

  private String[] getSupportedLibraries(String[] paramArrayOfString)
  {
    int i = 0;
    int j = paramArrayOfString.length;
    for (int k = 0; k < j; k++)
    {
      String str2 = paramArrayOfString[k];
      if (!this.mAccountLibrary.supportsLibrary(str2))
        i++;
    }
    String[] arrayOfString;
    if (i == paramArrayOfString.length)
      arrayOfString = null;
    while (true)
    {
      return arrayOfString;
      if (i > 0)
      {
        arrayOfString = new String[paramArrayOfString.length - i];
        int m = 0;
        int n = paramArrayOfString.length;
        for (int i1 = 0; i1 < n; i1++)
        {
          String str1 = paramArrayOfString[i1];
          if (this.mAccountLibrary.supportsLibrary(str1))
          {
            arrayOfString[m] = str1;
            m++;
          }
        }
      }
      else
      {
        arrayOfString = paramArrayOfString;
      }
    }
  }

  private void handleNextRequest(long paramLong)
  {
    try
    {
      this.mBackgroundHandler.removeCallbacks(this.mReplicationRunnable);
      this.mBackgroundHandler.postDelayed(this.mReplicationRunnable, paramLong);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private boolean internalApplyLibraryUpdate(Library.LibraryUpdate paramLibraryUpdate, String paramString)
  {
    if (this.mEnableDebugging)
      recordDebugEvent(0, paramLibraryUpdate, null, paramString);
    checkOnBackgroundHandler();
    String str1;
    boolean bool;
    if (paramLibraryUpdate.hasLibraryId())
    {
      str1 = paramLibraryUpdate.getLibraryId();
      if (this.mAccountLibrary.supportsLibrary(str1))
        break label81;
      FinskyLog.d("Ignoring library update for unsupported library %s", new Object[] { str1 });
      recordDebugEvent(5, null, null, paramString);
      bool = false;
    }
    while (true)
    {
      return bool;
      str1 = AccountLibrary.getLibraryIdFromBackend(paramLibraryUpdate.getCorpus());
      break;
      label81: this.mAccountLibrary.disableListeners();
      while (true)
      {
        ArrayList localArrayList;
        int i;
        try
        {
          switch (paramLibraryUpdate.getStatus())
          {
          default:
            Object[] arrayOfObject = new Object[2];
            arrayOfObject[0] = str1;
            arrayOfObject[1] = Integer.valueOf(paramLibraryUpdate.getStatus());
            FinskyLog.wtf("Unknown LibraryUpdate.status: libraryId=%s, status=%d", arrayOfObject);
            if (paramLibraryUpdate.hasServerToken())
            {
              byte[] arrayOfByte = paramLibraryUpdate.getServerToken().toByteArray();
              this.mAccountLibrary.setServerToken(str1, arrayOfByte);
              PreferenceFile.SharedPreference localSharedPreference = FinskyPreferences.getLibraryServerToken(str1).get(this.mAccountLibrary.getAccount().name);
              String str2 = Base64.encodeToString(arrayOfByte, 0);
              localSharedPreference.put(str2);
              if (FinskyLog.DEBUG)
                FinskyLog.v("Updated server token: libraryId=%s serverToken=%s", new Object[] { str1, str2 });
            }
            this.mAccountLibrary.enableListeners();
            bool = paramLibraryUpdate.getHasMore();
            break;
          case 3:
            this.mSQLiteLibrary.resetAccountLibrary(this.mAccountLibrary.getAccount(), str1);
            this.mAccountLibrary.resetLibrary(str1);
          case 2:
            localArrayList = Lists.newArrayList(paramLibraryUpdate.getMutationCount());
            i = 0;
            Iterator localIterator = paramLibraryUpdate.getMutationList().iterator();
            if (localIterator.hasNext())
            {
              Library.LibraryMutation localLibraryMutation = (Library.LibraryMutation)localIterator.next();
              LibraryEntry localLibraryEntry = createLibraryEntry(localLibraryMutation, str1);
              if (localLibraryMutation.getDeleted())
              {
                this.mAccountLibrary.remove(localLibraryEntry);
                this.mSQLiteLibrary.remove(localLibraryEntry);
                i = 1;
                continue;
              }
              localArrayList.add(localLibraryEntry);
              continue;
            }
            break;
          case 1:
          }
        }
        finally
        {
          this.mAccountLibrary.enableListeners();
        }
        this.mAccountLibrary.addAll(localArrayList);
        this.mSQLiteLibrary.addAll(localArrayList);
        if ((!localArrayList.isEmpty()) || (i != 0))
          notifyMutationListeners(str1);
      }
      if (FinskyLog.DEBUG)
        FinskyLog.v("NOT_MODIFIED received for libraryId=%s", new Object[] { str1 });
      bool = false;
      this.mAccountLibrary.enableListeners();
    }
  }

  private void notifyMutationListeners(final String paramString)
  {
    try
    {
      Iterator localIterator = this.mListeners.iterator();
      if (localIterator.hasNext())
      {
        final Listener localListener = (Listener)localIterator.next();
        this.mNotificationHandler.post(new Runnable()
        {
          public void run()
          {
            localListener.onMutationsApplied(paramString);
          }
        });
      }
    }
    finally
    {
    }
  }

  private void recordDebugEvent(int paramInt, Library.LibraryUpdate paramLibraryUpdate, VolleyError paramVolleyError, String paramString)
  {
    try
    {
      if (this.mDebugEvents == null)
        this.mDebugEvents = Lists.newLinkedList();
      DebugEvent localDebugEvent = new DebugEvent(null);
      DebugEvent.access$1502(localDebugEvent, System.currentTimeMillis());
      DebugEvent.access$1602(localDebugEvent, paramInt);
      DebugEvent.access$1702(localDebugEvent, paramString);
      DebugEvent.access$1802(localDebugEvent, paramLibraryUpdate);
      DebugEvent.access$1902(localDebugEvent, paramVolleyError);
      this.mDebugEvents.add(localDebugEvent);
      if (this.mDebugEvents.size() > 100)
        this.mDebugEvents.remove();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void scheduleRequestAtFront(ReplicationRequest paramReplicationRequest)
  {
    try
    {
      if (this.mEnableDebugging)
        recordDebugEvent(1, null, null, paramReplicationRequest.debugTag);
      this.mReplicationRequests.add(0, paramReplicationRequest);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addListener(Listener paramListener)
  {
    try
    {
      this.mListeners.add(paramListener);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void applyLibraryUpdate(final Library.LibraryUpdate paramLibraryUpdate, final String paramString)
  {
    this.mBackgroundHandler.post(new Runnable()
    {
      public void run()
      {
        LibraryReplicator.this.mSQLiteLibrary.reopen();
        LibraryReplicator.this.internalApplyLibraryUpdate(paramLibraryUpdate, paramString);
        LibraryReplicator.this.mSQLiteLibrary.close();
      }
    });
  }

  public void dumpState(String paramString)
  {
    String str = FinskyLog.scrubPii(this.mAccountLibrary.getAccount().name);
    Log.d("FinskyLibrary", paramString + "LibraryReplicator (account=" + str + ") {");
    if (this.mDebugEvents != null)
    {
      Log.d("FinskyLibrary", paramString + "  eventsCount=" + this.mDebugEvents.size());
      Iterator localIterator = this.mDebugEvents.iterator();
      while (localIterator.hasNext())
        ((DebugEvent)localIterator.next()).dumpState(paramString);
    }
    Log.d("FinskyLibrary", paramString + "  eventsCount=0");
    Log.d("FinskyLibrary", paramString + "} (account=" + str + ")");
  }

  public void replicate(String[] paramArrayOfString, Runnable paramRunnable, String paramString)
  {
    try
    {
      if (this.mEnableDebugging)
      {
        paramString = paramString + " libraryIds=" + Arrays.toString(paramArrayOfString);
        recordDebugEvent(1, null, null, paramString);
      }
      String[] arrayOfString = getSupportedLibraries(paramArrayOfString);
      if (arrayOfString == null)
      {
        FinskyLog.d("Skipping replication request since all libraries are unsupported.", new Object[0]);
        this.mNotificationHandler.post(paramRunnable);
      }
      while (true)
      {
        return;
        this.mReplicationRequests.add(new ReplicationRequest(arrayOfString, false, paramRunnable, paramString));
        handleNextRequest(0L);
      }
    }
    finally
    {
    }
  }

  private static class DebugEvent
  {
    private Library.LibraryUpdate libraryUpdate;
    private String tag;
    private long timestampMs;
    private int type;
    private VolleyError volleyError;

    private static String typeToString(int paramInt)
    {
      String str;
      switch (paramInt)
      {
      default:
        str = String.valueOf(paramInt) + " (FIXME)";
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      while (true)
      {
        return str;
        str = "APPLY_LIBRARY_UPDATE";
        continue;
        str = "SCHEDULE_REPLICATION";
        continue;
        str = "REPLICATE";
        continue;
        str = "ERROR_VOLLEY";
        continue;
        str = "ERROR_TOKEN_CHANGED";
        continue;
        str = "ERROR_UNSUPPORTED_LIBRARY";
      }
    }

    public void dumpState(String paramString)
    {
      Log.d("FinskyLibrary", paramString + "Event {");
      Log.d("FinskyLibrary", paramString + "  type=" + typeToString(this.type));
      Log.d("FinskyLibrary", paramString + "  timestampMs=" + this.timestampMs);
      Log.d("FinskyLibrary", paramString + "  timestamp=" + DateFormat.format("MM-dd hh:mm:ss", this.timestampMs));
      if (this.tag != null)
        Log.d("FinskyLibrary", paramString + "  tag=" + this.tag);
      if (this.libraryUpdate != null)
      {
        String[] arrayOfString = MicroProtoPrinter.prettyPrint("LibraryUpdate", Library.LibraryUpdate.class, this.libraryUpdate).split("\n");
        Log.d("FinskyLibrary", paramString + "  libraryUpdate=");
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          String str = arrayOfString[j];
          Log.d("FinskyLibrary", paramString + "    " + str);
        }
      }
      if (this.volleyError != null)
        Log.d("FinskyLibrary", paramString + "  volleyError=" + this.volleyError);
      Log.d("FinskyLibrary", paramString + "}");
    }
  }

  private class LibraryUpdateListener
    implements Response.ErrorListener, Response.Listener<LibraryReplication.LibraryReplicationResponse>
  {
    private final Map<String, byte[]> mOriginalTokens = Maps.newHashMap();

    public LibraryUpdateListener()
    {
      for (String str : AccountLibrary.LIBRARY_IDS)
        this.mOriginalTokens.put(str, LibraryReplicator.this.mAccountLibrary.getServerToken(str));
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      FinskyLog.w("Library replication failed: %s", new Object[] { paramVolleyError });
      if (LibraryReplicator.this.mEnableDebugging)
        LibraryReplicator.this.recordDebugEvent(3, null, paramVolleyError, null);
      LibraryReplicator.this.mBackgroundHandler.post(new Runnable()
      {
        public void run()
        {
          if (LibraryReplicator.this.mCurrentRequest == null)
          {
            FinskyLog.wtf("Expected pending replication request.", new Object[0]);
            return;
          }
          long l = 200L;
          if (!LibraryReplicator.this.mCurrentRequest.isRetry)
          {
            if (FinskyLog.DEBUG)
              FinskyLog.v("Retrying replication request.", new Object[0]);
            LibraryReplicator.ReplicationRequest localReplicationRequest = new LibraryReplicator.ReplicationRequest(LibraryReplicator.this.mCurrentRequest.libraryIds, true, LibraryReplicator.this.mCurrentRequest.finishRunnable, LibraryReplicator.this.mCurrentRequest.debugTag + "[r]");
            LibraryReplicator.this.scheduleRequestAtFront(localReplicationRequest);
            l = 2000L;
          }
          while (true)
          {
            LibraryReplicator.access$002(LibraryReplicator.this, null);
            LibraryReplicator.this.handleNextRequest(l);
            break;
            if (LibraryReplicator.this.mCurrentRequest.finishRunnable != null)
              LibraryReplicator.this.mNotificationHandler.post(LibraryReplicator.this.mCurrentRequest.finishRunnable);
          }
        }
      });
    }

    public void onResponse(final LibraryReplication.LibraryReplicationResponse paramLibraryReplicationResponse)
    {
      LibraryReplicator.this.mBackgroundHandler.post(new Runnable()
      {
        public void run()
        {
          if (LibraryReplicator.this.mCurrentRequest == null)
          {
            FinskyLog.wtf("Expected pending replication request.", new Object[0]);
            return;
          }
          ArrayList localArrayList = Lists.newArrayList();
          LibraryReplicator.this.mSQLiteLibrary.reopen();
          Iterator localIterator = paramLibraryReplicationResponse.getUpdateList().iterator();
          while (localIterator.hasNext())
          {
            Library.LibraryUpdate localLibraryUpdate = (Library.LibraryUpdate)localIterator.next();
            if (localLibraryUpdate.hasLibraryId());
            for (String str = localLibraryUpdate.getLibraryId(); ; str = AccountLibrary.getLibraryIdFromBackend(localLibraryUpdate.getCorpus()))
            {
              if (Arrays.equals((byte[])LibraryReplicator.LibraryUpdateListener.this.mOriginalTokens.get(str), LibraryReplicator.this.mAccountLibrary.getServerToken(str)))
                break label183;
              if (LibraryReplicator.this.mEnableDebugging)
                LibraryReplicator.this.recordDebugEvent(4, null, null, null);
              FinskyLog.wtf("Tokens changed, not applying library update for libraryId=%s", new Object[] { str });
              break;
            }
            label183: if (LibraryReplicator.this.internalApplyLibraryUpdate(localLibraryUpdate, LibraryReplicator.this.mCurrentRequest.debugTag))
              localArrayList.add(str);
          }
          LibraryReplicator.this.mSQLiteLibrary.close();
          if (!localArrayList.isEmpty())
          {
            String[] arrayOfString = new String[localArrayList.size()];
            for (int i = 0; i < localArrayList.size(); i++)
              arrayOfString[i] = ((String)localArrayList.get(i));
            LibraryReplicator.ReplicationRequest localReplicationRequest = new LibraryReplicator.ReplicationRequest(arrayOfString, false, LibraryReplicator.this.mCurrentRequest.finishRunnable, LibraryReplicator.this.mCurrentRequest.debugTag + "[c]");
            LibraryReplicator.this.scheduleRequestAtFront(localReplicationRequest);
          }
          while (true)
          {
            LibraryReplicator.access$002(LibraryReplicator.this, null);
            LibraryReplicator.this.handleNextRequest(200L);
            break;
            if (LibraryReplicator.this.mCurrentRequest.finishRunnable != null)
              LibraryReplicator.this.mNotificationHandler.post(LibraryReplicator.this.mCurrentRequest.finishRunnable);
          }
        }
      });
    }
  }

  public static abstract interface Listener
  {
    public abstract void onMutationsApplied(String paramString);
  }

  private static class ReplicationRequest
  {
    public String debugTag;
    public final Runnable finishRunnable;
    public final boolean isRetry;
    public final String[] libraryIds;

    public ReplicationRequest(String[] paramArrayOfString, boolean paramBoolean, Runnable paramRunnable, String paramString)
    {
      this.libraryIds = paramArrayOfString;
      this.finishRunnable = paramRunnable;
      this.isRetry = paramBoolean;
      this.debugTag = paramString;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.library.LibraryReplicator
 * JD-Core Version:    0.6.2
 */
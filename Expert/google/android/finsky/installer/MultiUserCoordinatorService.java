package com.google.android.finsky.installer;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class MultiUserCoordinatorService extends Service
{
  private boolean DEBUG_FORCE_BUSY_WITH_DELAY = false;
  private final IMultiUserCoordinatorService.Stub mBinder = new IMultiUserCoordinatorService.Stub()
  {
    private void notifyReleased(String paramAnonymousString)
    {
      synchronized (MultiUserCoordinatorService.this.mListeners)
      {
        Iterator localIterator = MultiUserCoordinatorService.this.mListeners.entrySet().iterator();
        while (true)
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            try
            {
              ((IMultiUserCoordinatorServiceListener)localEntry.getValue()).packageReleased(paramAnonymousString);
            }
            catch (RemoteException localRemoteException)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localEntry.getKey();
              FinskyLog.d("Could not notify listener for user %s", arrayOfObject);
            }
          }
      }
    }

    private void notifyTaken(String paramAnonymousString)
    {
      synchronized (MultiUserCoordinatorService.this.mListeners)
      {
        Iterator localIterator = MultiUserCoordinatorService.this.mListeners.entrySet().iterator();
        while (true)
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            try
            {
              ((IMultiUserCoordinatorServiceListener)localEntry.getValue()).packageAcquired(paramAnonymousString);
            }
            catch (RemoteException localRemoteException)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localEntry.getKey();
              FinskyLog.d("Could not notify listener for user %s", arrayOfObject);
            }
          }
      }
    }

    // ERROR //
    public boolean acquirePackage(final String paramAnonymousString)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_2
      //   2: aload_0
      //   3: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   6: invokestatic 82	com/google/android/finsky/installer/MultiUserCoordinatorService:access$100	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Z
      //   9: ifeq +54 -> 63
      //   12: aload_1
      //   13: aload_0
      //   14: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   17: invokestatic 86	com/google/android/finsky/installer/MultiUserCoordinatorService:access$200	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/lang/String;
      //   20: invokevirtual 92	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   23: ifne +31 -> 54
      //   26: new 94	android/os/Handler
      //   29: dup
      //   30: invokestatic 100	android/os/Looper:getMainLooper	()Landroid/os/Looper;
      //   33: invokespecial 103	android/os/Handler:<init>	(Landroid/os/Looper;)V
      //   36: new 105	com/google/android/finsky/installer/MultiUserCoordinatorService$1$1
      //   39: dup
      //   40: aload_0
      //   41: aload_1
      //   42: invokespecial 107	com/google/android/finsky/installer/MultiUserCoordinatorService$1$1:<init>	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService$1;Ljava/lang/String;)V
      //   45: ldc2_w 108
      //   48: invokevirtual 113	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
      //   51: pop
      //   52: iload_2
      //   53: ireturn
      //   54: aload_0
      //   55: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   58: aconst_null
      //   59: invokestatic 117	com/google/android/finsky/installer/MultiUserCoordinatorService:access$202	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;Ljava/lang/String;)Ljava/lang/String;
      //   62: pop
      //   63: invokestatic 123	android/os/Binder:getCallingUserHandle	()Landroid/os/UserHandle;
      //   66: astore_3
      //   67: aload_0
      //   68: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   71: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   74: astore 4
      //   76: aload 4
      //   78: monitorenter
      //   79: aload_0
      //   80: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   83: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   86: aload_1
      //   87: invokevirtual 130	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   90: checkcast 132	android/os/UserHandle
      //   93: astore 6
      //   95: aload 6
      //   97: ifnull +48 -> 145
      //   100: aload 6
      //   102: aload_3
      //   103: invokevirtual 133	android/os/UserHandle:equals	(Ljava/lang/Object;)Z
      //   106: ifne +39 -> 145
      //   109: ldc 135
      //   111: iconst_3
      //   112: anewarray 61	java/lang/Object
      //   115: dup
      //   116: iconst_0
      //   117: aload_3
      //   118: aastore
      //   119: dup
      //   120: iconst_1
      //   121: aload_1
      //   122: aastore
      //   123: dup
      //   124: iconst_2
      //   125: aload 6
      //   127: aastore
      //   128: invokestatic 72	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   131: aload 4
      //   133: monitorexit
      //   134: goto -82 -> 52
      //   137: astore 5
      //   139: aload 4
      //   141: monitorexit
      //   142: aload 5
      //   144: athrow
      //   145: aload_0
      //   146: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   149: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   152: aload_1
      //   153: aload_3
      //   154: invokevirtual 139	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   157: pop
      //   158: ldc 141
      //   160: iconst_2
      //   161: anewarray 61	java/lang/Object
      //   164: dup
      //   165: iconst_0
      //   166: aload_3
      //   167: aastore
      //   168: dup
      //   169: iconst_1
      //   170: aload_1
      //   171: aastore
      //   172: invokestatic 72	com/google/android/finsky/utils/FinskyLog:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   175: aload 4
      //   177: monitorexit
      //   178: aload_0
      //   179: aload_1
      //   180: invokespecial 143	com/google/android/finsky/installer/MultiUserCoordinatorService$1:notifyTaken	(Ljava/lang/String;)V
      //   183: iconst_1
      //   184: istore_2
      //   185: goto -133 -> 52
      //
      // Exception table:
      //   from	to	target	type
      //   79	142	137	finally
      //   145	178	137	finally
    }

    public void registerListener(IMultiUserCoordinatorServiceListener paramAnonymousIMultiUserCoordinatorServiceListener)
    {
      UserHandle localUserHandle = Binder.getCallingUserHandle();
      HashMap localHashMap = MultiUserCoordinatorService.this.mListeners;
      if (paramAnonymousIMultiUserCoordinatorServiceListener != null);
      try
      {
        MultiUserCoordinatorService.this.mListeners.put(localUserHandle, paramAnonymousIMultiUserCoordinatorServiceListener);
        return;
        MultiUserCoordinatorService.this.mListeners.remove(localUserHandle);
      }
      finally
      {
      }
    }

    // ERROR //
    public void releaseAllPackages()
    {
      // Byte code:
      //   0: invokestatic 123	android/os/Binder:getCallingUserHandle	()Landroid/os/UserHandle;
      //   3: astore_1
      //   4: invokestatic 155	com/google/android/finsky/utils/Sets:newHashSet	()Ljava/util/HashSet;
      //   7: astore_2
      //   8: aload_0
      //   9: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   12: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   15: astore_3
      //   16: aload_3
      //   17: monitorenter
      //   18: aload_0
      //   19: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   22: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   25: invokevirtual 158	java/util/HashMap:isEmpty	()Z
      //   28: ifeq +8 -> 36
      //   31: aload_3
      //   32: monitorexit
      //   33: goto +152 -> 185
      //   36: aload_0
      //   37: getfield 12	com/google/android/finsky/installer/MultiUserCoordinatorService$1:this$0	Lcom/google/android/finsky/installer/MultiUserCoordinatorService;
      //   40: invokestatic 126	com/google/android/finsky/installer/MultiUserCoordinatorService:access$400	(Lcom/google/android/finsky/installer/MultiUserCoordinatorService;)Ljava/util/HashMap;
      //   43: invokevirtual 33	java/util/HashMap:entrySet	()Ljava/util/Set;
      //   46: invokeinterface 39 1 0
      //   51: astore 5
      //   53: aload 5
      //   55: invokeinterface 45 1 0
      //   60: ifeq +88 -> 148
      //   63: aload 5
      //   65: invokeinterface 49 1 0
      //   70: checkcast 51	java/util/Map$Entry
      //   73: astore 7
      //   75: aload 7
      //   77: invokeinterface 54 1 0
      //   82: checkcast 132	android/os/UserHandle
      //   85: aload_1
      //   86: invokevirtual 133	android/os/UserHandle:equals	(Ljava/lang/Object;)Z
      //   89: ifeq -36 -> 53
      //   92: aload 7
      //   94: invokeinterface 64 1 0
      //   99: checkcast 88	java/lang/String
      //   102: astore 8
      //   104: ldc 160
      //   106: iconst_2
      //   107: anewarray 61	java/lang/Object
      //   110: dup
      //   111: iconst_0
      //   112: aload_1
      //   113: aastore
      //   114: dup
      //   115: iconst_1
      //   116: aload 8
      //   118: aastore
      //   119: invokestatic 163	com/google/android/finsky/utils/FinskyLog:w	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   122: aload 5
      //   124: invokeinterface 165 1 0
      //   129: aload_2
      //   130: aload 8
      //   132: invokeinterface 168 2 0
      //   137: pop
      //   138: goto -85 -> 53
      //   141: astore 4
      //   143: aload_3
      //   144: monitorexit
      //   145: aload 4
      //   147: athrow
      //   148: aload_3
      //   149: monitorexit
      //   150: aload_2
      //   151: invokeinterface 39 1 0
      //   156: astore 6
      //   158: aload 6
      //   160: invokeinterface 45 1 0
      //   165: ifeq +20 -> 185
      //   168: aload_0
      //   169: aload 6
      //   171: invokeinterface 49 1 0
      //   176: checkcast 88	java/lang/String
      //   179: invokespecial 21	com/google/android/finsky/installer/MultiUserCoordinatorService$1:notifyReleased	(Ljava/lang/String;)V
      //   182: goto -24 -> 158
      //   185: return
      //
      // Exception table:
      //   from	to	target	type
      //   18	145	141	finally
      //   148	150	141	finally
    }

    public void releasePackage(String paramAnonymousString)
    {
      UserHandle localUserHandle1 = Binder.getCallingUserHandle();
      for (int i = 1; ; i = 0)
      {
        UserHandle localUserHandle2;
        synchronized (MultiUserCoordinatorService.this.mPackageOwners)
        {
          localUserHandle2 = (UserHandle)MultiUserCoordinatorService.this.mPackageOwners.get(paramAnonymousString);
          if (localUserHandle2 == null)
          {
            FinskyLog.w("User=%s released=%s *** was not previously acquired", new Object[] { localUserHandle1, paramAnonymousString });
            if (i != 0)
              notifyReleased(paramAnonymousString);
            return;
          }
          if (localUserHandle2.equals(localUserHandle1))
          {
            MultiUserCoordinatorService.this.mPackageOwners.remove(paramAnonymousString);
            FinskyLog.d("User=%s released=%s", new Object[] { localUserHandle1, paramAnonymousString });
          }
        }
        FinskyLog.w("User=%s released=%s *** owned by=%s", new Object[] { localUserHandle1, paramAnonymousString, localUserHandle2 });
      }
    }
  };
  private String mDebugPackageJustReleased = null;
  private HashMap<UserHandle, IMultiUserCoordinatorServiceListener> mListeners = Maps.newHashMap();
  private HashMap<String, UserHandle> mPackageOwners = Maps.newHashMap();

  public static Intent createBindIntent(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramContext, MultiUserCoordinatorService.class));
    return localIntent;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.installer.MultiUserCoordinatorService
 * JD-Core Version:    0.6.2
 */
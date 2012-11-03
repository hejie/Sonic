package com.google.android.volley.elegant;

import com.android.volley.VolleyLog;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.AbstractPoolEntry;
import org.apache.http.impl.conn.tsccm.AbstractConnPool;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.BasicPooledConnAdapter;
import org.apache.http.impl.conn.tsccm.ConnPoolByRoute;
import org.apache.http.impl.conn.tsccm.PoolEntryRequest;
import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.apache.http.impl.conn.tsccm.WaitingThreadAborter;
import org.apache.http.params.HttpParams;

public class ElegantThreadSafeConnManager extends ThreadSafeClientConnManager
{
  public ElegantThreadSafeConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    super(paramHttpParams, paramSchemeRegistry);
  }

  protected AbstractConnPool createConnectionPool(HttpParams paramHttpParams)
  {
    ElegantPool localElegantPool = new ElegantPool(this.connOperator, paramHttpParams);
    if (1 != 0)
      localElegantPool.enableConnectionGC();
    return localElegantPool;
  }

  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    long l1;
    long l2;
    if (((paramManagedClientConnection instanceof ElegantBasicPooledConnAdapter)) && (paramManagedClientConnection.getRoute() != null))
    {
      ElegantBasicPooledConnAdapter localElegantBasicPooledConnAdapter = (ElegantBasicPooledConnAdapter)paramManagedClientConnection;
      l1 = System.currentTimeMillis() - localElegantBasicPooledConnAdapter.startTime;
      if (!paramManagedClientConnection.getRoute().isSecure())
        break label73;
      l2 = 5000L;
    }
    while (true)
    {
      if (l1 > l2);
      try
      {
        paramManagedClientConnection.close();
        label64: super.releaseConnection(paramManagedClientConnection, paramLong, paramTimeUnit);
        return;
        label73: l2 = 2500L;
      }
      catch (IOException localIOException)
      {
        break label64;
      }
    }
  }

  public ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
        this.val$poolRequest.abortRequest();
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        if (paramHttpRoute == null)
          throw new IllegalArgumentException("Route may not be null.");
        BasicPoolEntry localBasicPoolEntry = this.val$poolRequest.getPoolEntry(paramAnonymousLong, paramAnonymousTimeUnit);
        return new ElegantThreadSafeConnManager.ElegantBasicPooledConnAdapter(ElegantThreadSafeConnManager.this, localBasicPoolEntry);
      }
    };
  }

  public static class ElegantBasicPooledConnAdapter extends BasicPooledConnAdapter
  {
    public final long startTime = System.currentTimeMillis();

    protected ElegantBasicPooledConnAdapter(ThreadSafeClientConnManager paramThreadSafeClientConnManager, AbstractPoolEntry paramAbstractPoolEntry)
    {
      super(paramAbstractPoolEntry);
    }
  }

  public static class ElegantPool extends ConnPoolByRoute
  {
    public ElegantPool(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams)
    {
      super(paramHttpParams);
    }

    protected BasicPoolEntry getEntryBlocking(HttpRoute paramHttpRoute, Object paramObject, long paramLong, TimeUnit paramTimeUnit, WaitingThreadAborter paramWaitingThreadAborter)
      throws ConnectionPoolTimeoutException, InterruptedException
    {
      Date localDate = null;
      if (paramLong > 0L)
        localDate = new Date(System.currentTimeMillis() + paramTimeUnit.toMillis(paramLong));
      Object localObject1 = null;
      long l1 = System.currentTimeMillis();
      this.poolLock.lock();
      RouteSpecificPool localRouteSpecificPool;
      WaitingThread localWaitingThread;
      try
      {
        localRouteSpecificPool = getRoutePool(paramHttpRoute, true);
        localWaitingThread = null;
        if (localObject1 != null)
          break label158;
        if (this.isShutDown)
          throw new IllegalStateException("Connection pool shut down.");
      }
      finally
      {
        this.poolLock.unlock();
        long l2 = System.currentTimeMillis();
        if ((VolleyLog.DEBUG) && (l2 - l1 > 10L))
        {
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Long.valueOf(l2 - l1);
          VolleyLog.v("GetEntryBlocking() took %s ms", arrayOfObject1);
        }
      }
      BasicPoolEntry localBasicPoolEntry = getFreeEntry(localRouteSpecificPool, paramObject);
      localObject1 = localBasicPoolEntry;
      if (localObject1 != null)
      {
        label158: this.poolLock.unlock();
        long l3 = System.currentTimeMillis();
        if ((VolleyLog.DEBUG) && (l3 - l1 > 10L))
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Long.valueOf(l3 - l1);
          VolleyLog.v("GetEntryBlocking() took %s ms", arrayOfObject2);
        }
        return localObject1;
      }
      if (VolleyLog.DEBUG)
        VolleyLog.d("Constructed new connection to route=[%s]", new Object[] { paramHttpRoute });
      if (localRouteSpecificPool.getCapacity() > 0);
      for (int i = 1; ; i = 0)
        while (true)
        {
          if ((i != 0) && (this.numConnections < this.maxTotalConnections))
          {
            localObject1 = createEntry(localRouteSpecificPool, this.operator);
            break;
          }
          if ((i != 0) && (!this.freeConnections.isEmpty()))
          {
            deleteLeastUsedEntry();
            localObject1 = createEntry(localRouteSpecificPool, this.operator);
            break;
          }
          if (localWaitingThread == null)
          {
            localWaitingThread = newWaitingThread(this.poolLock.newCondition(), localRouteSpecificPool);
            paramWaitingThreadAborter.setWaitingThread(localWaitingThread);
          }
          try
          {
            localRouteSpecificPool.queueThread(localWaitingThread);
            this.waitingThreads.add(localWaitingThread);
            boolean bool = localWaitingThread.await(localDate);
            localRouteSpecificPool.removeThread(localWaitingThread);
            this.waitingThreads.remove(localWaitingThread);
            if ((bool) || (localDate == null) || (localDate.getTime() > System.currentTimeMillis()))
              break;
            throw new ConnectionPoolTimeoutException("Timeout waiting for connection");
          }
          finally
          {
            localRouteSpecificPool.removeThread(localWaitingThread);
            this.waitingThreads.remove(localWaitingThread);
          }
        }
    }

    public PoolEntryRequest requestPoolEntry(final HttpRoute paramHttpRoute, final Object paramObject)
    {
      return new PoolEntryRequest()
      {
        public void abortRequest()
        {
          ElegantThreadSafeConnManager.ElegantPool.this.poolLock.lock();
          try
          {
            this.val$aborter.abort();
            return;
          }
          finally
          {
            ElegantThreadSafeConnManager.ElegantPool.this.poolLock.unlock();
          }
        }

        public BasicPoolEntry getPoolEntry(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
          throws InterruptedException, ConnectionPoolTimeoutException
        {
          return ElegantThreadSafeConnManager.ElegantPool.this.getEntryBlocking(paramHttpRoute, paramObject, paramAnonymousLong, paramAnonymousTimeUnit, this.val$aborter);
        }
      };
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.volley.elegant.ElegantThreadSafeConnManager
 * JD-Core Version:    0.6.2
 */
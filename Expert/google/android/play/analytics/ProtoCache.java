package com.google.android.play.analytics;

import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class ProtoCache
{
  private static TreeSet<ClientsAnalytics.LogEvent> mEventCache = Sets.newTreeSet(sProtoComparator);
  private static TreeSet<ClientsAnalytics.LogEventKeyValues> mKeyValueCache = Sets.newTreeSet(sProtoComparator);
  private static final Comparator<Object> sProtoComparator = new Comparator()
  {
    public int compare(Object paramAnonymousObject1, Object paramAnonymousObject2)
    {
      return System.identityHashCode(paramAnonymousObject1) - System.identityHashCode(paramAnonymousObject2);
    }
  };

  public static ClientsAnalytics.LogEvent obtainEvent()
  {
    ClientsAnalytics.LogEvent localLogEvent;
    synchronized (mEventCache)
    {
      if (mEventCache.size() != 0)
      {
        localLogEvent = (ClientsAnalytics.LogEvent)mEventCache.first();
        mEventCache.remove(localLogEvent);
      }
      else
      {
        localLogEvent = new ClientsAnalytics.LogEvent();
      }
    }
    return localLogEvent;
  }

  public static ClientsAnalytics.LogEventKeyValues obtainKeyValue()
  {
    ClientsAnalytics.LogEventKeyValues localLogEventKeyValues;
    synchronized (mKeyValueCache)
    {
      if (mKeyValueCache.size() != 0)
      {
        localLogEventKeyValues = (ClientsAnalytics.LogEventKeyValues)mKeyValueCache.first();
        mKeyValueCache.remove(localLogEventKeyValues);
      }
      else
      {
        localLogEventKeyValues = new ClientsAnalytics.LogEventKeyValues();
      }
    }
    return localLogEventKeyValues;
  }

  public static void recycle(ClientsAnalytics.LogEvent paramLogEvent)
  {
    Iterator localIterator = paramLogEvent.getValuesList().iterator();
    while (localIterator.hasNext())
      recycle((ClientsAnalytics.LogEventKeyValues)localIterator.next());
    paramLogEvent.clear();
    synchronized (mEventCache)
    {
      if (mEventCache.size() < 10)
        mEventCache.add(paramLogEvent);
      return;
    }
  }

  public static void recycle(ClientsAnalytics.LogEventKeyValues paramLogEventKeyValues)
  {
    paramLogEventKeyValues.clear();
    synchronized (mKeyValueCache)
    {
      if (mKeyValueCache.size() < 30)
        mKeyValueCache.add(paramLogEventKeyValues);
      return;
    }
  }

  public static void recycleLogRequest(ClientsAnalytics.LogRequest paramLogRequest)
  {
    Iterator localIterator = paramLogRequest.getClickEventList().iterator();
    while (localIterator.hasNext())
      recycle((ClientsAnalytics.LogEvent)localIterator.next());
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.play.analytics.ProtoCache
 * JD-Core Version:    0.6.2
 */
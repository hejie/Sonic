package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

class ComputingConcurrentHashMap<K, V> extends MapMakerInternalMap<K, V>
{
  private static final long serialVersionUID = 4L;
  final Function<? super K, ? extends V> computingFunction;

  ComputingConcurrentHashMap(MapMaker paramMapMaker, Function<? super K, ? extends V> paramFunction)
  {
    super(paramMapMaker);
    this.computingFunction = ((Function)Preconditions.checkNotNull(paramFunction));
  }

  MapMakerInternalMap.Segment<K, V> createSegment(int paramInt1, int paramInt2)
  {
    return new ComputingSegment(this, paramInt1, paramInt2);
  }

  V getOrCompute(K paramK)
    throws ExecutionException
  {
    int i = hash(Preconditions.checkNotNull(paramK));
    return segmentFor(i).getOrCompute(paramK, i, this.computingFunction);
  }

  ComputingSegment<K, V> segmentFor(int paramInt)
  {
    return (ComputingSegment)super.segmentFor(paramInt);
  }

  Object writeReplace()
  {
    return new ComputingSerializationProxy(this.keyStrength, this.valueStrength, this.keyEquivalence, this.valueEquivalence, this.expireAfterWriteNanos, this.expireAfterAccessNanos, this.maximumSize, this.concurrencyLevel, this.removalListener, this, this.computingFunction);
  }

  private static final class ComputationExceptionReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V>
  {
    final Throwable t;

    ComputationExceptionReference(Throwable paramThrowable)
    {
      this.t = paramThrowable;
    }

    public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
    {
    }

    public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return null;
    }

    public MapMakerInternalMap.ReferenceEntry<K, V> getEntry()
    {
      return null;
    }

    public boolean isComputingReference()
    {
      return false;
    }

    public V waitForValue()
      throws ExecutionException
    {
      throw new ExecutionException(this.t);
    }
  }

  private static final class ComputedReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V>
  {
    final V value;

    ComputedReference(V paramV)
    {
      this.value = paramV;
    }

    public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
    {
    }

    public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return this.value;
    }

    public MapMakerInternalMap.ReferenceEntry<K, V> getEntry()
    {
      return null;
    }

    public boolean isComputingReference()
    {
      return false;
    }

    public V waitForValue()
    {
      return get();
    }
  }

  static final class ComputingMapAdapter<K, V> extends ComputingConcurrentHashMap<K, V>
    implements Serializable
  {
    private static final long serialVersionUID;

    ComputingMapAdapter(MapMaker paramMapMaker, Function<? super K, ? extends V> paramFunction)
    {
      super(paramFunction);
    }

    public V get(Object paramObject)
    {
      Object localObject;
      try
      {
        localObject = getOrCompute(paramObject);
        if (localObject == null)
          throw new NullPointerException(this.computingFunction + " returned null for key " + paramObject + ".");
      }
      catch (ExecutionException localExecutionException)
      {
        Throwable localThrowable = localExecutionException.getCause();
        Throwables.propagateIfInstanceOf(localThrowable, ComputationException.class);
        throw new ComputationException(localThrowable);
      }
      return localObject;
    }
  }

  static final class ComputingSegment<K, V> extends MapMakerInternalMap.Segment<K, V>
  {
    ComputingSegment(MapMakerInternalMap<K, V> paramMapMakerInternalMap, int paramInt1, int paramInt2)
    {
      super(paramInt1, paramInt2);
    }

    V compute(K paramK, int paramInt, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, ComputingConcurrentHashMap.ComputingValueReference<K, V> paramComputingValueReference)
      throws ExecutionException
    {
      Object localObject1 = null;
      System.nanoTime();
      long l = 0L;
      try
      {
        try
        {
          localObject1 = paramComputingValueReference.compute(paramK, paramInt);
          l = System.nanoTime();
          if ((localObject1 != null) && (put(paramK, paramInt, localObject1, true) != null))
            enqueueNotification(paramK, paramInt, localObject1, MapMaker.RemovalCause.REPLACED);
          return localObject1;
        }
        finally
        {
        }
      }
      finally
      {
        if (l == 0L)
          System.nanoTime();
        if (localObject1 == null)
          clearValue(paramK, paramInt, paramComputingValueReference);
      }
    }

    // ERROR //
    V getOrCompute(K paramK, int paramInt, Function<? super K, ? extends V> paramFunction)
      throws ExecutionException
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: iload_2
      //   3: invokevirtual 48	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:getEntry	(Ljava/lang/Object;I)Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;
      //   6: astore 5
      //   8: aload 5
      //   10: ifnull +29 -> 39
      //   13: aload_0
      //   14: aload 5
      //   16: invokevirtual 52	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:getLiveValue	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)Ljava/lang/Object;
      //   19: astore 15
      //   21: aload 15
      //   23: ifnull +16 -> 39
      //   26: aload_0
      //   27: aload 5
      //   29: invokevirtual 56	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:recordRead	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)V
      //   32: aload_0
      //   33: invokevirtual 60	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postReadCleanup	()V
      //   36: aload 15
      //   38: areturn
      //   39: aload 5
      //   41: ifnull +18 -> 59
      //   44: aload 5
      //   46: invokeinterface 66 1 0
      //   51: invokeinterface 72 1 0
      //   56: ifne +386 -> 442
      //   59: iconst_1
      //   60: istore 6
      //   62: aconst_null
      //   63: astore 7
      //   65: aload_0
      //   66: invokevirtual 75	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:lock	()V
      //   69: aload_0
      //   70: invokevirtual 78	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:preWriteCleanup	()V
      //   73: iconst_m1
      //   74: aload_0
      //   75: getfield 82	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:count	I
      //   78: iadd
      //   79: istore 9
      //   81: aload_0
      //   82: getfield 86	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:table	Ljava/util/concurrent/atomic/AtomicReferenceArray;
      //   85: astore 10
      //   87: iload_2
      //   88: iconst_m1
      //   89: aload 10
      //   91: invokevirtual 92	java/util/concurrent/atomic/AtomicReferenceArray:length	()I
      //   94: iadd
      //   95: iand
      //   96: istore 11
      //   98: aload 10
      //   100: iload 11
      //   102: invokevirtual 96	java/util/concurrent/atomic/AtomicReferenceArray:get	(I)Ljava/lang/Object;
      //   105: checkcast 62	com/google/common/collect/MapMakerInternalMap$ReferenceEntry
      //   108: astore 12
      //   110: aload 12
      //   112: astore 5
      //   114: aload 5
      //   116: ifnull +62 -> 178
      //   119: aload 5
      //   121: invokeinterface 100 1 0
      //   126: astore 17
      //   128: aload 5
      //   130: invokeinterface 103 1 0
      //   135: iload_2
      //   136: if_icmpne +274 -> 410
      //   139: aload 17
      //   141: ifnull +269 -> 410
      //   144: aload_0
      //   145: getfield 107	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:map	Lcom/google/common/collect/MapMakerInternalMap;
      //   148: getfield 113	com/google/common/collect/MapMakerInternalMap:keyEquivalence	Lcom/google/common/base/Equivalence;
      //   151: aload_1
      //   152: aload 17
      //   154: invokevirtual 119	com/google/common/base/Equivalence:equivalent	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   157: ifeq +253 -> 410
      //   160: aload 5
      //   162: invokeinterface 66 1 0
      //   167: invokeinterface 72 1 0
      //   172: ifeq +94 -> 266
      //   175: iconst_0
      //   176: istore 6
      //   178: iload 6
      //   180: ifeq +50 -> 230
      //   183: new 21	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference
      //   186: dup
      //   187: aload_3
      //   188: invokespecial 122	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference:<init>	(Lcom/google/common/base/Function;)V
      //   191: astore 13
      //   193: aload 5
      //   195: ifnonnull +231 -> 426
      //   198: aload_0
      //   199: aload_1
      //   200: iload_2
      //   201: aload 12
      //   203: invokevirtual 126	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:newEntry	(Ljava/lang/Object;ILcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;
      //   206: astore 5
      //   208: aload 5
      //   210: aload 13
      //   212: invokeinterface 130 2 0
      //   217: aload 10
      //   219: iload 11
      //   221: aload 5
      //   223: invokevirtual 134	java/util/concurrent/atomic/AtomicReferenceArray:set	(ILjava/lang/Object;)V
      //   226: aload 13
      //   228: astore 7
      //   230: aload_0
      //   231: invokevirtual 137	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:unlock	()V
      //   234: aload_0
      //   235: invokevirtual 140	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postWriteCleanup	()V
      //   238: iload 6
      //   240: ifeq +202 -> 442
      //   243: aload_0
      //   244: aload_1
      //   245: iload_2
      //   246: aload 5
      //   248: aload 7
      //   250: invokevirtual 142	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:compute	(Ljava/lang/Object;ILcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;Lcom/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference;)Ljava/lang/Object;
      //   253: astore 16
      //   255: aload 16
      //   257: astore 15
      //   259: aload_0
      //   260: invokevirtual 60	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postReadCleanup	()V
      //   263: goto -227 -> 36
      //   266: aload 5
      //   268: invokeinterface 66 1 0
      //   273: invokeinterface 144 1 0
      //   278: astore 15
      //   280: aload 15
      //   282: ifnonnull +70 -> 352
      //   285: aload_0
      //   286: aload 17
      //   288: iload_2
      //   289: aload 15
      //   291: getstatic 147	com/google/common/collect/MapMaker$RemovalCause:COLLECTED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   294: invokevirtual 38	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:enqueueNotification	(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/common/collect/MapMaker$RemovalCause;)V
      //   297: aload_0
      //   298: getfield 151	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:evictionQueue	Ljava/util/Queue;
      //   301: aload 5
      //   303: invokeinterface 157 2 0
      //   308: pop
      //   309: aload_0
      //   310: getfield 160	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:expirationQueue	Ljava/util/Queue;
      //   313: aload 5
      //   315: invokeinterface 157 2 0
      //   320: pop
      //   321: aload_0
      //   322: iload 9
      //   324: putfield 82	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:count	I
      //   327: goto -149 -> 178
      //   330: astore 8
      //   332: aload_0
      //   333: invokevirtual 137	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:unlock	()V
      //   336: aload_0
      //   337: invokevirtual 140	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postWriteCleanup	()V
      //   340: aload 8
      //   342: athrow
      //   343: astore 4
      //   345: aload_0
      //   346: invokevirtual 60	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postReadCleanup	()V
      //   349: aload 4
      //   351: athrow
      //   352: aload_0
      //   353: getfield 107	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:map	Lcom/google/common/collect/MapMakerInternalMap;
      //   356: invokevirtual 163	com/google/common/collect/MapMakerInternalMap:expires	()Z
      //   359: ifeq +30 -> 389
      //   362: aload_0
      //   363: getfield 107	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:map	Lcom/google/common/collect/MapMakerInternalMap;
      //   366: aload 5
      //   368: invokevirtual 167	com/google/common/collect/MapMakerInternalMap:isExpired	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)Z
      //   371: ifeq +18 -> 389
      //   374: aload_0
      //   375: aload 17
      //   377: iload_2
      //   378: aload 15
      //   380: getstatic 170	com/google/common/collect/MapMaker$RemovalCause:EXPIRED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   383: invokevirtual 38	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:enqueueNotification	(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/common/collect/MapMaker$RemovalCause;)V
      //   386: goto -89 -> 297
      //   389: aload_0
      //   390: aload 5
      //   392: invokevirtual 173	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:recordLockedRead	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)V
      //   395: aload_0
      //   396: invokevirtual 137	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:unlock	()V
      //   399: aload_0
      //   400: invokevirtual 140	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postWriteCleanup	()V
      //   403: aload_0
      //   404: invokevirtual 60	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postReadCleanup	()V
      //   407: goto -371 -> 36
      //   410: aload 5
      //   412: invokeinterface 177 1 0
      //   417: astore 18
      //   419: aload 18
      //   421: astore 5
      //   423: goto -309 -> 114
      //   426: aload 5
      //   428: aload 13
      //   430: invokeinterface 130 2 0
      //   435: aload 13
      //   437: astore 7
      //   439: goto -209 -> 230
      //   442: aload 5
      //   444: invokestatic 182	java/lang/Thread:holdsLock	(Ljava/lang/Object;)Z
      //   447: ifne +45 -> 492
      //   450: iconst_1
      //   451: istore 14
      //   453: iload 14
      //   455: ldc 184
      //   457: invokestatic 190	com/google/common/base/Preconditions:checkState	(ZLjava/lang/Object;)V
      //   460: aload 5
      //   462: invokeinterface 66 1 0
      //   467: invokeinterface 193 1 0
      //   472: astore 15
      //   474: aload 15
      //   476: ifnull -476 -> 0
      //   479: aload_0
      //   480: aload 5
      //   482: invokevirtual 56	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:recordRead	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)V
      //   485: aload_0
      //   486: invokevirtual 60	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:postReadCleanup	()V
      //   489: goto -453 -> 36
      //   492: iconst_0
      //   493: istore 14
      //   495: goto -42 -> 453
      //   498: astore 8
      //   500: goto -168 -> 332
      //
      // Exception table:
      //   from	to	target	type
      //   69	193	330	finally
      //   266	327	330	finally
      //   352	395	330	finally
      //   410	419	330	finally
      //   0	32	343	finally
      //   44	69	343	finally
      //   230	255	343	finally
      //   332	343	343	finally
      //   395	403	343	finally
      //   442	485	343	finally
      //   198	226	498	finally
      //   426	435	498	finally
    }
  }

  static final class ComputingSerializationProxy<K, V> extends MapMakerInternalMap.AbstractSerializationProxy<K, V>
  {
    private static final long serialVersionUID = 4L;
    final Function<? super K, ? extends V> computingFunction;

    ComputingSerializationProxy(MapMakerInternalMap.Strength paramStrength1, MapMakerInternalMap.Strength paramStrength2, Equivalence<Object> paramEquivalence1, Equivalence<Object> paramEquivalence2, long paramLong1, long paramLong2, int paramInt1, int paramInt2, MapMaker.RemovalListener<? super K, ? super V> paramRemovalListener, ConcurrentMap<K, V> paramConcurrentMap, Function<? super K, ? extends V> paramFunction)
    {
      super(paramStrength2, paramEquivalence1, paramEquivalence2, paramLong1, paramLong2, paramInt1, paramInt2, paramRemovalListener, paramConcurrentMap);
      this.computingFunction = paramFunction;
    }

    private void readObject(ObjectInputStream paramObjectInputStream)
      throws IOException, ClassNotFoundException
    {
      paramObjectInputStream.defaultReadObject();
      this.delegate = readMapMaker(paramObjectInputStream).makeComputingMap(this.computingFunction);
      readEntries(paramObjectInputStream);
    }

    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      paramObjectOutputStream.defaultWriteObject();
      writeMapTo(paramObjectOutputStream);
    }

    Object readResolve()
    {
      return this.delegate;
    }
  }

  private static final class ComputingValueReference<K, V>
    implements MapMakerInternalMap.ValueReference<K, V>
  {
    volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
    final Function<? super K, ? extends V> computingFunction;

    public ComputingValueReference(Function<? super K, ? extends V> paramFunction)
    {
      this.computingFunction = paramFunction;
    }

    public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
    {
      setValueReference(paramValueReference);
    }

    V compute(K paramK, int paramInt)
      throws ExecutionException
    {
      try
      {
        Object localObject = this.computingFunction.apply(paramK);
        setValueReference(new ComputingConcurrentHashMap.ComputedReference(localObject));
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        setValueReference(new ComputingConcurrentHashMap.ComputationExceptionReference(localThrowable));
        throw new ExecutionException(localThrowable);
      }
    }

    public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
    {
      return this;
    }

    public V get()
    {
      return null;
    }

    public MapMakerInternalMap.ReferenceEntry<K, V> getEntry()
    {
      return null;
    }

    public boolean isComputingReference()
    {
      return true;
    }

    void setValueReference(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
    {
      try
      {
        if (this.computedReference == MapMakerInternalMap.UNSET)
        {
          this.computedReference = paramValueReference;
          notifyAll();
        }
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public V waitForValue()
      throws ExecutionException
    {
      int i;
      if (this.computedReference == MapMakerInternalMap.UNSET)
        i = 0;
      try
      {
        try
        {
          while (true)
          {
            MapMakerInternalMap.ValueReference localValueReference1 = this.computedReference;
            MapMakerInternalMap.ValueReference localValueReference2 = MapMakerInternalMap.UNSET;
            if (localValueReference1 != localValueReference2)
              break;
            try
            {
              wait();
            }
            catch (InterruptedException localInterruptedException)
            {
              i = 1;
            }
          }
          return this.computedReference.waitForValue();
        }
        finally
        {
        }
      }
      finally
      {
        if (i != 0)
          Thread.currentThread().interrupt();
      }
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ComputingConcurrentHashMap
 * JD-Core Version:    0.6.2
 */
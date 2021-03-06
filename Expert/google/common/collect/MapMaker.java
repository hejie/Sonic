package com.google.common.collect;

import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.google.common.base.Ticker;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public final class MapMaker extends GenericMapMaker<Object, Object>
{
  int concurrencyLevel = -1;
  long expireAfterAccessNanos = -1L;
  long expireAfterWriteNanos = -1L;
  int initialCapacity = -1;
  Equivalence<Object> keyEquivalence;
  MapMakerInternalMap.Strength keyStrength;
  int maximumSize = -1;
  RemovalCause nullRemovalCause;
  Ticker ticker;
  boolean useCustomMap;
  Equivalence<Object> valueEquivalence;
  MapMakerInternalMap.Strength valueStrength;

  private void checkExpiration(long paramLong, TimeUnit paramTimeUnit)
  {
    boolean bool1;
    boolean bool2;
    if (this.expireAfterWriteNanos == -1L)
    {
      bool1 = true;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Long.valueOf(this.expireAfterWriteNanos);
      Preconditions.checkState(bool1, "expireAfterWrite was already set to %s ns", arrayOfObject1);
      if (this.expireAfterAccessNanos != -1L)
        break label124;
      bool2 = true;
      label54: Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Long.valueOf(this.expireAfterAccessNanos);
      Preconditions.checkState(bool2, "expireAfterAccess was already set to %s ns", arrayOfObject2);
      if (paramLong < 0L)
        break label130;
    }
    label130: for (boolean bool3 = true; ; bool3 = false)
    {
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Long.valueOf(paramLong);
      arrayOfObject3[1] = paramTimeUnit;
      Preconditions.checkArgument(bool3, "duration cannot be negative: %s %s", arrayOfObject3);
      return;
      bool1 = false;
      break;
      label124: bool2 = false;
      break label54;
    }
  }

  private boolean useNullMap()
  {
    if (this.nullRemovalCause == null);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public MapMaker concurrencyLevel(int paramInt)
  {
    boolean bool1 = true;
    boolean bool2;
    if (this.concurrencyLevel == -1)
    {
      bool2 = bool1;
      Object[] arrayOfObject = new Object[bool1];
      arrayOfObject[0] = Integer.valueOf(this.concurrencyLevel);
      Preconditions.checkState(bool2, "concurrency level was already set to %s", arrayOfObject);
      if (paramInt <= 0)
        break label57;
    }
    while (true)
    {
      Preconditions.checkArgument(bool1);
      this.concurrencyLevel = paramInt;
      return this;
      bool2 = false;
      break;
      label57: bool1 = false;
    }
  }

  @Deprecated
  MapMaker expireAfterAccess(long paramLong, TimeUnit paramTimeUnit)
  {
    checkExpiration(paramLong, paramTimeUnit);
    this.expireAfterAccessNanos = paramTimeUnit.toNanos(paramLong);
    if ((paramLong == 0L) && (this.nullRemovalCause == null))
      this.nullRemovalCause = RemovalCause.EXPIRED;
    this.useCustomMap = true;
    return this;
  }

  @Deprecated
  MapMaker expireAfterWrite(long paramLong, TimeUnit paramTimeUnit)
  {
    checkExpiration(paramLong, paramTimeUnit);
    this.expireAfterWriteNanos = paramTimeUnit.toNanos(paramLong);
    if ((paramLong == 0L) && (this.nullRemovalCause == null))
      this.nullRemovalCause = RemovalCause.EXPIRED;
    this.useCustomMap = true;
    return this;
  }

  int getConcurrencyLevel()
  {
    if (this.concurrencyLevel == -1);
    for (int i = 4; ; i = this.concurrencyLevel)
      return i;
  }

  long getExpireAfterAccessNanos()
  {
    if (this.expireAfterAccessNanos == -1L);
    for (long l = 0L; ; l = this.expireAfterAccessNanos)
      return l;
  }

  long getExpireAfterWriteNanos()
  {
    if (this.expireAfterWriteNanos == -1L);
    for (long l = 0L; ; l = this.expireAfterWriteNanos)
      return l;
  }

  int getInitialCapacity()
  {
    if (this.initialCapacity == -1);
    for (int i = 16; ; i = this.initialCapacity)
      return i;
  }

  Equivalence<Object> getKeyEquivalence()
  {
    return (Equivalence)Objects.firstNonNull(this.keyEquivalence, getKeyStrength().defaultEquivalence());
  }

  MapMakerInternalMap.Strength getKeyStrength()
  {
    return (MapMakerInternalMap.Strength)Objects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
  }

  Ticker getTicker()
  {
    return (Ticker)Objects.firstNonNull(this.ticker, Ticker.systemTicker());
  }

  Equivalence<Object> getValueEquivalence()
  {
    return (Equivalence)Objects.firstNonNull(this.valueEquivalence, getValueStrength().defaultEquivalence());
  }

  MapMakerInternalMap.Strength getValueStrength()
  {
    return (MapMakerInternalMap.Strength)Objects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
  }

  public MapMaker initialCapacity(int paramInt)
  {
    boolean bool1 = true;
    boolean bool2;
    if (this.initialCapacity == -1)
    {
      bool2 = bool1;
      Object[] arrayOfObject = new Object[bool1];
      arrayOfObject[0] = Integer.valueOf(this.initialCapacity);
      Preconditions.checkState(bool2, "initial capacity was already set to %s", arrayOfObject);
      if (paramInt < 0)
        break label57;
    }
    while (true)
    {
      Preconditions.checkArgument(bool1);
      this.initialCapacity = paramInt;
      return this;
      bool2 = false;
      break;
      label57: bool1 = false;
    }
  }

  MapMaker keyEquivalence(Equivalence<Object> paramEquivalence)
  {
    if (this.keyEquivalence == null);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.keyEquivalence;
      Preconditions.checkState(bool, "key equivalence was already set to %s", arrayOfObject);
      this.keyEquivalence = ((Equivalence)Preconditions.checkNotNull(paramEquivalence));
      this.useCustomMap = true;
      return this;
    }
  }

  @Deprecated
  public <K, V> ConcurrentMap<K, V> makeComputingMap(Function<? super K, ? extends V> paramFunction)
  {
    if (useNullMap());
    for (Object localObject = new ComputingConcurrentHashMap.ComputingMapAdapter(this, paramFunction); ; localObject = new NullComputingConcurrentMap(this, paramFunction))
      return localObject;
  }

  public <K, V> ConcurrentMap<K, V> makeMap()
  {
    Object localObject;
    if (!this.useCustomMap)
      localObject = new ConcurrentHashMap(getInitialCapacity(), 0.75F, getConcurrencyLevel());
    while (true)
    {
      return localObject;
      if (this.nullRemovalCause == null)
        localObject = new MapMakerInternalMap(this);
      else
        localObject = new NullConcurrentMap(this);
    }
  }

  @Deprecated
  MapMaker maximumSize(int paramInt)
  {
    boolean bool1 = false;
    if (this.maximumSize == -1);
    for (boolean bool2 = true; ; bool2 = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.maximumSize);
      Preconditions.checkState(bool2, "maximum size was already set to %s", arrayOfObject);
      if (paramInt >= 0)
        bool1 = true;
      Preconditions.checkArgument(bool1, "maximum size must not be negative");
      this.maximumSize = paramInt;
      this.useCustomMap = true;
      if (this.maximumSize == 0)
        this.nullRemovalCause = RemovalCause.SIZE;
      return this;
    }
  }

  @Deprecated
  <K, V> GenericMapMaker<K, V> removalListener(RemovalListener<K, V> paramRemovalListener)
  {
    if (this.removalListener == null);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkState(bool);
      this.removalListener = ((RemovalListener)Preconditions.checkNotNull(paramRemovalListener));
      this.useCustomMap = true;
      return this;
    }
  }

  MapMaker setKeyStrength(MapMakerInternalMap.Strength paramStrength)
  {
    if (this.keyStrength == null);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.keyStrength;
      Preconditions.checkState(bool, "Key strength was already set to %s", arrayOfObject);
      this.keyStrength = ((MapMakerInternalMap.Strength)Preconditions.checkNotNull(paramStrength));
      if (paramStrength != MapMakerInternalMap.Strength.STRONG)
        this.useCustomMap = true;
      return this;
    }
  }

  MapMaker setValueStrength(MapMakerInternalMap.Strength paramStrength)
  {
    if (this.valueStrength == null);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.valueStrength;
      Preconditions.checkState(bool, "Value strength was already set to %s", arrayOfObject);
      this.valueStrength = ((MapMakerInternalMap.Strength)Preconditions.checkNotNull(paramStrength));
      if (paramStrength != MapMakerInternalMap.Strength.STRONG)
        this.useCustomMap = true;
      return this;
    }
  }

  public String toString()
  {
    Objects.ToStringHelper localToStringHelper = Objects.toStringHelper(this);
    if (this.initialCapacity != -1)
      localToStringHelper.add("initialCapacity", this.initialCapacity);
    if (this.concurrencyLevel != -1)
      localToStringHelper.add("concurrencyLevel", this.concurrencyLevel);
    if (this.maximumSize != -1)
      localToStringHelper.add("maximumSize", this.maximumSize);
    if (this.expireAfterWriteNanos != -1L)
      localToStringHelper.add("expireAfterWrite", this.expireAfterWriteNanos + "ns");
    if (this.expireAfterAccessNanos != -1L)
      localToStringHelper.add("expireAfterAccess", this.expireAfterAccessNanos + "ns");
    if (this.keyStrength != null)
      localToStringHelper.add("keyStrength", Ascii.toLowerCase(this.keyStrength.toString()));
    if (this.valueStrength != null)
      localToStringHelper.add("valueStrength", Ascii.toLowerCase(this.valueStrength.toString()));
    if (this.keyEquivalence != null)
      localToStringHelper.addValue("keyEquivalence");
    if (this.valueEquivalence != null)
      localToStringHelper.addValue("valueEquivalence");
    if (this.removalListener != null)
      localToStringHelper.addValue("removalListener");
    return localToStringHelper.toString();
  }

  MapMaker valueEquivalence(Equivalence<Object> paramEquivalence)
  {
    if (this.valueEquivalence == null);
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.valueEquivalence;
      Preconditions.checkState(bool, "value equivalence was already set to %s", arrayOfObject);
      this.valueEquivalence = ((Equivalence)Preconditions.checkNotNull(paramEquivalence));
      this.useCustomMap = true;
      return this;
    }
  }

  public MapMaker weakKeys()
  {
    return setKeyStrength(MapMakerInternalMap.Strength.WEAK);
  }

  static final class NullComputingConcurrentMap<K, V> extends MapMaker.NullConcurrentMap<K, V>
  {
    private static final long serialVersionUID;
    final Function<? super K, ? extends V> computingFunction;

    NullComputingConcurrentMap(MapMaker paramMapMaker, Function<? super K, ? extends V> paramFunction)
    {
      super();
      this.computingFunction = ((Function)Preconditions.checkNotNull(paramFunction));
    }

    private V compute(K paramK)
    {
      Preconditions.checkNotNull(paramK);
      try
      {
        Object localObject = this.computingFunction.apply(paramK);
        return localObject;
      }
      catch (ComputationException localComputationException)
      {
        throw localComputationException;
      }
      catch (Throwable localThrowable)
      {
        throw new ComputationException(localThrowable);
      }
    }

    public V get(Object paramObject)
    {
      Object localObject = compute(paramObject);
      Preconditions.checkNotNull(localObject, this.computingFunction + " returned null for key " + paramObject + ".");
      notifyRemoval(paramObject, localObject);
      return localObject;
    }
  }

  static class NullConcurrentMap<K, V> extends AbstractMap<K, V>
    implements ConcurrentMap<K, V>, Serializable
  {
    private static final long serialVersionUID;
    private final MapMaker.RemovalCause removalCause;
    private final MapMaker.RemovalListener<K, V> removalListener;

    NullConcurrentMap(MapMaker paramMapMaker)
    {
      this.removalListener = paramMapMaker.getRemovalListener();
      this.removalCause = paramMapMaker.nullRemovalCause;
    }

    public boolean containsKey(Object paramObject)
    {
      return false;
    }

    public boolean containsValue(Object paramObject)
    {
      return false;
    }

    public Set<Map.Entry<K, V>> entrySet()
    {
      return Collections.emptySet();
    }

    public V get(Object paramObject)
    {
      return null;
    }

    void notifyRemoval(K paramK, V paramV)
    {
      MapMaker.RemovalNotification localRemovalNotification = new MapMaker.RemovalNotification(paramK, paramV, this.removalCause);
      this.removalListener.onRemoval(localRemovalNotification);
    }

    public V put(K paramK, V paramV)
    {
      Preconditions.checkNotNull(paramK);
      Preconditions.checkNotNull(paramV);
      notifyRemoval(paramK, paramV);
      return null;
    }

    public V putIfAbsent(K paramK, V paramV)
    {
      return put(paramK, paramV);
    }

    public V remove(Object paramObject)
    {
      return null;
    }

    public boolean remove(Object paramObject1, Object paramObject2)
    {
      return false;
    }

    public V replace(K paramK, V paramV)
    {
      Preconditions.checkNotNull(paramK);
      Preconditions.checkNotNull(paramV);
      return null;
    }

    public boolean replace(K paramK, V paramV1, V paramV2)
    {
      Preconditions.checkNotNull(paramK);
      Preconditions.checkNotNull(paramV2);
      return false;
    }
  }

  static abstract enum RemovalCause
  {
    static
    {
      // Byte code:
      //   0: new 17	com/google/common/collect/MapMaker$RemovalCause$1
      //   3: dup
      //   4: ldc 18
      //   6: iconst_0
      //   7: invokespecial 22	com/google/common/collect/MapMaker$RemovalCause$1:<init>	(Ljava/lang/String;I)V
      //   10: putstatic 24	com/google/common/collect/MapMaker$RemovalCause:EXPLICIT	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   13: new 26	com/google/common/collect/MapMaker$RemovalCause$2
      //   16: dup
      //   17: ldc 27
      //   19: iconst_1
      //   20: invokespecial 28	com/google/common/collect/MapMaker$RemovalCause$2:<init>	(Ljava/lang/String;I)V
      //   23: putstatic 30	com/google/common/collect/MapMaker$RemovalCause:REPLACED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   26: new 32	com/google/common/collect/MapMaker$RemovalCause$3
      //   29: dup
      //   30: ldc 33
      //   32: iconst_2
      //   33: invokespecial 34	com/google/common/collect/MapMaker$RemovalCause$3:<init>	(Ljava/lang/String;I)V
      //   36: putstatic 36	com/google/common/collect/MapMaker$RemovalCause:COLLECTED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   39: new 38	com/google/common/collect/MapMaker$RemovalCause$4
      //   42: dup
      //   43: ldc 39
      //   45: iconst_3
      //   46: invokespecial 40	com/google/common/collect/MapMaker$RemovalCause$4:<init>	(Ljava/lang/String;I)V
      //   49: putstatic 42	com/google/common/collect/MapMaker$RemovalCause:EXPIRED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   52: new 44	com/google/common/collect/MapMaker$RemovalCause$5
      //   55: dup
      //   56: ldc 45
      //   58: iconst_4
      //   59: invokespecial 46	com/google/common/collect/MapMaker$RemovalCause$5:<init>	(Ljava/lang/String;I)V
      //   62: putstatic 48	com/google/common/collect/MapMaker$RemovalCause:SIZE	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   65: iconst_5
      //   66: anewarray 2	com/google/common/collect/MapMaker$RemovalCause
      //   69: astore_0
      //   70: aload_0
      //   71: iconst_0
      //   72: getstatic 24	com/google/common/collect/MapMaker$RemovalCause:EXPLICIT	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   75: aastore
      //   76: aload_0
      //   77: iconst_1
      //   78: getstatic 30	com/google/common/collect/MapMaker$RemovalCause:REPLACED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   81: aastore
      //   82: aload_0
      //   83: iconst_2
      //   84: getstatic 36	com/google/common/collect/MapMaker$RemovalCause:COLLECTED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   87: aastore
      //   88: aload_0
      //   89: iconst_3
      //   90: getstatic 42	com/google/common/collect/MapMaker$RemovalCause:EXPIRED	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   93: aastore
      //   94: aload_0
      //   95: iconst_4
      //   96: getstatic 48	com/google/common/collect/MapMaker$RemovalCause:SIZE	Lcom/google/common/collect/MapMaker$RemovalCause;
      //   99: aastore
      //   100: aload_0
      //   101: putstatic 50	com/google/common/collect/MapMaker$RemovalCause:$VALUES	[Lcom/google/common/collect/MapMaker$RemovalCause;
      //   104: return
    }
  }

  static abstract interface RemovalListener<K, V>
  {
    public abstract void onRemoval(MapMaker.RemovalNotification<K, V> paramRemovalNotification);
  }

  static final class RemovalNotification<K, V> extends ImmutableEntry<K, V>
  {
    private static final long serialVersionUID;
    private final MapMaker.RemovalCause cause;

    RemovalNotification(K paramK, V paramV, MapMaker.RemovalCause paramRemovalCause)
    {
      super(paramV);
      this.cause = paramRemovalCause;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.MapMaker
 * JD-Core Version:    0.6.2
 */
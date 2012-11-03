package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;

public abstract class ImmutableCollection<E>
  implements Collection<E>, Serializable
{
  static final ImmutableCollection<Object> EMPTY_IMMUTABLE_COLLECTION = new EmptyImmutableCollection(null);

  public final boolean add(E paramE)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean addAll(Collection<? extends E> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final void clear()
  {
    throw new UnsupportedOperationException();
  }

  public boolean contains(Object paramObject)
  {
    if ((paramObject != null) && (Iterators.contains(iterator(), paramObject)));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    return Collections2.containsAllImpl(this, paramCollection);
  }

  public boolean isEmpty()
  {
    if (size() == 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public abstract UnmodifiableIterator<E> iterator();

  public final boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public final boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }

  public Object[] toArray()
  {
    return ObjectArrays.toArrayImpl(this);
  }

  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return ObjectArrays.toArrayImpl(this, paramArrayOfT);
  }

  public String toString()
  {
    return Collections2.toStringImpl(this);
  }

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  private static class ArrayImmutableCollection<E> extends ImmutableCollection<E>
  {
    private final E[] elements;

    ArrayImmutableCollection(E[] paramArrayOfE)
    {
      this.elements = paramArrayOfE;
    }

    public boolean isEmpty()
    {
      return false;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.forArray(this.elements);
    }

    public int size()
    {
      return this.elements.length;
    }
  }

  private static class EmptyImmutableCollection extends ImmutableCollection<Object>
  {
    private static final Object[] EMPTY_ARRAY = new Object[0];

    public boolean contains(Object paramObject)
    {
      return false;
    }

    public boolean isEmpty()
    {
      return true;
    }

    public UnmodifiableIterator<Object> iterator()
    {
      return Iterators.EMPTY_ITERATOR;
    }

    public int size()
    {
      return 0;
    }

    public Object[] toArray()
    {
      return EMPTY_ARRAY;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      if (paramArrayOfT.length > 0)
        paramArrayOfT[0] = null;
      return paramArrayOfT;
    }
  }

  private static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    final Object[] elements;

    SerializedForm(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    Object readResolve()
    {
      if (this.elements.length == 0);
      for (Object localObject = ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION; ; localObject = new ImmutableCollection.ArrayImmutableCollection(Platform.clone(this.elements)))
        return localObject;
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ImmutableCollection
 * JD-Core Version:    0.6.2
 */
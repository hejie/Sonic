package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public abstract class ImmutableSet<E> extends ImmutableCollection<E>
  implements Set<E>
{
  static int chooseTableSize(int paramInt)
  {
    int i;
    if (paramInt < 536870912)
    {
      i = Integer.highestOneBit(paramInt) << 2;
      return i;
    }
    if (paramInt < 1073741824);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool, "collection too large");
      i = 1073741824;
      break;
    }
  }

  private static <E> ImmutableSet<E> construct(Object[] paramArrayOfObject)
  {
    int i = chooseTableSize(paramArrayOfObject.length);
    Object[] arrayOfObject1 = new Object[i];
    int j = i - 1;
    ArrayList localArrayList = null;
    int k = 0;
    int m = 0;
    if (m < paramArrayOfObject.length)
    {
      Object localObject3 = paramArrayOfObject[m];
      int n = localObject3.hashCode();
      label152: for (int i1 = Hashing.smear(n); ; i1++)
      {
        int i2 = i1 & j;
        Object localObject4 = arrayOfObject1[i2];
        if (localObject4 == null)
        {
          if (localArrayList != null)
            localArrayList.add(localObject3);
          arrayOfObject1[i2] = localObject3;
          k += n;
        }
        while (true)
        {
          m++;
          break;
          if (!localObject4.equals(localObject3))
            break label152;
          if (localArrayList == null)
          {
            localArrayList = new ArrayList(paramArrayOfObject.length);
            for (int i3 = 0; i3 < m; i3++)
              localArrayList.add(paramArrayOfObject[i3]);
          }
        }
      }
    }
    Object[] arrayOfObject2;
    Object localObject1;
    if (localArrayList == null)
    {
      arrayOfObject2 = paramArrayOfObject;
      if (arrayOfObject2.length != 1)
        break label205;
      Object localObject2 = arrayOfObject2[0];
      localObject1 = new SingletonImmutableSet(localObject2, k);
    }
    while (true)
    {
      return localObject1;
      arrayOfObject2 = localArrayList.toArray();
      break;
      label205: if (i > 2 * chooseTableSize(arrayOfObject2.length))
        localObject1 = construct(arrayOfObject2);
      else
        localObject1 = new RegularImmutableSet(arrayOfObject2, k, arrayOfObject1, j);
    }
  }

  public static <E> ImmutableSet<E> copyOf(E[] paramArrayOfE)
  {
    ImmutableSet localImmutableSet;
    switch (paramArrayOfE.length)
    {
    default:
      localImmutableSet = construct((Object[])paramArrayOfE.clone());
    case 0:
    case 1:
    }
    while (true)
    {
      return localImmutableSet;
      localImmutableSet = of();
      continue;
      localImmutableSet = of(paramArrayOfE[0]);
    }
  }

  public static <E> ImmutableSet<E> of()
  {
    return EmptyImmutableSet.INSTANCE;
  }

  public static <E> ImmutableSet<E> of(E paramE)
  {
    return new SingletonImmutableSet(paramE);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (paramObject == this)
      bool = true;
    while (true)
    {
      return bool;
      if (((paramObject instanceof ImmutableSet)) && (isHashCodeFast()) && (((ImmutableSet)paramObject).isHashCodeFast()) && (hashCode() != paramObject.hashCode()))
        bool = false;
      else
        bool = Sets.equalsImpl(this, paramObject);
    }
  }

  public int hashCode()
  {
    return Sets.hashCodeImpl(this);
  }

  boolean isHashCodeFast()
  {
    return false;
  }

  public abstract UnmodifiableIterator<E> iterator();

  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }

  static abstract class ArrayImmutableSet<E> extends ImmutableSet<E>
  {
    final transient Object[] elements;

    ArrayImmutableSet(Object[] paramArrayOfObject)
    {
      this.elements = paramArrayOfObject;
    }

    public boolean containsAll(Collection<?> paramCollection)
    {
      boolean bool = true;
      if (paramCollection == this);
      label85: 
      while (true)
      {
        return bool;
        if (!(paramCollection instanceof ArrayImmutableSet))
        {
          bool = super.containsAll(paramCollection);
        }
        else if (paramCollection.size() > size())
        {
          bool = false;
        }
        else
        {
          Object[] arrayOfObject = ((ArrayImmutableSet)paramCollection).elements;
          int i = arrayOfObject.length;
          for (int j = 0; ; j++)
          {
            if (j >= i)
              break label85;
            if (!contains(arrayOfObject[j]))
            {
              bool = false;
              break;
            }
          }
        }
      }
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

    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(this.elements, 0, arrayOfObject, 0, size());
      return arrayOfObject;
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      if (paramArrayOfT.length < i)
        paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, i);
      while (true)
      {
        System.arraycopy(this.elements, 0, paramArrayOfT, 0, i);
        return paramArrayOfT;
        if (paramArrayOfT.length > i)
          paramArrayOfT[i] = null;
      }
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
      return ImmutableSet.copyOf(this.elements);
    }
  }

  static abstract class TransformedImmutableSet<D, E> extends ImmutableSet<E>
  {
    final int hashCode;
    final D[] source;

    TransformedImmutableSet(D[] paramArrayOfD, int paramInt)
    {
      this.source = paramArrayOfD;
      this.hashCode = paramInt;
    }

    public final int hashCode()
    {
      return this.hashCode;
    }

    public boolean isEmpty()
    {
      return false;
    }

    boolean isHashCodeFast()
    {
      return true;
    }

    public UnmodifiableIterator<E> iterator()
    {
      return new AbstractIndexedListIterator(this.source.length)
      {
        protected E get(int paramAnonymousInt)
        {
          return ImmutableSet.TransformedImmutableSet.this.transform(ImmutableSet.TransformedImmutableSet.this.source[paramAnonymousInt]);
        }
      };
    }

    public int size()
    {
      return this.source.length;
    }

    public Object[] toArray()
    {
      return toArray(new Object[size()]);
    }

    public <T> T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      if (paramArrayOfT.length < i)
        paramArrayOfT = ObjectArrays.newArray(paramArrayOfT, i);
      while (true)
      {
        T[] arrayOfT = paramArrayOfT;
        for (int j = 0; j < this.source.length; j++)
          arrayOfT[j] = transform(this.source[j]);
        if (paramArrayOfT.length > i)
          paramArrayOfT[i] = null;
      }
      return paramArrayOfT;
    }

    abstract E transform(D paramD);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ImmutableSet
 * JD-Core Version:    0.6.2
 */
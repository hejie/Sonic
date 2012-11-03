package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Iterators
{
  static final UnmodifiableIterator<Object> EMPTY_ITERATOR = new UnmodifiableIterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }
  };
  private static final Iterator<Object> EMPTY_MODIFIABLE_ITERATOR = new Iterator()
  {
    public boolean hasNext()
    {
      return false;
    }

    public Object next()
    {
      throw new NoSuchElementException();
    }

    public void remove()
    {
      throw new IllegalStateException();
    }
  };

  public static boolean contains(Iterator<?> paramIterator, Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null)
      do
        if (!paramIterator.hasNext())
          break;
      while (paramIterator.next() != null);
    while (true)
    {
      return bool;
      while (true)
        if (paramIterator.hasNext())
          if (paramObject.equals(paramIterator.next()))
            break;
      bool = false;
    }
  }

  public static <T> UnmodifiableIterator<T> emptyIterator()
  {
    return EMPTY_ITERATOR;
  }

  public static <T> UnmodifiableIterator<T> forArray(final T[] paramArrayOfT)
  {
    Preconditions.checkNotNull(paramArrayOfT);
    return new AbstractIndexedListIterator(paramArrayOfT.length)
    {
      protected T get(int paramAnonymousInt)
      {
        return paramArrayOfT[paramAnonymousInt];
      }
    };
  }

  public static <T> T getOnlyElement(Iterator<T> paramIterator)
  {
    Object localObject = paramIterator.next();
    if (!paramIterator.hasNext())
      return localObject;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("expected one element but was: <" + localObject);
    for (int i = 0; (i < 4) && (paramIterator.hasNext()); i++)
      localStringBuilder.append(", " + paramIterator.next());
    if (paramIterator.hasNext())
      localStringBuilder.append(", ...");
    localStringBuilder.append('>');
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  public static <T> UnmodifiableIterator<T> singletonIterator(T paramT)
  {
    return new UnmodifiableIterator()
    {
      boolean done;

      public boolean hasNext()
      {
        if (!this.done);
        for (boolean bool = true; ; bool = false)
          return bool;
      }

      public T next()
      {
        if (this.done)
          throw new NoSuchElementException();
        this.done = true;
        return this.val$value;
      }
    };
  }

  public static String toString(Iterator<?> paramIterator)
  {
    if (!paramIterator.hasNext());
    StringBuilder localStringBuilder;
    for (String str = "[]"; ; str = ']')
    {
      return str;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append('[').append(paramIterator.next());
      while (paramIterator.hasNext())
        localStringBuilder.append(", ").append(paramIterator.next());
    }
  }

  public static <F, T> Iterator<T> transform(Iterator<F> paramIterator, final Function<? super F, ? extends T> paramFunction)
  {
    Preconditions.checkNotNull(paramIterator);
    Preconditions.checkNotNull(paramFunction);
    return new Iterator()
    {
      public boolean hasNext()
      {
        return this.val$fromIterator.hasNext();
      }

      public T next()
      {
        Object localObject = this.val$fromIterator.next();
        return paramFunction.apply(localObject);
      }

      public void remove()
      {
        this.val$fromIterator.remove();
      }
    };
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.Iterators
 * JD-Core Version:    0.6.2
 */
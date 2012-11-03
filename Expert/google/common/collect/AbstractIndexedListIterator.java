package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E>
{
  private int position;
  private final int size;

  protected AbstractIndexedListIterator(int paramInt)
  {
    this(paramInt, 0);
  }

  protected AbstractIndexedListIterator(int paramInt1, int paramInt2)
  {
    Preconditions.checkPositionIndex(paramInt2, paramInt1);
    this.size = paramInt1;
    this.position = paramInt2;
  }

  protected abstract E get(int paramInt);

  public final boolean hasNext()
  {
    if (this.position < this.size);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public final boolean hasPrevious()
  {
    if (this.position > 0);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public final E next()
  {
    if (!hasNext())
      throw new NoSuchElementException();
    int i = this.position;
    this.position = (i + 1);
    return get(i);
  }

  public final int nextIndex()
  {
    return this.position;
  }

  public final E previous()
  {
    if (!hasPrevious())
      throw new NoSuchElementException();
    int i = -1 + this.position;
    this.position = i;
    return get(i);
  }

  public final int previousIndex()
  {
    return -1 + this.position;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.AbstractIndexedListIterator
 * JD-Core Version:    0.6.2
 */
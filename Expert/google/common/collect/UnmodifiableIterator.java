package com.google.common.collect;

import java.util.Iterator;

public abstract class UnmodifiableIterator<E>
  implements Iterator<E>
{
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.UnmodifiableIterator
 * JD-Core Version:    0.6.2
 */
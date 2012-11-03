package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.LinkedList;

public final class Lists
{
  static int computeArrayListCapacity(int paramInt)
  {
    if (paramInt >= 0);
    for (boolean bool = true; ; bool = false)
    {
      Preconditions.checkArgument(bool);
      return Ints.saturatedCast(5L + paramInt + paramInt / 10);
    }
  }

  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList();
  }

  public static <E> LinkedList<E> newLinkedList()
  {
    return new LinkedList();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.Lists
 * JD-Core Version:    0.6.2
 */
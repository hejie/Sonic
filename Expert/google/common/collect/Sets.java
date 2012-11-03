package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public final class Sets
{
  static boolean equalsImpl(Set<?> paramSet, Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramSet == paramObject)
      bool2 = bool1;
    while (true)
    {
      return bool2;
      if ((paramObject instanceof Set))
      {
        Set localSet = (Set)paramObject;
        try
        {
          if (paramSet.size() == localSet.size())
          {
            boolean bool3 = paramSet.containsAll(localSet);
            if (!bool3);
          }
          while (true)
          {
            bool2 = bool1;
            break;
            bool1 = false;
          }
        }
        catch (NullPointerException localNullPointerException)
        {
        }
        catch (ClassCastException localClassCastException)
        {
        }
      }
    }
  }

  static int hashCodeImpl(Set<?> paramSet)
  {
    int i = 0;
    Iterator localIterator = paramSet.iterator();
    if (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (localObject != null);
      for (int j = localObject.hashCode(); ; j = 0)
      {
        i += j;
        break;
      }
    }
    return i;
  }

  public static <E> TreeSet<E> newTreeSet(Comparator<? super E> paramComparator)
  {
    return new TreeSet((Comparator)Preconditions.checkNotNull(paramComparator));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.Sets
 * JD-Core Version:    0.6.2
 */
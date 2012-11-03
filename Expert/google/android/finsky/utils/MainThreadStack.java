package com.google.android.finsky.utils;

import java.util.Stack;

public class MainThreadStack<T> extends Stack<T>
{
  public boolean isEmpty()
  {
    Utils.ensureOnMainThread();
    return super.isEmpty();
  }

  public T peek()
  {
    Utils.ensureOnMainThread();
    return super.peek();
  }

  public T pop()
  {
    Utils.ensureOnMainThread();
    return super.pop();
  }

  public T push(T paramT)
  {
    Utils.ensureOnMainThread();
    return super.push(paramT);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.MainThreadStack
 * JD-Core Version:    0.6.2
 */
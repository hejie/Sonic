package com.google.common.collect;

public abstract class ForwardingObject
{
  protected abstract Object delegate();

  public String toString()
  {
    return delegate().toString();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.collect.ForwardingObject
 * JD-Core Version:    0.6.2
 */
package com.google.common.base;

import java.io.Serializable;

public final class Suppliers
{
  static class ExpiringMemoizingSupplier<T>
    implements Supplier<T>, Serializable
  {
    private static final long serialVersionUID;
    final Supplier<T> delegate;
    final long durationNanos;
  }

  static class MemoizingSupplier<T>
    implements Supplier<T>, Serializable
  {
    private static final long serialVersionUID;
    final Supplier<T> delegate;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.common.base.Suppliers
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.previews;

public abstract class SongSnippetStatusListener extends StatusListener
{
  public void completed()
  {
    update(5, false);
  }

  public void error()
  {
    update(4, false);
  }

  public void paused()
  {
    update(3, true);
  }

  public void playing()
  {
    update(2, true);
  }

  public void prepared()
  {
    update(2, true);
  }

  public void preparing()
  {
    update(1, true);
  }

  public void unrolling()
  {
    update(1, true);
  }

  protected abstract void update(int paramInt, boolean paramBoolean);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.previews.SongSnippetStatusListener
 * JD-Core Version:    0.6.2
 */
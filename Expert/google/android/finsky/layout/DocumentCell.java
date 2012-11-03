package com.google.android.finsky.layout;

import android.view.View.OnClickListener;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.BitmapLoader;

public abstract interface DocumentCell
{
  public abstract void bind(Document paramDocument1, Document paramDocument2, BitmapLoader paramBitmapLoader, boolean paramBoolean, View.OnClickListener paramOnClickListener);
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.DocumentCell
 * JD-Core Version:    0.6.2
 */
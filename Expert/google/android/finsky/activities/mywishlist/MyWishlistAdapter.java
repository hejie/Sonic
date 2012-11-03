package com.google.android.finsky.activities.mywishlist;

import android.content.Context;
import com.google.android.finsky.adapters.BucketedListAdapter;
import com.google.android.finsky.api.model.BucketedList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.BitmapLoader;

public class MyWishlistAdapter extends BucketedListAdapter
{
  public MyWishlistAdapter(Context paramContext, NavigationManager paramNavigationManager, BitmapLoader paramBitmapLoader, DfeToc paramDfeToc, BucketedList<?> paramBucketedList, int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    super(paramContext, paramNavigationManager, paramBitmapLoader, paramDfeToc, paramBucketedList, paramInt1, paramInt2, paramInt3, paramString);
    ignoreCustomTiles();
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.mywishlist.MyWishlistAdapter
 * JD-Core Version:    0.6.2
 */
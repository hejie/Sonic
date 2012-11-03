package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.res.Resources;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.PackageStateRepository;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.library.LibraryReplicators;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.remoting.protos.ModifyLibrary.ModifyLibraryResponse;

public class WishlistHelper
{
  public static boolean isInWishlist(Document paramDocument, Account paramAccount)
  {
    LibraryEntry localLibraryEntry = LibraryEntry.fromDocument(paramAccount.name, "u-wl", paramDocument, 1);
    return FinskyApp.get().getLibraries().getAccountLibrary(paramAccount).contains(localLibraryEntry);
  }

  public static void processWishlistClick(Document paramDocument, DfeApi paramDfeApi, final WishlistStatusListener paramWishlistStatusListener)
  {
    if (paramDocument == null)
    {
      FinskyLog.w("Tried to wishlist an item but there is no document active", new Object[0]);
      return;
    }
    boolean bool1 = isInWishlist(paramDocument, paramDfeApi.getAccount());
    final String str1 = paramDocument.getDocId();
    final String str2 = paramDocument.getTitle();
    final Resources localResources = FinskyApp.get().getResources();
    Response.Listener local1 = new Response.Listener()
    {
      public void onResponse(ModifyLibrary.ModifyLibraryResponse paramAnonymousModifyLibraryResponse)
      {
        FinskyApp.get().getLibraryReplicators().applyLibraryUpdate(this.val$dfeApi.getAccount(), paramAnonymousModifyLibraryResponse.getLibraryUpdate(), "modifed_wishlist");
      }
    };
    Response.ErrorListener local2 = new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        int i;
        if (this.val$wasInWishlist)
        {
          i = 2131165799;
          Resources localResources = localResources;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = str2;
          String str = localResources.getString(i, arrayOfObject);
          Toast.makeText(FinskyApp.get(), str, 1).show();
          if (!this.val$wasInWishlist)
            break label94;
          FinskyLog.e("Unable to remove from wishlist: %s", new Object[] { paramAnonymousVolleyError });
        }
        while (true)
        {
          paramWishlistStatusListener.onWishlistStatusChanged(str1, this.val$wasInWishlist);
          return;
          i = 2131165798;
          break;
          label94: FinskyLog.e("Unable to add to wishlist: %s", new Object[] { paramAnonymousVolleyError });
        }
      }
    };
    if (bool1)
    {
      paramDfeApi.removeFromLibrary(Lists.newArrayList(new String[] { str1 }), "u-wl", local1, local2);
      label101: if (bool1)
        break label161;
    }
    label161: for (boolean bool2 = true; ; bool2 = false)
    {
      paramWishlistStatusListener.onWishlistStatusChanged(str1, bool2);
      break;
      Toast.makeText(FinskyApp.get(), 2131165793, 0).show();
      paramDfeApi.addToLibrary(Lists.newArrayList(new String[] { str1 }), "u-wl", local1, local2);
      break label101;
    }
  }

  public static void processWishlistClick(NavigationManager paramNavigationManager, DfeApi paramDfeApi, WishlistStatusListener paramWishlistStatusListener)
  {
    processWishlistClick(paramNavigationManager.getCurrentDocument(), paramDfeApi, paramWishlistStatusListener);
  }

  public static boolean shouldHideWishlistAction(Document paramDocument, DfeApi paramDfeApi)
  {
    boolean bool = false;
    Account localAccount = LibraryUtils.getOwnerWithCurrentAccount(paramDocument, FinskyApp.get().getLibraries(), paramDfeApi.getAccount());
    int i = 0;
    if (paramDocument.getDocumentType() == 1)
    {
      String str = paramDocument.getAppDetails().getPackageName();
      if (FinskyApp.get().getPackageInfoRepository().get(str) == null)
        break label86;
    }
    label86: for (i = 1; ; i = 0)
    {
      if ((localAccount != null) || (i != 0) || (!((Boolean)G.wishlistEnabled.get()).booleanValue()))
        bool = true;
      return bool;
    }
  }

  public static abstract interface WishlistStatusListener
  {
    public abstract void onWishlistStatusChanged(String paramString, boolean paramBoolean);
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.utils.WishlistHelper
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.services;

import android.accounts.Account;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.remoting.protos.Toc.TocResponse;
import com.google.android.finsky.utils.Utils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MarketCatalogService extends Service
{
  private final IMarketCatalogService.Stub mBinder = new IMarketCatalogService.Stub()
  {
    public boolean isBackendEnabled(String paramAnonymousString, final int paramAnonymousInt)
    {
      Utils.ensureNotOnMainThread();
      final Semaphore localSemaphore = new Semaphore(0);
      final boolean[] arrayOfBoolean = new boolean[1];
      Account localAccount = AccountHandler.findAccount(paramAnonymousString, MarketCatalogService.this);
      FinskyApp.get().getDfeApi(localAccount.name).getToc(new Response.Listener()
      {
        public void onResponse(Toc.TocResponse paramAnonymous2TocResponse)
        {
          Iterator localIterator = paramAnonymous2TocResponse.getCorpusList().iterator();
          while (localIterator.hasNext())
          {
            Toc.CorpusMetadata localCorpusMetadata = (Toc.CorpusMetadata)localIterator.next();
            if (paramAnonymousInt == localCorpusMetadata.getBackend())
              arrayOfBoolean[0] = true;
          }
          localSemaphore.release();
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymous2VolleyError)
        {
          localSemaphore.release();
        }
      }
      , false);
      localSemaphore.acquireUninterruptibly();
      MarketCatalogService.this.stopSelf();
      return arrayOfBoolean[0];
    }
  };

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.services.MarketCatalogService
 * JD-Core Version:    0.6.2
 */
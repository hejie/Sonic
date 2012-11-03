package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.config.PreferenceFile.PrefixSharedPreference;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.remoting.protos.Toc.TocResponse;
import com.google.android.finsky.remoting.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;

public class TosAckedReceiver extends BroadcastReceiver
{
  private void ackTos(final String paramString, boolean paramBoolean, final DfeToc paramDfeToc)
  {
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(paramString);
    if (localDfeApi == null)
      FinskyLog.e("Could not get DFE API, returning.", new Object[0]);
    while (true)
    {
      return;
      localDfeApi.acceptTos(paramDfeToc.getTosToken(), Boolean.valueOf(paramBoolean), new Response.Listener()
      {
        public void onResponse(Tos.AcceptTosResponse paramAnonymousAcceptTosResponse)
        {
          FinskyPreferences.acceptedTosToken.get(paramString).put(paramDfeToc.getTosToken());
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          FinskyLog.e("Error sending TOS acceptance: %s", new Object[] { paramAnonymousVolleyError });
        }
      });
    }
  }

  private void fetchToc(final String paramString, final boolean paramBoolean)
  {
    DfeApi localDfeApi = FinskyApp.get().getDfeApi(paramString);
    if (localDfeApi == null)
      FinskyLog.e("Could not get DFE API, returning.", new Object[0]);
    while (true)
    {
      return;
      localDfeApi.getToc(new Response.Listener()
      {
        public void onResponse(Toc.TocResponse paramAnonymousTocResponse)
        {
          TosAckedReceiver.this.ackTos(paramString, paramBoolean, new DfeToc(paramAnonymousTocResponse));
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          FinskyLog.e("Error fetching TOC: %s", new Object[] { paramAnonymousVolleyError });
        }
      }
      , false);
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent == null) || (paramIntent.getExtras() == null))
      FinskyLog.w("Invalid Broadcast: requires extras.", new Object[0]);
    while (true)
    {
      return;
      Bundle localBundle = paramIntent.getExtras();
      String str = localBundle.getString("TosAckedReceiver.account");
      boolean bool = localBundle.getBoolean("TosAckedReceiver.optIn");
      if (str == null)
        FinskyLog.w("Invalid Broadcast: no account.", new Object[0]);
      else
        fetchToc(str, bool);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.receivers.TosAckedReceiver
 * JD-Core Version:    0.6.2
 */
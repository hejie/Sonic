package com.google.android.finsky.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.android.volley.NetworkError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.remoting.protos.Toc.CorpusMetadata;
import com.google.android.finsky.remoting.protos.Toc.TocResponse;
import java.util.Iterator;
import java.util.List;

public abstract class TrampolineActivity extends FragmentActivity
  implements SimpleAlertDialog.Listener
{
  public static PendingIntent getLaunchIntent(Context paramContext, Class<? extends TrampolineActivity> paramClass, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, paramClass);
    localIntent.addFlags(268435456);
    localIntent.putExtra("appWidgetId", paramInt);
    return PendingIntent.getActivity(paramContext, paramInt, localIntent, 0);
  }

  private void initialize(DfeToc paramDfeToc)
  {
    int i = paramDfeToc.getCorpusList().size();
    if (enableMultiCorpus());
    for (int j = 1; ; j = 0)
    {
      int k = j + i;
      if ((shouldAllowConfiguration()) && (k > 1))
        break;
      finish(-1, "apps");
      return;
    }
    Intent localIntent = new Intent(this, WidgetConfigurationActivity.class);
    localIntent.putExtra("enableMultiCorpus", enableMultiCorpus());
    localIntent.putExtra("dfeToc", paramDfeToc);
    Iterator localIterator = paramDfeToc.getCorpusList().iterator();
    label94: Toc.CorpusMetadata localCorpusMetadata;
    String str1;
    String str2;
    if (localIterator.hasNext())
    {
      localCorpusMetadata = (Toc.CorpusMetadata)localIterator.next();
      localIntent.putExtra("backend_" + localCorpusMetadata.getBackend(), isBackendEnabled(localCorpusMetadata.getBackend()));
      str1 = "name_" + localCorpusMetadata.getBackend();
      str2 = getCorpusName(localCorpusMetadata.getBackend());
      if (!TextUtils.isEmpty(str2))
        break label253;
    }
    label253: for (String str3 = localCorpusMetadata.getName(); ; str3 = str2)
    {
      localIntent.putExtra(str1, str3);
      break label94;
      localIntent.putExtra("name_0", getCorpusName(0));
      localIntent.putExtra("dialog_title", getDialogTitle());
      startActivityForResult(localIntent, 1);
      break;
    }
  }

  private void showNetworkNecessaryDialog()
  {
    FragmentManager localFragmentManager = getSupportFragmentManager();
    if ((localFragmentManager == null) || (localFragmentManager.findFragmentByTag("Dialog.NoNetworkConnection") != null));
    while (true)
    {
      return;
      SimpleAlertDialog.newInstance(2131165805, 2131165599, -1).show(localFragmentManager, "Dialog.NoNetworkConnection");
    }
  }

  protected abstract boolean enableMultiCorpus();

  public void finish(int paramInt, String paramString)
  {
    int i = getIntent().getIntExtra("appWidgetId", 0);
    if (paramString != null)
      WidgetTypeMap.get(this).put(getWidgetClass(), i, paramString);
    Intent localIntent1 = new Intent();
    localIntent1.putExtra("appWidgetId", i);
    setResult(paramInt, localIntent1);
    finish();
    int[] arrayOfInt = { i };
    Intent localIntent2 = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
    localIntent2.putExtra("appWidgetIds", arrayOfInt);
    localIntent2.setClass(this, getWidgetClass());
    sendBroadcast(localIntent2);
    if (Build.VERSION.SDK_INT >= 11)
      AppWidgetManager.getInstance(this).notifyAppWidgetViewDataChanged(arrayOfInt, 2131231184);
  }

  protected String getCorpusName(int paramInt)
  {
    return null;
  }

  protected int getDialogTitle()
  {
    return 2131165393;
  }

  protected abstract Class<? extends BaseWidgetProvider> getWidgetClass();

  protected boolean isBackendEnabled(int paramInt)
  {
    return true;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str = null;
    if (paramIntent != null)
      str = WidgetUtils.translate(paramIntent.getIntExtra("backend", 3));
    finish(paramInt2, str);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    DfeApi localDfeApi = FinskyApp.get().getDfeApi();
    if (localDfeApi == null)
      finish(-1, null);
    while (true)
    {
      return;
      DfeToc localDfeToc = FinskyApp.get().getToc();
      if (localDfeToc != null)
        initialize(localDfeToc);
      else
        localDfeApi.getToc(new Response.Listener()
        {
          public void onResponse(Toc.TocResponse paramAnonymousTocResponse)
          {
            DfeToc localDfeToc = new DfeToc(paramAnonymousTocResponse);
            FinskyApp.get().setToc(localDfeToc);
            TrampolineActivity.this.initialize(localDfeToc);
          }
        }
        , new Response.ErrorListener()
        {
          public void onErrorResponse(VolleyError paramAnonymousVolleyError)
          {
            if ((paramAnonymousVolleyError instanceof NetworkError))
              TrampolineActivity.this.showNetworkNecessaryDialog();
            while (true)
            {
              return;
              TrampolineActivity.this.setResult(0);
              TrampolineActivity.this.finish();
            }
          }
        }
        , false);
    }
  }

  public void onNegativeClick(int paramInt, Bundle paramBundle)
  {
    setResult(0);
    finish();
  }

  public void onPositiveClick(int paramInt, Bundle paramBundle)
  {
    setResult(0);
    finish();
  }

  protected boolean shouldAllowConfiguration()
  {
    return true;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.widget.TrampolineActivity
 * JD-Core Version:    0.6.2
 */
package com.google.android.finsky.layout;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.exploreactivity.ExploreActivity;

public class ExplorePanel extends FrameLayout
{
  private Activity mActivity;
  private Document mDocument;
  private View mExploreButton;
  private Fragment mFragment;
  IntentFilter mNetworkStateChangedFilter;
  BroadcastReceiver mNetworkStateIntentReceiver;
  private View mWifiView;

  public ExplorePanel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void doExplorerWifiAlert()
  {
    SimpleAlertDialog localSimpleAlertDialog = SimpleAlertDialog.newInstance(2131165790, 2131165621, 2131165273);
    localSimpleAlertDialog.setCallback(this.mFragment, 5, null);
    localSimpleAlertDialog.show(this.mFragment.getFragmentManager(), "explorer_wifi");
  }

  private void setupExplorer()
  {
    int i = 1;
    int j = 0;
    if ((((Boolean)G.explorerEnabled.get()).booleanValue()) && (this.mDocument.getBackend() == 2))
    {
      setVisibility(0);
      NetworkInfo localNetworkInfo = ((ConnectivityManager)FinskyApp.get().getSystemService("connectivity")).getNetworkInfo(i);
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
      {
        View localView = this.mWifiView;
        if (i != 0)
          j = 8;
        localView.setVisibility(j);
        this.mWifiView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ExplorePanel.this.doExplorerWifiAlert();
          }
        });
        this.mExploreButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Intent localIntent = ExploreActivity.createIntent(ExplorePanel.this.mFragment.getActivity().getApplicationContext(), ExplorePanel.this.mDocument);
            ExplorePanel.this.mFragment.getActivity().startActivity(localIntent);
          }
        });
      }
    }
    while (true)
    {
      return;
      i = 0;
      break;
      setVisibility(8);
    }
  }

  public void configure(Document paramDocument, Fragment paramFragment)
  {
    this.mDocument = paramDocument;
    this.mFragment = paramFragment;
    this.mActivity = this.mFragment.getActivity();
    if (this.mNetworkStateIntentReceiver == null)
    {
      this.mNetworkStateChangedFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
      this.mNetworkStateIntentReceiver = new BroadcastReceiver()
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          ExplorePanel.this.setupExplorer();
        }
      };
      this.mActivity.registerReceiver(this.mNetworkStateIntentReceiver, this.mNetworkStateChangedFilter);
    }
    setupExplorer();
    requestLayout();
  }

  public void onDetachedFromWindow()
  {
    if ((this.mNetworkStateIntentReceiver != null) && (this.mActivity != null))
    {
      this.mActivity.unregisterReceiver(this.mNetworkStateIntentReceiver);
      this.mNetworkStateIntentReceiver = null;
    }
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mWifiView = findViewById(2131231020);
    this.mExploreButton = findViewById(2131231019);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = this.mExploreButton.getMeasuredWidth();
    int j = this.mExploreButton.getMeasuredHeight();
    this.mWifiView.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(j, 1073741824));
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.layout.ExplorePanel
 * JD-Core Version:    0.6.2
 */